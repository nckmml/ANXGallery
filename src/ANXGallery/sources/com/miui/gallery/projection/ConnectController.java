package com.miui.gallery.projection;

import android.app.Activity;
import android.text.TextUtils;
import com.milink.api.v1.MilinkClientManager;
import com.milink.api.v1.MilinkClientManagerDataSource;
import com.milink.api.v1.MilinkClientManagerDelegate;
import com.milink.api.v1.type.DeviceType;
import com.milink.api.v1.type.ErrorCode;
import com.milink.api.v1.type.MediaType;
import com.milink.api.v1.type.ReturnCode;
import com.milink.api.v1.type.SlideMode;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.preference.GalleryPreferences;
import com.miui.gallery.projection.IConnectController;
import com.miui.gallery.util.BuildUtil;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.ToastUtils;
import com.nexstreaming.nexeditorsdk.nexProject;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.keyczar.Keyczar;

public abstract class ConnectController implements IConnectController {
    /* access modifiers changed from: private */
    public AtomicBoolean mConnectTillOpen = new AtomicBoolean(false);
    protected Map.Entry<String, String> mConnectedDevice;
    private Map.Entry<String, String> mConnectingDevice;
    private int mCurrentIndex = 0;
    private String mCurrentPhoto = "";
    private MilinkClientManagerDataSource mDataSource = new MilinkClientManagerDataSource() {
        public String getNextPhoto(String str, boolean z) {
            return ConnectController.this.mSlidingWindow.getNext(str, z);
        }

        public String getPrevPhoto(String str, boolean z) {
            return ConnectController.this.mSlidingWindow.getPrevious(str, z);
        }
    };
    private MilinkClientManagerDelegate mDelegate = new MilinkClientManagerDelegate() {
        public void onClose() {
            Log.v("project_IConnectController", "service closed");
        }

        public void onConnected() {
            Log.d("project_IConnectController", "service onConnected");
            ConnectController.this.setIsConnected(true);
            ConnectController.this.doOnConnected();
        }

        public void onConnectedFailed(ErrorCode errorCode) {
            Log.d("project_IConnectController", "service onConnectedFailed");
            ConnectController.this.setIsConnected(false);
            ConnectController.this.doOnConnectedFail();
        }

        public void onDeviceFound(String str, String str2, DeviceType deviceType) {
            Log.v("project_IConnectController", "service onDeviceFound");
            ConnectController.this.doOnDeviceFound(str, str2, deviceType);
        }

        public void onDeviceLost(String str) {
            Log.v("project_IConnectController", "service onDeviceLost");
            ConnectController.this.doOnDeviceLost(str);
        }

        public void onDisconnected() {
            Log.d("project_IConnectController", "service onDisconnected");
            ConnectController.this.setIsConnected(false);
        }

        public void onLoading() {
            Log.v("project_IConnectController", "loading...");
            if (ConnectController.this.mMediaPlayListener != null) {
                ConnectController.this.mMediaPlayListener.onLoading();
            }
        }

        public void onNextAudio(boolean z) {
            Log.v("project_IConnectController", "onRequestNextItem: " + z);
        }

        public void onOpen() {
            Log.v("project_IConnectController", "service openned");
            ConnectController.this.setIsOpen(true);
            if (ConnectController.this.mConnectTillOpen.get()) {
                ConnectController.this.mConnectTillOpen.set(false);
                boolean unused = ConnectController.this.connectRenderer();
            }
            ConnectController.this.doOnOpen();
        }

        public void onPaused() {
            Log.v("project_IConnectController", "paused");
            if (ConnectController.this.mMediaPlayListener != null) {
                ConnectController.this.mMediaPlayListener.onPaused();
            }
        }

        public void onPlaying() {
            Log.v("project_IConnectController", "playing...");
            if (ConnectController.this.mMediaPlayListener != null) {
                ConnectController.this.mMediaPlayListener.onPlaying();
            }
        }

        public void onPrevAudio(boolean z) {
            Log.v("project_IConnectController", "onRequestPrevItem: " + z);
        }

        public void onStopped() {
            Log.v("project_IConnectController", "stopped");
            if (ConnectController.this.mMediaPlayListener != null) {
                ConnectController.this.mMediaPlayListener.onStopped();
            }
        }

        public void onVolume(int i) {
        }
    };
    private boolean mIsOpen = false;
    /* access modifiers changed from: private */
    public IConnectController.OnMediaPlayListener mMediaPlayListener;
    protected MilinkClientManager mPhotoManager;
    /* access modifiers changed from: private */
    public SlidingWindow mSlidingWindow = new SlidingWindow();
    private IConnectController.OnStatusisListener mStatusListener;
    private final Object mStatusLock = new Object();
    private Map.Entry<String, String> mToConnectDevice;

