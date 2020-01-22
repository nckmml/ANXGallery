package com.miui.gallery.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.ui.PhotoPageFragment;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.photoview.PhotoPageDataCache;
import com.nostra13.universalimageloader.core.ImageLoader;

public class InternalPhotoPageActivity extends BaseActivity {
    private void repackageExtras() {
        Bundle arguments = PhotoPageDataCache.getInstance().getArguments();
        if (arguments != null) {
            Intent intent = getIntent();
            intent.setData((Uri) arguments.getParcelable("photo_data"));
            intent.putExtras(arguments);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    public RuntimePermission[] getRuntimePermissions() {
        return null;
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

    /* JADX WARNING: type inference failed for: r4v3, types: [android.app.Fragment, com.miui.gallery.ui.PhotoPageFragment] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            PhotoPageDataCache.getInstance().restoreInstance(bundle);
        }
        Intent intent = getIntent();
        repackageExtras();
        if (intent.getData() == null) {
            Log.e("InternalPhotoPageActivity", "uri shouldn't be null");
            finish();
            return;
        }
        startFragment(PhotoPageFragment.newInstance(intent.getData(), intent.getType(), intent.getExtras(), 1), "PhotoPageFragment", false, true);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        PhotoPageDataCache.getInstance().clear();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ImageLoader.getInstance().pause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ImageLoader.getInstance().resume();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        PhotoPageDataCache.getInstance().saveInstance(bundle);
    }
}
