package com.miui.gallery.util.photoview;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class ItemViewInfo implements Parcelable {
    public static final Parcelable.Creator<ItemViewInfo> CREATOR = new Parcelable.Creator<ItemViewInfo>() {
        public ItemViewInfo createFromParcel(Parcel parcel) {
            return new ItemViewInfo(parcel);
        }

        public ItemViewInfo[] newArray(int i) {
            return new ItemViewInfo[i];
        }
    };
    private int mAdapterPos;
    private int mHeight;
    private int mWidth;
    private int mX;
    private int mY;

    public ItemViewInfo(int i, int i2, int i3, int i4, int i5) {
        this.mAdapterPos = i;
        this.mX = i2;
        this.mY = i3;
        this.mWidth = i4;
        this.mHeight = i5;
    }

    protected ItemViewInfo(Parcel parcel) {
        this.mAdapterPos = parcel.readInt();
        this.mX = parcel.readInt();
        this.mY = parcel.readInt();
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
    }

    public static ItemViewInfo getImageInfo(int i, int i2, View view, int i3) {
        if (view == null) {
            return null;
        }
        return new ItemViewInfo(i3, i, i2, view.getWidth(), view.getHeight());
    }

    public static ItemViewInfo getImageInfo(View view, int i) {
        if (view == null) {
            return null;
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return new ItemViewInfo(i, iArr[0], iArr[1], view.getWidth(), view.getHeight());
    }

    public int describeContents() {
        return 0;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getPosition() {
        return this.mAdapterPos;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public boolean isLocationValid() {
        return (this.mX == -1 || this.mY == -1) ? false : true;
    }

    public String toString() {
        return "mPos: " + this.mAdapterPos + " mX: " + this.mX + " mY: " + this.mY + " mWidth: " + this.mWidth + " mHeight: " + this.mHeight;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mAdapterPos);
        parcel.writeInt(this.mX);
        parcel.writeInt(this.mY);
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
    }
}
