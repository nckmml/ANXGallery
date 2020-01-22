package com.miui.gallery.editor.photo.core.imports.sticker;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.imports.sticker.StickerEditorView;

class StickerRenderData extends RenderData {
    public static final Parcelable.Creator<StickerRenderData> CREATOR = new Parcelable.Creator<StickerRenderData>() {
        public StickerRenderData createFromParcel(Parcel parcel) {
            return new StickerRenderData(parcel);
        }

        public StickerRenderData[] newArray(int i) {
            return new StickerRenderData[i];
        }
    };
    StickerEditorView.StickerEntry mEntry;

    protected StickerRenderData(Parcel parcel) {
        super(parcel);
        this.mEntry = (StickerEditorView.StickerEntry) parcel.readParcelable(StickerEditorView.StickerEntry.class.getClassLoader());
    }

    public StickerRenderData(StickerEditorView.StickerEntry stickerEntry) {
        this.mEntry = stickerEntry;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mEntry, i);
    }
}
