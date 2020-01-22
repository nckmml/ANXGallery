package com.miui.gallery.provider;

import android.app.IntentService;
import android.content.Intent;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.NotificationHelper;

public class MediaOperationService extends IntentService {
    public static String EXTRA_ARGUMENT = "argument";
    public static String EXTRA_BUNDLE = "bundle";
    public static String EXTRA_METHOD = "method";

    public MediaOperationService() {
        super("MediaOperationService");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        Log.d("MediaOperationService", "On start handling intent %s", (Object) intent);
        startForeground(5, NotificationHelper.getEmptyNotification(getApplicationContext()));
        try {
            Log.d("MediaOperationService", "Done handling intent %s, result %s", intent, getBaseContext().getContentResolver().call(GalleryContract.AUTHORITY_URI, intent.getStringExtra(EXTRA_METHOD), intent.getStringExtra(EXTRA_ARGUMENT), intent.getBundleExtra(EXTRA_BUNDLE)));
        } catch (Exception unused) {
            Log.e("MediaOperationService", "Error occurred while executing intent %s", (Object) intent);
        } catch (Throwable th) {
            stopForeground(true);
            throw th;
        }
        stopForeground(true);
    }
}
