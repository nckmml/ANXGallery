.class public final enum Lcom/xiaomi/channel/commonutils/android/Region;
.super Ljava/lang/Enum;
.source "Region.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/channel/commonutils/android/Region;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/channel/commonutils/android/Region;

.field public static final enum China:Lcom/xiaomi/channel/commonutils/android/Region;

.field public static final enum Europe:Lcom/xiaomi/channel/commonutils/android/Region;

.field public static final enum Global:Lcom/xiaomi/channel/commonutils/android/Region;

.field public static final enum India:Lcom/xiaomi/channel/commonutils/android/Region;

.field public static final enum Russia:Lcom/xiaomi/channel/commonutils/android/Region;


# direct methods
.method static constructor <clinit>()V
    .locals 7

    new-instance v0, Lcom/xiaomi/channel/commonutils/android/Region;

    const/4 v1, 0x0

    const-string v2, "China"

    invoke-direct {v0, v2, v1}, Lcom/xiaomi/channel/commonutils/android/Region;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/channel/commonutils/android/Region;->China:Lcom/xiaomi/channel/commonutils/android/Region;

    new-instance v0, Lcom/xiaomi/channel/commonutils/android/Region;

    const/4 v2, 0x1

    const-string v3, "Global"

    invoke-direct {v0, v3, v2}, Lcom/xiaomi/channel/commonutils/android/Region;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/channel/commonutils/android/Region;->Global:Lcom/xiaomi/channel/commonutils/android/Region;

    new-instance v0, Lcom/xiaomi/channel/commonutils/android/Region;

    const/4 v3, 0x2

    const-string v4, "Europe"

    invoke-direct {v0, v4, v3}, Lcom/xiaomi/channel/commonutils/android/Region;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/channel/commonutils/android/Region;->Europe:Lcom/xiaomi/channel/commonutils/android/Region;

    new-instance v0, Lcom/xiaomi/channel/commonutils/android/Region;

    const/4 v4, 0x3

    const-string v5, "Russia"

    invoke-direct {v0, v5, v4}, Lcom/xiaomi/channel/commonutils/android/Region;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/channel/commonutils/android/Region;->Russia:Lcom/xiaomi/channel/commonutils/android/Region;

    new-instance v0, Lcom/xiaomi/channel/commonutils/android/Region;

    const/4 v5, 0x4

    const-string v6, "India"

    invoke-direct {v0, v6, v5}, Lcom/xiaomi/channel/commonutils/android/Region;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/xiaomi/channel/commonutils/android/Region;->India:Lcom/xiaomi/channel/commonutils/android/Region;

    const/4 v0, 0x5

    new-array v0, v0, [Lcom/xiaomi/channel/commonutils/android/Region;

    sget-object v6, Lcom/xiaomi/channel/commonutils/android/Region;->China:Lcom/xiaomi/channel/commonutils/android/Region;

    aput-object v6, v0, v1

    sget-object v1, Lcom/xiaomi/channel/commonutils/android/Region;->Global:Lcom/xiaomi/channel/commonutils/android/Region;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/channel/commonutils/android/Region;->Europe:Lcom/xiaomi/channel/commonutils/android/Region;

    aput-object v1, v0, v3

    sget-object v1, Lcom/xiaomi/channel/commonutils/android/Region;->Russia:Lcom/xiaomi/channel/commonutils/android/Region;

    aput-object v1, v0, v4

    sget-object v1, Lcom/xiaomi/channel/commonutils/android/Region;->India:Lcom/xiaomi/channel/commonutils/android/Region;

    aput-object v1, v0, v5

    sput-object v0, Lcom/xiaomi/channel/commonutils/android/Region;->$VALUES:[Lcom/xiaomi/channel/commonutils/android/Region;

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

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/channel/commonutils/android/Region;
    .locals 1

    const-class v0, Lcom/xiaomi/channel/commonutils/android/Region;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/channel/commonutils/android/Region;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/channel/commonutils/android/Region;
    .locals 1

    sget-object v0, Lcom/xiaomi/channel/commonutils/android/Region;->$VALUES:[Lcom/xiaomi/channel/commonutils/android/Region;

    invoke-virtual {v0}, [Lcom/xiaomi/channel/commonutils/android/Region;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/channel/commonutils/android/Region;

    return-object v0
.end method
