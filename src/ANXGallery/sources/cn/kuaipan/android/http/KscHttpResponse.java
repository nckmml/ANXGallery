package cn.kuaipan.android.http;

import android.util.Log;
import cn.kuaipan.android.utils.HttpUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpUriRequest;

public class KscHttpResponse {
    private final NetCacheManager mCache;
    private Throwable mError;
    private List<HttpMessage> mMessages;
    private HttpUriRequest mOrigRequest;
    private HttpResponse mResponse;

    public KscHttpResponse(NetCacheManager netCacheManager) {
        this.mCache = netCacheManager;
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        List<HttpMessage> list = this.mMessages;
        int i = 0;
        if (list != null) {
            Iterator<HttpMessage> it = list.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                HttpRequest httpRequest = (HttpMessage) it.next();
                if (httpRequest instanceof HttpRequest) {
                    sb.append("[Request " + i2 + "]\n");
                    sb.append(HttpUtils.toString(httpRequest));
                    i2++;
                } else if (httpRequest instanceof HttpResponse) {
                    sb.append("[Response " + i + "]\n");
                    sb.append(HttpUtils.toString((HttpResponse) httpRequest));
                    i++;
                }
            }
        }
        if (sb.length() <= 0) {
            sb.append("[Origin Request]\n");
            sb.append(HttpUtils.toString((HttpRequest) this.mOrigRequest));
        }
        sb.append("\n[Response " + i + "]\n");
        sb.append(HttpUtils.toString(this.mResponse));
        if (this.mError != null) {
            sb.append("\n[Error]\n");
            sb.append(Log.getStackTraceString(this.mError));
        }
        return sb.toString().replaceAll("password=.*&", "password=[secData]&");
    }

    public InputStream getContent() throws IllegalStateException, IOException {
        HttpResponse httpResponse = this.mResponse;
        String str = null;
        HttpEntity entity = httpResponse == null ? null : httpResponse.getEntity();
        if (entity == null) {
            return null;
        }
        InputStream content = entity.getContent();
        Header contentEncoding = entity.getContentEncoding();
        if (contentEncoding != null) {
            str = contentEncoding.getValue();
        }
        return (str == null || !str.contains("gzip")) ? content : new GZIPInputStream(content);
    }

    public Throwable getError() {
        return this.mError;
    }

    public HttpResponse getResponse() {
        return this.mResponse;
    }

    public int getStatusCode() {
        StatusLine statusLine;
        HttpResponse httpResponse = this.mResponse;
        if (httpResponse == null || (statusLine = httpResponse.getStatusLine()) == null) {
            return -1;
        }
        return statusLine.getStatusCode();
    }

    /* access modifiers changed from: package-private */
    public void handleResponse(KscHttpRequest kscHttpRequest, HttpResponse httpResponse, boolean z) {
        this.mOrigRequest = kscHttpRequest.getRequest();
        this.mResponse = httpResponse;
        IKscDecoder decoder = kscHttpRequest.getDecoder();
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null) {
            return;
        }
        if (z) {
            httpResponse.setEntity(KscHttpEntity.getRepeatableEntity(entity, decoder, this.mCache));
        } else if (decoder != null) {
            httpResponse.setEntity(new KscHttpEntity(entity, decoder));
        }
    }

    public void release() throws IOException {
        HttpResponse httpResponse = this.mResponse;
        if (httpResponse != null && httpResponse.getEntity() != null) {
            try {
                this.mResponse.getEntity().consumeContent();
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                Log.w("KscHttpResponse", "Meet exception when release a KscHttpResponse.", e2);
            } catch (Throwable th) {
                this.mResponse = null;
                throw th;
            }
            this.mResponse = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setError(Throwable th) {
        this.mError = th;
    }

    /* access modifiers changed from: package-private */
    public void setMessage(List<HttpMessage> list) {
        this.mMessages = list;
    }

    /* access modifiers changed from: package-private */
    public void setOrigRequest(HttpUriRequest httpUriRequest) {
        this.mOrigRequest = httpUriRequest;
    }
}
