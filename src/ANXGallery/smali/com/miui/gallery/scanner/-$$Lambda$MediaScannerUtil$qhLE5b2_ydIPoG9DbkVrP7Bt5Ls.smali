.class public final synthetic Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerUtil$qhLE5b2_ydIPoG9DbkVrP7Bt5Ls;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerUtil$qhLE5b2_ydIPoG9DbkVrP7Bt5Ls;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerUtil$qhLE5b2_ydIPoG9DbkVrP7Bt5Ls;

    invoke-direct {v0}, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerUtil$qhLE5b2_ydIPoG9DbkVrP7Bt5Ls;-><init>()V

    sput-object v0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerUtil$qhLE5b2_ydIPoG9DbkVrP7Bt5Ls;->INSTANCE:Lcom/miui/gallery/scanner/-$$Lambda$MediaScannerUtil$qhLE5b2_ydIPoG9DbkVrP7Bt5Ls;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final handle(Landroid/database/Cursor;)Ljava/lang/Object;
    .locals 0

    invoke-static {p1}, Lcom/miui/gallery/scanner/MediaScannerUtil;->lambda$scanMediaProvider$47(Landroid/database/Cursor;)Ljava/util/ArrayList;

    move-result-object p1

    return-object p1
.end method
