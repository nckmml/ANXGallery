package com.miui.gallery.video.editor;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.util.Log;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageLoadingProcess implements ImageLoadingListener {
    private int mColor;
    private ImageView mIcon;

    public ImageLoadingProcess(ImageView imageView) {
        this.mIcon = imageView;
    }

    public void onLoadingCancelled(String str, View view) {
        Log.d("ImageLoadingProcess", "load img is cancel.");
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        Log.d("ImageLoadingProcess", "load img is complete.");
        ImageView imageView = this.mIcon;
        if (imageView != null) {
            imageView.setBackground((Drawable) null);
        }
    }

    public void onLoadingFailed(String str, View view, FailReason failReason) {
        Log.w("ImageLoadingProcess", "load img is fail.");
    }

    public void onLoadingStarted(String str, View view) {
        if (this.mColor != 0) {
            this.mIcon.setBackground(GalleryApp.sGetAndroidContext().getResources().getDrawable(this.mColor));
        }
    }

    public void setBgColor(int i) {
        this.mColor = i;
    }
}
