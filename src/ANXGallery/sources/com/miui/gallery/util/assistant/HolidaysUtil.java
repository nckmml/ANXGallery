package com.miui.gallery.util.assistant;

import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import miui.date.Calendar;

public class HolidaysUtil {
    private static final int[][] CHINESE_CALENDAR_HOLIDAYS = {new int[]{-1, 100}, new int[]{101, 101}, new int[]{115, 102}, new int[]{505, 103}, new int[]{707, 104}, new int[]{815, 105}};
    private static final int[][] HOLIDAYS = {new int[]{101, 200}, new int[]{214, 201}, new int[]{501, 202}, new int[]{601, 203}, new int[]{1001, 204}, new int[]{1225, 205}};

    private static int daysInChineseYear(Calendar calendar) {
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.set(2, calendar.get(2) + 1);
        calendar2.set(6, 0);
        calendar2.set(10, 1);
        calendar2.setTimeInMillis(calendar2.getTimeInMillis() - 86400000);
        return calendar2.get(13);
    }

    public static int getChineseHoliday(long j) {
        Calendar calendar = new Calendar();
        calendar.setTimeInMillis(j);
        return getChineseHoliday(calendar);
    }

    public static int getChineseHoliday(Calendar calendar) {
        if (!calendar.outOfChineseCalendarRange()) {
            int i = ((calendar.get(6) + 1) * 100) + calendar.get(10);
            for (int[] iArr : CHINESE_CALENDAR_HOLIDAYS) {
                if (iArr[0] != -1) {
                    if (!calendar.isChineseLeapMonth() && i == iArr[0]) {
                        return iArr[1];
                    }
                } else if (daysInChineseYear(calendar) == calendar.get(13)) {
                    return iArr[1];
                }
            }
        }
        return -1;
    }

    public static long getChineseHolidayDatetimeOfPastYear(Calendar calendar, int i) {
        if (getChineseHoliday(calendar) == -1) {
            return -1;
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.set(2, calendar.get(2) - i);
        int daysInChineseYear = daysInChineseYear(calendar2);
        if (daysInChineseYear == calendar.get(13)) {
            calendar2.set(13, daysInChineseYear);
        }
        calendar2.set(18, 0);
        calendar2.set(20, 0);
        calendar2.set(21, 0);
        calendar2.set(22, 0);
        return calendar2.getTimeInMillis();
    }

    public static String getChineseHolidayNameForStory(int i, int i2) {
        int i3 = i2 - 100;
        if (i > 1) {
            String[] stringArray = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.chinese_holiday_past_year);
            return (stringArray.length <= 0 || stringArray.length <= i3) ? "" : stringArray[i3];
        }
        String[] stringArray2 = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.chinese_holiday_last_year);
        return (stringArray2.length <= 0 || stringArray2.length <= i3) ? "" : stringArray2[i3];
    }

    public static int getHoliday(long j) {
        Calendar calendar = new Calendar();
        calendar.setTimeInMillis(j);
        return getHoliday(calendar);
    }

    public static int getHoliday(Calendar calendar) {
        int i = ((calendar.get(5) + 1) * 100) + calendar.get(9);
        for (int[] iArr : HOLIDAYS) {
            if (iArr[0] == i) {
                return iArr[1];
            }
        }
        return -1;
    }

    public static String getHolidayNameForStory(int i, int i2) {
        int i3 = i2 - 200;
        if (i > 1) {
            String[] stringArray = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.holiday_past_year);
            return (stringArray.length <= 0 || stringArray.length <= i3) ? "" : stringArray[i3];
        }
        String[] stringArray2 = GalleryApp.sGetAndroidContext().getResources().getStringArray(R.array.holiday_last_year);
        return (stringArray2.length <= 0 || stringArray2.length <= i3) ? "" : stringArray2[i3];
    }
}
