.class Lcom/miui/gallery/ui/PhotoPageFragmentBase$1;
.super Ljava/lang/Object;
.source "PhotoPageFragmentBase.java"

# interfaces
.implements Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/PhotoPageFragmentBase;->getPhotoPageInteractionListener()Lcom/miui/gallery/ui/PhotoPageFragmentBase$PhotoPageInteractionListener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPageFragmentBase;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragmentBase$1;->this$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getActionBarHeight()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragmentBase$1;->this$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->isAdded()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragmentBase$1;->this$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragmentBase$1;->this$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->getActionBar()Lmiui/app/ActionBar;

    move-result-object v0

    invoke-virtual {v0}, Lmiui/app/ActionBar;->getHeight()I

    move-result v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getMenuBarHeight()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public notifyDataChanged()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragmentBase$1;->this$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->onContentChanged()V

    return-void
.end method

.method public playVideo(Lcom/miui/gallery/model/BaseDataItem;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragmentBase$1;->this$0:Lcom/miui/gallery/ui/PhotoPageFragmentBase;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/PhotoPageFragmentBase;->onPlayVideo(Lcom/miui/gallery/model/BaseDataItem;)V

    return-void
.end method
