package com.miui.gallery.util;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.miui.extraphoto.sdk.ExtraPhotoSDK;
import com.miui.gallery.R;
import com.miui.gallery.cloudcontrol.CloudControlStrategyHelper;
import com.miui.gallery.cloudcontrol.strategies.ScannerStrategy;
import com.miui.gallery3d.exif.ExifInterface;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import miui.util.SoftReferenceSingleton;
import org.jcodec.containers.mp4.MP4Util;
import org.jcodec.containers.mp4.boxes.MetaBox;
import org.jcodec.containers.mp4.boxes.MetaValue;

public class SpecialTypeMediaUtils {
    /* access modifiers changed from: private */
    public static final boolean DEBUG_ENABLE = Log.isLoggable("SpecialTypeMedia", 3);
    private static final SoftReferenceSingleton<ScannerStrategy.SpecialTypeMediaStrategy> SPECIAL_TYPE_MEDIA_STRATEGY = new SoftReferenceSingleton<ScannerStrategy.SpecialTypeMediaStrategy>() {
        /* access modifiers changed from: protected */
        public ScannerStrategy.SpecialTypeMediaStrategy createInstance() {
            ScannerStrategy.SpecialTypeMediaStrategy specialTypeMediaStrategy = CloudControlStrategyHelper.getSpecialTypeMediaStrategy();
            if (SpecialTypeMediaUtils.DEBUG_ENABLE) {
                Log.d("SpecialTypeMediaUtils", (Object) specialTypeMediaStrategy);
            }
            return specialTypeMediaStrategy;
        }
    };

    private static int extractFrameRate(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            mediaExtractor.setDataSource(str);
            int trackCount = mediaExtractor.getTrackCount();
            int i2 = 0;
            while (true) {
                if (i2 >= trackCount) {
                    break;
                }
                MediaFormat trackFormat = mediaExtractor.getTrackFormat(i2);
                String string = trackFormat.getString("mime");
                if (!TextUtils.isEmpty(string) && string.startsWith("video/") && trackFormat.containsKey("frame-rate")) {
                    i = trackFormat.getInteger("frame-rate");
                    break;
                }
                i2++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            mediaExtractor.release();
            throw th;
        }
        mediaExtractor.release();
        if (DEBUG_ENABLE) {
            Log.d("SpecialTypeMediaUtils", "path [%s] frameRate [%d]", str, Integer.valueOf(i));
        }
        return i;
    }

    public static int getSpecialTypeEnterIconId(long j) {
        if ((64 & j) != 0) {
            return R.drawable.burst_photo_btn_large;
        }
        if ((32 & j) != 0) {
            return R.drawable.motion_photo_btn;
        }
        if ((1 & j) != 0) {
            return R.drawable.blur_refocus_btn;
        }
        if ((4 & j) != 0 || (8 & j) != 0) {
            return R.drawable.slow_motion_btn;
        }
        if ((16 & j) != 0) {
            return R.drawable.super_slow_motion_btn;
        }
        if ((j & 16384) != 0) {
            return R.drawable.edit_subtitle_btn;
        }
        return 0;
    }

    public static int getSpecialTypeEnterTextId(long j) {
        if ((64 & j) != 0) {
            return R.string.burst_photo_enter;
        }
        if ((32 & j) != 0) {
            return R.string.motion_photo_enter;
        }
        if ((1 & j) != 0) {
            return R.string.blur_refocus_enter;
        }
        if ((4 & j) != 0 || (8 & j) != 0) {
            return R.string.slow_motion_enter;
        }
        if ((16 & j) != 0) {
            return R.string.super_slow_motion_enter;
        }
        if ((j & 16384) != 0) {
            return R.string.edit_subtitle_enter;
        }
        return 0;
    }

