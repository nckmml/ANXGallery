package com.xiaomi.clientreport.job;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.clientreport.manager.ClientReportLogicManager;

public class EventUploadJob extends ScheduledJobManager.Job {
    private Context mContext;

    public EventUploadJob(Context context) {
        this.mContext = context;
    }

    private boolean checkEventNeedUpload() {
        return ClientReportLogicManager.getInstance(this.mContext).getConfig().isEventUploadSwitchOpen();
    }

    public int getJobId() {
        return 100886;
    }

    public void run() {
        try {
            if (checkEventNeedUpload()) {
                MyLog.v(this.mContext.getPackageName() + " begin upload event");
                ClientReportLogicManager.getInstance(this.mContext).sendEvent();
            }
        } catch (Exception e) {
            MyLog.e((Throwable) e);
        }
    }
}
