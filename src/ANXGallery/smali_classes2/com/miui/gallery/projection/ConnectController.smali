.class public abstract Lcom/miui/gallery/projection/ConnectController;
.super Ljava/lang/Object;
.source "ConnectController.java"

# interfaces
.implements Lcom/miui/gallery/projection/IConnectController;


# instance fields
.field private mConnectTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

.field protected mConnectedDevice:Ljava/util/Map$Entry;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map$Entry<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mConnectingDevice:Ljava/util/Map$Entry;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map$Entry<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private mCurrentIndex:I

.field private mCurrentPhoto:Ljava/lang/String;

.field private mDataSource:Lcom/milink/api/v1/MilinkClientManagerDataSource;

.field private mDelegate:Lcom/milink/api/v1/MilinkClientManagerDelegate;

.field private mIsOpen:Z

.field private mMediaPlayListener:Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

.field protected mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

.field private mSlidingWindow:Lcom/miui/gallery/projection/SlidingWindow;

.field private mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

.field private final mStatusLock:Ljava/lang/Object;

.field private mToConnectDevice:Ljava/util/Map$Entry;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map$Entry<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/miui/gallery/projection/SlidingWindow;

    invoke-direct {v0}, Lcom/miui/gallery/projection/SlidingWindow;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mSlidingWindow:Lcom/miui/gallery/projection/SlidingWindow;

    new-instance v0, Lcom/miui/gallery/projection/ConnectController$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/projection/ConnectController$1;-><init>(Lcom/miui/gallery/projection/ConnectController;)V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mDataSource:Lcom/milink/api/v1/MilinkClientManagerDataSource;

    const-string v0, ""

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentPhoto:Ljava/lang/String;

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentIndex:I

    new-instance v1, Ljava/lang/Object;

    invoke-direct {v1}, Ljava/lang/Object;-><init>()V

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    iput-boolean v0, p0, Lcom/miui/gallery/projection/ConnectController;->mIsOpen:Z

    new-instance v1, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v1, v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>(Z)V

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

    new-instance v0, Lcom/miui/gallery/projection/ConnectController$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/projection/ConnectController$2;-><init>(Lcom/miui/gallery/projection/ConnectController;)V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mDelegate:Lcom/milink/api/v1/MilinkClientManagerDelegate;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/SlidingWindow;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/projection/ConnectController;->mSlidingWindow:Lcom/miui/gallery/projection/SlidingWindow;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/projection/ConnectController;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/projection/ConnectController;->setIsOpen(Z)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/projection/ConnectController;)Ljava/util/concurrent/atomic/AtomicBoolean;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/projection/ConnectController;)Z
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->connectRenderer()Z

    move-result p0

    return p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/projection/ConnectController;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/projection/ConnectController;->setIsConnected(Z)V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/gallery/projection/ConnectController;)Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/projection/ConnectController;->mMediaPlayListener:Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    return-object p0
.end method

