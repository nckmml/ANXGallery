.class public Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;
.super Ljava/lang/Object;
.source "VideoSubtitleProvider.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/video/VideoSubtitleProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "SubtitleItem"
.end annotation


# instance fields
.field private mEndTime:J

.field private mStartTime:J

.field private mText:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getEndTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->mEndTime:J

    return-wide v0
.end method

.method public getStartTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->mStartTime:J

    return-wide v0
.end method

.method public getText()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->mText:Ljava/lang/String;

    return-object v0
.end method

.method public setEndTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->mEndTime:J

    return-void
.end method

.method public setStartTime(J)V
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->mStartTime:J

    return-void
.end method

.method public setText(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/VideoSubtitleProvider$SubtitleItem;->mText:Ljava/lang/String;

    return-void
.end method
