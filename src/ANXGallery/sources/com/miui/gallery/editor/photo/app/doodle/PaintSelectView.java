package com.miui.gallery.editor.photo.app.doodle;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.miui.gallery.R;
import java.util.List;
import miui.view.animation.SineEaseOutInterpolator;

public class PaintSelectView extends View {
    private Rect mBasePaintRect = new Rect();
    private Context mContext;
    private int mCurrentPaintIndex = 1;
    /* access modifiers changed from: private */
    public List<DoodlePaintItem> mDoodlePaintItemList;
    private GesListener mGesListener = new GesListener();
    private GestureDetector mGestureDetector;
    private Handler mHandler = new Handler();
    private DecelerateInterpolator mInterpolator = new DecelerateInterpolator();
    /* access modifiers changed from: private */
    public boolean mIsSelectMode = false;
    private int mItemOffset;
    private ValueAnimator mPaintAnimator;
    private PaintAnimatorListener mPaintAnimatorListener = new PaintAnimatorListener();
    private int mPaintBottomOffset;
    private int mPaintDrawableWidth;
    private int mPaintRightOffset;
    private PaintSizeChangeListener mPaintSizeChangeListener;

    private class GesListener implements GestureDetector.OnGestureListener {
        private int mIndex;

        private GesListener() {
            this.mIndex = -1;
        }

        public boolean onDown(MotionEvent motionEvent) {
            this.mIndex = PaintSelectView.this.findTouchIndex(motionEvent.getX(), motionEvent.getY());
            if (this.mIndex != -1) {
                return true;
            }
            PaintSelectView.this.expansionPaint(false);
            return false;
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
            if (this.mIndex == -1) {
                return false;
            }
            if (PaintSelectView.this.mIsSelectMode) {
                PaintSelectView.this.setSelectIndex(this.mIndex);
            } else {
                PaintSelectView.this.expansionPaint(true);
            }
            return true;
        }
    }

