.class public Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;
.super Ljava/lang/Object;
.source "ViewSupportDelegate.java"

# interfaces
.implements Lcom/miui/gallery/base_optimization/support/IViewSupport;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate$SingletonHolder;
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate$SingletonHolder;->access$000()Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method public setText(Landroid/widget/TextView;Ljava/lang/String;)V
    .locals 0

    if-nez p1, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method
