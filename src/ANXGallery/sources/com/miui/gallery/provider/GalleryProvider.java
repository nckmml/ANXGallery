package com.miui.gallery.provider;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.dao.GalleryLiteEntityManager;
import com.miui.gallery.picker.helper.PickerSqlHelper;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.cache.MediaManager;
import com.miui.gallery.provider.cloudmanager.CloudManager;
import com.miui.gallery.provider.deprecated.GalleryCloudProvider;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.Numbers;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.UriUtil;
import com.miui.gallery.util.face.PeopleItem;
import com.nexstreaming.nexeditorsdk.nexClip;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GalleryProvider extends GalleryCloudProvider {
    private ContentResolver mContentResolver;
    private MediaManager.InitializeListener mInitializeListener = new MediaManager.InitializeListener() {
        public void onProgressUpdate() {
            GalleryProvider.this.notifyChange(GalleryContract.Cloud.CLOUD_URI, (ContentObserver) null, 0);
        }
    };

    private class ContentResolver extends GalleryContentResolver {
        private ContentResolver() {
        }

        /* access modifiers changed from: protected */
        public Object matchUri(Uri uri) {
            return Integer.valueOf(GalleryProvider.sUriMatcher.match(uri));
        }

        /* access modifiers changed from: protected */
        public void notifyInternal(Uri uri, ContentObserver contentObserver, long j) {
            int match = GalleryProvider.sUriMatcher.match(uri);
            if (match == 70 || match == 71 || match == 75) {
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Cloud.CLOUD_URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Album.URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI_OWNER_ALBUM_MEDIA, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI_ALL, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI_OTHER_ALBUM_MEDIA, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.PeopleFace.ONE_PERSON_URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.RecentDiscoveredMedia.URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.RecentDiscoveredMedia.URI_COVER, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.PeopleFace.PERSONS_URI, contentObserver, false);
            } else if (match == 92) {
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.PeopleFace.PERSONS_URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.PeopleFace.IGNORE_PERSONS_URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.PeopleFace.ONE_PERSON_URI, contentObserver, false);
            } else if (match != 110) {
                if (match != 112) {
                    if (match == 152) {
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.RecentDiscoveredMedia.URI, contentObserver, false);
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.RecentDiscoveredMedia.URI_COVER, contentObserver, false);
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Album.URI, contentObserver, false);
                    } else if (match != 180) {
                        switch (match) {
                            case BaiduSceneResult.INVOICE:
                                break;
                            case BaiduSceneResult.VARIOUS_TICKETS:
                            case BaiduSceneResult.EXPRESS_ORDER:
                                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.CloudUser.CLOUD_USER_URI, contentObserver, false);
                                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Album.URI_SHARE_ALL, contentObserver, false);
                                break;
                        }
                    } else {
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Favorites.URI, contentObserver, false);
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Album.URI, contentObserver, false);
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI, contentObserver, false);
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI_OWNER_ALBUM_MEDIA, contentObserver, false);
                        GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI_ALL, contentObserver, false);
                    }
                }
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.ShareUser.SHARE_USER_URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Album.URI_SHARE_ALL, contentObserver, false);
            } else {
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Album.URI, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI_OTHER_ALBUM_MEDIA, contentObserver, false);
                GalleryProvider.this.getContext().getContentResolver().notifyChange(GalleryContract.Media.URI_ALL, contentObserver, false);
            }
            if (j != 0) {
                SyncUtil.requestSync(GalleryProvider.this.getContext(), new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(j).setDelayUpload(true).build());
            }
        }
    }

    static {
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "album", 72);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "media", 51);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "media/#", 52);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "album_media", 73);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_album_media/#", 113);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "persons", 90);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "ignore_persons", 97);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "person", 91);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "gallery_cloud", 70);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "gallery_cloud/#", 71);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloud_owner_sububi/#", 74);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_image", 110);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_image/#", 111);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_image_sububi/#", 115);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "person_recommend", 94);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "peopleFace", 92);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloud_and_share", 50);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "owner_and_other_album", 112);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "other_share_album", 114);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloud_write_bulk_notify", 75);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "person_item", 93);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_user", BaiduSceneResult.VISA);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "share_user/#", BaiduSceneResult.INVOICE);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloud_user", BaiduSceneResult.VARIOUS_TICKETS);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloud_user/#", BaiduSceneResult.EXPRESS_ORDER);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "people_face_cover", 95);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "discovery_message", 150);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "discovery_message/#", 151);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "recent_discovered_media", 152);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "recent_discovered_cover", 153);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloudControl", 170);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "cloudControl/#", 171);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "people_cover", 96);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "image_face", 98);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "people_tag", 99);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "favorites", nexClip.kClip_Rotate_180);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "extended_cloud", 76);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "pick_cloud_and_share", 87);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "persons_item", 100);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "month_media", 53);
        sUriMatcher.addURI("com.miui.gallery.cloud.provider", "people_snapshot", 101);
    }

    private String buildNonProcessingSelection(String str) {
        List<String> queryProcessingMediaPaths = ProcessingMediaManager.queryProcessingMediaPaths();
        if (!MiscUtil.isValid(queryProcessingMediaPaths)) {
            return str;
        }
        StringBuilder sb = new StringBuilder("localFile NOT IN (");
        for (int i = 0; i < queryProcessingMediaPaths.size(); i++) {
            if (!TextUtils.isEmpty(queryProcessingMediaPaths.get(i))) {
                DatabaseUtils.appendEscapedSQLString(sb, queryProcessingMediaPaths.get(i));
                if (i != queryProcessingMediaPaths.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append(")");
        return DatabaseUtils.concatenateWhere(str, sb.toString());
    }

    private long doInsertWithNoNotify(Uri uri, int i, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = sDBHelper.getWritableDatabase();
        if (i == 70 || i == 75) {
            writableDatabase.beginTransactionNonExclusive();
            try {
                long onPreInsert = onPreInsert(writableDatabase, "cloud", contentValues);
                if (onPreInsert == -1) {
                    onPreInsert = writableDatabase.insert("cloud", (String) null, appendValuesForCloud(contentValues, true));
                    if (onPreInsert != -1) {
                        this.mMediaManager.insert(onPreInsert, contentValues);
                        if (contentValues.containsKey("serverType") && Numbers.equals(contentValues.getAsInteger("serverType"), 0)) {
                            Long asLong = contentValues.getAsLong("attributes");
                            this.mMediaManager.insertAttributes(onPreInsert, asLong == null ? 0 : asLong.longValue());
                        }
                    }
                }
                writableDatabase.setTransactionSuccessful();
                return onPreInsert;
            } finally {
                writableDatabase.endTransaction();
            }
        } else if (i == 92) {
            return writableDatabase.insert("peopleFace", (String) null, appendValuesForCloud(contentValues, true));
        } else {
            if (i == 110) {
                return writableDatabase.insert("shareImage", (String) null, appendValuesForCloud(contentValues, true));
            }
            if (i != 152) {
                return -1;
            }
            return writableDatabase.insertWithOnConflict("recentDiscoveredMedia", (String) null, contentValues, 5);
        }
    }

    private List<String> getSha1ListFromFavorites(String str, String[] strArr, boolean z) {
        Cursor cursor = null;
        if (z) {
            try {
                if (TextUtils.isEmpty(str)) {
                    str = "isFavorite NOT NULL AND isFavorite> 0";
                } else {
                    str = str + " AND (isFavorite NOT NULL AND isFavorite> 0)";
                }
            } catch (Throwable th) {
                MiscUtil.closeSilently(cursor);
                throw th;
            }
        }
        cursor = sDBHelper.query("favorites", new String[]{"sha1"}, str, strArr, (String) null, (String) null, (String) null, (String) null);
        if (cursor == null || cursor.getCount() <= 0) {
            ArrayList arrayList = new ArrayList();
            MiscUtil.closeSilently(cursor);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(cursor.getCount());
        while (cursor.moveToNext()) {
            String string = cursor.getString(0);
            if (!TextUtils.isEmpty(string)) {
                arrayList2.add(string);
            }
        }
        MiscUtil.closeSilently(cursor);
        return arrayList2;
    }

    private boolean isBlockedByMediaManager(int i) {
        return (i == 170 || i == 171) ? false : true;
    }

    private static boolean isCloudUri(int i) {
        return isSpecificUri(i, 70, 76);
    }

    private static boolean isFaceUri(int i) {
        return isSpecificUri(i, 90, 101);
    }

    private static boolean isFavoriteUri(int i) {
        return isSpecificUri(i, nexClip.kClip_Rotate_180, nexClip.kClip_Rotate_180);
    }

    private static boolean isMediaUri(int i) {
        return isSpecificUri(i, 50, 53);
    }

    private static boolean isShareUri(int i) {
        return isSpecificUri(i, 110, 115);
    }

    private static boolean isSpecificUri(int i, int i2, int i3) {
        return i >= i2 && i <= i3;
    }

    private static boolean isUserUri(int i) {
        return isSpecificUri(i, BaiduSceneResult.VISA, BaiduSceneResult.EXPRESS_ORDER);
    }

    private boolean needDelayNotify(Uri uri) {
        if (sUriMatcher.match(uri) != 75) {
            return uri.getBooleanQueryParameter("delay_notify", false);
        }
        return true;
    }

    private boolean needNotifyUpdate(Uri uri, int i, ContentValues contentValues) {
        if (i == 71 || i == 92) {
            return true;
        }
        if (i == 75 || i == 70) {
            return contentValues.containsKey("sha1") || contentValues.containsKey("title") || contentValues.containsKey("specialTypeFlags");
        }
        if (i != 180 || !contentValues.containsKey("sha1")) {
            return uri.getBooleanQueryParameter("requireNotifyUri", false);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void notifyChange(Uri uri, ContentObserver contentObserver, long j) {
        if (needDelayNotify(uri)) {
            this.mContentResolver.notifyChangeDelay(uri, contentObserver, j);
        } else {
            this.mContentResolver.notifyChange(uri, contentObserver, j);
        }
    }

    private String parseSelection(String str, String str2, String[] strArr) {
        String str3;
        String format = String.format(str2, (Object[]) strArr);
        StringBuilder sb = new StringBuilder();
        sb.append(format);
        if (!TextUtils.isEmpty(str)) {
            str3 = " AND (" + str + ")";
        } else {
            str3 = "";
        }
        sb.append(str3);
        return sb.toString();
    }

    private static long parseSyncReason(Uri uri, ContentValues contentValues) {
        if (!shouldRequestSync(uri, contentValues)) {
            return 0;
        }
        int match = sUriMatcher.match(uri);
        if (isMediaUri(match) || isCloudUri(match) || isFavoriteUri(match)) {
            return 33;
        }
        if (isFaceUri(match)) {
            return 136;
        }
        return (isShareUri(match) || isUserUri(match)) ? 528 : 0;
    }

    private void registerNotifyUri(Cursor cursor, int i) {
        if (i == 76) {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.ExtendedCloud.URI);
        } else if (i == 180) {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Favorites.URI);
        } else if (i == 90) {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.PeopleFace.PERSONS_URI);
        } else if (i == 91) {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.PeopleFace.ONE_PERSON_URI);
        } else if (i == 112) {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Album.URI_SHARE_ALL);
        } else if (i == 113) {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Media.URI_OTHER_ALBUM_MEDIA);
        } else if (i == 152) {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.RecentDiscoveredMedia.URI);
        } else if (i != 153) {
            switch (i) {
                case 50:
                    cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Media.URI_ALL);
                    return;
                case 51:
                case 52:
                case 53:
                    cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Media.URI);
                    return;
                default:
                    switch (i) {
                        case 70:
                        case 71:
                            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Cloud.CLOUD_URI);
                            return;
                        case 72:
                            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Album.URI);
                            return;
                        case 73:
                            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.Media.URI_OWNER_ALBUM_MEDIA);
                            return;
                        default:
                            switch (i) {
                                case 95:
                                    cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.PeopleFace.PERSONS_URI);
                                    return;
                                case 96:
                                    cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.PeopleFace.PERSONS_URI);
                                    return;
                                case 97:
                                    cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.PeopleFace.IGNORE_PERSONS_URI);
                                    return;
                                default:
                                    switch (i) {
                                        case BaiduSceneResult.VISA:
                                        case BaiduSceneResult.INVOICE:
                                            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.ShareUser.SHARE_USER_URI);
                                            return;
                                        case BaiduSceneResult.VARIOUS_TICKETS:
                                        case BaiduSceneResult.EXPRESS_ORDER:
                                            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.CloudUser.CLOUD_USER_URI);
                                            return;
                                        default:
                                            return;
                                    }
                            }
                    }
            }
        } else {
            cursor.setNotificationUri(getContext().getContentResolver(), GalleryContract.RecentDiscoveredMedia.URI_COVER);
        }
    }

    private static boolean shouldRequestSync(Uri uri, ContentValues contentValues) {
        if (uri.getQueryParameter("URI_PARAM_REQUEST_SYNC") != null) {
            return uri.getBooleanQueryParameter("URI_PARAM_REQUEST_SYNC", false);
        }
        return false;
    }

    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        if (!this.mMediaManager.initialized()) {
            return null;
        }
        SQLiteDatabase writableDatabase = sDBHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            ContentProviderResult[] applyBatch = super.applyBatch(arrayList);
            writableDatabase.setTransactionSuccessful();
            return applyBatch;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        int match = sUriMatcher.match(uri);
        int i = 0;
        if ((isBlockedByMediaManager(match) && !this.mMediaManager.initialized()) || contentValuesArr == null) {
            return 0;
        }
        SQLiteDatabase writableDatabase = sDBHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        int i2 = 0;
        ContentValues contentValues = null;
        while (i < contentValuesArr.length) {
            try {
                if (doInsertWithNoNotify(uri, match, contentValuesArr[i]) != -1) {
                    i2++;
                    contentValues = contentValuesArr[i];
                }
                i++;
            } finally {
                writableDatabase.endTransaction();
            }
        }
        writableDatabase.setTransactionSuccessful();
        if (i2 > 0) {
            notifyChange(uri, (ContentObserver) null, parseSyncReason(uri, contentValues));
        }
        return i2;
    }

    public Bundle call(String str, String str2, Bundle bundle) {
        if (!this.mMediaManager.initialized()) {
            return null;
        }
        SQLiteDatabase writableDatabase = sDBHelper.getWritableDatabase();
        if (!CloudManager.canHandle(str)) {
            return super.call(str, str2, bundle);
        }
        Bundle call = CloudManager.call(getContext(), writableDatabase, this.mMediaManager, str, str2, bundle);
        long j = 0;
        if (call.getBoolean("should_request_sync")) {
            j = 561;
        }
        notifyChange(GalleryContract.Cloud.CLOUD_URI, (ContentObserver) null, j);
        return call;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = sUriMatcher.match(uri);
        if (isBlockedByMediaManager(match) && !this.mMediaManager.initialized()) {
            return 0;
        }
        SQLiteDatabase writableDatabase = sDBHelper.getWritableDatabase();
        int i = -1;
        if (!(match == 51 || match == 52)) {
            if (match == 70 || match == 75) {
                String genIDSelection = genIDSelection(str, strArr);
                this.mMediaManager.delete(genIDSelection, (String[]) null);
                i = writableDatabase.delete("cloud", str, strArr);
                if (i > 0) {
                    deleteAttributes(genIDSelection);
                }
            } else if (match == 92) {
                i = writableDatabase.delete("peopleFace", str, strArr);
            } else if (match == 110) {
                i = writableDatabase.delete("shareImage", str, strArr);
            } else if (match == 130) {
                i = writableDatabase.delete("shareUser", str, strArr);
            } else if (match == 132) {
                i = writableDatabase.delete("cloudUser", str, strArr);
            } else if (match == 150) {
                i = writableDatabase.delete("discoveryMessage", str, strArr);
            } else if (match == 152) {
                i = writableDatabase.delete("recentDiscoveredMedia", str, strArr);
            } else if (match == 170) {
                i = writableDatabase.delete("cloudControl", str, strArr);
            } else if (match != 180) {
                return super.delete(uri, str, strArr);
            } else {
                List<String> sha1ListFromFavorites = getSha1ListFromFavorites(str, strArr, true);
                i = writableDatabase.delete("favorites", str, strArr);
                if (i > 0 && MiscUtil.isValid(sha1ListFromFavorites)) {
                    for (String removeFromFavorites : sha1ListFromFavorites) {
                        this.mMediaManager.removeFromFavorites(removeFromFavorites);
                    }
                }
            }
        }
        if (i > 0) {
            notifyChange(uri, (ContentObserver) null, parseSyncReason(uri, (ContentValues) null));
        }
        return i;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        long j;
        int match = sUriMatcher.match(uri);
        if (isBlockedByMediaManager(match) && !this.mMediaManager.initialized()) {
            return null;
        }
        SQLiteDatabase writableDatabase = sDBHelper.getWritableDatabase();
        if (match == 70 || match == 75 || match == 92 || match == 110) {
            j = doInsertWithNoNotify(uri, match, contentValues);
        } else if (match == 130) {
            j = writableDatabase.insert("shareUser", (String) null, contentValues);
        } else if (match == 132) {
            j = writableDatabase.insert("cloudUser", (String) null, contentValues);
        } else if (match == 150) {
            j = writableDatabase.insert("discoveryMessage", (String) null, contentValues);
        } else if (match == 152) {
            j = writableDatabase.insertWithOnConflict("recentDiscoveredMedia", (String) null, contentValues, 5);
        } else if (match == 170) {
            j = writableDatabase.insertWithOnConflict("cloudControl", (String) null, contentValues, 5);
        } else if (match != 180) {
            return super.insert(uri, contentValues);
        } else {
            j = writableDatabase.insert("favorites", (String) null, contentValues);
            if (j > 0 && contentValues.containsKey("sha1") && contentValues.containsKey("isFavorite")) {
                String asString = contentValues.getAsString("sha1");
                if (contentValues.getAsInteger("isFavorite").intValue() > 0) {
                    this.mMediaManager.insertToFavorites(asString);
                }
            }
        }
        if (j != -1) {
            notifyChange(uri, (ContentObserver) null, parseSyncReason(uri, contentValues));
        }
        return ContentUris.withAppendedId(uri, j);
    }

    public boolean onCreate() {
        super.onCreate();
        this.mContentResolver = new ContentResolver();
        this.mMediaManager = MediaManager.getInstance();
        this.mMediaManager.addInitializeListener(this.mInitializeListener);
        this.mMediaManager.load(sDBHelper);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005f, code lost:
        r28 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0256, code lost:
        r28 = r14;
        r15 = null;
     */
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        Cursor cursor;
        int i2;
        Uri uri2 = uri;
        String[] strArr3 = strArr;
        String str3 = str;
        String[] strArr4 = strArr2;
        String str4 = str2;
        SQLiteDatabase readableDatabase = sDBHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri2);
        if (match == 50 || match == 51 || match == 53) {
            if (uri2.getBooleanQueryParameter("require_full_load", false)) {
                this.mMediaManager.initialized();
            } else {
                this.mMediaManager.ensureMinimumPartDone();
            }
            boolean booleanQueryParameter = uri2.getBooleanQueryParameter("remove_duplicate_items", false);
            boolean booleanQueryParameter2 = uri2.getBooleanQueryParameter("generate_headers", false);
            boolean booleanQueryParameter3 = uri2.getBooleanQueryParameter("remove_processing_items", false);
            Bundle bundle = new Bundle();
            bundle.putBoolean("extra_generate_header", booleanQueryParameter2);
            bundle.putInt("extra_media_group_by", match == 53 ? 1 : 0);
            String buildNonProcessingSelection = booleanQueryParameter3 ? buildNonProcessingSelection(str3) : str3;
            Cursor query = this.mMediaManager.query(strArr, buildNonProcessingSelection, strArr2, str2, booleanQueryParameter ? "sha1" : null, bundle);
            if (query != null) {
                registerNotifyUri(query, match);
            }
            if (match != 50) {
                return query;
            }
            Cursor query2 = ShareMediaManager.query(readableDatabase, strArr, buildNonProcessingSelection, strArr2, "sha1", str2);
            if (query2 != null) {
                registerNotifyUri(query2, match);
            }
            return new MergeCursor(new Cursor[]{query, query2});
        }
        if (match == 76) {
            i = match;
            cursor = readableDatabase.query(UriUtil.getDistinct(uri), "extended_cloud", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
        } else if (match == 87) {
            i = match;
            cursor = rawQuery(PickerSqlHelper.buildPickerResultQuery(strArr3, str3, strArr4, UriUtil.getGroupBy(uri), str4), (String[]) null);
        } else if (match == 130) {
            i = match;
            cursor = readableDatabase.query(UriUtil.getDistinct(uri), "shareUser", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
        } else if (match == 132) {
            i = match;
            cursor = readableDatabase.query(UriUtil.getDistinct(uri), "cloudUser", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
        } else if (match == 180) {
            i = match;
            cursor = readableDatabase.query(UriUtil.getDistinct(uri), "favorites", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
        } else if (match == 170) {
            i = match;
            cursor = readableDatabase.query(UriUtil.getDistinct(uri), "cloudControl", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
        } else if (match != 171) {
            switch (match) {
                case 70:
                    i = match;
                    cursor = readableDatabase.query(UriUtil.getDistinct(uri), "cloud", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
                    break;
                case 71:
                    i = match;
                    cursor = readableDatabase.query("cloud", strArr, parseSelection(str3, "_id = %s", new String[]{String.valueOf(ContentUris.parseId(uri))}), strArr2, (String) null, (String) null, str2);
                    break;
                case 72:
                    boolean booleanQueryParameter4 = uri2.getBooleanQueryParameter("join_video", false);
                    boolean booleanQueryParameter5 = uri2.getBooleanQueryParameter("join_face", false);
                    boolean booleanQueryParameter6 = uri2.getBooleanQueryParameter("join_share", false);
                    boolean booleanQueryParameter7 = uri2.getBooleanQueryParameter("join_pano", false);
                    boolean booleanQueryParameter8 = uri2.getBooleanQueryParameter("join_recent", false);
                    boolean booleanQueryParameter9 = uri2.getBooleanQueryParameter("join_favorites", false);
                    boolean booleanQueryParameter10 = uri2.getBooleanQueryParameter("exclude_empty_album", false);
                    boolean booleanQueryParameter11 = uri2.getBooleanQueryParameter("all_except_deleted", false);
                    boolean booleanQueryParameter12 = uri2.getBooleanQueryParameter("fill_covers", true);
                    boolean booleanQueryParameter13 = uri2.getBooleanQueryParameter("stub_for_virtual_albums", false);
                    boolean booleanQueryParameter14 = uri2.getBooleanQueryParameter("exclude_hidden_album", true);
                    String queryParameter = uri2.getQueryParameter("media_type");
                    i = match;
                    cursor = AlbumManager.query(readableDatabase, strArr, str, strArr2, str2, booleanQueryParameter4, booleanQueryParameter5, booleanQueryParameter6, booleanQueryParameter7, booleanQueryParameter8, booleanQueryParameter9, booleanQueryParameter10, booleanQueryParameter11, uri2.getBooleanQueryParameter("remove_duplicate_items", false), queryParameter == null ? null : Integer.valueOf(queryParameter), booleanQueryParameter12, booleanQueryParameter13, booleanQueryParameter14, UriUtil.getLimit(uri));
                    break;
                case 73:
                    boolean booleanQueryParameter15 = uri2.getBooleanQueryParameter("remove_duplicate_items", false);
                    boolean booleanQueryParameter16 = uri2.getBooleanQueryParameter("generate_headers", false);
                    boolean booleanQueryParameter17 = uri2.getBooleanQueryParameter("remove_processing_items", false);
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("extra_generate_header", booleanQueryParameter16);
                    cursor = this.mMediaManager.query(strArr, booleanQueryParameter17 ? buildNonProcessingSelection(str3) : str3, strArr2, str2, booleanQueryParameter15 ? "sha1" : null, bundle2);
                    break;
                case 74:
                    uri.getLastPathSegment();
                    break;
                default:
                    switch (match) {
                        case 90:
                            cursor = rawQuery(FaceManager.buildPersonsQuery(), (String[]) null);
                            break;
                        case 91:
                            cursor = FaceManager.queryOnePersonAlbum(readableDatabase, strArr, str, str2, strArr4[0], strArr4[1], uri2.getBooleanQueryParameter("generate_headers", false));
                            break;
                        case 92:
                            cursor = readableDatabase.query("peopleFace", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
                            break;
                        case 93:
                            cursor = rawQuery(FaceManager.buildOnePersonItemQuery(strArr3, strArr4[0]), (String[]) null);
                            break;
                        case 94:
                            cursor = rawQuery(FaceManager.buildRecommendFacesOfOnePersonQuery(strArr3, str4, strArr4[0]), (String[]) null);
                            break;
                        case 95:
                            cursor = AlbumManager.queryFaceAlbumCover(readableDatabase);
                            break;
                        case 96:
                            cursor = rawQuery(FaceManager.buildPeopleCoverQuery(strArr3, uri2.getQueryParameter("serverId"), uri2.getQueryParameter("_id")), (String[]) null);
                            break;
                        case 97:
                            cursor = rawQuery(FaceManager.buildIgnorePersonsQuery(), (String[]) null);
                            break;
                        case 98:
                            cursor = rawQuery(FaceManager.buildImageFaceQuery(strArr3, uri2.getQueryParameter("serverId"), uri2.getQueryParameter("image_server_id")), (String[]) null);
                            break;
                        case 99:
                            cursor = rawQuery(FaceManager.buildPeopleTagQuery(), (String[]) null);
                            break;
                        case 100:
                            cursor = rawQuery(FaceManager.buildPersonsItemQuery(strArr3, str3, str4, strArr4[0], strArr4[1]), (String[]) null);
                            break;
                        case 101:
                            cursor = GalleryLiteEntityManager.getInstance().rawQuery(PeopleItem.class, strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
                            break;
                        default:
                            switch (match) {
                                case 110:
                                    cursor = readableDatabase.query(UriUtil.getDistinct(uri), "shareImage", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
                                    break;
                                case 111:
                                    cursor = readableDatabase.query("shareImage", strArr, parseSelection(str3, "_id = %s", new String[]{uri.getLastPathSegment()}), strArr2, (String) null, (String) null, str2);
                                    break;
                                case 112:
                                    cursor = ShareAlbumManager.query(getContext(), readableDatabase, strArr, str, strArr2, str2);
                                    break;
                                case 113:
                                    cursor = ShareMediaManager.query(readableDatabase, strArr, parseSelection(str3, "localGroupId = %s", new String[]{String.valueOf(ShareAlbumManager.getOriginalAlbumId(ContentUris.parseId(uri)))}), strArr2, uri2.getBooleanQueryParameter("remove_duplicate_items", false) ? "sha1" : null, str2);
                                    break;
                                case 114:
                                    cursor = readableDatabase.query("shareAlbum", strArr, str, strArr2, (String) null, (String) null, str2);
                                    break;
                                case 115:
                                    uri.getLastPathSegment();
                                    break;
                                default:
                                    switch (match) {
                                        case 150:
                                            cursor = readableDatabase.query(UriUtil.getDistinct(uri), "discoveryMessage", strArr, str, strArr2, UriUtil.getGroupBy(uri), UriUtil.getHaving(uri), str2, UriUtil.getLimit(uri));
                                            break;
                                        case 151:
                                            cursor = readableDatabase.query("discoveryMessage", strArr, parseSelection(str3, "_id = %s", new String[]{uri.getLastPathSegment()}), strArr2, (String) null, (String) null, str2);
                                            break;
                                        case 152:
                                            cursor = RecentDiscoveryMediaManager.query(readableDatabase, strArr, uri2.getBooleanQueryParameter("remove_processing_items", false) ? buildNonProcessingSelection(str3) : str3, strArr2, UriUtil.getGroupBy(uri), str2, uri2.getBooleanQueryParameter("remove_duplicate_items", false), uri2.getBooleanQueryParameter("generate_headers", false));
                                            break;
                                        case 153:
                                            boolean booleanQueryParameter18 = uri2.getBooleanQueryParameter("remove_duplicate_items", false);
                                            try {
                                                i2 = Integer.parseInt(uri2.getQueryParameter("album_cover_count"));
                                            } catch (NumberFormatException e) {
                                                Log.e("GalleryProvider", e.getMessage());
                                                i2 = 1;
                                            }
                                            cursor = RecentDiscoveryMediaManager.queryAlbumCover(readableDatabase, i2, booleanQueryParameter18);
                                            break;
                                        default:
                                            cursor = super.query(uri, strArr, str, strArr2, str2);
                                            break;
                                    }
                            }
                    }
            }
        } else {
            i = match;
            cursor = readableDatabase.query("cloudControl", strArr, parseSelection(str3, "_id = %s", new String[]{String.valueOf(ContentUris.parseId(uri))}), strArr2, (String) null, (String) null, str2);
        }
        if (cursor != null) {
            registerNotifyUri(cursor, i);
        }
        return cursor;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i;
        int match = sUriMatcher.match(uri);
        if (isBlockedByMediaManager(match) && !this.mMediaManager.initialized()) {
            return 0;
        }
        SQLiteDatabase writableDatabase = sDBHelper.getWritableDatabase();
        if (match != 70) {
            boolean z = true;
            if (match == 71) {
                Set<String> keySet = contentValues.keySet();
                if (keySet.size() != 1 || !keySet.contains("sync_status")) {
                    throw new IllegalArgumentException("only support key: sync_status");
                }
                i = this.mMediaManager.update(genIDSelection(str, strArr), (String[]) null, contentValues);
            } else if (match != 75) {
                if (match == 92) {
                    i = writableDatabase.update("peopleFace", contentValues, str, strArr);
                } else if (match == 110) {
                    i = writableDatabase.update("shareImage", appendValuesForCloud(contentValues, false), str, strArr);
                } else if (match == 114) {
                    i = writableDatabase.update("shareAlbum", contentValues, str, strArr);
                } else if (match != 180) {
                    if (match != 150 && match != 151) {
                        if (match != 170 && match != 171) {
                            switch (match) {
                                case BaiduSceneResult.VISA:
                                case BaiduSceneResult.INVOICE:
                                    i = writableDatabase.update("shareUser", contentValues, str, strArr);
                                    break;
                                case BaiduSceneResult.VARIOUS_TICKETS:
                                case BaiduSceneResult.EXPRESS_ORDER:
                                    i = writableDatabase.update("cloudUser", contentValues, str, strArr);
                                    break;
                                default:
                                    i = super.update(uri, contentValues, str, strArr);
                                    break;
                            }
                        } else {
                            i = writableDatabase.update("cloudControl", contentValues, str, strArr);
                        }
                    } else {
                        i = writableDatabase.update("discoveryMessage", contentValues, str, strArr);
                    }
                } else {
                    int update = writableDatabase.update("favorites", contentValues, str, strArr);
                    if (update > 0 && contentValues.containsKey("isFavorite")) {
                        if (contentValues.getAsInteger("isFavorite").intValue() <= 0) {
                            z = false;
                        }
                        for (String next : getSha1ListFromFavorites(str, strArr, false)) {
                            if (z) {
                                this.mMediaManager.insertToFavorites(next);
                            } else {
                                this.mMediaManager.removeFromFavorites(next);
                            }
                        }
                    }
                    i = update;
                }
            }
            if (i > 0 && needNotifyUpdate(uri, match, contentValues)) {
                notifyChange(uri, (ContentObserver) null, parseSyncReason(uri, contentValues));
            }
            return i;
        }
        appendValuesForCloud(contentValues, false);
        String genIDSelection = genIDSelection(str, strArr);
        if (contentValues.containsKey("attributes")) {
            updateAttributes(writableDatabase, "cloud", genIDSelection, contentValues);
        }
        this.mMediaManager.update(genIDSelection, (String[]) null, contentValues);
        i = writableDatabase.update("cloud", contentValues, str, strArr);
        notifyChange(uri, (ContentObserver) null, parseSyncReason(uri, contentValues));
        return i;
    }
}
