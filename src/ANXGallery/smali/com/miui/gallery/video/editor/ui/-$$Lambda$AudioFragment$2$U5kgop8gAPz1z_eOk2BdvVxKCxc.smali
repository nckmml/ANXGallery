.class public final synthetic Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$U5kgop8gAPz1z_eOk2BdvVxKCxc;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment$2;

.field private final synthetic f$1:Lcom/miui/gallery/net/base/ErrorCode;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/video/editor/ui/AudioFragment$2;Lcom/miui/gallery/net/base/ErrorCode;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$U5kgop8gAPz1z_eOk2BdvVxKCxc;->f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment$2;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$U5kgop8gAPz1z_eOk2BdvVxKCxc;->f$1:Lcom/miui/gallery/net/base/ErrorCode;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$U5kgop8gAPz1z_eOk2BdvVxKCxc;->f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment$2;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$U5kgop8gAPz1z_eOk2BdvVxKCxc;->f$1:Lcom/miui/gallery/net/base/ErrorCode;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->lambda$onResponseError$133$AudioFragment$2(Lcom/miui/gallery/net/base/ErrorCode;)V

    return-void
.end method
