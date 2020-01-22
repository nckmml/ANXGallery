.class public Lcom/nexstreaming/nexeditorsdk/nexEngineView;
.super Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;
.source "nexEngineView.java"

# interfaces
.implements Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView$b;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;
    }
.end annotation


# instance fields
.field private nexThemeViewListener:Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;-><init>(Landroid/content/Context;)V

    invoke-static {}, Lcom/nexstreaming/nexeditorsdk/nexApplicationConfig;->getAspectRatioInScreenMode()F

    move-result p1

    invoke-static {p1}, Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;->setAspectRatio(F)V

    invoke-super {p0, p0}, Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;->setNotify(Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView$b;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    invoke-static {}, Lcom/nexstreaming/nexeditorsdk/nexApplicationConfig;->getAspectRatioInScreenMode()F

    move-result p1

    invoke-static {p1}, Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;->setAspectRatio(F)V

    invoke-super {p0, p0}, Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;->setNotify(Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView$b;)V

    return-void
.end method


# virtual methods
.method public onEventNotify(ILjava/lang/Object;III)V
    .locals 0

    iget-object p2, p0, Lcom/nexstreaming/nexeditorsdk/nexEngineView;->nexThemeViewListener:Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;

    if-eqz p2, :cond_3

    const/4 p5, 0x1

    if-eq p1, p5, :cond_2

    const/4 p5, 0x2

    if-eq p1, p5, :cond_1

    const/4 p3, 0x3

    if-eq p1, p3, :cond_0

    goto :goto_0

    :cond_0
    invoke-interface {p2}, Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;->onEngineViewDestroyed()V

    goto :goto_0

    :cond_1
    invoke-interface {p2, p3, p4}, Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;->onEngineViewSizeChanged(II)V

    goto :goto_0

    :cond_2
    invoke-interface {p2, p3, p4}, Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;->onEngineViewAvailable(II)V

    :cond_3
    :goto_0
    return-void
.end method

.method public setBlackOut(Z)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    invoke-super {p0, p1}, Lcom/nexstreaming/kminternal/nexvideoeditor/NexThemeView;->setBlackOut(Z)V

    return-void
.end method

.method public setListener(Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;)V
    .locals 2

    iput-object p1, p0, Lcom/nexstreaming/nexeditorsdk/nexEngineView;->nexThemeViewListener:Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;

    invoke-virtual {p0}, Lcom/nexstreaming/nexeditorsdk/nexEngineView;->isSurfaceAvailable()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/nexstreaming/nexeditorsdk/nexEngineView;->nexThemeViewListener:Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;

    invoke-virtual {p0}, Lcom/nexstreaming/nexeditorsdk/nexEngineView;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/nexstreaming/nexeditorsdk/nexEngineView;->getHeight()I

    move-result v1

    invoke-interface {p1, v0, v1}, Lcom/nexstreaming/nexeditorsdk/nexEngineView$NexViewListener;->onEngineViewAvailable(II)V

    :cond_0
    return-void
.end method
