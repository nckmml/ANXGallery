package com.miui.gallery.editor.photo.app.frame;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.menu.FrameView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractFrameFragment;
import com.miui.gallery.editor.photo.core.common.model.FrameData;
import com.miui.gallery.editor.photo.widgets.seekbar.BasicSeekBar;
import com.miui.gallery.ui.StartEndSmoothScrollerController;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FrameMenuFragment extends MenuFragment<AbstractFrameFragment, SdkProvider<FrameData, AbstractFrameFragment>> {
    /* access modifiers changed from: private */
    public FrameAdapter mFrameAdapter;
    private List<FrameData> mFrameDataList;
    private SparseIntArray mItemStatusArray = new SparseIntArray();
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            FrameMenuFragment.this.mFrameAdapter.setSelection(i);
            FrameMenuFragment.this.onSelect(i);
            return true;
        }
    };
    private SimpleRecyclerView mRecyclerView;
    private BasicSeekBar mSeekBar;
    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            ((AbstractFrameFragment) FrameMenuFragment.this.getRenderFragment()).setScaleProgress(((float) i) / ((float) seekBar.getMax()));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            FrameMenuFragment frameMenuFragment = FrameMenuFragment.this;
            frameMenuFragment.putStatus(frameMenuFragment.mFrameAdapter.getSelection(), seekBar.getProgress());
        }
    };

    public FrameMenuFragment() {
        super(Effect.FRAME);
    }

    /* access modifiers changed from: private */
    public void onSelect(int i) {
        FrameData frameData = this.mFrameDataList.get(i);
        ((AbstractFrameFragment) getRenderFragment()).setRatio(((float) frameData.width) / ((float) frameData.height), frameData.toString());
        BasicSeekBar basicSeekBar = this.mSeekBar;
        basicSeekBar.setProgress(getStatus(i, basicSeekBar.getMax()));
    }

    public int getStatus(int i, int i2) {
        return this.mItemStatusArray.get(i, i2);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new FrameView(viewGroup.getContext());
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((TextView) view.findViewById(R.id.title)).setText(R.string.photo_editor_frame);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mSeekBar = (BasicSeekBar) view.findViewById(R.id.seek_bar);
        this.mSeekBar.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
        this.mFrameDataList = new ArrayList(this.mSdkProvider.list());
        ScrollLinearLayoutManager scrollLinearLayoutManager = new ScrollLinearLayoutManager(getActivity());
        scrollLinearLayoutManager.setSmoothScroller(new StartEndSmoothScrollerController(getActivity()));
        scrollLinearLayoutManager.setOrientation(0);
        this.mRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        this.mFrameAdapter = new FrameAdapter(this.mFrameDataList, getActivity());
        this.mFrameAdapter.setOnItemClickListener(this.mOnItemClickListener);
        this.mRecyclerView.setAdapter(this.mFrameAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        onSelect(0);
    }

    public void putStatus(int i, int i2) {
        this.mItemStatusArray.put(i, i2);
    }
}
