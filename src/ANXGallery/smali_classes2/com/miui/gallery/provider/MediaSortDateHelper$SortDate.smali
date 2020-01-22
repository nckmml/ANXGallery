.class public final enum Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;
.super Ljava/lang/Enum;
.source "MediaSortDateHelper.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/provider/MediaSortDateHelper;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "SortDate"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

.field public static final enum CREATE_TIME:Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

.field public static final enum MODIFY_TIME:Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    const/4 v1, 0x0

    const-string v2, "CREATE_TIME"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->CREATE_TIME:Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    new-instance v0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    const/4 v2, 0x1

    const-string v3, "MODIFY_TIME"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->MODIFY_TIME:Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    const/4 v0, 0x2

    new-array v0, v0, [Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    sget-object v3, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->CREATE_TIME:Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    aput-object v3, v0, v1

    sget-object v1, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->MODIFY_TIME:Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->$VALUES:[Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;
    .locals 1

    const-class v0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;
    .locals 1

    sget-object v0, Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->$VALUES:[Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    invoke-virtual {v0}, [Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/provider/MediaSortDateHelper$SortDate;

    return-object v0
.end method
