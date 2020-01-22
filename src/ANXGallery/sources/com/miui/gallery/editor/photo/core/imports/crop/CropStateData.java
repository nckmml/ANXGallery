package com.miui.gallery.editor.photo.core.imports.crop;

import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.common.model.IPositionChangeData;
import com.miui.gallery.editor.photo.core.imports.obsoletes.Crop;

class CropStateData extends RenderData implements IPositionChangeData {
    public static final Parcelable.Creator<CropStateData> CREATOR = new Parcelable.Creator<CropStateData>() {
        public CropStateData createFromParcel(Parcel parcel) {
            return new CropStateData(parcel);
        }

        public CropStateData[] newArray(int i) {
            return new CropStateData[i];
        }
    };
    final Crop.ParcelableCropEntry mEntry;

    protected CropStateData(Parcel parcel) {
        super(parcel);
        this.mEntry = (Crop.ParcelableCropEntry) parcel.readParcelable(Crop.ParcelableCropEntry.class.getClassLoader());
    }

    CropStateData(Crop.ParcelableCropEntry parcelableCropEntry) {
        this.mEntry = parcelableCropEntry;
    }

    public int describeContents() {
        return 0;
    }

    public void refreshTargetAreaPosition(RectF rectF, RectF rectF2) {
        this.mEntry.refreshTargetAreaPosition(rectF, rectF2);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mEntry, i);
    }
}
