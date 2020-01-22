.class public Lcom/miui/gallery/ui/CopyOrMoveDialog;
.super Lcom/miui/gallery/widget/GalleryDialogFragment;
.source "CopyOrMoveDialog.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;
    }
.end annotation


# instance fields
.field private mDialog:Lmiui/app/AlertDialog;

.field private mItemClickListener:Landroid/content/DialogInterface$OnClickListener;

.field private mOnOperationSelectedListener:Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Lcom/miui/gallery/widget/GalleryDialogFragment;-><init>()V

    new-instance v0, Lcom/miui/gallery/ui/CopyOrMoveDialog$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/ui/CopyOrMoveDialog$1;-><init>(Lcom/miui/gallery/ui/CopyOrMoveDialog;)V

    iput-object v0, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mItemClickListener:Landroid/content/DialogInterface$OnClickListener;

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/ui/CopyOrMoveDialog;)Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mOnOperationSelectedListener:Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;

    return-object p0
.end method


# virtual methods
.method public onCreate(Landroid/os/Bundle;)V
    .locals 0

    invoke-super {p0, p1}, Lcom/miui/gallery/widget/GalleryDialogFragment;->onCreate(Landroid/os/Bundle;)V

    return-void
.end method

.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 3

    new-instance p1, Lmiui/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CopyOrMoveDialog;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {p1, v0}, Lmiui/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {p0}, Lcom/miui/gallery/ui/CopyOrMoveDialog;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    const v1, 0x7f020016

    invoke-virtual {v0, v1}, Landroid/content/res/Resources;->getTextArray(I)[Ljava/lang/CharSequence;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x2

    if-le v1, v2, :cond_0

    invoke-static {v0, v2}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/CharSequence;

    :cond_0
    const v1, 0x7f100798

    invoke-virtual {p1, v1}, Lmiui/app/AlertDialog$Builder;->setTitle(I)Lmiui/app/AlertDialog$Builder;

    move-result-object v1

    iget-object v2, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mItemClickListener:Landroid/content/DialogInterface$OnClickListener;

    invoke-virtual {v1, v0, v2}, Lmiui/app/AlertDialog$Builder;->setItems([Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    move-result-object v0

    const v1, 0x7f10010a

    iget-object v2, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mItemClickListener:Landroid/content/DialogInterface$OnClickListener;

    invoke-virtual {v0, v1, v2}, Lmiui/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    invoke-virtual {p1}, Lmiui/app/AlertDialog$Builder;->create()Lmiui/app/AlertDialog;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mDialog:Lmiui/app/AlertDialog;

    iget-object p1, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mDialog:Lmiui/app/AlertDialog;

    const/4 v0, 0x0

    invoke-virtual {p1, v0}, Lmiui/app/AlertDialog;->setCanceledOnTouchOutside(Z)V

    iget-object p1, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mDialog:Lmiui/app/AlertDialog;

    return-object p1
.end method

.method public setOnOperationSelectedListener(Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/CopyOrMoveDialog;->mOnOperationSelectedListener:Lcom/miui/gallery/ui/CopyOrMoveDialog$OnOperationSelectedListener;

    return-void
.end method
