.class Lcom/miui/gallery/model/repository/config/ModelManager$SingletonHolder;
.super Ljava/lang/Object;
.source "ModelManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/miui/gallery/model/repository/config/ModelManager;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "SingletonHolder"
.end annotation


# static fields
.field private static final INSTANCE:Lcom/miui/gallery/model/repository/config/ModelManager;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    new-instance v0, Lcom/miui/gallery/model/repository/config/ModelManager;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/miui/gallery/model/repository/config/ModelManager;-><init>(Lcom/miui/gallery/model/repository/config/ModelManager$1;)V

    sput-object v0, Lcom/miui/gallery/model/repository/config/ModelManager$SingletonHolder;->INSTANCE:Lcom/miui/gallery/model/repository/config/ModelManager;

    return-void
.end method

.method static synthetic access$100()Lcom/miui/gallery/model/repository/config/ModelManager;
    .locals 1

    sget-object v0, Lcom/miui/gallery/model/repository/config/ModelManager$SingletonHolder;->INSTANCE:Lcom/miui/gallery/model/repository/config/ModelManager;

    return-object v0
.end method
