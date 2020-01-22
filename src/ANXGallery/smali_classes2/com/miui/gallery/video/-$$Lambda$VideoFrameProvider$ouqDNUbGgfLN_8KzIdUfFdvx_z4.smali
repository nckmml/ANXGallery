.class public final synthetic Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/video/VideoFrameProvider;

.field private final synthetic f$1:Ljava/lang/String;

.field private final synthetic f$2:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/video/VideoFrameProvider;Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;->f$0:Lcom/miui/gallery/video/VideoFrameProvider;

    iput-object p2, p0, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;->f$1:Ljava/lang/String;

    iput-object p3, p0, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;->f$2:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    return-void
.end method


# virtual methods
.method public final run()V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;->f$0:Lcom/miui/gallery/video/VideoFrameProvider;

    iget-object v1, p0, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;->f$1:Ljava/lang/String;

    iget-object v2, p0, Lcom/miui/gallery/video/-$$Lambda$VideoFrameProvider$ouqDNUbGgfLN_8KzIdUfFdvx_z4;->f$2:Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;

    invoke-virtual {v0, v1, v2}, Lcom/miui/gallery/video/VideoFrameProvider;->lambda$notifyThumbListResponse$13$VideoFrameProvider(Ljava/lang/String;Lcom/miui/gallery/video/VideoFrameProvider$ThumbListInfo;)V

    return-void
.end method
