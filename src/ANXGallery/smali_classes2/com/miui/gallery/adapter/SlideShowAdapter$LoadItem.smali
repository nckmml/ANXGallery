.class Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;
.super Ljava/lang/Object;
.source "SlideShowAdapter.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/adapter/SlideShowAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "LoadItem"
.end annotation


# instance fields
.field private mFileLength:J

.field private mIndex:I

.field private mSecretKey:[B

.field private mUri:Ljava/lang/String;

.field final synthetic this$0:Lcom/miui/gallery/adapter/SlideShowAdapter;


# direct methods
.method public constructor <init>(Lcom/miui/gallery/adapter/SlideShowAdapter;Ljava/lang/String;I[BJ)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->this$0:Lcom/miui/gallery/adapter/SlideShowAdapter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mUri:Ljava/lang/String;

    iput p3, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mIndex:I

    iput-object p4, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mSecretKey:[B

    iput-wide p5, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mFileLength:J

    return-void
.end method

.method static synthetic access$600(Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;)J
    .locals 2

    iget-wide v0, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mFileLength:J

    return-wide v0
.end method

.method static synthetic access$800(Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mUri:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$900(Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;)I
    .locals 0

    iget p0, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mIndex:I

    return p0
.end method


# virtual methods
.method public getSecretKey()[B
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mSecretKey:[B

    return-object v0
.end method

.method public isSecret()Z
    .locals 1

    iget-object v0, p0, Lcom/miui/gallery/adapter/SlideShowAdapter$LoadItem;->mSecretKey:[B

    if-eqz v0, :cond_0

    array-length v0, v0

    if-lez v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method
