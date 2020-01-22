package com.miui.gallery.cloud.peopleface;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.miui.gallery.cloud.CheckResult;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudSyncTagUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.cloud.GalleryMiCloudServerException;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.cloud.RequestItemBase;
import com.miui.gallery.cloud.SyncConditionManager;
import com.miui.gallery.cloud.base.GalleryExtendedAuthToken;
import com.miui.gallery.cloud.base.GallerySyncCode;
import com.miui.gallery.cloud.base.GallerySyncResult;
import com.miui.gallery.cloud.base.RetryRequestHelper;
import com.miui.gallery.cloud.base.SyncTask;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.util.Encode;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Log2File;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.SyncLog;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SyncPeopleFaceFromServer {
    protected Account mAccount;
    protected Context mContext;
    protected GalleryExtendedAuthToken mExtendedAuthToken;
    private Runnable mRunnable = new Runnable() {
        public void run() {
        }
    };
    private boolean mShouldFlatPeopleRelationshipInDB = false;

    public static class ErrorHandler {
        private static void cleanPeopleFaceTables() {
            for (String appendPath : GalleryDBHelper.getPeopleFaceTables()) {
                Uri build = GalleryCloudUtils.BASE_URI.buildUpon().appendPath(appendPath).build();
                Log2File.getInstance().flushLog("syncface", String.format("clean %s finished, deleted rows=%d", new Object[]{build, Integer.valueOf(GalleryUtils.safeDelete(build, (String) null, (String[]) null))}), (Throwable) null);
            }
        }

        public static boolean simpleCheckWhetherNeedCleanTables(JSONObject jSONObject, Account account) throws JSONException {
            if (jSONObject == null) {
                return false;
            }
            if (!jSONObject.has("code")) {
                Log2File.getInstance().flushLog("syncface", "don't have json_tag_code", (Throwable) null);
                return false;
            }
            int i = jSONObject.getInt("code");
            Log2File instance = Log2File.getInstance();
            instance.flushLog("syncface", "JSON_TAG_CODE is:" + i, (Throwable) null);
            if (i != 52000) {
                return false;
            }
            cleanPeopleFaceTables();
            GalleryCloudSyncTagUtils.setFaceSyncWatermark(account, 0);
            GalleryCloudSyncTagUtils.setFaceSyncToken(account, "");
            return true;
        }
    }

    public SyncPeopleFaceFromServer(Context context, Account account, GalleryExtendedAuthToken galleryExtendedAuthToken) {
        this.mContext = context;
        this.mAccount = account;
        this.mExtendedAuthToken = galleryExtendedAuthToken;
    }

    private void flatDBAndCheckBabyAlbumAfterSync() {
        if (this.mShouldFlatPeopleRelationshipInDB) {
            GalleryUtils.runOnMainThreadPostDelay(this.mRunnable, 4000);
            this.mShouldFlatPeopleRelationshipInDB = false;
        }
    }

    private final GallerySyncResult<JSONObject> getFaceConcreteInfoList(ArrayList<String> arrayList) throws ClientProtocolException, IOException, JSONException, URISyntaxException, IllegalBlockSizeException, BadPaddingException, GalleryMiCloudServerException {
        SyncLog.d("syncface", "getFaceConcreteInfoList begin");
        int size = arrayList.size();
        int i = 0;
        GallerySyncResult<JSONObject> gallerySyncResult = null;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(arrayList.get(i2));
            i++;
            if (i < 40 && i2 < size - 1) {
                sb.append(",");
            }
            if (i == 40 || i2 == size - 1) {
                final String sb2 = sb.toString();
                gallerySyncResult = RetryRequestHelper.retryTask(new SyncTask<JSONObject>() {
                    public String getIdentifier() {
                        return SyncPeopleFaceFromServer.this.getSyncFaceConcreteInfoUrl();
                    }

                    public GallerySyncResult<JSONObject> run() throws Exception {
                        return CheckResult.checkXMResultCodeForFaceRequest(SyncPeopleFaceFromServer.this.getFaceConcreteInfoListOneBatch(sb2), (RequestItemBase) null);
                    }
                });
                if (gallerySyncResult.data == null) {
                    break;
                }
                sb = new StringBuilder();
                i = 0;
            }
        }
        SyncLog.d("syncface", "getFaceConcreteInfoList end");
        return gallerySyncResult;
    }

    /* access modifiers changed from: private */
    public final JSONObject getFaceConcreteInfoListOneBatch(String str) throws ClientProtocolException, IOException, JSONException, URISyntaxException, IllegalBlockSizeException, BadPaddingException, GalleryMiCloudServerException {
        if (Log2File.getInstance().canLog()) {
            Log2File instance = Log2File.getInstance();
            instance.flushLog("syncface", "getPeopleFaceInfo  " + str, (Throwable) null);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("newFaceInfos", str));
        JSONObject postToXiaomi = CloudUtils.postToXiaomi(getSyncFaceConcreteInfoUrl(), arrayList, (JSONObject) null, this.mAccount, this.mExtendedAuthToken, 0, false);
        try {
            handleFaceInfosDataJson(postToXiaomi.optJSONObject("data"));
        } catch (Exception unused) {
        }
        if (Log2File.getInstance().canLog()) {
            Log2File instance2 = Log2File.getInstance();
            instance2.flushLog("syncface", "getFaceConcreteInfoListOneBatch end,allJson=" + postToXiaomi, (Throwable) null);
        }
        return postToXiaomi;
    }

    /* access modifiers changed from: private */
    public final JSONObject getPeopleFaceList(int i) throws ClientProtocolException, IOException, JSONException, URISyntaxException, IllegalBlockSizeException, BadPaddingException, GalleryMiCloudServerException {
        if (Log2File.getInstance().canLog()) {
            Log2File.getInstance().flushLog("syncface", "getPeopleFaceList :", (Throwable) null);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("syncToken", GalleryCloudSyncTagUtils.getFaceSyncToken(this.mAccount)));
        if (i > 0) {
            arrayList.add(new BasicNameValuePair("limit", Long.toString((long) i)));
        }
        JSONObject fromXiaomi = CloudUtils.getFromXiaomi(getPeopleFaceSyncUrl(), arrayList, this.mAccount, this.mExtendedAuthToken, 0, false);
        if (Log2File.getInstance().canLog()) {
            Log2File instance = Log2File.getInstance();
            instance.flushLog("syncface", "getPeopleFaceList allJson=" + fromXiaomi, (Throwable) null);
        }
        SyncLog.d("syncface", "getPeopleFaceList success\n");
        return fromXiaomi;
    }

    private final void handleCustom(ContentValues contentValues) throws JSONException {
        CloudUtils.updateToPeopleFaceDBForSync(FaceDataManager.PEOPLE_FACE_URI, contentValues, contentValues.getAsString("serverId"));
    }

    private final void handleDelete(ContentValues contentValues) throws JSONException {
        CloudUtils.updateToPeopleFaceDBForDeleteItem(FaceDataManager.PEOPLE_FACE_URI, contentValues, contentValues.getAsString("serverId"));
    }

    private void handleFaceInfosDataJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("faceInfoRecords");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                SyncLog.e("syncface", "response face info is empty");
                return;
            }
            SyncLog.d("syncface", "handleFaceInfosDataJson one batch:");
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String string = jSONObject2.getString("faceId");
                JSONObject jSONObject3 = jSONObject2.getJSONObject("faceInfo");
                JSONArray jSONArray = jSONObject2.getJSONArray("imageIds");
                putFaceInfo2Db(jSONObject3, string, jSONObject2.optDouble("faceCoverScore", -1.0d));
                putFaceImageIdsInfo2Db(jSONArray, string);
            }
        }
    }

    private ArrayList<String> handlePeopleFaceDataJson(JSONObject jSONObject) throws JSONException {
        ArrayList<String> arrayList = null;
        if (jSONObject == null || !jSONObject.has("records")) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("newFaceInfos");
        JSONArray jSONArray = jSONObject.getJSONArray("records");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            handleItem(jSONObject3);
            String string = jSONObject3.getString("id");
            if (jSONObject2.has(string)) {
                JSONObject optJSONObject = jSONObject3.optJSONObject("faceContent");
                if (optJSONObject != null) {
                    SyncLog.d("syncface", "to get FaceInfo from faceContent");
                    String optString = optJSONObject.optString("sha1Base64");
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("baseFaceInfo");
                    if (!TextUtils.isEmpty(optString) && optJSONObject2 != null) {
                        String optString2 = jSONObject3.optString("id");
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("faceInfo");
                        try {
                            List<String> imageServerIdsBySHA1 = CloudUtils.getImageServerIdsBySHA1(Encode.byteArrayToHexString(Base64.decode(optString, 8)));
                            double optDouble = optJSONObject2.optDouble("faceCoverScore", -1.0d);
                            if (optJSONObject3 != null && MiscUtil.isValid(imageServerIdsBySHA1)) {
                                SyncLog.d("syncface", "get FaceInfo from faceContent success");
                                putFaceInfo2Db(optJSONObject3, optString2, optDouble);
                                putFaceImageIdsInfo2Db(imageServerIdsBySHA1, optString2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                SyncLog.d("syncface", "get FaceInfo from faceContent fail");
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(jSONObject2.getString(string));
            }
        }
        return arrayList;
    }

    private void insertFace2Image(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("faceId", str);
        contentValues.put("imageServerId", str2);
        FaceDataManager.safeInsertFace2Image(contentValues);
    }

    private void putFaceImageIdsInfo2Db(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            insertFace2Image(str, list.get(i));
        }
    }

    private void putFaceImageIdsInfo2Db(JSONArray jSONArray, String str) throws JSONException {
        for (int i = 0; i < jSONArray.length(); i++) {
            insertFace2Image(str, jSONArray.get(i).toString());
        }
    }

    private void putFaceInfo2Db(JSONObject jSONObject, String str, double d) throws JSONException {
        JSONObject jSONObject2 = jSONObject;
        double d2 = jSONObject2.getDouble("faceXScale");
        double d3 = jSONObject2.getDouble("faceYScale");
        double d4 = jSONObject2.getDouble("faceWScale");
        double d5 = jSONObject2.getDouble("faceHScale");
        double d6 = jSONObject2.getDouble("eyeLeftXScale");
        double d7 = jSONObject2.getDouble("eyeLeftYScale");
        double d8 = jSONObject2.getDouble("eyeRightXScale");
        double d9 = jSONObject2.getDouble("eyeRightYScale");
        ContentValues contentValues = new ContentValues();
        contentValues.put("faceXScale", Double.valueOf(d2));
        contentValues.put("faceYScale", Double.valueOf(d3));
        contentValues.put("faceWScale", Double.valueOf(d4));
        contentValues.put("faceHScale", Double.valueOf(d5));
        contentValues.put("leftEyeXScale", Double.valueOf(d6));
        contentValues.put("leftEyeYScale", Double.valueOf(d7));
        contentValues.put("RightEyeXScale", Double.valueOf(d8));
        contentValues.put("RightEyeYScale", Double.valueOf(d9));
        contentValues.put("faceCoverScore", Double.valueOf(d));
        FaceDataManager.safeUpdateFace(contentValues, String.format(Locale.US, "(%s = \"%s\")", new Object[]{"serverId", str}), (String[]) null, true);
    }

    private void setShouldFlatPeopleRelationshipInDB(ContentValues contentValues, PeopleFace peopleFace) {
        if (!this.mShouldFlatPeopleRelationshipInDB) {
            if (!(contentValues.containsKey("groupId") ? contentValues.getAsString("groupId") : "").equalsIgnoreCase(peopleFace.groupId)) {
                this.mShouldFlatPeopleRelationshipInDB = true;
            }
        }
    }

    private boolean shouldContinue(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        return jSONObject.getBoolean("hasMore");
    }

    private void updateSyncInfo(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            GalleryCloudSyncTagUtils.setFaceSyncWatermark(this.mAccount, jSONObject.getLong("watermark"));
            GalleryCloudSyncTagUtils.setFaceSyncToken(this.mAccount, jSONObject.getString("syncToken"));
        }
    }

    /* access modifiers changed from: protected */
    public String getPeopleFaceSyncUrl() {
        return HostManager.PeopleFace.getPeopleFaceSyncUrl();
    }

    /* access modifiers changed from: protected */
    public String getSyncFaceConcreteInfoUrl() {
        return HostManager.PeopleFace.getFaceInfoSyncUrl();
    }

    /* access modifiers changed from: protected */
    public int getSyncItemLimit() {
        return 100;
    }

    /* access modifiers changed from: protected */
    public final boolean handleItem(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        String string = jSONObject.getString(nexExportFormat.TAG_FORMAT_TYPE);
        String string2 = jSONObject.getString("id");
        String string3 = jSONObject.getString("status");
        PeopleFace item = FaceDataManager.getItem(string2);
        ContentValues contentValuesForPeopleFace = FaceAlbumUtil.getContentValuesForPeopleFace(jSONObject);
        if (item == null) {
            if (string.equals("PEOPLE")) {
                PeopleFace groupByPeopleName = FaceDataManager.getGroupByPeopleName(this.mContext, FaceAlbumUtil.getPeopleName(jSONObject));
                if (groupByPeopleName != null) {
                    setShouldFlatPeopleRelationshipInDB(contentValuesForPeopleFace, groupByPeopleName);
                    CloudUtils.updateToLocalDBForSync(FaceDataManager.PEOPLE_FACE_URI, contentValuesForPeopleFace, groupByPeopleName._id);
                } else {
                    handleNewPeopleFace(contentValuesForPeopleFace);
                    this.mShouldFlatPeopleRelationshipInDB = true;
                }
                return false;
            }
            handleNewPeopleFace(contentValuesForPeopleFace);
            return true;
        } else if (item.serverTag >= CloudUtils.getLongAttributeFromJson(jSONObject, "eTag")) {
            return false;
        } else {
            if (string3.equals("normal")) {
                setShouldFlatPeopleRelationshipInDB(contentValuesForPeopleFace, item);
                handleCustom(contentValuesForPeopleFace);
            } else if (string3.equals("deleted")) {
                handleDelete(contentValuesForPeopleFace);
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void handleNewPeopleFace(ContentValues contentValues) {
        contentValues.put("localFlag", 0);
        FaceDataManager.safeInsertFace(contentValues, true);
    }

    public final GallerySyncResult<JSONObject> sync() throws ClientProtocolException, IOException, JSONException, URISyntaxException, IllegalBlockSizeException, BadPaddingException, GalleryMiCloudServerException {
        long currentTimeMillis = System.currentTimeMillis();
        while (SyncConditionManager.check(0) != 2) {
            GallerySyncResult<JSONObject> retryTask = RetryRequestHelper.retryTask(new SyncTask<JSONObject>() {
                public String getIdentifier() {
                    return SyncPeopleFaceFromServer.this.getPeopleFaceSyncUrl();
                }

                public GallerySyncResult<JSONObject> run() throws Exception {
                    SyncPeopleFaceFromServer syncPeopleFaceFromServer = SyncPeopleFaceFromServer.this;
                    return CheckResult.checkXMResultCodeForFaceRequest(syncPeopleFaceFromServer.getPeopleFaceList(syncPeopleFaceFromServer.getSyncItemLimit()), (RequestItemBase) null);
                }
            });
            JSONObject jSONObject = (JSONObject) retryTask.data;
            if (!ErrorHandler.simpleCheckWhetherNeedCleanTables(jSONObject, this.mAccount)) {
                if (jSONObject != null) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    ArrayList<String> handlePeopleFaceDataJson = handlePeopleFaceDataJson(optJSONObject);
                    if (handlePeopleFaceDataJson != null && handlePeopleFaceDataJson.size() > 0) {
                        retryTask = getFaceConcreteInfoList(handlePeopleFaceDataJson);
                        if (retryTask.data == null) {
                        }
                    }
                    updateSyncInfo(optJSONObject);
                    if (!(optJSONObject == null || optJSONObject.length() == 0 || !shouldContinue(optJSONObject))) {
                    }
                }
                flatDBAndCheckBabyAlbumAfterSync();
                SyncLog.d("syncface", "sync people face from server finish, cost time: " + (System.currentTimeMillis() - currentTimeMillis));
                if (!GalleryPreferences.Face.isFirstSyncCompleted()) {
                    GalleryPreferences.Face.setFirstSyncCompleted();
                }
                return retryTask;
            }
        }
        return new GallerySyncResult.Builder().setCode(GallerySyncCode.CONDITION_INTERRUPTED).build();
    }
}
