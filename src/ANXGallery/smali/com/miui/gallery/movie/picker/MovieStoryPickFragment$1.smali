.class Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;
.super Ljava/lang/Object;
.source "MovieStoryPickFragment.java"

# interfaces
.implements Landroid/app/LoaderManager$LoaderCallbacks;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;


# direct methods
.method constructor <init>(Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;->this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreateLoader(ILandroid/os/Bundle;)Landroid/content/Loader;
    .locals 0

    new-instance p1, Landroid/content/CursorLoader;

    iget-object p2, p0, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;->this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;

    invoke-static {p2}, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;->access$000(Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;)Landroid/app/Activity;

    move-result-object p2

    invoke-direct {p1, p2}, Landroid/content/CursorLoader;-><init>(Landroid/content/Context;)V

    iget-object p2, p0, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;->this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;->getUri()Landroid/net/Uri;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setUri(Landroid/net/Uri;)V

    iget-object p2, p0, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;->this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;

    invoke-static {p2}, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;->access$100(Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;)[Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setProjection([Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;->this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;->getSelection()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setSelection(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;->this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;

    invoke-static {p2}, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;->access$200(Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setSortOrder(Ljava/lang/String;)V

    return-object p1
.end method

.method public onLoadFinished(Landroid/content/Loader;Ljava/lang/Object;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment$1;->this$0:Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;

    invoke-static {p1}, Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;->access$300(Lcom/miui/gallery/movie/picker/MovieStoryPickFragment;)Lcom/miui/gallery/picker/helper/PickableBaseTimeLineAdapterWrapper;

    move-result-object p1

    check-cast p2, Landroid/database/Cursor;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/picker/helper/PickableBaseTimeLineAdapterWrapper;->swapCursor(Landroid/database/Cursor;)Landroid/database/Cursor;

    return-void
.end method

.method public onLoaderReset(Landroid/content/Loader;)V
    .locals 0

    return-void
.end method
