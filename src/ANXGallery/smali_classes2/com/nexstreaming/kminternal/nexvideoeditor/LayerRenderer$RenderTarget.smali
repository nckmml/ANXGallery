.class public final enum Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;
.super Ljava/lang/Enum;
.source "LayerRenderer.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "RenderTarget"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

.field public static final enum Mask:Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

.field public static final enum Normal:Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    const/4 v1, 0x0

    const-string v2, "Normal"

    invoke-direct {v0, v2, v1}, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;->Normal:Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    new-instance v0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    const/4 v2, 0x1

    const-string v3, "Mask"

    invoke-direct {v0, v3, v2}, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;->Mask:Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    const/4 v0, 0x2

    new-array v0, v0, [Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    sget-object v3, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;->Normal:Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    aput-object v3, v0, v1

    sget-object v1, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;->Mask:Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    aput-object v1, v0, v2

    sput-object v0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;->$VALUES:[Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

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

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;
    .locals 1

    const-class v0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;
    .locals 1

    sget-object v0, Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;->$VALUES:[Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    invoke-virtual {v0}, [Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/kminternal/nexvideoeditor/LayerRenderer$RenderTarget;

    return-object v0
.end method
