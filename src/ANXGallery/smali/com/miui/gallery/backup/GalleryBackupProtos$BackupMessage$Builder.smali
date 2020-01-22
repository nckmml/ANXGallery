.class public final Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
.super Lcom/google/protobuf/GeneratedMessageLite$Builder;
.source "GalleryBackupProtos.java"

# interfaces
.implements Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessageOrBuilder;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/google/protobuf/GeneratedMessageLite$Builder<",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;",
        ">;",
        "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessageOrBuilder;"
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2000()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/google/protobuf/GeneratedMessageLite$Builder;-><init>(Lcom/google/protobuf/GeneratedMessageLite;)V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/backup/GalleryBackupProtos$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;-><init>()V

    return-void
.end method


# virtual methods
.method public addAlbumProfiles(ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile$Builder;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1, p2}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$3000(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile$Builder;)V

    return-object p0
.end method

.method public addAlbumProfiles(ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1, p2}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2800(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;)V

    return-object p0
.end method

.method public addAlbumProfiles(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile$Builder;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2900(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile$Builder;)V

    return-object p0
.end method

.method public addAlbumProfiles(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2700(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;)V

    return-object p0
.end method

.method public addAllAlbumProfiles(Ljava/lang/Iterable;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Iterable<",
            "+",
            "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;",
            ">;)",
            "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;"
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$3100(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;Ljava/lang/Iterable;)V

    return-object p0
.end method

.method public clearAlbumProfiles()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$3200(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;)V

    return-object p0
.end method

.method public clearSettings()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2400(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;)V

    return-object p0
.end method

.method public getAlbumProfiles(I)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->getAlbumProfiles(I)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;

    move-result-object p1

    return-object p1
.end method

.method public getAlbumProfilesCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->getAlbumProfilesCount()I

    move-result v0

    return v0
.end method

.method public getAlbumProfilesList()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->getAlbumProfilesList()Ljava/util/List;

    move-result-object v0

    invoke-static {v0}, Ljava/util/Collections;->unmodifiableList(Ljava/util/List;)Ljava/util/List;

    move-result-object v0

    return-object v0
.end method

.method public getSettings()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->getSettings()Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;

    move-result-object v0

    return-object v0
.end method

.method public hasSettings()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-virtual {v0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->hasSettings()Z

    move-result v0

    return v0
.end method

.method public mergeSettings(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2300(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V

    return-object p0
.end method

.method public removeAlbumProfiles(I)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$3300(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;I)V

    return-object p0
.end method

.method public setAlbumProfiles(ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile$Builder;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1, p2}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2600(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile$Builder;)V

    return-object p0
.end method

.method public setAlbumProfiles(ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1, p2}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2500(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;ILcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$AlbumProfile;)V

    return-object p0
.end method

.method public setSettings(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2200(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings$Builder;)V

    return-object p0
.end method

.method public setSettings(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->copyOnWrite()V

    iget-object v0, p0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Builder;->instance:Lcom/google/protobuf/GeneratedMessageLite;

    check-cast v0, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;

    invoke-static {v0, p1}, Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;->access$2100(Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage;Lcom/miui/gallery/backup/GalleryBackupProtos$BackupMessage$Settings;)V

    return-object p0
.end method
