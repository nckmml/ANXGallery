.class Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;
.super Ljava/lang/Object;
.source "AssistantPageHeaderAdapter.java"

# interfaces
.implements Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyDownloadStateListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->bindView(Landroid/content/Context;Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;I)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;

.field final synthetic val$entrance:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;


# direct methods
.method constructor <init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;->this$0:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;

    iput-object p2, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;->val$entrance:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDownloadStart(I)V
    .locals 0

    return-void
.end method

.method public onDownloading(II)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;->this$0:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;

    iget-object p1, p1, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mTitle:Landroid/widget/TextView;

    const p2, 0x7f10068b

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(I)V

    return-void
.end method

.method public onFinish(IZI)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;->this$0:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;

    iget-object p1, p1, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->mTitle:Landroid/widget/TextView;

    iget-object p2, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder$2;->val$entrance:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    iget p2, p2, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mNameRes:I

    invoke-virtual {p1, p2}, Landroid/widget/TextView;->setText(I)V

    return-void
.end method
