.class public Lcom/xiaomi/mistatistic/sdk/controller/h;
.super Ljava/lang/Object;
.source "EventDAO.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/xiaomi/mistatistic/sdk/controller/h$a;
    }
.end annotation


# static fields
.field public static a:Z = false

.field private static b:Ljava/lang/String; = ""

.field private static volatile c:Lcom/xiaomi/mistatistic/sdk/controller/k;


# instance fields
.field private d:Z

.field private e:Lcom/xiaomi/mistatistic/sdk/a;

.field private f:Z

.field private g:Landroid/content/ServiceConnection;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->d:Z

    const/4 v1, 0x0

    iput-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    iput-boolean v0, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    new-instance v0, Lcom/xiaomi/mistatistic/sdk/controller/h$1;

    invoke-direct {v0, p0}, Lcom/xiaomi/mistatistic/sdk/controller/h$1;-><init>(Lcom/xiaomi/mistatistic/sdk/controller/h;)V

    iput-object v0, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->g:Landroid/content/ServiceConnection;

    return-void
.end method

.method static synthetic a(Lcom/xiaomi/mistatistic/sdk/controller/h;Lcom/xiaomi/mistatistic/sdk/a;)Lcom/xiaomi/mistatistic/sdk/a;
    .locals 0

    iput-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    return-object p1
.end method

