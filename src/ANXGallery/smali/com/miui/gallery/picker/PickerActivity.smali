.class public abstract Lcom/miui/gallery/picker/PickerActivity;
.super Lcom/miui/gallery/picker/PickerCompatActivity;
.source "PickerActivity.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/picker/PickerActivity$SimplePicker;,
        Lcom/miui/gallery/picker/PickerActivity$Decor;
    }
.end annotation


# static fields
.field protected static final PICKABLE_PROJECTION:[Ljava/lang/String;


# instance fields
.field private mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

.field protected mPicker:Lcom/miui/gallery/picker/helper/Picker;

.field private mPickerTitle:Ljava/lang/CharSequence;

.field private mTitle:Ljava/lang/CharSequence;


# direct methods
.method static constructor <clinit>()V
    .locals 9

    const-string v0, "_id"

    const-string v1, "sha1"

    const-string v2, "microthumbfile"

    const-string v3, "thumbnailFile"

    const-string v4, "localFile"

    const-string v5, "serverType"

    const-string v6, "size"

    const-string v7, "exifImageLength"

    const-string v8, "exifImageWidth"

    filled-new-array/range {v0 .. v8}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/picker/PickerActivity;->PICKABLE_PROJECTION:[Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;-><init>()V

    return-void
.end method

.method public static copyPicker(Lcom/miui/gallery/picker/helper/Picker;)Ljava/util/HashSet;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/miui/gallery/picker/helper/Picker;",
            ")",
            "Ljava/util/HashSet<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    if-eqz p0, :cond_1

    new-instance v0, Ljava/util/LinkedHashSet;

    invoke-interface {p0}, Lcom/miui/gallery/picker/helper/Picker;->count()I

    move-result v1

    invoke-direct {v0, v1}, Ljava/util/LinkedHashSet;-><init>(I)V

    invoke-interface {p0}, Lcom/miui/gallery/picker/helper/Picker;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/util/HashSet;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_0
    return-object v0

    :cond_1
    new-instance p0, Ljava/util/LinkedHashSet;

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Ljava/util/LinkedHashSet;-><init>(I)V

    return-object p0
.end method

.method private restoreInstanceState(Landroid/os/Bundle;)V
    .locals 6

    const-string v0, "PickerActivity"

    const-string v1, "restore instance state for picker: "

    invoke-static {v0, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    const/4 v1, 0x1

    const-string v2, "com.miui.gallery.picker.PickerActivity.capacity"

    invoke-virtual {p1, v2, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v2

    const-string v3, "com.miui.gallery.picker.PickerActivity.baseline"

    invoke-virtual {p1, v3, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;I)I

    move-result v1

    const-string v3, "com.miui.gallery.picker.PickerActivity.results"

    invoke-virtual {p1, v3}, Landroid/os/Bundle;->getSerializable(Ljava/lang/String;)Ljava/io/Serializable;

    move-result-object v3

    check-cast v3, Ljava/util/Set;

    if-nez v3, :cond_0

    new-instance v3, Ljava/util/LinkedHashSet;

    invoke-direct {v3, v2}, Ljava/util/LinkedHashSet;-><init>(I)V

    :cond_0
    new-instance v4, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;

    invoke-direct {v4, p0, v2, v1, v3}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;-><init>(Lcom/miui/gallery/picker/PickerActivity;IILjava/util/Set;)V

    iput-object v4, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    const-string v1, "com.miui.gallery.picker.PickerActivity.media_type"

    invoke-virtual {p1, v1}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v1

    const-string v4, "com.miui.gallery.picker.PickerActivity.result_type"

    invoke-virtual {p1, v4}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result p1

    iget-object v4, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-static {}, Lcom/miui/gallery/picker/helper/Picker$MediaType;->values()[Lcom/miui/gallery/picker/helper/Picker$MediaType;

    move-result-object v5

    aget-object v1, v5, v1

    invoke-interface {v4, v1}, Lcom/miui/gallery/picker/helper/Picker;->setMediaType(Lcom/miui/gallery/picker/helper/Picker$MediaType;)V

    iget-object v1, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-static {}, Lcom/miui/gallery/picker/helper/Picker$ResultType;->values()[Lcom/miui/gallery/picker/helper/Picker$ResultType;

    move-result-object v4

    aget-object p1, v4, p1

    invoke-interface {v1, p1}, Lcom/miui/gallery/picker/helper/Picker;->setResultType(Lcom/miui/gallery/picker/helper/Picker$ResultType;)V

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {v3}, Ljava/util/Set;->size()I

    move-result v1

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    const-string v2, "picker[capacity:%d, size:%d] restored."

    invoke-static {v0, v2, p1, v1}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method


# virtual methods
.method public getPicker()Lcom/miui/gallery/picker/helper/Picker;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    return-object v0
.end method

.method public onAttachFragment(Landroid/app/Fragment;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickerCompatActivity;->onAttachFragment(Landroid/app/Fragment;)V

    instance-of v0, p1, Lcom/miui/gallery/picker/PickerFragment;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/miui/gallery/picker/PickerFragment;

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/picker/PickerFragment;->attach(Lcom/miui/gallery/picker/helper/Picker;)V

    :cond_0
    return-void
.end method

.method protected onCancel()V
    .locals 0

    invoke-super {p0}, Lcom/miui/gallery/picker/PickerCompatActivity;->finish()V

    return-void
.end method

.method public onConfigurationChanged(Landroid/content/res/Configuration;)V
    .locals 1

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickerCompatActivity;->onConfigurationChanged(Landroid/content/res/Configuration;)V

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity;->initActionBar()V

    iget-object p1, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    invoke-virtual {p1}, Lcom/miui/gallery/picker/PickerActivity$Decor;->applyActionBar()V

    iget-object p1, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mTitle:Ljava/lang/CharSequence;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/picker/PickerActivity$Decor;->setTitle(Ljava/lang/CharSequence;)V

    iget-object p1, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPickerTitle:Ljava/lang/CharSequence;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/picker/PickerActivity$Decor;->setPickerTitle(Ljava/lang/CharSequence;)V

    return-void
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2

    if-eqz p1, :cond_0

    invoke-direct {p0, p1}, Lcom/miui/gallery/picker/PickerActivity;->restoreInstanceState(Landroid/os/Bundle;)V

    goto :goto_0

    :cond_0
    :try_start_0
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity;->onCreatePicker()Lcom/miui/gallery/picker/helper/Picker;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity;->onCreateDecor()Lcom/miui/gallery/picker/PickerActivity$Decor;

    move-result-object v0

    iput-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    invoke-virtual {v0}, Lcom/miui/gallery/picker/PickerActivity$Decor;->applyTheme()V

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickerCompatActivity;->onCreate(Landroid/os/Bundle;)V

    iget-object p1, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    invoke-virtual {p1}, Lcom/miui/gallery/picker/PickerActivity$Decor;->applyActionBar()V

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity;->updateTitle()V

    return-void

    :catch_0
    move-exception v0

    const-string v1, "PickerActivity"

    invoke-static {v1, v0}, Lcom/miui/gallery/util/Log;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickerCompatActivity;->onCreate(Landroid/os/Bundle;)V

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity;->finish()V

    return-void
.end method

.method protected onCreateDecor()Lcom/miui/gallery/picker/PickerActivity$Decor;
    .locals 1

    invoke-static {p0}, Lcom/miui/gallery/picker/PickerActivity$Decor;->create(Lcom/miui/gallery/picker/PickerActivity;)Lcom/miui/gallery/picker/PickerActivity$Decor;

    move-result-object v0

    return-object v0
.end method

.method protected onCreatePicker()Lcom/miui/gallery/picker/helper/Picker;
    .locals 9

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    invoke-virtual {v0, p0}, Landroid/content/Intent;->resolveType(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v1

    const-string v2, "pick-upper-bound"

    invoke-virtual {v0, v2}, Landroid/content/Intent;->hasExtra(Ljava/lang/String;)Z

    move-result v3

    const-string v4, "PickerActivity"

    const/4 v5, -0x1

    const/4 v6, 0x0

    const/4 v7, 0x1

    if-eqz v3, :cond_0

    invoke-virtual {v0, v2, v5}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const-string v8, "initial pick bound: %d"

    invoke-static {v4, v8, v3}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    goto :goto_0

    :cond_0
    const-string v2, "android.intent.extra.ALLOW_MULTIPLE"

    invoke-virtual {v0, v2, v6}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v2

    if-eqz v2, :cond_1

    const-string v2, "standard pick multiple"

    invoke-static {v4, v2}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)V

    move v2, v5

    goto :goto_0

    :cond_1
    move v2, v7

    :goto_0
    const-string v3, "pick-lower-bound"

    invoke-virtual {v0, v3, v7}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v3

    if-ge v3, v7, :cond_2

    move v3, v7

    :cond_2
    if-eq v2, v5, :cond_3

    if-le v3, v2, :cond_4

    :cond_3
    move v3, v7

    :cond_4
    new-instance v5, Ljava/util/LinkedHashSet;

    invoke-direct {v5}, Ljava/util/LinkedHashSet;-><init>()V

    new-instance v7, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;

    invoke-direct {v7, p0, v2, v3, v5}, Lcom/miui/gallery/picker/PickerActivity$SimplePicker;-><init>(Lcom/miui/gallery/picker/PickerActivity;IILjava/util/Set;)V

    const-string v3, "image/*"

    invoke-virtual {v3, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    const-string v5, "vnd.android.cursor.dir/video"

    const-string v8, "vnd.android.cursor.dir/image"

    if-eqz v3, :cond_5

    sget-object v3, Lcom/miui/gallery/picker/helper/Picker$MediaType;->IMAGE:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    invoke-interface {v7, v3}, Lcom/miui/gallery/picker/helper/Picker;->setMediaType(Lcom/miui/gallery/picker/helper/Picker$MediaType;)V

    goto :goto_1

    :cond_5
    const-string v3, "video/*"

    invoke-virtual {v3, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_6

    sget-object v3, Lcom/miui/gallery/picker/helper/Picker$MediaType;->VIDEO:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    invoke-interface {v7, v3}, Lcom/miui/gallery/picker/helper/Picker;->setMediaType(Lcom/miui/gallery/picker/helper/Picker$MediaType;)V

    goto :goto_1

    :cond_6
    invoke-virtual {v8, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_7

    sget-object v3, Lcom/miui/gallery/picker/helper/Picker$MediaType;->IMAGE:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    invoke-interface {v7, v3}, Lcom/miui/gallery/picker/helper/Picker;->setMediaType(Lcom/miui/gallery/picker/helper/Picker$MediaType;)V

    goto :goto_1

    :cond_7
    invoke-virtual {v5, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_8

    sget-object v3, Lcom/miui/gallery/picker/helper/Picker$MediaType;->VIDEO:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    invoke-interface {v7, v3}, Lcom/miui/gallery/picker/helper/Picker;->setMediaType(Lcom/miui/gallery/picker/helper/Picker$MediaType;)V

    goto :goto_1

    :cond_8
    sget-object v3, Lcom/miui/gallery/picker/helper/Picker$MediaType;->ALL:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    invoke-interface {v7, v3}, Lcom/miui/gallery/picker/helper/Picker;->setMediaType(Lcom/miui/gallery/picker/helper/Picker$MediaType;)V

    :goto_1
    const-string v3, "pick-need-id"

    invoke-virtual {v0, v3, v6}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v3

    if-eqz v3, :cond_9

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$ResultType;->ID:Lcom/miui/gallery/picker/helper/Picker$ResultType;

    invoke-interface {v7, v1}, Lcom/miui/gallery/picker/helper/Picker;->setResultType(Lcom/miui/gallery/picker/helper/Picker$ResultType;)V

    goto :goto_3

    :cond_9
    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerActivity;->getCallingPackage()Ljava/lang/String;

    move-result-object v3

    invoke-static {p0, v3}, Lcom/miui/gallery/provider/GalleryOpenProvider;->needReturnContentUri(Landroid/content/Context;Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_a

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$ResultType;->OPEN_URI:Lcom/miui/gallery/picker/helper/Picker$ResultType;

    invoke-interface {v7, v1}, Lcom/miui/gallery/picker/helper/Picker;->setResultType(Lcom/miui/gallery/picker/helper/Picker$ResultType;)V

    goto :goto_3

    :cond_a
    invoke-virtual {v8, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_c

    invoke-virtual {v5, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_b

    goto :goto_2

    :cond_b
    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$ResultType;->LEGACY_GENERAL:Lcom/miui/gallery/picker/helper/Picker$ResultType;

    invoke-interface {v7, v1}, Lcom/miui/gallery/picker/helper/Picker;->setResultType(Lcom/miui/gallery/picker/helper/Picker$ResultType;)V

    goto :goto_3

    :cond_c
    :goto_2
    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$ResultType;->LEGACY_MEDIA:Lcom/miui/gallery/picker/helper/Picker$ResultType;

    invoke-interface {v7, v1}, Lcom/miui/gallery/picker/helper/Picker;->setResultType(Lcom/miui/gallery/picker/helper/Picker$ResultType;)V

    :goto_3
    const-string v1, "pick-need-origin"

    invoke-virtual {v0, v1, v6}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v1

    if-eqz v1, :cond_d

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$ImageType;->ORIGIN:Lcom/miui/gallery/picker/helper/Picker$ImageType;

    invoke-interface {v7, v1}, Lcom/miui/gallery/picker/helper/Picker;->setImageType(Lcom/miui/gallery/picker/helper/Picker$ImageType;)V

    goto :goto_4

    :cond_d
    const-string v1, "pick-need-origin-download-info"

    invoke-virtual {v0, v1, v6}, Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z

    move-result v1

    if-eqz v1, :cond_e

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$ImageType;->ORIGIN_OR_DOWNLOAD_INFO:Lcom/miui/gallery/picker/helper/Picker$ImageType;

    invoke-interface {v7, v1}, Lcom/miui/gallery/picker/helper/Picker;->setImageType(Lcom/miui/gallery/picker/helper/Picker$ImageType;)V

    :cond_e
    :goto_4
    const-string v1, "extra_filter_media_type"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->hasExtra(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_f

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringArrayExtra(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v0

    invoke-interface {v7, v0}, Lcom/miui/gallery/picker/helper/Picker;->setFilterMimeTypes([Ljava/lang/String;)V

    :cond_f
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "creating picker, capacity is %d"

    invoke-static {v4, v1, v0}, Lcom/miui/gallery/util/Log;->d(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    return-object v7
.end method

.method protected abstract onDone()V
.end method

.method protected onSaveInstanceState(Landroid/os/Bundle;)V
    .locals 2

    invoke-super {p0, p1}, Lcom/miui/gallery/picker/PickerCompatActivity;->onSaveInstanceState(Landroid/os/Bundle;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-static {v0}, Lcom/miui/gallery/picker/PickerActivity;->copyPicker(Lcom/miui/gallery/picker/helper/Picker;)Ljava/util/HashSet;

    move-result-object v0

    const-string v1, "com.miui.gallery.picker.PickerActivity.results"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putSerializable(Ljava/lang/String;Ljava/io/Serializable;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0}, Lcom/miui/gallery/picker/helper/Picker;->getMode()Lcom/miui/gallery/picker/helper/Picker$Mode;

    move-result-object v0

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$Mode;->UNLIMITED:Lcom/miui/gallery/picker/helper/Picker$Mode;

    if-ne v0, v1, :cond_0

    const/4 v0, -0x1

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0}, Lcom/miui/gallery/picker/helper/Picker;->capacity()I

    move-result v0

    :goto_0
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "com.miui.gallery.picker.PickerActivity.capacity"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putSerializable(Ljava/lang/String;Ljava/io/Serializable;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0}, Lcom/miui/gallery/picker/helper/Picker;->baseline()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    const-string v1, "com.miui.gallery.picker.PickerActivity.baseline"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putSerializable(Ljava/lang/String;Ljava/io/Serializable;)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0}, Lcom/miui/gallery/picker/helper/Picker;->getMediaType()Lcom/miui/gallery/picker/helper/Picker$MediaType;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/picker/helper/Picker$MediaType;->ordinal()I

    move-result v0

    const-string v1, "com.miui.gallery.picker.PickerActivity.media_type"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0}, Lcom/miui/gallery/picker/helper/Picker;->getResultType()Lcom/miui/gallery/picker/helper/Picker$ResultType;

    move-result-object v0

    invoke-virtual {v0}, Lcom/miui/gallery/picker/helper/Picker$ResultType;->ordinal()I

    move-result v0

    const-string v1, "com.miui.gallery.picker.PickerActivity.result_type"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    return-void
.end method

.method public final setPickerTitle(Ljava/lang/CharSequence;)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity;->mPickerTitle:Ljava/lang/CharSequence;

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    invoke-virtual {v0, p1}, Lcom/miui/gallery/picker/PickerActivity$Decor;->setPickerTitle(Ljava/lang/CharSequence;)V

    return-void
.end method

.method public final setTitle(Ljava/lang/CharSequence;)V
    .locals 1

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerActivity;->mTitle:Ljava/lang/CharSequence;

    iget-object p1, p0, Lcom/miui/gallery/picker/PickerActivity;->mDecor:Lcom/miui/gallery/picker/PickerActivity$Decor;

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mTitle:Ljava/lang/CharSequence;

    invoke-virtual {p1, v0}, Lcom/miui/gallery/picker/PickerActivity$Decor;->setTitle(Ljava/lang/CharSequence;)V

    return-void
.end method

.method protected final updateTitle()V
    .locals 7

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0}, Lcom/miui/gallery/picker/helper/Picker;->count()I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v1}, Lcom/miui/gallery/picker/helper/Picker;->getMode()Lcom/miui/gallery/picker/helper/Picker$Mode;

    move-result-object v1

    const/4 v2, 0x2

    const/4 v3, 0x0

    const/4 v4, 0x1

    if-lez v0, :cond_1

    sget-object v5, Lcom/miui/gallery/picker/helper/Picker$Mode;->MULTIPLE:Lcom/miui/gallery/picker/helper/Picker$Mode;

    if-ne v1, v5, :cond_0

    const v1, 0x7f1006c8

    const/4 v5, 0x3

    new-array v5, v5, [Ljava/lang/Object;

    iget-object v6, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v6}, Lcom/miui/gallery/picker/helper/Picker;->baseline()I

    move-result v6

    invoke-static {v6}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v6

    aput-object v6, v5, v3

    iget-object v3, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v3}, Lcom/miui/gallery/picker/helper/Picker;->capacity()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v5, v4

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v5, v2

    invoke-virtual {p0, v1, v5}, Lcom/miui/gallery/picker/PickerActivity;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_0
    const v1, 0x7f1006c7

    new-array v2, v4, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    aput-object v0, v2, v3

    invoke-virtual {p0, v1, v2}, Lcom/miui/gallery/picker/PickerActivity;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    :goto_0
    invoke-virtual {p0, v0}, Lcom/miui/gallery/picker/PickerActivity;->setPickerTitle(Ljava/lang/CharSequence;)V

    goto/16 :goto_1

    :cond_1
    sget-object v0, Lcom/miui/gallery/picker/helper/Picker$Mode;->MULTIPLE:Lcom/miui/gallery/picker/helper/Picker$Mode;

    if-ne v1, v0, :cond_3

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v0}, Lcom/miui/gallery/picker/helper/Picker;->baseline()I

    move-result v0

    iget-object v1, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v1}, Lcom/miui/gallery/picker/helper/Picker;->capacity()I

    move-result v1

    if-eq v0, v1, :cond_2

    const v0, 0x7f1006c6

    new-array v1, v2, [Ljava/lang/Object;

    iget-object v2, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v2}, Lcom/miui/gallery/picker/helper/Picker;->baseline()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v1, v3

    iget-object v2, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v2}, Lcom/miui/gallery/picker/helper/Picker;->capacity()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v1, v4

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/picker/PickerActivity;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/picker/PickerActivity;->setPickerTitle(Ljava/lang/CharSequence;)V

    goto :goto_1

    :cond_2
    const v0, 0x7f1006c9

    new-array v1, v4, [Ljava/lang/Object;

    iget-object v2, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v2}, Lcom/miui/gallery/picker/helper/Picker;->baseline()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v1, v3

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/picker/PickerActivity;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/picker/PickerActivity;->setPickerTitle(Ljava/lang/CharSequence;)V

    goto :goto_1

    :cond_3
    sget-object v0, Lcom/miui/gallery/picker/helper/Picker$Mode;->SINGLE:Lcom/miui/gallery/picker/helper/Picker$Mode;

    if-ne v1, v0, :cond_4

    const v0, 0x7f1006ca

    new-array v1, v4, [Ljava/lang/Object;

    iget-object v2, p0, Lcom/miui/gallery/picker/PickerActivity;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    invoke-interface {v2}, Lcom/miui/gallery/picker/helper/Picker;->capacity()I

    move-result v2

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    aput-object v2, v1, v3

    invoke-virtual {p0, v0, v1}, Lcom/miui/gallery/picker/PickerActivity;->getString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/picker/PickerActivity;->setPickerTitle(Ljava/lang/CharSequence;)V

    goto :goto_1

    :cond_4
    const v0, 0x7f1006cb

    invoke-virtual {p0, v0}, Lcom/miui/gallery/picker/PickerActivity;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/miui/gallery/picker/PickerActivity;->setPickerTitle(Ljava/lang/CharSequence;)V

    :goto_1
    return-void
.end method
