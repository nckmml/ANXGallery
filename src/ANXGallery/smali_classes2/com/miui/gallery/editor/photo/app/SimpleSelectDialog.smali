.class public Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;
.super Lcom/miui/gallery/widget/GalleryDialogFragment;
.source "SimpleSelectDialog.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$OnOperationSelectedListener;,
        Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SimpleOptionAdapter;,
        Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;
    }
.end annotation


# instance fields
.field private mDialog:Lmiui/app/AlertDialog;

.field private mOnOperationSelectedListener:Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$OnOperationSelectedListener;

.field private mSelectModels:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/widget/GalleryDialogFragment;-><init>()V

    return-void
.end method

.method static synthetic access$100(Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mSelectModels:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$200(Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;)Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$OnOperationSelectedListener;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mOnOperationSelectedListener:Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$OnOperationSelectedListener;

    return-object p0
.end method


# virtual methods
.method public onCreateDialog(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 2

    new-instance p1, Lmiui/app/AlertDialog$Builder;

    invoke-virtual {p0}, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->getActivity()Landroid/app/Activity;

    move-result-object v0

    invoke-direct {p1, v0}, Lmiui/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    new-instance v0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SimpleOptionAdapter;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mSelectModels:Ljava/util/List;

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SimpleOptionAdapter;-><init>(Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;Ljava/util/List;)V

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Lmiui/app/AlertDialog$Builder;->setAdapter(Landroid/widget/ListAdapter;Landroid/content/DialogInterface$OnClickListener;)Lmiui/app/AlertDialog$Builder;

    invoke-virtual {p1}, Lmiui/app/AlertDialog$Builder;->create()Lmiui/app/AlertDialog;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mDialog:Lmiui/app/AlertDialog;

    iget-object p1, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mDialog:Lmiui/app/AlertDialog;

    return-object p1
.end method

.method public varargs setCreationModels([I)V
    .locals 5

    new-instance v0, Ljava/util/ArrayList;

    array-length v1, p1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    iput-object v0, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mSelectModels:Ljava/util/List;

    array-length v0, p1

    const/4 v1, 0x0

    :goto_0
    if-ge v1, v0, :cond_0

    aget v2, p1, v1

    iget-object v3, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mSelectModels:Ljava/util/List;

    new-instance v4, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;

    invoke-direct {v4, v2, v2}, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;-><init>(II)V

    invoke-interface {v3, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method

.method public setOnOperationSelectedListener(Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$OnOperationSelectedListener;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;->mOnOperationSelectedListener:Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$OnOperationSelectedListener;

    return-void
.end method
