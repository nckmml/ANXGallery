package com.miui.gallery.card.scenario.time.guarantee;

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
import com.miui.gallery.util.UriUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecentMemoryScenario extends TimeScenario {
    private List<Long> mRecentImages = new ArrayList(30);

    public RecentMemoryScenario() {
        super(501, 16, 16);
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        long j = Long.MAX_VALUE;
        long j2 = 0;
        if (MiscUtil.isValid(list)) {
            for (MediaFeatureItem dateTime : list) {
                long dateTime2 = dateTime.getDateTime();
                if (j > dateTime2) {
                    j = dateTime2;
                }
                if (j2 < dateTime2) {
                    j2 = dateTime2;
                }
            }
        }
        return DateUtils.getDatePeriodGraceful(j, j2);
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_title_recent_memory);
    }

    public List<Long> loadMediaItem() {
        return this.mRecentImages;
    }

    public boolean onPrepare(List<Record> list, List<Card> list2) {
        this.mRecentImages.clear();
        if (MiscUtil.isValid(list2) || MiscUtil.isValid(list)) {
            return false;
        }
        long currentTimeMillis = DateUtils.getCurrentTimeMillis();
        List list3 = (List) SafeDBUtil.safeQuery(GalleryApp.sGetAndroidContext(), UriUtil.appendLimit(GalleryContract.Cloud.CLOUD_URI, 30), PROJECTION, String.format(Locale.US, "(localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom')) AND serverType=1 AND exifImageWidth > 1000 AND exifImageLength > 1000 AND (lower(mimeType) NOT IN ('image/gif', 'image/x-adobe-dng')) AND localGroupId = (SELECT _id FROM cloud WHERE serverId=1) AND mixedDateTime < %s", new Object[]{Long.valueOf(currentTimeMillis)}), (String[]) null, "mixedDateTime DESC", new SafeDBUtil.QueryHandler<List<Long>>() {
            public List<Long> handle(Cursor cursor) {
                return RecentMemoryScenario.this.getMediaIdsFromCursor(cursor);
            }
        });
        if (!MiscUtil.isValid(list3)) {
            return false;
        }
        this.mRecentImages.addAll(list3);
        return true;
    }
}
