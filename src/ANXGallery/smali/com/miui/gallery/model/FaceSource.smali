.class public Lcom/miui/gallery/model/FaceSource;
.super Lcom/miui/gallery/model/PhotoLoaderSource;
.source "FaceSource.java"


# static fields
.field private static sUriMatcher:Landroid/content/UriMatcher;


# direct methods
.method public constructor <init>()V
    .locals 4

    invoke-direct {p0}, Lcom/miui/gallery/model/PhotoLoaderSource;-><init>()V

    sget-object v0, Lcom/miui/gallery/model/FaceSource;->sUriMatcher:Landroid/content/UriMatcher;

    if-nez v0, :cond_0

    new-instance v0, Landroid/content/UriMatcher;

    const/4 v1, -0x1

    invoke-direct {v0, v1}, Landroid/content/UriMatcher;-><init>(I)V

    sput-object v0, Lcom/miui/gallery/model/FaceSource;->sUriMatcher:Landroid/content/UriMatcher;

    sget-object v0, Lcom/miui/gallery/model/FaceSource;->sUriMatcher:Landroid/content/UriMatcher;

    const/4 v1, 0x0

    const-string v2, "com.miui.gallery.cloud.provider"

    const-string v3, "person"

    invoke-virtual {v0, v2, v3, v1}, Landroid/content/UriMatcher;->addURI(Ljava/lang/String;Ljava/lang/String;I)V

    sget-object v0, Lcom/miui/gallery/model/FaceSource;->sUriMatcher:Landroid/content/UriMatcher;

    const/4 v1, 0x1

    const-string v3, "person_recommend"

    invoke-virtual {v0, v2, v3, v1}, Landroid/content/UriMatcher;->addURI(Ljava/lang/String;Ljava/lang/String;I)V

    :cond_0
    return-void
.end method


# virtual methods
.method public createDataLoader(Landroid/content/Context;Landroid/net/Uri;Landroid/os/Bundle;)Lcom/miui/gallery/loader/BaseLoader;
    .locals 2

    sget-object v0, Lcom/miui/gallery/model/FaceSource;->sUriMatcher:Landroid/content/UriMatcher;

    invoke-virtual {v0, p2}, Landroid/content/UriMatcher;->match(Landroid/net/Uri;)I

    move-result v0

    if-eqz v0, :cond_1

    const/4 v1, 0x1

    if-eq v0, v1, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    new-instance v0, Lcom/miui/gallery/loader/FaceCloudSetLoader;

    invoke-direct {v0, p1, p2, p3}, Lcom/miui/gallery/loader/FaceCloudSetLoader;-><init>(Landroid/content/Context;Landroid/net/Uri;Landroid/os/Bundle;)V

    goto :goto_0

    :cond_1
    new-instance v0, Lcom/miui/gallery/loader/FaceCloudSetLoader;

    invoke-direct {v0, p1, p2, p3}, Lcom/miui/gallery/loader/FaceCloudSetLoader;-><init>(Landroid/content/Context;Landroid/net/Uri;Landroid/os/Bundle;)V

    :goto_0
    return-object v0
.end method

.method public match(Landroid/net/Uri;Landroid/os/Bundle;)Z
    .locals 1

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/net/Uri;->getScheme()Ljava/lang/String;

    move-result-object p2

    const-string v0, "content"

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_0

    invoke-virtual {p1}, Landroid/net/Uri;->getAuthority()Ljava/lang/String;

    move-result-object p2

    const-string v0, "com.miui.gallery.cloud.provider"

    invoke-virtual {v0, p2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p2

    if-eqz p2, :cond_0

    sget-object p2, Lcom/miui/gallery/model/FaceSource;->sUriMatcher:Landroid/content/UriMatcher;

    invoke-virtual {p2, p1}, Landroid/content/UriMatcher;->match(Landroid/net/Uri;)I

    move-result p1

    const/4 p2, -0x1

    if-eq p1, p2, :cond_0

    const/4 p1, 0x1

    return p1

    :cond_0
    const/4 p1, 0x0

    return p1
.end method
