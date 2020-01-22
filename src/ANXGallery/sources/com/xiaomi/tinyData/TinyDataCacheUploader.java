package com.xiaomi.tinyData;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.TinyDataHelper;
import com.xiaomi.xmpush.thrift.ClientUploadDataItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TinyDataCacheUploader {
    private static HashMap<String, ArrayList<ClientUploadDataItem>> prepareTinyDataItems(Context context, List<ClientUploadDataItem> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap<String, ArrayList<ClientUploadDataItem>> hashMap = new HashMap<>();
        for (ClientUploadDataItem next : list) {
            verifyTinyDataUploadItemValue(context, next);
            ArrayList arrayList = hashMap.get(next.getSourcePackage());
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(next.getSourcePackage(), arrayList);
            }
            arrayList.add(next);
        }
        return hashMap;
    }

    private static void upload(Context context, TinyDataUploader tinyDataUploader, HashMap<String, ArrayList<ClientUploadDataItem>> hashMap) {
        for (Map.Entry next : hashMap.entrySet()) {
            try {
                ArrayList arrayList = (ArrayList) next.getValue();
                if (arrayList != null) {
                    if (arrayList.size() != 0) {
                        MyLog.w("TinyData is uploaded immediately item size:" + arrayList.size());
                        tinyDataUploader.upload(arrayList, ((ClientUploadDataItem) arrayList.get(0)).getPkgName(), (String) next.getKey());
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void uploadTinyData(Context context, TinyDataUploader tinyDataUploader, List<ClientUploadDataItem> list) {
        HashMap<String, ArrayList<ClientUploadDataItem>> prepareTinyDataItems = prepareTinyDataItems(context, list);
        if (prepareTinyDataItems == null || prepareTinyDataItems.size() == 0) {
            MyLog.w("TinyData TinyDataCacheUploader.uploadTinyData itemsUploading == null || itemsUploading.size() == 0  ts:" + System.currentTimeMillis());
            return;
        }
        upload(context, tinyDataUploader, prepareTinyDataItems);
    }

    private static void verifyTinyDataUploadItemValue(Context context, ClientUploadDataItem clientUploadDataItem) {
        if (clientUploadDataItem.fromSdk) {
            clientUploadDataItem.setChannel("push_sdk_channel");
        }
        if (TextUtils.isEmpty(clientUploadDataItem.getId())) {
            clientUploadDataItem.setId(TinyDataHelper.nextTinyDataItemId());
        }
        clientUploadDataItem.setTimestamp(System.currentTimeMillis());
        if (TextUtils.isEmpty(clientUploadDataItem.getPkgName())) {
            clientUploadDataItem.setSourcePackage(context.getPackageName());
        }
        if (TextUtils.isEmpty(clientUploadDataItem.getSourcePackage())) {
            clientUploadDataItem.setSourcePackage(clientUploadDataItem.getPkgName());
        }
    }
}
