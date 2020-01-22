package com.miui.gallery.movie;

import android.content.Context;
import android.os.Build;
import com.miui.gallery.util.BaseFileUtils;
import java.io.File;

public class MovieConfig {
    public static String sAudioDir = null;
    private static boolean sInited = false;
    private static float sMovieRatio = 0.0f;
    public static String sTemplateDir = null;
    public static int sTestLocationType = -1;
    private static boolean sUseXmSdk = true;
    private static final String[] sXmSDKList = {"tucana", "phoenixin", "phoenix"};

    static {
        for (String equals : sXmSDKList) {
            if (equals.equals(Build.DEVICE)) {
            }
        }
    }

    public static float getMovieRatio() {
        return sMovieRatio;
    }

    public static void init(Context context) {
        if (!sInited) {
            String str = isUserXmSdk() ? "xm_" : "";
            String absolutePath = context.getExternalFilesDir(str + "movie").getAbsolutePath();
            sTemplateDir = absolutePath + File.separator + "template";
            sAudioDir = absolutePath + File.separator + "audio";
            BaseFileUtils.addNoMedia(absolutePath);
            sInited = true;
        }
    }

    public static boolean isUserXmSdk() {
        return sUseXmSdk;
    }

    public static void setMovieRatio(float f) {
        sMovieRatio = f;
    }
}
