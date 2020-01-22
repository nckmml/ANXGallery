package com.miui.gallery.backup;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.backup.GalleryBackupProtos;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.scanner.MediaScanner;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StaticContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GalleryBackupHelper {
    private static final String TAG = "GalleryBackupHelper";

    public static GalleryBackupProtos.BackupMessage backup() {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        GalleryBackupProtos.BackupMessage backupMessage = (GalleryBackupProtos.BackupMessage) GalleryBackupProtos.BackupMessage.newBuilder().setSettings(backupSettings()).addAllAlbumProfiles(backupAlbumProfiles(hashMap)).build();
        hashMap.put("costs", String.valueOf(System.currentTimeMillis() - currentTimeMillis));
        GallerySamplingStatHelper.recordCountEvent("local_backup", "full_backup", hashMap);
        return backupMessage;
    }

    private static List<GalleryBackupProtos.BackupMessage.AlbumProfile> backupAlbumProfiles(Map<String, String> map) {
        LinkedList linkedList = new LinkedList();
        Uri build = GalleryContract.Album.URI.buildUpon().appendQueryParameter("fill_covers", "false").appendQueryParameter("exclude_empty_album", "true").appendQueryParameter("exclude_hidden_album", "false").build();
        SafeDBUtil.safeQuery(StaticContext.sGetAndroidContext(), build, new String[]{"local_path", "attributes", "name"}, "attributes & 48 <> 16", (String[]) null, (String) null, new SafeDBUtil.QueryHandler(linkedList) {
            private final /* synthetic */ List f$0;

            {
                this.f$0 = r1;
            }

            public final Object handle(Cursor cursor) {
                return GalleryBackupHelper.lambda$backupAlbumProfiles$46(this.f$0, cursor);
            }
        });
        Log.i(TAG, "Backup %d album profiles", (Object) Integer.valueOf(linkedList.size()));
        map.put("album_count", String.valueOf(linkedList.size()));
        return linkedList;
    }

    private static GalleryBackupProtos.BackupMessage.Settings backupSettings() {
        return (GalleryBackupProtos.BackupMessage.Settings) GalleryBackupProtos.BackupMessage.Settings.newBuilder().setOnlyShowLocalPhoto(GalleryPreferences.LocalMode.isOnlyShowLocalPhoto()).setShowHiddenAlbum(GalleryPreferences.HiddenAlbum.isShowHiddenAlbum()).setSlideshowInterval(GalleryPreferences.SlideShow.getSlideShowInterval()).setRemindConnectNetworkEveryTime(GalleryPreferences.CTA.remindConnectNetworkEveryTime()).build();
    }

    static /* synthetic */ Object lambda$backupAlbumProfiles$46(List list, Cursor cursor) {
        if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                list.add(GalleryBackupProtos.BackupMessage.AlbumProfile.newBuilder().setPath(cursor.getString(0)).setAttributes(cursor.getLong(1)).setName(cursor.getString(2)).build());
                cursor.moveToNext();
            }
        }
        return null;
    }

    public static void restore(GalleryBackupProtos.BackupMessage backupMessage) {
        if (backupMessage == null) {
            Log.w(TAG, "Backup message is null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        restoreSettings(backupMessage.getSettings());
        restoreAlbumProfiles(backupMessage.getAlbumProfilesList());
        hashMap.put("costs", String.valueOf(System.currentTimeMillis() - currentTimeMillis));
        GallerySamplingStatHelper.recordCountEvent("local_backup", "full_restore", hashMap);
    }

    private static void restoreAlbumProfiles(List<GalleryBackupProtos.BackupMessage.AlbumProfile> list) {
        if (list == null || list.size() <= 0) {
            Log.i(TAG, "No album profile exists");
            return;
        }
        int i = 0;
        ContentValues contentValues = new ContentValues();
        for (GalleryBackupProtos.BackupMessage.AlbumProfile next : list) {
            if (!TextUtils.isEmpty(next.getPath())) {
                contentValues.clear();
                contentValues.put("attributes", Long.valueOf(next.getAttributes()));
                if (!TextUtils.isEmpty(next.getName())) {
                    contentValues.put("fileName", next.getName());
                }
                MediaScanner.updateOrInsertAlbum(StaticContext.sGetAndroidContext(), next.getPath(), contentValues);
                i++;
            }
        }
        if (i > 0) {
            GalleryPreferences.MediaScanner.recordAlbumRestoreTimeMillis();
        }
        Log.i(TAG, "Restore %d album profiles", (Object) Integer.valueOf(i));
    }

    private static void restoreSettings(GalleryBackupProtos.BackupMessage.Settings settings) {
        if (settings != null) {
            GalleryPreferences.LocalMode.setOnlyShowLocalPhoto(settings.getOnlyShowLocalPhoto());
            GalleryPreferences.HiddenAlbum.setShowHiddenAlbum(settings.getShowHiddenAlbum());
            GalleryPreferences.SlideShow.setSlideShowInterval(settings.getSlideshowInterval());
        }
    }
}
