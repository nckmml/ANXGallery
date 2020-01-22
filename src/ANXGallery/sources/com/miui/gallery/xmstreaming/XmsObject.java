package com.miui.gallery.xmstreaming;

import java.util.HashMap;

public class XmsObject {
    protected HashMap<String, Object> m_attachmentMap = new HashMap<>();
    protected long m_internalObject = 0;

    public Object getAttachment(String str) {
        return this.m_attachmentMap.get(str);
    }

    public long getInternalObject() {
        return this.m_internalObject;
    }

    public Object getValue(String str) {
        return this.m_attachmentMap.get(str);
    }

    /* access modifiers changed from: protected */
    public void mapData() {
    }

    public void setAttachment(String str, Object obj) {
        this.m_attachmentMap.put(str, obj);
    }

    /* access modifiers changed from: protected */
    public void setInternalObject(long j) {
        this.m_internalObject = j;
    }
}
