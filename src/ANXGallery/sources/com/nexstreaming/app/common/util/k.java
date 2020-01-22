package com.nexstreaming.app.common.util;

/* compiled from: SimplexNoise */
public class k {
    private static a[] a = {new a(1.0d, 1.0d, 0.0d), new a(-1.0d, 1.0d, 0.0d), new a(1.0d, -1.0d, 0.0d), new a(-1.0d, -1.0d, 0.0d), new a(1.0d, 0.0d, 1.0d), new a(-1.0d, 0.0d, 1.0d), new a(1.0d, 0.0d, -1.0d), new a(-1.0d, 0.0d, -1.0d), new a(0.0d, 1.0d, 1.0d), new a(0.0d, -1.0d, 1.0d), new a(0.0d, 1.0d, -1.0d), new a(0.0d, -1.0d, -1.0d)};
    private static a[] b = {new a(0.0d, 1.0d, 1.0d, 1.0d), new a(0.0d, 1.0d, 1.0d, -1.0d), new a(0.0d, 1.0d, -1.0d, 1.0d), new a(0.0d, 1.0d, -1.0d, -1.0d), new a(0.0d, -1.0d, 1.0d, 1.0d), new a(0.0d, -1.0d, 1.0d, -1.0d), new a(0.0d, -1.0d, -1.0d, 1.0d), new a(0.0d, -1.0d, -1.0d, -1.0d), new a(1.0d, 0.0d, 1.0d, 1.0d), new a(1.0d, 0.0d, 1.0d, -1.0d), new a(1.0d, 0.0d, -1.0d, 1.0d), new a(1.0d, 0.0d, -1.0d, -1.0d), new a(-1.0d, 0.0d, 1.0d, 1.0d), new a(-1.0d, 0.0d, 1.0d, -1.0d), new a(-1.0d, 0.0d, -1.0d, 1.0d), new a(-1.0d, 0.0d, -1.0d, -1.0d), new a(1.0d, 1.0d, 0.0d, 1.0d), new a(1.0d, 1.0d, 0.0d, -1.0d), new a(1.0d, -1.0d, 0.0d, 1.0d), new a(1.0d, -1.0d, 0.0d, -1.0d), new a(-1.0d, 1.0d, 0.0d, 1.0d), new a(-1.0d, 1.0d, 0.0d, -1.0d), new a(-1.0d, -1.0d, 0.0d, 1.0d), new a(-1.0d, -1.0d, 0.0d, -1.0d), new a(1.0d, 1.0d, 1.0d, 0.0d), new a(1.0d, 1.0d, -1.0d, 0.0d), new a(1.0d, -1.0d, 1.0d, 0.0d), new a(1.0d, -1.0d, -1.0d, 0.0d), new a(-1.0d, 1.0d, 1.0d, 0.0d), new a(-1.0d, 1.0d, -1.0d, 0.0d), new a(-1.0d, -1.0d, 1.0d, 0.0d), new a(-1.0d, -1.0d, -1.0d, 0.0d)};
    private static short[] c = {151, 160, 137, 91, 90, 15, 131, 13, 201, 95, 96, 53, 194, 233, 7, 225, 140, 36, 103, 30, 69, 142, 8, 99, 37, 240, 21, 10, 23, 190, 6, 148, 247, 120, 234, 75, 0, 26, 197, 62, 94, 252, 219, 203, 117, 35, 11, 32, 57, 177, 33, 88, 237, 149, 56, 87, 174, 20, 125, 136, 171, 168, 68, 175, 74, 165, 71, 134, 139, 48, 27, 166, 77, 146, 158, 231, 83, 111, 229, 122, 60, 211, 133, 230, 220, 105, 92, 41, 55, 46, 245, 40, 244, 102, 143, 54, 65, 25, 63, 161, 1, 216, 80, 73, 209, 76, 132, 187, 208, 89, 18, 169, 200, 196, 135, 130, 116, 188, 159, 86, 164, 100, 109, 198, 173, 186, 3, 64, 52, 217, 226, 250, 124, 123, 5, 202, 38, 147, 118, 126, 255, 82, 85, 212, 207, 206, 59, 227, 47, 16, 58, 17, 182, 189, 28, 42, 223, 183, 170, 213, 119, 248, 152, 2, 44, 154, 163, 70, 221, 153, 101, 155, 167, 43, 172, 9, 129, 22, 39, 253, 19, 98, 108, 110, 79, 113, 224, 232, 178, 185, 112, 104, 218, 246, 97, 228, 251, 34, 242, 193, 238, 210, 144, 12, 191, 179, 162, 241, 81, 51, 145, 235, 249, 14, 239, 107, 49, 192, 214, 31, 181, 199, 106, 157, 184, 84, 204, 176, 115, 121, 50, 45, 127, 4, 150, 254, 138, 236, 205, 93, 222, 114, 67, 29, 24, 72, 243, 141, 128, 195, 78, 66, 215, 61, 156, 180};
    private static short[] d = new short[512];
    private static short[] e = new short[512];
    private static final double f = ((Math.sqrt(3.0d) - 1.0d) * 0.5d);
    private static final double g = ((3.0d - Math.sqrt(3.0d)) / 6.0d);
    private static final double h = ((Math.sqrt(5.0d) - 1.0d) / 4.0d);
    private static final double i = ((5.0d - Math.sqrt(5.0d)) / 20.0d);

