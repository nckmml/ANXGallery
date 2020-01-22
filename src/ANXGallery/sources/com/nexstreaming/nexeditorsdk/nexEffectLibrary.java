package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemCategory;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemParameterType;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemType;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.assetpackage.g;
import com.nexstreaming.app.common.nexasset.assetpackage.h;
import com.nexstreaming.app.common.nexasset.assetpackage.i;
import com.nexstreaming.kminternal.kinemaster.config.EditorGlobal;
import com.nexstreaming.kminternal.kinemaster.config.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

public final class nexEffectLibrary {
    private static nexEffectLibrary sSingleton;
    private final Context mAppContext;

    @Deprecated
    public static abstract class OnInstallPluginEffectPackageAsyncListener {
        public abstract void onComplete(int i, String[] strArr);

        public abstract void onPackageLoaded(int i);
    }

    private nexEffectLibrary(Context context) {
        this.mAppContext = context;
    }

    public static nexEffectLibrary getEffectLibrary(Context context) {
        Context applicationContext = context.getApplicationContext();
        nexEffectLibrary nexeffectlibrary = sSingleton;
        if (nexeffectlibrary != null && !nexeffectlibrary.mAppContext.getPackageName().equals(applicationContext.getPackageName())) {
            sSingleton = null;
        }
        if (sSingleton == null) {
            sSingleton = new nexEffectLibrary(applicationContext);
        }
        return sSingleton;
    }

    @Deprecated
    public static String getPluginDirPath() {
        return EditorGlobal.g().getAbsolutePath();
    }

    /* access modifiers changed from: package-private */
    public boolean checkEffectID(String str) {
        boolean a = c.a(this.mAppContext).a(str, ItemCategory.transition);
        return !a ? c.a(this.mAppContext).a(str, ItemCategory.effect) : a;
    }

    public nexClipEffect findClipEffectById(String str) {
        f c = c.a(this.mAppContext).c(str);
        if (c == null || c.getCategory() != ItemCategory.effect) {
            return null;
        }
        nexClipEffect nexclipeffect = new nexClipEffect(str);
        nexclipeffect.itemMethodType = nexAssetPackageManager.getMethodType(c.getType());
        return nexclipeffect;
    }

    public nexOverlayFilter findOverlayFilterById(String str) {
        f c = c.a(this.mAppContext).c(str);
        if (c != null && c.getType() == ItemType.renderitem) {
            return new nexOverlayFilter(str);
        }
        return null;
    }

    @Deprecated
    public nexTheme findThemeById(String str) {
        return null;
    }

    public nexTransitionEffect findTransitionEffectById(String str) {
        f c = c.a(this.mAppContext).c(str);
        if (c == null || c.getCategory() != ItemCategory.transition) {
            return null;
        }
        nexTransitionEffect nextransitioneffect = new nexTransitionEffect(str);
        nextransitioneffect.itemMethodType = nexAssetPackageManager.getMethodType(c.getType());
        return nextransitioneffect;
    }

