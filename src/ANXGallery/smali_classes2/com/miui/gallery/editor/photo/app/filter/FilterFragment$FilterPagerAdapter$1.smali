.class Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;
.super Ljava/lang/Object;
.source "FilterFragment.java"

# interfaces
.implements Lcom/miui/gallery/widget/recyclerview/SimpleRecyclerView$OnItemClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private mFirstCheckSegmentDone:Z

.field private mWaitFilterData:Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;

.field private mWaitPosition:I

.field final synthetic this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;


# direct methods
.method constructor <init>(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mFirstCheckSegmentDone:Z

    return-void
.end method

.method static synthetic lambda$OnItemClick$115(Lio/reactivex/ObservableEmitter;)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {}, Lcom/xiaomi/skytransfer/SkyTranFilter;->getInstance()Lcom/xiaomi/skytransfer/SkyTranFilter;

    move-result-object v0

    invoke-virtual {v0}, Lcom/xiaomi/skytransfer/SkyTranFilter;->waitSegment()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-interface {p0, v0}, Lio/reactivex/ObservableEmitter;->onNext(Ljava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public OnItemClick(Landroidx/recyclerview/widget/RecyclerView;Landroid/view/View;I)Z
    .locals 3
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "CheckResult"
        }
    .end annotation

    invoke-static {p1, p3}, Lcom/miui/gallery/widget/recyclerview/ScrollLinearLayoutManager;->onItemClick(Landroidx/recyclerview/widget/RecyclerView;I)V

    invoke-virtual {p1}, Landroidx/recyclerview/widget/RecyclerView;->getAdapter()Landroidx/recyclerview/widget/RecyclerView$Adapter;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;

    invoke-virtual {p1, p3}, Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;->getItemData(I)Lcom/miui/gallery/editor/photo/core/common/model/FilterData;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;

    const/4 v0, 0x1

    if-nez p2, :cond_0

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string p2, "FilterFragment"

    const-string p3, "FilterAdapter get filterData null:pos is %d"

    invoke-static {p2, p3, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return v0

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object v1, v1, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-static {v1}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->access$900(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;)Lcom/miui/gallery/editor/photo/core/common/model/FilterData;

    move-result-object v1

    invoke-virtual {p2, v1}, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;->equals(Ljava/lang/Object;)Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_2

    iget-object p3, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object p3, p3, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-static {p3}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->access$1000(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;)Z

    move-result p3

    if-nez p3, :cond_7

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;->isInEditMode()Z

    move-result p3

    if-eqz p3, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;->exitEditMode()V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object p1, p1, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-virtual {p1, v2}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->showTopPanel(Z)V

    goto/16 :goto_0

    :cond_1
    invoke-virtual {p2}, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;->isNone()Z

    move-result p3

    if-nez p3, :cond_7

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;->enterEditMode()V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object p1, p1, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-static {p1}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->access$1100(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;)Landroid/widget/SeekBar;

    move-result-object p1

    iget p2, p2, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;->progress:I

    invoke-virtual {p1, p2}, Landroid/widget/SeekBar;->setProgress(I)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object p1, p1, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->showTopPanel(Z)V

    goto/16 :goto_0

    :cond_2
    invoke-virtual {p2}, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;->isNone()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;->isInEditMode()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;->exitEditMode()V

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object v1, v1, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-virtual {v1, v2}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->showTopPanel(Z)V

    :cond_3
    invoke-virtual {p2}, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;->isSkyTransfer()Z

    move-result v1

    if-eqz v1, :cond_6

    invoke-virtual {p2}, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;->isNone()Z

    move-result v1

    if-nez v1, :cond_6

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object v1, v1, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-static {v1}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->access$1200(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;)Z

    move-result v1

    if-eqz v1, :cond_4

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    invoke-static {v1, p2, p1, p3}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->access$1300(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;I)V

    goto :goto_0

    :cond_4
    iget-boolean v1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mFirstCheckSegmentDone:Z

    if-eqz v1, :cond_5

    iput-boolean v2, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mFirstCheckSegmentDone:Z

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mWaitFilterData:Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;

    iput p3, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mWaitPosition:I

    iget-object p2, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object p2, p2, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-static {p2}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->access$1400(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;)Lcom/miui/gallery/editor/photo/core/RenderFragment;

    move-result-object p2

    check-cast p2, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;->showProgressView()V

    sget-object p3, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA;->INSTANCE:Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$ZAM-lNKWPeWc0CNFDEyrThqMhwA;

    invoke-static {p3}, Lio/reactivex/Observable;->create(Lio/reactivex/ObservableOnSubscribe;)Lio/reactivex/Observable;

    move-result-object p3

    sget-object v1, Landroid/os/AsyncTask;->THREAD_POOL_EXECUTOR:Ljava/util/concurrent/Executor;

    invoke-static {v1}, Lio/reactivex/schedulers/Schedulers;->from(Ljava/util/concurrent/Executor;)Lio/reactivex/Scheduler;

    move-result-object v1

    invoke-virtual {p3, v1}, Lio/reactivex/Observable;->subscribeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object p3

    invoke-static {}, Lio/reactivex/android/schedulers/AndroidSchedulers;->mainThread()Lio/reactivex/Scheduler;

    move-result-object v1

    invoke-virtual {p3, v1}, Lio/reactivex/Observable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object p3

    new-instance v1, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;

    invoke-direct {v1, p0, p2, p1}, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;-><init>(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;)V

    invoke-virtual {p3, v1}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    goto :goto_0

    :cond_5
    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mWaitFilterData:Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;

    iput p3, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mWaitPosition:I

    return v2

    :cond_6
    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    invoke-static {v1, p2, p1, p3}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->access$1500(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;I)V

    :cond_7
    :goto_0
    return v0
.end method

.method public synthetic lambda$OnItemClick$116$FilterFragment$FilterPagerAdapter$1(Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;Ljava/lang/Boolean;)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object v0, v0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->this$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    invoke-static {v0, v1}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;->access$1602(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment;Z)Z

    invoke-virtual {p3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p3

    if-nez p3, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;->hideProgressView()V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->this$1:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;

    iget-object p3, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mWaitFilterData:Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;

    iget v0, p0, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->mWaitPosition:I

    invoke-static {p1, p3, p2, v0}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;->access$1300(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter;Lcom/miui/gallery/editor/photo/core/imports/filter/FilterItem;Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;I)V

    return-void
.end method
