package com.miui.gallery.card.scenario.time.recent;

import com.miui.gallery.R;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.ScenarioConstants;
import java.util.List;

public class YesterdayActivitiesScenario extends YesterdayScenario {
    public YesterdayActivitiesScenario() {
        super(116, ScenarioConstants.TAGS_ACTIVITY);
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        return getRandomArrayString(R.array.activities_title);
    }
}
