package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.stats.StatsHandler;

class ReconnectionManager {
    private static int MAX_RETRY_INTERVAL = 300000;
    private int attempts = 0;
    private int curDelay;
    private long lastConnectTime;
    private XMPushService mPushService;
    private int shortLiveConnCount = 0;

    public ReconnectionManager(XMPushService xMPushService) {
        this.mPushService = xMPushService;
        this.curDelay = 500;
        this.lastConnectTime = 0;
    }

    private int timeDelay() {
        double d;
        if (this.attempts > 8) {
            return 300000;
        }
        double random = (Math.random() * 2.0d) + 1.0d;
        int i = this.attempts;
        if (i > 4) {
            d = 60000.0d;
        } else if (i > 1) {
            d = 10000.0d;
        } else if (this.lastConnectTime == 0) {
            return 0;
        } else {
            if (System.currentTimeMillis() - this.lastConnectTime < 310000) {
                int i2 = this.curDelay;
                int i3 = MAX_RETRY_INTERVAL;
                if (i2 >= i3) {
                    return i2;
                }
                this.shortLiveConnCount++;
                if (this.shortLiveConnCount >= 4) {
                    return i3;
                }
                this.curDelay = (int) (((double) i2) * 1.5d);
                return i2;
            }
            this.curDelay = 1000;
            this.shortLiveConnCount = 0;
            return 0;
        }
        return (int) (random * d);
    }

    public void onConnectSucceeded() {
        this.lastConnectTime = System.currentTimeMillis();
        this.mPushService.removeJobs(1);
        this.attempts = 0;
    }

    public void tryReconnect(boolean z) {
        if (!this.mPushService.shouldReconnect()) {
            MyLog.v("should not reconnect as no client or network.");
        } else if (z) {
            if (!this.mPushService.hasJob(1)) {
                this.attempts++;
            }
            this.mPushService.removeJobs(1);
            XMPushService xMPushService = this.mPushService;
            xMPushService.getClass();
            xMPushService.executeJob(new XMPushService.ConnectJob());
        } else if (!this.mPushService.hasJob(1)) {
            int timeDelay = timeDelay();
            this.attempts++;
            MyLog.w("schedule reconnect in " + timeDelay + "ms");
            XMPushService xMPushService2 = this.mPushService;
            xMPushService2.getClass();
            xMPushService2.executeJobDelayed(new XMPushService.ConnectJob(), (long) timeDelay);
            if (this.attempts == 2 && StatsHandler.getInstance().isAllowStats()) {
                NetworkCheckup.dumpNativeNetInfo();
            }
            if (this.attempts == 3) {
                NetworkCheckup.connectivityTest();
            }
        }
    }
}
