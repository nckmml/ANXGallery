package com.miui.gallery.editor.photo.screen.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.android.internal.WindowCompat;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import miui.view.animation.QuarticEaseInInterpolator;
import miui.view.animation.QuarticEaseInOutInterpolator;
import miui.view.animation.QuarticEaseOutInterpolator;

public class ScreenAnimatorHelper {
    private int EDIT_BG_COLOR;
    private int SHARE_BG_COLOR;
    /* access modifiers changed from: private */
    public ScreenEditorActivity mActivity;
    /* access modifiers changed from: private */
    public List<AnimatorSet> mAnimatorSets = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public AnimatorViewCallback mAnimatorViewCallback;
    /* access modifiers changed from: private */
    public Resources mResources;
    public ThumbnailAnimatorCallback mShareViewAnimatorCallback = new ThumbnailAnimatorCallback() {
        public int[] getThumbnailRect() {
            return ScreenAnimatorHelper.this.mThumbnailRect;
        }

        public int getThumbnailStartLeft() {
            return ScreenAnimatorHelper.this.mThumbnailRect[0];
        }

        public int getThumbnailStartTop() {
            return ScreenAnimatorHelper.this.mThumbnailRect[1];
        }

        public void onAnimationStart() {
            ScreenAnimatorHelper.this.startQuitThumbnailService();
            ScreenAnimatorHelper.this.startEnterAnimator();
        }

        public void onAnimationUpdate(float f) {
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getShareBottomLine().setGuidelineEnd((int) (((float) ScreenAnimatorHelper.this.mResources.getDimensionPixelSize(R.dimen.screen_editor_share_mode_bottom_guideline_end)) * f));
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getShareTopLine().setGuidelineBegin((int) (((float) ScreenAnimatorHelper.this.mResources.getDimensionPixelSize(R.dimen.screen_editor_share_mode_top_guideline_begin)) * f));
        }

        public void onPrepareAnimatorStart() {
            ScreenAnimatorHelper.this.mActivity.configShareModeView();
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getShareBottomLine().setGuidelineEnd(0);
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getShareTopLine().setGuidelineBegin(0);
        }
    };
    public ThumbnailAnimatorCallback mThumbnailAnimatorCallback = new ThumbnailAnimatorCallback() {
        public int[] getThumbnailRect() {
            return ScreenAnimatorHelper.this.mThumbnailRect;
        }

        public int getThumbnailStartLeft() {
            return ScreenAnimatorHelper.this.mThumbnailRect[0];
        }

        public int getThumbnailStartTop() {
            return ScreenAnimatorHelper.this.mThumbnailRect[1];
        }

        public void onAnimationStart() {
            ScreenAnimatorHelper.this.startQuitThumbnailService();
            ScreenAnimatorHelper.this.startEnterAnimator();
        }

        public void onAnimationUpdate(float f) {
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getEditBottomLine().setGuidelineEnd((int) (((float) ScreenAnimatorHelper.this.mResources.getDimensionPixelSize(R.dimen.screen_editor_edit_mode_bottom_guideline_end)) * f));
            if (WindowCompat.isNotch(ScreenAnimatorHelper.this.mActivity)) {
                ScreenAnimatorHelper.this.mAnimatorViewCallback.getEditTopLine().setGuidelineBegin((int) (((float) (ScreenAnimatorHelper.this.mResources.getDimensionPixelSize(R.dimen.screen_editor_top_height) + WindowCompat.getTopNotchHeight(ScreenAnimatorHelper.this.mActivity))) * f));
                return;
            }
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getEditTopLine().setGuidelineBegin((int) (((float) ScreenAnimatorHelper.this.mResources.getDimensionPixelSize(R.dimen.screen_editor_top_height)) * f));
        }

        public void onPrepareAnimatorStart() {
            ScreenAnimatorHelper.this.mActivity.configEditModeView();
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getEditBottomLine().setGuidelineEnd(0);
            ScreenAnimatorHelper.this.mAnimatorViewCallback.getEditTopLine().setGuidelineBegin(0);
        }
    };
    /* access modifiers changed from: private */
    public int[] mThumbnailRect = {0, 0, 0, 0};

    interface AnimatorViewCallback {
        View getBottomLayoutView();

        Guideline getCommonChangeLine();

