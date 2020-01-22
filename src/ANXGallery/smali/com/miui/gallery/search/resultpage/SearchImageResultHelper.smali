.class public Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;
.super Lcom/miui/gallery/search/resultpage/SearchResultHelper;
.source "SearchImageResultHelper.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$ImageResultProcessor;,
        Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$FilterProcessor;
    }
.end annotation


# static fields
.field private static final OUT_PROJECTION:[Ljava/lang/String;

.field private static final QUERY_PROJECTION:[Ljava/lang/String;

.field private static final TITLE_SEPARATOR:Ljava/lang/String;


# instance fields
.field private mContext:Landroid/content/Context;

.field private mSupportExpand:Z


# direct methods
.method static constructor <clinit>()V
    .locals 17

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    const v1, 0x7f1005d0

    invoke-virtual {v0, v1}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->TITLE_SEPARATOR:Ljava/lang/String;

    const-string v1, "_id"

    const-string v2, "alias_micro_thumbnail"

    const-string v3, "alias_create_date"

    const-string v4, "alias_create_time"

    const-string v5, "location"

    const-string v6, "sha1"

    const-string v7, "serverType"

    const-string v8, "duration"

    const-string v9, "mimeType"

    const-string v10, "alias_sync_state"

    const-string v11, "thumbnailFile"

    const-string v12, "localFile"

    const-string v13, "serverId"

    const-string v14, "alias_is_favorite"

    const-string v15, "specialTypeFlags"

    const-string v16, "size"

    filled-new-array/range {v1 .. v16}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->QUERY_PROJECTION:[Ljava/lang/String;

    sget-object v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->QUERY_PROJECTION:[Ljava/lang/String;

    const-string v1, "item_collapse_visibility"

    filled-new-array {v1}, [Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/StringUtils;->mergeStringArray([Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->OUT_PROJECTION:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/gallery/search/core/QueryInfo;ZLcom/miui/gallery/search/core/suggestion/GroupedSuggestionCursor;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Lcom/miui/gallery/search/core/QueryInfo;",
            "Z",
            "Lcom/miui/gallery/search/core/suggestion/GroupedSuggestionCursor<",
            "Lcom/miui/gallery/search/core/suggestion/SuggestionSection;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0, p2, p4}, Lcom/miui/gallery/search/resultpage/SearchResultHelper;-><init>(Lcom/miui/gallery/search/core/QueryInfo;Lcom/miui/gallery/search/core/suggestion/GroupedSuggestionCursor;)V

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->mContext:Landroid/content/Context;

    iput-boolean p3, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->mSupportExpand:Z

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/miui/gallery/search/core/QueryInfo;ZLcom/miui/gallery/search/core/suggestion/SuggestionSection;)V
    .locals 0

    invoke-direct {p0, p2, p4}, Lcom/miui/gallery/search/resultpage/SearchResultHelper;-><init>(Lcom/miui/gallery/search/core/QueryInfo;Lcom/miui/gallery/search/core/suggestion/SuggestionSection;)V

    iput-object p1, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->mContext:Landroid/content/Context;

    iput-boolean p3, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->mSupportExpand:Z

    return-void
.end method

.method static synthetic access$100()[Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->QUERY_PROJECTION:[Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$200(Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;)Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->mContext:Landroid/content/Context;

    return-object p0
.end method

.method static synthetic access$300()[Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->OUT_PROJECTION:[Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$400()Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->TITLE_SEPARATOR:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$500(Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->mSupportExpand:Z

    return p0
.end method


# virtual methods
.method protected bridge synthetic createDataListResultProcessor(Lcom/miui/gallery/search/core/suggestion/RankInfo;)Lcom/miui/gallery/search/core/resultprocessor/ResultProcessor;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;->createDataListResultProcessor(Lcom/miui/gallery/search/core/suggestion/RankInfo;)Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$ImageResultProcessor;

    move-result-object p1

    return-object p1
.end method

.method protected createDataListResultProcessor(Lcom/miui/gallery/search/core/suggestion/RankInfo;)Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$ImageResultProcessor;
    .locals 1

    new-instance v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$ImageResultProcessor;

    invoke-direct {v0, p0, p1}, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$ImageResultProcessor;-><init>(Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;Lcom/miui/gallery/search/core/suggestion/RankInfo;)V

    return-object v0
.end method

.method protected createFilterProcessor()Lcom/miui/gallery/search/core/resultprocessor/ResultProcessor;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lcom/miui/gallery/search/core/resultprocessor/ResultProcessor<",
            "Lcom/miui/gallery/search/core/result/SuggestionResult;",
            ">;"
        }
    .end annotation

    new-instance v0, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$FilterProcessor;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$FilterProcessor;-><init>(Lcom/miui/gallery/search/resultpage/SearchImageResultHelper;Lcom/miui/gallery/search/resultpage/SearchImageResultHelper$1;)V

    return-object v0
.end method

.method protected getDataLoadCount(Lcom/miui/gallery/search/core/QueryInfo;)I
    .locals 1

    invoke-virtual {p1}, Lcom/miui/gallery/search/core/QueryInfo;->getSearchType()Lcom/miui/gallery/search/SearchConstants$SearchType;

    move-result-object p1

    sget-object v0, Lcom/miui/gallery/search/SearchConstants$SearchType;->SEARCH_TYPE_FEEDBACK_LIKELY_RESULT:Lcom/miui/gallery/search/SearchConstants$SearchType;

    if-ne p1, v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/search/SearchConfig;->get()Lcom/miui/gallery/search/SearchConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/search/SearchConfig;->getResultConfig()Lcom/miui/gallery/search/SearchConfig$ResultConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/search/SearchConfig$ResultConfig;->getLikelyImageLoadCount()I

    move-result p1

    goto :goto_0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/search/SearchConfig;->get()Lcom/miui/gallery/search/SearchConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/search/SearchConfig;->getResultConfig()Lcom/miui/gallery/search/SearchConfig$ResultConfig;

    move-result-object p1

    invoke-virtual {p1}, Lcom/miui/gallery/search/SearchConfig$ResultConfig;->getImageLoadCount()I

    move-result p1

    :goto_0
    return p1
.end method
