package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.io.File;
import java.text.Bidi;

@Deprecated
public final class nexOverlayKineMasterText extends nexOverlayImage {
    private static final int DEFAULT_BACKGROUND_COLOR = -2013265920;
    private static final String LOG_TAG = "nexOverlayKineMasterText";
    private static final int MAX_TEX_SIZE = 2000;
    private static final int TEXT_WRAP_WIDTH = 1280;
    private static int number = 1;
    private int TextId = 0;
    private float actualScale = 1.0f;
    private boolean bUserFontMode;
    private int backgroundColor = DEFAULT_BACKGROUND_COLOR;
    private TextPaint cachedTextPaint;
    private boolean enableBackground = false;
    private boolean enableGlow = false;
    private boolean enableGradient = false;
    private boolean enableOutline = false;
    private boolean enableShadow = true;
    private boolean extendBackground = false;
    private String fontId;
    private int glowColor = -1426063446;
    private float glowRadius = 8.0f;
    private int glowType = 0;
    private int[] gradientColors;
    private transient int height;
    private String layerText = "";
    private Context mContext;
    private int outlineColor = -3355444;
    private float outlineWidth = 1.0f;
    final boolean serialKMText = true;
    private int shadowColor = -16777216;
    private float shadowDx = 3.0f;
    private float shadowDy = 3.0f;
    private float shadowRadius = 5.0f;
    private Layout.Alignment textAlign = Layout.Alignment.ALIGN_CENTER;
    private int textColor = -1;
    private transient StaticLayout textLayout;
    private float textSize = 50.0f;
    private transient boolean validDimensions;
    private transient int width;

    @Deprecated
    public nexOverlayKineMasterText(Context context) {
        super("nexOverlayStandardText");
        this.mContext = context;
        int i = number;
        this.TextId = i;
        number = i + 1;
    }

    @Deprecated
    public nexOverlayKineMasterText(Context context, String str, int i) {
        super("nexOverlayStandardText");
        this.mContext = context;
        this.layerText = str;
        this.textSize = (float) i;
        int i2 = number;
        this.TextId = i2;
        number = i2 + 1;
    }

