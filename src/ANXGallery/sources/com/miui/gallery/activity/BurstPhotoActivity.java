package com.miui.gallery.activity;

import android.content.Intent;
import android.os.Bundle;
import com.miui.gallery.compat.app.ActivityCompat;
import com.miui.gallery.ui.BurstPhotoFragment;

public class BurstPhotoActivity extends BaseActivity {
    private BurstPhotoFragment mFragment;

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    public void onBackPressed() {
        if (!this.mFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [android.app.Fragment, com.miui.gallery.ui.BurstPhotoFragment] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null) {
            finish();
            return;
        }
        this.mFragment = BurstPhotoFragment.newInstance(intent.getData(), intent.getExtras());
        startFragment(this.mFragment, "BurstPhotoFragment", false, true);
        if (intent.getBooleanExtra("has_transition", false)) {
            ActivityCompat.startPostponedEnterTransition(this);
        }
    }
}
