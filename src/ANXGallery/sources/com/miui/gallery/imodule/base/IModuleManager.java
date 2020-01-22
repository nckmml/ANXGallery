package com.miui.gallery.imodule.base;

public interface IModuleManager {
    void addModule(Class<? extends IModule> cls, IModuleImpl iModuleImpl);

    IModule getModule(Class<? extends IModule> cls);
}
