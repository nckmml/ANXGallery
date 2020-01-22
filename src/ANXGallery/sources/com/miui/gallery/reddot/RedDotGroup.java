package com.miui.gallery.reddot;

import java.util.List;

public class RedDotGroup extends RedDot {
    private int mMaxDisplayNum = 0;
    List<RedDot> mPriorityGroup;

    public RedDotGroup(String str, List<RedDot> list, int i) {
        super(str);
        this.mPriorityGroup = list;
        this.mMaxDisplayNum = i;
    }

    public void onClick() {
        for (RedDot onSaw : this.mPriorityGroup) {
            onSaw.onSaw();
        }
    }

    public void onSaw() {
    }

    public void onUpdate() {
    }

    public boolean processDisplayStatus() {
        int i = 0;
        for (RedDot next : this.mPriorityGroup) {
            if (i >= this.mMaxDisplayNum || !next.processDisplayStatus() || !DisplayStatusManager.reddenDot(next.getKey())) {
                DisplayStatusManager.unreddenDot(next.getKey());
                if (next instanceof RedDotGroup) {
                    for (RedDot key : ((RedDotGroup) next).mPriorityGroup) {
                        DisplayStatusManager.unreddenDot(key.getKey());
                    }
                }
            } else {
                i++;
            }
        }
        return i > 0;
    }
}
