.class public final synthetic Lcom/miui/gallery/movie/utils/-$$Lambda$MovieBackgroundDownloadManager$bGNvIcYN3Aqzu68UQLZxhg-GJkM;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/movie/utils/-$$Lambda$MovieBackgroundDownloadManager$bGNvIcYN3Aqzu68UQLZxhg-GJkM;->f$0:Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/movie/utils/-$$Lambda$MovieBackgroundDownloadManager$bGNvIcYN3Aqzu68UQLZxhg-GJkM;->f$0:Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;

    check-cast p1, Ljava/lang/String;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/movie/utils/MovieBackgroundDownloadManager;->lambda$downloadTemplate$1$MovieBackgroundDownloadManager(Ljava/lang/String;)V

    return-void
.end method
