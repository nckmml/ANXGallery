package com.miui.gallery.xmstreaming;

public class XmsTimeline extends XmsObject {
    private static final String TAG = "XmsTimeline";
    private int mPreferHeight;
    private int mPreferWidth;

    private native XmsClip nativeAddClip(long j, String str);

    private native void nativeClearClip(long j);

    private native XmsClip nativeInsertClip(long j, String str, XmsClip xmsClip);

    private native void nativeSetFilter(long j, String str);

    private native void nativeSetMask(long j, String str);

    private native void nativeSetMusic(long j, String str);

    private native void nativeSetSticker(long j, String str, String str2);

    private native void nativeSetTitle(long j, String str, String str2);

    private native void nativeSetTransType(long j, int i);

    public XmsClip appendClip(String str) {
        return nativeAddClip(this.m_internalObject, str);
    }

    public void clearClip() {
        nativeClearClip(this.m_internalObject);
    }

    public int getPreferHeight() {
        return this.mPreferHeight;
    }

    public int getPreferWidth() {
        return this.mPreferWidth;
    }

    public XmsClip insertClip(String str) {
        return nativeInsertClip(this.m_internalObject, str, new XmsClip(str));
    }

    public void setFilter(String str) {
        nativeSetFilter(this.m_internalObject, str);
    }

    public void setMask(String str) {
        nativeSetMask(this.m_internalObject, str);
    }

    public void setMusic(String str) {
        nativeSetMusic(this.m_internalObject, str);
    }

    public void setPreferHeight(int i) {
        this.mPreferHeight = i;
    }

    public void setPreferWidth(int i) {
        this.mPreferWidth = i;
    }

    public void setSticker(String str, String str2) {
        nativeSetSticker(this.m_internalObject, str, str2);
    }

    public void setTitle(String str, String str2) {
        nativeSetTitle(this.m_internalObject, str, str2);
    }

    public void setTransType(int i) {
        nativeSetTransType(this.m_internalObject, i);
    }
}
