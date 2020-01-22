package com.miui.gallery.search;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.miui.gallery.R;
import com.miui.gallery.activity.BaseActivity;
import com.miui.gallery.search.transitions.SearchTransitionHelperImp;

public class SearchActivity extends BaseActivity {
    SearchFragment mSearchFragment;

    /* access modifiers changed from: protected */
    public int getFragmentContainerId() {
        return 16908290;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mSearchFragment.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.mSearchFragment.onBackPressed()) {
            super.onBackPressed();
            if (!SearchTransitionHelperImp.supportSharedElementTransition()) {
                overridePendingTransition(R.anim.appear, R.anim.disappear);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r4v12, types: [com.miui.gallery.search.SearchFragment, android.app.Fragment] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mSearchFragment = (SearchFragment) getFragmentManager().findFragmentByTag("RootFragment");
        boolean z = false;
        if (this.mSearchFragment == null) {
            this.mSearchFragment = new SearchFragment();
            startFragment(this.mSearchFragment, "RootFragment", false, true);
        }
        if (getIntent() != null && getIntent().getBooleanExtra("usingSpecialAnimation", false)) {
            z = true;
        }
        if (z && SearchTransitionHelperImp.supportSharedElementTransition()) {
            ActivityCompat.setEnterSharedElementCallback(this, this.mSearchFragment.getSharedElementCallback(true));
            ActivityCompat.postponeEnterTransition(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        SearchFragment searchFragment = this.mSearchFragment;
        if (searchFragment != null) {
            searchFragment.onNewIntent(intent);
        }
    }
}
