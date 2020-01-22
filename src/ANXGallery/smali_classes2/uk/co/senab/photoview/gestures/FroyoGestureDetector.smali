.class public Luk/co/senab/photoview/gestures/FroyoGestureDetector;
.super Luk/co/senab/photoview/gestures/EclairGestureDetector;
.source "FroyoGestureDetector.java"


# annotations
.annotation build Landroid/annotation/TargetApi;
    value = 0x8
.end annotation


# static fields
.field private static DEBUG:Z


# instance fields
.field protected final mDetector:Landroid/view/ScaleGestureDetector;

.field private mMultiPointerDiff:F

.field private mPrimaryPointerId:I

.field private mSecondLastTouchX:F

.field private mSecondLastTouchY:F

.field private mSecondPointerId:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-string v0, "FroyoGestureDetector"

    const/4 v1, 0x3

    invoke-static {v0, v1}, Landroid/util/Log;->isLoggable(Ljava/lang/String;I)Z

    move-result v0

    sput-boolean v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->DEBUG:Z

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0, p1}, Luk/co/senab/photoview/gestures/EclairGestureDetector;-><init>(Landroid/content/Context;)V

    const/4 v0, -0x1

    iput v0, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    iput v0, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    const/high16 v0, 0x41200000    # 10.0f

    iput v0, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mMultiPointerDiff:F

    new-instance v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector$1;

    invoke-direct {v0, p0}, Luk/co/senab/photoview/gestures/FroyoGestureDetector$1;-><init>(Luk/co/senab/photoview/gestures/FroyoGestureDetector;)V

    new-instance v1, Landroid/view/ScaleGestureDetector;

    invoke-direct {v1, p1, v0}, Landroid/view/ScaleGestureDetector;-><init>(Landroid/content/Context;Landroid/view/ScaleGestureDetector$OnScaleGestureListener;)V

    iput-object v1, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mDetector:Landroid/view/ScaleGestureDetector;

    return-void
.end method

.method private calculateDistance(DD)D
    .locals 0

    mul-double/2addr p1, p1

    mul-double/2addr p3, p3

    add-double/2addr p1, p3

    invoke-static {p1, p2}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide p1

    return-wide p1
.end method

