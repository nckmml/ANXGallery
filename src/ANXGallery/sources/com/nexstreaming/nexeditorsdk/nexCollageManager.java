package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.google.gson_nex.Gson;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.util.b;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.exception.ExpiredTimeException;
import com.nexstreaming.nexeditorsdk.nexAssetPackageManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class nexCollageManager {
    private static final String TAG = "nexCollageManager";
    private static nexCollageManager sSingleton;
    private List<Collage> collageEntries = new ArrayList();
    private Context mAppContext;
    private Context mResContext;
    private Object m_collageEntryLock = new Object();

    public static class Collage extends nexAssetPackageManager.c {
        private nexCollage collage;

        Collage() {
        }

        private Collage(nexAssetPackageManager.Item item) {
            super(item);
        }

        private boolean parsingCollage() {
            String a;
            if (this.collage.i()) {
                return true;
            }
            try {
                if (nexCollageManager.AssetPackageJsonToString(id()) == null || (a = this.collage.a(new JSONObject(nexCollageManager.AssetPackageJsonToString(id())))) == null) {
                    return true;
                }
                Log.d(nexCollageManager.TAG, "collage parsing error" + a);
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d(nexCollageManager.TAG, "Collage parsing error" + e.getMessage());
                return false;
            }
        }

        protected static Collage promote(nexAssetPackageManager.Item item) {
            if (item.category() == nexAssetPackageManager.Category.collage || item.category() == nexAssetPackageManager.Category.staticcollage || item.category() == nexAssetPackageManager.Category.dynamiccollage) {
                return new Collage(item);
            }
            return null;
        }

        public boolean applyCollage2Project(nexProject nexproject, nexEngine nexengine, int i, Context context) throws ExpiredTimeException {
            if (nexAssetPackageManager.checkExpireAsset(packageInfo())) {
                throw new ExpiredTimeException(id());
            } else if (this.collage == null || !parsingCollage()) {
                return false;
            } else {
                String a = this.collage.a(nexproject, nexengine, i, context, false);
                if (a == null) {
                    return true;
                }
                Log.d(nexCollageManager.TAG, "applyCollage2Project failed with " + a);
                return false;
            }
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Category category() {
            return super.category();
        }

        /* access modifiers changed from: protected */
        public nexCollage getCollage() {
            return this.collage;
        }

        public nexCollageInfo getCollageInfos(float f, float f2) {
            if (this.collage != null && parsingCollage()) {
                for (nexCollageTitleInfo next : this.collage.g()) {
                    if (next.a() && next.a(f, f2)) {
                        return next;
                    }
                }
                for (a next2 : this.collage.f()) {
                    if (!next2.a() && next2.a(f, f2)) {
                        return next2;
                    }
                }
            }
            return null;
        }

        public List<nexCollageInfo> getCollageInfos() {
            if (this.collage == null || !parsingCollage()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (a next : this.collage.f()) {
                if (!next.a()) {
                    arrayList.add(next);
                }
            }
            for (nexCollageTitleInfo next2 : this.collage.g()) {
                if (next2.a()) {
                    arrayList.add(next2);
                }
            }
            return arrayList;
        }

        public int getDuration() {
            if (this.collage == null || !parsingCollage()) {
                return 0;
            }
            return this.collage.c();
        }

        public int getEditTime() {
            if (this.collage == null || !parsingCollage()) {
                return 0;
            }
            return (int) (((float) this.collage.c()) * this.collage.a());
        }

        public float getRatio() {
            if (this.collage == null || !parsingCollage()) {
                return 0.0f;
            }
            return this.collage.e();
        }

        public int getRatioMode() {
            if (this.collage == null || !parsingCollage()) {
                return 1;
            }
            float e = this.collage.e();
            if (e == 1.0f) {
                return 2;
            }
            if (e == 1.7777778f) {
                return 1;
            }
            if (e == 0.5625f) {
                return 3;
            }
            if (e == 2.0f) {
                return 4;
            }
            if (e == 0.5f) {
                return 5;
            }
            if (e == 1.3333334f) {
                return 6;
            }
            return e == 0.75f ? 7 : 0;
        }

        public int getSourceCount() {
            if (id().contains(".sc.")) {
                String id = id();
                String substring = id.substring(id.indexOf(".sc.") + 4, id.length());
                if (substring != null && substring.length() > 0) {
                    return Integer.parseInt(substring);
                }
            }
            if (this.collage == null || !parsingCollage()) {
                return 0;
            }
            return this.collage.b();
        }

        public String[] getSupportedLocales() {
            return packageInfo() == null ? new String[0] : packageInfo().getSupportedLocales();
        }

        public CollageType getType() {
            nexAssetPackageManager.ItemMethodType type = type();
            if (type == nexAssetPackageManager.ItemMethodType.ItemDynamicCollage) {
                return CollageType.DynamicCollage;
            }
            if (type == nexAssetPackageManager.ItemMethodType.ItemStaticCollage) {
                return CollageType.StaticCollage;
            }
            if (this.collage == null || !parsingCollage()) {
                return null;
            }
            return this.collage.d();
        }

        public /* bridge */ /* synthetic */ boolean hidden() {
            return super.hidden();
        }

        public /* bridge */ /* synthetic */ Bitmap icon() {
            return super.icon();
        }

        public String id() {
            return super.id();
        }

        public /* bridge */ /* synthetic */ boolean isDelete() {
            return super.isDelete();
        }

        public boolean isFrameCollage() {
            nexCollage nexcollage = this.collage;
            if (nexcollage == null) {
                return false;
            }
            return nexcollage.h();
        }

        /* access modifiers changed from: protected */
        public boolean loadCollage2Project(nexProject nexproject, nexEngine nexengine, int i, Context context) {
            if (this.collage == null || !parsingCollage()) {
                return false;
            }
            String a = this.collage.a(nexproject, nexengine, i, context, true);
            if (a == null) {
                return true;
            }
            Log.d(nexCollageManager.TAG, "applyCollage2Project failed with " + a);
            return false;
        }

        public boolean loadFromSaveData(Context context, nexEngine nexengine, nexProject nexproject, String str) {
            if (this.collage == null) {
                return false;
            }
            nexSaveDataFormat nexsavedataformat = (nexSaveDataFormat) new Gson().fromJson(str, nexSaveDataFormat.class);
            if (nexsavedataformat.collage == null || nexsavedataformat.project == null) {
                return false;
            }
            this.collage.a(nexsavedataformat.collage);
            loadCollage2Project(nexproject, nexengine, getDuration(), context);
            nexengine.setProject(nexproject);
            return true;
        }

        public String name(String str) {
            String assetName = packageInfo().assetName(str);
            return assetName != null ? assetName : super.name(str);
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Asset packageInfo() {
            return super.packageInfo();
        }

        public boolean restoreBGM() {
            nexCollage nexcollage = this.collage;
            if (nexcollage == null) {
                return false;
            }
            return nexcollage.a((String) null);
        }

        public String saveToString() {
            if (this.collage == null) {
                return null;
            }
            return new Gson().toJson((Object) this.collage.j());
        }

        /* access modifiers changed from: protected */
        public void setCollage(nexCollage nexcollage) {
            this.collage = nexcollage;
        }

        public boolean swapDrawInfoClip(nexCollageInfoDraw nexcollageinfodraw, nexCollageInfoDraw nexcollageinfodraw2) {
            nexCollage nexcollage = this.collage;
            if (nexcollage == null) {
                return false;
            }
            return nexcollage.a((a) nexcollageinfodraw, (a) nexcollageinfodraw2);
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

    public enum CollageType {
        StaticCollage,
        DynamicCollage,
        ALL
    }

    private nexCollageManager(Context context, Context context2) {
        this.mAppContext = context;
        this.mResContext = context2;
    }

    /* access modifiers changed from: private */
    public static String AssetPackageJsonToString(String str) {
        f c = c.a().c(str);
        if (c == null) {
            Log.e(TAG, "AssetPackageJsonToString info fail=" + str);
            return null;
        } else if (c.a().a(c.getAssetPackage())) {
            Log.e(TAG, "AssetPackageJsonToString expire id=" + str);
            return null;
        } else {
            try {
                AssetPackageReader a = AssetPackageReader.a(a.a().b(), c.getPackageURI(), c.getAssetPackage().getAssetId());
                try {
                    InputStream a2 = a.a(c.getFilePath());
                    if (a2 != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a2));
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                sb.append(readLine);
                                sb.append("\n");
                            } else {
                                a2.close();
                                String sb2 = sb.toString();
                                b.a(a);
                                return sb2;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    b.a(a);
                    throw th;
                }
                b.a(a);
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public static nexCollageManager getCollageManager() {
        return sSingleton;
    }

    public static nexCollageManager getCollageManager(Context context, Context context2) {
        nexCollageManager nexcollagemanager = sSingleton;
        if (nexcollagemanager != null && !nexcollagemanager.mAppContext.getPackageName().equals(context.getPackageName())) {
            sSingleton = null;
        }
        if (sSingleton == null) {
            sSingleton = new nexCollageManager(context, context2);
        }
        return sSingleton;
    }

    private boolean resolveCollage(boolean z) {
        synchronized (this.m_collageEntryLock) {
            this.collageEntries.clear();
            for (nexAssetPackageManager.Item next : nexAssetPackageManager.getAssetPackageManager(this.mAppContext).getInstalledAssetItems(nexAssetPackageManager.Category.collage)) {
                if (!next.hidden()) {
                    if (z) {
                        nexAssetPackageManager.getAssetPackageManager(this.mAppContext);
                        if (nexAssetPackageManager.checkExpireAsset(next.packageInfo())) {
                        }
                    }
                    Collage promote = Collage.promote(next);
                    if (promote != null) {
                        promote.setCollage(new nexCollage());
                        this.collageEntries.add(promote);
                    }
                }
            }
            for (nexAssetPackageManager.Item next2 : nexAssetPackageManager.getAssetPackageManager(this.mAppContext).getInstalledAssetItems(nexAssetPackageManager.Category.staticcollage)) {
                if (!next2.hidden()) {
                    if (z) {
                        nexAssetPackageManager.getAssetPackageManager(this.mAppContext);
                        if (nexAssetPackageManager.checkExpireAsset(next2.packageInfo())) {
                        }
                    }
                    Collage promote2 = Collage.promote(next2);
                    if (promote2 != null) {
                        promote2.setCollage(new nexCollage());
                        this.collageEntries.add(promote2);
                    }
                }
            }
            for (nexAssetPackageManager.Item next3 : nexAssetPackageManager.getAssetPackageManager(this.mAppContext).getInstalledAssetItems(nexAssetPackageManager.Category.dynamiccollage)) {
                if (!next3.hidden()) {
                    if (z) {
                        nexAssetPackageManager.getAssetPackageManager(this.mAppContext);
                        if (nexAssetPackageManager.checkExpireAsset(next3.packageInfo())) {
                        }
                    }
                    Collage promote3 = Collage.promote(next3);
                    if (promote3 != null) {
                        promote3.setCollage(new nexCollage());
                        this.collageEntries.add(promote3);
                    }
                }
            }
        }
        return true;
    }

    public int findNewPackages() {
        return nexAssetPackageManager.getAssetPackageManager(a.a().b()).findNewPackages();
    }

    public Collage getCollage(String str) {
        synchronized (this.m_collageEntryLock) {
            for (Collage next : this.collageEntries) {
                if (next.id() != null && next.id().compareTo(str) == 0) {
                    return next;
                }
            }
            return null;
        }
    }

    public String getCollageItemId(boolean z, int i, int i2) {
        if (z) {
            loadCollage();
        }
        if (i < 2) {
            return null;
        }
        for (Collage next : this.collageEntries) {
            if (next.packageInfo().assetIdx() == i && next.getSourceCount() == i2) {
                return next.id();
            }
        }
        return null;
    }

    public List<Collage> getCollages() {
        ArrayList arrayList = new ArrayList();
        for (Collage add : this.collageEntries) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public List<Collage> getCollages(int i, CollageType collageType) {
        ArrayList arrayList = new ArrayList();
        for (Collage next : this.collageEntries) {
            if (next.getSourceCount() == i) {
                if (collageType == CollageType.ALL) {
                    arrayList.add(next);
                }
                if (next.getType() == collageType) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public List<Collage> getCollages(CollageType collageType) {
        ArrayList arrayList = new ArrayList();
        for (Collage next : this.collageEntries) {
            if (collageType == CollageType.ALL) {
                arrayList.add(next);
            }
            if (next.getType() == collageType) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void installPackagesAsync(int i, nexAssetPackageManager.OnInstallPackageListener onInstallPackageListener) {
        nexAssetPackageManager.getAssetPackageManager(a.a().b()).installPackagesAsync(i, onInstallPackageListener);
    }

    public void installPackagesAsync(nexAssetPackageManager.OnInstallPackageListener onInstallPackageListener) {
        nexAssetPackageManager.getAssetPackageManager(a.a().b()).installPackagesAsync(onInstallPackageListener);
    }

    public boolean isInstallingPackages() {
        return nexAssetPackageManager.getAssetPackageManager(a.a().b()).isInstallingPackages();
    }

    public boolean loadCollage() {
        return resolveCollage(false);
    }

    public boolean loadCollage(boolean z) {
        return resolveCollage(z);
    }

    public void uninstallPackageById(String str) {
        nexAssetPackageManager.getAssetPackageManager(a.a().b()).uninstallPackageById(str);
    }

    /* access modifiers changed from: package-private */
    public boolean updateCollage(boolean z, nexAssetPackageManager.Item item) {
        boolean z2;
        synchronized (this.m_collageEntryLock) {
            z2 = false;
            if (!z) {
                ArrayList arrayList = new ArrayList();
                for (Collage next : this.collageEntries) {
                    if (next.packageInfo().assetIdx() == item.packageInfo().assetIdx()) {
                        arrayList.add(next);
                        z2 = true;
                    }
                }
                this.collageEntries.removeAll(arrayList);
            } else if (item.hidden()) {
                return false;
            } else {
                Collage promote = Collage.promote(item);
                if (promote != null) {
                    try {
                        if (AssetPackageJsonToString(promote.id()) != null) {
                            JSONObject jSONObject = new JSONObject(AssetPackageJsonToString(promote.id()));
                            nexCollage nexcollage = new nexCollage();
                            String a = nexcollage.a(jSONObject);
                            if (a != null) {
                                Log.d(TAG, "collage parse error" + a);
                            } else {
                                promote.setCollage(nexcollage);
                                this.collageEntries.add(promote);
                                z2 = true;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "json create failed" + e.getMessage());
                    }
                }
            }
        }
        return z2;
    }
}
