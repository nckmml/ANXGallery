package com.nexstreaming.kminternal.kinemaster.editorwrapper;

import android.content.Intent;
import com.google.gson_nex.Gson;
import com.nexstreaming.kminternal.kinemaster.editorwrapper.KMIntentData;
import java.util.ArrayList;

/* compiled from: KMIntentBuilder */
public class a {
    private KMIntentData a = new KMIntentData();

    /* renamed from: com.nexstreaming.kminternal.kinemaster.editorwrapper.a$a  reason: collision with other inner class name */
    /* compiled from: KMIntentBuilder */
    public static class C0004a {
        protected final KMIntentData.AudioClip a;
        protected final a b;

        C0004a(KMIntentData.AudioClip audioClip, a aVar) {
            this.a = audioClip;
            this.b = aVar;
        }

        public C0004a a(int i) {
            this.a.startTrim = i;
            return this;
        }

        public C0004a a(String str) {
            this.a.path = str;
            return this;
        }

        public C0004a a(boolean z) {
            this.a.loop = z;
            return this;
        }

        public a a() {
            return this.b;
        }

        public C0004a b(int i) {
            this.a.endTrim = i;
            return this;
        }

        public C0004a b(boolean z) {
            this.a.background = z;
            return this;
        }

        public C0004a c(int i) {
            this.a.startTime = i;
            return this;
        }

        public C0004a c(boolean z) {
            this.a.mute = z;
            return this;
        }

        public C0004a d(int i) {
            this.a.endTime = i;
            return this;
        }

        public C0004a e(int i) {
            this.a.volume = i;
            return this;
        }
    }

    /* compiled from: KMIntentBuilder */
    public static class b extends h<b> {
        b(KMIntentData.VisualClip visualClip, a aVar) {
            super(visualClip, aVar);
        }

        public b a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
            this.a.cropStartLeft = f;
            this.a.cropStartTop = f2;
            this.a.cropStartRight = f3;
            this.a.cropStartBottom = f4;
            this.a.cropEndLeft = f5;
            this.a.cropEndTop = f6;
            this.a.cropEndRight = f7;
            this.a.cropEndBottom = f8;
            return this;
        }

        public b a(String str) {
            this.a.path = str;
            return this;
        }

