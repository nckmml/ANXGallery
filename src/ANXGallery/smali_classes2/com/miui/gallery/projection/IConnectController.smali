.class public interface abstract Lcom/miui/gallery/projection/IConnectController;
.super Ljava/lang/Object;
.source "IConnectController.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/projection/IConnectController$DataSet;,
        Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;,
        Lcom/miui/gallery/projection/IConnectController$OnMediaPlayListener;
    }
.end annotation


# virtual methods
.method public abstract closeService()V
.end method

.method public abstract getConnectStatus()I
.end method

.method public abstract getConnectedDevice()Ljava/lang/String;
.end method

.method public abstract getConnectingDevice()Ljava/lang/String;
.end method

.method public abstract isConnected()Z
.end method

.method public abstract project()V
.end method

.method public abstract registerStatusListener(Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;)V
.end method

.method public abstract showSlide(I)Z
.end method

.method public abstract syncRemoteView(FFFFFFF)V
.end method

.method public abstract unregisterStatusListener(Lcom/miui/gallery/projection/IConnectController$OnStatusisListener;)V
.end method

.method public abstract updateCurrentFolder(Lcom/miui/gallery/projection/IConnectController$DataSet;)V
.end method

.method public abstract updateCurrentPhoto(Ljava/lang/String;I)V
.end method
