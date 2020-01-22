package com.miui.gallery.util;

import android.content.Context;
import com.miui.gallery.R;
import miui.text.ExtraTextUtils;

public class FormatUtil {
    public static String formatDuration(Context context, int i) {
        int i2 = i / 3600;
        int i3 = i2 * 3600;
        int i4 = (i - i3) / 60;
        int i5 = i - (i3 + (i4 * 60));
        if (i2 == 0) {
            return String.format(context.getString(R.string.detail_ms), new Object[]{Integer.valueOf(i4), Integer.valueOf(i5)});
        }
        return String.format(context.getString(R.string.detail_hms), new Object[]{Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    public static String formatFileSize(Context context, long j) {
        return ExtraTextUtils.formatFileSize(context, j);
    }

    public static int formatMinutes(long j) {
        return (int) Math.ceil((double) ((((float) j) * 1.0f) / 60000.0f));
    }

    public static String formatVideoDuration(long j) {
        long j2;
        long j3;
        if (j < 0) {
            return null;
        }
        if (j >= 3600) {
            j2 = j / 3600;
            j -= 3600 * j2;
        } else {
            j2 = 0;
        }
        if (j >= 60) {
            j3 = j / 60;
            j -= 60 * j3;
        } else {
            j3 = 0;
        }
        StringBuilder sb = new StringBuilder();
        if (j2 > 0) {
            sb.append(String.format("%d", new Object[]{Long.valueOf(j2)}));
            sb.append(":");
        }
        sb.append(String.format("%d", new Object[]{Long.valueOf(j3)}));
        sb.append(":");
        if (j < 10) {
            sb.append(String.format("%d", new Object[]{0}));
        }
        sb.append(String.format("%d", new Object[]{Long.valueOf(j)}));
        return sb.toString();
    }
}
