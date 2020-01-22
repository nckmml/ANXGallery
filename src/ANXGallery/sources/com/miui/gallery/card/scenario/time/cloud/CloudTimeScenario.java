package com.miui.gallery.card.scenario.time.cloud;

import android.text.TextUtils;
import com.miui.gallery.assistant.model.MediaFeatureItem;
import com.miui.gallery.card.Card;
import com.miui.gallery.card.scenario.Record;
import com.miui.gallery.card.scenario.time.TimeScenario;
import com.miui.gallery.cloudcontrol.strategies.AssistantScenarioStrategy;
import com.miui.gallery.util.MiscUtil;
import java.util.List;

public class CloudTimeScenario extends TimeScenario {
    private String mDescription;
    private long mEndTime;
    private long mStartTime;
    private String mTitle;

    public CloudTimeScenario(int i) {
        super(i, 4, 0);
    }

    public String generateDescription(Record record, List<MediaFeatureItem> list) {
        return this.mDescription;
    }

    public String generateTitle(Record record, List<MediaFeatureItem> list) {
        return this.mTitle;
    }

    public void onFillScenarioRule(AssistantScenarioStrategy.ScenarioRule scenarioRule) {
        super.onFillScenarioRule(scenarioRule);
        if (scenarioRule != null && (scenarioRule instanceof AssistantScenarioStrategy.CloudTimeScenarioRule)) {
            AssistantScenarioStrategy.CloudTimeScenarioRule cloudTimeScenarioRule = (AssistantScenarioStrategy.CloudTimeScenarioRule) scenarioRule;
            this.mTitle = cloudTimeScenarioRule.getTitle();
            this.mDescription = cloudTimeScenarioRule.getDescription();
            this.mStartTime = cloudTimeScenarioRule.getStartTime();
            this.mEndTime = cloudTimeScenarioRule.getEndTime();
        }
    }

    public boolean onPrepare(List<Record> list, List<Card> list2) {
        if (!MiscUtil.isValid(list) && !MiscUtil.isValid(list2)) {
            long j = this.mStartTime;
            if (j > 0 && this.mEndTime > j && !TextUtils.isEmpty(this.mTitle) && !TextUtils.isEmpty(this.mDescription)) {
                setStartTime(this.mStartTime);
                setEndTime(this.mEndTime);
                return true;
            }
        }
        return false;
    }
}
