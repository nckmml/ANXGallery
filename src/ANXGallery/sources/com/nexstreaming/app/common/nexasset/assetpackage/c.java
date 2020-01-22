package com.nexstreaming.app.common.nexasset.assetpackage;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteFullException;
import android.graphics.Typeface;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.db.AssetPackageDb;
import com.nexstreaming.app.common.nexasset.assetpackage.db.AssetPackageRecord;
import com.nexstreaming.app.common.nexasset.assetpackage.db.CategoryRecord;
import com.nexstreaming.app.common.nexasset.assetpackage.db.InstallSourceRecord;
import com.nexstreaming.app.common.nexasset.assetpackage.db.ItemRecord;
import com.nexstreaming.app.common.nexasset.assetpackage.db.SubCategoryRecord;
import com.nexstreaming.app.common.nexasset.store.AbstractStoreAssetInfo;
import com.nexstreaming.app.common.nexasset.store.StoreAssetInfo;
import com.nexstreaming.app.common.util.h;
import com.nexstreaming.app.common.util.m;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.kminternal.kinemaster.fonts.Font;
import com.nexstreaming.kminternal.nexvideoeditor.NexEditor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: AssetPackageManager */
public class c {
    private static c a;
    private final AssetPackageDb b;
    private InstallSourceRecord c = null;

