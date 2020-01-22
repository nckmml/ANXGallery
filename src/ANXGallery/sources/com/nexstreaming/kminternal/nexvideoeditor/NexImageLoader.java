package com.nexstreaming.kminternal.nexvideoeditor;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.kminternal.kinemaster.fonts.Font;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class NexImageLoader {
    private static final String LOG_TAG = "NexImageLoader";
    private static final int MAX_USERSTRINGS = 3;
    private static final int NXT_ALIGNMASK = 15;
    private static final int NXT_ALIGN_CENTER = 1;
    private static final int NXT_ALIGN_LEFT = 0;
    private static final int NXT_ALIGN_RIGHT = 2;
    private static final int NXT_BLUR_INNER = 2;
    private static final int NXT_BLUR_NORMAL = 0;
    private static final int NXT_BLUR_OUTER = 3;
    private static final int NXT_BLUR_SOLID = 1;
    private static final int NXT_LONGTEXT_CROP_END = 0;
    private static final int NXT_LONGTEXT_ELLIPSIZE_END = 4;
    private static final int NXT_LONGTEXT_ELLIPSIZE_MIDDLE = 3;
    private static final int NXT_LONGTEXT_ELLIPSIZE_START = 2;
    private static final int NXT_LONGTEXT_WRAP = 1;
    private static final int NXT_TEXTFLAG_AUTOSIZE = 1024;
    private static final int NXT_TEXTFLAG_BOLD = 1;
    private static final int NXT_TEXTFLAG_CUTOUT = 2048;
    private static final int NXT_TEXTFLAG_FILL = 4;
    private static final int NXT_TEXTFLAG_ITALIC = 2;
    private static final int NXT_TEXTFLAG_LINEAR = 512;
    private static final int NXT_TEXTFLAG_SHADOW = 256;
    private static final int NXT_TEXTFLAG_STRIKE = 32;
    private static final int NXT_TEXTFLAG_STROKE = 8;
    private static final int NXT_TEXTFLAG_STROKEBACK = 4096;
    private static final int NXT_TEXTFLAG_SUBPIXEL = 128;
    private static final int NXT_TEXTFLAG_UNDERLINE = 16;
    private static final int NXT_VALIGNMASK = 240;
    private static final int NXT_VALIGN_BOTTOM = 32;
    private static final int NXT_VALIGN_CENTER = 16;
    private static final int NXT_VALIGN_TOP = 0;
    private static final String TAG_Overlay = "[Overlay]";
    private static final String TAG_PreviewThemeImage = "[PvwThImage]";
    private static final String TAG_Text = "[Text]";
    private static final String TAG_ThemeImage = "[ThemeImage]";
    private static final String TYPEFACE_ASSET = "asset:";
    private static final String TYPEFACE_FILE = "file:";
    private static final String TYPEFACE_FONTFILE = "fontfile:";
    private static final String TYPEFACE_FONTID = "fontid:";
    private static final String TYPEFACE_SYSTEM = "android:";
    private static final String TYPEFACE_THEME = "theme:";
    private static Map<a, WeakReference<Bitmap>> sBitmapCache = new HashMap();
    private static final Object sBitmapCacheLock = new Object();
    private static int sCleanCacheCount = 0;
    private static WeakHashMap<Bitmap, c> sLoadedBitmapCache = new WeakHashMap<>();
    private AssetManager m_assetManager;
    private a m_effectResourceLoader;
    private int m_jpegMaxHeight;
    private int m_jpegMaxSize;
    private int m_jpegMaxWidth;
    private d m_overlayPathResolver;

    private static class a {
        final String a;
        final int b;
        final int c;
        final int d;
        final Bitmap.Config e;
        final boolean f;

        private a(String str, int i, int i2, int i3, Bitmap.Config config, boolean z) {
            this.a = str == null ? "" : str;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = config;
            this.f = z;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            a aVar = (a) obj;
            return this.b == aVar.b && this.c == aVar.c && this.d == aVar.d && this.e == aVar.e && this.a.equals(aVar.a) && this.f == aVar.f;
        }

        public int hashCode() {
            int hashCode = this.a.hashCode() + (this.b * 191) + (this.c * 61) + (this.d * 199);
            Bitmap.Config config = this.e;
            return hashCode + (config == null ? 0 : config.hashCode());
        }
    }

    public static class b {
        private Bitmap a;
        private int b;
        private int c;
        private int d;

        private b(Bitmap bitmap, int i, int i2) {
            this.b = i;
            this.c = i2;
            this.a = bitmap;
            this.d = 1;
        }

        private b(Bitmap bitmap, int i, int i2, int i3) {
            this.b = i;
            this.c = i2;
            this.a = bitmap;
            this.d = i3;
        }

        public Bitmap a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public int d() {
            return this.d;
        }
    }

    public static class c {
        private int a;
        private int b;
        private int c;

        private c(int i, int i2) {
            this.a = i;
            this.b = i2;
            this.c = 1;
        }

        private c(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }

    public static abstract class d {
        public abstract String a(String str);
    }

    public NexImageLoader(Resources resources, a aVar, d dVar, int i, int i2, int i3) {
        if (resources == null) {
            this.m_assetManager = null;
        } else {
            this.m_assetManager = resources.getAssets();
        }
        this.m_effectResourceLoader = aVar;
        this.m_overlayPathResolver = dVar;
        this.m_jpegMaxWidth = i;
        this.m_jpegMaxHeight = i2;
        this.m_jpegMaxSize = i3;
    }

    public static void calcSampleSize(BitmapFactory.Options options) {
        int i = 1;
        while (i < 8 && ((options.outWidth / i > 1440 && options.outHeight / i > 810) || ((options.outWidth / i) * options.outHeight) / i > 1500000)) {
            i *= 2;
        }
        options.inSampleSize = i;
    }

    public static void calcSampleSize(BitmapFactory.Options options, int i, int i2, int i3) {
        int i4 = 1;
        while (i4 < 8) {
            if (options.outWidth / i4 <= i || options.outHeight / i4 <= i2) {
                if (((options.outWidth / i4) * options.outHeight) / i4 <= (i3 > 0 ? i3 : 1500000)) {
                    break;
                }
            }
            i4 *= 2;
        }
        options.inSampleSize = i4;
    }

    private static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static Bitmap getThumbnail(Context context, String str) {
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, (String) null);
        if (query == null || !query.moveToFirst()) {
            query.close();
            return null;
        }
        int i = query.getInt(query.getColumnIndex("_id"));
        query.close();
        return MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), (long) i, 1, (BitmapFactory.Options) null);
    }

    private static int getThumbnailOrientation(Context context, String str) {
        Uri uri;
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, (String) null);
        if (query == null || !query.moveToFirst()) {
            uri = null;
        } else {
            uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (long) query.getInt(query.getColumnIndex("_id")));
            query.close();
        }
        Uri uri2 = uri;
        if (uri2 != null) {
            String[] strArr = {"orientation"};
            Cursor query2 = context.getContentResolver().query(uri2, strArr, (String) null, (String[]) null, (String) null);
            if (query2 != null && query2.moveToFirst()) {
                return query2.getInt(query2.getColumnIndex(strArr[0]));
            }
        }
        return -1;
    }

    public static b loadBitmap(InputStream inputStream, int i, int i2, int i3) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        BitmapFactory.Options options = new BitmapFactory.Options();
        int i4 = 1;
        options.inJustDecodeBounds = true;
        try {
            bufferedInputStream.mark(inputStream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.decodeStream(bufferedInputStream, (Rect) null, options);
        try {
            bufferedInputStream.reset();
            options.inJustDecodeBounds = false;
            int i5 = options.outWidth;
            int i6 = options.outHeight;
            while (i4 < 8) {
                if (options.outWidth / i4 <= i || options.outHeight / i4 <= i2) {
                    if (((options.outWidth / i4) * options.outHeight) / i4 <= (i3 > 0 ? i3 : 1500000)) {
                        break;
                    }
                }
                i4 *= 2;
            }
            options.inSampleSize = i4;
            Log.d(LOG_TAG, "loadBitmap from stream width=" + options.outWidth + " height=" + options.outHeight + " sampleSize=" + i4);
            Bitmap decodeStream = BitmapFactory.decodeStream(bufferedInputStream, (Rect) null, options);
            try {
                bufferedInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return decodeStream == null ? new b((Bitmap) null, 0, 0) : new b(decodeStream, i5, i6);
        } catch (IOException e3) {
            throw new RuntimeException("Failed to reset stream", e3);
        }
    }

    public static b loadBitmap(String str, int i, int i2) {
        return loadBitmap(str, i, i2, Reader.READ_DONE, 0);
    }

    public static b loadBitmap(String str, int i, int i2, int i3, int i4) {
        return loadBitmap(str, i, i2, i3, (Bitmap.Config) null, i4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
        r2 = sLoadedBitmapCache.get((r13 = (android.graphics.Bitmap) r2.get()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00bb, code lost:
        r1 = r0.toLowerCase(java.util.Locale.US);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c7, code lost:
        if (r1.endsWith(".jpeg") != false) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cf, code lost:
        if (r1.endsWith(".jpg") == false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d2, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r1 = new android.media.ExifInterface(r0).getAttributeInt("Orientation", 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00df, code lost:
        r2 = new android.graphics.BitmapFactory.Options();
        r2.inJustDecodeBounds = true;
        android.graphics.BitmapFactory.decodeFile(r0, r2);
        r2.inJustDecodeBounds = false;
        r2.inPreferredConfig = r9;
        r4 = r2.outWidth;
        r5 = r2.outHeight;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f3, code lost:
        if (r10 < 8) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00fa, code lost:
        if ((r2.outWidth / r10) > r19) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0101, code lost:
        if ((r2.outHeight / r10) > r20) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0104, code lost:
        r13 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0106, code lost:
        r8 = ((r2.outWidth / r10) * r2.outHeight) / r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x010d, code lost:
        if (r21 > 0) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x010f, code lost:
        r14 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0112, code lost:
        r14 = 1500000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0115, code lost:
        if (r8 > r14) goto L_0x0117;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0117, code lost:
        r10 = r10 * 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011a, code lost:
        r2.inSampleSize = r10;
        android.util.Log.d(LOG_TAG, "loadBitmap width=" + r2.outWidth + " height=" + r2.outHeight + " sampleSize=" + r10 + " name='" + r0 + "'");
        r0 = android.graphics.BitmapFactory.decodeFile(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0157, code lost:
        if (r0 == null) goto L_0x0159;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x015e, code lost:
        return new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b((android.graphics.Bitmap) null, 0, 0, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0164, code lost:
        switch(r1) {
            case 0: goto L_0x0237;
            case 1: goto L_0x0237;
            case 2: goto L_0x01e9;
            case 3: goto L_0x01bd;
            case 4: goto L_0x020f;
            case 5: goto L_0x0237;
            case 6: goto L_0x0193;
            case 7: goto L_0x0237;
            case 8: goto L_0x0169;
            default: goto L_0x0167;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0169, code lost:
        r2 = android.graphics.Bitmap.createBitmap(r0.getHeight(), r0.getWidth(), android.graphics.Bitmap.Config.ARGB_8888);
        r6 = new android.graphics.Canvas(r2);
        r10 = new android.graphics.Matrix();
        r10.setRotate(270.0f);
        r10.postTranslate(0.0f, (float) r0.getWidth());
        r6.drawBitmap(r0, r10, (android.graphics.Paint) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0193, code lost:
        r2 = android.graphics.Bitmap.createBitmap(r0.getHeight(), r0.getWidth(), android.graphics.Bitmap.Config.ARGB_8888);
        r6 = new android.graphics.Canvas(r2);
        r10 = new android.graphics.Matrix();
        r10.setRotate(90.0f);
        r10.postTranslate((float) r0.getHeight(), 0.0f);
        r6.drawBitmap(r0, r10, (android.graphics.Paint) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01bd, code lost:
        r2 = android.graphics.Bitmap.createBitmap(r0.getWidth(), r0.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        r10 = new android.graphics.Canvas(r2);
        r12 = new android.graphics.Matrix();
        r12.setScale(-1.0f, -1.0f);
        r12.postTranslate((float) r0.getWidth(), (float) r0.getHeight());
        r10.drawBitmap(r0, r12, (android.graphics.Paint) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01e9, code lost:
        r12 = new android.graphics.Canvas(android.graphics.Bitmap.createBitmap(r0.getWidth(), r0.getHeight(), android.graphics.Bitmap.Config.ARGB_8888));
        r10 = new android.graphics.Matrix();
        r10.setScale(-1.0f, 1.0f);
        r10.postTranslate((float) r0.getWidth(), 0.0f);
        r12.drawBitmap(r0, r10, (android.graphics.Paint) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x020f, code lost:
        r10 = android.graphics.Bitmap.createBitmap(r0.getWidth(), r0.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        r12 = new android.graphics.Canvas(r10);
        r13 = new android.graphics.Matrix();
        r13.setScale(1.0f, -1.0f);
        r13.postTranslate(0.0f, (float) r0.getHeight());
        r12.drawBitmap(r0, r13, (android.graphics.Paint) null);
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0237, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0238, code lost:
        if (r9 == null) goto L_0x0258;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0240, code lost:
        r0 = android.graphics.Bitmap.createBitmap(r2.getWidth(), r2.getHeight(), r9);
        new android.graphics.Canvas(r0).drawBitmap(r2, 0.0f, 0.0f, (android.graphics.Paint) null);
        r2.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0258, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x025a, code lost:
        if (r1 == 5) goto L_0x026a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0264, code lost:
        r1 = new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b(r0, r4, r5, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x026a, code lost:
        r1 = new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b(r0, r5, r4, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x026f, code lost:
        sBitmapCache.put(r1, new java.lang.ref.WeakReference(r0));
        sLoadedBitmapCache.put(r0, new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.c(r1.b(), r1.c(), (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x028b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0117, code lost:
        continue;
     */
    public static b loadBitmap(String str, int i, int i2, int i3, Bitmap.Config config, int i4) {
        Bitmap bitmap;
        c cVar;
        String str2 = str;
        Bitmap.Config config2 = config;
        int i5 = 1;
        if (i4 == 1) {
            return loadBitmapThumb(str, i, i2, i3, config);
        }
        Log.d(LOG_TAG, "loadBitmap");
        a aVar = new a(str, i, i2, i3, config, false);
        synchronized (sBitmapCacheLock) {
            WeakReference weakReference = sBitmapCache.get(aVar);
            b bVar = (weakReference == null || bitmap == null || cVar == null) ? null : new b(bitmap, cVar.a(), cVar.b(), cVar.c());
            sCleanCacheCount++;
            if (sCleanCacheCount > 30) {
                sCleanCacheCount = 0;
                ArrayList<a> arrayList = null;
                for (Map.Entry next : sBitmapCache.entrySet()) {
                    if (((WeakReference) next.getValue()).get() == null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                        }
                        arrayList.add(next.getKey());
                    }
                }
                if (arrayList != null) {
                    for (a remove : arrayList) {
                        sBitmapCache.remove(remove);
                    }
                }
            }
            if (bVar != null) {
                return bVar;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00af, code lost:
        r1 = getThumbnail(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00bb, code lost:
        if (r1 != null) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c8, code lost:
        return new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b((android.graphics.Bitmap) null, 0, 0, 0, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c9, code lost:
        r0 = getThumbnailOrientation(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00d7, code lost:
        if (r0 == 90) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00db, code lost:
        if (r0 == 180) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00df, code lost:
        if (r0 == 270) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e1, code lost:
        r10 = new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b(r1, 1280, 720, 2, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ef, code lost:
        r16 = new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b(rotateImage(r1, com.nexstreaming.nexeditorsdk.nexClip.kClip_Rotate_270), 720, 1280, 2, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0103, code lost:
        r10 = new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b(rotateImage(r1, com.nexstreaming.nexeditorsdk.nexClip.kClip_Rotate_180), 1280, 720, 2, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0114, code lost:
        r16 = new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.b(rotateImage(r1, 90), 720, 1280, 2, (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0127, code lost:
        sBitmapCache.put(r1, new java.lang.ref.WeakReference(r1));
        sLoadedBitmapCache.put(r1, new com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.c(r0.b(), r0.c(), r0.d(), (com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader.AnonymousClass1) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0147, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0032, code lost:
        r2 = sLoadedBitmapCache.get((r11 = (android.graphics.Bitmap) r2.get()));
     */
    public static b loadBitmapThumb(String str, int i, int i2, int i3, Bitmap.Config config) {
        Bitmap bitmap;
        c cVar;
        String str2 = str;
        Log.d(LOG_TAG, "loadBitmapThumb");
        a aVar = new a(str, i, i2, i3, config, true);
        synchronized (sBitmapCacheLock) {
            WeakReference weakReference = sBitmapCache.get(aVar);
            b bVar = (weakReference == null || bitmap == null || cVar == null) ? null : new b(bitmap, cVar.a(), cVar.b(), cVar.c());
            sCleanCacheCount++;
            if (sCleanCacheCount > 30) {
                sCleanCacheCount = 0;
                ArrayList<a> arrayList = null;
                for (Map.Entry next : sBitmapCache.entrySet()) {
                    if (((WeakReference) next.getValue()).get() == null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                        }
                        arrayList.add(next.getKey());
                    }
                }
                if (arrayList != null) {
                    for (a remove : arrayList) {
                        sBitmapCache.remove(remove);
                    }
                }
            }
            if (bVar != null) {
                return bVar;
            }
        }
    }

    private String pdecode(String str) {
        int i;
        StringBuilder sb = new StringBuilder(str);
        String str2 = str;
        int i2 = -1;
        while (true) {
            i2 = sb.indexOf("%", i2 + 1);
            if (i2 != -1 && (i = i2 + 2) < sb.length()) {
                int i3 = i2 + 1;
                int indexOf = "0123456789ABCDEF".indexOf(str2.charAt(i3));
                int indexOf2 = "0123456789ABCDEF".indexOf(str2.charAt(i));
                if (!(indexOf == -1 || indexOf2 == -1)) {
                    sb.setCharAt(i2, (char) ((indexOf << 4) | indexOf2));
                    sb.delete(i3, i2 + 3);
                    str2 = sb.toString();
                }
            }
        }
        return sb.toString();
    }

    private static String relativePath(String str, String str2) {
        if (str2.startsWith("..") || str2.contains("/..")) {
            throw new SecurityException("Parent Path References Not Allowed");
        } else if (str.endsWith("/")) {
            return str + str2;
        } else {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf < 0) {
                return str2;
            }
            return str.substring(0, lastIndexOf + 1) + str2;
        }
    }

    public static Bitmap rotateAndFlipImage(Bitmap bitmap, int i, boolean z, boolean z2) {
        if ((i == 0 && !z && !z2) || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.preRotate((float) i, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        float f = -1.0f;
        float f2 = z ? -1.0f : 1.0f;
        if (!z2) {
            f = 1.0f;
        }
        matrix.preScale(f2, f, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return bitmap != createBitmap ? createBitmap : bitmap;
        } catch (OutOfMemoryError e) {
            Log.e(LOG_TAG, "rotateImage Error : " + e);
            return bitmap;
        }
    }

    public static Bitmap rotateImage(Bitmap bitmap, int i) {
        if (i == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate((float) i, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return bitmap != createBitmap ? createBitmap : bitmap;
        } catch (OutOfMemoryError e) {
            Log.e(LOG_TAG, "rotateImage Error : " + e);
            return bitmap;
        }
    }

    public byte[] callbackReadAssetItemFile(String str, String str2) {
        InputStream a2;
        f c2 = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(str);
        if (c2 == null) {
            Log.d(LOG_TAG, "Error get assetItem id=" + str);
            return null;
        }
        try {
            AssetPackageReader a3 = AssetPackageReader.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), c2.getPackageURI(), c2.getAssetPackage().getAssetId());
            String filePath = (str2 == null || str2.length() < 1) ? c2.getFilePath() : relativePath(c2.getFilePath(), str2);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                a2 = a3.a(filePath);
                copy(a2, byteArrayOutputStream);
                a2.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                Log.d(LOG_TAG, "Error reading file", e);
                return null;
            } catch (Throwable th) {
                a2.close();
                throw th;
            }
        } catch (IOException e2) {
            Log.d(LOG_TAG, "Error making reader", e2);
            return null;
        }
    }

    public NexImage openFile(String str, int i) {
        if (str.startsWith("@solid:") && str.endsWith(".jpg")) {
            int parseLong = (int) Long.parseLong(str.substring(7, 15), 16);
            int[] iArr = new int[576];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr[i2] = parseLong;
            }
            return new NexImage(Bitmap.createBitmap(iArr, 32, 18, Bitmap.Config.ARGB_8888), 32, 18);
        } else if (str.startsWith("@assetItem:")) {
            String substring = str.substring(11);
            a aVar = this.m_effectResourceLoader;
            if (aVar != null) {
                try {
                    Bitmap a2 = loadBitmap(aVar.b(substring, (String) null), this.m_jpegMaxWidth, this.m_jpegMaxHeight, this.m_jpegMaxSize).a();
                    if (a2 != null) {
                        Log.d(LOG_TAG, "@assetItem bitmap width=" + a2.getWidth() + " height=" + a2.getHeight());
                        return new NexImage(a2, a2.getWidth() & -2, a2.getHeight() & -2);
                    }
                } catch (IOException unused) {
                }
            }
            return null;
        } else {
            try {
                b loadBitmap = loadBitmap(str, this.m_jpegMaxWidth, this.m_jpegMaxHeight, this.m_jpegMaxSize, i);
                Bitmap a3 = loadBitmap.a();
                int d2 = loadBitmap.d();
                if (a3 != null) {
                    Log.d(LOG_TAG, "Actual bitmap width=" + a3.getWidth() + " height=" + a3.getHeight() + ", loadedtype=" + d2);
                    return new NexImage(a3, a3.getWidth() & -2, a3.getHeight() & -2, d2);
                }
            } catch (Exception unused2) {
            }
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x008b, code lost:
        r3 = r3 + r8;
     */
    public byte[] openThemeFile(String str) {
        String str2;
        int indexOf = str.indexOf(47);
        int i = 0;
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        } else {
            str2 = "";
        }
        a aVar = this.m_effectResourceLoader;
        if (aVar != null) {
            try {
                if (aVar.a(str2, str).exists()) {
                    InputStream b2 = this.m_effectResourceLoader.b(str2, str);
                    Log.e(LOG_TAG, "get size begin loading bitmap for effect(" + str2 + ") : " + str);
                    int i2 = 0;
                    while (true) {
                        int skip = (int) b2.skip(2147483647L);
                        if (skip <= 0) {
                            break;
                        }
                        i2 += skip;
                    }
                    b2.close();
                    InputStream b3 = this.m_effectResourceLoader.b(str2, str);
                    Log.e(LOG_TAG, "get size end loading bitmap for effect(" + str2 + ") : " + str + " size=" + i2);
                    byte[] bArr = new byte[i2];
                    int i3 = i2;
                    do {
                        int read = b3.read(bArr, i, i3);
                        if (-1 == read || (i3 = i2 - i) <= 0) {
                            return bArr;
                        }
                        int read2 = b3.read(bArr, i, i3);
                        break;
                    } while ((i3 = i2 - i) <= 0);
                    return bArr;
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error loading bitmap for effect(" + str2 + ") : " + str);
                e.printStackTrace();
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:202:0x076a, code lost:
        if (r5 <= r11) goto L_0x076f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x076c, code lost:
        r15 = r10;
        r3 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x076f, code lost:
        r3 = r5;
        r15 = r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0628  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0653  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x066b  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0670  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0675  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x067e  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0685  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0691  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x069d  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x06a9  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x06ad  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x06b6  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x06b9  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x06bc  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x06db  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x06e4  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x06e8  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x06eb  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x06ef  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x06f2  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x0751 A[LOOP:3: B:186:0x06f7->B:199:0x0751, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0766 A[EDGE_INSN: B:411:0x0766->B:200:0x0766 ?: BREAK  
    EDGE_INSN: B:413:0x0766->B:200:0x0766 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x07cf  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x07d1  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x07d4  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x07d9  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x07dc  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x080f  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x083c  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0842  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x0855  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0865  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x088c  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x08b0  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x08c8  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0a5d  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x0a6b  */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0b7c  */
    /* JADX WARNING: Removed duplicated region for block: B:356:0x0b8a  */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x0c4c  */
    public NexImage openThemeImage(String str) {
        int i;
        int i2;
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3;
        Bitmap bitmap4;
        Bitmap bitmap5;
        AssetManager assetManager;
        Bitmap decodeFile;
        AssetManager assetManager2;
        Typeface typeface;
        String str2;
        boolean z;
        int i3;
        float f;
        float f2;
        float f3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        float f4;
        TextUtils.TruncateAt truncateAt;
        Layout.Alignment alignment;
        int i9;
        float f5;
        int i10;
        StaticLayout staticLayout;
        int height;
        StaticLayout staticLayout2;
        int i11;
        int i12;
        int i13;
        int i14;
        StaticLayout staticLayout3;
        float f6;
        int i15;
        float f7;
        int i16;
        double d2;
        File file;
        String str3;
        Typeface typeface2;
        float f8;
        float f9;
        int i17;
        float f10;
        int i18;
        int i19;
        String str4;
        float f11;
        String[] strArr;
        float f12;
        float f13;
        int i20;
        float f14;
        String str5;
        float f15;
        String str6;
        float f16;
        String str7;
        int i21;
        int abs;
        String str8 = str;
        if (str8.contains(".force_effect/")) {
            str8 = str8.replace(".force_effect/", "/");
        }
        if (str8.startsWith("[ThemeImage]/@special:") || str8.startsWith("[PvwThImage]/@special:")) {
            return null;
        }
        String str9 = "";
        if (str8.startsWith(TAG_Text)) {
            String substring = str8.substring(6);
            int indexOf = substring.indexOf(";;");
            String substring2 = substring.substring(indexOf + 2);
            int indexOf2 = substring2.indexOf(27);
            if (indexOf2 > -1) {
                a aVar = this.m_effectResourceLoader;
                if (aVar != null) {
                    try {
                        typeface = aVar.a(substring2.substring(0, indexOf2));
                    } catch (Font.TypefaceLoadException e) {
                        Log.e(LOG_TAG, "typeface error", e);
                    }
                    substring2 = substring2.substring(indexOf2 + 1);
                }
                typeface = null;
                substring2 = substring2.substring(indexOf2 + 1);
            } else {
                typeface = null;
            }
            if (substring2.length() < 1) {
                substring2 = "Title Text Goes Here";
            }
            String[] strArr2 = {substring2};
            Log.d(LOG_TAG, "==== User Text: " + substring2);
            String[] split = substring.substring(0, indexOf).split(";");
            HashMap hashMap = new HashMap();
            for (int i22 = 0; i22 < split.length; i22++) {
                String[] split2 = split[i22].split("=");
                if (split2.length < 2) {
                    hashMap.put(split2[0], str9);
                } else {
                    hashMap.put(split2[0], split2[1]);
                }
            }
            int parseLong = (int) Long.parseLong((String) hashMap.get("flags"), 16);
            int parseInt = Integer.parseInt((String) hashMap.get("align"));
            int parseInt2 = Integer.parseInt((String) hashMap.get("longtext"));
            int parseInt3 = Integer.parseInt((String) hashMap.get(nexExportFormat.TAG_FORMAT_WIDTH));
            int parseInt4 = Integer.parseInt((String) hashMap.get(nexExportFormat.TAG_FORMAT_HEIGHT));
            String str10 = LOG_TAG;
            int parseLong2 = (int) Long.parseLong((String) hashMap.get("fillcolor"), 16);
            int i23 = parseInt2;
            int parseLong3 = (int) Long.parseLong((String) hashMap.get("shadowcolor"), 16);
            int parseLong4 = (int) Long.parseLong((String) hashMap.get("maxlines"), 16);
            float parseFloat = Float.parseFloat((String) hashMap.get("skewx"));
            float parseFloat2 = Float.parseFloat((String) hashMap.get("scalex"));
            float f17 = parseFloat;
            float parseFloat3 = Float.parseFloat((String) hashMap.get("size"));
            float parseFloat4 = Float.parseFloat((String) hashMap.get("strokewidth"));
            float parseFloat5 = Float.parseFloat((String) hashMap.get("spacingmult"));
            float parseFloat6 = Float.parseFloat((String) hashMap.get("spacingadd"));
            float parseFloat7 = Float.parseFloat((String) hashMap.get("shadowradius"));
            float parseFloat8 = Float.parseFloat((String) hashMap.get("textblur"));
            int parseInt5 = Integer.parseInt((String) hashMap.get("blurtype"));
            int parseInt6 = Integer.parseInt((String) hashMap.get("margin"));
            float parseFloat9 = Float.parseFloat((String) hashMap.get("shadowoffsx"));
            float parseFloat10 = Float.parseFloat((String) hashMap.get("shadowoffsy"));
            int i24 = parseLong2;
            String str11 = (String) hashMap.get("baseid");
            String pdecode = pdecode((String) hashMap.get("text"));
            int i25 = parseInt & NXT_VALIGNMASK;
            int i26 = parseInt & 15;
            int parseLong5 = (int) Long.parseLong((String) hashMap.get("bgcolor"), 16);
            int parseLong6 = (int) Long.parseLong((String) hashMap.get("strokecolor"), 16);
            int i27 = parseLong3;
            String str12 = pdecode;
            int i28 = 0;
            while (i28 < 3) {
                int i29 = i25;
                int i30 = parseLong4;
                int i31 = parseInt4;
                int i32 = i26;
                if (i28 < strArr2.length) {
                    String str13 = strArr2[i28];
                    strArr = strArr2;
                    int length = str13.length();
                    str4 = str11;
                    f11 = parseFloat2;
                    int i33 = length;
                    i18 = parseInt3;
                    int i34 = 0;
                    int i35 = 0;
                    while (i35 < length) {
                        int i36 = parseLong;
                        if (str13.charAt(i35) == ' ' && (abs = Math.abs((length / 2) - i35)) < i33) {
                            i33 = abs;
                            i34 = i35;
                        }
                        i35++;
                        parseLong = i36;
                    }
                    i19 = parseLong;
                    if (i34 <= 0 || (i21 = i34 + 1) >= length) {
                        f16 = f17;
                        f15 = parseFloat3;
                        f10 = parseFloat8;
                        i17 = parseInt5;
                        f9 = parseFloat9;
                        f8 = parseFloat10;
                        str7 = str13;
                        f14 = parseFloat7;
                        str6 = str9;
                        i20 = parseInt6;
                    } else {
                        String substring3 = str13.substring(0, i34);
                        f16 = f17;
                        f15 = parseFloat3;
                        f10 = parseFloat8;
                        i17 = parseInt5;
                        f9 = parseFloat9;
                        f8 = parseFloat10;
                        i20 = parseInt6;
                        str6 = str13.substring(i21);
                        str7 = substring3;
                        f14 = parseFloat7;
                    }
                    StringBuilder sb = new StringBuilder();
                    f13 = f15;
                    sb.append("%");
                    int i37 = i28 + 1;
                    sb.append(i37);
                    String replace = str12.replace(sb.toString(), str13);
                    StringBuilder sb2 = new StringBuilder();
                    f12 = f16;
                    sb2.append("%f");
                    sb2.append(i37);
                    str5 = replace.replace(sb2.toString(), str7).replace("%b" + i37, str6);
                    if (str13.length() == 1) {
                        str5 = str5.replace("%s" + i37, str13.substring(0, 1)).replace("%!s" + i37, str9).replace("%e" + i37, str9).replace("%!e" + i37, str9).replace("%m" + i37, str9);
                    } else if (str13.length() < 1) {
                        str5 = str5.replace("%s" + i37, str9).replace("%!s" + i37, str9).replace("%e" + i37, str9).replace("%!e" + i37, str9).replace("%m" + i37, str9);
                    } else if (str13.length() > 1) {
                        int i38 = length - 1;
                        str5 = str5.replace("%s" + i37, str13.substring(0, 1)).replace("%!s" + i37, str13.substring(1, length)).replace("%e" + i37, str13.substring(i38, length)).replace("%!e" + i37, str13.substring(0, i38)).replace("%m" + i37, str13.substring(1, i38));
                    }
                } else {
                    i19 = parseLong;
                    str4 = str11;
                    f11 = parseFloat2;
                    strArr = strArr2;
                    i18 = parseInt3;
                    f14 = parseFloat7;
                    f10 = parseFloat8;
                    i17 = parseInt5;
                    f9 = parseFloat9;
                    f8 = parseFloat10;
                    f12 = f17;
                    f13 = parseFloat3;
                    i20 = parseInt6;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("%");
                    int i39 = i28 + 1;
                    sb3.append(i39);
                    str5 = str12.replace(sb3.toString(), str9).replace("%s" + i39, str9).replace("%!s" + i39, str9).replace("%e" + i39, str9).replace("%!e" + i39, str9).replace("%m" + i39, str9);
                }
                str12 = str5;
                i28++;
                parseInt6 = i20;
                parseFloat3 = f13;
                f17 = f12;
                i26 = i32;
                strArr2 = strArr;
                parseFloat2 = f11;
                str11 = str4;
                parseLong = i19;
                i25 = i29;
                parseLong4 = i30;
                parseInt4 = i31;
                parseInt3 = i18;
                parseFloat8 = f10;
                parseInt5 = i17;
                parseFloat9 = f9;
                parseFloat10 = f8;
                parseFloat7 = f14;
            }
            int i40 = parseLong;
            int i41 = parseInt4;
            String str14 = str11;
            float f18 = parseFloat2;
            int i42 = i26;
            int i43 = i25;
            int i44 = parseLong4;
            int i45 = parseInt3;
            float f19 = parseFloat7;
            float f20 = parseFloat8;
            int i46 = parseInt5;
            float f21 = parseFloat9;
            float f22 = parseFloat10;
            float f23 = f17;
            float f24 = parseFloat3;
            int i47 = parseInt6;
            boolean matches = str12.matches(".*[[\\u0400-\\u052F][\\u2DE0-\\u2DFF][\\uA640-\\uA69F]].*");
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            String str15 = (String) hashMap.get("typeface");
            if (typeface != null) {
                textPaint.setTypeface(typeface);
            } else if (matches) {
                int i48 = (i40 & 1) != 0 ? 1 : 0;
                if ((i40 & 2) != 0) {
                    i48 |= 2;
                }
                textPaint.setTypeface(Typeface.defaultFromStyle(i48));
            } else if (str15.startsWith(TYPEFACE_SYSTEM)) {
                String substring4 = str15.substring(8);
                int i49 = (i40 & 1) != 0 ? 1 : 0;
                if ((i40 & 2) != 0) {
                    i49 |= 2;
                }
                textPaint.setTypeface(Typeface.create(substring4, i49));
            } else if (str15.startsWith(TYPEFACE_FILE)) {
                textPaint.setTypeface(Typeface.createFromFile(str15.substring(5)));
            } else {
                if (str15.startsWith(TYPEFACE_ASSET)) {
                    String substring5 = str15.substring(6);
                    AssetManager assetManager3 = this.m_assetManager;
                    if (assetManager3 != null) {
                        textPaint.setTypeface(Typeface.createFromAsset(assetManager3, substring5));
                    }
                } else {
                    if (str15.startsWith(TYPEFACE_THEME)) {
                        String substring6 = str15.substring(6);
                        a aVar2 = this.m_effectResourceLoader;
                        if (aVar2 != null) {
                            try {
                                textPaint.setTypeface(aVar2.a(substring6));
                            } catch (Font.TypefaceLoadException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (str15.startsWith(TYPEFACE_FONTID)) {
                        textPaint.setTypeface(com.nexstreaming.kminternal.kinemaster.fonts.c.a().b(str15.substring(7)));
                    } else {
                        if (str15.startsWith(TYPEFACE_FONTFILE)) {
                            String substring7 = str15.substring(9);
                            a aVar3 = this.m_effectResourceLoader;
                            if (aVar3 != null) {
                                str3 = str14;
                                try {
                                    typeface2 = aVar3.c(str3, substring7);
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                } catch (Font.TypefaceLoadException e4) {
                                    e4.printStackTrace();
                                }
                            } else {
                                str3 = str14;
                                typeface2 = null;
                            }
                            if (typeface2 != null) {
                                textPaint.setTypeface(typeface2);
                            } else {
                                str2 = str10;
                                Log.d(str2, String.format("TYPEFACE NOT FOUND : base_id=%s, font=%s", new Object[]{str3, substring7}));
                            }
                        } else {
                            str2 = str10;
                            String str16 = str14;
                            a aVar4 = this.m_effectResourceLoader;
                            if (aVar4 != null) {
                                try {
                                    file = aVar4.a(str16, str15);
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                                if (file != null) {
                                    Log.i(str2, "TYPEFACE NOT FOUND : base_id=" + str16);
                                } else if (!file.exists()) {
                                    Log.i(str2, "TYPEFACE FILE DOES NOT EXIST : base_id=" + str16 + "; f=" + file);
                                } else {
                                    textPaint.setTypeface(Typeface.createFromFile(file));
                                }
                            }
                            file = null;
                            if (file != null) {
                            }
                        }
                        if ((i40 & 16) != 0) {
                            z = true;
                            textPaint.setUnderlineText(true);
                        } else {
                            z = true;
                        }
                        if ((i40 & 32) != 0) {
                            textPaint.setStrikeThruText(z);
                        }
                        i3 = i40;
                        if ((i3 & 128) != 0) {
                            textPaint.setSubpixelText(z);
                        }
                        if ((i3 & 512) != 0) {
                            textPaint.setLinearText(z);
                        }
                        f = f18;
                        if (((double) f) > 0.0d) {
                            textPaint.setTextScaleX(f);
                        }
                        f2 = f23;
                        if (((double) f2) > 0.0d) {
                            textPaint.setTextSkewX(f2);
                        }
                        f3 = f24;
                        if (((double) f3) > 0.0d) {
                            textPaint.setTextSize(f3);
                        } else {
                            f3 = textPaint.getTextSize();
                        }
                        int i50 = (i3 & 12) == 0 ? i3 | 4 : i3;
                        if (i45 > 0) {
                            if (i23 != 0) {
                                int i51 = i23;
                                if (i51 == 2) {
                                    truncateAt = TextUtils.TruncateAt.START;
                                } else if (i51 == 3) {
                                    truncateAt = TextUtils.TruncateAt.MIDDLE;
                                } else if (i51 == 4) {
                                    truncateAt = TextUtils.TruncateAt.END;
                                }
                                Layout.Alignment alignment2 = Layout.Alignment.ALIGN_NORMAL;
                                int i52 = i42;
                                alignment = i52 == 1 ? i52 != 2 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER;
                                i9 = i45 <= 0 ? i45 : 2048;
                                int i53 = i41 <= 0 ? i41 : 2048;
                                f5 = f3;
                                i10 = i45;
                                while (true) {
                                    int i54 = i47 * 2;
                                    int i55 = i10 - i54;
                                    staticLayout = new StaticLayout(str12, 0, str12.length(), textPaint, i55, alignment, parseFloat5, parseFloat6, true, truncateAt, i55);
                                    i10 = staticLayout.getWidth() + i54;
                                    height = staticLayout.getHeight() + i54;
                                    if (i10 <= i9 || height > i53) {
                                        i16 = i44;
                                    } else {
                                        i16 = i44;
                                        if (i16 < 1 || staticLayout.getLineCount() <= i16) {
                                            i11 = height;
                                            int i56 = i10;
                                            staticLayout2 = staticLayout;
                                        }
                                    }
                                    String str17 = str12;
                                    Layout.Alignment alignment3 = alignment;
                                    d2 = (double) f5;
                                    staticLayout2 = staticLayout;
                                    String str18 = str17;
                                    if (d2 > ((double) f3) / 5.0d && d2 > 6.0d) {
                                        f5 -= Math.max(1.0f, f3 / 12.0f);
                                        textPaint.setTextSize(f5);
                                        i44 = i16;
                                        alignment = alignment3;
                                        str12 = str18;
                                    } else if (i10 <= i9) {
                                        i9 = i10;
                                    }
                                }
                                i11 = height;
                                int i562 = i10;
                                staticLayout2 = staticLayout;
                                if ((i50 & 1024) == 0 || i45 <= 0 || i41 <= 0) {
                                    i13 = 2048;
                                    i12 = 0;
                                } else {
                                    StringBuilder sb4 = new StringBuilder();
                                    sb4.append("VAlign=");
                                    int i57 = i43;
                                    sb4.append(i57);
                                    sb4.append(" originalWidth=");
                                    int i58 = i45;
                                    sb4.append(i58);
                                    sb4.append(" originalHeight=");
                                    int i59 = i41;
                                    sb4.append(i59);
                                    sb4.append(" width=");
                                    sb4.append(i562);
                                    sb4.append(" height=");
                                    sb4.append(i11);
                                    Log.d(str2, sb4.toString());
                                    i12 = i57 != 16 ? i57 != 32 ? 0 : i59 - i11 : (i59 - i11) / 2;
                                    i562 = i58;
                                    i11 = i59;
                                    i13 = 2048;
                                }
                                i14 = i562 <= i13 ? i13 : i562;
                                if (i11 > i13) {
                                    i11 = 2048;
                                }
                                if (i14 < 1) {
                                    i14 = 1;
                                }
                                if (i11 < 1) {
                                    i11 = 1;
                                }
                                bitmap = Bitmap.createBitmap(i14, i11, Bitmap.Config.ARGB_8888);
                                int width = bitmap.getWidth() & -2;
                                i5 = bitmap.getHeight() & -2;
                                Canvas canvas = new Canvas(bitmap);
                                canvas.drawColor(parseLong5);
                                canvas.save();
                                float f25 = (float) i47;
                                canvas.translate(f25, f25);
                                canvas.translate(0.0f, (float) i12);
                                if ((i50 & 256) == 0 || f19 <= 0.0f) {
                                    staticLayout3 = staticLayout2;
                                } else {
                                    canvas.save();
                                    canvas.translate(f21, f22);
                                    textPaint.setMaskFilter(new BlurMaskFilter(f19, BlurMaskFilter.Blur.SOLID));
                                    textPaint.setStyle(Paint.Style.FILL);
                                    textPaint.setColor(i27);
                                    staticLayout3 = staticLayout2;
                                    staticLayout3.draw(canvas);
                                    textPaint.setMaskFilter((MaskFilter) null);
                                    canvas.restore();
                                }
                                if ((i50 & 4096) == 0) {
                                    textPaint.setStyle(Paint.Style.STROKE);
                                    i15 = parseLong6;
                                    textPaint.setColor(i15);
                                    f6 = parseFloat4;
                                    textPaint.setStrokeWidth(f6);
                                    staticLayout3.draw(canvas);
                                } else {
                                    f6 = parseFloat4;
                                    i15 = parseLong6;
                                }
                                f7 = f20;
                                if (((double) f7) > 1.0E-5d) {
                                    BlurMaskFilter.Blur blur = BlurMaskFilter.Blur.NORMAL;
                                    int i60 = i46;
                                    if (i60 == 0) {
                                        blur = BlurMaskFilter.Blur.NORMAL;
                                    } else if (i60 == 1) {
                                        blur = BlurMaskFilter.Blur.SOLID;
                                    } else if (i60 == 2) {
                                        blur = BlurMaskFilter.Blur.INNER;
                                    } else if (i60 == 3) {
                                        blur = BlurMaskFilter.Blur.OUTER;
                                    }
                                    textPaint.setMaskFilter(new BlurMaskFilter(f7, blur));
                                }
                                if ((i50 & 4) != 0) {
                                    if ((i50 & 2048) != 0) {
                                        textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                                    }
                                    textPaint.setStyle(Paint.Style.FILL);
                                    textPaint.setColor(i24);
                                    staticLayout3.draw(canvas);
                                    textPaint.setXfermode((Xfermode) null);
                                }
                                if ((i50 & 8) != 0) {
                                    textPaint.setStyle(Paint.Style.STROKE);
                                    textPaint.setColor(i15);
                                    textPaint.setStrokeWidth(f6);
                                    staticLayout3.draw(canvas);
                                }
                                textPaint.setMaskFilter((MaskFilter) null);
                                canvas.restore();
                                i4 = width;
                            }
                            truncateAt = null;
                            Layout.Alignment alignment22 = Layout.Alignment.ALIGN_NORMAL;
                            int i522 = i42;
                            if (i522 == 1) {
                            }
                            if (i45 <= 0) {
                            }
                            if (i41 <= 0) {
                            }
                            f5 = f3;
                            i10 = i45;
                            while (true) {
                                int i542 = i47 * 2;
                                int i552 = i10 - i542;
                                staticLayout = new StaticLayout(str12, 0, str12.length(), textPaint, i552, alignment, parseFloat5, parseFloat6, true, truncateAt, i552);
                                i10 = staticLayout.getWidth() + i542;
                                height = staticLayout.getHeight() + i542;
                                if (i10 <= i9) {
                                }
                                i16 = i44;
                                String str172 = str12;
                                Layout.Alignment alignment32 = alignment;
                                d2 = (double) f5;
                                staticLayout2 = staticLayout;
                                String str182 = str172;
                                if (d2 > ((double) f3) / 5.0d || d2 > 6.0d) {
                                }
                                f5 -= Math.max(1.0f, f3 / 12.0f);
                                textPaint.setTextSize(f5);
                                i44 = i16;
                                alignment = alignment32;
                                str12 = str182;
                            }
                            i11 = height;
                            int i5622 = i10;
                            staticLayout2 = staticLayout;
                            if ((i50 & 1024) == 0) {
                            }
                            i13 = 2048;
                            i12 = 0;
                            if (i5622 <= i13) {
                            }
                            if (i11 > i13) {
                            }
                            if (i14 < 1) {
                            }
                            if (i11 < 1) {
                            }
                            bitmap = Bitmap.createBitmap(i14, i11, Bitmap.Config.ARGB_8888);
                            int width2 = bitmap.getWidth() & -2;
                            i5 = bitmap.getHeight() & -2;
                            Canvas canvas2 = new Canvas(bitmap);
                            canvas2.drawColor(parseLong5);
                            canvas2.save();
                            float f252 = (float) i47;
                            canvas2.translate(f252, f252);
                            canvas2.translate(0.0f, (float) i12);
                            if ((i50 & 256) == 0 || f19 <= 0.0f) {
                            }
                            if ((i50 & 4096) == 0) {
                            }
                            f7 = f20;
                            if (((double) f7) > 1.0E-5d) {
                            }
                            if ((i50 & 4) != 0) {
                            }
                            if ((i50 & 8) != 0) {
                            }
                            textPaint.setMaskFilter((MaskFilter) null);
                            canvas2.restore();
                            i4 = width2;
                        } else {
                            String str19 = str12;
                            float f26 = parseFloat4;
                            int i61 = i42;
                            int i62 = i24;
                            int i63 = parseLong6;
                            int i64 = parseLong5;
                            int i65 = i41;
                            int i66 = i45;
                            float f27 = f21;
                            float f28 = f22;
                            Rect rect = new Rect();
                            String str20 = str19;
                            textPaint.getTextBounds(str20, 0, str19.length(), rect);
                            int i67 = i24;
                            rect.right += 20;
                            int ceil = (int) Math.ceil((double) f19);
                            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                            int max = (int) Math.max((fontMetrics.bottom * 2.0f) - fontMetrics.top, (float) rect.height());
                            if (i66 < 1 || i65 < 1) {
                                int i68 = ceil * 2;
                                int max2 = Math.max(rect.width(), rect.right) + i68 + ((int) Math.abs(f27));
                                i8 = max + i68 + ((int) Math.abs(f28));
                                if (max2 > 2048) {
                                    max2 = 2048;
                                }
                                if (i8 > 2048) {
                                    i8 = 2048;
                                }
                                i6 = max2;
                                i7 = 1;
                            } else {
                                i8 = i65;
                                i7 = 1;
                                i6 = i66;
                            }
                            if (i6 < i7) {
                                i6 = i7;
                            }
                            if (i8 < i7) {
                                i8 = i7;
                            }
                            if (i61 == i7) {
                                textPaint.setTextAlign(Paint.Align.CENTER);
                                f4 = (float) (i6 / 2);
                            } else if (i61 != 2) {
                                textPaint.setTextAlign(Paint.Align.LEFT);
                                f4 = 20.0f;
                            } else {
                                textPaint.setTextAlign(Paint.Align.RIGHT);
                                f4 = (float) i6;
                            }
                            float f29 = (((float) i8) - fontMetrics.ascent) / 2.0f;
                            float min = f4 + (((float) ceil) - Math.min(0.0f, f27));
                            Bitmap createBitmap = Bitmap.createBitmap(i6, i8, Bitmap.Config.ARGB_8888);
                            i4 = createBitmap.getWidth() & -2;
                            int height2 = createBitmap.getHeight() & -2;
                            Canvas canvas3 = new Canvas(createBitmap);
                            canvas3.drawColor(i64);
                            int i69 = i50 & 4;
                            if (i69 != 0) {
                                textPaint.setStyle(Paint.Style.FILL);
                                textPaint.setColor(i67);
                                canvas3.drawText(str20, min, f29, textPaint);
                            }
                            if ((i50 & 8) != 0) {
                                if (!(i69 == 0 || (i50 & 256) == 0)) {
                                    textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                                }
                                textPaint.setStyle(Paint.Style.STROKE);
                                textPaint.setColor(i63);
                                textPaint.setStrokeWidth(parseFloat4);
                                Path path = new Path();
                                textPaint.getTextPath(str20, 0, str20.length(), min, f29, path);
                                canvas3.drawPath(path, textPaint);
                            }
                            bitmap = createBitmap;
                            i5 = height2;
                        }
                        i = i5;
                        i2 = i4;
                    }
                }
                str2 = str10;
                if ((i40 & 16) != 0) {
                }
                if ((i40 & 32) != 0) {
                }
                i3 = i40;
                if ((i3 & 128) != 0) {
                }
                if ((i3 & 512) != 0) {
                }
                f = f18;
                if (((double) f) > 0.0d) {
                }
                f2 = f23;
                if (((double) f2) > 0.0d) {
                }
                f3 = f24;
                if (((double) f3) > 0.0d) {
                }
                if ((i3 & 12) == 0) {
                }
                if (i45 > 0) {
                }
                i = i5;
                i2 = i4;
            }
            str2 = str10;
            if ((i40 & 16) != 0) {
            }
            if ((i40 & 32) != 0) {
            }
            i3 = i40;
            if ((i3 & 128) != 0) {
            }
            if ((i3 & 512) != 0) {
            }
            f = f18;
            if (((double) f) > 0.0d) {
            }
            f2 = f23;
            if (((double) f2) > 0.0d) {
            }
            f3 = f24;
            if (((double) f3) > 0.0d) {
            }
            if ((i3 & 12) == 0) {
            }
            if (i45 > 0) {
            }
            i = i5;
            i2 = i4;
        } else {
            String str21 = LOG_TAG;
            if (str8.startsWith(TAG_ThemeImage)) {
                String substring8 = str8.substring(12);
                int indexOf3 = substring8.indexOf(47);
                if (indexOf3 >= 0) {
                    str9 = substring8.substring(0, indexOf3);
                    substring8 = substring8.substring(indexOf3 + 1);
                }
                String str22 = substring8;
                a aVar5 = this.m_effectResourceLoader;
                if (aVar5 != null) {
                    try {
                        bitmap5 = BitmapFactory.decodeStream(aVar5.b(str9, str22));
                    } catch (IOException e6) {
                        Log.e(str21, "Error loading bitmap for effect(" + str9 + ") : " + str22);
                        e6.printStackTrace();
                    }
                    if (bitmap5 == null && (assetManager2 = this.m_assetManager) != null) {
                        bitmap5 = BitmapFactory.decodeStream(assetManager2.open(str22));
                    }
                    if (bitmap5 == null) {
                        return new NexImage(bitmap5, bitmap5.getWidth(), bitmap5.getHeight());
                    }
                    Log.e(str21, "Bitmap failed to load for effect(" + str9 + ") : " + str22);
                }
                bitmap5 = null;
                try {
                    bitmap5 = BitmapFactory.decodeStream(assetManager2.open(str22));
                } catch (IOException e7) {
                    Log.e(str21, "Error loading bitmap (general asset mode) for effect(" + str9 + ") : " + str22);
                    e7.printStackTrace();
                    bitmap5 = null;
                }
                if (bitmap5 == null) {
                }
            } else {
                if (str8.startsWith(TAG_Overlay)) {
                    if (this.m_overlayPathResolver == null) {
                        Bitmap createBitmap2 = Bitmap.createBitmap(8, 8, Bitmap.Config.ARGB_8888);
                        return new NexImage(createBitmap2, createBitmap2.getWidth(), createBitmap2.getHeight());
                    }
                    String a2 = this.m_overlayPathResolver.a(str8.substring(9));
                    if (a2 == null) {
                        Bitmap createBitmap3 = Bitmap.createBitmap(8, 8, Bitmap.Config.ARGB_8888);
                        return new NexImage(createBitmap3, createBitmap3.getWidth(), createBitmap3.getHeight());
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(a2, options);
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 1;
                    decodeFile = BitmapFactory.decodeFile(a2, options);
                    if (decodeFile != null) {
                        return new NexImage(decodeFile, decodeFile.getWidth(), decodeFile.getHeight());
                    }
                    i2 = 0;
                    i = 0;
                } else if (str8.startsWith(TAG_PreviewThemeImage)) {
                    String substring9 = str8.substring(12);
                    int indexOf4 = substring9.indexOf(47);
                    if (indexOf4 >= 0) {
                        str9 = substring9.substring(0, indexOf4);
                        substring9 = substring9.substring(indexOf4 + 1);
                    }
                    String str23 = substring9;
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    a aVar6 = this.m_effectResourceLoader;
                    if (aVar6 != null) {
                        try {
                            bitmap4 = BitmapFactory.decodeStream(aVar6.b(str9, str23), (Rect) null, options2);
                        } catch (IOException e8) {
                            Log.e(str21, "Error loading bitmap for effect(" + str9 + ") : " + str23);
                            e8.printStackTrace();
                        }
                        if (bitmap4 == null && (assetManager = this.m_assetManager) != null) {
                            bitmap4 = BitmapFactory.decodeStream(assetManager.open(str23), (Rect) null, options2);
                        }
                        if (bitmap5 == null) {
                            return new NexImage(bitmap5, bitmap5.getWidth(), bitmap5.getHeight());
                        }
                        Log.e(str21, "Bitmap failed to load for effect(" + str9 + ") : " + str23);
                    }
                    bitmap4 = null;
                    try {
                        bitmap4 = BitmapFactory.decodeStream(assetManager.open(str23), (Rect) null, options2);
                    } catch (IOException e9) {
                        Log.e(str21, "Error loading bitmap (general asset mode) for effect(" + str9 + ") : " + str23);
                        e9.printStackTrace();
                        bitmap4 = null;
                    }
                    if (bitmap5 == null) {
                    }
                } else {
                    if (!str8.startsWith(TAG_Overlay)) {
                        i2 = 0;
                        if (!str8.startsWith("[")) {
                            if (this.m_assetManager != null) {
                                try {
                                    if (str8.compareTo("placeholder1.jpg") != 0) {
                                        if (str8.compareTo("placeholder2.jpg") != 0) {
                                            bitmap3 = BitmapFactory.decodeStream(this.m_assetManager.open(str8));
                                            bitmap2 = bitmap3;
                                            if (bitmap2 != null) {
                                                return new NexImage(bitmap2, bitmap2.getWidth(), bitmap2.getHeight());
                                            }
                                        }
                                    }
                                    bitmap3 = BitmapFactory.decodeStream(this.m_assetManager.open(str8));
                                    bitmap2 = bitmap3;
                                } catch (IOException e10) {
                                    e10.printStackTrace();
                                }
                                if (bitmap2 != null) {
                                }
                            }
                            bitmap2 = null;
                            if (bitmap2 != null) {
                            }
                        } else {
                            i = 0;
                            bitmap = null;
                        }
                    } else if (this.m_overlayPathResolver == null) {
                        Bitmap createBitmap4 = Bitmap.createBitmap(8, 8, Bitmap.Config.ARGB_8888);
                        return new NexImage(createBitmap4, createBitmap4.getWidth(), createBitmap4.getHeight());
                    } else {
                        String a3 = this.m_overlayPathResolver.a(str8.substring(9));
                        if (a3 == null) {
                            Bitmap createBitmap5 = Bitmap.createBitmap(8, 8, Bitmap.Config.ARGB_8888);
                            return new NexImage(createBitmap5, createBitmap5.getWidth(), createBitmap5.getHeight());
                        }
                        BitmapFactory.Options options3 = new BitmapFactory.Options();
                        options3.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(a3, options3);
                        i2 = 0;
                        options3.inJustDecodeBounds = false;
                        options3.inSampleSize = 1;
                        bitmap2 = BitmapFactory.decodeFile(a3, options3);
                        if (bitmap2 != null) {
                            return new NexImage(bitmap2, bitmap2.getWidth(), bitmap2.getHeight());
                        }
                    }
                    i = i2;
                }
            }
            decodeFile = bitmap5;
            i2 = 0;
            i = 0;
        }
        if (bitmap == null || i2 <= 0 || i <= 0) {
            return null;
        }
        return new NexImage(bitmap, i2, i);
    }

    public void setResources(Resources resources) {
        if (resources == null) {
            this.m_assetManager = null;
        } else {
            this.m_assetManager = resources.getAssets();
        }
    }
}
