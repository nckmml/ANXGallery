package com.miui.gallery.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.gallery.R;

public class EmptyPage extends RelativeLayout {
    private Button mActionButton;
    private TextView mBigTitle;
    private TextView mDescription;
    private ImageView mIcon;
    private TextView mTitle;
    private View mTopDividerLine;

    public static class EmptyConfig {
        /* access modifiers changed from: private */
        public boolean isActionButtonClickable;
        /* access modifiers changed from: private */
        public boolean isShowActionButton;
        /* access modifiers changed from: private */
        public Drawable mActionButtonBackgroundDrawable;
        /* access modifiers changed from: private */
        public View.OnClickListener mActionButtonClickListener;
        /* access modifiers changed from: private */
        public CharSequence mActionButtonTitle;
        private int mBackgroundId;
        /* access modifiers changed from: private */
        public CharSequence mBigTitle;
        /* access modifiers changed from: private */
        public int mBigTitleResId;
        /* access modifiers changed from: private */
        public CharSequence mDescriptionText;
        /* access modifiers changed from: private */
        public int mDescriptionTextResId;
        /* access modifiers changed from: private */
        public Drawable mIconDrawable;
        /* access modifiers changed from: private */
        public int mIconResId;
        /* access modifiers changed from: private */
        public CharSequence mTitle;
        /* access modifiers changed from: private */
        public int mTitleResId;

        public EmptyConfig disableActionButton() {
            this.isShowActionButton = false;
            return this;
        }

        public EmptyConfig setBackground(int i) {
            this.mBackgroundId = i;
            return this;
        }

        public EmptyConfig setIcon(int i) {
            this.mIconResId = i;
            return this;
        }

        public EmptyConfig setTitle(int i) {
            this.mTitleResId = i;
            return this;
        }
    }

    public EmptyPage(Context context) {
        this(context, (AttributeSet) null);
    }

