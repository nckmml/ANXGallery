package com.miui.gallery.editor.photo.screen.doodle;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import androidx.recyclerview.widget.RecyclerView;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.common.model.DoodleData;
import com.miui.gallery.editor.photo.core.imports.doodle.DoodleManager;
import com.miui.gallery.editor.photo.screen.base.ScreenBaseMenuFragment;
import com.miui.gallery.editor.photo.screen.stat.ScreenEditorStatUtils;
import com.miui.gallery.editor.photo.widgets.seekbar.BubbleIndicator;
import com.miui.gallery.editor.photo.widgets.seekbar.ColorGradientDrawable;
import com.miui.gallery.editor.photo.widgets.seekbar.ColorPicker;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.miui.gallery.widget.recyclerview.SimpleRecyclerView;
import java.util.List;

public class ScreenDoodleMenuFragment extends ScreenBaseMenuFragment<IScreenDoodleOperation> {
    /* access modifiers changed from: private */
    public ColorPicker mColorPicker;
    private List<DoodleData> mDoodleData;
    private BubbleIndicator<View> mIndicator;
    private BubbleIndicator.Callback<View> mIndicatorCallback = new BubbleIndicator.Callback<View>() {
        public void updateProgress(View view, int i) {
            ((GradientDrawable) view.getBackground()).setColor(ScreenDoodleMenuFragment.this.mColorPicker.getColor());
        }
    };
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            int color = ScreenDoodleMenuFragment.this.mColorPicker.getColor();
            ScreenDoodleMenuFragment.this.mColorPicker.setThumbColor(color);
            ScreenDoodleMenuFragment.this.setColor(color);
            ScreenEditorStatUtils.statDoodleColorChoose(i);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };
    private ScreenDoodlePaintView mPaintView;

    /* access modifiers changed from: private */
    public void setColor(int i) {
        ((IScreenDoodleOperation) this.mScreenOperation).setColor(i);
        this.mPaintView.setPaintColor(i);
    }

    /* access modifiers changed from: protected */
    public Class<IScreenDoodleOperation> getScreenOperationClass() {
        return IScreenDoodleOperation.class;
    }

    public /* synthetic */ boolean lambda$onViewCreated$71$ScreenDoodleMenuFragment(ScreenDoodleAdapter screenDoodleAdapter, RecyclerView recyclerView, View view, int i) {
        ((IScreenDoodleOperation) this.mScreenOperation).setDoodleData(this.mDoodleData.get(i), i);
        screenDoodleAdapter.setSelection(i);
        ScreenEditorStatUtils.statDoodleMenuItemClick(i);
        return false;
    }

    public /* synthetic */ void lambda$onViewCreated$72$ScreenDoodleMenuFragment(View view) {
        this.mPaintView.transSize();
        ((IScreenDoodleOperation) this.mScreenOperation).setPaintType(this.mPaintView.getPaintType());
        ScreenEditorStatUtils.statDoodleSizeClick(this.mPaintView.getPaintType().name());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.screen_doodle_menu_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mDoodleData = DoodleManager.getScreenDoodleData();
        ScreenDoodleAdapter screenDoodleAdapter = new ScreenDoodleAdapter(this.mDoodleData, ((IScreenDoodleOperation) this.mScreenOperation).getCurrentMenuItemIndex());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        screenDoodleAdapter.setOnItemClickListener(new SimpleRecyclerView.OnItemClickListener(screenDoodleAdapter) {
            private final /* synthetic */ ScreenDoodleAdapter f$1;

            {
                this.f$1 = r2;
            }

            public final boolean OnItemClick(RecyclerView recyclerView, View view, int i) {
                return ScreenDoodleMenuFragment.this.lambda$onViewCreated$71$ScreenDoodleMenuFragment(this.f$1, recyclerView, view, i);
            }
        });
        this.mPaintView = (ScreenDoodlePaintView) view.findViewById(R.id.doodle_paint_view);
        this.mPaintView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ScreenDoodleMenuFragment.this.lambda$onViewCreated$72$ScreenDoodleMenuFragment(view);
            }
        });
        this.mPaintView.setPaintType(((IScreenDoodleOperation) this.mScreenOperation).getPaintType());
        recyclerView.addItemDecoration(new SimpleRecyclerView.BlankDivider(0, 0, (int) (((((float) ScreenUtils.getScreenWidth()) - (getResources().getDimension(R.dimen.screen_editor_doodle_menu_horizontal_margin) * 2.0f)) - (((float) this.mDoodleData.size()) * getResources().getDimension(R.dimen.screen_editor_doodle_item_size))) / ((float) (this.mDoodleData.size() - 1))), 0, 0, 0));
        recyclerView.setAdapter(screenDoodleAdapter);
        HorizontalOverScrollBounceEffectDecorator.setOverScrollEffect(recyclerView);
        View inflate = View.inflate(getActivity(), R.layout.doodle_color_indicator, (ViewGroup) null);
        this.mIndicator = new BubbleIndicator<>(inflate, getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), this.mIndicatorCallback, this.mOnSeekBarChangeListener);
        this.mColorPicker = (ColorPicker) view.findViewById(R.id.color_picker);
        this.mColorPicker.setProgressDrawable(new ColorGradientDrawable(ColorPicker.COLORS));
        ColorPicker colorPicker = this.mColorPicker;
        colorPicker.setProgress(colorPicker.getMax() / 2);
        ColorPicker colorPicker2 = this.mColorPicker;
        colorPicker2.setThumbColor(colorPicker2.getColor());
        setColor(this.mColorPicker.getColor());
        ((GradientDrawable) inflate.getBackground()).setColor(this.mColorPicker.getColor());
        this.mColorPicker.setOnSeekBarChangeListener(this.mIndicator);
    }
}
