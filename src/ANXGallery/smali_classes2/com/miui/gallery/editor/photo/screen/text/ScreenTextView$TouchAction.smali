.class final enum Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;
.super Ljava/lang/Enum;
.source "ScreenTextView.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x401a
    name = "TouchAction"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

.field public static final enum DELETE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

.field public static final enum EDIT:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

.field public static final enum MIRROR:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

.field public static final enum NONE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

.field public static final enum REVERSE_BLACK:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

.field public static final enum REVERSE_WHITE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

.field public static final enum SCALE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;


# direct methods
.method static constructor <clinit>()V
    .locals 9

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v1, 0x0

    const-string v2, "NONE"

    invoke-direct {v0, v2, v1}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->NONE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v2, 0x1

    const-string v3, "DELETE"

    invoke-direct {v0, v3, v2}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->DELETE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v3, 0x2

    const-string v4, "SCALE"

    invoke-direct {v0, v4, v3}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->SCALE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v4, 0x3

    const-string v5, "MIRROR"

    invoke-direct {v0, v5, v4}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->MIRROR:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v5, 0x4

    const-string v6, "EDIT"

    invoke-direct {v0, v6, v5}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->EDIT:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v6, 0x5

    const-string v7, "REVERSE_WHITE"

    invoke-direct {v0, v7, v6}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->REVERSE_WHITE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    new-instance v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v7, 0x6

    const-string v8, "REVERSE_BLACK"

    invoke-direct {v0, v8, v7}, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->REVERSE_BLACK:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    const/4 v0, 0x7

    new-array v0, v0, [Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    sget-object v8, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->NONE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    aput-object v8, v0, v1

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->DELETE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->SCALE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->MIRROR:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->EDIT:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->REVERSE_WHITE:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    aput-object v1, v0, v6

    sget-object v1, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->REVERSE_BLACK:Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    aput-object v1, v0, v7

    sput-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->$VALUES:[Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

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

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;
    .locals 1

    const-class v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;
    .locals 1

    sget-object v0, Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->$VALUES:[Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    invoke-virtual {v0}, [Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/editor/photo/screen/text/ScreenTextView$TouchAction;

    return-object v0
.end method
