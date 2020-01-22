.class public final enum Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;
.super Ljava/lang/Enum;
.source "ItemType.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum audio:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum beat:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum collage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum dynamiccollage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum font:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum kedl:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum lut:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum media:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum overlay:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum renderitem:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum staticcollage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

.field public static final enum template:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;


# instance fields
.field private final typeId:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 15

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v1, 0x0

    const-string v2, "overlay"

    const-string v3, "nex.overlay"

    invoke-direct {v0, v2, v1, v3}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->overlay:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v2, 0x1

    const-string v3, "renderitem"

    const-string v4, "nex.renderitem"

    invoke-direct {v0, v3, v2, v4}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->renderitem:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v3, 0x2

    const-string v4, "kedl"

    const-string v5, "nex.kedl"

    invoke-direct {v0, v4, v3, v5}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->kedl:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v4, 0x3

    const-string v5, "audio"

    const-string v6, "nex.audio"

    invoke-direct {v0, v5, v4, v6}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->audio:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v5, 0x4

    const-string v6, "font"

    const-string v7, "nex.font"

    invoke-direct {v0, v6, v5, v7}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->font:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v6, 0x5

    const-string v7, "template"

    const-string v8, "nex.template"

    invoke-direct {v0, v7, v6, v8}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->template:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v7, 0x6

    const-string v8, "lut"

    const-string v9, "nex.lut"

    invoke-direct {v0, v8, v7, v9}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->lut:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/4 v8, 0x7

    const-string v9, "media"

    const-string v10, "nex.media"

    invoke-direct {v0, v9, v8, v10}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->media:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/16 v9, 0x8

    const-string v10, "collage"

    const-string v11, "nex.collage"

    invoke-direct {v0, v10, v9, v11}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->collage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/16 v10, 0x9

    const-string v11, "staticcollage"

    const-string v12, "nex.staticcollage"

    invoke-direct {v0, v11, v10, v12}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->staticcollage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/16 v11, 0xa

    const-string v12, "dynamiccollage"

    const-string v13, "nex.dynamiccollage"

    invoke-direct {v0, v12, v11, v13}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->dynamiccollage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/16 v12, 0xb

    const-string v13, "beat"

    const-string v14, "nex.beat"

    invoke-direct {v0, v13, v12, v14}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->beat:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    const/16 v0, 0xc

    new-array v0, v0, [Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    sget-object v13, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->overlay:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v13, v0, v1

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->renderitem:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->kedl:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->audio:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v4

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->font:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v5

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->template:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v6

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->lut:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v7

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->media:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v8

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->collage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v9

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->staticcollage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v10

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->dynamiccollage:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v11

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->beat:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    aput-object v1, v0, v12

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->$VALUES:[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;ILjava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput-object p3, p0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->typeId:Ljava/lang/String;

    return-void
.end method

.method public static fromId(Ljava/lang/String;)Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;
    .locals 5

    invoke-static {}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->values()[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_1

    aget-object v3, v0, v2

    iget-object v4, v3, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->typeId:Ljava/lang/String;

    invoke-virtual {v4, p0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    return-object v3

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    const/4 p0, 0x0

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;
    .locals 1

    const-class v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;
    .locals 1

    sget-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->$VALUES:[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    invoke-virtual {v0}, [Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;

    return-object v0
.end method


# virtual methods
.method public getTypeId()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemType;->typeId:Ljava/lang/String;

    return-object v0
.end method
