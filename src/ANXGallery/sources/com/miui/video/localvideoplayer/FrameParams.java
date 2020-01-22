package com.miui.video.localvideoplayer;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.FileDescriptor;

public class FrameParams implements Parcelable {
    public static final Parcelable.Creator<FrameParams> CREATOR = new Parcelable.Creator<FrameParams>() {
        public FrameParams createFromParcel(Parcel parcel) {
            return new FrameParams(parcel);
        }

        public FrameParams[] newArray(int i) {
            return new FrameParams[i];
        }
    };
    private Bitmap.Config mConfig;
    private int mCount;
    private ParcelFileDescriptor mFd;
    private int mHeight;
    private int mWidth;

    private FrameParams() {
    }

    protected FrameParams(Parcel parcel) {
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
        this.mCount = parcel.readInt();
        this.mConfig = Bitmap.Config.valueOf(parcel.readString());
        this.mFd = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public Bitmap.Config getConfig() {
        return this.mConfig;
    }

    public int getCount() {
        return this.mCount;
    }

    public FileDescriptor getFileDescriptor() {
        ParcelFileDescriptor parcelFileDescriptor = this.mFd;
        if (parcelFileDescriptor != null) {
            return parcelFileDescriptor.getFileDescriptor();
        }
        return null;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
        parcel.writeInt(this.mCount);
        parcel.writeString(this.mConfig.name());
        this.mFd.writeToParcel(parcel, i);
    }
}
