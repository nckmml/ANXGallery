package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.clientreport.data.BaseClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.util.ClientReportUtil;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class DefaultPerfProcessor implements IPerfProcessor {
    protected Context mContext;
    private HashMap<String, HashMap<String, BaseClientReport>> mPerfMap;

    public DefaultPerfProcessor(Context context) {
        this.mContext = context;
    }

    public static String getFirstPerfFileName(BaseClientReport baseClientReport) {
        return String.valueOf(baseClientReport.production) + "#" + baseClientReport.clientInterfaceId;
    }

    private String getOriginalFilePath(BaseClientReport baseClientReport) {
        String str;
        int i = baseClientReport.production;
        String str2 = baseClientReport.clientInterfaceId;
        if (i <= 0 || TextUtils.isEmpty(str2)) {
            str = "";
        } else {
            str = String.valueOf(i) + "#" + str2;
        }
        File externalFilesDir = this.mContext.getExternalFilesDir("perf");
        if (externalFilesDir == null) {
            MyLog.e("cannot get folder when to write perf");
            return null;
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        return new File(externalFilesDir, str).getAbsolutePath();
    }

    private String getWriteFileName(BaseClientReport baseClientReport) {
        String originalFilePath = getOriginalFilePath(baseClientReport);
        if (TextUtils.isEmpty(originalFilePath)) {
            return null;
        }
        for (int i = 0; i < 20; i++) {
            String str = originalFilePath + i;
            if (ClientReportUtil.isFileCanBeUse(this.mContext, str)) {
                return str;
            }
        }
        return null;
    }

    public void preProcess(BaseClientReport baseClientReport) {
        if ((baseClientReport instanceof PerfClientReport) && this.mPerfMap != null) {
            PerfClientReport perfClientReport = (PerfClientReport) baseClientReport;
            String firstPerfFileName = getFirstPerfFileName(perfClientReport);
            String generateKey = PerfKVFileHelper.generateKey(perfClientReport);
            HashMap hashMap = this.mPerfMap.get(firstPerfFileName);
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            PerfClientReport perfClientReport2 = (PerfClientReport) hashMap.get(generateKey);
            if (perfClientReport2 != null) {
                perfClientReport.perfCounts += perfClientReport2.perfCounts;
                perfClientReport.perfLatencies += perfClientReport2.perfLatencies;
            }
            hashMap.put(generateKey, perfClientReport);
            this.mPerfMap.put(firstPerfFileName, hashMap);
        }
    }

    public void process() {
        HashMap<String, HashMap<String, BaseClientReport>> hashMap = this.mPerfMap;
        if (hashMap != null) {
            if (hashMap.size() > 0) {
                for (String str : this.mPerfMap.keySet()) {
                    HashMap hashMap2 = this.mPerfMap.get(str);
                    if (hashMap2 != null && hashMap2.size() > 0) {
                        BaseClientReport[] baseClientReportArr = new BaseClientReport[hashMap2.size()];
                        hashMap2.values().toArray(baseClientReportArr);
                        write(baseClientReportArr);
                    }
                }
            }
            this.mPerfMap.clear();
        }
    }

    public void readAndSend() {
        ClientReportUtil.moveFiles(this.mContext, "perf", "perfUploading");
        File[] readFileName = ClientReportUtil.getReadFileName(this.mContext, "perfUploading");
        if (readFileName != null && readFileName.length > 0) {
            for (File file : readFileName) {
                if (file != null) {
                    List<String> extractToDatas = PerfKVFileHelper.extractToDatas(this.mContext, file.getAbsolutePath());
                    file.delete();
                    send(extractToDatas);
                }
            }
        }
    }

    public void send(List<String> list) {
        ClientReportUtil.sendFile(this.mContext, list);
    }

    public void setPerfMap(HashMap<String, HashMap<String, BaseClientReport>> hashMap) {
        this.mPerfMap = hashMap;
    }

    public void write(BaseClientReport[] baseClientReportArr) {
        String writeFileName = getWriteFileName(baseClientReportArr[0]);
        if (!TextUtils.isEmpty(writeFileName)) {
            PerfKVFileHelper.put(writeFileName, baseClientReportArr);
        }
    }
}
