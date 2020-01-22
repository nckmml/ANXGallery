package com.miui.gallery.xmstreaming;

import java.util.List;

public class XmsEffect extends XmsObject {
    private List<XmsAnimation> animation;
    private String name;

    public List<XmsAnimation> getAnimation() {
        return this.animation;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    public void mapData() {
        super.mapData();
        this.m_attachmentMap.put("name", this.name);
        this.m_attachmentMap.put("animation", this.animation);
        for (XmsAnimation mapData : this.animation) {
            mapData.mapData();
        }
    }

    public void setAnimation(List<XmsAnimation> list) {
        this.animation = list;
    }

    public void setName(String str) {
        this.name = str;
    }
}
