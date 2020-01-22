package com.miui.gallery.cloud.peopleface.syncoperation;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.RequestItemBase;
import com.miui.gallery.cloud.RequestOperationBase;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.peopleface.FaceAlbumUtil;
import com.miui.gallery.cloud.peopleface.FaceDataManager;
import com.miui.gallery.cloud.peopleface.RequestFaceItem;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.util.SyncLog;
import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class RecoveryPeopleOperation extends FaceRequestOperationBase {
    public RecoveryPeopleOperation(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public RequestOperationBase.Request buildRequest(Account account, RequestItemBase requestItemBase) throws Exception {
        RequestFaceItem requestFaceItem = (RequestFaceItem) requestItemBase;
        String peopleRecoveryUrl = HostManager.PeopleFace.getPeopleRecoveryUrl(requestFaceItem.face.serverId);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(requestFaceItem.face.peopleName)) {
            arrayList.add(new BasicNameValuePair("peopleName", requestFaceItem.face.peopleName));
        }
        return new RequestOperationBase.Request.Builder().setMethod(2).setUrl(peopleRecoveryUrl).setParams(arrayList).setRetryTimes(requestFaceItem.createRetryTimes).setNeedReRequest(requestFaceItem.needReRequest).build();
    }

    /* access modifiers changed from: protected */
    public GallerySyncCode onPreRequest(RequestItemBase requestItemBase) {
        return !(requestItemBase instanceof RequestFaceItem) ? GallerySyncCode.NOT_RETRY_ERROR : super.onPreRequest(requestItemBase);
    }

    /* access modifiers changed from: protected */
    public void onRequestError(GallerySyncCode gallerySyncCode, RequestItemBase requestItemBase, JSONObject jSONObject) throws Exception {
        if (gallerySyncCode != GallerySyncCode.OK) {
            requestItemBase.createRetryTimes++;
        }
    }

    /* access modifiers changed from: protected */
    public void onRequestSuccess(RequestItemBase requestItemBase, JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("record");
        if (optJSONObject == null) {
            SyncLog.e(getTag(), "response record null");
            return;
        }
        RequestFaceItem requestFaceItem = (RequestFaceItem) requestItemBase;
        ContentValues contentValuesForPeopleFace = FaceAlbumUtil.getContentValuesForPeopleFace(optJSONObject);
        if (contentValuesForPeopleFace == null) {
            contentValuesForPeopleFace = new ContentValues();
        }
        boolean z = true;
        contentValuesForPeopleFace.put("visibilityType", 1);
        if (FaceManager.getPeopleLocalFlagByLocalID(requestFaceItem.face._id) == 14) {
            z = false;
        }
        CloudUtils.updateToLocalDBForSync(FaceDataManager.PEOPLE_FACE_URI, contentValuesForPeopleFace, requestFaceItem.face._id, z);
    }
}
