.class public final enum Lcom/miui/gallery/cloud/syncstate/SyncStatus;
.super Ljava/lang/Enum;
.source "SyncStatus.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/cloud/syncstate/SyncStatus;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum BATTERY_LOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum CLOUD_SPACE_FULL:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum CTA_NOT_ALLOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum DISCONNECTED:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum MASTER_SYNC_OFF:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum NO_ACCOUNT:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum NO_WIFI:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNCED:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNCED_WITH_OVERSIZED_FILE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNCING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNCING_METADATA:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNC_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNC_META_PENDING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNC_OFF:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNC_PAUSE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYNC_PENDING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum SYSTEM_SPACE_LOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum UNAVAILABLE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

.field public static final enum UNKNOWN_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;


# direct methods
.method static constructor <clinit>()V
    .locals 16

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v1, 0x0

    const-string v2, "SYNC_PENDING"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_PENDING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v2, 0x1

    const-string v3, "SYNCING"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v3, 0x2

    const-string v4, "SYNCING_METADATA"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCING_METADATA:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v4, 0x3

    const-string v5, "SYNC_META_PENDING"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_META_PENDING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v5, 0x4

    const-string v6, "SYNCED"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCED:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v6, 0x5

    const-string v7, "SYNC_PAUSE"

    invoke-direct {v0, v7, v6}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_PAUSE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v7, 0x6

    const-string v8, "SYNC_ERROR"

    invoke-direct {v0, v8, v7}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/4 v8, 0x7

    const-string v9, "SYNCED_WITH_OVERSIZED_FILE"

    invoke-direct {v0, v9, v8}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCED_WITH_OVERSIZED_FILE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v9, 0x8

    const-string v10, "NO_ACCOUNT"

    invoke-direct {v0, v10, v9}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->NO_ACCOUNT:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v10, 0x9

    const-string v11, "MASTER_SYNC_OFF"

    invoke-direct {v0, v11, v10}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->MASTER_SYNC_OFF:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v11, 0xa

    const-string v12, "SYNC_OFF"

    invoke-direct {v0, v12, v11}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_OFF:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v12, 0xb

    const-string v13, "CTA_NOT_ALLOW"

    invoke-direct {v0, v13, v12}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->CTA_NOT_ALLOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v13, 0xc

    const-string v14, "DISCONNECTED"

    invoke-direct {v0, v14, v13}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->DISCONNECTED:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v14, 0xd

    const-string v15, "NO_WIFI"

    invoke-direct {v0, v15, v14}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->NO_WIFI:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v15, 0xe

    const-string v14, "BATTERY_LOW"

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->BATTERY_LOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const-string v14, "SYSTEM_SPACE_LOW"

    const/16 v15, 0xf

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYSTEM_SPACE_LOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const-string v14, "CLOUD_SPACE_FULL"

    const/16 v15, 0x10

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->CLOUD_SPACE_FULL:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const-string v14, "UNKNOWN_ERROR"

    const/16 v15, 0x11

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->UNKNOWN_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const-string v14, "UNAVAILABLE"

    const/16 v15, 0x12

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->UNAVAILABLE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v0, 0x13

    new-array v0, v0, [Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    sget-object v14, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_PENDING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v14, v0, v1

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCING_METADATA:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_META_PENDING:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCED:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_PAUSE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v6

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v7

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNCED_WITH_OVERSIZED_FILE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v8

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->NO_ACCOUNT:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v9

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->MASTER_SYNC_OFF:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v10

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYNC_OFF:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v11

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->CTA_NOT_ALLOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v12

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->DISCONNECTED:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    aput-object v1, v0, v13

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->NO_WIFI:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v2, 0xd

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->BATTERY_LOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v2, 0xe

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->SYSTEM_SPACE_LOW:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v2, 0xf

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->CLOUD_SPACE_FULL:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v2, 0x10

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->UNKNOWN_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v2, 0x11

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->UNAVAILABLE:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    const/16 v2, 0x12

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->$VALUES:[Lcom/miui/gallery/cloud/syncstate/SyncStatus;

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

.method public static fromName(Ljava/lang/String;)Lcom/miui/gallery/cloud/syncstate/SyncStatus;
    .locals 5

    invoke-static {p0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->UNKNOWN_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    return-object p0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->values()[Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_2

    aget-object v3, v0, v2

    invoke-virtual {v3}, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->name()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4, p0}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_1

    return-object v3

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    sget-object p0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->UNKNOWN_ERROR:Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/cloud/syncstate/SyncStatus;
    .locals 1

    const-class v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/cloud/syncstate/SyncStatus;
    .locals 1

    sget-object v0, Lcom/miui/gallery/cloud/syncstate/SyncStatus;->$VALUES:[Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    invoke-virtual {v0}, [Lcom/miui/gallery/cloud/syncstate/SyncStatus;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/cloud/syncstate/SyncStatus;

    return-object v0
.end method
