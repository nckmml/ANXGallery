.class public final synthetic Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/google/common/base/Predicate;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;

    invoke-direct {v0}, Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;-><init>()V

    sput-object v0, Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;->INSTANCE:Lcom/miui/gallery/model/-$$Lambda$ImageType$Kj6RoUy5LtufSMRZvOw6W2QWbiA;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final apply(Ljava/lang/Object;)Z
    .locals 0

    check-cast p1, Lcom/miui/gallery/model/ImageType$ImageSize;

    invoke-static {p1}, Lcom/miui/gallery/model/ImageType;->lambda$static$0(Lcom/miui/gallery/model/ImageType$ImageSize;)Z

    move-result p1

    return p1
.end method
