.class public final enum Lcom/miui/gallery/error/core/ErrorCode;
.super Ljava/lang/Enum;
.source "ErrorCode.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/error/core/ErrorCode;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum CONNECT_TIMEOUT:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum DATA_PARSE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum DECODE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum FILE_OPERATE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NETWORK_RESTRICT:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NOT_SYNCED:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NO_ACCOUNT:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NO_CHARGING:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NO_CTA_PERMISSION:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NO_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NO_NETWORK:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum NO_WIFI_CONNECTED:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum OVER_QUOTA:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum PARAMS_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum POWER_LOW:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum PRIMARY_STORAGE_WRITE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum RETRIABLE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum SECONDARY_STORAGE_WRITE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum SERVER_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum SOCKET_TIMEOUT:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum STORAGE_FULL:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum STORAGE_LOW:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum STORAGE_NO_WRITE_PERMISSION:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum SYNC_OFF:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum THUMBNAIL_BUILD_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum UNKNOWN:Lcom/miui/gallery/error/core/ErrorCode;

.field public static final enum WRITE_EXIF_ERROR:Lcom/miui/gallery/error/core/ErrorCode;


# direct methods
.method static constructor <clinit>()V
    .locals 16

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v1, 0x0

    const-string v2, "NO_ERROR"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NO_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v2, 0x1

    const-string v3, "NO_CTA_PERMISSION"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NO_CTA_PERMISSION:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v3, 0x2

    const-string v4, "NO_NETWORK"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NO_NETWORK:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v4, 0x3

    const-string v5, "NO_WIFI_CONNECTED"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NO_WIFI_CONNECTED:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v5, 0x4

    const-string v6, "NO_CHARGING"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NO_CHARGING:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v6, 0x5

    const-string v7, "POWER_LOW"

    invoke-direct {v0, v7, v6}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->POWER_LOW:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v7, 0x6

    const-string v8, "SYNC_OFF"

    invoke-direct {v0, v8, v7}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->SYNC_OFF:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/4 v8, 0x7

    const-string v9, "STORAGE_LOW"

    invoke-direct {v0, v9, v8}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->STORAGE_LOW:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v9, 0x8

    const-string v10, "STORAGE_FULL"

    invoke-direct {v0, v10, v9}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->STORAGE_FULL:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v10, 0x9

    const-string v11, "STORAGE_NO_WRITE_PERMISSION"

    invoke-direct {v0, v11, v10}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->STORAGE_NO_WRITE_PERMISSION:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v11, 0xa

    const-string v12, "PRIMARY_STORAGE_WRITE_ERROR"

    invoke-direct {v0, v12, v11}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->PRIMARY_STORAGE_WRITE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v12, 0xb

    const-string v13, "SECONDARY_STORAGE_WRITE_ERROR"

    invoke-direct {v0, v13, v12}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->SECONDARY_STORAGE_WRITE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v13, 0xc

    const-string v14, "DECODE_ERROR"

    invoke-direct {v0, v14, v13}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->DECODE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v14, 0xd

    const-string v15, "FILE_OPERATE_ERROR"

    invoke-direct {v0, v15, v14}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->FILE_OPERATE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v15, 0xe

    const-string v14, "WRITE_EXIF_ERROR"

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->WRITE_EXIF_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "NETWORK_RESTRICT"

    const/16 v15, 0xf

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NETWORK_RESTRICT:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "NO_ACCOUNT"

    const/16 v15, 0x10

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NO_ACCOUNT:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "CONNECT_TIMEOUT"

    const/16 v15, 0x11

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->CONNECT_TIMEOUT:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "SOCKET_TIMEOUT"

    const/16 v15, 0x12

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->SOCKET_TIMEOUT:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "OVER_QUOTA"

    const/16 v15, 0x13

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->OVER_QUOTA:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "NOT_SYNCED"

    const/16 v15, 0x14

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->NOT_SYNCED:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "PARAMS_ERROR"

    const/16 v15, 0x15

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->PARAMS_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "SERVER_ERROR"

    const/16 v15, 0x16

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->SERVER_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "DATA_PARSE_ERROR"

    const/16 v15, 0x17

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->DATA_PARSE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "RETRIABLE_ERROR"

    const/16 v15, 0x18

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->RETRIABLE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "THUMBNAIL_BUILD_ERROR"

    const/16 v15, 0x19

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->THUMBNAIL_BUILD_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    new-instance v0, Lcom/miui/gallery/error/core/ErrorCode;

    const-string v14, "UNKNOWN"

    const/16 v15, 0x1a

    invoke-direct {v0, v14, v15}, Lcom/miui/gallery/error/core/ErrorCode;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->UNKNOWN:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v0, 0x1b

    new-array v0, v0, [Lcom/miui/gallery/error/core/ErrorCode;

    sget-object v14, Lcom/miui/gallery/error/core/ErrorCode;->NO_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v14, v0, v1

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->NO_CTA_PERMISSION:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->NO_NETWORK:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->NO_WIFI_CONNECTED:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->NO_CHARGING:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->POWER_LOW:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v6

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->SYNC_OFF:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v7

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->STORAGE_LOW:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v8

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->STORAGE_FULL:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v9

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->STORAGE_NO_WRITE_PERMISSION:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v10

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->PRIMARY_STORAGE_WRITE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v11

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->SECONDARY_STORAGE_WRITE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v12

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->DECODE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    aput-object v1, v0, v13

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->FILE_OPERATE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0xd

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->WRITE_EXIF_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0xe

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->NETWORK_RESTRICT:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0xf

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->NO_ACCOUNT:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x10

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->CONNECT_TIMEOUT:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x11

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->SOCKET_TIMEOUT:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x12

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->OVER_QUOTA:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x13

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->NOT_SYNCED:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x14

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->PARAMS_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x15

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->SERVER_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x16

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->DATA_PARSE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x17

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->RETRIABLE_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x18

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->THUMBNAIL_BUILD_ERROR:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x19

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/error/core/ErrorCode;->UNKNOWN:Lcom/miui/gallery/error/core/ErrorCode;

    const/16 v2, 0x1a

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/error/core/ErrorCode;->$VALUES:[Lcom/miui/gallery/error/core/ErrorCode;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/error/core/ErrorCode;
    .locals 1

    const-class v0, Lcom/miui/gallery/error/core/ErrorCode;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/error/core/ErrorCode;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/error/core/ErrorCode;
    .locals 1

    sget-object v0, Lcom/miui/gallery/error/core/ErrorCode;->$VALUES:[Lcom/miui/gallery/error/core/ErrorCode;

    invoke-virtual {v0}, [Lcom/miui/gallery/error/core/ErrorCode;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/error/core/ErrorCode;

    return-object v0
.end method
