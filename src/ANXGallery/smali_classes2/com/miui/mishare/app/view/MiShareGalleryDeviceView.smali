.class public Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;
.super Landroid/widget/LinearLayout;
.source "MiShareGalleryDeviceView.java"


# instance fields
.field private mDeviceIcon:Landroid/view/View;

.field private mDeviceModelNameTv:Landroid/widget/TextView;

.field private mDeviceName:Ljava/lang/String;

.field private mDeviceNameTv:Landroid/widget/TextView;

.field private mDivider:Landroid/view/View;

.field private mDividerEnd:Landroid/view/View;

.field private mDividerStart:Landroid/view/View;

.field private mPcView:Landroid/view/View;

.field private mPhoneView:Landroid/view/View;

.field private mStatusIv:Landroid/widget/ImageView;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 0

    invoke-direct {p0, p1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    invoke-direct {p0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->initView()V

    return-void
.end method

.method private initView()V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v0

    sget v1, Lcom/miui/mishare/R$layout;->view_mishare_gallery_device_view:I

    invoke-virtual {v0, v1, p0}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->rl_device_icon:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceIcon:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->tv_device_name:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceNameTv:Landroid/widget/TextView;

    sget v0, Lcom/miui/mishare/R$id;->tv_device_model:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceModelNameTv:Landroid/widget/TextView;

    sget v0, Lcom/miui/mishare/R$id;->iv_sending_status:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ImageView;

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    sget v0, Lcom/miui/mishare/R$id;->view_phone:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mPhoneView:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->iv_pc:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mPcView:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->view_divider:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDivider:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->view_divider_start:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDividerStart:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$id;->view_divider_right:I

    invoke-virtual {p0, v0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->findViewById(I)Landroid/view/View;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDividerEnd:Landroid/view/View;

    return-void
.end method


# virtual methods
.method public getIconView()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceIcon:Landroid/view/View;

    return-object v0
.end method

.method public setDeviceModelName(Ljava/lang/String;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceModelNameTv:Landroid/widget/TextView;

    invoke-virtual {v0, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceName:Ljava/lang/String;

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceModelNameTv:Landroid/widget/TextView;

    const/4 v0, 0x2

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setMaxLines(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceModelNameTv:Landroid/widget/TextView;

    const/4 v0, 0x1

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setMaxLines(I)V

    :goto_0
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceModelNameTv:Landroid/widget/TextView;

    sget-object v0, Landroid/text/TextUtils$TruncateAt;->END:Landroid/text/TextUtils$TruncateAt;

    invoke-virtual {p1, v0}, Landroid/widget/TextView;->setEllipsize(Landroid/text/TextUtils$TruncateAt;)V

    return-void
.end method

.method public setDeviceName(Ljava/lang/String;Z)V
    .locals 4

    iput-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceName:Ljava/lang/String;

    const/4 v0, 0x0

    if-eqz p2, :cond_0

    iget-object p2, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceNameTv:Landroid/widget/TextView;

    invoke-virtual {p0}, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/miui/mishare/R$string;->device_name_with_ellipsize:I

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    aput-object p1, v3, v0

    invoke-virtual {v1, v2, v3}, Landroid/content/res/Resources;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    return-void

    :cond_0
    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result p2

    if-nez p2, :cond_1

    iget-object p2, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceNameTv:Landroid/widget/TextView;

    invoke-virtual {p2, v0}, Landroid/widget/TextView;->setVisibility(I)V

    iget-object p2, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceNameTv:Landroid/widget/TextView;

    invoke-virtual {p2, p1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceNameTv:Landroid/widget/TextView;

    const/16 p2, 0x8

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setVisibility(I)V

    :goto_0
    return-void
.end method

.method public setDevicePhoneOrPc(Z)V
    .locals 2

    const/4 v0, 0x0

    const/16 v1, 0x8

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mPhoneView:Landroid/view/View;

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mPcView:Landroid/view/View;

    invoke-virtual {p1, v1}, Landroid/view/View;->setVisibility(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mPhoneView:Landroid/view/View;

    invoke-virtual {p1, v1}, Landroid/view/View;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mPcView:Landroid/view/View;

    invoke-virtual {p1, v0}, Landroid/view/View;->setVisibility(I)V

    :goto_0
    return-void
.end method

.method public setDeviceStatus(I)V
    .locals 3

    const/4 v0, 0x1

    const/16 v1, 0x8

    if-eq p1, v0, :cond_3

    const/4 v0, 0x2

    const/4 v2, 0x0

    if-eq p1, v0, :cond_2

    const/4 v0, 0x3

    if-eq p1, v0, :cond_1

    const/4 v0, 0x4

    if-eq p1, v0, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    invoke-virtual {p1, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    sget v0, Lcom/miui/mishare/R$drawable;->ic_device_send_success:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    sget v0, Lcom/miui/mishare/R$drawable;->ic_device_send_retry:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    invoke-virtual {p1, v2}, Landroid/widget/ImageView;->setVisibility(I)V

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    sget v0, Lcom/miui/mishare/R$drawable;->ic_device_sending:I

    invoke-virtual {p1, v0}, Landroid/widget/ImageView;->setImageResource(I)V

    goto :goto_0

    :cond_3
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mStatusIv:Landroid/widget/ImageView;

    invoke-virtual {p1, v1}, Landroid/widget/ImageView;->setVisibility(I)V

    :goto_0
    return-void
.end method

.method public setDeviceType(I)V
    .locals 1

    const/4 v0, 0x1

    if-eq p1, v0, :cond_3

    const/4 v0, 0x2

    if-eq p1, v0, :cond_2

    const/4 v0, 0x3

    if-eq p1, v0, :cond_1

    const/4 v0, 0x4

    if-eq p1, v0, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceIcon:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$drawable;->bg_scanned_device_xiaomi:I

    invoke-virtual {p1, v0}, Landroid/view/View;->setBackgroundResource(I)V

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceIcon:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$drawable;->bg_scanned_device_realme:I

    invoke-virtual {p1, v0}, Landroid/view/View;->setBackgroundResource(I)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceIcon:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$drawable;->bg_scanned_device_oppo:I

    invoke-virtual {p1, v0}, Landroid/view/View;->setBackgroundResource(I)V

    goto :goto_0

    :cond_2
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceIcon:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$drawable;->bg_scanned_device_vivo:I

    invoke-virtual {p1, v0}, Landroid/view/View;->setBackgroundResource(I)V

    goto :goto_0

    :cond_3
    iget-object p1, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDeviceIcon:Landroid/view/View;

    sget v0, Lcom/miui/mishare/R$drawable;->bg_scanned_device_xiaomi:I

    invoke-virtual {p1, v0}, Landroid/view/View;->setBackgroundResource(I)V

    :goto_0
    return-void
.end method

.method public showDivider(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDivider:Landroid/view/View;

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    const/16 p1, 0x8

    :goto_0
    invoke-virtual {v0, p1}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method

.method public showDividerEnd(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDividerEnd:Landroid/view/View;

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    const/16 p1, 0x8

    :goto_0
    invoke-virtual {v0, p1}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method

.method public showDividerStart(Z)V
    .locals 1

    iget-object v0, p0, Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;->mDividerStart:Landroid/view/View;

    if-eqz p1, :cond_0

    const/4 p1, 0x0

    goto :goto_0

    :cond_0
    const/16 p1, 0x8

    :goto_0
    invoke-virtual {v0, p1}, Landroid/view/View;->setVisibility(I)V

    return-void
.end method
