.class public final enum Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;
.super Ljava/lang/Enum;
.source "nexBeatTemplateManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Level"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

.field public static final enum Extends:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

.field public static final enum Max:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

.field public static final enum Recommend:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;


# direct methods
.method static constructor <clinit>()V
    .locals 5

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    const/4 v1, 0x0

    const-string v2, "Recommend"

    invoke-direct {v0, v2, v1}, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->Recommend:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    const/4 v2, 0x1

    const-string v3, "Extends"

    invoke-direct {v0, v3, v2}, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->Extends:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    new-instance v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    const/4 v3, 0x2

    const-string v4, "Max"

    invoke-direct {v0, v4, v3}, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->Max:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    const/4 v0, 0x3

    new-array v0, v0, [Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    sget-object v4, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->Recommend:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    aput-object v4, v0, v1

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->Extends:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    aput-object v1, v0, v2

    sget-object v1, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->Max:Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    aput-object v1, v0, v3

    sput-object v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

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

.method public static valueOf(Ljava/lang/String;)Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;
    .locals 1

    const-class v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    return-object p0
.end method

.method public static values()[Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;
    .locals 1

    sget-object v0, Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->$VALUES:[Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    invoke-virtual {v0}, [Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/nexstreaming/nexeditorsdk/nexBeatTemplateManager$Level;

    return-object v0
.end method
