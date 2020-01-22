package com.nexstreaming.nexeditorsdk.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.google.gson_nex.Gson;
import com.nexstreaming.app.common.nexasset.assetpackage.InstallSourceType;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.store.AssetLocalInstallDB;
import com.nexstreaming.app.common.nexasset.store.StoreAssetInfo;
import com.nexstreaming.app.common.nexasset.store.json.AssetStoreAPIData;
import com.nexstreaming.app.common.util.b;
import com.nexstreaming.app.common.util.l;
import com.nexstreaming.app.common.util.n;
import com.nexstreaming.nexeditorsdk.nexAssetPackageManager;
import com.nexstreaming.nexeditorsdk.service.INexAssetService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class nexAssetService extends Service {
    public static final String ACTION_ASSET_FEATUREDLIST_COMPLETED = "com.nexstreaming.nexeditorsdk.asset.feathredlist.completed";
    public static final String ACTION_ASSET_INSTALL_COMPLETED = "com.nexstreaming.nexeditorsdk.asset.install.completed";
    public static final String ACTION_ASSET_UNINSTALL_COMPLETED = "com.nexstreaming.nexeditorsdk.asset.uninstall.completed";
    private static final String ACTION_BIND = "com.nexstreaming.nexeditorsdk.service.bind";
    private static final String TAG = "nexAssetService";
    private static final int TYPE_BITMAP_DATA = 1;
    private static final int TYPE_JSON_DATA = 0;
    private static final ExecutorService sInstallThreadExcutor = Executors.newSingleThreadExecutor();
    private AsyncTask<Context, Void, Void> checkReceivedFeaturedListAsyncTask;
    /* access modifiers changed from: private */
    public boolean isRunningAsyncTask;
    /* access modifiers changed from: private */
    public volatile boolean isUpdatedFeaturedList;
    /* access modifiers changed from: private */
    public volatile long lastReceivedFeaturedListTime;
    private AssetStoreAPIData.AssetInfo mCurrentAssetInfo;
    private Bitmap mCurrentThumbnail;
    private FileOutputStream mFileOutputStream;
    private long mReceivedDataSize = 0;
    private INexAssetService.Stub nexAssetService = new INexAssetService.Stub() {
        public void connectInstaller(int i, String str, String str2, INexAssetConnectionCallback iNexAssetConnectionCallback) throws RemoteException {
            nexAssetService.this.onConnectionInstaller(i, str, str2, iNexAssetConnectionCallback);
        }

        public void loadInstalledAssetList(INexAssetDataCallback iNexAssetDataCallback) throws RemoteException {
            nexAssetService.this.onLoadInstalledAssetList(iNexAssetDataCallback);
        }

        public void saveAssetInfoData(int i, int i2, String str) throws RemoteException {
            nexAssetService.this.onReceivedAssetInfoData(i, i2, str);
        }

        public void sendAssetData(int i, String str, int i2, long j, INexAssetInstallCallback iNexAssetInstallCallback) throws RemoteException {
            nexAssetService.this.onReceivedAssetData(i, str, i2, j, iNexAssetInstallCallback);
        }

        public void uninstallAsset(int i, INexAssetUninstallCallback iNexAssetUninstallCallback) throws RemoteException {
            nexAssetService.this.onUninstallAsset(i, iNexAssetUninstallCallback);
        }
    };

    private static final class a extends AsyncTask<Void, Void, Boolean> {
        private Context a;
        private String b;
        private Bitmap c;
        private AssetStoreAPIData.AssetInfo d;
        private INexAssetInstallCallback e;
        private long f = 0;
        private l g = new l();

        public a(Context context, String str, Bitmap bitmap, AssetStoreAPIData.AssetInfo assetInfo, INexAssetInstallCallback iNexAssetInstallCallback) {
            this.g.c();
            this.g.a();
            Log.d(nexAssetService.TAG, "InstallTask() called with: context = [" + context + "], assetPath = [" + str + "], bitmap = [" + bitmap + "], assetInfo = [" + assetInfo + "], callback = [" + iNexAssetInstallCallback + "]");
            this.a = context;
            this.b = str;
            this.c = bitmap;
            this.d = assetInfo;
            this.e = iNexAssetInstallCallback;
        }

        private void a(File file, File file2, int i) throws IOException {
            Log.d(nexAssetService.TAG, "Unzipping '" + file + "' to '" + file2 + "'");
            if (file2.mkdirs() || file2.exists()) {
                long length = file.length();
                long j = 0;
                FileOutputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry != null) {
                            String name = nextEntry.getName();
                            if (!name.contains("..")) {
                                File file3 = new File(file2, name);
                                if (!nextEntry.isDirectory()) {
                                    zipInputStream = new FileOutputStream(file3);
                                    AssetLocalInstallDB.copy(zipInputStream, zipInputStream);
                                    b.a(zipInputStream);
                                    j += nextEntry.getCompressedSize();
                                    int i2 = (int) ((((double) j) * 100.0d) / ((double) length));
                                    if (i2 >= 100) {
                                        i2 = 99;
                                    }
                                    a(false, i2);
                                } else if (file3.mkdirs()) {
                                    continue;
                                } else if (!file3.exists()) {
                                    throw new IOException("Failed to create directory: " + file3);
                                }
                            } else {
                                throw new IOException("Relative paths not allowed: " + name);
                            }
                        } else {
                            b.a(zipInputStream);
                            Log.d(nexAssetService.TAG, "Unzipping DONE for: '" + file + "' to '" + file2 + "'");
                            return;
                        }
                    } catch (Throwable th) {
                        throw th;
                    } finally {
                    }
                }
            } else {
                throw new IOException("Failed to create directory: " + file2);
            }
        }

        private void a(String str) {
            if (str != null) {
                try {
                    this.e.onInstallFailed(this.d.idx, str);
                    Log.d(nexAssetService.TAG, "sendCompleted install asset(" + this.d.idx + ") error=" + str);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            } else {
                this.e.onInstallCompleted(this.d.idx);
                Log.d(nexAssetService.TAG, "sendCompleted install asset(" + this.d.idx + ") installed");
            }
        }

        private void a(boolean z, int i) {
            if (!z && System.currentTimeMillis() - this.f > 1000) {
                z = true;
            }
            if (z) {
                this.f = System.currentTimeMillis();
                Log.d(nexAssetService.TAG, "sendProgress() asset(" + this.d.idx + ") progress=" + i);
                try {
                    this.e.onProgressInstall(this.d.idx, i, 100);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            Log.d(nexAssetService.TAG, "installAsset() assetPath = [" + this.b + "]");
            a(true, 0);
            com.nexstreaming.app.common.nexasset.assetpackage.b b2 = c.a(this.a).b(this.d.idx);
            if (b2 != null) {
                Log.d(nexAssetService.TAG, "already installed Asset=" + this.d.idx);
                if (b2.getInstallSourceType() == InstallSourceType.STORE) {
                    try {
                        AssetLocalInstallDB.getInstance(this.a).uninstallPackage(this.d.idx);
                    } catch (Exception e2) {
                        String message = e2.getMessage();
                        if (message == null) {
                            message = "uninstall fail asset=" + this.d.idx;
                        }
                        new File(this.b).delete();
                        a(message);
                        return false;
                    }
                } else {
                    Log.d(nexAssetService.TAG, "installed Asset is not store type. idx=" + this.d.idx);
                    new File(this.b).delete();
                    a("installed Asset is not store type. idx=" + this.d.idx);
                    return false;
                }
            }
            try {
                File file = new File(this.b);
                File unzipFolder = AssetLocalInstallDB.getInstance(this.a).getUnzipFolder(this.d.idx);
                a(file, unzipFolder, this.d.idx);
                file.delete();
                File makeThumbnail = this.c != null ? AssetLocalInstallDB.getInstance(this.a).makeThumbnail(this.c, this.d.idx) : null;
                Log.d(nexAssetService.TAG, "installed Asset idx=" + this.d.idx + ", sdklevel=" + this.d.asset_sversion + ", version=" + this.d.asset_version);
                c.a(this.a).a(unzipFolder, makeThumbnail, (StoreAssetInfo) new AssetLocalInstallDB.internalStoreAssetInfo(this.d));
                a((String) null);
                return true;
            } catch (IOException e3) {
                e3.printStackTrace();
                String message2 = e3.getMessage();
                if (message2 == null) {
                    message2 = "unzip or db update fail! asset=" + this.d.idx;
                }
                a(message2);
                return false;
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            if (bool.booleanValue()) {
                Intent intent = new Intent(nexAssetService.ACTION_ASSET_INSTALL_COMPLETED);
                intent.putExtra("index", this.d.idx);
                intent.putExtra("category.alias", this.d.category_aliasName);
                this.a.sendBroadcast(intent);
                this.g.b();
                Log.d(nexAssetService.TAG, "install asset(" + this.d.idx + ") time elapsed = " + this.g.toString());
            }
        }
    }

    private void installAsset(String str, Bitmap bitmap, AssetStoreAPIData.AssetInfo assetInfo, INexAssetInstallCallback iNexAssetInstallCallback) {
        if (str == null || bitmap == null || bitmap.isRecycled() || assetInfo == null || iNexAssetInstallCallback == null) {
            Log.d(TAG, "installAsset fail!=" + str);
            if (iNexAssetInstallCallback != null) {
                try {
                    iNexAssetInstallCallback.onInstallFailed(assetInfo.idx, "component not found!");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        } else {
            new a(this, str, bitmap, assetInfo, iNexAssetInstallCallback).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onConnectionInstaller(int i, String str, String str2, INexAssetConnectionCallback iNexAssetConnectionCallback) {
        if (str != null && str2 != null && iNexAssetConnectionCallback != null) {
            byte[] decode = Base64.decode(str, 0);
            this.mCurrentThumbnail = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            Log.d(TAG, "onConnectionInstaller: make bitmap completed " + this.mCurrentThumbnail);
            this.mCurrentAssetInfo = (AssetStoreAPIData.AssetInfo) new Gson().fromJson(str2, AssetStoreAPIData.AssetInfo.class);
            Log.d(TAG, "onConnectionInstaller: convert Asset completed " + this.mCurrentAssetInfo);
            try {
                iNexAssetConnectionCallback.onConnectionCompleted(i);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onLoadInstalledAssetList(INexAssetDataCallback iNexAssetDataCallback) {
        String str;
        nexAssetService nexassetservice = this;
        INexAssetDataCallback iNexAssetDataCallback2 = iNexAssetDataCallback;
        if (iNexAssetDataCallback2 != null) {
            Log.d(TAG, "internalLoadInstallAssetList() called");
            List<? extends com.nexstreaming.app.common.nexasset.assetpackage.b> assetInstalledDownloadItemItems = AssetLocalInstallDB.getInstance(this).getAssetInstalledDownloadItemItems();
            if (assetInstalledDownloadItemItems == null || assetInstalledDownloadItemItems.size() <= 0) {
                try {
                    iNexAssetDataCallback2.onLoadAssetDatas((String) null);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            } else {
                ArrayList arrayList = new ArrayList();
                for (com.nexstreaming.app.common.nexasset.assetpackage.b bVar : assetInstalledDownloadItemItems) {
                    if (c.a((Context) this).a(bVar)) {
                        Log.d(TAG, "expire Asset Idx=" + bVar.getAssetIdx());
                    } else {
                        int assetIdx = bVar.getAssetIdx();
                        String assetId = bVar.getAssetId();
                        String str2 = "";
                        String a2 = bVar.getAssetName() != null ? n.a((Context) nexassetservice, bVar.getAssetName()) : str2;
                        if (bVar.getAssetCategory() != null) {
                            str2 = bVar.getAssetCategory().getCategoryAlias();
                        }
                        if (bVar.getAssetSubCategory() != null) {
                            String subCategoryAlias = bVar.getAssetSubCategory().getSubCategoryAlias();
                            if (bVar.getAssetSubCategory().getSubCategoryName() != null) {
                                subCategoryAlias = n.a((Context) nexassetservice, bVar.getAssetSubCategory().getSubCategoryName());
                            }
                            str = subCategoryAlias;
                        } else {
                            str = str2;
                        }
                        NexInstalledAssetItem nexInstalledAssetItem = r6;
                        NexInstalledAssetItem nexInstalledAssetItem2 = new NexInstalledAssetItem(assetIdx, assetId, a2, str, bVar.getThumbUrl(), bVar.getInstalledTime(), bVar.getExpireTime(), bVar.getMinVersion(), bVar.getPackageVersion());
                        arrayList.add(nexInstalledAssetItem);
                        nexassetservice = this;
                    }
                }
                try {
                    iNexAssetDataCallback2.onLoadAssetDatas(new Gson().toJson((Object) arrayList));
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onReceivedAssetData(int i, String str, int i2, long j, INexAssetInstallCallback iNexAssetInstallCallback) {
        String str2 = getFilesDir() + File.separator + i + ".zip";
        this.mReceivedDataSize += (long) i2;
        byte[] decode = Base64.decode(str, 0);
        if (this.mFileOutputStream == null) {
            try {
                this.mFileOutputStream = new FileOutputStream(new File(str2));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            this.mFileOutputStream.write(decode, 0, i2);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (this.mReceivedDataSize == j) {
            Log.d(TAG, "onReceivedAssetData: Received Completed size " + this.mReceivedDataSize);
            try {
                Log.d(TAG, "onReceivedAssetData: try close stream ");
                this.mFileOutputStream.close();
            } catch (IOException e3) {
                Log.d(TAG, "onReceivedAssetData: fileOutputStream close error", e3);
            }
            installAsset(str2, this.mCurrentThumbnail, this.mCurrentAssetInfo, iNexAssetInstallCallback);
            this.mFileOutputStream = null;
            this.mCurrentAssetInfo = null;
            this.mCurrentThumbnail = null;
            this.mReceivedDataSize = 0;
        }
    }

    /* access modifiers changed from: private */
    public void onReceivedAssetInfoData(int i, int i2, String str) {
        this.lastReceivedFeaturedListTime = System.currentTimeMillis();
        if (str == null) {
            return;
        }
        if (i == 0) {
            if (!this.isRunningAsyncTask) {
                this.isRunningAsyncTask = true;
                this.isUpdatedFeaturedList = false;
                this.checkReceivedFeaturedListAsyncTask = new AsyncTask<Context, Void, Void>() {
                    private Context b;

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Void doInBackground(Context... contextArr) {
                        this.b = contextArr[0];
                        while (System.currentTimeMillis() - nexAssetService.this.lastReceivedFeaturedListTime < 1000) {
                            Log.d(nexAssetService.TAG, "onReceivedAssetInfoData() wait=" + nexAssetService.this.lastReceivedFeaturedListTime);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Void voidR) {
                        super.onPostExecute(voidR);
                        Intent intent = new Intent(nexAssetService.ACTION_ASSET_FEATUREDLIST_COMPLETED);
                        intent.putExtra("update", nexAssetService.this.isUpdatedFeaturedList);
                        this.b.sendBroadcast(intent);
                        boolean unused = nexAssetService.this.isUpdatedFeaturedList = false;
                        boolean unused2 = nexAssetService.this.isRunningAsyncTask = false;
                        Log.d(nexAssetService.TAG, "onReceivedAssetInfoData() End..");
                    }

                    /* access modifiers changed from: protected */
                    public void onPreExecute() {
                        super.onPreExecute();
                        Log.d(nexAssetService.TAG, "onReceivedAssetInfoData() Start..");
                    }
                };
                this.checkReceivedFeaturedListAsyncTask.executeOnExecutor(sInstallThreadExcutor, new Context[]{this, null, null});
            }
            if (!this.isUpdatedFeaturedList) {
                this.isUpdatedFeaturedList = AssetLocalInstallDB.isUpdatedFeaturedList(i2, str);
            }
            AssetLocalInstallDB.saveFeaturedList(i2, str);
            Log.d(TAG, "onReceivedAssetInfoData: featuredAsset =" + i2);
        } else if (i == 1) {
            byte[] decode = Base64.decode(str.getBytes(), 0);
            AssetLocalInstallDB.saveFeaturedThumbnail(i2, BitmapFactory.decodeByteArray(decode, 0, decode.length));
            Log.d(TAG, "onReceivedAssetInfoData: bitmap index : " + i2);
        }
    }

    /* access modifiers changed from: private */
    public void onUninstallAsset(int i, INexAssetUninstallCallback iNexAssetUninstallCallback) {
        nexAssetPackageManager.getAssetPackageManager(getApplicationContext()).putUninstallItem(i);
        try {
            AssetLocalInstallDB.getInstance(getApplicationContext()).uninstallPackage(i);
            iNexAssetUninstallCallback.onUninstallCompleted(i);
        } catch (Exception e) {
            Log.w(TAG, "onUninstallAsset: error ", e);
            try {
                iNexAssetUninstallCallback.onUninstallFailed(i, e.getMessage());
            } catch (RemoteException e2) {
                Log.w(TAG, "onUninstallAsset: ", e2);
            }
        }
        Intent intent = new Intent(ACTION_ASSET_UNINSTALL_COMPLETED);
        intent.putExtra("index", i);
        sendBroadcast(intent);
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called with: intent = [" + intent + "]");
        if (intent == null || intent.getAction() == null || !intent.getAction().equals(ACTION_BIND)) {
            return null;
        }
        if (intent.getPackage() != null) {
            Intent intent2 = new Intent();
            intent2.setPackage(getPackageName());
            intent2.setAction("com.nexstreaming.app.assetstore.sdk.service.bind");
            String str = intent.getPackage();
            intent2.setClassName(str, intent.getPackage() + ".AssetStoreService");
            Log.d(TAG, "onBind: " + intent2);
            startService(intent2);
        }
        return this.nexAssetService;
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        Log.d(TAG, "onTaskRemoved() called");
    }
}
