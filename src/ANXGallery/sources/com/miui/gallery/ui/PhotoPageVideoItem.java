package com.miui.gallery.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.gallery.R;
import com.miui.gallery.adapter.PhotoPageAdapter;
import com.miui.gallery.error.core.ErrorCode;
import com.miui.gallery.model.BaseDataItem;
import com.miui.gallery.sdk.download.DownloadType;
import com.miui.gallery.threadpool.Future;
import com.miui.gallery.threadpool.FutureHandler;
import com.miui.gallery.threadpool.ThreadManager;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.ui.PhotoPageItem;
import com.miui.gallery.ui.PhotoPageVideoItem;
import com.miui.gallery.util.BitmapUtils;
import com.miui.gallery.util.FormatUtil;
import com.miui.gallery.util.IntentUtil;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.miui.gallery.util.photoview.ItemViewInfo;
import com.miui.gallery.util.uil.CloudImageLoader;
import com.miui.gallery.widget.AntiDoubleClickListener;
import com.miui.gallery.widget.CircleStrokeProgressBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import miui.view.animation.SineEaseInOutInterpolator;

public class PhotoPageVideoItem extends PhotoPageItem {
    private AntiDoubleClickListener mAntiDoubleClickListener = new AntiDoubleClickListener() {
        public void onAntiDoubleClick(View view) {
            Log.d("PhotoPageVideoItem", "onVideoIconClick");
            if (PhotoPageVideoItem.this.mDataItem == null) {
                return;
            }
            if (!TextUtils.isEmpty(PhotoPageVideoItem.this.mDataItem.getOriginalPath())) {
                PhotoPageVideoItem.this.playVideo();
            } else {
                PhotoPageVideoItem.this.handNoFileExist();
            }
        }
    };
    /* access modifiers changed from: private */
    public OnSurfacePreparedListener mOnSurfacePreparedListener;
    /* access modifiers changed from: private */
    public OnThumbnailLoadedListener mOnThumbnailLoadedListener;
    private VideoPreviewManager mPreviewManager;
    private VideoSubtitleManager mSubtitleManager;
    private VideoThumbnailManager mThumbnailManager;
    /* access modifiers changed from: private */
    public View mVideoIcon;
    private Runnable mVideoItemRunnable;

    private static class LoadThumbnailTask {
        /* access modifiers changed from: private */
        public LoadThumbnailTaskCallback mCallback;
        private Future mFuture;

        private LoadThumbnailTask() {
        }

        static /* synthetic */ Bitmap lambda$load$21(Bitmap bitmap, int i, int i2, ThreadPool.JobContext jobContext) {
            if (jobContext.isCancelled()) {
                return null;
            }
            return BitmapUtils.createScaledBitmap(bitmap, i, i2);
        }

        public void load(Bitmap bitmap, int i, int i2, LoadThumbnailTaskCallback loadThumbnailTaskCallback) {
            Future future = this.mFuture;
            if (future != null) {
                future.cancel();
            }
            this.mCallback = loadThumbnailTaskCallback;
            this.mFuture = ThreadManager.getMiscPool().submit(new ThreadPool.Job(bitmap, i, i2) {
                private final /* synthetic */ Bitmap f$0;
                private final /* synthetic */ int f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final Object run(ThreadPool.JobContext jobContext) {
                    return PhotoPageVideoItem.LoadThumbnailTask.lambda$load$21(this.f$0, this.f$1, this.f$2, jobContext);
                }
            }, new FutureHandler<Bitmap>() {
                public void onPostExecute(Future<Bitmap> future) {
                    if (!future.isCancelled() && LoadThumbnailTask.this.mCallback != null) {
                        LoadThumbnailTask.this.mCallback.onThumbnailLoadFinish(future.get());
                    }
                }
            });
        }

        public void release() {
            Future future = this.mFuture;
            if (future != null) {
                future.cancel();
                this.mFuture = null;
            }
            this.mCallback = null;
        }
    }

    private interface LoadThumbnailTaskCallback {
        void onThumbnailLoadFinish(Bitmap bitmap);
    }

    public interface OnSurfacePreparedListener {
        void onSurfacePrepared(Surface surface);
    }

    public interface OnThumbnailLoadedListener {
        void onThumbnailLoaded(Bitmap bitmap);
    }

