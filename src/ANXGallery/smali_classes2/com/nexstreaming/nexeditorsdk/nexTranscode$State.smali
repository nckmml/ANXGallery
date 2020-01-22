.class public final enum Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;
.super Ljava/lang/Enum;
.source "nexTranscode.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/nexstreaming/nexeditorsdk/nexTranscode;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "State"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

.field public static final enum COMPLETE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

.field public static final enum IDLE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

.field public static final enum NONE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

.field public static final enum RUNNING:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

.field public static final enum WAIT:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;


# direct methods
.method static constructor <clinit>()V
    .locals 7

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    const/4 v1, 0x0

    const-string v2, "NONE"

    invoke-direct {v0, v2, v1}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->NONE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    const/4 v2, 0x1

    const-string v3, "IDLE"

    invoke-direct {v0, v3, v2}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->IDLE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    const/4 v3, 0x2

    const-string v4, "WAIT"

    invoke-direct {v0, v4, v3}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->WAIT:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    const/4 v4, 0x3

    const-string v5, "RUNNING"

    invoke-direct {v0, v5, v4}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->RUNNING:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    const/4 v5, 0x4

    const-string v6, "COMPLETE"

    invoke-direct {v0, v6, v5}, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->COMPLETE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    const/4 v0, 0x5

    new-array v0, v0, [Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    sget-object v6, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->NONE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    aput-object v6, v0, v1

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->IDLE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    aput-object v1, v0, v2

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->WAIT:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    aput-object v1, v0, v3

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->RUNNING:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    aput-object v1, v0, v4

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->COMPLETE:Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    aput-object v1, v0, v5

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

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

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;
    .locals 1

    const-class v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;
    .locals 1

    sget-object v0, Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    invoke-virtual {v0}, [Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/nexeditorsdk/nexTranscode$State;

    return-object v0
.end method
