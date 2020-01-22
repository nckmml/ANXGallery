package com.miui.gallery.editor.photo.core;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.miui.gallery.editor.photo.core.Metadata;
import com.miui.gallery.editor.photo.core.RenderFragment;
import com.miui.gallery.editor.photo.core.RenderScript;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.List;

public abstract class SdkProvider<D extends Metadata, F extends RenderFragment> {
    private static RenderScript.Translator<?> DUMMY = new RenderScript.Translator<RenderData>(new ArrayList()) {
    };
    private Application mApplication;
    private volatile boolean mInitialized;
    private Handler mNotifyHandler = new Handler(Looper.getMainLooper());
    Effect<? extends SdkProvider<D, F>> mSupported;
    private RenderScript.Translator mTranslator;

    protected SdkProvider(Effect<? extends SdkProvider<D, F>> effect) {
        this.mSupported = effect;
    }

    /* access modifiers changed from: package-private */
    public void attach(Application application) {
        this.mApplication = application;
    }

    public abstract RenderEngine createEngine(Context context);

    public final F createFragment() {
        F onCreateFragment = onCreateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("RenderFragment:effect", this.mSupported.ordinal());
        onCreateFragment.setArguments(bundle);
        return onCreateFragment;
    }

    /* access modifiers changed from: protected */
    public final Application getApplicationContext() {
        return this.mApplication;
    }

    public boolean initialized() {
        return this.mInitialized;
    }

    public abstract List<? extends D> list();

    /* access modifiers changed from: protected */
    public final void notifyInitializeFinish() {
        Log.d("SdkProvider", "%s initialize finish, post notify task", (Object) this);
        this.mInitialized = true;
    }

    /* access modifiers changed from: protected */
    public void onActivityCreate() {
        this.mTranslator = onCreateTranslator();
    }

    /* access modifiers changed from: protected */
    public abstract F onCreateFragment();

    /* access modifiers changed from: protected */
    public RenderScript.Translator onCreateTranslator() {
        return DUMMY;
    }
}
