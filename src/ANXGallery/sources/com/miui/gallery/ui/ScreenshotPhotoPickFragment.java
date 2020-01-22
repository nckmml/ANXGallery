package com.miui.gallery.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.AlbumDetailSimpleAdapter;
import com.miui.gallery.ui.CleanerPhotoPickFragment;
import com.miui.gallery.util.GallerySamplingStatHelper;

public class ScreenshotPhotoPickFragment extends CleanerPhotoPickFragment {
    public ScreenshotPhotoPickFragment() {
        super(1);
    }

    /* access modifiers changed from: protected */
    public CleanerPhotoPickFragment.DeleteMessage getDeleteMessage() {
        return new CleanerPhotoPickFragment.DeleteMessage.Builder().setCleanerSubEvent("cleaner_screenshot_used").setReason(46).setDupType(2).build();
    }

    /* access modifiers changed from: protected */
    public int getLayoutSource() {
        return R.layout.screenshot_photo_pick_layout;
    }

    public String getPageName() {
        return "cleaner_screenshot_photo_pick";
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.mAlbumDetailGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbScreenshotColumnsLand);
        } else {
            this.mAlbumDetailGridView.setNumColumns(Config.ThumbConfig.get().sMicroThumbScreenshotColumnsPortrait);
        }
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onInflateView = super.onInflateView(layoutInflater, viewGroup, bundle);
        this.mActivity.getActionBar().setTitle(R.string.cleaner_screen_shot_title);
        this.mAdapter.setAlbumType(AlbumDetailSimpleAdapter.AlbumType.SCREENSHOT);
        return onInflateView;
    }

    /* access modifiers changed from: protected */
    public void recordCancelSelectAllEvent() {
        GallerySamplingStatHelper.recordCountEvent("cleaner", "screenshot_select_all_cancel");
    }
}
