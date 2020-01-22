package com.xiaomi.micloudsdk.request.utils;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.micloudsdk.exception.CipherException;
import com.xiaomi.micloudsdk.exception.CloudServerException;
import com.xiaomi.micloudsdk.stat.MiCloudNetEventStatInjector;
import com.xiaomi.micloudsdk.stat.MiCloudStatManager;
import com.xiaomi.micloudsdk.stat.NetFailedStatParam;
import com.xiaomi.micloudsdk.stat.NetSuccessStatParam;
import com.xiaomi.micloudsdk.utils.AESCoder;
import com.xiaomi.micloudsdk.utils.CryptCoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.keyczar.Keyczar;

public class CloudRequestHelper {
    static InputStream decodeGZip(HttpResponse httpResponse) throws IllegalStateException, IOException {
        Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
        return (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("gzip")) ? httpResponse.getEntity().getContent() : new GZIPInputStream(httpResponse.getEntity().getContent());
    }

    static String decodeGZipToString(HttpResponse httpResponse) throws IllegalStateException, IOException {
        InputStream decodeGZip = decodeGZip(httpResponse);
        try {
            return inputStreamToString(decodeGZip);
        } finally {
            if (decodeGZip != null) {
                decodeGZip.close();
            }
        }
    }

    public static String encodeData(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("security is empty.");
        } else if (TextUtils.isEmpty(str3)) {
            return "";
        } else {
            try {
                return getCryptCoder(str, str2).encrypt(str3);
            } catch (CipherException e) {
                Log.e("CloudRequestHelper", "encodeData failed:" + str3, e);
                return "";
            }
        }
    }

    private static HttpResponse excuteInternal(HttpRequestBase httpRequestBase) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                HttpResponse execute = HttpUtils.getHttpClient().execute(httpRequestBase);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                long j = 0;
                if (execute.getEntity() != null) {
                    j = execute.getEntity().getContentLength();
                }
                long j2 = j;
                int statusCode = execute.getStatusLine().getStatusCode();
                MiCloudStatManager.getInstance().addHttpEvent(httpRequestBase.getURI().toString(), currentTimeMillis2, j2, statusCode, (String) null);
                MiCloudNetEventStatInjector instance = MiCloudNetEventStatInjector.getInstance();
                NetSuccessStatParam netSuccessStatParam = r1;
                NetSuccessStatParam netSuccessStatParam2 = new NetSuccessStatParam(httpRequestBase.getURI().toString(), currentTimeMillis, currentTimeMillis2, j2, statusCode, 0);
                instance.addNetSuccessEvent(netSuccessStatParam);
                return execute;
            } catch (IOException e) {
                e = e;
                MiCloudStatManager.getInstance().addHttpEvent(httpRequestBase.getURI().toString(), -1, 0, -1, e.getClass().getSimpleName());
                MiCloudNetEventStatInjector.getInstance().addNetFailedEvent(new NetFailedStatParam(httpRequestBase.getURI().toString(), currentTimeMillis, System.currentTimeMillis() - currentTimeMillis, e, 0));
                throw e;
            }
        } catch (IOException e2) {
            e = e2;
            HttpRequestBase httpRequestBase2 = httpRequestBase;
            MiCloudStatManager.getInstance().addHttpEvent(httpRequestBase.getURI().toString(), -1, 0, -1, e.getClass().getSimpleName());
            MiCloudNetEventStatInjector.getInstance().addNetFailedEvent(new NetFailedStatParam(httpRequestBase.getURI().toString(), currentTimeMillis, System.currentTimeMillis() - currentTimeMillis, e, 0));
            throw e;
        }
    }

    public static CryptCoder getCryptCoder(String str, String str2) {
        return isV4Url(str) ? new CloudAESWithIVCoder(str2) : new AESCoder(str2);
    }

    private static String httpGetRequest(String str, Header header, CryptCoder cryptCoder, int i) throws IOException, CloudServerException {
        HttpGet httpGet = new HttpGet(str);
        if (header != null) {
            httpGet.setHeader(header);
        }
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Accept-Encoding", "gzip");
        if (i > 0) {
            httpGet.setHeader("X-XIAOMI-REDIRECT-COUNT", i + "");
        }
        httpGet.setHeader("X-XIAOMI-SUPPORT-REDIRECT", "true, https");
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "http get url : " + str);
            Log.d("Micloud", "http get cookies : " + header.toString());
        }
        HttpResponse excuteInternal = excuteInternal(httpGet);
        int statusCode = excuteInternal.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            try {
                String decrypt = cryptCoder.decrypt(decodeGZipToString(excuteInternal));
                String checkRedirect = CloudUtils.checkRedirect(decrypt, i);
                return !TextUtils.isEmpty(checkRedirect) ? httpGetRequest(checkRedirect, header, cryptCoder, i + 1) : decrypt;
            } catch (CipherException e) {
                Log.e("Micloud", "MiCloudServerException", e);
                throw new CloudServerException(0, excuteInternal);
            }
        } else if (CloudServerException.isMiCloudServerException(statusCode)) {
            Log.e("Micloud", "MiCloudServerException: " + statusCode + " " + excuteInternal.getStatusLine());
            throw new CloudServerException(statusCode, excuteInternal);
        } else {
            Log.e("Micloud", "Server error: " + statusCode + " " + excuteInternal.getStatusLine());
            throw new IOException("Server error: " + statusCode + " " + excuteInternal.getStatusLine());
        }
    }

    public static String httpGetRequestWithDecodeData(String str, Header header, CryptCoder cryptCoder) throws CloudServerException, IOException {
        String httpGetRequest = httpGetRequest(str, header, cryptCoder, 0);
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", httpGetRequest);
        }
        return httpGetRequest;
    }

    public static String httpPostJSONRequestWithDecodeData(String str, StringEntity stringEntity, Header header, CryptCoder cryptCoder) throws CloudServerException, IOException {
        String httpPostRequest = httpPostRequest(str, stringEntity, "application/json", header, cryptCoder, 0);
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", httpPostRequest);
        }
        return httpPostRequest;
    }

    private static String httpPostRequest(String str, HttpEntity httpEntity, String str2, Header header, CryptCoder cryptCoder, int i) throws IOException, CloudServerException {
        HttpPost httpPost = new HttpPost(str);
        if (httpEntity != null) {
            httpPost.setEntity(httpEntity);
        }
        if (!TextUtils.isEmpty(str2)) {
            httpPost.setHeader("Content-Type", str2);
        }
        if (header != null) {
            httpPost.setHeader(header);
        }
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Accept-Encoding", "gzip");
        if (i > 0) {
            httpPost.setHeader("X-XIAOMI-REDIRECT-COUNT", i + "");
        }
        httpPost.setHeader("X-XIAOMI-SUPPORT-REDIRECT", "true, https");
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", "http post url : " + str);
            Log.d("Micloud", "http post cookies : " + header.toString());
        }
        HttpResponse excuteInternal = excuteInternal(httpPost);
        int statusCode = excuteInternal.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            try {
                String decrypt = cryptCoder.decrypt(decodeGZipToString(excuteInternal));
                String checkRedirect = CloudUtils.checkRedirect(decrypt, i);
                if (TextUtils.isEmpty(checkRedirect)) {
                    return decrypt;
                }
                return httpPostRequest(checkRedirect, httpEntity, str2, header, cryptCoder, i + 1);
            } catch (CipherException e) {
                Log.e("Micloud", "MiCloudServerException", e);
                throw new CloudServerException(0, excuteInternal);
            }
        } else if (CloudServerException.isMiCloudServerException(statusCode)) {
            Log.e("Micloud", "MiCloudServerException: " + statusCode + " " + excuteInternal.getStatusLine());
            throw new CloudServerException(statusCode, excuteInternal);
        } else {
            Log.e("Micloud", "Server error: " + statusCode + " " + excuteInternal.getStatusLine());
            throw new IOException("Server error: " + statusCode + " " + excuteInternal.getStatusLine());
        }
    }

    public static String httpPostRequestWithDecodeData(String str, UrlEncodedFormEntity urlEncodedFormEntity, Header header, CryptCoder cryptCoder) throws CloudServerException, IOException {
        String httpPostRequest = httpPostRequest(str, urlEncodedFormEntity, urlEncodedFormEntity.getContentType().getValue(), header, cryptCoder, 0);
        if (Log.isLoggable("Micloud", 3)) {
            Log.d("Micloud", httpPostRequest);
        }
        return httpPostRequest;
    }

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Keyczar.DEFAULT_ENCODING));
        char[] cArr = new char[2048];
        while (true) {
            int read = bufferedReader.read(cArr, 0, 2048);
            if (read == -1) {
                return stringBuffer.toString();
            }
            stringBuffer.append(cArr, 0, read);
        }
    }

    public static boolean isV4Url(String str) {
        return str.indexOf("/v4") != -1;
    }
}
