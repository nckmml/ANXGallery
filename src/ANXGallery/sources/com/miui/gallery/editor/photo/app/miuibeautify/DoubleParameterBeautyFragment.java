package com.miui.gallery.editor.photo.app.miuibeautify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.miui.filtersdk.beauty.BeautyParameterType;
import com.miui.filtersdk.beauty.BeautyProcessorManager;
import com.miui.filtersdk.beauty.IntelligentBeautyProcessor;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.miuibeauty.MiuiBeautyEffect;
import com.miui.gallery.editor.photo.widgets.seekbar.BubbleIndicator;

public class DoubleParameterBeautyFragment extends ChildMenuFragment {
    private BubbleIndicator.Callback<TextView> mIndicatorCallback = new BubbleIndicator.Callback<TextView>() {
        public void updateProgress(TextView textView, int i) {
            textView.setText(String.valueOf(i));
        }
    };
    private SeekBar mSeekBarBottom;
    private SeekBar mSeekBarTop;

    private class ParameterChangeListener implements SeekBar.OnSeekBarChangeListener {
        private MiuiBeautyEffect mEffect;

        public ParameterChangeListener(MiuiBeautyEffect miuiBeautyEffect) {
            this.mEffect = miuiBeautyEffect;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            float[] supportedParamRange;
            IntelligentBeautyProcessor beautyProcessor = BeautyProcessorManager.INSTANCE.getBeautyProcessor();
            MiuiBeautyEffect miuiBeautyEffect = this.mEffect;
            if (miuiBeautyEffect != null && (supportedParamRange = beautyProcessor.getSupportedParamRange(miuiBeautyEffect.mBeautyType)) != null && supportedParamRange.length >= 2) {
                DoubleParameterBeautyFragment.this.setBeautyParameterTable(this.mEffect.mBeautyType, (((float) seekBar.getProgress()) / 100.0f) * (supportedParamRange[1] - supportedParamRange[0]));
                DoubleParameterBeautyFragment.this.notifyBeautyParameterChanged();
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.miuibeauty_double_param_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            MiuiBeautyEffect miuiBeautyEffect = (MiuiBeautyEffect) arguments.getParcelable("BEAUTY_EFFECT");
            MiuiBeautyEffect miuiBeautyEffect2 = miuiBeautyEffect.mBeautyType == BeautyParameterType.WHITEN_STRENGTH ? new MiuiBeautyEffect(getActivity().getResources().getString(R.string.photo_editor_miui_beauty_menu_skin_ruddy), -1, BeautyParameterType.RUDDY_STRENGTH) : miuiBeautyEffect;
            ((TextView) view.findViewById(R.id.top_param_title)).setText(miuiBeautyEffect.mName);
            ((TextView) view.findViewById(R.id.bottom_param_title)).setText(miuiBeautyEffect2.mName);
            this.mSeekBarTop = (SeekBar) view.findViewById(R.id.seekbar_top);
            this.mSeekBarBottom = (SeekBar) view.findViewById(R.id.seekbar_bottom);
            this.mSeekBarTop.setOnSeekBarChangeListener(new BubbleIndicator((TextView) View.inflate(getActivity(), R.layout.seekbar_bubble_indicator_text, (ViewGroup) null), getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), this.mIndicatorCallback, new ParameterChangeListener(miuiBeautyEffect)));
            this.mSeekBarBottom.setOnSeekBarChangeListener(new BubbleIndicator((TextView) View.inflate(getActivity(), R.layout.seekbar_bubble_indicator_text, (ViewGroup) null), getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_bubble_indicator_offset), this.mIndicatorCallback, new ParameterChangeListener(miuiBeautyEffect2)));
        }
    }
}
