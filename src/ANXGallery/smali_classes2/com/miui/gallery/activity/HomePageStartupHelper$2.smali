.class Lcom/miui/gallery/activity/HomePageStartupHelper$2;
.super Ljava/lang/Object;
.source "HomePageStartupHelper.java"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/HomePageStartupHelper;->loadHomePageData()Lcom/miui/gallery/threadpool/Future;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/threadpool/ThreadPool$Job<",
        "Ljava/lang/Void;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/HomePageStartupHelper;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;

    move-result-object p1

    return-object p1
.end method

.method public run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;
    .locals 11

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const/4 p1, 0x0

    const/4 v2, 0x0

    :try_start_0
    invoke-static {}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    move-result-object v3

    const-class v4, Lcom/miui/gallery/model/HomeMediaHeader;

    invoke-virtual {v3, v4, v2, v2}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->query(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/String;)Ljava/util/List;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v4

    if-eqz v4, :cond_1

    invoke-static {}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryLiteEntityManager;

    move-result-object v4

    const-class v5, Lcom/miui/gallery/model/HomeMedia;

    invoke-virtual {v4, v5, v2, v2}, Lcom/miui/gallery/dao/GalleryLiteEntityManager;->query(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/String;)Ljava/util/List;

    move-result-object v4

    invoke-static {v4}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v5

    if-eqz v5, :cond_1

    new-instance v5, Landroid/database/MatrixCursor;

    sget-object v6, Lcom/miui/gallery/model/HomeMedia$Constants;->PROJECTION:[Ljava/lang/String;

    invoke-interface {v4}, Ljava/util/List;->size()I

    move-result v7

    invoke-direct {v5, v6, v7}, Landroid/database/MatrixCursor;-><init>([Ljava/lang/String;I)V

    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/model/HomeMedia;

    invoke-virtual {v6}, Lcom/miui/gallery/model/HomeMedia;->values()[Ljava/lang/Object;

    move-result-object v6

    invoke-virtual {v5, v6}, Landroid/database/MatrixCursor;->addRow([Ljava/lang/Object;)V

    goto :goto_0

    :cond_0
    invoke-interface {v3, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/model/HomeMediaHeader;

    new-instance v4, Landroid/os/Bundle;

    invoke-direct {v4}, Landroid/os/Bundle;-><init>()V

    const-string v6, "extra_timeline_item_count_in_group"

    invoke-static {v3}, Lcom/miui/gallery/model/HomeMediaHeader;->getGroupItemCount(Lcom/miui/gallery/model/HomeMediaHeader;)Ljava/util/ArrayList;

    move-result-object v7

    invoke-virtual {v4, v6, v7}, Landroid/os/Bundle;->putIntegerArrayList(Ljava/lang/String;Ljava/util/ArrayList;)V

    const-string v6, "extra_timeline_group_start_pos"

    invoke-static {v3}, Lcom/miui/gallery/model/HomeMediaHeader;->getGroupItemStartPos(Lcom/miui/gallery/model/HomeMediaHeader;)Ljava/util/ArrayList;

    move-result-object v7

    invoke-virtual {v4, v6, v7}, Landroid/os/Bundle;->putIntegerArrayList(Ljava/lang/String;Ljava/util/ArrayList;)V

    const-string v6, "extra_timeline_group_start_locations"

    invoke-static {v3}, Lcom/miui/gallery/model/HomeMediaHeader;->getGroupLocations(Lcom/miui/gallery/model/HomeMediaHeader;)Ljava/util/ArrayList;

    move-result-object v3

    invoke-virtual {v4, v6, v3}, Landroid/os/Bundle;->putStringArrayList(Ljava/lang/String;Ljava/util/ArrayList;)V

    invoke-virtual {v5, v4}, Landroid/database/MatrixCursor;->setExtras(Landroid/os/Bundle;)V

    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v3, v5}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$102(Lcom/miui/gallery/activity/HomePageStartupHelper;Landroid/database/Cursor;)Landroid/database/Cursor;

    :cond_1
    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v3}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v3

    if-nez v3, :cond_2

    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    iget-object v4, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v4}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$200(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/content/Context;

    move-result-object v4

    invoke-virtual {v4}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v5

    sget-object v6, Lcom/miui/gallery/ui/HomePageFragment;->PHOTOS_LOADER_URI:Landroid/net/Uri;

    sget-object v7, Lcom/miui/gallery/ui/HomePageFragment;->PHOTOS_LOADER_PROJECTION:[Ljava/lang/String;

    const-string v8, "alias_show_in_homepage=1 AND sha1 NOT NULL"

    const/4 v9, 0x0

    const-string v10, "alias_sort_time DESC "

    invoke-virtual/range {v5 .. v10}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v4

    invoke-static {v3, v4}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$102(Lcom/miui/gallery/activity/HomePageStartupHelper;Landroid/database/Cursor;)Landroid/database/Cursor;

    :cond_2
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    sub-long v0, v3, v0

    invoke-static {}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$300()Ljava/lang/String;

    move-result-object v3

    const-string v4, "load preview cost %d"

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-static {v3, v4, v5}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception v3

    invoke-static {}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$300()Ljava/lang/String;

    move-result-object v4

    const-string v5, "error when load data in home page "

    invoke-static {v4, v5, v3}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_1
    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v3}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$400(Lcom/miui/gallery/activity/HomePageStartupHelper;)Z

    move-result v3

    if-eqz v3, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {p1}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$500(Lcom/miui/gallery/activity/HomePageStartupHelper;)V

    return-object v2

    :cond_3
    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v3}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$600(Lcom/miui/gallery/activity/HomePageStartupHelper;)Ljava/lang/Object;

    move-result-object v3

    monitor-enter v3

    :try_start_1
    iget-object v4, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v4}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v4

    if-eqz v4, :cond_4

    iget-object v4, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v4}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v4

    invoke-interface {v4}, Landroid/database/Cursor;->isClosed()Z

    move-result v4

    if-nez v4, :cond_4

    iget-object v4, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v4}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v4

    invoke-interface {v4}, Landroid/database/Cursor;->getExtras()Landroid/os/Bundle;

    move-result-object v4

    const-string v5, "extra_timeline_item_count_in_group"

    invoke-virtual {v4, v5}, Landroid/os/Bundle;->getIntegerArrayList(Ljava/lang/String;)Ljava/util/ArrayList;

    move-result-object v4

    goto :goto_2

    :cond_4
    move-object v4, v2

    :goto_2
    monitor-exit v3
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    iget-object v3, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v3, v4}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$700(Lcom/miui/gallery/activity/HomePageStartupHelper;Ljava/util/ArrayList;)I

    move-result v3

    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    :goto_3
    add-int/lit8 v5, p1, 0x1

    if-ge p1, v3, :cond_8

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {p1}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$600(Lcom/miui/gallery/activity/HomePageStartupHelper;)Ljava/lang/Object;

    move-result-object p1

    monitor-enter p1

    :try_start_2
    iget-object v6, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v6}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v6

    if-eqz v6, :cond_7

    iget-object v6, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v6}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v6

    invoke-interface {v6}, Landroid/database/Cursor;->isClosed()Z

    move-result v6

    if-nez v6, :cond_7

    iget-object v6, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v6}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v6

    invoke-interface {v6}, Landroid/database/Cursor;->moveToNext()Z

    move-result v6

    if-nez v6, :cond_5

    goto :goto_4

    :cond_5
    iget-object v6, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v6}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v6

    const/16 v7, 0xc

    const/4 v8, 0x5

    invoke-static {v6, v7, v8}, Lcom/miui/gallery/adapter/HomePageAdapter;->getMicroPath(Landroid/database/Cursor;II)Ljava/lang/String;

    move-result-object v6

    if-eqz v6, :cond_6

    new-instance v7, Lcom/miui/gallery/activity/HomePageStartupHelper$ImageDisplayItem;

    iget-object v8, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    iget-object v9, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v9}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v9

    const/16 v10, 0x10

    invoke-interface {v9, v10}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v9

    invoke-direct {v7, v8, v6, v9, v10}, Lcom/miui/gallery/activity/HomePageStartupHelper$ImageDisplayItem;-><init>(Lcom/miui/gallery/activity/HomePageStartupHelper;Ljava/lang/String;J)V

    invoke-virtual {v4, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_6
    monitor-exit p1

    move p1, v5

    goto :goto_3

    :cond_7
    :goto_4
    monitor-exit p1

    goto :goto_5

    :catchall_0
    move-exception v0

    monitor-exit p1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0

    :cond_8
    :goto_5
    invoke-static {}, Lcom/miui/gallery/threadpool/ThreadManager;->getMainHandler()Lcom/android/internal/CompatHandler;

    move-result-object p1

    new-instance v3, Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;

    invoke-direct {v3, p0}, Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;-><init>(Lcom/miui/gallery/activity/HomePageStartupHelper$2;)V

    invoke-virtual {p1, v3}, Lcom/android/internal/CompatHandler;->post(Ljava/lang/Runnable;)Z

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    const/4 v3, 0x1

    invoke-static {p1, v3}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$902(Lcom/miui/gallery/activity/HomePageStartupHelper;Z)Z

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {p1, v4}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$1000(Lcom/miui/gallery/activity/HomePageStartupHelper;Ljava/util/ArrayList;)V

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {p1}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$400(Lcom/miui/gallery/activity/HomePageStartupHelper;)Z

    move-result p1

    if-eqz p1, :cond_9

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {p1}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$500(Lcom/miui/gallery/activity/HomePageStartupHelper;)V

    :cond_9
    if-lez v5, :cond_a

    iget-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$1100(Lcom/miui/gallery/activity/HomePageStartupHelper;J)V

    :cond_a
    return-object v2

    :catchall_1
    move-exception p1

    :try_start_3
    monitor-exit v3
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw p1
.end method
