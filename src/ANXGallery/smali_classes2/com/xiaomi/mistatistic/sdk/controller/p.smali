.class public Lcom/xiaomi/mistatistic/sdk/controller/p;
.super Ljava/lang/Object;
.source "SessionManager.java"


# static fields
.field private static final a:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/xiaomi/mistatistic/sdk/data/i;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile b:Lcom/xiaomi/mistatistic/sdk/controller/p;

.field private static c:J


# instance fields
.field private d:Landroid/os/Handler;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    sput-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    const/4 v0, 0x0

    sput-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->b:Lcom/xiaomi/mistatistic/sdk/controller/p;

    const-wide/16 v0, 0x7530

    sput-wide v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->c:J

    return-void
.end method

.method private constructor <init>()V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/xiaomi/mistatistic/sdk/controller/p$1;

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/e;->c()Landroid/os/Looper;

    move-result-object v1

    invoke-direct {v0, p0, v1}, Lcom/xiaomi/mistatistic/sdk/controller/p$1;-><init>(Lcom/xiaomi/mistatistic/sdk/controller/p;Landroid/os/Looper;)V

    iput-object v0, p0, Lcom/xiaomi/mistatistic/sdk/controller/p;->d:Landroid/os/Handler;

    return-void
.end method

.method public static a()Lcom/xiaomi/mistatistic/sdk/controller/p;
    .locals 2

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->b:Lcom/xiaomi/mistatistic/sdk/controller/p;

    if-nez v0, :cond_1

    const-class v0, Lcom/xiaomi/mistatistic/sdk/controller/p;

    monitor-enter v0

    :try_start_0
    sget-object v1, Lcom/xiaomi/mistatistic/sdk/controller/p;->b:Lcom/xiaomi/mistatistic/sdk/controller/p;

    if-nez v1, :cond_0

    new-instance v1, Lcom/xiaomi/mistatistic/sdk/controller/p;

    invoke-direct {v1}, Lcom/xiaomi/mistatistic/sdk/controller/p;-><init>()V

    sput-object v1, Lcom/xiaomi/mistatistic/sdk/controller/p;->b:Lcom/xiaomi/mistatistic/sdk/controller/p;

    :cond_0
    monitor-exit v0

    goto :goto_0

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1

    :cond_1
    :goto_0
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->b:Lcom/xiaomi/mistatistic/sdk/controller/p;

    return-object v0
.end method

.method private a(Landroid/content/Context;J)V
    .locals 2

    new-instance p1, Lcom/xiaomi/mistatistic/sdk/data/m;

    const-wide/16 v0, 0x0

    invoke-direct {p1, p2, p3, v0, v1}, Lcom/xiaomi/mistatistic/sdk/data/m;-><init>(JJ)V

    invoke-static {p1}, Lcom/xiaomi/mistatistic/sdk/controller/LocalEventRecorder;->insertEvent(Lcom/xiaomi/mistatistic/sdk/data/AbstractEvent;)V

    return-void
.end method

.method private a(Landroid/content/Context;JJ)V
    .locals 7

    invoke-static {p1}, Lcom/xiaomi/mistatistic/sdk/controller/l;->c(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_0

    const-string v0, "NULL"

    :cond_0
    move-object v6, v0

    new-instance v0, Lcom/xiaomi/mistatistic/sdk/data/l;

    move-object v1, v0

    move-wide v2, p2

    move-wide v4, p4

    invoke-direct/range {v1 .. v6}, Lcom/xiaomi/mistatistic/sdk/data/l;-><init>(JJLjava/lang/String;)V

    invoke-static {v0}, Lcom/xiaomi/mistatistic/sdk/controller/LocalEventRecorder;->insertEvent(Lcom/xiaomi/mistatistic/sdk/data/AbstractEvent;)V

    const-wide/16 p2, 0x0

    const-string p4, "session_begin"

    invoke-static {p1, p4, p2, p3}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;J)V

    const-string p4, "last_deactivate"

    invoke-static {p1, p4, p2, p3}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;J)V

    return-void
.end method

.method private a(Landroid/content/Context;Ljava/lang/String;J)V
    .locals 4

    invoke-static {p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "source_path"

    const-string v1, ""

    invoke-static {p1, v0, v1}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Lcom/xiaomi/mistatistic/sdk/data/j;

    invoke-direct {v3, p2, v2, p3, p4}, Lcom/xiaomi/mistatistic/sdk/data/j;-><init>(Ljava/lang/String;Ljava/lang/String;J)V

    invoke-static {v3}, Lcom/xiaomi/mistatistic/sdk/controller/LocalEventRecorder;->insertEvent(Lcom/xiaomi/mistatistic/sdk/data/AbstractEvent;)V

    invoke-static {p1, v0, v1}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    const-string p2, "pv_path"

    invoke-static {p1, p2, v1}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method static synthetic a(Lcom/xiaomi/mistatistic/sdk/controller/p;Landroid/content/Context;JJ)V
    .locals 0

    invoke-direct/range {p0 .. p5}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;JJ)V

    return-void
