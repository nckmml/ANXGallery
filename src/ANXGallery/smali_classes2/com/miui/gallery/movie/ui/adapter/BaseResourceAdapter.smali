.class public Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter;
.super Lcom/miui/gallery/movie/ui/adapter/BaseAdapter;
.source "BaseResourceAdapter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter$BaseResourceHolder;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Lcom/miui/gallery/movie/entity/MovieResource;",
        ">",
        "Lcom/miui/gallery/movie/ui/adapter/BaseAdapter<",
        "TT;>;"
    }
.end annotation


# instance fields
.field private mPlaceColors:[I


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 2

    invoke-direct {p0, p1}, Lcom/miui/gallery/movie/ui/adapter/BaseAdapter;-><init>(Landroid/content/Context;)V

    const/4 p1, 0x6

    new-array p1, p1, [I

    sget v0, Lcom/miui/gallery/movie/R$drawable;->movie_image_place_1:I

    const/4 v1, 0x0

    aput v0, p1, v1

    sget v0, Lcom/miui/gallery/movie/R$drawable;->movie_image_place_2:I

    const/4 v1, 0x1

    aput v0, p1, v1

    sget v0, Lcom/miui/gallery/movie/R$drawable;->movie_image_place_3:I

    const/4 v1, 0x2

    aput v0, p1, v1

    sget v0, Lcom/miui/gallery/movie/R$drawable;->movie_image_place_4:I

    const/4 v1, 0x3

    aput v0, p1, v1

    sget v0, Lcom/miui/gallery/movie/R$drawable;->movie_image_place_5:I

    const/4 v1, 0x4

    aput v0, p1, v1

    sget v0, Lcom/miui/gallery/movie/R$drawable;->movie_image_place_6:I

    const/4 v1, 0x5

    aput v0, p1, v1

    iput-object p1, p0, Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter;->mPlaceColors:[I

    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter;)[I
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter;->mPlaceColors:[I

    return-object p0
.end method


# virtual methods
.method protected bridge synthetic getHolder(Landroid/view/View;)Lcom/miui/gallery/movie/ui/adapter/BaseAdapter$BaseHolder;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter;->getHolder(Landroid/view/View;)Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter$BaseResourceHolder;

    move-result-object p1

    return-object p1
.end method

.method protected getHolder(Landroid/view/View;)Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter$BaseResourceHolder;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/view/View;",
            ")",
            "Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter<",
            "TT;>.BaseResourceHolder;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter$BaseResourceHolder;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter$BaseResourceHolder;-><init>(Lcom/miui/gallery/movie/ui/adapter/BaseResourceAdapter;Landroid/view/View;)V

    return-object v0
.end method

.method protected getLayoutId()I
    .locals 1

    sget v0, Lcom/miui/gallery/movie/R$layout;->movie_layout_template_item:I

    return v0
.end method
