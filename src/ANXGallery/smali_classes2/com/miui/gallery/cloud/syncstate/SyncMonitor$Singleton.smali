.class final Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Singleton;
.super Ljava/lang/Object;
.source "SyncMonitor.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cloud/syncstate/SyncMonitor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1a
    name = "Singleton"
.end annotation


# static fields
.field private static final INSTANCE:Lcom/miui/gallery/cloud/syncstate/SyncMonitor;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/gallery/cloud/syncstate/SyncMonitor;-><init>(Lcom/miui/gallery/cloud/syncstate/SyncMonitor$1;)V

    sput-object v0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Singleton;->INSTANCE:Lcom/miui/gallery/cloud/syncstate/SyncMonitor;

    return-void
.end method

.method static synthetic access$200()Lcom/miui/gallery/cloud/syncstate/SyncMonitor;
    .locals 1

    sget-object v0, Lcom/miui/gallery/cloud/syncstate/SyncMonitor$Singleton;->INSTANCE:Lcom/miui/gallery/cloud/syncstate/SyncMonitor;

    return-object v0
.end method
