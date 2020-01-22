package com.miui.gallery.movie.utils;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.miui.gallery.imodule.loader.ModuleOperator;
import com.miui.gallery.imodule.modules.MovieDependsModule;
import com.miui.gallery.movie.entity.ImageEntity;
import com.miui.gallery.movie.entity.MovieResource;
import com.miui.gallery.util.BaseFileUtils;
import com.miui.gallery.util.ConvertFilepathUtil;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MovieUtils {
    public static void checkResourceExist(List<? extends MovieResource> list) {
        if (list != null) {
            for (MovieResource movieResource : list) {
                if (BaseFileUtils.isFileExist(movieResource.getDownloadSrcPath())) {
                    movieResource.downloadState = 17;
                }
            }
        }
    }

    public static List<ImageEntity> getImageFromClipData(Context context, Intent intent) {
        ArrayList arrayList = new ArrayList();
        ClipData clipData = intent.getClipData();
        if (clipData == null) {
            arrayList.add(new ImageEntity(ConvertFilepathUtil.getPath(context, intent.getData()), (String) null));
        } else {
            for (int i = 0; i < clipData.getItemCount(); i++) {
                ClipData.Item itemAt = clipData.getItemAt(i);
                if (itemAt != null) {
                    String path = ConvertFilepathUtil.getPath(context, itemAt.getUri());
                    String valueOf = String.valueOf(itemAt.getText());
                    Log.d("MovieUtils", "getImageFromClipData path is %s ,%s", path, itemAt.getUri());
                    if (path == null || !BaseFileUtils.isFileExist(path)) {
                        Log.e("MovieUtils", "getImageFromClipData path is null %s", (Object) itemAt.getUri());
                    } else {
                        arrayList.add(new ImageEntity(path, valueOf));
                    }
                }
            }
        }
        return arrayList;
    }

    public static void goDetail(Context context, Uri uri) {
        MovieDependsModule movieDependsModule = (MovieDependsModule) ModuleOperator.getModule(MovieDependsModule.class);
        if (movieDependsModule != null) {
            Intent intent = new Intent(context, movieDependsModule.getPhotoPagerClass());
            intent.setData(uri);
            context.startActivity(intent);
        }
    }
}
