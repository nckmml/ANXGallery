package com.miui.gallery.util;

import android.text.TextUtils;
import com.miui.gallery.util.MediaFile;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BaseFileMimeUtil {
    private static final Mime[] IMAGE_MIMES = {new Mime("image/jpeg", new byte[]{-1, -40}, new byte[]{-1, -39}), new Mime("image/jpeg", new byte[]{-1, -40}, new byte[]{0, 0}), new Mime("image/png", new byte[]{-119, 80, 78, 71, 13, 10, 26, 10}, (byte[]) null), new Mime("image/tga", new byte[]{0, 0, 2, 0, 0}, (byte[]) null), new Mime("image/tga", new byte[]{0, 0, 16, 0, 0}, (byte[]) null), new Mime("image/gif", new byte[]{71, 73, 70, 56, 55, 97}, (byte[]) null), new Mime("image/gif", new byte[]{71, 73, 70, 56, 57, 97}, (byte[]) null), new Mime("image/bmp", new byte[]{66, 77}, (byte[]) null), new Mime("image/tiff", new byte[]{77, 77}, (byte[]) null), new Mime("image/tiff", new byte[]{73, 73}, (byte[]) null), new Mime("image/webp", new byte[]{82, 73}, (byte[]) null)};
    private static final int MAX_END_LENGTH;
    private static final int MAX_HEAD_LENGTH;
    protected static final Mime[] VIDEO_MIMES = {new Mime("video/3gpp", new byte[]{0, 0, 0, 0, 105, 115, 111, 109, 51, 103, 112, 52}, (byte[]) null), new Mime("video/mp4", new byte[]{0, 0, 0, 24, 102, 116, 121, 112, 105, 115, 111, 109}, (byte[]) null), new Mime("video/mp4", new byte[]{0, 0, 0, 24, 102, 116, 121, 112, 109, 112, 52, 50}, (byte[]) null), new Mime("video/mp4", new byte[]{0, 0, 0, 24, 102, 116, 121, 112, 51, 103, 112}, (byte[]) null), new Mime("video/3gp", new byte[]{0, 0, 0, 28, 102, 116, 121, 112, 51, 103, 112, 52}, (byte[]) null), new Mime("video/quicktime", new byte[]{0, 0, 0, 20, 102, 116, 121, 112, 113, 116}, (byte[]) null), new Mime("video/mp4", new byte[]{0, 0, 0, 32, 102, 116, 121, 112, 97, 118, 99, 49}, (byte[]) null), new Mime("video/quicktime", new byte[]{0, 0, 0, 28, 102, 116, 121, 112, 109, 112, 52, 50}, (byte[]) null), new Mime("video/mp4", new byte[]{0, 0, 0, 32, 102, 116, 121, 112, 105, 115, 111, 109}, (byte[]) null)};

    private static class Mime {
        private final byte[] mEnd;
        private final byte[] mHead;
        private final String mMime;

        public Mime(String str, byte[] bArr, byte[] bArr2) {
            this.mMime = str;
            this.mHead = bArr;
            this.mEnd = bArr2;
        }

        public int getEndLength() {
            return this.mEnd.length;
        }

        public int getHeadLength() {
            return this.mHead.length;
        }

        public String getMimeType() {
            return this.mMime;
        }

        public boolean hasEnd() {
            return this.mEnd != null;
        }

        public boolean hasHead() {
            return this.mHead != null;
        }

        public boolean isEndRight(byte[] bArr) {
            int length = this.mEnd.length;
            int length2 = bArr.length - length;
            int i = 0;
            while (i < length) {
                if (bArr[length2] != this.mEnd[i]) {
                    return false;
                }
                i++;
                length2++;
            }
            return true;
        }

        public boolean isHeadRight(byte[] bArr) {
            int length = this.mHead.length;
            for (int i = 0; i < length; i++) {
                if (bArr[i] != this.mHead[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    static {
        int i = 0;
        int i2 = 0;
        for (Mime mime : getMimes()) {
            if (mime.hasHead() && mime.getHeadLength() > i) {
                i = mime.getHeadLength();
            }
            if (mime.hasEnd() && mime.getEndLength() > i2) {
                i2 = mime.getEndLength();
            }
        }
        MAX_HEAD_LENGTH = i;
        MAX_END_LENGTH = i2;
    }

    public static String getMimeType(String str) {
        String mimeTypeForFile = !TextUtils.isEmpty(str) ? MediaFile.getMimeTypeForFile(str) : "*/*";
        return TextUtils.isEmpty(mimeTypeForFile) ? "*/*" : mimeTypeForFile;
    }

    public static String getMimeTypeByParseFile(String str) {
        try {
            return rawGetMimeType(str, getMimes());
        } catch (IOException e) {
            Log.w("BaseFileMimeUtil", (Throwable) e);
            return "*/*";
        }
    }

    private static Mime[] getMimes() {
        int length = IMAGE_MIMES.length + VIDEO_MIMES.length;
        Mime[] mimeArr = new Mime[length];
        for (int i = 0; i < length; i++) {
            Mime[] mimeArr2 = IMAGE_MIMES;
            if (i < mimeArr2.length) {
                mimeArr[i] = mimeArr2[i];
            } else {
                mimeArr[i] = VIDEO_MIMES[i - mimeArr2.length];
            }
        }
        return mimeArr;
    }

    public static boolean hasExif(String str) {
        MediaFile.MediaFileType fileType = MediaFile.getFileType(str);
        return fileType != null && (fileType.fileType == 31 || fileType.fileType == 37);
    }

    public static boolean isBmpFromMimeType(String str) {
        return TextUtils.equals(str, "image/x-ms-bmp");
    }

    public static boolean isGifFromMimeType(String str) {
        return TextUtils.equals(str, "image/gif");
    }

    public static boolean isImageFromMimeType(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("image");
    }

    public static boolean isJpegImageFromMimeType(String str) {
        return TextUtils.equals(str, "image/jpeg");
    }

    public static boolean isJpgFromMimeType(String str) {
        return TextUtils.equals(str, "image/jpeg");
    }

    public static boolean isPngImageFromMimeType(String str) {
        return TextUtils.equals(str, "image/png");
    }

    public static boolean isRawFromMimeType(String str) {
        return TextUtils.equals(str, "image/x-adobe-dng");
    }

    public static boolean isVideoFromMimeType(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("video");
    }

    public static boolean isWbmpFromMimeType(String str) {
        return TextUtils.equals(str, "image/vnd.wap.wbmp");
    }

    public static boolean isWebpFromMimeType(String str) {
        return TextUtils.equals(str, "image/webp");
    }

    protected static String rawGetMimeType(String str, Mime[] mimeArr) throws IOException {
        RandomAccessFile randomAccessFile;
        Mime[] mimeArr2 = mimeArr;
        byte[] bArr = new byte[MAX_HEAD_LENGTH];
        byte[] bArr2 = new byte[MAX_END_LENGTH];
        try {
            randomAccessFile = new RandomAccessFile(str, "r");
            long j = 0;
            try {
                randomAccessFile.seek(0);
                int i = 0;
                int read = randomAccessFile.read(bArr, 0, bArr.length);
                long length = randomAccessFile.length();
                long min = Math.min((long) bArr2.length, length);
                int i2 = (min > 0 ? 1 : (min == 0 ? 0 : -1));
                if (i2 < 0 || min > 2147483647L) {
                    Log.e("BaseFileMimeUtil", String.format("unexpected error, endBufferedLength: %d, file length: %d", new Object[]{Long.valueOf(min), Long.valueOf(length)}));
                    BaseMiscUtil.closeSilently(randomAccessFile);
                    return null;
                } else if (i2 == 0) {
                    Log.d("BaseFileMimeUtil", "endBufferedLength is 0, just return null mime type");
                    BaseMiscUtil.closeSilently(randomAccessFile);
                    return null;
                } else {
                    randomAccessFile.seek(length - min);
                    if (min == ((long) randomAccessFile.read(bArr2, 0, (int) min))) {
                        j = min;
                    }
                    int length2 = mimeArr2.length;
                    while (i < length2) {
                        Mime mime = mimeArr2[i];
                        if (mime.hasHead()) {
                            if (read >= mime.getHeadLength()) {
                                if (!mime.isHeadRight(bArr)) {
                                }
                            }
                            i++;
                        }
                        if (mime.hasEnd()) {
                            if (j >= ((long) mime.getEndLength())) {
                                if (!mime.isEndRight(bArr2)) {
                                }
                            }
                            i++;
                        }
                        String mimeType = mime.getMimeType();
                        BaseMiscUtil.closeSilently(randomAccessFile);
                        return mimeType;
                    }
                    BaseMiscUtil.closeSilently(randomAccessFile);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                BaseMiscUtil.closeSilently(randomAccessFile);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
            BaseMiscUtil.closeSilently(randomAccessFile);
            throw th;
        }
    }
}
