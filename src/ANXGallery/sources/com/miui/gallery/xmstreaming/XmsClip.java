package com.miui.gallery.xmstreaming;

import com.miui.gallery.xmstreaming.TimelineTheme;

public class XmsClip extends XmsObject {
    private String path;

    public XmsClip(String str) {
        this.path = str;
    }

    private native void nativeSetTrans(long j, int i, String str, int i2, int i3, String str2, String str3, Object obj);

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setTrans(int i, TimelineTheme.ImageTrans imageTrans, XmsEffectTrack xmsEffectTrack) {
        if (xmsEffectTrack != null) {
            xmsEffectTrack.mapData();
        }
        nativeSetTrans(this.m_internalObject, i, imageTrans.transName, imageTrans.transLen, imageTrans.cutPoint, imageTrans.effects, imageTrans.para, xmsEffectTrack);
    }
}
