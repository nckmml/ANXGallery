.class Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatMiCloud_Base;
.super Ljava/lang/Object;
.source "IXiaomiAccountServiceProxyCompatMiCloud_Base.java"

# interfaces
.implements Lmicloud/compat/v18/utils/IXiaomiAccountServiceProxyCompatImpl;


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getEncryptedUserId(Landroid/os/IBinder;Landroid/accounts/Account;)Ljava/lang/String;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Landroid/os/RemoteException;
        }
    .end annotation

    invoke-static {p1, p2}, Lcom/xiaomi/micloudsdk/utils/IXiaomiAccountServiceProxy;->getEncryptedUserId(Landroid/os/IBinder;Landroid/accounts/Account;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method
