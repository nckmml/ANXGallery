.class public final Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;
.super Landroid/content/BroadcastReceiver;
.source "MiShareGalleryDeviceAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x11
    name = "Receiver"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;


# direct methods
.method public constructor <init>(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-direct {p0}, Landroid/content/BroadcastReceiver;-><init>()V

    return-void
.end method


# virtual methods
.method public onReceive(Landroid/content/Context;Landroid/content/Intent;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$600(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lmiui/app/AlertDialog;

    move-result-object p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$700(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Landroid/content/Context;

    move-result-object p1

    instance-of p1, p1, Landroid/app/Activity;

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$700(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Landroid/content/Context;

    move-result-object p1

    check-cast p1, Landroid/app/Activity;

    invoke-virtual {p1}, Landroid/app/Activity;->isFinishing()Z

    move-result p1

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$Receiver;->this$0:Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;

    invoke-static {p1}, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;->access$600(Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;)Lmiui/app/AlertDialog;

    move-result-object p1

    invoke-virtual {p1}, Lmiui/app/AlertDialog;->dismiss()V

    :cond_0
    return-void
.end method
