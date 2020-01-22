package com.miui.gallery.data;

import android.text.TextUtils;
import com.google.common.collect.Lists;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalUbifocus extends Ubifocus {
    private static final String UBIFOCUS_FILE_POSTFIX = ".jpg";
    public static final String UBIFOCUS_MAP_POSTFIX = ".y";

    public static class SubFile {
        public String mFilePath;
        public int mIndex;

        public SubFile(String str, int i) {
            this.mFilePath = str;
            this.mIndex = i;
        }

        public String getFilePath() {
            return this.mFilePath;
        }
    }

    public static String createInnerFileName(String str, int i, int i2) {
        if (!isOuterFile(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(getUbifocusPrefixIgnoreCase(str));
        sb.append("_");
        sb.append(i);
        sb.append(i == i2 + -1 ? UBIFOCUS_MAP_POSTFIX : UBIFOCUS_FILE_POSTFIX);
        return sb.toString();
    }

    public static int getMainFileIndex() {
        return 2;
    }

    public static String getSubUbiFolder(String str) {
        return str + File.separator + ".ubifocus";
    }

    public static int getUbifocusPatternIndex(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.indexOf("_UBIFOCUS");
        }
        return -1;
    }

    public static String getUbifocusPrefix(String str) {
        int ubifocusPatternIndex = getUbifocusPatternIndex(str);
        return ubifocusPatternIndex >= 0 ? str.substring(0, ubifocusPatternIndex + 9) : "";
    }

    private static String getUbifocusPrefixIgnoreCase(String str) {
        return getUbifocusPrefix(str.toUpperCase());
    }

    public static int getUbifocusSubFiles(String str, List<SubFile> list) {
        int i;
        boolean z;
        String fileName = FileUtils.getFileName(str);
        String ubifocusPrefixIgnoreCase = getUbifocusPrefixIgnoreCase(fileName);
        list.clear();
        if (isOuterFile(fileName, ubifocusPrefixIgnoreCase)) {
            String str2 = getSubUbiFolder(FileUtils.getParentFolderPath(str)) + File.separator + ubifocusPrefixIgnoreCase + "_";
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int i2 = 0;
            i = -1;
            while (true) {
                if (i2 >= 100) {
                    break;
                }
                sb.setLength(0);
                sb.append(str2);
                sb.append(i2);
                sb.append(UBIFOCUS_FILE_POSTFIX);
                boolean exists = new File(sb.toString()).exists();
                if (!exists) {
                    sb2.setLength(0);
                    sb2.append(str2);
                    sb2.append(i2);
                    sb2.append(UBIFOCUS_MAP_POSTFIX);
                    z = new File(sb2.toString()).exists();
                } else {
                    z = false;
                }
                if (!exists) {
                    if (!z) {
                        if (i != -1) {
                            break;
                        }
                        list.add(new SubFile(str, i2));
                        i = i2;
                    } else {
                        list.add(new SubFile(sb2.toString(), i2));
                        break;
                    }
                } else {
                    list.add(new SubFile(sb.toString(), i2));
                }
                i2++;
            }
        } else {
            i = -1;
        }
        boolean z2 = true;
        if (i == -1 || list.size() <= 0 || !list.get(list.size() - 1).mFilePath.endsWith(UBIFOCUS_MAP_POSTFIX)) {
            list.clear();
            i = -1;
        }
        if (i == -1 && !list.isEmpty()) {
            z2 = false;
        }
        Utils.assertTrue(z2);
        return i;
    }

    public static List<SubFile> getUbifocusSubFiles(String str) {
        ArrayList newArrayList = Lists.newArrayList();
        getUbifocusSubFiles(str, newArrayList);
        return newArrayList;
    }

    public static boolean isOuterFile(String str) {
        return isOuterFile(str, getUbifocusPrefixIgnoreCase(str));
    }

    private static boolean isOuterFile(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (str.equals(str2 + UBIFOCUS_FILE_POSTFIX)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUbifocusImage(String str) {
        String fileName = FileUtils.getFileName(str);
        return isOuterFile(fileName, getUbifocusPrefixIgnoreCase(fileName));
    }
}
