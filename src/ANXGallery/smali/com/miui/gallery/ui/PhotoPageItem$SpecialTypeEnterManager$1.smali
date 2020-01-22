.class Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager$1;
.super Lcom/miui/gallery/listener/SingleClickListener;
.source "PhotoPageItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager$1;->this$1:Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;

    invoke-direct {p0}, Lcom/miui/gallery/listener/SingleClickListener;-><init>()V

    return-void
.end method


# virtual methods
.method protected onSingleClick(Landroid/view/View;)V
    .locals 1

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager$1;->this$1:Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    invoke-static {p1}, Lcom/miui/gallery/ui/PhotoPageItem;->access$3000(Lcom/miui/gallery/ui/PhotoPageItem;)Lcom/miui/gallery/ui/PhotoPageItem$OnSpecialTypeEnterListener;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager$1;->this$1:Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;

    iget-object p1, p1, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    invoke-static {p1}, Lcom/miui/gallery/ui/PhotoPageItem;->access$3000(Lcom/miui/gallery/ui/PhotoPageItem;)Lcom/miui/gallery/ui/PhotoPageItem$OnSpecialTypeEnterListener;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager$1;->this$1:Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-interface {p1, v0}, Lcom/miui/gallery/ui/PhotoPageItem$OnSpecialTypeEnterListener;->onEnterClick(Lcom/miui/gallery/model/BaseDataItem;)V

    :cond_0
    return-void
.end method
