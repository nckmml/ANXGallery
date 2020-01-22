package com.miui.gallery.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.miui.gallery.dao.GalleryLiteEntityManager;
import com.miui.gallery.model.Album;
import java.util.List;

public class AlbumSnapshotLoader extends AsyncTaskLoader<List<Album>> {
    private List<Album> mDataSet;
    private String mSelection;
    private String[] mSelectionArgs;

    public AlbumSnapshotLoader(Context context) {
        super(context);
    }

    public final void deliverResult(List<Album> list) {
        if (!isReset()) {
            List<Album> list2 = this.mDataSet;
            this.mDataSet = list;
            if (isStarted()) {
                super.deliverResult(list);
            }
            if (list2 != null && list2 != list) {
                list2.clear();
            }
        } else if (list != null) {
            list.clear();
        }
    }

    public String getSelection() {
        return this.mSelection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    public List<Album> loadInBackground() {
        return GalleryLiteEntityManager.getInstance().query(Album.class, getSelection(), getSelectionArgs());
    }

    public final void onCanceled(List<Album> list) {
        if (list != null) {
            list.clear();
        }
    }

    /* access modifiers changed from: protected */
    public final void onReset() {
        super.onReset();
        onStopLoading();
        List<Album> list = this.mDataSet;
        if (list != null) {
            list.clear();
            this.mDataSet = null;
        }
    }

    /* access modifiers changed from: protected */
    public final void onStartLoading() {
        List<Album> list = this.mDataSet;
        if (list != null) {
            deliverResult(list);
        }
        if (takeContentChanged() || this.mDataSet == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }

    public void setSelection(String str) {
        this.mSelection = str;
    }
}
