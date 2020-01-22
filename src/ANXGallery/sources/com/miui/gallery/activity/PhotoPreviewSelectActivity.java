package com.miui.gallery.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.android.internal.WindowCompat;
import com.miui.gallery.ui.PhotoPreviewSelectFragment;
import com.miui.gallery.util.SystemUiUtil;

public class PhotoPreviewSelectActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.activity.PhotoPreviewSelectActivity, android.app.Activity] */
    public void onAttachedToWindow() {
        if (WindowCompat.isNotch(this)) {
            SystemUiUtil.extendToStatusBar(getWindow().getDecorView());
        }
        SystemUiUtil.hideSystemBars(getWindow().getDecorView());
    }

    /* JADX WARNING: type inference failed for: r4v4, types: [com.miui.gallery.ui.PhotoPreviewSelectFragment, android.app.Fragment] */
    public void onCreate(Bundle bundle) {
        Uri data;
        super.onCreate(bundle);
        WindowCompat.setCutoutModeShortEdges(getWindow());
        Intent intent = getIntent();
        if (intent == null || (data = intent.getData()) == null) {
            finish();
        } else {
            startFragment(PhotoPreviewSelectFragment.newInstance(data, intent.getExtras()), "PhotoPreviewSelectFragment", false, true);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        SystemUiUtil.hideSystemBars(getWindow().getDecorView());
    }
}
