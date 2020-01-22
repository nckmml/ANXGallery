package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.keyczar.Keyczar;

public abstract class HttpUtils {

    public static class DefaultHttpGetProcessor extends HttpProcessor {
        public DefaultHttpGetProcessor() {
            super(1);
        }

        public String visit(Context context, String str, List<NameValuePair> list) throws IOException {
            if (list == null) {
                return Network.downloadXml(context, new URL(str));
            }
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            for (NameValuePair next : list) {
                buildUpon.appendQueryParameter(next.getName(), next.getValue());
            }
            return Network.downloadXml(context, new URL(buildUpon.toString()));
        }
    }

    public static String get(Context context, String str, List<NameValuePair> list) {
        return httpRequest(context, str, list, new DefaultHttpGetProcessor(), true);
    }

    static int getHttpGetTxtTraffic(int i, int i2) {
        return (((i2 + 243) / 1448) * BaiduSceneResult.VARIOUS_TICKETS) + 1080 + i + i2;
    }

    static int getHttpPostTxtTraffic(int i, int i2, int i3) {
        return (((i2 + 200) / 1448) * BaiduSceneResult.VARIOUS_TICKETS) + 1011 + i2 + i + i3;
    }

    static int getPostDataLength(List<NameValuePair> list) {
        int i = 0;
        for (NameValuePair next : list) {
            if (!TextUtils.isEmpty(next.getName())) {
                i += next.getName().length();
            }
            if (!TextUtils.isEmpty(next.getValue())) {
                i += next.getValue().length();
            }
        }
        return i * 2;
    }

    static int getStringUTF8Length(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes(Keyczar.DEFAULT_ENCODING).length;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    private static int getTraffic(HttpProcessor httpProcessor, String str, List<NameValuePair> list, String str2) {
        if (httpProcessor.getRequestType() == 1) {
            return getHttpGetTxtTraffic(str.length(), getStringUTF8Length(str2));
        }
        if (httpProcessor.getRequestType() != 2) {
            return -1;
        }
        return getHttpPostTxtTraffic(str.length(), getPostDataLength(list), getStringUTF8Length(str2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x009d A[SYNTHETIC, Splitter:B:47:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b1 A[Catch:{ MalformedURLException -> 0x00bc }] */
    public static String httpRequest(Context context, String str, List<NameValuePair> list, HttpProcessor httpProcessor, boolean z) {
        Fallback fallback;
        String str2;
        String str3;
        Context context2 = context;
        String str4 = str;
        List<NameValuePair> list2 = list;
        HttpProcessor httpProcessor2 = httpProcessor;
        if (Network.hasNetwork(context)) {
            try {
                ArrayList<String> arrayList = new ArrayList<>();
                if (z) {
                    Fallback fallbacksByURL = HostManager.getInstance().getFallbacksByURL(str4);
                    if (fallbacksByURL != null) {
                        arrayList = fallbacksByURL.getUrls(str4);
                    }
                    fallback = fallbacksByURL;
                } else {
                    fallback = null;
                }
                if (!arrayList.contains(str4)) {
                    arrayList.add(str4);
                }
                Iterator<String> it = arrayList.iterator();
                String str5 = null;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    ArrayList arrayList2 = list2 != null ? new ArrayList(list2) : null;
                    long currentTimeMillis = System.currentTimeMillis();
                    try {
                        if (!httpProcessor2.prepare(context2, next, arrayList2)) {
                            break;
                        }
                        str3 = httpProcessor2.visit(context2, next, arrayList2);
                        try {
                            if (TextUtils.isEmpty(str3)) {
                                if (fallback != null) {
                                    str2 = str3;
                                    try {
                                        fallback.failedUrl(next, System.currentTimeMillis() - currentTimeMillis, (long) getTraffic(httpProcessor2, next, arrayList2, str3), (Exception) null);
                                    } catch (IOException e) {
                                        e = e;
                                        str3 = str2;
                                    }
                                } else {
                                    str2 = str3;
                                }
                                str5 = str2;
                            } else if (fallback == null) {
                                return str3;
                            } else {
                                try {
                                    fallback.succeedUrl(next, System.currentTimeMillis() - currentTimeMillis, (long) getTraffic(httpProcessor2, next, arrayList2, str3));
                                    return str3;
                                } catch (IOException e2) {
                                    e = e2;
                                }
                            }
                        } catch (IOException e3) {
                            e = e3;
                            String str6 = str3;
                            if (fallback == null) {
                            }
                            e.printStackTrace();
                            str5 = str2;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        str3 = str5;
                        if (fallback == null) {
                            str2 = str3;
                            fallback.failedUrl(next, System.currentTimeMillis() - currentTimeMillis, (long) getTraffic(httpProcessor2, next, arrayList2, str3), e);
                        } else {
                            str2 = str3;
                        }
                        e.printStackTrace();
                        str5 = str2;
                    }
                }
                return str5;
            } catch (MalformedURLException e5) {
                e5.printStackTrace();
            }
        }
        return null;
    }
}
