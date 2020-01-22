.class public final enum Lorg/keyczar/enums/KeyStatus;
.super Ljava/lang/Enum;
.source "KeyStatus.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/keyczar/enums/KeyStatus;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/keyczar/enums/KeyStatus;

.field public static final enum ACTIVE:Lorg/keyczar/enums/KeyStatus;

.field public static final enum INACTIVE:Lorg/keyczar/enums/KeyStatus;

.field public static final enum PRIMARY:Lorg/keyczar/enums/KeyStatus;


# instance fields
.field private name:Ljava/lang/String;

.field private value:I


# direct methods
.method static constructor <clinit>()V
    .locals 6

    new-instance v0, Lorg/keyczar/enums/KeyStatus;

    const/4 v1, 0x0

    const-string v2, "PRIMARY"

    const-string v3, "primary"

    invoke-direct {v0, v2, v1, v1, v3}, Lorg/keyczar/enums/KeyStatus;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyStatus;->PRIMARY:Lorg/keyczar/enums/KeyStatus;

    new-instance v0, Lorg/keyczar/enums/KeyStatus;

    const/4 v2, 0x1

    const-string v3, "ACTIVE"

    const-string v4, "active"

    invoke-direct {v0, v3, v2, v2, v4}, Lorg/keyczar/enums/KeyStatus;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyStatus;->ACTIVE:Lorg/keyczar/enums/KeyStatus;

    new-instance v0, Lorg/keyczar/enums/KeyStatus;

    const/4 v3, 0x2

    const-string v4, "INACTIVE"

    const-string v5, "inactive"

    invoke-direct {v0, v4, v3, v3, v5}, Lorg/keyczar/enums/KeyStatus;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyStatus;->INACTIVE:Lorg/keyczar/enums/KeyStatus;

    const/4 v0, 0x3

    new-array v0, v0, [Lorg/keyczar/enums/KeyStatus;

    sget-object v4, Lorg/keyczar/enums/KeyStatus;->PRIMARY:Lorg/keyczar/enums/KeyStatus;

    aput-object v4, v0, v1

    sget-object v1, Lorg/keyczar/enums/KeyStatus;->ACTIVE:Lorg/keyczar/enums/KeyStatus;

    aput-object v1, v0, v2

    sget-object v1, Lorg/keyczar/enums/KeyStatus;->INACTIVE:Lorg/keyczar/enums/KeyStatus;

    aput-object v1, v0, v3

    sput-object v0, Lorg/keyczar/enums/KeyStatus;->$VALUES:[Lorg/keyczar/enums/KeyStatus;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;IILjava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput p3, p0, Lorg/keyczar/enums/KeyStatus;->value:I

    iput-object p4, p0, Lorg/keyczar/enums/KeyStatus;->name:Ljava/lang/String;

    return-void
.end method

.method public static getStatus(I)Lorg/keyczar/enums/KeyStatus;
    .locals 1

    if-eqz p0, :cond_2

    const/4 v0, 0x1

    if-eq p0, v0, :cond_1

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    sget-object p0, Lorg/keyczar/enums/KeyStatus;->INACTIVE:Lorg/keyczar/enums/KeyStatus;

    return-object p0

    :cond_1
    sget-object p0, Lorg/keyczar/enums/KeyStatus;->ACTIVE:Lorg/keyczar/enums/KeyStatus;

    return-object p0

    :cond_2
    sget-object p0, Lorg/keyczar/enums/KeyStatus;->PRIMARY:Lorg/keyczar/enums/KeyStatus;

    return-object p0
.end method

.method public static getStatus(Ljava/lang/String;)Lorg/keyczar/enums/KeyStatus;
    .locals 1

    if-eqz p0, :cond_2

    sget-object v0, Lorg/keyczar/enums/KeyStatus;->PRIMARY:Lorg/keyczar/enums/KeyStatus;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyStatus;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lorg/keyczar/enums/KeyStatus;->PRIMARY:Lorg/keyczar/enums/KeyStatus;

    return-object p0

    :cond_0
    sget-object v0, Lorg/keyczar/enums/KeyStatus;->ACTIVE:Lorg/keyczar/enums/KeyStatus;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyStatus;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    sget-object p0, Lorg/keyczar/enums/KeyStatus;->ACTIVE:Lorg/keyczar/enums/KeyStatus;

    return-object p0

    :cond_1
    sget-object v0, Lorg/keyczar/enums/KeyStatus;->INACTIVE:Lorg/keyczar/enums/KeyStatus;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyStatus;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_2

    sget-object p0, Lorg/keyczar/enums/KeyStatus;->INACTIVE:Lorg/keyczar/enums/KeyStatus;

    return-object p0

    :cond_2
    sget-object p0, Lorg/keyczar/enums/KeyStatus;->ACTIVE:Lorg/keyczar/enums/KeyStatus;

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/keyczar/enums/KeyStatus;
    .locals 1

    const-class v0, Lorg/keyczar/enums/KeyStatus;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/keyczar/enums/KeyStatus;

    return-object p0
.end method

.method public static values()[Lorg/keyczar/enums/KeyStatus;
    .locals 1

    sget-object v0, Lorg/keyczar/enums/KeyStatus;->$VALUES:[Lorg/keyczar/enums/KeyStatus;

    invoke-virtual {v0}, [Lorg/keyczar/enums/KeyStatus;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/keyczar/enums/KeyStatus;

    return-object v0
.end method


# virtual methods
.method getName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lorg/keyczar/enums/KeyStatus;->name:Ljava/lang/String;

    return-object v0
.end method

.method getValue()I
    .locals 1

    iget v0, p0, Lorg/keyczar/enums/KeyStatus;->value:I

    return v0
.end method
