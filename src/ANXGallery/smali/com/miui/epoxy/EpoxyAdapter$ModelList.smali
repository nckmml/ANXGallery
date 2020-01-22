.class public Lcom/miui/epoxy/EpoxyAdapter$ModelList;
.super Ljava/util/ArrayList;
.source "EpoxyAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/epoxy/EpoxyAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xc
    name = "ModelList"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/util/ArrayList<",
        "Lcom/miui/epoxy/EpoxyModel<",
        "*>;>;"
    }
.end annotation


# instance fields
.field private final viewHolderFactory:Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;


# direct methods
.method protected constructor <init>()V
    .locals 2

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    new-instance v0, Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;-><init>(Lcom/miui/epoxy/EpoxyAdapter$1;)V

    iput-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->viewHolderFactory:Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    return-void
.end method

.method static synthetic access$100(Lcom/miui/epoxy/EpoxyAdapter$ModelList;)Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;
    .locals 0

    iget-object p0, p0, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->viewHolderFactory:Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    return-object p0
.end method


# virtual methods
.method public add(ILcom/miui/epoxy/EpoxyModel;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->viewHolderFactory:Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    invoke-virtual {v0, p2}, Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;->register(Lcom/miui/epoxy/EpoxyModel;)V

    invoke-super {p0, p1, p2}, Ljava/util/ArrayList;->add(ILjava/lang/Object;)V

    return-void
.end method

.method public bridge synthetic add(ILjava/lang/Object;)V
    .locals 0

    check-cast p2, Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {p0, p1, p2}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->add(ILcom/miui/epoxy/EpoxyModel;)V

    return-void
.end method

.method public add(Lcom/miui/epoxy/EpoxyModel;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;)Z"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->viewHolderFactory:Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;->register(Lcom/miui/epoxy/EpoxyModel;)V

    invoke-super {p0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    move-result p1

    return p1
.end method

.method public bridge synthetic add(Ljava/lang/Object;)Z
    .locals 0

    check-cast p1, Lcom/miui/epoxy/EpoxyModel;

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->add(Lcom/miui/epoxy/EpoxyModel;)Z

    move-result p1

    return p1
.end method

.method public addAll(ILjava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;)Z"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->viewHolderFactory:Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    invoke-virtual {v0, p2}, Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;->register(Ljava/util/Collection;)V

    invoke-super {p0, p1, p2}, Ljava/util/ArrayList;->addAll(ILjava/util/Collection;)Z

    move-result p1

    return p1
.end method

.method public addAll(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection<",
            "+",
            "Lcom/miui/epoxy/EpoxyModel<",
            "*>;>;)Z"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/epoxy/EpoxyAdapter$ModelList;->viewHolderFactory:Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/EpoxyAdapter$ViewHolderFactory;->register(Ljava/util/Collection;)V

    invoke-super {p0, p1}, Ljava/util/ArrayList;->addAll(Ljava/util/Collection;)Z

    move-result p1

    return p1
.end method
