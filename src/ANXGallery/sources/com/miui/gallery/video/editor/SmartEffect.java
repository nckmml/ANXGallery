package com.miui.gallery.video.editor;

import com.miui.gallery.R;
import com.miui.gallery.video.editor.model.LocalResource;
import com.miui.gallery.video.editor.model.SmartEffectLocalResource;
import com.miui.gallery.video.editor.model.VideoEditorTemplateBaseModel;
import com.miui.gallery.video.editor.util.ToolsUtil;
import com.nexstreaming.nexeditorsdk.nexTemplateManager;
import java.util.HashMap;

public class SmartEffect extends VideoEditorTemplateBaseModel {
    private static HashMap<String, Integer> smartEffectNames = new HashMap<String, Integer>() {
        {
            put("smart_effect_none", Integer.valueOf(R.string.video_editor_smart_effect_none));
            put("smart_effect_movie", Integer.valueOf(R.string.video_editor_smart_effect_movie));
            put("smart_effect_big_film", Integer.valueOf(R.string.video_editor_smart_effect_big_film));
            put("smart_effect_street", Integer.valueOf(R.string.video_editor_smart_effect_street));
            put("smart_effect_halo", Integer.valueOf(R.string.video_editor_smart_effect_halo));
            put("smart_effect_radical", Integer.valueOf(R.string.video_editor_smart_effect_radical));
            put("smart_effect_freeze", Integer.valueOf(R.string.video_editor_smart_effect_freesze));
            put("smart_effect_dynamic", Integer.valueOf(R.string.video_editor_smart_effect_dynamic));
        }
    };
    public final String TAG = "SmartEffect";
    private int mBgColor;
    private String mEnName;
    private boolean mHasSpeedChange;
    private int mIconResId;
    private int mLongTime;
    private int mNameResId;
    private int mShortTime;
    private nexTemplateManager.Template mTemplate;

    public SmartEffect() {
    }

    public SmartEffect(LocalResource localResource) {
        if (localResource != null) {
            this.mID = localResource.id;
            this.mNameKey = localResource.nameKey;
            this.mLabel = localResource.label;
            this.mIconResId = localResource.imageId;
            this.mIconUrl = localResource.icon;
            this.mType = localResource.type;
            this.mExtra = "ve_type_extra".equals(localResource.type);
            if (!this.mExtra) {
                this.mDownloadState = 17;
            }
            if (localResource instanceof SmartEffectLocalResource) {
                SmartEffectLocalResource smartEffectLocalResource = (SmartEffectLocalResource) localResource;
                this.mAssetId = ToolsUtil.parseIntFromStr(smartEffectLocalResource.assetId);
                this.mAssetName = smartEffectLocalResource.assetName;
                this.mEnName = smartEffectLocalResource.enName;
                this.mLongTime = ToolsUtil.parseIntFromStr(smartEffectLocalResource.longTime);
                this.mShortTime = ToolsUtil.parseIntFromStr(smartEffectLocalResource.shortTime);
                this.mHasSpeedChange = ToolsUtil.parseIntFromStr(smartEffectLocalResource.hasSpeedChange) != 1 ? false : true;
            }
            this.mIsTemplate = this.mExtra;
        }
    }

    public SmartEffect(String str, int i, String str2, boolean z) {
        this.mNameKey = str;
        this.mIconResId = i;
        this.mType = str2;
        this.mExtra = "ve_type_extra".equals(str2);
        if (!this.mExtra) {
            this.mDownloadState = 17;
        }
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public String getEnName() {
        return this.mEnName;
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    public int getLongTime() {
        return this.mLongTime;
    }

    public int getNameResId() {
        HashMap<String, Integer> hashMap;
        if (this.mNameResId == 0 && (hashMap = smartEffectNames) != null && hashMap.containsKey(this.mNameKey)) {
            this.mNameResId = smartEffectNames.get(this.mNameKey).intValue();
        }
        return this.mNameResId;
    }

    public int getShortTime() {
        return this.mShortTime;
    }

    public nexTemplateManager.Template getTemplate() {
        return this.mTemplate;
    }

    public boolean isHasSpeedChange() {
        return this.mHasSpeedChange;
    }

    public boolean isLimitFourtySeconds() {
        return this.mLongTime == 40000;
    }

    public boolean isLimitSixtySeconds() {
        return this.mLongTime == 60000;
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
    }

    public void setIconResId(int i) {
        this.mIconResId = i;
    }

    public void setTemplate(nexTemplateManager.Template template) {
        this.mTemplate = template;
    }
}
