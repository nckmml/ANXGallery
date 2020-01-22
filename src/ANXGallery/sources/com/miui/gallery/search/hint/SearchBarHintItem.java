package com.miui.gallery.search.hint;

import com.miui.gallery.search.widget.bannerView.BaseBannerItemData;

public class SearchBarHintItem extends BaseBannerItemData {
    private String mHintText;
    private String mQueryText;

    public SearchBarHintItem(String str, String str2) {
        this(str, str2, -1);
    }

    public SearchBarHintItem(String str, String str2, int i) {
        this.mHintText = str;
        this.mQueryText = str2;
        setDisplayDuration(i);
    }

    public String getHintText() {
        return this.mHintText;
    }

    public String getQueryText() {
        return this.mQueryText;
    }

    public String toString() {
        return super.toString() + ",hintText=" + this.mHintText + ",queryText=" + this.mQueryText;
    }
}
