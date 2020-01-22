package com.miui.widget.picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.widget.R;
import com.miui.widget.picker.DatePicker;
import com.miui.widget.util.date.DateUtils;

public class DateTimePickerDialog extends AlertDialog {
    private boolean is24Hour;
    private boolean isShowDay;
    private boolean isShowHour;
    private boolean isShowLabel;
    private boolean isShowMinute;
    private boolean isShowMonth;
    private OnDateSetListener mCallBack;
    private DatePicker mDatePicker;
    private int mItemHilightTextSize;
    private int mItemLabelSize;
    private int mItemTextSize;
    private long mMaxTime;
    private long mMinTime;
    private int mNegativeColor;
    private DatePicker.OnDateChangedListener mOnDateChangedListener;
    /* access modifiers changed from: private */
    public OnShowListener mOnShowListener;
    private int mPositiveColor;
    private View mRootView;
    private long mStartTime;
    /* access modifiers changed from: private */
    public boolean mTitleNeedsUpdate;
    private View mTitleView;

    public static class Builder {
        private boolean is24Hour;
        private boolean isShowDay;
        private boolean isShowHour;
        private boolean isShowLabel;
        private boolean isShowMinute;
        private boolean isShowMonth;
        private Context mContext;
        private int mItemHilightTextSize;
        private int mItemLabelSize;
        private int mItemTextSize;
        private OnDateSetListener mListener;
        private long mMaxTime = -1;
        private long mMinTime = -1;
        private int mNegativeColor = -1;
        private int mPositiveColor = -1;
        private long mStartTime = -1;
        private int mTitleResId;
        private View mTitleView;

        public Builder(Context context) {
            this.mContext = context;
        }

        public DateTimePickerDialog create() {
            DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this.mContext, this.mListener);
            dateTimePickerDialog.setMinTime(this.mMinTime);
            dateTimePickerDialog.setMaxTime(this.mMaxTime);
            dateTimePickerDialog.setStartTime(this.mStartTime);
            dateTimePickerDialog.setShowDay(this.isShowDay);
            dateTimePickerDialog.setShowHour(this.isShowHour);
            dateTimePickerDialog.setShowMinute(this.isShowMinute);
            dateTimePickerDialog.setShowMonth(this.isShowMonth);
            if (this.mTitleResId != 0) {
                this.mTitleView = LayoutInflater.from(this.mContext).inflate(this.mTitleResId, (ViewGroup) null);
            }
            View view = this.mTitleView;
            if (view != null) {
                dateTimePickerDialog.setCustomTitle(view);
            }
            dateTimePickerDialog.setNegativeColor(this.mNegativeColor);
            dateTimePickerDialog.setPositiveColor(this.mPositiveColor);
            dateTimePickerDialog.setEnable24Hour(this.is24Hour);
            dateTimePickerDialog.setItemTextSize(this.mItemTextSize);
            dateTimePickerDialog.setItemHilightTextSize(this.mItemHilightTextSize);
            dateTimePickerDialog.setItemLabelSize(this.mItemLabelSize);
            dateTimePickerDialog.setIsShowLabel(this.isShowLabel);
            dateTimePickerDialog.init();
            return dateTimePickerDialog;
        }

        public Builder enable24Hour() {
            this.is24Hour = true;
            return this;
        }

        public Builder setIsShowLabel(boolean z) {
            this.isShowLabel = z;
            return this;
        }

        public Builder setMaxTime(long j) {
            this.mMaxTime = j;
            return this;
        }

        public Builder setOnDateSetListener(OnDateSetListener onDateSetListener) {
            this.mListener = onDateSetListener;
            return this;
        }

        public Builder setPickerItemLabelSize(int i) {
            this.mItemLabelSize = i;
            return this;
        }

        public Builder setStartTime(long j) {
            this.mStartTime = j;
            return this;
        }

        public Builder setTitleView(int i) {
            this.mTitleResId = i;
            return this;
        }

