.class public Lcom/miui/gallery/reddot/DisplayStatusManager;
.super Ljava/lang/Object;
.source "DisplayStatusManager.java"


# static fields
.field private static TAG:Ljava/lang/String; = "DisplayStatusManager"

.field private static sDisplayStatus:I

.field private static sKeyPositionMap:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private static final sRedDotMap:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/reddot/RedDot;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sRedDotMap:Ljava/util/Map;

    new-instance v0, Lcom/miui/gallery/reddot/DisplayStatusManager$1;

    invoke-direct {v0}, Lcom/miui/gallery/reddot/DisplayStatusManager$1;-><init>()V

    sput-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sKeyPositionMap:Ljava/util/Map;

    return-void
.end method

.method private static flatMap(Lcom/miui/gallery/reddot/RedDot;)V
    .locals 2

    sget-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sRedDotMap:Ljava/util/Map;

    invoke-virtual {p0}, Lcom/miui/gallery/reddot/RedDot;->getKey()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v1, p0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    instance-of v0, p0, Lcom/miui/gallery/reddot/RedDotAtom;

    if-eqz v0, :cond_0

    return-void

    :cond_0
    instance-of v0, p0, Lcom/miui/gallery/reddot/RedDotGroup;

    if-eqz v0, :cond_1

    check-cast p0, Lcom/miui/gallery/reddot/RedDotGroup;

    iget-object p0, p0, Lcom/miui/gallery/reddot/RedDotGroup;->mPriorityGroup:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/reddot/RedDot;

    invoke-static {v0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->flatMap(Lcom/miui/gallery/reddot/RedDot;)V

    goto :goto_0

    :cond_1
    return-void
.end method

.method private static generateRedDotMap()V
    .locals 13

    new-instance v0, Lcom/miui/gallery/reddot/RedDotAtom;

    const/4 v1, 0x1

    const/4 v2, 0x0

    const-string v3, "collage"

    invoke-direct {v0, v3, v1, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v3, Lcom/miui/gallery/reddot/RedDotAtom;

    const-string v4, "photo_movie"

    invoke-direct {v3, v4, v1, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v4, Lcom/miui/gallery/reddot/RedDotAtom;

    const-string v5, "photo_cleaner"

    invoke-direct {v4, v5, v1, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v5, Lcom/miui/gallery/reddot/RedDotAtom;

    const-string v6, "trash_bin"

    invoke-direct {v5, v6, v2, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v6, Lcom/miui/gallery/reddot/RedDotAtom;

    const-string v7, "photo_print"

    invoke-direct {v6, v7, v1, v1}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v7, Lcom/miui/gallery/reddot/RedDotAtom;

    const-string v8, "settings"

    invoke-direct {v7, v8, v2, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    invoke-static {}, Lcom/miui/gallery/adapter/AssistantPageHeaderAdapter;->replaceRecommend()Z

    move-result v8

    const-string v9, "filter_sky"

    const-string v10, "recommendation"

    if-eqz v8, :cond_0

    new-instance v8, Lcom/miui/gallery/reddot/RedDotAtom;

    invoke-direct {v8, v10, v2, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v10, Lcom/miui/gallery/reddot/RedDotAtom;

    invoke-direct {v10, v9, v1, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    goto :goto_0

    :cond_0
    new-instance v8, Lcom/miui/gallery/reddot/RedDotAtom;

    invoke-direct {v8, v10, v1, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v10, Lcom/miui/gallery/reddot/RedDotAtom;

    invoke-direct {v10, v9, v2, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    :goto_0
    new-instance v9, Lcom/miui/gallery/reddot/RedDotAtom;

    const-string v11, "story_album"

    invoke-direct {v9, v11, v2, v2}, Lcom/miui/gallery/reddot/RedDotAtom;-><init>(Ljava/lang/String;ZZ)V

    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isDeviceSupportStoryFunction()Z

    move-result v11

    const-string v12, "action_bar_more"

    if-eqz v11, :cond_3

    new-instance v11, Ljava/util/ArrayList;

    invoke-direct {v11}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {v11, v10}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v11, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v11, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v11, v8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v11, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v0, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/util/deviceprovider/ApplicationHelper;->isCloudTrashBinFeatureOpen()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v3

    invoke-static {v3}, Lcom/miui/gallery/util/SyncUtil;->existXiaomiAccount(Landroid/content/Context;)Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_1
    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object v3

    invoke-virtual {v3}, Lcom/miui/gallery/util/PrintInstallHelper;->isPhotoPrintEnable()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-virtual {v0, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    new-instance v3, Lcom/miui/gallery/reddot/RedDotGroup;

    invoke-direct {v3, v12, v0, v1}, Lcom/miui/gallery/reddot/RedDotGroup;-><init>(Ljava/lang/String;Ljava/util/List;I)V

    new-instance v0, Lcom/miui/gallery/reddot/RedDotGroup;

    const/4 v4, 0x2

    const-string v5, "assistant_tab"

    invoke-direct {v0, v5, v11, v4}, Lcom/miui/gallery/reddot/RedDotGroup;-><init>(Ljava/lang/String;Ljava/util/List;I)V

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_1

    :cond_3
    new-instance v8, Ljava/util/ArrayList;

    invoke-direct {v8}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lcom/miui/gallery/util/PhotoMovieEntranceUtils;->isPhotoMovieAvailable()Z

    move-result v9

    if-eqz v9, :cond_4

    invoke-virtual {v8, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_4
    invoke-virtual {v8, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-virtual {v8, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/util/PrintInstallHelper;->getInstance()Lcom/miui/gallery/util/PrintInstallHelper;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/util/PrintInstallHelper;->isPhotoPrintEnable()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-virtual {v8, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_5
    invoke-static {}, Lcom/miui/gallery/util/deviceprovider/ApplicationHelper;->isCloudTrashBinFeatureOpen()Z

    move-result v0

    if-eqz v0, :cond_6

    invoke-static {}, Lcom/miui/gallery/GalleryApp;->sGetAndroidContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/util/SyncUtil;->existXiaomiAccount(Landroid/content/Context;)Z

    move-result v0

    if-eqz v0, :cond_6

    invoke-virtual {v8, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_6
    invoke-virtual {v8, v7}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    new-instance v0, Lcom/miui/gallery/reddot/RedDotGroup;

    const/4 v3, 0x4

    invoke-direct {v0, v12, v8, v3}, Lcom/miui/gallery/reddot/RedDotGroup;-><init>(Ljava/lang/String;Ljava/util/List;I)V

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_1
    new-instance v0, Lcom/miui/gallery/reddot/RedDotGroup;

    const-string v3, "root"

    invoke-direct {v0, v3, v2, v1}, Lcom/miui/gallery/reddot/RedDotGroup;-><init>(Ljava/lang/String;Ljava/util/List;I)V

    invoke-static {v0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->flatMap(Lcom/miui/gallery/reddot/RedDot;)V

    return-void
.end method

.method protected static getDisplayStatus(Z)I
    .locals 1

    if-eqz p0, :cond_0

    invoke-static {}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getRedDotMap()Ljava/util/Map;

    move-result-object p0

    const-string v0, "root"

    invoke-interface {p0, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/reddot/RedDot;

    invoke-virtual {p0}, Lcom/miui/gallery/reddot/RedDot;->processDisplayStatus()Z

    :cond_0
    sget p0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    return p0
.end method

.method private static getRedDotMap()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/reddot/RedDot;",
            ">;"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sRedDotMap:Ljava/util/Map;

    if-eqz v0, :cond_0

    invoke-interface {v0}, Ljava/util/Map;->size()I

    move-result v0

    if-eqz v0, :cond_0

    sget-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sRedDotMap:Ljava/util/Map;

    return-object v0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/reddot/DisplayStatusManager;->generateRedDotMap()V

    sget-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sRedDotMap:Ljava/util/Map;

    return-object v0
.end method

.method public static getRedDotStatus(Ljava/lang/String;)Z
    .locals 1

    const/4 v0, 0x1

    invoke-static {p0, v0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getRedDotStatus(Ljava/lang/String;Z)Z

    move-result p0

    return p0
.end method

.method protected static getRedDotStatus(Ljava/lang/String;Z)Z
    .locals 0

    invoke-static {p0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->keyToPosition(Ljava/lang/String;)I

    move-result p0

    invoke-static {p1}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getDisplayStatus(Z)I

    move-result p1

    and-int/2addr p0, p1

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method private static isRedDotShown(Ljava/lang/String;)Z
    .locals 8

    sget-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sRedDotMap:Ljava/util/Map;

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/reddot/RedDot;

    instance-of v2, v0, Lcom/miui/gallery/reddot/RedDotAtom;

    const/4 v3, 0x1

    if-eqz v2, :cond_2

    invoke-static {p0}, Lcom/miui/gallery/preference/GalleryPreferences$FeatureRedDot;->getRedDotSawTime(Ljava/lang/String;)J

    move-result-wide v4

    const-wide/16 v6, 0x0

    cmp-long p0, v4, v6

    if-lez p0, :cond_1

    move v1, v3

    :cond_1
    return v1

    :cond_2
    instance-of p0, v0, Lcom/miui/gallery/reddot/RedDotGroup;

    if-eqz p0, :cond_4

    check-cast v0, Lcom/miui/gallery/reddot/RedDotGroup;

    iget-object p0, v0, Lcom/miui/gallery/reddot/RedDotGroup;->mPriorityGroup:Ljava/util/List;

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_3
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/gallery/reddot/RedDot;

    invoke-virtual {v0}, Lcom/miui/gallery/reddot/RedDot;->getKey()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->isRedDotShown(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    return v3

    :cond_4
    return v1
.end method

.method private static keyToPosition(Ljava/lang/String;)I
    .locals 1

    sget-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sKeyPositionMap:Ljava/util/Map;

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0
.end method

.method protected static reddenDot(Ljava/lang/String;)Z
    .locals 7

    invoke-static {p0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->keyToPosition(Ljava/lang/String;)I

    move-result v0

    sget v1, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    and-int/2addr v1, v0

    const/4 v2, 0x1

    if-nez v1, :cond_2

    invoke-static {p0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->isRedDotShown(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_0

    sget p0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    or-int/2addr p0, v0

    sput p0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    return v2

    :cond_0
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$FeatureRedDot;->getLastUnreddenTime()J

    move-result-wide v5

    sub-long/2addr v3, v5

    const-wide/32 v5, 0x6ddd00

    cmp-long p0, v3, v5

    if-lez p0, :cond_1

    sget p0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    or-int/2addr p0, v0

    sput p0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    const-wide/16 v0, 0x0

    invoke-static {v0, v1}, Lcom/miui/gallery/preference/GalleryPreferences$FeatureRedDot;->setLastUnreddenTime(J)V

    return v2

    :cond_1
    const/4 p0, 0x0

    return p0

    :cond_2
    return v2
.end method

.method public static regenerateRedDotMap()V
    .locals 1

    sget-object v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sRedDotMap:Ljava/util/Map;

    if-eqz v0, :cond_0

    invoke-static {}, Lcom/miui/gallery/reddot/DisplayStatusManager;->generateRedDotMap()V

    :cond_0
    return-void
.end method

.method public static setRedDotClicked(Ljava/lang/String;)V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getRedDotMap()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/reddot/RedDot;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/reddot/RedDot;->onClick()V

    goto :goto_0

    :cond_0
    sget-object p0, Lcom/miui/gallery/reddot/DisplayStatusManager;->TAG:Ljava/lang/String;

    const-string v0, "get a null red dot from red dot map"

    invoke-static {p0, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)V

    :goto_0
    return-void
.end method

.method protected static unreddenDot(Ljava/lang/String;)V
    .locals 4

    invoke-static {p0}, Lcom/miui/gallery/reddot/DisplayStatusManager;->keyToPosition(Ljava/lang/String;)I

    move-result p0

    sget v0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    and-int v1, v0, p0

    if-eqz v1, :cond_0

    not-int p0, p0

    and-int/2addr p0, v0

    sput p0, Lcom/miui/gallery/reddot/DisplayStatusManager;->sDisplayStatus:I

    invoke-static {}, Lcom/miui/gallery/preference/GalleryPreferences$FeatureRedDot;->getLastUnreddenTime()J

    move-result-wide v0

    const-wide/16 v2, 0x0

    cmp-long p0, v0, v2

    if-nez p0, :cond_0

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/miui/gallery/preference/GalleryPreferences$FeatureRedDot;->setLastUnreddenTime(J)V

    :cond_0
    return-void
.end method

.method public static updateFeature(Ljava/lang/String;)V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/reddot/DisplayStatusManager;->getRedDotMap()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/reddot/RedDot;

    invoke-virtual {p0}, Lcom/miui/gallery/reddot/RedDot;->onUpdate()V

    return-void
.end method
