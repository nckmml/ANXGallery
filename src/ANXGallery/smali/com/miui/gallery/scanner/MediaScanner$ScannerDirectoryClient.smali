.class Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;
.super Ljava/lang/Object;
.source "MediaScanner.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/scanner/MediaScanner;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "ScannerDirectoryClient"
.end annotation


# instance fields
.field private final PRINTER:Landroid/util/Printer;

.field private mFileClient:Lcom/miui/gallery/scanner/MediaScanner$ScannerFileClient;

.field private mJobContext:Lcom/miui/gallery/threadpool/ThreadPool$JobContext;


# direct methods
.method constructor <init>(Lcom/miui/gallery/scanner/MediaScanner$ScannerFileClient;Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget-object v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$ScannerDirectoryClient$d79RSwiYaZLaagVIaZUaZpBRk5Q;->INSTANCE:Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$ScannerDirectoryClient$d79RSwiYaZLaagVIaZUaZpBRk5Q;

    iput-object v0, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->PRINTER:Landroid/util/Printer;

    iput-object p1, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->mFileClient:Lcom/miui/gallery/scanner/MediaScanner$ScannerFileClient;

    iput-object p2, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->mJobContext:Lcom/miui/gallery/threadpool/ThreadPool$JobContext;

    return-void
.end method

.method private doScan(Landroid/content/Context;Ljava/lang/String;Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;[Ljava/io/File;J)Ljava/util/ArrayList;
    .locals 19
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;",
            "[",
            "Ljava/io/File;",
            "J)",
            "Ljava/util/ArrayList<",
            "Ljava/io/File;",
            ">;"
        }
    .end annotation

    move-object/from16 v2, p1

    move-object/from16 v3, p2

    move-object/from16 v1, p4

    const/4 v4, 0x0

    if-nez p3, :cond_0

    invoke-static {v2, v3, v4}, Lcom/miui/gallery/scanner/MediaScanner;->access$200(Landroid/content/Context;Ljava/lang/String;Landroid/content/ContentValues;)Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;

    move-result-object v0

    move-object v6, v0

    goto :goto_0

    :cond_0
    move-object/from16 v6, p3

    :goto_0
    if-eqz v6, :cond_1

    iget-boolean v0, v6, Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;->isOnlyLinkFolder:Z

    if-eqz v0, :cond_1

    return-object v4

    :cond_1
    const/4 v5, 0x1

    const/4 v0, 0x0

    if-eqz v6, :cond_2

    iget-boolean v7, v6, Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;->isModified:Z

    if-eqz v7, :cond_2

    invoke-static {v6}, Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;->shouldScan(Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;)Z

    move-result v7

    if-eqz v7, :cond_2

    move v7, v5

    goto :goto_1

    :cond_2
    move v7, v0

    :goto_1
    const-string v8, "MediaScanner"

    if-eqz v7, :cond_3

    const-string v9, "do scan folder %s"

    invoke-static {v8, v9, v3}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_3
    new-instance v9, Ljava/util/ArrayList;

    invoke-direct {v9}, Ljava/util/ArrayList;-><init>()V

    array-length v10, v1

    move v11, v0

    move v12, v11

    :goto_2
    if-ge v11, v10, :cond_8

    aget-object v13, v1, v11

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->isCancelled()Z

    move-result v0

    if-eqz v0, :cond_4

    return-object v4

    :cond_4
    invoke-virtual {v13}, Ljava/io/File;->isFile()Z

    move-result v0

    if-eqz v0, :cond_6

    if-eqz v7, :cond_5

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1

    move-object/from16 v14, p0

    :try_start_1
    iget-object v0, v14, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->mFileClient:Lcom/miui/gallery/scanner/MediaScanner$ScannerFileClient;

    invoke-virtual {v0, v2, v13, v6}, Lcom/miui/gallery/scanner/MediaScanner$ScannerFileClient;->scanFile(Landroid/content/Context;Ljava/io/File;Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;)J

    move-result-wide v17

    const-wide/16 v15, -0x1

    cmp-long v0, v15, v17

    if-nez v0, :cond_7

    const-string v0, "scan %s fail"

    invoke-virtual {v13}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v15

    invoke-static {v8, v0, v15}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    move v12, v5

    goto :goto_4

    :catch_0
    move-exception v0

    goto :goto_3

    :catch_1
    move-exception v0

    move-object/from16 v14, p0

    :goto_3
    invoke-virtual {v13}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v13

    const-string v15, "scan file %s exception %s"

    invoke-static {v8, v15, v13, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_4

    :cond_5
    move-object/from16 v14, p0

    goto :goto_4

    :cond_6
    move-object/from16 v14, p0

    invoke-virtual {v9, v13}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_7
    :goto_4
    add-int/lit8 v11, v11, 0x1

    goto :goto_2

    :cond_8
    move-object/from16 v14, p0

    if-eqz v7, :cond_9

    invoke-direct/range {p0 .. p0}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->isCancelled()Z

    move-result v0

    if-nez v0, :cond_9

    if-nez v12, :cond_9

    invoke-static {v2, v6}, Lcom/miui/gallery/scanner/MediaScanner;->access$100(Landroid/content/Context;Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;)V

    sget-boolean v0, Lcom/miui/os/Rom;->IS_DEV:Z

    if-eqz v0, :cond_9

    move-object/from16 v1, p0

    move-object/from16 v2, p1

    move-object/from16 v3, p2

    move-wide/from16 v4, p5

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->recordScanCost(Landroid/content/Context;Ljava/lang/String;JLcom/miui/gallery/scanner/MediaScanner$AlbumEntry;)V

    :cond_9
    return-object v9
.end method

.method private isCancelled()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->mJobContext:Lcom/miui/gallery/threadpool/ThreadPool$JobContext;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Lcom/miui/gallery/threadpool/ThreadPool$JobContext;->isCancelled()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method static synthetic lambda$new$52(Ljava/lang/String;)V
    .locals 0

    return-void
.end method

.method private recordScanCost(Landroid/content/Context;Ljava/lang/String;JLcom/miui/gallery/scanner/MediaScanner$AlbumEntry;)V
    .locals 5

    :try_start_0
    iget-object v0, p5, Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;->mServerID:Ljava/lang/String;

    invoke-static {v0}, Ljava/lang/Long;->parseLong(Ljava/lang/String;)J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/NumberFormatException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v1

    const-wide/16 v3, 0x1

    cmp-long v1, v1, v3

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    const-wide/16 v2, 0x2

    cmp-long v0, v0, v2

    if-nez v0, :cond_1

    :cond_0
    invoke-static {p1, p2}, Lcom/miui/gallery/util/StorageUtils;->getRelativePath(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/util/HashMap;

    invoke-direct {p2}, Ljava/util/HashMap;-><init>()V

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-wide v2, p5, Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;->mDateModified:J

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x3e8

    div-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p5

    const-string v0, "wait_for_scan_cost(s)"

    invoke-virtual {p2, v0, p5}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v0

    sub-long/2addr v0, p3

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p3

    const-string p4, "scan_cost(ms)"

    invoke-virtual {p2, p4, p3}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 p3, 0x1

    new-array p3, p3, [Ljava/lang/Object;

    const/4 p4, 0x0

    aput-object p1, p3, p4

    const-string p1, "scan_directory_%s"

    invoke-static {p1, p3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    const-string p3, "media_scanner"

    invoke-static {p3, p1, p2}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    :cond_1
    return-void
.end method


# virtual methods
.method public scanFolder(Landroid/content/Context;Ljava/io/File;Z)V
    .locals 10

    invoke-static {}, Landroid/os/SystemClock;->uptimeMillis()J

    move-result-wide v5

    if-eqz p2, :cond_e

    invoke-virtual {p2}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_e

    invoke-virtual {p2}, Ljava/io/File;->isHidden()Z

    move-result v0

    if-nez v0, :cond_e

    invoke-virtual {p2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    invoke-static {p1, v2}, Lcom/miui/gallery/util/StorageUtils;->getRelativePath(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/scanner/MediaScanner$MediaScannerHelper;->isOnlyLinkFileFolder(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    return-void

    :cond_0
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "ScannerDirectoryClient-"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    const-string v0, "scanFolder"

    invoke-static {v7, v0}, Lcom/miui/gallery/util/logger/TimingTracing;->beginTracing(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v0, 0x0

    :try_start_0
    invoke-static {p1, v2, v0}, Lcom/miui/gallery/scanner/MediaScanner;->access$000(Landroid/content/Context;Ljava/lang/String;Landroid/content/ContentValues;)Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;

    move-result-object v3

    const-string v1, "queryAndUpdateAlbum"

    invoke-static {v7, v1}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v1, 0x0

    const/4 v8, 0x1

    if-eqz v3, :cond_2

    iget-boolean v4, v3, Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;->isModified:Z

    if-eqz v4, :cond_1

    invoke-static {v3}, Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;->shouldScan(Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;)Z

    move-result v4
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v4, :cond_1

    goto :goto_0

    :cond_1
    move v4, v1

    goto :goto_1

    :cond_2
    :goto_0
    move v4, v8

    :goto_1
    if-nez v4, :cond_3

    if-nez p3, :cond_3

    :goto_2
    iget-object p1, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->PRINTER:Landroid/util/Printer;

    invoke-static {v7, p1}, Lcom/miui/gallery/util/logger/TimingTracing;->stopTracing(Ljava/lang/String;Landroid/util/Printer;)J

    return-void

    :cond_3
    :try_start_1
    iget-object v4, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->mJobContext:Lcom/miui/gallery/threadpool/ThreadPool$JobContext;

    if-eqz p3, :cond_4

    goto :goto_3

    :cond_4
    move-object v0, v3

    :goto_3
    if-nez p3, :cond_5

    move v9, v8

    goto :goto_4

    :cond_5
    move v9, v1

    :goto_4
    invoke-static {v4, v0, v9}, Lcom/miui/gallery/scanner/MediaScanner$MediaScannerHelper;->newFileFilter(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;Z)Lcom/miui/gallery/scanner/MediaScanner$MediaScannerHelper$MediaFileFilter;

    move-result-object v0

    const-string v4, "newFileFilter"

    invoke-static {v7, v4}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p2, v0}, Ljava/io/File;->listFiles(Ljava/io/FilenameFilter;)[Ljava/io/File;

    move-result-object v4

    const-string p2, "listFiles"

    invoke-static {v7, p2}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V

    if-eqz v4, :cond_d

    invoke-direct {p0}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->isCancelled()Z

    move-result p2

    if-nez p2, :cond_d

    invoke-virtual {v0}, Lcom/miui/gallery/scanner/MediaScanner$MediaScannerHelper$MediaFileFilter;->hasMediaFile()Z

    move-result p2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const-string v9, "scanChildFolders"

    if-eqz p2, :cond_9

    :try_start_2
    invoke-static {}, Lcom/miui/gallery/scanner/MediaScanner$MediaScannerHelper;->getFileComparator()Ljava/util/Comparator;

    move-result-object p2

    invoke-static {v4, p2}, Ljava/util/Arrays;->sort([Ljava/lang/Object;Ljava/util/Comparator;)V

    const-string p2, "sortFiles"

    invoke-static {v7, p2}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->isCancelled()Z

    move-result p2

    if-eqz p2, :cond_6

    goto :goto_2

    :cond_6
    move-object v0, p0

    move-object v1, p1

    invoke-direct/range {v0 .. v6}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->doScan(Landroid/content/Context;Ljava/lang/String;Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;[Ljava/io/File;J)Ljava/util/ArrayList;

    move-result-object p2

    const-string v0, "doScan"

    invoke-static {v7, v0}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V

    if-eqz p3, :cond_d

    if-eqz p2, :cond_d

    invoke-virtual {p2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_5
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result p3

    if-eqz p3, :cond_8

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/io/File;

    invoke-direct {p0}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->isCancelled()Z

    move-result v0

    if-eqz v0, :cond_7

    goto :goto_2

    :cond_7
    invoke-virtual {p0, p1, p3, v8}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->scanFolder(Landroid/content/Context;Ljava/io/File;Z)V

    goto :goto_5

    :cond_8
    invoke-static {v7, v9}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_7

    :cond_9
    if-eqz v3, :cond_a

    invoke-static {p1, v3}, Lcom/miui/gallery/scanner/MediaScanner;->access$100(Landroid/content/Context;Lcom/miui/gallery/scanner/MediaScanner$AlbumEntry;)V

    const-string p2, "updateAlbumDateModified"

    invoke-static {v7, p2}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V

    :cond_a
    if-eqz p3, :cond_d

    array-length p2, v4

    :goto_6
    if-ge v1, p2, :cond_c

    aget-object p3, v4, v1

    invoke-direct {p0}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->isCancelled()Z

    move-result v0

    if-eqz v0, :cond_b

    goto/16 :goto_2

    :cond_b
    invoke-virtual {p0, p1, p3, v8}, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->scanFolder(Landroid/content/Context;Ljava/io/File;Z)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_6

    :cond_c
    invoke-static {v7, v9}, Lcom/miui/gallery/util/logger/TimingTracing;->addSplit(Ljava/lang/String;Ljava/lang/String;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :cond_d
    :goto_7
    iget-object p1, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->PRINTER:Landroid/util/Printer;

    invoke-static {v7, p1}, Lcom/miui/gallery/util/logger/TimingTracing;->stopTracing(Ljava/lang/String;Landroid/util/Printer;)J

    goto :goto_8

    :catchall_0
    move-exception p1

    iget-object p2, p0, Lcom/miui/gallery/scanner/MediaScanner$ScannerDirectoryClient;->PRINTER:Landroid/util/Printer;

    invoke-static {v7, p2}, Lcom/miui/gallery/util/logger/TimingTracing;->stopTracing(Ljava/lang/String;Landroid/util/Printer;)J

    throw p1

    :cond_e
    :goto_8
    return-void
.end method
