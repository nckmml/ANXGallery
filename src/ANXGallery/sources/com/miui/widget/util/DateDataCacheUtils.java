package com.miui.widget.util;

public class DateDataCacheUtils {
    public static String[] sChineseDays;
    public static String sChineseLeapMonthMark;
    public static String[] sChineseLeapYearMonths;
    public static String[] sChineseMonths;

    public static boolean isHaveCacheData() {
        return (sChineseDays == null || sChineseMonths == null || sChineseLeapMonthMark == null) ? false : true;
    }

    public static void saveDateData(String[] strArr, String[] strArr2, String[] strArr3, String str) {
        sChineseDays = strArr;
        sChineseMonths = strArr2;
        sChineseLeapYearMonths = new String[(sChineseMonths.length + 1)];
        sChineseLeapMonthMark = str;
    }
}
