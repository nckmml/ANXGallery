package com.nexstreaming.nexeditorsdk;

import android.content.Intent;
import android.graphics.Rect;
import android.util.Log;
import com.google.gson_nex.Gson;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import com.nexstreaming.kminternal.kinemaster.editorwrapper.a;
import com.nexstreaming.nexeditorsdk.exception.ProjectAlreadyAttachedException;
import com.nexstreaming.nexeditorsdk.nexSaveDataFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class nexProject extends b {
    private static String TAG = "nexProject";
    public static final int kAutoThemeClipDuration = 6000;
    public static final int kAutoThemeTransitionDuration = 2000;
    private static int sNextId = 1;
    private int mAudioFadeInTime;
    private int mAudioFadeOutTime;
    int mBGMTrimEndTime;
    int mBGMTrimStartTime;
    private float mBGMVolumeScale;
    private nexClip mBackGroundMusic;
    private boolean mEmptyTheme;
    private String mEndingTitle;
    private int mId;
    private String mLetterbox;
    boolean mLoopBGM;
    private int mManualVolCtl;
    private String mOpeningTitle;
    private List<nexOverlayItem> mOverlayItems;
    private List<nexClip> mPrimaryItems;
    private int mProjectVolume;
    private List<nexAudioItem> mSecondaryItems;
    int mStartTimeBGM;
    private List<nexDrawInfo> mSubEffectInfo;
    private int mTemplateApplyMode;
    private boolean mTemplateOverlappedTransition;
    private nexTheme mTheme;
    private String mThemeId;
    private List<nexDrawInfo> mTopEffectInfo;
    boolean mUseThemeMusic2BGM;
    private List<nexOverlayItem> m_externalView_overlayItems;
    private List<nexClip> m_externalView_primaryItems;
    private List<nexAudioItem> m_externalView_secondaryItems;

    private static class a implements Comparator<nexAudioItem> {
        private a() {
        }

        /* renamed from: a */
        public int compare(nexAudioItem nexaudioitem, nexAudioItem nexaudioitem2) {
            if (nexaudioitem.getStartTime() > nexaudioitem2.getStartTime()) {
                return -1;
            }
            return nexaudioitem.getStartTime() < nexaudioitem2.getStartTime() ? 1 : 0;
        }
    }

    private class b implements Comparator<nexOverlayItem> {
        private b() {
        }

        /* renamed from: a */
        public int compare(nexOverlayItem nexoverlayitem, nexOverlayItem nexoverlayitem2) {
            if (nexoverlayitem.mStartTime > nexoverlayitem2.mStartTime) {
                return -1;
            }
            return nexoverlayitem.mStartTime < nexoverlayitem2.mStartTime ? 1 : 0;
        }
    }

    public nexProject() {
        this.mThemeId = "com.nexstreaming.kinemaster.basic";
        this.mLetterbox = nexApplicationConfig.getDefaultLetterboxEffect();
        this.mTheme = null;
        this.mEmptyTheme = false;
        this.mProjectVolume = 100;
        this.mManualVolCtl = 0;
        this.mAudioFadeInTime = 200;
        this.mAudioFadeOutTime = 5000;
        this.mOpeningTitle = null;
        this.mEndingTitle = null;
        this.mBGMVolumeScale = 0.5f;
        this.mUseThemeMusic2BGM = true;
        this.mLoopBGM = true;
        this.mStartTimeBGM = 0;
        this.mBackGroundMusic = null;
        this.mTemplateApplyMode = 0;
        this.mTemplateOverlappedTransition = true;
        this.mOverlayItems = new ArrayList();
        this.m_externalView_overlayItems = null;
        this.mPrimaryItems = new ArrayList();
        this.m_externalView_primaryItems = null;
        this.mSecondaryItems = new ArrayList();
        this.m_externalView_secondaryItems = null;
        this.mTopEffectInfo = new ArrayList();
        this.mSubEffectInfo = new ArrayList();
        this.mThemeId = null;
        this.mEmptyTheme = true;
        int i = sNextId;
        sNextId = i + 1;
        this.mId = i;
        String str = TAG;
        Log.d(str, "[" + this.mId + "] nexProject create");
    }

    nexProject(nexSaveDataFormat.nexProjectOf nexprojectof) {
        this.mThemeId = "com.nexstreaming.kinemaster.basic";
        this.mLetterbox = nexApplicationConfig.getDefaultLetterboxEffect();
        this.mTheme = null;
        this.mEmptyTheme = false;
        this.mProjectVolume = 100;
        this.mManualVolCtl = 0;
        this.mAudioFadeInTime = 200;
        this.mAudioFadeOutTime = 5000;
        this.mOpeningTitle = null;
        this.mEndingTitle = null;
        this.mBGMVolumeScale = 0.5f;
        this.mUseThemeMusic2BGM = true;
        this.mLoopBGM = true;
        this.mStartTimeBGM = 0;
        this.mBackGroundMusic = null;
        this.mTemplateApplyMode = 0;
        this.mTemplateOverlappedTransition = true;
        this.mOverlayItems = new ArrayList();
        this.m_externalView_overlayItems = null;
        this.mPrimaryItems = new ArrayList();
        this.m_externalView_primaryItems = null;
        this.mSecondaryItems = new ArrayList();
        this.m_externalView_secondaryItems = null;
        this.mTopEffectInfo = new ArrayList();
        this.mSubEffectInfo = new ArrayList();
        this.mProjectVolume = nexprojectof.mProjectVolume;
        this.mManualVolCtl = nexprojectof.mManualVolCtl;
        this.mAudioFadeInTime = nexprojectof.mAudioFadeInTime;
        this.mAudioFadeOutTime = nexprojectof.mAudioFadeOutTime;
        this.mOpeningTitle = nexprojectof.mOpeningTitle;
        this.mEndingTitle = nexprojectof.mEndingTitle;
        this.mBGMVolumeScale = nexprojectof.mBGMVolumeScale;
        int i = sNextId;
        sNextId = i + 1;
        this.mId = i;
        this.mUseThemeMusic2BGM = nexprojectof.mUseThemeMusic2BGM;
        this.mLoopBGM = nexprojectof.mLoopBGM;
        this.mStartTimeBGM = nexprojectof.mStartTimeBGM;
        if (nexprojectof.mBackGroundMusic == null) {
            this.mBackGroundMusic = null;
        } else {
            this.mBackGroundMusic = new nexClip((b) null, nexprojectof.mBackGroundMusic);
        }
        this.mBGMTrimStartTime = nexprojectof.mBGMTrimStartTime;
        this.mBGMTrimEndTime = nexprojectof.mBGMTrimEndTime;
        this.mTemplateApplyMode = nexprojectof.mTemplateApplyMode;
        this.mTemplateOverlappedTransition = nexprojectof.mTemplateOverlappedTransition;
        if (nexprojectof.mPrimaryItems != null) {
            for (nexSaveDataFormat.nexClipOf nexclip : nexprojectof.mPrimaryItems) {
                this.mPrimaryItems.add(new nexClip((b) this, nexclip));
            }
        }
        if (nexprojectof.mSecondaryItems != null) {
            for (nexSaveDataFormat.nexAudioItemOf nexaudioitem : nexprojectof.mSecondaryItems) {
                this.mSecondaryItems.add(new nexAudioItem(this, nexaudioitem));
            }
        }
        if (nexprojectof.mTopEffectInfo != null) {
            this.mTopEffectInfo = nexprojectof.mTopEffectInfo;
        }
        if (nexprojectof.mSubEffectInfo != null) {
            this.mSubEffectInfo = nexprojectof.mSubEffectInfo;
        }
        this.mClipTimeUpdated = true;
    }

    @Deprecated
    public nexProject(nexTheme nextheme) {
        this.mThemeId = "com.nexstreaming.kinemaster.basic";
        this.mLetterbox = nexApplicationConfig.getDefaultLetterboxEffect();
        this.mTheme = null;
        this.mEmptyTheme = false;
        this.mProjectVolume = 100;
        this.mManualVolCtl = 0;
        this.mAudioFadeInTime = 200;
        this.mAudioFadeOutTime = 5000;
        this.mOpeningTitle = null;
        this.mEndingTitle = null;
        this.mBGMVolumeScale = 0.5f;
        this.mUseThemeMusic2BGM = true;
        this.mLoopBGM = true;
        this.mStartTimeBGM = 0;
        this.mBackGroundMusic = null;
        this.mTemplateApplyMode = 0;
        this.mTemplateOverlappedTransition = true;
        this.mOverlayItems = new ArrayList();
        this.m_externalView_overlayItems = null;
        this.mPrimaryItems = new ArrayList();
        this.m_externalView_primaryItems = null;
        this.mSecondaryItems = new ArrayList();
        this.m_externalView_secondaryItems = null;
        this.mTopEffectInfo = new ArrayList();
        this.mSubEffectInfo = new ArrayList();
        this.mThemeId = null;
        this.mEmptyTheme = true;
        int i = sNextId;
        sNextId = i + 1;
        this.mId = i;
        String str = TAG;
        Log.d(str, "[" + this.mId + "] nexProject create");
    }

    @Deprecated
    public nexProject(String str) {
        this.mThemeId = "com.nexstreaming.kinemaster.basic";
        this.mLetterbox = nexApplicationConfig.getDefaultLetterboxEffect();
        this.mTheme = null;
        this.mEmptyTheme = false;
        this.mProjectVolume = 100;
        this.mManualVolCtl = 0;
        this.mAudioFadeInTime = 200;
        this.mAudioFadeOutTime = 5000;
        this.mOpeningTitle = null;
        this.mEndingTitle = null;
        this.mBGMVolumeScale = 0.5f;
        this.mUseThemeMusic2BGM = true;
        this.mLoopBGM = true;
        this.mStartTimeBGM = 0;
        this.mBackGroundMusic = null;
        this.mTemplateApplyMode = 0;
        this.mTemplateOverlappedTransition = true;
        this.mOverlayItems = new ArrayList();
        this.m_externalView_overlayItems = null;
        this.mPrimaryItems = new ArrayList();
        this.m_externalView_primaryItems = null;
        this.mSecondaryItems = new ArrayList();
        this.m_externalView_secondaryItems = null;
        this.mTopEffectInfo = new ArrayList();
        this.mSubEffectInfo = new ArrayList();
        this.mThemeId = null;
        this.mEmptyTheme = true;
        int i = sNextId;
        sNextId = i + 1;
        this.mId = i;
        String str2 = TAG;
        Log.d(str2, "[" + this.mId + "] nexProject create");
    }

    @Deprecated
    public nexProject(String str, String str2, String str3) {
        this.mThemeId = "com.nexstreaming.kinemaster.basic";
        this.mLetterbox = nexApplicationConfig.getDefaultLetterboxEffect();
        this.mTheme = null;
        this.mEmptyTheme = false;
        this.mProjectVolume = 100;
        this.mManualVolCtl = 0;
        this.mAudioFadeInTime = 200;
        this.mAudioFadeOutTime = 5000;
        this.mOpeningTitle = null;
        this.mEndingTitle = null;
        this.mBGMVolumeScale = 0.5f;
        this.mUseThemeMusic2BGM = true;
        this.mLoopBGM = true;
        this.mStartTimeBGM = 0;
        this.mBackGroundMusic = null;
        this.mTemplateApplyMode = 0;
        this.mTemplateOverlappedTransition = true;
        this.mOverlayItems = new ArrayList();
        this.m_externalView_overlayItems = null;
        this.mPrimaryItems = new ArrayList();
        this.m_externalView_primaryItems = null;
        this.mSecondaryItems = new ArrayList();
        this.m_externalView_secondaryItems = null;
        this.mTopEffectInfo = new ArrayList();
        this.mSubEffectInfo = new ArrayList();
        this.mThemeId = null;
        this.mEmptyTheme = true;
        int i = sNextId;
        sNextId = i + 1;
        this.mId = i;
        String str4 = TAG;
        Log.d(str4, "[" + this.mId + "] nexProject create");
    }

    private int addClone(int i, nexClip nexclip) {
        updateTimeLine(false);
        nexclip.setAttachmentState(true, this);
        this.mPrimaryItems.add(i, nexclip);
        return 0;
    }

    private int addCloneAudio(int i, nexAudioItem nexaudioitem) {
        updateTimeLine(false);
        nexaudioitem.getClip().setAttachmentState(true, this);
        this.mSecondaryItems.add(i, nexaudioitem);
        return 0;
    }

    private boolean checkAudioTime(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (nexAudioItem next : this.mSecondaryItems) {
            if (next.getEndTime() > i && next.getStartTime() < i2) {
                arrayList.add(next);
            }
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < arrayList.size() - 1) {
            int i5 = i3 + 1;
            if (((nexAudioItem) arrayList.get(i3)).getEndTime() > ((nexAudioItem) arrayList.get(i5)).getStartTime() && ((nexAudioItem) arrayList.get(i3)).getStartTime() < ((nexAudioItem) arrayList.get(i5)).getEndTime()) {
                i4++;
            }
            i3 = i5;
        }
        return i4 < 2;
    }

    public static nexProject clone(nexProject nexproject) {
        nexProject nexproject2 = nexproject.mEmptyTheme ? new nexProject() : new nexProject(nexproject.mThemeId);
        String str = TAG;
        Log.d(str, "clone src project[" + nexproject.mId + "] to dest project[" + nexproject2.mId + "]");
        nexproject2.mThemeId = nexproject.mThemeId;
        nexproject2.mTheme = nexproject.mTheme;
        nexproject2.mEmptyTheme = nexproject.mEmptyTheme;
        nexproject2.mAudioFadeInTime = nexproject.mAudioFadeInTime;
        nexproject2.mAudioFadeOutTime = nexproject.mAudioFadeOutTime;
        nexproject2.mOpeningTitle = nexproject.mOpeningTitle;
        nexproject2.mEndingTitle = nexproject.mEndingTitle;
        nexproject2.mBGMVolumeScale = nexproject.mBGMVolumeScale;
        nexproject2.mUseThemeMusic2BGM = nexproject.mUseThemeMusic2BGM;
        nexproject2.mLoopBGM = nexproject.mLoopBGM;
        nexproject2.mStartTimeBGM = nexproject.mStartTimeBGM;
        nexproject2.mClipTimeUpdated = nexproject.mClipTimeUpdated;
        nexproject2.mNeedLoadList = nexproject.mNeedLoadList;
        for (nexOverlayItem clone : nexproject.getOverlayItems()) {
            nexproject2.addOverlay(nexOverlayItem.clone(clone));
        }
        nexClip nexclip = nexproject.mBackGroundMusic;
        if (nexclip != null) {
            nexproject2.mBackGroundMusic = nexClip.clone(nexclip);
        }
        for (nexClip clone2 : nexproject.getPrimaryItems()) {
            nexproject2.addClone(nexproject2.getTotalClipCount(true), nexClip.clone(clone2));
        }
        int i = 0;
        for (nexAudioItem clone3 : nexproject.getAudioItems()) {
            nexproject2.addCloneAudio(i, nexAudioItem.clone(clone3));
            i++;
        }
        return nexproject2;
    }

    public static nexProject createFromSaveString(String str) {
        return new nexProject(((nexSaveDataFormat) new Gson().fromJson(str, nexSaveDataFormat.class)).project);
    }

    private int getClipTimeGuideLine(int i) {
        int i2 = i - 1;
        int i3 = 0;
        if (i >= this.mPrimaryItems.size() || i < 0) {
            return 0;
        }
        if (i2 >= 0) {
            int offset = this.mPrimaryItems.get(i2).getTransitionEffect(true).getOffset();
            int overlap = this.mPrimaryItems.get(i2).getTransitionEffect(true).getOverlap();
            int duration = this.mPrimaryItems.get(i2).getTransitionEffect(true).getDuration();
            i3 = (duration - ((offset * duration) / 100)) + ((duration * overlap) / 100);
        }
        int offset2 = this.mPrimaryItems.get(i).getTransitionEffect(true).getOffset();
        int duration2 = this.mPrimaryItems.get(i).getTransitionEffect(true).getDuration();
        return ((i3 + (duration2 - ((offset2 * duration2) / 100))) / 500) * 500;
    }

    private int getTransitionTimeGuideLine(int i, int i2) {
        int i3;
        int i4 = i - 1;
        int i5 = i + 1;
        if (i5 >= this.mPrimaryItems.size()) {
            return 0;
        }
        if (i2 < 0) {
            i2 = 500;
        }
        int projectDuration = this.mPrimaryItems.get(i).getProjectDuration();
        if (i4 >= 0) {
            int offset = this.mPrimaryItems.get(i4).getTransitionEffect(true).getOffset();
            int overlap = this.mPrimaryItems.get(i4).getTransitionEffect(true).getOverlap();
            int duration = this.mPrimaryItems.get(i4).getTransitionEffect(true).getDuration();
            projectDuration = (projectDuration - (duration - ((offset * duration) / 100))) - ((duration * overlap) / 100);
        }
        int i6 = projectDuration - i2;
        if (i5 <= 0 || i6 <= (i3 = this.mPrimaryItems.get(i5).getProjectDuration() / 3)) {
            i3 = i6;
        }
        int offset2 = this.mPrimaryItems.get(i).getTransitionEffect(true).getOffset();
        int i7 = i3 * 2;
        if (offset2 > 0) {
            i7 = (i3 * 100) / offset2;
        }
        return (i7 / 500) * 500;
    }

    private int isPrimaryItem(nexClip nexclip) {
        if (nexclip.getClipType() == 3) {
            return 0;
        }
        return (nexclip.getClipType() == 4 || nexclip.getClipType() == 1) ? 1 : -1;
    }

    private static void logClipOf(int i, nexSaveDataFormat.nexClipOf nexclipof) {
        String str = TAG;
        Log.d(str, "[" + i + "]nexClipOf.m_BGMVolume=" + nexclipof.m_BGMVolume);
        String str2 = TAG;
        Log.d(str2, "[" + i + "]nexClipOf.mCollageDrawInfoID=" + nexclipof.mCollageDrawInfoID);
        String str3 = TAG;
        Log.d(str3, "[" + i + "]nexClipOf.mPath=" + nexclipof.mPath);
        String str4 = TAG;
        Log.d(str4, "[" + i + "]nexClipOf.mTransCodingPath=" + nexclipof.mTransCodingPath);
        String str5 = TAG;
        Log.d(str5, "[" + i + "]nexClipOf.m_Brightness=" + nexclipof.m_Brightness);
        String str6 = TAG;
        Log.d(str6, "[" + i + "]nexClipOf.m_ClipVolume=" + nexclipof.m_ClipVolume);
        String str7 = TAG;
        Log.d(str7, "[" + i + "]nexClipOf.m_Contrast=" + nexclipof.m_Contrast);
        String str8 = TAG;
        Log.d(str8, "[" + i + "]nexClipOf.m_Saturation=" + nexclipof.m_Saturation);
        String str9 = TAG;
        Log.d(str9, "[" + i + "]nexClipOf.mAudioOnOff=" + nexclipof.mAudioOnOff);
        String str10 = TAG;
        Log.d(str10, "[" + i + "]nexClipOf.mAVSyncAudioStartTime=" + nexclipof.mAVSyncAudioStartTime);
        String str11 = TAG;
        Log.d(str11, "[" + i + "]nexClipOf.mCustomLUT_A=" + nexclipof.mCustomLUT_A);
        String str12 = TAG;
        Log.d(str12, "[" + i + "]nexClipOf.mCustomLUT_B=" + nexclipof.mCustomLUT_B);
        String str13 = TAG;
        Log.d(str13, "[" + i + "]nexClipOf.mCustomLUT_Power=" + nexclipof.mCustomLUT_Power);
        String str14 = TAG;
        Log.d(str14, "[" + i + "]nexClipOf.mDuration=" + nexclipof.mDuration);
        String str15 = TAG;
        Log.d(str15, "[" + i + "]nexClipOf.mEndTime=" + nexclipof.mEndTime);
        String str16 = TAG;
        Log.d(str16, "[" + i + "]nexClipOf.mFaceDetected=" + nexclipof.mFaceDetected);
        String str17 = TAG;
        Log.d(str17, "[" + i + "]nexClipOf.mFacedetectProcessed=" + nexclipof.mFacedetectProcessed);
        String str18 = TAG;
        Log.d(str18, "[" + i + "]nexClipOf.misMustDownSize=" + nexclipof.misMustDownSize);
        String str19 = TAG;
        Log.d(str19, "[" + i + "]nexClipOf.mMediaInfoUseCache=" + nexclipof.mMediaInfoUseCache);
        String str20 = TAG;
        Log.d(str20, "[" + i + "]nexClipOf.mOverlappedTransition=" + nexclipof.mOverlappedTransition);
        String str21 = TAG;
        Log.d(str21, "[" + i + "]nexClipOf.mPropertySlowVideoMode=" + nexclipof.mPropertySlowVideoMode);
        String str22 = TAG;
        Log.d(str22, "[" + i + "]nexClipOf.mStartTime=" + nexclipof.mStartTime);
        String str23 = TAG;
        Log.d(str23, "[" + i + "]nexClipOf.mTemplateAudioPos=" + nexclipof.mTemplateAudioPos);
        String str24 = TAG;
        Log.d(str24, "[" + i + "]nexClipOf.mTemplateEffectID=" + nexclipof.mTemplateEffectID);
        String str25 = TAG;
        Log.d(str25, "[" + i + "]nexClipOf.mTitleEffectEndTime=" + nexclipof.mTitleEffectEndTime);
        String str26 = TAG;
        Log.d(str26, "[" + i + "]nexClipOf.mTitleEffectStartTime=" + nexclipof.mTitleEffectStartTime);
        String str27 = TAG;
        Log.d(str27, "[" + i + "]nexClipOf.mInfo.mExistAudio=" + nexclipof.mInfo.mExistAudio);
        String str28 = TAG;
        Log.d(str28, "[" + i + "]nexClipOf.mInfo.mVideoTotalTime=" + nexclipof.mInfo.mVideoTotalTime);
        String str29 = TAG;
        Log.d(str29, "[" + i + "]nexClipOf.mInfo.mSeekPointCount=" + nexclipof.mInfo.mSeekPointCount);
        String str30 = TAG;
        Log.d(str30, "[" + i + "]nexClipOf.mInfo.mFramesPerSecond=" + nexclipof.mInfo.mFramesPerSecond);
        String str31 = TAG;
        Log.d(str31, "[" + i + "]nexClipOf.mInfo.mAudioCodecType=" + nexclipof.mInfo.mAudioCodecType);
        String str32 = TAG;
        Log.d(str32, "[" + i + "]nexClipOf.mInfo.mMimeType=" + nexclipof.mInfo.mMimeType);
        String str33 = TAG;
        Log.d(str33, "[" + i + "]nexClipOf.mInfo.mVideoCodecType=" + nexclipof.mInfo.mVideoCodecType);
        String str34 = TAG;
        Log.d(str34, "[" + i + "]nexClipOf.mInfo.mAudioBitrate=" + nexclipof.mInfo.mAudioBitrate);
        String str35 = TAG;
        Log.d(str35, "[" + i + "]nexClipOf.mInfo.mAudioTotalTime=" + nexclipof.mInfo.mAudioTotalTime);
        String str36 = TAG;
        Log.d(str36, "[" + i + "]nexClipOf.mInfo.mClipType=" + nexclipof.mInfo.mClipType);
        String str37 = TAG;
        Log.d(str37, "[" + i + "]nexClipOf.mInfo.mDisplayHeight=" + nexclipof.mInfo.mDisplayHeight);
        String str38 = TAG;
        Log.d(str38, "[" + i + "]nexClipOf.mInfo.mDisplayWidth=" + nexclipof.mInfo.mDisplayWidth);
        String str39 = TAG;
        Log.d(str39, "[" + i + "]nexClipOf.mInfo.mExistVideo=" + nexclipof.mInfo.mExistVideo);
        String str40 = TAG;
        Log.d(str40, "[" + i + "]nexClipOf.mInfo.mH264Level=" + nexclipof.mInfo.mH264Level);
        String str41 = TAG;
        Log.d(str41, "[" + i + "]nexClipOf.mInfo.mH264Profile=" + nexclipof.mInfo.mH264Profile);
        String str42 = TAG;
        Log.d(str42, "[" + i + "]nexClipOf.mInfo.mHeight=" + nexclipof.mInfo.mHeight);
        String str43 = TAG;
        Log.d(str43, "[" + i + "]nexClipOf.mInfo.mRotateDegreeInMeta=" + nexclipof.mInfo.mRotateDegreeInMeta);
        String str44 = TAG;
        Log.d(str44, "[" + i + "]nexClipOf.mInfo.mSuppertedResult=" + nexclipof.mInfo.mSuppertedResult);
        String str45 = TAG;
        Log.d(str45, "[" + i + "]nexClipOf.mInfo.mTotalTime=" + nexclipof.mInfo.mTotalTime);
        String str46 = TAG;
        Log.d(str46, "[" + i + "]nexClipOf.mInfo.mVideoBitrate=" + nexclipof.mInfo.mVideoBitrate);
        String str47 = TAG;
        Log.d(str47, "[" + i + "]nexClipOf.mInfo.mVideoRenderMode=" + nexclipof.mInfo.mVideoRenderMode);
        String str48 = TAG;
        Log.d(str48, "[" + i + "]nexClipOf.mInfo.mVideoHDRType=" + nexclipof.mInfo.mVideoHDRType);
        String str49 = TAG;
        Log.d(str49, "[" + i + "]nexClipOf.mInfo.mVideoTotalTime=" + nexclipof.mInfo.mVideoTotalTime);
        String str50 = TAG;
        Log.d(str50, "[" + i + "]nexClipOf.mInfo.mWidth=" + nexclipof.mInfo.mWidth);
        if (nexclipof.mClipEffect == null) {
            String str51 = TAG;
            Log.d(str51, "[" + i + "]nexClipOf.mClipEffect= null");
        } else {
            String str52 = TAG;
            Log.d(str52, "[" + i + "]nexClipOf.mClipEffect.mAutoID=" + nexclipof.mClipEffect.mAutoID);
            String str53 = TAG;
            Log.d(str53, "[" + i + "]nexClipOf.mClipEffect.mID=" + nexclipof.mClipEffect.mID);
            String str54 = TAG;
            Log.d(str54, "[" + i + "]nexClipOf.mClipEffect.mName=" + nexclipof.mClipEffect.mName);
            String str55 = TAG;
            Log.d(str55, "[" + i + "]nexClipOf.mClipEffect.mID=" + nexclipof.mClipEffect.mDuration);
            String str56 = TAG;
            Log.d(str56, "[" + i + "]nexClipOf.mClipEffect.mEffectOffset=" + nexclipof.mClipEffect.mEffectOffset);
            String str57 = TAG;
            Log.d(str57, "[" + i + "]nexClipOf.mClipEffect.mEffectOverlap=" + nexclipof.mClipEffect.mEffectOverlap);
            String str58 = TAG;
            Log.d(str58, "[" + i + "]nexClipOf.mClipEffect.mIsResolveOptions=" + nexclipof.mClipEffect.mIsResolveOptions);
            String str59 = TAG;
            Log.d(str59, "[" + i + "]nexClipOf.mClipEffect.mMaxDuration=" + nexclipof.mClipEffect.mMaxDuration);
            String str60 = TAG;
            Log.d(str60, "[" + i + "]nexClipOf.mClipEffect.mMinDuration=" + nexclipof.mClipEffect.mMinDuration);
            String str61 = TAG;
            Log.d(str61, "[" + i + "]nexClipOf.mClipEffect.mOptionsUpdate=" + nexclipof.mClipEffect.mOptionsUpdate);
            String str62 = TAG;
            Log.d(str62, "[" + i + "]nexClipOf.mClipEffect.mShowEndTime=" + nexclipof.mClipEffect.mShowEndTime);
            String str63 = TAG;
            Log.d(str63, "[" + i + "]nexClipOf.mClipEffect.mShowStartTime=" + nexclipof.mClipEffect.mShowStartTime);
            String str64 = TAG;
            Log.d(str64, "[" + i + "]nexClipOf.mClipEffect.mType=" + nexclipof.mClipEffect.mType);
            String str65 = TAG;
            Log.d(str65, "[" + i + "]nexClipOf.mClipEffect.itemMethodType=" + nexclipof.mClipEffect.itemMethodType);
        }
        if (nexclipof.mTransitionEffect == null) {
            String str66 = TAG;
            Log.d(str66, "[" + i + "]nexClipOf.mTransitionEffect= null");
        } else {
            String str67 = TAG;
            Log.d(str67, "[" + i + "]nexClipOf.mTransitionEffect.mAutoID=" + nexclipof.mClipEffect.mAutoID);
            String str68 = TAG;
            Log.d(str68, "[" + i + "]nexClipOf.mTransitionEffect.mID=" + nexclipof.mClipEffect.mID);
            String str69 = TAG;
            Log.d(str69, "[" + i + "]nexClipOf.mTransitionEffect.mName=" + nexclipof.mClipEffect.mName);
            String str70 = TAG;
            Log.d(str70, "[" + i + "]nexClipOf.mTransitionEffect.mID=" + nexclipof.mClipEffect.mDuration);
            String str71 = TAG;
            Log.d(str71, "[" + i + "]nexClipOf.mTransitionEffect.mEffectOffset=" + nexclipof.mClipEffect.mEffectOffset);
            String str72 = TAG;
            Log.d(str72, "[" + i + "]nexClipOf.mTransitionEffect.mEffectOverlap=" + nexclipof.mClipEffect.mEffectOverlap);
            String str73 = TAG;
            Log.d(str73, "[" + i + "]nexClipOf.mTransitionEffect.mIsResolveOptions=" + nexclipof.mClipEffect.mIsResolveOptions);
            String str74 = TAG;
            Log.d(str74, "[" + i + "]nexClipOf.mTransitionEffect.mMaxDuration=" + nexclipof.mClipEffect.mMaxDuration);
            String str75 = TAG;
            Log.d(str75, "[" + i + "]nexClipOf.mTransitionEffect.mMinDuration=" + nexclipof.mClipEffect.mMinDuration);
            String str76 = TAG;
            Log.d(str76, "[" + i + "]nexClipOf.mTransitionEffect.mOptionsUpdate=" + nexclipof.mClipEffect.mOptionsUpdate);
            String str77 = TAG;
            Log.d(str77, "[" + i + "]nexClipOf.mTransitionEffect.mShowEndTime=" + nexclipof.mClipEffect.mShowEndTime);
            String str78 = TAG;
            Log.d(str78, "[" + i + "]nexClipOf.mTransitionEffect.mShowStartTime=" + nexclipof.mClipEffect.mShowStartTime);
            String str79 = TAG;
            Log.d(str79, "[" + i + "]nexClipOf.mTransitionEffect.mType=" + nexclipof.mClipEffect.mType);
            String str80 = TAG;
            Log.d(str80, "[" + i + "]nexClipOf.mTransitionEffect.itemMethodType=" + nexclipof.mClipEffect.itemMethodType);
        }
        if (nexclipof.mCrop == null) {
            String str81 = TAG;
            Log.d(str81, "[" + i + "]nexClipOf.mCrop= null");
        } else {
            String str82 = TAG;
            Log.d(str82, "[" + i + "]nexClipOf.mCrop.m_endPositionBottom=" + nexclipof.mCrop.m_endPositionBottom);
            String str83 = TAG;
            Log.d(str83, "[" + i + "]nexClipOf.mCrop.m_endPositionLeft=" + nexclipof.mCrop.m_endPositionLeft);
            String str84 = TAG;
            Log.d(str84, "[" + i + "]nexClipOf.mCrop.m_endPositionRight=" + nexclipof.mCrop.m_endPositionRight);
            String str85 = TAG;
            Log.d(str85, "[" + i + "]nexClipOf.mCrop.m_endPositionTop=" + nexclipof.mCrop.m_endPositionTop);
            String str86 = TAG;
            Log.d(str86, "[" + i + "]nexClipOf.mCrop.m_startPositionBottom=" + nexclipof.mCrop.m_startPositionBottom);
            String str87 = TAG;
            Log.d(str87, "[" + i + "]nexClipOf.mCrop.m_startPositionLeft=" + nexclipof.mCrop.m_startPositionLeft);
            String str88 = TAG;
            Log.d(str88, "[" + i + "]nexClipOf.mCrop.m_startPositionLeft=" + nexclipof.mCrop.m_startPositionRight);
            String str89 = TAG;
            Log.d(str89, "[" + i + "]nexClipOf.mCrop.m_startPositionLeft=" + nexclipof.mCrop.m_startPositionTop);
            String str90 = TAG;
            Log.d(str90, "[" + i + "]nexClipOf.mCrop.m_faceBounds_bottom=" + nexclipof.mCrop.m_faceBounds_bottom);
            String str91 = TAG;
            Log.d(str91, "[" + i + "]nexClipOf.mCrop.m_faceBounds_left=" + nexclipof.mCrop.m_faceBounds_left);
            String str92 = TAG;
            Log.d(str92, "[" + i + "]nexClipOf.mCrop.m_faceBounds_right=" + nexclipof.mCrop.m_faceBounds_right);
            String str93 = TAG;
            Log.d(str93, "[" + i + "]nexClipOf.mCrop.m_faceBounds_set=" + nexclipof.mCrop.m_faceBounds_set);
            String str94 = TAG;
            Log.d(str94, "[" + i + "]nexClipOf.mCrop.m_faceBounds_top=" + nexclipof.mCrop.m_faceBounds_top);
            String str95 = TAG;
            Log.d(str95, "[" + i + "]nexClipOf.mCrop.m_facePositionBottom=" + nexclipof.mCrop.m_facePositionBottom);
            String str96 = TAG;
            Log.d(str96, "[" + i + "]nexClipOf.mCrop.m_facePositionLeft=" + nexclipof.mCrop.m_facePositionLeft);
            String str97 = TAG;
            Log.d(str97, "[" + i + "]nexClipOf.mCrop.m_facePositionRight=" + nexclipof.mCrop.m_facePositionRight);
            String str98 = TAG;
            Log.d(str98, "[" + i + "]nexClipOf.mCrop.m_facePositionTop=" + nexclipof.mCrop.m_facePositionTop);
            String str99 = TAG;
            Log.d(str99, "[" + i + "]nexClipOf.mCrop.m_height=" + nexclipof.mCrop.m_height);
            String str100 = TAG;
            Log.d(str100, "[" + i + "]nexClipOf.mCrop.m_height=" + nexclipof.mCrop.m_width);
            String str101 = TAG;
            Log.d(str101, "[" + i + "]nexClipOf.mCrop.m_rotatedEndPositionBottom=" + nexclipof.mCrop.m_rotatedEndPositionBottom);
            String str102 = TAG;
            Log.d(str102, "[" + i + "]nexClipOf.mCrop.m_rotatedEndPositionLeft=" + nexclipof.mCrop.m_rotatedEndPositionLeft);
            String str103 = TAG;
            Log.d(str103, "[" + i + "]nexClipOf.mCrop.m_rotatedEndPositionRight=" + nexclipof.mCrop.m_rotatedEndPositionRight);
            String str104 = TAG;
            Log.d(str104, "[" + i + "]nexClipOf.mCrop.m_rotatedEndPositionTop=" + nexclipof.mCrop.m_rotatedEndPositionTop);
            String str105 = TAG;
            Log.d(str105, "[" + i + "]nexClipOf.mCrop.m_rotation=" + nexclipof.mCrop.m_rotation);
            String str106 = TAG;
            Log.d(str106, "[" + i + "]nexClipOf.mCrop.m_rotatedStartPositionBottom=" + nexclipof.mCrop.m_rotatedStartPositionBottom);
            String str107 = TAG;
            Log.d(str107, "[" + i + "]nexClipOf.mCrop.m_rotatedStartPositionBottom=" + nexclipof.mCrop.m_rotatedStartPositionLeft);
            String str108 = TAG;
            Log.d(str108, "[" + i + "]nexClipOf.mCrop.m_rotatedStartPositionBottom=" + nexclipof.mCrop.m_rotatedStartPositionRight);
            String str109 = TAG;
            Log.d(str109, "[" + i + "]nexClipOf.mCrop.m_rotatedStartPositionBottom=" + nexclipof.mCrop.m_rotatedStartPositionTop);
        }
        if (nexclipof.mDrawInfos != null) {
            for (nexDrawInfo next : nexclipof.mDrawInfos) {
                String str110 = TAG;
                Log.d(str110, "[" + i + "]nexClipOf.mDrawInfos.getCustomLUTA=" + next.getCustomLUTA());
                String str111 = TAG;
                Log.d(str111, "[" + i + "]nexClipOf.mDrawInfos.getCustomLUTB=" + next.getCustomLUTB());
                String str112 = TAG;
                Log.d(str112, "[" + i + "]nexClipOf.mDrawInfos.getEffectID=" + next.getEffectID());
                String str113 = TAG;
                Log.d(str113, "[" + i + "]nexClipOf.mDrawInfos.getTitle=" + next.getTitle());
                String str114 = TAG;
                Log.d(str114, "[" + i + "]nexClipOf.mDrawInfos.getBrightness=" + next.getBrightness());
                String str115 = TAG;
                Log.d(str115, "[" + i + "]nexClipOf.mDrawInfos.getClipID=" + next.getClipID());
                String str116 = TAG;
                Log.d(str116, "[" + i + "]nexClipOf.mDrawInfos.getEndRect=" + next.getEndRect().toString());
                String str117 = TAG;
                Log.d(str117, "[" + i + "]nexClipOf.mDrawInfos.getStartRect=" + next.getStartRect().toString());
                String str118 = TAG;
                Log.d(str118, "[" + i + "]nexClipOf.mDrawInfos.getContrast=" + next.getContrast());
                String str119 = TAG;
                Log.d(str119, "[" + i + "]nexClipOf.mDrawInfos.getSubEffectID=" + next.getSubEffectID());
                String str120 = TAG;
                Log.d(str120, "[" + i + "]nexClipOf.mDrawInfos.getTranslateX=" + next.getUserTranslateX());
                String str121 = TAG;
                Log.d(str121, "[" + i + "]nexClipOf.mDrawInfos.getTranslateX=" + next.getUserTranslateY());
                String str122 = TAG;
                Log.d(str122, "[" + i + "]nexClipOf.mDrawInfos.getRotateState=" + next.getRotateState());
                String str123 = TAG;
                Log.d(str123, "[" + i + "]nexClipOf.mDrawInfos.getUserRotateState=" + next.getUserRotateState());
                String str124 = TAG;
                Log.d(str124, "[" + i + "]nexClipOf.mDrawInfos.getEndTime=" + next.getEndTime());
            }
        }
    }

    static int logFromString(String str) {
        nexSaveDataFormat nexsavedataformat = (nexSaveDataFormat) new Gson().fromJson(str, nexSaveDataFormat.class);
        Log.d(TAG, "nexSaveDataFormatVersion=" + nexsavedataformat.nexSaveDataFormatVersion);
        Log.d(TAG, "project.mEndingTitle=" + nexsavedataformat.project.mEndingTitle);
        Log.d(TAG, "project.mOpeningTitle=" + nexsavedataformat.project.mOpeningTitle);
        Log.d(TAG, "project.mAudioFadeInTime=" + nexsavedataformat.project.mAudioFadeInTime);
        Log.d(TAG, "project.mAudioFadeOutTime=" + nexsavedataformat.project.mAudioFadeOutTime);
        if (nexsavedataformat.project.mBackGroundMusic == null) {
            Log.d(TAG, "project.mBackGroundMusic= null");
        } else {
            Log.d(TAG, "project.mBackGroundMusic= set");
        }
        Log.d(TAG, "project.mBGMTrimEndTime=" + nexsavedataformat.project.mBGMTrimEndTime);
        Log.d(TAG, "project.mBGMTrimStartTime=" + nexsavedataformat.project.mBGMTrimStartTime);
        Log.d(TAG, "project.mBGMVolumeScale=" + nexsavedataformat.project.mBGMVolumeScale);
        Log.d(TAG, "project.mLoopBGM=" + nexsavedataformat.project.mLoopBGM);
        Log.d(TAG, "project.mManualVolCtl=" + nexsavedataformat.project.mManualVolCtl);
        Log.d(TAG, "project.mProjectVolume=" + nexsavedataformat.project.mProjectVolume);
        Log.d(TAG, "project.mStartTimeBGM=" + nexsavedataformat.project.mStartTimeBGM);
        Log.d(TAG, "project.mTemplateOverlappedTransition=" + nexsavedataformat.project.mTemplateOverlappedTransition);
        Log.d(TAG, "project.mUseThemeMusic2BGM=" + nexsavedataformat.project.mUseThemeMusic2BGM);
        if (nexsavedataformat.project.mPrimaryItems == null) {
            Log.d(TAG, "project.mPrimaryItems=null");
            return 0;
        }
        int i = 1;
        for (nexSaveDataFormat.nexClipOf logClipOf : nexsavedataformat.project.mPrimaryItems) {
            logClipOf(i, logClipOf);
            i++;
        }
        return 0;
    }

    public int add(int i, boolean z, nexClip nexclip) {
        if (!nexclip.getAttachmentState()) {
            updateTimeLine(false);
            nexclip.setAttachmentState(true, this);
            if (z) {
                if (this.mEmptyTheme) {
                    nexclip.isFaceDetectProcessed();
                    nexclip.getClipEffect(true).setEffectNone();
                    nexclip.getTransitionEffect(true).setEffectNone();
                    nexclip.getTransitionEffect(true).setDuration(0);
                }
                this.mPrimaryItems.add(i, nexclip);
            } else {
                addAudio(nexclip, i, nexclip.getTotalTime() + i);
            }
            return 0;
        }
        String str = TAG;
        Log.e(str, "[" + this.mId + "] add() ProjectAlreadyAttachedException index=" + i);
        throw new ProjectAlreadyAttachedException();
    }

    public int add(nexClip nexclip) {
        int isPrimaryItem = isPrimaryItem(nexclip);
        return isPrimaryItem < 0 ? isPrimaryItem : isPrimaryItem == 1 ? add(this.mPrimaryItems.size(), true, nexclip) : add(this.mSecondaryItems.size(), false, nexclip);
    }

    public int addAudio(nexClip nexclip, int i, int i2) {
        if (!checkAudioTime(i, i2)) {
            return -1;
        }
        nexclip.setAttachmentState(true, this);
        nexAudioItem nexaudioitem = new nexAudioItem(nexclip, i, i2);
        int id = nexaudioitem.getId();
        this.mSecondaryItems.add(nexaudioitem);
        Collections.sort(this.mSecondaryItems, new a());
        this.mNeedLoadList = true;
        return id;
    }

    public boolean addOverlay(nexOverlayItem nexoverlayitem) {
        if (nexoverlayitem.getId() == 0) {
            return false;
        }
        this.mOverlayItems.add(nexoverlayitem);
        Collections.sort(this.mOverlayItems, new b());
        return true;
    }

    /* access modifiers changed from: package-private */
    public int adjustmentEffectTime(int i, int i2, int i3, int i4) {
        nexClip clip = getClip(i2, true);
        int projectEndTime = clip.getProjectEndTime() - ((clip.getTransitionEffect().getDuration() * clip.getTransitionEffect().getOffset()) / 100);
        int duration = clip.getTransitionEffect().getDuration() + projectEndTime;
        int projectStartTime = getClip(i, true).getProjectStartTime();
        int projectEndTime2 = getClip(i2, true).getProjectEndTime();
        for (int i5 = 0; i5 < getTotalClipCount(false); i5++) {
            nexClip clip2 = getClip(i5, false);
            if (clip2.getTemplateEffectID() == (-251658241 & i4) && projectStartTime <= clip2.getProjectStartTime() && projectEndTime2 > clip2.getProjectStartTime()) {
                remove(clip2);
                int templateAudioPos = (((projectEndTime - i3) * clip2.getTemplateAudioPos()) / 100) + i3;
                addAudio(clip2, templateAudioPos, clip2.getTotalTime() + templateAudioPos);
            }
        }
        while (i <= i2) {
            nexClip clip3 = getClip(i, true);
            clip3.mTitleEffectStartTime = i3;
            clip3.mTitleEffectEndTime = projectEndTime;
            clip3.getClipEffect().setEffectShowTime(0, 0);
            i++;
        }
        return duration;
    }

    public void allClear(boolean z) {
        if (z) {
            int size = this.mPrimaryItems.size();
            for (int i = 0; i < size; i++) {
                this.mPrimaryItems.get(i).setAttachmentState(false, (b) null);
            }
            this.mPrimaryItems.clear();
        } else {
            int size2 = this.mSecondaryItems.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.mSecondaryItems.get(i2).mClip.setAttachmentState(false, (b) null);
            }
            this.mSecondaryItems.clear();
        }
        this.mClipTimeUpdated = false;
        this.mTopEffectInfo.clear();
        this.mSubEffectInfo.clear();
    }

    public int changeAudio(nexAudioItem nexaudioitem, int i, int i2) {
        remove(nexaudioitem.getClip());
        if (!checkAudioTime(i, i2)) {
            this.mSecondaryItems.add(nexaudioitem);
            nexaudioitem.getClip().setAttachmentState(true, this);
            Collections.sort(this.mSecondaryItems, new a());
            return -1;
        }
        nexaudioitem.setProjectTime(i, i2);
        this.mSecondaryItems.add(nexaudioitem);
        nexaudioitem.getClip().setAttachmentState(true, this);
        Collections.sort(this.mSecondaryItems, new a());
        this.mNeedLoadList = true;
        return 0;
    }

    /* access modifiers changed from: protected */
    public void clearDrawInfo() {
        List<nexDrawInfo> list = this.mTopEffectInfo;
        if (list != null) {
            list.clear();
        }
        List<nexDrawInfo> list2 = this.mSubEffectInfo;
        if (list2 != null) {
            list2.clear();
        }
        for (nexClip drawInfos : this.mPrimaryItems) {
            drawInfos.getDrawInfos().clear();
        }
    }

    public void clearFaceDetectInfo() {
        int size = this.mPrimaryItems.size();
        for (int i = 0; i < size; i++) {
            nexClip nexclip = this.mPrimaryItems.get(i);
            if (nexclip.getClipType() == 1) {
                nexclip.resetFaceDetectProcessed();
            }
        }
    }

    public void clearOverlay() {
        this.mOverlayItems.clear();
    }

    public nexAudioItem findAudioItem(int i) {
        for (nexAudioItem next : this.mSecondaryItems) {
            if (next.getId() == i) {
                return next;
            }
        }
        return null;
    }

    public nexAudioItem getAudioItem(int i) {
        return this.mSecondaryItems.get(i);
    }

    public List<nexAudioItem> getAudioItems() {
        if (this.m_externalView_secondaryItems == null) {
            this.m_externalView_secondaryItems = Collections.unmodifiableList(this.mSecondaryItems);
        }
        return this.m_externalView_secondaryItems;
    }

    public float getBGMMasterVolumeScale() {
        return this.mBGMVolumeScale;
    }

    public nexClip getBackgroundMusic() {
        return this.mBackGroundMusic;
    }

    public String getBackgroundMusicPath() {
        nexClip nexclip = this.mBackGroundMusic;
        if (nexclip != null) {
            return nexclip.getPath();
        }
        return null;
    }

    public nexClip getClip(int i, boolean z) {
        return z ? this.mPrimaryItems.get(i) : this.mSecondaryItems.get(i).mClip;
    }

    public int[] getClipDurationTimeGuideLine(int i) {
        int clipTimeGuideLine = getClipTimeGuideLine(i);
        int i2 = 10000;
        if (this.mPrimaryItems.get(i).getClipType() == 4) {
            i2 = this.mPrimaryItems.get(i).getProjectDuration();
            if (clipTimeGuideLine > i2) {
                return new int[]{0, 0};
            }
        } else if (clipTimeGuideLine > 10000) {
            return new int[]{clipTimeGuideLine, clipTimeGuideLine + 1000};
        }
        return new int[]{clipTimeGuideLine, i2};
    }

    public int getClipMaxSpeedControlList(int i) {
        int i2 = 0;
        if (this.mPrimaryItems.get(i).getClipType() != 4) {
            return 0;
        }
        int totalTime = this.mPrimaryItems.get(i).getTotalTime();
        if (!(this.mPrimaryItems.get(i).getVideoClipEdit().mTrimStartDuration == 0 && this.mPrimaryItems.get(i).getVideoClipEdit().mTrimEndDuration == 0)) {
            totalTime = (this.mPrimaryItems.get(i).getTotalTime() - this.mPrimaryItems.get(i).getVideoClipEdit().mTrimStartDuration) - this.mPrimaryItems.get(i).getVideoClipEdit().mTrimEndDuration;
        }
        int i3 = i - 1;
        int i4 = i + 1;
        int projectEndTime = (i3 >= 0 ? this.mPrimaryItems.get(i3).getProjectEndTime() - this.mPrimaryItems.get(i).getProjectStartTime() : 0) + (i4 < this.mPrimaryItems.size() ? this.mPrimaryItems.get(i).getProjectEndTime() - this.mPrimaryItems.get(i4).getProjectStartTime() : 0) + 500;
        int[] iArr = {13, 25, 50, 75, 100, BaiduSceneResult.TRAVEL_OTHER, 150, 175, 200, 400};
        int i5 = 0;
        while (i2 < iArr.length && projectEndTime <= Math.round((float) ((totalTime * 100) / iArr[i2]))) {
            i5 = iArr[i2];
            i2++;
        }
        return i5;
    }

    public int getClipPosition(int i) {
        for (int i2 = 0; i2 < getTotalClipCount(true); i2++) {
            if (this.mPrimaryItems.get(i2).getProjectStartTime() <= i && i < this.mPrimaryItems.get(i2).getProjectEndTime()) {
                return i2;
            }
        }
        return -1;
    }

    public int[] getClipPositionTime(int i) {
        updateProject();
        return new int[]{this.mPrimaryItems.get(i).getProjectStartTime(), this.mPrimaryItems.get(i).getProjectEndTime()};
    }

    public String getEndingTitle() {
        return this.mEndingTitle;
    }

    /* access modifiers changed from: package-private */
    public int getId() {
        return this.mId;
    }

    /* access modifiers changed from: package-private */
    public int getLastClipIndexWithEffectID(int i, int i2) {
        if ((i2 & nexEngine.ExportHEVCMainTierLevel62) == 16777216) {
            return i;
        }
        while (i < getTotalClipCount(true)) {
            if ((getClip(i, true).getTemplateEffectID() & -251658241) != i2) {
                return i - 1;
            }
            i++;
        }
        return getTotalClipCount(true) - 1;
    }

    public nexClip getLastPrimaryClip() {
        List<nexClip> list = this.mPrimaryItems;
        return list.get(list.size() - 1);
    }

    public String getLetterboxEffect() {
        return this.mLetterbox;
    }

    public int getManualVolumeControl() {
        return this.mManualVolCtl;
    }

    public nexClip getNextClip(nexClip nexclip) {
        int indexOf = this.mPrimaryItems.indexOf(nexclip);
        if (indexOf == this.mPrimaryItems.size() || indexOf == -1) {
            return null;
        }
        return this.mPrimaryItems.get(indexOf);
    }

    public String getOpeningTitle() {
        return this.mOpeningTitle;
    }

    public nexOverlayItem getOverlay(int i) {
        for (nexOverlayItem next : this.mOverlayItems) {
            if (next.getId() == i) {
                return next;
            }
        }
        return null;
    }

    public List<nexOverlayItem> getOverlayItems() {
        if (this.m_externalView_overlayItems == null) {
            this.m_externalView_overlayItems = Collections.unmodifiableList(this.mOverlayItems);
        }
        return this.m_externalView_overlayItems;
    }

    public List<nexClip> getPrimaryItems() {
        if (this.m_externalView_primaryItems == null) {
            this.m_externalView_primaryItems = Collections.unmodifiableList(this.mPrimaryItems);
        }
        return this.m_externalView_primaryItems;
    }

    public int getProjectAudioFadeInTime() {
        return this.mAudioFadeInTime;
    }

    public int getProjectAudioFadeOutTime() {
        return this.mAudioFadeOutTime;
    }

    public int getProjectTime2ClipTimePosition(int i, int i2) {
        if (i < 0 || i >= this.mPrimaryItems.size()) {
            return -1;
        }
        int projectStartTime = this.mPrimaryItems.get(i).getProjectStartTime();
        int projectEndTime = this.mPrimaryItems.get(i).getProjectEndTime();
        if (projectStartTime > i2 || i2 >= projectEndTime) {
            return -1;
        }
        int i3 = i2 - projectStartTime;
        if (this.mPrimaryItems.get(i).getClipType() != 4) {
            return i3;
        }
        int startTrimTime = this.mPrimaryItems.get(i).getVideoClipEdit().getStartTrimTime();
        int speedControl = this.mPrimaryItems.get(i).getVideoClipEdit().getSpeedControl();
        if (speedControl != 100) {
            if (speedControl == 12) {
                speedControl = 25;
                i3 /= 2;
            }
            i3 = Math.round((float) ((i3 * speedControl) / 100));
        }
        return i3 + startTrimTime;
    }

    public int getProjectVolume() {
        return this.mProjectVolume;
    }

    /* access modifiers changed from: package-private */
    public nexSaveDataFormat.nexProjectOf getSaveData() {
        nexSaveDataFormat.nexProjectOf nexprojectof = new nexSaveDataFormat.nexProjectOf();
        nexprojectof.mProjectVolume = this.mProjectVolume;
        nexprojectof.mManualVolCtl = this.mManualVolCtl;
        nexprojectof.mAudioFadeInTime = this.mAudioFadeInTime;
        nexprojectof.mAudioFadeOutTime = this.mAudioFadeOutTime;
        nexprojectof.mOpeningTitle = this.mOpeningTitle;
        nexprojectof.mEndingTitle = this.mEndingTitle;
        nexprojectof.mBGMVolumeScale = this.mBGMVolumeScale;
        nexprojectof.mUseThemeMusic2BGM = this.mUseThemeMusic2BGM;
        nexprojectof.mLoopBGM = this.mLoopBGM;
        nexprojectof.mStartTimeBGM = this.mStartTimeBGM;
        nexClip nexclip = this.mBackGroundMusic;
        if (nexclip == null) {
            nexprojectof.mBackGroundMusic = null;
        } else {
            nexprojectof.mBackGroundMusic = nexclip.getSaveData();
        }
        nexprojectof.mBGMTrimStartTime = this.mBGMTrimStartTime;
        nexprojectof.mBGMTrimEndTime = this.mBGMTrimEndTime;
        nexprojectof.mTemplateApplyMode = this.mTemplateApplyMode;
        nexprojectof.mTemplateOverlappedTransition = this.mTemplateOverlappedTransition;
        if (this.mPrimaryItems.size() == 0) {
            nexprojectof.mPrimaryItems = null;
        } else {
            nexprojectof.mPrimaryItems = new ArrayList();
            for (nexClip next : this.mPrimaryItems) {
                if (!next.isMotionTrackedVideo()) {
                    nexprojectof.mPrimaryItems.add(next.getSaveData());
                }
            }
        }
        if (this.mSecondaryItems.size() == 0) {
            nexprojectof.mSecondaryItems = null;
        } else {
            nexprojectof.mSecondaryItems = new ArrayList();
            for (nexAudioItem saveData : this.mSecondaryItems) {
                nexprojectof.mSecondaryItems.add(saveData.getSaveData());
            }
        }
        if (this.mTopEffectInfo.size() == 0) {
            nexprojectof.mTopEffectInfo = null;
        } else {
            nexprojectof.mTopEffectInfo = this.mTopEffectInfo;
        }
        if (this.mSubEffectInfo.size() == 0) {
            nexprojectof.mSubEffectInfo = null;
        } else {
            nexprojectof.mSubEffectInfo = this.mSubEffectInfo;
        }
        return nexprojectof;
    }

    @Deprecated
    public List<nexClip> getSecondaryItems() {
        return null;
    }

    public boolean getTemplageOverlappedTransitionMode() {
        return this.mTemplateOverlappedTransition;
    }

    public int getTemplateApplyMode() {
        return this.mTemplateApplyMode;
    }

    public String getThemeId() {
        return this.mThemeId;
    }

    public List<nexDrawInfo> getTopDrawInfo() {
        return this.mTopEffectInfo;
    }

    public int getTotalClipCount(boolean z) {
        return z ? this.mPrimaryItems.size() : this.mSecondaryItems.size();
    }

    public int getTotalTime() {
        if (this.mTemplateApplyMode == 3) {
            int i = 0;
            for (int i2 = 0; i2 < this.mPrimaryItems.size(); i2++) {
                nexClip nexclip = this.mPrimaryItems.get(i2);
                if (i < nexclip.mEndTime) {
                    i = nexclip.mEndTime;
                }
            }
            return i;
        }
        int size = this.mPrimaryItems.size();
        if (size == 0) {
            return 0;
        }
        updateProject();
        return this.mPrimaryItems.get(size - 1).mEndTime;
    }

    @Deprecated
    public int getTotalVisualClipCount() {
        int size = this.mPrimaryItems.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            nexClip nexclip = this.mPrimaryItems.get(i2);
            if (nexclip.getClipType() == 4) {
                int trimCount = nexclip.getVideoClipEdit().getTrimCount();
                if (trimCount == 0) {
                    trimCount = 1;
                }
                i += trimCount;
            } else if (nexclip.getClipType() == 1) {
                i++;
            }
        }
        return i;
    }

    public int[] getTransitionDurationTimeGuideLine(int i, int i2) {
        int transitionTimeGuideLine = getTransitionTimeGuideLine(i, i2);
        if (500 > transitionTimeGuideLine) {
            return new int[]{0, 0};
        }
        return new int[]{500, transitionTimeGuideLine};
    }

    public int indexOf(nexClip nexclip) {
        return this.mPrimaryItems.indexOf(nexclip);
    }

    @Deprecated
    public Intent makeKineMasterIntent() {
        updateProject();
        String str = TAG;
        Log.d(str, "[" + this.mId + "] make intent start");
        com.nexstreaming.kminternal.kinemaster.editorwrapper.a aVar = new com.nexstreaming.kminternal.kinemaster.editorwrapper.a();
        for (nexClip next : this.mPrimaryItems) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            next.getCrop().getStartPositionRaw(rect);
            next.getCrop().getEndPositionRaw(rect2);
            int clipType = next.getClipType();
            if (clipType != 1) {
                if (clipType == 4) {
                    ((a.g) ((a.g) ((a.g) ((a.g) ((a.g) ((a.g) ((a.g) ((a.g) ((a.g) ((a.g) ((a.g) aVar.a().a(next.getPath()).e(next.getVideoClipEdit().getDuration())).a((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom, (float) rect2.left, (float) rect2.top, (float) rect2.right, (float) rect2.bottom).c(next.getVideoClipEdit().getSpeedControl()).a(next.getVideoClipEdit().getStartTrimTime()).d(next.getRotateDegree()).a(!next.getAudioOnOff())).g(next.getClipVolume())).h(next.getBrightness())).i(next.getContrast())).j(next.getSaturation())).d(next.getColorEffectID())).b(next.getClipEffect(true).getId())).c(next.getTransitionEffect(true).getId())).f(next.getTransitionEffect(true).getDuration())).b(next.getVignetteEffect())).a();
                }
            } else if (next.isSolid()) {
                ((a.e) ((a.e) ((a.e) ((a.e) ((a.e) ((a.e) ((a.e) ((a.e) ((a.e) ((a.e) aVar.c().a(next.getSolidColor()).e(next.getImageClipDuration())).h(next.getBrightness())).d(next.getRotateDegree())).i(next.getContrast())).j(next.getSaturation())).d(next.getColorEffectID())).b(next.getClipEffect(true).getId())).c(next.getTransitionEffect(true).getId())).f(next.getTransitionEffect(true).getDuration())).b(next.getVignetteEffect())).a();
            } else {
                ((a.b) ((a.b) ((a.b) ((a.b) ((a.b) ((a.b) ((a.b) ((a.b) ((a.b) ((a.b) aVar.b().a(next.getPath()).a((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom, (float) rect2.left, (float) rect2.top, (float) rect2.right, (float) rect2.bottom).e(next.getImageClipDuration())).h(next.getBrightness())).d(next.getRotateDegree())).i(next.getContrast())).j(next.getSaturation())).d(next.getColorEffectID())).b(next.getClipEffect(true).getId())).c(next.getTransitionEffect(true).getId())).f(next.getTransitionEffect(true).getDuration())).b(next.getVignetteEffect())).a();
            }
        }
        for (nexAudioItem next2 : this.mSecondaryItems) {
            aVar.d().a(next2.getClip().getPath()).c(next2.getStartTime()).d(next2.getEndTime()).b(false).a(false).a(next2.getStartTrimTime()).b(next2.getEndTrimTime()).c(!next2.getClip().getAudioOnOff()).e(next2.getClip().getClipVolume()).a();
        }
        if (this.mBackGroundMusic != null) {
            aVar.d().a(this.mBackGroundMusic.getPath()).c(this.mStartTimeBGM).d(getTotalTime()).b(true).a(this.mLoopBGM).a(this.mBGMTrimStartTime).b(this.mBGMTrimEndTime).e((int) (getBGMMasterVolumeScale() * 200.0f)).a();
        }
        for (nexOverlayItem next3 : this.mOverlayItems) {
            if (next3.getOverlayImage() instanceof nexOverlayKineMasterText) {
                nexOverlayKineMasterText nexoverlaykinemastertext = (nexOverlayKineMasterText) next3.getOverlayImage();
                ((a.f) ((a.f) ((a.f) ((a.f) ((a.f) ((a.f) ((a.f) ((a.f) aVar.e().d(nexoverlaykinemastertext.getText()).a(nexoverlaykinemastertext.getTextSize()).e(nexoverlaykinemastertext.getTextColor()).g(nexoverlaykinemastertext.getGlowColor(false)).h(nexoverlaykinemastertext.getOutlineColor(false)).f(nexoverlaykinemastertext.getShadowColor(false)).e(nexoverlaykinemastertext.getFontId()).a(next3.getStartTime())).b(next3.getEndTime())).a().a(0.0f).a((float) ((next3.getPositionX() * 1280) / nexApplicationConfig.getAspectProfile().getWidth()), (float) ((next3.getPositionY() * 720) / nexApplicationConfig.getAspectProfile().getHeight())).c(next3.getAlpha()).b((float) next3.getRotate()).d(next3.getScaledX()).a()).a(next3.getLayerExpression().getNames(0))).c(next3.getLayerExpressionDuration())).b(next3.getLayerExpression().getNames(1))).d(next3.getLayerExpressionDuration())).c(next3.getLayerExpression().getNames(2))).b();
            }
        }
        return aVar.f();
    }

    public void move(int i, nexClip nexclip) {
        int indexOf = this.mPrimaryItems.indexOf(nexclip);
        if (indexOf >= 0 && i != indexOf && this.mPrimaryItems.remove(nexclip)) {
            updateTimeLine(false);
            this.mPrimaryItems.add(i, nexclip);
        }
    }

    public int remove(nexClip nexclip) {
        int isPrimaryItem = isPrimaryItem(nexclip);
        if (isPrimaryItem < 0) {
            return isPrimaryItem;
        }
        if (isPrimaryItem == 1) {
            this.mPrimaryItems.remove(nexclip);
        } else {
            int i = 0;
            while (true) {
                if (i >= this.mSecondaryItems.size()) {
                    break;
                } else if (this.mSecondaryItems.get(i).mClip == nexclip) {
                    this.mSecondaryItems.remove(i);
                    break;
                } else {
                    i++;
                }
            }
        }
        nexclip.setAttachmentState(false, (b) null);
        updateTimeLine(false);
        return 0;
    }

    public boolean removeOverlay(int i) {
        for (nexOverlayItem next : this.mOverlayItems) {
            if (next.getId() == i) {
                this.mOverlayItems.remove(next);
                return true;
            }
        }
        return false;
    }

    public String saveToString() {
        Gson gson = new Gson();
        nexSaveDataFormat nexsavedataformat = new nexSaveDataFormat();
        nexsavedataformat.project = getSaveData();
        return gson.toJson((Object) nexsavedataformat);
    }

    public void setBGMMasterVolumeScale(float f) {
        if (f < 0.0f) {
            this.mBGMVolumeScale = 0.0f;
        } else if (f > 1.0f) {
            this.mBGMVolumeScale = 1.0f;
        } else {
            this.mBGMVolumeScale = f;
        }
        this.mNeedLoadList = true;
    }

    public void setBackgroundConfig(int i, boolean z, boolean z2) {
        this.mUseThemeMusic2BGM = z;
        this.mLoopBGM = z2;
        this.mStartTimeBGM = i;
        this.mNeedLoadList = true;
    }

    public boolean setBackgroundMusicPath(String str) {
        if (str == null) {
            if (this.mBackGroundMusic != null) {
                this.mNeedLoadList = true;
            }
            this.mBackGroundMusic = null;
            return true;
        }
        nexClip supportedClip = nexClip.getSupportedClip(str);
        if (supportedClip == null || supportedClip.getClipType() != 3) {
            return false;
        }
        this.mBackGroundMusic = supportedClip;
        this.mNeedLoadList = true;
        return true;
    }

    public void setBackgroundTrim(int i, int i2) {
        this.mBGMTrimStartTime = i;
        this.mBGMTrimEndTime = i2;
        this.mNeedLoadList = true;
    }

    public void setLetterboxEffect(String str) {
        this.mLetterbox = str;
    }

    public void setManualVolumeControl(int i) {
        this.mManualVolCtl = i;
    }

    public void setProjectAudioFadeInTime(int i) {
        this.mAudioFadeInTime = i;
        this.mNeedLoadList = true;
    }

    public void setProjectAudioFadeOutTime(int i) {
        this.mAudioFadeOutTime = i;
        this.mNeedLoadList = true;
    }

    public void setProjectVolume(int i) {
        this.mProjectVolume = i;
    }

    public void setTemplageOverlappedTransitionMode(boolean z) {
        this.mTemplateOverlappedTransition = z;
    }

    public void setTemplateApplyMode(int i) {
        this.mTemplateApplyMode = i;
    }

    @Deprecated
    public void setThemeId(String str) {
        this.mEmptyTheme = true;
        this.mThemeId = null;
    }

    public void setTitle(String str, String str2) {
        this.mOpeningTitle = str;
        this.mEndingTitle = str2;
        updateTimeLine(false);
        this.mNeedLoadList = true;
    }

    public void swap(nexClip nexclip, nexClip nexclip2) {
        int indexOf = this.mPrimaryItems.indexOf(nexclip);
        int indexOf2 = this.mPrimaryItems.indexOf(nexclip2);
        if (indexOf >= 0 && indexOf2 >= 0) {
            Collections.swap(this.mPrimaryItems, indexOf, indexOf2);
        }
    }

    public boolean updateProject() {
        int i;
        int i2;
        int totalClipCount = getTotalClipCount(true);
        int i3 = 0;
        if (!this.mClipTimeUpdated) {
            return false;
        }
        this.mClipTimeUpdated = false;
        if (totalClipCount == 0) {
            return false;
        }
        if (getTemplateApplyMode() == 3) {
            return true;
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < totalClipCount; i7++) {
            nexClip clip = getClip(i7, true);
            clip.mStartTime = i4;
            int clipType = clip.getClipType();
            if (clipType == 1) {
                i5 = clip.getImageClipDuration();
            } else if (clipType == 4) {
                i5 = !clip.getTemplateOverlappedTransition() ? clip.getVideoClipEdit().getDuration() + clip.getTransitionEffect().getDuration() : clip.getVideoClipEdit().getDuration();
            }
            int i8 = i4 + i5;
            int duration = (clip.getTransitionEffect(true).getType() == 0 || clip.getTransitionEffect(true).getOverlap() == 0) ? 0 : (clip.getTransitionEffect(true).getDuration() * 100) / clip.getTransitionEffect(true).getOverlap();
            if (getTemplateApplyMode() == 2) {
                clip.mTitleEffectStartTime = clip.getClipEffect().getShowStartTime();
                clip.mTitleEffectEndTime = clip.getClipEffect().getShowEndTime();
                i2 = i8 - duration;
                i = clip.getTransitionEffect(true).getDuration();
            } else {
                clip.mTitleEffectStartTime = i6;
                i2 = i8 - duration;
                clip.mTitleEffectEndTime = i2;
                i = clip.getTransitionEffect(true).getDuration();
            }
            i6 = i2 + i;
            clip.mEndTime = i8;
            i4 = i8 - duration;
        }
        if (getTemplateApplyMode() == 2) {
            int i9 = 0;
            while (i3 < totalClipCount) {
                nexClip clip2 = getClip(i3, true);
                if (clip2 == null) {
                    i3++;
                } else {
                    int lastClipIndexWithEffectID = getLastClipIndexWithEffectID(i3, clip2.getTemplateEffectID());
                    i9 = adjustmentEffectTime(i3, lastClipIndexWithEffectID, i9, clip2.getTemplateEffectID());
                    i3 = lastClipIndexWithEffectID + 1;
                }
            }
        }
        return true;
    }
}
