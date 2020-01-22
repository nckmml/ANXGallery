.class abstract Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;
.super Ljava/lang/Object;
.source "AssistantPageHeaderAdapter.java"

# interfaces
.implements Landroid/view/View$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x402
    name = "Entrance"
.end annotation


# instance fields
.field mFeatureName:Ljava/lang/String;

.field mIconRes:I

.field mIndex:I

.field mNameRes:I

.field final synthetic this$0:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;IIILjava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->this$0:Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p2, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mIndex:I

    iput p3, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mNameRes:I

    iput p4, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mIconRes:I

    iput-object p5, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mFeatureName:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public getFeatureName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mFeatureName:Ljava/lang/String;

    return-object v0
.end method

.method public onClick(Landroid/view/View;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mFeatureName:Ljava/lang/String;

    invoke-static {p1}, Lcom/miui/gallery/reddot/DisplayStatusManager;->setRedDotClicked(Ljava/lang/String;)V

    return-void
.end method
