.class public final synthetic Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$VideoThumbnailManager$hIM06F5t0HAkEAXSXQy_S1UzmuI;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTaskCallback;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$VideoThumbnailManager$hIM06F5t0HAkEAXSXQy_S1UzmuI;->f$0:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    return-void
.end method


# virtual methods
.method public final onThumbnailLoadFinish(Landroid/graphics/Bitmap;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$VideoThumbnailManager$hIM06F5t0HAkEAXSXQy_S1UzmuI;->f$0:Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$VideoThumbnailManager;->lambda$startLoadThumbnail$20$PhotoPageVideoItem$VideoThumbnailManager(Landroid/graphics/Bitmap;)V

    return-void
.end method
