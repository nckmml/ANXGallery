.class public final enum Lcom/miui/gallery/push/PushConstants$MessageType;
.super Ljava/lang/Enum;
.source "PushConstants.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/push/PushConstants;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "MessageType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/push/PushConstants$MessageType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/push/PushConstants$MessageType;

.field public static final enum DIRECT:Lcom/miui/gallery/push/PushConstants$MessageType;

.field public static final enum SYNC:Lcom/miui/gallery/push/PushConstants$MessageType;


# instance fields
.field private value:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    new-instance v0, Lcom/miui/gallery/push/PushConstants$MessageType;

    const/4 v1, 0x0

    const-string v2, "SYNC"

    const-string v3, "sync"

    invoke-direct {v0, v2, v1, v3}, Lcom/miui/gallery/push/PushConstants$MessageType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/miui/gallery/push/PushConstants$MessageType;->SYNC:Lcom/miui/gallery/push/PushConstants$MessageType;

    new-instance v0, Lcom/miui/gallery/push/PushConstants$MessageType;

    const/4 v2, 0x1

    const-string v3, "DIRECT"

    const-string v4, "direct"

    invoke-direct {v0, v3, v2, v4}, Lcom/miui/gallery/push/PushConstants$MessageType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/miui/gallery/push/PushConstants$MessageType;->DIRECT:Lcom/miui/gallery/push/PushConstants$MessageType;

    const/4 v0, 0x2

    new-array v0, v0, [Lcom/miui/gallery/push/PushConstants$MessageType;

    sget-object v3, Lcom/miui/gallery/push/PushConstants$MessageType;->SYNC:Lcom/miui/gallery/push/PushConstants$MessageType;

    aput-object v3, v0, v1

    sget-object v1, Lcom/miui/gallery/push/PushConstants$MessageType;->DIRECT:Lcom/miui/gallery/push/PushConstants$MessageType;

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/push/PushConstants$MessageType;->$VALUES:[Lcom/miui/gallery/push/PushConstants$MessageType;

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

    iput-object p3, p0, Lcom/miui/gallery/push/PushConstants$MessageType;->value:Ljava/lang/String;

    return-void
.end method

.method public static getType(Ljava/lang/String;)Lcom/miui/gallery/push/PushConstants$MessageType;
    .locals 5

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    invoke-static {}, Lcom/miui/gallery/push/PushConstants$MessageType;->values()[Lcom/miui/gallery/push/PushConstants$MessageType;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_1

    aget-object v3, v0, v2

    iget-object v4, v3, Lcom/miui/gallery/push/PushConstants$MessageType;->value:Ljava/lang/String;

    invoke-virtual {p0, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_0

    return-object v3

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/push/PushConstants$MessageType;
    .locals 1

    const-class v0, Lcom/miui/gallery/push/PushConstants$MessageType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/push/PushConstants$MessageType;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/push/PushConstants$MessageType;
    .locals 1

    sget-object v0, Lcom/miui/gallery/push/PushConstants$MessageType;->$VALUES:[Lcom/miui/gallery/push/PushConstants$MessageType;

    invoke-virtual {v0}, [Lcom/miui/gallery/push/PushConstants$MessageType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/push/PushConstants$MessageType;

    return-object v0
.end method
