package com.miui.gallery.modules;

import com.miui.gallery.imodule.loader.ModuleOperator;
import com.miui.gallery.imodule.modules.MovieDependsModule;
import com.miui.gallery.movie.picker.MovieDependsModuleImpl;

public class ModulesRegister {
    public static void register() {
        ModuleOperator.register(MovieDependsModule.class, new MovieDependsModuleImpl());
    }
}
