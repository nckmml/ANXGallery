package com.xiaomi.push.mpcd;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.JobMutualExclusor;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.push.mpcd.job.BroadcastActionCollectionjob;
import com.xiaomi.push.mpcd.job.CollectionJob;
import com.xiaomi.push.mpcd.receivers.BroadcastActionsReceiver;
import com.xiaomi.push.service.OnlineConfig;
import com.xiaomi.xmpush.thrift.ClientCollectionType;
import com.xiaomi.xmpush.thrift.ConfigKey;
import com.xiaomi.xmpush.thrift.DataCollectionItem;

public class CDEntrance {
    private static IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        return intentFilter;
    }

    private static IntentHandler getIntentHandler() {
        return new IntentHandler() {
            /* access modifiers changed from: private */
            public void handleIntent(Context context, Intent intent) {
                try {
                    String dataString = intent.getDataString();
                    if (!TextUtils.isEmpty(dataString)) {
                        String[] split = dataString.split(":");
                        if (split.length < 2) {
                            return;
                        }
                        if (!TextUtils.isEmpty(split[1])) {
                            String str = split[1];
                            long currentTimeMillis = System.currentTimeMillis();
                            boolean booleanValue = OnlineConfig.getInstance(context).getBooleanValue(ConfigKey.BroadcastActionCollectionSwitch.getValue(), true);
                            if (TextUtils.equals("android.intent.action.PACKAGE_RESTARTED", intent.getAction())) {
                                if (!JobMutualExclusor.checkPeriodAndRecordWithFileLock(context, String.valueOf(12), 1)) {
                                    return;
                                }
                                if (booleanValue) {
                                    if (TextUtils.isEmpty(BroadcastActionCollectionjob.mRestartedActions)) {
                                        BroadcastActionCollectionjob.mRestartedActions += Constants.ACTION_PACKAGE_RESTARTED + ":";
                                    }
                                    BroadcastActionCollectionjob.mRestartedActions += str + "(" + currentTimeMillis + ")" + ",";
                                }
                            } else if (TextUtils.equals("android.intent.action.PACKAGE_CHANGED", intent.getAction())) {
                                if (!JobMutualExclusor.checkPeriodAndRecordWithFileLock(context, String.valueOf(12), 1)) {
                                    return;
                                }
                                if (booleanValue) {
                                    if (TextUtils.isEmpty(BroadcastActionCollectionjob.mChangedActions)) {
                                        BroadcastActionCollectionjob.mChangedActions += Constants.ACTION_PACKAGE_CHANGED + ":";
                                    }
                                    BroadcastActionCollectionjob.mChangedActions += str + "(" + currentTimeMillis + ")" + ",";
                                }
                            } else if (TextUtils.equals("android.intent.action.PACKAGE_ADDED", intent.getAction())) {
                                if (!intent.getExtras().getBoolean("android.intent.extra.REPLACING") && booleanValue) {
                                    writeActionInfo(context, String.valueOf(ClientCollectionType.BroadcastActionAdded.getValue()), str);
                                }
                            } else if (TextUtils.equals("android.intent.action.PACKAGE_REMOVED", intent.getAction())) {
                                if (!intent.getExtras().getBoolean("android.intent.extra.REPLACING") && booleanValue) {
                                    writeActionInfo(context, String.valueOf(ClientCollectionType.BroadcastActionRemoved.getValue()), str);
                                }
                            } else if (TextUtils.equals("android.intent.action.PACKAGE_REPLACED", intent.getAction())) {
                                if (booleanValue) {
                                    writeActionInfo(context, String.valueOf(ClientCollectionType.BroadcastActionReplaced.getValue()), str);
                                }
                            } else if (TextUtils.equals("android.intent.action.PACKAGE_DATA_CLEARED", intent.getAction()) && booleanValue) {
                                writeActionInfo(context, String.valueOf(ClientCollectionType.BroadcastActionDataCleared.getValue()), str);
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
            }

            private void writeActionInfo(Context context, String str, String str2) {
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                    try {
                        if (JobMutualExclusor.checkPeriodAndRecordWithFileLock(context, String.valueOf(12), 1)) {
                            DataCollectionItem dataCollectionItem = new DataCollectionItem();
                            dataCollectionItem.setContent(str + ":" + str2);
                            dataCollectionItem.setCollectedAt(System.currentTimeMillis());
                            dataCollectionItem.setCollectionType(ClientCollectionType.BroadcastAction);
                            CollectionJob.writeItemToFile(context, dataCollectionItem);
                        }
                    } catch (Throwable unused) {
                    }
                }
            }

            public void handle(final Context context, final Intent intent) {
                if (intent != null) {
                    ScheduledJobManager.getInstance(context).addOneShootJob(new Runnable() {
                        public void run() {
                            AnonymousClass1.this.handleIntent(context, intent);
                        }
                    });
                }
            }
        };
    }

    public static void start(Context context) {
        JobController.getInstance(context).schedulerJob();
        try {
            context.registerReceiver(new BroadcastActionsReceiver(getIntentHandler()), getIntentFilter());
        } catch (Throwable th) {
            MyLog.e(th);
        }
    }
}
