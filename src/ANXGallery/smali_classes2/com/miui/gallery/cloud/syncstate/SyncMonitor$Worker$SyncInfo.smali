.class Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;
.super Ljava/lang/Object;
.source "SyncMonitor.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "SyncInfo"
.end annotation


# instance fields
.field final reason:J

.field final synthetic this$0:Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;

.field final type:Lcom/miui/gallery/cloud/base/SyncType;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;Lcom/miui/gallery/cloud/base/SyncType;J)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;->this$0:Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;->type:Lcom/miui/gallery/cloud/base/SyncType;

    iput-wide p3, p0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;->reason:J

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;Lcom/miui/gallery/cloud/base/SyncType;JLcom/miui/gallery/cloud/syncstate/SyncMonitor$1;)V
    .locals 0

    invoke-direct {p0, p1, p2, p3, p4}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker$SyncInfo;-><init>(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Worker;Lcom/miui/gallery/cloud/base/SyncType;J)V

    return-void
.end method