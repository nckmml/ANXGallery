.class public Lcom/miui/gallery/picker/PickerActivity$SimplePicker;
.super Ljava/lang/Object;
.source "PickerActivity.java"

# interfaces
.implements Lcom/miui/gallery/picker/helper/Picker;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/picker/PickerActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xc
    name = "SimplePicker"
.end annotation


# instance fields
.field private final mBaseline:I

.field private final mCapacity:I

.field private mFilterMimeTypes:[Ljava/lang/String;

.field private mImageType:Lcom/miui/gallery/picker/helper/Picker$ImageType;

.field private mMediaType:Lcom/miui/gallery/picker/helper/Picker$MediaType;

.field private mObservable:Landroid/database/DataSetObservable;

.field private mPickMode:Lcom/miui/gallery/picker/helper/Picker$Mode;

.field private mPickerActivity:Lcom/miui/gallery/picker/PickerActivity;

.field private mResultType:Lcom/miui/gallery/picker/helper/Picker$ResultType;

.field private mResults:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public constructor <init>(Lcom/miui/gallery/picker/PickerActivity;IILjava/util/Set;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/picker/PickerActivity;",
            "II",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget-object v0, Lcom/miui/gallery/picker/helper/Picker$ImageType;->THUMBNAIL:Lcom/miui/gallery/picker/helper/Picker$ImageType;

    iput-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mImageType:Lcom/miui/gallery/picker/helper/Picker$ImageType;

    if-eqz p4, :cond_3

    const/4 v0, 0x1

    if-gez p2, :cond_0

    sget-object p2, Lcom/miui/gallery/picker/helper/Picker$Mode;->UNLIMITED:Lcom/miui/gallery/picker/helper/Picker$Mode;

    iput-object p2, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickMode:Lcom/miui/gallery/picker/helper/Picker$Mode;

    const p2, 0x7fffffff

    goto :goto_0

    :cond_0
    if-le p2, v0, :cond_1

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$Mode;->MULTIPLE:Lcom/miui/gallery/picker/helper/Picker$Mode;

    iput-object v1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickMode:Lcom/miui/gallery/picker/helper/Picker$Mode;

    goto :goto_0

    :cond_1
    sget-object p2, Lcom/miui/gallery/picker/helper/Picker$Mode;->SINGLE:Lcom/miui/gallery/picker/helper/Picker$Mode;

    iput-object p2, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickMode:Lcom/miui/gallery/picker/helper/Picker$Mode;

    move p2, v0

    :goto_0
    invoke-interface {p4}, Ljava/util/Set;->size()I

    move-result v1

    if-gt v1, p2, :cond_2

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickerActivity:Lcom/miui/gallery/picker/PickerActivity;

    iput-object p4, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    iput p2, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mCapacity:I

    iput p3, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mBaseline:I

    new-instance p1, Landroid/database/DataSetObservable;

    invoke-direct {p1}, Landroid/database/DataSetObservable;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mObservable:Landroid/database/DataSetObservable;

    return-void

    :cond_2
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const/4 p3, 0x2

    new-array p3, p3, [Ljava/lang/Object;

    const/4 v1, 0x0

    invoke-interface {p4}, Ljava/util/Set;->size()I

    move-result p4

    invoke-static {p4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p4

    aput-object p4, p3, v1

    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    aput-object p2, p3, v0

    const-string p2, "ResultMap size (%d) is too large this picker (%d)"

    invoke-static {p2, p3}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1

    :cond_3
    new-instance p1, Ljava/lang/IllegalArgumentException;

    const-string p2, "Result can\'t be null"

    invoke-direct {p1, p2}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p1
.end method


# virtual methods
.method public baseline()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mBaseline:I

    return v0
.end method

.method public cancel()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickerActivity:Lcom/miui/gallery/picker/PickerActivity;

    invoke-virtual {v0}, Lcom/miui/gallery/picker/PickerActivity;->onCancel()V

    return-void
.end method

.method public capacity()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mCapacity:I

    return v0
.end method

.method public clear()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x0

    return v0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->clear()V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mObservable:Landroid/database/DataSetObservable;

    invoke-virtual {v0}, Landroid/database/DataSetObservable;->notifyChanged()V

    const/4 v0, 0x1

    return v0
.end method

.method public contains(Ljava/lang/String;)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0, p1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result p1

    return p1
.end method

.method public count()I
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->size()I

    move-result v0

    return v0
.end method

.method public done()V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickerActivity:Lcom/miui/gallery/picker/PickerActivity;

    invoke-virtual {v0}, Lcom/miui/gallery/picker/PickerActivity;->onDone()V

    return-void
.end method

.method public getFilterMimeTypes()[Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mFilterMimeTypes:[Ljava/lang/String;

    return-object v0
.end method

.method public getImageType()Lcom/miui/gallery/picker/helper/Picker$ImageType;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mImageType:Lcom/miui/gallery/picker/helper/Picker$ImageType;

    return-object v0
.end method

.method public getMediaType()Lcom/miui/gallery/picker/helper/Picker$MediaType;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mMediaType:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    return-object v0
.end method

.method public getMode()Lcom/miui/gallery/picker/helper/Picker$Mode;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickMode:Lcom/miui/gallery/picker/helper/Picker$Mode;

    return-object v0
.end method

.method public getResultType()Lcom/miui/gallery/picker/helper/Picker$ResultType;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResultType:Lcom/miui/gallery/picker/helper/Picker$ResultType;

    return-object v0
.end method

.method public isFull()Z
    .locals 2

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->count()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->capacity()I

    move-result v1

    if-lt v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public iterator()Ljava/util/Iterator;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Iterator<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    return-object v0
.end method

.method public pick(Ljava/lang/String;)Z
    .locals 2

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mPickMode:Lcom/miui/gallery/picker/helper/Picker$Mode;

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$Mode;->SINGLE:Lcom/miui/gallery/picker/helper/Picker$Mode;

    if-ne v0, v1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0}, Ljava/util/Set;->clear()V

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->isFull()Z

    move-result v0

    if-eqz v0, :cond_1

    const/4 p1, 0x0

    return p1

    :cond_1
    :goto_0
    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0, p1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mObservable:Landroid/database/DataSetObservable;

    invoke-virtual {v0}, Landroid/database/DataSetObservable;->notifyChanged()V

    :cond_2
    return p1
.end method

.method public pickAll(Ljava/util/List;)Z
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)Z"
        }
    .end annotation

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->isFull()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    return v1

    :cond_0
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v0

    move v2, v1

    move v3, v2

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->isFull()Z

    move-result v4

    if-nez v4, :cond_1

    if-ge v2, v0, :cond_1

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->capacity()I

    move-result v4

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->count()I

    move-result v5

    sub-int/2addr v4, v5

    invoke-static {v1, v4}, Ljava/lang/Math;->max(II)I

    move-result v4

    add-int/2addr v4, v2

    invoke-static {v4, v0}, Ljava/lang/Math;->min(II)I

    move-result v4

    iget-object v5, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {p1, v2, v4}, Ljava/util/List;->subList(II)Ljava/util/List;

    move-result-object v2

    invoke-interface {v5, v2}, Ljava/util/Set;->addAll(Ljava/util/Collection;)Z

    move-result v2

    or-int/2addr v3, v2

    move v2, v4

    goto :goto_0

    :cond_1
    if-eqz v3, :cond_2

    iget-object p1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mObservable:Landroid/database/DataSetObservable;

    invoke-virtual {p1}, Landroid/database/DataSetObservable;->notifyChanged()V

    :cond_2
    return v3
.end method

.method public registerObserver(Landroid/database/DataSetObserver;)V
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mObservable:Landroid/database/DataSetObservable;

    invoke-virtual {v0, p1}, Landroid/database/DataSetObservable;->registerObserver(Ljava/lang/Object;)V

    return-void
.end method

.method public remove(Ljava/lang/String;)Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0, p1}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mObservable:Landroid/database/DataSetObservable;

    invoke-virtual {v0}, Landroid/database/DataSetObservable;->notifyChanged()V

    :cond_0
    return p1
.end method

.method public removeAll(Ljava/util/List;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)Z"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-interface {v0, p1}, Ljava/util/Set;->removeAll(Ljava/util/Collection;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mObservable:Landroid/database/DataSetObservable;

    invoke-virtual {v0}, Landroid/database/DataSetObservable;->notifyChanged()V

    :cond_0
    return p1
.end method

.method public setFilterMimeTypes([Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mFilterMimeTypes:[Ljava/lang/String;

    return-void
.end method

.method public setImageType(Lcom/miui/gallery/picker/helper/Picker$ImageType;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mImageType:Lcom/miui/gallery/picker/helper/Picker$ImageType;

    return-void
.end method

.method public setMediaType(Lcom/miui/gallery/picker/helper/Picker$MediaType;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mMediaType:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    return-void
.end method

.method public setResultType(Lcom/miui/gallery/picker/helper/Picker$ResultType;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResultType:Lcom/miui/gallery/picker/helper/Picker$ResultType;

    return-void
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "SimplePicker{mResults="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;->mResults:Ljava/util/Set;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const/16 v1, 0x7d

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
