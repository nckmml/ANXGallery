.class final enum Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;
.super Ljava/lang/Enum;
.source "nexCollageTitleInfo.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4018
    name = "Type"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

.field public static final enum System:Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

.field public static final enum User:Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    const/4 v1, 0x0

    const-string v2, "User"

    invoke-direct {v0, v2, v1}, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;->User:Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    const/4 v2, 0x1

    const-string v3, "System"

    invoke-direct {v0, v3, v2}, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;->System:Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    const/4 v0, 0x2

    new-array v0, v0, [Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    sget-object v3, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;->User:Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    aput-object v3, v0, v1

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;->System:Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    aput-object v1, v0, v2

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

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

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;
    .locals 1

    const-class v0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;
    .locals 1

    sget-object v0, Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    invoke-virtual {v0}, [Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/nexeditorsdk/nexCollageTitleInfo$Type;

    return-object v0
.end method
