.class public Lcom/miui/gallery/net/library/LibraryStrategyUtils;
.super Ljava/lang/Object;
.source "LibraryStrategyUtils.java"


# static fields
.field private static final sMtkList:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    const-string v0, "begonia"

    const-string v1, "begoniain"

    filled-new-array {v0, v1}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/net/library/LibraryStrategyUtils;->sMtkList:[Ljava/lang/String;

    return-void
.end method

.method public static getKeyForLibraryId(J)Ljava/lang/String;
    .locals 2

    const-wide/16 v0, 0x3f4    # 5.0E-321

    cmp-long p0, p0, v0

    if-nez p0, :cond_0

    sget-object p0, Lcom/miui/gallery/net/library/LibraryStrategyUtils;->sMtkList:[Ljava/lang/String;

    invoke-static {p0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object p0

    sget-object p1, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-interface {p0, p1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const-string p0, "mtk"

    return-object p0

    :cond_0
    sget-object p0, Lcom/miui/gallery/assistant/library/LibraryConstants;->CURRENT_ABI:Ljava/lang/String;

    return-object p0
.end method
