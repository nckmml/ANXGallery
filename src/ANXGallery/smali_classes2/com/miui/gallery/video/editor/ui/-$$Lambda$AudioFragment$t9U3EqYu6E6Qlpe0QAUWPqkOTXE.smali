.class public final synthetic Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$t9U3EqYu6E6Qlpe0QAUWPqkOTXE;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

.field private final synthetic f$1:Ljava/util/ArrayList;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;Ljava/util/ArrayList;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$t9U3EqYu6E6Qlpe0QAUWPqkOTXE;->f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$t9U3EqYu6E6Qlpe0QAUWPqkOTXE;->f$1:Ljava/util/ArrayList;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$t9U3EqYu6E6Qlpe0QAUWPqkOTXE;->f$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$t9U3EqYu6E6Qlpe0QAUWPqkOTXE;->f$1:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->lambda$refreshData$134$AudioFragment(Ljava/util/ArrayList;)V

    return-void
.end method
