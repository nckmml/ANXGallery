.class public Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;
.super Ljava/lang/Object;
.source "SimpleSelectDialog.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "SelectModel"
.end annotation


# instance fields
.field public creationId:I

.field public iconResourceId:I

.field public informationString:Ljava/lang/String;

.field public isRemainWhenClick:Z

.field public showIcon:Z

.field public titleResourceId:I


# direct methods
.method public constructor <init>(II)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;->isRemainWhenClick:Z

    iput p1, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;->creationId:I

    iput p2, p0, Lcom/miui/gallery/editor/photo/app/SimpleSelectDialog$SelectModel;->titleResourceId:I

    return-void
.end method
