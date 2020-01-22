package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.lang.ref.WeakReference;

public class AwakeAppPingJob extends ScheduledJobManager.Job {
    private boolean mIsCache = false;
    private WeakReference<XMPushService> mXMPushServiceWR;
    private XmPushActionNotification mXmPushActionNotification;

    public AwakeAppPingJob(XmPushActionNotification xmPushActionNotification, WeakReference<XMPushService> weakReference, boolean z) {
        this.mXmPushActionNotification = xmPushActionNotification;
        this.mXMPushServiceWR = weakReference;
        this.mIsCache = z;
    }

    public int getJobId() {
        return 22;
    }

    public void run() {
        XMPushService xMPushService;
        WeakReference<XMPushService> weakReference = this.mXMPushServiceWR;
        if (weakReference != null && this.mXmPushActionNotification != null && (xMPushService = (XMPushService) weakReference.get()) != null) {
            this.mXmPushActionNotification.setId(PacketHelper.generatePacketID());
            this.mXmPushActionNotification.setRequireAck(false);
            MyLog.v("MoleInfo aw_ping : send aw_Ping msg " + this.mXmPushActionNotification.getId());
            try {
                String packageName = this.mXmPushActionNotification.getPackageName();
                xMPushService.sendMessage(packageName, XmPushThriftSerializeUtils.convertThriftObjectToBytes(MIPushHelper.generateRequestContainer(packageName, this.mXmPushActionNotification.getAppId(), this.mXmPushActionNotification, ActionType.Notification)), this.mIsCache);
            } catch (Exception e) {
                MyLog.e("MoleInfo aw_ping : send help app ping error" + e.toString());
            }
        }
    }
}
