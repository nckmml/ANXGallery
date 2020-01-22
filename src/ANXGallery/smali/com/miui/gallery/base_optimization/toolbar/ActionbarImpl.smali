.class public Lcom/miui/gallery/base_optimization/toolbar/ActionbarImpl;
.super Ljava/lang/Object;
.source "ActionbarImpl.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/toolbar/IToolbar;


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/base_optimization/toolbar/IToolbar<",
        "Landroid/app/ActionBar;",
        ">;"
    }
.end annotation


# instance fields
.field private mActionbar:Landroid/app/ActionBar;


# direct methods
.method public constructor <init>(Landroid/app/ActionBar;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/base_optimization/toolbar/ActionbarImpl;->mActionbar:Landroid/app/ActionBar;

    return-void
.end method


# virtual methods
.method public setTitle(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/base_optimization/toolbar/ActionbarImpl;->mActionbar:Landroid/app/ActionBar;

    invoke-virtual {v0, p1}, Landroid/app/ActionBar;->setTitle(I)V

    return-void
.end method
