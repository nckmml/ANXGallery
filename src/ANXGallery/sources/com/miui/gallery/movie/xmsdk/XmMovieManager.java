package com.miui.gallery.movie.xmsdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import com.miui.gallery.movie.MovieConfig;
import com.miui.gallery.movie.core.MovieManager;
import com.miui.gallery.movie.entity.AudioResource;
import com.miui.gallery.movie.entity.ImageEntity;
import com.miui.gallery.movie.entity.MovieAspectRatio;
import com.miui.gallery.movie.entity.MovieInfo;
import com.miui.gallery.movie.entity.TemplateResource;
import com.miui.gallery.movie.ui.factory.TemplateFactory;
import com.miui.gallery.movie.ui.view.IMultiThumbnailSequenceView;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.miui.gallery.util.BaseBuildUtil;
import com.miui.gallery.util.BaseFileUtils;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ScreenUtils;
import com.miui.gallery.xmstreaming.XmsContext;
import com.miui.gallery.xmstreaming.XmsMultiThumbnailSequenceView;
import com.miui.gallery.xmstreaming.XmsThemeHelper;
import com.miui.gallery.xmstreaming.XmsTimeline;
import com.miui.settings.Settings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class XmMovieManager extends MovieManager<ArrayList<XmsMultiThumbnailSequenceView.ThumbnailSequenceDesc>> implements XmsContext.PlaybackCallback, XmsContext.StreamingEngineCallback {
    /* access modifiers changed from: private */
    public MovieManager.EncodeStateInterface mExportListener;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (!XmMovieManager.this.isReadyForSwitch()) {
                XmMovieManager.this.mHandler.sendEmptyMessageDelayed(0, 10);
            } else {
                XmMovieManager.this.resetImage();
            }
        }
    };
    private boolean mIsPaused;
    private Object mLock = new Object();
    private MovieInfo mMovieInfo;
    private int mMovieState;
    private CopyOnWriteArrayList<MovieManager.StateChangeListener> mStateChangeListeners;
    /* access modifiers changed from: private */
    public XmsContext mStreamingContext;
    private XmsThemeHelper mThemeHelper = new XmsThemeHelper();
    /* access modifiers changed from: private */
    public XmsTimeline mTimeline;

    private int getStateFromXmEngine(int i) {
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                i2 = 3;
                if (i != 3) {
                    return -500;
                }
            }
        }
        return i2;
    }

    private void initTimeLine(Context context) {
        if (this.mStreamingContext == null) {
            Log.e("XmMovieManager", "streamingContext is null!");
            return;
        }
        int screenWidth = ScreenUtils.getScreenWidth();
        Activity activity = (Activity) context;
        float f = (float) screenWidth;
        int fullScreenHeight = ScreenUtils.getFullScreenHeight(activity);
        Log.d("XmMovieManager", "initTimeLine in size: %d:%d", Integer.valueOf(fullScreenHeight), Integer.valueOf(screenWidth));
        this.mTimeline = this.mStreamingContext.createTimeline(screenWidth, fullScreenHeight);
        this.mTimeline.setPreferWidth(screenWidth);
        this.mTimeline.setPreferHeight((int) (f * MovieAspectRatio.getFitRatio((((float) ScreenUtils.getFullScreenHeight(activity)) * 1.0f) / f).getRatio()));
        this.mStreamingContext.setPlaybackCallback(this);
        this.mStreamingContext.setStreamingEngineCallback(this);
        int i = 0;
        if (BaseBuildUtil.isInternational()) {
            i = Settings.getRegion().endsWith("IN") ? 2 : 1;
        }
        if (MovieConfig.sTestLocationType >= 0) {
            i = MovieConfig.sTestLocationType;
        }
        this.mThemeHelper.initHelper(context, this.mTimeline, i);
    }

    /* access modifiers changed from: private */
    public void resetImage() {
        synchronized (this.mLock) {
            this.mThemeHelper.clearClip();
            for (ImageEntity imageEntity : this.mMovieInfo.imageList) {
                this.mThemeHelper.appendClip(imageEntity.image);
            }
            this.mThemeHelper.reBuildTimeLine();
            this.mStreamingContext.applyTimeline(this.mTimeline);
        }
    }

    public void addImage(List<ImageEntity> list) {
        resetImage();
    }

    public void addStateChangeListener(MovieManager.StateChangeListener stateChangeListener) {
        if (stateChangeListener != null) {
            if (this.mStateChangeListeners == null) {
                this.mStateChangeListeners = new CopyOnWriteArrayList<>();
            }
            if (!this.mStateChangeListeners.contains(stateChangeListener)) {
                this.mStateChangeListeners.add(stateChangeListener);
            }
        }
    }

    public void cancelExport() {
        synchronized (this.mLock) {
            this.mStreamingContext.cancelCompile(this.mTimeline.getInternalObject());
        }
    }

    public IMultiThumbnailSequenceView createThumbnailSequenceView(Context context) {
        return new XmsMultiThumbnailSequenceViewWrapper(context);
    }

    public void destroy() {
        super.destroy();
        Log.d("XmMovieManager", "destroy");
        if (this.mIsPaused) {
            this.mStreamingContext.release();
            this.mExportListener = null;
        }
    }

    public void export(String str, MovieManager.EncodeStateInterface encodeStateInterface) {
        synchronized (this.mLock) {
            this.mExportListener = encodeStateInterface;
            this.mExportListener.onEncodeStart();
            this.mStreamingContext.compileTimeline(this.mTimeline, str, new XmsContext.CompileCallback() {
                public void onCompileFailed(boolean z) {
                    if (XmMovieManager.this.mExportListener != null) {
                        XmMovieManager.this.mExportListener.onEncodeEnd(false, z, 0);
                    }
                }

                public void onCompileFinished() {
                    if (XmMovieManager.this.mExportListener != null) {
                        XmMovieManager.this.mExportListener.onEncodeEnd(true, false, 0);
                    }
                }

                public void onCompileProgress(int i) {
                    if (XmMovieManager.this.mExportListener != null) {
                        XmMovieManager.this.mExportListener.onEncodeProgress(i);
                    }
                }
            });
        }
    }

    public ArrayList<XmsMultiThumbnailSequenceView.ThumbnailSequenceDesc> getThumbnailImages() {
        ArrayList<XmsMultiThumbnailSequenceView.ThumbnailSequenceDesc> thumbnailImages;
        synchronized (this.mLock) {
            thumbnailImages = this.mThemeHelper.getThumbnailImages();
        }
        return thumbnailImages;
    }

    public int getTotalTime() {
        return this.mThemeHelper.getDuration();
    }

    public void init(Context context) {
        this.mStreamingContext = XmsContext.getInstance();
        this.mStreamingContext.initPlayer(true);
        TextureView textureView = new TextureView(context);
        this.mDisplayView = textureView;
        initTimeLine(context);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                XmMovieManager.this.mStreamingContext.connectTimelineWithSurface(XmMovieManager.this.mTimeline, new Surface(surfaceTexture));
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                Log.d("XmMovieManager", "onSurfaceTextureSizeChanged %d:%d", Integer.valueOf(i), Integer.valueOf(i2));
                XmMovieManager.this.mStreamingContext.nativeSurfaceChanged(new Surface(surfaceTexture), i, i2);
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        });
        this.mIsPaused = false;
    }

    public boolean isReadyForExport() {
        return this.mStreamingContext.isReadyForExport();
    }

    public boolean isReadyForSwitch() {
        return this.mStreamingContext.isReadyForSwitch();
    }

    public boolean isSupportVideo(String str) {
        return true;
    }

    public void moveImage(int i, int i2) {
        this.mHandler.removeMessages(0);
        this.mHandler.sendEmptyMessageDelayed(0, 250);
    }

    public void onFirstVideoFramePresented() {
    }

    public void onPause() {
        super.onPause();
        Log.d("XmMovieManager", "onPause");
        synchronized (this.mLock) {
            pause();
            this.mStreamingContext.pauseToBackground();
            this.mHandler.removeMessages(0);
            this.mIsPaused = true;
        }
    }

    public void onPlaybackEOF() {
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackEOF();
        }
        this.mStreamingContext.skipPause();
    }

    public void onPlaybackPreloadingCompletion() {
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackPreloadingCompletion();
        }
        Log.d("XmMovieManager", "onPlaybackPreloadingCompletion");
    }

    public void onPlaybackTimelinePosition(int i) {
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackTimeChanged(i);
        }
    }

    public void onResume() {
        Log.d("XmMovieManager", "onResume");
        super.onResume();
        if (this.mIsPaused) {
            this.mStreamingContext.resumeToForeground(this.mTimeline.getInternalObject());
            this.mIsPaused = false;
        }
    }

    public void onStreamingEngineStateChanged(int i) {
        Log.d("XmMovieManager", "state: %d", (Object) Integer.valueOf(i));
        this.mMovieState = getStateFromXmEngine(i);
        Iterator<MovieManager.StateChangeListener> it = this.mStateChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(this.mMovieState);
        }
    }

    public void pause() {
        Log.d("XmMovieManager", "pause");
        this.mStreamingContext.pause();
    }

    public void pauseOrResume() {
        Log.d("XmMovieManager", "pauseOrResume:%d", (Object) Integer.valueOf(this.mMovieState));
        if (this.mMovieState == 1) {
            pause();
        } else {
            resume();
        }
    }

    public void removeImageAtIndex(int i) {
        resetImage();
    }

    public void removeStateChangeListener(MovieManager.StateChangeListener stateChangeListener) {
        this.mStateChangeListeners = null;
    }

    public void replay() {
        seek(0);
        resume();
    }

    public void resetImage(List<ImageEntity> list, boolean z) {
        synchronized (this.mLock) {
            this.mThemeHelper.changeTimelineDuration(z);
            resetImage();
        }
    }

    public void resume() {
        Log.d("XmMovieManager", "resume");
        this.mStreamingContext.resume();
    }

    public void seek(int i) {
        this.mStreamingContext.seekToPos(i);
    }

    public int seekToIndex(int i) {
        int geClipMiddleDuration;
        synchronized (this.mLock) {
            geClipMiddleDuration = this.mThemeHelper.geClipMiddleDuration(i);
            this.mStreamingContext.seekToPos(geClipMiddleDuration);
        }
        return geClipMiddleDuration;
    }

    public void setAudio(AudioResource audioResource) {
        Log.d("XmMovieManager", "setAudio" + audioResource + audioResource.getResType() + audioResource.getDownloadSrcPath());
        synchronized (this.mLock) {
            if (audioResource != null) {
                if (audioResource.getResType() != 1) {
                    if (audioResource.getResType() == 2) {
                        this.mThemeHelper.changeMusic(audioResource.getDownloadSrcPath(), 1);
                    } else {
                        this.mThemeHelper.changeMusic(audioResource.getDownloadSrcPath(), 2);
                    }
                    this.mStreamingContext.applyTimeline(this.mTimeline);
                }
            }
            this.mThemeHelper.changeMusic((String) null, 3);
            this.mStreamingContext.applyTimeline(this.mTimeline);
        }
    }

    public void setInfo(MovieInfo movieInfo) {
        Log.d("XmMovieManager", "setInfo");
        this.mMovieInfo = movieInfo;
        this.mThemeHelper.setShortVideo(movieInfo.isShortVideo);
        for (ImageEntity imageEntity : this.mMovieInfo.imageList) {
            this.mThemeHelper.appendClip(imageEntity.image);
        }
        String str = "assets:/xms/movieTemplateNormal";
        if (TextUtils.isEmpty(movieInfo.template) || TextUtils.equals(movieInfo.template, MovieStatUtils.FROM_NORMAL)) {
            movieInfo.template = MovieStatUtils.FROM_NORMAL;
        } else {
            String templateDir = TemplateFactory.getTemplateDir(movieInfo.template);
            if (!BaseFileUtils.isFileExist(templateDir)) {
                movieInfo.template = MovieStatUtils.FROM_NORMAL;
                Log.w("XmMovieManager", "default template:%s is not download", str);
            } else {
                str = templateDir;
            }
        }
        if (TextUtils.equals(movieInfo.template, MovieStatUtils.FROM_NORMAL)) {
            this.mTimeline.setTitle(this.mMovieInfo.title, this.mMovieInfo.subTitle);
        }
        this.mThemeHelper.setTemplate(str);
        this.mStreamingContext.applyTimeline(this.mTimeline);
        Log.d("XmMovieManager", "setInfo end");
    }

    public void setIsShortVideo(boolean z) {
        synchronized (this.mLock) {
            this.mThemeHelper.changeTimelineDuration(z);
            this.mStreamingContext.applyTimeline(this.mTimeline);
        }
    }

    public void setTemplate(TemplateResource templateResource) {
        synchronized (this.mLock) {
            if (templateResource.isPackageAssets) {
                this.mTimeline.setTitle(this.mMovieInfo.title, this.mMovieInfo.subTitle);
                this.mThemeHelper.setTemplate("assets:/xms/movieTemplateNormal");
            } else {
                this.mTimeline.setTitle((String) null, (String) null);
                this.mThemeHelper.setTemplate(templateResource.getDownloadSrcPath());
            }
            this.mStreamingContext.applyTimeline(this.mTimeline);
        }
    }
}
