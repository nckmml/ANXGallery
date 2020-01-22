.class public Lcom/miui/gallery/util/thread/ThreadConfig;
.super Ljava/lang/Object;
.source "ThreadConfig.java"


# direct methods
.method public static getSuggestThreadCoreSize()I
    .locals 1

    invoke-static {}, Lmiui/os/Environment;->getCpuCount()I

    move-result v0

    add-int/lit8 v0, v0, 0x1

    return v0
.end method

.method public static getSuggestThreadMaxSize()I
    .locals 1

    invoke-static {}, Lmiui/os/Environment;->getCpuCount()I

    move-result v0

    mul-int/lit8 v0, v0, 0x2

    add-int/lit8 v0, v0, 0x1

    return v0
.end method
