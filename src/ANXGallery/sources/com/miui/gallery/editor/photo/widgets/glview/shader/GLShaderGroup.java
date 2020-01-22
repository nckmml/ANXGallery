package com.miui.gallery.editor.photo.widgets.glview.shader;

import android.opengl.GLES20;
import com.miui.gallery.editor.photo.core.imports.mosaic.shader.GLTextureSizeShader;
import com.miui.gallery.editor.photo.widgets.glview.GLFBOManager;
import java.util.ArrayList;
import java.util.List;

public class GLShaderGroup extends GLTextureSizeShader {
    private GLFBOManager[] mGLFBOManagers;
    protected List<GLTextureShader> mShaders = new ArrayList();
    protected int mTextureHeight;
    protected int mTextureWidth;
    private int mViewHeight;
    private int mViewWidth;

    public GLShaderGroup(int i, int i2, int i3, int i4) {
        super((String) null, (String) null, i, i2);
        this.mTextureWidth = i;
        this.mTextureHeight = i2;
        this.mViewWidth = i3;
        this.mViewHeight = i4;
    }

    /* access modifiers changed from: protected */
    public void addShader(GLTextureShader gLTextureShader) {
        this.mShaders.add(gLTextureShader);
    }

    public void destroy() {
        for (GLTextureShader destroy : this.mShaders) {
            destroy.destroy();
        }
        GLFBOManager[] gLFBOManagerArr = this.mGLFBOManagers;
        if (gLFBOManagerArr != null) {
            for (GLFBOManager gLFBOManager : gLFBOManagerArr) {
                if (gLFBOManager != null) {
                    gLFBOManager.clear();
                }
            }
        }
    }

    public void draw(int i, float[] fArr, float[] fArr2) {
    }

    public int getEffectedTexture(int i) {
        onPreDraw();
        for (int i2 = 0; i2 < this.mShaders.size(); i2++) {
            GLTextureShader gLTextureShader = this.mShaders.get(i2);
            this.mGLFBOManagers[i2].bind();
            GLES20.glClear(16640);
            int i3 = i2 - 1;
            if (i3 >= 0) {
                gLTextureShader.drawFBO(this.mGLFBOManagers[i3].getTextureId());
            } else {
                gLTextureShader.draw(i);
            }
            this.mGLFBOManagers[i2].unBind();
        }
        GLFBOManager[] gLFBOManagerArr = this.mGLFBOManagers;
        return gLFBOManagerArr[gLFBOManagerArr.length - 1].getTextureId();
    }

    /* access modifiers changed from: protected */
    public void init(String str, String str2) {
    }

    /* access modifiers changed from: protected */
    public void notifyShaderAdded() {
        this.mGLFBOManagers = new GLFBOManager[this.mShaders.size()];
        int i = 0;
        while (true) {
            GLFBOManager[] gLFBOManagerArr = this.mGLFBOManagers;
            if (i < gLFBOManagerArr.length) {
                gLFBOManagerArr[i] = new GLFBOManager(this.mTextureWidth, this.mTextureHeight, this.mViewWidth, this.mViewHeight);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPreDraw() {
    }
}
