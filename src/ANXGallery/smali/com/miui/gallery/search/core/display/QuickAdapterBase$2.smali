.class Lcom/miui/gallery/search/core/display/QuickAdapterBase$2;
.super Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;
.source "QuickAdapterBase.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/search/core/display/QuickAdapterBase;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/search/core/display/QuickAdapterBase;


# direct methods
.method constructor <init>(Lcom/miui/gallery/search/core/display/QuickAdapterBase;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/search/core/display/QuickAdapterBase$2;->this$0:Lcom/miui/gallery/search/core/display/QuickAdapterBase;

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;-><init>()V

    return-void
.end method


# virtual methods
.method public onChanged()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/search/core/display/QuickAdapterBase$2;->this$0:Lcom/miui/gallery/search/core/display/QuickAdapterBase;

    invoke-static {v0}, Lcom/miui/gallery/search/core/display/QuickAdapterBase;->access$100(Lcom/miui/gallery/search/core/display/QuickAdapterBase;)V

    return-void
.end method
