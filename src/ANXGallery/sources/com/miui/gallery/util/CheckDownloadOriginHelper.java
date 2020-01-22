package com.miui.gallery.util;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.miui.gallery.R;
import com.miui.gallery.cloud.NetworkUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.ShareMediaManager;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.ui.DownloadFragment;
import com.miui.gallery.util.BulkDownloadHelper;
import com.miui.gallery.util.SafeDBUtil;
import com.miui.gallery.util.uil.CloudUriAdapter;
import java.util.ArrayList;
import java.util.List;

public class CheckDownloadOriginHelper {
    /* access modifiers changed from: private */
    public long mAlbumId;
    /* access modifiers changed from: private */
    public Context mContext;
    private FragmentManager mFragmentManger;
    /* access modifiers changed from: private */
    public CheckDownloadOriginListener mListener;
    /* access modifiers changed from: private */
    public long[] mMediaIds;

    public interface CheckDownloadOriginListener {
        void onCanceled();

        void onComplete();

        void onStartDownload();
    }

    class CheckOriginTask extends AsyncTask<Void, Void, List<MediaItem>> {
        private final String[] PROJECTION = {"_id", "size", "localFile"};
        private Context mContext;

        public CheckOriginTask(Context context) {
            this.mContext = context;
        }

        private boolean isLocalAlbum() {
            Integer num = (Integer) SafeDBUtil.safeQuery(this.mContext, GalleryContract.Cloud.CLOUD_URI, new String[0], "_id = ? AND attributes&1 =0 ", new String[]{String.valueOf(CheckDownloadOriginHelper.this.mAlbumId)}, (String) null, new SafeDBUtil.QueryHandler<Integer>() {
                public Integer handle(Cursor cursor) {
                    return Integer.valueOf(cursor == null ? 0 : cursor.getCount());
                }
            });
            return num != null && num.intValue() > 0;
        }

        private List<MediaItem> queryMediaItemByIds() {
            StringBuilder sb = new StringBuilder();
            sb.append("_id IN (");
            sb.append(TextUtils.join(",", MiscUtil.arrayToList(CheckDownloadOriginHelper.this.mMediaIds)));
            sb.append(") AND ");
            sb.append("localFlag");
            sb.append("=");
            boolean z = false;
            sb.append(0);
            String sb2 = sb.toString();
            long[] access$500 = CheckDownloadOriginHelper.this.mMediaIds;
            int length = access$500.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (ShareMediaManager.isOtherShareMediaId(access$500[i])) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            return (List) SafeDBUtil.safeQuery(this.mContext, z ? GalleryContract.Media.URI_ALL : GalleryContract.Media.URI, this.PROJECTION, sb2, (String[]) null, (String) null, new SafeDBUtil.QueryHandler<List<MediaItem>>() {
                public List<MediaItem> handle(Cursor cursor) {
                    if (cursor == null || !cursor.moveToFirst()) {
                        return null;
                    }
                    ArrayList arrayList = null;
                    do {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        MediaItem mediaItem = new MediaItem();
                        mediaItem.mId = cursor.getLong(0);
                        mediaItem.mSize = cursor.getLong(1);
                        mediaItem.mPath = cursor.getString(2);
                        arrayList.add(mediaItem);
                    } while (cursor.moveToNext());
                    return arrayList;
                }
            });
        }

        /* access modifiers changed from: protected */
        public List<MediaItem> doInBackground(Void... voidArr) {
            ArrayList arrayList = null;
            if (!isLocalAlbum()) {
                Log.d("CheckDownloadOriginHelper", "album is not local");
                return null;
            }
            List<MediaItem> queryMediaItemByIds = queryMediaItemByIds();
            if (!MiscUtil.isValid(queryMediaItemByIds)) {
                return null;
            }
            for (MediaItem next : queryMediaItemByIds) {
                if (!FileUtils.isFileExist(next.mPath)) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(next);
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List<MediaItem> list) {
            if (MiscUtil.isValid(list)) {
                if (CheckDownloadOriginHelper.this.mListener != null) {
                    CheckDownloadOriginHelper.this.mListener.onStartDownload();
                }
                CheckDownloadOriginHelper.this.doDownloadOrigin(list, false);
                return;
            }
            Log.d("CheckDownloadOriginHelper", "no item to download");
            if (CheckDownloadOriginHelper.this.mListener != null) {
                CheckDownloadOriginHelper.this.mListener.onComplete();
            }
        }
    }

