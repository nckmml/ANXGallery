.class Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$1;
.super Lcom/miui/gallery/ui/album/NoRepeatContentObserver;
.source "AlbumDbDataSource.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;->subscribe(Lio/reactivex/FlowableEmitter;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;

.field final synthetic val$emitter:Lio/reactivex/FlowableEmitter;


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;Landroid/os/Handler;Lio/reactivex/FlowableEmitter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$1;->this$1:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;

    iput-object p3, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$1;->val$emitter:Lio/reactivex/FlowableEmitter;

    invoke-direct {p0, p2}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver;-><init>(Landroid/os/Handler;)V

    return-void
.end method


# virtual methods
.method public onDataChange(ZLandroid/net/Uri;)V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/dao/AlbumTableServices;->queryAlbumsExcludeCamera()Landroid/database/Cursor;

    move-result-object p1

    if-eqz p1, :cond_0

    invoke-interface {p1}, Landroid/database/Cursor;->moveToFirst()Z

    move-result p2

    if-eqz p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$1;->val$emitter:Lio/reactivex/FlowableEmitter;

    iget-object v0, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$1;->this$1:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;

    iget-object v0, v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;->this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

    invoke-static {v0}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->access$000(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;)Lcom/miui/gallery/loader/CursorConvertCallback;

    move-result-object v0

    invoke-interface {v0, p1}, Lcom/miui/gallery/loader/CursorConvertCallback;->convert(Landroid/database/Cursor;)Ljava/lang/Object;

    move-result-object p1

    invoke-interface {p2, p1}, Lio/reactivex/FlowableEmitter;->onNext(Ljava/lang/Object;)V

    :cond_0
    return-void
.end method
