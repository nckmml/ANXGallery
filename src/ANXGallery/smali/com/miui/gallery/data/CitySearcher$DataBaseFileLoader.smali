.class Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;
.super Ljava/lang/Object;
.source "CitySearcher.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/data/CitySearcher;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "DataBaseFileLoader"
.end annotation


# instance fields
.field private mContext:Landroid/content/Context;

.field private mDataBasePath:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mContext:Landroid/content/Context;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1}, Landroid/content/Context;->getApplicationInfo()Landroid/content/pm/ApplicationInfo;

    move-result-object p1

    iget-object p1, p1, Landroid/content/pm/ApplicationInfo;->dataDir:Ljava/lang/String;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "/databases"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mDataBasePath:Ljava/lang/String;

    return-void
.end method

.method private compareStream(Ljava/io/InputStream;Ljava/io/InputStream;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    instance-of v0, p1, Ljava/io/BufferedInputStream;

    if-nez v0, :cond_0

    new-instance v0, Ljava/io/BufferedInputStream;

    invoke-direct {v0, p1}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V

    move-object p1, v0

    :cond_0
    instance-of v0, p2, Ljava/io/BufferedInputStream;

    if-nez v0, :cond_1

    new-instance v0, Ljava/io/BufferedInputStream;

    invoke-direct {v0, p2}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V

    move-object p2, v0

    :cond_1
    invoke-virtual {p1}, Ljava/io/InputStream;->read()I

    move-result v0

    const/4 v1, 0x0

    const/4 v2, -0x1

    if-eq v0, v2, :cond_2

    invoke-virtual {p2}, Ljava/io/InputStream;->read()I

    move-result v2

    if-eq v0, v2, :cond_1

    return v1

    :cond_2
    invoke-virtual {p2}, Ljava/io/InputStream;->read()I

    move-result p1

    if-ne p1, v2, :cond_3

    const/4 v1, 0x1

    :cond_3
    return v1
.end method

.method private copyFile()Z
    .locals 8

    const-string v0, "DataBaseFileLoader"

    new-instance v1, Ljava/io/File;

    iget-object v2, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mDataBasePath:Ljava/lang/String;

    invoke-direct {v1, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    new-instance v2, Ljava/io/File;

    const-string v3, "city.db"

    invoke-direct {v2, v1, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    new-instance v4, Ljava/io/File;

    const-string v5, "city.db.md5"

    invoke-direct {v4, v1, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    const/4 v6, 0x0

    :try_start_0
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v7

    if-nez v7, :cond_0

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    :cond_0
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-virtual {v2}, Ljava/io/File;->delete()Z

    move-result v1

    if-nez v1, :cond_1

    return v6

    :cond_1
    invoke-virtual {v4}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    move-result v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_5

    if-nez v1, :cond_2

    return v6

    :cond_2
    const/4 v1, 0x0

    :try_start_1
    iget-object v7, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mContext:Landroid/content/Context;

    invoke-virtual {v7}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v7

    invoke-virtual {v7, v3}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v3
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_4
    .catchall {:try_start_1 .. :try_end_1} :catchall_4

    :try_start_2
    new-instance v7, Ljava/io/FileOutputStream;

    invoke-direct {v7, v2}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_3
    .catchall {:try_start_2 .. :try_end_2} :catchall_3

    :try_start_3
    invoke-static {v3, v7}, Lmiui/util/IOUtils;->copy(Ljava/io/InputStream;Ljava/io/OutputStream;)J
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_2
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v7}, Lcom/nostra13/universalimageloader/utils/IoUtils;->closeSilently(Ljava/io/Closeable;)V

    :try_start_4
    iget-object v1, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    invoke-virtual {v1, v5}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v3

    new-instance v1, Ljava/io/FileOutputStream;

    invoke-direct {v1, v4}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_1
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    :try_start_5
    invoke-static {v3, v1}, Lmiui/util/IOUtils;->copy(Ljava/io/InputStream;Ljava/io/OutputStream;)J
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_0
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v1}, Lcom/nostra13/universalimageloader/utils/IoUtils;->closeSilently(Ljava/io/Closeable;)V

    const/4 v0, 0x1

    return v0

    :catchall_0
    move-exception v0

    move-object v7, v1

    goto :goto_1

    :catch_0
    move-exception v2

    move-object v7, v1

    goto :goto_0

    :catchall_1
    move-exception v0

    goto :goto_1

    :catch_1
    move-exception v2

    :goto_0
    :try_start_6
    invoke-static {v0, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v7}, Lcom/nostra13/universalimageloader/utils/IoUtils;->closeSilently(Ljava/io/Closeable;)V

    return v6

    :goto_1
    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v7}, Lcom/nostra13/universalimageloader/utils/IoUtils;->closeSilently(Ljava/io/Closeable;)V

    throw v0

    :catchall_2
    move-exception v0

    goto :goto_2

    :catch_2
    move-exception v1

    move-object v2, v1

    goto :goto_3

    :catchall_3
    move-exception v0

    move-object v7, v1

    :goto_2
    move-object v1, v3

    goto :goto_5

    :catch_3
    move-exception v2

    move-object v7, v1

    :goto_3
    move-object v1, v3

    goto :goto_4

    :catchall_4
    move-exception v0

    move-object v7, v1

    goto :goto_5

    :catch_4
    move-exception v2

    move-object v7, v1

    :goto_4
    :try_start_7
    invoke-static {v0, v2}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_5

    invoke-static {v1}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v7}, Lcom/nostra13/universalimageloader/utils/IoUtils;->closeSilently(Ljava/io/Closeable;)V

    return v6

    :catchall_5
    move-exception v0

    :goto_5
    invoke-static {v1}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v7}, Lcom/nostra13/universalimageloader/utils/IoUtils;->closeSilently(Ljava/io/Closeable;)V

    throw v0

    :catch_5
    move-exception v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V

    return v6
