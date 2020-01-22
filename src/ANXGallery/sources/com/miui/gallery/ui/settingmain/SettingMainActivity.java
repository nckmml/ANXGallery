package com.miui.gallery.ui.settingmain;

import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.base_optimization.mvp.view.BaseToolBarActivity;
import com.miui.gallery.ui.album.cloudalbum.CloudAlbumListFragment;
import com.miui.gallery.ui.album.hiddenalbum.HiddenAlbumFragment;

public class SettingMainActivity extends BaseToolBarActivity {
    /* JADX WARNING: type inference failed for: r0v3, types: [android.app.Fragment, com.miui.gallery.ui.album.hiddenalbum.HiddenAlbumFragment] */
    /* JADX WARNING: type inference failed for: r0v5, types: [android.app.Fragment, com.miui.gallery.ui.album.cloudalbum.CloudAlbumListFragment] */
    private void loadFragment() {
        int intExtra = getIntent().getIntExtra("extra_to_type", -1);
        if (intExtra == 1001) {
            setTitle(R.string.album_hidden_page_title);
            loadRootFragment(R.id.flSettingMain, HiddenAlbumFragment.newInstance());
        } else if (intExtra != 1002) {
            finish();
        } else {
            setTitle(R.string.album_cloud_list_page_title);
            loadRootFragment(R.id.flSettingMain, CloudAlbumListFragment.newInstance());
        }
    }

    public int getLayoutId() {
        return R.layout.activity_setting_main;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadFragment();
    }
}
