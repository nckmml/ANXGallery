package com.larvalabs.svgandroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Log;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SVGParser {

    private enum Prefix {
        matrix,
        translate,
        scale,
        skewX,
        skewY,
        rotate
    }

    private static class a {
        String a;
        String b;
        boolean c;
        float d;
        float e;
        float f;
        float g;
        float h;
        float i;
        float j;
        ArrayList<Float> k;
        ArrayList<Integer> l;
        Matrix m;

        private a() {
            this.k = new ArrayList<>();
            this.l = new ArrayList<>();
            this.m = null;
        }

        public a a(a aVar) {
            a aVar2 = new a();
            aVar2.a = aVar.a;
            aVar2.b = this.a;
            aVar2.c = aVar.c;
            aVar2.d = aVar.d;
            aVar2.f = aVar.f;
            aVar2.e = aVar.e;
            aVar2.g = aVar.g;
            aVar2.h = aVar.h;
            aVar2.i = aVar.i;
            aVar2.j = aVar.j;
            aVar2.k = this.k;
            aVar2.l = this.l;
            aVar2.m = this.m;
            Matrix matrix = aVar.m;
            if (matrix != null) {
                Matrix matrix2 = this.m;
                if (matrix2 == null) {
                    aVar2.m = matrix;
                } else {
                    Matrix matrix3 = new Matrix(matrix2);
                    matrix3.preConcat(aVar.m);
                    aVar2.m = matrix3;
                }
            }
            return aVar2;
        }
    }

    private static class b {
        /* access modifiers changed from: private */
        public ArrayList<Float> a;
        private int b;

        public b(ArrayList<Float> arrayList, int i) {
            this.a = arrayList;
            this.b = i;
        }

        public int a() {
            return this.b;
        }
    }

    public static class c {
        /* access modifiers changed from: private */
        public boolean a = false;
        /* access modifiers changed from: private */
        public int b = 0;
        /* access modifiers changed from: private */
        public boolean c = false;
        /* access modifiers changed from: private */
        public int d = 0;
        /* access modifiers changed from: private */
        public float e = 0.0f;
        /* access modifiers changed from: private */
        public int f = 0;
        /* access modifiers changed from: private */
        public int g = 0;
        /* access modifiers changed from: private */
        public Map<Integer, Integer> h;
        /* access modifiers changed from: private */
        public Set<Integer> i = new HashSet();
        private int j;

        public c() {
        }

        public c(c cVar) {
            if (cVar != null) {
                this.a = cVar.a;
                this.b = cVar.b;
                this.c = cVar.c;
                this.d = cVar.d;
                this.e = cVar.e;
                this.f = cVar.f;
                this.g = cVar.g;
                if (cVar.h != null) {
                    this.h = new HashMap();
                    this.h.putAll(cVar.h);
                }
                this.j = cVar.j;
                this.i.addAll(cVar.i);
            }
        }

        /* access modifiers changed from: private */
        public int a(int i2) {
            Log.d("SVGAndroid", "mapColor");
            Map<Integer, Integer> map = this.h;
            if (map == null || !map.containsKey(Integer.valueOf(i2))) {
                Map<Integer, Integer> map2 = this.h;
                if (map2 != null && this.j > 0) {
                    for (Map.Entry next : map2.entrySet()) {
                        int intValue = ((Integer) next.getKey()).intValue();
                        if (Math.max(Math.max(Math.abs(Color.red(intValue) - Color.red(i2)), Math.abs(Color.green(intValue) - Color.green(i2))), Math.abs(Color.blue(intValue) - Color.blue(i2))) <= this.j) {
                            Log.d("SVGAndroid", "mapColor : SIMILAR");
                            int intValue2 = ((Integer) next.getValue()).intValue();
                            return Color.rgb(Math.max(0, Math.min(255, (Color.red(i2) - Color.red(intValue)) + Color.red(intValue2))), Math.max(0, Math.min(255, (Color.green(i2) - Color.green(intValue)) + Color.green(intValue2))), Math.max(0, Math.min(255, (Color.blue(i2) - Color.blue(intValue)) + Color.blue(intValue2))));
                        }
                    }
                }
                Log.d("SVGAndroid", "mapColor : NO CHANGE");
                return i2;
            }
            Log.d("SVGAndroid", "mapColor : EXACT");
            return this.h.get(Integer.valueOf(i2)).intValue();
        }

        public void a(Map<Integer, Integer> map, int i2) {
            this.h = map;
            this.j = i2;
        }
    }

    private static class d {
        g a;
        Attributes b;
        Set<String> c;

        private d(Attributes attributes) {
            this.a = null;
            this.c = null;
            this.b = attributes;
            String a2 = SVGParser.e("style", attributes);
            if (a2 != null) {
                this.a = new g(a2);
            }
        }

        public void a(String str) {
            if (this.c == null) {
                this.c = new HashSet();
            }
            this.c.add(str);
        }

        public String b(String str) {
            Set<String> set = this.c;
            String str2 = null;
            if (set != null && set.contains(str)) {
                return null;
            }
            g gVar = this.a;
            if (gVar != null) {
                str2 = gVar.a(str);
            }
            return str2 == null ? SVGParser.e(str, this.b) : str2;
        }

        public String c(String str) {
            return b(str);
        }

        public Integer d(String str) {
            String b2 = b(str);
            if (b2 != null && b2.startsWith("#")) {
                try {
                    return Integer.valueOf(Integer.parseInt(b2.substring(1), 16));
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }

        public Float e(String str) {
            String b2 = b(str);
            if (b2 == null) {
                return null;
            }
            try {
                return Float.valueOf(Float.parseFloat(b2));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
    }

    private static class e extends DefaultHandler {
        Picture a;
        Canvas b;
        Paint c;
        RectF d;
        RectF e;
        RectF f;
        Deque<f> g;
        c h;
        boolean i;
        HashMap<String, Shader> j;
        HashMap<String, a> k;
        a l;
        private boolean m;
        private int n;
        private boolean o;

        private e(Picture picture, c cVar) {
            this.d = new RectF();
            this.e = null;
            this.f = new RectF(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
            this.g = new ArrayDeque();
            this.i = false;
            this.j = new HashMap<>();
            this.k = new HashMap<>();
            this.l = null;
            this.m = false;
            this.n = 0;
            this.o = false;
            Log.d("SVGAndroid", "SVGHandler Constructed");
            this.a = picture;
            this.c = new Paint();
            this.c.setAntiAlias(true);
            f fVar = new f();
            boolean unused = fVar.h = true;
            this.g.push(fVar);
            this.h = new c(cVar);
        }

        private int a(d dVar, Integer num, boolean z) {
            Log.d("SVGAndroid", "getColor : fillMode=" + z + " color=" + com.nexstreaming.app.common.util.c.a(num.intValue()));
            if (!z || !this.h.a) {
                int intValue = (num.intValue() & 16777215) | -16777216;
                if (this.h.i != null) {
                    this.h.i.add(Integer.valueOf(intValue));
                }
                if (this.h.f == intValue) {
                    Log.d("SVGAndroid", "getColor : REPLACE COLOR");
                    intValue = this.h.g;
                }
                if (this.h.h != null) {
                    Log.d("SVGAndroid", "getColor : MAP COLOR");
                    intValue = this.h.a(intValue);
                }
                Float e2 = dVar.e("opacity");
                if (e2 == null) {
                    e2 = dVar.e(z ? "fill-opacity" : "stroke-opacity");
                }
                if (e2 == null) {
                    return intValue;
                }
                return ((((int) (e2.floatValue() * 255.0f)) << 24) & -16777216) | (intValue & 16777215);
            }
            Log.d("SVGAndroid", "getColor : FILL OVERRIDE");
            return this.h.b;
        }

        private a a(boolean z, Attributes attributes) {
            Log.d("SVGAndroid", "doGradient : IN");
            a aVar = new a();
            aVar.a = SVGParser.e("id", attributes);
            aVar.c = z;
            Float valueOf = Float.valueOf(0.0f);
            if (z) {
                aVar.d = SVGParser.b("x1", attributes, valueOf).floatValue();
                aVar.f = SVGParser.b("x2", attributes, valueOf).floatValue();
                aVar.e = SVGParser.b("y1", attributes, valueOf).floatValue();
                aVar.g = SVGParser.b("y2", attributes, valueOf).floatValue();
            } else {
                aVar.h = SVGParser.b("cx", attributes, valueOf).floatValue();
                aVar.i = SVGParser.b("cy", attributes, valueOf).floatValue();
                aVar.j = SVGParser.b("r", attributes, valueOf).floatValue();
            }
            String a2 = SVGParser.e("gradientTransform", attributes);
            if (a2 != null) {
                aVar.m = SVGParser.e(a2);
            }
            String a3 = SVGParser.e("href", attributes);
            if (a3 != null) {
                if (a3.startsWith("#")) {
                    a3 = a3.substring(1);
                }
                aVar.b = a3;
            }
            return aVar;
        }

        private void a(float f2, float f3) {
            if (f2 < this.f.left) {
                this.f.left = f2;
            }
            if (f2 > this.f.right) {
                this.f.right = f2;
            }
            if (f3 < this.f.top) {
                this.f.top = f3;
            }
            if (f3 > this.f.bottom) {
                this.f.bottom = f3;
            }
        }

        private void a(float f2, float f3, float f4, float f5) {
            a(f2, f3);
            a(f2 + f4, f3 + f5);
        }

        private void a(Path path) {
            path.computeBounds(this.d, false);
            a(this.d.left, this.d.top);
            a(this.d.right, this.d.bottom);
        }

        private void a(Attributes attributes) {
            String a2 = SVGParser.e("transform", attributes);
            this.i = a2 != null;
            if (this.i) {
                Matrix b2 = SVGParser.e(a2);
                this.b.save();
                this.b.concat(b2);
            }
        }

        private boolean a(d dVar, f fVar) {
            Log.d("SVGAndroid", "doStroke : IN");
            if ("none".equals(dVar.c("display"))) {
                return false;
            }
            if (this.h.c) {
                fVar.b(this.h.d);
                fVar.a(this.h.e);
                return true;
            }
            String c2 = dVar.c("stroke");
            if (c2 == null || !c2.equals("none")) {
                Integer d2 = dVar.d("stroke");
                if (d2 == null) {
                    return false;
                }
                fVar.b(a(dVar, d2, false));
                Float e2 = dVar.e("stroke-width");
                if (e2 != null) {
                    fVar.a(e2.floatValue());
                }
                String c3 = dVar.c("stroke-linecap");
                if ("round".equals(c3)) {
                    fVar.a(Paint.Cap.ROUND);
                } else if ("square".equals(c3)) {
                    fVar.a(Paint.Cap.SQUARE);
                } else if ("butt".equals(c3)) {
                    fVar.a(Paint.Cap.BUTT);
                }
                String c4 = dVar.c("stroke-linejoin");
                if ("miter".equals(c4)) {
                    fVar.a(Paint.Join.MITER);
                } else if ("round".equals(c4)) {
                    fVar.a(Paint.Join.ROUND);
                } else if ("bevel".equals(c4)) {
                    fVar.a(Paint.Join.BEVEL);
                }
                return true;
            }
            boolean unused = fVar.g = false;
            return true;
        }

        private boolean a(d dVar, HashMap<String, Shader> hashMap, f fVar) {
            Log.d("SVGAndroid", "doFill : IN");
            if ("none".equals(dVar.c("display"))) {
                return false;
            }
            String c2 = dVar.c("fill-rule");
            if (c2 != null) {
                if ("nonzero".equals(c2)) {
                    fVar.a(Path.FillType.WINDING);
                } else if ("evenodd".equals(c2)) {
                    fVar.a(Path.FillType.EVEN_ODD);
                }
            }
            if (this.h.a) {
                fVar.a(this.h.b);
                return true;
            }
            String c3 = dVar.c("fill");
            if (c3 == null || !c3.startsWith("url(#")) {
                if (c3 == null || !c3.equals("none")) {
                    Integer d2 = dVar.d("fill");
                    if (d2 != null) {
                        Log.d("SVGAndroid", "doFill :   c=" + com.nexstreaming.app.common.util.c.a(d2.intValue()));
                        fVar.a(a(dVar, d2, true));
                        return true;
                    } else if (dVar.c("fill") == null && dVar.c("stroke") == null) {
                        Log.d("SVGAndroid", "doFill :   no fill & no stroke");
                        return true;
                    }
                } else {
                    Log.d("SVGAndroid", "doFill :   none");
                    boolean unused = fVar.h = false;
                }
                Log.d("SVGAndroid", "doFill :   no fill");
                return false;
            }
            Log.d("SVGAndroid", "doFill :   gradient>>" + c3);
            Shader shader = hashMap.get(c3.substring(5, c3.length() - 1));
            if (shader == null) {
                return false;
            }
            fVar.a(shader);
            return true;
        }

        private void b() {
            if (this.i) {
                this.b.restore();
            }
        }

        public c a() {
            return this.h;
        }

        public void characters(char[] cArr, int i2, int i3) {
        }

        public void endDocument() throws SAXException {
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            a aVar;
            a aVar2;
            a aVar3;
            if (str2.equals("svg")) {
                this.a.endRecording();
                return;
            }
            int i2 = 0;
            if (str2.equals("linearGradient")) {
                if (this.l.a != null) {
                    if (!(this.l.b == null || (aVar3 = this.k.get(this.l.b)) == null)) {
                        this.l = aVar3.a(this.l);
                    }
                    int[] iArr = new int[this.l.l.size()];
                    for (int i3 = 0; i3 < iArr.length; i3++) {
                        iArr[i3] = this.l.l.get(i3).intValue();
                    }
                    float[] fArr = new float[this.l.k.size()];
                    while (i2 < fArr.length) {
                        fArr[i2] = this.l.k.get(i2).floatValue();
                        i2++;
                    }
                    if (iArr.length == 0) {
                        Log.d("BAD", "BAD");
                    }
                    LinearGradient linearGradient = new LinearGradient(this.l.d, this.l.e, this.l.f, this.l.g, iArr, fArr, Shader.TileMode.CLAMP);
                    if (this.l.m != null) {
                        linearGradient.setLocalMatrix(this.l.m);
                    }
                    this.j.put(this.l.a, linearGradient);
                    this.k.put(this.l.a, this.l);
                }
            } else if (str2.equals("radialGradient")) {
                if (this.l.a != null) {
                    if (!(this.l.b == null || (aVar2 = this.k.get(this.l.b)) == null)) {
                        this.l = aVar2.a(this.l);
                    }
                    int[] iArr2 = new int[this.l.l.size()];
                    for (int i4 = 0; i4 < iArr2.length; i4++) {
                        iArr2[i4] = this.l.l.get(i4).intValue();
                    }
                    float[] fArr2 = new float[this.l.k.size()];
                    while (i2 < fArr2.length) {
                        fArr2[i2] = this.l.k.get(i2).floatValue();
                        i2++;
                    }
                    if (!(this.l.b == null || (aVar = this.k.get(this.l.b)) == null)) {
                        this.l = aVar.a(this.l);
                    }
                    RadialGradient radialGradient = new RadialGradient(this.l.h, this.l.i, this.l.j, iArr2, fArr2, Shader.TileMode.CLAMP);
                    if (this.l.m != null) {
                        radialGradient.setLocalMatrix(this.l.m);
                    }
                    this.j.put(this.l.a, radialGradient);
                    this.k.put(this.l.a, this.l);
                }
            } else if (str2.equals("g")) {
                this.g.pop();
                if (this.o) {
                    this.o = false;
                }
                this.b.restore();
                if (this.m) {
                    this.n--;
                    if (this.n == 0) {
                        this.m = false;
                    }
                }
            }
        }

        public void startDocument() throws SAXException {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:96:0x02d7, code lost:
            if (r5 == null) goto L_0x02d1;
         */
        /* JADX WARNING: Removed duplicated region for block: B:107:0x0386  */
        /* JADX WARNING: Removed duplicated region for block: B:99:0x0307  */
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            f fVar;
            int i2;
            int i3;
            float f2;
            int i4;
            String str4 = str2;
            Attributes attributes2 = attributes;
            Log.d("SVGAndroid", "startElement : " + str4 + " atts=" + attributes.toString());
            boolean z = this.o;
            float f3 = 0.0f;
            Float valueOf = Float.valueOf(0.0f);
            if (z) {
                if (str4.equals("rect")) {
                    Float b2 = SVGParser.f("x", attributes2);
                    if (b2 == null) {
                        b2 = valueOf;
                    }
                    Float b3 = SVGParser.f("y", attributes2);
                    if (b3 != null) {
                        valueOf = b3;
                    }
                    Float b4 = SVGParser.f(nexExportFormat.TAG_FORMAT_WIDTH, attributes2);
                    Float unused = SVGParser.f(nexExportFormat.TAG_FORMAT_HEIGHT, attributes2);
                    this.e = new RectF(b2.floatValue(), valueOf.floatValue(), b2.floatValue() + b4.floatValue(), valueOf.floatValue() + b4.floatValue());
                }
            } else if (str4.equals("svg")) {
                Float b5 = SVGParser.f(nexExportFormat.TAG_FORMAT_WIDTH, attributes2);
                Float b6 = SVGParser.f(nexExportFormat.TAG_FORMAT_HEIGHT, attributes2);
                if (b5 == null || b6 == null) {
                    String a2 = SVGParser.e("viewBox", attributes2);
                    i3 = 100;
                    if (a2 == null) {
                        Log.e("SVGHandler", "Missing SVG bounds! Defaulting to 100x100");
                    } else {
                        b a3 = SVGParser.d(a2);
                        if (a3.a.size() < 4) {
                            Log.e("SVGHandler", "Malformed SVG bounds! Defaulting to 100x100");
                        } else {
                            f3 = ((Float) a3.a.get(0)).floatValue();
                            f2 = ((Float) a3.a.get(1)).floatValue();
                            int ceil = (int) Math.ceil((double) ((Float) a3.a.get(2)).floatValue());
                            Log.d("SVGAndroid", "Used viewBox");
                            int i5 = ceil;
                            i3 = (int) Math.ceil((double) ((Float) a3.a.get(3)).floatValue());
                            i4 = i5;
                            Log.d("SVGAndroid", "BEGIN REC: dx,dy=" + f3 + "," + f2 + "  w,h=" + i4 + "," + i3);
                            this.b = this.a.beginRecording(i4, i3);
                            this.b.translate(f3, f2);
                        }
                    }
                    i4 = 100;
                } else {
                    i4 = (int) Math.ceil((double) b5.floatValue());
                    Log.d("SVGAndroid", "Used width, height");
                    i3 = (int) Math.ceil((double) b6.floatValue());
                }
                f2 = 0.0f;
                Log.d("SVGAndroid", "BEGIN REC: dx,dy=" + f3 + "," + f2 + "  w,h=" + i4 + "," + i3);
                this.b = this.a.beginRecording(i4, i3);
                this.b.translate(f3, f2);
            } else if (!str4.equals("defs")) {
                if (str4.equals("linearGradient")) {
                    this.l = a(true, attributes2);
                } else if (str4.equals("radialGradient")) {
                    this.l = a(false, attributes2);
                } else {
                    int i6 = 255;
                    if (str4.equals("stop")) {
                        if (this.l != null) {
                            float floatValue = SVGParser.f("offset", attributes2).floatValue();
                            String unused2 = SVGParser.e("style", attributes2);
                            d dVar = new d(attributes2);
                            String b7 = dVar.b("stop-color");
                            int i7 = -16777216;
                            if (b7 != null) {
                                i7 = b7.startsWith("#") ? Integer.parseInt(b7.substring(1), 16) : Integer.parseInt(b7, 16);
                            }
                            String b8 = dVar.b("stop-opacity");
                            if (b8 != null) {
                                int round = Math.round(Float.parseFloat(b8) * 255.0f);
                                if (round <= 255) {
                                    i6 = round;
                                }
                                i2 = i6 << 24;
                            } else {
                                i2 = -16777216;
                            }
                            int i8 = i2 | i7;
                            this.l.k.add(Float.valueOf(floatValue));
                            if (this.h.i != null) {
                                this.h.i.add(Integer.valueOf(i8));
                            }
                            this.l.l.add(Integer.valueOf(this.h.a(i8)));
                        }
                    } else if (str4.equals("g")) {
                        if ("bounds".equalsIgnoreCase(SVGParser.e("id", attributes2))) {
                            this.o = true;
                        }
                        if (this.m) {
                            this.n++;
                        }
                        f fVar2 = new f(this.g.peek());
                        d dVar2 = new d(attributes2);
                        Float e2 = dVar2.e("opacity");
                        int max = e2 == null ? 255 : Math.max(0, Math.min(255, (int) (e2.floatValue() * 255.0f)));
                        if (max >= 255 || max <= 0) {
                            this.b.save();
                        } else {
                            this.b.saveLayerAlpha((RectF) null, max, 31);
                            dVar2.a("opacity");
                        }
                        a(dVar2, this.j, fVar2);
                        a(dVar2, fVar2);
                        this.g.push(fVar2);
                        if ((max <= 0 || "none".equals(SVGParser.e("display", attributes2))) && !this.m) {
                            this.m = true;
                            this.n = 1;
                        }
                    } else if (!this.m && str4.equals("rect")) {
                        Float b9 = SVGParser.f("x", attributes2);
                        if (b9 == null) {
                            b9 = valueOf;
                        }
                        Float b10 = SVGParser.f("y", attributes2);
                        if (b10 == null) {
                            b10 = valueOf;
                        }
                        Float b11 = SVGParser.f("rx", attributes2);
                        Float b12 = SVGParser.f("ry", attributes2);
                        if (b11 == null && b12 == null) {
                            b11 = valueOf;
                        } else {
                            if (b11 == null) {
                                b11 = b12;
                            }
                            Float b13 = SVGParser.f(nexExportFormat.TAG_FORMAT_WIDTH, attributes2);
                            Float b14 = SVGParser.f(nexExportFormat.TAG_FORMAT_HEIGHT, attributes2);
                            a(attributes2);
                            d dVar3 = new d(attributes2);
                            fVar = new f(this.g.peek());
                            a(dVar3, this.j, fVar);
                            a(dVar3, fVar);
                            if (fVar.a(this.c)) {
                                a(b9.floatValue(), b10.floatValue(), b13.floatValue(), b14.floatValue());
                                if (b11.floatValue() <= 0.0f || b12.floatValue() <= 0.0f) {
                                    this.b.drawRect(b9.floatValue(), b10.floatValue(), b9.floatValue() + b13.floatValue(), b10.floatValue() + b14.floatValue(), this.c);
                                } else {
                                    this.b.drawRoundRect(new RectF(b9.floatValue(), b10.floatValue(), b9.floatValue() + b13.floatValue(), b10.floatValue() + b14.floatValue()), b11.floatValue(), b12.floatValue(), this.c);
                                }
                            }
                            if (fVar.b(this.c)) {
                                if (b11.floatValue() <= 0.0f || b12.floatValue() <= 0.0f) {
                                    this.b.drawRect(b9.floatValue(), b10.floatValue(), b9.floatValue() + b13.floatValue(), b10.floatValue() + b14.floatValue(), this.c);
                                } else {
                                    this.b.drawRoundRect(new RectF(b9.floatValue(), b10.floatValue(), b9.floatValue() + b13.floatValue(), b10.floatValue() + b14.floatValue()), b11.floatValue(), b12.floatValue(), this.c);
                                }
                            }
                            b();
                        }
                        b12 = b11;
                        Float b132 = SVGParser.f(nexExportFormat.TAG_FORMAT_WIDTH, attributes2);
                        Float b142 = SVGParser.f(nexExportFormat.TAG_FORMAT_HEIGHT, attributes2);
                        a(attributes2);
                        d dVar32 = new d(attributes2);
                        fVar = new f(this.g.peek());
                        a(dVar32, this.j, fVar);
                        a(dVar32, fVar);
                        if (fVar.a(this.c)) {
                        }
                        if (fVar.b(this.c)) {
                        }
                        b();
                    } else if (!this.m && str4.equals("line")) {
                        Float b15 = SVGParser.f("x1", attributes2);
                        Float b16 = SVGParser.f("x2", attributes2);
                        Float b17 = SVGParser.f("y1", attributes2);
                        Float b18 = SVGParser.f("y2", attributes2);
                        d dVar4 = new d(attributes2);
                        f fVar3 = new f(this.g.peek());
                        a(dVar4, fVar3);
                        if (fVar3.b(this.c)) {
                            a(attributes2);
                            a(b15.floatValue(), b17.floatValue());
                            a(b16.floatValue(), b18.floatValue());
                            this.b.drawLine(b15.floatValue(), b17.floatValue(), b16.floatValue(), b18.floatValue(), this.c);
                            b();
                        }
                    } else if (!this.m && str4.equals("circle")) {
                        Float b19 = SVGParser.f("cx", attributes2);
                        Float b20 = SVGParser.f("cy", attributes2);
                        Float b21 = SVGParser.f("r", attributes2);
                        if (b19 != null && b20 != null && b21 != null) {
                            a(attributes2);
                            d dVar5 = new d(attributes2);
                            f fVar4 = new f(this.g.peek());
                            a(dVar5, this.j, fVar4);
                            a(dVar5, fVar4);
                            if (fVar4.a(this.c)) {
                                a(b19.floatValue() - b21.floatValue(), b20.floatValue() - b21.floatValue());
                                a(b19.floatValue() + b21.floatValue(), b20.floatValue() + b21.floatValue());
                                this.b.drawCircle(b19.floatValue(), b20.floatValue(), b21.floatValue(), this.c);
                            }
                            if (fVar4.b(this.c)) {
                                this.b.drawCircle(b19.floatValue(), b20.floatValue(), b21.floatValue(), this.c);
                            }
                            b();
                        }
                    } else if (!this.m && str4.equals("ellipse")) {
                        Float b22 = SVGParser.f("cx", attributes2);
                        Float b23 = SVGParser.f("cy", attributes2);
                        Float b24 = SVGParser.f("rx", attributes2);
                        Float b25 = SVGParser.f("ry", attributes2);
                        if (b22 != null && b23 != null && b24 != null && b25 != null) {
                            a(attributes2);
                            d dVar6 = new d(attributes2);
                            f fVar5 = new f(this.g.peek());
                            a(dVar6, this.j, fVar5);
                            a(dVar6, fVar5);
                            this.d.set(b22.floatValue() - b24.floatValue(), b23.floatValue() - b25.floatValue(), b22.floatValue() + b24.floatValue(), b23.floatValue() + b25.floatValue());
                            if (fVar5.a(this.c)) {
                                a(b22.floatValue() - b24.floatValue(), b23.floatValue() - b25.floatValue());
                                a(b22.floatValue() + b24.floatValue(), b23.floatValue() + b25.floatValue());
                                this.b.drawOval(this.d, this.c);
                            }
                            if (fVar5.b(this.c)) {
                                this.b.drawOval(this.d, this.c);
                            }
                            b();
                        }
                    } else if (!this.m && (str4.equals("polygon") || str4.equals("polyline"))) {
                        b c2 = SVGParser.d("points", attributes2);
                        if (c2 != null) {
                            Path path = new Path();
                            ArrayList a4 = c2.a;
                            if (a4.size() > 1) {
                                a(attributes2);
                                d dVar7 = new d(attributes2);
                                f fVar6 = new f(this.g.peek());
                                a(dVar7, this.j, fVar6);
                                a(dVar7, fVar6);
                                path.moveTo(((Float) a4.get(0)).floatValue(), ((Float) a4.get(1)).floatValue());
                                for (int i9 = 2; i9 < a4.size(); i9 += 2) {
                                    path.lineTo(((Float) a4.get(i9)).floatValue(), ((Float) a4.get(i9 + 1)).floatValue());
                                }
                                if (str4.equals("polygon")) {
                                    path.close();
                                }
                                if (fVar6.a(this.c)) {
                                    path.setFillType(fVar6.a());
                                    a(path);
                                    this.b.drawPath(path, this.c);
                                }
                                if (fVar6.b(this.c)) {
                                    this.b.drawPath(path, this.c);
                                }
                                b();
                            }
                        }
                    } else if (!this.m && str4.equals(nexExportFormat.TAG_FORMAT_PATH)) {
                        Path c3 = SVGParser.f(SVGParser.e("d", attributes2));
                        a(attributes2);
                        d dVar8 = new d(attributes2);
                        f fVar7 = new f(this.g.peek());
                        a(dVar8, this.j, fVar7);
                        a(dVar8, fVar7);
                        if (fVar7.a(this.c)) {
                            c3.setFillType(fVar7.a());
                            a(c3);
                            this.b.drawPath(c3, this.c);
                        }
                        if (fVar7.b(this.c)) {
                            this.b.drawPath(c3, this.c);
                        }
                        b();
                    } else if (!this.m) {
                        Log.w("SVGAndroid", "UNRECOGNIZED SVG COMMAND: " + str4);
                    }
                }
            }
        }
    }

    private static class f {
        private int a = -16777216;
        private int b = -16777216;
        private Shader c = null;
        private float d = 1.0f;
        private Paint.Cap e = Paint.Cap.BUTT;
        private Paint.Join f = Paint.Join.MITER;
        /* access modifiers changed from: private */
        public boolean g = false;
        /* access modifiers changed from: private */
        public boolean h = false;
        private Path.FillType i;

        public f() {
        }

        public f(f fVar) {
            this.a = fVar.a;
            this.b = fVar.b;
            this.c = fVar.c;
            this.d = fVar.d;
            this.e = fVar.e;
            this.f = fVar.f;
            this.g = fVar.g;
            this.h = fVar.h;
        }

        public Path.FillType a() {
            Path.FillType fillType = this.i;
            return fillType == null ? Path.FillType.WINDING : fillType;
        }

        public void a(float f2) {
            this.d = f2;
        }

        public void a(int i2) {
            this.a = i2;
            this.c = null;
            this.h = true;
        }

        public void a(Paint.Cap cap) {
            this.e = cap;
        }

        public void a(Paint.Join join) {
            this.f = join;
        }

        public void a(Path.FillType fillType) {
            this.i = fillType;
        }

        public void a(Shader shader) {
            this.c = shader;
            this.a = -16777216;
            this.h = true;
        }

        public boolean a(Paint paint) {
            if (!this.h) {
                return false;
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setShader(this.c);
            paint.setColor(this.a);
            return true;
        }

        public void b(int i2) {
            this.b = i2;
            this.g = true;
        }

        public boolean b(Paint paint) {
            if (!this.g) {
                return false;
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setShader((Shader) null);
            paint.setColor(this.b);
            paint.setStrokeWidth(this.d);
            paint.setStrokeCap(this.e);
            paint.setStrokeJoin(this.f);
            return true;
        }
    }

    private static class g {
        HashMap<String, String> a;

        private g(String str) {
            this.a = new HashMap<>();
            for (String split : str.split(";")) {
                String[] split2 = split.split(":");
                if (split2.length == 2) {
                    this.a.put(split2[0], split2[1]);
                }
            }
        }

        public String a(String str) {
            return this.a.get(str);
        }
    }

    private static class h {
        private final String a;
        private Matrix b = null;

        public h(String str) {
            this.a = str;
        }

        private void b() {
            this.b = new Matrix();
            int length = this.a.length();
            int i = 0;
            while (i < length) {
                char charAt = this.a.charAt(i);
                if (charAt >= 'a' && charAt <= 'z') {
                    int i2 = i;
                    for (Prefix prefix : Prefix.values()) {
                        String name = prefix.name();
                        if (this.a.startsWith(name, i2) && this.a.charAt(name.length() + i2) == '(') {
                            int length2 = i2 + name.length();
                            b a2 = SVGParser.d(this.a.substring(length2 + 1));
                            i2 = length2 + a2.a();
                            Matrix a3 = SVGParser.b(prefix, a2);
                            if (a3 != null) {
                                this.b.preConcat(a3);
                            }
                        }
                    }
                    i = i2;
                }
                i++;
            }
        }

        public Matrix a() {
            if (this.b == null) {
                b();
            }
            return this.b;
        }
    }

    public static b a(InputStream inputStream) throws SVGParseException {
        return a(inputStream, (c) null);
    }

    private static b a(InputStream inputStream, c cVar) throws SVGParseException {
        Log.d("SVGAndroid", "Parse IN");
        try {
            System.currentTimeMillis();
            XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            Picture picture = new Picture();
            e eVar = new e(picture, cVar);
            xMLReader.setContentHandler(eVar);
            xMLReader.parse(new InputSource(inputStream));
            b bVar = new b(picture, eVar.e, eVar.a().i);
            if (!Float.isInfinite(eVar.f.top)) {
                bVar.a(eVar.f);
            }
            Log.d("SVGAndroid", "Parse OUT");
            return bVar;
        } catch (Exception e2) {
            Log.w("SVGAndroid", "Parse Error", e2);
            throw new SVGParseException(e2);
        }
    }

    public static b a(InputStream inputStream, Map<Integer, Integer> map, int i) throws SVGParseException {
        c cVar = new c();
        cVar.a(map, i);
        return a(inputStream, cVar);
    }

    private static void a(Path path, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i, int i2) {
    }

    /* access modifiers changed from: private */
    public static Matrix b(Prefix prefix, b bVar) {
        float f2;
        float f3 = 0.0f;
        switch (prefix) {
            case matrix:
                if (bVar.a.size() != 6) {
                    return null;
                }
                Matrix matrix = new Matrix();
                matrix.setValues(new float[]{((Float) bVar.a.get(0)).floatValue(), ((Float) bVar.a.get(2)).floatValue(), ((Float) bVar.a.get(4)).floatValue(), ((Float) bVar.a.get(1)).floatValue(), ((Float) bVar.a.get(3)).floatValue(), ((Float) bVar.a.get(5)).floatValue(), 0.0f, 0.0f, 1.0f});
                return matrix;
            case translate:
                if (bVar.a.size() <= 0) {
                    return null;
                }
                float floatValue = ((Float) bVar.a.get(0)).floatValue();
                if (bVar.a.size() > 1) {
                    f3 = ((Float) bVar.a.get(1)).floatValue();
                }
                Matrix matrix2 = new Matrix();
                matrix2.postTranslate(floatValue, f3);
                return matrix2;
            case scale:
                if (bVar.a.size() <= 0) {
                    return null;
                }
                float floatValue2 = ((Float) bVar.a.get(0)).floatValue();
                if (bVar.a.size() > 1) {
                    f3 = ((Float) bVar.a.get(1)).floatValue();
                }
                Matrix matrix3 = new Matrix();
                matrix3.postScale(floatValue2, f3);
                return matrix3;
            case skewX:
                if (bVar.a.size() <= 0) {
                    return null;
                }
                float floatValue3 = ((Float) bVar.a.get(0)).floatValue();
                Matrix matrix4 = new Matrix();
                matrix4.postSkew((float) Math.tan((double) floatValue3), 0.0f);
                return matrix4;
            case skewY:
                if (bVar.a.size() <= 0) {
                    return null;
                }
                float floatValue4 = ((Float) bVar.a.get(0)).floatValue();
                Matrix matrix5 = new Matrix();
                matrix5.postSkew(0.0f, (float) Math.tan((double) floatValue4));
                return matrix5;
            case rotate:
                if (bVar.a.size() <= 0) {
                    return null;
                }
                float floatValue5 = ((Float) bVar.a.get(0)).floatValue();
                if (bVar.a.size() > 2) {
                    f3 = ((Float) bVar.a.get(1)).floatValue();
                    f2 = ((Float) bVar.a.get(2)).floatValue();
                } else {
                    f2 = 0.0f;
                }
                Matrix matrix6 = new Matrix();
                matrix6.postTranslate(f3, f2);
                matrix6.postRotate(floatValue5);
                matrix6.postTranslate(-f3, -f2);
                return matrix6;
            default:
                return null;
        }
    }

    /* access modifiers changed from: private */
    public static Float b(String str, Attributes attributes, Float f2) {
        String e2 = e(str, attributes);
        if (e2 == null) {
            return f2;
        }
        if (e2.endsWith("%")) {
            return Float.valueOf(Float.parseFloat(e2.substring(0, e2.length() - 1)) / 100.0f);
        }
        if (e2.endsWith("px")) {
            e2 = e2.substring(0, e2.length() - 2);
        }
        return Float.valueOf(Float.parseFloat(e2));
    }

    /* access modifiers changed from: private */
    public static b d(String str) {
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = false;
        for (int i2 = 1; i2 < length; i2++) {
            if (z) {
                z = false;
            } else {
                char charAt = str.charAt(i2);
                switch (charAt) {
                    case 9:
                    case 10:
                    case ' ':
                    case ',':
                        String substring = str.substring(i, i2);
                        if (substring.trim().length() <= 0) {
                            i++;
                            break;
                        } else {
                            arrayList.add(Float.valueOf(Float.parseFloat(substring)));
                            if (charAt != '-') {
                                i = i2 + 1;
                                z = true;
                                break;
                            } else {
                                i = i2;
                                break;
                            }
                        }
                    case ')':
                    case 'A':
                    case 'C':
                    case 'H':
                    case 'L':
                    case 'M':
                    case 'Q':
                    case 'S':
                    case 'T':
                    case 'V':
                    case 'Z':
                    case 'a':
                    case 'c':
                    case 'h':
                    case 'l':
                    case 'm':
                    case 'q':
                    case 's':
                    case 't':
                    case 'v':
                    case 'z':
                        String substring2 = str.substring(i, i2);
                        if (substring2.trim().length() > 0) {
                            arrayList.add(Float.valueOf(Float.parseFloat(substring2)));
                        }
                        return new b(arrayList, i2);
                }
            }
        }
        String substring3 = str.substring(i);
        if (substring3.length() > 0) {
            try {
                arrayList.add(Float.valueOf(Float.parseFloat(substring3)));
            } catch (NumberFormatException unused) {
            }
            i = str.length();
        }
        return new b(arrayList, i);
    }

    /* access modifiers changed from: private */
    public static b d(String str, Attributes attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            if (attributes.getLocalName(i).equals(str)) {
                return d(attributes.getValue(i));
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static Matrix e(String str) {
        return new h(str).a();
    }

    /* access modifiers changed from: private */
    public static String e(String str, Attributes attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            if (attributes.getLocalName(i).equals(str)) {
                return attributes.getValue(i);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0194 A[SYNTHETIC] */
    public static Path f(String str) {
        char c2;
        boolean z;
        float f2;
        float f3;
        String str2 = str;
        int length = str.length();
        a aVar = new a(str2, 0);
        aVar.a();
        Path path = new Path();
        char c3 = 0;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        while (aVar.a < length) {
            char charAt = str2.charAt(aVar.a);
            if (!(charAt == '+' || charAt == '-')) {
                switch (charAt) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                }
            }
            if (c3 == 'm' || c3 == 'M') {
                charAt = (char) (c3 - 1);
                c2 = c3;
                z = true;
                switch (charAt) {
                    case 'A':
                    case 'a':
                        float e2 = aVar.e();
                        float e3 = aVar.e();
                        float e4 = aVar.e();
                        int e5 = (int) aVar.e();
                        int e6 = (int) aVar.e();
                        float e7 = aVar.e();
                        float e8 = aVar.e();
                        float f10 = f6;
                        a(path, f4, f5, e7, e8, e2, e3, e4, e5, e6);
                        f4 = e7;
                        f5 = e8;
                        f7 = f7;
                        f6 = f10;
                    case 'C':
                    case 'c':
                        float e9 = aVar.e();
                        float e10 = aVar.e();
                        float e11 = aVar.e();
                        float e12 = aVar.e();
                        float e13 = aVar.e();
                        float e14 = aVar.e();
                        if (charAt == 'c') {
                            e9 += f4;
                            e11 += f4;
                            e13 += f4;
                            e10 += f5;
                            e12 += f5;
                            e14 += f5;
                        }
                        float f11 = e14;
                        float f12 = e13;
                        float f13 = e12;
                        float f14 = e11;
                        path.cubicTo(e9, e10, f14, f13, f12, f11);
                        f4 = f12;
                        f5 = f11;
                        f9 = f13;
                        f8 = f14;
                        break;
                    case 'H':
                    case 'h':
                        float e15 = aVar.e();
                        if (charAt != 'h') {
                            path.lineTo(e15, f5);
                            z = false;
                            f4 = e15;
                            break;
                        } else {
                            path.rLineTo(e15, 0.0f);
                            f4 += e15;
                        }
                    case 'L':
                    case 'l':
                        f3 = aVar.e();
                        f2 = aVar.e();
                        if (charAt != 'l') {
                            path.lineTo(f3, f2);
                            z = false;
                            f4 = f3;
                            f5 = f2;
                            break;
                        } else {
                            path.rLineTo(f3, f2);
                            break;
                        }
                    case 'M':
                    case 'm':
                        f3 = aVar.e();
                        f2 = aVar.e();
                        if (charAt != 'm') {
                            path.moveTo(f3, f2);
                            z = false;
                            f4 = f3;
                            f7 = f4;
                            f5 = f2;
                            f6 = f5;
                            break;
                        } else {
                            f7 += f3;
                            f6 += f2;
                            path.rMoveTo(f3, f2);
                            break;
                        }
                    case 'S':
                    case 's':
                        float e16 = aVar.e();
                        float e17 = aVar.e();
                        float e18 = aVar.e();
                        float e19 = aVar.e();
                        if (charAt == 's') {
                            e16 += f4;
                            e18 += f4;
                            e17 += f5;
                            e19 += f5;
                        }
                        float f15 = e16;
                        float f16 = e17;
                        float f17 = e18;
                        float f18 = e19;
                        path.cubicTo((f4 * 2.0f) - f8, (f5 * 2.0f) - f9, f15, f16, f17, f18);
                        f8 = f15;
                        f9 = f16;
                        f4 = f17;
                        f5 = f18;
                        break;
                    case 'V':
                    case 'v':
                        float e20 = aVar.e();
                        if (charAt != 'v') {
                            path.lineTo(f4, e20);
                            z = false;
                            f5 = e20;
                            break;
                        } else {
                            path.rLineTo(0.0f, e20);
                            f5 += e20;
                        }
                    case 'Z':
                    case 'z':
                        path.close();
                        path.moveTo(f7, f6);
                        f5 = f6;
                        f9 = f5;
                        f4 = f7;
                        f8 = f4;
                        break;
                    default:
                        float f19 = f6;
                        float f20 = f7;
                }
                f4 += f3;
                f5 += f2;
                z = false;
                if (!z) {
                    f8 = f4;
                    f9 = f5;
                }
                aVar.a();
                c3 = c2;
            } else {
                if (c3 == 'c' || c3 == 'C' || c3 == 'l' || c3 == 'L') {
                    charAt = c3;
                    c2 = charAt;
                    z = true;
                    switch (charAt) {
                        case 'A':
                        case 'a':
                            break;
                        case 'C':
                        case 'c':
                            break;
                        case 'H':
                        case 'h':
                            break;
                        case 'L':
                        case 'l':
                            break;
                        case 'M':
                        case 'm':
                            break;
                        case 'S':
                        case 's':
                            break;
                        case 'V':
                        case 'v':
                            break;
                        case 'Z':
                        case 'z':
                            break;
                    }
                    f4 += f3;
                    f5 += f2;
                    z = false;
                    if (!z) {
                    }
                    aVar.a();
                    c3 = c2;
                }
                aVar.c();
                c2 = charAt;
                z = true;
                switch (charAt) {
                    case 'A':
                    case 'a':
                        break;
                    case 'C':
                    case 'c':
                        break;
                    case 'H':
                    case 'h':
                        break;
                    case 'L':
                    case 'l':
                        break;
                    case 'M':
                    case 'm':
                        break;
                    case 'S':
                    case 's':
                        break;
                    case 'V':
                    case 'v':
                        break;
                    case 'Z':
                    case 'z':
                        break;
                }
                f4 += f3;
                f5 += f2;
                z = false;
                if (!z) {
                }
                aVar.a();
                c3 = c2;
            }
        }
        return path;
    }

    /* access modifiers changed from: private */
    public static Float f(String str, Attributes attributes) {
        return b(str, attributes, (Float) null);
    }
}
