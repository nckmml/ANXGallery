package com.miui.gallery.ui.album.common.base;

import java.util.Comparator;

public class BaseViewBean implements Comparator<BaseViewBean> {
    protected long id;

    public int compare(BaseViewBean baseViewBean, BaseViewBean baseViewBean2) {
        return Long.compare(baseViewBean.getId(), baseViewBean2.getId());
    }

    public boolean equals(Object obj) {
        return (obj == null || !(obj instanceof BaseViewBean)) ? super.equals(obj) : ((BaseViewBean) obj).getId() == getId();
    }

    public long getId() {
        return this.id;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public void setId(long j) {
        this.id = j;
    }
}
