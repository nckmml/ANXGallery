.class final Lcom/miui/epoxy/ViewHolderState$1;
.super Ljava/lang/Object;
.source "ViewHolderState.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/epoxy/ViewHolderState;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator<",
        "Lcom/miui/epoxy/ViewHolderState;",
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
.method public createFromParcel(Landroid/os/Parcel;)Lcom/miui/epoxy/ViewHolderState;
    .locals 6

    invoke-virtual {p1}, Landroid/os/Parcel;->readInt()I

    move-result v0

    new-instance v1, Lcom/miui/epoxy/ViewHolderState;

    invoke-direct {v1, v0}, Lcom/miui/epoxy/ViewHolderState;-><init>(I)V

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v0, :cond_0

    invoke-virtual {p1}, Landroid/os/Parcel;->readLong()J

    move-result-wide v3

    const-class v5, Lcom/miui/epoxy/ViewHolderState$ViewState;

    invoke-virtual {v5}, Ljava/lang/Class;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v5

    invoke-virtual {p1, v5}, Landroid/os/Parcel;->readParcelable(Ljava/lang/ClassLoader;)Landroid/os/Parcelable;

    move-result-object v5

    check-cast v5, Lcom/miui/epoxy/ViewHolderState$ViewState;

    invoke-virtual {v1, v3, v4, v5}, Lcom/miui/epoxy/ViewHolderState;->put(JLjava/lang/Object;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_0
    return-object v1
.end method

.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/ViewHolderState$1;->createFromParcel(Landroid/os/Parcel;)Lcom/miui/epoxy/ViewHolderState;

    move-result-object p1

    return-object p1
.end method

.method public newArray(I)[Lcom/miui/epoxy/ViewHolderState;
    .locals 0

    new-array p1, p1, [Lcom/miui/epoxy/ViewHolderState;

    return-object p1
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 0

    invoke-virtual {p0, p1}, Lcom/miui/epoxy/ViewHolderState$1;->newArray(I)[Lcom/miui/epoxy/ViewHolderState;

    move-result-object p1

    return-object p1
.end method
