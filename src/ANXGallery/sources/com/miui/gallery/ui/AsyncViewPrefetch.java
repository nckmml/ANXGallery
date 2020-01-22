package com.miui.gallery.ui;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import com.miui.gallery.util.Log;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

public abstract class AsyncViewPrefetch implements AsyncLayoutInflater.OnInflateFinishedListener, ViewProvider {
    private int mCount;
    private AsyncLayoutInflater mInflater;
    private WeakReference<ViewGroup> mParent;
    private ViewPool mPool = new ViewPool();
    private int mPosition;
    private volatile Boolean mStarted;
    private long mTimer;

    private static class ViewPool {
        private SparseArray<ScrapData> mCache;

        private static class ScrapData {
            final LinkedList<View> mHeap;

            private ScrapData() {
                this.mHeap = new LinkedList<>();
            }

            /* access modifiers changed from: package-private */
            public View pop() {
                if (this.mHeap.isEmpty()) {
                    return null;
                }
                return this.mHeap.pop();
            }

            /* access modifiers changed from: package-private */
            public void push(View view) {
                this.mHeap.add(view);
            }

            /* access modifiers changed from: package-private */
            public int size() {
                return this.mHeap.size();
            }
        }

        private ViewPool() {
            this.mCache = new SparseArray<>();
        }

        /* access modifiers changed from: package-private */
        public synchronized View pop(int i) {
            ScrapData scrapData = this.mCache.get(i);
            if (scrapData == null) {
                return null;
            }
            return scrapData.pop();
        }

        /* access modifiers changed from: package-private */
        public synchronized void push(int i, View view) {
            ScrapData scrapData = this.mCache.get(i);
            if (scrapData == null) {
                scrapData = new ScrapData();
                this.mCache.put(i, scrapData);
            }
            scrapData.push(view);
        }

        /* access modifiers changed from: package-private */
        public synchronized int size() {
            int i;
            i = 0;
            for (int i2 = 0; i2 < this.mCache.size(); i2++) {
                i += this.mCache.valueAt(i2).size();
            }
            return i;
        }
    }

    public AsyncViewPrefetch(Context context, ViewGroup viewGroup, int i) {
        this.mInflater = new AsyncLayoutInflater(context);
        this.mCount = i;
        this.mParent = new WeakReference<>(viewGroup);
    }

    private void doFetch() {
        int viewResId = getViewResId(getItemViewType(this.mPosition));
        if (viewResId > 0) {
            this.mInflater.inflate(viewResId, (ViewGroup) this.mParent.get(), this);
            return;
        }
        throw new IllegalArgumentException("invalid res id");
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public View getViewByType(int i) {
        boolean booleanValue = this.mStarted.booleanValue();
        View pop = this.mPool.pop(i);
        if (booleanValue) {
            Log.d("AsyncViewPrefetch", "prefetch view %s, time %d, size %d", pop, Long.valueOf(System.currentTimeMillis() - this.mTimer), Integer.valueOf(this.mPool.size()));
        }
        return pop;
    }

    public final void onInflateFinished(View view, int i, ViewGroup viewGroup) {
        Log.d("AsyncViewPrefetch", "onInflateFinished");
        this.mPool.push(getItemViewType(this.mPosition), view);
        boolean z = true;
        this.mPosition++;
        if (this.mPosition >= this.mCount) {
            z = false;
        }
        this.mStarted = Boolean.valueOf(z);
        if (this.mStarted.booleanValue()) {
            doFetch();
        }
    }

    public void prefetch() {
        if (this.mStarted == null) {
            Log.d("AsyncViewPrefetch", "prefetch starting, size %d", (Object) Integer.valueOf(this.mCount));
            this.mStarted = true;
            this.mTimer = System.currentTimeMillis();
            doFetch();
            return;
        }
        throw new IllegalStateException("has started");
    }
}
