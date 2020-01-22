package com.miui.gallery.editor.photo.core.imports.mosaic;

import android.opengl.GLES20;
import com.miui.gallery.editor.photo.core.imports.mosaic.MosaicOperationItem;
import com.miui.gallery.editor.photo.widgets.glview.GLFBOManager;
import com.miui.gallery.editor.photo.widgets.glview.shader.GLTextureShader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class MosaicUndoManager {
    private final int mBitmapHeight;
    private final int mBitmapWidth;
    private final LinkedList<GLFBOManager> mBufferItems = new LinkedList<>();
    private CaptureListener mCaptureListener;
    private final LinkedList<MosaicOperationItem> mMosaicOperationItems = new LinkedList<>();
    private final Stack<GLFBOManager> mReuseBufferItem = new Stack<>();
    private final LinkedList<GLFBOManager> mRevokedBufferItems = new LinkedList<>();
    private final LinkedList<RevokedItem> mRevokedOperationItems = new LinkedList<>();
    private final int mViewHeight;
    private final int mViewWidth;

    interface CaptureListener {
        void onCapture();
    }

    static class RevokedItem {
        final MosaicGLEntity mosaicGLEntity;
        final MosaicOperationItem.PaintingItem paintingItem;

        RevokedItem(MosaicGLEntity mosaicGLEntity2, MosaicOperationItem.PaintingItem paintingItem2) {
            this.mosaicGLEntity = mosaicGLEntity2;
            this.paintingItem = paintingItem2;
        }
    }

    MosaicUndoManager(int i, int i2, int i3, int i4) {
        this.mBitmapWidth = i;
        this.mBitmapHeight = i2;
        this.mViewWidth = i3;
        this.mViewHeight = i4;
    }

    private void addNewOperationItem(MosaicOperationItem.PaintingItem paintingItem, MosaicGLEntity mosaicGLEntity) {
        MosaicOperationItem mosaicOperationItem = new MosaicOperationItem(mosaicGLEntity);
        mosaicOperationItem.add(paintingItem);
        this.mMosaicOperationItems.add(mosaicOperationItem);
    }

    private void clearRevokedItem() {
        this.mRevokedOperationItems.clear();
        while (!this.mRevokedBufferItems.isEmpty()) {
            this.mReuseBufferItem.push(this.mRevokedBufferItems.removeLast());
        }
    }

    public boolean canRevert() {
        return !this.mRevokedBufferItems.isEmpty() && !this.mRevokedOperationItems.isEmpty();
    }

    public boolean canRevoke() {
        return this.mBufferItems.size() > 1 && !this.mMosaicOperationItems.isEmpty();
    }

    public void capture(GLFBOManager gLFBOManager, GLTextureShader gLTextureShader) {
        if (this.mBufferItems.size() < 6) {
            GLFBOManager pop = !this.mReuseBufferItem.isEmpty() ? this.mReuseBufferItem.pop() : new GLFBOManager(this.mBitmapWidth, this.mBitmapHeight, this.mViewWidth, this.mViewHeight);
            pop.bind();
            GLES20.glClear(16640);
            gLTextureShader.drawFBO(gLFBOManager.getTextureId());
            pop.unBind();
            this.mBufferItems.add(pop);
        } else {
            GLFBOManager removeFirst = this.mBufferItems.removeFirst();
            removeFirst.bind();
            GLES20.glClear(16640);
            gLTextureShader.drawFBO(gLFBOManager.getTextureId());
            removeFirst.unBind();
            this.mBufferItems.add(removeFirst);
        }
        CaptureListener captureListener = this.mCaptureListener;
        if (captureListener != null) {
            captureListener.onCapture();
        }
    }

    public void clearBuffer() {
        while (!this.mBufferItems.isEmpty()) {
            this.mBufferItems.removeLast().clear();
        }
        while (!this.mRevokedBufferItems.isEmpty()) {
            this.mRevokedBufferItems.removeLast().clear();
        }
        while (!this.mReuseBufferItem.isEmpty()) {
            this.mReuseBufferItem.pop().clear();
        }
    }

    public GLFBOManager doRevert() {
        if (!canRevert()) {
            return null;
        }
        RevokedItem removeLast = this.mRevokedOperationItems.removeLast();
        record(removeLast.paintingItem, removeLast.mosaicGLEntity, false);
        GLFBOManager removeLast2 = this.mRevokedBufferItems.removeLast();
        this.mBufferItems.add(removeLast2);
        return removeLast2;
    }

    public GLFBOManager doRevoke() {
        if (!canRevoke()) {
            return null;
        }
        MosaicOperationItem last = this.mMosaicOperationItems.getLast();
        this.mRevokedOperationItems.add(new RevokedItem(last.mosaicGLEntity, last.removeLast()));
        if (last.isEmpty()) {
            this.mMosaicOperationItems.removeLast();
        }
        this.mRevokedBufferItems.add(this.mBufferItems.removeLast());
        if (this.mBufferItems.isEmpty()) {
            return null;
        }
        return this.mBufferItems.getLast();
    }

    public LinkedList<MosaicOperationItem> exportRecord() {
        return this.mMosaicOperationItems;
    }

    public List<String> generateSample() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.mMosaicOperationItems.iterator();
        while (it.hasNext()) {
            arrayList.add(((MosaicOperationItem) it.next()).mosaicGLEntity.name);
        }
        return arrayList;
    }

    public boolean isEmpty() {
        if (this.mMosaicOperationItems.isEmpty()) {
            return true;
        }
        if (this.mMosaicOperationItems.size() == 1) {
            return this.mMosaicOperationItems.getLast().isEmpty();
        }
        return false;
    }

    public void record(MosaicOperationItem.PaintingItem paintingItem, MosaicGLEntity mosaicGLEntity, boolean z) {
        if (z) {
            clearRevokedItem();
        }
        if (this.mMosaicOperationItems.isEmpty()) {
            addNewOperationItem(paintingItem, mosaicGLEntity);
            return;
        }
        MosaicOperationItem last = this.mMosaicOperationItems.getLast();
        if (last.mosaicGLEntity == mosaicGLEntity) {
            last.add(paintingItem);
        } else {
            addNewOperationItem(paintingItem, mosaicGLEntity);
        }
    }

    public void setCaptureListener(CaptureListener captureListener) {
        this.mCaptureListener = captureListener;
    }
}
