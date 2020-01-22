package com.miui.gallery.editor.photo.app.miuibeautify;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.drawable.SelectableDrawable;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;
import miui.view.animation.SineEaseInOutInterpolator;

public class SmartBeautyLevelItemAdapter extends SimpleRecyclerView.Adapter<BeautyLevelItemHolder> implements Selectable {
    private List<LevelItem> mBeautyItemList;
    private Context mContext;
    private Selectable.Delegator mDelegator = new Selectable.Delegator(0);

    class BeautyLevelItemHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        BeautyLevelItemHolder(View view) {
            super(view);
            this.mImageView = (ImageView) view.findViewById(R.id.img);
        }

        public void bind(int i, int i2) {
            SelectableDrawable selectableDrawable = new SelectableDrawable(this.mImageView.getResources().getDrawable(i), this.mImageView.getResources().getDrawable(i2));
            selectableDrawable.setInterpolator(new SineEaseInOutInterpolator());
            selectableDrawable.setDuration((long) this.mImageView.getResources().getInteger(R.integer.selectable_drawable_fade_duration));
            this.mImageView.setImageDrawable(selectableDrawable);
        }
    }

    public static class LevelItem {
        private int mResourceNormal;
        private int mResourcePressed;

        public LevelItem(int i, int i2) {
            this.mResourceNormal = i;
            this.mResourcePressed = i2;
        }

        public int getNormalResource() {
            return this.mResourceNormal;
        }

        public int getPressedResource() {
            return this.mResourcePressed;
        }
    }

    public SmartBeautyLevelItemAdapter(Context context, List<LevelItem> list) {
        this.mContext = context;
        this.mBeautyItemList = list;
    }

    public int getItemCount() {
        List<LevelItem> list = this.mBeautyItemList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(BeautyLevelItemHolder beautyLevelItemHolder, int i) {
        super.onBindViewHolder(beautyLevelItemHolder, i);
        beautyLevelItemHolder.bind(this.mBeautyItemList.get(i).getNormalResource(), this.mBeautyItemList.get(i).getPressedResource());
        this.mDelegator.onBindViewHolder(beautyLevelItemHolder, i);
    }

    public BeautyLevelItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BeautyLevelItemHolder(getInflater().inflate(R.layout.beauty_level_item, viewGroup, false));
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        this.mDelegator.setSelection(i);
    }
}
