package com.miui.gallery.activity.facebaby;

import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.activity.BaseActivity;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.ui.renameface.InputFaceNameFragment;
import com.miui.gallery.util.ArrayUtils;

public class InputFaceNameActivity extends BaseActivity {
    InputFaceNameFragment mFragment;

    private RuntimePermission[] getOptionalPermissions() {
        return new RuntimePermission[]{new RuntimePermission("android.permission.READ_CONTACTS", getString(R.string.permission_contacts_desc), false)};
    }

    public RuntimePermission[] getRuntimePermissions() {
        return (RuntimePermission[]) ArrayUtils.concat(super.getRuntimePermissions(), getOptionalPermissions());
    }

    public void onBackPressed() {
        this.mFragment.onBackPressed();
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.input_face_name_activity);
        this.mFragment = (InputFaceNameFragment) getFragmentManager().findFragmentById(R.id.input_face_name_fragment);
    }

    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        super.onPermissionsChecked(runtimePermissionArr, iArr);
        InputFaceNameFragment inputFaceNameFragment = this.mFragment;
        if (inputFaceNameFragment != null) {
            inputFaceNameFragment.updateNameList();
        }
    }
}
