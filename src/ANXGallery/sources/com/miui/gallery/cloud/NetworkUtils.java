package com.miui.gallery.cloud;

import com.miui.gallery.util.BaseNetworkUtils;
import com.miui.gallery.util.GalleryStatHelper;
import com.miui.gallery.util.StreamUtils;
import com.miui.gallery.util.SyncLog;
import com.miui.gallery.util.deviceprovider.ApplicationHelper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class NetworkUtils extends BaseNetworkUtils {
    private static boolean DBG;

    /* renamed from: com.miui.gallery.cloud.NetworkUtils$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$cloud$NetworkUtils$RequestType = new int[RequestType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$cloud$NetworkUtils$RequestType[RequestType.GET.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$cloud$NetworkUtils$RequestType[RequestType.POST.ordinal()] = 2;
        }
    }

    public enum RequestType {
        POST,
        GET
    }

    public static void closeHttpClient(HttpClient httpClient) {
        if (httpClient != null) {
            try {
                ClientConnectionManager connectionManager = httpClient.getConnectionManager();
                if (connectionManager != null) {
                    connectionManager.shutdown();
                }
            } catch (Throwable th) {
                SyncLog.e("NetworkUtils", th);
            }
        }
    }

    public static InputStream decodeGZip(HttpResponse httpResponse) throws IllegalStateException, IOException {
        Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
        return (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("gzip")) ? httpResponse.getEntity().getContent() : new GZIPInputStream(httpResponse.getEntity().getContent());
    }

    public static HttpResponse excuteInternal(HttpClient httpClient, HttpRequestBase httpRequestBase) throws IOException {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            HttpResponse execute = httpClient.execute(httpRequestBase);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            long j = 0;
            if (execute.getEntity() != null) {
                j = execute.getEntity().getContentLength();
            }
            GalleryStatHelper.addHttpEvent(httpRequestBase.getURI().toString(), currentTimeMillis2, j, execute.getStatusLine().getStatusCode());
            return execute;
        } catch (IOException e) {
            GalleryStatHelper.addHttpEvent(httpRequestBase.getURI().toString(), e.getClass().getSimpleName());
            throw e;
        }
    }

    public static HttpClient getHttpClient() {
        HttpClient httpClient = ApplicationHelper.getMiCloudProvider().getHttpClient();
        HttpParams params = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 30000);
        HttpConnectionParams.setSoTimeout(params, 30000);
        ConnManagerParams.setTimeout(params, 30000);
        return httpClient;
    }

    public static HttpURLConnection getHttpConn(String str, RequestType requestType) throws IOException {
        URL url = new URL(str);
        HttpURLConnection httpURLConnection = url.getProtocol().equals("https") ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(getHttpMethod(requestType));
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
        return httpURLConnection;
    }

    private static String getHttpMethod(RequestType requestType) {
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$cloud$NetworkUtils$RequestType[requestType.ordinal()];
        return i != 1 ? i != 2 ? "" : "POST" : "GET";
    }

    private static InputStream handleResult(HttpRequestBase httpRequestBase, HttpResponse httpResponse, String str, boolean z) throws IllegalStateException, IOException {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            return decodeGZip(httpResponse);
        }
        SyncLog.d("NetworkUtils", "Server error: " + statusCode + " " + httpResponse.getStatusLine());
        throw new IOException();
    }

    public static InputStream httpGetRequest(HttpClient httpClient, String str, Header header) throws ClientProtocolException, IOException, GalleryMiCloudServerException {
        HttpGet httpGet = new HttpGet(str);
        return handleResult(httpGet, httpGetRequest(httpClient, httpGet, header), str, true);
    }

    public static HttpResponse httpGetRequest(HttpClient httpClient, HttpGet httpGet, Header header) throws ClientProtocolException, IOException {
        httpGet.addHeader(header);
        if (!httpGet.containsHeader("Accept-Encoding")) {
            httpGet.addHeader("Accept-Encoding", "gzip");
        }
        if (DBG) {
            SyncLog.d("NetworkUtils", "http get url : " + httpGet.getURI());
            SyncLog.d("NetworkUtils", "http get cookies : " + header);
        }
        return excuteInternal(httpClient, httpGet);
    }

    public static String httpGetRequestForString(String str, Header header) throws ClientProtocolException, IOException, GalleryMiCloudServerException {
        HttpClient httpClient = getHttpClient();
        InputStream httpGetRequest = httpGetRequest(httpClient, str, header);
        if (httpGetRequest != null) {
            try {
                return StreamUtils.toString(httpGetRequest);
            } finally {
                if (httpGetRequest != null) {
                    httpGetRequest.close();
                }
                closeHttpClient(httpClient);
            }
        } else {
            if (httpGetRequest != null) {
                httpGetRequest.close();
            }
            closeHttpClient(httpClient);
            return null;
        }
    }

    public static InputStream httpPostRequest(HttpClient httpClient, String str, HttpEntity httpEntity, Header header) throws ClientProtocolException, IOException, GalleryMiCloudServerException {
        HttpPost httpPost = new HttpPost(str);
        if (httpEntity != null) {
            httpPost.addHeader(httpEntity.getContentType());
            httpPost.setEntity(httpEntity);
        }
        if (header != null) {
            httpPost.addHeader(header);
        }
        if (!httpPost.containsHeader("Accept-Encoding")) {
            httpPost.addHeader("Accept-Encoding", "gzip");
        }
        if (DBG) {
            SyncLog.d("NetworkUtils", "http post url: %s", (Object) str);
            SyncLog.d("NetworkUtils", "http post cookies: %s", (Object) header);
        }
        return handleResult(httpPost, excuteInternal(httpClient, httpPost), str, true);
    }

    public static String httpPostRequestForString(String str, HttpEntity httpEntity, Header header) throws ClientProtocolException, IOException, GalleryMiCloudServerException {
        HttpClient httpClient = getHttpClient();
        InputStream httpPostRequest = httpPostRequest(httpClient, str, httpEntity, header);
        if (httpPostRequest != null) {
            try {
                return StreamUtils.toString(httpPostRequest);
            } finally {
                if (httpPostRequest != null) {
                    httpPostRequest.close();
                }
                closeHttpClient(httpClient);
            }
        } else {
            if (httpPostRequest != null) {
                httpPostRequest.close();
            }
            closeHttpClient(httpClient);
            return null;
        }
    }
}
