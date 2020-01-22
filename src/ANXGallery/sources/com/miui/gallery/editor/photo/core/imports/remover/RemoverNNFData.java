package com.miui.gallery.editor.photo.core.imports.remover;

import android.os.MemoryFile;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.utils.MemoryFileUtils;
import com.miui.gallery.util.MiscUtil;
import java.io.FileInputStream;
import java.io.IOException;

public class RemoverNNFData implements Parcelable {
    public static final Parcelable.Creator<RemoverNNFData> CREATOR = new Parcelable.Creator<RemoverNNFData>() {
        public RemoverNNFData createFromParcel(Parcel parcel) {
            return new RemoverNNFData(parcel);
        }

        public RemoverNNFData[] newArray(int i) {
            return new RemoverNNFData[i];
        }
    };
    int count;
    ParcelFileDescriptor fileDescriptor;
    int height;
    int index;
    int length;
    MemoryFile memoryFile;
    byte[] nnf;
    int width;

    public RemoverNNFData() {
    }

    protected RemoverNNFData(Parcel parcel) {
        this.height = parcel.readInt();
        this.width = parcel.readInt();
        this.index = parcel.readInt();
        this.fileDescriptor = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.length = parcel.readInt();
        this.count = parcel.readInt();
        getDataFromParcel();
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void getDataFromParcel() {
        FileInputStream inputStream = MemoryFileUtils.getInputStream(this.fileDescriptor);
        try {
            this.nnf = new byte[this.count];
            inputStream.read(this.nnf, 0, this.count);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            MiscUtil.closeSilently(inputStream);
            throw th;
        }
        MiscUtil.closeSilently(inputStream);
    }

    public void releaseMemoryFile() {
        MemoryFile memoryFile2 = this.memoryFile;
        if (memoryFile2 != null) {
            memoryFile2.close();
        }
    }

    /* access modifiers changed from: package-private */
    public void saveDataForParcel() {
        this.count = this.nnf.length;
        this.length = this.count;
        this.memoryFile = MemoryFileUtils.createMemoryFile("remove", this.length);
        try {
            this.memoryFile.writeBytes(this.nnf, 0, 0, this.nnf.length);
            this.memoryFile.allowPurging(false);
            this.fileDescriptor = MemoryFileUtils.getParcelFileDescriptor(this.memoryFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        saveDataForParcel();
        parcel.writeInt(this.height);
        parcel.writeInt(this.width);
        parcel.writeInt(this.index);
        parcel.writeParcelable(this.fileDescriptor, i);
        parcel.writeInt(this.length);
        parcel.writeInt(this.count);
    }
}
