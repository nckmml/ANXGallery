.class public Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;
.super Ljava/lang/Object;
.source "MiShareGalleryConnectivity.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;
    }
.end annotation


# static fields
.field private static sInstance:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;


# instance fields
.field private mCallback:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;

.field private final mConnection:Landroid/content/ServiceConnection;

.field private mContext:Landroid/content/Context;

.field private mIsBound:Z

.field private mService:Lcom/miui/mishare/IMiShareService;


# direct methods
.method private constructor <init>(Landroid/content/Context;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$1;

    invoke-direct {v0, p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$1;-><init>(Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;)V

    iput-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mConnection:Landroid/content/ServiceConnection;

    iput-object p1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mContext:Landroid/content/Context;

    return-void
.end method

.method static synthetic access$002(Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;Lcom/miui/mishare/IMiShareService;)Lcom/miui/mishare/IMiShareService;
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    return-object p1
.end method

.method static synthetic access$100(Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;)Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mCallback:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;

    return-object p0
.end method

.method private ensureServiceBound()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    if-eqz v0, :cond_0

    return-void

    :cond_0
    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "service not bound"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private getClipData(Ljava/util/List;)Landroid/content/ClipData;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroid/net/Uri;",
            ">;)",
            "Landroid/content/ClipData;"
        }
    .end annotation

    if-eqz p1, :cond_3

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    if-gtz v0, :cond_0

    goto :goto_1

    :cond_0
    const/4 v0, 0x0

    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/Uri;

    new-instance v1, Landroid/content/ClipData$Item;

    invoke-direct {v1, v0}, Landroid/content/ClipData$Item;-><init>(Landroid/net/Uri;)V

    new-instance v0, Landroid/content/ClipDescription;

    const-string v2, ""

    filled-new-array {v2}, [Ljava/lang/String;

    move-result-object v2

    const-string v3, "mishare data"

    invoke-direct {v0, v3, v2}, Landroid/content/ClipDescription;-><init>(Ljava/lang/CharSequence;[Ljava/lang/String;)V

    new-instance v2, Landroid/content/ClipData;

    invoke-direct {v2, v0, v1}, Landroid/content/ClipData;-><init>(Landroid/content/ClipDescription;Landroid/content/ClipData$Item;)V

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    const/4 v1, 0x1

    :goto_0
    if-ge v1, v0, :cond_2

    invoke-interface {p1, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Landroid/net/Uri;

    if-eqz v3, :cond_1

    new-instance v4, Landroid/content/ClipData$Item;

    invoke-direct {v4, v3}, Landroid/content/ClipData$Item;-><init>(Landroid/net/Uri;)V

    invoke-virtual {v2, v4}, Landroid/content/ClipData;->addItem(Landroid/content/ClipData$Item;)V

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    return-object v2

    :cond_3
    :goto_1
    const/4 p1, 0x0

    return-object p1
.end method

.method public static getInstance(Landroid/content/Context;)Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;
    .locals 1

    sget-object v0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->sInstance:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    invoke-virtual {p0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object p0

    invoke-direct {v0, p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;-><init>(Landroid/content/Context;)V

    sput-object v0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->sInstance:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    :cond_0
    sget-object p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->sInstance:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    return-object p0
.end method

.method public static isAvailable(Landroid/content/Context;)Z
    .locals 3

    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    const-string v1, "com.miui.mishare.connectivity"

    const-string v2, "com.miui.mishare.connectivity.MiShareService"

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->setClassName(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object p0

    const/4 v1, 0x0

    if-nez p0, :cond_0

    return v1

    :cond_0
    invoke-virtual {p0, v0, v1}, Landroid/content/pm/PackageManager;->queryIntentServices(Landroid/content/Intent;I)Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p0

    if-lez p0, :cond_1

    const/4 v1, 0x1

    :cond_1
    return v1
.end method


# virtual methods
.method public bind(Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;)Z
    .locals 3

    iput-object p1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mCallback:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;

    new-instance p1, Landroid/content/Intent;

    invoke-direct {p1}, Landroid/content/Intent;-><init>()V

    const-string v0, "com.miui.mishare.connectivity"

    const-string v1, "com.miui.mishare.connectivity.MiShareService"

    invoke-virtual {p1, v0, v1}, Landroid/content/Intent;->setClassName(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mConnection:Landroid/content/ServiceConnection;

    const/4 v2, 0x1

    invoke-virtual {v0, p1, v1, v2}, Landroid/content/Context;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z

    move-result p1

    iput-boolean p1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mIsBound:Z

    iget-boolean p1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mIsBound:Z

    return p1
.end method

.method public cancel(Lcom/miui/mishare/MiShareTask;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    if-eqz v0, :cond_0

    :try_start_0
    invoke-interface {v0, p1}, Lcom/miui/mishare/IMiShareService;->cancel(Lcom/miui/mishare/MiShareTask;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "MiShareConnectivity"

    const-string v1, "cancel: "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :cond_0
    :goto_0
    return-void
.end method

.method public checkServiceBound()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public getServiceState()I
    .locals 4

    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    :try_start_0
    invoke-interface {v0}, Lcom/miui/mishare/IMiShareService;->getState()I

    move-result v0
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    return v0

    :catch_0
    move-exception v0

    const-string v2, "MiShareConnectivity"

    const-string v3, "getServiceState: "

    invoke-static {v2, v3, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    return v1
.end method

.method public registerStateListener(Lcom/miui/mishare/IMiShareStateListener;)V
    .locals 2

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    :try_start_0
    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    invoke-interface {v0, p1}, Lcom/miui/mishare/IMiShareService;->registerStateListener(Lcom/miui/mishare/IMiShareStateListener;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "MiShareConnectivity"

    const-string v1, "registerStateListener: "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_0
    return-void

    :cond_0
    new-instance p1, Ljava/lang/NullPointerException;

    const-string v0, "null listener"

    invoke-direct {p1, v0}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public registerTaskStateListener(Lcom/miui/mishare/IMiShareTaskStateListener;)V
    .locals 2

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    :try_start_0
    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    invoke-interface {v0, p1}, Lcom/miui/mishare/IMiShareService;->registerTaskStateListener(Lcom/miui/mishare/IMiShareTaskStateListener;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "MiShareConnectivity"

    const-string v1, "registerTaskStateListener: "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_0
    return-void
.end method

.method public sendData(Lcom/miui/mishare/MiShareTask;)V
    .locals 5

    if-eqz p1, :cond_5

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const/4 v1, 0x0

    :goto_0
    iget-object v2, p1, Lcom/miui/mishare/MiShareTask;->clipData:Landroid/content/ClipData;

    invoke-virtual {v2}, Landroid/content/ClipData;->getItemCount()I

    move-result v2

    if-ge v1, v2, :cond_3

    iget-object v2, p1, Lcom/miui/mishare/MiShareTask;->clipData:Landroid/content/ClipData;

    invoke-virtual {v2, v1}, Landroid/content/ClipData;->getItemAt(I)Landroid/content/ClipData$Item;

    move-result-object v2

    if-nez v2, :cond_0

    goto :goto_1

    :cond_0
    iget-object v2, p1, Lcom/miui/mishare/MiShareTask;->clipData:Landroid/content/ClipData;

    invoke-virtual {v2, v1}, Landroid/content/ClipData;->getItemAt(I)Landroid/content/ClipData$Item;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/ClipData$Item;->getUri()Landroid/net/Uri;

    move-result-object v2

    if-nez v2, :cond_1

    goto :goto_1

    :cond_1
    invoke-virtual {v2}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object v3

    const-string v4, "content"

    invoke-virtual {v3, v4}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    :goto_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_3
    new-instance v1, Landroid/content/Intent;

    const-string v2, "com.miui.mishare.action.SEND_TASK"

    invoke-direct {v1, v2}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const-string v2, "com.miui.mishare.connectivity"

    const-string v3, "com.miui.mishare.connectivity.MiShareService"

    invoke-virtual {v1, v2, v3}, Landroid/content/Intent;->setClassName(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-lez v2, :cond_4

    invoke-direct {p0, v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->getClipData(Ljava/util/List;)Landroid/content/ClipData;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/content/Intent;->setClipData(Landroid/content/ClipData;)V

    const v0, -0x7fffffff

    invoke-virtual {v1, v0}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    :cond_4
    const-string v0, "task"

    invoke-virtual {v1, v0, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    iget-object p1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mContext:Landroid/content/Context;

    invoke-virtual {p1, v1}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    return-void

    :cond_5
    new-instance p1, Ljava/lang/NullPointerException;

    const-string v0, "null task"

    invoke-direct {p1, v0}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public startDiscover(Lcom/miui/mishare/IMiShareDiscoverCallback;)V
    .locals 2

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    :try_start_0
    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    invoke-interface {v0, p1}, Lcom/miui/mishare/IMiShareService;->discover(Lcom/miui/mishare/IMiShareDiscoverCallback;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "MiShareConnectivity"

    const-string v1, "startDiscover: "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_0
    return-void
.end method

.method public startDiscoverWithIntent(Lcom/miui/mishare/IMiShareDiscoverCallback;Landroid/content/Intent;)V
    .locals 1

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    :try_start_0
    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    invoke-interface {v0, p1, p2}, Lcom/miui/mishare/IMiShareService;->discoverWithIntent(Lcom/miui/mishare/IMiShareDiscoverCallback;Landroid/content/Intent;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string p2, "MiShareConnectivity"

    const-string v0, "startDiscoverWithConfig: "

    invoke-static {p2, v0, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_0
    return-void
.end method

.method public stopDiscover(Lcom/miui/mishare/IMiShareDiscoverCallback;)V
    .locals 2

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    :try_start_0
    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    invoke-interface {v0, p1}, Lcom/miui/mishare/IMiShareService;->stopDiscover(Lcom/miui/mishare/IMiShareDiscoverCallback;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "MiShareConnectivity"

    const-string v1, "startDiscover: "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_0
    return-void
.end method

.method public unbind()V
    .locals 2

    iget-boolean v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mIsBound:Z

    if-eqz v0, :cond_1

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mCallback:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;

    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mConnection:Landroid/content/ServiceConnection;

    invoke-virtual {v0, v1}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mIsBound:Z

    :cond_1
    return-void
.end method

.method public unregisterStateListener(Lcom/miui/mishare/IMiShareStateListener;)V
    .locals 2

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    :try_start_0
    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    invoke-interface {v0, p1}, Lcom/miui/mishare/IMiShareService;->unregisterStateListener(Lcom/miui/mishare/IMiShareStateListener;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "MiShareConnectivity"

    const-string v1, "unregisterStateListener: "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_0
    return-void

    :cond_0
    new-instance p1, Ljava/lang/NullPointerException;

    const-string v0, "null listener"

    invoke-direct {p1, v0}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw p1
.end method

.method public unregisterTaskStateListener(Lcom/miui/mishare/IMiShareTaskStateListener;)V
    .locals 2

    invoke-direct {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->ensureServiceBound()V

    :try_start_0
    iget-object v0, p0, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->mService:Lcom/miui/mishare/IMiShareService;

    invoke-interface {v0, p1}, Lcom/miui/mishare/IMiShareService;->unregisterTaskStateListener(Lcom/miui/mishare/IMiShareTaskStateListener;)V
    :try_end_0
    .catch Landroid/os/RemoteException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "MiShareConnectivity"

    const-string v1, "unregisterTaskStateListener: "

    invoke-static {v0, v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    :goto_0
    return-void
.end method