    /* access modifiers changed from: private */
    public boolean connectRenderer() {
        Log.i("project_IConnectController", "connectrender");
        if (!isOpen()) {
            Log.e("project_IConnectController", "connect before open");
            this.mConnectTillOpen.set(true);
            return false;
        }
        String value = this.mToConnectDevice.getValue();
        if (value == null) {
            Log.e("project_IConnectController", "device not in devices map");
        }
        if (isConnected()) {
            Log.e("project_IConnectController", "connected to another device");
            if (!disconnectRenderer()) {
                Log.e("project_IConnectController", "could not disconnect to previous device");
                return false;
            }
        }
        if (this.mPhotoManager.connect(value, nexProject.kAutoThemeClipDuration) != ReturnCode.OK) {
            Log.e("project_IConnectController", "connect error ");
            return false;
        }
        setConnectingDevice();
        return true;
    }

    private boolean disconnectRenderer() {
        if (this.mPhotoManager == null) {
            return false;
        }
        setIsConnected(false);
        if (this.mPhotoManager.stopShow() != ReturnCode.OK) {
            Log.w("project_IConnectController", "stop show failed");
            return false;
        } else if (this.mPhotoManager.disconnect() == ReturnCode.OK) {
            return true;
        } else {
            Log.w("project_IConnectController", "disconnect renderer failed");
            return false;
        }
    }

    private void doOnShowError() {
        Log.d("project_IConnectController", "connection is broken without informing gallery");
        closeService();
        ToastUtils.makeText(GalleryApp.sGetAndroidContext(), (int) R.string.projection_device_connection_failed);
        GallerySamplingStatHelper.recordCountEvent("photo", "project_photo_fail");
    }

