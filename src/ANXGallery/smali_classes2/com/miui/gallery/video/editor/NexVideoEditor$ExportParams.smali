.class Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;
.super Ljava/lang/Object;
.source "NexVideoEditor.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/video/editor/NexVideoEditor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "ExportParams"
.end annotation


# instance fields
.field mBitrate:I

.field mCodecValue:I

.field mFps:I

.field mHeight:I

.field mIsValid:Z

.field mLevelValue:I

.field mProfileValue:I

.field mWidth:I


# direct methods
.method constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;->mProfileValue:I

    iput v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;->mBitrate:I

    iput v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;->mLevelValue:I

    iput v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;->mCodecValue:I

    iput v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;->mFps:I

    iput v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;->mWidth:I

    iput v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$ExportParams;->mHeight:I

    return-void
.end method
