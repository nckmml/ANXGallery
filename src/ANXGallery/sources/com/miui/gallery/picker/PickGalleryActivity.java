package com.miui.gallery.picker;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Pair;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.assistant.cache.ImageFeatureCacheManager;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.permission.core.RuntimePermission;
import com.miui.gallery.picker.helper.Picker;
import com.miui.gallery.picker.uri.DownloadCancelDialog;
import com.miui.gallery.picker.uri.DownloadConfirmDialog;
import com.miui.gallery.picker.uri.Downloader;
import com.miui.gallery.picker.uri.OriginUrlRequestor;
import com.miui.gallery.picker.uri.PrepareProgressDialog;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.GalleryOpenProvider;
import com.miui.gallery.scanner.MediaScannerUtil;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.ImageSelectionTipFragment;
import com.miui.gallery.util.ActionURIHandler;
import com.miui.gallery.util.FileMimeUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.SyncUtil;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.UriUtil;
import com.miui.gallery.util.uil.CloudUriAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import miui.app.ActionBar;

public class PickGalleryActivity extends PickerActivity {
    private DialogInterface.OnCancelListener mCancelConfirmListener = new DialogInterface.OnCancelListener() {
        public void onCancel(DialogInterface dialogInterface) {
            int remainSize = PickGalleryActivity.this.mDownloader == null ? 0 : PickGalleryActivity.this.mDownloader.getRemainSize();
            if (remainSize > 0) {
                DownloadCancelDialog downloadCancelDialog = new DownloadCancelDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("remaining_count", remainSize);
                downloadCancelDialog.setArguments(bundle);
                downloadCancelDialog.showAllowingStateLoss(PickGalleryActivity.this.getFragmentManager(), "cancel_dialog");
            }
        }
    };
    private DialogInterface.OnClickListener mCancelDownloadListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (PickGalleryActivity.this.mDownloader != null) {
                PickGalleryActivity.this.mDownloader.cancel();
            }
            if (PickGalleryActivity.this.getPicker() != null && PickGalleryActivity.this.getPicker().getMode() == Picker.Mode.SINGLE) {
                PickGalleryActivity.this.getPicker().clear();
            }
        }
    };
    private int mCloseType;
    private DialogInterface.OnClickListener mContinueDownloadListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (PickGalleryActivity.this.mDownloadProgressDialog != null) {
                PickGalleryActivity.this.mDownloadProgressDialog.showAllowingStateLoss(PickGalleryActivity.this.getFragmentManager(), "prepare_progress_dialog");
            }
        }
    };
    /* access modifiers changed from: private */
    public int mCurrentPagePosition = 0;
    private DialogInterface.OnClickListener mDownloadListener = new DialogInterface.OnClickListener() {
        /* JADX WARNING: type inference failed for: r1v3, types: [android.content.Context, com.miui.gallery.picker.PickGalleryActivity] */
        public void onClick(DialogInterface dialogInterface, int i) {
            if (!NetworkUtils.isNetworkConnected()) {
                ToastUtils.makeText((Context) PickGalleryActivity.this, (int) R.string.picker_no_network_message);
                PickGalleryActivity.this.showConfirmDialog(true);
                return;
            }
            PickGalleryActivity.this.startDownload();
        }
    };
    /* access modifiers changed from: private */
    public ArrayList<Downloader.DownloadTask> mDownloadPendings;
    /* access modifiers changed from: private */
    public PrepareProgressDialog mDownloadProgressDialog;
    /* access modifiers changed from: private */
    public Downloader mDownloader;
    /* access modifiers changed from: private */
    public ArrayList<OriginUrlRequestor.OriginUrlRequestTask> mOriginRequestPendings;
    /* access modifiers changed from: private */
    public PrepareProgressDialog mOriginRequestProgressDialog;
    /* access modifiers changed from: private */
    public OriginUrlRequestor mOriginUrlRequestor;
    /* access modifiers changed from: private */
    public ParseTask mParseTask;
    private Intent mPickIntent;
    /* access modifiers changed from: private */
    public Uri[] mResults;
    private String[] mSha1s;

    private class DownloadProgressListener implements Downloader.DownloadListener {
        private DownloadProgressListener() {
        }

        private void parseDownloadResult(List<Downloader.DownloadResult> list, List<Downloader.DownloadResult> list2) {
            for (Downloader.DownloadResult next : list) {
                if (next != null && !TextUtils.isEmpty(next.mPath)) {
                    File file = new File(next.mPath);
                    if (file.exists()) {
                        PickGalleryActivity.this.mResults[next.mTask.mPosition] = Uri.fromFile(file);
                    }
                }
                list2.add(next);
            }
            PickGalleryActivity.this.mDownloadPendings.clear();
            for (Downloader.DownloadResult downloadResult : list2) {
                PickGalleryActivity.this.mDownloadPendings.add(downloadResult.mTask);
            }
        }

        public void onCancelled(List<Downloader.DownloadResult> list, List<Downloader.DownloadResult> list2) {
            Log.d("PickGalleryActivity", "download cancelled, success: %d, fails: %d", Integer.valueOf(list.size()), Integer.valueOf(list2.size()));
            if (PickGalleryActivity.this.mDownloadProgressDialog != null) {
                PickGalleryActivity.this.mDownloadProgressDialog.dismissSafely();
                PrepareProgressDialog unused = PickGalleryActivity.this.mDownloadProgressDialog = null;
            }
            parseDownloadResult(list, list2);
            PickGalleryActivity.this.mDownloader.destroy();
            Downloader unused2 = PickGalleryActivity.this.mDownloader = null;
        }

        public void onEnd(List<Downloader.DownloadResult> list, List<Downloader.DownloadResult> list2) {
            Log.d("PickGalleryActivity", "download end, success: %d, fails: %d", Integer.valueOf(list.size()), Integer.valueOf(list2.size()));
            if (PickGalleryActivity.this.mDownloadProgressDialog != null) {
                PickGalleryActivity.this.mDownloadProgressDialog.dismissSafely();
                Fragment findFragmentByTag = PickGalleryActivity.this.getFragmentManager().findFragmentByTag("cancel_dialog");
                if (findFragmentByTag != null && (findFragmentByTag instanceof DownloadCancelDialog)) {
                    ((DownloadCancelDialog) findFragmentByTag).dismissSafely();
                }
                PrepareProgressDialog unused = PickGalleryActivity.this.mDownloadProgressDialog = null;
            }
            parseDownloadResult(list, list2);
            if (list2.isEmpty()) {
                PickGalleryActivity.this.parseResult();
            } else {
                PickGalleryActivity.this.showConfirmDialog(true);
            }
            PickGalleryActivity.this.mDownloader.destroy();
            Downloader unused2 = PickGalleryActivity.this.mDownloader = null;
        }

        public void onStart(List<Downloader.DownloadTask> list) {
            Log.d("PickGalleryActivity", "download start, %d", (Object) Integer.valueOf(list.size()));
            PrepareProgressDialog unused = PickGalleryActivity.this.mDownloadProgressDialog = new PrepareProgressDialog();
            PickGalleryActivity.this.mDownloadProgressDialog.setMax(list.size());
            PickGalleryActivity.this.mDownloadProgressDialog.setStage(0);
            PickGalleryActivity.this.mDownloadProgressDialog.showAllowingStateLoss(PickGalleryActivity.this.getFragmentManager(), "prepare_progress_dialog");
        }

        public void onUpdate(List<Downloader.DownloadResult> list, List<Downloader.DownloadResult> list2) {
            PickGalleryActivity.this.mDownloadProgressDialog.updateProgress(list.size() + list2.size());
        }
    }

    private static class MediaScanJob implements ThreadPool.Job<Void> {
        private MediaScanJob() {
        }

        public Void run(ThreadPool.JobContext jobContext) {
            Context sGetAndroidContext = GalleryApp.sGetAndroidContext();
            MediaScannerUtil.scanMediaProvider(sGetAndroidContext);
            MediaScannerUtil.scanAllAlbumDirectories(sGetAndroidContext, 5);
            MediaScannerUtil.cleanup(sGetAndroidContext);
            return null;
        }
    }

    private class OriginUrlRequestProgressListener implements OriginUrlRequestor.ProgressListener {
        private OriginUrlRequestProgressListener() {
        }

        private void finish() {
            if (PickGalleryActivity.this.mOriginRequestProgressDialog != null) {
                PickGalleryActivity.this.mOriginRequestProgressDialog.dismissSafely();
                PrepareProgressDialog unused = PickGalleryActivity.this.mOriginRequestProgressDialog = null;
            }
            if (PickGalleryActivity.this.mOriginRequestPendings != null) {
                PickGalleryActivity.this.mOriginRequestPendings.clear();
            }
            if (PickGalleryActivity.this.mOriginUrlRequestor != null) {
                PickGalleryActivity.this.mOriginUrlRequestor.destroy();
                OriginUrlRequestor unused2 = PickGalleryActivity.this.mOriginUrlRequestor = null;
            }
        }

        public void onCancelled() {
            finish();
        }

        public void onEnd(ArrayList<OriginUrlRequestor.OriginInfo> arrayList, boolean z) {
            finish();
            if (z) {
                PickGalleryActivity.this.parseOriginUrlResult(arrayList);
            } else {
                Log.e("PickGalleryActivity", "selected images OriginInfo generate error");
            }
        }

        public void onStart(int i) {
            PrepareProgressDialog unused = PickGalleryActivity.this.mOriginRequestProgressDialog = new PrepareProgressDialog();
            PickGalleryActivity.this.mOriginRequestProgressDialog.setMax(i);
            PickGalleryActivity.this.mOriginRequestProgressDialog.setStage(1);
            PickGalleryActivity.this.mOriginRequestProgressDialog.showAllowingStateLoss(PickGalleryActivity.this.getFragmentManager(), "prepare_progress_dialog");
        }

        public void onUpdate(int i) {
            if (PickGalleryActivity.this.mOriginRequestProgressDialog != null) {
                PickGalleryActivity.this.mOriginRequestProgressDialog.updateProgress(i);
            }
        }
    }

    private class ParseTask extends AsyncTask<Void, Void, Cursor> {
        private String mPickSha1Results;

        ParseTask(Picker picker) {
            this.mPickSha1Results = TextUtils.join("','", picker);
        }

        /* access modifiers changed from: protected */
        public Cursor doInBackground(Void... voidArr) {
            Cursor query = PickGalleryActivity.this.getContentResolver().query(UriUtil.appendGroupBy(GalleryContract.Media.URI_PICKER, "sha1", (String) null), PickerActivity.PICKABLE_PROJECTION, String.format("sha1 IN ('%s') AND (localGroupId!=-1000) AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new Object[]{this.mPickSha1Results}), (String[]) null, String.format("INSTR(\"'%s'\", sha1)", new Object[]{this.mPickSha1Results}));
            if (query != null) {
                query.getCount();
            }
            return query;
        }

        /* JADX WARNING: type inference failed for: r1v11, types: [android.content.Context, com.miui.gallery.picker.PickGalleryActivity] */
        /* JADX WARNING: type inference failed for: r2v12, types: [android.content.Context, com.miui.gallery.picker.PickGalleryActivity] */
        /* JADX WARNING: type inference failed for: r2v16, types: [android.content.Context, com.miui.gallery.picker.PickGalleryActivity] */
        /* access modifiers changed from: protected */
        public void onPostExecute(Cursor cursor) {
            String str;
            ParseTask unused = PickGalleryActivity.this.mParseTask = null;
            if (cursor != null) {
                try {
                    Log.d("PickGalleryActivity", "Picker capacity: %d, result %d items", Integer.valueOf(PickGalleryActivity.this.getPicker().capacity()), Integer.valueOf(cursor.getCount()));
                    if (PickGalleryActivity.this.getPicker().getResultType() == Picker.ResultType.ID) {
                        Intent access$400 = PickGalleryActivity.this.getResultIntent();
                        ArrayList arrayList = new ArrayList();
                        int i = 0;
                        while (cursor.moveToNext()) {
                            long j = cursor.getLong(0);
                            arrayList.add(Long.valueOf(j));
                            if (ImageFeatureCacheManager.getInstance().isBestImage(j, false, false, (List<Long>) null)) {
                                i++;
                            }
                        }
                        PickGalleryActivity.this.statBestImageCount(cursor.getCount(), i);
                        access$400.putExtra("pick-result-data", arrayList);
                        PickGalleryActivity.this.doCompleteOperation(access$400);
                    } else {
                        PickGalleryActivity.this.prepareResult(cursor);
                        Log.d("PickGalleryActivity", "picked file: %d, pending: %d", Integer.valueOf(PickGalleryActivity.this.mResults.length), Integer.valueOf(PickGalleryActivity.this.mDownloadPendings.size()));
                        if (PickGalleryActivity.this.mDownloadPendings.isEmpty()) {
                            PickGalleryActivity.this.parseResult();
                        } else if (!SyncUtil.existXiaomiAccount(PickGalleryActivity.this)) {
                            ToastUtils.makeText((Context) PickGalleryActivity.this, (CharSequence) PickGalleryActivity.this.getResources().getQuantityString(R.plurals.picker_skip_unavailable_images, PickGalleryActivity.this.mDownloadPendings.size(), new Object[]{Integer.valueOf(PickGalleryActivity.this.mDownloadPendings.size())}));
                            PickGalleryActivity.this.parseResult();
                        } else if (NetworkUtils.isActiveNetworkMetered()) {
                            PickGalleryActivity.this.showConfirmDialog(false);
                        } else if (NetworkUtils.isNetworkConnected()) {
                            if (PickGalleryActivity.this.mResults.length == 0) {
                                str = PickGalleryActivity.this.getResources().getQuantityString(R.plurals.picker_all_image_will_download, PickGalleryActivity.this.mDownloadPendings.size());
                            } else {
                                str = PickGalleryActivity.this.getResources().getString(R.string.picker_image_will_download, new Object[]{Integer.valueOf(PickGalleryActivity.this.mDownloadPendings.size())});
                            }
                            ToastUtils.makeText((Context) PickGalleryActivity.this, (CharSequence) str);
                            PickGalleryActivity.this.startDownload();
                        } else {
                            PickGalleryActivity.this.showConfirmDialog(true);
                        }
                    }
                } catch (Exception e) {
                    Log.e("PickGalleryActivity", "Parse failed %s", (Object) e);
                } catch (Throwable th) {
                    cursor.close();
                    throw th;
                }
                cursor.close();
                return;
            }
            throw new IllegalStateException("return a null cursor");
        }
    }

    private boolean contains(RuntimePermission[] runtimePermissionArr, String str) {
        for (RuntimePermission runtimePermission : runtimePermissionArr) {
            if (str.equalsIgnoreCase(runtimePermission.name)) {
                return true;
            }
        }
        return false;
    }

    private void dispatchPermissionChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        PickHomePageFragment pickHomePageFragment;
        if (runtimePermissionArr != null && runtimePermissionArr.length != 0 && (pickHomePageFragment = (PickHomePageFragment) getFragmentManager().findFragmentByTag("PickHomePageFragment")) != null && contains(runtimePermissionArr, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            pickHomePageFragment.onPermissionsChecked();
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.miui.gallery.picker.PickGalleryActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void doCompleteOperation(Intent intent) {
        int i = -1;
        if (isPickIntentMode()) {
            Uri data = intent.getData();
            boolean z = this.mCloseType == 3;
            if (data != null && TextUtils.equals(data.getAuthority(), "gallery.i.mi.com")) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("start_activity_for_result", z);
                bundle.putParcelable("extra_intent", intent);
                if (z) {
                    i = 42;
                }
                bundle.putInt("request_code", i);
                ActionURIHandler.handleUri(this, data, bundle);
            } else if (z) {
                startActivityForResult(this.mPickIntent, 42);
            } else {
                startActivity(intent);
            }
            if (this.mCloseType == 1) {
                finish();
                return;
            }
            return;
        }
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public String getPageName(int i) {
        if (i == 0) {
            return "home";
        }
        if (i == 1) {
            return "album";
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Intent getResultIntent() {
        return isPickIntentMode() ? this.mPickIntent : new Intent();
    }

    private boolean isPickIntentMode() {
        return this.mPickIntent != null;
    }

    /* access modifiers changed from: private */
    public void parseOriginUrlResult(ArrayList<OriginUrlRequestor.OriginInfo> arrayList) {
        Intent resultIntent = getResultIntent();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(arrayList.get(i).toJson());
        }
        resultIntent.putExtra("pick-result-origin-download-info", arrayList2);
        resultIntent.putExtra("pick-result-data", new ArrayList(Arrays.asList(this.mResults)));
        resultIntent.setFlags(1);
        doCompleteOperation(resultIntent);
    }

    /* access modifiers changed from: private */
    public void parseResult() {
        Uri[] uriArr;
        if (this.mResults.length == 0) {
            finish();
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (true) {
            uriArr = this.mResults;
            if (i >= uriArr.length) {
                break;
            }
            if (uriArr[i] != null) {
                arrayList.add(uriArr[i]);
                arrayList2.add(this.mSha1s[i]);
            }
            i++;
        }
        if (uriArr.length != arrayList.size()) {
            this.mResults = (Uri[]) arrayList.toArray(new Uri[arrayList.size()]);
            this.mSha1s = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
            if (this.mResults.length == 0) {
                finish();
                return;
            }
        }
        Picker.ResultType resultType = getPicker().getResultType();
        Log.d("PickGalleryActivity", "parse raw results[%s] for %s", this.mResults, resultType);
        if (resultType == Picker.ResultType.OPEN_URI) {
            int i2 = 0;
            while (true) {
                Uri[] uriArr2 = this.mResults;
                if (i2 >= uriArr2.length) {
                    break;
                }
                uriArr2[i2] = GalleryOpenProvider.translateToContent(uriArr2[i2].getPath());
                i2++;
            }
        }
        if (getPicker().getImageType() == Picker.ImageType.ORIGIN_OR_DOWNLOAD_INFO) {
            this.mOriginUrlRequestor = new OriginUrlRequestor(this.mOriginRequestPendings, new OriginUrlRequestProgressListener());
            this.mOriginUrlRequestor.start(this.mResults, this.mSha1s);
            return;
        }
        Intent resultIntent = getResultIntent();
        Picker.MediaType mediaType = getPicker().getMediaType();
        if (getPicker().getMode() == Picker.Mode.MULTIPLE || getPicker().getMode() == Picker.Mode.UNLIMITED) {
            ClipData clipData = mediaType == Picker.MediaType.IMAGE ? new ClipData("data", new String[]{"image/*", "text/uri-list"}, new ClipData.Item(this.mResults[0])) : mediaType == Picker.MediaType.ALL ? new ClipData("data", new String[]{"image/*", "video/*", "text/uri-list"}, new ClipData.Item(this.mResults[0])) : new ClipData("data", new String[]{"video/*", "text/uri-list"}, new ClipData.Item(this.mResults[0]));
            int i3 = 1;
            while (true) {
                Uri[] uriArr3 = this.mResults;
                if (i3 >= uriArr3.length) {
                    break;
                }
                clipData.addItem(new ClipData.Item(uriArr3[i3]));
                i3++;
            }
            resultIntent.setClipData(clipData);
            resultIntent.putExtra("pick-result-data", new ArrayList(Arrays.asList(this.mResults)));
        } else if (resultType == Picker.ResultType.OPEN_URI) {
            Uri uri = this.mResults[0];
            resultIntent.setDataAndType(uri, getContentResolver().getType(uri));
        } else {
            parseSingle(resultIntent, this.mResults[0].getPath());
        }
        resultIntent.setFlags(1);
        doCompleteOperation(resultIntent);
    }

    private void parseSingle(Intent intent, String str) {
        if (getPicker().getResultType() == Picker.ResultType.LEGACY_MEDIA) {
            Pair<Uri, String> queryMediaUri = queryMediaUri(str);
            if (queryMediaUri != null) {
                intent.setDataAndType((Uri) queryMediaUri.first, (String) queryMediaUri.second);
                return;
            }
            return;
        }
        Uri fromFile = Uri.fromFile(new File(str));
        String mimeType = FileMimeUtil.getMimeType(str);
        if ("*/*".equals(mimeType)) {
            intent.setData(fromFile);
        } else {
            intent.setDataAndType(fromFile, mimeType);
        }
    }

    /* access modifiers changed from: private */
    public void prepareResult(Cursor cursor) {
        int count = cursor.getCount();
        this.mResults = new Uri[count];
        this.mSha1s = new String[count];
        this.mDownloadPendings = new ArrayList<>();
        this.mOriginRequestPendings = new ArrayList<>();
        int i = 0;
        while (cursor.moveToNext()) {
            int position = cursor.getPosition();
            this.mSha1s[position] = cursor.getString(1);
            String string = cursor.getString(4);
            long j = cursor.getLong(0);
            if (ImageFeatureCacheManager.getInstance().isBestImage(j, false, false, (List<Long>) null)) {
                i++;
            }
            if (!TextUtils.isEmpty(string)) {
                File file = new File(string);
                if (file.exists()) {
                    this.mResults[position] = Uri.fromFile(file);
                }
            }
            Picker.ImageType imageType = getPicker().getImageType();
            if (imageType == Picker.ImageType.ORIGIN) {
                this.mDownloadPendings.add(new Downloader.DownloadTask(CloudUriAdapter.getDownloadUri(j), NetworkUtils.isActiveNetworkMetered() ? DownloadType.ORIGIN_FORCE : DownloadType.ORIGIN, cursor.getInt(6), position));
            } else {
                if (imageType == Picker.ImageType.ORIGIN_OR_DOWNLOAD_INFO) {
                    this.mOriginRequestPendings.add(new OriginUrlRequestor.OriginUrlRequestTask(position, CloudUriAdapter.getDownloadUri(j), cursor.getInt(7), cursor.getInt(8), cursor.getInt(5)));
                }
                String string2 = cursor.getString(3);
                if (!TextUtils.isEmpty(string2)) {
                    File file2 = new File(string2);
                    if (file2.exists()) {
                        this.mResults[position] = Uri.fromFile(file2);
                    }
                }
                this.mDownloadPendings.add(new Downloader.DownloadTask(CloudUriAdapter.getDownloadUri(j), DownloadType.THUMBNAIL, 0, position));
            }
        }
        statBestImageCount(count, i);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.util.Pair<android.net.Uri, java.lang.String>, android.database.Cursor] */
    private Pair<Uri, String> queryMediaUri(String str) {
        Cursor cursor;
        String str2;
        Uri uri;
        Cursor cursor2 = 0;
        if (TextUtils.isEmpty(str)) {
            return cursor2;
        }
        try {
            if (getPicker().getMediaType() == Picker.MediaType.IMAGE) {
                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                str2 = "vnd.android.cursor.dir/image";
                cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, (String) null);
            } else {
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                str2 = "vnd.android.cursor.dir/video";
                cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{str}, (String) null);
            }
            cursor2 = cursor;
            if (cursor2 != null && cursor2.moveToFirst()) {
                uri = uri.buildUpon().appendPath(cursor2.getString(0)).build();
            }
            return new Pair<>(uri, str2);
        } finally {
            if (cursor2 != 0) {
                cursor2.close();
            }
        }
    }

    private void sendStatistics() {
        String callingPackage = getCallingPackage();
        if (TextUtils.isEmpty(callingPackage)) {
            callingPackage = "unknown";
        }
        GallerySamplingStatHelper.recordStringPropertyEvent("picker", "open_picker", callingPackage);
        GallerySamplingStatHelper.recordNumericPropertyEvent("best_image", "best_image_count", (long) ImageFeatureCacheManager.getInstance().getBestImageCount(false));
    }

    private void setupTabFragments() {
        this.mActionBar.addFragmentTab("PickHomePageFragment", this.mActionBar.newTab().setText(R.string.home_page_label), PickHomePageFragment.class, (Bundle) null, false);
        this.mActionBar.addFragmentTab("PickAlbumPageFragment", this.mActionBar.newTab().setText(R.string.album_page_label), PickAlbumPageFragment.class, (Bundle) null, false);
        this.mActionBar.addOnFragmentViewPagerChangeListener(new ActionBar.FragmentViewPagerChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, boolean z, boolean z2) {
            }

            /* JADX WARNING: type inference failed for: r0v4, types: [com.miui.gallery.picker.PickGalleryActivity, android.app.Activity] */
            /* JADX WARNING: type inference failed for: r0v5, types: [com.miui.gallery.picker.PickGalleryActivity, android.app.Activity] */
            public void onPageSelected(int i) {
                Log.d("PickGalleryActivity", "onPageSelected %d", (Object) Integer.valueOf(i));
                if (PickGalleryActivity.this.mCurrentPagePosition != i) {
                    ? r0 = PickGalleryActivity.this;
                    GallerySamplingStatHelper.recordPageEnd(r0, r0.getPageName(r0.mCurrentPagePosition));
                    ? r02 = PickGalleryActivity.this;
                    GallerySamplingStatHelper.recordPageStart(r02, r02.getPageName(i));
                }
                int unused = PickGalleryActivity.this.mCurrentPagePosition = i;
            }
        });
    }

    /* access modifiers changed from: private */
    public void showConfirmDialog(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("download_file_count", this.mDownloadPendings.size());
        Uri[] uriArr = this.mResults;
        int i = 0;
        bundle.putInt("local_file_count", uriArr == null ? 0 : uriArr.length);
        Iterator<Downloader.DownloadTask> it = this.mDownloadPendings.iterator();
        while (it.hasNext()) {
            i += it.next().mSize;
        }
        bundle.putInt("download_file_size", i);
        bundle.putBoolean("retry_mode", z);
        DownloadConfirmDialog downloadConfirmDialog = new DownloadConfirmDialog();
        downloadConfirmDialog.setArguments(bundle);
        downloadConfirmDialog.showAllowingStateLoss(getFragmentManager(), "confirm_dialog");
    }

    /* access modifiers changed from: private */
    public void startDownload() {
        Downloader downloader = this.mDownloader;
        if (downloader != null) {
            downloader.cancel();
            this.mDownloader.destroy();
        }
        this.mDownloader = new Downloader(this.mDownloadPendings, new DownloadProgressListener());
        this.mDownloader.start();
    }

    /* access modifiers changed from: private */
    public void statBestImageCount(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("count", Integer.valueOf(i));
        hashMap.put("best_image_count", Integer.valueOf(i2));
        GallerySamplingStatHelper.recordCountEvent("picker", "pick_result", hashMap);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomContentView() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 42 && i2 == -1) {
            finish();
        }
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof DownloadConfirmDialog) {
            DownloadConfirmDialog downloadConfirmDialog = (DownloadConfirmDialog) fragment;
            downloadConfirmDialog.setPositiveListener(this.mDownloadListener);
            downloadConfirmDialog.setNegativeListener(this.mCancelDownloadListener);
        } else if (fragment instanceof PrepareProgressDialog) {
            ((PrepareProgressDialog) fragment).setCancelListener(this.mCancelConfirmListener);
        } else if (fragment instanceof DownloadCancelDialog) {
            DownloadCancelDialog downloadCancelDialog = (DownloadCancelDialog) fragment;
            downloadCancelDialog.setCancelListener(this.mCancelDownloadListener);
            downloadCancelDialog.setContinueListener(this.mContinueDownloadListener);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.miui.gallery.picker.PickGalleryActivity, com.miui.gallery.picker.PickerActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mPicker != null) {
            sendStatistics();
            getActionBar().setFragmentViewPagerMode(this, getFragmentManager());
            setupTabFragments();
            if (bundle != null) {
                Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("confirm_dialog");
                Fragment findFragmentByTag2 = getFragmentManager().findFragmentByTag("prepare_progress_dialog");
                Fragment findFragmentByTag3 = getFragmentManager().findFragmentByTag("cancel_dialog");
                if (!(findFragmentByTag == null && findFragmentByTag2 == null && findFragmentByTag3 == null)) {
                    FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                    if (findFragmentByTag != null) {
                        beginTransaction.remove(findFragmentByTag);
                    }
                    if (findFragmentByTag2 != null) {
                        beginTransaction.remove(findFragmentByTag2);
                    }
                    if (findFragmentByTag3 != null) {
                        beginTransaction.remove(findFragmentByTag3);
                    }
                    beginTransaction.commit();
                    getFragmentManager().executePendingTransactions();
                }
            }
            this.mPickIntent = (Intent) getIntent().getParcelableExtra("pick_intent");
            this.mCloseType = getIntent().getIntExtra("pick_close_type", 1);
            ImageSelectionTipFragment.showImageSelectionTipDialogIfNecessary(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Downloader downloader = this.mDownloader;
        if (downloader != null) {
            downloader.cancel();
            this.mDownloader.destroy();
        }
        ArrayList<Downloader.DownloadTask> arrayList = this.mDownloadPendings;
        if (arrayList != null) {
            arrayList.clear();
        }
        if (this.mResults != null) {
            this.mResults = null;
        }
        if (this.mSha1s != null) {
            this.mSha1s = null;
        }
        OriginUrlRequestor originUrlRequestor = this.mOriginUrlRequestor;
        if (originUrlRequestor != null) {
            originUrlRequestor.cancel();
        }
        ArrayList<OriginUrlRequestor.OriginUrlRequestTask> arrayList2 = this.mOriginRequestPendings;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void onDone() {
        if (this.mParseTask == null) {
            this.mParseTask = new ParseTask(getPicker());
            this.mParseTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        Log.w("PickGalleryActivity", "parse task is running, skip");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.picker.PickGalleryActivity, com.miui.gallery.picker.PickerActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        GallerySamplingStatHelper.recordPageEnd(this, getPageName(this.mCurrentPagePosition));
        Downloader downloader = this.mDownloader;
        if (downloader != null) {
            downloader.pause();
        }
    }

    public void onPermissionsChecked(RuntimePermission[] runtimePermissionArr, int[] iArr) {
        super.onPermissionsChecked(runtimePermissionArr, iArr);
        if (!isCrossUserPick()) {
            ThreadManager.getMiscPool().submit(new MediaScanJob());
        } else {
            dispatchPermissionChecked(runtimePermissionArr, iArr);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.miui.gallery.picker.PickGalleryActivity, com.miui.gallery.picker.PickerActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        GallerySamplingStatHelper.recordPageStart(this, getPageName(this.mCurrentPagePosition));
        Downloader downloader = this.mDownloader;
        if (downloader != null) {
            downloader.resume();
        }
    }
}
