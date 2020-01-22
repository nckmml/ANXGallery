.class public Lcn/kuaipan/android/http/ProcessMonitorOutputStream;
.super Ljava/io/FilterOutputStream;
.source "ProcessMonitorOutputStream.java"


# instance fields
.field private final mListener:Lcn/kuaipan/android/http/IKscTransferListener;

.field private final mMonitor:Lcn/kuaipan/android/http/KscSpeedMonitor;

.field private final mSendMode:Z

.field private final mStopper:Lcn/kuaipan/android/http/KssTransferStopper;


# direct methods
.method public constructor <init>(Ljava/io/OutputStream;Lcn/kuaipan/android/http/KscSpeedMonitor;Lcn/kuaipan/android/http/IKscTransferListener;Lcn/kuaipan/android/http/KssTransferStopper;Z)V
    .locals 0

    invoke-direct {p0, p1}, Ljava/io/FilterOutputStream;-><init>(Ljava/io/OutputStream;)V

    iput-object p2, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mMonitor:Lcn/kuaipan/android/http/KscSpeedMonitor;

    iput-object p3, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mListener:Lcn/kuaipan/android/http/IKscTransferListener;

    iput-object p4, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mStopper:Lcn/kuaipan/android/http/KssTransferStopper;

    iput-boolean p5, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mSendMode:Z

    return-void
.end method

.method private process(J)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    iget-object v0, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mStopper:Lcn/kuaipan/android/http/KssTransferStopper;

    if-eqz v0, :cond_1

    invoke-interface {v0}, Lcn/kuaipan/android/http/KssTransferStopper;->checkStop()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    new-instance p1, Ljava/io/IOException;

    new-instance p2, Lcn/kuaipan/android/exception/KscTransferStopByCallerException;

    invoke-direct {p2}, Lcn/kuaipan/android/exception/KscTransferStopByCallerException;-><init>()V

    const-string v0, "@ ProcessMonitorOutputStream::process()"

    invoke-direct {p1, v0, p2}, Ljava/io/IOException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw p1

    :cond_1
    :goto_0
    const-wide/16 v0, 0x0

    cmp-long v0, p1, v0

    if-ltz v0, :cond_4

    iget-object v0, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mMonitor:Lcn/kuaipan/android/http/KscSpeedMonitor;

    if-eqz v0, :cond_2

    invoke-virtual {v0, p1, p2}, Lcn/kuaipan/android/http/KscSpeedMonitor;->recode(J)V

    :cond_2
    iget-object v0, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mListener:Lcn/kuaipan/android/http/IKscTransferListener;

    if-eqz v0, :cond_4

    iget-boolean v1, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->mSendMode:Z

    if-eqz v1, :cond_3

    invoke-interface {v0, p1, p2}, Lcn/kuaipan/android/http/IKscTransferListener;->sended(J)V

    goto :goto_1

    :cond_3
    invoke-interface {v0, p1, p2}, Lcn/kuaipan/android/http/IKscTransferListener;->received(J)V

    :cond_4
    :goto_1
    return-void
.end method


# virtual methods
.method public write(I)V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    invoke-super {p0, p1}, Ljava/io/FilterOutputStream;->write(I)V

    const-wide/16 v0, 0x1

    invoke-direct {p0, v0, v1}, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->process(J)V

    return-void
.end method

.method public write([BII)V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    iget-object v0, p0, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->out:Ljava/io/OutputStream;

    invoke-virtual {v0, p1, p2, p3}, Ljava/io/OutputStream;->write([BII)V

    int-to-long p1, p3

    invoke-direct {p0, p1, p2}, Lcn/kuaipan/android/http/ProcessMonitorOutputStream;->process(J)V

    return-void
.end method
