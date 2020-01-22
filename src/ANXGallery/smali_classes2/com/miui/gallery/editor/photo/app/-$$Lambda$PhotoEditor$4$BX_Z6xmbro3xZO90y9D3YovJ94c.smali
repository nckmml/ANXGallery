.class public final synthetic Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$4$BX_Z6xmbro3xZO90y9D3YovJ94c;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$OnOperationSelectedListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor$4;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/app/PhotoEditor$4;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$4$BX_Z6xmbro3xZO90y9D3YovJ94c;->f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor$4;

    return-void
.end method


# virtual methods
.method public final onOperationSelected(I)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$4$BX_Z6xmbro3xZO90y9D3YovJ94c;->f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor$4;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/app/PhotoEditor$4;->lambda$onExport$105$PhotoEditor$4(I)Z

    move-result p1

    return p1
.end method
