package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemCategory;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.store.AssetLocalInstallDB;
import com.nexstreaming.app.common.util.n;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.kminternal.kinemaster.fonts.Font;
import com.nexstreaming.kminternal.kinemaster.fonts.b;
import com.nexstreaming.kminternal.kinemaster.fonts.c;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class nexFont {
    private static final String LOG_TAG = "nexFont";
    private static List<nexFont> list = new ArrayList();
    private static boolean s_update;
    private boolean builtin;
    private Font font;
    private String sample;
    private boolean system;

    private nexFont(boolean z, Font font2, String str, boolean z2) {
        this.builtin = z;
        this.font = font2;
        this.sample = str;
        this.system = z2;
    }

    static void checkUpdate() {
        if (s_update) {
            s_update = false;
            reload();
        }
    }

    public static void clearBuiltinFontsCache() {
        c.a().c();
    }

    public static nexFont getFont(String str) {
        checkUpdate();
        for (nexFont next : list) {
            if (next.getId().compareTo(str) == 0) {
                return next;
            }
        }
        return null;
    }

    public static String[] getFontIds() {
        checkUpdate();
        String[] strArr = new String[list.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = list.get(i).getId();
        }
        return strArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041 A[SYNTHETIC, Splitter:B:18:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0052 A[SYNTHETIC, Splitter:B:26:0x0052] */
    private static String getLocalPath(f fVar) {
        AssetPackageReader assetPackageReader;
        AssetPackageReader assetPackageReader2 = null;
        try {
            assetPackageReader = AssetPackageReader.a(a.a().b(), fVar.getPackageURI(), fVar.getId());
            try {
                String absolutePath = assetPackageReader.c(fVar.getFilePath()).getAbsolutePath();
                if (assetPackageReader != null) {
                    try {
                        assetPackageReader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, e.getMessage(), e);
                    }
                }
                return absolutePath;
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.e(LOG_TAG, e.getMessage(), e);
                    if (assetPackageReader != null) {
                        try {
                            assetPackageReader.close();
                        } catch (IOException e3) {
                            Log.e(LOG_TAG, e3.getMessage(), e3);
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    assetPackageReader2 = assetPackageReader;
                    if (assetPackageReader2 != null) {
                    }
                    throw th;
                }
            }
        } catch (Exception e4) {
            e = e4;
            assetPackageReader = null;
            Log.e(LOG_TAG, e.getMessage(), e);
            if (assetPackageReader != null) {
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (assetPackageReader2 != null) {
                try {
                    assetPackageReader2.close();
                } catch (IOException e5) {
                    Log.e(LOG_TAG, e5.getMessage(), e5);
                }
            }
            throw th;
        }
    }

    public static List<nexFont> getPresetList() {
        if (list.size() == 0) {
            for (b a : c.a().b()) {
                for (Font next : a.a()) {
                    list.add(new nexFont(true, next, next.a((Context) null), next.a().startsWith("system")));
                }
            }
            for (f fVar : com.nexstreaming.app.common.nexasset.assetpackage.c.a().a(ItemCategory.font)) {
                if (!fVar.isHidden()) {
                    String valueOf = String.valueOf((int) fVar.getAssetPackage().getAssetSubCategory().getSubCategoryId());
                    if (valueOf == null) {
                        valueOf = "asset";
                    }
                    String sampleText = fVar.getSampleText();
                    if (sampleText == null || sampleText.trim().length() < 1) {
                        sampleText = n.a(a.a().b(), fVar.getLabel());
                    }
                    if (sampleText == null) {
                        sampleText = fVar.getId();
                    }
                    Log.d(LOG_TAG, "asset font id=" + fVar.getId() + ", loacalPath=" + getLocalPath(fVar));
                    list.add(new nexFont(fVar.getPackageURI().contains(AssetLocalInstallDB.getInstalledAssetPath()) ^ true, new Font(fVar.getId(), valueOf, new File(getLocalPath(fVar)), sampleText), sampleText, false));
                }
            }
        }
        return list;
    }

    public static Typeface getTypeface(Context context, String str) {
        return c.a().b(str);
    }

    static boolean isLoadedFont(String str) {
        if (c.a().a(str)) {
            return true;
        }
        f c = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(str);
        return c != null && c.getCategory() == ItemCategory.font;
    }

    static void needUpdate() {
        s_update = true;
    }

    public static void reload() {
        list.clear();
        getPresetList();
    }

    public String getId() {
        return this.font.a();
    }

    public Bitmap getSampleImage(Context context) {
        return this.font.c(context);
    }

    public String getSampleText() {
        return this.sample;
    }

    public Typeface getTypeFace() {
        return c.a().b(getId());
    }

    public boolean isBuiltinFont() {
        return this.builtin;
    }

    public boolean isSystemFont() {
        return this.system;
    }
}
