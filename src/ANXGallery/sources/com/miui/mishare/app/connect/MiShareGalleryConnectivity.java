package com.miui.mishare.app.connect;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.miui.mishare.IMiShareDiscoverCallback;
import com.miui.mishare.IMiShareService;
import com.miui.mishare.IMiShareStateListener;
import com.miui.mishare.IMiShareTaskStateListener;
import com.miui.mishare.MiShareTask;
import java.util.ArrayList;
import java.util.List;

public class MiShareGalleryConnectivity {
    private static MiShareGalleryConnectivity sInstance;
    /* access modifiers changed from: private */
    public ServiceBindCallBack mCallback;
    private final ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMiShareService unused = MiShareGalleryConnectivity.this.mService = IMiShareService.Stub.asInterface(iBinder);
            if (MiShareGalleryConnectivity.this.mCallback != null) {
                MiShareGalleryConnectivity.this.mCallback.onServiceBound();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            IMiShareService unused = MiShareGalleryConnectivity.this.mService = null;
        }
    };
    private Context mContext;
    private boolean mIsBound;
    /* access modifiers changed from: private */
    public IMiShareService mService;

    public interface ServiceBindCallBack {
        void onServiceBound();
    }

    private MiShareGalleryConnectivity(Context context) {
        this.mContext = context;
    }

    private void ensureServiceBound() {
        if (this.mService == null) {
            throw new NullPointerException("service not bound");
        }
    }

    private ClipData getClipData(List<Uri> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ClipData clipData = new ClipData(new ClipDescription("mishare data", new String[]{""}), new ClipData.Item(list.get(0)));
        int size = list.size();
        for (int i = 1; i < size; i++) {
            Uri uri = list.get(i);
            if (uri != null) {
                clipData.addItem(new ClipData.Item(uri));
            }
        }
        return clipData;
    }

    public static MiShareGalleryConnectivity getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MiShareGalleryConnectivity(context.getApplicationContext());
        }
        return sInstance;
    }

    public static boolean isAvailable(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.miui.mishare.connectivity", "com.miui.mishare.connectivity.MiShareService");
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.queryIntentServices(intent, 0).size() > 0;
    }

    public boolean bind(ServiceBindCallBack serviceBindCallBack) {
        this.mCallback = serviceBindCallBack;
        Intent intent = new Intent();
        intent.setClassName("com.miui.mishare.connectivity", "com.miui.mishare.connectivity.MiShareService");
        this.mIsBound = this.mContext.bindService(intent, this.mConnection, 1);
        return this.mIsBound;
    }

    public void cancel(MiShareTask miShareTask) {
        IMiShareService iMiShareService = this.mService;
        if (iMiShareService != null) {
            try {
                iMiShareService.cancel(miShareTask);
            } catch (RemoteException e) {
                Log.e("MiShareConnectivity", "cancel: ", e);
            }
        }
    }

    public boolean checkServiceBound() {
        return this.mService != null;
    }

    public int getServiceState() {
        IMiShareService iMiShareService = this.mService;
        if (iMiShareService == null) {
            return 0;
        }
        try {
            return iMiShareService.getState();
        } catch (RemoteException e) {
            Log.e("MiShareConnectivity", "getServiceState: ", e);
            return 0;
        }
    }

    public void registerStateListener(IMiShareStateListener iMiShareStateListener) {
        if (iMiShareStateListener != null) {
            ensureServiceBound();
            try {
                this.mService.registerStateListener(iMiShareStateListener);
            } catch (RemoteException e) {
                Log.e("MiShareConnectivity", "registerStateListener: ", e);
            }
        } else {
            throw new NullPointerException("null listener");
        }
    }

    public void registerTaskStateListener(IMiShareTaskStateListener iMiShareTaskStateListener) {
        ensureServiceBound();
        try {
            this.mService.registerTaskStateListener(iMiShareTaskStateListener);
        } catch (RemoteException e) {
            Log.e("MiShareConnectivity", "registerTaskStateListener: ", e);
        }
    }

    public void sendData(MiShareTask miShareTask) {
        Uri uri;
        if (miShareTask != null) {
            ensureServiceBound();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < miShareTask.clipData.getItemCount(); i++) {
                if (!(miShareTask.clipData.getItemAt(i) == null || (uri = miShareTask.clipData.getItemAt(i).getUri()) == null || !uri.getScheme().startsWith("content"))) {
                    arrayList.add(uri);
                }
            }
            Intent intent = new Intent("com.miui.mishare.action.SEND_TASK");
            intent.setClassName("com.miui.mishare.connectivity", "com.miui.mishare.connectivity.MiShareService");
            if (arrayList.size() > 0) {
                intent.setClipData(getClipData(arrayList));
                intent.addFlags(-2147483647);
            }
            intent.putExtra("task", miShareTask);
            this.mContext.startService(intent);
            return;
        }
        throw new NullPointerException("null task");
    }

    public void startDiscover(IMiShareDiscoverCallback iMiShareDiscoverCallback) {
        ensureServiceBound();
        try {
            this.mService.discover(iMiShareDiscoverCallback);
        } catch (RemoteException e) {
            Log.e("MiShareConnectivity", "startDiscover: ", e);
        }
    }

    public void startDiscoverWithIntent(IMiShareDiscoverCallback iMiShareDiscoverCallback, Intent intent) {
        ensureServiceBound();
        try {
            this.mService.discoverWithIntent(iMiShareDiscoverCallback, intent);
        } catch (RemoteException e) {
            Log.e("MiShareConnectivity", "startDiscoverWithConfig: ", e);
        }
    }

    public void stopDiscover(IMiShareDiscoverCallback iMiShareDiscoverCallback) {
        ensureServiceBound();
        try {
            this.mService.stopDiscover(iMiShareDiscoverCallback);
        } catch (RemoteException e) {
            Log.e("MiShareConnectivity", "startDiscover: ", e);
        }
    }

    public void unbind() {
        if (this.mIsBound) {
            this.mCallback = null;
            if (this.mService != null) {
                this.mContext.unbindService(this.mConnection);
            }
            this.mIsBound = false;
        }
    }

    public void unregisterStateListener(IMiShareStateListener iMiShareStateListener) {
        if (iMiShareStateListener != null) {
            ensureServiceBound();
            try {
                this.mService.unregisterStateListener(iMiShareStateListener);
            } catch (RemoteException e) {
                Log.e("MiShareConnectivity", "unregisterStateListener: ", e);
            }
        } else {
            throw new NullPointerException("null listener");
        }
    }

    public void unregisterTaskStateListener(IMiShareTaskStateListener iMiShareTaskStateListener) {
        ensureServiceBound();
        try {
            this.mService.unregisterTaskStateListener(iMiShareTaskStateListener);
        } catch (RemoteException e) {
            Log.e("MiShareConnectivity", "unregisterTaskStateListener: ", e);
        }
    }
}
