.class public final synthetic Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/google/common/base/Predicate;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;

    invoke-direct {v0}, Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;-><init>()V

    sput-object v0, Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;->INSTANCE:Lcom/miui/gallery/model/-$$Lambda$ImageType$5iyzyhlKTDWZ2A1fdVgB80qgGRA;

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

    invoke-static {p1}, Lcom/miui/gallery/model/ImageType;->lambda$static$1(Lcom/miui/gallery/model/ImageType$ImageSize;)Z

    move-result p1

    return p1
.end method
