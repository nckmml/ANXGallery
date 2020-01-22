package com.miui.gallery.xmstreaming;

import com.meicam.sdk.NvsFxDescription;

public class XmsAnimation extends XmsObject {
    private String paramName;
    private String value;

    public String getParamName() {
        return this.paramName;
    }

    public String getValue() {
        return this.value;
    }

    /* access modifiers changed from: protected */
    public void mapData() {
        this.m_attachmentMap.put(NvsFxDescription.ParamInfoObject.PARAM_NAME, this.paramName);
        this.m_attachmentMap.put("value", this.value);
    }

    public void setParamName(String str) {
        this.paramName = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
