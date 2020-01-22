package com.miui.gallery.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.util.face.FaceRegionRectF;
import java.util.Objects;

public class FaceAlbumCover extends BaseAlbumCover implements Parcelable {
    public static final Parcelable.Creator<FaceAlbumCover> CREATOR = new Parcelable.Creator<FaceAlbumCover>() {
        public FaceAlbumCover createFromParcel(Parcel parcel) {
            return new FaceAlbumCover(parcel);
        }

        public FaceAlbumCover[] newArray(int i) {
            return new FaceAlbumCover[i];
        }
    };
    public FaceRegionRectF faceRectF;

    public FaceAlbumCover() {
    }

    protected FaceAlbumCover(Parcel parcel) {
        super(parcel);
        this.faceRectF = (FaceRegionRectF) parcel.readParcelable(FaceRegionRectF.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof FaceAlbumCover) && super.equals(obj)) {
            return Objects.equals(this.faceRectF, ((FaceAlbumCover) obj).faceRectF);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(super.hashCode()), this.faceRectF});
    }

    public String toString() {
        return "FaceAlbumCover{faceRectF=" + this.faceRectF + ", coverId=" + this.coverId + ", coverPath='" + this.coverPath + '\'' + ", coverSha1='" + this.coverSha1 + '\'' + ", coverSyncState=" + this.coverSyncState + ", coverSize=" + this.coverSize + '}';
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.faceRectF, i);
    }
}
