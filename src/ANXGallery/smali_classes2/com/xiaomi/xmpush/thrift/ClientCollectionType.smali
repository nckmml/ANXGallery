.class public final enum Lcom/xiaomi/xmpush/thrift/ClientCollectionType;
.super Ljava/lang/Enum;
.source "ClientCollectionType.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/xmpush/thrift/ClientCollectionType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum Account:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum ActivityActiveTimeStamp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum AppActiveList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum AppInstallList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum AppPermission:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum Battery:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum Bluetooth:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum BroadcastAction:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum BroadcastActionAdded:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum BroadcastActionChanged:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum BroadcastActionDataCleared:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum BroadcastActionRemoved:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum BroadcastActionReplaced:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum BroadcastActionRestarted:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum Cellular:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum DeviceBaseInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum DeviceInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum DeviceInfoV2:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum Location:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum Storage:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum TopApp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum WIFI:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

.field public static final enum WifiDevicesMac:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;


# instance fields
.field private final value:I


# direct methods
.method static constructor <clinit>()V
    .locals 16

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v1, 0x1

    const-string v2, "DeviceInfo"

    const/4 v3, 0x0

    invoke-direct {v0, v2, v3, v1}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v2, 0x2

    const-string v3, "AppInstallList"

    invoke-direct {v0, v3, v1, v2}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppInstallList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v3, 0x3

    const-string v4, "AppActiveList"

    invoke-direct {v0, v4, v2, v3}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppActiveList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v4, 0x4

    const-string v5, "Bluetooth"

    invoke-direct {v0, v5, v3, v4}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Bluetooth:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v5, 0x5

    const-string v6, "Location"

    invoke-direct {v0, v6, v4, v5}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Location:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v6, 0x6

    const-string v7, "Account"

    invoke-direct {v0, v7, v5, v6}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Account:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v7, 0x7

    const-string v8, "WIFI"

    invoke-direct {v0, v8, v6, v7}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->WIFI:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v8, 0x8

    const-string v9, "Cellular"

    invoke-direct {v0, v9, v7, v8}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Cellular:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v9, 0x9

    const-string v10, "TopApp"

    invoke-direct {v0, v10, v8, v9}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->TopApp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v10, 0xa

    const-string v11, "BroadcastAction"

    invoke-direct {v0, v11, v9, v10}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastAction:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v11, 0xb

    const-string v12, "BroadcastActionAdded"

    invoke-direct {v0, v12, v10, v11}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionAdded:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v12, 0xc

    const-string v13, "BroadcastActionRemoved"

    invoke-direct {v0, v13, v11, v12}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionRemoved:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v13, 0xd

    const-string v14, "BroadcastActionReplaced"

    invoke-direct {v0, v14, v12, v13}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionReplaced:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v14, 0xe

    const-string v15, "BroadcastActionDataCleared"

    invoke-direct {v0, v15, v13, v14}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionDataCleared:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v15, 0xf

    const-string v13, "BroadcastActionRestarted"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionRestarted:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "BroadcastActionChanged"

    const/16 v14, 0x10

    invoke-direct {v0, v13, v15, v14}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionChanged:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "AppPermission"

    const/16 v15, 0x11

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppPermission:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "WifiDevicesMac"

    const/16 v14, 0x11

    const/16 v15, 0x12

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->WifiDevicesMac:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "ActivityActiveTimeStamp"

    const/16 v14, 0x12

    const/16 v15, 0x13

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->ActivityActiveTimeStamp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "DeviceBaseInfo"

    const/16 v14, 0x13

    const/16 v15, 0x14

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceBaseInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "DeviceInfoV2"

    const/16 v14, 0x14

    const/16 v15, 0x15

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceInfoV2:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "Battery"

    const/16 v14, 0x15

    const/16 v15, 0x16

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Battery:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const-string v13, "Storage"

    const/16 v14, 0x16

    const/16 v15, 0x17

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Storage:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v0, 0x17

    new-array v0, v0, [Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    sget-object v13, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/4 v14, 0x0

    aput-object v13, v0, v14

    sget-object v13, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppInstallList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v13, v0, v1

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppActiveList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Bluetooth:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Location:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v4

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Account:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v5

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->WIFI:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v6

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Cellular:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v7

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->TopApp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v8

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastAction:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v9

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionAdded:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v10

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionRemoved:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v11

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionReplaced:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    aput-object v1, v0, v12

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionDataCleared:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0xd

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionRestarted:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0xe

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionChanged:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0xf

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppPermission:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0x10

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->WifiDevicesMac:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0x11

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->ActivityActiveTimeStamp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0x12

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceBaseInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0x13

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceInfoV2:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0x14

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Battery:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0x15

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Storage:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    const/16 v2, 0x16

    aput-object v1, v0, v2

    sput-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->$VALUES:[Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

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

    iput p3, p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->value:I

    return-void
.end method

.method public static findByValue(I)Lcom/xiaomi/xmpush/thrift/ClientCollectionType;
    .locals 0

    packed-switch p0, :pswitch_data_0

    const/4 p0, 0x0

    return-object p0

    :pswitch_0
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Storage:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_1
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Battery:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_2
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceInfoV2:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_3
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceBaseInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_4
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->ActivityActiveTimeStamp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_5
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->WifiDevicesMac:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_6
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppPermission:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_7
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionChanged:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_8
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionRestarted:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_9
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionDataCleared:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_a
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionReplaced:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_b
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionRemoved:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_c
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastActionAdded:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_d
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->BroadcastAction:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_e
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->TopApp:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_f
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Cellular:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_10
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->WIFI:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_11
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Account:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_12
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Location:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_13
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->Bluetooth:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_14
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppActiveList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_15
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->AppInstallList:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_16
    sget-object p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->DeviceInfo:Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_f
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/xmpush/thrift/ClientCollectionType;
    .locals 1

    const-class v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/xmpush/thrift/ClientCollectionType;
    .locals 1

    sget-object v0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->$VALUES:[Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    invoke-virtual {v0}, [Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/xmpush/thrift/ClientCollectionType;

    return-object v0
.end method


# virtual methods
.method public getValue()I
    .locals 1

    iget v0, p0, Lcom/xiaomi/xmpush/thrift/ClientCollectionType;->value:I

    return v0
.end method
