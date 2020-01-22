.class Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate$SingletonHolder;
.super Ljava/lang/Object;
.source "ViewSupportDelegate.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "SingletonHolder"
.end annotation


# static fields
.field private static final INSTANCE:Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;-><init>(Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate$1;)V

    sput-object v0, Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate$SingletonHolder;->INSTANCE:Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;

    return-void
.end method

.method static synthetic access$000()Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;
    .locals 1

    sget-object v0, Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate$SingletonHolder;->INSTANCE:Lcom/miui/gallery/base_optimization/support/ViewSupportDelegate;

    return-object v0
.end method
