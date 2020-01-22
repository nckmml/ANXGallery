package com.miui.gallery.cloud;

import android.accounts.Account;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.cloud.CloudUtils;
import com.miui.gallery.cloudcontrol.CloudControlRequestHelper;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.InternalContract;
import com.miui.gallery.push.GalleryPushManager;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddAccount {
    static /* synthetic */ Set lambda$queryExistSecretFiles$54(Cursor cursor) {
        if (cursor == null || cursor.getCount() <= 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        while (cursor.moveToNext()) {
            hashSet.add(cursor.getString(0));
        }
        return hashSet;
    }

    public static void onAddAccount(Context context, Account account) {
        File[] listFiles;
        String probeSecretFileName;
        GalleryPushManager.getInstance().onAddAccount(context, account);
        boolean existXiaomiAccount = SyncUtil.existXiaomiAccount(GalleryApp.sGetAndroidContext());
        Log.d("AddAccount", "Exist xiaomi Account %s", (Object) String.valueOf(existXiaomiAccount));
        if (existXiaomiAccount) {
            Log.d("AddAccount", "Done request cloud control data, result %s", (Object) String.valueOf(new CloudControlRequestHelper(GalleryApp.sGetAndroidContext()).execRequestSync(true)));
        }
        String[] pathsInExternalStorage = StorageUtils.getPathsInExternalStorage(context, StorageUtils.DIRECTORY_SECRET_ALBUM_PATH);
        if (account != null && !TextUtils.isEmpty(account.name) && pathsInExternalStorage != null && pathsInExternalStorage.length > 0) {
            Set<String> queryExistSecretFiles = queryExistSecretFiles(context);
            for (String file : pathsInExternalStorage) {
                File file2 = new File(file);
                if (file2.exists() && file2.isDirectory() && (listFiles = file2.listFiles()) != null && listFiles.length > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (File file3 : listFiles) {
                        if (file3.isFile() && ((queryExistSecretFiles == null || !queryExistSecretFiles.contains(file3.getAbsolutePath())) && (probeSecretFileName = CloudUtils.SecretAlbumUtils.probeSecretFileName(file3.getName(), account.name)) != null)) {
                            Log.d("AddAccount", "Recover secret file [%s] to album", (Object) file3.getAbsolutePath());
                            File file4 = new File(file3.getParent(), probeSecretFileName);
                            if (FileUtils.move(file3, file4)) {
                                arrayList.add(new Uri.Builder().scheme("file").path(file4.getAbsolutePath()).build());
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        com.miui.gallery.provider.CloudUtils.addToSecret(context, (ArrayList<Uri>) arrayList);
                    }
                }
            }
        }
        DeleteRecorder.onAddAccount();
    }

    private static Set<String> queryExistSecretFiles(Context context) {
        return (Set) SafeDBUtil.safeQuery(context, GalleryContract.Cloud.CLOUD_URI, new String[]{"localFile"}, DatabaseUtils.concatenateWhere(DatabaseUtils.concatenateWhere("serverType IN (1,2)", "(localGroupId=-1000)"), InternalContract.Cloud.ALIAS_ORIGIN_FILE_VALID), (String[]) null, (String) null, $$Lambda$AddAccount$xcYjnTPbkaMdIda091249JT6fW8.INSTANCE);
    }
}
