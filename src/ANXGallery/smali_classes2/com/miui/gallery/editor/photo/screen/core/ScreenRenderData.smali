.class public Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;
.super Lcom/miui/gallery/editor/photo/core/RenderData;
.source "ScreenRenderData.java"


# annotations
.annotation build Landroid/annotation/SuppressLint;
    value = {
        "ParcelCreator"
    }
.end annotation


# instance fields
.field public mDrawEntry:Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;

.field public mLongCropEntry:Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

.field mScreenCropEntry:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/editor/photo/core/RenderData;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mDrawEntry:Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mScreenCropEntry:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;

    return-void
.end method


# virtual methods
.method public apply(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mDrawEntry:Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;->apply(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object p1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mScreenCropEntry:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;

    if-eqz v0, :cond_0

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;->apply(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object p1

    return-object p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mLongCropEntry:Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

    if-eqz v0, :cond_1

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;->apply(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;

    move-result-object p1

    :cond_1
    return-object p1
.end method

.method public putStat(Ljava/util/HashMap;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/HashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mScreenCropEntry:Lcom/miui/gallery/editor/photo/screen/crop/ScreenCropEntry;

    const-string v1, "false"

    const-string v2, "true"

    if-nez v0, :cond_0

    move-object v0, v1

    goto :goto_0

    :cond_0
    move-object v0, v2

    :goto_0
    const-string v3, "cropChanged"

    invoke-virtual {p1, v3, v0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mLongCropEntry:Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;->isModified()Z

    move-result v0

    if-eqz v0, :cond_1

    move-object v1, v2

    :cond_1
    const-string v0, "longCropChanged"

    invoke-virtual {p1, v0, v1}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mDrawEntry:Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/editor/photo/screen/core/ScreenDrawEntry;->putStat(Ljava/util/HashMap;)V

    return-void
.end method

.method public setLongCropEntry(Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/screen/core/ScreenRenderData;->mLongCropEntry:Lcom/miui/gallery/editor/photo/core/imports/longcrop/LongScreenshotCropEditorView$Entry;

    return-void
.end method
