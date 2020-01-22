package com.miui.widget.picker;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.widget.R;
import com.miui.widget.picker.NumberPicker;
import com.miui.widget.util.DateDataCacheUtils;
import com.miui.widget.util.date.Calendar;
import com.miui.widget.util.date.CalendarFormatSymbols;
import com.miui.widget.util.date.DateUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

public class DatePicker extends FrameLayout {
    private static final char[] DEFALUT_DATE_FORMAT = {'y', 'M', 'd', 'H', 'm'};
    private static final Long DEFAULT_MIN_TIME = 57600000L;
    private static final String LOG_TAG = "DatePicker";
    private boolean is24Hour;
    private boolean isInit;
    private boolean isShowDay;
    private boolean isShowHour;
    private boolean isShowLabel;
    private boolean isShowMinute;
    private boolean isShowMonth;
    private boolean isShowSpinnersShown;
    private boolean isShowYear;
    /* access modifiers changed from: private */
    public Calendar mCurrentDate;
    private Locale mCurrentLocale;
    private final DateFormat mDateFormat;
    private char[] mDateFormatOrder;
    /* access modifiers changed from: private */
    public NumberPicker mDaySpinner;
    /* access modifiers changed from: private */
    public NumberPicker mHourSpinner;
    private boolean mIsEnabled;
    /* access modifiers changed from: private */
    public boolean mIsLunarMode;
    private int mItemHilightTextSize;
    private int mItemLabelSize;
    private int mItemTextSize;
    private java.util.Calendar mJavaCalendar;
    private Calendar mMaxDate;
    private long mMaxTime;
    private Calendar mMinDate;
    private long mMinTime;
    private String[] mMinuteDisplayValues;
    /* access modifiers changed from: private */
    public int mMinuteInterval;
    /* access modifiers changed from: private */
    public NumberPicker mMinuteSpinner;
    /* access modifiers changed from: private */
    public NumberPicker mMonthSpinner;
    private int mNumberOfMonths;
    private OnDateChangedListener mOnDateChangedListener;
    private NumberPicker.OnValueChangeListener mOnPickerCheckChangeListener;
    private NumberPicker[] mPickers;
    private LinearLayout mPickersContainer;
    private View mRootView;
    private String[] mShortMonths;
    private LinearLayout mSpinners;
    private long mStartTime;
    /* access modifiers changed from: private */
    public Calendar mTempDate;
    /* access modifiers changed from: private */
    public NumberPicker mYearSpinner;
    private String[] sChineseDays;
    private String sChineseLeapMonthMark;
    private String[] sChineseLeapYearMonths;
    private String[] sChineseMonths;

    public interface OnDateChangedListener {
        void onDateChanged(DatePicker datePicker, long j, boolean z);
    }

    public DatePicker(Context context) {
        this(context, (AttributeSet) null);
    }

