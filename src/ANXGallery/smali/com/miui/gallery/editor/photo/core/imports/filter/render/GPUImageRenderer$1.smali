.class Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;
.super Ljava/lang/Object;
.source "GPUImageRenderer.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;->setFilter(Lcom/miui/filtersdk/filter/base/GPUImageFilter;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;

.field final synthetic val$filter:Lcom/miui/filtersdk/filter/base/GPUImageFilter;


# direct methods
.method constructor <init>(Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;Lcom/miui/filtersdk/filter/base/GPUImageFilter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;->this$0:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;->val$filter:Lcom/miui/filtersdk/filter/base/GPUImageFilter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;->this$0:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;->access$000(Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;)Lcom/miui/filtersdk/filter/base/GPUImageFilter;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;->this$0:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;->val$filter:Lcom/miui/filtersdk/filter/base/GPUImageFilter;

    invoke-static {v1, v2}, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;->access$002(Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;Lcom/miui/filtersdk/filter/base/GPUImageFilter;)Lcom/miui/filtersdk/filter/base/GPUImageFilter;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/miui/filtersdk/filter/base/GPUImageFilter;->destroy()V

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;->this$0:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;->access$000(Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;)Lcom/miui/filtersdk/filter/base/GPUImageFilter;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/filtersdk/filter/base/GPUImageFilter;->init()V

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer$1;->this$0:Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;

    invoke-static {v0}, Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;->access$100(Lcom/miui/gallery/editor/photo/core/imports/filter/render/GPUImageRenderer;)V

    return-void
.end method
