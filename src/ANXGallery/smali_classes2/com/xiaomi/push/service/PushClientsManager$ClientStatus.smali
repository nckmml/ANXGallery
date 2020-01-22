.class public final enum Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;
.super Ljava/lang/Enum;
.source "PushClientsManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/xiaomi/push/service/PushClientsManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "ClientStatus"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

.field public static final enum binded:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

.field public static final enum binding:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

.field public static final enum unbind:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    new-instance v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    const/4 v1, 0x0

    const-string v2, "unbind"

    invoke-direct {v0, v2, v1}, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->unbind:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    new-instance v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    const/4 v2, 0x1

    const-string v3, "binding"

    invoke-direct {v0, v3, v2}, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->binding:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    new-instance v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    const/4 v3, 0x2

    const-string v4, "binded"

    invoke-direct {v0, v4, v3}, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->binded:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    const/4 v0, 0x3

    new-array v0, v0, [Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    sget-object v4, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->unbind:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    aput-object v4, v0, v1

    sget-object v1, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->binding:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->binded:Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    aput-object v1, v0, v3

    sput-object v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->$VALUES:[Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;
    .locals 1

    const-class v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;
    .locals 1

    sget-object v0, Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->$VALUES:[Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    invoke-virtual {v0}, [Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/push/service/PushClientsManager$ClientStatus;

    return-object v0
.end method
