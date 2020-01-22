package com.miui.gallery.editor.photo.screen.crop;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import androidx.annotation.Keep;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.obsoletes.EventHandler;
import com.miui.gallery.editor.photo.core.imports.obsoletes.OneShotAnimateListener;
import com.miui.gallery.editor.photo.core.imports.obsoletes.RectFEvaluator;
import com.miui.gallery.editor.photo.core.imports.obsoletes.TranslateEvaluator;
import com.miui.gallery.editor.photo.screen.base.IScreenEditor;
import com.miui.gallery.editor.photo.screen.base.ScreenBaseGestureView;
import com.miui.gallery.editor.photo.screen.base.ScreenVirtualEditorView;
import com.miui.gallery.editor.photo.screen.home.ScreenEditorView;
import com.miui.gallery.util.Log;

public class ScreenCropView extends ScreenVirtualEditorView implements IScreenEditor {
    private AnimatorSet mAnimatorSet;
    private Rect mBgBounds = new Rect();
    private Rect mBgPadding = new Rect();
    /* access modifiers changed from: private */
    public BoundsFixCallback mBoundsFixCallback = new BoundsFixCallback() {
        public void onDone() {
            ScreenCropView.this.postCropFinish();
        }
    };
    private Rect mClipBounds = new Rect();
    /* access modifiers changed from: private */
    public RectF mCropArea = new RectF();
    /* access modifiers changed from: private */
    public boolean mCropAreaChanged = false;
    private Drawable mCropWindow;
    private Runnable mEditFinished = new Runnable() {
        public void run() {
            ScreenCropView.this.animCropAreaChanged((OneShotAnimateListener) null);
        }
    };
    /* access modifiers changed from: private */
    public EventHandler mEventHandler;
    /* access modifiers changed from: private */
    public EventState mEventState = EventState.IDLE;
    private boolean mFirstIn = true;
    protected ValueAnimator mImageAnimator;
    private Matrix mLastModifiedMatrix = new Matrix();
    private int mMaskColor;
    private int mMaskColorAlpha;
    private ObjectAnimator mMaskColorAnimator;
    private int mMaskGap;
    /* access modifiers changed from: private */
    public int mMsgCropFinish;
    /* access modifiers changed from: private */
    public PointF mOffset = new PointF();
    private Paint mPaint = new Paint();
    private Animator.AnimatorListener mPerformCropFinish = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            ScreenCropView.this.performCropFinished();
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
            ScreenCropView.this.mEventHandler.removeMessages(ScreenCropView.this.mMsgCropFinish);
        }
    };
    /* access modifiers changed from: private */
    public ResizeDetector mResizeDetector;
    /* access modifiers changed from: private */
    public float mResizeEdgeSlop;
    private Matrix mTempMatrix = new Matrix();
    private PropertyValuesHolder mTransValues;
    private ValueAnimator.AnimatorUpdateListener mTranslateUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ScreenCropView.this.setImageMatrix((Matrix) ScreenCropView.this.mImageAnimator.getAnimatedValue());
            ScreenCropView.this.invalidate();
        }
    };
    private Matrix mUnmodifiedMatrix = new Matrix();
    /* access modifiers changed from: private */
    public ValueAnimator mWindowAnimator;
    private ValueAnimator.AnimatorUpdateListener mWindowUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ScreenCropView.this.mCropArea.set((RectF) ScreenCropView.this.mWindowAnimator.getAnimatedValue());
            ScreenCropView.this.mEditorView.invalidate();
        }
    };
    private PropertyValuesHolder mWindowValues;

    protected interface BoundsFixCallback {
        void onDone();
    }

    private class BoundsFixListener extends OneShotAnimateListener {
        private BoundsFixCallback mCallback;

        public BoundsFixListener(BoundsFixCallback boundsFixCallback) {
            this.mCallback = boundsFixCallback;
        }

        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            this.mCallback.onDone();
        }
    }

    private enum EventState {
        IDLE,
        RESIZE,
        SCALE,
        TRANSLATE,
        ANIMATOR,
        ROTATION,
        SKIP
    }

    private class GesListener implements ScreenBaseGestureView.FeatureGesListener {
        private PointF mResizeStart;

        private GesListener() {
            this.mResizeStart = new PointF();
        }

        public void onActionUp(float f, float f2) {
            int unused = ScreenCropView.this.mResizeDetector.mResizeEdge = 0;
            EventState unused2 = ScreenCropView.this.mEventState = EventState.SKIP;
            ScreenCropView.this.mResizeDetector.fixImageBounds(ScreenCropView.this.mCropArea, ScreenCropView.this.mBoundsFixCallback);
            ScreenCropView.this.animTouchMaskColor(false);
        }

        public boolean onDown(MotionEvent motionEvent) {
            EventState unused = ScreenCropView.this.mEventState = EventState.IDLE;
            ScreenCropView.this.postCropProceed();
            int unused2 = ScreenCropView.this.mResizeDetector.mResizeEdge = ScreenCropView.this.mResizeDetector.resolveResizeEdge(motionEvent.getX(), motionEvent.getY());
            if (ScreenCropView.this.mResizeDetector.mResizeEdge == 0) {
                return false;
            }
            EventState unused3 = ScreenCropView.this.mEventState = EventState.RESIZE;
            this.mResizeStart.set(motionEvent.getX(), motionEvent.getY());
            ScreenCropView.this.animTouchMaskColor(true);
            boolean unused4 = ScreenCropView.this.mCropAreaChanged = true;
            return true;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            ScreenCropView.this.preTransform();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        public void onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            PointF access$3500 = ScreenCropView.this.mResizeDetector.onFreeResize(motionEvent2.getX() - this.mResizeStart.x, motionEvent2.getY() - this.mResizeStart.y);
            this.mResizeStart.x += access$3500.x;
            this.mResizeStart.y += access$3500.y;
            ScreenCropView.this.invalidate();
        }

        public void onSingleTapUp(MotionEvent motionEvent) {
        }
    }

    private class ResizeDetector {
        /* access modifiers changed from: private */
        public int mResizeEdge;

        private ResizeDetector() {
            this.mResizeEdge = 0;
        }

        private int calculateMinSize() {
            return Math.max((int) (ScreenCropView.this.getImageMatrix().mapRadius(ScreenCropView.this.calculateMinPixels()) + 0.5f), 200);
        }

        private float checkOtherBoundOffset(int i, float f, RectF rectF) {
            if ((i & 1) != 0) {
                float f2 = ScreenCropView.this.mCropArea.left - rectF.left;
                if (f2 <= 0.0f) {
                    return f;
                }
                if (f <= f2) {
                    f2 = f;
                }
                float f3 = f - f2;
                ScreenCropView.this.mCropArea.left -= f2;
                ScreenCropView.this.getImageMatrix().postTranslate(-f2, 0.0f);
                ScreenCropView.this.performCanvasMatrixChange();
                return f3;
            } else if ((i & 16) != 0) {
                float f4 = ScreenCropView.this.mCropArea.top - rectF.top;
                if (f4 <= 0.0f) {
                    return f;
                }
                if (f <= f4) {
                    f4 = f;
                }
                float f5 = f - f4;
                ScreenCropView.this.mCropArea.top -= f4;
                ScreenCropView.this.getImageMatrix().postTranslate(0.0f, -f4);
                ScreenCropView.this.performCanvasMatrixChange();
                return f5;
            } else if ((i & 256) != 0) {
                float f6 = rectF.right - ScreenCropView.this.mCropArea.right;
                if (f6 <= 0.0f) {
                    return f;
                }
                if (f <= f6) {
                    f6 = f;
                }
                float f7 = f - f6;
                ScreenCropView.this.mCropArea.right += f6;
                ScreenCropView.this.getImageMatrix().postTranslate(f6, 0.0f);
                ScreenCropView.this.performCanvasMatrixChange();
                return f7;
            } else if ((i & 4096) == 0) {
                return f;
            } else {
                float f8 = rectF.bottom - ScreenCropView.this.mCropArea.bottom;
                if (f8 <= 0.0f) {
                    return f;
                }
                if (f <= f8) {
                    f8 = f;
                }
                float f9 = f - f8;
                ScreenCropView.this.mCropArea.bottom += f8;
                ScreenCropView.this.getImageMatrix().postTranslate(0.0f, f8);
                ScreenCropView.this.performCanvasMatrixChange();
                return f9;
            }
        }

        private float constraint(float f, float f2) {
            float f3 = f2 + f;
            if (f * f3 < 0.0f) {
                return 0.0f;
            }
            return f3;
        }

        /* access modifiers changed from: private */
        public PointF onFreeResize(float f, float f2) {
            RectF access$900 = ScreenCropView.this.getBounds();
            RectF access$1000 = ScreenCropView.this.getImageBounds();
            RectF access$1100 = ScreenCropView.this.getImageDisplayRect();
            int calculateMinSize = calculateMinSize();
            int i = this.mResizeEdge;
            if ((i & 1) != 0) {
                float f3 = access$1100.left - ScreenCropView.this.mCropArea.left;
                float f4 = access$900.left - ScreenCropView.this.mCropArea.left;
                float constraint = constraint(ScreenCropView.this.mCropArea.right - ScreenCropView.this.mCropArea.left, (float) (-calculateMinSize));
                if (f < f3) {
                    f = f3;
                }
                if (f < f4) {
                    float checkOtherBoundOffset = checkOtherBoundOffset(256, f4 - f, access$900);
                    if (checkOtherBoundOffset > 0.0f) {
                        float width = 1.0f - (checkOtherBoundOffset / access$1000.width());
                        ScreenCropView.this.getBitmapGestureParamsHolder().performScale(width, ScreenCropView.this.mCropArea.right, (ScreenCropView.this.mCropArea.top + ScreenCropView.this.mCropArea.bottom) / 2.0f);
                        float f5 = 1.0f - width;
                        ScreenCropView.this.mCropArea.top += (ScreenCropView.this.mCropArea.height() * f5) / 2.0f;
                        ScreenCropView.this.mCropArea.bottom -= (f5 * ScreenCropView.this.mCropArea.height()) / 2.0f;
                    }
                    f = f4;
                } else if (f > constraint) {
                    f = constraint;
                }
                ScreenCropView.this.mCropArea.left += f;
            } else if ((i & 256) != 0) {
                float f6 = access$1100.right - ScreenCropView.this.mCropArea.right;
                float f7 = access$900.right - ScreenCropView.this.mCropArea.right;
                float constraint2 = constraint(ScreenCropView.this.mCropArea.left - ScreenCropView.this.mCropArea.right, (float) calculateMinSize);
                if (f > f6) {
                    f = f6;
                }
                if (f > f7) {
                    float checkOtherBoundOffset2 = checkOtherBoundOffset(1, f - f7, access$900);
                    if (checkOtherBoundOffset2 > 0.0f) {
                        float width2 = 1.0f - (checkOtherBoundOffset2 / access$1000.width());
                        ScreenCropView.this.getBitmapGestureParamsHolder().performScale(width2, ScreenCropView.this.mCropArea.left, (ScreenCropView.this.mCropArea.top + ScreenCropView.this.mCropArea.bottom) / 2.0f);
                        float f8 = 1.0f - width2;
                        ScreenCropView.this.mCropArea.top += (ScreenCropView.this.mCropArea.height() * f8) / 2.0f;
                        ScreenCropView.this.mCropArea.bottom -= (f8 * ScreenCropView.this.mCropArea.height()) / 2.0f;
                    }
                    f = f7;
                } else if (f < constraint2) {
                    f = constraint2;
                }
                ScreenCropView.this.mCropArea.right += f;
            }
            int i2 = this.mResizeEdge;
            if ((i2 & 16) != 0) {
                float f9 = access$1100.top - ScreenCropView.this.mCropArea.top;
                float f10 = access$900.top - ScreenCropView.this.mCropArea.top;
                float constraint3 = constraint(ScreenCropView.this.mCropArea.bottom - ScreenCropView.this.mCropArea.top, (float) (-calculateMinSize));
                if (f2 < f9) {
                    f2 = f9;
                }
                if (f2 < f10) {
                    float checkOtherBoundOffset3 = checkOtherBoundOffset(4096, f10 - f2, access$900);
                    if (checkOtherBoundOffset3 > 0.0f) {
                        float height = 1.0f - (checkOtherBoundOffset3 / access$1000.height());
                        ScreenCropView.this.getBitmapGestureParamsHolder().performScale(height, (ScreenCropView.this.mCropArea.left + ScreenCropView.this.mCropArea.right) / 2.0f, ScreenCropView.this.mCropArea.bottom);
                        float f11 = 1.0f - height;
                        ScreenCropView.this.mCropArea.left += (ScreenCropView.this.mCropArea.width() * f11) / 2.0f;
                        ScreenCropView.this.mCropArea.right -= (f11 * ScreenCropView.this.mCropArea.width()) / 2.0f;
                    }
                    f2 = f10;
                } else if (f2 > constraint3) {
                    f2 = constraint3;
                }
                ScreenCropView.this.mCropArea.top += f2;
            } else if ((i2 & 4096) != 0) {
                float f12 = access$1100.bottom - ScreenCropView.this.mCropArea.bottom;
                float f13 = access$900.bottom - ScreenCropView.this.mCropArea.bottom;
                float constraint4 = constraint(ScreenCropView.this.mCropArea.top - ScreenCropView.this.mCropArea.bottom, (float) calculateMinSize);
                if (f2 > f12) {
                    f2 = f12;
                }
                if (f2 > f13) {
                    float checkOtherBoundOffset4 = checkOtherBoundOffset(16, f2 - f13, access$900);
                    if (checkOtherBoundOffset4 > 0.0f) {
                        float height2 = 1.0f - (checkOtherBoundOffset4 / access$1000.height());
                        ScreenCropView.this.getBitmapGestureParamsHolder().performScale(height2, (ScreenCropView.this.mCropArea.left + ScreenCropView.this.mCropArea.right) / 2.0f, ScreenCropView.this.mCropArea.top);
                        float f14 = 1.0f - height2;
                        ScreenCropView.this.mCropArea.left += (ScreenCropView.this.mCropArea.width() * f14) / 2.0f;
                        ScreenCropView.this.mCropArea.right -= (f14 * ScreenCropView.this.mCropArea.width()) / 2.0f;
                    }
                    f2 = f13;
                } else if (f2 < constraint4) {
                    f2 = constraint4;
                }
                ScreenCropView.this.mCropArea.bottom += f2;
            }
            ScreenCropView.this.mOffset.set(f, f2);
            return ScreenCropView.this.mOffset;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x00f8  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x00fb  */
        public int resolveResizeEdge(float f, float f2) {
            int i;
            float f3 = ((ScreenCropView.this.mCropArea.right - ScreenCropView.this.mCropArea.left) * 0.5f) + ScreenCropView.this.mCropArea.left;
            float f4 = ((ScreenCropView.this.mCropArea.bottom - ScreenCropView.this.mCropArea.top) * 0.5f) + ScreenCropView.this.mCropArea.top;
            float min = Math.min(ScreenCropView.this.mResizeEdgeSlop, ScreenCropView.this.mCropArea.height() / 3.0f);
            float min2 = Math.min(ScreenCropView.this.mResizeEdgeSlop, ScreenCropView.this.mCropArea.width() / 3.0f);
            if (f2 > f4 - ScreenCropView.this.mResizeEdgeSlop && f2 < f4 + ScreenCropView.this.mResizeEdgeSlop) {
                if (f > ScreenCropView.this.mCropArea.left - ScreenCropView.this.mResizeEdgeSlop && f < ScreenCropView.this.mCropArea.left + min2) {
                    i = 1;
                    if (f2 > ScreenCropView.this.mCropArea.top - ScreenCropView.this.mResizeEdgeSlop || f2 < ScreenCropView.this.mCropArea.top + min) {
                    }
                } else if (f > ScreenCropView.this.mCropArea.right - min2 && f < ScreenCropView.this.mCropArea.right + ScreenCropView.this.mResizeEdgeSlop) {
                    i = 256;
                    if (f > f3 - ScreenCropView.this.mResizeEdgeSlop && f < f3 + ScreenCropView.this.mResizeEdgeSlop) {
                        if (f2 > ScreenCropView.this.mCropArea.top - ScreenCropView.this.mResizeEdgeSlop && f2 < ScreenCropView.this.mCropArea.top + min) {
                            i |= 16;
                        } else if (f2 > ScreenCropView.this.mCropArea.bottom - min && f2 < ScreenCropView.this.mCropArea.bottom + ScreenCropView.this.mResizeEdgeSlop) {
                            i |= 4096;
                        }
                    }
                    if (f2 > ScreenCropView.this.mCropArea.top - ScreenCropView.this.mResizeEdgeSlop && f2 < ScreenCropView.this.mCropArea.top + ScreenCropView.this.mResizeEdgeSlop) {
                        if (f > ScreenCropView.this.mCropArea.left - ScreenCropView.this.mResizeEdgeSlop && f < ScreenCropView.this.mCropArea.left + min2) {
                            i |= 1;
                        } else if (f > ScreenCropView.this.mCropArea.right - min2 && f < ScreenCropView.this.mCropArea.right + ScreenCropView.this.mResizeEdgeSlop) {
                            i |= 256;
                        }
                    }
                    if (f2 > ScreenCropView.this.mCropArea.bottom - ScreenCropView.this.mResizeEdgeSlop && f2 < ScreenCropView.this.mCropArea.bottom + ScreenCropView.this.mResizeEdgeSlop) {
                        if (f > ScreenCropView.this.mCropArea.left - ScreenCropView.this.mResizeEdgeSlop && f < ScreenCropView.this.mCropArea.left + min2) {
                            i |= 1;
                        } else if (f > ScreenCropView.this.mCropArea.right - min2 && f < ScreenCropView.this.mCropArea.right + ScreenCropView.this.mResizeEdgeSlop) {
                            i |= 256;
                        }
                    }
                    if (f > ScreenCropView.this.mCropArea.left - ScreenCropView.this.mResizeEdgeSlop && f < ScreenCropView.this.mCropArea.left + ScreenCropView.this.mResizeEdgeSlop) {
                        if (f2 > ScreenCropView.this.mCropArea.top - ScreenCropView.this.mResizeEdgeSlop && f2 < ScreenCropView.this.mCropArea.top + min) {
                            i |= 16;
                        } else if (f2 > ScreenCropView.this.mCropArea.bottom - min && f2 < ScreenCropView.this.mCropArea.bottom + ScreenCropView.this.mResizeEdgeSlop) {
                            i |= 4096;
                        }
                    }
                    return (f <= ScreenCropView.this.mCropArea.right - ScreenCropView.this.mResizeEdgeSlop || f >= ScreenCropView.this.mCropArea.right + ScreenCropView.this.mResizeEdgeSlop) ? i : (f2 <= ScreenCropView.this.mCropArea.top - ScreenCropView.this.mResizeEdgeSlop || f2 >= ScreenCropView.this.mCropArea.top + min) ? (f2 <= ScreenCropView.this.mCropArea.bottom - min || f2 >= ScreenCropView.this.mCropArea.bottom + ScreenCropView.this.mResizeEdgeSlop) ? i : i | 4096 : i | 16;
                }
            }
            i = 0;
            if (f2 > ScreenCropView.this.mCropArea.top - ScreenCropView.this.mResizeEdgeSlop || f2 < ScreenCropView.this.mCropArea.top + min) {
            }
        }

        /* access modifiers changed from: protected */
        public void fixImageBounds(RectF rectF, BoundsFixCallback boundsFixCallback) {
            Matrix access$2200 = ScreenCropView.this.getImageMatrix();
            RectF rectF2 = new RectF(ScreenCropView.this.getImageDisplayRect());
            if (!rectF2.contains(rectF)) {
                Matrix matrix = new Matrix(access$2200);
                Matrix matrix2 = new Matrix(access$2200);
                if (rectF.width() > rectF2.width() || rectF.height() > rectF2.height()) {
                    float resolveScale = ScreenCropView.resolveScale(rectF2, rectF);
                    matrix2.postScale(resolveScale, resolveScale, rectF.centerX(), rectF.centerY());
                    matrix2.mapRect(rectF2, ScreenCropView.this.getBitmapDisplayInitRect());
                }
                if (!rectF2.contains(rectF)) {
                    PointF pointF = new PointF();
                    ScreenCropView.resolveTranslate(rectF2, rectF, pointF);
                    matrix2.postTranslate(pointF.x, pointF.y);
                    Log.d("ScreenCropView", "fixImageBounds %f,%f", Float.valueOf(pointF.x), Float.valueOf(pointF.y));
                }
                ScreenCropView screenCropView = ScreenCropView.this;
                screenCropView.setupImageAnimator(matrix, matrix2, boundsFixCallback == null ? null : new BoundsFixListener(boundsFixCallback));
                ScreenCropView.this.mImageAnimator.start();
            } else if (boundsFixCallback != null) {
                boundsFixCallback.onDone();
            }
        }
    }

    public ScreenCropView(ScreenEditorView screenEditorView) {
        super(screenEditorView);
        this.mEditorView.setCropGestureListener(new GesListener());
        this.mEventHandler = new EventHandler();
        this.mCropWindow = this.mContext.getResources().getDrawable(R.drawable.screen_crop_window);
        this.mCropWindow.getPadding(this.mBgPadding);
        this.mMaskColor = this.mContext.getResources().getColor(R.color.frame_editor_view_background);
        this.mMaskColorAlpha = 229;
        this.mResizeEdgeSlop = this.mContext.getResources().getDimension(R.dimen.screen_editor_crop_menu_bound_touch_width);
        this.mResizeDetector = new ResizeDetector();
        this.mMsgCropFinish = this.mEventHandler.register(this.mEditFinished);
        this.mMaskGap = GalleryApp.sGetAndroidContext().getResources().getDimensionPixelSize(R.dimen.photo_screen_edit_distance);
    }

    /* access modifiers changed from: private */
    public void animCropAreaChanged(OneShotAnimateListener oneShotAnimateListener) {
        Log.d("ScreenCropView", "animCropAreaChanged");
        Matrix matrix = new Matrix(getImageMatrix());
        Matrix matrix2 = new Matrix(getImageMatrix());
        RectF rectF = new RectF(this.mCropArea);
        RectF rectF2 = new RectF();
        this.mTempMatrix.setRectToRect(this.mCropArea, getBounds(), Matrix.ScaleToFit.CENTER);
        this.mTempMatrix.mapRect(rectF2, this.mCropArea);
        matrix2.postConcat(this.mTempMatrix);
        setupImageAnimator(matrix, matrix2, (OneShotAnimateListener) null);
        setupWindowAnimator(rectF, rectF2, (OneShotAnimateListener) null);
        setupAnimatorSet(this.mImageAnimator, this.mWindowAnimator, oneShotAnimateListener);
        this.mAnimatorSet.start();
    }

    /* access modifiers changed from: private */
    public void animTouchMaskColor(boolean z) {
        ObjectAnimator objectAnimator = this.mMaskColorAnimator;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.mMaskColorAnimator.cancel();
        }
        if (z) {
            this.mMaskColorAnimator = ObjectAnimator.ofInt(this, "maskColorAlpha", new int[]{this.mMaskColorAlpha, 127});
        } else {
            this.mMaskColorAnimator = ObjectAnimator.ofInt(this, "maskColorAlpha", new int[]{this.mMaskColorAlpha, 229});
        }
        this.mMaskColorAnimator.setDuration(300);
        this.mMaskColorAnimator.start();
    }

    /* access modifiers changed from: private */
    public float calculateMinPixels() {
        return Math.max(32.0f, Math.max(getImageBounds().height(), getImageBounds().width()) / 10.0f);
    }

    /* access modifiers changed from: private */
    public void performCropFinished() {
        this.mCropAreaChanged = false;
    }

    /* access modifiers changed from: private */
    public void postCropFinish() {
        if (this.mCropAreaChanged) {
            this.mEventHandler.sendEmptyMessageDelayed(this.mMsgCropFinish, 1000);
        }
    }

    /* access modifiers changed from: private */
    public void postCropProceed() {
        if (this.mCropAreaChanged) {
            this.mEventHandler.removeMessages(this.mMsgCropFinish);
        }
    }

    /* access modifiers changed from: private */
    public void preTransform() {
        postCropProceed();
        ValueAnimator valueAnimator = this.mImageAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.mImageAnimator.cancel();
        }
    }

    protected static float resolveScale(RectF rectF, RectF rectF2) {
        float width = rectF2.width() > rectF.width() ? rectF2.width() / rectF.width() : 1.0f;
        return rectF2.height() > rectF.height() ? Math.max(width, rectF2.height() / rectF.height()) : width;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    protected static void resolveTranslate(RectF rectF, RectF rectF2, PointF pointF) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6 = 0.0f;
        if (!rectF.contains(rectF2)) {
            if (rectF2.top < rectF.top) {
                f4 = rectF2.top;
                f5 = rectF.top;
            } else if (rectF2.bottom > rectF.bottom) {
                f4 = rectF2.bottom;
                f5 = rectF.bottom;
            } else {
                f = 0.0f;
                if (rectF2.left >= rectF.left) {
                    f2 = rectF2.left;
                    f3 = rectF.left;
                } else {
                    if (rectF2.right > rectF.right) {
                        f2 = rectF2.right;
                        f3 = rectF.right;
                    }
                    pointF.x = f6;
                    pointF.y = f;
                    return;
                }
                f6 = f2 - f3;
                pointF.x = f6;
                pointF.y = f;
                return;
            }
            f = f4 - f5;
            if (rectF2.left >= rectF.left) {
            }
            f6 = f2 - f3;
            pointF.x = f6;
            pointF.y = f;
            return;
        }
        pointF.x = 0.0f;
        pointF.y = 0.0f;
    }

    private void setupAnimatorSet(Animator animator, Animator animator2, OneShotAnimateListener oneShotAnimateListener) {
        if (this.mAnimatorSet == null) {
            this.mAnimatorSet = new AnimatorSet();
            this.mAnimatorSet.playTogether(new Animator[]{animator, animator2});
            this.mAnimatorSet.addListener(this.mPerformCropFinish);
        }
        this.mAnimatorSet.setDuration(300);
        if (oneShotAnimateListener != null) {
            this.mAnimatorSet.addListener(oneShotAnimateListener);
        }
    }

    private void setupWindowAnimator(RectF rectF, RectF rectF2, OneShotAnimateListener oneShotAnimateListener) {
        if (this.mWindowAnimator == null) {
            this.mWindowAnimator = new ValueAnimator();
            this.mWindowValues = PropertyValuesHolder.ofObject("window", new RectFEvaluator(), new Object[]{rectF, rectF2});
            this.mWindowAnimator.addUpdateListener(this.mWindowUpdateListener);
        }
        this.mWindowAnimator.setDuration(300);
        this.mWindowValues.setObjectValues(new Object[]{rectF, rectF2});
        this.mWindowAnimator.setValues(new PropertyValuesHolder[]{this.mWindowValues});
        if (oneShotAnimateListener != null) {
            this.mWindowAnimator.addListener(oneShotAnimateListener);
        }
    }

    public void canvasMatrixChange() {
    }

    public void drawOverlay(Canvas canvas) {
        this.mBgBounds.set(Math.round(this.mCropArea.left - ((float) this.mBgPadding.left)), Math.round(this.mCropArea.top - ((float) this.mBgPadding.top)), Math.round(this.mCropArea.right + ((float) this.mBgPadding.right)), Math.round(this.mCropArea.bottom + ((float) this.mBgPadding.bottom)));
        this.mCropWindow.setBounds(this.mBgBounds);
        float f = getImageDisplayRect().top;
        float f2 = f > 0.0f ? 0.0f : f;
        canvas.getClipBounds(this.mClipBounds);
        this.mPaint.setColor(this.mMaskColor);
        this.mPaint.setAlpha(this.mMaskColorAlpha);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0.0f, f2, (float) this.mClipBounds.right, (float) (this.mBgBounds.top + this.mMaskGap), this.mPaint);
        canvas.drawRect(0.0f, (float) (this.mBgBounds.top + this.mMaskGap), (float) (this.mBgBounds.left + this.mMaskGap), (float) (this.mBgBounds.bottom - this.mMaskGap), this.mPaint);
        canvas.drawRect((float) (this.mBgBounds.right - this.mMaskGap), (float) (this.mBgBounds.top + this.mMaskGap), (float) this.mClipBounds.right, (float) (this.mBgBounds.bottom - this.mMaskGap), this.mPaint);
        canvas.drawRect(0.0f, (float) (this.mBgBounds.bottom - this.mMaskGap), (float) this.mClipBounds.right, (float) this.mClipBounds.bottom, this.mPaint);
        this.mCropWindow.draw(canvas);
    }

    public ScreenCropEntry export() {
        ScreenCropEntry screenCropEntry = isModified() ? new ScreenCropEntry(this.mCropArea, getBitmapGestureParamsHolder().mBitmapDisplayRect) : null;
        if (this.mCropAreaChanged) {
            this.mEventHandler.removeMessages(this.mMsgCropFinish);
            this.mCropAreaChanged = false;
        }
        return screenCropEntry;
    }

    public boolean isModified() {
        return !getImageMatrix().equals(this.mUnmodifiedMatrix) || this.mCropAreaChanged;
    }

    public boolean isModifiedBaseLast() {
        if (getImageMatrix().equals(this.mLastModifiedMatrix) && !this.mCropAreaChanged) {
            return false;
        }
        this.mLastModifiedMatrix.set(getImageMatrix());
        return true;
    }

    public void onDetachedFromWindow() {
        this.mEventHandler.removeMessages(this.mMsgCropFinish);
        this.mEventHandler.unregister(this.mMsgCropFinish);
        this.mMsgCropFinish = 0;
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null && animatorSet.isStarted()) {
            this.mAnimatorSet.cancel();
        }
        ValueAnimator valueAnimator = this.mImageAnimator;
        if (valueAnimator != null && valueAnimator.isStarted()) {
            this.mImageAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mWindowAnimator;
        if (valueAnimator2 != null && valueAnimator2.isStarted()) {
            this.mWindowAnimator.cancel();
        }
        ObjectAnimator objectAnimator = this.mMaskColorAnimator;
        if (objectAnimator != null && objectAnimator.isStarted()) {
            this.mMaskColorAnimator.cancel();
        }
    }

    public void onStart() {
        if (this.mFirstIn) {
            this.mFirstIn = false;
        }
        reset();
    }

    public void reset() {
        this.mCropArea.set(getImageDisplayRect());
        this.mUnmodifiedMatrix.set(getImageMatrix());
    }

    @Keep
    public void setMaskColorAlpha(int i) {
        this.mMaskColorAlpha = i;
        this.mEditorView.invalidate();
    }

    /* access modifiers changed from: protected */
    public void setupImageAnimator(Matrix matrix, Matrix matrix2, OneShotAnimateListener oneShotAnimateListener) {
        if (this.mImageAnimator == null) {
            this.mImageAnimator = new ValueAnimator();
            this.mTransValues = PropertyValuesHolder.ofObject("matrix", new TranslateEvaluator(), new Object[]{matrix, matrix2});
            this.mImageAnimator.setObjectValues(new Object[]{this.mTransValues});
            this.mImageAnimator.addUpdateListener(this.mTranslateUpdateListener);
        }
        this.mTransValues.setObjectValues(new Object[]{matrix, matrix2});
        this.mImageAnimator.setDuration(300);
        this.mImageAnimator.setValues(new PropertyValuesHolder[]{this.mTransValues});
        if (oneShotAnimateListener != null) {
            this.mImageAnimator.addListener(oneShotAnimateListener);
        }
    }
}
