.class Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;
.super Ljava/lang/Object;
.source "WaterMarkFragment.java"

# interfaces
.implements Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->initListener()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public synthetic lambda$onCommandSuccess$137$WaterMarkFragment$1(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I[Ljava/lang/String;)V
    .locals 0

    check-cast p1, Lcom/miui/gallery/video/editor/TextStyle;

    invoke-static {p3, p1}, Lcom/miui/gallery/video/editor/manager/WaterMarkManager;->updateDataWithTemplate([Ljava/lang/String;Lcom/miui/gallery/video/editor/TextStyle;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->notifyDateSetChanged(I)V

    return-void
.end method

.method public onCommandFail(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 1

    if-eqz p1, :cond_0

    const/16 v0, 0x14

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->setDownloadState(I)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->notifyDateSetChanged(I)V

    :cond_0
    return-void
.end method

.method public onCommandStart(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 1

    if-eqz p1, :cond_0

    const/16 v0, 0x12

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->setDownloadState(I)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->notifyDateSetChanged(I)V

    :cond_0
    return-void
.end method

.method public onCommandSuccess(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->access$100(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;)Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$LoadWaterMarkTemplateTask;

    move-result-object v0

    new-instance v1, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;

    invoke-direct {v1, p0, p1, p2}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;-><init>(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$LoadWaterMarkTemplateTask;->access$200(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$LoadWaterMarkTemplateTask;Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$LoadWaterMarkTemplateTask$Callback;)V

    return-void
.end method

.method public onTaskCancel(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 2

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->getDownloadState()I

    move-result v0

    if-eqz v0, :cond_0

    const/16 v1, 0x11

    if-eq v0, v1, :cond_0

    const/16 v0, 0x14

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->setDownloadState(I)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;->this$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment;->notifyDateSetChanged(I)V

    :cond_0
    return-void
.end method
