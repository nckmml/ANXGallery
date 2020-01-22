package com.miui.gallery.movie.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.movie.R;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseHolder> implements View.OnClickListener {
    protected Context mContext;
    private ItemSelectChangeListener mItemSelectChangeListener;
    protected int mLastSelectedItemPosition = -1;
    protected List<T> mList;
    protected int mSelectedItemPosition = 0;

    public static abstract class BaseHolder extends RecyclerView.ViewHolder {
        protected View mSelected;

        public BaseHolder(View view) {
            super(view);
            view.setTag(R.id.item_root, this);
            this.mSelected = view.findViewById(R.id.iv_selected);
        }

        public abstract void bindView(int i);

        public void bindView(int i, Object obj) {
        }
    }

    public interface ItemSelectChangeListener {
        boolean onItemSelect(RecyclerView recyclerView, BaseHolder baseHolder, int i, boolean z);
    }

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    private void setSelected(BaseHolder baseHolder, boolean z) {
        baseHolder.itemView.setSelected(z);
        baseHolder.mSelected.setVisibility(z ? 0 : 4);
    }

    /* access modifiers changed from: protected */
    public abstract BaseHolder getHolder(View view);

    public int getItemCount() {
        List<T> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public T getItemData(int i) {
        List<T> list = this.mList;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.mList.get(i);
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    public List<T> getList() {
        return this.mList;
    }

    public int getSelectedItemPosition() {
        return this.mSelectedItemPosition;
    }

    public void onBindViewHolder(BaseHolder baseHolder, int i) {
        baseHolder.itemView.setOnClickListener(this);
        setSelected(baseHolder, i == this.mSelectedItemPosition);
        baseHolder.bindView(i);
    }

    public void onBindViewHolder(BaseHolder baseHolder, int i, List<Object> list) {
        if (list == null || list.size() <= 0) {
            super.onBindViewHolder(baseHolder, i, list);
            return;
        }
        setSelected(baseHolder, i == this.mSelectedItemPosition);
        baseHolder.bindView(i, list);
    }

    public void onClick(View view) {
        BaseHolder baseHolder = (BaseHolder) view.getTag(R.id.item_root);
        int adapterPosition = baseHolder.getAdapterPosition();
        if (this.mSelectedItemPosition != adapterPosition) {
            ItemSelectChangeListener itemSelectChangeListener = this.mItemSelectChangeListener;
            if (itemSelectChangeListener != null && itemSelectChangeListener.onItemSelect((RecyclerView) view.getParent(), baseHolder, adapterPosition, true)) {
                setSelectedItemPosition(adapterPosition);
                return;
            }
            return;
        }
        ItemSelectChangeListener itemSelectChangeListener2 = this.mItemSelectChangeListener;
        if (itemSelectChangeListener2 != null) {
            itemSelectChangeListener2.onItemSelect((RecyclerView) view.getParent(), baseHolder, adapterPosition, false);
        }
    }

    public BaseHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return getHolder(LayoutInflater.from(this.mContext).inflate(getLayoutId(), viewGroup, false));
    }

    public void setItemSelectChangeListener(ItemSelectChangeListener itemSelectChangeListener) {
        this.mItemSelectChangeListener = itemSelectChangeListener;
    }

    public void setList(List<T> list) {
        this.mList = list;
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

    public void setSelectedItemPositionWithoutNotify(int i) {
        this.mLastSelectedItemPosition = this.mSelectedItemPosition;
        this.mSelectedItemPosition = i;
    }
}
