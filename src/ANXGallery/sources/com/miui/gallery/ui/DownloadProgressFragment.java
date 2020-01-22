package com.miui.gallery.ui;

import android.os.Bundle;
import com.miui.gallery.util.BulkDownloadHelper;
import java.util.ArrayList;

public class DownloadProgressFragment extends DownloadFragment {
    public static DownloadProgressFragment newInstance(ArrayList<BulkDownloadHelper.BulkDownloadItem> arrayList) {
        DownloadProgressFragment downloadProgressFragment = new DownloadProgressFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("key_download_items", arrayList);
        downloadProgressFragment.setArguments(bundle);
        return downloadProgressFragment;
    }

    public void onDownloadProgress(float f) {
        this.mProgressDialog.setProgress((int) (f * 100.0f));
    }
}
