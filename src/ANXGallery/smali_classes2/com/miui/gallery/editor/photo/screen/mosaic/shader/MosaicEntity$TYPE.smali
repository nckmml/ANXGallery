.class public final enum Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;
.super Ljava/lang/Enum;
.source "MosaicEntity.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "TYPE"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

.field public static final enum BITMAP:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

.field public static final enum BLUR:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

.field public static final enum NORMAL:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

.field public static final enum ORIGIN:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

.field public static final enum TRIANGLE:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

.field public static final enum TRIANGLE_RECT:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;


# direct methods
.method static constructor <clinit>()V
    .locals 8

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    const/4 v1, 0x0

    const-string v2, "ORIGIN"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->ORIGIN:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    const/4 v2, 0x1

    const-string v3, "NORMAL"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->NORMAL:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    const/4 v3, 0x2

    const-string v4, "TRIANGLE"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->TRIANGLE:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    const/4 v4, 0x3

    const-string v5, "TRIANGLE_RECT"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->TRIANGLE_RECT:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    const/4 v5, 0x4

    const-string v6, "BLUR"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->BLUR:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    const/4 v6, 0x5

    const-string v7, "BITMAP"

    invoke-direct {v0, v7, v6}, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->BITMAP:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    const/4 v0, 0x6

    new-array v0, v0, [Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    sget-object v7, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->ORIGIN:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    aput-object v7, v0, v1

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->NORMAL:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->TRIANGLE:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->TRIANGLE_RECT:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->BLUR:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->BITMAP:Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    aput-object v1, v0, v6

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->$VALUES:[Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;
    .locals 1

    const-class v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;
    .locals 1

    sget-object v0, Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->$VALUES:[Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    invoke-virtual {v0}, [Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/editor/photo/screen/mosaic/shader/MosaicEntity$TYPE;

    return-object v0
.end method
