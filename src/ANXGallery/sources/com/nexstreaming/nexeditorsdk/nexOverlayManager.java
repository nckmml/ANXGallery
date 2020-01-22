package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.util.b;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.nexAssetPackageManager;
import com.nexstreaming.nexeditorsdk.nexOverlayTitle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class nexOverlayManager {
    private static final String TAG = "nexOverlayManager";
    /* access modifiers changed from: private */
    public static Context mAppContext = null;
    /* access modifiers changed from: private */
    public static boolean sAutoAspectSelect = true;
    private static nexOverlayManager sSingleton;
    private List<Overlay> externalView_overlayEntries = null;
    private Object m_overlayEntryLock = new Object();
    private List<Overlay> overlayEntries = new ArrayList();
    private nexOverlayTitle overlayTitle = null;

    public static class Overlay extends nexAssetPackageManager.c {
        private float[] aspect;
        private String[] ids;
        private int maxAspect;
        private int selectAspect;
        private int tag;

        private Overlay(nexAssetPackageManager.Item item) {
            super(item);
        }

        /* access modifiers changed from: private */
        public static Overlay promote(nexAssetPackageManager.Item item) {
            if (item.category() == nexAssetPackageManager.Category.overlay && item.type() == nexAssetPackageManager.ItemMethodType.ItemTemplate) {
                return new Overlay(item);
            }
            return null;
        }

        private void selectAspect() {
            float aspectRatio = nexApplicationConfig.getAspectRatio();
            float f = 3.0f;
            for (int i = 0; i < this.maxAspect; i++) {
                float f2 = aspectRatio - this.aspect[i];
                if (f2 < 0.0f) {
                    f2 *= -1.0f;
                }
                if (f > f2) {
                    this.selectAspect = i;
                    f = f2;
                }
            }
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Category category() {
            return super.category();
        }

        public Bitmap getIcon() {
            return icon();
        }

        public String getId() {
            return id();
        }

        public String getName(String str) {
            return name(str);
        }

        public float getRatio() {
            if (nexOverlayManager.sAutoAspectSelect) {
                selectAspect();
            }
            return this.aspect[this.selectAspect];
        }

        public float[] getSupportedAspects() {
            float[] fArr = new float[this.maxAspect];
            for (int i = 0; i < fArr.length; i++) {
                fArr[i] = this.aspect[i];
            }
            return fArr;
        }

        public /* bridge */ /* synthetic */ String[] getSupportedLocales() {
            return super.getSupportedLocales();
        }

        public int getTag() {
            return this.tag;
        }

        public Bitmap getThumbnail() {
            return thumbnail();
        }

        public /* bridge */ /* synthetic */ boolean hidden() {
            return super.hidden();
        }

        public /* bridge */ /* synthetic */ Bitmap icon() {
            return super.icon();
        }

        public String id() {
            if (nexOverlayManager.sAutoAspectSelect) {
                selectAspect();
            }
            return this.ids[this.selectAspect];
        }

        public boolean includeSubtitle() {
            String[] strArr = this.ids;
            if (strArr != null) {
                for (String contains : strArr) {
                    if (contains.contains("sub")) {
                        return true;
                    }
                }
            }
            return false;
        }

        public /* bridge */ /* synthetic */ boolean isDelete() {
            return super.isDelete();
        }

        public String name(String str) {
            String assetName = packageInfo().assetName(str);
            return assetName != null ? assetName : super.name(str);
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Asset packageInfo() {
            return super.packageInfo();
        }

        public void selectAspect(int i) {
            if (this.maxAspect > i) {
                this.selectAspect = i;
            }
        }

        /* access modifiers changed from: package-private */
        public void setAspect(String str) {
            if (this.maxAspect < 5) {
                if (this.aspect == null) {
                    this.aspect = new float[5];
                    this.ids = new String[5];
                }
                if (str.contains("9v16")) {
                    this.aspect[this.maxAspect] = 0.5625f;
                } else if (str.contains("2v1")) {
                    this.aspect[this.maxAspect] = 2.0f;
                } else if (str.contains("1v2")) {
                    this.aspect[this.maxAspect] = 0.5f;
                } else if (str.contains("1v1")) {
                    this.aspect[this.maxAspect] = 1.0f;
                } else {
                    this.aspect[this.maxAspect] = 1.7777778f;
                }
                String[] strArr = this.ids;
                int i = this.maxAspect;
                strArr[i] = str;
                this.maxAspect = i + 1;
                selectAspect();
            }
        }

        public void setTag(int i) {
            this.tag = i;
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

    public static class nexTitleInfo {
        private TextPaint cachePaint;
        private boolean changeText;
        private String fontID;
        private int fontSize;
        private String group;
        private int id;
        private int overlayHeight;
        private int overlayWidth;
        private int realMaxFontSize;
        private String text;
        private String textDesc;
        private int textMaxLen;

        private nexTitleInfo(int i, int i2, String str) {
            this.id = i;
            this.textMaxLen = i2;
            this.textDesc = str;
        }

        private synchronized void calcFontSize() {
            if (this.changeText || this.realMaxFontSize <= 0) {
                if (this.cachePaint == null) {
                    this.cachePaint = new TextPaint();
                } else {
                    this.cachePaint.reset();
                }
                int i = this.overlayHeight - 4;
                this.cachePaint.setTextSize((float) i);
                this.cachePaint.setTypeface(nexFont.getTypeface(a.a().b(), this.fontID));
                this.cachePaint.setAntiAlias(true);
                this.cachePaint.setStyle(Paint.Style.FILL_AND_STROKE);
                while (true) {
                    if (((int) this.cachePaint.getFontSpacing()) < this.overlayHeight - 4) {
                        break;
                    } else if (i < 10) {
                        break;
                    } else {
                        i -= 10;
                        this.cachePaint.setTextSize((float) i);
                    }
                }
                this.realMaxFontSize = i;
                this.changeText = false;
            }
        }

        public String getFontID() {
            return this.fontID;
        }

        public int getFontSize() {
            return this.fontSize;
        }

        /* access modifiers changed from: package-private */
        public String getGroup() {
            return this.group;
        }

        public int getId() {
            return this.id;
        }

        public int getMaxFontSize() {
            calcFontSize();
            return this.realMaxFontSize;
        }

        public int getOverlayHeight() {
            return this.overlayHeight;
        }

        public int getOverlayWidth() {
            return this.overlayWidth;
        }

        public String getText() {
            return this.text;
        }

        public String getTextDesc() {
            return this.textDesc;
        }

        public synchronized int getTextHeight(String str) {
            if (this.cachePaint == null) {
                this.cachePaint = new TextPaint();
            } else {
                this.cachePaint.reset();
            }
            this.cachePaint.setTextSize((float) this.fontSize);
            this.cachePaint.setTypeface(nexFont.getTypeface(a.a().b(), this.fontID));
            this.cachePaint.setAntiAlias(true);
            this.cachePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            return (int) this.cachePaint.getFontSpacing();
        }

        public int getTextMaxLen() {
            return this.textMaxLen;
        }

        public int getTextWidth(String str) {
            TextPaint textPaint = new TextPaint();
            textPaint.setTypeface(nexFont.getTypeface(nexOverlayManager.mAppContext, this.fontID));
            textPaint.setTextSize((float) this.fontSize);
            return (int) textPaint.measureText(str);
        }

        public void setFontID(String str) {
            this.fontID = str;
            this.changeText = true;
        }

        public void setFontSize(int i) {
            this.fontSize = i;
            this.changeText = true;
        }

        /* access modifiers changed from: package-private */
        public void setGroup(String str) {
            this.group = str;
        }

        /* access modifiers changed from: package-private */
        public void setOverlayResolution(int i, int i2) {
            this.overlayWidth = i;
            this.overlayHeight = i2;
        }

        public void setText(String str) {
            this.text = str;
        }
    }

    private nexOverlayManager(Context context, Context context2) {
        mAppContext = context;
    }

    private String AssetPackageJsonToString(String str) {
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

    private Overlay getAssetOverlay(String str) {
        for (Overlay next : this.overlayEntries) {
            if (next.packageInfo().assetId().compareTo(str) == 0) {
                return next;
            }
        }
        return null;
    }

    public static nexOverlayManager getOverlayManager(Context context, Context context2) {
        if (sSingleton != null && !mAppContext.getPackageName().equals(context.getPackageName())) {
            sSingleton = null;
        }
        if (sSingleton == null) {
            sSingleton = new nexOverlayManager(context, context2);
        }
        return sSingleton;
    }

    private void resolveOverlay() {
        synchronized (this.m_overlayEntryLock) {
            this.overlayEntries.clear();
            for (nexAssetPackageManager.Item next : nexAssetPackageManager.getAssetPackageManager(a.a().b()).getInstalledAssetItems(nexAssetPackageManager.Category.overlay)) {
                if (next.type() == nexAssetPackageManager.ItemMethodType.ItemTemplate) {
                    Overlay assetOverlay = getAssetOverlay(next.packageInfo().assetId());
                    if (assetOverlay == null) {
                        assetOverlay = Overlay.promote(next);
                        this.overlayEntries.add(assetOverlay);
                    }
                    assetOverlay.setAspect(next.id());
                }
            }
        }
    }

    public static void setAutoSelectFromAspect(boolean z) {
        sAutoAspectSelect = z;
    }

    public boolean applyOverlayToProjectById(nexProject nexproject, String str, List<nexTitleInfo> list) {
        Log.d(TAG, "applyOverlayToProjectById with : " + str);
        parseOverlay(str, (List<nexTitleInfo>) null);
        nexOverlayTitle nexoverlaytitle = this.overlayTitle;
        if (nexoverlaytitle == null) {
            Log.d(TAG, "Overlay data did not exist");
            return false;
        } else if (nexoverlaytitle.a(mAppContext, nexproject, list) == null) {
            return true;
        } else {
            Log.d(TAG, "Overlay apply failed");
            return false;
        }
    }

    public boolean clearOverlayToProject(nexProject nexproject) {
        nexOverlayTitle nexoverlaytitle = this.overlayTitle;
        if (nexoverlaytitle == null) {
            Log.d(TAG, "Overlay data did not exist");
            return false;
        } else if (nexoverlaytitle.a(nexproject) == null) {
            return true;
        } else {
            Log.d(TAG, "Overlay apply failed");
            return false;
        }
    }

    public List<Overlay> getOverlays() {
        Log.d(TAG, "getOverlays total : " + this.overlayEntries.size());
        if (this.externalView_overlayEntries == null) {
            this.externalView_overlayEntries = Collections.unmodifiableList(this.overlayEntries);
        }
        return this.externalView_overlayEntries;
    }

    public List<Overlay> getOverlays(boolean z) {
        ArrayList arrayList;
        Log.d(TAG, "getOverlays total : " + this.overlayEntries.size() + " match : " + z);
        synchronized (this.m_overlayEntryLock) {
            arrayList = new ArrayList();
            float aspectRatio = nexApplicationConfig.getAspectRatio();
            for (Overlay next : this.overlayEntries) {
                if (!z) {
                    arrayList.add(next);
                } else if (next.getRatio() == aspectRatio) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public void loadOverlay() {
        resolveOverlay();
    }

    public boolean parseOverlay(String str, final List<nexTitleInfo> list) {
        this.overlayTitle = new nexOverlayTitle();
        if (str != null) {
            Log.d(TAG, "parseOverlay with : " + str);
        }
        try {
            if (AssetPackageJsonToString(str) != null) {
                String a = this.overlayTitle.a(new JSONObject(AssetPackageJsonToString(str)), (nexOverlayTitle.TitleInfoListener) new nexOverlayTitle.TitleInfoListener() {
                    public void OnTitleInfoListener(int i, String str, int i2, String str2, int i3, String str3, String str4, int i4, int i5) {
                        if (list != null) {
                            nexTitleInfo nextitleinfo = new nexTitleInfo(i, i3, str3);
                            nextitleinfo.setText(str2);
                            nextitleinfo.setFontID(str);
                            nextitleinfo.setFontSize(i2);
                            nextitleinfo.setGroup(str4);
                            nextitleinfo.setOverlayResolution(i4, i5);
                            list.add(nextitleinfo);
                        }
                    }
                });
                if (a == null) {
                    return true;
                }
                Log.d(TAG, "json parse failed" + a);
                list.clear();
                return false;
            }
            Log.d(TAG, "json create failed overlayid is null");
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "json create failed" + e.getMessage());
            return false;
        }
    }

    public void uninstallPackageById(String str) {
        nexAssetPackageManager.getAssetPackageManager(a.a().b()).uninstallPackageById(str);
    }

    /* access modifiers changed from: package-private */
    public boolean updateOverlayTitle(boolean z, nexAssetPackageManager.Item item) {
        synchronized (this.m_overlayEntryLock) {
            if (!z) {
                Iterator<Overlay> it = this.overlayEntries.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Overlay next = it.next();
                    if (next.packageInfo().assetId().compareTo(item.packageInfo().assetId()) == 0) {
                        this.overlayEntries.remove(next);
                        break;
                    }
                }
            } else {
                Overlay assetOverlay = getAssetOverlay(item.packageInfo().assetId());
                if (assetOverlay == null) {
                    assetOverlay = Overlay.promote(item);
                    this.overlayEntries.add(assetOverlay);
                }
                assetOverlay.setAspect(item.id());
            }
        }
        return false;
    }
}
