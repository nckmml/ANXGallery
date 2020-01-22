.class public interface abstract Lcom/miui/gallery/cloud/base/SyncConstants$Sync;
.super Ljava/lang/Object;
.source "SyncConstants.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cloud/base/SyncConstants;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "Sync"
.end annotation


# static fields
.field public static final EXTRA_SYNC_FORCE:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    sget-object v0, Lcom/xiaomi/micloudsdk/utils/MiCloudConstants$SYNC;->SYNC_EXTRAS_FORCE:Ljava/lang/String;

    sput-object v0, Lcom/miui/gallery/cloud/base/SyncConstants$Sync;->EXTRA_SYNC_FORCE:Ljava/lang/String;

    return-void
.end method
