package com.miui.gallery.video.editor.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.video.editor.VideoEditor;
import com.miui.gallery.video.editor.factory.VideoEditorModuleFactory;
import com.miui.gallery.video.editor.interfaces.IVideoEditorListener;
import com.miui.gallery.video.editor.manager.SamplerManager;
import com.miui.gallery.video.editor.manager.SmartVideoJudgeManager;
import java.util.HashMap;
import java.util.List;
import miui.view.animation.CubicEaseOutInterpolator;

public abstract class MenuFragment extends Fragment {
    private static int sAnimAppearDelay;
    private static int sAnimAppearDuration;
    private static int sAnimDisappearDuration;
    private static int sAnimOffset;
    /* access modifiers changed from: protected */
    public IVideoEditorListener.IVideoEditorFragmentCallback mCallback;
    /* access modifiers changed from: protected */
    public Context mContext;
    protected boolean mHasLoadData = false;
    protected VideoEditorModuleFactory mModuleFactory;
    private SamplerManager mSamplerManager;
    /* access modifiers changed from: protected */
    public VideoEditor mVideoEditor;

    private void initAnimatorData() {
        if (sAnimOffset == 0) {
            sAnimOffset = getActivity().getResources().getDimensionPixelSize(R.dimen.video_editor_enter_sub_editor_main_menu_offset);
        }
        if (sAnimAppearDuration == 0) {
            sAnimAppearDuration = getActivity().getResources().getInteger(R.integer.video_editor_sub_editor_sub_menu_appear_duration);
        }
        if (sAnimDisappearDuration == 0) {
            sAnimDisappearDuration = getActivity().getResources().getInteger(R.integer.video_editor_sub_editor_sub_menu_disappear_duration);
        }
        if (sAnimAppearDelay == 0) {
            sAnimAppearDelay = getActivity().getResources().getInteger(R.integer.video_editor_sub_editor_sub_menu_appear_delay);
        }
    }

    private String mapIdToStatCate(int i) {
        switch (i) {
            case R.id.video_editor_audio:
                return "video_editor_audio";
            case R.id.video_editor_filter:
                return "video_editor_filter";
            case R.id.video_editor_smart_effect:
                return "video_editor_smart_effect";
            case R.id.video_editor_trim:
                return "video_editor_clip";
            case R.id.video_editor_water_mark:
                return "video_editor_text";
            default:
                return "video_editor_unknown";
        }
    }

    public abstract boolean doCancel();

    public abstract List<String> getCurrentEffect();

    public abstract int getEffectId();

    public void loadResourceData() {
    }

    public void notifyDateSetChanged(int i) {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initAnimatorData();
        this.mSamplerManager = SamplerManager.create();
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        if (z) {
            objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{(float) sAnimOffset, 0.0f}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{0.0f, 1.0f})});
            objectAnimator.setInterpolator(new CubicEaseOutInterpolator());
            if (getView() != null) {
                getView().setVisibility(4);
            }
            objectAnimator.setStartDelay((long) sAnimAppearDelay);
            objectAnimator.setDuration((long) sAnimAppearDuration);
            objectAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                    if (MenuFragment.this.getView() != null) {
                        MenuFragment.this.getView().setVisibility(0);
                    }
                }
            });
        } else {
            PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{1.0f, 0.0f});
            objectAnimator.setInterpolator(new CubicEaseOutInterpolator());
            objectAnimator.setValues(new PropertyValuesHolder[]{ofFloat});
            objectAnimator.setDuration((long) sAnimDisappearDuration);
        }
        return objectAnimator;
    }

    public void onDetach() {
        super.onDetach();
        this.mCallback = null;
    }

    /* access modifiers changed from: protected */
    public void onExitMode() {
        IVideoEditorListener.IVideoEditorFragmentCallback iVideoEditorFragmentCallback = this.mCallback;
        if (iVideoEditorFragmentCallback != null) {
            iVideoEditorFragmentCallback.showNavEditMenu();
        }
    }

    public void onPlayButtonClicked() {
    }

    public void onVideoLoadCompleted() {
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updatePlayBtnView();
    }

    /* access modifiers changed from: protected */
    public void recordEventWithApply() {
        HashMap hashMap = new HashMap();
        List<String> currentEffect = getCurrentEffect();
        if (currentEffect == null || currentEffect.isEmpty()) {
            hashMap.put("effect", "none");
            GallerySamplingStatHelper.recordCountEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
            if (SmartVideoJudgeManager.isAvailable()) {
                this.mSamplerManager.recordEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
            }
        } else {
            for (String put : currentEffect) {
                hashMap.put("effect", put);
                GallerySamplingStatHelper.recordCountEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
                if (SmartVideoJudgeManager.isAvailable()) {
                    this.mSamplerManager.recordEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
                }
            }
        }
        GallerySamplingStatHelper.recordCountEvent(mapIdToStatCate(getEffectId()), "save");
    }

    /* access modifiers changed from: protected */
    public void recordEventWithCancel() {
        HashMap hashMap = new HashMap();
        List<String> currentEffect = getCurrentEffect();
        if (currentEffect == null || currentEffect.isEmpty()) {
            hashMap.put("effect", "none");
            GallerySamplingStatHelper.recordCountEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
            if (SmartVideoJudgeManager.isAvailable()) {
                this.mSamplerManager.recordEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
            }
        } else {
            for (String put : currentEffect) {
                hashMap.put("effect", put);
                GallerySamplingStatHelper.recordCountEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
                if (SmartVideoJudgeManager.isAvailable()) {
                    this.mSamplerManager.recordEvent(mapIdToStatCate(getEffectId()), "save_detail", hashMap);
                }
            }
        }
        GallerySamplingStatHelper.recordCountEvent(mapIdToStatCate(getEffectId()), "save");
    }

    /* access modifiers changed from: protected */
    public void recordEventWithEffectChanged() {
        GallerySamplingStatHelper.recordCountEvent(mapIdToStatCate(getEffectId()), "enter");
    }

    public void setCallBack(IVideoEditorListener.IVideoEditorFragmentCallback iVideoEditorFragmentCallback) {
        this.mCallback = iVideoEditorFragmentCallback;
        this.mVideoEditor = this.mCallback.getVideoEditor();
    }

    public void updateLastFragment(MenuFragment menuFragment) {
    }

    public void updatePlayBtnView() {
        IVideoEditorListener.IVideoEditorFragmentCallback iVideoEditorFragmentCallback = this.mCallback;
        if (iVideoEditorFragmentCallback != null) {
            iVideoEditorFragmentCallback.updatePlayBtnView();
        }
    }

    public void updateVoiceState(boolean z) {
    }
}
