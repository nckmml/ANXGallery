.class public final enum Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;
.super Ljava/lang/Enum;
.source "AlbumDetailSimpleAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "AlbumType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum BABY:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum FAVORITES:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum NORMAL:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum OTHER_SHARE:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum OTHER_SHARE_BABY:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum PANO:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum RECENT:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum SCREENSHOT:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

.field public static final enum SECRET:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;


# direct methods
.method static constructor <clinit>()V
    .locals 11

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v1, 0x0

    const-string v2, "NORMAL"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->NORMAL:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v2, 0x1

    const-string v3, "SECRET"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->SECRET:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v3, 0x2

    const-string v4, "BABY"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->BABY:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v4, 0x3

    const-string v5, "SCREENSHOT"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->SCREENSHOT:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v5, 0x4

    const-string v6, "OTHER_SHARE"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->OTHER_SHARE:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v6, 0x5

    const-string v7, "PANO"

    invoke-direct {v0, v7, v6}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->PANO:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v7, 0x6

    const-string v8, "RECENT"

    invoke-direct {v0, v8, v7}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->RECENT:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/4 v8, 0x7

    const-string v9, "FAVORITES"

    invoke-direct {v0, v9, v8}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->FAVORITES:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    new-instance v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/16 v9, 0x8

    const-string v10, "OTHER_SHARE_BABY"

    invoke-direct {v0, v10, v9}, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->OTHER_SHARE_BABY:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    const/16 v0, 0x9

    new-array v0, v0, [Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    sget-object v10, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->NORMAL:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v10, v0, v1

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->SECRET:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->BABY:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->SCREENSHOT:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->OTHER_SHARE:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->PANO:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v6

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->RECENT:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v7

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->FAVORITES:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v8

    sget-object v1, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->OTHER_SHARE_BABY:Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    aput-object v1, v0, v9

    sput-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->$VALUES:[Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;
    .locals 1

    const-class v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;
    .locals 1

    sget-object v0, Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->$VALUES:[Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    invoke-virtual {v0}, [Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/adapter/AlbumDetailSimpleAdapter$AlbumType;

    return-object v0
.end method
