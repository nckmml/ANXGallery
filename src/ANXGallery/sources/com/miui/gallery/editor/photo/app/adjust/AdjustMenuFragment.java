package com.miui.gallery.editor.photo.app.adjust;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.menu.AdjustView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.Metadata;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractEffectFragment;
import com.miui.gallery.editor.photo.core.common.model.AdjustData;
import com.miui.gallery.editor.photo.widgets.recyclerview.Selectable;
import com.miui.gallery.editor.photo.widgets.seekbar.BasicSeekBar;
import com.miui.gallery.editor.photo.widgets.seekbar.BiDirectionSeekBar;
import com.miui.gallery.editor.photo.widgets.seekbar.BubbleIndicator;
import com.miui.gallery.util.Log;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;
import miui.gallery.support.linearmotor.LinearMotorHelper;

public class AdjustMenuFragment extends MenuFragment<AbstractEffectFragment, SdkProvider<AdjustData, AbstractEffectFragment>> {
    /* access modifiers changed from: private */
    public AdjustAdapter mAdapter;
    private List<AdjustData> mDataList;
    private BubbleIndicator<TextView> mIndicator;
    private BubbleIndicator.Callback<TextView> mIndicatorCallback = new BubbleIndicator.Callback<TextView>() {
        public void updateProgress(TextView textView, int i) {
            textView.setText(String.format("%d", new Object[]{Integer.valueOf(i)}));
        }
    };
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            if (AdjustMenuFragment.this.mAdapter.getSelection() == i) {
                return true;
            }
            AdjustMenuFragment.this.performItemSelect(i, false);
            return true;
        }
    };
    private SimpleRecyclerView mRecyclerView;
    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            AdjustMenuFragment.this.doConfig(i);
            if (z && i == 0) {
                LinearMotorHelper.performHapticFeedback(AdjustMenuFragment.this.getActivity(), LinearMotorHelper.EFFECT_KEY_MESH_NORMAL);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    private TextView mTitle;
    private LinearLayout mTopPanel;

    public AdjustMenuFragment() {
        super(Effect.ADJUST);
    }

    /* access modifiers changed from: private */
    public void doConfig(int i) {
        if (this.mAdapter.getSelection() == -1) {
            Log.w("AdjustMenuFragment", "no effect rendered, skip");
            return;
        }
        Log.w("AdjustMenuFragment", "AdjustData progress:" + i);
        AdjustData adjustData = this.mDataList.get(this.mAdapter.getSelection());
        adjustData.progress = i;
        ((AbstractEffectFragment) getRenderFragment()).remove(adjustData);
        ((AbstractEffectFragment) getRenderFragment()).add(adjustData, (Object) null);
        ((AbstractEffectFragment) getRenderFragment()).render();
    }

    private void doRender(Metadata metadata) {
        ((AbstractEffectFragment) getRenderFragment()).remove(metadata);
        ((AbstractEffectFragment) getRenderFragment()).add(metadata, (Object) null);
        ((AbstractEffectFragment) getRenderFragment()).render();
    }

    /* access modifiers changed from: private */
    public void performItemSelect(int i, boolean z) {
        BasicSeekBar basicSeekBar;
        AdjustData adjustData = this.mDataList.get(i);
        if (!z) {
            doRender(adjustData);
        }
        this.mAdapter.setSelection(i);
        BubbleIndicator<TextView> bubbleIndicator = this.mIndicator;
        if (bubbleIndicator != null && bubbleIndicator.isShowing()) {
            this.mIndicator.dismiss();
        }
        if (((SeekBar) this.mTopPanel.getChildAt(0)) != null) {
            this.mTopPanel.removeAllViews();
        }
        if (!adjustData.isMid()) {
            BasicSeekBar basicSeekBar2 = new BasicSeekBar(getActivity());
            basicSeekBar2.setMax(adjustData.getMax());
            basicSeekBar2.setProgress(adjustData.progress);
            basicSeekBar = basicSeekBar2;
        } else {
            BiDirectionSeekBar biDirectionSeekBar = new BiDirectionSeekBar(getActivity());
            biDirectionSeekBar.setMaxValue(adjustData.getMax());
            biDirectionSeekBar.setCurrentValue(adjustData.progress);
            basicSeekBar = biDirectionSeekBar;
        }
        this.mTopPanel.addView(basicSeekBar, -1, -2);
        this.mIndicator.onProgressChanged(basicSeekBar, adjustData.progress, false);
        basicSeekBar.setOnSeekBarChangeListener(this.mIndicator);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDataList = new ArrayList(this.mSdkProvider.list());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new AdjustView(viewGroup.getContext());
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTopPanel = (LinearLayout) view.findViewById(R.id.top_panel);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mAdapter = new AdjustAdapter(getActivity(), this.mDataList, new Selectable.Selector(getResources().getColor(R.color.photo_editor_highlight_color)));
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mAdapter.setOnItemClickListener(this.mOnItemClickListener);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mTitle.setText(R.string.photo_editor_adjust);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mIndicator = new BubbleIndicator<>((TextView) View.inflate(getActivity(), R.layout.seekbar_bubble_indicator_text, (ViewGroup) null), getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), this.mIndicatorCallback, this.mSeekBarChangeListener);
        performItemSelect(0, true);
    }
}
