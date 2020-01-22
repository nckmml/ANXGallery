package com.miui.gallery.editor.photo.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.miui.gallery.R;
import com.miui.gallery.compat.view.ViewCompat;
import com.miui.gallery.editor.photo.app.PreviewFragment;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MiscUtil;
import java.util.HashMap;
import miui.view.animation.CubicEaseOutInterpolator;

public class PreviewFragment extends Fragment {
    private int mAnimatorDuration;
    Callbacks mCallbacks;
    private int mCompareButtonDelay;
    private boolean mCompareEnable = false;
    private Runnable mCompareRunnable = new Runnable() {
        public void run() {
            PreviewFragment.this.mHandler.removeCallbacks(this);
            PreviewFragment.this.mImageView.setImageBitmap(PreviewFragment.this.mCallbacks.onLoadPreviewOriginal());
            HashMap hashMap = new HashMap();
            hashMap.put("page", "main");
            GallerySamplingStatHelper.recordCountEvent("photo_editor", "compare_button_touch", hashMap);
        }
    };
    private Runnable mDismissTipRunnable = new Runnable() {
        public void run() {
            PreviewFragment.this.dismissTip();
        }
    };
    private ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            PreviewFragment.this.updateRemoverBtnLocation();
            PreviewFragment.this.mRemoverBtn.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public ImageView mImageView;
    private boolean mIsNear3V4;
    private boolean mIsOnExit = false;
    private boolean mLoadDone = true;
    OnViewReadyListener mOnViewReadyListener;
    private boolean mOverwriteBackground;
    private View mProgressView;
    private boolean mRemoveWatermarkEnable = false;
    private View.OnClickListener mRemoveWatermarkListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean isSelected = view.isSelected();
            PreviewFragment.this.mCallbacks.reRender(isSelected);
            view.setSelected(!isSelected);
            boolean unused = PreviewFragment.this.mRemoveWatermarkSelected = view.isSelected();
            PreviewFragment.this.mRemoverBtn.setText(PreviewFragment.this.mRemoveWatermarkSelected ? R.string.photo_editor_remove_watermark_on : R.string.photo_editor_remove_watermark);
        }
    };
    /* access modifiers changed from: private */
    public boolean mRemoveWatermarkSelected = false;
    /* access modifiers changed from: private */
    public Button mRemoverBtn;
    private Guideline mRemoverBtnEndLine;
    private Guideline mRemoverBtnTopLine;
    private TextView mTipView;
    private ValueAnimator mValueAnimator;

    interface Callbacks {
        boolean isPreviewChanged();

        Bitmap onLoadPreview();

        Bitmap onLoadPreviewOriginal();

        void reRender(boolean z);
    }

    private class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        private OnPreDrawListener() {
        }

        public /* synthetic */ void lambda$onPreDraw$107$PreviewFragment$OnPreDrawListener() {
            PreviewFragment.this.mOnViewReadyListener.onViewReady();
        }

        public boolean onPreDraw() {
            PreviewFragment.this.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
            ThreadManager.getMainHandler().post(new Runnable() {
                public final void run() {
                    PreviewFragment.OnPreDrawListener.this.lambda$onPreDraw$107$PreviewFragment$OnPreDrawListener();
                }
            });
            return true;
        }
    }

    interface OnPrepareEditFragmentListener {
        void showEditFragment(Effect effect);
    }

    interface OnViewReadyListener {
        void onViewReady();
    }

    /* access modifiers changed from: private */
    public void dismissTip() {
        this.mHandler.removeCallbacks(this.mDismissTipRunnable);
        TextView textView = this.mTipView;
        if (textView != null) {
            TransitionManager.beginDelayedTransition((ViewGroup) textView.getParent());
            this.mTipView.setVisibility(8);
        }
    }

    private void refreshRemoveButtonStatus() {
        Button button = this.mRemoverBtn;
        if (button != null) {
            button.setVisibility(this.mRemoveWatermarkEnable ? 0 : 8);
            this.mRemoverBtn.setSelected(this.mRemoveWatermarkSelected);
            this.mRemoverBtn.setText(this.mRemoveWatermarkSelected ? R.string.photo_editor_remove_watermark_on : R.string.photo_editor_remove_watermark);
        }
    }

    private void showCompareButtonTip() {
        if (GalleryPreferences.PhotoEditor.shouldCompareTipShow()) {
            showTip(R.string.photo_editor_compare_tip);
        }
    }

    private void showTip(int i) {
        TextView textView = this.mTipView;
        if (textView != null && !this.mOverwriteBackground) {
            textView.setText(i);
            TransitionManager.beginDelayedTransition((ViewGroup) this.mTipView.getParent());
            this.mTipView.setVisibility(0);
            this.mHandler.removeCallbacks(this.mDismissTipRunnable);
            this.mHandler.postDelayed(this.mDismissTipRunnable, 3000);
            GalleryPreferences.PhotoEditor.setCompareTipShow();
        }
    }

    /* access modifiers changed from: private */
    public void updateRemoverBtnLocation() {
        int i;
        int i2;
        setBitmapRatio(this.mCallbacks.onLoadPreview());
        Activity activity = getActivity();
        if (activity != null) {
            if (!MiscUtil.checkNavigationBarShow(activity, activity.getWindow())) {
                if (this.mIsNear3V4) {
                    i2 = getResources().getDimensionPixelSize(R.dimen.editor_remove_watermark_margin_top_3V4);
                    i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_3V4);
                } else {
                    i2 = getResources().getDimensionPixelSize(R.dimen.editor_remove_watermark_margin_top_9V16);
                    i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_9V16);
                }
            } else if (this.mIsNear3V4) {
                i2 = getResources().getDimensionPixelSize(R.dimen.editor_remove_watermark_margin_top_has_nav_bar_3V4);
                i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_has_nav_bar_3V4);
            } else {
                i2 = getResources().getDimensionPixelSize(R.dimen.editor_remove_watermark_margin_top_has_nav_bar_9V16);
                i = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_compare_btn_margin_end_has_nav_bar_9V16);
            }
            this.mRemoverBtnTopLine.setGuidelineBegin(i2);
            this.mRemoverBtnEndLine.setGuidelineEnd(i);
        }
    }

    public void enableComparison(boolean z) {
        this.mCompareEnable = z;
        if (this.mCompareEnable) {
            showCompareButtonTip();
        }
    }

    public boolean isRunningPreviewAnimator() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            return valueAnimator.isRunning();
        }
        return false;
    }

    public /* synthetic */ boolean lambda$onViewCreated$106$PreviewFragment(View view, MotionEvent motionEvent) {
        if (!this.mCompareEnable || this.mOverwriteBackground) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mHandler.removeCallbacks(this.mCompareRunnable);
            this.mHandler.postDelayed(this.mCompareRunnable, (long) this.mCompareButtonDelay);
        } else if (action == 1 || action == 3) {
            this.mHandler.removeCallbacks(this.mCompareRunnable);
            this.mImageView.setImageBitmap(this.mCallbacks.onLoadPreview());
        }
        return true;
    }

    public /* synthetic */ void lambda$prepareShowEditFragment$108$PreviewFragment(ConstraintLayout.LayoutParams layoutParams, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        layoutParams.setMarginStart(intValue);
        layoutParams.setMarginEnd(intValue);
        this.mImageView.setLayoutParams(layoutParams);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ConstraintLayout constraintLayout = (ConstraintLayout) layoutInflater.inflate(R.layout.preview_container, viewGroup, false);
        this.mImageView = (ImageView) constraintLayout.findViewById(R.id.preview);
        this.mRemoverBtn = (Button) constraintLayout.findViewById(R.id.remove_btn);
        this.mProgressView = constraintLayout.findViewById(R.id.progress_bar);
        this.mTipView = (TextView) constraintLayout.findViewById(R.id.tip_view);
        this.mRemoverBtnTopLine = (Guideline) constraintLayout.findViewById(R.id.remove_btn_line_top);
        this.mRemoverBtnEndLine = (Guideline) constraintLayout.findViewById(R.id.remove_btn_line_end);
        ViewCompat.setTransitionName(this.mImageView, R.string.photo_editor_transition_image_view);
        if (getArguments() != null) {
            this.mOverwriteBackground = getArguments().getBoolean("overwrite_background");
        }
        if (this.mOverwriteBackground) {
            TypedValue typedValue = new TypedValue();
            getActivity().getTheme().resolveAttribute(16842801, typedValue, true);
            this.mImageView.setBackgroundColor(typedValue.data);
        }
        this.mRemoverBtn.getViewTreeObserver().addOnGlobalLayoutListener(this.mGlobalLayoutListener);
        this.mAnimatorDuration = getResources().getInteger(R.integer.photo_editor_sub_editor_sub_menu_appear_duration);
        return constraintLayout;
    }

    public void onDetach() {
        super.onDetach();
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setImageBitmap((Bitmap) null);
        }
    }

    public void onExit(boolean z) {
        this.mIsOnExit = true;
        if (this.mImageView != null && !z) {
            Bitmap onLoadPreview = this.mCallbacks.onLoadPreview();
            Bitmap onLoadPreviewOriginal = this.mCallbacks.onLoadPreviewOriginal();
            if (onLoadPreview != null && onLoadPreviewOriginal != null && onLoadPreview.getWidth() == onLoadPreviewOriginal.getWidth() && onLoadPreview.getHeight() == onLoadPreviewOriginal.getHeight()) {
                this.mImageView.setImageBitmap(onLoadPreviewOriginal);
            }
        }
    }

    public void onPause() {
        super.onPause();
        dismissTip();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mCompareButtonDelay = getResources().getInteger(R.integer.compare_button_delay);
        if (this.mOnViewReadyListener != null) {
            this.mImageView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener());
        }
        showForLoadDown();
        refreshRemoveButtonStatus();
        this.mRemoverBtn.setOnClickListener(this.mRemoveWatermarkListener);
        this.mImageView.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return PreviewFragment.this.lambda$onViewCreated$106$PreviewFragment(view, motionEvent);
            }
        });
    }

    public void prepareShowEditFragment(final Effect effect, final OnPrepareEditFragmentListener onPrepareEditFragmentListener) {
        this.mValueAnimator = ValueAnimator.ofInt(new int[]{0, getResources().getDimensionPixelSize(R.dimen.editor_menu_crop_rotate_crop_padding_start)});
        this.mValueAnimator.setDuration((long) this.mAnimatorDuration);
        this.mValueAnimator.setInterpolator(new CubicEaseOutInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener((ConstraintLayout.LayoutParams) this.mImageView.getLayoutParams()) {
            private final /* synthetic */ ConstraintLayout.LayoutParams f$1;

            {
                this.f$1 = r2;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                PreviewFragment.this.lambda$prepareShowEditFragment$108$PreviewFragment(this.f$1, valueAnimator);
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (PreviewFragment.this.mCallbacks != null) {
                    onPrepareEditFragmentListener.showEditFragment(effect);
                }
            }
        });
        this.mValueAnimator.start();
    }

    public void refreshPreview(Bitmap bitmap) {
        if (isAdded() && !this.mIsOnExit) {
            this.mImageView.setImageBitmap(bitmap);
        }
    }

    public void setBitmapRatio(Bitmap bitmap) {
        if (bitmap != null) {
            float width = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
            this.mIsNear3V4 = Math.abs(width - 0.75f) <= Math.abs(width - 0.5625f);
        }
    }

    public void setLoadDone(boolean z) {
        this.mLoadDone = z;
    }

    public void setRemoveWatermarkEnable(boolean z) {
        this.mRemoveWatermarkEnable = z;
        Button button = this.mRemoverBtn;
        if (button != null) {
            button.setVisibility(z ? 0 : 8);
        }
    }

    public void showForLoadDown() {
        if (this.mLoadDone) {
            this.mImageView.setVisibility(0);
            this.mImageView.setImageBitmap(this.mCallbacks.onLoadPreview());
            enableComparison(this.mCallbacks.isPreviewChanged());
            this.mProgressView.setVisibility(8);
            updateRemoverBtnLocation();
        }
    }
}
