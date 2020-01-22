package com.nexstreaming.app.common.util;

import android.util.Log;
import com.miui.gallery.assistant.jni.filter.BaiduSceneResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum FileType {
    JPEG((String) FileCategory.Image, (int) new String[]{"jpeg", "jpg"}, (FileCategory) new int[][]{new int[]{255, 216, 255}}),
    PNG((String) FileCategory.Image, (int) new String[]{"png"}, (FileCategory) new int[][]{new int[]{BaiduSceneResult.JEWELRY, 80, 78, 71, 13, 10, 26, 10}}),
    SVG((String) FileCategory.Image, (int) new String[]{"svg"}, (FileCategory) new int[][]{new int[]{60, 115, 118, 103}, new int[]{60, 83, 86, 71}}),
    WEBP((String) FileCategory.Image, (int) new String[]{"webp"}, (FileCategory) new int[][]{new int[]{82, 73, 70, 70, -1, -1, -1, -1, 87, 69, 66, 80}}),
    GIF((String) FileCategory.Image, (int) new String[]{"gif"}, (FileCategory) new int[][]{new int[]{71, 73, 70, 56, 55, 97}, new int[]{71, 73, 70, 56, 57, 97}}),
    M4A((String) FileCategory.Audio, (int) new String[]{"m4a"}, (FileCategory) new int[][]{new int[]{0, 0, 0, 32, 102, 116, BaiduSceneResult.STATION, 112, 77, 52, 65, 32}, new int[]{-1, -1, -1, -1, 102, 116, BaiduSceneResult.STATION, 112, 77, 52, 65, 32}}),
    M4V((String) FileCategory.Video, (int) new String[]{"m4v"}, (FileCategory) new int[][]{new int[]{0, 0, 0, 24, 102, 116, BaiduSceneResult.STATION, 112, 109, 112, 52, 50}, new int[]{-1, -1, -1, -1, 102, 116, BaiduSceneResult.STATION, 112, 109, 112, 52, 50}}),
    MP4((String) FileCategory.Video, (int) new String[]{"mp4"}, (FileCategory) new int[][]{new int[]{0, 0, 0, 20, 102, 116, BaiduSceneResult.STATION, 112, 105, 115, 111, 109}, new int[]{0, 0, 0, 24, 102, 116, BaiduSceneResult.STATION, 112, 105, 115, 111, 109}, new int[]{0, 0, 0, 24, 102, 116, BaiduSceneResult.STATION, 112, 51, 103, 112, 53}, new int[]{0, 0, 0, 28, 102, 116, BaiduSceneResult.STATION, 112, 77, 83, 78, 86, 1, 41, 0, 70, 77, 83, 78, 86, 109, 112, 52, 50}, new int[]{-1, -1, -1, -1, 102, 116, BaiduSceneResult.STATION, 112, 51, 103, 112, 53}, new int[]{-1, -1, -1, -1, 102, 116, BaiduSceneResult.STATION, 112, 77, 83, 78, 86}, new int[]{-1, -1, -1, -1, 102, 116, BaiduSceneResult.STATION, 112, 105, 115, 111, 109}, new int[]{0, 0, 0, 24, 102, 116, BaiduSceneResult.STATION, 112, 109, 112, 52, 49}}),
    F_3GP((String) FileCategory.VideoOrAudio, (int) new String[]{"3gp", "3gpp", "3g2"}, (FileCategory) new int[][]{new int[]{0, 0, 0, -1, 102, 116, BaiduSceneResult.STATION, 112, 51, 103, 112}, new int[]{0, 0, 0, -1, 102, 116, BaiduSceneResult.STATION, 112, 51, 103, 50}}),
    K3G(FileCategory.Video, new String[]{"k3g"}),
    ACC(FileCategory.Video, new String[]{"acc"}),
    AVI((String) FileCategory.Video, (int) new String[]{"avi"}, (FileCategory) new int[][]{new int[]{82, 73, 70, 70, -1, -1, -1, -1, 65, 86, 73, 32, 76, 73, 83, 84}}),
    MOV((String) FileCategory.Video, (int) new String[]{"mov"}, (FileCategory) new int[][]{new int[]{0, 0, 0, 20, 102, 116, BaiduSceneResult.STATION, 112, 113, 116, 32, 32}, new int[]{102, 116, BaiduSceneResult.STATION, 112, 113, 116, 32, 32}, new int[]{-1, -1, -1, -1, 109, 111, 111, 118}}),
    WMV((String) FileCategory.Video, (int) new String[]{"wmv"}, (FileCategory) new int[][]{new int[]{48, 38, 178, 117, BaiduSceneResult.DIGITAL_PRODUCT, 102, 207, 17, 166, 217, 0, 170, 0, 98, 206, 108}}),
    MP3((String) FileCategory.Audio, (int) new String[]{"mp3"}, (FileCategory) new int[][]{new int[]{73, 68, 51}, new int[]{255, 251}}),
    AAC((String) FileCategory.Audio, (int) new String[]{"aac"}, (FileCategory) new int[][]{new int[]{255, 241}, new int[]{255, 249}}),
    BMP((String) FileCategory.Image, (int) new String[]{"bmp"}, (FileCategory) new int[][]{new int[]{66, 77}}),
    TTF(FileCategory.Font, new String[]{"ttf", "otf"}),
    WBMP((String) FileCategory.Image, (int) new String[]{"wbmp"}, (FileCategory) false);
    
    private static final int CHECK_SIZE = 32;
    private static final String LOG_TAG = "FileType";
    private static final byte[] WEBP_HEADER = null;
    private static final Map<String, FileType> extensionMap = null;
    private final FileCategory category;
    private final boolean extensionOnly;
    private final String[] extensions;
    private final a imp;
    private final boolean isSupportedFormat;

    public enum FileCategory {
        Audio,
        Video,
        VideoOrAudio,
        Image,
        Font
    }

    private static abstract class a {
        private a() {
        }

        /* access modifiers changed from: package-private */
        public abstract boolean a(byte[] bArr);
    }

    static {
        extensionMap = new HashMap();
        WEBP_HEADER = new byte[]{82, 73, 70, 70, 87, 69, 66, 80};
    }

    private FileType(FileCategory fileCategory, String[] strArr) {
        this.imp = new a() {
            /* access modifiers changed from: package-private */
            public boolean a(byte[] bArr) {
                return false;
            }
        };
        this.category = fileCategory;
        this.extensions = strArr;
        this.extensionOnly = true;
        this.isSupportedFormat = true;
    }

    private FileType(FileCategory fileCategory, String[] strArr, boolean z) {
        this.imp = new a() {
            /* access modifiers changed from: package-private */
            public boolean a(byte[] bArr) {
                return false;
            }
        };
        this.category = fileCategory;
        this.extensions = strArr;
        this.extensionOnly = false;
        this.isSupportedFormat = z;
    }

    private FileType(FileCategory fileCategory, String[] strArr, final int[]... iArr) {
        this.imp = new a() {
            /* access modifiers changed from: package-private */
            public boolean a(byte[] bArr) {
                int i = 0;
                while (true) {
                    int[][] iArr = iArr;
                    if (i >= iArr.length) {
                        return false;
                    }
                    int[] iArr2 = iArr[i];
                    if (bArr.length >= iArr2.length) {
                        int i2 = 0;
                        while (i2 < iArr2.length) {
                            if (iArr2[i2] == -1 || bArr[i2] == ((byte) iArr2[i2])) {
                                i2++;
                            }
                        }
                        return true;
                    }
                    i++;
                }
            }
        };
        this.category = fileCategory;
        this.extensions = strArr;
        this.extensionOnly = false;
        this.isSupportedFormat = true;
    }

    private static void a() {
        if (extensionMap.isEmpty()) {
            for (FileType fileType : values()) {
                for (String put : fileType.extensions) {
                    extensionMap.put(put, fileType);
                }
            }
        }
    }

    public static FileType fromExtension(File file) {
        if (file == null) {
            return null;
        }
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        String lowerCase = lastIndexOf >= 0 ? name.substring(lastIndexOf + 1).toLowerCase(Locale.US) : null;
        if (lowerCase == null) {
            return null;
        }
        if (extensionMap.isEmpty()) {
            a();
        }
        return extensionMap.get(lowerCase);
    }

    public static FileType fromExtension(String str) {
        int lastIndexOf;
        if (str == null || (lastIndexOf = str.lastIndexOf(46)) < 0 || lastIndexOf < str.lastIndexOf(47)) {
            return null;
        }
        String lowerCase = str.substring(lastIndexOf + 1).toLowerCase(Locale.US);
        if (extensionMap.isEmpty()) {
            a();
        }
        return extensionMap.get(lowerCase);
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d6 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00d4 A[EDGE_INSN: B:75:0x00d4->B:51:0x00d4 ?: BREAK  , SYNTHETIC] */
    public static FileType fromFile(File file) {
        byte[] bArr;
        int i;
        int length;
        FileType fileType;
        int i2;
        FileInputStream fileInputStream;
        if (file == null) {
            return null;
        }
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        String substring = lastIndexOf >= 0 ? name.substring(lastIndexOf + 1) : null;
        if (substring != null) {
            for (FileType fileType2 : values()) {
                String[] strArr = fileType2.extensions;
                int length2 = strArr.length;
                int i3 = 0;
                while (i3 < length2) {
                    String str = strArr[i3];
                    if (!fileType2.extensionOnly || !str.equalsIgnoreCase(substring)) {
                        i3++;
                    } else {
                        Log.d(LOG_TAG, "FileType extension match: " + fileType2.name());
                        return fileType2;
                    }
                }
            }
        }
        if (!file.exists() || file.length() < 32) {
            bArr = null;
        } else {
            bArr = new byte[32];
            try {
                fileInputStream = new FileInputStream(file);
                i = fileInputStream.read(bArr);
                try {
                    fileInputStream.close();
                } catch (IOException unused) {
                }
            } catch (IOException unused2) {
                i = 0;
                bArr = null;
                FileType[] values = values();
                length = values.length;
                fileType = null;
                i2 = 0;
                while (true) {
                    if (i2 < length) {
                    }
                    i2++;
                }
                if (fileType != null) {
                }
                if (substring != null) {
                }
                return null;
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
            if (bArr != null && i >= 32) {
                FileType[] values2 = values();
                length = values2.length;
                fileType = null;
                i2 = 0;
                while (true) {
                    if (i2 < length) {
                        break;
                    }
                    FileType fileType3 = values2[i2];
                    if (fileType3.imp.a(bArr)) {
                        Log.d(LOG_TAG, "FileType analysis match: " + fileType3.name());
                        if (fileType != null) {
                            Log.d(LOG_TAG, "FileType analysis MULTIPLE match: " + fileType3.name() + " (fall back to file extension)");
                            fileType = null;
                            break;
                        }
                        fileType = fileType3;
                    }
                    i2++;
                }
                if (fileType != null) {
                    return fileType;
                }
            }
        }
        if (substring != null) {
            for (FileType fileType4 : values()) {
                for (String equalsIgnoreCase : fileType4.extensions) {
                    if (equalsIgnoreCase.equalsIgnoreCase(substring)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("FileType extension match: ");
                        sb.append(fileType4.name());
                        sb.append(" [");
                        sb.append(bArr == null ? "null" : n.a(bArr));
                        sb.append("]");
                        Log.d(LOG_TAG, sb.toString());
                        return fileType4;
                    }
                }
            }
        }
        return null;
    }

    public static FileType fromFile(String str) {
        if (str == null) {
            return null;
        }
        return fromFile(new File(str));
    }

    public FileCategory getCategory() {
        return this.category;
    }

    public boolean isAudio() {
        return this.category == FileCategory.Audio || this.category == FileCategory.VideoOrAudio;
    }

    public boolean isImage() {
        return this.category == FileCategory.Image;
    }

    public boolean isSupportedFormat() {
        return this.isSupportedFormat;
    }

    public boolean isVideo() {
        return this.category == FileCategory.Video || this.category == FileCategory.VideoOrAudio;
    }
}
