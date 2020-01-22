.class public Lcom/miui/gallery/modules/ModulesRegister;
.super Ljava/lang/Object;
.source "ModulesRegister.java"


# direct methods
.method public static register()V
    .locals 2

    const-class v0, Lcom/miui/gallery/imodule/modules/MovieDependsModule;

    new-instance v1, Lcom/miui/gallery/movie/picker/MovieDependsModuleImpl;

    invoke-direct {v1}, Lcom/miui/gallery/movie/picker/MovieDependsModuleImpl;-><init>()V

    invoke-static {v0, v1}, Lcom/miui/gallery/imodule/loader/ModuleOperator;->register(Ljava/lang/Class;Lcom/miui/gallery/imodule/base/IModuleImpl;)Z

    return-void
.end method
