.class public final enum Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;
.super Ljava/lang/Enum;
.source "PaintElementOperationDrawable.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Action"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

.field public static final enum DELETE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

.field public static final enum EDIT:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

.field public static final enum MIRROR:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

.field public static final enum REVERSE_BLACK:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

.field public static final enum REVERSE_WHITE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

.field public static final enum ROTATE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

.field public static final enum SCALE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;


# instance fields
.field public final icon:I


# direct methods
.method static constructor <clinit>()V
    .locals 10

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v1, 0x0

    const-string v2, "EDIT"

    const v3, 0x7f0700e9

    invoke-direct {v0, v2, v1, v3}, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->EDIT:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v2, 0x1

    const-string v3, "ROTATE"

    const v4, 0x7f0700f1

    invoke-direct {v0, v3, v2, v4}, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->ROTATE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v3, 0x2

    const-string v4, "SCALE"

    const v5, 0x7f0700f4

    invoke-direct {v0, v4, v3, v5}, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->SCALE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v4, 0x3

    const-string v5, "DELETE"

    const v6, 0x7f0700e6

    invoke-direct {v0, v5, v4, v6}, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->DELETE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v5, 0x4

    const-string v6, "MIRROR"

    const v7, 0x7f0700ec

    invoke-direct {v0, v6, v5, v7}, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->MIRROR:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v6, 0x5

    const-string v7, "REVERSE_WHITE"

    const v8, 0x7f0700f0

    invoke-direct {v0, v7, v6, v8}, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->REVERSE_WHITE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    new-instance v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v7, 0x6

    const-string v8, "REVERSE_BLACK"

    const v9, 0x7f0700ef

    invoke-direct {v0, v8, v7, v9}, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;-><init>(Ljava/lang/String;II)V

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->REVERSE_BLACK:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    const/4 v0, 0x7

    new-array v0, v0, [Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    sget-object v8, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->EDIT:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    aput-object v8, v0, v1

    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->ROTATE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    aput-object v1, v0, v2

    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->SCALE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    aput-object v1, v0, v3

    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->DELETE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    aput-object v1, v0, v4

    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->MIRROR:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    aput-object v1, v0, v5

    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->REVERSE_WHITE:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    aput-object v1, v0, v6

    sget-object v1, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->REVERSE_BLACK:Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    aput-object v1, v0, v7

    sput-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->$VALUES:[Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

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

    iput p3, p0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->icon:I

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;
    .locals 1

    const-class v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0

    check-cast p0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    return-object p0
.end method

.method public static values()[Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;
    .locals 1

    sget-object v0, Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->$VALUES:[Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    invoke-virtual {v0}, [Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lcom/miui/gallery/editor/photo/core/imports/doodle/PaintElementOperationDrawable$Action;

    return-object v0
.end method
