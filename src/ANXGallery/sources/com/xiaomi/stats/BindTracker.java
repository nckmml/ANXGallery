package com.xiaomi.stats;

import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.thrift.ChannelStatsType;
import com.xiaomi.push.thrift.StatsEvent;
import com.xiaomi.smack.Connection;
import com.xiaomi.stats.StatsAnalyser;

class BindTracker implements PushClientsManager.ClientLoginInfo.ClientStatusListener {
    private PushClientsManager.ClientLoginInfo client;
    private Connection connection;
    private XMPushService pushService;
    private int reason;
    private PushClientsManager.ClientStatus status;
    private boolean tracked = false;

    /* renamed from: com.xiaomi.stats.BindTracker$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$push$service$PushClientsManager$ClientStatus = new int[PushClientsManager.ClientStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$xiaomi$push$service$PushClientsManager$ClientStatus[PushClientsManager.ClientStatus.unbind.ordinal()] = 1;
            $SwitchMap$com$xiaomi$push$service$PushClientsManager$ClientStatus[PushClientsManager.ClientStatus.binding.ordinal()] = 2;
            try {
                $SwitchMap$com$xiaomi$push$service$PushClientsManager$ClientStatus[PushClientsManager.ClientStatus.binded.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    BindTracker(XMPushService xMPushService, PushClientsManager.ClientLoginInfo clientLoginInfo) {
        this.pushService = xMPushService;
        this.status = PushClientsManager.ClientStatus.binding;
        this.client = clientLoginInfo;
    }

    /* access modifiers changed from: private */
    public void done() {
        untrack();
        if (this.tracked && this.reason != 11) {
            StatsEvent createStatsEvent = StatsHandler.getInstance().createStatsEvent();
            int i = AnonymousClass2.$SwitchMap$com$xiaomi$push$service$PushClientsManager$ClientStatus[this.status.ordinal()];
            if (i == 1) {
                int i2 = this.reason;
                if (i2 == 17) {
                    createStatsEvent.type = ChannelStatsType.BIND_TCP_READ_TIMEOUT.getValue();
                } else if (i2 == 21) {
                    createStatsEvent.type = ChannelStatsType.BIND_TIMEOUT.getValue();
                } else {
                    try {
                        StatsAnalyser.TypeWraper fromBind = StatsAnalyser.fromBind(StatsHandler.getContext().getCaughtException());
                        createStatsEvent.type = fromBind.type.getValue();
                        createStatsEvent.setAnnotation(fromBind.annotation);
                    } catch (NullPointerException unused) {
                        createStatsEvent = null;
                    }
                }
            } else if (i != 2 && i == 3) {
                createStatsEvent.type = ChannelStatsType.BIND_SUCCESS.getValue();
            }
            if (createStatsEvent != null) {
                createStatsEvent.setHost(this.connection.getHost());
                createStatsEvent.setUser(this.client.userId);
                createStatsEvent.value = 1;
                try {
                    createStatsEvent.setChid((byte) Integer.parseInt(this.client.chid));
                } catch (NumberFormatException unused2) {
                }
                StatsHandler.getInstance().add(createStatsEvent);
            }
        }
    }

    private void untrack() {
        this.client.removeClientStatusListener(this);
    }

    public void onChange(PushClientsManager.ClientStatus clientStatus, PushClientsManager.ClientStatus clientStatus2, int i) {
        if (!this.tracked && clientStatus == PushClientsManager.ClientStatus.binding) {
            this.status = clientStatus2;
            this.reason = i;
            this.tracked = true;
        }
        this.pushService.executeJob(new XMPushService.Job(4) {
            public String getDesc() {
                return "Handling bind stats";
            }

            public void process() {
                BindTracker.this.done();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void track() {
        this.client.addClientStatusListener(this);
        this.connection = this.pushService.getCurrentConnection();
    }
}
