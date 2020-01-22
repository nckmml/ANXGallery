package com.miui.gallery.imodule.modules;

import android.content.Context;
import com.miui.gallery.imodule.base.IModule;

public interface MovieDependsModule extends IModule {
    String getMovieLibraryPath();

    Class getPhotoPagerClass();

    Class getStoryPickClass();

    void scanSingleFile(Context context, String str);
}
