.class public final synthetic Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$3VbZRLGbFM_9zMUbHqZySM7yE_k;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/ObservableOnSubscribe;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

.field private final synthetic f$1:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$3VbZRLGbFM_9zMUbHqZySM7yE_k;->f$0:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$3VbZRLGbFM_9zMUbHqZySM7yE_k;->f$1:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;

    return-void
.end method


# virtual methods
.method public final subscribe(Lio/reactivex/ObservableEmitter;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$3VbZRLGbFM_9zMUbHqZySM7yE_k;->f$0:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$3VbZRLGbFM_9zMUbHqZySM7yE_k;->f$1:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;->lambda$renderBitmap$69$ScreenRenderManager(Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;Lio/reactivex/ObservableEmitter;)V

    return-void
.end method
