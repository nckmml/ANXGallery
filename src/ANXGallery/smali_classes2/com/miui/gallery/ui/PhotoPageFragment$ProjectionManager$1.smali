.class Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager$1;
.super Ljava/lang/Object;
.source "PhotoPageFragment.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->onConnectStatusChanged(I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;

.field final synthetic val$connectStatus:I


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;I)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager$1;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;

    iput p2, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager$1;->val$connectStatus:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager$1;->this$1:Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;

    iget v1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager$1;->val$connectStatus:I

    invoke-virtual {v0, v1}, Lcom/miui/gallery/ui/PhotoPageFragment$ProjectionManager;->refreshProjectState(I)V

    return-void
.end method
