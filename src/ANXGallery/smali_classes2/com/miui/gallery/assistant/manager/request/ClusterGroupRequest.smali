.class public Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;
.super Lcom/miui/gallery/assistant/manager/AlgorithmRequest;
.source "ClusterGroupRequest.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/assistant/manager/AlgorithmRequest<",
        "Ljava/util/List<",
        "Lcom/miui/gallery/assistant/model/MediaFeatureItem;",
        ">;",
        "Lcom/miui/gallery/assistant/manager/request/param/ClusteGroupRequestParams<",
        "Lcom/miui/gallery/assistant/model/MediaFeatureItem;",
        ">;",
        "Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;",
        ">;"
    }
.end annotation


# instance fields
.field private mImageFeatureItems:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/miui/gallery/assistant/model/MediaFeatureItem;",
            ">;"
        }
    .end annotation
.end field

.field private mImageFeatures:[Lcom/miui/gallery/assistant/model/ImageFeature;

.field private mIsCalculateClusterFeature:Z

.field private final mSaveResult:Z


# direct methods
.method public constructor <init>(Lcom/miui/gallery/assistant/manager/AlgorithmRequest$Priority;Lcom/miui/gallery/assistant/manager/request/param/ClusteGroupRequestParams;Z)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/assistant/manager/AlgorithmRequest$Priority;",
            "Lcom/miui/gallery/assistant/manager/request/param/ClusteGroupRequestParams<",
            "Lcom/miui/gallery/assistant/model/MediaFeatureItem;",
            ">;Z)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Lcom/miui/gallery/assistant/manager/AlgorithmRequest;-><init>(Lcom/miui/gallery/assistant/manager/AlgorithmRequest$Priority;Lcom/miui/gallery/assistant/manager/request/param/RequestParams;)V

    invoke-virtual {p2}, Lcom/miui/gallery/assistant/manager/request/param/ClusteGroupRequestParams;->isCalculateClusterFeature()Z

    move-result p1

    iput-boolean p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mIsCalculateClusterFeature:Z

    iput-boolean p3, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mSaveResult:Z

    return-void
.end method

.method private allImagesHaveClusterFeature()Z
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/assistant/model/MediaFeatureItem;

    invoke-virtual {v1}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getImageFeature()Lcom/miui/gallery/assistant/model/ImageFeature;

    move-result-object v2

    if-eqz v2, :cond_1

    invoke-virtual {v1}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getImageFeature()Lcom/miui/gallery/assistant/model/ImageFeature;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/assistant/model/ImageFeature;->getClusterFeature()[F

    move-result-object v1

    if-nez v1, :cond_0

    :cond_1
    const/4 v0, 0x0

    return v0

    :cond_2
    const/4 v0, 0x1

    return v0
.end method

.method private generateGroupId(J)J
    .locals 6

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const-wide/32 v2, 0xf4240

    mul-long/2addr v0, v2

    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v4

    sub-long/2addr v4, p1

    rem-long/2addr v4, v2

    add-long/2addr v0, v4

    return-wide v0
.end method


# virtual methods
.method protected bridge synthetic onSaveResult(Lcom/miui/gallery/assistant/manager/result/AlgorithmResult;)V
    .locals 0

    check-cast p1, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->onSaveResult(Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;)V

    return-void
.end method

.method protected onSaveResult(Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;)V
    .locals 14

    iget-boolean v0, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mSaveResult:Z

    if-eqz v0, :cond_7

    if-eqz p1, :cond_7

    invoke-virtual {p1}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;->getResultCode()I

    move-result v0

    if-nez v0, :cond_7

    invoke-virtual {p1}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;->getClusters()Ljava/util/List;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_7

    new-instance v0, Landroid/util/LongSparseArray;

    iget-object v1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    invoke-direct {v0, v1}, Landroid/util/LongSparseArray;-><init>(I)V

    iget-object v1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/assistant/model/MediaFeatureItem;

    invoke-virtual {v2}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getMediaSha1()Ljava/lang/String;

    move-result-object v3

    if-eqz v3, :cond_0

    invoke-virtual {v2}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getMediaId()J

    move-result-wide v3

    invoke-virtual {v0, v3, v4, v2}, Landroid/util/LongSparseArray;->put(JLjava/lang/Object;)V

    goto :goto_0

    :cond_1
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v1

    invoke-virtual {p1}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;->getClusters()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_2
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_7

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/assistant/jni/cluster/Cluster;

    invoke-virtual {v3}, Lcom/miui/gallery/assistant/jni/cluster/Cluster;->getImageIds()[J

    move-result-object v3

    if-eqz v3, :cond_2

    array-length v4, v3

    if-nez v4, :cond_3

    goto :goto_1

    :cond_3
    const-wide/16 v4, -0x1

    array-length v6, v3

    const/4 v7, 0x0

    move v8, v7

    :goto_2
    const-wide/16 v9, 0x0

    if-ge v8, v6, :cond_5

    aget-wide v11, v3, v8

    invoke-virtual {v0, v11, v12}, Landroid/util/LongSparseArray;->get(J)Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/miui/gallery/assistant/model/MediaFeatureItem;

    if-eqz v11, :cond_4

    invoke-virtual {v11}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getImageFeature()Lcom/miui/gallery/assistant/model/ImageFeature;

    move-result-object v12

    if-eqz v12, :cond_4

    invoke-virtual {v11}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getImageFeature()Lcom/miui/gallery/assistant/model/ImageFeature;

    move-result-object v12

    invoke-virtual {v12}, Lcom/miui/gallery/assistant/model/ImageFeature;->getClusterGroupId()J

    move-result-wide v12

    cmp-long v12, v12, v9

    if-lez v12, :cond_4

    invoke-virtual {v11}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getImageFeature()Lcom/miui/gallery/assistant/model/ImageFeature;

    move-result-object v4

    invoke-virtual {v4}, Lcom/miui/gallery/assistant/model/ImageFeature;->getClusterGroupId()J

    move-result-wide v4

    goto :goto_3

    :cond_4
    add-int/lit8 v8, v8, 0x1

    goto :goto_2

    :cond_5
    :goto_3
    cmp-long v6, v4, v9

    if-gez v6, :cond_6

    invoke-direct {p0, v1, v2}, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->generateGroupId(J)J

    move-result-wide v4

    :cond_6
    invoke-static {}, Lcom/miui/gallery/dao/GalleryEntityManager;->getInstance()Lcom/miui/gallery/dao/GalleryEntityManager;

    move-result-object v6

    new-instance v8, Landroid/content/ContentValues;

    invoke-direct {v8}, Landroid/content/ContentValues;-><init>()V

    invoke-static {v4, v5}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v4

    const-string v5, "clusterGroupId"

    invoke-virtual {v8, v5, v4}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/Long;)V

    invoke-static {v3}, Lcom/miui/gallery/util/MiscUtil;->arrayToList([J)Ljava/util/List;

    move-result-object v3

    const/4 v4, 0x2

    new-array v4, v4, [Ljava/lang/Object;

    const-string v5, "imageId"

    aput-object v5, v4, v7

    const-string v5, ","

    invoke-static {v5, v3}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;

    move-result-object v5

    const/4 v7, 0x1

    aput-object v5, v4, v7

    const-string v5, "%s IN (%s)"

    invoke-static {v5, v4}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v4

    const-class v5, Lcom/miui/gallery/assistant/model/ImageFeature;

    const/4 v7, 0x0

    invoke-virtual {v6, v5, v8, v4, v7}, Lcom/miui/gallery/dao/GalleryEntityManager;->update(Ljava/lang/Class;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)Z

    invoke-static {}, Lcom/miui/gallery/assistant/cache/ImageFeatureCacheManager;->getInstance()Lcom/miui/gallery/assistant/cache/ImageFeatureCacheManager;

    move-result-object v4

    invoke-virtual {v4, v3}, Lcom/miui/gallery/assistant/cache/ImageFeatureCacheManager;->onImageFeaturesChanged(Ljava/util/List;)V

    goto/16 :goto_1

    :cond_7
    return-void
.end method

.method protected bridge synthetic process(Ljava/lang/Object;)Lcom/miui/gallery/assistant/manager/result/AlgorithmResult;
    .locals 0

    check-cast p1, Ljava/util/List;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->process(Ljava/util/List;)Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;

    move-result-object p1

    return-object p1
.end method

.method protected process(Ljava/util/List;)Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/assistant/model/MediaFeatureItem;",
            ">;)",
            "Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;"
        }
    .end annotation

    iput-object p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    iget-object p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result p1

    if-eqz p1, :cond_8

    iget-boolean p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mIsCalculateClusterFeature:Z

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    invoke-direct {p0}, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->allImagesHaveClusterFeature()Z

    move-result p1

    if-nez p1, :cond_1

    iput-boolean v0, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mIsCalculateClusterFeature:Z

    iget-object p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/assistant/model/MediaFeatureItem;

    new-instance v1, Lcom/miui/gallery/assistant/manager/request/ClusterFeatureRequest;

    invoke-virtual {p0}, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->getPriority()Lcom/miui/gallery/assistant/manager/AlgorithmRequest$Priority;

    move-result-object v2

    new-instance v3, Lcom/miui/gallery/assistant/manager/request/param/ImageFeatureBgrRequestParams;

    invoke-direct {v3, v0}, Lcom/miui/gallery/assistant/manager/request/param/ImageFeatureBgrRequestParams;-><init>(Lcom/miui/gallery/assistant/model/MediaFeatureItem;)V

    invoke-direct {v1, v2, v3}, Lcom/miui/gallery/assistant/manager/request/ClusterFeatureRequest;-><init>(Lcom/miui/gallery/assistant/manager/AlgorithmRequest$Priority;Lcom/miui/gallery/assistant/manager/request/param/ImageFeatureBgrRequestParams;)V

    invoke-virtual {v1}, Lcom/miui/gallery/assistant/manager/request/ClusterFeatureRequest;->execute()V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->execute()V

    iget-object p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->TAG:Ljava/lang/String;

    const-string v0, "calculate cluster feature before group"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    new-instance p1, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;

    const/4 v0, 0x6

    invoke-direct {p1, v0}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;-><init>(I)V

    return-object p1

    :cond_1
    iget-object p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->TAG:Ljava/lang/String;

    iget-object v1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "Process %d images"

    invoke-static {p1, v2, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    move p1, v0

    move v1, p1

    :goto_1
    iget-object v2, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    if-ge p1, v2, :cond_3

    iget-object v2, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v2, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/assistant/model/MediaFeatureItem;

    invoke-virtual {v2}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getImageFeature()Lcom/miui/gallery/assistant/model/ImageFeature;

    move-result-object v2

    if-eqz v2, :cond_2

    invoke-virtual {v2}, Lcom/miui/gallery/assistant/model/ImageFeature;->getClusterFeature()[F

    move-result-object v2

    if-eqz v2, :cond_2

    add-int/lit8 v1, v1, 0x1

    :cond_2
    add-int/lit8 p1, p1, 0x1

    goto :goto_1

    :cond_3
    iget-object p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->TAG:Ljava/lang/String;

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const-string v3, "Valid image count:%d"

    invoke-static {p1, v3, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    mul-int/lit16 p1, v1, 0x400

    new-array p1, p1, [F

    new-array v2, v1, [J

    new-array v3, v1, [J

    new-array v4, v1, [Lcom/miui/gallery/assistant/model/ImageFeature;

    iput-object v4, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatures:[Lcom/miui/gallery/assistant/model/ImageFeature;

    move v4, v0

    move v5, v4

    :goto_2
    iget-object v6, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v6}, Ljava/util/List;->size()I

    move-result v6

    if-ge v4, v6, :cond_5

    if-ge v5, v1, :cond_5

    iget-object v6, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v6, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/miui/gallery/assistant/model/MediaFeatureItem;

    invoke-virtual {v6}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getImageFeature()Lcom/miui/gallery/assistant/model/ImageFeature;

    move-result-object v6

    if-eqz v6, :cond_4

    invoke-virtual {v6}, Lcom/miui/gallery/assistant/model/ImageFeature;->getClusterFeature()[F

    move-result-object v7

    if-eqz v7, :cond_4

    iget-object v7, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatures:[Lcom/miui/gallery/assistant/model/ImageFeature;

    aput-object v6, v7, v5

    invoke-virtual {v6}, Lcom/miui/gallery/assistant/model/ImageFeature;->getClusterFeature()[F

    move-result-object v7

    mul-int/lit16 v8, v5, 0x400

    const/16 v9, 0x400

    invoke-static {v7, v0, p1, v8, v9}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    iget-object v7, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->mImageFeatureItems:Ljava/util/List;

    invoke-interface {v7, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/miui/gallery/assistant/model/MediaFeatureItem;

    invoke-virtual {v7}, Lcom/miui/gallery/assistant/model/MediaFeatureItem;->getDateTime()J

    move-result-wide v7

    aput-wide v7, v2, v5

    invoke-virtual {v6}, Lcom/miui/gallery/assistant/model/ImageFeature;->getImageId()J

    move-result-wide v6

    aput-wide v6, v3, v5

    add-int/lit8 v5, v5, 0x1

    :cond_4
    add-int/lit8 v4, v4, 0x1

    goto :goto_2

    :cond_5
    invoke-static {}, Lcom/miui/gallery/assistant/library/LibraryManager;->getInstance()Lcom/miui/gallery/assistant/library/LibraryManager;

    move-result-object v0

    const-wide/32 v4, 0xf51e1

    invoke-virtual {v0, v4, v5}, Lcom/miui/gallery/assistant/library/LibraryManager;->loadLibrary(J)Z

    move-result v0

    if-eqz v0, :cond_7

    const/16 v0, 0x8

    invoke-static {v0}, Lcom/miui/gallery/assistant/algorithm/AlgorithmFactroy;->getAlgorithmByFlag(I)Lcom/miui/gallery/assistant/algorithm/Algorithm;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/assistant/algorithm/ClusterAlgorithm;

    array-length v1, v3

    invoke-virtual {v0, p1, v2, v3, v1}, Lcom/miui/gallery/assistant/algorithm/ClusterAlgorithm;->getClusterResult([F[J[JI)Ljava/util/ArrayList;

    move-result-object p1

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v0

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->TAG:Ljava/lang/String;

    const-string v1, "ClusterAlgorithm execute success!"

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    new-instance v0, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;

    invoke-direct {v0, p1}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;-><init>(Ljava/util/ArrayList;)V

    return-object v0

    :cond_6
    new-instance p1, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;

    const/4 v0, 0x1

    invoke-direct {p1, v0}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;-><init>(I)V

    return-object p1

    :cond_7
    iget-object p1, p0, Lcom/miui/gallery/assistant/manager/request/ClusterGroupRequest;->TAG:Ljava/lang/String;

    const-string v0, "Load library %s failed"

    const-string v1, "ClusterAlgorithm"

    invoke-static {p1, v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    new-instance p1, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;

    const/4 v0, 0x2

    invoke-direct {p1, v0}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;-><init>(I)V

    return-object p1

    :cond_8
    new-instance p1, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;

    const/4 v0, 0x3

    invoke-direct {p1, v0}, Lcom/miui/gallery/assistant/manager/result/ClusteGroupResult;-><init>(I)V

    return-object p1
.end method