    protected class VideoCheckManager extends PhotoPageItem.CheckManager {
        protected TextView mVideoSmallText;

        protected VideoCheckManager() {
            super();
        }

        public void endCheck() {
            super.endCheck();
            PhotoPageVideoItem.this.mVideoIcon.setAlpha(1.0f);
            this.mVideoSmallText.setVisibility(8);
        }

        /* access modifiers changed from: protected */
        public void ensureInflated() {
            super.ensureInflated();
            this.mVideoSmallText = (TextView) PhotoPageVideoItem.this.findViewById(R.id.video_small_icon_duration);
        }

        public void refreshCheck(PhotoPageAdapter.ChoiceModeInterface... choiceModeInterfaceArr) {
            super.refreshCheck(choiceModeInterfaceArr);
            if (PhotoPageVideoItem.this.mDataItem != null) {
                this.mVideoSmallText.setVisibility(0);
                this.mVideoSmallText.setText(FormatUtil.formatVideoDuration((long) PhotoPageVideoItem.this.mDataItem.getDuration()));
                return;
            }
            this.mVideoSmallText.setVisibility(8);
        }

        public void startCheck(PhotoPageAdapter.ChoiceModeInterface... choiceModeInterfaceArr) {
            super.startCheck(choiceModeInterfaceArr);
            PhotoPageVideoItem.this.mVideoIcon.setAlpha(0.0f);
            if (PhotoPageVideoItem.this.mDataItem != null) {
                this.mVideoSmallText.setVisibility(0);
                this.mVideoSmallText.setText(FormatUtil.formatVideoDuration((long) PhotoPageVideoItem.this.mDataItem.getDuration()));
            }
        }
    }

    private class VideoDownloadManager extends PhotoPageItem.DownloadManager {
        private ProgressBar mOriginProgressBar;

        private VideoDownloadManager() {
            super();
        }

        private ProgressBar initOriginProgressBar() {
            CircleStrokeProgressBar circleStrokeProgressBar = new CircleStrokeProgressBar(PhotoPageVideoItem.this.getContext());
            circleStrokeProgressBar.setDrawablesForLevels(new int[]{R.drawable.photo_download_progress_bg_big}, new int[]{R.drawable.photo_download_progress_second_big}, (int[]) null);
            circleStrokeProgressBar.setMiddleStrokeColors(new int[]{PhotoPageVideoItem.this.getResources().getColor(R.color.download_progress_shadow_color)}, (float) PhotoPageVideoItem.this.getResources().getDimensionPixelSize(R.dimen.download_progress_shadow_radius));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            layoutParams.addRule(13);
            circleStrokeProgressBar.setVisibility(8);
            PhotoPageVideoItem.this.addView(circleStrokeProgressBar, layoutParams);
            return circleStrokeProgressBar;
        }

        /* access modifiers changed from: protected */
        public void adjustProgressBarLocation(boolean z, RectF rectF, boolean z2) {
            if (!CloudImageLoader.getInstance().isOrigin(getCurDownloadType())) {
                super.adjustProgressBarLocation(z, rectF, z2);
            }
        }

        /* access modifiers changed from: protected */
        public void doOnDownloaded(DownloadType downloadType, String str) {
            super.doOnDownloaded(downloadType, str);
            if (CloudImageLoader.getInstance().isOrigin(downloadType) && PhotoPageVideoItem.this.canPlay()) {
                PhotoPageVideoItem.this.playVideo();
            }
        }

        /* access modifiers changed from: protected */
        public void doOnProgressVisibilityChanged(boolean z) {
            super.doOnProgressVisibilityChanged(z);
            if (CloudImageLoader.getInstance().isOrigin(getCurDownloadType())) {
                if (!z && PhotoPageVideoItem.this.canPlay()) {
                    PhotoPageVideoItem.this.setVideoItemVisibleDelay(!z);
                } else {
                    PhotoPageVideoItem.this.setVideoItemVisible(!z);
                }
                if (z) {
                    PhotoPageVideoItem.this.mEmptyView.setVisible(false);
                }
            }
        }

        /* access modifiers changed from: protected */
        public boolean filterError(ErrorCode errorCode) {
            return errorCode == ErrorCode.DECODE_ERROR && PhotoPageVideoItem.this.mPhotoView.getDrawable() != null;
        }

