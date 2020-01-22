package com.miui.gallery.editor.photo.screen.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.screen.home.ScreenEditViewAnimatorHelper;
import com.miui.gallery.util.BitmapUtils;
import miui.view.animation.QuarticEaseInOutInterpolator;

public class ScreenEditViewAnimatorHelper {
    /* access modifiers changed from: private */
    public AnimatorState mAnimatorState;
    private Context mContext;
    private RectF mDstRect = new RectF();
    private Matrix mMatrix = new Matrix();
    private Bitmap mPreBitmap;
    private float mRoundRadius;
    private Rect mSrcRect = new Rect();
    private int mStartBottom;
    private int mStartLeft;
    private int mStartRight;
    private int mStartTop;
    private int[] mThumbnailRect = {0, 0, 0, 0};

    interface Callback {
        Bitmap getOriginBitmap();

        RectF getShowRect();

        void onAnimationStart();

        void onAnimationUpdate(float f);

        void onInvalidate();
    }

    private void animatorPreStart(Callback callback) {
        this.mAnimatorState = AnimatorState.ANIMATOR_PRE_START;
        this.mSrcRect.set(0, 0, callback.getOriginBitmap().getWidth(), callback.getOriginBitmap().getHeight());
        int[] iArr = this.mThumbnailRect;
        this.mStartLeft = iArr[0];
        this.mStartTop = iArr[1];
        this.mStartRight = this.mStartLeft + iArr[2];
        this.mStartBottom = this.mStartTop + iArr[3];
        this.mRoundRadius = (float) this.mContext.getResources().getDimensionPixelSize(R.dimen.screen_editor_thumbnail_btn_radius);
        this.mDstRect.set((float) this.mStartLeft, (float) this.mStartTop, (float) this.mStartRight, (float) this.mStartBottom);
        callback.onInvalidate();
        animatorStart(callback);
    }

    private void animatorStart(final Callback callback) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(450);
        ofFloat.setInterpolator(new QuarticEaseInOutInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(callback) {
            private final /* synthetic */ ScreenEditViewAnimatorHelper.Callback f$1;

            {
                this.f$1 = r2;
            }

            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenEditViewAnimatorHelper.this.lambda$animatorStart$61$ScreenEditViewAnimatorHelper(this.f$1, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                AnimatorState unused = ScreenEditViewAnimatorHelper.this.mAnimatorState = AnimatorState.ANIMATOR_END;
                callback.onInvalidate();
            }

            public void onAnimationStart(Animator animator) {
                AnimatorState unused = ScreenEditViewAnimatorHelper.this.mAnimatorState = AnimatorState.ANIMATOR_START;
                callback.onAnimationStart();
            }
        });
        ofFloat.start();
    }

    private void changeRect(Callback callback, float f) {
        RectF rectF = this.mDstRect;
        float f2 = callback.getShowRect().left;
        int i = this.mStartLeft;
        rectF.left = (float) Math.round(((f2 - ((float) i)) * f) + ((float) i));
        RectF rectF2 = this.mDstRect;
        float f3 = callback.getShowRect().top;
        int i2 = this.mStartTop;
        rectF2.top = (float) Math.round(((f3 - ((float) i2)) * f) + ((float) i2));
        RectF rectF3 = this.mDstRect;
        float f4 = callback.getShowRect().right;
        int i3 = this.mStartRight;
        rectF3.right = (float) Math.round(((f4 - ((float) i3)) * f) + ((float) i3));
        RectF rectF4 = this.mDstRect;
        float f5 = callback.getShowRect().bottom;
        int i4 = this.mStartBottom;
        rectF4.bottom = (float) Math.round(((f5 - ((float) i4)) * f) + ((float) i4));
    }

    public void draw(Canvas canvas) {
        this.mMatrix.reset();
        if (this.mAnimatorState == AnimatorState.ANIMATOR_PRE_START || this.mAnimatorState == AnimatorState.ANIMATOR_UPDATE) {
            Bitmap bitmap = this.mPreBitmap;
            float f = this.mRoundRadius;
            int[] iArr = this.mThumbnailRect;
            Bitmap roundedCornerBitmap = BitmapUtils.getRoundedCornerBitmap(bitmap, f, iArr[2], iArr[3]);
            if (roundedCornerBitmap != null) {
                this.mMatrix.setRectToRect(new RectF(0.0f, 0.0f, (float) roundedCornerBitmap.getWidth(), (float) roundedCornerBitmap.getHeight()), this.mDstRect, Matrix.ScaleToFit.CENTER);
                canvas.drawBitmap(roundedCornerBitmap, this.mMatrix, (Paint) null);
                return;
            }
            return;
        }
        this.mMatrix.setRectToRect(new RectF(this.mSrcRect), this.mDstRect, Matrix.ScaleToFit.CENTER);
        canvas.drawBitmap(this.mPreBitmap, this.mMatrix, (Paint) null);
    }

    public boolean isAnimatorEnd() {
        return this.mAnimatorState == AnimatorState.ANIMATOR_END;
    }

    public /* synthetic */ void lambda$animatorStart$61$ScreenEditViewAnimatorHelper(Callback callback, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mAnimatorState = AnimatorState.ANIMATOR_UPDATE;
        this.mRoundRadius = ((float) this.mContext.getResources().getDimensionPixelSize(R.dimen.screen_editor_thumbnail_btn_radius)) * (1.0f - floatValue);
        changeRect(callback, floatValue);
        callback.onAnimationUpdate(floatValue);
        callback.onInvalidate();
    }

    public void startEnterAnimation(Context context, Callback callback, int[] iArr) {
        this.mContext = context;
        int i = 0;
        while (iArr != null && i < iArr.length) {
            this.mThumbnailRect[i] = iArr[i];
            i++;
        }
        this.mPreBitmap = callback.getOriginBitmap();
        animatorPreStart(callback);
    }
}
