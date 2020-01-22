package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cc A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01b3 A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb }] */
    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        TimeZone timeZone;
        char charAt;
        String str3 = str;
        ParsePosition parsePosition2 = parsePosition;
        try {
            int index = parsePosition.getIndex();
            int i7 = index + 4;
            int parseInt = parseInt(str3, index, i7);
            if (checkOffset(str3, i7, '-')) {
                i7++;
            }
            int i8 = i7 + 2;
            int parseInt2 = parseInt(str3, i7, i8);
            if (checkOffset(str3, i8, '-')) {
                i8++;
            }
            int i9 = i8 + 2;
            int parseInt3 = parseInt(str3, i8, i9);
            boolean checkOffset = checkOffset(str3, i9, 'T');
            if (checkOffset || str.length() > i9) {
                if (checkOffset) {
                    int i10 = i9 + 1;
                    int i11 = i10 + 2;
                    i4 = parseInt(str3, i10, i11);
                    if (checkOffset(str3, i11, ':')) {
                        i11++;
                    }
                    i = i11 + 2;
                    i3 = parseInt(str3, i11, i);
                    if (checkOffset(str3, i, ':')) {
                        i++;
                    }
                    if (str.length() <= i || (charAt = str3.charAt(i)) == 'Z' || charAt == '+' || charAt == '-') {
                        i5 = 0;
                    } else {
                        int i12 = i + 2;
                        int parseInt4 = parseInt(str3, i, i12);
                        i2 = (parseInt4 <= 59 || parseInt4 >= 63) ? parseInt4 : 59;
                        if (checkOffset(str3, i12, '.')) {
                            int i13 = i12 + 1;
                            i = indexOfNonDigit(str3, i13 + 1);
                            int min = Math.min(i, i13 + 3);
                            int parseInt5 = parseInt(str3, i13, min);
                            int i14 = min - i13;
                            if (i14 == 1) {
                                parseInt5 *= 100;
                            } else if (i14 == 2) {
                                parseInt5 *= 10;
                            }
                            i5 = parseInt5;
                        } else {
                            i = i12;
                            i5 = 0;
                        }
                        if (str.length() <= i) {
                            char charAt2 = str3.charAt(i);
                            if (charAt2 == 'Z') {
                                timeZone = TIMEZONE_UTC;
                                i6 = i + 1;
                            } else {
                                if (charAt2 != '+') {
                                    if (charAt2 != '-') {
                                        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + charAt2 + "'");
                                    }
                                }
                                String substring = str3.substring(i);
                                if (substring.length() < 5) {
                                    substring = substring + "00";
                                }
                                i6 = i + substring.length();
                                if (!"+0000".equals(substring)) {
                                    if (!"+00:00".equals(substring)) {
                                        String str4 = "GMT" + substring;
                                        TimeZone timeZone2 = TimeZone.getTimeZone(str4);
                                        String id = timeZone2.getID();
                                        if (!id.equals(str4)) {
                                            if (!id.replace(":", "").equals(str4)) {
                                                throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str4 + " given, resolves to " + timeZone2.getID());
                                            }
                                        }
                                        timeZone = timeZone2;
                                    }
                                }
                                timeZone = TIMEZONE_UTC;
                            }
                            GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
                            gregorianCalendar.setLenient(false);
                            gregorianCalendar.set(1, parseInt);
                            gregorianCalendar.set(2, parseInt2 - 1);
                            gregorianCalendar.set(5, parseInt3);
                            gregorianCalendar.set(11, i4);
                            gregorianCalendar.set(12, i3);
                            gregorianCalendar.set(13, i2);
                            gregorianCalendar.set(14, i5);
                            parsePosition2.setIndex(i6);
                            return gregorianCalendar.getTime();
                        }
                        throw new IllegalArgumentException("No time zone indicator");
                    }
                } else {
                    i = i9;
                    i5 = 0;
                    i4 = 0;
                    i3 = 0;
                }
                i2 = 0;
                if (str.length() <= i) {
                }
            } else {
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar(parseInt, parseInt2 - 1, parseInt3);
                parsePosition2.setIndex(i9);
                return gregorianCalendar2.getTime();
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException e) {
            if (str3 == null) {
                str2 = null;
            } else {
                str2 = '\"' + str3 + "'";
            }
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        }
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i4 = i5;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        }
        return -i3;
    }
}
