.class public Lcom/miui/gallery/search/resultpage/SearchResultActivity;
.super Lcom/miui/gallery/activity/BaseActivity;
.source "SearchResultActivity.java"


# instance fields
.field private mFragment:Lmiui/app/Fragment;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/activity/BaseActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected getFragmentContainerId()I
    .locals 1

    const v0, 0x7f0900d0

    return v0
.end method

.method public onActivityResult(IILandroid/content/Intent;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    invoke-virtual {v0, p1, p2, p3}, Lmiui/app/Fragment;->onActivityResult(IILandroid/content/Intent;)V

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 11

    invoke-super {p0, p1}, Lcom/miui/gallery/activity/BaseActivity;->onCreate(Landroid/os/Bundle;)V

    const p1, 0x7f0b018a

    invoke-virtual {p0, p1}, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->setContentView(I)V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->getIntent()Landroid/content/Intent;

    move-result-object p1

    invoke-virtual {p1}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v0

    const-string v1, "SearchResultActivity"

    if-nez v0, :cond_0

    const-string p1, "No extra data fount!"

    invoke-static {v1, p1}, Lcom/miui/gallery/search/utils/SearchLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->finish()V

    return-void

    :cond_0
    invoke-virtual {p1}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v0

    invoke-virtual {v0}, Landroid/net/Uri;->getLastPathSegment()Ljava/lang/String;

    move-result-object v2

    const-string v3, "result"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    const-string v3, "type"

    const/4 v4, 0x0

    if-eqz v2, :cond_1

    invoke-virtual {v0, v3}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    const-string v2, "client_enter_image_result_page"

    goto :goto_0

    :cond_1
    move-object v2, v4

    :goto_0
    invoke-virtual {v0}, Landroid/net/Uri;->getLastPathSegment()Ljava/lang/String;

    move-result-object v5

    const-string v6, "tags"

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    const-string v6, "likelyImage"

    const-string v7, "locationList"

    const-string v8, "tagList"

    if-nez v5, :cond_6

    invoke-virtual {v8, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_2

    goto :goto_2

    :cond_2
    invoke-virtual {v0}, Landroid/net/Uri;->getLastPathSegment()Ljava/lang/String;

    move-result-object v5

    const-string v9, "locations"

    invoke-virtual {v5, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_5

    invoke-virtual {v7, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_3

    goto :goto_1

    :cond_3
    invoke-virtual {v0}, Landroid/net/Uri;->getLastPathSegment()Ljava/lang/String;

    move-result-object v5

    const-string v9, "likelyResults"

    invoke-virtual {v5, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_4

    invoke-virtual {v6, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_7

    :cond_4
    const-string v2, "client_enter_likely_image_list_page"

    move-object v4, v6

    goto :goto_3

    :cond_5
    :goto_1
    const-string v2, "client_enter_location_list_page"

    move-object v4, v7

    goto :goto_3

    :cond_6
    :goto_2
    const-string v2, "client_enter_tag_list_page"

    move-object v4, v8

    :cond_7
    :goto_3
    if-nez v4, :cond_8

    const-string p1, "Invalid action uri, no result type specified!"

    invoke-static {v1, p1}, Lcom/miui/gallery/search/utils/SearchLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->finish()V

    return-void

    :cond_8
    invoke-virtual {p1}, Landroid/content/Intent;->getExtras()Landroid/os/Bundle;

    move-result-object v1

    invoke-static {v1}, Lcom/miui/gallery/search/statistics/SearchStatUtils;->buildSearchEventParams(Landroid/os/Bundle;)Ljava/util/Map;

    move-result-object v1

    if-nez v1, :cond_9

    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    :cond_9
    invoke-virtual {v0}, Landroid/net/Uri;->getQueryParameterNames()Ljava/util/Set;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_4
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v9

    if-eqz v9, :cond_a

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v9

    check-cast v9, Ljava/lang/String;

    invoke-virtual {v0, v9}, Landroid/net/Uri;->getQueryParameter(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    invoke-interface {v1, v9, v10}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_4

    :cond_a
    const-string v5, "from"

    invoke-virtual {p1, v5}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, v2, v1}, Lcom/miui/gallery/search/statistics/SearchStatUtils;->cacheEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->getFragmentManager()Landroid/app/FragmentManager;

    move-result-object p1

    const-string v1, "RootFragment"

    invoke-virtual {p1, v1}, Landroid/app/FragmentManager;->findFragmentByTag(Ljava/lang/String;)Landroid/app/Fragment;

    move-result-object p1

    check-cast p1, Lmiui/app/Fragment;

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    iget-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    if-nez p1, :cond_f

    invoke-virtual {v0}, Landroid/net/Uri;->buildUpon()Landroid/net/Uri$Builder;

    move-result-object p1

    invoke-virtual {p1, v3, v4}, Landroid/net/Uri$Builder;->appendQueryParameter(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;

    move-result-object p1

    invoke-virtual {p1}, Landroid/net/Uri$Builder;->build()Landroid/net/Uri;

    move-result-object p1

    invoke-virtual {p0}, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/content/Intent;->setData(Landroid/net/Uri;)Landroid/content/Intent;

    const-string p1, "imageList"

    invoke-virtual {v4, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_b

    new-instance p1, Lcom/miui/gallery/search/resultpage/SearchImageResultFragment;

    invoke-direct {p1}, Lcom/miui/gallery/search/resultpage/SearchImageResultFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    goto :goto_5

    :cond_b
    invoke-virtual {v4, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_c

    new-instance p1, Lcom/miui/gallery/search/resultpage/LocationListFragment;

    invoke-direct {p1}, Lcom/miui/gallery/search/resultpage/LocationListFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    goto :goto_5

    :cond_c
    invoke-virtual {v4, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_d

    new-instance p1, Lcom/miui/gallery/search/resultpage/TagListFragment;

    invoke-direct {p1}, Lcom/miui/gallery/search/resultpage/TagListFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    goto :goto_5

    :cond_d
    invoke-virtual {v4, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_e

    new-instance p1, Lcom/miui/gallery/search/resultpage/SearchFeedbackLikelyResultFragment;

    invoke-direct {p1}, Lcom/miui/gallery/search/resultpage/SearchFeedbackLikelyResultFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    goto :goto_5

    :cond_e
    new-instance p1, Lcom/miui/gallery/search/resultpage/SearchResultFragment;

    invoke-direct {p1}, Lcom/miui/gallery/search/resultpage/SearchResultFragment;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    :goto_5
    iget-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->mFragment:Lmiui/app/Fragment;

    const/4 v0, 0x0

    invoke-virtual {p0, p1, v1, v0, v0}, Lcom/miui/gallery/search/resultpage/SearchResultActivity;->startFragment(Landroid/app/Fragment;Ljava/lang/String;ZZ)V

    :cond_f
    return-void
.end method
