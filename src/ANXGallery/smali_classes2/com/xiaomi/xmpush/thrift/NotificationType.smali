.class public final enum Lcom/xiaomi/xmpush/thrift/NotificationType;
.super Ljava/lang/Enum;
.source "NotificationType.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/xiaomi/xmpush/thrift/NotificationType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum APP_SLEEP:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum APP_WAKEUP:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum AppOpen:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum AppUninstall:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum AwakeApp:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum AwakeAppResponse:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum AwakeSystemApp:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum BarCancel:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum BarClick:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum CancelPushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum ClientABTest:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum ClientInfoUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum ClientInfoUpdateOk:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum ClientMIIDUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum ConnectionDisabled:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum CustomClientConfigUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum DailyCheckClientConfig:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum DataCollection:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum DecryptMessageFail:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum DisablePushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum EnablePushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum ForceSync:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum HybridRegister:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum HybridRegisterResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum HybridUnregister:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum HybridUnregisterResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum Invalid:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum IosSleep:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum IosWakeUp:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum NOTIFICATION_SWITCH:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum NormalClientConfigUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum NotificationBarInfo:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum PackageUninstall:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum PackageUnregistered:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum PullOfflineMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum PushLogUpload:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum RegIdExpired:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum SyncInfo:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum SyncInfoResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum SyncMIID:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum ThirdPartyRegUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum UploadClientLog:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum UploadTinyData:Lcom/xiaomi/xmpush/thrift/NotificationType;

.field public static final enum VRUpload:Lcom/xiaomi/xmpush/thrift/NotificationType;


