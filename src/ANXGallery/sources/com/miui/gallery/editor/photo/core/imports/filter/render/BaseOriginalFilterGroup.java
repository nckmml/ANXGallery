package com.miui.gallery.editor.photo.core.imports.filter.render;

import android.opengl.GLES20;
import com.miui.filtersdk.filter.base.BaseOriginalFilter;
import com.miui.gallery.editor.blocksdk.Block;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.List;

public class BaseOriginalFilterGroup extends BaseOriginalFilter implements IFilterEmptyValidate, ISpecialProcessFilter {
    private List<BaseOriginalFilter> mFilters;
    private int[] mGroupFrameBufferTextures = null;
    private int[] mGroupFrameBuffers = null;
    private int mGroupFrameHeight = -1;
    private int mGroupFrameWidth = -1;

    public BaseOriginalFilterGroup() {
        super("", "");
    }

    public BaseOriginalFilterGroup(List<BaseOriginalFilter> list) {
        super("", "");
        this.mFilters = list;
    }

    private void destroyGroupFrameBuffers() {
        int[] iArr = this.mGroupFrameBufferTextures;
        if (iArr != null) {
            GLES20.glDeleteTextures(iArr.length, iArr, 0);
            this.mGroupFrameBufferTextures = null;
        }
        int[] iArr2 = this.mGroupFrameBuffers;
        if (iArr2 != null) {
            GLES20.glDeleteFramebuffers(iArr2.length, iArr2, 0);
            this.mGroupFrameBuffers = null;
        }
    }

    public int getSpecialBoard() {
        int i = 0;
        for (BaseOriginalFilter next : this.mFilters) {
            if (next instanceof ISpecialProcessFilter) {
                i = Math.max(i, ((ISpecialProcessFilter) next).getSpecialBoard());
            }
        }
        return i;
    }

    public void init() {
        for (BaseOriginalFilter init : this.mFilters) {
            init.init();
        }
    }

    public void initFrameBuffers(int i, int i2) {
        for (int i3 = 0; i3 < this.mFilters.size(); i3++) {
            this.mFilters.get(i3).initFrameBuffers(i, i2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000c  */
    public boolean isEmpty() {
        for (BaseOriginalFilter next : this.mFilters) {
            if (!(next instanceof IFilterEmptyValidate) || !((IFilterEmptyValidate) next).isEmpty()) {
                return false;
            }
            while (r0.hasNext()) {
            }
        }
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        for (BaseOriginalFilter destroy : this.mFilters) {
            destroy.destroy();
        }
        destroyGroupFrameBuffers();
    }

    public void onDisplaySizeChanged(int i, int i2) {
        super.onDisplaySizeChanged(i, i2);
        for (int i3 = 0; i3 < this.mFilters.size(); i3++) {
            this.mFilters.get(i3).onDisplaySizeChanged(i, i2);
        }
    }

    public int onDrawFrame(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (this.mGroupFrameBuffers == null || this.mGroupFrameBufferTextures == null) {
            return -1;
        }
        int size = this.mFilters.size();
        int i2 = i;
        int i3 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= size) {
                return 1;
            }
            BaseOriginalFilter baseOriginalFilter = this.mFilters.get(i3);
            if (i3 >= size - 1) {
                z = false;
            }
            if (z) {
                GLES20.glViewport(0, 0, this.mInputWidth, this.mInputHeight);
                int[] iArr = this.mGroupFrameBuffers;
                GLES20.glBindFramebuffer(36160, iArr[i3 % iArr.length]);
                baseOriginalFilter.onDrawFrame(i2, this.mGLCubeBuffer, this.mGLTextureBuffer);
                GLES20.glBindFramebuffer(36160, 0);
                i2 = this.mGroupFrameBufferTextures[i3 % this.mGroupFrameBuffers.length];
            } else {
                baseOriginalFilter.onDrawFrame(i2, floatBuffer, floatBuffer2);
            }
            i3++;
        }
    }

    public int onDrawToTexture(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        int size = this.mFilters.size();
        int i2 = i;
        int i3 = 0;
        while (i3 < size) {
            boolean z = i3 < size + -1;
            BaseOriginalFilter baseOriginalFilter = this.mFilters.get(i3);
            i2 = z ? baseOriginalFilter.onDrawToTexture(i2, this.mGLCubeBuffer, this.mGLTextureBuffer) : baseOriginalFilter.onDrawToTexture(i2, floatBuffer, floatBuffer2);
            i3++;
        }
        return i2;
    }

    public void onInputSizeChanged(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        super.onInputSizeChanged(i, i2);
        int size = this.mFilters.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.mFilters.get(i5).onInputSizeChanged(i3, i4);
        }
        int[] iArr = this.mGroupFrameBuffers;
        if (!(iArr == null || (this.mGroupFrameWidth == i3 && this.mGroupFrameHeight == i4 && iArr.length == size - 1))) {
            destroyGroupFrameBuffers();
            this.mGroupFrameWidth = i3;
            this.mGroupFrameHeight = i4;
        }
        if (this.mGroupFrameBuffers == null) {
            this.mGroupFrameBuffers = new int[2];
            this.mGroupFrameBufferTextures = new int[2];
            int i6 = 0;
            while (true) {
                int[] iArr2 = this.mGroupFrameBuffers;
                if (i6 < iArr2.length) {
                    GLES20.glGenFramebuffers(1, iArr2, i6);
                    GLES20.glGenTextures(1, this.mGroupFrameBufferTextures, i6);
                    GLES20.glBindTexture(3553, this.mGroupFrameBufferTextures[i6]);
                    GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, (Buffer) null);
                    GLES20.glTexParameterf(3553, 10240, 9729.0f);
                    GLES20.glTexParameterf(3553, 10241, 9729.0f);
                    GLES20.glTexParameterf(3553, 10242, 33071.0f);
                    GLES20.glTexParameterf(3553, 10243, 33071.0f);
                    GLES20.glBindFramebuffer(36160, this.mGroupFrameBuffers[i6]);
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.mGroupFrameBufferTextures[i6], 0);
                    GLES20.glBindTexture(3553, 0);
                    GLES20.glBindFramebuffer(36160, 0);
                    i6++;
                } else {
                    return;
                }
            }
        }
    }

    public void setBlock(Block block) {
        for (BaseOriginalFilter next : this.mFilters) {
            if (next instanceof ISpecialProcessFilter) {
                ((ISpecialProcessFilter) next).setBlock(block);
            }
        }
    }
}
