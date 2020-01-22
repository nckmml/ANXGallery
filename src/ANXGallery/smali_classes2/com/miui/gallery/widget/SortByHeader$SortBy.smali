.class public final enum Lcom/miui/gallery/widget/SortByHeader$SortBy;
.super Ljava/lang/Enum;
.source "SortByHeader.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/widget/SortByHeader;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "SortBy"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/widget/SortByHeader$SortBy;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/widget/SortByHeader$SortBy;

.field public static final enum DATE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

.field public static final enum NAME:Lcom/miui/gallery/widget/SortByHeader$SortBy;

.field public static final enum NONE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

.field public static final enum SIZE:Lcom/miui/gallery/widget/SortByHeader$SortBy;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    new-instance v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;

    const/4 v1, 0x0

    const-string v2, "NONE"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/widget/SortByHeader$SortBy;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;->NONE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    new-instance v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;

    const/4 v2, 0x1

    const-string v3, "DATE"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/widget/SortByHeader$SortBy;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;->DATE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    new-instance v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;

    const/4 v3, 0x2

    const-string v4, "NAME"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/widget/SortByHeader$SortBy;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;->NAME:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    new-instance v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;

    const/4 v4, 0x3

    const-string v5, "SIZE"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/widget/SortByHeader$SortBy;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;->SIZE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    const/4 v0, 0x4

    new-array v0, v0, [Lcom/miui/gallery/widget/SortByHeader$SortBy;

    sget-object v5, Lcom/miui/gallery/widget/SortByHeader$SortBy;->NONE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    aput-object v5, v0, v1

    sget-object v1, Lcom/miui/gallery/widget/SortByHeader$SortBy;->DATE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/widget/SortByHeader$SortBy;->NAME:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/widget/SortByHeader$SortBy;->SIZE:Lcom/miui/gallery/widget/SortByHeader$SortBy;

    aput-object v1, v0, v4

    sput-object v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;->$VALUES:[Lcom/miui/gallery/widget/SortByHeader$SortBy;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/widget/SortByHeader$SortBy;
    .locals 1

    const-class v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/widget/SortByHeader$SortBy;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/widget/SortByHeader$SortBy;
    .locals 1

    sget-object v0, Lcom/miui/gallery/widget/SortByHeader$SortBy;->$VALUES:[Lcom/miui/gallery/widget/SortByHeader$SortBy;

    invoke-virtual {v0}, [Lcom/miui/gallery/widget/SortByHeader$SortBy;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/widget/SortByHeader$SortBy;

    return-object v0
.end method