    private static class MediaItem {
        public long mId;
        public String mPath;
        public long mSize;

        private MediaItem() {
        }
    }

    public CheckDownloadOriginHelper(Context context, FragmentManager fragmentManager, long j, long[] jArr) {
        this.mContext = context;
        this.mFragmentManger = fragmentManager;
        this.mAlbumId = j;
        this.mMediaIds = jArr;
    }

    /* access modifiers changed from: private */
    public void doDownloadOrigin(final List<MediaItem> list, boolean z) {
        Log.d("CheckDownloadOriginHelper", "doDownloadOrigin %s", (Object) Integer.valueOf(list == null ? 0 : list.size()));
        if (!NetworkUtils.isActiveNetworkMetered() || z) {
            ArrayList arrayList = new ArrayList();
            for (MediaItem next : list) {
                arrayList.add(new BulkDownloadHelper.BulkDownloadItem(CloudUriAdapter.getDownloadUri(next.mId), DownloadType.ORIGIN_FORCE, next.mSize));
            }
            startDownloadOrigin(arrayList);
            return;
        }
        Context context = this.mContext;
        DialogUtil.showInfoDialog(context, false, context.getString(R.string.download_with_metered_network_msg), this.mContext.getString(R.string.download_with_metered_network_title), 17039370, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CheckDownloadOriginHelper.this.doDownloadOrigin(list, true);
            }
        }, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (CheckDownloadOriginHelper.this.mListener != null) {
                    CheckDownloadOriginHelper.this.mListener.onCanceled();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void startDownloadOrigin(ArrayList<BulkDownloadHelper.BulkDownloadItem> arrayList) {
        Log.d("CheckDownloadOriginHelper", "startDownloadOrigin %s", (Object) Integer.valueOf(arrayList == null ? 0 : arrayList.size()));
        AnonymousClass3 r0 = new DownloadFragment.OnDownloadListener() {
            public void onCanceled() {
                Log.d("CheckDownloadOriginHelper", "download canceled");
                if (CheckDownloadOriginHelper.this.mListener != null) {
                    CheckDownloadOriginHelper.this.mListener.onCanceled();
                }
            }

            public void onDownloadComplete(List<BulkDownloadHelper.BulkDownloadItem> list, List<BulkDownloadHelper.BulkDownloadItem> list2) {
                Log.d("CheckDownloadOriginHelper", "onDownloadComplete fails: %s", (Object) Integer.valueOf(list2 == null ? 0 : list2.size()));
                if (list2 != null && !list2.isEmpty()) {
                    final ArrayList arrayList = new ArrayList(list2);
                    DialogUtil.showInfoDialog(CheckDownloadOriginHelper.this.mContext, false, CheckDownloadOriginHelper.this.mContext.getString(R.string.download_retry_message), CheckDownloadOriginHelper.this.mContext.getString(R.string.download_retry_title), (int) R.string.download_retry_text, 17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CheckDownloadOriginHelper.this.startDownloadOrigin(arrayList);
                        }
                    }, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (CheckDownloadOriginHelper.this.mListener != null) {
                                CheckDownloadOriginHelper.this.mListener.onCanceled();
                            }
                        }
                    });
                } else if (CheckDownloadOriginHelper.this.mListener != null) {
                    CheckDownloadOriginHelper.this.mListener.onComplete();
                }
            }
        };
        DownloadFragment newInstance = DownloadFragment.newInstance(arrayList);
        newInstance.setOnDownloadListener(r0);
        newInstance.showAllowingStateLoss(this.mFragmentManger, "DownloadFragment");
    }

    public void setListener(CheckDownloadOriginListener checkDownloadOriginListener) {
        this.mListener = checkDownloadOriginListener;
    }

    public void start() {
        Log.d("CheckDownloadOriginHelper", "doCheckOrigin");
        new CheckOriginTask(this.mContext).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