    private static Map<String, MetaValue> getVideoKeyedMeta(String str) {
        try {
            MetaBox parseMeta = MP4Util.parseMeta(new File(str));
            if (parseMeta != null) {
                return parseMeta.getKeyedMeta();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r2 = r2.get("com.xiaomi.capture_framerate");
     */
    private static boolean is960FpsVideo(Map<String, MetaValue> map) {
        MetaValue metaValue;
        return (map == null || metaValue == null || metaValue.getInt() != 960) ? false : true;
    }

    public static boolean is960VideoEditable(String str) {
        return is960VideoEditable(getVideoKeyedMeta(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r2 = r2.get("com.xiaomi.capture_origin_track");
     */
    private static boolean is960VideoEditable(Map<String, MetaValue> map) {
        MetaValue metaValue;
        return (map == null || metaValue == null || metaValue.getInt() < 0) ? false : true;
    }

    public static boolean isBurstPhoto(long j) {
        return (j & 64) != 0;
    }

    public static boolean isMTSpecialAITypeSupport() {
        return Build.DEVICE.equalsIgnoreCase("vela");
    }

    public static boolean isMotionPhoto(Context context, long j) {
        return (j & 32) != 0 && ExtraPhotoSDK.isDeviceSupportMotionPhoto(context);
    }

    public static boolean isRefocusSupported(Context context, long j) {
        return (j & 1) != 0 && ExtraPhotoSDK.isDeviceSupportRefocus(context);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r2 = r2.get("com.xiaomi.support_subtitle");
     */
    private static boolean isVideoSupportSubtitle(Map<String, MetaValue> map) {
        MetaValue metaValue;
        return (map == null || metaValue == null || metaValue.getInt() != 1) ? false : true;
    }

    public static long parseFlagsForImage(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                if (FileMimeUtil.hasExif(str)) {
                    ExifInterface exifInterface = new ExifInterface();
                    exifInterface.readExif(str);
                    if (ExifUtil.supportRefocus(exifInterface)) {
                        j = 1;
                    } else if (ExifUtil.isMotionPhoto(exifInterface)) {
                        j = 32;
                    }
                    int mTSpecialAITypeValue = ExifUtil.getMTSpecialAITypeValue(exifInterface);
                    if (mTSpecialAITypeValue > 0) {
                        j |= parseMTSpecialAITypeByValue(mTSpecialAITypeValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (DEBUG_ENABLE) {
            Log.d("SpecialTypeMediaUtils", "parseFlagsForImage costs [%dms], path [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str);
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0067  */
    public static long parseFlagsForVideo(String str) {
        long j;
        Map<String, MetaValue> videoKeyedMeta;
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(str)) {
            ScannerStrategy.SpecialTypeMediaStrategy specialTypeMediaStrategy = (ScannerStrategy.SpecialTypeMediaStrategy) SPECIAL_TYPE_MEDIA_STRATEGY.get();
            long extractFrameRate = (long) extractFrameRate(str);
            if (extractFrameRate >= specialTypeMediaStrategy.getHfr120FpsLowerBound() && extractFrameRate <= specialTypeMediaStrategy.getHfr120FpsUpperBound()) {
                j = 4;
                if (DEBUG_ENABLE) {
                }
                return j;
            } else if (extractFrameRate >= specialTypeMediaStrategy.getHfr240FpsLowerBound() && extractFrameRate <= specialTypeMediaStrategy.getHfr240FpsUpperBound()) {
                j = 8;
                if (DEBUG_ENABLE) {
                }
                return j;
            } else if ("video/mp4".equals(FileMimeUtil.getMimeTypeByParseFile(str)) && (videoKeyedMeta = getVideoKeyedMeta(str)) != null) {
                if (is960FpsVideo(videoKeyedMeta)) {
                    j = 16;
                } else if (isVideoSupportSubtitle(videoKeyedMeta)) {
                    j = 16384;
                }
                if (DEBUG_ENABLE) {
                    Log.d("SpecialTypeMediaUtils", "parseFlagsForVideo costs [%dms], flags [%d], path [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Long.valueOf(j), str);
                }
                return j;
            }
        }
        j = 0;
        if (DEBUG_ENABLE) {
        }
        return j;
    }

    public static long parseMTSpecialAITypeByValue(int i) {
        if (i == 5) {
            return 128;
        }
        if (i == 9) {
            return 256;
        }
        if (i == 10) {
            return 512;
        }
        switch (i) {
            case 13:
                return 1024;
            case 14:
                return 2048;
            case 15:
                return 4096;
            default:
                return 0;
        }
    }

    public static int parseSpecialTypeDescriptionRes(long j) {
        if (j == 0) {
            return 0;
        }
        if ((8192 & j) != 0) {
            return R.string.special_type_raw;
        }
        if (isMTSpecialAITypeSupport()) {
            if ((128 & j) != 0) {
                return R.string.special_type_hdr;
            }
            if ((256 & j) != 0) {
                return R.string.special_type_night_beauty;
            }
            if ((512 & j) != 0) {
                return R.string.special_type_night_mode;
            }
            if ((1024 & j) != 0) {
                return R.string.special_type_portrait;
            }
            if ((2048 & j) != 0) {
                return R.string.special_type_eye_repair;
            }
            if ((j & 4096) != 0) {
                return R.string.special_type_wide_angle;
            }
        }
        return 0;
    }

    public static int tryGetHFRIndicatorResId(long j) {
        if ((4 & j) != 0 || (8 & j) != 0) {
            return R.drawable.type_indicator_hfr;
        }
        if ((j & 16) != 0) {
            return R.drawable.type_indicator_extra_hfr;
        }
        return 0;
    }
}
