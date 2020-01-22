.class public abstract Lcom/miui/gallery/editor/ui/menu/content/BaseEditContentView;
.super Landroidx/constraintlayout/widget/ConstraintLayout;
.source "BaseEditContentView.java"


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Landroidx/constraintlayout/widget/ConstraintLayout;-><init>(Landroid/content/Context;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/ui/menu/content/BaseEditContentView;->init(Landroid/content/Context;)V

    return-void
.end method

.method private init(Landroid/content/Context;)V
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/editor/ui/menu/content/BaseEditContentView;->inflateContentView(Landroid/content/Context;)V

    return-void
.end method


# virtual methods
.method protected abstract inflateContentView(Landroid/content/Context;)V
.end method
