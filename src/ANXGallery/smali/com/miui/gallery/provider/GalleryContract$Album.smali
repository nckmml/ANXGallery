.class public interface abstract Lcom/miui/gallery/provider/GalleryContract$Album;
.super Ljava/lang/Object;
.source "GalleryContract.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/GalleryContract;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "Album"
.end annotation


# static fields
.field public static final ALL_SYSTEM_ALBUM_SERVER_IDS:[Ljava/lang/Long;

.field public static final ALL_VIRTUAL_ALBUM_IDS:[I

.field public static final LOCAL_SYSTEM_ALBUM_SERVER_IDS:[Ljava/lang/Long;

.field public static final URI:Landroid/net/Uri;

.field public static final URI_ALL:Landroid/net/Uri;

.field public static final URI_ALL_EXCEPT_DELETED:Landroid/net/Uri;

.field public static final URI_NO_COVERS:Landroid/net/Uri;

.field public static final URI_SHARE_ALL:Landroid/net/Uri;


# direct methods
.method static constructor <clinit>()V
    .locals 10

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract;->AUTHORITY_URI:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v1, "album"

    invoke-virtual {v0, v1}, Landroid/net/Uri$Builder;->appendPath(Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI:Landroid/net/Uri;

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v1, "true"

    const-string v2, "join_face"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v2, "join_video"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v2, "join_share"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v2, "join_recent"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v2, "join_favorites"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI_ALL:Landroid/net/Uri;

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v2, "fill_covers"

    const-string v3, "false"

    invoke-virtual {v0, v2, v3}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI_NO_COVERS:Landroid/net/Uri;

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI_ALL:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v2, "all_except_deleted"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v2, "join_pano"

    invoke-virtual {v0, v2, v1}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI_ALL_EXCEPT_DELETED:Landroid/net/Uri;

    sget-object v0, Lcom/miui/gallery/provider/GalleryContract;->AUTHORITY_URI:Landroid/net/Uri;

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object v0

    const-string v1, "owner_and_other_album"

    invoke-virtual {v0, v1}, Landroid/net/Uri$Builder;->appendPath(Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->URI_SHARE_ALL:Landroid/net/Uri;

    const/16 v0, 0xa

    new-array v0, v0, [Ljava/lang/Long;

    const-wide/32 v1, -0x7fffffff

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v2, 0x0

    aput-object v1, v0, v2

    const-wide/32 v3, -0x7ffffffd

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v3, 0x1

    aput-object v1, v0, v3

    const-wide/32 v4, -0x7ffffffc

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v4, 0x2

    aput-object v1, v0, v4

    const-wide/32 v5, -0x7ffffffe

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v5, 0x3

    aput-object v1, v0, v5

    const-wide/16 v5, 0x1

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const/4 v5, 0x4

    aput-object v1, v0, v5

    const-wide/16 v5, 0x2

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    const/4 v6, 0x5

    aput-object v5, v0, v6

    const-wide/32 v6, -0x7ffffffa

    invoke-static {v6, v7}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    const/4 v7, 0x6

    aput-object v6, v0, v7

    const-wide/32 v8, -0x7ffffffb

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    const/4 v8, 0x7

    aput-object v6, v0, v8

    const-wide/16 v8, 0x3e8

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    const/16 v8, 0x8

    aput-object v6, v0, v8

    const-wide/16 v8, 0x4

    invoke-static {v8, v9}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v6

    const/16 v8, 0x9

    aput-object v6, v0, v8

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->ALL_SYSTEM_ALBUM_SERVER_IDS:[Ljava/lang/Long;

    new-array v0, v4, [Ljava/lang/Long;

    aput-object v1, v0, v2

    aput-object v5, v0, v3

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->LOCAL_SYSTEM_ALBUM_SERVER_IDS:[Ljava/lang/Long;

    new-array v0, v7, [I

    fill-array-data v0, :array_0

    sput-object v0, Lcom/miui/gallery/provider/GalleryContract$Album;->ALL_VIRTUAL_ALBUM_IDS:[I

    return-void

    :array_0
    .array-data 4
        0x7fffffff
        0x7ffffffe
        0x7ffffffd
        0x7ffffffc
        0x7ffffffb
        0x7ffffffa
    .end array-data
.end method
