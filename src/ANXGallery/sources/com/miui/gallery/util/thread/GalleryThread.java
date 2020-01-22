package com.miui.gallery.util.thread;

public class GalleryThread extends Thread {
    public GalleryThread(Runnable runnable, String str) {
        super(runnable, str);
    }
}
