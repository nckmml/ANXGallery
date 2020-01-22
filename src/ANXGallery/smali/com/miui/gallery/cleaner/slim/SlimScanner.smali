.class public Lcom/miui/gallery/cleaner/slim/SlimScanner;
.super Lcom/miui/gallery/cleaner/BaseScanner;
.source "SlimScanner.java"

# interfaces
.implements Lcom/miui/gallery/cleaner/slim/SlimController$SpaceSlimObserver;


# static fields
.field public static final SLIM_SCAN_ORDER:Ljava/lang/String;

.field private static final SLIM_SCAN_SELECTION:Ljava/lang/String;

.field public static final SYNCED_SLIM_SCAN_SELECTION:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    const/4 v0, 0x1

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    const-string v2, "alias_sort_time"

    aput-object v2, v0, v1

    const-string v1, "%s DESC"

    invoke-static {v1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->SLIM_SCAN_ORDER:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "serverType = 1 AND mimeType != \'image/gif\' AND localFile NOT NULL AND localFile != \'\' AND localGroupId != "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-wide/16 v1, 0x3e8

    invoke-static {v1, v2}, Lcom/miui/gallery/cloud/CloudTableUtils;->getCloudIdForGroupWithoutRecord(J)J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, " AND "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "localGroupId"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " != "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-wide/16 v1, 0x3e9

    invoke-static {v1, v2}, Lcom/miui/gallery/cloud/CloudTableUtils;->getCloudIdForGroupWithoutRecord(J)J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->SLIM_SCAN_SELECTION:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "alias_sync_state = 0 AND "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v1, Lcom/miui/gallery/cleaner/slim/SlimScanner;->SLIM_SCAN_SELECTION:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->SYNCED_SLIM_SCAN_SELECTION:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/cleaner/BaseScanner;-><init>(I)V

    return-void
.end method

.method private removeMediaItem(J)V
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mMediaItems:Ljava/util/ArrayList;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;

    iget-wide v2, v2, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mId:J

    cmp-long v2, v2, p1

    if-nez v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->remove()V

    const/4 p1, 0x1

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    :goto_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_2

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/slim/SlimScanner;->buildScanResult()Lcom/miui/gallery/cleaner/ScanResult;

    move-result-object p1

    invoke-virtual {p0, p1}, Lcom/miui/gallery/cleaner/slim/SlimScanner;->updateScanResult(Lcom/miui/gallery/cleaner/ScanResult;)V

    :cond_2
    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method


# virtual methods
.method protected buildScanResult()Lcom/miui/gallery/cleaner/ScanResult;
    .locals 17

    move-object/from16 v1, p0

    invoke-static {}, Lcom/miui/gallery/util/ScreenUtils;->getScreenWidth()I

    move-result v0

    invoke-static {}, Lcom/miui/gallery/util/ScreenUtils;->getScreenHeight()I

    move-result v2

    const/4 v3, 0x4

    new-array v3, v3, [Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

    iget-object v4, v1, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mMediaItems:Ljava/util/ArrayList;

    monitor-enter v4

    :try_start_0
    iget-object v5, v1, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v5}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v5

    const/4 v8, 0x0

    move v10, v8

    const-wide/16 v8, 0x0

    :goto_0
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v11
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    if-eqz v11, :cond_3

    :try_start_1
    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;

    iget v12, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mWidth:I

    iget v13, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mHeight:I

    invoke-static {v12, v13}, Ljava/lang/Math;->max(II)I

    move-result v12

    if-le v12, v0, :cond_0

    iget-object v12, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mPath:Ljava/lang/String;

    invoke-static {v12}, Lcom/miui/gallery/util/FileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v12

    if-eqz v12, :cond_0

    iget v12, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mWidth:I

    iget v13, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mHeight:I

    invoke-static {v12, v13, v0, v2}, Lcom/miui/gallery/util/BitmapUtils;->computeThumbNailScaleSize(IIII)F

    move-result v12

    iget-wide v13, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mSize:J

    iget-wide v6, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mSize:J

    long-to-double v6, v6

    move v15, v0

    float-to-double v0, v12

    move-object/from16 v16, v11

    const-wide/high16 v11, 0x4000000000000000L    # 2.0

    invoke-static {v0, v1, v11, v12}, Ljava/lang/Math;->pow(DD)D

    move-result-wide v0

    mul-double/2addr v6, v0

    const-wide v0, 0x3fd99999a0000000L    # 0.4000000059604645

    mul-double/2addr v6, v0

    double-to-long v0, v6

    sub-long/2addr v13, v0

    const-wide/16 v0, 0x0

    cmp-long v6, v13, v0

    if-lez v6, :cond_2

    add-long/2addr v8, v13

    goto :goto_1

    :cond_0
    move v15, v0

    move-object/from16 v16, v11

    const-wide/16 v0, 0x0

    :goto_1
    array-length v6, v3

    if-ge v10, v6, :cond_1

    new-instance v6, Lcom/miui/gallery/cleaner/ScanResult$ResultImage;

    move-object/from16 v11, v16

    iget-wide v12, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mId:J

    iget-object v7, v11, Lcom/miui/gallery/cleaner/BaseScanner$MediaItem;->mPath:Ljava/lang/String;

    invoke-direct {v6, v12, v13, v7}, Lcom/miui/gallery/cleaner/ScanResult$ResultImage;-><init>(JLjava/lang/String;)V

    aput-object v6, v3, v10

    :cond_1
    add-int/lit8 v10, v10, 0x1

    :cond_2
    move-object/from16 v1, p0

    move v0, v15

    goto :goto_0

    :cond_3
    monitor-exit v4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    new-instance v0, Lcom/miui/gallery/cleaner/ScanResult$Builder;

    invoke-direct {v0}, Lcom/miui/gallery/cleaner/ScanResult$Builder;-><init>()V

    move-object/from16 v1, p0

    iget v2, v1, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mType:I

    invoke-virtual {v0, v2}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setType(I)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    invoke-virtual {v0, v8, v9}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setSize(J)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    invoke-virtual {v0, v3}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setImages([Lcom/miui/gallery/cleaner/ScanResult$ResultImage;)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    invoke-virtual {v0, v10}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setCount(I)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    iget-object v2, v1, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mOnScanResultClickListener:Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;

    invoke-virtual {v0, v2}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->setOnScanResultClickListener(Lcom/miui/gallery/cleaner/ScanResult$OnScanResultClickListener;)Lcom/miui/gallery/cleaner/ScanResult$Builder;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/cleaner/ScanResult$Builder;->build()Lcom/miui/gallery/cleaner/ScanResult;

    move-result-object v0

    return-object v0

    :catchall_0
    move-exception v0

    move-object/from16 v1, p0

    goto :goto_2

    :catchall_1
    move-exception v0

    :goto_2
    :try_start_2
    monitor-exit v4
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    throw v0
.end method

.method protected getSelection()Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->SYNCED_SLIM_SCAN_SELECTION:Ljava/lang/String;

    return-object v0
.end method

.method public onMediaItemDeleted(J)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/cleaner/slim/SlimScanner;->removeMediaItem(J)V

    return-void
.end method

.method protected onReset()V
    .locals 1

    invoke-super {p0}, Lcom/miui/gallery/cleaner/BaseScanner;->onReset()V

    invoke-static {}, Lcom/miui/gallery/cleaner/slim/SlimController;->getInstance()Lcom/miui/gallery/cleaner/slim/SlimController;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/miui/gallery/cleaner/slim/SlimController;->unregisterObserver(Lcom/miui/gallery/cleaner/slim/SlimController$SpaceSlimObserver;)V

    return-void
.end method

.method public onSlimPaused()V
    .locals 0

    return-void
.end method

.method public onSlimProgress(JJI)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/cleaner/slim/SlimScanner;->removeMediaItem(J)V

    return-void
.end method

.method public onSlimResumed()V
    .locals 0

    return-void
.end method

.method protected recordClickScanResultEvent()V
    .locals 3

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iget-object v1, p0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v1

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    const-string v2, "result"

    invoke-virtual {v0, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const-string v1, "cleaner"

    const-string v2, "cleaner_result_slim_click"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method

.method public scan()Lcom/miui/gallery/cleaner/ScanResult;
    .locals 7

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    sget-object v1, Lcom/miui/gallery/provider/GalleryContract$Media;->URI_OWNER_ALBUM_MEDIA:Landroid/net/Uri;

    sget-object v2, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->SLIM_SCAN_PROJECTION:[Ljava/lang/String;

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/slim/SlimScanner;->getSelection()Ljava/lang/String;

    move-result-object v3

    sget-object v5, Lcom/miui/gallery/cleaner/slim/SlimScanner;->SLIM_SCAN_ORDER:Ljava/lang/String;

    new-instance v6, Lcom/miui/gallery/cleaner/slim/SlimScanner$1;

    invoke-direct {v6, p0}, Lcom/miui/gallery/cleaner/slim/SlimScanner$1;-><init>(Lcom/miui/gallery/cleaner/slim/SlimScanner;)V

    const/4 v4, 0x0

    invoke-static/range {v0 .. v6}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v1

    if-nez v1, :cond_0

    const/4 v0, 0x0

    return-object v0

    :cond_0
    iput-object v0, p0, Lcom/miui/gallery/cleaner/slim/SlimScanner;->mMediaItems:Ljava/util/ArrayList;

    invoke-static {}, Lcom/miui/gallery/cleaner/slim/SlimController;->getInstance()Lcom/miui/gallery/cleaner/slim/SlimController;

    move-result-object v0

    invoke-virtual {v0, p0}, Lcom/miui/gallery/cleaner/slim/SlimController;->registerObserver(Lcom/miui/gallery/cleaner/slim/SlimController$SpaceSlimObserver;)V

    invoke-virtual {p0}, Lcom/miui/gallery/cleaner/slim/SlimScanner;->buildScanResult()Lcom/miui/gallery/cleaner/ScanResult;

    move-result-object v0

    return-object v0
.end method
