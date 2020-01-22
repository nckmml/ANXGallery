package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.Context;
import com.miui.gallery.cloud.RequestOperationBase;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.util.UbiFocusUtils;
import java.util.List;
import org.json.JSONObject;

public class EditCloudItem extends EditCloudBase {
    public EditCloudItem(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public RequestOperationBase.Request buildRequest(Account account, RequestCloudItem requestCloudItem) throws Exception {
        List<DBImage> subUbiImages;
        DBImage dBImage = requestCloudItem.dbCloud;
        String editUrl = CloudUrlProvider.getUrlProvider(dBImage.isShareItem(), dBImage.isVideoType()).getEditUrl(account.name, dBImage.getRequestId());
        new JSONObject();
        JSONObject jSONObject = dBImage.toJSONObject();
        if (dBImage.isUbiFocus() && (subUbiImages = UbiFocusUtils.getSubUbiImages(dBImage.isShareItem(), dBImage.getRequestId(), dBImage.getId())) != null) {
            JSONObject jSONObject2 = new JSONObject();
            for (DBImage next : subUbiImages) {
                jSONObject2.putOpt(String.valueOf(next.getSubUbiIndex()), next.toJsonObjectForSubUbi());
            }
            jSONObject2.putOpt(String.valueOf(dBImage.getSubUbiIndex()), dBImage.toJsonObjectForSubUbi());
            jSONObject.putOpt("ubiSubImageContentMap", jSONObject2);
        }
        return new RequestOperationBase.Request.Builder().setUrl(editUrl).setMethod(2).setPostData(new JSONObject().put("content", jSONObject)).setRetryTimes(requestCloudItem.otherRetryTimes).setNeedReRequest(false).build();
    }

    /* access modifiers changed from: protected */
    public int getColumnIndex() {
        return 57;
    }
}