.method private connectRenderer()Z
    .locals 6

    const-string v0, "project_IConnectController"

    const-string v1, "connectrender"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->isOpen()Z

    move-result v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-nez v1, :cond_0

    const-string v1, "connect before open"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectTillOpen:Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-virtual {v0, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->set(Z)V

    return v3

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mToConnectDevice:Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    if-nez v1, :cond_1

    const-string v4, "device not in devices map"

    invoke-static {v0, v4}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectController;->isConnected()Z

    move-result v4

    if-eqz v4, :cond_2

    const-string v4, "connected to another device"

    invoke-static {v0, v4}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->disconnectRenderer()Z

    move-result v4

    if-nez v4, :cond_2

    const-string v1, "could not disconnect to previous device"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return v3

    :cond_2
    iget-object v4, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    const/16 v5, 0x1770

    invoke-virtual {v4, v1, v5}, Lcom/milink/api/v1/MilinkClientManager;->connect(Ljava/lang/String;I)Lcom/milink/api/v1/type/ReturnCode;

    move-result-object v1

    sget-object v4, Lcom/milink/api/v1/type/ReturnCode;->OK:Lcom/milink/api/v1/type/ReturnCode;

    if-eq v1, v4, :cond_3

    const-string v1, "connect error "

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    return v3

    :cond_3
    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->setConnectingDevice()V

    return v2
.end method

.method private disconnectRenderer()Z
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    invoke-direct {p0, v1}, Lcom/miui/gallery/projection/ConnectController;->setIsConnected(Z)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-virtual {v0}, Lcom/milink/api/v1/MilinkClientManager;->stopShow()Lcom/milink/api/v1/type/ReturnCode;

    move-result-object v0

    sget-object v2, Lcom/milink/api/v1/type/ReturnCode;->OK:Lcom/milink/api/v1/type/ReturnCode;

    const-string v3, "project_IConnectController"

    if-eq v0, v2, :cond_0

    const-string v0, "stop show failed"

    invoke-static {v3, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return v1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-virtual {v0}, Lcom/milink/api/v1/MilinkClientManager;->disconnect()Lcom/milink/api/v1/type/ReturnCode;

    move-result-object v0

    sget-object v2, Lcom/milink/api/v1/type/ReturnCode;->OK:Lcom/milink/api/v1/type/ReturnCode;

    if-eq v0, v2, :cond_1

    const-string v0, "disconnect renderer failed"

    invoke-static {v3, v0}, Lcom/miui/gallery/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)V

    return v1

    :cond_1
    const/4 v0, 0x1

    return v0

    :cond_2
    return v1
.end method

.method private doOnShowError()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "connection is broken without informing gallery"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectController;->closeService()V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    const v1, 0x7f1006d8

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    const-string v0, "photo"

    const-string v1, "project_photo_fail"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private isOpen()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-boolean v1, p0, Lcom/miui/gallery/projection/ConnectController;->mIsOpen:Z

    monitor-exit v0

    return v1

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method private setConnectedDevice(Z)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    monitor-enter v0

    const/4 v1, 0x0

    if-eqz p1, :cond_1

    :try_start_0
    iget-object v2, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectingDevice:Ljava/util/Map$Entry;

    if-nez v2, :cond_0

    monitor-exit v0

    return-void

    :cond_0
    iget-object v2, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectingDevice:Ljava/util/Map$Entry;

    iput-object v2, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectedDevice:Ljava/util/Map$Entry;

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectingDevice:Ljava/util/Map$Entry;

    goto :goto_0

    :cond_1
    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mToConnectDevice:Ljava/util/Map$Entry;

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectingDevice:Ljava/util/Map$Entry;

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectedDevice:Ljava/util/Map$Entry;

    :goto_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

    if-eqz v0, :cond_3

    if-eqz p1, :cond_2

    const/4 p1, 0x1

    invoke-interface {v0, p1}, Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;->onConnectStatusChanged(I)V

    goto :goto_1

    :cond_2
    const/4 p1, -0x1

    invoke-interface {v0, p1}, Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;->onConnectStatusChanged(I)V

    :cond_3
    :goto_1
    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method private setConnectingDevice()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mToConnectDevice:Ljava/util/Map$Entry;

    if-nez v1, :cond_0

    monitor-exit v0

    return-void

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mToConnectDevice:Ljava/util/Map$Entry;

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectingDevice:Ljava/util/Map$Entry;

    const/4 v1, 0x0

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mToConnectDevice:Ljava/util/Map$Entry;

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

    if-eqz v0, :cond_1

    const/4 v1, 0x0

    invoke-interface {v0, v1}, Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;->onConnectStatusChanged(I)V

    :cond_1
    return-void

    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method private setIsConnected(Z)V
    .locals 3

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    const-string v1, "project_IConnectController"

    const-string v2, "setisconnected %s"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-direct {p0, p1}, Lcom/miui/gallery/projection/ConnectController;->setConnectedDevice(Z)V

    return-void
.end method

.method private setIsOpen(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iput-boolean p1, p0, Lcom/miui/gallery/projection/ConnectController;->mIsOpen:Z

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

    if-eqz v0, :cond_0

    invoke-interface {v0, p1}, Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;->onOpenStatusChanged(Z)V

    :cond_0
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
.method public attachActivity(Landroid/app/Activity;)V
    .locals 0

    return-void
.end method

.method protected abstract chooseDevice()Z
.end method

.method public closeService()V
    .locals 3

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/miui/gallery/projection/ConnectController;->setIsConnected(Z)V

    invoke-direct {p0, v0}, Lcom/miui/gallery/projection/ConnectController;->setIsOpen(Z)V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mMediaPlayListener:Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->disconnectRenderer()Z

    monitor-enter p0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-eqz v1, :cond_0

    const-string v1, "project_IConnectController"

    const-string v2, "close milink service"

    invoke-static {v1, v2}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-virtual {v1, v0}, Lcom/milink/api/v1/MilinkClientManager;->setDelegate(Lcom/milink/api/v1/MilinkClientManagerDelegate;)V

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-virtual {v1}, Lcom/milink/api/v1/MilinkClientManager;->close()V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    :cond_0
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public detachActivity(Landroid/app/Activity;)V
    .locals 0

    return-void
.end method

.method protected doOnConnected()V
    .locals 3

    const-string v0, "project_IConnectController"

    const-string v1, "connect is responded ok"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-virtual {v1}, Lcom/milink/api/v1/MilinkClientManager;->startShow()Lcom/milink/api/v1/type/ReturnCode;

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentPhoto:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "==the to show photo is: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v2, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentPhoto:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentPhoto:Ljava/lang/String;

    iget v1, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentIndex:I

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/projection/ConnectController;->showPhoto(Ljava/lang/String;I)V

    :cond_0
    const-string v0, "photo"

    const-string v1, "project_photo_success"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method protected doOnConnectedFail()V
    .locals 2

    const-string v0, "project_IConnectController"

    const-string v1, "renderer is failed to connect"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    const v1, 0x7f1006d8

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    const-string v0, "photo"

    const-string v1, "project_photo_fail"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method protected abstract doOnDeviceFound(Ljava/lang/String;Ljava/lang/String;Lcom/milink/api/v1/type/DeviceType;)V
.end method

.method protected abstract doOnDeviceLost(Ljava/lang/String;)V
.end method

.method protected abstract doOnOpen()V
.end method

.method public getConnectStatus()I
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectedDevice:Ljava/util/Map$Entry;

    if-eqz v1, :cond_0

    const/4 v1, 0x1

    monitor-exit v0

    return v1

    :cond_0
    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectingDevice:Ljava/util/Map$Entry;

    if-eqz v1, :cond_1

    const/4 v1, 0x0

    monitor-exit v0

    return v1

    :cond_1
    const/4 v1, -0x1

    monitor-exit v0

    return v1

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public getConnectedDevice()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectedDevice:Ljava/util/Map$Entry;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    :goto_0
    return-object v0
.end method

.method public getConnectingDevice()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectingDevice:Ljava/util/Map$Entry;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    :goto_0
    return-object v0
.end method

.method public getCurrentPosition()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/milink/api/v1/MilinkClientManager;->getPlaybackProgress()I

    move-result v0

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method public getDuration()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/milink/api/v1/MilinkClientManager;->getPlaybackDuration()I

    move-result v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public isConnected()Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mConnectedDevice:Ljava/util/Map$Entry;

    if-eqz v1, :cond_0

    const/4 v1, 0x1

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    monitor-exit v0

    return v1

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public isPlaying()Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/milink/api/v1/MilinkClientManager;->getPlaybackRate()I

    move-result v0

    const/4 v2, 0x1

    if-ne v0, v2, :cond_0

    move v1, v2

    :cond_0
    return v1
.end method

.method protected open()V
    .locals 2

    monitor-enter p0

    :try_start_0
    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->isOpen()Z

    move-result v0

    if-eqz v0, :cond_0

    monitor-exit p0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mDelegate:Lcom/milink/api/v1/MilinkClientManagerDelegate;

    if-nez v0, :cond_1

    monitor-exit p0

    return-void

    :cond_1
    new-instance v0, Lcom/milink/api/v1/MilinkClientManager;

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/milink/api/v1/MilinkClientManager;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/util/BuildUtil;->getDeviceName(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/milink/api/v1/MilinkClientManager;->setDeviceName(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mDataSource:Lcom/milink/api/v1/MilinkClientManagerDataSource;

    invoke-virtual {v0, v1}, Lcom/milink/api/v1/MilinkClientManager;->setDataSource(Lcom/milink/api/v1/MilinkClientManagerDataSource;)V

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mDelegate:Lcom/milink/api/v1/MilinkClientManagerDelegate;

    invoke-virtual {v0, v1}, Lcom/milink/api/v1/MilinkClientManager;->setDelegate(Lcom/milink/api/v1/MilinkClientManagerDelegate;)V

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-virtual {v0}, Lcom/milink/api/v1/MilinkClientManager;->open()V

    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public pause()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-eqz v0, :cond_0

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/milink/api/v1/MilinkClientManager;->setPlaybackRate(I)Lcom/milink/api/v1/type/ReturnCode;

    :cond_0
    return-void
.end method

.method public playVideo(Ljava/lang/String;Ljava/lang/String;)V
    .locals 8

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    :try_start_0
    const-string v0, "UTF-8"

    invoke-static {p1, v0}, Ljava/net/URLEncoder;->encode(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    iget-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    const/4 v4, 0x0

    const-wide/16 v5, 0x0

    sget-object v7, Lcom/milink/api/v1/type/MediaType;->Video:Lcom/milink/api/v1/type/MediaType;

    move-object v3, p2

    invoke-virtual/range {v1 .. v7}, Lcom/milink/api/v1/MilinkClientManager;->startPlay(Ljava/lang/String;Ljava/lang/String;IDLcom/milink/api/v1/type/MediaType;)Lcom/milink/api/v1/type/ReturnCode;
    :try_end_0
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    invoke-virtual {p1}, Ljava/io/UnsupportedEncodingException;->printStackTrace()V

    :cond_1
    :goto_0
    return-void
.end method

.method public project()V
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectController;->isConnected()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->disconnectRenderer()Z

    return-void

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->isOpen()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectController;->open()V

    :cond_1
    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectController;->chooseDevice()Z

    return-void
.end method

.method public registerMediaPlayListener(Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;)V
    .locals 0

    if-eqz p1, :cond_0

    iput-object p1, p0, Lcom/miui/gallery/projection/ConnectController;->mMediaPlayListener:Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    :cond_0
    return-void
.end method

.method public registerStatusListener(Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;)V
    .locals 0

    if-eqz p1, :cond_0

    iput-object p1, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

    :cond_0
    return-void
.end method

.method public resume()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-eqz v0, :cond_0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lcom/milink/api/v1/MilinkClientManager;->setPlaybackRate(I)Lcom/milink/api/v1/type/ReturnCode;

    :cond_0
    return-void
.end method

.method public seekTo(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/milink/api/v1/MilinkClientManager;->setPlaybackProgress(I)Lcom/milink/api/v1/type/ReturnCode;

    :cond_0
    return-void
.end method

.method protected setToConnectDevice(Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusLock:Ljava/lang/Object;

    monitor-enter v0

    :try_start_0
    const-string v1, "project_IConnectController"

    const-string v2, "set toconnectdevice"

    invoke-static {v1, v2}, Lcom/miui/gallery/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v1, Ljava/util/AbstractMap$SimpleEntry;

    invoke-direct {v1, p1, p2}, Ljava/util/AbstractMap$SimpleEntry;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    iput-object v1, p0, Lcom/miui/gallery/projection/ConnectController;->mToConnectDevice:Ljava/util/Map$Entry;

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->connectRenderer()Z

    monitor-exit v0

    return-void

    :catchall_0
    move-exception p1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1
.end method

.method public showPhoto(Ljava/lang/String;I)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectController;->isConnected()Z

    move-result v0

    const-string v1, "project_IConnectController"

    if-nez v0, :cond_0

    const-string p1, "no renderer connected"

    invoke-static {v1, p1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    const-string v0, "the photo is: %s %d "

    invoke-static {v1, v0, p1, p2}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p2

    if-nez p2, :cond_2

    :try_start_0
    iget-object p2, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    invoke-virtual {p2, p1}, Lcom/milink/api/v1/MilinkClientManager;->show(Ljava/lang/String;)Lcom/milink/api/v1/type/ReturnCode;

    move-result-object p1

    sget-object p2, Lcom/milink/api/v1/type/ReturnCode;->OK:Lcom/milink/api/v1/type/ReturnCode;

    if-ne p1, p2, :cond_1

    return-void

    :cond_1
    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->doOnShowError()V
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string p2, "MilinkClientManager show exception"

    invoke-static {v1, p2, p1}, Lcom/miui/gallery/util/Log;->v(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :cond_2
    :goto_0
    return-void
.end method

.method public showSlide(I)Z
    .locals 2

    iput p1, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentIndex:I

    iget-object p1, p0, Lcom/miui/gallery/projection/ConnectController;->mSlidingWindow:Lcom/miui/gallery/projection/SlidingWindow;

    iget v0, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentIndex:I

    invoke-virtual {p1, v0}, Lcom/miui/gallery/projection/SlidingWindow;->onCurrentIndexChanged(I)V

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$SlideShow;->getSlideShowInterval()I

    move-result p1

    mul-int/lit16 p1, p1, 0x3e8

    const/16 v0, 0xbb8

    invoke-static {v0, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    sget-object v1, Lcom/milink/api/v1/type/SlideMode;->Recyle:Lcom/milink/api/v1/type/SlideMode;

    invoke-virtual {v0, p1, v1}, Lcom/milink/api/v1/MilinkClientManager;->startSlideshow(ILcom/milink/api/v1/type/SlideMode;)Lcom/milink/api/v1/type/ReturnCode;

    move-result-object p1

    sget-object v0, Lcom/milink/api/v1/type/ReturnCode;->OK:Lcom/milink/api/v1/type/ReturnCode;

    if-eq p1, v0, :cond_0

    invoke-direct {p0}, Lcom/miui/gallery/projection/ConnectController;->doOnShowError()V

    const/4 p1, 0x0

    return p1

    :cond_0
    const/4 p1, 0x1

    return p1
.end method

.method public stop()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/milink/api/v1/MilinkClientManager;->stopPlay()Lcom/milink/api/v1/type/ReturnCode;

    :cond_0
    return-void
.end method

.method public syncRemoteView(FFFFFFF)V
    .locals 10

    move-object v0, p0

    iget-object v1, v0, Lcom/miui/gallery/projection/ConnectController;->mPhotoManager:Lcom/milink/api/v1/MilinkClientManager;

    iget-object v2, v0, Lcom/miui/gallery/projection/ConnectController;->mCurrentPhoto:Ljava/lang/String;

    move v3, p1

    float-to-int v3, v3

    move v4, p2

    float-to-int v4, v4

    move v5, p3

    float-to-int v5, v5

    move v6, p4

    float-to-int v6, v6

    move v7, p5

    float-to-int v7, v7

    move/from16 v8, p6

    float-to-int v8, v8

    move/from16 v9, p7

    invoke-virtual/range {v1 .. v9}, Lcom/milink/api/v1/MilinkClientManager;->zoomPhoto(Ljava/lang/String;IIIIIIF)Lcom/milink/api/v1/type/ReturnCode;

    return-void
.end method

.method public unregisterMediaPlayListener(Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mMediaPlayListener:Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    if-ne p1, v0, :cond_0

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/projection/ConnectController;->mMediaPlayListener:Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;

    :cond_0
    return-void
.end method

.method public unregisterStatusListener(Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

    if-ne p1, v0, :cond_0

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/gallery/projection/ConnectController;->mStatusListener:Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;

    :cond_0
    return-void
.end method

.method public updateCurrentFolder(Lcom/miui/gallery/projection/IConnectController$DataSet;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mSlidingWindow:Lcom/miui/gallery/projection/SlidingWindow;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/projection/SlidingWindow;->setMediaSet(Lcom/miui/gallery/projection/IConnectController$DataSet;)V

    return-void
.end method

.method public updateCurrentPhoto(Ljava/lang/String;I)V
    .locals 2

    iput-object p1, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentPhoto:Ljava/lang/String;

    iput p2, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentIndex:I

    invoke-virtual {p0}, Lcom/miui/gallery/projection/ConnectController;->getConnectedDevice()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/projection/ConnectController;->mSlidingWindow:Lcom/miui/gallery/projection/SlidingWindow;

    iget v1, p0, Lcom/miui/gallery/projection/ConnectController;->mCurrentIndex:I

    invoke-virtual {v0, v1}, Lcom/miui/gallery/projection/SlidingWindow;->onCurrentIndexChanged(I)V

    invoke-virtual {p0, p1, p2}, Lcom/miui/gallery/projection/ConnectController;->showPhoto(Ljava/lang/String;I)V

    :cond_0
    return-void
.end method
