.class public final synthetic Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;

.field private final synthetic f$1:Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;

.field private final synthetic f$2:Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;->f$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;

    iput-object p2, p0, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;->f$1:Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;

    iput-object p3, p0, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;->f$2:Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 3

    iget-object v0, p0, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;->f$0:Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;

    iget-object v1, p0, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;->f$1:Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;

    iget-object v2, p0, Lcom/miui/gallery/editor/photo/app/filter/-$$Lambda$FilterFragment$FilterPagerAdapter$1$Rf3U3xNVk-h0AiEFohpgy4rfIXk;->f$2:Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;

    check-cast p1, Ljava/lang/Boolean;

    invoke-virtual {v0, v1, v2, p1}, Lcom/miui/gallery/editor/photo/app/filter/FilterFragment$FilterPagerAdapter$1;->lambda$OnItemClick$116$FilterFragment$FilterPagerAdapter$1(Lcom/miui/gallery/editor/photo/core/imports/filter/FilterRenderFragment;Lcom/miui/gallery/editor/photo/app/filter/FilterAdapter;Ljava/lang/Boolean;)V

    return-void
.end method
