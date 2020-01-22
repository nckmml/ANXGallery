.class public Lcom/meicam/themehelper/NvsThemeHelper;
.super Ljava/lang/Object;
.source "NvsThemeHelper.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;
    }
.end annotation


# static fields
.field private static final DEFAULT_FONT1_SIZE:I = 0x47

.field private static final DEFAULT_FONT2_SIZE:I = 0x21

.field private static final DEFAULT_FONT_COLOR:I = -0x1

.field private static final FONT_MAX_LENGTH:Ljava/lang/String; = "\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u5341"

.field private static final NVS_MAX_IMAGE_FILE_COUNT:I = 0x14

.field private static final NVS_MAX_TIME_LINE_LENGTH:J = 0x55d4a80L

.field private static final NVS_MIN_IMAGE_FILE_COUNT:I = 0x3

.field private static final NVS_USE_ALL_PHOTO:I = -0x2

.field private static final NVS_USE_CURRENT_DURATION:I = -0x1

.field private static final TAG:Ljava/lang/String; = "NvsThemeHelper"

.field public static m_timelineRatio:F

.field public static rand:Ljava/util/Random;


# instance fields
.field private clipMaxLen:J

.field private coverEndROI:Landroid/graphics/RectF;

.field private coverStartROI:Landroid/graphics/RectF;

.field private mMusicFileList:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsMusicFileDesc;",
            ">;"
        }
    .end annotation
.end field

.field private mThemeAssetMap:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;",
            ">;"
        }
    .end annotation
.end field

.field private m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

.field private m_cafStickerId:Ljava/lang/StringBuilder;

.field private m_caption1:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

.field private m_caption1Text:Ljava/lang/String;

.field private m_caption2:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

.field private m_caption2Text:Ljava/lang/String;

.field private m_captionBitmapPath1:Ljava/lang/String;

.field private m_captionBitmapPath2:Ljava/lang/String;

.field private m_context:Landroid/content/Context;

.field private m_defaultRhythm10sPath:Ljava/lang/String;

.field private m_defaultRhythmPath:Ljava/lang/String;

.field private m_fxTransClipCount:I

.field private m_inputIamgeInfo:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;"
        }
    .end annotation
.end field

.field private m_is10sMode:Z

.field private m_maxTotalTime:J

.field private m_musicVolumeGain:F

.field private m_selectedExtMusicIdx:I

.field private m_selectedLocalMusic:Ljava/lang/String;

.field private m_selectedLocalMusicEnd:J

.field private m_selectedLocalMusicStart:J

.field private m_selectedMusicType:I

.field private m_showCaption:Z

.field private m_stickerId1:Ljava/lang/String;

.field private m_stickerId2:Ljava/lang/String;

.field private m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

.field private m_themeAssetID:Ljava/lang/String;

.field private m_timeline:Lcom/meicam/sdk/NvsTimeline;

.field private seed:J

.field private timeBase:J


# direct methods
.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Ljava/util/Random;

    const-wide/16 v1, 0x3e8

    invoke-direct {v0, v1, v2}, Ljava/util/Random;-><init>(J)V

    sput-object v0, Lcom/meicam/themehelper/NvsThemeHelper;->rand:Ljava/util/Random;

    const/high16 v0, 0x3f100000    # 0.5625f

    sput v0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    return-void
.end method

.method public constructor <init>()V
    .locals 5

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    const-wide/16 v1, 0x3e8

    iput-wide v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->seed:J

    const-wide/32 v1, 0xf4240

    iput-wide v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    iget-wide v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    const-wide/16 v3, 0xa

    mul-long/2addr v1, v3

    iput-wide v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->clipMaxLen:J

    const/4 v1, 0x0

    iput-boolean v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    iput-boolean v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_showCaption:Z

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    new-instance v2, Ljava/lang/String;

    invoke-direct {v2}, Ljava/lang/String;-><init>()V

    iput-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1Text:Ljava/lang/String;

    new-instance v2, Ljava/lang/String;

    invoke-direct {v2}, Ljava/lang/String;-><init>()V

    iput-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2Text:Ljava/lang/String;

    new-instance v2, Ljava/lang/String;

    invoke-direct {v2}, Ljava/lang/String;-><init>()V

    iput-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath1:Ljava/lang/String;

    new-instance v2, Ljava/lang/String;

    invoke-direct {v2}, Ljava/lang/String;-><init>()V

    iput-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath2:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    iput-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafStickerId:Ljava/lang/StringBuilder;

    const/4 v2, -0x1

    iput v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedMusicType:I

    const/high16 v3, 0x3f800000    # 1.0f

    iput v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    iput v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedExtMusicIdx:I

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusic:Ljava/lang/String;

    const-wide/16 v2, -0x1

    iput-wide v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicStart:J

    iput-wide v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicEnd:J

    const-wide/32 v2, 0x55d4a80

    iput-wide v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_maxTotalTime:J

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->coverStartROI:Landroid/graphics/RectF;

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->coverEndROI:Landroid/graphics/RectF;

    iput v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mThemeAssetMap:Ljava/util/Map;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mMusicFileList:Ljava/util/ArrayList;

    return-void
.end method

