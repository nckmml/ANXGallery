.class public abstract Lcom/miui/gallery/editor/photo/screen/base/ScreenBaseMenuFragment;
.super Landroid/app/Fragment;
.source "ScreenBaseMenuFragment.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T::",
        "Lcom/miui/gallery/editor/photo/screen/base/IScreenOperation;",
        ">",
        "Landroid/app/Fragment;"
    }
.end annotation


# instance fields
.field protected mScreenOperation:Lcom/miui/gallery/editor/photo/screen/base/IScreenOperation;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/app/Fragment;-><init>()V

    return-void
.end method


# virtual methods
.method protected abstract getScreenOperationClass()Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/lang/Class<",
            "TT;>;"
        }
    .end annotation
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/app/Fragment;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/base/ScreenBaseMenuFragment;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p1

    const-string v0, "fragment_tag_editor"

    invoke-virtual {p1, v0}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorFragment;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/screen/base/ScreenBaseMenuFragment;->getScreenOperationClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/screen/home/ScreenEditorFragment;->getScreenOperation(Ljava/lang/Class;)Lcom/miui/gallery/editor/photo/screen/base/IScreenOperation;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/base/ScreenBaseMenuFragment;->mScreenOperation:Lcom/miui/gallery/editor/photo/screen/base/IScreenOperation;

    return-void
.end method