    /* compiled from: SimplexNoise */
    private static class a {
        double a;
        double b;
        double c;
        double d;

        a(double d2, double d3, double d4) {
            this.a = d2;
            this.b = d3;
            this.c = d4;
        }

        a(double d2, double d3, double d4, double d5) {
            this.a = d2;
            this.b = d3;
            this.c = d4;
            this.d = d5;
        }
    }

    static {
        for (int i2 = 0; i2 < 512; i2++) {
            short[] sArr = d;
            sArr[i2] = c[i2 & 255];
            e[i2] = (short) (sArr[i2] % 12);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0132  */
    public static double a(double d2, double d3, double d4) {
        short s;
        short s2;
        short s3;
        short s4;
        short s5;
        double d5;
        double d6;
        double d7;
        double d8;
        double d9;
        double d10;
        double d11;
        short s6;
        short s7;
        short s8;
        short s9;
        short s10;
        short s11;
        short s12;
        double d12 = (d2 + d3 + d4) * 0.3333333333333333d;
        int a2 = a(d2 + d12);
        int a3 = a(d3 + d12);
        int a4 = a(d4 + d12);
        double d13 = ((double) (a2 + a3 + a4)) * 0.16666666666666666d;
        double d14 = d2 - (((double) a2) - d13);
        double d15 = d3 - (((double) a3) - d13);
        double d16 = d4 - (((double) a4) - d13);
        short s13 = 0;
        if (d14 < d15) {
            if (d15 < d16) {
                s11 = 0;
                s10 = 0;
                s9 = 1;
                s12 = 1;
            } else if (d14 < d16) {
                s9 = 0;
                s10 = 0;
                s11 = 1;
                s12 = 1;
            } else {
                s6 = 0;
                s = 0;
                s7 = 1;
                s8 = 1;
            }
            s = s2;
            double d17 = (d14 - ((double) s13)) + 0.16666666666666666d;
            double d18 = (d15 - ((double) s5)) + 0.16666666666666666d;
            double d19 = (d16 - ((double) s4)) + 0.16666666666666666d;
            double d20 = (d14 - ((double) s3)) + 0.3333333333333333d;
            double d21 = (d15 - ((double) s2)) + 0.3333333333333333d;
            double d22 = (d16 - ((double) s)) + 0.3333333333333333d;
            double d23 = (d14 - 1.0d) + 0.5d;
            double d24 = (d15 - 1.0d) + 0.5d;
            double d25 = (d16 - 1.0d) + 0.5d;
            short s14 = a2 & 255;
            short s15 = a3 & 255;
            short s16 = a4 & 255;
            short[] sArr = e;
            short[] sArr2 = d;
            short s17 = sArr[sArr2[sArr2[s16] + s15] + s14];
            short s18 = sArr[s13 + s14 + sArr2[s5 + s15 + sArr2[s4 + s16]]];
            short s19 = sArr[s3 + s14 + sArr2[s2 + s15 + sArr2[s + s16]]];
            short s20 = sArr[s14 + 1 + sArr2[s15 + 1 + sArr2[s16 + 1]]];
            d5 = ((0.6d - (d14 * d14)) - (d15 * d15)) - (d16 * d16);
            double d26 = 0.0d;
            if (d5 < 0.0d) {
            }
            d7 = ((0.6d - (d17 * d17)) - (d18 * d18)) - (d19 * d19);
            if (d7 < 0.0d) {
            }
            d9 = ((0.6d - (d20 * d20)) - (d21 * d21)) - (d22 * d22);
            if (d9 < 0.0d) {
            }
            d11 = ((0.6d - (d23 * d23)) - (d24 * d24)) - (d25 * d25);
            if (d11 >= 0.0d) {
            }
            return (d6 + d8 + d10 + d26) * 32.0d;
        } else if (d15 >= d16) {
            s7 = 0;
            s6 = 0;
            s = 0;
            s13 = 1;
            s8 = 1;
        } else {
            if (d14 >= d16) {
                s5 = 0;
                s4 = 0;
                s2 = 0;
                s13 = 1;
                s3 = 1;
            } else {
                s5 = 0;
                s2 = 0;
                s4 = 1;
                s3 = 1;
            }
            s = s3;
            double d172 = (d14 - ((double) s13)) + 0.16666666666666666d;
            double d182 = (d15 - ((double) s5)) + 0.16666666666666666d;
            double d192 = (d16 - ((double) s4)) + 0.16666666666666666d;
            double d202 = (d14 - ((double) s3)) + 0.3333333333333333d;
            double d212 = (d15 - ((double) s2)) + 0.3333333333333333d;
            double d222 = (d16 - ((double) s)) + 0.3333333333333333d;
            double d232 = (d14 - 1.0d) + 0.5d;
            double d242 = (d15 - 1.0d) + 0.5d;
            double d252 = (d16 - 1.0d) + 0.5d;
            short s142 = a2 & 255;
            short s152 = a3 & 255;
            short s162 = a4 & 255;
            short[] sArr3 = e;
            short[] sArr22 = d;
            short s172 = sArr3[sArr22[sArr22[s162] + s152] + s142];
            short s182 = sArr3[s13 + s142 + sArr22[s5 + s152 + sArr22[s4 + s162]]];
            short s192 = sArr3[s3 + s142 + sArr22[s2 + s152 + sArr22[s + s162]]];
            short s202 = sArr3[s142 + 1 + sArr22[s152 + 1 + sArr22[s162 + 1]]];
            d5 = ((0.6d - (d14 * d14)) - (d15 * d15)) - (d16 * d16);
            double d262 = 0.0d;
            if (d5 < 0.0d) {
                d6 = 0.0d;
            } else {
                double d27 = d5 * d5;
                d6 = d27 * d27 * a(a[s172], d14, d15, d16);
            }
            d7 = ((0.6d - (d172 * d172)) - (d182 * d182)) - (d192 * d192);
            if (d7 < 0.0d) {
                d8 = 0.0d;
            } else {
                double d28 = d7 * d7;
                d8 = d28 * d28 * a(a[s182], d172, d182, d192);
            }
            d9 = ((0.6d - (d202 * d202)) - (d212 * d212)) - (d222 * d222);
            if (d9 < 0.0d) {
                d10 = 0.0d;
            } else {
                double d29 = d9 * d9;
                d10 = d29 * d29 * a(a[s192], d202, d212, d222);
            }
            d11 = ((0.6d - (d232 * d232)) - (d242 * d242)) - (d252 * d252);
            if (d11 >= 0.0d) {
                double d30 = d11 * d11;
                d262 = d30 * d30 * a(a[s202], d232, d242, d252);
            }
            return (d6 + d8 + d10 + d262) * 32.0d;
        }
        s2 = s3;
        double d1722 = (d14 - ((double) s13)) + 0.16666666666666666d;
        double d1822 = (d15 - ((double) s5)) + 0.16666666666666666d;
        double d1922 = (d16 - ((double) s4)) + 0.16666666666666666d;
        double d2022 = (d14 - ((double) s3)) + 0.3333333333333333d;
        double d2122 = (d15 - ((double) s2)) + 0.3333333333333333d;
        double d2222 = (d16 - ((double) s)) + 0.3333333333333333d;
        double d2322 = (d14 - 1.0d) + 0.5d;
        double d2422 = (d15 - 1.0d) + 0.5d;
        double d2522 = (d16 - 1.0d) + 0.5d;
        short s1422 = a2 & 255;
        short s1522 = a3 & 255;
        short s1622 = a4 & 255;
        short[] sArr32 = e;
        short[] sArr222 = d;
        short s1722 = sArr32[sArr222[sArr222[s1622] + s1522] + s1422];
        short s1822 = sArr32[s13 + s1422 + sArr222[s5 + s1522 + sArr222[s4 + s1622]]];
        short s1922 = sArr32[s3 + s1422 + sArr222[s2 + s1522 + sArr222[s + s1622]]];
        short s2022 = sArr32[s1422 + 1 + sArr222[s1522 + 1 + sArr222[s1622 + 1]]];
        d5 = ((0.6d - (d14 * d14)) - (d15 * d15)) - (d16 * d16);
        double d2622 = 0.0d;
        if (d5 < 0.0d) {
        }
        d7 = ((0.6d - (d1722 * d1722)) - (d1822 * d1822)) - (d1922 * d1922);
        if (d7 < 0.0d) {
        }
        d9 = ((0.6d - (d2022 * d2022)) - (d2122 * d2122)) - (d2222 * d2222);
        if (d9 < 0.0d) {
        }
        d11 = ((0.6d - (d2322 * d2322)) - (d2422 * d2422)) - (d2522 * d2522);
        if (d11 >= 0.0d) {
        }
        return (d6 + d8 + d10 + d2622) * 32.0d;
    }

    private static double a(a aVar, double d2, double d3, double d4) {
        return (aVar.a * d2) + (aVar.b * d3) + (aVar.c * d4);
    }

    private static int a(double d2) {
        int i2 = (int) d2;
        return d2 < ((double) i2) ? i2 - 1 : i2;
    }
}
