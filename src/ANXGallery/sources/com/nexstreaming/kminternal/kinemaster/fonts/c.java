package com.nexstreaming.kminternal.kinemaster.fonts;

import android.graphics.Typeface;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemType;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.util.b;
import com.nexstreaming.kminternal.kinemaster.fonts.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: FontManager */
public class c {
    private static c a;
    private List<b> b = null;
    private Map<String, Font> c;

    /* compiled from: FontManager */
    private static class a implements b {
        private final String a;
        private final int b;
        private final List<Font> c = new ArrayList();

        a(String str, int i) {
            this.a = str;
            this.b = i;
        }

        public List<Font> a() {
            return Collections.unmodifiableList(this.c);
        }

        /* access modifiers changed from: package-private */
        public List<Font> b() {
            return this.c;
        }
    }

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    private static Map<String, Integer> d() {
        HashMap hashMap = new HashMap();
        hashMap.put("latin", 0);
        hashMap.put("hangul", 0);
        hashMap.put("chs", 0);
        hashMap.put("cht", 0);
        hashMap.put("japanese", 0);
        hashMap.put("android", 0);
        return hashMap;
    }

    private void e() {
        Map<String, Integer> d = d();
        HashMap hashMap = new HashMap();
        for (Font next : f().values()) {
            String b2 = next.b();
            a aVar = (a) hashMap.get(b2);
            if (aVar == null) {
                Integer num = d.get(b2);
                if (num == null) {
                    num = 0;
                }
                a aVar2 = new a(b2, num.intValue());
                hashMap.put(b2, aVar2);
                aVar = aVar2;
            }
            aVar.b().add(next);
        }
        this.b = Collections.unmodifiableList(new ArrayList(hashMap.values()));
    }

    private Map<String, Font> f() {
        if (this.c == null) {
            List<Font> a2 = a.a();
            this.c = new HashMap();
            for (Font next : a2) {
                this.c.put(next.a(), next);
            }
        }
        return this.c;
    }

    public boolean a(String str) {
        if (f().get(str) != null) {
            return true;
        }
        f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(str);
        return c2 != null && c2.getType() == ItemType.font;
    }

    public Typeface b(String str) {
        if (str != null && str.trim().length() >= 1) {
            String substring = str.substring(str.indexOf(47) + 1);
            Font font = f().get(substring);
            if (font != null) {
                try {
                    return font.b(com.nexstreaming.kminternal.kinemaster.config.a.a().b());
                } catch (Font.TypefaceLoadException unused) {
                    return null;
                }
            } else {
                Log.d("FontManager", "Get typeface: " + substring);
                f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(substring);
                if (c2 == null || c2.getType() != ItemType.font) {
                    Log.w("FontManager", "Typeface not found: " + substring);
                } else if (com.nexstreaming.app.common.nexasset.assetpackage.c.a().a(c2.getAssetPackage())) {
                    Log.w("FontManager", "Typeface expire: " + substring);
                    return null;
                } else {
                    try {
                        AssetPackageReader a2 = AssetPackageReader.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), c2.getPackageURI(), c2.getAssetPackage().getAssetId());
                        try {
                            return a2.d(c2.getFilePath());
                        } catch (AssetPackageReader.LocalPathNotAvailableException e) {
                            Log.e("FontManager", "Error loading typeface: " + substring, e);
                            return null;
                        } finally {
                            b.a(a2);
                        }
                    } catch (IOException e2) {
                        Log.e("FontManager", "Error loading typeface: " + substring, e2);
                        return null;
                    }
                }
            }
        }
        return null;
    }

    public List<b> b() {
        if (this.b == null) {
            e();
        }
        return this.b;
    }

    public void c() {
        this.c = null;
    }
}
