.class public Lcom/miui/gallery/ui/photodetail/PhotoDetailUtils;
.super Ljava/lang/Object;
.source "PhotoDetailUtils.java"


# direct methods
.method public static isMimeTypeSupportEditDateTime(Ljava/lang/String;)Z
    .locals 2

    invoke-static {p0}, Lcom/miui/gallery/util/BaseFileMimeUtil;->isGifFromMimeType(Ljava/lang/String;)Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_0

    return v1

    :cond_0
    invoke-static {p0}, Lcom/miui/gallery/util/BaseFileMimeUtil;->isJpegImageFromMimeType(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    return v1

    :cond_1
    invoke-static {p0}, Lcom/miui/gallery/util/BaseFileMimeUtil;->isPngImageFromMimeType(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_2

    return v1

    :cond_2
    const/4 p0, 0x0

    return p0
.end method
