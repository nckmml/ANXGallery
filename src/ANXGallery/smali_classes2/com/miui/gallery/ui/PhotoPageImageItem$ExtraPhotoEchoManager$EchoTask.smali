.class Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;
.super Landroid/os/AsyncTask;
.source "PhotoPageImageItem.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "EchoTask"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/os/AsyncTask<",
        "Ljava/lang/Void;",
        "Ljava/lang/Void;",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# instance fields
.field private mEchoListener:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;

.field private mPath:Ljava/lang/String;

.field final synthetic this$1:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->this$1:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mPath:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public cancel()V
    .locals 2

    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->cancel(Z)Z

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mEchoListener:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->getInstance()Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mEchoListener:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;

    invoke-virtual {v0, v1}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->unregisterEchoListener(Lcom/miui/extraphoto/sdk/BaseEchoListener;)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mEchoListener:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;

    :cond_0
    return-void
.end method

.method protected varargs doInBackground([Ljava/lang/Void;)Ljava/lang/Boolean;
    .locals 1

    invoke-static {}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->getInstance()Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mPath:Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->needEcho(Ljava/lang/String;)Z

    move-result p1

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method

.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, [Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->doInBackground([Ljava/lang/Void;)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method

.method protected onPostExecute(Ljava/lang/Boolean;)V
    .locals 3

    invoke-virtual {p0}, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->isCancelled()Z

    move-result v0

    if-nez v0, :cond_0

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mPath:Ljava/lang/String;

    const-string v0, "PhotoPageImageItem"

    const-string v1, "need echo %s"

    invoke-static {v0, v1, p1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->this$1:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;

    invoke-static {p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;->access$1100(Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;)V

    new-instance p1, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->this$1:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;

    const/4 v1, 0x0

    invoke-direct {p1, v0, v1}, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;-><init>(Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager;Lcom/miui/gallery/ui/PhotoPageImageItem$1;)V

    iput-object p1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mEchoListener:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;

    invoke-static {}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->getInstance()Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mPath:Ljava/lang/String;

    iget-object v1, p0, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->mEchoListener:Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoListener;

    const/4 v2, 0x1

    invoke-virtual {p1, v0, v1, v2}, Lcom/miui/extraphoto/sdk/ExtraPhotoWrapper;->echo(Ljava/lang/String;Lcom/miui/extraphoto/sdk/BaseEchoListener;Z)Z

    :cond_0
    return-void
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/ui/PhotoPageImageItem$ExtraPhotoEchoManager$EchoTask;->onPostExecute(Ljava/lang/Boolean;)V

    return-void
.end method
