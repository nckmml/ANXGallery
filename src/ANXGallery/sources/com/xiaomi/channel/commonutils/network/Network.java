package com.xiaomi.channel.commonutils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.string.MD5;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.keyczar.Keyczar;

public class Network {
    public static final Pattern ContentTypePattern_Charset = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    public static final Pattern ContentTypePattern_MimeType = Pattern.compile("([^\\s;]+)(.*)");
    public static final Pattern ContentTypePattern_XmlEncoding = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);

    public static final class DoneHandlerInputStream extends FilterInputStream {
        private boolean done;

        public DoneHandlerInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            if (!this.done && (read = super.read(bArr, i, i2)) != -1) {
                return read;
            }
            this.done = true;
            return -1;
        }
    }

    public static class HttpHeaderInfo {
        public Map<String, String> AllHeaders;
        public int ResponseCode;

        public String toString() {
            return String.format("resCode = %1$d, headers = %2$s", new Object[]{Integer.valueOf(this.ResponseCode), this.AllHeaders.toString()});
        }
    }

    public static HttpResponse doHttpPost(Context context, String str, Map<String, String> map) throws IOException {
        return httpRequest(context, str, "POST", (Map<String, String>) null, fromParamsMapToString(map));
    }

    public static String downloadXml(Context context, URL url) throws IOException {
        return downloadXml(context, url, false, (String) null, Keyczar.DEFAULT_ENCODING, (String) null);
    }

    public static String downloadXml(Context context, URL url, boolean z, String str, String str2, String str3) throws IOException {
        InputStream inputStream;
        try {
            inputStream = downloadXmlAsStream(context, url, z, str, str3);
            try {
                StringBuilder sb = new StringBuilder(1024);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str2));
                char[] cArr = new char[4096];
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (-1 != read) {
                        sb.append(cArr, 0, read);
                    } else {
                        IOUtils.closeQuietly(inputStream);
                        return sb.toString();
                    }
                }
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IOUtils.closeQuietly(inputStream);
            throw th;
        }
    }

    public static InputStream downloadXmlAsStream(Context context, URL url, boolean z, String str, String str2) throws IOException {
        return downloadXmlAsStream(context, url, z, str, str2, (Map<String, String>) null, (HttpHeaderInfo) null);
    }

    public static InputStream downloadXmlAsStream(Context context, URL url, boolean z, String str, String str2, Map<String, String> map, HttpHeaderInfo httpHeaderInfo) throws IOException {
        if (context == null) {
            throw new IllegalArgumentException("context");
        } else if (url != null) {
            URL url2 = !z ? new URL(encryptURL(url.toString())) : url;
            try {
                HttpURLConnection.setFollowRedirects(true);
                HttpURLConnection httpUrlConnection = getHttpUrlConnection(context, url2);
                httpUrlConnection.setConnectTimeout(10000);
                httpUrlConnection.setReadTimeout(15000);
                if (!TextUtils.isEmpty(str)) {
                    httpUrlConnection.setRequestProperty("User-Agent", str);
                }
                if (str2 != null) {
                    httpUrlConnection.setRequestProperty("Cookie", str2);
                }
                if (map != null) {
                    for (String next : map.keySet()) {
                        httpUrlConnection.setRequestProperty(next, map.get(next));
                    }
                }
                if (httpHeaderInfo != null && (url.getProtocol().equals("http") || url.getProtocol().equals("https"))) {
                    httpHeaderInfo.ResponseCode = httpUrlConnection.getResponseCode();
                    if (httpHeaderInfo.AllHeaders == null) {
                        httpHeaderInfo.AllHeaders = new HashMap();
                    }
                    int i = 0;
                    while (true) {
                        String headerFieldKey = httpUrlConnection.getHeaderFieldKey(i);
                        String headerField = httpUrlConnection.getHeaderField(i);
                        if (headerFieldKey == null && headerField == null) {
                            break;
                        }
                        if (!TextUtils.isEmpty(headerFieldKey)) {
                            if (!TextUtils.isEmpty(headerField)) {
                                httpHeaderInfo.AllHeaders.put(headerFieldKey, headerField);
                            }
                        }
                        i++;
                    }
                }
                return new DoneHandlerInputStream(httpUrlConnection.getInputStream());
            } catch (IOException e) {
                throw new IOException("IOException:" + e.getClass().getSimpleName());
            } catch (Throwable th) {
                throw new IOException(th.getMessage());
            }
        } else {
            throw new IllegalArgumentException("url");
        }
    }

    public static String encryptURL(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new String();
        return String.format("%s&key=%s", new Object[]{str, MD5.MD5_32(String.format("%sbe988a6134bc8254465424e5a70ef037", new Object[]{str}))});
    }

    public static String fromParamsMapToString(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry next : map.entrySet()) {
            if (!(next.getKey() == null || next.getValue() == null)) {
                try {
                    stringBuffer.append(URLEncoder.encode((String) next.getKey(), Keyczar.DEFAULT_ENCODING));
                    stringBuffer.append("=");
                    stringBuffer.append(URLEncoder.encode((String) next.getValue(), Keyczar.DEFAULT_ENCODING));
                    stringBuffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    Log.d("com.xiaomi.common.Network", "Failed to convert from params map to string: " + e.toString());
                    Log.d("com.xiaomi.common.Network", "map: " + map.toString());
                    return null;
                }
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static String getActiveConnPoint(Context context) {
        if (isWIFIConnected(context)) {
            return "wifi";
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "";
            }
            return (activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName() + "-" + activeNetworkInfo.getExtraInfo()).toLowerCase();
        } catch (Exception unused) {
            return "";
        }
    }

    public static int getActiveNetworkType(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return -1;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return -1;
            }
            return activeNetworkInfo.getType();
        } catch (Exception unused) {
            return -1;
        }
    }

    private static URL getDefaultStreamHandlerURL(String str) throws MalformedURLException {
        return new URL(str);
    }

    public static HttpURLConnection getHttpUrlConnection(Context context, URL url) throws IOException {
        return !"http".equals(url.getProtocol()) ? (HttpURLConnection) url.openConnection() : isCtwap(context) ? (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80))) : (HttpURLConnection) url.openConnection();
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return null;
            }
            return connectivityManager.getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean hasNetwork(Context context) {
        return getActiveNetworkType(context) >= 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v25, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v18 */
    /* JADX WARNING: type inference failed for: r6v24 */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f6, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f7, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f9, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00fa, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0105, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        r4 = th;
        r6 = r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00aa */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f6 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:22:0x006c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f9 A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:1:0x0006] */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static HttpResponse httpRequest(Context context, String str, String str2, Map<String, String> map, String str3) throws IOException {
        BufferedReader bufferedReader;
        HttpURLConnection httpUrlConnection;
        HttpResponse httpResponse = new HttpResponse();
        OutputStream outputStream = null;
        try {
            BufferedReader bufferedReader2 = str2;
            httpUrlConnection = getHttpUrlConnection(context, getDefaultStreamHandlerURL(str));
            httpUrlConnection.setConnectTimeout(10000);
            httpUrlConnection.setReadTimeout(15000);
            String str4 = str2;
            if (str2 == null) {
                str4 = "GET";
            }
            httpUrlConnection.setRequestMethod(str4);
            if (map != null) {
                for (String next : map.keySet()) {
                    httpUrlConnection.setRequestProperty(next, map.get(next));
                }
            }
            int i = 0;
            if (!TextUtils.isEmpty(str3)) {
                httpUrlConnection.setDoOutput(true);
                byte[] bytes = str3.getBytes();
                OutputStream outputStream2 = httpUrlConnection.getOutputStream();
                try {
                    outputStream2.write(bytes, 0, bytes.length);
                    outputStream2.flush();
                    outputStream2.close();
                } catch (IOException e) {
                    e = e;
                    OutputStream outputStream3 = outputStream2;
                } catch (Throwable th) {
                    th = th;
                    ? r6 = 0;
                    outputStream = outputStream2;
                    IOUtils.closeQuietly(outputStream);
                    IOUtils.closeQuietly(r6);
                    throw th;
                }
            }
            httpResponse.responseCode = httpUrlConnection.getResponseCode();
            Log.d("com.xiaomi.common.Network", "Http POST Response Code: " + httpResponse.responseCode);
            while (true) {
                String headerFieldKey = httpUrlConnection.getHeaderFieldKey(i);
                String headerField = httpUrlConnection.getHeaderField(i);
                if (headerFieldKey == null && headerField == null) {
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(new DoneHandlerInputStream(httpUrlConnection.getInputStream())));
                    bufferedReader3 = new BufferedReader(new InputStreamReader(new DoneHandlerInputStream(httpUrlConnection.getErrorStream())));
                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        String property = System.getProperty("line.separator");
                        for (String readLine = bufferedReader3.readLine(); readLine != null; readLine = bufferedReader3.readLine()) {
                            stringBuffer.append(readLine);
                            stringBuffer.append(property);
                        }
                        httpResponse.responseString = stringBuffer.toString();
                        bufferedReader3.close();
                        IOUtils.closeQuietly((Closeable) null);
                        IOUtils.closeQuietly((Closeable) null);
                        return httpResponse;
                    } catch (IOException e2) {
                        e = e2;
                        throw new IOException("err while request " + str + ":" + e.getClass().getSimpleName());
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader3;
                        bufferedReader2 = bufferedReader;
                        throw new IOException(th.getMessage());
                    }
                } else {
                    httpResponse.headers.put(headerFieldKey, headerField);
                    i = i + 1 + 1;
                }
            }
        } catch (IOException ) {
            bufferedReader3 = new BufferedReader(new InputStreamReader(new DoneHandlerInputStream(httpUrlConnection.getErrorStream())));
        } catch (Throwable th3) {
        }
    }

    public static boolean is2GConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (networkInfo == null || networkInfo.getType() != 0) {
            return false;
        }
        int subtype = networkInfo.getSubtype();
        return subtype == 1 || subtype == 2 || subtype == 4 || subtype == 7 || subtype == 11;
    }

    public static boolean is3GConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (networkInfo == null || networkInfo.getType() != 0) {
            return false;
        }
        String subtypeName = networkInfo.getSubtypeName();
        if (!"TD-SCDMA".equalsIgnoreCase(subtypeName) && !"CDMA2000".equalsIgnoreCase(subtypeName) && !"WCDMA".equalsIgnoreCase(subtypeName)) {
            switch (networkInfo.getSubtype()) {
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }

    public static boolean is4GConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return networkInfo != null && networkInfo.getType() == 0 && 13 == networkInfo.getSubtype();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo networkInfo;
        try {
            networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            networkInfo = null;
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isCtwap(Context context) {
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso())) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            String extraInfo = activeNetworkInfo.getExtraInfo();
            return !TextUtils.isEmpty(extraInfo) && extraInfo.length() >= 3 && extraInfo.contains("ctwap");
        } catch (Exception unused) {
        }
    }

    public static boolean isUsingMobileDataConnection(Context context) {
        return is4GConnected(context) || is3GConnected(context) || is2GConnected(context);
    }

    public static boolean isWIFIConnected(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && 1 == activeNetworkInfo.getType();
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.io.File} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v15, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v21, resolved type: java.io.File} */
    /* JADX WARNING: type inference failed for: r9v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: type inference failed for: r9v17 */
    /* JADX WARNING: type inference failed for: r9v20 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    public static String uploadFile(String str, Map<String, String> map, File file, String str2) throws IOException {
        ? r9;
        File file2;
        File file3;
        File file4;
        Object obj;
        FileInputStream fileInputStream = null;
        if (!file.exists()) {
            return null;
        }
        String name = file.getName();
        try {
            file2 = file;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");
            if (map != null) {
                for (Map.Entry next : map.entrySet()) {
                    httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
                }
            }
            httpURLConnection.setFixedLengthStreamingMode(name.length() + 77 + ((int) file.length()) + str2.length());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes("--*****\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + str2 + "\";filename=\"" + file.getName() + "\"" + "\r\n");
            dataOutputStream.writeBytes("\r\n");
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                    dataOutputStream.flush();
                }
                dataOutputStream.writeBytes("\r\n");
                dataOutputStream.writeBytes("--");
                dataOutputStream.writeBytes("*****");
                dataOutputStream.writeBytes("--");
                dataOutputStream.writeBytes("\r\n");
                dataOutputStream.flush();
                StringBuffer stringBuffer = new StringBuffer();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DoneHandlerInputStream(httpURLConnection.getInputStream())));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            stringBuffer.append(readLine);
                        } else {
                            String stringBuffer2 = stringBuffer.toString();
                            IOUtils.closeQuietly(fileInputStream2);
                            IOUtils.closeQuietly(bufferedReader);
                            return stringBuffer2;
                        }
                    } catch (IOException e) {
                        e = e;
                        FileInputStream fileInputStream3 = fileInputStream2;
                        throw new IOException("IOException:" + e.getClass().getSimpleName());
                    } catch (Throwable th) {
                        th = th;
                        obj = bufferedReader;
                        fileInputStream = fileInputStream2;
                        r9 = obj;
                        IOUtils.closeQuietly(fileInputStream);
                        IOUtils.closeQuietly(r9);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                FileInputStream fileInputStream32 = fileInputStream2;
                throw new IOException("IOException:" + e.getClass().getSimpleName());
            } catch (Throwable th2) {
                th = th2;
                obj = null;
                fileInputStream = fileInputStream2;
                r9 = obj;
                IOUtils.closeQuietly(fileInputStream);
                IOUtils.closeQuietly(r9);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            throw new IOException("IOException:" + e.getClass().getSimpleName());
        } catch (Throwable th3) {
            th = th3;
            r9 = file2;
            IOUtils.closeQuietly(fileInputStream);
            IOUtils.closeQuietly(r9);
            throw th;
        }
    }
}