        Guideline getEditBottomLine();

        Guideline getEditTopLine();

        View getEditTopView();

        Guideline getMenuBottomLine();

        View getMenuLayoutView();

        Guideline getMenuTopLine();

        View getScreenEditorBgView();

        Guideline getShareBottomLine();

        Guideline getShareTopLine();

        View getShareTopView();
    }

    public ScreenAnimatorHelper(ScreenEditorActivity screenEditorActivity, int[] iArr, AnimatorViewCallback animatorViewCallback) {
        this.mActivity = screenEditorActivity;
        this.mAnimatorViewCallback = animatorViewCallback;
        setThumbnailRect(iArr);
        this.mResources = this.mActivity.getResources();
        this.SHARE_BG_COLOR = this.mResources.getColor(R.color.screen_editor_share_bg);
        this.EDIT_BG_COLOR = this.mResources.getColor(R.color.screen_editor_edit_bg);
    }

    private void setThumbnailRect(int[] iArr) {
        int i = 0;
        while (iArr != null && i < iArr.length) {
            this.mThumbnailRect[i] = iArr[i];
            i++;
        }
    }

    /* access modifiers changed from: private */
    public void startQuitThumbnailService() {
        if (this.mActivity != null) {
            Log.d("ScreenAnimatorHelper", "start ScreenShotService.");
            Intent intent = new Intent(this.mActivity, ScreenShotService.class);
            intent.putExtra("quit_thumnail", true);
            this.mActivity.startService(intent);
        }
    }

    public /* synthetic */ void lambda$startEditPageEnterAnimator$77$ScreenAnimatorHelper(int i, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        if (this.mActivity.isMenuExpand()) {
            this.mAnimatorViewCallback.getMenuTopLine().setGuidelineEnd(intValue);
            this.mAnimatorViewCallback.getMenuBottomLine().setGuidelineEnd(intValue - i);
        }
        this.mAnimatorViewCallback.getCommonChangeLine().setGuidelineEnd(intValue - i);
    }

    public /* synthetic */ void lambda$startEditPageExitAnimator$84$ScreenAnimatorHelper(int i, int i2, int i3, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mAnimatorViewCallback.getCommonChangeLine().setGuidelineEnd(intValue);
        if (this.mActivity.isMenuExpand()) {
            int i4 = i - intValue;
            this.mAnimatorViewCallback.getMenuBottomLine().setGuidelineEnd(i2 - i4);
            this.mAnimatorViewCallback.getMenuTopLine().setGuidelineEnd(i3 - i4);
        }
    }

    public /* synthetic */ void lambda$startLongCropEditPageEnterAnimator$78$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getScreenEditorBgView().setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$startMenuAnimator$82$ScreenAnimatorHelper(int i, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mAnimatorViewCallback.getMenuBottomLine().setGuidelineEnd(intValue);
        this.mAnimatorViewCallback.getMenuTopLine().setGuidelineEnd(intValue + i);
    }

    public /* synthetic */ void lambda$startMenuAnimator$83$ScreenAnimatorHelper(int i, ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mAnimatorViewCallback.getMenuTopLine().setGuidelineEnd(intValue);
        this.mAnimatorViewCallback.getMenuBottomLine().setGuidelineEnd(intValue - i);
    }

