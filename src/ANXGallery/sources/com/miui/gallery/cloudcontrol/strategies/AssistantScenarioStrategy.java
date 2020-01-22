package com.miui.gallery.cloudcontrol.strategies;

import com.google.gson.annotations.SerializedName;
import com.nexstreaming.nexeditorsdk.nexClip;
import java.util.List;

public class AssistantScenarioStrategy extends BaseStrategy {
    @SerializedName("cloudTimeScenarioRules")
    private List<CloudTimeScenarioRule> mCloudTimeScenarioRules;
    @SerializedName("defaultMaxImageCount")
    private int mDefaultMaxImageCount;
    @SerializedName("defaultMaxSelectedImageCount")
    private int mDefaultMaxSelectedImageCount;
    @SerializedName("defaultMinImageCount")
    private int mDefaultMinImageCount;
    @SerializedName("defaultMinSelectedImageCount")
    private int mDefaultMinSelectedImageCount;
    @SerializedName("duplicateRemovalInterval")
    private int mDuplicateRemovalInterval;
    @SerializedName("freeScenarioTriggerInterval")
    private int mFreeScenarioTriggerInterval;
    @SerializedName("localScenarioRules")
    private List<ScenarioRule> mLocalScenarioRules;

    public static class CloudTimeScenarioRule extends ScenarioRule {
        @SerializedName("description")
        private String mDescription;
        @SerializedName("endTime")
        private long mEndTime;
        @SerializedName("startTime")
        private long mStartTime;
        @SerializedName("title")
        private String mTitle;

        public String getDescription() {
            return this.mDescription;
        }

        public long getEndTime() {
            return this.mEndTime;
        }

        public long getStartTime() {
            return this.mStartTime;
        }

        public String getTitle() {
            return this.mTitle;
        }
    }

    public static class ScenarioRule {
        @SerializedName("maxImageCount")
        protected int mMaxImageCount;
        @SerializedName("maxSelectedImageCount")
        protected int mMaxSelectedImageCount;
        @SerializedName("minImageCount")
        protected int mMinImageCount;
        @SerializedName("minSelectedImageCount")
        protected int mMinSelectedImageCount;
        @SerializedName("priority")
        protected int mPriority;
        @SerializedName("scenarioId")
        protected int mScenarioId;
        @SerializedName("triggerInterval")
        protected int mTriggerInterval;

        public int getMaxImageCount() {
            return this.mMaxImageCount;
        }

        public int getMaxSelectedImageCount() {
            return this.mMaxSelectedImageCount;
        }

        public int getMinImageCount() {
            return this.mMinImageCount;
        }

        public int getMinSelectedImageCount() {
            return this.mMinSelectedImageCount;
        }

        public int getPriority() {
            return this.mPriority;
        }

        public int getScenarioId() {
            return this.mScenarioId;
        }

        public int getTriggerInterval() {
            return this.mTriggerInterval;
        }
    }

    public List<CloudTimeScenarioRule> getCloudTimeScenarioRules() {
        return this.mCloudTimeScenarioRules;
    }

    public int getDefaultMaxImageCount() {
        int i = this.mDefaultMaxImageCount;
        if (i > 0) {
            return i;
        }
        return 500;
    }

    public int getDefaultMaxSelectedImageCount() {
        int i = this.mDefaultMaxSelectedImageCount;
        if (i > 0) {
            return i;
        }
        return 80;
    }

    public int getDefaultMinImageCount() {
        int i = this.mDefaultMinImageCount;
        if (i > 0) {
            return i;
        }
        return 20;
    }

    public int getDefaultMinSelectedImageCount() {
        int i = this.mDefaultMinSelectedImageCount;
        if (i > 0) {
            return i;
        }
        return 6;
    }

    public int getDuplicateRemovalInterval() {
        int i = this.mDuplicateRemovalInterval;
        return i > 0 ? i : nexClip.kClip_Rotate_180;
    }

    public int getFreeScenarioTriggerInterval() {
        int i = this.mFreeScenarioTriggerInterval;
        if (i > 0) {
            return i;
        }
        return 6;
    }

    public List<ScenarioRule> getLocalScenarioRules() {
        return this.mLocalScenarioRules;
    }
}
