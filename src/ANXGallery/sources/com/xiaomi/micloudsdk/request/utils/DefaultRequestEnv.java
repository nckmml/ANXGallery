package com.xiaomi.micloudsdk.request.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.micloudsdk.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.remote.XiaomiAccountRemoteMethodInvoker;
import com.xiaomi.micloudsdk.request.utils.Request;
import java.io.IOException;
import micloud.compat.v18.utils.IXiaomiAccountServiceProxyCompat;

public class DefaultRequestEnv implements Request.RequestEnv {
    private static final int[] RETRY_INTERVALS = {5000, 10000};
    private ThreadLocal<ExtendedAuthToken> mExtendedAuthToken = new ThreadLocal<>();
    private String mUserAgent;

    public String getAccountName() {
        Account xiaomiAccount = CloudUtils.getXiaomiAccount();
        if (xiaomiAccount != null) {
            return xiaomiAccount.name;
        }
        Log.e("DefaultRequestEnv", "no account in system");
        return null;
    }

    public synchronized String getUserAgent() {
        if (this.mUserAgent == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(Build.MODEL);
            sb.append("; MIUI/");
            sb.append(Build.VERSION.INCREMENTAL);
            try {
                if (((Boolean) Class.forName("android.os.Build").getField("IS_ALPHA_BUILD").get((Object) null)).booleanValue()) {
                    sb.append(' ');
                    sb.append("ALPHA");
                }
            } catch (ClassNotFoundException unused) {
                Log.d("DefaultRequestEnv", "Not in MIUI in getUserAgent");
            } catch (NoSuchFieldException unused2) {
                Log.d("DefaultRequestEnv", "Not in MIUI in getUserAgent");
            } catch (IllegalAccessException unused3) {
                Log.d("DefaultRequestEnv", "Not in MIUI in getUserAgent");
            } catch (IllegalArgumentException unused4) {
                Log.d("DefaultRequestEnv", "Not in MIUI in getUserAgent");
            }
            this.mUserAgent = sb.toString();
        }
        return this.mUserAgent;
    }

    public void invalidateAuthToken() {
        if (this.mExtendedAuthToken.get() != null) {
            Log.d("DefaultRequestEnv", "invalidateAuthToken");
            AccountManager.get(Request.getContext()).invalidateAuthToken("com.xiaomi", this.mExtendedAuthToken.get().toPlain());
            this.mExtendedAuthToken.set((Object) null);
        }
    }

    public ExtendedAuthToken queryAuthToken() {
        int i = 0;
        boolean z = false;
        while (i < 3) {
            try {
                Account xiaomiAccount = CloudUtils.getXiaomiAccount();
                if (xiaomiAccount == null) {
                    Log.e("DefaultRequestEnv", "no account in system");
                    return null;
                }
                String string = AccountManager.get(Request.getContext()).getAuthToken(xiaomiAccount, "micloud", true, (AccountManagerCallback) null, (Handler) null).getResult().getString("authtoken");
                if (string == null) {
                    return null;
                }
                this.mExtendedAuthToken.set(ExtendedAuthToken.parse(string));
                return this.mExtendedAuthToken.get();
            } catch (IOException e) {
                Log.e("DefaultRequestEnv", "IOException when getting service token", e);
                if (i < 2) {
                    try {
                        Thread.sleep((long) RETRY_INTERVALS[i]);
                    } catch (InterruptedException unused) {
                        Log.e("DefaultRequestEnv", "InterruptedException when sleep", e);
                    }
                }
                i++;
            } catch (OperationCanceledException e2) {
                Log.e("DefaultRequestEnv", "OperationCanceledException when getting service token", e2);
                return null;
            } catch (AuthenticatorException e3) {
                Log.e("DefaultRequestEnv", "AuthenticatorException when getting service token", e3);
                if (z) {
                    break;
                }
                invalidateAuthToken();
                z = true;
                i++;
            }
        }
        return null;
    }

    public String queryEncryptedAccountName() {
        final Account xiaomiAccount = CloudUtils.getXiaomiAccount();
        if (xiaomiAccount != null) {
            return (String) new XiaomiAccountRemoteMethodInvoker<String>(Request.getContext()) {
                /* access modifiers changed from: protected */
                public String invokeRemoteMethod(IBinder iBinder) throws RemoteException {
                    return IXiaomiAccountServiceProxyCompat.getEncryptedUserId(iBinder, xiaomiAccount);
                }
            }.invoke();
        }
        Log.e("DefaultRequestEnv", "no account in system");
        return null;
    }
}
