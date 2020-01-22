.class Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;
.super Ljava/lang/Object;
.source "BaseEpoxyAdapter.java"

# interfaces
.implements Ljava/lang/Iterable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/epoxy/BaseEpoxyAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "OrderedMap"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<K:",
        "Ljava/lang/Object;",
        "V:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Ljava/lang/Iterable<",
        "TV;>;"
    }
.end annotation


# instance fields
.field private map:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "TK;TV;>;"
        }
    .end annotation
.end field

.field private orderList:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "TK;>;"
        }
    .end annotation
.end field

.field final synthetic this$0:Lcom/miui/epoxy/BaseEpoxyAdapter;


# direct methods
.method private constructor <init>(Lcom/miui/epoxy/BaseEpoxyAdapter;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->this$0:Lcom/miui/epoxy/BaseEpoxyAdapter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance p1, Ljava/util/HashMap;

    invoke-direct {p1}, Ljava/util/HashMap;-><init>()V

    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->map:Ljava/util/HashMap;

    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->orderList:Ljava/util/List;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/epoxy/BaseEpoxyAdapter;Lcom/miui/epoxy/BaseEpoxyAdapter$1;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;-><init>(Lcom/miui/epoxy/BaseEpoxyAdapter;)V

    return-void
.end method

.method static synthetic access$300(Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;)Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->orderList:Ljava/util/List;

    return-object p0
.end method

.method static synthetic access$400(Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;)Ljava/util/HashMap;
    .locals 0

    iget-object p0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->map:Ljava/util/HashMap;

    return-object p0
.end method


# virtual methods
.method public getFirstOrNull()Ljava/lang/Object;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TV;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->orderList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->map:Ljava/util/HashMap;

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->orderList:Ljava/util/List;

    const/4 v2, 0x0

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    :goto_0
    return-object v0
.end method

.method public iterator()Ljava/util/Iterator;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Iterator<",
            "TV;>;"
        }
    .end annotation

    new-instance v0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;

    invoke-direct {v0, p0}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;-><init>(Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;)V

    return-object v0
.end method

.method public size()I
    .locals 1

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->orderList:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v0

    return v0
.end method

.method public values()Ljava/util/Collection;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Collection<",
            "TV;>;"
        }
    .end annotation

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->orderList:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    iget-object v3, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->map:Ljava/util/HashMap;

    invoke-virtual {v3, v2}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    invoke-interface {v0, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    return-object v0
.end method
