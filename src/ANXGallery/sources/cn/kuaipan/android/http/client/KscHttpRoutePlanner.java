package cn.kuaipan.android.http.client;

import cn.kuaipan.android.utils.NetworkHelpers;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.apache.http.params.AbstractHttpParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class KscHttpRoutePlanner extends DefaultHttpRoutePlanner {

    private class KscHttpParams extends AbstractHttpParams {
        private final HttpParams mExtParams;
        private final HttpParams mOrgParams;

        public KscHttpParams(HttpParams httpParams) {
            this.mOrgParams = httpParams;
            this.mExtParams = new BasicHttpParams();
        }

        private KscHttpParams(HttpParams httpParams, HttpParams httpParams2) {
            this.mOrgParams = httpParams;
            this.mExtParams = httpParams2;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [org.apache.http.params.HttpParams, cn.kuaipan.android.http.client.KscHttpRoutePlanner$KscHttpParams] */
        public HttpParams copy() {
            return new KscHttpParams(this.mOrgParams, this.mExtParams.copy());
        }

        public Object getParameter(String str) {
            Object parameter = this.mExtParams.getParameter(str);
            return parameter == null ? this.mOrgParams.getParameter(str) : parameter;
        }

        public boolean removeParameter(String str) {
            boolean removeParameter = this.mExtParams.removeParameter(str);
            if (removeParameter) {
                return removeParameter;
            }
            try {
                return this.mOrgParams.removeParameter(str);
            } catch (Exception unused) {
                return removeParameter;
            }
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [org.apache.http.params.HttpParams, cn.kuaipan.android.http.client.KscHttpRoutePlanner$KscHttpParams] */
        public HttpParams setParameter(String str, Object obj) {
            this.mExtParams.setParameter(str, obj);
            return this;
        }
    }

    public KscHttpRoutePlanner(SchemeRegistry schemeRegistry) {
        super(schemeRegistry);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [org.apache.http.params.HttpParams, cn.kuaipan.android.http.client.KscHttpRoutePlanner$KscHttpParams] */
    public HttpRoute determineRoute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws HttpException {
        HttpHost currentProxy = NetworkHelpers.getCurrentProxy();
        if (currentProxy != null) {
            ? kscHttpParams = new KscHttpParams(httpRequest.getParams());
            ConnRouteParams.setDefaultProxy(kscHttpParams, currentProxy);
            httpRequest.setParams(kscHttpParams);
        }
        return KscHttpRoutePlanner.super.determineRoute(httpHost, httpRequest, httpContext);
    }
}
