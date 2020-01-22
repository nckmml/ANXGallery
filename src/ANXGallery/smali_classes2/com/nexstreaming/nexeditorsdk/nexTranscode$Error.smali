.class public final enum Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;
.super Ljava/lang/Enum;
.source "nexTranscode.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/nexstreaming/nexeditorsdk/nexTranscode;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Error"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

.field public static final enum BUSY:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

.field public static final enum CANCEL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

.field public static final enum ENGINEFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

.field public static final enum NONE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

.field public static final enum NOTSUPPORTEDFILE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

.field public static final enum RUNFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

.field public static final enum SOURCEFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;


# direct methods
.method static constructor <clinit>()V
    .locals 9

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v1, 0x0

    const-string v2, "NONE"

    invoke-direct {v0, v2, v1}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->NONE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v2, 0x1

    const-string v3, "NOTSUPPORTEDFILE"

    invoke-direct {v0, v3, v2}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->NOTSUPPORTEDFILE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v3, 0x2

    const-string v4, "ENGINEFAIL"

    invoke-direct {v0, v4, v3}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->ENGINEFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v4, 0x3

    const-string v5, "SOURCEFAIL"

    invoke-direct {v0, v5, v4}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->SOURCEFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v5, 0x4

    const-string v6, "BUSY"

    invoke-direct {v0, v6, v5}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->BUSY:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v6, 0x5

    const-string v7, "RUNFAIL"

    invoke-direct {v0, v7, v6}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->RUNFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v7, 0x6

    const-string v8, "CANCEL"

    invoke-direct {v0, v8, v7}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->CANCEL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    const/4 v0, 0x7

    new-array v0, v0, [Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    sget-object v8, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->NONE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    aput-object v8, v0, v1

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->NOTSUPPORTEDFILE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    aput-object v1, v0, v2

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->ENGINEFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    aput-object v1, v0, v3

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->SOURCEFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    aput-object v1, v0, v4

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->BUSY:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    aput-object v1, v0, v5

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->RUNFAIL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    aput-object v1, v0, v6

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->CANCEL:Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    aput-object v1, v0, v7

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

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

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;
    .locals 1

    const-class v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;
    .locals 1

    sget-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    invoke-virtual {v0}, [Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/nexeditorsdk/nexTranscode$Error;

    return-object v0
.end method
