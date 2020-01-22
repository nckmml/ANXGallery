.class Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;
.super Ljava/lang/Object;
.source "MiShareGalleryDeviceAdapter.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$100(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$200(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Ljava/util/List;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$200(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Ljava/util/List;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$200(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Ljava/util/List;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {v1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$300(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$DeviceRssiComparator;

    move-result-object v1

    invoke-static {v0, v1}, Ljava/util/Collections;->sort(Ljava/util/List;Ljava/util/Comparator;)V

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->notifyDataSetChanged()V

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$1;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {v0}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$400(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V

    :cond_0
    return-void
.end method
