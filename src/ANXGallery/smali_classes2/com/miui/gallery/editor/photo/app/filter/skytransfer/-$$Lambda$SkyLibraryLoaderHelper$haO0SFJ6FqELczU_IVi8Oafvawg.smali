.class public final synthetic Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyLibraryLoaderHelper$haO0SFJ6FqELczU_IVi8Oafvawg;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

.field private final synthetic f$1:Z


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;Z)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyLibraryLoaderHelper$haO0SFJ6FqELczU_IVi8Oafvawg;->f$0:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

    iput-boolean p2, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyLibraryLoaderHelper$haO0SFJ6FqELczU_IVi8Oafvawg;->f$1:Z

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyLibraryLoaderHelper$haO0SFJ6FqELczU_IVi8Oafvawg;->f$0:Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;

    iget-boolean v1, p0, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/-$$Lambda$SkyLibraryLoaderHelper$haO0SFJ6FqELczU_IVi8Oafvawg;->f$1:Z

    check-cast p1, Lcom/miui/gallery/util/OptionalResult;

    invoke-virtual {v0, v1, p1}, Lcom/miui/gallery/editor/photo/app/filter/skytransfer/SkyLibraryLoaderHelper;->lambda$startDownload$118$SkyLibraryLoaderHelper(ZLcom/miui/gallery/util/OptionalResult;)V

    return-void
.end method
