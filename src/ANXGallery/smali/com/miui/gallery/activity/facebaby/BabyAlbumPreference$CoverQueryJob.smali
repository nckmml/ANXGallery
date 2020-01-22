.class Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;
.super Ljava/lang/Object;
.source "BabyLockWallpaperSettingActivity.java"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "CoverQueryJob"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lcom/miui/gallery/threadpool/ThreadPool$Job<",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# instance fields
.field private mAlbumLocalId:J

.field private mIsOtherShare:Z


# direct methods
.method constructor <init>(JZ)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-wide p1, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;->mAlbumLocalId:J

    iput-boolean p3, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;->mIsOtherShare:Z

    return-void
.end method


# virtual methods
.method public bridge synthetic run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;->run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/String;
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;->mAlbumLocalId:J

    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p1

    iget-boolean v0, p0, Lcom/miui/gallery/activity/facebaby/BabyAlbumPreference$CoverQueryJob;->mIsOtherShare:Z

    invoke-static {p1, v0}, Lcom/miui/gallery/provider/FaceManager;->queryBabyAlbumCover(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method
