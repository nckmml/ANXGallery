.class Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;
.super Ljava/lang/Object;
.source "AutoLineLayout.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "SingleLineText"
.end annotation


# instance fields
.field private mBuilder:Ljava/lang/StringBuilder;

.field private mWidth:F

.field final synthetic this$0:Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->this$0:Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Ljava/lang/StringBuilder;

    const-string v0, ""

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->mBuilder:Ljava/lang/StringBuilder;

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->mWidth:F

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;-><init>(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;)V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;)F
    .locals 0

    iget p0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->mWidth:F

    return p0
.end method

.method static synthetic access$800(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;)Ljava/lang/StringBuilder;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->mBuilder:Ljava/lang/StringBuilder;

    return-object p0
.end method


# virtual methods
.method public count()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->this$0:Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;->access$400(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;)Landroid/text/TextPaint;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->mBuilder:Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/text/TextPaint;->measureText(Ljava/lang/String;)F

    move-result v0

    iput v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->mWidth:F

    return-void
.end method

.method public draw(Landroid/graphics/Canvas;)V
    .locals 7

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->mBuilder:Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->length()I

    move-result v3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->this$0:Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;->access$500(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;)Landroid/graphics/Paint$FontMetricsInt;

    move-result-object v0

    iget v0, v0, Landroid/graphics/Paint$FontMetricsInt;->ascent:I

    neg-int v0, v0

    int-to-float v5, v0

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout$SingleLineText;->this$0:Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;->access$400(Lcom/miui/gallery/editor/photo/core/imports/text/utils/AutoLineLayout;)Landroid/text/TextPaint;

    move-result-object v6

    const/4 v2, 0x0

    const/4 v4, 0x0

    move-object v0, p1

    invoke-virtual/range {v0 .. v6}, Landroid/graphics/Canvas;->drawText(Ljava/lang/CharSequence;IIFFLandroid/graphics/Paint;)V

    return-void
.end method
