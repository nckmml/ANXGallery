.class Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;
.super Ljava/lang/Object;
.source "NumberPicker.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "SoundPlayerContainer"
.end annotation


# instance fields
.field private mContext:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Landroid/content/Context;",
            ">;"
        }
    .end annotation
.end field

.field private mPrevPlayTime:J

.field private mRefs:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private mSoundId:I

.field private mSoundPlayer:Landroid/media/SoundPool;


# direct methods
.method private constructor <init>(Landroid/content/Context;)V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Lcom/miui/widget/util/ArraySet;

    invoke-direct {v0}, Lcom/miui/widget/util/ArraySet;-><init>()V

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mRefs:Ljava/util/Set;

    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mContext:Ljava/lang/ref/WeakReference;

    return-void
.end method

.method public static newInstance(Landroid/content/Context;)Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;
    .locals 1

    new-instance v0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;

    invoke-direct {v0, p0}, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;-><init>(Landroid/content/Context;)V

    return-object v0
.end method


# virtual methods
.method init(I)V
    .locals 4

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mContext:Ljava/lang/ref/WeakReference;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundPlayer:Landroid/media/SoundPool;

    if-nez v0, :cond_1

    new-instance v0, Landroid/media/SoundPool;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-direct {v0, v2, v2, v1}, Landroid/media/SoundPool;-><init>(III)V

    iput-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundPlayer:Landroid/media/SoundPool;

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundPlayer:Landroid/media/SoundPool;

    iget-object v1, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mContext:Ljava/lang/ref/WeakReference;

    invoke-virtual {v1}, Ljava/lang/ref/WeakReference;->get()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/content/Context;

    sget v3, Lcom/miui/widget/R$raw;->numberpicker_value_change:I

    invoke-virtual {v0, v1, v3, v2}, Landroid/media/SoundPool;->load(Landroid/content/Context;II)I

    move-result v0

    iput v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundId:I

    :cond_1
    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mRefs:Ljava/util/Set;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    :cond_2
    :goto_0
    return-void
.end method

.method play()V
    .locals 9

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    iget-object v2, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundPlayer:Landroid/media/SoundPool;

    if-eqz v2, :cond_0

    iget-wide v3, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mPrevPlayTime:J

    sub-long v3, v0, v3

    const-wide/16 v5, 0x32

    cmp-long v3, v3, v5

    if-lez v3, :cond_0

    iget v3, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundId:I

    const/high16 v4, 0x3f800000    # 1.0f

    const/high16 v5, 0x3f800000    # 1.0f

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/high16 v8, 0x3f800000    # 1.0f

    invoke-virtual/range {v2 .. v8}, Landroid/media/SoundPool;->play(IFFIIF)I

    iput-wide v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mPrevPlayTime:J

    :cond_0
    return-void
.end method

.method release(I)V
    .locals 1

    iget-object v0, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mRefs:Ljava/util/Set;

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {v0, p1}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mRefs:Ljava/util/Set;

    invoke-interface {p1}, Ljava/util/Set;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_0

    iget-object p1, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundPlayer:Landroid/media/SoundPool;

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/media/SoundPool;->release()V

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/miui/widget/picker/NumberPicker$SoundPlayHandler$SoundPlayerContainer;->mSoundPlayer:Landroid/media/SoundPool;

    :cond_0
    return-void
.end method
