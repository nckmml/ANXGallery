.class Lcom/miui/gallery/discovery/GalleryJobService$JobTask;
.super Lmiui/util/async/Task;
.source "GalleryJobService.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/discovery/GalleryJobService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "JobTask"
.end annotation


# instance fields
.field private mJob:Lcom/miui/gallery/discovery/AbstractJob;

.field private mJobParams:Landroid/app/job/JobParameters;

.field final synthetic this$0:Lcom/miui/gallery/discovery/GalleryJobService;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/discovery/GalleryJobService;Landroid/app/job/JobParameters;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->this$0:Lcom/miui/gallery/discovery/GalleryJobService;

    invoke-direct {p0}, Lmiui/util/async/Task;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    return-void
.end method


# virtual methods
.method public doLoad()Ljava/lang/Object;
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    invoke-static {}, Lcom/miui/gallery/discovery/GalleryJobService;->access$000()Ljava/util/Map;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {v1}, Landroid/app/job/JobParameters;->getJobId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Class;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/Class;->newInstance()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/miui/gallery/discovery/AbstractJob;

    iput-object v1, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJob:Lcom/miui/gallery/discovery/AbstractJob;

    iget-object v1, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJob:Lcom/miui/gallery/discovery/AbstractJob;

    iget-object v2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {v2}, Landroid/app/job/JobParameters;->getJobId()I

    move-result v2

    invoke-virtual {v1, v2}, Lcom/miui/gallery/discovery/AbstractJob;->setJobId(I)V

    iget-object v1, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {v1}, Landroid/app/job/JobParameters;->getJobId()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "GalleryJobService"

    const-string v3, "Exec job %d"

    invoke-static {v2, v3, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object v1

    const-string v2, "JobServiceStarted"

    invoke-static {v2, v0, v1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    iget-object v0, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJob:Lcom/miui/gallery/discovery/AbstractJob;

    invoke-virtual {v0}, Lcom/miui/gallery/discovery/AbstractJob;->execJob()Ljava/lang/Object;

    move-result-object v0

    return-object v0

    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public onResult(Lmiui/util/async/TaskManager;Ljava/lang/Object;)V
    .locals 5

    invoke-static {}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->generatorCommonParams()Ljava/util/Map;

    move-result-object p1

    const-string v0, "jobId: "

    const-string v1, "GalleryJobService"

    const/4 v2, 0x0

    if-eqz p2, :cond_3

    instance-of v3, p2, Ljava/lang/Boolean;

    if-eqz v3, :cond_3

    check-cast p2, Ljava/lang/Boolean;

    invoke-virtual {p2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p2

    if-nez p2, :cond_3

    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJob:Lcom/miui/gallery/discovery/AbstractJob;

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Lcom/miui/gallery/discovery/AbstractJob;->needsReschedule()Z

    move-result p2

    if-eqz p2, :cond_0

    const/4 v2, 0x1

    :cond_0
    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->this$0:Lcom/miui/gallery/discovery/GalleryJobService;

    iget-object v3, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {p2, v3, v2}, Lcom/miui/gallery/discovery/GalleryJobService;->jobFinished(Landroid/app/job/JobParameters;Z)V

    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {p2}, Landroid/app/job/JobParameters;->getJobId()I

    move-result p2

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    invoke-static {v2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v3

    const-string v4, "Job %d failed, rescheduled: %b."

    invoke-static {v1, v4, p2, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJob:Lcom/miui/gallery/discovery/AbstractJob;

    if-eqz p2, :cond_1

    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p2

    goto :goto_0

    :cond_1
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {v0}, Landroid/app/job/JobParameters;->getJobId()I

    move-result v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    :goto_0
    const-string v0, "JobServiceFailed"

    invoke-static {v0, p2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    if-eqz v2, :cond_2

    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJob:Lcom/miui/gallery/discovery/AbstractJob;

    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p2

    const-string v0, "JobServiceRescheduled"

    invoke-static {v0, p2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordErrorEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    :cond_2
    return-void

    :cond_3
    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->this$0:Lcom/miui/gallery/discovery/GalleryJobService;

    iget-object v3, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {p2, v3, v2}, Lcom/miui/gallery/discovery/GalleryJobService;->jobFinished(Landroid/app/job/JobParameters;Z)V

    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {p2}, Landroid/app/job/JobParameters;->getJobId()I

    move-result p2

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    const-string v2, "Job %d done"

    invoke-static {v1, v2, p2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    iget-object p2, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJob:Lcom/miui/gallery/discovery/AbstractJob;

    if-eqz p2, :cond_4

    invoke-virtual {p2}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p2

    goto :goto_1

    :cond_4
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lcom/miui/gallery/discovery/GalleryJobService$JobTask;->mJobParams:Landroid/app/job/JobParameters;

    invoke-virtual {v0}, Landroid/app/job/JobParameters;->getJobId()I

    move-result v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    :goto_1
    const-string v0, "JobServiceDone"

    invoke-static {v0, p2, p1}, Lcom/miui/gallery/util/GallerySamplingStatHelper;->recordCountEvent(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V

    return-void
.end method
