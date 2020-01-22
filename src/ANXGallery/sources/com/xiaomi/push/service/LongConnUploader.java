package com.xiaomi.push.service;

import android.text.TextUtils;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.tinyData.TinyDataUploader;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.ClientUploadDataItem;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LongConnUploader implements TinyDataUploader {
    /* access modifiers changed from: private */
    public final XMPushService mPushService;

    public LongConnUploader(XMPushService xMPushService) {
        this.mPushService = xMPushService;
    }

    /* access modifiers changed from: private */
    public String getAppId(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.mPushService.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, (String) null);
    }

    public void upload(List<ClientUploadDataItem> list, String str, String str2) {
        MyLog.w("TinyData LongConnUploader.upload items size:" + list.size() + "  ts:" + System.currentTimeMillis());
        final String str3 = str;
        final List<ClientUploadDataItem> list2 = list;
        final String str4 = str2;
        this.mPushService.executeJob(new XMPushService.Job(4) {
            public String getDesc() {
                return "Send tiny data.";
            }

            public void process() {
                String access$000 = LongConnUploader.this.getAppId(str3);
                ArrayList<XmPushActionNotification> pack = TinyDataHelper.pack(list2, str3, access$000, 32768);
                MyLog.w("TinyData LongConnUploader.upload pack notifications " + pack.toString() + "  ts:" + System.currentTimeMillis());
                if (pack != null) {
                    Iterator<XmPushActionNotification> it = pack.iterator();
                    while (it.hasNext()) {
                        XmPushActionNotification next = it.next();
                        next.putToExtra("uploadWay", "longXMPushService");
                        XmPushActionContainer generateRequestContainer = MIPushHelper.generateRequestContainer(str3, access$000, next, ActionType.Notification);
                        if (!TextUtils.isEmpty(str4) && !TextUtils.equals(str3, str4)) {
                            if (generateRequestContainer.getMetaInfo() == null) {
                                PushMetaInfo pushMetaInfo = new PushMetaInfo();
                                pushMetaInfo.setId(MovieStatUtils.DOWNLOAD_FAILED);
                                generateRequestContainer.setMetaInfo(pushMetaInfo);
                            }
                            generateRequestContainer.getMetaInfo().putToInternal("ext_traffic_source_pkg", str4);
                        }
                        LongConnUploader.this.mPushService.sendMessage(str3, XmPushThriftSerializeUtils.convertThriftObjectToBytes(generateRequestContainer), true);
                    }
                    for (ClientUploadDataItem id : list2) {
                        MyLog.w("TinyData LongConnUploader.upload uploaded by com.xiaomi.push.service.TinyDataUploader.  item" + id.getId() + "  ts:" + System.currentTimeMillis());
                    }
                    return;
                }
                MyLog.e("TinyData LongConnUploader.upload Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
            }
        });
    }
}
