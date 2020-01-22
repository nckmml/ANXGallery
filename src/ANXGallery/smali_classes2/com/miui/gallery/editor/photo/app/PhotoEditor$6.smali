.class Lcom/miui/gallery/editor/photo/app/PhotoEditor$6;
.super Ljava/lang/Object;
.source "PhotoEditor.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Callbacks;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/app/PhotoEditor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;


# direct methods
.method constructor <init>(Lcom/miui/gallery/editor/photo/app/PhotoEditor;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/PhotoEditor$6;->this$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCancel(Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;)V
    .locals 0

    return-void
.end method

.method public onClick(Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;I)V
    .locals 4

    if-nez p1, :cond_0

    const-string v0, "unknown"

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;->getTag()Ljava/lang/String;

    move-result-object v0

    :goto_0
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "PhotoEditor"

    const-string v3, "confirm dialog from %s, event is %d"

    invoke-static {v2, v3, v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;->getTag()Ljava/lang/String;

    move-result-object v0

    const-string v1, "main_alert_dialog"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    const/4 v1, -0x2

    const/4 v2, -0x1

    if-eqz v0, :cond_2

    if-ne p2, v2, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/PhotoEditor$6;->this$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/app/PhotoEditor;->access$2100(Lcom/miui/gallery/editor/photo/app/PhotoEditor;)Lcom/miui/gallery/editor/photo/app/AbstractNaviFragment$Callbacks;

    move-result-object p1

    invoke-interface {p1}, Lcom/miui/gallery/editor/photo/app/AbstractNaviFragment$Callbacks;->onExport()V

    goto :goto_1

    :cond_1
    if-ne p2, v1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/PhotoEditor$6;->this$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/app/PhotoEditor;->access$2100(Lcom/miui/gallery/editor/photo/app/PhotoEditor;)Lcom/miui/gallery/editor/photo/app/AbstractNaviFragment$Callbacks;

    move-result-object p1

    invoke-interface {p1}, Lcom/miui/gallery/editor/photo/app/AbstractNaviFragment$Callbacks;->onDiscard()V

    goto :goto_1

    :cond_2
    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;->getTag()Ljava/lang/String;

    move-result-object p1

    const-string v0, "menu_alert_dialog"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/PhotoEditor$6;->this$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/app/PhotoEditor;->access$400(Lcom/miui/gallery/editor/photo/app/PhotoEditor;)Landroid/app/FragmentManager;

    move-result-object p1

    const v0, 0x7f090209

    invoke-virtual {p1, v0}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object p1

    instance-of v0, p1, Lcom/miui/gallery/editor/photo/app/MenuFragment;

    if-eqz v0, :cond_4

    if-ne p2, v2, :cond_3

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/PhotoEditor$6;->this$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    invoke-static {p2}, Lcom/miui/gallery/editor/photo/app/PhotoEditor;->access$2400(Lcom/miui/gallery/editor/photo/app/PhotoEditor;)Lcom/miui/gallery/editor/photo/app/MenuFragment$Callbacks;

    move-result-object p2

    check-cast p1, Lcom/miui/gallery/editor/photo/app/MenuFragment;

    invoke-interface {p2, p1}, Lcom/miui/gallery/editor/photo/app/MenuFragment$Callbacks;->onSave(Lcom/miui/gallery/editor/photo/app/MenuFragment;)V

    goto :goto_1

    :cond_3
    if-ne p2, v1, :cond_4

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/PhotoEditor$6;->this$0:Lcom/miui/gallery/editor/photo/app/PhotoEditor;

    invoke-static {p2}, Lcom/miui/gallery/editor/photo/app/PhotoEditor;->access$2400(Lcom/miui/gallery/editor/photo/app/PhotoEditor;)Lcom/miui/gallery/editor/photo/app/MenuFragment$Callbacks;

    move-result-object p2

    check-cast p1, Lcom/miui/gallery/editor/photo/app/MenuFragment;

    invoke-interface {p2, p1}, Lcom/miui/gallery/editor/photo/app/MenuFragment$Callbacks;->onDiscard(Lcom/miui/gallery/editor/photo/app/MenuFragment;)V

    :cond_4
    :goto_1
    return-void
.end method

.method public onDismiss(Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;)V
    .locals 0

    return-void
.end method
