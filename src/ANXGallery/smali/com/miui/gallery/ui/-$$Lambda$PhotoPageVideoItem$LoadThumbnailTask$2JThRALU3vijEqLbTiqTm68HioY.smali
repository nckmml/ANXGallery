.class public final synthetic Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# instance fields
.field private final synthetic f$0:Landroid/graphics/Bitmap;

.field private final synthetic f$1:I

.field private final synthetic f$2:I


# direct methods
.method public synthetic constructor <init>(Landroid/graphics/Bitmap;II)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;->f$0:Landroid/graphics/Bitmap;

    iput p2, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;->f$1:I

    iput p3, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;->f$2:I

    return-void
.end method


# virtual methods
.method public final run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;->f$0:Landroid/graphics/Bitmap;

    iget v1, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;->f$1:I

    iget v2, p0, Lcom/miui/gallery/ui/-$$Lambda$PhotoPageVideoItem$LoadThumbnailTask$2JThRALU3vijEqLbTiqTm68HioY;->f$2:I

    invoke-static {v0, v1, v2, p1}, Lcom/miui/gallery/ui/PhotoPageVideoItem$LoadThumbnailTask;->lambda$load$21(Landroid/graphics/Bitmap;IILcom/miui/gallery/threadpool/ThreadPool$JobContext;)Landroid/graphics/Bitmap;

    move-result-object p1

    return-object p1
.end method
