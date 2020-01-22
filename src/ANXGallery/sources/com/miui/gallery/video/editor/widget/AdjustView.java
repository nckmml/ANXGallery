package com.miui.gallery.video.editor.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.editor.photo.widgets.seekbar.BasicSeekBar;
import com.miui.gallery.editor.photo.widgets.seekbar.BiDirectionSeekBar;
import com.miui.gallery.editor.photo.widgets.seekbar.BubbleIndicator;
import com.miui.gallery.video.editor.adapter.AdjustAdapter;
import com.miui.gallery.video.editor.manager.FilterAdjustManager;
import com.miui.gallery.video.editor.model.AdjustData;
import com.miui.gallery.video.editor.model.FilterAdjustData;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miui.gallery.support.linearmotor.LinearMotorHelper;

public class AdjustView extends LinearLayout {
    /* access modifiers changed from: private */
    public AdjustAdapter mAdapter;
    private HashMap<Integer, String> mAdjustCurrentEffects;
    /* access modifiers changed from: private */
    public List<AdjustData> mDataList;
    private IAdjustEffectChangeListener mIAdjustEffectChangeListener;
    private IFilterAdjustHeadViewListener mIFilterAdjustHeadViewListener;
    private BubbleIndicator<TextView> mIndicator;
    private BubbleIndicator.Callback<TextView> mIndicatorCallback = new BubbleIndicator.Callback<TextView>() {
        public void updateProgress(TextView textView, int i) {
            textView.setText(String.valueOf(i));
        }
    };
    private boolean mIsAdjustView;
    /* access modifiers changed from: private */
    public boolean mIsTracking;
    private SimpleRecyclerView mRecyclerView;
    private SeekBar mSeekBar;
    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        int value = 0;

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.value = i;
            boolean unused = AdjustView.this.mIsTracking = true;
            AdjustView adjustView = AdjustView.this;
            adjustView.setEffect(this.value, (AdjustData) adjustView.mDataList.get(AdjustView.this.mAdapter.getSelection()));
            if (i == 0) {
                LinearMotorHelper.performHapticFeedback(AdjustView.this.getContext(), LinearMotorHelper.EFFECT_KEY_MESH_NORMAL);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            boolean unused = AdjustView.this.mIsTracking = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            boolean unused = AdjustView.this.mIsTracking = false;
            AdjustView adjustView = AdjustView.this;
            adjustView.setEffect(this.value, (AdjustData) adjustView.mDataList.get(AdjustView.this.mAdapter.getSelection()));
        }
    };
    SimpleRecyclerView.OnItemClickListener onAdjustItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (AdjustView.this.mAdapter.getSelection() == i) {
                AdjustView.this.updateHeadBar();
                return true;
            }
            AdjustView.this.mAdapter.setSelection(i);
            AdjustData adjustData = (AdjustData) AdjustView.this.mDataList.get(i);
            if (adjustData != null && (adjustData instanceof FilterAdjustData)) {
                AdjustView.this.performItemSelect(adjustData);
            }
            return true;
        }
    };

    public interface IAdjustEffectChangeListener {
        void adjustBrightness(int i);

        void adjustContrast(int i);

        void adjustSaturation(int i);

        void adjustSharpness(int i);

        void adjustVignetteRange(int i);
    }

    public interface IFilterAdjustHeadViewListener {
        void addFilterViewToHeadBar(View view);

        void addSeekBarToHeadBar(View view);

        void removeAllViewFromHeadBar();
    }

    public AdjustView(Context context) {
        super(context);
        init(context);
    }

    private void addNewSeekBar(AdjustData adjustData) {
        if (!adjustData.isMid()) {
            this.mSeekBar = new BasicSeekBar(getContext());
            this.mSeekBar.setMax(adjustData.getMax());
            this.mSeekBar.setProgress(adjustData.progress);
        } else {
            BiDirectionSeekBar biDirectionSeekBar = new BiDirectionSeekBar(getContext());
            biDirectionSeekBar.setMaxValue(adjustData.getMax());
            biDirectionSeekBar.setCurrentValue(adjustData.progress);
            this.mSeekBar = biDirectionSeekBar;
        }
        IFilterAdjustHeadViewListener iFilterAdjustHeadViewListener = this.mIFilterAdjustHeadViewListener;
        if (iFilterAdjustHeadViewListener != null) {
            this.mIsAdjustView = true;
            iFilterAdjustHeadViewListener.addSeekBarToHeadBar(this.mSeekBar);
        }
        this.mSeekBar.setOnSeekBarChangeListener(this.mIndicator);
    }

    private void init(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.video_editor_adjustview, this);
        initData(context);
        initRecyclerView(context);
    }

    private void initData(Context context) {
        this.mDataList = new ArrayList();
        List<FilterAdjustData> adjustData = FilterAdjustManager.getAdjustData();
        if (adjustData != null && adjustData.size() > 0) {
            for (FilterAdjustData next : adjustData) {
                if (next != null) {
                    this.mDataList.add(next);
                }
            }
        }
        this.mIndicator = new BubbleIndicator<>((TextView) View.inflate(context, R.layout.seekbar_bubble_indicator_text, (ViewGroup) null), context.getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), this.mIndicatorCallback, this.mSeekBarChangeListener);
    }

    private void initRecyclerView(Context context) {
        this.mRecyclerView = (SimpleRecyclerView) findViewById(R.id.recycler_view);
        this.mAdapter = new AdjustAdapter(context, this.mDataList, new Selectable.Selector(getResources().getColor(R.color.photo_editor_highlight_color)));
        this.mRecyclerView.setAdapter(this.mAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mAdapter.setOnItemClickListener(this.onAdjustItemClickListener);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_item_start);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimensionPixelSize, dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.editor_menu_filter_item_horizontal_interval), 0, 0, 0));
    }

    /* access modifiers changed from: private */
    public void performItemSelect(AdjustData adjustData) {
        removePreviousSeekBar();
        addNewSeekBar(adjustData);
    }

    private void removePreviousSeekBar() {
        BubbleIndicator<TextView> bubbleIndicator = this.mIndicator;
        if (bubbleIndicator != null && bubbleIndicator.isShowing()) {
            this.mIndicator.dismiss();
        }
        IFilterAdjustHeadViewListener iFilterAdjustHeadViewListener = this.mIFilterAdjustHeadViewListener;
        if (iFilterAdjustHeadViewListener != null) {
            iFilterAdjustHeadViewListener.removeAllViewFromHeadBar();
        }
    }

    /* access modifiers changed from: private */
    public void setEffect(int i, AdjustData adjustData) {
        if (this.mAdapter.getSelection() != -1 && this.mIAdjustEffectChangeListener != null) {
            if (this.mAdjustCurrentEffects == null) {
                this.mAdjustCurrentEffects = new HashMap<>();
            }
            adjustData.setProgress(i);
            if (adjustData instanceof FilterAdjustData) {
                FilterAdjustData filterAdjustData = (FilterAdjustData) adjustData;
                int id = filterAdjustData.getId();
                if (!this.mAdjustCurrentEffects.containsKey(Integer.valueOf(id))) {
                    this.mAdjustCurrentEffects.put(Integer.valueOf(id), filterAdjustData.getLable());
                }
                if (id == 0) {
                    this.mIAdjustEffectChangeListener.adjustBrightness(i);
                } else if (id == 1) {
                    this.mIAdjustEffectChangeListener.adjustContrast(i);
                } else if (id == 2) {
                    this.mIAdjustEffectChangeListener.adjustSaturation(i);
                } else if (id == 3) {
                    this.mIAdjustEffectChangeListener.adjustSharpness(i);
                } else if (id == 4) {
                    this.mIAdjustEffectChangeListener.adjustVignetteRange(i);
                } else {
                    throw new IllegalArgumentException("not support adjust id: " + id);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateHeadBar() {
        IFilterAdjustHeadViewListener iFilterAdjustHeadViewListener = this.mIFilterAdjustHeadViewListener;
        if (iFilterAdjustHeadViewListener != null) {
            if (this.mIsAdjustView) {
                iFilterAdjustHeadViewListener.addFilterViewToHeadBar((View) null);
            } else {
                removePreviousSeekBar();
                this.mIFilterAdjustHeadViewListener.addSeekBarToHeadBar(this.mSeekBar);
            }
            this.mIsAdjustView = !this.mIsAdjustView;
        }
    }

    public void clearCurrentEffects() {
        HashMap<Integer, String> hashMap = this.mAdjustCurrentEffects;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public boolean doCancel() {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList();
        }
        this.mDataList.clear();
        List<FilterAdjustData> adjustData = FilterAdjustManager.getAdjustData();
        if (adjustData == null || adjustData.size() <= 0) {
            return true;
        }
        for (FilterAdjustData next : adjustData) {
            if (next != null) {
                this.mDataList.add(next);
                setEffect(next.getProgress(), next);
            }
        }
        return true;
    }

    public List<String> getAdjustCurrentEffect() {
        if (this.mAdjustCurrentEffects == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String next : this.mAdjustCurrentEffects.values()) {
            if (next != null) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public boolean isTracking() {
        return this.mIsTracking;
    }

    public void refreshData() {
        AdjustAdapter adjustAdapter = this.mAdapter;
        if (adjustAdapter != null) {
            adjustAdapter.setSelection(-1);
        }
    }

    public void setIAdjustEffectChangeListener(IAdjustEffectChangeListener iAdjustEffectChangeListener) {
        this.mIAdjustEffectChangeListener = iAdjustEffectChangeListener;
    }

    public void setIFilterAdjustHeadViewListener(IFilterAdjustHeadViewListener iFilterAdjustHeadViewListener) {
        this.mIFilterAdjustHeadViewListener = iFilterAdjustHeadViewListener;
    }
}