    public nexClipEffect[] getClipEffects() {
        List<? extends f> a = c.a(this.mAppContext).a(ItemCategory.effect);
        int i = 0;
        int i2 = 0;
        for (f isHidden : a) {
            if (!isHidden.isHidden()) {
                i2++;
            }
        }
        nexClipEffect[] nexclipeffectArr = new nexClipEffect[i2];
        for (f fVar : a) {
            if (!fVar.isHidden()) {
                nexclipeffectArr[i] = new nexClipEffect(fVar.getId());
                nexclipeffectArr[i].itemMethodType = nexAssetPackageManager.getMethodType(fVar.getType());
                i++;
            }
        }
        return nexclipeffectArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: int} */
    /* JADX WARNING: type inference failed for: r11v0 */
    /* JADX WARNING: type inference failed for: r11v2 */
    /* JADX WARNING: type inference failed for: r11v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005d  */
    public nexEffectOptions getEffectOptions(Context context, String str) {
        h hVar;
        Map map;
        Map map2;
        String str2 = str;
        f c = c.a(a.a().b()).c(str2);
        if (c == null) {
            return null;
        }
        nexEffectOptions nexeffectoptions = new nexEffectOptions(str2, c.getCategory() == ItemCategory.transition ? 4 : c.getCategory() == ItemCategory.effect ? 2 : (c.getCategory() == ItemCategory.filter && c.getType() == ItemType.renderitem) ? 5 : 0);
        try {
            hVar = i.a(a.a().b(), str2);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            hVar = null;
            if (hVar != null) {
            }
            return nexeffectoptions;
        } catch (IOException e2) {
            e2.printStackTrace();
            hVar = null;
            if (hVar != null) {
            }
            return nexeffectoptions;
        }
        if (hVar != null) {
            for (g next : hVar.a()) {
                Map<String, Map<String, String>> i = next.i();
                String str3 = (i == null || (map2 = i.get("label")) == null) ? null : (String) map2.get("en");
                if (next.a() == ItemParameterType.RGBA || next.a() == ItemParameterType.RGB) {
                    if (str3 == null) {
                        str3 = "Color";
                    }
                    nexeffectoptions.addColorOpt(next.e(), str3, next.b());
                } else {
                    ? r11 = 1;
                    if (next.a() == ItemParameterType.TEXT) {
                        if (str3 == null) {
                            str3 = "Text";
                        }
                        String e3 = next.e();
                        if (next.f()) {
                            r11 = 2;
                        }
                        nexeffectoptions.addTextOpt(e3, str3, r11);
                    } else if (next.a() == ItemParameterType.CHOICE) {
                        if (str3 == null) {
                            str3 = "Choice";
                        }
                        String str4 = str3;
                        List<g.a> j = next.j();
                        String[] strArr = new String[j.size()];
                        String[] strArr2 = new String[j.size()];
                        int i2 = 0;
                        for (int i3 = 0; i3 < j.size(); i3++) {
                            Map<String, Map<String, String>> a = j.get(i3).a();
                            String str5 = (a == null || (map = a.get("label")) == null) ? null : (String) map.get("en");
                            if (j.get(i3).b().compareTo(next.b()) == 0) {
                                i2 = i3;
                            }
                            if (str5 == null) {
                                str5 = new String("item" + i3);
                            }
                            strArr[i3] = str5;
                            strArr2[i3] = j.get(i3).b();
                        }
                        nexeffectoptions.addSelectOpt(next.e(), str4, strArr, strArr2, i2);
                    } else if (next.a() == ItemParameterType.RANGE) {
                        if (str3 == null) {
                            str3 = "Range";
                        }
                        nexeffectoptions.addRangeOpt(next.e(), str3, Integer.parseInt(next.b()), next.g(), next.h());
                    } else if (next.a() == ItemParameterType.SWITCH) {
                        if (str3 == null) {
                            str3 = "Switch";
                        }
                        if (next.d().compareTo(next.b()) != 0) {
                            r11 = 0;
                        }
                        nexeffectoptions.addSwitchOpt(next.e(), str3, r11);
                    }
                }
            }
        }
        return nexeffectoptions;
    }

    public nexOverlayFilter[] getOverlayFilters() {
        ArrayList arrayList = new ArrayList();
        for (f fVar : c.a(this.mAppContext).a(ItemCategory.effect)) {
            if (!fVar.isHidden() && fVar.getType() == ItemType.renderitem) {
                arrayList.add(fVar.getId());
            }
        }
        nexOverlayFilter[] nexoverlayfilterArr = new nexOverlayFilter[arrayList.size()];
        for (int i = 0; i < nexoverlayfilterArr.length; i++) {
            nexoverlayfilterArr[i] = new nexOverlayFilter((String) arrayList.get(i));
        }
        return nexoverlayfilterArr;
    }

    @Deprecated
    public nexTheme[] getThemes() {
        return new nexTheme[0];
    }

    @Deprecated
    public ArrayList<nexTheme> getThemesEx() {
        return new ArrayList<>();
    }

    public nexTransitionEffect[] getTransitionEffects() {
        List<? extends f> a = c.a(this.mAppContext).a(ItemCategory.transition);
        int i = 0;
        int i2 = 0;
        for (f isHidden : a) {
            if (!isHidden.isHidden()) {
                i2++;
            }
        }
        nexTransitionEffect[] nextransitioneffectArr = new nexTransitionEffect[i2];
        for (f fVar : a) {
            if (!fVar.isHidden()) {
                nextransitioneffectArr[i] = new nexTransitionEffect(fVar.getId());
                nextransitioneffectArr[i].itemMethodType = nexAssetPackageManager.getMethodType(fVar.getType());
                i++;
            }
        }
        return nextransitioneffectArr;
    }

    public ArrayList<nexTransitionEffect> getTransitionEffectsEx() {
        ArrayList<nexTransitionEffect> arrayList = new ArrayList<>();
        for (f fVar : c.a(this.mAppContext).a(ItemCategory.transition)) {
            if (!fVar.isHidden()) {
                arrayList.add(new nexTransitionEffect(fVar.getId()));
            }
        }
        return arrayList;
    }

    @Deprecated
    public boolean installPluginEffectPackageAsync(String[] strArr, OnInstallPluginEffectPackageAsyncListener onInstallPluginEffectPackageAsyncListener) {
        return true;
    }
}
