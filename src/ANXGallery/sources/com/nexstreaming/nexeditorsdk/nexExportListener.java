package com.nexstreaming.nexeditorsdk;

import android.graphics.Bitmap;
import com.nexstreaming.nexeditorsdk.nexEngine;

public interface nexExportListener {
    void onExportDone(Bitmap bitmap);

    void onExportFail(nexEngine.nexErrorCode nexerrorcode);

    void onExportProgress(int i);
}
