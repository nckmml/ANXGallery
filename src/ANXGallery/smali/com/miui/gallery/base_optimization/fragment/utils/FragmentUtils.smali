.class public Lcom/miui/gallery/base_optimization/fragment/utils/FragmentUtils;
.super Ljava/lang/Object;
.source "FragmentUtils.java"


# direct methods
.method public static startFragment(ILandroid/app/FragmentManager;Landroid/app/Fragment;Lcom/miui/gallery/base_optimization/fragment/FragmentAnimation;ZZLjava/lang/String;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    :cond_0
    if-gtz p0, :cond_1

    return-void

    :cond_1
    invoke-static {p6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_2

    invoke-virtual {p1, p6}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object v0

    if-eqz v0, :cond_2

    return-void

    :cond_2
    invoke-virtual {p1}, Landroid/app/FragmentManager;->beginTransaction()Landroid/app/FragmentTransaction;

    move-result-object p1

    if-eqz p5, :cond_3

    invoke-virtual {p1, p6}, Landroid/app/FragmentTransaction;->addToBackStack(Ljava/lang/String;)Landroid/app/FragmentTransaction;

    :cond_3
    if-eqz p4, :cond_4

    invoke-virtual {p2}, Landroid/app/Fragment;->isAdded()Z

    move-result p4

    if-nez p4, :cond_4

    invoke-virtual {p1, p0, p2, p6}, Landroid/app/FragmentTransaction;->add(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    goto :goto_0

    :cond_4
    invoke-virtual {p1, p0, p2, p6}, Landroid/app/FragmentTransaction;->replace(ILandroid/app/Fragment;Ljava/lang/String;)Landroid/app/FragmentTransaction;

    :goto_0
    if-eqz p3, :cond_5

    invoke-virtual {p3}, Lcom/miui/gallery/base_optimization/fragment/FragmentAnimation;->getEnterAnim()I

    move-result p0

    invoke-virtual {p3}, Lcom/miui/gallery/base_optimization/fragment/FragmentAnimation;->getExitAnim()I

    move-result p2

    const/4 p3, 0x0

    invoke-virtual {p1, p0, p3, p3, p2}, Landroid/app/FragmentTransaction;->setCustomAnimations(IIII)Landroid/app/FragmentTransaction;

    :cond_5
    invoke-virtual {p1}, Landroid/app/FragmentTransaction;->commitAllowingStateLoss()I

    return-void
.end method
