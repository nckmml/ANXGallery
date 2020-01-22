.class public final enum Lorg/keyczar/enums/KeyPurpose;
.super Ljava/lang/Enum;
.source "KeyPurpose.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lorg/keyczar/enums/KeyPurpose;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lorg/keyczar/enums/KeyPurpose;

.field public static final enum DECRYPT_AND_ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

.field public static final enum ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

.field public static final enum SIGN_AND_VERIFY:Lorg/keyczar/enums/KeyPurpose;

.field public static final enum TEST:Lorg/keyczar/enums/KeyPurpose;

.field public static final enum VERIFY:Lorg/keyczar/enums/KeyPurpose;


# instance fields
.field private name:Ljava/lang/String;

.field private value:I


# direct methods
.method static constructor <clinit>()V
    .locals 9

    new-instance v0, Lorg/keyczar/enums/KeyPurpose;

    const/4 v1, 0x0

    const-string v2, "DECRYPT_AND_ENCRYPT"

    const-string v3, "crypt"

    invoke-direct {v0, v2, v1, v1, v3}, Lorg/keyczar/enums/KeyPurpose;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyPurpose;->DECRYPT_AND_ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    new-instance v0, Lorg/keyczar/enums/KeyPurpose;

    const/4 v2, 0x1

    const-string v3, "ENCRYPT"

    const-string v4, "encrypt"

    invoke-direct {v0, v3, v2, v2, v4}, Lorg/keyczar/enums/KeyPurpose;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyPurpose;->ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    new-instance v0, Lorg/keyczar/enums/KeyPurpose;

    const/4 v3, 0x2

    const-string v4, "SIGN_AND_VERIFY"

    const-string v5, "sign"

    invoke-direct {v0, v4, v3, v3, v5}, Lorg/keyczar/enums/KeyPurpose;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyPurpose;->SIGN_AND_VERIFY:Lorg/keyczar/enums/KeyPurpose;

    new-instance v0, Lorg/keyczar/enums/KeyPurpose;

    const/4 v4, 0x3

    const-string v5, "VERIFY"

    const-string v6, "verify"

    invoke-direct {v0, v5, v4, v4, v6}, Lorg/keyczar/enums/KeyPurpose;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyPurpose;->VERIFY:Lorg/keyczar/enums/KeyPurpose;

    new-instance v0, Lorg/keyczar/enums/KeyPurpose;

    const/4 v5, 0x4

    const-string v6, "TEST"

    const/16 v7, 0x7f

    const-string v8, "test"

    invoke-direct {v0, v6, v5, v7, v8}, Lorg/keyczar/enums/KeyPurpose;-><init>(Ljava/lang/String;IILjava/lang/String;)V

    sput-object v0, Lorg/keyczar/enums/KeyPurpose;->TEST:Lorg/keyczar/enums/KeyPurpose;

    const/4 v0, 0x5

    new-array v0, v0, [Lorg/keyczar/enums/KeyPurpose;

    sget-object v6, Lorg/keyczar/enums/KeyPurpose;->DECRYPT_AND_ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    aput-object v6, v0, v1

    sget-object v1, Lorg/keyczar/enums/KeyPurpose;->ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    aput-object v1, v0, v2

    sget-object v1, Lorg/keyczar/enums/KeyPurpose;->SIGN_AND_VERIFY:Lorg/keyczar/enums/KeyPurpose;

    aput-object v1, v0, v3

    sget-object v1, Lorg/keyczar/enums/KeyPurpose;->VERIFY:Lorg/keyczar/enums/KeyPurpose;

    aput-object v1, v0, v4

    sget-object v1, Lorg/keyczar/enums/KeyPurpose;->TEST:Lorg/keyczar/enums/KeyPurpose;

    aput-object v1, v0, v5

    sput-object v0, Lorg/keyczar/enums/KeyPurpose;->$VALUES:[Lorg/keyczar/enums/KeyPurpose;

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

    iput p3, p0, Lorg/keyczar/enums/KeyPurpose;->value:I

    iput-object p4, p0, Lorg/keyczar/enums/KeyPurpose;->name:Ljava/lang/String;

    return-void
.end method

.method public static getPurpose(I)Lorg/keyczar/enums/KeyPurpose;
    .locals 1

    if-eqz p0, :cond_4

    const/4 v0, 0x1

    if-eq p0, v0, :cond_3

    const/4 v0, 0x2

    if-eq p0, v0, :cond_2

    const/4 v0, 0x3

    if-eq p0, v0, :cond_1

    const/16 v0, 0x7f

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    :cond_0
    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->TEST:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_1
    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->VERIFY:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_2
    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->SIGN_AND_VERIFY:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_3
    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_4
    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->DECRYPT_AND_ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    return-object p0
.end method

.method public static getPurpose(Ljava/lang/String;)Lorg/keyczar/enums/KeyPurpose;
    .locals 1

    if-eqz p0, :cond_4

    sget-object v0, Lorg/keyczar/enums/KeyPurpose;->DECRYPT_AND_ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyPurpose;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->DECRYPT_AND_ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_0
    sget-object v0, Lorg/keyczar/enums/KeyPurpose;->ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyPurpose;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->ENCRYPT:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_1
    sget-object v0, Lorg/keyczar/enums/KeyPurpose;->SIGN_AND_VERIFY:Lorg/keyczar/enums/KeyPurpose;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyPurpose;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->SIGN_AND_VERIFY:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_2
    sget-object v0, Lorg/keyczar/enums/KeyPurpose;->VERIFY:Lorg/keyczar/enums/KeyPurpose;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyPurpose;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->VERIFY:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_3
    sget-object v0, Lorg/keyczar/enums/KeyPurpose;->TEST:Lorg/keyczar/enums/KeyPurpose;

    invoke-virtual {v0}, Lorg/keyczar/enums/KeyPurpose;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result p0

    if-eqz p0, :cond_4

    sget-object p0, Lorg/keyczar/enums/KeyPurpose;->TEST:Lorg/keyczar/enums/KeyPurpose;

    return-object p0

    :cond_4
    const/4 p0, 0x0

    return-object p0
.end method

.method public static valueOf(Ljava/lang/String;)Lorg/keyczar/enums/KeyPurpose;
    .locals 1

    const-class v0, Lorg/keyczar/enums/KeyPurpose;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lorg/keyczar/enums/KeyPurpose;

    return-object p0
.end method

.method public static values()[Lorg/keyczar/enums/KeyPurpose;
    .locals 1

    sget-object v0, Lorg/keyczar/enums/KeyPurpose;->$VALUES:[Lorg/keyczar/enums/KeyPurpose;

    invoke-virtual {v0}, [Lorg/keyczar/enums/KeyPurpose;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lorg/keyczar/enums/KeyPurpose;

    return-object v0
.end method


# virtual methods
.method getName()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lorg/keyczar/enums/KeyPurpose;->name:Ljava/lang/String;

    return-object v0
.end method

.method getValue()I
    .locals 1

    iget v0, p0, Lorg/keyczar/enums/KeyPurpose;->value:I

    return v0
.end method
