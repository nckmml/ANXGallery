.class public final enum Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;
.super Ljava/lang/Enum;
.source "ProcessingMetadataQuery.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/photosapi/ProcessingMetadataQuery;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "ProgressStatus"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

.field public static final enum DETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

.field public static final enum INDETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;


# instance fields
.field private final identifier:I


# direct methods
.method static constructor <clinit>()V
    .locals 5

    new-instance v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    const/4 v1, 0x0

    const/4 v2, 0x1

    const-string v3, "INDETERMINATE"

    invoke-direct {v0, v3, v1, v2}, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->INDETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    new-instance v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    const/4 v3, 0x2

    const-string v4, "DETERMINATE"

    invoke-direct {v0, v4, v2, v3}, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->DETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    new-array v0, v3, [Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    sget-object v3, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->INDETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    aput-object v3, v0, v1

    sget-object v1, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->DETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    aput-object v1, v0, v2

    sput-object v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->$VALUES:[Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;II)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I)V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput p3, p0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->identifier:I

    return-void
.end method

.method public static fromIdentifier(I)Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;
    .locals 1

    sget-object v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->DETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    invoke-virtual {v0}, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->getIdentifier()I

    move-result v0

    if-ne p0, v0, :cond_0

    sget-object p0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->DETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    return-object p0

    :cond_0
    sget-object p0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->INDETERMINATE:Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;
    .locals 1

    const-class v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;
    .locals 1

    sget-object v0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->$VALUES:[Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    invoke-virtual {v0}, [Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;

    return-object v0
.end method


# virtual methods
.method public getIdentifier()I
    .locals 1

    iget v0, p0, Lcom/miui/gallery/photosapi/ProcessingMetadataQuery$ProgressStatus;->identifier:I

    return v0
.end method
