package com.miui.extraphoto.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.miui.extraphoto.sdk.IExtraPhotoService;
import com.miui.gallery.R;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ExtraPhotoWrapper implements ServiceConnection {
    private static ExtraPhotoWrapper sWrapper;
    /* access modifiers changed from: private */
    public IExtraPhotoService mDualService;
    /* access modifiers changed from: private */
    public boolean mHasBindService;
    /* access modifiers changed from: private */
    public StartCallback mStartCallback;

    /* renamed from: com.miui.extraphoto.sdk.ExtraPhotoWrapper$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$extraphoto$sdk$ExtraFunc = new int[ExtraFunc.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$extraphoto$sdk$ExtraFunc[ExtraFunc.FUNC_FANCYCOLOR.ordinal()] = 1;
            $SwitchMap$com$miui$extraphoto$sdk$ExtraFunc[ExtraFunc.FUNC_REFOCUS.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$extraphoto$sdk$ExtraFunc[ExtraFunc.FUNC_FREEVIEW.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public interface StartCallback {
        void onStarted(boolean z);
    }

    private ExtraPhotoWrapper() {
    }

    private void bindService(final Context context) {
        ThreadManager.getWorkHandler().post(new Runnable() {
            public void run() {
                if (ExtraPhotoWrapper.this.mHasBindService) {
                    Log.e("ExtraPhotoWrapper", "has bind service");
                    return;
                }
                try {
                    Intent intent = new Intent("com.miui.extraphoto.action.EXTRA_PHOTO");
                    intent.setPackage("com.miui.extraphoto");
                    boolean bindService = context.bindService(intent, ExtraPhotoWrapper.this, 1);
                    boolean unused = ExtraPhotoWrapper.this.mHasBindService = true;
                    Log.i("ExtraPhotoWrapper", "bind %s", (Object) Boolean.valueOf(bindService));
                    if (!bindService) {
                        ExtraPhotoWrapper.this.callbackStarted(false);
                    }
                } catch (Exception e) {
                    Log.e("ExtraPhotoWrapper", (Throwable) e);
                    ExtraPhotoWrapper.this.callbackStarted(false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void callbackStarted(final boolean z) {
        AnonymousClass3 r0 = new Runnable() {
            public void run() {
                if (ExtraPhotoWrapper.this.mDualService != null) {
                    try {
                        ExtraPhotoWrapper.this.mDualService.resumeEcho();
                    } catch (RemoteException e) {
                        Log.e("ExtraPhotoWrapper", (Throwable) e);
                    } catch (Exception e2) {
                        Log.e("ExtraPhotoWrapper", (Throwable) e2);
                    }
                }
                if (ExtraPhotoWrapper.this.mStartCallback != null) {
                    ExtraPhotoWrapper.this.mStartCallback.onStarted(z);
                    StartCallback unused = ExtraPhotoWrapper.this.mStartCallback = null;
                }
            }
        };
        if (Looper.getMainLooper() == Looper.myLooper()) {
            r0.run();
        } else {
            ThreadManager.getMainHandler().post(r0);
        }
    }

    public static ExtraPhotoWrapper getInstance() {
        if (sWrapper == null) {
            sWrapper = new ExtraPhotoWrapper();
        }
        return sWrapper;
    }

    private void init(Context context, StartCallback startCallback) {
        if (this.mDualService != null) {
            Log.e("ExtraPhotoWrapper", "service has been initialized");
            return;
        }
        this.mStartCallback = startCallback;
        bindService(context);
    }

    private void releaseEcho() {
        Log.i("ExtraPhotoWrapper", "releaseEcho");
        IExtraPhotoService iExtraPhotoService = this.mDualService;
        if (iExtraPhotoService != null) {
            try {
                iExtraPhotoService.releaseEcho();
            } catch (RemoteException e) {
                Log.e("ExtraPhotoWrapper", (Throwable) e);
            } catch (Exception e2) {
                Log.e("ExtraPhotoWrapper", (Throwable) e2);
            }
        }
    }

    private int transFunc2Id(ExtraFunc extraFunc) {
        int i = AnonymousClass4.$SwitchMap$com$miui$extraphoto$sdk$ExtraFunc[extraFunc.ordinal()];
        if (i == 1) {
            return R.id.fancycolor_enter;
        }
        if (i == 2) {
            return R.id.refocus_enter;
        }
        if (i != 3) {
            return 0;
        }
        return R.id.freeview_enter;
    }

    private void unbindService(final Context context) {
        ThreadManager.getWorkHandler().post(new Runnable() {
            public void run() {
                if (!ExtraPhotoWrapper.this.mHasBindService) {
                    Log.e("ExtraPhotoWrapper", "hasn't bind service");
                    return;
                }
                try {
                    context.unbindService(ExtraPhotoWrapper.this);
                    boolean unused = ExtraPhotoWrapper.this.mHasBindService = false;
                    Log.i("ExtraPhotoWrapper", "unbind service");
                } catch (Exception e) {
                    Log.e("ExtraPhotoWrapper", (Throwable) e);
                }
            }
        });
    }

    public boolean echo(String str, BaseEchoListener baseEchoListener, boolean z) {
        Log.i("ExtraPhotoWrapper", "echo %s, %s, %s", str, baseEchoListener, Boolean.valueOf(z));
        IExtraPhotoService iExtraPhotoService = this.mDualService;
        if (iExtraPhotoService == null) {
            return false;
        }
        try {
            return iExtraPhotoService.echo(str, baseEchoListener, z);
        } catch (RemoteException e) {
            Log.e("ExtraPhotoWrapper", (Throwable) e);
            return false;
        } catch (Exception e2) {
            Log.e("ExtraPhotoWrapper", (Throwable) e2);
            return false;
        }
    }

    public boolean isExtraCamera() {
        boolean z;
        IExtraPhotoService iExtraPhotoService = this.mDualService;
        if (iExtraPhotoService != null) {
            try {
                z = iExtraPhotoService.isExtraCamera();
            } catch (RemoteException e) {
                Log.e("ExtraPhotoWrapper", (Throwable) e);
            } catch (Exception e2) {
                Log.e("ExtraPhotoWrapper", (Throwable) e2);
            }
            Log.i("ExtraPhotoWrapper", "isExtraCamera %s", (Object) Boolean.valueOf(z));
            return z;
        }
        z = false;
        Log.i("ExtraPhotoWrapper", "isExtraCamera %s", (Object) Boolean.valueOf(z));
        return z;
    }

    public boolean isExtraPhoto(String str) {
        boolean z;
        IExtraPhotoService iExtraPhotoService = this.mDualService;
        if (iExtraPhotoService != null) {
            try {
                z = iExtraPhotoService.isExtraPhoto(str);
            } catch (RemoteException e) {
                Log.e("ExtraPhotoWrapper", (Throwable) e);
            } catch (Exception e2) {
                Log.e("ExtraPhotoWrapper", (Throwable) e2);
            }
            Log.i("ExtraPhotoWrapper", "isExtraPhoto %s, %s", Boolean.valueOf(z), str);
            return z;
        }
        z = false;
        Log.i("ExtraPhotoWrapper", "isExtraPhoto %s, %s", Boolean.valueOf(z), str);
        return z;
    }

    public boolean needEcho(String str) {
        boolean z;
        IExtraPhotoService iExtraPhotoService = this.mDualService;
        if (iExtraPhotoService != null) {
            try {
                z = iExtraPhotoService.needEcho(str);
            } catch (RemoteException e) {
                Log.e("ExtraPhotoWrapper", (Throwable) e);
            } catch (Exception e2) {
                Log.e("ExtraPhotoWrapper", (Throwable) e2);
            }
            Log.i("ExtraPhotoWrapper", "needEcho %s, %s", Boolean.valueOf(z), str);
            return z;
        }
        z = false;
        Log.i("ExtraPhotoWrapper", "needEcho %s, %s", Boolean.valueOf(z), str);
        return z;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i("ExtraPhotoWrapper", "onServiceConnected %s", (Object) iBinder);
        if (iBinder != null) {
            this.mDualService = IExtraPhotoService.Stub.asInterface(iBinder);
            callbackStarted(true);
            return;
        }
        callbackStarted(false);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.i("ExtraPhotoWrapper", "onServiceDisconnected %s", (Object) componentName);
    }

    public List<Integer> resolveExtraFuncs() {
        ArrayList arrayList = new ArrayList();
        IExtraPhotoService iExtraPhotoService = this.mDualService;
        if (iExtraPhotoService != null) {
            try {
                List<ExtraFunc> resolveExtraFuncs = iExtraPhotoService.resolveExtraFuncs();
                if (resolveExtraFuncs != null) {
                    for (ExtraFunc next : resolveExtraFuncs) {
                        int transFunc2Id = transFunc2Id(next);
                        if (transFunc2Id > 0) {
                            Log.i("ExtraPhotoWrapper", "resolveExtraFuncs %s", (Object) next.name());
                            arrayList.add(Integer.valueOf(transFunc2Id));
                        }
                    }
                }
            } catch (RemoteException e) {
                Log.e("ExtraPhotoWrapper", (Throwable) e);
            } catch (Exception e2) {
                Log.e("ExtraPhotoWrapper", (Throwable) e2);
            }
        }
        return arrayList;
    }

    public void start(Context context, StartCallback startCallback) {
        Log.i("ExtraPhotoWrapper", "resumeEcho");
        init(context, startCallback);
    }

    public void stop(Context context) {
        Log.i("ExtraPhotoWrapper", "pauseEcho");
        releaseEcho();
        this.mStartCallback = null;
        this.mDualService = null;
        unbindService(context);
    }

    public void unregisterEchoListener(BaseEchoListener baseEchoListener) {
        Log.i("ExtraPhotoWrapper", "unregisterEchoListener %s", (Object) baseEchoListener);
        IExtraPhotoService iExtraPhotoService = this.mDualService;
        if (iExtraPhotoService != null) {
            try {
                iExtraPhotoService.unregisterEchoListener(baseEchoListener);
            } catch (RemoteException e) {
                Log.e("ExtraPhotoWrapper", (Throwable) e);
            } catch (Exception e2) {
                Log.e("ExtraPhotoWrapper", (Throwable) e2);
            }
        }
    }
}
