package com.nexstreaming.nexeditorsdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;
import com.nexstreaming.app.common.task.ResultTask;
import com.nexstreaming.app.common.task.Task;
import com.nexstreaming.app.common.util.FileType;
import com.nexstreaming.app.common.util.e;
import com.nexstreaming.kminternal.kinemaster.config.EditorGlobal;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.kminternal.kinemaster.mediainfo.MediaInfo;
import com.nexstreaming.kminternal.kinemaster.mediainfo.b;
import com.nexstreaming.kminternal.kinemaster.mediainfo.h;
import com.nexstreaming.kminternal.kinemaster.mediainfo.nexCodecType;
import com.nexstreaming.kminternal.nexvideoeditor.NexEditor;
import com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader;
import com.nexstreaming.kminternal.nexvideoeditor.d;
import com.nexstreaming.nexeditorsdk.exception.ClipIsNotVideoException;
import com.nexstreaming.nexeditorsdk.exception.ProjectNotAttachedException;
import com.nexstreaming.nexeditorsdk.nexSaveDataFormat;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class nexClip implements Cloneable {
    public static final int AVC_Profile_Baseline = 66;
    public static final int AVC_Profile_Extended = 88;
    public static final int AVC_Profile_High = 100;
    public static final int AVC_Profile_High10 = 100;
    public static final int AVC_Profile_High422 = 122;
    public static final int AVC_Profile_High444 = 244;
    public static final int AVC_Profile_Main = 77;
    public static final int AVC_Profile_Unknown = 0;
    public static final int HDR_TYPE_10HLG = 18;
    public static final int HDR_TYPE_10PQ = 16;
    private static final String TAG = "nexClip";
    public static final int kCLIP_Supported = 0;
    public static final int kCLIP_TYPE_AUDIO = 3;
    public static final int kCLIP_TYPE_IMAGE = 1;
    public static final int kCLIP_TYPE_NONE = 0;
    public static final int kCLIP_TYPE_VIDEO = 4;
    public static final int kCLIP_VIDEORENDERMODE_360VIDE = 1;
    public static final int kCLIP_VIDEORENDERMODE_NORMAL = 0;
    public static final int kClip_NotSupported = 12;
    public static final int kClip_NotSupported_AudioCodec = 2;
    public static final int kClip_NotSupported_AudioProfile = 3;
    public static final int kClip_NotSupported_Container = 4;
    public static final int kClip_NotSupported_DurationTooShort = 6;
    public static final int kClip_NotSupported_ResolutionTooHigh = 5;
    public static final int kClip_NotSupported_ResolutionTooLow = 7;
    public static final int kClip_NotSupported_VideoCodec = 9;
    public static final int kClip_NotSupported_VideoFPS = 10;
    public static final int kClip_NotSupported_VideoLevel = 11;
    public static final int kClip_NotSupported_VideoProfile = 8;
    public static final int kClip_Rotate_0 = 0;
    public static final int kClip_Rotate_180 = 180;
    public static final int kClip_Rotate_270 = 270;
    public static final int kClip_Rotate_90 = 90;
    public static final int kClip_Supported_NeedFPSTranscoding = 14;
    public static final int kClip_Supported_NeedResolutionTranscoding = 13;
    public static final int kClip_Supported_Unknown = 1;
    private static long sVideoClipDetailThumbnailsDiskLimitSize = 100000000;
    private int count = 0;
    private int index = 0;
    private boolean isMotionTrackedVideo = false;
    private int mAVSyncAudioStartTime = 0;
    private nexAudioEdit mAudioEdit;
    private nexAudioEnvelop mAudioEnvelop = null;
    private boolean mAudioOnOff = true;
    private nexClipEffect mClipEffect = null;
    private String mCollageDrawInfoID;
    private nexColorEffect mColorEffect = nexColorEffect.NONE;
    private nexCrop mCrop;
    private int mCustomLUT_A = 0;
    private int mCustomLUT_B = 0;
    private int mCustomLUT_Power = nexCrop.ABSTRACT_DIMENSION;
    private List<nexDrawInfo> mDrawInfos = new ArrayList();
    protected int mDuration = nexProject.kAutoThemeClipDuration;
    protected int mEndTime = 0;
    private int mFaceDetected = 0;
    private Rect mFaceRect = new Rect();
    private boolean mFacedetectProcessed = false;
    private ClipInfo mInfo;
    private boolean mIsAssetResource = false;
    private boolean mMediaInfoUseCache = true;
    private b mObserver;
    private boolean mOverlappedTransition = true;
    private String mPath = null;
    private boolean mProjectAttachment;
    boolean mPropertySlowVideoMode;
    private int mRotate = 0;
    private transient WeakReference<Bitmap> mSingleThumbnail = null;
    protected int mStartTime = 0;
    private int mTemplateAudioPos = 0;
    private int mTemplateEffectID = 0;
    /* access modifiers changed from: private */
    public h mThumbnails = null;
    protected int mTitleEffectEndTime;
    protected int mTitleEffectStartTime;
    private String mTransCodingPath = null;
    private nexTransitionEffect mTransitionEffect = null;
    private nexVideoClipEdit mVideoEdit;
    private boolean mVignette;
    private int m_BGMVolume = 100;
    private int m_Brightness;
    private int m_ClipVolume = 100;
    private int m_Contrast;
    private int m_Saturation;
    /* access modifiers changed from: private */
    public boolean m_getThumbnailsFailed = false;
    /* access modifiers changed from: private */
    public transient boolean m_gettingPcmData;
    /* access modifiers changed from: private */
    public boolean m_gettingThumbnails = false;
    private MediaInfo mediainfo = null;
    private boolean misMustDownSize;

    protected static class ClipInfo implements Cloneable {
        public int mAudioBitrate;
        public String mAudioCodecType;
        public int mAudioTotalTime;
        public int mClipType = 0;
        public int mDisplayHeight;
        public int mDisplayWidth;
        public boolean mExistAudio;
        public boolean mExistVideo;
        public int mFramesPerSecond;
        public int mH264Level;
        public int mH264Profile;
        public int mHeight;
        public String mMimeType;
        public int mRotateDegreeInMeta;
        public int mSeekPointCount;
        public int mSuppertedResult = 1;
        public int mTotalTime;
        public int mVideoBitrate;
        public String mVideoCodecType;
        public int mVideoHDRType;
        public int mVideoRenderMode;
        public int mVideoTotalTime;
        public byte[] mVideoUUID;
        public int mWidth;

        protected ClipInfo() {
        }

        protected static ClipInfo clone(ClipInfo clipInfo) {
            ClipInfo clipInfo2;
            try {
                clipInfo2 = (ClipInfo) clipInfo.clone();
                try {
                    clipInfo2.mMimeType = clipInfo.mMimeType;
                    clipInfo2.mVideoCodecType = clipInfo.mVideoCodecType;
                    clipInfo2.mAudioCodecType = clipInfo.mAudioCodecType;
                    if (clipInfo.mVideoUUID == null) {
                        clipInfo2.mVideoUUID = null;
                    } else {
                        clipInfo2.mVideoUUID = new byte[clipInfo.mVideoUUID.length];
                        System.arraycopy(clipInfo.mVideoUUID, 0, clipInfo2.mVideoUUID, 0, clipInfo2.mVideoUUID.length);
                    }
                } catch (CloneNotSupportedException e) {
                    e = e;
                    e.printStackTrace();
                    return clipInfo2;
                }
            } catch (CloneNotSupportedException e2) {
                e = e2;
                clipInfo2 = null;
                e.printStackTrace();
                return clipInfo2;
            }
            return clipInfo2;
        }
    }

    public static abstract class OnGetAudioPcmLevelsResultListener {
        public abstract void onGetAudioPcmLevelsResult(byte[] bArr);
    }

    public static abstract class OnGetVideoClipDetailThumbnailsListener {
        public static int kEvent_Completed = 1;
        public static int kEvent_Fail = -1;
        public static int kEvent_Ok = 0;
        public static int kEvent_UserCancel = -3;
        public static int kEvent_systemError = -2;

        public abstract void onGetDetailThumbnailResult(int i, Bitmap bitmap, int i2, int i3, int i4);
    }

    @Deprecated
    public static abstract class OnGetVideoClipIDR2YOnlyThumbnailsListener {
        public static int kEvent_Completed = 1;
        public static int kEvent_Fail = -1;
        public static int kEvent_Ok = 0;
        public static int kEvent_UserCancel = -3;
        public static int kEvent_systemError = -2;

        public abstract void onGetVideoClipIDR2YOnlyThumbnailsResult(int i, byte[] bArr, int i2, int i3, int i4);
    }

    public static abstract class OnLoadVideoClipThumbnailListener {
        public static int kEvent_Ok = 0;
        public static int kEvent_Running = 2;
        public static int kEvent_loadCompleted = 1;
        public static int kEvent_loadFail = -1;
        public static int kEvent_mustRetry = 3;
        public static int kEvent_systemError = -2;

        public abstract void onLoadThumbnailResult(int i);
    }

    private nexClip() {
    }

    nexClip(b bVar, nexSaveDataFormat.nexClipOf nexclipof) {
        this.m_BGMVolume = nexclipof.m_BGMVolume;
        this.mPath = nexclipof.mPath;
        this.mTransCodingPath = nexclipof.mTransCodingPath;
        this.mProjectAttachment = true;
        if (nexclipof.mClipEffect == null) {
            this.mClipEffect = null;
        } else {
            this.mClipEffect = new nexClipEffect(nexclipof.mClipEffect);
        }
        if (nexclipof.mTransitionEffect == null) {
            this.mTransitionEffect = null;
        } else {
            this.mTransitionEffect = new nexTransitionEffect(bVar, nexclipof.mTransitionEffect);
        }
        this.misMustDownSize = nexclipof.misMustDownSize;
        this.mObserver = bVar;
        this.mAudioOnOff = nexclipof.mAudioOnOff;
        this.m_Brightness = nexclipof.m_Brightness;
        this.m_Contrast = nexclipof.m_Contrast;
        this.m_Saturation = nexclipof.m_Saturation;
        this.mVignette = nexclipof.mVignette;
        this.mFacedetectProcessed = nexclipof.mFacedetectProcessed;
        this.mFaceDetected = nexclipof.mFaceDetected;
        this.mFaceRect = nexclipof.mFaceRect;
        if (nexclipof.mCrop == null) {
            this.mCrop = null;
        } else {
            this.mCrop = new nexCrop(this.mPath, nexclipof.mCrop);
        }
        this.mTitleEffectStartTime = nexclipof.mTitleEffectStartTime;
        this.mTitleEffectStartTime = nexclipof.mTitleEffectEndTime;
        if (nexclipof.mAudioEnvelop == null) {
            this.mAudioEnvelop = null;
        } else {
            this.mAudioEnvelop = new nexAudioEnvelop(nexclipof.mAudioEnvelop);
        }
        this.mTemplateEffectID = nexclipof.mTemplateEffectID;
        this.mCollageDrawInfoID = nexclipof.mCollageDrawInfoID;
        this.mTemplateAudioPos = nexclipof.mTemplateAudioPos;
        this.mDrawInfos = nexclipof.mDrawInfos;
        this.mOverlappedTransition = nexclipof.mOverlappedTransition;
        this.mMediaInfoUseCache = nexclipof.mMediaInfoUseCache;
        this.mStartTime = nexclipof.mStartTime;
        this.mEndTime = nexclipof.mEndTime;
        this.mDuration = nexclipof.mDuration;
        this.mInfo = nexclipof.mInfo;
        this.mCustomLUT_A = nexclipof.mCustomLUT_A;
        this.mCustomLUT_B = nexclipof.mCustomLUT_B;
        this.mCustomLUT_Power = nexclipof.mCustomLUT_Power;
        this.mRotate = nexclipof.mRotate;
        if (nexclipof.mColorEffect == null) {
            this.mColorEffect = null;
        } else {
            this.mColorEffect = new nexColorEffect(nexclipof.mColorEffect);
        }
        this.mPropertySlowVideoMode = nexclipof.mPropertySlowVideoMode;
        this.mAVSyncAudioStartTime = nexclipof.mAVSyncAudioStartTime;
        this.m_ClipVolume = nexclipof.m_ClipVolume;
        this.m_BGMVolume = nexclipof.m_BGMVolume;
        if (nexclipof.mVideoEdit == null) {
            this.mVideoEdit = null;
        } else {
            this.mVideoEdit = new nexVideoClipEdit(this, nexclipof.mVideoEdit);
        }
        if (nexclipof.mAudioEdit == null) {
            this.mAudioEdit = null;
        } else {
            this.mAudioEdit = new nexAudioEdit(this, nexclipof.mAudioEdit);
        }
    }

    protected nexClip(nexClip nexclip) {
        this.mPath = nexclip.mPath;
        this.mTransCodingPath = nexclip.mTransCodingPath;
        this.misMustDownSize = nexclip.misMustDownSize;
        this.mMediaInfoUseCache = nexclip.mMediaInfoUseCache;
        this.mPropertySlowVideoMode = nexclip.mPropertySlowVideoMode;
        if (nexclip.mInfo != null) {
            if (this.mInfo == null) {
                this.mInfo = new ClipInfo();
            }
            this.mInfo.mMimeType = nexclip.mInfo.mMimeType;
            this.mInfo.mClipType = nexclip.mInfo.mClipType;
            this.mInfo.mWidth = nexclip.mInfo.mWidth;
            this.mInfo.mHeight = nexclip.mInfo.mHeight;
            this.mInfo.mDisplayWidth = nexclip.mInfo.mDisplayWidth;
            this.mInfo.mDisplayHeight = nexclip.mInfo.mDisplayHeight;
            this.mInfo.mExistVideo = nexclip.mInfo.mExistVideo;
            this.mInfo.mExistAudio = nexclip.mInfo.mExistAudio;
            this.mInfo.mTotalTime = nexclip.mInfo.mTotalTime;
            this.mInfo.mFramesPerSecond = nexclip.mInfo.mFramesPerSecond;
            this.mInfo.mRotateDegreeInMeta = nexclip.mInfo.mRotateDegreeInMeta;
            this.mInfo.mH264Profile = nexclip.mInfo.mH264Profile;
            this.mInfo.mH264Level = nexclip.mInfo.mH264Level;
            this.mInfo.mSuppertedResult = nexclip.mInfo.mSuppertedResult;
            this.mInfo.mVideoBitrate = nexclip.mInfo.mVideoBitrate;
            this.mInfo.mAudioBitrate = nexclip.mInfo.mAudioBitrate;
            this.mInfo.mVideoTotalTime = nexclip.mInfo.mVideoTotalTime;
            this.mInfo.mAudioTotalTime = nexclip.mInfo.mAudioTotalTime;
            this.mInfo.mSeekPointCount = nexclip.mInfo.mSeekPointCount;
            this.mInfo.mVideoRenderMode = nexclip.mInfo.mVideoRenderMode;
            this.mInfo.mVideoHDRType = nexclip.mInfo.mVideoHDRType;
            if (nexclip.mInfo.mAudioCodecType != null) {
                this.mInfo.mAudioCodecType = new String(nexclip.mInfo.mAudioCodecType);
            }
            if (nexclip.mInfo.mVideoCodecType != null) {
                this.mInfo.mVideoCodecType = new String(nexclip.mInfo.mVideoCodecType);
            }
            if (nexclip.mInfo.mVideoUUID != null) {
                this.mInfo.mVideoUUID = new byte[nexclip.mInfo.mVideoUUID.length];
                System.arraycopy(nexclip.mInfo.mVideoUUID, 0, this.mInfo.mVideoUUID, 0, this.mInfo.mVideoUUID.length);
            }
        }
        MediaInfo mediaInfo = nexclip.mediainfo;
        if (mediaInfo != null) {
            this.mediainfo = mediaInfo;
        }
    }

    public nexClip(String str) {
        if (str.startsWith("@assetItem:")) {
            this.mPath = str.substring(11);
            this.mTransCodingPath = str;
            this.mMediaInfoUseCache = false;
            this.mInfo = new ClipInfo();
            this.mInfo.mClipType = 1;
            Rect assetPackageMediaOptions = nexAssetPackageManager.getAssetPackageMediaOptions(this.mPath);
            this.mInfo.mWidth = assetPackageMediaOptions.width();
            this.mInfo.mHeight = assetPackageMediaOptions.height();
            return;
        }
        this.mPath = str;
        this.mTransCodingPath = transCodingPath(str);
        String str2 = this.mTransCodingPath;
        if (str2 == null || !str2.startsWith("@assetItem:")) {
            this.mMediaInfoUseCache = true;
            setMediaInfo(MediaInfo.a(getRealPath(), this.mMediaInfoUseCache));
            this.mInfo = resolveClip(getRealPath(), this.mMediaInfoUseCache, getMediaInfo(), false);
            return;
        }
        this.mMediaInfoUseCache = false;
        this.mInfo = new ClipInfo();
        this.mInfo.mClipType = 1;
        Rect assetPackageMediaOptions2 = nexAssetPackageManager.getAssetPackageMediaOptions(this.mPath);
        this.mInfo.mWidth = assetPackageMediaOptions2.width();
        this.mInfo.mHeight = assetPackageMediaOptions2.height();
    }

    nexClip(String str, String str2) {
        this.mPath = str;
        this.mTransCodingPath = str2;
        this.mMediaInfoUseCache = false;
        this.mInfo = new ClipInfo();
        this.mInfo.mClipType = 1;
        Rect assetPackageMediaOptions = nexAssetPackageManager.getAssetPackageMediaOptions(this.mPath);
        this.mInfo.mWidth = assetPackageMediaOptions.width();
        this.mInfo.mHeight = assetPackageMediaOptions.height();
        Log.d(TAG, "@assetItem: w=" + this.mInfo.mWidth + ", h=" + this.mInfo.mHeight);
    }

    private nexClip(String str, String str2, ClipInfo clipInfo, boolean z, MediaInfo mediaInfo) {
        this.mPath = str;
        this.mInfo = clipInfo;
        if (str.compareTo(str2) != 0) {
            this.mTransCodingPath = str2;
        }
        this.mMediaInfoUseCache = z;
        if (mediaInfo != null) {
            setMediaInfo(mediaInfo);
        } else {
            setMediaInfo(MediaInfo.a(str2, this.mMediaInfoUseCache));
        }
    }

    public nexClip(String str, boolean z) {
        if (str.startsWith("@assetItem:")) {
            this.mPath = str.substring(11);
            this.mTransCodingPath = str;
            this.mMediaInfoUseCache = false;
            this.mInfo = new ClipInfo();
            this.mInfo.mClipType = 1;
            Rect assetPackageMediaOptions = nexAssetPackageManager.getAssetPackageMediaOptions(this.mPath);
            this.mInfo.mWidth = assetPackageMediaOptions.width();
            this.mInfo.mHeight = assetPackageMediaOptions.height();
            return;
        }
        this.mPath = str;
        this.mTransCodingPath = transCodingPath(str);
        String str2 = this.mTransCodingPath;
        if (str2 == null || !str2.startsWith("@assetItem:")) {
            this.mMediaInfoUseCache = true;
            setMediaInfo(MediaInfo.a(getRealPath(), this.mMediaInfoUseCache));
            this.mInfo = resolveClip(getRealPath(), this.mMediaInfoUseCache, getMediaInfo(), false);
            return;
        }
        this.mMediaInfoUseCache = false;
        this.mInfo = new ClipInfo();
        this.mInfo.mClipType = 1;
        Rect assetPackageMediaOptions2 = nexAssetPackageManager.getAssetPackageMediaOptions(this.mPath);
        this.mInfo.mWidth = assetPackageMediaOptions2.width();
        this.mInfo.mHeight = assetPackageMediaOptions2.height();
    }

    public static boolean cancelThumbnails() {
        return MediaInfo.a();
    }

    protected static nexClip clone(nexClip nexclip) {
        nexClip nexclip2;
        try {
            nexclip2 = (nexClip) nexclip.clone();
            try {
                if (nexclip.mClipEffect != null) {
                    nexclip2.mClipEffect = nexClipEffect.clone(nexclip.mClipEffect);
                }
                if (nexclip.mTransitionEffect != null) {
                    nexclip2.mTransitionEffect = nexTransitionEffect.clone(nexclip.mTransitionEffect);
                }
                if (nexclip.mCrop != null) {
                    nexclip2.mCrop = nexCrop.clone(nexclip.mCrop);
                }
                if (nexclip.mVideoEdit != null) {
                    nexclip2.mVideoEdit = nexVideoClipEdit.clone(nexclip.mVideoEdit);
                }
                if (nexclip.mColorEffect != null) {
                    nexclip2.mColorEffect = nexColorEffect.clone(nexclip.mColorEffect);
                }
                if (nexclip.mInfo != null) {
                    nexclip2.mInfo = ClipInfo.clone(nexclip.mInfo);
                }
                if (nexclip.mAudioEnvelop != null) {
                    nexclip2.mAudioEnvelop = nexAudioEnvelop.clone(nexclip.mAudioEnvelop);
                }
                if (nexclip.mAudioEdit != null) {
                    nexclip2.mAudioEdit = nexAudioEdit.clone(nexclip2, nexclip.mAudioEdit);
                }
                nexclip2.mTransCodingPath = nexclip.mTransCodingPath;
                nexclip2.mSingleThumbnail = nexclip.mSingleThumbnail;
                nexclip2.mThumbnails = nexclip.mThumbnails;
            } catch (CloneNotSupportedException e) {
                e = e;
                e.printStackTrace();
                return nexclip2;
            }
        } catch (CloneNotSupportedException e2) {
            e = e2;
            nexclip2 = null;
            e.printStackTrace();
            return nexclip2;
        }
        return nexclip2;
    }

    public static nexClip dup(nexClip nexclip) {
        return new nexClip(nexclip);
    }

    /* access modifiers changed from: private */
    public int getIndexSeekTabNearTimeStamp(int[] iArr, int i, int i2) {
        while (i < iArr.length) {
            if (iArr[i] == i2) {
                return i;
            }
            if (iArr[i] <= i2) {
                i++;
            } else if (i == 0) {
                return i;
            } else {
                int i3 = i - 1;
                return iArr[i] - i2 > i2 - iArr[i3] ? i3 : i;
            }
        }
        if (i >= iArr.length) {
            return iArr.length - 1;
        }
        return -1;
    }

    public static nexClip getSolidClip(int i) {
        return getSolidClip(String.format("@solid:%08X.jpg", new Object[]{Integer.valueOf(i)}));
    }

    private static nexClip getSolidClip(String str) {
        nexClip nexclip = new nexClip();
        nexclip.mPath = str;
        nexclip.mInfo = new ClipInfo();
        ClipInfo clipInfo = nexclip.mInfo;
        clipInfo.mClipType = 1;
        clipInfo.mWidth = 32;
        clipInfo.mHeight = 18;
        return nexclip;
    }

    public static nexClip getSupportedClip(String str) {
        return getSupportedClip(str, true);
    }

    public static nexClip getSupportedClip(String str, boolean z) {
        return getSupportedClip(str, z, 0);
    }

    public static nexClip getSupportedClip(String str, boolean z, int i) {
        return getSupportedClip(str, z, i, false);
    }

    public static nexClip getSupportedClip(String str, boolean z, int i, boolean z2) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("@solid:")) {
            return getSolidClip(str);
        }
        if (str.startsWith("@assetItem:")) {
            return new nexClip(str.substring(11), str);
        }
        String transCodingPath = transCodingPath(str);
        String str2 = transCodingPath == null ? str : transCodingPath;
        if (str2.startsWith("@assetItem:")) {
            return new nexClip(str, str2);
        }
        MediaInfo a = MediaInfo.a(str2, z);
        ClipInfo resolveClip = resolveClip(str2, z, a, z2);
        if (resolveClip.mSuppertedResult == 0) {
            if (resolveClip.mClipType == 1) {
                return new nexClip(str, str2, resolveClip, z, a);
            }
            if (resolveClip.mClipType == 4) {
                if (i <= 0 || resolveClip.mWidth * resolveClip.mHeight * resolveClip.mFramesPerSecond <= i) {
                    return new nexClip(str, str2, resolveClip, z, a);
                }
                return null;
            } else if (resolveClip.mClipType == 3) {
                return new nexClip(str, str2, resolveClip, z, a);
            }
        }
        if (resolveClip.mSuppertedResult != 13) {
            int i2 = resolveClip.mSuppertedResult;
        }
        return null;
    }

    private Bitmap makeThumbnail(float f, float f2) {
        Bitmap bitmap;
        if (this.mInfo.mClipType == 1) {
            if (isSolid()) {
                int parseLong = (int) Long.parseLong(this.mPath.substring(7, 15), 16);
                int[] iArr = new int[576];
                for (int i = 0; i < iArr.length; i++) {
                    iArr[i] = parseLong;
                }
                bitmap = Bitmap.createBitmap(iArr, 32, 18, Bitmap.Config.ARGB_8888);
            } else {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.mPath, options);
                options.inJustDecodeBounds = false;
                bitmap = NexImageLoader.loadBitmap(this.mPath, Math.min((int) (f2 * 100.0f), (int) ((((float) options.outWidth) / ((float) options.outHeight)) * f)) * 2, ((int) f) * 2).a();
            }
            if (bitmap != null) {
                return Bitmap.createScaledBitmap(bitmap, Math.min((int) (f2 * 100.0f), (int) ((((float) bitmap.getWidth()) / ((float) bitmap.getHeight())) * f)), (int) f, true);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ca  */
    private static ClipInfo resolveClip(String str, boolean z, MediaInfo mediaInfo, boolean z2) {
        NexEditor a;
        d j;
        int i;
        String str2 = str;
        ClipInfo clipInfo = new ClipInfo();
        FileType fromFile = FileType.fromFile(str);
        if (fromFile == null || !fromFile.isImage()) {
            MediaInfo a2 = mediaInfo == null ? MediaInfo.a(str, z) : mediaInfo;
            int j2 = a2.j();
            if (j2 == -1) {
                clipInfo.mSuppertedResult = 1;
            } else if (j2 == -2) {
                clipInfo.mSuppertedResult = 2;
            } else if (j2 == -3) {
                clipInfo.mSuppertedResult = 3;
            } else if (j2 == -4) {
                clipInfo.mSuppertedResult = 4;
            } else if (j2 == -5) {
                clipInfo.mSuppertedResult = 5;
            } else if (j2 == -6) {
                clipInfo.mSuppertedResult = 6;
            } else if (j2 == -7) {
                clipInfo.mSuppertedResult = 7;
            } else if (j2 == -8) {
                clipInfo.mSuppertedResult = 8;
            } else if (j2 == -9) {
                clipInfo.mSuppertedResult = 9;
            } else if (j2 == -10) {
                clipInfo.mSuppertedResult = 10;
            } else if (j2 == -11) {
                clipInfo.mSuppertedResult = 11;
            } else if (j2 == -12) {
                clipInfo.mSuppertedResult = 12;
            } else {
                clipInfo.mSuppertedResult = 0;
            }
            if (clipInfo.mExistVideo && clipInfo.mSuppertedResult == 1 && (a = EditorGlobal.a()) != null && (j = a.j()) != null) {
                int a3 = j.a(a2.u(), a2.v(), a2.n(), a2.o(), a2.s(), a2.w(), a2.y(), a2.z());
                if (a3 == 0) {
                    clipInfo.mSuppertedResult = 0;
                } else if (a3 == 1) {
                    clipInfo.mSuppertedResult = 13;
                } else if (a3 == 2) {
                    clipInfo.mSuppertedResult = 14;
                } else if (a3 == 3) {
                    clipInfo.mSuppertedResult = 8;
                } else if (a3 == 4) {
                    clipInfo.mSuppertedResult = 5;
                }
            }
            clipInfo.mRotateDegreeInMeta = a2.t();
            clipInfo.mH264Level = a2.v();
            clipInfo.mH264Profile = a2.u();
            clipInfo.mFramesPerSecond = a2.s();
            clipInfo.mExistAudio = a2.l();
            clipInfo.mExistVideo = a2.m();
            clipInfo.mTotalTime = a2.p();
            clipInfo.mVideoTotalTime = a2.r();
            clipInfo.mAudioTotalTime = a2.q();
            clipInfo.mSeekPointCount = a2.f();
            clipInfo.mVideoRenderMode = a2.A();
            clipInfo.mVideoHDRType = a2.D();
            if (clipInfo.mExistVideo) {
                clipInfo.mVideoCodecType = nexCodecType.fromValue(a2.B()).getType();
            } else {
                clipInfo.mVideoCodecType = "none";
            }
            if (clipInfo.mExistAudio) {
                clipInfo.mAudioCodecType = nexCodecType.fromValue(a2.C()).getType();
            } else {
                clipInfo.mAudioCodecType = "none";
            }
            if (clipInfo.mExistVideo) {
                clipInfo.mWidth = a2.n();
                clipInfo.mHeight = a2.o();
                clipInfo.mClipType = 4;
                clipInfo.mVideoBitrate = a2.w();
                int i2 = clipInfo.mAudioTotalTime - clipInfo.mVideoTotalTime;
                if (i2 > 200 || i2 < -200) {
                    Log.w(TAG, "AudioTotalTime=" + clipInfo.mAudioTotalTime + ", VideoTotalTime=" + clipInfo.mVideoTotalTime + ", diff=" + i2);
                }
                if (clipInfo.mTotalTime == 0) {
                    clipInfo.mTotalTime = clipInfo.mVideoTotalTime;
                }
                if (i2 > 0) {
                    Log.w(TAG, "different video and audio TotalTime. TotalTime(" + clipInfo.mTotalTime + ") was set videoTotalTime=" + clipInfo.mVideoTotalTime);
                    clipInfo.mTotalTime = clipInfo.mVideoTotalTime;
                }
                if (clipInfo.mExistAudio) {
                    clipInfo.mAudioBitrate = a2.x();
                }
            } else if (clipInfo.mExistAudio) {
                clipInfo.mClipType = 3;
                clipInfo.mAudioBitrate = a2.x();
            }
            if (clipInfo.mTotalTime < 0) {
                clipInfo.mSuppertedResult = 6;
            }
        } else if (!fromFile.isSupportedFormat()) {
            clipInfo.mSuppertedResult = 12;
            clipInfo.mClipType = 1;
            return clipInfo;
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (z2) {
                options.inJustDecodeBounds = false;
                options.inSampleSize = 16;
            } else {
                options.inJustDecodeBounds = true;
            }
            Bitmap decodeFile = BitmapFactory.decodeFile(str2, options);
            clipInfo.mWidth = options.outWidth;
            clipInfo.mHeight = options.outHeight;
            if (z2 && decodeFile == null) {
                clipInfo.mClipType = 0;
                clipInfo.mSuppertedResult = 12;
                Log.e(TAG, "error : Image file=" + str2 + " decode fail!");
            } else if (clipInfo.mWidth <= 1 || clipInfo.mHeight <= 1) {
                clipInfo.mClipType = 0;
                clipInfo.mSuppertedResult = 7;
                Log.e(TAG, "error : Image file=" + str2 + " decoded resolution is too low!");
            } else {
                clipInfo.mClipType = 1;
                clipInfo.mSuppertedResult = 0;
            }
            String lowerCase = str2.toLowerCase(Locale.US);
            if (lowerCase.endsWith(".jpeg") || lowerCase.endsWith(".jpg")) {
                try {
                    i = new ExifInterface(str2).getAttributeInt("Orientation", 0);
                } catch (IOException unused) {
                }
                if (i != 3) {
                    clipInfo.mRotateDegreeInMeta = kClip_Rotate_180;
                } else if (i == 6) {
                    clipInfo.mRotateDegreeInMeta = 90;
                } else if (i != 8) {
                    clipInfo.mRotateDegreeInMeta = 0;
                } else {
                    clipInfo.mRotateDegreeInMeta = kClip_Rotate_270;
                }
            }
            i = 0;
            if (i != 3) {
            }
        }
        return clipInfo;
    }

    private int setMediaInfo(MediaInfo mediaInfo) {
        this.mediainfo = mediaInfo;
        return 1;
    }

    @Deprecated
    public static void setThumbTempDir(String str) {
        if (str == null) {
            MediaInfo.a((File) null);
            return;
        }
        File file = new File(str);
        if (file.isDirectory()) {
            MediaInfo.a(file);
            return;
        }
        Log.e(TAG, "setThumbTempDir not dir=" + str);
    }

    @Deprecated
    public static void setVideoClipDetailThumbnailsDiskLimit(long j) {
        sVideoClipDetailThumbnailsDiskLimitSize = j;
    }

    private static String transCodingPath(String str) {
        if (str == null) {
            Log.d(TAG, "transCodingPath path null");
            return null;
        } else if (str.startsWith("nexasset://")) {
            Log.d(TAG, "transCodingPath path asset");
            return null;
        } else if (str.startsWith("@solid:")) {
            Log.d(TAG, "transCodingPath path solid");
            return null;
        } else if (!new File(str).isFile()) {
            return nexAssetPackageManager.getAssetPackageMediaPath(a.a().b(), str);
        } else {
            Log.d(TAG, "transCodingPath path is file");
            return null;
        }
    }

    public boolean ableToDecoding() {
        boolean hasVideo = hasVideo();
        boolean hasAudio = hasAudio();
        if (hasVideo || hasAudio) {
            MediaInfo mediaInfo = getMediaInfo();
            if (mediaInfo == null) {
                Log.d(TAG, "ableToDecoding getMediaInfo fail!");
                return false;
            }
            NexEditor.ErrorCode a = mediaInfo.a(hasVideo, hasAudio);
            if (a == NexEditor.ErrorCode.NONE) {
                return true;
            }
            Log.d(TAG, "ableToDecoding fail!=" + a);
            return false;
        }
        Log.d(TAG, "ableToDecoding video not found!");
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addDrawInfo(nexDrawInfo nexdrawinfo) {
        this.mDrawInfos.add(nexdrawinfo);
    }

    /* access modifiers changed from: package-private */
    public void addDrawInfos(List<nexDrawInfo> list) {
        for (nexDrawInfo add : list) {
            this.mDrawInfos.add(add);
        }
    }

    /* access modifiers changed from: package-private */
    public void clearDrawInfos() {
        this.mDrawInfos.clear();
    }

    public int getAVCLevel() {
        return this.mInfo.mH264Level;
    }

    public int getAVCProfile() {
        return this.mInfo.mH264Profile;
    }

    public int getAVSyncTime() {
        return this.mAVSyncAudioStartTime;
    }

    /* access modifiers changed from: protected */
    public final boolean getAttachmentState() {
        return this.mProjectAttachment;
    }

    public int getAudioBitrate() {
        return this.mInfo.mAudioBitrate;
    }

    public String getAudioCodecType() {
        return this.mInfo.mAudioCodecType;
    }

    public int getAudioDuration() {
        return this.mInfo.mAudioTotalTime;
    }

    public nexAudioEdit getAudioEdit() {
        if (!this.mProjectAttachment) {
            throw new ProjectNotAttachedException();
        } else if (this.mInfo.mClipType != 4 && this.mInfo.mClipType != 3) {
            return null;
        } else {
            if (this.mAudioEdit == null) {
                this.mAudioEdit = nexAudioEdit.getnexAudioEdit(this);
            }
            return this.mAudioEdit;
        }
    }

    public nexAudioEnvelop getAudioEnvelop() {
        if (this.mAudioEnvelop == null) {
            this.mAudioEnvelop = new nexAudioEnvelop(this);
        }
        return this.mAudioEnvelop;
    }

    public boolean getAudioOnOff() {
        return this.mAudioOnOff;
    }

    public boolean getAudioPcmLevels(final OnGetAudioPcmLevelsResultListener onGetAudioPcmLevelsResultListener) {
        MediaInfo mediaInfo;
        if (!hasAudio() || (mediaInfo = getMediaInfo()) == null || this.m_gettingPcmData) {
            return false;
        }
        this.m_gettingPcmData = true;
        mediaInfo.c().onResultAvailable(new ResultTask.OnResultAvailableListener<b>() {
            /* renamed from: a */
            public void onResultAvailable(ResultTask<b> resultTask, Task.Event event, b bVar) {
                boolean unused = nexClip.this.m_gettingPcmData = false;
                OnGetAudioPcmLevelsResultListener onGetAudioPcmLevelsResultListener = onGetAudioPcmLevelsResultListener;
                if (onGetAudioPcmLevelsResultListener != null) {
                    onGetAudioPcmLevelsResultListener.onGetAudioPcmLevelsResult(bVar.a());
                }
            }
        });
        return true;
    }

    public int getBGMVolume() {
        return this.m_BGMVolume;
    }

    public int getBrightness() {
        return this.m_Brightness;
    }

    public nexClipEffect getClipEffect() {
        if (this.mProjectAttachment) {
            if (this.mClipEffect == null) {
                this.mClipEffect = new nexClipEffect();
            }
            return this.mClipEffect;
        }
        throw new ProjectNotAttachedException();
    }

    public nexClipEffect getClipEffect(boolean z) {
        if (!this.mProjectAttachment) {
            Log.e(TAG, "getClipEffect not project attachment startTime=" + this.mStartTime);
        }
        if (this.mClipEffect == null) {
            this.mClipEffect = new nexClipEffect();
        }
        return this.mClipEffect;
    }

    public boolean getClipPropertySlowVideoMode() {
        return this.mPropertySlowVideoMode;
    }

    public int getClipType() {
        return this.mInfo.mClipType;
    }

    public int getClipVolume() {
        return this.m_ClipVolume;
    }

    /* access modifiers changed from: package-private */
    public String getCollageDrawInfoID() {
        return this.mCollageDrawInfoID;
    }

    public nexColorEffect getColorEffect() {
        return this.mColorEffect;
    }

    /* access modifiers changed from: package-private */
    public String getColorEffectID() {
        nexColorEffect nexcoloreffect = this.mColorEffect;
        return nexcoloreffect == null ? nexColorEffect.NONE.getKineMasterID() : nexcoloreffect.getKineMasterID();
    }

    /* access modifiers changed from: protected */
    public int getCombinedBrightness() {
        nexColorEffect nexcoloreffect = this.mColorEffect;
        return nexcoloreffect == null ? this.m_Brightness : this.m_Brightness + ((int) (nexcoloreffect.getBrightness() * 255.0f));
    }

    /* access modifiers changed from: protected */
    public int getCombinedContrast() {
        nexColorEffect nexcoloreffect = this.mColorEffect;
        return nexcoloreffect == null ? this.m_Contrast : this.m_Contrast + ((int) (nexcoloreffect.getContrast() * 255.0f));
    }

    /* access modifiers changed from: protected */
    public int getCombinedSaturation() {
        nexColorEffect nexcoloreffect = this.mColorEffect;
        return nexcoloreffect == null ? this.m_Saturation : this.m_Saturation + ((int) (nexcoloreffect.getSaturation() * 255.0f));
    }

    public int getContrast() {
        return this.m_Contrast;
    }

    public nexCrop getCrop() {
        nexCrop nexcrop = this.mCrop;
        if (nexcrop == null) {
            int i = this.mInfo.mWidth;
            int i2 = this.mInfo.mHeight;
            if (this.mInfo.mClipType == 1 && (this.mInfo.mRotateDegreeInMeta == 90 || this.mInfo.mRotateDegreeInMeta == 270)) {
                i = this.mInfo.mHeight;
                i2 = this.mInfo.mWidth;
            }
            int projectDuration = getProjectDuration();
            this.mCrop = new nexCrop(this.mPath, i, i2, this.mRotate, projectDuration);
        } else {
            nexcrop.setRotate(this.mRotate);
            this.mCrop.setClipDuration(getProjectDuration());
        }
        return this.mCrop;
    }

    /* access modifiers changed from: protected */
    public int getCustomLUTA() {
        return this.mCustomLUT_A;
    }

    /* access modifiers changed from: protected */
    public int getCustomLUTB() {
        return this.mCustomLUT_B;
    }

    /* access modifiers changed from: protected */
    public int getCustomLUTPower() {
        return this.mCustomLUT_Power;
    }

    public int getDisplayHeight() {
        return this.mInfo.mDisplayHeight;
    }

    public int getDisplayWidth() {
        return this.mInfo.mDisplayWidth;
    }

    /* access modifiers changed from: package-private */
    public List<nexDrawInfo> getDrawInfos() {
        return this.mDrawInfos;
    }

    /* access modifiers changed from: package-private */
    public Rect getFaceRect() {
        return this.mFaceRect;
    }

    public int getFramesPerSecond() {
        return this.mInfo.mFramesPerSecond;
    }

    public int getHeight() {
        return this.mInfo.mHeight;
    }

    public int getImageClipDuration() {
        return this.mDuration;
    }

    /* access modifiers changed from: protected */
    public int getLUTId() {
        nexColorEffect nexcoloreffect = this.mColorEffect;
        if (nexcoloreffect == null) {
            return 0;
        }
        return nexcoloreffect.getLUTId();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.graphics.Bitmap} */
    /* JADX WARNING: Multi-variable type inference failed */
    public Bitmap getMainThumbnail(float f, float f2) {
        Bitmap bitmap = null;
        if (this.mInfo.mClipType == 4) {
            h hVar = this.mThumbnails;
            if (hVar != null) {
                return hVar.a(0, this.mInfo.mTotalTime / 2, false, false);
            }
            return null;
        }
        WeakReference<Bitmap> weakReference = this.mSingleThumbnail;
        if (weakReference != null) {
            bitmap = weakReference.get();
        }
        if (bitmap == null && (bitmap = makeThumbnail(f, f2)) != null) {
            this.mSingleThumbnail = new WeakReference<>(bitmap);
        }
        return bitmap;
    }

    /* access modifiers changed from: package-private */
    public MediaInfo getMediaInfo() {
        if (this.mediainfo == null) {
            this.mediainfo = MediaInfo.a(getRealPath(), this.mMediaInfoUseCache);
        }
        return this.mediainfo;
    }

    public String getPath() {
        return this.mPath;
    }

    /* access modifiers changed from: protected */
    public int getProjectDuration() {
        int i = this.mInfo.mClipType;
        if (i == 1) {
            return this.mDuration;
        }
        if (i == 3) {
            return this.mInfo.mTotalTime;
        }
        if (i != 4) {
            return 0;
        }
        int i2 = this.mInfo.mTotalTime;
        nexVideoClipEdit nexvideoclipedit = this.mVideoEdit;
        if (nexvideoclipedit == null) {
            return i2;
        }
        if (!(nexvideoclipedit.mTrimStartDuration == 0 && this.mVideoEdit.mTrimEndDuration == 0)) {
            i2 = (this.mInfo.mTotalTime - this.mVideoEdit.mTrimStartDuration) - this.mVideoEdit.mTrimEndDuration;
        }
        int speedControl = this.mVideoEdit.getSpeedControl();
        return speedControl != 100 ? Math.round((float) ((i2 * 100) / speedControl)) : i2;
    }

    public int getProjectEndTime() {
        if (this.mProjectAttachment) {
            return this.mEndTime;
        }
        return 0;
    }

    public int getProjectStartTime() {
        if (this.mProjectAttachment) {
            return this.mStartTime;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public String getRealPath() {
        String str = this.mTransCodingPath;
        return str != null ? str : this.mPath;
    }

    public int getRotateDegree() {
        return this.mRotate;
    }

    public int getRotateInMeta() {
        return this.mInfo.mRotateDegreeInMeta;
    }

    public int getSaturation() {
        return this.m_Saturation;
    }

    /* access modifiers changed from: package-private */
    public nexSaveDataFormat.nexClipOf getSaveData() {
        nexSaveDataFormat.nexClipOf nexclipof = new nexSaveDataFormat.nexClipOf();
        nexclipof.m_BGMVolume = this.m_BGMVolume;
        nexclipof.mPath = this.mPath;
        nexclipof.mTransCodingPath = this.mTransCodingPath;
        nexClipEffect nexclipeffect = this.mClipEffect;
        if (nexclipeffect == null) {
            nexclipof.mClipEffect = null;
        } else {
            nexclipof.mClipEffect = nexclipeffect.getSaveData();
        }
        nexTransitionEffect nextransitioneffect = this.mTransitionEffect;
        if (nextransitioneffect == null) {
            nexclipof.mTransitionEffect = null;
        } else {
            nexclipof.mTransitionEffect = nextransitioneffect.getSaveData();
        }
        nexclipof.misMustDownSize = this.misMustDownSize;
        nexclipof.mAudioOnOff = this.mAudioOnOff;
        nexclipof.m_Brightness = this.m_Brightness;
        nexclipof.m_Contrast = this.m_Contrast;
        nexclipof.m_Saturation = this.m_Saturation;
        nexclipof.mVignette = this.mVignette;
        nexclipof.mFacedetectProcessed = this.mFacedetectProcessed;
        nexclipof.mFaceDetected = this.mFaceDetected;
        nexclipof.mFaceRect = this.mFaceRect;
        nexCrop nexcrop = this.mCrop;
        if (nexcrop == null) {
            nexclipof.mCrop = null;
        } else {
            nexclipof.mCrop = nexcrop.getSaveData();
        }
        int i = this.mTitleEffectStartTime;
        nexclipof.mTitleEffectStartTime = i;
        nexclipof.mTitleEffectEndTime = i;
        nexAudioEnvelop nexaudioenvelop = this.mAudioEnvelop;
        if (nexaudioenvelop == null) {
            nexclipof.mAudioEnvelop = null;
        } else {
            nexclipof.mAudioEnvelop = nexaudioenvelop.getSaveData();
        }
        nexAudioEdit nexaudioedit = this.mAudioEdit;
        if (nexaudioedit == null) {
            nexclipof.mAudioEdit = null;
        } else {
            nexclipof.mAudioEdit = nexaudioedit.getSaveData();
        }
        nexclipof.mTemplateEffectID = this.mTemplateEffectID;
        nexclipof.mCollageDrawInfoID = this.mCollageDrawInfoID;
        nexclipof.mTemplateAudioPos = this.mTemplateAudioPos;
        nexclipof.mDrawInfos = this.mDrawInfos;
        nexclipof.mOverlappedTransition = this.mOverlappedTransition;
        nexclipof.mMediaInfoUseCache = this.mMediaInfoUseCache;
        nexclipof.mStartTime = this.mStartTime;
        nexclipof.mEndTime = this.mEndTime;
        nexclipof.mDuration = this.mDuration;
        nexclipof.mInfo = this.mInfo;
        nexVideoClipEdit nexvideoclipedit = this.mVideoEdit;
        if (nexvideoclipedit == null) {
            nexclipof.mVideoEdit = null;
        } else {
            nexclipof.mVideoEdit = nexvideoclipedit.getSaveData();
        }
        nexclipof.mCustomLUT_A = this.mCustomLUT_A;
        nexclipof.mCustomLUT_B = this.mCustomLUT_B;
        nexclipof.mCustomLUT_Power = this.mCustomLUT_Power;
        nexColorEffect nexcoloreffect = this.mColorEffect;
        if (nexcoloreffect == null) {
            nexclipof.mColorEffect = null;
        } else {
            nexclipof.mColorEffect = nexcoloreffect.getSaveData();
        }
        nexclipof.mPropertySlowVideoMode = this.mPropertySlowVideoMode;
        nexclipof.mAVSyncAudioStartTime = this.mAVSyncAudioStartTime;
        nexclipof.m_ClipVolume = this.m_ClipVolume;
        nexclipof.m_BGMVolume = this.m_BGMVolume;
        nexclipof.mRotate = this.mRotate;
        return nexclipof;
    }

    public int getSeekPointCount() {
        return this.mInfo.mSeekPointCount;
    }

    public int getSeekPointInterval() {
        return this.mInfo.mSeekPointCount == 0 ? this.mInfo.mVideoTotalTime : this.mInfo.mVideoTotalTime / this.mInfo.mSeekPointCount;
    }

    public int[] getSeekPointsSync() {
        MediaInfo mediaInfo;
        if (this.mInfo.mClipType == 4 && (mediaInfo = getMediaInfo()) != null) {
            return mediaInfo.d();
        }
        return null;
    }

    public int getSolidColor() {
        if (!isSolid()) {
            return 0;
        }
        return (int) Long.parseLong(this.mPath.substring(7, 15), 16);
    }

    public int getSupportedResult() {
        return this.mInfo.mSuppertedResult;
    }

    /* access modifiers changed from: package-private */
    public int getTemplateAudioPos() {
        return this.mTemplateAudioPos;
    }

    /* access modifiers changed from: package-private */
    public int getTemplateEffectID() {
        return this.mTemplateEffectID;
    }

    /* access modifiers changed from: package-private */
    public boolean getTemplateOverlappedTransition() {
        return this.mOverlappedTransition;
    }

    /* access modifiers changed from: protected */
    public int getTintColor() {
        nexColorEffect nexcoloreffect = this.mColorEffect;
        if (nexcoloreffect == null) {
            return 0;
        }
        return nexcoloreffect.getTintColor();
    }

    public int getTotalTime() {
        return this.mInfo.mTotalTime;
    }

    public nexTransitionEffect getTransitionEffect() {
        if (this.mProjectAttachment) {
            if (this.mTransitionEffect == null) {
                this.mTransitionEffect = new nexTransitionEffect(this.mObserver);
            }
            return this.mTransitionEffect;
        }
        throw new ProjectNotAttachedException();
    }

    public nexTransitionEffect getTransitionEffect(boolean z) {
        if (!this.mProjectAttachment) {
            Log.e(TAG, "getTransitionEffect not project attachment startTime=" + this.mStartTime);
        }
        if (this.mTransitionEffect == null) {
            this.mTransitionEffect = new nexTransitionEffect(this.mObserver);
        }
        return this.mTransitionEffect;
    }

    public int getVideoBitrate() {
        return this.mInfo.mVideoBitrate;
    }

    public int getVideoClipDetailThumbnails(int i, int i2, int i3, int i4, int i5, int i6, OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener) {
        boolean z;
        long a = e.a(Environment.getDataDirectory().getAbsoluteFile());
        if (a < sVideoClipDetailThumbnailsDiskLimitSize) {
            Log.d(TAG, "getVideoClipDetailThumbnails() disk low. run no cache mode. disk size=" + a + ", limit=" + sVideoClipDetailThumbnailsDiskLimitSize);
            z = true;
        } else {
            z = false;
        }
        return getVideoClipDetailThumbnails(i, i2, i3, i4, i5, i6, z, (int[]) null, onGetVideoClipDetailThumbnailsListener);
    }

    public int getVideoClipDetailThumbnails(int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, boolean z3, boolean z4, int[] iArr, OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener) {
        int[] iArr2;
        int i7 = i6;
        final OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener2 = onGetVideoClipDetailThumbnailsListener;
        if (this.mInfo.mClipType != 4 || onGetVideoClipDetailThumbnailsListener2 == null) {
            return -1;
        }
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null) {
            if (onGetVideoClipDetailThumbnailsListener2 != null) {
                onGetVideoClipDetailThumbnailsListener.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_systemError, (Bitmap) null, 0, 0, 0);
            }
            return OnGetVideoClipDetailThumbnailsListener.kEvent_systemError;
        }
        int i8 = i7 != 90 ? i7 != 180 ? i7 != 270 ? 0 : 64 : 32 : 16;
        if (z) {
            i8 |= nexEngine.ExportHEVCMainTierLevel52;
        }
        if (z2) {
            i8 |= 256;
            iArr2 = null;
        } else {
            iArr2 = iArr;
        }
        if (z3) {
            i8 |= nexEngine.ExportHEVCHighTierLevel52;
        }
        if (z4) {
            i8 |= nexEngine.ExportHEVCMainTierLevel6;
        }
        mediaInfo.a(i, i2, i3, i4, i5, i8, iArr2, new com.nexstreaming.kminternal.kinemaster.mediainfo.d() {
            public void a(Bitmap bitmap, int i, int i2, int i3) {
                if (bitmap == null) {
                    Log.d(nexClip.TAG, "detailThumbTest processThumbnail : bm=null index=" + i + " totalCount=" + i2 + " timestamp=" + i3);
                } else {
                    Log.d(nexClip.TAG, "detailThumbTest processThumbnail : bm=[" + bitmap.getWidth() + "x" + bitmap.getHeight() + " cfg=" + bitmap.getConfig().name() + "] index=" + i + " totalCount=" + i2 + " timestamp=" + i3);
                }
                onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_Ok, bitmap, i, i2, i3);
            }
        }).onFailure((Task.OnFailListener) new Task.OnFailListener() {
            public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                Log.d(nexClip.TAG, "detailThumbTest onFail : " + taskError.getMessage());
                if (taskError == NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL) {
                    onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_UserCancel, (Bitmap) null, 0, 0, 0);
                } else {
                    onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_Fail, (Bitmap) null, 0, 0, 0);
                }
            }
        }).onComplete(new Task.OnTaskEventListener() {
            public void onTaskEvent(Task task, Task.Event event) {
                Log.d(nexClip.TAG, "detailThumbTest onComplete");
                onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_Completed, (Bitmap) null, 0, 0, 0);
            }
        });
        return 0;
    }

    public int getVideoClipDetailThumbnails(int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int[] iArr, OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener) {
        return getVideoClipDetailThumbnails(i, i2, i3, i4, i5, i6, z, z2, false, false, (int[]) null, onGetVideoClipDetailThumbnailsListener);
    }

    public int getVideoClipDetailThumbnails(int i, int i2, int i3, int i4, int i5, int i6, boolean z, int[] iArr, OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener) {
        int i7 = i6;
        final OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener2 = onGetVideoClipDetailThumbnailsListener;
        if (this.mInfo.mClipType != 4 || onGetVideoClipDetailThumbnailsListener2 == null) {
            return -1;
        }
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null) {
            if (onGetVideoClipDetailThumbnailsListener2 != null) {
                onGetVideoClipDetailThumbnailsListener.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_systemError, (Bitmap) null, 0, 0, 0);
            }
            return OnGetVideoClipDetailThumbnailsListener.kEvent_systemError;
        }
        int i8 = i7 != 90 ? i7 != 180 ? i7 != 270 ? 0 : 64 : 32 : 16;
        if (z) {
            i8 |= nexEngine.ExportHEVCMainTierLevel52;
        }
        mediaInfo.a(i, i2, i3, i4, i5, i8, iArr, new com.nexstreaming.kminternal.kinemaster.mediainfo.d() {
            public void a(Bitmap bitmap, int i, int i2, int i3) {
                if (bitmap == null) {
                    Log.d(nexClip.TAG, "detailThumbTest processThumbnail : bm=null index=" + i + " totalCount=" + i2 + " timestamp=" + i3);
                } else {
                    Log.d(nexClip.TAG, "detailThumbTest processThumbnail : bm=[" + bitmap.getWidth() + "x" + bitmap.getHeight() + " cfg=" + bitmap.getConfig().name() + "] index=" + i + " totalCount=" + i2 + " timestamp=" + i3);
                }
                onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_Ok, bitmap, i, i2, i3);
            }
        }).onFailure((Task.OnFailListener) new Task.OnFailListener() {
            public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                Log.d(nexClip.TAG, "detailThumbTest onFail : " + taskError.getMessage());
                if (taskError == NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL) {
                    onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_UserCancel, (Bitmap) null, 0, 0, 0);
                } else {
                    onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_Fail, (Bitmap) null, 0, 0, 0);
                }
            }
        }).onComplete(new Task.OnTaskEventListener() {
            public void onTaskEvent(Task task, Task.Event event) {
                Log.d(nexClip.TAG, "detailThumbTest onComplete");
                onGetVideoClipDetailThumbnailsListener2.onGetDetailThumbnailResult(OnGetVideoClipDetailThumbnailsListener.kEvent_Completed, (Bitmap) null, 0, 0, 0);
            }
        });
        return 0;
    }

    public int getVideoClipDetailThumbnails(int i, int i2, int[] iArr, int i3, OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener) {
        boolean z;
        long a = e.a(Environment.getDataDirectory().getAbsoluteFile());
        if (a < sVideoClipDetailThumbnailsDiskLimitSize) {
            Log.d(TAG, "getVideoClipDetailThumbnails() disk low. run no cache mode. disk size=" + a + ", limit=" + sVideoClipDetailThumbnailsDiskLimitSize);
            z = true;
        } else {
            z = false;
        }
        return getVideoClipDetailThumbnails(i, i2, 0, 0, iArr != null ? iArr.length : 0, i3, z, iArr, onGetVideoClipDetailThumbnailsListener);
    }

    public nexVideoClipEdit getVideoClipEdit() {
        if (!this.mProjectAttachment) {
            throw new ProjectNotAttachedException();
        } else if (this.mInfo.mClipType != 4) {
            return null;
        } else {
            if (this.mVideoEdit == null) {
                this.mVideoEdit = nexVideoClipEdit.getnexVideoClipEdit(this);
            }
            return this.mVideoEdit;
        }
    }

    @Deprecated
    public int getVideoClipIDR2YOnlyThumbnails(int i, int i2, int i3, int i4, int i5, OnGetVideoClipIDR2YOnlyThumbnailsListener onGetVideoClipIDR2YOnlyThumbnailsListener) {
        final OnGetVideoClipIDR2YOnlyThumbnailsListener onGetVideoClipIDR2YOnlyThumbnailsListener2 = onGetVideoClipIDR2YOnlyThumbnailsListener;
        if (this.mInfo.mClipType != 4 || onGetVideoClipIDR2YOnlyThumbnailsListener2 == null) {
            return -1;
        }
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null) {
            if (onGetVideoClipIDR2YOnlyThumbnailsListener2 != null) {
                onGetVideoClipIDR2YOnlyThumbnailsListener.onGetVideoClipIDR2YOnlyThumbnailsResult(OnGetVideoClipIDR2YOnlyThumbnailsListener.kEvent_systemError, (byte[]) null, 0, 0, 0);
            }
            return OnGetVideoClipIDR2YOnlyThumbnailsListener.kEvent_systemError;
        }
        final int[] d = mediaInfo.d();
        mediaInfo.a(i, i2, i3, i4, i5, 131074, (int[]) null, new com.nexstreaming.kminternal.kinemaster.mediainfo.e() {
            private int d = 0;

            public void a(byte[] bArr, int i, int i2, int i3) {
                if (bArr == null) {
                    Log.d(nexClip.TAG, "YonlyThumbTest processThumbnail : Y=null index=" + i + " totalCount=" + i2 + " timestamp=" + i3);
                } else {
                    Log.d(nexClip.TAG, "YonlyThumbTest processThumbnail : Y=[" + bArr.length + "] index=" + i + " totalCount=" + i2 + " timestamp=" + i3);
                }
                int access$300 = nexClip.this.getIndexSeekTabNearTimeStamp(d, this.d, i3);
                if (access$300 >= 0) {
                    Log.d(nexClip.TAG, "YonlyThumbTest processThumbnail : timestamp =" + i3 + ", seektable=" + d[access$300] + ", lastIndex=" + this.d + ", index=" + access$300);
                    i3 = d[access$300];
                    this.d = access$300 + 1;
                } else {
                    Log.d(nexClip.TAG, "YonlyThumbTest processThumbnail : timestamp =" + i3 + ", lastIndex=" + this.d);
                }
                onGetVideoClipIDR2YOnlyThumbnailsListener2.onGetVideoClipIDR2YOnlyThumbnailsResult(OnGetVideoClipIDR2YOnlyThumbnailsListener.kEvent_Ok, bArr, i, i2, i3);
            }
        }).onFailure((Task.OnFailListener) new Task.OnFailListener() {
            public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                Log.d(nexClip.TAG, "YonlyThumbTest onFail : " + taskError.getMessage());
                if (taskError == NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL) {
                    onGetVideoClipIDR2YOnlyThumbnailsListener2.onGetVideoClipIDR2YOnlyThumbnailsResult(OnGetVideoClipIDR2YOnlyThumbnailsListener.kEvent_UserCancel, (byte[]) null, 0, 0, 0);
                } else {
                    onGetVideoClipIDR2YOnlyThumbnailsListener2.onGetVideoClipIDR2YOnlyThumbnailsResult(OnGetVideoClipIDR2YOnlyThumbnailsListener.kEvent_Fail, (byte[]) null, 0, 0, 0);
                }
            }
        }).onComplete(new Task.OnTaskEventListener() {
            public void onTaskEvent(Task task, Task.Event event) {
                Log.d(nexClip.TAG, "YonlyThumbTest onComplete");
                onGetVideoClipIDR2YOnlyThumbnailsListener2.onGetVideoClipIDR2YOnlyThumbnailsResult(OnGetVideoClipIDR2YOnlyThumbnailsListener.kEvent_Completed, (byte[]) null, 0, 0, 0);
            }
        });
        return 0;
    }

    @Deprecated
    public int getVideoClipIFrameThumbnails(int i, int i2, OnGetVideoClipDetailThumbnailsListener onGetVideoClipDetailThumbnailsListener) {
        return getVideoClipDetailThumbnails(i, i2, 0, 0, 3600, 0, onGetVideoClipDetailThumbnailsListener);
    }

    public int[] getVideoClipTimeLineOfThumbnail() {
        h hVar;
        if (this.mInfo.mClipType == 4 && (hVar = this.mThumbnails) != null) {
            return hVar.b();
        }
        throw new ClipIsNotVideoException();
    }

    public Bitmap getVideoClipTimeLineThumbnail(int i, int i2, boolean z, boolean z2) {
        h hVar;
        if (this.mInfo.mClipType == 4 && (hVar = this.mThumbnails) != null) {
            return hVar.a(i, i2, z, z2);
        }
        throw new ClipIsNotVideoException();
    }

    public String getVideoCodecType() {
        return this.mInfo.mVideoCodecType;
    }

    public int getVideoDuration() {
        return this.mInfo.mVideoTotalTime;
    }

    public int getVideoHDRType() {
        return this.mInfo.mVideoHDRType;
    }

    public int getVideoRenderMode() {
        return this.mInfo.mVideoRenderMode;
    }

    @Deprecated
    public byte[] getVideoUUID() {
        if (this.mInfo.mVideoRenderMode == 0) {
            return null;
        }
        return this.mInfo.mVideoUUID;
    }

    @Deprecated
    public boolean getVignetteEffect() {
        return this.mVignette;
    }

    @Deprecated
    public int getVoiceChangerFactor() {
        if (this.mInfo.mClipType == 4 || this.mInfo.mClipType == 3) {
            return getAudioEdit().getVoiceChangerFactor();
        }
        return 0;
    }

    public int getWidth() {
        return this.mInfo.mWidth;
    }

    public boolean hasAudio() {
        return this.mInfo.mExistAudio;
    }

    public boolean hasVideo() {
        return this.mInfo.mExistVideo;
    }

    public boolean isAssetResource() {
        return this.mIsAssetResource;
    }

    public boolean isEncryptedAssetClip() {
        String str = this.mTransCodingPath;
        return str != null && str.startsWith("@assetItem:");
    }

    /* access modifiers changed from: package-private */
    public boolean isFaceDetectProcessed() {
        return this.mFacedetectProcessed;
    }

    /* access modifiers changed from: package-private */
    public boolean isFaceDetected() {
        return this.mFaceDetected > 0;
    }

    /* access modifiers changed from: protected */
    public boolean isMotionTrackedVideo() {
        return this.isMotionTrackedVideo;
    }

    public boolean isSolid() {
        String str = this.mPath;
        return str != null && str.startsWith("@solid:") && this.mPath.endsWith(".jpg");
    }

    /* access modifiers changed from: package-private */
    public void loadSaveData(nexSaveDataFormat.nexClipOf nexclipof) {
        if (this.mPath.compareTo(nexclipof.mPath) != 0) {
            Log.d(TAG, "loadSaveData invaild path=" + this.mPath + ", input=" + nexclipof.mPath);
            return;
        }
        this.m_BGMVolume = nexclipof.m_BGMVolume;
        if (nexclipof.mClipEffect == null) {
            this.mClipEffect = null;
        } else {
            this.mClipEffect = new nexClipEffect(nexclipof.mClipEffect);
        }
        if (nexclipof.mTransitionEffect == null) {
            this.mTransitionEffect = null;
        } else {
            this.mTransitionEffect = new nexTransitionEffect(this.mObserver, nexclipof.mTransitionEffect);
        }
        this.misMustDownSize = nexclipof.misMustDownSize;
        this.mAudioOnOff = nexclipof.mAudioOnOff;
        this.m_Brightness = nexclipof.m_Brightness;
        this.m_Contrast = nexclipof.m_Contrast;
        this.m_Saturation = nexclipof.m_Saturation;
        this.mVignette = nexclipof.mVignette;
        this.mFacedetectProcessed = nexclipof.mFacedetectProcessed;
        this.mFaceDetected = nexclipof.mFaceDetected;
        this.mFaceRect = nexclipof.mFaceRect;
        if (nexclipof.mCrop == null) {
            this.mCrop = null;
        } else {
            this.mCrop = new nexCrop(this.mPath, nexclipof.mCrop);
        }
        this.mTitleEffectStartTime = nexclipof.mTitleEffectStartTime;
        this.mTitleEffectStartTime = nexclipof.mTitleEffectEndTime;
        if (nexclipof.mAudioEnvelop == null) {
            this.mAudioEnvelop = null;
        } else {
            this.mAudioEnvelop = new nexAudioEnvelop(nexclipof.mAudioEnvelop);
        }
        this.mTemplateEffectID = nexclipof.mTemplateEffectID;
        this.mCollageDrawInfoID = nexclipof.mCollageDrawInfoID;
        this.mTemplateAudioPos = nexclipof.mTemplateAudioPos;
        this.mDrawInfos = nexclipof.mDrawInfos;
        this.mOverlappedTransition = nexclipof.mOverlappedTransition;
        this.mMediaInfoUseCache = nexclipof.mMediaInfoUseCache;
        this.mStartTime = nexclipof.mStartTime;
        this.mEndTime = nexclipof.mEndTime;
        this.mDuration = nexclipof.mDuration;
        this.mInfo = nexclipof.mInfo;
        this.mCustomLUT_A = nexclipof.mCustomLUT_A;
        this.mCustomLUT_B = nexclipof.mCustomLUT_B;
        this.mCustomLUT_Power = nexclipof.mCustomLUT_Power;
        this.mRotate = nexclipof.mRotate;
        if (nexclipof.mColorEffect == null) {
            this.mColorEffect = null;
        } else {
            this.mColorEffect = new nexColorEffect(nexclipof.mColorEffect);
        }
        this.mPropertySlowVideoMode = nexclipof.mPropertySlowVideoMode;
        this.mAVSyncAudioStartTime = nexclipof.mAVSyncAudioStartTime;
        this.m_ClipVolume = nexclipof.m_ClipVolume;
        this.m_BGMVolume = nexclipof.m_BGMVolume;
        if (nexclipof.mVideoEdit == null) {
            this.mVideoEdit = null;
        } else {
            this.mVideoEdit = new nexVideoClipEdit(this, nexclipof.mVideoEdit);
        }
        if (nexclipof.mAudioEdit == null) {
            this.mAudioEdit = null;
        } else {
            this.mAudioEdit = new nexAudioEdit(this, nexclipof.mAudioEdit);
        }
    }

    public int loadVideoClipThumbnails(final OnLoadVideoClipThumbnailListener onLoadVideoClipThumbnailListener) {
        if (this.mInfo.mClipType != 4) {
            return -1;
        }
        if (this.mThumbnails == null && !this.m_gettingThumbnails && !this.m_getThumbnailsFailed) {
            MediaInfo mediaInfo = getMediaInfo();
            if (mediaInfo == null) {
                if (onLoadVideoClipThumbnailListener != null) {
                    onLoadVideoClipThumbnailListener.onLoadThumbnailResult(OnLoadVideoClipThumbnailListener.kEvent_systemError);
                }
                return OnLoadVideoClipThumbnailListener.kEvent_systemError;
            }
            this.m_gettingThumbnails = true;
            mediaInfo.b().onResultAvailable(new ResultTask.OnResultAvailableListener<h>() {
                /* renamed from: a */
                public void onResultAvailable(ResultTask<h> resultTask, Task.Event event, h hVar) {
                    boolean unused = nexClip.this.m_gettingThumbnails = false;
                    h unused2 = nexClip.this.mThumbnails = hVar;
                    OnLoadVideoClipThumbnailListener onLoadVideoClipThumbnailListener = onLoadVideoClipThumbnailListener;
                    if (onLoadVideoClipThumbnailListener != null) {
                        onLoadVideoClipThumbnailListener.onLoadThumbnailResult(OnLoadVideoClipThumbnailListener.kEvent_Ok);
                    }
                }
            }).onFailure((Task.OnFailListener) new Task.OnFailListener() {
                public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                    boolean unused = nexClip.this.m_gettingThumbnails = false;
                    if (taskError != NexEditor.ErrorCode.INPROGRESS_GETCLIPINFO) {
                        boolean unused2 = nexClip.this.m_getThumbnailsFailed = true;
                    }
                    if (onLoadVideoClipThumbnailListener == null) {
                        return;
                    }
                    if (nexClip.this.m_getThumbnailsFailed) {
                        onLoadVideoClipThumbnailListener.onLoadThumbnailResult(OnLoadVideoClipThumbnailListener.kEvent_loadFail);
                    } else {
                        onLoadVideoClipThumbnailListener.onLoadThumbnailResult(OnLoadVideoClipThumbnailListener.kEvent_mustRetry);
                    }
                }
            });
            return 0;
        } else if (this.m_gettingThumbnails) {
            if (onLoadVideoClipThumbnailListener != null) {
                onLoadVideoClipThumbnailListener.onLoadThumbnailResult(OnLoadVideoClipThumbnailListener.kEvent_Running);
            }
            return OnLoadVideoClipThumbnailListener.kEvent_Running;
        } else if (this.m_getThumbnailsFailed) {
            if (onLoadVideoClipThumbnailListener != null) {
                onLoadVideoClipThumbnailListener.onLoadThumbnailResult(OnLoadVideoClipThumbnailListener.kEvent_loadFail);
            }
            return OnLoadVideoClipThumbnailListener.kEvent_loadFail;
        } else if (this.mThumbnails == null || onLoadVideoClipThumbnailListener == null) {
            return 0;
        } else {
            onLoadVideoClipThumbnailListener.onLoadThumbnailResult(OnLoadVideoClipThumbnailListener.kEvent_loadCompleted);
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void removeDrawInfos(int i) {
        for (nexDrawInfo next : this.mDrawInfos) {
            if (next.getSubEffectID() == i) {
                this.mDrawInfos.remove(next);
                return;
            }
        }
    }

    public int replaceClip(String str) {
        this.mTransCodingPath = transCodingPath(str);
        String str2 = this.mTransCodingPath;
        if (str2 == null) {
            str2 = str;
        }
        ClipInfo resolveClip = resolveClip(str2, this.mMediaInfoUseCache, getMediaInfo(), false);
        if (resolveClip.mSuppertedResult != 0) {
            return resolveClip.mSuppertedResult;
        }
        if (this.mInfo.mClipType != resolveClip.mClipType) {
            return -1;
        }
        this.mPath = str;
        this.mInfo = resolveClip;
        setProjectUpdateSignal(false);
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void resetFaceDetectProcessed() {
        this.mFacedetectProcessed = false;
    }

    /* access modifiers changed from: protected */
    public int runDuration() {
        return this.mInfo.mClipType == 4 ? this.mVideoEdit == null ? this.mInfo.mTotalTime : ((this.mInfo.mTotalTime - this.mVideoEdit.mTrimStartDuration) - this.mVideoEdit.mTrimEndDuration) * Math.round((float) (this.mVideoEdit.getSpeedControl() / 100)) : this.mInfo.mClipType == 1 ? this.mDuration : this.mInfo.mTotalTime;
    }

    public void setAVSyncTime(int i) {
        if (getClipType() == 4 && hasAudio()) {
            if (i < -500) {
                this.mAVSyncAudioStartTime = -500;
            } else if (i > 500) {
                this.mAVSyncAudioStartTime = 500;
            } else {
                this.mAVSyncAudioStartTime = i;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setAssetResource(boolean z) {
        this.mIsAssetResource = z;
    }

    /* access modifiers changed from: protected */
    public final void setAttachmentState(boolean z, b bVar) {
        nexTransitionEffect nextransitioneffect;
        this.mObserver = bVar;
        this.mProjectAttachment = z;
        if (!this.mProjectAttachment && (nextransitioneffect = this.mTransitionEffect) != null) {
            nextransitioneffect.setObserver((b) null);
        }
    }

    public void setAudioOnOff(boolean z) {
        this.mAudioOnOff = z;
        setProjectUpdateSignal(true);
    }

    public boolean setBGMVolume(int i) {
        if (i < 0 || i > 100) {
            return false;
        }
        this.m_BGMVolume = i;
        setProjectUpdateSignal(true);
        return true;
    }

    public boolean setBrightness(int i) {
        if (i < -255 || i > 255) {
            return false;
        }
        this.m_Brightness = i;
        setProjectUpdateSignal(true);
        return true;
    }

    public boolean setClipPropertySlowVideoMode(boolean z) {
        if (this.mInfo.mClipType != 4) {
            Log.d(TAG, "was  not video");
            return false;
        } else if (this.mInfo.mFramesPerSecond < 60) {
            Log.d(TAG, "not supported fps=" + this.mInfo.mFramesPerSecond);
            return false;
        } else {
            int i = this.mInfo.mVideoTotalTime / (this.mInfo.mSeekPointCount > 0 ? this.mInfo.mSeekPointCount : 1);
            if (i > 600) {
                Log.d(TAG, "not supported idr dur=" + i);
                return false;
            }
            this.mPropertySlowVideoMode = z;
            return true;
        }
    }

    public boolean setClipVolume(int i) {
        if (i < 0 || i > 200) {
            return false;
        }
        this.m_ClipVolume = i;
        setProjectUpdateSignal(true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setCollageDrawInfoID(String str) {
        this.mCollageDrawInfoID = str;
    }

    public void setColorEffect(nexColorEffect nexcoloreffect) {
        this.mColorEffect = nexcoloreffect;
        setProjectUpdateSignal(true);
    }

    public boolean setContrast(int i) {
        if (i < -255 || i > 255) {
            return false;
        }
        this.m_Contrast = i;
        setProjectUpdateSignal(true);
        return true;
    }

    public void setCustomLUTA(int i) {
        this.mCustomLUT_A = i;
    }

    public void setCustomLUTB(int i) {
        this.mCustomLUT_B = i;
    }

    public void setCustomLUTPower(int i) {
        this.mCustomLUT_Power = i;
    }

    /* access modifiers changed from: package-private */
    public void setFaceDetectProcessed(boolean z, Rect rect) {
        this.mFacedetectProcessed = true;
        this.mFaceDetected = z ? 1 : 0;
        this.mFaceRect = rect;
    }

    public void setImageClipDuration(int i) {
        if (this.mDuration != i) {
            setProjectUpdateSignal(false);
        }
        this.mDuration = i;
    }

    public void setMainThumbnail(Bitmap bitmap) {
        WeakReference<Bitmap> weakReference = this.mSingleThumbnail;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.mSingleThumbnail = new WeakReference<>(bitmap);
    }

    /* access modifiers changed from: protected */
    public void setMotionTrackedVideo(boolean z) {
        this.isMotionTrackedVideo = z;
    }

    /* access modifiers changed from: protected */
    public void setProjectUpdateSignal(boolean z) {
        b bVar;
        if (this.mProjectAttachment && (bVar = this.mObserver) != null) {
            bVar.updateTimeLine(false);
        }
    }

    public void setRotateDegree(int i) {
        if (i >= 360) {
            i = 0;
        }
        this.mRotate = i;
        nexCrop nexcrop = this.mCrop;
        if (nexcrop != null) {
            nexcrop.setRotate(this.mRotate);
        }
        setProjectUpdateSignal(true);
    }

    public boolean setSaturation(int i) {
        if (i < -255 || i > 255) {
            return false;
        }
        this.m_Saturation = i;
        setProjectUpdateSignal(true);
        return true;
    }

    public boolean setSolidColor(int i) {
        if (!isSolid()) {
            return false;
        }
        this.mPath = String.format("@solid:%08X.jpg", new Object[]{Integer.valueOf(i)});
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setTemplateAudioPos(int i) {
        this.mTemplateAudioPos = i;
    }

    /* access modifiers changed from: package-private */
    public void setTemplateEffectID(int i) {
        this.mTemplateEffectID = i;
    }

    /* access modifiers changed from: package-private */
    public void setTemplateOverlappedTransition(boolean z) {
        this.mOverlappedTransition = z;
    }

    @Deprecated
    public void setVignetteEffect(boolean z) {
        this.mVignette = z;
    }

    @Deprecated
    public void setVoiceChangerFactor(int i) {
        if (this.mInfo.mClipType == 4 || this.mInfo.mClipType == 3) {
            getAudioEdit().setVoiceChangerFactor(i);
        }
    }
}
