.class public Lcom/miui/gallery/scanner/MediaScannerService;
.super Lcom/miui/gallery/service/ServiceBase;
.source "MediaScannerService.java"

# interfaces
.implements Landroidx/lifecycle/DefaultLifecycleObserver;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;,
        Lcom/miui/gallery/scanner/MediaScannerService$CleanupTask;,
        Lcom/miui/gallery/scanner/MediaScannerService$ScanFoldersTask;,
        Lcom/miui/gallery/scanner/MediaScannerService$ScanFilesTask;,
        Lcom/miui/gallery/scanner/MediaScannerService$ScanFileTask;
    }
.end annotation


# instance fields
.field private volatile mCurrentStartId:I

.field private mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

.field private mMediaUnmountedReceiver:Landroid/content/BroadcastReceiver;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/service/ServiceBase;-><init>()V

    new-instance v0, Lcom/miui/gallery/scanner/MediaScannerService$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/scanner/MediaScannerService$1;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;)V

    iput-object v0, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mMediaUnmountedReceiver:Landroid/content/BroadcastReceiver;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/scanner/MediaScannerService;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/scanner/MediaScannerService;->recordStorageUnmounted()V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/scanner/MediaScannerService;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/scanner/MediaScannerService;->recordMainSDCardUnwritable()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/scanner/MediaScannerService;)Lcom/miui/gallery/scanner/ScanTaskManager;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    return-object p0
.end method

.method private recordMainSDCardUnwritable()V
    .locals 3

    const-string v0, "MediaScannerService"

    const-string v1, "Main SDCard is unwritable, skip cleanup"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v0

    const-string v1, "media_scanner"

    const-string v2, "main_sdcard_unwritable"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method private recordStorageUnmounted()V
    .locals 3

    const-string v0, "MediaScannerService"

    const-string v1, "No external storage mounted, skip cleanup"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v0

    const-string v1, "media_scanner"

    const-string v2, "no_external_storage_mounted"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method


# virtual methods
.method protected getNotification()Landroid/app/Notification;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/scanner/MediaScannerService;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/NotificationHelper;->getEmptyNotification(Landroid/content/Context;)Landroid/app/Notification;

    move-result-object v0

    return-object v0
.end method

.method protected getNotificationId()I
    .locals 1

    const/16 v0, 0x8

    return v0
.end method

.method public synthetic lambda$onCreate$49$MediaScannerService()V
    .locals 3

    iget v0, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mCurrentStartId:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "MediaScannerService"

    const-string v2, "onAllTasksExecuted, current startId: %d"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget v0, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mCurrentStartId:I

    invoke-virtual {p0, v0}, Lcom/miui/gallery/scanner/MediaScannerService;->stopSelf(I)V

    return-void
.end method

