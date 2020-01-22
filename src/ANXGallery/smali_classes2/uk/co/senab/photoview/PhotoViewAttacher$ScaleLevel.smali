.class public final enum Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;
.super Ljava/lang/Enum;
.source "PhotoViewAttacher.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Luk/co/senab/photoview/PhotoViewAttacher;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "ScaleLevel"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

.field public static final enum HIGH:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

.field public static final enum LOW:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

.field public static final enum MIDDLE:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    new-instance v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    const/4 v1, 0x0

    const-string v2, "LOW"

    invoke-direct {v0, v2, v1}, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;-><init>(Ljava/lang/String;I)V

    sput-object v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->LOW:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    new-instance v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    const/4 v2, 0x1

    const-string v3, "MIDDLE"

    invoke-direct {v0, v3, v2}, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;-><init>(Ljava/lang/String;I)V

    sput-object v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->MIDDLE:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    new-instance v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    const/4 v3, 0x2

    const-string v4, "HIGH"

    invoke-direct {v0, v4, v3}, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;-><init>(Ljava/lang/String;I)V

    sput-object v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->HIGH:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    const/4 v0, 0x3

    new-array v0, v0, [Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    sget-object v4, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->LOW:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    aput-object v4, v0, v1

    sget-object v1, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->MIDDLE:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    aput-object v1, v0, v2

    sget-object v1, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->HIGH:Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    aput-object v1, v0, v3

    sput-object v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->$VALUES:[Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

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

.method public static valueOf(Ljava/lang/String;)Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;
    .locals 1

    const-class v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    return-object p0
.end method

.method public static values()[Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;
    .locals 1

    sget-object v0, Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->$VALUES:[Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    invoke-virtual {v0}, [Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Luk/co/senab/photoview/PhotoViewAttacher$ScaleLevel;

    return-object v0
.end method
