.class public interface abstract Lcom/miui/gallery/cleaner/CleanerContract$Projection;
.super Ljava/lang/Object;
.source "CleanerContract.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/cleaner/CleanerContract;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x609
    name = "Projection"
.end annotation


# static fields
.field public static final BASE_SCAN_PROJECTION:[Ljava/lang/String;

.field public static final NORMAL_SCAN_PROJECTION:[Ljava/lang/String;

.field public static final SIMILAR_SCAN_PROJECTION:[Ljava/lang/String;

.field public static final SLIM_SCAN_PROJECTION:[Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    const-string v0, "_id"

    const-string v1, "size"

    const-string v2, "localFile"

    filled-new-array {v0, v1, v2}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->BASE_SCAN_PROJECTION:[Ljava/lang/String;

    sget-object v0, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->BASE_SCAN_PROJECTION:[Ljava/lang/String;

    const-string v1, "thumbnailFile"

    const-string v2, "microthumbfile"

    const-string v3, "sha1"

    filled-new-array {v1, v2, v3}, [Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ArrayUtils;->concat([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    sput-object v0, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->NORMAL_SCAN_PROJECTION:[Ljava/lang/String;

    sget-object v0, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->BASE_SCAN_PROJECTION:[Ljava/lang/String;

    const-string v1, "exifImageWidth"

    const-string v2, "exifImageLength"

    filled-new-array {v1, v2}, [Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ArrayUtils;->concat([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    sput-object v0, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->SLIM_SCAN_PROJECTION:[Ljava/lang/String;

    sget-object v0, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->NORMAL_SCAN_PROJECTION:[Ljava/lang/String;

    const-string v1, "alias_create_time"

    filled-new-array {v1}, [Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Lcom/miui/gallery/util/ArrayUtils;->concat([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    sput-object v0, Lcom/miui/gallery/cleaner/CleanerContract$Projection;->SIMILAR_SCAN_PROJECTION:[Ljava/lang/String;

    return-void
.end method