.method private static a(Landroid/database/Cursor;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;
    .locals 9

    new-instance v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;

    invoke-direct {v0}, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;-><init>()V

    const/4 v1, 0x2

    invoke-interface {p0, v1}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v1

    const/4 v3, 0x4

    invoke-interface {p0, v3}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x5

    invoke-interface {p0, v4}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v4

    const/4 v5, 0x1

    invoke-interface {p0, v5}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v5

    const/4 v6, 0x3

    invoke-interface {p0, v6}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v6

    const/4 v7, 0x6

    invoke-interface {p0, v7}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x7

    invoke-interface {p0, v8}, Landroid/database/Cursor;->getInt(I)I

    move-result p0

    iput-object v5, v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mCategory:Ljava/lang/String;

    iput-object v6, v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mKey:Ljava/lang/String;

    iput-object v3, v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mValue:Ljava/lang/String;

    iput-wide v1, v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mTimeStamp:J

    iput-object v4, v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mType:Ljava/lang/String;

    iput-object v7, v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mExtra:Ljava/lang/String;

    iput p0, v0, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mAnonymous:I

    return-object v0
.end method

.method public static a()V
    .locals 1

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    sput-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    return-void
.end method

.method static synthetic a(Lcom/xiaomi/mistatistic/sdk/controller/h;Z)Z
    .locals 0

    iput-boolean p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    return p1
.end method

.method public static b()Z
    .locals 1

    sget-boolean v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    return v0
.end method

.method private f()V
    .locals 5

    const-string v0, "DAO"

    iget-boolean v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    if-nez v1, :cond_1

    :try_start_0
    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v1

    new-instance v2, Landroid/content/Intent;

    sget-object v3, Lcom/xiaomi/mistatistic/sdk/controller/h;->b:Ljava/lang/String;

    invoke-static {v3}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v3

    invoke-direct {v2, v1, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {v1, v2}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    iget-object v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    if-eqz v3, :cond_0

    const-string v3, "unbind service before bind it again!"

    invoke-static {v0, v3}, Lcom/xiaomi/mistatistic/sdk/controller/j;->b(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->g:Landroid/content/ServiceConnection;

    invoke-virtual {v1, v3}, Landroid/content/Context;->unbindService(Landroid/content/ServiceConnection;)V

    :cond_0
    iget-object v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->g:Landroid/content/ServiceConnection;

    const/4 v4, 0x1

    invoke-virtual {v1, v2, v3, v4}, Landroid/content/Context;->bindService(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v1

    const-string v2, "ensureServiceBinded exception"

    invoke-static {v0, v2, v1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    return-void
.end method

.method private static g()Lcom/xiaomi/mistatistic/sdk/controller/k;
    .locals 1

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-nez v0, :cond_0

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h$a;->a()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    sput-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :cond_0
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    return-object v0
.end method


# virtual methods
.method public a(I)I
    .locals 8

    const-string v0, "DAO"

    sget-boolean v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v1, :cond_3

    invoke-direct {p0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->f()V

    const/4 v1, 0x0

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    :cond_0
    iget-boolean v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    if-eqz v4, :cond_2

    iget-object v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    if-nez v4, :cond_1

    goto :goto_0

    :cond_1
    iget-object v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    invoke-interface {v2, p1}, Lcom/xiaomi/mistatistic/sdk/a;->a(I)I

    move-result p1

    const-string v2, "process getEventCount, result is: %d"

    const/4 v3, 0x1

    new-array v3, v3, [Ljava/lang/Object;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    aput-object v4, v3, v1

    invoke-static {v2, v3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v0, v2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->b(Ljava/lang/String;Ljava/lang/String;)V

    return p1

    :cond_2
    :goto_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    sub-long/2addr v4, v2

    const-wide/16 v6, 0x3e8

    cmp-long v4, v4, v6

    if-lez v4, :cond_0

    return v1

    :catch_0
    move-exception p1

    const-string v2, "getEventCount"

    invoke-static {v0, v2, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    return v1

    :cond_3
    invoke-virtual {p0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/h;->b(I)I

    move-result p1

    return p1
.end method

.method public a(Ljava/lang/String;Ljava/lang/String;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;
    .locals 8

    const-string v0, "DAO"

    sget-boolean v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v1, :cond_3

    invoke-direct {p0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->f()V

    const/4 v1, 0x0

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    :cond_0
    iget-boolean v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    if-eqz v4, :cond_2

    iget-object v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    if-nez v4, :cond_1

    goto :goto_0

    :cond_1
    iget-object v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    invoke-interface {v2, p1, p2}, Lcom/xiaomi/mistatistic/sdk/a;->a(Ljava/lang/String;Ljava/lang/String;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "process query, result is: "

    invoke-virtual {p2, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->b(Ljava/lang/String;Ljava/lang/String;)V

    return-object p1

    :cond_2
    :goto_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    sub-long/2addr v4, v2

    const-wide/16 v6, 0x3e8

    cmp-long v4, v4, v6

    if-lez v4, :cond_0

    return-object v1

    :catch_0
    move-exception p1

    const-string p2, "queryCustomEvent exception"

    invoke-static {v0, p2, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v1

    :cond_3
    invoke-virtual {p0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->b(Ljava/lang/String;Ljava/lang/String;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;

    move-result-object p1

    return-object p1
.end method

.method public a(J)Ljava/util/List;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Ljava/util/List<",
            "Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;",
            ">;"
        }
    .end annotation

    const-string v0, "DAO"

    sget-boolean v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v1, :cond_4

    invoke-direct {p0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->f()V

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    :cond_0
    iget-boolean v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    if-eqz v3, :cond_3

    iget-object v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    if-nez v3, :cond_1

    goto :goto_1

    :cond_1
    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    invoke-interface {v1, p1, p2}, Lcom/xiaomi/mistatistic/sdk/a;->a(J)Ljava/util/List;

    move-result-object p1

    const-string p2, "process getAll, result size is : %d"

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    if-nez p1, :cond_2

    move v3, v2

    goto :goto_0

    :cond_2
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v3

    :goto_0
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v1, v2

    invoke-static {p2, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->b(Ljava/lang/String;Ljava/lang/String;)V

    return-object p1

    :cond_3
    :goto_1
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    sub-long/2addr v3, v1

    const-wide/16 v5, 0x3e8

    cmp-long v3, v3, v5

    if-lez v3, :cond_0

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p1

    :catch_0
    move-exception p1

    const-string p2, "getAllEventOrderByTimestampDescend exception"

    invoke-static {v0, p2, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    return-object p1

    :cond_4
    invoke-virtual {p0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->b(J)Ljava/util/List;

    move-result-object p1

    return-object p1
.end method

.method public a(JJI)V
    .locals 4

    sget-boolean v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v0, :cond_0

    :try_start_0
    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v0

    new-instance v1, Landroid/content/Intent;

    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->b:Ljava/lang/String;

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    invoke-direct {v1, v0, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v2, "type"

    const/4 v3, 0x5

    invoke-virtual {v1, v2, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    const-string v2, "startTime"

    invoke-virtual {v1, v2, p1, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    const-string p1, "endTime"

    invoke-virtual {v1, p1, p3, p4}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    const-string p1, "eventType"

    invoke-virtual {v1, p1, p5}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    invoke-virtual {v0, v1}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string p2, "DAO"

    const-string p3, "deleteEventsByStartAndEndTS"

    invoke-static {p2, p3, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    :cond_0
    invoke-virtual/range {p0 .. p5}, Lcom/xiaomi/mistatistic/sdk/controller/h;->b(JJI)V

    :goto_0
    return-void
.end method

.method public a(Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;)V
    .locals 3

    sget-boolean v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v0, :cond_0

    :try_start_0
    new-instance v0, Landroid/content/Intent;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v1

    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->b:Ljava/lang/String;

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v1, "type"

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    const-string v1, "StatEventPojo"

    invoke-virtual {v0, v1, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1, v0}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string v0, "DAO"

    const-string v1, "insertNewEvent exception"

    invoke-static {v0, v1, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/h;->b(Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;)V

    :goto_0
    return-void
.end method

.method public a(Ljava/lang/Boolean;)V
    .locals 0

    invoke-virtual {p1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p1

    iput-boolean p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->d:Z

    return-void
.end method

.method public a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    sget-boolean v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v0, :cond_0

    :try_start_0
    new-instance v0, Landroid/content/Intent;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v1

    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->b:Ljava/lang/String;

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v1, "type"

    const/4 v2, 0x2

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    const-string v1, "key"

    invoke-virtual {v0, v1, p1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    const-string p1, "category"

    invoke-virtual {v0, p1, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    const-string p1, "newValue"

    invoke-virtual {v0, p1, p3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1, v0}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string p2, "DAO"

    const-string p3, "updateEventByKeyAndCategory exception"

    invoke-static {p2, p3, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/xiaomi/mistatistic/sdk/controller/h;->b(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method public b(I)I
    .locals 16

    move/from16 v0, p1

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v1

    monitor-enter v1

    const/4 v2, 0x0

    const/4 v3, 0x0

    :try_start_0
    sget-object v4, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v4}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v5

    const/4 v4, 0x1

    if-ne v0, v4, :cond_0

    const-string v6, "mistat_event"

    const-string v0, "count(*)"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    invoke-virtual/range {v5 .. v12}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    :goto_0
    move-object v3, v0

    goto :goto_1

    :cond_0
    const/4 v4, 0x2

    if-ne v0, v4, :cond_1

    const-string v6, "mistat_event"

    const-string v0, "count(*)"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v7

    const-string v8, "category IN (?, ?, ?, ?, ?, ?, ?)"

    const-string v9, "mistat_basic"

    const-string v10, "mistat_pt"

    const-string v11, "mistat_pv"

    const-string v12, "mistat_session"

    const-string v13, "mistat_pa"

    const-string v14, "mistat_session_extra"

    const-string v15, "mistat_monitor"

    filled-new-array/range {v9 .. v15}, [Ljava/lang/String;

    move-result-object v9

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    invoke-virtual/range {v5 .. v13}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    goto :goto_0

    :cond_1
    :goto_1
    if-eqz v3, :cond_4

    invoke-interface {v3}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {v3, v2}, Landroid/database/Cursor;->getInt(I)I

    move-result v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v3, :cond_2

    :try_start_1
    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    :cond_2
    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v2, :cond_3

    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_3
    monitor-exit v1

    return v0

    :cond_4
    if-eqz v3, :cond_5

    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    :cond_5
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v0, :cond_7

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_2
    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_3

    :catchall_0
    move-exception v0

    goto :goto_4

    :catch_0
    move-exception v0

    :try_start_2
    const-string v4, "DAO"

    const-string v5, "Error while getting count from DB"

    invoke-static {v4, v5, v0}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eqz v3, :cond_6

    :try_start_3
    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    :cond_6
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v0, :cond_7

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_2

    :cond_7
    :goto_3
    monitor-exit v1

    return v2

    :goto_4
    if-eqz v3, :cond_8

    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    :cond_8
    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v2, :cond_9

    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_9
    throw v0

    :catchall_1
    move-exception v0

    monitor-exit v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw v0
.end method

.method public b(Ljava/lang/String;Ljava/lang/String;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;
    .locals 11

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    monitor-enter v0

    const/4 v1, 0x0

    :try_start_0
    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v3

    const-string v4, "mistat_event"

    const/4 v5, 0x0

    const-string v6, "category=? AND key=?"

    const/4 v2, 0x2

    new-array v7, v2, [Ljava/lang/String;

    const/4 v2, 0x0

    aput-object p1, v7, v2

    const/4 p1, 0x1

    aput-object p2, v7, p1

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    invoke-virtual/range {v3 .. v10}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_0

    :try_start_1
    invoke-interface {p1}, Landroid/database/Cursor;->moveToFirst()Z

    move-result p2

    if-eqz p2, :cond_0

    invoke-static {p1}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Landroid/database/Cursor;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;

    move-result-object v1
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_0

    :catch_0
    move-exception p2

    goto :goto_2

    :cond_0
    :goto_0
    if-eqz p1, :cond_1

    :try_start_2
    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    :cond_1
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_3

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_1
    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    goto :goto_3

    :catchall_0
    move-exception p2

    goto :goto_4

    :catch_1
    move-exception p2

    move-object p1, v1

    :goto_2
    :try_start_3
    const-string v2, "DAO"

    const-string v3, "queryCustomEvent exception"

    invoke-static {v2, v3, p2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    if-eqz p1, :cond_2

    :try_start_4
    invoke-interface {p1}, Landroid/database/Cursor;->close()V

    :cond_2
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_3

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_1

    :cond_3
    :goto_3
    monitor-exit v0

    return-object v1

    :catchall_1
    move-exception p2

    move-object v1, p1

    :goto_4
    if-eqz v1, :cond_4

    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_4
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_5

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_5
    throw p2

    :catchall_2
    move-exception p1

    monitor-exit v0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    throw p1
.end method

.method public b(J)Ljava/util/List;
    .locals 15
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Ljava/util/List<",
            "Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;",
            ">;"
        }
    .end annotation

    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v2

    monitor-enter v2

    const/4 v3, 0x0

    :try_start_0
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_1
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    if-nez v0, :cond_1

    :try_start_1
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v0, :cond_0

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_0
    monitor-exit v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    return-object v1

    :catchall_0
    move-exception v0

    move-object v12, p0

    goto/16 :goto_7

    :cond_1
    :try_start_2
    const-string v5, "mistat_event"

    const/4 v6, 0x0

    const-string v7, "ts < ? "

    const/4 v13, 0x1

    new-array v8, v13, [Ljava/lang/String;

    invoke-static/range {p1 .. p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    const/4 v14, 0x0

    aput-object v4, v8, v14

    const/4 v9, 0x0

    const/4 v10, 0x0

    const-string v11, "ts DESC"

    const/16 v4, 0x1f4

    invoke-static {v4}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v12

    move-object v4, v0

    invoke-virtual/range {v4 .. v12}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v3

    if-eqz v3, :cond_3

    invoke-interface {v3}, Landroid/database/Cursor;->moveToLast()Z

    move-result v4

    if-eqz v4, :cond_3

    const-string v4, "ts"

    invoke-interface {v3, v4}, Landroid/database/Cursor;->getColumnIndex(Ljava/lang/String;)I

    move-result v4

    invoke-interface {v3, v4}, Landroid/database/Cursor;->getLong(I)J

    move-result-wide v4

    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    const-string v7, "ts<? AND ts>=? AND anonymous=?"

    const/4 v6, 0x3

    new-array v8, v6, [Ljava/lang/String;

    invoke-static/range {p1 .. p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v6

    aput-object v6, v8, v14

    invoke-static {v4, v5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    aput-object v4, v8, v13
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_1
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    const/4 v4, 0x2

    move-object v12, p0

    :try_start_3
    iget-boolean v5, v12, Lcom/xiaomi/mistatistic/sdk/controller/h;->d:Z

    if-eqz v5, :cond_2

    invoke-static {v13}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v5

    goto :goto_0

    :cond_2
    invoke-static {v14}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v5

    :goto_0
    aput-object v5, v8, v4

    const-string v5, "mistat_event"

    const/4 v6, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const-string v11, "ts DESC"

    move-object v4, v0

    invoke-virtual/range {v4 .. v11}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    move-object v3, v0

    goto :goto_1

    :cond_3
    move-object v12, p0

    :goto_1
    if-eqz v3, :cond_5

    invoke-interface {v3}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v0

    if-eqz v0, :cond_5

    :cond_4
    invoke-static {v3}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Landroid/database/Cursor;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;

    move-result-object v0

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-interface {v3}, Landroid/database/Cursor;->moveToNext()Z

    move-result v0
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    if-nez v0, :cond_4

    goto :goto_2

    :catch_0
    move-exception v0

    goto :goto_4

    :cond_5
    :goto_2
    if-eqz v3, :cond_6

    :try_start_4
    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    :cond_6
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v0, :cond_8

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_3
    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_3

    goto :goto_5

    :catchall_1
    move-exception v0

    move-object v12, p0

    goto :goto_6

    :catch_1
    move-exception v0

    move-object v12, p0

    :goto_4
    :try_start_5
    const-string v4, "DAO"

    const-string v5, "Error while reading data from DB"

    invoke-static {v4, v5, v0}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    if-eqz v3, :cond_7

    :try_start_6
    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    :cond_7
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v0, :cond_8

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_3

    :cond_8
    :goto_5
    monitor-exit v2

    return-object v1

    :catchall_2
    move-exception v0

    :goto_6
    if-eqz v3, :cond_9

    invoke-interface {v3}, Landroid/database/Cursor;->close()V

    :cond_9
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_a

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_a
    throw v0

    :catchall_3
    move-exception v0

    :goto_7
    monitor-exit v2
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_3

    throw v0
.end method

.method public b(JJI)V
    .locals 8

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    monitor-enter v0

    :try_start_0
    const-string v1, "DAO"

    const-string v2, "deleteEventsByStartAndEndTS, start:%d, end:%d"

    const/4 v3, 0x2

    new-array v4, v3, [Ljava/lang/Object;

    invoke-static {p1, p2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    const/4 v6, 0x0

    aput-object v5, v4, v6

    invoke-static {p3, p4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v5

    const/4 v7, 0x1

    aput-object v5, v4, v7

    invoke-static {v1, v2, v4}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v1

    const/4 v2, 0x3

    if-ne p5, v7, :cond_1

    const-string p5, "mistat_event"

    const-string v4, "ts<=? AND ts>=? AND anonymous=?"

    new-array v2, v2, [Ljava/lang/String;

    invoke-static {p3, p4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p3

    aput-object p3, v2, v6

    invoke-static {p1, p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v2, v7

    iget-boolean p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->d:Z

    if-eqz p1, :cond_0

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    invoke-static {v6}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    :goto_0
    aput-object p1, v2, v3

    invoke-virtual {v1, p5, v4, v2}, Landroid/database/sqlite/SQLiteDatabase;->delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I

    goto :goto_2

    :cond_1
    if-ne p5, v3, :cond_3

    const-string p5, "mistat_event"

    const-string v4, "ts<=? AND ts>=? AND category IN (?, ?, ?, ?, ?, ?, ?) AND anonymous=?"

    const/16 v5, 0xa

    new-array v5, v5, [Ljava/lang/String;

    invoke-static {p3, p4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p3

    aput-object p3, v5, v6

    invoke-static {p1, p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v5, v7

    const-string p1, "mistat_basic"

    aput-object p1, v5, v3

    const-string p1, "mistat_pt"

    aput-object p1, v5, v2

    const/4 p1, 0x4

    const-string p2, "mistat_pv"

    aput-object p2, v5, p1

    const/4 p1, 0x5

    const-string p2, "mistat_session"

    aput-object p2, v5, p1

    const/4 p1, 0x6

    const-string p2, "mistat_pa"

    aput-object p2, v5, p1

    const/4 p1, 0x7

    const-string p2, "mistat_session_extra"

    aput-object p2, v5, p1

    const/16 p1, 0x8

    const-string p2, "mistat_monitor"

    aput-object p2, v5, p1

    const/16 p1, 0x9

    iget-boolean p2, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->d:Z

    if-eqz p2, :cond_2

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    goto :goto_1

    :cond_2
    invoke-static {v6}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p2

    :goto_1
    aput-object p2, v5, p1

    invoke-virtual {v1, p5, v4, v5}, Landroid/database/sqlite/SQLiteDatabase;->delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :cond_3
    :goto_2
    :try_start_1
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_4

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_3
    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_4

    :catchall_0
    move-exception p1

    goto :goto_5

    :catch_0
    move-exception p1

    :try_start_2
    const-string p2, "DAO"

    const-string p3, "Error while deleting event by ts from DB"

    invoke-static {p2, p3, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_4

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_3

    :cond_4
    :goto_4
    monitor-exit v0

    return-void

    :goto_5
    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p2, :cond_5

    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_5
    throw p1

    :catchall_1
    move-exception p1

    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw p1
.end method

.method public b(Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;)V
    .locals 6

    new-instance v0, Landroid/content/ContentValues;

    invoke-direct {v0}, Landroid/content/ContentValues;-><init>()V

    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mCategory:Ljava/lang/String;

    const-string v2, "category"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mKey:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    const-string v1, ""

    goto :goto_0

    :cond_0
    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mKey:Ljava/lang/String;

    :goto_0
    const-string v2, "key"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-wide v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mTimeStamp:J

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "ts"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mType:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    const-string v1, ""

    goto :goto_1

    :cond_1
    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mType:Ljava/lang/String;

    :goto_1
    const-string v2, "type"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mValue:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_2

    const-string v1, ""

    goto :goto_2

    :cond_2
    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mValue:Ljava/lang/String;

    :goto_2
    const-string v2, "value"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mExtra:Ljava/lang/String;

    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_3

    const-string v1, ""

    goto :goto_3

    :cond_3
    iget-object v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mExtra:Ljava/lang/String;

    :goto_3
    const-string v2, "extra"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    iget v1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mAnonymous:I

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "anonymous"

    invoke-virtual {v0, v2, v1}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Integer;)V

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v1

    monitor-enter v1

    :try_start_0
    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v2

    const-string v3, "mistat_event"

    const-string v4, ""

    invoke-virtual {v2, v3, v4, v0}, Landroid/database/sqlite/SQLiteDatabase;->insert(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :try_start_1
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_4

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_4
    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_5

    :catchall_0
    move-exception p1

    goto :goto_6

    :catch_0
    move-exception v0

    :try_start_2
    const-string v2, "DAO"

    const-string v3, "Error to insert data into DB, key= %s"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    iget-object p1, p1, Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;->mKey:Ljava/lang/String;

    aput-object p1, v4, v5

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    invoke-static {v2, p1, v0}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_4

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_4

    :cond_4
    :goto_5
    monitor-exit v1

    return-void

    :goto_6
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v0, :cond_5

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_5
    throw p1

    :catchall_1
    move-exception p1

    monitor-exit v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw p1
.end method

.method public b(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 6

    new-instance v0, Landroid/content/ContentValues;

    invoke-direct {v0}, Landroid/content/ContentValues;-><init>()V

    const-string v1, "value"

    invoke-virtual {v0, v1, p3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object p3

    monitor-enter p3

    :try_start_0
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v1

    const-string v2, "mistat_event"

    const-string v3, "category=? AND key=?"

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/String;

    const/4 v5, 0x0

    aput-object p2, v4, v5

    const/4 p2, 0x1

    aput-object p1, v4, p2

    invoke-virtual {v1, v2, v0, v3, v4}, Landroid/database/sqlite/SQLiteDatabase;->update(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :try_start_1
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_0

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_0
    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception p2

    :try_start_2
    const-string v0, "DAO"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Error to update data from DB, key="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_0

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_0

    :cond_0
    :goto_1
    monitor-exit p3

    return-void

    :goto_2
    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p2, :cond_1

    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_1
    throw p1

    :catchall_1
    move-exception p1

    monitor-exit p3
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw p1
.end method

.method public c(J)Ljava/util/List;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Ljava/util/List<",
            "Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;",
            ">;"
        }
    .end annotation

    const-string v0, "DAO"

    sget-boolean v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v1, :cond_4

    invoke-direct {p0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->f()V

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    :cond_0
    iget-boolean v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    if-eqz v3, :cond_3

    iget-object v3, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    if-nez v3, :cond_1

    goto :goto_1

    :cond_1
    iget-object v1, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    invoke-interface {v1, p1, p2}, Lcom/xiaomi/mistatistic/sdk/a;->c(J)Ljava/util/List;

    move-result-object p1

    const-string p2, "process getBasicEventsOrderByTsDescend, result size is : %d"

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    if-nez p1, :cond_2

    move v3, v2

    goto :goto_0

    :cond_2
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v3

    :goto_0
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v1, v2

    invoke-static {p2, v1}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->b(Ljava/lang/String;Ljava/lang/String;)V

    return-object p1

    :cond_3
    :goto_1
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    sub-long/2addr v3, v1

    const-wide/16 v5, 0x3e8

    cmp-long v3, v3, v5

    if-lez v3, :cond_0

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p1

    :catch_0
    move-exception p1

    const-string p2, "getBasicEventsOrderByTsDescend exception"

    invoke-static {v0, p2, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    return-object p1

    :cond_4
    invoke-virtual {p0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->d(J)Ljava/util/List;

    move-result-object p1

    return-object p1
.end method

.method public c()Z
    .locals 8

    const-string v0, "DAO"

    sget-boolean v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v1, :cond_3

    invoke-direct {p0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->f()V

    const/4 v1, 0x0

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    :cond_0
    iget-boolean v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    if-eqz v4, :cond_2

    iget-object v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    if-nez v4, :cond_1

    goto :goto_0

    :cond_1
    iget-object v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    invoke-interface {v2}, Lcom/xiaomi/mistatistic/sdk/a;->a()Z

    move-result v2

    const-string v3, "process hasMonitorEvent , result is: %b"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v5

    aput-object v5, v4, v1

    invoke-static {v3, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-static {v0, v3}, Lcom/xiaomi/mistatistic/sdk/controller/j;->b(Ljava/lang/String;Ljava/lang/String;)V

    return v2

    :cond_2
    :goto_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    sub-long/2addr v4, v2

    const-wide/16 v6, 0x3e8

    cmp-long v4, v4, v6

    if-lez v4, :cond_0

    return v1

    :catch_0
    move-exception v2

    const-string v3, "hasMonitorEvent"

    invoke-static {v0, v3, v2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    return v1

    :cond_3
    invoke-virtual {p0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->d()Z

    move-result v0

    return v0
.end method

.method public d(J)Ljava/util/List;
    .locals 11
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Ljava/util/List<",
            "Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;",
            ">;"
        }
    .end annotation

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object p2

    monitor-enter p2

    const/4 v0, 0x0

    :try_start_0
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v2, :cond_0

    :try_start_1
    invoke-interface {v0}, Landroid/database/Cursor;->close()V

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    monitor-exit p2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    return-object p1

    :cond_0
    :try_start_2
    const-string v3, "mistat_event"

    const/4 v4, 0x0

    const-string v5, "category IN (?, ?, ?, ?, ?, ?, ?) AND anonymous=?"

    const/16 v1, 0x8

    new-array v6, v1, [Ljava/lang/String;

    const-string v1, "mistat_basic"

    const/4 v7, 0x0

    aput-object v1, v6, v7

    const-string v1, "mistat_pt"

    const/4 v8, 0x1

    aput-object v1, v6, v8

    const/4 v1, 0x2

    const-string v9, "mistat_pv"

    aput-object v9, v6, v1

    const/4 v1, 0x3

    const-string v9, "mistat_session"

    aput-object v9, v6, v1

    const/4 v1, 0x4

    const-string v9, "mistat_pa"

    aput-object v9, v6, v1

    const/4 v1, 0x5

    const-string v9, "mistat_session_extra"

    aput-object v9, v6, v1

    const/4 v1, 0x6

    const-string v9, "mistat_monitor"

    aput-object v9, v6, v1

    const/4 v1, 0x7

    iget-boolean v9, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->d:Z

    if-eqz v9, :cond_1

    invoke-static {v8}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    goto :goto_0

    :cond_1
    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    :goto_0
    aput-object v7, v6, v1

    const/4 v7, 0x0

    const/4 v8, 0x0

    const-string v9, "ts DESC"

    const/16 v1, 0x1f4

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v10

    invoke-virtual/range {v2 .. v10}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    if-eqz v0, :cond_3

    invoke-interface {v0}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v1

    if-eqz v1, :cond_3

    :cond_2
    invoke-static {v0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Landroid/database/Cursor;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;

    move-result-object v1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-interface {v0}, Landroid/database/Cursor;->moveToNext()Z

    move-result v1
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-nez v1, :cond_2

    :cond_3
    :try_start_3
    invoke-interface {v0}, Landroid/database/Cursor;->close()V

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_1
    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_2

    :catchall_0
    move-exception p1

    goto :goto_3

    :catch_0
    move-exception v1

    :try_start_4
    const-string v2, "DAO"

    const-string v3, "Error while getBasicEventsOrderByTsDescendImpl"

    invoke-static {v2, v3, v1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    :try_start_5
    invoke-interface {v0}, Landroid/database/Cursor;->close()V

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_1

    :goto_2
    monitor-exit p2

    return-object p1

    :goto_3
    invoke-interface {v0}, Landroid/database/Cursor;->close()V

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    throw p1

    :catchall_1
    move-exception p1

    monitor-exit p2
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    throw p1
.end method

.method public d()Z
    .locals 11

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    monitor-enter v0

    const/4 v1, 0x0

    :try_start_0
    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v3

    const-string v4, "mistat_event"

    const/4 v5, 0x0

    const-string v6, "category=?"

    const-string v2, "mistat_monitor"

    filled-new-array {v2}, [Ljava/lang/String;

    move-result-object v7

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    invoke-virtual/range {v3 .. v10}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v1

    if-eqz v1, :cond_2

    invoke-interface {v1}, Landroid/database/Cursor;->moveToFirst()Z

    move-result v2
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v2, :cond_2

    const/4 v2, 0x1

    if-eqz v1, :cond_0

    :try_start_1
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_0
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_1

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_1
    monitor-exit v0

    return v2

    :cond_2
    if-eqz v1, :cond_3

    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_3
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_5

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_0
    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_1

    :catchall_0
    move-exception v2

    goto :goto_2

    :catch_0
    move-exception v2

    :try_start_2
    const-string v3, "DAO"

    const-string v4, "hasMonitorEventImpl exception"

    invoke-static {v3, v4, v2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eqz v1, :cond_4

    :try_start_3
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_4
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_5

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_0

    :cond_5
    :goto_1
    monitor-exit v0

    const/4 v0, 0x0

    return v0

    :goto_2
    if-eqz v1, :cond_6

    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_6
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_7

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_7
    throw v2

    :catchall_1
    move-exception v1

    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw v1
.end method

.method public e(J)Ljava/util/List;
    .locals 13
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(J)",
            "Ljava/util/List<",
            "Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v1

    monitor-enter v1

    const/4 v2, 0x0

    :try_start_0
    sget-object v3, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v3}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v4, :cond_1

    :try_start_1
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_0

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_0
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    return-object v0

    :cond_1
    :try_start_2
    const-string v5, "mistat_event"

    const/4 v6, 0x0

    const-string v7, "ts <= ? AND category = ? OR category = ? OR category = ? OR category = ? OR category = ? "

    const/4 v3, 0x6

    new-array v8, v3, [Ljava/lang/String;

    const/4 v3, 0x0

    invoke-static {p1, p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v8, v3

    const/4 p1, 0x1

    const-string p2, "mistat_basic"

    aput-object p2, v8, p1

    const/4 p1, 0x2

    const-string p2, "mistat_pa"

    aput-object p2, v8, p1

    const/4 p1, 0x3

    const-string p2, "mistat_session"

    aput-object p2, v8, p1

    const/4 p1, 0x4

    const-string p2, "mistat_pv"

    aput-object p2, v8, p1

    const/4 p1, 0x5

    const-string p2, "mistat_pt"

    aput-object p2, v8, p1

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    invoke-virtual/range {v4 .. v12}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v2

    if-eqz v2, :cond_3

    invoke-interface {v2}, Landroid/database/Cursor;->moveToFirst()Z

    move-result p1

    if-eqz p1, :cond_3

    :cond_2
    invoke-static {v2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->a(Landroid/database/Cursor;)Lcom/xiaomi/mistatistic/sdk/data/StatEventPojo;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-interface {v2}, Landroid/database/Cursor;->moveToNext()Z

    move-result p1
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-nez p1, :cond_2

    :cond_3
    if-eqz v2, :cond_4

    :try_start_3
    invoke-interface {v2}, Landroid/database/Cursor;->close()V

    :cond_4
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_6

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_0
    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception p1

    :try_start_4
    const-string p2, "DAO"

    const-string v3, "getExpiredEvents"

    invoke-static {p2, v3, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    if-eqz v2, :cond_5

    :try_start_5
    invoke-interface {v2}, Landroid/database/Cursor;->close()V

    :cond_5
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_6

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_0

    :cond_6
    :goto_1
    monitor-exit v1

    return-object v0

    :goto_2
    if-eqz v2, :cond_7

    invoke-interface {v2}, Landroid/database/Cursor;->close()V

    :cond_7
    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p2, :cond_8

    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_8
    throw p1

    :catchall_1
    move-exception p1

    monitor-exit v1
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    throw p1
.end method

.method public e()Z
    .locals 13

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    monitor-enter v0

    const/4 v1, 0x0

    const/4 v2, 0x0

    :try_start_0
    sget-object v3, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v3}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v4

    const-string v5, "mistat_event"

    const/4 v6, 0x0

    const-string v7, "anonymous=?"

    const/4 v3, 0x1

    new-array v8, v3, [Ljava/lang/String;

    invoke-static {v3}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v9

    aput-object v9, v8, v2

    const/4 v9, 0x0

    const/4 v10, 0x0

    const-string v11, "ts DESC"

    const/16 v12, 0x1f4

    invoke-static {v12}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v12

    invoke-virtual/range {v4 .. v12}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-interface {v1}, Landroid/database/Cursor;->moveToLast()Z

    move-result v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v4, :cond_0

    move v2, v3

    :cond_0
    if-eqz v1, :cond_1

    :try_start_1
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_1
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_3

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_0
    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_1

    :catchall_0
    move-exception v2

    goto :goto_2

    :catch_0
    move-exception v3

    :try_start_2
    const-string v4, "DAO"

    const-string v5, "Error while isExistAnonymousData from DB"

    invoke-static {v4, v5, v3}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eqz v1, :cond_2

    :try_start_3
    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_2
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_3

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_0

    :cond_3
    :goto_1
    monitor-exit v0

    return v2

    :goto_2
    if-eqz v1, :cond_4

    invoke-interface {v1}, Landroid/database/Cursor;->close()V

    :cond_4
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz v1, :cond_5

    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_5
    throw v2

    :catchall_1
    move-exception v1

    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw v1
.end method

.method public f(J)V
    .locals 3

    sget-boolean v0, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v0, :cond_0

    :try_start_0
    new-instance v0, Landroid/content/Intent;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v1

    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/h;->b:Ljava/lang/String;

    invoke-static {v2}, Ljava/lang/Class;->forName(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v1, "type"

    const/4 v2, 0x3

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    const-string v1, "timeStamp"

    invoke-virtual {v0, v1, p1, p2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;J)Landroid/content/Intent;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object p1

    invoke-virtual {p1, v0}, Landroid/content/Context;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    const-string p2, "DAO"

    const-string v0, "deleteExpiredEvents"

    invoke-static {p2, v0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g(J)V

    :goto_0
    return-void
.end method

.method public g(J)V
    .locals 6

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getWritableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v1

    const-string v2, "mistat_event"

    const-string v3, "ts <= ? AND category <> ? "

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/String;

    const/4 v5, 0x0

    invoke-static {p1, p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v4, v5

    const/4 p1, 0x1

    const-string p2, "mistat_monitor"

    aput-object p2, v4, p1

    invoke-virtual {v1, v2, v3, v4}, Landroid/database/sqlite/SQLiteDatabase;->delete(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I

    move-result p1

    if-lez p1, :cond_0

    const-string p2, "quality_monitor"

    const-string v1, "delete_old_events"

    int-to-long v2, p1

    invoke-static {p2, v1, v2, v3}, Lcom/xiaomi/mistatistic/sdk/MiStatInterface;->recordCalculateEvent(Ljava/lang/String;Ljava/lang/String;J)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    :cond_0
    :try_start_1
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_1

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_0
    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception p1

    :try_start_2
    const-string p2, "DAO"

    const-string v1, "Error while deleting out-of-date data from DB"

    invoke-static {p2, v1, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :try_start_3
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_1

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_0

    :cond_1
    :goto_1
    monitor-exit v0

    return-void

    :goto_2
    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p2, :cond_2

    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_2
    throw p1

    :catchall_1
    move-exception p1

    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw p1
.end method

.method public j(J)Z
    .locals 8

    const-string v0, "DAO"

    sget-boolean v1, Lcom/xiaomi/mistatistic/sdk/controller/h;->a:Z

    if-eqz v1, :cond_3

    invoke-direct {p0}, Lcom/xiaomi/mistatistic/sdk/controller/h;->f()V

    const/4 v1, 0x0

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    :cond_0
    iget-boolean v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->f:Z

    if-eqz v4, :cond_2

    iget-object v4, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    if-nez v4, :cond_1

    goto :goto_0

    :cond_1
    iget-object v2, p0, Lcom/xiaomi/mistatistic/sdk/controller/h;->e:Lcom/xiaomi/mistatistic/sdk/a;

    invoke-interface {v2, p1, p2}, Lcom/xiaomi/mistatistic/sdk/a;->b(J)Z

    move-result p1

    const-string p2, "process queryPaEventByTs , result is: %b"

    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Object;

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    aput-object v3, v2, v1

    invoke-static {p2, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-static {v0, p2}, Lcom/xiaomi/mistatistic/sdk/controller/j;->b(Ljava/lang/String;Ljava/lang/String;)V

    return p1

    :cond_2
    :goto_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    sub-long/2addr v4, v2

    const-wide/16 v6, 0x3e8

    cmp-long v4, v4, v6

    if-lez v4, :cond_0

    return v1

    :catch_0
    move-exception p1

    const-string p2, "queryPaEventByTs"

    invoke-static {v0, p2, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V

    return v1

    :cond_3
    invoke-virtual {p0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/h;->k(J)Z

    move-result p1

    return p1
.end method

.method public k(J)Z
    .locals 12

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/h;->g()Lcom/xiaomi/mistatistic/sdk/controller/k;

    move-result-object v0

    monitor-enter v0

    const/4 v1, 0x0

    const/4 v2, 0x0

    :try_start_0
    sget-object v3, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {v3}, Lcom/xiaomi/mistatistic/sdk/controller/k;->getReadableDatabase()Landroid/database/sqlite/SQLiteDatabase;

    move-result-object v4

    const-string v5, "mistat_event"

    const/4 v6, 0x0

    const-string v7, "ts=? AND category=?"

    const/4 v3, 0x2

    new-array v8, v3, [Ljava/lang/String;

    invoke-static {p1, p2}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v8, v1

    const-string p1, "mistat_pa"

    const/4 p2, 0x1

    aput-object p1, v8, p2

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    invoke-virtual/range {v4 .. v11}, Landroid/database/sqlite/SQLiteDatabase;->query(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v2

    if-eqz v2, :cond_2

    invoke-interface {v2}, Landroid/database/Cursor;->moveToFirst()Z

    move-result p1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_2

    if-eqz v2, :cond_0

    :try_start_1
    invoke-interface {v2}, Landroid/database/Cursor;->close()V

    :cond_0
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_1

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_1
    monitor-exit v0

    return p2

    :cond_2
    if-eqz v2, :cond_3

    invoke-interface {v2}, Landroid/database/Cursor;->close()V

    :cond_3
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_5

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    :goto_0
    invoke-virtual {p1}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    :catch_0
    move-exception p1

    :try_start_2
    const-string p2, "DAO"

    const-string v3, "queryPaEventByTsImpl exception"

    invoke-static {p2, v3, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eqz v2, :cond_4

    :try_start_3
    invoke-interface {v2}, Landroid/database/Cursor;->close()V

    :cond_4
    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p1, :cond_5

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    goto :goto_0

    :cond_5
    :goto_1
    monitor-exit v0

    return v1

    :goto_2
    if-eqz v2, :cond_6

    invoke-interface {v2}, Landroid/database/Cursor;->close()V

    :cond_6
    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    if-eqz p2, :cond_7

    sget-object p2, Lcom/xiaomi/mistatistic/sdk/controller/h;->c:Lcom/xiaomi/mistatistic/sdk/controller/k;

    invoke-virtual {p2}, Lcom/xiaomi/mistatistic/sdk/controller/k;->close()V

    :cond_7
    throw p1

    :catchall_1
    move-exception p1

    monitor-exit v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    throw p1
.end method
