package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.mpcd.Constants;
import com.xiaomi.xmpush.thrift.ClientCollectionType;

public class BroadcastActionCollectionjob extends CollectionJob {
    public static String mChangedActions = "";
    public static String mRestartedActions = "";

    public BroadcastActionCollectionjob(Context context, int i) {
        super(context, i);
    }

    private String shrinkActionInfo(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String[] split = str2.split(",");
        if (split.length <= 10) {
            return str2;
        }
        int length = split.length;
        while (true) {
            length--;
            if (length < split.length - 10) {
                return str;
            }
            str = str + split[length];
        }
    }

    public String collectInfo() {
        String str;
        if (!TextUtils.isEmpty(mRestartedActions)) {
            str = "" + shrinkActionInfo(Constants.ACTION_PACKAGE_RESTARTED, mRestartedActions);
            mRestartedActions = "";
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(mChangedActions)) {
            return str;
        }
        String str2 = str + shrinkActionInfo(Constants.ACTION_PACKAGE_CHANGED, mChangedActions);
        mChangedActions = "";
        return str2;
    }

    public ClientCollectionType getCollectionType() {
        return ClientCollectionType.BroadcastAction;
    }

    public int getJobId() {
        return 12;
    }
}
