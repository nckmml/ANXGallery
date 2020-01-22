package com.xiaomi.micloudsdk.request.utils;

import android.util.Log;
import com.xiaomi.micloudsdk.CloudHttpClient;
import com.xiaomi.micloudsdk.utils.CloudCoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.keyczar.Keyczar;

public class HttpUtils {
    private static boolean DBG;

    public enum HttpMethod {
        GET,
        POST,
        POST_JSON
    }

    public static String appendUrl(String str, List<NameValuePair> list) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        if (str != null) {
            StringBuilder sb = new StringBuilder(str);
            if (!str.contains("?")) {
                sb.append("?");
            }
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    sb.append("&");
                }
                String name = list.get(i).getName();
                String value = list.get(i).getValue();
                sb.append(name);
                sb.append("=");
                sb.append(URLEncoder.encode(value, Keyczar.DEFAULT_ENCODING));
            }
            String sb2 = sb.toString();
            if (DBG) {
                Log.d("HttpUtils", "result url length is: " + sb2.length());
            }
            return sb2;
        }
        throw new NullPointerException("origin is not allowed null");
    }

    public static HttpClient getHttpClient() {
        CloudHttpClient newInstance = CloudHttpClient.newInstance();
        HttpParams params = newInstance.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 30000);
        HttpConnectionParams.setSoTimeout(params, 30000);
        ConnManagerParams.setTimeout(params, 30000);
        return newInstance;
    }

    public static String getSignature(HttpMethod httpMethod, String str, ArrayList<NameValuePair> arrayList, String str2) throws UnsupportedEncodingException {
        TreeMap treeMap = new TreeMap();
        if (arrayList != null) {
            Iterator<NameValuePair> it = arrayList.iterator();
            while (it.hasNext()) {
                NameValuePair next = it.next();
                treeMap.put(next.getName(), next.getValue());
            }
        }
        return CloudCoder.generateSignature(httpMethod.name(), str, treeMap, str2);
    }
}
