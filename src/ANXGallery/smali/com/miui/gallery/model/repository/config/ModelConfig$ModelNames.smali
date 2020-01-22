.class public interface abstract Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;
.super Ljava/lang/Object;
.source "ModelConfig.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/model/repository/config/ModelConfig;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "ModelNames"
.end annotation


# static fields
.field public static final ALBUM_REPOSITORY:Ljava/lang/String;

.field public static final LOCATION_REPOSITORY:Ljava/lang/String;

.field public static final PHOTO_REPOSITORY:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const-class v0, Lcom/miui/gallery/model/repository/datesource/photo/IPhotoRepository;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;->PHOTO_REPOSITORY:Ljava/lang/String;

    const-class v0, Lcom/miui/gallery/model/repository/datesource/location/ILocationRepository;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;->LOCATION_REPOSITORY:Ljava/lang/String;

    const-class v0, Lcom/miui/gallery/model/repository/datesource/album/IAlbumRepository;

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;->ALBUM_REPOSITORY:Ljava/lang/String;

    return-void
.end method
