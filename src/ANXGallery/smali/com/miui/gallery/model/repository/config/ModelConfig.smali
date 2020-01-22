.class public Lcom/miui/gallery/model/repository/config/ModelConfig;
.super Ljava/lang/Object;
.source "ModelConfig.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;
    }
.end annotation


# static fields
.field private static final mConfigs:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;>;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Ljava/util/HashMap;

    const/4 v1, 0x2

    invoke-direct {v0, v1}, Ljava/util/HashMap;-><init>(I)V

    sput-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig;->mConfigs:Ljava/util/HashMap;

    return-void
.end method

.method public static getModelConfigs()Ljava/util/HashMap;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;>;"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig;->mConfigs:Ljava/util/HashMap;

    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    sget-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig;->mConfigs:Ljava/util/HashMap;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/miui/gallery/model/repository/config/ModelConfig;->mConfigs:Ljava/util/HashMap;

    invoke-virtual {v1}, Ljava/util/HashMap;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    sget-object v1, Lcom/miui/gallery/model/repository/config/ModelConfig;->mConfigs:Ljava/util/HashMap;

    invoke-static {v1}, Lcom/miui/gallery/model/repository/config/ModelConfig;->initModels(Ljava/util/HashMap;)V

    :cond_0
    sget-object v1, Lcom/miui/gallery/model/repository/config/ModelConfig;->mConfigs:Ljava/util/HashMap;

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1

    :cond_1
    sget-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig;->mConfigs:Ljava/util/HashMap;

    return-object v0
.end method

.method private static initModels(Ljava/util/HashMap;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;>;)V"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;->PHOTO_REPOSITORY:Ljava/lang/String;

    const-class v1, Lcom/miui/gallery/model/repository/PhotoRepository;

    invoke-virtual {p0, v0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;->LOCATION_REPOSITORY:Ljava/lang/String;

    const-class v1, Lcom/miui/gallery/model/repository/LocationRepository;

    invoke-virtual {p0, v0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v0, Lcom/miui/gallery/model/repository/config/ModelConfig$ModelNames;->ALBUM_REPOSITORY:Ljava/lang/String;

    const-class v1, Lcom/miui/gallery/model/repository/AlbumRepository;

    invoke-virtual {p0, v0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method
