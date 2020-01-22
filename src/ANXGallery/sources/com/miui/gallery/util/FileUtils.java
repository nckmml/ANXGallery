package com.miui.gallery.util;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.documentfile.provider.DocumentFile;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.BaseDocumentProviderUtils;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.deleterecorder.DeleteRecord;
import com.miui.gallery.util.deleterecorder.DeleteRecorder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import miui.security.DigestUtils;
import miui.text.ExtraTextUtils;

public class FileUtils extends BaseFileUtils {
    public static boolean copyFile(File file, File file2) {
        return DocumentProviderUtils.needUseDocumentProvider(file2.getAbsolutePath()) ? copyFileByDocumentProvider(file, file2) : miui.os.FileUtils.copyFile(file, file2);
    }

    public static boolean copyFileByDocumentProvider(File file, File file2) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                boolean copyFileByDocumentProvider = copyFileByDocumentProvider((InputStream) fileInputStream2, file2);
                MiscUtil.closeSilently(fileInputStream2);
                return copyFileByDocumentProvider;
            } catch (IOException e) {
                e = e;
                fileInputStream = fileInputStream2;
                try {
                    Log.e("FileUtils", (Throwable) e);
                    MiscUtil.closeSilently(fileInputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                MiscUtil.closeSilently(fileInputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Log.e("FileUtils", (Throwable) e);
            MiscUtil.closeSilently(fileInputStream);
            return false;
        }
    }

    public static boolean copyFileByDocumentProvider(final InputStream inputStream, File file) {
        if (file.exists()) {
            if (MediaFileUtils.deleteFile(file) <= 0) {
                return false;
            }
        }
        Boolean bool = (Boolean) DocumentProviderUtils.safeWriteFile(file.getAbsolutePath(), new BaseDocumentProviderUtils.WriteHandler<Boolean>() {
            public Boolean handle(FileOutputStream fileOutputStream) {
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read >= 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            return true;
                        }
                    }
                } catch (IOException e) {
                    Log.e("FileUtils", (Throwable) e);
                    return false;
                }
            }
        });
        return bool != null && bool.booleanValue();
    }

    public static boolean createFolder(File file, boolean z) {
        if (file.exists() && file.isFile()) {
            boolean z2 = MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, file) > 0;
            Log.e("FileUtils", "createFolder  there is a file that has the same name as the dest folder, delete it first. result:" + z2);
            if (!z2) {
                return false;
            }
            DeleteRecorder.record(new DeleteRecord(52, file.getAbsolutePath(), "FileUtils"));
        }
        mkdirsCustomize(file, 511, -1, -1);
        if (!file.exists()) {
            Log.e("FileUtils", "folder: " + file + " Can not be created.");
            return false;
        }
        if (z) {
            try {
                if (DocumentProviderUtils.needUseDocumentProvider(file.getAbsolutePath())) {
                    DocumentFile documentFile = DocumentProviderUtils.getDocumentFile(GalleryApp.sGetAndroidContext(), file);
                    if (documentFile.findFile(".nomedia") == null) {
                        documentFile.createFile("any/any", ".nomedia");
                    }
                } else {
                    new File(file, ".nomedia").createNewFile();
                }
            } catch (IOException e) {
                Log.e("FileUtils", (Throwable) e);
            }
        }
        return true;
    }

    public static boolean createFolder(String str, boolean z) {
        return createFolder(new File(str), z);
    }

    public static File forceCreate(String str, String str2, int i) {
        Log.d("FileUtils", "forceCreate file(%s, %s).", str, str2);
        File file = new File(str, str2);
        if (!file.exists()) {
            Log.d("FileUtils", "no conflict, create finish.");
            return file;
        } else if (i != 1) {
            Log.d("FileUtils", "duplicated file fount, rename: ");
            int indexOf = str2.indexOf(".");
            String substring = str2.substring(0, indexOf);
            String substring2 = str2.substring(indexOf, str2.length());
            int i2 = 1;
            while (file.exists()) {
                file = new File(str, substring + "_" + i2 + substring2);
                i2++;
            }
            Log.d("FileUtils", "rename to %s, create finish.", (Object) file);
            return file;
        } else {
            if (MediaFileUtils.deleteFileType(MediaFileUtils.FileType.NORMAL, file) <= 0) {
                Log.e("FileUtils", "forceCreate: Delete exist file failed");
            } else {
                DeleteRecorder.record(new DeleteRecord(51, file.getAbsolutePath(), "FileUtils"));
            }
            return file;
        }
    }

    public static String generateCacheFilePath(Context context, File file, long j) {
        return generateFileDateTimeName(file, j, context.getCacheDir().getAbsolutePath());
    }

    public static String generateFileDateTimeName(File file, long j) {
        return generateFileDateTimeName(file, j, getParentFolderPath(file.getAbsolutePath()));
    }

    public static String generateFileDateTimeName(File file, long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        Date date = new Date(j);
        String absolutePath = file.getAbsolutePath();
        String str2 = "." + getExtension(absolutePath);
        StringBuilder sb = new StringBuilder(str + File.separator);
        String format = simpleDateFormat.format(date);
        String name = file.getName();
        int indexOf = name.indexOf("_");
        if (indexOf > 0) {
            sb.append(name.substring(0, indexOf + 1));
            sb.append(format);
            sb.append(str2);
        } else {
            if (name.contains(".")) {
                name = name.substring(0, name.lastIndexOf(46));
            }
            sb.append(name);
            sb.append("_" + format + str2);
        }
        return sb.toString();
    }

    public static int getBucketID(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return str.toLowerCase(Locale.ENGLISH).hashCode();
    }

    public static byte[] getFileSha1(String str) {
        FileInputStream fileInputStream;
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            Log.d("FileUtils", "File [%s] doesn't exist or is not a file", (Object) str);
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = DigestUtils.get(fileInputStream, "SHA-1");
                MiscUtil.closeSilently(fileInputStream);
                return bArr;
            } catch (Exception e) {
                e = e;
                try {
                    Log.e("FileUtils", (Throwable) e);
                    MiscUtil.closeSilently(fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            Log.e("FileUtils", (Throwable) e);
            MiscUtil.closeSilently(fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            MiscUtil.closeSilently(fileInputStream);
            throw th;
        }
    }

    public static File getImageRelativeDngFile(String str) {
        File file;
        if (str == null || str.length() < 4) {
            return null;
        }
        if (str.substring(str.length() - 4).equalsIgnoreCase(".jpg")) {
            file = new File(str.substring(0, str.length() - 4) + ".dng");
        } else {
            file = null;
        }
        if (file == null || (file.exists() && file.isFile())) {
            return file;
        }
        return null;
    }

    public static String getSha1(File file) {
        FileInputStream fileInputStream;
        long uptimeMillis = SystemClock.uptimeMillis();
        String str = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                str = ExtraTextUtils.toHexReadable(DigestUtils.get(fileInputStream, "SHA-1"));
            } catch (Exception e) {
                e = e;
                try {
                    Log.e("FileUtils", (Throwable) e);
                    MiscUtil.closeSilently(fileInputStream);
                    Log.d("FileUtils", "getSha1 for [%s] costs [%d]", file.getPath(), Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
                    return str;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            Log.e("FileUtils", (Throwable) e);
            MiscUtil.closeSilently(fileInputStream);
            Log.d("FileUtils", "getSha1 for [%s] costs [%d]", file.getPath(), Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
            return str;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            MiscUtil.closeSilently(fileInputStream);
            throw th;
        }
        MiscUtil.closeSilently(fileInputStream);
        Log.d("FileUtils", "getSha1 for [%s] costs [%d]", file.getPath(), Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
        return str;
    }

    public static String getSha1(String str) {
        return getSha1(new File(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r2 = r2.getLastPathSegment();
     */
    public static boolean isScreenShot(Uri uri) {
        String lastPathSegment;
        return (uri == null || lastPathSegment == null || !lastPathSegment.startsWith("Screenshot")) ? false : true;
    }

    private static boolean mkdirsCustomize(File file, int i, int i2, int i3) {
        DocumentFile documentFileDirectory;
        if (file.exists()) {
            return file.isDirectory();
        }
        if (!mkdirsCustomize(file.getParentFile(), i, i2, i3)) {
            return false;
        }
        if (file.mkdir()) {
            String path = file.getPath();
            Log.i("FileUtils", "path %s, chmod %s, chown %s", path, Boolean.valueOf(chmod(path, i)), Boolean.valueOf(chown(path, i2, i3)));
            return true;
        } else if (!DocumentProviderUtils.needUseDocumentProvider(file.getAbsolutePath()) || (documentFileDirectory = DocumentProviderUtils.getDocumentFileDirectory(GalleryApp.sGetAndroidContext(), file)) == null || !documentFileDirectory.isDirectory()) {
            return false;
        } else {
            String path2 = file.getPath();
            Log.i("FileUtils", "path %s, chmod %s, chown %s", path2, Boolean.valueOf(chmod(path2, i)), Boolean.valueOf(chown(path2, i2, i3)));
            return true;
        }
    }

    public static boolean move(File file, File file2) {
        if (!(file == null || file2 == null)) {
            if (renameFile(file, file2)) {
                return true;
            }
            if (copyFile(file, file2)) {
                MediaFileUtils.deleteFile(file);
                return true;
            }
        }
        return false;
    }

    public static boolean setLastModified(String str, long j) {
        try {
            return new File(str).setLastModified(j);
        } catch (Exception e) {
            Log.e("FileUtils", (Throwable) e);
            return false;
        }
    }
}
