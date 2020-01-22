package com.xiaomi.clientreport.job;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.clientreport.manager.ClientReportLogicManager;

public class PerfUploadJob extends ScheduledJobManager.Job {
    private Context mContext;

    public PerfUploadJob(Context context) {
        this.mContext = context;
    }

    private boolean checkPerfNeedUpload() {
        return ClientReportLogicManager.getInstance(this.mContext).getConfig().isPerfUploadSwitchOpen();
    }

    public int getJobId() {
        return 100887;
    }

    public void run() {
        try {
            if (checkPerfNeedUpload()) {
                ClientReportLogicManager.getInstance(this.mContext).sendPerf();
                MyLog.v(this.mContext.getPackageName() + "perf  begin upload");
            }
        } catch (Exception e) {
            MyLog.e((Throwable) e);
        }
    }
}
