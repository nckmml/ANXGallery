.class Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;
.super Lcom/miui/gallery/provider/cache/Filter;
.source "Filter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/cache/Filter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "CompoundFilter"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T::",
        "Lcom/miui/gallery/provider/cache/CacheItem;",
        ">",
        "Lcom/miui/gallery/provider/cache/Filter<",
        "TT;>;"
    }
.end annotation


# instance fields
.field private mCompound:Lcom/miui/gallery/provider/cache/Filter$Compound;

.field private mLeft:Lcom/miui/gallery/provider/cache/Filter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/provider/cache/Filter<",
            "TT;>;"
        }
    .end annotation
.end field

.field private mRight:Lcom/miui/gallery/provider/cache/Filter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/miui/gallery/provider/cache/Filter<",
            "TT;>;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/miui/gallery/provider/cache/Filter;Lcom/miui/gallery/provider/cache/Filter;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lcom/miui/gallery/provider/cache/Filter<",
            "TT;>;",
            "Lcom/miui/gallery/provider/cache/Filter<",
            "TT;>;)V"
        }
    .end annotation

    invoke-direct {p0}, Lcom/miui/gallery/provider/cache/Filter;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mLeft:Lcom/miui/gallery/provider/cache/Filter;

    iput-object p3, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mRight:Lcom/miui/gallery/provider/cache/Filter;

    invoke-static {p1}, Lcom/miui/gallery/provider/cache/Filter$Compound;->valueOf(Ljava/lang/String;)Lcom/miui/gallery/provider/cache/Filter$Compound;

    move-result-object p1

    iput-object p1, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mCompound:Lcom/miui/gallery/provider/cache/Filter$Compound;

    return-void
.end method


# virtual methods
.method public filter(Lcom/miui/gallery/provider/cache/CacheItem;)Lcom/miui/gallery/provider/cache/CacheItem;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)TT;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mCompound:Lcom/miui/gallery/provider/cache/Filter$Compound;

    sget-object v1, Lcom/miui/gallery/provider/cache/Filter$Compound;->AND:Lcom/miui/gallery/provider/cache/Filter$Compound;

    if-ne v0, v1, :cond_1

    iget-object v0, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mLeft:Lcom/miui/gallery/provider/cache/Filter;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/provider/cache/Filter;->filter(Lcom/miui/gallery/provider/cache/CacheItem;)Lcom/miui/gallery/provider/cache/CacheItem;

    move-result-object v0

    if-nez v0, :cond_0

    const/4 p1, 0x0

    return-object p1

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mRight:Lcom/miui/gallery/provider/cache/Filter;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/provider/cache/Filter;->filter(Lcom/miui/gallery/provider/cache/CacheItem;)Lcom/miui/gallery/provider/cache/CacheItem;

    move-result-object p1

    return-object p1

    :cond_1
    iget-object v0, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mLeft:Lcom/miui/gallery/provider/cache/Filter;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/provider/cache/Filter;->filter(Lcom/miui/gallery/provider/cache/CacheItem;)Lcom/miui/gallery/provider/cache/CacheItem;

    move-result-object v0

    if-eqz v0, :cond_2

    return-object p1

    :cond_2
    iget-object v0, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mRight:Lcom/miui/gallery/provider/cache/Filter;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/provider/cache/Filter;->filter(Lcom/miui/gallery/provider/cache/CacheItem;)Lcom/miui/gallery/provider/cache/CacheItem;

    move-result-object p1

    return-object p1
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "CompoundFilter{mFilters=["

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mLeft:Lcom/miui/gallery/provider/cache/Filter;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mRight:Lcom/miui/gallery/provider/cache/Filter;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, "]"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", mCompound="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/provider/cache/Filter$CompoundFilter;->mCompound:Lcom/miui/gallery/provider/cache/Filter$Compound;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/16 v1, 0x7d

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