    /* renamed from: com.nexstreaming.app.common.nexasset.assetpackage.c$5  reason: invalid class name */
    /* compiled from: AssetPackageManager */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[ItemType.values().length];
        static final /* synthetic */ int[] b = new int[InstallSourceType.values().length];

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
        static {
            try {
                b[InstallSourceType.STORE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            b[InstallSourceType.APP_ASSETS.ordinal()] = 2;
            try {
                b[InstallSourceType.FOLDER.ordinal()] = 3;
            } catch (NoSuchFieldError unused2) {
            }
            a[ItemType.audio.ordinal()] = 1;
            try {
                a[ItemType.font.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private c(Context context) {
        this.b = new AssetPackageDb(context);
    }

    public static c a() {
        if (a == null) {
            a = new c(a.a().b());
        }
        return a;
    }

    public static c a(Context context) {
        if (a == null) {
            a = new c(context.getApplicationContext());
        }
        return a;
    }

    private InstallSourceRecord a(InstallSourceType installSourceType, String str) {
        String str2;
        int i = AnonymousClass5.b[installSourceType.ordinal()];
        if (i == 1) {
            return f();
        }
        if (i == 2) {
            str2 = "assets:" + str;
        } else if (i == 3) {
            str2 = "file:" + str;
        } else {
            throw new IllegalArgumentException();
        }
        InstallSourceRecord installSourceRecord = (InstallSourceRecord) this.b.findFirst(InstallSourceRecord.class, "install_source_id = ?", str2);
        if (installSourceRecord != null) {
            return installSourceRecord;
        }
        InstallSourceRecord installSourceRecord2 = new InstallSourceRecord();
        installSourceRecord2.installSourceId = str2;
        installSourceRecord2.installSourceType = installSourceType;
        installSourceRecord2.installSourceVersion = 0;
        this.b.add(installSourceRecord2);
        return installSourceRecord2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0052, code lost:
        r5 = r7;
     */
    private List<a> a(List<? extends a> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        for (AssetCategoryAlias assetCategoryAlias : AssetCategoryAlias.values()) {
            a aVar = null;
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                a aVar2 = (a) it.next();
                if (aVar2.getCategoryAlias().equalsIgnoreCase(assetCategoryAlias.name())) {
                    break;
                }
                if (aVar2.getCategoryAlias().equalsIgnoreCase(assetCategoryAlias.name() + "s")) {
                    break;
                }
            }
            if (aVar != null) {
                arrayList.add(aVar);
                arrayList2.remove(aVar);
            }
        }
        if (!arrayList2.isEmpty()) {
            Collections.sort(arrayList2, new Comparator<a>() {
                /* renamed from: a */
                public int compare(a aVar, a aVar2) {
                    return aVar.getCategoryAlias().compareTo(aVar2.getCategoryAlias());
                }
            });
            arrayList.addAll(arrayList2);
        }
        return arrayList;
    }

    private void a(AssetPackageReader assetPackageReader, File file, StoreAssetInfo storeAssetInfo, InstallSourceRecord installSourceRecord) throws IOException {
        CategoryRecord categoryRecord;
        SubCategoryRecord subCategoryRecord;
        this.b.beginTransaction();
        try {
            int i = 0;
            String str = null;
            if (storeAssetInfo.getCategoryIndex() != 0) {
                categoryRecord = (CategoryRecord) this.b.findFirst(CategoryRecord.class, "category_id = ?", Integer.valueOf(storeAssetInfo.getCategoryIndex()));
                if (categoryRecord == null) {
                    categoryRecord = new CategoryRecord();
                    categoryRecord.categoryId = (long) storeAssetInfo.getCategoryIndex();
                    categoryRecord.categoryName = storeAssetInfo.getCategoryAliasName();
                    categoryRecord.categoryIconURL = storeAssetInfo.getCategoryIconURL();
                    this.b.addOrUpdate(categoryRecord);
                } else if (!(categoryRecord.categoryIconURL == null || storeAssetInfo.getCategoryIconURL() == null || categoryRecord.categoryIconURL.equals(storeAssetInfo.getCategoryIconURL()))) {
                    categoryRecord.categoryIconURL = storeAssetInfo.getCategoryIconURL();
                    this.b.update(categoryRecord);
                }
            } else {
                categoryRecord = null;
            }
            if (storeAssetInfo.getSubCategoryIndex() != 0) {
                subCategoryRecord = (SubCategoryRecord) this.b.findFirst(SubCategoryRecord.class, "sub_category_id = ?", Integer.valueOf(storeAssetInfo.getSubCategoryIndex()));
                if (subCategoryRecord == null) {
                    subCategoryRecord = new SubCategoryRecord();
                    subCategoryRecord.subCategoryId = (long) storeAssetInfo.getSubCategoryIndex();
                    subCategoryRecord.subCategoryName = storeAssetInfo.getSubCategoryNameMap();
                    subCategoryRecord.subCategoryAlias = storeAssetInfo.getSubCategoryAliasName();
                    this.b.add(subCategoryRecord);
                } else {
                    subCategoryRecord.subCategoryName = storeAssetInfo.getSubCategoryNameMap();
                    this.b.update(subCategoryRecord);
                }
            } else {
                subCategoryRecord = (SubCategoryRecord) this.b.findFirst(SubCategoryRecord.class, "sub_category_id = ?", -1L);
                if (subCategoryRecord == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("en", "Local");
                    SubCategoryRecord subCategoryRecord2 = new SubCategoryRecord();
                    subCategoryRecord2.subCategoryId = -1;
                    subCategoryRecord2.subCategoryAlias = "local";
                    subCategoryRecord2.subCategoryName = hashMap;
                    this.b.add(subCategoryRecord2);
                    subCategoryRecord = subCategoryRecord2;
                }
            }
            AssetPackageRecord assetPackageRecord = new AssetPackageRecord();
            assetPackageRecord.assetIdx = storeAssetInfo.getAssetIndex();
            assetPackageRecord.packageURI = assetPackageReader.f();
            assetPackageRecord.assetId = storeAssetInfo.getAssetId();
            assetPackageRecord.assetUrl = storeAssetInfo.getAssetPackageDownloadURL();
            assetPackageRecord.thumbPath = file == null ? null : file.getAbsolutePath();
            assetPackageRecord.assetDesc = null;
            assetPackageRecord.assetName = storeAssetInfo.getAssetNameMap();
            if (assetPackageRecord.assetName.size() < 1) {
                assetPackageRecord.assetName = assetPackageReader.e();
            }
            assetPackageRecord.priceType = storeAssetInfo.getPriceType();
            assetPackageRecord.thumbUrl = storeAssetInfo.getAssetThumbnailURL();
            assetPackageRecord.installSource = installSourceRecord;
            assetPackageRecord.category = categoryRecord;
            assetPackageRecord.subCategory = subCategoryRecord;
            File g = assetPackageReader.g();
            if (g != null) {
                str = g.getAbsolutePath();
            }
            assetPackageRecord.localPath = str;
            assetPackageRecord.expireTime = storeAssetInfo.getExpireTime();
            assetPackageRecord.installedTime = System.currentTimeMillis();
            assetPackageRecord.minVersion = storeAssetInfo.getAssetScopeVersion();
            assetPackageRecord.packageVersion = storeAssetInfo.getAssetVersion();
            this.b.add(assetPackageRecord);
            for (f next : assetPackageReader.d()) {
                ItemRecord itemRecord = new ItemRecord();
                itemRecord.assetPackageRecord = assetPackageRecord;
                itemRecord.itemId = next.getId();
                itemRecord.packageURI = next.getPackageURI();
                itemRecord.filePath = next.getFilePath();
                itemRecord.iconPath = next.getIconPath();
                itemRecord.hidden = next.isHidden();
                itemRecord.thumbPath = next.getThumbPath();
                itemRecord.label = next.getLabel();
                itemRecord.itemType = next.getType();
                itemRecord.itemCategory = next.getCategory();
                itemRecord.sampleText = next.getSampleText();
                this.b.add(itemRecord);
                i++;
            }
            assetPackageReader.close();
            this.b.setTransactionSuccessful();
            Log.d("AssetPackageManager", "Added DB Record for: " + assetPackageRecord.assetId + " and " + i + " items.");
            try {
                this.b.endTransaction();
            } catch (SQLiteFullException e) {
                Log.d("AssetPackageManager", "AssetPackageDb.endTransaction() throws SQLiteFullException. e=" + e);
                throw new IOException(e);
            }
        } catch (Throwable th) {
            try {
                this.b.endTransaction();
                throw th;
            } catch (SQLiteFullException e2) {
                Log.d("AssetPackageManager", "AssetPackageDb.endTransaction() throws SQLiteFullException. e=" + e2);
                throw new IOException(e2);
            }
        }
    }

    private static String b(String str, String str2) {
        if (str2.startsWith("..") || str2.contains("/..")) {
            throw new SecurityException("Parent Path References Not Allowed");
        } else if (str.endsWith("/")) {
            return str + str2;
        } else {
            return str + "/" + str2;
        }
    }

    /* access modifiers changed from: private */
    public static String c(String str, String str2) {
        if (str2.startsWith("..") || str2.contains("/..")) {
            throw new SecurityException("Parent Path References Not Allowed");
        } else if (str.endsWith("/")) {
            return str + str2;
        } else {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf < 0) {
                return str2;
            }
            return str.substring(0, lastIndexOf + 1) + str2;
        }
    }

    private InstallSourceRecord f() {
        InstallSourceRecord installSourceRecord = this.c;
        if (installSourceRecord != null) {
            return installSourceRecord;
        }
        InstallSourceRecord installSourceRecord2 = (InstallSourceRecord) this.b.findFirst(InstallSourceRecord.class, "install_source_id = ?", "store");
        if (installSourceRecord2 == null) {
            installSourceRecord2 = new InstallSourceRecord();
            installSourceRecord2.installSourceId = "store";
            installSourceRecord2.installSourceType = InstallSourceType.STORE;
            installSourceRecord2.installSourceVersion = 0;
            this.b.add(installSourceRecord2);
        }
        this.c = installSourceRecord2;
        return installSourceRecord2;
    }

    public b a(String str) {
        return (b) this.b.findFirst(AssetPackageRecord.class, "asset_id = ?", str);
    }

    public String a(Iterable<String> iterable) throws IOException {
        AssetPackageReader a2;
        Context b2 = a.a().b();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StringBuilder sb = new StringBuilder();
        for (String next : iterable) {
            if (next != null) {
                f c2 = next.endsWith(".force_effect") ? c(next.substring(0, next.length() - 13)) : c(next);
                if (c2 == null) {
                    Log.w("AssetPackageManager", "Could not find item for id: " + next);
                } else if (c2.getType() == ItemType.renderitem) {
                    try {
                        a2 = AssetPackageReader.a(b2, c2.getPackageURI(), c2.getAssetPackage().getAssetId());
                        InputStream a3 = a2.a(c2.getFilePath());
                        byteArrayOutputStream.reset();
                        m.a(a3, byteArrayOutputStream);
                        a2.close();
                        sb.append(byteArrayOutputStream.toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (Throwable th) {
                        a2.close();
                        throw th;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX INFO: finally extract failed */
    public String a(Iterable<String> iterable, boolean z) throws IOException {
        Context b2 = a.a().b();
        StringBuilder sb = new StringBuilder();
        sb.append("<themeset name=\"KM\" defaultTheme=\"none\" defaultTransition=\"none\" >");
        sb.append("<texture id=\"video_out\" video=\"1\" />");
        sb.append("<texture id=\"video_in\" video=\"2\" />");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (String next : iterable) {
            if (next != null) {
                f c2 = next.endsWith(".force_effect") ? c(next.substring(0, next.length() - 13)) : c(next);
                if (c2 == null) {
                    Log.w("AssetPackageManager", "Could not find item for id: " + next);
                } else if (c2.getType() != ItemType.renderitem) {
                    AssetPackageReader a2 = AssetPackageReader.a(b2, c2.getPackageURI(), c2.getAssetPackage().getAssetId());
                    try {
                        InputStream a3 = a2.a(c2.getFilePath());
                        byteArrayOutputStream.reset();
                        m.a(a3, byteArrayOutputStream);
                        a2.close();
                        Log.d("AssetPackageManager", "template effects: " + c2.getId());
                        if (z || !next.endsWith(".force_effect")) {
                            sb.append(byteArrayOutputStream.toString());
                        } else {
                            Log.d("AssetPackageManager", "Original template transition: " + byteArrayOutputStream.toString());
                            String a4 = h.a(byteArrayOutputStream.toString(), next);
                            Log.d("AssetPackageManager", "Modify template transition: " + a4);
                            sb.append(a4);
                        }
                    } catch (Throwable th) {
                        a2.close();
                        throw th;
                    }
                }
            }
        }
        sb.append("</themeset>");
        return sb.toString();
    }

    public List<? extends f> a(int i, ItemCategory itemCategory) {
        return this.b.query(ItemRecord.class, "asset_package_record = ? AND item_category = ?", Long.valueOf(this.b.findFirstRowId(AssetPackageRecord.class, "asset_idx = ?", Integer.valueOf(i))), itemCategory);
    }

    public List<? extends f> a(ItemCategory itemCategory) {
        return this.b.query(ItemRecord.class, "item_category = ?", itemCategory);
    }

    public void a(int i) {
        this.b.beginTransaction();
        try {
            AssetPackageRecord assetPackageRecord = (AssetPackageRecord) this.b.findFirst(AssetPackageRecord.class, "asset_idx = ?", Integer.valueOf(i));
            for (ItemRecord delete : this.b.query(ItemRecord.class, "asset_package_record = ?", Long.valueOf(assetPackageRecord.getDbRowID()))) {
                this.b.delete(delete);
            }
            this.b.delete(assetPackageRecord);
            this.b.setTransactionSuccessful();
        } finally {
            try {
                this.b.endTransaction();
            } catch (SQLiteFullException e) {
                Log.d("AssetPackageManager", "AssetPackageDb.endTransaction() throws SQLiteFullException. e=" + e);
            }
        }
    }

    public void a(Context context, String str, long j) throws IOException {
        String[] strArr;
        Log.d("AssetPackageManager", "syncPackagesFromAndroidAssets - IN : " + str);
        InstallSourceRecord a2 = a(InstallSourceType.APP_ASSETS, str);
        Log.d("AssetPackageManager", "syncPackagesFromAndroidAssets - ISR CHECK: " + a2.installSourceVersion + " / " + j);
        if (a2.installSourceVersion != j) {
            Log.d("AssetPackageManager", "syncPackagesFromAndroidAssets - ISR MISMATCH; UPDATING : " + a2.installSourceVersion + " -> " + j);
            a2.installSourceVersion = 0;
            this.b.update(a2);
            a(a2);
            AssetManager assets = context.getAssets();
            for (final String str2 : assets.list(str)) {
                String b2 = b(str, str2);
                try {
                    strArr = assets.list(b2);
                } catch (IOException unused) {
                    strArr = null;
                }
                if (strArr != null && strArr.length > 0) {
                    Log.d("AssetPackageManager", "syncPackagesFromAndroidAssets - Processing package: " + str2 + " (in " + b2 + ")");
                    AssetPackageReader a3 = AssetPackageReader.a(assets, b2, str2);
                    a(a3, (File) null, (StoreAssetInfo) new AbstractStoreAssetInfo(a3.c(), a3.b()) {
                        public String getAssetId() {
                            return str2;
                        }

                        public String getAssetTitle() {
                            return str2;
                        }

                        public String getSubCategoryAliasName() {
                            return "local";
                        }
                    }, a2);
                }
            }
            a2.installSourceVersion = j;
            this.b.update(a2);
        }
        Log.d("AssetPackageManager", "syncPackagesFromAndroidAssets - OUT");
    }

    public void a(InstallSourceRecord installSourceRecord) {
        this.b.beginTransaction();
        try {
            for (AssetPackageRecord next : this.b.query(AssetPackageRecord.class, "install_source = ?", Long.valueOf(installSourceRecord.getDbRowID()))) {
                for (ItemRecord delete : this.b.query(ItemRecord.class, "asset_package_record = ?", Long.valueOf(next.getDbRowID()))) {
                    this.b.delete(delete);
                }
                this.b.delete(next);
            }
            this.b.setTransactionSuccessful();
        } finally {
            try {
                this.b.endTransaction();
            } catch (SQLiteFullException e) {
                Log.d("AssetPackageManager", "AssetPackageDb.endTransaction() throws SQLiteFullException. e=" + e);
            }
        }
    }

    public void a(File file) {
        InstallSourceRecord a2 = a(InstallSourceType.FOLDER, file.getAbsolutePath());
        a(a2);
        a2.installSourceVersion = 0;
        this.b.update(a2);
    }

    public void a(File file, File file2, StoreAssetInfo storeAssetInfo) throws IOException {
        a(file.isDirectory() ? AssetPackageReader.b(file, storeAssetInfo.getAssetId()) : AssetPackageReader.a(file, storeAssetInfo.getAssetId()), file2, storeAssetInfo, f());
    }

    public void a(Iterable<String> iterable, NexEditor nexEditor, boolean z) {
        AssetPackageReader a2;
        if (nexEditor != null && iterable != null) {
            Log.d("AssetPackageManager", "loadRenderItemsInEditor");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Context b2 = a.a().b();
            nexEditor.a(z);
            for (String next : iterable) {
                if (next != null) {
                    f c2 = next.endsWith(".force_effect") ? c(next.substring(0, next.length() - 13)) : c(next);
                    if (c2 == null) {
                        Log.w("AssetPackageManager", "Could not find item for id: " + next);
                    } else if (c2.getType() == ItemType.renderitem) {
                        try {
                            a2 = AssetPackageReader.a(b2, c2.getPackageURI(), c2.getAssetPackage().getAssetId());
                            InputStream a3 = a2.a(c2.getFilePath());
                            byteArrayOutputStream.reset();
                            m.a(a3, byteArrayOutputStream);
                            a2.close();
                            nexEditor.a(next, byteArrayOutputStream.toString(), z);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (Throwable th) {
                            a2.close();
                            throw th;
                        }
                    }
                }
            }
        }
    }

    public void a(Iterable<String> iterable, NexEditor nexEditor, boolean z, boolean z2) {
        if (nexEditor != null && iterable != null) {
            try {
                nexEditor.b(a(iterable, z2), z);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean a(b bVar) {
        if (bVar == null) {
            Log.d("AssetPackageManager", "checkExpireAsset assetinfo is null.");
            return true;
        } else if (bVar.getExpireTime() <= 0) {
            return false;
        } else {
            long installedTime = bVar.getInstalledTime() + bVar.getExpireTime();
            long installedTime2 = bVar.getInstalledTime() - 86400000;
            long currentTimeMillis = System.currentTimeMillis();
            return installedTime < currentTimeMillis || installedTime2 > currentTimeMillis;
        }
    }

    public boolean a(String str, ItemCategory itemCategory) {
        return this.b.count(ItemRecord.class, "asset_package_record = ? AND item_category = ?", Long.valueOf(this.b.findFirstRowId(AssetPackageRecord.class, "asset_id = ?", str)), itemCategory) > 0;
    }

    public b b(int i) {
        return (b) this.b.findFirst(AssetPackageRecord.class, "asset_idx = ?", Integer.valueOf(i));
    }

    public List<? extends b> b() {
        return this.b.query(AssetPackageRecord.class);
    }

    public List<? extends f> b(String str) {
        return this.b.query(ItemRecord.class, "asset_package_record = ?", Long.valueOf(this.b.findFirstRowId(AssetPackageRecord.class, "asset_id = ?", str)));
    }

    public boolean b(File file) throws IOException {
        final String str;
        AssetPackageReader assetPackageReader;
        InstallSourceRecord a2 = a(InstallSourceType.FOLDER, file.getAbsolutePath());
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length < 1) {
            a(a2);
            return false;
        }
        long j = 0;
        for (File lastModified : listFiles) {
            j = Math.max(j, lastModified.lastModified());
        }
        if (a2.installSourceVersion == j) {
            return false;
        }
        a2.installSourceVersion = 0;
        this.b.update(a2);
        a(a2);
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                str = file2.getName();
                assetPackageReader = AssetPackageReader.b(file2, str);
            } else if (file2.getName().endsWith(".zip")) {
                String name = file2.getName();
                str = name.substring(0, name.length() - 4);
                assetPackageReader = AssetPackageReader.a(file2, str);
            } else {
                assetPackageReader = null;
                str = null;
            }
            if (assetPackageReader != null) {
                a(assetPackageReader, (File) null, (StoreAssetInfo) new AbstractStoreAssetInfo(assetPackageReader.c(), assetPackageReader.b()) {
                    public String getAssetId() {
                        return str;
                    }

                    public int getAssetIndex() {
                        return 1;
                    }

                    public String getSubCategoryAliasName() {
                        return "local";
                    }
                }, a2);
            }
        }
        a2.installSourceVersion = j;
        this.b.update(a2);
        return true;
    }

    public f c(String str) {
        return (f) this.b.findFirst(ItemRecord.class, "item_id = ?", str);
    }

    public List<? extends f> c() {
        return this.b.query(ItemRecord.class);
    }

    public List<? extends f> c(int i) {
        return this.b.query(ItemRecord.class, "asset_package_record = ?", Long.valueOf(this.b.findFirstRowId(AssetPackageRecord.class, "asset_idx = ?", Integer.valueOf(i))));
    }

    public List<? extends a> d() {
        ArrayList arrayList = new ArrayList();
        for (CategoryRecord next : this.b.query(CategoryRecord.class)) {
            if (this.b.count(AssetPackageRecord.class, "category = ?", Long.valueOf(next.getDbRowID())) > 0) {
                arrayList.add(next);
            }
        }
        return a((List<? extends a>) arrayList);
    }

    public com.nexstreaming.kminternal.nexvideoeditor.a e() {
        return new com.nexstreaming.kminternal.nexvideoeditor.a() {
            public Typeface a(String str) throws Font.TypefaceLoadException {
                return com.nexstreaming.kminternal.kinemaster.fonts.c.a().b(str);
            }

            public File a(String str, String str2) throws IOException {
                throw new UnsupportedOperationException();
            }

            public InputStream b(String str, String str2) throws IOException {
                f c = c.this.c(str);
                return AssetPackageReader.a(a.a().b(), c.getPackageURI(), c.getAssetPackage().getAssetId()).a(str2 == null ? c.getFilePath() : c.c(c.getFilePath(), str2));
            }

            public Typeface c(String str, String str2) throws Font.TypefaceLoadException, IOException {
                AssetManager assets;
                f c = c.this.c(str);
                AssetPackageReader a2 = AssetPackageReader.a(a.a().b(), c.getPackageURI(), c.getAssetPackage().getAssetId());
                String f = a2.f();
                String a3 = c.c(c.getFilePath(), str2);
                if (f.startsWith("file:")) {
                    String substring = f.substring(5);
                    try {
                        return Typeface.createFromFile(substring + "/" + a2.b(a3));
                    } catch (RuntimeException unused) {
                        throw new Font.TypefaceLoadException();
                    }
                } else if (!f.startsWith("assets:") || (assets = a.a().b().getAssets()) == null) {
                    return null;
                } else {
                    String substring2 = f.substring(7);
                    try {
                        return Typeface.createFromAsset(assets, substring2 + "/" + a2.b(a3));
                    } catch (RuntimeException unused2) {
                        throw new Font.TypefaceLoadException();
                    }
                }
            }
        };
    }
}
