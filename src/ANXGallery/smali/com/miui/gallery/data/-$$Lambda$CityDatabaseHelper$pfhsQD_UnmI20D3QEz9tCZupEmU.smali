.class public final synthetic Lcom/miui/gallery/data/-$$Lambda$CityDatabaseHelper$pfhsQD_UnmI20D3QEz9tCZupEmU;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/data/-$$Lambda$CityDatabaseHelper$pfhsQD_UnmI20D3QEz9tCZupEmU;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/data/-$$Lambda$CityDatabaseHelper$pfhsQD_UnmI20D3QEz9tCZupEmU;

    invoke-direct {v0}, Lcom/miui/gallery/data/-$$Lambda$CityDatabaseHelper$pfhsQD_UnmI20D3QEz9tCZupEmU;-><init>()V

    sput-object v0, Lcom/miui/gallery/data/-$$Lambda$CityDatabaseHelper$pfhsQD_UnmI20D3QEz9tCZupEmU;->INSTANCE:Lcom/miui/gallery/data/-$$Lambda$CityDatabaseHelper$pfhsQD_UnmI20D3QEz9tCZupEmU;

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

    invoke-static {p1}, Lcom/miui/gallery/data/CityDatabaseHelper;->lambda$queryCityBoundary$23(Landroid/database/Cursor;)Ljava/util/concurrent/ConcurrentMap;

    move-result-object p1

    return-object p1
.end method
