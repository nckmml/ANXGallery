package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.file.IOUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.network.Host;
import com.xiaomi.push.protobuf.ChannelConfig;
import com.xiaomi.stats.StatsHandler;
import com.xiaomi.stats.StatsHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class NetworkCheckup {
    private static final Pattern IP_PATTERN = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
    private static long lastCheckTime = 0;
    private static ThreadPoolExecutor sExecutor = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static void connectivityTest() {
        ChannelConfig.PushServiceConfig config;
        long currentTimeMillis = System.currentTimeMillis();
        if ((sExecutor.getActiveCount() <= 0 || currentTimeMillis - lastCheckTime >= 1800000) && StatsHandler.getInstance().isAllowStats() && (config = ServiceConfig.getInstance().getConfig()) != null && config.getTestHostsCount() > 0) {
            lastCheckTime = currentTimeMillis;
            connectivityTest(config.getTestHostsList(), true);
        }
    }

    public static void connectivityTest(final List<String> list, final boolean z) {
        sExecutor.execute(new Runnable() {
            public void run() {
                int i;
                boolean access$200 = NetworkCheckup.doConnectTest("www.baidu.com:80");
                Iterator it = list.iterator();
                while (true) {
                    i = 1;
                    if (!it.hasNext()) {
                        break;
                    }
                    access$200 = access$200 || NetworkCheckup.doConnectTest((String) it.next());
                    if (access$200 && !z) {
                        break;
                    }
                }
                if (!access$200) {
                    i = 2;
                }
                StatsHelper.count(i);
            }
        });
    }

    /* access modifiers changed from: private */
    public static boolean doConnectTest(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            MyLog.w("ConnectivityTest: begin to connect to " + str);
            Socket socket = new Socket();
            socket.connect(Host.from(str, 5222), 5000);
            socket.setTcpNoDelay(true);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            MyLog.w("ConnectivityTest: connect to " + str + " in " + currentTimeMillis2);
            socket.close();
            return true;
        } catch (Throwable th) {
            MyLog.e("ConnectivityTest: could not connect to:" + str + " exception: " + th.getClass().getSimpleName() + " description: " + th.getMessage());
            return false;
        }
    }

    public static void dumpNativeNetInfo() {
        String readFile = readFile("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(readFile)) {
            MyLog.w("dump tcp for uid = " + Process.myUid());
            MyLog.w(readFile);
        }
        String readFile2 = readFile("/proc/self/net/tcp6");
        if (!TextUtils.isEmpty(readFile2)) {
            MyLog.w("dump tcp6 for uid = " + Process.myUid());
            MyLog.w(readFile2);
        }
    }

    private static String readFile(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append("\n");
                        sb.append(readLine);
                    } else {
                        String sb2 = sb.toString();
                        IOUtils.closeQuietly(bufferedReader);
                        return sb2;
                    }
                }
            } catch (Exception unused) {
                IOUtils.closeQuietly(bufferedReader);
                return null;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly(bufferedReader);
                throw th;
            }
        } catch (Exception unused2) {
            bufferedReader = null;
            IOUtils.closeQuietly(bufferedReader);
            return null;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            IOUtils.closeQuietly(bufferedReader);
            throw th;
        }
    }
}
