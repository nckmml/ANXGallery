package com.miui.gallery.activity;

import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.ui.RawPhotoPickFragment;
import com.miui.gallery.ui.ScreenshotPhotoPickFragment;
import com.miui.gallery.ui.SimilarPhotoPickFragment;
import com.miui.gallery.ui.SlimPhotoPickFragment;
import com.miui.gallery.ui.VideoResultPickFragment;

public class CleanerPhotoPickActivity extends BaseActivity {
    /* JADX WARNING: type inference failed for: r4v3, types: [com.miui.gallery.ui.SlimPhotoPickFragment, android.app.Fragment] */
    /* JADX WARNING: type inference failed for: r4v6, types: [android.app.Fragment, com.miui.gallery.ui.ScreenshotPhotoPickFragment] */
    /* JADX WARNING: type inference failed for: r4v9, types: [com.miui.gallery.ui.VideoResultPickFragment, android.app.Fragment] */
    /* JADX WARNING: type inference failed for: r4v12, types: [android.app.Fragment, com.miui.gallery.ui.SimilarPhotoPickFragment] */
    /* JADX WARNING: type inference failed for: r4v15, types: [com.miui.gallery.ui.RawPhotoPickFragment, android.app.Fragment] */
    private void startFragmentByType(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4 && getFragmentManager().findFragmentByTag("RawPhotoPickFragment") == null) {
                            startFragment(new RawPhotoPickFragment(), "RawPhotoPickFragment", false, true);
                        }
                    } else if (getFragmentManager().findFragmentByTag("SimilarPhotoPickFragment") == null) {
                        startFragment(new SimilarPhotoPickFragment(), "SimilarPhotoPickFragment", false, true);
                    }
                } else if (getFragmentManager().findFragmentByTag("VideoResultPickFragment") == null) {
                    startFragment(new VideoResultPickFragment(), "VideoResultPickFragment", false, true);
                }
            } else if (getFragmentManager().findFragmentByTag("ScreenshotPhotoPickFragment") == null) {
                startFragment(new ScreenshotPhotoPickFragment(), "ScreenshotPhotoPickFragment", false, true);
            }
        } else if (getFragmentManager().findFragmentByTag("SlimPhotoPickFragment") == null) {
            startFragment(new SlimPhotoPickFragment(), "SlimPhotoPickFragment", false, true);
        }
    }

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        startFragmentByType(getIntent().getIntExtra("extra_cleaner_photo_pick_type", -1));
        this.mActionBar.setCustomView(R.layout.cleaner_photo_pick_action_layout);
        this.mActionBar.setDisplayShowCustomEnabled(true);
    }
}
