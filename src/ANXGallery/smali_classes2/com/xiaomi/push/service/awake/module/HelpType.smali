.class public final enum Lcom/xiaomi/push/service/awake/module/HelpType;
.super Ljava/lang/Enum;
.source "HelpType.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/push/service/awake/module/HelpType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/push/service/awake/module/HelpType;

.field public static final enum ACTIVITY:Lcom/xiaomi/push/service/awake/module/HelpType;

.field public static final enum PROVIDER:Lcom/xiaomi/push/service/awake/module/HelpType;

.field public static final enum SERVICE_ACTION:Lcom/xiaomi/push/service/awake/module/HelpType;

.field public static final enum SERVICE_COMPONENT:Lcom/xiaomi/push/service/awake/module/HelpType;


# instance fields
.field public typeValue:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 7

    new-instance v0, Lcom/xiaomi/push/service/awake/module/HelpType;

    const/4 v1, 0x0

    const-string v2, "ACTIVITY"

    const-string v3, "activity"

    invoke-direct {v0, v2, v1, v3}, Lcom/xiaomi/push/service/awake/module/HelpType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/awake/module/HelpType;->ACTIVITY:Lcom/xiaomi/push/service/awake/module/HelpType;

    new-instance v0, Lcom/xiaomi/push/service/awake/module/HelpType;

    const/4 v2, 0x1

    const-string v3, "SERVICE_ACTION"

    const-string v4, "service_action"

    invoke-direct {v0, v3, v2, v4}, Lcom/xiaomi/push/service/awake/module/HelpType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/awake/module/HelpType;->SERVICE_ACTION:Lcom/xiaomi/push/service/awake/module/HelpType;

    new-instance v0, Lcom/xiaomi/push/service/awake/module/HelpType;

    const/4 v3, 0x2

    const-string v4, "SERVICE_COMPONENT"

    const-string v5, "service_component"

    invoke-direct {v0, v4, v3, v5}, Lcom/xiaomi/push/service/awake/module/HelpType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/awake/module/HelpType;->SERVICE_COMPONENT:Lcom/xiaomi/push/service/awake/module/HelpType;

    new-instance v0, Lcom/xiaomi/push/service/awake/module/HelpType;

    const/4 v4, 0x3

    const-string v5, "PROVIDER"

    const-string v6, "provider"

    invoke-direct {v0, v5, v4, v6}, Lcom/xiaomi/push/service/awake/module/HelpType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/push/service/awake/module/HelpType;->PROVIDER:Lcom/xiaomi/push/service/awake/module/HelpType;

    const/4 v0, 0x4

    new-array v0, v0, [Lcom/xiaomi/push/service/awake/module/HelpType;

    sget-object v5, Lcom/xiaomi/push/service/awake/module/HelpType;->ACTIVITY:Lcom/xiaomi/push/service/awake/module/HelpType;

    aput-object v5, v0, v1

    sget-object v1, Lcom/xiaomi/push/service/awake/module/HelpType;->SERVICE_ACTION:Lcom/xiaomi/push/service/awake/module/HelpType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/push/service/awake/module/HelpType;->SERVICE_COMPONENT:Lcom/xiaomi/push/service/awake/module/HelpType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/xiaomi/push/service/awake/module/HelpType;->PROVIDER:Lcom/xiaomi/push/service/awake/module/HelpType;

    aput-object v1, v0, v4

    sput-object v0, Lcom/xiaomi/push/service/awake/module/HelpType;->$VALUES:[Lcom/xiaomi/push/service/awake/module/HelpType;

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

    iput-object p3, p0, Lcom/xiaomi/push/service/awake/module/HelpType;->typeValue:Ljava/lang/String;

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/push/service/awake/module/HelpType;
    .locals 1

    const-class v0, Lcom/xiaomi/push/service/awake/module/HelpType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/push/service/awake/module/HelpType;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/push/service/awake/module/HelpType;
    .locals 1

    sget-object v0, Lcom/xiaomi/push/service/awake/module/HelpType;->$VALUES:[Lcom/xiaomi/push/service/awake/module/HelpType;

    invoke-virtual {v0}, [Lcom/xiaomi/push/service/awake/module/HelpType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/push/service/awake/module/HelpType;

    return-object v0
.end method
