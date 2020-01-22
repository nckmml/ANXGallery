.class public abstract Lcom/miui/gallery/ui/album/NoRepeatContentObserver;
.super Landroid/database/ContentObserver;
.source "NoRepeatContentObserver.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;,
        Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;
    }
.end annotation


# instance fields
.field private mContentOnSubscribe:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe<",
            "Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;",
            ">;"
        }
    .end annotation
.end field

.field private mContentSubscribeBean:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;

.field private mDisposable:Lio/reactivex/disposables/Disposable;

.field private mIntervalDuration:J

.field private mTimeUnit:Ljava/util/concurrent/TimeUnit;


# direct methods
.method public constructor <init>(Landroid/os/Handler;)V
    .locals 3

    sget-object v0, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v1, 0x1

    invoke-direct {p0, p1, v1, v2, v0}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;-><init>(Landroid/os/Handler;JLjava/util/concurrent/TimeUnit;)V

    return-void
.end method

.method public constructor <init>(Landroid/os/Handler;JLjava/util/concurrent/TimeUnit;)V
    .locals 0

    invoke-direct {p0, p1}, Landroid/database/ContentObserver;-><init>(Landroid/os/Handler;)V

    iput-wide p2, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mIntervalDuration:J

    iput-object p4, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mTimeUnit:Ljava/util/concurrent/TimeUnit;

    new-instance p1, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;

    const/4 p2, 0x0

    invoke-direct {p1, p0, p2}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;-><init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;Lcom/miui/gallery/ui/album/NoRepeatContentObserver$1;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentOnSubscribe:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;

    new-instance p1, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;

    invoke-direct {p1, p2}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;-><init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver$1;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentSubscribeBean:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;

    iget-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentOnSubscribe:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;

    sget-object p2, Lio/reactivex/BackpressureStrategy;->BUFFER:Lio/reactivex/BackpressureStrategy;

    invoke-static {p1, p2}, Lio/reactivex/Flowable;->create(Lio/reactivex/FlowableOnSubscribe;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;

    move-result-object p1

    iget-wide p2, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mIntervalDuration:J

    iget-object p4, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mTimeUnit:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {p1, p2, p3, p4}, Lio/reactivex/Flowable;->debounce(JLjava/util/concurrent/TimeUnit;)Lio/reactivex/Flowable;

    move-result-object p1

    new-instance p2, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$2;

    invoke-direct {p2, p0}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$2;-><init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;)V

    invoke-virtual {p1, p2}, Lio/reactivex/Flowable;->doOnCancel(Lio/reactivex/functions/Action;)Lio/reactivex/Flowable;

    move-result-object p1

    new-instance p2, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$1;

    invoke-direct {p2, p0}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$1;-><init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;)V

    invoke-virtual {p1, p2}, Lio/reactivex/Flowable;->subscribeWith(Lorg/reactivestreams/Subscriber;)Lorg/reactivestreams/Subscriber;

    move-result-object p1

    check-cast p1, Lio/reactivex/disposables/Disposable;

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mDisposable:Lio/reactivex/disposables/Disposable;

    return-void
.end method

.method static synthetic access$402(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;)Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentOnSubscribe:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;

    return-object p1
.end method

.method static synthetic access$502(Lcom/miui/gallery/ui/album/NoRepeatContentObserver;Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;)Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentSubscribeBean:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;

    return-object p1
.end method


# virtual methods
.method public onChange(ZLandroid/net/Uri;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentSubscribeBean:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0, p2}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;->setUri(Landroid/net/Uri;)V

    iget-object p2, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentSubscribeBean:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;

    invoke-virtual {p2, p1}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;->setSelfChange(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentOnSubscribe:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$ContentOnSubscribe;->getEmitter()Lio/reactivex/FlowableEmitter;

    move-result-object p1

    iget-object p2, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;->mContentSubscribeBean:Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;

    invoke-interface {p1, p2}, Lio/reactivex/FlowableEmitter;->onNext(Ljava/lang/Object;)V

    return-void
.end method

.method public abstract onDataChange(ZLandroid/net/Uri;)V
.end method
