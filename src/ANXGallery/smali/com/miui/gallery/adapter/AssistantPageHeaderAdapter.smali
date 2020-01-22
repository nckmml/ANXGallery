.class public Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;
.super Landroid/widget/BaseAdapter;
.source "AssistantPageHeaderAdapter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$FilterSkyEntrance;,
        Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$RecommendEntrance;,
        Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$PhotoMovieEntrance;,
        Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$CollageEntrance;,
        Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;,
        Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;
    }
.end annotation


# instance fields
.field private mContext:Landroid/content/Context;

.field private mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

.field private mLayoutInflater:Landroid/view/LayoutInflater;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .locals 4

    invoke-direct {p0}, Landroid/widget/BaseAdapter;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mContext:Landroid/content/Context;

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mContext:Landroid/content/Context;

    invoke-static {p1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mLayoutInflater:Landroid/view/LayoutInflater;

    const/4 p1, 0x3

    new-array p1, p1, [Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    iput-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    new-instance v0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$CollageEntrance;

    const/4 v1, 0x0

    const v2, 0x7f1004f3

    const v3, 0x7f07006d

    invoke-direct {v0, p0, v1, v2, v3}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$CollageEntrance;-><init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;III)V

    aput-object v0, p1, v1

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    new-instance v0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$PhotoMovieEntrance;

    const/4 v1, 0x1

    const v2, 0x7f1004f4

    const v3, 0x7f07006f

    invoke-direct {v0, p0, v1, v2, v3}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$PhotoMovieEntrance;-><init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;III)V

    aput-object v0, p1, v1

    invoke-static {}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->replaceRecommend()Z

    move-result p1

    const/4 v0, 0x2

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    new-instance v1, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$FilterSkyEntrance;

    const v2, 0x7f10048d

    const v3, 0x7f07006e

    invoke-direct {v1, p0, v0, v2, v3}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$FilterSkyEntrance;-><init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;III)V

    aput-object v1, p1, v0

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    new-instance v1, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$RecommendEntrance;

    const v2, 0x7f100098

    const v3, 0x7f070070

    invoke-direct {v1, p0, v0, v2, v3}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$RecommendEntrance;-><init>(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;III)V

    aput-object v1, p1, v0

    :goto_0
    return-void
.end method

.method static synthetic access$000(Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;)Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mContext:Landroid/content/Context;

    return-object p0
.end method

.method public static replaceRecommend()Z
    .locals 1

    invoke-static {}, Lcom/miui/gallery/util/FilterSkyEntranceUtils;->showSingleFilterSky()Z

    move-result v0

    return v0
.end method


# virtual methods
.method public getCount()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    if-eqz v0, :cond_0

    array-length v0, v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public getItem(I)Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mEntrances:[Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    if-eqz v0, :cond_0

    if-ltz p1, :cond_0

    array-length v1, v0

    if-ge p1, v1, :cond_0

    aget-object p1, v0, p1

    return-object p1

    :cond_0
    const/4 p1, 0x0

    return-object p1
.end method

.method public bridge synthetic getItem(I)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->getItem(I)Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    move-result-object p1

    return-object p1
.end method

.method public getItemId(I)J
    .locals 2

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->getItem(I)Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    move-result-object p1

    if-eqz p1, :cond_0

    iget p1, p1, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;->mIndex:I

    int-to-long v0, p1

    return-wide v0

    :cond_0
    const-wide/16 v0, -0x1

    return-wide v0
.end method

.method public getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 2

    if-nez p2, :cond_0

    iget-object p2, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mLayoutInflater:Landroid/view/LayoutInflater;

    const v0, 0x7f0b0042

    const/4 v1, 0x0

    invoke-virtual {p2, v0, p3, v1}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object p2

    new-instance p3, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;

    invoke-direct {p3, p2}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;-><init>(Landroid/view/View;)V

    invoke-virtual {p2, p3}, Landroid/view/View;->setTag(Ljava/lang/Object;)V

    :cond_0
    invoke-virtual {p2}, Landroid/view/View;->getTag()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;

    iget-object v0, p0, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->mContext:Landroid/content/Context;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->getItem(I)Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;

    move-result-object v1

    invoke-virtual {p3, v0, v1, p1}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$ViewHolder;->bindView(Landroid/content/Context;Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter$Entrance;I)V

    return-object p2
.end method
