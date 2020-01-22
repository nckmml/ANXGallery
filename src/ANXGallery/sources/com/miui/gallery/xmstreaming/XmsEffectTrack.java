package com.miui.gallery.xmstreaming;

import java.util.List;

public class XmsEffectTrack extends XmsObject {
    private int clipDuration;
    private int clipStart;
    private List<XmsEffect> effect;

    public int getClipDuration() {
        return this.clipDuration;
    }

    public int getClipStart() {
        return this.clipStart;
    }

    public List<XmsEffect> getEffect() {
        return this.effect;
    }

    /* access modifiers changed from: protected */
    public void mapData() {
        this.m_attachmentMap.put("effect", this.effect);
        for (XmsEffect mapData : this.effect) {
            mapData.mapData();
        }
    }

    public void setClipDuration(int i) {
        this.clipDuration = i;
    }

    public void setClipStart(int i) {
        this.clipStart = i;
    }

    public void setEffect(List<XmsEffect> list) {
        this.effect = list;
    }
}
