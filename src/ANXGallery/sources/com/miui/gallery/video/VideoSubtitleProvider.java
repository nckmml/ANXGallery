package com.miui.gallery.video;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.keyczar.Keyczar;

public class VideoSubtitleProvider {
    private Context mContext;
    private Future mFuture;
    public FutureListener<List<SubtitleItem>> mFutureListener = new FutureHandler<List<SubtitleItem>>() {
        public void onPostExecute(Future<List<SubtitleItem>> future) {
            List list;
            if (!future.isCancelled() && (list = future.get()) != null) {
                Log.d("VideoSubtitleProvider", "read subtitle success.");
                if (VideoSubtitleProvider.this.mListener != null) {
                    VideoSubtitleProvider.this.mListener.onSubtitleParsed(VideoSubtitleProvider.this.mVideoPath, list, VideoSubtitleProvider.this.mTypeface);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public Listener mListener;
    private ThreadPool.Job<List<SubtitleItem>> mTask = new ThreadPool.Job() {
        public final Object run(ThreadPool.JobContext jobContext) {
            return VideoSubtitleProvider.this.lambda$new$15$VideoSubtitleProvider(jobContext);
        }
    };
    /* access modifiers changed from: private */
    public volatile Typeface mTypeface;
    private final Object mTypefaceLock = new Object();
    /* access modifiers changed from: private */
    public String mVideoPath;

    public interface Listener {
        void onSubtitleParsed(String str, List<SubtitleItem> list, Typeface typeface);
    }

    public static class SubtitleItem {
        private long mEndTime;
        private long mStartTime;
        private String mText;

        public long getEndTime() {
            return this.mEndTime;
        }

        public long getStartTime() {
            return this.mStartTime;
        }

        public String getText() {
            return this.mText;
        }

        public void setEndTime(long j) {
            this.mEndTime = j;
        }

        public void setStartTime(long j) {
            this.mStartTime = j;
        }

        public void setText(String str) {
            this.mText = str;
        }
    }

    public VideoSubtitleProvider(Context context, Listener listener) {
        this.mContext = context.getApplicationContext();
        this.mListener = listener;
    }

    private void createTextTypeface() {
        synchronized (this.mTypefaceLock) {
            if (this.mTypeface == null) {
                try {
                    if (BuildUtil.isMiui10(GalleryApp.sGetAndroidContext())) {
                        this.mTypeface = Typeface.createFromFile("/system/fonts/Miui-Light.ttf");
                    } else if (Build.VERSION.SDK_INT >= 26) {
                        this.mTypeface = new Typeface.Builder("/system/fonts/MiLanProVF.ttf").setFontVariationSettings("'wght' 305").build();
                    } else {
                        this.mTypeface = Typeface.createFromFile("/system/fonts/MiLanProVF.ttf");
                    }
                } catch (Exception e) {
                    Log.d("VideoSubtitleProvider", "createTextTypeface occurs error.\n", (Object) e);
                }
            }
        }
    }

    public static SubtitleItem findActiveSubtitle(List<SubtitleItem> list, long j) {
        if (!MiscUtil.isValid(list) || j < 0) {
            return null;
        }
        int size = list.size();
        int i = 0;
        if (j < list.get(0).getStartTime()) {
            return null;
        }
        int i2 = size - 1;
        SubtitleItem subtitleItem = list.get(i2);
        if (j < subtitleItem.getStartTime()) {
            while (i2 > i + 1) {
                int i3 = (i2 + i) / 2;
                if (list.get(i3).getStartTime() > j) {
                    i2 = i3;
                } else {
                    i = i3;
                }
            }
            SubtitleItem subtitleItem2 = list.get(i);
            if (j > subtitleItem2.getEndTime()) {
                return null;
            }
            return subtitleItem2;
        } else if (j > subtitleItem.getEndTime()) {
            return null;
        } else {
            return subtitleItem;
        }
    }

    private static Uri getSubtitleUri(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse("content://com.miui.video.SRT/srt").buildUpon().appendPath(str).build();
    }

    private static long parseMs(String str) {
        return (Long.parseLong(str.split(":")[0].trim()) * 60 * 60 * 1000) + (Long.parseLong(str.split(":")[1].trim()) * 60 * 1000) + (Long.parseLong(str.split(":")[2].split(",")[0].trim()) * 1000) + Long.parseLong(str.split(":")[2].split(",")[1].trim());
    }

    private List<SubtitleItem> parseSrtInputStream(InputStream inputStream) throws IOException {
        String readLine;
        String readLine2;
        if (inputStream == null || inputStream.available() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Keyczar.DEFAULT_ENCODING));
        while (bufferedReader.readLine() != null && (readLine = bufferedReader.readLine()) != null) {
            SubtitleItem subtitleItem = new SubtitleItem();
            String[] split = readLine.split("-->");
            subtitleItem.setStartTime(parseMs(split[0]));
            subtitleItem.setEndTime(parseMs(split[1]));
            subtitleItem.setText(bufferedReader.readLine());
            arrayList.add(subtitleItem);
            do {
                readLine2 = bufferedReader.readLine();
                if (readLine2 == null || readLine2.trim().equals("")) {
                }
                readLine2 = bufferedReader.readLine();
                break;
            } while (readLine2.trim().equals(""));
        }
        return arrayList;
    }

    public void cancel() {
        Future future = this.mFuture;
        if (future != null) {
            future.cancel();
            this.mFuture = null;
        }
        this.mVideoPath = null;
    }

    public /* synthetic */ List lambda$new$15$VideoSubtitleProvider(ThreadPool.JobContext jobContext) {
        Uri subtitleUri;
        InputStream inputStream;
        if (jobContext.isCancelled()) {
            return null;
        }
        createTextTypeface();
        Context context = this.mContext;
        String str = this.mVideoPath;
        if (context == null || TextUtils.isEmpty(str) || (subtitleUri = getSubtitleUri(context, str)) == null) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(subtitleUri);
            try {
                List<SubtitleItem> parseSrtInputStream = parseSrtInputStream(inputStream);
                MiscUtil.closeSilently(inputStream);
                return parseSrtInputStream;
            } catch (Exception e) {
                e = e;
                try {
                    Log.w("VideoSubtitleProvider", "read subtitle error.\n", e);
                    MiscUtil.closeSilently(inputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(inputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            inputStream = null;
            Log.w("VideoSubtitleProvider", "read subtitle error.\n", e);
            MiscUtil.closeSilently(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            MiscUtil.closeSilently(inputStream);
            throw th;
        }
    }

    public void release() {
        cancel();
        this.mContext = null;
        this.mFutureListener = null;
        this.mTypeface = null;
    }

    public void request(String str) {
        if (!TextUtils.isEmpty(str)) {
            Future future = this.mFuture;
            if (future != null) {
                future.cancel();
            }
            this.mVideoPath = str;
            this.mFuture = ThreadManager.getMiscPool().submit(this.mTask, this.mFutureListener);
        }
    }
}
