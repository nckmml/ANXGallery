package com.miui.gallery.cloud.jobs;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import com.miui.gallery.cloud.taskmanager.Task;
import com.miui.gallery.cloud.taskmanager.TaskManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SyncJobService extends JobService {
    private TaskManager mTaskManager;

    private static class JobFactory {
        public static AbsSyncJob createJob(int i) {
            Class cls = i != 10000 ? null : BackDownloadJob.class;
            if (cls != null) {
                try {
                    AbsSyncJob newInstance = cls.newInstance();
                    newInstance.setJobId(i);
                    return newInstance;
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
            return null;
        }
    }

    private class JobTask extends Task {
        private JobParameters mParameters;

        JobTask(JobParameters jobParameters) {
            this.mParameters = jobParameters;
        }

        public int compareTo(Task task) {
            return 0;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof JobTask)) {
                return false;
            }
            JobTask jobTask = (JobTask) obj;
            return (getParameters() == null && jobTask.getParameters() == null) || !(getParameters() == null || jobTask.getParameters() == null || getParameters().getJobId() != jobTask.getParameters().getJobId());
        }

        public JobParameters getParameters() {
            return this.mParameters;
        }

        public int hashCode() {
            JobParameters jobParameters = this.mParameters;
            if (jobParameters == null) {
                return 0;
            }
            return jobParameters.getJobId();
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
        public Object run(ThreadPool.JobContext jobContext) {
            AbsSyncJob absSyncJob;
            Object obj = null;
            try {
                if (this.mParameters != null) {
                    absSyncJob = JobFactory.createJob(this.mParameters.getJobId());
                    if (absSyncJob != null) {
                        try {
                            obj = absSyncJob.execute();
                        } catch (Throwable th) {
                            AbsSyncJob absSyncJob2 = absSyncJob;
                            th = th;
                            obj = absSyncJob2;
                            SyncJobService.this.jobFinished(this.mParameters, false);
                            if (obj != null) {
                            }
                            throw th;
                        }
                    }
                } else {
                    absSyncJob = null;
                }
                SyncJobService.this.jobFinished(this.mParameters, false);
                if (absSyncJob != null) {
                    GallerySamplingStatHelper.recordCountEvent("Sync", String.format(Locale.US, "sync_job_%s", new Object[]{absSyncJob.getClass().getSimpleName()}));
                }
                return obj;
            } catch (Throwable th2) {
                th = th2;
                SyncJobService.this.jobFinished(this.mParameters, false);
                if (obj != null) {
                    GallerySamplingStatHelper.recordCountEvent("Sync", String.format(Locale.US, "sync_job_%s", new Object[]{obj.getClass().getSimpleName()}));
                }
                throw th;
            }
        }
    }

    public static int scheduleJob(Context context, int i) {
        if (context == null) {
            return 0;
        }
        AbsSyncJob createJob = JobFactory.createJob(i);
        if (createJob != null) {
            return scheduleJob(context, createJob);
        }
        return 1;
    }

    private static int scheduleJob(Context context, AbsSyncJob absSyncJob) {
        boolean z;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
        JobInfo jobInfo = absSyncJob.getJobInfo(context, new ComponentName(context, SyncJobService.class));
        Iterator<JobInfo> it = allPendingJobs.iterator();
        while (true) {
            if (it.hasNext()) {
                if (absSyncJob.equals(jobInfo, it.next())) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            Log.d("SyncJobService", "scheduledJob %d", (Object) Integer.valueOf(absSyncJob.getJobId()));
            return 1;
        }
        if (jobInfo == null) {
            jobScheduler.cancel(absSyncJob.getJobId());
            Log.d("SyncJobService", "cancel Job %d", (Object) Integer.valueOf(absSyncJob.getJobId()));
        } else if (jobScheduler.schedule(jobInfo) > 0) {
            Log.d("SyncJobService", "scheduleJob %d success", (Object) Integer.valueOf(absSyncJob.getJobId()));
            return 1;
        }
        return 0;
    }

    public void onCreate() {
        super.onCreate();
        this.mTaskManager = new TaskManager(2);
    }

    public void onDestroy() {
        super.onDestroy();
        TaskManager taskManager = this.mTaskManager;
        if (taskManager != null) {
            taskManager.shutdown();
            this.mTaskManager = null;
        }
    }

    public boolean onStartJob(JobParameters jobParameters) {
        if (jobParameters != null) {
            Log.i("SyncJobService", "onStartJob %s", (Object) Integer.valueOf(jobParameters.getJobId()));
        }
        TaskManager taskManager = this.mTaskManager;
        if (taskManager == null) {
            return true;
        }
        taskManager.submit(new JobTask(jobParameters));
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
