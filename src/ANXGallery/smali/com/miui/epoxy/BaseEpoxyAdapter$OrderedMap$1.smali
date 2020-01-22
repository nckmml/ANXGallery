.class Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;
.super Ljava/lang/Object;
.source "BaseEpoxyAdapter.java"

# interfaces
.implements Ljava/util/Iterator;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->iterator()Ljava/util/Iterator;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/Iterator<",
        "TV;>;"
    }
.end annotation


# instance fields
.field private index:I

.field final synthetic this$1:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;


# direct methods
.method constructor <init>(Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->this$1:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const/4 p1, 0x0

    iput p1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->index:I

    return-void
.end method


# virtual methods
.method public hasNext()Z
    .locals 2

    iget v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->index:I

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->this$1:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-static {v1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->access$300(Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;)Ljava/util/List;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public next()Ljava/lang/Object;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TV;"
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->this$1:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-static {v0}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->access$400(Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;)Ljava/util/HashMap;

    move-result-object v0

    iget-object v1, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->this$1:Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;

    invoke-static {v1}, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;->access$300(Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap;)Ljava/util/List;

    move-result-object v1

    iget v2, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->index:I

    add-int/lit8 v3, v2, 0x1

    iput v3, p0, Lcom/miui/epoxy/BaseEpoxyAdapter$OrderedMap$1;->index:I

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    return-object v0

    :cond_0
    new-instance v0, Ljava/util/NoSuchElementException;

    invoke-direct {v0}, Ljava/util/NoSuchElementException;-><init>()V

    throw v0
.end method

.method public remove()V
    .locals 1

    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method
