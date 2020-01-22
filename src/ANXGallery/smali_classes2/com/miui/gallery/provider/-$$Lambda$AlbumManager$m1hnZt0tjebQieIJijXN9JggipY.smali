.class public final synthetic Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;

    invoke-direct {v0}, Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;-><init>()V

    sput-object v0, Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;->INSTANCE:Lcom/miui/gallery/provider/-$$Lambda$AlbumManager$m1hnZt0tjebQieIJijXN9JggipY;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final handle(Landroid/database/Cursor;)Ljava/lang/Object;
    .locals 0

    invoke-static {p1}, Lcom/miui/gallery/provider/AlbumManager;->lambda$doQueryScreenshotsAlbumId$33(Landroid/database/Cursor;)Ljava/lang/Long;

    move-result-object p1

    return-object p1
.end method
