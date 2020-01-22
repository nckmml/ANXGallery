package com.miui.gallery.movie.picker;

import android.content.Context;
import com.miui.gallery.activity.ExternalPhotoPageActivity;
import com.miui.gallery.imodule.base.IModuleImpl;
import com.miui.gallery.imodule.modules.MovieDependsModule;
import com.miui.gallery.scanner.MediaScanner;
import com.miui.gallery.util.MovieLibraryLoaderHelper;

public class MovieDependsModuleImpl implements IModuleImpl, MovieDependsModule {
    public String getMovieLibraryPath() {
        return MovieLibraryLoaderHelper.getInstance().getLibraryDirPath();
    }

    public Class getPhotoPagerClass() {
        return ExternalPhotoPageActivity.class;
    }

    public Class getStoryPickClass() {
        return MovieStoryPickActivity.class;
    }

    public void scanSingleFile(Context context, String str) {
        MediaScanner.scanSingleFile(context, str);
    }
}
