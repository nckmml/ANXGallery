.class public final synthetic Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyResourceDownloadHelper$aJOz7kMoiKMRxSqnG6cyufqwjfM;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Function;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

.field private final synthetic f$1:Ljava/lang/String;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyResourceDownloadHelper$aJOz7kMoiKMRxSqnG6cyufqwjfM;->f$0:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyResourceDownloadHelper$aJOz7kMoiKMRxSqnG6cyufqwjfM;->f$1:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public final apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyResourceDownloadHelper$aJOz7kMoiKMRxSqnG6cyufqwjfM;->f$0:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyResourceDownloadHelper$aJOz7kMoiKMRxSqnG6cyufqwjfM;->f$1:Ljava/lang/String;

    check-cast p1, Ljava/lang/Integer;

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyResourceDownloadHelper;->lambda$downloadResource$120$SkyResourceDownloadHelper(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;

    move-result-object p1

    return-object p1
.end method
