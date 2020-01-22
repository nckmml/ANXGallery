.class public Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;
.super Lcom/miui/gallery/activity/BaseActivity;
.source "InputFaceNameActivity.java"


# instance fields
.field mFragment:Lcom/miui/gallery/ui/renameface/InputFaceNameFragment;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/BaseActivity;-><init>()V

    return-void
.end method

.method private getOptionalPermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;
    .locals 4

    new-instance v0, Lcom/miui/gallery/permission/core/RuntimePermission;

    const v1, 0x7f10062f

    invoke-virtual {p0, v1}, Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    const-string v3, "android.permission.READ_CONTACTS"

    invoke-direct {v0, v3, v1, v2}, Lcom/miui/gallery/permission/core/RuntimePermission;-><init>(Ljava/lang/String;Ljava/lang/String;Z)V

    const/4 v1, 0x1

    new-array v1, v1, [Lcom/miui/gallery/permission/core/RuntimePermission;

    aput-object v0, v1, v2

    return-object v1
.end method


# virtual methods
.method public getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;
    .locals 2

    invoke-super {p0}, Lcom/miui/gallery/activity/BaseActivity;->getRuntimePermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;

    move-result-object v0

    invoke-direct {p0}, Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;->getOptionalPermissions()[Lcom/miui/gallery/permission/core/RuntimePermission;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ArrayUtils;->concat([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/permission/core/RuntimePermission;

    return-object v0
.end method

.method public onBackPressed()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;->mFragment:Lcom/miui/gallery/ui/renameface/InputFaceNameFragment;

    invoke-virtual {v0}, Lcom/miui/gallery/ui/renameface/InputFaceNameFragment;->onBackPressed()V

    invoke-super {p0}, Lcom/miui/gallery/activity/BaseActivity;->onBackPressed()V

    return-void
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onCreate(Landroid/os/Bundle;)V

    const p1, 0x7f0b00cb

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;->setContentView(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p1

    const v0, 0x7f0901b1

    invoke-virtual {p1, v0}, Landroid/app/FragmentManager;->findFragmentById(I)Landroid/app/Fragment;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/ui/renameface/InputFaceNameFragment;

    iput-object p1, p0, Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;->mFragment:Lcom/miui/gallery/ui/renameface/InputFaceNameFragment;

    return-void
.end method

.method public onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V
    .locals 0

    invoke-super {p0, p1, p2}, Lcom/miui/gallery/activity/BaseActivity;->onPermissionsChecked([Lcom/miui/gallery/permission/core/RuntimePermission;[I)V

    iget-object p1, p0, Lcom/miui/gallery/activity/facebaby/InputFaceNameActivity;->mFragment:Lcom/miui/gallery/ui/renameface/InputFaceNameFragment;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Lcom/miui/gallery/ui/renameface/InputFaceNameFragment;->updateNameList()V

    :cond_0
    return-void
.end method
