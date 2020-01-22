.class public Lcom/miui/mishare/app/view/MiShareGalleryTransferView;
.super Landroid/widget/LinearLayout;
.source "MiShareGalleryTransferView.java"

# interfaces
.implements Landroid/view/View$OnClickListener;
.implements Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;
.implements Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;,
        Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;
    }
.end annotation


# instance fields
.field private mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

.field private mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

.field private mCurrentDeviceStatus:I

.field private mDeviceId:Ljava/lang/String;

.field private mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

.field private final mDiscoverCallback:Lcom/miui/mishare/IMiShareDiscoverCallback$Stub;

.field private mDividerView:Landroid/view/View;

.field private mEnableMiShareView:Landroid/view/View;

.field private mFiles:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Landroid/net/Uri;",
            ">;"
        }
    .end annotation
.end field

.field private mHandler:Landroid/os/Handler;

.field private mHasNoFilesListener:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;

.field private mIntent:Landroid/content/Intent;

.field private mIsPrint:Z

.field private mMiPrintClick:Landroid/view/View$OnClickListener;

.field private mMiShareDisabled:Landroid/view/View;

.field private mPrintBtn:Landroid/widget/ImageButton;

.field private mPrintEnabled:Z

.field private mScreenThrow:Landroid/widget/ImageButton;

.field private mScreenThrowClick:Landroid/view/View$OnClickListener;

.field private mShareTaskStateReceive:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;

.field private mState:I

.field private mStateListener:Lcom/miui/mishare/IMiShareStateListener;

.field private final mTaskStateListener:Lcom/miui/mishare/IMiShareTaskStateListener$Stub;

