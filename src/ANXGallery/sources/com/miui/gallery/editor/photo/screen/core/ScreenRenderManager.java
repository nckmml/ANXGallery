package com.miui.gallery.editor.photo.screen.core;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import com.miui.gallery.editor.photo.app.DraftManager;
import com.miui.gallery.editor.photo.screen.base.ScreenRenderCallback;
import com.miui.gallery.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ScreenRenderManager {
    private DraftManager mDraftManager;
    private Bitmap mOrigin;
    private OnOriginLoadedListener mOriginLoadedListener;
    private Bitmap mRenderBitmap;

    public interface OnOriginLoadedListener {
        void onRefresh(Bitmap bitmap);
    }

    public ScreenRenderManager(DraftManager draftManager) {
        this.mDraftManager = draftManager;
    }

    private Bitmap getOriginBitmap() {
        Bitmap bitmap = this.mOrigin;
        return bitmap == null ? this.mDraftManager.getPreview() : bitmap;
    }

    public void decodeOrigin() {
        if (this.mDraftManager.isPreviewSameWithOrigin()) {
            this.mOrigin = this.mDraftManager.getPreview();
            OnOriginLoadedListener onOriginLoadedListener = this.mOriginLoadedListener;
            if (onOriginLoadedListener != null) {
                onOriginLoadedListener.onRefresh(this.mOrigin);
                return;
            }
            return;
        }
        Observable.create(new ObservableOnSubscribe() {
            public final void subscribe(ObservableEmitter observableEmitter) {
                ScreenRenderManager.this.lambda$decodeOrigin$67$ScreenRenderManager(observableEmitter);
            }
        }).subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                ScreenRenderManager.this.lambda$decodeOrigin$68$ScreenRenderManager((Bitmap) obj);
            }
        });
    }

    public Bitmap getRenderBitmap() {
        Bitmap bitmap = this.mRenderBitmap;
        return bitmap == null ? getOriginBitmap() : bitmap;
    }

    public /* synthetic */ void lambda$decodeOrigin$67$ScreenRenderManager(ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(this.mDraftManager.decodeOrigin());
    }

    public /* synthetic */ void lambda$decodeOrigin$68$ScreenRenderManager(Bitmap bitmap) throws Exception {
        this.mOrigin = bitmap;
        OnOriginLoadedListener onOriginLoadedListener = this.mOriginLoadedListener;
        if (onOriginLoadedListener != null) {
            onOriginLoadedListener.onRefresh(this.mOrigin);
        }
    }

    public /* synthetic */ void lambda$renderBitmap$69$ScreenRenderManager(ScreenRenderData screenRenderData, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(screenRenderData.apply(getOriginBitmap()));
    }

    public /* synthetic */ void lambda$renderBitmap$70$ScreenRenderManager(ScreenRenderCallback screenRenderCallback, boolean z, Bitmap bitmap) throws Exception {
        this.mRenderBitmap = bitmap;
        screenRenderCallback.setShareBitmap(bitmap, true);
        screenRenderCallback.onComplete(z);
    }

    public void release() {
        Bitmap bitmap = this.mRenderBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mRenderBitmap.recycle();
            this.mRenderBitmap = null;
        }
        Bitmap bitmap2 = this.mOrigin;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.mOrigin.recycle();
            this.mOrigin = null;
        }
    }

    public void renderBitmap(boolean z, ScreenRenderData screenRenderData, ScreenRenderCallback screenRenderCallback) {
        Log.d("ScreenRenderManager", "renderBitmap: start.");
        Observable.create(new ObservableOnSubscribe(screenRenderData) {
            private final /* synthetic */ ScreenRenderData f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                ScreenRenderManager.this.lambda$renderBitmap$69$ScreenRenderManager(this.f$1, observableEmitter);
            }
        }).subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer(screenRenderCallback, z) {
            private final /* synthetic */ ScreenRenderCallback f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void accept(Object obj) {
                ScreenRenderManager.this.lambda$renderBitmap$70$ScreenRenderManager(this.f$1, this.f$2, (Bitmap) obj);
            }
        });
    }

    public void setOriginLoadedListener(OnOriginLoadedListener onOriginLoadedListener) {
        this.mOriginLoadedListener = onOriginLoadedListener;
    }
}
