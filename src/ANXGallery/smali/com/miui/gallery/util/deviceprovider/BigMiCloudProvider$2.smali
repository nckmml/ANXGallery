.class final Lcom/miui/gallery/util/deviceprovider/BigMiCloudProvider$2;
.super Ljava/lang/Object;
.source "BigMiCloudProvider.java"

# interfaces
.implements Lcom/miui/gallery/util/deviceprovider/MiCloudProviderInterface$GalleryCloudManager;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/util/deviceprovider/BigMiCloudProvider;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getFaceHost()Ljava/lang/String;
    .locals 1

    sget-object v0, Lmiui/cloud/MiCloudCompat;->FACE_HOST:Ljava/lang/String;

    return-object v0
.end method

.method public getGalleryAnonymousHost()Ljava/lang/String;
    .locals 1

    sget-object v0, Lmiui/cloud/MiCloudCompat;->GALLERY_ANONYMOUS_HOST:Ljava/lang/String;

    return-object v0
.end method

.method public getGalleryHost()Ljava/lang/String;
    .locals 1

    sget-object v0, Lmiui/cloud/MiCloudCompat;->GALLERY_HOST:Ljava/lang/String;

    return-object v0
.end method

.method public getSearchAnonymousHost()Ljava/lang/String;
    .locals 1

    sget-object v0, Lmiui/cloud/MiCloudCompat;->SEARCH_ANONYMOUS_HOST:Ljava/lang/String;

    return-object v0
.end method

.method public getSearchHost()Ljava/lang/String;
    .locals 1

    sget-object v0, Lmiui/cloud/MiCloudCompat;->SEARCH_HOST:Ljava/lang/String;

    return-object v0
.end method

.method public getUserAgent()Ljava/lang/String;
    .locals 1

    invoke-static {}, Lcom/xiaomi/micloudsdk/cloudinfo/utils/CloudInfoUtils;->getUserAgent()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
