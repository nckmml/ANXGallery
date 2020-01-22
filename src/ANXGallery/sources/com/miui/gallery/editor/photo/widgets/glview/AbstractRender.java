package com.miui.gallery.editor.photo.widgets.glview;

import android.opengl.GLSurfaceView;
import java.util.LinkedList;
import javax.microedition.khronos.opengles.GL10;

public abstract class AbstractRender implements GLSurfaceView.Renderer {
    private final LinkedList<Runnable> mRunOnDraw = new LinkedList<>();

    public void onDrawFrame(GL10 gl10) {
        runPendingOnDrawTasks();
    }

    public void runOnDraw(Runnable runnable) {
        synchronized (this.mRunOnDraw) {
            this.mRunOnDraw.addLast(runnable);
        }
    }

    /* access modifiers changed from: protected */
    public void runPendingOnDrawTasks() {
        synchronized (this.mRunOnDraw) {
            while (!this.mRunOnDraw.isEmpty()) {
                this.mRunOnDraw.removeFirst().run();
            }
        }
    }
}
