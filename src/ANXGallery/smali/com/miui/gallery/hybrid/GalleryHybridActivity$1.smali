.class Lcom/miui/gallery/hybrid/GalleryHybridActivity$1;
.super Ljava/lang/Object;
.source "GalleryHybridActivity.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/hybrid/GalleryHybridActivity;->configureActionBar()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/hybrid/GalleryHybridActivity;


# direct methods
.method constructor <init>(Lcom/miui/gallery/hybrid/GalleryHybridActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/hybrid/GalleryHybridActivity$1;->this$0:Lcom/miui/gallery/hybrid/GalleryHybridActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/view/View;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/hybrid/GalleryHybridActivity$1;->this$0:Lcom/miui/gallery/hybrid/GalleryHybridActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/hybrid/GalleryHybridActivity;->finish()V

    return-void
.end method
