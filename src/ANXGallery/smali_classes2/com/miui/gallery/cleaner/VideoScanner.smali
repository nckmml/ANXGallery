.class public Lcom/miui/gallery/cleaner/VideoScanner;
.super Lcom/miui/gallery/cleaner/BaseScanner;
.source "VideoScanner.java"


# direct methods
.method constructor <init>()V
    .locals 1

    const/4 v0, 0x2

    invoke-direct {p0, v0}, Lcom/miui/gallery/cleaner/BaseScanner;-><init>(I)V

    return-void
.end method


# virtual methods
.method public getSelection()Ljava/lang/String;
    .locals 1

    const-string v0, "serverType = 2 AND alias_hidden = 0 AND localGroupId != -1000 AND (size > 0)"

    return-object v0
.end method

.method protected recordClickScanResultEvent()V
    .locals 3

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iget-object v1, p0, Lcom/miui/gallery/cleaner/VideoScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    const-string v2, "result"

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "cleaner"

    const-string v2, "cleaner_result_video_click"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method
