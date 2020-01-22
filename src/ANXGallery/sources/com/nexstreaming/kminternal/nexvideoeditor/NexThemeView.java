package com.nexstreaming.kminternal.nexvideoeditor;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NexThemeView extends TextureView implements TextureView.SurfaceTextureListener {
    private static String LOG_TAG = "VideoEditor_ThemeView";
    public static final int kEventType_Available = 1;
    public static final int kEventType_Destroyed = 3;
    public static final int kEventType_SizeChanged = 2;
    private static float mAspectRatio = 1.7777778f;
    private Surface actualSurface;
    private SurfaceTexture actualSurfaceTexture;
    private boolean isAvailable = false;
    private boolean mBlackOut = false;
    private Queue<Object> mClearListenerList = new LinkedList();
    private int mCurrentTime;
    private NexEditor mEditor = null;
    private boolean mHadNativeRenderSinceClear = false;
    private Handler mHandler = new Handler();
    private RenderType mRenderType = RenderType.None;
    private a m_captureListener = null;
    private int m_height = 0;
    private int m_width = 0;
    private b nexThemeViewCallback;
    private List<Runnable> postOnPrepareSurfaceRunnables = new ArrayList();

    private enum RenderType {
        None,
        Native,
        Clear
    }

    public static abstract class a {
    }

    public interface b {
        void onEventNotify(int i, Object obj, int i2, int i3, int i4);
    }

    public NexThemeView(Context context) {
        super(context);
        init(false, 0, 1);
    }

    public NexThemeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(false, 0, 0);
    }

    public NexThemeView(Context context, boolean z, int i, int i2) {
        super(context);
        init(z, i, i2);
    }

    private String diagString() {
        return "[0x" + Integer.toHexString(System.identityHashCode(this)) + " " + getWidth() + "x" + getHeight() + "] ";
    }

    public static float getAspectRatio() {
        return mAspectRatio;
    }

    private void init(boolean z, int i, int i2) {
        setSurfaceTextureListener(this);
    }

    public static void setAspectRatio(float f) {
        mAspectRatio = f;
    }

    private void updateActualSurface(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != this.actualSurfaceTexture) {
            this.actualSurfaceTexture = surfaceTexture;
            SurfaceTexture surfaceTexture2 = this.actualSurfaceTexture;
            if (surfaceTexture2 != null) {
                this.actualSurface = new Surface(surfaceTexture2);
            } else {
                this.actualSurface = null;
            }
        }
    }

    public void capture(a aVar) {
        this.m_captureListener = aVar;
    }

    public boolean isSurfaceAvailable() {
        return this.isAvailable;
    }

    /* access modifiers changed from: package-private */
    public void linkToEditor(NexEditor nexEditor) {
        Surface surface;
        String str = LOG_TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("linkToEditor ");
        sb.append(diagString());
        sb.append(" editor=");
        sb.append(nexEditor == null ? "NULL" : "not-null");
        Log.d(str, sb.toString());
        this.mEditor = nexEditor;
        NexEditor nexEditor2 = this.mEditor;
        if (nexEditor2 != null && (surface = this.actualSurface) != null) {
            nexEditor2.a(surface);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            i4 = size;
        } else {
            if (mode == 1073741824) {
                int min = (int) Math.min((float) size2, ((float) size) / mAspectRatio);
                i3 = min;
                i4 = (int) (((float) min) * mAspectRatio);
            } else if (mode2 == 1073741824) {
                i4 = (int) Math.min((float) size, ((float) size2) * mAspectRatio);
                i3 = (int) (((float) i4) / mAspectRatio);
            } else {
                float f = (float) size;
                float f2 = (float) size2;
                float f3 = mAspectRatio;
                if (f > f2 * f3) {
                    i4 = (int) (f2 * f3);
                } else {
                    i3 = (int) (f / f3);
                    i4 = size;
                }
            }
            setMeasuredDimension(i4, i3);
            Log.d(LOG_TAG, String.format("onMeasure(%X %X %d %d %f)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(size), Integer.valueOf(size2), Float.valueOf(mAspectRatio)}));
        }
        i3 = size2;
        setMeasuredDimension(i4, i3);
        Log.d(LOG_TAG, String.format("onMeasure(%X %X %d %d %f)", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(size), Integer.valueOf(size2), Float.valueOf(mAspectRatio)}));
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        updateActualSurface(surfaceTexture);
        this.m_width = i;
        this.m_height = i2;
        String str = LOG_TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onSurfaceTextureAvailable ");
        sb.append(diagString());
        sb.append(this.mEditor == null ? "(editor is null)" : "(editor is SET)");
        Log.d(str, sb.toString());
        NexEditor nexEditor = this.mEditor;
        if (nexEditor != null) {
            nexEditor.a(this.actualSurface);
            this.mEditor.w();
            for (Runnable post : this.postOnPrepareSurfaceRunnables) {
                post(post);
            }
            this.postOnPrepareSurfaceRunnables.clear();
        }
        b bVar = this.nexThemeViewCallback;
        if (bVar != null) {
            bVar.onEventNotify(1, (Object) null, i, i2, 0);
        }
        this.isAvailable = true;
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        String str = LOG_TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onSurfaceTextureDestroyed ");
        sb.append(diagString());
        sb.append(this.mEditor == null ? "(editor is null)" : "(editor is SET)");
        Log.d(str, sb.toString());
        NexEditor nexEditor = this.mEditor;
        if (nexEditor != null) {
            nexEditor.a((Surface) null);
        }
        this.actualSurfaceTexture = null;
        this.actualSurface = null;
        b bVar = this.nexThemeViewCallback;
        if (bVar != null) {
            bVar.onEventNotify(3, (Object) null, 0, 0, 0);
        }
        this.isAvailable = false;
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        updateActualSurface(surfaceTexture);
        this.m_width = i;
        this.m_height = i2;
        String str = LOG_TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onSurfaceTextureSizeChanged ");
        sb.append(diagString());
        sb.append(this.mEditor == null ? "(editor is null)" : "(editor is SET)");
        Log.d(str, sb.toString());
        NexEditor nexEditor = this.mEditor;
        if (nexEditor != null) {
            nexEditor.a(this.actualSurface);
            this.mEditor.w();
        }
        b bVar = this.nexThemeViewCallback;
        if (bVar != null) {
            bVar.onEventNotify(2, (Object) null, i, i2, 0);
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void postOnPrepareSurface(Runnable runnable) {
        this.postOnPrepareSurfaceRunnables.add(runnable);
    }

    public void requestDraw(int i) {
        String str = LOG_TAG;
        Log.e(str, "requestDraw(" + i + ")");
        this.mRenderType = RenderType.Native;
        this.mCurrentTime = i;
    }

    public void setBlackOut(boolean z) {
        this.mBlackOut = z;
    }

    /* access modifiers changed from: protected */
    public void setNotify(b bVar) {
        this.nexThemeViewCallback = bVar;
    }
}
