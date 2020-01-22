.class Lcom/miui/gallery/ui/PhotoPageFragment$ThemeManager;
.super Ljava/lang/Object;
.source "PhotoPageFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "ThemeManager"
.end annotation


# instance fields
.field private mHostView:Lcom/miui/gallery/widget/IMultiThemeView;


# direct methods
.method constructor <init>(Lcom/miui/gallery/widget/IMultiThemeView;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ThemeManager;->mHostView:Lcom/miui/gallery/widget/IMultiThemeView;

    return-void
.end method


# virtual methods
.method public setBackgroundAlpha(F)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ThemeManager;->mHostView:Lcom/miui/gallery/widget/IMultiThemeView;

    if-eqz v0, :cond_0

    invoke-interface {v0, p1}, Lcom/miui/gallery/widget/IMultiThemeView;->setBackgroundAlpha(F)V

    :cond_0
    return-void
.end method

.method public setDarkTheme(ZZ)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ThemeManager;->mHostView:Lcom/miui/gallery/widget/IMultiThemeView;

    if-eqz v0, :cond_3

    if-eqz p2, :cond_1

    sget-object v1, Lcom/miui/gallery/widget/IMultiThemeView$Theme;->DARK:Lcom/miui/gallery/widget/IMultiThemeView$Theme;

    if-eqz p1, :cond_0

    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->FADE_OUT:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    goto :goto_0

    :cond_0
    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->NONE:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    :goto_0
    move-object v2, p1

    new-instance v3, Lmiui/view/animation/CubicEaseOutInterpolator;

    invoke-direct {v3}, Lmiui/view/animation/CubicEaseOutInterpolator;-><init>()V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ThemeManager;->mHostView:Lcom/miui/gallery/widget/IMultiThemeView;

    check-cast p1, Landroid/view/View;

    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const p2, 0x7f0a002d

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p1

    int-to-long v4, p1

    invoke-interface/range {v0 .. v5}, Lcom/miui/gallery/widget/IMultiThemeView;->setTheme(Lcom/miui/gallery/widget/IMultiThemeView$Theme;Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;Landroid/view/animation/Interpolator;J)V

    goto :goto_2

    :cond_1
    sget-object p2, Lcom/miui/gallery/widget/IMultiThemeView$Theme;->DARK:Lcom/miui/gallery/widget/IMultiThemeView$Theme;

    if-eqz p1, :cond_2

    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->FADE_OUT:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    goto :goto_1

    :cond_2
    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->NONE:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    :goto_1
    invoke-interface {v0, p2, p1}, Lcom/miui/gallery/widget/IMultiThemeView;->setTheme(Lcom/miui/gallery/widget/IMultiThemeView$Theme;Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;)V

    :cond_3
    :goto_2
    return-void
.end method

.method public setLightTheme(ZZ)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ThemeManager;->mHostView:Lcom/miui/gallery/widget/IMultiThemeView;

    if-eqz v0, :cond_3

    if-eqz p2, :cond_1

    sget-object v1, Lcom/miui/gallery/widget/IMultiThemeView$Theme;->LIGHT:Lcom/miui/gallery/widget/IMultiThemeView$Theme;

    if-eqz p1, :cond_0

    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->FADE_IN:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    goto :goto_0

    :cond_0
    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->NONE:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    :goto_0
    move-object v2, p1

    new-instance v3, Lmiui/view/animation/CubicEaseOutInterpolator;

    invoke-direct {v3}, Lmiui/view/animation/CubicEaseOutInterpolator;-><init>()V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageFragment$ThemeManager;->mHostView:Lcom/miui/gallery/widget/IMultiThemeView;

    check-cast p1, Landroid/view/View;

    invoke-virtual {p1}, Landroid/view/View;->getContext()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    const p2, 0x7f0a002b

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getInteger(I)I

    move-result p1

    int-to-long v4, p1

    invoke-interface/range {v0 .. v5}, Lcom/miui/gallery/widget/IMultiThemeView;->setTheme(Lcom/miui/gallery/widget/IMultiThemeView$Theme;Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;Landroid/view/animation/Interpolator;J)V

    goto :goto_2

    :cond_1
    sget-object p2, Lcom/miui/gallery/widget/IMultiThemeView$Theme;->LIGHT:Lcom/miui/gallery/widget/IMultiThemeView$Theme;

    if-eqz p1, :cond_2

    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->FADE_IN:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    goto :goto_1

    :cond_2
    sget-object p1, Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;->NONE:Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;

    :goto_1
    invoke-interface {v0, p2, p1}, Lcom/miui/gallery/widget/IMultiThemeView;->setTheme(Lcom/miui/gallery/widget/IMultiThemeView$Theme;Lcom/miui/gallery/widget/IMultiThemeView$ThemeTransition;)V

    :cond_3
    :goto_2
    return-void
.end method
