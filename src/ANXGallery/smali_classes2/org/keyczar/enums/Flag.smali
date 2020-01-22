.class public final enum Lorg/keyczar/enums/Flag;
.super Ljava/lang/Enum;
.source "Flag.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/keyczar/enums/Flag;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/keyczar/enums/Flag;

.field public static final enum ASYMMETRIC:Lorg/keyczar/enums/Flag;

.field public static final enum CRYPTER:Lorg/keyczar/enums/Flag;

.field public static final enum DESTINATION:Lorg/keyczar/enums/Flag;

.field public static final enum LOCATION:Lorg/keyczar/enums/Flag;

.field public static final enum NAME:Lorg/keyczar/enums/Flag;

.field public static final enum PADDING:Lorg/keyczar/enums/Flag;

.field public static final enum PASSPHRASE:Lorg/keyczar/enums/Flag;

.field public static final enum PEMFILE:Lorg/keyczar/enums/Flag;

.field public static final enum PURPOSE:Lorg/keyczar/enums/Flag;

.field public static final enum SIZE:Lorg/keyczar/enums/Flag;

.field public static final enum STATUS:Lorg/keyczar/enums/Flag;

.field public static final enum VERSION:Lorg/keyczar/enums/Flag;


# instance fields
.field private final name:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 15

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v1, 0x0

    const-string v2, "LOCATION"

    const-string v3, "location"

    invoke-direct {v0, v2, v1, v3}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->LOCATION:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v2, 0x1

    const-string v3, "NAME"

    const-string v4, "name"

    invoke-direct {v0, v3, v2, v4}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->NAME:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v3, 0x2

    const-string v4, "SIZE"

    const-string v5, "size"

    invoke-direct {v0, v4, v3, v5}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->SIZE:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v4, 0x3

    const-string v5, "STATUS"

    const-string v6, "status"

    invoke-direct {v0, v5, v4, v6}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->STATUS:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v5, 0x4

    const-string v6, "PURPOSE"

    const-string v7, "purpose"

    invoke-direct {v0, v6, v5, v7}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->PURPOSE:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v6, 0x5

    const-string v7, "PADDING"

    const-string v8, "padding"

    invoke-direct {v0, v7, v6, v8}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->PADDING:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v7, 0x6

    const-string v8, "DESTINATION"

    const-string v9, "destination"

    invoke-direct {v0, v8, v7, v9}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->DESTINATION:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/4 v8, 0x7

    const-string v9, "VERSION"

    const-string v10, "version"

    invoke-direct {v0, v9, v8, v10}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->VERSION:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/16 v9, 0x8

    const-string v10, "ASYMMETRIC"

    const-string v11, "asymmetric"

    invoke-direct {v0, v10, v9, v11}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->ASYMMETRIC:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/16 v10, 0x9

    const-string v11, "CRYPTER"

    const-string v12, "crypter"

    invoke-direct {v0, v11, v10, v12}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->CRYPTER:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/16 v11, 0xa

    const-string v12, "PEMFILE"

    const-string v13, "pemfile"

    invoke-direct {v0, v12, v11, v13}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->PEMFILE:Lorg/keyczar/enums/Flag;

    new-instance v0, Lorg/keyczar/enums/Flag;

    const/16 v12, 0xb

    const-string v13, "PASSPHRASE"

    const-string v14, "passphrase"

    invoke-direct {v0, v13, v12, v14}, Lorg/keyczar/enums/Flag;-><init>(Ljava/lang/String;ILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/Flag;->PASSPHRASE:Lorg/keyczar/enums/Flag;

    const/16 v0, 0xc

    new-array v0, v0, [Lorg/keyczar/enums/Flag;

    sget-object v13, Lorg/keyczar/enums/Flag;->LOCATION:Lorg/keyczar/enums/Flag;

    aput-object v13, v0, v1

    sget-object v1, Lorg/keyczar/enums/Flag;->NAME:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v2

    sget-object v1, Lorg/keyczar/enums/Flag;->SIZE:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v3

    sget-object v1, Lorg/keyczar/enums/Flag;->STATUS:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v4

    sget-object v1, Lorg/keyczar/enums/Flag;->PURPOSE:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v5

    sget-object v1, Lorg/keyczar/enums/Flag;->PADDING:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v6

    sget-object v1, Lorg/keyczar/enums/Flag;->DESTINATION:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v7

    sget-object v1, Lorg/keyczar/enums/Flag;->VERSION:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v8

    sget-object v1, Lorg/keyczar/enums/Flag;->ASYMMETRIC:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v9

    sget-object v1, Lorg/keyczar/enums/Flag;->CRYPTER:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v10

    sget-object v1, Lorg/keyczar/enums/Flag;->PEMFILE:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v11

    sget-object v1, Lorg/keyczar/enums/Flag;->PASSPHRASE:Lorg/keyczar/enums/Flag;

    aput-object v1, v0, v12

    sput-object v0, Lorg/keyczar/enums/Flag;->$VALUES:[Lorg/keyczar/enums/Flag;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;ILjava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    iput-object p3, p0, Lorg/keyczar/enums/Flag;->name:Ljava/lang/String;

    return-void
.end method

.method public static getFlag(Ljava/lang/String;)Lorg/keyczar/enums/Flag;
    .locals 3

    if-eqz p0, :cond_c

    sget-object v0, Lorg/keyczar/enums/Flag;->LOCATION:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lorg/keyczar/enums/Flag;->LOCATION:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_0
    sget-object v0, Lorg/keyczar/enums/Flag;->NAME:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    sget-object p0, Lorg/keyczar/enums/Flag;->NAME:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_1
    sget-object v0, Lorg/keyczar/enums/Flag;->SIZE:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    sget-object p0, Lorg/keyczar/enums/Flag;->SIZE:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_2
    sget-object v0, Lorg/keyczar/enums/Flag;->STATUS:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    sget-object p0, Lorg/keyczar/enums/Flag;->STATUS:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_3
    sget-object v0, Lorg/keyczar/enums/Flag;->PURPOSE:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_4

    sget-object p0, Lorg/keyczar/enums/Flag;->PURPOSE:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_4
    sget-object v0, Lorg/keyczar/enums/Flag;->DESTINATION:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_5

    sget-object p0, Lorg/keyczar/enums/Flag;->DESTINATION:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_5
    sget-object v0, Lorg/keyczar/enums/Flag;->VERSION:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_6

    sget-object p0, Lorg/keyczar/enums/Flag;->VERSION:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_6
    sget-object v0, Lorg/keyczar/enums/Flag;->ASYMMETRIC:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_7

    sget-object p0, Lorg/keyczar/enums/Flag;->ASYMMETRIC:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_7
    sget-object v0, Lorg/keyczar/enums/Flag;->CRYPTER:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_8

    sget-object p0, Lorg/keyczar/enums/Flag;->CRYPTER:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_8
    sget-object v0, Lorg/keyczar/enums/Flag;->PEMFILE:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_9

    sget-object p0, Lorg/keyczar/enums/Flag;->PEMFILE:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_9
    sget-object v0, Lorg/keyczar/enums/Flag;->PASSPHRASE:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_a

    sget-object p0, Lorg/keyczar/enums/Flag;->PASSPHRASE:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_a
    sget-object v0, Lorg/keyczar/enums/Flag;->PADDING:Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, Lorg/keyczar/enums/Flag;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_b

    sget-object p0, Lorg/keyczar/enums/Flag;->PADDING:Lorg/keyczar/enums/Flag;

    return-object p0

    :cond_b
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const/4 v1, 0x1

    new-array v1, v1, [Ljava/lang/Object;

    const/4 v2, 0x0

    aput-object p0, v1, v2

    const-string p0, "Flag.UnknownFlag"

    invoke-static {p0, v1}, Lorg/keyczar/i18n/Messages;->getString(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_c
    new-instance p0, Ljava/lang/NullPointerException;

    invoke-direct {p0}, Ljava/lang/NullPointerException;-><init>()V

    throw p0
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/keyczar/enums/Flag;
    .locals 1

    const-class v0, Lorg/keyczar/enums/Flag;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/keyczar/enums/Flag;

    return-object p0
.end method

.method public static values()[Lorg/keyczar/enums/Flag;
    .locals 1

    sget-object v0, Lorg/keyczar/enums/Flag;->$VALUES:[Lorg/keyczar/enums/Flag;

    invoke-virtual {v0}, [Lorg/keyczar/enums/Flag;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/keyczar/enums/Flag;

    return-object v0
.end method


# virtual methods
.method public toString()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lorg/keyczar/enums/Flag;->name:Ljava/lang/String;

    return-object v0
.end method
