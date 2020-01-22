.class public Lcom/miui/gallery/loader/StorageSetLoader;
.super Lcom/miui/gallery/loader/BaseLoader;
.source "StorageSetLoader.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/loader/StorageSetLoader$StorageDataSet;,
        Lcom/miui/gallery/loader/StorageSetLoader$MFileComparator;,
        Lcom/miui/gallery/loader/StorageSetLoader$MFileFilter;
    }
.end annotation


# instance fields
.field private mFolderPath:Ljava/lang/String;

.field private mInitFilePath:Ljava/lang/String;

.field private mInitUri:Landroid/net/Uri;

.field private mLimitUris:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Landroid/net/Uri;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/net/Uri;Landroid/os/Bundle;)V
    .locals 1

    invoke-direct {p0, p1}, Lcom/miui/gallery/loader/BaseLoader;-><init>(Landroid/content/Context;)V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    iput-object p2, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitUri:Landroid/net/Uri;

    invoke-virtual {p2}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/io/File;

    invoke-direct {p2, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2}, Ljava/io/File;->isDirectory()Z

    move-result p2

    if-eqz p2, :cond_0

    iput-object p1, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mFolderPath:Ljava/lang/String;

    goto :goto_0

    :cond_0
    const-string p2, "com.miui.gallery.extra.photo_items"

    invoke-virtual {p3, p2}, Landroid/os/Bundle;->getParcelableArrayList(Ljava/lang/String;)Ljava/util/ArrayList;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mLimitUris:Ljava/util/ArrayList;

    const/4 p2, 0x0

    const-string v0, "com.miui.gallery.extra.preview_single_item"

    invoke-virtual {p3, v0, p2}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;Z)Z

    move-result p2

    if-eqz p2, :cond_1

    new-instance p2, Ljava/util/ArrayList;

    const/4 p3, 0x1

    invoke-direct {p2, p3}, Ljava/util/ArrayList;-><init>(I)V

    iput-object p2, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mLimitUris:Ljava/util/ArrayList;

    iget-object p2, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mLimitUris:Ljava/util/ArrayList;

    new-instance p3, Ljava/io/File;

    invoke-direct {p3, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {p3}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object p3

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    invoke-direct {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->isLimitedUris()Z

    move-result p2

    if-nez p2, :cond_2

    invoke-static {p1}, Lcom/miui/gallery/util/FileUtils;->getParentFolderPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mFolderPath:Ljava/lang/String;

    :cond_2
    :goto_0
    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/loader/StorageSetLoader;Ljava/lang/String;)Z
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/loader/StorageSetLoader;->isSupportFile(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/loader/StorageSetLoader;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->isLimitedUris()Z

    move-result p0

    return p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/loader/StorageSetLoader;)Ljava/util/ArrayList;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mLimitUris:Ljava/util/ArrayList;

    return-object p0
.end method

.method private isLimitedUris()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mLimitUris:Ljava/util/ArrayList;

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    return v0
.end method

.method private isSupportFile(Ljava/lang/String;)Z
    .locals 2

    invoke-static {p1}, Lcom/miui/gallery/util/FileMimeUtil;->getMimeType(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/gallery/loader/StorageSetLoader;->isSupportType(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    invoke-virtual {p1, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-static {p1}, Lcom/miui/gallery/util/FileMimeUtil;->getMimeTypeByParseFile(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/gallery/loader/StorageSetLoader;->isSupportType(Ljava/lang/String;)Z

    move-result p1

    or-int/2addr v0, p1

    :cond_0
    return v0
.end method

.method private isSupportType(Ljava/lang/String;)Z
    .locals 1

    invoke-static {p1}, Lcom/miui/gallery/util/FileMimeUtil;->isImageFromMimeType(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {p1}, Lcom/miui/gallery/util/FileMimeUtil;->isVideoFromMimeType(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    return p1
.end method


# virtual methods
.method public loadInBackground()Lcom/miui/gallery/model/BaseDataSet;
    .locals 14

    const-string v0, "load_performance"

    const-string v1, "_"

    const-string v2, "cost"

    const-string v3, "StorageSetLoader"

    invoke-direct {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->isLimitedUris()Z

    move-result v4

    if-eqz v4, :cond_0

    const-string v4, "StorageSetLoader_limited_load"

    goto :goto_0

    :cond_0
    const-string v4, "StorageSetLoader_load"

    :goto_0
    const-string v5, "loadInBackground"

    invoke-static {v4, v5}, Lcom/miui/gallery/util/logger/TimingTracing;->beginTracing(Ljava/lang/String;Ljava/lang/String;)V

    const-wide/16 v5, 0x1f4

    const/4 v7, 0x0

    :try_start_0
    iget-object v8, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    invoke-static {v8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v8

    if-eqz v8, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->getContext()Landroid/content/Context;

    move-result-object v8

    iget-object v9, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitUri:Landroid/net/Uri;

    invoke-static {v8, v9}, Lcom/miui/gallery/util/ContentUtils;->getValidFilePathForContentUri(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object v8

    iput-object v8, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    iget-object v8, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    invoke-static {v8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v8

    if-eqz v8, :cond_1

    iget-object v8, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitUri:Landroid/net/Uri;

    invoke-virtual {v8}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v8

    iput-object v8, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    :cond_1
    invoke-direct {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->isLimitedUris()Z

    move-result v8

    if-eqz v8, :cond_3

    new-instance v8, Ljava/util/ArrayList;

    iget-object v9, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mLimitUris:Ljava/util/ArrayList;

    invoke-virtual {v9}, Ljava/util/ArrayList;->size()I

    move-result v9

    invoke-direct {v8, v9}, Ljava/util/ArrayList;-><init>(I)V

    iget-object v9, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mLimitUris:Ljava/util/ArrayList;

    invoke-virtual {v9}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v9

    :cond_2
    :goto_1
    invoke-interface {v9}, Ljava/util/Iterator;->hasNext()Z

    move-result v10

    if-eqz v10, :cond_6

    invoke-interface {v9}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Landroid/net/Uri;

    if-eqz v10, :cond_2

    const-string v11, "file"

    invoke-virtual {v10}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v11, v12}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v11

    if-eqz v11, :cond_2

    invoke-virtual {v10}, Landroid/net/Uri;->getPath()Ljava/lang/String;

    move-result-object v10

    invoke-static {v10}, Lcom/miui/gallery/util/FileUtils;->isFileExist(Ljava/lang/String;)Z

    move-result v11

    if-eqz v11, :cond_2

    invoke-direct {p0, v10}, Lcom/miui/gallery/loader/StorageSetLoader;->isSupportFile(Ljava/lang/String;)Z

    move-result v11

    if-eqz v11, :cond_2

    new-instance v11, Ljava/io/File;

    invoke-direct {v11, v10}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-interface {v8, v11}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_3
    new-instance v8, Ljava/io/File;

    iget-object v9, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mFolderPath:Ljava/lang/String;

    invoke-direct {v8, v9}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    new-instance v9, Lcom/miui/gallery/loader/StorageSetLoader$MFileFilter;

    invoke-direct {v9, p0, v7}, Lcom/miui/gallery/loader/StorageSetLoader$MFileFilter;-><init>(Lcom/miui/gallery/loader/StorageSetLoader;Lcom/miui/gallery/loader/StorageSetLoader$1;)V

    invoke-virtual {v8, v9}, Ljava/io/File;->listFiles(Ljava/io/FileFilter;)[Ljava/io/File;

    move-result-object v8

    if-eqz v8, :cond_4

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v9

    new-instance v11, Lcom/miui/gallery/loader/StorageSetLoader$MFileComparator;

    invoke-direct {v11, v7}, Lcom/miui/gallery/loader/StorageSetLoader$MFileComparator;-><init>(Lcom/miui/gallery/loader/StorageSetLoader$1;)V

    invoke-static {v8, v11}, Ljava/util/Arrays;->sort([Ljava/lang/Object;Ljava/util/Comparator;)V

    const-string v11, "file sort cost[%d], size[%d]"

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v12

    sub-long/2addr v12, v9

    invoke-static {v12, v13}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v9

    array-length v10, v8

    invoke-static {v10}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v10

    invoke-static {v3, v11, v9, v10}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-static {v8}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v8

    goto :goto_2

    :cond_4
    iget-object v8, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    invoke-static {v8}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v8

    if-nez v8, :cond_5

    new-instance v8, Ljava/io/File;

    iget-object v9, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    invoke-direct {v8, v9}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8}, Ljava/io/File;->exists()Z

    move-result v9

    if-eqz v9, :cond_5

    new-instance v9, Ljava/util/ArrayList;

    const/4 v10, 0x1

    invoke-direct {v9, v10}, Ljava/util/ArrayList;-><init>(I)V

    invoke-interface {v9, v8}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    move-object v8, v9

    goto :goto_2

    :cond_5
    move-object v8, v7

    :cond_6
    :goto_2
    new-instance v9, Lcom/miui/gallery/loader/StorageSetLoader$StorageDataSet;

    iget-object v10, p0, Lcom/miui/gallery/loader/StorageSetLoader;->mInitFilePath:Ljava/lang/String;

    invoke-direct {v9, p0, v8, v10}, Lcom/miui/gallery/loader/StorageSetLoader$StorageDataSet;-><init>(Lcom/miui/gallery/loader/StorageSetLoader;Ljava/util/List;Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    invoke-static {v4, v7}, Lcom/miui/gallery/util/logger/TimingTracing;->stopTracing(Ljava/lang/String;Landroid/util/Printer;)J

    move-result-wide v7

    cmp-long v4, v7, v5

    if-lez v4, :cond_7

    new-instance v4, Ljava/util/HashMap;

    invoke-direct {v4}, Ljava/util/HashMap;-><init>()V

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-direct {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->isLimitedUris()Z

    move-result v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v4, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v0, v3, v4}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    :cond_7
    return-object v9

    :catchall_0
    move-exception v8

    invoke-static {v4, v7}, Lcom/miui/gallery/util/logger/TimingTracing;->stopTracing(Ljava/lang/String;Landroid/util/Printer;)J

    move-result-wide v9

    cmp-long v4, v9, v5

    if-lez v4, :cond_8

    new-instance v4, Ljava/util/HashMap;

    invoke-direct {v4}, Ljava/util/HashMap;-><init>()V

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-direct {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->isLimitedUris()Z

    move-result v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v4, v2, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    invoke-static {v0, v3, v4}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    :cond_8
    throw v8
.end method

.method public bridge synthetic loadInBackground()Ljava/lang/Object;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/loader/StorageSetLoader;->loadInBackground()Lcom/miui/gallery/model/BaseDataSet;

    move-result-object v0

    return-object v0
.end method
