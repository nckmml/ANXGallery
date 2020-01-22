.class public final enum Lcom/xiaomi/push/service/xmpush/Command;
.super Ljava/lang/Enum;
.source "Command.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/push/service/xmpush/Command;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_CHK_VDEVID:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_REGISTER:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_SET_ACCEPT_TIME:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_SET_ACCOUNT:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_SET_ALIAS:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_SUBSCRIBE_TOPIC:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_UNREGISTER:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_UNSET_ACCOUNT:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_UNSET_ALIAS:Lcom/xiaomi/push/service/xmpush/Command;

.field public static final enum COMMAND_UNSUBSCRIBE_TOPIC:Lcom/xiaomi/push/service/xmpush/Command;


# instance fields
.field public final value:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 13

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v1, 0x0

    const-string v2, "COMMAND_REGISTER"

    const-string v3, "register"

    invoke-direct {v0, v2, v1, v3}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_REGISTER:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v2, 0x1

    const-string v3, "COMMAND_UNREGISTER"

    const-string v4, "unregister"

    invoke-direct {v0, v3, v2, v4}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNREGISTER:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v3, 0x2

    const-string v4, "COMMAND_SET_ALIAS"

    const-string v5, "set-alias"

    invoke-direct {v0, v4, v3, v5}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SET_ALIAS:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v4, 0x3

    const-string v5, "COMMAND_UNSET_ALIAS"

    const-string v6, "unset-alias"

    invoke-direct {v0, v5, v4, v6}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNSET_ALIAS:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v5, 0x4

    const-string v6, "COMMAND_SET_ACCOUNT"

    const-string v7, "set-account"

    invoke-direct {v0, v6, v5, v7}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SET_ACCOUNT:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v6, 0x5

    const-string v7, "COMMAND_UNSET_ACCOUNT"

    const-string v8, "unset-account"

    invoke-direct {v0, v7, v6, v8}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNSET_ACCOUNT:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v7, 0x6

    const-string v8, "COMMAND_SUBSCRIBE_TOPIC"

    const-string v9, "subscribe-topic"

    invoke-direct {v0, v8, v7, v9}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SUBSCRIBE_TOPIC:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/4 v8, 0x7

    const-string v9, "COMMAND_UNSUBSCRIBE_TOPIC"

    const-string v10, "unsubscibe-topic"

    invoke-direct {v0, v9, v8, v10}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNSUBSCRIBE_TOPIC:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/16 v9, 0x8

    const-string v10, "COMMAND_SET_ACCEPT_TIME"

    const-string v11, "accept-time"

    invoke-direct {v0, v10, v9, v11}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SET_ACCEPT_TIME:Lcom/xiaomi/push/service/xmpush/Command;

    new-instance v0, Lcom/xiaomi/push/service/xmpush/Command;

    const/16 v10, 0x9

    const-string v11, "COMMAND_CHK_VDEVID"

    const-string v12, "check-vdeviceid"

    invoke-direct {v0, v11, v10, v12}, Lcom/xiaomi/push/service/xmpush/Command;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_CHK_VDEVID:Lcom/xiaomi/push/service/xmpush/Command;

    const/16 v0, 0xa

    new-array v0, v0, [Lcom/xiaomi/push/service/xmpush/Command;

    sget-object v11, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_REGISTER:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v11, v0, v1

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNREGISTER:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SET_ALIAS:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v3

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNSET_ALIAS:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v4

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SET_ACCOUNT:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v5

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNSET_ACCOUNT:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v6

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SUBSCRIBE_TOPIC:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v7

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_UNSUBSCRIBE_TOPIC:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v8

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_SET_ACCEPT_TIME:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v9

    sget-object v1, Lcom/xiaomi/push/service/xmpush/Command;->COMMAND_CHK_VDEVID:Lcom/xiaomi/push/service/xmpush/Command;

    aput-object v1, v0, v10

    sput-object v0, Lcom/xiaomi/push/service/xmpush/Command;->$VALUES:[Lcom/xiaomi/push/service/xmpush/Command;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;ILjava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput-object p3, p0, Lcom/xiaomi/push/service/xmpush/Command;->value:Ljava/lang/String;

    return-void
.end method

.method public static getCode(Ljava/lang/String;)I
    .locals 6

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const/4 v1, -0x1

    if-eqz v0, :cond_0

    return v1

    :cond_0
    invoke-static {}, Lcom/xiaomi/push/service/xmpush/Command;->values()[Lcom/xiaomi/push/service/xmpush/Command;

    move-result-object v0

    array-length v2, v0

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_2

    aget-object v4, v0, v3

    iget-object v5, v4, Lcom/xiaomi/push/service/xmpush/Command;->value:Ljava/lang/String;

    invoke-virtual {v5, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-static {v4}, Lcom/xiaomi/push/service/clientReport/PushClientReportHelper;->changeOrdinalToCode(Ljava/lang/Enum;)I

    move-result v1

    :cond_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    return v1
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/push/service/xmpush/Command;
    .locals 1

    const-class v0, Lcom/xiaomi/push/service/xmpush/Command;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/push/service/xmpush/Command;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/push/service/xmpush/Command;
    .locals 1

    sget-object v0, Lcom/xiaomi/push/service/xmpush/Command;->$VALUES:[Lcom/xiaomi/push/service/xmpush/Command;

    invoke-virtual {v0}, [Lcom/xiaomi/push/service/xmpush/Command;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/push/service/xmpush/Command;

    return-object v0
.end method
