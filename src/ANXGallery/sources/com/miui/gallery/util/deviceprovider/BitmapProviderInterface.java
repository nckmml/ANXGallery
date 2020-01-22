package com.miui.gallery.util.deviceprovider;

import android.graphics.BitmapFactory;
import java.io.IOException;

public interface BitmapProviderInterface {
    BitmapFactory.Options getBitmapSize(String str) throws IOException;
}
