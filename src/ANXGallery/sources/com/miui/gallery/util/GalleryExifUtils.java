package com.miui.gallery.util;

import android.content.Context;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Pair;
import com.miui.gallery3d.exif.ExifInterface;
import java.io.File;

public class GalleryExifUtils {
    public static boolean isPhotoContainsSpecialTags(String str) {
        return !TextUtils.isEmpty(str) && (ExifUtil.supportRefocus(str) || ExifUtil.isMotionPhoto(str) || ExifUtil.getMTSpecialAITypeValue(str) > 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045 A[Catch:{ Exception -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x007a A[Catch:{ Exception -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008b A[SYNTHETIC, Splitter:B:40:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0099 A[Catch:{ Exception -> 0x0128 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bb A[Catch:{ Exception -> 0x0128 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00de A[Catch:{ Exception -> 0x0128 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00df A[Catch:{ Exception -> 0x0128 }] */
    public static Pair<Boolean, File> setImageFileDataTime(Context context, String str, long j, boolean z) {
        File file;
        File file2;
        ParcelFileDescriptor parcelFileDescriptor;
        ExifInterface exifInterface;
        String str2;
        android.media.ExifInterface exifInterface2;
        Context context2 = context;
        long j2 = j;
        boolean z2 = z;
        File file3 = new File(str);
        String generateFileDateTimeName = FileUtils.generateFileDateTimeName(file3, j2);
        try {
            boolean needUseDocumentProvider = DocumentProviderUtils.needUseDocumentProvider(str);
            boolean z3 = false;
            boolean z4 = Build.VERSION.SDK_INT >= 28;
            boolean isPhotoContainsSpecialTags = isPhotoContainsSpecialTags(str);
            boolean z5 = Build.VERSION.SDK_INT == 23;
            if (z4 && isPhotoContainsSpecialTags) {
                if (needUseDocumentProvider) {
                    file2 = null;
                    if (needUseDocumentProvider) {
                        exifInterface = ExifUtil.sGallery3DExifCreator.create(generateFileDateTimeName);
                        str2 = null;
                        file = file2;
                        parcelFileDescriptor = null;
                    } else if (!z4 || !isPhotoContainsSpecialTags) {
                        ParcelFileDescriptor openFileDescriptor = DocumentProviderUtils.openFileDescriptor(context2, generateFileDateTimeName);
                        if (openFileDescriptor == null) {
                            return null;
                        }
                        exifInterface = ExifUtil.sGallery3DExifCreator.create(openFileDescriptor.getFileDescriptor());
                        file = file2;
                        parcelFileDescriptor = openFileDescriptor;
                        str2 = null;
                    } else {
                        str2 = FileUtils.generateCacheFilePath(context2, file3, j2);
                        file = new File(str2);
                        try {
                            if (!FileUtils.copyFile(file3, file)) {
                                return null;
                            }
                            exifInterface = ExifUtil.sGallery3DExifCreator.create(str2);
                            parcelFileDescriptor = null;
                        } catch (Exception unused) {
                            FileUtils.deleteFile(file);
                            return null;
                        }
                    }
                    if (str2 == null) {
                        str2 = generateFileDateTimeName;
                    }
                    if (!isPhotoContainsSpecialTags) {
                        exifInterface.readExif(str2);
                        ExifUtil.setDateTime(exifInterface, j2, z2);
                        ExifUtil.setSubSecTime(exifInterface, j2);
                        exifInterface.forceRewriteExif(str2);
                        exifInterface2 = null;
                    } else {
                        exifInterface2 = needUseDocumentProvider ? ExifUtil.sMediaExifCreator.create(parcelFileDescriptor.getFileDescriptor()) : ExifUtil.sMediaExifCreator.create(generateFileDateTimeName);
                        ExifUtil.setDateTime(exifInterface2, j2, z2);
                        ExifUtil.setSubSecTime(exifInterface2, j2);
                        exifInterface2.saveAttributes();
                    }
                    if (isPhotoContainsSpecialTags) {
                        exifInterface2 = (!needUseDocumentProvider || z4) ? ExifUtil.sMediaExifCreator.create(str2) : ExifUtil.sMediaExifCreator.create(parcelFileDescriptor.getFileDescriptor());
                    }
                    if (ExifUtil.getDateTime(exifInterface2, true) == j2) {
                        return null;
                    }
                    if (isPhotoContainsSpecialTags && z4 && needUseDocumentProvider) {
                        File file4 = new File(generateFileDateTimeName);
                        if (FileUtils.copyFileByDocumentProvider(file, file4)) {
                            return new Pair<>(Boolean.valueOf(FileUtils.deleteFile(file)), file4);
                        }
                        return null;
                    } else if (z5) {
                        file.setLastModified(j2);
                        return new Pair<>(true, file);
                    } else {
                        if (file.exists() && file.setLastModified(j2)) {
                            z3 = true;
                        }
                        return new Pair<>(Boolean.valueOf(z3), file);
                    }
                }
            }
            file2 = new File(generateFileDateTimeName);
            try {
                if (!FileUtils.copyFile(file3, file2)) {
                    return null;
                }
                if (needUseDocumentProvider) {
                }
                if (str2 == null) {
                }
                if (!isPhotoContainsSpecialTags) {
                }
                if (isPhotoContainsSpecialTags) {
                }
                if (ExifUtil.getDateTime(exifInterface2, true) == j2) {
                }
            } catch (Exception unused2) {
                file = file2;
                FileUtils.deleteFile(file);
                return null;
            }
        } catch (Exception unused3) {
            file = null;
            FileUtils.deleteFile(file);
            return null;
        }
    }
}
