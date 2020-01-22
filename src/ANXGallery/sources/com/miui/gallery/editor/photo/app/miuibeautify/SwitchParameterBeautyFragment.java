package com.miui.gallery.editor.photo.app.miuibeautify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.miui.filtersdk.beauty.BeautyProcessorManager;
import com.miui.filtersdk.beauty.IntelligentBeautyProcessor;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.miuibeauty.MiuiBeautyEffect;
import miui.widget.SlidingButton;

public class SwitchParameterBeautyFragment extends ChildMenuFragment {
    private SlidingButton mSlidingButton;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.miuibeauty_switch_param_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ((TextView) view.findViewById(R.id.switch_param_title)).setText(getActivity().getResources().getString(R.string.photo_editor_miui_beauty_menu_eraser_blemish_title));
            this.mSlidingButton = view.findViewById(R.id.check_box);
            final MiuiBeautyEffect miuiBeautyEffect = (MiuiBeautyEffect) arguments.getParcelable("BEAUTY_EFFECT");
            this.mSlidingButton.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    float[] supportedParamRange;
                    IntelligentBeautyProcessor beautyProcessor = BeautyProcessorManager.INSTANCE.getBeautyProcessor();
                    MiuiBeautyEffect miuiBeautyEffect = miuiBeautyEffect;
                    if (miuiBeautyEffect != null && (supportedParamRange = beautyProcessor.getSupportedParamRange(miuiBeautyEffect.mBeautyType)) != null && supportedParamRange.length >= 2) {
                        SwitchParameterBeautyFragment.this.setBeautyParameterTable(miuiBeautyEffect.mBeautyType, z ? 1.0f : 0.0f);
                        SwitchParameterBeautyFragment.this.notifyBeautyParameterChanged();
                    }
                }
            });
        }
    }
}
