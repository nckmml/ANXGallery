package com.miui.gallery.editor.photo.core.imports.longcrop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.miui.gallery.editor.photo.core.imports.longcrop.LongCropViewAnimatorHelper;
import com.miui.gallery.editor.photo.screen.home.AnimatorState;
import miui.view.animation.QuarticEaseInOutInterpolator;

public class LongCropViewAnimatorHelper {
    /* access modifiers changed from: private */
    public AnimatorState mAnimatorState = AnimatorState.ANIMATOR_END;
    private Rect mDstRect = new Rect();
    private Bitmap mPreBitmap;
    private int[] mScreenRect = {0, 0, 0, 0};
    private Rect mSrcRect = new Rect();
    private int mStartBottom;
    private int mStartLeft;
    private int mStartRight;
    private int mStartTop;

    interface Callback {
        Bitmap getPreBitmap();

        Rect getShowRect();

        void onInvalidate();
    }

    private void animatorPreStart(Callback callback) {
        this.mAnimatorState = AnimatorState.ANIMATOR_PRE_START;
        int[] iArr = this.mScreenRect;
        this.mStartBottom = iArr[3];
        this.mStartTop = this.mStartBottom - Math.round((((float) iArr[2]) / ((float) callback.getPreBitmap().getWidth())) * ((float) callback.getPreBitmap().getHeight()));
        int[] iArr2 = this.mScreenRect;
        this.mStartLeft = iArr2[0];
        this.mStartRight = this.mStartLeft + iArr2[2];
        this.mSrcRect.set(0, 0, this.mPreBitmap.getWidth(), this.mPreBitmap.getHeight());
        this.mDstRect.set(this.mStartLeft, this.mStartTop, this.mStartRight, this.mStartBottom);
        callback.onInvalidate();
        animatorStart(callback);
    }

    private void animatorStart(final Callback callback) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(600);
        ofFloat.setInterpolator(new QuarticEaseInOutInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(callback) {
            private final /* synthetic */ LongCropViewAnimatorHelper.Callback f$1;

            {
                this.f$1 = r2;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                LongCropViewAnimatorHelper.this.lambda$animatorStart$66$LongCropViewAnimatorHelper(this.f$1, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                AnimatorState unused = LongCropViewAnimatorHelper.this.mAnimatorState = AnimatorState.ANIMATOR_END;
                callback.onInvalidate();
            }

            public void onAnimationStart(Animator animator) {
                AnimatorState unused = LongCropViewAnimatorHelper.this.mAnimatorState = AnimatorState.ANIMATOR_START;
            }
        });
        ofFloat.start();
    }

    private void changeRect(Callback callback, float f) {
        Rect rect = this.mDstRect;
        int i = callback.getShowRect().left;
        int i2 = this.mStartLeft;
        rect.left = Math.round((((float) (i - i2)) * f) + ((float) i2));
        Rect rect2 = this.mDstRect;
        int i3 = callback.getShowRect().top;
        int i4 = this.mStartTop;
        rect2.top = Math.round((((float) (i3 - i4)) * f) + ((float) i4));
        Rect rect3 = this.mDstRect;
        int i5 = callback.getShowRect().right;
        int i6 = this.mStartRight;
        rect3.right = Math.round((((float) (i5 - i6)) * f) + ((float) i6));
        Rect rect4 = this.mDstRect;
        int i7 = callback.getShowRect().bottom;
        int i8 = this.mStartBottom;
        rect4.bottom = Math.round((((float) (i7 - i8)) * f) + ((float) i8));
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.mPreBitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, this.mSrcRect, this.mDstRect, (Paint) null);
        }
    }

    public boolean isAnimatorEnd() {
        return this.mAnimatorState == AnimatorState.ANIMATOR_END;
    }

    public /* synthetic */ void lambda$animatorStart$66$LongCropViewAnimatorHelper(Callback callback, ValueAnimator valueAnimator) {
        changeRect(callback, valueAnimator.getAnimatedFraction());
        this.mAnimatorState = AnimatorState.ANIMATOR_UPDATE;
        callback.onInvalidate();
    }

    public void startEnterAnimation(Callback callback, int[] iArr) {
        int i = 0;
        while (iArr != null && i < iArr.length) {
            this.mScreenRect[i] = iArr[i];
            i++;
        }
        this.mPreBitmap = callback.getPreBitmap();
        animatorPreStart(callback);
    }
}
