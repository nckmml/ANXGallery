.class public final synthetic Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KWmqrCaeuJPk-jPk0Ru9e7YouwU;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

.field private final synthetic f$1:Lcom/miui/gallery/editor/photo/core/Effect;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/app/PhotoEditor;Lcom/miui/gallery/editor/photo/core/Effect;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KWmqrCaeuJPk-jPk0Ru9e7YouwU;->f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KWmqrCaeuJPk-jPk0Ru9e7YouwU;->f$1:Lcom/miui/gallery/editor/photo/core/Effect;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KWmqrCaeuJPk-jPk0Ru9e7YouwU;->f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KWmqrCaeuJPk-jPk0Ru9e7YouwU;->f$1:Lcom/miui/gallery/editor/photo/core/Effect;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/editor/photo/app/PhotoEditor;->lambda$showSingleEffectDisplay$103$PhotoEditor(Lcom/miui/gallery/editor/photo/core/Effect;)V

    return-void
.end method
