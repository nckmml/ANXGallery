.class public Lcom/miui/gallery/video/editor/manager/SmartVideoJudgeManager;
.super Ljava/lang/Object;
.source "SmartVideoJudgeManager.java"


# static fields
.field private static sLoaded:Z

.field private static final sWhiteList:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 25

    const-string v0, "umi"

    const-string v1, "cmi"

    const-string v2, "dipper"

    const-string v3, "ursa"

    const-string v4, "equuleus"

    const-string v5, "perseus"

    const-string v6, "sirius"

    const-string v7, "lavender"

    const-string v8, "cepheus"

    const-string v9, "violet"

    const-string v10, "onc"

    const-string v11, "grus"

    const-string v12, "pyxis"

    const-string v13, "raphael"

    const-string v14, "davinci"

    const-string v15, "davinciin"

    const-string v16, "raphaelin"

    const-string v17, "vela"

    const-string v18, "crux"

    const-string v19, "tucana"

    const-string v20, "ginkgo"

    const-string v21, "willow"

    const-string v22, "draco"

    const-string v23, "phoenix"

    const-string v24, "phoenixin"

    filled-new-array/range {v0 .. v24}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/video/editor/manager/SmartVideoJudgeManager;->sWhiteList:[Ljava/lang/String;

    const/4 v0, 0x0

    sput-boolean v0, Lcom/miui/gallery/video/editor/manager/SmartVideoJudgeManager;->sLoaded:Z

    sget-object v1, Lcom/miui/gallery/video/editor/manager/SmartVideoJudgeManager;->sWhiteList:[Ljava/lang/String;

    array-length v2, v1

    :goto_0
    if-ge v0, v2, :cond_1

    aget-object v3, v1, v0

    sget-object v4, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v4, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    const/4 v3, 0x1

    sput-boolean v3, Lcom/miui/gallery/video/editor/manager/SmartVideoJudgeManager;->sLoaded:Z

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public static isAvailable()Z
    .locals 1

    sget-boolean v0, Lcom/miui/gallery/video/editor/manager/SmartVideoJudgeManager;->sLoaded:Z

    return v0
.end method
