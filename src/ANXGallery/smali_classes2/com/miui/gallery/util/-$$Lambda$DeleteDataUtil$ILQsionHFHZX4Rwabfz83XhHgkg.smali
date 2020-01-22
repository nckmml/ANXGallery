.class public final synthetic Lcom/miui/gallery/util/-$$Lambda$DeleteDataUtil$ILQsionHFHZX4Rwabfz83XhHgkg;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/util/GalleryUtils$QueryHandler;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/util/-$$Lambda$DeleteDataUtil$ILQsionHFHZX4Rwabfz83XhHgkg;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/util/-$$Lambda$DeleteDataUtil$ILQsionHFHZX4Rwabfz83XhHgkg;

    invoke-direct {v0}, Lcom/miui/gallery/util/-$$Lambda$DeleteDataUtil$ILQsionHFHZX4Rwabfz83XhHgkg;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/-$$Lambda$DeleteDataUtil$ILQsionHFHZX4Rwabfz83XhHgkg;->INSTANCE:Lcom/miui/gallery/util/-$$Lambda$DeleteDataUtil$ILQsionHFHZX4Rwabfz83XhHgkg;

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

    invoke-static {p1}, Lcom/miui/gallery/util/DeleteDataUtil;->lambda$deleteHiddenFiles$59(Landroid/database/Cursor;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method