    public DatePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.datePickerStyle);
    }

    public DatePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMinuteInterval = 1;
        this.sChineseDays = null;
        this.sChineseMonths = null;
        this.sChineseLeapYearMonths = null;
        this.sChineseLeapMonthMark = null;
        this.mDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.mIsEnabled = true;
        initData();
        setAttributes(context.obtainStyledAttributes(attributeSet, R.styleable.DatePicker, i, 0));
        this.mJavaCalendar = java.util.Calendar.getInstance();
        this.mTempDate = new Calendar();
        this.mMinDate = new Calendar();
        this.mMaxDate = new Calendar();
        this.mCurrentDate = new Calendar();
        initView(isShowYear(), isShowMonth(), isShowDay(), isShowHour(), isShowMinute());
    }

    private void applyPickerStyle(NumberPicker numberPicker) {
        boolean z;
        int i = this.mItemHilightTextSize;
        if (i > 0) {
            numberPicker.setTextHilightSize(i);
            z = true;
        } else {
            z = false;
        }
        int i2 = this.mItemLabelSize;
        if (i2 > 0) {
            numberPicker.setLableTextSize(i2);
            z = true;
        }
        int i3 = this.mItemTextSize;
        if (i3 > 0) {
            numberPicker.setTextSize(i3);
            z = true;
        }
        numberPicker.setIsShowLabel(this.isShowLabel);
        if (z) {
            numberPicker.invalidate();
        }
    }

    private void checkDisplayeValid(NumberPicker numberPicker, int i, int i2) {
        String[] displayedValues = numberPicker.getDisplayedValues();
        if (displayedValues != null && displayedValues.length < (i2 - i) + 1) {
            numberPicker.setDisplayedValues((String[]) null);
        }
    }

    private boolean checkIfTheDateIsOutOfMaxDateBounds() {
        int i = this.mIsLunarMode ? 2 : 1;
        return this.mCurrentDate.get(i) == this.mMaxDate.get(i);
    }

    private boolean checkIfTheDateIsOutOfMinDateBounds() {
        int i = this.mIsLunarMode ? 2 : 1;
        return this.mCurrentDate.get(i) == this.mMinDate.get(i);
    }

    private int computeDayCount(Calendar calendar, Calendar calendar2) {
        Calendar calendar3 = (Calendar) calendar.clone();
        Calendar calendar4 = (Calendar) calendar2.clone();
        calendar3.set(18, 0);
        calendar3.set(20, 0);
        calendar3.set(21, 0);
        calendar3.set(22, 0);
        calendar4.set(18, 0);
        calendar4.set(20, 0);
        calendar4.set(21, 0);
        calendar4.set(22, 0);
        return (int) (((((calendar3.getTimeInMillis() / 1000) / 60) / 60) / 24) - ((((calendar4.getTimeInMillis() / 1000) / 60) / 60) / 24));
    }

    private void initData() {
        if (!DateDataCacheUtils.isHaveCacheData()) {
            this.sChineseDays = CalendarFormatSymbols.getDefault().getChineseDays(getContext());
            this.sChineseMonths = CalendarFormatSymbols.getDefault().getChineseMonths(getContext());
            Resources resources = getContext().getResources();
            int i = 0;
            while (true) {
                String[] strArr = this.sChineseMonths;
                if (i < strArr.length) {
                    StringBuilder sb = new StringBuilder();
                    String[] strArr2 = this.sChineseMonths;
                    sb.append(strArr2[i]);
                    sb.append(resources.getString(R.string.chinese_month));
                    strArr2[i] = sb.toString();
                    i++;
                } else {
                    this.sChineseLeapYearMonths = new String[(strArr.length + 1)];
                    this.sChineseLeapMonthMark = CalendarFormatSymbols.getDefault().getChineseLeapMonths(getContext())[1];
                    DateDataCacheUtils.saveDateData(this.sChineseDays, this.sChineseMonths, this.sChineseLeapYearMonths, this.sChineseLeapMonthMark);
                    return;
                }
            }
        } else {
            this.sChineseDays = DateDataCacheUtils.sChineseDays;
            this.sChineseMonths = DateDataCacheUtils.sChineseMonths;
            this.sChineseLeapYearMonths = DateDataCacheUtils.sChineseLeapYearMonths;
            this.sChineseLeapMonthMark = DateDataCacheUtils.sChineseLeapMonthMark;
        }
    }

    private boolean isComputerFirstYearAndMonth(Calendar calendar) {
        int i = this.mIsLunarMode ? 6 : 5;
        int i2 = this.mIsLunarMode ? 2 : 1;
        return calendar.get(i2) == this.mMinDate.get(i2) && calendar.get(i) == this.mMinDate.get(i);
    }

    /* access modifiers changed from: private */
    public void notifyDateChanged() {
        sendAccessibilityEvent(4);
        OnDateChangedListener onDateChangedListener = this.mOnDateChangedListener;
        if (onDateChangedListener != null) {
            onDateChangedListener.onDateChanged(this, getUserSelectedTime(), this.mIsLunarMode);
        }
    }

    private void reorderSpinners() {
        this.mSpinners.removeAllViews();
        char[] cArr = this.mDateFormatOrder;
        if (cArr == null) {
            cArr = android.text.format.DateFormat.getDateFormatOrder(getContext());
        }
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            char c = cArr[i];
            if (c != 'H') {
                if (c != 'M') {
                    if (c != 'd') {
                        if (c != 'm') {
                            if (c != 'y') {
                                throw new IllegalArgumentException();
                            } else if (this.isShowYear) {
                                this.mSpinners.addView(this.mYearSpinner);
                                setImeOptions(this.mYearSpinner, length, i);
                            }
                        } else if (this.isShowMinute) {
                            this.mSpinners.addView(this.mMinuteSpinner);
                            setImeOptions(this.mMinuteSpinner, length, i);
                        }
                    } else if (this.isShowDay) {
                        this.mSpinners.addView(this.mDaySpinner);
                        setImeOptions(this.mDaySpinner, length, i);
                    }
                } else if (this.isShowMonth) {
                    this.mSpinners.addView(this.mMonthSpinner);
                    setImeOptions(this.mMonthSpinner, length, i);
                }
            } else if (this.isShowHour) {
                this.mSpinners.addView(this.mHourSpinner);
                setImeOptions(this.mHourSpinner, length, i);
            }
        }
    }

    /* access modifiers changed from: private */
    public void resetMonthsDisplayedValues() {
        int i = 0;
        if (this.mIsLunarMode) {
            int chineseLeapMonth = this.mCurrentDate.getChineseLeapMonth();
            if (chineseLeapMonth < 0) {
                this.mShortMonths = this.sChineseMonths;
                return;
            }
            this.mShortMonths = this.sChineseLeapYearMonths;
            int i2 = chineseLeapMonth + 1;
            System.arraycopy(this.sChineseMonths, 0, this.mShortMonths, 0, i2);
            String[] strArr = this.sChineseMonths;
            System.arraycopy(strArr, chineseLeapMonth, this.mShortMonths, i2, strArr.length - chineseLeapMonth);
            String[] strArr2 = this.mShortMonths;
            strArr2[i2] = this.sChineseLeapMonthMark + this.mShortMonths[i2];
        } else if ("en".equals(this.mCurrentLocale.getLanguage().toLowerCase())) {
            this.mShortMonths = CalendarFormatSymbols.getDefault().getShortMonths(getContext());
        } else {
            this.mShortMonths = new String[12];
            while (true) {
                String[] strArr3 = this.mShortMonths;
                if (i < strArr3.length) {
                    int i3 = i + 1;
                    strArr3[i] = NumberPicker.TWO_DIGIT_FORMATTER.format(i3);
                    i = i3;
                } else {
                    return;
                }
            }
        }
    }

    private void resetSpinnerToInitial(boolean z) {
        if (this.mMonthSpinner != null || this.mDaySpinner != null) {
            int i = 6;
            if (z) {
                this.mMonthSpinner.setMinValue(this.mIsLunarMode ? this.mMinDate.get(6) : this.mMinDate.get(5));
                this.mMonthSpinner.setWrapSelectorWheel(false);
                if (!this.mIsLunarMode) {
                    i = 5;
                }
                if (this.mCurrentDate.get(i) == this.mMinDate.get(i)) {
                    this.mDaySpinner.setMinValue(this.mIsLunarMode ? this.mMinDate.get(10) : this.mMinDate.get(9));
                    this.mDaySpinner.setWrapSelectorWheel(false);
                    return;
                }
                return;
            }
            this.mMonthSpinner.setMaxValue(this.mIsLunarMode ? this.mMinDate.get(6) : this.mMaxDate.get(5));
            this.mMonthSpinner.setWrapSelectorWheel(false);
            this.mMonthSpinner.setDisplayedValues((String[]) null);
            if (!this.mIsLunarMode) {
                i = 5;
            }
            if (this.mCurrentDate.get(i) == this.mMaxDate.get(i)) {
                this.mDaySpinner.setMaxValue(this.mIsLunarMode ? this.mMaxDate.get(10) : this.mMaxDate.get(9));
                this.mDaySpinner.setWrapSelectorWheel(false);
            }
        }
    }

    private void setAttributes(TypedArray typedArray) {
        this.isShowSpinnersShown = typedArray.getBoolean(R.styleable.DatePicker_spinnersShown, true);
        this.isShowYear = typedArray.getBoolean(R.styleable.DatePicker_showYear, true);
        this.isShowMonth = typedArray.getBoolean(R.styleable.DatePicker_showMonth, false);
        this.isShowDay = typedArray.getBoolean(R.styleable.DatePicker_showDay, false);
        this.isShowHour = typedArray.getBoolean(R.styleable.DatePicker_showHour, false);
        this.isShowMinute = typedArray.getBoolean(R.styleable.DatePicker_showMinute, false);
        this.mStartTime = (long) typedArray.getInteger(R.styleable.DatePicker_startTime, -1);
        this.mMaxTime = (long) typedArray.getInteger(R.styleable.DatePicker_maxTime, -1);
        this.mMinTime = (long) typedArray.getInteger(R.styleable.DatePicker_minTime, -1);
        if (-1 == this.mMinTime) {
            this.mMinTime = DEFAULT_MIN_TIME.longValue();
        }
        if (-1 == this.mMaxTime) {
            this.mMaxTime = 4102416000000L;
        }
        this.mIsLunarMode = typedArray.getBoolean(R.styleable.DatePicker_lunarCalendar, false);
        typedArray.recycle();
    }

    private void setCurrentLocale(Locale locale) {
        if (!locale.equals(this.mCurrentLocale)) {
            this.mCurrentLocale = locale;
            this.mNumberOfMonths = this.mTempDate.getActualMaximum(5) + 1;
            resetMonthsDisplayedValues();
            updateFormatters();
        }
    }

    /* access modifiers changed from: private */
    public void setDate(long j) {
        this.mCurrentDate.setTimeInMillis(j);
        if (this.mCurrentDate.before(this.mMinDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMinDate.getTimeInMillis());
        } else if (this.mCurrentDate.after(this.mMaxDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMaxDate.getTimeInMillis());
        }
    }

    private void setImeOptions(NumberPicker numberPicker, int i, int i2) {
        ((TextView) numberPicker.findViewById(R.id.numberpicker_input)).setImeOptions(i2 < i + -1 ? 5 : 6);
    }

    private void setSpinnerLableFromMode(NumberPicker numberPicker, boolean z) {
        if (z) {
            numberPicker.setLabel((String) null);
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (numberPicker.getId() == R.id.year) {
            sb.append(getContext().getString(R.string.date_picker_label_year));
        } else if (numberPicker.getId() == R.id.month) {
            sb.append(getContext().getString(R.string.date_picker_label_month));
        } else if (numberPicker.getId() == R.id.day) {
            sb.append(getContext().getString(R.string.date_picker_label_day));
        } else if (numberPicker.getId() == R.id.hour) {
            sb.append(getContext().getString(R.string.time_picker_label_hour));
        } else if (numberPicker.getId() == R.id.minute) {
            sb.append(getContext().getString(R.string.time_picker_label_minute));
        }
        numberPicker.setLabel(sb.toString());
    }

    private void updateDaySpinner() {
        NumberPicker numberPicker = this.mDaySpinner;
        if (numberPicker != null) {
            setSpinnerLableFromMode(numberPicker, isLunarMode());
            this.mDaySpinner.setDisplayedValues((String[]) null);
            int i = this.mIsLunarMode ? 10 : 9;
            if (isComputerFirstYearAndMonth(this.mCurrentDate)) {
                this.mDaySpinner.setMinValue(this.mMinDate.get(i));
            } else {
                this.mDaySpinner.setMinValue(1);
            }
            this.mDaySpinner.setMaxValue(this.mCurrentDate.getActualMaximum(i));
            this.mDaySpinner.setWrapSelectorWheel(true);
            this.mDaySpinner.setValue(this.mIsLunarMode ? this.mCurrentDate.get(10) : this.mCurrentDate.get(9));
        }
    }

    private void updateFormatters() {
        NumberPicker numberPicker = this.mDaySpinner;
        if (numberPicker != null && this.mYearSpinner != null) {
            numberPicker.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
            this.mYearSpinner.setFormatter(new NumberPicker.NumberFormatter());
        }
    }

    private void updateHourSpinner() {
        boolean z;
        int i;
        int i2;
        NumberPicker numberPicker = this.mHourSpinner;
        if (numberPicker != null) {
            setSpinnerLableFromMode(numberPicker, isLunarMode());
            Calendar calendar = this.mMaxDate;
            int i3 = 11;
            if (calendar == null || computeDayCount(this.mCurrentDate, calendar) != 0) {
                z = false;
            } else {
                if (this.is24Hour) {
                    this.mJavaCalendar.setTimeInMillis(this.mMaxDate.getTimeInMillis());
                    i2 = this.mMaxDate.get(18);
                } else {
                    i2 = this.mJavaCalendar.get(11);
                }
                this.mHourSpinner.setMaxValue(i2);
                this.mHourSpinner.setWrapSelectorWheel(false);
                z = true;
            }
            Calendar calendar2 = this.mMinDate;
            if (calendar2 != null && computeDayCount(this.mCurrentDate, calendar2) == 0) {
                if (this.is24Hour) {
                    this.mJavaCalendar.setTimeInMillis(this.mMinDate.getTimeInMillis());
                    i = this.mMinDate.get(18);
                } else {
                    i = this.mJavaCalendar.get(11);
                }
                this.mHourSpinner.setMinValue(i);
                this.mHourSpinner.setWrapSelectorWheel(false);
                z = true;
            }
            if (!z) {
                this.mHourSpinner.setMinValue(0);
                NumberPicker numberPicker2 = this.mHourSpinner;
                if (this.is24Hour) {
                    i3 = 23;
                }
                numberPicker2.setMaxValue(i3);
                this.mHourSpinner.setWrapSelectorWheel(true);
            }
            this.mHourSpinner.setValue(this.mCurrentDate.get(18));
        }
    }

    private void updateMinuteSpinner() {
        boolean z;
        NumberPicker numberPicker = this.mMinuteSpinner;
        if (numberPicker != null) {
            setSpinnerLableFromMode(numberPicker, isLunarMode());
            Calendar calendar = this.mMaxDate;
            if (calendar != null && computeDayCount(this.mCurrentDate, calendar) == 0 && this.mCurrentDate.get(18) == this.mMaxDate.get(18)) {
                int i = this.mMaxDate.get(20);
                this.mMinuteSpinner.setMinValue(0);
                this.mMinuteSpinner.setMaxValue(i / this.mMinuteInterval);
                this.mMinuteSpinner.setWrapSelectorWheel(false);
                z = true;
            } else {
                z = false;
            }
            Calendar calendar2 = this.mMinDate;
            if (calendar2 != null && computeDayCount(this.mCurrentDate, calendar2) == 0 && this.mCurrentDate.get(18) == this.mMinDate.get(18)) {
                this.mMinuteSpinner.setMinValue(this.mMinDate.get(20) / this.mMinuteInterval);
                this.mMinuteSpinner.setWrapSelectorWheel(false);
                z = true;
            }
            if (!z) {
                checkDisplayeValid(this.mMinuteSpinner, 0, (60 / this.mMinuteInterval) - 1);
                this.mMinuteSpinner.setMinValue(0);
                this.mMinuteSpinner.setMaxValue((60 / this.mMinuteInterval) - 1);
                this.mMinuteSpinner.setWrapSelectorWheel(true);
            }
            int maxValue = (this.mMinuteSpinner.getMaxValue() - this.mMinuteSpinner.getMinValue()) + 1;
            String[] strArr = this.mMinuteDisplayValues;
            if (strArr == null || strArr.length != maxValue) {
                this.mMinuteDisplayValues = new String[maxValue];
                for (int i2 = 0; i2 < maxValue; i2++) {
                    this.mMinuteDisplayValues[i2] = NumberPicker.TWO_DIGIT_FORMATTER.format((this.mMinuteSpinner.getMinValue() + i2) * this.mMinuteInterval);
                }
                this.mMinuteSpinner.setDisplayedValues(this.mMinuteDisplayValues);
            }
            this.mMinuteSpinner.setValue(this.mCurrentDate.get(20) / this.mMinuteInterval);
        }
    }

    private void updateMonthSpinner() {
        NumberPicker numberPicker = this.mMonthSpinner;
        if (numberPicker != null) {
            setSpinnerLableFromMode(numberPicker, isLunarMode());
            this.mMonthSpinner.setDisplayedValues((String[]) null);
            boolean z = false;
            this.mMonthSpinner.setMinValue(0);
            int i = isLunarMode() ? 2 : 1;
            if (this.mCurrentDate.get(i) == this.mMaxDate.get(i)) {
                this.mMonthSpinner.setMaxValue(this.mIsLunarMode ? this.mMinDate.get(6) : this.mMaxDate.get(5));
                this.mMonthSpinner.setWrapSelectorWheel(false);
                this.mMonthSpinner.setDisplayedValues((String[]) null);
                int i2 = this.mIsLunarMode ? 6 : 5;
                if (this.mCurrentDate.get(i2) == this.mMaxDate.get(i2)) {
                    this.mDaySpinner.setMaxValue(this.mIsLunarMode ? this.mMaxDate.get(10) : this.mMaxDate.get(9));
                    this.mDaySpinner.setWrapSelectorWheel(false);
                }
            } else {
                NumberPicker numberPicker2 = this.mMonthSpinner;
                int i3 = 11;
                if (this.mIsLunarMode && this.mCurrentDate.getChineseLeapMonth() >= 0) {
                    i3 = 12;
                }
                numberPicker2.setMaxValue(i3);
                this.mMonthSpinner.setWrapSelectorWheel(true);
            }
            int chineseLeapMonth = this.mCurrentDate.getChineseLeapMonth();
            if (chineseLeapMonth >= 0 && (this.mCurrentDate.isChineseLeapMonth() || this.mCurrentDate.get(6) > chineseLeapMonth)) {
                z = true;
            }
            this.mMonthSpinner.setValue(this.mIsLunarMode ? z ? this.mCurrentDate.get(6) + 1 : this.mCurrentDate.get(6) : this.mCurrentDate.get(5));
        }
    }

    /* access modifiers changed from: private */
    public void updateSpinners() {
        if (this.isShowDay) {
            if (checkIfTheDateIsOutOfMinDateBounds()) {
                resetSpinnerToInitial(true);
            } else {
                updateDaySpinner();
            }
            if (checkIfTheDateIsOutOfMaxDateBounds()) {
                resetSpinnerToInitial(false);
            } else {
                updateDaySpinner();
            }
        }
        if (this.isShowMonth) {
            updateMonthSpinner();
            this.mMonthSpinner.setDisplayedValues((String[]) Arrays.copyOfRange(this.mShortMonths, this.mMonthSpinner.getMinValue(), this.mShortMonths.length));
            if (this.mIsLunarMode) {
                this.mDaySpinner.setDisplayedValues((String[]) Arrays.copyOfRange(this.sChineseDays, this.mDaySpinner.getMinValue() - 1, this.sChineseDays.length));
            }
        }
        if (this.isShowHour) {
            updateHourSpinner();
        }
        if (this.isShowMinute) {
            updateMinuteSpinner();
        }
        updateYearSpinner();
    }

    private void updateYearSpinner() {
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker != null) {
            setSpinnerLableFromMode(numberPicker, isLunarMode());
            int i = isLunarMode() ? 2 : 1;
            this.mYearSpinner.setMinValue(this.mMinDate.get(i));
            this.mYearSpinner.setMaxValue(this.mMaxDate.get(i));
            this.mYearSpinner.setWrapSelectorWheel(false);
            this.mYearSpinner.setValue(this.mIsLunarMode ? this.mCurrentDate.get(2) : this.mCurrentDate.get(1));
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public long getCurrentSelectDate() {
        Calendar calendar = new Calendar();
        calendar.set(getYear(), getMonth(), getDayOfMonth(), getHourOfDay(), getMinuteOfHour(), 0, 0);
        return calendar.getTimeInMillis();
    }

    public int getDayOfMonth() {
        return this.mCurrentDate.get(this.mIsLunarMode ? 10 : 9);
    }

    public float getDaySpinnerWidth() {
        NumberPicker numberPicker = this.mDaySpinner;
        if (numberPicker == null || !this.isShowDay) {
            return 0.0f;
        }
        return (float) numberPicker.getMeasuredWidth();
    }

    public int getHourOfDay() {
        return this.mCurrentDate.get(this.mIsLunarMode ? 19 : 18);
    }

    public float getHourSpinnerWidth() {
        NumberPicker numberPicker = this.mHourSpinner;
        if (numberPicker == null || !this.isShowHour) {
            return 0.0f;
        }
        return (float) numberPicker.getMeasuredWidth();
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return R.layout.date_pickerview;
    }

    public long getMaxDate() {
        return this.mMaxDate.getTimeInMillis();
    }

    public long getMinDate() {
        return this.mMinDate.getTimeInMillis();
    }

    public int getMinuteOfHour() {
        return this.mCurrentDate.get(20);
    }

    public float getMinuteSpinnerWidth() {
        NumberPicker numberPicker = this.mMinuteSpinner;
        if (numberPicker == null || !this.isShowMinute) {
            return 0.0f;
        }
        return (float) numberPicker.getMeasuredWidth();
    }

    public int getMonth() {
        return this.mIsLunarMode ? this.mCurrentDate.isChineseLeapMonth() ? this.mCurrentDate.get(6) + 12 : this.mCurrentDate.get(6) : this.mCurrentDate.get(5);
    }

    public float getMonthSpinnerWidth() {
        NumberPicker numberPicker = this.mMonthSpinner;
        if (numberPicker == null || !this.isShowMonth) {
            return 0.0f;
        }
        return (float) numberPicker.getMeasuredWidth();
    }

    public boolean getSpinnersShown() {
        return this.mSpinners.isShown();
    }

    public long getUserSelectedTime() {
        Calendar calendar = this.mCurrentDate;
        if (calendar != null) {
            return calendar.getTimeInMillis();
        }
        return 0;
    }

    public int getYear() {
        return this.mCurrentDate.get(this.mIsLunarMode ? 2 : 1);
    }

    public float getYearSpinnerWidth() {
        NumberPicker numberPicker = this.mYearSpinner;
        if (numberPicker == null || !this.isShowYear) {
            return 0.0f;
        }
        return (float) numberPicker.getMeasuredWidth();
    }

    public void init(int i, int i2, int i3, OnDateChangedListener onDateChangedListener) {
        Calendar calendar = new Calendar();
        calendar.set(i, i2, i3, 0, 0, 0, 0);
        init(calendar.getTimeInMillis(), DEFAULT_MIN_TIME.longValue(), 4102416000000L, onDateChangedListener);
    }

    public void init(long j, long j2, long j3, OnDateChangedListener onDateChangedListener) {
        int i = isShowYear() ? 1 : 0;
        if (isShowMonth()) {
            i++;
        }
        if (isShowDay()) {
            i++;
        }
        if (isShowHour()) {
            i++;
        }
        if (isShowMinute()) {
            i++;
        }
        initView(isShowYear(), isShowMonth(), isShowDay(), isShowHour(), isShowMinute());
        this.mPickersContainer.setWeightSum((float) i);
        this.mPickersContainer.requestLayout();
        setMinDate(false, -1 == j2 ? this.mMinTime : j2);
        setMaxDate(false, -1 == j3 ? this.mMaxTime : j3);
        setDate(-1 == j ? this.mStartTime : j);
        setCurrentLocale(Locale.getDefault());
        updateSpinners();
        this.mOnDateChangedListener = onDateChangedListener;
        setDateFormatOrder(DEFALUT_DATE_FORMAT);
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    /* access modifiers changed from: protected */
    public void initView(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (this.mRootView == null) {
            this.mRootView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(getLayoutId(), this, true);
            this.mPickersContainer = (LinearLayout) this.mRootView.findViewById(R.id.pickers);
            this.mOnPickerCheckChangeListener = new NumberPicker.OnValueChangeListener() {
                public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                    DatePicker.this.mTempDate.setTimeInMillis(DatePicker.this.mCurrentDate.getTimeInMillis());
                    if (numberPicker == DatePicker.this.mDaySpinner) {
                        DatePicker.this.mTempDate.add(DatePicker.this.mIsLunarMode ? 10 : 9, i2 - i);
                    } else if (numberPicker == DatePicker.this.mMonthSpinner) {
                        DatePicker.this.mTempDate.add(DatePicker.this.mIsLunarMode ? 6 : 5, i2 - i);
                    } else if (numberPicker == DatePicker.this.mYearSpinner) {
                        DatePicker.this.mTempDate.set(DatePicker.this.mIsLunarMode ? 2 : 1, i2);
                    } else if (numberPicker == DatePicker.this.mHourSpinner) {
                        DatePicker.this.mTempDate.set(DatePicker.this.mIsLunarMode ? 19 : 18, DatePicker.this.mHourSpinner.getValue());
                    } else if (numberPicker == DatePicker.this.mMinuteSpinner) {
                        DatePicker.this.mTempDate.set(20, DatePicker.this.mMinuteInterval * DatePicker.this.mMinuteSpinner.getValue());
                    } else {
                        throw new IllegalArgumentException();
                    }
                    DatePicker datePicker = DatePicker.this;
                    datePicker.setDate(datePicker.mTempDate.getTimeInMillis());
                    if (numberPicker == DatePicker.this.mYearSpinner) {
                        DatePicker.this.resetMonthsDisplayedValues();
                    }
                    DatePicker.this.updateSpinners();
                    DatePicker.this.notifyDateChanged();
                }
            };
            this.mSpinners = (LinearLayout) findViewById(R.id.pickers);
        }
        if (this.isShowSpinnersShown) {
            if (this.mPickers == null) {
                this.mPickers = new NumberPicker[5];
            }
            if (z && this.mYearSpinner == null) {
                this.mYearSpinner = (NumberPicker) ((ViewStub) findViewById(R.id.year)).inflate();
                this.mYearSpinner.setOnLongPressUpdateInterval(100);
                this.mYearSpinner.setOnValueChangedListener(this.mOnPickerCheckChangeListener);
                this.mPickers[0] = this.mYearSpinner;
            }
            if (z2 && this.mMonthSpinner == null) {
                this.mMonthSpinner = (NumberPicker) ((ViewStub) findViewById(R.id.month)).inflate();
                this.mMonthSpinner.setDisplayedValues(this.mShortMonths);
                this.mMonthSpinner.setOnLongPressUpdateInterval(200);
                this.mMonthSpinner.setOnValueChangedListener(this.mOnPickerCheckChangeListener);
                this.mPickers[1] = this.mMonthSpinner;
            }
            if (z3 && this.mDaySpinner == null) {
                this.mDaySpinner = (NumberPicker) ((ViewStub) findViewById(R.id.day)).inflate();
                this.mDaySpinner.setOnLongPressUpdateInterval(100);
                this.mDaySpinner.setOnValueChangedListener(this.mOnPickerCheckChangeListener);
                this.mPickers[2] = this.mDaySpinner;
            }
            if (z4 && this.mHourSpinner == null) {
                this.mHourSpinner = (NumberPicker) ((ViewStub) findViewById(R.id.hour)).inflate();
                this.mHourSpinner.setOnLongPressUpdateInterval(100);
                this.mHourSpinner.setOnValueChangedListener(this.mOnPickerCheckChangeListener);
                this.mPickers[3] = this.mHourSpinner;
            }
            if (z5 && this.mMinuteSpinner == null) {
                this.mMinuteSpinner = (NumberPicker) ((ViewStub) findViewById(R.id.minute)).inflate();
                this.mMinuteSpinner.setOnLongPressUpdateInterval(100);
                this.mMinuteSpinner.setOnValueChangedListener(this.mOnPickerCheckChangeListener);
            }
            NumberPicker numberPicker = this.mYearSpinner;
            if (numberPicker != null) {
                applyPickerStyle(numberPicker);
            }
            NumberPicker numberPicker2 = this.mMonthSpinner;
            if (numberPicker2 != null) {
                applyPickerStyle(numberPicker2);
            }
            NumberPicker numberPicker3 = this.mDaySpinner;
            if (numberPicker3 != null) {
                applyPickerStyle(numberPicker3);
            }
            NumberPicker numberPicker4 = this.mHourSpinner;
            if (numberPicker4 != null) {
                applyPickerStyle(numberPicker4);
            }
            NumberPicker numberPicker5 = this.mMinuteSpinner;
            if (numberPicker5 != null) {
                applyPickerStyle(numberPicker5);
            }
            setSpinnersShown(this.isShowSpinnersShown);
            updateFormatters();
            long j = this.mStartTime;
            if (-1 != j) {
                this.mCurrentDate.setTimeInMillis(j);
                return;
            }
            this.mStartTime = System.currentTimeMillis();
            this.mCurrentDate.setTimeInMillis(this.mStartTime);
        }
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean isInit() {
        return this.isInit;
    }

    public boolean isLunarMode() {
        return this.mIsLunarMode;
    }

    public boolean isShowDay() {
        return this.isShowDay;
    }

    public boolean isShowHour() {
        return this.isShowHour;
    }

    public boolean isShowMinute() {
        return this.isShowMinute;
    }

    public boolean isShowMonth() {
        return this.isShowMonth;
    }

    public boolean isShowYear() {
        return this.isShowYear;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(DatePicker.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DatePicker.class.getName());
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.mCurrentDate.getTimeInMillis(), 896));
    }

    public void setDateFormatOrder(char[] cArr) {
        this.mDateFormatOrder = cArr;
        reorderSpinners();
    }

    public void setEnable24Hour(boolean z) {
        this.is24Hour = z;
    }

    public void setEnabled(boolean z) {
        if (this.mIsEnabled != z) {
            super.setEnabled(z);
            NumberPicker numberPicker = this.mDaySpinner;
            if (numberPicker != null) {
                numberPicker.setEnabled(z);
            }
            NumberPicker numberPicker2 = this.mMonthSpinner;
            if (numberPicker2 != null) {
                numberPicker2.setEnabled(z);
            }
            NumberPicker numberPicker3 = this.mYearSpinner;
            if (numberPicker3 != null) {
                numberPicker3.setEnabled(z);
            }
            this.mIsEnabled = z;
        }
    }

    public void setIsShowLabel(boolean z) {
        this.isShowLabel = z;
    }

    public void setLunarMode(boolean z) {
        if (z != this.mIsLunarMode) {
            this.mIsLunarMode = z;
            resetMonthsDisplayedValues();
            updateSpinners();
        }
    }

    public void setMaxDate(long j) {
        setMaxDate(true, j);
    }

    public void setMaxDate(boolean z, long j) {
        this.mTempDate.setTimeInMillis(j);
        if (this.mTempDate.get(1) != this.mMaxDate.get(1) || this.mTempDate.get(12) == this.mMaxDate.get(12)) {
            this.mMaxDate.setTimeInMillis(j);
            if (this.mCurrentDate.after(this.mMaxDate)) {
                this.mCurrentDate.setTimeInMillis(this.mMaxDate.getTimeInMillis());
            }
            if (z) {
                updateSpinners();
            }
        }
    }

    public void setMinDate(long j) {
        setMinDate(true, j);
    }

    public void setMinDate(boolean z, long j) {
        this.mTempDate.setTimeInMillis(j);
        if (this.mTempDate.get(1) != this.mMinDate.get(1) || this.mTempDate.get(12) == this.mMinDate.get(12)) {
            this.mMinDate.setTimeInMillis(j);
            if (this.mCurrentDate.before(this.mMinDate)) {
                this.mCurrentDate.setTimeInMillis(this.mMinDate.getTimeInMillis());
            }
            if (z) {
                updateSpinners();
            }
        }
    }

    public void setPickerItemTextStyle(int i, int i2, int i3) {
        this.mItemHilightTextSize = i2;
        this.mItemLabelSize = i3;
        this.mItemTextSize = i;
    }

    public void setShowDay(boolean z) {
        this.isShowDay = z;
    }

    public void setShowHour(boolean z) {
        this.isShowHour = z;
    }

    public void setShowMinute(boolean z) {
        this.isShowMinute = z;
    }

    public void setShowMonth(boolean z) {
        this.isShowMonth = z;
    }

    public void setSpinnersShown(boolean z) {
        LinearLayout linearLayout = this.mSpinners;
        if (linearLayout != null) {
            linearLayout.setVisibility(z ? 0 : 8);
        }
    }
}
