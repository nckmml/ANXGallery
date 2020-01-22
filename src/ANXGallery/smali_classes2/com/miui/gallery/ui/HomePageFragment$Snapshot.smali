.class Lcom/miui/gallery/ui/HomePageFragment$Snapshot;
.super Ljava/lang/Object;
.source "HomePageFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/HomePageFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "Snapshot"
.end annotation


# instance fields
.field private header:Lcom/miui/gallery/model/HomeMediaHeader;

.field private medias:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/model/HomeMedia;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/HomePageFragment$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;-><init>()V

    return-void
.end method

.method static synthetic access$1300(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;->medias:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$1302(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;Ljava/util/List;)Ljava/util/List;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;->medias:Ljava/util/List;

    return-object p1
.end method

.method static synthetic access$1400(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;)Lcom/miui/gallery/model/HomeMediaHeader;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;->header:Lcom/miui/gallery/model/HomeMediaHeader;

    return-object p0
.end method

.method static synthetic access$1402(Lcom/miui/gallery/ui/HomePageFragment$Snapshot;Lcom/miui/gallery/model/HomeMediaHeader;)Lcom/miui/gallery/model/HomeMediaHeader;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/HomePageFragment$Snapshot;->header:Lcom/miui/gallery/model/HomeMediaHeader;

    return-object p1
.end method
