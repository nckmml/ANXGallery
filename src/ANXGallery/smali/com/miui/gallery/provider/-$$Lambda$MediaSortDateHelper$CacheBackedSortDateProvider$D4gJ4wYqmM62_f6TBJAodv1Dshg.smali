.class public final synthetic Lcom/miui/gallery/provider/-$$Lambda$MediaSortDateHelper$CacheBackedSortDateProvider$D4gJ4wYqmM62_f6TBJAodv1Dshg;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/cloudcontrol/strategies/AlbumSortDateStrategy;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/cloudcontrol/strategies/AlbumSortDateStrategy;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/provider/-$$Lambda$MediaSortDateHelper$CacheBackedSortDateProvider$D4gJ4wYqmM62_f6TBJAodv1Dshg;->f$0:Lcom/miui/gallery/cloudcontrol/strategies/AlbumSortDateStrategy;

    return-void
.end method


# virtual methods
.method public final run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/provider/-$$Lambda$MediaSortDateHelper$CacheBackedSortDateProvider$D4gJ4wYqmM62_f6TBJAodv1Dshg;->f$0:Lcom/miui/gallery/cloudcontrol/strategies/AlbumSortDateStrategy;

    invoke-static {v0, p1}, Lcom/miui/gallery/provider/MediaSortDateHelper$CacheBackedSortDateProvider;->lambda$dispatchStrategy$28(Lcom/miui/gallery/cloudcontrol/strategies/AlbumSortDateStrategy;Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method
