.class Lcom/xiaomi/push/service/timers/AlarmManagerTimer;
.super Ljava/lang/Object;
.source "AlarmManagerTimer.java"

# interfaces
.implements Lcom/xiaomi/push/service/timers/Alarm$IAlarm;


# instance fields
.field protected mContext:Landroid/content/Context;

.field private volatile mNextPingTs:J

.field private mPi:Landroid/app/PendingIntent;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    iput-object v0, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mContext:Landroid/content/Context;

    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    iput-object p1, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mContext:Landroid/content/Context;

    return-void
.end method

.method private setExact(Landroid/app/AlarmManager;JLandroid/app/PendingIntent;)V
    .locals 8

    const-class v0, Landroid/app/AlarmManager;

    :try_start_0
    const-string v1, "setExact"

    const/4 v2, 0x3

    new-array v3, v2, [Ljava/lang/Class;

    sget-object v4, Ljava/lang/Integer;->TYPE:Ljava/lang/Class;

    const/4 v5, 0x0

    aput-object v4, v3, v5

    sget-object v4, Ljava/lang/Long;->TYPE:Ljava/lang/Class;

    const/4 v6, 0x1

    aput-object v4, v3, v6

    const-class v4, Landroid/app/PendingIntent;

    const/4 v7, 0x2

    aput-object v4, v3, v7

    invoke-virtual {v0, v1, v3}, Ljava/lang/Class;->getMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;

    move-result-object v0

    new-array v1, v2, [Ljava/lang/Object;

    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v1, v5

    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p2

    aput-object p2, v1, v6

    aput-object p4, v1, v7

    invoke-virtual {v0, p1, v1}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    invoke-static {p1}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->e(Ljava/lang/Throwable;)V

    :goto_0
    return-void
.end method


# virtual methods
.method getPingInteval()J
    .locals 2

    invoke-static {}, Lcom/xiaomi/smack/SmackConfiguration;->getPingInteval()I

    move-result v0

    int-to-long v0, v0

    return-wide v0
.end method

.method public isAlive()Z
    .locals 4

    iget-wide v0, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public register(Landroid/content/Intent;J)V
    .locals 3

    iget-object v0, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mContext:Landroid/content/Context;

    const-string v1, "alarm"

    invoke-virtual {v0, v1}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/AlarmManager;

    iget-object v1, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mContext:Landroid/content/Context;

    const/4 v2, 0x0

    invoke-static {v1, v2, p1, v2}, Landroid/app/PendingIntent;->getBroadcast(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;

    move-result-object p1

    iput-object p1, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    sget p1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt p1, v1, :cond_0

    const/4 p1, 0x3

    new-array p1, p1, [Ljava/lang/Object;

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    aput-object v1, p1, v2

    const/4 v1, 0x1

    invoke-static {p2, p3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v2

    aput-object v2, p1, v1

    const/4 v1, 0x2

    iget-object v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    aput-object v2, p1, v1

    const-string v1, "setExactAndAllowWhileIdle"

    invoke-static {v0, v1, p1}, Lcom/xiaomi/channel/commonutils/reflect/JavaCalls;->callMethod(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    :cond_0
    sget p1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x13

    if-lt p1, v1, :cond_1

    iget-object p1, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    invoke-direct {p0, v0, p2, p3, p1}, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->setExact(Landroid/app/AlarmManager;JLandroid/app/PendingIntent;)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    invoke-virtual {v0, v2, p2, p3, p1}, Landroid/app/AlarmManager;->set(IJLandroid/app/PendingIntent;)V

    :goto_0
    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "register timer "

    invoke-virtual {p1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    return-void
.end method

.method public registerPing(Z)V
    .locals 6

    invoke-virtual {p0}, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->getPingInteval()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    if-nez p1, :cond_0

    iget-wide v4, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    cmp-long v4, v4, v2

    if-nez v4, :cond_0

    return-void

    :cond_0
    if-eqz p1, :cond_1

    invoke-virtual {p0}, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->stop()V

    :cond_1
    if-nez p1, :cond_3

    iget-wide v4, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    cmp-long p1, v4, v2

    if-nez p1, :cond_2

    goto :goto_0

    :cond_2
    iget-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    add-long/2addr v2, v0

    iput-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    iget-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    cmp-long p1, v2, v4

    if-gez p1, :cond_4

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    add-long/2addr v2, v0

    iput-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    goto :goto_1

    :cond_3
    :goto_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtime()J

    move-result-wide v2

    rem-long/2addr v2, v0

    sub-long/2addr v0, v2

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v2

    add-long/2addr v2, v0

    iput-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    :cond_4
    :goto_1
    new-instance p1, Landroid/content/Intent;

    sget-object v0, Lcom/xiaomi/push/service/PushConstants;->ACTION_PING_TIMER:Ljava/lang/String;

    invoke-direct {p1, v0}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mContext:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, v0}, Landroid/content/Intent;->setPackage(Ljava/lang/String;)Landroid/content/Intent;

    iget-wide v0, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    invoke-virtual {p0, p1, v0, v1}, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->register(Landroid/content/Intent;J)V

    return-void
.end method

.method public stop()V
    .locals 6

    const-string v0, "unregister timer"

    iget-object v1, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    const-wide/16 v2, 0x0

    if-eqz v1, :cond_0

    iget-object v1, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mContext:Landroid/content/Context;

    const-string v4, "alarm"

    invoke-virtual {v1, v4}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/app/AlarmManager;

    const/4 v4, 0x0

    :try_start_0
    iget-object v5, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    invoke-virtual {v1, v5}, Landroid/app/AlarmManager;->cancel(Landroid/app/PendingIntent;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v1

    iput-object v4, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    iput-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    throw v1

    :catch_0
    :goto_0
    iput-object v4, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mPi:Landroid/app/PendingIntent;

    invoke-static {v0}, Lcom/xiaomi/channel/commonutils/logger/MyLog;->v(Ljava/lang/String;)V

    iput-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    :cond_0
    iput-wide v2, p0, Lcom/xiaomi/push/service/timers/AlarmManagerTimer;->mNextPingTs:J

    return-void
.end method
