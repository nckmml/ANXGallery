.class public final synthetic Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$gWbG1skUhQNZbqTso114jJF9FMk;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Consumer;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$gWbG1skUhQNZbqTso114jJF9FMk;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$gWbG1skUhQNZbqTso114jJF9FMk;

    invoke-direct {v0}, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$gWbG1skUhQNZbqTso114jJF9FMk;-><init>()V

    sput-object v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$gWbG1skUhQNZbqTso114jJF9FMk;->INSTANCE:Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerReceiver$gWbG1skUhQNZbqTso114jJF9FMk;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final accept(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/util/List;

    invoke-static {p1}, Lcom/miui/gallery/scanner/MediaScannerReceiver;->lambda$ensureSubscribed$27(Ljava/util/List;)V

    return-void
.end method
