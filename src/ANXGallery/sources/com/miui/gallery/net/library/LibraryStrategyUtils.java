package com.miui.gallery.net.library;

import android.os.Build;
import com.miui.gallery.assistant.library.LibraryConstants;
import java.util.Arrays;

public class LibraryStrategyUtils {
    private static final String[] sMtkList = {"begonia", "begoniain"};

    public static String getKeyForLibraryId(long j) {
        return (j != 1012 || !Arrays.asList(sMtkList).contains(Build.DEVICE)) ? LibraryConstants.CURRENT_ABI : "mtk";
    }
}