    public /* synthetic */ void lambda$startSharePageEnterAnimator$73$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getShareTopLine().setGuidelineBegin(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$startSharePageEnterAnimator$74$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getShareBottomLine().setGuidelineEnd(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$startSharePageEnterAnimator$75$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getCommonChangeLine().setGuidelineEnd(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$startSharePageEnterAnimator$76$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getScreenEditorBgView().setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$startSharePageExitAnimator$79$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getShareTopLine().setGuidelineBegin(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$startSharePageExitAnimator$80$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getShareBottomLine().setGuidelineEnd(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public /* synthetic */ void lambda$startSharePageExitAnimator$81$ScreenAnimatorHelper(ValueAnimator valueAnimator) {
        this.mAnimatorViewCallback.getCommonChangeLine().setGuidelineEnd(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public void release() {
        for (AnimatorSet next : this.mAnimatorSets) {
            if (next != null) {
                next.cancel();
            }
        }
    }

    public void setViewsAlpha(float f, View... viewArr) {
        for (View alpha : viewArr) {
            alpha.setAlpha(f);
        }
    }

    public void startEditPageEnterAnimator() {
        int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_menu_top_guideline_end);
        int dimensionPixelSize2 = dimensionPixelSize - this.mResources.getDimensionPixelSize(R.dimen.screen_editor_menu_bottom_guideline_end);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mAnimatorViewCallback.getBottomLayoutView().getLayoutParams();
        layoutParams.height = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_edit_mode_bottom_guideline_end);
        layoutParams.topToBottom = R.id.common_base_guide_line;
        this.mAnimatorViewCallback.getBottomLayoutView().setLayoutParams(layoutParams);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, dimensionPixelSize});
        ofInt.setDuration(370);
        ofInt.setInterpolator(new QuarticEaseOutInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(dimensionPixelSize2) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startEditPageEnterAnimator$77$ScreenAnimatorHelper(this.f$1, valueAnimator);
            }
        });
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getEditTopView(), new PropertyValuesHolder[]{ofFloat});
        ofPropertyValuesHolder.setDuration(370);
        ofPropertyValuesHolder.setInterpolator(new QuarticEaseInOutInterpolator());
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofInt).with(ofPropertyValuesHolder);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.remove(animatorSet);
            }

            public void onAnimationStart(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.add(animatorSet);
            }
        });
        animatorSet.start();
    }

    public void startEditPageExitAnimator(Animator.AnimatorListener animatorListener) {
        Resources resources = this.mActivity.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.screen_editor_edit_mode_bottom_guideline_end);
        this.mAnimatorViewCallback.getCommonChangeLine().setGuidelineEnd(dimensionPixelSize);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.screen_editor_menu_top_guideline_end);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.screen_editor_menu_bottom_guideline_end);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mAnimatorViewCallback.getBottomLayoutView().getLayoutParams();
        layoutParams.topToBottom = R.id.common_base_guide_line;
        this.mAnimatorViewCallback.getBottomLayoutView().setLayoutParams(layoutParams);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{dimensionPixelSize, dimensionPixelSize3 - dimensionPixelSize2});
        ofInt.setDuration(180);
        ofInt.setInterpolator(new QuarticEaseInInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize2) {
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ int f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startEditPageExitAnimator$84$ScreenAnimatorHelper(this.f$1, this.f$2, this.f$3, valueAnimator);
            }
        });
        ofInt.addListener(animatorListener);
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getEditTopView(), new PropertyValuesHolder[]{ofFloat});
        ofPropertyValuesHolder.setDuration(370);
        ofPropertyValuesHolder.setInterpolator(new QuarticEaseInOutInterpolator());
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofInt).with(ofPropertyValuesHolder);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.remove(animatorSet);
            }

            public void onAnimationStart(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.add(animatorSet);
            }
        });
        animatorSet.start();
    }

    public void startEnterAnimator() {
        final AnimatorSet animatorSet = new AnimatorSet();
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mActivity.isSharePage() ? this.mAnimatorViewCallback.getShareTopView() : this.mAnimatorViewCallback.getEditTopView(), new PropertyValuesHolder[]{ofFloat});
        ofPropertyValuesHolder.setDuration(500);
        ofPropertyValuesHolder.setInterpolator(new QuarticEaseOutInterpolator());
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getScreenEditorBgView(), new PropertyValuesHolder[]{ofFloat});
        ofPropertyValuesHolder2.setDuration(500);
        ofPropertyValuesHolder2.setInterpolator(new QuarticEaseOutInterpolator());
        ObjectAnimator ofPropertyValuesHolder3 = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getBottomLayoutView(), new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("translationY", new float[]{(float) this.mAnimatorViewCallback.getBottomLayoutView().getHeight(), 0.0f}), ofFloat});
        ofPropertyValuesHolder3.setStartDelay(150);
        ofPropertyValuesHolder3.setDuration(500);
        ofPropertyValuesHolder3.setInterpolator(new QuarticEaseOutInterpolator());
        animatorSet.play(ofPropertyValuesHolder3).with(ofPropertyValuesHolder).with(ofPropertyValuesHolder2);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.remove(animatorSet);
            }

            public void onAnimationStart(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.add(animatorSet);
            }
        });
        animatorSet.start();
    }

    public void startLongCropEditPageEnterAnimator() {
        this.mActivity.configEditModeView();
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getScreenEditorBgView(), new PropertyValuesHolder[]{ofFloat});
        ofPropertyValuesHolder.setDuration(500);
        ofPropertyValuesHolder.setInterpolator(new QuarticEaseOutInterpolator());
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getEditTopView(), new PropertyValuesHolder[]{ofFloat});
        ofPropertyValuesHolder2.setDuration(500);
        ofPropertyValuesHolder2.setInterpolator(new QuarticEaseOutInterpolator());
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("translationY", new float[]{(float) this.mAnimatorViewCallback.getBottomLayoutView().getHeight(), 0.0f});
        ObjectAnimator ofPropertyValuesHolder3 = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getBottomLayoutView(), new PropertyValuesHolder[]{ofFloat2, ofFloat});
        ofPropertyValuesHolder3.setStartDelay(250);
        ofPropertyValuesHolder3.setDuration(500);
        ofPropertyValuesHolder3.setInterpolator(new QuarticEaseOutInterpolator());
        ValueAnimator ofObject = ValueAnimator.ofObject(new ScreenArgbEvaluator(), new Object[]{Integer.valueOf(this.SHARE_BG_COLOR), Integer.valueOf(this.EDIT_BG_COLOR)});
        ofObject.setDuration(370);
        ofObject.setInterpolator(new QuarticEaseInOutInterpolator());
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startLongCropEditPageEnterAnimator$78$ScreenAnimatorHelper(valueAnimator);
            }
        });
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofPropertyValuesHolder).with(ofPropertyValuesHolder2).with(ofPropertyValuesHolder3).with(ofObject);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.remove(animatorSet);
            }

            public void onAnimationStart(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.add(animatorSet);
            }
        });
        animatorSet.start();
    }

    public void startMenuAnimator(boolean z) {
        Resources resources = this.mActivity.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.screen_editor_menu_top_guideline_end);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.screen_editor_menu_bottom_guideline_end);
        int i = dimensionPixelSize - dimensionPixelSize2;
        this.mAnimatorViewCallback.getMenuBottomLine().setGuidelineEnd(dimensionPixelSize2);
        this.mAnimatorViewCallback.getMenuTopLine().setGuidelineEnd(dimensionPixelSize);
        if (z) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{dimensionPixelSize2 - i, dimensionPixelSize2});
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ScreenAnimatorHelper.this.lambda$startMenuAnimator$82$ScreenAnimatorHelper(this.f$1, valueAnimator);
                }
            });
            ofInt.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    ScreenAnimatorHelper.this.mAnimatorViewCallback.getMenuLayoutView().setVisibility(0);
                }
            });
            ofInt.setDuration(270);
            ofInt.setInterpolator(new QuarticEaseOutInterpolator());
            ofInt.start();
            return;
        }
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{dimensionPixelSize, dimensionPixelSize2});
        ofInt2.setDuration(120);
        ofInt2.setInterpolator(new QuarticEaseInInterpolator());
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(i) {
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startMenuAnimator$83$ScreenAnimatorHelper(this.f$1, valueAnimator);
            }
        });
        ofInt2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorViewCallback.getMenuLayoutView().setVisibility(8);
            }
        });
        ofInt2.start();
    }

    public void startSharePageEnterAnimator() {
        final AnimatorSet animatorSet = new AnimatorSet();
        int topNotchHeight = WindowCompat.isNotch(this.mActivity) ? WindowCompat.getTopNotchHeight(this.mActivity) : 0;
        int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_share_mode_top_guideline_begin);
        int dimensionPixelSize2 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_share_mode_bottom_guideline_end);
        int dimensionPixelSize3 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_base_guideline_end);
        int dimensionPixelSize4 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_top_height) + topNotchHeight + this.mResources.getDimensionPixelSize(R.dimen.longscreenshot_crop_cornor_bar_width);
        int dimensionPixelSize5 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_edit_mode_bottom_guideline_end) + this.mResources.getDimensionPixelSize(R.dimen.screen_editor_view_bottom_margin);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{dimensionPixelSize4, dimensionPixelSize});
        long j = (long) 450;
        ofInt.setDuration(j);
        ofInt.setInterpolator(new QuarticEaseInOutInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startSharePageEnterAnimator$73$ScreenAnimatorHelper(valueAnimator);
            }
        });
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{dimensionPixelSize5, dimensionPixelSize2});
        ofInt2.setDuration(j);
        ofInt2.setInterpolator(new QuarticEaseInOutInterpolator());
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startSharePageEnterAnimator$74$ScreenAnimatorHelper(valueAnimator);
            }
        });
        ValueAnimator ofInt3 = ValueAnimator.ofInt(new int[]{0, dimensionPixelSize3});
        ofInt3.setDuration(j);
        ofInt3.setInterpolator(new QuarticEaseInOutInterpolator());
        ofInt3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startSharePageEnterAnimator$75$ScreenAnimatorHelper(valueAnimator);
            }
        });
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getShareTopView(), new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f, 1.0f})});
        ofPropertyValuesHolder.setDuration(370);
        ofPropertyValuesHolder.setInterpolator(new QuarticEaseInOutInterpolator());
        ValueAnimator ofObject = ValueAnimator.ofObject(new ScreenArgbEvaluator(), new Object[]{Integer.valueOf(this.EDIT_BG_COLOR), Integer.valueOf(this.SHARE_BG_COLOR)});
        ofObject.setDuration(370);
        ofObject.setInterpolator(new QuarticEaseInOutInterpolator());
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startSharePageEnterAnimator$76$ScreenAnimatorHelper(valueAnimator);
            }
        });
        animatorSet.play(ofInt).with(ofInt2).with(ofInt3).with(ofPropertyValuesHolder).with(ofObject);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.remove(animatorSet);
            }

            public void onAnimationStart(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.add(animatorSet);
            }
        });
        animatorSet.start();
    }

    public void startSharePageExitAnimator(Animator.AnimatorListener animatorListener) {
        int topNotchHeight = WindowCompat.isNotch(this.mActivity) ? WindowCompat.getTopNotchHeight(this.mActivity) : 0;
        int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_share_mode_top_guideline_begin);
        int dimensionPixelSize2 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_share_mode_bottom_guideline_end);
        int dimensionPixelSize3 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_base_guideline_end);
        int dimensionPixelSize4 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_top_height) + topNotchHeight + this.mResources.getDimensionPixelSize(R.dimen.longscreenshot_crop_cornor_bar_width);
        int dimensionPixelSize5 = this.mResources.getDimensionPixelSize(R.dimen.screen_editor_edit_mode_bottom_guideline_end) + this.mResources.getDimensionPixelSize(R.dimen.screen_editor_view_bottom_margin);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{dimensionPixelSize, dimensionPixelSize4});
        long j = (long) 450;
        ofInt.setDuration(j);
        ofInt.setInterpolator(new QuarticEaseInOutInterpolator());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startSharePageExitAnimator$79$ScreenAnimatorHelper(valueAnimator);
            }
        });
        ValueAnimator ofInt2 = ValueAnimator.ofInt(new int[]{dimensionPixelSize2, dimensionPixelSize5});
        ofInt2.setDuration(j);
        ofInt2.setInterpolator(new QuarticEaseInOutInterpolator());
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startSharePageExitAnimator$80$ScreenAnimatorHelper(valueAnimator);
            }
        });
        ValueAnimator ofInt3 = ValueAnimator.ofInt(new int[]{dimensionPixelSize3, 0});
        ofInt3.setDuration(j);
        ofInt3.setInterpolator(new QuarticEaseInOutInterpolator());
        ofInt3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenAnimatorHelper.this.lambda$startSharePageExitAnimator$81$ScreenAnimatorHelper(valueAnimator);
            }
        });
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.mAnimatorViewCallback.getShareTopView(), new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.0f})});
        ofPropertyValuesHolder.setDuration(j);
        ofPropertyValuesHolder.setInterpolator(new QuarticEaseInOutInterpolator());
        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofInt).with(ofInt2).with(ofInt3).with(ofPropertyValuesHolder);
        animatorSet.addListener(animatorListener);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.remove(animatorSet);
            }

            public void onAnimationStart(Animator animator) {
                ScreenAnimatorHelper.this.mAnimatorSets.add(animatorSet);
            }
        });
        animatorSet.start();
    }
}
