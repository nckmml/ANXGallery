.class public final enum Lcom/miui/gallery/picker/helper/Picker$MediaType;
.super Ljava/lang/Enum;
.source "Picker.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/picker/helper/Picker;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "MediaType"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/picker/helper/Picker$MediaType;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/picker/helper/Picker$MediaType;

.field public static final enum ALL:Lcom/miui/gallery/picker/helper/Picker$MediaType;

.field public static final enum IMAGE:Lcom/miui/gallery/picker/helper/Picker$MediaType;

.field public static final enum VIDEO:Lcom/miui/gallery/picker/helper/Picker$MediaType;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    new-instance v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;

    const/4 v1, 0x0

    const-string v2, "IMAGE"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/picker/helper/Picker$MediaType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;->IMAGE:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    new-instance v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;

    const/4 v2, 0x1

    const-string v3, "VIDEO"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/picker/helper/Picker$MediaType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;->VIDEO:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    new-instance v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;

    const/4 v3, 0x2

    const-string v4, "ALL"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/picker/helper/Picker$MediaType;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;->ALL:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    const/4 v0, 0x3

    new-array v0, v0, [Lcom/miui/gallery/picker/helper/Picker$MediaType;

    sget-object v4, Lcom/miui/gallery/picker/helper/Picker$MediaType;->IMAGE:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    aput-object v4, v0, v1

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$MediaType;->VIDEO:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/picker/helper/Picker$MediaType;->ALL:Lcom/miui/gallery/picker/helper/Picker$MediaType;

    aput-object v1, v0, v3

    sput-object v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;->$VALUES:[Lcom/miui/gallery/picker/helper/Picker$MediaType;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/picker/helper/Picker$MediaType;
    .locals 1

    const-class v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/picker/helper/Picker$MediaType;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/picker/helper/Picker$MediaType;
    .locals 1

    sget-object v0, Lcom/miui/gallery/picker/helper/Picker$MediaType;->$VALUES:[Lcom/miui/gallery/picker/helper/Picker$MediaType;

    invoke-virtual {v0}, [Lcom/miui/gallery/picker/helper/Picker$MediaType;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/picker/helper/Picker$MediaType;

    return-object v0
.end method
