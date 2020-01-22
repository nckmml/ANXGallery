package com.miui.gallery.editor.photo.app.filter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.FilterCategory;
import com.miui.gallery.editor.photo.widgets.PercentCircleView;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

class FilterHeadAdapter extends SimpleRecyclerView.Adapter<HeaderHolder> {
    private List<FilterCategory> mCategories;
    protected int mLastSelectedItemPosition = -1;
    protected int mSelectedItemPosition = 0;

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        private ImageView mIVState;
        private PercentCircleView mPercentCircleView;
        public TextView mTitle;

        public HeaderHolder(View view) {
            super(view);
            this.mTitle = (TextView) view.findViewById(R.id.tv);
            this.mIVState = (ImageView) view.findViewById(R.id.iv_state);
            this.mPercentCircleView = (PercentCircleView) view.findViewById(R.id.percent_circle_view);
        }

        /* access modifiers changed from: package-private */
        public void bind(FilterCategory filterCategory) {
            this.mTitle.setText(filterCategory.name);
            int downloadState = filterCategory.getDownloadState();
            if (downloadState == 0) {
                this.mIVState.setVisibility(8);
                this.mPercentCircleView.setVisibility(8);
            } else if (downloadState == 1) {
                this.mIVState.setVisibility(0);
                this.mIVState.setImageResource(R.drawable.editor_ic_download);
                this.mPercentCircleView.setVisibility(8);
            } else if (downloadState == 3) {
                this.mIVState.setVisibility(0);
                this.mIVState.setImageResource(R.drawable.editor_ic_downloaded);
                this.mPercentCircleView.setVisibility(8);
            } else if (downloadState == 2) {
                this.mIVState.setVisibility(8);
                this.mPercentCircleView.setVisibility(0);
                this.mPercentCircleView.setPercent(filterCategory.getDownloadPercent());
            }
        }
    }

    public FilterHeadAdapter(List<FilterCategory> list) {
        this.mCategories = list;
    }

    private void setSelected(HeaderHolder headerHolder, boolean z) {
        headerHolder.mTitle.setSelected(z);
    }

    public int getItemCount() {
        List<FilterCategory> list = this.mCategories;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void onBindViewHolder(HeaderHolder headerHolder, int i) {
        super.onBindViewHolder(headerHolder, i);
        headerHolder.bind(this.mCategories.get(i));
        setSelected(headerHolder, i == this.mSelectedItemPosition);
    }

    public HeaderHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HeaderHolder(getInflater().inflate(R.layout.filter_category_navigation_item, viewGroup, false));
    }

    public void setSelectedItemPosition(int i) {
        int i2 = this.mSelectedItemPosition;
        if (i != i2) {
            this.mLastSelectedItemPosition = i2;
            this.mSelectedItemPosition = i;
            notifyItemChanged(this.mLastSelectedItemPosition, true);
            notifyItemChanged(this.mSelectedItemPosition, true);
        }
    }
}
