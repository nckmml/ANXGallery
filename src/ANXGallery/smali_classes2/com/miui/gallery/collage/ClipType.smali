.class public final enum Lcom/miui/gallery/collage/ClipType;
.super Ljava/lang/Enum;
.source "ClipType.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/collage/ClipType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/collage/ClipType;

.field public static final enum CIRCLE:Lcom/miui/gallery/collage/ClipType;

.field public static final enum PATH:Lcom/miui/gallery/collage/ClipType;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/miui/gallery/collage/ClipType;

    const/4 v1, 0x0

    const-string v2, "PATH"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/collage/ClipType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/collage/ClipType;->PATH:Lcom/miui/gallery/collage/ClipType;

    new-instance v0, Lcom/miui/gallery/collage/ClipType;

    const/4 v2, 0x1

    const-string v3, "CIRCLE"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/collage/ClipType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/collage/ClipType;->CIRCLE:Lcom/miui/gallery/collage/ClipType;

    const/4 v0, 0x2

    new-array v0, v0, [Lcom/miui/gallery/collage/ClipType;

    sget-object v3, Lcom/miui/gallery/collage/ClipType;->PATH:Lcom/miui/gallery/collage/ClipType;

    aput-object v3, v0, v1

    sget-object v1, Lcom/miui/gallery/collage/ClipType;->CIRCLE:Lcom/miui/gallery/collage/ClipType;

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/collage/ClipType;->$VALUES:[Lcom/miui/gallery/collage/ClipType;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/collage/ClipType;
    .locals 1

    const-class v0, Lcom/miui/gallery/collage/ClipType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/collage/ClipType;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/collage/ClipType;
    .locals 1

    sget-object v0, Lcom/miui/gallery/collage/ClipType;->$VALUES:[Lcom/miui/gallery/collage/ClipType;

    invoke-virtual {v0}, [Lcom/miui/gallery/collage/ClipType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/collage/ClipType;

    return-object v0
.end method
