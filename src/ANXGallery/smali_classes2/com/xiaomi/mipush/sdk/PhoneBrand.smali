.class public final enum Lcom/xiaomi/mipush/sdk/PhoneBrand;
.super Ljava/lang/Enum;
.source "PhoneBrand.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/mipush/sdk/PhoneBrand;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/mipush/sdk/PhoneBrand;

.field public static final enum FCM:Lcom/xiaomi/mipush/sdk/PhoneBrand;

.field public static final enum HUAWEI:Lcom/xiaomi/mipush/sdk/PhoneBrand;

.field public static final enum MEIZU:Lcom/xiaomi/mipush/sdk/PhoneBrand;

.field public static final enum OPPO:Lcom/xiaomi/mipush/sdk/PhoneBrand;

.field public static final enum OTHER:Lcom/xiaomi/mipush/sdk/PhoneBrand;

.field public static final enum VIVO:Lcom/xiaomi/mipush/sdk/PhoneBrand;


# direct methods
.method static constructor <clinit>()V
    .locals 8

    new-instance v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    const/4 v1, 0x0

    const-string v2, "HUAWEI"

    invoke-direct {v0, v2, v1}, Lcom/xiaomi/mipush/sdk/PhoneBrand;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->HUAWEI:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    new-instance v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    const/4 v2, 0x1

    const-string v3, "MEIZU"

    invoke-direct {v0, v3, v2}, Lcom/xiaomi/mipush/sdk/PhoneBrand;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->MEIZU:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    new-instance v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    const/4 v3, 0x2

    const-string v4, "FCM"

    invoke-direct {v0, v4, v3}, Lcom/xiaomi/mipush/sdk/PhoneBrand;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->FCM:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    new-instance v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    const/4 v4, 0x3

    const-string v5, "OPPO"

    invoke-direct {v0, v5, v4}, Lcom/xiaomi/mipush/sdk/PhoneBrand;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->OPPO:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    new-instance v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    const/4 v5, 0x4

    const-string v6, "VIVO"

    invoke-direct {v0, v6, v5}, Lcom/xiaomi/mipush/sdk/PhoneBrand;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->VIVO:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    new-instance v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    const/4 v6, 0x5

    const-string v7, "OTHER"

    invoke-direct {v0, v7, v6}, Lcom/xiaomi/mipush/sdk/PhoneBrand;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->OTHER:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    const/4 v0, 0x6

    new-array v0, v0, [Lcom/xiaomi/mipush/sdk/PhoneBrand;

    sget-object v7, Lcom/xiaomi/mipush/sdk/PhoneBrand;->HUAWEI:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    aput-object v7, v0, v1

    sget-object v1, Lcom/xiaomi/mipush/sdk/PhoneBrand;->MEIZU:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/mipush/sdk/PhoneBrand;->FCM:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    aput-object v1, v0, v3

    sget-object v1, Lcom/xiaomi/mipush/sdk/PhoneBrand;->OPPO:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    aput-object v1, v0, v4

    sget-object v1, Lcom/xiaomi/mipush/sdk/PhoneBrand;->VIVO:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    aput-object v1, v0, v5

    sget-object v1, Lcom/xiaomi/mipush/sdk/PhoneBrand;->OTHER:Lcom/xiaomi/mipush/sdk/PhoneBrand;

    aput-object v1, v0, v6

    sput-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->$VALUES:[Lcom/xiaomi/mipush/sdk/PhoneBrand;

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

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/mipush/sdk/PhoneBrand;
    .locals 1

    const-class v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/mipush/sdk/PhoneBrand;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/mipush/sdk/PhoneBrand;
    .locals 1

    sget-object v0, Lcom/xiaomi/mipush/sdk/PhoneBrand;->$VALUES:[Lcom/xiaomi/mipush/sdk/PhoneBrand;

    invoke-virtual {v0}, [Lcom/xiaomi/mipush/sdk/PhoneBrand;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/mipush/sdk/PhoneBrand;

    return-object v0
.end method
