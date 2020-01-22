.class Lcom/miui/gallery/ui/HomePageFragment$SnapshotFunction;
.super Ljava/lang/Object;
.source "HomePageFragment.java"

# interfaces
.implements Lio/reactivex/functions/Function;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/HomePageFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "SnapshotFunction"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lio/reactivex/functions/Function<",
        "Lcom/miui/gallery/ui/HomePageFragment$Snapshot;",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/HomePageFragment$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/HomePageFragment$SnapshotFunction;-><init>()V

    return-void
.end method


# virtual methods
.method public apply(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;)Ljava/lang/Boolean;
    .locals 7

    const-string v0, "save snap cost %d"

    const-string v1, "HomePageFragment"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-static {}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getTransaction()Lcom/miui/gallery/dao/base/EntityTransaction;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/dao/base/EntityTransaction;->begin()V

    :try_start_0
    invoke-static {}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    move-result-object v5

    const-class v6, Lcom/miui/gallery/model/HomeMedia;

    invoke-virtual {v5, v6}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->deleteAll(Ljava/lang/Class;)Z

    invoke-static {}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    move-result-object v5

    const-class v6, Lcom/miui/gallery/model/HomeMediaHeader;

    invoke-virtual {v5, v6}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->deleteAll(Ljava/lang/Class;)Z

    invoke-static {}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    move-result-object v5

    invoke-static {p1}, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;->access$1300(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;)Ljava/util/List;

    move-result-object v6

    invoke-virtual {v5, v6}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->insert(Ljava/util/Collection;)I

    invoke-static {}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    move-result-object v5

    invoke-static {p1}, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;->access$1400(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;)Lcom/miui/gallery/model/HomeMediaHeader;

    move-result-object p1

    invoke-virtual {v5, p1}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->insert(Lcom/miui/gallery/dao/base/Entity;)Z

    invoke-virtual {v4}, Lcom/miui/gallery/dao/base/EntityTransaction;->commit()V

    const/4 p1, 0x1

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    invoke-virtual {v4}, Lcom/miui/gallery/dao/base/EntityTransaction;->end()V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    sub-long/2addr v4, v2

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-static {v1, v0, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-object p1

    :catchall_0
    move-exception p1

    goto :goto_0

    :catch_0
    move-exception p1

    :try_start_1
    const-string v5, "save snapshot error"

    invoke-static {v1, v5, p1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    invoke-virtual {v4}, Lcom/miui/gallery/dao/base/EntityTransaction;->end()V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    sub-long/2addr v4, v2

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    invoke-static {v1, v0, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    const/4 p1, 0x0

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1

    :goto_0
    invoke-virtual {v4}, Lcom/miui/gallery/dao/base/EntityTransaction;->end()V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    sub-long/2addr v4, v2

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-static {v1, v0, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    throw p1
.end method

.method public bridge synthetic apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    check-cast p1, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/HomePageFragment$SnapshotFunction;->apply(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method
