package com.miui.gallery.card.scenario.time.annual;

import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.time.LocationScenario;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SafeDBUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnnualTravelsScenario extends AnnualScenario {
    public AnnualTravelsScenario() {
        super(118);
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_description_annual_travel);
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        long recordStartTime = getRecordStartTime(record);
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_title_annual_travel, new Object[]{DateUtils.getYearLocale(recordStartTime)});
    }

    public List<Long> loadMediaItem() {
        ArrayList arrayList = new ArrayList();
        List list = (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), GalleryContract.Cloud.CLOUD_URI, LocationScenario.PROJECTION, String.format(Locale.US, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType=1 AND exifImageWidth > 1000 AND exifImageLength > 1000 AND (lower(mimeType) NOT IN ('image/gif', 'image/x-adobe-dng')) AND localGroupId = (SELECT _id FROM cloud WHERE serverId=1) AND location is not null AND mixedDateTime > %s AND mixedDateTime < %s", new Object[]{Long.valueOf(getStartTime()), Long.valueOf(getEndTime())}), (String[]) null, "mixedDateTime ASC", new SafeDBUtil.QueryHandler<List<LocationScenario.MediaItem>>() {
            public List<LocationScenario.MediaItem> handle(Cursor cursor) {
                return LocationScenario.getMediaItemsFromCursor(cursor, true);
            }
        });
        if (MiscUtil.isValid(list)) {
            LocationScenario.MediaItem mediaItem = null;
            for (int i = 0; i < list.size(); i++) {
                LocationScenario.MediaItem mediaItem2 = (LocationScenario.MediaItem) list.get(i);
                if (mediaItem != null) {
                    if (!TextUtils.equals(mediaItem.mCity, mediaItem2.mCity)) {
                        LocationScenario.MediaItem mediaItem3 = (LocationScenario.MediaItem) list.get(i - 1);
                        List<Long> mediaIdsByStartEndTime = getMediaIdsByStartEndTime(mediaItem.mDateTime, mediaItem3.mDateTime);
                        if (mediaItem3.mDateTime - mediaItem.mDateTime <= 864000000 && mediaIdsByStartEndTime != null && mediaIdsByStartEndTime.size() > getMinImageCount()) {
                            arrayList.addAll(getMediaIdsByStartEndTime(mediaItem.mDateTime, mediaItem3.mDateTime));
                        }
                    }
                }
                mediaItem = mediaItem2;
            }
        }
        return arrayList;
    }
}
