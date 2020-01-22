.class public Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;
.super Ljava/lang/Object;
.source "ResourceDownloadManager.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;
    }
.end annotation


# static fields
.field private static TAG:Ljava/lang/String; = "ResourceDownloadManager"


# instance fields
.field private mAssetTemplateListener:Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;

.field private mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

.field private mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

.field private mCommandState:I

.field private mContext:Landroid/content/Context;

.field private mDownloadManager:Lcom/miui/gallery/video/editor/manager/DownloadManager;

.field private mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

.field private mFragmentManager:Landroid/app/FragmentManager;

.field private mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

.field private mRequestListener:Lcom/miui/gallery/net/download/Request$Listener;

.field private mUnZipAsyncTask:Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;

.field private mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/app/FragmentManager;Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/16 v0, 0x13

    iput v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandState:I

    new-instance v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$2;-><init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mRequestListener:Lcom/miui/gallery/net/download/Request$Listener;

    new-instance v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$3;-><init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    new-instance v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;

    invoke-direct {v0, p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$4;-><init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mAssetTemplateListener:Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;

    iput-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mContext:Landroid/content/Context;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mFragmentManager:Landroid/app/FragmentManager;

    iput-object p3, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->buildCommand(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    return-void
.end method

.method static synthetic access$100()Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$200(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->unzipResource()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Lcom/miui/gallery/video/editor/DownloadCommand;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->onCommandFail(Lcom/miui/gallery/video/editor/DownloadCommand;)V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->onNextCommandRunning(Lcom/miui/gallery/video/editor/DownloadCommand;)V

    return-void
.end method

.method static synthetic access$600(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->allowResourceInstall()Z

    move-result p0

    return p0
.end method

.method static synthetic access$700(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->installResource()V

    return-void
.end method

.method static synthetic access$800(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->onCommandSuccess(Lcom/miui/gallery/video/editor/DownloadCommand;)V

    return-void
.end method

.method static synthetic access$900(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;)Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mContext:Landroid/content/Context;

    return-object p0
.end method

.method private allowResourceInstall()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/DownloadCommand;->isTemplate()Z

    move-result v0

    return v0
.end method

.method private buildCommand(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-direct {v0}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    :cond_0
    new-instance v0, Lcom/miui/gallery/video/editor/DownloadCommand;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mModuleFactory:Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;

    invoke-direct {v0, p1, p2, v1}, Lcom/miui/gallery/video/editor/DownloadCommand;-><init>(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;ILcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;)V

    iget-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->put(Lcom/miui/gallery/video/editor/DownloadCommand;)Z

    move-result p1

    sget-object p2, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {v1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->getCapacity()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "command num: %s "

    invoke-static {p2, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    if-eqz p1, :cond_1

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->onCommandStart(Lcom/miui/gallery/video/editor/DownloadCommand;)V

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->downloadResource()V

    :cond_1
    return-void
.end method

.method private confirmDownloadResource()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadManager:Lcom/miui/gallery/video/editor/manager/DownloadManager;

    if-nez v0, :cond_1

    new-instance v0, Lcom/miui/gallery/video/editor/manager/DownloadManager;

    invoke-direct {v0}, Lcom/miui/gallery/video/editor/manager/DownloadManager;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadManager:Lcom/miui/gallery/video/editor/manager/DownloadManager;

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->get()Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    const/16 v0, 0x11

    iput v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandState:I

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadManager:Lcom/miui/gallery/video/editor/manager/DownloadManager;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    iget-object v2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mRequestListener:Lcom/miui/gallery/net/download/Request$Listener;

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/video/editor/manager/DownloadManager;->download(Lcom/miui/gallery/video/editor/DownloadCommand;Lcom/miui/gallery/net/download/Request$Listener;)V

    return-void
.end method

.method private downloadResource()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->hasCommandRunning()Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    const-string v1, "the other command is running."

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->confirmDownloadResource()V

    return-void
.end method

.method private hasCommandRunning()Z
    .locals 2

    iget v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandState:I

    const/16 v1, 0x11

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method private hasCurrentCommand(J)Z
    .locals 4

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    iget-object v2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    if-eqz v2, :cond_2

    invoke-virtual {v2}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->getCapacity()I

    move-result v2

    if-ge v1, v2, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {v2, v1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->get(I)Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object v2

    if-nez v2, :cond_0

    goto :goto_1

    :cond_0
    invoke-virtual {v2}, Lcom/miui/gallery/video/editor/DownloadCommand;->getId()J

    move-result-wide v2

    cmp-long v2, v2, p1

    if-nez v2, :cond_1

    const/4 p1, 0x1

    return p1

    :cond_1
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    return v0
.end method

.method private installResource()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/video/editor/model/VideoEditorTemplateBaseModel;

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/model/VideoEditorTemplateBaseModel;->getAssetId()I

    move-result v1

    if-lez v1, :cond_1

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->getInstance()Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;

    move-result-object v1

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/model/VideoEditorTemplateBaseModel;->getAssetId()I

    move-result v0

    iget-object v2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mAssetTemplateListener:Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;

    invoke-virtual {v1, v0, v2}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->installProcess(ILcom/miui/gallery/video/editor/manager/NexAssetTemplateManager$ILoadAssetTemplate;)V

    :cond_1
    return-void
.end method

.method private onCommandFail(Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    sget-object v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->getLabel()Ljava/lang/String;

    move-result-object v1

    const-string v2, "the command %s is completed on fail. "

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v1

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getPosition()I

    move-result p1

    invoke-interface {v0, v1, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;->onCommandFail(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    :cond_0
    return-void
.end method

.method private onCommandStart(Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    if-eqz v0, :cond_1

    sget-object v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->getLabel()Ljava/lang/String;

    move-result-object v1

    const-string v2, "the command %s start. "

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->hasCommandRunning()Z

    move-result v0

    if-eqz v0, :cond_0

    iget v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandState:I

    goto :goto_0

    :cond_0
    const/16 v0, 0x10

    :goto_0
    iput v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandState:I

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v1

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getPosition()I

    move-result p1

    invoke-interface {v0, v1, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;->onCommandStart(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    :cond_1
    return-void
.end method

.method private onCommandSuccess(Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    sget-object v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->getLabel()Ljava/lang/String;

    move-result-object v1

    const-string v2, "the command %s is completed on success. "

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v1

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getPosition()I

    move-result p1

    invoke-interface {v0, v1, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;->onCommandSuccess(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    :cond_0
    return-void
.end method

.method private onNextCommandRunning(Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/16 v0, 0x12

    iput v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandState:I

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->remove(Lcom/miui/gallery/video/editor/DownloadCommand;)Z

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->confirmDownloadResource()V

    :cond_1
    :goto_0
    return-void
.end method

.method private onTaskCancel(Lcom/miui/gallery/video/editor/DownloadCommand;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    const/16 v1, 0x13

    iput v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandState:I

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getData()Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;

    move-result-object v1

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommand;->getPosition()I

    move-result p1

    invoke-interface {v0, v1, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;->onTaskCancel(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    :cond_0
    :goto_0
    iget-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->get()Lcom/miui/gallery/video/editor/DownloadCommand;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->onCommandFail(Lcom/miui/gallery/video/editor/DownloadCommand;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->remove(Lcom/miui/gallery/video/editor/DownloadCommand;)Z

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    const/4 v0, 0x0

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/DownloadCommandQueue;->clear()I

    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommandQueue:Lcom/miui/gallery/video/editor/DownloadCommandQueue;

    :cond_2
    iput-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    sget-object p1, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    const-string v0, "the task is completed. "

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private showNetworkConfirmDialog(Lcom/miui/gallery/ui/ConfirmDialog$ConfirmDialogInterface;)V
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mFragmentManager:Landroid/app/FragmentManager;

    iget-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f10089e

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const v3, 0x7f10089d

    invoke-virtual {v2, v3}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v2

    iget-object v3, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mContext:Landroid/content/Context;

    invoke-virtual {v3}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    const v4, 0x7f100896

    invoke-virtual {v3, v4}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v3

    iget-object v4, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mContext:Landroid/content/Context;

    invoke-virtual {v4}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    const v5, 0x7f10089b

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v4

    move-object v5, p1

    invoke-static/range {v0 .. v5}, Lcom/miui/gallery/ui/ConfirmDialog;->showConfirmDialog(Landroid/app/FragmentManager;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/ui/ConfirmDialog$ConfirmDialogInterface;)V

    return-void
.end method

.method private unzipResource()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    if-nez v0, :cond_0

    return-void

    :cond_0
    new-instance v1, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;

    iget-object v2, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mUnzipFileListener:Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;

    invoke-direct {v1, v0, v2}, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;-><init>(Lcom/miui/gallery/video/editor/DownloadCommand;Lcom/miui/gallery/video/editor/interfaces/IVideoEditorListener$IUnzipFileListener;)V

    iput-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mUnZipAsyncTask:Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mUnZipAsyncTask:Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Void;

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    return-void
.end method


# virtual methods
.method public cancel()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mCommand:Lcom/miui/gallery/video/editor/DownloadCommand;

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->onTaskCancel(Lcom/miui/gallery/video/editor/DownloadCommand;)V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadManager:Lcom/miui/gallery/video/editor/manager/DownloadManager;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/manager/DownloadManager;->cancelAll()V

    iput-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadManager:Lcom/miui/gallery/video/editor/manager/DownloadManager;

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mUnZipAsyncTask:Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->clearListener()V

    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mUnZipAsyncTask:Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;

    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Lcom/miui/gallery/video/editor/manager/UnZipAsyncTask;->cancel(Z)Z

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    if-eqz v0, :cond_2

    iput-object v1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    :cond_2
    return-void
.end method

.method public createDownloadCommand(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V
    .locals 4

    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isNetworkConnected()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mContext:Landroid/content/Context;

    const p2, 0x7f10089c

    invoke-static {p1, p2}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    goto :goto_0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloud/NetworkUtils;->isActiveNetworkMetered()Z

    move-result v0

    const-string v1, "the command has exist."

    if-eqz v0, :cond_2

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->getId()J

    move-result-wide v2

    invoke-direct {p0, v2, v3}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->hasCurrentCommand(J)Z

    move-result v0

    if-eqz v0, :cond_1

    sget-object p1, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    invoke-static {p1, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_1
    new-instance v0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;

    invoke-direct {v0, p0, p1, p2}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$1;-><init>(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->showNetworkConfirmDialog(Lcom/miui/gallery/ui/ConfirmDialog$ConfirmDialogInterface;)V

    goto :goto_0

    :cond_2
    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;->getId()J

    move-result-wide v2

    invoke-direct {p0, v2, v3}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->hasCurrentCommand(J)Z

    move-result v0

    if-eqz v0, :cond_3

    sget-object p1, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->TAG:Ljava/lang/String;

    invoke-static {p1, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_3
    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->buildCommand(Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;I)V

    :goto_0
    return-void
.end method

.method public setDownloadTaskListener(Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/net/ResourceDownloadManager;->mDownloadTaskListener:Lcom/miui/gallery/video/editor/net/ResourceDownloadManager$IDownloadTaskListener;

    return-void
.end method
