.class public Lcn/kuaipan/android/http/KssTransferStopper$KssTransferStopperFromMiCloudTransferStopper;
.super Ljava/lang/Object;
.source "KssTransferStopper.java"

# interfaces
.implements Lcn/kuaipan/android/http/KssTransferStopper;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcn/kuaipan/android/http/KssTransferStopper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "KssTransferStopperFromMiCloudTransferStopper"
.end annotation


# instance fields
.field private final mMiCloudTransferStopper:Lcom/xiaomi/opensdk/file/model/MiCloudTransferStopper;


# direct methods
.method private constructor <init>(Lcom/xiaomi/opensdk/file/model/MiCloudTransferStopper;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcn/kuaipan/android/http/KssTransferStopper$KssTransferStopperFromMiCloudTransferStopper;->mMiCloudTransferStopper:Lcom/xiaomi/opensdk/file/model/MiCloudTransferStopper;

    return-void
.end method

.method public static get(Lcom/xiaomi/opensdk/file/model/MiCloudTransferStopper;)Lcn/kuaipan/android/http/KssTransferStopper;
    .locals 1

    if-nez p0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    new-instance v0, Lcn/kuaipan/android/http/KssTransferStopper$KssTransferStopperFromMiCloudTransferStopper;

    invoke-direct {v0, p0}, Lcn/kuaipan/android/http/KssTransferStopper$KssTransferStopperFromMiCloudTransferStopper;-><init>(Lcom/xiaomi/opensdk/file/model/MiCloudTransferStopper;)V

    return-object v0
.end method


# virtual methods
.method public checkStop()Z
    .locals 1

    iget-object v0, p0, Lcn/kuaipan/android/http/KssTransferStopper$KssTransferStopperFromMiCloudTransferStopper;->mMiCloudTransferStopper:Lcom/xiaomi/opensdk/file/model/MiCloudTransferStopper;

    invoke-interface {v0}, Lcom/xiaomi/opensdk/file/model/MiCloudTransferStopper;->checkStop()Z

    move-result v0

    return v0
.end method
