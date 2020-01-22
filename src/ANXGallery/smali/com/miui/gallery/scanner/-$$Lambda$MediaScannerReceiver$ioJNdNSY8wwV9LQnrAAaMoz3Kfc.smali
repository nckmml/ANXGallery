.class public final synthetic Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$ioJNdNSY8wwV9LQnrAAaMoz3Kfc;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Predicate;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$ioJNdNSY8wwV9LQnrAAaMoz3Kfc;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$ioJNdNSY8wwV9LQnrAAaMoz3Kfc;

    invoke-direct {v0}, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$ioJNdNSY8wwV9LQnrAAaMoz3Kfc;-><init>()V

    sput-object v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$ioJNdNSY8wwV9LQnrAAaMoz3Kfc;->INSTANCE:Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$ioJNdNSY8wwV9LQnrAAaMoz3Kfc;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final test(Ljava/lang/Object;)Z
    .locals 0

    check-cast p1, Ljava/util/List;

    invoke-static {p1}, Lcom/miui/gallery/scanner/MediaScannerReceiver;->lambda$ensureSubscribed$25(Ljava/util/List;)Z

    move-result p1

    return p1
.end method
