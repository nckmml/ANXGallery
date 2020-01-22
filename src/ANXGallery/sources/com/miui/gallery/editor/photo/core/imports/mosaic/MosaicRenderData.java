package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicGLView;

class MosaicRenderData extends RenderData {
    public static final Parcelable.Creator<MosaicRenderData> CREATOR = new Parcelable.Creator<MosaicRenderData>() {
        public MosaicRenderData createFromParcel(Parcel parcel) {
            return new MosaicRenderData(parcel);
        }

        public MosaicRenderData[] newArray(int i) {
            return new MosaicRenderData[i];
        }
    };
    MosaicGLView.MosaicEntry mMosaicEntry;

    protected MosaicRenderData(Parcel parcel) {
        super(parcel);
        this.mMosaicEntry = (MosaicGLView.MosaicEntry) parcel.readParcelable(MosaicGLView.MosaicEntry.class.getClassLoader());
    }

    MosaicRenderData(MosaicGLView.MosaicEntry mosaicEntry) {
        this.mMosaicEntry = mosaicEntry;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mMosaicEntry, i);
    }
}
