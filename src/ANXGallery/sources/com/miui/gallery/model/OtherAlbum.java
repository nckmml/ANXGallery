package com.miui.gallery.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.model.Album;
import java.util.ArrayList;

public class OtherAlbum extends Album {
    public static final Parcelable.Creator<OtherAlbum> CREATOR = new Parcelable.Creator<OtherAlbum>() {
        public OtherAlbum createFromParcel(Parcel parcel) {
            return new OtherAlbum(parcel);
        }

        public OtherAlbum[] newArray(int i) {
            return new OtherAlbum[i];
        }
    };
    private ArrayList<String> mAlbumNames;

    public OtherAlbum() {
        super(2147483641);
        super.setAlbumType(Album.AlbumType.OTHER_ALBUMS);
    }

    protected OtherAlbum(Parcel parcel) {
        super(parcel);
        this.mAlbumNames = parcel.createStringArrayList();
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<String> getAlbumNames() {
        return this.mAlbumNames;
    }

    public void setAlbumNames(ArrayList<String> arrayList) {
        this.mAlbumNames = arrayList;
    }

    public final void setAlbumType(Album.AlbumType albumType) {
        super.setAlbumType(Album.AlbumType.OTHER_ALBUMS);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringList(this.mAlbumNames);
    }
}
