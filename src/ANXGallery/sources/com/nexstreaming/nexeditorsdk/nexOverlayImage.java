package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.nexstreaming.app.common.nexasset.overlay.OverlayAsset;
import com.nexstreaming.app.common.nexasset.overlay.OverlayAssetFactory;
import com.nexstreaming.app.common.util.FileType;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.exception.ClipIsNotVideoException;
import com.nexstreaming.nexeditorsdk.exception.nexSDKException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public class nexOverlayImage implements Cloneable {
    private static final String TAG = "nexOverlayImage";
    public static final int kOverlayType_Asset = 6;
    public static final int kOverlayType_ResourceImage = 1;
    public static final int kOverlayType_RunTimeImage = 4;
    public static final int kOverlayType_SolidColorImage = 5;
    public static final int kOverlayType_UserImage = 2;
    public static final int kOverlayType_UserVideo = 3;
    private static Map<String, nexOverlayImage> sOverlayImageItems;
    private int anchorPoint = 4;
    private OverlayAsset cachedOverlayAsset;
    private boolean mAssetManager;
    private int mBitmapHeight;
    private int mBitmapInSample = 1;
    private String mBitmapPath;
    private int mBitmapWidth;
    private int mHeight;
    private String mId;
    private runTimeMakeBitMap mMakeBitMap;
    public int mRecommandDuration = -1;
    protected int mResourceId;
    private int mSolidColor;
    private int mType;
    protected boolean mUpdate = false;
    private boolean mUpdateInfo;
    private VideoClipInfo mVideoClipInfo;
    private int mWidth;

    public static class VideoClipInfo implements Cloneable {
        /* access modifiers changed from: private */
        public boolean mHasAudio;
        /* access modifiers changed from: private */
        public boolean mHasVideo;
        /* access modifiers changed from: private */
        public int mHeight;
        /* access modifiers changed from: private */
        public String mPath;
        /* access modifiers changed from: private */
        public int mTotalTime;
        /* access modifiers changed from: private */
        public int mWidth;

        protected static VideoClipInfo clone(VideoClipInfo videoClipInfo) {
            VideoClipInfo videoClipInfo2;
            try {
                videoClipInfo2 = (VideoClipInfo) videoClipInfo.clone();
                try {
                    videoClipInfo2.mPath = videoClipInfo.mPath;
                } catch (CloneNotSupportedException e) {
                    e = e;
                }
            } catch (CloneNotSupportedException e2) {
                e = e2;
                videoClipInfo2 = null;
                e.printStackTrace();
                return videoClipInfo2;
            }
            return videoClipInfo2;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public String getPath() {
            return this.mPath;
        }

        public int getTotalTime() {
            return this.mTotalTime;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public boolean hasAudio() {
            return this.mHasAudio;
        }

        public boolean hasVideo() {
            return this.mHasVideo;
        }
    }

    public interface runTimeMakeBitMap {
        int getBitmapID();

        boolean isAniMate();

        Bitmap makeBitmap();
    }

    nexOverlayImage(String str) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = null;
        this.mBitmapPath = null;
        this.mUpdateInfo = false;
        this.mBitmapWidth = this.mWidth;
        this.mBitmapHeight = this.mHeight;
        this.mVideoClipInfo = null;
        this.mSolidColor = 0;
    }

    public nexOverlayImage(String str, int i) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = null;
        this.mWidth = 32;
        this.mHeight = 18;
        this.mBitmapPath = null;
        this.mBitmapWidth = 32;
        this.mBitmapHeight = 18;
        this.mSolidColor = i;
        this.mVideoClipInfo = null;
        this.mUpdateInfo = true;
    }

    nexOverlayImage(String str, int i, int i2, runTimeMakeBitMap runtimemakebitmap) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = runtimemakebitmap;
        this.mBitmapPath = null;
        this.mWidth = i;
        this.mHeight = i2;
        this.mUpdateInfo = true;
        this.mBitmapWidth = this.mWidth;
        this.mBitmapHeight = this.mHeight;
        this.mVideoClipInfo = null;
        this.mSolidColor = 0;
    }

    nexOverlayImage(String str, int i, int i2, String str2) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = null;
        this.mBitmapPath = str2;
        this.mWidth = i;
        this.mHeight = i2;
        this.mUpdateInfo = true;
        this.mBitmapWidth = this.mWidth;
        this.mBitmapHeight = this.mHeight;
        this.mVideoClipInfo = null;
        this.mSolidColor = 0;
        initSample();
    }

    public nexOverlayImage(String str, Context context, int i) {
        this.mId = str;
        this.mResourceId = i;
        this.mUpdateInfo = true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getApplicationContext().getResources(), this.mResourceId, options);
        this.mWidth = options.outWidth;
        this.mHeight = options.outHeight;
        this.mMakeBitMap = null;
        this.mBitmapPath = null;
        this.mBitmapWidth = 0;
        this.mBitmapHeight = 0;
        this.mVideoClipInfo = null;
        this.mSolidColor = 0;
    }

    public nexOverlayImage(String str, nexClip nexclip) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = null;
        this.mBitmapWidth = 0;
        this.mBitmapHeight = 0;
        if (nexclip.getClipType() == 4) {
            this.mVideoClipInfo = new VideoClipInfo();
            boolean unused = this.mVideoClipInfo.mHasAudio = nexclip.hasAudio();
            boolean unused2 = this.mVideoClipInfo.mHasVideo = nexclip.hasVideo();
            String unused3 = this.mVideoClipInfo.mPath = nexclip.getRealPath();
            int unused4 = this.mVideoClipInfo.mTotalTime = nexclip.getTotalTime();
            int unused5 = this.mVideoClipInfo.mWidth = nexclip.getWidth();
            int unused6 = this.mVideoClipInfo.mHeight = nexclip.getHeight();
            this.mSolidColor = 0;
            this.mBitmapPath = null;
        } else if (nexclip.getClipType() != 1) {
            this.mVideoClipInfo = null;
            this.mSolidColor = 0;
            this.mBitmapPath = null;
            throw new nexSDKException("Audio not supported!");
        } else if (nexclip.isSolid()) {
            this.mVideoClipInfo = null;
            this.mSolidColor = nexclip.getSolidColor();
            this.mBitmapPath = null;
        } else {
            this.mSolidColor = 0;
            this.mVideoClipInfo = null;
            this.mBitmapPath = nexclip.getRealPath();
            initSample();
        }
    }

    public nexOverlayImage(String str, runTimeMakeBitMap runtimemakebitmap) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = runtimemakebitmap;
        this.mBitmapPath = null;
        this.mBitmapWidth = 0;
        this.mBitmapHeight = 0;
        this.mVideoClipInfo = null;
        this.mSolidColor = 0;
    }

    public nexOverlayImage(String str, String str2) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = null;
        this.mBitmapPath = str2;
        this.mUpdateInfo = true;
        this.mBitmapWidth = this.mWidth;
        this.mBitmapHeight = this.mHeight;
        this.mVideoClipInfo = null;
        this.mSolidColor = 0;
        FileType fromFile = FileType.fromFile(str2);
        if (fromFile == null || !fromFile.isImage()) {
            this.mBitmapPath = null;
            nexClip supportedClip = nexClip.getSupportedClip(str2);
            if (supportedClip == null) {
                throw new ClipIsNotVideoException();
            } else if (supportedClip.getClipType() == 4) {
                this.mVideoClipInfo = new VideoClipInfo();
                boolean unused = this.mVideoClipInfo.mHasAudio = supportedClip.hasAudio();
                boolean unused2 = this.mVideoClipInfo.mHasVideo = supportedClip.hasVideo();
                String unused3 = this.mVideoClipInfo.mPath = supportedClip.getRealPath();
                int unused4 = this.mVideoClipInfo.mTotalTime = supportedClip.getTotalTime();
                int unused5 = this.mVideoClipInfo.mWidth = supportedClip.getWidth();
                int unused6 = this.mVideoClipInfo.mHeight = supportedClip.getHeight();
            } else {
                throw new ClipIsNotVideoException();
            }
        } else {
            this.mBitmapPath = str2;
            initSample();
        }
    }

    nexOverlayImage(String str, boolean z) {
        this.mId = str;
        this.mResourceId = 0;
        this.mMakeBitMap = null;
        this.mBitmapPath = null;
        this.mUpdateInfo = false;
        this.mBitmapWidth = this.mWidth;
        this.mBitmapHeight = this.mHeight;
        this.mVideoClipInfo = null;
        this.mSolidColor = 0;
        this.mAssetManager = z;
    }

    static void allClearRegisterOverlayImage() {
        if (sOverlayImageItems == null) {
            sOverlayImageItems = new HashMap();
        }
        sOverlayImageItems.clear();
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 <= i2 && i4 <= i) {
            return 1;
        }
        int round = Math.round(((float) i3) / ((float) i2));
        int round2 = Math.round(((float) i4) / ((float) i));
        return round < round2 ? round : round2;
    }

    protected static nexOverlayImage clone(nexOverlayImage nexoverlayimage) {
        nexOverlayImage nexoverlayimage2;
        try {
            nexoverlayimage2 = (nexOverlayImage) nexoverlayimage.clone();
            try {
                if (nexoverlayimage.mVideoClipInfo != null) {
                    nexoverlayimage2.mVideoClipInfo = VideoClipInfo.clone(nexoverlayimage.mVideoClipInfo);
                }
                nexoverlayimage2.mId = nexoverlayimage.mId;
            } catch (CloneNotSupportedException e) {
                e = e;
                e.printStackTrace();
                return nexoverlayimage2;
            }
        } catch (CloneNotSupportedException e2) {
            e = e2;
            nexoverlayimage2 = null;
            e.printStackTrace();
            return nexoverlayimage2;
        }
        return nexoverlayimage2;
    }

    private Bitmap decodeSampledBitmapFromFile(String str, int i, int i2) {
        int i3;
        int i4;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = this.mBitmapInSample;
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            return null;
        }
        if (decodeFile.getWidth() == i || decodeFile.getHeight() == i2) {
            return decodeFile;
        }
        float width = ((float) decodeFile.getWidth()) / ((float) i2);
        float height = ((float) decodeFile.getHeight()) / ((float) i);
        if (height > width) {
            i4 = Math.round(((float) decodeFile.getWidth()) * height);
            i3 = Math.round(height * ((float) decodeFile.getHeight()));
        } else {
            i4 = Math.round(((float) decodeFile.getWidth()) * width);
            i3 = Math.round(width * ((float) decodeFile.getHeight()));
        }
        return Bitmap.createScaledBitmap(decodeFile, i4, i3, true);
    }

    static nexOverlayImage getOverlayImage(String str) {
        if (sOverlayImageItems == null) {
            sOverlayImageItems = new HashMap();
        }
        return sOverlayImageItems.get(str);
    }

    private Bitmap getSolidRect() {
        int i = this.mSolidColor;
        int[] iArr = new int[576];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = i;
        }
        return Bitmap.createBitmap(iArr, 32, 18, Bitmap.Config.ARGB_8888);
    }

    private int getType() {
        if (this.mType == 0) {
            if (this.mAssetManager) {
                this.mType = 6;
                return this.mType;
            } else if (this.mMakeBitMap != null) {
                this.mType = 4;
                return this.mType;
            } else if (this.mVideoClipInfo != null) {
                this.mType = 3;
                return this.mType;
            } else if (this.mResourceId != 0) {
                this.mType = 1;
                return this.mType;
            } else if (this.mSolidColor != 0) {
                this.mType = 5;
                return this.mType;
            } else if (this.mBitmapPath != null) {
                this.mType = 2;
                return this.mType;
            }
        }
        return this.mType;
    }

    private void initSample() {
        if (this.mBitmapPath != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(this.mBitmapPath, options);
            int i = 720;
            int i2 = 1280;
            if (options.outHeight <= options.outWidth) {
                i2 = 720;
                i = 1280;
            }
            options.inSampleSize = calculateInSampleSize(options, i, i2);
            this.mBitmapInSample = options.inSampleSize;
            BitmapFactory.decodeFile(this.mBitmapPath, options);
            this.mWidth = options.outWidth;
            this.mHeight = options.outHeight;
            this.mBitmapWidth = this.mWidth;
            this.mBitmapHeight = this.mHeight;
        }
    }

    static boolean registerOverlayImage(nexOverlayImage nexoverlayimage) {
        if (sOverlayImageItems == null) {
            sOverlayImageItems = new HashMap();
        }
        if (sOverlayImageItems.containsKey(nexoverlayimage.getId())) {
            return false;
        }
        sOverlayImageItems.put(nexoverlayimage.getId(), nexoverlayimage);
        return true;
    }

    static boolean unregisterOverlayImage(String str) {
        if (sOverlayImageItems == null) {
            sOverlayImageItems = new HashMap();
        }
        sOverlayImageItems.remove(str);
        return true;
    }

    private void updateInfo() {
        if (!this.mUpdateInfo) {
            int type = getType();
            if (type != 1) {
                if (type == 3) {
                    this.mWidth = this.mVideoClipInfo.mWidth;
                    this.mHeight = this.mVideoClipInfo.mHeight;
                    this.mUpdateInfo = true;
                } else if (type != 5) {
                    if (type != 6) {
                        Bitmap userBitmap = getUserBitmap();
                        this.mWidth = userBitmap.getWidth();
                        this.mHeight = userBitmap.getHeight();
                    } else {
                        getUserBitmap();
                        this.mWidth = this.cachedOverlayAsset.getIntrinsicWidth();
                        this.mHeight = this.cachedOverlayAsset.getIntrinsicHeight();
                    }
                }
            }
            this.mUpdateInfo = true;
        }
    }

    public int getAnchorPoint() {
        return this.anchorPoint;
    }

    /* access modifiers changed from: package-private */
    public void getBound(Rect rect) {
        updateInfo();
        int i = this.mWidth;
        rect.left = 0 - (i / 2);
        int i2 = this.mHeight;
        rect.top = 0 - (i2 / 2);
        rect.right = (i / 2) + 0;
        rect.bottom = (i2 / 2) + 0;
    }

    public int getDefaultDuration() {
        if (this.mRecommandDuration < 0) {
            int i = 0;
            if (getType() == 6) {
                try {
                    i = getOverlayAssetBitmap().getDefaultDuration();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e2) {
                    e2.printStackTrace();
                }
            }
            this.mRecommandDuration = i;
        }
        return this.mRecommandDuration;
    }

    public int getHeight() {
        updateInfo();
        return this.mHeight;
    }

    public String getId() {
        return this.mId;
    }

    /* access modifiers changed from: package-private */
    public OverlayAsset getOverlayAssetBitmap() throws IOException, XmlPullParserException {
        if (!this.mAssetManager) {
            return null;
        }
        if (this.cachedOverlayAsset == null) {
            this.cachedOverlayAsset = OverlayAssetFactory.forItem(this.mId);
        }
        return this.cachedOverlayAsset;
    }

    public int getResourceId() {
        return this.mResourceId;
    }

    /* access modifiers changed from: protected */
    public Bitmap getUserBitmap() {
        int type = getType();
        if (type == 1) {
            return BitmapFactory.decodeResource(a.a().b().getResources(), this.mResourceId);
        }
        if (type != 3) {
            if (type == 4) {
                return this.mMakeBitMap.makeBitmap();
            }
            if (type == 5) {
                return getSolidRect();
            }
            if (type != 6) {
                if (this.mBitmapWidth == 0) {
                    this.mBitmapWidth = 1280;
                }
                if (this.mBitmapHeight == 0) {
                    this.mBitmapHeight = 720;
                }
                return decodeSampledBitmapFromFile(this.mBitmapPath, this.mBitmapWidth, this.mBitmapHeight);
            }
            try {
                getOverlayAssetBitmap();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getUserBitmapID() {
        if (getType() == 4 && this.mMakeBitMap != null) {
            return this.mId + this.mMakeBitMap.getBitmapID();
        } else if (getType() != 2 || this.mBitmapPath == null) {
            return this.mId;
        } else {
            return this.mId + this.mBitmapPath.hashCode();
        }
    }

    public VideoClipInfo getVideoClipInfo() {
        return this.mVideoClipInfo;
    }

    public int getWidth() {
        updateInfo();
        return this.mWidth;
    }

    /* access modifiers changed from: package-private */
    public boolean isAniMate() {
        runTimeMakeBitMap runtimemakebitmap = this.mMakeBitMap;
        return runtimemakebitmap != null && runtimemakebitmap.isAniMate();
    }

    /* access modifiers changed from: package-private */
    public boolean isAssetManager() {
        return getType() == 6;
    }

    /* access modifiers changed from: package-private */
    public boolean isUpdated() {
        return this.mUpdate;
    }

    /* access modifiers changed from: package-private */
    public boolean isVideo() {
        return getType() == 3;
    }

    public void releaseBitmap() {
    }

    @Deprecated
    public void resizeBitmap(int i, int i2) {
        if (getType() == 2) {
            this.mBitmapWidth = i;
            this.mBitmapHeight = i2;
        }
    }

    public void setAnchorPoint(int i) {
        this.anchorPoint = i;
    }

    @Deprecated
    public void setCrop(int i, int i2) {
        if (getType() == 2) {
            this.mBitmapWidth = i;
            this.mBitmapHeight = i2;
        }
    }
}
