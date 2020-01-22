package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.awake.AwakeDataHelper;
import com.xiaomi.push.service.awake.module.AwakeManager;
import com.xiaomi.push.service.awake.module.IProcessData;
import com.xiaomi.tinyData.TinyDataManager;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.NotificationType;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.util.HashMap;

public class PushLayerProcessIml implements IProcessData {
    public void sendByTinyData(Context context, HashMap<String, String> hashMap) {
        TinyDataManager instance = TinyDataManager.getInstance(context);
        if (instance != null) {
            instance.upload("category_awake_app", "wake_up_app", 1, AwakeDataHelper.getString(hashMap));
        }
    }

    public void sendDirectly(Context context, HashMap<String, String> hashMap) {
        XmPushActionNotification xmPushActionNotification = new XmPushActionNotification();
        xmPushActionNotification.setAppId(AwakeManager.getInstance(context).getAppId());
        xmPushActionNotification.setPackageName(AwakeManager.getInstance(context).getPackageName());
        xmPushActionNotification.setType(NotificationType.AwakeAppResponse.value);
        xmPushActionNotification.setId(PacketHelper.generatePacketID());
        xmPushActionNotification.extra = hashMap;
        byte[] convertThriftObjectToBytes = XmPushThriftSerializeUtils.convertThriftObjectToBytes(MIPushHelper.generateRequestContainer(xmPushActionNotification.getPackageName(), xmPushActionNotification.getAppId(), xmPushActionNotification, ActionType.Notification));
        if (context instanceof XMPushService) {
            MyLog.w("MoleInfo : send data directly in pushLayer " + xmPushActionNotification.getId());
            ((XMPushService) context).sendMessage(context.getPackageName(), convertThriftObjectToBytes, true);
            return;
        }
        MyLog.w("MoleInfo : context is not correct in pushLayer " + xmPushActionNotification.getId());
    }

    public void shouldDoLast(Context context, HashMap<String, String> hashMap) {
        MyLog.w("MoleInfo：　" + AwakeDataHelper.obfuscateLogContent(hashMap));
    }
}
