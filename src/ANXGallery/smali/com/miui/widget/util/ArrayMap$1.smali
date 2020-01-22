.class Lcom/miui/widget/util/ArrayMap$1;
.super Lcom/miui/widget/util/MapCollections;
.source "ArrayMap.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/widget/util/ArrayMap;->getCollection()Lcom/miui/widget/util/MapCollections;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/widget/util/MapCollections<",
        "TK;TV;>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/widget/util/ArrayMap;


# direct methods
.method constructor <init>(Lcom/miui/widget/util/ArrayMap;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-direct {p0}, Lcom/miui/widget/util/MapCollections;-><init>()V

    return-void
.end method


# virtual methods
.method protected colClear()V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-virtual {v0}, Lcom/miui/widget/util/ArrayMap;->clear()V

    return-void
.end method

.method protected colGetEntry(II)Ljava/lang/Object;
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    iget-object v0, v0, Lcom/miui/widget/util/ArrayMap;->mArray:[Ljava/lang/Object;

    shl-int/lit8 p1, p1, 0x1

    add-int/2addr p1, p2

    aget-object p1, v0, p1

    return-object p1
.end method

.method protected colGetMap()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "TK;TV;>;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    return-object v0
.end method

.method protected colGetSize()I
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    iget v0, v0, Lcom/miui/widget/util/ArrayMap;->mSize:I

    return v0
.end method

.method protected colIndexOfKey(Ljava/lang/Object;)I
    .locals 2

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-virtual {p1}, Lcom/miui/widget/util/ArrayMap;->indexOfNull()I

    move-result p1

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-virtual {v0, p1, v1}, Lcom/miui/widget/util/ArrayMap;->indexOf(Ljava/lang/Object;I)I

    move-result p1

    :goto_0
    return p1
.end method

.method protected colIndexOfValue(Ljava/lang/Object;)I
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-virtual {v0, p1}, Lcom/miui/widget/util/ArrayMap;->indexOfValue(Ljava/lang/Object;)I

    move-result p1

    return p1
.end method

.method protected colPut(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TK;TV;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-virtual {v0, p1, p2}, Lcom/miui/widget/util/ArrayMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method protected colRemoveAt(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-virtual {v0, p1}, Lcom/miui/widget/util/ArrayMap;->removeAt(I)Ljava/lang/Object;

    return-void
.end method

.method protected colSetValue(ILjava/lang/Object;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ITV;)TV;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/widget/util/ArrayMap$1;->this$0:Lcom/miui/widget/util/ArrayMap;

    invoke-virtual {v0, p1, p2}, Lcom/miui/widget/util/ArrayMap;->setValueAt(ILjava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method
