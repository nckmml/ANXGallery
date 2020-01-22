.class public Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;
.super Ljava/lang/Object;
.source "CleanerPhotoPickFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/CleanerPhotoPickFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "DeleteMessage"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage$Builder;
    }
.end annotation


# instance fields
.field private mCleanerSubEvent:Ljava/lang/String;

.field private mDupType:I

.field private mReason:I


# direct methods
.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->mCleanerSubEvent:Ljava/lang/String;

    iput p2, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->mReason:I

    iput p3, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->mDupType:I

    return-void
.end method

.method synthetic constructor <init>(Ljava/lang/String;IILcom/miui/gallery/ui/CleanerPhotoPickFragment$1;)V
    .locals 0

    invoke-direct {p0, p1, p2, p3}, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;-><init>(Ljava/lang/String;II)V

    return-void
.end method


# virtual methods
.method public getCleanerSubEvent()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->mCleanerSubEvent:Ljava/lang/String;

    return-object v0
.end method

.method public getDupType()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->mDupType:I

    return v0
.end method

.method public getReason()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/ui/CleanerPhotoPickFragment$DeleteMessage;->mReason:I

    return v0
.end method
