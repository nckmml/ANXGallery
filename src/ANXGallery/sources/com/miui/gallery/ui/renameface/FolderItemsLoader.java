package com.miui.gallery.ui.renameface;

import android.app.Activity;
import android.os.AsyncTask;
import com.miui.gallery.model.DisplayFolderItem;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

public abstract class FolderItemsLoader {
    protected SoftReference<Activity> mActivityRef;
    /* access modifiers changed from: private */
    public boolean mCancelled;
    protected ArrayList<DisplayFolderItem> mItems = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<DisplayFolderItem> mLoadedItems = null;
    /* access modifiers changed from: private */
    public final LoaderUpdatedItems mOutListener;
    private String mPath;
    private long[] mSelectedFoldersLocalID;

    public interface LoaderUpdatedItems {
        void onLoaderUpdatedItems();
    }

    public FolderItemsLoader(Activity activity, String str, LoaderUpdatedItems loaderUpdatedItems, long[] jArr, boolean z) {
        this.mPath = str;
        this.mOutListener = loaderUpdatedItems;
        this.mSelectedFoldersLocalID = jArr;
        this.mActivityRef = new SoftReference<>(activity);
        reload();
    }

    private void reload() {
        new AsyncTask<Void, Void, ArrayList<DisplayFolderItem>>() {
            /* access modifiers changed from: protected */
            public ArrayList<DisplayFolderItem> doInBackground(Void... voidArr) {
                FolderItemsLoader folderItemsLoader = FolderItemsLoader.this;
                ArrayList unused = folderItemsLoader.mLoadedItems = folderItemsLoader.refreshCloudFolderItems();
                return FolderItemsLoader.this.mLoadedItems;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(ArrayList<DisplayFolderItem> arrayList) {
                FolderItemsLoader folderItemsLoader = FolderItemsLoader.this;
                folderItemsLoader.mItems = arrayList;
                if (!folderItemsLoader.mCancelled && FolderItemsLoader.this.mOutListener != null) {
                    FolderItemsLoader.this.mOutListener.onLoaderUpdatedItems();
                }
            }
        }.execute(new Void[0]);
    }

    public void cancel() {
        this.mCancelled = true;
    }

    /* access modifiers changed from: protected */
    public boolean isMediaSetCandidate(String str) {
        boolean z = false;
        if (this.mSelectedFoldersLocalID != null) {
            int i = 0;
            while (true) {
                if (i >= this.mSelectedFoldersLocalID.length) {
                    break;
                } else if (Long.parseLong(str) == this.mSelectedFoldersLocalID[i]) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
        }
        return !z;
    }

    /* access modifiers changed from: protected */
    public ArrayList<DisplayFolderItem> refreshCloudFolderItems() {
        return new ArrayList<>();
    }

    public final ArrayList<DisplayFolderItem> tryToGetLoadedItems(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        do {
            ArrayList<DisplayFolderItem> arrayList = this.mLoadedItems;
            if (arrayList != null) {
                return arrayList;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException unused) {
            }
        } while (System.currentTimeMillis() - currentTimeMillis < j);
        return null;
    }

    public final ArrayList<DisplayFolderItem> unblockGetItems() {
        return this.mItems;
    }
}
