.class public Lcom/miui/gallery/model/PhotoDetailInfo$FlashState;
.super Ljava/lang/Object;
.source "PhotoDetailInfo.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/model/PhotoDetailInfo;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "FlashState"
.end annotation


# static fields
.field private static FLASH_FIRED_MASK:I = 0x1

.field private static FLASH_FUNCTION_MASK:I = 0x20

.field private static FLASH_MODE_MASK:I = 0x18

.field private static FLASH_RED_EYE_MASK:I = 0x40

.field private static FLASH_RETURN_MASK:I = 0x6


# instance fields
.field private mState:I


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Lcom/miui/gallery/model/PhotoDetailInfo$FlashState;->mState:I

    return-void
.end method


# virtual methods
.method public isFlashFired()Z
    .locals 2

    iget v0, p0, Lcom/miui/gallery/model/PhotoDetailInfo$FlashState;->mState:I

    sget v1, Lcom/miui/gallery/model/PhotoDetailInfo$FlashState;->FLASH_FIRED_MASK:I

    and-int/2addr v0, v1

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method
