.class Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;
.super Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;
.source "HiddenAlbumFragment.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;->getRecycleViewDecoration()Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

.field final synthetic val$firstItemMarginTop:I

.field final synthetic val$verticalSpacing:I


# direct methods
.method constructor <init>(Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;II)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;->this$0:Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment;

    iput p2, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;->val$firstItemMarginTop:I

    iput p3, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;->val$verticalSpacing:I

    invoke-direct {p0}, Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;-><init>()V

    return-void
.end method


# virtual methods
.method public getItemOffsets(Landroid/graphics/Rect;Landroid/view/View;Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$State;)V
    .locals 0

    invoke-super {p0, p1, p2, p3, p4}, Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;->getItemOffsets(Landroid/graphics/Rect;Landroid/view/View;Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$State;)V

    invoke-virtual {p3, p2}, Landroidx/recyclerview/widget/RecyclerView;->getChildAdapterPosition(Landroid/view/View;)I

    move-result p2

    if-nez p2, :cond_0

    iget p2, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;->val$firstItemMarginTop:I

    iput p2, p1, Landroid/graphics/Rect;->top:I

    iget p2, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;->val$verticalSpacing:I

    iput p2, p1, Landroid/graphics/Rect;->bottom:I

    goto :goto_0

    :cond_0
    iget p2, p0, Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumFragment$1;->val$verticalSpacing:I

    iput p2, p1, Landroid/graphics/Rect;->bottom:I

    iput p2, p1, Landroid/graphics/Rect;->top:I

    :goto_0
    return-void
.end method
