package com.nexstreaming.nexeditorsdk;

import android.util.Log;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import com.nexstreaming.nexeditorsdk.exception.InvalidRangeException;
import com.nexstreaming.nexeditorsdk.nexSaveDataFormat;

public final class nexVideoClipEdit implements Cloneable {
    @Deprecated
    public static int kAutoTrim_Divided = 1;
    @Deprecated
    public static int kAutoTrim_Interval = 2;
    public static final int kSpeedControl_MaxValue = 1600;
    public static final int kSpeedControl_MinValue = 3;
    private nexClip mClip;
    int mFreezeDuration = 0;
    private int mMasterSpeedControl = 100;
    int mTrimEndDuration;
    int mTrimStartDuration;
    boolean mUpdated;

    private nexVideoClipEdit() {
    }

    private nexVideoClipEdit(nexClip nexclip) {
        this.mClip = nexclip;
    }

    nexVideoClipEdit(nexClip nexclip, nexSaveDataFormat.nexVideoClipEditOf nexvideoclipeditof) {
        this.mTrimStartDuration = nexvideoclipeditof.mTrimStartDuration;
        this.mTrimEndDuration = nexvideoclipeditof.mTrimEndDuration;
        this.mMasterSpeedControl = nexvideoclipeditof.mMasterSpeedControl;
        this.mClip = nexclip;
    }

    protected static nexVideoClipEdit clone(nexVideoClipEdit nexvideoclipedit) {
        try {
            return (nexVideoClipEdit) nexvideoclipedit.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    static nexVideoClipEdit getnexVideoClipEdit(nexClip nexclip) {
        if (nexclip.getClipType() != 4) {
            return null;
        }
        return new nexVideoClipEdit(nexclip);
    }

    private int speedControlTab(int i) {
        int[] iArr = {3, 6, 13, 25, 50, 75, 100, BaiduSceneResult.TRAVEL_OTHER, 150, 175, 200, 400, 800, kSpeedControl_MaxValue};
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] >= i) {
                return iArr[i2];
            }
        }
        return kSpeedControl_MaxValue;
    }

    @Deprecated
    public void addTrim(int i, int i2, int i3) {
    }

    public void clearTrim() {
        this.mTrimStartDuration = 0;
        this.mTrimEndDuration = 0;
        this.mUpdated = true;
        this.mClip.setProjectUpdateSignal(false);
    }

    public int getDuration() {
        int totalTime = (this.mTrimStartDuration == 0 && this.mTrimEndDuration == 0) ? this.mClip.getTotalTime() : (this.mClip.getTotalTime() - this.mTrimStartDuration) - this.mTrimEndDuration;
        int i = this.mMasterSpeedControl;
        if (i != 100) {
            totalTime = i == 2 ? totalTime * 50 : i == 3 ? totalTime * 32 : i == 6 ? totalTime * 16 : i == 13 ? totalTime * 8 : Math.round((float) ((totalTime * 100) / i));
        }
        if (totalTime < 500) {
            Log.w("nexVideoClipEdit", "clip duration under 500! duration=" + totalTime + ", speed=" + this.mMasterSpeedControl + ", trim_start=" + this.mTrimStartDuration + ", trim_end=" + this.mTrimEndDuration);
        }
        return totalTime + this.mFreezeDuration;
    }

    public int getEndTrimTime() {
        return this.mClip.getTotalTime() - this.mTrimEndDuration;
    }

    /* access modifiers changed from: package-private */
    public nexSaveDataFormat.nexVideoClipEditOf getSaveData() {
        nexSaveDataFormat.nexVideoClipEditOf nexvideoclipeditof = new nexSaveDataFormat.nexVideoClipEditOf();
        nexvideoclipeditof.mTrimStartDuration = this.mTrimStartDuration;
        nexvideoclipeditof.mTrimEndDuration = this.mTrimEndDuration;
        nexvideoclipeditof.mMasterSpeedControl = this.mMasterSpeedControl;
        return nexvideoclipeditof;
    }

    public int getSpeedControl() {
        return this.mMasterSpeedControl;
    }

    public int getStartTrimTime() {
        return this.mTrimStartDuration;
    }

    @Deprecated
    public int getTrimCount() {
        return 0;
    }

    @Deprecated
    public int removeTrim(int i) {
        return -1;
    }

    @Deprecated
    public int setAutoTrim(int i, int i2) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void setFreezeDuration(int i) {
        this.mFreezeDuration = i;
    }

    public void setSpeedControl(int i) {
        if (this.mClip.getAudioOnOff()) {
            i = speedControlTab(i);
        }
        if (this.mMasterSpeedControl != i) {
            this.mUpdated = true;
            this.mClip.setProjectUpdateSignal(false);
            this.mMasterSpeedControl = i;
        }
    }

    public void setTrim(int i, int i2) {
        if (i2 > i) {
            this.mTrimStartDuration = i;
            this.mTrimEndDuration = this.mClip.getTotalTime() - i2;
            if (this.mTrimEndDuration < 0 || this.mTrimStartDuration < 0) {
                throw new InvalidRangeException(this.mTrimStartDuration, this.mTrimEndDuration);
            }
            this.mUpdated = true;
            this.mClip.setProjectUpdateSignal(false);
            return;
        }
        throw new InvalidRangeException(i, i2);
    }
}
