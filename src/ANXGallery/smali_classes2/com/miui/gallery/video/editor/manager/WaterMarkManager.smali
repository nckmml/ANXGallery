.class public Lcom/miui/gallery/video/editor/manager/WaterMarkManager;
.super Ljava/lang/Object;
.source "WaterMarkManager.java"


# static fields
.field private static final sBgColor:[I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/16 v0, 0x8

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Lcom/miui/gallery/video/editor/manager/WaterMarkManager;->sBgColor:[I

    return-void

    :array_0
    .array-data 4
        0x7f0703e1
        0x7f0703e2
        0x7f0703e3
        0x7f0703e4
        0x7f0703e5
        0x7f0703e6
        0x7f0703e7
        0x7f0703e8
    .end array-data
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static getLocalCustomTextStyle()Lcom/miui/gallery/video/editor/TextStyle;
    .locals 3

    new-instance v0, Lcom/miui/gallery/video/editor/TextStyle;

    const v1, 0x7f0703e9

    const-string v2, "ve_type_local"

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/video/editor/TextStyle;-><init>(ILjava/lang/String;)V

    const/16 v1, 0x11

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/TextStyle;->setDownloadState(I)V

    return-object v0
.end method

.method private static getLocalNoneTextStyle()Lcom/miui/gallery/video/editor/TextStyle;
    .locals 3

    new-instance v0, Lcom/miui/gallery/video/editor/TextStyle;

    const v1, 0x7f0703ce

    const-string v2, "ve_type_none"

    invoke-direct {v0, v1, v2}, Lcom/miui/gallery/video/editor/TextStyle;-><init>(ILjava/lang/String;)V

    const/16 v1, 0x11

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/TextStyle;->setDownloadState(I)V

    return-object v0
.end method

.method public static initDataWithBgColor()Ljava/util/ArrayList;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/video/editor/TextStyle;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/WaterMarkManager;->getLocalNoneTextStyle()Lcom/miui/gallery/video/editor/TextStyle;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/WaterMarkManager;->getLocalCustomTextStyle()Lcom/miui/gallery/video/editor/TextStyle;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    sget-object v1, Lcom/miui/gallery/video/editor/manager/WaterMarkManager;->sBgColor:[I

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_0

    aget v4, v1, v3

    new-instance v5, Lcom/miui/gallery/video/editor/TextStyle;

    invoke-direct {v5}, Lcom/miui/gallery/video/editor/TextStyle;-><init>()V

    invoke-virtual {v5, v4}, Lcom/miui/gallery/video/editor/TextStyle;->setIconResId(I)V

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_0
    return-object v0
.end method

.method public static loadWaterMarks(Ljava/util/List;)Ljava/util/ArrayList;
    .locals 8
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/editor/model/LocalResource;",
            ">;)",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/video/editor/TextStyle;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-static {p0}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v1

    if-nez v1, :cond_0

    return-object v0

    :cond_0
    const/4 v1, 0x0

    move v2, v1

    :goto_0
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v3

    if-ge v2, v3, :cond_8

    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/miui/gallery/video/editor/model/LocalResource;

    if-nez v3, :cond_1

    goto :goto_3

    :cond_1
    new-instance v4, Lcom/miui/gallery/video/editor/TextStyle;

    invoke-direct {v4, v3}, Lcom/miui/gallery/video/editor/TextStyle;-><init>(Lcom/miui/gallery/video/editor/model/LocalResource;)V

    invoke-virtual {v4}, Lcom/miui/gallery/video/editor/TextStyle;->isNone()Z

    move-result v5

    if-eqz v5, :cond_2

    const v5, 0x7f0703ce

    invoke-virtual {v4, v5}, Lcom/miui/gallery/video/editor/TextStyle;->setIconResId(I)V

    goto :goto_2

    :cond_2
    invoke-virtual {v4}, Lcom/miui/gallery/video/editor/TextStyle;->isLocal()Z

    move-result v5

    if-eqz v5, :cond_3

    const v5, 0x7f0703e9

    invoke-virtual {v4, v5}, Lcom/miui/gallery/video/editor/TextStyle;->setIconResId(I)V

    goto :goto_2

    :cond_3
    invoke-virtual {v4}, Lcom/miui/gallery/video/editor/TextStyle;->isExtra()Z

    move-result v5

    if-eqz v5, :cond_5

    sget-object v5, Lcom/miui/gallery/video/editor/manager/WaterMarkManager;->sBgColor:[I

    const/4 v6, 0x2

    if-lt v2, v6, :cond_4

    add-int/lit8 v6, v2, -0x2

    array-length v7, v5

    if-ge v6, v7, :cond_4

    goto :goto_1

    :cond_4
    move v6, v1

    :goto_1
    aget v5, v5, v6

    invoke-virtual {v4, v5}, Lcom/miui/gallery/video/editor/TextStyle;->setBgColor(I)V

    :cond_5
    :goto_2
    invoke-static {}, Lcom/miui/gallery/util/BuildUtil;->isInternational()Z

    move-result v5

    if-eqz v5, :cond_6

    invoke-virtual {v3}, Lcom/miui/gallery/video/editor/model/LocalResource;->isInternational()Z

    move-result v3

    if-eqz v3, :cond_7

    :cond_6
    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_7
    :goto_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_8
    return-object v0
.end method

.method public static updateDataWithTemplate([Ljava/lang/String;Lcom/miui/gallery/video/editor/TextStyle;)V
    .locals 4

    if-eqz p0, :cond_2

    if-nez p1, :cond_0

    goto :goto_1

    :cond_0
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    array-length v2, p0

    if-ge v1, v2, :cond_2

    aget-object v2, p0, v1

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/TextStyle;->getAssetName()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_1

    invoke-virtual {p1}, Lcom/miui/gallery/video/editor/TextStyle;->getAssetName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-virtual {p1, v0}, Lcom/miui/gallery/video/editor/TextStyle;->setDownloadState(I)V

    invoke-virtual {p1, v2}, Lcom/miui/gallery/video/editor/TextStyle;->setTemplateId(Ljava/lang/String;)V

    return-void

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    :goto_1
    return-void
.end method


# virtual methods
.method public initDataWithTemplate([Ljava/lang/String;Ljava/util/ArrayList;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([",
            "Ljava/lang/String;",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/video/editor/TextStyle;",
            ">;)V"
        }
    .end annotation

    if-eqz p1, :cond_5

    if-nez p2, :cond_0

    goto :goto_3

    :cond_0
    const/4 v0, 0x0

    move v1, v0

    :goto_0
    array-length v2, p1

    if-ge v1, v2, :cond_5

    aget-object v2, p1, v1

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_4

    invoke-virtual {p2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_1
    :goto_1
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_3

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/miui/gallery/video/editor/TextStyle;

    if-nez v4, :cond_2

    goto :goto_1

    :cond_2
    invoke-virtual {v4}, Lcom/miui/gallery/video/editor/TextStyle;->getAssetName()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v5

    if-nez v5, :cond_1

    invoke-virtual {v4}, Lcom/miui/gallery/video/editor/TextStyle;->getAssetName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v5}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v5

    if-eqz v5, :cond_1

    const/16 v3, 0x11

    invoke-virtual {v4, v3}, Lcom/miui/gallery/video/editor/TextStyle;->setDownloadState(I)V

    invoke-virtual {v4, v2}, Lcom/miui/gallery/video/editor/TextStyle;->setTemplateId(Ljava/lang/String;)V

    move v3, v0

    goto :goto_2

    :cond_3
    const/4 v3, 0x1

    :goto_2
    if-eqz v3, :cond_4

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->getInstance()Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;

    move-result-object v3

    invoke-virtual {v3, v2}, Lcom/miui/gallery/video/editor/manager/NexAssetTemplateManager;->uninstallPackageById(Ljava/lang/String;)V

    :cond_4
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_5
    :goto_3
    return-void
.end method
