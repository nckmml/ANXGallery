.class public Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;
.super Landroidx/recyclerview/widget/RecyclerView$Adapter;
.source "MiShareGalleryDeviceAdapter.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;,
        Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;,
        Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$IdleHandler;,
        Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;,
        Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroidx/recyclerview/widget/RecyclerView$Adapter<",
        "Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;",
        ">;",
        "Landroid/view/View$OnClickListener;"
    }
.end annotation


# instance fields
.field private mComparator:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;

.field private mContext:Landroid/content/Context;

.field private mDevices:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/mishare/app/model/MiShareDevice;",
            ">;"
        }
    .end annotation
.end field

.field private mDialog:Lmiui/app/AlertDialog;

.field private mHandler:Landroid/os/Handler;

.field private mIsInitialAddDevice:Z

.field private mListener:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

.field private final mReceiver:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;

.field private mSortHandler:Landroid/os/Handler;

.field private final mSortRunnable:Ljava/lang/Runnable;

.field private mStopSort:Z


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$Adapter;-><init>()V

    new-instance v0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;

    invoke-direct {v0, p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;-><init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V

    iput-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mReceiver:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;

    new-instance v0, Landroid/os/Handler;

    invoke-direct {v0}, Landroid/os/Handler;-><init>()V

    iput-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mSortHandler:Landroid/os/Handler;

    new-instance v0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;-><init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;)V

    iput-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mComparator:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mIsInitialAddDevice:Z

    new-instance v0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;

    invoke-direct {v0, p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;-><init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V

    iput-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mSortRunnable:Ljava/lang/Runnable;

    new-instance v0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$IdleHandler;

    invoke-direct {v0, p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$IdleHandler;-><init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V

    iput-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mHandler:Landroid/os/Handler;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    iput-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    new-instance p1, Landroid/content/IntentFilter;

    const-string v0, "com.miui.mishare.NO_NEED_CANCEL"

    invoke-direct {p1, v0}, Landroid/content/IntentFilter;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mReceiver:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;

    invoke-virtual {v0, v1, p1}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    return-void
.end method

.method static synthetic access$100(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mStopSort:Z

    return p0
.end method

.method static synthetic access$200(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$300(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mComparator:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->startSort()V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mListener:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lmiui/app/AlertDialog;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDialog:Lmiui/app/AlertDialog;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    return-object p0
.end method

.method private delayIdle(Ljava/lang/String;I)V
    .locals 2

    const/4 v0, 0x4

    if-eq p2, v0, :cond_0

    const/4 v0, 0x3

    if-ne p2, v0, :cond_1

    :cond_0
    new-instance p2, Landroid/os/Message;

    invoke-direct {p2}, Landroid/os/Message;-><init>()V

    const/4 v0, 0x1

    iput v0, p2, Landroid/os/Message;->what:I

    iput-object p1, p2, Landroid/os/Message;->obj:Ljava/lang/Object;

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mHandler:Landroid/os/Handler;

    const-wide/16 v0, 0xbb8

    invoke-virtual {p1, p2, v0, v1}, Landroid/os/Handler;->sendMessageDelayed(Landroid/os/Message;J)Z

    :cond_1
    return-void
.end method

.method private showCancelConfirm(Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 5

    if-eqz p1, :cond_1

    iget-object v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    if-eqz v0, :cond_1

    iget-object v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    invoke-virtual {v0}, Lcom/miui/mishare/RemoteDevice;->getExtras()Landroid/os/Bundle;

    move-result-object v0

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    iget-object v1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->files:Ljava/util/List;

    invoke-static {v0, v1}, Lcom/miui/mishare/app/util/MiShareFileUtil;->isAllImageOverview(Landroid/content/Context;Ljava/util/List;)Z

    move-result v0

    iget-object v1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    invoke-virtual {v1}, Lcom/miui/mishare/RemoteDevice;->getExtras()Landroid/os/Bundle;

    move-result-object v1

    const-string v2, "nickname"

    invoke-virtual {v1, v2}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v4, Lcom/miui/mishare/R$string;->confirm_cancel_task_images:I

    new-array v2, v2, [Ljava/lang/Object;

    aput-object v1, v2, v3

    invoke-virtual {v0, v4, v2}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v4, Lcom/miui/mishare/R$string;->confirm_cancel_task_files:I

    new-array v2, v2, [Ljava/lang/Object;

    aput-object v1, v2, v3

    invoke-virtual {v0, v4, v2}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    :goto_0
    new-instance v1, Lmiui/app/AlertDialog$Builder;

    iget-object v2, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    invoke-direct {v1, v2}, Lmiui/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {v1, v0}, Lmiui/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Lmiui/app/AlertDialog$Builder;

    move-result-object v0

    invoke-virtual {v0, v3}, Lmiui/app/AlertDialog$Builder;->setCancelable(Z)Lmiui/app/AlertDialog$Builder;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/mishare/R$string;->btn_cancel:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Lmiui/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/mishare/R$string;->btn_sure:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;

    invoke-direct {v2, p0, p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$2;-><init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;Lcom/miui/mishare/app/model/MiShareDevice;)V

    invoke-virtual {v0, v1, v2}, Lmiui/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lmiui/app/AlertDialog$Builder;->create()Lmiui/app/AlertDialog;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDialog:Lmiui/app/AlertDialog;

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDialog:Lmiui/app/AlertDialog;

    invoke-virtual {p1}, Lmiui/app/AlertDialog;->show()V

    :cond_1
    return-void
.end method

.method private startSort()V
    .locals 4

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mStopSort:Z

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mSortHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mSortRunnable:Ljava/lang/Runnable;

    const-wide/16 v2, 0x7d0

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    return-void
.end method


# virtual methods
.method public addOrUpdateDevice(Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 5

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    iget-object v2, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    if-ge v1, v2, :cond_1

    iget-object v2, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/mishare/app/model/MiShareDevice;

    iget-object v3, v2, Lcom/miui/mishare/app/model/MiShareDevice;->deviceId:Ljava/lang/String;

    iget-object v4, p1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceId:Ljava/lang/String;

    invoke-static {v3, v4}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_0

    iget v0, v2, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    iput v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    iget-object v0, v2, Lcom/miui/mishare/app/model/MiShareDevice;->taskId:Ljava/lang/String;

    iput-object v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->taskId:Ljava/lang/String;

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v0, v1, p1}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    return-void

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v1, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    iget-boolean p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mIsInitialAddDevice:Z

    if-eqz p1, :cond_2

    iput-boolean v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mIsInitialAddDevice:Z

    invoke-direct {p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->startSort()V

    :cond_2
    return-void
.end method

.method public devicesEmpty()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    if-eqz v0, :cond_1

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 v0, 0x1

    :goto_1
    return v0
.end method

.method public getDeviceById(Ljava/lang/String;)Lcom/miui/mishare/app/model/MiShareDevice;
    .locals 4

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return-object v1

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/mishare/app/model/MiShareDevice;

    if-eqz v2, :cond_1

    iget-object v3, v2, Lcom/miui/mishare/app/model/MiShareDevice;->deviceId:Ljava/lang/String;

    invoke-static {v3, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_1

    return-object v2

    :cond_2
    return-object v1
.end method

.method public getItem(I)Lcom/miui/mishare/app/model/MiShareDevice;
    .locals 1

    if-ltz p1, :cond_0

    invoke-virtual {p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->getItemCount()I

    move-result v0

    if-ge p1, v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/mishare/app/model/MiShareDevice;

    return-object p1

    :cond_0
    const/4 p1, 0x0

    return-object p1
.end method

.method public getItemCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public getItemId(I)J
    .locals 2

    const-wide/16 v0, 0x0

    return-wide v0
.end method

.method public bridge synthetic onBindViewHolder(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V
    .locals 0

    check-cast p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;

    invoke-virtual {p0, p1, p2}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->onBindViewHolder(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;I)V

    return-void
.end method

.method public onBindViewHolder(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;I)V
    .locals 6

    invoke-virtual {p0, p2}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->getItem(I)Lcom/miui/mishare/app/model/MiShareDevice;

    move-result-object v0

    const/4 v1, 0x0

    const/4 v2, 0x1

    if-eqz v0, :cond_2

    iget-object v3, v0, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    if-eqz v3, :cond_0

    iget-object v3, v0, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    invoke-virtual {v3}, Lcom/miui/mishare/RemoteDevice;->getExtras()Landroid/os/Bundle;

    move-result-object v3

    if-eqz v3, :cond_0

    iget-object v3, v0, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    invoke-virtual {v3}, Lcom/miui/mishare/RemoteDevice;->getExtras()Landroid/os/Bundle;

    move-result-object v3

    const-string v4, "nickname_has_more"

    invoke-virtual {v3, v4}, Landroid/os/Bundle;->getBoolean(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_0

    move v3, v2

    goto :goto_0

    :cond_0
    move v3, v1

    :goto_0
    iget-object v4, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    iget v5, v0, Lcom/miui/mishare/app/model/MiShareDevice;->deviceType:I

    invoke-virtual {v4, v5}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->setDeviceType(I)V

    iget-object v4, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    iget-object v5, v0, Lcom/miui/mishare/app/model/MiShareDevice;->deviceName:Ljava/lang/String;

    invoke-virtual {v4, v5, v3}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->setDeviceName(Ljava/lang/String;Z)V

    iget-object v3, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    iget v4, v0, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    invoke-virtual {v3, v4}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->setDeviceStatus(I)V

    invoke-virtual {v0}, Lcom/miui/mishare/app/model/MiShareDevice;->isPC()Z

    move-result v3

    xor-int/2addr v3, v2

    iget-object v4, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    invoke-virtual {v4, v3}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->setDevicePhoneOrPc(Z)V

    if-eqz v3, :cond_1

    iget-object v3, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    iget-object v0, v0, Lcom/miui/mishare/app/model/MiShareDevice;->deviceModelName:Ljava/lang/String;

    invoke-virtual {v3, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->setDeviceModelName(Ljava/lang/String;)V

    goto :goto_1

    :cond_1
    iget-object v0, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    iget-object v3, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    sget v4, Lcom/miui/mishare/R$string;->pc_device_model_name:I

    invoke-virtual {v3, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->setDeviceModelName(Ljava/lang/String;)V

    :cond_2
    :goto_1
    iget-object v0, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    invoke-virtual {v0, v2}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->showDivider(Z)V

    iget-object v0, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    if-nez p2, :cond_3

    move v3, v2

    goto :goto_2

    :cond_3
    move v3, v1

    :goto_2
    invoke-virtual {v0, v3}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->showDividerStart(Z)V

    iget-object v0, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    invoke-virtual {p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->getItemCount()I

    move-result v3

    sub-int/2addr v3, v2

    if-ne p2, v3, :cond_4

    move v1, v2

    :cond_4
    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->showDividerEnd(Z)V

    iget-object v0, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    invoke-virtual {v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->getIconView()Landroid/view/View;

    move-result-object v0

    sget v1, Lcom/miui/mishare/R$id;->position:I

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-virtual {v0, v1, p2}, Landroid/view/View;->setTag(ILjava/lang/Object;)V

    iget-object p1, p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    invoke-virtual {p1}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->getIconView()Landroid/view/View;

    move-result-object p1

    invoke-virtual {p1, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 2

    sget v0, Lcom/miui/mishare/R$id;->position:I

    invoke-virtual {p1, v0}, Landroid/view/View;->getTag(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-virtual {p0, p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->getItem(I)Lcom/miui/mishare/app/model/MiShareDevice;

    move-result-object p1

    if-eqz p1, :cond_4

    iget v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    const/4 v1, 0x1

    if-eq v0, v1, :cond_3

    const/4 v1, 0x2

    if-eq v0, v1, :cond_2

    const/4 v1, 0x3

    if-eq v0, v1, :cond_1

    const/4 v1, 0x4

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mListener:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

    if-eqz v0, :cond_4

    invoke-interface {v0, p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;->onDeviceTaskStart(Lcom/miui/mishare/app/model/MiShareDevice;)V

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mListener:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

    if-eqz v0, :cond_4

    invoke-interface {v0, p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;->onDeviceTaskRetry(Lcom/miui/mishare/app/model/MiShareDevice;)V

    goto :goto_0

    :cond_2
    invoke-direct {p0, p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->showCancelConfirm(Lcom/miui/mishare/app/model/MiShareDevice;)V

    goto :goto_0

    :cond_3
    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mListener:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

    if-eqz v0, :cond_4

    invoke-interface {v0, p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;->onDeviceTaskStart(Lcom/miui/mishare/app/model/MiShareDevice;)V

    :cond_4
    :goto_0
    return-void
.end method

.method public bridge synthetic onCreateViewHolder(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;

    move-result-object p1

    return-object p1
.end method

.method public onCreateViewHolder(Landroid/view/ViewGroup;I)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;
    .locals 1

    new-instance p1, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;

    new-instance p2, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    invoke-direct {p2, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;-><init>(Landroid/content/Context;)V

    invoke-direct {p1, p2}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;)V

    return-object p1
.end method

.method public registerListener(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mListener:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;

    return-void
.end method

.method public removeDevice(Ljava/lang/String;)V
    .locals 2

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_1

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/mishare/app/model/MiShareDevice;

    iget-object v1, v1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceId:Ljava/lang/String;

    invoke-static {v1, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {p1, v0}, Ljava/util/List;->remove(I)Ljava/lang/Object;

    return-void

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public replaceTaskId(Lcom/miui/mishare/MiShareTask;)V
    .locals 4

    if-eqz p1, :cond_1

    iget-object v0, p1, Lcom/miui/mishare/MiShareTask;->device:Lcom/miui/mishare/RemoteDevice;

    if-eqz v0, :cond_1

    const/4 v0, 0x0

    :goto_0
    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_1

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/mishare/app/model/MiShareDevice;

    iget-object v2, v1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceId:Ljava/lang/String;

    iget-object v3, p1, Lcom/miui/mishare/MiShareTask;->device:Lcom/miui/mishare/RemoteDevice;

    invoke-virtual {v3}, Lcom/miui/mishare/RemoteDevice;->getDeviceId()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    iget-object p1, p1, Lcom/miui/mishare/MiShareTask;->taskId:Ljava/lang/String;

    iput-object p1, v1, Lcom/miui/mishare/app/model/MiShareDevice;->taskId:Ljava/lang/String;

    return-void

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public setDeviceState(Ljava/lang/String;I)V
    .locals 3

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mDevices:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/mishare/app/model/MiShareDevice;

    if-eqz v1, :cond_0

    iget-object v2, v1, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    if-eqz v2, :cond_0

    iget-object v2, v1, Lcom/miui/mishare/app/model/MiShareDevice;->taskId:Ljava/lang/String;

    invoke-static {p1, v2}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    iget v0, v1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    const/4 v2, 0x2

    if-ne v0, v2, :cond_1

    const/4 v0, 0x1

    if-ne p2, v0, :cond_1

    goto :goto_0

    :cond_1
    iput p2, v1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    invoke-virtual {p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->notifyDataSetChanged()V

    invoke-direct {p0, p1, p2}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->delayIdle(Ljava/lang/String;I)V

    :cond_2
    :goto_0
    return-void
.end method

.method public setInitialAddDevice()V
    .locals 1

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mIsInitialAddDevice:Z

    return-void
.end method

.method public stopSort()V
    .locals 2

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mStopSort:Z

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mSortHandler:Landroid/os/Handler;

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mSortRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    return-void
.end method

.method public unregister()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mContext:Landroid/content/Context;

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->mReceiver:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;

    invoke-virtual {v0, v1}, Landroid/content/Context;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    return-void
.end method
