package com.miui.gallery.util.deviceprovider;

import android.graphics.BitmapFactory;
import java.io.IOException;

public class BigBitmapProvider implements BitmapProviderInterface {
    public BitmapFactory.Options getBitmapSize(String str) throws IOException {
        return miui.graphics.BitmapFactory.getBitmapSize(str);
    }
}
