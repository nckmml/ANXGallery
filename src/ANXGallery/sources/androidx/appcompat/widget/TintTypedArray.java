package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i, iArr));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean getBoolean(int i, boolean z) {
        return this.mWrapped.getBoolean(i, z);
    }

    public int getColor(int i, int i2) {
        return this.mWrapped.getColor(i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0011, code lost:
        r0 = androidx.appcompat.content.res.AppCompatResources.getColorStateList(r2.mContext, (r0 = r2.mWrapped.getResourceId(r3, 0)));
     */
    public ColorStateList getColorStateList(int i) {
        int resourceId;
        ColorStateList colorStateList;
        return (!this.mWrapped.hasValue(i) || resourceId == 0 || colorStateList == null) ? this.mWrapped.getColorStateList(i) : colorStateList;
    }

    public int getDimensionPixelOffset(int i, int i2) {
        return this.mWrapped.getDimensionPixelOffset(i, i2);
    }

    public int getDimensionPixelSize(int i, int i2) {
        return this.mWrapped.getDimensionPixelSize(i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r2.mWrapped.getResourceId(r3, 0);
     */
    public Drawable getDrawable(int i) {
        int resourceId;
        return (!this.mWrapped.hasValue(i) || resourceId == 0) ? this.mWrapped.getDrawable(i) : AppCompatResources.getDrawable(this.mContext, resourceId);
    }

    public float getFloat(int i, float f) {
        return this.mWrapped.getFloat(i, f);
    }

    public Typeface getFont(int i, int i2, ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.mWrapped.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        return ResourcesCompat.getFont(this.mContext, resourceId, this.mTypedValue, i2, fontCallback);
    }

    public int getInt(int i, int i2) {
        return this.mWrapped.getInt(i, i2);
    }

    public int getInteger(int i, int i2) {
        return this.mWrapped.getInteger(i, i2);
    }

    public int getLayoutDimension(int i, int i2) {
        return this.mWrapped.getLayoutDimension(i, i2);
    }

    public int getResourceId(int i, int i2) {
        return this.mWrapped.getResourceId(i, i2);
    }

    public String getString(int i) {
        return this.mWrapped.getString(i);
    }

    public CharSequence getText(int i) {
        return this.mWrapped.getText(i);
    }

    public CharSequence[] getTextArray(int i) {
        return this.mWrapped.getTextArray(i);
    }

    public boolean hasValue(int i) {
        return this.mWrapped.hasValue(i);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }
}
