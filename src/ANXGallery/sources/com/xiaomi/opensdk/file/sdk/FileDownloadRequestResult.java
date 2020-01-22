package com.xiaomi.opensdk.file.sdk;

import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.kss.DownloadRequestResult;
import cn.kuaipan.android.utils.ApiDataHelper;
import java.util.Map;

public class FileDownloadRequestResult extends DownloadRequestResult implements ApiDataHelper.IKscData {
    public static final ApiDataHelper.IKscData.Parser<FileDownloadRequestResult> PARSER = new ApiDataHelper.IKscData.Parser<FileDownloadRequestResult>() {
    };

    public FileDownloadRequestResult(Map<String, Object> map) throws KscException {
        super(map);
    }
}
