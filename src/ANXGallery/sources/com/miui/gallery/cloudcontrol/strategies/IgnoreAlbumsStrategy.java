package com.miui.gallery.cloudcontrol.strategies;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.util.MiscUtil;
import java.util.ArrayList;
import java.util.List;

public class IgnoreAlbumsStrategy extends BaseStrategy {
    @SerializedName("patterns")
    private List<String> mPatterns;
    @SerializedName("version")
    private int mVersion;

    public void doAdditionalProcessing() {
        if (MiscUtil.isValid(this.mPatterns)) {
            ArrayList arrayList = new ArrayList();
            for (String next : this.mPatterns) {
                if (!TextUtils.isEmpty(next)) {
                    arrayList.add(next);
                }
            }
            if (arrayList.size() < this.mPatterns.size()) {
                this.mPatterns.clear();
                this.mPatterns.addAll(arrayList);
            }
        }
    }

    public List<String> getPatterns() {
        return this.mPatterns;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public String toString() {
        return "IgnoreAlbumsStrategy{mVersion" + this.mVersion + "mPatterns=" + this.mPatterns + '}';
    }
}
