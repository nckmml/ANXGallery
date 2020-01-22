package com.miui.gallery.util.thread;

public class RxGalleryExecutors {
    private static volatile RxGalleryExecutors INSTANCE;
    private UIThreadExecutor mUiThreadExecutor = new UIThreadExecutor();
    private UserThreadExecutor mUserThreadExecutor = new UserThreadExecutor();

    private RxGalleryExecutors() {
    }

    public static RxGalleryExecutors getInstance() {
        if (INSTANCE == null) {
            synchronized (RxGalleryExecutors.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RxGalleryExecutors();
                }
            }
        }
        return INSTANCE;
    }

    public UIThreadExecutor getUiThreadExecutor() {
        return this.mUiThreadExecutor;
    }

    public UserThreadExecutor getUserThreadExecutor() {
        return this.mUserThreadExecutor;
    }
}
