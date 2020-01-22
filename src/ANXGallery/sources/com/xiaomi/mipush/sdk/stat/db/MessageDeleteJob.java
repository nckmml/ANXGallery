package com.xiaomi.mipush.sdk.stat.db;

import com.xiaomi.mipush.sdk.stat.db.base.DbManager;

public class MessageDeleteJob extends DbManager.DeleteJob {
    protected String mDescription = "MessageDeleteJob";

    public MessageDeleteJob(String str, String str2, String[] strArr, String str3) {
        super(str, str2, strArr);
        this.mDescription = str3;
    }

    public static MessageDeleteJob deleteUploadedJob(String str) {
        return new MessageDeleteJob(str, "status = ?", new String[]{String.valueOf(2)}, "a job build to delete uploaded job");
    }
}
