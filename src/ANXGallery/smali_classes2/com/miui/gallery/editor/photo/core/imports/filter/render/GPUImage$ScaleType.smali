.class public final enum Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;
.super Ljava/lang/Enum;
.source "GPUImage.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "ScaleType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

.field public static final enum CENTER_CROP:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

.field public static final enum CENTER_INSIDE:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    const/4 v1, 0x0

    const-string v2, "CENTER_INSIDE"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;->CENTER_INSIDE:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    const/4 v2, 0x1

    const-string v3, "CENTER_CROP"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;->CENTER_CROP:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    const/4 v0, 0x2

    new-array v0, v0, [Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    sget-object v3, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;->CENTER_INSIDE:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    aput-object v3, v0, v1

    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;->CENTER_CROP:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;->$VALUES:[Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;
    .locals 1

    const-class v0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;
    .locals 1

    sget-object v0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;->$VALUES:[Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    invoke-virtual {v0}, [Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImage$ScaleType;

    return-object v0
.end method
