.class public Lcom/miui/gallery/cleaner/ScanResult$Builder;
.super Ljava/lang/Object;
.source "ScanResult.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cleaner/ScanResult;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field private mCount:I

.field private mCountText:I

.field private mImages:[Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

.field private mOnClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

.field private mSize:J

.field private mType:I


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mType:I

    const v0, 0x7f0e0041

    iput v0, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mCountText:I

    return-void
.end method


# virtual methods
.method public build()Lcom/miui/gallery/cleaner/ScanResult;
    .locals 6

    new-instance v0, Lcom/miui/gallery/cleaner/ScanResult;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;-><init>(Lcom/miui/gallery/cleaner/ScanResult$1;)V

    iget v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mType:I

    const/4 v2, -0x1

    if-eq v1, v2, :cond_9

    if-eqz v1, :cond_4

    const/4 v2, 0x1

    if-eq v1, v2, :cond_3

    const/4 v2, 0x2

    if-eq v1, v2, :cond_2

    const/4 v2, 0x3

    if-eq v1, v2, :cond_1

    const/4 v2, 0x4

    if-eq v1, v2, :cond_0

    goto :goto_0

    :cond_0
    const v1, 0x7f1002f8

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$102(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f1002f7

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$202(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f1002f6

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$302(Lcom/miui/gallery/cleaner/ScanResult;I)I

    goto :goto_0

    :cond_1
    const v1, 0x7f100306

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$102(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f100305

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$202(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f100304

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$302(Lcom/miui/gallery/cleaner/ScanResult;I)I

    goto :goto_0

    :cond_2
    const v1, 0x7f100311

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$102(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f10030f

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$202(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f10030e

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$302(Lcom/miui/gallery/cleaner/ScanResult;I)I

    goto :goto_0

    :cond_3
    const v1, 0x7f100303

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$102(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f100302

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$202(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f100301

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$302(Lcom/miui/gallery/cleaner/ScanResult;I)I

    goto :goto_0

    :cond_4
    const v1, 0x7f10030a

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$102(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f100308

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$202(Lcom/miui/gallery/cleaner/ScanResult;I)I

    const v1, 0x7f100307

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$302(Lcom/miui/gallery/cleaner/ScanResult;I)I

    :goto_0
    invoke-static {v0}, Lcom/miui/gallery/cleaner/ScanResult;->access$100(Lcom/miui/gallery/cleaner/ScanResult;)I

    move-result v1

    if-eqz v1, :cond_8

    invoke-static {v0}, Lcom/miui/gallery/cleaner/ScanResult;->access$300(Lcom/miui/gallery/cleaner/ScanResult;)I

    move-result v1

    if-eqz v1, :cond_7

    iget-object v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mOnClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

    if-eqz v1, :cond_6

    iget v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mType:I

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$402(Lcom/miui/gallery/cleaner/ScanResult;I)I

    iget-wide v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mSize:J

    const-wide/16 v3, 0x0

    cmp-long v5, v1, v3

    if-gez v5, :cond_5

    move-wide v1, v3

    :cond_5
    invoke-static {v0, v1, v2}, Lcom/miui/gallery/cleaner/ScanResult;->access$502(Lcom/miui/gallery/cleaner/ScanResult;J)J

    iget-object v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mImages:[Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$602(Lcom/miui/gallery/cleaner/ScanResult;[Lcom/miui/gallery/cleaner/ScanResult$ResultImage;)[Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

    iget v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mCount:I

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$702(Lcom/miui/gallery/cleaner/ScanResult;I)I

    iget v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mCountText:I

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$802(Lcom/miui/gallery/cleaner/ScanResult;I)I

    iget-object v1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mOnClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

    invoke-static {v0, v1}, Lcom/miui/gallery/cleaner/ScanResult;->access$902(Lcom/miui/gallery/cleaner/ScanResult;Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;)Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

    return-object v0

    :cond_6
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "the OnScanResultClickListener must not be null"

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_7
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "the action must not be empty."

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_8
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "the title must not be empty."

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_9
    new-instance v0, Ljava/lang/RuntimeException;

    const-string v1, "the type must set."

    invoke-direct {v0, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setCount(I)Lcom/miui/gallery/cleaner/ScanResult$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mCount:I

    return-object p0
.end method

.method public setImages([Lcom/miui/gallery/cleaner/ScanResult$ResultImage;)Lcom/miui/gallery/cleaner/ScanResult$Builder;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mImages:[Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

    return-object p0
.end method

.method public setOnScanResultClickListener(Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;)Lcom/miui/gallery/cleaner/ScanResult$Builder;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mOnClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

    return-object p0
.end method

.method public setSize(J)Lcom/miui/gallery/cleaner/ScanResult$Builder;
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mSize:J

    return-object p0
.end method

.method public setType(I)Lcom/miui/gallery/cleaner/ScanResult$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/cleaner/ScanResult$Builder;->mType:I

    return-object p0
.end method
