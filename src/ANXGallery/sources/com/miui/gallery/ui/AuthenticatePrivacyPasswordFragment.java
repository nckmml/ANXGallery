package com.miui.gallery.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.ui.ConfirmDialog;
import com.miui.privacy.LockSettingsHelper;

public class AuthenticatePrivacyPasswordFragment extends Fragment {
    private Activity mActivity;
    private LockSettingsHelper mChooseLockSettingsHelper;

    /* access modifiers changed from: private */
    public void authenticatePrivacyPassword() {
        if (this.mChooseLockSettingsHelper.isPrivacyPasswordEnabled()) {
            if (GalleryPreferences.Secret.isFirstUsePrivacyPassword()) {
                Activity activity = this.mActivity;
                if (activity != null) {
                    Resources resources = activity.getResources();
                    GalleryPreferences.Secret.setIsFirstUsePrivacyPassword(false);
                    ConfirmDialog.showConfirmDialog(getFragmentManager(), resources.getString(R.string.secret_album_password_first_use_title), resources.getString(R.string.secret_album_password_first_use_msg), resources.getString(R.string.cancel), resources.getString(R.string.ok), new ConfirmDialog.ConfirmDialogInterface() {
                        public void onCancel(DialogFragment dialogFragment) {
                            AuthenticatePrivacyPasswordFragment.this.setResult(0);
                        }

                        public void onConfirm(DialogFragment dialogFragment) {
                            LockSettingsHelper.startAuthenticatePasswordActivity((Fragment) AuthenticatePrivacyPasswordFragment.this, 27);
                        }
                    });
                    return;
                }
                return;
            }
            LockSettingsHelper.startAuthenticatePasswordActivity((Fragment) this, 27);
        } else if (GalleryPreferences.Secret.isFirstUsePrivacyPassword()) {
            Activity activity2 = this.mActivity;
            if (activity2 != null) {
                Resources resources2 = activity2.getResources();
                GalleryPreferences.Secret.setIsFirstUsePrivacyPassword(false);
                ConfirmDialog.showConfirmDialog(getFragmentManager(), resources2.getString(R.string.secret_album_set_pc_password_dialog_title), resources2.getString(R.string.secret_album_set_pc_password_dialog_msg), resources2.getString(R.string.cancel), resources2.getString(R.string.ok), new ConfirmDialog.ConfirmDialogInterface() {
                    public void onCancel(DialogFragment dialogFragment) {
                        AuthenticatePrivacyPasswordFragment.this.setResult(0);
                    }

                    public void onConfirm(DialogFragment dialogFragment) {
                        LockSettingsHelper.startSetPrivacyPasswordActivity(AuthenticatePrivacyPasswordFragment.this, 28);
                    }
                });
            }
        } else {
            LockSettingsHelper.startSetPrivacyPasswordActivity(this, 28);
        }
    }

    /* access modifiers changed from: private */
    public void setResult(int i) {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            parentFragment.onActivityResult(36, i, getArguments() != null ? new Intent().putExtras(getArguments()) : null);
        }
        getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    private void showPasswordUpdateDialog() {
        Activity activity = this.mActivity;
        if (activity != null) {
            Resources resources = activity.getResources();
            GalleryPreferences.Secret.setIsFirstUsePrivacyPassword(false);
            ConfirmDialog.showConfirmDialog(getFragmentManager(), resources.getString(this.mChooseLockSettingsHelper.isPrivacyPasswordEnabled() ? R.string.secret_album_password_update_dialog_title : R.string.secret_album_password_update_first_user_dialog_title), resources.getString(this.mChooseLockSettingsHelper.isPrivacyPasswordEnabled() ? R.string.secret_album_password_update_dialog_msg : R.string.secret_album_password_update_first_user_dialog_msg), resources.getString(R.string.cancel), resources.getString(R.string.ok), new ConfirmDialog.ConfirmDialogInterface() {
                public void onCancel(DialogFragment dialogFragment) {
                    AuthenticatePrivacyPasswordFragment.this.setResult(0);
                }

                public void onConfirm(DialogFragment dialogFragment) {
                    AuthenticatePrivacyPasswordFragment.this.authenticatePrivacyPassword();
                }
            });
        }
    }

    public static void startAuthenticatePrivacyPassword(Fragment fragment) {
        FragmentTransaction beginTransaction = fragment.getChildFragmentManager().beginTransaction();
        beginTransaction.add(new AuthenticatePrivacyPasswordFragment(), "AuthenticatePrivacyPassword");
        beginTransaction.commitAllowingStateLoss();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            setResult(0);
        } else if (i == 27 || i == 28) {
            setResult(-1);
        } else if (i == 35) {
            this.mChooseLockSettingsHelper.setPrivateGalleryEnabled(false);
            showPasswordUpdateDialog();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mActivity = getActivity();
        if (this.mChooseLockSettingsHelper == null) {
            this.mChooseLockSettingsHelper = new LockSettingsHelper(getActivity());
        }
        if (this.mChooseLockSettingsHelper.isPrivateGalleryEnabled()) {
            LockSettingsHelper.confirmPrivateGalleryPassword(this, 35);
        } else {
            authenticatePrivacyPassword();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new View(getActivity());
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onStart() {
        super.onStart();
    }
}
