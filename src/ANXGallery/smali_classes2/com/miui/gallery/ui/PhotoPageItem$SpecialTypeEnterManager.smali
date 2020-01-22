.class Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;
.super Ljava/lang/Object;
.source "PhotoPageItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageItem;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "SpecialTypeEnterManager"
.end annotation


# instance fields
.field private mCache:Lcom/miui/gallery/util/RecyclerLayoutCache;

.field private mSpecialTypeEnterListener:Lcom/miui/gallery/listener/SingleClickListener;

.field private mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

.field final synthetic this$0:Lcom/miui/gallery/ui/PhotoPageItem;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/ui/PhotoPageItem;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager$1;

    invoke-direct {p1, p0}, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager$1;-><init>(Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterListener:Lcom/miui/gallery/listener/SingleClickListener;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/PhotoPageItem;Lcom/miui/gallery/ui/PhotoPageItem$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;-><init>(Lcom/miui/gallery/ui/PhotoPageItem;)V

    return-void
.end method


# virtual methods
.method onSelected()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/ui/SpecialTypeEnterView;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterListener:Lcom/miui/gallery/listener/SingleClickListener;

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/ui/SpecialTypeEnterView;-><init>(Landroid/view/ViewGroup;Landroid/view/View$OnClickListener;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->update(Z)V

    :cond_0
    return-void
.end method

.method onSelecting()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mCache:Lcom/miui/gallery/util/RecyclerLayoutCache;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/util/RecyclerLayoutCache;->get()Landroid/view/View;

    move-result-object v0

    if-eqz v0, :cond_0

    new-instance v1, Lcom/miui/gallery/ui/SpecialTypeEnterView;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterListener:Lcom/miui/gallery/listener/SingleClickListener;

    invoke-direct {v1, v0, v2}, Lcom/miui/gallery/ui/SpecialTypeEnterView;-><init>(Landroid/view/View;Landroid/view/View$OnClickListener;)V

    iput-object v1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->update(Z)V

    :cond_0
    return-void
.end method

.method onUnSelected(I)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mCache:Lcom/miui/gallery/util/RecyclerLayoutCache;

    if-nez v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    const/4 v1, 0x1

    if-nez v0, :cond_1

    invoke-static {p1}, Ljava/lang/Math;->abs(I)I

    move-result v0

    if-ne v0, v1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mCache:Lcom/miui/gallery/util/RecyclerLayoutCache;

    invoke-virtual {p1}, Lcom/miui/gallery/util/RecyclerLayoutCache;->get()Landroid/view/View;

    move-result-object p1

    if-eqz p1, :cond_2

    new-instance v0, Lcom/miui/gallery/ui/SpecialTypeEnterView;

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterListener:Lcom/miui/gallery/listener/SingleClickListener;

    invoke-direct {v0, v2, p1, v3}, Lcom/miui/gallery/ui/SpecialTypeEnterView;-><init>(Landroid/view/ViewGroup;Landroid/view/View;Landroid/view/View$OnClickListener;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    invoke-virtual {p0, v1}, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->update(Z)V

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-eqz v0, :cond_2

    invoke-static {p1}, Ljava/lang/Math;->abs(I)I

    move-result p1

    if-le p1, v1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mCache:Lcom/miui/gallery/util/RecyclerLayoutCache;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->getView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {p1, v0}, Lcom/miui/gallery/util/RecyclerLayoutCache;->put(Landroid/view/View;)V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    :cond_2
    :goto_0
    return-void
.end method

.method release()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mCache:Lcom/miui/gallery/util/RecyclerLayoutCache;

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->getView()Landroid/view/View;

    move-result-object v0

    invoke-virtual {v1, v0}, Lcom/miui/gallery/util/RecyclerLayoutCache;->put(Landroid/view/View;)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    :cond_0
    return-void
.end method

.method setSpecialTypeEnterViewCache(Lcom/miui/gallery/util/RecyclerLayoutCache;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mCache:Lcom/miui/gallery/util/RecyclerLayoutCache;

    return-void
.end method

.method setVisible(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->setVisible(Z)V

    :cond_0
    return-void
.end method

.method startAnim(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->startEnterAlphaAnim(Z)V

    :cond_0
    return-void
.end method

.method update(Z)V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->isSpecialTypeEditable()Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    invoke-static {v0}, Lcom/miui/gallery/ui/PhotoPageItem;->access$2800(Lcom/miui/gallery/ui/PhotoPageItem;)Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/PhotoPageItem;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/res/Resources;->getConfiguration()Landroid/content/res/Configuration;

    move-result-object v0

    iget v0, v0, Landroid/content/res/Configuration;->orientation:I

    const/4 v1, 0x2

    if-ne v0, v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->isVisible()Z

    move-result v0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v0, v0, Lcom/miui/gallery/ui/PhotoPageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v0}, Lcom/miui/gallery/model/BaseDataItem;->getSpecialTypeFlags()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/util/SpecialTypeMediaUtils;->getSpecialTypeEnterIconId(J)I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v1, v1, Lcom/miui/gallery/ui/PhotoPageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v1}, Lcom/miui/gallery/model/BaseDataItem;->getSpecialTypeFlags()J

    move-result-wide v1

    invoke-static {v1, v2}, Lcom/miui/gallery/util/SpecialTypeMediaUtils;->getSpecialTypeEnterTextId(J)I

    move-result v1

    iget-object v2, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    iget-object v3, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->this$0:Lcom/miui/gallery/ui/PhotoPageItem;

    iget-object v4, v3, Lcom/miui/gallery/ui/PhotoPageItem;->mDataItem:Lcom/miui/gallery/model/BaseDataItem;

    invoke-virtual {v4}, Lcom/miui/gallery/model/BaseDataItem;->isVideo()Z

    move-result v4

    invoke-virtual {v2, v3, v4, v0, v1}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->update(Landroid/view/ViewGroup;ZII)V

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->show(Z)V

    :cond_1
    return-void

    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageItem$SpecialTypeEnterManager;->mSpecialTypeEnterView:Lcom/miui/gallery/ui/SpecialTypeEnterView;

    if-eqz v0, :cond_3

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/SpecialTypeEnterView;->hide(Z)V

    :cond_3
    return-void
.end method
