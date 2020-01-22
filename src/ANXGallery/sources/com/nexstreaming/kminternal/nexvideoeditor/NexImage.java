package com.nexstreaming.kminternal.nexvideoeditor;

import android.graphics.Bitmap;
import android.util.Log;

public class NexImage {
    private final Bitmap a;
    private final int b;
    private final int c;
    private final int d;

    public NexImage(Bitmap bitmap, int i, int i2) {
        Log.d("NexImage", "new NexImage(" + bitmap + "," + i + "," + i2 + ")");
        this.a = bitmap;
        this.b = i;
        this.c = i2;
        this.d = 1;
    }

    public NexImage(Bitmap bitmap, int i, int i2, int i3) {
        Log.d("NexImage", "new NexImage(" + bitmap + "," + i + "," + i2 + "," + i3 + ")");
        this.a = bitmap;
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public Bitmap getBitmap() {
        return this.a;
    }

    public int getHeight() {
        return this.c;
    }

    public int getLoadedType() {
        return this.d;
    }

    public void getPixels(int[] iArr) {
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            try {
                bitmap.getPixels(iArr, 0, this.b, 0, 0, this.b, this.c);
            } catch (ArrayIndexOutOfBoundsException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("w=");
                sb.append(this.b);
                sb.append(" h=");
                sb.append(this.c);
                sb.append(" bm=");
                sb.append(this.a.getWidth());
                sb.append("x");
                sb.append(this.a.getHeight());
                sb.append(" pixels=");
                sb.append(iArr == null ? "null" : Integer.valueOf(iArr.length));
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
        }
    }

    public void getPixels(int[] iArr, int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr2 = iArr;
        int i7 = i;
        int i8 = i2;
        int i9 = i3;
        int i10 = i4;
        int i11 = i5;
        int i12 = i6;
        Bitmap bitmap = this.a;
        if (bitmap != null) {
            String str = "}";
            String str2 = "; height=";
            String str3 = "; mBitmap {width=";
            String str4 = "NexImage";
            if (i10 + i12 > bitmap.getHeight()) {
                Log.d(str4, "getPixels() WARNING: y + height exceeds bitmap height!!; offset=" + i7 + "; stride=" + i8 + "; x,y=" + i9 + "," + i10 + "; width,height=" + i11 + "," + i12 + "; mWidth,mHeight=" + this.b + "," + this.c + "; pixels.length=" + iArr.length + str3 + this.a.getWidth() + str2 + this.a.getHeight() + str);
                return;
            }
            int[] iArr3 = iArr;
            String str5 = str4;
            if (i9 + i11 > this.a.getWidth()) {
                Log.d(str5, "getPixels() WARNING: y + height exceeds bitmap height!!; offset=" + i7 + "; stride=" + i8 + "; x,y=" + i9 + "," + i10 + "; width,height=" + i11 + "," + i12 + "; mWidth,mHeight=" + this.b + "," + this.c + "; pixels.length=" + iArr.length + str3 + this.a.getWidth() + str2 + this.a.getHeight() + str);
                return;
            }
            this.a.getPixels(iArr, i, i2, i3, i4, i5, i6);
        }
    }

    public int getWidth() {
        return this.b;
    }

    public void recycle() {
        this.a.recycle();
        Log.d("NexImage", "recycle Bitmap from native");
    }
}
