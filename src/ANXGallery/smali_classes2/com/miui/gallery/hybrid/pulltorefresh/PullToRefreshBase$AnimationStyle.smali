.class public final enum Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;
.super Ljava/lang/Enum;
.source "PullToRefreshBase.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "AnimationStyle"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

.field public static final enum FLIP:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

.field public static final enum ROTATE:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    const/4 v1, 0x0

    const-string v2, "ROTATE"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->ROTATE:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    new-instance v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    const/4 v2, 0x1

    const-string v3, "FLIP"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->FLIP:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    const/4 v0, 0x2

    new-array v0, v0, [Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    sget-object v3, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->ROTATE:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    aput-object v3, v0, v1

    sget-object v1, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->FLIP:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->$VALUES:[Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method static getDefault()Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;
    .locals 1

    sget-object v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->ROTATE:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    return-object v0
.end method

.method static mapIntToValue(I)Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;
    .locals 1

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    sget-object p0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->ROTATE:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    return-object p0

    :cond_0
    sget-object p0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->FLIP:Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;
    .locals 1

    const-class v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;
    .locals 1

    sget-object v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->$VALUES:[Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    invoke-virtual {v0}, [Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;

    return-object v0
.end method


# virtual methods
.method createLoadingLayout(Landroid/content/Context;Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$Mode;Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$Orientation;Landroid/content/res/TypedArray;)Lcom/miui/gallery/hybrid/pulltorefresh/LoadingLayout;
    .locals 2

    sget-object v0, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$3;->$SwitchMap$com$miui$gallery$hybrid$pulltorefresh$PullToRefreshBase$AnimationStyle:[I

    invoke-virtual {p0}, Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$AnimationStyle;->ordinal()I

    move-result v1

    aget v0, v0, v1

    new-instance v0, Lcom/miui/gallery/hybrid/pulltorefresh/FlipLoadingLayout;

    invoke-direct {v0, p1, p2, p3, p4}, Lcom/miui/gallery/hybrid/pulltorefresh/FlipLoadingLayout;-><init>(Landroid/content/Context;Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$Mode;Lcom/miui/gallery/hybrid/pulltorefresh/PullToRefreshBase$Orientation;Landroid/content/res/TypedArray;)V

    return-object v0
.end method
