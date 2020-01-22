package com.miui.gallery.cloud.card;

import com.miui.gallery.card.Card;
import com.miui.gallery.card.CardManager;
import com.miui.gallery.card.CardUtil;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.card.exception.NoResultException;
import com.miui.gallery.cloud.card.model.CardCreateResult;
import com.miui.gallery.cloud.card.model.CardInfo;
import com.miui.gallery.cloud.card.network.CommonGalleryRequestHelper;
import com.miui.gallery.cloud.card.network.RequestArguments;
import com.miui.gallery.net.base.RequestError;
import com.miui.gallery.util.GsonUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.List;

public class SyncCardToServer {
    private void syncCreateCard(Card card) {
        try {
            updateCard(card, (CardCreateResult) new CommonGalleryRequestHelper(new RequestArguments<CardCreateResult>() {
                public int getMethod() {
                    return 1002;
                }

                public String getUrl() {
                    return HostManager.Story.getCreateCardUrl();
                }
            }).addParam("data", GsonUtils.toString(CardManager.getInstance().getCardInfoFromCard(card))).executeSync());
        } catch (RequestError e) {
            Log.e("SyncCardToServer", "Post CreateCard failed, %s", (Object) e);
        } catch (NoResultException e2) {
            Log.e("SyncCardToServer", "Post CreateCard failed, %s", (Object) e2);
        }
    }

    private void updateCard(Card card, CardCreateResult cardCreateResult) {
        if (card != null && cardCreateResult != null && cardCreateResult.getGalleryCard() != null) {
            if (cardCreateResult.isDuplicate()) {
                CardInfo galleryCard = cardCreateResult.getGalleryCard();
                List<Long> list = null;
                List<Long> mediaList = galleryCard.getMediaInfo() == null ? null : galleryCard.getMediaInfo().getMediaList();
                List<Long> allMediaList = galleryCard.getMediaInfo() == null ? null : galleryCard.getMediaInfo().getAllMediaList();
                if (allMediaList == null) {
                    allMediaList = mediaList;
                }
                if (galleryCard.getMediaInfo() != null) {
                    list = galleryCard.getMediaInfo().getCoverMediaList();
                }
                card.setUpdateTime(galleryCard.getUpdateTime());
                card.setTitle(galleryCard.getTitle());
                card.setDescription(galleryCard.getDescription());
                card.setCardExtraInfo((Card.CardExtraInfo) GsonUtils.fromJson(galleryCard.getExtraInfo(), Card.CardExtraInfo.class));
                card.setAllMediaSha1s(CardUtil.getSha1sByServerIds(allMediaList));
                card.setSelectedMediaSha1s(CardUtil.getSha1sByServerIds(mediaList), "cardCardFromServer");
                card.setCoverMediaFeatureItems(CardUtil.getCoverMediaItemsByServerIds(list));
                card.setScenarioId(galleryCard.getScenarioId());
                card.setServerId(galleryCard.getServerId());
                card.setServerTag(galleryCard.getTag());
                card.setCreateBy(galleryCard.isAppCreate() ^ true ? 1 : 0);
                card.setCreateTime(galleryCard.getSortTime());
                card.setUpdateTime(galleryCard.getUpdateTime());
            } else if (card.getServerTag() < cardCreateResult.getGalleryCard().getTag()) {
                CardInfo galleryCard2 = cardCreateResult.getGalleryCard();
                card.setServerId(galleryCard2.getServerId());
                card.setServerTag(galleryCard2.getTag());
                card.setUpdateTime(galleryCard2.getUpdateTime());
            }
            CardManager.getInstance().update(card, false);
        }
    }

    private void updateCard(Card card, CardInfo cardInfo) {
        if (card != null && cardInfo != null && card.getServerTag() < cardInfo.getTag()) {
            Log.d("SyncCardToServer", "updateCard after SyncCardToServer");
            card.setServerId(cardInfo.getServerId());
            card.setServerTag(cardInfo.getTag());
            card.setUpdateTime(cardInfo.getUpdateTime());
            CardManager.getInstance().update(card, false);
        }
    }

    public void sync() {
        List<Card> unsynchronizedCards = CardManager.getInstance().getUnsynchronizedCards(100);
        if (MiscUtil.isValid(unsynchronizedCards)) {
            for (Card next : unsynchronizedCards) {
                int localFlag = next.getLocalFlag();
                if (localFlag == 0) {
                    syncCreateCard(next);
                } else if (localFlag == 1) {
                    syncDeleteCard(next);
                } else if (localFlag != 2) {
                    Log.e("SyncCardToServer", "get local card flag error:" + next.getLocalFlag());
                } else {
                    syncModifyCard(next);
                }
            }
        }
    }

    public void syncDeleteCard(Card card) {
        try {
            CardInfo cardInfo = (CardInfo) new CommonGalleryRequestHelper(new RequestArguments<CardInfo>() {
                public int getMethod() {
                    return 1002;
                }

                public String getUrl() {
                    return HostManager.Story.getDeleteCardUrl();
                }
            }).addParam("cardId", card.getServerId()).executeSync();
            if (cardInfo == null || !cardInfo.isStatusDelete()) {
                Log.e("SyncCardToServer", "Post DeleteCard failed!");
                return;
            }
            CardManager.getInstance().delete(card, false);
            CardManager.getInstance().recordCardDeleteReason("serverSynced");
        } catch (RequestError e) {
            Log.e("SyncCardToServer", "Post DeleteCard failed, %s", (Object) e);
        } catch (NoResultException e2) {
            Log.e("SyncCardToServer", "Post DeleteCard failed, %s", (Object) e2);
        }
    }

    public void syncModifyCard(Card card) {
        try {
            updateCard(card, (CardInfo) new CommonGalleryRequestHelper(new RequestArguments<CardInfo>() {
                public int getMethod() {
                    return 1002;
                }

                public String getUrl() {
                    return HostManager.Story.getUpdateCardUrl();
                }
            }).addParam("data", GsonUtils.toString(CardManager.getInstance().getCardInfoFromCard(card))).addParam("cardId", card.getServerId()).executeSync());
        } catch (RequestError e) {
            Log.e("SyncCardToServer", "Post ModifyCard failed, %s", (Object) e);
        } catch (NoResultException e2) {
            Log.e("SyncCardToServer", "Post ModifyCard failed, %s", (Object) e2);
        }
    }
}
