.class public Lcom/miui/gallery/provider/SettingsSearchProvider;
.super Landroid/content/ContentProvider;
.source "SettingsSearchProvider.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;,
        Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;
    }
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/content/ContentProvider;-><init>()V

    return-void
.end method


# virtual methods
.method public delete(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    .locals 0

    const/4 p1, 0x0

    return p1
.end method

.method public getType(Landroid/net/Uri;)Ljava/lang/String;
    .locals 0

    const/4 p1, 0x0

    return-object p1
.end method

.method public insert(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    .locals 0

    const/4 p1, 0x0

    return-object p1
.end method

.method public onCreate()Z
    .locals 1

    const/4 v0, 0x1

    return v0
.end method

.method public prepareData()Ljava/util/List;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/LinkedList;

    invoke-direct {v0}, Ljava/util/LinkedList;-><init>()V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x7f020004

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getStringArray(I)[Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    const/4 v3, 0x0

    invoke-direct {v2, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v4

    const v5, 0x7f1000bf

    invoke-virtual {v4, v5}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v2

    const-string v4, ";"

    invoke-static {v4, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setKeywords(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/ui/AIAlbumStatusHelper;->useNewAIAlbumSwitch()Z

    move-result v1

    if-eqz v1, :cond_0

    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v5, 0x7f100051

    invoke-virtual {v2, v5}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v5, 0x7f10004f

    invoke-virtual {v2, v5}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setKeywords(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_0
    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v5, 0x7f10009d

    invoke-virtual {v2, v5}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    const/4 v1, 0x2

    new-array v1, v1, [Ljava/lang/String;

    const/4 v2, 0x0

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v5

    invoke-virtual {v5}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v5

    const v6, 0x7f1003a9

    invoke-virtual {v5, v6}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v5

    aput-object v5, v1, v2

    const/4 v2, 0x1

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v5

    invoke-virtual {v5}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v5

    const v6, 0x7f1003ab

    invoke-virtual {v5, v6}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v5

    aput-object v5, v1, v2

    new-instance v2, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v2, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v5

    const v6, 0x7f1003ad

    invoke-virtual {v5, v6}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v5}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v2

    invoke-static {v4, v1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setKeywords(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isImageFeatureCalculationEnable()Z

    move-result v1

    if-eqz v1, :cond_1

    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v4, 0x7f10050e

    invoke-virtual {v2, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/assistant/manager/ImageFeatureManager;->isDeviceSupportStoryFunction()Z

    move-result v1

    if-eqz v1, :cond_1

    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v4, 0x7f1004dc

    invoke-virtual {v2, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_1
    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v4, 0x7f10007e

    invoke-virtual {v2, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v4, 0x7f1007be

    invoke-virtual {v2, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v2

    const v4, 0x7f1007c4

    invoke-virtual {v2, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    invoke-static {}, Lcom/miui/gallery/util/deviceprovider/ApplicationHelper;->isCloudTrashBinFeatureOpen()Z

    move-result v1

    const-string v2, "android.intent.action.VIEW"

    if-eqz v1, :cond_2

    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v4

    const v5, 0x7f10086a

    invoke-virtual {v4, v5}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v4

    const v5, 0x7f10086b

    invoke-virtual {v4, v5}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setKeywords(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setIntentAction(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-static {}, Lcom/miui/gallery/request/HostManager;->getTrashBinUrl()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setUriString(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_2
    new-instance v1, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    invoke-direct {v1, p0, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;-><init>(Lcom/miui/gallery/provider/SettingsSearchProvider;Lcom/miui/gallery/provider/SettingsSearchProvider$1;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->getContext()Landroid/content/Context;

    move-result-object v3

    const v4, 0x7f100881

    invoke-virtual {v3, v4}, Landroid/content/Context;->getString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v3}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setTitle(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setIntentAction(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    const-string v2, ""

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setIntentTargetPackage(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-static {}, Lcom/miui/gallery/agreement/cta/CtaAgreement$Licence;->getPrivacyUrl()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->setUriString(Ljava/lang/String;)Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lcom/miui/gallery/provider/SettingsSearchProvider$Builder;->build()Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    return-object v0
.end method

.method public query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    .locals 1

    new-instance p1, Landroid/database/MatrixCursor;

    sget-object p2, Lcom/miui/gallery/provider/SettingsSearchContract;->SEARCH_RESULT_COLUMNS:[Ljava/lang/String;

    invoke-direct {p1, p2}, Landroid/database/MatrixCursor;-><init>([Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/miui/gallery/provider/SettingsSearchProvider;->prepareData()Ljava/util/List;

    move-result-object p2

    invoke-interface {p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result p3

    if-eqz p3, :cond_0

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;

    invoke-virtual {p1}, Landroid/database/MatrixCursor;->newRow()Landroid/database/MatrixCursor$RowBuilder;

    move-result-object p4

    iget-object p5, p3, Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;->title:Ljava/lang/String;

    const-string v0, "title"

    invoke-virtual {p4, v0, p5}, Landroid/database/MatrixCursor$RowBuilder;->add(Ljava/lang/String;Ljava/lang/Object;)Landroid/database/MatrixCursor$RowBuilder;

    move-result-object p4

    iget-object p5, p3, Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;->keywords:Ljava/lang/String;

    const-string v0, "keywords"

    invoke-virtual {p4, v0, p5}, Landroid/database/MatrixCursor$RowBuilder;->add(Ljava/lang/String;Ljava/lang/Object;)Landroid/database/MatrixCursor$RowBuilder;

    move-result-object p4

    iget-object p5, p3, Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;->intentAction:Ljava/lang/String;

    const-string v0, "intentAction"

    invoke-virtual {p4, v0, p5}, Landroid/database/MatrixCursor$RowBuilder;->add(Ljava/lang/String;Ljava/lang/Object;)Landroid/database/MatrixCursor$RowBuilder;

    move-result-object p4

    iget-object p5, p3, Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;->intentTargetPackage:Ljava/lang/String;

    const-string v0, "intentTargetPackage"

    invoke-virtual {p4, v0, p5}, Landroid/database/MatrixCursor$RowBuilder;->add(Ljava/lang/String;Ljava/lang/Object;)Landroid/database/MatrixCursor$RowBuilder;

    move-result-object p4

    iget-object p3, p3, Lcom/miui/gallery/provider/SettingsSearchProvider$RawData;->uriString:Ljava/lang/String;

    const-string p5, "uriString"

    invoke-virtual {p4, p5, p3}, Landroid/database/MatrixCursor$RowBuilder;->add(Ljava/lang/String;Ljava/lang/Object;)Landroid/database/MatrixCursor$RowBuilder;

    goto :goto_0

    :cond_0
    return-object p1
.end method

.method public update(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    .locals 0

    const/4 p1, 0x0

    return p1
.end method
