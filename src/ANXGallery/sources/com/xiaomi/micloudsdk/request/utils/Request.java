package com.xiaomi.micloudsdk.request.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.micloudsdk.data.ExtendedAuthToken;
import com.xiaomi.micloudsdk.exception.CipherException;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.request.utils.HttpUtils;
import com.xiaomi.micloudsdk.utils.CloudCoder;
import com.xiaomi.micloudsdk.utils.CloudUtils;
import com.xiaomi.micloudsdk.utils.CryptCoder;
import com.xiaomi.micloudsdk.utils.MiCloudConstants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import miui.cloud.log.PrivacyFilter;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.keyczar.Keyczar;

public class Request {
    private static Context sContext;
    private static String sRegion;
    private static RequestEnv sRequestEnv = new DefaultRequestEnv();

    public interface RequestEnv {
        String getAccountName();

        String getUserAgent();

        void invalidateAuthToken();

        ExtendedAuthToken queryAuthToken();

        String queryEncryptedAccountName();
    }

    public static void addFilterTagsToParams(String str, Map<String, String> map, String str2, Set<String> set, long j) throws UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        String str3;
        String str4 = str;
        String str5 = str2;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new BasicNameValuePair((String) next.getKey(), (String) next.getValue()));
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        for (String next2 : set) {
            if (Long.parseLong(next2) > j) {
                if (i < 100) {
                    if (sb2.length() > 0) {
                        sb2.append(",");
                    }
                    sb2.append(next2);
                    i++;
                    if (!(i % 10 == 0 || i == set.size())) {
                    }
                }
                if (sb.length() == 0) {
                    str3 = sb2.toString();
                } else {
                    str3 = sb + "," + sb2;
                }
                BasicNameValuePair basicNameValuePair = new BasicNameValuePair("filterTag", CloudRequestHelper.encodeData(str, str5, str3));
                arrayList.add(basicNameValuePair);
                BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("signature", HttpUtils.getSignature(HttpUtils.HttpMethod.GET, str, arrayList, str5));
                arrayList.add(basicNameValuePair2);
                int length = HttpUtils.appendUrl(str, arrayList).length();
                arrayList.remove(basicNameValuePair);
                arrayList.remove(basicNameValuePair2);
                if (length >= 1024) {
                    break;
                }
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(sb2);
                sb2.setLength(0);
                if (i >= 100) {
                    break;
                }
            }
        }
        String sb3 = sb.toString();
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "The filterTag is :" + sb3);
        }
        map.put("filterTag", sb3);
    }

    private static void addSignatureParam(HttpUtils.HttpMethod httpMethod, String str, ArrayList<NameValuePair> arrayList, String str2) throws UnsupportedEncodingException {
        arrayList.add(new BasicNameValuePair("signature", HttpUtils.getSignature(httpMethod, str, arrayList, str2)));
    }

    private static String checkHostLocation(String str) throws CloudServerException {
        if (!str.startsWith(MiCloudConstants.URL.URL_RELOCATION_BASE)) {
            return CloudUtils.updateRequestHost(str, false);
        }
        Log.d("Request", "relocation url " + MiCloudConstants.URL.URL_RELOCATION_BASE);
        return str;
    }

    private static ArrayList<NameValuePair> encodeParameters(CryptCoder cryptCoder, String str, Map<String, String> map) throws CipherException {
        ArrayList<NameValuePair> arrayList;
        if (map == null) {
            map = new HashMap<>();
        }
        if (str == null || map.containsKey("_nonce")) {
            arrayList = new ArrayList<>(map.size());
        } else {
            arrayList = new ArrayList<>(map.size() + 1);
            arrayList.add(new BasicNameValuePair("_nonce", str));
        }
        for (Map.Entry next : map.entrySet()) {
            String str2 = (String) next.getKey();
            String str3 = (String) next.getValue();
            if (!str2.startsWith("_")) {
                if (str3 == null) {
                    str3 = "";
                }
                arrayList.add(new BasicNameValuePair(str2, cryptCoder.encrypt(str3)));
            } else {
                arrayList.add(new BasicNameValuePair(str2, str3));
            }
        }
        return arrayList;
    }

    private static String execute(String str, HttpUtils.HttpMethod httpMethod, Map<String, String> map, Map<String, String> map2) throws IOException, CloudServerException, IllegalBlockSizeException, BadPaddingException {
        HttpUtils.HttpMethod httpMethod2 = httpMethod;
        try {
            String checkHostLocation = checkHostLocation(str);
            String str2 = "";
            boolean isV4Url = CloudRequestHelper.isV4Url(checkHostLocation);
            int i = 0;
            while (true) {
                if (i >= 2) {
                    break;
                }
                ExtendedAuthToken queryAuthToken = queryAuthToken();
                CryptCoder cryptCoder = CloudRequestHelper.getCryptCoder(checkHostLocation, queryAuthToken.security);
                ArrayList<NameValuePair> encodeParameters = encodeParameters(cryptCoder, isV4Url ? CloudCoder.generateNonce() : null, map);
                addSignatureParam(httpMethod2, checkHostLocation, encodeParameters, queryAuthToken.security);
                Header cookies = getCookies(sRequestEnv.getAccountName(), sRequestEnv.queryEncryptedAccountName(), queryAuthToken.authToken, map2);
                try {
                    if (HttpUtils.HttpMethod.GET.equals(httpMethod2)) {
                        str2 = CloudRequestHelper.httpGetRequestWithDecodeData(HttpUtils.appendUrl(checkHostLocation, encodeParameters), cookies, cryptCoder);
                        break;
                    } else if (HttpUtils.HttpMethod.POST.equals(httpMethod2)) {
                        str2 = CloudRequestHelper.httpPostRequestWithDecodeData(checkHostLocation, new UrlEncodedFormEntity(encodeParameters, Keyczar.DEFAULT_ENCODING), cookies, cryptCoder);
                        break;
                    } else if (HttpUtils.HttpMethod.POST_JSON.equals(httpMethod2)) {
                        JSONObject jSONObject = new JSONObject();
                        for (int i2 = 0; i2 < encodeParameters.size(); i2++) {
                            jSONObject.put(encodeParameters.get(i2).getName(), encodeParameters.get(i2).getValue());
                        }
                        str2 = CloudRequestHelper.httpPostJSONRequestWithDecodeData(checkHostLocation, new StringEntity(jSONObject.toString(), "utf-8"), cookies, cryptCoder);
                        break;
                    } else {
                        throw new IllegalArgumentException("http method not supported.");
                    }
                } catch (JSONException e) {
                    throw new CloudServerException(0, e.toString());
                } catch (CloudServerException e2) {
                    Log.e("Micloud", "CloudServer Exception: " + PrivacyFilter.filterPrivacyLog(Log.getStackTraceString(e2)));
                    if (e2.getStatusCode() == 401) {
                        if (i == 0) {
                            Log.e("Micloud", "CloudServer 401 Exception: retry=" + i + PrivacyFilter.filterPrivacyLog(Log.getStackTraceString(e2)));
                            sRequestEnv.invalidateAuthToken();
                            CloudCoder.setServerDateOffset(e2.serverDate - System.currentTimeMillis());
                        } else if (i == 1) {
                            Log.e("Micloud", "CloudServer 401 Exception: retry=" + i + PrivacyFilter.filterPrivacyLog(Log.getStackTraceString(e2)));
                            throw e2;
                        }
                        i++;
                    } else {
                        RequestInjector.handleCloudServerException(getContext(), e2);
                        throw e2;
                    }
                }
            }
            RequestInjector.checkResponse(getContext(), str2);
            CloudNetworkAvailabilityManager.setRequestResult(getContext(), true);
            return str2;
        } catch (CipherException e3) {
            CipherException cipherException = e3;
            Log.e("Micloud", "CipherException: " + PrivacyFilter.filterPrivacyLog(Log.getStackTraceString(cipherException)));
            throw new CloudServerException(0, cipherException.toString());
        } catch (IOException e4) {
            CloudNetworkAvailabilityManager.setRequestResult(getContext(), false);
            throw e4;
        }
    }

    public static Context getContext() {
        Context context = sContext;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("sContext=null! Please call Request.init(Context) at Application onCreate");
    }

    private static Header getCookies(String str, String str2, String str3, Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("serviceToken=");
        sb.append(str3);
        if (str2 != null) {
            sb.append("; cUserId=");
            sb.append(str2);
        }
        if (map != null && map.size() > 0) {
            for (String next : map.keySet()) {
                if (!"cUserId".equals(next) && !"userId".equals(next) && !"serviceToken".equals(next) && !TextUtils.isEmpty(next)) {
                    sb.append("; " + next + "=");
                    sb.append(map.get(next));
                }
            }
        }
        return new BasicHeader("Cookie", sb.toString());
    }

    public static String getRegion() {
        return sRegion;
    }

    public static RequestEnv getRequestEnv() {
        return sRequestEnv;
    }

    public static void init(Context context) {
        sContext = context;
    }

    public static ExtendedAuthToken queryAuthToken() throws CloudServerException {
        ExtendedAuthToken queryAuthToken = sRequestEnv.queryAuthToken();
        if (queryAuthToken != null) {
            return queryAuthToken;
        }
        throw new CloudServerException(0, "execute() : queryAuthToken == null");
    }

    public static String secureGet(String str, Map<String, String> map) throws IllegalBlockSizeException, BadPaddingException, IOException, CloudServerException {
        return secureGet(str, map, (Map<String, String>) null);
    }

    public static String secureGet(String str, Map<String, String> map, Map<String, String> map2) throws IllegalBlockSizeException, BadPaddingException, IOException, CloudServerException {
        return execute(str, HttpUtils.HttpMethod.GET, map, map2);
    }

    public static String securePost(String str, Map<String, String> map) throws IllegalBlockSizeException, BadPaddingException, IOException, CloudServerException {
        return securePost(str, map, (Map<String, String>) null);
    }

    public static String securePost(String str, Map<String, String> map, Map<String, String> map2) throws IllegalBlockSizeException, BadPaddingException, IOException, CloudServerException {
        return execute(str, HttpUtils.HttpMethod.POST, map, map2);
    }

    public static void setRegion(String str) {
        sRegion = str;
    }
}
