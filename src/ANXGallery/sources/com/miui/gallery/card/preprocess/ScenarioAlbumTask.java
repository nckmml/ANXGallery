package com.miui.gallery.card.preprocess;

import android.accounts.Account;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.CardManager;
import com.miui.gallery.card.CardUtil;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.Scenario;
import com.miui.gallery.card.scenario.ScenarioTrigger;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.cloud.card.SyncCardFromServer;
import com.miui.gallery.cloud.card.model.CardInfo;
import com.miui.gallery.cloud.card.model.CardInfoList;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.GalleryDateUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StaticContext;
import com.miui.gallery.util.SyncUtil;
import com.xiaomi.micloudsdk.request.utils.CloudUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public class ScenarioAlbumTask extends ScenarioTask {

    public enum CardResult {
        CREATED,
        INPUT_ERROR,
        HAVE_UNPROCESSED_IMAGES,
        DUPLICATED,
        NO_ENOUGH_IMAGE,
        IMAGE_PROCESS_FAIL,
        COVER_DUPLICATED
    }

    public ScenarioAlbumTask(int i) {
        super(i);
    }

    private void statScenarioCreateFailed() {
        HashMap hashMap = new HashMap();
        hashMap.put("reason", "No enough image");
        GallerySamplingStatHelper.recordCountEvent("assistant", "assistant_card_create_failed", hashMap);
    }

    private void updateRecord(Record record, boolean z) {
        if (record != null) {
            record.setState(z ? 2 : 3);
            GalleryEntityManager.getInstance().update(record);
        }
    }

    public CardResult generateCard(JSONObject jSONObject, Record record, boolean z) {
        CardInfoList cardInfoList;
        Record record2 = record;
        if (record2 == null) {
            return CardResult.INPUT_ERROR;
        }
        Scenario scenarioById = new ScenarioTrigger().getScenarioById(record.getScenarioId());
        int i = 0;
        if (scenarioById == null) {
            updateRecord(record2, false);
            return CardResult.INPUT_ERROR;
        }
        Log.d("ScenarioAlbumTask", "ScenarioAlbumTask %d begin!", (Object) Integer.valueOf(scenarioById.getScenarioId()));
        List<MediaFeatureItem> queryMediaItemByIds = queryMediaItemByIds(record.getMediaIds());
        if (!MiscUtil.isValid(queryMediaItemByIds)) {
            Log.d("ScenarioAlbumTask", "no media item found");
            updateRecord(record2, false);
            statScenarioCreateFailed();
            return CardResult.INPUT_ERROR;
        }
        Log.d("ScenarioAlbumTask", "media count:" + queryMediaItemByIds.size());
        List<MediaFeatureItem> unProcessedMediaItems = CardUtil.getUnProcessedMediaItems(queryMediaItemByIds);
        if (MiscUtil.isValid(unProcessedMediaItems) && !z) {
            return CardResult.HAVE_UNPROCESSED_IMAGES;
        }
        if (processImages(jSONObject, unProcessedMediaItems, false, true)) {
            Log.w("ScenarioAlbumTask", "process %d images success", Integer.valueOf(unProcessedMediaItems.size()));
            CardUtil.bindImageFeatures(queryMediaItemByIds);
            List<MediaFeatureItem> selectedImages = getSelectedImages(queryMediaItemByIds);
            int minSelectedImageCount = scenarioById.getMinSelectedImageCount();
            int maxSelectedImageCount = scenarioById.getMaxSelectedImageCount();
            if (selectedImages == null || selectedImages.size() < minSelectedImageCount) {
                Log.d("ScenarioAlbumTask", "no enough selected Images from row images");
                updateRecord(record2, false);
                statScenarioCreateFailed();
                return CardResult.NO_ENOUGH_IMAGE;
            }
            if (selectedImages.size() > maxSelectedImageCount) {
                Collections.sort(selectedImages);
                while (selectedImages.size() > maxSelectedImageCount) {
                    selectedImages.remove(maxSelectedImageCount);
                }
            }
            Uri albumUri = CardUtil.getAlbumUri("album");
            String str = null;
            Card.Builder imageUri = new Card.Builder(GalleryApp.sGetAndroidContext()).setTitle(scenarioById.generateTitle(record2, selectedImages)).setDescription(scenarioById.generateDescription(record2, selectedImages)).setDeletable(scenarioById.isDeletable()).setType(0).setImageUri((Uri) null);
            if (albumUri != null) {
                str = albumUri.toString();
            }
            Card build = imageUri.setDetailUrl(str).setUniqueKey(record.getUniqueKey()).setAllMediaSha1s(CardUtil.getSha1sFromImages(queryMediaItemByIds)).setSelectedMediaSha1s(CardUtil.getSha1sFromImages(selectedImages)).setCoverMediaFeatureItems(CardUtil.getCardCovers(selectedImages)).setScenarioId(record.getScenarioId()).setCreateBy(0).setValidStartTime(0).setValidEndTime(Long.MAX_VALUE).build();
            List<Card> query = GalleryEntityManager.getInstance().query(Card.class, "ignored = 0 AND scenarioId = " + record.getScenarioId() + " AND " + "createTime" + " > " + (System.currentTimeMillis() - 604800000), (String[]) null, "createTime desc", (String) null);
            if (MiscUtil.isValid(query)) {
                for (Card isDuplicated : query) {
                    if (CardUtil.isDuplicated(build, isDuplicated)) {
                        updateRecord(record2, false);
                        return CardResult.DUPLICATED;
                    }
                }
            }
            Account xiaomiAccount = CloudUtils.getXiaomiAccount();
            if (!(xiaomiAccount == null || (cardInfoList = new SyncCardFromServer(xiaomiAccount).getCardInfoList(GalleryCloudSyncTagUtils.getCardSyncTag(xiaomiAccount), GalleryCloudSyncTagUtils.getCardSyncInfo(xiaomiAccount), 10)) == null)) {
                ArrayList<CardInfo> galleryCards = cardInfoList.getGalleryCards();
                if (MiscUtil.isValid(galleryCards)) {
                    for (CardInfo next : galleryCards) {
                        if (next != null && !next.isStatusDelete() && CardUtil.isDuplicated(build, next)) {
                            Log.e("ScenarioAlbumTask", "Create a local card duplicated with server");
                            updateRecord(record2, false);
                            SyncUtil.requestSync(StaticContext.sGetAndroidContext(), new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(68).build());
                            return CardResult.DUPLICATED;
                        }
                    }
                }
            }
            List<Card> query2 = GalleryEntityManager.getInstance().query(Card.class, (String) null, (String[]) null, "createTime desc", String.format(Locale.US, "%s,%s", new Object[]{0, 1}));
            if (MiscUtil.isValid(query2)) {
                List<MediaFeatureItem> coverMediaFeatureItems = build.getCoverMediaFeatureItems();
                List<MediaFeatureItem> coverMediaFeatureItems2 = query2.get(0).getCoverMediaFeatureItems();
                for (MediaFeatureItem sha1 : coverMediaFeatureItems) {
                    String sha12 = sha1.getSha1();
                    Iterator<MediaFeatureItem> it = coverMediaFeatureItems2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (TextUtils.equals(sha12, it.next().getSha1())) {
                                i++;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (i >= 3) {
                    Log.w("ScenarioAlbumTask", "cover images duplicate");
                    GallerySamplingStatHelper.recordCountEvent("assistant", "assistant_card_cover_duplicate");
                    return CardResult.COVER_DUPLICATED;
                }
            }
            CardManager.getInstance().add(build, true);
            Log.d("ScenarioAlbumTask", "Card generated");
            HashMap hashMap = new HashMap();
            hashMap.put("scenario_id", String.valueOf(scenarioById.getScenarioId()));
            long lastCardCreateTime = CardUtil.getLastCardCreateTime();
            if (lastCardCreateTime > 0) {
                hashMap.put("date_interval_with_last_card", String.valueOf(GalleryDateUtils.daysBetween(lastCardCreateTime, System.currentTimeMillis())));
            }
            GallerySamplingStatHelper.recordCountEvent("assistant", "assistant_card_created_success", hashMap);
            updateRecord(record2, true);
            return CardResult.CREATED;
        }
        Log.w("ScenarioAlbumTask", "process images failed");
        return CardResult.IMAGE_PROCESS_FAIL;
    }

    public DownloadType onGetImageDownloadType() {
        return DownloadType.MICRO;
    }

    public boolean onProcess(JSONObject jSONObject, long j) throws Exception {
        Record record = (Record) GalleryEntityManager.getInstance().find(Record.class, j);
        if (record == null) {
            return false;
        }
        if (isCancelled()) {
            Log.d("ScenarioAlbumTask", "task is cancelled");
            return false;
        }
        if (GalleryPreferences.Sync.getPowerCanSync() || GalleryPreferences.Sync.getIsPlugged()) {
            Log.d("ScenarioAlbumTask", "power meet requirements，start generate card");
            generateCard(jSONObject, record, true);
        } else {
            Log.d("ScenarioAlbumTask", "power do not meet requirements，try generate card without processing images");
            if (generateCard(jSONObject, record, false) == CardResult.HAVE_UNPROCESSED_IMAGES) {
                Log.d("ScenarioAlbumTask", "generate card without processing images failed , schedule charging task");
                ScenarioTask.post(10, String.valueOf(record.getId()), record.getId());
            }
        }
        return false;
    }
}
