package com.xiaomi.micloudsdk.request.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.nexstreaming.nexeditorsdk.nexProject;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.provider.MiCloudSettings;
import com.xiaomi.micloudsdk.utils.JsonUtils;
import com.xiaomi.micloudsdk.utils.MiCloudConstants;
import com.xiaomi.micloudsdk.utils.NetworkUtils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import miui.cloud.common.XLogger;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class CloudUtils {
    private static final int[] SERVER_RETRY_INTERVALS = {1000, nexProject.kAutoThemeTransitionDuration, 5000, 10000};
    private static final String URL_RELOCATION_QUERY = (MiCloudConstants.URL.URL_RELOCATION_BASE + "/mic/relocation/v3/user/record");
    private static volatile Map<String, Object> sHostsCacheInner = new HashMap();
    private static volatile Object sNeedInitHostList;
    private static volatile boolean sNeedUpdateHostList;
    private static Object sUpdateMiCloudHostsLock = new Object();
    private static UpdateStatus sUpdateStatus;

    private enum UpdateStatus {
        UPDATING,
        SUCCESS,
        FAILED
    }

    public static String checkRedirect(String str, int i) throws CloudServerException {
        if (i < 15) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("code") == 308) {
                    if (jSONObject.getJSONObject("data").optBoolean("isPermanent")) {
                        setNeedUpdateHostsList(true);
                    }
                    return jSONObject.getJSONObject("data").getString("redirectUrl");
                } else if (jSONObject.getInt("code") == 503) {
                    throw new CloudServerException(503, 503, jSONObject.getJSONObject("data").getInt("retryAfter"));
                } else if (jSONObject.getInt("code") != 10034) {
                    return null;
                } else {
                    throw new CloudServerException(503, 10034, jSONObject.getJSONObject("data").getInt("retryAfter"));
                }
            } catch (JSONException e) {
                Log.e("Micloud", "JSONException in checkRedirect():" + str, e);
                return null;
            }
        } else {
            throw new CloudServerException(503, 10034, 10);
        }
    }

    private static Map<String, Object> getHostCache() {
        return new HashMap(sHostsCacheInner);
    }

    private static String getHostList() {
        if (getPersistHostsFromSp()) {
            if (Log.isLoggable("Micloud", 3)) {
                Log.d("Micloud", "get hostlist from sp");
            }
            return PreferenceManager.getDefaultSharedPreferences(Request.getContext()).getString("pref_micloud_hosts_v2", "");
        }
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "get hostlist from settings");
        }
        return MiCloudSettings.getString(Request.getContext().getContentResolver(), "micloud_hosts_v2");
    }

    private static String getHostWithScheme(String str) {
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "Enter getHost key=" + str);
        }
        Object obj = getHostCache().get(str);
        String str2 = (obj == null || !(obj instanceof String)) ? null : (String) obj;
        if (!TextUtils.isEmpty(str2)) {
            if (Log.isLoggable("Micloud", 3)) {
                Log.d("Micloud", "Hit host cache directly return host = " + str2);
            }
            return str2;
        }
        String hostList = getHostList();
        if (TextUtils.isEmpty(hostList)) {
            if (Log.isLoggable("Micloud", 3)) {
                Log.d("Micloud", "Hosts in SystemSettings/sp = null, return null");
            }
            return null;
        }
        try {
            updateHostCache(JsonUtils.jsonToMap(new JSONObject(hostList)));
            Object obj2 = getHostCache().get(str);
            if (obj2 != null && (obj2 instanceof String)) {
                str2 = (String) obj2;
            }
            if (Log.isLoggable("Micloud", 3)) {
                Log.d("Micloud", "find host in SystemSettings/sp return host = " + str2);
            }
            return str2;
        } catch (JSONException e) {
            Log.e("Micloud", "JSONException in getHost, return null", e);
            return null;
        }
    }

    private static boolean getPersistHostsFromSp() {
        return "true".equals(MiCloudSettings.getString(Request.getContext().getContentResolver(), "micloud_updatehosts_third_party"));
    }

    public static String getUserAgent() {
        return Request.getRequestEnv().getUserAgent();
    }

    public static Account getXiaomiAccount() {
        return getXiaomiAccount(Request.getContext());
    }

    public static Account getXiaomiAccount(Context context) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0];
        }
        return null;
    }

    private static String getXiaomiAccountName() {
        if (getPersistHostsFromSp()) {
            if (Log.isLoggable("Micloud", 3)) {
                Log.d("Micloud", "get accountName from sp");
            }
            return PreferenceManager.getDefaultSharedPreferences(Request.getContext()).getString("pref_micloud_accountname_v2", "");
        }
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "get accountName from settings");
        }
        return MiCloudSettings.getString(Request.getContext().getContentResolver(), "micloud_accountname_v2");
    }

    public static int isInternationalAccount(boolean z) {
        String host;
        if (z) {
            updateHostCache(new HashMap());
        }
        if (getHostCache().isEmpty()) {
            setNeedUpdateHostsList(true);
            try {
                updateMiCloudHosts(z);
                if (getHostCache().isEmpty()) {
                    return 2;
                }
            } catch (CloudServerException e) {
                Log.e("Micloud", "CloudServerException in isInternationalAccount", e);
                return 2;
            }
        }
        for (Map.Entry next : getHostCache().entrySet()) {
            String str = (String) next.getKey();
            Object value = next.getValue();
            if ((value instanceof String) && (host = Uri.parse((String) value).getHost()) != null) {
                return host.equals(str) ? 0 : 1;
            }
        }
        return 2;
    }

    private static boolean needUpdateHostListAndInitIfNeeded() {
        if (sNeedInitHostList == null) {
            synchronized (sUpdateMiCloudHostsLock) {
                if (sNeedInitHostList == null) {
                    sNeedInitHostList = new Object();
                    sNeedUpdateHostList = TextUtils.isEmpty(getHostList());
                }
            }
        }
        return sNeedUpdateHostList;
    }

    private static void setHostList(String str) {
        if (getPersistHostsFromSp()) {
            if (Log.isLoggable("Micloud", 3)) {
                Log.d("Micloud", "set hostlist to sp");
            }
            PreferenceManager.getDefaultSharedPreferences(Request.getContext()).edit().putString("pref_micloud_hosts_v2", str).commit();
            return;
        }
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "set hostlist to settings");
        }
        MiCloudSettings.putString(Request.getContext().getContentResolver(), "micloud_hosts_v2", str);
    }

    private static void setNeedUpdateHostsList(boolean z) {
        sNeedUpdateHostList = z;
    }

    private static void setXiaomiAccountName(String str) {
        if (getPersistHostsFromSp()) {
            if (Log.isLoggable("Micloud", 3)) {
                Log.d("Micloud", "set accountName: " + str + " to sp");
            }
            PreferenceManager.getDefaultSharedPreferences(Request.getContext()).edit().putString("pref_micloud_accountname_v2", str).commit();
            return;
        }
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "set accountName: " + str + " to settings");
        }
        MiCloudSettings.putString(Request.getContext().getContentResolver(), "micloud_accountname_v2", str);
    }

    private static void updateHostCache(Map<String, Object> map) {
        sHostsCacheInner = new HashMap(map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a6, code lost:
        if (r1 == false) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a8, code lost:
        sUpdateStatus = com.xiaomi.micloudsdk.request.utils.CloudUtils.UpdateStatus.UPDATING;
     */
    private static void updateMiCloudHosts(boolean z) throws CloudServerException {
        boolean z2;
        HashMap hashMap;
        needUpdateHostListAndInitIfNeeded();
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "enter updateMiCloudHosts, sNeedUpdateHosts: " + needUpdateHostListAndInitIfNeeded());
        }
        String xiaomiAccountName = getXiaomiAccountName();
        if (!TextUtils.isEmpty(xiaomiAccountName) && !xiaomiAccountName.equals(Request.getRequestEnv().getAccountName())) {
            Log.d("Micloud", "change sNeedUpdateHosts to true");
            setNeedUpdateHostsList(true);
        }
        if (needUpdateHostListAndInitIfNeeded() || z) {
            try {
                synchronized (sUpdateMiCloudHostsLock) {
                    loop0:
                    while (true) {
                        z2 = true;
                        while (sUpdateStatus == UpdateStatus.UPDATING) {
                            Log.v("Micloud", "Waiting for an existing updateMiCloudHosts to finish " + Thread.currentThread().getName());
                            sUpdateMiCloudHostsLock.wait();
                            Log.v("Micloud", "The existing updateMiCloudHosts finished " + Thread.currentThread().getName());
                            if (sUpdateStatus == UpdateStatus.SUCCESS) {
                                z2 = false;
                            }
                        }
                        break loop0;
                    }
                    while (true) {
                    }
                }
                if (z2) {
                    try {
                        Log.d("Micloud", "updateMiCloudHosts " + Thread.currentThread().getName());
                        hashMap = new HashMap();
                        String str = (String) Class.forName("android.os.Build").getDeclaredMethod("getRegion", new Class[0]).invoke((Object) null, new Object[0]);
                        Request.setRegion(str);
                        hashMap.put("romCountry", str);
                    } catch (JSONException e) {
                        Log.e("Micloud", "JSONException in updateMiCloudHosts", e);
                        throw new CloudServerException(0, (Throwable) e);
                    } catch (IllegalBlockSizeException e2) {
                        Log.e("Micloud", "IllegalBlockSizeException in updateMiCloudHosts", e2);
                        throw new CloudServerException(0, (Throwable) e2);
                    } catch (BadPaddingException e3) {
                        Log.e("Micloud", "BadPaddingException in updateMiCloudHosts", e3);
                        throw new CloudServerException(0, (Throwable) e3);
                    } catch (ClientProtocolException e4) {
                        Log.e("Micloud", "ClientProtocolException in updateMiCloudHosts", e4);
                        throw new CloudServerException(0, (Throwable) e4);
                    } catch (IOException e5) {
                        e = e5;
                        Log.e("Micloud", "IOException in updateMiCloudHosts", e);
                        if (!NetworkUtils.isNetworkAvailable(Request.getContext())) {
                            Log.d("Micloud", "No network in IOException");
                            throw new CloudServerException(0, (Throwable) e);
                        }
                    } catch (Exception e6) {
                        Log.e("Micloud", "Exception in updateMiCloudHosts()", e6);
                        hashMap.put("romCountry", Request.getRegion());
                    } catch (Throwable th) {
                        synchronized (sUpdateMiCloudHostsLock) {
                            sUpdateStatus = UpdateStatus.FAILED;
                            sUpdateMiCloudHostsLock.notifyAll();
                            throw th;
                        }
                    }
                    if (TextUtils.isEmpty((CharSequence) hashMap.get("romCountry"))) {
                        Log.d("Micloud", "request romCountry null, thirdparty app");
                        MiCloudSettings.putString(Request.getContext().getContentResolver(), "micloud_updatehosts_third_party", "true");
                    } else {
                        Log.d("Micloud", "request romCountry not null, system app");
                        MiCloudSettings.putString(Request.getContext().getContentResolver(), "micloud_updatehosts_third_party", "false");
                    }
                    int i = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        String accountName = Request.getRequestEnv().getAccountName();
                        if (TextUtils.isEmpty(accountName)) {
                            synchronized (sUpdateMiCloudHostsLock) {
                                sUpdateStatus = UpdateStatus.SUCCESS;
                                sUpdateMiCloudHostsLock.notifyAll();
                            }
                            return;
                        }
                        JSONObject jSONObject = new JSONObject(Request.securePost(URL_RELOCATION_QUERY, hashMap));
                        if (jSONObject.getInt("code") == 0) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("data").getJSONObject("hostList");
                            if (jSONObject2 != null) {
                                Log.d("Micloud", "getHostList set sNeedUpdateHosts to false " + Thread.currentThread().getName());
                                setHostList(jSONObject2.toString());
                                updateHostCache(JsonUtils.jsonToMap(jSONObject2));
                                setNeedUpdateHostsList(false);
                                if (!TextUtils.isEmpty(accountName)) {
                                    setXiaomiAccountName(accountName);
                                }
                            }
                            synchronized (sUpdateMiCloudHostsLock) {
                                sUpdateStatus = UpdateStatus.SUCCESS;
                                sUpdateMiCloudHostsLock.notifyAll();
                            }
                            return;
                        }
                        e = null;
                        if (i < SERVER_RETRY_INTERVALS.length) {
                            Log.e("Micloud", "Wait " + SERVER_RETRY_INTERVALS[i] + " ms for retry");
                            Thread.sleep((long) SERVER_RETRY_INTERVALS[i]);
                            i++;
                        } else if (e != null) {
                            throw new CloudServerException(0, (Throwable) e);
                        } else {
                            throw new CloudServerException(0);
                        }
                    }
                    throw new InterruptedException();
                }
            } catch (InterruptedException e7) {
                Log.e("Micloud", "InterruptedException in updateMiCloudHosts", e7);
                throw new CloudServerException(0, (Throwable) e7);
            }
        }
    }

    public static String updateRequestHost(String str, boolean z) throws CloudServerException {
        updateMiCloudHosts(z);
        try {
            XLogger.info("Micloud", "Original URL: " + str + ". ", new Object[0]);
            URL url = new URL(str);
            String hostWithScheme = getHostWithScheme(url.getHost());
            if (!TextUtils.isEmpty(hostWithScheme)) {
                XLogger.info("Micloud", "New URL: " + hostWithScheme + ". ", new Object[0]);
                URL url2 = new URL(hostWithScheme);
                str = new URL(url2.getProtocol(), url2.getHost(), url.getFile()).toString();
            }
        } catch (MalformedURLException e) {
            XLogger.error("Micloud", "MalformedURLException in updateHost %s", e);
        }
        XLogger.info("Micloud", "Final URL: " + str + ". ", new Object[0]);
        return str;
    }
}
