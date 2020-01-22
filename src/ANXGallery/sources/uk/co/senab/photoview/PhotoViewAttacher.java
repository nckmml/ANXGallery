package uk.co.senab.photoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import com.android.internal.CompatHandler;
import com.miui.gallery.BaseConfig;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.MatrixUtil;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.photoview.BitmapRecycleCallback;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.util.photoview.TileBitProvider;
import com.miui.gallery.util.photoview.TileView;
import com.miui.gallery.util.photoview.TrimMemoryCallback;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import miui.gallery.support.linearmotor.LinearMotorHelper;
import miui.view.animation.CubicEaseOutInterpolator;
import uk.co.senab.photoview.gestures.OnGestureListener;
import uk.co.senab.photoview.gestures.RotateGestureDetector;
import uk.co.senab.photoview.gestures.VersionedGestureDetector;
import uk.co.senab.photoview.log.LogManager;
import uk.co.senab.photoview.scrollerproxy.ScrollerProxy;

public class PhotoViewAttacher implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener, IPhotoView, OnGestureListener {
    private final float DEFAULT_POINTS_SCALE_ENLARGE_FACTOR;
    private final float EXIT_SCALE_RATIO;
    private boolean mAllowParentInterceptOnEdge;
    /* access modifiers changed from: private */
    public float mAlpha;
    private OnBackgroundAlphaChangedListener mAlphaChangedListener;
    private Drawable mAlphaDrawable;
    private int mAnim;
    /* access modifiers changed from: private */
    public final Matrix mBaseMatrix;
    private FlingRunnable mCurrentFlingRunnable;
    private int mDestStrokeAlpha;
    private final RectF mDisplayRect;
    private float mDownScale;
    private float mDragDownOutThreshold;
    private float mDragDownOutY;
    private boolean mDragHandled;
    private final Matrix mDrawMatrix;
    private OnExitListener mExitListener;
    private float mExitScale;
    private GestureDetector mGestureDetector;
    private int mHScrollEdge;
    private boolean mHasScale;
    /* access modifiers changed from: private */
    public float mHighScaleLevelLowerBound;
    private WeakReference<ImageView> mImageView;
    private boolean mIntercepted;
    private boolean mIsDragDownOut;
    private boolean mIsDragInsideBoundsEnabled;
    private boolean mIsRegionDecodeEnable;
    private boolean mIsSlipping;
    private boolean mIsTouchInProgress;
    private int mIvBottom;
    private int mIvLeft;
    private int mIvRight;
    private int mIvTop;
    private float mLastScaleFocusX;
    private float mLastScaleFocusY;
    /* access modifiers changed from: private */
    public View.OnLongClickListener mLongClickListener;
    private Set<OnMatrixChangedListener> mMatrixChangeListeners;
    private final float[] mMatrixValues;
    /* access modifiers changed from: private */
    public float mMaxDoubleTapScale;
    private float mMaxPointsScale;
    private float mMaxScale;
    private float mMidScale;
    private float mMinScale;
    /* access modifiers changed from: private */
    public Set<OnRotateListener> mOnRotateListeners;
    private int mOriginHeight;
    private int mOriginWidth;
    private Rect mOutOfBoundsRect;
    private boolean mPendingDisableDragInsideBounds;
    private OnPhotoTapListener mPhotoTapListener;
    private float mPointsScaleEnlargeFactor;
    private float mProgress;
    private RotateManager mRotateDetector;
    private boolean mRotateEnabled;
    private OnScaleChangeListener mScaleChangeListener;
    private uk.co.senab.photoview.gestures.GestureDetector mScaleDragDetector;
    private OnScaleLevelChangedListener mScaleStageChangedListener;
    private ImageView.ScaleType mScaleType;
    private int mSlippedRectBottom;
    private int mSlippedRectTop;
    private int mStrokeAlpha;
    private int mStrokeColor;
    private Paint mStrokePaint;
    private int mStrokeWidth;
    /* access modifiers changed from: private */
    public final Matrix mSuppMatrix;
    private boolean mSupportHd;
    private TileView mTileView;
    private float mTouchDownY;
    private Transition mTransition;
    private int mVScrollEdge;
    private OnViewTapListener mViewTapListener;
    private WaitingTransition mWaitingTransition;
    /* access modifiers changed from: private */
    public int mZoomDuration;
    /* access modifiers changed from: private */
    public float mZoomDurationLengthenFactor;
    /* access modifiers changed from: private */
    public boolean mZoomEnabled;
    /* access modifiers changed from: private */
    public Interpolator mZoomInterpolator;