.end method

.method static synthetic a(Lcom/xiaomi/mistatistic/sdk/controller/p;Landroid/content/Context;Ljava/lang/String;J)V
    .locals 0

    invoke-direct {p0, p1, p2, p3, p4}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;Ljava/lang/String;J)V

    return-void
.end method

.method static synthetic a(Lcom/xiaomi/mistatistic/sdk/controller/p;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/p;->b(Ljava/lang/String;)V

    return-void
.end method

.method static synthetic a(Lcom/xiaomi/mistatistic/sdk/controller/p;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/p;->b(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private b(Ljava/lang/String;)V
    .locals 7

    :try_start_0
    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    const-string v2, "last_deactivate"

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v3

    invoke-static {v0, v2, v3, v4}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;J)V

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_3

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p1, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result p1

    add-int/lit8 p1, p1, -0x1

    goto :goto_1

    :cond_0
    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    :goto_0
    if-ltz v0, :cond_2

    sget-object v2, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    invoke-interface {v2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/xiaomi/mistatistic/sdk/data/i;

    invoke-virtual {v2}, Lcom/xiaomi/mistatistic/sdk/data/i;->b()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2, p1}, Landroid/text/TextUtils;->equals(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_1

    move p1, v0

    goto :goto_1

    :cond_1
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    :cond_2
    const/4 p1, -0x1

    :goto_1
    if-ltz p1, :cond_3

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/xiaomi/mistatistic/sdk/data/i;

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/data/i;->b()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/data/i;->a()J

    move-result-wide v3

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v5

    sub-long/2addr v5, v3

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_3

    const-wide/16 v1, 0x0

    cmp-long v3, v3, v1

    if-lez v3, :cond_3

    cmp-long v1, v5, v1

    if-lez v1, :cond_3

    invoke-static {v5, v6}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/xiaomi/mistatistic/sdk/data/i;->a(Ljava/lang/Long;)V

    invoke-static {v0}, Lcom/xiaomi/mistatistic/sdk/controller/LocalEventRecorder;->insertEvent(Lcom/xiaomi/mistatistic/sdk/data/AbstractEvent;)V

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->remove(I)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_2

    :catch_0
    move-exception p1

    const-string v0, "processActDeativated exception: "

    invoke-static {v0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_3
    :goto_2
    return-void
.end method

.method private b(Ljava/lang/String;Ljava/lang/String;)V
    .locals 20

    move-object/from16 v7, p0

    move-object/from16 v0, p1

    const-string v8, "source_path"

    const-string v9, "pv_path"

    const-string v10, ""

    const-string v11, "session_begin"

    :try_start_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v12

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v14

    const-wide/16 v1, 0x0

    invoke-static {v14, v11, v1, v2}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;J)J

    move-result-wide v5

    const-string v3, "last_deactivate"

    invoke-static {v14, v3, v1, v2}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;J)J

    move-result-wide v15

    invoke-static {v14, v9, v10}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    cmp-long v4, v5, v1

    if-gtz v4, :cond_0

    invoke-static {v14, v11, v12, v13}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;J)V

    invoke-direct {v7, v14, v12, v13}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;J)V

    goto :goto_0

    :cond_0
    cmp-long v4, v15, v1

    if-gtz v4, :cond_1

    invoke-static {v14, v11, v12, v13}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;J)V

    invoke-direct {v7, v14, v12, v13}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;J)V

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v4

    if-nez v4, :cond_1

    invoke-direct {v7, v14, v3, v5, v6}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;Ljava/lang/String;J)V

    move-object v3, v10

    :cond_1
    :goto_0
    cmp-long v1, v15, v1

    if-lez v1, :cond_3

    sub-long v1, v12, v15

    sget-wide v17, Lcom/xiaomi/mistatistic/sdk/controller/p;->c:J

    cmp-long v1, v1, v17

    if-lez v1, :cond_3

    move-object/from16 v1, p0

    move-object v2, v14

    move-object/from16 v17, v3

    move-wide v3, v5

    move-object/from16 v18, v8

    move-object/from16 v19, v9

    move-wide v8, v5

    move-wide v5, v15

    invoke-direct/range {v1 .. v6}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;JJ)V

    invoke-static/range {v17 .. v17}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_2

    move-object/from16 v3, v17

    invoke-direct {v7, v14, v3, v8, v9}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;Ljava/lang/String;J)V

    move-object v3, v10

    goto :goto_1

    :cond_2
    move-object/from16 v3, v17

    :goto_1
    invoke-static {v14, v11, v12, v13}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;J)V

    invoke-direct {v7, v14, v12, v13}, Lcom/xiaomi/mistatistic/sdk/controller/p;->a(Landroid/content/Context;J)V

    goto :goto_2

    :cond_3
    move-object/from16 v18, v8

    move-object/from16 v19, v9

    :goto_2
    invoke-virtual {v3, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-static/range {p2 .. p2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_5

    :cond_4
    invoke-direct {v7, v3, v0}, Lcom/xiaomi/mistatistic/sdk/controller/p;->c(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    move-object/from16 v2, v19

    invoke-static {v14, v2, v1}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    move-object/from16 v1, v18

    invoke-static {v14, v1, v10}, Lcom/xiaomi/mistatistic/sdk/controller/m;->a(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    move-object/from16 v3, p2

    invoke-direct {v7, v2, v3}, Lcom/xiaomi/mistatistic/sdk/controller/p;->c(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v14, v1, v2}, Lcom/xiaomi/mistatistic/sdk/controller/m;->b(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V

    :cond_5
    new-instance v1, Lcom/xiaomi/mistatistic/sdk/data/i;

    invoke-static {v12, v13}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-direct {v1, v0, v2}, Lcom/xiaomi/mistatistic/sdk/data/i;-><init>(Ljava/lang/String;Ljava/lang/Long;)V

    sget-object v0, Lcom/xiaomi/mistatistic/sdk/controller/p;->a:Ljava/util/List;

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_3

    :catch_0
    move-exception v0

    const-string v1, "processActActivated exception: "

    invoke-static {v1, v0}, Lcom/xiaomi/mistatistic/sdk/controller/j;->a(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_3
    return-void
.end method

.method private c(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, p1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const-string p1, ","

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    :goto_0
    return-object p2
.end method


# virtual methods
.method public a(Ljava/lang/String;)V
    .locals 3

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/mistatistic/sdk/controller/f;->a(Landroid/content/Context;)Lcom/xiaomi/mistatistic/sdk/controller/f;

    move-result-object v0

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/f;->b()Z

    move-result v0

    if-nez v0, :cond_0

    const-string p1, "Session manager V1 is disabled."

    invoke-static {p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->c(Ljava/lang/String;)V

    return-void

    :cond_0
    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/e;->a()Lcom/xiaomi/mistatistic/sdk/controller/e;

    move-result-object v0

    new-instance v1, Lcom/xiaomi/mistatistic/sdk/controller/p$3;

    invoke-direct {v1, p0, p1}, Lcom/xiaomi/mistatistic/sdk/controller/p$3;-><init>(Lcom/xiaomi/mistatistic/sdk/controller/p;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Lcom/xiaomi/mistatistic/sdk/controller/e;->a(Lcom/xiaomi/mistatistic/sdk/controller/e$a;)V

    iget-object p1, p0, Lcom/xiaomi/mistatistic/sdk/controller/p;->d:Landroid/os/Handler;

    const v0, 0x1df5e77

    sget-wide v1, Lcom/xiaomi/mistatistic/sdk/controller/p;->c:J

    invoke-virtual {p1, v0, v1, v2}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    return-void
.end method

.method public a(Ljava/lang/String;Ljava/lang/String;)V
    .locals 2

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/d;->a()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/xiaomi/mistatistic/sdk/controller/f;->a(Landroid/content/Context;)Lcom/xiaomi/mistatistic/sdk/controller/f;

    move-result-object v0

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/controller/f;->b()Z

    move-result v0

    if-nez v0, :cond_0

    const-string p1, "Session manager V1 is disabled."

    invoke-static {p1}, Lcom/xiaomi/mistatistic/sdk/controller/j;->c(Ljava/lang/String;)V

    return-void

    :cond_0
    iget-object v0, p0, Lcom/xiaomi/mistatistic/sdk/controller/p;->d:Landroid/os/Handler;

    const v1, 0x1df5e77

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeMessages(I)V

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/controller/e;->a()Lcom/xiaomi/mistatistic/sdk/controller/e;

    move-result-object v0

    new-instance v1, Lcom/xiaomi/mistatistic/sdk/controller/p$2;

    invoke-direct {v1, p0, p1, p2}, Lcom/xiaomi/mistatistic/sdk/controller/p$2;-><init>(Lcom/xiaomi/mistatistic/sdk/controller/p;Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {v0, v1}, Lcom/xiaomi/mistatistic/sdk/controller/e;->a(Lcom/xiaomi/mistatistic/sdk/controller/e$a;)V

    return-void
.end method
