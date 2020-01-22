package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.ScheduledJobManager;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.service.clientReport.PushClientReportManager;
import com.xiaomi.push.service.xmpush.Command;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MessageHandleService extends BaseService {
    private static ConcurrentLinkedQueue<MessageHandleJob> jobQueue = new ConcurrentLinkedQueue<>();
    private static ExecutorService sPool = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static class MessageHandleJob {
        private Intent intent;
        private PushMessageReceiver receiver;

        public MessageHandleJob(Intent intent2, PushMessageReceiver pushMessageReceiver) {
            this.receiver = pushMessageReceiver;
            this.intent = intent2;
        }

        public Intent getIntent() {
            return this.intent;
        }

        public PushMessageReceiver getReceiver() {
            return this.receiver;
        }
    }

    public static void addJob(Context context, MessageHandleJob messageHandleJob) {
        if (messageHandleJob != null) {
            jobQueue.add(messageHandleJob);
            scheduleJob(context);
            startService(context);
        }
    }

    protected static void onHandleIntent(Context context, Intent intent) {
        if (intent != null) {
            scheduleJob(context);
        }
    }

    /* access modifiers changed from: private */
    public static void processJob(Context context) {
        String[] stringArrayExtra;
        try {
            MessageHandleJob poll = jobQueue.poll();
            if (poll != null) {
                PushMessageReceiver receiver = poll.getReceiver();
                Intent intent = poll.getIntent();
                int intExtra = intent.getIntExtra("message_type", 1);
                if (intExtra == 1) {
                    PushMessageHandler.PushMessageInterface processIntent = PushMessageProcessor.getInstance(context).processIntent(intent);
                    int intExtra2 = intent.getIntExtra("eventMessageType", -1);
                    if (processIntent == null) {
                        return;
                    }
                    if (processIntent instanceof MiPushMessage) {
                        MiPushMessage miPushMessage = (MiPushMessage) processIntent;
                        if (!miPushMessage.isArrivedMessage()) {
                            receiver.onReceiveMessage(context, miPushMessage);
                        }
                        if (miPushMessage.getPassThrough() == 1) {
                            PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent(context.getPackageName(), intent, 2004, "call passThrough callBack");
                            receiver.onReceivePassThroughMessage(context, miPushMessage);
                        } else if (miPushMessage.isNotified()) {
                            if (intExtra2 == 1000) {
                                PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent(context.getPackageName(), intent, 1007, "call notification callBack");
                            } else {
                                PushClientReportManager.getInstance(context.getApplicationContext()).reportEvent(context.getPackageName(), intent, 3007, "call business callBack");
                            }
                            MyLog.w("begin execute onNotificationMessageClicked from　" + miPushMessage.getMessageId());
                            receiver.onNotificationMessageClicked(context, miPushMessage);
                        } else {
                            receiver.onNotificationMessageArrived(context, miPushMessage);
                        }
                    } else if (processIntent instanceof MiPushCommandMessage) {
                        MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) processIntent;
                        receiver.onCommandResult(context, miPushCommandMessage);
                        if (TextUtils.equals(miPushCommandMessage.getCommand(), Command.COMMAND_REGISTER.value)) {
                            receiver.onReceiveRegisterResult(context, miPushCommandMessage);
                            if (miPushCommandMessage.getResultCode() == 0) {
                                AssemblePushHelper.registerAssemblePush(context);
                            }
                        }
                    }
                } else if (intExtra == 3) {
                    MiPushCommandMessage miPushCommandMessage2 = (MiPushCommandMessage) intent.getSerializableExtra("key_command");
                    receiver.onCommandResult(context, miPushCommandMessage2);
                    if (TextUtils.equals(miPushCommandMessage2.getCommand(), Command.COMMAND_REGISTER.value)) {
                        receiver.onReceiveRegisterResult(context, miPushCommandMessage2);
                        if (miPushCommandMessage2.getResultCode() == 0) {
                            AssemblePushHelper.registerAssemblePush(context);
                        }
                    }
                } else if (intExtra == 4) {
                } else {
                    if (intExtra == 5) {
                        if ("error_lack_of_permission".equals(intent.getStringExtra("error_type")) && (stringArrayExtra = intent.getStringArrayExtra("error_message")) != null) {
                            receiver.onRequirePermissions(context, stringArrayExtra);
                        }
                    }
                }
            }
        } catch (RuntimeException e) {
            MyLog.e((Throwable) e);
        }
    }

    private static void scheduleJob(final Context context) {
        if (!sPool.isShutdown()) {
            sPool.execute(new Runnable() {
                public void run() {
                    MessageHandleService.processJob(context);
                }
            });
        }
    }

    public static void startService(final Context context) {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, MessageHandleService.class));
        ScheduledJobManager.getInstance(context).addOneShootJob(new Runnable() {
            public void run() {
                try {
                    context.startService(intent);
                } catch (Exception e) {
                    MyLog.w(e.getMessage());
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean hasJob() {
        ConcurrentLinkedQueue<MessageHandleJob> concurrentLinkedQueue = jobQueue;
        return concurrentLinkedQueue != null && concurrentLinkedQueue.size() > 0;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }
}
