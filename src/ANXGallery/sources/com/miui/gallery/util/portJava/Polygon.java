package com.miui.gallery.util.portJava;

import java.io.Serializable;

public class Polygon implements Serializable {
    private static final long serialVersionUID = -6460061437900069969L;
    protected Rectangle bounds;
    public int npoints;
    public int[] xpoints;
    public int[] ypoints;

    /* access modifiers changed from: package-private */
    public void calculateBounds(int[] iArr, int[] iArr2, int i) {
        int i2 = Reader.READ_DONE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MAX_VALUE;
        for (int i6 = 0; i6 < i; i6++) {
            int i7 = iArr[i6];
            i2 = Math.min(i2, i7);
            i3 = Math.max(i3, i7);
            int i8 = iArr2[i6];
            i5 = Math.min(i5, i8);
            i4 = Math.max(i4, i8);
        }
        this.bounds = new Rectangle(i2, i5, i3 - i2, i4 - i5);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    public boolean contains(double d, double d2) {
        int i;
        int i2;
        double d3;
        int i3;
        double d4;
        double d5 = d;
        double d6 = d2;
        if (this.npoints <= 2 || !getBoundingBox().contains(d5, d6)) {
            return false;
        }
        int[] iArr = this.xpoints;
        int i4 = this.npoints;
        boolean z = true;
        int i5 = iArr[i4 - 1];
        int i6 = this.ypoints[i4 - 1];
        int i7 = 0;
        int i8 = 0;
        while (i7 < this.npoints) {
            int i9 = this.xpoints[i7];
            int i10 = this.ypoints[i7];
            if (i10 != i6) {
                if (i9 < i5) {
                    if (d5 < ((double) i5)) {
                        i2 = i9;
                    }
                } else if (d5 < ((double) i9)) {
                    i2 = i5;
                }
                if (i10 < i6) {
                    double d7 = (double) i10;
                    if (d6 >= d7) {
                        int i11 = i5;
                        if (d6 < ((double) i6)) {
                            if (d5 >= ((double) i2)) {
                                double d8 = d6 - d7;
                                i3 = i11;
                                d3 = d5 - ((double) i9);
                                d4 = d8;
                                i = i8;
                                if (d3 < (d4 / ((double) (i6 - i10))) * ((double) (i3 - i9))) {
                                    i8 = i + 1;
                                    i7++;
                                    i5 = i9;
                                    i6 = i10;
                                    z = true;
                                }
                                i8 = i;
                                i7++;
                                i5 = i9;
                                i6 = i10;
                                z = true;
                            }
                        }
                    }
                } else {
                    int i12 = i5;
                    double d9 = (double) i6;
                    if (d6 >= d9 && d6 < ((double) i10)) {
                        if (d5 >= ((double) i2)) {
                            i3 = i12;
                            d3 = d5 - ((double) i3);
                            d4 = d6 - d9;
                            i = i8;
                            if (d3 < (d4 / ((double) (i6 - i10))) * ((double) (i3 - i9))) {
                            }
                            i8 = i;
                            i7++;
                            i5 = i9;
                            i6 = i10;
                            z = true;
                        }
                    }
                }
                i8++;
                i7++;
                i5 = i9;
                i6 = i10;
                z = true;
            }
            i = i8;
            i8 = i;
            i7++;
            i5 = i9;
            i6 = i10;
            z = true;
        }
        boolean z2 = z;
        if ((i8 & 1) != 0) {
            return z2;
        }
        return false;
    }

    public boolean contains(int i, int i2) {
        return contains((double) i, (double) i2);
    }

    @Deprecated
    public Rectangle getBoundingBox() {
        int i = this.npoints;
        if (i == 0) {
            return new Rectangle();
        }
        if (this.bounds == null) {
            calculateBounds(this.xpoints, this.ypoints, i);
        }
        return this.bounds.getBounds();
    }
}
