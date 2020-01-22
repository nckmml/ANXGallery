package com.miui.gallery.editor.photo.core.imports.miuibeauty;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.filtersdk.beauty.BeautyParameterType;

public class MiuiBeautyEffect implements Parcelable {
    public static final Parcelable.Creator<MiuiBeautyEffect> CREATOR = new Parcelable.Creator<MiuiBeautyEffect>() {
        public MiuiBeautyEffect createFromParcel(Parcel parcel) {
            return new MiuiBeautyEffect(parcel);
        }

        public MiuiBeautyEffect[] newArray(int i) {
            return new MiuiBeautyEffect[i];
        }
    };
    public BeautyParameterType mBeautyType;
    public String mName;
    public int mOrdinal;

    /* renamed from: com.miui.gallery.editor.photo.core.imports.miuibeauty.MiuiBeautyEffect$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$filtersdk$beauty$BeautyParameterType = new int[BeautyParameterType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$filtersdk$beauty$BeautyParameterType[BeautyParameterType.WHITEN_STRENGTH.ordinal()] = 1;
            $SwitchMap$com$miui$filtersdk$beauty$BeautyParameterType[BeautyParameterType.SMOOTH_STRENGTH.ordinal()] = 2;
        }
    }

    protected MiuiBeautyEffect(Parcel parcel) {
        this.mOrdinal = parcel.readInt();
        this.mName = parcel.readString();
        this.mBeautyType = BeautyParameterType.valueOf(parcel.readString());
    }

    public MiuiBeautyEffect(String str, int i, BeautyParameterType beautyParameterType) {
        this.mOrdinal = i;
        this.mName = str;
        this.mBeautyType = beautyParameterType;
    }

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r0 = com.miui.gallery.editor.photo.core.imports.miuibeauty.MiuiBeautyEffect.AnonymousClass2.$SwitchMap$com$miui$filtersdk$beauty$BeautyParameterType[r4.mBeautyType.ordinal()];
     */
    public boolean needFace() {
        int i;
        return (this.mBeautyType == null || i == 1 || i == 2) ? false : true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mOrdinal);
        parcel.writeString(this.mName);
        parcel.writeString(this.mBeautyType.name());
    }
}
