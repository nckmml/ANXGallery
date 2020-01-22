package cn.kuaipan.android.utils;

import android.util.MalformedJsonException;
import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.http.KscHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.json.JSONException;

public class ApiDataHelper {

    public interface IKscData {

        public interface Parser<T extends IKscData> {
        }
    }

    public static Number asNumber(Object obj, Number number) {
        if (obj == null) {
            return number;
        }
        if (obj instanceof Number) {
            return (Number) obj;
        }
        String obj2 = obj.toString();
        try {
            return Long.valueOf(Long.parseLong(obj2));
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.parseDouble(obj2));
        }
    }

    public static String asString(Map<String, Object> map, String str) {
        if (map != null) {
            Object obj = map.get(str);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
        throw new IllegalArgumentException("DataMap can't be null when parse.");
    }

    public static Map<String, Object> contentToMap(KscHttpResponse kscHttpResponse) throws KscException, InterruptedException {
        InputStream inputStream = null;
        try {
            inputStream = kscHttpResponse.getContent();
            if (inputStream != null) {
                Map<String, Object> map = (Map) JsonUtils.parser(inputStream);
                if (map == null || map.isEmpty()) {
                    throw new KscException(501003, kscHttpResponse.dump());
                }
                try {
                    inputStream.close();
                } catch (Throwable unused) {
                }
                return map;
            }
            throw new KscException(501001, kscHttpResponse.dump());
        } catch (MalformedJsonException e) {
            throw new KscException(501001, kscHttpResponse.dump(), e);
        } catch (JSONException e2) {
            throw new KscException(501001, kscHttpResponse.dump(), e2);
        } catch (IOException e3) {
            throw KscException.newException(e3, kscHttpResponse.dump());
        } catch (ClassCastException e4) {
            throw new KscException(501003, kscHttpResponse.dump(), e4);
        } catch (Throwable unused2) {
        }
        throw th;
    }
}
