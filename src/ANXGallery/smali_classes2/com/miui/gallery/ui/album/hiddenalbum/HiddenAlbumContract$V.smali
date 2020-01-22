.class public abstract Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$V;
.super Lcom/miui/gallery/app/base/BaseListPageFragment;
.source "HiddenAlbumContract.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x409
    name = "V"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        "P::",
        "Lcom/miui/gallery/base_optimization/mvp/presenter/IPresenter;",
        ">",
        "Lcom/miui/gallery/app/base/BaseListPageFragment<",
        "TT;",
        "Lcom/miui/gallery/ui/album/hiddenalbum/HiddenAlbumContract$P;",
        ">;"
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/app/base/BaseListPageFragment;-><init>()V

    return-void
.end method


# virtual methods
.method public abstract showHiddenListData(Ljava/util/List;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation
.end method

.method public abstract showUnHiddenAlbumIsFaild()V
.end method

.method public abstract showUnHiddenAlbumIsSuccess(Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;)V
.end method

.method public abstract updateHiddenListData(Ljava/util/List;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/ui/album/hiddenalbum/viewbean/HiddenAlbumItemViewBean;",
            ">;)V"
        }
    .end annotation
.end method
