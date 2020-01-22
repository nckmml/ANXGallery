.class public final synthetic Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieEditorMenuFragment$KfX9w9CcbgM2JKc0ZhGGZ_64ndk;
.super Ljava/lang/Object;
.source "lambda"

# interfaces
.implements Lio/reactivex/functions/Function;


# static fields
.field public static final synthetic INSTANCE:Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieEditorMenuFragment$KfX9w9CcbgM2JKc0ZhGGZ_64ndk;


# direct methods
.method static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieEditorMenuFragment$KfX9w9CcbgM2JKc0ZhGGZ_64ndk;

    invoke-direct {v0}, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieEditorMenuFragment$KfX9w9CcbgM2JKc0ZhGGZ_64ndk;-><init>()V

    sput-object v0, Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieEditorMenuFragment$KfX9w9CcbgM2JKc0ZhGGZ_64ndk;->INSTANCE:Lcom/miui/gallery/movie/ui/fragment/-$$Lambda$MovieEditorMenuFragment$KfX9w9CcbgM2JKc0ZhGGZ_64ndk;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final apply(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    check-cast p1, Lcom/miui/gallery/util/OptionalResult;

    invoke-static {p1}, Lcom/miui/gallery/movie/ui/fragment/MovieEditorMenuFragment;->lambda$loadTemplate$7(Lcom/miui/gallery/util/OptionalResult;)Ljava/util/List;

    move-result-object p1

    return-object p1
.end method
