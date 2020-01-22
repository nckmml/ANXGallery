package com.miui.widget.util.date;

import android.content.Context;
import com.miui.widget.R;
import com.miui.widget.util.SoftReferenceSingleton;
import java.util.Locale;

public class CalendarFormatSymbols {
    private static final SoftReferenceSingleton<CalendarFormatSymbols> INSTANCE = new SoftReferenceSingleton<CalendarFormatSymbols>() {
        /* access modifiers changed from: protected */
        public CalendarFormatSymbols createInstance() {
            return new CalendarFormatSymbols();
        }
    };

    public static CalendarFormatSymbols getDefault() {
        return INSTANCE.get();
    }

    public String[] getAmPms(Context context) {
        return context.getResources().getStringArray(R.array.am_pms);
    }

    public String[] getChineseDays(Context context) {
        return context.getResources().getStringArray(R.array.chinese_days);
    }

    public String[] getChineseDigits(Context context) {
        return context.getResources().getStringArray(R.array.chinese_digits);
    }

    public String[] getChineseLeapMonths(Context context) {
        return context.getResources().getStringArray(R.array.chinese_leap_months);
    }

    public String[] getChineseMonths(Context context) {
        return context.getResources().getStringArray(R.array.chinese_months);
    }

    public String[] getChineseSymbolAnimals(Context context) {
        return context.getResources().getStringArray(R.array.chinese_symbol_animals);
    }

    public String[] getDetailedAmPms(Context context) {
        return context.getResources().getStringArray(R.array.detailed_am_pms);
    }

    public String[] getEarthlyBranches(Context context) {
        return context.getResources().getStringArray(R.array.earthly_branches);
    }

    public String[] getEras(Context context) {
        return context.getResources().getStringArray(R.array.eras);
    }

    public String[] getHeavenlyStems(Context context) {
        return context.getResources().getStringArray(R.array.heavenly_stems);
    }

    public Locale getLocale() {
        return Locale.getDefault();
    }

    public String[] getMonths(Context context) {
        return context.getResources().getStringArray(R.array.months);
    }

    public String[] getShortMonths(Context context) {
        return context.getResources().getStringArray(R.array.months_short);
    }

    public String[] getShortWeekDays(Context context) {
        return context.getResources().getStringArray(R.array.week_days_short);
    }

    public String[] getShortestMonths(Context context) {
        return context.getResources().getStringArray(R.array.months_shortest);
    }

    public String[] getShortestWeekDays(Context context) {
        return context.getResources().getStringArray(R.array.week_days_shortest);
    }

    public String[] getSolarTerms(Context context) {
        return context.getResources().getStringArray(R.array.solar_terms);
    }

    public String[] getWeekDays(Context context) {
        return context.getResources().getStringArray(R.array.week_days);
    }
}
