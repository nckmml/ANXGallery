.class public Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;
.super Ljava/lang/Object;
.source "MovieBackgroundDownloadManager.java"


# static fields
.field private static sInstance:Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;


# instance fields
.field private mTemplateResources:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/movie/entity/TemplateResource;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;

    invoke-direct {v0}, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;-><init>()V

    sput-object v0, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;->sInstance:Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getInstance()Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;
    .locals 1

    sget-object v0, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;->sInstance:Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;

    return-object v0
.end method

.method private getTemplateResourceFromNameSync(Ljava/lang/String;)Lcom/miui/gallery/movie/entity/TemplateResource;
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;->mTemplateResources:Ljava/util/List;

    const-string v1, "MovieBackgroundDownloadManager"

    if-nez v0, :cond_2

    :try_start_0
    new-instance v2, Lcom/miui/gallery/movie/net/TemplateResourceRequest;

    invoke-direct {v2}, Lcom/miui/gallery/movie/net/TemplateResourceRequest;-><init>()V

    invoke-virtual {v2}, Lcom/miui/gallery/movie/net/TemplateResourceRequest;->executeSync()[Ljava/lang/Object;

    move-result-object v2

    if-eqz v2, :cond_0

    array-length v3, v2

    if-lez v3, :cond_0

    const/4 v3, 0x0

    aget-object v4, v2, v3

    instance-of v4, v4, Ljava/util/List;

    if-eqz v4, :cond_0

    aget-object v2, v2, v3

    check-cast v2, Ljava/util/List;

    move-object v0, v2

    :cond_0
    const-string v2, "getTemplateList %d "

    if-nez v0, :cond_1

    const/4 v3, -0x1

    goto :goto_0

    :cond_1
    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v3

    :goto_0
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-static {v1, v2, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/miui/gallery/net/base/RequestError; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    const-string v2, "RequestError: getTemplateList"

    invoke-static {v1, v2}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    :cond_2
    :goto_1
    const/4 v2, 0x0

    if-nez v0, :cond_3

    const-string p1, "template resource is null"

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_3

    :cond_3
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_4
    :goto_2
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_5

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/movie/entity/TemplateResource;

    iget-object v4, v3, Lcom/miui/gallery/movie/entity/TemplateResource;->nameKey:Ljava/lang/String;

    invoke-static {v4, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_4

    move-object v2, v3

    goto :goto_2

    :cond_5
    :goto_3
    iput-object v0, p0, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;->mTemplateResources:Ljava/util/List;

    return-object v2
.end method

.method static synthetic lambda$downloadTemplate$0(Ljava/lang/Integer;)Ljava/lang/String;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    invoke-static {p0}, Lcom/miui/gallery/movie/ui/factory/MovieFactory;->getTemplateNameById(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lcom/miui/gallery/movie/ui/factory/MovieFactory;->getParentTemplateName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method


# virtual methods
.method public downloadTemplate(Landroid/content/Context;I)V
    .locals 2

    invoke-static {}, Lcom/miui/gallery/util/BaseNetworkUtils;->isNetworkConnected()Z

    move-result v0

    const-string v1, "MovieBackgroundDownloadManager"

    if-nez v0, :cond_0

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string p2, "download templateId %d no network"

    invoke-static {v1, p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_0
    invoke-static {}, Lcom/miui/gallery/util/BaseNetworkUtils;->isActiveNetworkMetered()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string p2, "download templateId %d in network metered"

    invoke-static {v1, p2, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-void

    :cond_1
    invoke-static {p1}, Lcom/miui/gallery/movie/MovieConfig;->init(Landroid/content/Context;)V

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-static {p1}, Lio/reactivex/Observable;->just(Ljava/lang/Object;)Lio/reactivex/Observable;

    move-result-object p1

    sget-object p2, Landroid/os/AsyncTask;->THREAD_POOL_EXECUTOR:Ljava/util/concurrent/Executor;

    invoke-static {p2}, Lio/reactivex/schedulers/Schedulers;->from(Ljava/util/concurrent/Executor;)Lio/reactivex/Scheduler;

    move-result-object p2

    invoke-virtual {p1, p2}, Lio/reactivex/Observable;->observeOn(Lio/reactivex/Scheduler;)Lio/reactivex/Observable;

    move-result-object p1

    sget-object p2, Lcom/miui/gallery/movie/utils/-$$Lambda$MovieBackgroundDownloadManager$k7LycHZWs-21VROhZxpUkRVToJE;->INSTANCE:Lcom/miui/gallery/movie/utils/-$$Lambda$MovieBackgroundDownloadManager$k7LycHZWs-21VROhZxpUkRVToJE;

    invoke-virtual {p1, p2}, Lio/reactivex/Observable;->map(Lio/reactivex/functions/Function;)Lio/reactivex/Observable;

    move-result-object p1

    new-instance p2, Lcom/miui/gallery/movie/utils/-$$Lambda$MovieBackgroundDownloadManager$bGNvIcYN3Aqzu68UQLZxhg-GJkM;

    invoke-direct {p2, p0}, Lcom/miui/gallery/movie/utils/-$$Lambda$MovieBackgroundDownloadManager$bGNvIcYN3Aqzu68UQLZxhg-GJkM;-><init>(Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;)V

    invoke-virtual {p1, p2}, Lio/reactivex/Observable;->subscribe(Lio/reactivex/functions/Consumer;)Lio/reactivex/disposables/Disposable;

    return-void
.end method

.method public synthetic lambda$downloadTemplate$1$MovieBackgroundDownloadManager(Ljava/lang/String;)V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    const-string v0, "movieAssetsNormal"

    invoke-static {p1, v0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_1

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/factory/TemplateFactory;->getTemplateDir(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/BaseFileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    move v0, v1

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    const-string v2, "MovieBackgroundDownloadManager"

    if-eqz v0, :cond_2

    const-string v0, "template %s is already exist"

    invoke-static {v2, v0, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_2

    :cond_2
    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;->getTemplateResourceFromNameSync(Ljava/lang/String;)Lcom/miui/gallery/movie/entity/TemplateResource;

    move-result-object p1

    if-eqz p1, :cond_3

    iget-object v0, p1, Lcom/miui/gallery/movie/entity/TemplateResource;->nameKey:Ljava/lang/String;

    const-string v3, "start download %s in background"

    invoke-static {v2, v3, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-static {}, Lcom/miui/gallery/movie/utils/MovieDownloadManager;->getInstance()Lcom/miui/gallery/movie/utils/MovieDownloadManager;

    move-result-object v0

    const/4 v2, 0x0

    invoke-virtual {v0, p1, v2, v1}, Lcom/miui/gallery/movie/utils/MovieDownloadManager;->downloadResource(Lcom/miui/gallery/movie/entity/MovieResource;Lcom/miui/gallery/movie/ui/listener/MovieDownloadListener;Z)V

    :cond_3
    :goto_2
    return-void
.end method
