package com.xiaomi.mistatistic.sdk.data;

import android.os.Parcel;
import android.os.Parcelable;

public class StatEventPojo implements Parcelable {
    public static final Parcelable.Creator<StatEventPojo> CREATOR = new Parcelable.Creator<StatEventPojo>() {
        /* renamed from: a */
        public StatEventPojo createFromParcel(Parcel parcel) {
            StatEventPojo statEventPojo = new StatEventPojo();
            statEventPojo.mCategory = parcel.readString();
            statEventPojo.mTimeStamp = parcel.readLong();
            statEventPojo.mKey = parcel.readString();
            statEventPojo.mType = parcel.readString();
            statEventPojo.mValue = parcel.readString();
            statEventPojo.mExtra = parcel.readString();
            statEventPojo.mAnonymous = parcel.readInt();
            return statEventPojo;
        }

        /* renamed from: a */
        public StatEventPojo[] newArray(int i) {
            return new StatEventPojo[i];
        }
    };
    public int mAnonymous;
    public String mCategory;
    public String mExtra;
    public String mKey;
    public long mTimeStamp;
    public String mType;
    public String mValue;

    public long a() {
        return 6 + ((long) this.mCategory.getBytes().length) + ((long) this.mKey.getBytes().length) + ((long) this.mType.getBytes().length) + ((long) this.mValue.getBytes().length) + ((long) this.mExtra.getBytes().length);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "Event [" + "category=" + this.mCategory + "," + "key=" + this.mKey + "," + "value=" + this.mValue + ",params=" + this.mExtra + ",anonymous=" + this.mAnonymous + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCategory);
        parcel.writeLong(this.mTimeStamp);
        parcel.writeString(this.mKey);
        parcel.writeString(this.mType);
        parcel.writeString(this.mValue);
        parcel.writeString(this.mExtra);
        parcel.writeInt(this.mAnonymous);
    }
}