.method public onCreate()V
    .locals 3

    invoke-super {p0}, Lcom/miui/gallery/service/ServiceBase;->onCreate()V

    new-instance v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerService$9NXTBa_ryERqPF-7RjhaCMN1Vlc;

    invoke-direct {v0, p0}, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerService$9NXTBa_ryERqPF-7RjhaCMN1Vlc;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;)V

    new-instance v1, Lcom/miui/gallery/scanner/ScanTaskManager;

    const/4 v2, 0x1

    invoke-direct {v1, v2, v0}, Lcom/miui/gallery/scanner/ScanTaskManager;-><init>(ILcom/miui/gallery/scanner/ScanTaskManager$OnAllTasksExecutedListener;)V

    iput-object v1, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v0, Landroid/content/IntentFilter;

    invoke-direct {v0}, Landroid/content/IntentFilter;-><init>()V

    const-string v1, "android.intent.action.MEDIA_UNMOUNTED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    const-string v1, "file"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addDataScheme(Ljava/lang/String;)V

    iget-object v1, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mMediaUnmountedReceiver:Landroid/content/BroadcastReceiver;

    invoke-virtual {p0, v1, v0}, Lcom/miui/gallery/scanner/MediaScannerService;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    invoke-static {}, Landroidx/lifecycle/ProcessLifecycleOwner;->get()Landroidx/lifecycle/LifecycleOwner;

    move-result-object v0

    invoke-interface {v0}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroidx/lifecycle/Lifecycle;->addObserver(Landroidx/lifecycle/LifecycleObserver;)V

    const-string v0, "MediaScannerService"

    const-string v1, "onCreate"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public onDestroy()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mMediaUnmountedReceiver:Landroid/content/BroadcastReceiver;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/scanner/MediaScannerService;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    invoke-static {}, Landroidx/lifecycle/ProcessLifecycleOwner;->get()Landroidx/lifecycle/LifecycleOwner;

    move-result-object v0

    invoke-interface {v0}, Landroidx/lifecycle/LifecycleOwner;->getLifecycle()Landroidx/lifecycle/Lifecycle;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroidx/lifecycle/Lifecycle;->removeObserver(Landroidx/lifecycle/LifecycleObserver;)V

    iget-object v0, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    invoke-virtual {v0}, Lcom/miui/gallery/scanner/ScanTaskManager;->shutdown()V

    const-string v0, "MediaScannerService"

    const-string v1, "onDestroy"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public onStartCommand(Landroid/content/Intent;II)I
    .locals 19

    move-object/from16 v9, p0

    move-object/from16 v10, p1

    invoke-super/range {p0 .. p3}, Lcom/miui/gallery/service/ServiceBase;->onStartCommand(Landroid/content/Intent;II)I

    if-eqz v10, :cond_e

    invoke-virtual/range {p1 .. p1}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v0

    if-nez v0, :cond_0

    goto/16 :goto_c

    :cond_0
    invoke-static/range {p3 .. p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v11, "MediaScannerService"

    const-string v1, "onStartCommand: %d"

    invoke-static {v11, v1, v0}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    move/from16 v0, p3

    iput v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mCurrentStartId:I

    const-string v0, "scan_file_path"

    invoke-virtual {v10, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    const-string v0, "scan_files_path"

    invoke-virtual {v10, v0}, Landroid/content/Intent;->hasExtra(Ljava/lang/String;)Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    invoke-virtual {v10, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_1

    invoke-static {}, Lcom/miui/gallery/util/GalleryDataCache;->getInstance()Lcom/miui/gallery/util/GalleryDataCache;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/miui/gallery/util/GalleryDataCache;->remove(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    move-object v7, v0

    goto :goto_0

    :cond_1
    move-object v7, v2

    :goto_0
    const-string v0, "scan_folder_paths"

    invoke-virtual {v10, v0}, Landroid/content/Intent;->hasExtra(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-virtual {v10, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_2

    invoke-static {}, Lcom/miui/gallery/util/GalleryDataCache;->getInstance()Lcom/miui/gallery/util/GalleryDataCache;

    move-result-object v1

    invoke-virtual {v1, v0}, Lcom/miui/gallery/util/GalleryDataCache;->remove(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    move-object v12, v0

    goto :goto_1

    :cond_2
    move-object v12, v2

    :goto_1
    const-string v0, "scan_volume"

    invoke-virtual {v10, v0}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v13

    const-string v0, "cleanup_by_album_ids"

    invoke-virtual {v10, v0}, Landroid/content/Intent;->getStringArrayListExtra(Ljava/lang/String;)Ljava/util/ArrayList;

    move-result-object v14

    const/16 v0, 0x8

    const-string v1, "key_scan_priority"

    invoke-virtual {v10, v1, v0}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v15

    invoke-static {}, Lcom/miui/gallery/util/MiscUtil;->isAppProcessInForeground()Z

    move-result v8

    const/4 v6, 0x0

    const-string v0, "key_force_scan"

    invoke-virtual {v10, v0, v6}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v16

    const-string v0, "key_cancel_running"

    invoke-virtual {v10, v0, v6}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_3

    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    invoke-virtual {v0}, Lcom/miui/gallery/scanner/ScanTaskManager;->cancelAll()V

    :cond_3
    const-string v0, "refresh_ignore_albums"

    invoke-virtual {v10, v0}, Landroid/content/Intent;->hasExtra(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    :try_start_0
    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v1, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;

    const/16 v2, 0x9

    invoke-direct {v1, v9, v8, v2}, Lcom/miui/gallery/scanner/MediaScannerService$RefreshIgnoreAlbumsTask;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;ZI)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/scanner/ScanTaskManager;->submit(Lcom/miui/gallery/scanner/ScanTask;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception v0

    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_4
    :goto_2
    const-string v0, "key_trigger_full_cleanup"

    invoke-virtual {v10, v0, v6}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_5

    :try_start_1
    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v1, Lcom/miui/gallery/scanner/MediaScannerService$CleanupTask;

    const/4 v2, 0x7

    invoke-direct {v1, v9, v8, v2}, Lcom/miui/gallery/scanner/MediaScannerService$CleanupTask;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;ZI)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/scanner/ScanTaskManager;->submit(Lcom/miui/gallery/scanner/ScanTask;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_3

    :catch_1
    move-exception v0

    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_5
    :goto_3
    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_6

    :try_start_2
    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v5, Lcom/miui/gallery/scanner/MediaScannerService$ScanFileTask;
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_3

    move-object v1, v5

    move-object/from16 v2, p0

    move v4, v15

    move-object/from16 p2, v13

    move-object v13, v5

    move v5, v8

    move-object/from16 p3, v14

    move v14, v6

    move/from16 v6, v16

    :try_start_3
    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/scanner/MediaScannerService$ScanFileTask;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;Ljava/lang/String;IZZ)V

    invoke-virtual {v0, v13}, Lcom/miui/gallery/scanner/ScanTaskManager;->submit(Lcom/miui/gallery/scanner/ScanTask;)V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_2

    goto :goto_5

    :catch_2
    move-exception v0

    goto :goto_4

    :catch_3
    move-exception v0

    move-object/from16 p2, v13

    move-object/from16 p3, v14

    move v14, v6

    :goto_4
    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_5

    :cond_6
    move-object/from16 p2, v13

    move-object/from16 p3, v14

    move v14, v6

    :goto_5
    invoke-static {v7}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_7

    :try_start_4
    const-string v0, "key_scan_type"

    invoke-virtual {v10, v0, v14}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v6

    const-string v0, "key_task_start_time"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-virtual {v10, v0, v1, v2}, Landroid/content/Intent;->getLongExtra(Ljava/lang/String;J)J

    move-result-wide v16

    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v13, Lcom/miui/gallery/scanner/MediaScannerService$ScanFilesTask;
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_5

    move-object v1, v13

    move-object/from16 v2, p0

    move-object v3, v7

    move v4, v15

    move v5, v8

    move/from16 v18, v8

    move-wide/from16 v7, v16

    :try_start_5
    invoke-direct/range {v1 .. v8}, Lcom/miui/gallery/scanner/MediaScannerService$ScanFilesTask;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;Ljava/util/List;IZIJ)V

    invoke-virtual {v0, v13}, Lcom/miui/gallery/scanner/ScanTaskManager;->submit(Lcom/miui/gallery/scanner/ScanTask;)V
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_4

    goto :goto_7

    :catch_4
    move-exception v0

    goto :goto_6

    :catch_5
    move-exception v0

    move/from16 v18, v8

    :goto_6
    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_7

    :cond_7
    move/from16 v18, v8

    :goto_7
    invoke-static {v12}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    const/4 v8, 0x3

    if-eqz v0, :cond_9

    :try_start_6
    const-string v0, "key_bulk_notify"

    const/4 v1, 0x1

    invoke-virtual {v10, v0, v1}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v7

    const-string v0, "key_recursive_scan"

    invoke-virtual {v10, v0, v14}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v5

    if-ne v15, v8, :cond_8

    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    invoke-virtual {v0, v8}, Lcom/miui/gallery/scanner/ScanTaskManager;->cancelByPriority(I)V

    :cond_8
    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v10, Lcom/miui/gallery/scanner/MediaScannerService$ScanFoldersTask;

    move-object v1, v10

    move-object/from16 v2, p0

    move-object v3, v12

    move v4, v15

    move/from16 v6, v18

    invoke-direct/range {v1 .. v7}, Lcom/miui/gallery/scanner/MediaScannerService$ScanFoldersTask;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;Ljava/util/List;IZZZ)V

    invoke-virtual {v0, v10}, Lcom/miui/gallery/scanner/ScanTaskManager;->submit(Lcom/miui/gallery/scanner/ScanTask;)V
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_6

    goto :goto_8

    :catch_6
    move-exception v0

    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_9
    :goto_8
    invoke-static/range {p3 .. p3}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_b

    const/4 v0, 0x4

    if-ne v15, v0, :cond_a

    :try_start_7
    iget-object v1, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    invoke-virtual {v1, v0}, Lcom/miui/gallery/scanner/ScanTaskManager;->cancelByPriority(I)V

    :cond_a
    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v1, Lcom/miui/gallery/scanner/MediaScannerService$CleanupTask;
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_8

    move-object/from16 v2, p3

    move/from16 v6, v18

    :try_start_8
    invoke-direct {v1, v9, v6, v15, v2}, Lcom/miui/gallery/scanner/MediaScannerService$CleanupTask;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;ZILjava/util/List;)V

    invoke-virtual {v0, v1}, Lcom/miui/gallery/scanner/ScanTaskManager;->submit(Lcom/miui/gallery/scanner/ScanTask;)V
    :try_end_8
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_8} :catch_7

    goto :goto_a

    :catch_7
    move-exception v0

    goto :goto_9

    :catch_8
    move-exception v0

    move/from16 v6, v18

    :goto_9
    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_a

    :cond_b
    move/from16 v6, v18

    :goto_a
    const-string v0, "external"

    move-object/from16 v1, p2

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_c

    const-string v0, "scan external volume"

    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual/range {p0 .. p0}, Lcom/miui/gallery/scanner/MediaScannerService;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/StorageUtils;->getMountedVolumePaths(Landroid/content/Context;)Ljava/util/List;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_d

    iget-object v0, v9, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    new-instance v7, Lcom/miui/gallery/scanner/MediaScannerService$ScanFoldersTask;

    const/4 v5, 0x1

    move-object v1, v7

    move-object/from16 v2, p0

    move v4, v15

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/scanner/MediaScannerService$ScanFoldersTask;-><init>(Lcom/miui/gallery/scanner/MediaScannerService;Ljava/util/List;IZZ)V

    invoke-virtual {v0, v7}, Lcom/miui/gallery/scanner/ScanTaskManager;->submit(Lcom/miui/gallery/scanner/ScanTask;)V

    goto :goto_b

    :cond_c
    const-string v0, "internal"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_d

    const-string v0, "Received a scan internal volume command, ignore it."

    invoke-static {v11, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    :cond_d
    :goto_b
    return v8

    :cond_e
    :goto_c
    const/4 v0, 0x2

    return v0
.end method

.method public onStop(Landroidx/lifecycle/LifecycleOwner;)V
    .locals 1

    invoke-static {}, Landroidx/lifecycle/ProcessLifecycleOwner;->get()Landroidx/lifecycle/LifecycleOwner;

    move-result-object v0

    if-ne v0, p1, :cond_0

    const-string p1, "MediaScannerService"

    const-string v0, "onProcessStopped"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    iget-object p1, p0, Lcom/miui/gallery/scanner/MediaScannerService;->mManager:Lcom/miui/gallery/scanner/ScanTaskManager;

    invoke-virtual {p1}, Lcom/miui/gallery/scanner/ScanTaskManager;->cancelForegroundTask()V

    :cond_0
    return-void
.end method
