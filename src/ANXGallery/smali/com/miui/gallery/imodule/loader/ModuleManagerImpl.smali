.class Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;
.super Ljava/lang/Object;
.source "ModuleManagerImpl.java"

# interfaces
.implements Lcom/miui/gallery/imodule/base/IModuleManager;


# static fields
.field private static final mModuleManager:Lcom/miui/gallery/imodule/base/IModuleManager;


# instance fields
.field private final mBuiltinModules:Ljava/util/HashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/HashMap<",
            "Ljava/lang/Class<",
            "+",
            "Lcom/miui/gallery/imodule/base/IModule;",
            ">;",
            "Lcom/miui/gallery/imodule/base/IModuleImpl;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;

    invoke-direct {v0}, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;-><init>()V

    sput-object v0, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;->mModuleManager:Lcom/miui/gallery/imodule/base/IModuleManager;

    return-void
.end method

.method private constructor <init>()V
    .locals 1

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    iput-object v0, p0, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;->mBuiltinModules:Ljava/util/HashMap;

    return-void
.end method

.method public static getModuleManager()Lcom/miui/gallery/imodule/base/IModuleManager;
    .locals 1

    sget-object v0, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;->mModuleManager:Lcom/miui/gallery/imodule/base/IModuleManager;

    return-object v0
.end method


# virtual methods
.method public addModule(Ljava/lang/Class;Lcom/miui/gallery/imodule/base/IModuleImpl;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "+",
            "Lcom/miui/gallery/imodule/base/IModule;",
            ">;",
            "Lcom/miui/gallery/imodule/base/IModuleImpl;",
            ")V"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;->mBuiltinModules:Ljava/util/HashMap;

    invoke-virtual {v0, p1, p2}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public getModule(Ljava/lang/Class;)Lcom/miui/gallery/imodule/base/IModule;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "+",
            "Lcom/miui/gallery/imodule/base/IModule;",
            ">;)",
            "Lcom/miui/gallery/imodule/base/IModule;"
        }
    .end annotation

    iget-object v0, p0, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;->mBuiltinModules:Ljava/util/HashMap;

    invoke-virtual {v0, p1}, Ljava/util/HashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/miui/gallery/imodule/base/IModule;

    return-object p1
.end method
