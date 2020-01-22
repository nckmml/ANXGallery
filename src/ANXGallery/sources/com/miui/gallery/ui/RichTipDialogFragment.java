package com.miui.gallery.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.widget.GalleryDialogFragment;

public class RichTipDialogFragment extends GalleryDialogFragment {
    private View mContent;
    /* access modifiers changed from: private */
    public RichTipDialog mDialog;
    private CharSequence mPositiveButtonText;
    /* access modifiers changed from: private */
    public DialogInterface.OnClickListener mPositiveClickListener;
    private CharSequence mSubTitle;
    private CharSequence mTitle;

    private static class RichTipDialog extends Dialog implements View.OnClickListener {
        private View mContent;
        private FrameLayout mContentContainer;
        private Button mPositiveBtn;
        private CharSequence mPositiveButtonText;
        private View.OnClickListener mPositiveClickListener;
        private TextView mSubTitle;
        private CharSequence mSubTitleText;
        private TextView mTitle;
        private CharSequence mTitleText;

        public RichTipDialog(Context context) {
            super(context);
        }

        private void initValue() {
            if (!TextUtils.isEmpty(this.mTitleText)) {
                setTitle(this.mTitleText);
            }
            if (!TextUtils.isEmpty(this.mSubTitleText)) {
                setSubTitle(this.mSubTitleText);
            }
            if (!TextUtils.isEmpty(this.mPositiveButtonText)) {
                setPositiveButton(this.mPositiveButtonText);
            }
            View view = this.mContent;
            if (view != null) {
                setContentView(view);
            }
        }

        public void onClick(View view) {
            View.OnClickListener onClickListener;
            if (view.getId() == R.id.positive_btn && (onClickListener = this.mPositiveClickListener) != null) {
                onClickListener.onClick(view);
            }
        }

        /* access modifiers changed from: protected */
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.rich_tip_dialog);
            this.mTitle = (TextView) findViewById(R.id.title);
            this.mSubTitle = (TextView) findViewById(R.id.sub_title);
            this.mContentContainer = (FrameLayout) findViewById(R.id.content);
            this.mPositiveBtn = (Button) findViewById(R.id.positive_btn);
            this.mPositiveBtn.setOnClickListener(this);
            initValue();
            Window window = getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }

        public void setContentView(View view) {
            this.mContent = view;
            FrameLayout frameLayout = this.mContentContainer;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
                this.mContentContainer.addView(view);
            }
        }

        public void setPositiveButton(CharSequence charSequence) {
            this.mPositiveButtonText = charSequence;
            Button button = this.mPositiveBtn;
            if (button != null) {
                button.setText(charSequence);
            }
        }

        public RichTipDialog setPositiveButtonOnClickListener(View.OnClickListener onClickListener) {
            this.mPositiveClickListener = onClickListener;
            return this;
        }

        public void setSubTitle(CharSequence charSequence) {
            this.mSubTitleText = charSequence;
            TextView textView = this.mSubTitle;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }

        public void setTitle(CharSequence charSequence) {
            this.mTitleText = charSequence;
            TextView textView = this.mTitle;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
    }

    public final Dialog onCreateDialog(Bundle bundle) {
        RichTipDialog richTipDialog = new RichTipDialog(getActivity());
        richTipDialog.setTitle(this.mTitle);
        richTipDialog.setSubTitle(this.mSubTitle);
        richTipDialog.setContentView(this.mContent);
        richTipDialog.setPositiveButton(this.mPositiveButtonText);
        richTipDialog.setPositiveButtonOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (RichTipDialogFragment.this.mPositiveClickListener != null) {
                    RichTipDialogFragment.this.mPositiveClickListener.onClick(RichTipDialogFragment.this.mDialog, -1);
                }
                RichTipDialogFragment.this.dismissSafely();
            }
        });
        richTipDialog.setCancelable(true);
        richTipDialog.setCanceledOnTouchOutside(false);
        this.mDialog = richTipDialog;
        return richTipDialog;
    }

    public void setContentView(View view) {
        this.mContent = view;
        RichTipDialog richTipDialog = this.mDialog;
        if (richTipDialog != null) {
            richTipDialog.setContentView(view);
        }
    }

    public void setPositiveButton(CharSequence charSequence) {
        this.mPositiveButtonText = charSequence;
        RichTipDialog richTipDialog = this.mDialog;
        if (richTipDialog != null) {
            richTipDialog.setPositiveButton(charSequence);
        }
    }

    public void setSubTitle(CharSequence charSequence) {
        this.mSubTitle = charSequence;
        RichTipDialog richTipDialog = this.mDialog;
        if (richTipDialog != null) {
            richTipDialog.setSubTitle(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        RichTipDialog richTipDialog = this.mDialog;
        if (richTipDialog != null) {
            richTipDialog.setTitle(charSequence);
        }
    }
}
