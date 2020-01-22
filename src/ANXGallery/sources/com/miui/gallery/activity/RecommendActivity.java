package com.miui.gallery.activity;

import android.content.Intent;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.ui.RecommendFragment;
import miui.app.Fragment;

public class RecommendActivity extends BaseActivity {
    private Fragment mFragment;

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return R.id.content;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mFragment.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.recommend_activity);
        setTitle(R.string.assistant_page_discovery);
        this.mFragment = getFragmentManager().findFragmentByTag("RootFragment");
        if (this.mFragment == null) {
            this.mFragment = new RecommendFragment();
        }
        startFragment(this.mFragment, "RootFragment", false, false);
    }
}
