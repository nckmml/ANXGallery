.class public Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;
.super Lcom/miui/gallery/widget/GalleryDialogFragment;
.source "StoryAlbumRenameDialog.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$OnRenameDoneListener;
    }
.end annotation


# instance fields
.field protected mConfirmButton:Landroid/widget/Button;

.field private final mConfirmListener:Landroid/view/View$OnClickListener;

.field protected mDefaultName:Ljava/lang/String;

.field protected mDialog:Lmiui/app/AlertDialog;

.field protected mInputView:Landroid/widget/EditText;

.field protected mOnRenameDoneListener:Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$OnRenameDoneListener;

.field private final mTextWatcher:Landroid/text/TextWatcher;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/widget/GalleryDialogFragment;-><init>()V

    new-instance v0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$2;

    invoke-direct {v0, p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$2;-><init>(Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;)V

    iput-object v0, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mConfirmListener:Landroid/view/View$OnClickListener;

    new-instance v0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$3;

    invoke-direct {v0, p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$3;-><init>(Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;)V

    iput-object v0, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mTextWatcher:Landroid/text/TextWatcher;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;)Landroid/view/View$OnClickListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mConfirmListener:Landroid/view/View$OnClickListener;

    return-object p0
.end method

.method static synthetic access$100(Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;Ljava/lang/String;)Z
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->verify(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method private parseArguments()V
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->getArguments()Landroid/os/Bundle;

    move-result-object v0

    const-string v1, "card_name"

    invoke-virtual {v0, v1}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mDefaultName:Ljava/lang/String;

    return-void
.end method

.method private verify(Ljava/lang/String;)Z
    .locals 5

    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mInputView:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->selectAll()V

    return v1

    :cond_0
    invoke-virtual {p1, v1}, Ljava/lang/String;->charAt(I)C

    move-result v0

    const-string v2, "._"

    invoke-virtual {v2, v0}, Ljava/lang/String;->indexOf(I)I

    move-result v0

    if-ltz v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->getActivity()Landroid/app/Activity;

    move-result-object p1

    const v0, 0x7f100818

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    return v1

    :cond_1
    move v0, v1

    :goto_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v2

    const/4 v3, 0x1

    if-ge v0, v2, :cond_4

    invoke-virtual {p1, v0}, Ljava/lang/String;->charAt(I)C

    move-result v2

    const-string v4, "/\\:@*?<>\r\n\t"

    invoke-virtual {v4, v2}, Ljava/lang/String;->indexOf(I)I

    move-result v4

    if-ltz v4, :cond_3

    const-string p1, "\r\n\t"

    invoke-virtual {p1, v2}, Ljava/lang/String;->indexOf(I)I

    move-result p1

    if-ltz p1, :cond_2

    const/16 v2, 0x20

    :cond_2
    invoke-virtual {p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->getActivity()Landroid/app/Activity;

    move-result-object p1

    const v0, 0x7f100817

    new-array v3, v3, [Ljava/lang/Object;

    invoke-static {v2}, Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;

    move-result-object v2

    aput-object v2, v3, v1

    invoke-virtual {p0, v0, v3}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {p1, v0}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;)V

    return v1

    :cond_3
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_4
    return v3
.end method


# virtual methods
.method public onCreate(Landroid/os/Bundle;)V
    .locals 3

    invoke-super {p0, p1}, Lcom/miui/gallery/widget/GalleryDialogFragment;->onCreate(Landroid/os/Bundle;)V

    invoke-direct {p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->parseArguments()V

    invoke-virtual {p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->getActivity()Landroid/app/Activity;

    move-result-object p1

    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    const v0, 0x7f0b0099

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-virtual {p1, v0, v1, v2}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p1

    check-cast p1, Landroid/widget/EditText;

    iput-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mInputView:Landroid/widget/EditText;

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mInputView:Landroid/widget/EditText;

    const v0, 0x7f100728

    invoke-virtual {p1, v0}, Landroid/widget/EditText;->setHint(I)V

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mInputView:Landroid/widget/EditText;

    iget-object v0, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mDefaultName:Ljava/lang/String;

    invoke-virtual {p1, v0}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mInputView:Landroid/widget/EditText;

    invoke-virtual {p1}, Landroid/widget/EditText;->selectAll()V

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mInputView:Landroid/widget/EditText;

    iget-object v0, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mTextWatcher:Landroid/text/TextWatcher;

    invoke-virtual {p1, v0}, Landroid/widget/EditText;->addTextChangedListener(Landroid/text/TextWatcher;)V

    return-void
.end method

.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 2

    new-instance p1, Lmiui/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {p1, v0}, Lmiui/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    iget-object v0, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mInputView:Landroid/widget/EditText;

    invoke-virtual {p1, v0}, Lmiui/app/AlertDialog$Builder;->setView(Landroid/view/View;)Lmiui/app/AlertDialog$Builder;

    move-result-object p1

    const v0, 0x7f100729

    invoke-virtual {p1, v0}, Lmiui/app/AlertDialog$Builder;->setTitle(I)Lmiui/app/AlertDialog$Builder;

    move-result-object p1

    const/4 v0, 0x0

    const v1, 0x104000a

    invoke-virtual {p1, v1, v0}, Lmiui/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    move-result-object p1

    const/high16 v1, 0x1040000

    invoke-virtual {p1, v1, v0}, Lmiui/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    move-result-object p1

    invoke-virtual {p1}, Lmiui/app/AlertDialog$Builder;->create()Lmiui/app/AlertDialog;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mDialog:Lmiui/app/AlertDialog;

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mDialog:Lmiui/app/AlertDialog;

    invoke-virtual {p1}, Lmiui/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mDialog:Lmiui/app/AlertDialog;

    invoke-virtual {p1}, Lmiui/app/AlertDialog;->getWindow()Landroid/view/Window;

    move-result-object p1

    const/4 v0, 0x4

    invoke-virtual {p1, v0}, Landroid/view/Window;->setSoftInputMode(I)V

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mDialog:Lmiui/app/AlertDialog;

    new-instance v0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$1;-><init>(Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;)V

    invoke-virtual {p1, v0}, Lmiui/app/AlertDialog;->setOnShowListener(Landroid/content/DialogInterface$OnShowListener;)V

    iget-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mDialog:Lmiui/app/AlertDialog;

    return-object p1
.end method

.method public setOnRenameDoneListener(Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$OnRenameDoneListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog;->mOnRenameDoneListener:Lcom/miui/gallery/card/ui/detail/StoryAlbumRenameDialog$OnRenameDoneListener;

    return-void
.end method
