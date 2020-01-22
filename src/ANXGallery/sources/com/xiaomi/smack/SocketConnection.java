package com.xiaomi.smack;

import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.Host;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.slim.Blob;
import com.xiaomi.smack.util.TaskExecutor;
import com.xiaomi.stats.StatsHelper;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class SocketConnection extends Connection {
    private String connectedHost;
    String connectionID = null;
    private int curShortConnCount;
    protected Exception failedException = null;
    protected volatile long lastConnectedTime = 0;
    protected volatile long lastPingReceived = 0;
    protected volatile long lastPingSent = 0;
    protected XMPushService pushService;
    protected Socket socket;

    public SocketConnection(XMPushService xMPushService, ConnectionConfiguration connectionConfiguration) {
        super(xMPushService, connectionConfiguration);
        this.pushService = xMPushService;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00d8, code lost:
        r1.lastConnectedTime = android.os.SystemClock.elapsedRealtime();
        com.xiaomi.channel.commonutils.logger.MyLog.w("connected to " + r8 + " in " + r1.connectTime);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00fc, code lost:
        r18 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x012c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x012d, code lost:
        r22 = r8;
        r9 = r12;
        r8 = r13;
        r20 = r14;
        r18 = r10;
        r14 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0138, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0139, code lost:
        r9 = r12;
        r8 = r13;
        r20 = r14;
        r18 = r10;
        r14 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0142, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0143, code lost:
        r22 = r8;
        r9 = r12;
        r8 = r13;
        r20 = r14;
        r23 = r15;
        r18 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x015b, code lost:
        com.xiaomi.channel.commonutils.logger.MyLog.e("SMACK: Could not connect to:" + r8);
        r9.append("SMACK: Could not connect to ");
        r9.append(r8);
        r9.append(" port:");
        r9.append(r2);
        r9.append(" err:");
        r9.append(r1.failedException.getClass().getSimpleName());
        r9.append("\n");
        com.xiaomi.stats.StatsHelper.connectFail(r8, r1.failedException);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0191, code lost:
        if (r20 != null) goto L_0x0193;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0193, code lost:
        r20.failedHost(r8, java.lang.System.currentTimeMillis() - r16, 0, r1.failedException);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x01a5, code lost:
        r14 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x01b1, code lost:
        if (android.text.TextUtils.equals(r14, com.xiaomi.channel.commonutils.network.Network.getActiveConnPoint(r1.pushService)) == false) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x01b5, code lost:
        r14 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01cd, code lost:
        com.xiaomi.channel.commonutils.logger.MyLog.e("SMACK: Could not connect to:" + r8);
        r9.append("SMACK: Could not connect to ");
        r9.append(r8);
        r9.append(" port:");
        r9.append(r2);
        r9.append(" err:");
        r9.append(r1.failedException.getClass().getSimpleName());
        r9.append("\n");
        com.xiaomi.stats.StatsHelper.connectFail(r8, r1.failedException);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0203, code lost:
        if (r20 != null) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0205, code lost:
        r11 = r8;
        r8 = r14;
        r20.failedHost(r11, java.lang.System.currentTimeMillis() - r16, 0, r1.failedException);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x021a, code lost:
        r8 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0225, code lost:
        if (android.text.TextUtils.equals(r8, com.xiaomi.channel.commonutils.network.Network.getActiveConnPoint(r1.pushService)) == false) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x028f, code lost:
        if (android.text.TextUtils.equals(r5, com.xiaomi.channel.commonutils.network.Network.getActiveConnPoint(r1.pushService)) == false) goto L_0x0296;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0138 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:10:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0142 A[ExcHandler: Throwable (th java.lang.Throwable), Splitter:B:10:0x0077] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0237  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x029f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x02a0  */
    private void connectDirectly(String str, int i) throws XMPPException {
        StringBuilder sb;
        boolean z;
        Fallback fallback;
        String str2;
        String str3;
        String str4;
        Iterator<String> it;
        String str5;
        boolean z2;
        String str6;
        String str7 = str;
        int i2 = i;
        this.failedException = null;
        ArrayList<String> arrayList = new ArrayList<>();
        int intValue = MyLog.ps("get bucket for host : " + str7).intValue();
        Fallback fallback2 = getFallback(str);
        MyLog.pe(Integer.valueOf(intValue));
        int i3 = 1;
        if (fallback2 != null) {
            arrayList = fallback2.getHosts(true);
        }
        if (arrayList.isEmpty()) {
            arrayList.add(str7);
        }
        this.lastConnectedTime = 0;
        String activeConnPoint = Network.getActiveConnPoint(this.pushService);
        StringBuilder sb2 = new StringBuilder();
        Iterator<String> it2 = arrayList.iterator();
        boolean z3 = false;
        while (true) {
            if (!it2.hasNext()) {
                sb = sb2;
                z = z3;
                break;
            }
            String next = it2.next();
            long currentTimeMillis = System.currentTimeMillis();
            this.connTimes += i3;
            try {
                MyLog.w("begin to connect to " + next);
                this.socket = createSocket();
                this.socket.connect(Host.from(next, i2), 8000);
                MyLog.w("tcp connected");
                this.socket.setTcpNoDelay(true);
                this.connectedHost = next;
                initConnection();
                try {
                    this.connectTime = System.currentTimeMillis() - currentTimeMillis;
                    this.connectionPoint = activeConnPoint;
                    if (fallback2 == null) {
                        Iterator<String> it3 = it2;
                        sb = sb2;
                        str3 = next;
                        Fallback fallback3 = fallback2;
                        String str8 = activeConnPoint;
                        break;
                    }
                    it = it2;
                    sb = sb2;
                    str3 = next;
                    fallback = fallback2;
                    str6 = activeConnPoint;
                    try {
                        fallback2.succeedHost(next, this.connectTime, 0);
                        break;
                    } catch (Exception e) {
                        e = e;
                        str5 = str6;
                        z2 = true;
                        try {
                            this.failedException = e;
                            if (!z) {
                            }
                            String str9 = str2;
                            z3 = z;
                            activeConnPoint = str9;
                            sb2 = sb;
                            fallback2 = fallback;
                            it2 = it;
                            i3 = 1;
                        } catch (Throwable th) {
                            th = th;
                            if (!z) {
                                MyLog.e("SMACK: Could not connect to:" + str3);
                                sb.append("SMACK: Could not connect to ");
                                sb.append(str3);
                                sb.append(" port:");
                                sb.append(i2);
                                sb.append(" err:");
                                sb.append(this.failedException.getClass().getSimpleName());
                                sb.append("\n");
                                StatsHelper.connectFail(str3, this.failedException);
                                if (fallback != null) {
                                    str4 = str2;
                                    fallback.failedHost(str3, System.currentTimeMillis() - currentTimeMillis, 0, this.failedException);
                                } else {
                                    str4 = str2;
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        str2 = str6;
                        z = true;
                        if (!z) {
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    it = it2;
                    sb = sb2;
                    str3 = next;
                    fallback = fallback2;
                    str5 = activeConnPoint;
                    z2 = true;
                    this.failedException = e;
                    if (!z) {
                    }
                    String str92 = str2;
                    z3 = z;
                    activeConnPoint = str92;
                    sb2 = sb;
                    fallback2 = fallback;
                    it2 = it;
                    i3 = 1;
                } catch (Throwable th3) {
                    th = th3;
                    sb = sb2;
                    str3 = next;
                    fallback = fallback2;
                    str2 = activeConnPoint;
                    z = true;
                    if (!z) {
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                it = it2;
                sb = sb2;
                str3 = next;
                fallback = fallback2;
                str5 = activeConnPoint;
                z2 = z3;
                this.failedException = e;
                if (!z) {
                }
                String str922 = str2;
                z3 = z;
                activeConnPoint = str922;
                sb2 = sb;
                fallback2 = fallback;
                it2 = it;
                i3 = 1;
            } catch (Throwable th4) {
            }
            z3 = z;
            activeConnPoint = str922;
            sb2 = sb;
            fallback2 = fallback;
            it2 = it;
            i3 = 1;
        }
        HostManager.getInstance().persist();
        if (!z) {
            throw new XMPPException(sb.toString());
        }
    }

    private void connectUsingConfiguration(ConnectionConfiguration connectionConfiguration) throws XMPPException, IOException {
        connectDirectly(connectionConfiguration.getHost(), connectionConfiguration.getPort());
    }

    public void batchSend(Blob[] blobArr) throws XMPPException {
        throw new XMPPException("Don't support send Blob");
    }

    public synchronized void connect() throws XMPPException {
        try {
            if (!isConnected()) {
                if (!isConnecting()) {
                    setConnectionStatus(0, 0, (Exception) null);
                    connectUsingConfiguration(this.config);
                    return;
                }
            }
            MyLog.w("WARNING: current xmpp has connected");
        } catch (IOException e) {
            throw new XMPPException((Throwable) e);
        }
    }

    public Socket createSocket() {
        return new Socket();
    }

    public void disconnect(int i, Exception exc) {
        shutdown(i, exc);
        if ((exc != null || i == 18) && this.lastConnectedTime != 0) {
            sinkdownHost(exc);
        }
    }

    public String getChallenge() {
        return this.challenge;
    }

    /* access modifiers changed from: package-private */
    public Fallback getFallback(final String str) {
        Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(str, false);
        if (!fallbacksByHost.isEffective()) {
            TaskExecutor.execute((Runnable) new Runnable() {
                public void run() {
                    HostManager.getInstance().getFallbacksByHost(str, true);
                }
            });
        }
        return fallbacksByHost;
    }

    public String getHost() {
        return this.connectedHost;
    }

    /* access modifiers changed from: protected */
    public synchronized void initConnection() throws XMPPException, IOException {
    }

    public void notifyConnectionError(final int i, final Exception exc) {
        this.pushService.executeJob(new XMPushService.Job(2) {
            public String getDesc() {
                return "shutdown the connection. " + i + ", " + exc;
            }

            public void process() {
                SocketConnection.this.pushService.disconnect(i, exc);
            }
        });
    }

    public void ping(boolean z) throws XMPPException {
        final long currentTimeMillis = System.currentTimeMillis();
        sendPing(z);
        if (!z) {
            this.pushService.executeJobDelayed(new XMPushService.Job(13) {
                public String getDesc() {
                    return "check the ping-pong." + currentTimeMillis;
                }

                public void process() {
                    Thread.yield();
                    if (SocketConnection.this.isConnected() && !SocketConnection.this.isReadAlive(currentTimeMillis)) {
                        SocketConnection.this.pushService.disconnect(22, (Exception) null);
                    }
                }
            }, 10000);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void sendPing(boolean z) throws XMPPException;

    /* access modifiers changed from: protected */
    public synchronized void shutdown(int i, Exception exc) {
        if (getConnectionStatus() != 2) {
            setConnectionStatus(2, i, exc);
            this.challenge = "";
            try {
                this.socket.close();
            } catch (Throwable unused) {
            }
            this.lastPingSent = 0;
            this.lastPingReceived = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void sinkdownHost(Exception exc) {
        if (SystemClock.elapsedRealtime() - this.lastConnectedTime >= 300000) {
            this.curShortConnCount = 0;
        } else if (Network.hasNetwork(this.pushService)) {
            this.curShortConnCount++;
            if (this.curShortConnCount >= 2) {
                String host = getHost();
                MyLog.w("max short conn time reached, sink down current host:" + host);
                sinkdownHost(host, 0, exc);
                this.curShortConnCount = 0;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void sinkdownHost(String str, long j, Exception exc) {
        Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(ConnectionConfiguration.getXmppServerHost(), false);
        if (fallbacksByHost != null) {
            fallbacksByHost.failedHost(str, j, 0, exc);
            HostManager.getInstance().persist();
        }
    }

    public void updateLastReceived() {
        this.lastPingReceived = SystemClock.elapsedRealtime();
    }

    public void updateLastSent() {
        this.lastPingSent = SystemClock.elapsedRealtime();
    }
}
