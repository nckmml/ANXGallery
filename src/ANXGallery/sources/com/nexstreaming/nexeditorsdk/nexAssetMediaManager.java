package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.e;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.nexAssetPackageManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class nexAssetMediaManager {
    private static final String TAG = "nexAudioManager";
    private static Context mAppContext;
    private static nexAssetMediaManager sSingleton;
    private List<AssetMedia> externalView_mediaEntries = null;
    private Object m_mediaentryLock = new Object();
    private List<AssetMedia> mediaEntries = new ArrayList();

    public static class AssetMedia extends nexAssetPackageManager.c {
        /* access modifiers changed from: private */
        public boolean filter;
        private boolean getPath;
        private boolean getpreloadedAssetPath;
        private String mediaPath;
        private String preloadedAssetPath;
        /* access modifiers changed from: private */
        public int type;

        AssetMedia(nexAssetPackageManager.Item item) {
            super(item);
        }

        public static AssetMedia promote(nexAssetPackageManager.Item item) {
            AssetMedia assetMedia = new AssetMedia(item);
            assetMedia.type = 3;
            assetMedia.filter = false;
            return assetMedia;
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Category category() {
            return super.category();
        }

        public boolean filter() {
            return this.filter;
        }

        public int getClipType() {
            return this.type;
        }

        public Bitmap getImageThumbnail() {
            f c;
            if (this.type != 1 || (c = c.a().c(id())) == null) {
                return null;
            }
            try {
                return e.b(a.a().b(), c, 0, 0);
            } catch (IOException unused) {
                return null;
            }
        }

        public String getPath() {
            if (!this.getPath) {
                this.mediaPath = nexAssetPackageManager.getAssetPackageMediaPath(a.a().b(), id());
                this.getPath = true;
            }
            return this.mediaPath;
        }

        public String getPreloadedAssetPath() {
            if (!this.getpreloadedAssetPath) {
                this.preloadedAssetPath = nexAssetPackageManager.getPreloadedMediaAppAssetPath(a.a().b(), id());
                this.getpreloadedAssetPath = true;
            }
            return this.preloadedAssetPath;
        }

        public /* bridge */ /* synthetic */ String[] getSupportedLocales() {
            return super.getSupportedLocales();
        }

        public /* bridge */ /* synthetic */ boolean hidden() {
            return super.hidden();
        }

        public /* bridge */ /* synthetic */ Bitmap icon() {
            return super.icon();
        }

        public /* bridge */ /* synthetic */ String id() {
            return super.id();
        }

        public /* bridge */ /* synthetic */ boolean isDelete() {
            return super.isDelete();
        }

        public /* bridge */ /* synthetic */ String name(String str) {
            return super.name(str);
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Asset packageInfo() {
            return super.packageInfo();
        }

        public /* bridge */ /* synthetic */ Bitmap thumbnail() {
            return super.thumbnail();
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.ItemMethodType type() {
            return super.type();
        }

        public /* bridge */ /* synthetic */ boolean validate() {
            return super.validate();
        }
    }

    private nexAssetMediaManager(Context context) {
        mAppContext = context;
    }

    public static nexAssetMediaManager getAudioManager(Context context) {
        if (sSingleton != null && !mAppContext.getPackageName().equals(context.getPackageName())) {
            sSingleton = null;
        }
        if (sSingleton == null) {
            sSingleton = new nexAssetMediaManager(context);
        }
        return sSingleton;
    }

    private void resolveMedia(int i, boolean z) {
        synchronized (this.m_mediaentryLock) {
            this.mediaEntries.clear();
            if (i == 3) {
                for (nexAssetPackageManager.Item next : nexAssetPackageManager.getAssetPackageManager(mAppContext).getInstalledAssetItems(nexAssetPackageManager.Category.audio)) {
                    if (!next.hidden()) {
                        if (z) {
                            nexAssetPackageManager.getAssetPackageManager(mAppContext);
                            if (nexAssetPackageManager.checkExpireAsset(next.packageInfo())) {
                            }
                        }
                        AssetMedia assetMedia = new AssetMedia(next);
                        int unused = assetMedia.type = 3;
                        boolean unused2 = assetMedia.filter = false;
                        this.mediaEntries.add(assetMedia);
                    }
                }
            }
        }
    }

    public void applyProjectBGM(nexProject nexproject, String str) {
        String str2;
        AssetMedia assetMedia = getAssetMedia(str);
        if (assetMedia != null) {
            if (nexAssetPackageManager.checkExpireAsset(assetMedia.packageInfo())) {
                Log.d(TAG, "applyProjectBGM expire Id=" + str);
                return;
            } else if (assetMedia.getClipType() == 3) {
                str2 = assetMedia.getPath();
                nexproject.setBackgroundMusicPath(str2);
            }
        }
        str2 = null;
        nexproject.setBackgroundMusicPath(str2);
    }

    public nexClip createAudioClip(String str) {
        AssetMedia assetMedia = getAssetMedia(str);
        if (assetMedia == null) {
            return null;
        }
        return nexClip.getSupportedClip(assetMedia.getPath());
    }

    public AssetMedia getAssetMedia(String str) {
        synchronized (this.m_mediaentryLock) {
            for (AssetMedia next : this.mediaEntries) {
                if (next.id().compareTo(str) == 0) {
                    return next;
                }
            }
            return null;
        }
    }

    public String[] getAssetMediaIds(int i) {
        String[] strArr;
        synchronized (this.m_mediaentryLock) {
            ArrayList arrayList = new ArrayList();
            for (AssetMedia next : this.mediaEntries) {
                if (next.getClipType() == 3) {
                    arrayList.add(next.id());
                }
            }
            strArr = new String[arrayList.size()];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr[i2] = (String) arrayList.get(i2);
            }
        }
        return strArr;
    }

    public List<AssetMedia> getAssetMedias() {
        if (this.externalView_mediaEntries == null) {
            this.externalView_mediaEntries = Collections.unmodifiableList(this.mediaEntries);
        }
        return this.externalView_mediaEntries;
    }

    public AssetMedia[] getAssetMedias(int i) {
        AssetMedia[] assetMediaArr;
        synchronized (this.m_mediaentryLock) {
            ArrayList arrayList = new ArrayList();
            for (AssetMedia next : this.mediaEntries) {
                if (next.getClipType() == i) {
                    arrayList.add(next);
                }
            }
            assetMediaArr = new AssetMedia[arrayList.size()];
            for (int i2 = 0; i2 < assetMediaArr.length; i2++) {
                assetMediaArr[i2] = (AssetMedia) arrayList.get(i2);
            }
        }
        return assetMediaArr;
    }

    public void loadMedia(int i) {
        resolveMedia(i, false);
    }

    public void loadMedia(int i, boolean z) {
        resolveMedia(i, z);
    }

    public void uninstallPackageById(String str) {
        nexAssetPackageManager.getAssetPackageManager(a.a().b()).uninstallPackageById(str);
    }

    /* access modifiers changed from: package-private */
    public boolean updateMedia(boolean z, int i, boolean z2, nexAssetPackageManager.Item item) {
        synchronized (this.m_mediaentryLock) {
            Log.d(TAG, "updateMedia(" + z + "," + i + "," + item.packageInfo().assetIdx() + ")");
            if (!z) {
                Iterator<AssetMedia> it = this.mediaEntries.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AssetMedia next = it.next();
                    if (next.id().compareTo(item.id()) == 0) {
                        this.mediaEntries.remove(next);
                        break;
                    }
                }
            } else if (!item.hidden()) {
                AssetMedia assetMedia = new AssetMedia(item);
                int unused = assetMedia.type = i;
                boolean unused2 = assetMedia.filter = z2;
                this.mediaEntries.add(assetMedia);
            }
        }
        return false;
    }
}
