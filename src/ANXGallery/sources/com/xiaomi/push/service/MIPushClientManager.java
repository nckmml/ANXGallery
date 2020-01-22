package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.XMPPException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MIPushClientManager {
    private static ArrayList<Pair<String, byte[]>> pendingMessages = new ArrayList<>();
    private static final Map<String, byte[]> pendingRegisterationRequests = new HashMap();

    public static void addPendingMessages(String str, byte[] bArr) {
        synchronized (pendingMessages) {
            pendingMessages.add(new Pair(str, bArr));
            if (pendingMessages.size() > 50) {
                pendingMessages.remove(0);
            }
        }
    }

    public static void notifyError(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, MIPushHelper.getReceiverPermission(str));
    }

    public static void notifyRegisterError(Context context, int i, String str) {
        synchronized (pendingRegisterationRequests) {
            for (String next : pendingRegisterationRequests.keySet()) {
                notifyError(context, next, pendingRegisterationRequests.get(next), i, str);
            }
            pendingRegisterationRequests.clear();
        }
    }

    public static void processPendingMessages(XMPushService xMPushService) {
        ArrayList<Pair<String, byte[]>> arrayList;
        try {
            synchronized (pendingMessages) {
                arrayList = pendingMessages;
                pendingMessages = new ArrayList<>();
            }
            Iterator<Pair<String, byte[]>> it = arrayList.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                MIPushHelper.sendPacket(xMPushService, (String) next.first, (byte[]) next.second);
            }
        } catch (XMPPException e) {
            MyLog.e((Throwable) e);
            xMPushService.disconnect(10, e);
        }
    }

    public static void processPendingRegistrationRequest(XMPushService xMPushService) {
        try {
            synchronized (pendingRegisterationRequests) {
                for (String next : pendingRegisterationRequests.keySet()) {
                    MIPushHelper.sendPacket(xMPushService, next, pendingRegisterationRequests.get(next));
                }
                pendingRegisterationRequests.clear();
            }
        } catch (XMPPException e) {
            MyLog.e((Throwable) e);
            xMPushService.disconnect(10, e);
        }
    }

    public static void registerApp(String str, byte[] bArr) {
        synchronized (pendingRegisterationRequests) {
            pendingRegisterationRequests.put(str, bArr);
        }
    }
}
