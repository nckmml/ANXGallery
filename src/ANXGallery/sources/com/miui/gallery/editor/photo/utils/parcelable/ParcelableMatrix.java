package com.miui.gallery.editor.photo.utils.parcelable;

import android.graphics.Matrix;
import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableMatrix extends Matrix implements Parcelable {
    public static final Parcelable.Creator<ParcelableMatrix> CREATOR = new Parcelable.Creator<ParcelableMatrix>() {
        public ParcelableMatrix createFromParcel(Parcel parcel) {
            return new ParcelableMatrix(parcel);
        }

        public ParcelableMatrix[] newArray(int i) {
            return new ParcelableMatrix[i];
        }
    };

    public ParcelableMatrix() {
    }

    protected ParcelableMatrix(Parcel parcel) {
        float[] fArr = new float[9];
        parcel.readFloatArray(fArr);
        setValues(fArr);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        float[] fArr = new float[9];
        getValues(fArr);
        parcel.writeFloatArray(fArr);
    }
}
