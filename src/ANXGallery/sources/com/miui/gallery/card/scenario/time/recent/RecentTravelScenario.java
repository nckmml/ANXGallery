package com.miui.gallery.card.scenario.time.recent;

import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.time.LocationScenario;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class RecentTravelScenario extends LocationScenario {
    public RecentTravelScenario() {
        super(114, 2, 6);
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        return getDatePeriodFromRecord(record);
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_title_somewhere, new Object[]{record.getLocation()});
    }

    public boolean onPrepare(List<Record> list, List<Card> list2) {
        List<Long> recordStartTimesFromRecordAndCards = getRecordStartTimesFromRecordAndCards(list, list2, true);
        long currentTimeMillis = DateUtils.getCurrentTimeMillis();
        long dateTime = DateUtils.getDateTime(currentTimeMillis);
        List<LocationScenario.MediaItem> list3 = (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, PROJECTION, String.format(Locale.US, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType=1 AND exifImageWidth > 1000 AND exifImageLength > 1000 AND (lower(mimeType) NOT IN ('image/gif', 'image/x-adobe-dng')) AND localGroupId = (SELECT _id FROM cloud WHERE serverId=1) AND mixedDateTime > %s AND mixedDateTime < %s", new Object[]{Long.valueOf(dateTime - 2592000000L), Long.valueOf(dateTime)}), (String[]) null, "mixedDateTime DESC", new SafeDBUtil.QueryHandler<List<LocationScenario.MediaItem>>() {
            public List<LocationScenario.MediaItem> handle(Cursor cursor) {
                return LocationScenario.getMediaItemsFromCursor(cursor, true);
            }
        });
        if (!MiscUtil.isValid(list3)) {
            return false;
        }
        String str = null;
        LinkedList linkedList = null;
        LocationScenario.MediaItem mediaItem = null;
        for (LocationScenario.MediaItem mediaItem2 : list3) {
            if (str == null) {
                str = mediaItem2.mCity;
            } else if (linkedList != null) {
                if (!TextUtils.equals(mediaItem.mCity, mediaItem2.mCity)) {
                    break;
                }
                linkedList.add(mediaItem2);
            } else if (!TextUtils.isEmpty(mediaItem2.mCity) && !TextUtils.equals(mediaItem2.mCity, str)) {
                if (currentTimeMillis - mediaItem2.mDateTime >= 259200000) {
                    return false;
                }
                linkedList = new LinkedList();
                linkedList.add(mediaItem2);
                mediaItem = mediaItem2;
            }
        }
        if (MiscUtil.isValid(linkedList)) {
            LocationScenario.MediaItem mediaItem3 = (LocationScenario.MediaItem) linkedList.get(linkedList.size() - 1);
            LocationScenario.MediaItem mediaItem4 = (LocationScenario.MediaItem) linkedList.get(0);
            if (mediaItem4.mDateTime - mediaItem3.mDateTime <= 864000000 && !recordStartTimesFromRecordAndCards.contains(Long.valueOf(DateUtils.getDateTime(mediaItem3.mDateTime)))) {
                this.mTargetCity = mediaItem.mCity;
                setStartTime(mediaItem3.mDateTime);
                setEndTime(mediaItem4.mDateTime);
                return true;
            }
        }
        return false;
    }
}