        public Builder showHour() {
            this.isShowDay = true;
            this.isShowMonth = true;
            this.isShowHour = true;
            return this;
        }
    }

    public interface OnDateSetListener {
        void onDateSet(DateTimePickerDialog dateTimePickerDialog, int i, int i2, int i3, int i4, int i5, long j);

        void onSelectChange(DateTimePickerDialog dateTimePickerDialog, String str, long j);
    }

    public interface OnShowListener {
        void onShow(DialogInterface dialogInterface, AlertDialog alertDialog);
    }

    protected DateTimePickerDialog(Context context, OnDateSetListener onDateSetListener) {
        this(context, onDateSetListener, -1, -1, -1);
    }

    protected DateTimePickerDialog(Context context, OnDateSetListener onDateSetListener, long j, long j2, long j3) {
        super(context, 0);
        this.mTitleNeedsUpdate = true;
        this.mOnDateChangedListener = new DatePicker.OnDateChangedListener() {
            public void onDateChanged(DatePicker datePicker, long j, boolean z) {
                if (DateTimePickerDialog.this.mTitleNeedsUpdate) {
                    DateTimePickerDialog.this.updateTitle(j);
                }
            }
        };
        this.mCallBack = onDateSetListener;
        Context context2 = getContext();
        this.mRootView = ((LayoutInflater) context2.getSystemService("layout_inflater")).inflate(R.layout.date_pickerview_dialog, (ViewGroup) null);
        this.mDatePicker = (DatePicker) this.mRootView.findViewById(R.id.datePicker);
        setView(this.mRootView);
        setButton(-1, context2.getText(17039370), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DateTimePickerDialog.this.tryNotifyDateSet();
            }
        });
        setButton(-2, getContext().getText(17039360), (DialogInterface.OnClickListener) null);
        super.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                DateTimePickerDialog.this.configButtonTextColor();
                if (DateTimePickerDialog.this.mOnShowListener != null) {
                    DateTimePickerDialog.this.mOnShowListener.onShow(dialogInterface, DateTimePickerDialog.this);
                }
            }
        });
        setIcon(0);
        if (-1 == j) {
            this.mStartTime = System.currentTimeMillis();
        }
    }

    /* access modifiers changed from: private */
    public void configButtonTextColor() {
        if (-1 != getNegativeColor()) {
            getButton(-2).setTextColor(getNegativeColor());
        }
        if (-1 != getPositiveColor()) {
            getButton(-1).setTextColor(getPositiveColor());
        }
    }

    /* access modifiers changed from: private */
    public void init() {
        DatePicker datePicker = this.mDatePicker;
        if (datePicker != null && !datePicker.isInit()) {
            this.mDatePicker.setShowDay(isShowDay());
            this.mDatePicker.setShowHour(isShowHour());
            this.mDatePicker.setShowMonth(isShowMonth());
            this.mDatePicker.setShowMinute(isShowMinute());
            this.mDatePicker.setEnable24Hour(this.is24Hour);
            this.mDatePicker.setPickerItemTextStyle(getItemTextSize(), getItemHilightTextSize(), getItemLabelSize());
            this.mDatePicker.setIsShowLabel(this.isShowLabel);
            this.mDatePicker.init(this.mStartTime, this.mMinTime, this.mMaxTime, this.mOnDateChangedListener);
        }
    }

    /* access modifiers changed from: private */
    public void setIsShowLabel(boolean z) {
        this.isShowLabel = z;
    }

    /* access modifiers changed from: private */
    public void tryNotifyDateSet() {
        if (getCallBack() != null) {
            this.mDatePicker.clearFocus();
            getCallBack().onDateSet(this, this.mDatePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth(), this.mDatePicker.getHourOfDay(), this.mDatePicker.getMinuteOfHour(), this.mDatePicker.getCurrentSelectDate());
        }
    }

    /* access modifiers changed from: private */
    public void updateTitle(long j) {
        if (this.mTitleView == null) {
            super.setTitle(calculateTitle(j));
        } else if (isShowing()) {
            getCallBack().onSelectChange(this, calculateTitle(j), j);
        }
    }

    public String calculateTitle(long j) {
        int i = this.mDatePicker.isShowHour() ? 1928 : 1920;
        if (this.mDatePicker.isShowMinute()) {
            i |= 4;
        }
        return DateUtils.formatDateTime(getContext(), j, i | (this.is24Hour ? 32 : 16));
    }

    public OnDateSetListener getCallBack() {
        return this.mCallBack;
    }

    public int getItemHilightTextSize() {
        return this.mItemHilightTextSize;
    }

    public int getItemLabelSize() {
        return this.mItemLabelSize;
    }

    public int getItemTextSize() {
        return this.mItemTextSize;
    }

    public int getNegativeColor() {
        return this.mNegativeColor;
    }

    public int getPositiveColor() {
        return this.mPositiveColor;
    }

    public View getTitleView() {
        return this.mTitleView;
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

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDatePicker = null;
        this.mCallBack = null;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mDatePicker.init(bundle.getInt("miui:year"), bundle.getInt("miui:month"), bundle.getInt("miui:day"), this.mOnDateChangedListener);
    }

    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putInt("miui:year", this.mDatePicker.getYear());
        onSaveInstanceState.putInt("miui:month", this.mDatePicker.getMonth());
        onSaveInstanceState.putInt("miui:day", this.mDatePicker.getDayOfMonth());
        return onSaveInstanceState;
    }

    public void setCustomTitle(View view) {
        super.setCustomTitle(view);
        this.mTitleView = view;
    }

    public void setEnable24Hour(boolean z) {
        this.is24Hour = z;
    }

    public void setItemHilightTextSize(int i) {
        this.mItemHilightTextSize = i;
    }

    public void setItemLabelSize(int i) {
        this.mItemLabelSize = i;
    }

    public void setItemTextSize(int i) {
        this.mItemTextSize = i;
    }

    public void setMaxTime(long j) {
        this.mMaxTime = j;
    }

    public void setMinTime(long j) {
        this.mMinTime = j;
    }

    public void setNegativeColor(int i) {
        this.mNegativeColor = i;
    }

    public void setOnShowListener(OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    public void setPositiveColor(int i) {
        this.mPositiveColor = i;
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

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public void setTitle(int i) {
        super.setTitle(i);
        this.mTitleNeedsUpdate = false;
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mTitleNeedsUpdate = false;
    }

    public void show() {
        super.show();
        updateTitle(this.mStartTime);
    }
}
