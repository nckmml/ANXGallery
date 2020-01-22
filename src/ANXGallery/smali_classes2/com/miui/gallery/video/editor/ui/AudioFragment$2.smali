.class Lcom/miui/gallery/video/editor/ui/AudioFragment$2;
.super Ljava/lang/Object;
.source "AudioFragment.java"

# interfaces
.implements Lcom/miui/gallery/net/base/ResponseListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/video/editor/ui/AudioFragment;->loadResourceData()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/video/editor/ui/AudioFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public synthetic lambda$null$132$AudioFragment$2()V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    iget-object v0, v0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mContext:Landroid/content/Context;

    const v1, 0x7f10089c

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ToastUtils;->makeText(Landroid/content/Context;I)V

    return-void
.end method

.method public synthetic lambda$onResponseError$133$AudioFragment$2(Lcom/miui/gallery/net/base/ErrorCode;)V
    .locals 5

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->access$200(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Ljava/util/ArrayList;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->access$200(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Ljava/util/ArrayList;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_0

    new-instance v0, Lcom/miui/gallery/video/editor/LocalAudio;

    const v1, 0x7f0703c3

    const/4 v2, 0x0

    const-string v3, "audio_none"

    const-string v4, "ve_type_none"

    invoke-direct {v0, v3, v1, v4, v2}, Lcom/miui/gallery/video/editor/LocalAudio;-><init>(Ljava/lang/String;ILjava/lang/String;Z)V

    iget-object v1, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    invoke-static {v1}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->access$200(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->access$300(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    move-result-object v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    invoke-static {v0}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->access$300(Lcom/miui/gallery/video/editor/ui/AudioFragment;)Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/video/editor/adapter/AudioRecyclerViewAdapter;->notifyDataSetChanged()V

    :cond_0
    sget-object v0, Lcom/miui/gallery/net/base/ErrorCode;->NETWORK_NOT_CONNECTED:Lcom/miui/gallery/net/base/ErrorCode;

    if-ne p1, v0, :cond_1

    new-instance p1, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$XDAe2A0ohLVoWOWhaRWAd0-YIj8;

    invoke-direct {p1, p0}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$XDAe2A0ohLVoWOWhaRWAd0-YIj8;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment$2;)V

    invoke-static {p1}, Lcom/miui/gallery/threadpool/ThreadManager;->runOnMainThread(Ljava/lang/Runnable;)V

    :cond_1
    return-void
.end method

.method public varargs onResponse([Ljava/lang/Object;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    const/4 v1, 0x1

    iput-boolean v1, v0, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mHasLoadData:Z

    const/4 v1, 0x0

    aget-object p1, p1, v1

    check-cast p1, Ljava/util/List;

    invoke-static {v0, p1}, Lcom/miui/gallery/video/editor/ui/AudioFragment;->access$100(Lcom/miui/gallery/video/editor/ui/AudioFragment;Ljava/util/List;)V

    return-void
.end method

.method public onResponseError(Lcom/miui/gallery/net/base/ErrorCode;Ljava/lang/String;Ljava/lang/Object;)V
    .locals 0

    const-string p2, "AudioFragment"

    const-string p3, "errorCode: %s"

    invoke-static {p2, p3, p1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object p2, p0, Lcom/miui/gallery/video/editor/ui/AudioFragment$2;->this$0:Lcom/miui/gallery/video/editor/ui/AudioFragment;

    const/4 p3, 0x1

    iput-boolean p3, p2, Lcom/miui/gallery/video/editor/ui/AudioFragment;->mHasLoadData:Z

    new-instance p2, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$U5kgop8gAPz1z_eOk2BdvVxKCxc;

    invoke-direct {p2, p0, p1}, Lcom/miui/gallery/video/editor/ui/-$$Lambda$AudioFragment$2$U5kgop8gAPz1z_eOk2BdvVxKCxc;-><init>(Lcom/miui/gallery/video/editor/ui/AudioFragment$2;Lcom/miui/gallery/net/base/ErrorCode;)V

    invoke-static {p2}, Lcom/miui/gallery/threadpool/ThreadManager;->runOnMainThread(Ljava/lang/Runnable;)V

    return-void
.end method
