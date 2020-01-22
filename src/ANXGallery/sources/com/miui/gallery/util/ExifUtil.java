package com.miui.gallery.util;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.miui.gallery.stat.BaseSamplingStatHelper;
import com.miui.gallery3d.exif.ExifInterface;
import com.miui.gallery3d.exif.ExifInvalidFormatException;
import com.nexstreaming.nexeditorsdk.nexClip;
import com.nexstreaming.nexeditorsdk.nexExportFormat;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ExifUtil {
    public static final ExifCreator<ExifInterface> sGallery3DExifCreator = new ExifCreator<ExifInterface>() {
        public ExifInterface create(FileDescriptor fileDescriptor) {
            Log.d("ExifUtil", "Not support create com.miui.gallery3d.exif.ExifInterface from fileDescriptor");
            return null;
        }

        public ExifInterface create(InputStream inputStream) {
            if (inputStream == null) {
                return null;
            }
            try {
                ExifInterface exifInterface = new ExifInterface();
                exifInterface.readExif(inputStream);
                return exifInterface;
            } catch (ExifInvalidFormatException e) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", inputStream, e);
                return null;
            } catch (IOException e2) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", inputStream, e2);
                return null;
            }
        }

        public ExifInterface create(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                ExifInterface exifInterface = new ExifInterface();
                exifInterface.readExif(str);
                return exifInterface;
            } catch (ExifInvalidFormatException e) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", str, e);
                return null;
            } catch (IOException e2) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", str, e2);
                return null;
            }
        }
    };
    public static final ExifCreator<android.media.ExifInterface> sMediaExifCreator = new ExifCreator<android.media.ExifInterface>() {
        @TargetApi(24)
        public android.media.ExifInterface create(FileDescriptor fileDescriptor) {
            if (fileDescriptor == null) {
                return null;
            }
            try {
                return new android.media.ExifInterface(fileDescriptor);
            } catch (IOException e) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", fileDescriptor, e);
                return null;
            }
        }

        @TargetApi(24)
        public android.media.ExifInterface create(InputStream inputStream) {
            if (inputStream == null) {
                return null;
            }
            try {
                return new android.media.ExifInterface(inputStream);
            } catch (IOException e) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", inputStream, e);
                return null;
            }
        }

        public android.media.ExifInterface create(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                return new android.media.ExifInterface(str);
            } catch (IOException e) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", str, e);
                return null;
            }
        }
    };
    public static final ExifCreator<androidx.exifinterface.media.ExifInterface> sSupportExifCreator = new ExifCreator<androidx.exifinterface.media.ExifInterface>() {
        public androidx.exifinterface.media.ExifInterface create(FileDescriptor fileDescriptor) {
            Log.d("ExifUtil", "Not support create android.support.media.ExifInterface from fileDescriptor");
            return null;
        }

        public androidx.exifinterface.media.ExifInterface create(InputStream inputStream) {
            if (inputStream == null) {
                return null;
            }
            try {
                return new androidx.exifinterface.media.ExifInterface(inputStream);
            } catch (IOException e) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", inputStream, e);
                return null;
            }
        }

        public androidx.exifinterface.media.ExifInterface create(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                return new androidx.exifinterface.media.ExifInterface(str);
            } catch (IOException e) {
                Log.e("ExifUtil", "failed to create exif interface from %s, %s", str, e);
                return null;
            }
        }
    };

    public interface ExifCreator<T> {
        T create(FileDescriptor fileDescriptor);

        T create(InputStream inputStream);

        T create(String str);
    }

    public static class ExifInfo {
        public final int exifOrientation;
        public final boolean flip;
        public final int rotation;

        public ExifInfo(int i, int i2, boolean z) {
            this.exifOrientation = i;
            this.rotation = i2;
            this.flip = z;
        }
    }

    public static final class UserCommentData {
        private String mExt;
        private String mSha1;

        public UserCommentData(String str, String str2) {
            this.mSha1 = str;
            this.mExt = str2;
        }

        public String getExt() {
            return this.mExt;
        }

        public String getFileName(String str) {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(getExt())) {
                return null;
            }
            return str + "." + getExt();
        }

        public String getSha1() {
            return this.mSha1;
        }
    }

    public static Rect adjustRectOrientation(int i, int i2, Rect rect, int i3, boolean z) {
        RectF adjustRectOrientation = adjustRectOrientation(i, i2, new RectF(rect), i3, z);
        return new Rect((int) adjustRectOrientation.left, (int) adjustRectOrientation.top, (int) adjustRectOrientation.right, (int) adjustRectOrientation.bottom);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        r0 = (((float) r9) - r10.top) - r10.height();
        r1 = r10.left;
        r2 = r10.height();
        r3 = r10.width();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r8 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        r0 = r10.top;
        r1 = (((float) r8) - r10.left) - r10.width();
        r2 = r10.height();
        r3 = r10.width();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        r0 = (((float) r8) - r10.left) - r10.width();
        r1 = (((float) r9) - r10.top) - r10.height();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006d, code lost:
        if (r12 == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006f, code lost:
        r0 = (((float) r8) - r0) - r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007a, code lost:
        return new android.graphics.RectF(r0, r1, r2 + r0, r3 + r1);
     */
    public static RectF adjustRectOrientation(int i, int i2, RectF rectF, int i3, boolean z) {
        float f = rectF.left;
        float f2 = rectF.top;
        float width = rectF.width();
        float height = rectF.height();
        if (!z) {
            if (i3 == 5) {
                i3 = 7;
            } else if (i3 == 6) {
                i3 = 8;
            } else if (i3 == 7) {
                i3 = 5;
            } else if (i3 == 8) {
                i3 = 6;
            }
        }
        boolean z2 = false;
        switch (i3) {
            case 2:
                z2 = true;
                break;
            case 3:
                break;
            case 4:
                z2 = true;
                break;
            case 5:
                z2 = true;
                break;
            case 6:
                break;
            case 7:
                z2 = true;
                break;
            case 8:
                break;
        }
    }

    public static <T> T createExifInterface(InputStream inputStream, ExifCreator<T> exifCreator) {
        if (exifCreator == null || inputStream == null) {
            return null;
        }
        try {
            T create = exifCreator.create(inputStream);
            if (create != null) {
                BaseMiscUtil.closeSilently(inputStream);
                return create;
            }
        } catch (Exception e) {
            Log.w("ExifUtil", "Can't read EXIF tags from file ", e);
        } catch (Throwable th) {
            BaseMiscUtil.closeSilently(inputStream);
            throw th;
        }
        BaseMiscUtil.closeSilently(inputStream);
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0034 A[RETURN] */
    public static <T> T createExifInterface(String str, byte[] bArr, ExifCreator<T> exifCreator) {
        InputStream inputStream;
        if (exifCreator == null || !BaseFileUtils.isFileExist(str)) {
            return null;
        }
        try {
            inputStream = BaseBitmapUtils.createInputStream(str, bArr);
            try {
                T create = exifCreator.create(inputStream);
                if (create != null) {
                    BaseMiscUtil.closeSilently(inputStream);
                    return create;
                }
            } catch (Exception e) {
                e = e;
                try {
                    Log.w("ExifUtil", "Can't read EXIF tags from file [%s] %s", str, e);
                    BaseMiscUtil.closeSilently(inputStream);
                    if (bArr != null) {
                    }
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            inputStream = null;
            Log.w("ExifUtil", "Can't read EXIF tags from file [%s] %s", str, e);
            BaseMiscUtil.closeSilently(inputStream);
            if (bArr != null) {
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            BaseMiscUtil.closeSilently(inputStream);
            throw th;
        }
        BaseMiscUtil.closeSilently(inputStream);
        if (bArr != null) {
            return createExifInterfaceByDecryptFile(str, bArr, exifCreator);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        if (r2 != null) goto L_0x004a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0051  */
    public static <T> T createExifInterfaceByDecryptFile(String str, byte[] bArr, ExifCreator<T> exifCreator) {
        File file;
        if (exifCreator == null || !BaseFileUtils.isFileExist(str)) {
            return null;
        }
        try {
            file = new File(StaticContext.sGetAndroidContext().getFilesDir(), String.valueOf(SystemClock.elapsedRealtimeNanos()));
            try {
                if (CryptoUtil.decryptFile(str, file.getAbsolutePath(), bArr)) {
                    Log.d("ExifUtil", "create exif by decrypt file");
                    T create = exifCreator.create(file.getAbsolutePath());
                    BaseFileUtils.deleteFile(file);
                    return create;
                }
            } catch (Exception e) {
                e = e;
                try {
                    Log.w("ExifUtil", "Can't read EXIF tags from file [%s] %s", str, e);
                } catch (Throwable th) {
                    th = th;
                    if (file != null) {
                    }
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            file = null;
            Log.w("ExifUtil", "Can't read EXIF tags from file [%s] %s", str, e);
        } catch (Throwable th2) {
            th = th2;
            file = null;
            if (file != null) {
                BaseFileUtils.deleteFile(file);
            }
            throw th;
        }
        BaseFileUtils.deleteFile(file);
        return null;
    }

    public static int exifOrientationToDegrees(int i) {
        if (i == 3) {
            return nexClip.kClip_Rotate_180;
        }
        if (i == 6) {
            return 90;
        }
        if (i != 8) {
            return 0;
        }
        return nexClip.kClip_Rotate_270;
    }

    @Deprecated
    public static long getDateTime(android.media.ExifInterface exifInterface) {
        if (exifInterface == null) {
            return -1;
        }
        return GalleryTimeUtils.getDateTime(exifInterface.getAttribute("DateTime"), exifInterface.getAttribute("SubSecTime"));
    }

    public static long getDateTime(android.media.ExifInterface exifInterface, boolean z) {
        if (exifInterface == null) {
            return -1;
        }
        return z ? getDateTime(exifInterface) : GalleryTimeUtils.getDateTime(exifInterface.getAttribute("DateTime"));
    }

    @Deprecated
    public static long getDateTime(androidx.exifinterface.media.ExifInterface exifInterface) {
        if (exifInterface == null) {
            return -1;
        }
        return GalleryTimeUtils.getDateTime(exifInterface.getAttribute("DateTime"), exifInterface.getAttribute("SubSecTime"));
    }

    public static long getDateTime(androidx.exifinterface.media.ExifInterface exifInterface, boolean z) {
        if (exifInterface == null) {
            return -1;
        }
        return z ? getDateTime(exifInterface) : GalleryTimeUtils.getDateTime(exifInterface.getAttribute("DateTime"));
    }

    public static long getGpsDateTime(android.media.ExifInterface exifInterface) {
        if (exifInterface == null) {
            return -1;
        }
        return GalleryTimeUtils.getGpsDateTime(exifInterface.getAttribute("GPSDateStamp"), exifInterface.getAttribute("GPSTimeStamp"));
    }

    public static long getGpsDateTime(androidx.exifinterface.media.ExifInterface exifInterface) {
        if (exifInterface == null) {
            return -1;
        }
        return GalleryTimeUtils.getGpsDateTime(exifInterface.getAttribute("GPSDateStamp"), exifInterface.getAttribute("GPSTimeStamp"));
    }

    public static int getMTSpecialAITypeValue(ExifInterface exifInterface) {
        Integer tagIntValue = exifInterface != null ? exifInterface.getTagIntValue(42243, 2) : null;
        if (tagIntValue == null) {
            return -1;
        }
        return tagIntValue.intValue();
    }

    public static int getMTSpecialAITypeValue(String str) {
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.readExif(str);
            return getMTSpecialAITypeValue(exifInterface);
        } catch (ExifInvalidFormatException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static int getOrientation(ExifInterface exifInterface) {
        Integer tagIntValue = exifInterface == null ? null : exifInterface.getTagIntValue(ExifInterface.TAG_ORIENTATION);
        if (tagIntValue == null) {
            return 1;
        }
        return tagIntValue.intValue();
    }

    public static int getRotationDegrees(androidx.exifinterface.media.ExifInterface exifInterface) {
        int i = 1;
        if (exifInterface != null) {
            i = exifInterface.getAttributeInt("Orientation", 1);
        }
        return exifOrientationToDegrees(i);
    }

    public static int getRotationDegrees(ExifInterface exifInterface) {
        return exifOrientationToDegrees(getOrientation(exifInterface));
    }

    public static UserCommentData getUserCommentData(String str) throws Exception {
        if (!TextUtils.isEmpty(str)) {
            try {
                UserComment userComment = new UserComment(new ExifInterfaceWrapper(sSupportExifCreator.create(str)));
                if (userComment.isOriginalUserCommentUsable()) {
                    return new UserCommentData(userComment.getSha1(), userComment.getFileExt());
                }
                e = null;
                File file = new File(str);
                if (file.exists() && file.isFile() && file.length() <= 1048576) {
                    try {
                        ExifInterface create = sGallery3DExifCreator.create(str);
                        if (create != null) {
                            UserComment userComment2 = new UserComment(new ExifInterfaceWrapper(create));
                            if (userComment2.isOriginalUserCommentUsable() && !TextUtils.isEmpty(userComment2.getSha1())) {
                                UserCommentData userCommentData = new UserCommentData(userComment2.getSha1(), userComment2.getFileExt());
                                Map<String, String> generatorCommonParams = BaseSamplingStatHelper.generatorCommonParams();
                                generatorCommonParams.put("exception", e != null ? e.toString() : "unrecognizable UserComment");
                                generatorCommonParams.put(nexExportFormat.TAG_FORMAT_PATH, str);
                                BaseSamplingStatHelper.recordErrorEvent("exif_parser", "exif_read_by_gallery_3d_exif_interface", generatorCommonParams);
                                rewriteUserComment(str, userCommentData);
                                return userCommentData;
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        Log.d("ExifUtil", "Failed to read user comment using gallery 3d exif interface, %s", (Object) e);
                    }
                    try {
                        UserComment userComment3 = new UserComment(new ExifInterfaceWrapper(sMediaExifCreator.create(str)));
                        if (userComment3.isOriginalUserCommentUsable()) {
                            return new UserCommentData(userComment3.getSha1(), userComment3.getFileExt());
                        }
                    } catch (Exception e2) {
                        e = e2;
                        Log.d("ExifUtil", "Failed to read user comment using media exif interface, %s", (Object) e);
                    }
                }
                if (e != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("model", Build.MODEL);
                    hashMap.put("version", Build.VERSION.INCREMENTAL);
                    hashMap.put(nexExportFormat.TAG_FORMAT_PATH, str);
                    hashMap.put("exception", e.toString());
                    BaseSamplingStatHelper.recordCountEvent("exif_parser", "exif_read_error", hashMap);
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                Log.d("ExifUtil", "Failed to read user comment using support exif interface, %s", (Object) e);
            }
        }
        return null;
    }

    public static String getUserCommentSha1(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            UserCommentData userCommentData = getUserCommentData(str);
            if (userCommentData != null) {
                return userCommentData.getSha1();
            }
            return null;
        } catch (Exception e) {
            Log.e("ExifUtil", "Failed to read exif!!", (Object) e);
            return null;
        }
    }

    public static String getXiaomiComment(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ExifInterface create = sGallery3DExifCreator.create(str);
            if (create != null) {
                return create.getXiaomiComment();
            }
            return null;
        } catch (Exception e) {
            Log.w("ExifUtil", (Throwable) e);
            return null;
        }
    }

    public static String getXiaomiCommentSensorType(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String xiaomiComment = getXiaomiComment(str);
            if (!TextUtils.isEmpty(xiaomiComment)) {
                return new JSONObject(xiaomiComment).optString("sensor_type");
            }
            return null;
        } catch (Exception e) {
            Log.w("ExifUtil", (Throwable) e);
            return null;
        }
    }

    public static boolean isFromFrontCamera(String str) {
        return "front".equalsIgnoreCase(getXiaomiCommentSensorType(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0004, code lost:
        r3 = r3.getTagByteValue(34967, 2);
     */
    public static boolean isMotionPhoto(ExifInterface exifInterface) {
        Byte tagByteValue;
        return (exifInterface == null || tagByteValue == null || tagByteValue.byteValue() != 1) ? false : true;
    }

    public static boolean isMotionPhoto(String str) {
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.readExif(str);
            return isMotionPhoto(exifInterface);
        } catch (ExifInvalidFormatException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean isWidthHeightRotated(int i) {
        return i == 5 || i == 6 || i == 7 || i == 8;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r3 = r1;
        r1 = 0;
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        return new com.miui.gallery.util.ExifUtil.ExifInfo(r4, r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r1 = 90;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        r1 = com.nexstreaming.nexeditorsdk.nexClip.kClip_Rotate_270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1 = com.nexstreaming.nexeditorsdk.nexClip.kClip_Rotate_180;
     */
    public static ExifInfo parseRotationInfo(int i) {
        boolean z = false;
        boolean z2 = true;
        switch (i) {
            case 1:
                z2 = false;
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                z = true;
                break;
            case 5:
                z = true;
                break;
            case 6:
                break;
            case 7:
                z = true;
                break;
            case 8:
                break;
            default:
                int i2 = 0;
                break;
        }
    }

    public static <T> ExifInfo parseRotationInfo(T t) {
        int i;
        if (t != null) {
            if (t instanceof androidx.exifinterface.media.ExifInterface) {
                i = ((androidx.exifinterface.media.ExifInterface) t).getAttributeInt("Orientation", 1);
            } else if (t instanceof ExifInterface) {
                i = getOrientation((ExifInterface) t);
            } else if (t instanceof android.media.ExifInterface) {
                i = ((android.media.ExifInterface) t).getAttributeInt("Orientation", 1);
            } else {
                Log.w("ExifUtil", "Not supported exif interface %s", t);
            }
            return parseRotationInfo(i);
        }
        return null;
    }

    public static ExifInfo parseRotationInfo(String str, byte[] bArr) {
        if (bArr == null) {
            return parseRotationInfo(createExifInterface(str, bArr, sSupportExifCreator));
        }
        ExifInterface exifInterface = (ExifInterface) createExifInterface(str, bArr, sGallery3DExifCreator);
        return exifInterface == null ? parseRotationInfo((androidx.exifinterface.media.ExifInterface) createExifInterfaceByDecryptFile(str, bArr, sSupportExifCreator)) : parseRotationInfo(exifInterface);
    }

    private static void rewriteUserComment(String str, UserCommentData userCommentData) {
        try {
            Log.d("ExifUtil", "Try to rewrite UserComment using android.media.ExifInterface");
            android.media.ExifInterface create = sMediaExifCreator.create(str);
            setUserCommentData(create, userCommentData);
            create.saveAttributes();
        } catch (Exception e) {
            Log.w("ExifUtil", "Failed to rewrite UserComment using android.media.ExifInterface, %s", e);
        }
    }

    public static void setDateTime(android.media.ExifInterface exifInterface, String str) {
        if (exifInterface != null && !TextUtils.isEmpty(str)) {
            exifInterface.setAttribute("DateTimeOriginal", str);
            exifInterface.setAttribute("DateTime", str);
        }
    }

    public static void setDateTime(ExifInterface exifInterface, String str) {
        if (exifInterface != null && !TextUtils.isEmpty(str)) {
            exifInterface.addDateTimeStampTag(ExifInterface.TAG_DATE_TIME_ORIGINAL, str);
            exifInterface.addDateTimeStampTag(ExifInterface.TAG_DATE_TIME, str);
        }
    }

    public static boolean setDateTime(android.media.ExifInterface exifInterface, long j, boolean z) {
        if (!(exifInterface == null || j == 0)) {
            try {
                SimpleDateFormat defaultDateFormat = GalleryTimeUtils.getDefaultDateFormat();
                Date date = new Date(j);
                if (z) {
                    setDateTime(exifInterface, defaultDateFormat.format(date));
                    if (getGpsDateTime(exifInterface) != -1) {
                        String format = GalleryTimeUtils.getUTCDateFormat().format(date);
                        String[] split = format.split(" ");
                        setGpsDateTime(exifInterface, split[0], split[1]);
                        Log.d("ExifUtil", "修改照片时间(包含GPS),修改后的时间为:%s", (Object) format);
                    }
                } else {
                    setDateTime(exifInterface, defaultDateFormat.format(date));
                }
                return true;
            } catch (Exception e) {
                Log.e("ExifUtil", (Throwable) e);
            }
        }
        return false;
    }

    public static boolean setDateTime(ExifInterface exifInterface, long j, boolean z) {
        if (!(exifInterface == null || j == 0)) {
            try {
                SimpleDateFormat defaultDateFormat = GalleryTimeUtils.getDefaultDateFormat();
                Date date = new Date(j);
                if (z) {
                    setDateTime(exifInterface, defaultDateFormat.format(date));
                    String format = GalleryTimeUtils.getUTCDateFormat().format(date);
                    String[] split = format.split(" ");
                    setGpsDateTime(exifInterface, split[0], split[1]);
                    Log.d("ExifUtil", "修改照片时间(包含GPS),修改后的时间为:%s", (Object) format);
                } else {
                    setDateTime(exifInterface, defaultDateFormat.format(date));
                }
                return true;
            } catch (Exception e) {
                Log.e("ExifUtil", (Throwable) e);
            }
        }
        return false;
    }

    public static void setGpsDateTime(android.media.ExifInterface exifInterface, String str, String str2) {
        if (exifInterface != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            exifInterface.setAttribute("GPSDateStamp", str);
            exifInterface.setAttribute("GPSTimeStamp", str2);
        }
    }

    public static void setGpsDateTime(ExifInterface exifInterface, String str, String str2) {
        if (exifInterface != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            exifInterface.addGpsDateTimeStampTag(str, str2);
        }
    }

    public static void setSubSecTime(android.media.ExifInterface exifInterface, long j) {
        if (exifInterface != null && j > 0) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j);
            int i = instance.get(14);
            if (i > 0) {
                exifInterface.setAttribute("SubSecTime", String.valueOf(i));
            }
        }
    }

    public static void setSubSecTime(ExifInterface exifInterface, long j) {
        if (exifInterface != null && j > 0) {
            exifInterface.addSubSecTimeStampTag(j);
        }
    }

    public static void setUserCommentData(android.media.ExifInterface exifInterface, UserCommentData userCommentData) throws Exception {
        if (exifInterface != null && userCommentData != null) {
            new UserComment(new ExifInterfaceWrapper(exifInterface)).setData(userCommentData);
        }
    }

    public static boolean supportRefocus(ExifInterface exifInterface) {
        return exifInterface != null && (!TextUtils.isEmpty(exifInterface.getTagStringValue(34960, 2)) || exifInterface.getTag(34968, 2) != null);
    }

    public static boolean supportRefocus(String str) {
        try {
            ExifInterface exifInterface = new ExifInterface();
            exifInterface.readExif(str);
            return supportRefocus(exifInterface);
        } catch (ExifInvalidFormatException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
