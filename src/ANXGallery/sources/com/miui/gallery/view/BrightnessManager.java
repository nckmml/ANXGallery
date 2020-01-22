package com.miui.gallery.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import com.miui.gallery.util.BrightnessProvider;
import com.miui.gallery.util.BrightnessUtils;
import com.miui.gallery.util.Log;
import java.lang.ref.WeakReference;

public class BrightnessManager implements BrightnessProvider {
    private static final Uri BRIGHTNESS_ADJ_URI = Settings.System.getUriFor("screen_auto_brightness_adj");
    private static final Uri BRIGHTNESS_URI = Settings.System.getUriFor("screen_brightness");
    /* access modifiers changed from: private */
    public WeakReference<Activity> mActivityRef;
    /* access modifiers changed from: private */
    public int mBrightMode;
    BrightnessAsynTask mBrightnessAsynTask;
    private ContentObserver mBrightnessObserver;
    /* access modifiers changed from: private */
    public float mCurrentBrightnessAuto = 0.0f;
    /* access modifiers changed from: private */
    public float mCurrentBrightnessManual;
    private boolean mFocus;
    /* access modifiers changed from: private */
    public boolean mSystemBrightnessChanged;

    class BrightnessAsynTask extends AsyncTask<Void, Void, Float> {
        BrightnessAsynTask() {
        }

        /* access modifiers changed from: protected */
        public Float doInBackground(Void... voidArr) {
            try {
                Activity activity = (Activity) BrightnessManager.this.mActivityRef.get();
                if (activity != null) {
                    int unused = BrightnessManager.this.mBrightMode = Settings.System.getInt(activity.getContentResolver(), "screen_brightness_mode");
                    if (BrightnessManager.this.mBrightMode == 1) {
                        if (BrightnessManager.this.mCurrentBrightnessManual == -1.0f) {
                            return Float.valueOf(BrightnessManager.this.mCurrentBrightnessAuto);
                        }
                        if (BrightnessManager.this.mCurrentBrightnessAuto != 0.0f) {
                            return Float.valueOf(BrightnessManager.this.mCurrentBrightnessAuto);
                        }
                        int unused2 = BrightnessManager.this.mBrightMode = 0;
                        return Float.valueOf(BrightnessManager.this.mCurrentBrightnessManual);
                    } else if (BrightnessManager.this.mBrightMode == 0) {
                        return Float.valueOf(BrightnessManager.this.mCurrentBrightnessManual);
                    }
                }
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return Float.valueOf(-1.0f);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Float f) {
            super.onPostExecute(f);
            if (!isCancelled()) {
                BrightnessManager brightnessManager = BrightnessManager.this;
                brightnessManager.adjustBrightnessTask(brightnessManager.mSystemBrightnessChanged, f.floatValue(), BrightnessManager.this.mBrightMode);
            }
        }
    }

    public BrightnessManager(Activity activity, float f, float f2) {
        float f3 = -1.0f;
        this.mCurrentBrightnessManual = -1.0f;
        this.mBrightMode = -1;
        this.mSystemBrightnessChanged = false;
        this.mFocus = true;
        this.mBrightnessObserver = new ContentObserver(new Handler()) {
            public void onChange(boolean z) {
                super.onChange(z);
                if (BrightnessManager.this.getActivity() != null && !z) {
                    boolean unused = BrightnessManager.this.mSystemBrightnessChanged = true;
                    BrightnessManager.this.adjustBrightness();
                }
            }
        };
        this.mCurrentBrightnessAuto = f2 >= 0.0f ? ensureBrightness(f2) : 0.0f;
        this.mCurrentBrightnessManual = f > 0.0f ? ensureBrightness(f) : f3;
        this.mActivityRef = new WeakReference<>(activity);
    }

    /* access modifiers changed from: private */
    public void adjustBrightness() {
        BrightnessAsynTask brightnessAsynTask = this.mBrightnessAsynTask;
        if (brightnessAsynTask != null) {
            brightnessAsynTask.cancel(true);
        }
        this.mBrightnessAsynTask = new BrightnessAsynTask();
        this.mBrightnessAsynTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    private float ensureBrightness(float f) {
        return Math.max(0.0f, Math.min(f, 1.0f));
    }

    /* access modifiers changed from: private */
    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.mActivityRef;
        if (weakReference != null) {
            return (Activity) weakReference.get();
        }
        return null;
    }

    private void registerObserver() {
        Activity activity = getActivity();
        if (activity != null) {
            try {
                ContentResolver contentResolver = activity.getContentResolver();
                contentResolver.registerContentObserver(BRIGHTNESS_URI, true, this.mBrightnessObserver);
                contentResolver.registerContentObserver(BRIGHTNESS_ADJ_URI, true, this.mBrightnessObserver);
            } catch (Exception e) {
                Log.e("BrightnessManager", "Register BrightnessObserver error: %s" + e);
            }
        }
    }

    private void unregisterObserver() {
        Activity activity = getActivity();
        if (activity != null) {
            try {
                activity.getContentResolver().unregisterContentObserver(this.mBrightnessObserver);
            } catch (Exception e) {
                Log.e("BrightnessManager", "Unregister BrightnessObserver error: %s" + e);
            }
        }
    }

    public void adjustBrightnessTask(boolean z, float f, int i) {
        Activity activity = getActivity();
        if (activity != null) {
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            if (i == 1) {
                if (this.mFocus) {
                    setTemporaryScreenAutoBrightnessAdjustmentRatio(f);
                } else {
                    setTemporaryScreenAutoBrightnessAdjustmentRatio(0.0f);
                }
            } else if (i == 0) {
                float f2 = -1.0f;
                if (this.mFocus) {
                    if (!z) {
                        f2 = this.mCurrentBrightnessManual;
                    }
                    attributes.screenBrightness = f2;
                } else {
                    attributes.screenBrightness = -1.0f;
                }
                activity.getWindow().setAttributes(attributes);
            }
        }
    }

    public float getAutoBrightness() {
        return this.mCurrentBrightnessAuto;
    }

    public float getManualBrightness() {
        if (this.mSystemBrightnessChanged) {
            return -1.0f;
        }
        return this.mCurrentBrightnessManual;
    }

    public void onPause() {
        adjustBrightness();
        unregisterObserver();
    }

    public void onResume() {
        registerObserver();
        adjustBrightness();
    }

    public void onWindowFocusChanged(boolean z) {
        this.mFocus = z;
        adjustBrightness();
    }

    public void setTemporaryScreenAutoBrightnessAdjustmentRatio(float f) {
        BrightnessUtils.setTemporaryAutoBrightness(getActivity(), f);
    }
}
