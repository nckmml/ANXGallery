.class public Lcom/miui/gallery/provider/GalleryOpenApiProvider;
.super Landroid/content/ContentProvider;
.source "GalleryOpenApiProvider.java"


# static fields
.field protected static final sUriMatcher:Landroid/content/UriMatcher;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Landroid/content/UriMatcher;

    const/4 v1, -0x1

    invoke-direct {v0, v1}, Landroid/content/UriMatcher;-><init>(I)V

    sput-object v0, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->sUriMatcher:Landroid/content/UriMatcher;

    sget-object v0, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->sUriMatcher:Landroid/content/UriMatcher;

    const-string v1, "com.miui.gallery.open_api"

    const-string v2, "check_thumbnail"

    const/4 v3, 0x1

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/UriMatcher;->addURI(Ljava/lang/String;Ljava/lang/String;I)V

    sget-object v0, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->sUriMatcher:Landroid/content/UriMatcher;

    const-string v2, "search_status"

    const/4 v3, 0x2

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/UriMatcher;->addURI(Ljava/lang/String;Ljava/lang/String;I)V

    sget-object v0, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->sUriMatcher:Landroid/content/UriMatcher;

    const-string v2, "secret_album"

    const/4 v3, 0x3

    invoke-virtual {v0, v1, v2, v3}, Landroid/content/UriMatcher;->addURI(Ljava/lang/String;Ljava/lang/String;I)V

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/content/ContentProvider;-><init>()V

    return-void
.end method

.method private getSecretAlbumUnsyncedCount()I
    .locals 11

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x6

    new-array v1, v1, [Ljava/lang/Object;

    sget-object v2, Lcom/miui/gallery/cloud/CloudUtils;->SELECTION_OWNER_NEED_SYNC:Ljava/lang/String;

    const/4 v3, 0x0

    aput-object v2, v1, v3

    const/4 v2, 0x1

    const-string v4, "(localGroupId=-1000)"

    aput-object v4, v1, v2

    const-string v2, "localFlag"

    const/4 v4, 0x2

    aput-object v2, v1, v4

    const/4 v4, 0x7

    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    const/4 v5, 0x3

    aput-object v4, v1, v5

    const/4 v4, 0x4

    aput-object v2, v1, v4

    const/16 v2, 0x8

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const/4 v4, 0x5

    aput-object v2, v1, v4

    const-string v2, "(%s) AND (%s) AND (%s = %d OR %s = %d)"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->getContext()Landroid/content/Context;

    move-result-object v4

    sget-object v5, Lcom/miui/gallery/provider/GalleryContract$Cloud;->CLOUD_URI:Landroid/net/Uri;

    const-string v0, "count(_id)"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v6

    new-instance v10, Lcom/miui/gallery/provider/GalleryOpenApiProvider$1;

    invoke-direct {v10, p0}, Lcom/miui/gallery/provider/GalleryOpenApiProvider$1;-><init>(Lcom/miui/gallery/provider/GalleryOpenApiProvider;)V

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-static/range {v4 .. v10}, Lcom/miui/gallery/util/SafeDBUtil;->safeQuery(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v3

    :cond_0
    return v3
.end method


# virtual methods
.method public delete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    .locals 0

    const/4 p1, 0x0

    return p1
.end method

.method public getType(Landroid/net/Uri;)Ljava/lang/String;
    .locals 0

    const/4 p1, 0x0

    return-object p1
.end method

.method public insert(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    .locals 0

    const/4 p1, 0x0

    return-object p1
.end method

.method public onCreate()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    .locals 0

    sget-object p2, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->sUriMatcher:Landroid/content/UriMatcher;

    invoke-virtual {p2, p1}, Landroid/content/UriMatcher;->match(Landroid/net/Uri;)I

    move-result p2

    const/4 p3, 0x0

    const/4 p5, 0x1

    if-eq p2, p5, :cond_2

    const/4 p1, 0x2

    if-eq p2, p1, :cond_1

    const/4 p1, 0x3

    if-eq p2, p1, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    invoke-direct {p0}, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->getSecretAlbumUnsyncedCount()I

    move-result p1

    new-instance p2, Landroid/database/MatrixCursor;

    const-string p4, "unsynced_count"

    filled-new-array {p4}, [Ljava/lang/String;

    move-result-object p4

    invoke-direct {p2, p4, p5}, Landroid/database/MatrixCursor;-><init>([Ljava/lang/String;I)V

    new-array p4, p5, [Ljava/lang/Object;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    aput-object p1, p4, p3

    invoke-virtual {p2, p4}, Landroid/database/MatrixCursor;->addRow([Ljava/lang/Object;)V

    return-object p2

    :cond_1
    invoke-static {}, Lcom/miui/gallery/ui/AIAlbumStatusHelper;->getOpenApiSearchStatus()I

    move-result p1

    new-instance p2, Landroid/database/MatrixCursor;

    const-string p4, "status"

    filled-new-array {p4}, [Ljava/lang/String;

    move-result-object p4

    invoke-direct {p2, p4, p5}, Landroid/database/MatrixCursor;-><init>([Ljava/lang/String;I)V

    new-array p4, p5, [Ljava/lang/Object;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    aput-object p1, p4, p3

    invoke-virtual {p2, p4}, Landroid/database/MatrixCursor;->addRow([Ljava/lang/Object;)V

    return-object p2

    :cond_2
    const-string p2, "strict_mode"

    invoke-virtual {p1, p2, p3}, Landroid/net/Uri;->getBooleanQueryParameter(Ljava/lang/String;Z)Z

    move-result p1

    invoke-virtual {p0}, Lcom/miui/gallery/provider/GalleryOpenApiProvider;->getContext()Landroid/content/Context;

    move-result-object p2

    invoke-static {p2, p4, p1}, Lcom/miui/gallery/provider/CheckThumbnailHelper;->checkThumbnail(Landroid/content/Context;[Ljava/lang/String;Z)Landroid/database/Cursor;

    move-result-object p1

    return-object p1
.end method

.method public update(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    .locals 0

    const/4 p1, 0x0

    return p1
.end method
