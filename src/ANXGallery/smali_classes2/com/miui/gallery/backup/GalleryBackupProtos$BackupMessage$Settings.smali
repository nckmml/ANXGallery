.class public final Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
.super Lcom/google/protobuf/GeneratedMessageLite;
.source "GalleryBackupProtos.java"

# interfaces
.implements Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$SettingsOrBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Settings"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/google/protobuf/GeneratedMessageLite<",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;",
        ">;",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$SettingsOrBuilder;"
    }
.end annotation


# static fields
.field private static final DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

.field public static final ONLYSHOWLOCALPHOTO_FIELD_NUMBER:I = 0x1

.field private static volatile PARSER:Lcom/google/protobuf/Parser; = null
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/google/protobuf/Parser<",
            "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;",
            ">;"
        }
    .end annotation
.end field

.field public static final REMINDCONNECTNETWORKEVERYTIME_FIELD_NUMBER:I = 0x4

.field public static final SHOWHIDDENALBUM_FIELD_NUMBER:I = 0x2

.field public static final SLIDESHOWINTERVAL_FIELD_NUMBER:I = 0x3


# instance fields
.field private onlyShowLocalPhoto_:Z

.field private remindConnectNetworkEveryTime_:Z

.field private showHiddenAlbum_:Z

.field private slideshowInterval_:I


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-direct {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;-><init>()V

    sput-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    const-class v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    sget-object v1, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, v1}, Lcom/google/protobuf/GeneratedMessageLite;->registerDefaultInstance(Ljava/lang/Class;Lcom/google/protobuf/GeneratedMessageLite;)V

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/google/protobuf/GeneratedMessageLite;-><init>()V

    return-void
.end method

.method static synthetic access$000()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object v0
.end method

