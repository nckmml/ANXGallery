package com.miui.gallery.editor.photo.app;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Property;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.Effect;
import com.miui.gallery.editor.photo.core.RenderData;
import com.miui.gallery.editor.photo.core.RenderFragment;
import com.miui.gallery.editor.photo.core.SdkManager;
import com.miui.gallery.editor.photo.core.SdkProvider;
import com.miui.gallery.util.Log;
import java.util.List;
import miui.app.ProgressDialog;
import miui.view.animation.CubicEaseOutInterpolator;

public abstract class MenuFragment<F extends RenderFragment, P extends SdkProvider<?, F>> extends EditorFragment {
    private static final Property<View, Float> VIEW_RELATIVE_Y = new Property<View, Float>(Float.class, "relative_y") {
        public Float get(View view) {
            return null;
        }

        public void set(View view, Float f) {
            view.setY((((float) ((View) view.getParent()).getHeight()) - ((float) view.getHeight())) + f.floatValue());
        }
    };
    private static int sAnimAppearDelay;
    private static int sAnimAppearDuration;
    private static int sAnimDisappearDuration;
    private static int sAnimOffset;
    Callbacks mCallbacks;
    private View mDiscardBtn;
    Effect<?> mEffect;
    private Fragment mGestureFragment;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view.getId() == R.id.ok) {
                MenuFragment.this.notifySave();
            } else if (view.getId() == R.id.cancel) {
                MenuFragment.this.notifyDiscard();
            }
        }
    };
    private RenderFragment.Callbacks mRenderCallbacks = new RenderFragment.Callbacks() {
    };
    private F mRenderFragment;
    private View mSaveBtn;
    protected final P mSdkProvider;
    protected boolean mSingleEffectMode;

    public interface Callbacks {
        void onDiscard(MenuFragment menuFragment);

        Bitmap onLoadOrigin();

        Bitmap onLoadPreview();

        List<RenderData> onLoadRenderData();

        void onSave(MenuFragment menuFragment);
    }

    public MenuFragment(Effect<P> effect) {
        this.mEffect = effect;
        this.mSdkProvider = SdkManager.INSTANCE.getProvider(effect);
    }

    private void attachRenderFragment() {
        Bitmap preview = getPreview();
        int i = -1;
        Integer valueOf = Integer.valueOf(preview == null ? -1 : preview.getWidth());
        if (preview != null) {
            i = preview.getHeight();
        }
        Log.d("MenuFragment", "MenuFragment onViewCreated and preview bitmap : %s width : %d height : %d", preview, valueOf, Integer.valueOf(i));
        this.mRenderFragment.setBitmap(preview);
        this.mRenderFragment.setBitmapRatio(preview);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.attach(this.mRenderFragment);
        Fragment fragment = this.mGestureFragment;
        if (fragment != null) {
            beginTransaction.attach(fragment);
        }
        beginTransaction.commit();
    }

    /* access modifiers changed from: protected */
    public Fragment createGestureFragment() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public F createRenderFragment() {
        return this.mSdkProvider.createFragment();
    }

    /* access modifiers changed from: protected */
    public final Bitmap decodeOrigin() {
        return this.mCallbacks.onLoadOrigin();
    }

    /* access modifiers changed from: protected */
    public final ProgressDialog genProgressDialog(String str) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(str);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        return progressDialog;
    }

    /* access modifiers changed from: protected */
    public final Fragment getGestureFragment() {
        return this.mGestureFragment;
    }

    /* access modifiers changed from: protected */
    public final List<RenderData> getPreRenderData() {
        return this.mCallbacks.onLoadRenderData();
    }

    /* access modifiers changed from: protected */
    public final Bitmap getPreview() {
        return this.mCallbacks.onLoadPreview();
    }

    /* access modifiers changed from: protected */
    public final F getRenderFragment() {
        return this.mRenderFragment;
    }

    /* access modifiers changed from: protected */
    public void hideProcessDialog() {
    }

    /* access modifiers changed from: protected */
    public void notifyDiscard() {
        this.mCallbacks.onDiscard(this);
    }

    /* access modifiers changed from: protected */
    public void notifySave() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (MenuFragment.this.isAdded()) {
                    MenuFragment.this.showProcessDialog();
                }
            }
        }, 1000);
        this.mCallbacks.onSave(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("MenuFragment", "MenuFragment onCreate");
        this.mGestureFragment = getFragmentManager().getFragment(getArguments(), "MenuFragment:gesture_fragment");
        if (getArguments() != null) {
            this.mSingleEffectMode = getArguments().getBoolean("single_effect_mode", false);
        }
        if (!this.mSingleEffectMode) {
            this.mRenderFragment = (RenderFragment) getFragmentManager().getFragment(getArguments(), "MenuFragment:display_fragment");
        }
        if (sAnimOffset == 0) {
            sAnimOffset = getActivity().getResources().getDimensionPixelSize(R.dimen.photo_editor_enter_sub_editor_sub_menu_offset);
        }
        if (sAnimAppearDuration == 0) {
            sAnimAppearDuration = getActivity().getResources().getInteger(R.integer.photo_editor_sub_editor_sub_menu_appear_duration);
        }
        if (sAnimDisappearDuration == 0) {
            sAnimDisappearDuration = getActivity().getResources().getInteger(R.integer.photo_editor_sub_editor_sub_menu_disappear_duration);
        }
        if (sAnimAppearDelay == 0) {
            sAnimAppearDelay = getActivity().getResources().getInteger(R.integer.photo_editor_sub_editor_sub_menu_appear_delay);
        }
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        if (this.mSingleEffectMode) {
            return null;
        }
        ObjectAnimator objectAnimator = new ObjectAnimator();
        if (z) {
            objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{(float) sAnimOffset, 0.0f}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{0.0f, 1.0f})});
            objectAnimator.setDuration((long) sAnimAppearDuration);
            objectAnimator.setStartDelay((long) sAnimAppearDelay);
        } else {
            objectAnimator.setValues(new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(VIEW_RELATIVE_Y, new float[]{0.0f, (float) sAnimOffset}), PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{1.0f, 0.0f})});
            objectAnimator.setDuration((long) sAnimDisappearDuration);
        }
        objectAnimator.setInterpolator(new CubicEaseOutInterpolator());
        return objectAnimator;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mRenderFragment = null;
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (!getFragmentManager().isDestroyed()) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.detach(this.mRenderFragment);
            Fragment fragment = this.mGestureFragment;
            if (fragment != null) {
                beginTransaction.detach(fragment);
            }
            beginTransaction.commit();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSaveBtn = view.findViewById(R.id.ok);
        View view2 = this.mSaveBtn;
        if (view2 != null) {
            view2.setOnClickListener(this.mOnClickListener);
        }
        this.mDiscardBtn = view.findViewById(R.id.cancel);
        View view3 = this.mDiscardBtn;
        if (view3 != null) {
            view3.setOnClickListener(this.mOnClickListener);
        }
        if (!this.mSingleEffectMode) {
            view.setAlpha(0.0f);
            attachRenderFragment();
            return;
        }
        this.mSaveBtn.setEnabled(false);
    }

    public void setRenderFragmentInSingleMode(RenderFragment renderFragment) {
        this.mRenderFragment = renderFragment;
        attachRenderFragment();
    }

    public void setSaveEnable(boolean z) {
        this.mSaveBtn.setEnabled(z);
    }

    /* access modifiers changed from: protected */
    public void showProcessDialog() {
    }
}
