package com.miui.gallery.hybrid.hybridclient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.miui.account.AccountHelper;
import com.miui.gallery.hybrid.hybridclient.HybridClient;

public class LoginHybridClient extends DeviceIdHybridClient {
    public LoginHybridClient(Context context, String str) {
        super(context, str);
    }

    public void getActualPath(final HybridClient.ActualPathCallback actualPathCallback) {
        if (actualPathCallback != null) {
            if (this.mContext == null) {
                actualPathCallback.onGetActualPath((String) null);
                return;
            }
            String str = "weblogin:" + this.mUrl;
            Account xiaomiAccount = AccountHelper.getXiaomiAccount(this.mContext);
            if (xiaomiAccount != null) {
                AccountManager.get(this.mContext).getAuthToken(xiaomiAccount, str, (Bundle) null, false, new AccountManagerCallback<Bundle>() {
                    public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
                        try {
                            actualPathCallback.onGetActualPath(accountManagerFuture.getResult().getString("authtoken"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, (Handler) null);
            }
        }
    }
}
