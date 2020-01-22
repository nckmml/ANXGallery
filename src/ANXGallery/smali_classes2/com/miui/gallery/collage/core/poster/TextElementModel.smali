.class public Lcom/miui/gallery/collage/core/poster/TextElementModel;
.super Lcom/miui/gallery/collage/core/poster/ElementPositionModel;
.source "TextElementModel.java"


# instance fields
.field public currentText:Ljava/lang/String;

.field public letterSpace:F

.field public maxLength:I

.field public text:Ljava/lang/String;

.field public textColor:Ljava/lang/String;

.field public textSize:F


# virtual methods
.method public getText(Landroid/content/Context;)Ljava/lang/String;
    .locals 4

    iget-object v0, p0, Lcom/miui/gallery/collage/core/poster/TextElementModel;->text:Ljava/lang/String;

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    const-string v1, ""

    if-eqz v0, :cond_0

    return-object v1

    :cond_0
    invoke-virtual {p1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    iget-object v2, p0, Lcom/miui/gallery/collage/core/poster/TextElementModel;->text:Ljava/lang/String;

    const-string v3, "@string/"

    invoke-virtual {v2, v3}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_2

    iget-object v2, p0, Lcom/miui/gallery/collage/core/poster/TextElementModel;->text:Ljava/lang/String;

    const/16 v3, 0x8

    invoke-virtual {v2, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object p1

    const-string v3, "string"

    invoke-virtual {v0, v2, v3, p1}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result p1

    if-eqz p1, :cond_1

    invoke-virtual {v0, p1}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_1
    return-object v1

    :cond_2
    iget-object p1, p0, Lcom/miui/gallery/collage/core/poster/TextElementModel;->text:Ljava/lang/String;

    return-object p1
.end method
