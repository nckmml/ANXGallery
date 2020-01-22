.class public Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompat;
.super Ljava/lang/Object;
.source "IXiaomiAccountServiceProxyCompat.java"


# static fields
.field private static final IMPL:Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatImpl;

.field private static final version:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    sget v0, Lcom/xiaomi/micloudsdk/utils/MiCloudSdkBuild;->CURRENT_VERSION:I

    sput v0, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompat;->version:I

    sget v0, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompat;->version:I

    const/16 v1, 0x14

    if-lt v0, v1, :cond_0

    new-instance v0, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatMiCloud_V20;

    invoke-direct {v0}, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatMiCloud_V20;-><init>()V

    sput-object v0, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompat;->IMPL:Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatImpl;

    goto :goto_0

    :cond_0
    new-instance v0, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatMiCloud_Base;

    invoke-direct {v0}, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatMiCloud_Base;-><init>()V

    sput-object v0, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompat;->IMPL:Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatImpl;

    :goto_0
    return-void
.end method

.method public static getEncryptedUserId(Landroid/os/IBinder;Landroid/accounts/Account;)Ljava/lang/String;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    sget-object v0, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompat;->IMPL:Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatImpl;

    invoke-interface {v0, p0, p1}, Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatImpl;->getEncryptedUserId(Landroid/os/IBinder;Landroid/accounts/Account;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method
