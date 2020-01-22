.class public Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;
.super Ljava/lang/Object;
.source "EmptyPage.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/widget/EmptyPage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "EmptyConfig"
.end annotation


# instance fields
.field private isActionButtonClickable:Z

.field private isShowActionButton:Z

.field private mActionButtonBackgroundDrawable:Landroid/graphics/drawable/Drawable;

.field private mActionButtonClickListener:Landroid/view/View$OnClickListener;

.field private mActionButtonTitle:Ljava/lang/CharSequence;

.field private mBackgroundId:I

.field private mBigTitle:Ljava/lang/CharSequence;

.field private mBigTitleResId:I

.field private mDescriptionText:Ljava/lang/CharSequence;

.field private mDescriptionTextResId:I

.field private mIconDrawable:Landroid/graphics/drawable/Drawable;

.field private mIconResId:I

.field private mTitle:Ljava/lang/CharSequence;

.field private mTitleResId:I


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Landroid/graphics/drawable/Drawable;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mIconDrawable:Landroid/graphics/drawable/Drawable;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mIconResId:I

    return p0
.end method

.method static synthetic access$1000(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->isShowActionButton:Z

    return p0
.end method

.method static synthetic access$1100(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->isActionButtonClickable:Z

    return p0
.end method

.method static synthetic access$1200(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Landroid/view/View$OnClickListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mActionButtonClickListener:Landroid/view/View$OnClickListener;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mDescriptionTextResId:I

    return p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Ljava/lang/CharSequence;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mDescriptionText:Ljava/lang/CharSequence;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Ljava/lang/CharSequence;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mBigTitle:Ljava/lang/CharSequence;

    return-object p0
.end method

.method static synthetic access$500(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mBigTitleResId:I

    return p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Ljava/lang/CharSequence;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mTitle:Ljava/lang/CharSequence;

    return-object p0
.end method

.method static synthetic access$700(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mTitleResId:I

    return p0
.end method

.method static synthetic access$800(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Landroid/graphics/drawable/Drawable;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mActionButtonBackgroundDrawable:Landroid/graphics/drawable/Drawable;

    return-object p0
.end method

.method static synthetic access$900(Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;)Ljava/lang/CharSequence;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mActionButtonTitle:Ljava/lang/CharSequence;

    return-object p0
.end method


# virtual methods
.method public disableActionButton()Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->isShowActionButton:Z

    return-object p0
.end method

.method public setBackground(I)Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mBackgroundId:I

    return-object p0
.end method

.method public setIcon(I)Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mIconResId:I

    return-object p0
.end method

.method public setTitle(I)Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/widget/EmptyPage$EmptyConfig;->mTitleResId:I

    return-object p0
.end method
