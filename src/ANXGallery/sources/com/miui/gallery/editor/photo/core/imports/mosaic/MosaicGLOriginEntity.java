package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicGLEntity;

class MosaicGLOriginEntity extends MosaicGLEntity {
    public static final Parcelable.Creator<MosaicGLOriginEntity> CREATOR = new Parcelable.Creator<MosaicGLOriginEntity>() {
        public MosaicGLOriginEntity createFromParcel(Parcel parcel) {
            return new MosaicGLOriginEntity(parcel);
        }

        public MosaicGLOriginEntity[] newArray(int i) {
            return new MosaicGLOriginEntity[i];
        }
    };

    protected MosaicGLOriginEntity(Parcel parcel) {
        super(parcel);
    }

    MosaicGLOriginEntity(short s, String str, String str2) {
        super(s, str, str2, MosaicGLEntity.TYPE.ORIGIN);
    }
}
