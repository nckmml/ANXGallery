.class public Lmicloud/compat/v18/sync/SyncAdapterBaseInjector;
.super Ljava/lang/Object;
.source "SyncAdapterBaseInjector.java"


# static fields
.field private static final IMPL:Lmicloud/compat/v18/sync/ISyncAdapterBaseInjector;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;->CURRENT_VERSION:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_0

    new-instance v0, Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_V23;

    invoke-direct {v0}, Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_V23;-><init>()V

    sput-object v0, Lmicloud/compat/v18/sync/SyncAdapterBaseInjector;->IMPL:Lmicloud/compat/v18/sync/ISyncAdapterBaseInjector;

    goto :goto_0

    :cond_0
    new-instance v0, Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_Base;

    invoke-direct {v0}, Lmicloud/compat/v18/sync/SyncAdapterBaseInjectorMiCloud_Base;-><init>()V

    sput-object v0, Lmicloud/compat/v18/sync/SyncAdapterBaseInjector;->IMPL:Lmicloud/compat/v18/sync/ISyncAdapterBaseInjector;

    :goto_0
    return-void
.end method

.method public static isGdprPermissionGranted(Landroid/content/Context;Landroid/content/SyncResult;)Z
    .locals 1

    sget-object v0, Lmicloud/compat/v18/sync/SyncAdapterBaseInjector;->IMPL:Lmicloud/compat/v18/sync/ISyncAdapterBaseInjector;

    invoke-interface {v0, p0, p1}, Lmicloud/compat/v18/sync/ISyncAdapterBaseInjector;->isGdprPermissionGranted(Landroid/content/Context;Landroid/content/SyncResult;)Z

    move-result p0

    return p0
.end method
