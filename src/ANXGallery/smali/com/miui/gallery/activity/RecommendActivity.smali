.class public Lcom/miui/gallery/activity/RecommendActivity;
.super Lcom/miui/gallery/activity/BaseActivity;
.source "RecommendActivity.java"


# instance fields
.field private mFragment:Lmiui/app/Fragment;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/BaseActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected getFragmentContainerId()I
    .locals 1

    const v0, 0x7f0900d0

    return v0
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/RecommendActivity;->mFragment:Lmiui/app/Fragment;

    invoke-virtual {v0, p1, p2, p3}, Lmiui/app/Fragment;->onActivityResult(IILandroid/content/Intent;)V

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onCreate(Landroid/os/Bundle;)V

    const p1, 0x7f0b015b

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/RecommendActivity;->setContentView(I)V

    const p1, 0x7f100098

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/RecommendActivity;->setTitle(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/RecommendActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p1

    const-string v0, "RootFragment"

    invoke-virtual {p1, v0}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object p1

    check-cast p1, Lmiui/app/Fragment;

    iput-object p1, p0, Lcom/miui/gallery/activity/RecommendActivity;->mFragment:Lmiui/app/Fragment;

    iget-object p1, p0, Lcom/miui/gallery/activity/RecommendActivity;->mFragment:Lmiui/app/Fragment;

    if-nez p1, :cond_0

    new-instance p1, Lcom/miui/gallery/ui/RecommendFragment;

    invoke-direct {p1}, Lcom/miui/gallery/ui/RecommendFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/activity/RecommendActivity;->mFragment:Lmiui/app/Fragment;

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/activity/RecommendActivity;->mFragment:Lmiui/app/Fragment;

    const/4 v1, 0x0

    invoke-virtual {p0, p1, v0, v1, v1}, Lcom/miui/gallery/activity/RecommendActivity;->startFragment(Landroid/app/Fragment;Ljava/lang/String;ZZ)V

    return-void
.end method
