.class Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;
.super Ljava/lang/Object;
.source "AlbumDbDataSource.java"

# interfaces
.implements Ljava/util/concurrent/Callable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;->doChangeAlbumHiddenStatus(ZJ)Lio/reactivex/Flowable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/concurrent/Callable<",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

.field final synthetic val$albumId:J

.field final synthetic val$hide:Z


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;JZ)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;->this$0:Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource;

    iput-wide p2, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;->val$albumId:J

    iput-boolean p4, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;->val$hide:Z

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public call()Ljava/lang/Boolean;
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    iget-wide v0, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;->val$albumId:J

    iget-boolean v2, p0, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;->val$hide:Z

    const/4 v3, 0x0

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/dao/AlbumTableServices;->changeAlbumHiddenStatus(JZZ)Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    return-object v0
.end method

.method public bridge synthetic call()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/model/repository/datesource/album/db/AlbumDbDataSource$5;->call()Ljava/lang/Boolean;

    move-result-object v0

    return-object v0
.end method
