.class final Lcom/miui/epoxy/ViewHolderState$ViewState$1;
.super Ljava/lang/Object;
.source "ViewHolderState.java"

# interfaces
.implements Landroidx/core/os/ParcelableCompatCreatorCallbacks;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/epoxy/ViewHolderState$ViewState;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroidx/core/os/ParcelableCompatCreatorCallbacks<",
        "Lcom/miui/epoxy/ViewHolderState$ViewState;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public createFromParcel(Landroid/os/Parcel;Ljava/lang/ClassLoader;)Lcom/miui/epoxy/ViewHolderState$ViewState;
    .locals 3

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    new-array v1, v0, [I

    invoke-virtual {p1, v1}, Landroid/os/Parcel;->readIntArray([I)V

    invoke-virtual {p1, p2}, Landroid/os/Parcel;->readParcelableArray(Ljava/lang/ClassLoader;)[Landroid/os/Parcelable;

    move-result-object p1

    new-instance p2, Lcom/miui/epoxy/ViewHolderState$ViewState;

    const/4 v2, 0x0

    invoke-direct {p2, v0, v1, p1, v2}, Lcom/miui/epoxy/ViewHolderState$ViewState;-><init>(I[I[Landroid/os/Parcelable;Lcom/miui/epoxy/ViewHolderState$1;)V

    return-object p2
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;Ljava/lang/ClassLoader;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1, p2}, Lcom/miui/epoxy/ViewHolderState$ViewState$1;->createFromParcel(Landroid/os/Parcel;Ljava/lang/ClassLoader;)Lcom/miui/epoxy/ViewHolderState$ViewState;

    move-result-object p1

    return-object p1
.end method

.method public newArray(I)[Lcom/miui/epoxy/ViewHolderState$ViewState;
    .locals 0

    new-array p1, p1, [Lcom/miui/epoxy/ViewHolderState$ViewState;

    return-object p1
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/ViewHolderState$ViewState$1;->newArray(I)[Lcom/miui/epoxy/ViewHolderState$ViewState;

    move-result-object p1

    return-object p1
.end method
