package miui.module.appstore;

import com.miui.gallery.movie.utils.MovieStatUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import miui.security.DigestUtils;

public class MD5Utils {
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0033 A[SYNTHETIC, Splitter:B:20:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0042 A[SYNTHETIC, Splitter:B:27:0x0042] */
    public static String MD5Sum(File file) {
        StringBuilder sb = new StringBuilder();
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                for (byte byte2Hex : DigestUtils.get(fileInputStream2, "MD5")) {
                    sb.append(byte2Hex(byte2Hex));
                }
            } catch (Exception e) {
                e = e;
                fileInputStream = fileInputStream2;
                try {
                    e.printStackTrace();
                    if (fileInputStream != null) {
                    }
                    return sb.toString();
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (fileInputStream2 != null) {
                }
                throw th;
            }
            try {
                fileInputStream2.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return sb.toString();
        }
        return sb.toString();
    }

    private static String byte2Hex(byte b) {
        int i = (b & Byte.MAX_VALUE) + (b < 0 ? (byte) 128 : 0);
        StringBuilder sb = new StringBuilder();
        sb.append(i < 16 ? MovieStatUtils.DOWNLOAD_SUCCESS : "");
        sb.append(Integer.toHexString(i).toLowerCase());
        return sb.toString();
    }

    public static boolean checkMD5(File file, String str) {
        return file != null && file.exists() && str != null && MD5Sum(file).equals(str.toLowerCase());
    }
}
