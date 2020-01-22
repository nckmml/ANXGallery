package com.miui.gallery.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.gallery.R;

public abstract class TimeLineGridHeaderItem extends LinearLayout implements CheckableView {
    protected TextView mDateTime;
    protected TextView mLocation;
    private SelectGroupHelper mSelectGroupOrNot;

    private static class SelectGroupHelper {
        private boolean isChecked;
        private Context mContext;
        private View.OnClickListener mSelectClickListener;
        private ViewStub mSelectStub;
        private View mSelectView;

        SelectGroupHelper(Context context, ViewStub viewStub) {
            this.mContext = context;
            this.mSelectStub = viewStub;
        }

        /* access modifiers changed from: package-private */
        public void setCheckable(boolean z) {
            if (z) {
                if (this.mSelectView == null) {
                    this.mSelectView = this.mSelectStub.inflate();
                    setCheckableListener(this.mSelectClickListener);
                    setChecked(this.isChecked);
                }
                if (this.mSelectView.getBackground() == null) {
                    this.mSelectView.setBackgroundResource(R.drawable.select_all_button);
                }
                this.mSelectView.setVisibility(0);
                return;
            }
            View view = this.mSelectView;
            if (view != null) {
                view.setVisibility(8);
            }
        }

        /* access modifiers changed from: package-private */
        public void setCheckableListener(View.OnClickListener onClickListener) {
            View view = this.mSelectView;
            if (view == null) {
                this.mSelectClickListener = onClickListener;
            } else {
                view.setOnClickListener(onClickListener);
            }
        }

        /* access modifiers changed from: package-private */
        public void setChecked(boolean z) {
            View view = this.mSelectView;
            if (view == null) {
                this.isChecked = z;
            } else {
                ((TextView) view).setText(this.mContext.getString(z ? miui.R.string.deselect_all : miui.R.string.select_all));
            }
        }
    }

    public TimeLineGridHeaderItem(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimeLineGridHeaderItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeLineGridHeaderItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutDirection(3);
    }

    public void bindData(String str, String str2) {
        this.mDateTime.setText(str);
        if (!TextUtils.isEmpty(str2)) {
            this.mLocation.setText(str2);
            this.mLocation.setVisibility(0);
            return;
        }
        this.mLocation.setVisibility(4);
    }

    public boolean isChecked() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mDateTime = (TextView) findViewById(R.id.datetime);
        this.mLocation = (TextView) findViewById(R.id.location);
        this.mSelectGroupOrNot = new SelectGroupHelper(getContext(), (ViewStub) findViewById(R.id.select_stub));
    }

    public void setCheckable(boolean z) {
        this.mSelectGroupOrNot.setCheckable(z);
    }

    public void setCheckableListener(View.OnClickListener onClickListener) {
        this.mSelectGroupOrNot.setCheckableListener(onClickListener);
    }

    public void setChecked(boolean z) {
        this.mSelectGroupOrNot.setChecked(z);
    }

    public void toggle() {
    }
}
