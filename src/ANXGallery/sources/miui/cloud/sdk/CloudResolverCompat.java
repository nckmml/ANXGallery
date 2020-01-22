package miui.cloud.sdk;

import android.accounts.Account;
import android.content.Context;
import android.util.Log;
import com.xiaomi.micloudsdk.sync.MiCloudResolver;
import miui.cloud.content.MiSyncPolicyResolver;
import miui.util.AppConstants;

public class CloudResolverCompat {
    private static final LazyValue<Context, Integer> SDK_VERSION = new LazyValue<Context, Integer>() {
        /* access modifiers changed from: protected */
        public Integer onInit(Context context) {
            return Integer.valueOf(AppConstants.getSdkLevel(context, "com.xiaomi.micloud.sdk"));
        }
    };

    public static long getResumeTime(Context context, Account account, String str) {
        if (getSDKVersion(context) >= 29) {
            return MiSyncPolicyResolver.getMiSyncPauseToTime(account);
        }
        try {
            return MiCloudResolver.getResumeTime(context, str);
        } catch (Throwable th) {
            Log.e("CloudResolverCompat", "getResumeTime", th);
            return -1;
        }
    }

    public static int getSDKVersion(Context context) {
        return SDK_VERSION.get(context).intValue();
    }

    public static boolean isSyncPaused(Context context, Account account, String str) {
        return getResumeTime(context, account, str) > System.currentTimeMillis();
    }

    public static boolean pauseSync(Context context, Account account, String str, long j) {
        if (getSDKVersion(context) < 29) {
            try {
                MiCloudResolver.pauseSync(context, account, str, j);
                return true;
            } catch (Throwable th) {
                Log.e("CloudResolverCompat", "pauseSync", th);
                return false;
            }
        } else {
            MiSyncPolicyResolver.setMiSyncPauseToTime(account, System.currentTimeMillis() + j);
            return true;
        }
    }

    public static boolean resumeSync(Context context, Account account, String str) {
        if (getSDKVersion(context) < 29) {
            try {
                MiCloudResolver.resumeSync(context, account, str);
                return true;
            } catch (Throwable th) {
                Log.e("CloudResolverCompat", "resumeSync", th);
                return false;
            }
        } else {
            MiSyncPolicyResolver.setMiSyncResume(account);
            return true;
        }
    }
}
