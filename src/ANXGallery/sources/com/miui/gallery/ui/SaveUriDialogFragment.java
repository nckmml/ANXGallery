package com.miui.gallery.ui;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.miui.gallery.R;
import com.miui.gallery.scanner.MediaScannerUtil;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureListener;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.FileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MediaFileUtils;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.util.StorageUtils;
import com.miui.gallery.widget.GalleryDialogFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import miui.app.ProgressDialog;

public class SaveUriDialogFragment extends GalleryDialogFragment {
    /* access modifiers changed from: private */
    public OnCompleteListener mListener;
    private ProgressDialog mProgressDialog;

    public interface OnCompleteListener {
        void onComplete(String str);
    }

    private static File generateJPGFileForSaving(String str) {
        String str2;
        int i = 0;
        while (true) {
            String format = String.format("%s/%s", new Object[]{str, String.format("SAVE_%s", new Object[]{new SimpleDateFormat("yyyyMMdd_kkmmss").format(new Date(System.currentTimeMillis()))})});
            if (i > 0) {
                str2 = format + "_" + i + "." + "jpg";
            } else {
                str2 = format + "." + "jpg";
            }
            File file = new File(str2);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    return file;
                } catch (IOException e) {
                    Log.e("SaveUriDialogFragment", "generateJPGFileForSaving() failed %s %s", file.getAbsolutePath(), e);
                    return null;
                }
            } else {
                i++;
            }
        }
    }

    private void save(final Uri uri) {
        if (uri == null) {
            OnCompleteListener onCompleteListener = this.mListener;
            if (onCompleteListener != null) {
                onCompleteListener.onComplete((String) null);
                return;
            }
            return;
        }
        ThreadManager.getMiscPool().submit(new ThreadPool.Job<String>() {
            public String run(ThreadPool.JobContext jobContext) {
                return SaveUriDialogFragment.this.saveInternal(uri);
            }
        }, new FutureListener<String>() {
            public void onFutureDone(final Future<String> future) {
                if (SaveUriDialogFragment.this.getActivity() != null) {
                    SaveUriDialogFragment.this.getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            if (SaveUriDialogFragment.this.mListener != null) {
                                SaveUriDialogFragment.this.mListener.onComplete((String) future.get());
                            }
                            if (SaveUriDialogFragment.this.isAdded()) {
                                SaveUriDialogFragment.this.dismissAllowingStateLoss();
                            }
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String saveInternal(Uri uri) {
        File generateJPGFileForSaving;
        FileOutputStream fileOutputStream;
        String safePathInPriorStorageForUnadapted = StorageUtils.getSafePathInPriorStorageForUnadapted("DCIM/Camera");
        if (!FileUtils.createFolder(new File(safePathInPriorStorageForUnadapted), false) || (generateJPGFileForSaving = generateJPGFileForSaving(safePathInPriorStorageForUnadapted)) == null) {
            return null;
        }
        Bitmap loadImageSync = ImageLoader.getInstance().loadImageSync(uri.toString(), (ImageSize) null, new DisplayImageOptions.Builder().imageScaleType(ImageScaleType.NONE_SAFE).considerExifParams(true).build());
        if (loadImageSync == null) {
            Log.e("SaveUriDialogFragment", "fail to convert %s to bitmap", (Object) uri.toString());
            return null;
        }
        try {
            fileOutputStream = new FileOutputStream(generateJPGFileForSaving);
            try {
                loadImageSync.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                String absolutePath = generateJPGFileForSaving.getAbsolutePath();
                MediaScannerUtil.scanSingleFile(getActivity().getApplicationContext(), absolutePath, 0);
                MediaFileUtils.triggerMediaScan(true, generateJPGFileForSaving);
                MiscUtil.closeSilently(fileOutputStream);
                loadImageSync.recycle();
                return absolutePath;
            } catch (Exception e) {
                e = e;
                try {
                    Log.e("SaveUriDialogFragment", "fail to save %s %s", uri, e);
                    MiscUtil.closeSilently(fileOutputStream);
                    loadImageSync.recycle();
                    return null;
                } catch (Throwable th) {
                    th = th;
                    MiscUtil.closeSilently(fileOutputStream);
                    loadImageSync.recycle();
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileOutputStream = null;
            Log.e("SaveUriDialogFragment", "fail to save %s %s", uri, e);
            MiscUtil.closeSilently(fileOutputStream);
            loadImageSync.recycle();
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            MiscUtil.closeSilently(fileOutputStream);
            loadImageSync.recycle();
            throw th;
        }
    }

    public static void show(Activity activity, Uri uri, OnCompleteListener onCompleteListener) {
        SaveUriDialogFragment saveUriDialogFragment = new SaveUriDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_uri", uri);
        saveUriDialogFragment.setArguments(bundle);
        saveUriDialogFragment.setOnCompleteListener(onCompleteListener);
        saveUriDialogFragment.showAllowingStateLoss(activity.getFragmentManager(), "SaveUriDialogFragment");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        save((Uri) getArguments().getParcelable("key_uri"));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        this.mProgressDialog = ProgressDialog.show(getActivity(), "", getActivity().getString(R.string.adding_to_album), true, false);
        return this.mProgressDialog;
    }

    public void onDestroy() {
        setOnCompleteListener((OnCompleteListener) null);
        super.onDestroy();
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.mListener = onCompleteListener;
    }
}
