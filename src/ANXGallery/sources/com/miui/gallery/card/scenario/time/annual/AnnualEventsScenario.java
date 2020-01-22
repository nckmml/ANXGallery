package com.miui.gallery.card.scenario.time.annual;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.CardUtil;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.ScenarioConstants;
import java.util.List;

public class AnnualEventsScenario extends AnnualScenario {
    public AnnualEventsScenario() {
        super(119);
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_description_annual_events);
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        long recordStartTime = getRecordStartTime(record);
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_title_annual_events, new Object[]{DateUtils.getYearLocale(recordStartTime)});
    }

    public List<Long> loadMediaItem() {
        return getImageIdsFromImageFeatures(getCameraMediaIdsByStartEndTimeTags(CardUtil.concat(ScenarioConstants.TAGS_PARTY, CardUtil.concat(ScenarioConstants.TAGS_ACTIVITY, ScenarioConstants.TAGS_SPORTS)), getStartTime(), getEndTime()));
    }
}
