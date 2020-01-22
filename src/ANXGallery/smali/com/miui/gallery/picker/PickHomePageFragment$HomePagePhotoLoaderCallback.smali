.class Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;
.super Ljava/lang/Object;
.source "PickHomePageFragment.java"

# interfaces
.implements Landroid/app/LoaderManager$LoaderCallbacks;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/picker/PickHomePageFragment;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "HomePagePhotoLoaderCallback"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/picker/PickHomePageFragment;


# direct methods
.method private constructor <init>(Lcom/miui/gallery/picker/PickHomePageFragment;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;->this$0:Lcom/miui/gallery/picker/PickHomePageFragment;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/picker/PickHomePageFragment;Lcom/miui/gallery/picker/PickHomePageFragment$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;-><init>(Lcom/miui/gallery/picker/PickHomePageFragment;)V

    return-void
.end method


# virtual methods
.method public onCreateLoader(ILandroid/os/Bundle;)Landroid/content/Loader;
    .locals 0

    new-instance p1, Landroid/content/CursorLoader;

    iget-object p2, p0, Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;->this$0:Lcom/miui/gallery/picker/PickHomePageFragment;

    iget-object p2, p2, Lcom/miui/gallery/picker/PickHomePageFragment;->mActivity:Landroid/app/Activity;

    invoke-direct {p1, p2}, Landroid/content/CursorLoader;-><init>(Landroid/content/Context;)V

    iget-object p2, p0, Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;->this$0:Lcom/miui/gallery/picker/PickHomePageFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/picker/PickHomePageFragment;->getUri()Landroid/net/Uri;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setUri(Landroid/net/Uri;)V

    sget-object p2, Lcom/miui/gallery/picker/PickerHomePageAdapter;->PROJECTION:[Ljava/lang/String;

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setProjection([Ljava/lang/String;)V

    const-string p2, "alias_sort_time DESC "

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setSortOrder(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;->this$0:Lcom/miui/gallery/picker/PickHomePageFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/picker/PickHomePageFragment;->getSelection()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setSelection(Ljava/lang/String;)V

    iget-object p2, p0, Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;->this$0:Lcom/miui/gallery/picker/PickHomePageFragment;

    invoke-virtual {p2}, Lcom/miui/gallery/picker/PickHomePageFragment;->getSelectionArgs()[Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p1, p2}, Landroid/content/CursorLoader;->setSelectionArgs([Ljava/lang/String;)V

    return-object p1
.end method

.method public onLoadFinished(Landroid/content/Loader;Ljava/lang/Object;)V
    .locals 0

    iget-object p1, p0, Lcom/miui/gallery/picker/PickHomePageFragment$HomePagePhotoLoaderCallback;->this$0:Lcom/miui/gallery/picker/PickHomePageFragment;

    invoke-static {p1}, Lcom/miui/gallery/picker/PickHomePageFragment;->access$000(Lcom/miui/gallery/picker/PickHomePageFragment;)Lcom/miui/gallery/picker/helper/PickableBaseTimeLineAdapterWrapper;

    move-result-object p1

    check-cast p2, Landroid/database/Cursor;

    invoke-virtual {p1, p2}, Lcom/miui/gallery/picker/helper/PickableBaseTimeLineAdapterWrapper;->swapCursor(Landroid/database/Cursor;)Landroid/database/Cursor;

    return-void
.end method

.method public onLoaderReset(Landroid/content/Loader;)V
    .locals 0

    return-void
.end method
