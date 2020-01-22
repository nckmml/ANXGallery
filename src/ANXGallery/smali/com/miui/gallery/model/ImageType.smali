.class public Lcom/miui/gallery/model/ImageType;
.super Ljava/lang/Object;
.source "ImageType.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/miui/gallery/model/ImageType$ImageSize;
    }
.end annotation


# static fields
.field public static final HIGH_RESOLUTION:Lcom/miui/gallery/model/ImageType;

.field public static final HIGH_RESOLUTION_108M:Lcom/miui/gallery/model/ImageType;

.field private static final HR_108MP_SUPPORTED_DEVICES:[Ljava/lang/String;

.field public static final NORMAL:Lcom/miui/gallery/model/ImageType;

.field private static sIsHR108MPSupportedDevice:Z


# instance fields
.field private mIsHighResolution:Z

.field private mIsNavMapSupported:Z

.field private mName:Ljava/lang/String;

.field private mOrdinal:I

.field private mPredicate:Lcom/google/common/base/Predicate;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lcom/google/common/base/Predicate<",
            "Lcom/miui/gallery/model/ImageType$ImageSize;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 15

    const-string v0, "tucana"

    const-string v1, "cmi"

    const-string v2, "umi"

    const-string v3, "draco"

    filled-new-array {v0, v1, v2, v3}, [Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/model/ImageType;->HR_108MP_SUPPORTED_DEVICES:[Ljava/lang/String;

    const/4 v0, 0x0

    sput-boolean v0, Lcom/miui/gallery/model/ImageType;->sIsHR108MPSupportedDevice:Z

    sget-object v1, Lcom/miui/gallery/model/ImageType;->HR_108MP_SUPPORTED_DEVICES:[Ljava/lang/String;

    array-length v2, v1

    :goto_0
    if-ge v0, v2, :cond_1

    aget-object v3, v1, v0

    sget-object v4, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_0

    const/4 v0, 0x1

    sput-boolean v0, Lcom/miui/gallery/model/ImageType;->sIsHR108MPSupportedDevice:Z

    goto :goto_1

    :cond_0
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    :goto_1
    new-instance v0, Lcom/miui/gallery/model/ImageType;

    const/4 v5, 0x0

    const/4 v6, 0x1

    const/4 v7, 0x1

    sget-object v8, Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;->INSTANCE:Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;

    const-string v4, "HIGH_RESOLUTION_108MP"

    move-object v3, v0

    invoke-direct/range {v3 .. v8}, Lcom/miui/gallery/model/ImageType;-><init>(Ljava/lang/String;IZZLcom/google/common/base/Predicate;)V

    sput-object v0, Lcom/miui/gallery/model/ImageType;->HIGH_RESOLUTION_108M:Lcom/miui/gallery/model/ImageType;

    new-instance v0, Lcom/miui/gallery/model/ImageType;

    const/4 v11, 0x1

    const/4 v12, 0x1

    const/4 v13, 0x0

    sget-object v14, Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;->INSTANCE:Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;

    const-string v10, "HIGH_RESOLUTION_AT_LEAST_24MP"

    move-object v9, v0

    invoke-direct/range {v9 .. v14}, Lcom/miui/gallery/model/ImageType;-><init>(Ljava/lang/String;IZZLcom/google/common/base/Predicate;)V

    sput-object v0, Lcom/miui/gallery/model/ImageType;->HIGH_RESOLUTION:Lcom/miui/gallery/model/ImageType;

    new-instance v0, Lcom/miui/gallery/model/ImageType;

    const/4 v3, 0x2

    const/4 v4, 0x0

    invoke-static {}, Lcom/google/common/base/Predicates;->alwaysTrue()Lcom/google/common/base/Predicate;

    move-result-object v6

    const-string v2, "NORMAL"

    move-object v1, v0

    invoke-direct/range {v1 .. v6}, Lcom/miui/gallery/model/ImageType;-><init>(Ljava/lang/String;IZZLcom/google/common/base/Predicate;)V

    sput-object v0, Lcom/miui/gallery/model/ImageType;->NORMAL:Lcom/miui/gallery/model/ImageType;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;IZZLcom/google/common/base/Predicate;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "IZZ",
            "Lcom/google/common/base/Predicate<",
            "Lcom/miui/gallery/model/ImageType$ImageSize;",
            ">;)V"
        }
    .end annotation

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p2, p0, Lcom/miui/gallery/model/ImageType;->mOrdinal:I

    iput-object p1, p0, Lcom/miui/gallery/model/ImageType;->mName:Ljava/lang/String;

    iput-boolean p3, p0, Lcom/miui/gallery/model/ImageType;->mIsHighResolution:Z

    iput-boolean p4, p0, Lcom/miui/gallery/model/ImageType;->mIsNavMapSupported:Z

    iput-object p5, p0, Lcom/miui/gallery/model/ImageType;->mPredicate:Lcom/google/common/base/Predicate;

    return-void
.end method

.method static synthetic lambda$static$0(Lcom/miui/gallery/model/ImageType$ImageSize;)Z
    .locals 3

    sget-boolean v0, Lcom/miui/gallery/model/ImageType;->sIsHR108MPSupportedDevice:Z

    if-eqz v0, :cond_0

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getHeight()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    const/16 v1, 0x2ee0

    const/16 v2, 0x64

    invoke-static {v0, v1, v2}, Lcom/miui/gallery/util/MiscUtil;->rangeIn(III)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getHeight()I

    move-result p0

    invoke-static {v0, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    const/16 v0, 0x2328

    invoke-static {p0, v0, v2}, Lcom/miui/gallery/util/MiscUtil;->rangeIn(III)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method static synthetic lambda$static$1(Lcom/miui/gallery/model/ImageType$ImageSize;)Z
    .locals 2

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getHeight()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->max(II)I

    move-result v0

    const/16 v1, 0x1770

    if-lt v0, v1, :cond_0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Lcom/miui/gallery/model/ImageType$ImageSize;->getHeight()I

    move-result p0

    invoke-static {v0, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    const/16 v0, 0xfa0

    if-lt p0, v0, :cond_0

    const/4 p0, 0x1

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    return p0
.end method

.method public static of(II)Lcom/miui/gallery/model/ImageType;
    .locals 6

    invoke-static {}, Lcom/miui/gallery/model/ImageType;->values()[Lcom/miui/gallery/model/ImageType;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    :goto_0
    if-ge v2, v1, :cond_1

    aget-object v3, v0, v2

    iget-object v4, v3, Lcom/miui/gallery/model/ImageType;->mPredicate:Lcom/google/common/base/Predicate;

    new-instance v5, Lcom/miui/gallery/model/ImageType$ImageSize;

    invoke-direct {v5, p0, p1}, Lcom/miui/gallery/model/ImageType$ImageSize;-><init>(II)V

    invoke-interface {v4, v5}, Lcom/google/common/base/Predicate;->apply(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_0

    return-object v3

    :cond_0
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_1
    sget-object p0, Lcom/miui/gallery/model/ImageType;->NORMAL:Lcom/miui/gallery/model/ImageType;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/model/ImageType;
    .locals 3

    const/4 v0, 0x3

    new-array v0, v0, [Lcom/miui/gallery/model/ImageType;

    sget-object v1, Lcom/miui/gallery/model/ImageType;->HIGH_RESOLUTION_108M:Lcom/miui/gallery/model/ImageType;

    const/4 v2, 0x0

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/model/ImageType;->HIGH_RESOLUTION:Lcom/miui/gallery/model/ImageType;

    const/4 v2, 0x1

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/model/ImageType;->NORMAL:Lcom/miui/gallery/model/ImageType;

    const/4 v2, 0x2

    aput-object v1, v0, v2

    return-object v0
.end method


# virtual methods
.method public isHighResolution()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/model/ImageType;->mIsHighResolution:Z

    return v0
.end method

.method public isNavMapSupported()Z
    .locals 1

    iget-boolean v0, p0, Lcom/miui/gallery/model/ImageType;->mIsNavMapSupported:Z

    return v0
.end method

.method public name()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/model/ImageType;->mName:Ljava/lang/String;

    return-object v0
.end method

.method public final toString()Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "ImageType{mOrdinal="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lcom/miui/gallery/model/ImageType;->mOrdinal:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", mName=\'"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/miui/gallery/model/ImageType;->mName:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v1, 0x27

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    const-string v1, ", mIsHighResolution="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, p0, Lcom/miui/gallery/model/ImageType;->mIsHighResolution:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", mIsNavMapSupported="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v1, p0, Lcom/miui/gallery/model/ImageType;->mIsNavMapSupported:Z

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const/16 v1, 0x7d

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
