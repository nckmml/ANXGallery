.class public abstract Lcom/xiaomi/channel/commonutils/logger/MyLog;
.super Ljava/lang/Object;
.source "MyLog.java"


# static fields
.field private static LOG_LEVEL:I = 0x2

.field private static final NEGATIVE_CODE:Ljava/lang/Integer;

.field private static logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

.field private static final mActionNames:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static mCodeGenerator:Ljava/util/concurrent/atomic/AtomicInteger;

.field private static final mStartTimes:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Integer;",
            "Ljava/lang/Long;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/xiaomi/channel/commonutils/logger/DefaultAndroidLogger;

    invoke-direct {v0}, Lcom/xiaomi/channel/commonutils/logger/DefaultAndroidLogger;-><init>()V

    sput-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mStartTimes:Ljava/util/HashMap;

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mActionNames:Ljava/util/HashMap;

    const/4 v0, -0x1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    sput-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->NEGATIVE_CODE:Ljava/lang/Integer;

    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    sput-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mCodeGenerator:Ljava/util/concurrent/atomic/AtomicInteger;

    return-void
.end method

.method public static e(Ljava/lang/String;)V
    .locals 1

    const/4 v0, 0x4

    invoke-static {v0, p0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->log(ILjava/lang/String;)V

    return-void
.end method

.method public static e(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 1

    const/4 v0, 0x4

    invoke-static {v0, p0, p1}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->log(ILjava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public static e(Ljava/lang/Throwable;)V
    .locals 1

    const/4 v0, 0x4

    invoke-static {v0, p0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->log(ILjava/lang/Throwable;)V

    return-void
.end method

.method public static getLogLevel()I
    .locals 1

    sget v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->LOG_LEVEL:I

    return v0
.end method

.method public static i(Ljava/lang/String;)V
    .locals 1

    const/4 v0, 0x0

    invoke-static {v0, p0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->log(ILjava/lang/String;)V

    return-void
.end method

.method public static log(ILjava/lang/String;)V
    .locals 1

    sget v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->LOG_LEVEL:I

    if-lt p0, v0, :cond_0

    sget-object p0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

    invoke-interface {p0, p1}, Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;->log(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method public static log(ILjava/lang/String;Ljava/lang/Throwable;)V
    .locals 1

    sget v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->LOG_LEVEL:I

    if-lt p0, v0, :cond_0

    sget-object p0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

    invoke-interface {p0, p1, p2}, Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_0
    return-void
.end method

.method public static log(ILjava/lang/Throwable;)V
    .locals 1

    sget v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->LOG_LEVEL:I

    if-lt p0, v0, :cond_0

    sget-object p0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

    const-string v0, ""

    invoke-interface {p0, v0, p1}, Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_0
    return-void
.end method

.method public static pe(Ljava/lang/Integer;)V
    .locals 4

    sget v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->LOG_LEVEL:I

    const/4 v1, 0x1

    if-gt v0, v1, :cond_1

    sget-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mStartTimes:Ljava/util/HashMap;

    invoke-virtual {v0, p0}, Ljava/util/HashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    sget-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mStartTimes:Ljava/util/HashMap;

    invoke-virtual {v0, p0}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Long;

    invoke-virtual {v0}, Ljava/lang/Long;->longValue()J

    move-result-wide v0

    sget-object v2, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mActionNames:Ljava/util/HashMap;

    invoke-virtual {v2, p0}, Ljava/util/HashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    sub-long/2addr v2, v0

    sget-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " ends in "

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, " ms"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-interface {v0, p0}, Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;->log(Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method public static ps(Ljava/lang/String;)Ljava/lang/Integer;
    .locals 4

    sget v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->LOG_LEVEL:I

    const/4 v1, 0x1

    if-gt v0, v1, :cond_0

    sget-object v0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mCodeGenerator:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    sget-object v1, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mStartTimes:Ljava/util/HashMap;

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    invoke-virtual {v1, v0, v2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v1, Lcom/xiaomi/channel/commonutils/logger/MyLog;->mActionNames:Ljava/util/HashMap;

    invoke-virtual {v1, v0, p0}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    sget-object v1, Lcom/xiaomi/channel/commonutils/logger/MyLog;->logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " starts"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-interface {v1, p0}, Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;->log(Ljava/lang/String;)V

    return-object v0

    :cond_0
    sget-object p0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->NEGATIVE_CODE:Ljava/lang/Integer;

    return-object p0
.end method

.method public static setLogLevel(I)V
    .locals 3

    if-ltz p0, :cond_0

    const/4 v0, 0x5

    if-le p0, v0, :cond_1

    :cond_0
    const/4 v0, 0x2

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "set log level as "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->log(ILjava/lang/String;)V

    :cond_1
    sput p0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->LOG_LEVEL:I

    return-void
.end method

.method public static setLogger(Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;)V
    .locals 0

    sput-object p0, Lcom/xiaomi/channel/commonutils/logger/MyLog;->logger:Lcom/xiaomi/channel/commonutils/logger/LoggerInterface;

    return-void
.end method

.method public static v(Ljava/lang/String;)V
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "[Thread:"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Thread;->getId()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, "] "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x1

    invoke-static {v0, p0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->log(ILjava/lang/String;)V

    return-void
.end method

.method public static w(Ljava/lang/String;)V
    .locals 3

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "[Thread:"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Thread;->getId()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v1, "] "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const/4 v0, 0x2

    invoke-static {v0, p0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->log(ILjava/lang/String;)V

    return-void
.end method
