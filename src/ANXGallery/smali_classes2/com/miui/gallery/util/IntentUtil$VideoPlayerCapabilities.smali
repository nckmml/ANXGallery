.class Lcom/miui/gallery/util/IntentUtil$VideoPlayerCapabilities;
.super Lcom/miui/gallery/util/LazyValue;
.source "IntentUtil.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/util/IntentUtil;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "VideoPlayerCapabilities"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/gallery/util/LazyValue<",
        "Ljava/lang/Void;",
        "[",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/util/LazyValue;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/util/IntentUtil$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/util/IntentUtil$VideoPlayerCapabilities;-><init>()V

    return-void
.end method


# virtual methods
.method protected bridge synthetic onInit(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Ljava/lang/Void;

    invoke-virtual {p0, p1}, Lcom/miui/gallery/util/IntentUtil$VideoPlayerCapabilities;->onInit(Ljava/lang/Void;)[Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method protected onInit(Ljava/lang/Void;)[Ljava/lang/String;
    .locals 1

    invoke-static {}, Lcom/miui/gallery/util/IntentUtil;->getMiuiVideoPackageName()Ljava/lang/String;

    move-result-object p1

    const-string v0, "com.miui.video.EDIT_CAPABILITIES"

    invoke-static {p1, v0}, Lcom/miui/gallery/util/MiscUtil;->getApplicationMetaData(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    const-string v0, "\\|"

    invoke-virtual {p1, v0}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method