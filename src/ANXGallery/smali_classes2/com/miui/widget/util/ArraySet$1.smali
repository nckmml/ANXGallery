.class Lcom/miui/widget/util/ArraySet$1;
.super Lcom/miui/widget/util/MapCollections;
.source "ArraySet.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/miui/widget/util/ArraySet;->getCollection()Lcom/miui/widget/util/MapCollections;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lcom/miui/widget/util/MapCollections<",
        "TE;TE;>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/miui/widget/util/ArraySet;


# direct methods
.method constructor <init>(Lcom/miui/widget/util/ArraySet;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-direct {p0}, Lcom/miui/widget/util/MapCollections;-><init>()V

    return-void
.end method


# virtual methods
.method protected colClear()V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-virtual {v0}, Lcom/miui/widget/util/ArraySet;->clear()V

    return-void
.end method

.method protected colGetEntry(II)Ljava/lang/Object;
    .locals 0

    iget-object p2, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    iget-object p2, p2, Lcom/miui/widget/util/ArraySet;->mArray:[Ljava/lang/Object;

    aget-object p1, p2, p1

    return-object p1
.end method

.method protected colGetMap()Ljava/util/Map;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "TE;TE;>;"
        }
    .end annotation

    new-instance v0, Ljava/lang/UnsupportedOperationException;

    const-string v1, "not a map"

    invoke-direct {v0, v1}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method protected colGetSize()I
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    iget v0, v0, Lcom/miui/widget/util/ArraySet;->mSize:I

    return v0
.end method

.method protected colIndexOfKey(Ljava/lang/Object;)I
    .locals 2

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-static {p1}, Lcom/miui/widget/util/ArraySet;->access$000(Lcom/miui/widget/util/ArraySet;)I

    move-result p1

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-static {v0, p1, v1}, Lcom/miui/widget/util/ArraySet;->access$100(Lcom/miui/widget/util/ArraySet;Ljava/lang/Object;I)I

    move-result p1

    :goto_0
    return p1
.end method

.method protected colIndexOfValue(Ljava/lang/Object;)I
    .locals 2

    if-nez p1, :cond_0

    iget-object p1, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-static {p1}, Lcom/miui/widget/util/ArraySet;->access$000(Lcom/miui/widget/util/ArraySet;)I

    move-result p1

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-virtual {p1}, Ljava/lang/Object;->hashCode()I

    move-result v1

    invoke-static {v0, p1, v1}, Lcom/miui/widget/util/ArraySet;->access$100(Lcom/miui/widget/util/ArraySet;Ljava/lang/Object;I)I

    move-result p1

    :goto_0
    return p1
.end method

.method protected colPut(Ljava/lang/Object;Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TE;TE;)V"
        }
    .end annotation

    iget-object p2, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-virtual {p2, p1}, Lcom/miui/widget/util/ArraySet;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method protected colRemoveAt(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/util/ArraySet$1;->this$0:Lcom/miui/widget/util/ArraySet;

    invoke-virtual {v0, p1}, Lcom/miui/widget/util/ArraySet;->removeAt(I)Ljava/lang/Object;

    return-void
.end method

.method protected colSetValue(ILjava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(ITE;)TE;"
        }
    .end annotation

    new-instance p1, Ljava/lang/UnsupportedOperationException;

    const-string p2, "not a map"

    invoke-direct {p1, p2}, Ljava/lang/UnsupportedOperationException;-><init>(Ljava/lang/String;)V

    throw p1
.end method
