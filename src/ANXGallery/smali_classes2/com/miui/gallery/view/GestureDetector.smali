.class public Lcom/miui/gallery/view/GestureDetector;
.super Ljava/lang/Object;
.source "GestureDetector.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/view/GestureDetector$GestureHandler;,
        Lcom/miui/gallery/view/GestureDetector$SimpleOnGestureListener;,
        Lcom/miui/gallery/view/GestureDetector$OnContextClickListener;,
        Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;,
        Lcom/miui/gallery/view/GestureDetector$OnGestureListener;
    }
.end annotation


# static fields
.field private static final DOUBLE_TAP_TIMEOUT:I

.field private static final LONGPRESS_TIMEOUT:I

.field private static final TAP_TIMEOUT:I


# instance fields
.field private mAlwaysInBiggerTapRegion:Z

.field private mAlwaysInTapRegion:Z

.field private mContextClickListener:Lcom/miui/gallery/view/GestureDetector$OnContextClickListener;

.field private mCurrentDownEvent:Landroid/view/MotionEvent;

.field private mDeferConfirmSingleTap:Z

.field private mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

.field private mDoubleTapSlopSquare:I

.field private mDoubleTapTouchSlopSquare:I

.field private mDownFocusX:F

.field private mDownFocusY:F

.field private final mHandler:Landroid/os/Handler;

.field private mIgnoreNextUpEvent:Z

.field private mInContextClick:Z

.field private mInLongPress:Z

.field private mIsDoubleTapEnabled:Z

.field private mIsDoubleTapping:Z

.field private mIsLongpressEnabled:Z

.field private mLastFocusX:F

.field private mLastFocusY:F

.field private final mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

.field private mMaximumFlingVelocity:I

.field private mMinimumFlingVelocity:I

.field private mPreviousUpEvent:Landroid/view/MotionEvent;

.field private mStillDown:Z

.field private mTouchSlopSquare:I

.field private mVelocityTracker:Landroid/view/VelocityTracker;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    invoke-static {}, Landroid/view/ViewConfiguration;->getLongPressTimeout()I

    move-result v0

    sput v0, Lcom/miui/gallery/view/GestureDetector;->LONGPRESS_TIMEOUT:I

    invoke-static {}, Landroid/view/ViewConfiguration;->getTapTimeout()I

    move-result v0

    sput v0, Lcom/miui/gallery/view/GestureDetector;->TAP_TIMEOUT:I

    invoke-static {}, Landroid/view/ViewConfiguration;->getDoubleTapTimeout()I

    move-result v0

    sput v0, Lcom/miui/gallery/view/GestureDetector;->DOUBLE_TAP_TIMEOUT:I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/gallery/view/GestureDetector$OnGestureListener;)V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/miui/gallery/view/GestureDetector;-><init>(Landroid/content/Context;Lcom/miui/gallery/view/GestureDetector$OnGestureListener;Landroid/os/Handler;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/gallery/view/GestureDetector$OnGestureListener;Landroid/os/Handler;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    if-eqz p3, :cond_0

    new-instance v0, Lcom/miui/gallery/view/GestureDetector$GestureHandler;

    invoke-direct {v0, p0, p3}, Lcom/miui/gallery/view/GestureDetector$GestureHandler;-><init>(Lcom/miui/gallery/view/GestureDetector;Landroid/os/Handler;)V

    iput-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    goto :goto_0

    :cond_0
    new-instance p3, Lcom/miui/gallery/view/GestureDetector$GestureHandler;

    invoke-direct {p3, p0}, Lcom/miui/gallery/view/GestureDetector$GestureHandler;-><init>(Lcom/miui/gallery/view/GestureDetector;)V

    iput-object p3, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    :goto_0
    iput-object p2, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    instance-of p3, p2, Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    if-eqz p3, :cond_1

    move-object p3, p2

    check-cast p3, Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    invoke-virtual {p0, p3}, Lcom/miui/gallery/view/GestureDetector;->setOnDoubleTapListener(Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;)V

    :cond_1
    instance-of p3, p2, Lcom/miui/gallery/view/GestureDetector$OnContextClickListener;

    if-eqz p3, :cond_2

    check-cast p2, Lcom/miui/gallery/view/GestureDetector$OnContextClickListener;

    invoke-virtual {p0, p2}, Lcom/miui/gallery/view/GestureDetector;->setContextClickListener(Lcom/miui/gallery/view/GestureDetector$OnContextClickListener;)V

    :cond_2
    invoke-direct {p0, p1}, Lcom/miui/gallery/view/GestureDetector;->init(Landroid/content/Context;)V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/view/GestureDetector;)Landroid/view/MotionEvent;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/view/GestureDetector;)Lcom/miui/gallery/view/GestureDetector$OnGestureListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/view/GestureDetector;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/view/GestureDetector;->dispatchLongPress()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/view/GestureDetector;)Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/view/GestureDetector;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/view/GestureDetector;->mStillDown:Z

    return p0
