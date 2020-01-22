package com.miui.gallery.editor.photo.core.common.model;

import android.os.Parcel;

public abstract class FilterData extends RenderMetaData {
    public final int icon;
    public int progress;

    protected FilterData(Parcel parcel) {
        super(parcel);
        this.icon = parcel.readInt();
        this.progress = parcel.readInt();
    }

    public FilterData(short s, String str, int i) {
        super(s, str);
        this.icon = i;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        FilterData filterData = (FilterData) obj;
        return this.name.equals(filterData.name) && this.priority == filterData.priority;
    }

    public int hashCode() {
        return this.name.hashCode() + this.priority;
    }

    public abstract boolean isNone();

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.icon);
        parcel.writeInt(this.progress);
    }
}
