package com.nexstreaming.kminternal.kinemaster.mediainfo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import com.nexstreaming.app.common.task.Task;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: ThumbnailParser */
class g {

    /* compiled from: ThumbnailParser */
    static class a implements Comparator<b> {
        a() {
        }

        /* renamed from: a */
        public int compare(b bVar, b bVar2) {
            if (bVar.a() < bVar2.a()) {
                return -1;
            }
            return bVar.a() > bVar2.a() ? 1 : 0;
        }
    }

    /* compiled from: ThumbnailParser */
    public static class b {
        private int a;
        private int b;
        private long c;

        b(int i, int i2, long j) {
            this.a = i;
            this.b = i2;
            this.c = j;
        }

        public int a() {
            return this.a;
        }

        public long b() {
            return this.c;
        }
    }

    private static int a(int i) {
        return ((i & 255) << 24) | ((-16777216 & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    static Task.TaskError a(File file, int i, c cVar) {
        if (!file.exists()) {
            return ThumbnailError.RawFileNotFound;
        }
        long length = file.length();
        if (length < 8) {
            return ThumbnailError.RawFileTooSmall;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            long filePointer = randomAccessFile.getFilePointer();
            int readInt = randomAccessFile.readInt();
            int readInt2 = randomAccessFile.readInt();
            int readInt3 = randomAccessFile.readInt();
            int a2 = a(readInt);
            int a3 = a(readInt2);
            int a4 = a(readInt3);
            int i2 = ((a3 * a4) * a2) / 8;
            int min = (int) Math.min((long) i, (length - 8) / ((long) (i2 + 4)));
            if (min < 1) {
                return ThumbnailError.NoThumbailsFound;
            }
            byte[] bArr = new byte[i2];
            ByteBuffer.wrap(bArr);
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i4 < min) {
                int readInt4 = randomAccessFile.readInt();
                int a5 = a(readInt4);
                if (i5 > a5) {
                    Log.d("ThumbnailParser", "thumbnail needSort");
                }
                arrayList.add(new b(a5, readInt4, randomAccessFile.getFilePointer()));
                randomAccessFile.read(bArr);
                i4++;
                i5 = a5;
            }
            randomAccessFile.seek(filePointer);
            Collections.sort(arrayList, new a());
            Log.d("ThumbnailParser", "Sort thumbnail time stamp");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                randomAccessFile.seek(bVar.b());
                randomAccessFile.read(bArr);
                a(bArr, bVar.a(), a3, a4, a2, i3, min, cVar);
                i3++;
            }
            randomAccessFile.close();
            arrayList.clear();
            return null;
        } catch (IOException e) {
            return Task.makeTaskError((Exception) e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ca  */
    static ThumbnailError a(InputStream inputStream, long j, int i, c cVar) throws IOException {
        e eVar;
        d dVar;
        int i2;
        boolean z;
        Bitmap bitmap;
        Canvas canvas;
        Bitmap bitmap2;
        int min;
        Bitmap bitmap3;
        Object obj;
        c cVar2 = cVar;
        if (cVar2 == null) {
            return ThumbnailError.ParameterError;
        }
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int readInt = dataInputStream.readInt();
        int readInt2 = dataInputStream.readInt();
        int readInt3 = dataInputStream.readInt();
        boolean z2 = cVar2 instanceof e;
        if (z2) {
            eVar = (e) cVar2;
            dVar = null;
        } else {
            dVar = (d) cVar2;
            eVar = null;
        }
        if ((readInt2 & -65536) == 0 && (-65536 & readInt3) == 0) {
            i2 = readInt;
            z = false;
        } else {
            int a2 = a(readInt);
            readInt2 = a(readInt2);
            readInt3 = a(readInt3);
            i2 = a2;
            z = true;
        }
        Log.d("ThumbnailParser", "processRawFile: w/h/swap=" + readInt2 + "/" + readInt3 + "/" + z + " format=" + i2);
        if (i2 == 32) {
            if (!z2) {
                bitmap = Bitmap.createBitmap(readInt2, readInt3, Bitmap.Config.ARGB_8888);
                if (z2) {
                    bitmap2 = Bitmap.createBitmap(readInt2, readInt3, bitmap.getConfig());
                    canvas = new Canvas(bitmap2);
                    canvas.scale(1.0f, -1.0f);
                } else {
                    bitmap2 = null;
                    canvas = null;
                }
                int i3 = ((readInt2 * readInt3) * i2) / 8;
                String str = "ThumbnailParser";
                Bitmap bitmap4 = bitmap2;
                min = (int) Math.min((long) i, (j - 8) / ((long) (i3 + 4)));
                if (min >= 1) {
                    return ThumbnailError.NoThumbailsFound;
                }
                byte[] bArr = new byte[i3];
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Log.d(str, "processRawFile : thumbCount=" + min);
                int i4 = 0;
                while (i4 < min) {
                    StringBuilder sb = new StringBuilder();
                    Bitmap bitmap5 = bitmap4;
                    sb.append("processRawFile : i=");
                    sb.append(i4);
                    Log.d(str, sb.toString());
                    int readInt4 = dataInputStream.readInt();
                    if (z) {
                        readInt4 = a(readInt4);
                    }
                    StringBuilder sb2 = new StringBuilder();
                    boolean z3 = z;
                    sb2.append("processRawFile : time=");
                    sb2.append(readInt4);
                    Log.d(str, sb2.toString());
                    DataInputStream dataInputStream2 = dataInputStream;
                    if (dataInputStream.read(bArr) < i3 - 1) {
                        if (z2) {
                            obj = null;
                            eVar.a((byte[]) null, i4, min, readInt4);
                        } else {
                            obj = null;
                            dVar.a((Bitmap) null, i4, min, readInt4);
                        }
                        Object obj2 = obj;
                        bitmap3 = bitmap5;
                    } else if (z2) {
                        eVar.a(bArr, i4, min, readInt4);
                        wrap.rewind();
                        bitmap3 = bitmap5;
                    } else {
                        bitmap.copyPixelsFromBuffer(wrap);
                        wrap.rewind();
                        canvas.drawBitmap(bitmap, 0.0f, (float) (-readInt3), (Paint) null);
                        bitmap3 = bitmap5;
                        dVar.a(bitmap3, i4, min, readInt4);
                    }
                    i4++;
                    bitmap4 = bitmap3;
                    z = z3;
                    dataInputStream = dataInputStream2;
                }
                return null;
            }
        } else if (i2 == 16) {
            if (!z2) {
                bitmap = Bitmap.createBitmap(readInt2, readInt3, Bitmap.Config.RGB_565);
                if (z2) {
                }
                int i32 = ((readInt2 * readInt3) * i2) / 8;
                String str2 = "ThumbnailParser";
                Bitmap bitmap42 = bitmap2;
                min = (int) Math.min((long) i, (j - 8) / ((long) (i32 + 4)));
                if (min >= 1) {
                }
            }
        } else if (i2 != 8) {
            return ThumbnailError.UnknownFormat;
        } else {
            if (!z2) {
                bitmap = Bitmap.createBitmap(readInt2, readInt3, Bitmap.Config.ARGB_8888);
                if (z2) {
                }
                int i322 = ((readInt2 * readInt3) * i2) / 8;
                String str22 = "ThumbnailParser";
                Bitmap bitmap422 = bitmap2;
                min = (int) Math.min((long) i, (j - 8) / ((long) (i322 + 4)));
                if (min >= 1) {
                }
            }
        }
        bitmap = null;
        if (z2) {
        }
        int i3222 = ((readInt2 * readInt3) * i2) / 8;
        String str222 = "ThumbnailParser";
        Bitmap bitmap4222 = bitmap2;
        min = (int) Math.min((long) i, (j - 8) / ((long) (i3222 + 4)));
        if (min >= 1) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009d  */
    static ThumbnailError a(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, c cVar) throws IOException {
        e eVar;
        d dVar;
        Bitmap bitmap;
        Bitmap bitmap2;
        Canvas canvas;
        if (cVar == null) {
            return ThumbnailError.ParameterError;
        }
        boolean z = cVar instanceof e;
        if (z) {
            eVar = (e) cVar;
            dVar = null;
        } else {
            dVar = (d) cVar;
            eVar = null;
        }
        Log.d("ThumbnailParser", "processRawFile: w/h/time=" + i2 + "/" + i3 + "/" + i + ", format=" + i4);
        if (i4 == 32) {
            if (!z) {
                bitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                if (z) {
                    bitmap2 = Bitmap.createBitmap(i2, i3, bitmap.getConfig());
                    canvas = new Canvas(bitmap2);
                    canvas.scale(1.0f, -1.0f);
                } else {
                    bitmap2 = null;
                    canvas = null;
                }
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Log.d("ThumbnailParser", "processRawFile : thumbCount=" + i6);
                if (!z) {
                    eVar.a(bArr, i5, i6, i);
                } else {
                    bitmap.copyPixelsFromBuffer(wrap);
                    canvas.drawBitmap(bitmap, 0.0f, (float) (-i3), (Paint) null);
                    dVar.a(bitmap2, i5, i6, i);
                }
                return null;
            }
        } else if (i4 == 16) {
            if (!z) {
                bitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.RGB_565);
                if (z) {
                }
                ByteBuffer wrap2 = ByteBuffer.wrap(bArr);
                Log.d("ThumbnailParser", "processRawFile : thumbCount=" + i6);
                if (!z) {
                }
                return null;
            }
        } else if (i4 != 8) {
            return ThumbnailError.UnknownFormat;
        } else {
            if (!z) {
                bitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
                if (z) {
                }
                ByteBuffer wrap22 = ByteBuffer.wrap(bArr);
                Log.d("ThumbnailParser", "processRawFile : thumbCount=" + i6);
                if (!z) {
                }
                return null;
            }
        }
        bitmap = null;
        if (z) {
        }
        ByteBuffer wrap222 = ByteBuffer.wrap(bArr);
        Log.d("ThumbnailParser", "processRawFile : thumbCount=" + i6);
        if (!z) {
        }
        return null;
    }
}
