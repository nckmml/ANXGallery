.class public final synthetic Lcom/miui/gallery/provider/-$$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/util/Comparator;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/provider/-$$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/provider/-$$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA;

    invoke-direct {v0}, Lcom/miui/gallery/provider/-$$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA;-><init>()V

    sput-object v0, Lcom/miui/gallery/provider/-$$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA;->INSTANCE:Lcom/miui/gallery/provider/-$$Lambda$AlbumSnapshotHelper$MMhziQ4qVtFhiDJkezusxNeottA;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 0

    check-cast p1, Lcom/miui/gallery/model/Album;

    check-cast p2, Lcom/miui/gallery/model/Album;

    invoke-static {p1, p2}, Lcom/miui/gallery/provider/AlbumSnapshotHelper;->lambda$persist$32(Lcom/miui/gallery/model/Album;Lcom/miui/gallery/model/Album;)I

    move-result p1

    return p1
.end method