.field private mTipsTv:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mState:I

    new-instance p1, Landroid/os/Handler;

    invoke-direct {p1}, Landroid/os/Handler;-><init>()V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mHandler:Landroid/os/Handler;

    new-instance p1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;

    invoke-direct {p1, p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mStateListener:Lcom/miui/mishare/IMiShareStateListener;

    new-instance p1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    invoke-direct {p1, p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDiscoverCallback:Lcom/miui/mishare/IMiShareDiscoverCallback$Stub;

    new-instance p1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;

    invoke-direct {p1, p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mTaskStateListener:Lcom/miui/mishare/IMiShareTaskStateListener$Stub;

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->init()V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mState:I

    new-instance p1, Landroid/os/Handler;

    invoke-direct {p1}, Landroid/os/Handler;-><init>()V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mHandler:Landroid/os/Handler;

    new-instance p1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;

    invoke-direct {p1, p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mStateListener:Lcom/miui/mishare/IMiShareStateListener;

    new-instance p1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;

    invoke-direct {p1, p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$2;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDiscoverCallback:Lcom/miui/mishare/IMiShareDiscoverCallback$Stub;

    new-instance p1, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;

    invoke-direct {p1, p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$3;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mTaskStateListener:Lcom/miui/mishare/IMiShareTaskStateListener$Stub;

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->init()V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)I
    .locals 0

    iget p0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mState:I

    return p0
.end method

.method static synthetic access$1000(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroidx/recyclerview/widget/RecyclerView;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

    return-object p0
.end method

.method static synthetic access$102(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;I)I
    .locals 0

    iput p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mState:I

    return p1
.end method

.method static synthetic access$200(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;II)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->refreshView(II)V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setDiscoveringView()V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroid/os/Handler;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mHandler:Landroid/os/Handler;

    return-object p0
.end method

.method static synthetic access$600(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDividerView:Landroid/view/View;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$800(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;B)I
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getDeviceType(B)I

    move-result p0

    return p0
.end method

.method static synthetic access$900(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;)Landroid/view/View;
    .locals 0

    iget-object p0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mMiShareDisabled:Landroid/view/View;

    return-object p0
.end method

.method private canPrint()Z
    .locals 4

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v2, 0x1

    if-gt v0, v2, :cond_1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/net/Uri;

    if-eqz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-static {v3, v0}, Lcom/miui/mishare/app/util/MiShareFileUtil;->isImageCanPrint(Landroid/content/Context;Landroid/net/Uri;)Z

    move-result v0

    if-eqz v0, :cond_1

    move v1, v2

    :cond_1
    :goto_0
    return v1
.end method

.method private doPrint()V
    .locals 5

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    new-instance v0, Lcom/miui/mishare/app/util/PrintHelper;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v0, v2}, Lcom/miui/mishare/app/util/PrintHelper;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/util/PrintHelper;->setScaleMode(I)V

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    const/4 v2, 0x0

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/net/Uri;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-static {v2, v1}, Lcom/miui/mishare/app/util/MiShareFileUtil;->getFileNameFromUri(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-static {v3, v1}, Lcom/miui/mishare/app/util/MiShareFileUtil;->isImageCanPrint(Landroid/content/Context;Landroid/net/Uri;)Z

    move-result v3

    if-eqz v3, :cond_0

    :try_start_0
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "MIUI:"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x0

    invoke-virtual {v0, v2, v1, v3}, Lcom/miui/mishare/app/util/PrintHelper;->printBitmap(Ljava/lang/String;Landroid/net/Uri;Lcom/miui/mishare/app/util/PrintHelper$OnPrintFinishCallback;)V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    invoke-virtual {v0}, Ljava/io/FileNotFoundException;->printStackTrace()V

    :cond_0
    :goto_0
    return-void
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

.method private getDeviceType(B)I
    .locals 1

    const/16 v0, 0x14

    if-lt p1, v0, :cond_0

    const/16 v0, 0x1d

    if-gt p1, v0, :cond_0

    const/4 p1, 0x2

    return p1

    :cond_0
    const/16 v0, 0xa

    if-lt p1, v0, :cond_2

    const/16 v0, 0x13

    if-gt p1, v0, :cond_2

    const/16 v0, 0xb

    if-ne p1, v0, :cond_1

    const/4 p1, 0x4

    return p1

    :cond_1
    const/4 p1, 0x3

    return p1

    :cond_2
    const/4 p1, 0x1

    return p1
.end method

.method private hasPrinter()Z
    .locals 3

    new-instance v0, Landroid/content/Intent;

    invoke-direct {v0}, Landroid/content/Intent;-><init>()V

    const-string v1, "com.android.printspooler"

    const-string v2, "com.android.printspooler.ui.MiuiPrintActivity"

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->setClassName(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    const/4 v2, 0x0

    if-nez v1, :cond_0

    return v2

    :cond_0
    invoke-virtual {v1, v0, v2}, Landroid/content/pm/PackageManager;->queryIntentActivities(Landroid/content/Intent;I)Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-lez v0, :cond_1

    const/4 v2, 0x1

    :cond_1
    return v2
.end method

.method private init()V
    .locals 4

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    sget v1, Lcom/miui/mishare/R$layout;->view_mishare_transfer_gallery:I

    invoke-virtual {v0, v1, p0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->getInstance(Landroid/content/Context;)Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    sget v0, Lcom/miui/mishare/R$id;->tv_prompt_tips:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mTipsTv:Landroid/widget/TextView;

    sget v0, Lcom/miui/mishare/R$id;->iv_throwing_screen:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageButton;

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrow:Landroid/widget/ImageButton;

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrow:Landroid/widget/ImageButton;

    invoke-virtual {v0, p0}, Landroid/widget/ImageButton;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    sget v0, Lcom/miui/mishare/R$id;->iv_printer:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageButton;

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mPrintBtn:Landroid/widget/ImageButton;

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mPrintBtn:Landroid/widget/ImageButton;

    invoke-virtual {v0, p0}, Landroid/widget/ImageButton;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mPrintBtn:Landroid/widget/ImageButton;

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->hasPrinter()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_0

    move v1, v2

    goto :goto_0

    :cond_0
    const/16 v1, 0x8

    :goto_0
    invoke-virtual {v0, v1}, Landroid/widget/ImageButton;->setVisibility(I)V

    sget v0, Lcom/miui/mishare/R$id;->rl_prompt_switch:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mMiShareDisabled:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->btn_enable_midrop:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mEnableMiShareView:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->view_middle_divider:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDividerView:Landroid/view/View;

    new-instance v0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-direct {v0, v1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {v0, p0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->registerListener(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$OnDeviceClickListener;)V

    sget v0, Lcom/miui/mishare/R$id;->lv_scanned_device:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroidx/recyclerview/widget/RecyclerView;

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

    new-instance v1, Landroidx/recyclerview/widget/LinearLayoutManager;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v3

    invoke-direct {v1, v3, v2, v2}, Landroidx/recyclerview/widget/LinearLayoutManager;-><init>(Landroid/content/Context;IZ)V

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setLayoutManager(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setAdapter(Landroidx/recyclerview/widget/RecyclerView$Adapter;)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mEnableMiShareView:Landroid/view/View;

    invoke-virtual {v0, p0}, Landroid/view/View;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->initMiShareStatus()V

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->registerStateReceiver()V

    return-void
.end method

.method private initMiShareStatus()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mMiShareDisabled:Landroid/view/View;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setVisibility(I)V

    return-void
.end method

.method private isFileCanPrint(Landroid/net/Uri;)Z
    .locals 1

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0, p1}, Lcom/miui/mishare/app/util/MiShareFileUtil;->isImageCanPrint(Landroid/content/Context;Landroid/net/Uri;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0, p1}, Lcom/miui/mishare/app/util/MiShareFileUtil;->isFilePdf(Landroid/content/Context;Landroid/net/Uri;)Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    :goto_1
    return p1
.end method

.method public static isServiceAvailable(Landroid/content/Context;)Z
    .locals 0

    invoke-static {p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->isAvailable(Landroid/content/Context;)Z

    move-result p0

    return p0
.end method

.method private print()V
    .locals 2

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->canPrint()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->doPrint()V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mMiPrintClick:Landroid/view/View$OnClickListener;

    if-eqz v0, :cond_1

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mPrintBtn:Landroid/widget/ImageButton;

    invoke-interface {v0, v1}, Landroid/view/View$OnClickListener;->onClick(Landroid/view/View;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/mishare/R$string;->file_cannot_print:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->showToast(Ljava/lang/String;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private refreshView(II)V
    .locals 2

    const/4 v0, 0x1

    const/4 v1, 0x0

    packed-switch p1, :pswitch_data_0

    return-void

    :pswitch_0
    move p1, v0

    move v0, v1

    goto :goto_0

    :pswitch_1
    move p1, v1

    :goto_0
    packed-switch p2, :pswitch_data_1

    return-void

    :pswitch_2
    move p1, v1

    goto :goto_1

    :pswitch_3
    move v0, v1

    :goto_1
    if-eqz v0, :cond_0

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setDiscoveringView()V

    goto :goto_2

    :cond_0
    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->stopSort()V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDividerView:Landroid/view/View;

    invoke-virtual {p1, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mMiShareDisabled:Landroid/view/View;

    invoke-virtual {p1, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

    const/4 p2, 0x4

    invoke-virtual {p1, p2}, Landroidx/recyclerview/widget/RecyclerView;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mTipsTv:Landroid/widget/TextView;

    sget p2, Lcom/miui/mishare/R$string;->tips_prompt_enable_midrop:I

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mEnableMiShareView:Landroid/view/View;

    invoke-virtual {p1, v1}, Landroid/view/View;->setVisibility(I)V

    :cond_1
    :goto_2
    return-void

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_1
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x1
        :pswitch_3
        :pswitch_3
        :pswitch_3
        :pswitch_2
        :pswitch_2
        :pswitch_2
        :pswitch_3
    .end packed-switch
.end method

.method private registerStateReceiver()V
    .locals 3

    new-instance v0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;-><init>(Lcom/miui/mishare/app/view/MiShareGalleryTransferView;Lcom/miui/mishare/app/view/MiShareGalleryTransferView$1;)V

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mShareTaskStateReceive:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;

    new-instance v0, Landroid/content/IntentFilter;

    invoke-direct {v0}, Landroid/content/IntentFilter;-><init>()V

    const-string v1, "com.miui.mishare.connectivity.TASK_STATE"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mShareTaskStateReceive:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;

    invoke-virtual {v1, v2, v0}, Landroid/content/Context;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    return-void
.end method

.method private send(Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 1

    if-nez p1, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mIsPrint:Z

    const/4 v0, 0x2

    iput v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mCurrentDeviceStatus:I

    iget-object p1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceId:Ljava/lang/String;

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceId:Ljava/lang/String;

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mHasNoFilesListener:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;

    if-eqz p1, :cond_1

    invoke-interface {p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;->fileNotYetSet()V

    :cond_1
    return-void
.end method

.method private sendToDevice(Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 2

    if-nez p1, :cond_0

    return-void

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    if-eqz v0, :cond_1

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_2

    :cond_1
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mHasNoFilesListener:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;

    if-eqz v0, :cond_2

    const/4 v0, 0x1

    iput v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->notifyDataSetChanged()V

    return-void

    :cond_2
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    iput-object v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->files:Ljava/util/List;

    new-instance v0, Lcom/miui/mishare/MiShareTask;

    invoke-direct {v0}, Lcom/miui/mishare/MiShareTask;-><init>()V

    iget-object v1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    iput-object v1, v0, Lcom/miui/mishare/MiShareTask;->device:Lcom/miui/mishare/RemoteDevice;

    iget-object v1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->files:Ljava/util/List;

    invoke-direct {p0, v1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getClipData(Ljava/util/List;)Landroid/content/ClipData;

    move-result-object v1

    iput-object v1, v0, Lcom/miui/mishare/MiShareTask;->clipData:Landroid/content/ClipData;

    iget-object v1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->taskId:Ljava/lang/String;

    iput-object v1, v0, Lcom/miui/mishare/MiShareTask;->taskId:Ljava/lang/String;

    iget-object v1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->files:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    iput v1, v0, Lcom/miui/mishare/MiShareTask;->count:I

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    invoke-virtual {v1, v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->sendData(Lcom/miui/mishare/MiShareTask;)V

    iget v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mCurrentDeviceStatus:I

    iput v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->deviceStatus:I

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->notifyDataSetChanged()V

    return-void
.end method

.method private setDiscoveringView()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mEnableMiShareView:Landroid/view/View;

    const/16 v1, 0x8

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mTipsTv:Landroid/widget/TextView;

    sget v1, Lcom/miui/mishare/R$string;->scanning_device_tips:I

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDividerView:Landroid/view/View;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mMiShareDisabled:Landroid/view/View;

    invoke-virtual {v0, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceLv:Landroidx/recyclerview/widget/RecyclerView;

    const/4 v1, 0x4

    invoke-virtual {v0, v1}, Landroidx/recyclerview/widget/RecyclerView;->setVisibility(I)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->setInitialAddDevice()V

    return-void
.end method

.method private showToast(Ljava/lang/String;)V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    const/4 v1, 0x0

    invoke-static {v0, p1, v1}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object p1

    invoke-virtual {p1}, Landroid/widget/Toast;->show()V

    return-void
.end method

.method private startDiscover()V
    .locals 3

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mIntent:Landroid/content/Intent;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    iget-object v2, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDiscoverCallback:Lcom/miui/mishare/IMiShareDiscoverCallback$Stub;

    invoke-virtual {v1, v2, v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->startDiscoverWithIntent(Lcom/miui/mishare/IMiShareDiscoverCallback;Landroid/content/Intent;)V

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDiscoverCallback:Lcom/miui/mishare/IMiShareDiscoverCallback$Stub;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->startDiscover(Lcom/miui/mishare/IMiShareDiscoverCallback;)V

    :goto_0
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mTaskStateListener:Lcom/miui/mishare/IMiShareTaskStateListener$Stub;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->registerTaskStateListener(Lcom/miui/mishare/IMiShareTaskStateListener;)V

    return-void
.end method

.method private unregisterStateReceiver()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mShareTaskStateReceive:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mShareTaskStateReceive:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$MiShareTaskStateReceiver;

    invoke-virtual {v0, v1}, Landroid/content/Context;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    :cond_0
    return-void
.end method


# virtual methods
.method public bind()V
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    invoke-virtual {v0, p0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->bind(Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity$ServiceBindCallBack;)Z

    return-void
.end method

.method public onCancelTask(Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 2

    new-instance v0, Lcom/miui/mishare/MiShareTask;

    invoke-direct {v0}, Lcom/miui/mishare/MiShareTask;-><init>()V

    iget-object v1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->remoteDevice:Lcom/miui/mishare/RemoteDevice;

    iput-object v1, v0, Lcom/miui/mishare/MiShareTask;->device:Lcom/miui/mishare/RemoteDevice;

    iget-object p1, p1, Lcom/miui/mishare/app/model/MiShareDevice;->taskId:Ljava/lang/String;

    iput-object p1, v0, Lcom/miui/mishare/MiShareTask;->taskId:Ljava/lang/String;

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    invoke-virtual {p1, v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->cancel(Lcom/miui/mishare/MiShareTask;)V

    return-void
.end method

.method public onClick(Landroid/view/View;)V
    .locals 2

    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v0

    sget v1, Lcom/miui/mishare/R$id;->btn_enable_midrop:I

    if-ne v0, v1, :cond_0

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->startDiscover()V

    goto :goto_0

    :cond_0
    sget v1, Lcom/miui/mishare/R$id;->iv_throwing_screen:I

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrowClick:Landroid/view/View$OnClickListener;

    if-eqz v0, :cond_4

    invoke-interface {v0, p1}, Landroid/view/View$OnClickListener;->onClick(Landroid/view/View;)V

    goto :goto_0

    :cond_1
    sget p1, Lcom/miui/mishare/R$id;->iv_printer:I

    if-ne v0, p1, :cond_4

    iget-boolean p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mPrintEnabled:Z

    if-eqz p1, :cond_3

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mIsPrint:Z

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mHasNoFilesListener:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;

    if-eqz p1, :cond_2

    invoke-interface {p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;->fileNotYetSet()V

    goto :goto_0

    :cond_2
    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->print()V

    goto :goto_0

    :cond_3
    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    sget v0, Lcom/miui/mishare/R$string;->file_cannot_print:I

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->showToast(Ljava/lang/String;)V

    :cond_4
    :goto_0
    return-void
.end method

.method public onDeviceTaskRetry(Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 1

    invoke-virtual {p1}, Lcom/miui/mishare/app/model/MiShareDevice;->isPC()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/mishare/app/model/MiShareDevice;->generatePCTaskId()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p1, Lcom/miui/mishare/app/model/MiShareDevice;->taskId:Ljava/lang/String;

    :cond_0
    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->send(Lcom/miui/mishare/app/model/MiShareDevice;)V

    return-void
.end method

.method public onDeviceTaskStart(Lcom/miui/mishare/app/model/MiShareDevice;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->send(Lcom/miui/mishare/app/model/MiShareDevice;)V

    return-void
.end method

.method public onServiceBound()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    invoke-virtual {v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->getServiceState()I

    move-result v0

    iput v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mState:I

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mStateListener:Lcom/miui/mishare/IMiShareStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->registerStateListener(Lcom/miui/mishare/IMiShareStateListener;)V

    iget v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mState:I

    const/4 v1, 0x3

    if-eq v0, v1, :cond_0

    const/4 v1, 0x4

    if-eq v0, v1, :cond_0

    const/4 v1, 0x5

    if-eq v0, v1, :cond_0

    const/4 v1, 0x6

    if-eq v0, v1, :cond_0

    goto :goto_0

    :cond_0
    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->startDiscover()V

    :goto_0
    return-void
.end method

.method public sendFiles(Ljava/util/List;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroid/net/Uri;",
            ">;)V"
        }
    .end annotation

    invoke-virtual {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setFiles(Ljava/util/List;)V

    iget-boolean p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mIsPrint:Z

    if-eqz p1, :cond_0

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->print()V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDeviceId:Ljava/lang/String;

    invoke-virtual {p1, v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->getDeviceById(Ljava/lang/String;)Lcom/miui/mishare/app/model/MiShareDevice;

    move-result-object p1

    if-nez p1, :cond_1

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object p1

    sget v0, Lcom/miui/mishare/R$string;->device_offline:I

    invoke-virtual {p1, v0}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->showToast(Ljava/lang/String;)V

    return-void

    :cond_1
    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->sendToDevice(Lcom/miui/mishare/app/model/MiShareDevice;)V

    :goto_0
    return-void
.end method

.method public setDeliveryTitle(Ljava/lang/String;)V
    .locals 1

    sget v0, Lcom/miui/mishare/R$id;->tv_send_file_to:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public setFileIfNotYet(Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mHasNoFilesListener:Lcom/miui/mishare/app/view/MiShareGalleryTransferView$FilesNotYetSetListener;

    return-void
.end method

.method public setFiles(Ljava/util/List;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Landroid/net/Uri;",
            ">;)V"
        }
    .end annotation

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mFiles:Ljava/util/List;

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v1

    const/4 v2, 0x1

    if-eq v1, v2, :cond_0

    goto :goto_0

    :cond_0
    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroid/net/Uri;

    invoke-direct {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->isFileCanPrint(Landroid/net/Uri;)Z

    move-result p1

    invoke-virtual {p0, p1}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setMiPrintEnable(Z)V

    goto :goto_1

    :cond_1
    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->setMiPrintEnable(Z)V

    :goto_1
    return-void
.end method

.method public setIntent(Landroid/content/Intent;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mIntent:Landroid/content/Intent;

    return-void
.end method

.method public setMiPrintClickListener(Landroid/view/View$OnClickListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mMiPrintClick:Landroid/view/View$OnClickListener;

    return-void
.end method

.method public setMiPrintEnable(Z)V
    .locals 1

    iput-boolean p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mPrintEnabled:Z

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mPrintBtn:Landroid/widget/ImageButton;

    if-eqz p1, :cond_0

    sget p1, Lcom/miui/mishare/R$drawable;->bg_ic_printer:I

    goto :goto_0

    :cond_0
    sget p1, Lcom/miui/mishare/R$drawable;->ic_printer_pressed:I

    :goto_0
    invoke-virtual {v0, p1}, Landroid/widget/ImageButton;->setImageResource(I)V

    return-void
.end method

.method public setScreenThrowClickListener(Landroid/view/View$OnClickListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrowClick:Landroid/view/View$OnClickListener;

    return-void
.end method

.method public setScreenThrowEnable(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrow:Landroid/widget/ImageButton;

    if-eqz p1, :cond_0

    sget p1, Lcom/miui/mishare/R$drawable;->bg_ic_screen:I

    goto :goto_0

    :cond_0
    sget p1, Lcom/miui/mishare/R$drawable;->ic_screen_pressed:I

    :goto_0
    invoke-virtual {v0, p1}, Landroid/widget/ImageButton;->setImageResource(I)V

    return-void
.end method

.method public setScreenThrowHighLight(Z)V
    .locals 2

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrow:Landroid/widget/ImageButton;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/mishare/R$drawable;->bg_screen_throw_hight_light:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageButton;->setBackground(Landroid/graphics/drawable/Drawable;)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrow:Landroid/widget/ImageButton;

    sget v0, Lcom/miui/mishare/R$drawable;->ic_screen_throwing:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageButton;->setImageResource(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrow:Landroid/widget/ImageButton;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget v1, Lcom/miui/mishare/R$drawable;->bg_mishare_tranfer_btn:I

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/widget/ImageButton;->setBackground(Landroid/graphics/drawable/Drawable;)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mScreenThrow:Landroid/widget/ImageButton;

    sget v0, Lcom/miui/mishare/R$drawable;->bg_ic_screen:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageButton;->setImageResource(I)V

    :goto_0
    return-void
.end method

.method public stopDiscover()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mDiscoverCallback:Lcom/miui/mishare/IMiShareDiscoverCallback$Stub;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->stopDiscover(Lcom/miui/mishare/IMiShareDiscoverCallback;)V

    return-void
.end method

.method public unbind()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    invoke-virtual {v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->checkServiceBound()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mStateListener:Lcom/miui/mishare/IMiShareStateListener;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->unregisterStateListener(Lcom/miui/mishare/IMiShareStateListener;)V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    iget-object v1, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mTaskStateListener:Lcom/miui/mishare/IMiShareTaskStateListener$Stub;

    invoke-virtual {v0, v1}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->unregisterTaskStateListener(Lcom/miui/mishare/IMiShareTaskStateListener;)V

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->stopDiscover()V

    :cond_0
    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mConnectivity:Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;

    invoke-virtual {v0}, Lcom/miui/mishare/app/connect/MiShareGalleryConnectivity;->unbind()V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->unregister()V

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->mAdapter:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->stopSort()V

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryTransferView;->unregisterStateReceiver()V

    return-void
.end method
