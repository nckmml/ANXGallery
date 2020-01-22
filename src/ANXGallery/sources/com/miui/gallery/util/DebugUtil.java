package com.miui.gallery.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.preference.ThumbnailPreference;
import com.miui.gallery.provider.CloudUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.GalleryDBHelper;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.ui.ProcessTask;
import com.miui.gallery.ui.ProgressDialogFragment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class DebugUtil {
    private static AtomicBoolean sDBExporting = new AtomicBoolean(false);
    private static Pattern sDupPattern;

    private static class DebugTask extends AsyncTask<Void, Void, Void> {
        private static AtomicBoolean sDebugging = new AtomicBoolean(false);
        private WeakReference<Activity> mActivityRef;
        private ProgressDialogFragment mProgress;

        DebugTask(Activity activity) {
            this.mActivityRef = new WeakReference<>(activity);
        }

        private Activity getActivity() {
            Activity activity;
            WeakReference<Activity> weakReference = this.mActivityRef;
            if (weakReference != null && (activity = (Activity) weakReference.get()) != null) {
                return activity;
            }
            ProgressDialogFragment progressDialogFragment = this.mProgress;
            if (progressDialogFragment == null) {
                return null;
            }
            progressDialogFragment.dismissAllowingStateLoss();
            return null;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            if (sDebugging.get()) {
                return null;
            }
            sDebugging.set(true);
            DebugUtil.doExportDB();
            sDebugging.set(false);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            ProgressDialogFragment progressDialogFragment = this.mProgress;
            if (progressDialogFragment != null && progressDialogFragment.getFragmentManager() != null) {
                this.mProgress.dismissAllowingStateLoss();
                final Activity activity = getActivity();
                if (activity != null) {
                    DialogUtil.showConfirmAlert(activity, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String access$100 = DebugUtil.getDebugPath();
                            if (!TextUtils.isEmpty(access$100)) {
                                IntentUtil.jumpToExplore(activity, access$100);
                            }
                        }
                    }, activity.getString(R.string.title_tip), activity.getString(R.string.debugging_info), activity.getString(R.string.ok));
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            Activity activity = getActivity();
            if (activity != null) {
                this.mProgress = new ProgressDialogFragment();
                this.mProgress.setTitle(activity.getResources().getString(R.string.debugging_tip));
                this.mProgress.setCancelable(false);
                this.mProgress.showAllowingStateLoss(activity.getFragmentManager(), "DebugUtil");
            }
        }
    }

    public static void clearThumbnailErrorLog(Context context) {
        ThumbnailPreference.clear();
        ToastUtils.makeText(context, (int) R.string.thumbnail_error_log_cleared);
    }

    public static void correctPhotoTime(Activity activity) {
        final Application application = activity.getApplication();
        ProcessTask processTask = new ProcessTask(new ProcessTask.ProcessCallback<Void, Void>() {
            public Void doProcess(Void[] voidArr) {
                DebugUtil.doCorrectPhotoTime(application);
                return null;
            }
        });
        processTask.setCompleteListener(new ProcessTask.OnCompleteListener() {
            public void onCompleteProcess(Object obj) {
                ToastUtils.makeText(application, (int) R.string.correct_time_success);
            }
        });
        processTask.showProgress(activity, activity.getString(R.string.correct_time_tip));
        processTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static void deleteDupMedias(Activity activity) {
        final Application application = activity.getApplication();
        ProcessTask processTask = new ProcessTask(new ProcessTask.ProcessCallback<Void, Void>() {
            public Void doProcess(Void[] voidArr) {
                DebugUtil.doDeleteDupMedias(application);
                return null;
            }
        });
        processTask.setCompleteListener(new ProcessTask.OnCompleteListener() {
            public void onCompleteProcess(Object obj) {
                ToastUtils.makeText(application, (int) R.string.clear_dup_success);
            }
        });
        processTask.showProgress(activity, activity.getString(R.string.clear_dup_tip));
        processTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public static void doCorrectPhotoTime(Context context) {
        GalleryDBHelper.getInstance(context).getWritableDatabase().execSQL("UPDATE cloud SET mixedDateTime=dateTaken WHERE _id IN (SELECT _id FROM cloud WHERE serverType IN (1,2) AND exifGPSDateStamp LIKE '1970%' AND dateTaken > mixedDateTime)");
    }

    /* access modifiers changed from: private */
    public static void doDeleteDupMedias(Context context) {
        String[] strArr = {"_id", "fileName", "localGroupId", "localFile", "size"};
        LinkedList linkedList = new LinkedList();
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, strArr, "size < 1000000 AND serverType = 1 AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", (String[]) null, (String) null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String string = cursor.getString(1);
                    long j = cursor.getLong(2);
                    String maybeDupMediaName = maybeDupMediaName(string);
                    if (!TextUtils.isEmpty(maybeDupMediaName)) {
                        String hasMedia = hasMedia(context, "title = ? AND localGroupId = ? AND _id != ? AND size >= ? AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{FileUtils.getFileTitle(maybeDupMediaName), String.valueOf(j), String.valueOf(cursor.getLong(0)), String.valueOf(cursor.getLong(4))});
                        if (!TextUtils.isEmpty(hasMedia)) {
                            Log.d("DebugUtil", "delete dup pair origin: %s, dup: %s, %s", hasMedia, string, Long.valueOf(cursor.getLong(2)));
                            linkedList.add(Long.valueOf(cursor.getLong(0)));
                        }
                    } else {
                        String hasMedia2 = hasMedia(context, "title = ? AND localGroupId = ? AND _id != ? AND size >= ? AND mimeType != ? AND (localFlag NOT IN (11, 0, -1, 2, 15) OR (localFlag=0 AND serverStatus='custom'))", new String[]{FileUtils.getFileTitle(string), String.valueOf(j), String.valueOf(cursor.getLong(0)), String.valueOf(cursor.getLong(4)), "image/jpeg"});
                        if (!TextUtils.isEmpty(hasMedia2)) {
                            Log.d("DebugUtil", "delete dup pair origin: %s, dup: %s, %s", hasMedia2, string, Long.valueOf(cursor.getLong(2)));
                            linkedList.add(Long.valueOf(cursor.getLong(0)));
                        }
                    }
                }
            }
            Log.d("DebugUtil", "delete dup count %s", (Object) Integer.valueOf(linkedList.size()));
            CloudUtils.deleteById(context, -1, MiscUtil.listToArray(linkedList));
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* access modifiers changed from: private */
    public static void doExportDB() {
        if (!sDBExporting.get()) {
            sDBExporting.set(true);
            File file = new File(getDebugPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                exportFile(new File(GalleryApp.sGetAndroidContext().getFilesDir().getParentFile(), "databases/gallery.db"), new File(file, "DBTest.db"));
                exportFile(new File(GalleryApp.sGetAndroidContext().getFilesDir().getParentFile(), "databases/gallery_sub.db"), new File(file, "DBTest_sub.db"));
                exportFile(new File(GalleryApp.sGetAndroidContext().getFilesDir().getParentFile(), "databases/gallery_lite.db"), new File(file, "DBTest_lite.db"));
            } catch (Exception e) {
                Log.w("DebugUtil", "Error occurred while exporting db, %s", e);
            } catch (Throwable th) {
                sDBExporting.set(false);
                throw th;
            }
            sDBExporting.set(false);
        }
    }

    public static void exportDB(boolean z) {
        if (z) {
            ThreadManager.getMiscPool().submit($$Lambda$DebugUtil$ms11Qfle9p6XI1kMKfuNMe1FFuw.INSTANCE);
        } else {
            doExportDB();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0045, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0049, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004a, code lost:
        r11 = r2;
        r2 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005e, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x005f, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0063, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0064, code lost:
        r11 = r2;
        r2 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0076, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0077, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x007b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x007c, code lost:
        r11 = r2;
        r2 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x005e A[ExcHandler: all (th java.lang.Throwable)] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0076 A[ExcHandler: all (th java.lang.Throwable)] */
    private static void exportFile(File file, File file2) {
        FileInputStream fileInputStream;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        try {
            fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            FileChannel channel2 = fileOutputStream.getChannel();
            channel2.transferFrom(channel, 0, channel.size());
            Log.d("DebugUtil", "Done exporting file: %s", (Object) file2.getPath());
            MediaFileUtils.triggerMediaScan(true, file2);
            if (channel2 != null) {
                channel2.close();
            }
            fileOutputStream.close();
            if (channel != null) {
                channel.close();
            }
            fileInputStream.close();
            return;
            throw th;
            throw th3;
            if (th4 != null) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th7) {
                }
            } else {
                fileOutputStream.close();
            }
            throw th3;
            if (channel2 != null) {
                if (th6 != null) {
                    try {
                        channel2.close();
                    } catch (Throwable th8) {
                    }
                } else {
                    channel2.close();
                }
            }
            throw th5;
            throw th5;
            if (channel != null) {
                if (th2 != null) {
                    try {
                        channel.close();
                    } catch (Throwable th9) {
                        th2.addSuppressed(th9);
                    }
                } else {
                    channel.close();
                }
            }
            throw th;
            throw th;
        } catch (Exception e) {
            Log.e("DebugUtil", (Throwable) e);
        } catch (Throwable th10) {
            r12.addSuppressed(th10);
        }
    }

    public static void generateDebugLog(Activity activity) {
        new DebugTask(activity).execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public static String getDebugPath() {
        return StorageUtils.getPathInPrimaryStorage("MIUI/Gallery/Debug");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033  */
    private static String hasMedia(Context context, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, new String[]{"fileName"}, str, strArr, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(0);
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private static String maybeDupMediaName(String str) {
        if (sDupPattern == null) {
            sDupPattern = Pattern.compile("^1[0-9]{12}");
        }
        String[] split = FileUtils.getFileTitle(str).split("_");
        if (split != null && split.length > 1) {
            int length = split[split.length - 1].equalsIgnoreCase("_BURST") ? split.length - 2 : split.length - 1;
            String str2 = (length <= -1 || !sDupPattern.matcher(split[length]).matches()) ? null : split[length];
            if (str2 != null && str2.length() >= 13) {
                StringBuilder sb = new StringBuilder();
                int indexOf = str.indexOf(str2);
                sb.append(str.substring(0, indexOf - 1));
                sb.append(str.substring(indexOf + 13));
                return sb.toString();
            }
        }
        return null;
    }
}
