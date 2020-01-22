package com.miui.gallery.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;

public class LoginAndSyncCheckFragment extends Fragment implements DialogInterface.OnCancelListener {
    private boolean mCheckGallerySync;
    private int mCheckingType = 0;
    private Dialog mDialog;

    private void checkGallerySync() {
        this.mCheckingType = 3;
        showDialog(R.string.to_enable_sync_dialog_title, R.string.to_enable_gallery_sync_dialog_message, R.string.to_enable_sync_dialog_positive_button_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                IntentUtil.enterGallerySetting(LoginAndSyncCheckFragment.this.getActivity());
            }
        }, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginAndSyncCheckFragment.this.setResult(0);
            }
        });
    }

    private void checkLogin() {
        IntentUtil.guideToLoginXiaomiAccount((Context) getActivity(), getArguments());
        this.mCheckingType = 1;
    }

    public static void checkLoginAndSyncState(Fragment fragment, Bundle bundle) {
        FragmentTransaction beginTransaction = fragment.getChildFragmentManager().beginTransaction();
        LoginAndSyncCheckFragment loginAndSyncCheckFragment = new LoginAndSyncCheckFragment();
        loginAndSyncCheckFragment.setArguments(bundle);
        beginTransaction.add(loginAndSyncCheckFragment, "LoginAndSyncCheckFragment");
        beginTransaction.commitAllowingStateLoss();
    }

    private void checkSystemSync() {
        this.mCheckingType = 2;
        showDialog(R.string.to_enable_sync_dialog_title, R.string.to_enable_sync_dialog_message, R.string.to_enable_sync_dialog_positive_button_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginAndSyncCheckFragment.this.getActivity().startActivity(new Intent("com.xiaomi.action.MICLOUD_MAIN"));
            }
        }, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ToastUtils.makeText((Context) LoginAndSyncCheckFragment.this.getActivity(), (int) R.string.sync_diabled_toast);
                LoginAndSyncCheckFragment.this.setResult(0);
            }
        });
    }

    private void doCheck() {
        if (!SyncUtil.existXiaomiAccount(getActivity())) {
            checkLogin();
        } else if (!ContentResolver.getMasterSyncAutomatically()) {
            checkSystemSync();
        } else if (!this.mCheckGallerySync || SyncUtil.isGalleryCloudSyncable(getActivity())) {
            setResult(-1);
        } else {
            checkGallerySync();
        }
    }

    /* access modifiers changed from: private */
    public void setResult(int i) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.onActivityResult(29, i, getArguments() != null ? new Intent().putExtras(getArguments()) : null);
        }
        getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    private void showDialog(int i, int i2, int i3, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        Dialog dialog = this.mDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mDialog.setOnCancelListener((DialogInterface.OnCancelListener) null);
            this.mDialog.dismiss();
        }
        this.mDialog = new AlertDialog.Builder(getActivity()).setTitle(i).setMessage(i2).setPositiveButton(i3, onClickListener).setNegativeButton(17039360, onClickListener2).create();
        this.mDialog.setOnCancelListener(this);
        this.mDialog.show();
    }

    public void onCancel(DialogInterface dialogInterface) {
        int i = this.mCheckingType;
        if (i != 2) {
            if (i == 3) {
                ToastUtils.makeText((Context) getActivity(), (int) R.string.gallery_sync_disable_toast);
                setResult(0);
            }
            setResult(0);
            return;
        }
        ToastUtils.makeText((Context) getActivity(), (int) R.string.sync_diabled_toast);
        setResult(0);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        boolean z = false;
        this.mCheckingType = 0;
        if (arguments != null) {
            z = arguments.getBoolean("key_check_gallery_sync", false);
        }
        this.mCheckGallerySync = z;
        return new View(getActivity());
    }

    public void onDestroy() {
        super.onDestroy();
        Dialog dialog = this.mDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mDialog.setOnCancelListener((DialogInterface.OnCancelListener) null);
            this.mDialog.dismiss();
        }
        Log.i("LoginAndSyncCheckFragment", "onDestroy");
    }

    public void onStart() {
        super.onStart();
        int i = this.mCheckingType;
        if (i == 0) {
            doCheck();
        } else if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    setResult(0);
                } else if (!SyncUtil.isGalleryCloudSyncable(getActivity())) {
                    ToastUtils.makeText((Context) getActivity(), (int) R.string.gallery_sync_disable_toast);
                    setResult(0);
                } else {
                    doCheck();
                }
            } else if (!ContentResolver.getMasterSyncAutomatically()) {
                ToastUtils.makeText((Context) getActivity(), (int) R.string.sync_diabled_toast);
                setResult(0);
            } else {
                doCheck();
            }
        } else if (!SyncUtil.existXiaomiAccount(getActivity())) {
            ToastUtils.makeText((Context) getActivity(), (int) R.string.xiaomi_account_not_exists_toast);
            setResult(0);
        } else {
            doCheck();
        }
        Log.i("LoginAndSyncCheckFragment", "onStart isCheckPending %d", (Object) Integer.valueOf(this.mCheckingType));
    }
}
