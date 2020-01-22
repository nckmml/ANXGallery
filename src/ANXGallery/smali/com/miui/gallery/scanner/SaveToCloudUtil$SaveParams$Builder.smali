.class public Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
.super Ljava/lang/Object;
.source "SaveToCloudUtil.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Builder"
.end annotation


# instance fields
.field private isBulkNotify:Z

.field private isForceParserPhotoDateTime:Z

.field private isInternalScanRequest:Z

.field private mAlbumAttributes:I

.field private mAlbumId:J

.field private mAlbumSyncState:I

.field private mFileLastModifyTime:J

.field private mLocalFlag:I

.field private mSaveFile:Ljava/io/File;


# direct methods
.method public constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, -0x1

    iput v0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mAlbumSyncState:I

    return-void
.end method

.method static synthetic access$1000(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mAlbumAttributes:I

    return p0
.end method

.method static synthetic access$1100(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->isInternalScanRequest:Z

    return p0
.end method

.method static synthetic access$1200(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->isForceParserPhotoDateTime:Z

    return p0
.end method

.method static synthetic access$600(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;)J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mFileLastModifyTime:J

    return-wide v0
.end method

.method static synthetic access$700(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;)J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mAlbumId:J

    return-wide v0
.end method

.method static synthetic access$800(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mLocalFlag:I

    return p0
.end method

.method static synthetic access$900(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;)Ljava/io/File;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mSaveFile:Ljava/io/File;

    return-object p0
.end method


# virtual methods
.method public build()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;
    .locals 2

    new-instance v0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams;-><init>(Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;Lcom/miui/gallery/scanner/SaveToCloudUtil$1;)V

    return-object v0
.end method

.method public disableBulkNotify()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->isBulkNotify:Z

    return-object p0
.end method

.method public enableForceParserPhotoDateTime()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 1

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->isForceParserPhotoDateTime:Z

    return-object p0
.end method

.method public enableInternalScanRequest()Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 1

    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->isInternalScanRequest:Z

    return-object p0
.end method

.method public setAlbumAttributes(I)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mAlbumAttributes:I

    return-object p0
.end method

.method public setAlbumId(J)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mAlbumId:J

    return-object p0
.end method

.method public setFileLastModifyTime(J)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 0

    iput-wide p1, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mFileLastModifyTime:J

    return-object p0
.end method

.method public setLocalFlag(I)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 0

    iput p1, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mLocalFlag:I

    return-object p0
.end method

.method public setSaveFile(Ljava/io/File;)Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/scanner/SaveToCloudUtil$SaveParams$Builder;->mSaveFile:Ljava/io/File;

    return-object p0
.end method
