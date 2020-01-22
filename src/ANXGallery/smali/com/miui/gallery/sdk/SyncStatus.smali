.class public final enum Lcom/miui/gallery/sdk/SyncStatus;
.super Ljava/lang/Enum;
.source "SyncStatus.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/sdk/SyncStatus;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/sdk/SyncStatus;

.field public static final enum STATUS_ABADON:Lcom/miui/gallery/sdk/SyncStatus;

.field public static final enum STATUS_INIT:Lcom/miui/gallery/sdk/SyncStatus;

.field public static final enum STATUS_INTERRUPT:Lcom/miui/gallery/sdk/SyncStatus;

.field public static final enum STATUS_NONE:Lcom/miui/gallery/sdk/SyncStatus;

.field public static final enum STATUS_SUCCESS:Lcom/miui/gallery/sdk/SyncStatus;


# direct methods
.method static constructor <clinit>()V
    .locals 7

    new-instance v0, Lcom/miui/gallery/sdk/SyncStatus;

    const/4 v1, 0x0

    const-string v2, "STATUS_NONE"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/sdk/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_NONE:Lcom/miui/gallery/sdk/SyncStatus;

    new-instance v0, Lcom/miui/gallery/sdk/SyncStatus;

    const/4 v2, 0x1

    const-string v3, "STATUS_INIT"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/sdk/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_INIT:Lcom/miui/gallery/sdk/SyncStatus;

    new-instance v0, Lcom/miui/gallery/sdk/SyncStatus;

    const/4 v3, 0x2

    const-string v4, "STATUS_INTERRUPT"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/sdk/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_INTERRUPT:Lcom/miui/gallery/sdk/SyncStatus;

    new-instance v0, Lcom/miui/gallery/sdk/SyncStatus;

    const/4 v4, 0x3

    const-string v5, "STATUS_SUCCESS"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/sdk/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_SUCCESS:Lcom/miui/gallery/sdk/SyncStatus;

    new-instance v0, Lcom/miui/gallery/sdk/SyncStatus;

    const/4 v5, 0x4

    const-string v6, "STATUS_ABADON"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/sdk/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_ABADON:Lcom/miui/gallery/sdk/SyncStatus;

    const/4 v0, 0x5

    new-array v0, v0, [Lcom/miui/gallery/sdk/SyncStatus;

    sget-object v6, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_NONE:Lcom/miui/gallery/sdk/SyncStatus;

    aput-object v6, v0, v1

    sget-object v1, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_INIT:Lcom/miui/gallery/sdk/SyncStatus;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_INTERRUPT:Lcom/miui/gallery/sdk/SyncStatus;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_SUCCESS:Lcom/miui/gallery/sdk/SyncStatus;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_ABADON:Lcom/miui/gallery/sdk/SyncStatus;

    aput-object v1, v0, v5

    sput-object v0, Lcom/miui/gallery/sdk/SyncStatus;->$VALUES:[Lcom/miui/gallery/sdk/SyncStatus;

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

.method public static toSyncStatus(I)Lcom/miui/gallery/sdk/SyncStatus;
    .locals 3

    const/4 v0, -0x1

    if-eq p0, v0, :cond_4

    if-eqz p0, :cond_3

    const/4 v0, 0x1

    if-eq p0, v0, :cond_2

    const/4 v0, 0x2

    if-eq p0, v0, :cond_1

    const/4 v0, 0x3

    if-ne p0, v0, :cond_0

    sget-object p0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_ABADON:Lcom/miui/gallery/sdk/SyncStatus;

    return-object p0

    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "unknown status: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_1
    sget-object p0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_SUCCESS:Lcom/miui/gallery/sdk/SyncStatus;

    return-object p0

    :cond_2
    sget-object p0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_INTERRUPT:Lcom/miui/gallery/sdk/SyncStatus;

    return-object p0

    :cond_3
    sget-object p0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_INIT:Lcom/miui/gallery/sdk/SyncStatus;

    return-object p0

    :cond_4
    sget-object p0, Lcom/miui/gallery/sdk/SyncStatus;->STATUS_NONE:Lcom/miui/gallery/sdk/SyncStatus;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/sdk/SyncStatus;
    .locals 1

    const-class v0, Lcom/miui/gallery/sdk/SyncStatus;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/sdk/SyncStatus;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/sdk/SyncStatus;
    .locals 1

    sget-object v0, Lcom/miui/gallery/sdk/SyncStatus;->$VALUES:[Lcom/miui/gallery/sdk/SyncStatus;

    invoke-virtual {v0}, [Lcom/miui/gallery/sdk/SyncStatus;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/sdk/SyncStatus;

    return-object v0
.end method
