package com.miui.mishare;

import android.content.ClipData;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class MiShareTask implements Parcelable {
    public static final Parcelable.Creator<MiShareTask> CREATOR = new Parcelable.Creator<MiShareTask>() {
        public MiShareTask createFromParcel(Parcel parcel) {
            return new MiShareTask(parcel);
        }

        public MiShareTask[] newArray(int i) {
            return new MiShareTask[i];
        }
    };
    public ClipData clipData;
    public int count;
    public RemoteDevice device;
    public int deviceX;
    public int deviceY;
    public String mimeType;
    public boolean send;
    public String taskId;
    public int tbHeight;
    public int tbWidth;

    public MiShareTask() {
    }

    protected MiShareTask(Parcel parcel) {
        this.send = parcel.readByte() != 0;
        this.taskId = parcel.readString();
        this.count = parcel.readInt();
        this.device = (RemoteDevice) parcel.readParcelable(RemoteDevice.class.getClassLoader());
        this.deviceX = parcel.readInt();
        this.deviceY = parcel.readInt();
        this.clipData = (ClipData) parcel.readParcelable(ClipData.class.getClassLoader());
        this.mimeType = parcel.readString();
        this.tbWidth = parcel.readInt();
        this.tbHeight = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof MiShareTask ? Objects.equals(this.taskId, ((MiShareTask) obj).taskId) : super.equals(obj);
    }

    public int hashCode() {
        return Objects.hashCode(this.taskId);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.send ? (byte) 1 : 0);
        parcel.writeString(this.taskId);
        parcel.writeInt(this.count);
        parcel.writeParcelable(this.device, i);
        parcel.writeInt(this.deviceX);
        parcel.writeInt(this.deviceY);
        parcel.writeParcelable(this.clipData, i);
        parcel.writeString(this.mimeType);
        parcel.writeInt(this.tbWidth);
        parcel.writeInt(this.tbHeight);
    }
}
