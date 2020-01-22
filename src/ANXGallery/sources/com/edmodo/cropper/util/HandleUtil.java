package com.edmodo.cropper.util;

import android.graphics.PointF;
import com.edmodo.cropper.cropwindow.handle.Handle;

public class HandleUtil {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0026, code lost:
        r0 = r7 - r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        r2 = r6 - r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r9.x = r0;
        r9.y = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        r0 = r2;
        r2 = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0023, code lost:
        r2 = r8 - r4;
     */
    public static void getOffset(Handle handle, float f, float f2, float f3, float f4, float f5, float f6, PointF pointF) {
        float f7;
        float f8 = 0.0f;
        switch (handle) {
            case TOP_LEFT:
                f8 = f3 - f;
                break;
            case TOP_RIGHT:
                break;
            case BOTTOM_LEFT:
                f8 = f3 - f;
                break;
            case BOTTOM_RIGHT:
                f8 = f5 - f;
                break;
            case LEFT:
                f7 = f3 - f;
                break;
            case TOP:
                break;
            case RIGHT:
                f7 = f5 - f;
                break;
            case BOTTOM:
                break;
            case CENTER:
                f5 = (f5 + f3) / 2.0f;
                f4 = (f4 + f6) / 2.0f;
                break;
            default:
                float f9 = 0.0f;
                break;
        }
    }

    public static Handle getPressedHandle(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8;
        Handle handle;
        float f9 = f;
        float f10 = f2;
        float f11 = f5;
        float f12 = f6;
        float calculateDistance = MathUtil.calculateDistance(f, f2, f3, f4);
        if (calculateDistance < Float.POSITIVE_INFINITY) {
            handle = Handle.TOP_LEFT;
            f8 = f4;
        } else {
            f8 = f4;
            calculateDistance = Float.POSITIVE_INFINITY;
            handle = null;
        }
        float calculateDistance2 = MathUtil.calculateDistance(f, f2, f11, f8);
        if (calculateDistance2 < calculateDistance) {
            handle = Handle.TOP_RIGHT;
            float f13 = f3;
            calculateDistance = calculateDistance2;
        } else {
            float f14 = f3;
        }
        float calculateDistance3 = MathUtil.calculateDistance(f, f2, f3, f12);
        if (calculateDistance3 < calculateDistance) {
            handle = Handle.BOTTOM_LEFT;
            calculateDistance = calculateDistance3;
        }
        float calculateDistance4 = MathUtil.calculateDistance(f, f2, f11, f12);
        if (calculateDistance4 < calculateDistance) {
            handle = Handle.BOTTOM_RIGHT;
            calculateDistance = calculateDistance4;
        }
        if (calculateDistance <= f7) {
            return handle;
        }
        if (isInHorizontalTargetZone(f, f2, f3, f5, f4, f7)) {
            return Handle.TOP;
        }
        if (isInHorizontalTargetZone(f, f2, f3, f5, f6, f7)) {
            return Handle.BOTTOM;
        }
        if (isInVerticalTargetZone(f, f2, f3, f4, f6, f7)) {
            return Handle.LEFT;
        }
        if (isInVerticalTargetZone(f, f2, f5, f4, f6, f7)) {
            return Handle.RIGHT;
        }
        if (isWithinBounds(f, f2, f3, f4, f5, f6)) {
            return Handle.CENTER;
        }
        return null;
    }

    private static boolean isInHorizontalTargetZone(float f, float f2, float f3, float f4, float f5, float f6) {
        return f > f3 && f < f4 && Math.abs(f2 - f5) <= f6;
    }

    private static boolean isInVerticalTargetZone(float f, float f2, float f3, float f4, float f5, float f6) {
        return Math.abs(f - f3) <= f6 && f2 > f4 && f2 < f5;
    }

    private static boolean isWithinBounds(float f, float f2, float f3, float f4, float f5, float f6) {
        return f >= f3 && f <= f5 && f2 >= f4 && f2 <= f6;
    }
}
