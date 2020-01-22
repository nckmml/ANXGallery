package com.miui.gallery.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;
import com.miui.gallery.R;
import com.miui.gallery.compat.view.ViewCompat;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.RecyclerLayoutCache;
import miui.view.animation.SineEaseInOutInterpolator;

public class SpecialTypeEnterView {
    private View mCommonEnterView;
    private Animation mEnterHideAnim;
    private Animation mEnterShowAnim;
    private int mMarginBottom;
    private int mSystemWindowInsetBottom;

    public SpecialTypeEnterView(View view, View.OnClickListener onClickListener) {
        this.mCommonEnterView = view;
        view.setVisibility(8);
        view.setOnClickListener(onClickListener);
    }

    public SpecialTypeEnterView(ViewGroup viewGroup, View.OnClickListener onClickListener) {
        this(viewGroup, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.special_type_enter_layout, viewGroup, false), onClickListener);
    }

    public SpecialTypeEnterView(ViewGroup viewGroup, View view, View.OnClickListener onClickListener) {
        this(view, onClickListener);
        viewGroup.addView(view);
    }

    public static RecyclerLayoutCache buildCache(Context context) {
        if (context == null) {
            return null;
        }
        RecyclerLayoutCache recyclerLayoutCache = new RecyclerLayoutCache(3);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        LayoutInflater from = LayoutInflater.from(context);
        for (int i = 0; i < 3; i++) {
            recyclerLayoutCache.put(from.inflate(R.layout.special_type_enter_layout, relativeLayout, false));
        }
        return recyclerLayoutCache;
    }

    private int getInsetBottom(View view) {
        return ViewCompat.getSystemWindowInsetBottom(view);
    }

    private void setOnApplyWindowInsetsListener() {
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(this.mCommonEnterView, new OnApplyWindowInsetsListener() {
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return SpecialTypeEnterView.this.lambda$setOnApplyWindowInsetsListener$8$SpecialTypeEnterView(view, windowInsetsCompat);
            }
        });
    }

    private void startViewAlphaAnim(View view, boolean z) {
        if (view != null && view.getVisibility() == 0) {
            view.animate().alpha(z ? 1.0f : 0.0f).setDuration(0).setInterpolator(new SineEaseInOutInterpolator()).start();
        }
    }

    private void updatePosition() {
        View view = this.mCommonEnterView;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (marginLayoutParams != null) {
            marginLayoutParams.bottomMargin = getInsetBottom(view) + this.mMarginBottom;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public View getView() {
        return this.mCommonEnterView;
    }

    public void hide(boolean z) {
        View view = this.mCommonEnterView;
        if (view.getVisibility() == 0) {
            if (z) {
                if (this.mEnterHideAnim == null) {
                    this.mEnterHideAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.special_type_enter_out);
                }
                view.startAnimation(this.mEnterHideAnim);
            }
            view.setVisibility(8);
        }
    }

    public boolean isVisible() {
        return this.mCommonEnterView.getVisibility() == 0;
    }

    public /* synthetic */ WindowInsetsCompat lambda$setOnApplyWindowInsetsListener$8$SpecialTypeEnterView(View view, WindowInsetsCompat windowInsetsCompat) {
        int systemWindowInsetBottom = ViewCompat.getSystemWindowInsetBottom(this.mCommonEnterView);
        if (systemWindowInsetBottom != this.mSystemWindowInsetBottom) {
            this.mSystemWindowInsetBottom = systemWindowInsetBottom;
            updatePosition();
        }
        return windowInsetsCompat;
    }

    public void setVisible(boolean z) {
        this.mCommonEnterView.setVisibility(z ? 0 : 8);
    }

    public void show(boolean z) {
        View view = this.mCommonEnterView;
        if (view.getVisibility() == 8) {
            if (z) {
                if (this.mEnterShowAnim == null) {
                    this.mEnterShowAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.special_type_enter_in);
                }
                view.startAnimation(this.mEnterShowAnim);
            }
            view.setVisibility(0);
        }
    }

    public void startEnterAlphaAnim(boolean z) {
        startViewAlphaAnim(this.mCommonEnterView, z);
    }

    public void update(ViewGroup viewGroup, boolean z, int i, int i2) {
        if (i > 0 && i2 > 0) {
            View view = this.mCommonEnterView;
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(i);
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup2 = (ViewGroup) view;
                ((TextView) viewGroup2.findViewById(R.id.text)).setText(i2);
                ((ImageView) viewGroup2.findViewById(R.id.icon)).setImageDrawable(view.getResources().getDrawable(i));
            }
            int i3 = R.dimen.specify_type_common_enter_margin_bottom;
            if (z && IntentUtil.isSupportNewVideoPlayer()) {
                i3 = R.dimen.specify_type_video_enter_margin_bottom;
            }
            this.mMarginBottom = view.getResources().getDimensionPixelSize(i3);
            if (view.getParent() == null) {
                viewGroup.addView(view);
            }
            updatePosition();
            setOnApplyWindowInsetsListener();
        }
    }
}
