.class public final enum Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;
.super Ljava/lang/Enum;
.source "nexEngine.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/nexstreaming/nexeditorsdk/nexEngine;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "FastPreviewOption"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum adj_brightness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum adj_contrast:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum adj_saturation:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum adj_sharpness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum adj_vignette:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum adj_vignetteRange:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum brightness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum contrast:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum cts:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum customlut_clip:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum customlut_power:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum normal:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum saturation:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

.field public static final enum tintColor:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;


# direct methods
.method static constructor <clinit>()V
    .locals 16

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v1, 0x0

    const-string v2, "normal"

    invoke-direct {v0, v2, v1}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->normal:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v2, 0x1

    const-string v3, "brightness"

    invoke-direct {v0, v3, v2}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->brightness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v3, 0x2

    const-string v4, "contrast"

    invoke-direct {v0, v4, v3}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->contrast:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v4, 0x3

    const-string v5, "saturation"

    invoke-direct {v0, v5, v4}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->saturation:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v5, 0x4

    const-string v6, "adj_brightness"

    invoke-direct {v0, v6, v5}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_brightness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v6, 0x5

    const-string v7, "adj_contrast"

    invoke-direct {v0, v7, v6}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_contrast:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v7, 0x6

    const-string v8, "adj_saturation"

    invoke-direct {v0, v8, v7}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_saturation:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/4 v8, 0x7

    const-string v9, "tintColor"

    invoke-direct {v0, v9, v8}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->tintColor:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/16 v9, 0x8

    const-string v10, "cts"

    invoke-direct {v0, v10, v9}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->cts:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/16 v10, 0x9

    const-string v11, "adj_vignette"

    invoke-direct {v0, v11, v10}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_vignette:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/16 v11, 0xa

    const-string v12, "adj_vignetteRange"

    invoke-direct {v0, v12, v11}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_vignetteRange:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/16 v12, 0xb

    const-string v13, "adj_sharpness"

    invoke-direct {v0, v13, v12}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_sharpness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/16 v13, 0xc

    const-string v14, "customlut_clip"

    invoke-direct {v0, v14, v13}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->customlut_clip:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/16 v14, 0xd

    const-string v15, "customlut_power"

    invoke-direct {v0, v15, v14}, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->customlut_power:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    const/16 v0, 0xe

    new-array v0, v0, [Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    sget-object v15, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->normal:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v15, v0, v1

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->brightness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v2

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->contrast:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v3

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->saturation:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v4

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_brightness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v5

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_contrast:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v6

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_saturation:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v7

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->tintColor:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v8

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->cts:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v9

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_vignette:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v10

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_vignetteRange:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v11

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->adj_sharpness:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v12

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->customlut_clip:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v13

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->customlut_power:Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    aput-object v1, v0, v14

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

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

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;
    .locals 1

    const-class v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;
    .locals 1

    sget-object v0, Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    invoke-virtual {v0}, [Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/nexeditorsdk/nexEngine$FastPreviewOption;

    return-object v0
.end method