    public EmptyPage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmptyPage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    /* JADX INFO: finally extract failed */
    private void init(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.EmptyPage);
            try {
                Drawable drawable = obtainStyledAttributes.getDrawable(3);
                CharSequence text = obtainStyledAttributes.getText(4);
                CharSequence text2 = obtainStyledAttributes.getText(2);
                Drawable drawable2 = obtainStyledAttributes.getDrawable(0);
                CharSequence text3 = obtainStyledAttributes.getText(1);
                boolean z = obtainStyledAttributes.getBoolean(5, true);
                boolean z2 = obtainStyledAttributes.getBoolean(6, false);
                obtainStyledAttributes.recycle();
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.empty_page, this);
                this.mIcon = (ImageView) inflate.findViewById(R.id.icon);
                this.mTitle = (TextView) inflate.findViewById(R.id.title);
                this.mBigTitle = (TextView) inflate.findViewById(R.id.big_title);
                this.mDescription = (TextView) inflate.findViewById(R.id.description);
                if (Build.VERSION.SDK_INT >= 21) {
                    this.mBigTitle.setLetterSpacing(0.025f);
                    this.mDescription.setLetterSpacing(0.025f);
                }
                this.mActionButton = (Button) inflate.findViewById(R.id.action_button);
                this.mTopDividerLine = inflate.findViewById(R.id.top_divider_line);
                if (drawable != null) {
                    setIcon(drawable);
                }
                if (text2 != null) {
                    setSingleLineTextMode(false);
                    setDescription(text2);
                    if (text != null) {
                        setBigTitle(text);
                    }
                } else {
                    setSingleLineTextMode(true);
                    if (text != null) {
                        setTitle(text);
                    }
                }
                if (drawable2 != null) {
                    setActionButtonBackground(drawable2);
                }
                if (text3 != null) {
                    setActionButtonText(text3);
                }
                setActionButtonVisible(z);
                setTopDividerLineVisible(z2);
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
    }

    private void setSingleLineTextMode(boolean z) {
        if (z) {
            this.mTitle.setVisibility(0);
            this.mBigTitle.setVisibility(8);
            this.mDescription.setVisibility(8);
            return;
        }
        this.mTitle.setVisibility(8);
        this.mBigTitle.setVisibility(0);
        this.mDescription.setVisibility(0);
    }

    public void apply(EmptyConfig emptyConfig) {
        if (emptyConfig.mIconDrawable != null) {
            setIcon(emptyConfig.mIconDrawable);
        } else if (emptyConfig.mIconResId != 0) {
            setIcon(emptyConfig.mIconResId);
        }
        if (emptyConfig.mDescriptionTextResId != 0) {
            setSingleLineTextMode(false);
            setDescription(emptyConfig.mDescriptionTextResId);
        } else if (emptyConfig.mDescriptionText != null) {
            setSingleLineTextMode(false);
            setDescription(emptyConfig.mDescriptionText);
        } else {
            setSingleLineTextMode(true);
        }
        if (!TextUtils.isEmpty(emptyConfig.mBigTitle)) {
            setBigTitle(emptyConfig.mBigTitle);
        } else if (emptyConfig.mBigTitleResId != 0) {
            setBigTitle(emptyConfig.mBigTitleResId);
        } else if (!TextUtils.isEmpty(emptyConfig.mTitle)) {
            setTitle(emptyConfig.mTitle);
        } else if (emptyConfig.mTitleResId != 0) {
            setTitle(emptyConfig.mTitleResId);
        }
        if (emptyConfig.mActionButtonBackgroundDrawable != null) {
            setActionButtonBackground(emptyConfig.mActionButtonBackgroundDrawable);
        }
        if (!TextUtils.isEmpty(emptyConfig.mActionButtonTitle)) {
            setActionButtonText(emptyConfig.mActionButtonTitle);
        }
        setActionButtonVisible(emptyConfig.isShowActionButton);
        setTopDividerLineVisible(emptyConfig.isActionButtonClickable);
        if (emptyConfig.isShowActionButton && emptyConfig.mActionButtonClickListener != null) {
            setOnActionButtonClickListener(emptyConfig.mActionButtonClickListener);
            setActionButtonClickable(emptyConfig.isActionButtonClickable);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        ImageView imageView = this.mIcon;
        if (imageView != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.vertical_spacing_twostagedraw);
            if (marginLayoutParams.topMargin != dimensionPixelSize) {
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, dimensionPixelSize, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                this.mIcon.setLayoutParams(marginLayoutParams);
            }
            if (configuration.orientation == 2) {
                this.mTitle.setVisibility(8);
            } else {
                this.mTitle.setVisibility(0);
            }
        }
    }

    public void setActionButtonBackground(int i) {
        this.mActionButton.setBackgroundResource(i);
    }

    public void setActionButtonBackground(Drawable drawable) {
        this.mActionButton.setBackground(drawable);
    }

    public void setActionButtonClickable(boolean z) {
        this.mActionButton.setClickable(z);
    }

    public void setActionButtonText(int i) {
        this.mActionButton.setText(i);
    }

    public void setActionButtonText(CharSequence charSequence) {
        this.mActionButton.setText(charSequence);
    }

    public void setActionButtonVisible(boolean z) {
        this.mActionButton.setVisibility(z ? 0 : 4);
    }

    public void setBigTitle(int i) {
        this.mBigTitle.setText(i);
    }

    public void setBigTitle(CharSequence charSequence) {
        this.mBigTitle.setText(charSequence);
    }

    public void setDescription(int i) {
        this.mDescription.setText(i);
    }

    public void setDescription(CharSequence charSequence) {
        this.mDescription.setText(charSequence);
    }

    public void setIcon(int i) {
        this.mIcon.setImageResource(i);
    }

    public void setIcon(Drawable drawable) {
        this.mIcon.setImageDrawable(drawable);
    }

    public void setOnActionButtonClickListener(View.OnClickListener onClickListener) {
        this.mActionButton.setOnClickListener(onClickListener);
    }

    public void setTitle(int i) {
        this.mTitle.setText(i);
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle.setText(charSequence);
    }

    public void setTopDividerLineVisible(boolean z) {
        this.mTopDividerLine.setVisibility(z ? 0 : 4);
    }
}
