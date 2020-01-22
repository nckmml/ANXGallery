.class public Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;
.super Ljava/lang/Object;
.source "ActivityFragmentSupportDelegate.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/base_optimization/fragment/support/FragmentSupport$IActivityFragmentSupport<",
        "Landroid/app/Fragment;",
        ">;"
    }
.end annotation


# instance fields
.field private mActivityWeakReference:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Landroid/app/Activity;",
            ">;"
        }
    .end annotation
.end field

.field private mContainerId:I

.field private mRootFragmentName:Ljava/lang/String;


# direct methods
.method public constructor <init>(ILandroid/app/Activity;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p2}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mActivityWeakReference:Ljava/lang/ref/WeakReference;

    iput p1, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mContainerId:I

    return-void
.end method

.method private getName(Landroid/app/Fragment;)Ljava/lang/String;
    .locals 0

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Class;->getCanonicalName()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method


# virtual methods
.method public getFragmentManager()Landroid/app/FragmentManager;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mActivityWeakReference:Ljava/lang/ref/WeakReference;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return-object v1

    :cond_0
    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    if-nez v0, :cond_1

    return-object v1

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mActivityWeakReference:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/Activity;

    invoke-virtual {v0}, Landroid/app/Activity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v0

    return-object v0
.end method

.method public loadRootFragment(ILandroid/app/Fragment;)V
    .locals 9

    iput p1, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mContainerId:I

    invoke-direct {p0, p2}, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->getName(Landroid/app/Fragment;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mRootFragmentName:Ljava/lang/String;

    invoke-virtual {p2}, Landroid/app/Fragment;->getArguments()Landroid/os/Bundle;

    move-result-object p1

    if-eqz p1, :cond_0

    iget v0, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mContainerId:I

    const-string v1, "fragment_contail_id"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    :cond_0
    iget v2, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mContainerId:I

    invoke-virtual {p0}, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object v3

    const/4 v5, 0x0

    const/4 v6, 0x1

    const/4 v7, 0x0

    iget-object v8, p0, Lcom/miui/gallery/base_optimization/fragment/support/ActivityFragmentSupportDelegate;->mRootFragmentName:Ljava/lang/String;

    move-object v4, p2

    invoke-static/range {v2 .. v8}, Lcom/miui/gallery/base_optimization/fragment/utils/FragmentUtils;->startFragment(ILandroid/app/FragmentManager;Landroid/app/Fragment;Lcom/miui/gallery/base_optimization/fragment/FragmentAnimation;ZZLjava/lang/String;)V

    return-void
.end method
