.class public Lcom/miui/gallery/util/deleterecorder/DeleteRecord;
.super Lcom/miui/gallery/dao/base/Entity;
.source "DeleteRecord.java"


# instance fields
.field private mFilePath:Ljava/lang/String;

.field private mReason:I

.field private mTag:Ljava/lang/String;

.field private mTimestamp:J


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    return-void
.end method

.method public constructor <init>(ILjava/lang/String;Ljava/lang/String;)V
    .locals 6

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    move-object v0, p0

    move v3, p1

    move-object v4, p2

    move-object v5, p3

    invoke-direct/range {v0 .. v5}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;-><init>(JILjava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public constructor <init>(JILjava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    iput-wide p1, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTimestamp:J

    iput p3, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mReason:I

    iput-object p4, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mFilePath:Ljava/lang/String;

    iput-object p5, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTag:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 6

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    :cond_0
    const/4 v1, 0x0

    if-eqz p1, :cond_6

    instance-of v2, p1, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;

    if-nez v2, :cond_1

    goto :goto_0

    :cond_1
    check-cast p1, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;

    iget-wide v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTimestamp:J

    iget-wide v4, p1, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTimestamp:J

    cmp-long v2, v2, v4

    if-eqz v2, :cond_2

    return v1

    :cond_2
    iget v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mReason:I

    iget v3, p1, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mReason:I

    if-eq v2, v3, :cond_3

    return v1

    :cond_3
    iget-object v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mFilePath:Ljava/lang/String;

    iget-object v3, p1, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mFilePath:Ljava/lang/String;

    invoke-static {v2, v3}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_4

    return v1

    :cond_4
    iget-object v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTag:Ljava/lang/String;

    iget-object p1, p1, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTag:Ljava/lang/String;

    invoke-static {v2, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result p1

    if-nez p1, :cond_5

    return v1

    :cond_5
    return v0

    :cond_6
    :goto_0
    return v1
.end method

.method public getFilePath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mFilePath:Ljava/lang/String;

    return-object v0
.end method

.method protected getTableColumns()Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/miui/gallery/dao/base/TableColumn;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const-string v1, "INTEGER"

    const-string v2, "timestamp"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "TEXT"

    const-string v3, "filePath"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "reason"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v1, "tag"

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    return-object v0
.end method

.method protected onConvertToContents(Landroid/content/ContentValues;)V
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTimestamp:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "timestamp"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mFilePath:Ljava/lang/String;

    const-string v1, "filePath"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mReason:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "reason"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-object v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTag:Ljava/lang/String;

    const-string v1, "tag"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method protected onInitFromCursor(Landroid/database/Cursor;)V
    .locals 2

    const-string v0, "timestamp"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTimestamp:J

    const-string v0, "filePath"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mFilePath:Ljava/lang/String;

    const-string v0, "reason"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mReason:I

    const-string v0, "tag"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTag:Ljava/lang/String;

    return-void
.end method

.method public toString()Ljava/lang/String;
    .locals 4

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x4

    new-array v1, v1, [Ljava/lang/Object;

    iget-wide v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTimestamp:J

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    const/4 v3, 0x0

    aput-object v2, v1, v3

    iget v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mReason:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const/4 v3, 0x1

    aput-object v2, v1, v3

    iget-object v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mFilePath:Ljava/lang/String;

    const/4 v3, 0x2

    aput-object v2, v1, v3

    iget-object v2, p0, Lcom/miui/gallery/util/deleterecorder/DeleteRecord;->mTag:Ljava/lang/String;

    const/4 v3, 0x3

    aput-object v2, v1, v3

    const-string v2, "DeleteRecord [timestamp=%d, reason=%d, filePath=%s, tag=%s]"

    invoke-static {v0, v2, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