    private synchronized void calcDimensions() {
        if (!this.validDimensions || this.textLayout == null) {
            TextPaint paint = getPaint();
            String text = getText();
            int min = (int) Math.min((float) nexApplicationConfig.getAspectProfile().getWidth(), StaticLayout.getDesiredWidth(text, paint) + 1.0f);
            Bidi bidi = new Bidi(text, -2);
            if (!this.textAlign.equals(Layout.Alignment.ALIGN_CENTER)) {
                if (!bidi.baseIsLeftToRight()) {
                    if (bidi.baseIsLeftToRight() || !this.textAlign.equals(Layout.Alignment.ALIGN_NORMAL)) {
                        if (!bidi.baseIsLeftToRight() && this.textAlign.equals(Layout.Alignment.ALIGN_OPPOSITE)) {
                            this.textLayout = new StaticLayout(text, 0, this.layerText.length(), paint, min, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
                        }
                        int shadowPadding = getShadowPadding() * 2;
                        this.width = Math.max(1, this.textLayout.getWidth()) + shadowPadding;
                        this.height = Math.max(1, this.textLayout.getHeight()) + shadowPadding;
                        this.validDimensions = true;
                    }
                    this.textLayout = new StaticLayout(text, 0, this.layerText.length(), paint, min, Layout.Alignment.ALIGN_OPPOSITE, 1.0f, 0.0f, true);
                    int shadowPadding2 = getShadowPadding() * 2;
                    this.width = Math.max(1, this.textLayout.getWidth()) + shadowPadding2;
                    this.height = Math.max(1, this.textLayout.getHeight()) + shadowPadding2;
                    this.validDimensions = true;
                }
            }
            this.textLayout = new StaticLayout(text, 0, this.layerText.length(), paint, min, this.textAlign, 1.0f, 0.0f, true);
            int shadowPadding22 = getShadowPadding() * 2;
            this.width = Math.max(1, this.textLayout.getWidth()) + shadowPadding22;
            this.height = Math.max(1, this.textLayout.getHeight()) + shadowPadding22;
            this.validDimensions = true;
        }
    }

    private TextPaint getPaint() {
        TextPaint textPaint = this.cachedTextPaint;
        if (textPaint == null) {
            this.cachedTextPaint = new TextPaint();
        } else {
            textPaint.reset();
        }
        TextPaint textPaint2 = this.cachedTextPaint;
        textPaint2.setTextSize(this.textSize);
        textPaint2.setAntiAlias(true);
        textPaint2.setColor(-1);
        textPaint2.setStrokeWidth(this.outlineWidth);
        textPaint2.setStyle(Paint.Style.FILL_AND_STROKE);
        Typeface typeface = null;
        String str = this.fontId;
        if (str != null) {
            typeface = this.bUserFontMode ? Typeface.createFromFile(str) : nexFont.getTypeface(this.mContext, str);
        }
        if (typeface != null) {
            textPaint2.setTypeface(typeface);
        }
        return textPaint2;
    }

    private int getShadowPadding() {
        return (int) Math.ceil((double) Math.max(this.outlineWidth, Math.max(this.glowRadius, this.shadowRadius + Math.max(Math.abs(this.shadowDx), Math.abs(this.shadowDy)))));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00b9, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00bb, code lost:
        return null;
     */
    private synchronized Bitmap makeTextBitmap() {
        calcDimensions();
        int shadowPadding = getShadowPadding();
        int i = (int) (((float) this.width) * this.actualScale);
        int i2 = (int) (((float) this.height) * this.actualScale);
        if (i > 0) {
            if (i2 > 0) {
                Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.scale(this.actualScale, this.actualScale);
                float f = (float) shadowPadding;
                canvas.translate(f, f);
                TextPaint paint = this.textLayout.getPaint();
                paint.setTextAlign(Paint.Align.LEFT);
                paint.setStyle(Paint.Style.FILL);
                if (this.enableShadow) {
                    paint.setMaskFilter(new BlurMaskFilter(this.shadowRadius / this.actualScale, BlurMaskFilter.Blur.NORMAL));
                    paint.setColor(this.shadowColor);
                    canvas.save();
                    canvas.translate(this.shadowDx, this.shadowDy);
                    this.textLayout.draw(canvas);
                    canvas.restore();
                }
                if (this.enableGlow) {
                    paint.setMaskFilter(new BlurMaskFilter(this.glowRadius / this.actualScale, BlurMaskFilter.Blur.OUTER));
                    paint.setColor(this.glowColor);
                    this.textLayout.draw(canvas);
                }
                paint.setMaskFilter((MaskFilter) null);
                paint.setColor(this.textColor);
                this.textLayout.draw(canvas);
                if (this.enableOutline) {
                    this.textLayout.getPaint().setStyle(Paint.Style.STROKE);
                    paint.setColor(this.outlineColor);
                    paint.setStrokeWidth(this.outlineWidth / this.actualScale);
                    this.textLayout.draw(canvas);
                }
            }
        }
    }

    @Deprecated
    public void EnableGlow(boolean z) {
        if (this.enableGlow != z) {
            this.enableGlow = z;
            this.mUpdate = true;
        }
    }

    @Deprecated
    public void EnableOutline(boolean z) {
        if (this.enableOutline != z) {
            this.enableOutline = z;
            this.mUpdate = true;
        }
    }

    @Deprecated
    public void EnableShadow(boolean z) {
        if (this.enableShadow != z) {
            this.enableShadow = z;
            this.mUpdate = true;
        }
    }

    @Deprecated
    public String getFontId() {
        return getFontIdInternal(true);
    }

    /* access modifiers changed from: package-private */
    public String getFontIdInternal(boolean z) {
        if (z) {
            return this.fontId;
        }
        if (!this.bUserFontMode) {
            return this.fontId;
        }
        return null;
    }

    @Deprecated
    public int getGlowColor() {
        return getGlowColor(true);
    }

    /* access modifiers changed from: package-private */
    public int getGlowColor(boolean z) {
        if (z) {
            return this.glowColor;
        }
        if (this.enableGlow) {
            return this.glowColor;
        }
        return 0;
    }

    public int getHeight() {
        calcDimensions();
        return this.height;
    }

    @Deprecated
    public int getOutlineColor() {
        return getOutlineColor(true);
    }

    /* access modifiers changed from: package-private */
    public int getOutlineColor(boolean z) {
        if (z) {
            return this.outlineColor;
        }
        if (this.enableOutline) {
            return this.outlineColor;
        }
        return 0;
    }

    @Deprecated
    public int getShadowColor() {
        return getShadowColor(true);
    }

    /* access modifiers changed from: package-private */
    public int getShadowColor(boolean z) {
        if (z) {
            return this.shadowColor;
        }
        if (this.enableShadow) {
            return this.shadowColor;
        }
        return 0;
    }

    @Deprecated
    public String getText() {
        String str = this.layerText;
        return str == null ? "" : str;
    }

    @Deprecated
    public int getTextColor() {
        return this.textColor;
    }

    @Deprecated
    public float getTextSize() {
        return this.textSize;
    }

    /* access modifiers changed from: protected */
    public Bitmap getUserBitmap() {
        return makeTextBitmap();
    }

    /* access modifiers changed from: protected */
    public String getUserBitmapID() {
        return "KineMasterText-" + this.TextId;
    }

    public int getWidth() {
        calcDimensions();
        return this.width;
    }

    @Deprecated
    public boolean isEnableGlow() {
        return this.enableGlow;
    }

    @Deprecated
    public boolean isEnableOutline() {
        return this.enableOutline;
    }

    @Deprecated
    public boolean isEnableShadow() {
        return this.enableShadow;
    }

    @Deprecated
    public void setFontId(String str) {
        if (str == null) {
            this.bUserFontMode = false;
            this.fontId = null;
            this.validDimensions = false;
            this.mUpdate = true;
            calcDimensions();
        } else if (this.fontId == str) {
        } else {
            if (str.charAt(0) == '/') {
                int lastIndexOf = str.lastIndexOf(46);
                if (lastIndexOf != -1 && str.substring(lastIndexOf).compareToIgnoreCase(".ttf") == 0 && new File(str).isFile()) {
                    this.bUserFontMode = true;
                    this.fontId = str;
                    this.validDimensions = false;
                    this.mUpdate = true;
                    calcDimensions();
                }
            } else if (nexFont.isLoadedFont(str)) {
                this.bUserFontMode = false;
                this.fontId = str;
                this.validDimensions = false;
                this.mUpdate = true;
                calcDimensions();
            }
        }
    }

    @Deprecated
    public void setGlowColor(int i) {
        if (this.glowColor != i) {
            this.glowColor = i;
            if (this.enableGlow) {
                this.mUpdate = true;
            }
        }
    }

    @Deprecated
    public void setOutlineColor(int i) {
        if (this.outlineColor != i) {
            this.outlineColor = i;
            if (this.enableOutline) {
                this.mUpdate = true;
            }
        }
    }

    @Deprecated
    public void setShadowColor(int i) {
        if (this.shadowColor != i) {
            this.shadowColor = i;
            if (this.enableShadow) {
                this.mUpdate = true;
            }
        }
    }

    @Deprecated
    public void setText(String str) {
        this.layerText = str;
        this.validDimensions = false;
        this.mUpdate = true;
    }

    @Deprecated
    public void setTextColor(int i) {
        if (this.textColor != i) {
            this.textColor = i;
            this.mUpdate = true;
        }
    }

    @Deprecated
    public void setTextSize(float f) {
        this.textSize = f;
        this.validDimensions = false;
        this.mUpdate = true;
    }
}
