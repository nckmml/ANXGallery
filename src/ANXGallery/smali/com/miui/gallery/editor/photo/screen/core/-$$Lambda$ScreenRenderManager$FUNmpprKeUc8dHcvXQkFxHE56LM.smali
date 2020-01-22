.class public final synthetic Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$FUNmpprKeUc8dHcvXQkFxHE56LM;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$FUNmpprKeUc8dHcvXQkFxHE56LM;->f$0:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/-$$Lambda$ScreenRenderManager$FUNmpprKeUc8dHcvXQkFxHE56LM;->f$0:Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;

    check-cast p1, Landroid/graphics/Bitmap;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderManager;->lambda$decodeOrigin$68$ScreenRenderManager(Landroid/graphics/Bitmap;)V

    return-void
.end method
