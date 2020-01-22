package com.miui.gallery.card.scenario.time;

import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.dao.GalleryEntityManager;
import com.miui.gallery.data.LocationManager;
import java.util.ArrayList;
import java.util.List;

public abstract class LocationScenario extends TimeScenario {
    public static final String[] PROJECTION = {"DISTINCT sha1", "location", "mixedDateTime"};
    protected String mTargetCity;

    public static class MediaItem {
        public String mCity;
        public long mDateTime;
        public String mLocation;
        public String mSha1;
    }

    protected LocationScenario(int i, int i2, int i3) {
        super(i, i2, i3);
    }

    public static String getCityNameFromLoaction(String str) {
        String[] segmentLocation;
        if (!(TextUtils.isEmpty(str) || str.indexOf("/") == -1 || (segmentLocation = LocationManager.segmentLocation(str)) == null)) {
            if (!TextUtils.isEmpty(segmentLocation[2])) {
                return segmentLocation[2];
            }
            if (!TextUtils.isEmpty(segmentLocation[3])) {
                return segmentLocation[3];
            }
            if (!TextUtils.isEmpty(segmentLocation[1])) {
                return segmentLocation[1];
            }
            if (!TextUtils.isEmpty(segmentLocation[0])) {
                return segmentLocation[0];
            }
        }
        return "";
    }

    public static List<MediaItem> getMediaItemsFromCursor(Cursor cursor, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                try {
                    MediaItem mediaItem = new MediaItem();
                    mediaItem.mSha1 = cursor.getString(0);
                    mediaItem.mLocation = cursor.getString(1);
                    mediaItem.mDateTime = cursor.getLong(2);
                    mediaItem.mCity = getCityNameFromLoaction(mediaItem.mLocation);
                    if (!z || !TextUtils.isEmpty(mediaItem.mCity)) {
                        arrayList.add(mediaItem);
                    }
                } finally {
                    cursor.close();
                }
            }
        }
        return arrayList;
    }

    public List<Record> findRecords() {
        this.mTargetCity = null;
        return GalleryEntityManager.getInstance().query(Record.class, String.format("%s IN (%s) AND %s > %s", new Object[]{"scenarioId", TextUtils.join(",", new Integer[]{114, 201}), "time", String.valueOf(DateUtils.getCurrentTimeMillis() - 15552000000L)}), (String[]) null, "time ASC", (String) null);
    }

    public String getDatePeriodFromRecord(Record record) {
        return DateUtils.getDatePeriodGraceful(getRecordStartTime(record), getRecordEndTime(record));
    }

    /* access modifiers changed from: protected */
    public String getLocation() {
        return this.mTargetCity;
    }

    /* access modifiers changed from: protected */
    public String getPeopleId() {
        return null;
    }

    public List<Long> loadMediaItem() {
        return super.loadMediaItem();
    }
}
