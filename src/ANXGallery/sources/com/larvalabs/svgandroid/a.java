package com.larvalabs.svgandroid;

/* compiled from: ParserHelper */
public class a {
    private static final double[] e = new double[128];
    public int a;
    private char b;
    private CharSequence c;
    private int d;

    static {
        int i = 0;
        while (true) {
            double[] dArr = e;
            if (i < dArr.length) {
                dArr[i] = Math.pow(10.0d, (double) i);
                i++;
            } else {
                return;
            }
        }
    }

    public a(CharSequence charSequence, int i) {
        this.c = charSequence;
        this.a = i;
        this.d = charSequence.length();
        this.b = charSequence.charAt(i);
    }

    public static float a(int i, int i2) {
        if (i2 < -125 || i == 0) {
            return 0.0f;
        }
        if (i2 >= 128) {
            return i > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        if (i2 == 0) {
            return (float) i;
        }
        if (i >= 67108864) {
            i++;
        }
        return (float) (i2 > 0 ? ((double) i) * e[i2] : ((double) i) / e[-i2]);
    }

    private void a(char c2) {
        throw new RuntimeException("Unexpected char '" + c2 + "'.");
    }

    private char f() {
        int i = this.a;
        if (i < this.d) {
            this.a = i + 1;
        }
        int i2 = this.a;
        if (i2 == this.d) {
            return 0;
        }
        return this.c.charAt(i2);
    }

    public void a() {
        while (true) {
            int i = this.a;
            if (i < this.d && Character.isWhitespace(this.c.charAt(i))) {
                c();
            } else {
                return;
            }
        }
    }

    public void b() {
        while (true) {
            int i = this.a;
            if (i < this.d) {
                char charAt = this.c.charAt(i);
                if (charAt == 9 || charAt == 10 || charAt == ' ' || charAt == ',') {
                    c();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void c() {
        this.b = f();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r15.b = f();
        r5 = r15.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (r5 == '.') goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        if (r5 == 'E') goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        if (r5 == 'e') goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        switch(r5) {
            case 48: goto L_0x0028;
            case 49: goto L_0x003a;
            case 50: goto L_0x003a;
            case 51: goto L_0x003a;
            case 52: goto L_0x003a;
            case 53: goto L_0x003a;
            case 54: goto L_0x003a;
            case 55: goto L_0x003a;
            case 56: goto L_0x003a;
            case 57: goto L_0x003a;
            default: goto L_0x0039;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        r5 = 0;
        r11 = 0;
        r12 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        if (r5 >= 9) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        r5 = r5 + 1;
        r12 = (r12 * 10) + (r15.b - '0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0049, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        r15.b = f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        switch(r15.b) {
            case 48: goto L_0x003d;
            case 49: goto L_0x003d;
            case 50: goto L_0x003d;
            case 51: goto L_0x003d;
            case 52: goto L_0x003d;
            case 53: goto L_0x003d;
            case 54: goto L_0x003d;
            case 55: goto L_0x003d;
            case 56: goto L_0x003d;
            case 57: goto L_0x003d;
            default: goto L_0x0056;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0056, code lost:
        r13 = r12;
        r12 = r11;
        r11 = r5;
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        r5 = true;
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        r12 = r11;
        r13 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d1, code lost:
        r15.b = f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d9, code lost:
        switch(r15.b) {
            case 48: goto L_0x00d1;
            case 49: goto L_0x00dd;
            case 50: goto L_0x00dd;
            case 51: goto L_0x00dd;
            case 52: goto L_0x00dd;
            case 53: goto L_0x00dd;
            case 54: goto L_0x00dd;
            case 55: goto L_0x00dd;
            case 56: goto L_0x00dd;
            case 57: goto L_0x00dd;
            default: goto L_0x00dc;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00dd, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00df, code lost:
        if (r4 >= 3) goto L_0x00ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e1, code lost:
        r4 = r4 + 1;
        r1 = (r1 * 10) + (r15.b - '0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ea, code lost:
        r15.b = f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f2, code lost:
        switch(r15.b) {
            case 48: goto L_0x00de;
            case 49: goto L_0x00de;
            case 50: goto L_0x00de;
            case 51: goto L_0x00de;
            case 52: goto L_0x00de;
            case 53: goto L_0x00de;
            case 54: goto L_0x00de;
            case 55: goto L_0x00de;
            case 56: goto L_0x00de;
            case 57: goto L_0x00de;
            default: goto L_0x00f5;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f5, code lost:
        r4 = r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0079 A[LOOP_START, PHI: r12 
      PHI: (r12v6 int) = (r12v0 int), (r12v7 int) binds: [B:33:0x0077, B:35:0x0083] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0089 A[LOOP_START, PHI: r11 r12 r13 
      PHI: (r11v1 int) = (r11v0 int), (r11v2 int) binds: [B:83:0x0089, B:41:0x009e] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r12v3 int) = (r12v2 int), (r12v4 int) binds: [B:83:0x0089, B:41:0x009e] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r13v4 int) = (r13v0 int), (r13v5 int) binds: [B:83:0x0089, B:41:0x009e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025 A[RETURN] */
    public float d() {
        boolean z;
        int i;
        int i2;
        int i3;
        boolean z2;
        char c2;
        char c3;
        char c4 = this.b;
        boolean z3 = true;
        int i4 = 0;
        if (c4 == '+') {
            z = true;
        } else if (c4 != '-') {
            z = true;
            switch (this.b) {
                case '.':
                    z2 = false;
                    i3 = 0;
                    break;
                case '0':
                    break;
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
                default:
                    return Float.NaN;
            }
            if (this.b == '.') {
                this.b = f();
                char c5 = this.b;
                switch (c5) {
                    case '0':
                        if (i3 == 0) {
                            while (true) {
                                this.b = f();
                                i2--;
                                switch (this.b) {
                                    case '0':
                                        break;
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                    default:
                                        if (!z2) {
                                            return 0.0f;
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        while (true) {
                            if (i3 < 9) {
                                i3++;
                                i = (i * 10) + (this.b - '0');
                                i2--;
                            }
                            this.b = f();
                            switch (this.b) {
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
                        break;
                    default:
                        if (!z2) {
                            a(c5);
                            return 0.0f;
                        }
                        break;
                }
            }
            char c6 = this.b;
            if (c6 == 'E' || c6 == 'e') {
                this.b = f();
                c2 = this.b;
                if (c2 != '+') {
                    if (c2 != '-') {
                        switch (c2) {
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
                            default:
                                a(c2);
                                return 0.0f;
                        }
                        switch (this.b) {
                            case '0':
                                break;
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
                    } else {
                        z3 = false;
                    }
                }
                this.b = f();
                c3 = this.b;
                switch (c3) {
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
                    default:
                        a(c3);
                        return 0.0f;
                }
                switch (this.b) {
                    case '0':
                        break;
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
            if (!z3) {
                i4 = -i4;
            }
            int i5 = i4 + i2;
            if (!z) {
                i = -i;
            }
            return a(i, i5);
        } else {
            z = false;
        }
        this.b = f();
        switch (this.b) {
            case '.':
                break;
            case '0':
                break;
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
        if (this.b == '.') {
        }
        char c62 = this.b;
        this.b = f();
        c2 = this.b;
        if (c2 != '+') {
        }
        this.b = f();
        c3 = this.b;
        switch (c3) {
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
        switch (this.b) {
            case '0':
                break;
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
        if (!z3) {
        }
        int i52 = i4 + i2;
        if (!z) {
        }
        return a(i, i52);
    }

    public float e() {
        a();
        float d2 = d();
        b();
        return d2;
    }
}
