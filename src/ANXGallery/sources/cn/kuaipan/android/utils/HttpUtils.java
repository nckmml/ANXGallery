package cn.kuaipan.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

public class HttpUtils {
    /* JADX INFO: finally extract failed */
    private static String entityToString(HttpEntity httpEntity, int i) throws IOException {
        String str;
        long contentLength = httpEntity.getContentLength();
        int i2 = (contentLength > 0 ? 1 : (contentLength == 0 ? 0 : -1));
        long j = (long) i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) Math.min(i2 < 0 ? 2147483647L : contentLength, j));
        int i3 = 0;
        boolean z = i2 >= 0 && contentLength <= j;
        if (z) {
            httpEntity.writeTo(byteArrayOutputStream);
        } else {
            InputStream content = httpEntity.getContent();
            try {
                byte[] bArr = new byte[1024];
                int i4 = 0;
                while (true) {
                    int read = content.read(bArr);
                    if (read == -1 || i4 >= i) {
                        content.close();
                        i3 = i4;
                    } else {
                        byteArrayOutputStream.write(bArr, 0, Math.min(read, i - i4));
                        i4 += read;
                    }
                }
                content.close();
                i3 = i4;
            } catch (Throwable th) {
                content.close();
                throw th;
            }
        }
        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(byteArrayOutputStream2);
        if (z || i3 < i) {
            str = "";
        } else {
            str = "\n [TOO MUCH DATA TO INCLUDE, SIZE=" + contentLength + "]";
        }
        sb.append(str);
        return sb.toString();
    }

    public static long getHeaderSize(Header[] headerArr) {
        long j = 0;
        if (headerArr != null && headerArr.length > 0) {
            for (Header obj : headerArr) {
                j += (long) (obj.toString().getBytes().length + 1);
            }
        }
        return j;
    }

    public static long getRequestSize(HttpRequest httpRequest, boolean z) {
        if (httpRequest == null) {
            return 0;
        }
        return ((long) (httpRequest.getRequestLine().toString().getBytes().length + 1)) + getHeaderSize(httpRequest.getAllHeaders()) + 0;
    }

    public static long getRequestSize(HttpRequest... httpRequestArr) {
        long j = 0;
        if (httpRequestArr == null) {
            return 0;
        }
        for (HttpRequest requestSize : httpRequestArr) {
            j += getRequestSize(requestSize, true);
        }
        return j;
    }

    public static long getResponseSize(HttpResponse httpResponse, boolean z) {
        if (httpResponse == null) {
            return 0;
        }
        return ((long) (httpResponse.getStatusLine().toString().getBytes().length + 1)) + getHeaderSize(httpResponse.getAllHeaders()) + 0;
    }

    public static long getResponseSize(HttpResponse... httpResponseArr) {
        long j = 0;
        if (httpResponseArr == null) {
            return 0;
        }
        for (HttpResponse responseSize : httpResponseArr) {
            j += getResponseSize(responseSize, true);
        }
        return j;
    }

    public static StringBuffer toString(HttpRequest httpRequest) {
        HttpEntity entity;
        if (httpRequest == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(httpRequest.getRequestLine());
        stringBuffer.append("\n");
        for (Header obj : httpRequest.getAllHeaders()) {
            stringBuffer.append(obj.toString().trim());
            stringBuffer.append("\n");
        }
        if ((httpRequest instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) httpRequest).getEntity()) != null) {
            stringBuffer.append("Content:\n");
            try {
                if (entity.isRepeatable()) {
                    stringBuffer.append(entityToString(entity, 1024));
                } else {
                    stringBuffer.append(" [DATA CAN NOT REPEAT]");
                }
            } catch (Exception unused) {
                stringBuffer.append(" [FAILED OUTPUT DATA]");
            }
            stringBuffer.append("\n");
        }
        return stringBuffer;
    }

    public static StringBuffer toString(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(httpResponse.getStatusLine());
        stringBuffer.append("\n");
        for (Header obj : httpResponse.getAllHeaders()) {
            stringBuffer.append(obj.toString().trim());
            stringBuffer.append("\n");
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            stringBuffer.append("Content:\n");
            try {
                if (entity.isRepeatable()) {
                    stringBuffer.append(entityToString(entity, 1024));
                } else {
                    stringBuffer.append(" [DATA CAN NOT REPEAT]");
                }
            } catch (Exception unused) {
                stringBuffer.append(" [FAILED OUTPUT DATA]");
            }
            stringBuffer.append("\n");
        }
        return stringBuffer;
    }
}