.method private addCaption(Lcom/meicam/sdk/NvsTimeline;)V
    .locals 11

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    invoke-virtual {p1, v0}, Lcom/meicam/sdk/NvsTimeline;->removeAnimatedSticker(Lcom/meicam/sdk/NvsTimelineAnimatedSticker;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    invoke-virtual {p1, v0}, Lcom/meicam/sdk/NvsTimeline;->removeAnimatedSticker(Lcom/meicam/sdk/NvsTimelineAnimatedSticker;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1Text:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    const-wide v7, 0x400bd70a3d70a3d7L    # 3.48

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2Text:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_1

    :cond_0
    iget-boolean v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_showCaption:Z

    if-eqz v0, :cond_1

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    invoke-virtual {p1, v0}, Lcom/meicam/sdk/NvsTimeline;->removeAnimatedSticker(Lcom/meicam/sdk/NvsTimelineAnimatedSticker;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafStickerId:Ljava/lang/StringBuilder;

    if-eqz v0, :cond_2

    const-wide/16 v1, 0x0

    iget-wide v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    long-to-double v3, v3

    mul-double/2addr v3, v7

    double-to-long v3, v3

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    move-object v0, p1

    invoke-virtual/range {v0 .. v5}, Lcom/meicam/sdk/NvsTimeline;->addAnimatedSticker(JJLjava/lang/String;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    move-result-object v0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    :cond_2
    :goto_0
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath1:Ljava/lang/String;

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_3

    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath1:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    :cond_3
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath2:Ljava/lang/String;

    if-eqz v0, :cond_4

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_4

    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath2:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-virtual {v0}, Ljava/io/File;->delete()Z

    :cond_4
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_stickerId1:Ljava/lang/String;

    const-string v9, "meicam"

    if-eqz v0, :cond_d

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_stickerId2:Ljava/lang/String;

    if-nez v1, :cond_5

    goto/16 :goto_3

    :cond_5
    iget-boolean v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_showCaption:Z

    if-nez v1, :cond_6

    return-void

    :cond_6
    invoke-virtual {v0}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object v5

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_stickerId2:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object v10

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1Text:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_9

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1Text:Ljava/lang/String;

    const/16 v1, 0x47

    invoke-direct {p0, v0, v1}, Lcom/meicam/themehelper/NvsThemeHelper;->manageStringLength(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0, v1}, Lcom/meicam/themehelper/NvsThemeHelper;->createCaptionBitmap(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath1:Ljava/lang/String;

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath1:Ljava/lang/String;

    if-eqz v0, :cond_8

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_8

    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath1:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_7

    const-wide/16 v1, 0x0

    iget-wide v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    long-to-double v3, v3

    mul-double/2addr v3, v7

    double-to-long v3, v3

    iget-object v6, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath1:Ljava/lang/String;

    move-object v0, p1

    invoke-virtual/range {v0 .. v6}, Lcom/meicam/sdk/NvsTimeline;->addCustomAnimatedSticker(JJLjava/lang/String;Ljava/lang/String;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    move-result-object v0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    goto :goto_1

    :cond_7
    const-string v0, "bitmap_file1 not exist"

    invoke-static {v9, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    :cond_8
    const-string v0, "caption1_path is null"

    invoke-static {v9, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :cond_9
    :goto_1
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2Text:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_c

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2Text:Ljava/lang/String;

    const/16 v1, 0x21

    invoke-direct {p0, v0, v1}, Lcom/meicam/themehelper/NvsThemeHelper;->manageString2Length(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0, v1}, Lcom/meicam/themehelper/NvsThemeHelper;->createCaptionBitmap(Ljava/lang/String;I)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath2:Ljava/lang/String;

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath2:Ljava/lang/String;

    if-eqz v0, :cond_b

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_b

    new-instance v0, Ljava/io/File;

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath2:Ljava/lang/String;

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_a

    const-wide/16 v1, 0x0

    iget-wide v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    long-to-double v3, v3

    mul-double/2addr v3, v7

    double-to-long v3, v3

    iget-object v6, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_captionBitmapPath2:Ljava/lang/String;

    move-object v0, p1

    move-object v5, v10

    invoke-virtual/range {v0 .. v6}, Lcom/meicam/sdk/NvsTimeline;->addCustomAnimatedSticker(JJLjava/lang/String;Ljava/lang/String;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    move-result-object v0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    goto :goto_2

    :cond_a
    const-string v0, "bitmap_file2 not exist"

    invoke-static {v9, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_2

    :cond_b
    const-string v0, "caption2_path is null"

    invoke-static {v9, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    :cond_c
    :goto_2
    return-void

    :cond_d
    :goto_3
    const-string v0, "m_stickerId is null"

    invoke-static {v9, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void
.end method

.method private addEndingFx(Lcom/meicam/sdk/NvsTimeline;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Z)V
    .locals 4

    if-eqz p3, :cond_0

    iget-object p3, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFxId10s:Ljava/lang/String;

    iget-object v0, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFxImgPath:Ljava/lang/String;

    iget-wide v1, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFx10sLen:J

    invoke-virtual {p1, p3, v0, v1, v2}, Lcom/meicam/sdk/NvsTimeline;->setTimelineEndingFilter(Ljava/lang/String;Ljava/lang/String;J)Z

    goto :goto_0

    :cond_0
    iget-object p3, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFxId:Ljava/lang/String;

    iget-object p2, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFxImgPath:Ljava/lang/String;

    const-wide/high16 v0, 0x3ff8000000000000L    # 1.5

    iget-wide v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    long-to-double v2, v2

    mul-double/2addr v2, v0

    double-to-long v0, v2

    invoke-virtual {p1, p3, p2, v0, v1}, Lcom/meicam/sdk/NvsTimeline;->setTimelineEndingFilter(Ljava/lang/String;Ljava/lang/String;J)Z

    :goto_0
    return-void
.end method

.method private addMusicFile(Lcom/meicam/sdk/NvsTimeline;Ljava/lang/String;Z)V
    .locals 20

    move-object/from16 v0, p0

    move-object/from16 v7, p2

    if-nez v7, :cond_0

    return-void

    :cond_0
    const-string v1, ""

    if-ne v7, v1, :cond_1

    return-void

    :cond_1
    const-string v1, "theme helper"

    invoke-static {v1, v7}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iget-object v1, v0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    invoke-virtual {v1, v7}, Lcom/meicam/sdk/NvsStreamingContext;->getAVFileInfo(Ljava/lang/String;)Lcom/meicam/sdk/NvsAVFileInfo;

    move-result-object v1

    if-nez v1, :cond_2

    return-void

    :cond_2
    invoke-virtual {v1}, Lcom/meicam/sdk/NvsAVFileInfo;->getAudioStreamCount()I

    move-result v2

    const/4 v3, 0x1

    if-ge v2, v3, :cond_3

    return-void

    :cond_3
    invoke-virtual/range {p1 .. p1}, Lcom/meicam/sdk/NvsTimeline;->getDuration()J

    move-result-wide v2

    const/4 v4, 0x0

    invoke-virtual {v1, v4}, Lcom/meicam/sdk/NvsAVFileInfo;->getAudioStreamDuration(I)J

    move-result-wide v5

    iget-wide v8, v0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    div-long/2addr v5, v8

    mul-long/2addr v5, v8

    const-wide/16 v10, 0x0

    if-eqz p3, :cond_7

    iget-wide v12, v0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicStart:J

    cmp-long v1, v12, v10

    if-ltz v1, :cond_7

    iget-wide v14, v0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicEnd:J

    cmp-long v1, v14, v10

    if-lez v1, :cond_7

    div-long/2addr v12, v8

    mul-long/2addr v12, v8

    div-long/2addr v14, v8

    mul-long/2addr v14, v8

    sub-long v16, v14, v12

    const-wide/16 v18, 0x2

    mul-long v8, v8, v18

    cmp-long v1, v16, v8

    if-lez v1, :cond_4

    cmp-long v1, v14, v5

    if-lez v1, :cond_5

    sub-long v8, v5, v12

    move-wide/from16 v16, v8

    goto :goto_0

    :cond_4
    move-wide/from16 v16, v5

    :cond_5
    :goto_0
    iget-wide v8, v0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    mul-long v8, v8, v18

    cmp-long v1, v16, v8

    if-gez v1, :cond_6

    goto :goto_1

    :cond_6
    move-wide v10, v12

    goto :goto_2

    :cond_7
    :goto_1
    move-wide/from16 v16, v5

    :goto_2
    const/4 v1, 0x0

    move-object/from16 v5, p1

    invoke-virtual {v5, v1, v1}, Lcom/meicam/sdk/NvsTimeline;->setThemeMusicVolumeGain(FF)V

    invoke-virtual/range {p1 .. p1}, Lcom/meicam/sdk/NvsTimeline;->appendAudioTrack()Lcom/meicam/sdk/NvsAudioTrack;

    move-result-object v8

    add-long v2, v2, v16

    iget-wide v5, v0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    sub-long/2addr v2, v5

    div-long v2, v2, v16

    long-to-int v9, v2

    move v12, v4

    :goto_3
    if-ge v12, v9, :cond_8

    add-long v5, v10, v16

    move-object v1, v8

    move-object/from16 v2, p2

    move-wide v3, v10

    invoke-virtual/range {v1 .. v6}, Lcom/meicam/sdk/NvsAudioTrack;->appendClip(Ljava/lang/String;JJ)Lcom/meicam/sdk/NvsAudioClip;

    add-int/lit8 v12, v12, 0x1

    goto :goto_3

    :cond_8
    return-void
.end method

.method private addToFxList(Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Ljava/lang/String;Ljava/lang/String;)V
    .locals 5

    const-string v0, "9v16"

    invoke-virtual {p3, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    const-string v1, "-fh"

    const-string v2, "-hf"

    const-string v3, "-half"

    const-string v4, "-full"

    if-eqz v0, :cond_4

    invoke-virtual {p3, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_0
    invoke-virtual {p3, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_1
    invoke-virtual {p3, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_2

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_2
    invoke-virtual {p3, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p3

    if-eqz p3, :cond_3

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_3
    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v16List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_4
    const-string v0, "9v18"

    invoke-virtual {p3, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_9

    invoke-virtual {p3, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_5

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_5
    invoke-virtual {p3, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_6

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_6
    invoke-virtual {p3, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_7

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_7
    invoke-virtual {p3, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p3

    if-eqz p3, :cond_8

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_8
    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v18List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_9
    const-string v0, "9v19"

    invoke-virtual {p3, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_e

    invoke-virtual {p3, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_a

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_a
    invoke-virtual {p3, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_b

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_b
    invoke-virtual {p3, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_c

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_c
    invoke-virtual {p3, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p3

    if-eqz p3, :cond_d

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_d
    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v19List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_e
    const-string v0, "9vx73"

    invoke-virtual {p3, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_13

    invoke-virtual {p3, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_f

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_f
    invoke-virtual {p3, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_10

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_10
    invoke-virtual {p3, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_11

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_11
    invoke-virtual {p3, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p3

    if-eqz p3, :cond_12

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_12
    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p3, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p3, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p1, p1, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9vx73List:Ljava/util/ArrayList;

    invoke-virtual {p1, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_13
    :goto_0
    return-void
.end method

.method private applyFxTrans(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JZI)J
    .locals 29

    move-object/from16 v8, p0

    move-object/from16 v9, p1

    move/from16 v10, p2

    move-object/from16 v11, p3

    move-object/from16 v12, p4

    move/from16 v7, p8

    const-string v13, ""

    invoke-virtual {v11, v13}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    const-wide/16 v14, 0x0

    if-eqz v0, :cond_0

    return-wide v14

    :cond_0
    invoke-virtual/range {p1 .. p2}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v0

    add-int/lit8 v6, v10, 0x1

    invoke-virtual {v9, v6}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v1

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getFilePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1}, Lcom/meicam/sdk/NvsVideoClip;->getFilePath()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v16

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v18

    sub-long v16, v16, v18

    invoke-virtual {v1}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v18

    invoke-virtual {v1}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v20

    sub-long v18, v18, v20

    const-string v5, "fullscreenMode"

    invoke-virtual {v0, v5}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v3

    const-string v14, "indexOfShowList"

    invoke-virtual {v0, v14}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v15

    invoke-virtual {v1, v14}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v14

    add-int/lit8 v0, v10, -0x1

    if-ltz v0, :cond_2

    invoke-virtual {v9, v0}, Lcom/meicam/sdk/NvsVideoTrack;->getTransitionBySourceClipIndex(I)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoTransition;->getVideoTransitionType()I

    move-result v22

    if-nez v22, :cond_1

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoTransition;->getBuiltinVideoTransitionName()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_1
    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoTransition;->getVideoTransitionPackageId()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_2
    const/4 v0, 0x0

    :goto_0
    if-eqz v0, :cond_3

    move-object/from16 v22, v2

    int-to-long v1, v7

    move-wide/from16 v23, v1

    goto :goto_1

    :cond_3
    move-object/from16 v22, v2

    const-wide/16 v23, 0x0

    :goto_1
    const/4 v2, 0x0

    invoke-virtual {v9, v10, v2}, Lcom/meicam/sdk/NvsVideoTrack;->removeClip(IZ)Z

    invoke-virtual {v9, v10, v2}, Lcom/meicam/sdk/NvsVideoTrack;->removeClip(IZ)Z

    const-wide/16 v25, 0x0

    add-long v18, v16, v18

    move-object v1, v0

    move-object/from16 v0, p1

    move-object v10, v1

    const/4 v12, 0x0

    move-object/from16 v1, v22

    move-object v12, v3

    move-object/from16 v22, v13

    move v13, v2

    move-wide/from16 v2, v25

    move-object v13, v4

    move-object/from16 v27, v5

    move-wide/from16 v4, v18

    move/from16 v28, v6

    move/from16 v6, p2

    invoke-virtual/range {v0 .. v6}, Lcom/meicam/sdk/NvsVideoTrack;->insertClip(Ljava/lang/String;JJI)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v5

    move-object/from16 v0, v27

    invoke-virtual {v5, v0, v12}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    const-string v0, "hasFxTrans"

    const-string v1, "true"

    invoke-virtual {v5, v0, v1}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    const-string v0, "fxFilePath"

    invoke-virtual {v5, v0, v13}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    const-wide/16 v0, 0x3e8

    div-long v2, v16, v0

    invoke-static {v2, v3}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v2

    const-string v3, "fxInpoint"

    invoke-virtual {v5, v3, v2}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getIndex()I

    move-result v2

    const/4 v3, 0x0

    invoke-virtual {v9, v2, v3}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    add-int/lit8 v2, v2, -0x1

    const/16 v12, 0x1e

    if-ltz v2, :cond_6

    if-eqz v10, :cond_5

    invoke-virtual {v10}, Ljava/lang/String;->length()I

    move-result v3

    if-le v3, v12, :cond_4

    invoke-virtual {v9, v2, v10}, Lcom/meicam/sdk/NvsVideoTrack;->setPackagedTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_2

    :cond_4
    invoke-virtual {v9, v2, v10}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_2

    :cond_5
    const/4 v3, 0x0

    invoke-virtual {v9, v2, v3}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    :cond_6
    :goto_2
    const-string v2, "Storyboard"

    invoke-virtual {v5, v2}, Lcom/meicam/sdk/NvsVideoClip;->appendBuiltinFx(Ljava/lang/String;)Lcom/meicam/sdk/NvsVideoFx;

    move-result-object v10

    int-to-long v2, v7

    sub-long v6, v2, v23

    sub-long v6, v16, v6

    div-long/2addr v6, v0

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v18

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v23

    sub-long v18, v18, v23

    move-object v4, v13

    iget-wide v12, v8, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    add-long v18, v18, v12

    sub-long v18, v18, v2

    div-long v18, v18, v0

    sub-long v12, v18, v6

    move-object/from16 v0, p0

    move-object/from16 v1, p3

    move-object v2, v4

    move-wide v3, v6

    move-object v7, v5

    move-wide v5, v12

    move-object v12, v7

    move/from16 v7, p7

    invoke-direct/range {v0 .. v7}, Lcom/meicam/themehelper/NvsThemeHelper;->changeFxDesc(Ljava/lang/String;Ljava/lang/String;JJZ)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_7

    const-wide/16 v1, 0x0

    return-wide v1

    :cond_7
    const-string v1, "hori"

    invoke-virtual {v11, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_8

    iget-object v1, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v14}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v2, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v2, v14}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    const/4 v3, 0x0

    invoke-static {v1, v2, v0, v3}, Lcom/meicam/themehelper/Utils;->changeHoriROI(FLandroid/graphics/RectF;Ljava/lang/String;Lcom/meicam/sdk/NvsVideoClip;)Ljava/lang/String;

    move-result-object v0

    iget-object v1, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v15}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v2, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v2, v15}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v2, v0, v12}, Lcom/meicam/themehelper/Utils;->changeHoriROI(FLandroid/graphics/RectF;Ljava/lang/String;Lcom/meicam/sdk/NvsVideoClip;)Ljava/lang/String;

    move-result-object v0

    const/4 v3, 0x0

    goto :goto_3

    :cond_8
    const-string v1, "vert"

    invoke-virtual {v11, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_9

    iget-object v1, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v14}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v2, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v2, v14}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    const/4 v3, 0x0

    invoke-static {v1, v2, v0, v3}, Lcom/meicam/themehelper/Utils;->changeVertROI(FLandroid/graphics/RectF;Ljava/lang/String;Lcom/meicam/sdk/NvsVideoClip;)Ljava/lang/String;

    move-result-object v0

    iget-object v1, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v15}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v2, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v2, v15}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v2, v0, v12}, Lcom/meicam/themehelper/Utils;->changeVertROI(FLandroid/graphics/RectF;Ljava/lang/String;Lcom/meicam/sdk/NvsVideoClip;)Ljava/lang/String;

    move-result-object v0

    goto :goto_3

    :cond_9
    const/4 v3, 0x0

    iget-object v1, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v15}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    invoke-direct {v8, v12, v1}, Lcom/meicam/themehelper/NvsThemeHelper;->setImageMotion(Lcom/meicam/sdk/NvsVideoClip;Lcom/meicam/themehelper/NvsImageFileDesc;)V

    iget-object v1, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v14}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v2, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v2, v14}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v2, v0}, Lcom/meicam/themehelper/Utils;->changeROI(FLandroid/graphics/RectF;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    :goto_3
    const-string v1, "Description String"

    invoke-virtual {v10, v1, v0}, Lcom/meicam/sdk/NvsVideoFx;->setStringVal(Ljava/lang/String;Ljava/lang/String;)V

    if-nez p7, :cond_a

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "assets:/"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    move-object v11, v0

    :cond_a
    const-string v0, "/"

    invoke-virtual {v11, v0}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v0

    const/4 v1, 0x0

    invoke-virtual {v11, v1, v0}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    const-string v1, "Resource Dir"

    invoke-virtual {v10, v1, v0}, Lcom/meicam/sdk/NvsVideoFx;->setStringVal(Ljava/lang/String;Ljava/lang/String;)V

    move/from16 v0, v28

    invoke-virtual {v9, v0}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v0

    if-eqz v0, :cond_d

    move-object/from16 v0, p4

    move-object v2, v3

    move-object/from16 v1, v22

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_b

    move/from16 v1, p2

    invoke-virtual {v9, v1, v2}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_5

    :cond_b
    move/from16 v1, p2

    invoke-virtual/range {p4 .. p4}, Ljava/lang/String;->length()I

    move-result v2

    const/16 v3, 0x1e

    if-le v2, v3, :cond_c

    invoke-virtual {v9, v1, v0}, Lcom/meicam/sdk/NvsVideoTrack;->setPackagedTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    goto :goto_4

    :cond_c
    invoke-virtual {v9, v1, v0}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    :goto_4
    if-eqz v0, :cond_d

    move-wide/from16 v1, p5

    long-to-float v1, v1

    const/high16 v2, 0x447a0000    # 1000.0f

    div-float/2addr v1, v2

    invoke-virtual {v0, v1}, Lcom/meicam/sdk/NvsVideoTransition;->setVideoTransitionDurationScaleFactor(F)V

    :cond_d
    :goto_5
    iget v0, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/lit8 v0, v0, 0x1

    iput v0, v8, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    invoke-virtual {v12}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v0

    add-long v0, v0, v16

    return-wide v0
.end method

.method private applyFxTransV2(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JLcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)V
    .locals 11

    move-object v9, p0

    move-object/from16 v10, p7

    iget-boolean v7, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->isDownload:Z

    iget v0, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transOffset:I

    mul-int/lit16 v8, v0, 0x3e8

    move-object v0, p0

    move-object v1, p1

    move v2, p2

    move-object v3, p3

    move-object v4, p4

    move-wide/from16 v5, p5

    invoke-direct/range {v0 .. v8}, Lcom/meicam/themehelper/NvsThemeHelper;->applyFxTrans(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JZI)J

    move-result-wide v0

    iget-object v2, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_blurFxId:Ljava/lang/StringBuilder;

    if-eqz v2, :cond_2

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    iget-object v4, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_blurWidthFxTransList:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v4

    const/4 v5, 0x1

    if-ge v3, v4, :cond_1

    iget-object v4, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_blurWidthFxTransList:Ljava/util/ArrayList;

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/CharSequence;

    move-object v6, p3

    invoke-virtual {p3, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_0

    move v2, v5

    goto :goto_1

    :cond_0
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_1
    :goto_1
    if-eqz v2, :cond_2

    iget-object v2, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    iget-wide v3, v9, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    const-wide/16 v6, 0x2

    div-long v6, v3, v6

    sub-long/2addr v0, v6

    iget-object v6, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_blurFxId:Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    move-object p1, v2

    move-wide p2, v0

    move-wide p4, v3

    move-object/from16 p6, v6

    invoke-virtual/range {p1 .. p6}, Lcom/meicam/sdk/NvsTimeline;->addPackagedTimelineVideoFx(JJLjava/lang/String;)Lcom/meicam/sdk/NvsTimelineVideoFx;

    move-result-object v0

    const-string v1, "No Background"

    invoke-virtual {v0, v1, v5}, Lcom/meicam/sdk/NvsTimelineVideoFx;->setBooleanVal(Ljava/lang/String;Z)V

    :cond_2
    return-void
.end method

.method private applyFxTransV3(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JLcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)V
    .locals 36

    move-object/from16 v11, p0

    move-object/from16 v12, p1

    move/from16 v13, p2

    move-object/from16 v14, p3

    move-object/from16 v15, p4

    move-object/from16 v10, p7

    const-string v8, ""

    invoke-virtual {v14, v8}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    :cond_0
    add-int/lit8 v0, v13, 0x2

    invoke-virtual/range {p1 .. p1}, Lcom/meicam/sdk/NvsVideoTrack;->getClipCount()I

    move-result v1

    if-lt v0, v1, :cond_1

    return-void

    :cond_1
    invoke-virtual/range {p1 .. p2}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v1

    add-int/lit8 v9, v13, 0x1

    invoke-virtual {v12, v9}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v2

    invoke-virtual {v12, v0}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v0

    invoke-virtual {v1}, Lcom/meicam/sdk/NvsVideoClip;->getFilePath()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2}, Lcom/meicam/sdk/NvsVideoClip;->getFilePath()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getFilePath()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v1}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v4

    invoke-virtual {v1}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v16

    sub-long v16, v4, v16

    invoke-virtual {v2}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v4

    invoke-virtual {v2}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v18

    sub-long v18, v4, v18

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v4

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v20

    sub-long v4, v4, v20

    move-object/from16 v20, v8

    const-string v8, "fullscreenMode"

    invoke-virtual {v1, v8}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v21

    move/from16 v22, v9

    invoke-virtual/range {v21 .. v21}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v9

    move-object/from16 v21, v6

    const-string v6, "indexOfShowList"

    invoke-virtual {v1, v6}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-virtual {v2, v6}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Integer;->intValue()I

    move-result v2

    invoke-virtual {v0, v6}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result v6

    add-int/lit8 v0, v13, -0x1

    move/from16 v23, v2

    if-ltz v0, :cond_3

    invoke-virtual {v12, v0}, Lcom/meicam/sdk/NvsVideoTrack;->getTransitionBySourceClipIndex(I)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    if-eqz v0, :cond_3

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoTransition;->getVideoTransitionType()I

    move-result v24

    if-nez v24, :cond_2

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoTransition;->getBuiltinVideoTransitionName()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_2
    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoTransition;->getVideoTransitionPackageId()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_3
    const/4 v0, 0x0

    :goto_0
    const-wide/16 v24, 0x0

    iget v2, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transOffset:I

    mul-int/lit16 v2, v2, 0x3e8

    move-object/from16 v27, v3

    int-to-long v2, v2

    if-eqz v0, :cond_4

    move-wide/from16 v24, v2

    move-wide/from16 v28, v24

    goto :goto_1

    :cond_4
    move-wide/from16 v28, v2

    :goto_1
    const/4 v2, 0x0

    invoke-virtual {v12, v13, v2}, Lcom/meicam/sdk/NvsVideoTrack;->removeClip(IZ)Z

    invoke-virtual {v12, v13, v2}, Lcom/meicam/sdk/NvsVideoTrack;->removeClip(IZ)Z

    invoke-virtual {v12, v13, v2}, Lcom/meicam/sdk/NvsVideoTrack;->removeClip(IZ)Z

    const-wide/16 v30, 0x0

    add-long v32, v16, v18

    add-long v4, v32, v4

    move-object v3, v0

    move-object/from16 v0, p1

    move v13, v1

    move-object/from16 v1, v27

    move-object v15, v3

    move-wide/from16 v26, v28

    const/4 v14, 0x0

    move-wide/from16 v2, v30

    move/from16 v34, v6

    move-object/from16 v14, v21

    move/from16 v6, p2

    invoke-virtual/range {v0 .. v6}, Lcom/meicam/sdk/NvsVideoTrack;->insertClip(Ljava/lang/String;JJI)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v0

    invoke-virtual {v0, v8, v9}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    const-string v1, "hasFxTrans"

    const-string v2, "true"

    invoke-virtual {v0, v1, v2}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    const-string v1, "fxFilePath"

    invoke-virtual {v0, v1, v7}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    const-string v1, "fxFileV3Path"

    invoke-virtual {v0, v1, v14}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    const-wide/16 v1, 0x3e8

    div-long v3, v16, v1

    invoke-static {v3, v4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v3

    const-string v4, "fxInpoint"

    invoke-virtual {v0, v4, v3}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    div-long v18, v18, v1

    invoke-static/range {v18 .. v19}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v3

    const-string v4, "fxV3Inpoint"

    invoke-virtual {v0, v4, v3}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getIndex()I

    move-result v3

    const/4 v4, 0x0

    invoke-virtual {v12, v3, v4}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    const/4 v8, 0x1

    sub-int/2addr v3, v8

    const/16 v9, 0x1e

    if-ltz v3, :cond_7

    if-eqz v15, :cond_6

    invoke-virtual {v15}, Ljava/lang/String;->length()I

    move-result v4

    if-le v4, v9, :cond_5

    invoke-virtual {v12, v3, v15}, Lcom/meicam/sdk/NvsVideoTrack;->setPackagedTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_2

    :cond_5
    invoke-virtual {v12, v3, v15}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_2

    :cond_6
    const/4 v15, 0x0

    invoke-virtual {v12, v3, v15}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_3

    :cond_7
    :goto_2
    const/4 v15, 0x0

    :goto_3
    iget-object v3, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v3, v13}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/meicam/themehelper/NvsImageFileDesc;

    invoke-direct {v11, v0, v3}, Lcom/meicam/themehelper/NvsThemeHelper;->setImageMotion(Lcom/meicam/sdk/NvsVideoClip;Lcom/meicam/themehelper/NvsImageFileDesc;)V

    const-string v3, "Storyboard"

    invoke-virtual {v0, v3}, Lcom/meicam/sdk/NvsVideoClip;->appendBuiltinFx(Ljava/lang/String;)Lcom/meicam/sdk/NvsVideoFx;

    move-result-object v13

    sub-long v3, v26, v24

    sub-long v16, v16, v3

    div-long v4, v16, v1

    add-long v16, v4, v18

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v18

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v24

    sub-long v18, v18, v24

    add-long v18, v18, v26

    div-long v18, v18, v1

    sub-long v18, v18, v4

    iget-boolean v6, v10, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->isDownload:Z

    move-object/from16 v0, p0

    move-object/from16 v1, p3

    move-object v2, v7

    move-object v3, v14

    move v14, v6

    move-wide/from16 v6, v16

    move v12, v8

    move-object/from16 v35, v20

    move/from16 v15, v22

    move-wide/from16 v8, v18

    move v10, v14

    invoke-direct/range {v0 .. v10}, Lcom/meicam/themehelper/NvsThemeHelper;->changeFxDescV3(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJZ)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_8

    return-void

    :cond_8
    const-string v1, "hori"

    move-object/from16 v2, p3

    const/4 v3, 0x0

    invoke-virtual {v2, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_9

    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    move/from16 v4, v23

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v5, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v5, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v4, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v4, v0, v12}, Lcom/meicam/themehelper/Utils;->changeHoriROIV3(FLandroid/graphics/RectF;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    move/from16 v5, v34

    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v4, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v4, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    const/4 v6, 0x0

    invoke-static {v1, v4, v0, v6}, Lcom/meicam/themehelper/Utils;->changeHoriROIV3(FLandroid/graphics/RectF;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    goto :goto_4

    :cond_9
    move/from16 v4, v23

    move/from16 v5, v34

    const/4 v6, 0x0

    const-string v1, "vert"

    invoke-virtual {v2, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_a

    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v7, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v7, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v4, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v4, v0, v12}, Lcom/meicam/themehelper/Utils;->changeVertROIV3(FLandroid/graphics/RectF;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v4, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v4, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v4, v0, v6}, Lcom/meicam/themehelper/Utils;->changeVertROIV3(FLandroid/graphics/RectF;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    goto :goto_4

    :cond_a
    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v7, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v7, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v4, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v4, v0, v12}, Lcom/meicam/themehelper/Utils;->changeROIV3(FLandroid/graphics/RectF;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget v1, v1, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-object v4, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v4, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v4, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    invoke-static {v1, v4, v0, v6}, Lcom/meicam/themehelper/Utils;->changeROIV3(FLandroid/graphics/RectF;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    :goto_4
    const-string v1, "Description String"

    invoke-virtual {v13, v1, v0}, Lcom/meicam/sdk/NvsVideoFx;->setStringVal(Ljava/lang/String;Ljava/lang/String;)V

    move-object/from16 v0, p7

    iget-boolean v0, v0, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->isDownload:Z

    if-nez v0, :cond_b

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "assets:/"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_5

    :cond_b
    move-object v0, v2

    :goto_5
    const-string v1, "/"

    invoke-virtual {v0, v1}, Ljava/lang/String;->lastIndexOf(Ljava/lang/String;)I

    move-result v1

    invoke-virtual {v0, v6, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v0

    const-string v1, "Resource Dir"

    invoke-virtual {v13, v1, v0}, Lcom/meicam/sdk/NvsVideoFx;->setStringVal(Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "Compact Preload Resource"

    invoke-virtual {v13, v0, v12}, Lcom/meicam/sdk/NvsVideoFx;->setBooleanVal(Ljava/lang/String;Z)V

    move-object/from16 v0, p1

    invoke-virtual {v0, v15}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v1

    if-eqz v1, :cond_e

    move-object/from16 v1, p4

    move-object/from16 v2, v35

    invoke-virtual {v1, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_c

    move/from16 v2, p2

    invoke-virtual {v0, v2, v3}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_7

    :cond_c
    move/from16 v2, p2

    invoke-virtual/range {p4 .. p4}, Ljava/lang/String;->length()I

    move-result v3

    const/16 v4, 0x1e

    if-le v3, v4, :cond_d

    invoke-virtual {v0, v2, v1}, Lcom/meicam/sdk/NvsVideoTrack;->setPackagedTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    goto :goto_6

    :cond_d
    invoke-virtual {v0, v2, v1}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    :goto_6
    if-eqz v0, :cond_e

    move-wide/from16 v1, p5

    long-to-float v1, v1

    const/high16 v2, 0x447a0000    # 1000.0f

    div-float/2addr v1, v2

    invoke-virtual {v0, v1}, Lcom/meicam/sdk/NvsVideoTransition;->setVideoTransitionDurationScaleFactor(F)V

    :cond_e
    :goto_7
    iget v0, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/lit8 v0, v0, 0x2

    iput v0, v11, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    return-void
.end method

.method private buildVideoTrack(Ljava/util/ArrayList;Lcom/meicam/sdk/NvsVideoTrack;Ljava/util/ArrayList;JJZ)Ljava/util/Map;
    .locals 15
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;",
            "Lcom/meicam/sdk/NvsVideoTrack;",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsMusicPointDesc;",
            ">;JJZ)",
            "Ljava/util/Map<",
            "Ljava/lang/Integer;",
            "Lcom/meicam/themehelper/NvsMusicPointDesc;",
            ">;"
        }
    .end annotation

    move-object v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p3

    new-instance v3, Ljava/util/HashMap;

    invoke-direct {v3}, Ljava/util/HashMap;-><init>()V

    const/4 v4, 0x0

    move v5, v4

    move v6, v5

    :goto_0
    invoke-virtual/range {p1 .. p1}, Ljava/util/ArrayList;->size()I

    move-result v7

    if-ge v5, v7, :cond_a

    iget-wide v7, v0, Lcom/meicam/themehelper/NvsThemeHelper;->clipMaxLen:J

    long-to-int v7, v7

    invoke-virtual/range {p3 .. p3}, Ljava/util/ArrayList;->size()I

    move-result v8

    if-lez v8, :cond_8

    if-lez v5, :cond_7

    invoke-virtual/range {p3 .. p3}, Ljava/util/ArrayList;->size()I

    move-result v7

    if-lt v6, v7, :cond_0

    goto/16 :goto_6

    :cond_0
    invoke-virtual {v2, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/meicam/themehelper/NvsMusicPointDesc;

    add-int/lit8 v6, v6, 0x1

    invoke-virtual/range {p3 .. p3}, Ljava/util/ArrayList;->size()I

    move-result v8

    if-ge v6, v8, :cond_1

    invoke-virtual {v2, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget v8, v8, Lcom/meicam/themehelper/NvsMusicPointDesc;->cutPoint:I

    iget v9, v7, Lcom/meicam/themehelper/NvsMusicPointDesc;->cutPoint:I

    sub-int/2addr v8, v9

    mul-int/lit16 v8, v8, 0x3e8

    goto :goto_2

    :cond_1
    if-eqz p8, :cond_2

    const-wide/16 v8, 0xa

    iget-wide v10, v0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    mul-long/2addr v8, v10

    goto :goto_1

    :cond_2
    move-wide/from16 v8, p4

    :goto_1
    iget v10, v7, Lcom/meicam/themehelper/NvsMusicPointDesc;->cutPoint:I

    mul-int/lit16 v10, v10, 0x3e8

    int-to-long v10, v10

    sub-long/2addr v8, v10

    long-to-int v8, v8

    :goto_2
    if-gtz v8, :cond_3

    goto :goto_5

    :cond_3
    iget-object v9, v7, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v9}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v9

    if-eqz v9, :cond_4

    iget-object v9, v7, Lcom/meicam/themehelper/NvsMusicPointDesc;->fxNames:Ljava/util/ArrayList;

    invoke-virtual {v9}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v9

    if-nez v9, :cond_6

    :cond_4
    invoke-virtual/range {p2 .. p2}, Lcom/meicam/sdk/NvsVideoTrack;->getClipCount()I

    move-result v9

    if-lez v9, :cond_5

    invoke-virtual/range {p2 .. p2}, Lcom/meicam/sdk/NvsVideoTrack;->getClipCount()I

    move-result v9

    add-int/lit8 v9, v9, -0x1

    goto :goto_3

    :cond_5
    move v9, v4

    :goto_3
    invoke-static {v9}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v9

    invoke-interface {v3, v9, v7}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_6
    move v7, v8

    goto :goto_4

    :cond_7
    invoke-virtual {v2, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget v7, v7, Lcom/meicam/themehelper/NvsMusicPointDesc;->cutPoint:I

    mul-int/lit16 v7, v7, 0x3e8

    :cond_8
    :goto_4
    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v10, v8, Lcom/meicam/themehelper/NvsImageFileDesc;->filePath:Ljava/lang/String;

    const-wide/16 v11, 0x0

    int-to-long v13, v7

    move-object/from16 v9, p2

    invoke-virtual/range {v9 .. v14}, Lcom/meicam/sdk/NvsVideoTrack;->appendClip(Ljava/lang/String;JJ)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v7

    if-eqz v7, :cond_9

    invoke-static {v5}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v9

    const-string v10, "indexOfShowList"

    invoke-virtual {v7, v10, v9}, Lcom/meicam/sdk/NvsVideoClip;->setAttachment(Ljava/lang/String;Ljava/lang/Object;)V

    invoke-direct {p0, v7, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->setImageMotion(Lcom/meicam/sdk/NvsVideoClip;Lcom/meicam/themehelper/NvsImageFileDesc;)V

    goto :goto_5

    :cond_9
    invoke-virtual {v1, v5}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    add-int/lit8 v5, v5, -0x1

    :goto_5
    add-int/lit8 v5, v5, 0x1

    goto/16 :goto_0

    :cond_a
    :goto_6
    return-object v3
.end method

.method private changeFxDesc(Ljava/lang/String;Ljava/lang/String;JJZ)Ljava/lang/String;
    .locals 1

    const/4 v0, 0x0

    if-eqz p7, :cond_0

    move-object p7, v0

    goto :goto_0

    :cond_0
    iget-object p7, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {p7}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p7

    :goto_0
    invoke-static {p1, p7}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_1

    return-object v0

    :cond_1
    const-string p7, "placeholder.jpg"

    invoke-virtual {p1, p7, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p3, p4}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const-string p3, "xiaomiStartTime"

    invoke-virtual {p1, p3, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p5, p6}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const-string p3, "xiaomiDurationTime"

    invoke-virtual {p1, p3, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private changeFxDescV3(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJZ)Ljava/lang/String;
    .locals 1

    const/4 v0, 0x0

    if-eqz p10, :cond_0

    move-object p10, v0

    goto :goto_0

    :cond_0
    iget-object p10, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {p10}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object p10

    :goto_0
    invoke-static {p1, p10}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_1

    return-object v0

    :cond_1
    const-string p10, "placeholder.jpg"

    invoke-virtual {p1, p10, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-string p2, "picture.jpg"

    invoke-virtual {p1, p2, p3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p4, p5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const-string p3, "xiaomiStartTime"

    invoke-virtual {p1, p3, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p6, p7}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const-string p3, "pictureShowUp"

    invoke-virtual {p1, p3, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p8, p9}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const-string p3, "xiaomiDurationTime"

    invoke-virtual {p1, p3, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p8, p9}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const-string p3, "pictureLoop"

    invoke-virtual {p1, p3, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    const-wide/16 p2, 0x3e8

    add-long/2addr p6, p2

    invoke-static {p6, p7}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p2

    const-string p3, "lastMove"

    invoke-virtual {p1, p3, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private changeLocalMusic(Ljava/lang/String;JJ)V
    .locals 1

    const/4 v0, -0x1

    iput v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedExtMusicIdx:I

    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusic:Ljava/lang/String;

    iput-wide p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicStart:J

    iput-wide p4, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicEnd:J

    const-wide/16 p1, -0x1

    const/4 p3, 0x1

    invoke-direct {p0, p1, p2, p3}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    return-void
.end method

.method private checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z
    .locals 2

    const/4 v0, 0x0

    if-eqz p1, :cond_3

    if-nez p2, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p1, :cond_3

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    if-nez v1, :cond_1

    invoke-static {}, Lcom/meicam/sdk/NvsStreamingContext;->getInstance()Lcom/meicam/sdk/NvsStreamingContext;

    move-result-object v1

    iput-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    :cond_1
    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    if-nez v1, :cond_2

    return v0

    :cond_2
    invoke-virtual {v1}, Lcom/meicam/sdk/NvsStreamingContext;->getAssetPackageManager()Lcom/meicam/sdk/NvsAssetPackageManager;

    move-result-object v1

    invoke-virtual {v1, p2}, Lcom/meicam/sdk/NvsAssetPackageManager;->getAssetPackageVersionFromAssetPackageFilePath(Ljava/lang/String;)I

    move-result p2

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    invoke-virtual {v1}, Lcom/meicam/sdk/NvsStreamingContext;->getAssetPackageManager()Lcom/meicam/sdk/NvsAssetPackageManager;

    move-result-object v1

    invoke-virtual {v1, p1, p3}, Lcom/meicam/sdk/NvsAssetPackageManager;->getAssetPackageVersion(Ljava/lang/String;I)I

    move-result p1

    if-le p2, p1, :cond_3

    const/4 v0, 0x1

    :cond_3
    :goto_0
    return v0
.end method

.method private cleanUpTimeLine(Lcom/meicam/sdk/NvsTimeline;)V
    .locals 3

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsTimeline;->removeCurrentTheme()V

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsTimeline;->getFirstTimelineVideoFx()Lcom/meicam/sdk/NvsTimelineVideoFx;

    move-result-object v0

    :goto_0
    if-eqz v0, :cond_0

    invoke-virtual {p1, v0}, Lcom/meicam/sdk/NvsTimeline;->removeTimelineVideoFx(Lcom/meicam/sdk/NvsTimelineVideoFx;)Lcom/meicam/sdk/NvsTimelineVideoFx;

    move-result-object v0

    goto :goto_0

    :cond_0
    invoke-virtual {p1}, Lcom/meicam/sdk/NvsTimeline;->getFirstAnimatedSticker()Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    move-result-object v0

    :goto_1
    if-eqz v0, :cond_1

    invoke-virtual {p1, v0}, Lcom/meicam/sdk/NvsTimeline;->removeAnimatedSticker(Lcom/meicam/sdk/NvsTimelineAnimatedSticker;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    move-result-object v0

    goto :goto_1

    :cond_1
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    iput-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsTimeline;->videoTrackCount()I

    move-result v0

    const/4 v1, 0x0

    move v2, v1

    :goto_2
    if-ge v2, v0, :cond_2

    invoke-virtual {p1, v2}, Lcom/meicam/sdk/NvsTimeline;->removeVideoTrack(I)Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_2

    :cond_2
    invoke-virtual {p1}, Lcom/meicam/sdk/NvsTimeline;->audioTrackCount()I

    move-result v0

    :goto_3
    if-ge v1, v0, :cond_3

    invoke-virtual {p1, v1}, Lcom/meicam/sdk/NvsTimeline;->removeAudioTrack(I)Z

    add-int/lit8 v1, v1, 0x1

    goto :goto_3

    :cond_3
    const/high16 v0, 0x3f800000    # 1.0f

    invoke-virtual {p1, v0, v0}, Lcom/meicam/sdk/NvsTimeline;->setThemeMusicVolumeGain(FF)V

    return-void
.end method

.method private createCaptionBitmap(Ljava/lang/String;I)Ljava/lang/String;
    .locals 9

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    new-instance v3, Landroid/text/TextPaint;

    invoke-direct {v3}, Landroid/text/TextPaint;-><init>()V

    const/4 v0, 0x1

    invoke-virtual {v3, v0}, Landroid/text/TextPaint;->setAntiAlias(Z)V

    sget-object v0, Landroid/graphics/Paint$Style;->FILL:Landroid/graphics/Paint$Style;

    invoke-virtual {v3, v0}, Landroid/text/TextPaint;->setStyle(Landroid/graphics/Paint$Style;)V

    sget-object v0, Landroid/graphics/Typeface;->DEFAULT:Landroid/graphics/Typeface;

    invoke-virtual {v3, v0}, Landroid/text/TextPaint;->setTypeface(Landroid/graphics/Typeface;)Landroid/graphics/Typeface;

    int-to-float p2, p2

    invoke-virtual {v3, p2}, Landroid/text/TextPaint;->setTextSize(F)V

    const/4 p2, -0x1

    invoke-virtual {v3, p2}, Landroid/text/TextPaint;->setColor(I)V

    sget p2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v0, 0x15

    if-lt p2, v0, :cond_1

    const p2, 0x3cf5c28f    # 0.03f

    invoke-virtual {v3, p2}, Landroid/text/TextPaint;->setLetterSpacing(F)V

    :cond_1
    new-instance p2, Landroid/text/StaticLayout;

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-static {v0}, Lcom/meicam/themehelper/Utils;->getScreenWidth(Landroid/content/Context;)I

    move-result v4

    sget-object v5, Landroid/text/Layout$Alignment;->ALIGN_CENTER:Landroid/text/Layout$Alignment;

    const/high16 v6, 0x3f800000    # 1.0f

    const/4 v7, 0x0

    const/4 v8, 0x1

    move-object v1, p2

    move-object v2, p1

    invoke-direct/range {v1 .. v8}, Landroid/text/StaticLayout;-><init>(Ljava/lang/CharSequence;Landroid/text/TextPaint;ILandroid/text/Layout$Alignment;FFZ)V

    invoke-virtual {p2}, Landroid/text/StaticLayout;->getWidth()I

    move-result p1

    invoke-virtual {p2}, Landroid/text/StaticLayout;->getHeight()I

    move-result v0

    sget-object v1, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {p1, v0, v1}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object p1

    new-instance v0, Landroid/graphics/Canvas;

    invoke-direct {v0, p1}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    invoke-virtual {p2, v0}, Landroid/text/StaticLayout;->draw(Landroid/graphics/Canvas;)V

    invoke-direct {p0, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->saveBitmapToSD(Landroid/graphics/Bitmap;)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_2
    :goto_0
    const/4 p1, 0x0

    return-object p1
.end method

.method private getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
    .locals 1

    const/4 v0, 0x0

    :try_start_0
    invoke-virtual {p1, p2}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p1

    invoke-virtual {p1}, Lorg/json/JSONException;->printStackTrace()V

    move-object p1, v0

    :goto_0
    if-eqz p1, :cond_2

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result p2

    if-eqz p2, :cond_0

    goto :goto_1

    :cond_0
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v0, "assets:/"

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "/"

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    if-eqz p4, :cond_1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    :cond_1
    return-object p2

    :cond_2
    :goto_1
    return-object v0
.end method

.method private getClipCountInDuation(Ljava/util/ArrayList;J)I
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsMusicPointDesc;",
            ">;J)I"
        }
    .end annotation

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x1

    if-ge v0, v1, :cond_0

    invoke-direct {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->getMinImgCount()I

    move-result p1

    return p1

    :cond_0
    add-int/lit8 v2, v0, -0x1

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget v2, v2, Lcom/meicam/themehelper/NvsMusicPointDesc;->cutPoint:I

    mul-int/lit16 v2, v2, 0x3e8

    int-to-long v2, v2

    cmp-long v2, p2, v2

    if-lez v2, :cond_1

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    add-int/2addr p1, v1

    return p1

    :cond_1
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v2

    sub-int/2addr v2, v1

    :goto_0
    if-ltz v2, :cond_3

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget v3, v3, Lcom/meicam/themehelper/NvsMusicPointDesc;->cutPoint:I

    mul-int/lit16 v3, v3, 0x3e8

    int-to-long v3, v3

    iget-wide v5, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    add-long/2addr v5, p2

    const-wide/16 v7, 0x1

    sub-long/2addr v5, v7

    cmp-long v3, v3, v5

    if-gtz v3, :cond_2

    add-int/lit8 v0, v2, 0x1

    goto :goto_1

    :cond_2
    add-int/lit8 v2, v2, -0x1

    goto :goto_0

    :cond_3
    :goto_1
    return v0
.end method

.method private getFxXml(ILcom/meicam/sdk/NvsVideoTrack;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Lcom/meicam/themehelper/NvsMusicPointDesc;)Ljava/lang/String;
    .locals 10

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iget-object v1, p4, Lcom/meicam/themehelper/NvsMusicPointDesc;->fxNames:Ljava/util/ArrayList;

    const/4 v2, 0x0

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    const-string v3, ""

    invoke-virtual {v1, v3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    return-object v3

    :cond_0
    invoke-direct {p0, p1, p2}, Lcom/meicam/themehelper/NvsThemeHelper;->getTransType(ILcom/meicam/sdk/NvsVideoTrack;)Ljava/lang/String;

    move-result-object p1

    const-string p2, "full"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p2

    const-wide v4, 0x3fdebf5114f42816L    # 0.4804270462633452

    const/high16 v1, 0x3f000000    # 0.5f

    const-wide/high16 v6, 0x3fe2000000000000L    # 0.5625

    if-eqz p2, :cond_4

    sget p1, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v8, p1

    cmpl-double p2, v8, v6

    if-ltz p2, :cond_1

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v16List:Ljava/util/ArrayList;

    goto/16 :goto_0

    :cond_1
    cmpl-float p2, p1, v1

    if-ltz p2, :cond_2

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v18List:Ljava/util/ArrayList;

    goto/16 :goto_0

    :cond_2
    float-to-double p1, p1

    cmpl-double p1, p1, v4

    if-ltz p1, :cond_3

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9vx73List:Ljava/util/ArrayList;

    goto/16 :goto_0

    :cond_3
    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v19List:Ljava/util/ArrayList;

    goto/16 :goto_0

    :cond_4
    const-string p2, "half"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_8

    sget p1, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v8, p1

    cmpl-double p2, v8, v6

    if-ltz p2, :cond_5

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v16List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_5
    cmpl-float p2, p1, v1

    if-ltz p2, :cond_6

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v18List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_6
    float-to-double p1, p1

    cmpl-double p1, p1, v4

    if-ltz p1, :cond_7

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9vx73List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_7
    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v19List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_8
    const-string p2, "hf"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_c

    sget p1, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v8, p1

    cmpl-double p2, v8, v6

    if-ltz p2, :cond_9

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v16List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_9
    cmpl-float p2, p1, v1

    if-ltz p2, :cond_a

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v18List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_a
    float-to-double p1, p1

    cmpl-double p1, p1, v4

    if-ltz p1, :cond_b

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9vx73List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_b
    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v19List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_c
    const-string p2, "fh"

    invoke-virtual {p1, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_10

    sget p1, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v8, p1

    cmpl-double p2, v8, v6

    if-ltz p2, :cond_d

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v16List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_d
    cmpl-float p2, p1, v1

    if-ltz p2, :cond_e

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v18List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_e
    float-to-double p1, p1

    cmpl-double p1, p1, v4

    if-ltz p1, :cond_f

    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9vx73List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_f
    iget-object v0, p3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v19List:Ljava/util/ArrayList;

    :cond_10
    :goto_0
    move p1, v2

    :goto_1
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p2

    if-ge p1, p2, :cond_12

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    iget-object p3, p4, Lcom/meicam/themehelper/NvsMusicPointDesc;->fxNames:Ljava/util/ArrayList;

    invoke-virtual {p3, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/CharSequence;

    invoke-virtual {p2, p3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p2

    if-eqz p2, :cond_11

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    move-object v3, p1

    check-cast v3, Ljava/lang/String;

    goto :goto_2

    :cond_11
    add-int/lit8 p1, p1, 0x1

    goto :goto_1

    :cond_12
    :goto_2
    return-object v3
.end method

.method private getLicFilePath(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    const-string v0, "\\."

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    array-length v0, p1

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    const/4 v1, 0x0

    aget-object p1, p1, v1

    invoke-direct {v0, p1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const-string p1, ".lic"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method private getMaxImgCount()I
    .locals 1

    const/16 v0, 0x14

    return v0
.end method

.method private getMinImgCount()I
    .locals 2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-boolean v0, v0, Lcom/meicam/themehelper/NvsImageFileDesc;->isCover:Z

    if-eqz v0, :cond_0

    const/4 v0, 0x4

    goto :goto_0

    :cond_0
    const/4 v0, 0x3

    :goto_0
    return v0
.end method

.method private getRebuildFileList(Ljava/util/ArrayList;Ljava/util/ArrayList;I)Ljava/util/ArrayList;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsMusicPointDesc;",
            ">;I)",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;"
        }
    .end annotation

    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x1

    if-le p3, v0, :cond_0

    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result p2

    add-int/lit8 p3, p2, 0x1

    :cond_0
    invoke-direct {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->getMaxImgCount()I

    move-result p2

    if-le p3, p2, :cond_1

    invoke-direct {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->getMaxImgCount()I

    move-result p3

    :cond_1
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    const/4 v0, -0x1

    const/4 v2, 0x0

    if-ne p3, v0, :cond_3

    move p3, v2

    :goto_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p3, v0, :cond_6

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-boolean v1, v0, Lcom/meicam/themehelper/NvsImageFileDesc;->show:Z

    if-eqz v1, :cond_2

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_2
    add-int/lit8 p3, p3, 0x1

    goto :goto_0

    :cond_3
    const/4 v0, -0x2

    if-ne p3, v0, :cond_4

    move p3, v2

    :goto_1
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p3, v0, :cond_6

    invoke-virtual {p1, p3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsImageFileDesc;

    iput-boolean v1, v0, Lcom/meicam/themehelper/NvsImageFileDesc;->show:Z

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 p3, p3, 0x1

    goto :goto_1

    :cond_4
    move v0, v2

    :goto_2
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v0, v3, :cond_6

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/meicam/themehelper/NvsImageFileDesc;

    if-lt v0, p3, :cond_5

    iput-boolean v2, v3, Lcom/meicam/themehelper/NvsImageFileDesc;->show:Z

    goto :goto_3

    :cond_5
    iput-boolean v1, v3, Lcom/meicam/themehelper/NvsImageFileDesc;->show:Z

    invoke-virtual {p2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_3
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    :cond_6
    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result p1

    const/16 p3, 0x14

    if-le p1, p3, :cond_7

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    :goto_4
    if-ge v2, p3, :cond_8

    invoke-virtual {p2, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v2, v2, 0x1

    goto :goto_4

    :cond_7
    move-object p1, p2

    :cond_8
    return-object p1
.end method

.method private getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/lang/String;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/StringBuilder;",
            ">;)",
            "Ljava/lang/String;"
        }
    .end annotation

    invoke-virtual {p1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    const-string v1, ""

    if-eqz v0, :cond_0

    return-object v1

    :cond_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-direct {p0, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->hasNullTrans(Ljava/util/ArrayList;)Z

    move-result v2

    if-eqz v2, :cond_1

    mul-int/lit8 v0, v0, 0x2

    :cond_1
    sget-object v2, Lcom/meicam/themehelper/NvsThemeHelper;->rand:Ljava/util/Random;

    invoke-virtual {v2, v0}, Ljava/util/Random;->nextInt(I)I

    move-result v0

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v0, v2, :cond_4

    if-ltz v0, :cond_4

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    if-eqz p2, :cond_3

    invoke-virtual {p2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_3

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge v0, v1, :cond_3

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_2
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_3
    return-object p1

    :cond_4
    return-object v1
.end method

.method private getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/StringBuilder;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;)",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    invoke-virtual {p1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    const-string v2, ""

    if-eqz v1, :cond_0

    invoke-virtual {p2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v1

    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v3

    add-int/2addr v1, v3

    invoke-direct {p0, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->hasNullTrans(Ljava/util/ArrayList;)Z

    move-result v3

    if-eqz v3, :cond_1

    mul-int/lit8 v1, v1, 0x2

    :cond_1
    sget-object v3, Lcom/meicam/themehelper/NvsThemeHelper;->rand:Ljava/util/Random;

    invoke-virtual {v3, v1}, Ljava/util/Random;->nextInt(I)I

    move-result v1

    if-gez v1, :cond_2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_2
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v3

    const/4 v4, 0x0

    if-ge v1, v3, :cond_5

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    if-eqz p3, :cond_4

    invoke-virtual {p3}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p2

    if-nez p2, :cond_4

    :goto_0
    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result p2

    if-ge v4, p2, :cond_4

    invoke-virtual {p3, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_3

    invoke-virtual {p3, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_3
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    :cond_4
    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_5
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v3

    sub-int/2addr v1, v3

    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v1, v3, :cond_9

    if-ltz v1, :cond_9

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    invoke-virtual {p2, v2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_6

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_6
    if-eqz p4, :cond_8

    invoke-virtual {p4}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_8

    :goto_1
    invoke-virtual {p4}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-ge v4, v1, :cond_8

    invoke-virtual {p4, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v1}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1, p2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_7

    invoke-virtual {p4, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    invoke-virtual {p2}, Ljava/lang/String;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    invoke-direct {p0, p1, p3}, Lcom/meicam/themehelper/NvsThemeHelper;->getTransFollowFx(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_7
    add-int/lit8 v4, v4, 0x1

    goto :goto_1

    :cond_8
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0

    :cond_9
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-object v0
.end method

.method private getTransFollowFx(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/lang/String;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/StringBuilder;",
            ">;)",
            "Ljava/lang/String;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v2, v3, :cond_1

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v3

    const/16 v4, 0x1e

    if-ge v3, v4, :cond_0

    goto :goto_1

    :cond_0
    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p1

    const-string v2, ""

    if-eqz p1, :cond_2

    return-object v2

    :cond_2
    sget-object p1, Lcom/meicam/themehelper/NvsThemeHelper;->rand:Ljava/util/Random;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    invoke-virtual {p1, v3}, Ljava/util/Random;->nextInt(I)I

    move-result p1

    if-ltz p1, :cond_5

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-lt p1, v3, :cond_3

    goto :goto_3

    :cond_3
    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    :goto_2
    invoke-virtual {p2}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v1, v0, :cond_5

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    invoke-virtual {p2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_4
    add-int/lit8 v1, v1, 0x1

    goto :goto_2

    :cond_5
    :goto_3
    return-object v2
.end method

.method private getTransId(Ljava/lang/String;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)Ljava/lang/String;
    .locals 5

    const/4 v0, 0x0

    if-nez p2, :cond_0

    return-object v0

    :cond_0
    iget-object v1, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    if-nez v1, :cond_1

    return-object v0

    :cond_1
    const/4 v1, 0x0

    :goto_0
    iget-object v2, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_3

    iget-object v2, p2, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const-string v3, ".videotransition"

    const-string v4, ""

    invoke-virtual {p1, v3, v4}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_2

    return-object v2

    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_3
    return-object v0
.end method

.method private getTransType(ILcom/meicam/sdk/NvsVideoTrack;)Ljava/lang/String;
    .locals 1

    invoke-virtual {p2, p1}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v0

    add-int/lit8 p1, p1, 0x1

    invoke-virtual {p2, p1}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object p1

    const-string p2, "fullscreenMode"

    invoke-virtual {v0, p2}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p1, p2}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    const-string p2, "true"

    invoke-virtual {v0, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-virtual {p1, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_0

    const-string p1, "full"

    return-object p1

    :cond_0
    const-string p1, "fh"

    return-object p1

    :cond_1
    invoke-virtual {p1, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_2

    const-string p1, "hf"

    return-object p1

    :cond_2
    const-string p1, "half"

    return-object p1
.end method

.method private hasNullTrans(Ljava/util/ArrayList;)Z
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/lang/String;",
            ">;)Z"
        }
    .end annotation

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_1

    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    const-string v3, ""

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    const/4 p1, 0x1

    return p1

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    return v0
.end method

.method private installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;
    .locals 13

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    const/4 v1, 0x0

    if-eqz v0, :cond_8

    if-nez p1, :cond_0

    goto/16 :goto_2

    :cond_0
    invoke-direct {p0, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->getLicFilePath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const/4 v2, 0x4

    const-string v3, ".captionstyle"

    invoke-virtual {p1, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    const/4 v8, 0x2

    if-eqz v3, :cond_1

    move v9, v8

    goto :goto_1

    :cond_1
    const-string v3, ".videofx"

    invoke-virtual {p1, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_3

    const/4 v2, 0x0

    :cond_2
    :goto_0
    move v9, v2

    goto :goto_1

    :cond_3
    const-string v3, ".videotransition"

    invoke-virtual {p1, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_4

    const/4 v2, 0x1

    goto :goto_0

    :cond_4
    const-string v3, ".animatedsticker"

    invoke-virtual {p1, v3}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_2

    const/4 v2, 0x3

    goto :goto_0

    :goto_1
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "Failed to upgrade package!"

    const-string v12, "NvsThemeHelper"

    if-eqz p2, :cond_5

    iget-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    invoke-virtual {p2}, Lcom/meicam/sdk/NvsStreamingContext;->getAssetPackageManager()Lcom/meicam/sdk/NvsAssetPackageManager;

    move-result-object v2

    const/4 v6, 0x1

    move-object v3, p1

    move-object v4, v0

    move v5, v9

    move-object v7, v10

    invoke-virtual/range {v2 .. v7}, Lcom/meicam/sdk/NvsAssetPackageManager;->upgradeAssetPackage(Ljava/lang/String;Ljava/lang/String;IZLjava/lang/StringBuilder;)I

    move-result p1

    if-eqz p1, :cond_7

    invoke-static {v12, v11}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-object v1

    :cond_5
    iget-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    invoke-virtual {p2}, Lcom/meicam/sdk/NvsStreamingContext;->getAssetPackageManager()Lcom/meicam/sdk/NvsAssetPackageManager;

    move-result-object v2

    const/4 v6, 0x1

    move-object v3, p1

    move-object v4, v0

    move v5, v9

    move-object v7, v10

    invoke-virtual/range {v2 .. v7}, Lcom/meicam/sdk/NvsAssetPackageManager;->installAssetPackage(Ljava/lang/String;Ljava/lang/String;IZLjava/lang/StringBuilder;)I

    move-result p2

    if-eqz p2, :cond_6

    if-eq p2, v8, :cond_6

    const-string p1, "Failed to install package!"

    invoke-static {v12, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-object v1

    :cond_6
    if-ne p2, v8, :cond_7

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-direct {p0, p2, p1, v9}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result p2

    if-eqz p2, :cond_7

    iget-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    invoke-virtual {p2}, Lcom/meicam/sdk/NvsStreamingContext;->getAssetPackageManager()Lcom/meicam/sdk/NvsAssetPackageManager;

    move-result-object v2

    const/4 v6, 0x1

    move-object v3, p1

    move-object v4, v0

    move v5, v9

    move-object v7, v10

    invoke-virtual/range {v2 .. v7}, Lcom/meicam/sdk/NvsAssetPackageManager;->upgradeAssetPackage(Ljava/lang/String;Ljava/lang/String;IZLjava/lang/StringBuilder;)I

    move-result p1

    if-eqz p1, :cond_7

    invoke-static {v12, v11}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return-object v1

    :cond_7
    return-object v10

    :cond_8
    :goto_2
    return-object v1
.end method

.method private installThemeAsset(Landroid/content/Context;Ljava/lang/String;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)Z
    .locals 16

    move-object/from16 v1, p0

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    const-string v4, "/black_block.png"

    const-string v5, "assets:/"

    const-string v0, "blurWithFxTrans"

    const-string v6, "blurFx"

    const-string v7, "cafSticker10s"

    const-string v8, "cafSticker"

    const-string v9, "transOffset"

    const-string v10, "hasFxTrans"

    const-string v11, "/"

    iget-object v12, v1, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    if-nez v12, :cond_0

    invoke-static {}, Lcom/meicam/sdk/NvsStreamingContext;->getInstance()Lcom/meicam/sdk/NvsStreamingContext;

    move-result-object v12

    iput-object v12, v1, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    :cond_0
    iget-object v12, v1, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    const/4 v13, 0x0

    if-nez v12, :cond_1

    return v13

    :cond_1
    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v14, "/info.json"

    invoke-virtual {v12, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    if-nez p1, :cond_2

    const/4 v15, 0x0

    goto :goto_0

    :cond_2
    invoke-virtual/range {p1 .. p1}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v15

    :goto_0
    invoke-static {v12, v15}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v12

    const-string v15, "NvsThemeHelper"

    if-nez v12, :cond_3

    const-string v0, "read theme info json file error!"

    invoke-static {v15, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v13

    :cond_3
    new-instance v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;

    invoke-direct {v14, v1}, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;-><init>(Lcom/meicam/themehelper/NvsThemeHelper;)V

    if-nez p1, :cond_4

    const/4 v13, 0x1

    goto :goto_1

    :cond_4
    const/4 v13, 0x0

    :goto_1
    iput-boolean v13, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->isDownload:Z

    :try_start_0
    new-instance v13, Lorg/json/JSONObject;

    invoke-direct {v13, v12}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-virtual {v13, v10}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v12

    if-eqz v12, :cond_5

    invoke-virtual {v13, v10}, Lorg/json/JSONObject;->getBoolean(Ljava/lang/String;)Z

    move-result v10

    iput-boolean v10, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hasFxTrans:Z

    :cond_5
    invoke-virtual {v13, v9}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v10

    if-eqz v10, :cond_6

    invoke-virtual {v13, v9}, Lorg/json/JSONObject;->getInt(Ljava/lang/String;)I

    move-result v9

    iput v9, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transOffset:I

    goto :goto_2

    :cond_6
    const/16 v9, 0x1f4

    iput v9, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transOffset:I

    :goto_2
    const-string v9, "theme"

    if-nez p1, :cond_7

    const/4 v10, 0x1

    goto :goto_3

    :cond_7
    const/4 v10, 0x0

    :goto_3
    invoke-direct {v1, v13, v9, v2, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v9

    if-nez v3, :cond_8

    const/4 v10, 0x0

    goto :goto_4

    :cond_8
    iget-object v10, v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_themeId:Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    :goto_4
    const/4 v12, 0x4

    invoke-direct {v1, v10, v9, v12}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v10

    invoke-direct {v1, v9, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v9

    iput-object v9, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_themeId:Ljava/lang/StringBuilder;

    iget-object v9, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_themeId:Ljava/lang/StringBuilder;

    if-nez v9, :cond_9

    const-string v0, "Failed to install theme package!"

    invoke-static {v15, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const/4 v2, 0x0

    return v2

    :cond_9
    const-string v9, "theme10s"

    if-nez p1, :cond_a

    const/4 v10, 0x1

    goto :goto_5

    :cond_a
    const/4 v10, 0x0

    :goto_5
    invoke-direct {v1, v13, v9, v2, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v9

    if-nez v3, :cond_b

    const/4 v10, 0x0

    goto :goto_6

    :cond_b
    iget-object v10, v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_theme10sId:Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    :goto_6
    invoke-direct {v1, v10, v9, v12}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v10

    invoke-direct {v1, v9, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v9

    iput-object v9, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_theme10sId:Ljava/lang/StringBuilder;

    iget-object v9, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_theme10sId:Ljava/lang/StringBuilder;

    if-nez v9, :cond_c

    const-string v0, "Failed to install theme 10s package!"

    invoke-static {v15, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const/4 v2, 0x0

    return v2

    :cond_c
    invoke-virtual {v13, v8}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v9

    const/4 v10, 0x3

    if-eqz v9, :cond_f

    if-nez p1, :cond_d

    const/4 v9, 0x1

    goto :goto_7

    :cond_d
    const/4 v9, 0x0

    :goto_7
    invoke-direct {v1, v13, v8, v2, v9}, Lcom/meicam/themehelper/NvsThemeHelper;->getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v8

    if-nez v3, :cond_e

    const/4 v9, 0x0

    goto :goto_8

    :cond_e
    iget-object v9, v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_cafStickerId:Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    :goto_8
    invoke-direct {v1, v9, v8, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v9

    invoke-direct {v1, v8, v9}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v8

    iput-object v8, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_cafStickerId:Ljava/lang/StringBuilder;

    :cond_f
    invoke-virtual {v13, v7}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_12

    if-nez p1, :cond_10

    const/4 v8, 0x1

    goto :goto_9

    :cond_10
    const/4 v8, 0x0

    :goto_9
    invoke-direct {v1, v13, v7, v2, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v7

    if-nez v3, :cond_11

    const/4 v8, 0x0

    goto :goto_a

    :cond_11
    iget-object v8, v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_cafSticker10sId:Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    :goto_a
    invoke-direct {v1, v8, v7, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v8

    invoke-direct {v1, v7, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v7

    iput-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_cafSticker10sId:Ljava/lang/StringBuilder;

    :cond_12
    const-string v7, "sticker1"

    if-nez p1, :cond_13

    const/4 v8, 0x1

    goto :goto_b

    :cond_13
    const/4 v8, 0x0

    :goto_b
    invoke-direct {v1, v13, v7, v2, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v7

    if-nez v3, :cond_14

    const/4 v8, 0x0

    goto :goto_c

    :cond_14
    iget-object v8, v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId1:Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    :goto_c
    invoke-direct {v1, v8, v7, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v8

    invoke-direct {v1, v7, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v7

    iput-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId1:Ljava/lang/StringBuilder;

    iget-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId1:Ljava/lang/StringBuilder;

    if-nez v7, :cond_15

    const-string v0, "Failed to install sticker package!"

    invoke-static {v15, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const/4 v2, 0x0

    return v2

    :cond_15
    iget-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId1:Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    iput-object v7, v1, Lcom/meicam/themehelper/NvsThemeHelper;->m_stickerId1:Ljava/lang/String;

    const-string v7, "sticker2"

    if-nez p1, :cond_16

    const/4 v8, 0x1

    goto :goto_d

    :cond_16
    const/4 v8, 0x0

    :goto_d
    invoke-direct {v1, v13, v7, v2, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v7

    if-nez v3, :cond_17

    const/4 v8, 0x0

    goto :goto_e

    :cond_17
    iget-object v8, v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId2:Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    :goto_e
    invoke-direct {v1, v8, v7, v10}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v8

    invoke-direct {v1, v7, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v7

    iput-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId2:Ljava/lang/StringBuilder;

    iget-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId2:Ljava/lang/StringBuilder;

    if-nez v7, :cond_18

    const-string v0, "Failed to install sticker2 package!"

    invoke-static {v15, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const/4 v2, 0x0

    return v2

    :cond_18
    iget-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_stickerId2:Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    iput-object v7, v1, Lcom/meicam/themehelper/NvsThemeHelper;->m_stickerId2:Ljava/lang/String;

    invoke-virtual {v13, v6}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v7

    if-eqz v7, :cond_1b

    if-nez p1, :cond_19

    const/4 v7, 0x1

    goto :goto_f

    :cond_19
    const/4 v7, 0x0

    :goto_f
    invoke-direct {v1, v13, v6, v2, v7}, Lcom/meicam/themehelper/NvsThemeHelper;->getAssetPath(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v6

    if-nez v3, :cond_1a

    const/4 v7, 0x0

    :goto_10
    const/4 v8, 0x0

    goto :goto_11

    :cond_1a
    iget-object v7, v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_blurFxId:Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    goto :goto_10

    :goto_11
    invoke-direct {v1, v7, v6, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v7

    invoke-direct {v1, v6, v7}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v6

    iput-object v6, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_blurFxId:Ljava/lang/StringBuilder;

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_1b

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v0

    const/4 v6, 0x0

    :goto_12
    invoke-virtual {v0}, Lorg/json/JSONArray;->length()I

    move-result v7

    if-ge v6, v7, :cond_1b

    iget-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_blurWidthFxTransList:Ljava/util/ArrayList;

    invoke-virtual {v0, v6}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v7, v8}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_2

    add-int/lit8 v6, v6, 0x1

    goto :goto_12

    :cond_1b
    :try_start_1
    const-string v0, "endingVideoFX"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    if-nez p1, :cond_1c

    const/4 v6, 0x0

    goto :goto_13

    :cond_1c
    iget-object v6, v1, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {v6}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v6

    :goto_13
    invoke-static {v0, v6}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFxId:Ljava/lang/String;
    :try_end_1
    .catch Lorg/json/JSONException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_14

    :catch_0
    move-exception v0

    :try_start_2
    invoke-virtual {v0}, Lorg/json/JSONException;->printStackTrace()V
    :try_end_2
    .catch Lorg/json/JSONException; {:try_start_2 .. :try_end_2} :catch_2

    :goto_14
    :try_start_3
    const-string v0, "endingVideoFX10s"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    if-nez p1, :cond_1d

    const/4 v6, 0x0

    goto :goto_15

    :cond_1d
    iget-object v6, v1, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {v6}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v6

    :goto_15
    invoke-static {v0, v6}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFxId10s:Ljava/lang/String;
    :try_end_3
    .catch Lorg/json/JSONException; {:try_start_3 .. :try_end_3} :catch_1

    goto :goto_16

    :catch_1
    move-exception v0

    :try_start_4
    invoke-virtual {v0}, Lorg/json/JSONException;->printStackTrace()V

    :goto_16
    const-string v0, "endingVideoFX10sDuration"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getLong(Ljava/lang/String;)J

    move-result-wide v6

    const-wide/16 v8, 0x3e8

    mul-long/2addr v6, v8

    iput-wide v6, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFx10sLen:J

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    if-nez p1, :cond_1e

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    :cond_1e
    iput-object v0, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_endingFxImgPath:Ljava/lang/String;

    const-string v0, "musicrhythm"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    if-nez p1, :cond_1f

    const/4 v4, 0x0

    goto :goto_17

    :cond_1f
    invoke-virtual/range {p1 .. p1}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v4

    :goto_17
    invoke-static {v0, v4}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_20

    iget-object v4, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicPoints:Ljava/util/ArrayList;

    iget-object v6, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transDesc:Lcom/meicam/themehelper/NvsTransDesc;

    invoke-static {v0, v4, v6}, Lcom/meicam/themehelper/NvsParseHelper;->readMusicPoint(Ljava/lang/String;Ljava/util/ArrayList;Lcom/meicam/themehelper/NvsTransDesc;)J

    move-result-wide v6

    iput-wide v6, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicLen:J

    :cond_20
    const-string v0, "musicrhythm10s"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    if-nez p1, :cond_21

    const/4 v4, 0x0

    goto :goto_18

    :cond_21
    invoke-virtual/range {p1 .. p1}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v4

    :goto_18
    invoke-static {v0, v4}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_22

    iget-object v4, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicPoints10s:Ljava/util/ArrayList;

    const/4 v6, 0x0

    invoke-static {v0, v4, v6}, Lcom/meicam/themehelper/NvsParseHelper;->readMusicPoint(Ljava/lang/String;Ljava/util/ArrayList;Lcom/meicam/themehelper/NvsTransDesc;)J

    move-result-wide v6

    iput-wide v6, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicLen10s:J

    :cond_22
    const-string v0, "transition"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_27

    const-string v0, "transition"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v0

    const/4 v4, 0x0

    :goto_19
    invoke-virtual {v0}, Lorg/json/JSONArray;->length()I

    move-result v6

    if-ge v4, v6, :cond_27

    invoke-virtual {v0, v4}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v6

    if-eqz v6, :cond_26

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    if-nez p1, :cond_23

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    :cond_23
    if-eqz v3, :cond_24

    invoke-direct {v1, v6, v3}, Lcom/meicam/themehelper/NvsThemeHelper;->getTransId(Ljava/lang/String;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)Ljava/lang/String;

    move-result-object v6

    const/4 v8, 0x1

    invoke-direct {v1, v6, v7, v8}, Lcom/meicam/themehelper/NvsThemeHelper;->checkUpdateAsset(Ljava/lang/String;Ljava/lang/String;I)Z

    move-result v6

    goto :goto_1a

    :cond_24
    const/4 v6, 0x0

    :goto_1a
    invoke-direct {v1, v7, v6}, Lcom/meicam/themehelper/NvsThemeHelper;->installAssetToContext(Ljava/lang/String;Z)Ljava/lang/StringBuilder;

    move-result-object v6

    if-nez v6, :cond_25

    goto :goto_1b

    :cond_25
    iget-object v7, v14, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-virtual {v7, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_26
    :goto_1b
    add-int/lit8 v4, v4, 0x1

    goto :goto_19

    :cond_27
    const-string v0, "transFx"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->has(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_28

    const-string v0, "transFx"

    invoke-virtual {v13, v0}, Lorg/json/JSONObject;->getJSONArray(Ljava/lang/String;)Lorg/json/JSONArray;

    move-result-object v0

    const/4 v4, 0x0

    :goto_1c
    invoke-virtual {v0}, Lorg/json/JSONArray;->length()I

    move-result v5

    if-ge v4, v5, :cond_28

    invoke-virtual {v0, v4}, Lorg/json/JSONArray;->getString(I)Ljava/lang/String;

    move-result-object v5

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v1, v14, v6, v5}, Lcom/meicam/themehelper/NvsThemeHelper;->addToFxList(Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Ljava/lang/String;Ljava/lang/String;)V
    :try_end_4
    .catch Lorg/json/JSONException; {:try_start_4 .. :try_end_4} :catch_2

    add-int/lit8 v4, v4, 0x1

    goto :goto_1c

    :cond_28
    if-eqz v3, :cond_29

    iget-object v0, v1, Lcom/meicam/themehelper/NvsThemeHelper;->mThemeAssetMap:Ljava/util/Map;

    invoke-interface {v0, v3}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_29
    iget-object v0, v1, Lcom/meicam/themehelper/NvsThemeHelper;->mThemeAssetMap:Ljava/util/Map;

    invoke-interface {v0, v2, v14}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    const/4 v2, 0x1

    return v2

    :catch_2
    move-exception v0

    invoke-virtual {v0}, Lorg/json/JSONException;->printStackTrace()V

    const/4 v2, 0x0

    return v2
.end method

.method private isLargeImg(Lcom/meicam/sdk/NvsSize;)Z
    .locals 7

    new-instance v0, Lcom/meicam/sdk/NvsSize;

    iget v1, p1, Lcom/meicam/sdk/NvsSize;->width:I

    iget v2, p1, Lcom/meicam/sdk/NvsSize;->height:I

    invoke-direct {v0, v1, v2}, Lcom/meicam/sdk/NvsSize;-><init>(II)V

    iget v1, v0, Lcom/meicam/sdk/NvsSize;->width:I

    iget v2, v0, Lcom/meicam/sdk/NvsSize;->height:I

    if-lt v1, v2, :cond_0

    iget v1, p1, Lcom/meicam/sdk/NvsSize;->height:I

    iput v1, v0, Lcom/meicam/sdk/NvsSize;->width:I

    iget p1, p1, Lcom/meicam/sdk/NvsSize;->width:I

    iput p1, v0, Lcom/meicam/sdk/NvsSize;->height:I

    :cond_0
    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsTimeline;->getVideoRes()Lcom/meicam/sdk/NvsVideoResolution;

    move-result-object p1

    iget v1, p1, Lcom/meicam/sdk/NvsVideoResolution;->imageWidth:I

    int-to-double v1, v1

    iget v3, v0, Lcom/meicam/sdk/NvsSize;->width:I

    int-to-double v3, v3

    div-double/2addr v1, v3

    iget p1, p1, Lcom/meicam/sdk/NvsVideoResolution;->imageHeight:I

    int-to-double v3, p1

    iget p1, v0, Lcom/meicam/sdk/NvsSize;->height:I

    int-to-double v5, p1

    div-double/2addr v3, v5

    invoke-static {v1, v2, v3, v4}, Ljava/lang/Math;->max(DD)D

    move-result-wide v1

    const-wide/high16 v3, 0x3ff0000000000000L    # 1.0

    invoke-static {v1, v2, v3, v4}, Ljava/lang/Math;->min(DD)D

    move-result-wide v1

    iget p1, v0, Lcom/meicam/sdk/NvsSize;->width:I

    iget v5, v0, Lcom/meicam/sdk/NvsSize;->height:I

    cmpg-double v3, v1, v3

    if-gez v3, :cond_1

    iget p1, v0, Lcom/meicam/sdk/NvsSize;->width:I

    int-to-double v3, p1

    mul-double/2addr v3, v1

    const-wide/high16 v5, 0x3fe0000000000000L    # 0.5

    add-double/2addr v3, v5

    double-to-int p1, v3

    iget v0, v0, Lcom/meicam/sdk/NvsSize;->height:I

    int-to-double v3, v0

    mul-double/2addr v3, v1

    add-double/2addr v3, v5

    double-to-int v5, v3

    :cond_1
    const/16 v0, 0x2000

    if-gt p1, v0, :cond_3

    if-le v5, v0, :cond_2

    goto :goto_0

    :cond_2
    const/4 p1, 0x0

    return p1

    :cond_3
    :goto_0
    const/4 p1, 0x1

    return p1
.end method

.method private manageString2Length(Ljava/lang/String;I)Ljava/lang/String;
    .locals 8

    if-eqz p1, :cond_4

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    int-to-float p2, p2

    invoke-virtual {v0, p2}, Landroid/graphics/Paint;->setTextSize(F)V

    iget-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-static {p2}, Lcom/meicam/themehelper/Utils;->getScreenWidth(Landroid/content/Context;)I

    move-result p2

    int-to-float p2, p2

    const/high16 v1, 0x42c80000    # 100.0f

    sub-float/2addr p2, v1

    const-string v1, "\ud83d\ude00"

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v2

    const/4 v3, 0x1

    const/4 v4, 0x0

    invoke-virtual {v1, v4, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v1

    sub-float/2addr v2, v1

    move v1, v4

    move v5, v1

    :goto_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v6

    if-ge v1, v6, :cond_2

    add-int/lit8 v5, v1, 0x1

    invoke-virtual {p1, v4, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v6

    cmpl-float v7, v6, p2

    if-ltz v7, :cond_1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p2

    sub-int/2addr p2, v3

    if-gt v1, p2, :cond_3

    invoke-virtual {p1, v4, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v0, p2}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result p2

    sub-float/2addr v6, p2

    cmpg-float p2, v6, v2

    if-gtz p2, :cond_3

    add-int/lit8 v1, v1, -0x1

    goto :goto_1

    :cond_1
    move v1, v5

    goto :goto_0

    :cond_2
    move v1, v5

    :cond_3
    :goto_1
    invoke-virtual {p1, v4, v1}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_4
    :goto_2
    const/4 p1, 0x0

    return-object p1
.end method

.method private manageStringLength(Ljava/lang/String;I)Ljava/lang/String;
    .locals 8

    if-eqz p1, :cond_5

    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_2

    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p1

    new-instance v0, Landroid/graphics/Paint;

    invoke-direct {v0}, Landroid/graphics/Paint;-><init>()V

    int-to-float p2, p2

    invoke-virtual {v0, p2}, Landroid/graphics/Paint;->setTextSize(F)V

    const-string p2, "\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u5341"

    invoke-virtual {v0, p2}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result p2

    const-string v1, "\ud83d\ude00"

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v2

    const/4 v3, 0x1

    const/4 v4, 0x0

    invoke-virtual {v1, v4, v3}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v1

    sub-float/2addr v2, v1

    move v1, v4

    move v5, v1

    :goto_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v6

    if-ge v1, v6, :cond_3

    add-int/lit8 v5, v1, 0x1

    invoke-virtual {p1, v4, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v6

    cmpl-float v7, v6, p2

    if-ltz v7, :cond_2

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p2

    sub-int/2addr p2, v3

    if-ge v1, p2, :cond_1

    add-int/lit8 p2, v1, 0x2

    invoke-virtual {p1, v4, p2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/graphics/Paint;->measureText(Ljava/lang/String;)F

    move-result v0

    sub-float/2addr v0, v6

    cmpg-float v0, v0, v2

    if-gtz v0, :cond_3

    move v5, p2

    goto :goto_1

    :cond_1
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v5

    goto :goto_1

    :cond_2
    move v1, v5

    goto :goto_0

    :cond_3
    :goto_1
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p2

    if-eq v5, p2, :cond_4

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, v4, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "..."

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_4
    invoke-virtual {p1, v4, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p1

    return-object p1

    :cond_5
    :goto_2
    const/4 p1, 0x0

    return-object p1
.end method

.method private reBuildTimeLine(Lcom/meicam/sdk/NvsTimeline;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Ljava/util/ArrayList;JLcom/meicam/themehelper/NvsMusicFileDesc;Z)V
    .locals 22
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/meicam/sdk/NvsTimeline;",
            "Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;J",
            "Lcom/meicam/themehelper/NvsMusicFileDesc;",
            "Z)V"
        }
    .end annotation

    move-object/from16 v9, p0

    move-object/from16 v10, p1

    move-object/from16 v11, p2

    move-object/from16 v0, p3

    move-wide/from16 v6, p4

    move-object/from16 v12, p6

    new-instance v1, Ljava/util/Random;

    iget-wide v2, v9, Lcom/meicam/themehelper/NvsThemeHelper;->seed:J

    invoke-direct {v1, v2, v3}, Ljava/util/Random;-><init>(J)V

    sput-object v1, Lcom/meicam/themehelper/NvsThemeHelper;->rand:Ljava/util/Random;

    invoke-static {}, Lcom/meicam/sdk/NvsStreamingContext;->getInstance()Lcom/meicam/sdk/NvsStreamingContext;

    move-result-object v1

    if-eqz v1, :cond_26

    if-eqz v10, :cond_26

    if-eqz v11, :cond_26

    if-nez v0, :cond_0

    goto/16 :goto_16

    :cond_0
    const/4 v13, 0x0

    invoke-virtual {v1, v13}, Lcom/meicam/sdk/NvsStreamingContext;->clearCachedResources(Z)V

    invoke-virtual/range {p3 .. p3}, Ljava/util/ArrayList;->size()I

    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transDesc:Lcom/meicam/themehelper/NvsTransDesc;

    iget-wide v2, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicLen:J

    iget-object v4, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicPoints:Ljava/util/ArrayList;

    const/4 v14, 0x1

    if-eqz v12, :cond_4

    iget-object v2, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc:Ljava/util/ArrayList;

    if-eqz v2, :cond_1

    iget-object v2, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-lez v2, :cond_1

    iget-object v2, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicFile:Ljava/lang/String;

    if-eqz v2, :cond_1

    iget-object v2, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc:Ljava/util/ArrayList;

    move-object v4, v2

    :cond_1
    iget-object v2, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->transDesc:Lcom/meicam/themehelper/NvsTransDesc;

    if-eqz v2, :cond_2

    iget-object v1, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->transDesc:Lcom/meicam/themehelper/NvsTransDesc;

    :cond_2
    iget v2, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedMusicType:I

    if-ne v2, v14, :cond_3

    move v2, v14

    goto :goto_0

    :cond_3
    move v2, v13

    :goto_0
    iget-wide v14, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicLen:J

    move-object/from16 v19, v1

    move-wide/from16 v17, v14

    const/4 v14, 0x1

    move v15, v2

    goto :goto_1

    :cond_4
    move-object/from16 v19, v1

    move-wide/from16 v17, v2

    move v14, v13

    move v15, v14

    :goto_1
    iput-boolean v13, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    const-wide/16 v1, 0x0

    cmp-long v1, v6, v1

    if-lez v1, :cond_6

    const/4 v2, 0x1

    iput-boolean v2, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    if-eqz v14, :cond_5

    iget-object v4, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc10s:Ljava/util/ArrayList;

    goto :goto_2

    :cond_5
    iget-object v4, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicPoints10s:Ljava/util/ArrayList;

    :cond_6
    :goto_2
    if-lez v1, :cond_7

    invoke-virtual {v4}, Ljava/util/ArrayList;->size()I

    move-result v1

    if-lez v1, :cond_7

    invoke-direct {v9, v4, v6, v7}, Lcom/meicam/themehelper/NvsThemeHelper;->getClipCountInDuation(Ljava/util/ArrayList;J)I

    move-result v1

    goto :goto_3

    :cond_7
    long-to-int v1, v6

    :goto_3
    invoke-direct {v9, v0, v4, v1}, Lcom/meicam/themehelper/NvsThemeHelper;->getRebuildFileList(Ljava/util/ArrayList;Ljava/util/ArrayList;I)Ljava/util/ArrayList;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v0

    const-string v2, "NvsThemeHelper"

    const/4 v3, 0x1

    if-ge v0, v3, :cond_8

    const-string v0, "not any show file!"

    invoke-static {v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :cond_8
    invoke-virtual {v1}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v5, 0x5

    if-gt v0, v5, :cond_a

    iput-boolean v3, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    if-eqz v14, :cond_9

    iget-object v0, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc10s:Ljava/util/ArrayList;

    goto :goto_4

    :cond_9
    iget-object v0, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_musicPoints10s:Ljava/util/ArrayList;

    :goto_4
    move-object v8, v0

    goto :goto_5

    :cond_a
    move-object v8, v4

    :goto_5
    invoke-direct/range {p0 .. p1}, Lcom/meicam/themehelper/NvsThemeHelper;->cleanUpTimeLine(Lcom/meicam/sdk/NvsTimeline;)V

    invoke-virtual/range {p1 .. p1}, Lcom/meicam/sdk/NvsTimeline;->appendVideoTrack()Lcom/meicam/sdk/NvsVideoTrack;

    move-result-object v4

    if-nez v4, :cond_b

    const-string v0, "appendVideoTrack failed!"

    invoke-static {v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-void

    :cond_b
    iget-boolean v5, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    move-object/from16 v0, p0

    move-object v2, v4

    move-object v3, v8

    move-object/from16 p3, v4

    move/from16 v20, v5

    move-wide/from16 v4, v17

    move-wide/from16 v6, p4

    move-object/from16 p4, v8

    move/from16 v8, v20

    invoke-direct/range {v0 .. v8}, Lcom/meicam/themehelper/NvsThemeHelper;->buildVideoTrack(Ljava/util/ArrayList;Lcom/meicam/sdk/NvsVideoTrack;Ljava/util/ArrayList;JJZ)Ljava/util/Map;

    move-result-object v8

    const/4 v7, 0x0

    if-nez p7, :cond_d

    :goto_6
    invoke-virtual/range {p3 .. p3}, Lcom/meicam/sdk/NvsVideoTrack;->getClipCount()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    if-ge v13, v0, :cond_c

    move-object/from16 v6, p3

    invoke-virtual {v6, v13, v7}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    add-int/lit8 v13, v13, 0x1

    goto :goto_6

    :cond_c
    return-void

    :cond_d
    move-object/from16 v6, p3

    iget-boolean v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    if-eqz v0, :cond_e

    iget-object v0, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_theme10sId:Ljava/lang/StringBuilder;

    goto :goto_7

    :cond_e
    iget-object v0, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_themeId:Ljava/lang/StringBuilder;

    :goto_7
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v10, v0}, Lcom/meicam/sdk/NvsTimeline;->applyTheme(Ljava/lang/String;)Z

    iget-boolean v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    if-eqz v0, :cond_f

    iget-object v0, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_cafSticker10sId:Ljava/lang/StringBuilder;

    goto :goto_8

    :cond_f
    iget-object v0, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_cafStickerId:Ljava/lang/StringBuilder;

    :goto_8
    iput-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafStickerId:Ljava/lang/StringBuilder;

    iget-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafStickerId:Ljava/lang/StringBuilder;

    if-eqz v0, :cond_12

    iget-boolean v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_showCaption:Z

    if-eqz v0, :cond_11

    iget-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1Text:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_10

    iget-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2Text:Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_11

    :cond_10
    move v0, v13

    goto :goto_9

    :cond_11
    const/4 v0, 0x1

    :goto_9
    if-eqz v0, :cond_12

    const-wide/16 v1, 0x0

    const-wide v3, 0x400bd70a3d70a3d7L    # 3.48

    move-object/from16 p5, v8

    iget-wide v7, v9, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    long-to-double v7, v7

    mul-double/2addr v7, v3

    double-to-long v3, v7

    iget-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafStickerId:Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    move-object/from16 v0, p1

    invoke-virtual/range {v0 .. v5}, Lcom/meicam/sdk/NvsTimeline;->addAnimatedSticker(JJLjava/lang/String;)Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    move-result-object v0

    iput-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_cafSticker:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    goto :goto_a

    :cond_12
    move-object/from16 p5, v8

    :goto_a
    iput v13, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    move v8, v13

    :goto_b
    invoke-virtual {v6}, Lcom/meicam/sdk/NvsVideoTrack;->getClipCount()I

    move-result v0

    const/4 v1, 0x1

    sub-int/2addr v0, v1

    if-ge v8, v0, :cond_1d

    const/4 v7, 0x0

    invoke-virtual {v6, v8, v7}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    iget v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/2addr v0, v8

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    move-object/from16 v5, p5

    invoke-interface {v5, v0}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v0

    const/16 v1, 0x3e8

    if-eqz v0, :cond_1a

    iget v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/2addr v0, v8

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {v5, v0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget-object v2, v0, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_13

    iget-object v1, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-direct {v9, v6, v8, v1, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->setTrans(Lcom/meicam/sdk/NvsVideoTrack;ILjava/util/ArrayList;Lcom/meicam/themehelper/NvsMusicPointDesc;)V

    goto/16 :goto_e

    :cond_13
    iget-object v2, v0, Lcom/meicam/themehelper/NvsMusicPointDesc;->fxNames:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_19

    invoke-direct {v9, v8, v6, v11, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->getFxXml(ILcom/meicam/sdk/NvsVideoTrack;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Lcom/meicam/themehelper/NvsMusicPointDesc;)Ljava/lang/String;

    move-result-object v3

    const-string v0, ""

    const-string v2, "fxV3"

    invoke-virtual {v3, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_16

    iget v2, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/2addr v2, v8

    add-int/lit8 v2, v2, 0x2

    invoke-virtual/range {p4 .. p4}, Ljava/util/ArrayList;->size()I

    move-result v4

    if-ge v2, v4, :cond_15

    iget v1, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/2addr v1, v8

    add-int/lit8 v1, v1, 0x2

    move-object/from16 v4, p4

    invoke-virtual {v4, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget v2, v1, Lcom/meicam/themehelper/NvsMusicPointDesc;->transLen:I

    iget-object v7, v1, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_14

    iget-object v0, v1, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v0, v13}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    :cond_14
    move-object v7, v0

    goto :goto_c

    :cond_15
    move-object/from16 v4, p4

    move-object v7, v0

    move v2, v1

    :goto_c
    int-to-long v1, v2

    move-object/from16 v0, p0

    move-wide/from16 v17, v1

    move-object v1, v6

    move v2, v8

    move-object/from16 p4, v4

    move-object v4, v7

    move-object/from16 v21, v5

    move-object/from16 v20, v6

    move-wide/from16 v5, v17

    const/16 v17, 0x0

    move-object/from16 v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/meicam/themehelper/NvsThemeHelper;->applyFxTransV3(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JLcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)V

    goto :goto_f

    :cond_16
    move-object/from16 v21, v5

    move-object/from16 v20, v6

    move-object/from16 v17, v7

    iget v2, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/2addr v2, v8

    const/16 v16, 0x1

    add-int/lit8 v2, v2, 0x1

    invoke-virtual/range {p4 .. p4}, Ljava/util/ArrayList;->size()I

    move-result v4

    if-ge v2, v4, :cond_18

    iget v1, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_fxTransClipCount:I

    add-int/2addr v1, v8

    add-int/lit8 v1, v1, 0x1

    move-object/from16 v7, p4

    invoke-virtual {v7, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget v2, v1, Lcom/meicam/themehelper/NvsMusicPointDesc;->transLen:I

    iget-object v4, v1, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v4}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v4

    if-nez v4, :cond_17

    iget-object v0, v1, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v0, v13}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    :cond_17
    move-object v4, v0

    goto :goto_d

    :cond_18
    move-object/from16 v7, p4

    move-object v4, v0

    move v2, v1

    :goto_d
    int-to-long v5, v2

    move-object/from16 v0, p0

    move-object/from16 v1, v20

    move v2, v8

    move-object/from16 p4, v7

    move-object/from16 v7, p2

    invoke-direct/range {v0 .. v7}, Lcom/meicam/themehelper/NvsThemeHelper;->applyFxTransV2(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JLcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)V

    goto :goto_10

    :cond_19
    :goto_e
    move-object/from16 v21, v5

    move-object/from16 v20, v6

    move-object/from16 v17, v7

    :goto_f
    const/16 v16, 0x1

    :goto_10
    move-object/from16 v6, p4

    goto :goto_12

    :cond_1a
    move-object/from16 v21, v5

    move-object/from16 v20, v6

    move-object/from16 v17, v7

    const/16 v16, 0x1

    invoke-virtual/range {p4 .. p4}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge v8, v0, :cond_1b

    move-object/from16 v6, p4

    invoke-virtual {v6, v8}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsMusicPointDesc;

    iget v0, v0, Lcom/meicam/themehelper/NvsMusicPointDesc;->transLen:I

    move v2, v0

    goto :goto_11

    :cond_1b
    move-object/from16 v6, p4

    move v2, v1

    :goto_11
    iget-boolean v0, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hasFxTrans:Z

    if-eqz v0, :cond_1c

    move-object/from16 v0, p0

    move-object/from16 v1, v20

    move-object/from16 v3, v19

    move-object/from16 v4, p2

    move v5, v8

    invoke-direct/range {v0 .. v5}, Lcom/meicam/themehelper/NvsThemeHelper;->setFxTrans(Lcom/meicam/sdk/NvsVideoTrack;ILcom/meicam/themehelper/NvsTransDesc;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;I)V

    goto :goto_12

    :cond_1c
    iget-object v4, v11, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    move-object/from16 v0, p0

    move-object/from16 v1, v20

    move-object/from16 v3, v19

    move v5, v8

    invoke-direct/range {v0 .. v5}, Lcom/meicam/themehelper/NvsThemeHelper;->setTrans(Lcom/meicam/sdk/NvsVideoTrack;ILcom/meicam/themehelper/NvsTransDesc;Ljava/util/ArrayList;I)V

    :goto_12
    add-int/lit8 v8, v8, 0x1

    move-object/from16 p4, v6

    move-object/from16 v6, v20

    move-object/from16 p5, v21

    goto/16 :goto_b

    :cond_1d
    if-eqz v14, :cond_22

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "assets:/"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    if-eqz v1, :cond_1e

    iget-object v1, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicFile10s:Ljava/lang/String;

    goto :goto_13

    :cond_1e
    iget-object v1, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicFile:Ljava/lang/String;

    :goto_13
    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    if-nez v15, :cond_1f

    iget-boolean v1, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->isDownloadFile:Z

    if-eqz v1, :cond_21

    :cond_1f
    iget-boolean v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    if-eqz v0, :cond_20

    iget-object v0, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicFile10s:Ljava/lang/String;

    goto :goto_14

    :cond_20
    iget-object v0, v12, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicFile:Ljava/lang/String;

    :cond_21
    :goto_14
    invoke-direct {v9, v10, v0, v15}, Lcom/meicam/themehelper/NvsThemeHelper;->addMusicFile(Lcom/meicam/sdk/NvsTimeline;Ljava/lang/String;Z)V

    :cond_22
    invoke-direct/range {p0 .. p1}, Lcom/meicam/themehelper/NvsThemeHelper;->addCaption(Lcom/meicam/sdk/NvsTimeline;)V

    iget-boolean v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    invoke-direct {v9, v10, v11, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->addEndingFx(Lcom/meicam/sdk/NvsTimeline;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Z)V

    iget v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedMusicType:I

    if-nez v0, :cond_23

    iget-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    iget v1, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    invoke-virtual {v0, v1, v1}, Lcom/meicam/sdk/NvsTimeline;->setThemeMusicVolumeGain(FF)V

    goto :goto_15

    :cond_23
    iget-object v0, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    invoke-virtual {v0, v13}, Lcom/meicam/sdk/NvsTimeline;->getAudioTrackByIndex(I)Lcom/meicam/sdk/NvsAudioTrack;

    move-result-object v0

    if-eqz v0, :cond_24

    iget v1, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    invoke-virtual {v0, v1, v1}, Lcom/meicam/sdk/NvsAudioTrack;->setVolumeGain(FF)V

    :cond_24
    :goto_15
    const-wide/16 v0, 0x5

    iget-wide v2, v9, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    mul-long/2addr v0, v2

    iget-boolean v4, v9, Lcom/meicam/themehelper/NvsThemeHelper;->m_is10sMode:Z

    if-eqz v4, :cond_25

    const-wide/high16 v0, 0x3ff8000000000000L    # 1.5

    long-to-double v2, v2

    mul-double/2addr v2, v0

    double-to-long v0, v2

    :cond_25
    invoke-virtual {v10, v0, v1}, Lcom/meicam/sdk/NvsTimeline;->setAudioFadeOutDuration(J)V

    :cond_26
    :goto_16
    return-void
.end method

.method private reBuildTimeLineExt(JZ)V
    .locals 9

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mThemeAssetMap:Ljava/util/Map;

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_themeAssetID:Ljava/lang/String;

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    move-object v3, v0

    check-cast v3, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;

    if-nez v3, :cond_0

    return-void

    :cond_0
    iget v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedExtMusicIdx:I

    const/4 v1, 0x0

    if-ltz v0, :cond_1

    iget-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mMusicFileList:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v0, v2, :cond_1

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mMusicFileList:Ljava/util/ArrayList;

    iget v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedExtMusicIdx:I

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsMusicFileDesc;

    :goto_0
    move-object v7, v0

    goto :goto_1

    :cond_1
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusic:Ljava/lang/String;

    if-eqz v0, :cond_4

    new-instance v0, Lcom/meicam/themehelper/NvsMusicFileDesc;

    invoke-direct {v0}, Lcom/meicam/themehelper/NvsMusicFileDesc;-><init>()V

    iget-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusic:Ljava/lang/String;

    iput-object v2, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicFile:Ljava/lang/String;

    iput-object v2, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicFile10s:Ljava/lang/String;

    iget-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythmPath:Ljava/lang/String;

    iget-object v4, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {v4}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v4

    invoke-static {v2, v4}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_2

    iget-object v4, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc:Ljava/util/ArrayList;

    iget-object v5, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->transDesc:Lcom/meicam/themehelper/NvsTransDesc;

    invoke-static {v2, v4, v5}, Lcom/meicam/themehelper/NvsParseHelper;->readMusicPoint(Ljava/lang/String;Ljava/util/ArrayList;Lcom/meicam/themehelper/NvsTransDesc;)J

    move-result-wide v4

    iput-wide v4, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicLen:J

    :cond_2
    iget-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythm10sPath:Ljava/lang/String;

    iget-object v4, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {v4}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v4

    invoke-static {v2, v4}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_3

    iget-object v4, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc10s:Ljava/util/ArrayList;

    invoke-static {v2, v4, v1}, Lcom/meicam/themehelper/NvsParseHelper;->readMusicPoint(Ljava/lang/String;Ljava/util/ArrayList;Lcom/meicam/themehelper/NvsTransDesc;)J

    move-result-wide v4

    iput-wide v4, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicLen10s:J

    :cond_3
    iput-object v1, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->jsonFile:Ljava/lang/String;

    iput-object v1, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->jsonFile10s:Ljava/lang/String;

    goto :goto_0

    :cond_4
    move-object v7, v1

    :goto_1
    iget-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    iget-object v4, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    move-object v1, p0

    move-wide v5, p1

    move v8, p3

    invoke-direct/range {v1 .. v8}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLine(Lcom/meicam/sdk/NvsTimeline;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Ljava/util/ArrayList;JLcom/meicam/themehelper/NvsMusicFileDesc;Z)V

    return-void
.end method

.method private saveBitmapToSD(Landroid/graphics/Bitmap;)Ljava/lang/String;
    .locals 6

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    const-string v1, "meicam"

    const/4 v2, 0x0

    if-nez v0, :cond_0

    const-string p1, "m_context is null,can not make path!"

    invoke-static {v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-object v2

    :cond_0
    new-instance v3, Ljava/io/File;

    invoke-virtual {v0}, Landroid/content/Context;->getCacheDir()Ljava/io/File;

    move-result-object v0

    const-string v4, "caption_bitmap"

    invoke-direct {v3, v0, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_1

    invoke-virtual {v3}, Ljava/io/File;->mkdirs()Z

    move-result v0

    if-nez v0, :cond_1

    const-string p1, "Failed to make caption bitmap directory"

    invoke-static {v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-object v2

    :cond_1
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v4

    invoke-static {v4, v5}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ".png"

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    new-instance v4, Ljava/io/File;

    invoke-direct {v4, v3, v0}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v4}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-virtual {v4}, Ljava/io/File;->delete()Z

    :cond_2
    invoke-virtual {v4}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    if-eqz p1, :cond_4

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_3

    goto :goto_1

    :cond_3
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    :try_start_0
    new-instance v3, Ljava/io/FileOutputStream;

    invoke-direct {v3, v1}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    sget-object v1, Landroid/graphics/Bitmap$CompressFormat;->PNG:Landroid/graphics/Bitmap$CompressFormat;

    const/16 v4, 0x64

    invoke-virtual {p1, v1, v4, v3}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z

    invoke-virtual {v3}, Ljava/io/FileOutputStream;->flush()V

    invoke-virtual {v3}, Ljava/io/FileOutputStream;->close()V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception p1

    invoke-virtual {p1}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_0

    :catch_1
    move-exception p1

    invoke-virtual {p1}, Ljava/io/FileNotFoundException;->printStackTrace()V

    :goto_0
    return-object v2

    :cond_4
    :goto_1
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "bt == null "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-nez p1, :cond_5

    const/4 p1, 0x1

    goto :goto_2

    :cond_5
    const/4 p1, 0x0

    :goto_2
    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string p1, " target_path.isEmpty(): "

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result p1

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-object v2
.end method

.method private setFxTrans(Lcom/meicam/sdk/NvsVideoTrack;ILcom/meicam/themehelper/NvsTransDesc;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;I)V
    .locals 14

    move-object v8, p0

    move-object v1, p1

    move/from16 v0, p2

    move-object/from16 v2, p3

    move-object/from16 v7, p4

    move/from16 v3, p5

    invoke-direct {p0, v3, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->getTransType(ILcom/meicam/sdk/NvsVideoTrack;)Ljava/lang/String;

    move-result-object v4

    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    new-instance v6, Ljava/util/ArrayList;

    invoke-direct {v6}, Ljava/util/ArrayList;-><init>()V

    const-string v6, "full"

    invoke-virtual {v4, v6}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    const-wide v9, 0x3fdebf5114f42816L    # 0.4804270462633452

    const/high16 v11, 0x3f000000    # 0.5f

    const-wide/high16 v12, 0x3fe2000000000000L    # 0.5625

    if-eqz v6, :cond_3

    sget v4, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v5, v4

    cmpl-double v5, v5, v12

    if-ltz v5, :cond_0

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v16List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_0
    cmpl-float v5, v4, v11

    if-ltz v5, :cond_1

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v18List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_1
    float-to-double v4, v4

    cmpl-double v4, v4, v9

    if-ltz v4, :cond_2

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9vx73List:Ljava/util/ArrayList;

    goto :goto_0

    :cond_2
    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fullFx9v19List:Ljava/util/ArrayList;

    :goto_0
    iget-object v5, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_fulltransList:Ljava/util/ArrayList;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_fulltransFxList:Ljava/util/ArrayList;

    iget-object v6, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-direct {p0, v5, v2, v6, v4}, Lcom/meicam/themehelper/NvsThemeHelper;->getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;

    move-result-object v5

    goto/16 :goto_4

    :cond_3
    const-string v6, "half"

    invoke-virtual {v4, v6}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_7

    sget v4, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v5, v4

    cmpl-double v5, v5, v12

    if-ltz v5, :cond_4

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v16List:Ljava/util/ArrayList;

    goto :goto_1

    :cond_4
    cmpl-float v5, v4, v11

    if-ltz v5, :cond_5

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v18List:Ljava/util/ArrayList;

    goto :goto_1

    :cond_5
    float-to-double v4, v4

    cmpl-double v4, v4, v9

    if-ltz v4, :cond_6

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9vx73List:Ljava/util/ArrayList;

    goto :goto_1

    :cond_6
    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_halfFx9v19List:Ljava/util/ArrayList;

    :goto_1
    iget-object v5, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_halftransList:Ljava/util/ArrayList;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_halftransFxList:Ljava/util/ArrayList;

    iget-object v6, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-direct {p0, v5, v2, v6, v4}, Lcom/meicam/themehelper/NvsThemeHelper;->getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;

    move-result-object v5

    goto :goto_4

    :cond_7
    const-string v6, "fh"

    invoke-virtual {v4, v6}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v6

    if-eqz v6, :cond_b

    sget v4, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v5, v4

    cmpl-double v5, v5, v12

    if-ltz v5, :cond_8

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v16List:Ljava/util/ArrayList;

    goto :goto_2

    :cond_8
    cmpl-float v5, v4, v11

    if-ltz v5, :cond_9

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v18List:Ljava/util/ArrayList;

    goto :goto_2

    :cond_9
    float-to-double v4, v4

    cmpl-double v4, v4, v9

    if-ltz v4, :cond_a

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9vx73List:Ljava/util/ArrayList;

    goto :goto_2

    :cond_a
    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_fhFx9v19List:Ljava/util/ArrayList;

    :goto_2
    iget-object v5, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_hftransList:Ljava/util/ArrayList;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_fhtransFxList:Ljava/util/ArrayList;

    iget-object v6, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-direct {p0, v5, v2, v6, v4}, Lcom/meicam/themehelper/NvsThemeHelper;->getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;

    move-result-object v5

    goto :goto_4

    :cond_b
    const-string v6, "hf"

    invoke-virtual {v4, v6}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_f

    sget v4, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    float-to-double v5, v4

    cmpl-double v5, v5, v12

    if-ltz v5, :cond_c

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v16List:Ljava/util/ArrayList;

    goto :goto_3

    :cond_c
    cmpl-float v5, v4, v11

    if-ltz v5, :cond_d

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v18List:Ljava/util/ArrayList;

    goto :goto_3

    :cond_d
    float-to-double v4, v4

    cmpl-double v4, v4, v9

    if-ltz v4, :cond_e

    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9vx73List:Ljava/util/ArrayList;

    goto :goto_3

    :cond_e
    iget-object v4, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_hfFx9v19List:Ljava/util/ArrayList;

    :goto_3
    iget-object v5, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_hftransList:Ljava/util/ArrayList;

    iget-object v2, v2, Lcom/meicam/themehelper/NvsTransDesc;->m_hftransFxList:Ljava/util/ArrayList;

    iget-object v6, v7, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;->m_transIDList:Ljava/util/ArrayList;

    invoke-direct {p0, v5, v2, v6, v4}, Lcom/meicam/themehelper/NvsThemeHelper;->getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList;

    move-result-object v5

    :cond_f
    :goto_4
    invoke-virtual {v5}, Ljava/util/ArrayList;->size()I

    move-result v2

    const/4 v4, 0x0

    const/4 v6, 0x1

    if-le v2, v6, :cond_11

    invoke-virtual {v5, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    move-object v4, v2

    check-cast v4, Ljava/lang/String;

    invoke-virtual {v5, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    move-object v5, v2

    check-cast v5, Ljava/lang/String;

    const-string v2, "fxV3"

    invoke-virtual {v4, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_10

    int-to-long v9, v0

    move-object v0, p0

    move-object v1, p1

    move/from16 v2, p5

    move-object v3, v4

    move-object v4, v5

    move-wide v5, v9

    move-object/from16 v7, p4

    invoke-direct/range {v0 .. v7}, Lcom/meicam/themehelper/NvsThemeHelper;->applyFxTransV3(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JLcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)V

    goto :goto_6

    :cond_10
    int-to-long v9, v0

    move-object v0, p0

    move-object v1, p1

    move/from16 v2, p5

    move-object v3, v4

    move-object v4, v5

    move-wide v5, v9

    move-object/from16 v7, p4

    invoke-direct/range {v0 .. v7}, Lcom/meicam/themehelper/NvsThemeHelper;->applyFxTransV2(Lcom/meicam/sdk/NvsVideoTrack;ILjava/lang/String;Ljava/lang/String;JLcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)V

    goto :goto_6

    :cond_11
    invoke-virtual {v5, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    const-string v4, ""

    invoke-virtual {v2, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v4

    const/4 v5, 0x0

    if-eqz v4, :cond_12

    invoke-virtual {p1, v3, v5}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_5

    :cond_12
    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v4

    const/16 v5, 0x1e

    if-lt v4, v5, :cond_13

    invoke-virtual {p1, v3, v2}, Lcom/meicam/sdk/NvsVideoTrack;->setPackagedTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v5

    goto :goto_5

    :cond_13
    invoke-virtual {p1, v3, v2}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v5

    :goto_5
    if-eqz v5, :cond_14

    int-to-float v0, v0

    const/high16 v1, 0x447a0000    # 1000.0f

    div-float/2addr v0, v1

    invoke-virtual {v5, v0}, Lcom/meicam/sdk/NvsVideoTransition;->setVideoTransitionDurationScaleFactor(F)V

    :cond_14
    :goto_6
    return-void
.end method

.method private setImageMotion(Lcom/meicam/sdk/NvsVideoClip;Lcom/meicam/themehelper/NvsImageFileDesc;)V
    .locals 6

    if-nez p1, :cond_0

    return-void

    :cond_0
    iget v0, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    const/4 v1, 0x0

    cmpl-float v0, v0, v1

    const/4 v1, 0x0

    if-nez v0, :cond_1

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_streamingContext:Lcom/meicam/sdk/NvsStreamingContext;

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsVideoClip;->getFilePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/meicam/sdk/NvsStreamingContext;->getAVFileInfo(Ljava/lang/String;)Lcom/meicam/sdk/NvsAVFileInfo;

    move-result-object v0

    invoke-static {v0}, Lcom/meicam/themehelper/Utils;->getImgRatio(Lcom/meicam/sdk/NvsAVFileInfo;)F

    move-result v2

    iput v2, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    invoke-virtual {v0, v1}, Lcom/meicam/sdk/NvsAVFileInfo;->getVideoStreamDimension(I)Lcom/meicam/sdk/NvsSize;

    move-result-object v0

    invoke-direct {p0, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->isLargeImg(Lcom/meicam/sdk/NvsSize;)Z

    move-result v0

    iput-boolean v0, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->isLargeImg:Z

    :cond_1
    const/4 v0, 0x0

    iget-boolean v2, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->hasFace:Z

    if-eqz v2, :cond_2

    iget-object v2, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    if-eqz v2, :cond_2

    iget-object v0, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->faceRect:Landroid/graphics/RectF;

    :cond_2
    iget-boolean v2, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->isCover:Z

    if-eqz v2, :cond_3

    iget-object v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->coverStartROI:Landroid/graphics/RectF;

    if-eqz v2, :cond_3

    iget-object v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->coverEndROI:Landroid/graphics/RectF;

    if-eqz v3, :cond_3

    invoke-virtual {p1, v2, v3}, Lcom/meicam/sdk/NvsVideoClip;->setImageMotionROI(Landroid/graphics/RectF;Landroid/graphics/RectF;)V

    const/4 p2, 0x2

    invoke-virtual {p1, p2}, Lcom/meicam/sdk/NvsVideoClip;->setImageMotionMode(I)V

    goto :goto_0

    :cond_3
    iget v2, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->imgRatio:F

    iget-boolean p2, p2, Lcom/meicam/themehelper/NvsImageFileDesc;->isLargeImg:Z

    invoke-static {p1, v2, p2, v0}, Lcom/meicam/themehelper/Utils;->setImageMotion(Lcom/meicam/sdk/NvsVideoClip;FZLandroid/graphics/RectF;)V

    :goto_0
    const/4 p2, 0x1

    invoke-virtual {p1, p2}, Lcom/meicam/sdk/NvsVideoClip;->setSourceBackgroundMode(I)V

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v2

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v4

    sub-long/2addr v2, v4

    iget-wide v4, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    cmp-long p2, v2, v4

    if-gez p2, :cond_4

    invoke-virtual {p1, v1}, Lcom/meicam/sdk/NvsVideoClip;->setImageMotionAnimationEnabled(Z)V

    :cond_4
    return-void
.end method

.method private setTrans(Lcom/meicam/sdk/NvsVideoTrack;ILcom/meicam/themehelper/NvsTransDesc;Ljava/util/ArrayList;I)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/meicam/sdk/NvsVideoTrack;",
            "I",
            "Lcom/meicam/themehelper/NvsTransDesc;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/StringBuilder;",
            ">;I)V"
        }
    .end annotation

    invoke-direct {p0, p5, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->getTransType(ILcom/meicam/sdk/NvsVideoTrack;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "full"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    iget-object p3, p3, Lcom/meicam/themehelper/NvsTransDesc;->m_fulltransList:Ljava/util/ArrayList;

    invoke-direct {p0, p3, p4}, Lcom/meicam/themehelper/NvsThemeHelper;->getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/lang/String;

    move-result-object p3

    goto :goto_0

    :cond_0
    const-string v1, "half"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    iget-object p3, p3, Lcom/meicam/themehelper/NvsTransDesc;->m_halftransList:Ljava/util/ArrayList;

    invoke-direct {p0, p3, p4}, Lcom/meicam/themehelper/NvsThemeHelper;->getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/lang/String;

    move-result-object p3

    goto :goto_0

    :cond_1
    iget-object p3, p3, Lcom/meicam/themehelper/NvsTransDesc;->m_hftransList:Ljava/util/ArrayList;

    invoke-direct {p0, p3, p4}, Lcom/meicam/themehelper/NvsThemeHelper;->getTrans(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/lang/String;

    move-result-object p3

    :goto_0
    const-string p4, ""

    invoke-virtual {p3, p4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p4

    const/4 v0, 0x0

    if-eqz p4, :cond_2

    invoke-virtual {p1, p5, v0}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    goto :goto_1

    :cond_2
    invoke-virtual {p3}, Ljava/lang/String;->length()I

    move-result p4

    const/16 v0, 0x1e

    if-lt p4, v0, :cond_3

    invoke-virtual {p1, p5, p3}, Lcom/meicam/sdk/NvsVideoTrack;->setPackagedTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    goto :goto_1

    :cond_3
    invoke-virtual {p1, p5, p3}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v0

    :goto_1
    if-eqz v0, :cond_4

    int-to-float p1, p2

    const/high16 p2, 0x447a0000    # 1000.0f

    div-float/2addr p1, p2

    invoke-virtual {v0, p1}, Lcom/meicam/sdk/NvsVideoTransition;->setVideoTransitionDurationScaleFactor(F)V

    :cond_4
    return-void
.end method

.method private setTrans(Lcom/meicam/sdk/NvsVideoTrack;ILjava/util/ArrayList;Lcom/meicam/themehelper/NvsMusicPointDesc;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/meicam/sdk/NvsVideoTrack;",
            "I",
            "Ljava/util/ArrayList<",
            "Ljava/lang/StringBuilder;",
            ">;",
            "Lcom/meicam/themehelper/NvsMusicPointDesc;",
            ")V"
        }
    .end annotation

    iget-object v0, p4, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    const/4 v1, 0x0

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    iget-object v0, p4, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p4, Lcom/meicam/themehelper/NvsMusicPointDesc;->transNames:Ljava/util/ArrayList;

    invoke-virtual {v0, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    goto :goto_0

    :cond_0
    move-object v0, v2

    :goto_0
    if-eqz v0, :cond_5

    const-string v3, ""

    invoke-virtual {v0, v3}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_1

    goto :goto_3

    :cond_1
    :goto_1
    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v1, v3, :cond_3

    invoke-virtual {p3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v4

    if-eqz v4, :cond_2

    invoke-virtual {p1, p2, v3}, Lcom/meicam/sdk/NvsVideoTrack;->setPackagedTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v2

    goto :goto_2

    :cond_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    :cond_3
    :goto_2
    if-nez v2, :cond_4

    invoke-virtual {p1, p2, v0}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    move-result-object v2

    :cond_4
    if-eqz v2, :cond_6

    iget p1, p4, Lcom/meicam/themehelper/NvsMusicPointDesc;->transLen:I

    int-to-float p1, p1

    const/high16 p2, 0x447a0000    # 1000.0f

    div-float/2addr p1, p2

    invoke-virtual {v2, p1}, Lcom/meicam/sdk/NvsVideoTransition;->setVideoTransitionDurationScaleFactor(F)V

    goto :goto_4

    :cond_5
    :goto_3
    invoke-virtual {p1, p2, v2}, Lcom/meicam/sdk/NvsVideoTrack;->setBuiltinTransition(ILjava/lang/String;)Lcom/meicam/sdk/NvsVideoTransition;

    :cond_6
    :goto_4
    return-void
.end method

.method private sortFileList(Ljava/util/ArrayList;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;)V"
        }
    .end annotation

    if-eqz p1, :cond_4

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x1

    if-ge v0, v1, :cond_0

    goto :goto_2

    :cond_0
    const/4 v0, 0x0

    const/4 v1, 0x0

    move v2, v1

    :goto_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-ge v2, v3, :cond_2

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-boolean v3, v3, Lcom/meicam/themehelper/NvsImageFileDesc;->isCover:Z

    if-eqz v3, :cond_1

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsImageFileDesc;

    goto :goto_1

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    move v2, v1

    :goto_1
    if-eqz v0, :cond_3

    invoke-virtual {p1, v2}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    :cond_3
    invoke-static {p1}, Lcom/meicam/themehelper/NvsParseHelper;->sortFileByName(Ljava/util/ArrayList;)V

    invoke-static {p1}, Lcom/meicam/themehelper/NvsParseHelper;->sortFileByModifyTime(Ljava/util/ArrayList;)V

    if-eqz v0, :cond_4

    invoke-virtual {p1, v1, v0}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    :cond_4
    :goto_2
    return-void
.end method


# virtual methods
.method public applyTimelineTheme(Ljava/lang/String;ZZ)Z
    .locals 12

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    const-string v1, "NvsThemeHelper"

    const/4 v2, 0x0

    if-eqz v0, :cond_6

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_6

    if-nez p1, :cond_0

    goto :goto_1

    :cond_0
    invoke-virtual {p1}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    return v2

    :cond_1
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mThemeAssetMap:Ljava/util/Map;

    invoke-interface {v0, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;

    const/4 v3, 0x0

    if-eqz p2, :cond_2

    move-object p2, v3

    goto :goto_0

    :cond_2
    iget-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    :goto_0
    invoke-direct {p0, p2, p1, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->installThemeAsset(Landroid/content/Context;Ljava/lang/String;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;)Z

    move-result p2

    if-nez p2, :cond_3

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    const-string p3, "install theme asset error!, id:"

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v2

    :cond_3
    iget-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mThemeAssetMap:Ljava/util/Map;

    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    move-object v6, p2

    check-cast v6, Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;

    if-nez v6, :cond_4

    return v2

    :cond_4
    invoke-static {}, Lcom/meicam/sdk/NvsStreamingContext;->getInstance()Lcom/meicam/sdk/NvsStreamingContext;

    move-result-object p2

    if-eqz p2, :cond_5

    invoke-virtual {p2}, Lcom/meicam/sdk/NvsStreamingContext;->stop()V

    :cond_5
    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_themeAssetID:Ljava/lang/String;

    iput-boolean p3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_showCaption:Z

    iput v2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedMusicType:I

    const/4 p1, -0x1

    iput p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedExtMusicIdx:I

    const/high16 p1, 0x3f800000    # 1.0f

    iput p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    iput-object v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusic:Ljava/lang/String;

    const-wide/16 p1, -0x1

    iput-wide p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicStart:J

    iput-wide p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicEnd:J

    iget-object v5, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    iget-object v7, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    const-wide/16 v8, -0x1

    const/4 v10, 0x0

    const/4 v11, 0x1

    move-object v4, p0

    invoke-direct/range {v4 .. v11}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLine(Lcom/meicam/sdk/NvsTimeline;Lcom/meicam/themehelper/NvsThemeHelper$NvsThemeAsset;Ljava/util/ArrayList;JLcom/meicam/themehelper/NvsMusicFileDesc;Z)V

    const/4 p1, 0x1

    return p1

    :cond_6
    :goto_1
    const-string p1, "helper need init"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v2
.end method

.method public changeCaptionText(Ljava/lang/String;I)Z
    .locals 2

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return v0

    :cond_0
    const/4 v1, 0x1

    if-eqz p2, :cond_2

    if-eq p2, v1, :cond_1

    return v0

    :cond_1
    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2Text:Ljava/lang/String;

    goto :goto_0

    :cond_2
    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1Text:Ljava/lang/String;

    :goto_0
    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    invoke-direct {p0, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->addCaption(Lcom/meicam/sdk/NvsTimeline;)V

    return v1
.end method

.method public changeMusic(Ljava/lang/String;I)Z
    .locals 10

    invoke-static {}, Lcom/meicam/sdk/NvsStreamingContext;->getInstance()Lcom/meicam/sdk/NvsStreamingContext;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/meicam/sdk/NvsStreamingContext;->stop()V

    :cond_0
    const/4 v1, 0x0

    const/4 v2, 0x1

    if-nez p1, :cond_3

    const/4 p1, 0x0

    iput p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    iget p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedMusicType:I

    if-nez p1, :cond_1

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    iget p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    invoke-virtual {p1, p2, p2}, Lcom/meicam/sdk/NvsTimeline;->setThemeMusicVolumeGain(FF)V

    goto :goto_0

    :cond_1
    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    invoke-virtual {p1, v1}, Lcom/meicam/sdk/NvsTimeline;->getAudioTrackByIndex(I)Lcom/meicam/sdk/NvsAudioTrack;

    move-result-object p1

    if-eqz p1, :cond_2

    iget p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    invoke-virtual {p1, p2, p2}, Lcom/meicam/sdk/NvsAudioTrack;->setVolumeGain(FF)V

    :cond_2
    :goto_0
    return v2

    :cond_3
    iput p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedMusicType:I

    const/high16 v3, 0x3f800000    # 1.0f

    iput v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    const-string v3, "NvsThemeHelper"

    if-eq p2, v2, :cond_10

    const/4 v0, 0x2

    if-eq p2, v0, :cond_4

    return v1

    :cond_4
    move p2, v1

    :goto_1
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mMusicFileList:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v4, 0x0

    if-ge p2, v0, :cond_6

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mMusicFileList:Ljava/util/ArrayList;

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsMusicFileDesc;

    iget-object v5, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->id:Ljava/lang/String;

    invoke-virtual {v5, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_5

    iput p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedExtMusicIdx:I

    goto :goto_2

    :cond_5
    add-int/lit8 p2, p2, 0x1

    goto :goto_1

    :cond_6
    move-object v0, v4

    :goto_2
    if-nez v0, :cond_7

    move p2, v2

    goto :goto_3

    :cond_7
    move p2, v1

    :goto_3
    if-eqz p2, :cond_9

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, "/info.json"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5, v4}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object v5

    if-nez v5, :cond_8

    const-string p1, "read music info json error!"

    invoke-static {v3, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_8
    :try_start_0
    new-instance v6, Lorg/json/JSONObject;

    invoke-direct {v6, v5}, Lorg/json/JSONObject;-><init>(Ljava/lang/String;)V

    invoke-static {v6, p1}, Lcom/meicam/themehelper/NvsParseHelper;->GetMusicFileFromJsonObject(Lorg/json/JSONObject;Ljava/lang/String;)Lcom/meicam/themehelper/NvsMusicFileDesc;

    move-result-object v0

    iput-boolean v2, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->isDownloadFile:Z
    :try_end_0
    .catch Lorg/json/JSONException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_4

    :catch_0
    move-exception p1

    invoke-virtual {p1}, Lorg/json/JSONException;->printStackTrace()V

    :cond_9
    :goto_4
    if-nez v0, :cond_a

    const-string p1, "currentSelected music item is null!"

    invoke-static {v3, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_a
    iget-object p1, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-gtz p1, :cond_e

    iget-object p1, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->jsonFile:Ljava/lang/String;

    if-eqz p2, :cond_b

    move-object v1, v4

    goto :goto_5

    :cond_b
    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    :goto_5
    invoke-static {p1, v1}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_c

    iget-object v1, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc:Ljava/util/ArrayList;

    iget-object v3, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->transDesc:Lcom/meicam/themehelper/NvsTransDesc;

    invoke-static {p1, v1, v3}, Lcom/meicam/themehelper/NvsParseHelper;->readMusicPoint(Ljava/lang/String;Ljava/util/ArrayList;Lcom/meicam/themehelper/NvsTransDesc;)J

    move-result-wide v5

    iput-wide v5, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicLen:J

    :cond_c
    iget-object p1, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->jsonFile10s:Ljava/lang/String;

    if-eqz p2, :cond_d

    move-object v1, v4

    goto :goto_6

    :cond_d
    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    :goto_6
    invoke-static {p1, v1}, Lcom/meicam/themehelper/Utils;->readFile(Ljava/lang/String;Landroid/content/res/AssetManager;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_e

    iget-object v1, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->pointsDesc10s:Ljava/util/ArrayList;

    invoke-static {p1, v1, v4}, Lcom/meicam/themehelper/NvsParseHelper;->readMusicPoint(Ljava/lang/String;Ljava/util/ArrayList;Lcom/meicam/themehelper/NvsTransDesc;)J

    move-result-wide v5

    iput-wide v5, v0, Lcom/meicam/themehelper/NvsMusicFileDesc;->musicLen10s:J

    :cond_e
    if-eqz p2, :cond_f

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mMusicFileList:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mMusicFileList:Ljava/util/ArrayList;

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result p1

    sub-int/2addr p1, v2

    iput p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedExtMusicIdx:I

    :cond_f
    iput-object v4, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusic:Ljava/lang/String;

    const-wide/16 p1, -0x1

    iput-wide p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicStart:J

    iput-wide p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedLocalMusicEnd:J

    invoke-direct {p0, p1, p2, v2}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    return v2

    :cond_10
    invoke-virtual {v0, p1}, Lcom/meicam/sdk/NvsStreamingContext;->getAVFileInfo(Ljava/lang/String;)Lcom/meicam/sdk/NvsAVFileInfo;

    move-result-object p2

    if-nez p2, :cond_11

    const-string p1, "loacl music format is not suported!"

    invoke-static {v3, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_11
    const-wide/16 v6, 0x0

    invoke-virtual {p2, v1}, Lcom/meicam/sdk/NvsAVFileInfo;->getAudioStreamDuration(I)J

    move-result-wide v8

    move-object v4, p0

    move-object v5, p1

    invoke-direct/range {v4 .. v9}, Lcom/meicam/themehelper/NvsThemeHelper;->changeLocalMusic(Ljava/lang/String;JJ)V

    return v2
.end method

.method public changeMusicVolumeGain(F)Z
    .locals 4

    iget v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_selectedMusicType:I

    const/4 v1, 0x1

    if-eqz v0, :cond_1

    const/4 v2, 0x0

    if-eq v0, v1, :cond_0

    const/4 v3, 0x2

    if-eq v0, v3, :cond_0

    return v2

    :cond_0
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    invoke-virtual {v0, v2}, Lcom/meicam/sdk/NvsTimeline;->getAudioTrackByIndex(I)Lcom/meicam/sdk/NvsAudioTrack;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-virtual {v0, p1, p1}, Lcom/meicam/sdk/NvsAudioTrack;->setVolumeGain(FF)V

    goto :goto_0

    :cond_1
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    invoke-virtual {v0, p1, p1}, Lcom/meicam/sdk/NvsTimeline;->setThemeMusicVolumeGain(FF)V

    :cond_2
    :goto_0
    iput p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_musicVolumeGain:F

    return v1
.end method

.method public changeTimelineDuration(Z)Z
    .locals 5

    const/4 v0, 0x1

    if-eqz p1, :cond_0

    const-wide/16 v1, 0xa

    iget-wide v3, p0, Lcom/meicam/themehelper/NvsThemeHelper;->timeBase:J

    mul-long/2addr v3, v1

    invoke-direct {p0, v3, v4, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    goto :goto_0

    :cond_0
    const-wide/16 v1, -0x2

    invoke-direct {p0, v1, v2, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    :goto_0
    return v0
.end method

.method public deleteClip(I)Z
    .locals 4

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    const/4 v1, 0x0

    const-string v2, "NvsThemeHelper"

    if-nez v0, :cond_0

    const-string p1, "invalid file!"

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_0
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-direct {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->getMinImgCount()I

    move-result v3

    if-gt v0, v3, :cond_1

    const-string p1, "less than min clip count!"

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_1
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-gt v0, p1, :cond_2

    const-string p1, "invalid clip index!"

    invoke-static {v2, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_2
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    invoke-virtual {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->randomFx()V

    const-wide/16 v0, -0x1

    const/4 p1, 0x1

    invoke-direct {p0, v0, v1, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    return p1
.end method

.method public getCaptionText(I)Ljava/lang/String;
    .locals 2

    const-string v0, ""

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption1Text:Ljava/lang/String;

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2:Lcom/meicam/sdk/NvsTimelineAnimatedSticker;

    if-eqz p1, :cond_1

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_caption2Text:Ljava/lang/String;

    goto :goto_0

    :cond_1
    move-object p1, v0

    :goto_0
    const-string v1, "\n"

    invoke-virtual {p1, v1, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public getCurrentThemeName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_themeAssetID:Ljava/lang/String;

    return-object v0
.end method

.method public getThumbnailImages()Ljava/util/ArrayList;
    .locals 15
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;",
            ">;"
        }
    .end annotation

    move-object v0, p0

    iget-object v1, v0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    return-object v2

    :cond_0
    const/4 v3, 0x0

    invoke-virtual {v1, v3}, Lcom/meicam/sdk/NvsTimeline;->getVideoTrackByIndex(I)Lcom/meicam/sdk/NvsVideoTrack;

    move-result-object v1

    if-nez v1, :cond_1

    return-object v2

    :cond_1
    new-instance v4, Ljava/util/ArrayList;

    invoke-direct {v4}, Ljava/util/ArrayList;-><init>()V

    :goto_0
    invoke-virtual {v1}, Lcom/meicam/sdk/NvsVideoTrack;->getClipCount()I

    move-result v5

    if-ge v3, v5, :cond_6

    invoke-virtual {v1, v3}, Lcom/meicam/sdk/NvsVideoTrack;->getClipByIndex(I)Lcom/meicam/sdk/NvsVideoClip;

    move-result-object v5

    if-nez v5, :cond_2

    move v10, v3

    goto/16 :goto_2

    :cond_2
    new-instance v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;

    invoke-direct {v6}, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;-><init>()V

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getFilePath()Ljava/lang/String;

    move-result-object v7

    iput-object v7, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->mediaFilePath:Ljava/lang/String;

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getTrimIn()J

    move-result-wide v7

    iput-wide v7, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimIn:J

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getTrimOut()J

    move-result-wide v7

    iput-wide v7, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimOut:J

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getInPoint()J

    move-result-wide v7

    iput-wide v7, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v7

    iput-wide v7, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    const/4 v7, 0x1

    iput-boolean v7, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->stillImageHint:Z

    const-string v8, "hasFxTrans"

    invoke-virtual {v5, v8}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    if-eqz v9, :cond_3

    invoke-virtual {v5, v8}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v8

    goto :goto_1

    :cond_3
    move-object v8, v2

    :goto_1
    if-eqz v8, :cond_5

    const-string v9, "true"

    invoke-virtual {v8, v9}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_5

    const-string v8, "fxFilePath"

    invoke-virtual {v5, v8}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v8

    const-string v9, "fxInpoint"

    invoke-virtual {v5, v9}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-static {v9}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/Integer;->longValue()J

    move-result-wide v9

    const-wide/16 v11, 0x3e8

    mul-long/2addr v9, v11

    iget-wide v13, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimIn:J

    add-long/2addr v13, v9

    iput-wide v13, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimOut:J

    iget-wide v13, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    add-long/2addr v13, v9

    iput-wide v13, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    new-instance v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;

    invoke-direct {v9}, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;-><init>()V

    iput-object v8, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->mediaFilePath:Ljava/lang/String;

    iget-wide v13, v6, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    iput-wide v13, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v13

    iput-wide v13, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    const-wide/16 v13, 0x0

    iput-wide v13, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimIn:J

    move v10, v3

    iget-wide v2, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    iget-wide v13, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    sub-long/2addr v2, v13

    iput-wide v2, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimOut:J

    iput-boolean v7, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->stillImageHint:Z

    const-string v2, "fxFileV3Path"

    invoke-virtual {v5, v2}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    if-eqz v3, :cond_4

    invoke-virtual {v5, v2}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    const-string v3, "fxV3Inpoint"

    invoke-virtual {v5, v3}, Lcom/meicam/sdk/NvsVideoClip;->getAttachment(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Integer;->longValue()J

    move-result-wide v13

    mul-long/2addr v13, v11

    iget-wide v11, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimIn:J

    add-long/2addr v11, v13

    iput-wide v11, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimOut:J

    iget-wide v11, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    add-long/2addr v11, v13

    iput-wide v11, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    invoke-virtual {v4, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    new-instance v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;

    invoke-direct {v3}, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;-><init>()V

    iput-object v2, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->mediaFilePath:Ljava/lang/String;

    iget-wide v11, v9, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    iput-wide v11, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    invoke-virtual {v5}, Lcom/meicam/sdk/NvsVideoClip;->getOutPoint()J

    move-result-wide v5

    iput-wide v5, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    const-wide/16 v5, 0x0

    iput-wide v5, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimIn:J

    iget-wide v5, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    iget-wide v11, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    sub-long/2addr v5, v11

    iput-wide v5, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->trimOut:J

    iput-boolean v7, v3, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->stillImageHint:Z

    invoke-virtual {v4, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_2

    :cond_4
    invoke-virtual {v4, v9}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_2

    :cond_5
    move v10, v3

    invoke-virtual {v4, v6}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :goto_2
    add-int/lit8 v3, v10, 0x1

    const/4 v2, 0x0

    goto/16 :goto_0

    :cond_6
    return-object v4
.end method

.method public getTimelinePosition(I)J
    .locals 4

    const-wide/16 v0, 0x0

    if-gez p1, :cond_0

    return-wide v0

    :cond_0
    invoke-virtual {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->getThumbnailImages()Ljava/util/ArrayList;

    move-result-object v2

    if-nez v2, :cond_1

    return-wide v0

    :cond_1
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v3

    if-lt p1, v3, :cond_2

    return-wide v0

    :cond_2
    invoke-virtual {v2, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;

    if-nez p1, :cond_3

    return-wide v0

    :cond_3
    iget-wide v0, p1, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->inPoint:J

    iget-wide v2, p1, Lcom/meicam/sdk/NvsMultiThumbnailSequenceView$ThumbnailSequenceDesc;->outPoint:J

    add-long/2addr v0, v2

    const-wide/16 v2, 0x2

    div-long/2addr v0, v2

    return-wide v0
.end method

.method public initHelper(Landroid/content/Context;Lcom/meicam/sdk/NvsTimeline;Ljava/util/ArrayList;)Z
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroid/content/Context;",
            "Lcom/meicam/sdk/NvsTimeline;",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;)Z"
        }
    .end annotation

    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_context:Landroid/content/Context;

    const-string v0, "NvsThemeHelper"

    const/4 v1, 0x0

    if-nez p1, :cond_0

    const-string p1, "Context can not be NULL"

    invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_0
    if-nez p2, :cond_1

    const-string p1, "timeline can not be NULL"

    invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1

    :cond_1
    if-eqz p3, :cond_4

    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result p1

    const/4 v2, 0x1

    if-ge p1, v2, :cond_2

    goto :goto_1

    :cond_2
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    :goto_0
    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result p1

    if-ge v1, p1, :cond_3

    invoke-virtual {p3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/meicam/themehelper/NvsImageFileDesc;

    invoke-virtual {p1}, Lcom/meicam/themehelper/NvsImageFileDesc;->copy()Lcom/meicam/themehelper/NvsImageFileDesc;

    move-result-object p1

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_3
    iput-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    invoke-virtual {p1}, Lcom/meicam/sdk/NvsTimeline;->getVideoRes()Lcom/meicam/sdk/NvsVideoResolution;

    move-result-object p1

    iget p2, p1, Lcom/meicam/sdk/NvsVideoResolution;->imageWidth:I

    int-to-float p2, p2

    const/high16 p3, 0x3f800000    # 1.0f

    mul-float/2addr p2, p3

    iget p1, p1, Lcom/meicam/sdk/NvsVideoResolution;->imageHeight:I

    int-to-float p1, p1

    div-float/2addr p2, p1

    sput p2, Lcom/meicam/themehelper/NvsThemeHelper;->m_timelineRatio:F

    return v2

    :cond_4
    :goto_1
    const-string p1, "file list is empty"

    invoke-static {v0, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v1
.end method

.method public insertClip(Ljava/util/ArrayList;)Z
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;)Z"
        }
    .end annotation

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    const-string v1, "NvsThemeHelper"

    const/4 v2, 0x0

    if-eqz v0, :cond_6

    if-eqz p1, :cond_6

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_4

    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    move v3, v2

    :goto_0
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v4

    const/4 v5, 0x1

    if-ge v3, v4, :cond_5

    invoke-virtual {p1, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-boolean v6, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->alternative:Z

    if-eqz v6, :cond_3

    move v6, v2

    :goto_1
    iget-object v7, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v7}, Ljava/util/ArrayList;->size()I

    move-result v7

    if-ge v6, v7, :cond_2

    iget-object v7, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v7, v6}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/meicam/themehelper/NvsImageFileDesc;

    iget-object v8, v7, Lcom/meicam/themehelper/NvsImageFileDesc;->filePath:Ljava/lang/String;

    iget-object v9, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->filePath:Ljava/lang/String;

    invoke-virtual {v8, v9}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v8

    if-eqz v8, :cond_1

    const-string v6, "exist file!"

    invoke-static {v1, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    iput-boolean v5, v7, Lcom/meicam/themehelper/NvsImageFileDesc;->show:Z

    move v6, v5

    goto :goto_2

    :cond_1
    add-int/lit8 v6, v6, 0x1

    goto :goto_1

    :cond_2
    move v6, v2

    :goto_2
    if-nez v6, :cond_4

    invoke-virtual {v4}, Lcom/meicam/themehelper/NvsImageFileDesc;->copy()Lcom/meicam/themehelper/NvsImageFileDesc;

    move-result-object v4

    iput-boolean v5, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->show:Z

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_3

    :cond_3
    invoke-virtual {v4}, Lcom/meicam/themehelper/NvsImageFileDesc;->copy()Lcom/meicam/themehelper/NvsImageFileDesc;

    move-result-object v4

    iput-boolean v5, v4, Lcom/meicam/themehelper/NvsImageFileDesc;->show:Z

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_4
    :goto_3
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_5
    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    invoke-virtual {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->randomFx()V

    const-wide/16 v0, -0x1

    invoke-direct {p0, v0, v1, v5}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    return v5

    :cond_6
    :goto_4
    const-string p1, "invalid clip!"

    invoke-static {v1, p1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    return v2
.end method

.method public moveClip(II)Z
    .locals 2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    const/4 v1, 0x0

    if-ge p1, v0, :cond_2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p2, v0, :cond_2

    if-ltz p1, :cond_2

    if-gez p2, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    if-nez v0, :cond_1

    goto :goto_0

    :cond_1
    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/meicam/themehelper/NvsImageFileDesc;

    invoke-virtual {v0}, Lcom/meicam/themehelper/NvsImageFileDesc;->copy()Lcom/meicam/themehelper/NvsImageFileDesc;

    move-result-object v0

    iget-object v1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v1, p1}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {p1, p2, v0}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    invoke-virtual {p0}, Lcom/meicam/themehelper/NvsThemeHelper;->randomFx()V

    const-wide/16 p1, -0x1

    const/4 v0, 0x1

    invoke-direct {p0, p1, p2, v0}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    return v0

    :cond_2
    :goto_0
    return v1
.end method

.method public randomFx()V
    .locals 2

    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    iput-wide v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->seed:J

    return-void
.end method

.method public resetClip(Ljava/util/ArrayList;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/meicam/themehelper/NvsImageFileDesc;",
            ">;)Z"
        }
    .end annotation

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    if-eqz v0, :cond_1

    if-eqz p1, :cond_1

    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_inputIamgeInfo:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->clear()V

    invoke-virtual {p0, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->insertClip(Ljava/util/ArrayList;)Z

    move-result p1

    return p1

    :cond_1
    :goto_0
    const-string p1, "NvsThemeHelper"

    const-string v0, "invalid clip!"

    invoke-static {p1, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    const/4 p1, 0x0

    return p1
.end method

.method public setDefaultRhythmPath(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 1

    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythmPath:Ljava/lang/String;

    iput-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythm10sPath:Ljava/lang/String;

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythmPath:Ljava/lang/String;

    if-eqz p1, :cond_1

    iget-object p2, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythm10sPath:Ljava/lang/String;

    if-nez p2, :cond_0

    goto :goto_0

    :cond_0
    const-string p2, ""

    const-string v0, "assets:/"

    invoke-virtual {p1, v0, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythmPath:Ljava/lang/String;

    iget-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythm10sPath:Ljava/lang/String;

    invoke-virtual {p1, v0, p2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    iput-object p1, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_defaultRhythm10sPath:Ljava/lang/String;

    const/4 p1, 0x1

    return p1

    :cond_1
    :goto_0
    const/4 p1, 0x0

    return p1
.end method

.method public setThemeEnabled(Z)V
    .locals 2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_timeline:Lcom/meicam/sdk/NvsTimeline;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->mThemeAssetMap:Ljava/util/Map;

    if-eqz v0, :cond_2

    iget-object v0, p0, Lcom/meicam/themehelper/NvsThemeHelper;->m_themeAssetID:Ljava/lang/String;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const-wide/16 v0, -0x1

    if-eqz p1, :cond_1

    const/4 p1, 0x1

    invoke-direct {p0, v0, v1, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    invoke-direct {p0, v0, v1, p1}, Lcom/meicam/themehelper/NvsThemeHelper;->reBuildTimeLineExt(JZ)V

    :cond_2
    :goto_0
    return-void
.end method
