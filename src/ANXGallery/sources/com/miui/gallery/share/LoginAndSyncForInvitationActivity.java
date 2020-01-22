package com.miui.gallery.share;

import android.app.FragmentTransaction;
import android.os.Bundle;
import com.miui.gallery.activity.BaseActivity;

public class LoginAndSyncForInvitationActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public boolean allowUseOnOffline() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [android.app.Fragment, com.miui.gallery.share.LoginAndSyncForInvitationFragment] */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ? loginAndSyncForInvitationFragment = new LoginAndSyncForInvitationFragment();
        loginAndSyncForInvitationFragment.setArguments(getIntent().getExtras());
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add(loginAndSyncForInvitationFragment, "LoginAndSyncForInvitationFragment");
        beginTransaction.commitAllowingStateLoss();
    }
}
