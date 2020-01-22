package com.miui.gallery.editor.photo.core.imports.filter;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.filtersdk.filter.base.BaseOriginalFilter;
import com.miui.gallery.editor.photo.core.common.model.AdjustData;
import com.miui.gallery.editor.photo.core.imports.filter.adjust.GPUBrightnessFilter;
import com.miui.gallery.editor.photo.core.imports.filter.adjust.GPUContrastFilter;
import com.miui.gallery.editor.photo.core.imports.filter.adjust.GPUImageSaturationFilter;
import com.miui.gallery.editor.photo.core.imports.filter.adjust.GPUImageSharpenFilter;
import com.miui.gallery.editor.photo.core.imports.filter.adjust.GPUImageVignetteFilter;

public class FilterAdjust extends AdjustData {
    public static final Parcelable.Creator<FilterAdjust> CREATOR = new Parcelable.Creator<FilterAdjust>() {
        public FilterAdjust createFromParcel(Parcel parcel) {
            return new FilterAdjust(parcel);
        }

        public FilterAdjust[] newArray(int i) {
            return new FilterAdjust[i];
        }
    };
    private int mId;
    private boolean mIsMid;

    public FilterAdjust(int i, short s, String str, int i2, boolean z) {
        super(s, str, i2);
        this.mId = i;
        this.mIsMid = z;
    }

    protected FilterAdjust(Parcel parcel) {
        super(parcel);
        this.mId = parcel.readInt();
        this.mIsMid = parcel.readByte() != 0;
    }

    public int describeContents() {
        return 0;
    }

    public int getMax() {
        return 100;
    }

    public int getMin() {
        return this.mIsMid ? -100 : 0;
    }

    public BaseOriginalFilter instantiate() {
        int min = ((this.progress - getMin()) * getMax()) / (getMax() - getMin());
        int i = this.mId;
        if (i == 0) {
            return new GPUBrightnessFilter(min);
        }
        if (i == 1) {
            return new GPUContrastFilter(min);
        }
        if (i == 2) {
            return new GPUImageSaturationFilter(min);
        }
        if (i == 3) {
            return new GPUImageSharpenFilter(min);
        }
        if (i == 4) {
            return new GPUImageVignetteFilter(min);
        }
        throw new IllegalArgumentException("not support adjust id: " + this.mId);
    }

    public boolean isMid() {
        return this.mIsMid;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mId);
        parcel.writeByte(this.mIsMid ? (byte) 1 : 0);
    }
}
