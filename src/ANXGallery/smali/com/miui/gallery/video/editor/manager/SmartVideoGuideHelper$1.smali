.class Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$1;
.super Ljava/lang/Object;
.source "SmartVideoGuideHelper.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;->tryShowGuideView(Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;

.field final synthetic val$filePath:Ljava/lang/String;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$1;->this$0:Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;

    iput-object p2, p0, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$1;->val$filePath:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$1;->this$0:Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;->access$000(Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;)Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$SmartVideoGuideListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$1;->this$0:Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;->access$000(Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper;)Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$SmartVideoGuideListener;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$1;->val$filePath:Ljava/lang/String;

    invoke-interface {v0, v1}, Lcom/miui/gallery/video/editor/manager/SmartVideoGuideHelper$SmartVideoGuideListener;->showGuideView(Ljava/lang/String;)V

    :cond_0
    return-void
.end method
