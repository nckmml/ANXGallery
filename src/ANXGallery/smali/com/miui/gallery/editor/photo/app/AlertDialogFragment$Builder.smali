.class public Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;
.super Ljava/lang/Object;
.source "AlertDialogFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field private mCancellable:Z

.field private mMessageId:I

.field private mNegativeId:I

.field private mPositiveId:I


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public build()Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;
    .locals 3

    new-instance v0, Landroid/os/Bundle;

    invoke-direct {v0}, Landroid/os/Bundle;-><init>()V

    iget v1, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mMessageId:I

    if-eqz v1, :cond_0

    const-string v2, "AlertDialogFragment:message_resource"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    :cond_0
    iget v1, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mPositiveId:I

    if-eqz v1, :cond_1

    const-string v2, "AlertDialogFragment:positive_resource"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    :cond_1
    iget v1, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mNegativeId:I

    if-eqz v1, :cond_2

    const-string v2, "AlertDialogFragment:negative_resource"

    invoke-virtual {v0, v2, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    :cond_2
    new-instance v1, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;

    invoke-direct {v1}, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;-><init>()V

    iget-boolean v2, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mCancellable:Z

    invoke-virtual {v1, v2}, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;->setCancelable(Z)V

    invoke-virtual {v1, v0}, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment;->setArguments(Landroid/os/Bundle;)V

    return-object v1
.end method

.method public setCancellable(Z)Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mCancellable:Z

    return-object p0
.end method

.method public setMessage(I)Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mMessageId:I

    return-object p0
.end method

.method public setNegativeButton(I)Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mNegativeId:I

    return-object p0
.end method

.method public setPositiveButton(I)Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/editor/photo/app/AlertDialogFragment$Builder;->mPositiveId:I

    return-object p0
.end method
