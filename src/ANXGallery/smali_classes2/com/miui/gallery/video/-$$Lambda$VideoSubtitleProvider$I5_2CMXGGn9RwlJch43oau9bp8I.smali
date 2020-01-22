.class public final synthetic Lcom/miui/gallery/video/-$$Lambda$VideoSubtitleProvider$I5_2CMXGGn9RwlJch43oau9bp8I;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/video/VideoSubtitleProvider;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/video/VideoSubtitleProvider;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/-$$Lambda$VideoSubtitleProvider$I5_2CMXGGn9RwlJch43oau9bp8I;->f$0:Lcom/miui/gallery/video/VideoSubtitleProvider;

    return-void
.end method


# virtual methods
.method public final run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/-$$Lambda$VideoSubtitleProvider$I5_2CMXGGn9RwlJch43oau9bp8I;->f$0:Lcom/miui/gallery/video/VideoSubtitleProvider;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/VideoSubtitleProvider;->lambda$new$15$VideoSubtitleProvider(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/util/List;

    move-result-object p1

    return-object p1
.end method
