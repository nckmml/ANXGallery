.class Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_V23;
.super Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_Base;
.source "SyncAdapterBaseInjectorMiCloud_V23.java"


# static fields
.field private static final permissionUtils:Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;

    invoke-direct {v0}, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;-><init>()V

    sput-object v0, Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_V23;->permissionUtils:Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;

    return-void
.end method

.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_Base;-><init>()V

    return-void
.end method


# virtual methods
.method public isGdprPermissionGranted(Landroid/content/Context;Landroid/content/SyncResult;)Z
    .locals 4

    sget-object v0, Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_V23;->permissionUtils:Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;

    invoke-virtual {v0, p1}, Lmicloud/compat/v18/sync/PermissionUtilsCompatMiCloud_V23;->isGdprPermissionGranted(Landroid/content/Context;)Z

    move-result p1

    if-nez p1, :cond_0

    iget-object p2, p2, Landroid/content/SyncResult;->stats:Landroid/content/SyncStats;

    iget-wide v0, p2, Landroid/content/SyncStats;->numAuthExceptions:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    iput-wide v0, p2, Landroid/content/SyncStats;->numAuthExceptions:J

    :cond_0
    return p1
.end method
