.class Lcom/miui/gallery/search/feedback/SearchFeedbackHelper$FeedbackTaskResponseData;
.super Ljava/lang/Object;
.source "SearchFeedbackHelper.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/search/feedback/SearchFeedbackHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "FeedbackTaskResponseData"
.end annotation


# instance fields
.field public awardInfo:Ljava/lang/String;
    .annotation runtime Lcom/google/gson/annotations/SerializedName;
        value = "awardInfo"
    .end annotation
.end field

.field public finishImageNum:I
    .annotation runtime Lcom/google/gson/annotations/SerializedName;
        value = "finishImageNum"
    .end annotation
.end field

.field public needHandleImageNum:I
    .annotation runtime Lcom/google/gson/annotations/SerializedName;
        value = "needHandleImageNum"
    .end annotation
.end field


# direct methods
.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 v0, 0x0

    iput v0, p0, Lcom/miui/gallery/search/feedback/SearchFeedbackHelper$FeedbackTaskResponseData;->needHandleImageNum:I

    iput v0, p0, Lcom/miui/gallery/search/feedback/SearchFeedbackHelper$FeedbackTaskResponseData;->finishImageNum:I

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/miui/gallery/search/feedback/SearchFeedbackHelper$FeedbackTaskResponseData;->awardInfo:Ljava/lang/String;

    return-void
.end method