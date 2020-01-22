package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.e;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.util.n;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.exception.InvalidRangeException;
import com.nexstreaming.nexeditorsdk.nexSaveDataFormat;
import java.io.IOException;

public final class nexClipEffect extends nexEffect implements Cloneable {
    private int mShowEndTime = 10000;
    private int mShowStartTime = 0;

    nexClipEffect() {
        this.mType = 1;
    }

    nexClipEffect(nexSaveDataFormat.nexEffectOf nexeffectof) {
        this.mID = nexeffectof.mID;
        this.mAutoID = nexeffectof.mAutoID;
        this.mName = nexeffectof.mName;
        this.mType = nexeffectof.mType;
        this.mDuration = nexeffectof.mDuration;
        this.itemMethodType = nexeffectof.itemMethodType;
        this.mIsResolveOptions = nexeffectof.mIsResolveOptions;
        this.mOptionsUpdate = nexeffectof.mOptionsUpdate;
        this.m_effectOptions = nexeffectof.m_effectOptions;
        this.mShowStartTime = nexeffectof.mShowStartTime;
        this.mShowEndTime = nexeffectof.mShowEndTime;
    }

    nexClipEffect(String str) {
        this.mID = str;
        this.mType = 2;
    }

    protected static nexClipEffect clone(nexClipEffect nexclipeffect) {
        nexClipEffect nexclipeffect2;
        try {
            nexclipeffect2 = (nexClipEffect) nexclipeffect.clone();
            try {
                nexclipeffect2.mShowEndTime = nexclipeffect.mShowEndTime;
                nexclipeffect2.mShowStartTime = nexclipeffect.mShowStartTime;
            } catch (CloneNotSupportedException e) {
                e = e;
            }
        } catch (CloneNotSupportedException e2) {
            e = e2;
            nexclipeffect2 = null;
            e.printStackTrace();
            return nexclipeffect2;
        }
        return nexclipeffect2;
    }

    public String getCategoryTitle(Context context) {
        if (this.mType == 0) {
            return "None";
        }
        if (this.mType == 1) {
            return "Theme";
        }
        f c = c.a().c(this.mID);
        if (c != null) {
            return c.getCategory().toString();
        }
        return null;
    }

    public String getDesc(Context context) {
        f c;
        if (this.mType == 0) {
            return "None Clip Effect";
        }
        if (this.mType == 1) {
            return "Theme Clip Effect";
        }
        if (context == null || (c = c.a().c(this.mID)) == null) {
            return null;
        }
        return c.getLabel().get("en");
    }

    public Bitmap getIcon() {
        f c;
        if (this.mID == null || (c = c.a().c(this.mID)) == null) {
            return null;
        }
        try {
            return e.a(a.a().b(), c, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getName(Context context) {
        if (this.mType == 0) {
            return "None Clip Effect";
        }
        if (this.mType == 1) {
            return "Theme Clip Effect";
        }
        f c = c.a().c(this.mID);
        if (c != null) {
            if (context != null) {
                this.mName = n.a(context, c.getLabel());
                if (this.mName == null) {
                    this.mName = c.getLabel().get("en");
                }
            } else {
                this.mName = c.getLabel().get("en");
            }
        }
        return this.mName;
    }

    /* access modifiers changed from: package-private */
    public nexSaveDataFormat.nexEffectOf getSaveData() {
        nexSaveDataFormat.nexEffectOf nexeffectof = new nexSaveDataFormat.nexEffectOf();
        nexeffectof.mID = this.mID;
        nexeffectof.mAutoID = this.mAutoID;
        nexeffectof.mName = this.mName;
        nexeffectof.mType = this.mType;
        nexeffectof.mDuration = this.mDuration;
        nexeffectof.itemMethodType = this.itemMethodType;
        nexeffectof.mIsResolveOptions = this.mIsResolveOptions;
        nexeffectof.mOptionsUpdate = this.mOptionsUpdate;
        nexeffectof.m_effectOptions = this.m_effectOptions;
        nexeffectof.mShowStartTime = this.mShowStartTime;
        nexeffectof.mShowEndTime = this.mShowEndTime;
        nexeffectof.mMinDuration = 0;
        nexeffectof.mMaxDuration = 0;
        nexeffectof.mEffectOffset = 0;
        nexeffectof.mEffectOverlap = 0;
        return nexeffectof;
    }

    public int getShowEndTime() {
        return this.mShowEndTime;
    }

    public int getShowStartTime() {
        return this.mShowStartTime;
    }

    public String getTitle() {
        return getTitle(0);
    }

    public void setAutoTheme() {
        this.mType = 1;
    }

    public void setEffect(String str) {
        super.setEffect(str, 2);
    }

    public void setEffectShowTime(int i, int i2) {
        if (i2 >= i) {
            this.mShowStartTime = i;
            this.mShowEndTime = i2;
            return;
        }
        throw new InvalidRangeException(i, i2);
    }

    public void setShowEndTime(int i) {
        int i2 = this.mShowStartTime;
        if (i2 <= i) {
            this.mShowEndTime = i;
            return;
        }
        throw new InvalidRangeException(i2, i);
    }

    public void setShowStartTime(int i) {
        int i2 = this.mShowEndTime;
        if (i2 >= i) {
            this.mShowStartTime = i;
            return;
        }
        throw new InvalidRangeException(i, i2);
    }

    public void setTitle(String str) {
        this.mUpdated = true;
        setTitle(0, str);
    }
}
