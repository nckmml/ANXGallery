package com.miui.gallery.editor.photo.core.imports.doodle;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.imports.doodle.DoodleEditorView;

class DoodleRenderData extends RenderData {
    public static final Parcelable.Creator<DoodleRenderData> CREATOR = new Parcelable.Creator<DoodleRenderData>() {
        public DoodleRenderData createFromParcel(Parcel parcel) {
            return new DoodleRenderData(parcel);
        }

        public DoodleRenderData[] newArray(int i) {
            return new DoodleRenderData[i];
        }
    };
    DoodleEditorView.DoodleEntry mDoodleEntry;

    protected DoodleRenderData(Parcel parcel) {
        super(parcel);
        this.mDoodleEntry = (DoodleEditorView.DoodleEntry) parcel.readParcelable(DoodleEditorView.DoodleEntry.class.getClassLoader());
    }

    DoodleRenderData(DoodleEditorView.DoodleEntry doodleEntry) {
        this.mDoodleEntry = doodleEntry;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mDoodleEntry, i);
    }
}
