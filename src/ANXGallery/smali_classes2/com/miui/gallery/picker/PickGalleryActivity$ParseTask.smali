.class Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;
.super Landroid/os/AsyncTask;
.source "PickGalleryActivity.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/picker/PickGalleryActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "ParseTask"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/os/AsyncTask<",
        "Ljava/lang/Void;",
        "Ljava/lang/Void;",
        "Landroid/database/Cursor;",
        ">;"
    }
.end annotation


# instance fields
.field private mPickSha1Results:Ljava/lang/String;

.field final synthetic this$0:Lcom/miui/gallery/picker/PickGalleryActivity;


# direct methods
.method constructor <init>(Lcom/miui/gallery/picker/PickGalleryActivity;Lcom/miui/gallery/picker/helper/Picker;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    const-string p1, "\',\'"

    invoke-static {p1, p2}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->mPickSha1Results:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method protected varargs doInBackground([Ljava/lang/Void;)Landroid/database/Cursor;
    .locals 6

    iget-object p1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-virtual {p1}, Lcom/miui/gallery/picker/PickGalleryActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    sget-object p1, Lcom/miui/gallery/provider/GalleryContract$Media;->URI_PICKER:Landroid/net/Uri;

    const-string v1, "sha1"

    const/4 v2, 0x0

    invoke-static {p1, v1, v2}, Lcom/miui/gallery/util/UriUtil;->appendGroupBy(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    sget-object v2, Lcom/miui/gallery/picker/PickerActivity;->PICKABLE_PROJECTION:[Ljava/lang/String;

    const/4 p1, 0x1

    new-array v3, p1, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->mPickSha1Results:Ljava/lang/String;

    const/4 v5, 0x0

    aput-object v4, v3, v5

    const-string v4, "sha1 IN (\'%s\') AND (localGroupId!=-1000) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus=\'custom\'))"

    invoke-static {v4, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    new-array p1, p1, [Ljava/lang/Object;

    iget-object v4, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->mPickSha1Results:Ljava/lang/String;

    aput-object v4, p1, v5

    const-string v4, "INSTR(\"\'%s\'\", sha1)"

    invoke-static {v4, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    const/4 v4, 0x0

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p1

    if-eqz p1, :cond_0

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    :cond_0
    return-object p1
.end method

.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, [Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->doInBackground([Ljava/lang/Void;)Landroid/database/Cursor;

    move-result-object p1

    return-object p1
.end method

.method protected onPostExecute(Landroid/database/Cursor;)V
    .locals 12

    const-string v0, "PickGalleryActivity"

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    const/4 v2, 0x0

    invoke-static {v1, v2}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$302(Lcom/miui/gallery/picker/PickGalleryActivity;Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;)Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;

    if-eqz p1, :cond_8

    :try_start_0
    const-string v1, "Picker capacity: %d, result %d items"

    iget-object v2, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-virtual {v2}, Lcom/miui/gallery/picker/PickGalleryActivity;->getPicker()Lcom/miui/gallery/picker/helper/Picker;

    move-result-object v2

    invoke-interface {v2}, Lcom/miui/gallery/picker/helper/Picker;->capacity()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-virtual {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->getPicker()Lcom/miui/gallery/picker/helper/Picker;

    move-result-object v1

    invoke-interface {v1}, Lcom/miui/gallery/picker/helper/Picker;->getResultType()Lcom/miui/gallery/picker/helper/Picker$ResultType;

    move-result-object v1

    sget-object v2, Lcom/miui/gallery/picker/helper/Picker$ResultType;->ID:Lcom/miui/gallery/picker/helper/Picker$ResultType;

    const/4 v3, 0x0

    if-ne v1, v2, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$400(Lcom/miui/gallery/picker/PickGalleryActivity;)Landroid/content/Intent;

    move-result-object v1

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    move v4, v3

    :cond_0
    :goto_0
    invoke-interface {p1}, Landroid/database/Cursor;->moveToNext()Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-interface {p1, v3}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v7

    invoke-static {v7, v8}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    invoke-virtual {v2, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/assistant/cache/ImageFeatureCacheManager;->getInstance()Lcom/miui/gallery/assistant/cache/ImageFeatureCacheManager;

    move-result-object v6

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-virtual/range {v6 .. v11}, Lcom/miui/gallery/assistant/cache/ImageFeatureCacheManager;->isBestImage(JZZLjava/util/List;)Z

    move-result v5

    if-eqz v5, :cond_0

    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_1
    iget-object v3, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result v5

    invoke-static {v3, v5, v4}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$500(Lcom/miui/gallery/picker/PickGalleryActivity;II)V

    const-string v3, "pick-result-data"

    invoke-virtual {v1, v3, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;

    iget-object v2, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v2, v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$600(Lcom/miui/gallery/picker/PickGalleryActivity;Landroid/content/Intent;)V

    goto/16 :goto_2

    :cond_2
    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1, p1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$700(Lcom/miui/gallery/picker/PickGalleryActivity;Landroid/database/Cursor;)V

    const-string v1, "picked file: %d, pending: %d"

    iget-object v2, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v2}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$800(Lcom/miui/gallery/picker/PickGalleryActivity;)[Landroid/net/Uri;

    move-result-object v2

    array-length v2, v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    iget-object v4, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v4}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$900(Lcom/miui/gallery/picker/PickGalleryActivity;)Ljava/util/ArrayList;

    move-result-object v4

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v4

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    invoke-static {v0, v1, v2, v4}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$900(Lcom/miui/gallery/picker/PickGalleryActivity;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_3

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$1000(Lcom/miui/gallery/picker/PickGalleryActivity;)V

    goto/16 :goto_2

    :cond_3
    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1}, Lcom/miui/gallery/util/SyncUtil;->existXiaomiAccount(Landroid/content/Context;)Z

    move-result v1

    const/4 v2, 0x1

    if-nez v1, :cond_4

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-virtual {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v4, 0x7f0e004a

    iget-object v5, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v5}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$900(Lcom/miui/gallery/picker/PickGalleryActivity;)Ljava/util/ArrayList;

    move-result-object v5

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v5

    new-array v2, v2, [Ljava/lang/Object;

    iget-object v6, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v6}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$900(Lcom/miui/gallery/picker/PickGalleryActivity;)Ljava/util/ArrayList;

    move-result-object v6

    invoke-virtual {v6}, Ljava/util/ArrayList;->size()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v2, v3

    invoke-virtual {v1, v4, v5, v2}, Landroid/content/res/Resources;->getQuantityString(II[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v2, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$1000(Lcom/miui/gallery/picker/PickGalleryActivity;)V

    goto :goto_2

    :cond_4
    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v1

    if-eqz v1, :cond_5

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1, v3}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$1100(Lcom/miui/gallery/picker/PickGalleryActivity;Z)V

    goto :goto_2

    :cond_5
    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isNetworkConnected()Z

    move-result v1

    if-eqz v1, :cond_7

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$800(Lcom/miui/gallery/picker/PickGalleryActivity;)[Landroid/net/Uri;

    move-result-object v1

    array-length v1, v1

    if-nez v1, :cond_6

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-virtual {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f0e0043

    iget-object v3, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v3}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$900(Lcom/miui/gallery/picker/PickGalleryActivity;)Ljava/util/ArrayList;

    move-result-object v3

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    invoke-virtual {v1, v2, v3}, Landroid/content/res/Resources;->getQuantityString(II)Ljava/lang/String;

    move-result-object v1

    goto :goto_1

    :cond_6
    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-virtual {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v4, 0x7f1006c1

    new-array v2, v2, [Ljava/lang/Object;

    iget-object v5, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v5}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$900(Lcom/miui/gallery/picker/PickGalleryActivity;)Ljava/util/ArrayList;

    move-result-object v5

    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v5

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    aput-object v5, v2, v3

    invoke-virtual {v1, v4, v2}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    :goto_1
    iget-object v2, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v2, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$1200(Lcom/miui/gallery/picker/PickGalleryActivity;)V

    goto :goto_2

    :cond_7
    iget-object v1, p0, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->this$0:Lcom/miui/gallery/picker/PickGalleryActivity;

    invoke-static {v1, v2}, Lcom/miui/gallery/picker/PickGalleryActivity;->access$1100(Lcom/miui/gallery/picker/PickGalleryActivity;Z)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_2

    :catchall_0
    move-exception v0

    goto :goto_3

    :catch_0
    move-exception v1

    :try_start_1
    const-string v2, "Parse failed %s"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :goto_2
    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    return-void

    :goto_3
    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    throw v0

    :cond_8
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string v0, "return a null cursor"

    invoke-direct {p1, v0}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Landroid/database/Cursor;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/picker/PickGalleryActivity$ParseTask;->onPostExecute(Landroid/database/Cursor;)V

    return-void
.end method
