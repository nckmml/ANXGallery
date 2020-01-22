package com.miui.gallery.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import com.miui.gallery.R;
import com.miui.gallery.activity.PhotoSlimActivity;
import com.miui.gallery.cleaner.slim.SlimController;
import com.miui.gallery.cleaner.slim.SlimScanner;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.util.DocumentProviderUtils;
import com.miui.gallery.util.GallerySamplingStatHelper;
import java.util.HashMap;

public class SlimPhotoPickFragment extends CleanerPhotoPickFragment {
    /* access modifiers changed from: private */
    public Dialog mSlimDescriptionDialog;
    private View.OnClickListener mStartSlimListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (DocumentProviderUtils.needRequestExternalSDCardPermission(SlimPhotoPickFragment.this.getActivity())) {
                DocumentProviderUtils.startExtSDCardPermissionActivityForResult(SlimPhotoPickFragment.this.getActivity());
                return;
            }
            long[] checkedItemIds = SlimPhotoPickFragment.this.mGridViewWrapper.getCheckedItemIds();
            SlimController.getInstance().start(checkedItemIds);
            SlimPhotoPickFragment.this.getActivity().startActivity(new Intent(SlimPhotoPickFragment.this.getActivity(), PhotoSlimActivity.class));
            HashMap hashMap = new HashMap();
            hashMap.put("count", GallerySamplingStatHelper.formatValueStage((float) checkedItemIds.length, CleanerPhotoPickFragment.DELETE_COUNT_STAGE));
            GallerySamplingStatHelper.recordCountEvent("cleaner", "cleaner_slim_used", hashMap);
            SlimPhotoPickFragment.this.finish();
        }
    };

    public SlimPhotoPickFragment() {
        super(0);
    }

    /* access modifiers changed from: protected */
    public int getLayoutSource() {
        return R.layout.slim_photo_pick_layout;
    }

    public String getPageName() {
        return "cleaner_slim_photo_pick";
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        return String.format("%s AND %s IN (%s)", new Object[]{SlimScanner.SYNCED_SLIM_SCAN_SELECTION, "_id", TextUtils.join(",", this.mScanResultIds)});
    }

    /* JADX WARNING: type inference failed for: r4v2, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.miui.gallery.activity.BaseActivity, android.content.Context] */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (GalleryPreferences.PhotoSlim.isFirstUsePhotoSlim()) {
            View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.photo_slim_description_dialog, (ViewGroup) null, false);
            this.mSlimDescriptionDialog = new Dialog(this.mActivity);
            this.mSlimDescriptionDialog.setContentView(inflate);
            ((Button) inflate.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SlimPhotoPickFragment.this.mSlimDescriptionDialog.dismiss();
                }
            });
            Window window = this.mSlimDescriptionDialog.getWindow();
            window.setGravity(80);
            window.setLayout(-1, -2);
            this.mSlimDescriptionDialog.show();
            GalleryPreferences.PhotoSlim.setIsFirstUsePhotoSlim(false);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Dialog dialog = this.mSlimDescriptionDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mSlimDescriptionDialog.dismiss();
        }
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        this.mActivity.getActionBar().setTitle(R.string.cleaner_slim_title);
        this.mDeleteButton.setOnClickListener(this.mStartSlimListener);
        this.mAdapter.setClickToPhotoPageEnable(false);
        return onInflateView;
    }

    /* access modifiers changed from: protected */
    public void recordCancelSelectAllEvent() {
        GallerySamplingStatHelper.recordCountEvent("cleaner", "slim_select_all_cancel");
    }
}
