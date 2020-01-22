package cn.kuaipan.android.http;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import cn.kuaipan.android.KssConfig;
import cn.kuaipan.android.exception.ErrorHelper;
import cn.kuaipan.android.http.client.KscHttpClient;
import cn.kuaipan.android.http.client.URIRedirector;
import cn.kuaipan.android.utils.ContextUtils;
import cn.kuaipan.android.utils.HttpUtils;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;

public class KscHttpTransmitter {
    private final NetCacheManager mCacheManager;
    private final SparseArray<Pair<Long, ? extends HttpClient>> mClients;
    private final Context mContext;
    private final KscSpeedManager mDownloadSpeedManager = new KscSpeedManager(1800);
    private URIRedirector mRedirector;
    private final KscSpeedManager mUploadSpeedManager = new KscSpeedManager(1800);
    private final SparseArray<String> mUserAgents;

    public KscHttpTransmitter(Context context) {
        ContextUtils.init(context);
        this.mContext = context;
        this.mCacheManager = NetCacheManager.getInstance(context, true);
        this.mClients = new SparseArray<>(4);
        this.mUserAgents = new SparseArray<>(4);
    }

    private HttpClient getClient(int i) {
        KscHttpClient kscHttpClient;
        int i2 = i & 7;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Pair pair = this.mClients.get(i2);
        if (pair != null && elapsedRealtime - ((Long) pair.first).longValue() <= 180000) {
            return (HttpClient) pair.second;
        }
        String str = this.mUserAgents.get(i2);
        boolean z = false;
        if ((i2 & 4) != 0) {
            kscHttpClient = KscHttpClient.newKssInstance(str);
        } else {
            boolean z2 = (i2 & 2) == 0;
            if ((i2 & 1) != 0) {
                z = true;
            }
            kscHttpClient = KscHttpClient.newInstance(str, z2, z);
        }
        this.mClients.put(i2, Pair.create(Long.valueOf(elapsedRealtime), kscHttpClient));
        return kscHttpClient;
    }

    private HttpRequest[] getRequest(List<HttpMessage> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HttpMessage> it = list.iterator();
        while (it.hasNext()) {
            HttpRequest httpRequest = (HttpMessage) it.next();
            if (httpRequest instanceof HttpRequest) {
                arrayList.add(httpRequest);
            }
        }
        return (HttpRequest[]) arrayList.toArray(new HttpRequest[arrayList.size()]);
    }

    private static String getRequestHost(HttpUriRequest httpUriRequest) {
        URI uri;
        if (httpUriRequest == null || (uri = httpUriRequest.getURI()) == null) {
            return null;
        }
        return uri.getHost();
    }

    private static HttpResponse[] getResponse(List<HttpMessage> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HttpMessage> it = list.iterator();
        while (it.hasNext()) {
            HttpResponse httpResponse = (HttpMessage) it.next();
            if (httpResponse instanceof HttpResponse) {
                arrayList.add(httpResponse);
            }
        }
        return (HttpResponse[]) arrayList.toArray(new HttpResponse[arrayList.size()]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001e, code lost:
        r7 = (org.apache.http.HttpResponse) r7;
     */
    private static void setMonitor(HttpMessage httpMessage, KscSpeedMonitor kscSpeedMonitor, IKscTransferListener iKscTransferListener, KssTransferStopper kssTransferStopper) {
        HttpResponse httpResponse;
        HttpEntity entity;
        if (httpMessage instanceof HttpEntityEnclosingRequestBase) {
            HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase) httpMessage;
            HttpEntity entity2 = httpEntityEnclosingRequestBase.getEntity();
            if (entity2 != null) {
                httpEntityEnclosingRequestBase.setEntity(new ProcessMonitorEntity(entity2, kscSpeedMonitor, iKscTransferListener, kssTransferStopper, true));
            }
        } else if ((httpMessage instanceof HttpResponse) && (entity = httpResponse.getEntity()) != null) {
            httpResponse.setEntity(new ProcessMonitorEntity(entity, kscSpeedMonitor, iKscTransferListener, kssTransferStopper, false));
        }
    }

    public KscHttpResponse execute(KscHttpRequest kscHttpRequest, int i, KssTransferStopper kssTransferStopper) throws InterruptedException {
        KscSpeedMonitor moniter;
        HttpResponse execute;
        boolean z;
        int i2 = i;
        KssTransferStopper kssTransferStopper2 = kssTransferStopper == null ? KssConfig.DEFAULT_TRANSFER_STOPPER : kssTransferStopper;
        IKscTransferListener listener = kscHttpRequest.getListener();
        KscHttpResponse kscHttpResponse = new KscHttpResponse(this.mCacheManager);
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        try {
            HttpUriRequest request = kscHttpRequest.getRequest();
            kscHttpResponse.setOrigRequest(request);
            HttpClient client = getClient(i2);
            if (!((i2 & 1) == 0 || this.mRedirector == null)) {
                basicHttpContext.setAttribute("ksc.connect_redirector", this.mRedirector);
            }
            String requestHost = getRequestHost(kscHttpRequest.getRequest());
            KscSpeedMonitor moniter2 = this.mUploadSpeedManager.getMoniter(requestHost);
            moniter = this.mDownloadSpeedManager.getMoniter(requestHost);
            setMonitor(request, moniter2, listener, kssTransferStopper2);
            long current = KscSpeedManager.current();
            execute = client.execute(request, basicHttpContext);
            long current2 = KscSpeedManager.current();
            Object attribute = basicHttpContext.getAttribute("ksc.message_list");
            z = false;
            if (attribute instanceof List) {
                List list = (List) attribute;
                if (list != null && !list.isEmpty()) {
                    kscHttpResponse.setMessage(list);
                }
                long requestSize = HttpUtils.getRequestSize(getRequest(list));
                long responseSize = HttpUtils.getResponseSize(getResponse(list)) + HttpUtils.getResponseSize(execute, false);
                moniter2.recode(current, current2, requestSize);
                moniter.recode(current, current2, responseSize);
            }
        } catch (Exception e) {
            Log.w("KscHttpTransmitter", "Failed get requestList from context.", e);
        } catch (Throwable th) {
            ErrorHelper.handleInterruptException(th);
            Log.w("KscHttpTransmitter", "Meet exception when execute a KscHttpRequest.", th);
            kscHttpResponse.setError(th);
        }
        setMonitor(execute, moniter, listener, kssTransferStopper2);
        if ((i2 & 4) == 0) {
            z = true;
        }
        kscHttpResponse.handleResponse(kscHttpRequest, execute, z);
        return kscHttpResponse;
    }

    public void setUserAgent(int i, String str) {
        this.mUserAgents.put(i, str);
        Pair pair = this.mClients.get(i);
        if (pair != null && pair.second != null) {
            HttpProtocolParams.setUserAgent(((HttpClient) pair.second).getParams(), str);
        }
    }
}
