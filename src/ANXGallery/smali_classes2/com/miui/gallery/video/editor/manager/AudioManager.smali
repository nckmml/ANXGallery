.class public Lcom/miui/gallery/video/editor/manager/AudioManager;
.super Ljava/lang/Object;
.source "AudioManager.java"


# static fields
.field private static final sBgColor:[I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x6

    new-array v0, v0, [I

    fill-array-data v0, :array_0

    sput-object v0, Lcom/miui/gallery/video/editor/manager/AudioManager;->sBgColor:[I

    return-void

    nop

    :array_0
    .array-data 4
        0x7f0703a6
        0x7f0703a7
        0x7f0703a8
        0x7f0703a9
        0x7f0703aa
        0x7f0703ab
    .end array-data
.end method

.method public static getLocalCustomAudio()Lcom/miui/gallery/video/editor/LocalAudio;
    .locals 2

    new-instance v0, Lcom/miui/gallery/video/editor/LocalAudio;

    invoke-direct {v0}, Lcom/miui/gallery/video/editor/LocalAudio;-><init>()V

    const v1, 0x7f0703ac

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/LocalAudio;->setIconResId(I)V

    const v1, 0x7f10088c

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/LocalAudio;->setNameResId(I)V

    const/16 v1, 0x11

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/LocalAudio;->setDownloadState(I)V

    return-object v0
.end method

.method private static getLocalNoneAudio()Lcom/miui/gallery/video/editor/LocalAudio;
    .locals 2

    new-instance v0, Lcom/miui/gallery/video/editor/LocalAudio;

    invoke-direct {v0}, Lcom/miui/gallery/video/editor/LocalAudio;-><init>()V

    const v1, 0x7f0703ae

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/LocalAudio;->setIconResId(I)V

    const v1, 0x7f10088f

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/LocalAudio;->setNameResId(I)V

    const/16 v1, 0x11

    invoke-virtual {v0, v1}, Lcom/miui/gallery/video/editor/LocalAudio;->setDownloadState(I)V

    return-object v0
.end method

.method public static initDataWithBgColor()Ljava/util/ArrayList;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/video/editor/LocalAudio;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-static {}, Lcom/miui/gallery/video/editor/manager/AudioManager;->getLocalNoneAudio()Lcom/miui/gallery/video/editor/LocalAudio;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    sget-object v1, Lcom/miui/gallery/video/editor/manager/AudioManager;->sBgColor:[I

    array-length v2, v1

    const/4 v3, 0x0

    :goto_0
    if-ge v3, v2, :cond_0

    aget v4, v1, v3

    new-instance v5, Lcom/miui/gallery/video/editor/LocalAudio;

    invoke-direct {v5}, Lcom/miui/gallery/video/editor/LocalAudio;-><init>()V

    invoke-virtual {v5, v4}, Lcom/miui/gallery/video/editor/LocalAudio;->setIconResId(I)V

    invoke-virtual {v0, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_0
    invoke-static {}, Lcom/miui/gallery/video/editor/manager/AudioManager;->getLocalCustomAudio()Lcom/miui/gallery/video/editor/LocalAudio;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0
.end method

.method public static loadAudioData(Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;Ljava/util/List;)Ljava/util/ArrayList;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;",
            "Ljava/util/List<",
            "Lcom/miui/gallery/video/editor/model/LocalResource;",
            ">;)",
            "Ljava/util/ArrayList<",
            "Lcom/miui/gallery/video/editor/LocalAudio;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-static {p1}, Lcom/miui/gallery/util/MiscUtil;->isValid(Ljava/util/Collection;)Z

    move-result v1

    if-nez v1, :cond_0

    return-object v0

    :cond_0
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_7

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/miui/gallery/video/editor/model/LocalResource;

    if-nez v2, :cond_1

    goto :goto_0

    :cond_1
    new-instance v3, Lcom/miui/gallery/video/editor/LocalAudio;

    invoke-direct {v3, v2}, Lcom/miui/gallery/video/editor/LocalAudio;-><init>(Lcom/miui/gallery/video/editor/model/LocalResource;)V

    invoke-virtual {v3}, Lcom/miui/gallery/video/editor/LocalAudio;->isNone()Z

    move-result v4

    if-eqz v4, :cond_2

    const v4, 0x7f0703ae

    invoke-virtual {v3, v4}, Lcom/miui/gallery/video/editor/LocalAudio;->setIconResId(I)V

    goto :goto_2

    :cond_2
    invoke-virtual {v3}, Lcom/miui/gallery/video/editor/LocalAudio;->isExtra()Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-interface {p1, v2}, Ljava/util/List;->indexOf(Ljava/lang/Object;)I

    move-result v4

    sget-object v5, Lcom/miui/gallery/video/editor/manager/AudioManager;->sBgColor:[I

    const/4 v6, 0x1

    if-lt v4, v6, :cond_3

    add-int/lit8 v4, v4, -0x1

    array-length v6, v5

    if-ge v4, v6, :cond_3

    goto :goto_1

    :cond_3
    const/4 v4, 0x0

    :goto_1
    aget v4, v5, v4

    invoke-virtual {v3, v4}, Lcom/miui/gallery/video/editor/LocalAudio;->setColor(I)V

    goto :goto_2

    :cond_4
    invoke-virtual {v3}, Lcom/miui/gallery/video/editor/LocalAudio;->isCustom()Z

    move-result v4

    if-eqz v4, :cond_5

    const v4, 0x7f0703ac

    invoke-virtual {v3, v4}, Lcom/miui/gallery/video/editor/LocalAudio;->setIconResId(I)V

    :cond_5
    :goto_2
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    iget-wide v5, v2, Lcom/miui/gallery/video/editor/model/LocalResource;->id:J

    invoke-virtual {p0, v5, v6}, Lcom/miui/gallery/video/editor/factory/VideoEditorModuleFactory;->getTemplateDir(J)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    sget-object v2, Ljava/io/File;->separator:Ljava/lang/String;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Lcom/miui/gallery/video/editor/LocalAudio;->getFileName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    new-instance v4, Ljava/io/File;

    invoke-direct {v4, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4}, Ljava/io/File;->exists()Z

    move-result v4

    if-eqz v4, :cond_6

    const/16 v4, 0x11

    invoke-virtual {v3, v4}, Lcom/miui/gallery/video/editor/LocalAudio;->setDownloadState(I)V

    invoke-virtual {v3, v2}, Lcom/miui/gallery/video/editor/LocalAudio;->setSrcPath(Ljava/lang/String;)V

    :cond_6
    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_7
    return-object v0
.end method
