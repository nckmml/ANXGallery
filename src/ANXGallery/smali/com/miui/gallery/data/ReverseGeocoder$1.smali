.class Lcom/miui/gallery/data/ReverseGeocoder$1;
.super Ljava/lang/Object;
.source "ReverseGeocoder.java"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$JobContext;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/data/ReverseGeocoder;->lookupAddress(DDZ)Landroid/location/Address;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/data/ReverseGeocoder;


# direct methods
.method constructor <init>(Lcom/miui/gallery/data/ReverseGeocoder;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/data/ReverseGeocoder$1;->this$0:Lcom/miui/gallery/data/ReverseGeocoder;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public isCancelled()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public setCancelListener(Lcom/miui/gallery/threadpool/ThreadPool$CancelListener;)V
    .locals 0

    return-void
.end method
