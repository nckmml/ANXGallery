.class Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;
.super Landroid/os/AsyncTask;
.source "AddRemoveSecretDialogFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "AddRemoveTask"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/os/AsyncTask<",
        "Ljava/lang/Void;",
        "Ljava/lang/Void;",
        "[J>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    return-void
.end method

.method private addOrRemove()[J
    .locals 4

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$300(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_1

    iget-object v1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$500(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)[J

    move-result-object v1

    if-nez v1, :cond_0

    iget-object v1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$600(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/provider/CloudUtils;->addToSecret(Landroid/content/Context;Ljava/util/ArrayList;)[J

    move-result-object v0

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$500(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)[J

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/provider/CloudUtils;->addToSecret(Landroid/content/Context;[J)[J

    move-result-object v0

    goto :goto_0

    :cond_1
    iget-object v1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$300(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)I

    move-result v1

    const/4 v2, 0x2

    if-ne v1, v2, :cond_2

    iget-object v1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$700(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)J

    move-result-wide v1

    iget-object v3, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v3}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$500(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)[J

    move-result-object v3

    invoke-static {v0, v1, v2, v3}, Lcom/miui/gallery/provider/CloudUtils;->removeFromSecret(Landroid/content/Context;J[J)[J

    move-result-object v0

    goto :goto_0

    :cond_2
    const/4 v0, 0x0

    :goto_0
    return-object v0
.end method

.method private getAddFailTip(Landroid/content/res/Resources;III)Ljava/lang/String;
    .locals 3

    const/16 v0, -0x6b

    if-ne p2, v0, :cond_0

    const p2, 0x7f100795

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_0
    invoke-direct {p0, p1, p2, p4}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->getFailReason(Landroid/content/res/Resources;II)Ljava/lang/String;

    move-result-object p2

    const/4 v0, 0x0

    const/4 v1, 0x1

    if-le p3, v1, :cond_1

    const p3, 0x7f0e000b

    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/Object;

    aput-object p2, v2, v0

    invoke-static {p4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    aput-object p2, v2, v1

    invoke-virtual {p1, p3, p4, v2}, Landroid/content/res/Resources;->getQuantityString(II[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_1
    const p3, 0x7f100038

    new-array p4, v1, [Ljava/lang/Object;

    aput-object p2, p4, v0

    invoke-virtual {p1, p3, p4}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private getFailReason(Landroid/content/res/Resources;II)Ljava/lang/String;
    .locals 1

    const/16 v0, -0x6f

    if-eq p2, v0, :cond_0

    packed-switch p2, :pswitch_data_0

    const p2, 0x7f100794

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :pswitch_0
    const p2, 0x7f100791

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :pswitch_1
    const p2, 0x7f100793

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :pswitch_2
    const p2, 0x7f100792

    invoke-virtual {p1, p2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_0
    const p2, 0x7f0e0036

    invoke-virtual {p1, p2, p3}, Landroid/content/res/Resources;->getQuantityString(II)Ljava/lang/String;

    move-result-object p1

    return-object p1

    nop

    :pswitch_data_0
    .packed-switch -0x6c
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method private outputResult([J)Landroid/util/Pair;
    .locals 20
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([J)",
            "Landroid/util/Pair<",
            "Ljava/lang/Boolean;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    iget-object v2, v0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-virtual {v2}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    const/4 v3, 0x0

    const/4 v4, 0x0

    if-nez v2, :cond_0

    new-instance v1, Landroid/util/Pair;

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-direct {v1, v2, v3}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v1

    :cond_0
    iget-object v2, v0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-virtual {v2}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v2

    invoke-virtual {v2}, Landroid/app/Activity;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    iget-object v5, v0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v5}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$300(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)I

    move-result v5

    const-wide/16 v8, -0x67

    const-wide/16 v10, 0x0

    const/4 v12, 0x1

    if-ne v5, v12, :cond_f

    if-eqz v1, :cond_d

    array-length v3, v1

    if-ge v3, v12, :cond_1

    goto/16 :goto_8

    :cond_1
    array-length v3, v1

    move v5, v4

    move v13, v5

    move v14, v13

    move v15, v14

    :goto_0
    if-ge v5, v3, :cond_6

    aget-wide v6, v1, v5

    cmp-long v18, v6, v10

    if-lez v18, :cond_2

    add-int/lit8 v13, v13, 0x1

    goto :goto_2

    :cond_2
    cmp-long v18, v6, v8

    if-eqz v18, :cond_4

    const-wide/16 v16, -0x68

    cmp-long v18, v6, v16

    if-nez v18, :cond_3

    goto :goto_1

    :cond_3
    add-int/lit8 v14, v14, 0x1

    if-nez v4, :cond_5

    long-to-int v4, v6

    goto :goto_2

    :cond_4
    :goto_1
    add-int/lit8 v15, v15, 0x1

    :cond_5
    :goto_2
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    :cond_6
    array-length v3, v1

    const v5, 0x7f10049c

    const v6, 0x7f100039

    if-le v3, v12, :cond_9

    if-eqz v14, :cond_7

    array-length v1, v1

    invoke-direct {v0, v2, v4, v1, v14}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->getAddFailTip(Landroid/content/res/Resources;III)Ljava/lang/String;

    move-result-object v1

    :goto_3
    move-object v3, v1

    goto :goto_6

    :cond_7
    iget-object v1, v0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$400(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)Z

    move-result v1

    if-eqz v1, :cond_8

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Secret;->isFirstAddSecretVideo()Z

    move-result v1

    if-eqz v1, :cond_8

    goto :goto_4

    :cond_8
    move v5, v6

    :goto_4
    invoke-virtual {v2, v5}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_3

    :cond_9
    if-ne v13, v12, :cond_b

    iget-object v1, v0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$400(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)Z

    move-result v1

    if-eqz v1, :cond_a

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Secret;->isFirstAddSecretVideo()Z

    move-result v1

    if-eqz v1, :cond_a

    goto :goto_5

    :cond_a
    move v5, v6

    :goto_5
    invoke-virtual {v2, v5}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_3

    :cond_b
    if-ne v15, v12, :cond_c

    const v1, 0x7f100036

    invoke-virtual {v2, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_3

    :cond_c
    array-length v1, v1

    invoke-direct {v0, v2, v4, v1, v14}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->getAddFailTip(Landroid/content/res/Resources;III)Ljava/lang/String;

    move-result-object v1

    goto :goto_3

    :goto_6
    if-lez v13, :cond_e

    :goto_7
    move v4, v12

    goto/16 :goto_12

    :cond_d
    :goto_8
    iget-object v1, v0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    const v2, 0x7f100037

    invoke-virtual {v1, v2}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getString(I)Ljava/lang/String;

    move-result-object v3

    :cond_e
    const/4 v4, 0x0

    goto/16 :goto_12

    :cond_f
    iget-object v4, v0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v4}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$300(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)I

    move-result v4

    const/4 v5, 0x2

    if-ne v4, v5, :cond_1c

    if-eqz v1, :cond_1b

    array-length v3, v1

    if-ge v3, v12, :cond_10

    goto/16 :goto_10

    :cond_10
    array-length v3, v1

    const/4 v4, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    :goto_9
    if-ge v4, v3, :cond_15

    move/from16 v18, v6

    aget-wide v5, v1, v4

    cmp-long v19, v5, v10

    if-lez v19, :cond_11

    add-int/lit8 v13, v13, 0x1

    move/from16 v6, v18

    const-wide/16 v16, -0x68

    goto :goto_c

    :cond_11
    cmp-long v19, v5, v8

    if-eqz v19, :cond_13

    const-wide/16 v16, -0x68

    cmp-long v19, v5, v16

    if-nez v19, :cond_12

    goto :goto_a

    :cond_12
    add-int/lit8 v18, v18, 0x1

    if-nez v7, :cond_14

    long-to-int v5, v5

    move v7, v5

    goto :goto_b

    :cond_13
    const-wide/16 v16, -0x68

    :goto_a
    add-int/lit8 v14, v14, 0x1

    :cond_14
    :goto_b
    move/from16 v6, v18

    :goto_c
    add-int/lit8 v4, v4, 0x1

    const/4 v5, 0x2

    goto :goto_9

    :cond_15
    move/from16 v18, v6

    if-lez v18, :cond_16

    move/from16 v6, v18

    invoke-direct {v0, v2, v7, v6}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->getFailReason(Landroid/content/res/Resources;II)Ljava/lang/String;

    move-result-object v3

    goto :goto_d

    :cond_16
    move/from16 v6, v18

    const-string v3, ""

    :goto_d
    array-length v1, v1

    const v4, 0x7f100719

    if-le v1, v12, :cond_18

    if-eqz v6, :cond_17

    const v1, 0x7f0e004d

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v3, v4, v5

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v4, v12

    invoke-virtual {v2, v1, v6, v4}, Landroid/content/res/Resources;->getQuantityString(II[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    goto :goto_e

    :cond_17
    invoke-virtual {v2, v4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_e

    :cond_18
    if-ne v13, v12, :cond_19

    invoke-virtual {v2, v4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    :goto_e
    move-object v3, v1

    const/4 v5, 0x0

    goto :goto_f

    :cond_19
    if-ne v14, v12, :cond_1a

    const v1, 0x7f100030

    invoke-virtual {v2, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    goto :goto_e

    :cond_1a
    const v1, 0x7f100718

    new-array v4, v12, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v3, v4, v5

    invoke-virtual {v2, v1, v4}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    move-object v3, v1

    :goto_f
    if-lez v13, :cond_1d

    goto/16 :goto_7

    :cond_1b
    :goto_10
    const/4 v5, 0x0

    const v1, 0x7f100717

    invoke-virtual {v2, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v3

    goto :goto_11

    :cond_1c
    const/4 v5, 0x0

    :cond_1d
    :goto_11
    move v4, v5

    :goto_12
    new-instance v1, Landroid/util/Pair;

    invoke-static {v4}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    invoke-direct {v1, v2, v3}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v1
.end method


# virtual methods
.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, [Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->doInBackground([Ljava/lang/Void;)[J

    move-result-object p1

    return-object p1
.end method

.method protected varargs doInBackground([Ljava/lang/Void;)[J
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->addOrRemove()[J

    move-result-object p1

    return-object p1
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, [J

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->onPostExecute([J)V

    return-void
.end method

.method protected onPostExecute([J)V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$200(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)Lcom/miui/gallery/util/MediaAndAlbumOperations$OnCompleteListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {v0}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$200(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)Lcom/miui/gallery/util/MediaAndAlbumOperations$OnCompleteListener;

    move-result-object v0

    invoke-interface {v0, p1}, Lcom/miui/gallery/util/MediaAndAlbumOperations$OnCompleteListener;->onComplete([J)V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object v0

    if-nez v0, :cond_1

    return-void

    :cond_1
    invoke-direct {p0, p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->outputResult([J)Landroid/util/Pair;

    move-result-object p1

    iget-object v0, p1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v0, Ljava/lang/String;

    iget-object p1, p1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    const/4 v1, 0x1

    const/4 v2, 0x0

    if-eqz p1, :cond_5

    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$300(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)I

    move-result p1

    if-ne p1, v1, :cond_5

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Secret;->isFirstAddSecret()Z

    move-result p1

    if-eqz p1, :cond_3

    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$400(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Secret;->isFirstAddSecretVideo()Z

    move-result p1

    if-eqz p1, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    const v0, 0x7f10049c

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v2}, Lcom/miui/gallery/preference/GalleryPreferences$Secret;->setFirstAddSecretVideo(Z)V

    goto :goto_0

    :cond_2
    move v1, v2

    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/ui/JumpDialogFragment;->enterPrivateAlbum(Landroid/app/Activity;)V

    goto :goto_2

    :cond_3
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_4

    const/4 p1, 0x3

    new-array p1, p1, [Ljava/lang/Object;

    aput-object v0, p1, v2

    iget-object v0, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    const v3, 0x7f1005d0

    invoke-virtual {v0, v3}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getString(I)Ljava/lang/String;

    move-result-object v0

    aput-object v0, p1, v1

    const/4 v0, 0x2

    iget-object v3, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    const v4, 0x7f100087

    invoke-virtual {v3, v4}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getString(I)Ljava/lang/String;

    move-result-object v3

    aput-object v3, p1, v0

    const-string v0, "%s%s%s"

    invoke-static {v0, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    goto :goto_1

    :cond_4
    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    const v0, 0x7f100086

    invoke-virtual {p1, v0}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getString(I)Ljava/lang/String;

    move-result-object p1

    :goto_1
    move-object v0, p1

    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-static {p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->access$400(Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;)Z

    move-result p1

    if-eqz p1, :cond_6

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$Secret;->isFirstAddSecretVideo()Z

    move-result p1

    if-eqz p1, :cond_6

    invoke-static {v2}, Lcom/miui/gallery/preference/GalleryPreferences$Secret;->setFirstAddSecretVideo(Z)V

    goto :goto_2

    :cond_5
    move v1, v2

    :cond_6
    :goto_2
    if-eqz v0, :cond_7

    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)V

    :cond_7
    iget-object p1, p0, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment$AddRemoveTask;->this$0:Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;

    invoke-virtual {p1}, Lcom/miui/gallery/ui/AddRemoveSecretDialogFragment;->dismissAllowingStateLoss()V

    return-void
.end method
