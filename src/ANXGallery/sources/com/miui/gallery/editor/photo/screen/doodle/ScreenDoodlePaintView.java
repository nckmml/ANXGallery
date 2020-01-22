package com.miui.gallery.editor.photo.screen.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.miui.gallery.editor.photo.app.doodle.DoodlePaintItem;
import java.util.List;

public class ScreenDoodlePaintView extends View {
    private Rect mBasePaintRect = new Rect();
    private int mCurrentPaintIndex = 1;
    private List<DoodlePaintItem> mDoodlePaintItemList;

    /* renamed from: com.miui.gallery.editor.photo.screen.doodle.ScreenDoodlePaintView$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType = new int[DoodlePaintItem.PaintType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[DoodlePaintItem.PaintType.HEAVY.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[DoodlePaintItem.PaintType.MEDIUM.ordinal()] = 2;
            try {
                $SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[DoodlePaintItem.PaintType.LIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public ScreenDoodlePaintView(Context context) {
        super(context);
        init();
    }

    public ScreenDoodlePaintView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ScreenDoodlePaintView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.mDoodlePaintItemList = DoodlePaintItem.getList(getResources());
    }

    private void setPaintLocation(int i, int i2) {
        this.mBasePaintRect.set(0, 0, i, i2);
        for (int i3 = 0; i3 < this.mDoodlePaintItemList.size(); i3++) {
            DoodlePaintItem doodlePaintItem = this.mDoodlePaintItemList.get(i3);
            doodlePaintItem.setBounds(this.mBasePaintRect);
            doodlePaintItem.setSelect(true);
            doodlePaintItem.setBigSize((int) (((float) i) - getResources().getDisplayMetrics().density));
        }
    }

    public DoodlePaintItem.PaintType getPaintType() {
        return this.mDoodlePaintItemList.get(this.mCurrentPaintIndex).paintType;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.mDoodlePaintItemList.get(this.mCurrentPaintIndex).draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setPaintLocation(i, i2);
    }

    public void setPaintColor(int i) {
        for (int i2 = 0; i2 < this.mDoodlePaintItemList.size(); i2++) {
            this.mDoodlePaintItemList.get(i2).setCurrentColor(i);
        }
    }

    public void setPaintType(DoodlePaintItem.PaintType paintType) {
        if (paintType != null) {
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$editor$photo$app$doodle$DoodlePaintItem$PaintType[paintType.ordinal()];
            if (i == 1) {
                this.mCurrentPaintIndex++;
                this.mCurrentPaintIndex %= this.mDoodlePaintItemList.size();
            } else if (i != 2 && i == 3) {
                this.mCurrentPaintIndex += 2;
                this.mCurrentPaintIndex %= this.mDoodlePaintItemList.size();
            }
        }
    }

    public void transSize() {
        this.mCurrentPaintIndex = (this.mCurrentPaintIndex + 1) % this.mDoodlePaintItemList.size();
        invalidate();
    }
}
