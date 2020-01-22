package com.miui.gallery.editor.photo.screen.home;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.android.systemui.screenshot.IBitmapService;
import com.android.systemui.screenshot.IScreenShotCallback;
import com.miui.gallery.util.Log;

public class ScreenShotService extends Service {
    /* access modifiers changed from: private */
    public RemoteCallbackList<IScreenShotCallback> mListenerList = new RemoteCallbackList<>();
    IBitmapService.Stub mStub = new IBitmapService.Stub() {
        public void registerCallback(IScreenShotCallback iScreenShotCallback) throws RemoteException {
            try {
                Log.d("ScreenShotService", "registerCallback");
                ScreenShotService.this.mListenerList.register(iScreenShotCallback);
            } catch (Exception e) {
                Log.e("ScreenShotService", "registerCallback: %s", (Object) e.toString());
            }
        }

        public void unregisterCallback(IScreenShotCallback iScreenShotCallback) throws RemoteException {
            try {
                Log.d("ScreenShotService", "unregisterCallback");
                ScreenShotService.this.mListenerList.unregister(iScreenShotCallback);
            } catch (Exception e) {
                Log.e("ScreenShotService", "unregisterCallback: %s", (Object) e.toString());
            }
        }
    };

    private void quitThumbnail() {
        int beginBroadcast = this.mListenerList.beginBroadcast();
        Log.d("ScreenShotService", "quitThumbnail count: " + beginBroadcast);
        for (int i = 0; i < beginBroadcast; i++) {
            IScreenShotCallback broadcastItem = this.mListenerList.getBroadcastItem(i);
            if (broadcastItem != null) {
                try {
                    broadcastItem.quitThumnail();
                } catch (RemoteException e) {
                    Log.d("ScreenShotService", "quitThumbnail: %s", (Object) e.toString());
                }
            }
        }
        this.mListenerList.finishBroadcast();
    }

    public IBinder onBind(Intent intent) {
        return this.mStub;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null && intent.getBooleanExtra("quit_thumnail", false)) {
            quitThumbnail();
            stopSelf();
        }
        return super.onStartCommand(intent, i, i2);
    }
}
