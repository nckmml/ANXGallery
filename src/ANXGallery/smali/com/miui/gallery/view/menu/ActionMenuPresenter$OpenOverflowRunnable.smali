.class Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;
.super Ljava/lang/Object;
.source "ActionMenuPresenter.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/view/menu/ActionMenuPresenter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "OpenOverflowRunnable"
.end annotation


# instance fields
.field private mPopup:Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;

.field final synthetic this$0:Lcom/miui/gallery/view/menu/ActionMenuPresenter;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/view/menu/ActionMenuPresenter;Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->this$0:Lcom/miui/gallery/view/menu/ActionMenuPresenter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->mPopup:Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->this$0:Lcom/miui/gallery/view/menu/ActionMenuPresenter;

    iget-object v0, v0, Lcom/miui/gallery/view/menu/ActionMenuPresenter;->mMenu:Lcom/miui/gallery/view/menu/MenuBuilder;

    invoke-virtual {v0}, Lcom/miui/gallery/view/menu/MenuBuilder;->changeMenuMode()V

    iget-object v0, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->this$0:Lcom/miui/gallery/view/menu/ActionMenuPresenter;

    iget-object v0, v0, Lcom/miui/gallery/view/menu/ActionMenuPresenter;->mMenuView:Lcom/miui/gallery/view/menu/MenuView;

    check-cast v0, Landroid/view/View;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Landroid/view/View;->getWindowToken()Landroid/os/IBinder;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->mPopup:Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;

    invoke-interface {v0}, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;->tryShow()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->this$0:Lcom/miui/gallery/view/menu/ActionMenuPresenter;

    iget-object v1, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->mPopup:Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;

    invoke-static {v0, v1}, Lcom/miui/gallery/view/menu/ActionMenuPresenter;->access$602(Lcom/miui/gallery/view/menu/ActionMenuPresenter;Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;)Lcom/miui/gallery/view/menu/ActionMenuPresenter$OverflowMenu;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;->this$0:Lcom/miui/gallery/view/menu/ActionMenuPresenter;

    const/4 v1, 0x0

    invoke-static {v0, v1}, Lcom/miui/gallery/view/menu/ActionMenuPresenter;->access$902(Lcom/miui/gallery/view/menu/ActionMenuPresenter;Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;)Lcom/miui/gallery/view/menu/ActionMenuPresenter$OpenOverflowRunnable;

    return-void
.end method