# instance fields
.field public final value:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 16

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v1, 0x0

    const-string v2, "Invalid"

    const-string v3, "INVALID"

    invoke-direct {v0, v2, v1, v3}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->Invalid:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v2, 0x1

    const-string v3, "BarClick"

    const-string v4, "bar:click"

    invoke-direct {v0, v3, v2, v4}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->BarClick:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v3, 0x2

    const-string v4, "BarCancel"

    const-string v5, "bar:cancel"

    invoke-direct {v0, v4, v3, v5}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->BarCancel:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v4, 0x3

    const-string v5, "AppOpen"

    const-string v6, "app:open"

    invoke-direct {v0, v5, v4, v6}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->AppOpen:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v5, 0x4

    const-string v6, "PackageUninstall"

    const-string v7, "package uninstalled"

    invoke-direct {v0, v6, v5, v7}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->PackageUninstall:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v6, 0x5

    const-string v7, "AppUninstall"

    const-string v8, "app_uninstalled"

    invoke-direct {v0, v7, v6, v8}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->AppUninstall:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v7, 0x6

    const-string v8, "ClientInfoUpdate"

    const-string v9, "client_info_update"

    invoke-direct {v0, v8, v7, v9}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientInfoUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/4 v8, 0x7

    const-string v9, "ClientInfoUpdateOk"

    const-string v10, "client_info_update_ok"

    invoke-direct {v0, v9, v8, v10}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientInfoUpdateOk:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v9, 0x8

    const-string v10, "ClientMIIDUpdate"

    const-string v11, "client_miid_update"

    invoke-direct {v0, v10, v9, v11}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientMIIDUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v10, 0x9

    const-string v11, "PullOfflineMessage"

    const-string v12, "pull"

    invoke-direct {v0, v11, v10, v12}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->PullOfflineMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v11, 0xa

    const-string v12, "IosSleep"

    const-string v13, "ios_sleep"

    invoke-direct {v0, v12, v11, v13}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->IosSleep:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v12, 0xb

    const-string v13, "IosWakeUp"

    const-string v14, "ios_wakeup"

    invoke-direct {v0, v13, v12, v14}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->IosWakeUp:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v13, 0xc

    const-string v14, "AwakeApp"

    const-string v15, "awake_app"

    invoke-direct {v0, v14, v13, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->AwakeApp:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v14, 0xd

    const-string v15, "NormalClientConfigUpdate"

    const-string v13, "normal_client_config_update"

    invoke-direct {v0, v15, v14, v13}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->NormalClientConfigUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v13, 0xe

    const-string v15, "CustomClientConfigUpdate"

    const-string v14, "custom_client_config_update"

    invoke-direct {v0, v15, v13, v14}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->CustomClientConfigUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v14, "DailyCheckClientConfig"

    const/16 v15, 0xf

    const-string v13, "daily_check_client_config"

    invoke-direct {v0, v14, v15, v13}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->DailyCheckClientConfig:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "DataCollection"

    const/16 v14, 0x10

    const-string v15, "data_collection"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->DataCollection:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "RegIdExpired"

    const/16 v14, 0x11

    const-string v15, "registration id expired"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->RegIdExpired:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "ConnectionDisabled"

    const/16 v14, 0x12

    const-string v15, "!!!MILINK CONNECTION DISABLED!!!"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->ConnectionDisabled:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "PackageUnregistered"

    const/16 v14, 0x13

    const-string v15, "package_unregistered"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->PackageUnregistered:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "DecryptMessageFail"

    const/16 v14, 0x14

    const-string v15, "decrypt_msg_fail"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->DecryptMessageFail:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "SyncInfo"

    const/16 v14, 0x15

    const-string v15, "sync_info"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->SyncInfo:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "SyncInfoResult"

    const/16 v14, 0x16

    const-string v15, "sync_info_result"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->SyncInfoResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "ForceSync"

    const/16 v14, 0x17

    const-string v15, "force_sync"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->ForceSync:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "UploadClientLog"

    const/16 v14, 0x18

    const-string v15, "upload_client_log"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->UploadClientLog:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "NotificationBarInfo"

    const/16 v14, 0x19

    const-string v15, "notification_bar_info"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->NotificationBarInfo:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "SyncMIID"

    const/16 v14, 0x1a

    const-string v15, "sync_miid"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->SyncMIID:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "UploadTinyData"

    const/16 v14, 0x1b

    const-string v15, "upload"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->UploadTinyData:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "CancelPushMessage"

    const/16 v14, 0x1c

    const-string v15, "clear_push_message"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->CancelPushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "DisablePushMessage"

    const/16 v14, 0x1d

    const-string v15, "disable_push"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->DisablePushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "EnablePushMessage"

    const/16 v14, 0x1e

    const-string v15, "enable_push"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->EnablePushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "ClientABTest"

    const/16 v14, 0x1f

    const-string v15, "client_ab_test"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientABTest:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "AwakeSystemApp"

    const/16 v14, 0x20

    const-string v15, "awake_system_app"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->AwakeSystemApp:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "AwakeAppResponse"

    const/16 v14, 0x21

    const-string v15, "awake_app_response"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->AwakeAppResponse:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "HybridRegister"

    const/16 v14, 0x22

    const-string v15, "hb_register"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridRegister:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "HybridRegisterResult"

    const/16 v14, 0x23

    const-string v15, "hb_register_res"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridRegisterResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "HybridUnregister"

    const/16 v14, 0x24

    const-string v15, "hb_unregister"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridUnregister:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "HybridUnregisterResult"

    const/16 v14, 0x25

    const-string v15, "hb_unregister_res"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridUnregisterResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "ThirdPartyRegUpdate"

    const/16 v14, 0x26

    const-string v15, "3rd_party_reg_update"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->ThirdPartyRegUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "VRUpload"

    const/16 v14, 0x27

    const-string v15, "vr_upload"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->VRUpload:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "PushLogUpload"

    const/16 v14, 0x28

    const-string v15, "log_upload"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->PushLogUpload:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "APP_WAKEUP"

    const/16 v14, 0x29

    const-string v15, "app_wakeup"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->APP_WAKEUP:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "APP_SLEEP"

    const/16 v14, 0x2a

    const-string v15, "app_sleep"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->APP_SLEEP:Lcom/xiaomi/xmpush/thrift/NotificationType;

    new-instance v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    const-string v13, "NOTIFICATION_SWITCH"

    const/16 v14, 0x2b

    const-string v15, "notification_switch"

    invoke-direct {v0, v13, v14, v15}, Lcom/xiaomi/xmpush/thrift/NotificationType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->NOTIFICATION_SWITCH:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v0, 0x2c

    new-array v0, v0, [Lcom/xiaomi/xmpush/thrift/NotificationType;

    sget-object v13, Lcom/xiaomi/xmpush/thrift/NotificationType;->Invalid:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v13, v0, v1

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->BarClick:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->BarCancel:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->AppOpen:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v4

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->PackageUninstall:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v5

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->AppUninstall:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v6

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientInfoUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v7

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientInfoUpdateOk:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v8

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientMIIDUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v9

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->PullOfflineMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v10

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->IosSleep:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v11

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->IosWakeUp:Lcom/xiaomi/xmpush/thrift/NotificationType;

    aput-object v1, v0, v12

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->AwakeApp:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0xc

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->NormalClientConfigUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0xd

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->CustomClientConfigUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0xe

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->DailyCheckClientConfig:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0xf

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->DataCollection:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x10

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->RegIdExpired:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x11

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->ConnectionDisabled:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x12

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->PackageUnregistered:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x13

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->DecryptMessageFail:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x14

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->SyncInfo:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x15

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->SyncInfoResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x16

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->ForceSync:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x17

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->UploadClientLog:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x18

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->NotificationBarInfo:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x19

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->SyncMIID:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x1a

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->UploadTinyData:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x1b

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->CancelPushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x1c

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->DisablePushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x1d

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->EnablePushMessage:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x1e

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->ClientABTest:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x1f

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->AwakeSystemApp:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x20

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->AwakeAppResponse:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x21

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridRegister:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x22

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridRegisterResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x23

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridUnregister:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x24

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->HybridUnregisterResult:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x25

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->ThirdPartyRegUpdate:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x26

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->VRUpload:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x27

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->PushLogUpload:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x28

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->APP_WAKEUP:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x29

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->APP_SLEEP:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x2a

    aput-object v1, v0, v2

    sget-object v1, Lcom/xiaomi/xmpush/thrift/NotificationType;->NOTIFICATION_SWITCH:Lcom/xiaomi/xmpush/thrift/NotificationType;

    const/16 v2, 0x2b

    aput-object v1, v0, v2

    sput-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->$VALUES:[Lcom/xiaomi/xmpush/thrift/NotificationType;

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

    iput-object p3, p0, Lcom/xiaomi/xmpush/thrift/NotificationType;->value:Ljava/lang/String;

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/xiaomi/xmpush/thrift/NotificationType;
    .locals 1

    const-class v0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/xiaomi/xmpush/thrift/NotificationType;

    return-object p0
.end method

.method public static values()[Lcom/xiaomi/xmpush/thrift/NotificationType;
    .locals 1

    sget-object v0, Lcom/xiaomi/xmpush/thrift/NotificationType;->$VALUES:[Lcom/xiaomi/xmpush/thrift/NotificationType;

    invoke-virtual {v0}, [Lcom/xiaomi/xmpush/thrift/NotificationType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/xiaomi/xmpush/thrift/NotificationType;

    return-object v0
.end method


# virtual methods
.method public toString()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/xiaomi/xmpush/thrift/NotificationType;->value:Ljava/lang/String;

    return-object v0
.end method
