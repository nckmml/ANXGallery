.class public Lcom/miui/gallery/editor/photo/core/imports/text/model/FontBean;
.super Ljava/lang/Object;
.source "FontBean.java"


# instance fields
.field protected extraInfo:Lcom/miui/gallery/editor/photo/core/imports/text/model/ExtraInfo;

.field protected icon:Ljava/lang/String;

.field protected id:J

.field protected size:J

.field protected text:Ljava/lang/String;

.field protected type:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getExtraInfo()Lcom/miui/gallery/editor/photo/core/imports/text/model/ExtraInfo;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/model/FontBean;->extraInfo:Lcom/miui/gallery/editor/photo/core/imports/text/model/ExtraInfo;

    return-object v0
.end method

.method public getId()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/model/FontBean;->id:J

    return-wide v0
.end method

.method public getText()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/text/model/FontBean;->text:Ljava/lang/String;

    return-object v0
.end method
