.class Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoByPath;
.super Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;
.source "CloudManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/cloudmanager/CloudManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "EditPhotoDateInfoByPath"
.end annotation


# instance fields
.field protected mSourceFilePath:Ljava/lang/String;


# direct methods
.method public constructor <init>(Landroid/content/Context;Ljava/util/ArrayList;JLjava/lang/String;Z)V
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Long;",
            ">;J",
            "Ljava/lang/String;",
            "Z)V"
        }
    .end annotation

    const-wide/16 v3, -0x1

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-wide v5, p3

    move v7, p6

    invoke-direct/range {v0 .. v7}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;-><init>(Landroid/content/Context;Ljava/util/ArrayList;JJZ)V

    iput-object p5, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoByPath;->mSourceFilePath:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method protected prepare(Landroid/database/sqlite/SQLiteDatabase;)Landroid/database/Cursor;
    .locals 8

    iget-object v2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoByPath;->mBasicColumns:[Ljava/lang/String;

    iget-object v0, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoByPath;->mSourceFilePath:Ljava/lang/String;

    invoke-virtual {p0, v0}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoByPath;->getFilePathSelection(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    const-string v1, "cloud"

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v0, p1

    invoke-virtual/range {v0 .. v7}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p1

    return-object p1
.end method

.method public toString()Ljava/lang/String;
    .locals 4

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    iget-object v2, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoByPath;->mSourceFilePath:Ljava/lang/String;

    const/4 v3, 0x0

    aput-object v2, v1, v3

    const-string v2, "updatePhotoDateTime by path: [%s]"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected verify(Landroid/database/sqlite/SQLiteDatabase;)J
    .locals 4

    invoke-super {p0, p1}, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoById;->verify(Landroid/database/sqlite/SQLiteDatabase;)J

    move-result-wide v0

    const-wide/16 v2, -0x1

    cmp-long p1, v0, v2

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/miui/gallery/provider/cloudmanager/CloudManager$EditPhotoDateInfoByPath;->mCursor:Landroid/database/Cursor;

    invoke-interface {p1}, Landroid/database/Cursor;->getCount()I

    move-result p1

    const/4 v0, 0x1

    if-gt p1, v0, :cond_0

    goto :goto_0

    :cond_0
    const-wide/16 v2, -0x67

    :goto_0
    return-wide v2

    :cond_1
    return-wide v0
.end method
