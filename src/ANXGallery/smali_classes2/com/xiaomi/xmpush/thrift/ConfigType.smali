.class public final enum Lcom/xiaomi/xmpush/thrift/ConfigType;
.super Ljava/lang/Enum;
.source "ConfigType.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/xmpush/thrift/ConfigType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/xmpush/thrift/ConfigType;

.field public static final enum BOOLEAN:Lcom/xiaomi/xmpush/thrift/ConfigType;

.field public static final enum INT:Lcom/xiaomi/xmpush/thrift/ConfigType;

.field public static final enum LONG:Lcom/xiaomi/xmpush/thrift/ConfigType;

.field public static final enum STRING:Lcom/xiaomi/xmpush/thrift/ConfigType;


# instance fields
.field private final value:I


# direct methods
.method static constructor <clinit>()V
    .locals 7

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ConfigType;

    const/4 v1, 0x0

    const/4 v2, 0x1

    const-string v3, "INT"

    invoke-direct {v0, v3, v1, v2}, Lcom/xiaomi/xmpush/thrift/ConfigType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ConfigType;->INT:Lcom/xiaomi/xmpush/thrift/ConfigType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ConfigType;

    const/4 v3, 0x2

    const-string v4, "LONG"

    invoke-direct {v0, v4, v2, v3}, Lcom/xiaomi/xmpush/thrift/ConfigType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ConfigType;->LONG:Lcom/xiaomi/xmpush/thrift/ConfigType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ConfigType;

    const/4 v4, 0x3

    const-string v5, "STRING"

    invoke-direct {v0, v5, v3, v4}, Lcom/xiaomi/xmpush/thrift/ConfigType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ConfigType;->STRING:Lcom/xiaomi/xmpush/thrift/ConfigType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ConfigType;

    const/4 v5, 0x4

    const-string v6, "BOOLEAN"

    invoke-direct {v0, v6, v4, v5}, Lcom/xiaomi/xmpush/thrift/ConfigType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ConfigType;->BOOLEAN:Lcom/xiaomi/xmpush/thrift/ConfigType;

    new-array v0, v5, [Lcom/xiaomi/xmpush/thrift/ConfigType;

    sget-object v5, Lcom/xiaomi/xmpush/thrift/ConfigType;->INT:Lcom/xiaomi/xmpush/thrift/ConfigType;

    aput-object v5, v0, v1

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ConfigType;->LONG:Lcom/xiaomi/xmpush/thrift/ConfigType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ConfigType;->STRING:Lcom/xiaomi/xmpush/thrift/ConfigType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ConfigType;->BOOLEAN:Lcom/xiaomi/xmpush/thrift/ConfigType;

    aput-object v1, v0, v4

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ConfigType;->$VALUES:[Lcom/xiaomi/xmpush/thrift/ConfigType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput p3, p0, Lcom/xiaomi/xmpush/thrift/ConfigType;->value:I

    return-void
.end method

.method public static findByValue(I)Lcom/xiaomi/xmpush/thrift/ConfigType;
    .locals 1

    const/4 v0, 0x1

    if-eq p0, v0, :cond_3

    const/4 v0, 0x2

    if-eq p0, v0, :cond_2

    const/4 v0, 0x3

    if-eq p0, v0, :cond_1

    const/4 v0, 0x4

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ConfigType;->BOOLEAN:Lcom/xiaomi/xmpush/thrift/ConfigType;

    return-object p0

    :cond_1
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ConfigType;->STRING:Lcom/xiaomi/xmpush/thrift/ConfigType;

    return-object p0

    :cond_2
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ConfigType;->LONG:Lcom/xiaomi/xmpush/thrift/ConfigType;

    return-object p0

    :cond_3
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ConfigType;->INT:Lcom/xiaomi/xmpush/thrift/ConfigType;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/xmpush/thrift/ConfigType;
    .locals 1

    const-class v0, Lcom/xiaomi/xmpush/thrift/ConfigType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/xmpush/thrift/ConfigType;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/xmpush/thrift/ConfigType;
    .locals 1

    sget-object v0, Lcom/xiaomi/xmpush/thrift/ConfigType;->$VALUES:[Lcom/xiaomi/xmpush/thrift/ConfigType;

    invoke-virtual {v0}, [Lcom/xiaomi/xmpush/thrift/ConfigType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/xmpush/thrift/ConfigType;

    return-object v0
.end method
