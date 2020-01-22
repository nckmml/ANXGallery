package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.InstallSourceType;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemCategory;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemType;
import com.nexstreaming.app.common.nexasset.assetpackage.e;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.store.AssetLocalInstallDB;
import com.nexstreaming.app.common.task.Task;
import com.nexstreaming.app.common.util.FileType;
import com.nexstreaming.app.common.util.n;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class nexAssetPackageManager {
    public static final int Mode_Hot = 1;
    public static final int Mode_New = 2;
    private static final String TAG = "nexAssetPackageMan";
    private static nexAssetPackageManager sSingleton;
    private List<b> featuredLists;
    /* access modifiers changed from: private */
    public int installPackagesCount = 0;
    /* access modifiers changed from: private */
    public int installPackagesMaxCount = 0;
    private Context mAppContext;
    private List<Integer> uninstalledAssetIdxList = new ArrayList();
    private Object uninstalledAssetLock = new Object();
    private SparseArray<List<Item>> uninstalledItems = new SparseArray<>();

    public interface Asset {
        String assetId();

        int assetIdx();

        String assetName(String str);

        long expireRemain();

        int getAssetVersionCode();

        String getCategoryAlias();

        String[] getSupportedLocales();

        String getThumbnailPath();

        long installedTime();

        AssetInstallType installedType();

        String priceType();
    }

    public enum AssetInstallType {
        STORE,
        SHARE,
        APP_ASSETS,
        EXTRA
    }

    public enum Category {
        audio,
        audiofilter,
        background,
        effect,
        filter,
        font,
        overlay,
        template,
        transition,
        extra,
        collage,
        staticcollage,
        dynamiccollage,
        beattemplate
    }

    public interface Item {
        Category category();

        String[] getSupportedLocales();

        boolean hidden();

        Bitmap icon();

        String id();

        boolean isDelete();

        String name(String str);

        Asset packageInfo();

        Bitmap thumbnail();

        ItemMethodType type();

        boolean validate();
    }

    public enum ItemMethodType {
        ItemOverlay,
        ItemRenderitem,
        ItemKedl,
        ItemAudio,
        ItemFont,
        ItemTemplate,
        ItemLut,
        ItemMedia,
        ItemExtra,
        ItemCollage,
        ItemStaticCollage,
        ItemDynamicCollage,
        ItemBeat
    }

    public static abstract class OnInstallPackageListener {
        public static int kEvent_installOk = 0;
        public static int kEvent_linstallFail = -1;

        public abstract void onCompleted(int i, int i2);

        public abstract void onProgress(int i, int i2, int i3);
    }

    public enum PreAssetCategoryAlias {
        Effect,
        Transition,
        Font,
        Overlay,
        Audio,
        Template,
        ClipGraphics,
        TextEffect,
        Extra,
        Collage,
        StaticCollage,
        DynamicCollage,
        BeatTemplate
    }

    public interface RemoteAssetInfo {
        String getCategoryAlias();

        Bitmap icon();

        String id();

        int idx();

        String name();
    }

    static class a implements Asset {
        protected int a;
        protected String b;
        protected String c;
        protected Map<String, String> d;
        protected String e;
        protected long f;
        protected long g;
        protected AssetInstallType h;
        protected String i;
        protected String j;
        protected int k;
        private boolean l;

        a() {
        }

        private void a() {
            if (this.d == null && !this.l) {
                this.l = true;
                this.d = new HashMap();
                com.nexstreaming.app.common.nexasset.assetpackage.b a2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().a(this.b);
                if (a2 == null) {
                    Log.d(nexAssetPackageManager.TAG, "No AssetInfo! id=" + this.b);
                    return;
                }
                Map<String, String> assetName = a2.getAssetName();
                if (assetName != null) {
                    for (String next : assetName.keySet()) {
                        if (this.c == null) {
                            this.c = assetName.get(next);
                        }
                        this.d.put(next.toLowerCase(Locale.ENGLISH), assetName.get(next));
                    }
                    return;
                }
                AssetPackageReader assetPackageReader = null;
                try {
                    AssetPackageReader a3 = AssetPackageReader.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), ((f) com.nexstreaming.app.common.nexasset.assetpackage.c.a().b(this.b).get(0)).getPackageURI(), this.b);
                    Map<String, String> e2 = a3.e();
                    if (e2 != null) {
                        for (String next2 : e2.keySet()) {
                            if (this.c == null) {
                                this.c = e2.get(next2);
                            }
                            this.d.put(next2.toLowerCase(Locale.ENGLISH), e2.get(next2));
                        }
                    }
                    a3.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                } catch (Throwable th) {
                    assetPackageReader.close();
                    throw th;
                }
            }
        }

        public String assetId() {
            return this.b;
        }

        public int assetIdx() {
            return this.a;
        }

        public String assetName(String str) {
            a();
            if (str == null) {
                this.c = n.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), this.d, this.b);
            }
            Map<String, String> map = this.d;
            if (map != null) {
                if (str == null) {
                    return this.c;
                }
                String str2 = map.get(str.toLowerCase(Locale.ENGLISH));
                if (str2 != null) {
                    return str2;
                }
            }
            String str3 = this.c;
            return str3 == null ? this.b : str3;
        }

        public long expireRemain() {
            return this.g;
        }

        public int getAssetVersionCode() {
            return this.k;
        }

        public String getCategoryAlias() {
            return this.i;
        }

        public String[] getSupportedLocales() {
            a();
            Map<String, String> map = this.d;
            int i2 = 0;
            if (map == null) {
                return new String[0];
            }
            if (map.size() == 0) {
                return new String[0];
            }
            String[] strArr = new String[this.d.size()];
            for (String next : this.d.keySet()) {
                Log.d(nexAssetPackageManager.TAG, "AssetEnt locale=" + next);
                strArr[i2] = next;
                i2++;
            }
            return strArr;
        }

        public String getThumbnailPath() {
            return this.j;
        }

        public long installedTime() {
            return this.f;
        }

        public AssetInstallType installedType() {
            return this.h;
        }

        public String priceType() {
            return this.e;
        }
    }

    private class b {
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public List<d> d;

        private b() {
        }
    }

    static class c implements Item {
        protected Category category;
        private boolean getNamesMap;
        protected String id;
        protected boolean isDelete;
        protected boolean isHidden;
        protected String name;
        private Map<String, String> namesMap;
        protected Asset packInfo;
        protected ItemMethodType type;

        c() {
        }

        protected c(Item item) {
            this.id = item.id();
            this.packInfo = item.packageInfo();
            this.category = item.category();
            this.isDelete = item.isDelete();
            this.isHidden = item.hidden();
            this.category = item.category();
            this.type = item.type();
        }

        private void loadLabels() {
            if (this.namesMap == null && !this.getNamesMap) {
                this.getNamesMap = true;
                this.namesMap = new HashMap();
                f c = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(id());
                if (c == null) {
                    Log.d(nexAssetPackageManager.TAG, "No ItemInfo! id=" + id());
                    return;
                }
                Map<String, String> label = c.getLabel();
                if (label != null) {
                    for (String next : label.keySet()) {
                        if (this.name == null) {
                            this.name = label.get(next);
                        }
                        this.namesMap.put(next.toLowerCase(Locale.ENGLISH), label.get(next));
                    }
                }
            }
        }

        public Category category() {
            return this.category;
        }

        public String[] getSupportedLocales() {
            loadLabels();
            Map<String, String> map = this.namesMap;
            int i = 0;
            if (map == null) {
                return new String[0];
            }
            if (map.size() == 0) {
                return new String[0];
            }
            String[] strArr = new String[this.namesMap.size()];
            for (String str : this.namesMap.keySet()) {
                strArr[i] = str;
                i++;
            }
            return strArr;
        }

        public boolean hidden() {
            return this.isHidden;
        }

        public Bitmap icon() {
            Bitmap bitmap;
            f c = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(this.id);
            if (c != null) {
                try {
                    bitmap = e.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), c, 0, 0);
                } catch (IOException unused) {
                    bitmap = null;
                }
                if (bitmap == null) {
                    try {
                        bitmap = e.b(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), c, 0, 0);
                    } catch (IOException unused2) {
                    }
                }
                if (bitmap != null) {
                    return bitmap;
                }
            }
            return null;
        }

        public String id() {
            return this.id;
        }

        public boolean isDelete() {
            return this.isDelete;
        }

        public String name(String str) {
            String str2;
            loadLabels();
            if (str == null) {
                this.name = n.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), this.namesMap, this.id);
            }
            if (str == null) {
                String str3 = this.name;
                return str3 == null ? this.id : str3;
            }
            Map<String, String> map = this.namesMap;
            if (map != null && (str2 = map.get(str.toLowerCase(Locale.ENGLISH))) != null) {
                return str2;
            }
            String str4 = this.name;
            return str4 == null ? this.id : str4;
        }

        public Asset packageInfo() {
            return this.packInfo;
        }

        public Bitmap thumbnail() {
            com.nexstreaming.app.common.nexasset.assetpackage.b assetPackage;
            String thumbPath;
            Asset asset = this.packInfo;
            if (asset == null) {
                f c = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(this.id);
                if (c == null || (assetPackage = c.getAssetPackage()) == null || (thumbPath = assetPackage.getThumbPath()) == null || !new File(thumbPath).isFile()) {
                    return null;
                }
                return BitmapFactory.decodeFile(thumbPath);
            }
            String thumbnailPath = asset.getThumbnailPath();
            if (thumbnailPath == null || !new File(thumbnailPath).isFile()) {
                return null;
            }
            return BitmapFactory.decodeFile(thumbnailPath);
        }

        public ItemMethodType type() {
            return this.type;
        }

        public boolean validate() {
            return com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(this.id) != null;
        }
    }

    static class d implements RemoteAssetInfo {
        private int a;
        private String b;
        private String c;
        private String d;
        private String e;

        d(String str, String str2, int i, String str3, String str4) {
            this.d = str;
            this.b = str2;
            this.a = i;
            this.c = str3;
            this.e = str4;
        }

        public String getCategoryAlias() {
            return this.e;
        }

        public Bitmap icon() {
            String str = this.d;
            if (str == null) {
                return null;
            }
            return BitmapFactory.decodeFile(str);
        }

        public String id() {
            return this.b;
        }

        public int idx() {
            return this.a;
        }

        public String name() {
            return this.c;
        }

        public String toString() {
            return "RemoteAssetInfoEnt{category='" + this.e + '\'' + ", idx=" + this.a + ", id='" + this.b + '\'' + ", name='" + this.c + '\'' + ", iconPath='" + this.d + '\'' + '}';
        }
    }

    private nexAssetPackageManager(Context context) {
        this.mAppContext = context;
    }

    public static boolean checkExpireAsset(Asset asset) {
        if (asset != null) {
            return com.nexstreaming.app.common.nexasset.assetpackage.c.a().a(com.nexstreaming.app.common.nexasset.assetpackage.c.a().b(asset.assetIdx()));
        }
        Log.d(TAG, "checkExpireAsset() : asset is null! return true.");
        return true;
    }

    public static long expireRemainTime(Asset asset) {
        if (asset.expireRemain() <= 0) {
            return Long.MAX_VALUE;
        }
        long installedTime = (asset.installedTime() + asset.expireRemain()) - System.currentTimeMillis();
        if (installedTime < 0) {
            return 0;
        }
        return installedTime;
    }

    private a getAssetEnt(com.nexstreaming.app.common.nexasset.assetpackage.b bVar) {
        a aVar = new a();
        if (bVar == null) {
            Log.d(TAG, "getAssetEnt AssetInfo is null!");
            aVar.h = AssetInstallType.EXTRA;
            aVar.i = "Unknown";
            aVar.e = "Free";
        } else {
            aVar.a = bVar.getAssetIdx();
            aVar.b = bVar.getAssetId();
            Map<String, String> assetName = bVar.getAssetName();
            if (assetName != null) {
                aVar.c = assetName.get("en");
            }
            aVar.e = bVar.getPriceType();
            if (aVar.e == null) {
                aVar.e = "Free";
            }
            aVar.g = bVar.getExpireTime();
            aVar.f = bVar.getInstalledTime();
            if (bVar.getInstallSourceType() == InstallSourceType.APP_ASSETS) {
                aVar.h = AssetInstallType.APP_ASSETS;
            } else if (bVar.getInstallSourceType() == InstallSourceType.FOLDER) {
                aVar.h = AssetInstallType.SHARE;
            } else if (bVar.getInstallSourceType() == InstallSourceType.STORE) {
                aVar.h = AssetInstallType.STORE;
            }
            if (bVar.getAssetCategory() != null) {
                aVar.i = bVar.getAssetCategory().getCategoryAlias();
            } else {
                aVar.i = "Unknown";
            }
            aVar.j = bVar.getThumbPath();
            aVar.k = bVar.getPackageVersion();
        }
        return aVar;
    }

    public static nexAssetPackageManager getAssetPackageManager(Context context) {
        nexAssetPackageManager nexassetpackagemanager = sSingleton;
        if (nexassetpackagemanager != null && !nexassetpackagemanager.mAppContext.getPackageName().equals(context.getPackageName())) {
            sSingleton = null;
        }
        if (sSingleton == null) {
            sSingleton = new nexAssetPackageManager(context);
            AssetLocalInstallDB.getInstance(context);
        }
        return sSingleton;
    }

    static Rect getAssetPackageMediaOptions(String str) {
        f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(str);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Rect rect = new Rect();
        if (c2 != null) {
            try {
                AssetPackageReader a2 = AssetPackageReader.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), c2.getPackageURI(), c2.getAssetPackage().getAssetId());
                try {
                    InputStream a3 = a2.a(c2.getFilePath());
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(a3);
                    bufferedInputStream.mark(bufferedInputStream.available());
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeStream(bufferedInputStream, (Rect) null, options);
                    rect.set(0, 0, options.outWidth, options.outHeight);
                    bufferedInputStream.close();
                    a3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                a2.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return rect;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0057 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008b  */
    public static String getAssetPackageMediaPath(Context context, String str) {
        boolean z;
        AssetPackageReader assetPackageReader;
        AssetLocalInstallDB.getInstance(context);
        f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(str);
        if (c2 == null) {
            return null;
        }
        if (com.nexstreaming.app.common.nexasset.assetpackage.c.a().a(c2.getAssetPackage())) {
            Log.d(TAG, "getAssetPackageMediaPath expire id=" + str);
            return null;
        }
        z = false;
        try {
            assetPackageReader = AssetPackageReader.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), c2.getPackageURI(), c2.getAssetPackage().getAssetId());
            try {
                assetPackageReader.c(c2.getFilePath());
            } catch (AssetPackageReader.EncryptedException ) {
                Log.d(TAG, "getAssetPackageMediaPath encrypted item id=" + str);
                z = true;
            } catch (IOException unused) {
            }
            try {
                Log.d(TAG, "getAssetPackageMediaPath encrypted item id=" + str);
                z = true;
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                if (assetPackageReader == null) {
                }
            }
        } catch (IOException e2) {
            e = e2;
            assetPackageReader = null;
            e.printStackTrace();
            if (assetPackageReader == null) {
            }
        }
        if (assetPackageReader == null) {
            Log.d(TAG, "getAssetPackageMediaPath file not found!=" + str);
            return null;
        }
        if (!z) {
            String packageURI = c2.getPackageURI();
            if (packageURI != null) {
                String substring = packageURI.substring(packageURI.indexOf(58) + 1);
                if (packageURI.startsWith("file:")) {
                    File file = new File(substring, c2.getFilePath());
                    if (file.isFile()) {
                        try {
                            assetPackageReader.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return file.getAbsolutePath();
                    }
                } else if (packageURI.startsWith("assets:")) {
                    String str2 = substring + "/" + c2.getFilePath();
                    if (!FileType.fromExtension(str2).isImage()) {
                        try {
                            AssetFileDescriptor openFd = context.getAssets().openFd(str2);
                            long startOffset = openFd.getStartOffset();
                            long length = openFd.getLength();
                            openFd.close();
                            try {
                                assetPackageReader.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            return "nexasset://" + str.hashCode() + ":" + startOffset + ":" + length;
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                }
            }
        } else {
            String packageURI2 = c2.getPackageURI();
            if (packageURI2 != null) {
                if (FileType.fromExtension(packageURI2 + "/" + c2.getFilePath()).isImage()) {
                    try {
                        assetPackageReader.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    return "@assetItem:" + str;
                }
            }
        }
        String str3 = context.getCacheDir().getAbsolutePath() + File.separator + "media";
        File file2 = new File(str3);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        Log.d(TAG, "media path=" + c2.getFilePath());
        File file3 = new File(str3, c2.getId());
        if (file3.isFile() || assetPackageReader == null) {
            try {
                assetPackageReader.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            return file3.getAbsolutePath();
        }
        try {
            InputStream a2 = assetPackageReader.a(c2.getFilePath());
            if (a2 != null) {
                com.nexstreaming.app.common.util.d.a(a2, file3);
                a2.close();
            }
            if (assetPackageReader != null) {
                try {
                    assetPackageReader.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
        } catch (IOException e9) {
            e9.printStackTrace();
            file3.delete();
            if (assetPackageReader != null) {
                assetPackageReader.close();
            }
        } catch (Throwable th) {
            if (assetPackageReader != null) {
                try {
                    assetPackageReader.close();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            }
            throw th;
        }
        return file3.getAbsolutePath();
    }

    private Category getCategory(ItemCategory itemCategory) {
        return itemCategory == ItemCategory.audio ? Category.audio : itemCategory == ItemCategory.audiofilter ? Category.audiofilter : itemCategory == ItemCategory.background ? Category.background : itemCategory == ItemCategory.effect ? Category.effect : itemCategory == ItemCategory.filter ? Category.filter : itemCategory == ItemCategory.font ? Category.font : itemCategory == ItemCategory.overlay ? Category.overlay : itemCategory == ItemCategory.template ? Category.template : itemCategory == ItemCategory.transition ? Category.transition : itemCategory == ItemCategory.collage ? Category.collage : itemCategory == ItemCategory.staticcollage ? Category.staticcollage : itemCategory == ItemCategory.dynamiccollage ? Category.dynamiccollage : itemCategory == ItemCategory.beattemplate ? Category.beattemplate : Category.extra;
    }

    private ItemCategory getItemCategory(Category category) {
        if (category == Category.audio) {
            return ItemCategory.audio;
        }
        if (category == Category.audiofilter) {
            return ItemCategory.audiofilter;
        }
        if (category == Category.background) {
            return ItemCategory.background;
        }
        if (category == Category.effect) {
            return ItemCategory.effect;
        }
        if (category == Category.filter) {
            return ItemCategory.filter;
        }
        if (category == Category.font) {
            return ItemCategory.font;
        }
        if (category == Category.overlay) {
            return ItemCategory.overlay;
        }
        if (category == Category.template) {
            return ItemCategory.template;
        }
        if (category == Category.transition) {
            return ItemCategory.transition;
        }
        if (category == Category.collage) {
            return ItemCategory.collage;
        }
        if (category == Category.staticcollage) {
            return ItemCategory.staticcollage;
        }
        if (category == Category.dynamiccollage) {
            return ItemCategory.dynamiccollage;
        }
        if (category == Category.beattemplate) {
            return ItemCategory.beattemplate;
        }
        return null;
    }

    private c getItemEnt(f fVar) {
        c cVar = new c();
        cVar.id = fVar.getId();
        Map<String, String> label = fVar.getLabel();
        if (label != null) {
            cVar.name = label.get("en");
        }
        if (cVar.name == null) {
            cVar.name = cVar.id;
        }
        cVar.packInfo = getAssetEnt(fVar.getAssetPackage());
        if (cVar.packInfo.installedType() == AssetInstallType.STORE) {
            cVar.isDelete = true;
        } else {
            cVar.isDelete = false;
        }
        cVar.isHidden = fVar.isHidden();
        cVar.category = getCategory(fVar.getCategory());
        cVar.type = getMethodType(fVar.getType());
        return cVar;
    }

    static ItemMethodType getMethodType(ItemType itemType) {
        if (itemType == ItemType.overlay) {
            return ItemMethodType.ItemOverlay;
        }
        if (itemType == ItemType.renderitem) {
            return ItemMethodType.ItemRenderitem;
        }
        if (itemType == ItemType.kedl) {
            return ItemMethodType.ItemKedl;
        }
        if (itemType == ItemType.audio) {
            return ItemMethodType.ItemAudio;
        }
        if (itemType == ItemType.font) {
            return ItemMethodType.ItemFont;
        }
        if (itemType == ItemType.template) {
            return ItemMethodType.ItemTemplate;
        }
        if (itemType == ItemType.lut) {
            return ItemMethodType.ItemLut;
        }
        if (itemType == ItemType.media) {
            return ItemMethodType.ItemMedia;
        }
        if (itemType == ItemType.collage) {
            return ItemMethodType.ItemCollage;
        }
        if (itemType == ItemType.staticcollage) {
            return ItemMethodType.ItemStaticCollage;
        }
        if (itemType == ItemType.dynamiccollage) {
            return ItemMethodType.ItemDynamicCollage;
        }
        if (itemType == ItemType.beat) {
            return ItemMethodType.ItemBeat;
        }
        return null;
    }

    public static PreAssetCategoryAlias getPreAssetCategoryAliasFromItem(Item item) {
        return item.category() == Category.template ? PreAssetCategoryAlias.Template : (item.category() == Category.overlay && item.type() == ItemMethodType.ItemTemplate) ? PreAssetCategoryAlias.TextEffect : (item.category() == Category.overlay && item.type() == ItemMethodType.ItemOverlay) ? PreAssetCategoryAlias.Overlay : item.category() == Category.audio ? PreAssetCategoryAlias.Audio : item.category() == Category.effect ? PreAssetCategoryAlias.Effect : item.category() == Category.transition ? PreAssetCategoryAlias.Transition : item.category() == Category.font ? PreAssetCategoryAlias.Font : item.category() == Category.collage ? PreAssetCategoryAlias.Collage : item.category() == Category.staticcollage ? PreAssetCategoryAlias.StaticCollage : item.category() == Category.dynamiccollage ? PreAssetCategoryAlias.DynamicCollage : item.category() == Category.beattemplate ? PreAssetCategoryAlias.BeatTemplate : PreAssetCategoryAlias.Extra;
    }

    public static String getPreloadedMediaAppAssetPath(Context context, String str) {
        AssetLocalInstallDB.getInstance(context);
        f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(str);
        if (c2 != null) {
            if (com.nexstreaming.app.common.nexasset.assetpackage.c.a().a(c2.getAssetPackage())) {
                Log.d(TAG, "getPreloadedAssetPath expire id=" + str);
                return null;
            }
            String packageURI = c2.getPackageURI();
            if (packageURI != null) {
                String substring = packageURI.substring(packageURI.indexOf(58) + 1);
                if (packageURI.startsWith("assets:")) {
                    String str2 = substring + "/" + c2.getFilePath();
                    Log.d(TAG, "getPreloadedAssetPath assets path=" + str2);
                    return str2;
                }
            }
        }
        return null;
    }

    private void removeUninstallItem(int i) {
        Iterator<Integer> it = this.uninstalledAssetIdxList.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().intValue() == i) {
                    it.remove();
                    break;
                }
            } else {
                break;
            }
        }
        this.uninstalledItems.remove(i);
        if (this.uninstalledAssetIdxList.size() > 5) {
            this.uninstalledItems.remove(this.uninstalledAssetIdxList.remove(0).intValue());
        }
    }

    private void resolveAssets(List<Item> list, Category category) {
        ItemCategory itemCategory;
        List<? extends f> a2;
        if (category != null) {
            itemCategory = getItemCategory(category);
            if (itemCategory == null) {
                return;
            }
        } else {
            itemCategory = null;
        }
        if (itemCategory != null && (a2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).a(itemCategory)) != null) {
            for (f itemEnt : a2) {
                list.add(getItemEnt(itemEnt));
            }
        }
    }

    private void updateList(int i) {
        b bVar;
        if (this.featuredLists == null) {
            this.featuredLists = new ArrayList();
        }
        List<AssetLocalInstallDB.remoteAssetItem> list = AssetLocalInstallDB.getInstance(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).getList(i, nexAssetStoreAppUtils.vendorName());
        if (list.size() != 0) {
            Iterator<b> it = this.featuredLists.iterator();
            while (true) {
                if (!it.hasNext()) {
                    bVar = null;
                    break;
                }
                bVar = it.next();
                if (bVar.b == i) {
                    break;
                }
            }
            if (bVar == null) {
                bVar = new b();
                int unused = bVar.b = i;
                List unused2 = bVar.d = new ArrayList();
                this.featuredLists.add(bVar);
            } else {
                bVar.d.clear();
                int unused3 = bVar.c = 0;
            }
            for (AssetLocalInstallDB.remoteAssetItem next : list) {
                bVar.d.add(new d(next.thumbnailPath, next.id, next.idx, next.name, next.category));
            }
        }
    }

    public int findNewPackages() {
        return AssetLocalInstallDB.getInstance(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).checkStoreInstall();
    }

    public String[] getAssetCategoriesWithInstalledItems() {
        List<? extends com.nexstreaming.app.common.nexasset.assetpackage.a> d2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().d();
        String[] strArr = new String[d2.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = new String(((com.nexstreaming.app.common.nexasset.assetpackage.a) d2.get(i)).getCategoryAlias());
        }
        return strArr;
    }

    public List<Asset> getInstalledAsset() {
        List<? extends com.nexstreaming.app.common.nexasset.assetpackage.b> b2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).b();
        ArrayList arrayList = new ArrayList();
        for (com.nexstreaming.app.common.nexasset.assetpackage.b assetEnt : b2) {
            arrayList.add(getAssetEnt(assetEnt));
        }
        return arrayList;
    }

    public List<Asset> getInstalledAssetByAppAsset() {
        List<? extends com.nexstreaming.app.common.nexasset.assetpackage.b> b2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).b();
        ArrayList arrayList = new ArrayList();
        for (com.nexstreaming.app.common.nexasset.assetpackage.b bVar : b2) {
            if (bVar.getInstallSourceType() == InstallSourceType.APP_ASSETS) {
                arrayList.add(getAssetEnt(bVar));
            }
        }
        return arrayList;
    }

    public List<Asset> getInstalledAssetByAppShare() {
        List<? extends com.nexstreaming.app.common.nexasset.assetpackage.b> b2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).b();
        ArrayList arrayList = new ArrayList();
        for (com.nexstreaming.app.common.nexasset.assetpackage.b bVar : b2) {
            if (bVar.getInstallSourceType() == InstallSourceType.FOLDER) {
                arrayList.add(getAssetEnt(bVar));
            }
        }
        return arrayList;
    }

    public Asset getInstalledAssetByIdx(int i) {
        com.nexstreaming.app.common.nexasset.assetpackage.b b2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).b(i);
        if (b2 == null) {
            return null;
        }
        return getAssetEnt(b2);
    }

    public List<Asset> getInstalledAssetByStore() {
        List<? extends com.nexstreaming.app.common.nexasset.assetpackage.b> b2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).b();
        ArrayList arrayList = new ArrayList();
        for (com.nexstreaming.app.common.nexasset.assetpackage.b bVar : b2) {
            if (bVar.getInstallSourceType() == InstallSourceType.STORE) {
                arrayList.add(getAssetEnt(bVar));
            }
        }
        return arrayList;
    }

    public Item getInstalledAssetItemById(String str) {
        f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).c(str);
        if (c2 == null) {
            return null;
        }
        return getItemEnt(c2);
    }

    public List<Item> getInstalledAssetItems() {
        ArrayList arrayList = new ArrayList();
        List<? extends f> c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).c();
        if (c2 != null) {
            for (f itemEnt : c2) {
                arrayList.add(getItemEnt(itemEnt));
            }
        }
        return arrayList;
    }

    public List<Item> getInstalledAssetItems(Category category) {
        ArrayList arrayList = new ArrayList();
        resolveAssets(arrayList, category);
        return arrayList;
    }

    public void getInstalledAssetItems(List<Item> list, Category category) {
        resolveAssets(list, category);
    }

    public List<Item> getInstalledAssetItemsByAssetID(String str) {
        List<? extends f> b2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).b(str);
        ArrayList arrayList = new ArrayList();
        for (f itemEnt : b2) {
            arrayList.add(getItemEnt(itemEnt));
        }
        return arrayList;
    }

    public List<Item> getInstalledAssetItemsByAssetIDx(int i) {
        List<? extends f> c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).c(i);
        ArrayList arrayList = new ArrayList();
        for (f itemEnt : c2) {
            arrayList.add(getItemEnt(itemEnt));
        }
        return arrayList;
    }

    public List<Item> getInstalledAssetItemsByAssetIDx(int i, Category category) {
        List<? extends f> a2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).a(i, getItemCategory(category));
        ArrayList arrayList = new ArrayList();
        for (f itemEnt : a2) {
            arrayList.add(getItemEnt(itemEnt));
        }
        return arrayList;
    }

    public PreAssetCategoryAlias getPreAssetCategoryAliasFromItem(String str) {
        Item installedAssetItemById = getAssetPackageManager(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).getInstalledAssetItemById(str);
        return installedAssetItemById == null ? PreAssetCategoryAlias.Extra : getPreAssetCategoryAliasFromItem(installedAssetItemById);
    }

    public RemoteAssetInfo getRemoteAssetInfo(int i) {
        b bVar;
        if (this.featuredLists == null) {
            updateList(i);
        }
        Iterator<b> it = this.featuredLists.iterator();
        while (true) {
            if (!it.hasNext()) {
                bVar = null;
                break;
            }
            bVar = it.next();
            if (bVar.b == i) {
                break;
            }
        }
        if (bVar == null || bVar.d.size() == 0) {
            return null;
        }
        if (bVar.c >= bVar.d.size()) {
            updateList(i);
            int unused = bVar.c = 0;
        }
        Log.d(TAG, "afl.current=" + bVar.c + ", afl.list.size()=" + bVar.d.size());
        RemoteAssetInfo remoteAssetInfo = (RemoteAssetInfo) bVar.d.get(bVar.c);
        int unused2 = bVar.c = bVar.c + 1;
        return remoteAssetInfo;
    }

    public RemoteAssetInfo[] getRemoteAssetInfos(int i) {
        List<AssetLocalInstallDB.remoteAssetItem> list = AssetLocalInstallDB.getInstance(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).getList(i, nexAssetStoreAppUtils.vendorName());
        d[] dVarArr = new d[list.size()];
        int i2 = 0;
        for (AssetLocalInstallDB.remoteAssetItem next : list) {
            dVarArr[i2] = new d(next.thumbnailPath, next.id, next.idx, next.name, next.category);
            i2++;
        }
        return dVarArr;
    }

    public boolean installPackageFromStorePath(int i) {
        return AssetLocalInstallDB.getInstance(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).installPackageSync(i);
    }

    public void installPackagesAsync(final int i, final OnInstallPackageListener onInstallPackageListener) {
        this.installPackagesCount = 0;
        this.installPackagesMaxCount = 0;
        AssetLocalInstallDB.getInstance(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).installPackageAsync(i).onComplete(new Task.OnTaskEventListener() {
            public void onTaskEvent(Task task, Task.Event event) {
                OnInstallPackageListener onInstallPackageListener = onInstallPackageListener;
                if (onInstallPackageListener != null) {
                    onInstallPackageListener.onCompleted(0, i);
                }
            }
        }).onFailure((Task.OnFailListener) new Task.OnFailListener() {
            public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                OnInstallPackageListener onInstallPackageListener = onInstallPackageListener;
                if (onInstallPackageListener != null) {
                    onInstallPackageListener.onCompleted(-1, i);
                }
            }
        }).onProgress(new Task.OnProgressListener() {
            public void onProgress(Task task, Task.Event event, int i, int i2) {
                OnInstallPackageListener onInstallPackageListener = onInstallPackageListener;
                if (onInstallPackageListener == null) {
                    return;
                }
                if (i2 == 100) {
                    onInstallPackageListener.onProgress(nexAssetPackageManager.this.installPackagesCount, nexAssetPackageManager.this.installPackagesMaxCount, i);
                    return;
                }
                int unused = nexAssetPackageManager.this.installPackagesCount = i;
                int unused2 = nexAssetPackageManager.this.installPackagesMaxCount = i2;
                onInstallPackageListener.onProgress(i, i2, 0);
            }
        });
    }

    public void installPackagesAsync(OnInstallPackageListener onInstallPackageListener) {
        installPackagesAsync(0, onInstallPackageListener);
    }

    public boolean isInstallingPackages() {
        return AssetLocalInstallDB.getInstance(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).isInstallingPackages();
    }

    public List<Item> popUninstallItems(int i) {
        List<Item> list;
        synchronized (this.uninstalledAssetLock) {
            list = this.uninstalledItems.get(i);
            removeUninstallItem(i);
        }
        return list;
    }

    public void putUninstallItem(int i) {
        synchronized (this.uninstalledAssetLock) {
            removeUninstallItem(i);
            List<Item> installedAssetItemsByAssetIDx = getInstalledAssetItemsByAssetIDx(i);
            if (installedAssetItemsByAssetIDx != null) {
                this.uninstalledItems.put(i, installedAssetItemsByAssetIDx);
                this.uninstalledAssetIdxList.add(Integer.valueOf(i));
            }
        }
    }

    @Deprecated
    public void uninstallFromAssetStoreApp() {
        AssetLocalInstallDB.getInstance(this.mAppContext).uninstallFromAssetStoreApp();
    }

    public void uninstallPackageByAssetIdx(int i) {
        putUninstallItem(i);
        try {
            AssetLocalInstallDB.getInstance(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).uninstallPackage(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uninstallPackageById(String str) {
        f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(str);
        if (c2 != null) {
            com.nexstreaming.app.common.nexasset.assetpackage.b assetPackage = c2.getAssetPackage();
            if (assetPackage != null) {
                uninstallPackageByAssetIdx(assetPackage.getAssetIdx());
            } else {
                Log.d(TAG, "AssetInfo is null");
            }
        }
    }

    public PreAssetCategoryAlias[] updateAssetInManager(boolean z, int i) {
        List<Item> list;
        HashSet<PreAssetCategoryAlias> hashSet = new HashSet<>();
        if (!z) {
            list = popUninstallItems(i);
            if (list == null) {
                Log.d(TAG, "updateAssetInManager can not found uninstalled items assetIdx=" + i);
            }
        } else {
            list = null;
        }
        if (list == null) {
            list = getAssetPackageManager(com.nexstreaming.kminternal.kinemaster.config.a.a().b()).getInstalledAssetItemsByAssetIDx(i);
        }
        int i2 = 0;
        if (list == null) {
            Log.d(TAG, "updateAssetInManager asset not found assetIdx=" + i);
            return new PreAssetCategoryAlias[0];
        } else if (list.size() == 0) {
            Log.d(TAG, "updateAssetInManager asset size 0 assetIdx=" + i);
            return new PreAssetCategoryAlias[0];
        } else {
            boolean z2 = true;
            boolean z3 = true;
            boolean z4 = true;
            boolean z5 = true;
            for (Item next : list) {
                if (next.category() == Category.filter && next.type() == ItemMethodType.ItemLut) {
                    Log.d(TAG, "updateAssetInManager update color effect assetIdx=" + i);
                    com.nexstreaming.kminternal.kinemaster.editorwrapper.b a2 = com.nexstreaming.kminternal.kinemaster.editorwrapper.b.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b());
                    if (a2 != null) {
                        if (next.hidden()) {
                            a2.h();
                        } else {
                            nexColorEffect.setNeedUpdate();
                        }
                    }
                } else if (next.category() == Category.template || next.category() == Category.beattemplate) {
                    nexTemplateManager templateManager = nexTemplateManager.getTemplateManager(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), com.nexstreaming.kminternal.kinemaster.config.a.a().b());
                    if (z2) {
                        templateManager.updateTemplate(false, next);
                        z2 = false;
                    }
                    if (z) {
                        templateManager.updateTemplate(true, next);
                    }
                    if (next.category() == Category.beattemplate) {
                        nexBeatTemplateManager beatTemplateManager = nexBeatTemplateManager.getBeatTemplateManager(com.nexstreaming.kminternal.kinemaster.config.a.a().b());
                        if (z3) {
                            beatTemplateManager.updateBeatTemplate(false, next);
                            z3 = false;
                        }
                        if (z) {
                            beatTemplateManager.updateBeatTemplate(true, next);
                        }
                    }
                    if (!next.hidden()) {
                        if (next.category() == Category.template) {
                            hashSet.add(PreAssetCategoryAlias.Template);
                        } else {
                            hashSet.add(PreAssetCategoryAlias.BeatTemplate);
                        }
                    }
                } else if (next.category() == Category.overlay && next.type() == ItemMethodType.ItemTemplate) {
                    nexOverlayManager overlayManager = nexOverlayManager.getOverlayManager(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), com.nexstreaming.kminternal.kinemaster.config.a.a().b());
                    if (z4) {
                        overlayManager.updateOverlayTitle(false, next);
                        z4 = false;
                    }
                    if (z) {
                        overlayManager.updateOverlayTitle(true, next);
                    }
                    if (!next.hidden()) {
                        hashSet.add(PreAssetCategoryAlias.TextEffect);
                    }
                } else if (next.category() == Category.audio) {
                    nexAssetMediaManager audioManager = nexAssetMediaManager.getAudioManager(com.nexstreaming.kminternal.kinemaster.config.a.a().b());
                    audioManager.updateMedia(false, 0, false, next);
                    if (z) {
                        audioManager.updateMedia(true, 3, false, next);
                    }
                    if (!next.hidden()) {
                        hashSet.add(PreAssetCategoryAlias.Audio);
                    }
                } else if (next.category() == Category.font) {
                    nexFont.needUpdate();
                    if (!next.hidden()) {
                        hashSet.add(PreAssetCategoryAlias.Font);
                    }
                } else if (next.category() == Category.effect) {
                    if (!next.hidden()) {
                        hashSet.add(PreAssetCategoryAlias.Effect);
                    }
                } else if (next.category() == Category.transition) {
                    if (!next.hidden()) {
                        hashSet.add(PreAssetCategoryAlias.Transition);
                    }
                } else if (next.category() == Category.overlay && next.type() == ItemMethodType.ItemOverlay) {
                    if (!next.hidden()) {
                        hashSet.add(PreAssetCategoryAlias.Overlay);
                    }
                } else if (next.category() == Category.collage || next.category() == Category.staticcollage || next.category() == Category.dynamiccollage) {
                    nexCollageManager collageManager = nexCollageManager.getCollageManager();
                    if (z5) {
                        collageManager.updateCollage(false, next);
                        z5 = false;
                    }
                    if (z) {
                        collageManager.updateCollage(true, next);
                    }
                    if (!next.hidden()) {
                        PreAssetCategoryAlias preAssetCategoryAlias = PreAssetCategoryAlias.Collage;
                        if (next.category() == Category.staticcollage) {
                            preAssetCategoryAlias = PreAssetCategoryAlias.StaticCollage;
                        } else if (next.category() == Category.dynamiccollage) {
                            preAssetCategoryAlias = PreAssetCategoryAlias.DynamicCollage;
                        }
                        hashSet.add(preAssetCategoryAlias);
                    }
                }
            }
            PreAssetCategoryAlias[] preAssetCategoryAliasArr = new PreAssetCategoryAlias[hashSet.size()];
            for (PreAssetCategoryAlias preAssetCategoryAlias2 : hashSet) {
                preAssetCategoryAliasArr[i2] = preAssetCategoryAlias2;
                i2++;
            }
            return preAssetCategoryAliasArr;
        }
    }

    public boolean validateAssetPackage(int i) {
        if (i < 3) {
            return true;
        }
        List<? extends f> c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(i);
        if (c2.size() == 0) {
            return false;
        }
        String packageURI = ((f) c2.get(0)).getPackageURI();
        if (!packageURI.startsWith("file:")) {
            return true;
        }
        String substring = packageURI.substring(packageURI.indexOf(58) + 1);
        if (!new File(substring).isDirectory()) {
            return false;
        }
        for (f fVar : c2) {
            if (!new File(substring, fVar.getFilePath()).isFile()) {
                Log.d(TAG, "file not found " + substring + "/" + fVar.getFilePath());
                return false;
            }
        }
        return true;
    }
}
