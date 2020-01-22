.class public interface abstract Lcom/miui/gallery/imodule/base/IModuleManager;
.super Ljava/lang/Object;
.source "IModuleManager.java"


# virtual methods
.method public abstract addModule(Ljava/lang/Class;Lcom/miui/gallery/imodule/base/IModuleImpl;)V
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
.end method

.method public abstract getModule(Ljava/lang/Class;)Lcom/miui/gallery/imodule/base/IModule;
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
.end method
