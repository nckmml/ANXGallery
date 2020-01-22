package com.miui.gallery.provider.deprecated;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.AccountCache;
import com.miui.gallery.cloud.CheckResult;
import com.miui.gallery.cloud.CloudGroupUrlProvider;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloud.GalleryCloudUtils;
import com.miui.gallery.data.DBImage;
import com.miui.gallery.data.DBShareAlbum;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.FaceManager;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.util.GalleryUtils;
import com.miui.gallery.util.Utils;
import com.miui.gallery.util.face.FaceRegionRectF;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

public class ThumbnailInfo {
    private String mBgImageId;
    private long mBgImageLocalId = -1;
    private String mBgUrl;
    private String mCoverImageId;
    private String mCoverUrl;
    private String mFaceId;
    private FaceInfo mFaceInfo;
    private RectF mFaceRegion;
    private String mFaceUrl;
    private final long mGroupLocalId;
    private final boolean mIsSharerAlbum;
    private long mLastTimeRequest;

    private static class FaceInfo {
        private final double eyeLeftXScale;
        private final double eyeLeftYScale;
        private final double eyeRightXScale;
        private final double eyeRightYScale;
        private final double faceHScale;
        private final int faceRegionOrientation;
        private final double faceWScale;
        private final double faceXScale;
        private final double faceYScale;

        private FaceInfo(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, int i) {
            this.eyeLeftXScale = d;
            this.eyeLeftYScale = d2;
            this.eyeRightXScale = d3;
            this.eyeRightYScale = d4;
            this.faceXScale = d5;
            this.faceYScale = d6;
            this.faceWScale = d7;
            this.faceHScale = d8;
            this.faceRegionOrientation = i;
        }

        static boolean equals(FaceInfo faceInfo, FaceInfo faceInfo2) {
            return faceInfo == null ? faceInfo2 == null : faceInfo.equals(faceInfo2);
        }

