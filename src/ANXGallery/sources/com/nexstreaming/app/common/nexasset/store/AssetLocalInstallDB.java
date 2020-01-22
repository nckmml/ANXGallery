package com.nexstreaming.app.common.nexasset.store;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Process;
import android.util.Log;
import com.google.gson_nex.Gson;
import com.google.gson_nex.JsonIOException;
import com.google.gson_nex.JsonSyntaxException;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.InstallSourceType;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.store.json.AssetStoreAPIData;
import com.nexstreaming.app.common.task.Task;
import com.nexstreaming.app.common.util.b;
import com.nexstreaming.app.common.util.p;
import com.nexstreaming.kminternal.kinemaster.config.EditorGlobal;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AssetLocalInstallDB {
    public static final int ASSET_UNINSTALL_FINISHED = 1;
    public static final int ASSET_UNINSTALL_NOT_YET = 0;
    private static final String TAG = "AssetLocalInstallDB";
    /* access modifiers changed from: private */
    public static String assetStoreRootPath;
    private static final boolean commAS = false;
    private static AssetLocalInstallDB instance;
    private static String localFeaturedPath;
    private static String localRootPath;
    private static final Executor sInstallThreadPool = Executors.newSingleThreadExecutor();
    private static int supportedMimeType;
    /* access modifiers changed from: private */
    public boolean installing = false;
    private Context mContext = null;
    private Map<Integer, ArrayList<remoteAssetItem>> mFeaturedList = new HashMap();
    private Object m_assetdbLock = new Object();
    private List<String> readyToDeletePackages;
    private List<String> readyToInstallPackages;
    private List<String> readyToLoadPackages;

    public static final class InstallTaskError implements Task.TaskError {
        final Exception exception;
        private final String message;

        public InstallTaskError(String str) {
            this.message = str;
            this.exception = null;
        }

        public InstallTaskError(String str, Exception exc) {
            this.message = str;
            this.exception = exc;
        }

        public Exception getException() {
            return this.exception;
        }

        public String getLocalizedMessage(Context context) {
            return this.message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static class internalStoreAssetInfo implements StoreAssetInfo {
        private AssetStoreAPIData.AssetInfo info;

        public internalStoreAssetInfo(AssetStoreAPIData.AssetInfo assetInfo) {
            this.info = assetInfo;
        }

        public String getAssetDescription() {
            return this.info.description;
        }

        public Map<String, String> getAssetDescriptionMap() {
            return null;
        }

        public int getAssetFilesize() {
            return this.info.asset_filesize;
        }

        public String getAssetId() {
            return this.info.asset_id;
        }

        public int getAssetIndex() {
            return this.info.idx;
        }

        public Map<String, String> getAssetNameMap() {
            HashMap hashMap = new HashMap();
            if (this.info.assetName != null) {
                for (AssetStoreAPIData.LangString next : this.info.assetName) {
                    hashMap.put(next.language_code.toLowerCase(Locale.ENGLISH), next.string_title);
                }
            }
            return hashMap;
        }

        public String getAssetPackageDownloadURL() {
            return this.info.asset_filepath;
        }

        public int getAssetScopeVersion() {
            return this.info.asset_sversion;
        }

        public String getAssetThumbnailURL() {
            return this.info.thumbnail_path;
        }

        public String getAssetThumbnailURL_L() {
            return this.info.thumbnail_path_l;
        }

        public String getAssetThumbnailURL_S() {
            return this.info.thumbnail_path_s;
        }

        public String getAssetTitle() {
            return this.info.title;
        }

        public int getAssetVersion() {
            return this.info.asset_version;
        }

        public String getAssetVideoURL() {
            return this.info.videoclip_path;
        }

        public String getCategoryAliasName() {
            return this.info.category_aliasName;
        }

        public String getCategoryIconURL() {
            return this.info.categoryimagePath;
        }

        public int getCategoryIndex() {
            return this.info.category_idx;
        }

        public long getExpireTime() {
            return this.info.expire_time;
        }

        public String getPriceType() {
            return this.info.priceType;
        }

        public String getSubCategoryAliasName() {
            return this.info.category_aliasName;
        }

        public int getSubCategoryIndex() {
            return this.info.subcategory_idx;
        }

        public Map<String, String> getSubCategoryNameMap() {
            HashMap hashMap = new HashMap();
            if (this.info.subcategoryName != null) {
                for (AssetStoreAPIData.LangString next : this.info.subcategoryName) {
                    hashMap.put(next.language_code.toLowerCase(Locale.ENGLISH), next.string_title);
                }
            }
            return hashMap;
        }

        public List<String> getThumbnailPaths() {
            ArrayList arrayList = new ArrayList();
            if (this.info.thumb != null) {
                for (AssetStoreAPIData.ThumbInfo thumbInfo : this.info.thumb) {
                    arrayList.add(thumbInfo.file_path);
                }
            }
            return arrayList;
        }

        public int getUpdateTime() {
            return this.info.update_time;
        }
    }

    public static class remoteAssetItem {
        public String category;
        public String id;
        public int idx;
        public String name;
        public String thumbnailPath;
        public String thumbnailURL;
    }

    AssetLocalInstallDB(Context context) {
        if (assetStoreRootPath == null) {
            assetStoreRootPath = EditorGlobal.e().getAbsolutePath() + File.separator + ".nexassets" + File.separator + context.getPackageName();
        }
        if (localRootPath == null) {
            localRootPath = context.getFilesDir().getAbsolutePath() + File.separator + "assets";
            File file = new File(localRootPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        if (localFeaturedPath == null) {
            localFeaturedPath = context.getFilesDir().getAbsolutePath() + File.separator + "featured";
            File file2 = new File(localFeaturedPath);
            if (!file2.exists()) {
                file2.mkdirs();
            }
        }
        this.readyToInstallPackages = new ArrayList();
        this.readyToDeletePackages = new ArrayList();
        this.readyToLoadPackages = new ArrayList();
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public boolean checkInstallFile(String str, String str2) {
        boolean z;
        File file = new File(str, str2 + ".json");
        int assetIdxInJson = getAssetIdxInJson(str2);
        if (assetIdxInJson != Integer.parseInt(str2)) {
            Log.d(TAG, "invalid " + str2 + ".json. idx=" + assetIdxInJson);
            z = false;
        } else {
            z = true;
        }
        new File(str, "." + str2);
        File file2 = new File(str, str2 + ".jpg");
        if (!file2.isFile()) {
            Log.d(TAG, "thumbnail not found (" + str2 + ")");
            z = false;
        }
        if (!z) {
            if (file.isFile()) {
                file.delete();
            }
            if (file2.isFile()) {
                file2.delete();
            }
        }
        return z;
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private boolean copyFile(File file, String str) {
        if (file == null || !file.exists()) {
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c8, code lost:
        if (r2 != null) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d2, code lost:
        if (r2 != null) goto L_0x00ca;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00db A[SYNTHETIC, Splitter:B:44:0x00db] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x00c5=Splitter:B:31:0x00c5, B:37:0x00cf=Splitter:B:37:0x00cf} */
    public File copyThumbnail(String str) {
        if (isSamePath()) {
            File file = new File(assetStoreRootPath + File.separator + str + ".jpg");
            if (!file.isFile()) {
                Log.d(TAG, "copyThumbnail() file not found=" + file.getAbsolutePath());
            }
            return file;
        }
        File file2 = new File(assetStoreRootPath + File.separator + str + ".jpg");
        File file3 = new File(getThumbnailOutputPath(str));
        if (file2.isFile()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file2.getAbsolutePath(), options);
            options.inJustDecodeBounds = false;
            while (i < 8 && options.outWidth / i > 320 && options.outHeight / i > 180) {
                i *= 2;
            }
            options.inSampleSize = i;
            Bitmap decodeFile = BitmapFactory.decodeFile(file2.getAbsolutePath(), options);
            FileOutputStream fileOutputStream = null;
            try {
                file3.createNewFile();
                FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                try {
                    decodeFile.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                } catch (FileNotFoundException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    try {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                    }
                    throw th;
                }
                try {
                    fileOutputStream2.close();
                } catch (IOException unused2) {
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                e.printStackTrace();
            } catch (IOException e4) {
                e = e4;
                e.printStackTrace();
            }
            file2.delete();
            return file3;
        }
        createDummyIcon(str);
        return file3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0074, code lost:
        if (r1 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007e, code lost:
        if (r1 != null) goto L_0x0076;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0087 A[SYNTHETIC, Splitter:B:45:0x0087] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x0071=Splitter:B:32:0x0071, B:38:0x007b=Splitter:B:38:0x007b} */
    private boolean copyThumbnail(String str, String str2) {
        File file = new File(str);
        File file2 = new File(str2);
        if (file2.isFile()) {
            file2.delete();
        }
        if (!file.isFile()) {
            return false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        options.inJustDecodeBounds = false;
        int i = 1;
        while (i < 8 && options.outWidth / i > 320 && options.outHeight / i > 180) {
            i *= 2;
        }
        options.inSampleSize = i;
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (decodeFile == null) {
            file.delete();
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
            try {
                decodeFile.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
            } catch (FileNotFoundException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                e.printStackTrace();
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
            try {
                fileOutputStream2.close();
            } catch (IOException unused2) {
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            e.printStackTrace();
        } catch (IOException e4) {
            e = e4;
            e.printStackTrace();
        }
        file.delete();
        return true;
    }

    private void createDummy(String str) throws IOException {
        if (!isSamePath()) {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(assetStoreRootPath, str));
            fileOutputStream.write(new byte[]{80, 75});
            fileOutputStream.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0055 A[SYNTHETIC, Splitter:B:30:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x0042=Splitter:B:19:0x0042, B:25:0x004c=Splitter:B:25:0x004c} */
    private void createDummyIcon(String str) {
        File file = new File(getThumbnailOutputPath(str));
        int[] iArr = new int[576];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = -16777216;
        }
        FileOutputStream fileOutputStream = null;
        Bitmap createBitmap = Bitmap.createBitmap(iArr, 32, 18, Bitmap.Config.ARGB_8888);
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                createBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
            } catch (FileNotFoundException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                e.printStackTrace();
                if (fileOutputStream == null) {
                    return;
                }
                fileOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                    if (fileOutputStream == null) {
                        return;
                    }
                    fileOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
            try {
                fileOutputStream2.close();
            } catch (IOException unused2) {
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            e.printStackTrace();
            if (fileOutputStream == null) {
            }
            fileOutputStream.close();
        } catch (IOException e4) {
            e = e4;
            e.printStackTrace();
            if (fileOutputStream == null) {
            }
            fileOutputStream.close();
        }
    }

    private void deleteDir(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File deleteDir : listFiles) {
                deleteDir(deleteDir);
            }
        }
        file.delete();
    }

    private int getAssetIdxInJson(String str) {
        AssetStoreAPIData.AssetInfo assetInfo;
        FileInputStream fileInputStream;
        File file = new File(assetStoreRootPath, str + ".json");
        if (!file.isFile()) {
            return -1;
        }
        try {
            fileInputStream = new FileInputStream(file);
            assetInfo = (AssetStoreAPIData.AssetInfo) new Gson().fromJson((Reader) new InputStreamReader(fileInputStream), AssetStoreAPIData.AssetInfo.class);
            try {
                fileInputStream.close();
            } catch (FileNotFoundException | IOException unused) {
            }
        } catch (FileNotFoundException | IOException unused2) {
            assetInfo = null;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
        if (assetInfo == null) {
            return -1;
        }
        return assetInfo.idx;
    }

    public static String getAssetStoreRootPath() {
        return assetStoreRootPath;
    }

    private boolean getFeaturedList(String str, int i) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        String str2 = str;
        int i2 = i;
        File file = new File(str2);
        ArrayList arrayList = this.mFeaturedList.get(Integer.valueOf(i));
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.mFeaturedList.put(Integer.valueOf(i), arrayList);
        }
        boolean z = false;
        if (file.isDirectory()) {
            File file2 = new File(file.getAbsolutePath(), "" + i2 + ".json");
            if (file2.isFile()) {
                try {
                    fileInputStream2 = new FileInputStream(file2);
                    try {
                        AssetStoreAPIData.GetNewAssetList getNewAssetList = (AssetStoreAPIData.GetNewAssetList) new Gson().fromJson((Reader) new InputStreamReader(fileInputStream2), AssetStoreAPIData.GetNewAssetList.class);
                        if (getNewAssetList == null) {
                            try {
                                fileInputStream2.close();
                                return false;
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            } catch (JsonSyntaxException e3) {
                                Log.e(TAG, "getFeaturedList err!");
                                e3.printStackTrace();
                            } catch (JsonIOException e4) {
                                Log.e(TAG, "getFeaturedList err!");
                                e4.printStackTrace();
                            }
                        } else if (getNewAssetList.objList == null) {
                            fileInputStream2.close();
                            return false;
                        } else {
                            if (getNewAssetList.objList.size() > 0) {
                                arrayList.clear();
                                for (AssetStoreAPIData.AssetInfo next : getNewAssetList.objList) {
                                    if (!new File(str2, "" + next.idx).isFile()) {
                                        Log.d(TAG, "getFeaturedList() mode=" + i2 + ", idx=" + i2 + ", thumbnail not found!");
                                    } else {
                                        remoteAssetItem remoteassetitem = new remoteAssetItem();
                                        remoteassetitem.id = next.asset_id;
                                        remoteassetitem.idx = next.idx;
                                        remoteassetitem.name = next.assetName.get(0).string_title;
                                        String language = this.mContext.getResources().getConfiguration().locale.getLanguage();
                                        int i3 = 0;
                                        while (true) {
                                            if (i3 < next.assetName.size()) {
                                                if (next.assetName.get(i3).language_code.equals(language) && next.assetName.get(i3).string_title != null) {
                                                    remoteassetitem.name = next.assetName.get(i3).string_title;
                                                    break;
                                                }
                                                i3++;
                                            } else {
                                                break;
                                            }
                                        }
                                        remoteassetitem.thumbnailPath = str2 + File.separator + remoteassetitem.idx;
                                        remoteassetitem.thumbnailURL = next.thumbnail_path_s;
                                        if (next.category_aliasName != null) {
                                            remoteassetitem.category = next.category_aliasName;
                                        } else {
                                            remoteassetitem.category = "None";
                                        }
                                        arrayList.add(remoteassetitem);
                                    }
                                }
                                z = true;
                            }
                            fileInputStream2.close();
                            if (!z) {
                                try {
                                    fileInputStream = new FileInputStream(file2);
                                    try {
                                        byte[] bArr = new byte[((int) file2.length())];
                                        fileInputStream.read(bArr);
                                        Log.d(TAG, "ErrJson:" + String.valueOf(bArr));
                                        try {
                                            fileInputStream.close();
                                        } catch (FileNotFoundException e5) {
                                            e5.printStackTrace();
                                        } catch (IOException e6) {
                                            e6.printStackTrace();
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        fileInputStream.close();
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileInputStream = null;
                                    fileInputStream.close();
                                    throw th;
                                }
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream2.close();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileInputStream2 = null;
                    fileInputStream2.close();
                    throw th;
                }
            } else {
                Log.d(TAG, "file not found=" + file2.getAbsolutePath());
            }
        }
        return z;
    }

    public static String getInstalledAssetPath() {
        return localRootPath;
    }

    public static AssetLocalInstallDB getInstance(Context context) {
        if (instance == null) {
            instance = new AssetLocalInstallDB(context.getApplicationContext());
        }
        return instance;
    }

    private String getThumbnailOutputPath(String str) {
        String str2 = localRootPath;
        if (str2.startsWith(this.mContext.getFilesDir().getAbsolutePath())) {
            str2 = this.mContext.getFilesDir().getAbsolutePath() + File.separator + "thumb";
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2 + File.separator + str + ".jpg";
    }

    /* access modifiers changed from: private */
    public void installPackage(String str, String str2, File file, Task task, boolean z, String str3) throws IOException {
        Log.d(TAG, "installPackage() called with: item = [" + str + "], thumbFile = [" + file + "]");
        File file2 = new File(assetStoreRootPath, str);
        if (file2.exists()) {
            if (p.a(file2)) {
                z = false;
            }
            File file3 = new File(localRootPath, str2);
            if (z) {
                try {
                    if (str3.compareTo(AssetStoreClient.none) == 0) {
                        Log.d(TAG, "installPackage() fail. key not found.");
                        file2.delete();
                        String str4 = assetStoreRootPath;
                        File file4 = new File(str4, "." + str2);
                        if (file4.isFile()) {
                            file4.delete();
                            return;
                        }
                        return;
                    }
                } catch (IOException e) {
                    Log.w(TAG, "installPackage unzip error", e);
                    if (file3.exists()) {
                        file3.delete();
                    }
                    throw new IOException(e);
                }
            }
            try {
                unzip(file2, file3, task, z, str3);
                String str5 = assetStoreRootPath;
                File file5 = new File(str5, "." + str2);
                if (file5.isFile()) {
                    file5.delete();
                }
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
                if (file3.exists()) {
                    file3.delete();
                }
            } catch (NoSuchPaddingException e3) {
                e3.printStackTrace();
                if (file3.exists()) {
                    file3.delete();
                }
            } catch (InvalidKeyException e4) {
                e4.printStackTrace();
                if (file3.exists()) {
                    file3.delete();
                }
            }
            synchronized (this.m_assetdbLock) {
                StoreAssetInfo parseStoreAssetInfo = parseStoreAssetInfo(file3, str2);
                if (parseStoreAssetInfo != null) {
                    Log.d(TAG, "install StoreAssetItem, idx=" + parseStoreAssetInfo.getAssetIndex() + ", id=" + parseStoreAssetInfo.getAssetId() + ", SDKLevel=" + parseStoreAssetInfo.getAssetScopeVersion() + ", version=" + parseStoreAssetInfo.getAssetVersion());
                }
                c.a(this.mContext).a(file3, file, parseStoreAssetInfo);
                file2.delete();
            }
            return;
        }
        throw new FileNotFoundException("Not found asset file");
    }

    private boolean isSamePath() {
        return localRootPath.compareTo(assetStoreRootPath) == 0;
    }

    public static boolean isUpdatedFeaturedList(int i, String str) {
        File file = new File(localFeaturedPath + File.separator + "" + i + ".json");
        boolean z = true;
        if (file.isFile()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[((int) file.length())];
                fileInputStream.read(bArr);
                if (Arrays.equals(bArr, str.getBytes())) {
                    Log.d(TAG, "FeaturedList equals index=" + i);
                    z = false;
                }
                fileInputStream.close();
            } catch (FileNotFoundException unused) {
            } catch (IOException unused2) {
                Log.d(TAG, "FeaturedList IOException");
            }
        }
        return z;
    }

    private void moveFeaturedList(String str) {
        File file = new File(assetStoreRootPath + File.separator + str);
        if (file.isDirectory()) {
            for (String str2 : file.list()) {
                if (!str2.startsWith(".")) {
                    if (str2.endsWith(".json")) {
                        File file2 = new File(file.getAbsolutePath(), str2);
                        if (!copyFile(file2, localFeaturedPath + File.separator + str2)) {
                            Log.d(TAG, "copyFile fail!");
                        }
                        file2.delete();
                    } else {
                        copyThumbnail(file.getAbsolutePath() + File.separator + str2, localFeaturedPath + File.separator + str2);
                    }
                }
            }
        }
    }

    private StoreAssetInfo parseStoreAssetInfo(File file, String str) {
        AssetStoreAPIData.AssetInfo assetInfo;
        FileInputStream fileInputStream;
        File file2 = new File(assetStoreRootPath, str + ".json");
        if (file2.isFile()) {
            try {
                fileInputStream = new FileInputStream(file2);
                assetInfo = (AssetStoreAPIData.AssetInfo) new Gson().fromJson((Reader) new InputStreamReader(fileInputStream), AssetStoreAPIData.AssetInfo.class);
                try {
                    fileInputStream.close();
                } catch (FileNotFoundException | IOException unused) {
                }
            } catch (FileNotFoundException | IOException unused2) {
                assetInfo = null;
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
            file2.delete();
            return new internalStoreAssetInfo(assetInfo);
        }
        Log.d(TAG, "jsonFile file not found!");
        AssetStoreAPIData.AssetInfo assetInfo2 = new AssetStoreAPIData.AssetInfo();
        try {
            AssetPackageReader b = AssetPackageReader.b(file, str);
            if (b != null) {
                if (b.e() != null) {
                    assetInfo2.title = b.e().get("en");
                } else {
                    assetInfo2.title = str;
                }
                assetInfo2.idx = Integer.parseInt(str);
                assetInfo2.asset_id = str;
                b.close();
            } else {
                assetInfo2.idx = Integer.parseInt(str);
                assetInfo2.asset_id = str;
                assetInfo2.title = str;
            }
        } catch (IOException unused3) {
            assetInfo2.idx = Integer.parseInt(str);
            assetInfo2.asset_id = str;
            assetInfo2.title = str;
        }
        return new internalStoreAssetInfo(assetInfo2);
    }

    private static String readFromFile(File file) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    bufferedInputStream.close();
                    return sb.toString();
                }
            }
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e2) {
            return e2.getMessage();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003f */
    public static void saveFeaturedList(int i, String str) {
        FileOutputStream fileOutputStream;
        String str2 = localFeaturedPath;
        try {
            fileOutputStream = new FileOutputStream(str2 + File.separator + "" + i + ".json");
            fileOutputStream.write(str.getBytes());
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException ) {
            fileOutputStream.close();
        } catch (FileNotFoundException unused) {
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00bd A[SYNTHETIC, Splitter:B:35:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x00b4=Splitter:B:30:0x00b4, B:24:0x00aa=Splitter:B:24:0x00aa} */
    public static void saveFeaturedThumbnail(int i, Bitmap bitmap) {
        String str = localFeaturedPath;
        File file = new File(str, "" + i);
        if (file.isFile()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            if (options.outWidth == bitmap.getWidth() || options.outHeight == bitmap.getHeight()) {
                Log.d(TAG, "saveFeaturedThumbnail assetIdx=" + i + ", exists. lastModified=" + (System.currentTimeMillis() - file.lastModified()));
                return;
            }
            Log.d(TAG, "saveFeaturedThumbnail assetIdx=" + i + ", is not bmp . size=" + file.length());
            file.delete();
        }
        FileOutputStream fileOutputStream = null;
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                try {
                    fileOutputStream2.close();
                } catch (IOException unused) {
                }
            } catch (FileNotFoundException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                e.printStackTrace();
                if (fileOutputStream == null) {
                    return;
                }
                fileOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                    if (fileOutputStream == null) {
                        return;
                    }
                    fileOutputStream.close();
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                }
                throw th;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            e.printStackTrace();
            if (fileOutputStream == null) {
            }
            fileOutputStream.close();
        } catch (IOException e4) {
            e = e4;
            e.printStackTrace();
            if (fileOutputStream == null) {
            }
            fileOutputStream.close();
        }
    }

    public static void setAssetStoreRootPath(String str) {
        assetStoreRootPath = str;
    }

    public static void setInstalledAssetPath(String str) {
        localRootPath = str;
        File file = new File(localRootPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void setMimeType(int i) {
        supportedMimeType = i;
    }

    /* access modifiers changed from: private */
    public int uninstallPackage(int i, boolean z) throws Exception {
        Log.d(TAG, "uninstallPackage() called with: assetIdx = [" + i + "]");
        boolean z2 = true;
        if (i == 0 || i == 1) {
            return 1;
        }
        synchronized (this.m_assetdbLock) {
            f assetInstalledItemInfoByAssetIdx = getAssetInstalledItemInfoByAssetIdx(i);
            if (assetInstalledItemInfoByAssetIdx != null) {
                File file = new File(URI.create(assetInstalledItemInfoByAssetIdx.getPackageURI()).getPath());
                File file2 = new File(getThumbnailOutputPath("" + i));
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
                if (file2.isFile()) {
                    if (z) {
                        file2.delete();
                    } else if (!isSamePath()) {
                        file2.delete();
                    }
                }
                if (z) {
                    String str = assetStoreRootPath;
                    File file3 = new File(str, i + ".zip");
                    if (file3.isFile()) {
                        file3.delete();
                    }
                }
                if (file.exists()) {
                    z2 = file.delete();
                }
                c.a(this.mContext).a(i);
            }
            Log.d(TAG, "uninstallPackage() returned: " + (z2 ? 1 : 0));
        }
        return z2;
    }

    private static void unzip(File file, File file2, Task task, boolean z, String str) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        ZipInputStream zipInputStream;
        String str2;
        String str3;
        FileOutputStream fileOutputStream;
        File file3 = file;
        File file4 = file2;
        Task task2 = task;
        StringBuilder sb = new StringBuilder();
        sb.append("Unzipping '");
        sb.append(file3);
        String str4 = "' to '";
        sb.append(str4);
        sb.append(file4);
        String str5 = "'";
        sb.append(str5);
        Log.d(TAG, sb.toString());
        if (file2.mkdirs() || file2.exists()) {
            long length = file.length();
            task2.setProgress(0, 100);
            if (length > 0) {
                if (z) {
                    Cipher instance2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    instance2.init(2, new SecretKeySpec(str.getBytes(), "AES"));
                    zipInputStream = new ZipInputStream(new CipherInputStream(new FileInputStream(file3), instance2));
                } else {
                    zipInputStream = new ZipInputStream(new FileInputStream(file3));
                }
                long j = 0;
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream.getNextEntry();
                        if (nextEntry != null) {
                            String name = nextEntry.getName();
                            if (!name.contains("..")) {
                                File file5 = new File(file4, name);
                                if (nextEntry.isDirectory()) {
                                    if (!file5.mkdirs()) {
                                        if (!file5.exists()) {
                                            throw new IOException("Failed to create directory: " + file5);
                                        }
                                    }
                                    Log.d(TAG, "  - unzip: made folder '" + name + str5);
                                    str3 = str4;
                                    str2 = str5;
                                } else {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("  - unzip: unzipping file '");
                                    sb2.append(name);
                                    sb2.append("' ");
                                    str3 = str4;
                                    str2 = str5;
                                    sb2.append(nextEntry.getCompressedSize());
                                    sb2.append("->");
                                    sb2.append(nextEntry.getSize());
                                    sb2.append(" (");
                                    sb2.append(nextEntry.getMethod());
                                    sb2.append(")");
                                    Log.d(TAG, sb2.toString());
                                    fileOutputStream = new FileOutputStream(file5);
                                    copy(zipInputStream, fileOutputStream);
                                    b.a(fileOutputStream);
                                    j += nextEntry.getCompressedSize();
                                    int i = (int) ((100 * j) / length);
                                    if (i >= 100) {
                                        i = 99;
                                    }
                                    task2.setProgress(i, 100);
                                }
                                str4 = str3;
                                str5 = str2;
                            } else {
                                throw new IOException("Relative paths not allowed");
                            }
                        } else {
                            b.a(zipInputStream);
                            task2.setProgress(100, 100);
                            Log.d(TAG, "Unzipping DONE for: '" + file3 + str4 + file4 + str5);
                            return;
                        }
                    } catch (Throwable th) {
                        b.a(zipInputStream);
                        throw th;
                    }
                }
            } else {
                throw new IOException("Failed because file size is zero");
            }
        } else {
            throw new IOException("Failed to create directory: " + file4);
        }
    }

    public void checkInstallDB() {
        int i = 0;
        for (com.nexstreaming.app.common.nexasset.assetpackage.b installSourceType : c.a(this.mContext).b()) {
            if (installSourceType.getInstallSourceType() == InstallSourceType.STORE) {
                i++;
            }
        }
        if (i == 0) {
            Log.d(TAG, "StoreAsset NotFound!");
            String str = assetStoreRootPath;
            File file = new File(str);
            if (file.isDirectory()) {
                for (String str2 : file.list()) {
                    File file2 = new File(str, str2);
                    if (file2.isFile() && (!str2.endsWith(".zip") || file2.length() <= 2)) {
                        file2.delete();
                    }
                }
            }
        }
    }

    public void checkStore() {
        this.readyToDeletePackages.clear();
        this.readyToLoadPackages.clear();
        File file = new File(localRootPath);
        if (file.isDirectory()) {
            for (String str : file.list()) {
                File file2 = new File(localRootPath, str);
                if (file2.isDirectory()) {
                    if (!new File(assetStoreRootPath + File.separator + str + ".zip").isFile()) {
                        this.readyToDeletePackages.add(str);
                        try {
                            uninstallPackage(Integer.parseInt(str));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (file2.length() <= 2) {
                        this.readyToLoadPackages.add(str);
                    }
                }
            }
        }
    }

    public int checkStoreInstall() {
        this.readyToInstallPackages.clear();
        int i = supportedMimeType;
        String str = assetStoreRootPath;
        File file = new File(str);
        if (file.isDirectory()) {
            for (String str2 : file.list()) {
                File file2 = new File(str, str2);
                if (file2.isFile() && str2.endsWith(".zip") && file2.length() > 2) {
                    if (checkInstallFile(str, str2.substring(0, str2.length() - 4))) {
                        this.readyToInstallPackages.add(str2);
                    } else {
                        file2.delete();
                    }
                }
            }
        }
        return this.readyToInstallPackages.size();
    }

    public List<? extends com.nexstreaming.app.common.nexasset.assetpackage.b> getAssetInstalledDownloadItemItems() {
        ArrayList arrayList = new ArrayList();
        for (com.nexstreaming.app.common.nexasset.assetpackage.b bVar : c.a(this.mContext).b()) {
            if (bVar.getInstallSourceType() == InstallSourceType.STORE) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public f getAssetInstalledItemInfoByAssetIdx(int i) {
        Iterator<? extends f> it = c.a(this.mContext).c(i).iterator();
        if (it.hasNext()) {
            return (f) it.next();
        }
        return null;
    }

    public List<remoteAssetItem> getList(int i, String str) {
        getFeaturedList(localFeaturedPath, i);
        return this.mFeaturedList.get(Integer.valueOf(i));
    }

    public List<String> getReadyToInstallPackages() {
        checkStoreInstall();
        return this.readyToInstallPackages;
    }

    public String getThumbnailUrl(int i) {
        List<remoteAssetItem> list = this.mFeaturedList.get(1);
        if (list != null) {
            for (remoteAssetItem remoteassetitem : list) {
                if (remoteassetitem.idx == i) {
                    return remoteassetitem.thumbnailURL;
                }
            }
        }
        List<remoteAssetItem> list2 = this.mFeaturedList.get(2);
        if (list2 == null) {
            return null;
        }
        for (remoteAssetItem remoteassetitem2 : list2) {
            if (remoteassetitem2.idx == i) {
                return remoteassetitem2.thumbnailURL;
            }
        }
        return null;
    }

    public File getUnzipFolder(int i) {
        String str = localRootPath;
        return new File(str, "" + i);
    }

    public Task installPackageAsync(int i) {
        final Task task = new Task();
        this.installing = true;
        new AsyncTask<Integer, Void, Exception>() {
            /* access modifiers changed from: protected */
            public Exception doInBackground(Integer... numArr) {
                String[] strArr;
                String[] strArr2;
                Process.setThreadPriority(0);
                int intValue = numArr[0].intValue();
                if (intValue == 0) {
                    ArrayList arrayList = new ArrayList();
                    String access$000 = AssetLocalInstallDB.assetStoreRootPath;
                    File file = new File(access$000);
                    if (file.isDirectory()) {
                        for (String str : file.list()) {
                            File file2 = new File(access$000, str);
                            if (file2.isFile() && str.endsWith(".zip") && file2.length() > 2) {
                                if (AssetLocalInstallDB.this.checkInstallFile(access$000, str.substring(0, str.length() - 4))) {
                                    arrayList.add(str);
                                } else {
                                    file2.delete();
                                }
                            }
                        }
                    }
                    if (arrayList.size() == 0) {
                        Log.d(AssetLocalInstallDB.TAG, "download asset package not found.");
                        return new Resources.NotFoundException("download asset package not found");
                    }
                    strArr2 = new String[arrayList.size()];
                    strArr = new String[arrayList.size()];
                    for (int i = 0; i < arrayList.size(); i++) {
                        strArr2[i] = (String) arrayList.get(i);
                    }
                } else {
                    if (AssetLocalInstallDB.this.checkInstallFile(AssetLocalInstallDB.assetStoreRootPath, "" + intValue)) {
                        strArr = new String[1];
                        strArr2 = new String[]{"" + intValue + ".zip"};
                    } else {
                        Log.d(AssetLocalInstallDB.TAG, "download asset package not found. AssetIdx=" + intValue);
                        return new Resources.NotFoundException("download asset package not found");
                    }
                }
                int i2 = 0;
                while (i2 < strArr2.length) {
                    String str2 = strArr2[i2];
                    String substring = str2.substring(0, str2.length() - 4);
                    try {
                        int parseInt = Integer.parseInt(substring);
                        com.nexstreaming.app.common.nexasset.assetpackage.b b = c.a().b(parseInt);
                        if (b != null) {
                            Log.d(AssetLocalInstallDB.TAG, "already installed Asset=" + parseInt);
                            if (b.getInstallSourceType() == InstallSourceType.STORE) {
                                try {
                                    int unused = AssetLocalInstallDB.this.uninstallPackage(parseInt, false);
                                } catch (Exception unused2) {
                                }
                            } else {
                                Log.d(AssetLocalInstallDB.TAG, "installed Asset is not store type. idx=" + parseInt);
                            }
                        }
                    } catch (NumberFormatException unused3) {
                        Log.d(AssetLocalInstallDB.TAG, "baseId is not Integer baseId=" + substring);
                    }
                    File access$300 = AssetLocalInstallDB.this.copyThumbnail(substring);
                    int i3 = i2 + 1;
                    task.setProgress(i3, strArr2.length);
                    try {
                        AssetLocalInstallDB.this.installPackage(strArr2[i2], substring, access$300, task, false, strArr[i2]);
                        Log.i(AssetLocalInstallDB.TAG, "install asset completed : asset = [" + strArr2[i2] + "]");
                        i2 = i3;
                    } catch (FileNotFoundException e) {
                        Log.d(AssetLocalInstallDB.TAG, "install asset failed : asset = [" + strArr2[i2] + "]");
                        return e;
                    } catch (IOException e2) {
                        Log.d(AssetLocalInstallDB.TAG, "install asset failed : asset = [" + strArr2[i2] + "]");
                        new File(AssetLocalInstallDB.assetStoreRootPath, strArr2[i2]).delete();
                        return e2;
                    }
                }
                return null;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Exception exc) {
                super.onPostExecute(exc);
                boolean unused = AssetLocalInstallDB.this.installing = false;
                if (exc != null) {
                    task.sendFailure(new InstallTaskError("asset_install_failed", exc));
                    return;
                }
                task.signalEvent(Task.Event.COMPLETE);
            }

            /* access modifiers changed from: protected */
            public void onPreExecute() {
                super.onPreExecute();
            }
        }.executeOnExecutor(sInstallThreadPool, new Integer[]{Integer.valueOf(i)});
        return task;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0199  */
    public boolean installPackageSync(int i) {
        boolean z;
        FileInputStream fileInputStream;
        String str = assetStoreRootPath;
        String str2 = "" + i;
        if (this.installing) {
            Log.d(TAG, "installPackageSync(" + i + "): installing retry");
            return false;
        }
        this.installing = true;
        File file = new File(str);
        File file2 = new File(str, str2 + ".json");
        File file3 = new File(str, str2 + ".jpg");
        File file4 = new File(str, str2 + ".zip");
        AssetStoreAPIData.AssetInfo assetInfo = null;
        if (file.isDirectory()) {
            if (!file2.isFile()) {
                Log.d(TAG, "installPackageSync(" + i + "): info json not found!");
            } else {
                try {
                    fileInputStream = new FileInputStream(file2);
                    AssetStoreAPIData.AssetInfo assetInfo2 = (AssetStoreAPIData.AssetInfo) new Gson().fromJson((Reader) new InputStreamReader(fileInputStream), AssetStoreAPIData.AssetInfo.class);
                    try {
                        fileInputStream.close();
                        z = true;
                        assetInfo = assetInfo2;
                    } catch (FileNotFoundException unused) {
                        assetInfo = assetInfo2;
                    } catch (IOException unused2) {
                        assetInfo = assetInfo2;
                        Log.d(TAG, "installPackageSync(" + i + "): info json parse fail!");
                        z = false;
                        if (assetInfo != null) {
                        }
                        z = false;
                        if (!file3.isFile()) {
                        }
                        if (!file4.isFile()) {
                        }
                        z = false;
                        if (z) {
                        }
                    }
                } catch (FileNotFoundException unused3) {
                } catch (IOException unused4) {
                    Log.d(TAG, "installPackageSync(" + i + "): info json parse fail!");
                    z = false;
                    if (assetInfo != null) {
                    }
                    z = false;
                    if (!file3.isFile()) {
                    }
                    if (!file4.isFile()) {
                    }
                    z = false;
                    if (z) {
                    }
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
                if (assetInfo != null) {
                    Log.d(TAG, "installPackageSync(" + i + "): json parsing fail!");
                } else {
                    if (assetInfo.idx != i) {
                        Log.d(TAG, "installPackageSync(" + i + "): invalidation idx=" + assetInfo.idx);
                    }
                    if (!file3.isFile()) {
                        Log.d(TAG, "installPackageSync(" + i + "): thumbnail not found!");
                        z = false;
                    }
                    if (!file4.isFile()) {
                        Log.d(TAG, "installPackageSync(" + i + "): package not found!");
                    } else {
                        if (!p.a(file4)) {
                            Log.d(TAG, "installPackageSync(" + i + "): package is not zip!");
                        }
                        if (z) {
                            if (file2.isFile()) {
                                file2.delete();
                            }
                            if (file4.isFile()) {
                                file4.delete();
                            }
                            if (file3.isFile()) {
                                file3.delete();
                            }
                            this.installing = false;
                            return false;
                        }
                        File file5 = new File(localRootPath, str2);
                        try {
                            File file6 = new File(getThumbnailOutputPath(str2));
                            copyFile(file3, file6.getAbsolutePath());
                            p.a(file4, file5);
                            c.a(this.mContext).a(file5, file6, (StoreAssetInfo) new internalStoreAssetInfo(assetInfo));
                            file3.delete();
                            file2.delete();
                            file4.delete();
                            this.installing = false;
                            return true;
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(TAG, "installPackageSync(" + i + "): unzip fail=" + file5.getAbsolutePath());
                            this.installing = false;
                            return false;
                        }
                    }
                    z = false;
                    if (z) {
                    }
                }
                z = false;
                if (!file3.isFile()) {
                }
                if (!file4.isFile()) {
                }
                z = false;
                if (z) {
                }
            }
            z = false;
            if (assetInfo != null) {
            }
            z = false;
            if (!file3.isFile()) {
            }
            if (!file4.isFile()) {
            }
            z = false;
            if (z) {
            }
        } else {
            Log.d(TAG, "installPackageSync(" + i + "): store path not found=" + str);
            this.installing = false;
            return false;
        }
    }

    public boolean isInstallingPackages() {
        return this.installing;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        if (r5 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (r5 != null) goto L_0x003f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004d A[SYNTHETIC, Splitter:B:27:0x004d] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0044=Splitter:B:21:0x0044, B:15:0x003a=Splitter:B:15:0x003a} */
    public File makeThumbnail(Bitmap bitmap, int i) {
        File file = new File(getThumbnailOutputPath("" + i));
        FileOutputStream fileOutputStream = null;
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
            } catch (FileNotFoundException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                e.printStackTrace();
            } catch (IOException e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                }
                throw th;
            }
            try {
                fileOutputStream2.close();
            } catch (IOException unused2) {
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            e.printStackTrace();
        } catch (IOException e4) {
            e = e4;
            e.printStackTrace();
        }
        return file;
    }

    public void uninstallFromAssetStoreApp() {
        String str = assetStoreRootPath;
        File file = new File(str);
        if (file.isDirectory()) {
            for (String str2 : file.list()) {
                if (str2.endsWith(".del")) {
                    String substring = str2.substring(0, str2.length() - 4);
                    Log.d(TAG, "uninstallFromAssetStoreApp idx=" + substring);
                    try {
                        uninstallPackage(Integer.parseInt(substring));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new File(str, str2).delete();
                }
            }
        }
    }

    public int uninstallPackage(int i) throws Exception {
        return uninstallPackage(i, true);
    }
}
