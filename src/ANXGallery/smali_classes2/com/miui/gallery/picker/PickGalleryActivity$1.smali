.class Lcom/miui/gallery/picker/PickGalleryActivity$1;
.super Ljava/lang/Object;
.source "PickGalleryActivity.java"

# interfaces
.implements Lmiui/app/ActionBar$FragmentViewPagerChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/picker/PickGalleryActivity;->setupTabFragments()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/picker/PickGalleryActivity;


# direct methods
.method constructor <init>(Lcom/miui/gallery/picker/PickGalleryActivity;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$1;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onPageScrollStateChanged(I)V
    .locals 0

    return-void
.end method

.method public onPageScrolled(IFZZ)V
    .locals 0

    return-void
.end method

.method public onPageSelected(I)V
    .locals 3

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "PickGalleryActivity"

    const-string v2, "onPageSelected %d"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickGalleryActivity$1;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v0}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$100(Lcom/miui/gallery/picker/PickGalleryActivity;)I

    move-result v0

    if-eq v0, p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/picker/PickGalleryActivity$1;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v0}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$100(Lcom/miui/gallery/picker/PickGalleryActivity;)I

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$200(Lcom/miui/gallery/picker/PickGalleryActivity;I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordPageEnd(Landroid/app/Activity;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickGalleryActivity$1;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v0, p1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$200(Lcom/miui/gallery/picker/PickGalleryActivity;I)Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordPageStart(Landroid/app/Activity;Ljava/lang/String;)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/picker/PickGalleryActivity$1;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v0, p1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$102(Lcom/miui/gallery/picker/PickGalleryActivity;I)I

    return-void
.end method
