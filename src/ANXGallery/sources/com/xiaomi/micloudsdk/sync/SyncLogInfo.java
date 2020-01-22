package com.xiaomi.micloudsdk.sync;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import com.xiaomi.micloudsdk.utils.MiCloudConstants;

abstract class SyncLogInfo {
    protected final String authority;

    static class SyncEndLogInfo extends SyncLogInfo {
        private final long mEndTime;
        private final String mErrorMsg;
        private final boolean mIsSuccess;
        private final long mStartTime;

        SyncEndLogInfo(String str, boolean z, long j, long j2, String str2) {
            super(str);
            this.mIsSuccess = z;
            this.mStartTime = j;
            this.mEndTime = j2;
            if (str2 == null) {
                this.mErrorMsg = "";
            } else {
                this.mErrorMsg = str2;
            }
        }

        public String generateLogInfoString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mIsSuccess ? "Success" : "Error");
            sb.append(":{");
            sb.append("authority:");
            sb.append(this.authority);
            sb.append(", ");
            sb.append("start:");
            sb.append(formatTime(this.mStartTime));
            sb.append(", ");
            sb.append("end:");
            sb.append(formatTime(this.mEndTime));
            sb.append(", ");
            sb.append("delta:");
            sb.append(getDeltaTime(this.mStartTime, this.mEndTime));
            sb.append(", ");
            sb.append("error:");
            sb.append(this.mErrorMsg);
            sb.append("}");
            return sb.toString();
        }
    }

    static class SyncStartLogInfo extends SyncLogInfo {
        private final Bundle mExtras;
        private final long mStartTime;

        public SyncStartLogInfo(String str, long j, Bundle bundle) {
            super(str);
            this.mStartTime = j;
            this.mExtras = bundle;
        }

        public String generateLogInfoString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Start Sync");
            sb.append(":{");
            sb.append("authority:");
            sb.append(this.authority);
            sb.append(", ");
            sb.append("start:");
            sb.append(formatTime(this.mStartTime));
            sb.append(", ");
            appendSyncExtras(sb, this.mExtras);
            sb.append("}");
            return sb.toString();
        }
    }

    SyncLogInfo(String str) {
        this.authority = str;
    }

    protected static String formatTime(long j) {
        Time time = new Time();
        time.set(j);
        return time.format("%Y-%m-%d %H:%M:%S");
    }

    protected static String getDeltaTime(long j, long j2) {
        long j3 = (j2 - j) / 1000;
        if (j3 < 60) {
            return String.valueOf(j3);
        }
        if (j3 < 3600) {
            return String.format("%02d:%02d", new Object[]{Long.valueOf(j3 / 60), Long.valueOf(j3 % 60)});
        }
        long j4 = j3 % 3600;
        return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(j3 / 3600), Long.valueOf(j4 / 60), Long.valueOf(j4 % 60)});
    }

    /* access modifiers changed from: protected */
    public StringBuilder appendSyncExtras(StringBuilder sb, Bundle bundle) {
        sb.append("extras:");
        sb.append("{");
        sb.append(MiCloudConstants.SYNC.SYNC_EXTRAS_FORCE + "=" + bundle.getBoolean(MiCloudConstants.SYNC.SYNC_EXTRAS_FORCE, false));
        sb.append(",");
        sb.append("force=" + bundle.getBoolean("force", false));
        sb.append(",");
        sb.append("ignore_backoff=" + bundle.getBoolean("ignore_backoff", false));
        sb.append(",");
        sb.append("upload=" + bundle.getBoolean("upload", false));
        String string = bundle.getString("xiaomi_request");
        if (!TextUtils.isEmpty(string)) {
            sb.append(",");
            sb.append("xiaomi_request=" + string);
        }
        sb.append("}");
        return sb;
    }

    public abstract String generateLogInfoString();
}
