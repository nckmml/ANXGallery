.class public final synthetic Lcom/miui/gallery/util/-$$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/threadpool/ThreadPool$Job;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/util/-$$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/util/-$$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw;

    invoke-direct {v0}, Lcom/miui/gallery/util/-$$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw;-><init>()V

    sput-object v0, Lcom/miui/gallery/util/-$$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw;->INSTANCE:Lcom/miui/gallery/util/-$$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final run(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;
    .locals 0

    invoke-static {p1}, Lcom/miui/gallery/util/DebugUtil;->lambda$exportDB$22(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;)Ljava/lang/Object;

    move-result-object p1

    return-object p1
.end method
