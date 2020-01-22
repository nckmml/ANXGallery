package com.xiaomi.push.service.timers;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMJobService;
import com.xiaomi.push.service.timers.Alarm;
import com.xiaomi.smack.SmackConfiguration;

@TargetApi(21)
public class AlarmV21 implements Alarm.IAlarm {
    Context mContext;
    JobScheduler mJobScheduler;
    private boolean mStarted = false;

    AlarmV21(Context context) {
        this.mContext = context;
        this.mJobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
    }

    public boolean isAlive() {
        return this.mStarted;
    }

    /* access modifiers changed from: package-private */
    public void register(long j) {
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this.mContext.getPackageName(), XMJobService.class.getName()));
        builder.setMinimumLatency(j);
        builder.setOverrideDeadline(j);
        builder.setRequiredNetworkType(1);
        builder.setPersisted(false);
        JobInfo build = builder.build();
        MyLog.v("schedule Job = " + build.getId() + " in " + j);
        this.mJobScheduler.schedule(builder.build());
    }

    public void registerPing(boolean z) {
        if (z || this.mStarted) {
            long pingInteval = (long) SmackConfiguration.getPingInteval();
            if (z) {
                stop();
                pingInteval -= SystemClock.elapsedRealtime() % pingInteval;
            }
            this.mStarted = true;
            register(pingInteval);
        }
    }

    public void stop() {
        this.mStarted = false;
        this.mJobScheduler.cancel(1);
    }
}
