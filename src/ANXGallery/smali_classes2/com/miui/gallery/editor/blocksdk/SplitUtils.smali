.class public Lcom/miui/gallery/editor/blocksdk/SplitUtils;
.super Ljava/lang/Object;
.source "SplitUtils.java"


# direct methods
.method private static ceil(II)I
    .locals 1

    div-int v0, p0, p1

    rem-int/2addr p0, p1

    if-nez p0, :cond_0

    const/4 p0, 0x0

    goto :goto_0

    :cond_0
    const/4 p0, 0x1

    :goto_0
    add-int/2addr v0, p0

    return v0
.end method

.method private static floor(II)I
    .locals 0

    rem-int/2addr p0, p1

    if-nez p0, :cond_0

    move p0, p1

    :cond_0
    return p0
.end method

.method public static final split(II)Ljava/util/List;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(II)",
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/blocksdk/Block;",
            ">;"
        }
    .end annotation

    const/4 v0, 0x1

    const/4 v1, 0x0

    invoke-static {p0, p1, v0, v0, v1}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->split(IIIZZ)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public static split(IIIZZ)Ljava/util/List;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(IIIZZ)",
            "Ljava/util/List<",
            "Lcom/miui/gallery/editor/blocksdk/Block;",
            ">;"
        }
    .end annotation

    mul-int v0, p0, p1

    const v1, 0x5b8d80

    if-gt v0, v1, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    new-instance v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;

    invoke-direct {v0}, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;-><init>()V

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    if-eqz p3, :cond_3

    invoke-static {}, Lcom/miui/gallery/util/GLESUtils;->getMaxTextureSize()I

    move-result p3

    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const-string v4, "SplitUtils"

    const-string v5, "GL_MAX_TEXTURE_SIZE %d"

    invoke-static {v4, v5, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    move v3, p0

    :goto_0
    if-le v3, p3, :cond_1

    const/4 v4, 0x2

    invoke-static {v3, v4}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->ceil(II)I

    move-result v3

    goto :goto_0

    :cond_1
    if-ge v3, p0, :cond_2

    rem-int p3, v3, p2

    if-eqz p3, :cond_2

    sub-int p3, p2, p3

    add-int/2addr p3, v3

    goto :goto_1

    :cond_2
    move p3, v3

    goto :goto_1

    :cond_3
    move p3, p0

    :goto_1
    invoke-static {p0, p3}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->ceil(II)I

    move-result v3

    mul-int v4, p3, p1

    if-eqz p4, :cond_4

    const v1, 0xb71b00

    :cond_4
    invoke-static {v4, v1}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->ceil(II)I

    move-result p4

    invoke-static {p1, p4}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->ceil(II)I

    move-result p4

    if-ge p4, p1, :cond_5

    rem-int v1, p4, p2

    if-eqz v1, :cond_5

    sub-int/2addr p2, v1

    add-int/2addr p4, p2

    :cond_5
    invoke-static {p1, p4}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->ceil(II)I

    move-result p2

    iput p1, v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mTotalHeight:I

    iput p0, v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mTotalWidth:I

    iput v3, v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mTotalColumn:I

    iput p2, v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mTotalRow:I

    iput p3, v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mBlockWidth:I

    iput p4, v0, Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;->mBlockHeight:I

    const/4 v1, 0x0

    move v4, v1

    :goto_2
    if-ge v4, p2, :cond_b

    move v5, v1

    :goto_3
    if-ge v5, v3, :cond_a

    new-instance v6, Lcom/miui/gallery/editor/blocksdk/Block;

    invoke-direct {v6}, Lcom/miui/gallery/editor/blocksdk/Block;-><init>()V

    if-nez v5, :cond_6

    invoke-static {p0, p3}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->floor(II)I

    move-result v7

    iput v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mWidth:I

    goto :goto_4

    :cond_6
    iput p3, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mWidth:I

    :goto_4
    if-nez v4, :cond_7

    invoke-static {p1, p4}, Lcom/miui/gallery/editor/blocksdk/SplitUtils;->floor(II)I

    move-result v7

    iput v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mHeight:I

    goto :goto_5

    :cond_7
    iput p4, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mHeight:I

    :goto_5
    iput v4, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mRow:I

    iput v5, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mColumn:I

    iget v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mColumn:I

    if-lez v7, :cond_8

    iget v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mOffset:I

    invoke-interface {v2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lcom/miui/gallery/editor/blocksdk/Block;

    iget v8, v8, Lcom/miui/gallery/editor/blocksdk/Block;->mWidth:I

    iget v9, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mColumn:I

    add-int/lit8 v9, v9, -0x1

    mul-int/2addr v9, p3

    add-int/2addr v8, v9

    add-int/2addr v7, v8

    iput v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mOffset:I

    :cond_8
    iget v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mRow:I

    if-lez v7, :cond_9

    iget v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mOffset:I

    invoke-interface {v2, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lcom/miui/gallery/editor/blocksdk/Block;

    iget v8, v8, Lcom/miui/gallery/editor/blocksdk/Block;->mHeight:I

    iget v9, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mRow:I

    add-int/lit8 v9, v9, -0x1

    mul-int/2addr v9, p4

    add-int/2addr v8, v9

    mul-int/2addr v8, p0

    add-int/2addr v7, v8

    iput v7, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mOffset:I

    :cond_9
    iput-object v0, v6, Lcom/miui/gallery/editor/blocksdk/Block;->mTotalBlockInfo:Lcom/miui/gallery/editor/blocksdk/Block$TotalBlockInfo;

    invoke-interface {v2, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    add-int/lit8 v5, v5, 0x1

    goto :goto_3

    :cond_a
    add-int/lit8 v4, v4, 0x1

    goto :goto_2

    :cond_b
    return-object v2
.end method
