package com.miui.gallery.editor.photo.screen.doodle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.DoodleData;
import com.miui.gallery.editor.photo.drawable.SelectableDrawable;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;
import miui.view.animation.SineEaseInOutInterpolator;

public class ScreenDoodleAdapter extends SimpleRecyclerView.Adapter<ScreenDoodleHolder> implements Selectable {
    private List<DoodleData> mDataList;
    private Selectable.Delegator mDelegator;

    static final class ScreenDoodleHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;

        public ScreenDoodleHolder(View view) {
            super(view);
            this.mImageView = (ImageView) view.findViewById(R.id.img);
        }

        /* access modifiers changed from: package-private */
        public void bind(boolean z, DoodleData doodleData) {
            SelectableDrawable selectableDrawable = new SelectableDrawable(this.mImageView.getResources().getDrawable(doodleData.normal), this.mImageView.getResources().getDrawable(doodleData.selected));
            selectableDrawable.setInterpolator(new SineEaseInOutInterpolator());
            selectableDrawable.setDuration((long) this.mImageView.getResources().getInteger(R.integer.selectable_drawable_fade_duration));
            this.mImageView.setImageDrawable(selectableDrawable);
            ImageView imageView = this.mImageView;
            imageView.setContentDescription(imageView.getResources().getString(doodleData.talkback));
            this.mImageView.setSelected(z);
        }
    }

    public ScreenDoodleAdapter(List<DoodleData> list, int i) {
        this.mDataList = list;
        this.mDelegator = new Selectable.Delegator(i, (Selectable.Selector) null);
    }

    public int getItemCount() {
        return this.mDataList.size();
    }

    public int getSelection() {
        return this.mDelegator.getSelection();
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mDelegator.onAttachedToRecyclerView(recyclerView);
    }

    public void onBindViewHolder(ScreenDoodleHolder screenDoodleHolder, int i) {
        super.onBindViewHolder(screenDoodleHolder, i);
        screenDoodleHolder.bind(this.mDelegator.getSelection() == i, this.mDataList.get(i));
        this.mDelegator.onBindViewHolder(screenDoodleHolder, i);
    }

    public ScreenDoodleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ScreenDoodleHolder(getInflater().inflate(R.layout.screen_doodle_menu_item, viewGroup, false));
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mDelegator.onDetachedFromRecyclerView(recyclerView);
    }

    public void setSelection(int i) {
        int selection = this.mDelegator.getSelection();
        if (selection != i) {
            this.mDelegator.setSelection(i);
            notifyItemChanged(selection);
            notifyItemChanged(i);
        }
    }
}
