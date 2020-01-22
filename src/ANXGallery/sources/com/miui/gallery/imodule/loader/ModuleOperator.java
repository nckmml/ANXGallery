package com.miui.gallery.imodule.loader;

import com.miui.gallery.imodule.base.IModule;
import com.miui.gallery.imodule.base.IModuleImpl;
import com.miui.gallery.imodule.base.IModuleManager;

public class ModuleOperator {
    private static final IModuleManager moduleManager = ModuleManagerImpl.getModuleManager();

    public static <T extends IModule> T getModule(Class<? extends IModule> cls) {
        return moduleManager.getModule(cls);
    }

    public static boolean register(Class<? extends IModule> cls, IModuleImpl iModuleImpl) {
        moduleManager.addModule(cls, iModuleImpl);
        return true;
    }
}