.method private checkPointerIndex(Landroid/view/MotionEvent;I)Z
    .locals 1

    const/4 v0, -0x1

    if-le p2, v0, :cond_0

    invoke-virtual {p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result p1

    if-ge p2, p1, :cond_0

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    return p1
.end method

.method private handleDragEvent(Landroid/view/MotionEvent;)Z
    .locals 18

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getActionMasked()I

    move-result v2

    const-string v3, "FroyoGestureDetector"

    const/4 v4, 0x0

    const/4 v5, 0x1

    if-eqz v2, :cond_16

    const/4 v6, 0x0

    if-eq v2, v5, :cond_13

    const/4 v7, -0x1

    const/4 v8, 0x3

    const/4 v9, 0x2

    if-eq v2, v9, :cond_b

    if-eq v2, v8, :cond_9

    const/4 v6, 0x5

    if-eq v2, v6, :cond_8

    const/4 v6, 0x6

    if-eq v2, v6, :cond_0

    goto/16 :goto_8

    :cond_0
    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v6

    iget v10, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    if-ne v6, v10, :cond_4

    iget v10, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    if-eq v10, v7, :cond_1

    iput v10, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    goto :goto_1

    :cond_1
    if-nez v2, :cond_2

    move v2, v5

    goto :goto_0

    :cond_2
    move v2, v4

    :goto_0
    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v2

    iput v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    :goto_1
    iget v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v2

    invoke-direct {v0, v1, v2}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->checkPointerIndex(Landroid/view/MotionEvent;I)Z

    move-result v10

    if-nez v10, :cond_3

    invoke-static {}, Luk/co/senab/photoview/log/LogManager;->getLogger()Luk/co/senab/photoview/log/Logger;

    move-result-object v10

    new-array v11, v8, [Ljava/lang/Object;

    iget v12, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-static {v12}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v12

    aput-object v12, v11, v4

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v11, v5

    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v11, v9

    const-string v2, "new primary id %s, new index %s, count %s"

    invoke-static {v2, v11}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-interface {v10, v3, v2}, Luk/co/senab/photoview/log/Logger;->e(Ljava/lang/String;Ljava/lang/String;)I

    :cond_3
    invoke-virtual/range {p0 .. p1}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->getActiveX(Landroid/view/MotionEvent;)F

    move-result v2

    iput v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchX:F

    invoke-virtual/range {p0 .. p1}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->getActiveY(Landroid/view/MotionEvent;)F

    move-result v2

    iput v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchY:F

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v2, :cond_4

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v2}, Landroid/view/VelocityTracker;->clear()V

    :cond_4
    iget-boolean v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    if-nez v2, :cond_7

    iget v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    iget v10, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    if-eq v2, v10, :cond_5

    if-ne v2, v6, :cond_7

    :cond_5
    iput v7, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v2

    if-le v2, v5, :cond_7

    move v7, v4

    :goto_2
    if-ge v7, v2, :cond_7

    invoke-virtual {v1, v7}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v10

    iget v11, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    if-eq v11, v10, :cond_6

    if-eq v10, v6, :cond_6

    iput v10, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    goto :goto_3

    :cond_6
    add-int/lit8 v7, v7, 0x1

    goto :goto_2

    :cond_7
    :goto_3
    sget-boolean v2, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->DEBUG:Z

    if-eqz v2, :cond_18

    invoke-static {}, Luk/co/senab/photoview/log/LogManager;->getLogger()Luk/co/senab/photoview/log/Logger;

    move-result-object v2

    new-array v6, v8, [Ljava/lang/Object;

    iget v7, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-static {v7}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v7

    aput-object v7, v6, v4

    iget v4, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v6, v5

    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getPointerCount()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    aput-object v1, v6, v9

    const-string v1, "pointer info after ACTION_POINTER_UP, primary %s, second %s, count %s"

    invoke-static {v1, v6}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-interface {v2, v3, v1}, Luk/co/senab/photoview/log/Logger;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_8

    :cond_8
    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v6

    iput v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getX(I)F

    move-result v6

    iput v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondLastTouchX:F

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getY(I)F

    move-result v1

    iput v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondLastTouchY:F

    sget-boolean v1, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->DEBUG:Z

    if-eqz v1, :cond_18

    invoke-static {}, Luk/co/senab/photoview/log/LogManager;->getLogger()Luk/co/senab/photoview/log/Logger;

    move-result-object v1

    new-array v2, v5, [Ljava/lang/Object;

    iget v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v2, v4

    const-string v4, "ACTION_POINTER_DOWN pointer id %s"

    invoke-static {v4, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-interface {v1, v3, v2}, Luk/co/senab/photoview/log/Logger;->d(Ljava/lang/String;Ljava/lang/String;)I

    goto/16 :goto_8

    :cond_9
    iget-object v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v1, :cond_a

    iget-object v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v1}, Landroid/view/VelocityTracker;->recycle()V

    iput-object v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_a
    iput-boolean v4, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    goto/16 :goto_8

    :cond_b
    invoke-virtual/range {p0 .. p1}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->getActiveX(Landroid/view/MotionEvent;)F

    move-result v2

    invoke-virtual/range {p0 .. p1}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->getActiveY(Landroid/view/MotionEvent;)F

    move-result v6

    iget v10, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchX:F

    sub-float v10, v2, v10

    iget v11, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchY:F

    sub-float v11, v6, v11

    iget-boolean v12, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    if-nez v12, :cond_12

    iget v12, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    if-eq v12, v7, :cond_10

    invoke-virtual {v1, v12}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v12

    invoke-direct {v0, v1, v12}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->checkPointerIndex(Landroid/view/MotionEvent;I)Z

    move-result v13

    if-eqz v13, :cond_f

    invoke-virtual {v1, v12}, Landroid/view/MotionEvent;->getX(I)F

    move-result v7

    invoke-virtual {v1, v12}, Landroid/view/MotionEvent;->getY(I)F

    move-result v12

    iget v13, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondLastTouchX:F

    sub-float v13, v7, v13

    iget v14, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondLastTouchY:F

    sub-float v14, v12, v14

    sget-boolean v15, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->DEBUG:Z

    if-eqz v15, :cond_c

    invoke-static {}, Luk/co/senab/photoview/log/LogManager;->getLogger()Luk/co/senab/photoview/log/Logger;

    move-result-object v15

    const/4 v8, 0x4

    new-array v8, v8, [Ljava/lang/Object;

    invoke-static {v10}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v17

    aput-object v17, v8, v4

    invoke-static {v11}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v17

    aput-object v17, v8, v5

    invoke-static {v13}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v17

    aput-object v17, v8, v9

    invoke-static {v14}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v9

    const/16 v16, 0x3

    aput-object v9, v8, v16

    const-string v9, "dx %s, dy %s, secondDx %s. secondDy %s"

    invoke-static {v9, v8}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v8

    invoke-interface {v15, v3, v8}, Luk/co/senab/photoview/log/Logger;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_c
    iget v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mMultiPointerDiff:F

    invoke-static {v10, v13, v3}, Lcom/miui/gallery/util/MiscUtil;->floatNear(FFF)Z

    move-result v3

    if-eqz v3, :cond_e

    iget v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mMultiPointerDiff:F

    invoke-static {v11, v14, v3}, Lcom/miui/gallery/util/MiscUtil;->floatNear(FFF)Z

    move-result v3

    if-eqz v3, :cond_e

    float-to-double v7, v10

    float-to-double v12, v11

    invoke-direct {v0, v7, v8, v12, v13}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->calculateDistance(DD)D

    move-result-wide v7

    iget v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mTouchSlop:F

    float-to-double v12, v3

    cmpl-double v3, v7, v12

    if-ltz v3, :cond_d

    move v3, v5

    goto :goto_4

    :cond_d
    move v3, v4

    :goto_4
    iput-boolean v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    goto :goto_5

    :cond_e
    iput v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchX:F

    iput v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchY:F

    iput v7, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondLastTouchX:F

    iput v12, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondLastTouchY:F

    :goto_5
    move v3, v4

    goto :goto_6

    :cond_f
    iput v7, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mSecondPointerId:I

    :cond_10
    move v3, v5

    :goto_6
    if-eqz v3, :cond_12

    float-to-double v7, v10

    float-to-double v12, v11

    invoke-direct {v0, v7, v8, v12, v13}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->calculateDistance(DD)D

    move-result-wide v7

    iget v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mTouchSlop:F

    float-to-double v12, v3

    cmpl-double v3, v7, v12

    if-ltz v3, :cond_11

    move v4, v5

    :cond_11
    iput-boolean v4, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    :cond_12
    iget-boolean v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    if-eqz v3, :cond_18

    iget-object v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mListener:Luk/co/senab/photoview/gestures/OnGestureListener;

    invoke-interface {v3, v10, v11}, Luk/co/senab/photoview/gestures/OnGestureListener;->onDrag(FF)V

    iput v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchX:F

    iput v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchY:F

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v2, :cond_18

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v2, v1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    goto/16 :goto_8

    :cond_13
    iget-boolean v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    if-eqz v2, :cond_14

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v2, :cond_14

    invoke-virtual/range {p0 .. p1}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->getActiveX(Landroid/view/MotionEvent;)F

    move-result v2

    iput v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchX:F

    invoke-virtual/range {p0 .. p1}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->getActiveY(Landroid/view/MotionEvent;)F

    move-result v2

    iput v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchY:F

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v2, v1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    iget-object v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    const/16 v2, 0x3e8

    invoke-virtual {v1, v2}, Landroid/view/VelocityTracker;->computeCurrentVelocity(I)V

    iget-object v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    iget v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-virtual {v1, v2}, Landroid/view/VelocityTracker;->getXVelocity(I)F

    move-result v1

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    iget v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-virtual {v2, v3}, Landroid/view/VelocityTracker;->getYVelocity(I)F

    move-result v2

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v3

    invoke-static {v2}, Ljava/lang/Math;->abs(F)F

    move-result v7

    invoke-static {v3, v7}, Ljava/lang/Math;->max(FF)F

    move-result v3

    iget v7, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mMinimumVelocity:F

    cmpl-float v3, v3, v7

    if-ltz v3, :cond_14

    iget-object v3, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mListener:Luk/co/senab/photoview/gestures/OnGestureListener;

    iget v7, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchX:F

    iget v8, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchY:F

    neg-float v1, v1

    neg-float v2, v2

    invoke-interface {v3, v7, v8, v1, v2}, Luk/co/senab/photoview/gestures/OnGestureListener;->onFling(FFFF)V

    :cond_14
    iget-object v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v1, :cond_15

    iget-object v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v1}, Landroid/view/VelocityTracker;->recycle()V

    iput-object v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    :cond_15
    iput-boolean v4, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    goto :goto_8

    :cond_16
    invoke-static {}, Landroid/view/VelocityTracker;->obtain()Landroid/view/VelocityTracker;

    move-result-object v2

    iput-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    if-eqz v2, :cond_17

    iget-object v2, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mVelocityTracker:Landroid/view/VelocityTracker;

    invoke-virtual {v2, v1}, Landroid/view/VelocityTracker;->addMovement(Landroid/view/MotionEvent;)V

    goto :goto_7

    :cond_17
    invoke-static {}, Luk/co/senab/photoview/log/LogManager;->getLogger()Luk/co/senab/photoview/log/Logger;

    move-result-object v2

    const-string v6, "Velocity tracker is null"

    invoke-interface {v2, v3, v6}, Luk/co/senab/photoview/log/Logger;->d(Ljava/lang/String;Ljava/lang/String;)I

    :goto_7
    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getActionIndex()I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getPointerId(I)I

    move-result v6

    iput v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getX(I)F

    move-result v6

    iput v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchX:F

    invoke-virtual {v1, v2}, Landroid/view/MotionEvent;->getY(I)F

    move-result v1

    iput v1, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mLastTouchY:F

    iput-boolean v4, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mIsDragging:Z

    sget-boolean v1, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->DEBUG:Z

    if-eqz v1, :cond_18

    invoke-static {}, Luk/co/senab/photoview/log/LogManager;->getLogger()Luk/co/senab/photoview/log/Logger;

    move-result-object v1

    new-array v2, v5, [Ljava/lang/Object;

    iget v6, v0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v2, v4

    const-string v4, "ACTION_DOWN pointer id %s"

    invoke-static {v4, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-interface {v1, v3, v2}, Luk/co/senab/photoview/log/Logger;->d(Ljava/lang/String;Ljava/lang/String;)I

    :cond_18
    :goto_8
    return v5
.end method


# virtual methods
.method getActiveX(Landroid/view/MotionEvent;)F
    .locals 2

    iget v0, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    invoke-direct {p0, p1, v0}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->checkPointerIndex(Landroid/view/MotionEvent;I)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getX(I)F

    move-result p1

    return p1

    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getX()F

    move-result p1

    return p1
.end method

.method getActiveY(Landroid/view/MotionEvent;)F
    .locals 2

    iget v0, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mPrimaryPointerId:I

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->findPointerIndex(I)I

    move-result v0

    invoke-direct {p0, p1, v0}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->checkPointerIndex(Landroid/view/MotionEvent;I)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {p1, v0}, Landroid/view/MotionEvent;->getY(I)F

    move-result p1

    return p1

    :cond_0
    invoke-virtual {p1}, Landroid/view/MotionEvent;->getY()F

    move-result p1

    return p1
.end method

.method public isScaling()Z
    .locals 1

    iget-object v0, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mDetector:Landroid/view/ScaleGestureDetector;

    invoke-virtual {v0}, Landroid/view/ScaleGestureDetector;->isInProgress()Z

    move-result v0

    return v0
.end method

.method public onTouchEvent(Landroid/view/MotionEvent;)Z
    .locals 1

    iget-object v0, p0, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->mDetector:Landroid/view/ScaleGestureDetector;

    invoke-virtual {v0, p1}, Landroid/view/ScaleGestureDetector;->onTouchEvent(Landroid/view/MotionEvent;)Z

    invoke-direct {p0, p1}, Luk/co/senab/photoview/gestures/FroyoGestureDetector;->handleDragEvent(Landroid/view/MotionEvent;)Z

    move-result p1

    return p1
.end method
