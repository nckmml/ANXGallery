.class public final enum Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;
.super Ljava/lang/Enum;
.source "Downloader.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/picker/uri/Downloader$DownloadResult;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "State"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

.field public static final enum CANCELLED:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

.field public static final enum FAILED:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

.field public static final enum PENDING:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

.field public static final enum SUCCESS:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    new-instance v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    const/4 v1, 0x0

    const-string v2, "PENDING"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->PENDING:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    new-instance v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    const/4 v2, 0x1

    const-string v3, "SUCCESS"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->SUCCESS:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    new-instance v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    const/4 v3, 0x2

    const-string v4, "FAILED"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->FAILED:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    new-instance v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    const/4 v4, 0x3

    const-string v5, "CANCELLED"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->CANCELLED:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    const/4 v0, 0x4

    new-array v0, v0, [Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    sget-object v5, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->PENDING:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    aput-object v5, v0, v1

    sget-object v1, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->SUCCESS:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->FAILED:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->CANCELLED:Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    aput-object v1, v0, v4

    sput-object v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->$VALUES:[Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;
    .locals 1

    const-class v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;
    .locals 1

    sget-object v0, Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->$VALUES:[Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    invoke-virtual {v0}, [Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/picker/uri/Downloader$DownloadResult$State;

    return-object v0
.end method