    private boolean isOpen() {
        boolean z;
        synchronized (this.mStatusLock) {
            z = this.mIsOpen;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        r0 = r3.mStatusListener;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001e, code lost:
        if (r4 == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0020, code lost:
        r0.onConnectStatusChanged(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        r0.onConnectStatusChanged(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    private void setConnectedDevice(boolean z) {
        synchronized (this.mStatusLock) {
            if (!z) {
                this.mToConnectDevice = null;
                this.mConnectingDevice = null;
                this.mConnectedDevice = null;
            } else if (this.mConnectingDevice != null) {
                this.mConnectedDevice = this.mConnectingDevice;
                this.mConnectingDevice = null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r0 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        r0.onConnectStatusChanged(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        r0 = r2.mStatusListener;
     */
    private void setConnectingDevice() {
        synchronized (this.mStatusLock) {
            if (this.mToConnectDevice != null) {
                this.mConnectingDevice = this.mToConnectDevice;
                this.mToConnectDevice = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void setIsConnected(boolean z) {
        Log.i("project_IConnectController", "setisconnected %s", (Object) Boolean.valueOf(z));
        setConnectedDevice(z);
    }

    /* access modifiers changed from: private */
    public void setIsOpen(boolean z) {
        synchronized (this.mStatusLock) {
            this.mIsOpen = z;
        }
        IConnectController.OnStatusisListener onStatusisListener = this.mStatusListener;
        if (onStatusisListener != null) {
            onStatusisListener.onOpenStatusChanged(z);
        }
    }

    public void attachActivity(Activity activity) {
    }

    /* access modifiers changed from: protected */
    public abstract boolean chooseDevice();

    public void closeService() {
        setIsConnected(false);
        setIsOpen(false);
        this.mMediaPlayListener = null;
        this.mStatusListener = null;
        disconnectRenderer();
        synchronized (this) {
            if (this.mPhotoManager != null) {
                Log.v("project_IConnectController", "close milink service");
                this.mPhotoManager.setDelegate((MilinkClientManagerDelegate) null);
                this.mPhotoManager.close();
                this.mPhotoManager = null;
            }
        }
    }

    public void detachActivity(Activity activity) {
    }

    /* access modifiers changed from: protected */
    public void doOnConnected() {
        Log.d("project_IConnectController", "connect is responded ok");
        this.mPhotoManager.startShow();
        if (!TextUtils.isEmpty(this.mCurrentPhoto)) {
            Log.v("project_IConnectController", "==the to show photo is: " + this.mCurrentPhoto);
            showPhoto(this.mCurrentPhoto, this.mCurrentIndex);
        }
        GallerySamplingStatHelper.recordCountEvent("photo", "project_photo_success");
    }

    /* access modifiers changed from: protected */
    public void doOnConnectedFail() {
        Log.d("project_IConnectController", "renderer is failed to connect");
        ToastUtils.makeText(GalleryApp.sGetAndroidContext(), (int) R.string.projection_device_connection_failed);
        GallerySamplingStatHelper.recordCountEvent("photo", "project_photo_fail");
    }

    /* access modifiers changed from: protected */
    public abstract void doOnDeviceFound(String str, String str2, DeviceType deviceType);

    /* access modifiers changed from: protected */
    public abstract void doOnDeviceLost(String str);

    /* access modifiers changed from: protected */
    public abstract void doOnOpen();

    public int getConnectStatus() {
        synchronized (this.mStatusLock) {
            if (this.mConnectedDevice != null) {
                return 1;
            }
            return this.mConnectingDevice != null ? 0 : -1;
        }
    }

    public String getConnectedDevice() {
        Map.Entry<String, String> entry = this.mConnectedDevice;
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public String getConnectingDevice() {
        Map.Entry<String, String> entry = this.mConnectingDevice;
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public int getCurrentPosition() {
        MilinkClientManager milinkClientManager = this.mPhotoManager;
        if (milinkClientManager != null) {
            return milinkClientManager.getPlaybackProgress();
        }
        return 0;
    }

    public int getDuration() {
        MilinkClientManager milinkClientManager = this.mPhotoManager;
        if (milinkClientManager != null) {
            return milinkClientManager.getPlaybackDuration();
        }
        return 0;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mStatusLock) {
            z = this.mConnectedDevice != null;
        }
        return z;
    }

    public boolean isPlaying() {
        MilinkClientManager milinkClientManager = this.mPhotoManager;
        return milinkClientManager != null && milinkClientManager.getPlaybackRate() == 1;
    }

    /* access modifiers changed from: protected */
    public void open() {
        synchronized (this) {
            if (!isOpen()) {
                if (this.mPhotoManager == null) {
                    if (this.mDelegate != null) {
                        this.mPhotoManager = new MilinkClientManager(GalleryApp.sGetAndroidContext());
                        this.mPhotoManager.setDeviceName(BuildUtil.getDeviceName(GalleryApp.sGetAndroidContext()));
                        this.mPhotoManager.setDataSource(this.mDataSource);
                        this.mPhotoManager.setDelegate(this.mDelegate);
                    } else {
                        return;
                    }
                }
                this.mPhotoManager.open();
            }
        }
    }

    public void pause() {
        MilinkClientManager milinkClientManager = this.mPhotoManager;
        if (milinkClientManager != null) {
            milinkClientManager.setPlaybackRate(0);
        }
    }

    public void playVideo(String str, String str2) {
        if (this.mPhotoManager != null && !TextUtils.isEmpty(str)) {
            try {
                this.mPhotoManager.startPlay(URLEncoder.encode(str, Keyczar.DEFAULT_ENCODING), str2, 0, 0.0d, MediaType.Video);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public void project() {
        if (isConnected()) {
            disconnectRenderer();
            return;
        }
        if (!isOpen()) {
            open();
        }
        chooseDevice();
    }

    public void registerMediaPlayListener(IConnectController.OnMediaPlayListener onMediaPlayListener) {
        if (onMediaPlayListener != null) {
            this.mMediaPlayListener = onMediaPlayListener;
        }
    }

    public void registerStatusListener(IConnectController.OnStatusisListener onStatusisListener) {
        if (onStatusisListener != null) {
            this.mStatusListener = onStatusisListener;
        }
    }

    public void resume() {
        MilinkClientManager milinkClientManager = this.mPhotoManager;
        if (milinkClientManager != null) {
            milinkClientManager.setPlaybackRate(1);
        }
    }

    public void seekTo(int i) {
        MilinkClientManager milinkClientManager = this.mPhotoManager;
        if (milinkClientManager != null) {
            milinkClientManager.setPlaybackProgress(i);
        }
    }

    /* access modifiers changed from: protected */
    public void setToConnectDevice(String str, String str2) {
        synchronized (this.mStatusLock) {
            Log.i("project_IConnectController", "set toconnectdevice");
            this.mToConnectDevice = new AbstractMap.SimpleEntry(str, str2);
            connectRenderer();
        }
    }

    public void showPhoto(String str, int i) {
        if (!isConnected()) {
            Log.v("project_IConnectController", "no renderer connected");
            return;
        }
        Log.v("project_IConnectController", "the photo is: %s %d ", str, Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            try {
                if (this.mPhotoManager.show(str) != ReturnCode.OK) {
                    doOnShowError();
                }
            } catch (IllegalArgumentException e) {
                Log.v("project_IConnectController", "MilinkClientManager show exception", e);
            }
        }
    }

    public boolean showSlide(int i) {
        this.mCurrentIndex = i;
        this.mSlidingWindow.onCurrentIndexChanged(this.mCurrentIndex);
        if (this.mPhotoManager.startSlideshow(Math.max(3000, GalleryPreferences.SlideShow.getSlideShowInterval() * 1000), SlideMode.Recyle) == ReturnCode.OK) {
            return true;
        }
        doOnShowError();
        return false;
    }

    public void stop() {
        MilinkClientManager milinkClientManager = this.mPhotoManager;
        if (milinkClientManager != null) {
            milinkClientManager.stopPlay();
        }
    }

    public void syncRemoteView(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.mPhotoManager.zoomPhoto(this.mCurrentPhoto, (int) f, (int) f2, (int) f3, (int) f4, (int) f5, (int) f6, f7);
    }

    public void unregisterMediaPlayListener(IConnectController.OnMediaPlayListener onMediaPlayListener) {
        if (onMediaPlayListener == this.mMediaPlayListener) {
            this.mMediaPlayListener = null;
        }
    }

    public void unregisterStatusListener(IConnectController.OnStatusisListener onStatusisListener) {
        if (onStatusisListener == this.mStatusListener) {
            this.mStatusListener = null;
        }
    }

    public void updateCurrentFolder(IConnectController.DataSet dataSet) {
        this.mSlidingWindow.setMediaSet(dataSet);
    }

    public void updateCurrentPhoto(String str, int i) {
        this.mCurrentPhoto = str;
        this.mCurrentIndex = i;
        if (!TextUtils.isEmpty(getConnectedDevice())) {
            this.mSlidingWindow.onCurrentIndexChanged(this.mCurrentIndex);
            showPhoto(str, i);
        }
    }
}
