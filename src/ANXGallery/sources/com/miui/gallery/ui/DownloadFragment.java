package com.miui.gallery.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.util.BulkDownloadHelper;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import java.util.ArrayList;
import java.util.List;
import miui.app.ProgressDialog;

public class DownloadFragment extends GalleryDialogFragment implements BulkDownloadHelper.BulkDownloadListener {
    /* access modifiers changed from: private */
    public OnDownloadListener mDownloadListener;
    private BulkDownloadHelper mHelper;
    protected ProgressDialog mProgressDialog;
    protected String mTitle;

    public interface OnDownloadListener {
        void onCanceled();

        void onDownloadComplete(List<BulkDownloadHelper.BulkDownloadItem> list, List<BulkDownloadHelper.BulkDownloadItem> list2);
    }

    public static DownloadFragment newInstance(ArrayList<BulkDownloadHelper.BulkDownloadItem> arrayList) {
        DownloadFragment downloadFragment = new DownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("key_download_items", arrayList);
        downloadFragment.setArguments(bundle);
        return downloadFragment;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        ArrayList parcelableArrayList = getArguments().getParcelableArrayList("key_download_items");
        if (MiscUtil.isValid(parcelableArrayList)) {
            this.mHelper = new BulkDownloadHelper();
            this.mHelper.download(parcelableArrayList, true, this);
            return;
        }
        dismissAllowingStateLoss();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.mProgressDialog = new ProgressDialog(getActivity());
        this.mTitle = getResources().getString(R.string.download_title);
        this.mProgressDialog.setTitle(this.mTitle);
        this.mProgressDialog.setProgressStyle(1);
        this.mProgressDialog.setIndeterminate(false);
        this.mProgressDialog.setMax(100);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.mProgressDialog.setButton(-2, getResources().getString(17039360), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (DownloadFragment.this.mDownloadListener != null) {
                    DownloadFragment.this.mDownloadListener.onCanceled();
                    OnDownloadListener unused = DownloadFragment.this.mDownloadListener = null;
                }
                DownloadFragment.this.dismissAllowingStateLoss();
            }
        });
        setCancelable(false);
        return this.mProgressDialog;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        BulkDownloadHelper bulkDownloadHelper = this.mHelper;
        if (bulkDownloadHelper != null) {
            bulkDownloadHelper.cancel();
            this.mHelper = null;
        }
        this.mDownloadListener = null;
    }

    public void onDownloadEnd(List<BulkDownloadHelper.BulkDownloadItem> list, List<BulkDownloadHelper.BulkDownloadItem> list2) {
        int size = list2.size();
        if (size > 0) {
            ToastUtils.makeText((Context) getActivity(), (CharSequence) getResources().getQuantityString(R.plurals.download_error, size, new Object[]{Integer.valueOf(size)}));
        }
        OnDownloadListener onDownloadListener = this.mDownloadListener;
        if (onDownloadListener != null) {
            onDownloadListener.onDownloadComplete(list, list2);
            this.mDownloadListener = null;
        }
        dismissAllowingStateLoss();
    }

    public void onDownloadProgress(float f) {
        int i = (int) (f * 100.0f);
        ProgressDialog progressDialog = this.mProgressDialog;
        progressDialog.setTitle(this.mTitle + " " + i + "%");
        this.mProgressDialog.setProgress(i);
    }

    public void setOnDownloadListener(OnDownloadListener onDownloadListener) {
        this.mDownloadListener = onDownloadListener;
    }
}
