package com.miui.gallery.editor.photo.core.imports.text.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.imports.text.typeface.TextStyle;
import com.miui.gallery.editor.photo.core.imports.text.utils.AutoLineLayout;

public class TextStatusData implements Parcelable {
    public static final Parcelable.Creator<TextStatusData> CREATOR = new Parcelable.Creator<TextStatusData>() {
        public TextStatusData createFromParcel(Parcel parcel) {
            return new TextStatusData(parcel);
        }

        public TextStatusData[] newArray(int i) {
            return new TextStatusData[i];
        }
    };
    public int color;
    public String text;
    public AutoLineLayout.TextAlignment textAlignment;
    public boolean textBold;
    public boolean textShadow;
    public TextStyle textStyle;
    public float transparentProgress;

    public TextStatusData() {
    }

    protected TextStatusData(Parcel parcel) {
        this.color = parcel.readInt();
        this.transparentProgress = parcel.readFloat();
        boolean z = true;
        this.textBold = parcel.readByte() != 0;
        this.textShadow = parcel.readByte() == 0 ? false : z;
        int readInt = parcel.readInt();
        this.textAlignment = readInt == -1 ? null : AutoLineLayout.TextAlignment.values()[readInt];
        this.text = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.color);
        parcel.writeFloat(this.transparentProgress);
        parcel.writeByte(this.textBold ? (byte) 1 : 0);
        parcel.writeByte(this.textShadow ? (byte) 1 : 0);
        AutoLineLayout.TextAlignment textAlignment2 = this.textAlignment;
        parcel.writeInt(textAlignment2 == null ? -1 : textAlignment2.ordinal());
        parcel.writeString(this.text);
    }
}
