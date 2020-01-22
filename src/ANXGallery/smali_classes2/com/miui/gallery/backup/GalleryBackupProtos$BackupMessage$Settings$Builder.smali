.class public final Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
.super Lcom/google/protobuf/GeneratedMessageLite$Builder;
.source "GalleryBackupProtos.java"

# interfaces
.implements Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$SettingsOrBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/google/protobuf/GeneratedMessageLite$Builder<",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;",
        ">;",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$SettingsOrBuilder;"
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$000()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;-><init>(Lcom/google/protobuf/GeneratedMessageLite;)V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/backup/GalleryBackupProtos$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;-><init>()V

    return-void
.end method


# virtual methods
.method public clearOnlyShowLocalPhoto()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$200(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V

    return-object p0
.end method

.method public clearRemindConnectNetworkEveryTime()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$800(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V

    return-object p0
.end method

.method public clearShowHiddenAlbum()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$400(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V

    return-object p0
.end method

.method public clearSlideshowInterval()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$600(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V

    return-object p0
.end method

.method public getOnlyShowLocalPhoto()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->getOnlyShowLocalPhoto()Z

    move-result v0

    return v0
.end method

.method public getRemindConnectNetworkEveryTime()Z
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->getRemindConnectNetworkEveryTime()Z

    move-result v0

    return v0
.end method

.method public getShowHiddenAlbum()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->getShowHiddenAlbum()Z

    move-result v0

    return v0
.end method

.method public getSlideshowInterval()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->getSlideshowInterval()I

    move-result v0

    return v0
.end method

.method public setOnlyShowLocalPhoto(Z)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$100(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;Z)V

    return-object p0
.end method

.method public setRemindConnectNetworkEveryTime(Z)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$700(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;Z)V

    return-object p0
.end method

.method public setShowHiddenAlbum(Z)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$300(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;Z)V

    return-object p0
.end method

.method public setSlideshowInterval(I)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;->access$500(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;I)V

    return-object p0
.end method
