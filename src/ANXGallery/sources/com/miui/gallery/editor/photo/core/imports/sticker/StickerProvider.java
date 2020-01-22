package com.miui.gallery.editor.photo.core.imports.sticker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.LongSparseArray;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.RenderEngine;
import com.miui.gallery.editor.photo.core.SdkManager;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.common.model.StickerCategory;
import com.miui.gallery.editor.photo.core.common.model.StickerData;
import com.miui.gallery.editor.photo.core.common.provider.AbstractStickerProvider;
import com.miui.gallery.editor.photo.core.imports.obsoletes.ZipUtils;
import com.miui.gallery.util.IoUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.settings.Settings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class StickerProvider extends AbstractStickerProvider {
    static final StickerProvider INSTANCE = new StickerProvider();
    private Map<StickerItem, WeakReference<Bitmap>> mCache = new ArrayMap();
    /* access modifiers changed from: private */
    public LongSparseArray<CategoryData> mCategories = new LongSparseArray<>();
    private Deque<StickerItem> mHistory = new LinkedList();
    private File mIndexFile;
    private File mRecentFile;

    private static class InitializeException extends Exception {
        InitializeException(String str) {
            super(str);
        }

        InitializeException(String str, Throwable th) {
            super(str, th);
        }
    }

    private class InstallTask extends AsyncTask<Void, Void, Boolean> {
        private InstallTask() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(StickerProvider.this.doInstall());
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            Log.d("StickerProvider", "stickers installed: %d categories", (Object) Integer.valueOf(StickerProvider.this.mCategories.size()));
            StickerProvider.this.notifyInitializeFinish();
        }
    }

    static {
        SdkManager.INSTANCE.register(INSTANCE);
    }

    protected StickerProvider() {
        super(Effect.STICKER);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016  */
    private boolean checkResourceVersion(JSONObject jSONObject) {
        boolean z;
        try {
            if (jSONObject.getInt("version") == 4) {
                z = true;
                if (!z) {
                    File file = new File(getApplicationContext().getFilesDir(), "photo_editor/stickers/");
                    if (file.exists()) {
                        MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, file);
                    }
                }
                return z;
            }
        } catch (JSONException e) {
            Log.w("StickerProvider", (Throwable) e);
        }
        z = false;
        if (!z) {
        }
        return z;
    }

    private void deleteIndexFile() {
        if (!this.mIndexFile.exists()) {
            Log.d("StickerProvider", "index file not found");
        } else if (!this.mIndexFile.delete()) {
            Log.d("StickerProvider", "delete index file failed");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:33:0x0098=Splitter:B:33:0x0098, B:41:0x00ae=Splitter:B:41:0x00ae} */
    public boolean doInstall() {
        JSONObject jSONObject;
        boolean z;
        File file = null;
        try {
            if (this.mIndexFile.exists()) {
                jSONObject = new JSONObject(loadPackageInfo());
                z = !checkResourceVersion(jSONObject);
            } else {
                Log.d("StickerProvider", "index file not exist");
                jSONObject = null;
                z = true;
            }
            if (z) {
                File file2 = new File(getApplicationContext().getCacheDir(), "stickers.zip");
                try {
                    releaseZip(file2);
                    unzipData(file2);
                    if (this.mRecentFile.exists() && !this.mRecentFile.delete()) {
                        Log.d("StickerProvider", "delete recent stickers failed");
                    }
                    jSONObject = new JSONObject(loadPackageInfo());
                    file = file2;
                } catch (InitializeException e) {
                    File file3 = file2;
                    e = e;
                    file = file3;
                    Log.w("StickerProvider", (Throwable) e);
                    deleteIndexFile();
                    if (file == null || !file.exists() || file.delete()) {
                        return false;
                    }
                    Log.w("StickerProvider", "delete zip file failed");
                    return false;
                } catch (JSONException e2) {
                    File file4 = file2;
                    e = e2;
                    file = file4;
                    try {
                        Log.w("StickerProvider", (Throwable) e);
                        deleteIndexFile();
                        if (file == null || !file.exists() || file.delete()) {
                            return false;
                        }
                        Log.w("StickerProvider", "delete zip file failed");
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        if (file != null && file.exists() && !file.delete()) {
                            Log.w("StickerProvider", "delete zip file failed");
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    File file5 = file2;
                    th = th2;
                    file = file5;
                    Log.w("StickerProvider", "delete zip file failed");
                    throw th;
                }
            }
            long currentTimeMillis = System.currentTimeMillis();
            loadData(jSONObject);
            readRecentFromFile();
            Log.d("StickerProvider", "load package info costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            if (file != null && file.exists() && !file.delete()) {
                Log.w("StickerProvider", "delete zip file failed");
            }
            return true;
        } catch (InitializeException e3) {
            e = e3;
            Log.w("StickerProvider", (Throwable) e);
            deleteIndexFile();
            return false;
        } catch (JSONException e4) {
            e = e4;
            Log.w("StickerProvider", (Throwable) e);
            deleteIndexFile();
            return false;
        }
    }

    private void fillRecent(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                this.mHistory.add(StickerItem.fromJson(jSONArray.getJSONObject(i)));
            } catch (JSONException e) {
                Log.w("StickerProvider", (Throwable) e);
            }
        }
        Log.d("StickerProvider", "read %d recent items from file", (Object) Integer.valueOf(this.mHistory.size()));
    }

    private void loadData(JSONObject jSONObject) throws InitializeException {
        String str;
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("categories");
            String region = Settings.getRegion();
            int i = 8;
            Resources resources = getApplicationContext().getResources();
            int i2 = 0;
            while (i2 < jSONArray.length()) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String optString = jSONObject2.optString("region");
                if (TextUtils.isEmpty(optString) || optString.equalsIgnoreCase(region)) {
                    String string = jSONObject2.getString("name");
                    if (string.startsWith("@string/")) {
                        String substring = string.substring(i);
                        int i3 = jSONObject2.getInt("id");
                        if (resources.getIdentifier(substring, "string", "com.miui.gallery") == 0) {
                            Log.e("StickerProvider", "not valid category name string resource:" + substring);
                        } else {
                            JSONArray jSONArray2 = jSONObject2.getJSONArray("items");
                            ArrayList arrayList = new ArrayList();
                            for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                                JSONObject jSONObject3 = jSONArray2.getJSONObject(i4);
                                int i5 = jSONObject3.getInt("id");
                                String string2 = jSONObject3.getString("main");
                                if (Uri.parse(string2).isRelative()) {
                                    File file = new File(this.mIndexFile.getParentFile(), string2);
                                    if (file.exists()) {
                                        str = file.getPath();
                                    } else {
                                        throw new InitializeException(String.format("main url is not found %s", new Object[]{string2}));
                                    }
                                } else {
                                    str = string2;
                                }
                                arrayList.add(new StickerItem(0, (String) null, (long) i5, str, str, substring));
                            }
                            this.mCategories.put((long) i3, new CategoryData(i3, -1, substring, arrayList));
                        }
                    } else {
                        Log.e("StickerProvider", "not string type category name: " + string);
                    }
                }
                i2++;
                i = 8;
            }
        } catch (JSONException e) {
            throw new InitializeException("package info missing", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x004d A[SYNTHETIC, Splitter:B:30:0x004d] */
    private String loadPackageInfo() throws InitializeException {
        StringBuilder sb = new StringBuilder();
        FileReader fileReader = null;
        try {
            FileReader fileReader2 = new FileReader(this.mIndexFile);
            try {
                char[] cArr = new char[1024];
                while (fileReader2.read(cArr) != -1) {
                    sb.append(cArr);
                }
                String sb2 = sb.toString();
                Log.d("StickerProvider", "load pkg info finished");
                try {
                    fileReader2.close();
                } catch (IOException e) {
                    Log.w("StickerProvider", (Throwable) e);
                }
                return sb2;
            } catch (FileNotFoundException unused) {
                FileReader fileReader3 = fileReader2;
                throw new InitializeException("index file is not found");
            } catch (IOException e2) {
                e = e2;
                fileReader = fileReader2;
                try {
                    throw new InitializeException("read index file failed", e);
                } catch (Throwable th) {
                    th = th;
                    if (fileReader != null) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileReader = fileReader2;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e3) {
                        Log.w("StickerProvider", (Throwable) e3);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused2) {
            throw new InitializeException("index file is not found");
        } catch (IOException e4) {
            e = e4;
            throw new InitializeException("read index file failed", e);
        }
    }

    private void readRecentFromFile() {
        Reader openReader = IoUtils.openReader(this.mRecentFile);
        if (openReader != null) {
            try {
                char[] cArr = new char[128];
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int read = openReader.read(cArr);
                    if (read == -1) {
                        break;
                    }
                    sb.append(cArr, 0, read);
                }
                fillRecent(new JSONArray(sb.toString()));
            } catch (IOException e) {
                Log.w("StickerProvider", (Throwable) e);
            } catch (JSONException e2) {
                Log.w("StickerProvider", (Throwable) e2);
            } catch (Throwable th) {
                IoUtils.close("StickerProvider", openReader);
                throw th;
            }
            IoUtils.close("StickerProvider", openReader);
        }
    }

    private void releaseZip(File file) throws InitializeException {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            inputStream = getApplicationContext().getAssets().open("stickers.zip");
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                throw new InitializeException("release assets's zip file failed", e);
            } catch (Throwable th) {
                th = th;
                IoUtils.close("StickerProvider", inputStream);
                IoUtils.close("StickerProvider", fileOutputStream2);
                throw th;
            }
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        IoUtils.close("StickerProvider", inputStream);
                        IoUtils.close("StickerProvider", fileOutputStream);
                        return;
                    }
                }
            } catch (IOException e2) {
                throw new InitializeException("release failed during release zip file", e2);
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                IoUtils.close("StickerProvider", inputStream);
                IoUtils.close("StickerProvider", fileOutputStream2);
                throw th;
            }
        } catch (IOException e3) {
            throw new InitializeException("missing assets file", e3);
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            IoUtils.close("StickerProvider", inputStream);
            IoUtils.close("StickerProvider", fileOutputStream2);
            throw th;
        }
    }

    private void unzipData(File file) throws InitializeException {
        long currentTimeMillis = System.currentTimeMillis();
        if (file.exists()) {
            File file2 = new File(getApplicationContext().getFilesDir(), "photo_editor/stickers/");
            if (file2.exists()) {
                MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, file2);
            }
            if (file2.exists()) {
                Log.w("StickerProvider", "delete folder failed");
            } else if (!file2.mkdirs()) {
                throw new InitializeException("create folder stickers/ failed");
            }
            try {
                ZipUtils.unzip(file, file2);
                Log.d("StickerProvider", "unzip files finish, costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            } catch (IOException e) {
                throw new InitializeException("copy directory failed", e);
            }
        } else {
            throw new InitializeException("resource missing");
        }
    }

    public RenderEngine createEngine(Context context) {
        return new StickerEngine();
    }

    /* access modifiers changed from: package-private */
    public Bitmap fromCache(StickerItem stickerItem) {
        Reference reference = this.mCache.get(stickerItem);
        if (reference == null) {
            return null;
        }
        return (Bitmap) reference.get();
    }

    public void install() {
        new InstallTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public List<StickerCategory> list() {
        ArrayList arrayList = new ArrayList(this.mCategories.size());
        for (int i = 0; i < this.mCategories.size(); i++) {
            arrayList.add(this.mCategories.valueAt(i));
        }
        return arrayList;
    }

    public List<StickerData> list(long j) {
        return this.mCategories.get(j).stickerList;
    }

    /* access modifiers changed from: protected */
    public void onActivityCreate() {
        super.onActivityCreate();
        Log.d("StickerProvider", "perform application creating, install stickers");
        this.mIndexFile = new File(getApplicationContext().getFilesDir(), "photo_editor/stickers/package.json");
        this.mRecentFile = new File(getApplicationContext().getFilesDir(), "photo_editor/stickers/recent.json");
        install();
    }

    public AbstractEffectFragment onCreateFragment() {
        return new StickerFragment();
    }

    /* access modifiers changed from: package-private */
    public void putToCache(StickerItem stickerItem, Bitmap bitmap) {
        this.mCache.put(stickerItem, new WeakReference(bitmap));
    }

    public List<StickerData> recent() {
        return new ArrayList(this.mHistory);
    }

    /* access modifiers changed from: package-private */
    public void touch(StickerItem stickerItem) {
        if (this.mHistory.contains(stickerItem)) {
            this.mHistory.remove(stickerItem);
        }
        this.mHistory.push(stickerItem);
    }

    /* access modifiers changed from: package-private */
    public void writeRecentToFile() {
        if (!this.mHistory.isEmpty()) {
            long currentTimeMillis = System.currentTimeMillis();
            Writer openWriter = IoUtils.openWriter(this.mRecentFile);
            if (openWriter == null) {
                Log.w("StickerProvider", "open recent file failed.");
            }
            JSONArray jSONArray = new JSONArray();
            int i = 0;
            for (StickerItem json : this.mHistory) {
                JSONObject json2 = StickerItem.toJson(json);
                if (json2 == null) {
                    Log.w("StickerProvider", "jsonfy failed");
                } else {
                    jSONArray.put(json2);
                    i++;
                }
                if (i >= 10) {
                    break;
                }
            }
            Log.d("StickerProvider", "write %d recent items to file", (Object) Integer.valueOf(i));
            try {
                openWriter.write(jSONArray.toString());
            } catch (IOException e) {
                Log.w("StickerProvider", (Throwable) e);
            } catch (Throwable th) {
                IoUtils.close("StickerProvider", openWriter);
                throw th;
            }
            IoUtils.close("StickerProvider", openWriter);
            Log.d("StickerProvider", "write to file costs %dms", (Object) Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
    }
}
