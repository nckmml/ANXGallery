package com.miui.gallery.data;

import android.content.Context;
import android.location.Address;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.util.Locale;

public class LocationUtil {
    private static int INT_COORDINATE_FACTOR = 1000000;

    public static String convertDoubleToLaLon(double d) {
        long j = (long) d;
        double d2 = d - ((double) j);
        long j2 = (long) (d2 * 60.0d);
        long round = Math.round((d2 - (((double) j2) / 60.0d)) * 3600.0d * 1.0E7d);
        return j + "/1," + j2 + "/1," + round + "/10000000";
    }

    public static int convertIntLat(double d) {
        return (int) (d * ((double) INT_COORDINATE_FACTOR));
    }

    public static double convertRationalLatLonToDouble(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            String[] split = str.split(",");
            String[] split2 = split[0].split("/");
            double parseDouble = Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim());
            String[] split3 = split[1].split("/");
            double parseDouble2 = Double.parseDouble(split3[0].trim()) / Double.parseDouble(split3[1].trim());
            String[] split4 = split[2].split("/");
            double parseDouble3 = parseDouble + (parseDouble2 / 60.0d) + ((Double.parseDouble(split4[0].trim()) / Double.parseDouble(split4[1].trim())) / 3600.0d);
            return (TextUtils.isEmpty(str2) || (!str2.equals("S") && !str2.equals("W"))) ? parseDouble3 : -parseDouble3;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
            return 0.0d;
        }
    }

    public static String getCityNameFromRes(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int identifier = context.getResources().getIdentifier("cityname_" + str, "string", context.getPackageName());
        if (identifier != 0) {
            return context.getResources().getString(identifier);
        }
        Log.e("LocationUtil", "cannot find a res id for %s", (Object) str);
        return null;
    }

    public static Address getInvalidAddress() {
        Address address = new Address((Locale) null);
        Bundle bundle = new Bundle(2);
        bundle.putBoolean("invalid_key", true);
        address.setExtras(bundle);
        return address;
    }

    public static boolean isInvalidAddress(Address address) {
        if (address == null) {
            return true;
        }
        return address.getExtras() != null && address.getExtras().getBoolean("invalid_key");
    }

    public static boolean isLocationValidate(String str) {
        return !TextUtils.isEmpty(str) && !MovieStatUtils.DOWNLOAD_FAILED.equals(str) && !"-2".equals(str);
    }

    public static boolean isValidateCoordinate(double d, double d2) {
        return !MiscUtil.doubleEquals(d, 0.0d) && !MiscUtil.doubleEquals(d2, 0.0d);
    }
}