        static FaceInfo from(String str) throws JSONException {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            return new FaceInfo(jSONObject.getDouble("eyeLeftXScale"), jSONObject.getDouble("eyeLeftYScale"), jSONObject.getDouble("eyeRightXScale"), jSONObject.getDouble("eyeRightYScale"), jSONObject.getDouble("faceXScale"), jSONObject.getDouble("faceYScale"), jSONObject.getDouble("faceWScale"), jSONObject.getDouble("faceHScale"), jSONObject.getInt("faceOrientation"));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FaceInfo)) {
                return false;
            }
            FaceInfo faceInfo = (FaceInfo) obj;
            return Utils.doubleEquals(this.eyeLeftXScale, faceInfo.eyeLeftXScale) && Utils.doubleEquals(this.eyeLeftYScale, faceInfo.eyeLeftYScale) && Utils.doubleEquals(this.eyeRightXScale, faceInfo.eyeRightXScale) && Utils.doubleEquals(this.eyeRightYScale, faceInfo.eyeRightYScale) && Utils.doubleEquals(this.faceXScale, faceInfo.faceXScale) && Utils.doubleEquals(this.faceYScale, faceInfo.faceYScale) && Utils.doubleEquals(this.faceWScale, faceInfo.faceWScale) && Utils.doubleEquals(this.faceHScale, faceInfo.faceHScale) && this.faceRegionOrientation == faceInfo.faceRegionOrientation;
        }

        /* access modifiers changed from: package-private */
        public RectF getFacePosition() {
            double d = this.faceXScale;
            float f = (float) d;
            double d2 = this.faceYScale;
            return new FaceRegionRectF(f, (float) d2, (float) (d + this.faceWScale), (float) (d2 + this.faceHScale), this.faceRegionOrientation);
        }

        /* access modifiers changed from: package-private */
        public String toJSON() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("eyeLeftXScale", this.eyeLeftXScale);
            jSONObject.put("eyeLeftYScale", this.eyeLeftYScale);
            jSONObject.put("eyeRightXScale", this.eyeRightXScale);
            jSONObject.put("eyeRightYScale", this.eyeRightYScale);
            jSONObject.put("faceXScale", this.faceXScale);
            jSONObject.put("faceYScale", this.faceYScale);
            jSONObject.put("faceWScale", this.faceWScale);
            jSONObject.put("faceHScale", this.faceHScale);
            jSONObject.put("faceOrientation", this.faceRegionOrientation);
            return jSONObject.toString();
        }
    }

    public ThumbnailInfo(long j, boolean z, String str) {
        this.mGroupLocalId = j;
        this.mIsSharerAlbum = z;
        initBy(str);
    }

    public static String getFaceByFaceId(String str, RectF[] rectFArr) {
        return FaceManager.queryCoverImageOfOneFace(str, rectFArr);
    }

    public static Cursor getItemCursor(Uri uri, String str, String str2) {
        return GalleryApp.sGetAndroidContext().getContentResolver().query(CloudUtils.getLimitUri(uri, 1), new String[]{"*"}, str + "=?", new String[]{str2}, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    private void getThumbnailInfoFromServer() {
        String str;
        String valueOf = String.valueOf(this.mGroupLocalId);
        if (this.mIsSharerAlbum) {
            DBShareAlbum dBShareAlbumByLocalId = CloudUtils.getDBShareAlbumByLocalId(valueOf);
            if (dBShareAlbumByLocalId != null) {
                str = dBShareAlbumByLocalId.getAlbumId();
                if (!TextUtils.isEmpty(str)) {
                    if (!GalleryPreferences.CTA.canConnectNetwork()) {
                        Log.e("ThumbnailInfo", "cta not allowed");
                        return;
                    }
                    AccountCache.AccountInfo accountInfo = AccountCache.getAccountInfo();
                    if (accountInfo == null) {
                        Log.e("ThumbnailInfo", "accountInfo is null");
                        return;
                    }
                    String thumbnailInfoUrl = CloudGroupUrlProvider.getUrlProvider(this.mIsSharerAlbum).getThumbnailInfoUrl(GalleryCloudUtils.getAccountName(), str);
                    try {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new BasicNameValuePair("sharedAlbumId", str));
                        JSONObject fromXiaomi = CloudUtils.getFromXiaomi(thumbnailInfoUrl, arrayList, accountInfo.mAccount, accountInfo.mToken, 0, false);
                        Log.d("ThumbnailInfo", "get thumbnail info from server: " + fromXiaomi);
                        if (CheckResult.parseErrorCode(fromXiaomi) == 0) {
                            ThumbnailInfo thumbnailInfo = new ThumbnailInfo(this.mGroupLocalId, this.mIsSharerAlbum, fromXiaomi.getJSONObject("data").toString());
                            if (TextUtils.isEmpty(this.mCoverImageId)) {
                                this.mCoverUrl = thumbnailInfo.mCoverUrl;
                            }
                            if (TextUtils.isEmpty(this.mFaceId)) {
                                this.mFaceUrl = thumbnailInfo.mFaceUrl;
                                this.mFaceInfo = thumbnailInfo.mFaceInfo;
                            }
                            if (this.mBgImageLocalId == -1) {
                                this.mBgUrl = thumbnailInfo.mBgUrl;
                                this.mBgImageId = null;
                            }
                            this.mLastTimeRequest = System.currentTimeMillis();
                            saveToDB();
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    return;
                }
            }
        } else {
            DBImage item = CloudUtils.getItem(GalleryCloudUtils.CLOUD_URI, GalleryApp.sGetAndroidContext(), "_id", valueOf);
            if (item != null) {
                str = item.getServerId();
                if (!TextUtils.isEmpty(str)) {
                }
            }
        }
        str = null;
        if (!TextUtils.isEmpty(str)) {
        }
    }

    private void initBy(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.mCoverImageId = jSONObject.optString("coverImageId");
                this.mBgImageId = jSONObject.optString("backgroundImageId");
                this.mBgImageLocalId = jSONObject.optLong("backgroundImageLocalId", -1);
                this.mFaceId = jSONObject.optString("faceId");
                this.mCoverUrl = jSONObject.optString("coverUrl");
                this.mFaceUrl = jSONObject.optString("faceUrl");
                this.mBgUrl = jSONObject.optString("backgroundUrl");
                String optString = jSONObject.optString("faceInfo");
                if (!TextUtils.isEmpty(optString)) {
                    JSONObject jSONObject2 = new JSONObject(optString);
                    JSONObject optJSONObject = jSONObject.optJSONObject("faceExif");
                    int i = -1;
                    if (optJSONObject != null) {
                        i = optJSONObject.optInt("orientation", -1);
                    }
                    jSONObject2.put("faceOrientation", i);
                    optString = jSONObject2.toString();
                }
                this.mFaceInfo = FaceInfo.from(optString);
                this.mLastTimeRequest = jSONObject.optLong("lastTimeRequest");
            } catch (JSONException e) {
                Log.e("ThumbnailInfo", "fail to parse ThumbnailInfo from " + str, e);
            }
        }
    }

    private void putString(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put(str, str2);
        }
    }

    private void saveToDB() {
        boolean z = this.mIsSharerAlbum;
        boolean z2 = this.mIsSharerAlbum;
        if (userChanged()) {
            String str = this.mIsSharerAlbum ? "shareAlbum" : "cloud";
            String transformToEditedColumnsElement = GalleryCloudUtils.transformToEditedColumnsElement(this.mIsSharerAlbum ? 29 : 66);
            boolean z3 = this.mIsSharerAlbum;
            GalleryUtils.safeExec(String.format("update %s set %s=%s, %s=coalesce(replace(%s, '%s', '') || '%s', '%s') where %s=%d", new Object[]{str, "thumbnailInfo", DatabaseUtils.sqlEscapeString(toString()), "editedColumns", "editedColumns", transformToEditedColumnsElement, transformToEditedColumnsElement, transformToEditedColumnsElement, "_id", Long.valueOf(this.mGroupLocalId)}));
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("thumbnailInfo", toString());
            GalleryUtils.safeUpdate(this.mIsSharerAlbum ? GalleryCloudUtils.SHARE_ALBUM_URI : GalleryCloudUtils.CLOUD_URI, contentValues, String.format(Locale.US, "%s=?", new Object[]{"_id"}), new String[]{String.valueOf(this.mGroupLocalId)});
        }
        GalleryApp.sGetAndroidContext().getContentResolver().notifyChange(GalleryContract.Album.URI, (ContentObserver) null, true);
    }

    private boolean thumbnailInfoTimeout() {
        return System.currentTimeMillis() - this.mLastTimeRequest > 86400000;
    }

    private boolean userChanged() {
        return !TextUtils.isEmpty(this.mCoverImageId) || !TextUtils.isEmpty(this.mFaceId) || !TextUtils.isEmpty(this.mBgImageId) || this.mBgImageLocalId != -1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ThumbnailInfo)) {
            return false;
        }
        ThumbnailInfo thumbnailInfo = (ThumbnailInfo) obj;
        return TextUtils.equals(this.mCoverImageId, thumbnailInfo.mCoverImageId) && TextUtils.equals(this.mBgImageId, thumbnailInfo.mBgImageId) && this.mBgImageLocalId == thumbnailInfo.mBgImageLocalId && TextUtils.equals(this.mFaceId, thumbnailInfo.mFaceId) && TextUtils.equals(this.mCoverUrl, thumbnailInfo.mCoverUrl) && TextUtils.equals(this.mFaceUrl, thumbnailInfo.mFaceUrl) && TextUtils.equals(this.mBgUrl, thumbnailInfo.mBgUrl) && FaceInfo.equals(this.mFaceInfo, thumbnailInfo.mFaceInfo);
    }

    public String getBgPath() {
        long j = this.mBgImageLocalId;
        if (j == -1 && !TextUtils.isEmpty(this.mBgImageId)) {
            j = CloudUtils.getItemId(GalleryCloudUtils.CLOUD_URI, "serverId", this.mBgImageId);
        }
        Cursor cursor = null;
        try {
            cursor = getItemCursor(GalleryCloudUtils.CLOUD_URI, "_id", String.valueOf(j));
            if (cursor != null && cursor.moveToNext()) {
                return getItemPath(cursor);
            }
            if (cursor == null) {
                return "";
            }
            cursor.close();
            return "";
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public String getFaceInfo(boolean z) {
        String str;
        boolean z2 = false;
        if (z || TextUtils.isEmpty(this.mFaceId)) {
            str = null;
        } else {
            RectF[] rectFArr = new RectF[1];
            str = getFaceByFaceId(this.mFaceId, rectFArr);
            this.mFaceRegion = rectFArr[0];
        }
        if (str != null) {
            return str;
        }
        boolean isEmpty = TextUtils.isEmpty(this.mFaceUrl);
        if (!z ? !(!isEmpty || !thumbnailInfoTimeout()) : !(!isEmpty && !thumbnailInfoTimeout())) {
            z2 = true;
        }
        if (z2) {
            getThumbnailInfoFromServer();
        }
        if (!TextUtils.isEmpty(this.mFaceUrl)) {
            return this.mFaceUrl;
        }
        return null;
    }

    public RectF getFaceRegion() {
        return this.mFaceRegion;
    }

    public RectF getFaceRegionFromFaceInfo() {
        FaceInfo faceInfo = this.mFaceInfo;
        if (faceInfo == null) {
            return null;
        }
        return faceInfo.getFacePosition();
    }

    public String getItemPath(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("localFile"));
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String string2 = cursor.getString(cursor.getColumnIndex("thumbnailFile"));
        return !TextUtils.isEmpty(string2) ? string2 : cursor.getString(cursor.getColumnIndex("microthumbfile"));
    }

    public String setBgImage(long j) {
        String valueOf = String.valueOf(-1001);
        Cursor itemCursor = getItemCursor(GalleryCloudUtils.CLOUD_URI, "_id", String.valueOf(j));
        if (itemCursor != null && itemCursor.getCount() != 0) {
            itemCursor.moveToNext();
            ContentValues contentValues = new ContentValues();
            int i = itemCursor.getInt(itemCursor.getColumnIndex("localFlag"));
            if (i == 0 || i == 5 || i == 6) {
                Log.d("ThumbnailInfo", "server id found, insert as copy from cloud");
                contentValues.put("localFlag", 6);
            } else {
                Log.d("ThumbnailInfo", "no server id, just insert as manual create");
                contentValues.put("localFlag", -2);
            }
            contentValues.put("microthumbfile", itemCursor.getString(itemCursor.getColumnIndex("microthumbfile")));
            contentValues.put("thumbnailFile", itemCursor.getString(itemCursor.getColumnIndex("thumbnailFile")));
            contentValues.put("localFile", itemCursor.getString(itemCursor.getColumnIndex("localFile")));
            contentValues.put("localGroupId", valueOf);
            contentValues.put("localImageId", Integer.valueOf(itemCursor.getInt(itemCursor.getColumnIndex("_id"))));
            String itemPath = getItemPath(itemCursor);
            if (itemCursor != null) {
                itemCursor.close();
            }
            long insert = GalleryDBHelper.getInstance().insert("cloud", contentValues);
            Log.w("ThumbnailInfo", "mediaId1 : " + insert);
            if (insert > 0) {
                long j2 = this.mBgImageLocalId;
                if (j2 != -1 && !CloudUtils.deleteItemInHiddenAlbum(j2)) {
                    Log.w("ThumbnailInfo", "failed to delete old bg, old local id: " + this.mBgImageLocalId);
                }
                this.mBgImageLocalId = insert;
                this.mBgImageId = null;
                this.mBgUrl = null;
                saveToDB();
            }
            return itemPath;
        } else if (itemCursor == null) {
            return "";
        } else {
            itemCursor.close();
            return "";
        }
    }

    public boolean setFaceId(String str) {
        if (TextUtils.equals(this.mFaceId, str)) {
            return false;
        }
        this.mFaceId = str;
        this.mFaceUrl = null;
        this.mFaceInfo = null;
        return true;
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            putString(jSONObject, "coverImageId", this.mCoverImageId);
            putString(jSONObject, "backgroundImageId", this.mBgImageId);
            putString(jSONObject, "faceId", this.mFaceId);
            putString(jSONObject, "coverUrl", this.mCoverUrl);
            putString(jSONObject, "faceUrl", this.mFaceUrl);
            putString(jSONObject, "backgroundUrl", this.mBgUrl);
            jSONObject.put("faceInfo", this.mFaceInfo == null ? null : this.mFaceInfo.toJSON());
            if (this.mBgImageLocalId != -1) {
                jSONObject.put("backgroundImageLocalId", this.mBgImageLocalId);
            }
            if (this.mLastTimeRequest > 0) {
                jSONObject.put("lastTimeRequest", this.mLastTimeRequest);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
