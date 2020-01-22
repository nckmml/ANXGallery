.class Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;
.super Ljava/lang/Object;
.source "NoRepeatContentObserver.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/ui/album/NoRepeatContentObserver;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "MyContentSubscribeBean"
.end annotation


# instance fields
.field private isSelfChange:Z

.field private mUri:Landroid/net/Uri;


# direct methods
.method private constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method synthetic constructor <init>(Lcom/miui/gallery/ui/album/NoRepeatContentObserver$1;)V
    .locals 0

    invoke-direct {p0}, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;-><init>()V

    return-void
.end method

.method static synthetic access$200(Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;->isSelfChange:Z

    return p0
.end method

.method static synthetic access$300(Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;)Landroid/net/Uri;
    .locals 0

    iget-object p0, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;->mUri:Landroid/net/Uri;

    return-object p0
.end method


# virtual methods
.method public setSelfChange(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;->isSelfChange:Z

    return-void
.end method

.method public setUri(Landroid/net/Uri;)V
    .locals 0

    iput-object p1, p0, Lcom/miui/gallery/ui/album/NoRepeatContentObserver$MyContentSubscribeBean;->mUri:Landroid/net/Uri;

    return-void
.end method
