package com.miui.gallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.gallery.R;
import com.miui.gallery.app.base.BaseActivity;
import com.miui.gallery.ui.photodetail.PhotoDetailFragment;

public class PhotoDetailActivity extends BaseActivity {
    private PhotoDetailFragment mPhotoDetailFragment;

    public int getLayoutId() {
        return R.layout.photo_detail_activity;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mPhotoDetailFragment.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.mPhotoDetailFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.app.Fragment, com.miui.gallery.ui.photodetail.PhotoDetailFragment] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ? newInstance = PhotoDetailFragment.newInstance();
        this.mPhotoDetailFragment = newInstance;
        loadRootFragment(R.id.frame_photodetail_main, newInstance);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.mPhotoDetailFragment.onCreateOptionsMenu(menu, getMenuInflater());
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mPhotoDetailFragment.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        this.mPhotoDetailFragment.onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public boolean supportShowOnScreenLocked() {
        return true;
    }
}
