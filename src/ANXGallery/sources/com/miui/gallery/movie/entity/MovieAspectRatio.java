package com.miui.gallery.movie.entity;

import android.os.Build;

public enum MovieAspectRatio {
    A16V9(1.7777778f),
    A18V9(2.0f),
    A19V9(2.1111112f);
    
    private float ratio;

    private MovieAspectRatio(float f) {
        this.ratio = f;
    }

    public static MovieAspectRatio getFitRatio(float f) {
        if (!isNoSupportSpecialRatio()) {
            MovieAspectRatio movieAspectRatio = A18V9;
            if (f >= movieAspectRatio.ratio) {
                MovieAspectRatio movieAspectRatio2 = A19V9;
                return f < movieAspectRatio2.ratio ? movieAspectRatio : movieAspectRatio2;
            }
        }
        return A16V9;
    }

    private static boolean isNoSupportSpecialRatio() {
        return Build.DEVICE.equals("lotus") || Build.DEVICE.equals("cepheus");
    }

    public float getRatio() {
        return this.ratio;
    }
}
