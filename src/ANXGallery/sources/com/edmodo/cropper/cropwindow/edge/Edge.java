package com.edmodo.cropper.cropwindow.edge;

import android.graphics.RectF;
import com.edmodo.cropper.util.AspectRatioUtil;

public enum Edge {
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;
    
    private float mCoordinate;

    /* renamed from: com.edmodo.cropper.cropwindow.edge.Edge$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            $SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge = new int[Edge.values().length];
            $SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[Edge.LEFT.ordinal()] = 1;
            $SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[Edge.TOP.ordinal()] = 2;
            $SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[Edge.RIGHT.ordinal()] = 3;
            try {
                $SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[Edge.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private static float adjustBottom(float f, RectF rectF, float f2, float f3) {
        if (rectF.bottom - f < f2) {
            return rectF.bottom;
        }
        float f4 = Float.NEGATIVE_INFINITY;
        float coordinate = f <= TOP.getCoordinate() + 40.0f ? TOP.getCoordinate() + 40.0f : Float.NEGATIVE_INFINITY;
        if ((f - TOP.getCoordinate()) * f3 <= 40.0f) {
            f4 = TOP.getCoordinate() + (40.0f / f3);
        }
        return Math.min(rectF.bottom, Math.max(f, Math.max(f4, coordinate)));
    }

    private static float adjustLeft(float f, RectF rectF, float f2, float f3) {
        if (f - rectF.left < f2) {
            return rectF.left;
        }
        float f4 = Float.POSITIVE_INFINITY;
        float coordinate = f >= RIGHT.getCoordinate() - 40.0f ? RIGHT.getCoordinate() - 40.0f : Float.POSITIVE_INFINITY;
        if ((RIGHT.getCoordinate() - f) / f3 <= 40.0f) {
            f4 = RIGHT.getCoordinate() - (f3 * 40.0f);
        }
        return Math.max(rectF.left, Math.min(f, Math.min(coordinate, f4)));
    }

    private static float adjustRight(float f, RectF rectF, float f2, float f3) {
        if (rectF.right - f < f2) {
            return rectF.right;
        }
        float f4 = Float.NEGATIVE_INFINITY;
        float coordinate = f <= LEFT.getCoordinate() + 40.0f ? LEFT.getCoordinate() + 40.0f : Float.NEGATIVE_INFINITY;
        if ((f - LEFT.getCoordinate()) / f3 <= 40.0f) {
            f4 = LEFT.getCoordinate() + (f3 * 40.0f);
        }
        return Math.min(rectF.right, Math.max(f, Math.max(coordinate, f4)));
    }

    private static float adjustTop(float f, RectF rectF, float f2, float f3) {
        if (f - rectF.top < f2) {
            return rectF.top;
        }
        float f4 = Float.POSITIVE_INFINITY;
        float coordinate = f >= BOTTOM.getCoordinate() - 40.0f ? BOTTOM.getCoordinate() - 40.0f : Float.POSITIVE_INFINITY;
        if ((BOTTOM.getCoordinate() - f) * f3 <= 40.0f) {
            f4 = BOTTOM.getCoordinate() - (40.0f / f3);
        }
        return Math.max(rectF.top, Math.min(f, Math.min(coordinate, f4)));
    }

    public static float getHeight() {
        return BOTTOM.getCoordinate() - TOP.getCoordinate();
    }

    public static float getWidth() {
        return RIGHT.getCoordinate() - LEFT.getCoordinate();
    }

    private boolean isOutOfBounds(float f, float f2, float f3, float f4, RectF rectF) {
        return f < rectF.top || f2 < rectF.left || f3 > rectF.bottom || f4 > rectF.right;
    }

    public void adjustCoordinate(float f) {
        float coordinate = LEFT.getCoordinate();
        float coordinate2 = TOP.getCoordinate();
        float coordinate3 = RIGHT.getCoordinate();
        float coordinate4 = BOTTOM.getCoordinate();
        int i = AnonymousClass1.$SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[ordinal()];
        if (i == 1) {
            this.mCoordinate = AspectRatioUtil.calculateLeft(coordinate2, coordinate3, coordinate4, f);
        } else if (i == 2) {
            this.mCoordinate = AspectRatioUtil.calculateTop(coordinate, coordinate3, coordinate4, f);
        } else if (i == 3) {
            this.mCoordinate = AspectRatioUtil.calculateRight(coordinate, coordinate2, coordinate4, f);
        } else if (i == 4) {
            this.mCoordinate = AspectRatioUtil.calculateBottom(coordinate, coordinate2, coordinate3, f);
        }
    }

    public void adjustCoordinate(float f, float f2, RectF rectF, float f3, float f4) {
        int i = AnonymousClass1.$SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[ordinal()];
        if (i == 1) {
            this.mCoordinate = adjustLeft(f, rectF, f3, f4);
        } else if (i == 2) {
            this.mCoordinate = adjustTop(f2, rectF, f3, f4);
        } else if (i == 3) {
            this.mCoordinate = adjustRight(f, rectF, f3, f4);
        } else if (i == 4) {
            this.mCoordinate = adjustBottom(f2, rectF, f3, f4);
        }
    }

    public float getCoordinate() {
        return this.mCoordinate;
    }

    public boolean isNewRectangleOutOfBounds(Edge edge, RectF rectF, float f) {
        float snapOffset = edge.snapOffset(rectF);
        int i = AnonymousClass1.$SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        if (edge.equals(LEFT)) {
                            float f2 = rectF.left;
                            float coordinate = RIGHT.getCoordinate() - snapOffset;
                            float coordinate2 = TOP.getCoordinate();
                            return isOutOfBounds(coordinate2, f2, AspectRatioUtil.calculateBottom(f2, coordinate2, coordinate, f), coordinate, rectF);
                        } else if (edge.equals(RIGHT)) {
                            float f3 = rectF.right;
                            float coordinate3 = LEFT.getCoordinate() - snapOffset;
                            float coordinate4 = TOP.getCoordinate();
                            return isOutOfBounds(coordinate4, coordinate3, AspectRatioUtil.calculateBottom(coordinate3, coordinate4, f3, f), f3, rectF);
                        }
                    }
                } else if (edge.equals(TOP)) {
                    float f4 = rectF.top;
                    float coordinate5 = BOTTOM.getCoordinate() - snapOffset;
                    float coordinate6 = LEFT.getCoordinate();
                    return isOutOfBounds(f4, coordinate6, coordinate5, AspectRatioUtil.calculateRight(coordinate6, f4, coordinate5, f), rectF);
                } else if (edge.equals(BOTTOM)) {
                    float f5 = rectF.bottom;
                    float coordinate7 = TOP.getCoordinate() - snapOffset;
                    float coordinate8 = LEFT.getCoordinate();
                    return isOutOfBounds(coordinate7, coordinate8, f5, AspectRatioUtil.calculateRight(coordinate8, coordinate7, f5, f), rectF);
                }
            } else if (edge.equals(LEFT)) {
                float f6 = rectF.left;
                float coordinate9 = RIGHT.getCoordinate() - snapOffset;
                float coordinate10 = BOTTOM.getCoordinate();
                return isOutOfBounds(AspectRatioUtil.calculateTop(f6, coordinate9, coordinate10, f), f6, coordinate10, coordinate9, rectF);
            } else if (edge.equals(RIGHT)) {
                float f7 = rectF.right;
                float coordinate11 = LEFT.getCoordinate() - snapOffset;
                float coordinate12 = BOTTOM.getCoordinate();
                return isOutOfBounds(AspectRatioUtil.calculateTop(coordinate11, f7, coordinate12, f), coordinate11, coordinate12, f7, rectF);
            }
        } else if (edge.equals(TOP)) {
            float f8 = rectF.top;
            float coordinate13 = BOTTOM.getCoordinate() - snapOffset;
            float coordinate14 = RIGHT.getCoordinate();
            return isOutOfBounds(f8, AspectRatioUtil.calculateLeft(f8, coordinate14, coordinate13, f), coordinate13, coordinate14, rectF);
        } else if (edge.equals(BOTTOM)) {
            float f9 = rectF.bottom;
            float coordinate15 = TOP.getCoordinate() - snapOffset;
            float coordinate16 = RIGHT.getCoordinate();
            return isOutOfBounds(coordinate15, AspectRatioUtil.calculateLeft(coordinate15, coordinate16, f9, f), f9, coordinate16, rectF);
        }
        return true;
    }

    public boolean isOutsideMargin(RectF rectF, float f) {
        int i = AnonymousClass1.$SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (rectF.bottom - this.mCoordinate >= f) {
                        return false;
                    }
                } else if (rectF.right - this.mCoordinate >= f) {
                    return false;
                }
            } else if (this.mCoordinate - rectF.top >= f) {
                return false;
            }
        } else if (this.mCoordinate - rectF.left >= f) {
            return false;
        }
        return true;
    }

    public void offset(float f) {
        this.mCoordinate += f;
    }

    public void setCoordinate(float f) {
        this.mCoordinate = f;
    }

    public float snapOffset(RectF rectF) {
        float f = this.mCoordinate;
        int i = AnonymousClass1.$SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[ordinal()];
        return (i != 1 ? i != 2 ? i != 3 ? rectF.bottom : rectF.right : rectF.top : rectF.left) - f;
    }

    public float snapToRect(RectF rectF) {
        float f = this.mCoordinate;
        int i = AnonymousClass1.$SwitchMap$com$edmodo$cropper$cropwindow$edge$Edge[ordinal()];
        if (i == 1) {
            this.mCoordinate = rectF.left;
        } else if (i == 2) {
            this.mCoordinate = rectF.top;
        } else if (i == 3) {
            this.mCoordinate = rectF.right;
        } else if (i == 4) {
            this.mCoordinate = rectF.bottom;
        }
        return this.mCoordinate - f;
    }
}
