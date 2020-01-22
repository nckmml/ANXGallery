package com.miui.gallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import com.miui.gallery.R;
import com.miui.gallery.cloud.base.SyncRequest;
import com.miui.gallery.cloud.base.SyncType;
import com.miui.gallery.ui.PeoplePageFragment;
import com.miui.gallery.util.SyncUtil;

public class PeoplePageActivity extends BaseActivity {
    private final Handler mMainHandler = new Handler();
    PeoplePageFragment mPeopleFragment;
    private Runnable mRequestSyncRunnable = new Runnable() {
        /* JADX WARNING: type inference failed for: r1v2, types: [android.content.Context, com.miui.gallery.activity.PeoplePageActivity] */
        public void run() {
            SyncUtil.requestSync(PeoplePageActivity.this, new SyncRequest.Builder().setSyncType(SyncType.NORMAL).setSyncReason(8).build());
        }
    };

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mPeopleFragment.onActivityResult(i, i2, intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.people_page_activity);
        this.mPeopleFragment = (PeoplePageFragment) getFragmentManager().findFragmentById(R.id.people_page);
        this.mMainHandler.postDelayed(this.mRequestSyncRunnable, 3000);
        setImmersionMenuEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.people_album_menu, menu);
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mMainHandler.removeCallbacks(this.mRequestSyncRunnable);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mPeopleFragment.onOptionsItemSelected(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onResume() {
        super.onResume();
    }
}
