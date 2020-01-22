package com.miui.gallery.editor.photo.core.imports.text;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.imports.text.TextEditorView;

class TextRenderData extends RenderData {
    public static final Parcelable.Creator<TextRenderData> CREATOR = new Parcelable.Creator<TextRenderData>() {
        public TextRenderData createFromParcel(Parcel parcel) {
            return new TextRenderData(parcel);
        }

        public TextRenderData[] newArray(int i) {
            return new TextRenderData[i];
        }
    };
    TextEditorView.TextEntry mTextEntry;

    protected TextRenderData(Parcel parcel) {
        super(parcel);
        this.mTextEntry = (TextEditorView.TextEntry) parcel.readParcelable(TextEditorView.TextEntry.class.getClassLoader());
    }

    TextRenderData(TextEditorView.TextEntry textEntry) {
        this.mTextEntry = textEntry;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mTextEntry, i);
    }
}