.end method

.method static synthetic access$502(Lcom/miui/gallery/view/GestureDetector;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/view/GestureDetector;->mDeferConfirmSingleTap:Z

    return p1
.end method

.method private cancel()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapping:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mStillDown:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInTapRegion:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInBiggerTapRegion:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDeferConfirmSingleTap:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mInLongPress:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mInContextClick:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mIgnoreNextUpEvent:Z

    return-void
.end method

.method private cancelTaps()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x2

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapping:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInTapRegion:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInBiggerTapRegion:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDeferConfirmSingleTap:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mInLongPress:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mInContextClick:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mIgnoreNextUpEvent:Z

    return-void
.end method

.method private dispatchLongPress()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    const/4 v1, 0x3

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDeferConfirmSingleTap:Z

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mInLongPress:Z

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    invoke-interface {v0, v1}, Lcom/miui/gallery/view/GestureDetector$OnGestureListener;->onLongPress(Landroid/view/MotionEvent;)V

    return-void
.end method

.method private init(Landroid/content/Context;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    if-eqz v0, :cond_1

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mIsLongpressEnabled:Z

    iput-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapEnabled:Z

    if-nez p1, :cond_0

    invoke-static {}, Landroid/view/ViewConfiguration;->getTouchSlop()I

    move-result p1

    const/16 v0, 0x64

    invoke-static {}, Landroid/view/ViewConfiguration;->getMinimumFlingVelocity()I

    move-result v1

    iput v1, p0, Lcom/miui/gallery/view/GestureDetector;->mMinimumFlingVelocity:I

    invoke-static {}, Landroid/view/ViewConfiguration;->getMaximumFlingVelocity()I

    move-result v1

    iput v1, p0, Lcom/miui/gallery/view/GestureDetector;->mMaximumFlingVelocity:I

    goto :goto_0

    :cond_0
    invoke-static {p1}, Landroid/view/ViewConfiguration;->get(Landroid/content/Context;)Landroid/view/ViewConfiguration;

    move-result-object p1

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledTouchSlop()I

    move-result v0

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledDoubleTapSlop()I

    move-result v1

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledMinimumFlingVelocity()I

    move-result v2

    iput v2, p0, Lcom/miui/gallery/view/GestureDetector;->mMinimumFlingVelocity:I

    invoke-virtual {p1}, Landroid/view/ViewConfiguration;->getScaledMaximumFlingVelocity()I

    move-result p1

    iput p1, p0, Lcom/miui/gallery/view/GestureDetector;->mMaximumFlingVelocity:I

    move p1, v0

    move v0, v1

    :goto_0
    mul-int/2addr p1, p1

    iput p1, p0, Lcom/miui/gallery/view/GestureDetector;->mTouchSlopSquare:I

    iput p1, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapTouchSlopSquare:I

    mul-int/2addr v0, v0

    iput v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapSlopSquare:I

    return-void

    :cond_1
    new-instance p1, Ljava/lang/NullPointerException;

    const-string v0, "OnGestureListener must not be null"

    invoke-direct {p1, v0}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method private isConsideredDoubleTap(Landroid/view/MotionEvent;Landroid/view/MotionEvent;Landroid/view/MotionEvent;)Z
    .locals 6

    iget-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapEnabled:Z

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    iget-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInBiggerTapRegion:Z

    if-nez v0, :cond_1

    return v1

    :cond_1
    invoke-virtual {p3}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v2

    invoke-virtual {p2}, Landroid/view/MotionEvent;->getEventTime()J

    move-result-wide v4

    sub-long/2addr v2, v4

    sget p2, Lcom/miui/gallery/view/GestureDetector;->DOUBLE_TAP_TIMEOUT:I

    int-to-long v4, p2

    cmp-long p2, v2, v4

    if-gtz p2, :cond_3

    const-wide/16 v4, 0x28

    cmp-long p2, v2, v4

    if-gez p2, :cond_2

    goto :goto_0

    :cond_2
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result p2

    float-to-int p2, p2

    invoke-virtual {p3}, Landroid/view/MotionEvent;->getX()F

    move-result v0

    float-to-int v0, v0

    sub-int/2addr p2, v0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result p1

    float-to-int p1, p1

    invoke-virtual {p3}, Landroid/view/MotionEvent;->getY()F

    move-result p3

    float-to-int p3, p3

    sub-int/2addr p1, p3

    mul-int/2addr p2, p2

    mul-int/2addr p1, p1

    add-int/2addr p2, p1

    iget p1, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapSlopSquare:I

    if-ge p2, p1, :cond_3

    const/4 v1, 0x1

    :cond_3
    :goto_0
    return v1
.end method


# virtual methods
.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 12

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-nez v1, :cond_0

    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v1, p1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    and-int/lit16 v0, v0, 0xff

    const/4 v1, 0x6

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-ne v0, v1, :cond_1

    move v4, v2

    goto :goto_0

    :cond_1
    move v4, v3

    :goto_0
    if-eqz v4, :cond_2

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v5

    goto :goto_1

    :cond_2
    const/4 v5, -0x1

    :goto_1
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v6

    const/4 v7, 0x0

    move v8, v3

    move v9, v7

    move v10, v9

    :goto_2
    if-ge v8, v6, :cond_4

    if-ne v5, v8, :cond_3

    goto :goto_3

    :cond_3
    invoke-virtual {p1, v8}, Landroid/view/MotionEvent;->getX(I)F

    move-result v11

    add-float/2addr v9, v11

    invoke-virtual {p1, v8}, Landroid/view/MotionEvent;->getY(I)F

    move-result v11

    add-float/2addr v10, v11

    :goto_3
    add-int/lit8 v8, v8, 0x1

    goto :goto_2

    :cond_4
    if-eqz v4, :cond_5

    add-int/lit8 v4, v6, -0x1

    goto :goto_4

    :cond_5
    move v4, v6

    :goto_4
    int-to-float v4, v4

    div-float/2addr v9, v4

    div-float/2addr v10, v4

    const/4 v4, 0x2

    const/4 v5, 0x3

    if-eqz v0, :cond_1b

    const/16 v8, 0x3e8

    if-eq v0, v2, :cond_11

    if-eq v0, v4, :cond_b

    if-eq v0, v5, :cond_a

    const/4 v2, 0x5

    if-eq v0, v2, :cond_9

    if-eq v0, v1, :cond_6

    goto/16 :goto_c

    :cond_6
    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusX:F

    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusX:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusY:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusY:F

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    iget v1, p0, Lcom/miui/gallery/view/GestureDetector;->mMaximumFlingVelocity:I

    int-to-float v1, v1

    invoke-virtual {v0, v8, v1}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v2, v1}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v2

    iget-object v4, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v4, v1}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v1

    move v4, v3

    :goto_5
    if-ge v4, v6, :cond_21

    if-ne v4, v0, :cond_7

    goto :goto_6

    :cond_7
    invoke-virtual {p1, v4}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v5

    iget-object v8, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v8, v5}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v8

    mul-float/2addr v8, v2

    iget-object v9, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v9, v5}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v5

    mul-float/2addr v5, v1

    add-float/2addr v8, v5

    cmpg-float v5, v8, v7

    if-gez v5, :cond_8

    iget-object p1, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {p1}, Landroid/view/VelocityTracker;->clear()V

    goto/16 :goto_c

    :cond_8
    :goto_6
    add-int/lit8 v4, v4, 0x1

    goto :goto_5

    :cond_9
    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusX:F

    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusX:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusY:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusY:F

    invoke-direct {p0}, Lcom/miui/gallery/view/GestureDetector;->cancelTaps()V

    goto/16 :goto_c

    :cond_a
    invoke-direct {p0}, Lcom/miui/gallery/view/GestureDetector;->cancel()V

    goto/16 :goto_c

    :cond_b
    iget-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mInLongPress:Z

    if-nez v0, :cond_21

    iget-boolean v0, p0, Lcom/miui/gallery/view/GestureDetector;->mInContextClick:Z

    if-eqz v0, :cond_c

    goto/16 :goto_c

    :cond_c
    iget v0, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusX:F

    sub-float/2addr v0, v9

    iget v1, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusY:F

    sub-float/2addr v1, v10

    iget-boolean v6, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapping:Z

    if-eqz v6, :cond_d

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    invoke-interface {v0, p1}, Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;->onDoubleTapEvent(Landroid/view/MotionEvent;)Z

    move-result p1

    or-int/2addr v3, p1

    goto/16 :goto_c

    :cond_d
    iget-boolean v6, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInTapRegion:Z

    if-eqz v6, :cond_f

    iget v6, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusX:F

    sub-float v6, v9, v6

    float-to-int v6, v6

    iget v7, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusY:F

    sub-float v7, v10, v7

    float-to-int v7, v7

    mul-int/2addr v6, v6

    mul-int/2addr v7, v7

    add-int/2addr v6, v7

    iget v7, p0, Lcom/miui/gallery/view/GestureDetector;->mTouchSlopSquare:I

    if-le v6, v7, :cond_e

    iget-object v7, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    iget-object v8, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    invoke-interface {v7, v8, p1, v0, v1}, Lcom/miui/gallery/view/GestureDetector$OnGestureListener;->onScroll(Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z

    move-result p1

    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusX:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusY:F

    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInTapRegion:Z

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v5}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v4}, Landroid/os/Handler;->removeMessages(I)V

    goto :goto_7

    :cond_e
    move p1, v3

    :goto_7
    iget v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapTouchSlopSquare:I

    if-le v6, v0, :cond_1a

    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInBiggerTapRegion:Z

    goto/16 :goto_a

    :cond_f
    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v2

    const/high16 v4, 0x3f800000    # 1.0f

    cmpl-float v2, v2, v4

    if-gez v2, :cond_10

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v2

    cmpl-float v2, v2, v4

    if-ltz v2, :cond_21

    :cond_10
    iget-object v2, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    iget-object v3, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    invoke-interface {v2, v3, p1, v0, v1}, Lcom/miui/gallery/view/GestureDetector$OnGestureListener;->onScroll(Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z

    move-result v3

    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusX:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusY:F

    goto/16 :goto_c

    :cond_11
    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mStillDown:Z

    invoke-static {p1}, Landroid/view/MotionEvent;->obtain(Landroid/view/MotionEvent;)Landroid/view/MotionEvent;

    move-result-object v0

    iget-boolean v1, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapping:Z

    if-eqz v1, :cond_12

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    invoke-interface {v1, p1}, Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;->onDoubleTapEvent(Landroid/view/MotionEvent;)Z

    move-result p1

    or-int/2addr p1, v3

    goto :goto_9

    :cond_12
    iget-boolean v1, p0, Lcom/miui/gallery/view/GestureDetector;->mInLongPress:Z

    if-eqz v1, :cond_13

    iget-object p1, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {p1, v5}, Landroid/os/Handler;->removeMessages(I)V

    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mInLongPress:Z

    goto :goto_8

    :cond_13
    iget-boolean v1, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInTapRegion:Z

    if-eqz v1, :cond_15

    iget-boolean v1, p0, Lcom/miui/gallery/view/GestureDetector;->mIgnoreNextUpEvent:Z

    if-nez v1, :cond_15

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    invoke-interface {v1, p1}, Lcom/miui/gallery/view/GestureDetector$OnGestureListener;->onSingleTapUp(Landroid/view/MotionEvent;)Z

    move-result v1

    iget-boolean v5, p0, Lcom/miui/gallery/view/GestureDetector;->mDeferConfirmSingleTap:Z

    if-eqz v5, :cond_14

    iget-object v5, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    if-eqz v5, :cond_14

    invoke-interface {v5, p1}, Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;->onSingleTapConfirmed(Landroid/view/MotionEvent;)Z

    :cond_14
    move p1, v1

    goto :goto_9

    :cond_15
    iget-boolean v1, p0, Lcom/miui/gallery/view/GestureDetector;->mIgnoreNextUpEvent:Z

    if-nez v1, :cond_17

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {p1, v3}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v5

    iget v6, p0, Lcom/miui/gallery/view/GestureDetector;->mMaximumFlingVelocity:I

    int-to-float v6, v6

    invoke-virtual {v1, v8, v6}, Landroid/view/VelocityTracker;->computeCurrentVelocity(IF)V

    invoke-virtual {v1, v5}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v6

    invoke-virtual {v1, v5}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v1

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v5

    iget v7, p0, Lcom/miui/gallery/view/GestureDetector;->mMinimumFlingVelocity:I

    int-to-float v7, v7

    cmpl-float v5, v5, v7

    if-gtz v5, :cond_16

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v5

    iget v7, p0, Lcom/miui/gallery/view/GestureDetector;->mMinimumFlingVelocity:I

    int-to-float v7, v7

    cmpl-float v5, v5, v7

    if-lez v5, :cond_17

    :cond_16
    iget-object v5, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    iget-object v7, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    invoke-interface {v5, v7, p1, v1, v6}, Lcom/miui/gallery/view/GestureDetector$OnGestureListener;->onFling(Landroid/view/MotionEvent;Landroid/view/MotionEvent;FF)Z

    move-result p1

    goto :goto_9

    :cond_17
    :goto_8
    move p1, v3

    :goto_9
    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mPreviousUpEvent:Landroid/view/MotionEvent;

    if-eqz v1, :cond_18

    invoke-virtual {v1}, Landroid/view/MotionEvent;->recycle()V

    :cond_18
    iput-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mPreviousUpEvent:Landroid/view/MotionEvent;

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v0, :cond_19

    invoke-virtual {v0}, Landroid/view/VelocityTracker;->recycle()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_19
    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapping:Z

    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mDeferConfirmSingleTap:Z

    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mIgnoreNextUpEvent:Z

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v4}, Landroid/os/Handler;->removeMessages(I)V

    :cond_1a
    :goto_a
    move v3, p1

    goto/16 :goto_c

    :cond_1b
    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    if-eqz v0, :cond_1e

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v0, v5}, Landroid/os/Handler;->hasMessages(I)Z

    move-result v0

    if-eqz v0, :cond_1c

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v1, v5}, Landroid/os/Handler;->removeMessages(I)V

    :cond_1c
    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    if-eqz v1, :cond_1d

    iget-object v6, p0, Lcom/miui/gallery/view/GestureDetector;->mPreviousUpEvent:Landroid/view/MotionEvent;

    if-eqz v6, :cond_1d

    if-eqz v0, :cond_1d

    invoke-direct {p0, v1, v6, p1}, Lcom/miui/gallery/view/GestureDetector;->isConsideredDoubleTap(Landroid/view/MotionEvent;Landroid/view/MotionEvent;Landroid/view/MotionEvent;)Z

    move-result v0

    if-eqz v0, :cond_1d

    iput-boolean v2, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapping:Z

    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    invoke-interface {v0, v1}, Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;->onDoubleTap(Landroid/view/MotionEvent;)Z

    move-result v0

    or-int/2addr v0, v3

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    invoke-interface {v1, p1}, Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;->onDoubleTapEvent(Landroid/view/MotionEvent;)Z

    move-result v1

    or-int/2addr v0, v1

    goto :goto_b

    :cond_1d
    iget-object v0, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    sget v1, Lcom/miui/gallery/view/GestureDetector;->DOUBLE_TAP_TIMEOUT:I

    int-to-long v6, v1

    invoke-virtual {v0, v5, v6, v7}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    :cond_1e
    move v0, v3

    :goto_b
    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusX:F

    iput v9, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusX:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mLastFocusY:F

    iput v10, p0, Lcom/miui/gallery/view/GestureDetector;->mDownFocusY:F

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    if-eqz v1, :cond_1f

    invoke-virtual {v1}, Landroid/view/MotionEvent;->recycle()V

    :cond_1f
    invoke-static {p1}, Landroid/view/MotionEvent;->obtain(Landroid/view/MotionEvent;)Landroid/view/MotionEvent;

    move-result-object v1

    iput-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    iput-boolean v2, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInTapRegion:Z

    iput-boolean v2, p0, Lcom/miui/gallery/view/GestureDetector;->mAlwaysInBiggerTapRegion:Z

    iput-boolean v2, p0, Lcom/miui/gallery/view/GestureDetector;->mStillDown:Z

    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mInLongPress:Z

    iput-boolean v3, p0, Lcom/miui/gallery/view/GestureDetector;->mDeferConfirmSingleTap:Z

    iget-boolean v1, p0, Lcom/miui/gallery/view/GestureDetector;->mIsLongpressEnabled:Z

    if-eqz v1, :cond_20

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    invoke-virtual {v1, v4}, Landroid/os/Handler;->removeMessages(I)V

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    iget-object v3, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    invoke-virtual {v3}, Landroid/view/MotionEvent;->getDownTime()J

    move-result-wide v5

    sget v3, Lcom/miui/gallery/view/GestureDetector;->TAP_TIMEOUT:I

    int-to-long v7, v3

    add-long/2addr v5, v7

    sget v3, Lcom/miui/gallery/view/GestureDetector;->LONGPRESS_TIMEOUT:I

    int-to-long v7, v3

    add-long/2addr v5, v7

    invoke-virtual {v1, v4, v5, v6}, Landroid/os/Handler;->sendEmptyMessageAtTime(IJ)Z

    :cond_20
    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mHandler:Landroid/os/Handler;

    iget-object v3, p0, Lcom/miui/gallery/view/GestureDetector;->mCurrentDownEvent:Landroid/view/MotionEvent;

    invoke-virtual {v3}, Landroid/view/MotionEvent;->getDownTime()J

    move-result-wide v3

    sget v5, Lcom/miui/gallery/view/GestureDetector;->TAP_TIMEOUT:I

    int-to-long v5, v5

    add-long/2addr v3, v5

    invoke-virtual {v1, v2, v3, v4}, Landroid/os/Handler;->sendEmptyMessageAtTime(IJ)Z

    iget-object v1, p0, Lcom/miui/gallery/view/GestureDetector;->mListener:Lcom/miui/gallery/view/GestureDetector$OnGestureListener;

    invoke-interface {v1, p1}, Lcom/miui/gallery/view/GestureDetector$OnGestureListener;->onDown(Landroid/view/MotionEvent;)Z

    move-result p1

    or-int v3, v0, p1

    :cond_21
    :goto_c
    return v3
.end method

.method public setContextClickListener(Lcom/miui/gallery/view/GestureDetector$OnContextClickListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/view/GestureDetector;->mContextClickListener:Lcom/miui/gallery/view/GestureDetector$OnContextClickListener;

    return-void
.end method

.method public setIsDoubleTapEnabled(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/view/GestureDetector;->mIsDoubleTapEnabled:Z

    return-void
.end method

.method public setOnDoubleTapListener(Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/view/GestureDetector;->mDoubleTapListener:Lcom/miui/gallery/view/GestureDetector$OnDoubleTapListener;

    return-void
.end method

.method public setTouchSlop(I)V
    .locals 1

    const/4 v0, 0x1

    if-lt p1, v0, :cond_0

    mul-int/2addr p1, p1

    iput p1, p0, Lcom/miui/gallery/view/GestureDetector;->mTouchSlopSquare:I

    :cond_0
    return-void
.end method
