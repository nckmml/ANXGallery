.class Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;
.super Ljava/lang/Object;
.source "HomePageStartupHelper.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/activity/HomePageStartupHelper$2;->run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Void;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lcom/miui/gallery/activity/HomePageStartupHelper$2;


# direct methods
.method constructor <init>(Lcom/miui/gallery/activity/HomePageStartupHelper$2;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;->this$1:Lcom/miui/gallery/activity/HomePageStartupHelper$2;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;->this$1:Lcom/miui/gallery/activity/HomePageStartupHelper$2;

    iget-object v0, v0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v0}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$800(Lcom/miui/gallery/activity/HomePageStartupHelper;)Lcom/miui/gallery/activity/HomePageStartupHelper$DataLoadListener;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;->this$1:Lcom/miui/gallery/activity/HomePageStartupHelper$2;

    iget-object v0, v0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v0}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$400(Lcom/miui/gallery/activity/HomePageStartupHelper;)Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;->this$1:Lcom/miui/gallery/activity/HomePageStartupHelper$2;

    iget-object v0, v0, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v0}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$800(Lcom/miui/gallery/activity/HomePageStartupHelper;)Lcom/miui/gallery/activity/HomePageStartupHelper$DataLoadListener;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/activity/HomePageStartupHelper$2$1;->this$1:Lcom/miui/gallery/activity/HomePageStartupHelper$2;

    iget-object v1, v1, Lcom/miui/gallery/activity/HomePageStartupHelper$2;->this$0:Lcom/miui/gallery/activity/HomePageStartupHelper;

    invoke-static {v1}, Lcom/miui/gallery/activity/HomePageStartupHelper;->access$100(Lcom/miui/gallery/activity/HomePageStartupHelper;)Landroid/database/Cursor;

    move-result-object v1

    invoke-interface {v0, v1}, Lcom/miui/gallery/activity/HomePageStartupHelper$DataLoadListener;->onDataLoadFinish(Landroid/database/Cursor;)V

    :cond_0
    return-void
.end method
