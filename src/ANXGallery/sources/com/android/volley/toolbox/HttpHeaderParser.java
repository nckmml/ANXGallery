package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.Header;
import com.android.volley.NetworkResponse;
import com.android.volley.VolleyLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

public class HttpHeaderParser {
    static String formatEpochAsRfc1123(long j) {
        return newRfc1123Formatter().format(new Date(j));
    }

    private static SimpleDateFormat newRfc1123Formatter() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    public static Cache.Entry parseCacheHeaders(NetworkResponse networkResponse) {
        boolean z;
        long j;
        long j2;
        long j3;
        long j4;
        NetworkResponse networkResponse2 = networkResponse;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse2.headers;
        String str = map.get("Date");
        long parseDateAsEpoch = str != null ? parseDateAsEpoch(str) : 0;
        String str2 = map.get("Cache-Control");
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",", 0);
            int i2 = 0;
            j2 = 0;
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i2 = 1;
                }
                i++;
            }
            i = i2;
            z = true;
        } else {
            z = false;
            j2 = 0;
            j = 0;
        }
        String str3 = map.get("Expires");
        long parseDateAsEpoch2 = str3 != null ? parseDateAsEpoch(str3) : 0;
        String str4 = map.get("Last-Modified");
        long parseDateAsEpoch3 = str4 != null ? parseDateAsEpoch(str4) : 0;
        String str5 = map.get("ETag");
        if (z) {
            j4 = currentTimeMillis + (j2 * 1000);
            if (i == 0) {
                j3 = (j * 1000) + j4;
                Cache.Entry entry = new Cache.Entry();
                entry.data = networkResponse2.data;
                entry.etag = str5;
                entry.softTtl = j4;
                entry.ttl = j3;
                entry.serverDate = parseDateAsEpoch;
                entry.lastModified = parseDateAsEpoch3;
                entry.responseHeaders = map;
                entry.allResponseHeaders = networkResponse2.allHeaders;
                return entry;
            }
        } else if (parseDateAsEpoch <= 0 || parseDateAsEpoch2 < parseDateAsEpoch) {
            j4 = 0;
        } else {
            j3 = (parseDateAsEpoch2 - parseDateAsEpoch) + currentTimeMillis;
            j4 = j3;
            Cache.Entry entry2 = new Cache.Entry();
            entry2.data = networkResponse2.data;
            entry2.etag = str5;
            entry2.softTtl = j4;
            entry2.ttl = j3;
            entry2.serverDate = parseDateAsEpoch;
            entry2.lastModified = parseDateAsEpoch3;
            entry2.responseHeaders = map;
            entry2.allResponseHeaders = networkResponse2.allHeaders;
            return entry2;
        }
        j3 = j4;
        Cache.Entry entry22 = new Cache.Entry();
        entry22.data = networkResponse2.data;
        entry22.etag = str5;
        entry22.softTtl = j4;
        entry22.ttl = j3;
        entry22.serverDate = parseDateAsEpoch;
        entry22.lastModified = parseDateAsEpoch3;
        entry22.responseHeaders = map;
        entry22.allResponseHeaders = networkResponse2.allHeaders;
        return entry22;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return newRfc1123Formatter().parse(str).getTime();
        } catch (ParseException e) {
            VolleyLog.e(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    static List<Header> toAllHeaderList(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new Header((String) next.getKey(), (String) next.getValue()));
        }
        return arrayList;
    }

    static Map<String, String> toHeaderMap(List<Header> list) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Header next : list) {
            treeMap.put(next.getName(), next.getValue());
        }
        return treeMap;
    }
}
