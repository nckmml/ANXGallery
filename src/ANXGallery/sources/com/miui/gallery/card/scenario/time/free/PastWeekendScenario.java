package com.miui.gallery.card.scenario.time.free;

import android.database.Cursor;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.time.TimeScenario;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PastWeekendScenario extends TimeScenario {
    protected final String[] PROJECTION = {"DISTINCT sha1", "mixedDateTime"};

    private static class MediaItem {
        public long mDateTime;
        public String mSha1;

        private MediaItem() {
        }
    }

    public PastWeekendScenario() {
        super(404, 4, 4);
    }

    /* access modifiers changed from: private */
    public List<MediaItem> getMediaItemsFromCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    MediaItem mediaItem = new MediaItem();
                    mediaItem.mSha1 = cursor.getString(0);
                    mediaItem.mDateTime = cursor.getLong(1);
                    arrayList.add(mediaItem);
                } finally {
                    cursor.close();
                }
            }
        }
        return arrayList;
    }

    private long getWeekendStart(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.mDateTime <= 0) {
            return -1;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(mediaItem.mDateTime);
        int i = instance.get(7);
        if (i != 7 && i != 1) {
            return -1;
        }
        instance.set(7, 7);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        return getRecordStartTime(record) > 0 ? getDatePeriodFromRecord(record) : "";
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        return getRandomArrayString(R.array.weekend_title);
    }

    public boolean onPrepare(List<Record> list, List<Card> list2) {
        long currentTimeMillis = DateUtils.getCurrentTimeMillis();
        List<Long> recordStartTimesFromRecordAndCards = getRecordStartTimesFromRecordAndCards(list, list2, false);
        long j = 0;
        for (Long next : recordStartTimesFromRecordAndCards) {
            if (next.longValue() > j) {
                j = next.longValue();
            }
        }
        List<MediaItem> list3 = (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, this.PROJECTION, String.format(Locale.US, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType=1 AND exifImageWidth > 1000 AND exifImageLength > 1000 AND (lower(mimeType) NOT IN ('image/gif', 'image/x-adobe-dng')) AND localGroupId = (SELECT _id FROM cloud WHERE serverId=1) AND mixedDateTime > %s AND mixedDateTime < %s", new Object[]{Long.valueOf(j), Long.valueOf(currentTimeMillis - 15552000000L)}), (String[]) null, "mixedDateTime ASC", new SafeDBUtil.QueryHandler<List<MediaItem>>() {
            public List<MediaItem> handle(Cursor cursor) {
                return PastWeekendScenario.this.getMediaItemsFromCursor(cursor);
            }
        });
        if (MiscUtil.isValid(list3)) {
            long j2 = 0;
            for (MediaItem mediaItem : list3) {
                if (mediaItem.mDateTime >= j2) {
                    long weekendStart = getWeekendStart(mediaItem);
                    if (weekendStart <= 0) {
                        continue;
                    } else {
                        long j3 = 172800000 + weekendStart;
                        List<Long> mediaIdsByStartEndTime = getMediaIdsByStartEndTime(weekendStart, j3);
                        if (mediaIdsByStartEndTime == null || mediaIdsByStartEndTime.size() <= getMinImageCount() || recordStartTimesFromRecordAndCards.contains(Long.valueOf(weekendStart))) {
                            j2 = weekendStart + 604800000;
                        } else {
                            setStartTime(weekendStart);
                            setEndTime(j3);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
