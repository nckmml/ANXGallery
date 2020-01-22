package com.miui.gallery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.gallery.ui.AlbumDetailFragment;
import com.miui.gallery.ui.JumpDialogFragment;

public class AlbumDetailActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public AlbumDetailFragment mAlbumDetailFragment;

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        AlbumDetailFragment albumDetailFragment = this.mAlbumDetailFragment;
        if (albumDetailFragment != null) {
            albumDetailFragment.onActivityResult(i, i2, intent);
        }
    }

    public void onBackPressed() {
        AlbumDetailFragment albumDetailFragment = this.mAlbumDetailFragment;
        if (albumDetailFragment != null && !albumDetailFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /* JADX WARNING: type inference failed for: r4v3, types: [android.app.Fragment, com.miui.gallery.ui.AlbumDetailFragment] */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            JumpDialogFragment.obtainAlbumIntent(this, intent.getData(), new JumpDialogFragment.HandleIntentCallback() {
                public void onHandleFailed(Context context, String str) {
                    AlbumDetailActivity.this.finish();
                }

                /* JADX WARNING: type inference failed for: r0v2, types: [android.app.Fragment, com.miui.gallery.ui.AlbumDetailFragment] */
                public void onHandleIntent(Intent intent) {
                    AlbumDetailActivity.this.setIntent(intent);
                    AlbumDetailFragment unused = AlbumDetailActivity.this.mAlbumDetailFragment = new AlbumDetailFragment();
                    AlbumDetailActivity albumDetailActivity = AlbumDetailActivity.this;
                    albumDetailActivity.startFragment(albumDetailActivity.mAlbumDetailFragment, "albumDetail", false, true);
                }
            });
            return;
        }
        this.mAlbumDetailFragment = new AlbumDetailFragment();
        startFragment(this.mAlbumDetailFragment, "albumDetail", false, true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        AlbumDetailFragment albumDetailFragment = this.mAlbumDetailFragment;
        if (albumDetailFragment == null || !albumDetailFragment.isVisible()) {
            return false;
        }
        this.mAlbumDetailFragment.onCreateOptionsMenu(menu, getMenuInflater());
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        AlbumDetailFragment albumDetailFragment = this.mAlbumDetailFragment;
        if (albumDetailFragment == null || !albumDetailFragment.isVisible() || !this.mAlbumDetailFragment.onOptionsItemSelected(menuItem)) {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        AlbumDetailFragment albumDetailFragment = this.mAlbumDetailFragment;
        if (albumDetailFragment == null || !albumDetailFragment.isVisible()) {
            return super.onPrepareOptionsMenu(menu);
        }
        this.mAlbumDetailFragment.onPrepareOptionsMenu(menu);
        return true;
    }
}
