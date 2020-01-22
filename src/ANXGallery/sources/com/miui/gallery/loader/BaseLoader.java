package com.miui.gallery.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.miui.gallery.model.BaseDataSet;

public abstract class BaseLoader extends AsyncTaskLoader<BaseDataSet> {
    private OnLoadCompleteListener mBackgroundLoadListener;
    protected BaseDataSet mDataSet;

    public interface OnLoadCompleteListener {
        void onLoadComplete(BaseLoader baseLoader, BaseDataSet baseDataSet);
    }

    public BaseLoader(Context context) {
        super(context);
    }

    public final void deliverResult(BaseDataSet baseDataSet) {
        if (!isReset()) {
            BaseDataSet baseDataSet2 = this.mDataSet;
            this.mDataSet = baseDataSet;
            if (isStarted()) {
                super.deliverResult(baseDataSet);
            } else {
                OnLoadCompleteListener onLoadCompleteListener = this.mBackgroundLoadListener;
                if (onLoadCompleteListener != null) {
                    onLoadCompleteListener.onLoadComplete(this, baseDataSet);
                }
            }
            if (baseDataSet2 != null && baseDataSet2 != baseDataSet) {
                baseDataSet2.release();
            }
        } else if (baseDataSet != null) {
            baseDataSet.release();
        }
    }

    public final void onCanceled(BaseDataSet baseDataSet) {
        OnLoadCompleteListener onLoadCompleteListener;
        if (baseDataSet != null) {
            baseDataSet.release();
        }
        if (!isStarted() && (onLoadCompleteListener = this.mBackgroundLoadListener) != null) {
            onLoadCompleteListener.onLoadComplete(this, (BaseDataSet) null);
        }
    }

    /* access modifiers changed from: protected */
    public final void onReset() {
        super.onReset();
        onStopLoading();
        BaseDataSet baseDataSet = this.mDataSet;
        if (baseDataSet != null) {
            baseDataSet.release();
            this.mDataSet = null;
        }
    }

    /* access modifiers changed from: protected */
    public final void onStartLoading() {
        BaseDataSet baseDataSet = this.mDataSet;
        if (baseDataSet != null) {
            deliverResult(baseDataSet);
        }
        if (takeContentChanged() || this.mDataSet == null) {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public final void onStopLoading() {
        cancelLoad();
    }

    public void setBackgroundLoadListener(OnLoadCompleteListener onLoadCompleteListener) {
        this.mBackgroundLoadListener = onLoadCompleteListener;
    }
}