        public a a() {
            return this.b;
        }
    }

    /* compiled from: KMIntentBuilder */
    public static class c<T> {
        protected final KMIntentData.KeyFrame a;
        protected final d<T> b;

        c(KMIntentData.KeyFrame keyFrame, d<T> dVar) {
            this.a = keyFrame;
            this.b = dVar;
        }

        public c<T> a(float f) {
            this.a.time = f;
            return this;
        }

        public c<T> a(float f, float f2) {
            KMIntentData.KeyFrame keyFrame = this.a;
            keyFrame.x = f;
            keyFrame.y = f2;
            return this;
        }

        public T a() {
            return this.b;
        }

        public c<T> b(float f) {
            this.a.angle = f;
            return this;
        }

        public c<T> c(float f) {
            this.a.alpha = f;
            return this;
        }

        public c<T> d(float f) {
            this.a.scale = f;
            return this;
        }
    }

    /* compiled from: KMIntentBuilder */
    private static class d<T> {
        protected final KMIntentData.Layer a;
        protected final a b;
        protected final T c = this;

        d(KMIntentData.Layer layer, a aVar) {
            this.a = layer;
            this.b = aVar;
        }

        public c<T> a() {
            if (this.a.keyFrames == null) {
                this.a.keyFrames = new ArrayList();
            }
            KMIntentData.KeyFrame keyFrame = new KMIntentData.KeyFrame();
            this.a.keyFrames.add(keyFrame);
            return new c<>(keyFrame, this);
        }

        public T a(int i) {
            this.a.startTime = i;
            return this.c;
        }

        public T a(String str) {
            this.a.animationIn = str;
            return this.c;
        }

        public T b(int i) {
            this.a.endTime = i;
            return this.c;
        }

        public T b(String str) {
            this.a.animationOut = str;
            return this.c;
        }

        public T c(int i) {
            this.a.animationInDuration = i;
            return this.c;
        }

        public T c(String str) {
            this.a.animationOverall = str;
            return this.c;
        }

        public T d(int i) {
            this.a.animationOutDuration = i;
            return this.c;
        }
    }

    /* compiled from: KMIntentBuilder */
    public static class e extends h<e> {
        e(KMIntentData.VisualClip visualClip, a aVar) {
            super(visualClip, aVar);
        }

        public e a(int i) {
            String hexString = Long.toHexString((((long) i) & 4294967295L) | 68719476736L);
            KMIntentData.VisualClip visualClip = this.a;
            visualClip.path = "@solid:" + hexString.substring(hexString.length() - 8) + ".jpg";
            return this;
        }

        public a a() {
            return this.b;
        }
    }

    /* compiled from: KMIntentBuilder */
    public static class f extends d<f> {
        f(KMIntentData.Layer layer, a aVar) {
            super(layer, aVar);
            layer.textLayerAttributes = new KMIntentData.TextLayerAttributes();
        }

        public /* bridge */ /* synthetic */ c a() {
            return super.a();
        }

        public f a(float f) {
            this.a.textLayerAttributes.textSize = f;
            return this;
        }

        public a b() {
            return this.b;
        }

        public f d(String str) {
            this.a.textLayerAttributes.text = str;
            return this;
        }

        public f e(int i) {
            this.a.textLayerAttributes.textColor = i;
            return this;
        }

        public f e(String str) {
            this.a.textLayerAttributes.fontId = str;
            return this;
        }

        public f f(int i) {
            this.a.textLayerAttributes.shadowColor = i;
            return this;
        }

        public f g(int i) {
            this.a.textLayerAttributes.glowColor = i;
            return this;
        }

        public f h(int i) {
            this.a.textLayerAttributes.outlineColor = i;
            return this;
        }
    }

    /* compiled from: KMIntentBuilder */
    public static class g extends h<g> {
        g(KMIntentData.VisualClip visualClip, a aVar) {
            super(visualClip, aVar);
        }

        public g a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
            this.a.cropStartLeft = f;
            this.a.cropStartTop = f2;
            this.a.cropStartRight = f3;
            this.a.cropStartBottom = f4;
            this.a.cropEndLeft = f5;
            this.a.cropEndTop = f6;
            this.a.cropEndRight = f7;
            this.a.cropEndBottom = f8;
            return this;
        }

        public g a(int i) {
            this.a.startTrim = i;
            return this;
        }

        public g a(String str) {
            this.a.path = str;
            return this;
        }

        public a a() {
            return this.b;
        }

        /* renamed from: b */
        public g d(int i) {
            this.a.rotation = i;
            return this;
        }

        public g c(int i) {
            this.a.playbackSpeed = i;
            return this;
        }
    }

    /* compiled from: KMIntentBuilder */
    private static class h<T> {
        protected final KMIntentData.VisualClip a;
        protected final a b;
        protected final T c = this;

        h(KMIntentData.VisualClip visualClip, a aVar) {
            this.a = visualClip;
            this.b = aVar;
        }

        public T a(boolean z) {
            this.a.mute = z;
            return this.c;
        }

        public T b(String str) {
            this.a.clipEffectId = str;
            return this.c;
        }

        public T b(boolean z) {
            this.a.vignette = z;
            return this.c;
        }

        public T c(String str) {
            this.a.transitionEffectId = str;
            return this.c;
        }

        public T d(int i) {
            this.a.rotation = i;
            return this.c;
        }

        public T d(String str) {
            this.a.colorFilterId = str;
            return this.c;
        }

        public T e(int i) {
            this.a.duration = i;
            return this.c;
        }

        public T f(int i) {
            this.a.transitionDuration = i;
            return this.c;
        }

        public T g(int i) {
            this.a.volume = i;
            return this.c;
        }

        public T h(int i) {
            this.a.brightness = i;
            return this.c;
        }

        public T i(int i) {
            this.a.contrast = i;
            return this.c;
        }

        public T j(int i) {
            this.a.saturation = i;
            return this.c;
        }
    }

    public g a() {
        KMIntentData.VisualClip visualClip = new KMIntentData.VisualClip();
        this.a.project.visualClips.add(visualClip);
        return new g(visualClip, this);
    }

    public b b() {
        KMIntentData.VisualClip visualClip = new KMIntentData.VisualClip();
        this.a.project.visualClips.add(visualClip);
        return new b(visualClip, this);
    }

    public e c() {
        KMIntentData.VisualClip visualClip = new KMIntentData.VisualClip();
        this.a.project.visualClips.add(visualClip);
        return new e(visualClip, this);
    }

    public C0004a d() {
        KMIntentData.AudioClip audioClip = new KMIntentData.AudioClip();
        this.a.project.audioClips.add(audioClip);
        return new C0004a(audioClip, this);
    }

    public f e() {
        KMIntentData.Layer layer = new KMIntentData.Layer();
        layer.layerType = KMIntentData.LayerType.Text;
        this.a.project.layers.add(layer);
        return new f(layer, this);
    }

    public Intent f() {
        Gson gson = new Gson();
        Intent intent = new Intent("com.kinemaster.intent.NEW_PROJECT");
        intent.setPackage("com.nexstreaming.app.kinemasterfree");
        intent.putExtra("com.kinemaster.intent.projectData", gson.toJson((Object) this.a));
        intent.setFlags(268468224);
        return intent;
    }
}
