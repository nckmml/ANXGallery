package com.miui.gallery.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.R;
import com.miui.gallery.ui.CleanerPhotoPickFragment;
import com.miui.gallery.util.GallerySamplingStatHelper;

public class VideoResultPickFragment extends CleanerSortablePhotoPickFragment {
    public VideoResultPickFragment() {
        super(2);
    }

    /* access modifiers changed from: protected */
    public CleanerPhotoPickFragment.DeleteMessage getDeleteMessage() {
        return new CleanerPhotoPickFragment.DeleteMessage.Builder().setCleanerSubEvent("cleaner_video_used").setReason(47).setDupType(0).build();
    }

    /* access modifiers changed from: protected */
    public int getLayoutSource() {
        return R.layout.video_result_pick_layout;
    }

    public String getPageName() {
        return "cleaner_video_result_pick";
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        return String.format("%s IN (%s) AND %s", new Object[]{"_id", TextUtils.join(",", this.mScanResultIds), "(size > 0)"});
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        this.mActivity.getActionBar().setTitle(R.string.cleaner_video_title);
        return onInflateView;
    }

    /* access modifiers changed from: protected */
    public void recordCancelSelectAllEvent() {
        GallerySamplingStatHelper.recordCountEvent("cleaner", "video_select_all_cancel");
    }
}
