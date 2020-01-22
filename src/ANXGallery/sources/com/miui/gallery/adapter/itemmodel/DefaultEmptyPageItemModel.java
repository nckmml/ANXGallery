package com.miui.gallery.adapter.itemmodel;

import android.view.View;
import android.view.ViewGroup;
import com.miui.epoxy.EpoxyAdapter;
import com.miui.epoxy.EpoxyViewHolder;
import com.miui.gallery.R;
import com.miui.gallery.adapter.itemmodel.base.BaseItemModel;
import com.miui.gallery.widget.EmptyPage;
import java.util.List;

public class DefaultEmptyPageItemModel extends BaseItemModel<Void, EpoxyViewHolder> {
    protected EmptyPage.EmptyConfig mConfig;
    protected View mEmptyView = null;
    protected int mLayoutId = 0;

    public DefaultEmptyPageItemModel(EmptyPage.EmptyConfig emptyConfig) {
        this.mConfig = emptyConfig;
    }

    public void bindData(EpoxyViewHolder epoxyViewHolder, List<Object> list) {
        super.bindData(epoxyViewHolder, list);
        if (this.mConfig != null && (epoxyViewHolder.itemView instanceof EmptyPage)) {
            ((EmptyPage) epoxyViewHolder.itemView).apply(this.mConfig);
        }
    }

    public int getLayoutRes() {
        int i = this.mLayoutId;
        return i == 0 ? R.layout.empty_page_default : i;
    }

    public int getSpanSize(int i, int i2, int i3) {
        return i;
    }

    public EpoxyAdapter.IViewHolderCreator<EpoxyViewHolder> getViewHolderCreator() {
        return new EpoxyAdapter.IViewHolderCreator<EpoxyViewHolder>() {
            public EpoxyViewHolder create(View view, View view2) {
                if (DefaultEmptyPageItemModel.this.mEmptyView == null || !(view instanceof ViewGroup)) {
                    DefaultEmptyPageItemModel.this.mEmptyView = view;
                    return new EpoxyViewHolder(view);
                }
                if (DefaultEmptyPageItemModel.this.mEmptyView.getParent() == view) {
                    ((ViewGroup) view).removeView(DefaultEmptyPageItemModel.this.mEmptyView);
                }
                return new EpoxyViewHolder(DefaultEmptyPageItemModel.this.mEmptyView);
            }
        };
    }
}