        /* access modifiers changed from: protected */
        public CharSequence getErrorTip() {
            if (!CloudImageLoader.getInstance().isOrigin(getCurDownloadType())) {
                return super.getErrorTip();
            }
            return PhotoPageVideoItem.this.getResources().getString(R.string.download_error_format, new Object[]{PhotoPageVideoItem.this.getResources().getString(R.string.origin_video_name)});
        }

        /* access modifiers changed from: protected */
        public ProgressBar getProgressBar() {
            if (!CloudImageLoader.getInstance().isOrigin(getCurDownloadType())) {
                return super.getProgressBar();
            }
            if (this.mOriginProgressBar == null) {
                this.mOriginProgressBar = initOriginProgressBar();
            }
            return this.mOriginProgressBar;
        }

        public boolean isProgressVisible() {
            ProgressBar progressBar = this.mOriginProgressBar;
            return progressBar != null && progressBar.getVisibility() == 0;
        }

        /* access modifiers changed from: protected */
        public boolean needShowDownloadView() {
            return CloudImageLoader.getInstance().isOrigin(getCurDownloadType()) ? !PhotoPageVideoItem.this.isInActionMode() && !isDrawableDisplayInside() : super.needShowDownloadView();
        }
    }

    class VideoItemRunnable implements Runnable {
        private final boolean visible;

        public VideoItemRunnable(boolean z) {
            this.visible = z;
        }

        public void run() {
            PhotoPageVideoItem.this.doSetVideoItemVisible(this.visible);
        }
    }

    private class VideoPreviewManager {
        private Bitmap mBitmap;
        private Runnable mHideRunnable;
        private boolean mIsPreviewUpdated;
        private boolean mIsShowPreview;
        private RelativeLayout.LayoutParams mLayoutParams;
        private boolean mPreviewStarted;
        /* access modifiers changed from: private */
        public Surface mSurface;
        private TextureView.SurfaceTextureListener mSurfaceTextureListener;
        private TextureView mTextureView;
        private RectF mVideoRect;

