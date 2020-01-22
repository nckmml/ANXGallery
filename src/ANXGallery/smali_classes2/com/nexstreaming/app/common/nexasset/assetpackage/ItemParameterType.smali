.class public final enum Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;
.super Ljava/lang/Enum;
.source "ItemParameterType.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum CHOICE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum IMAGE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum RANGE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum RECT:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum RGB:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum RGBA:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum SWITCH:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum TEXT:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum XY:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

.field public static final enum XYZ:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;


# direct methods
.method static constructor <clinit>()V
    .locals 12

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v1, 0x0

    const-string v2, "TEXT"

    invoke-direct {v0, v2, v1}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->TEXT:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v2, 0x1

    const-string v3, "RGB"

    invoke-direct {v0, v3, v2}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RGB:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v3, 0x2

    const-string v4, "RGBA"

    invoke-direct {v0, v4, v3}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RGBA:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v4, 0x3

    const-string v5, "XY"

    invoke-direct {v0, v5, v4}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->XY:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v5, 0x4

    const-string v6, "XYZ"

    invoke-direct {v0, v6, v5}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->XYZ:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v6, 0x5

    const-string v7, "RECT"

    invoke-direct {v0, v7, v6}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RECT:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v7, 0x6

    const-string v8, "RANGE"

    invoke-direct {v0, v8, v7}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RANGE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/4 v8, 0x7

    const-string v9, "CHOICE"

    invoke-direct {v0, v9, v8}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->CHOICE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/16 v9, 0x8

    const-string v10, "IMAGE"

    invoke-direct {v0, v10, v9}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->IMAGE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    new-instance v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/16 v10, 0x9

    const-string v11, "SWITCH"

    invoke-direct {v0, v11, v10}, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->SWITCH:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    const/16 v0, 0xa

    new-array v0, v0, [Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    sget-object v11, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->TEXT:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v11, v0, v1

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RGB:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RGBA:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->XY:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v4

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->XYZ:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v5

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RECT:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v6

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->RANGE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v7

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->CHOICE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v8

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->IMAGE:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v9

    sget-object v1, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->SWITCH:Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    aput-object v1, v0, v10

    sput-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->$VALUES:[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

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

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;
    .locals 1

    const-class v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;
    .locals 1

    sget-object v0, Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->$VALUES:[Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    invoke-virtual {v0}, [Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/app/common/nexasset/assetpackage/ItemParameterType;

    return-object v0
.end method
