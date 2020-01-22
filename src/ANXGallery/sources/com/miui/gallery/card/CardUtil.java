package com.miui.gallery.card;

import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.assistant.model.ImageFeature;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.assistant.process.BaseImageTask;
import com.miui.gallery.card.Card;
import com.miui.gallery.cloud.card.model.CardInfo;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.GsonUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CardUtil {
    public static void bindImageFeatures(List<MediaFeatureItem> list) {
        if (MiscUtil.isValid(list)) {
            ArrayList arrayList = new ArrayList(list.size());
            for (MediaFeatureItem id : list) {
                arrayList.add(Long.valueOf(id.getId()));
            }
            List<ImageFeature> query = GalleryEntityManager.getInstance().query(ImageFeature.class, String.format("%s IN (%s)", new Object[]{"imageId", TextUtils.join(",", arrayList)}), (String[]) null, "createTime DESC", (String) null);
            if (MiscUtil.isValid(query)) {
                for (MediaFeatureItem next : list) {
                    Iterator<ImageFeature> it = query.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ImageFeature next2 = it.next();
                        if (next2.getImageId() == next.getId()) {
                            next.setImageFeature(next2);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static Integer[] concat(Integer[] numArr, Integer[] numArr2) {
        if (numArr == null || numArr.length == 0) {
            return numArr2;
        }
        if (numArr2 == null || numArr2.length == 0) {
            return numArr;
        }
        Integer[] numArr3 = new Integer[(numArr.length + numArr2.length)];
        System.arraycopy(numArr, 0, numArr3, 0, numArr.length);
        System.arraycopy(numArr2, 0, numArr3, numArr.length, numArr2.length);
        return numArr3;
    }

    public static void downloadCoverThumb(List<MediaFeatureItem> list) {
        if (MiscUtil.isValid(list)) {
            for (int i = 0; i < list.size(); i++) {
                MediaFeatureItem mediaFeatureItem = list.get(i);
                DownloadType downloadType = getDownloadType(list.size(), i);
                if ((downloadType == DownloadType.THUMBNAIL && TextUtils.isEmpty(mediaFeatureItem.getThumbnailPath())) || (downloadType == DownloadType.MICRO && TextUtils.isEmpty(mediaFeatureItem.getMicroThumbnailPath()))) {
                    String str = BaseImageTask.getImageFilePath(mediaFeatureItem, downloadType, true).get();
                    if (!TextUtils.isEmpty(str)) {
                        if (downloadType == DownloadType.THUMBNAIL) {
                            mediaFeatureItem.setThumbnailPath(str);
                        } else {
                            mediaFeatureItem.setMicroThumbnailPath(str);
                        }
                    }
                }
            }
        }
    }

    public static Uri getAlbumUri(String str) {
        return GalleryContract.Common.URI_CARD_ACTION.buildUpon().appendQueryParameter("actionType", str).build();
    }

    public static List<MediaFeatureItem> getCardCovers(List<MediaFeatureItem> list) {
        ArrayList arrayList = new ArrayList();
        if (MiscUtil.isValid(list)) {
            Collections.sort(list);
            if (MiscUtil.isValid(list)) {
                for (int i = 0; i < Math.min(list.size(), 5); i++) {
                    arrayList.add(list.get(i));
                }
            }
        }
        return arrayList;
    }

    public static List<MediaFeatureItem> getCoverMediaItemsByServerIds(final List<Long> list) {
        if (!MiscUtil.isValid(list)) {
            return null;
        }
        Uri build = GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).build();
        SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), build, MediaFeatureItem.MEDIA_PROJECTION, "(localGroupId!=-1000) AND " + String.format("%s IN ('%s')", new Object[]{"serverId", TextUtils.join("','", list)}), (String[]) null, "alias_create_time DESC", new SafeDBUtil.QueryHandler<List<MediaFeatureItem>>() {
            /* JADX INFO: finally extract failed */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x003a A[EDGE_INSN: B:23:0x003a->B:12:0x003a ?: BREAK  , SYNTHETIC] */
            /* JADX WARNING: Removed duplicated region for block: B:5:0x0019 A[Catch:{ all -> 0x0050 }] */
            public List<MediaFeatureItem> handle(Cursor cursor) {
                int i;
                if (cursor == null) {
                    return null;
                }
                MediaFeatureItem[] mediaFeatureItemArr = new MediaFeatureItem[Math.max(cursor.getCount(), list.size())];
                while (true) {
                    try {
                        i = 0;
                        if (!cursor.moveToNext()) {
                            MediaFeatureItem mediaFeatureItem = new MediaFeatureItem(cursor);
                            while (true) {
                                if (i < mediaFeatureItemArr.length) {
                                    if (((Long) list.get(i)).longValue() == mediaFeatureItem.getServerId()) {
                                        mediaFeatureItemArr[i] = mediaFeatureItem;
                                    }
                                    i++;
                                }
                            }
                            i = 0;
                            if (!cursor.moveToNext()) {
                                break;
                            }
                        }
                    } catch (Throwable th) {
                        MiscUtil.closeSilently(cursor);
                        throw th;
                    }
                }
                MiscUtil.closeSilently(cursor);
                ArrayList arrayList = new ArrayList();
                int length = mediaFeatureItemArr.length;
                while (i < length) {
                    MediaFeatureItem mediaFeatureItem2 = mediaFeatureItemArr[i];
                    if (mediaFeatureItem2 != null) {
                        arrayList.add(mediaFeatureItem2);
                    }
                    i++;
                }
                return arrayList;
            }
        });
        return null;
    }

    public static DownloadType getDownloadType(int i, int i2) {
        return (i == 1 || i == 2 || i == 3) ? DownloadType.THUMBNAIL : i != 4 ? i != 5 ? DownloadType.MICRO : i2 == 0 ? DownloadType.THUMBNAIL : DownloadType.MICRO : i2 == 3 ? DownloadType.MICRO : DownloadType.THUMBNAIL;
    }

    public static List<Long> getImageIdsFromMediaItems(List<MediaFeatureItem> list) {
        ArrayList arrayList = new ArrayList();
        if (MiscUtil.isValid(list)) {
            for (MediaFeatureItem id : list) {
                arrayList.add(Long.valueOf(id.getId()));
            }
        }
        return arrayList;
    }

    public static long getLastCardCreateTime() {
        List<Card> query = GalleryEntityManager.getInstance().query(Card.class, "ignored = 0", (String[]) null, "createTime desc", String.format(Locale.US, "%s,%s", new Object[]{0, 1}));
        if (MiscUtil.isValid(query)) {
            return query.get(0).getCreateTime();
        }
        return -1;
    }

    public static int getMovieTemplateFromCard(Card card) {
        if (card == null) {
            return 0;
        }
        int scenarioId = card.getScenarioId();
        if (scenarioId <= 0 && card.getUniqueKey() != null) {
            scenarioId = card.getUniqueKey().getScenarioId();
        }
        if (scenarioId != 102) {
            if (scenarioId == 111) {
                return 3;
            }
            if (scenarioId == 118 || scenarioId == 201) {
                return 1;
            }
            if (scenarioId == 10199) {
                return 7;
            }
            if (scenarioId == 114) {
                return 1;
            }
            if (scenarioId == 115) {
                return 3;
            }
            if (scenarioId == 120) {
                return 2;
            }
            if (scenarioId == 121) {
                return 4;
            }
            if (scenarioId == 10099) {
                return 2;
            }
            if (scenarioId == 10100) {
                return 7;
            }
            switch (scenarioId) {
                case 401:
                    return 8;
                case 402:
                    return 9;
                case 403:
                    return 4;
                default:
                    switch (scenarioId) {
                        case 10001:
                        case 10002:
                        case 10003:
                        case 10004:
                        case 10005:
                            return 2;
                    }
            }
        } else if (card.getUniqueKey() != null) {
            try {
                int parseInt = Integer.parseInt(card.getUniqueKey().getPrimaryKey());
                if (parseInt == 200) {
                    return 6;
                }
                if (parseInt == 205) {
                    return 5;
                }
            } catch (NumberFormatException e) {
                Log.e("CardUtil", (Throwable) e);
            }
        }
        return 0;
    }

    public static List<String> getSha1sByServerIds(List<Long> list) {
        if (!MiscUtil.isValid(list)) {
            return null;
        }
        return (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Media.URI_OWNER_ALBUM_MEDIA.buildUpon().appendQueryParameter("remove_duplicate_items", String.valueOf(true)).build(), CardManager.CONVERT_PROJECTION, String.format("%s IN (%s)", new Object[]{"serverId", TextUtils.join(",", list)}), (String[]) null, String.format("%s DESC", new Object[]{"alias_create_time"}), new SafeDBUtil.QueryHandler<List<String>>() {
            public List<String> handle(Cursor cursor) {
                ArrayList arrayList = new ArrayList(cursor != null ? cursor.getCount() : 0);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        arrayList.add(cursor.getString(1));
                    }
                }
                return arrayList;
            }
        });
    }

    public static List<String> getSha1sFromImages(List<MediaFeatureItem> list) {
        ArrayList arrayList = new ArrayList();
        if (MiscUtil.isValid(list)) {
            for (MediaFeatureItem mediaSha1 : list) {
                arrayList.add(mediaSha1.getMediaSha1());
            }
        }
        return arrayList;
    }

    public static List<MediaFeatureItem> getUnProcessedMediaItems(List<MediaFeatureItem> list) {
        ArrayList arrayList = null;
        if (MiscUtil.isValid(list)) {
            List<ImageFeature> query = GalleryEntityManager.getInstance().query(ImageFeature.class, String.format(ImageFeature.ALL_FEATURE_PROCESSED_SELECTION + " AND " + "imageId" + " IN ('%s') ", new Object[]{TextUtils.join("','", getImageIdsFromMediaItems(list))}), (String[]) null);
            arrayList = new ArrayList(list);
            if (MiscUtil.isValid(query)) {
                for (MediaFeatureItem next : list) {
                    Iterator<ImageFeature> it = query.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (TextUtils.equals(it.next().getSha1(), next.getSha1())) {
                                arrayList.remove(next);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static boolean isCoverMediaPathEmpty(List<MediaFeatureItem> list) {
        if (MiscUtil.isValid(list)) {
            int i = 0;
            while (i < list.size()) {
                MediaFeatureItem mediaFeatureItem = list.get(i);
                DownloadType downloadType = getDownloadType(list.size(), i);
                if ((downloadType != DownloadType.THUMBNAIL || FileUtils.isFileExist(mediaFeatureItem.getThumbnailPath())) && (downloadType != DownloadType.MICRO || FileUtils.isFileExist(mediaFeatureItem.getMicroThumbnailPath()) || FileUtils.isFileExist(mediaFeatureItem.getThumbnailPath()))) {
                    i++;
                } else {
                    Log.d("CardUtil", "Image " + mediaFeatureItem.getId() + "has no local image of " + downloadType);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isDuplicated(Card card, Card card2) {
        if (card == null || card2 == null || !TextUtils.equals(card.getDescription(), card2.getDescription()) || card2.getUniqueKey() == null || card.getUniqueKey() == null) {
            return false;
        }
        return card.getUniqueKey().equals(card2.getUniqueKey());
    }

    public static boolean isDuplicated(Card card, CardInfo cardInfo) {
        Card.CardExtraInfo cardExtraInfo;
        if (card == null || cardInfo == null || !TextUtils.equals(card.getDescription(), cardInfo.getDescription()) || (cardExtraInfo = (Card.CardExtraInfo) GsonUtils.fromJson(cardInfo.getExtraInfo(), Card.CardExtraInfo.class)) == null || card.getUniqueKey() == null) {
            return false;
        }
        return card.getUniqueKey().equals(cardExtraInfo.uniqueKey);
    }
}
