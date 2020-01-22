package com.miui.gallery.editor.photo.app.doodle;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.app.MenuFragment;
import com.miui.gallery.editor.photo.app.OperationUpdateListener;
import com.miui.gallery.editor.photo.app.menu.DoodleView;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.editor.photo.core.common.fragment.AbstractDoodleFragment;
import com.miui.gallery.editor.photo.core.common.model.DoodleData;
import com.miui.gallery.editor.photo.widgets.seekbar.BubbleIndicator;
import com.miui.gallery.editor.photo.widgets.seekbar.ColorGradientDrawable;
import com.miui.gallery.editor.photo.widgets.seekbar.ColorPicker;
import com.miui.gallery.ui.CenterSmoothScrollerController;
import com.miui.gallery.ui.SmoothScrollerController;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.ScrollLinearLayoutManager;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DoodleMenuFragment extends MenuFragment<AbstractDoodleFragment, SdkProvider<DoodleData, AbstractDoodleFragment>> {
    /* access modifiers changed from: private */
    public ColorPicker mColorPicker;
    /* access modifiers changed from: private */
    public List<DoodleData> mDoodleDataList;
    /* access modifiers changed from: private */
    public DoodlePaintView mDoodlePaintView;
    /* access modifiers changed from: private */
    public DoodleAdapter mDooodleAdapter;
    private BubbleIndicator<View> mIndicator;
    private BubbleIndicator.Callback<View> mIndicatorCallback = new BubbleIndicator.Callback<View>() {
        public void updateProgress(View view, int i) {
            ((GradientDrawable) view.getBackground()).setColor(DoodleMenuFragment.this.mColorPicker.getColor());
        }
    };
    private SimpleRecyclerView.OnItemClickListener mOnItemClickListener = new SimpleRecyclerView.OnItemClickListener() {
        public boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
            ScrollLinearLayoutManager.onItemClick(recyclerView, i);
            ((AbstractDoodleFragment) DoodleMenuFragment.this.getRenderFragment()).setDoodleData((DoodleData) DoodleMenuFragment.this.mDoodleDataList.get(i));
            if (i == DoodleMenuFragment.this.mDooodleAdapter.getSelection()) {
                return false;
            }
            DoodleMenuFragment.this.mDooodleAdapter.setSelection(i);
            DoodleMenuFragment.this.mDooodleAdapter.notifyDataSetChanged();
            return true;
        }
    };
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int color = DoodleMenuFragment.this.mColorPicker.getColor();
            DoodleMenuFragment.this.mColorPicker.setThumbColor(color);
            ((AbstractDoodleFragment) DoodleMenuFragment.this.getRenderFragment()).setColor(color);
            DoodleMenuFragment.this.mDoodlePaintView.setColor(color);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    private View.OnClickListener mOperationClickListener = new View.OnClickListener() {
        public final void onClick(View view) {
            DoodleMenuFragment.this.lambda$new$110$DoodleMenuFragment(view);
        }
    };
    private View mOperationPanel;
    private OperationUpdateListener mOperationUpdateListener = new OperationUpdateListener() {
        public final void onOperationUpdate() {
            DoodleMenuFragment.this.lambda$new$109$DoodleMenuFragment();
        }
    };
    private View.OnClickListener mPaintViewSelectClickListener = new View.OnClickListener() {
        public final void onClick(View view) {
            DoodleMenuFragment.this.lambda$new$111$DoodleMenuFragment(view);
        }
    };
    private SimpleRecyclerView mRecyclerView;
    private View mRevert;
    private View mRevoke;
    private ScrollLinearLayoutManager mScrollLinearLayoutManger;
    private SmoothScrollerController mSmoothScrollerController;
    private TextView mTitleView;

    public DoodleMenuFragment() {
        super(Effect.DOODLE);
    }

    private void initRecyclerView() {
        this.mScrollLinearLayoutManger = new ScrollLinearLayoutManager(getActivity());
        this.mSmoothScrollerController = new CenterSmoothScrollerController(getActivity());
        this.mScrollLinearLayoutManger.setSmoothScroller(this.mSmoothScrollerController);
        this.mScrollLinearLayoutManger.setOrientation(0);
        this.mRecyclerView.setLayoutManager(this.mScrollLinearLayoutManger);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.editor_menu_mosaic_item_decoration);
        this.mRecyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(dimensionPixelSize, dimensionPixelSize, getResources().getDimensionPixelSize(R.dimen.editor_menu_mosaic_item_decoration), 0, 0, 0));
        this.mDoodleDataList = new ArrayList(this.mSdkProvider.list());
        this.mDooodleAdapter = new DoodleAdapter(getActivity(), this.mDoodleDataList);
        this.mRecyclerView.setAdapter(this.mDooodleAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(this.mRecyclerView);
        this.mDooodleAdapter.setOnItemClickListener(this.mOnItemClickListener);
        this.mDooodleAdapter.setSelection(0);
        this.mDooodleAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    /* renamed from: refreshOperationPanel */
    public void lambda$new$109$DoodleMenuFragment() {
        this.mTitleView.setVisibility(8);
        this.mOperationPanel.setVisibility(0);
        this.mRevoke.setEnabled(((AbstractDoodleFragment) getRenderFragment()).canRevoke());
        this.mRevert.setEnabled(((AbstractDoodleFragment) getRenderFragment()).canRevert());
    }

    public /* synthetic */ void lambda$new$110$DoodleMenuFragment(View view) {
        switch (view.getId()) {
            case R.id.operation_revert:
                ((AbstractDoodleFragment) getRenderFragment()).doRevert();
                return;
            case R.id.operation_revoke:
                ((AbstractDoodleFragment) getRenderFragment()).doRevoke();
                return;
            default:
                return;
        }
    }

    public /* synthetic */ void lambda$new$111$DoodleMenuFragment(View view) {
        DoodlePaintView doodlePaintView = (DoodlePaintView) view;
        doodlePaintView.updateInnerRadiusPercent();
        ((AbstractDoodleFragment) getRenderFragment()).setPaintSize(doodlePaintView.getPaintType().paintSize * getResources().getDisplayMetrics().density);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new DoodleView(viewGroup.getContext());
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mTitleView = (TextView) view.findViewById(R.id.title);
        this.mTitleView.setText(R.string.photo_editor_doodle);
        this.mDoodlePaintView = (DoodlePaintView) view.findViewById(R.id.doodle_paint_select);
        this.mDoodlePaintView.setOnClickListener(this.mPaintViewSelectClickListener);
        this.mOperationPanel = view.findViewById(R.id.operation_panel);
        this.mRevoke = view.findViewById(R.id.operation_revoke);
        this.mRevert = view.findViewById(R.id.operation_revert);
        this.mRecyclerView = (SimpleRecyclerView) view.findViewById(R.id.recycler_view);
        this.mRevoke.setOnClickListener(this.mOperationClickListener);
        this.mRevert.setOnClickListener(this.mOperationClickListener);
        this.mColorPicker = (ColorPicker) view.findViewById(R.id.color_picker);
        this.mColorPicker.setProgressDrawable(new ColorGradientDrawable(ColorPicker.COLORS));
        initRecyclerView();
        View inflate = View.inflate(getActivity(), R.layout.doodle_color_indicator, (ViewGroup) null);
        this.mIndicator = new BubbleIndicator<>(inflate, getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), this.mIndicatorCallback, this.mOnSeekBarChangeListener);
        this.mColorPicker.setOnSeekBarChangeListener(this.mIndicator);
        ColorPicker colorPicker = this.mColorPicker;
        colorPicker.setProgress(colorPicker.getMax() / 2);
        ColorPicker colorPicker2 = this.mColorPicker;
        colorPicker2.setThumbColor(colorPicker2.getColor());
        ((GradientDrawable) inflate.getBackground()).setColor(this.mColorPicker.getColor());
        ((AbstractDoodleFragment) getRenderFragment()).setOperationUpdateListener(this.mOperationUpdateListener);
    }
}
