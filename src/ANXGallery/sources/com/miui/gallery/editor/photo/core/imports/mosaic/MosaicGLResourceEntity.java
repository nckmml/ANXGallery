package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicGLEntity;

class MosaicGLResourceEntity extends MosaicGLEntity {
    public static final Parcelable.Creator<MosaicGLResourceEntity> CREATOR = new Parcelable.Creator<MosaicGLResourceEntity>() {
        public MosaicGLResourceEntity createFromParcel(Parcel parcel) {
            return new MosaicGLResourceEntity(parcel);
        }

        public MosaicGLResourceEntity[] newArray(int i) {
            return new MosaicGLResourceEntity[i];
        }
    };
    public final String mResourcePath;
    public final String mTileMode;

    protected MosaicGLResourceEntity(Parcel parcel) {
        super(parcel);
        this.mResourcePath = parcel.readString();
        this.mTileMode = parcel.readString();
    }

    MosaicGLResourceEntity(short s, String str, String str2, String str3, String str4) {
        super(s, str, str2, MosaicGLEntity.TYPE.RESOURCE);
        this.mResourcePath = str3;
        this.mTileMode = str4;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mResourcePath);
        parcel.writeString(this.mTileMode);
    }
}
