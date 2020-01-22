package com.nexstreaming.kminternal.kinemaster.config;

import android.content.Context;
import android.util.Log;
import com.nexstreaming.kminternal.nexvideoeditor.NexEditor;
import com.nexstreaming.kminternal.nexvideoeditor.NexImageLoader;
import com.nexstreaming.kminternal.nexvideoeditor.NexThemeView;
import java.io.File;

/* compiled from: KineMasterSingleTon */
public class a {
    private static a b;
    Context a;
    private NexEditor c;
    private NexEditor.EditorInitException d;
    private UnsatisfiedLinkError e;
    private Object f = new Object();

    public a(Context context) {
        this.a = context;
        b = this;
    }

    public static a a() {
        if (b == null) {
            Log.e("KineMasterSingleTon", "getApplicationInstance : Returning NULL!");
        }
        return b;
    }

    private void e() {
        if (this.c == null) {
            try {
                Log.d("KineMasterSingleTon", "Editor Instance Created");
                AnonymousClass1 r7 = new NexImageLoader.d() {
                    public String a(String str) {
                        return new File(EditorGlobal.i(), str).getAbsolutePath();
                    }
                };
                NexEditorDeviceProfile deviceProfile = NexEditorDeviceProfile.getDeviceProfile();
                int[] iArr = new int[7];
                iArr[0] = 2;
                int i = 1;
                iArr[1] = deviceProfile.getGLDepthBufferBits();
                iArr[2] = 1;
                if (!deviceProfile.getGLMultisample()) {
                    i = 0;
                }
                iArr[3] = i;
                iArr[4] = 3;
                iArr[5] = deviceProfile.getNativeLogLevel();
                iArr[6] = 0;
                this.c = new NexEditor(this.a, (NexThemeView) null, EditorGlobal.d(), EditorGlobal.c(), r7, iArr);
                this.c.createProject();
            } catch (UnsatisfiedLinkError e2) {
                Log.e("KineMasterSingleTon", "UnsatisfiedLinkError!!!");
                this.e = e2;
            } catch (NexEditor.EditorInitException e3) {
                Log.e("KineMasterSingleTon", "EditorInitException!!!");
                this.d = e3;
            }
        }
    }

    public Context b() {
        return this.a;
    }

    public NexEditor c() {
        NexEditor nexEditor;
        synchronized (this.f) {
            if (this.c == null) {
                Log.d("KineMasterSingleTon", "getEditor : creating editor instance");
                e();
            }
            if (this.c == null) {
                Log.e("KineMasterSingleTon", "getEditor : editor instance is null");
            }
            nexEditor = this.c;
        }
        return nexEditor;
    }

    public void d() {
        if (this.c != null) {
            Log.d("KineMasterSingleTon", "releaseEditor : release editor instance");
            this.c.closeProject();
            this.c.i();
            this.c = null;
        }
    }
}
