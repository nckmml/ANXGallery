.class Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$2;
.super Ljava/lang/Object;
.source "AlbumDbDataSource.java"

# interfaces
.implements Lio/reactivex/functions/Cancellable;


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

.field final synthetic val$albumContentObserver:Landroid/database/ContentObserver;


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;Landroid/database/ContentObserver;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$2;->this$1:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;

    iput-object p2, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$2;->val$albumContentObserver:Landroid/database/ContentObserver;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public cancel()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$2;->this$1:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;

    iget-object v0, v0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2;->this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

    iget-object v1, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$2$2;->val$albumContentObserver:Landroid/database/ContentObserver;

    invoke-static {v0, v1}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->access$200(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;Landroid/database/ContentObserver;)V

    return-void
.end method
