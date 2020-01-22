.class Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;
.super Landroid/os/AsyncTask;
.source "CleanService.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/sdk/CleanService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "CleanTask"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/os/AsyncTask<",
        "Landroid/app/job/JobParameters;",
        "Ljava/lang/Void;",
        "Ljava/lang/Void;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/editor/photo/sdk/CleanService;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/editor/photo/sdk/CleanService;Lcom/miui/gallery/editor/photo/sdk/CleanService$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;-><init>(Lcom/miui/gallery/editor/photo/sdk/CleanService;)V

    return-void
.end method

.method private delete(Ljava/io/File;)Z
    .locals 17

    move-object/from16 v1, p0

    move-object/from16 v0, p1

    if-eqz v0, :cond_c

    invoke-virtual/range {p1 .. p1}, Ljava/io/File;->isFile()Z

    move-result v2

    const-string v3, "CleanService.CleanTask"

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v2, :cond_2

    invoke-virtual/range {p1 .. p1}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-virtual/range {p1 .. p1}, Ljava/io/File;->delete()Z

    move-result v2

    if-eqz v2, :cond_0

    goto :goto_0

    :cond_0
    move v4, v5

    :cond_1
    :goto_0
    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    const-string v5, "deleting file: %s. deleted: %b"

    invoke-static {v3, v5, v0, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return v4

    :cond_2
    invoke-virtual/range {p1 .. p1}, Ljava/io/File;->isDirectory()Z

    move-result v2

    if-eqz v2, :cond_b

    iget-object v2, v1, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;

    invoke-virtual {v2}, Lcom/miui/gallery/editor/photo/sdk/CleanService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v6

    const-string v2, "external"

    invoke-static {v2}, Landroid/provider/MediaStore$Files;->getContentUri(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v7

    const-string v8, "_id"

    filled-new-array {v8}, [Ljava/lang/String;

    move-result-object v8

    new-array v10, v4, [Ljava/lang/String;

    invoke-virtual/range {p1 .. p1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v9

    aput-object v9, v10, v5

    const/4 v11, 0x0

    const-string v9, "_data=?"

    invoke-virtual/range {v6 .. v11}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v6

    const-wide/16 v7, -0x1

    if-eqz v6, :cond_4

    :try_start_0
    invoke-interface {v6}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v9

    if-eqz v9, :cond_3

    invoke-interface {v6, v5}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v9
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_1

    :cond_3
    move-wide v9, v7

    :goto_1
    invoke-interface {v6}, Landroid/database/Cursor;->close()V

    goto :goto_2

    :catchall_0
    move-exception v0

    invoke-interface {v6}, Landroid/database/Cursor;->close()V

    throw v0

    :cond_4
    move-wide v9, v7

    :goto_2
    invoke-static {v9, v10}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    const-string v11, "cleaning directory(%d)"

    invoke-static {v3, v11, v6}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance v6, Landroid/util/ArraySet;

    invoke-direct {v6}, Landroid/util/ArraySet;-><init>()V

    cmp-long v7, v9, v7

    if-eqz v7, :cond_7

    iget-object v7, v1, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;

    invoke-virtual {v7}, Lcom/miui/gallery/editor/photo/sdk/CleanService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v11

    invoke-static {v2}, Landroid/provider/MediaStore$Files;->getContentUri(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v12

    const-string v7, "_data"

    filled-new-array {v7}, [Ljava/lang/String;

    move-result-object v13

    new-array v15, v4, [Ljava/lang/String;

    invoke-static {v9, v10}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v7

    aput-object v7, v15, v5

    const/16 v16, 0x0

    const-string v14, "parent=?"

    invoke-virtual/range {v11 .. v16}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v7

    invoke-static {v9, v10}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v8

    if-nez v7, :cond_5

    const/4 v9, -0x1

    goto :goto_3

    :cond_5
    invoke-interface {v7}, Landroid/database/Cursor;->getCount()I

    move-result v9

    :goto_3
    invoke-static {v9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v9

    const-string v10, "mark children of %d, count: %d"

    invoke-static {v3, v10, v8, v9}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    if-eqz v7, :cond_7

    :goto_4
    :try_start_1
    invoke-interface {v7}, Landroid/database/Cursor;->moveToNext()Z

    move-result v8

    if-eqz v8, :cond_6

    invoke-interface {v7, v5}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-interface {v6, v8}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_4

    :cond_6
    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    goto :goto_5

    :catchall_1
    move-exception v0

    invoke-interface {v7}, Landroid/database/Cursor;->close()V

    throw v0

    :cond_7
    :goto_5
    invoke-virtual/range {p1 .. p1}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v0

    array-length v7, v0

    move v9, v4

    move v8, v5

    :goto_6
    if-ge v8, v7, :cond_a

    aget-object v10, v0, v8

    invoke-virtual {v10}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v11

    invoke-interface {v6, v11}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v12

    if-eqz v12, :cond_9

    iget-object v12, v1, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;

    invoke-virtual {v12}, Lcom/miui/gallery/editor/photo/sdk/CleanService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v12

    invoke-static {v2}, Landroid/provider/MediaStore$Files;->getContentUri(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v13

    new-array v14, v4, [Ljava/lang/String;

    aput-object v11, v14, v5

    const-string v15, "_data=?"

    invoke-virtual {v12, v13, v15, v14}, Landroid/content/ContentResolver;->delete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I

    move-result v12

    invoke-static {v12}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v13

    const-string v14, "deleted %d item from MediaProvider"

    invoke-static {v3, v14, v13}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    if-lez v12, :cond_8

    invoke-interface {v6, v11}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    invoke-direct {v1, v10}, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->delete(Ljava/io/File;)Z

    move-result v10

    goto :goto_7

    :cond_8
    move v9, v5

    goto :goto_8

    :cond_9
    invoke-direct {v1, v10}, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->delete(Ljava/io/File;)Z

    move-result v10

    :goto_7
    and-int/2addr v9, v10

    :goto_8
    add-int/lit8 v8, v8, 0x1

    goto :goto_6

    :cond_a
    return v9

    :cond_b
    return v5

    :cond_c
    new-instance v0, Ljava/lang/NullPointerException;

    const-string v2, "file can\'t be null"

    invoke-direct {v0, v2}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, [Landroid/app/job/JobParameters;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->doInBackground([Landroid/app/job/JobParameters;)Ljava/lang/Void;

    move-result-object p1

    return-object p1
.end method

.method protected varargs doInBackground([Landroid/app/job/JobParameters;)Ljava/lang/Void;
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;

    const/4 v1, 0x0

    aget-object v2, p1, v1

    invoke-static {v0, v2}, Lcom/miui/gallery/editor/photo/sdk/CleanService;->access$100(Lcom/miui/gallery/editor/photo/sdk/CleanService;Landroid/app/job/JobParameters;)Ljava/util/List;

    move-result-object v0

    const-string v2, "CleanService.CleanTask"

    const-string v3, "start clean files: %s"

    invoke-static {v2, v3, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    move v3, v1

    :cond_0
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/io/File;

    invoke-virtual {v4}, Ljava/io/File;->exists()Z

    move-result v5

    if-eqz v5, :cond_0

    invoke-direct {p0, v4}, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->delete(Ljava/io/File;)Z

    move-result v4

    xor-int/lit8 v4, v4, 0x1

    or-int/2addr v3, v4

    goto :goto_0

    :cond_1
    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    const-string v4, "job finish, reschedule ? %b"

    invoke-static {v2, v4, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;

    aget-object p1, p1, v1

    invoke-virtual {v0, p1, v3}, Lcom/miui/gallery/editor/photo/sdk/CleanService;->jobFinished(Landroid/app/job/JobParameters;Z)V

    const/4 p1, 0x0

    return-object p1
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->onPostExecute(Ljava/lang/Void;)V

    return-void
.end method

.method protected onPostExecute(Ljava/lang/Void;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onPostExecute(Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;->this$0:Lcom/miui/gallery/editor/photo/sdk/CleanService;

    const/4 v0, 0x0

    invoke-static {p1, v0}, Lcom/miui/gallery/editor/photo/sdk/CleanService;->access$202(Lcom/miui/gallery/editor/photo/sdk/CleanService;Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;)Lcom/miui/gallery/editor/photo/sdk/CleanService$CleanTask;

    return-void
.end method
