package com.miui.gallery.video.editor.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.video.editor.util.ToolsUtil;
import java.lang.ref.WeakReference;

public class SmartVideoGuideHelper {
    private static volatile boolean sHasGuided = true;
    private SmartVideoGuideHandleThread mGuideHandleThread;
    /* access modifiers changed from: private */
    public SmartVideoGuideListener mGuideListener;

    static class SmartVideoGuideHandleThread extends HandlerThread implements Handler.Callback {
        private final WeakReference<SmartVideoGuideHelper> mWeakReference;
        private Handler mWorkHandler;

        public SmartVideoGuideHandleThread(SmartVideoGuideHelper smartVideoGuideHelper) {
            super("SmartEffectAttentionThread");
            this.mWeakReference = new WeakReference<>(smartVideoGuideHelper);
            start();
        }

        private boolean isSupportSmartVideo(int i) {
            return i >= 100 && i <= 120;
        }

        public void destroyThread() {
            this.mWorkHandler.removeCallbacksAndMessages((Object) null);
            quit();
        }

        public void handleHighFrameRate(String str) {
            if (this.mWorkHandler == null) {
                this.mWorkHandler = new Handler(getLooper(), this);
            }
            Message obtain = Message.obtain(this.mWorkHandler);
            obtain.obj = str;
            obtain.what = 0;
            this.mWorkHandler.removeCallbacksAndMessages((Object) null);
            this.mWorkHandler.sendMessage(obtain);
        }

        public boolean handleMessage(Message message) {
            SmartVideoGuideHelper smartVideoGuideHelper;
            if (message.what != 0) {
                return false;
            }
            String str = (String) message.obj;
            int videoFrameRate = ToolsUtil.getVideoFrameRate(str);
            if (TextUtils.isEmpty(str) || !isSupportSmartVideo(videoFrameRate) || (smartVideoGuideHelper = (SmartVideoGuideHelper) this.mWeakReference.get()) == null) {
                return false;
            }
            smartVideoGuideHelper.tryShowGuideView(str);
            return false;
        }
    }

    public interface SmartVideoGuideListener {
        void showGuideView(String str);
    }

    public static boolean hasGuided() {
        return sHasGuided;
    }

    public static void init() {
        if (SmartVideoJudgeManager.isAvailable()) {
            sHasGuided = GalleryPreferences.SmartVideoGuide.hasGuided();
        }
    }

    public static void setHasGuided(boolean z) {
        sHasGuided = z;
        GalleryPreferences.SmartVideoGuide.setSmartVideoGuided(z);
    }

    /* access modifiers changed from: private */
    public void tryShowGuideView(final String str) {
        ThreadManager.runOnMainThread(new Runnable() {
            public void run() {
                if (SmartVideoGuideHelper.this.mGuideListener != null) {
                    SmartVideoGuideHelper.this.mGuideListener.showGuideView(str);
                }
            }
        });
    }

    public void handleHighFrameRate(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (this.mGuideHandleThread == null) {
                this.mGuideHandleThread = new SmartVideoGuideHandleThread(this);
            }
            this.mGuideHandleThread.handleHighFrameRate(str);
        }
    }

    public void release() {
        if (this.mGuideListener != null) {
            this.mGuideListener = null;
        }
        SmartVideoGuideHandleThread smartVideoGuideHandleThread = this.mGuideHandleThread;
        if (smartVideoGuideHandleThread != null) {
            smartVideoGuideHandleThread.destroyThread();
        }
    }

    public void setSmartVideoGuideListener(SmartVideoGuideListener smartVideoGuideListener) {
        this.mGuideListener = smartVideoGuideListener;
    }
}
