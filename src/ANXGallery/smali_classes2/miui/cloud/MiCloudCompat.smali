.class public Lmiui/cloud/MiCloudCompat;
.super Ljava/lang/Object;
.source "MiCloudCompat.java"


# static fields
.field public static final FACE_HOST:Ljava/lang/String;

.field public static final GALLERY_ANONYMOUS_HOST:Ljava/lang/String;

.field public static final GALLERY_HOST:Ljava/lang/String;

.field public static final SEARCH_ANONYMOUS_HOST:Ljava/lang/String;

.field public static final SEARCH_HOST:Ljava/lang/String;

.field public static final URL_ACCOUNT_API_BASE_SECURE:Ljava/lang/String;

.field public static final USE_PREVIEW:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Ljava/io/File;

    const-string v1, "/data/system/xiaomi_account_preview"

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    sput-boolean v0, Lmiui/cloud/MiCloudCompat;->USE_PREVIEW:Z

    sget-boolean v0, Lmiui/cloud/MiCloudCompat;->USE_PREVIEW:Z

    if-eqz v0, :cond_0

    const-string v0, "http://galleryapi.micloud.preview.n.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->GALLERY_HOST:Ljava/lang/String;

    sput-object v0, Lmiui/cloud/MiCloudCompat;->GALLERY_ANONYMOUS_HOST:Ljava/lang/String;

    const-string v0, "http://api.account.preview.n.xiaomi.net/pass"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->URL_ACCOUNT_API_BASE_SECURE:Ljava/lang/String;

    const-string v0, "http://galleryfaceapi.micloud.preview.n.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->FACE_HOST:Ljava/lang/String;

    const-string v0, "http://gallerysearchapi.micloud.preview.n.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->SEARCH_HOST:Ljava/lang/String;

    const-string v0, "http://gallerysearchapi.micloud.preview.n.xiaomi.nett"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->SEARCH_ANONYMOUS_HOST:Ljava/lang/String;

    goto :goto_0

    :cond_0
    const-string v0, "http://galleryapi.micloud.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->GALLERY_HOST:Ljava/lang/String;

    const-string v0, "http://g.galleryapi.micloud.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->GALLERY_ANONYMOUS_HOST:Ljava/lang/String;

    const-string v0, "https://api.account.xiaomi.com/pass"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->URL_ACCOUNT_API_BASE_SECURE:Ljava/lang/String;

    const-string v0, "http://galleryfaceapi.micloud.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->FACE_HOST:Ljava/lang/String;

    const-string v0, "http://gallerysearchapi.micloud.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->SEARCH_HOST:Ljava/lang/String;

    const-string v0, "http://g.gallerysearchapi.micloud.xiaomi.net"

    sput-object v0, Lmiui/cloud/MiCloudCompat;->SEARCH_ANONYMOUS_HOST:Ljava/lang/String;

    :goto_0
    return-void
.end method

.method public static getQuantityStringWithUnit(J)Ljava/lang/String;
    .locals 3

    long-to-float p0, p0

    const p1, 0x4ccccccd    # 1.07374184E8f

    cmpl-float p1, p0, p1

    const/4 v0, 0x0

    const/4 v1, 0x1

    const/high16 v2, 0x44800000    # 1024.0f

    if-lez p1, :cond_0

    div-float/2addr p0, v2

    div-float/2addr p0, v2

    div-float/2addr p0, v2

    new-array p1, v1, [Ljava/lang/Object;

    invoke-static {p0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    aput-object p0, p1, v0

    const-string p0, "%1$.2fGB"

    invoke-static {p0, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const p1, 0x47cccccd

    cmpl-float p1, p0, p1

    if-lez p1, :cond_1

    div-float/2addr p0, v2

    div-float/2addr p0, v2

    new-array p1, v1, [Ljava/lang/Object;

    invoke-static {p0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    aput-object p0, p1, v0

    const-string p0, "%1$.2fMB"

    invoke-static {p0, p1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    cmpl-float p0, p0, p1

    if-lez p0, :cond_2

    const-string p0, "0.1MB"

    goto :goto_0

    :cond_2
    const-string p0, "0MB"

    :goto_0
    return-object p0
.end method
