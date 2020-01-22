.class public Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;
.super Ljava/lang/Object;
.source "CleanerPhotoPickFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field private mCleanerSubEvent:Ljava/lang/String;

.field private mDupType:I

.field private mReason:I


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mReason:I

    iput v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mDupType:I

    return-void
.end method


# virtual methods
.method public build()Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mCleanerSubEvent:Ljava/lang/String;

    if-eqz v0, :cond_0

    iget v1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mReason:I

    const/4 v2, -0x1

    if-eq v1, v2, :cond_0

    iget v3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mDupType:I

    if-eq v3, v2, :cond_0

    new-instance v2, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;

    const/4 v4, 0x0

    invoke-direct {v2, v0, v1, v3, v4}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;-><init>(Ljava/lang/String;IILcom/miui/gallery/ui/CleanerPhotoPickFragment$1;)V

    return-object v2

    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    invoke-direct {v0}, Ljava/lang/IllegalArgumentException;-><init>()V

    throw v0
.end method

.method public setCleanerSubEvent(Ljava/lang/String;)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mCleanerSubEvent:Ljava/lang/String;

    return-object p0
.end method

.method public setDupType(I)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mDupType:I

    return-object p0
.end method

.method public setReason(I)Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;->mReason:I

    return-object p0
.end method
