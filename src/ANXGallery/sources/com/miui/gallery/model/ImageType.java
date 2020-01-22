package com.miui.gallery.model;

import android.os.Build;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.miui.gallery.util.MiscUtil;

public class ImageType {
    public static final ImageType HIGH_RESOLUTION = new ImageType("HIGH_RESOLUTION_AT_LEAST_24MP", 1, true, false, $$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA.INSTANCE);
    public static final ImageType HIGH_RESOLUTION_108M = new ImageType("HIGH_RESOLUTION_108MP", 0, true, true, $$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA.INSTANCE);
    private static final String[] HR_108MP_SUPPORTED_DEVICES = {"tucana", "cmi", "umi", "draco"};
    public static final ImageType NORMAL = new ImageType("NORMAL", 2, false, false, Predicates.alwaysTrue());
    private static boolean sIsHR108MPSupportedDevice;
    private boolean mIsHighResolution;
    private boolean mIsNavMapSupported;
    private String mName;
    private int mOrdinal;
    private Predicate<ImageSize> mPredicate;

    private static class ImageSize {
        private final int height;
        private final int width;

        public ImageSize(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public int getHeight() {
            return this.height;
        }

        public int getWidth() {
            return this.width;
        }
    }

    static {
        int i = 0;
        sIsHR108MPSupportedDevice = false;
        String[] strArr = HR_108MP_SUPPORTED_DEVICES;
        int length = strArr.length;
        while (true) {
            if (i >= length) {
                break;
            } else if (strArr[i].equalsIgnoreCase(Build.DEVICE)) {
                sIsHR108MPSupportedDevice = true;
                break;
            } else {
                i++;
            }
        }
    }

    private ImageType(String str, int i, boolean z, boolean z2, Predicate<ImageSize> predicate) {
        this.mOrdinal = i;
        this.mName = str;
        this.mIsHighResolution = z;
        this.mIsNavMapSupported = z2;
        this.mPredicate = predicate;
    }

    static /* synthetic */ boolean lambda$static$0(ImageSize imageSize) {
        return sIsHR108MPSupportedDevice && imageSize != null && MiscUtil.rangeIn(Math.max(imageSize.getWidth(), imageSize.getHeight()), 12000, 100) && MiscUtil.rangeIn(Math.min(imageSize.getWidth(), imageSize.getHeight()), 9000, 100);
    }

    static /* synthetic */ boolean lambda$static$1(ImageSize imageSize) {
        return imageSize != null && Math.max(imageSize.getWidth(), imageSize.getHeight()) >= 6000 && Math.min(imageSize.getWidth(), imageSize.getHeight()) >= 4000;
    }

    public static ImageType of(int i, int i2) {
        for (ImageType imageType : values()) {
            if (imageType.mPredicate.apply(new ImageSize(i, i2))) {
                return imageType;
            }
        }
        return NORMAL;
    }

    public static ImageType[] values() {
        return new ImageType[]{HIGH_RESOLUTION_108M, HIGH_RESOLUTION, NORMAL};
    }

    public boolean isHighResolution() {
        return this.mIsHighResolution;
    }

    public boolean isNavMapSupported() {
        return this.mIsNavMapSupported;
    }

    public String name() {
        return this.mName;
    }

    public final String toString() {
        return "ImageType{mOrdinal=" + this.mOrdinal + ", mName='" + this.mName + '\'' + ", mIsHighResolution=" + this.mIsHighResolution + ", mIsNavMapSupported=" + this.mIsNavMapSupported + '}';
    }
}
