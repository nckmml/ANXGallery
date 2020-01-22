package com.miui.gallery.video.editor.util;

import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileHelper {
    public static boolean createDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            Log.d("FileHelper", "The target directory already exists");
            return true;
        } else if (file.mkdirs()) {
            Log.d("FileHelper", "create dir sucessed！");
            return true;
        } else {
            Log.d("FileHelper", "create dir failed！ ");
            return false;
        }
    }

    private static String generateFileName(String str, String str2, String str3, int i, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z && !str2.startsWith(".")) {
            sb.append(".");
        }
        sb.append(str2);
        sb.append("(");
        sb.append(String.valueOf(i));
        sb.append(")");
        if (!TextUtils.isEmpty(str3)) {
            sb.append(".");
            sb.append(str3);
        }
        File file = new File(str, sb.toString());
        return file.exists() ? generateFileName(str, str2, str3, i + 1, z) : file.getAbsolutePath();
    }

    public static String generateOutputFilePath(File file, boolean z) {
        String str;
        String str2;
        String str3 = null;
        if (file == null) {
            return null;
        }
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            str3 = parentFile.getAbsolutePath();
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = StorageUtils.getCreativeDirectory();
            FileUtils.createFolder(str3, false);
        } else if (DocumentProviderUtils.needUseDocumentProvider(str3)) {
            str3 = StorageUtils.getPathInPrimaryStorage(StorageUtils.getRelativePath(GalleryApp.sGetAndroidContext(), str3));
            FileUtils.createFolder(str3, false);
        }
        int lastIndexOf = file.getName().lastIndexOf(".");
        if (lastIndexOf == -1 || lastIndexOf >= file.getName().length() - 1) {
            str = file.getName();
            str2 = "";
        } else {
            str2 = file.getName().substring(lastIndexOf + 1);
            str = file.getName().substring(0, lastIndexOf);
        }
        return generateFileName(str3, str, str2, 0, z);
    }

    public static String generateOutputFilePath(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            return generateOutputFilePath(new File(str), z);
        }
        return null;
    }

    private static void outputToFileStream(byte[] bArr, File file, ZipInputStream zipInputStream) {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            while (true) {
                try {
                    int read = zipInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        MiscUtil.closeSilently(fileOutputStream2);
                        return;
                    }
                } catch (FileNotFoundException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    Log.e("FileHelper", e.toString());
                    MiscUtil.closeSilently(fileOutputStream);
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    Log.e("FileHelper", e.toString());
                    MiscUtil.closeSilently(fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    MiscUtil.closeSilently(fileOutputStream);
                    throw th;
                }
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            Log.e("FileHelper", e.toString());
            MiscUtil.closeSilently(fileOutputStream);
        } catch (IOException e4) {
            e = e4;
            Log.e("FileHelper", e.toString());
            MiscUtil.closeSilently(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            MiscUtil.closeSilently(fileOutputStream);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c6 A[Catch:{ all -> 0x00d6 }] */
    public static String unZipFile(String str, String str2) {
        File file;
        ZipInputStream zipInputStream;
        File file2 = new File(str);
        if (!file2.exists()) {
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            file = new File(file2.getParent() + "/" + file2.getName().replaceAll("[.][^.]+$", ""));
        } else {
            file = new File(str2);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(file2));
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                byte[] bArr = new byte[1024];
                while (nextEntry != null) {
                    if (nextEntry.isDirectory()) {
                        File file3 = new File(file, nextEntry.getName());
                        if (!file3.exists()) {
                            file3.mkdir();
                        }
                        nextEntry = zipInputStream.getNextEntry();
                    } else {
                        File file4 = new File(file, nextEntry.getName());
                        if (!file4.getParentFile().exists()) {
                            file4.getParentFile().mkdirs();
                        }
                        file4.createNewFile();
                        outputToFileStream(bArr, file4, zipInputStream);
                        nextEntry = zipInputStream.getNextEntry();
                    }
                }
                MiscUtil.closeSilently(zipInputStream);
                if (!file.exists()) {
                    return null;
                }
                if (file2.exists()) {
                    file2.delete();
                }
                return file.getAbsolutePath();
            } catch (IOException e) {
                e = e;
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    Log.e("FileHelper", e.toString());
                    MiscUtil.closeSilently(zipInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(zipInputStream);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            zipInputStream = null;
            if (file.exists()) {
            }
            Log.e("FileHelper", e.toString());
            MiscUtil.closeSilently(zipInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            zipInputStream = null;
            MiscUtil.closeSilently(zipInputStream);
            throw th;
        }
    }
}
