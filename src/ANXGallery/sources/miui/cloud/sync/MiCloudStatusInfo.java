package miui.cloud.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.micloudsdk.cloudinfo.utils.CloudInfoUtils;
import java.util.ArrayList;
import miui.accounts.ExtraAccountManager;
import org.json.JSONException;
import org.json.JSONObject;

public class MiCloudStatusInfo {
    private QuotaInfo mQuotaInfo;
    private String mUserId;

    public class ItemInfo {
        private String mLocalizedName;
        private String mName;
        private long mUsed;

        public ItemInfo(String str, String str2, long j) {
            this.mName = str;
            this.mLocalizedName = str2;
            this.mUsed = j;
        }

        public String toString() {
            return "ItemInfo{mName=" + this.mName + ", mLocalizedName=" + this.mLocalizedName + ", mUsed='" + this.mUsed + '}';
        }
    }

    public class QuotaInfo {
        private ArrayList<ItemInfo> mItemInfoList = new ArrayList<>();
        private long mTotal;
        private long mUsed;
        private String mWarn;
        private long mYearlyPackageCreateTime;
        private long mYearlyPackageExpireTime;
        private long mYearlyPackageSize;
        private String mYearlyPackageType;

        public QuotaInfo(long j, long j2, String str, String str2, long j3, long j4, long j5) {
            this.mTotal = j;
            this.mUsed = j2;
            this.mWarn = str;
            this.mYearlyPackageType = str2;
            this.mYearlyPackageSize = j3;
            this.mYearlyPackageCreateTime = j4;
            this.mYearlyPackageExpireTime = j5;
        }

        public void addItemInfo(ItemInfo itemInfo) {
            this.mItemInfoList.add(itemInfo);
        }

        public long getTotal() {
            return this.mTotal;
        }

        public long getUsed() {
            return this.mUsed;
        }

        public String getWarn() {
            return this.mWarn;
        }

        public String getYearlyPackageType() {
            return this.mYearlyPackageType;
        }

        public boolean isSpaceFull() {
            return "full".equals(getWarn());
        }

        public boolean isSpaceLowPercent() {
            return "low_percent".equals(getWarn());
        }

        public String toString() {
            return "QuotaInfo{mTotal=" + this.mTotal + ", mUsed=" + this.mUsed + ", mWarn='" + this.mWarn + '\'' + ", mYearlyPackageType='" + this.mYearlyPackageType + '\'' + ", mYearlyPackageSize=" + this.mYearlyPackageSize + ", mYearlyPackageCreateTime=" + this.mYearlyPackageCreateTime + ", mYearlyPackageExpireTime=" + this.mYearlyPackageExpireTime + ", mItemInfoList=" + this.mItemInfoList + '}';
        }
    }

    public MiCloudStatusInfo(String str) {
        this.mUserId = str;
    }

    public static MiCloudStatusInfo fromUserData(Context context) {
        AccountManager accountManager = AccountManager.get(context);
        Account xiaomiAccount = ExtraAccountManager.getXiaomiAccount(context);
        if (xiaomiAccount == null) {
            return null;
        }
        String userData = accountManager.getUserData(xiaomiAccount, "extra_micloud_status_info_quota");
        MiCloudStatusInfo miCloudStatusInfo = new MiCloudStatusInfo(xiaomiAccount.name);
        miCloudStatusInfo.parseQuotaString(userData);
        QuotaInfo quotaInfo = miCloudStatusInfo.getQuotaInfo();
        if (quotaInfo == null || quotaInfo.getWarn() == null) {
            Log.w("MiCloudStatusInfo", "deserialize failed");
            accountManager.setUserData(xiaomiAccount, "extra_micloud_status_info_quota", "");
        }
        return miCloudStatusInfo;
    }

    public QuotaInfo getQuotaInfo() {
        return this.mQuotaInfo;
    }

    public void parseQuotaString(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e("MiCloudStatusInfo", "parseQuotaString() quota is empty.");
            this.mQuotaInfo = null;
            return;
        }
        try {
            this.mQuotaInfo = CloudInfoUtils.getQuotaInfo(this, new JSONObject(str));
        } catch (JSONException unused) {
            Log.e("MiCloudStatusInfo", "catch JSONException in parseQuotaString()");
            this.mQuotaInfo = null;
        }
    }
}