        private VideoPreviewManager() {
            this.mHideRunnable = new Runnable() {
                public final void run() {
                    PhotoPageVideoItem.VideoPreviewManager.this.lambda$new$19$PhotoPageVideoItem$VideoPreviewManager();
                }
            };
            this.mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    Surface unused = VideoPreviewManager.this.mSurface = new Surface(surfaceTexture);
                    if (PhotoPageVideoItem.this.mOnSurfacePreparedListener != null) {
                        PhotoPageVideoItem.this.mOnSurfacePreparedListener.onSurfacePrepared(VideoPreviewManager.this.mSurface);
                    }
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    Surface unused = VideoPreviewManager.this.mSurface = null;
                    return true;
                }

                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                }

                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }
            };
        }

        private void addTextureView() {
            if (this.mTextureView == null) {
                Log.d("VideoItemPreviewManager", "addTextureView");
                RectF videoRect = getVideoRect();
                if (videoRect != null) {
                    if (this.mLayoutParams == null) {
                        this.mLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
                        this.mLayoutParams.addRule(13);
                    }
                    this.mLayoutParams.width = (int) videoRect.width();
                    this.mLayoutParams.height = (int) videoRect.height();
                    this.mTextureView = new TextureView(PhotoPageVideoItem.this.getContext());
                    this.mTextureView.setSurfaceTextureListener(this.mSurfaceTextureListener);
                    PhotoPageVideoItem.this.addView(this.mTextureView, 0, this.mLayoutParams);
                }
            }
        }

        private RectF getVideoRect() {
            Drawable drawable = PhotoPageVideoItem.this.mPhotoView.getDrawable();
            if (drawable == null) {
                return null;
            }
            if (this.mVideoRect == null) {
                this.mVideoRect = new RectF();
            }
            this.mVideoRect.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            PhotoPageVideoItem.this.mPhotoView.getDisplayMatrix().mapRect(this.mVideoRect);
            return this.mVideoRect;
        }

        private void hideTextureView() {
            if (this.mSurface != null) {
                Log.d("VideoItemPreviewManager", "hideTextureView");
                this.mTextureView.removeCallbacks(this.mHideRunnable);
                PhotoPageVideoItem.this.mPhotoView.setVisibility(0);
                if (this.mIsPreviewUpdated) {
                    this.mIsPreviewUpdated = false;
                    if (this.mBitmap == null) {
                        this.mBitmap = Bitmap.createBitmap(PhotoPageVideoItem.this.getResources().getDisplayMetrics(), this.mTextureView.getWidth(), this.mTextureView.getHeight(), Bitmap.Config.ARGB_8888);
                    }
                    this.mTextureView.getBitmap(this.mBitmap);
                    this.mIsShowPreview = true;
                    PhotoPageVideoItem.this.mPhotoView.setImageBitmap(this.mBitmap);
                }
            }
        }

        private void hideTextureViewDelay() {
            TextureView textureView = this.mTextureView;
            if (textureView != null) {
                textureView.removeCallbacks(this.mHideRunnable);
                this.mTextureView.postDelayed(this.mHideRunnable, 100);
            }
        }

        /* access modifiers changed from: private */
        public void release() {
            this.mIsShowPreview = false;
            removeTextureView("release");
        }

        private void removeTextureView(String str) {
            if (this.mTextureView != null) {
                Log.d("VideoItemPreviewManager", "removeTextureView %s", (Object) str);
                hideTextureView();
                if (this.mTextureView != null) {
                    PhotoPageVideoItem.this.mPhotoView.setVisibility(0);
                    this.mTextureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
                    this.mTextureView.removeCallbacks(this.mHideRunnable);
                    PhotoPageVideoItem.this.removeView(this.mTextureView);
                    this.mPreviewStarted = false;
                    this.mIsPreviewUpdated = false;
                    this.mSurface = null;
                    this.mTextureView = null;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Surface getSurface() {
            addTextureView();
            return this.mSurface;
        }

        /* access modifiers changed from: package-private */
        public boolean isShowPreview() {
            return this.mIsShowPreview;
        }

        public /* synthetic */ void lambda$new$19$PhotoPageVideoItem$VideoPreviewManager() {
            Log.w("VideoItemPreviewManager", "hideTextureViewDelay");
            hideTextureView();
        }

        /* access modifiers changed from: package-private */
        public void onConfigurationChanged() {
            removeTextureView("configChanged");
        }

        /* access modifiers changed from: package-private */
        public void onExit() {
            removeTextureView("exit");
        }

        /* access modifiers changed from: package-private */
        public void onMatrixChanged() {
            RectF videoRect;
            if (this.mTextureView != null && (videoRect = getVideoRect()) != null) {
                if (videoRect.width() != ((float) this.mTextureView.getWidth()) || videoRect.height() != ((float) this.mTextureView.getHeight())) {
                    removeTextureView("matrixChanged");
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onPreviewStart() {
            this.mPreviewStarted = true;
            TextureView textureView = this.mTextureView;
            if (textureView != null) {
                textureView.removeCallbacks(this.mHideRunnable);
            }
        }

        /* access modifiers changed from: package-private */
        public void onPreviewStop() {
            this.mPreviewStarted = false;
            hideTextureViewDelay();
        }

        /* access modifiers changed from: package-private */
        public void onPreviewUpdate(boolean z) {
            if (this.mSurface != null) {
                Log.d("VideoItemPreviewManager", "onPreviewUpdate %b", (Object) Boolean.valueOf(z));
                this.mIsPreviewUpdated = true;
                if (!z || this.mPreviewStarted) {
                    PhotoPageVideoItem.this.mPhotoView.setVisibility(8);
                } else {
                    hideTextureView();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onUnSelected(boolean z) {
            removeTextureView("unSelected");
            if (!z && this.mIsShowPreview) {
                this.mIsShowPreview = false;
                if (this.mBitmap != null) {
                    Log.d("VideoItemPreviewManager", "refresh");
                    PhotoPageVideoItem.this.refreshItem();
                    this.mBitmap = null;
                }
            }
        }
    }

    private class VideoSubtitleManager {
        private RectF mBaseRect;
        private int mLayoutHeight;
        private RelativeLayout.LayoutParams mLayoutParams;
        private TextView mTextView;
        private RectF mVideoRect;

        private VideoSubtitleManager() {
        }

        private void addTextView() {
            RectF baseVideoRect;
            if (this.mTextView == null && (baseVideoRect = getBaseVideoRect()) != null) {
                this.mTextView = new TextView(PhotoPageVideoItem.this.getContext());
                this.mTextView.setShadowLayer(0.5f, 1.0f, 1.0f, Integer.MIN_VALUE);
                this.mTextView.setGravity(1);
                this.mTextView.setMaxLines(2);
                this.mTextView.setEllipsize(TextUtils.TruncateAt.END);
                this.mTextView.setTextColor(-1);
                updateTextViewStyle(baseVideoRect);
                updateTextViewLayout(baseVideoRect);
                PhotoPageVideoItem.this.addView(this.mTextView, this.mLayoutParams);
                if (PhotoPageVideoItem.this.mCheckManager != null) {
                    onActionModeChanged(PhotoPageVideoItem.this.mCheckManager.inAction());
                }
            }
        }

        private RectF getBaseVideoRect() {
            Drawable drawable = PhotoPageVideoItem.this.mPhotoView.getDrawable();
            if (drawable == null) {
                return null;
            }
            if (this.mVideoRect == null) {
                this.mVideoRect = new RectF();
            }
            this.mVideoRect.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            PhotoPageVideoItem.this.mPhotoView.getBaseMatrix().mapRect(this.mVideoRect);
            return this.mVideoRect;
        }

        private void removeTextView(String str) {
            if (this.mTextView != null) {
                Log.d("VideoSubtitleManager", "removeTextView %s", (Object) str);
                PhotoPageVideoItem.this.removeView(this.mTextView);
                this.mTextView = null;
            }
        }

        private void updateTextViewLayout(RectF rectF) {
            if (this.mLayoutParams == null) {
                this.mLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
                this.mLayoutParams.addRule(14);
                this.mLayoutParams.addRule(12);
            }
            this.mLayoutHeight = PhotoPageVideoItem.this.getHeight();
            this.mLayoutParams.setMargins(0, 0, 0, (int) (((float) this.mLayoutHeight) - rectF.bottom));
            this.mTextView.setMaxWidth((int) rectF.width());
        }

        private void updateTextViewStyle(RectF rectF) {
            int i;
            float f;
            float width = rectF.width();
            float height = rectF.height();
            int i2 = ((height / width) > 1.7f ? 1 : ((height / width) == 1.7f ? 0 : -1));
            int i3 = i2 >= 0 ? 40 : 36;
            int i4 = (int) ((i2 >= 0 ? 0.20833333f : 0.23888889f) * width);
            if (PhotoPageVideoItem.this.getResources().getConfiguration().orientation == 2) {
                i = (int) ((i2 >= 0 ? 0.050925925f : 0.06388889f) * height);
                f = 2.0f;
            } else {
                i = (int) ((i2 >= 0 ? 0.06574074f : 0.02962963f) * width);
                f = i2 >= 0 ? 9.0f : 3.0f;
            }
            this.mTextView.setPadding(i4, 0, i4, i);
            this.mTextView.setTextSize(0, (width * ((float) i3)) / 1080.0f);
            this.mTextView.setLineSpacing(f, 1.0f);
            this.mTextView.setLetterSpacing(0.0375f);
        }

        /* access modifiers changed from: package-private */
        public void onActionModeChanged(boolean z) {
            TextView textView = this.mTextView;
            if (textView != null) {
                textView.setVisibility(z ? 4 : 0);
            }
        }

        /* access modifiers changed from: package-private */
        public void onExit() {
            removeTextView("onExit");
        }

        /* access modifiers changed from: package-private */
        public void onMatrixChanged(RectF rectF) {
            RectF baseVideoRect;
            TextView textView = this.mTextView;
            if (textView != null && textView.getVisibility() == 0 && (baseVideoRect = getBaseVideoRect()) != null) {
                if (!baseVideoRect.equals(this.mBaseRect)) {
                    if (this.mBaseRect == null) {
                        this.mBaseRect = new RectF();
                    }
                    this.mBaseRect.set(baseVideoRect);
                    updateTextViewStyle(baseVideoRect);
                    updateTextViewLayout(baseVideoRect);
                    this.mTextView.requestLayout();
                } else if (this.mTextView.getWidth() != 0 && rectF != null) {
                    float scale = PhotoPageVideoItem.this.mPhotoView.getScale();
                    int width = (int) (((rectF.width() - ((float) this.mTextView.getWidth())) / 2.0f) + rectF.left);
                    int height = (int) ((rectF.height() - ((float) ((int) (((float) this.mTextView.getHeight()) * scale)))) + rectF.top);
                    TextView textView2 = this.mTextView;
                    textView2.setTranslationX((float) (width - textView2.getLeft()));
                    TextView textView3 = this.mTextView;
                    textView3.setTranslationY((float) (height - textView3.getTop()));
                    this.mTextView.setScaleX(scale);
                    this.mTextView.setScaleY(scale);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void onUnSelected(boolean z) {
            if (!z) {
                removeTextView("onUnSelected");
            }
        }

        /* access modifiers changed from: package-private */
        public void release() {
            removeTextView("release");
        }

        /* access modifiers changed from: package-private */
        public void update(String str, Typeface typeface) {
            addTextView();
            TextView textView = this.mTextView;
            if (textView != null) {
                if (typeface != null) {
                    textView.setTypeface(typeface);
                }
                this.mTextView.setText(str);
            }
        }
    }

    private class VideoThumbnailManager {
        private LoadThumbnailTask mLoadThumbnailTask;
        private int mThumbItemHeight;
        private int mThumbItemWidth;
        private Bitmap mVideoThumbnail;

        VideoThumbnailManager() {
            this.mThumbItemWidth = PhotoPageVideoItem.this.getResources().getDimensionPixelSize(R.dimen.video_frame_thumb_width);
            this.mThumbItemHeight = PhotoPageVideoItem.this.getResources().getDimensionPixelSize(R.dimen.video_frame_thumb_height);
        }

        private void cancelLoadThumbnail() {
            LoadThumbnailTask loadThumbnailTask = this.mLoadThumbnailTask;
            if (loadThumbnailTask != null) {
                loadThumbnailTask.release();
                this.mLoadThumbnailTask = null;
            }
        }

        private void startLoadThumbnail() {
            if (this.mVideoThumbnail == null && PhotoPageVideoItem.this.mPhotoView != null) {
                Drawable drawable = PhotoPageVideoItem.this.mPhotoView.getDrawable();
                Bitmap bitmap = drawable instanceof BitmapDrawable ? ((BitmapDrawable) drawable).getBitmap() : null;
                if (bitmap != null) {
                    if (this.mLoadThumbnailTask == null) {
                        this.mLoadThumbnailTask = new LoadThumbnailTask();
                    }
                    this.mLoadThumbnailTask.load(bitmap, this.mThumbItemWidth, this.mThumbItemHeight, new LoadThumbnailTaskCallback() {
                        public final void onThumbnailLoadFinish(Bitmap bitmap) {
                            PhotoPageVideoItem.VideoThumbnailManager.this.lambda$startLoadThumbnail$20$PhotoPageVideoItem$VideoThumbnailManager(bitmap);
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Bitmap getVideoThumbnail() {
            return this.mVideoThumbnail;
        }

        public /* synthetic */ void lambda$startLoadThumbnail$20$PhotoPageVideoItem$VideoThumbnailManager(Bitmap bitmap) {
            this.mVideoThumbnail = bitmap;
            if (PhotoPageVideoItem.this.mOnThumbnailLoadedListener != null) {
                PhotoPageVideoItem.this.mOnThumbnailLoadedListener.onThumbnailLoaded(bitmap);
            }
        }

        /* access modifiers changed from: package-private */
        public void onImageLoadFinish() {
            startLoadThumbnail();
        }

        /* access modifiers changed from: package-private */
        public void onItemUpdated() {
            cancelLoadThumbnail();
            this.mVideoThumbnail = null;
        }

        /* access modifiers changed from: package-private */
        public void release() {
            cancelLoadThumbnail();
            this.mVideoThumbnail = null;
        }
    }

    public PhotoPageVideoItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    public boolean canPlay() {
        return this.mDataItem != null && !TextUtils.isEmpty(this.mDataItem.getOriginalPath()) && isPagerSelected() && hasWindowFocus();
    }

    private void cancelVideoItemRunnable() {
        if (this.mVideoItemRunnable != null) {
            ThreadManager.getMainHandler().removeCallbacks(this.mVideoItemRunnable);
        }
    }

    /* access modifiers changed from: private */
    public void doSetVideoItemVisible(boolean z) {
        if (!z || !needShowPlayIcon()) {
            this.mVideoIcon.setVisibility(8);
        } else {
            this.mVideoIcon.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void handNoFileExist() {
        if (this.mDataItem == null || this.mDataItem.getDownloadUri() == null) {
            ToastUtils.makeText(getContext(), (int) R.string.video_not_exist);
        } else {
            downloadOrigin();
        }
    }

    private boolean needShowPlayIcon() {
        return !isAnimExiting() && (this.mDownloadManager == null || !((VideoDownloadManager) this.mDownloadManager).isProgressVisible()) && (this.mPhotoView.getDrawable() != null || (this.mDataItem != null && !TextUtils.isEmpty(this.mDataItem.getOriginalPath())));
    }

    /* access modifiers changed from: private */
    public void playVideo() {
        Log.d("PhotoPageVideoItem", "playVideo");
        if (this.mPhotoPageInteractionListener != null) {
            this.mPhotoPageInteractionListener.playVideo(this.mDataItem);
        } else {
            Log.w("PhotoPageVideoItem", "playVideo callback is null");
        }
    }

    private void setVideoIconVisibleWithAnim(boolean z) {
        this.mVideoIcon.animate().alpha(z ? 1.0f : 0.0f).setDuration(0).setInterpolator(new SineEaseInOutInterpolator()).start();
    }

    /* access modifiers changed from: private */
    public void setVideoItemVisible(boolean z) {
        cancelVideoItemRunnable();
        doSetVideoItemVisible(z);
    }

    /* access modifiers changed from: private */
    public void setVideoItemVisibleDelay(boolean z) {
        cancelVideoItemRunnable();
        this.mVideoItemRunnable = new VideoItemRunnable(z);
        ThreadManager.getMainHandler().postDelayed(this.mVideoItemRunnable, 200);
    }

    public void animEnter(ItemViewInfo itemViewInfo, final PhotoPageItem.TransitionListener transitionListener) {
        AnonymousClass1 r0 = new PhotoPageItem.TransitionListener() {
            public void onTransitEnd() {
                PhotoPageVideoItem.this.setVideoItemVisible(true);
                transitionListener.onTransitEnd();
            }
        };
        setVideoItemVisible(false);
        super.animEnter(itemViewInfo, r0);
    }

    public void animExit(ItemViewInfo itemViewInfo, PhotoPageItem.TransitionListener transitionListener) {
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.onExit();
        }
        VideoSubtitleManager videoSubtitleManager = this.mSubtitleManager;
        if (videoSubtitleManager != null) {
            videoSubtitleManager.onExit();
        }
        setVideoItemVisible(false);
        super.animExit(itemViewInfo, transitionListener);
    }

    /* access modifiers changed from: protected */
    public PhotoPageItem.CheckManager createCheckManager() {
        return new VideoCheckManager();
    }

    /* access modifiers changed from: protected */
    public PhotoPageItem.DownloadManager createDownloadManager() {
        return new VideoDownloadManager();
    }

    /* access modifiers changed from: protected */
    public void displayImage(String str, DisplayImageOptions displayImageOptions, ImageSize imageSize, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener) {
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager == null || !videoPreviewManager.isShowPreview()) {
            super.displayImage(str, displayImageOptions, imageSize, imageLoadingListener, imageLoadingProgressListener);
        }
    }

    /* access modifiers changed from: protected */
    public void doOnMatrixChanged(RectF rectF) {
        super.doOnMatrixChanged(rectF);
        float[] fArr = {(float) (getWidth() / 2), (float) (getHeight() / 2)};
        this.mPhotoView.getSuppMatrix().mapPoints(fArr);
        this.mVideoIcon.setTranslationX(fArr[0] - ((float) (getWidth() / 2)));
        this.mVideoIcon.setTranslationY(fArr[1] - ((float) (getHeight() / 2)));
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.onMatrixChanged();
        }
        VideoSubtitleManager videoSubtitleManager = this.mSubtitleManager;
        if (videoSubtitleManager != null) {
            videoSubtitleManager.onMatrixChanged(rectF);
        }
    }

    /* access modifiers changed from: protected */
    public void doOnSelected(boolean z, boolean z2, boolean z3) {
        super.doOnSelected(z, z2, z3);
    }

    /* access modifiers changed from: protected */
    public void doOnUnSelected(boolean z, int i) {
        super.doOnUnSelected(z, i);
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.onUnSelected(z);
        }
        VideoSubtitleManager videoSubtitleManager = this.mSubtitleManager;
        if (videoSubtitleManager != null) {
            videoSubtitleManager.onUnSelected(z);
        }
    }

    public Surface getPreviewSurface() {
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            return videoPreviewManager.getSurface();
        }
        return null;
    }

    public Bitmap getVideoThumbnail() {
        VideoThumbnailManager videoThumbnailManager = this.mThumbnailManager;
        if (videoThumbnailManager != null) {
            return videoThumbnailManager.getVideoThumbnail();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onActionModeChanged(boolean z) {
        super.onActionModeChanged(z);
        VideoSubtitleManager videoSubtitleManager = this.mSubtitleManager;
        if (videoSubtitleManager != null) {
            videoSubtitleManager.onActionModeChanged(z);
        }
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.onConfigurationChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        cancelVideoItemRunnable();
        super.onDetachedFromWindow();
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.release();
        }
        this.mOnSurfacePreparedListener = null;
        VideoThumbnailManager videoThumbnailManager = this.mThumbnailManager;
        if (videoThumbnailManager != null) {
            videoThumbnailManager.release();
        }
        this.mOnThumbnailLoadedListener = null;
        VideoSubtitleManager videoSubtitleManager = this.mSubtitleManager;
        if (videoSubtitleManager != null) {
            videoSubtitleManager.release();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mVideoIcon = findViewById(R.id.video_icon);
        this.mVideoIcon.setOnClickListener(this.mAntiDoubleClickListener);
        if (IntentUtil.isSupportNewVideoPlayer()) {
            this.mPreviewManager = new VideoPreviewManager();
            this.mThumbnailManager = new VideoThumbnailManager();
            if (IntentUtil.isVideoPlayerSupportEditSubtitle()) {
                this.mSubtitleManager = new VideoSubtitleManager();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onImageLoadFinish(ErrorCode errorCode) {
        super.onImageLoadFinish(errorCode);
        setVideoItemVisible(this.mPhotoView.getDrawable() != null);
        VideoThumbnailManager videoThumbnailManager = this.mThumbnailManager;
        if (videoThumbnailManager != null) {
            videoThumbnailManager.onImageLoadFinish();
        }
    }

    public void onPreviewStart() {
        setVideoIconVisibleWithAnim(false);
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.onPreviewStart();
        }
    }

    public void onPreviewStop() {
        setVideoIconVisibleWithAnim(true);
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.onPreviewStop();
        }
    }

    public void onPreviewUpdate(boolean z) {
        VideoPreviewManager videoPreviewManager = this.mPreviewManager;
        if (videoPreviewManager != null) {
            videoPreviewManager.onPreviewUpdate(z);
        }
    }

    public void onSlipping(float f) {
        super.onSlipping(f);
        this.mVideoIcon.animate().cancel();
        this.mVideoIcon.setAlpha(1.0f - f);
    }

    public void setOnSurfacePreparedListener(OnSurfacePreparedListener onSurfacePreparedListener) {
        this.mOnSurfacePreparedListener = onSurfacePreparedListener;
    }

    public void setOnThumbnailLoadedListener(OnThumbnailLoadedListener onThumbnailLoadedListener) {
        this.mOnThumbnailLoadedListener = onThumbnailLoadedListener;
    }

    public void swapItem(BaseDataItem baseDataItem) {
        if (baseDataItem == null || !baseDataItem.equals(this.mDataItem)) {
            VideoPreviewManager videoPreviewManager = this.mPreviewManager;
            if (videoPreviewManager != null) {
                videoPreviewManager.onUnSelected(false);
            }
            VideoThumbnailManager videoThumbnailManager = this.mThumbnailManager;
            if (videoThumbnailManager != null) {
                videoThumbnailManager.onItemUpdated();
            }
            VideoSubtitleManager videoSubtitleManager = this.mSubtitleManager;
            if (videoSubtitleManager != null) {
                videoSubtitleManager.onUnSelected(false);
            }
        }
        super.swapItem(baseDataItem);
    }

    public void updateSubtitleView(String str, Typeface typeface) {
        VideoSubtitleManager videoSubtitleManager = this.mSubtitleManager;
        if (videoSubtitleManager != null) {
            videoSubtitleManager.update(str, typeface);
        }
    }
}
