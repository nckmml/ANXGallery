.class Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$3;
.super Ljava/lang/Object;
.source "PhotoLocalDataSource.java"

# interfaces
.implements Ljava/util/concurrent/Callable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;->queryAlbumNameByAlbumId(Ljava/lang/Long;)Lio/reactivex/Flowable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/concurrent/Callable<",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;

.field final synthetic val$albumId:Ljava/lang/Long;


# direct methods
.method constructor <init>(Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;Ljava/lang/Long;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$3;->this$0:Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource;

    iput-object p2, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$3;->val$albumId:Ljava/lang/Long;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic call()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$3;->call()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public call()Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/model/repository/datesource/photo/PhotoLocalDataSource$3;->val$albumId:Ljava/lang/Long;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v1

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/provider/CloudUtils;->queryAlbumNameByAlbumId(Landroid/content/Context;J)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
