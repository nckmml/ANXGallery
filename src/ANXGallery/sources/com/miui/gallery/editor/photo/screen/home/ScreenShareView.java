package com.miui.gallery.editor.photo.screen.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.ScreenUtils;
import miui.view.animation.QuarticEaseInOutInterpolator;

public class ScreenShareView extends View {
    /* access modifiers changed from: private */
    public AnimatorState mAnimatorState;
    private RectF mDstRect;
    private int mEnterFrom = 0;
    private Paint mPaint;
    private Matrix mResultMatrix = new Matrix();
    private RectF mResultRect;
    /* access modifiers changed from: private */
    public float mRoundRadius;
    private Bitmap mShareBitmap;
    private Rect mSrcRect;
    private int mThumbnailDstRectStartLeft;
    private int mThumbnailDstRectStartTop;
    /* access modifiers changed from: private */
    public int[] mThumbnailRect = new int[4];
    /* access modifiers changed from: private */
    public ValueAnimator mValueAnimator;

    public ScreenShareView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    /* access modifiers changed from: private */
    public void changeTransformRect(float f, int[] iArr) {
        RectF rectF = this.mDstRect;
        float f2 = this.mResultRect.left;
        int i = this.mThumbnailDstRectStartLeft;
        rectF.left = (float) ((int) (((f2 - ((float) i)) * f) + ((float) i)));
        RectF rectF2 = this.mDstRect;
        float f3 = this.mResultRect.top;
        int i2 = this.mThumbnailDstRectStartTop;
        rectF2.top = (float) ((int) (((f3 - ((float) i2)) * f) + ((float) i2)));
        RectF rectF3 = this.mDstRect;
        float f4 = this.mResultRect.right;
        int i3 = this.mThumbnailDstRectStartLeft;
        rectF3.right = (float) ((int) (((f4 - ((float) (iArr[2] + i3))) * f) + ((float) (i3 + iArr[2]))));
        RectF rectF4 = this.mDstRect;
        float f5 = this.mResultRect.bottom;
        int i4 = this.mThumbnailDstRectStartTop;
        rectF4.bottom = (float) ((int) (((f5 - ((float) (iArr[3] + i4))) * f) + ((float) (i4 + iArr[3]))));
    }

    private void init(Context context) {
        this.mPaint = new Paint(1);
        this.mSrcRect = new Rect();
        this.mDstRect = new RectF(0.0f, 0.0f, (float) ScreenUtils.getScreenWidth(), (float) ScreenUtils.getExactScreenHeight((Activity) context));
        this.mResultRect = new RectF();
    }

    private void refreshResultRectF() {
        this.mResultMatrix.reset();
        this.mResultMatrix.setRectToRect(new RectF(this.mSrcRect), this.mDstRect, Matrix.ScaleToFit.CENTER);
        this.mResultRect.set(this.mSrcRect);
        this.mResultMatrix.mapRect(this.mResultRect);
    }

    private void startAnimator(final ThumbnailAnimatorCallback thumbnailAnimatorCallback) {
        this.mValueAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mValueAnimator.setDuration(450);
        this.mValueAnimator.setInterpolator(new QuarticEaseInOutInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (thumbnailAnimatorCallback != null) {
                    AnimatorState unused = ScreenShareView.this.mAnimatorState = AnimatorState.ANIMATOR_UPDATE;
                    ScreenShareView screenShareView = ScreenShareView.this;
                    float unused2 = screenShareView.mRoundRadius = ((float) screenShareView.getResources().getDimensionPixelSize(R.dimen.screen_editor_thumbnail_btn_radius)) * (1.0f - floatValue);
                    ScreenShareView screenShareView2 = ScreenShareView.this;
                    screenShareView2.changeTransformRect(floatValue, screenShareView2.mThumbnailRect);
                    thumbnailAnimatorCallback.onAnimationUpdate(floatValue);
                    ScreenShareView.this.invalidate();
                }
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (thumbnailAnimatorCallback != null) {
                    AnimatorState unused = ScreenShareView.this.mAnimatorState = AnimatorState.ANIMATOR_END;
                    ScreenShareView.this.invalidate();
                }
            }

            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (thumbnailAnimatorCallback != null) {
                    AnimatorState unused = ScreenShareView.this.mAnimatorState = AnimatorState.ANIMATOR_START;
                    thumbnailAnimatorCallback.onAnimationStart();
                }
            }
        });
        postDelayed(new Runnable() {
            public void run() {
                ScreenShareView.this.mValueAnimator.start();
            }
        }, 30);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = this.mShareBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            if (this.mEnterFrom == 0) {
                canvas.drawBitmap(this.mShareBitmap, this.mSrcRect, this.mResultRect, this.mPaint);
            } else if (this.mAnimatorState == AnimatorState.ANIMATOR_END) {
                canvas.drawBitmap(this.mShareBitmap, this.mSrcRect, this.mDstRect, this.mPaint);
            } else if (this.mAnimatorState == AnimatorState.ANIMATOR_PRE_START || this.mAnimatorState == AnimatorState.ANIMATOR_UPDATE) {
                Bitmap bitmap2 = this.mShareBitmap;
                float f = this.mRoundRadius;
                int[] iArr = this.mThumbnailRect;
                Bitmap roundedCornerBitmap = BitmapUtils.getRoundedCornerBitmap(bitmap2, f, iArr[2], iArr[3]);
                canvas.drawBitmap(roundedCornerBitmap, new Rect(0, 0, roundedCornerBitmap.getWidth(), roundedCornerBitmap.getHeight()), this.mDstRect, this.mPaint);
            } else if (this.mAnimatorState == AnimatorState.ANIMATOR_START) {
                canvas.drawBitmap(this.mShareBitmap, this.mSrcRect, this.mDstRect, this.mPaint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mEnterFrom == 0) {
            this.mDstRect.set(0.0f, 0.0f, (float) i, (float) i2);
            refreshResultRectF();
        }
    }

    public void setShareBitmap(Bitmap bitmap, boolean z) {
        this.mShareBitmap = bitmap;
        this.mSrcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        refreshResultRectF();
        if (z) {
            invalidate();
        }
    }

    public void startShareViewAnimator(ThumbnailAnimatorCallback thumbnailAnimatorCallback) {
        this.mThumbnailDstRectStartLeft = thumbnailAnimatorCallback.getThumbnailStartLeft();
        this.mThumbnailDstRectStartTop = thumbnailAnimatorCallback.getThumbnailStartTop();
        this.mRoundRadius = (float) getResources().getDimensionPixelSize(R.dimen.screen_editor_thumbnail_btn_radius);
        this.mAnimatorState = AnimatorState.ANIMATOR_PRE_START;
        this.mThumbnailRect = thumbnailAnimatorCallback.getThumbnailRect();
        changeTransformRect(0.0f, this.mThumbnailRect);
        thumbnailAnimatorCallback.onPrepareAnimatorStart();
        this.mEnterFrom = 1;
        invalidate();
        startAnimator(thumbnailAnimatorCallback);
    }
}
