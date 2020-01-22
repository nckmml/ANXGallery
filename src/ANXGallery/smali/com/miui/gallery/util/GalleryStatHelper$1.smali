.class final Lcom/miui/gallery/util/GalleryStatHelper$1;
.super Ljava/lang/Object;
.source "GalleryStatHelper.java"

# interfaces
.implements Lcom/xiaomi/micloudsdk/stat/IMiCloudStatCallback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/util/GalleryStatHelper;->init(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/util/GalleryStatHelper$1;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onAddHttpEvent(Ljava/lang/String;JJILjava/lang/String;)V
    .locals 9

    new-instance v8, Lcom/xiaomi/mistatistic/sdk/data/HttpEvent;

    move-object v0, v8

    move-object v1, p1

    move-wide v2, p2

    move-wide v4, p4

    move v6, p6

    move-object/from16 v7, p7

    invoke-direct/range {v0 .. v7}, Lcom/xiaomi/mistatistic/sdk/data/HttpEvent;-><init>(Ljava/lang/String;JJILjava/lang/String;)V

    invoke-static {v8}, Lcom/xiaomi/mistatistic/sdk/URLStatsRecorder;->addHttpEvent(Lcom/xiaomi/mistatistic/sdk/data/HttpEvent;)V

    return-void
.end method

.method public onEnableAutoRecord()V
    .locals 0

    invoke-static {}, Lcom/xiaomi/mistatistic/sdk/URLStatsRecorder;->enableAutoRecord()Z

    return-void
.end method

.method public onInitialize()V
    .locals 4

    :try_start_0
    iget-object v0, p0, Lcom/miui/gallery/util/GalleryStatHelper$1;->val$context:Landroid/content/Context;

    const-string v1, "2882303761517492012"

    const-string v2, "5601749292012"

    const-string v3, "com.miui.gallery"

    invoke-static {v0, v1, v2, v3}, Lcom/xiaomi/mistatistic/sdk/MiStatInterface;->initialize(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    :try_end_0
    .catch Lcom/xiaomi/mistatistic/sdk/MiStatException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    invoke-virtual {v0}, Lcom/xiaomi/mistatistic/sdk/MiStatException;->printStackTrace()V

    :goto_0
    return-void
.end method

.method public onSetEventFilter()V
    .locals 0

    return-void
.end method

.method public onSetUploadPolicy()V
    .locals 3

    const/4 v0, 0x0

    const-wide/32 v1, 0x493e0

    invoke-static {v0, v1, v2}, Lcom/xiaomi/mistatistic/sdk/MiStatInterface;->setUploadPolicy(IJ)V

    return-void
.end method
