.class Lcom/miui/gallery/ui/PhotoPagerHelper$11;
.super Ljava/lang/Object;
.source "PhotoPagerHelper.java"

# interfaces
.implements Lcom/miui/gallery/ui/PhotoPageItem$OnSpecialTypeEnterListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPagerHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPagerHelper;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPagerHelper;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPagerHelper$11;->this$0:Lcom/miui/gallery/ui/PhotoPagerHelper;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onEnterClick(Lcom/miui/gallery/model/BaseDataItem;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPagerHelper$11;->this$0:Lcom/miui/gallery/ui/PhotoPagerHelper;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPagerHelper;->access$700(Lcom/miui/gallery/ui/PhotoPagerHelper;)Lcom/miui/gallery/ui/PhotoPagerHelper$OnSpecialTypeEnterListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPagerHelper$11;->this$0:Lcom/miui/gallery/ui/PhotoPagerHelper;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPagerHelper;->access$700(Lcom/miui/gallery/ui/PhotoPagerHelper;)Lcom/miui/gallery/ui/PhotoPagerHelper$OnSpecialTypeEnterListener;

    move-result-object v0

    invoke-interface {v0, p1}, Lcom/miui/gallery/ui/PhotoPagerHelper$OnSpecialTypeEnterListener;->onEnterClick(Lcom/miui/gallery/model/BaseDataItem;)V

    :cond_0
    return-void
.end method
