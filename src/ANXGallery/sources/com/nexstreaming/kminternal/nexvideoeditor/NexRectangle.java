package com.nexstreaming.kminternal.nexvideoeditor;

public final class NexRectangle {
    public int mBottom;
    public int mLeft;
    public int mRight;
    public int mTop;

    public NexRectangle(int i, int i2, int i3, int i4) {
        this.mLeft = i;
        this.mTop = i2;
        this.mRight = i3;
        this.mBottom = i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NexRectangle nexRectangle = (NexRectangle) obj;
        if (this.mLeft == nexRectangle.mLeft && this.mTop == nexRectangle.mTop && this.mRight == nexRectangle.mRight) {
            return this.mBottom == nexRectangle.mBottom;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.mLeft * 31) + this.mTop) * 31) + this.mRight) * 31) + this.mBottom;
    }

    public void setRect(int i, int i2, int i3, int i4) {
        this.mLeft = i;
        this.mTop = i2;
        this.mRight = i3;
        this.mBottom = i4;
    }
}
