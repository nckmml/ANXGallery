.class public final enum Lcom/miui/gallery/util/MediaFileUtils$FileType;
.super Ljava/lang/Enum;
.source "MediaFileUtils.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/util/MediaFileUtils;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "FileType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/util/MediaFileUtils$FileType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum FOLDER:Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum MICRO_THUMBNAIL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum NONE:Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum NORMAL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum ORIGINAL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum TEMP:Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum THUMBNAIL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

.field public static final enum UBI_SUB_FILE:Lcom/miui/gallery/util/MediaFileUtils$FileType;


# direct methods
.method static constructor <clinit>()V
    .locals 10

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v1, 0x0

    const-string v2, "NONE"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->NONE:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v2, 0x1

    const-string v3, "NORMAL"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->NORMAL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v3, 0x2

    const-string v4, "ORIGINAL"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->ORIGINAL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v4, 0x3

    const-string v5, "THUMBNAIL"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->THUMBNAIL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v5, 0x4

    const-string v6, "MICRO_THUMBNAIL"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->MICRO_THUMBNAIL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v6, 0x5

    const-string v7, "UBI_SUB_FILE"

    invoke-direct {v0, v7, v6}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->UBI_SUB_FILE:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v7, 0x6

    const-string v8, "FOLDER"

    invoke-direct {v0, v8, v7}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->FOLDER:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    new-instance v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/4 v8, 0x7

    const-string v9, "TEMP"

    invoke-direct {v0, v9, v8}, Lcom/miui/gallery/util/MediaFileUtils$FileType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->TEMP:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    const/16 v0, 0x8

    new-array v0, v0, [Lcom/miui/gallery/util/MediaFileUtils$FileType;

    sget-object v9, Lcom/miui/gallery/util/MediaFileUtils$FileType;->NONE:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v9, v0, v1

    sget-object v1, Lcom/miui/gallery/util/MediaFileUtils$FileType;->NORMAL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/util/MediaFileUtils$FileType;->ORIGINAL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/util/MediaFileUtils$FileType;->THUMBNAIL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/util/MediaFileUtils$FileType;->MICRO_THUMBNAIL:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/util/MediaFileUtils$FileType;->UBI_SUB_FILE:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v1, v0, v6

    sget-object v1, Lcom/miui/gallery/util/MediaFileUtils$FileType;->FOLDER:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v1, v0, v7

    sget-object v1, Lcom/miui/gallery/util/MediaFileUtils$FileType;->TEMP:Lcom/miui/gallery/util/MediaFileUtils$FileType;

    aput-object v1, v0, v8

    sput-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->$VALUES:[Lcom/miui/gallery/util/MediaFileUtils$FileType;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/util/MediaFileUtils$FileType;
    .locals 1

    const-class v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/util/MediaFileUtils$FileType;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/util/MediaFileUtils$FileType;
    .locals 1

    sget-object v0, Lcom/miui/gallery/util/MediaFileUtils$FileType;->$VALUES:[Lcom/miui/gallery/util/MediaFileUtils$FileType;

    invoke-virtual {v0}, [Lcom/miui/gallery/util/MediaFileUtils$FileType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/util/MediaFileUtils$FileType;

    return-object v0
.end method
