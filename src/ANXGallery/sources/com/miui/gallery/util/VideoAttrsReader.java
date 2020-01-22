package com.miui.gallery.util;

import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import android.text.format.Time;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoAttrsReader {
    private long mDateTaken = -1;
    private long mDuration = -1;
    private int mHeight = -1;
    private double mLatitude = 0.0d;
    private double mLongitude = 0.0d;
    private final String mPath;
    private String mTitle;
    private int mWidth = -1;

    public static class VideoAttrsUnretrivableException extends RuntimeException {
        public VideoAttrsUnretrivableException(String str, Throwable th) {
            super(str, th);
        }
    }

    private VideoAttrsReader(String str) throws IOException {
        this.mPath = str;
        initByMediaMediaPlayer(this.mPath);
    }

    private long calculateTaken(String str) {
        Date date;
        if (!TextUtils.isEmpty(str)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss.SSS'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                date = simpleDateFormat.parse(str);
            } catch (Exception unused) {
                Log.i("VideoAttrsReader", "simple format error %s", (Object) str);
                return new Time(str).toMillis(true);
            }
        } else {
            date = null;
        }
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

    private void dump(String str) {
        Log.d("VideoAttrsReader", "msg=" + str + "\r\n" + "file=" + this.mPath + "\r\n" + "title=" + this.mTitle + "\r\n" + "width=" + this.mWidth + "\r\n" + "height=" + this.mHeight + "\r\n" + "duration=" + this.mDuration + "\r\n" + "datataken=" + this.mDateTaken + "\r\n" + "latitude=" + this.mLatitude + "\r\n" + "longitude=" + this.mLongitude);
    }

    private void getLatLong(String str) {
        Matcher matcher = Pattern.compile("([+-]\\d+\\.\\d+)([+-]\\d+\\.\\d+)").matcher(str);
        if (matcher.find()) {
            this.mLatitude = Double.parseDouble(matcher.group(1));
            this.mLongitude = Double.parseDouble(matcher.group(2));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a A[SYNTHETIC, Splitter:B:28:0x008a] */
    private void initByMediaMediaPlayer(String str) throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever;
        RuntimeException e;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(str);
                this.mTitle = mediaMetadataRetriever.extractMetadata(7);
                this.mWidth = parseIntSafely(mediaMetadataRetriever.extractMetadata(18));
                this.mHeight = parseIntSafely(mediaMetadataRetriever.extractMetadata(19));
                this.mDuration = parseLongSafely(mediaMetadataRetriever.extractMetadata(9));
                this.mDateTaken = calculateTaken(mediaMetadataRetriever.extractMetadata(5));
                String extractMetadata = mediaMetadataRetriever.extractMetadata(23);
                if (extractMetadata != null) {
                    getLatLong(extractMetadata);
                }
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException unused) {
                }
                if (TextUtils.isEmpty(this.mTitle)) {
                    this.mTitle = FileUtils.getFileTitle(FileUtils.getFileName(str));
                }
                if (this.mDateTaken <= 0) {
                    this.mDateTaken = new File(str).lastModified();
                }
                dump("final result");
            } catch (RuntimeException e2) {
                e = e2;
                try {
                    throw new VideoAttrsUnretrivableException(str, e);
                } catch (Throwable th) {
                    th = th;
                    if (mediaMetadataRetriever != null) {
                        try {
                            mediaMetadataRetriever.release();
                        } catch (RuntimeException unused2) {
                        }
                    }
                    throw th;
                }
            }
        } catch (RuntimeException e3) {
            RuntimeException runtimeException = e3;
            mediaMetadataRetriever = null;
            e = runtimeException;
            throw new VideoAttrsUnretrivableException(str, e);
        } catch (Throwable th2) {
            th = th2;
            mediaMetadataRetriever = null;
            if (mediaMetadataRetriever != null) {
            }
            throw th;
        }
    }

    private int parseIntSafely(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private long parseLongSafely(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static VideoAttrsReader read(String str) throws IOException {
        return new VideoAttrsReader(str);
    }

    public long getDateTaken() {
        return this.mDateTaken;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public double[] getLatLong() {
        return new double[]{this.mLatitude, this.mLongitude};
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getVideoHeight() {
        return this.mHeight;
    }

    public int getVideoWidth() {
        return this.mWidth;
    }
}
