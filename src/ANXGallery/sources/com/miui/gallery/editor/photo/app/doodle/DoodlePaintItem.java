package com.miui.gallery.editor.photo.app.doodle;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.miui.gallery.R;
import java.util.Arrays;
import java.util.List;

public class DoodlePaintItem extends Drawable {
    private float mAlphaProgress;
    private int mBigSize;
    private int mCurrentColor;
    private Paint mPaint;
    private float mScale = 1.0f;
    private boolean mSelect;
    private int mSmallDefaultColor;
    private float mSmallSize;
    private int mStrokeColor;
    public final PaintType paintType;

    /* renamed from: com.miui.gallery.editor.photo.app.doodle.DoodlePaintItem$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType = new int[PaintType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[PaintType.HEAVY.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[PaintType.MEDIUM.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[PaintType.LIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public enum PaintType {
        HEAVY(13.333f),
        MEDIUM(4.333f),
        LIGHT(1.333f);
        
        public final float paintSize;

        private PaintType(float f) {
            this.paintSize = f;
        }
    }

    private DoodlePaintItem(PaintType paintType2, Resources resources) {
        this.paintType = paintType2;
        this.mBigSize = Math.round(resources.getDimension(R.dimen.doodle_paint_item_big_circle_width));
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[paintType2.ordinal()];
        if (i == 1) {
            this.mSmallSize = resources.getDimension(R.dimen.doodle_paint_item_small_circle_heavy);
        } else if (i == 2) {
            this.mSmallSize = resources.getDimension(R.dimen.doodle_paint_item_small_circle_medium);
        } else if (i == 3) {
            this.mSmallSize = resources.getDimension(R.dimen.doodle_paint_item_small_circle_light);
        }
        this.mStrokeColor = resources.getColor(R.color.doodle_paint_stroke_color);
        this.mSmallDefaultColor = resources.getColor(R.color.doodle_paint_small_color);
        this.mPaint = new Paint(1);
    }

    private static int getColorWithAlphaProgress(int i, float f) {
        return Color.argb((int) (((float) Color.alpha(i)) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    public static List<DoodlePaintItem> getList(Resources resources) {
        return Arrays.asList(new DoodlePaintItem[]{new DoodlePaintItem(PaintType.LIGHT, resources), new DoodlePaintItem(PaintType.MEDIUM, resources), new DoodlePaintItem(PaintType.HEAVY, resources)});
    }

    private int getSuggestColor(int i, boolean z) {
        return !z ? i : getColorWithAlphaProgress(i, this.mAlphaProgress);
    }

    public int centerX() {
        return getBounds().centerX();
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        float f = this.mScale;
        canvas.scale(f, f, (float) bounds.centerX(), (float) bounds.centerY());
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(getSuggestColor(-1, !this.mSelect));
        canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), ((float) this.mBigSize) / 2.0f, this.mPaint);
        int i = this.mSelect ? this.mCurrentColor : this.mSmallDefaultColor;
        this.mPaint.setColor(getSuggestColor(i, !this.mSelect));
        canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), this.mSmallSize / 2.0f, this.mPaint);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
        this.mPaint.setColor(getSuggestColor(this.mStrokeColor, !this.mSelect));
        canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), ((float) this.mBigSize) / 2.0f, this.mPaint);
        if (i == -1) {
            canvas.drawCircle((float) bounds.centerX(), (float) bounds.centerY(), this.mSmallSize / 2.0f, this.mPaint);
        }
        canvas.restore();
    }

    public int getIntrinsicHeight() {
        return this.mBigSize;
    }

    public int getIntrinsicWidth() {
        return this.mBigSize;
    }

    public int getOpacity() {
        return -2;
    }

    public boolean isContain(float f, float f2) {
        return getBounds().contains((int) f, (int) f2);
    }

    public void offset(int i, int i2) {
        getBounds().offset(i, i2);
    }

    public void setAlpha(float f) {
        this.mAlphaProgress = f;
    }

    public void setAlpha(int i) {
    }

    public void setBigSize(int i) {
        this.mBigSize = i;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setCurrentColor(int i) {
        this.mCurrentColor = i;
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public void setSelect(boolean z) {
        this.mSelect = z;
    }
}
