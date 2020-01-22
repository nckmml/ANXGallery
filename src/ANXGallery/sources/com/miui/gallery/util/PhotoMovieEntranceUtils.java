package com.miui.gallery.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.miui.gallery.movie.ui.activity.MovieActivity;

public class PhotoMovieEntranceUtils {
    public static boolean isPhotoMovieAvailable() {
        return Build.VERSION.SDK_INT >= 21 && !BuildUtil.isPad();
    }

    public static void startPicker(Context context) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("pick-upper-bound", 20);
        intent.putExtra("pick-lower-bound", 3);
        intent.putExtra("extra_filter_media_type", new String[]{"image/x-adobe-dng"});
        intent.putExtra("pick_intent", new Intent(context, MovieActivity.class));
        intent.putExtra("pick_close_type", 3);
        intent.setPackage("com.miui.gallery");
        context.startActivity(intent);
    }
}
