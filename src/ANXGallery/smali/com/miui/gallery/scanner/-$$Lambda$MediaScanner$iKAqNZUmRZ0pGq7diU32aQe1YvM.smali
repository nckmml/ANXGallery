.class public final synthetic Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lcom/miui/gallery/util/SafeDBUtil$QueryHandler;


# instance fields
.field private final synthetic f$0:Lcom/miui/gallery/threadpool/ThreadPool$JobContext;

.field private final synthetic f$1:Lcom/miui/gallery/scanner/CloudMediaBulkDeleter;

.field private final synthetic f$2:Landroid/content/Context;

.field private final synthetic f$3:Z

.field private final synthetic f$4:Lcom/miui/gallery/provider/ContentProviderBatchOperator;


# direct methods
.method public synthetic constructor <init>(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;Lcom/miui/gallery/scanner/CloudMediaBulkDeleter;Landroid/content/Context;ZLcom/miui/gallery/provider/ContentProviderBatchOperator;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$0:Lcom/miui/gallery/threadpool/ThreadPool$JobContext;

    iput-object p2, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$1:Lcom/miui/gallery/scanner/CloudMediaBulkDeleter;

    iput-object p3, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$2:Landroid/content/Context;

    iput-boolean p4, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$3:Z

    iput-object p5, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$4:Lcom/miui/gallery/provider/ContentProviderBatchOperator;

    return-void
.end method


# virtual methods
.method public final handle(Landroid/database/Cursor;)Ljava/lang/Object;
    .locals 6

    iget-object v0, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$0:Lcom/miui/gallery/threadpool/ThreadPool$JobContext;

    iget-object v1, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$1:Lcom/miui/gallery/scanner/CloudMediaBulkDeleter;

    iget-object v2, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$2:Landroid/content/Context;

    iget-boolean v3, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$3:Z

    iget-object v4, p0, Lcom/miui/gallery/scanner/-$$Lambda$MediaScanner$iKAqNZUmRZ0pGq7diU32aQe1YvM;->f$4:Lcom/miui/gallery/provider/ContentProviderBatchOperator;

    move-object v5, p1

    invoke-static/range {v0 .. v5}, Lcom/miui/gallery/scanner/MediaScanner;->lambda$cleanup$50(Lcom/miui/gallery/threadpool/ThreadPool$JobContext;Lcom/miui/gallery/scanner/CloudMediaBulkDeleter;Landroid/content/Context;ZLcom/miui/gallery/provider/ContentProviderBatchOperator;Landroid/database/Cursor;)Ljava/lang/Integer;

    move-result-object p1

    return-object p1
.end method
