package com.nexstreaming.app.common.nexasset.assetpackage;

import android.content.Context;
import android.graphics.RectF;
import android.util.LruCache;
import android.util.Xml;
import com.nexstreaming.app.common.nexasset.assetpackage.g;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: XMLItemDefReader */
public class i {
    private static h a = new h() {
        public List<g> a() {
            return Collections.emptyList();
        }

        public int b() {
            return 0;
        }

        public int c() {
            return 0;
        }

        public int d() {
            return 0;
        }

        public int e() {
            return 0;
        }
    };
    private static LruCache<String, h> b = new LruCache<>(100);

    /* renamed from: com.nexstreaming.app.common.nexasset.assetpackage.i$2  reason: invalid class name */
    /* compiled from: XMLItemDefReader */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[ItemType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|27|28|29|30|(3:31|32|34)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|27|28|29|30|31|32|34) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|27|28|29|30|31|32|34) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0097 */
        static {
            b = new int[ItemParameterType.values().length];
            try {
                b[ItemParameterType.CHOICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ItemParameterType.SWITCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            b[ItemParameterType.IMAGE.ordinal()] = 3;
            b[ItemParameterType.RANGE.ordinal()] = 4;
            b[ItemParameterType.RECT.ordinal()] = 5;
            b[ItemParameterType.RGB.ordinal()] = 6;
            b[ItemParameterType.RGBA.ordinal()] = 7;
            b[ItemParameterType.TEXT.ordinal()] = 8;
            b[ItemParameterType.XY.ordinal()] = 9;
            try {
                b[ItemParameterType.XYZ.ordinal()] = 10;
            } catch (NoSuchFieldError unused3) {
            }
            a[ItemType.kedl.ordinal()] = 1;
            a[ItemType.renderitem.ordinal()] = 2;
            try {
                a[ItemType.overlay.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* compiled from: XMLItemDefReader */
    private static class a implements g {
        public ItemParameterType a;
        public String b;
        public String c;
        public String d;
        public String e;
        public int f;
        public boolean g;
        public boolean h;
        public int i;
        public int j;
        public int k;
        public RectF l;
        public Map<String, Map<String, String>> m;
        public List<g.a> n;
        public String o;

        private a() {
        }

        private String k() {
            switch (this.a) {
                case CHOICE:
                    return "selection";
                case SWITCH:
                    return "switch";
                case IMAGE:
                    return "image";
                case RANGE:
                    return "range";
                case RECT:
                    return "rect";
                case RGB:
                case RGBA:
                    return "color";
                case TEXT:
                    return "text";
                case XY:
                case XYZ:
                    return "point";
                default:
                    throw new IllegalStateException("Unknown type: " + String.valueOf(this.a));
            }
        }

        public ItemParameterType a() {
            return this.a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public String e() {
            return k() + ":" + this.e;
        }

        public boolean f() {
            return this.g;
        }

        public int g() {
            return this.i;
        }

        public int h() {
            return this.j;
        }

        public Map<String, Map<String, String>> i() {
            return this.m;
        }

        public List<g.a> j() {
            return this.n;
        }
    }

    /* compiled from: XMLItemDefReader */
    private static class b implements g.a {
        public Map<String, Map<String, String>> a;
        public String b;
        public String c;

        private b() {
        }

        public Map<String, Map<String, String>> a() {
            return this.a;
        }

        public String b() {
            return this.c;
        }
    }

    /* compiled from: XMLItemDefReader */
    private static class c implements h {
        /* access modifiers changed from: private */
        public int a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public int d;
        /* access modifiers changed from: private */
        public List<g> e;

        private c() {
        }

        public List<g> a() {
            return this.e;
        }

        public int b() {
            return this.a;
        }

        public int c() {
            return this.b;
        }

        public int d() {
            return this.c;
        }

        public int e() {
            return this.d;
        }
    }

    private static int a(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    private static RectF a(String str, RectF rectF) {
        if (str == null) {
            return rectF;
        }
        try {
            String[] split = str.split(" +");
            return split.length < 4 ? rectF : new RectF(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3]));
        } catch (NumberFormatException unused) {
            return rectF;
        }
    }

    public static h a(Context context, String str) throws XmlPullParserException, IOException {
        if (str == null) {
            return a;
        }
        h hVar = b.get(str);
        if (hVar != null) {
            return hVar;
        }
        f c2 = c.a(context).c(str);
        if (c2 == null) {
            return a;
        }
        int i = AnonymousClass2.a[c2.getType().ordinal()];
        if (i != 1 && i != 2 && i != 3) {
            return a;
        }
        h a2 = a(AssetPackageReader.a(context, c2.getPackageURI(), c2.getAssetPackage().getAssetId()).a(c2.getFilePath()));
        b.put(str, a2);
        return a2;
    }

    public static h a(InputStream inputStream) throws XmlPullParserException, IOException {
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            newPullParser.setInput(inputStream, (String) null);
            newPullParser.nextTag();
            return a(newPullParser);
        } finally {
            inputStream.close();
        }
    }

    public static h a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        c cVar = new c();
        String name = xmlPullParser.getName();
        if (xmlPullParser.getEventType() != 2 || (!name.equalsIgnoreCase("effect") && !name.equalsIgnoreCase("renderitem") && !name.equalsIgnoreCase("overlay"))) {
            throw new XmlPullParserException("expected <effect>, <overlay> or <renderitem>" + xmlPullParser.getPositionDescription());
        }
        if (name.equalsIgnoreCase("effect")) {
            if ("transition".equalsIgnoreCase(xmlPullParser.getAttributeValue((String) null, nexExportFormat.TAG_FORMAT_TYPE))) {
                int unused = cVar.a = a(xmlPullParser.getAttributeValue((String) null, "effectoffset"), 100);
                String attributeValue = xmlPullParser.getAttributeValue((String) null, "effectoverlap");
                if (attributeValue == null) {
                    attributeValue = xmlPullParser.getAttributeValue((String) null, "videooverlap");
                }
                int unused2 = cVar.b = a(attributeValue, 100);
            }
        } else if (name.equalsIgnoreCase("renderitem")) {
            if ("transition".equalsIgnoreCase(xmlPullParser.getAttributeValue((String) null, nexExportFormat.TAG_FORMAT_TYPE))) {
                int unused3 = cVar.a = a(xmlPullParser.getAttributeValue((String) null, "transitionoffset"), 100);
                int unused4 = cVar.b = a(xmlPullParser.getAttributeValue((String) null, "transitionoverlap"), 100);
            }
            int unused5 = cVar.c = a(xmlPullParser.getAttributeValue((String) null, nexExportFormat.TAG_FORMAT_WIDTH), 0);
            int unused6 = cVar.d = a(xmlPullParser.getAttributeValue((String) null, nexExportFormat.TAG_FORMAT_HEIGHT), 0);
        }
        List unused7 = cVar.e = new ArrayList();
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name2 = xmlPullParser.getName();
                if (name2.equalsIgnoreCase("parameter")) {
                    cVar.e.add(c(xmlPullParser));
                } else if (name2.equalsIgnoreCase("userfield")) {
                    g b2 = b(xmlPullParser);
                    if (b2 != null) {
                        cVar.e.add(b2);
                    }
                } else {
                    f(xmlPullParser);
                }
            }
        }
        return cVar;
    }

    private static boolean a(String str, boolean z) {
        if (str == null) {
            return z;
        }
        if ("true".equalsIgnoreCase(str)) {
            return true;
        }
        if ("false".equalsIgnoreCase(str)) {
            return false;
        }
        return z;
    }

    private static g b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, (String) null, "userfield");
        a aVar = new a();
        aVar.e = xmlPullParser.getAttributeValue((String) null, "id");
        aVar.b = xmlPullParser.getAttributeValue((String) null, "default");
        aVar.f = Reader.READ_DONE;
        aVar.g = a(xmlPullParser.getAttributeValue((String) null, "maxlines"), 1) > 1;
        aVar.h = false;
        aVar.i = 0;
        aVar.j = 100;
        aVar.k = 1;
        aVar.l = a(xmlPullParser.getAttributeValue((String) null, "step"), (RectF) null);
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "label");
        if (attributeValue != null) {
            aVar.m = new HashMap();
            aVar.m.put("label", new HashMap());
            aVar.m.get("label").put("", attributeValue);
        }
        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, nexExportFormat.TAG_FORMAT_TYPE);
        if ("selection".equalsIgnoreCase(attributeValue2)) {
            aVar.a = ItemParameterType.CHOICE;
        } else if ("color".equalsIgnoreCase(attributeValue2)) {
            aVar.a = ItemParameterType.RGB;
        } else if ("text".equalsIgnoreCase(attributeValue2)) {
            aVar.a = ItemParameterType.TEXT;
        } else if ("overlay".equalsIgnoreCase(attributeValue2)) {
            aVar.a = ItemParameterType.IMAGE;
        } else if ("undefined".equalsIgnoreCase(attributeValue2)) {
            f(xmlPullParser);
            return null;
        } else {
            throw new XmlPullParserException("unrecognized parameter type" + xmlPullParser.getPositionDescription());
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equalsIgnoreCase("fieldlabel")) {
                    if (aVar.m == null) {
                        aVar.m = new HashMap();
                    }
                    String attributeValue3 = xmlPullParser.getAttributeValue((String) null, "locale");
                    String attributeValue4 = xmlPullParser.getAttributeValue((String) null, "value");
                    f(xmlPullParser);
                    if (!(attributeValue3 == null || attributeValue4 == null)) {
                        Map map = aVar.m.get("label");
                        if (map == null) {
                            map = new HashMap();
                            aVar.m.put("label", map);
                        }
                        map.put(attributeValue3, attributeValue4);
                    }
                } else if (name.equalsIgnoreCase("option")) {
                    if (aVar.n == null) {
                        aVar.n = new ArrayList();
                    }
                    aVar.n.add(e(xmlPullParser));
                } else if (!name.equalsIgnoreCase("icon")) {
                    f(xmlPullParser);
                } else if (aVar.o == null) {
                    aVar.o = xmlPullParser.getAttributeValue((String) null, "src");
                } else {
                    throw new XmlPullParserException("multiple <icon> tags not allowed" + xmlPullParser.getPositionDescription());
                }
            }
        }
        return aVar;
    }

    private static g c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, (String) null, "parameter");
        a aVar = new a();
        aVar.e = xmlPullParser.getAttributeValue((String) null, "id");
        aVar.b = xmlPullParser.getAttributeValue((String) null, "default");
        aVar.f = a(xmlPullParser.getAttributeValue((String) null, "maxlen"), (int) Reader.READ_DONE);
        aVar.g = a(xmlPullParser.getAttributeValue((String) null, "multiline"), false);
        aVar.h = a(xmlPullParser.getAttributeValue((String) null, "private"), false);
        aVar.i = a(xmlPullParser.getAttributeValue((String) null, "minvalue"), 0);
        aVar.j = a(xmlPullParser.getAttributeValue((String) null, "maxvalue"), 100);
        aVar.k = a(xmlPullParser.getAttributeValue((String) null, "step"), 1);
        aVar.l = a(xmlPullParser.getAttributeValue((String) null, "bounds"), (RectF) null);
        String attributeValue = xmlPullParser.getAttributeValue((String) null, nexExportFormat.TAG_FORMAT_TYPE);
        if ("choice".equalsIgnoreCase(attributeValue)) {
            aVar.a = ItemParameterType.CHOICE;
        } else if ("point".equalsIgnoreCase(attributeValue)) {
            int a2 = a(xmlPullParser.getAttributeValue((String) null, "dimensions"), 2);
            if (a2 == 2) {
                aVar.a = ItemParameterType.XY;
            } else if (a2 == 3) {
                aVar.a = ItemParameterType.XYZ;
            } else {
                throw new XmlPullParserException("unsupported number of parameter dimensions: " + a2);
            }
        } else if ("color".equalsIgnoreCase(attributeValue)) {
            if (a(xmlPullParser.getAttributeValue((String) null, "alpha"), false)) {
                aVar.a = ItemParameterType.RGBA;
            } else {
                aVar.a = ItemParameterType.RGB;
            }
        } else if ("range".equalsIgnoreCase(attributeValue)) {
            aVar.a = ItemParameterType.RANGE;
        } else if ("rect".equalsIgnoreCase(attributeValue)) {
            aVar.a = ItemParameterType.RECT;
        } else if ("text".equalsIgnoreCase(attributeValue)) {
            aVar.a = ItemParameterType.TEXT;
        } else if ("switch".equalsIgnoreCase(attributeValue)) {
            aVar.a = ItemParameterType.SWITCH;
            aVar.c = xmlPullParser.getAttributeValue((String) null, "off");
            aVar.d = xmlPullParser.getAttributeValue((String) null, "on");
        } else if ("image".equalsIgnoreCase(attributeValue)) {
            aVar.a = ItemParameterType.IMAGE;
        } else {
            throw new XmlPullParserException("unrecognized parameter type" + xmlPullParser.getPositionDescription());
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equalsIgnoreCase("string")) {
                    if (aVar.m == null) {
                        aVar.m = new HashMap();
                    }
                    String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "name");
                    String attributeValue3 = xmlPullParser.getAttributeValue((String) null, "lang");
                    String g = g(xmlPullParser);
                    if (!(attributeValue2 == null || attributeValue3 == null || g == null)) {
                        Map map = aVar.m.get(attributeValue2);
                        if (map == null) {
                            map = new HashMap();
                            aVar.m.put(attributeValue2, map);
                        }
                        map.put(attributeValue3, g);
                    }
                } else if (name.equalsIgnoreCase("option")) {
                    if (aVar.n == null) {
                        aVar.n = new ArrayList();
                    }
                    aVar.n.add(d(xmlPullParser));
                } else if (!name.equalsIgnoreCase("icon")) {
                    f(xmlPullParser);
                } else if (aVar.o == null) {
                    aVar.o = xmlPullParser.getAttributeValue((String) null, "src");
                } else {
                    throw new XmlPullParserException("multiple <icon> tags not allowed" + xmlPullParser.getPositionDescription());
                }
            }
        }
        return aVar;
    }

    private static g.a d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, (String) null, "option");
        b bVar = new b();
        bVar.c = xmlPullParser.getAttributeValue((String) null, "value");
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                String name = xmlPullParser.getName();
                if (name.equalsIgnoreCase("string")) {
                    String attributeValue = xmlPullParser.getAttributeValue((String) null, "name");
                    String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "lang");
                    String g = g(xmlPullParser);
                    if (!(attributeValue == null || attributeValue2 == null || g == null)) {
                        if (bVar.a == null) {
                            bVar.a = new HashMap();
                        }
                        Map map = bVar.a.get(attributeValue);
                        if (map == null) {
                            map = new HashMap();
                            bVar.a.put(attributeValue, map);
                        }
                        map.put(attributeValue2, g);
                    }
                } else if (!name.equalsIgnoreCase("icon")) {
                    f(xmlPullParser);
                } else if (bVar.b == null) {
                    bVar.b = xmlPullParser.getAttributeValue((String) null, "src");
                } else {
                    throw new XmlPullParserException("multiple <icon> tags not allowed" + xmlPullParser.getPositionDescription());
                }
            }
        }
        return bVar;
    }

    private static g.a e(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.require(2, (String) null, "option");
        b bVar = new b();
        bVar.c = xmlPullParser.getAttributeValue((String) null, "value");
        bVar.b = xmlPullParser.getAttributeValue((String) null, "icon");
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "label");
        if (attributeValue != null) {
            bVar.a = new HashMap();
            bVar.a.put("label", new HashMap());
            bVar.a.get("label").put("", attributeValue);
        }
        while (xmlPullParser.next() != 3) {
            if (xmlPullParser.getEventType() == 2) {
                if (xmlPullParser.getName().equalsIgnoreCase("fieldlabel")) {
                    if (bVar.a == null) {
                        bVar.a = new HashMap();
                    }
                    String attributeValue2 = xmlPullParser.getAttributeValue((String) null, "locale");
                    String attributeValue3 = xmlPullParser.getAttributeValue((String) null, "value");
                    f(xmlPullParser);
                    if (!(attributeValue2 == null || attributeValue3 == null)) {
                        Map map = bVar.a.get("label");
                        if (map == null) {
                            map = new HashMap();
                            bVar.a.put("label", map);
                        }
                        map.put(attributeValue2, attributeValue3);
                    }
                } else {
                    f(xmlPullParser);
                }
            }
        }
        return bVar;
    }

    private static void f(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() == 2) {
            int i = 1;
            while (i != 0) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    i++;
                } else if (next == 3) {
                    i--;
                }
            }
            return;
        }
        throw new IllegalStateException();
    }

    private static String g(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() == 2) {
            int i = 1;
            String str = null;
            while (i != 0) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    i++;
                } else if (next == 3) {
                    i--;
                } else if (next == 4) {
                    if (str == null) {
                        str = xmlPullParser.getText();
                    } else {
                        str = str + xmlPullParser.getText();
                    }
                }
            }
            return str;
        }
        throw new IllegalStateException();
    }
}
