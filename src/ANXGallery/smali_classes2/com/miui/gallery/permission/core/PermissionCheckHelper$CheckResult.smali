.class Lcom/miui/gallery/permission/core/PermissionCheckHelper$CheckResult;
.super Ljava/lang/Object;
.source "PermissionCheckHelper.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/permission/core/PermissionCheckHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "CheckResult"
.end annotation


# instance fields
.field grantResult:I

.field permission:Lcom/miui/gallery/permission/core/RuntimePermission;


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/permission/core/PermissionCheckHelper$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/permission/core/PermissionCheckHelper$CheckResult;-><init>()V

    return-void
.end method
