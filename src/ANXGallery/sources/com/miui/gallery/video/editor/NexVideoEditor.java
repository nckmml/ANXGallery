package com.miui.gallery.video.editor;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.UriUtils;
import com.miui.gallery.video.editor.VideoEditor;
import com.miui.gallery.video.editor.manager.NexAssetTemplateManager;
import com.miui.gallery.video.editor.util.FileHelper;
import com.miui.gallery.video.editor.util.IntentUtil;
import com.nexstreaming.nexeditorsdk.exception.ExpiredTimeException;
import com.nexstreaming.nexeditorsdk.nexAnimate;
import com.nexstreaming.nexeditorsdk.nexApplicationConfig;
import com.nexstreaming.nexeditorsdk.nexAspectProfile;
import com.nexstreaming.nexeditorsdk.nexClip;
import com.nexstreaming.nexeditorsdk.nexColorEffect;
import com.nexstreaming.nexeditorsdk.nexCrop;
import com.nexstreaming.nexeditorsdk.nexEngine;
import com.nexstreaming.nexeditorsdk.nexEngineListener;
import com.nexstreaming.nexeditorsdk.nexEngineView;
import com.nexstreaming.nexeditorsdk.nexOverlayImage;
import com.nexstreaming.nexeditorsdk.nexOverlayItem;
import com.nexstreaming.nexeditorsdk.nexOverlayKineMasterText;
import com.nexstreaming.nexeditorsdk.nexOverlayPreset;
import com.nexstreaming.nexeditorsdk.nexProject;
import com.nexstreaming.nexeditorsdk.nexTemplateManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NexVideoEditor extends VideoEditor implements nexEngineListener {
    private static final String[] sHardwareSupportWhiteList = {"riva", "pine", "cereus", "cactus", "olive", "ginkgo", "olivelite"};
    /* access modifiers changed from: private */
    public HashMap<String, Change> mApplyedEditValue = new HashMap<>();
    /* access modifiers changed from: private */
    public nexProject mCloneProject;
    /* access modifiers changed from: private */
    public Context mContext;
    private VideoEditor.EnocdeStateInterface mCurrentEnocdeStateInterface;
    /* access modifiers changed from: private */
    public TextClip mEndClip;
    /* access modifiers changed from: private */
    public nexEngine mEngin;
    private int mEngineState = -1;
    private volatile ExportParams mExportParams;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public boolean mHasApplyedSmartEffect = false;
    /* access modifiers changed from: private */
    public boolean mHasUsingAutoTrim = false;
    /* access modifiers changed from: private */
    public boolean mIsDestroy;
    /* access modifiers changed from: private */
    public nexClip mMainVideoClip;
    /* access modifiers changed from: private */
    public nexProject mNexProject;
    /* access modifiers changed from: private */
    public TextClip mOpenClip;
    private VideoEditor.OnCompletedListener mPauseOnCompletedListener;
    /* access modifiers changed from: private */
    public float mRatio;
    private HashMap<String, Change> mSavedEditValue = new HashMap<>();
    private int mSeekTarget = -1;
    /* access modifiers changed from: private */
    public HashMap<String, Change> mTempEditValue = new HashMap<>();
    private int mThumbnailPickCursor = 0;
    /* access modifiers changed from: private */
    public VideoEditor.TrimStateInterface mTrimStateInterface;
    private int mVideoBitrate;
    private int mVideoFrames;
    /* access modifiers changed from: private */
    public int mVideoHeight;
    private String mVideoPath;
    /* access modifiers changed from: private */
    public int mVideoRotation;
    /* access modifiers changed from: private */
    public int mVideoWidth;
    /* access modifiers changed from: private */
    public List<VideoThumbnail> preLoadVideoThumbnails = new ArrayList();
    private VideoEditor.OnCompletedListener seekOnCompletedListener;
    private boolean setTimeSuccess = false;

    /* renamed from: com.miui.gallery.video.editor.NexVideoEditor$18  reason: invalid class name */
    static /* synthetic */ class AnonymousClass18 {
        static final /* synthetic */ int[] $SwitchMap$com$nostra13$universalimageloader$core$download$ImageDownloader$Scheme = new int[ImageDownloader.Scheme.values().length];

        static {
            try {
                $SwitchMap$com$nostra13$universalimageloader$core$download$ImageDownloader$Scheme[ImageDownloader.Scheme.FILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private class ApplyTask extends AsyncTask<Change, Void, Boolean> {
        private VideoEditor.OnCompletedListener mOnCompletedListener;

        public ApplyTask(VideoEditor.OnCompletedListener onCompletedListener) {
            this.mOnCompletedListener = onCompletedListener;
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Change... changeArr) {
            if (changeArr != null) {
                for (Change applyChange : changeArr) {
                    applyChange.applyChange();
                }
            }
            NexVideoEditor.this.mEngin.updateProject();
            return true;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            NexVideoEditor.this.mTempEditValue.clear();
            NexVideoEditor.this.setEngineState(0);
            VideoEditor.OnCompletedListener onCompletedListener = this.mOnCompletedListener;
            if (onCompletedListener != null) {
                onCompletedListener.onCompleted();
                this.mOnCompletedListener = null;
            }
        }
    }

    private class BGMEdit implements Change {
        /* access modifiers changed from: private */
        public String mBGMUri;

        public BGMEdit(String str) {
            this.mBGMUri = str;
        }

        public void applyChange() {
            if (TextUtils.isEmpty(this.mBGMUri)) {
                if (!NexVideoEditor.this.mHasApplyedSmartEffect || NexVideoEditor.this.mCloneProject == null) {
                    NexVideoEditor.this.mNexProject.setBackgroundMusicPath(this.mBGMUri);
                } else {
                    SmartEffectTemplate smartEffectTemplate = (SmartEffectTemplate) NexVideoEditor.this.mApplyedEditValue.get("edit_type_smart_effect_template");
                    if (smartEffectTemplate != null) {
                        smartEffectTemplate.applyChange();
                    }
                }
                NexVideoEditor.this.mApplyedEditValue.remove("bg_audio");
                return;
            }
            String crop = AnonymousClass18.$SwitchMap$com$nostra13$universalimageloader$core$download$ImageDownloader$Scheme[ImageDownloader.Scheme.ofUri(this.mBGMUri).ordinal()] != 1 ? this.mBGMUri : ImageDownloader.Scheme.FILE.crop(this.mBGMUri);
            if (!NexVideoEditor.this.mHasApplyedSmartEffect || NexVideoEditor.this.mCloneProject == null) {
                NexVideoEditor.this.mNexProject.setBackgroundMusicPath(crop);
            } else {
                NexVideoEditor.this.mCloneProject.setBackgroundMusicPath(crop);
            }
            NexVideoEditor.this.mApplyedEditValue.put("bg_audio", this);
            if (!new File(this.mBGMUri).exists()) {
                HashMap hashMap = new HashMap();
                hashMap.put("manufacturer", Build.MANUFACTURER);
                hashMap.put("model", Build.MODEL);
                GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_bgm_empty_file_error", hashMap);
            }
        }
    }

    private interface Change {
        void applyChange();
    }

    static class ExportParams {
        int mBitrate = 0;
        int mCodecValue = 0;
        int mFps = 0;
        int mHeight = 0;
        boolean mIsValid;
        int mLevelValue = 0;
        int mProfileValue = 0;
        int mWidth = 0;

        ExportParams() {
        }
    }

    private class FilterEffect implements Change {
        /* access modifiers changed from: private */
        public Filter mFilter;

        public FilterEffect(Filter filter) {
            this.mFilter = filter;
        }

        public void applyChange() {
            if (this.mFilter != null) {
                if (!NexVideoEditor.this.mHasApplyedSmartEffect || NexVideoEditor.this.mCloneProject == null) {
                    NexVideoEditor.this.mMainVideoClip.setColorEffect(NexVideoEditor.this.finEffect(this.mFilter.getFilterId()));
                } else {
                    int totalClipCount = NexVideoEditor.this.mCloneProject.getTotalClipCount(true);
                    nexColorEffect access$2900 = NexVideoEditor.this.finEffect(this.mFilter.getFilterId());
                    for (int i = 0; i < totalClipCount; i++) {
                        NexVideoEditor.this.mCloneProject.getClip(i, true).setColorEffect(access$2900);
                    }
                }
                if ("NONE".equals(this.mFilter.getFilterId())) {
                    NexVideoEditor.this.mApplyedEditValue.remove("filter");
                } else {
                    NexVideoEditor.this.mApplyedEditValue.put("filter", this);
                }
            } else {
                NexVideoEditor.this.mMainVideoClip.setColorEffect((nexColorEffect) null);
                NexVideoEditor.this.mApplyedEditValue.remove("filter");
            }
        }
    }

    class SmartEffectTemplate implements Change {
        private int mLimitTime;
        private nexTemplateManager.Template template;

        public SmartEffectTemplate(SmartEffect smartEffect) {
            if (smartEffect != null) {
                this.template = smartEffect.getTemplate();
                this.mLimitTime = smartEffect.getLongTime();
            }
        }

        private String checkTemplate() {
            String str;
            if (NexVideoEditor.this.mNexProject.getTotalTime() <= 0) {
                return "Project is empty";
            }
            if (this.template == null) {
                return "Template did not selected";
            }
            if (NexVideoEditor.this.mCloneProject != null) {
                NexVideoEditor.this.mCloneProject.allClear(true);
            }
            NexVideoEditor nexVideoEditor = NexVideoEditor.this;
            nexProject unused = nexVideoEditor.mCloneProject = nexProject.clone(nexVideoEditor.mNexProject);
            int i = this.mLimitTime;
            if (i > 0 && i < NexVideoEditor.this.getVideoTotalTime()) {
                int totalClipCount = NexVideoEditor.this.mCloneProject.getTotalClipCount(true);
                for (int i2 = 0; i2 < totalClipCount; i2++) {
                    NexVideoEditor.this.mCloneProject.getClip(i2, true).getVideoClipEdit().setTrim(0, this.mLimitTime);
                }
            }
            NexVideoEditor.this.mEngin.setProject(NexVideoEditor.this.mCloneProject);
            try {
                NexAssetTemplateManager.getInstance();
                str = !NexAssetTemplateManager.getKmTemplateManager().applyTemplateToProjectById(NexVideoEditor.this.mCloneProject, this.template.id()) ? "Fail to apply template on project" : "";
            } catch (ExpiredTimeException unused2) {
                str = "This is expired asset!";
            } catch (Exception e) {
                str = e.getMessage();
            }
            Log.d("NexVideoEditor", "check template info : %s ", (Object) str);
            return str;
        }

        private void delete() {
            boolean unused = NexVideoEditor.this.mHasApplyedSmartEffect = false;
            NexVideoEditor.this.mApplyedEditValue.remove("edit_type_smart_effect_template");
        }

        public void applyChange() {
            if (this.template == null || !TextUtils.isEmpty(checkTemplate())) {
                nexProject unused = NexVideoEditor.this.mCloneProject = null;
                NexVideoEditor.this.mEngin.setProject(NexVideoEditor.this.mNexProject);
                delete();
                return;
            }
            delete();
            NexVideoEditor.this.mApplyedEditValue.put("edit_type_smart_effect_template", this);
            boolean unused2 = NexVideoEditor.this.mHasApplyedSmartEffect = true;
        }
    }

    private class SourceAudioChange implements Change {
        private boolean mEnable;

        public SourceAudioChange(boolean z) {
            this.mEnable = z;
        }

        public void applyChange() {
            if (NexVideoEditor.this.mMainVideoClip == null) {
                return;
            }
            if (this.mEnable) {
                NexVideoEditor.this.mMainVideoClip.setClipVolume(100);
                NexVideoEditor.this.mApplyedEditValue.remove("source_audio");
                return;
            }
            NexVideoEditor.this.mMainVideoClip.setClipVolume(0);
            NexVideoEditor.this.mApplyedEditValue.put("source_audio", this);
        }
    }

    private class TextClip implements Change {
        private int mShowTime;
        private nexOverlayItem mTextOverLayItem;
        private int x;
        private int y;

        private TextClip() {
            this.mShowTime = 0;
        }

        private TextClip(String str, int i) {
            this.mShowTime = 0;
            if (!TextUtils.isEmpty(str)) {
                this.mShowTime = i;
                initXY();
                this.mTextOverLayItem = createTextNexOverLayItem(str);
            }
        }

        private nexOverlayItem createTextNexOverLayItem(String str) {
            nexOverlayKineMasterText nexoverlaykinemastertext = new nexOverlayKineMasterText(NexVideoEditor.this.mContext, str, 59);
            nexoverlaykinemastertext.setTextColor(-1);
            nexoverlaykinemastertext.setFontId((String) null);
            int projectTotalTime = NexVideoEditor.this.getProjectTotalTime();
            int i = this.mShowTime;
            if (i == 0) {
                nexOverlayItem nexoverlayitem = new nexOverlayItem(nexoverlaykinemastertext, this.x, this.y, 0, projectTotalTime);
                nexoverlayitem.addAnimate(nexAnimate.getAlpha(nexProject.kAutoThemeTransitionDuration, 400, 1.0f, 0.0f));
                return nexoverlayitem;
            } else if (i == 1) {
                nexOverlayItem nexoverlayitem2 = new nexOverlayItem(nexoverlaykinemastertext, this.x, this.y, (projectTotalTime - 2000) - 400, projectTotalTime);
                nexoverlayitem2.addAnimate(nexAnimate.getAlpha(nexProject.kAutoThemeTransitionDuration, 400, 1.0f, 0.0f));
                return nexoverlayitem2;
            } else if (i == 2) {
                return new nexOverlayItem(nexoverlaykinemastertext, this.x, this.y, 0, projectTotalTime);
            } else {
                return null;
            }
        }

        private void delete(TextClip textClip) {
            if (textClip != null && textClip.getTextOverLayItem() != null) {
                NexVideoEditor.this.mNexProject.removeOverlay(textClip.getTextOverLayItem().getId());
                NexVideoEditor.this.mApplyedEditValue.remove("edit_type_auto_water_mark_text");
            }
        }

        private nexOverlayItem getTextOverLayItem() {
            return this.mTextOverLayItem;
        }

        private void initXY() {
            int access$3000 = NexVideoEditor.this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? NexVideoEditor.this.mVideoWidth : NexVideoEditor.this.mVideoHeight;
            int access$3100 = NexVideoEditor.this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? NexVideoEditor.this.mVideoHeight : NexVideoEditor.this.mVideoWidth;
            if (access$3000 > access$3100) {
                this.x = Math.round((((float) access$3000) / ((float) access$3100)) * 720.0f) / 2;
                this.y = 360;
            } else if (access$3000 == access$3100) {
                this.x = 360;
                this.y = 360;
            } else {
                int round = Math.round((((float) access$3100) / ((float) access$3000)) * 720.0f);
                this.x = 360;
                this.y = round / 2;
            }
        }

        public void applyChange() {
            delete((TextClip) NexVideoEditor.this.mApplyedEditValue.get("edit_type_auto_water_mark_text"));
            if (this.mTextOverLayItem != null) {
                NexVideoEditor.this.mApplyedEditValue.put("edit_type_auto_water_mark_text", this);
                NexVideoEditor.this.mNexProject.addOverlay(this.mTextOverLayItem);
            }
            SmartEffectTemplate smartEffectTemplate = (SmartEffectTemplate) NexVideoEditor.this.mApplyedEditValue.get("edit_type_smart_effect_template");
            if (smartEffectTemplate != null) {
                smartEffectTemplate.applyChange();
            }
        }
    }

    private class TrimInfo implements Change {
        private int end;
        private int start;

        TrimInfo(int i, int i2) {
            this.start = i;
            this.end = i2;
        }

        public void applyChange() {
            if (NexVideoEditor.this.mMainVideoClip != null) {
                int i = this.start;
                int i2 = this.end;
                if (i >= i2 || i2 > NexVideoEditor.this.getVideoTotalTime()) {
                    Log.d("NexVideoEditor", "TrimInfo: end time is lower than start time ");
                    return;
                }
                NexVideoEditor.this.mMainVideoClip.getVideoClipEdit().setTrim(this.start, this.end);
                if (this.start == 0 && this.end == NexVideoEditor.this.getVideoTotalTime()) {
                    NexVideoEditor.this.mApplyedEditValue.remove("trim");
                } else {
                    NexVideoEditor.this.mApplyedEditValue.put("trim", this);
                }
                if (NexVideoEditor.this.mEndClip != null) {
                    NexVideoEditor.this.mEndClip.applyChange();
                }
            }
            SmartEffectTemplate smartEffectTemplate = (SmartEffectTemplate) NexVideoEditor.this.mApplyedEditValue.get("edit_type_smart_effect_template");
            if (smartEffectTemplate != null) {
                smartEffectTemplate.applyChange();
            }
        }
    }

    class VideoEditorAdjust implements Change {
        boolean mAdjust;

        public VideoEditorAdjust(boolean z) {
            this.mAdjust = z;
        }

        private void delete() {
            NexVideoEditor.this.mApplyedEditValue.remove("edit_type_adjust");
        }

        public void applyChange() {
            if (!this.mAdjust) {
                delete();
            }
        }
    }

    class WaterMark implements Change {
        private int anchorPoint = 0;
        private int mAlphaDurationTime = 400;
        private int mEndTime = 0;
        private int mStartTime = 0;
        private String mTemplateId;
        private nexOverlayImage mWaterMarkOverlay;
        private nexOverlayItem mWaterMarkOverlayItem;
        private float scale = 0.0f;
        private int timeType = 0;
        private int x;
        private int y;

        public WaterMark() {
        }

        public WaterMark(int i, String str) {
            this.timeType = i;
            this.mTemplateId = str;
            initOverLayItemParams();
            initOverLayItem();
        }

        private void delete(WaterMark waterMark) {
            if (waterMark != null && waterMark.mWaterMarkOverlayItem != null) {
                NexVideoEditor.this.mNexProject.removeOverlay(waterMark.mWaterMarkOverlayItem.getId());
                NexVideoEditor.this.mApplyedEditValue.remove("edit_type_water_mark");
            }
        }

        private void initOverLayItem() {
            this.mEndTime = NexVideoEditor.this.getProjectTotalTime();
            if (!TextUtils.isEmpty(this.mTemplateId) && this.mEndTime > 0) {
                int i = this.timeType;
                if (i == 0) {
                    this.mStartTime = 0;
                    this.mWaterMarkOverlay = nexOverlayPreset.getOverlayPreset(NexVideoEditor.this.mContext.getApplicationContext()).getOverlayImage(this.mTemplateId);
                    this.mWaterMarkOverlayItem = new nexOverlayItem(this.mWaterMarkOverlay, this.anchorPoint, false, (float) this.x, (float) this.y, this.mStartTime, this.mEndTime);
                    nexOverlayImage nexoverlayimage = this.mWaterMarkOverlay;
                    if (nexoverlayimage != null) {
                        this.mWaterMarkOverlayItem.addAnimate(nexAnimate.getAlpha(nexoverlayimage.getDefaultDuration() + 1500, this.mAlphaDurationTime, 1.0f, 0.0f));
                    }
                } else if (i == 2) {
                    this.mStartTime = 0;
                    this.mWaterMarkOverlay = nexOverlayPreset.getOverlayPreset(NexVideoEditor.this.mContext.getApplicationContext()).getOverlayImage(this.mTemplateId);
                    this.mWaterMarkOverlayItem = new nexOverlayItem(this.mWaterMarkOverlay, this.anchorPoint, false, (float) this.x, (float) this.y, this.mStartTime, this.mEndTime);
                } else if (i == 1) {
                    this.mWaterMarkOverlay = nexOverlayPreset.getOverlayPreset(NexVideoEditor.this.mContext.getApplicationContext()).getOverlayImage(this.mTemplateId);
                    this.mStartTime = ((this.mEndTime - 800) - this.mWaterMarkOverlay.getDefaultDuration()) - 1000;
                    this.mWaterMarkOverlayItem = new nexOverlayItem(this.mWaterMarkOverlay, this.anchorPoint, false, (float) this.x, (float) this.y, this.mStartTime, this.mEndTime);
                    this.mWaterMarkOverlayItem.addAnimate(nexAnimate.getAlpha(this.mWaterMarkOverlay.getDefaultDuration() + 500, 800, 1.0f, 0.0f));
                }
                nexOverlayItem nexoverlayitem = this.mWaterMarkOverlayItem;
                float f = this.scale;
                nexoverlayitem.setScale(f, f);
            }
        }

        private void initOverLayItemParams() {
            int access$3000 = NexVideoEditor.this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? NexVideoEditor.this.mVideoWidth : NexVideoEditor.this.mVideoHeight;
            int access$3100 = NexVideoEditor.this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? NexVideoEditor.this.mVideoHeight : NexVideoEditor.this.mVideoWidth;
            if (access$3000 > access$3100) {
                int round = Math.round((((float) access$3000) / ((float) access$3100)) * 720.0f);
                this.x = round / 2;
                this.y = 360;
                this.scale = 970.0f / ((float) round);
            } else if (access$3000 == access$3100) {
                this.x = 360;
                this.y = 360;
                this.scale = 0.576f;
            } else {
                int round2 = Math.round((((float) access$3100) / ((float) access$3000)) * 720.0f);
                this.x = 360;
                this.y = round2 / 2;
                this.scale = 0.576f;
            }
        }

        public void applyChange() {
            delete((WaterMark) NexVideoEditor.this.mApplyedEditValue.get("edit_type_water_mark"));
            if (this.mWaterMarkOverlayItem != null) {
                NexVideoEditor.this.mApplyedEditValue.put("edit_type_water_mark", this);
                NexVideoEditor.this.mNexProject.addOverlay(this.mWaterMarkOverlayItem);
            }
            SmartEffectTemplate smartEffectTemplate = (SmartEffectTemplate) NexVideoEditor.this.mApplyedEditValue.get("edit_type_smart_effect_template");
            if (smartEffectTemplate != null) {
                smartEffectTemplate.applyChange();
                Log.d("NexVideoEditor", "WaterMark change: have  smartEffect;");
            }
            BGMEdit bGMEdit = (BGMEdit) NexVideoEditor.this.mApplyedEditValue.get("bg_audio");
            if (bGMEdit != null) {
                bGMEdit.applyChange();
                Log.d("NexVideoEditor", "WaterMark change: have  audio;");
            }
            FilterEffect filterEffect = (FilterEffect) NexVideoEditor.this.mApplyedEditValue.get("filter");
            if (filterEffect != null) {
                filterEffect.applyChange();
                Log.d("NexVideoEditor", "WaterMark change: have  filter;");
            }
        }
    }

    public NexVideoEditor(Context context) {
        this.mContext = context;
    }

    private boolean addChange(final Change change, final VideoEditor.OnCompletedListener onCompletedListener) {
        int i = this.mEngineState;
        if (i != 0) {
            if (i == 1) {
                setEngineState(101);
                nexEngine nexengine = this.mEngin;
                if (nexengine != null) {
                    nexengine.stop(new nexEngine.OnCompletionListener() {
                        public void onComplete(int i) {
                            change.applyChange();
                            if (NexVideoEditor.this.mEngin != null) {
                                NexVideoEditor.this.mEngin.updateProject();
                            }
                            NexVideoEditor.this.setEngineState(0);
                            VideoEditor.OnCompletedListener onCompletedListener = onCompletedListener;
                            if (onCompletedListener != null) {
                                onCompletedListener.onCompleted();
                            }
                        }
                    });
                }
                return true;
            } else if (i != 2) {
                Log.e("NexVideoEditor", "addChange is not allowed at EngineState :" + this.mEngineState);
                return false;
            }
        }
        change.applyChange();
        this.mEngin.updateProject();
        setEngineState(0);
        if (onCompletedListener != null) {
            onCompletedListener.onCompleted();
        }
        return true;
    }

    private void checkExportParams() {
        if (this.mExportParams != null && Build.VERSION.SDK_INT >= 21) {
            int i = 0;
            int i2 = 0;
            for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(0).getCodecInfos()) {
                if (mediaCodecInfo != null) {
                    String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                    int i3 = i2;
                    int i4 = i;
                    int i5 = 0;
                    while (supportedTypes != null && i5 < supportedTypes.length) {
                        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(supportedTypes[i5]);
                        if (mediaCodecInfo.getName().toLowerCase().contains("avc") && mediaCodecInfo.getName().toLowerCase().contains("encoder")) {
                            int i6 = i3;
                            int i7 = i4;
                            for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : capabilitiesForType.profileLevels) {
                                if (codecProfileLevel != null) {
                                    i7 = Math.max(i7, codecProfileLevel.profile);
                                    i6 = Math.max(i7, codecProfileLevel.level);
                                }
                            }
                            i4 = i7;
                            i3 = i6;
                        }
                        i5++;
                    }
                    i = i4;
                    i2 = i3;
                }
            }
            Log.d("NexVideoEditor", "checkExportParams: maxProfile = %d , maxLevel = %d ", Integer.valueOf(i), Integer.valueOf(i2));
            this.mExportParams.mProfileValue = Math.min(this.mExportParams.mProfileValue, i);
            this.mExportParams.mLevelValue = Math.min(this.mExportParams.mLevelValue, i2);
        }
    }

    /* access modifiers changed from: private */
    public void configEngine() {
        nexApplicationConfig.setAspectProfile(this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? new nexAspectProfile(this.mVideoWidth, this.mVideoHeight) : new nexAspectProfile(this.mVideoHeight, this.mVideoWidth));
    }

    private nexProject createProject(nexClip nexclip) {
        nexProject nexproject = this.mNexProject;
        if (nexproject != null) {
            nexproject.allClear(true);
            this.mNexProject = null;
        }
        if (nexclip == null) {
            return null;
        }
        nexclip.setRotateDegree(this.mVideoRotation);
        nexProject nexproject2 = new nexProject();
        nexproject2.add(nexclip);
        nexclip.getCrop().randomizeStartEndPosition(true, nexCrop.CropMode.FIT);
        return nexproject2;
    }

    /* access modifiers changed from: private */
    public void doSeek(int i) {
        int i2 = this.mEngineState;
        if (i2 == 0 || i2 == 2) {
            this.setTimeSuccess = false;
            setEngineState(104);
            this.mEngin.seek(i);
        } else if (i2 != 104) {
            Log.e("NexVideoEditor", "setFilter is not allowed at EngineState :" + this.mEngineState);
        } else {
            this.mSeekTarget = i;
            updataSeek();
        }
    }

    private void export(String str) {
        if (this.mExportParams == null || !this.mExportParams.mIsValid) {
            this.mEngin.export(str, this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? this.mVideoWidth : this.mVideoHeight, this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? this.mVideoHeight : this.mVideoWidth, this.mVideoBitrate, Long.MAX_VALUE, 0);
        } else {
            this.mEngin.exportNoException(str, this.mExportParams.mWidth, this.mExportParams.mHeight, this.mExportParams.mBitrate, Long.MAX_VALUE, 0, 0, this.mExportParams.mProfileValue, this.mExportParams.mLevelValue, this.mExportParams.mFps, this.mExportParams.mCodecValue);
        }
    }

    /* access modifiers changed from: private */
    public nexColorEffect finEffect(String str) {
        if (str == null) {
            return null;
        }
        for (nexColorEffect next : nexColorEffect.getPresetList()) {
            if (str.equals(next.getPresetName())) {
                return next;
            }
        }
        return null;
    }

    private int getLevelValue(nexClip nexclip) {
        switch (nexclip.getAVCLevel()) {
            case 20:
                return 32;
            case 21:
                return 64;
            case 22:
                return 128;
            case 30:
                return 256;
            case 31:
                return 512;
            case 32:
                return 1024;
            case 40:
                return 2048;
            case 41:
                return 4096;
            case 42:
                return 8192;
            case 50:
                return 16384;
            case 51:
                return 32768;
            case 52:
                return 65536;
            default:
                return 0;
        }
    }

    private int getProfileValue(nexClip nexclip) {
        int aVCProfile = nexclip.getAVCProfile();
        if (aVCProfile == 66) {
            return 1;
        }
        if (aVCProfile != 77) {
            return (aVCProfile == 100 || aVCProfile == 110 || aVCProfile == 122 || aVCProfile == 244) ? 4 : 0;
        }
        return 2;
    }

    /* access modifiers changed from: private */
    public void handleHardwareSupport() throws VideoEditor.NotSupportVideoException {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        if (Arrays.asList(sHardwareSupportWhiteList).contains(Build.DEVICE) && Build.VERSION.SDK_INT >= 21) {
            int width = this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? this.mMainVideoClip.getWidth() : this.mMainVideoClip.getHeight();
            int height = this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? this.mMainVideoClip.getHeight() : this.mMainVideoClip.getWidth();
            String videoCodecType = this.mMainVideoClip.getVideoCodecType();
            for (MediaCodecInfo mediaCodecInfo : new MediaCodecList(0).getCodecInfos()) {
                if (mediaCodecInfo != null && !mediaCodecInfo.isEncoder()) {
                    String[] supportedTypes = mediaCodecInfo.getSupportedTypes();
                    int i = 0;
                    while (supportedTypes != null && i < supportedTypes.length) {
                        if (TextUtils.isEmpty(supportedTypes[i]) || !supportedTypes[i].contains("video/") || ((!supportedTypes[i].contains(videoCodecType) && ((!videoCodecType.equalsIgnoreCase("video/h264") || !supportedTypes[i].equalsIgnoreCase("video/avc")) && (!videoCodecType.equalsIgnoreCase("video/avc") || !supportedTypes[i].equalsIgnoreCase("video/h264")))) || (videoCapabilities = mediaCodecInfo.getCapabilitiesForType(supportedTypes[i]).getVideoCapabilities()) == null || !videoCapabilities.areSizeAndRateSupported(width, height, (double) this.mMainVideoClip.getFramesPerSecond()))) {
                            i++;
                        } else {
                            Log.d("NexVideoEditor", String.format("the MediaCodecInfo name is: %s, and the  phone is supported to edit:  %s", new Object[]{mediaCodecInfo.getName(), "true"}));
                            return;
                        }
                    }
                }
            }
            Log.e("NexVideoEditor", "the device can not support to edit the video.");
            throw new VideoEditor.NotSupportVideoException();
        }
    }

    private boolean haveChange(String str) {
        Change change = this.mSavedEditValue.get(str);
        Change change2 = this.mApplyedEditValue.get(str);
        if (change == null && change2 == null) {
            return false;
        }
        return change == null || !change.equals(change2);
    }

    /* access modifiers changed from: private */
    public void initExportParams() {
        if (this.mExportParams == null) {
            this.mExportParams = new ExportParams();
            this.mExportParams.mWidth = this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? this.mVideoWidth : this.mVideoHeight;
            this.mExportParams.mHeight = this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? this.mVideoHeight : this.mVideoWidth;
            nexProject project = this.mEngin.getProject();
            int i = 0;
            if (project == null) {
                Log.d("NexVideoEditor", "init export params  is invalid.");
                this.mExportParams.mIsValid = false;
                return;
            }
            Log.d("NexVideoEditor", "project total clip count : %d", (Object) Integer.valueOf(project.getTotalClipCount(true)));
            while (true) {
                if (i < project.getTotalClipCount(true)) {
                    nexClip clip = project.getClip(i, true);
                    if (clip != null && clip.getClipType() == 4) {
                        this.mExportParams.mBitrate = clip.getVideoBitrate();
                        this.mExportParams.mProfileValue = getProfileValue(clip);
                        this.mExportParams.mLevelValue = getLevelValue(clip);
                        this.mExportParams.mFps = (clip.getFramesPerSecond() + 1) * 100;
                        this.mExportParams.mCodecValue = nexEngine.ExportCodec_AVC;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            this.mExportParams.mIsValid = true;
            Log.d("NexVideoEditor", "init export params profileValue: %s, levelValue: %s .", Integer.valueOf(this.mExportParams.mProfileValue), Integer.valueOf(this.mExportParams.mLevelValue));
        }
        checkExportParams();
    }

    private void loadMainThumbnail(String str) {
        ImageLoader.getInstance().loadImage(UriUtils.toSafeString(Uri.fromFile(new File(str))), new ImageLoadingListener() {
            public void onLoadingCancelled(String str, View view) {
            }

            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (bitmap != null) {
                    NexVideoEditor.this.mDisplayWrapper.showThumbnail(bitmap);
                    if (NexVideoEditor.this.preLoadVideoThumbnails.size() == 0) {
                        if (NexVideoEditor.this.mVideoRotation % nexClip.kClip_Rotate_180 == 0) {
                            NexVideoEditor.this.preLoadVideoThumbnails.add(new VideoThumbnail(0, 0, 0, Bitmap.createScaledBitmap(bitmap, 160, 90, false)));
                        } else {
                            NexVideoEditor.this.preLoadVideoThumbnails.add(new VideoThumbnail(0, 0, 0, Bitmap.createScaledBitmap(bitmap, 90, 160, false)));
                        }
                        NexVideoEditor.this.notifyThumbnailChanged();
                    }
                }
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
            }

            public void onLoadingStarted(String str, View view) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadProject() {
        this.mNexProject = createProject(this.mMainVideoClip);
        this.mNexProject.getLastPrimaryClip().setRotateDegree(this.mMainVideoClip.getRotateInMeta());
        this.mNexProject.getLastPrimaryClip().getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FIT);
        this.mEngin.setProject(this.mNexProject);
        this.mEngin.updateProject();
    }

    /* access modifiers changed from: private */
    public void loadThumbnail(final VideoEditor.OnCompletedListener onCompletedListener) {
        int i = this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? 160 : 90;
        int i2 = this.mVideoRotation % nexClip.kClip_Rotate_180 == 0 ? 90 : 160;
        nexClip nexclip = this.mMainVideoClip;
        nexclip.getVideoClipDetailThumbnails(i, i2, 0, nexclip.getTotalTime(), 50, this.mVideoRotation, new NexOnGetVideoClipDetailThumbnailsListener(new VideoEditor.OnGetVideoThumbnailListener() {
            public void onGetVideoThumbnailCompleted(List<VideoThumbnail> list) {
                if (!NexVideoEditor.this.mIsDestroy) {
                    if (list != null && list.size() > 0) {
                        NexVideoEditor.this.preLoadVideoThumbnails.clear();
                        NexVideoEditor.this.preLoadVideoThumbnails.addAll(list);
                        NexVideoEditor.this.notifyThumbnailChanged();
                    }
                    NexVideoEditor nexVideoEditor = NexVideoEditor.this;
                    nexVideoEditor.setDisplayView(nexVideoEditor.mDisplayWrapper.getDisplayView());
                    NexVideoEditor.this.setEngineState(0);
                    onCompletedListener.onCompleted();
                }
            }
        }));
    }

    /* access modifiers changed from: private */
    public void loadVideo(String str) throws VideoEditor.NotSupportVideoException {
        nexClip supportedClip = nexClip.getSupportedClip(str, true);
        if (supportedClip != null) {
            this.mVideoWidth = supportedClip.getWidth();
            this.mVideoHeight = supportedClip.getHeight();
            this.mVideoRotation = supportedClip.getRotateInMeta();
            this.mVideoBitrate = supportedClip.getVideoBitrate();
            this.mVideoFrames = supportedClip.getFramesPerSecond();
            this.mMainVideoClip = supportedClip;
            if (this.mVideoRotation % nexClip.kClip_Rotate_180 == 0) {
                this.mRatio = ((float) this.mVideoWidth) / ((float) this.mVideoHeight);
            } else {
                this.mRatio = ((float) this.mVideoHeight) / ((float) this.mVideoWidth);
            }
        } else {
            throw new VideoEditor.NotSupportVideoException();
        }
    }

    private void pause(VideoEditor.OnCompletedListener onCompletedListener) {
        if (this.mEngineState != 1 || this.mEngin == null) {
            Log.e("NexVideoEditor", "pause is not allowed at EngineState :" + this.mEngineState);
            return;
        }
        this.mPauseOnCompletedListener = onCompletedListener;
        setEngineState(102);
        this.mEngin.pause();
    }

    /* access modifiers changed from: private */
    public void setEngineState(int i) {
        if (i == -500) {
            setState(-500);
        } else if (i == 500) {
            setState(500);
            keepScreenOn(false);
        } else if (i == 0) {
            setState(0);
            keepScreenOn(false);
        } else if (i == 1) {
            setState(1);
            keepScreenOn(true);
            this.mDisplayWrapper.hideThumbnail();
        } else if (i != 2) {
            switch (i) {
                case 100:
                case 101:
                case 102:
                case 103:
                case 107:
                    setState(200);
                    break;
                case 104:
                    setState(3);
                    this.mDisplayWrapper.hideThumbnail();
                    break;
                case 105:
                case 106:
                    keepScreenOn(true);
                    setState(200);
                    break;
            }
        } else {
            setState(2);
            keepScreenOn(false);
        }
        this.mEngineState = i;
    }

    private void setTrimInfo(TrimInfo trimInfo) {
        this.mTempEditValue.put("trim", trimInfo);
    }

    private void updataSeek() {
        if (this.mEngineState != 104) {
            return;
        }
        if (this.mSeekTarget != -1) {
            if (Math.abs(this.mEngin.getCurrentPlayTimeTime() - this.mSeekTarget) > 150) {
                this.mEngin.seekIDROnly(this.mSeekTarget);
                Log.d("NexVideoEditor", "SEEK IDR progress :" + this.mSeekTarget);
            } else {
                this.mEngin.seek(this.mSeekTarget);
                Log.d("NexVideoEditor", "progress :" + this.mSeekTarget);
            }
            this.mSeekTarget = -1;
            return;
        }
        if (this.setTimeSuccess) {
            setEngineState(2);
        } else {
            setEngineState(0);
        }
        VideoEditor.OnCompletedListener onCompletedListener = this.seekOnCompletedListener;
        if (onCompletedListener != null) {
            onCompletedListener.onCompleted();
        }
    }

    public void adjustBrightness(int i) {
        int i2 = this.mEngineState;
        if (i2 == 0 || i2 == 2) {
            apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    NexVideoEditor.this.play();
                }
            });
        }
        this.mEngin.setBrightness(i);
        this.mEngin.fastPreview(nexEngine.FastPreviewOption.adj_brightness, i);
    }

    public void adjustContrast(int i) {
        int i2 = this.mEngineState;
        if (i2 == 0 || i2 == 2) {
            apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    NexVideoEditor.this.play();
                }
            });
        }
        this.mEngin.setContrast(i);
        this.mEngin.fastPreview(nexEngine.FastPreviewOption.adj_contrast, i);
    }

    public void adjustSaturation(int i) {
        int i2 = this.mEngineState;
        if (i2 == 0 || i2 == 2) {
            apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    NexVideoEditor.this.play();
                }
            });
        }
        this.mEngin.setSaturation(i);
        this.mEngin.fastPreview(nexEngine.FastPreviewOption.adj_saturation, i);
    }

    public void adjustSharpness(int i) {
        int i2 = this.mEngineState;
        if (i2 == 0 || i2 == 2) {
            apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    NexVideoEditor.this.play();
                }
            });
        }
        this.mEngin.setSharpness(i);
        this.mEngin.fastPreview(nexEngine.FastPreviewOption.adj_sharpness, i);
    }

    public void adjustVignetteRange(int i) {
        int i2 = this.mEngineState;
        if (i2 == 0 || i2 == 2) {
            apply(new VideoEditor.OnCompletedListener() {
                public void onCompleted() {
                    NexVideoEditor.this.play();
                }
            });
        }
        this.mEngin.setVignetteRange(i);
        this.mEngin.fastPreview(nexEngine.FastPreviewOption.adj_vignetteRange, i);
    }

    public boolean apply(VideoEditor.OnCompletedListener onCompletedListener) {
        return applyAsync(onCompletedListener);
    }

    public boolean applyAsync(final VideoEditor.OnCompletedListener onCompletedListener) {
        if (this.mTempEditValue.size() == 0) {
            onCompletedListener.onCompleted();
            return true;
        }
        final Change[] changeArr = new Change[this.mTempEditValue.size()];
        this.mTempEditValue.values().toArray(changeArr);
        this.mTempEditValue.clear();
        int i = this.mEngineState;
        if (i != 0) {
            if (i == 1) {
                setEngineState(101);
                this.mEngin.stop(new nexEngine.OnCompletionListener() {
                    public void onComplete(int i) {
                        NexVideoEditor.this.setEngineState(100);
                        new ApplyTask(onCompletedListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, changeArr);
                    }
                });
                return true;
            } else if (i != 2) {
                return false;
            }
        }
        setEngineState(100);
        new ApplyTask(onCompletedListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, changeArr);
        return true;
    }

    public void autoTrim(final int i, VideoEditor.TrimStateInterface trimStateInterface) {
        if (this.mEngineState == 0 && isSupportAutoTrim() && i < this.mMainVideoClip.getTotalTime()) {
            this.mTrimStateInterface = trimStateInterface;
            setEngineState(106);
            this.mEngin.autoTrim(this.mMainVideoClip.getPath(), true, i / 5, 5, 0, new nexEngine.OnAutoTrimResultListener() {
                public void onAutoTrimResult(int i, int[] iArr) {
                    if (iArr != null && iArr.length != 0) {
                        NexVideoEditor.this.mNexProject.allClear(true);
                        int totalTime = NexVideoEditor.this.mMainVideoClip.getTotalTime();
                        for (int i2 = 0; i2 < iArr.length; i2++) {
                            nexClip dup = nexClip.dup(NexVideoEditor.this.mMainVideoClip);
                            NexVideoEditor.this.mNexProject.add(dup);
                            int i3 = iArr[i2];
                            dup.getVideoClipEdit().setTrim(i3, i3 >= totalTime ? iArr[i2] + (i / 5) : Math.min(iArr[i2] + (i / 5), totalTime));
                            dup.setRotateDegree(NexVideoEditor.this.mVideoRotation);
                            NexVideoEditor.this.mEngin.setProject(NexVideoEditor.this.mNexProject);
                        }
                        final String generateOutputFilePath = FileHelper.generateOutputFilePath(NexVideoEditor.this.mMainVideoClip.getPath(), true);
                        if (NexVideoEditor.this.mTrimStateInterface != null) {
                            NexVideoEditor.this.mTrimStateInterface.onTrimEnd(generateOutputFilePath);
                        }
                        NexVideoEditor.this.setEngineState(0);
                        NexVideoEditor.this.mHandler.post(new Runnable() {
                            public void run() {
                                NexVideoEditor.this.export(generateOutputFilePath, new VideoEditor.EnocdeStateInterface() {
                                    public void onEncodeEnd(boolean z, int i, int i2) {
                                        if (z) {
                                            boolean unused = NexVideoEditor.this.mHasUsingAutoTrim = true;
                                        }
                                        if (NexVideoEditor.this.mTrimStateInterface != null) {
                                            NexVideoEditor.this.mTrimStateInterface.onEncodeEnd(z, i, i2);
                                        }
                                    }

                                    public void onEncodeProgress(int i) {
                                        if (NexVideoEditor.this.mTrimStateInterface != null) {
                                            NexVideoEditor.this.mTrimStateInterface.onEncodeProgress(i);
                                        }
                                    }

                                    public void onEncodeStart() {
                                        if (NexVideoEditor.this.mTrimStateInterface != null) {
                                            NexVideoEditor.this.mTrimStateInterface.onEncodeStart();
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            });
            VideoEditor.TrimStateInterface trimStateInterface2 = this.mTrimStateInterface;
            if (trimStateInterface2 != null) {
                trimStateInterface2.onTrimStart();
            }
        }
    }

    public void cancelExport(final VideoEditor.OnCompletedListener onCompletedListener) {
        if (this.mEngineState == 105) {
            this.mEngin.stop(new nexEngine.OnCompletionListener() {
                public void onComplete(int i) {
                    NexVideoEditor.this.setEngineState(0);
                    VideoEditor.OnCompletedListener onCompletedListener = onCompletedListener;
                    if (onCompletedListener != null) {
                        onCompletedListener.onCompleted();
                    }
                }
            });
        }
    }

    public boolean checkIDRSupport() {
        long currentTimeMillis = System.currentTimeMillis();
        nexClip nexclip = this.mMainVideoClip;
        boolean z = nexclip != null && nexclip.getSeekPointCount() > 0 && this.mMainVideoClip.getSeekPointInterval() <= 4000;
        Log.d("NexVideoEditor", "get interval costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return z;
    }

    public void destroy() {
        nexEngine nexengine = this.mEngin;
        if (nexengine != null) {
            nexengine.setView((nexEngineView) null);
            this.mEngin.stop();
            nexApplicationConfig.releaseNativeEngine(this.mEngin);
            keepScreenOn(false);
        }
        this.mExportParams = null;
        this.mIsDestroy = true;
    }

    public void export(final String str, final VideoEditor.EnocdeStateInterface enocdeStateInterface) {
        Log.d("NexVideoEditor", "export engine state: %d ", (Object) Integer.valueOf(this.mEngineState));
        int i = this.mEngineState;
        if (i != 0) {
            if (i == 1) {
                setEngineState(101);
                this.mEngin.stop(new nexEngine.OnCompletionListener() {
                    public void onComplete(int i) {
                        NexVideoEditor.this.setEngineState(0);
                        NexVideoEditor.this.mHandler.post(new Runnable() {
                            public void run() {
                                NexVideoEditor.this.export(str, enocdeStateInterface);
                            }
                        });
                    }
                });
                return;
            } else if (i != 2) {
                Log.e("NexVideoEditor", "export is not allowed at EngineState :" + this.mEngineState);
                enocdeStateInterface.onEncodeEnd(false, -1, -1);
                return;
            }
        }
        setEngineState(105);
        this.mCurrentEnocdeStateInterface = enocdeStateInterface;
        export(str);
        enocdeStateInterface.onEncodeStart();
    }

    public float getCurrentDisplayRatio() {
        return this.mRatio;
    }

    public int getProjectTotalTime() {
        nexProject project;
        nexEngine nexengine = this.mEngin;
        if (nexengine == null || (project = nexengine.getProject()) == null) {
            return 0;
        }
        return project.getTotalTime();
    }

    public int getVideoFrames() {
        return this.mVideoFrames;
    }

    public String getVideoPath() {
        return TextUtils.isEmpty(this.mVideoPath) ? "" : this.mVideoPath;
    }

    public int getVideoStartTime() {
        nexClip nexclip = this.mMainVideoClip;
        if (nexclip != null) {
            return nexclip.getProjectStartTime();
        }
        return 0;
    }

    public int getVideoTotalTime() {
        nexClip nexclip = this.mMainVideoClip;
        if (nexclip != null) {
            return nexclip.getTotalTime();
        }
        nexProject nexproject = this.mNexProject;
        if (nexproject != null) {
            return nexproject.getTotalTime();
        }
        return 0;
    }

    public boolean hasEdit() {
        return this.mApplyedEditValue.size() > 0 || this.mHasUsingAutoTrim;
    }

    public boolean haveSavedEditState() {
        return !this.mSavedEditValue.isEmpty();
    }

    public boolean isSourceAudioEnable() {
        nexClip nexclip = this.mMainVideoClip;
        return nexclip == null || nexclip.getClipVolume() == 100;
    }

    public boolean isSupportAutoTrim() {
        return this.mMainVideoClip != null && getProjectTotalTime() > 30000 && !this.mHasApplyedSmartEffect;
    }

    public void keepScreenOn(boolean z) {
        if (this.mDisplayWrapper != null) {
            this.mDisplayWrapper.setKeepScreenOn(z);
        }
    }

    public void load(String str, VideoEditor.OnCompletedListener onCompletedListener) {
        this.mVideoPath = str;
        loadMainThumbnail(str);
        final long currentTimeMillis = System.currentTimeMillis();
        setEngineState(100);
        final String str2 = str;
        final VideoEditor.OnCompletedListener onCompletedListener2 = onCompletedListener;
        NexEngine.init(this.mContext, 1, new Runnable() {
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                Log.d("NexVideoEditor", "sdk init using :" + (currentTimeMillis - currentTimeMillis) + "ms");
                NexVideoEditor nexVideoEditor = NexVideoEditor.this;
                nexVideoEditor.mIsInit = true;
                try {
                    nexEngine unused = nexVideoEditor.mEngin = NexEngine.getEngine(nexVideoEditor.mContext);
                    NexVideoEditor.this.mEngin.setEventHandler(NexVideoEditor.this);
                    Log.d("NexVideoEditor", "engine create time :" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                    new AsyncTask<Void, Void, Boolean>() {
                        /* access modifiers changed from: protected */
                        public Boolean doInBackground(Void... voidArr) {
                            try {
                                long currentTimeMillis = System.currentTimeMillis();
                                NexVideoEditor.this.loadVideo(str2);
                                NexVideoEditor.this.handleHardwareSupport();
                                NexVideoEditor.this.configEngine();
                                NexVideoEditor.this.loadProject();
                                NexVideoEditor.this.initExportParams();
                                Log.d("NexVideoEditor", "load project using :" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                                return true;
                            } catch (Exception e) {
                                Log.e("NexVideoEditor", "doInBackground Exception : ", (Object) e);
                                return false;
                            }
                        }

                        /* access modifiers changed from: protected */
                        public void onPostExecute(Boolean bool) {
                            super.onPostExecute(bool);
                            if (!NexVideoEditor.this.mIsDestroy) {
                                NexVideoEditor.this.mDisplayWrapper.getDisplayView().requestLayout();
                                NexVideoEditor.this.mDisplayWrapper.setAspectRatio(NexVideoEditor.this.mRatio);
                                if (bool.booleanValue()) {
                                    NexVideoEditor.this.loadThumbnail(onCompletedListener2);
                                    NexVideoEditor.this.mEngin.updateScreenMode();
                                    return;
                                }
                                NexVideoEditor.this.setEngineState(500);
                            }
                        }
                    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } catch (Exception e) {
                    Log.e("NexVideoEditor", "sdk init error: %s .", (Object) e.getMessage());
                    NexVideoEditor.this.setEngineState(-500);
                    Log.e("NexVideoEditor", "build manufacturer :" + Build.MANUFACTURER);
                    Log.e("NexVideoEditor", "build model :" + Build.MODEL);
                    HashMap hashMap = new HashMap();
                    hashMap.put("manufacturer", Build.MANUFACTURER);
                    hashMap.put("model", Build.MODEL);
                    GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_sdk_init_error", hashMap);
                }
            }
        });
    }

    public void onCheckDirectExport(int i) {
    }

    public void onClipInfoDone() {
    }

    public void onEncodingDone(boolean z, int i) {
        VideoEditor.EnocdeStateInterface enocdeStateInterface = this.mCurrentEnocdeStateInterface;
        if (enocdeStateInterface != null) {
            enocdeStateInterface.onEncodeEnd(!z, i, 0);
        }
        setEngineState(0);
        Log.i("NexVideoEditor", "onEncodingDone errorCode : %s", (Object) Integer.valueOf(i));
        if (this.mApplyedEditValue.size() > 0) {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : this.mApplyedEditValue.entrySet()) {
                String str = (String) next.getKey();
                if ("bg_audio".equals(str)) {
                    hashMap.put("usingBGM", String.valueOf(((BGMEdit) next.getValue()).mBGMUri));
                } else if ("filter".equals(str)) {
                    hashMap.put("usingFilter", String.valueOf(((FilterEffect) next.getValue()).mFilter.getFilterId()));
                } else if ("source_audio".equals(str)) {
                    hashMap.put("disable_source_audio", "true");
                } else if ("trim".equals(str)) {
                    hashMap.put("usingTRIM", "trim");
                } else if ("edit_type_auto_water_mark_text".equals(str)) {
                    hashMap.put("usingText", "text");
                }
            }
            GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_using_effect", hashMap);
        }
    }

    public void onEncodingProgress(int i) {
        VideoEditor.EnocdeStateInterface enocdeStateInterface = this.mCurrentEnocdeStateInterface;
        if (enocdeStateInterface != null) {
            enocdeStateInterface.onEncodeProgress(i);
        }
        Log.i("NexVideoEditor", "engine encoding : " + i);
    }

    public void onFastPreviewStartDone(int i, int i2, int i3) {
    }

    public void onFastPreviewStopDone(int i) {
    }

    public void onFastPreviewTimeDone(int i) {
    }

    public void onPlayEnd() {
        if (this.mEngineState != 105) {
            setEngineState(0);
            seek(0, (VideoEditor.OnCompletedListener) null);
            Log.i("NexVideoEditor", "onPlayEnd");
        }
    }

    public void onPlayFail(int i, int i2) {
        VideoEditor.EnocdeStateInterface enocdeStateInterface;
        Log.e("NexVideoEditor", "onPlayFail : error code :" + i, (Object) "clipId" + i2);
        HashMap hashMap = new HashMap();
        hashMap.put("errorcofr", String.valueOf(i));
        hashMap.put("state", String.valueOf(this.mEngineState));
        GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_play_failed", hashMap);
        int i3 = this.mEngineState;
        if (!(i3 == 1 || i3 != 105 || (enocdeStateInterface = this.mCurrentEnocdeStateInterface) == null)) {
            enocdeStateInterface.onEncodeEnd(false, -1, -1);
        }
        setEngineState(0);
    }

    public void onPlayStart() {
        if (this.mEngineState != 105) {
            setEngineState(1);
            Log.i("NexVideoEditor", "onPlayStart");
        }
    }

    public void onPreviewPeakMeter(int i, int i2) {
    }

    public void onProgressThumbnailCaching(int i, int i2) {
        VideoEditor.TrimStateInterface trimStateInterface = this.mTrimStateInterface;
        if (trimStateInterface != null) {
            trimStateInterface.onTrimProgress(i);
        }
        Log.i("NexVideoEditor", "engine onProgressThumbnailCaching : " + i + "/" + i2);
    }

    public void onSeekStateChanged(boolean z) {
        Log.e("NexVideoEditor", "onSeekStateChanged " + z);
    }

    public void onSetTimeDone(int i) {
        this.setTimeSuccess = true;
        updataSeek();
        Log.d("NexVideoEditor", "onSetTimeDone : " + i);
    }

    public void onSetTimeFail(int i) {
        updataSeek();
        Log.d("NexVideoEditor", "onSetTimeFail");
    }

    public void onSetTimeIgnored() {
        updataSeek();
        Log.d("NexVideoEditor", "onSetTimeIgnored");
    }

    public void onStateChange(int i, int i2) {
        if (i == 2 && i2 == 1 && this.mEngineState == 102) {
            setEngineState(2);
            VideoEditor.OnCompletedListener onCompletedListener = this.mPauseOnCompletedListener;
            if (onCompletedListener != null) {
                onCompletedListener.onCompleted();
                this.mPauseOnCompletedListener = null;
            }
        }
        Log.d("NexVideoEditor", "nexEngineState : old state :" + i + " new state :" + i2);
    }

    public void onTimeChange(int i) {
        if (this.mEngineState == 1) {
            onTimeChanged(i);
        }
    }

    public void pause() {
        pause((VideoEditor.OnCompletedListener) null);
    }

    public Bitmap pickThumbnail(int i) {
        this.mThumbnailPickCursor = 0;
        return pickThumbnailSerial(i);
    }

    public Bitmap pickThumbnailSerial(int i) {
        List<VideoThumbnail> list = this.preLoadVideoThumbnails;
        Bitmap bitmap = null;
        if (list != null && list.size() > 0) {
            int size = this.preLoadVideoThumbnails.size();
            int i2 = Reader.READ_DONE;
            for (int i3 = this.mThumbnailPickCursor; i3 < size; i3++) {
                VideoThumbnail videoThumbnail = this.preLoadVideoThumbnails.get(i3);
                if (Math.abs(videoThumbnail.getIntrinsicTime() - i) >= i2) {
                    break;
                }
                i2 = Math.abs(videoThumbnail.getIntrinsicTime() - i);
                this.mThumbnailPickCursor = i3;
                bitmap = videoThumbnail.getThumbnail();
            }
        }
        return bitmap;
    }

    public void play() {
        if (this.mEngineState != 0) {
            Log.d("NexVideoEditor", "play is not allowed at EngineState :" + this.mEngineState);
        } else if (this.mEngin != null) {
            setEngineState(103);
            this.mEngin.play();
        }
    }

    public void release() {
        destroy();
        NexEngine.releaseEngine();
    }

    public boolean resetProject(VideoEditor.OnCompletedListener onCompletedListener) {
        return addChange(new Change() {
            public void applyChange() {
                NexVideoEditor nexVideoEditor = NexVideoEditor.this;
                nexClip unused = nexVideoEditor.mMainVideoClip = nexClip.dup(nexVideoEditor.mMainVideoClip);
                NexVideoEditor.this.mMainVideoClip.setRotateDegree(NexVideoEditor.this.mVideoRotation);
                nexProject unused2 = NexVideoEditor.this.mNexProject = new nexProject();
                NexVideoEditor.this.mNexProject.add(NexVideoEditor.this.mMainVideoClip);
                NexVideoEditor.this.mEngin.setProject(NexVideoEditor.this.mNexProject);
                NexVideoEditor.this.mApplyedEditValue.clear();
                TextClip unused3 = NexVideoEditor.this.mOpenClip = null;
                TextClip unused4 = NexVideoEditor.this.mEndClip = null;
            }
        }, onCompletedListener);
    }

    public void restoreEditState() {
        if (haveChange("filter")) {
            FilterEffect filterEffect = (FilterEffect) this.mSavedEditValue.get("filter");
            if (filterEffect == null) {
                filterEffect = new FilterEffect((Filter) null);
            }
            this.mTempEditValue.put("filter", filterEffect);
        }
        if (haveChange("bg_audio")) {
            BGMEdit bGMEdit = (BGMEdit) this.mSavedEditValue.get("bg_audio");
            if (bGMEdit == null) {
                bGMEdit = new BGMEdit((String) null);
            }
            this.mTempEditValue.put("bg_audio", bGMEdit);
        }
        if (haveChange("source_audio")) {
            SourceAudioChange sourceAudioChange = (SourceAudioChange) this.mSavedEditValue.get("source_audio");
            if (sourceAudioChange == null) {
                sourceAudioChange = new SourceAudioChange(true);
            }
            this.mTempEditValue.put("source_audio", sourceAudioChange);
        }
        if (haveChange("edit_type_water_mark")) {
            WaterMark waterMark = (WaterMark) this.mSavedEditValue.get("edit_type_water_mark");
            if (waterMark == null) {
                waterMark = new WaterMark();
            }
            this.mTempEditValue.put("edit_type_water_mark", waterMark);
        }
        if (haveChange("edit_type_auto_water_mark_text")) {
            TextClip textClip = (TextClip) this.mSavedEditValue.get("edit_type_auto_water_mark_text");
            if (textClip == null) {
                textClip = new TextClip();
            }
            this.mTempEditValue.put("edit_type_auto_water_mark_text", textClip);
        }
        if (haveChange("edit_type_adjust")) {
            VideoEditorAdjust videoEditorAdjust = (VideoEditorAdjust) this.mSavedEditValue.get("edit_type_adjust");
            if (videoEditorAdjust == null) {
                videoEditorAdjust = new VideoEditorAdjust(false);
            }
            this.mTempEditValue.put("edit_type_adjust", videoEditorAdjust);
        }
        if (haveChange("edit_type_smart_effect_template")) {
            SmartEffectTemplate smartEffectTemplate = (SmartEffectTemplate) this.mSavedEditValue.get("edit_type_smart_effect_template");
            if (smartEffectTemplate == null) {
                smartEffectTemplate = new SmartEffectTemplate((SmartEffect) null);
            }
            this.mTempEditValue.put("edit_type_smart_effect_template", smartEffectTemplate);
        }
    }

    public void resume() {
        if (this.mEngineState != 2) {
            Log.e("NexVideoEditor", "resume is not allowed at EngineState :" + this.mEngineState);
        } else if (this.mEngin != null) {
            setEngineState(107);
            this.mEngin.resume();
        }
    }

    public void saveEditState() {
        this.mSavedEditValue.clear();
        this.mSavedEditValue.putAll(this.mApplyedEditValue);
    }

    public void seek(final int i, VideoEditor.OnCompletedListener onCompletedListener) {
        this.seekOnCompletedListener = onCompletedListener;
        int i2 = this.mEngineState;
        if (i2 != 0) {
            if (i2 == 1) {
                pause(new VideoEditor.OnCompletedListener() {
                    public void onCompleted() {
                        NexVideoEditor.this.doSeek(i);
                    }
                });
                return;
            } else if (!(i2 == 2 || i2 == 104)) {
                Log.e("NexVideoEditor", "seek is not allowed at EngineState :" + this.mEngineState);
                return;
            }
        }
        doSeek(i);
    }

    public boolean setAutoWaterMark(String str, int i) {
        this.mTempEditValue.put("edit_type_auto_water_mark_text", new TextClip(str, i));
        return false;
    }

    public void setBackgroundMusic(String str) {
        this.mTempEditValue.put("bg_audio", new BGMEdit(str));
    }

    /* access modifiers changed from: protected */
    public void setDisplayView(View view) {
        this.mEngin.setView((nexEngineView) view);
    }

    public void setFilter(Filter filter) {
        this.mTempEditValue.put("filter", new FilterEffect(filter));
    }

    public boolean setSmartEffectTemplate(SmartEffect smartEffect) {
        this.mTempEditValue.put("edit_type_smart_effect_template", new SmartEffectTemplate(smartEffect));
        return false;
    }

    public void setSourceAudioEnable(Boolean bool) {
        this.mTempEditValue.put("source_audio", new SourceAudioChange(bool.booleanValue()));
    }

    public void setTrimInfo(int i, int i2) {
        setTrimInfo(new TrimInfo(i, i2));
    }

    public void setVideoEditorAdjust(boolean z) {
        this.mApplyedEditValue.put("edit_type_adjust", new VideoEditorAdjust(z));
    }

    public boolean setWarterMark(int i, String str) {
        this.mTempEditValue.put("edit_type_water_mark", new WaterMark(i, str));
        return false;
    }

    public void startPreview() {
        startPreview((VideoEditor.OnCompletedListener) null);
    }

    public void startPreview(VideoEditor.OnCompletedListener onCompletedListener) {
        nexEngine nexengine;
        int i = this.mEngineState;
        if ((i == 0 || i == 2) && (nexengine = this.mEngin) != null) {
            int currentPlayTimeTime = nexengine.getCurrentPlayTimeTime();
            if (currentPlayTimeTime == 0) {
                currentPlayTimeTime = 100;
            }
            seek(currentPlayTimeTime, onCompletedListener);
        }
    }

    public void toThirdEditor(Context context) {
        int i = this.mEngineState;
        if (i == 1 || i == 2 || i == 0) {
            try {
                if (this.mNexProject != null) {
                    try {
                        Intent makeKineMasterIntent = this.mNexProject.makeKineMasterIntent();
                        if (makeKineMasterIntent != null) {
                            context.startActivity(makeKineMasterIntent);
                            GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_to_3rd_exitor");
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (ActivityNotFoundException unused2) {
                try {
                    context.startActivity(IntentUtil.makeMarketIntent("com.nexstreaming.app.kinemasterfree", true));
                    GallerySamplingStatHelper.recordCountEvent("video_editor", "video_editor_to_market");
                } catch (ActivityNotFoundException unused3) {
                    Log.e("NexVideoEditor", "no market found !!!!");
                }
            }
        }
    }
}
