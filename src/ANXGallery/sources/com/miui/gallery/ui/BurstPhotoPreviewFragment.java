package com.miui.gallery.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.Config;
import com.miui.gallery.R;
import com.miui.gallery.adapter.HeaderFooterRecyclerAdapterWrapper;
import com.miui.gallery.app.Fragment;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.model.BaseDataSet;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.util.BindImageHelper;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.util.uil.CloudUriAdapter;
import com.miui.gallery.widget.PagerIndicator;
import com.miui.gallery.widget.recyclerview.BaseViewHolder;
import com.miui.gallery.widget.recyclerview.GalleryRecyclerView;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import java.util.ArrayList;
import java.util.List;
import miui.view.animation.CubicEaseOutInterpolator;

public class BurstPhotoPreviewFragment extends Fragment {
    private PreviewViewAdapter mAdapter;
    /* access modifiers changed from: private */
    public PreviewViewHeaderFooterWrapper mAdapterWrapper;
    private BaseDataSet mContentDataSet;
    private View mDiscard;
    /* access modifiers changed from: private */
    public View mIndicator;
    /* access modifiers changed from: private */
    public boolean mIsScrolledByOutside = true;
    private View.OnClickListener mOnDiscardClickedListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (BurstPhotoPreviewFragment.this.mOnExitListener != null) {
                BurstPhotoPreviewFragment.this.mOnExitListener.onDiscard();
            }
        }
    };
    /* access modifiers changed from: private */
    public OnExitListener mOnExitListener;
    /* access modifiers changed from: private */
    public OnScrollToPositionListener mOnItemScrolledListener;
    private View.OnClickListener mOnSaveClickedListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (BurstPhotoPreviewFragment.this.mOnExitListener != null) {
                BurstPhotoPreviewFragment.this.mOnExitListener.onSave();
            }
        }
    };
    /* access modifiers changed from: private */
    public GalleryRecyclerView mPreviewRecyclerView;
    private View mSave;

    private class BurstLinearLayoutManager extends StartEndSmoothScrollerController {
        public BurstLinearLayoutManager(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            if (getLayoutManager() != null) {
                int screenWidth = (ScreenUtils.getScreenWidth() / 2) - ((view.getLeft() + view.getRight()) / 2);
                if (((float) Math.abs(screenWidth)) > this.mMinDistance) {
                    action.update(-screenWidth, 0, 300, new CubicEaseOutInterpolator());
                }
            }
        }
    }

    private class BurstOnScrollListener extends RecyclerView.OnScrollListener {
        private BurstOnScrollListener() {
        }

        private int getCurrentPosition(RecyclerView recyclerView) {
            int left = (BurstPhotoPreviewFragment.this.mIndicator.getLeft() + BurstPhotoPreviewFragment.this.mIndicator.getRight()) / 2;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int itemCount = layoutManager.getItemCount();
            int i = Reader.READ_DONE;
            int i2 = -1;
            for (int i3 = 0; i3 < itemCount; i3++) {
                View findViewByPosition = layoutManager.findViewByPosition(i3);
                if (findViewByPosition != null) {
                    int abs = Math.abs(((findViewByPosition.getLeft() + findViewByPosition.getRight()) / 2) - left);
                    if (abs >= i) {
                        return i2;
                    }
                    i2 = i3;
                    i = abs;
                }
            }
            return -1;
        }

        private boolean isScrollPositionValid(int i) {
            return i >= BurstPhotoPreviewFragment.this.mAdapterWrapper.getHeadersCount() && i <= BurstPhotoPreviewFragment.this.mAdapterWrapper.getItemCount() - BurstPhotoPreviewFragment.this.mAdapterWrapper.getFootersCount();
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            if (i == 1) {
                boolean unused = BurstPhotoPreviewFragment.this.mIsScrolledByOutside = false;
            } else if (i == 0) {
                int currentPosition = getCurrentPosition(recyclerView);
                if (isScrollPositionValid(currentPosition)) {
                    BurstPhotoPreviewFragment.this.mPreviewRecyclerView.smoothScrollToPosition(currentPosition);
                    if (BurstPhotoPreviewFragment.this.mOnItemScrolledListener != null) {
                        BurstPhotoPreviewFragment.this.mOnItemScrolledListener.onScrollToPosition(BurstPhotoPreviewFragment.this.mAdapterWrapper.getWrappedAdapterPosition(currentPosition));
                    }
                }
                boolean unused2 = BurstPhotoPreviewFragment.this.mIsScrolledByOutside = true;
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            int currentPosition = getCurrentPosition(recyclerView);
            if (BurstPhotoPreviewFragment.this.mOnItemScrolledListener != null && !BurstPhotoPreviewFragment.this.mIsScrolledByOutside && isScrollPositionValid(currentPosition)) {
                BurstPhotoPreviewFragment.this.mOnItemScrolledListener.onScrollToPosition(BurstPhotoPreviewFragment.this.mAdapterWrapper.getWrappedAdapterPosition(currentPosition));
            }
        }
    }

    class BurstScrollControlLinearLayoutManager extends ScrollLinearLayoutManager {
        public BurstScrollControlLinearLayoutManager(Context context) {
            super(context);
        }

        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
            BurstLinearLayoutManager burstLinearLayoutManager = new BurstLinearLayoutManager(recyclerView.getContext());
            burstLinearLayoutManager.setTargetPosition(i);
            startSmoothScroll(burstLinearLayoutManager);
        }
    }

    public interface OnExitListener {
        void onDiscard();

        void onSave();
    }

    public interface OnScrollToPositionListener {
        void onScrollToPosition(int i);
    }

    class PreviewViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
        private Context mContext;
        private BaseDataSet mDataSet;
        DisplayImageOptions mDisplayOptions = new DisplayImageOptions.Builder().cloneFrom(Config.ThumbConfig.get().MICRO_THUMB_DISPLAY_IMAGE_OPTIONS_DEFAULT).cacheThumbnail(false).loadFromMicroCache(false).build();
        private int mItemHeight;
        private int mItemWidth;
        private List<Integer> mSelectDataPos = new ArrayList();

        PreviewViewAdapter(Context context) {
            this.mContext = context;
            this.mItemWidth = BurstPhotoPreviewFragment.this.getActivity().getResources().getDimensionPixelSize(R.dimen.burst_preview_width);
            this.mItemHeight = BurstPhotoPreviewFragment.this.getActivity().getResources().getDimensionPixelSize(R.dimen.burst_preview_height);
        }

        public int getItemCount() {
            BaseDataSet baseDataSet = this.mDataSet;
            if (baseDataSet != null) {
                return baseDataSet.getCount();
            }
            return 0;
        }

        public int getSelectCount() {
            return this.mSelectDataPos.size();
        }

        public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
            baseViewHolder.itemView.setTag(Integer.valueOf(i));
            BaseDataSet baseDataSet = this.mDataSet;
            if (baseDataSet != null) {
                Uri uri = null;
                BaseDataItem item = baseDataSet.getItem((BaseDataItem) null, i);
                long key = item.getKey();
                String microPath = item.getOriginalPath() == null ? item.getThumnailPath() == null ? item.getMicroPath() : item.getThumnailPath() : item.getOriginalPath();
                if (item.isSynced()) {
                    uri = CloudUriAdapter.getDownloadUri(key);
                }
                Uri uri2 = uri;
                if (baseViewHolder instanceof PreviewViewHolder) {
                    ((PreviewViewHolder) baseViewHolder).bindImage(this.mSelectDataPos.contains(Integer.valueOf(i)), microPath, uri2, this.mItemWidth, this.mItemHeight, this.mDisplayOptions);
                }
            }
        }

        public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new PreviewViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.burst_preview_item, viewGroup, false));
        }

        public void setDateSet(BaseDataSet baseDataSet) {
            this.mDataSet = baseDataSet;
            notifyDataSetChanged();
        }

        public void setSelectDataPosition(int i, boolean z) {
            if (z) {
                this.mSelectDataPos.add(Integer.valueOf(i));
            } else if (this.mSelectDataPos.contains(Integer.valueOf(i))) {
                this.mSelectDataPos.remove(Integer.valueOf(i));
            }
            BaseViewHolder baseViewHolder = (BaseViewHolder) BurstPhotoPreviewFragment.this.mPreviewRecyclerView.findViewHolderForAdapterPosition(i + BurstPhotoPreviewFragment.this.mAdapterWrapper.getHeadersCount());
            if (baseViewHolder != null && (baseViewHolder instanceof PreviewViewHolder)) {
                ((PreviewViewHolder) baseViewHolder).setSelect(z);
            }
        }
    }

    class PreviewViewHeaderFooterWrapper extends HeaderFooterRecyclerAdapterWrapper<PreviewViewAdapter, BaseViewHolder> {
        public PreviewViewHeaderFooterWrapper(PreviewViewAdapter previewViewAdapter) {
            super(previewViewAdapter);
        }

        /* access modifiers changed from: protected */
        public BaseViewHolder onCreateHeaderFooterViewHolder(View view) {
            return new BaseViewHolder(view);
        }
    }

    class PreviewViewHolder extends BaseViewHolder {
        private ImageView mPreview;
        private View mSelectView;

        public PreviewViewHolder(View view) {
            super(view);
            this.mPreview = (ImageView) view.findViewById(R.id.preview);
            this.mSelectView = view.findViewById(R.id.select_view);
        }

        public void bindImage(boolean z, String str, Uri uri, int i, int i2, DisplayImageOptions displayImageOptions) {
            this.mSelectView.setVisibility(z ? 0 : 8);
            this.mPreview.setLayoutParams(new FrameLayout.LayoutParams(i, i2));
            BindImageHelper.bindImage(str, uri, DownloadType.MICRO, this.mPreview, displayImageOptions, new ImageSize(i, i2));
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BurstPhotoPreviewFragment.this.scrollToPosition(((Integer) PreviewViewHolder.this.itemView.getTag()).intValue());
                }
            });
        }

        public void setSelect(boolean z) {
            this.mSelectView.setVisibility(z ? 0 : 8);
        }
    }

    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.burst_photo_preview, viewGroup, false);
        this.mSave = inflate.findViewById(R.id.save);
        this.mSave.setOnClickListener(this.mOnSaveClickedListener);
        this.mDiscard = inflate.findViewById(R.id.discard);
        this.mDiscard.setOnClickListener(this.mOnDiscardClickedListener);
        this.mIndicator = inflate.findViewById(R.id.indicator);
        ((PagerIndicator) this.mIndicator).showIndex(0, 1);
        this.mPreviewRecyclerView = (GalleryRecyclerView) inflate.findViewById(R.id.preview_view);
        BurstScrollControlLinearLayoutManager burstScrollControlLinearLayoutManager = new BurstScrollControlLinearLayoutManager(getActivity());
        burstScrollControlLinearLayoutManager.setOrientation(0);
        this.mPreviewRecyclerView.setLayoutManager(burstScrollControlLinearLayoutManager);
        this.mPreviewRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(getResources(), R.dimen.burst_preview_decoration_width, 0));
        this.mAdapter = new PreviewViewAdapter(getActivity());
        BaseDataSet baseDataSet = this.mContentDataSet;
        if (baseDataSet != null) {
            this.mAdapter.setDateSet(baseDataSet);
        }
        int screenWidth = ((ScreenUtils.getScreenWidth() / 2) - (getActivity().getResources().getDimensionPixelSize(R.dimen.burst_preview_width) / 2)) - (getActivity().getResources().getDimensionPixelSize(R.dimen.burst_preview_decoration_width) * 2);
        View view = new View(getActivity());
        view.setLayoutParams(new ViewGroup.LayoutParams(screenWidth, 1));
        View view2 = new View(getActivity());
        view2.setLayoutParams(new ViewGroup.LayoutParams(screenWidth, 1));
        this.mAdapterWrapper = new PreviewViewHeaderFooterWrapper(this.mAdapter);
        this.mAdapterWrapper.addFooterView(view2);
        this.mAdapterWrapper.addHeaderView(view);
        this.mPreviewRecyclerView.setAdapter(this.mAdapterWrapper);
        this.mPreviewRecyclerView.addOnScrollListener(new BurstOnScrollListener());
        return inflate;
    }

    public void scrollToPosition(int i) {
        if (this.mIsScrolledByOutside) {
            this.mPreviewRecyclerView.smoothScrollToPosition(i + this.mAdapterWrapper.getHeadersCount());
        }
    }

    public void setCheckedItem(int i, boolean z) {
        this.mAdapter.setSelectDataPosition(i, z);
        this.mSave.setEnabled(this.mAdapter.getSelectCount() > 0);
    }

    public void setDataSet(BaseDataSet baseDataSet) {
        this.mContentDataSet = baseDataSet;
        PreviewViewAdapter previewViewAdapter = this.mAdapter;
        if (previewViewAdapter != null) {
            previewViewAdapter.setDateSet(baseDataSet);
        }
    }

    public void setOnExitListener(OnExitListener onExitListener) {
        this.mOnExitListener = onExitListener;
    }

    public void setOnItemScrolledListener(OnScrollToPositionListener onScrollToPositionListener) {
        this.mOnItemScrolledListener = onScrollToPositionListener;
    }
}
