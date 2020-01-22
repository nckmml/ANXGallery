package com.miui.gallery.collage.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import com.miui.gallery.collage.render.PosterElementRender;
import com.miui.gallery.collage.utils.TextEditDialog;

public class PosterLayout extends ViewGroup {
    public static final float[] DEFAULT_LAYOUT_PARAMS = {0.0f, 0.0f, 1.0f, 1.0f};
    /* access modifiers changed from: private */
    public PosterElementRender.TextEditorHolder mActiveTextEditorHolder;
    private Context mContext;
    private GesListener mGesListener = new GesListener();
    private GestureDetector mGestureDetector;
    private Paint mPaint;
    private PosterElementRender mPosterElementRender;
    /* access modifiers changed from: private */
    public Rect mRectTemp = new Rect();
    private ValueAnimator mTextAnimator;
    private TextEditDialog mTextEditDialog = new TextEditDialog();
    private CustomTextWatch mTextWatch = new CustomTextWatch();

    private class AnimListener implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        private AnimListener() {
        }

        public void onAnimationCancel(Animator animator) {
            PosterLayout.this.setTextAnimProgress(0.0f);
            PosterLayout.this.invalidate();
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            PosterLayout.this.setTextAnimProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
            PosterLayout.this.invalidate();
        }
    }

    private class CustomTextWatch implements TextWatcher {
        private CustomTextWatch() {
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (PosterLayout.this.mActiveTextEditorHolder != null) {
                PosterLayout.this.mActiveTextEditorHolder.setTextAndCount(charSequence.toString(), PosterLayout.this.mRectTemp);
                PosterLayout.this.invalidate();
            }
        }
    }

    private class GesListener implements GestureDetector.OnGestureListener {
        private GesListener() {
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            PosterLayout posterLayout = PosterLayout.this;
            PosterElementRender.TextEditorHolder unused = posterLayout.mActiveTextEditorHolder = posterLayout.findTextHolderByTouchEvent(motionEvent.getX(), motionEvent.getY());
            if (PosterLayout.this.mActiveTextEditorHolder != null) {
                PosterLayout posterLayout2 = PosterLayout.this;
                posterLayout2.showDialog(posterLayout2.mActiveTextEditorHolder.getCurrentText(), !PosterLayout.this.mActiveTextEditorHolder.hasModify(), PosterLayout.this.mActiveTextEditorHolder.getMaxSize());
            }
            return true;
        }
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        /* access modifiers changed from: private */
        public float mBottom;
        /* access modifiers changed from: private */
        public float mLeft;
        /* access modifiers changed from: private */
        public float mRight;
        /* access modifiers changed from: private */
        public float mTop;

        public LayoutParams(float... fArr) {
            super(0, 0);
            this.mLeft = fArr[0];
            this.mTop = fArr[1];
            this.mRight = fArr[2];
            this.mBottom = fArr[3];
        }
    }

    public PosterLayout(Context context) {
        super(context);
        init(context);
    }

    public PosterLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PosterLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void drawElement(Canvas canvas) {
        PosterElementRender posterElementRender = this.mPosterElementRender;
        if (posterElementRender != null) {
            posterElementRender.draw(canvas);
        }
    }

    private void init(Context context) {
        this.mContext = context;
        setWillNotDraw(false);
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mGestureDetector = new GestureDetector(this.mContext, this.mGesListener);
        this.mTextEditDialog.setTextWatch(this.mTextWatch);
    }

    private void notifyTextEdit() {
        ValueAnimator valueAnimator = this.mTextAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        } else {
            AnimListener animListener = new AnimListener();
            this.mTextAnimator = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f, 1.0f, 0.0f});
            this.mTextAnimator.setDuration(3000);
            this.mTextAnimator.setRepeatCount(0);
            this.mTextAnimator.setInterpolator(new LinearInterpolator());
            this.mTextAnimator.addUpdateListener(animListener);
            this.mTextAnimator.addListener(animListener);
        }
        PosterElementRender.TextEditorHolder[] textEditorHolders = this.mPosterElementRender.getTextEditorHolders();
        if (textEditorHolders != null && textEditorHolders.length > 0) {
            this.mTextAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public void setTextAnimProgress(float f) {
        PosterElementRender.TextEditorHolder[] textEditorHolders;
        PosterElementRender posterElementRender = this.mPosterElementRender;
        if (posterElementRender != null && (textEditorHolders = posterElementRender.getTextEditorHolders()) != null && textEditorHolders.length > 0) {
            for (PosterElementRender.TextEditorHolder currentTextProgress : textEditorHolders) {
                currentTextProgress.setCurrentTextProgress(f);
            }
        }
    }

    /* access modifiers changed from: private */
    public void showDialog(String str, boolean z, int i) {
        if ((this.mContext instanceof Activity) && !this.mTextEditDialog.isShowing()) {
            this.mTextEditDialog.setWillEditText(str, z);
            this.mTextEditDialog.setMaxEditLength(i);
            this.mTextEditDialog.showAllowingStateLoss(((Activity) this.mContext).getFragmentManager(), "PosterLayout");
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: package-private */
    public PosterElementRender.TextEditorHolder findTextHolderByTouchEvent(float f, float f2) {
        PosterElementRender.TextEditorHolder[] textEditorHolders;
        PosterElementRender posterElementRender = this.mPosterElementRender;
        if (!(posterElementRender == null || (textEditorHolders = posterElementRender.getTextEditorHolders()) == null || textEditorHolders.length <= 0)) {
            for (PosterElementRender.TextEditorHolder textEditorHolder : textEditorHolders) {
                if (textEditorHolder.contains(f, f2)) {
                    return textEditorHolder;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(DEFAULT_LAYOUT_PARAMS);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(DEFAULT_LAYOUT_PARAMS);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        PosterElementRender posterElementRender = this.mPosterElementRender;
        if (posterElementRender != null) {
            canvas.drawColor(posterElementRender.getBackground());
            drawElement(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int height = getHeight();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                float f = (float) width;
                float f2 = (float) height;
                childAt.layout((int) (layoutParams.mLeft * f), (int) (layoutParams.mTop * f2), (int) (f * layoutParams.mRight), (int) (f2 * layoutParams.mBottom));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildren(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    public void setRenderData(PosterElementRender posterElementRender) {
        ValueAnimator valueAnimator = this.mTextAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mPosterElementRender = posterElementRender;
        if (this.mPosterElementRender != null) {
            notifyTextEdit();
        }
        invalidate();
    }
}
