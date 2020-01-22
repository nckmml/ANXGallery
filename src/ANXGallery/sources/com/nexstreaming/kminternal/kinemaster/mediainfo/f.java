package com.nexstreaming.kminternal.kinemaster.mediainfo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import com.nexstreaming.app.common.task.Task;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: ThumbnailConversionTask */
abstract class f extends AsyncTask<Integer, Integer, Task.TaskError> {
    private File a;
    private File b;
    private File c;
    private File d;
    private long e;
    /* access modifiers changed from: private */
    public Bitmap f;
    /* access modifiers changed from: private */
    public Bitmap g;
    /* access modifiers changed from: private */
    public Bitmap h;
    /* access modifiers changed from: private */
    public int[] i;

    public f(File file, File file2, File file3, File file4) {
        this.a = file2;
        this.d = file;
        this.b = file3;
        this.c = file4;
    }

    private Task.TaskError a(InputStream inputStream) throws IOException {
        return g.a(inputStream, this.e, 50, new d() {
            int a;
            int b;
            Bitmap c;
            Canvas d;
            Rect e;
            Paint f;

            public void a(Bitmap bitmap, int i, int i2, int i3) {
                if (i == 0) {
                    this.a = 90;
                    this.b = i2 * 160;
                    this.c = Bitmap.createBitmap(this.b, this.a, Bitmap.Config.RGB_565);
                    Bitmap unused = f.this.f = this.c;
                    this.d = new Canvas(this.c);
                    this.e = new Rect(0, 0, 160, 90);
                    this.f = new Paint();
                    this.f.setFilterBitmap(true);
                    int[] unused2 = f.this.i = new int[i2];
                    Log.d("KMMediaInfo_ThumbConv", "processRawFile : totalCount=" + i2);
                }
                f.this.i[i] = i3;
                if (bitmap == null) {
                    this.e.offset(160, 0);
                    return;
                }
                if (i == 0) {
                    Log.d("KMMediaInfo_ThumbConv", "Make large thumnail at i==0");
                    Bitmap createBitmap = Bitmap.createBitmap(640, 360, Bitmap.Config.RGB_565);
                    new Canvas(createBitmap).drawBitmap(bitmap, (Rect) null, new Rect(0, 0, 640, 360), this.f);
                    Bitmap unused3 = f.this.g = createBitmap;
                } else if (i == i2 - 1) {
                    Log.d("KMMediaInfo_ThumbConv", "Make large end thumnail at i==" + i);
                    Bitmap createBitmap2 = Bitmap.createBitmap(640, 360, Bitmap.Config.RGB_565);
                    new Canvas(createBitmap2).drawBitmap(bitmap, (Rect) null, new Rect(0, 0, 640, 360), this.f);
                    Bitmap unused4 = f.this.h = createBitmap2;
                }
                this.d.save();
                this.d.scale(-1.0f, -1.0f, 80.0f, 45.0f);
                this.d.drawBitmap(bitmap, (Rect) null, this.e, this.f);
                this.d.restore();
                this.d.translate(160.0f, 0.0f);
            }
        });
    }

    private void a(int[] iArr, Bitmap bitmap, File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("writeBitmapToFile(");
        sb.append(file);
        sb.append(") : ");
        sb.append(iArr == null ? "no index" : "width index");
        Log.d("KMMediaInfo_ThumbConv", sb.toString());
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        if (iArr != null) {
            try {
                dataOutputStream.writeInt(160);
                dataOutputStream.writeInt(90);
                dataOutputStream.writeInt(iArr.length);
                for (int writeInt : iArr) {
                    dataOutputStream.writeInt(writeInt);
                }
            } catch (Throwable th) {
                dataOutputStream.close();
                file.setReadable(true);
                throw th;
            }
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, dataOutputStream);
        dataOutputStream.close();
        file.setReadable(true);
    }

    private Task.TaskError b() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.d));
        try {
            return a((InputStream) bufferedInputStream);
        } finally {
            bufferedInputStream.close();
        }
    }

    private void c() throws IOException {
        a((int[]) null, this.g, this.b);
        Bitmap bitmap = this.h;
        if (bitmap == null) {
            bitmap = this.g;
        }
        a((int[]) null, bitmap, this.c);
        a(this.i, this.f, this.a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Task.TaskError doInBackground(Integer... numArr) {
        if (!this.d.exists()) {
            Log.d("KMMediaInfo_ThumbConv", "doInBackground : raw thumbnail file not found");
            return ThumbnailError.RawFileNotFound;
        }
        this.e = this.d.length();
        Log.d("KMMediaInfo_ThumbConv", "doInBackground : mThumbFileSize=" + this.e);
        if (this.e < 8) {
            Log.d("KMMediaInfo_ThumbConv", "doInBackground : raw thumbnail file too small");
            return ThumbnailError.RawFileTooSmall;
        }
        try {
            Task.TaskError b2 = b();
            if (b2 != null) {
                Log.d("KMMediaInfo_ThumbConv", "doInBackground : raw thumbnail file parse error");
                return b2;
            }
            c();
            Log.d("KMMediaInfo_ThumbConv", "doInBackground : out");
            return null;
        } catch (IOException e2) {
            Log.d("KMMediaInfo_ThumbConv", "doInBackground : EXCEPTION", e2);
            return Task.makeTaskError((Exception) e2);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public abstract void a(Task.TaskError taskError);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void onPostExecute(Task.TaskError taskError) {
        if (taskError == null) {
            a();
        } else {
            a(taskError);
        }
    }
}
