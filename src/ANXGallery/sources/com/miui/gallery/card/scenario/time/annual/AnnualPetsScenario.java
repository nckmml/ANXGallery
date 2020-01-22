package com.miui.gallery.card.scenario.time.annual;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.scenario.DateUtils;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.ScenarioConstants;
import java.util.List;

public class AnnualPetsScenario extends AnnualScenario {
    public AnnualPetsScenario() {
        super(120);
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_description_annual_pets);
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        long recordStartTime = getRecordStartTime(record);
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_title_annual_pets, new Object[]{DateUtils.getYearLocale(recordStartTime)});
    }

    public List<Long> loadMediaItem() {
        return getImageIdsFromImageFeatures(getCameraMediaIdsByStartEndTimeTags(ScenarioConstants.TAGS_PETS, getStartTime(), getEndTime()));
    }
}
