.class public Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;
.super Landroid/os/AsyncTask;
.source "UnZipAsyncTask.java"


# annotations
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
.field private mData:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

.field private mUnZipPath:Ljava/lang/String;

.field private mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

.field private mZipPath:Ljava/lang/String;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/video/editor/DownloadCommand;Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;)V
    .locals 1

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mData:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getZipPath()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mZipPath:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getUnzipPath()Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnZipPath:Ljava/lang/String;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    return-void
.end method


# virtual methods
.method public clearListener()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    invoke-interface {v0, v1}, Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;->onUnzipFileFailed(Z)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    :cond_0
    return-void
.end method

.method protected varargs doInBackground([Ljava/lang/Void;)Ljava/lang/Boolean;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->isCancelled()Z

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mZipPath:Ljava/lang/String;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnZipPath:Ljava/lang/String;

    invoke-static {p1, v0}, Lcom/miui/gallery/video/editor/util/FileHelper;->unZipFile(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnZipPath:Ljava/lang/String;

    iget-object p1, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnZipPath:Ljava/lang/String;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mData:Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnZipPath:Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->setUnZipPath(Ljava/lang/String;)V

    const/4 p1, 0x1

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    :goto_0
    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method

.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, [Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->doInBackground([Ljava/lang/Void;)Ljava/lang/Boolean;

    move-result-object p1

    return-object p1
.end method

.method protected onPostExecute(Ljava/lang/Boolean;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    invoke-interface {p1}, Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;->onUnzipFileSuccess()V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    const/4 v0, 0x0

    invoke-interface {p1, v0}, Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;->onUnzipFileFailed(Z)V

    :goto_0
    return-void
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->onPostExecute(Ljava/lang/Boolean;)V

    return-void
.end method
