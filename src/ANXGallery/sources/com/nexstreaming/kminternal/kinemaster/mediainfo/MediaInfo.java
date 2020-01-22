package com.nexstreaming.kminternal.kinemaster.mediainfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;
import com.google.gson_nex.Gson;
import com.google.gson_nex.JsonIOException;
import com.google.gson_nex.JsonSyntaxException;
import com.nexstreaming.app.common.task.ResultTask;
import com.nexstreaming.app.common.task.Task;
import com.nexstreaming.app.common.util.FileType;
import com.nexstreaming.kminternal.kinemaster.config.EditorGlobal;
import com.nexstreaming.kminternal.kinemaster.config.ExclusionList;
import com.nexstreaming.kminternal.kinemaster.config.NexEditorDeviceProfile;
import com.nexstreaming.kminternal.nexvideoeditor.NexClipInfo;
import com.nexstreaming.kminternal.nexvideoeditor.NexEditor;
import com.nexstreaming.nexeditorsdk.nexEngine;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MediaInfo {
    /* access modifiers changed from: private */
    public static Deque<c<h, f>> B = new ArrayDeque();
    /* access modifiers changed from: private */
    public static int C = 0;
    /* access modifiers changed from: private */
    public static Deque<c<b, Void>> D = new ArrayDeque();
    /* access modifiers changed from: private */
    public static Task E = null;
    /* access modifiers changed from: private */
    public static SparseArray<c<?, ?>> F = new SparseArray<>();
    private static boolean G = false;
    private static boolean H = false;
    private static Object I = new Object();
    private static NexEditor.k J = new NexEditor.k() {
        public void a(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6, int i7, int i8) {
            int i9 = i8;
            c cVar = (c) MediaInfo.F.get(i9);
            Log.d("MediaInfo", "sThumbDoneListener onGetThumbDoneListener : tag=" + i9);
            if (cVar != null) {
                Log.d("MediaInfo", "sThumbDoneListener NOTIFY TASK!");
                cVar.a(i, i2, i3, i4, i5, bArr, i6, i7);
            }
        }
    };
    private static NexEditor.j K = new NexEditor.j() {
        public void a(NexEditor.ErrorCode errorCode, int i) {
            c cVar = (c) MediaInfo.F.get(i);
            Log.d("MediaInfo", "sClipInfoDoneListener onGetClipInfoDone : tag=" + i + " resultCode=" + errorCode);
            if (cVar != null) {
                Log.d("MediaInfo", "sClipInfoDoneListener NOTIFY TASK!");
                cVar.a(errorCode);
            }
        }
    };
    private static NexEditor.h L = new NexEditor.h() {
        public void a() {
            Log.d("MediaInfo", "sOnEditorDestroyedListener onEditorDestroyed");
            for (int i = 0; i < MediaInfo.F.size(); i++) {
                ((c) MediaInfo.F.valueAt(i)).sendFailure(NexEditor.ErrorCode.EDITOR_INSTANCE_DESTROYED);
            }
            SparseArray unused = MediaInfo.F = new SparseArray();
            Deque unused2 = MediaInfo.B = new ArrayDeque();
            int unused3 = MediaInfo.C = 0;
        }
    };
    private static NexEditor.m M = new NexEditor.m() {
        public void a() {
            Log.d("MediaInfo", "sIdleListener onIdle!");
            MediaInfo.R();
            MediaInfo.S();
        }
    };
    private static Map<String, WeakReference<MediaInfo>> a;
    private static ExclusionList b;
    /* access modifiers changed from: private */
    public static Executor v = Executors.newSingleThreadExecutor();
    private static Executor w = Executors.newSingleThreadExecutor();
    private static File y;
    private static File z;
    private boolean A = true;
    /* access modifiers changed from: private */
    public String c;
    private int d = -1;
    private File e;
    /* access modifiers changed from: private */
    public File f;
    /* access modifiers changed from: private */
    public File g;
    private File h;
    private File i;
    /* access modifiers changed from: private */
    public File j;
    /* access modifiers changed from: private */
    public File k;
    private File l;
    private String m;
    private NexEditor.ErrorCode n = NexEditor.ErrorCode.NONE;
    /* access modifiers changed from: private */
    public c<h, f> o = null;
    /* access modifiers changed from: private */
    public c<b, Void> p = null;
    private ResultTask<Bitmap> q = null;
    private ResultTask<Bitmap> r = null;
    /* access modifiers changed from: private */
    public ResultTask<int[]> s = null;
    private AsyncTask<String, Integer, int[]> t = null;
    private b u;
    private boolean x;

    public enum MediaInfoError implements Task.TaskError {
        PCMLevelsNotAvailable,
        SeekPointsNotAvailable,
        ThumbnailsNotAvailable,
        LargeStartThumbnailNotAvailable,
        LargeEndThumbnailNotAvailable;

        public Exception getException() {
            return null;
        }

        public String getLocalizedMessage(Context context) {
            return name();
        }

        public String getMessage() {
            return name();
        }
    }

    private static class a {
        final int a;
        final int b;
        final int[] c;
        final Bitmap d;

        a(int i, int i2, int[] iArr, Bitmap bitmap) {
            this.a = i;
            this.b = i2;
            this.c = iArr;
            this.d = bitmap;
        }
    }

    private static class b {
        public int a;
        public String b;
        public long c;
        public boolean d;
        public boolean e;
        public boolean f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;
        public int w;
        public int x;
        public int y;
        public int z;

        private b() {
        }
    }

    private static class c<RESULT_TYPE, PARAM_TYPE> extends ResultTask<RESULT_TYPE> {
        int a = 3;
        boolean b = false;
        /* access modifiers changed from: private */
        public final MediaInfo c;
        private final d<RESULT_TYPE, PARAM_TYPE> d;
        private final e e;
        private final PARAM_TYPE f;

        public c(MediaInfo mediaInfo, PARAM_TYPE param_type, d<RESULT_TYPE, PARAM_TYPE> dVar) {
            this.d = dVar;
            this.e = null;
            this.c = mediaInfo;
            this.f = param_type;
            Log.d("MediaInfo", "MediaInfoTask : add to active tasks; tag=" + getTaskId());
            MediaInfo.F.put(getTaskId(), this);
        }

        public c(MediaInfo mediaInfo, PARAM_TYPE param_type, d<RESULT_TYPE, PARAM_TYPE> dVar, e eVar) {
            this.d = dVar;
            this.e = eVar;
            this.c = mediaInfo;
            this.f = param_type;
            Log.d("MediaInfo", "MediaInfoTask : add to active tasks; thumb tag=" + getTaskId());
            MediaInfo.F.put(getTaskId(), this);
        }

        /* access modifiers changed from: private */
        public PARAM_TYPE a() {
            return this.f;
        }

        /* access modifiers changed from: private */
        public void a(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6, int i7) {
            e eVar;
            if (MediaInfo.F.get(getTaskId()) == this && (eVar = this.e) != null) {
                eVar.a(i, i2, i3, i4, i5, bArr, i6, i7);
            }
        }

        /* access modifiers changed from: private */
        public void a(NexEditor.ErrorCode errorCode) {
            d<RESULT_TYPE, PARAM_TYPE> dVar;
            if (MediaInfo.F.get(getTaskId()) == this && (dVar = this.d) != null) {
                dVar.a(this, errorCode);
            }
        }

        public void sendFailure(Task.TaskError taskError) {
            Log.d("MediaInfo", "MediaInfoTask::sendFailure : remove from active tasks; tag=" + getTaskId());
            if (MediaInfo.F.get(getTaskId()) != this) {
                Log.d("MediaInfo", "MediaInfoTask::sendFailure : NOT THIS; tag=" + getTaskId());
                return;
            }
            super.sendFailure(taskError);
            MediaInfo.F.remove(getTaskId());
            Log.d("MediaInfo", "MediaInfoTask::sendFailure : tag=" + getTaskId() + " sActiveTasks.size()=" + MediaInfo.F.size());
            if (MediaInfo.F.size() == 0 && MediaInfo.E != null) {
                Log.d("MediaInfo", "MediaInfoTask::sendFailure : tag=" + getTaskId() + " mWaitNotBusyTask.signalEvent(COMPLETE)");
                MediaInfo.E.signalEvent(Task.Event.COMPLETE);
            }
        }

        public void sendResult(RESULT_TYPE result_type) {
            Log.d("MediaInfo", "MediaInfoTask::sendResult : remove from active tasks; tag=" + getTaskId());
            if (MediaInfo.F.get(getTaskId()) != this) {
                Log.d("MediaInfo", "MediaInfoTask::sendResult : NOT THIS; tag=" + getTaskId());
                return;
            }
            MediaInfo.F.remove(getTaskId());
            Log.d("MediaInfo", "MediaInfoTask::sendResult : tag=" + getTaskId() + " sActiveTasks.size()=" + MediaInfo.F.size());
            if (MediaInfo.F.size() == 0 && MediaInfo.E != null) {
                Log.d("MediaInfo", "MediaInfoTask::sendResult : tag=" + getTaskId() + " mWaitNotBusyTask.signalEvent(COMPLETE)");
                MediaInfo.E.signalEvent(Task.Event.COMPLETE);
            }
            super.sendResult(result_type);
        }
    }

    private interface d<RESULT_TYPE, PARAM_TYPE> {
        void a(c<RESULT_TYPE, PARAM_TYPE> cVar, NexEditor.ErrorCode errorCode);
    }

    private interface e {
        void a(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6, int i7);
    }

    private static class f {
        public final String a;
        public final File b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final int[] i;

        protected f(String str, File file, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.a = str;
            this.b = file;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
            this.h = i7;
            this.i = null;
        }

        protected f(String str, File file, int i2, int i3, int[] iArr, int i4) {
            this.a = str;
            this.b = file;
            this.c = i2;
            this.d = i3;
            this.e = 0;
            this.f = 0;
            this.g = iArr.length;
            this.h = i4;
            this.i = iArr;
        }
    }

    private MediaInfo(String str, int i2, boolean z2) {
        Gson gson;
        BufferedWriter bufferedWriter;
        b bVar;
        BufferedReader bufferedReader;
        FileType fromFile;
        this.c = str;
        this.d = i2;
        if (i2 != 0 || (fromFile = FileType.fromFile(str)) == null || !fromFile.isImage()) {
            File file = new File(y, ".km_mediainfo");
            file.mkdirs();
            String format = String.format(Locale.US, "none_%08X", new Object[]{Integer.valueOf(str.hashCode())});
            if (i2 == 0) {
                format = b(new File(str));
            } else if (i2 == 1) {
                format = c(str);
            }
            Log.d("MediaInfo", "mediaInfoDir=" + file);
            this.e = new File(file, format + "_info.dat");
            this.f = new File(file, format + "_seek.dat");
            this.g = new File(file, format + "_vthumb.dat");
            this.h = new File(file, format + "_vthumb_large.dat");
            this.i = new File(file, format + "_vthumb_large_end.dat");
            this.j = new File(file, format + "_vthumb_raw.dat");
            this.k = new File(file, format + "_pcm.dat");
            this.l = file;
            this.m = format;
            this.A = z2;
            if (!z2 || !this.e.exists()) {
                gson = null;
            } else {
                Log.d("MediaInfo", "getInfo(" + str + ") info file exists -> attemptng to read");
                gson = new Gson();
                try {
                    bufferedReader = new BufferedReader(new FileReader(this.e));
                    bVar = (b) gson.fromJson((Reader) bufferedReader, b.class);
                    try {
                        bufferedReader.close();
                    } catch (JsonIOException | JsonSyntaxException | IOException unused) {
                    }
                } catch (JsonIOException | JsonSyntaxException | IOException unused2) {
                    bVar = null;
                } catch (Throwable th) {
                    bufferedReader.close();
                    throw th;
                }
                if (bVar != null && bVar.a == 9) {
                    Log.d("MediaInfo", "getInfo(" + str + ") info from disk cache");
                    this.u = bVar;
                    return;
                }
            }
            ExclusionList exclusionList = b;
            if (exclusionList == null || !exclusionList.isExcluded(format)) {
                NexClipInfo nexClipInfo = new NexClipInfo();
                NexEditor Q = Q();
                if (Q == null) {
                    this.n = NexEditor.ErrorCode.NO_INSTANCE_AVAILABLE;
                    Log.d("MediaInfo", "getInfo(" + str + ") failure:" + this.n);
                    return;
                }
                b.add(format);
                Log.d("MediaInfo", "getInfo(" + str + ") call editor for info");
                this.n = Q.a(str, nexClipInfo, false, 0);
                Log.d("MediaInfo", "getInfo(" + str + ") returned from editor");
                b.remove(format);
                this.u = new b();
                if (this.n != NexEditor.ErrorCode.NONE) {
                    Log.d("MediaInfo", "getInfo(" + str + ") failure:" + this.n);
                    return;
                }
                b bVar2 = this.u;
                bVar2.a = 9;
                bVar2.b = str;
                bVar2.d = nexClipInfo.mExistAudio != 0;
                this.u.e = nexClipInfo.mExistVideo != 0;
                b bVar3 = this.u;
                bVar3.f = false;
                bVar3.k = nexClipInfo.mAudioDuration;
                this.u.l = nexClipInfo.mVideoDuration;
                this.u.c = N();
                this.u.g = nexClipInfo.mVideoWidth;
                this.u.h = nexClipInfo.mVideoHeight;
                this.u.i = nexClipInfo.mDisplayVideoWidth;
                this.u.j = nexClipInfo.mDisplayVideoHeight;
                this.u.m = nexClipInfo.mSeekPointCount;
                this.u.n = nexClipInfo.mFPS;
                this.u.o = nexClipInfo.mVideoH264Profile;
                this.u.p = nexClipInfo.mVideoH264Level;
                this.u.q = nexClipInfo.mVideoH264Interlaced;
                this.u.v = nexClipInfo.mVideoOrientation;
                this.u.r = nexClipInfo.mVideoBitRate;
                this.u.s = nexClipInfo.mAudioBitRate;
                this.u.t = nexClipInfo.mAudioSampleRate;
                this.u.u = nexClipInfo.mAudioChannels;
                this.u.w = nexClipInfo.mVideoRenderType;
                this.u.x = nexClipInfo.mVideoCodecType;
                this.u.y = nexClipInfo.mAudioCodecType;
                this.u.z = nexClipInfo.mVideoHDRType;
                if (z2) {
                    gson = gson == null ? new Gson() : gson;
                    Log.d("MediaInfo", "getInfo(" + str + ") writing:" + this.e);
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(this.e));
                        gson.toJson((Object) this.u, (Appendable) bufferedWriter);
                        bufferedWriter.close();
                        this.e.setReadable(true);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    } catch (Throwable th2) {
                        bufferedWriter.close();
                        this.e.setReadable(true);
                        throw th2;
                    }
                }
                Log.d("MediaInfo", "getInfo(" + str + ") out");
                return;
            }
            Log.d("MediaInfo", "getInfo(" + str + ") skip due to exclusion");
            this.x = true;
        } else if (fromFile.isSupportedFormat()) {
            M();
        } else {
            this.x = true;
        }
    }

    private void M() {
        if (this.d != 0) {
            this.u = new b();
            this.u.a = 9;
            return;
        }
        File file = new File(this.c);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        this.u = new b();
        b bVar = this.u;
        bVar.a = 9;
        bVar.b = file.getAbsolutePath();
        b bVar2 = this.u;
        bVar2.d = false;
        bVar2.e = false;
        bVar2.f = true;
        bVar2.k = 0;
        bVar2.l = 0;
        bVar2.c = file.length();
        this.u.g = options.outWidth;
        this.u.h = options.outHeight;
        b bVar3 = this.u;
        bVar3.m = 0;
        bVar3.n = 0;
        bVar3.o = 0;
        bVar3.p = 0;
        bVar3.q = 0;
        bVar3.w = 0;
        bVar3.x = 0;
        bVar3.y = 0;
        bVar3.z = 0;
        this.x = false;
        this.n = NexEditor.ErrorCode.NONE;
    }

    private long N() {
        int i2 = this.d;
        if (i2 == 0) {
            return new File(this.c).length();
        }
        if (i2 != 1) {
            return 0;
        }
        String str = this.c;
        return (long) Integer.parseInt(str.substring(str.lastIndexOf(58) + 1));
    }

    /* access modifiers changed from: private */
    public Task O() {
        Task task = new Task();
        new File(y, ".km_mediainfo").mkdirs();
        final Task task2 = task;
        new f(this.j, this.g, this.h, this.i) {
            /* access modifiers changed from: protected */
            public void a() {
                MediaInfo.this.j.delete();
                task2.signalEvent(Task.Event.SUCCESS, Task.Event.COMPLETE);
            }

            /* access modifiers changed from: protected */
            public void a(Task.TaskError taskError) {
                MediaInfo.this.j.delete();
                task2.sendFailure(taskError);
            }
        }.executeOnExecutor(v, new Integer[]{0});
        return task;
    }

    /* access modifiers changed from: private */
    public ResultTask<b> P() {
        final ResultTask<b> resultTask = new ResultTask<>();
        new AsyncTask<MediaInfo, Integer, b>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public b doInBackground(MediaInfo... mediaInfoArr) {
                FileInputStream fileInputStream;
                MediaInfo mediaInfo = mediaInfoArr[0];
                if (!mediaInfo.k.exists()) {
                    return null;
                }
                int min = (int) Math.min(mediaInfo.k.length(), 204800);
                try {
                    byte[] bArr = new byte[min];
                    fileInputStream = new FileInputStream(MediaInfo.this.k);
                    int read = fileInputStream.read(bArr);
                    fileInputStream.close();
                    if (read >= min) {
                        return new b(bArr);
                    }
                    return null;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(b bVar) {
                resultTask.setResult(bVar);
                resultTask.signalEvent(Task.Event.RESULT_AVAILABLE, Task.Event.SUCCESS, Task.Event.COMPLETE);
            }
        }.executeOnExecutor(w, new MediaInfo[]{this});
        return resultTask;
    }

    /* access modifiers changed from: private */
    public static NexEditor Q() {
        if (EditorGlobal.a() == null) {
            Log.d("MediaInfo", "getEditor : NULL EDITOR");
            return null;
        }
        Log.d("MediaInfo", "getEditor : setting sClipInfoDoneListener=" + K);
        EditorGlobal.a().a(K);
        EditorGlobal.a().a(M);
        EditorGlobal.a().a(L);
        EditorGlobal.a().a(J);
        return EditorGlobal.a();
    }

    /* access modifiers changed from: private */
    public static void R() {
        NexEditor Q;
        if (!D.isEmpty() && (Q = Q()) != null) {
            c remove = D.remove();
            Q.a(remove.c.c, remove.c.k, remove.getTaskId());
        }
    }

    /* access modifiers changed from: private */
    public static void S() {
        synchronized (I) {
            Log.d("MediaInfo", "startPendingThumbnailTask taskcount=" + B.size());
            if (!B.isEmpty()) {
                NexEditor Q = Q();
                if (Q != null) {
                    c cVar = null;
                    boolean z2 = true;
                    while (true) {
                        if (B.isEmpty()) {
                            break;
                        }
                        cVar = B.remove();
                        if (!cVar.b) {
                            z2 = false;
                            break;
                        }
                        Log.d("MediaInfo", "startPendingThumbnailTask cancel thumbnail TaskId=" + cVar.getTaskId());
                        cVar.sendFailure(NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL);
                    }
                    if (z2) {
                        Log.d("MediaInfo", "startPendingThumbnailTask all canceled.");
                        return;
                    }
                    f fVar = (f) cVar.a();
                    try {
                        if (fVar.i != null) {
                            Log.d("MediaInfo", "startPendingThumbnailTask use TimeTable sThumbnailsRunTaskId=" + cVar.getTaskId());
                            Q.a(fVar.a, fVar.b, fVar.c, fVar.d, fVar.i.length, fVar.i, fVar.h, cVar.getTaskId());
                        } else {
                            Log.d("MediaInfo", "startPendingThumbnailTask range time sThumbnailsRunTaskId=" + cVar.getTaskId());
                            Q.a(fVar.a, fVar.b, fVar.c, fVar.d, fVar.e, fVar.f, fVar.g, fVar.h, cVar.getTaskId());
                        }
                    } catch (IOException e2) {
                        cVar.sendFailure(Task.makeTaskError((Exception) e2));
                    }
                }
            } else {
                Log.d("MediaInfo", "startPendingThumbnailTask all run end.");
            }
        }
    }

    public static MediaInfo a(String str) {
        if (str == null) {
            return null;
        }
        return a(str, true);
    }

    public static MediaInfo a(String str, boolean z2) {
        WeakReference weakReference;
        MediaInfo mediaInfo;
        if (str == null) {
            return null;
        }
        Log.d("MediaInfo", "getInfo(" + str + ")");
        if (a == null) {
            Log.d("MediaInfo", "getInfo : init cache");
            a = new HashMap();
        }
        if (b == null) {
            Log.d("MediaInfo", "getInfo : init exclusion list");
            b = ExclusionList.exclusionListBackedBy(new File(new File(y, ".km_mediainfo"), "mediainfo_exclude.dat"));
        }
        int b2 = b(str);
        String absolutePath = b2 == 0 ? new File(str).getAbsolutePath() : str;
        if (!z2 || (weakReference = a.get(absolutePath)) == null || (mediaInfo = (MediaInfo) weakReference.get()) == null) {
            if (b2 == 1) {
                z2 = false;
            }
            MediaInfo mediaInfo2 = new MediaInfo(str, b2, z2);
            if (mediaInfo2.n == NexEditor.ErrorCode.NO_INSTANCE_AVAILABLE) {
                return mediaInfo2;
            }
            Log.d("MediaInfo", "getInfo(" + str + ") adding to cache duration=" + mediaInfo2.p());
            a.put(absolutePath, new WeakReference(mediaInfo2));
            return mediaInfo2;
        }
        Log.d("MediaInfo", "getInfo(" + str + ") using cache. duration=" + mediaInfo.p());
        return mediaInfo;
    }

    public static void a(Context context) {
        y = context.getApplicationContext().getCacheDir();
    }

    public static void a(File file) {
        z = file;
    }

    public static boolean a() {
        NexEditor Q = Q();
        if (Q == null) {
            return false;
        }
        Log.d("MediaInfo", "cancelAllGetThumbnails : Pending size=" + B.size());
        Log.d("MediaInfo", "cancelAllGetThumbnails : Active size=" + F.size());
        for (int i2 = 0; i2 < F.size(); i2++) {
            c valueAt = F.valueAt(i2);
            valueAt.a = 0;
            valueAt.b = true;
        }
        for (c next : B) {
            Log.d("MediaInfo", "cancelAllGetThumbnails : id=" + next.getTaskId());
            next.a = 0;
            next.b = true;
        }
        Q.l(0);
        return true;
    }

    private static int b(String str) {
        if (str != null) {
            return str.startsWith("nexasset://") ? 1 : 0;
        }
        return -1;
    }

    private static String b(File file) {
        String name = file.getName();
        int hashCode = file.getAbsolutePath().hashCode();
        long lastModified = file.lastModified();
        long length = file.length();
        if (name.length() > 32) {
            name = name.substring(0, 32);
        }
        int i2 = (int) ((((lastModified * 212501089) + (length * 194851861)) + ((long) hashCode)) % 268435455);
        return name + "_" + String.format(Locale.US, "%08X", new Object[]{Integer.valueOf(i2)});
    }

    /* access modifiers changed from: private */
    public ResultTask<a> c(File file) {
        final ResultTask<a> resultTask = new ResultTask<>();
        new AsyncTask<File, Integer, a>() {
            private a a(File file) throws IOException {
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                int readInt = dataInputStream.readInt();
                int readInt2 = dataInputStream.readInt();
                int readInt3 = dataInputStream.readInt();
                int[] iArr = new int[readInt3];
                for (int i = 0; i < readInt3; i++) {
                    iArr[i] = dataInputStream.readInt();
                }
                return new a(readInt, readInt2, iArr, BitmapFactory.decodeStream(dataInputStream));
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public a doInBackground(File... fileArr) {
                try {
                    return a(fileArr[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(a aVar) {
                resultTask.setResult(aVar);
                resultTask.signalEvent(Task.Event.RESULT_AVAILABLE, Task.Event.SUCCESS, Task.Event.COMPLETE);
            }
        }.executeOnExecutor(w, new File[]{file});
        return resultTask;
    }

    private static String c(String str) {
        String substring = str.substring(11);
        substring.replace(':', '_');
        return "nexasset_" + substring;
    }

    public static boolean k() {
        return F.size() != 0;
    }

    public int A() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.w;
    }

    public int B() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.x;
    }

    public int C() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.y;
    }

    public int D() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.z;
    }

    public Task a(int i2, int i3, int i4, int i5, int i6, int i7, int[] iArr, c cVar) {
        final int i8 = i6;
        final c cVar2 = cVar;
        boolean z2 = true;
        C++;
        File file = this.l;
        File file2 = z;
        if (file2 != null) {
            Log.d("MediaInfo", "getDetailThumbnails::temp cache dir=" + file2.getAbsolutePath());
            file = file2;
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        final File file3 = new File(file, this.m + "_detail_" + i2 + "_" + i3 + "_" + i4 + "_" + i5 + "_" + i8 + "_" + C);
        c cVar3 = new c(this, iArr != null ? new f(this.c, file3, i2, i3, iArr, i7) : new f(this.c, file3, i2, i3, i4, i5, i6, i7), new d<h, f>() {
            public void a(final c<h, f> cVar, NexEditor.ErrorCode errorCode) {
                if (errorCode == NexEditor.ErrorCode.NONE) {
                    MediaInfo.S();
                    if ((((f) cVar.a()).h & nexEngine.ExportHEVCMainTierLevel52) == 262144) {
                        Log.d("MediaInfo", "getDetailThumbnails::onEditorAsyncDone() no cache mode");
                        cVar.signalEvent(Task.Event.SUCCESS, Task.Event.COMPLETE);
                        cVar.sendResult(a.a());
                        return;
                    }
                    Log.d("MediaInfo", "getDetailThumbnails::onEditorAsyncDone(" + MediaInfo.this.c + ") -> " + file3);
                    new AsyncTask<Void, Void, Void>() {
                        Task.TaskError a = null;

                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Void doInBackground(Void... voidArr) {
                            if (i8 == 0) {
                                this.a = g.a(file3, 50, cVar2);
                            } else {
                                this.a = g.a(file3, i8, cVar2);
                            }
                            file3.delete();
                            return null;
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public void onPostExecute(Void voidR) {
                            Task.TaskError taskError = this.a;
                            if (taskError != null) {
                                cVar.sendFailure(taskError);
                                return;
                            }
                            cVar.signalEvent(Task.Event.SUCCESS, Task.Event.COMPLETE);
                            cVar.sendResult(a.a());
                        }
                    }.executeOnExecutor(MediaInfo.v, new Void[0]);
                } else if (cVar.b) {
                    Log.d("MediaInfo", "getDetailThumbnails::onEditorAsyncDone : User Cancel ID=" + cVar.getTaskId());
                    cVar.a = 0;
                    file3.delete();
                    cVar.sendFailure(NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL);
                    MediaInfo.S();
                } else {
                    cVar.a--;
                    if (cVar2 instanceof e) {
                        cVar.a = 0;
                        Log.d("MediaInfo", "getDetailThumbnails::Raw File. No RETRYING=" + errorCode + ", ID=" + cVar.getTaskId());
                    }
                    if (cVar.a > 0) {
                        Log.d("MediaInfo", "getDetailThumbnails::onEditorAsyncDone : RETRYING=" + errorCode + ", ID=" + cVar.getTaskId());
                        file3.delete();
                        if (errorCode == NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL || cVar.b) {
                            cVar.a = 0;
                            cVar.sendFailure(NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL);
                            MediaInfo.S();
                            return;
                        }
                        MediaInfo.B.add(cVar);
                        if (errorCode != NexEditor.ErrorCode.INPROGRESS_GETCLIPINFO) {
                            MediaInfo.S();
                            return;
                        }
                        return;
                    }
                    Log.d("MediaInfo", "getDetailThumbnails::onEditorAsyncDone : SEND FAILURE=" + errorCode + ", cancel=" + cVar.b);
                    if (errorCode == NexEditor.ErrorCode.GETCLIPINFO_USER_CANCEL || cVar.b) {
                        cVar.a = 0;
                    }
                    file3.delete();
                    cVar.sendFailure(errorCode);
                    MediaInfo.S();
                }
            }
        }, new e() {
            public void a(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6, int i7) {
                if (i != 1) {
                    Log.d("MediaInfo", "getDetailThumbnails::onThumbDone() not video. mode=" + i);
                    return;
                }
                try {
                    g.a(bArr, i2, i3, i4, (i5 / (i3 * i4)) * 8, i6 - 1, i7, cVar2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        if (Q() == null || !B.isEmpty()) {
            z2 = false;
        }
        B.add(cVar3);
        if (z2) {
            S();
        }
        return cVar3;
    }

    public NexEditor.ErrorCode a(boolean z2, boolean z3) {
        NexEditor Q = Q();
        if (Q == null) {
            return NexEditor.ErrorCode.GENERAL;
        }
        int i2 = z2 ? 256 : 0;
        if (z3) {
            i2 |= 16;
        }
        return Q.a(this.c, new NexClipInfo(), i2, 0);
    }

    public ResultTask<h> b() {
        if (this.g == null && this.j == null) {
            return ResultTask.failedResultTask(MediaInfoError.ThumbnailsNotAvailable);
        }
        Log.d("MediaInfo", "getThumbnails(" + this.c + ") sPendingThumbnailTasks=" + B.size());
        c<h, f> cVar = this.o;
        if (cVar == null || cVar.didSignalEvent(Task.Event.FAIL)) {
            this.o = new c<>(this, new f(this.c, this.j, 640, 360, 0, r(), 30, 0), new d<h, f>() {
                public void a(c<h, f> cVar, NexEditor.ErrorCode errorCode) {
                    Log.d("MediaInfo", "getThumbnails::onEditorAsyncDone(" + MediaInfo.this.c + ") resultCode=" + errorCode + " retry=" + cVar.a);
                    if (errorCode != NexEditor.ErrorCode.NONE) {
                        cVar.a--;
                        if (cVar.a > 0) {
                            Log.d("MediaInfo", "getThumbnails::onEditorAsyncDone : RETRYING ID=" + cVar.getTaskId());
                            MediaInfo.B.add(cVar);
                            if (MediaInfo.B.size() <= 1 && errorCode != NexEditor.ErrorCode.INPROGRESS_GETCLIPINFO) {
                                MediaInfo.S();
                                return;
                            }
                            return;
                        }
                        Log.d("MediaInfo", "getThumbnails::onEditorAsyncDone : SEND FAILURE");
                        MediaInfo.this.o.sendFailure(errorCode);
                        return;
                    }
                    MediaInfo.S();
                    Log.d("MediaInfo", "getThumbnails::onEditorAsyncDone(" + MediaInfo.this.c + ") Start JPEG Conversion");
                    MediaInfo.this.O().onComplete(new Task.OnTaskEventListener() {
                        public void onTaskEvent(Task task, Task.Event event) {
                            MediaInfo.this.c(MediaInfo.this.g).onResultAvailable(new ResultTask.OnResultAvailableListener<a>() {
                                /* renamed from: a */
                                public void onResultAvailable(ResultTask<a> resultTask, Task.Event event, a aVar) {
                                    if (aVar != null) {
                                        MediaInfo.this.o.sendResult(new i(aVar.d, aVar.a, aVar.b, aVar.c));
                                    } else {
                                        MediaInfo.this.o.sendFailure((Task.TaskError) null);
                                    }
                                }
                            });
                        }
                    }).onFailure((Task.OnFailListener) new Task.OnFailListener() {
                        public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                            MediaInfo.this.o.sendFailure((Task.TaskError) null);
                        }
                    });
                }
            });
            c(this.g).onResultAvailable(new ResultTask.OnResultAvailableListener<a>() {
                /* renamed from: a */
                public void onResultAvailable(ResultTask<a> resultTask, Task.Event event, a aVar) {
                    if (aVar == null || aVar.d == null || aVar.a <= 0 || aVar.b <= 0 || aVar.c == null) {
                        boolean z = MediaInfo.Q() != null && MediaInfo.B.isEmpty();
                        MediaInfo.B.add(MediaInfo.this.o);
                        if (z) {
                            MediaInfo.S();
                            return;
                        }
                        return;
                    }
                    MediaInfo.this.o.sendResult(new i(aVar.d, aVar.a, aVar.b, aVar.c));
                }
            });
            Log.d("MediaInfo", "getThumbnails(" + this.c + ") returning NEW task");
            return this.o;
        }
        Log.d("MediaInfo", "getThumbnails(" + this.c + ") returning existing task");
        return this.o;
    }

    public ResultTask<b> c() {
        if (this.k == null) {
            return ResultTask.failedResultTask(MediaInfoError.PCMLevelsNotAvailable);
        }
        c<b, Void> cVar = this.p;
        if (cVar != null && !cVar.didSignalEvent(Task.Event.FAIL)) {
            return this.p;
        }
        this.p = new c<>(this, null, new d<b, Void>() {
            public void a(final c<b, Void> cVar, NexEditor.ErrorCode errorCode) {
                if (errorCode == NexEditor.ErrorCode.INVALID_STATE) {
                    MediaInfo.D.add(cVar);
                    return;
                }
                MediaInfo.R();
                cVar.c.P().onResultAvailable(new ResultTask.OnResultAvailableListener<b>() {
                    /* renamed from: a */
                    public void onResultAvailable(ResultTask<b> resultTask, Task.Event event, b bVar) {
                        if (bVar != null) {
                            cVar.sendResult(bVar);
                        } else {
                            cVar.sendFailure((Task.TaskError) null);
                        }
                    }
                });
            }
        });
        P().onResultAvailable(new ResultTask.OnResultAvailableListener<b>() {
            /* renamed from: a */
            public void onResultAvailable(ResultTask<b> resultTask, Task.Event event, b bVar) {
                if (bVar != null) {
                    MediaInfo.this.p.sendResult(bVar);
                    return;
                }
                NexEditor H = MediaInfo.Q();
                if (H == null || !MediaInfo.D.isEmpty()) {
                    MediaInfo.D.add(MediaInfo.this.p);
                } else {
                    H.a(MediaInfo.this.c, MediaInfo.this.k, MediaInfo.this.p.getTaskId());
                }
            }
        });
        return this.p;
    }

    public int[] d() {
        if (this.t == null) {
            e();
            if (this.t == null) {
                throw new IllegalStateException();
            }
        }
        try {
            return this.t.get();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
            return null;
        } catch (ExecutionException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public ResultTask<int[]> e() {
        if (this.f == null) {
            return ResultTask.failedResultTask(MediaInfoError.SeekPointsNotAvailable);
        }
        ResultTask<int[]> resultTask = this.s;
        if (resultTask != null && !resultTask.didSignalEvent(Task.Event.FAIL)) {
            return this.s;
        }
        Log.d("MediaInfo", "getSeekPoints(" + this.c + ")");
        this.s = new ResultTask<>();
        this.t = new AsyncTask<String, Integer, int[]>() {
            private void a(File file, int[] iArr) throws IOException {
                Log.d("MediaInfo", "getSeekPoints():writeFile(" + file + ")");
                DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                int i = 0;
                while (i < iArr.length) {
                    try {
                        dataOutputStream.writeInt(iArr[i]);
                        i++;
                    } finally {
                        dataOutputStream.close();
                        Log.d("MediaInfo", "getSeekPoints(" + MediaInfo.this.c + ") wrote file: " + file);
                        file.setReadable(true);
                    }
                }
                Log.d("MediaInfo", "getSeekPoints(" + MediaInfo.this.c + ") wrote " + iArr.length + " points");
            }

            private int[] a(File file) throws IOException {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                try {
                    int min = ((int) Math.min(file.length(), 204800)) / 4;
                    int[] iArr = new int[min];
                    for (int i = 0; i < min; i++) {
                        iArr[i] = dataInputStream.readInt();
                    }
                    Log.d("MediaInfo", "getSeekPoints():readFile : got " + min + " entries.");
                    return iArr;
                } finally {
                    dataInputStream.close();
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(int[] iArr) {
                if (iArr == null) {
                    Log.d("MediaInfo", "onPostExecute : FAIL");
                    MediaInfo.this.s.signalEvent(Task.Event.FAIL);
                    return;
                }
                Log.d("MediaInfo", "onPostExecute : SUCCESS " + iArr.length);
                MediaInfo.this.s.setResult(iArr);
                MediaInfo.this.s.signalEvent(Task.Event.RESULT_AVAILABLE, Task.Event.SUCCESS, Task.Event.COMPLETE);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public int[] doInBackground(String... strArr) {
                Log.d("MediaInfo", "getSeekPoints:doInBackground(" + strArr[0] + ")");
                try {
                    return a(MediaInfo.this.f);
                } catch (IOException unused) {
                    Log.d("MediaInfo", "getSeekPoints:doInBackground(" + strArr[0] + ") -> no cache available; making");
                    NexEditor H = MediaInfo.Q();
                    if (H == null) {
                        return null;
                    }
                    NexClipInfo nexClipInfo = new NexClipInfo();
                    NexEditor.ErrorCode a2 = H.a(strArr[0], nexClipInfo, true, 0);
                    if (a2.isError() || nexClipInfo.mSeekTable == null) {
                        Log.d("MediaInfo", "getSeekPoints(" + MediaInfo.this.c + ") FAIL -> " + a2);
                        return null;
                    }
                    try {
                        a(MediaInfo.this.f, nexClipInfo.mSeekTable);
                    } catch (IOException e) {
                        Log.d("MediaInfo", "getSeekPoints(" + MediaInfo.this.c + ") FAILED WRITING FILE");
                        e.printStackTrace();
                    }
                    return nexClipInfo.mSeekTable;
                }
            }
        }.executeOnExecutor(w, new String[]{this.c});
        return this.s;
    }

    public int f() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.m;
    }

    public boolean g() {
        return this.n != NexEditor.ErrorCode.UNSUPPORT_MAX_RESOLUTION && o() * n() <= (NexEditorDeviceProfile.getDeviceProfile().getMaxResolution() * 110) / 100;
    }

    public boolean h() {
        return !this.x && !this.n.isError() && g();
    }

    public boolean i() {
        return this.x || this.n.isError();
    }

    public int j() {
        if (!this.n.isError()) {
            return h() ? 0 : -1;
        }
        switch (this.n) {
            case UNSUPPORT_AUDIO_CODEC:
                return -2;
            case UNSUPPORT_AUDIO_PROFILE:
                return -3;
            case UNSUPPORT_FORMAT:
                return -4;
            case UNSUPPORT_MAX_RESOLUTION:
                return -5;
            case UNSUPPORT_MIN_DURATION:
                return -6;
            case UNSUPPORT_MIN_RESOLUTION:
                return -7;
            case UNSUPPORT_VIDEIO_PROFILE:
                return -8;
            case UNSUPPORT_VIDEO_CODEC:
                return -9;
            case UNSUPPORT_VIDEO_FPS:
                return -10;
            case UNSUPPORT_VIDEO_LEVEL:
                return -11;
            default:
                return -12;
        }
    }

    public boolean l() {
        if (this.x || this.n.isError()) {
            return false;
        }
        return this.u.d;
    }

    public boolean m() {
        if (this.x || this.n.isError()) {
            return false;
        }
        return this.u.e;
    }

    public int n() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.g;
    }

    public int o() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.h;
    }

    public int p() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.d ? this.u.k : this.u.l;
    }

    public int q() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.k;
    }

    public int r() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.l;
    }

    public int s() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.n;
    }

    public int t() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.v;
    }

    public int u() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.o;
    }

    public int v() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.p;
    }

    public int w() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.r;
    }

    public int x() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.s;
    }

    public int y() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.t;
    }

    public int z() {
        if (this.x || this.n.isError()) {
            return 0;
        }
        return this.u.u;
    }
}
