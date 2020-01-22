.class Lcom/miui/epoxy/ViewHolderState;
.super Landroidx/collection/LongSparseArray;
.source "ViewHolderState.java"

# interfaces
.implements Landroid/os/Parcelable;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/epoxy/ViewHolderState$ViewState;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroidx/collection/LongSparseArray<",
        "Lcom/miui/epoxy/ViewHolderState$ViewState;",
        ">;",
        "Landroid/os/Parcelable;"
    }
.end annotation


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/miui/epoxy/ViewHolderState;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/epoxy/ViewHolderState$1;

    invoke-direct {v0}, Lcom/miui/epoxy/ViewHolderState$1;-><init>()V

    sput-object v0, Lcom/miui/epoxy/ViewHolderState;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroidx/collection/LongSparseArray;-><init>()V

    return-void
.end method

.method public constructor <init>(I)V
    .locals 0

    invoke-direct {p0, p1}, Landroidx/collection/LongSparseArray;-><init>(I)V

    return-void
.end method


# virtual methods
.method public describeContents()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public restore(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 2

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->shouldSaveViewState()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->getItemId()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/miui/epoxy/ViewHolderState;->get(J)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/epoxy/ViewHolderState$ViewState;

    if-eqz v0, :cond_1

    iget-object p1, p1, Lcom/miui/epoxy/EpoxyViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0, p1}, Lcom/miui/epoxy/ViewHolderState$ViewState;->restore(Landroid/view/View;)V

    :cond_1
    return-void
.end method

.method public save(Lcom/miui/epoxy/EpoxyViewHolder;)V
    .locals 3

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->shouldSaveViewState()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->getItemId()J

    move-result-wide v0

    invoke-virtual {p0, v0, v1}, Lcom/miui/epoxy/ViewHolderState;->get(J)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/miui/epoxy/ViewHolderState$ViewState;

    if-nez v0, :cond_1

    new-instance v0, Lcom/miui/epoxy/ViewHolderState$ViewState;

    invoke-direct {v0}, Lcom/miui/epoxy/ViewHolderState$ViewState;-><init>()V

    :cond_1
    iget-object v1, p1, Lcom/miui/epoxy/EpoxyViewHolder;->itemView:Landroid/view/View;

    invoke-virtual {v0, v1}, Lcom/miui/epoxy/ViewHolderState$ViewState;->save(Landroid/view/View;)V

    invoke-virtual {p1}, Lcom/miui/epoxy/EpoxyViewHolder;->getItemId()J

    move-result-wide v1

    invoke-virtual {p0, v1, v2, v0}, Lcom/miui/epoxy/ViewHolderState;->put(JLjava/lang/Object;)V

    return-void
.end method

.method public writeToParcel(Landroid/os/Parcel;I)V
    .locals 4

    invoke-virtual {p0}, Lcom/miui/epoxy/ViewHolderState;->size()I

    move-result p2

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->writeInt(I)V

    const/4 v0, 0x0

    move v1, v0

    :goto_0
    if-ge v1, p2, :cond_0

    invoke-virtual {p0, v1}, Lcom/miui/epoxy/ViewHolderState;->keyAt(I)J

    move-result-wide v2

    invoke-virtual {p1, v2, v3}, Landroid/os/Parcel;->writeLong(J)V

    invoke-virtual {p0, v1}, Lcom/miui/epoxy/ViewHolderState;->valueAt(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/os/Parcelable;

    invoke-virtual {p1, v2, v0}, Landroid/os/Parcel;->writeParcelable(Landroid/os/Parcelable;I)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_0
    return-void
.end method
