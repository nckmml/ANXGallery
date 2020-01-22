package com.miui.gallery3d.exif;

import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.documentfile.provider.DocumentFile;
import com.miui.gallery.stat.BaseSamplingStatHelper;
import com.miui.gallery.util.BaseDocumentProviderUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.StaticContext;
import com.nexstreaming.nexeditorsdk.nexEngine;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ExifInterface {
    public static final ByteOrder DEFAULT_BYTE_ORDER = ByteOrder.BIG_ENDIAN;
    private static final String TAG = "com.miui.gallery3d.exif.ExifInterface";
    public static final int TAG_APERTURE_VALUE = defineTag(2, -28158);
    public static final int TAG_ARTIST = defineTag(0, 315);
    public static final int TAG_BITS_PER_SAMPLE = defineTag(0, 258);
    public static final int TAG_BRIGHTNESS_VALUE = defineTag(2, -28157);
    public static final int TAG_CFA_PATTERN = defineTag(2, -23806);
    public static final int TAG_COLOR_SPACE = defineTag(2, -24575);
    public static final int TAG_COMPONENTS_CONFIGURATION = defineTag(2, -28415);
    public static final int TAG_COMPRESSED_BITS_PER_PIXEL = defineTag(2, -28414);
    public static final int TAG_COMPRESSION = defineTag(0, 259);
    public static final int TAG_CONTRAST = defineTag(2, -23544);
    public static final int TAG_COPYRIGHT = defineTag(0, -32104);
    public static final int TAG_CUSTOM_RENDERED = defineTag(2, -23551);
    public static final int TAG_DATE_TIME = defineTag(0, 306);
    public static final int TAG_DATE_TIME_DIGITIZED = defineTag(2, -28668);
    public static final int TAG_DATE_TIME_ORIGINAL = defineTag(2, -28669);
    public static final int TAG_DEVICE_SETTING_DESCRIPTION = defineTag(2, -23541);
    public static final int TAG_DIGITAL_ZOOM_RATIO = defineTag(2, -23548);
    public static final int TAG_EXIF_IFD = defineTag(0, -30871);
    public static final int TAG_EXIF_VERSION = defineTag(2, -28672);
    public static final int TAG_EXPOSURE_BIAS_VALUE = defineTag(2, -28156);
    public static final int TAG_EXPOSURE_INDEX = defineTag(2, -24043);
    public static final int TAG_EXPOSURE_MODE = defineTag(2, -23550);
    public static final int TAG_EXPOSURE_PROGRAM = defineTag(2, -30686);
    public static final int TAG_EXPOSURE_TIME = defineTag(2, -32102);
    public static final int TAG_FILE_SOURCE = defineTag(2, -23808);
    public static final int TAG_FLASH = defineTag(2, -28151);
    public static final int TAG_FLASHPIX_VERSION = defineTag(2, -24576);
    public static final int TAG_FLASH_ENERGY = defineTag(2, -24053);
    public static final int TAG_FOCAL_LENGTH = defineTag(2, -28150);
    public static final int TAG_FOCAL_LENGTH_IN_35_MM_FILE = defineTag(2, -23547);
    public static final int TAG_FOCAL_PLANE_RESOLUTION_UNIT = defineTag(2, -24048);
    public static final int TAG_FOCAL_PLANE_X_RESOLUTION = defineTag(2, -24050);
    public static final int TAG_FOCAL_PLANE_Y_RESOLUTION = defineTag(2, -24049);
    public static final int TAG_F_NUMBER = defineTag(2, -32099);
    public static final int TAG_GAIN_CONTROL = defineTag(2, -23545);
    public static final int TAG_GPS_ALTITUDE = defineTag(4, 6);
    public static final int TAG_GPS_ALTITUDE_REF = defineTag(4, 5);
    public static final int TAG_GPS_AREA_INFORMATION = defineTag(4, 28);
    public static final int TAG_GPS_DATE_STAMP = defineTag(4, 29);
    public static final int TAG_GPS_DEST_BEARING = defineTag(4, 24);
    public static final int TAG_GPS_DEST_BEARING_REF = defineTag(4, 23);
    public static final int TAG_GPS_DEST_DISTANCE = defineTag(4, 26);
    public static final int TAG_GPS_DEST_DISTANCE_REF = defineTag(4, 25);
    public static final int TAG_GPS_DEST_LATITUDE = defineTag(4, 20);
    public static final int TAG_GPS_DEST_LATITUDE_REF = defineTag(4, 19);
    public static final int TAG_GPS_DEST_LONGITUDE = defineTag(4, 22);
    public static final int TAG_GPS_DEST_LONGITUDE_REF = defineTag(4, 21);
    public static final int TAG_GPS_DIFFERENTIAL = defineTag(4, 30);
    public static final int TAG_GPS_DOP = defineTag(4, 11);
    public static final int TAG_GPS_IFD = defineTag(0, -30683);
    public static final int TAG_GPS_IMG_DIRECTION = defineTag(4, 17);
    public static final int TAG_GPS_IMG_DIRECTION_REF = defineTag(4, 16);
    public static final int TAG_GPS_LATITUDE = defineTag(4, 2);
    public static final int TAG_GPS_LATITUDE_REF = defineTag(4, 1);
    public static final int TAG_GPS_LONGITUDE = defineTag(4, 4);
    public static final int TAG_GPS_LONGITUDE_REF = defineTag(4, 3);
    public static final int TAG_GPS_MAP_DATUM = defineTag(4, 18);
    public static final int TAG_GPS_MEASURE_MODE = defineTag(4, 10);
    public static final int TAG_GPS_PROCESSING_METHOD = defineTag(4, 27);
    public static final int TAG_GPS_SATTELLITES = defineTag(4, 8);
    public static final int TAG_GPS_SPEED = defineTag(4, 13);
    public static final int TAG_GPS_SPEED_REF = defineTag(4, 12);
    public static final int TAG_GPS_STATUS = defineTag(4, 9);
    public static final int TAG_GPS_TIME_STAMP = defineTag(4, 7);
    public static final int TAG_GPS_TRACK = defineTag(4, 15);
    public static final int TAG_GPS_TRACK_REF = defineTag(4, 14);
    public static final int TAG_GPS_VERSION_ID = defineTag(4, 0);
    public static final int TAG_IMAGE_DESCRIPTION = defineTag(0, 270);
    public static final int TAG_IMAGE_LENGTH = defineTag(0, 257);
    public static final int TAG_IMAGE_UNIQUE_ID = defineTag(2, -23520);
    public static final int TAG_IMAGE_WIDTH = defineTag(0, 256);
    public static final int TAG_INTEROPERABILITY_IFD = defineTag(2, -24571);
    public static final int TAG_INTEROPERABILITY_INDEX = defineTag(3, 1);
    public static final int TAG_ISO_SPEED_RATINGS = defineTag(2, -30681);
    public static final int TAG_JPEG_INTERCHANGE_FORMAT = defineTag(1, 513);
    public static final int TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = defineTag(1, 514);
    public static final int TAG_LIGHT_SOURCE = defineTag(2, -28152);
    public static final int TAG_MAKE = defineTag(0, 271);
    public static final int TAG_MAKER_NOTE = defineTag(2, -28036);
    public static final int TAG_MAX_APERTURE_VALUE = defineTag(2, -28155);
    public static final int TAG_METERING_MODE = defineTag(2, -28153);
    public static final int TAG_MODEL = defineTag(0, 272);
    public static final int TAG_OECF = defineTag(2, -30680);
    public static final int TAG_ORIENTATION = defineTag(0, 274);
    public static final int TAG_PARALLEL_PROCESS_COMMENT = defineTag(2, -30584);
    public static final int TAG_PHOTOMETRIC_INTERPRETATION = defineTag(0, 262);
    public static final int TAG_PIXEL_X_DIMENSION = defineTag(2, -24574);
    public static final int TAG_PIXEL_Y_DIMENSION = defineTag(2, -24573);
    public static final int TAG_PLANAR_CONFIGURATION = defineTag(0, 284);
    public static final int TAG_PRIMARY_CHROMATICITIES = defineTag(0, 319);
    public static final int TAG_REFERENCE_BLACK_WHITE = defineTag(0, 532);
    public static final int TAG_RELATED_SOUND_FILE = defineTag(2, -24572);
    public static final int TAG_RESOLUTION_UNIT = defineTag(0, 296);
    public static final int TAG_ROWS_PER_STRIP = defineTag(0, 278);
    public static final int TAG_SAMPLES_PER_PIXEL = defineTag(0, 277);
    public static final int TAG_SATURATION = defineTag(2, -23543);
    public static final int TAG_SCENE_CAPTURE_TYPE = defineTag(2, -23546);
    public static final int TAG_SCENE_TYPE = defineTag(2, -23807);
    public static final int TAG_SENSING_METHOD = defineTag(2, -24041);
    public static final int TAG_SHARPNESS = defineTag(2, -23542);
    public static final int TAG_SHUTTER_SPEED_VALUE = defineTag(2, -28159);
    public static final int TAG_SOFTWARE = defineTag(0, 305);
    public static final int TAG_SPATIAL_FREQUENCY_RESPONSE = defineTag(2, -24052);
    public static final int TAG_SPECTRAL_SENSITIVITY = defineTag(2, -30684);
    public static final int TAG_STRIP_BYTE_COUNTS = defineTag(0, 279);
    public static final int TAG_STRIP_OFFSETS = defineTag(0, 273);
    public static final int TAG_SUBJECT_AREA = defineTag(2, -28140);
    public static final int TAG_SUBJECT_DISTANCE = defineTag(2, -28154);
    public static final int TAG_SUBJECT_DISTANCE_RANGE = defineTag(2, -23540);
    public static final int TAG_SUBJECT_LOCATION = defineTag(2, -24044);
    public static final int TAG_SUB_SEC_TIME = defineTag(2, -28016);
    public static final int TAG_SUB_SEC_TIME_DIGITIZED = defineTag(2, -28014);
    public static final int TAG_SUB_SEC_TIME_ORIGINAL = defineTag(2, -28015);
    public static final int TAG_TRANSFER_FUNCTION = defineTag(0, 301);
    public static final int TAG_USER_COMMENT = defineTag(2, -28026);
    public static final int TAG_WHITE_BALANCE = defineTag(2, -23549);
    public static final int TAG_WHITE_POINT = defineTag(0, 318);
    public static final int TAG_XIAOMI_COMMENT = defineTag(2, -26215);
    public static final int TAG_X_RESOLUTION = defineTag(0, 282);
    public static final int TAG_Y_CB_CR_COEFFICIENTS = defineTag(0, 529);
    public static final int TAG_Y_CB_CR_POSITIONING = defineTag(0, 531);
    public static final int TAG_Y_CB_CR_SUB_SAMPLING = defineTag(0, 530);
    public static final int TAG_Y_RESOLUTION = defineTag(0, 283);
    protected static HashSet<Short> sBannedDefines = new HashSet<>(sOffsetTags);
    private static HashSet<Short> sOffsetTags = new HashSet<>();
    private ExifData mData = new ExifData(DEFAULT_BYTE_ORDER);
    private final DateFormat mDateTimeStampFormat = new SimpleDateFormat("yyyy:MM:dd kk:mm:ss");
    private final DateFormat mGPSDateStampFormat = new SimpleDateFormat("yyyy:MM:dd");
    private final Calendar mGPSTimeStampCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    private SparseIntArray mTagInfo = null;

    static {
        sOffsetTags.add(Short.valueOf(getTrueTagKey(TAG_GPS_IFD)));
        sOffsetTags.add(Short.valueOf(getTrueTagKey(TAG_EXIF_IFD)));
        sOffsetTags.add(Short.valueOf(getTrueTagKey(TAG_JPEG_INTERCHANGE_FORMAT)));
        sOffsetTags.add(Short.valueOf(getTrueTagKey(TAG_INTEROPERABILITY_IFD)));
        sOffsetTags.add(Short.valueOf(getTrueTagKey(TAG_STRIP_OFFSETS)));
        sBannedDefines.add(Short.valueOf(getTrueTagKey(-1)));
        sBannedDefines.add(Short.valueOf(getTrueTagKey(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH)));
        sBannedDefines.add(Short.valueOf(getTrueTagKey(TAG_STRIP_BYTE_COUNTS)));
    }

    public ExifInterface() {
        this.mGPSDateStampFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private ExifTag buildTagIgnoreIfdAllowed(int i, int i2, Object obj) {
        int i3 = getTagInfo().get(i);
        if (i3 == 0 || obj == null) {
            return null;
        }
        short typeFromInfo = getTypeFromInfo(i3);
        int componentCountFromInfo = getComponentCountFromInfo(i3);
        ExifTag exifTag = new ExifTag(getTrueTagKey(i), typeFromInfo, componentCountFromInfo, i2, componentCountFromInfo != 0);
        if (!exifTag.setValue(obj)) {
            return null;
        }
        return exifTag;
    }

    protected static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static int defineTag(int i, short s) {
        return (i << 16) | (s & 65535);
    }

    private void doExifStreamIO(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        int read = inputStream.read(bArr, 0, 1024);
        while (read != -1) {
            outputStream.write(bArr, 0, read);
            read = inputStream.read(bArr, 0, 1024);
        }
    }

    protected static int getAllowedIfdFlagsFromInfo(int i) {
        return i >>> 24;
    }

    protected static int getComponentCountFromInfo(int i) {
        return i & 65535;
    }

    protected static int getFlagsFromAllowedIfds(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return 0;
        }
        int[] ifds = IfdData.getIfds();
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int length = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (ifds[i2] == iArr[i3]) {
                    i |= 1 << i2;
                    break;
                }
                i3++;
            }
        }
        return i;
    }

    public static short getOrientationValueForRotation(int i) {
        int i2 = i % 360;
        if (i2 < 0) {
            i2 += 360;
        }
        if (i2 < 90) {
            return 1;
        }
        if (i2 < 180) {
            return 6;
        }
        return i2 < 270 ? (short) 3 : 8;
    }

    public static int getTrueIfd(int i) {
        return i >>> 16;
    }

    public static short getTrueTagKey(int i) {
        return (short) i;
    }

    protected static short getTypeFromInfo(int i) {
        return (short) ((i >> 16) & 255);
    }

    private void initTagInfo() {
        int flagsFromAllowedIfds = getFlagsFromAllowedIfds(new int[]{0, 1}) << 24;
        SparseIntArray sparseIntArray = this.mTagInfo;
        int i = TAG_MAKE;
        int i2 = flagsFromAllowedIfds | nexEngine.ExportHEVCHighTierLevel51;
        int i3 = i2 | 0;
        sparseIntArray.put(i, i3);
        SparseIntArray sparseIntArray2 = this.mTagInfo;
        int i4 = TAG_IMAGE_WIDTH;
        int i5 = flagsFromAllowedIfds | nexEngine.ExportHEVCMainTierLevel52;
        int i6 = i5 | 1;
        sparseIntArray2.put(i4, i6);
        this.mTagInfo.put(TAG_IMAGE_LENGTH, i6);
        int i7 = flagsFromAllowedIfds | 196608;
        this.mTagInfo.put(TAG_BITS_PER_SAMPLE, i7 | 3);
        int i8 = i7 | 1;
        this.mTagInfo.put(TAG_COMPRESSION, i8);
        this.mTagInfo.put(TAG_PHOTOMETRIC_INTERPRETATION, i8);
        this.mTagInfo.put(TAG_ORIENTATION, i8);
        this.mTagInfo.put(TAG_SAMPLES_PER_PIXEL, i8);
        this.mTagInfo.put(TAG_PLANAR_CONFIGURATION, i8);
        this.mTagInfo.put(TAG_Y_CB_CR_SUB_SAMPLING, i7 | 2);
        this.mTagInfo.put(TAG_Y_CB_CR_POSITIONING, i8);
        int i9 = flagsFromAllowedIfds | 327680;
        int i10 = i9 | 1;
        this.mTagInfo.put(TAG_X_RESOLUTION, i10);
        this.mTagInfo.put(TAG_Y_RESOLUTION, i10);
        this.mTagInfo.put(TAG_RESOLUTION_UNIT, i8);
        int i11 = i5 | 0;
        this.mTagInfo.put(TAG_STRIP_OFFSETS, i11);
        this.mTagInfo.put(TAG_ROWS_PER_STRIP, i6);
        this.mTagInfo.put(TAG_STRIP_BYTE_COUNTS, i11);
        this.mTagInfo.put(TAG_TRANSFER_FUNCTION, i7 | 768);
        this.mTagInfo.put(TAG_WHITE_POINT, i9 | 2);
        int i12 = i9 | 6;
        this.mTagInfo.put(TAG_PRIMARY_CHROMATICITIES, i12);
        this.mTagInfo.put(TAG_Y_CB_CR_COEFFICIENTS, i9 | 3);
        this.mTagInfo.put(TAG_REFERENCE_BLACK_WHITE, i12);
        this.mTagInfo.put(TAG_DATE_TIME, i2 | 20);
        this.mTagInfo.put(TAG_IMAGE_DESCRIPTION, i3);
        this.mTagInfo.put(TAG_MAKE, i3);
        this.mTagInfo.put(TAG_MODEL, i3);
        this.mTagInfo.put(TAG_SOFTWARE, i3);
        this.mTagInfo.put(TAG_ARTIST, i3);
        this.mTagInfo.put(TAG_COPYRIGHT, i3);
        this.mTagInfo.put(TAG_EXIF_IFD, i6);
        this.mTagInfo.put(TAG_GPS_IFD, i6);
        SparseIntArray sparseIntArray3 = this.mTagInfo;
        int i13 = TAG_JPEG_INTERCHANGE_FORMAT;
        int flagsFromAllowedIfds2 = (getFlagsFromAllowedIfds(new int[]{1}) << 24) | nexEngine.ExportHEVCMainTierLevel52 | 1;
        sparseIntArray3.put(i13, flagsFromAllowedIfds2);
        this.mTagInfo.put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, flagsFromAllowedIfds2);
        int flagsFromAllowedIfds3 = getFlagsFromAllowedIfds(new int[]{2}) << 24;
        int i14 = flagsFromAllowedIfds3 | 458752;
        int i15 = i14 | 4;
        this.mTagInfo.put(TAG_EXIF_VERSION, i15);
        this.mTagInfo.put(TAG_FLASHPIX_VERSION, i15);
        int i16 = flagsFromAllowedIfds3 | 196608;
        int i17 = i16 | 1;
        this.mTagInfo.put(TAG_COLOR_SPACE, i17);
        this.mTagInfo.put(TAG_COMPONENTS_CONFIGURATION, i15);
        int i18 = flagsFromAllowedIfds3 | 327680 | 1;
        this.mTagInfo.put(TAG_COMPRESSED_BITS_PER_PIXEL, i18);
        int i19 = 262144 | flagsFromAllowedIfds3 | 1;
        this.mTagInfo.put(TAG_PIXEL_X_DIMENSION, i19);
        this.mTagInfo.put(TAG_PIXEL_Y_DIMENSION, i19);
        int i20 = i14 | 0;
        this.mTagInfo.put(TAG_MAKER_NOTE, i20);
        this.mTagInfo.put(TAG_USER_COMMENT, i20);
        SparseIntArray sparseIntArray4 = this.mTagInfo;
        int i21 = TAG_RELATED_SOUND_FILE;
        int i22 = flagsFromAllowedIfds3 | nexEngine.ExportHEVCHighTierLevel51;
        sparseIntArray4.put(i21, i22 | 13);
        int i23 = i22 | 20;
        this.mTagInfo.put(TAG_DATE_TIME_ORIGINAL, i23);
        this.mTagInfo.put(TAG_DATE_TIME_DIGITIZED, i23);
        int i24 = i22 | 0;
        this.mTagInfo.put(TAG_SUB_SEC_TIME, i24);
        this.mTagInfo.put(TAG_SUB_SEC_TIME_ORIGINAL, i24);
        this.mTagInfo.put(TAG_SUB_SEC_TIME_DIGITIZED, i24);
        this.mTagInfo.put(TAG_IMAGE_UNIQUE_ID, i22 | 33);
        this.mTagInfo.put(TAG_EXPOSURE_TIME, i18);
        this.mTagInfo.put(TAG_F_NUMBER, i18);
        this.mTagInfo.put(TAG_EXPOSURE_PROGRAM, i17);
        this.mTagInfo.put(TAG_SPECTRAL_SENSITIVITY, i24);
        int i25 = i16 | 0;
        this.mTagInfo.put(TAG_ISO_SPEED_RATINGS, i25);
        this.mTagInfo.put(TAG_OECF, i20);
        int i26 = flagsFromAllowedIfds3 | 655360 | 1;
        this.mTagInfo.put(TAG_SHUTTER_SPEED_VALUE, i26);
        this.mTagInfo.put(TAG_APERTURE_VALUE, i18);
        this.mTagInfo.put(TAG_BRIGHTNESS_VALUE, i26);
        this.mTagInfo.put(TAG_EXPOSURE_BIAS_VALUE, i26);
        this.mTagInfo.put(TAG_MAX_APERTURE_VALUE, i18);
        this.mTagInfo.put(TAG_SUBJECT_DISTANCE, i18);
        this.mTagInfo.put(TAG_METERING_MODE, i17);
        this.mTagInfo.put(TAG_LIGHT_SOURCE, i17);
        this.mTagInfo.put(TAG_FLASH, i17);
        this.mTagInfo.put(TAG_FOCAL_LENGTH, i18);
        this.mTagInfo.put(TAG_SUBJECT_AREA, i25);
        this.mTagInfo.put(TAG_FLASH_ENERGY, i18);
        this.mTagInfo.put(TAG_SPATIAL_FREQUENCY_RESPONSE, i20);
        this.mTagInfo.put(TAG_FOCAL_PLANE_X_RESOLUTION, i18);
        this.mTagInfo.put(TAG_FOCAL_PLANE_Y_RESOLUTION, i18);
        this.mTagInfo.put(TAG_FOCAL_PLANE_RESOLUTION_UNIT, i17);
        this.mTagInfo.put(TAG_SUBJECT_LOCATION, 2 | i16);
        this.mTagInfo.put(TAG_EXPOSURE_INDEX, i18);
        this.mTagInfo.put(TAG_SENSING_METHOD, i17);
        int i27 = i14 | 1;
        this.mTagInfo.put(TAG_FILE_SOURCE, i27);
        this.mTagInfo.put(TAG_SCENE_TYPE, i27);
        this.mTagInfo.put(TAG_CFA_PATTERN, i20);
        this.mTagInfo.put(TAG_CUSTOM_RENDERED, i17);
        this.mTagInfo.put(TAG_EXPOSURE_MODE, i17);
        this.mTagInfo.put(TAG_WHITE_BALANCE, i17);
        this.mTagInfo.put(TAG_DIGITAL_ZOOM_RATIO, i18);
        this.mTagInfo.put(TAG_FOCAL_LENGTH_IN_35_MM_FILE, i17);
        this.mTagInfo.put(TAG_SCENE_CAPTURE_TYPE, i17);
        this.mTagInfo.put(TAG_GAIN_CONTROL, i18);
        this.mTagInfo.put(TAG_CONTRAST, i17);
        this.mTagInfo.put(TAG_SATURATION, i17);
        this.mTagInfo.put(TAG_SHARPNESS, i17);
        this.mTagInfo.put(TAG_DEVICE_SETTING_DESCRIPTION, i20);
        this.mTagInfo.put(TAG_SUBJECT_DISTANCE_RANGE, i17);
        this.mTagInfo.put(TAG_INTEROPERABILITY_IFD, i19);
        this.mTagInfo.put(TAG_PARALLEL_PROCESS_COMMENT, i24);
        this.mTagInfo.put(TAG_XIAOMI_COMMENT, i24);
        int flagsFromAllowedIfds4 = getFlagsFromAllowedIfds(new int[]{4}) << 24;
        int i28 = 65536 | flagsFromAllowedIfds4;
        this.mTagInfo.put(TAG_GPS_VERSION_ID, i28 | 4);
        SparseIntArray sparseIntArray5 = this.mTagInfo;
        int i29 = TAG_GPS_LATITUDE_REF;
        int i30 = flagsFromAllowedIfds4 | nexEngine.ExportHEVCHighTierLevel51;
        int i31 = i30 | 2;
        sparseIntArray5.put(i29, i31);
        this.mTagInfo.put(TAG_GPS_LONGITUDE_REF, i31);
        int i32 = flagsFromAllowedIfds4 | 655360 | 3;
        this.mTagInfo.put(TAG_GPS_LATITUDE, i32);
        this.mTagInfo.put(TAG_GPS_LONGITUDE, i32);
        this.mTagInfo.put(TAG_GPS_ALTITUDE_REF, i28 | 1);
        int i33 = 327680 | flagsFromAllowedIfds4;
        int i34 = i33 | 1;
        this.mTagInfo.put(TAG_GPS_ALTITUDE, i34);
        this.mTagInfo.put(TAG_GPS_TIME_STAMP, i33 | 3);
        int i35 = i30 | 0;
        this.mTagInfo.put(TAG_GPS_SATTELLITES, i35);
        this.mTagInfo.put(TAG_GPS_STATUS, i31);
        this.mTagInfo.put(TAG_GPS_MEASURE_MODE, i31);
        this.mTagInfo.put(TAG_GPS_DOP, i34);
        this.mTagInfo.put(TAG_GPS_SPEED_REF, i31);
        this.mTagInfo.put(TAG_GPS_SPEED, i34);
        this.mTagInfo.put(TAG_GPS_TRACK_REF, i31);
        this.mTagInfo.put(TAG_GPS_TRACK, i34);
        this.mTagInfo.put(TAG_GPS_IMG_DIRECTION_REF, i31);
        this.mTagInfo.put(TAG_GPS_IMG_DIRECTION, i34);
        this.mTagInfo.put(TAG_GPS_MAP_DATUM, i35);
        this.mTagInfo.put(TAG_GPS_DEST_LATITUDE_REF, i31);
        this.mTagInfo.put(TAG_GPS_DEST_LATITUDE, i34);
        this.mTagInfo.put(TAG_GPS_DEST_BEARING_REF, i31);
        this.mTagInfo.put(TAG_GPS_DEST_BEARING, i34);
        this.mTagInfo.put(TAG_GPS_DEST_DISTANCE_REF, i31);
        this.mTagInfo.put(TAG_GPS_DEST_DISTANCE, i34);
        int i36 = 458752 | flagsFromAllowedIfds4 | 0;
        this.mTagInfo.put(TAG_GPS_PROCESSING_METHOD, i36);
        this.mTagInfo.put(TAG_GPS_AREA_INFORMATION, i36);
        this.mTagInfo.put(TAG_GPS_DATE_STAMP, i30 | 11);
        this.mTagInfo.put(TAG_GPS_DIFFERENTIAL, flagsFromAllowedIfds4 | 196608 | 11);
        this.mTagInfo.put(TAG_INTEROPERABILITY_INDEX, (getFlagsFromAllowedIfds(new int[]{3}) << 24) | nexEngine.ExportHEVCHighTierLevel51 | 0);
    }

    protected static boolean isIfdAllowed(int i, int i2) {
        int[] ifds = IfdData.getIfds();
        int allowedIfdFlagsFromInfo = getAllowedIfdFlagsFromInfo(i);
        for (int i3 = 0; i3 < ifds.length; i3++) {
            if (i2 == ifds[i3] && ((allowedIfdFlagsFromInfo >> i3) & 1) == 1) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isOffsetTag(short s) {
        return sOffsetTags.contains(Short.valueOf(s));
    }

    public boolean addDateTimeStampTag(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (i != TAG_DATE_TIME && i != TAG_DATE_TIME_DIGITIZED && i != TAG_DATE_TIME_ORIGINAL) {
            return false;
        }
        if (!str.endsWith("\u0000")) {
            str = str + 0;
        }
        ExifTag buildTag = buildTag(i, str);
        if (buildTag == null) {
            return false;
        }
        setTag(buildTag);
        return true;
    }

    public boolean addGpsDateTimeStampTag(String str, String str2) {
        ExifTag buildTag;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            int i = TAG_GPS_DATE_STAMP;
            ExifTag buildTag2 = buildTag(i, str + 0);
            if (buildTag2 == null) {
                return false;
            }
            setTag(buildTag2);
            String[] split = str2.split(":");
            if (split == null || split.length != 3 || (buildTag = buildTag(TAG_GPS_TIME_STAMP, new Rational[]{new Rational((long) Integer.parseInt(split[0]), 1), new Rational((long) Integer.parseInt(split[1]), 1), new Rational((long) Integer.parseInt(split[2]), 1)})) == null) {
                return false;
            }
            setTag(buildTag);
            return true;
        }
        return false;
    }

    public boolean addSubSecTimeStampTag(long j) {
        ExifTag buildTag;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = instance.get(14);
        if (i <= 0 || (buildTag = buildTag(TAG_SUB_SEC_TIME, String.valueOf(i))) == null) {
            return false;
        }
        setTag(buildTag);
        return true;
    }

    public boolean addUserComment(String str) {
        ExifTag buildTagIgnoreIfdAllowed = buildTagIgnoreIfdAllowed(TAG_USER_COMMENT, 0, str);
        if (buildTagIgnoreIfdAllowed == null) {
            return false;
        }
        setTag(buildTagIgnoreIfdAllowed);
        return true;
    }

    public ExifTag buildTag(int i, int i2, Object obj) {
        int i3 = getTagInfo().get(i);
        if (i3 == 0 || obj == null) {
            return null;
        }
        short typeFromInfo = getTypeFromInfo(i3);
        int componentCountFromInfo = getComponentCountFromInfo(i3);
        boolean z = componentCountFromInfo != 0;
        if (!isIfdAllowed(i3, i2)) {
            return null;
        }
        ExifTag exifTag = new ExifTag(getTrueTagKey(i), typeFromInfo, componentCountFromInfo, i2, z);
        if (!exifTag.setValue(obj)) {
            return null;
        }
        return exifTag;
    }

    public ExifTag buildTag(int i, Object obj) {
        return buildTag(i, getTrueIfd(i), obj);
    }

    /* access modifiers changed from: protected */
    public ExifTag buildUninitializedTag(int i) {
        int i2 = getTagInfo().get(i);
        if (i2 == 0) {
            return null;
        }
        short typeFromInfo = getTypeFromInfo(i2);
        int componentCountFromInfo = getComponentCountFromInfo(i2);
        boolean z = componentCountFromInfo != 0;
        return new ExifTag(getTrueTagKey(i), typeFromInfo, componentCountFromInfo, getTrueIfd(i), z);
    }

    public void forceRewriteExif(String str) throws FileNotFoundException, IOException {
        forceRewriteExif(str, getAllTags());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v16, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v15, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v22, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v29, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v30, resolved type: java.io.FileOutputStream} */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    public void forceRewriteExif(String str, Collection<ExifTag> collection) throws FileNotFoundException, IOException {
        boolean z;
        ParcelFileDescriptor parcelFileDescriptor;
        ExifOutputStream exifOutputStream;
        FileInputStream fileInputStream;
        ExifOutputStream exifOutputStream2;
        FileOutputStream fileOutputStream;
        ParcelFileDescriptor parcelFileDescriptor2 = null;
        try {
            z = rewriteExif(str, collection);
        } catch (Exception unused) {
            z = false;
        }
        if (!z) {
            ExifData exifData = this.mData;
            this.mData = new ExifData(DEFAULT_BYTE_ORDER);
            FileInputStream fileInputStream2 = 0;
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    doExifStreamIO(fileInputStream, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    readExif(byteArray);
                    setTags(collection);
                    if (byteArray == null || str == null) {
                        throw new IllegalArgumentException("Argument is null");
                    }
                    if (BaseDocumentProviderUtils.needUseDocumentProvider(str)) {
                        DocumentFile documentFile = BaseDocumentProviderUtils.getDocumentFile(StaticContext.sGetAndroidContext(), new File(str));
                        if (documentFile != null) {
                            parcelFileDescriptor = StaticContext.sGetAndroidContext().getContentResolver().openFileDescriptor(documentFile.getUri(), "w");
                            try {
                                fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
                            } catch (ExifInvalidFormatException e) {
                                e = e;
                                ParcelFileDescriptor parcelFileDescriptor3 = parcelFileDescriptor;
                            } catch (IOException e2) {
                                e = e2;
                                parcelFileDescriptor2 = parcelFileDescriptor;
                                str = null;
                                exifOutputStream2 = null;
                                fileInputStream2 = fileInputStream;
                                throw e;
                            } catch (Throwable th) {
                                th = th;
                                exifOutputStream = null;
                                closeSilently(fileInputStream2);
                                closeSilently(fileInputStream);
                                closeSilently(exifOutputStream);
                                closeSilently(parcelFileDescriptor);
                                this.mData = exifData;
                                throw th;
                            }
                        } else {
                            IOException iOException = new IOException("failed to get document file");
                            Log.e(TAG, "failed to get document file");
                            throw iOException;
                        }
                    } else {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                        parcelFileDescriptor = null;
                        fileOutputStream = fileOutputStream2;
                    }
                    try {
                        exifOutputStream = new ExifOutputStream(fileOutputStream, this);
                        exifOutputStream.setExifData(this.mData);
                        try {
                            exifOutputStream.write(byteArray, 0, byteArray.length);
                            exifOutputStream.flush();
                            closeSilently(fileOutputStream);
                            closeSilently(fileInputStream);
                            closeSilently(exifOutputStream);
                            closeSilently(parcelFileDescriptor);
                            this.mData = exifData;
                        } catch (ExifInvalidFormatException e3) {
                            e = e3;
                            FileInputStream fileInputStream3 = fileInputStream;
                            ExifInvalidFormatException exifInvalidFormatException = e;
                            ParcelFileDescriptor parcelFileDescriptor4 = parcelFileDescriptor;
                            FileOutputStream fileOutputStream3 = fileOutputStream;
                            e = exifInvalidFormatException;
                            throw new IOException("Invalid exif format : " + e);
                        } catch (IOException e4) {
                            e = e4;
                            fileInputStream2 = fileInputStream;
                            IOException iOException2 = e;
                            parcelFileDescriptor2 = parcelFileDescriptor;
                            str = fileOutputStream;
                            e = iOException2;
                            throw e;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream2 = fileOutputStream;
                            th = th;
                            closeSilently(fileInputStream2);
                            closeSilently(fileInputStream);
                            closeSilently(exifOutputStream);
                            closeSilently(parcelFileDescriptor);
                            this.mData = exifData;
                            throw th;
                        }
                    } catch (ExifInvalidFormatException e5) {
                        e = e5;
                        FileInputStream fileInputStream32 = fileInputStream;
                        ExifInvalidFormatException exifInvalidFormatException2 = e;
                        ParcelFileDescriptor parcelFileDescriptor42 = parcelFileDescriptor;
                        FileOutputStream fileOutputStream32 = fileOutputStream;
                        e = exifInvalidFormatException2;
                        throw new IOException("Invalid exif format : " + e);
                    } catch (IOException e6) {
                        e = e6;
                        exifOutputStream = null;
                        fileInputStream2 = fileInputStream;
                        IOException iOException22 = e;
                        parcelFileDescriptor2 = parcelFileDescriptor;
                        str = fileOutputStream;
                        e = iOException22;
                        throw e;
                    } catch (Throwable th3) {
                        th = th3;
                        exifOutputStream = null;
                        fileInputStream2 = fileOutputStream;
                        th = th;
                        closeSilently(fileInputStream2);
                        closeSilently(fileInputStream);
                        closeSilently(exifOutputStream);
                        closeSilently(parcelFileDescriptor);
                        this.mData = exifData;
                        throw th;
                    }
                } catch (ExifInvalidFormatException e7) {
                    e = e7;
                    FileInputStream fileInputStream4 = fileInputStream;
                    throw new IOException("Invalid exif format : " + e);
                } catch (IOException e8) {
                    e = e8;
                    str = null;
                    parcelFileDescriptor2 = null;
                    exifOutputStream2 = null;
                    fileInputStream2 = fileInputStream;
                    throw e;
                } catch (Throwable th4) {
                    th = th4;
                    parcelFileDescriptor = null;
                    exifOutputStream = null;
                    closeSilently(fileInputStream2);
                    closeSilently(fileInputStream);
                    closeSilently(exifOutputStream);
                    closeSilently(parcelFileDescriptor);
                    this.mData = exifData;
                    throw th;
                }
            } catch (ExifInvalidFormatException e9) {
                e = e9;
                throw new IOException("Invalid exif format : " + e);
            } catch (IOException e10) {
                e = e10;
                str = null;
                parcelFileDescriptor2 = null;
                exifOutputStream = null;
                throw e;
            } catch (Throwable th5) {
                th = th5;
                fileInputStream = fileInputStream2;
                fileInputStream2 = str;
                parcelFileDescriptor = parcelFileDescriptor2;
                closeSilently(fileInputStream2);
                closeSilently(fileInputStream);
                closeSilently(exifOutputStream);
                closeSilently(parcelFileDescriptor);
                this.mData = exifData;
                throw th;
            }
        }
    }

    public List<ExifTag> getAllTags() {
        return this.mData.getAllTags();
    }

    public int getDefinedTagDefaultIfd(int i) {
        if (getTagInfo().get(i) == 0) {
            return -1;
        }
        return getTrueIfd(i);
    }

    public OutputStream getExifWriterStream(OutputStream outputStream) {
        if (outputStream != null) {
            ExifOutputStream exifOutputStream = new ExifOutputStream(outputStream, this);
            exifOutputStream.setExifData(this.mData);
            return exifOutputStream;
        }
        throw new IllegalArgumentException("Argument is null");
    }

    public ExifTag getTag(int i, int i2) {
        if (!ExifTag.isValidIfd(i2)) {
            return null;
        }
        return this.mData.getTag(getTrueTagKey(i), i2);
    }

    public Byte getTagByteValue(int i, int i2) {
        byte[] tagByteValues = getTagByteValues(i, i2);
        if (tagByteValues == null || tagByteValues.length <= 0) {
            return null;
        }
        return new Byte(tagByteValues[0]);
    }

    public byte[] getTagByteValues(int i, int i2) {
        ExifTag tag = getTag(i, i2);
        if (tag == null) {
            return null;
        }
        return tag.getValueAsBytes();
    }

    /* access modifiers changed from: protected */
    public SparseIntArray getTagInfo() {
        if (this.mTagInfo == null) {
            this.mTagInfo = new SparseIntArray();
            initTagInfo();
        }
        return this.mTagInfo;
    }

    public Integer getTagIntValue(int i) {
        return getTagIntValue(i, getDefinedTagDefaultIfd(i));
    }

    public Integer getTagIntValue(int i, int i2) {
        int[] tagIntValues = getTagIntValues(i, i2);
        if (tagIntValues == null || tagIntValues.length <= 0) {
            return null;
        }
        return new Integer(tagIntValues[0]);
    }

    public int[] getTagIntValues(int i, int i2) {
        ExifTag tag = getTag(i, i2);
        if (tag == null) {
            return null;
        }
        return tag.getValueAsInts();
    }

    public String getTagStringValue(int i, int i2) {
        ExifTag tag = getTag(i, i2);
        if (tag == null) {
            return null;
        }
        return tag.getValueAsString();
    }

    public String getUserCommentAsASCII() {
        return this.mData.getUserCommentAsASCII();
    }

    public String getXiaomiComment() {
        return this.mData.getXiaomiComment();
    }

    public void readExif(InputStream inputStream) throws ExifInvalidFormatException, IOException {
        if (inputStream != null) {
            this.mData = new ExifReader(this).read(inputStream);
            return;
        }
        throw new IllegalArgumentException("Argument is null");
    }

    public void readExif(String str) throws ExifInvalidFormatException, IOException {
        if (str != null) {
            BufferedInputStream bufferedInputStream = null;
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(str));
                try {
                    readExif((InputStream) bufferedInputStream2);
                    closeSilently(bufferedInputStream2);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    closeSilently(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                closeSilently(bufferedInputStream);
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Argument is null");
        }
    }

    public void readExif(byte[] bArr) throws ExifInvalidFormatException, IOException {
        readExif((InputStream) new ByteArrayInputStream(bArr));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.io.BufferedInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: java.io.RandomAccessFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.BufferedInputStream} */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005c, code lost:
        r12 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005d, code lost:
        r11 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0001] */
    public boolean rewriteExif(String str, Collection<ExifTag> collection) throws FileNotFoundException, IOException {
        BufferedInputStream bufferedInputStream;
        RandomAccessFile randomAccessFile;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            File file = new File(str);
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                long offsetToExifEndFromSOF = (long) ExifParser.parse(bufferedInputStream, this).getOffsetToExifEndFromSOF();
                bufferedInputStream.close();
                randomAccessFile = new RandomAccessFile(file, "rw");
                if (randomAccessFile.length() >= offsetToExifEndFromSOF) {
                    boolean rewriteExif = rewriteExif((ByteBuffer) randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, offsetToExifEndFromSOF), collection);
                    closeSilently((Closeable) null);
                    randomAccessFile.close();
                    return rewriteExif;
                }
                throw new IOException("Filesize changed during operation");
            } catch (ExifInvalidFormatException e) {
                throw new IOException("Invalid exif format : ", e);
            } catch (IOException e2) {
                e = e2;
                try {
                    closeSilently(bufferedInputStream2);
                    throw e;
                } catch (Throwable th) {
                    bufferedInputStream2 = bufferedInputStream;
                    Throwable th2 = th;
                    closeSilently(bufferedInputStream2);
                    throw th2;
                }
            }
        } catch (IOException e3) {
            e = e3;
            bufferedInputStream2 = randomAccessFile;
            bufferedInputStream = null;
            closeSilently(bufferedInputStream2);
            throw e;
        } catch (Throwable th3) {
        }
    }

    public boolean rewriteExif(ByteBuffer byteBuffer, Collection<ExifTag> collection) throws IOException {
        try {
            ExifModifier exifModifier = new ExifModifier(byteBuffer, this);
            for (ExifTag modifyTag : collection) {
                exifModifier.modifyTag(modifyTag);
            }
            return exifModifier.commit();
        } catch (ExifInvalidFormatException e) {
            throw new IOException("Invalid exif format : " + e);
        }
    }

    public ExifTag setTag(ExifTag exifTag) {
        if (exifTag == null) {
            return null;
        }
        if (exifTag.getDataSize() < 65535) {
            return this.mData.addTag(exifTag);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tagId", String.format(Locale.ENGLISH, "%04X\n", new Object[]{Short.valueOf(exifTag.getTagId())}));
        hashMap.put("tagSize", String.valueOf(exifTag.getDataSize()));
        BaseSamplingStatHelper.recordErrorEvent("exif_parser", "exif_tag_overlength", hashMap);
        return null;
    }

    public void setTags(Collection<ExifTag> collection) {
        for (ExifTag tag : collection) {
            setTag(tag);
        }
    }
}
