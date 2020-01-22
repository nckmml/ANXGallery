package com.miui.gallery.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import java.util.ArrayList;
import java.util.List;

public class VideoFrameThumbAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Bitmap> mDataList;
    private int mDividerWidth;
    private int mEmptyViewWidth;
    private boolean mIsRtl;
    /* access modifiers changed from: private */
    public int mItemWidth;
    private int mScreenWidth;
    private int mTotalWidth;

    private class EmptyHolder extends RecyclerView.ViewHolder {
        public EmptyHolder(View view) {
            super(view);
        }
    }

    private class FrameThumbHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView = ((ImageView) this.itemView);

        public FrameThumbHolder(View view) {
            super(new ImageView(view.getContext()));
            this.mImageView.setLayoutParams(new RecyclerView.LayoutParams(VideoFrameThumbAdapter.this.mItemWidth, -1));
            this.mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        public void setBitmap(Bitmap bitmap) {
            this.mImageView.setImageBitmap(bitmap);
        }
    }

    public VideoFrameThumbAdapter(Context context) {
        this.mContext = context;
        this.mDividerWidth = context.getResources().getDimensionPixelSize(R.dimen.video_frame_seek_bar_divider);
        this.mItemWidth = context.getResources().getDimensionPixelSize(R.dimen.video_frame_thumb_width);
        int i = this.mDividerWidth;
        boolean z = true;
        if (i % 2 != 0) {
            this.mDividerWidth = i + 1;
        }
        this.mIsRtl = context.getResources().getConfiguration().getLayoutDirection() != 1 ? false : z;
    }

    private int getCurrentOffset(RecyclerView recyclerView) {
        if (this.mScreenWidth == 0 || getDataListSize() <= 0 || recyclerView.getChildCount() == 0) {
            return 0;
        }
        View childAt = recyclerView.getChildAt(0);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
        if (layoutParams == null) {
            return 0;
        }
        int viewAdapterPosition = layoutParams.getViewAdapterPosition();
        int right = this.mIsRtl ? childAt.getRight() - this.mScreenWidth : -childAt.getLeft();
        if (viewAdapterPosition > 0) {
            right += this.mEmptyViewWidth + ((viewAdapterPosition - 1) * this.mItemWidth);
        }
        return right + (this.mScreenWidth / 2);
    }

    private Bitmap getDataItem(int i) {
        List<Bitmap> list = this.mDataList;
        if (list == null) {
            return null;
        }
        return list.get(i - 1);
    }

    public void configLayoutParams(int i) {
        if (i != this.mScreenWidth) {
            this.mScreenWidth = i;
            this.mEmptyViewWidth = (this.mScreenWidth - this.mDividerWidth) / 2;
            notifyDataSetChanged();
        }
    }

    public int getDataListSize() {
        List<Bitmap> list = this.mDataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getItemCount() {
        return getDataListSize() + 2;
    }

    public int getItemViewType(int i) {
        return (i == 0 || i == getDataListSize() + 1) ? 1 : 0;
    }

    public int getScrollOffset(RecyclerView recyclerView, float f) {
        int currentOffset = getCurrentOffset(recyclerView);
        if (currentOffset <= 0) {
            return 0;
        }
        int i = this.mTotalWidth;
        int i2 = this.mDividerWidth;
        return ((int) (((((float) (i - i2)) * f) + ((float) (i2 / 2))) + ((float) this.mEmptyViewWidth))) - currentOffset;
    }

    public float getScrollPercent(RecyclerView recyclerView) {
        int currentOffset = getCurrentOffset(recyclerView);
        if (currentOffset <= 0) {
            return 0.0f;
        }
        int i = currentOffset - this.mEmptyViewWidth;
        int i2 = this.mDividerWidth;
        int i3 = i - (i2 / 2);
        int i4 = this.mTotalWidth;
        return Math.min(Math.max(i4 > i2 ? (((float) i3) * 1.0f) / ((float) (i4 - i2)) : 0.0f, 0.0f), 1.0f);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            ((FrameThumbHolder) viewHolder).setBitmap(getDataItem(i));
        } else if (itemViewType == 1) {
            viewHolder.itemView.getLayoutParams().width = this.mEmptyViewWidth;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 1) {
            return new FrameThumbHolder(viewGroup);
        }
        View view = new View(this.mContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(this.mEmptyViewWidth, -1));
        return new EmptyHolder(view);
    }

    public void updateDataList(List<Bitmap> list) {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList();
        }
        this.mDataList.clear();
        this.mTotalWidth = 0;
        if (list != null) {
            this.mDataList.addAll(list);
            this.mTotalWidth = this.mItemWidth * list.size();
        }
        notifyDataSetChanged();
    }
}
