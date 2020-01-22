package com.miui.gallery.activity;

import android.content.ContentUris;
import android.content.Intent;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.display.DisplayFeatureHelper;
import com.miui.gallery.Config;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.model.ImageLoadParams;
import com.miui.gallery.permission.core.PermissionUtils;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.ui.PhotoPageFragment;
import com.miui.gallery.util.BrightnessProvider;
import com.miui.gallery.util.FeatureUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaStoreUtils;
import com.miui.gallery.util.ProcessingMediaHelper;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.view.BrightnessManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;

public class ExternalPhotoPageActivity extends BaseActivity implements BrightnessProvider {
    private BrightnessManager mBrightnessManager;
    private boolean mSupportWindowAnim;

    private static ImageSize getImageSize(boolean z) {
        if (!FeatureUtil.isSupportFastBlur()) {
            z = false;
        }
        return z ? Config.ThumbConfig.get().sMicroTargetSize : new ImageSize(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
    }

    private boolean isCameraPreview() {
        Intent intent = getIntent();
        return intent != null && intent.getBooleanExtra("from_MiuiCamera", false);
    }

    public static void preloadThumbnail(String str) {
        preloadThumbnail(str, ProcessingMediaHelper.getInstance().isMediaInProcessing(str));
    }

    public static void preloadThumbnail(String str, boolean z) {
        if (!FeatureUtil.isSupportFastBlur()) {
            z = false;
        }
        if (!PermissionUtils.checkPermission(GalleryApp.sGetAndroidContext(), "android.permission.WRITE_EXTERNAL_STORAGE")) {
            Log.w("ExternalPhotoPageActivity", "Can't access external storage, relate permission is ungranted");
            return;
        }
        ImageSize imageSize = getImageSize(z);
        DisplayImageOptions.Builder cloneFrom = new DisplayImageOptions.Builder().cloneFrom(Config.ThumbConfig.THUMBNAIL_DISPLAY_IMAGE_OPTIONS_DEFAULT);
        cloneFrom.loadFromBigPhotoCache(true).delayCacheThumbnail(true).cacheInMemory(true).highPriority(true);
        DisplayImageOptions build = cloneFrom.build();
        if (z) {
            build = Config.ThumbConfig.appendBlurOptions(build);
        }
        NonViewAware nonViewAware = new NonViewAware(imageSize, ViewScaleType.FIT_INSIDE);
        ImageLoader.getInstance().resume();
        ImageLoader.getInstance().displayImage(str, (ImageAware) nonViewAware, build);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.miui.gallery.activity.ExternalPhotoPageActivity, android.app.Activity] */
    private void prepareIfFromCamera() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getExtras() != null) {
            Bundle extras = intent.getExtras();
            if (isCameraPreview()) {
                int integer = getResources().getInteger(R.integer.photo_page_anim_support_width);
                int integer2 = getResources().getInteger(R.integer.photo_page_anim_support_height);
                if (ScreenUtils.getScreenWidth() == integer && Math.abs(ScreenUtils.getScreenHeight() - integer2) <= 5 && getResources().getConfiguration().orientation == 1) {
                    getWindow().setWindowAnimations(2131820549);
                    this.mSupportWindowAnim = true;
                }
                Uri data = intent.getData();
                String uri = data.toString();
                intent.putExtra("photo_count", 1);
                intent.putExtra("photo_init_position", 0);
                String str = uri;
                intent.putExtra("photo_transition_data", new ImageLoadParams(ContentUris.parseId(data), str, getImageSize(ProcessingMediaHelper.getInstance().isMediaInProcessing(uri)), (RectF) null, 0, MediaStoreUtils.getMineTypeFromUri(data), -1));
                preloadThumbnail(uri);
                String str2 = "camera-brightness-manual";
                if (extras.getInt(str2, -1) == -1) {
                    str2 = "camera-brightness";
                }
                this.mBrightnessManager = new BrightnessManager(this, (((float) extras.getInt(str2, -1)) * 1.0f) / 255.0f, extras.getFloat("camera-brightness-auto", -1.0f));
            }
        }
    }

    private void setScreenEffect(boolean z) {
        DisplayFeatureHelper.setScreenEffect(z);
    }

    private void startMainActivity() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setPackage(getPackageName());
        intent.addFlags(67108864);
        startActivity(intent);
    }

    public void finish() {
        if (Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask();
        } else {
            super.finish();
        }
        if (this.mSupportWindowAnim) {
            overridePendingTransition(R.anim.photo_page_close_enter, R.anim.photo_page_close_exit);
        }
    }

    public float getAutoBrightness() {
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager == null) {
            return -1.0f;
        }
        return brightnessManager.getAutoBrightness();
    }

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    public float getManualBrightness() {
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager == null) {
            return -1.0f;
        }
        return brightnessManager.getManualBrightness();
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    public void onActivityReenter(int i, Intent intent) {
        PhotoPageFragment photoPageFragment = (PhotoPageFragment) getFragmentManager().findFragmentByTag("PhotoPageFragment");
        if (photoPageFragment != null) {
            photoPageFragment.onActivityReenter(i, intent);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        PhotoPageFragment photoPageFragment;
        super.onActivityResult(i, i2, intent);
        if (getFragmentManager().getBackStackEntryCount() == 0 && (photoPageFragment = (PhotoPageFragment) getFragmentManager().findFragmentByTag("PhotoPageFragment")) != null) {
            photoPageFragment.onActivityResult(i, i2, intent);
        }
    }

    public void onBackPressed() {
        PhotoPageFragment photoPageFragment;
        if (getFragmentManager().getBackStackEntryCount() != 0 || (photoPageFragment = (PhotoPageFragment) getFragmentManager().findFragmentByTag("PhotoPageFragment")) == null || !photoPageFragment.isVisible() || !photoPageFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.miui.gallery.activity.BaseActivity, com.miui.gallery.activity.ExternalPhotoPageActivity, android.app.Activity] */
    public void onCreate(Bundle bundle) {
        prepareIfFromCamera();
        super.onCreate(bundle);
        String callingPackage = IntentUtil.getCallingPackage(this);
        if (TextUtils.isEmpty(callingPackage)) {
            callingPackage = "Unknown";
        }
        GallerySamplingStatHelper.recordStringPropertyEvent("photo", "external_view_photo", callingPackage);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ImageLoader.getInstance().pause();
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager != null) {
            brightnessManager.onPause();
        }
        if (isCameraPreview()) {
            setScreenEffect(false);
        }
    }

    /* JADX WARNING: type inference failed for: r4v3, types: [android.app.Fragment, com.miui.gallery.ui.PhotoPageFragment] */
    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        Uri data;
        super.onPermissionsChecked(runtimePermissionArr, iArr);
        Intent intent = getIntent();
        if (intent == null || (data = intent.getData()) == null) {
            finish();
            startMainActivity();
        } else if (((PhotoPageFragment) getFragmentManager().findFragmentByTag("PhotoPageFragment")) == null) {
            startFragment(PhotoPageFragment.newInstance(data, intent.getType(), intent.getExtras(), 0), "PhotoPageFragment", false, true);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ImageLoader.getInstance().resume();
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager != null) {
            brightnessManager.onResume();
        }
        if (isCameraPreview()) {
            setScreenEffect(true);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        BrightnessManager brightnessManager = this.mBrightnessManager;
        if (brightnessManager != null) {
            brightnessManager.onWindowFocusChanged(z);
        }
    }
}