.method static synthetic access$100(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->setOnlyShowLocalPhoto(Z)V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->clearOnlyShowLocalPhoto()V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->setShowHiddenAlbum(Z)V

    return-void
.end method

.method static synthetic access$400(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->clearShowHiddenAlbum()V

    return-void
.end method

.method static synthetic access$500(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;I)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->setSlideshowInterval(I)V

    return-void
.end method

.method static synthetic access$600(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->clearSlideshowInterval()V

    return-void
.end method

.method static synthetic access$700(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;Z)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->setRemindConnectNetworkEveryTime(Z)V

    return-void
.end method

.method static synthetic access$800(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->clearRemindConnectNetworkEveryTime()V

    return-void
.end method

.method private clearOnlyShowLocalPhoto()V
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->onlyShowLocalPhoto_:Z

    return-void
.end method

.method private clearRemindConnectNetworkEveryTime()V
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->remindConnectNetworkEveryTime_:Z

    return-void
.end method

.method private clearShowHiddenAlbum()V
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->showHiddenAlbum_:Z

    return-void
.end method

.method private clearSlideshowInterval()V
    .locals 1

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->slideshowInterval_:I

    return-void
.end method

.method public static getDefaultInstance()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object v0
.end method

.method public static newBuilder()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->createBuilder()Lcom/google/protobuf/GeneratedMessageLite$Builder;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;

    return-object v0
.end method

.method public static newBuilder(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-virtual {v0, p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->createBuilder(Lcom/google/protobuf/GeneratedMessageLite;)Lcom/google/protobuf/GeneratedMessageLite$Builder;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;

    return-object p0
.end method

.method public static parseDelimitedFrom(Ljava/io/InputStream;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->parseDelimitedFrom(Lcom/google/protobuf/GeneratedMessageLite;Ljava/io/InputStream;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseDelimitedFrom(Ljava/io/InputStream;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->parseDelimitedFrom(Lcom/google/protobuf/GeneratedMessageLite;Ljava/io/InputStream;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Lcom/google/protobuf/ByteString;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/google/protobuf/InvalidProtocolBufferException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Lcom/google/protobuf/ByteString;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Lcom/google/protobuf/ByteString;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/google/protobuf/InvalidProtocolBufferException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0, p1}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Lcom/google/protobuf/ByteString;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Lcom/google/protobuf/CodedInputStream;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Lcom/google/protobuf/CodedInputStream;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0, p1}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Lcom/google/protobuf/CodedInputStream;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Ljava/io/InputStream;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Ljava/io/InputStream;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Ljava/io/InputStream;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0, p1}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Ljava/io/InputStream;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Ljava/nio/ByteBuffer;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/google/protobuf/InvalidProtocolBufferException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Ljava/nio/ByteBuffer;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom(Ljava/nio/ByteBuffer;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/google/protobuf/InvalidProtocolBufferException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0, p1}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;Ljava/nio/ByteBuffer;Lcom/google/protobuf/ExtensionRegistryLite;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom([B)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/google/protobuf/InvalidProtocolBufferException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;[B)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parseFrom([BLcom/google/protobuf/ExtensionRegistryLite;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Lcom/google/protobuf/InvalidProtocolBufferException;
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p0, p1}, Lcom/google/protobuf/GeneratedMessageLite;->parseFrom(Lcom/google/protobuf/GeneratedMessageLite;[BLcom/google/protobuf/ExtensionRegistryLite;)Lcom/google/protobuf/GeneratedMessageLite;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p0
.end method

.method public static parser()Lcom/google/protobuf/Parser;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/google/protobuf/Parser<",
            "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->getParserForType()Lcom/google/protobuf/Parser;

    move-result-object v0

    return-object v0
.end method

.method private setOnlyShowLocalPhoto(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->onlyShowLocalPhoto_:Z

    return-void
.end method

.method private setRemindConnectNetworkEveryTime(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->remindConnectNetworkEveryTime_:Z

    return-void
.end method

.method private setShowHiddenAlbum(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->showHiddenAlbum_:Z

    return-void
.end method

.method private setSlideshowInterval(I)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->slideshowInterval_:I

    return-void
.end method


# virtual methods
.method protected final dynamicMethod(Lcom/google/protobuf/GeneratedMessageLite$MethodToInvoke;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    sget-object p2, Lcom/miui/gallery/backup/GalleryBackupProtos$1;->$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke:[I

    invoke-virtual {p1}, Lcom/google/protobuf/GeneratedMessageLite$MethodToInvoke;->ordinal()I

    move-result p1

    aget p1, p2, p1

    const/4 p2, 0x1

    const/4 p3, 0x0

    packed-switch p1, :pswitch_data_0

    new-instance p1, Ljava/lang/UnsupportedOperationException;

    invoke-direct {p1}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw p1

    :pswitch_0
    return-object p3

    :pswitch_1
    invoke-static {p2}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p1

    return-object p1

    :pswitch_2
    sget-object p1, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->PARSER:Lcom/google/protobuf/Parser;

    if-nez p1, :cond_1

    const-class p2, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    monitor-enter p2

    :try_start_0
    sget-object p1, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->PARSER:Lcom/google/protobuf/Parser;

    if-nez p1, :cond_0

    new-instance p1, Lcom/google/protobuf/GeneratedMessageLite$DefaultInstanceBasedParser;

    sget-object p3, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-direct {p1, p3}, Lcom/google/protobuf/GeneratedMessageLite$DefaultInstanceBasedParser;-><init>(Lcom/google/protobuf/GeneratedMessageLite;)V

    sput-object p1, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->PARSER:Lcom/google/protobuf/Parser;

    :cond_0
    monitor-exit p2

    goto :goto_0

    :catchall_0
    move-exception p1

    monitor-exit p2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p1

    :cond_1
    :goto_0
    return-object p1

    :pswitch_3
    sget-object p1, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    return-object p1

    :pswitch_4
    const/4 p1, 0x4

    new-array p1, p1, [Ljava/lang/Object;

    const/4 p3, 0x0

    const-string v0, "onlyShowLocalPhoto_"

    aput-object v0, p1, p3

    const-string p3, "showHiddenAlbum_"

    aput-object p3, p1, p2

    const/4 p2, 0x2

    const-string p3, "slideshowInterval_"

    aput-object p3, p1, p2

    const/4 p2, 0x3

    const-string p3, "remindConnectNetworkEveryTime_"

    aput-object p3, p1, p2

    sget-object p2, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->DEFAULT_INSTANCE:Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    const-string p3, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0007\u0002\u0007\u0003\u0004\u0004\u0007"

    invoke-static {p2, p3, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->newMessageInfo(Lcom/google/protobuf/MessageLite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    return-object p1

    :pswitch_5
    new-instance p1, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;

    invoke-direct {p1, p3}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;-><init>(Lcom/miui/gallery/backup/GalleryBackupProtos$1;)V

    return-object p1

    :pswitch_6
    new-instance p1, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-direct {p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;-><init>()V

    return-object p1

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public getOnlyShowLocalPhoto()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->onlyShowLocalPhoto_:Z

    return v0
.end method

.method public getRemindConnectNetworkEveryTime()Z
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    iget-boolean v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->remindConnectNetworkEveryTime_:Z

    return v0
.end method

.method public getShowHiddenAlbum()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->showHiddenAlbum_:Z

    return v0
.end method

.method public getSlideshowInterval()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->slideshowInterval_:I

    return v0
.end method