    private class PaintAnimatorListener implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
        private PaintAnimatorListener() {
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            for (int i = 0; i < PaintSelectView.this.mDoodlePaintItemList.size(); i++) {
                DoodlePaintItem doodlePaintItem = (DoodlePaintItem) PaintSelectView.this.mDoodlePaintItemList.get(i);
                float floatValue = ((Float) valueAnimator.getAnimatedValue(String.valueOf(i))).floatValue();
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue("progress")).floatValue();
                doodlePaintItem.offset((int) (floatValue - ((float) doodlePaintItem.centerX())), 0);
                doodlePaintItem.setAlpha(floatValue2);
            }
            PaintSelectView.this.invalidate();
        }
    }

    public interface PaintSizeChangeListener {
        void onPaintSizeChange(float f);
    }

    public PaintSelectView(Context context) {
        super(context);
        init(context);
    }

    public PaintSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PaintSelectView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void doSelectAnimator(final DoodlePaintItem doodlePaintItem) {
        ValueAnimator doodlePaintItemSizeAnim = getDoodlePaintItemSizeAnim(doodlePaintItem, 1.0f, 1.1f);
        doodlePaintItemSizeAnim.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                PaintSelectView.this.getDoodlePaintItemSizeAnim(doodlePaintItem, 1.1f, 1.0f).start();
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        });
        doodlePaintItemSizeAnim.start();
    }

    private void drawPaint(Canvas canvas) {
        for (int i = 0; i < this.mDoodlePaintItemList.size(); i++) {
            if (i != this.mCurrentPaintIndex) {
                this.mDoodlePaintItemList.get(i).draw(canvas);
            }
        }
        this.mDoodlePaintItemList.get(this.mCurrentPaintIndex).draw(canvas);
    }

    /* access modifiers changed from: private */
    public int findTouchIndex(float f, float f2) {
        for (int i = 0; i < this.mDoodlePaintItemList.size(); i++) {
            if (this.mDoodlePaintItemList.get(i).isContain(f, f2)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public ValueAnimator getDoodlePaintItemSizeAnim(final DoodlePaintItem doodlePaintItem, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(new SineEaseOutInterpolator());
        ofFloat.setDuration(150);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                doodlePaintItem.setScale(((Float) valueAnimator.getAnimatedValue()).floatValue());
                PaintSelectView.this.invalidate();
            }
        });
        return ofFloat;
    }

    private void init(Context context) {
        this.mContext = context;
        initPaint();
        this.mGestureDetector = new GestureDetector(this.mContext, this.mGesListener);
        this.mGestureDetector.setIsLongpressEnabled(false);
    }

    private void initPaint() {
        this.mDoodlePaintItemList = DoodlePaintItem.getList(getResources());
        this.mPaintDrawableWidth = this.mDoodlePaintItemList.get(0).getIntrinsicWidth();
        this.mPaintRightOffset = this.mContext.getResources().getDimensionPixelSize(R.dimen.doodle_paint_select_right_offset);
        this.mPaintBottomOffset = this.mContext.getResources().getDimensionPixelSize(R.dimen.doodle_paint_select_bottom_offset);
        this.mDoodlePaintItemList.get(this.mCurrentPaintIndex).setSelect(true);
        this.mItemOffset = this.mContext.getResources().getDimensionPixelSize(R.dimen.doodle_paint_item_offset);
    }

    private void setPaintLocation(int i, int i2) {
        Rect rect = this.mBasePaintRect;
        int i3 = this.mPaintDrawableWidth;
        rect.set(i - i3, i2 - i3, i, i2);
        this.mBasePaintRect.offset(-this.mPaintRightOffset, -this.mPaintBottomOffset);
        for (int i4 = 0; i4 < this.mDoodlePaintItemList.size(); i4++) {
            this.mDoodlePaintItemList.get(i4).setBounds(this.mBasePaintRect);
        }
    }

    /* access modifiers changed from: private */
    public void setSelectIndex(int i) {
        this.mDoodlePaintItemList.get(this.mCurrentPaintIndex).setSelect(false);
        this.mCurrentPaintIndex = i;
        DoodlePaintItem doodlePaintItem = this.mDoodlePaintItemList.get(this.mCurrentPaintIndex);
        doodlePaintItem.setSelect(true);
        PaintSizeChangeListener paintSizeChangeListener = this.mPaintSizeChangeListener;
        if (paintSizeChangeListener != null) {
            paintSizeChangeListener.onPaintSizeChange(doodlePaintItem.paintType.paintSize);
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
        invalidate();
        doSelectAnimator(doodlePaintItem);
    }

    public void expansionPaint(boolean z) {
        this.mIsSelectMode = z;
        ValueAnimator valueAnimator = this.mPaintAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        } else {
            this.mPaintAnimator = new ValueAnimator();
        }
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[(this.mDoodlePaintItemList.size() + 1)];
        for (int i = 0; i < this.mDoodlePaintItemList.size(); i++) {
            propertyValuesHolderArr[i] = PropertyValuesHolder.ofFloat(String.valueOf(i), new float[]{(float) this.mDoodlePaintItemList.get(i).centerX(), (float) (z ? this.mBasePaintRect.centerX() - ((this.mPaintDrawableWidth + this.mItemOffset) * i) : this.mBasePaintRect.centerX())});
        }
        if (z) {
            propertyValuesHolderArr[propertyValuesHolderArr.length - 1] = PropertyValuesHolder.ofFloat("progress", new float[]{0.0f, 1.0f});
        } else {
            propertyValuesHolderArr[propertyValuesHolderArr.length - 1] = PropertyValuesHolder.ofFloat("progress", new float[]{1.0f, 0.0f});
        }
        this.mPaintAnimator.setValues(propertyValuesHolderArr);
        this.mPaintAnimator.addUpdateListener(this.mPaintAnimatorListener);
        this.mPaintAnimator.addListener(this.mPaintAnimatorListener);
        this.mPaintAnimator.setInterpolator(this.mInterpolator);
        this.mPaintAnimator.setDuration(300);
        this.mPaintAnimator.start();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawPaint(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setPaintLocation(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    public void setPaintColor(int i) {
        for (DoodlePaintItem currentColor : this.mDoodlePaintItemList) {
            currentColor.setCurrentColor(i);
        }
        invalidate();
    }

    public void setPaintSizeChangeListener(PaintSizeChangeListener paintSizeChangeListener) {
        this.mPaintSizeChangeListener = paintSizeChangeListener;
        PaintSizeChangeListener paintSizeChangeListener2 = this.mPaintSizeChangeListener;
        if (paintSizeChangeListener2 != null) {
            paintSizeChangeListener2.onPaintSizeChange(this.mDoodlePaintItemList.get(this.mCurrentPaintIndex).paintType.paintSize);
        }
    }
}
