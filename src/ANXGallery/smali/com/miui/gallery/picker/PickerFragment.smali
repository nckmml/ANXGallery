.class public abstract Lcom/miui/gallery/picker/PickerFragment;
.super Lcom/miui/gallery/picker/PickerCompatFragment;
.source "PickerFragment.java"


# instance fields
.field protected mPicker:Lcom/miui/gallery/picker/helper/Picker;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/picker/PickerCompatFragment;-><init>()V

    return-void
.end method


# virtual methods
.method public final attach(Lcom/miui/gallery/picker/helper/Picker;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/picker/PickerFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    return-void
.end method

.method protected getFileLength(Landroid/database/Cursor;)J
    .locals 2

    invoke-static {p1}, Lcom/miui/gallery/picker/helper/CursorUtils;->getFileLength(Landroid/database/Cursor;)J

    move-result-wide v0

    return-wide v0
.end method

.method protected getFilterSelectionWithMimeType([Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    invoke-static {p1}, Lcom/miui/gallery/util/StringUtils;->isValid([Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string p1, ""

    return-object p1

    :cond_0
    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/Object;

    const/4 v1, 0x0

    const-string v2, "mimeType"

    aput-object v2, v0, v1

    const/4 v1, 0x1

    const-string v2, "\',\'"

    invoke-static {v2, p1}, Landroid/text/TextUtils;->join(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    aput-object p1, v0, v1

    const-string p1, "%s NOT IN (\'%s\')"

    invoke-static {p1, v0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method protected getGridViewOnItemClickListener()Landroid/widget/AdapterView$OnItemClickListener;
    .locals 1

    new-instance v0, Lcom/miui/gallery/picker/PickerFragment$1;

    invoke-direct {v0, p0}, Lcom/miui/gallery/picker/PickerFragment$1;-><init>(Lcom/miui/gallery/picker/PickerFragment;)V

    return-object v0
.end method

.method protected getKey(Landroid/database/Cursor;)J
    .locals 2

    invoke-static {p1}, Lcom/miui/gallery/picker/helper/CursorUtils;->getId(Landroid/database/Cursor;)J

    move-result-wide v0

    return-wide v0
.end method

.method protected getLocalPath(Landroid/database/Cursor;)Ljava/lang/String;
    .locals 0

    invoke-static {p1}, Lcom/miui/gallery/picker/helper/CursorUtils;->getMicroPath(Landroid/database/Cursor;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public final getPicker()Lcom/miui/gallery/picker/helper/Picker;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/picker/PickerFragment;->mPicker:Lcom/miui/gallery/picker/helper/Picker;

    return-object v0
.end method

.method protected getPreviewOrder()Ljava/lang/String;
    .locals 1

    const/4 v0, 0x0

    return-object v0
.end method

.method protected getPreviewSelection(Landroid/database/Cursor;)Ljava/lang/String;
    .locals 4

    sget-object v0, Ljava/util/Locale;->US:Ljava/util/Locale;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    invoke-static {p1}, Lcom/miui/gallery/picker/helper/CursorUtils;->getId(Landroid/database/Cursor;)J

    move-result-wide v2

    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p1

    const/4 v2, 0x0

    aput-object p1, v1, v2

    const-string p1, "_id = %d"

    invoke-static {v0, p1, v1}, Ljava/lang/String;->format(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method protected getPreviewSelectionArgs(Landroid/database/Cursor;)[Ljava/lang/String;
    .locals 0

    const/4 p1, 0x0

    return-object p1
.end method

.method protected getPreviewUri()Landroid/net/Uri;
    .locals 1

    invoke-virtual {p0}, Lcom/miui/gallery/picker/PickerFragment;->getUri()Landroid/net/Uri;

    move-result-object v0

    return-object v0
.end method

.method protected getSelection()Ljava/lang/String;
    .locals 1

    const-string v0, ""

    return-object v0
.end method

.method protected getSelectionArgs()[Ljava/lang/String;
    .locals 1

    const/4 v0, 0x0

    return-object v0
.end method

.method protected abstract getUri()Landroid/net/Uri;
.end method

.method protected isPreviewFace()Z
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method protected abstract onPhotoItemClick(Landroid/database/Cursor;)Z
.end method

.method protected abstract supportFoldBurstItems()Z
.end method
