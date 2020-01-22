package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.SystemUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.service.clientReport.PushClientReportManager;
import com.xiaomi.push.service.xmpush.Command;
import com.xiaomi.xmpush.thrift.ClientUploadDataItem;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PushMessageHandler extends BaseService {
    private static List<MiPushClient.MiPushClientCallback> sCallbacks = new ArrayList();
    private static ThreadPoolExecutor sPool = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    interface PushMessageInterface extends Serializable {
    }

    public static void addJob(Context context, Intent intent) {
        MyLog.v("addjob PushMessageHandler " + intent);
        if (intent != null) {
            scheduleJob(context, intent);
            startService(context);
        }
    }

    protected static void addPushCallbackClass(MiPushClient.MiPushClientCallback miPushClientCallback) {
        synchronized (sCallbacks) {
            if (!sCallbacks.contains(miPushClientCallback)) {
                sCallbacks.add(miPushClientCallback);
            }
        }
    }

    private static void handleNewMessage(Context context, Intent intent, ResolveInfo resolveInfo) {
        try {
            MessageHandleService.addJob(context.getApplicationContext(), new MessageHandleService.MessageHandleJob(intent, (PushMessageReceiver) SystemUtils.loadClass(context, resolveInfo.activityInfo.name).newInstance()));
            MessageHandleService.onHandleIntent(context, new Intent(context.getApplicationContext(), MessageHandleService.class));
        } catch (Throwable th) {
            MyLog.e(th);
        }
    }

    public static boolean isCallbackEmpty() {
        return sCallbacks.isEmpty();
    }

    protected static boolean isCategoryMatch(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    protected static void onCommandResult(Context context, String str, String str2, long j, String str3, List<String> list) {
        synchronized (sCallbacks) {
            for (MiPushClient.MiPushClientCallback next : sCallbacks) {
                if (isCategoryMatch(str, next.getCategory())) {
                    next.onCommandResult(str2, j, str3, list);
                }
            }
        }
    }

    protected static void onHandleIntent(Context context, Intent intent) {
        try {
            ResolveInfo resolveInfo = null;
            if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                AwakeHelper.doAWork(context, intent, (Uri) null);
            } else if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                ClientUploadDataItem clientUploadDataItem = new ClientUploadDataItem();
                XmPushThriftSerializeUtils.convertByteArrayToThriftObject(clientUploadDataItem, intent.getByteArrayExtra("mipush_payload"));
                MyLog.v("PushMessageHandler.onHandleIntent " + clientUploadDataItem.getId());
                MiTinyDataClient.upload(context, clientUploadDataItem);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                if (isCallbackEmpty()) {
                    MyLog.e("receive a message before application calling initialize");
                    return;
                }
                PushMessageInterface processIntent = PushMessageProcessor.getInstance(context).processIntent(intent);
                if (processIntent != null) {
                    processMessageForCallback(context, processIntent);
                }
            } else if ("com.xiaomi.mipush.sdk.SYNC_LOG".equals(intent.getAction())) {
                Logger.uploadLogFile(context, false);
            } else {
                Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent2.setPackage(context.getPackageName());
                intent2.putExtras(intent);
                List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 32);
                if (queryBroadcastReceivers != null) {
                    Iterator<ResolveInfo> it = queryBroadcastReceivers.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ResolveInfo next = it.next();
                        if (next.activityInfo != null && next.activityInfo.packageName.equals(context.getPackageName()) && PushMessageReceiver.class.isAssignableFrom(SystemUtils.loadClass(context, next.activityInfo.name))) {
                            resolveInfo = next;
                            break;
                        }
                    }
                }
                if (resolveInfo != null) {
                    handleNewMessage(context, intent2, resolveInfo);
                    return;
                }
                MyLog.e("cannot find the receiver to handler this message, check your manifest");
                PushClientReportManager.getInstance(context).reportEvent4ERROR(context.getPackageName(), intent, "cannot find the receiver to handler this message, check your manifest");
            }
        } catch (Exception e) {
            MyLog.e((Throwable) e);
            PushClientReportManager.getInstance(context).reportEvent4ERROR(context.getPackageName(), intent, (Throwable) e);
        } catch (Throwable th) {
            MyLog.e(th);
            PushClientReportManager.getInstance(context).reportEvent4ERROR(context.getPackageName(), intent, th);
        }
    }

    public static void onInitializeResult(long j, String str, String str2) {
        synchronized (sCallbacks) {
            for (MiPushClient.MiPushClientCallback onInitializeResult : sCallbacks) {
                onInitializeResult.onInitializeResult(j, str, str2);
            }
        }
    }

    public static void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        synchronized (sCallbacks) {
            for (MiPushClient.MiPushClientCallback next : sCallbacks) {
                if (isCategoryMatch(miPushMessage.getCategory(), next.getCategory())) {
                    next.onReceiveMessage(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    next.onReceiveMessage(miPushMessage);
                }
            }
        }
    }

    protected static void onSubscribeResult(Context context, String str, long j, String str2, String str3) {
        synchronized (sCallbacks) {
            for (MiPushClient.MiPushClientCallback next : sCallbacks) {
                if (isCategoryMatch(str, next.getCategory())) {
                    next.onSubscribeResult(j, str2, str3);
                }
            }
        }
    }

    protected static void onUnsubscribeResult(Context context, String str, long j, String str2, String str3) {
        synchronized (sCallbacks) {
            for (MiPushClient.MiPushClientCallback next : sCallbacks) {
                if (isCategoryMatch(str, next.getCategory())) {
                    next.onUnsubscribeResult(j, str2, str3);
                }
            }
        }
    }

    public static void processMessageForCallback(Context context, PushMessageInterface pushMessageInterface) {
        if (pushMessageInterface instanceof MiPushMessage) {
            onReceiveMessage(context, (MiPushMessage) pushMessageInterface);
        } else if (pushMessageInterface instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) pushMessageInterface;
            String command = miPushCommandMessage.getCommand();
            String str = null;
            if (Command.COMMAND_REGISTER.value.equals(command)) {
                List<String> commandArguments = miPushCommandMessage.getCommandArguments();
                if (commandArguments != null && !commandArguments.isEmpty()) {
                    str = commandArguments.get(0);
                }
                onInitializeResult(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (Command.COMMAND_SET_ALIAS.value.equals(command) || Command.COMMAND_UNSET_ALIAS.value.equals(command) || Command.COMMAND_SET_ACCEPT_TIME.value.equals(command)) {
                onCommandResult(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
            } else if (Command.COMMAND_SUBSCRIBE_TOPIC.value.equals(command)) {
                List<String> commandArguments2 = miPushCommandMessage.getCommandArguments();
                if (commandArguments2 != null && !commandArguments2.isEmpty()) {
                    str = commandArguments2.get(0);
                }
                Context context2 = context;
                onSubscribeResult(context2, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (Command.COMMAND_UNSUBSCRIBE_TOPIC.value.equals(command)) {
                List<String> commandArguments3 = miPushCommandMessage.getCommandArguments();
                if (commandArguments3 != null && !commandArguments3.isEmpty()) {
                    str = commandArguments3.get(0);
                }
                Context context3 = context;
                onUnsubscribeResult(context3, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            }
        }
    }

    protected static void removeAllPushCallbackClass() {
        synchronized (sCallbacks) {
            sCallbacks.clear();
        }
    }

    private static void scheduleJob(final Context context, final Intent intent) {
        if (intent != null && !sPool.isShutdown()) {
            sPool.execute(new Runnable() {
                public void run() {
                    PushMessageHandler.onHandleIntent(context, intent);
                }
            });
        }
    }

    public static void startService(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, PushMessageHandler.class));
        try {
            context.startService(intent);
        } catch (Exception e) {
            MyLog.w(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasJob() {
        ThreadPoolExecutor threadPoolExecutor = sPool;
        return (threadPoolExecutor == null || threadPoolExecutor.getQueue() == null || sPool.getQueue().size() <= 0) ? false : true;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        scheduleJob(getApplicationContext(), intent);
    }
}
