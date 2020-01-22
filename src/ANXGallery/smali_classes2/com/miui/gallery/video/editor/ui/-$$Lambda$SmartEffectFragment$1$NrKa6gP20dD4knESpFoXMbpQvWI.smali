.class public final synthetic Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$1$NrKa6gP20dD4knESpFoXMbpQvWI;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$LoadSmartEffectTemplateTask$Callback;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;

.field private final synthetic f$1:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

.field private final synthetic f$2:I


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$1$NrKa6gP20dD4knESpFoXMbpQvWI;->f$0:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$1$NrKa6gP20dD4knESpFoXMbpQvWI;->f$1:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    iput p3, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$1$NrKa6gP20dD4knESpFoXMbpQvWI;->f$2:I

    return-void
.end method


# virtual methods
.method public final onLoadTemplateSuccess(Ljava/util/List;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$1$NrKa6gP20dD4knESpFoXMbpQvWI;->f$0:Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$1$NrKa6gP20dD4knESpFoXMbpQvWI;->f$1:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    iget v2, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$SmartEffectFragment$1$NrKa6gP20dD4knESpFoXMbpQvWI;->f$2:I

    invoke-virtual {v0, v1, v2, p1}, Lcom/miui/gallery/video/editor/ui/SmartEffectFragment$1;->lambda$onCommandSuccess$142$SmartEffectFragment$1(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;ILjava/util/List;)V

    return-void
.end method
