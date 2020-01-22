package com.miui.gallery.card.scenario.time.pastyear;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.ScenarioConstants;
import java.util.List;

public class PastYearSports extends DayOfLastYearScenario {
    public PastYearSports() {
        super(113, 1, ScenarioConstants.TAGS_SPORTS);
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        return GalleryApp.sGetAndroidContext().getResources().getString(R.string.card_title_pastyear_sports);
    }
}
