package com.xiaomi.opensdk.file.sdk;

import cn.kuaipan.android.exception.KscException;
import cn.kuaipan.android.kss.UploadRequestResult;
import cn.kuaipan.android.utils.ApiDataHelper;
import cn.kuaipan.android.utils.IObtainable;
import cn.kuaipan.android.utils.JsonUtils;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class FileUploadRequestResult extends UploadRequestResult implements ApiDataHelper.IKscData {
    public static final ApiDataHelper.IKscData.Parser<FileUploadRequestResult> PARSER = new ApiDataHelper.IKscData.Parser<FileUploadRequestResult>() {
    };
    public final String requestId;

    public FileUploadRequestResult(Map<String, Object> map) throws KscException {
        super(map);
        this.requestId = ApiDataHelper.asString(map, "requestId");
    }

    public static FileUploadRequestResult create(String str) throws KscException {
        Map map = null;
        try {
            Map map2 = (Map) JsonUtils.parser((Reader) new StringReader(str));
            try {
                FileUploadRequestResult fileUploadRequestResult = new FileUploadRequestResult(map2);
                if (map2 != null && (map2 instanceof IObtainable)) {
                    ((IObtainable) map2).recycle();
                }
                return fileUploadRequestResult;
            } catch (IOException e) {
                IOException iOException = e;
                Map map3 = map2;
                e = iOException;
                throw new KscException(501004, "kss is null", e);
            } catch (JSONException e2) {
                JSONException jSONException = e2;
                map = map2;
                e = jSONException;
                throw new KscException(501001, "kss is not json", e);
            } catch (Throwable th) {
                Throwable th2 = th;
                map = map2;
                th = th2;
                ((IObtainable) map).recycle();
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            throw new KscException(501004, "kss is null", e);
        } catch (JSONException e4) {
            e = e4;
            throw new KscException(501001, "kss is not json", e);
        } catch (Throwable th3) {
            th = th3;
            if (map != null && (map instanceof IObtainable)) {
                ((IObtainable) map).recycle();
            }
            throw th;
        }
    }

    public String toString() {
        String uploadRequestResult = super.toString();
        try {
            return new JSONObject(uploadRequestResult).put("requestId", this.requestId).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return uploadRequestResult;
        }
    }
}
