.class public final synthetic Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KKPdtm51BMTIC73wXqaKugCq3K8;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/agreement/core/OnAgreementInvokedListener;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/app/PhotoEditor;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KKPdtm51BMTIC73wXqaKugCq3K8;->f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    return-void
.end method


# virtual methods
.method public final onAgreementInvoked(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/-$$Lambda$PhotoEditor$KKPdtm51BMTIC73wXqaKugCq3K8;->f$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/app/PhotoEditor;->lambda$onPermissionsChecked$102$PhotoEditor(Z)V

    return-void
.end method
