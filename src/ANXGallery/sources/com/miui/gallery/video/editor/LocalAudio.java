package com.miui.gallery.video.editor;

import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.video.editor.model.LocalResource;
import com.miui.gallery.video.editor.model.VideoEditorBaseModel;
import java.io.File;
import java.util.HashMap;

public class LocalAudio extends VideoEditorBaseModel {
    private static HashMap<String, Integer> sAudioNames = new HashMap<String, Integer>() {
        {
            put("audio_none", Integer.valueOf(R.string.video_editor_audio_none));
            put("audio_memory", Integer.valueOf(R.string.video_editor_audio_summertime));
            put("audio_sad", Integer.valueOf(R.string.video_editor_audio_quiet));
            put("audio_rock", Integer.valueOf(R.string.video_editor_audio_electronics));
            put("audio_love", Integer.valueOf(R.string.video_editor_audio_dream));
            put("audio_young", Integer.valueOf(R.string.video_editor_audio_antiquity));
            put("audio_dynamic", Integer.valueOf(R.string.video_editor_audio_vitality));
            put("audio_custom", Integer.valueOf(R.string.video_editor_audio_custom));
        }
    };
    private int mBgColor;
    private int mIconResId = 0;
    private int mNameResId;
    private String mSrcPath;

    public LocalAudio() {
    }

    public LocalAudio(LocalResource localResource) {
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
        }
    }

    public LocalAudio(String str, int i, String str2, boolean z) {
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

    public String getFileName() {
        this.mFileName = this.mNameKey.trim() + ".aac";
        return this.mFileName;
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    public int getNameResId() {
        HashMap<String, Integer> hashMap;
        if (this.mNameResId == 0 && (hashMap = sAudioNames) != null && hashMap.containsKey(this.mNameKey)) {
            this.mNameResId = sAudioNames.get(this.mNameKey).intValue();
        }
        return this.mNameResId;
    }

    public String getSrcPath() {
        if (!TextUtils.isEmpty(this.mUnZipPath)) {
            this.mSrcPath = this.mUnZipPath + File.separator + getFileName();
        }
        return this.mSrcPath;
    }

    public void setColor(int i) {
        this.mBgColor = i;
    }

    public void setIconResId(int i) {
        this.mIconResId = i;
    }

    public void setNameResId(int i) {
        this.mNameResId = i;
    }

    public void setSrcPath(String str) {
        this.mSrcPath = str;
    }
}
