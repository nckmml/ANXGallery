package com.miui.gallery.util;

import android.text.TextUtils;
import androidx.documentfile.provider.DocumentFile;
import com.miui.gallery.util.BaseDocumentProviderUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.Locale;
import miui.os.FileUtils;

public class BaseFileUtils extends FileUtils {
    public static String concat(String str, String str2) {
        String str3 = (TextUtils.isEmpty(str2) || str2.startsWith(File.separator)) ? "" : File.separator;
        return str + str3 + str2;
    }

    public static boolean contains(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        return str2.toLowerCase().startsWith(str.toLowerCase());
    }

    public static boolean copyFile(File file, File file2) {
        return BaseDocumentProviderUtils.needUseDocumentProvider(file2.getAbsolutePath()) ? copyFileByDocumentProvider(file, file2) : FileUtils.copyFile(file, file2);
    }

    public static boolean copyFileByDocumentProvider(File file, File file2) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                boolean copyFileByDocumentProvider = copyFileByDocumentProvider((InputStream) fileInputStream2, file2);
                BaseMiscUtil.closeSilently(fileInputStream2);
                return copyFileByDocumentProvider;
            } catch (IOException e) {
                e = e;
                fileInputStream = fileInputStream2;
                try {
                    Log.e("BaseFileUtils", (Throwable) e);
                    BaseMiscUtil.closeSilently(fileInputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    BaseMiscUtil.closeSilently(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                BaseMiscUtil.closeSilently(fileInputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Log.e("BaseFileUtils", (Throwable) e);
            BaseMiscUtil.closeSilently(fileInputStream);
            return false;
        }
    }

    public static boolean copyFileByDocumentProvider(final InputStream inputStream, File file) {
        if (file.exists()) {
            if (countDeleteFile(file) <= 0) {
                return false;
            }
        }
        Boolean bool = (Boolean) BaseDocumentProviderUtils.safeWriteFile(file.getAbsolutePath(), new BaseDocumentProviderUtils.WriteHandler<Boolean>() {
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
                    Log.e("BaseFileUtils", (Throwable) e);
                    return false;
                }
            }
        });
        return bool != null && bool.booleanValue();
    }

    public static int countDeleteFile(File... fileArr) {
        int i = 0;
        for (File file : fileArr) {
            if (file != null) {
                i = !file.exists() ? i + 1 : i + (deleteFile(file) ? 1 : 0);
            }
        }
        return i;
    }

    public static boolean createFolderWithoutCheck(File file, boolean z) {
        if (file == null) {
            return false;
        }
        boolean z2 = true;
        if (!file.exists()) {
            z2 = file.mkdirs();
            if (z) {
                addNoMedia(file.getAbsolutePath());
            }
        }
        return z2;
    }

    public static boolean deleteFile(File file) {
        boolean z = false;
        if (file == null || !file.exists()) {
            return false;
        }
        if (BaseDocumentProviderUtils.needUseDocumentProvider(file.getAbsolutePath())) {
            boolean isDirectory = file.isDirectory();
            DocumentFile documentFile = BaseDocumentProviderUtils.getDocumentFile(StaticContext.sGetAndroidContext(), file);
            if (documentFile != null) {
                z = documentFile.delete();
            }
            if (isDirectory) {
                Log.i("BaseFileUtils", "delete folder [%s]'s files, result %s", file.getAbsolutePath(), Boolean.valueOf(z));
            } else {
                Log.i("BaseFileUtils", "delete [%s]'s file, result %s", file.getAbsolutePath(), Boolean.valueOf(z));
            }
            return z;
        } else if (file.isDirectory()) {
            boolean rm = rm(file.getAbsolutePath());
            Log.i("BaseFileUtils", "delete folder [%s]'s files, result %s", file.getAbsolutePath(), Boolean.valueOf(rm));
            return rm;
        } else {
            boolean delete = file.delete();
            if (!delete) {
                Log.e("BaseFileUtils", "File.delete() returns false %s", (Object) file.getAbsoluteFile());
                delete = file.delete();
                Log.d("BaseFileUtils", "Try File.delete() again. return %s", (Object) Boolean.valueOf(delete));
            }
            Log.i("BaseFileUtils", "delete [%s]'s file, result %s", file.getAbsolutePath(), Boolean.valueOf(delete));
            return delete;
        }
    }

    public static int getBucketID(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return str.toLowerCase(Locale.ENGLISH).hashCode();
    }

    public static String getFileName(String str) {
        String trim = str == null ? null : str.trim();
        if (TextUtils.isEmpty(trim)) {
            return "";
        }
        if (trim.endsWith(File.separator)) {
            trim = trim.substring(0, trim.length() - 1);
        }
        return trim.substring(trim.lastIndexOf(File.separator) + 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = r2.lastIndexOf(46);
     */
    public static String getFileNameWithoutExtension(String str) {
        int lastIndexOf;
        String fileName = getFileName(str);
        return (TextUtils.isEmpty(fileName) || lastIndexOf <= 0) ? fileName : str.substring(0, lastIndexOf);
    }

    public static long getFileSize(String str) {
        if (!TextUtils.isEmpty(str)) {
            return new File(str).length();
        }
        return 0;
    }

    public static String getFileTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf > -1 ? str.substring(0, lastIndexOf) : str;
    }

    public static String getParentFolderPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf > -1 ? str.substring(0, lastIndexOf) : str;
    }

    public static boolean isFileExist(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static boolean move(File file, File file2) {
        if (!(file == null || file2 == null)) {
            if (renameFile(file, file2)) {
                return true;
            }
            if (copyFile(file, file2)) {
                deleteFile(file);
                return true;
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: java.nio.channels.FileChannel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: java.nio.channels.FileChannel} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00c6  */
    public static boolean renameFile(File file, File file2) {
        Closeable closeable;
        FileChannel fileChannel;
        FileInputStream fileInputStream;
        boolean z;
        FileChannel fileChannel2;
        FileChannel fileChannel3;
        FileOutputStream fileOutputStream;
        boolean z2 = true;
        if (file.renameTo(file2)) {
            return true;
        }
        boolean z3 = false;
        if (!BaseDocumentProviderUtils.needUseDocumentProvider(file2.getAbsolutePath())) {
            FileChannel fileChannel4 = null;
            try {
            } catch (IOException unused) {
                File file3 = file;
                File file4 = file2;
                z = false;
                fileInputStream = null;
                fileChannel2 = null;
                closeable = fileChannel;
                BaseMiscUtil.closeSilently(fileChannel);
                BaseMiscUtil.closeSilently(fileChannel4);
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(closeable);
                if (!z) {
                }
                return false;
            } catch (Throwable th) {
                th = th;
                File file5 = file;
                File file6 = file2;
                fileInputStream = null;
                fileChannel = null;
                fileChannel3 = fileChannel;
                BaseMiscUtil.closeSilently(fileChannel);
                BaseMiscUtil.closeSilently(fileChannel4);
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(fileChannel3);
                if (z3) {
                }
                throw th;
            }
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileChannel = fileInputStream.getChannel();
                    try {
                        try {
                            fileOutputStream = new FileOutputStream(file2);
                        } catch (IOException unused2) {
                            z = false;
                            closeable = null;
                            BaseMiscUtil.closeSilently(fileChannel);
                            BaseMiscUtil.closeSilently(fileChannel4);
                            BaseMiscUtil.closeSilently(fileInputStream);
                            BaseMiscUtil.closeSilently(closeable);
                            if (!z) {
                                deleteFile(file);
                            } else {
                                deleteFile(file2);
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            fileChannel3 = null;
                            BaseMiscUtil.closeSilently(fileChannel);
                            BaseMiscUtil.closeSilently(fileChannel4);
                            BaseMiscUtil.closeSilently(fileInputStream);
                            BaseMiscUtil.closeSilently(fileChannel3);
                            if (z3) {
                                deleteFile(file);
                            } else {
                                deleteFile(file2);
                            }
                            throw th;
                        }
                    } catch (IOException unused3) {
                        File file7 = file2;
                        z = false;
                        closeable = null;
                        BaseMiscUtil.closeSilently(fileChannel);
                        BaseMiscUtil.closeSilently(fileChannel4);
                        BaseMiscUtil.closeSilently(fileInputStream);
                        BaseMiscUtil.closeSilently(closeable);
                        if (!z) {
                        }
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        File file8 = file2;
                        fileChannel3 = null;
                        BaseMiscUtil.closeSilently(fileChannel);
                        BaseMiscUtil.closeSilently(fileChannel4);
                        BaseMiscUtil.closeSilently(fileInputStream);
                        BaseMiscUtil.closeSilently(fileChannel3);
                        if (z3) {
                        }
                        throw th;
                    }
                } catch (IOException unused4) {
                    File file9 = file2;
                    z = false;
                    fileChannel2 = null;
                    closeable = fileChannel;
                    BaseMiscUtil.closeSilently(fileChannel);
                    BaseMiscUtil.closeSilently(fileChannel4);
                    BaseMiscUtil.closeSilently(fileInputStream);
                    BaseMiscUtil.closeSilently(closeable);
                    if (!z) {
                    }
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    File file10 = file2;
                    fileChannel = null;
                    fileChannel3 = fileChannel;
                    BaseMiscUtil.closeSilently(fileChannel);
                    BaseMiscUtil.closeSilently(fileChannel4);
                    BaseMiscUtil.closeSilently(fileInputStream);
                    BaseMiscUtil.closeSilently(fileChannel3);
                    if (z3) {
                    }
                    throw th;
                }
            } catch (IOException unused5) {
                File file42 = file2;
                z = false;
                fileInputStream = null;
                fileChannel2 = null;
                closeable = fileChannel;
                BaseMiscUtil.closeSilently(fileChannel);
                BaseMiscUtil.closeSilently(fileChannel4);
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(closeable);
                if (!z) {
                }
                return false;
            } catch (Throwable th5) {
                th = th5;
                File file62 = file2;
                fileInputStream = null;
                fileChannel = null;
                fileChannel3 = fileChannel;
                BaseMiscUtil.closeSilently(fileChannel);
                BaseMiscUtil.closeSilently(fileChannel4);
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(fileChannel3);
                if (z3) {
                }
                throw th;
            }
            try {
                fileChannel4 = fileOutputStream.getChannel();
                long size = fileChannel.size();
                long transferTo = fileChannel.transferTo(0, size, fileChannel4);
                if (size != transferTo) {
                    z2 = false;
                }
                if (!z) {
                    try {
                        Log.w("BaseFileUtils", "transfer error, expect count %s, actual count %s", Long.valueOf(size), Long.valueOf(transferTo));
                    } catch (IOException unused6) {
                        closeable = fileOutputStream;
                    } catch (Throwable th6) {
                        th = th6;
                        z3 = z;
                        fileChannel3 = fileOutputStream;
                        BaseMiscUtil.closeSilently(fileChannel);
                        BaseMiscUtil.closeSilently(fileChannel4);
                        BaseMiscUtil.closeSilently(fileInputStream);
                        BaseMiscUtil.closeSilently(fileChannel3);
                        if (z3) {
                        }
                        throw th;
                    }
                }
                BaseMiscUtil.closeSilently(fileChannel);
                BaseMiscUtil.closeSilently(fileChannel4);
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(fileOutputStream);
                if (z) {
                    deleteFile(file);
                } else {
                    deleteFile(file2);
                }
                return z;
            } catch (IOException unused7) {
                z = false;
                closeable = fileOutputStream;
                BaseMiscUtil.closeSilently(fileChannel);
                BaseMiscUtil.closeSilently(fileChannel4);
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(closeable);
                if (!z) {
                }
                return false;
            } catch (Throwable th7) {
                th = th7;
                fileChannel3 = fileOutputStream;
                BaseMiscUtil.closeSilently(fileChannel);
                BaseMiscUtil.closeSilently(fileChannel4);
                BaseMiscUtil.closeSilently(fileInputStream);
                BaseMiscUtil.closeSilently(fileChannel3);
                if (z3) {
                }
                throw th;
            }
        } else if (copyFile(file, file2)) {
            deleteFile(file);
            return true;
        }
        return false;
    }
}