.end method

.method private isFileCopied()Z
    .locals 5

    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mDataBasePath:Ljava/lang/String;

    const-string v2, "city.db"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    new-instance v0, Ljava/io/File;

    iget-object v2, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mDataBasePath:Ljava/lang/String;

    const-string v3, "city.db.md5"

    invoke-direct {v0, v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v2

    if-nez v2, :cond_1

    return v1

    :cond_1
    const/4 v2, 0x0

    :try_start_0
    new-instance v4, Ljava/io/FileInputStream;

    invoke-direct {v4, v0}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    :try_start_1
    iget-object v0, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v0

    invoke-virtual {v0, v3}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v2

    invoke-direct {p0, v4, v2}, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->compareStream(Ljava/io/InputStream;Ljava/io/InputStream;)Z

    move-result v0
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    invoke-static {v4}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    return v0

    :catchall_0
    move-exception v0

    move-object v3, v2

    move-object v2, v4

    goto :goto_1

    :catch_0
    move-exception v0

    move-object v3, v2

    move-object v2, v4

    goto :goto_0

    :catchall_1
    move-exception v0

    move-object v3, v2

    goto :goto_1

    :catch_1
    move-exception v0

    move-object v3, v2

    :goto_0
    :try_start_2
    const-string v4, "DataBaseFileLoader"

    invoke-static {v4, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    return v1

    :catchall_2
    move-exception v0

    :goto_1
    invoke-static {v2}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    invoke-static {v3}, Lmiui/util/IOUtils;->closeQuietly(Ljava/io/InputStream;)V

    throw v0
.end method


# virtual methods
.method public loadFile()Ljava/lang/String;
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->isFileCopied()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->copyFile()Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "/data/miui/gallery/city.db"

    return-object v0

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Lcom/miui/gallery/data/CitySearcher$DataBaseFileLoader;->mDataBasePath:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "/"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "city.db"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
