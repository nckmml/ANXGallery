package com.miui.gallery.imodule.loader;

import com.miui.gallery.imodule.base.IModule;
import com.miui.gallery.imodule.base.IModuleImpl;
import com.miui.gallery.imodule.base.IModuleManager;
import java.util.HashMap;
import java.util.LinkedHashMap;

class ModuleManagerImpl implements IModuleManager {
    private static final IModuleManager mModuleManager = new ModuleManagerImpl();
    private final HashMap<Class<? extends IModule>, IModuleImpl> mBuiltinModules = new LinkedHashMap();

    private ModuleManagerImpl() {
    }

    public static IModuleManager getModuleManager() {
        return mModuleManager;
    }

    public void addModule(Class<? extends IModule> cls, IModuleImpl iModuleImpl) {
        this.mBuiltinModules.put(cls, iModuleImpl);
    }

    public IModule getModule(Class<? extends IModule> cls) {
        return this.mBuiltinModules.get(cls);
    }
}
