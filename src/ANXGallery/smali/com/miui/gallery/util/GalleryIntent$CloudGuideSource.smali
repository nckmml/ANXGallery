.class public final enum Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;
.super Ljava/lang/Enum;
.source "GalleryIntent.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/util/GalleryIntent;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "CloudGuideSource"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

.field public static final enum AUTOBACKUP:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

.field public static final enum NOTIFICATION:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

.field public static final enum SEARCH:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

.field public static final enum SECRET:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

.field public static final enum SHARE_INVITATION:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

.field public static final enum TOPBAR:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

.field public static final enum URL:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;


# direct methods
.method static constructor <clinit>()V
    .locals 9

    new-instance v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v1, 0x0

    const-string v2, "NOTIFICATION"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->NOTIFICATION:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    new-instance v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v2, 0x1

    const-string v3, "TOPBAR"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->TOPBAR:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    new-instance v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v3, 0x2

    const-string v4, "SECRET"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->SECRET:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    new-instance v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v4, 0x3

    const-string v5, "AUTOBACKUP"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->AUTOBACKUP:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    new-instance v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v5, 0x4

    const-string v6, "SHARE_INVITATION"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->SHARE_INVITATION:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    new-instance v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v6, 0x5

    const-string v7, "URL"

    invoke-direct {v0, v7, v6}, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->URL:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    new-instance v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v7, 0x6

    const-string v8, "SEARCH"

    invoke-direct {v0, v8, v7}, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->SEARCH:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    const/4 v0, 0x7

    new-array v0, v0, [Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    sget-object v8, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->NOTIFICATION:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    aput-object v8, v0, v1

    sget-object v1, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->TOPBAR:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->SECRET:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->AUTOBACKUP:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->SHARE_INVITATION:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->URL:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    aput-object v1, v0, v6

    sget-object v1, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->SEARCH:Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    aput-object v1, v0, v7

    sput-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->$VALUES:[Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;
    .locals 1

    const-class v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;
    .locals 1

    sget-object v0, Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->$VALUES:[Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    invoke-virtual {v0}, [Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/util/GalleryIntent$CloudGuideSource;

    return-object v0
.end method
