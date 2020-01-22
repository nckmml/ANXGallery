.class Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;
.super Ljava/lang/Object;
.source "AlbumDbDataSource.java"

# interfaces
.implements Lio/reactivex/FlowableOnSubscribe;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->queryHiddenAlbum()Lio/reactivex/Flowable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lio/reactivex/FlowableOnSubscribe<",
        "Ljava/util/List<",
        "Lcom/miui/gallery/model/Album;",
        ">;>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;->this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public subscribe(Lio/reactivex/FlowableEmitter;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lio/reactivex/FlowableEmitter<",
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/Album;",
            ">;>;)V"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {}, Lcom/miui/gallery/dao/AlbumTableServices;->queryHiddenAlbum()Landroid/database/Cursor;

    move-result-object v0

    if-nez v0, :cond_0

    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "can\'t find datas"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    invoke-interface {p1, v0}, Lio/reactivex/FlowableEmitter;->onError(Ljava/lang/Throwable;)V

    return-void

    :cond_0
    invoke-interface {v0}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object v1, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;->this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

    invoke-static {v1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->access$000(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)Lcom/miui/gallery/loader/CursorConvertCallback;

    move-result-object v1

    invoke-interface {v1, v0}, Lcom/miui/gallery/loader/CursorConvertCallback;->convert(Landroid/database/Cursor;)Ljava/lang/Object;

    move-result-object v1

    invoke-interface {p1, v1}, Lio/reactivex/FlowableEmitter;->onNext(Ljava/lang/Object;)V

    goto :goto_0

    :cond_1
    sget-object v1, Ljava/util/Collections;->EMPTY_LIST:Ljava/util/List;

    invoke-interface {p1, v1}, Lio/reactivex/FlowableEmitter;->onNext(Ljava/lang/Object;)V

    :goto_0
    invoke-interface {v0}, Landroid/database/Cursor;->getNotificationUri()Landroid/net/Uri;

    move-result-object v1

    if-eqz v1, :cond_2

    new-instance v1, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3$1;

    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getWorkHandler()Lcom/android/internal/CompatHandler;

    move-result-object v2

    invoke-direct {v1, p0, v2, p1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3$1;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;Landroid/os/Handler;Lio/reactivex/FlowableEmitter;)V

    iget-object v2, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;->this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

    invoke-interface {v0}, Landroid/database/Cursor;->getNotificationUri()Landroid/net/Uri;

    move-result-object v0

    const/4 v3, 0x1

    invoke-static {v2, v0, v3, v1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->access$100(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;Landroid/net/Uri;ZLandroid/database/ContentObserver;)V

    new-instance v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3$2;

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3$2;-><init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$3;Landroid/database/ContentObserver;)V

    invoke-interface {p1, v0}, Lio/reactivex/FlowableEmitter;->setCancellable(Lio/reactivex/functions/Cancellable;)V

    :cond_2
    return-void
.end method
