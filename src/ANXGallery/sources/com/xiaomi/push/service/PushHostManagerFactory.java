package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostFilter;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.protobuf.ChannelConfig;
import com.xiaomi.push.protobuf.ChannelMessage;
import com.xiaomi.push.service.ServiceConfig;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.util.StringUtils;
import com.xiaomi.stats.StatsHandler;
import com.xiaomi.stats.StatsHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class PushHostManagerFactory extends ServiceConfig.Listener implements HostManager.HostManagerFactory {
    private long lastFetchTime;
    private XMPushService pushService;

    static class GslbHttpGet implements HostManager.HttpGet {
        GslbHttpGet() {
        }

        public String doGet(String str) throws IOException {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter("sdkver", String.valueOf(38));
            buildUpon.appendQueryParameter("osver", String.valueOf(Build.VERSION.SDK_INT));
            buildUpon.appendQueryParameter("os", StringUtils.escapeForXML(Build.MODEL + ":" + Build.VERSION.INCREMENTAL));
            buildUpon.appendQueryParameter("mi", String.valueOf(SystemUtils.getMIUIType()));
            String builder = buildUpon.toString();
            MyLog.v("fetch bucket from : " + builder);
            URL url = new URL(builder);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String downloadXml = Network.downloadXml(SystemUtils.getContext(), url);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                StatsHelper.statsGslb(url.getHost() + ":" + port, (int) currentTimeMillis2, (Exception) null);
                return downloadXml;
            } catch (IOException e) {
                StatsHelper.statsGslb(url.getHost() + ":" + port, -1, e);
                throw e;
            }
        }
    }

    static class PushHostManager extends HostManager {
        protected PushHostManager(Context context, HostFilter hostFilter, HostManager.HttpGet httpGet, String str) {
            super(context, hostFilter, httpGet, str);
        }

        /* access modifiers changed from: protected */
        public String getRemoteFallbackJSON(ArrayList<String> arrayList, String str, String str2, boolean z) throws IOException {
            try {
                if (StatsHandler.getInstance().isAllowStats()) {
                    str2 = ServiceConfig.getDeviceUUID();
                }
                return super.getRemoteFallbackJSON(arrayList, str, str2, z);
            } catch (IOException e) {
                StatsHelper.stats(0, ChannelStatsType.GSLB_ERR.getValue(), 1, (String) null, Network.hasNetwork(sAppContext) ? 1 : 0);
                throw e;
            }
        }
    }

    PushHostManagerFactory(XMPushService xMPushService) {
        this.pushService = xMPushService;
    }

    public static void init(XMPushService xMPushService) {
        PushHostManagerFactory pushHostManagerFactory = new PushHostManagerFactory(xMPushService);
        ServiceConfig.getInstance().addListener(pushHostManagerFactory);
        synchronized (HostManager.class) {
            HostManager.setHostManagerFactory(pushHostManagerFactory);
            HostManager.init(xMPushService, (HostFilter) null, new GslbHttpGet(), MovieStatUtils.DOWNLOAD_SUCCESS, "push", "2.2");
        }
    }

    public HostManager createHostManager(Context context, HostFilter hostFilter, HostManager.HttpGet httpGet, String str) {
        return new PushHostManager(context, hostFilter, httpGet, str);
    }

    public void onConfigChange(ChannelConfig.PushServiceConfig pushServiceConfig) {
    }

    public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg pushServiceConfigMsg) {
        Fallback fallbacksByHost;
        if (pushServiceConfigMsg.hasFetchBucket() && pushServiceConfigMsg.getFetchBucket() && System.currentTimeMillis() - this.lastFetchTime > 3600000) {
            MyLog.w("fetch bucket :" + pushServiceConfigMsg.getFetchBucket());
            this.lastFetchTime = System.currentTimeMillis();
            HostManager instance = HostManager.getInstance();
            instance.clear();
            instance.refreshFallbacks();
            Connection currentConnection = this.pushService.getCurrentConnection();
            if (currentConnection != null && (fallbacksByHost = instance.getFallbacksByHost(currentConnection.getConfiguration().getHost())) != null) {
                ArrayList<String> hosts = fallbacksByHost.getHosts();
                boolean z = true;
                Iterator<String> it = hosts.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().equals(currentConnection.getHost())) {
                            z = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (z && !hosts.isEmpty()) {
                    MyLog.w("bucket changed, force reconnect");
                    this.pushService.disconnect(0, (Exception) null);
                    this.pushService.scheduleConnect(false);
                }
            }
        }
    }
}
