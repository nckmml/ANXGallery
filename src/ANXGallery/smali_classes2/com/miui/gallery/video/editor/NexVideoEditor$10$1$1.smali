.class Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;
.super Ljava/lang/Object;
.source "NexVideoEditor.java"

# interfaces
.implements Lcom/miui/gallery/video/editor/VideoEditor$EnocdeStateInterface;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->run()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onEncodeEnd(ZII)V
    .locals 2

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->this$1:Lcom/miui/gallery/video/editor/NexVideoEditor$10;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10;->this$0:Lcom/miui/gallery/video/editor/NexVideoEditor;

    const/4 v1, 0x1

    invoke-static {v0, v1}, Lcom/miui/gallery/video/editor/NexVideoEditor;->access$2302(Lcom/miui/gallery/video/editor/NexVideoEditor;Z)Z

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->this$1:Lcom/miui/gallery/video/editor/NexVideoEditor$10;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10;->this$0:Lcom/miui/gallery/video/editor/NexVideoEditor;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/NexVideoEditor;->access$2200(Lcom/miui/gallery/video/editor/NexVideoEditor;)Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->this$1:Lcom/miui/gallery/video/editor/NexVideoEditor$10;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10;->this$0:Lcom/miui/gallery/video/editor/NexVideoEditor;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/NexVideoEditor;->access$2200(Lcom/miui/gallery/video/editor/NexVideoEditor;)Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;

    move-result-object v0

    invoke-interface {v0, p1, p2, p3}, Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;->onEncodeEnd(ZII)V

    :cond_1
    return-void
.end method

.method public onEncodeProgress(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->this$1:Lcom/miui/gallery/video/editor/NexVideoEditor$10;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10;->this$0:Lcom/miui/gallery/video/editor/NexVideoEditor;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/NexVideoEditor;->access$2200(Lcom/miui/gallery/video/editor/NexVideoEditor;)Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->this$1:Lcom/miui/gallery/video/editor/NexVideoEditor$10;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10;->this$0:Lcom/miui/gallery/video/editor/NexVideoEditor;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/NexVideoEditor;->access$2200(Lcom/miui/gallery/video/editor/NexVideoEditor;)Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;

    move-result-object v0

    invoke-interface {v0, p1}, Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;->onEncodeProgress(I)V

    :cond_0
    return-void
.end method

.method public onEncodeStart()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->this$1:Lcom/miui/gallery/video/editor/NexVideoEditor$10;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10;->this$0:Lcom/miui/gallery/video/editor/NexVideoEditor;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/NexVideoEditor;->access$2200(Lcom/miui/gallery/video/editor/NexVideoEditor;)Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1$1;->this$2:Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10$1;->this$1:Lcom/miui/gallery/video/editor/NexVideoEditor$10;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/NexVideoEditor$10;->this$0:Lcom/miui/gallery/video/editor/NexVideoEditor;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/NexVideoEditor;->access$2200(Lcom/miui/gallery/video/editor/NexVideoEditor;)Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;

    move-result-object v0

    invoke-interface {v0}, Lcom/miui/gallery/video/editor/VideoEditor$TrimStateInterface;->onEncodeStart()V

    :cond_0
    return-void
.end method
