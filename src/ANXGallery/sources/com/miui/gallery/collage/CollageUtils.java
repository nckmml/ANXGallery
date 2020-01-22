package com.miui.gallery.collage;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miui.gallery.util.IoUtils;
import com.miui.gallery.util.Log;
import java.io.IOException;
import java.io.InputStream;

public class CollageUtils {
    public static Gson generateCustomGson() {
        return new GsonBuilder().create();
    }

    public static Drawable getDrawableByAssets(Resources resources, String str) {
        InputStream inputStream;
        Drawable drawable = null;
        try {
            inputStream = resources.getAssets().open(str);
            try {
                drawable = Drawable.createFromStream(inputStream, (String) null);
            } catch (IOException unused) {
                try {
                    Log.e("CollageUtils", "load poster element img fail ! path %s", (Object) str);
                    IoUtils.close("CollageUtils", inputStream);
                    return drawable;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (IOException unused2) {
            inputStream = null;
            Log.e("CollageUtils", "load poster element img fail ! path %s", (Object) str);
            IoUtils.close("CollageUtils", inputStream);
            return drawable;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IoUtils.close("CollageUtils", inputStream);
            throw th;
        }
        IoUtils.close("CollageUtils", inputStream);
        return drawable;
    }

    public static String loadResourceFileString(AssetManager assetManager, String str) {
        InputStream inputStream;
        String str2 = null;
        try {
            inputStream = assetManager.open(str);
            try {
                str2 = IoUtils.readInputStreamToString("CollageUtils", inputStream);
            } catch (IOException e) {
                e = e;
                try {
                    Log.e("CollageUtils", (Throwable) e);
                    IoUtils.close(inputStream);
                    return str2;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            inputStream = null;
            Log.e("CollageUtils", (Throwable) e);
            IoUtils.close(inputStream);
            return str2;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IoUtils.close(inputStream);
            throw th;
        }
        IoUtils.close(inputStream);
        return str2;
    }
}
