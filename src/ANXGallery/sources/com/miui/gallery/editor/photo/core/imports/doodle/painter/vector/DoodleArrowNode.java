package com.miui.gallery.editor.photo.core.imports.doodle.painter.vector;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.miui.gallery.R;
import com.miui.gallery.editor.photo.core.imports.doodle.painter.DoodleItem;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class DoodleArrowNode extends DoodleVectorNode {
    public static final Parcelable.Creator<DoodleArrowNode> CREATOR = new Parcelable.Creator<DoodleArrowNode>() {
        public DoodleArrowNode createFromParcel(Parcel parcel) {
            return new DoodleArrowNode(parcel);
        }

        public DoodleArrowNode[] newArray(int i) {
            return new DoodleArrowNode[i];
        }
    };
    private static final DoodleItem DOODLE_ITEM = DoodleItem.ARROW;
    private static Reference<Drawable> sBackground;
    private float mArrowWidth;
    private Drawable mBackground;
    private ColorFilter mColorFilter;
    private float[] mPoint_1 = new float[2];
    private float[] mPoint_2 = new float[2];
    private Matrix mRotateMatrix = new Matrix();

    public DoodleArrowNode(Resources resources) {
        super(resources, true);
    }

    protected DoodleArrowNode(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void drawSafely(Canvas canvas) {
        canvas.save();
        configCanvas(canvas, false);
        float f = this.mEndPoint.y - this.mStartPoint.y;
        float f2 = this.mEndPoint.x - this.mStartPoint.x;
        this.mPoint_1[0] = this.mStartPoint.x;
        this.mPoint_1[1] = this.mStartPoint.y;
        this.mPoint_2[0] = this.mEndPoint.x;
        this.mPoint_2[1] = this.mEndPoint.y;
        double degrees = Math.toDegrees(Math.atan2((double) f, (double) f2));
        float[] fArr = this.mPoint_1;
        this.mRotateMatrix.setRotate((float) (-degrees), fArr[0], fArr[1]);
        this.mRotateMatrix.mapPoints(this.mPoint_2);
        float f3 = this.mPoint_2[0] - this.mPoint_1[0];
        this.mArrowWidth = f3;
        canvas.translate(this.mStartPoint.x, this.mStartPoint.y);
        canvas.rotate(45.0f);
        canvas.rotate((float) degrees);
        int sqrt = (int) ((float) (((double) f3) / Math.sqrt(2.0d)));
        this.mBackground.setBounds(0, -sqrt, sqrt, 0);
        this.mBackground.setColorFilter(this.mColorFilter);
        this.mBackground.draw(canvas);
        canvas.restore();
    }

    public String getDoodleName() {
        return DOODLE_ITEM.name();
    }

    /* access modifiers changed from: protected */
    public void init(Resources resources) {
        Reference<Drawable> reference = sBackground;
        if (reference == null || reference.get() == null) {
            sBackground = new WeakReference(resources.getDrawable(R.drawable.doodle_arrow));
        }
        this.mBackground = sBackground.get();
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mColorFilter = new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_IN);
    }

    /* access modifiers changed from: protected */
    public void refreshRectByPoint() {
        super.refreshRectByPoint();
        this.mRectF.inset(0.0f, -((this.mArrowWidth * 0.18f) / 2.0f));
    }

    public void setPaintColor(int i) {
        super.setPaintColor(i);
        this.mColorFilter = new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