    /* renamed from: uk.co.senab.photoview.PhotoViewAttacher$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.MATRIX.ordinal()] = 1;
            $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 2;
            $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
        }
    }

    private class AnimatedTranslateRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final ScrollerProxy mScroller;

        public AnimatedTranslateRunnable(Context context) {
            this.mScroller = ScrollerProxy.getScroller(context, new CubicEaseOutInterpolator());
        }

        public void run() {
            if (this.mScroller.isFinished()) {
                PhotoViewAttacher.this.clearAnim(22);
                return;
            }
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView == null) {
                PhotoViewAttacher.this.clearAnim(22);
                return;
            }
            PhotoViewAttacher.this.appendAnim(22);
            if (this.mScroller.computeScrollOffset()) {
                int currX = this.mScroller.getCurrX() - this.mCurrentX;
                int currY = this.mScroller.getCurrY() - this.mCurrentY;
                this.mCurrentX = this.mScroller.getCurrX();
                this.mCurrentY = this.mScroller.getCurrY();
                PhotoViewAttacher.this.postTranslate((float) currX, (float) currY);
                PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
                photoViewAttacher.setImageViewMatrix(photoViewAttacher.getDrawMatrix());
                Compat.postOnAnimation(imageView, this);
                return;
            }
            PhotoViewAttacher.this.clearAnim(22);
        }

        public AnimatedTranslateRunnable translate(int i, int i2, int i3, int i4) {
            this.mCurrentX = i;
            this.mCurrentY = i2;
            if (!(i3 == 0 && i4 == 0)) {
                this.mScroller.startScroll(i, i2, i3, i4, 300);
            }
            return this;
        }
    }

    private class AnimatedZoomRunnable implements Runnable {
        private final float mFocalX;
        private final float mFocalY;
        private final long mStartTime = System.currentTimeMillis();
        private final int mZoomDuration;
        private final float mZoomEnd;
        private final float mZoomStart;

        public AnimatedZoomRunnable(float f, float f2, float f3, float f4) {
            this.mFocalX = f3;
            this.mFocalY = f4;
            this.mZoomStart = f;
            this.mZoomEnd = f2;
            this.mZoomDuration = (f2 <= f || MiscUtil.floatEquals(PhotoViewAttacher.this.mHighScaleLevelLowerBound, PhotoViewAttacher.this.mMaxDoubleTapScale) || f >= PhotoViewAttacher.this.mHighScaleLevelLowerBound || !MiscUtil.floatNear(f2, PhotoViewAttacher.this.mMaxDoubleTapScale, 1.0E-5f)) ? PhotoViewAttacher.this.mZoomDuration : (int) (((float) PhotoViewAttacher.this.mZoomDuration) * PhotoViewAttacher.this.mZoomDurationLengthenFactor);
        }

        private float interpolate() {
            return PhotoViewAttacher.this.mZoomInterpolator.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.mStartTime)) * 1.0f) / ((float) this.mZoomDuration)));
        }

        public void run() {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView == null) {
                PhotoViewAttacher.this.clearAnim(1);
                return;
            }
            PhotoViewAttacher.this.appendAnim(1);
            float interpolate = interpolate();
            float f = this.mZoomStart;
            PhotoViewAttacher.this.onScale((f + ((this.mZoomEnd - f) * interpolate)) / PhotoViewAttacher.this.getScale(), this.mFocalX, this.mFocalY);
            if (interpolate < 1.0f) {
                Compat.postOnAnimation(imageView, this);
                return;
            }
            PhotoViewAttacher.this.clearAnim(1);
            PhotoViewAttacher.this.tryAlignBounds();
        }
    }

    private class FlingRunnable implements Runnable {
        private int mCurrentX;
        private int mCurrentY;
        private final ScrollerProxy mScroller;

        public FlingRunnable(Context context) {
            this.mScroller = ScrollerProxy.getScroller(context);
        }

        public void cancelFling() {
            this.mScroller.forceFinished(true);
        }

        public void fling(int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            int i7;
            int i8;
            RectF displayRect = PhotoViewAttacher.this.getDisplayRect();
            if (displayRect != null) {
                int round = Math.round(-displayRect.left);
                float f = (float) i;
                if (f < displayRect.width()) {
                    i5 = Math.round(displayRect.width() - f);
                    i6 = 0;
                } else {
                    i6 = round;
                    i5 = i6;
                }
                int round2 = Math.round(-displayRect.top);
                float f2 = (float) i2;
                if (f2 < displayRect.height()) {
                    i7 = Math.round(displayRect.height() - f2);
                    i8 = 0;
                } else {
                    i8 = round2;
                    i7 = i8;
                }
                this.mCurrentX = round;
                this.mCurrentY = round2;
                if (round != i5 || round2 != i7) {
                    this.mScroller.fling(round, round2, i3, i4, i6, i5, i8, i7, 0, 0);
                }
            }
        }

        public void run() {
            if (this.mScroller.isFinished()) {
                PhotoViewAttacher.this.clearAnim(2);
                return;
            }
            PhotoViewAttacher.this.appendAnim(2);
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView == null || !this.mScroller.computeScrollOffset()) {
                PhotoViewAttacher.this.clearAnim(2);
                return;
            }
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            PhotoViewAttacher.this.postTranslate((float) (this.mCurrentX - currX), (float) (this.mCurrentY - currY));
            PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
            photoViewAttacher.setImageViewMatrix(photoViewAttacher.getDrawMatrix());
            this.mCurrentX = currX;
            this.mCurrentY = currY;
            Compat.postOnAnimation(imageView, this);
        }
    }

    public interface OnBackgroundAlphaChangedListener {
        void onAlphaChanged(float f);
    }

    public interface OnExitListener {
        void onExit();
    }

    public interface OnMatrixChangedListener {
        void onMatrixChanged(RectF rectF);
    }

    public interface OnPhotoTapListener {
        void onPhotoTap(View view, float f, float f2);
    }

    public interface OnRotateListener {
        void onRotate(float f, float f2, float f3, float f4);

        void onRotateBegin(float f);

        void onRotateEnd(float f);
    }

    public interface OnScaleChangeListener {
        void onScaleChange(float f, float f2, float f3, float f4, float f5);
    }

    public interface OnScaleLevelChangedListener {
        void onHighScaleLevel(boolean z, boolean z2);

        void onMidScaleLevel(boolean z, boolean z2);
    }

    public interface OnViewTapListener {
        void onViewTap(View view, float f, float f2);
    }

    private class RotateManager implements RotateGestureDetector.OnRotationGestureListener {
        private final float CRITICAL_VELOCITY;
        private AdjustAnimation mAdjustAnim;
        private RotateGestureDetector mRotateDetector;
        private float mRotatedDegrees;

        class AdjustAnimation implements Runnable {
            private boolean isRunning;
            private Scroller mAlphaScroller;
            private float mRotateFocusX;
            private float mRotateFocusY;
            private Scroller mRotateScroller;
            private Scroller mScaleScroller;
            private Scroller mTranslateScroller;
            private int mTranslateX;
            private int mTranslateY;

            public AdjustAnimation(Context context) {
                CubicEaseOutInterpolator cubicEaseOutInterpolator = new CubicEaseOutInterpolator();
                this.mRotateScroller = new Scroller(context, cubicEaseOutInterpolator);
                this.mScaleScroller = new Scroller(context, cubicEaseOutInterpolator);
                this.mTranslateScroller = new Scroller(context, cubicEaseOutInterpolator);
                this.mAlphaScroller = new Scroller(context, cubicEaseOutInterpolator);
            }

            private void checkBounds() {
                ImageView imageView = PhotoViewAttacher.this.getImageView();
                if (imageView != null) {
                    RectF access$1100 = PhotoViewAttacher.this.getDisplayRect(PhotoViewAttacher.this.getDrawMatrix());
                    float rotate = PhotoViewAttacher.this.getRotate();
                    float access$2000 = (float) PhotoViewAttacher.this.trimRotation(rotate);
                    if (!MiscUtil.floatEquals(rotate, access$2000)) {
                        PhotoViewAttacher.this.postRotate(rotate - access$2000, access$1100.centerX(), access$1100.centerY());
                    }
                    float[] access$3000 = RotateManager.this.calculateTranslate(PhotoViewAttacher.this.getDrawMatrix());
                    if (access$3000 != null) {
                        PhotoViewAttacher.this.postTranslate(access$3000[0], access$3000[1]);
                    }
                    RectF access$2300 = PhotoViewAttacher.this.calculateBaseRect(imageView.getDrawable(), (int) PhotoViewAttacher.this.getRotate());
                    if (access$2300 == null) {
                        LogManager.getLogger().e("PhotoViewAttacher", "calculate base display null");
                        return;
                    }
                    RectF access$11002 = PhotoViewAttacher.this.getDisplayRect(PhotoViewAttacher.this.getDrawMatrix());
                    float width = access$11002.width() < access$2300.width() ? access$2300.width() / access$11002.width() : 1.0f;
                    float width2 = ((access$11002.width() * width) * (access$2300.height() / access$2300.width())) / access$11002.height();
                    if (!MiscUtil.floatEquals(width, 1.0f) || !MiscUtil.floatEquals(width2, 1.0f)) {
                        PhotoViewAttacher.this.postScale(width, width2, access$11002.centerX(), access$11002.centerY());
                    }
                }
            }

            private int precise(float f) {
                return (int) (f * 10000.0f);
            }

            private float unPrecise(int i) {
                return (((float) i) * 1.0f) / 10000.0f;
            }

            private void updateMatrix() {
                float f;
                float f2;
                if (PhotoViewAttacher.this.getImageView() != null) {
                    float rotate = PhotoViewAttacher.this.getRotate();
                    RectF rectF = new RectF(PhotoViewAttacher.this.getDisplayRect(PhotoViewAttacher.this.getDrawMatrix()));
                    PhotoViewAttacher.this.updateBaseMatrix(PhotoViewAttacher.this.getImageView().getDrawable(), PhotoViewAttacher.this.trimRotation(rotate));
                    RectF rectF2 = new RectF(PhotoViewAttacher.this.getDisplayRect(PhotoViewAttacher.this.mBaseMatrix));
                    PhotoViewAttacher.this.mSuppMatrix.reset();
                    if (!RotateManager.this.needTrimToBaseRect(rectF, rectF2)) {
                        float width = rectF.width() / rectF2.width();
                        float height = rectF.height() / rectF2.height();
                        Matrix matrix = new Matrix();
                        matrix.postScale(width, height, rectF2.centerX(), rectF2.centerY());
                        matrix.postTranslate(rectF.centerX() - rectF2.centerX(), rectF.centerY() - rectF2.centerY());
                        PhotoViewAttacher.this.mSuppMatrix.set(matrix);
                        f = height;
                        f2 = width;
                    } else {
                        f2 = 1.0f;
                        f = 1.0f;
                    }
                    PhotoViewAttacher.this.dispatchScaleChanged(f2, f, rectF2.centerX(), rectF2.centerY(), PhotoViewAttacher.this.getScale());
                }
            }

            public void alpha(float f, float f2, int i) {
                this.mAlphaScroller.forceFinished(true);
                this.mAlphaScroller.startScroll(precise(f), 0, precise(f2 - f), 0, i);
            }

            public boolean isRunning() {
                return this.isRunning;
            }

            public void rotate(float f, float f2, float f3, float f4, int i) {
                this.mRotateScroller.forceFinished(true);
                this.mRotateFocusX = f3;
                this.mRotateFocusY = f4;
                this.mRotateScroller.startScroll(precise(f), 0, precise(f2 - f), 0, i);
            }

            public void run() {
                boolean z;
                if (!isRunning()) {
                    stop();
                    return;
                }
                ImageView imageView = PhotoViewAttacher.this.getImageView();
                if (imageView != null) {
                    if (this.mRotateScroller.computeScrollOffset()) {
                        PhotoViewAttacher.this.postRotate(PhotoViewAttacher.this.getRotate() - unPrecise(this.mRotateScroller.getCurrX()), this.mRotateFocusX, this.mRotateFocusY);
                        z = true;
                    } else {
                        z = false;
                    }
                    if (this.mScaleScroller.computeScrollOffset()) {
                        float unPrecise = unPrecise(this.mScaleScroller.getCurrX()) / MatrixUtil.getScale(PhotoViewAttacher.this.mSuppMatrix);
                        RectF access$1100 = PhotoViewAttacher.this.getDisplayRect(PhotoViewAttacher.this.getDrawMatrix());
                        if (access$1100 != null) {
                            PhotoViewAttacher.this.postScale(unPrecise, unPrecise, access$1100.centerX(), access$1100.centerY());
                        }
                        z = true;
                    }
                    if (this.mTranslateScroller.computeScrollOffset()) {
                        int currX = this.mTranslateScroller.getCurrX();
                        int currY = this.mTranslateScroller.getCurrY();
                        float unPrecise2 = unPrecise(currX - this.mTranslateX);
                        float unPrecise3 = unPrecise(currY - this.mTranslateY);
                        this.mRotateFocusX += unPrecise2;
                        this.mRotateFocusY += unPrecise3;
                        PhotoViewAttacher.this.postTranslate(unPrecise2, unPrecise3);
                        this.mTranslateX = currX;
                        this.mTranslateY = currY;
                        z = true;
                    }
                    if (this.mAlphaScroller.computeScrollOffset()) {
                        PhotoViewAttacher.this.updateAlpha(unPrecise(this.mAlphaScroller.getCurrX()));
                    }
                    PhotoViewAttacher.this.setImageViewMatrix(PhotoViewAttacher.this.getDrawMatrix());
                    if (z) {
                        Compat.postOnAnimation(imageView, this);
                        return;
                    }
                    checkBounds();
                    updateMatrix();
                    PhotoViewAttacher.this.updateAlpha(1.0f);
                    this.isRunning = false;
                    PhotoViewAttacher.this.setImageViewMatrix(PhotoViewAttacher.this.getDrawMatrix());
                    PhotoViewAttacher.this.clearAnim(8);
                    float rotate = PhotoViewAttacher.this.getRotate();
                    for (OnRotateListener onRotateEnd : PhotoViewAttacher.this.mOnRotateListeners) {
                        onRotateEnd.onRotateEnd(rotate);
                    }
                }
            }

            public void scale(float f, float f2, int i) {
                this.mScaleScroller.forceFinished(true);
                this.mScaleScroller.startScroll(precise(f), 0, precise(f2 - f), 0, i);
            }

            public void start() {
                ImageView imageView = PhotoViewAttacher.this.getImageView();
                if (imageView != null) {
                    this.isRunning = true;
                    Compat.postOnAnimation(imageView, this);
                }
            }

            public void stop() {
                this.mRotateScroller.forceFinished(true);
                this.mScaleScroller.forceFinished(true);
                this.mTranslateScroller.forceFinished(true);
                if (this.isRunning) {
                    this.isRunning = false;
                    PhotoViewAttacher.this.clearAnim(8);
                }
            }

            public void translate(float f, float f2, float f3, float f4, int i) {
                this.mTranslateScroller.forceFinished(true);
                this.mTranslateX = precise(f);
                this.mTranslateY = precise(f2);
                this.mTranslateScroller.startScroll(precise(f), precise(f2), precise(f3), precise(f4), i);
            }
        }

        RotateManager() {
            Context context = PhotoViewAttacher.this.getImageView().getContext();
            this.mRotateDetector = new RotateGestureDetector(context, this);
            this.mAdjustAnim = new AdjustAnimation(context);
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.CRITICAL_VELOCITY = Math.min(((float) viewConfiguration.getScaledMinimumFlingVelocity()) * 10.0f, ((float) viewConfiguration.getScaledMaximumFlingVelocity()) / 10.0f);
        }

        private void adjustAfterRotate(RotateGestureDetector rotateGestureDetector, boolean z, float f) {
            int i;
            if (PhotoViewAttacher.this.getImageView() != null) {
                this.mAdjustAnim.stop();
                float rotate = PhotoViewAttacher.this.getRotate();
                float calculateFinalDegrees = (float) calculateFinalDegrees(z, f);
                Matrix matrix = new Matrix(PhotoViewAttacher.this.getDrawMatrix());
                float f2 = rotate - calculateFinalDegrees;
                matrix.postRotate(f2, rotateGestureDetector.getFocusX(), rotateGestureDetector.getFocusY());
                int calculateRotateDuration = calculateRotateDuration(Math.abs(f2), f);
                this.mAdjustAnim.rotate(rotate, calculateFinalDegrees, rotateGestureDetector.getFocusX(), rotateGestureDetector.getFocusY(), calculateRotateDuration);
                float[] calculateTranslate = calculateTranslate(matrix);
                if (calculateTranslate != null) {
                    this.mAdjustAnim.translate(0.0f, 0.0f, calculateTranslate[0], calculateTranslate[1], calculateRotateDuration);
                }
                float calculateScale = calculateScale(matrix);
                if (!Float.isNaN(calculateScale)) {
                    float scale = MatrixUtil.getScale(PhotoViewAttacher.this.mSuppMatrix);
                    float f3 = scale * calculateScale;
                    i = calculateScaleDuration(calculateScale, f);
                    this.mAdjustAnim.scale(scale, f3, i);
                } else {
                    i = calculateRotateDuration;
                }
                this.mAdjustAnim.alpha(PhotoViewAttacher.this.mAlpha, 1.0f, Math.max(calculateRotateDuration, i));
                this.mAdjustAnim.start();
            }
        }

        private int calculateFinalDegrees(boolean z, float f) {
            int i;
            float f2;
            float rotate = PhotoViewAttacher.this.getRotate();
            float f3 = this.mRotatedDegrees % 90.0f;
            float abs = ((f3 <= 0.0f || z) && (f3 >= 0.0f || !z)) ? Math.abs(f3) : 90.0f - Math.abs(f3);
            if (Math.abs(f) > this.CRITICAL_VELOCITY) {
                if (abs > 20.0f) {
                    float f4 = rotate % 90.0f;
                    if (f4 > 0.0f) {
                        if (!z) {
                            f2 = 90.0f - f4;
                            return PhotoViewAttacher.this.trimRotation(rotate + f2);
                        }
                    } else if (z) {
                        f2 = -90.0f - f4;
                        return PhotoViewAttacher.this.trimRotation(rotate + f2);
                    }
                    f2 = -f4;
                    return PhotoViewAttacher.this.trimRotation(rotate + f2);
                }
                i = ((int) rotate) / 90;
            } else if (abs > 45.0f) {
                return PhotoViewAttacher.this.trimRotation(rotate);
            } else {
                i = ((int) rotate) / 90;
            }
            return i * 90;
        }

        private int calculateRotateDuration(float f, float f2) {
            return 300;
        }

        private float calculateScale(Matrix matrix) {
            RectF access$2300;
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView == null || (access$2300 = PhotoViewAttacher.this.calculateBaseRect(imageView.getDrawable(), (int) MatrixUtil.getRotate(matrix))) == null) {
                return Float.NaN;
            }
            RectF rectF = new RectF(PhotoViewAttacher.this.getDisplayRect(matrix));
            if (needTrimToBaseRect(rectF, access$2300)) {
                return Math.max(access$2300.width() / rectF.width(), access$2300.height() / rectF.height());
            }
            return Float.NaN;
        }

        private int calculateScaleDuration(float f, float f2) {
            return 300;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0094  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00ba  */
        public float[] calculateTranslate(Matrix matrix) {
            RectF access$2300;
            float f;
            float f2;
            float centerY;
            float centerY2;
            float f3;
            float f4;
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView == null || (access$2300 = PhotoViewAttacher.this.calculateBaseRect(imageView.getDrawable(), (int) MatrixUtil.getRotate(matrix))) == null) {
                return null;
            }
            RectF rectF = new RectF(PhotoViewAttacher.this.getDisplayRect(matrix));
            if (needTrimToBaseRect(rectF, access$2300)) {
                f = (float) ((int) (access$2300.centerX() - rectF.centerX()));
                centerY = access$2300.centerY();
                centerY2 = rectF.centerY();
            } else {
                if (rectF.width() > ((float) PhotoViewAttacher.this.getImageViewWidth(imageView))) {
                    f = rectF.left > 0.0f ? (float) ((int) (0.0f - rectF.left)) : 0.0f;
                    if (rectF.right < ((float) PhotoViewAttacher.this.getImageViewWidth(imageView))) {
                        f3 = (float) PhotoViewAttacher.this.getImageViewWidth(imageView);
                        f4 = rectF.right;
                    }
                    if (rectF.height() <= ((float) PhotoViewAttacher.this.getImageViewHeight(imageView))) {
                        float f5 = rectF.top > 0.0f ? (float) ((int) (0.0f - rectF.top)) : 0.0f;
                        if (rectF.bottom < ((float) PhotoViewAttacher.this.getImageViewHeight(imageView))) {
                            centerY = (float) PhotoViewAttacher.this.getImageViewHeight(imageView);
                            centerY2 = rectF.bottom;
                        } else {
                            f2 = f5;
                            return new float[]{f, f2};
                        }
                    } else {
                        centerY = access$2300.centerY();
                        centerY2 = rectF.centerY();
                    }
                } else {
                    f3 = access$2300.centerX();
                    f4 = rectF.centerX();
                }
                f = (float) ((int) (f3 - f4));
                if (rectF.height() <= ((float) PhotoViewAttacher.this.getImageViewHeight(imageView))) {
                }
            }
            f2 = (float) ((int) (centerY - centerY2));
            return new float[]{f, f2};
        }

        /* access modifiers changed from: private */
        public boolean needTrimToBaseRect(RectF rectF, RectF rectF2) {
            return !PhotoViewAttacher.this.mZoomEnabled || rectF.width() < rectF2.width() * 1.2f || rectF.height() < rectF2.height() * 1.2f;
        }

        private void rotateBy(float f, float f2, float f3) {
            PhotoViewAttacher.this.postRotate(f, f2, f3);
            PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
            photoViewAttacher.setImageViewMatrix(photoViewAttacher.getDrawMatrix());
        }

        public boolean isAutoRotating() {
            return this.mAdjustAnim.isRunning();
        }

        public boolean isManualRotating() {
            return this.mRotateDetector.isInProgress();
        }

        public boolean isRotating() {
            return isManualRotating() || isAutoRotating();
        }

        public boolean onRotate(RotateGestureDetector rotateGestureDetector) {
            float rotateDegrees = rotateGestureDetector.getRotateDegrees();
            if (Float.isNaN(rotateDegrees) || Float.isInfinite(rotateDegrees)) {
                return false;
            }
            this.mRotatedDegrees += rotateDegrees;
            rotateBy(rotateDegrees, rotateGestureDetector.getFocusX(), rotateGestureDetector.getFocusY());
            float rotate = PhotoViewAttacher.this.getRotate();
            for (OnRotateListener onRotate : PhotoViewAttacher.this.mOnRotateListeners) {
                onRotate.onRotate(rotateDegrees, rotate, rotateGestureDetector.getFocusX(), rotateGestureDetector.getFocusY());
            }
            return true;
        }

        public boolean onRotateBegin(RotateGestureDetector rotateGestureDetector) {
            PhotoViewAttacher.this.appendAnim(8);
            this.mRotatedDegrees = 0.0f;
            float rotate = PhotoViewAttacher.this.getRotate();
            for (OnRotateListener onRotateBegin : PhotoViewAttacher.this.mOnRotateListeners) {
                onRotateBegin.onRotateBegin(rotate);
            }
            return true;
        }

        public void onRotateEnd(RotateGestureDetector rotateGestureDetector, boolean z, float f) {
            adjustAfterRotate(rotateGestureDetector, z, f);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return this.mRotateDetector.onTouchEvent(motionEvent);
        }

        public void stop() {
            this.mAdjustAnim.stop();
        }
    }

    public enum ScaleLevel {
        LOW,
        MIDDLE,
        HIGH
    }

    private class Transition implements Runnable {
        private boolean isExitTransition;
        private boolean isExited;
        private boolean isRunning;
        private ScrollerProxy mAlphaScroller;
        private Matrix mClipMatrix = new Matrix();
        private RectF mClipRect;
        private ScrollerProxy mClipScroller;
        private int mCurrentX;
        private int mCurrentY;
        public TransitionListener mListener;
        private ScrollerProxy mScaleScroller;
        private ScrollerProxy mTranslateScroller;

        public Transition(Context context) {
            CubicEaseOutInterpolator cubicEaseOutInterpolator = new CubicEaseOutInterpolator();
            this.mAlphaScroller = ScrollerProxy.getScroller(context, new LinearInterpolator());
            this.mTranslateScroller = ScrollerProxy.getScroller(context, cubicEaseOutInterpolator);
            this.mScaleScroller = ScrollerProxy.getScroller(context, cubicEaseOutInterpolator);
            this.mClipScroller = ScrollerProxy.getScroller(context, cubicEaseOutInterpolator);
        }

        public void alpha(float f, float f2) {
            this.mAlphaScroller.startScroll((int) (f * 10000.0f), 0, (int) ((f2 - f) * 10000.0f), 0, 150);
        }

        public void clip(float f, float f2, float f3, float f4) {
            PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
            RectF access$1100 = photoViewAttacher.getDisplayRect(photoViewAttacher.getDrawMatrix());
            if (access$1100 != null) {
                float f5 = (access$1100.left + access$1100.right) / 2.0f;
                this.mClipMatrix.setScale(f, f2, f5, f5);
                this.mClipRect = new RectF();
                this.mClipMatrix.mapRect(this.mClipRect, access$1100);
                this.mClipScroller.startScroll((int) (f * 10000.0f), (int) (f2 * 10000.0f), (int) (f3 * 10000.0f), (int) (f4 * 10000.0f), 150);
            }
        }

        public void ensureAlpha(boolean z) {
            float f = 1.0f;
            float f2 = 0.0f;
            if (!z) {
                if (!MiscUtil.floatEquals(PhotoViewAttacher.this.mAlpha, 0.0f)) {
                    f = PhotoViewAttacher.this.mAlpha;
                }
                f2 = f;
            } else if (!MiscUtil.floatEquals(PhotoViewAttacher.this.mAlpha, 1.0f)) {
                f2 = PhotoViewAttacher.this.mAlpha;
            }
            PhotoViewAttacher.this.updateAlpha(f2);
        }

        public RectF getClipRect() {
            return this.mClipRect;
        }

        public boolean isExited() {
            return this.isExited;
        }

        public boolean isRunning() {
            return this.isRunning;
        }

        public void run() {
            boolean z;
            if (!this.isRunning) {
                PhotoViewAttacher.this.clearAnim(4);
                return;
            }
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView != null) {
                if (this.mTranslateScroller.computeScrollOffset()) {
                    PhotoViewAttacher.this.postTranslate((float) (this.mTranslateScroller.getCurrX() - this.mCurrentX), (float) (this.mTranslateScroller.getCurrY() - this.mCurrentY));
                    this.mCurrentX = this.mTranslateScroller.getCurrX();
                    this.mCurrentY = this.mTranslateScroller.getCurrY();
                    z = true;
                } else {
                    z = false;
                }
                if (this.mScaleScroller.computeScrollOffset()) {
                    float currX = ((float) this.mScaleScroller.getCurrX()) / (PhotoViewAttacher.this.getScale() * 10000.0f);
                    PhotoViewAttacher photoViewAttacher = PhotoViewAttacher.this;
                    RectF access$1100 = photoViewAttacher.getDisplayRect(photoViewAttacher.getDrawMatrix());
                    if (access$1100 != null) {
                        PhotoViewAttacher.this.postScale(currX, currX, access$1100.left, access$1100.top);
                    }
                    z = true;
                }
                if (this.mClipScroller.computeScrollOffset()) {
                    float currX2 = (((float) this.mClipScroller.getCurrX()) * 1.0f) / 10000.0f;
                    float currY = (((float) this.mClipScroller.getCurrY()) * 1.0f) / 10000.0f;
                    PhotoViewAttacher photoViewAttacher2 = PhotoViewAttacher.this;
                    RectF access$11002 = photoViewAttacher2.getDisplayRect(photoViewAttacher2.getDrawMatrix());
                    if (access$11002 != null) {
                        this.mClipMatrix.setScale(currX2, currY, (access$11002.left + access$11002.right) / 2.0f, (access$11002.top + access$11002.bottom) / 2.0f);
                        this.mClipMatrix.mapRect(this.mClipRect, access$11002);
                    }
                    if (MiscUtil.floatEquals(currX2, 1.0f)) {
                        RectF rectF = this.mClipRect;
                        rectF.left = 0.0f;
                        rectF.right = (float) imageView.getWidth();
                    }
                    if (MiscUtil.floatEquals(currY, 1.0f)) {
                        RectF rectF2 = this.mClipRect;
                        rectF2.top = 0.0f;
                        rectF2.bottom = (float) imageView.getHeight();
                    }
                    z = true;
                }
                if (this.mAlphaScroller.computeScrollOffset()) {
                    PhotoViewAttacher.this.updateAlpha((((float) this.mAlphaScroller.getCurrX()) * 1.0f) / 10000.0f);
                    z = true;
                }
                PhotoViewAttacher photoViewAttacher3 = PhotoViewAttacher.this;
                if (photoViewAttacher3.getDisplayRect(photoViewAttacher3.getDrawMatrix()) != null) {
                    PhotoViewAttacher photoViewAttacher4 = PhotoViewAttacher.this;
                    photoViewAttacher4.setImageViewMatrix(photoViewAttacher4.getDrawMatrix());
                } else {
                    imageView.invalidate();
                }
                if (z) {
                    PhotoViewAttacher.this.appendAnim(4);
                    Compat.postOnAnimation(imageView, this);
                    return;
                }
                PhotoViewAttacher.this.clearAnim(4);
                if (this.isRunning) {
                    this.mClipRect = null;
                    this.isRunning = false;
                    if (this.isExitTransition) {
                        this.isExited = true;
                    } else {
                        this.isExited = false;
                        PhotoViewAttacher.this.resetMatrix();
                    }
                    if (this.mListener != null) {
                        imageView.postDelayed(new Runnable() {
                            public void run() {
                                if (Transition.this.mListener != null) {
                                    Transition.this.mListener.onTransitEnd();
                                    Transition.this.mListener = null;
                                }
                            }
                        }, 20);
                        return;
                    }
                    return;
                }
                return;
            }
            PhotoViewAttacher.this.clearAnim(4);
        }

        public void scale(float f, float f2) {
            this.mScaleScroller.startScroll((int) (f * 10000.0f), 0, (int) ((f2 - f) * 10000.0f), 0, 150);
        }

        public void setTransitionListener(TransitionListener transitionListener) {
            this.mListener = transitionListener;
        }

        public void start(boolean z) {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView != null) {
                this.isRunning = true;
                this.isExitTransition = z;
                Compat.postOnAnimation(imageView, this);
            }
        }

        public void stop() {
            ImageView imageView = PhotoViewAttacher.this.getImageView();
            if (imageView != null) {
                imageView.removeCallbacks(this);
            }
            this.mTranslateScroller.forceFinished(true);
            this.mScaleScroller.forceFinished(true);
            this.mClipScroller.forceFinished(true);
            this.isRunning = false;
        }

        public void translate(int i, int i2, int i3, int i4) {
            this.mCurrentX = i;
            this.mCurrentY = i2;
            this.mTranslateScroller.startScroll(i, i2, i3, i4, 150);
        }
    }

    public interface TransitionListener {
        void onTransitEnd();
    }

    private class WaitingTransition implements Runnable {
        final ItemViewInfo mEnterInfo;
        final long mStartTime;
        final TransitionListener mTransitionListener;

        private WaitingTransition(ItemViewInfo itemViewInfo, TransitionListener transitionListener) {
            this.mEnterInfo = itemViewInfo;
            this.mTransitionListener = transitionListener;
            this.mStartTime = System.currentTimeMillis();
        }

        public void run() {
            if (!(PhotoViewAttacher.this.getTransition() == null || PhotoViewAttacher.this.getImageView() == null)) {
                PhotoViewAttacher.this.getTransition().ensureAlpha(true);
                PhotoViewAttacher.this.getTransition().alpha(PhotoViewAttacher.this.mAlpha, 1.0f);
                PhotoViewAttacher.this.getTransition().setTransitionListener(this.mTransitionListener);
                PhotoViewAttacher.this.getTransition().start(false);
            }
            LogManager.getLogger().w("PhotoViewAttacher", "Loading drawable is slow, transiting only with alpha");
        }
    }

    public PhotoViewAttacher(ImageView imageView) {
        this(imageView, true);
    }

    public PhotoViewAttacher(ImageView imageView, boolean z) {
        this.mZoomInterpolator = DEFAULT_ZOOM_INTERPOLATOR;
        this.mZoomDuration = 200;
        this.EXIT_SCALE_RATIO = 0.8f;
        this.mExitScale = 0.8f;
        this.DEFAULT_POINTS_SCALE_ENLARGE_FACTOR = 1.5f;
        this.mPointsScaleEnlargeFactor = 1.5f;
        this.mDownScale = 1.0f;
        this.mMinScale = 1.0f;
        this.mMidScale = 1.75f;
        this.mMaxScale = 2.0f;
        this.mZoomDurationLengthenFactor = 1.5f;
        this.mAllowParentInterceptOnEdge = true;
        this.mBaseMatrix = new Matrix();
        this.mDrawMatrix = new Matrix();
        this.mSuppMatrix = new Matrix();
        this.mDisplayRect = new RectF();
        this.mMatrixValues = new float[9];
        this.mMatrixChangeListeners = new HashSet();
        this.mOnRotateListeners = new CopyOnWriteArraySet();
        this.mHScrollEdge = 4;
        this.mVScrollEdge = 4;
        this.mOutOfBoundsRect = new Rect(0, 0, 0, 0);
        this.mIsDragInsideBoundsEnabled = false;
        this.mIsTouchInProgress = false;
        this.mPendingDisableDragInsideBounds = false;
        this.mRotateEnabled = true;
        this.mScaleType = ImageView.ScaleType.FIT_CENTER;
        this.mIsRegionDecodeEnable = true;
        this.mAnim = 0;
        this.mAlpha = 1.0f;
        this.mImageView = new WeakReference<>(imageView);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        setImageViewScaleTypeMatrix(imageView);
        if (!imageView.isInEditMode()) {
            setZoomable(z);
            this.mDragDownOutThreshold = imageView.getResources().getDisplayMetrics().density * 10.0f;
        }
    }

    private void animEnterAfterDrawableBound() {
        if (isWaitingTransition()) {
            animEnter(this.mWaitingTransition.mEnterInfo, this.mWaitingTransition.mTransitionListener);
            removeWaitingTransition();
        }
    }

    /* access modifiers changed from: private */
    public void appendAnim(int i) {
        this.mAnim = i | this.mAnim;
    }

    /* access modifiers changed from: private */
    public RectF calculateBaseRect(Drawable drawable, int i) {
        ImageView imageView = getImageView();
        if (!(imageView == null || drawable == null)) {
            float imageViewWidth = (float) getImageViewWidth(imageView);
            float imageViewHeight = (float) getImageViewHeight(imageView);
            int drawableWidth = getDrawableWidth(i);
            int drawableHeight = getDrawableHeight(i);
            if (!(imageViewWidth == 0.0f || imageViewHeight == 0.0f || drawableWidth == 0 || drawableHeight == 0)) {
                Matrix matrix = new Matrix();
                float f = (float) drawableWidth;
                float f2 = imageViewWidth / f;
                float f3 = (float) drawableHeight;
                float f4 = imageViewHeight / f3;
                if (this.mScaleType == ImageView.ScaleType.CENTER) {
                    matrix.postTranslate((imageViewWidth - f) / 2.0f, (imageViewHeight - f3) / 2.0f);
                } else if (this.mScaleType == ImageView.ScaleType.CENTER_CROP) {
                    float max = Math.max(f2, f4);
                    matrix.postScale(max, max);
                    matrix.postTranslate((imageViewWidth - (f * max)) / 2.0f, (imageViewHeight - (max * f3)) / 2.0f);
                } else if (this.mScaleType == ImageView.ScaleType.CENTER_INSIDE) {
                    float min = Math.min(1.0f, Math.min(f2, f4));
                    matrix.postScale(min, min);
                    matrix.postTranslate((imageViewWidth - (f * min)) / 2.0f, (imageViewHeight - (min * f3)) / 2.0f);
                } else {
                    RectF rectF = new RectF(0.0f, 0.0f, f, f3);
                    RectF rectF2 = new RectF(0.0f, 0.0f, imageViewWidth, imageViewHeight);
                    int i2 = AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
                    if (i2 == 2) {
                        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                    } else if (i2 == 3) {
                        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                    } else if (i2 == 4) {
                        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                    } else if (i2 == 5) {
                        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
                    }
                }
                RectF rectF3 = new RectF();
                rectF3.set(0.0f, 0.0f, f, f3);
                matrix.mapRect(rectF3);
                return rectF3;
            }
        }
        return null;
    }

    private void calculateScales() {
        if (getImageView() != null) {
            float imageViewWidth = (float) getImageViewWidth(getImageView());
            float imageViewHeight = (float) getImageViewHeight(getImageView());
            int rotate = (int) getRotate();
            int drawableWidth = getDrawableWidth(rotate);
            int drawableHeight = getDrawableHeight(rotate);
            if (imageViewWidth != 0.0f && imageViewHeight != 0.0f && drawableWidth != 0 && drawableHeight != 0) {
                this.mMaxDoubleTapScale = 0.0f;
                this.mMaxPointsScale = 0.0f;
                RectF displayRect = getDisplayRect(this.mBaseMatrix);
                float width = displayRect.width();
                float maximumScale = (getMaximumScale() * width) / imageViewWidth;
                float height = (displayRect.height() * getMaximumScale()) / imageViewHeight;
                int enlargeMode = getEnlargeMode(width, imageViewWidth);
                if (enlargeMode == 0) {
                    if (maximumScale < height) {
                        this.mMidScale = ((double) maximumScale) < 1.0d ? imageViewWidth / displayRect.width() : 2.0f;
                        this.mMaxDoubleTapScale = ((float) getCorrectWidth(this.mOriginWidth, this.mOriginHeight)) / displayRect.width();
                    } else {
                        this.mMidScale = ((double) height) < 1.0d ? imageViewHeight / displayRect.height() : 2.0f;
                        this.mMaxDoubleTapScale = ((float) getCorrectHeight(this.mOriginWidth, this.mOriginHeight)) / displayRect.height();
                    }
                    if (this.mMidScale > this.mMaxDoubleTapScale) {
                        this.mMaxDoubleTapScale = this.mMaxScale;
                    }
                } else if (enlargeMode == 1) {
                    this.mMidScale = imageViewWidth / displayRect.width();
                    this.mMaxDoubleTapScale = this.mMidScale;
                } else if (enlargeMode == 2) {
                    if (((double) height) < 1.0d) {
                        this.mMaxDoubleTapScale = imageViewHeight / displayRect.height();
                    } else {
                        this.mMaxDoubleTapScale = getMaximumScale();
                    }
                    this.mMidScale = this.mMaxDoubleTapScale;
                }
                float f = this.mMaxDoubleTapScale;
                float f2 = this.mMidScale;
                if (f > f2 * 2.0f) {
                    this.mHighScaleLevelLowerBound = f - (((f - f2) * 2.0f) / 3.0f);
                } else {
                    this.mHighScaleLevelLowerBound = f;
                }
                this.mMaxPointsScale = this.mPointsScaleEnlargeFactor * this.mMaxDoubleTapScale;
            }
        }
    }

    private void cancelFling() {
        FlingRunnable flingRunnable = this.mCurrentFlingRunnable;
        if (flingRunnable != null) {
            flingRunnable.cancelFling();
            this.mCurrentFlingRunnable = null;
        }
    }

    private void checkAndDisplayMatrix() {
        if (checkMatrixBounds()) {
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkAndDisplayMatrix(boolean z, boolean z2) {
        PointF checkMatrixBounds = checkMatrixBounds(z);
        if (checkMatrixBounds == null) {
            return;
        }
        if (checkMatrixBounds.x != 0.0f || checkMatrixBounds.y != 0.0f) {
            if (z2) {
                ImageView imageView = getImageView();
                if (imageView != null) {
                    float[] translate = MatrixUtil.getTranslate(this.mSuppMatrix);
                    imageView.post(new AnimatedTranslateRunnable(imageView.getContext()).translate(Math.round(translate[0]), Math.round(translate[1]), Math.round(checkMatrixBounds.x), Math.round(checkMatrixBounds.y)));
                    return;
                }
                return;
            }
            postTranslate(checkMatrixBounds.x, checkMatrixBounds.y);
            setImageViewMatrix(getDrawMatrix());
        }
    }

    private void checkImageViewScaleType() {
        ImageView imageView = getImageView();
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private PointF checkMatrixBounds(boolean z) {
        RectF displayRect;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        if (interceptCheckBounds()) {
            return new PointF();
        }
        ImageView imageView = getImageView();
        if (imageView == null || (displayRect = getDisplayRect(getDrawMatrix())) == null) {
            return null;
        }
        float height = displayRect.height();
        float width = displayRect.width();
        int imageViewHeight = getImageViewHeight(imageView);
        double d = (double) imageViewHeight;
        if (Math.floor((double) height) <= d) {
            int i = AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i != 2) {
                if (i != 3) {
                    f7 = (((float) imageViewHeight) - height) / 2.0f;
                    f8 = displayRect.top;
                } else {
                    f7 = ((float) imageViewHeight) - height;
                    f8 = displayRect.top;
                }
                f6 = f7 - f8;
            } else {
                f6 = -displayRect.top;
            }
            this.mVScrollEdge = 5;
            f = f6;
        } else {
            if (Math.floor((double) displayRect.top) >= 0.0d) {
                if (!this.mIsDragInsideBoundsEnabled || z) {
                    this.mVScrollEdge = 2;
                    f = -displayRect.top;
                } else if (Math.floor((double) displayRect.top) >= ((double) this.mOutOfBoundsRect.top)) {
                    this.mVScrollEdge = 2;
                    f = (-displayRect.top) + ((float) this.mOutOfBoundsRect.top);
                } else {
                    this.mVScrollEdge = -1;
                }
            } else if (Math.floor((double) displayRect.bottom) > d) {
                this.mVScrollEdge = -1;
            } else if (!this.mIsDragInsideBoundsEnabled || z) {
                f = ((float) imageViewHeight) - displayRect.bottom;
                this.mVScrollEdge = 3;
            } else if (Math.floor((double) displayRect.bottom) <= ((double) (imageViewHeight - this.mOutOfBoundsRect.bottom))) {
                this.mVScrollEdge = 3;
                f = ((float) (imageViewHeight - this.mOutOfBoundsRect.bottom)) - displayRect.bottom;
            } else {
                this.mVScrollEdge = -1;
            }
            f = 0.0f;
        }
        int imageViewWidth = getImageViewWidth(imageView);
        float f9 = (float) imageViewWidth;
        if (width <= f9) {
            int i2 = AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[this.mScaleType.ordinal()];
            if (i2 != 2) {
                if (i2 != 3) {
                    f4 = (f9 - width) / 2.0f;
                    f5 = displayRect.left;
                } else {
                    f4 = f9 - width;
                    f5 = displayRect.left;
                }
                f3 = f4 - f5;
            } else {
                f3 = -displayRect.left;
            }
            this.mHScrollEdge = 4;
            f2 = f3;
        } else {
            if (Math.floor((double) displayRect.left) >= 0.0d) {
                if (!this.mIsDragInsideBoundsEnabled || z) {
                    this.mHScrollEdge = 0;
                    f2 = -displayRect.left;
                } else if (Math.floor((double) displayRect.left) >= ((double) this.mOutOfBoundsRect.left)) {
                    this.mVScrollEdge = 0;
                    f2 = (-displayRect.left) + ((float) this.mOutOfBoundsRect.left);
                } else {
                    this.mVScrollEdge = -1;
                }
            } else if (Math.floor((double) displayRect.right) > ((double) imageViewWidth)) {
                this.mHScrollEdge = -1;
            } else if (!this.mIsDragInsideBoundsEnabled || z) {
                f2 = f9 - displayRect.right;
                this.mHScrollEdge = 1;
            } else if (Math.floor((double) displayRect.right) <= ((double) (imageViewWidth - this.mOutOfBoundsRect.right))) {
                this.mVScrollEdge = 1;
                f2 = ((float) (imageViewWidth - this.mOutOfBoundsRect.right)) - displayRect.right;
            } else {
                this.mVScrollEdge = -1;
            }
            f2 = 0.0f;
        }
        return new PointF(f2, f);
    }

    private boolean checkMatrixBounds() {
        PointF checkMatrixBounds = checkMatrixBounds(false);
        if (checkMatrixBounds == null) {
            return false;
        }
        postTranslate(checkMatrixBounds.x, checkMatrixBounds.y);
        return true;
    }

    private static void checkZoomLevels(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    /* access modifiers changed from: private */
    public void clearAnim(int i) {
        this.mAnim = (~i) & this.mAnim;
        if (!isAniming()) {
            consumePendingDisableDisplayOutOfBounds();
        }
    }

    private void consumePendingDisableDisplayOutOfBounds() {
        if (this.mPendingDisableDragInsideBounds && !isAniming() && !this.mIsTouchInProgress) {
            this.mPendingDisableDragInsideBounds = false;
            this.mIsDragInsideBoundsEnabled = false;
            tryAlignBounds();
        }
    }

    /* access modifiers changed from: private */
    public void dispatchScaleChanged(float f, float f2, float f3, float f4, float f5) {
        OnScaleChangeListener onScaleChangeListener = this.mScaleChangeListener;
        if (onScaleChangeListener != null) {
            onScaleChangeListener.onScaleChange(f, f2, f3, f4, f5);
        }
    }

    private void dispatchScaleStageChange(float f, float f2, float f3, boolean z) {
        OnScaleLevelChangedListener onScaleLevelChangedListener = this.mScaleStageChangedListener;
        if (onScaleLevelChangedListener == null) {
            return;
        }
        if (f < 1.0f) {
            float f4 = this.mHighScaleLevelLowerBound;
            if (f3 >= f4 || f2 < f4) {
                float f5 = this.mMidScale;
                if (f3 < f5 && f2 >= f5) {
                    this.mScaleStageChangedListener.onMidScaleLevel(false, z);
                    return;
                }
                return;
            }
            onScaleLevelChangedListener.onHighScaleLevel(false, z);
            return;
        }
        float f6 = this.mHighScaleLevelLowerBound;
        if (f2 < f6 && f3 >= f6) {
            onScaleLevelChangedListener.onHighScaleLevel(true, z);
        }
    }

    private void drawBackground(Canvas canvas) {
        Drawable drawable;
        ImageView imageView = getImageView();
        if (imageView != null && (drawable = this.mAlphaDrawable) != null) {
            drawable.setAlpha((int) (this.mAlpha * 255.0f));
            this.mAlphaDrawable.setBounds(0, 0, getImageViewWidth(imageView), getImageViewHeight(imageView));
            this.mAlphaDrawable.draw(canvas);
        }
    }

    private void drawStroke(Canvas canvas) {
        RectF displayRect;
        if (this.mStrokeWidth > 0 && this.mStrokePaint != null && (displayRect = getDisplayRect()) != null) {
            if (this.mIsSlipping) {
                displayRect.left = Math.max(0.0f, displayRect.left);
                displayRect.top = Math.max(displayRect.top, 0.0f);
                displayRect.right = Math.min(displayRect.right, (float) getImageViewWidth(getImageView()));
                displayRect.bottom = Math.min(displayRect.bottom, (float) getImageViewHeight(getImageView()));
            }
            int i = this.mStrokeWidth;
            displayRect.inset((float) i, (float) i);
            canvas.drawRect(displayRect, this.mStrokePaint);
        }
    }

    private void ensureRotateDetector() {
        if (this.mRotateDetector == null) {
            this.mRotateDetector = new RotateManager();
        }
    }

    private void ensureScaleDragDetector() {
        ImageView imageView;
        if (this.mScaleDragDetector == null && (imageView = getImageView()) != null) {
            this.mScaleDragDetector = VersionedGestureDetector.newInstance(imageView.getContext(), this);
        }
    }

    private void ensureStrokePaint() {
        if (this.mStrokePaint == null) {
            this.mStrokePaint = new Paint();
            this.mStrokePaint.setAntiAlias(false);
            this.mStrokePaint.setStyle(Paint.Style.STROKE);
        }
    }

    private void ensureTapDetector() {
        ImageView imageView;
        if (this.mGestureDetector == null && (imageView = getImageView()) != null) {
            this.mGestureDetector = new GestureDetector(imageView.getContext(), new GestureDetector.SimpleOnGestureListener() {
                public boolean onDown(MotionEvent motionEvent) {
                    return true;
                }

                public void onLongPress(MotionEvent motionEvent) {
                    if (PhotoViewAttacher.this.mLongClickListener != null) {
                        PhotoViewAttacher.this.mLongClickListener.onLongClick(PhotoViewAttacher.this.getImageView());
                    }
                }
            });
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        }
    }

    private int getCorrectHeight(int i, int i2) {
        RectF displayRect = getDisplayRect(this.mBaseMatrix);
        return displayRect.height() > displayRect.width() ? Math.max(i, i2) : Math.min(i, i2);
    }

    private int getCorrectWidth(int i, int i2) {
        RectF displayRect = getDisplayRect(this.mBaseMatrix);
        return displayRect.width() > displayRect.height() ? Math.max(i, i2) : Math.min(i, i2);
    }

    /* access modifiers changed from: private */
    public RectF getDisplayRect(Matrix matrix) {
        Drawable drawable;
        ImageView imageView = getImageView();
        if (imageView == null || getImageViewHeight(imageView) <= 0 || getImageViewWidth(imageView) <= 0 || (drawable = imageView.getDrawable()) == null) {
            return null;
        }
        this.mDisplayRect.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        matrix.mapRect(this.mDisplayRect);
        return this.mDisplayRect;
    }

    private int getDrawableHeight(int i) {
        Drawable drawable;
        ImageView imageView = getImageView();
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return 0;
        }
        return (((i > 0 ? i + 45 : i + -45) / 90) & 1) == 0 ? drawable.getIntrinsicHeight() : drawable.getIntrinsicWidth();
    }

    private int getDrawableWidth(int i) {
        Drawable drawable;
        ImageView imageView = getImageView();
        if (imageView == null || (drawable = imageView.getDrawable()) == null) {
            return 0;
        }
        return (((i > 0 ? i + 45 : i + -45) / 90) & 1) == 0 ? drawable.getIntrinsicWidth() : drawable.getIntrinsicHeight();
    }

    private int getEnlargeMode(float f, float f2) {
        if (this.mSupportHd) {
            return 0;
        }
        return Math.round(f) < Math.round(f2) ? 1 : 2;
    }

    private float getHighScaleLevelLowerBound() {
        float f = this.mHighScaleLevelLowerBound;
        return f > 0.0f ? f : getMaximumScale();
    }

    /* access modifiers changed from: private */
    public int getImageViewHeight(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    /* access modifiers changed from: private */
    public int getImageViewWidth(ImageView imageView) {
        if (imageView == null) {
            return 0;
        }
        return (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private float getMaxPointsScale() {
        float f = this.mMaxPointsScale;
        return f > 0.0f ? f : getMaximumScale();
    }

    private float getMaxPointsScalingScale() {
        return getMaxPointsScale() * 1.5f;
    }

    private RectF getSlippedModeRect(int i, int i2) {
        Drawable drawable;
        ImageView imageView = getImageView();
        int realScreenWidth = BaseConfig.ScreenConfig.getRealScreenWidth();
        int i3 = i2 - i;
        if (imageView == null || getImageViewHeight(imageView) <= 0 || getImageViewWidth(imageView) <= 0 || (drawable = imageView.getDrawable()) == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (!(intrinsicWidth == 0 || intrinsicHeight == 0)) {
            double d = 1.0d;
            double d2 = (double) intrinsicHeight;
            double d3 = (((double) i3) * 1.0d) / d2;
            double d4 = ((double) intrinsicWidth) * d3;
            int i4 = (int) d4;
            if (i4 < 450) {
                d = 450.0d / ((double) i4);
            }
            int i5 = (int) (d4 * d);
            int i6 = (i2 + i) / 2;
            int i7 = ((int) ((d2 * d3) * d)) / 2;
            return new RectF((float) ((realScreenWidth - i5) / 2), (float) (i6 + i7), (float) ((realScreenWidth + i5) / 2), (float) (i6 - i7));
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Transition getTransition() {
        ImageView imageView;
        if (this.mTransition == null && (imageView = getImageView()) != null) {
            this.mTransition = new Transition(imageView.getContext());
        }
        return this.mTransition;
    }

    private float getValue(Matrix matrix, int i) {
        matrix.getValues(this.mMatrixValues);
        return this.mMatrixValues[i];
    }

    private static boolean hasDrawable(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r0 = r1.mRotateDetector;
     */
    private boolean interceptCheckBounds() {
        RotateManager rotateManager;
        return this.mIsDragDownOut || getTransition().isRunning() || (rotateManager != null && rotateManager.isRotating());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r1.mRotateDetector;
     */
    private boolean interceptDrag() {
        RotateManager rotateManager;
        uk.co.senab.photoview.gestures.GestureDetector gestureDetector = this.mScaleDragDetector;
        return (gestureDetector != null && gestureDetector.isScaling()) || (rotateManager != null && rotateManager.isRotating());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        r0 = r2.mRotateDetector;
     */
    private boolean interceptDrawTiles() {
        RotateManager rotateManager;
        return !this.mIsRegionDecodeEnable || getTransition().isRunning() || isViewAnimationRunning() || (rotateManager != null && rotateManager.isRotating());
    }

    private boolean interceptMotionEnd() {
        RotateManager rotateManager = this.mRotateDetector;
        return rotateManager != null && rotateManager.isRotating();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r0 = r1.mRotateDetector;
     */
    private boolean interceptTouch() {
        RotateManager rotateManager;
        return getTransition().isRunning() || (rotateManager != null && rotateManager.isAutoRotating());
    }

    private boolean isAnimRunning(int i) {
        return (this.mAnim & i) == i;
    }

    private boolean isAniming() {
        return this.mAnim != 0;
    }

    private static boolean isSupportedScaleType(ImageView.ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        if (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()] != 1) {
            return true;
        }
        throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = r0.getAnimation();
     */
    private boolean isViewAnimationRunning() {
        Animation animation;
        ImageView imageView = getImageView();
        return imageView != null && animation != null && animation.hasStarted() && !animation.hasEnded();
    }

    private boolean isWaitingTransition() {
        return this.mWaitingTransition != null && ThreadManager.getMainHandler().hasCallbacksCompat(this.mWaitingTransition);
    }

    private boolean needDrawTile() {
        if (interceptDrawTiles()) {
            return false;
        }
        ImageView imageView = getImageView();
        if (!(this.mTileView == null || imageView == null || imageView.getDrawable() == null)) {
            Drawable drawable = imageView.getDrawable();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int tileProviderWidth = this.mTileView.getTileProviderWidth();
            int tileProviderHeight = this.mTileView.getTileProviderHeight();
            if (tileProviderWidth <= 0 || tileProviderHeight <= 0) {
                LogManager.getLogger().w("PhotoViewAttacher", String.format(Locale.US, "invalid tile size[%dx%d]", new Object[]{Integer.valueOf(tileProviderWidth), Integer.valueOf(tileProviderHeight)}));
            } else {
                if (((this.mTileView.getTileProviderRotation() / 90) & 1) == 1) {
                    tileProviderWidth = this.mTileView.getTileProviderHeight();
                    tileProviderHeight = this.mTileView.getTileProviderWidth();
                }
                float f = (((float) intrinsicWidth) * 1.0f) / ((float) intrinsicHeight);
                float f2 = (((float) tileProviderWidth) * 1.0f) / ((float) tileProviderHeight);
                if (MiscUtil.floatNear(f, f2, 0.5f)) {
                    return getDisplayRect() != null && getScale() >= 1.0f;
                }
                LogManager.getLogger().w("PhotoViewAttacher", String.format("drawable w/h not equal to tile w/h: %.2f, %.2f", new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
                if (MiscUtil.floatEquals((float) (Math.min(intrinsicWidth, intrinsicHeight) / Math.max(intrinsicWidth, intrinsicHeight)), (float) (Math.min(tileProviderWidth, tileProviderHeight) / Math.max(tileProviderWidth, tileProviderHeight)))) {
                    LogManager.getLogger().e("PhotoViewAttacher", String.format("drawable w[%s], h[%s] not equal to tile w[%s], h[%s]; tile rotation[%s]", new Object[]{Integer.valueOf(intrinsicWidth), Integer.valueOf(intrinsicHeight), Integer.valueOf(tileProviderWidth), Integer.valueOf(tileProviderHeight), Integer.valueOf(this.mTileView.getTileProviderRotation())}));
                    HashMap hashMap = new HashMap();
                    hashMap.put("tile_rotation", String.valueOf(this.mTileView.getTileProviderRotation()));
                    GallerySamplingStatHelper.recordErrorEvent("photo", "photo_tile_orientation_error", hashMap);
                }
            }
        }
        return false;
    }

    private void notifyTileViewInvalidate() {
        if (needDrawTile()) {
            this.mTileView.notifyInvalidate(getDisplayRect(), getRotate());
        }
    }

    private void onBaseMatrixChanged() {
        animEnterAfterDrawableBound();
        calculateScales();
        TileView tileView = this.mTileView;
        if (tileView != null) {
            tileView.setViewPort(new Rect(0, 0, getImageViewWidth(getImageView()), getImageViewHeight(getImageView())));
        }
    }

    private void onScaleChanged(float f, float f2, float f3, float f4) {
        dispatchScaleChanged(f, f2, f3, f4, getScale());
    }

    private void performMeshHapticFeedback() {
        ImageView imageView = getImageView();
        if (imageView != null) {
            LinearMotorHelper.performHapticFeedback(imageView.getContext(), LinearMotorHelper.EFFECT_KEY_MESH_NORMAL);
        }
    }

    /* access modifiers changed from: private */
    public void postRotate(float f, float f2, float f3) {
        this.mSuppMatrix.postRotate(f, f2, f3);
    }

    /* access modifiers changed from: private */
    public void postScale(float f, float f2, float f3, float f4) {
        this.mSuppMatrix.postScale(f, f2, f3, f4);
        onScaleChanged(f, f2, f3, f4);
    }

    /* access modifiers changed from: private */
    public void postTranslate(float f, float f2) {
        this.mSuppMatrix.postTranslate(f, f2);
    }

    private void removeWaitingTransition() {
        if (this.mWaitingTransition != null) {
            ThreadManager.getMainHandler().removeCallbacks(this.mWaitingTransition);
            this.mWaitingTransition = null;
        }
    }

    private void resetDragDownOutState() {
        this.mIsDragDownOut = false;
        this.mDragDownOutY = 0.0f;
    }

    /* access modifiers changed from: private */
    public void resetMatrix() {
        RectF displayRect;
        float scale = getScale();
        if (!getTransition().isRunning()) {
            this.mSuppMatrix.reset();
        }
        setImageViewMatrix(getDrawMatrix());
        OnScaleLevelChangedListener onScaleLevelChangedListener = this.mScaleStageChangedListener;
        if (onScaleLevelChangedListener != null) {
            if (scale >= this.mMaxDoubleTapScale) {
                onScaleLevelChangedListener.onHighScaleLevel(false, false);
            } else if (scale >= this.mMidScale) {
                onScaleLevelChangedListener.onMidScaleLevel(false, false);
            }
        }
        if (!getTransition().isRunning() && (displayRect = getDisplayRect(this.mBaseMatrix)) != null) {
            dispatchScaleChanged(1.0f, 1.0f, displayRect.centerX(), displayRect.centerY(), getScale());
        }
        checkMatrixBounds();
    }

    /* access modifiers changed from: private */
    public void setImageViewMatrix(Matrix matrix) {
        RectF displayRect;
        ImageView imageView = getImageView();
        if (imageView != null) {
            checkImageViewScaleType();
            imageView.setImageMatrix(matrix);
            notifyTileViewInvalidate();
            Set<OnMatrixChangedListener> set = this.mMatrixChangeListeners;
            if (set != null && set.size() > 0 && (displayRect = getDisplayRect(matrix)) != null) {
                RectF rectF = new RectF(displayRect);
                for (OnMatrixChangedListener onMatrixChanged : this.mMatrixChangeListeners) {
                    onMatrixChanged.onMatrixChanged(rectF);
                }
            }
        }
    }

    private static void setImageViewScaleTypeMatrix(ImageView imageView) {
        if (imageView != null && !(imageView instanceof IPhotoView) && !ImageView.ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ImageView.ScaleType.MATRIX);
        }
    }

    private void setIsSlipping(boolean z) {
        this.mIsSlipping = z;
    }

    private void setRotate(float f, float f2, float f3) {
        this.mSuppMatrix.setRotate(f, f2, f3);
    }

    private void setScale(float f, float f2, float f3, float f4) {
        this.mSuppMatrix.setScale(f, f2, f3, f4);
        onScaleChanged(f, f2, f3, f4);
    }

    private void setStroke(int i, int i2, int i3) {
        if (this.mStrokeColor != i || this.mStrokeWidth != i2 || this.mStrokeAlpha != i3) {
            this.mStrokeColor = i;
            this.mStrokeWidth = i2;
            this.mStrokeAlpha = i3;
            if (i2 > 0) {
                ensureStrokePaint();
                this.mStrokePaint.setColor(this.mStrokeColor);
                this.mStrokePaint.setStrokeWidth((float) this.mStrokeWidth);
                int i4 = this.mStrokeAlpha;
                if (i4 >= 0 && i4 <= 255) {
                    this.mStrokePaint.setAlpha(i4);
                }
            }
            ImageView imageView = getImageView();
            if (imageView != null) {
                imageView.invalidate();
            }
        }
    }

    /* access modifiers changed from: private */
    public int trimRotation(float f) {
        return ((((int) (f > 0.0f ? f + 45.0f : f - 45.0f)) / 90) * 90) % 360;
    }

    /* access modifiers changed from: private */
    public void tryAlignBounds() {
        checkAndDisplayMatrix(true, true);
    }

    private Paint tryGetViewPaint() {
        if (!hasDrawable(getImageView())) {
            return null;
        }
        Drawable drawable = getImageView().getDrawable();
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getPaint();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void updateAlpha(float f) {
        if (f < 1.0f) {
            this.mAlpha = MiscUtil.clamp(f, 0.0f, 1.0f);
        } else {
            this.mAlpha = 1.0f;
        }
        OnBackgroundAlphaChangedListener onBackgroundAlphaChangedListener = this.mAlphaChangedListener;
        if (onBackgroundAlphaChangedListener != null) {
            onBackgroundAlphaChangedListener.onAlphaChanged(this.mAlpha);
        }
    }

    /* access modifiers changed from: private */
    public void updateBaseMatrix(Drawable drawable, int i) {
        RectF calculateBaseRect = calculateBaseRect(drawable, i);
        if (calculateBaseRect != null) {
            this.mBaseMatrix.reset();
            this.mBaseMatrix.postRotate((float) (0 - i), calculateBaseRect.centerX(), calculateBaseRect.centerY());
            RectF displayRect = getDisplayRect(this.mBaseMatrix);
            this.mBaseMatrix.postTranslate(calculateBaseRect.centerX() - displayRect.centerX(), calculateBaseRect.centerY() - displayRect.centerY());
            this.mBaseMatrix.postScale(calculateBaseRect.width() / displayRect.width(), calculateBaseRect.height() / displayRect.height(), calculateBaseRect.centerX(), calculateBaseRect.centerY());
            onBaseMatrixChanged();
        }
    }

    public void addOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        if (onMatrixChangedListener != null) {
            this.mMatrixChangeListeners.add(onMatrixChangedListener);
        }
    }

    public void addOnRotateListener(OnRotateListener onRotateListener) {
        if (onRotateListener != null) {
            this.mOnRotateListeners.add(onRotateListener);
        }
    }

    public void afterDraw(Canvas canvas) {
        if (needDrawTile()) {
            this.mTileView.draw(canvas, tryGetViewPaint());
        }
        drawStroke(canvas);
    }

    public void animEnter(ItemViewInfo itemViewInfo, TransitionListener transitionListener) {
        if (itemViewInfo != null && getTransition() != null && getImageView() != null) {
            getTransition().stop();
            RectF displayRect = getDisplayRect();
            if (displayRect != null) {
                ItemViewInfo imageInfo = ItemViewInfo.getImageInfo(getImageView(), 0);
                float width = ((float) itemViewInfo.getWidth()) / displayRect.width();
                float height = ((float) itemViewInfo.getHeight()) / displayRect.height();
                if (width <= height) {
                    width = height;
                }
                float scale = getScale();
                float width2 = displayRect.width() * width;
                float height2 = displayRect.height() * width;
                int height3 = (int) (((((float) itemViewInfo.getHeight()) - height2) / 2.0f) + ((float) itemViewInfo.getY()));
                int x = (int) ((((float) imageInfo.getX()) + displayRect.left) - ((float) ((int) (((((float) itemViewInfo.getWidth()) - width2) / 2.0f) + ((float) itemViewInfo.getX())))));
                int y = (int) ((((float) imageInfo.getY()) + displayRect.top) - ((float) height3));
                postScale(width, width, displayRect.left, displayRect.top);
                postTranslate((float) (-x), (float) (-y));
                setImageViewMatrix(getDrawMatrix());
                getTransition().scale(width, scale);
                getTransition().translate(0, 0, x, y);
                if (((float) itemViewInfo.getWidth()) < width2 || ((float) itemViewInfo.getHeight()) < height2) {
                    float clamp = MiscUtil.clamp(((float) itemViewInfo.getWidth()) / width2, 0.0f, 1.0f);
                    float clamp2 = MiscUtil.clamp(((float) itemViewInfo.getHeight()) / height2, 0.0f, 1.0f);
                    getTransition().clip(clamp, clamp2, 1.0f - clamp, 1.0f - clamp2);
                }
                getTransition().ensureAlpha(true);
                getTransition().alpha(this.mAlpha, 1.0f);
                getTransition().setTransitionListener(transitionListener);
                getTransition().start(false);
            } else if (!isWaitingTransition()) {
                CompatHandler mainHandler = ThreadManager.getMainHandler();
                WaitingTransition waitingTransition = new WaitingTransition(itemViewInfo, transitionListener);
                this.mWaitingTransition = waitingTransition;
                mainHandler.postDelayed(waitingTransition, 200);
            }
        } else if (transitionListener != null) {
            transitionListener.onTransitEnd();
        }
    }

    public void animExit(ItemViewInfo itemViewInfo, TransitionListener transitionListener) {
        if (itemViewInfo != null && itemViewInfo.isLocationValid() && getTransition() != null && getImageView() != null) {
            getTransition().stop();
            RectF displayRect = getDisplayRect(getDrawMatrix());
            if (displayRect != null) {
                RectF rectF = new RectF(displayRect);
                ItemViewInfo imageInfo = ItemViewInfo.getImageInfo(getImageView(), 0);
                RectF displayRect2 = getDisplayRect(this.mBaseMatrix);
                float width = ((float) itemViewInfo.getWidth()) / displayRect2.width();
                float height = ((float) itemViewInfo.getHeight()) / displayRect2.height();
                if (width <= height) {
                    width = height;
                }
                float scale = getScale();
                float width2 = displayRect2.width() * width;
                float height2 = displayRect2.height() * width;
                int height3 = (int) (((((float) itemViewInfo.getHeight()) - height2) / 2.0f) + ((float) itemViewInfo.getY()));
                int x = (int) ((((float) imageInfo.getX()) + rectF.left) - ((float) ((int) (((((float) itemViewInfo.getWidth()) - width2) / 2.0f) + ((float) itemViewInfo.getX())))));
                int y = (int) ((((float) imageInfo.getY()) + rectF.top) - ((float) height3));
                if (((float) itemViewInfo.getWidth()) < width2 || ((float) itemViewInfo.getHeight()) < height2) {
                    getTransition().clip(1.0f, 1.0f, (((float) itemViewInfo.getWidth()) / width2) - 1.0f, MiscUtil.clamp(((float) itemViewInfo.getHeight()) / height2, 0.0f, 1.0f) - 1.0f);
                }
                getTransition().scale(scale, width);
                getTransition().translate(0, 0, -x, -y);
            }
            getTransition().setTransitionListener(transitionListener);
            getTransition().ensureAlpha(false);
            getTransition().alpha(this.mAlpha, 0.0f);
            getTransition().start(true);
        } else if (transitionListener != null) {
            transitionListener.onTransitEnd();
        }
    }

    public boolean beforeDraw(Canvas canvas) {
        drawBackground(canvas);
        if (!getTransition().isRunning()) {
            return !getTransition().isExited();
        }
        if (getTransition().getClipRect() != null) {
            canvas.clipRect(getTransition().getClipRect());
        }
        return true;
    }

    public boolean canRotatable() {
        return this.mRotateEnabled;
    }

    public boolean canZoom() {
        return this.mZoomEnabled;
    }

    public void cleanup() {
        WeakReference<ImageView> weakReference = this.mImageView;
        if (weakReference != null) {
            ImageView imageView = (ImageView) weakReference.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener((View.OnTouchListener) null);
                cancelFling();
            }
            GestureDetector gestureDetector = this.mGestureDetector;
            if (gestureDetector != null) {
                gestureDetector.setOnDoubleTapListener((GestureDetector.OnDoubleTapListener) null);
            }
            this.mMatrixChangeListeners.clear();
            this.mPhotoTapListener = null;
            this.mViewTapListener = null;
            this.mLongClickListener = null;
            this.mScaleChangeListener = null;
            this.mExitListener = null;
            if (getTransition().isRunning()) {
                getTransition().stop();
                getTransition().setTransitionListener((TransitionListener) null);
            }
            RotateManager rotateManager = this.mRotateDetector;
            if (rotateManager != null && rotateManager.isRotating()) {
                this.mRotateDetector.stop();
            }
            releaseTile();
            removeWaitingTransition();
            this.mImageView = null;
        }
    }

    public boolean getAbsoluteRect(RectF rectF) {
        return getDrawMatrix().mapRect(rectF);
    }

    public RectF getBaseDisplayRect() {
        RectF displayRect = getDisplayRect(this.mBaseMatrix);
        if (displayRect != null) {
            return new RectF(displayRect);
        }
        return null;
    }

    public Matrix getBaseMatrix() {
        return new Matrix(this.mBaseMatrix);
    }

    public Matrix getDisplayMatrix() {
        return new Matrix(getDrawMatrix());
    }

    public RectF getDisplayRect() {
        checkMatrixBounds();
        RectF displayRect = getDisplayRect(getDrawMatrix());
        if (displayRect != null) {
            return new RectF(displayRect);
        }
        return null;
    }

    public Matrix getDrawMatrix() {
        RectF slippedModeRect;
        this.mDrawMatrix.set(this.mBaseMatrix);
        if (this.mIsSlipping) {
            ImageView imageView = getImageView();
            RectF baseDisplayRect = getBaseDisplayRect();
            if (!(baseDisplayRect == null || (slippedModeRect = getSlippedModeRect(this.mSlippedRectTop, this.mSlippedRectBottom)) == null)) {
                this.mSuppMatrix.setScale(((((slippedModeRect.right - slippedModeRect.left) / (baseDisplayRect.right - baseDisplayRect.left)) - 1.0f) * this.mProgress) + 1.0f, ((((slippedModeRect.right - slippedModeRect.left) / (baseDisplayRect.right - baseDisplayRect.left)) - 1.0f) * this.mProgress) + 1.0f, (float) (imageView.getRight() / 2), (float) (imageView.getBottom() / 2));
            }
        }
        this.mDrawMatrix.postConcat(this.mSuppMatrix);
        return this.mDrawMatrix;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        r0 = r0.getDrawable();
     */
    public RectF getDrawableSize() {
        Drawable drawable;
        ImageView imageView = getImageView();
        return (imageView == null || getImageViewHeight(imageView) <= 0 || getImageViewWidth(imageView) <= 0 || drawable == null) ? new RectF(0.0f, 0.0f, 0.0f, 0.0f) : new RectF(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
    }

    public ImageView getImageView() {
        WeakReference<ImageView> weakReference = this.mImageView;
        ImageView imageView = weakReference != null ? (ImageView) weakReference.get() : null;
        if (imageView == null) {
            cleanup();
            LogManager.getLogger().i("PhotoViewAttacher", "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    public float getMaximumScale() {
        float f = this.mMaxDoubleTapScale;
        return f > 0.0f ? f : this.mMaxScale;
    }

    public float getMediumScale() {
        return this.mMidScale;
    }

    public float getMinimumScale() {
        return this.mMinScale;
    }

    public OnPhotoTapListener getOnPhotoTapListener() {
        return this.mPhotoTapListener;
    }

    public OnViewTapListener getOnViewTapListener() {
        return this.mViewTapListener;
    }

    /* access modifiers changed from: package-private */
    public float getPointsScaleEnlargeFactor() {
        return this.mPointsScaleEnlargeFactor;
    }

    public float getRotate() {
        return MatrixUtil.getRotate(this.mSuppMatrix) + MatrixUtil.getRotate(this.mBaseMatrix);
    }

    public float getScale() {
        return MatrixUtil.getScale(this.mSuppMatrix);
    }

    public ScaleLevel getScaleLevel() {
        float scale = getScale();
        return scale < getMediumScale() - 0.001f ? ScaleLevel.LOW : (scale < getMediumScale() - 0.001f || scale >= this.mHighScaleLevelLowerBound - 0.001f) ? ScaleLevel.HIGH : ScaleLevel.MIDDLE;
    }

    public ImageView.ScaleType getScaleType() {
        return this.mScaleType;
    }

    public Matrix getSuppMatrix() {
        return new Matrix(this.mSuppMatrix);
    }

    public Rect getViewPort() {
        ImageView imageView = getImageView();
        return new Rect(0, 0, getImageViewWidth(imageView), getImageViewHeight(imageView));
    }

    public Bitmap getVisibleRectangleBitmap() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getZoomDuration() {
        return this.mZoomDuration;
    }

    /* access modifiers changed from: package-private */
    public float getZoomDurationLengthenFactor() {
        return this.mZoomDurationLengthenFactor;
    }

    /* access modifiers changed from: package-private */
    public Interpolator getZoomInterpolator() {
        return this.mZoomInterpolator;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        r0 = r1.mRotateDetector;
     */
    public boolean isGestureOperating() {
        RotateManager rotateManager;
        uk.co.senab.photoview.gestures.GestureDetector gestureDetector = this.mScaleDragDetector;
        return (gestureDetector != null && (gestureDetector.isDragging() || this.mScaleDragDetector.isScaling())) || (rotateManager != null && rotateManager.isRotating());
    }

    public void onDoubleTap(float f, float f2) {
        if (hasDrawable(getImageView()) && !isAnimRunning(1)) {
            float scale = getScale();
            if (scale < getMediumScale() - 0.001f) {
                setScale(getMediumScale(), f, f2, true);
                if (this.mScaleStageChangedListener != null && getMediumScale() < getMaximumScale()) {
                    this.mScaleStageChangedListener.onMidScaleLevel(true, true);
                }
            } else if (scale < getMediumScale() - 0.001f || scale >= getHighScaleLevelLowerBound() - 0.001f) {
                setScale(getMinimumScale(), f, f2, true);
            } else {
                setScale(getMaximumScale(), f, f2, true);
                OnScaleLevelChangedListener onScaleLevelChangedListener = this.mScaleStageChangedListener;
                if (onScaleLevelChangedListener != null) {
                    onScaleLevelChangedListener.onHighScaleLevel(true, true);
                }
            }
        }
    }

    public void onDrag(float f, float f2) {
        if (!interceptDrag()) {
            if (getScale() >= this.mMinScale || this.mIsDragDownOut) {
                if (!this.mHasScale && (this.mIsDragDownOut || (this.mVScrollEdge == 5 && Math.abs(f2) > Math.abs(f) && this.mTouchDownY > getImageView().getResources().getDisplayMetrics().density * 25.0f))) {
                    this.mDragDownOutY += f2;
                    if (!this.mIsDragDownOut && this.mDragDownOutY >= this.mDragDownOutThreshold) {
                        this.mIsDragDownOut = true;
                        this.mDragHandled = true;
                    }
                }
                ImageView imageView = getImageView();
                if (this.mIsDragDownOut) {
                    float scale = getScale();
                    int imageViewWidth = getImageViewWidth(getImageView());
                    int imageViewHeight = getImageViewHeight(getImageView());
                    float value = getValue(this.mSuppMatrix, 5);
                    float f3 = (float) imageViewHeight;
                    float f4 = ((1.0f - (f2 / f3)) * 0.5f) + 0.5f;
                    postScale(f4, f4, (float) (imageViewWidth / 2), (f3 + value) / 2.0f);
                    postTranslate(f / 2.0f, f2);
                    updateAlpha(1.0f - (value / f3));
                    dispatchScaleStageChange(f4, scale, getScale(), false);
                } else if (this.mZoomEnabled) {
                    postTranslate(f, f2);
                }
                checkAndDisplayMatrix();
                ViewParent parent = imageView.getParent();
                if (this.mAllowParentInterceptOnEdge) {
                    if (Math.abs(f) > Math.abs(f2)) {
                        int i = this.mHScrollEdge;
                        if (i == 4 || ((i == 0 && f >= 1.0f) || (this.mHScrollEdge == 1 && f <= -1.0f))) {
                            if (parent != null && !this.mDragHandled) {
                                parent.requestDisallowInterceptTouchEvent(false);
                                return;
                            }
                            return;
                        }
                    } else {
                        int i2 = this.mVScrollEdge;
                        if (i2 == 5 || ((i2 == 2 && f2 >= 1.0f) || (this.mVScrollEdge == 3 && f2 <= -1.0f))) {
                            if (parent != null && !this.mDragHandled) {
                                parent.requestDisallowInterceptTouchEvent(false);
                                return;
                            }
                            return;
                        }
                    }
                } else if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                this.mDragHandled = true;
            }
        }
    }

    public void onFling(float f, float f2, float f3, float f4) {
        if (this.mZoomEnabled && !interceptDrag() && getScale() >= this.mMinScale) {
            ImageView imageView = getImageView();
            RectF displayRect = getDisplayRect();
            if (imageView != null && displayRect != null) {
                int imageViewHeight = getImageViewHeight(imageView);
                int imageViewWidth = getImageViewWidth(imageView);
                if (Math.floor((double) displayRect.left) <= 0.0d && Math.floor((double) displayRect.top) <= 0.0d) {
                    if (imageViewHeight > 0 && Math.ceil((double) displayRect.bottom) < ((double) imageViewHeight)) {
                        return;
                    }
                    if (imageViewWidth <= 0 || Math.ceil((double) displayRect.right) >= ((double) imageViewWidth)) {
                        this.mCurrentFlingRunnable = new FlingRunnable(imageView.getContext());
                        this.mCurrentFlingRunnable.fling(imageViewWidth, imageViewHeight, (int) f3, (int) f4);
                        imageView.post(this.mCurrentFlingRunnable);
                    }
                }
            }
        }
    }

    public void onGlobalLayout() {
        ImageView imageView = getImageView();
        if (imageView != null) {
            int top = imageView.getTop();
            int right = imageView.getRight();
            int bottom = imageView.getBottom();
            int left = imageView.getLeft();
            if (top != this.mIvTop || bottom != this.mIvBottom || left != this.mIvLeft || right != this.mIvRight) {
                updateBaseMatrix(imageView.getDrawable(), trimRotation(getRotate()));
                resetMatrix();
                this.mIvTop = top;
                this.mIvRight = right;
                this.mIvBottom = bottom;
                this.mIvLeft = left;
            }
        }
    }

    public void onScale(float f, float f2, float f3) {
        if (this.mZoomEnabled) {
            this.mHasScale = true;
            this.mIsDragDownOut = false;
            float scale = getScale();
            if (scale < getMaxPointsScalingScale() || f < 1.0f) {
                this.mLastScaleFocusX = f2;
                this.mLastScaleFocusY = f3;
                postScale(f, f, f2, f3);
                checkAndDisplayMatrix();
                float scale2 = getScale();
                updateAlpha(scale2);
                if (scale < getMaxPointsScalingScale() && scale2 >= getMaxPointsScalingScale()) {
                    performMeshHapticFeedback();
                }
                dispatchScaleStageChange(f, scale, scale2, isAnimRunning(1));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0125  */
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        OnExitListener onExitListener;
        int actionMasked = motionEvent.getActionMasked();
        boolean z2 = false;
        if (actionMasked == 0) {
            this.mIsTouchInProgress = true;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.mIsTouchInProgress = false;
        }
        if (this.mIntercepted) {
            return false;
        }
        if (!interceptTouch()) {
            if (hasDrawable((ImageView) view)) {
                ViewParent parent = view.getParent();
                int actionMasked2 = motionEvent.getActionMasked();
                if (actionMasked2 == 0) {
                    this.mDownScale = getScale();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    } else {
                        LogManager.getLogger().i("PhotoViewAttacher", "onTouch getParent() returned null");
                    }
                    this.mTouchDownY = motionEvent.getY();
                    this.mDragHandled = false;
                    this.mHasScale = false;
                    cancelFling();
                } else if (actionMasked2 == 1 || actionMasked2 == 3) {
                    if (interceptMotionEnd()) {
                        return true;
                    }
                    float scale = getScale();
                    if (this.mIsDragDownOut) {
                        if (this.mDragDownOutY >= this.mDragDownOutThreshold) {
                            OnExitListener onExitListener2 = this.mExitListener;
                            if (onExitListener2 != null) {
                                onExitListener2.onExit();
                            }
                        } else {
                            updateAlpha(1.0f);
                            resetMatrix();
                        }
                    } else if (this.mZoomEnabled) {
                        if (scale < this.mExitScale && (onExitListener = this.mExitListener) != null && this.mDownScale <= 1.0f) {
                            onExitListener.onExit();
                        } else if (scale < this.mMinScale) {
                            RectF displayRect = getDisplayRect();
                            if (displayRect != null) {
                                view.post(new AnimatedZoomRunnable(scale, this.mMinScale, displayRect.centerX(), displayRect.centerY()));
                                z = true;
                                this.mDragHandled = false;
                                resetDragDownOutState();
                                if (canRotatable()) {
                                    ensureRotateDetector();
                                    z = this.mRotateDetector.onTouchEvent(motionEvent);
                                }
                                z2 = z;
                                ensureScaleDragDetector();
                                if (this.mScaleDragDetector.onTouchEvent(motionEvent)) {
                                    z2 = true;
                                }
                            }
                        } else if (scale - getMaxPointsScale() <= 0.001f || this.mLastScaleFocusX <= 0.0f || this.mLastScaleFocusY <= 0.0f) {
                            tryAlignBounds();
                        } else {
                            view.post(new AnimatedZoomRunnable(scale, getMaxPointsScale(), this.mLastScaleFocusX, this.mLastScaleFocusY));
                            this.mLastScaleFocusX = 0.0f;
                            this.mLastScaleFocusY = 0.0f;
                        }
                    }
                    z = false;
                    this.mDragHandled = false;
                    resetDragDownOutState();
                    if (canRotatable()) {
                    }
                    z2 = z;
                    ensureScaleDragDetector();
                    if (this.mScaleDragDetector.onTouchEvent(motionEvent)) {
                    }
                }
                z = false;
                if (canRotatable()) {
                }
                z2 = z;
                ensureScaleDragDetector();
                if (this.mScaleDragDetector.onTouchEvent(motionEvent)) {
                }
            }
            ensureTapDetector();
            if (this.mGestureDetector.onTouchEvent(motionEvent)) {
                return true;
            }
            return z2;
        } else if (view.getParent() == null) {
            return true;
        } else {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        }
    }

    public void releaseTile() {
        TileView tileView = this.mTileView;
        if (tileView != null) {
            tileView.cleanup();
        }
        this.mTileView = null;
    }

    public void removeOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        if (onMatrixChangedListener != null) {
            this.mMatrixChangeListeners.remove(onMatrixChangedListener);
        }
    }

    public void removeOnRotateListener(OnRotateListener onRotateListener) {
        if (onRotateListener != null) {
            this.mOnRotateListeners.remove(onRotateListener);
        }
    }

    public void requestAlignBounds() {
        tryAlignBounds();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAllowParentInterceptOnEdge = z;
    }

    public void setAlphaBackground(Drawable drawable) {
        this.mAlphaDrawable = drawable;
        ImageView imageView = getImageView();
        if (imageView != null) {
            imageView.invalidate();
        }
    }

    public void setBackgroundAlpha(float f) {
        updateAlpha(f);
        ImageView imageView = getImageView();
        if (imageView != null) {
            imageView.invalidate();
        }
    }

    public boolean setDisplayMatrix(Matrix matrix) {
        if (matrix != null) {
            ImageView imageView = getImageView();
            if (imageView == null || imageView.getDrawable() == null) {
                return false;
            }
            this.mSuppMatrix.set(matrix);
            setImageViewMatrix(getDrawMatrix());
            checkMatrixBounds();
            return true;
        }
        throw new IllegalArgumentException("Matrix cannot be null");
    }

    public void setDragInsideBoundsEnabled(boolean z) {
        if (z) {
            if (this.mPendingDisableDragInsideBounds) {
                this.mPendingDisableDragInsideBounds = false;
            }
            this.mIsDragInsideBoundsEnabled = true;
            return;
        }
        this.mPendingDisableDragInsideBounds = false;
        if (!this.mIsDragInsideBoundsEnabled) {
            return;
        }
        if (this.mIsTouchInProgress || isAniming()) {
            this.mPendingDisableDragInsideBounds = true;
            return;
        }
        this.mIsDragInsideBoundsEnabled = false;
        tryAlignBounds();
    }

    public void setDraggableViewportInsets(Rect rect) {
        this.mOutOfBoundsRect.set(Math.max(0, rect.left), Math.max(0, rect.top), Math.max(0, rect.right), Math.max(0, rect.bottom));
    }

    public void setHDState(int i, int i2, boolean z) {
        this.mOriginWidth = i;
        this.mOriginHeight = i2;
        this.mSupportHd = z;
    }

    public void setInterceptTouch(boolean z) {
        this.mIntercepted = z;
    }

    public void setMaximumScale(float f) {
        checkZoomLevels(this.mMinScale, this.mMidScale, f);
        this.mMaxScale = f;
    }

    public void setMediumScale(float f) {
        checkZoomLevels(this.mMinScale, f, this.mMaxScale);
        this.mMidScale = f;
    }

    public void setMinimumScale(float f) {
        checkZoomLevels(f, this.mMidScale, this.mMaxScale);
        this.mMinScale = f;
        this.mExitScale = this.mMinScale * 0.8f;
    }

    public void setOnBackgroundAlphaChangedListener(OnBackgroundAlphaChangedListener onBackgroundAlphaChangedListener) {
        this.mAlphaChangedListener = onBackgroundAlphaChangedListener;
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        ensureTapDetector();
        if (onDoubleTapListener != null) {
            this.mGestureDetector.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
        }
    }

    public void setOnExitListener(OnExitListener onExitListener) {
        this.mExitListener = onExitListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mLongClickListener = onLongClickListener;
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.mPhotoTapListener = onPhotoTapListener;
    }

    public void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener) {
        this.mScaleChangeListener = onScaleChangeListener;
    }

    public void setOnScaleStageChangedListener(OnScaleLevelChangedListener onScaleLevelChangedListener) {
        this.mScaleStageChangedListener = onScaleLevelChangedListener;
    }

    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.mViewTapListener = onViewTapListener;
    }

    public void setPointsScaleEnlargeFactor(float f) {
        this.mPointsScaleEnlargeFactor = f;
        calculateScales();
    }

    public void setRegionDecodeEnable(boolean z) {
        if (this.mIsRegionDecodeEnable != z) {
            this.mIsRegionDecodeEnable = z;
            if (z) {
                notifyTileViewInvalidate();
            } else if (getImageView() != null) {
                getImageView().invalidate();
            }
        }
    }

    public void setRotatable(boolean z) {
        this.mRotateEnabled = z;
        if (!this.mRotateEnabled) {
            update(false);
        }
    }

    public void setRotationBy(float f) {
        postRotate(f % 360.0f, 0.0f, 0.0f);
        checkAndDisplayMatrix();
    }

    public void setRotationTo(float f) {
        setRotate(f % 360.0f, 0.0f, 0.0f);
        checkAndDisplayMatrix();
    }

    public void setScale(float f) {
        setScale(f, false);
    }

    public void setScale(float f, float f2, float f3, boolean z) {
        ImageView imageView = getImageView();
        if (imageView == null) {
            return;
        }
        if (f < this.mMinScale || f > getMaximumScale()) {
            LogManager.getLogger().i("PhotoViewAttacher", "Scale must be within the range of minScale and maxScale");
        } else if (z) {
            imageView.post(new AnimatedZoomRunnable(getScale(), f, f2, f3));
        } else {
            setScale(f, f, f2, f3);
            checkAndDisplayMatrix();
        }
    }

    public void setScale(float f, boolean z) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            setScale(f, (float) (imageView.getRight() / 2), (float) (imageView.getBottom() / 2), z);
        }
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (isSupportedScaleType(scaleType) && scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            update(true);
        }
    }

    /* access modifiers changed from: protected */
    public void setSlipProgress(float f) {
        this.mProgress = f;
        if (f > 0.0f) {
            setIsSlipping(true);
        } else {
            setIsSlipping(false);
        }
        setStroke(this.mStrokeColor, this.mStrokeWidth, (int) (this.mProgress * ((float) this.mDestStrokeAlpha)));
    }

    public void setSlippedRect(int i, int i2) {
        this.mSlippedRectTop = i;
        this.mSlippedRectBottom = i2;
    }

    public void setStroke(int i, int i2) {
        this.mDestStrokeAlpha = Color.alpha(i);
        setStroke(i, i2, this.mDestStrokeAlpha);
    }

    public void setZoomDuration(int i) {
        this.mZoomDuration = i;
    }

    public void setZoomDurationLengthenFactor(float f) {
        this.mZoomDurationLengthenFactor = f;
    }

    public void setZoomInterpolator(Interpolator interpolator) {
        this.mZoomInterpolator = interpolator;
    }

    public void setZoomTransitionDuration(int i) {
        if (i < 0) {
            i = 200;
        }
        this.mZoomDuration = i;
    }

    public void setZoomable(boolean z) {
        this.mZoomEnabled = z;
        ImageView imageView = getImageView();
        if (imageView != null && imageView.getDrawable() != null) {
            update(true);
        }
    }

    public void setupTile(TileBitProvider tileBitProvider, BitmapRecycleCallback bitmapRecycleCallback, TrimMemoryCallback trimMemoryCallback) {
        if (this.mTileView == null) {
            this.mTileView = new TileView(tileBitProvider, getImageView(), bitmapRecycleCallback, trimMemoryCallback);
        }
        this.mTileView.setViewPort(new Rect(0, 0, getImageViewWidth(getImageView()), getImageViewHeight(getImageView())));
        calculateScales();
        notifyTileViewInvalidate();
    }

    public boolean translateBy(float f, float f2) {
        if (!this.mZoomEnabled) {
            return false;
        }
        postTranslate(f, f2);
        checkAndDisplayMatrix();
        return true;
    }

    public void update(boolean z) {
        ImageView imageView = getImageView();
        if (imageView != null) {
            if (this.mZoomEnabled) {
                setImageViewScaleTypeMatrix(imageView);
            }
            updateBaseMatrix(imageView.getDrawable(), z ? trimRotation(getRotate()) : 0);
            if (!this.mZoomEnabled || !z) {
                resetMatrix();
            } else {
                setImageViewMatrix(getDrawMatrix());
            }
        }
    }
}
