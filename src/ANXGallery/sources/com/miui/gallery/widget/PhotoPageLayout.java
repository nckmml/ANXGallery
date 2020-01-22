package com.miui.gallery.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.miui.gallery.R;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import com.miui.gallery.widget.IMultiThemeView;
import miui.view.animation.CubicEaseOutInterpolator;
import miui.view.animation.SineEaseInOutInterpolator;

public class PhotoPageLayout extends FrameLayout implements IMultiThemeView {
    private static final IMultiThemeView.Theme DEFAULT_THEME = IMultiThemeView.Theme.LIGHT;
    private float mAnimFinalValue;
    /* access modifiers changed from: private */
    public float mBackgroundAlpha;
    private int mBackgroundColor;
    private ValueAnimator mColorAnim;
    /* access modifiers changed from: private */
    public float mPreBackgroundAlpha;
    private int mPreBackgroundColor;
    private IMultiThemeView.Theme mTheme;
    /* access modifiers changed from: private */
    public IMultiThemeView.ThemeTransition mTransition;

    /* renamed from: com.miui.gallery.widget.PhotoPageLayout$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$widget$IMultiThemeView$Theme = new int[IMultiThemeView.Theme.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$widget$IMultiThemeView$ThemeTransition = new int[IMultiThemeView.ThemeTransition.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|(2:5|6)|7|9|10|11|12|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        static {
            try {
                $SwitchMap$com$miui$gallery$widget$IMultiThemeView$Theme[IMultiThemeView.Theme.LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$miui$gallery$widget$IMultiThemeView$Theme[IMultiThemeView.Theme.DARK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$miui$gallery$widget$IMultiThemeView$ThemeTransition[IMultiThemeView.ThemeTransition.FADE_IN.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$widget$IMultiThemeView$ThemeTransition[IMultiThemeView.ThemeTransition.FADE_OUT.ordinal()] = 2;
        }
    }

    public PhotoPageLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public PhotoPageLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoPageLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void cancelBackgroundTransition() {
        if (isBackColorTransiting()) {
            this.mColorAnim.cancel();
        }
    }

    private void drawBackground(Canvas canvas) {
        if (isBackColorTransiting()) {
            int i = this.mPreBackgroundColor;
            float f = this.mPreBackgroundAlpha;
            int i2 = this.mBackgroundColor;
            float f2 = this.mBackgroundAlpha;
            if (this.mTransition == IMultiThemeView.ThemeTransition.FADE_OUT) {
                i = this.mBackgroundColor;
                f = this.mBackgroundAlpha;
                i2 = this.mPreBackgroundColor;
                f2 = this.mPreBackgroundAlpha;
            }
            if (f > 0.0f) {
                canvas.save();
                drawColor(canvas, i, f);
                canvas.restore();
            }
            drawColor(canvas, i2, f2);
            return;
        }
        drawColor(canvas, this.mBackgroundColor, this.mBackgroundAlpha);
    }

    private void drawColor(Canvas canvas, int i, float f) {
        if (f > 0.0f) {
            canvas.drawColor(genColor(i, f));
        }
    }

    private int genColor(int i, float f) {
        return Color.argb((int) (f * 255.0f), Color.red(i), Color.green(i), Color.blue(i));
    }

    private int getBackgroundColor(IMultiThemeView.Theme theme) {
        int i = AnonymousClass2.$SwitchMap$com$miui$gallery$widget$IMultiThemeView$Theme[theme.ordinal()];
        if (i == 1) {
            return getContext().getResources().getColor(R.color.photo_page_default_background);
        }
        if (i != 2) {
            return 0;
        }
        return getContext().getResources().getColor(R.color.photo_page_fullscreen_background);
    }

    private Interpolator getDefaultTransitionInterpolator(IMultiThemeView.ThemeTransition themeTransition) {
        int i = AnonymousClass2.$SwitchMap$com$miui$gallery$widget$IMultiThemeView$ThemeTransition[themeTransition.ordinal()];
        if (i == 1) {
            return new CubicEaseOutInterpolator();
        }
        if (i != 2) {
            return null;
        }
        return new SineEaseInOutInterpolator();
    }

    private long getDefaultTransitionTime(IMultiThemeView.ThemeTransition themeTransition) {
        int integer;
        int i = AnonymousClass2.$SwitchMap$com$miui$gallery$widget$IMultiThemeView$ThemeTransition[themeTransition.ordinal()];
        if (i == 1) {
            integer = getResources().getInteger(R.integer.photo_background_in_duration);
        } else if (i != 2) {
            return 0;
        } else {
            integer = getResources().getInteger(R.integer.photo_background_out_duration);
        }
        return (long) integer;
    }

    private void init() {
        setWillNotDraw(false);
        this.mBackgroundAlpha = 1.0f;
        this.mPreBackgroundAlpha = 0.0f;
        setTheme(DEFAULT_THEME);
    }

    private boolean isBackColorTransiting() {
        ValueAnimator valueAnimator = this.mColorAnim;
        return valueAnimator != null && valueAnimator.isRunning();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawBackground(canvas);
        super.onDraw(canvas);
    }

    public void setBackgroundAlpha(float f) {
        if (!isBackColorTransiting() || !MiscUtil.floatEquals(f, this.mAnimFinalValue)) {
            cancelBackgroundTransition();
            this.mPreBackgroundAlpha = 0.0f;
            if (!MiscUtil.floatEquals(this.mBackgroundAlpha, f)) {
                this.mBackgroundAlpha = f;
                invalidate();
                return;
            }
            return;
        }
        Log.d("PhotoPageLayout", "color transiting while set alpha %s", (Object) Float.valueOf(f));
    }

    public void setTheme(IMultiThemeView.Theme theme) {
        setTheme(theme, IMultiThemeView.ThemeTransition.NONE);
    }

    public void setTheme(IMultiThemeView.Theme theme, IMultiThemeView.ThemeTransition themeTransition) {
        setTheme(theme, themeTransition, getDefaultTransitionInterpolator(themeTransition), getDefaultTransitionTime(themeTransition));
    }

    public void setTheme(IMultiThemeView.Theme theme, IMultiThemeView.ThemeTransition themeTransition, Interpolator interpolator, long j) {
        float f;
        IMultiThemeView.Theme theme2 = this.mTheme;
        if (theme2 != theme) {
            Log.d("PhotoPageLayout", "setTheme %s -> %s", theme2, theme);
            this.mTheme = theme;
            this.mPreBackgroundColor = this.mBackgroundColor;
            this.mBackgroundColor = getBackgroundColor(theme);
            this.mTransition = themeTransition;
            int i = AnonymousClass2.$SwitchMap$com$miui$gallery$widget$IMultiThemeView$ThemeTransition[themeTransition.ordinal()];
            float f2 = 0.0f;
            if (i == 1) {
                this.mPreBackgroundAlpha = this.mBackgroundAlpha;
                this.mBackgroundAlpha = 0.0f;
                if (isBackColorTransiting()) {
                    this.mPreBackgroundAlpha = this.mAnimFinalValue;
                } else {
                    this.mAnimFinalValue = this.mPreBackgroundAlpha;
                }
                f2 = this.mAnimFinalValue;
                f = 0.0f;
            } else if (i != 2) {
                this.mPreBackgroundAlpha = 0.0f;
                this.mBackgroundAlpha = 1.0f;
                this.mAnimFinalValue = 1.0f;
                f = 1.0f;
                f2 = 1.0f;
            } else {
                if (isBackColorTransiting()) {
                    this.mBackgroundAlpha = this.mAnimFinalValue;
                } else {
                    this.mAnimFinalValue = this.mBackgroundAlpha;
                }
                f = this.mAnimFinalValue;
                this.mPreBackgroundAlpha = f;
            }
            if (!MiscUtil.floatEquals(f, f2)) {
                cancelBackgroundTransition();
                if (j <= 0) {
                    j = getDefaultTransitionTime(themeTransition);
                }
                if (interpolator == null) {
                    interpolator = getDefaultTransitionInterpolator(themeTransition);
                }
                this.mColorAnim = ValueAnimator.ofFloat(new float[]{f, f2}).setDuration(j);
                this.mColorAnim.setInterpolator(interpolator);
                this.mColorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        int i = AnonymousClass2.$SwitchMap$com$miui$gallery$widget$IMultiThemeView$ThemeTransition[PhotoPageLayout.this.mTransition.ordinal()];
                        if (i == 1) {
                            float unused = PhotoPageLayout.this.mBackgroundAlpha = floatValue;
                        } else if (i == 2) {
                            float unused2 = PhotoPageLayout.this.mPreBackgroundAlpha = floatValue;
                        }
                        PhotoPageLayout.this.invalidate();
                    }
                });
                this.mColorAnim.start();
                return;
            }
            invalidate();
        }
    }
}
