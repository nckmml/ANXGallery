.class public Lcom/miui/gallery/card/scenario/Record;
.super Lcom/miui/gallery/dao/base/Entity;
.source "Record.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/card/scenario/Record$UniqueKey;
    }
.end annotation


# instance fields
.field private mEndTime:J

.field private mLocation:Ljava/lang/String;

.field private mMediaIds:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field

.field private mPeopleId:Ljava/lang/String;

.field private mPrimaryKey:Ljava/lang/String;

.field private mScenarioId:I

.field private mSecondaryKey:Ljava/lang/String;

.field private mStartTime:J

.field private mState:I

.field private mTargetTime:J

.field private mTertiaryKey:Ljava/lang/String;

.field private mTime:J


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    return-void
.end method

.method public constructor <init>(Lcom/miui/gallery/card/scenario/Scenario;Ljava/util/List;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/card/scenario/Scenario;",
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/dao/base/Entity;-><init>()V

    invoke-static {}, Lcom/miui/gallery/card/scenario/DateUtils;->getCurrentTimeMillis()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTime:J

    iput-object p2, p0, Lcom/miui/gallery/card/scenario/Record;->mMediaIds:Ljava/util/List;

    const/4 p2, 0x1

    iput p2, p0, Lcom/miui/gallery/card/scenario/Record;->mState:I

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getScenarioId()I

    move-result p2

    iput p2, p0, Lcom/miui/gallery/card/scenario/Record;->mScenarioId:I

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getStartTime()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mStartTime:J

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getEndTime()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mEndTime:J

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getTargetTime()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long p2, v0, v2

    if-gtz p2, :cond_0

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mStartTime:J

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getTargetTime()J

    move-result-wide v0

    :goto_0
    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTargetTime:J

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getPeopleId()Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/card/scenario/Record;->mPeopleId:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getLocation()Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/card/scenario/Record;->mLocation:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getPrimaryKey()Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/card/scenario/Record;->mPrimaryKey:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getSecondary()Ljava/lang/String;

    move-result-object p2

    iput-object p2, p0, Lcom/miui/gallery/card/scenario/Record;->mSecondaryKey:Ljava/lang/String;

    invoke-virtual {p1}, Lcom/miui/gallery/card/scenario/Scenario;->getTertiaryKey()Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/card/scenario/Record;->mTertiaryKey:Ljava/lang/String;

    :cond_1
    return-void
.end method


# virtual methods
.method public getEndTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mEndTime:J

    return-wide v0
.end method

.method public getLocation()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mLocation:Ljava/lang/String;

    return-object v0
.end method

.method public getMediaIds()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mMediaIds:Ljava/util/List;

    return-object v0
.end method

.method public getPeopleId()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mPeopleId:Ljava/lang/String;

    return-object v0
.end method

.method public getPrimaryKey()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mPrimaryKey:Ljava/lang/String;

    return-object v0
.end method

.method public getScenarioId()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/card/scenario/Record;->mScenarioId:I

    return v0
.end method

.method public getSecondaryKey()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mSecondaryKey:Ljava/lang/String;

    return-object v0
.end method

.method public getStartTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mStartTime:J

    return-wide v0
.end method

.method public getState()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/card/scenario/Record;->mState:I

    return v0
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

    const-string v2, "scenarioId"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "scenarioStartTime"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "scenarioEndTime"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "TEXT"

    const-string v3, "scenarioPeopleId"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "scenarioLocation"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "scenarioPrimaryKey"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "scenarioSecondaryKey"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "scenarioTertiaryKey"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "time"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "state"

    invoke-static {v0, v3, v1}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v3, "medias"

    invoke-static {v0, v3, v2}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    const-string v2, "scenarioTargetTime"

    invoke-static {v0, v2, v1}, Lcom/miui/gallery/card/scenario/Record;->addColumn(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V

    return-object v0
.end method

.method public getTargetTime()J
    .locals 4

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTargetTime:J

    const-wide/16 v2, 0x0

    cmp-long v2, v0, v2

    if-gtz v2, :cond_0

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mStartTime:J

    :cond_0
    return-wide v0
.end method

.method public getTime()J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTime:J

    return-wide v0
.end method

.method protected getUniqueConstraints()[Ljava/lang/String;
    .locals 1

    const-string v0, "scenarioId"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public getUniqueKey()Lcom/miui/gallery/card/scenario/Record$UniqueKey;
    .locals 14

    new-instance v13, Lcom/miui/gallery/card/scenario/Record$UniqueKey;

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getScenarioId()I

    move-result v1

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getStartTime()J

    move-result-wide v2

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getEndTime()J

    move-result-wide v4

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getTargetTime()J

    move-result-wide v6

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getPeopleId()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getLocation()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getPrimaryKey()Ljava/lang/String;

    move-result-object v10

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getSecondaryKey()Ljava/lang/String;

    move-result-object v11

    invoke-virtual {p0}, Lcom/miui/gallery/card/scenario/Record;->getPrimaryKey()Ljava/lang/String;

    move-result-object v12

    move-object v0, v13

    invoke-direct/range {v0 .. v12}, Lcom/miui/gallery/card/scenario/Record$UniqueKey;-><init>(IJJJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-object v13
.end method

.method protected onConvertToContents(Landroid/content/ContentValues;)V
    .locals 3

    iget v0, p0, Lcom/miui/gallery/card/scenario/Record;->mScenarioId:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "scenarioId"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mStartTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "scenarioStartTime"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mEndTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "scenarioEndTime"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mPeopleId:Ljava/lang/String;

    const-string v1, ""

    if-nez v0, :cond_0

    move-object v0, v1

    :cond_0
    const-string v2, "scenarioPeopleId"

    invoke-virtual {p1, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mLocation:Ljava/lang/String;

    if-nez v0, :cond_1

    move-object v0, v1

    :cond_1
    const-string v2, "scenarioLocation"

    invoke-virtual {p1, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mPrimaryKey:Ljava/lang/String;

    if-nez v0, :cond_2

    move-object v0, v1

    :cond_2
    const-string v2, "scenarioPrimaryKey"

    invoke-virtual {p1, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mSecondaryKey:Ljava/lang/String;

    if-nez v0, :cond_3

    move-object v0, v1

    :cond_3
    const-string v2, "scenarioSecondaryKey"

    invoke-virtual {p1, v2, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTertiaryKey:Ljava/lang/String;

    if-nez v0, :cond_4

    move-object v0, v1

    :cond_4
    const-string v1, "scenarioTertiaryKey"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "time"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget v0, p0, Lcom/miui/gallery/card/scenario/Record;->mState:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "state"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    iget-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mMediaIds:Ljava/util/List;

    invoke-static {v0}, Lcom/miui/gallery/util/GsonUtils;->toString(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "medias"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTargetTime:J

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    const-string v1, "scenarioTargetTime"

    invoke-virtual {p1, v1, v0}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    return-void
.end method

.method protected onInitFromCursor(Landroid/database/Cursor;)V
    .locals 4

    const-string v0, "scenarioId"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/card/scenario/Record;->mScenarioId:I

    const-string v0, "scenarioStartTime"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mStartTime:J

    const-string v0, "scenarioEndTime"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mEndTime:J

    const-string v0, "scenarioPeopleId"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mPeopleId:Ljava/lang/String;

    const-string v0, "scenarioLocation"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mLocation:Ljava/lang/String;

    const-string v0, "scenarioPrimaryKey"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mPrimaryKey:Ljava/lang/String;

    const-string v0, "scenarioSecondaryKey"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mSecondaryKey:Ljava/lang/String;

    const-string v0, "scenarioTertiaryKey"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTertiaryKey:Ljava/lang/String;

    const-string v0, "time"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTime:J

    const-string v0, "state"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getInt(Landroid/database/Cursor;Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/miui/gallery/card/scenario/Record;->mState:I

    :try_start_0
    const-string v0, "medias"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getString(Landroid/database/Cursor;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-class v1, Ljava/lang/Long;

    invoke-static {v0, v1}, Lcom/miui/gallery/util/GsonUtils;->getArray(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/util/ArrayList;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/card/scenario/Record;->mMediaIds:Ljava/util/List;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    iget v0, p0, Lcom/miui/gallery/card/scenario/Record;->mScenarioId:I

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "Record"

    const-string v2, "Get media array of scenario %d from cursor error"

    invoke-static {v1, v2, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    :goto_0
    const-string v0, "scenarioTargetTime"

    invoke-static {p1, v0}, Lcom/miui/gallery/card/scenario/Record;->getLong(Landroid/database/Cursor;Ljava/lang/String;)J

    move-result-wide v0

    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTargetTime:J

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTargetTime:J

    const-wide/16 v2, 0x0

    cmp-long p1, v0, v2

    if-gtz p1, :cond_0

    iget-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mStartTime:J

    :cond_0
    iput-wide v0, p0, Lcom/miui/gallery/card/scenario/Record;->mTargetTime:J

    return-void
.end method

.method public setState(I)V
    .locals 0

    iput p1, p0, Lcom/miui/gallery/card/scenario/Record;->mState:I

    return-void
.end method
