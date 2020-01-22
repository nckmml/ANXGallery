.class public Lcom/miui/gallery/video/editor/model/VideoEditorTemplateBaseModel;
.super Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;
.source "VideoEditorTemplateBaseModel.java"


# instance fields
.field protected mAssetId:I

.field protected mAssetName:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/video/editor/model/VideoEditorBaseModel;-><init>()V

    return-void
.end method


# virtual methods
.method public getAssetId()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/video/editor/model/VideoEditorTemplateBaseModel;->mAssetId:I

    return v0
.end method

.method public getAssetName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/video/editor/model/VideoEditorTemplateBaseModel;->mAssetName:Ljava/lang/String;

    return-object v0
.end method
