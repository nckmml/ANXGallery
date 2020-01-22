.class public final synthetic Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$LoadWaterMarkTemplateTask$Callback;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;

.field private final synthetic f$1:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

.field private final synthetic f$2:I


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;->f$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;->f$1:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    iput p3, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;->f$2:I

    return-void
.end method


# virtual methods
.method public final onLoadTemplateSuccess([Ljava/lang/String;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;->f$0:Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;->f$1:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    iget v2, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$WaterMarkFragment$1$W2UXHvYwqGM2qqVzpZsMPV5qbuc;->f$2:I

    invoke-virtual {v0, v1, v2, p1}, Lcom/miui/gallery/video/editor/ui/WaterMarkFragment$1;->lambda$onCommandSuccess$137$WaterMarkFragment$1(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I[Ljava/lang/String;)V

    return-void
.end method
