package com.miui.gallery.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.miui.gallery.util.Log;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class GalleryVideoView extends TextureView {
    private AudioAttributes mAudioAttributes;
    /* access modifiers changed from: private */
    public int mAudioFocusType;
    /* access modifiers changed from: private */
    public AudioManager mAudioManager;
    private int mAudioSession;
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private MediaPlayer.OnCompletionListener mCompletionListener;
    private Context mContext;
    /* access modifiers changed from: private */
    public int mCurrentBufferPercentage;
    /* access modifiers changed from: private */
    public int mCurrentState;
    private int mDefaultHeight;
    private int mDefaultWidth;
    private MediaPlayer.OnErrorListener mErrorListener;
    private long mFileLength;
    private long mFileOffset;
    private String mFilePath;
    private Map<String, String> mHeaders;
    private MediaPlayer.OnInfoListener mInfoListener;
    /* access modifiers changed from: private */
    public MediaPlayer mMediaPlayer;
    /* access modifiers changed from: private */
    public MediaPlayer.OnCompletionListener mOnCompletionListener;
    /* access modifiers changed from: private */
    public MediaPlayer.OnErrorListener mOnErrorListener;
    /* access modifiers changed from: private */
    public MediaPlayer.OnInfoListener mOnInfoListener;
    /* access modifiers changed from: private */
    public MediaPlayer.OnPreparedListener mOnPreparedListener;
    MediaPlayer.OnPreparedListener mPreparedListener;
    /* access modifiers changed from: private */
    public int mSeekWhenPrepared;
    MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    /* access modifiers changed from: private */
    public SurfaceTexture mSurfaceTexture;
    TextureView.SurfaceTextureListener mSurfaceTextureListener;
    /* access modifiers changed from: private */
    public int mTargetState;
    private boolean mTransformApply;
    private Uri mUri;
    /* access modifiers changed from: private */
    public int mVideoHeight;
    /* access modifiers changed from: private */
    public int mVideoWidth;
    private float mVolume;

    public GalleryVideoView(Context context) {
        this(context, (AttributeSet) null);
    }

    public GalleryVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GalleryVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mMediaPlayer = null;
        this.mAudioFocusType = 1;
        this.mVolume = -1.0f;
        this.mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                int unused = GalleryVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                int unused2 = GalleryVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (GalleryVideoView.this.mVideoWidth != 0 && GalleryVideoView.this.mVideoHeight != 0) {
                    GalleryVideoView.this.requestLayout();
                }
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                int unused = GalleryVideoView.this.mCurrentState = 2;
                if (GalleryVideoView.this.mOnPreparedListener != null) {
                    GalleryVideoView.this.mOnPreparedListener.onPrepared(GalleryVideoView.this.mMediaPlayer);
                }
                int unused2 = GalleryVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                int unused3 = GalleryVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                int access$500 = GalleryVideoView.this.mSeekWhenPrepared;
                if (access$500 != 0) {
                    GalleryVideoView.this.seekTo(access$500);
                }
                if (GalleryVideoView.this.mTargetState == 3) {
                    GalleryVideoView.this.start();
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                int unused = GalleryVideoView.this.mCurrentState = 5;
                int unused2 = GalleryVideoView.this.mTargetState = 5;
                if (GalleryVideoView.this.mOnCompletionListener != null) {
                    GalleryVideoView.this.mOnCompletionListener.onCompletion(GalleryVideoView.this.mMediaPlayer);
                }
                if (GalleryVideoView.this.mAudioFocusType != 0) {
                    GalleryVideoView.this.mAudioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
                }
            }
        };
        this.mInfoListener = new MediaPlayer.OnInfoListener() {
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                if (GalleryVideoView.this.mOnInfoListener == null) {
                    return true;
                }
                GalleryVideoView.this.mOnInfoListener.onInfo(mediaPlayer, i, i2);
                return true;
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                Log.d("GalleryVideoView", "Error: " + i + "," + i2);
                int unused = GalleryVideoView.this.mCurrentState = -1;
                int unused2 = GalleryVideoView.this.mTargetState = -1;
                if (GalleryVideoView.this.mOnErrorListener == null || GalleryVideoView.this.mOnErrorListener.onError(GalleryVideoView.this.mMediaPlayer, i, i2)) {
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() {
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                int unused = GalleryVideoView.this.mCurrentBufferPercentage = i;
            }
        };
        this.mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                SurfaceTexture unused = GalleryVideoView.this.mSurfaceTexture = surfaceTexture;
                GalleryVideoView.this.openVideo();
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                SurfaceTexture unused = GalleryVideoView.this.mSurfaceTexture = null;
                GalleryVideoView.this.release(true);
                Log.d("GalleryVideoView", "surfaceDestroyed");
                return true;
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }
        };
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (Build.VERSION.SDK_INT >= 21) {
            this.mAudioAttributes = new AudioAttributes.Builder().setUsage(1).setContentType(3).build();
        }
        setSurfaceTextureListener(this.mSurfaceTextureListener);
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r3.mCurrentState;
     */
    private boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || i == -1 || i == 0 || i == 1) ? false : true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0111 A[SYNTHETIC, Splitter:B:49:0x0111] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x013d A[SYNTHETIC, Splitter:B:57:0x013d] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0149 A[SYNTHETIC, Splitter:B:62:0x0149] */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:46:0x00f0=Splitter:B:46:0x00f0, B:54:0x011c=Splitter:B:54:0x011c} */
    public void openVideo() {
        Throwable th;
        if ((this.mUri != null || !TextUtils.isEmpty(this.mFilePath)) && this.mSurfaceTexture != null) {
            release(false);
            int i = this.mAudioFocusType;
            FileInputStream fileInputStream = null;
            if (i != 0) {
                this.mAudioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, i);
            }
            try {
                this.mMediaPlayer = new MediaPlayer();
                if (this.mAudioSession != 0) {
                    this.mMediaPlayer.setAudioSessionId(this.mAudioSession);
                } else {
                    this.mAudioSession = this.mMediaPlayer.getAudioSessionId();
                }
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mCurrentBufferPercentage = 0;
                if (this.mUri != null) {
                    this.mMediaPlayer.setDataSource(this.mContext, this.mUri, this.mHeaders);
                } else if (!TextUtils.isEmpty(this.mFilePath)) {
                    FileInputStream fileInputStream2 = new FileInputStream(this.mFilePath);
                    try {
                        this.mMediaPlayer.setDataSource(fileInputStream2.getFD(), this.mFileOffset, this.mFileLength);
                        fileInputStream = fileInputStream2;
                    } catch (IOException e) {
                        e = e;
                        fileInputStream = fileInputStream2;
                        Log.w("GalleryVideoView", "Unable to open content: " + this.mUri, e);
                        this.mCurrentState = -1;
                        this.mTargetState = -1;
                        this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
                        if (fileInputStream == null) {
                            try {
                                fileInputStream.close();
                                return;
                            } catch (IOException e2) {
                                Log.w("GalleryVideoView", "close stream error", e2);
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        fileInputStream = fileInputStream2;
                        try {
                            Log.w("GalleryVideoView", "Unable to open content: " + this.mUri, e);
                            this.mCurrentState = -1;
                            this.mTargetState = -1;
                            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
                            if (fileInputStream == null) {
                                try {
                                    fileInputStream.close();
                                    return;
                                } catch (IOException e4) {
                                    Log.w("GalleryVideoView", "close stream error", e4);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e5) {
                                Log.w("GalleryVideoView", "close stream error", e5);
                            }
                        }
                        throw th;
                    }
                }
                this.mMediaPlayer.setSurface(new Surface(this.mSurfaceTexture));
                if (Build.VERSION.SDK_INT >= 21) {
                    this.mMediaPlayer.setAudioAttributes(this.mAudioAttributes);
                }
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                if (this.mVolume >= 0.0f) {
                    this.mMediaPlayer.setVolume(this.mVolume, this.mVolume);
                }
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = 1;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e6) {
                        Log.w("GalleryVideoView", "close stream error", e6);
                    }
                }
            } catch (IOException e7) {
                e = e7;
                Log.w("GalleryVideoView", "Unable to open content: " + this.mUri, e);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
                if (fileInputStream == null) {
                }
            } catch (IllegalArgumentException e8) {
                e = e8;
                Log.w("GalleryVideoView", "Unable to open content: " + this.mUri, e);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
                if (fileInputStream == null) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void release(boolean z) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
            if (this.mAudioFocusType != 0) {
                this.mAudioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
            }
        }
    }

    public CharSequence getAccessibilityClassName() {
        return GalleryVideoView.class.getName();
    }

    public int getAudioSessionId() {
        if (this.mAudioSession == 0) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mAudioSession = mediaPlayer.getAudioSessionId();
            mediaPlayer.release();
        }
        return this.mAudioSession;
    }

    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = this.mDefaultWidth;
        int i8 = this.mDefaultHeight;
        int i9 = this.mVideoWidth;
        if (i9 > 0 && (i6 = this.mVideoHeight) > 0) {
            i7 = i9;
            i8 = i6;
        }
        int defaultSize = getDefaultSize(i7, i);
        int defaultSize2 = getDefaultSize(i8, i2);
        if (i7 <= 0 || i8 <= 0 || this.mTransformApply) {
            i4 = defaultSize;
        } else {
            int mode = View.MeasureSpec.getMode(i);
            i4 = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            i3 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i10 = i7 * i3;
                int i11 = i4 * i8;
                if (i10 < i11) {
                    i4 = i10 / i8;
                } else if (i10 > i11) {
                    defaultSize2 = i11 / i7;
                }
                setMeasuredDimension(i4, i3);
            }
            if (mode == 1073741824) {
                int i12 = (i8 * i4) / i7;
                if (mode2 != Integer.MIN_VALUE || i12 <= i3) {
                    i3 = i12;
                }
            } else if (mode2 == 1073741824) {
                int i13 = (i7 * i3) / i8;
                if (mode != Integer.MIN_VALUE || i13 <= i4) {
                    i4 = i13;
                }
            } else {
                if (mode2 != Integer.MIN_VALUE || i8 <= i3) {
                    i5 = i7;
                    i3 = i8;
                } else {
                    i5 = (i3 * i7) / i8;
                }
                if (mode != Integer.MIN_VALUE || i5 <= i4) {
                    i4 = i5;
                } else {
                    defaultSize2 = (i8 * i4) / i7;
                }
            }
            setMeasuredDimension(i4, i3);
        }
        i3 = defaultSize2;
        setMeasuredDimension(i4, i3);
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        this.mTargetState = 4;
    }

    public void requestAudioFocus(int i) {
        setAudioFocusRequest(i);
        if (i != 0) {
            this.mAudioManager.requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, i);
        }
    }

    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = i;
    }

    public void setAudioAttributes(AudioAttributes audioAttributes) {
        if (audioAttributes != null) {
            this.mAudioAttributes = audioAttributes;
            return;
        }
        throw new IllegalArgumentException("Illegal null AudioAttributes");
    }

    public void setAudioFocusRequest(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {
            this.mAudioFocusType = i;
            return;
        }
        throw new IllegalArgumentException("Illegal audio focus type " + i);
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(MediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setTransform(Matrix matrix) {
        if (matrix == null || matrix.isIdentity()) {
            this.mTransformApply = false;
            return;
        }
        super.setTransform(matrix);
        this.mTransformApply = true;
    }

    public void setVideoFilePath(String str) {
        setVideoFilePath(str, 0, 576460752303423487L);
    }

    public void setVideoFilePath(String str, long j) {
        setVideoFilePath(str, j, 576460752303423487L);
    }

    public void setVideoFilePath(String str, long j, long j2) {
        this.mFilePath = str;
        this.mFileOffset = j;
        this.mFileLength = j2;
        this.mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, (Map<String, String>) null);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mHeaders = map;
        this.mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void setVolume(float f) {
        MediaPlayer mediaPlayer;
        this.mVolume = f;
        if (f >= 0.0f && (mediaPlayer = this.mMediaPlayer) != null) {
            mediaPlayer.setVolume(f, f);
        }
    }

    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
        }
        this.mTargetState = 3;
    }

    public void stopPlayback() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
            this.mAudioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        }
    }
}
