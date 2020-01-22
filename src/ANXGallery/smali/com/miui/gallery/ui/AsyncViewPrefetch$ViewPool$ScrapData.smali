.class Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool$ScrapData;
.super Ljava/lang/Object;
.source "AsyncViewPrefetch.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "ScrapData"
.end annotation


# instance fields
.field final mHeap:Ljava/util/LinkedList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/LinkedList<",
            "Landroid/view/View;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/LinkedList;

    invoke-direct {v0}, Ljava/util/LinkedList;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool$ScrapData;->mHeap:Ljava/util/LinkedList;

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/AsyncViewPrefetch$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool$ScrapData;-><init>()V

    return-void
.end method


# virtual methods
.method pop()Landroid/view/View;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool$ScrapData;->mHeap:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/LinkedList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool$ScrapData;->mHeap:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/LinkedList;->pop()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/view/View;

    :goto_0
    return-object v0
.end method

.method push(Landroid/view/View;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool$ScrapData;->mHeap:Ljava/util/LinkedList;

    invoke-virtual {v0, p1}, Ljava/util/LinkedList;->add(Ljava/lang/Object;)Z

    return-void
.end method

.method size()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/ui/AsyncViewPrefetch$ViewPool$ScrapData;->mHeap:Ljava/util/LinkedList;

    invoke-virtual {v0}, Ljava/util/LinkedList;->size()I

    move-result v0

    return v0
.end method
