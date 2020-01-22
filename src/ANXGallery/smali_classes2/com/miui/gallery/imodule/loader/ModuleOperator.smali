.class public Lcom/miui/gallery/imodule/loader/ModuleOperator;
.super Ljava/lang/Object;
.source "ModuleOperator.java"


# static fields
.field private static final moduleManager:Lcom/miui/gallery/imodule/base/IModuleManager;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    invoke-static {}, Lcom/miui/gallery/imodule/loader/ModuleManagerImpl;->getModuleManager()Lcom/miui/gallery/imodule/base/IModuleManager;

    move-result-object v0

    sput-object v0, Lcom/miui/gallery/imodule/loader/ModuleOperator;->moduleManager:Lcom/miui/gallery/imodule/base/IModuleManager;

    return-void
.end method

.method public static getModule(Ljava/lang/Class;)Lcom/miui/gallery/imodule/base/IModule;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T::",
            "Lcom/miui/gallery/imodule/base/IModule;",
            ">(",
            "Ljava/lang/Class<",
            "+",
            "Lcom/miui/gallery/imodule/base/IModule;",
            ">;)TT;"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/imodule/loader/ModuleOperator;->moduleManager:Lcom/miui/gallery/imodule/base/IModuleManager;

    invoke-interface {v0, p0}, Lcom/miui/gallery/imodule/base/IModuleManager;->getModule(Ljava/lang/Class;)Lcom/miui/gallery/imodule/base/IModule;

    move-result-object p0

    return-object p0
.end method

.method public static register(Ljava/lang/Class;Lcom/miui/gallery/imodule/base/IModuleImpl;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "+",
            "Lcom/miui/gallery/imodule/base/IModule;",
            ">;",
            "Lcom/miui/gallery/imodule/base/IModuleImpl;",
            ")Z"
        }
    .end annotation

    sget-object v0, Lcom/miui/gallery/imodule/loader/ModuleOperator;->moduleManager:Lcom/miui/gallery/imodule/base/IModuleManager;

    invoke-interface {v0, p0, p1}, Lcom/miui/gallery/imodule/base/IModuleManager;->addModule(Ljava/lang/Class;Lcom/miui/gallery/imodule/base/IModuleImpl;)V

    const/4 p0, 0x1

    return p0
.end method
