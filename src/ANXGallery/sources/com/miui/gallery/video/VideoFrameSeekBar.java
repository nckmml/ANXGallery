package com.miui.gallery.video;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import java.util.List;

public class VideoFrameSeekBar extends FrameLayout {
    private Context mContext;
    private boolean mIsRtl;
    /* access modifiers changed from: private */
    public OnSeekBarChangeListener mOnSeekBarChangeListener;
    /* access modifiers changed from: private */
    public float mProgress;
    /* access modifiers changed from: private */
    public VideoFrameThumbAdapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public boolean mScrollIdle = true;
    private AnimatorListenerAdapter mTransitionAnimListener = new AnimatorListenerAdapter() {
        public void onAnimationCancel(Animator animator) {
            VideoFrameSeekBar.this.hideTransitionView();
        }

        public void onAnimationEnd(Animator animator) {
            VideoFrameSeekBar.this.hideTransitionView();
        }
    };
    private View mTransitionView;

    public interface OnSeekBarChangeListener {
        void onProgressChanged(float f);

        void onScrollStateChanged(boolean z);
    }

    public VideoFrameSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoFrameSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void configTransitionView() {
        VideoFrameThumbAdapter videoFrameThumbAdapter = this.mRecyclerAdapter;
        if (videoFrameThumbAdapter != null && videoFrameThumbAdapter.getDataListSize() != 0) {
            this.mRecyclerView.destroyDrawingCache();
            Bitmap drawingCache = this.mRecyclerView.getDrawingCache();
            if (drawingCache != null) {
                Bitmap createBitmap = Bitmap.createBitmap(drawingCache);
                this.mRecyclerView.destroyDrawingCache();
                this.mTransitionView.setBackground(new BitmapDrawable(getResources(), createBitmap));
                this.mTransitionView.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideTransitionView() {
        View view = this.mTransitionView;
        if (view != null) {
            view.setVisibility(4);
            this.mTransitionView.setBackground((Drawable) null);
        }
    }

    public float getProgress() {
        return this.mProgress;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mContext = getContext();
        this.mIsRtl = getResources().getConfiguration().getLayoutDirection() == 1;
        this.mTransitionView = findViewById(R.id.transition_view);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.video_frame_thumb_list);
        this.mRecyclerView.setDrawingCacheEnabled(true);
        this.mRecyclerAdapter = new VideoFrameThumbAdapter(this.mContext);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.mRecyclerView.setAdapter(this.mRecyclerAdapter);
        this.mRecyclerView.setOverScrollMode(2);
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                boolean z = false;
                if (VideoFrameSeekBar.this.mScrollIdle && i != 0) {
                    boolean unused = VideoFrameSeekBar.this.mScrollIdle = false;
                }
                if (VideoFrameSeekBar.this.mOnSeekBarChangeListener != null) {
                    OnSeekBarChangeListener access$300 = VideoFrameSeekBar.this.mOnSeekBarChangeListener;
                    if (i != 0) {
                        z = true;
                    }
                    access$300.onScrollStateChanged(z);
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                if (!VideoFrameSeekBar.this.mScrollIdle) {
                    VideoFrameSeekBar videoFrameSeekBar = VideoFrameSeekBar.this;
                    float unused = videoFrameSeekBar.mProgress = videoFrameSeekBar.mRecyclerAdapter.getScrollPercent(recyclerView);
                    if (VideoFrameSeekBar.this.mOnSeekBarChangeListener != null) {
                        VideoFrameSeekBar.this.mOnSeekBarChangeListener.onProgressChanged(VideoFrameSeekBar.this.mProgress);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            VideoFrameThumbAdapter videoFrameThumbAdapter = this.mRecyclerAdapter;
            if (videoFrameThumbAdapter != null) {
                videoFrameThumbAdapter.configLayoutParams(i3 - i);
            }
            setProgress(this.mProgress);
        }
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public void setProgress(float f) {
        VideoFrameThumbAdapter videoFrameThumbAdapter = this.mRecyclerAdapter;
        if (videoFrameThumbAdapter != null) {
            int scrollOffset = videoFrameThumbAdapter.getScrollOffset(this.mRecyclerView, f);
            if (scrollOffset != 0) {
                RecyclerView recyclerView = this.mRecyclerView;
                if (this.mIsRtl) {
                    scrollOffset = -scrollOffset;
                }
                recyclerView.scrollBy(scrollOffset, 0);
            } else if (this.mIsRtl) {
                this.mRecyclerView.scrollToPosition(0);
            }
            this.mProgress = f;
        }
    }

    public void updateFrameList(List<Bitmap> list, boolean z, boolean z2) {
        if (this.mRecyclerAdapter != null) {
            Log.d("VideoFrameSeekBar", "scrollToPosition 0");
            boolean z3 = z & (this.mRecyclerAdapter.getDataListSize() != 0 && this.mRecyclerView.getAlpha() == 1.0f && this.mRecyclerView.getScrollState() == 0);
            if (z3) {
                configTransitionView();
            }
            if (z2) {
                this.mScrollIdle = true;
                this.mProgress = 0.0f;
                this.mRecyclerView.scrollToPosition(0);
            }
            this.mRecyclerAdapter.updateDataList(list);
            if (z3) {
                this.mRecyclerView.setAlpha(0.0f);
                this.mRecyclerView.animate().alpha(1.0f).setDuration(200).setListener(this.mTransitionAnimListener).start();
            }
        }
    }
}
