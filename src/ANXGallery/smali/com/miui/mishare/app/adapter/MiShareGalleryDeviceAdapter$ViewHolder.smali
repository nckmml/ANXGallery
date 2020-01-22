.class Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;
.super Landroidx/recyclerview/widget/RecyclerView$ViewHolder;
.source "MiShareGalleryDeviceAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = "ViewHolder"
.end annotation


# instance fields
.field mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;


# direct methods
.method constructor <init>(Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;)V
    .locals 0

    invoke-direct {p0, p1}, Landroidx/recyclerview/widget/RecyclerView$ViewHolder;-><init>(Landroid/view/View;)V

    iput-object p1, p0, Lcom/miui/mishare/app/adapter/MiShareGalleryDeviceAdapter$ViewHolder;->mDeviceView:Lcom/miui/mishare/app/view/MiShareGalleryDeviceView;

    return-void
.end method
