.class public final synthetic Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$i4x-erwI1DqQYTbi4bY0u00Wcnc;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/ui/AlbumPageHeaderController;

.field private final synthetic f$1:I


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/ui/AlbumPageHeaderController;I)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$i4x-erwI1DqQYTbi4bY0u00Wcnc;->f$0:Lcom/miui/gallery/ui/AlbumPageHeaderController;

    iput p2, p0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$i4x-erwI1DqQYTbi4bY0u00Wcnc;->f$1:I

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$i4x-erwI1DqQYTbi4bY0u00Wcnc;->f$0:Lcom/miui/gallery/ui/AlbumPageHeaderController;

    iget v1, p0, Lcom/miui/gallery/ui/-$$Lambda$AlbumPageHeaderController$i4x-erwI1DqQYTbi4bY0u00Wcnc;->f$1:I

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/ui/AlbumPageHeaderController;->lambda$subscribeSubject$42$AlbumPageHeaderController(ILjava/lang/Boolean;)V

    return-void
.end method
