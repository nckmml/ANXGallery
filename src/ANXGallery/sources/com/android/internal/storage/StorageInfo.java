package com.android.internal.storage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;

public class StorageInfo {
    private boolean isMounted;
    private boolean isPrimary;
    private boolean isSd;
    private boolean isUsb;
    private boolean isVisible;
    private boolean isXspace;
    private String mDescription;
    private String mPath;
    private Object mWrapped;

    public StorageInfo(String str) {
        this.mPath = str;
    }

    @TargetApi(24)
    public Intent createAccessIntent(Context context) {
        Object obj = this.mWrapped;
        StorageVolume buildStorageVolume = obj instanceof VolumeInfo ? ((VolumeInfo) obj).buildStorageVolume(context, context.getUserId(), false) : obj instanceof StorageVolume ? (StorageVolume) obj : null;
        if (buildStorageVolume != null) {
            return buildStorageVolume.createAccessIntent((String) null);
        }
        return null;
    }

    public String getDescription() {
        String str = this.mDescription;
        return str == null ? "" : str;
    }

    public String getPath() {
        return this.mPath;
    }

    public boolean isMounted() {
        return this.isMounted;
    }

    public boolean isPrimary() {
        return this.isPrimary;
    }

    public boolean isSd() {
        return this.isSd;
    }

    public boolean isUsb() {
        return this.isUsb;
    }

    public boolean isXspace() {
        return this.isXspace;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setMounted(boolean z) {
        this.isMounted = z;
    }

    /* access modifiers changed from: package-private */
    public void setPath(String str) {
        this.mPath = str;
    }

    /* access modifiers changed from: package-private */
    public void setPrimary(boolean z) {
        this.isPrimary = z;
    }

    /* access modifiers changed from: package-private */
    public void setSd(boolean z) {
        this.isSd = z;
    }

    /* access modifiers changed from: package-private */
    public void setUsb(boolean z) {
        this.isUsb = z;
    }

    /* access modifiers changed from: package-private */
    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    /* access modifiers changed from: package-private */
    public void setWrapped(Object obj) {
        this.mWrapped = obj;
    }

    public void setXspace(boolean z) {
        this.isXspace = z;
    }

    public String toString() {
        return "Storage{ " + "mPath=" + this.mPath + "," + "mDescription=" + this.mDescription + "," + "isMounted=" + this.isMounted + "," + "isPrimary=" + this.isPrimary + "," + "isSD=" + this.isSd + "," + "isUsb=" + this.isUsb + "," + "isXspace=" + this.isXspace + " }";
    }
}
