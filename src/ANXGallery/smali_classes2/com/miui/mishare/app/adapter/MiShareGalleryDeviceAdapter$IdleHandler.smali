.class Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$IdleHandler;
.super Landroid/os/Handler;
.source "MiShareGalleryDeviceAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "IdleHandler"
.end annotation


# instance fields
.field private mOuter:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V
    .locals 1

    invoke-direct {p0}, Landroid/os/Handler;-><init>()V

    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$IdleHandler;->mOuter:Ljava/lang/ref/WeakReference;

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 2

    invoke-super {p0, p1}, Landroid/os/Handler;->handleMessage(Landroid/os/Message;)V

    iget v0, p1, Landroid/os/Message;->what:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    iget-object p1, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast p1, Ljava/lang/String;

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$IdleHandler;->mOuter:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$IdleHandler;->mOuter:Ljava/lang/ref/WeakReference;

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-virtual {v0, p1, v1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->setDeviceState(Ljava/lang/String;I)V

    :cond_0
    return-void
.end method
