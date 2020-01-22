package com.miui.gallery.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.RectF;
import android.view.View;
import android.widget.AbsListView;
import com.miui.gallery.R;
import com.miui.gallery.adapter.CheckableAdapter;

public abstract class BaseMediaAdapterDeprecated extends BaseAdapter {
    protected int mViewScrollState = 0;

    private static class OnScrollListenerWrapper implements AbsListView.OnScrollListener {
        private final AbsListView.OnScrollListener mWrapped;

        public OnScrollListenerWrapper(AbsListView.OnScrollListener onScrollListener) {
            this.mWrapped = onScrollListener;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            AbsListView.OnScrollListener onScrollListener = this.mWrapped;
            if (onScrollListener != null) {
                onScrollListener.onScroll(absListView, i, i2, i3);
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            AbsListView.OnScrollListener onScrollListener = this.mWrapped;
            if (onScrollListener != null) {
                onScrollListener.onScrollStateChanged(absListView, i);
            }
        }
    }

    public BaseMediaAdapterDeprecated(Context context) {
        super(context);
    }

    public final void bindView(View view, Context context, Cursor cursor) {
        view.setTag(R.id.tag_item_position, Integer.valueOf(cursor.getPosition()));
        doBindView(view, context, cursor);
    }

    /* access modifiers changed from: protected */
    public abstract void doBindView(View view, Context context, Cursor cursor);

    public AbsListView.OnScrollListener generateWrapScrollListener(AbsListView.OnScrollListener onScrollListener) {
        return new OnScrollListenerWrapper(onScrollListener) {
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                super.onScroll(absListView, i, i2, i3);
                BaseMediaAdapterDeprecated.this.onViewScrolled(absListView, i, i2, i3);
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                super.onScrollStateChanged(absListView, i);
                BaseMediaAdapterDeprecated.this.onViewScrollStateChanged(absListView, i);
            }
        };
    }

    public CheckableAdapter.CheckedItem getCheckedItem(int i) {
        if (i >= getCount() || i < 0) {
            return null;
        }
        return new CheckableAdapter.CheckedItem.Builder().setId(getItemKey(i)).setSha1(getSha1(i)).setMicroThumbnailFile(getMicroThumbFilePath(i)).setThumbnailFile(getThumbFilePath(i)).setOriginFile(getOriginFilePath(i)).setMimeType(getMimeType(i)).build();
    }

    public RectF getItemDecodeRectF(int i) {
        return null;
    }

    public abstract long getItemKey(int i);

    public byte[] getItemSecretKey(int i) {
        return null;
    }

    public abstract String getMimeType(int i);

    public abstract String getSha1(int i);

    /* access modifiers changed from: protected */
    public void onViewScrollStateChanged(AbsListView absListView, int i) {
        this.mViewScrollState = i;
    }

    /* access modifiers changed from: protected */
    public void onViewScrolled(AbsListView absListView, int i, int i2, int i3) {
    }
}
