package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.nexstreaming.app.common.task.Task;
import com.nexstreaming.kminternal.kinemaster.config.EditorGlobal;
import com.nexstreaming.kminternal.kinemaster.mediainfo.MediaInfo;
import com.nexstreaming.kminternal.nexvideoeditor.LayerRenderer;
import com.nexstreaming.kminternal.nexvideoeditor.NexAudioClip;
import com.nexstreaming.kminternal.nexvideoeditor.NexDrawInfo;
import com.nexstreaming.kminternal.nexvideoeditor.NexEditor;
import com.nexstreaming.kminternal.nexvideoeditor.NexRectangle;
import com.nexstreaming.kminternal.nexvideoeditor.NexThemeView;
import com.nexstreaming.kminternal.nexvideoeditor.NexVisualClip;
import com.nexstreaming.kminternal.nexvideoeditor.b;
import com.nexstreaming.kminternal.nexvideoeditor.c;
import com.nexstreaming.nexeditorsdk.exception.InvalidRangeException;
import com.nexstreaming.nexeditorsdk.exception.ProjectNotAttachedException;
import com.nexstreaming.nexeditorsdk.module.nexExternalExportProvider;
import com.nexstreaming.nexeditorsdk.module.nexFaceDetectionProvider;
import com.nexstreaming.nexeditorsdk.nexOverlayItem;
import com.nexstreaming.nexeditorsdk.nexTranscode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.keyczar.Keyczar;

public final class nexEngine implements SurfaceHolder.Callback {
    public static final int DirectExportOption_AudioEncode = 1;
    public static final int DirectExportOption_None = 0;
    public static final int ExportAVCLevel1 = 1;
    public static final int ExportAVCLevel11 = 4;
    public static final int ExportAVCLevel12 = 8;
    public static final int ExportAVCLevel13 = 16;
    public static final int ExportAVCLevel1b = 2;
    public static final int ExportAVCLevel2 = 32;
    public static final int ExportAVCLevel21 = 64;
    public static final int ExportAVCLevel22 = 128;
    public static final int ExportAVCLevel3 = 256;
    public static final int ExportAVCLevel31 = 512;
    public static final int ExportAVCLevel32 = 1024;
    public static final int ExportAVCLevel4 = 2048;
    public static final int ExportAVCLevel41 = 4096;
    public static final int ExportAVCLevel42 = 8192;
    public static final int ExportAVCLevel5 = 16384;
    public static final int ExportAVCLevel51 = 32768;
    public static final int ExportAVCLevel52 = 65536;
    public static final int ExportCodec_AVC = 268501760;
    public static final int ExportCodec_HEVC = 268502016;
    public static final int ExportCodec_MPEG4V = 268566784;
    public static final int ExportCropMode_Enhanced = 1;
    public static final int ExportCropMode_Fill = 2;
    public static final int ExportCropMode_None = 0;
    public static final int ExportHEVCHighTierLevel1 = 2;
    public static final int ExportHEVCHighTierLevel2 = 8;
    public static final int ExportHEVCHighTierLevel21 = 32;
    public static final int ExportHEVCHighTierLevel3 = 128;
    public static final int ExportHEVCHighTierLevel31 = 512;
    public static final int ExportHEVCHighTierLevel4 = 2048;
    public static final int ExportHEVCHighTierLevel41 = 8192;
    public static final int ExportHEVCHighTierLevel5 = 32768;
    public static final int ExportHEVCHighTierLevel51 = 131072;
    public static final int ExportHEVCHighTierLevel52 = 524288;
    public static final int ExportHEVCHighTierLevel6 = 2097152;
    public static final int ExportHEVCHighTierLevel61 = 8388608;
    public static final int ExportHEVCHighTierLevel62 = 33554432;
    public static final int ExportHEVCMainTierLevel1 = 1;
    public static final int ExportHEVCMainTierLevel2 = 4;
    public static final int ExportHEVCMainTierLevel21 = 16;
    public static final int ExportHEVCMainTierLevel3 = 64;
    public static final int ExportHEVCMainTierLevel31 = 256;
    public static final int ExportHEVCMainTierLevel4 = 1024;
    public static final int ExportHEVCMainTierLevel41 = 4096;
    public static final int ExportHEVCMainTierLevel5 = 16384;
    public static final int ExportHEVCMainTierLevel51 = 65536;
    public static final int ExportHEVCMainTierLevel52 = 262144;
    public static final int ExportHEVCMainTierLevel6 = 1048576;
    public static final int ExportHEVCMainTierLevel61 = 4194304;
    public static final int ExportHEVCMainTierLevel62 = 16777216;
    public static final int ExportMPEG4Level0 = 1;
    public static final int ExportMPEG4Level0b = 2;
    public static final int ExportMPEG4Level1 = 4;
    public static final int ExportMPEG4Level2 = 8;
    public static final int ExportMPEG4Level3 = 16;
    public static final int ExportMPEG4Level4 = 32;
    public static final int ExportMPEG4Level4a = 64;
    public static final int ExportMPEG4Level5 = 128;
    public static final int ExportProfile_AVCBaseline = 1;
    public static final int ExportProfile_AVCExtended = 3;
    public static final int ExportProfile_AVCHigh = 4;
    public static final int ExportProfile_AVCHigh10 = 5;
    public static final int ExportProfile_AVCHigh422 = 6;
    public static final int ExportProfile_AVCHigh444 = 7;
    public static final int ExportProfile_AVCMain = 2;
    public static final int ExportProfile_HEVCMain = 1;
    public static final int ExportProfile_HEVCMain10 = 2;
    public static final int ExportProfile_MPEG4VASP = 32768;
    public static final int ExportProfile_MPEG4VSimple = 1;
    private static final int PERSIST_INTERVAL = 500;
    private static final int PREWAKE_INTERVAL = 2000;
    private static final String TAG = "nexEngine";
    private static final int export_audio_sampling_rate = 44100;
    private static final int export_fps = 3000;
    private static final int kState_export = 2;
    private static final int kState_idle = 0;
    private static final int kState_load = 1;
    /* access modifiers changed from: private */
    public static Comparator<nexOverlayItem> layerZOrderComparator = new Comparator<nexOverlayItem>() {
        /* renamed from: a */
        public int compare(nexOverlayItem nexoverlayitem, nexOverlayItem nexoverlayitem2) {
            int zOrder = nexoverlayitem.getZOrder() - nexoverlayitem2.getZOrder();
            if (zOrder < 0) {
                return -1;
            }
            return zOrder > 0 ? 1 : 0;
        }
    };
    public static final int retCheckDirectExport_ClipCountZero = 3;
    public static final int retCheckDirectExport_EncoderDSIMismatch = 11;
    public static final int retCheckDirectExport_FilterApplied = 15;
    public static final int retCheckDirectExport_HasImageClip = 7;
    public static final int retCheckDirectExport_HasSpeedControl = 12;
    public static final int retCheckDirectExport_HasVideoLayer = 8;
    public static final int retCheckDirectExport_InvalidClipList = 2;
    public static final int retCheckDirectExport_InvalidHandle = 1;
    public static final int retCheckDirectExport_InvalidRotate = 14;
    public static final int retCheckDirectExport_InvalidVideoInfo = 4;
    public static final int retCheckDirectExport_NotStartIFrame = 6;
    public static final int retCheckDirectExport_OK = 0;
    public static final int retCheckDirectExport_SetClipEffect = 10;
    public static final int retCheckDirectExport_SetTransitionEffect = 9;
    public static final int retCheckDirectExport_UnmatchedVideoCodec = 5;
    public static final int retCheckDirectExport_UnsupportedCodec = 13;
    /* access modifiers changed from: private */
    public static int sExportVideoTrackUUIDMode = 0;
    private static boolean sLoadListAsync = true;
    private static int sNextId = 1;
    /* access modifiers changed from: private */
    public static nexEngineListener sTranscodeListener;
    /* access modifiers changed from: private */
    public static boolean sTranscodeMode;
    /* access modifiers changed from: private */
    public static nexProject sTranscodeProject;
    private static ExportProfile[] s_exportProfiles;
    ArrayList<AsyncTask<String, Void, com.nexstreaming.kminternal.kinemaster.utils.facedetect.a>> async_facedetect_worker_list_ = new ArrayList<>();
    private boolean bLetterBox = false;
    boolean bNeedScaling = false;
    /* access modifiers changed from: private */
    public boolean cacheSeekMode = false;
    /* access modifiers changed from: private */
    public boolean externalImageExportProcessing = false;
    private boolean facedetect_asyncmode = true;
    private int facedetect_syncclip_count = 1;
    private int facedetect_undetected_clip_cropping_mode = 0;
    private int lastCheckDirectExportOption = 0;
    private int lastSeekTime = 0;
    /* access modifiers changed from: private */
    public Set<nexOverlayItem> mActiveRenderLayers = new HashSet();
    private Context mAppContext;
    private List<NexAudioClip> mCachedNexAudioClips;
    private List<NexVisualClip> mCachedNexVisualClips;
    /* access modifiers changed from: private */
    public int mCurrentPlayTime;
    /* access modifiers changed from: private */
    public c mEditorListener = null;
    /* access modifiers changed from: private */
    public int mEncodeBitrate;
    /* access modifiers changed from: private */
    public int mEncodeHeight;
    /* access modifiers changed from: private */
    public long mEncodeMaxFileSize;
    /* access modifiers changed from: private */
    public int mEncodeWidth;
    private int mEnhancedCropMode;
    private int mEnhancedCropOutputHeight;
    private int mEnhancedCropOutputWidth;
    /* access modifiers changed from: private */
    public nexEngineListener mEventListener = null;
    /* access modifiers changed from: private */
    public String mExportFilePath;
    /* access modifiers changed from: private */
    public int mExportTotalTime;
    /* access modifiers changed from: private */
    public boolean mForceMixExportMode;
    /* access modifiers changed from: private */
    public int mId;
    private int mLastManualVolCtl = -1;
    private int mLastProjectFadeIn = -1;
    private int mLastProjectFadeOut = -1;
    private int mLastProjectVolume = -1;
    /* access modifiers changed from: private */
    public NexEditor.PlayState mPlayState = NexEditor.PlayState.IDLE;
    /* access modifiers changed from: private */
    public nexProject mProject = null;
    /* access modifiers changed from: private */
    public List<nexOverlayItem> mRenderInCurrentPass = new ArrayList();
    /* access modifiers changed from: private */
    public int mState = 0;
    private Surface mSurface;
    private SurfaceView mSurfaceView;
    /* access modifiers changed from: private */
    public NexEditor mVideoEditor;
    /* access modifiers changed from: private */
    public boolean m_layerLock = false;
    private NexEditor.c m_layerRenderCallback = new NexEditor.c() {
        public void a(LayerRenderer layerRenderer) {
            synchronized (nexEngine.this.m_layerRenderLock) {
                if (!nexEngine.this.m_layerLock) {
                    nexEngine.this.mRenderInCurrentPass.clear();
                    if (nexEngine.this.mProject != null) {
                        if (nexEngine.this.mProject.getOverlayItems() != null) {
                            if (nexEngine.this.mProject.getOverlayItems().size() > 0) {
                                int g = layerRenderer.g();
                                for (nexOverlayItem next : nexEngine.this.mProject.getOverlayItems()) {
                                    int startTime = next.getStartTime();
                                    int endTime = next.getEndTime();
                                    boolean contains = nexEngine.this.mActiveRenderLayers.contains(next);
                                    if (startTime <= g && endTime > g) {
                                        if (!contains) {
                                            nexEngine.this.mActiveRenderLayers.add(next);
                                            next.onRenderAwake(layerRenderer);
                                        }
                                        nexEngine.this.mRenderInCurrentPass.add(next);
                                    } else if (contains) {
                                        nexEngine.this.mActiveRenderLayers.remove(next);
                                        next.onRenderAsleep(layerRenderer);
                                    }
                                }
                            }
                            Collections.sort(nexEngine.this.mRenderInCurrentPass, nexEngine.layerZOrderComparator);
                            int size = nexEngine.this.mRenderInCurrentPass.size();
                            for (int i = 0; i < size; i++) {
                                ((nexOverlayItem) nexEngine.this.mRenderInCurrentPass.get(i)).onRender(layerRenderer);
                            }
                        }
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public Object m_layerRenderLock = new Object();
    /* access modifiers changed from: private */
    public OnSurfaceChangeListener m_onSurfaceChangeListener = null;

    /* renamed from: com.nexstreaming.nexeditorsdk.nexEngine$13  reason: invalid class name */
    static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] a = new int[OverlayCommand.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|41|42|(3:43|44|46)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|(2:1|2)|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(41:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0086 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0092 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x009e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00bd */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00c7 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00d1 */
        static {
            b = new int[FastPreviewOption.values().length];
            try {
                b[FastPreviewOption.normal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[FastPreviewOption.brightness.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[FastPreviewOption.contrast.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            b[FastPreviewOption.saturation.ordinal()] = 4;
            b[FastPreviewOption.adj_brightness.ordinal()] = 5;
            b[FastPreviewOption.adj_contrast.ordinal()] = 6;
            b[FastPreviewOption.adj_saturation.ordinal()] = 7;
            b[FastPreviewOption.tintColor.ordinal()] = 8;
            b[FastPreviewOption.cts.ordinal()] = 9;
            b[FastPreviewOption.adj_vignette.ordinal()] = 10;
            b[FastPreviewOption.adj_vignetteRange.ordinal()] = 11;
            b[FastPreviewOption.adj_sharpness.ordinal()] = 12;
            b[FastPreviewOption.customlut_clip.ordinal()] = 13;
            b[FastPreviewOption.customlut_power.ordinal()] = 14;
            a[OverlayCommand.clear.ordinal()] = 1;
            a[OverlayCommand.upload.ordinal()] = 2;
            a[OverlayCommand.lock.ordinal()] = 3;
            try {
                a[OverlayCommand.unlock.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static class ExportProfile {
        /* access modifiers changed from: private */
        public int mimeType;
        /* access modifiers changed from: private */
        public ProfileAndLevel[] proFileAndLevel;

        public int getMimeType() {
            return this.mimeType;
        }

        public ProfileAndLevel[] getProFileAndLevel() {
            return (ProfileAndLevel[]) this.proFileAndLevel.clone();
        }

        public boolean isSupported(int i, int i2) {
            for (ProfileAndLevel profileAndLevel : this.proFileAndLevel) {
                if (profileAndLevel.getProfile() == i && profileAndLevel.getLevel() <= i2) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "ExportProfile{mimeType=" + this.mimeType + ", proFileAndLevel=" + Arrays.toString(this.proFileAndLevel) + '}';
        }
    }

    public enum FastPreviewOption {
        normal,
        brightness,
        contrast,
        saturation,
        adj_brightness,
        adj_contrast,
        adj_saturation,
        tintColor,
        cts,
        adj_vignette,
        adj_vignetteRange,
        adj_sharpness,
        customlut_clip,
        customlut_power
    }

    public static abstract class OnAutoTrimRatioResultListener {
        public abstract void onAutoTrimRatioResult(int i, int[] iArr, int[] iArr2);
    }

    public static abstract class OnAutoTrimResultListener {
        public abstract void onAutoTrimResult(int i, int[] iArr);
    }

    public static abstract class OnCaptureListener {
        public abstract void onCapture(Bitmap bitmap);

        public abstract void onCaptureFail(nexErrorCode nexerrorcode);
    }

    public static abstract class OnCompletionListener {
        public abstract void onComplete(int i);
    }

    public static abstract class OnSeekCompletionListener {
        public abstract void onSeekComplete(int i, int i2, int i3);
    }

    public static abstract class OnSurfaceChangeListener {
        public abstract void onSurfaceChanged();
    }

    private enum OverlayCommand {
        clear,
        upload,
        lock,
        unlock
    }

    public class OverlayPreviewBuilder {
        private nexOverlayItem mItem;

        private OverlayPreviewBuilder(int i) {
            for (nexOverlayItem next : nexEngine.this.mProject.getOverlayItems()) {
                if (next.getId() == i) {
                    this.mItem = next;
                }
            }
        }

        public void clear() {
            if (this.mItem != null) {
                this.mItem = null;
            }
        }

        public void display() {
            if (this.mItem != null) {
                nexEngine.this.fastPreview(FastPreviewOption.normal, 0);
            }
        }

        public OverlayPreviewBuilder setAlpha(float f) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setAlpha(f);
            }
            return this;
        }

        public OverlayPreviewBuilder setOutline(boolean z) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.showOutline(z);
            }
            return this;
        }

        public OverlayPreviewBuilder setPositionX(int i) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setPosition(i, nexoverlayitem.getPositionY());
            }
            return this;
        }

        public OverlayPreviewBuilder setPositionY(int i) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setPosition(nexoverlayitem.getPositionX(), i);
            }
            return this;
        }

        public OverlayPreviewBuilder setRotateX(int i) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setRotate(i, nexoverlayitem.getRotateY(), this.mItem.getRotateZ());
            }
            return this;
        }

        public OverlayPreviewBuilder setRotateY(int i) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setRotate(nexoverlayitem.getRotateX(), i, this.mItem.getRotateZ());
            }
            return this;
        }

        public OverlayPreviewBuilder setRotateZ(int i) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setRotate(nexoverlayitem.getRotateX(), this.mItem.getRotateY(), i);
            }
            return this;
        }

        public OverlayPreviewBuilder setScaleX(float f) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setScale(f, nexoverlayitem.getScaledY());
            }
            return this;
        }

        public OverlayPreviewBuilder setScaleY(float f) {
            nexOverlayItem nexoverlayitem = this.mItem;
            if (nexoverlayitem != null) {
                nexoverlayitem.setScale(nexoverlayitem.getScaledX(), f);
            }
            return this;
        }
    }

    public static class ProfileAndLevel implements Cloneable {
        private int level;
        private int profile;

        private ProfileAndLevel(int i, int i2) {
            this.profile = i;
            this.level = i2;
        }

        /* access modifiers changed from: protected */
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public int getLevel() {
            return this.level;
        }

        public int getProfile() {
            return this.profile;
        }

        public String toString() {
            return "ProfileAndLevel{level=" + this.level + ", profile=" + this.profile + '}';
        }
    }

    private class a extends NexEditor.v {
        private nexExternalExportProvider b;
        private nexExportListener c;

        public a(nexExternalExportProvider nexexternalexportprovider, nexExportListener nexexportlistener) {
            this.b = nexexternalexportprovider;
            this.c = nexexportlistener;
        }

        public void a() {
            this.b.OnLastProcess();
        }

        public boolean a(int i) {
            nexExportListener nexexportlistener;
            nexErrorCode nexerrorcode = nexErrorCode.EXPORT_WRITER_START_FAIL;
            if (i == -3) {
                nexerrorcode = nexErrorCode.EXPORT_WRITER_INIT_FAIL;
            } else if (i == -2) {
                nexerrorcode = nexErrorCode.EXPORT_USER_CANCEL;
            } else if (i == -1) {
                nexerrorcode = nexErrorCode.INVALID_STATE;
            } else if (i == 0) {
                nexerrorcode = nexErrorCode.NONE;
            }
            boolean OnEnd = this.b.OnEnd(i);
            if (i < 0 && (nexexportlistener = this.c) != null) {
                nexexportlistener.onExportFail(nexerrorcode);
            }
            nexExportListener nexexportlistener2 = this.c;
            if (nexexportlistener2 != null) {
                nexexportlistener2.onExportDone((Bitmap) null);
            } else if (nexEngine.this.mEventListener != null) {
                nexEngine.this.mEventListener.onEncodingDone(i != 0, nexerrorcode.getValue());
            }
            boolean unused = nexEngine.this.externalImageExportProcessing = false;
            int unused2 = nexEngine.this.mState = 1;
            return OnEnd;
        }

        public boolean a(byte[] bArr, int i) {
            return this.b.OnPushData(i, bArr);
        }

        public int b() {
            return nexEngine.this.async_facedetect_worker_list_.size();
        }

        public void b(int i) {
            nexExportListener nexexportlistener = this.c;
            if (nexexportlistener != null) {
                nexexportlistener.onExportProgress(i);
            } else if (nexEngine.this.mEventListener != null) {
                nexEngine.this.mEventListener.onEncodingProgress(i);
            }
        }
    }

    public enum nexErrorCode {
        NONE(0),
        GENERAL(1),
        UNKNOWN(2),
        NO_ACTION(3),
        INVALID_INFO(4),
        INVALID_STATE(5),
        VERSION_MISMATCH(6),
        CREATE_FAILED(7),
        MEMALLOC_FAILED(8),
        ARGUMENT_FAILED(9),
        NOT_ENOUGH_NEMORY(10),
        EVENTHANDLER(11),
        FILE_IO_FAILED(12),
        FILE_INVALID_SYNTAX(13),
        FILEREADER_CREATE_FAIL(14),
        FILEWRITER_CREATE_FAIL(15),
        AUDIORESAMPLER_CREATE_FAIL(16),
        UNSUPPORT_FORMAT(17),
        FILEREADER_FAILED(18),
        PLAYSTART_FAILED(19),
        PLAYSTOP_FAILED(20),
        PROJECT_NOT_CREATE(21),
        PROJECT_NOT_OPEN(22),
        CODEC_INIT(23),
        RENDERER_INIT(24),
        THEMESET_CREATE_FAIL(25),
        ADD_CLIP_FAIL(26),
        ENCODE_VIDEO_FAIL(27),
        INPROGRESS_GETCLIPINFO(28),
        THUMBNAIL_BUSY(29),
        UNSUPPORT_MIN_DURATION(30),
        UNSUPPORT_MAX_RESOLUTION(31),
        UNSUPPORT_MIN_RESOLUTION(32),
        UNSUPPORT_VIDEIO_PROFILE(33),
        UNSUPPORT_VIDEO_LEVEL(34),
        UNSUPPORT_VIDEO_FPS(35),
        TRANSCODING_BUSY(36),
        TRANSCODING_NOT_SUPPORTED_FORMAT(37),
        TRANSCODING_USER_CANCEL(38),
        TRANSCODING_NOT_ENOUGHT_DISK_SPACE(39),
        TRANSCODING_CODEC_FAILED(40),
        EXPORT_WRITER_INVAILED_HANDLE(41),
        EXPORT_WRITER_INIT_FAIL(42),
        EXPORT_WRITER_START_FAIL(43),
        EXPORT_AUDIO_DEC_INIT_FAIL(44),
        EXPORT_VIDEO_DEC_INIT_FAIL(45),
        EXPORT_VIDEO_ENC_FAIL(46),
        EXPORT_VIDEO_RENDER_INIT_FAIL(47),
        EXPORT_NOT_ENOUGHT_DISK_SPACE(48),
        UNSUPPORT_AUDIO_PROFILE(49),
        THUMBNAIL_INIT_FAIL(50),
        UNSUPPORT_AUDIO_CODEC(51),
        UNSUPPORT_VIDEO_CODEC(52),
        HIGHLIGHT_FILEREADER_INIT_ERROR(53),
        HIGHLIGHT_TOO_SHORT_CONTENTS(54),
        HIGHLIGHT_CODEC_INIT_ERROR(55),
        HIGHLIGHT_CODEC_DECODE_ERROR(56),
        HIGHLIGHT_RENDER_INIT_ERROR(57),
        HIGHLIGHT_WRITER_INIT_ERROR(58),
        HIGHLIGHT_WRITER_WRITE_ERROR(59),
        HIGHLIGHT_GET_INDEX_ERROR(60),
        HIGHLIGHT_USER_CANCEL(61),
        GETCLIPINFO_USER_CANCEL(62),
        DIRECTEXPORT_CLIPLIST_ERROR(63),
        DIRECTEXPORT_CHECK_ERROR(64),
        DIRECTEXPORT_FILEREADER_INIT_ERROR(65),
        DIRECTEXPORT_FILEWRITER_INIT_ERROR(66),
        DIRECTEXPORT_DEC_INIT_ERROR(67),
        DIRECTEXPORT_DEC_INIT_SURFACE_ERROR(68),
        DIRECTEXPORT_DEC_DECODE_ERROR(69),
        DIRECTEXPORT_ENC_INIT_ERROR(70),
        DIRECTEXPORT_ENC_ENCODE_ERROR(71),
        DIRECTEXPORT_ENC_INPUT_SURFACE_ERROR(72),
        DIRECTEXPORT_ENC_FUNCTION_ERROR(73),
        DIRECTEXPORT_ENC_DSI_DIFF_ERROR(74),
        DIRECTEXPORT_ENC_FRAME_CONVERT_ERROR(75),
        DIRECTEXPORT_RENDER_INIT_ERROR(76),
        DIRECTEXPORT_WRITER_WRITE_ERROR(77),
        DIRECTEXPORT_WRITER_UNKNOWN_ERROR(78),
        FASTPREVIEW_USER_CANCEL(79),
        FASTPREVIEW_CLIPLIST_ERROR(80),
        FASTPREVIEW_FIND_CLIP_ERROR(81),
        FASTPREVIEW_FIND_READER_ERROR(82),
        FASTPREVIEW_VIDEO_RENDERER_ERROR(83),
        FASTPREVIEW_DEC_INIT_SURFACE_ERROR(84),
        HW_NOT_ENOUGH_MEMORY(85),
        EXPORT_USER_CANCEL(86),
        FASTPREVIEW_DEC_INIT_ERROR(87),
        FASTPREVIEW_FILEREADER_INIT_ERROR(88),
        FASTPREVIEW_TIME_ERROR(89),
        FASTPREVIEW_RENDER_INIT_ERROR(90),
        FASTPREVIEW_OUTPUTSURFACE_INIT_ERROR(91),
        FASTPREVIEW_BUSY(92),
        CODEC_DECODE(93),
        RENDERER_AUDIO(94);
        
        private final int errno;

        private nexErrorCode(int i) {
            this.errno = i;
        }

        public static nexErrorCode fromValue(int i) {
            for (nexErrorCode nexerrorcode : values()) {
                if (nexerrorcode.getValue() == i) {
                    return nexerrorcode;
                }
            }
            return null;
        }

        public int getValue() {
            return this.errno;
        }
    }

    public enum nexPlayState {
        NONE(0),
        IDLE(1),
        RUN(2),
        RECORD(3);
        
        private int mValue;

        private nexPlayState(int i) {
            this.mValue = i;
        }

        public static nexPlayState fromValue(int i) {
            for (nexPlayState nexplaystate : values()) {
                if (nexplaystate.getValue() == i) {
                    return nexplaystate;
                }
            }
            return null;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public enum nexUndetectedFaceCrop {
        NONE(0),
        ZOOM(1);
        
        private int mValue;

        private nexUndetectedFaceCrop(int i) {
            this.mValue = i;
        }

        public static nexUndetectedFaceCrop fromValue(int i) {
            for (nexUndetectedFaceCrop nexundetectedfacecrop : values()) {
                if (nexundetectedfacecrop.getValue() == i) {
                    return nexundetectedfacecrop;
                }
            }
            return null;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public nexEngine(Context context) {
        int i = sNextId;
        sNextId = i + 1;
        this.mId = i;
        Log.d(TAG, "[" + this.mId + "] nexEngine create");
        this.mAppContext = context;
        this.mVideoEditor = EditorGlobal.a();
        NexEditor.a(nexApplicationConfig.getAspectProfile().getWidth(), nexApplicationConfig.getAspectProfile().getHeight(), nexApplicationConfig.getOverlayCoordinateMode());
        this.mVideoEditor.a(nexApplicationConfig.getScreenMode());
        this.mVideoEditor.a(this.m_layerRenderCallback);
        if (this.mVideoEditor.y() == 1) {
            this.mVideoEditor.e(true);
        } else {
            this.mVideoEditor.e(false);
        }
    }

    nexEngine(Context context, boolean z) {
        int i = sNextId;
        sNextId = i + 1;
        this.mId = i;
        Log.d(TAG, "[" + this.mId + "] nexEngine create internal");
        this.mAppContext = context;
        this.mVideoEditor = EditorGlobal.a();
        NexEditor.a(nexApplicationConfig.getAspectProfile().getWidth(), nexApplicationConfig.getAspectProfile().getHeight(), nexApplicationConfig.getOverlayCoordinateMode());
        this.mVideoEditor.a(nexApplicationConfig.getScreenMode());
        this.mVideoEditor.a(this.m_layerRenderCallback);
        if (z) {
            setMark();
        }
    }

    private void applyCropSpeed() {
    }

    private static int bsearch(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = (i2 + length) / 2;
            if (iArr[i3] == i) {
                return i3;
            }
            if (iArr[i3] < i) {
                i2 = i3 + 1;
            } else {
                length = i3 - 1;
            }
        }
        if (i2 > 0) {
            return i2 - 1;
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        if (r8.size() > 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007e, code lost:
        if (r9.size() > 0) goto L_0x0082;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0084  */
    private boolean checkUpdateProject(List<NexVisualClip> list, List<NexAudioClip> list2) {
        boolean z;
        List<NexVisualClip> list3 = this.mCachedNexVisualClips;
        int i = 0;
        boolean z2 = true;
        if (list3 != null) {
            if (list3.size() == list.size()) {
                int size = list.size();
                int i2 = 0;
                while (i2 < size) {
                    if (!this.mCachedNexVisualClips.get(i2).equals(list.get(i2))) {
                        Log.d(TAG, "checkUpdateProject video not equals");
                    } else {
                        i2++;
                    }
                }
                z = false;
                if (!z) {
                    List<NexAudioClip> list4 = this.mCachedNexAudioClips;
                    if (list4 != null) {
                        if (list4.size() == list2.size()) {
                            int size2 = list2.size();
                            while (true) {
                                if (i >= size2) {
                                    break;
                                } else if (!this.mCachedNexAudioClips.get(i).equals(list2.get(i))) {
                                    Log.d(TAG, "checkUpdateProject audio not equals");
                                    break;
                                } else {
                                    i++;
                                }
                            }
                        } else {
                            Log.d(TAG, "checkUpdateProject audio diff size");
                        }
                        if (z2) {
                            this.mCachedNexVisualClips = list;
                            this.mCachedNexAudioClips = list2;
                        }
                        return z2;
                    }
                }
                z2 = z;
                if (z2) {
                }
                return z2;
            }
            Log.d(TAG, "checkUpdateProject video diff size");
        }
        z = true;
        if (!z) {
        }
        z2 = z;
        if (z2) {
        }
        return z2;
    }

    private void clearList() {
        Log.i(TAG, "[" + this.mId + "]clearList()");
        if (sLoadListAsync) {
            this.mVideoEditor.b((NexVisualClip[]) null, (NexAudioClip[]) null, 0);
            this.mVideoEditor.asyncDrawInfoList((NexDrawInfo[]) null, (NexDrawInfo[]) null);
            return;
        }
        this.mVideoEditor.a((NexVisualClip[]) null, (NexAudioClip[]) null, 0);
        this.mVideoEditor.asyncDrawInfoList((NexDrawInfo[]) null, (NexDrawInfo[]) null);
    }

    private void defaultFaceDetectSetting() {
        this.facedetect_asyncmode = true;
        this.facedetect_syncclip_count = 1;
        this.facedetect_undetected_clip_cropping_mode = 0;
    }

    private void encodeEffectOptions(StringBuilder sb, Map<String, String> map) {
        try {
            boolean z = true;
            for (Map.Entry next : map.entrySet()) {
                if (z) {
                    z = false;
                } else {
                    sb.append('&');
                }
                sb.append(URLEncoder.encode((String) next.getKey(), Keyczar.DEFAULT_ENCODING));
                sb.append("=");
                sb.append(URLEncoder.encode((String) next.getValue(), Keyczar.DEFAULT_ENCODING));
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    private boolean fastPreviewStart(int i, int i2, int i3, int i4) {
        this.mVideoEditor.fastPreviewStart(i, i2, i3, i4);
        return true;
    }

    @Deprecated
    private boolean fastPreviewStop() {
        this.mVideoEditor.fastPreviewStop();
        return true;
    }

    @Deprecated
    private boolean fastPreviewTime(int i) {
        this.mVideoEditor.fastPreviewTime(i);
        return true;
    }

    public static ExportProfile[] getExportProfile() {
        MediaCodecInfo mediaCodecInfo;
        int i;
        MediaCodecInfo[] mediaCodecInfoArr;
        char c;
        int i2 = 0;
        if (Build.VERSION.SDK_INT < 21) {
            return new ExportProfile[0];
        }
        if (s_exportProfiles == null) {
            MediaCodecList mediaCodecList = new MediaCodecList(0);
            ArrayList arrayList = new ArrayList();
            MediaCodecInfo[] codecInfos = mediaCodecList.getCodecInfos();
            int length = codecInfos.length;
            int i3 = 0;
            while (i3 < length) {
                MediaCodecInfo mediaCodecInfo2 = codecInfos[i3];
                if (mediaCodecInfo2.isEncoder()) {
                    String[] supportedTypes = mediaCodecInfo2.getSupportedTypes();
                    int i4 = i2;
                    while (i4 < supportedTypes.length) {
                        if (supportedTypes[i4].equalsIgnoreCase("video/avc") || supportedTypes[i4].equalsIgnoreCase("video/hevc") || supportedTypes[i4].equalsIgnoreCase("video/mp4v-es")) {
                            ExportProfile exportProfile = new ExportProfile();
                            boolean equalsIgnoreCase = supportedTypes[i4].equalsIgnoreCase("video/avc");
                            int i5 = ExportCodec_AVC;
                            if (equalsIgnoreCase) {
                                int unused = exportProfile.mimeType = ExportCodec_AVC;
                            } else if (supportedTypes[i4].equalsIgnoreCase("video/hevc")) {
                                int unused2 = exportProfile.mimeType = ExportCodec_HEVC;
                            } else if (supportedTypes[i4].equalsIgnoreCase("video/mp4v-es")) {
                                int unused3 = exportProfile.mimeType = ExportCodec_MPEG4V;
                            }
                            Log.d(TAG, "codec name=" + mediaCodecInfo2.getName());
                            try {
                                MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo2.getCapabilitiesForType(supportedTypes[i4]);
                                SparseIntArray sparseIntArray = new SparseIntArray();
                                MediaCodecInfo.CodecProfileLevel[] codecProfileLevelArr = capabilitiesForType.profileLevels;
                                int length2 = codecProfileLevelArr.length;
                                while (i2 < length2) {
                                    MediaCodecInfo.CodecProfileLevel codecProfileLevel = codecProfileLevelArr[i2];
                                    mediaCodecInfoArr = codecInfos;
                                    i = length;
                                    if (exportProfile.mimeType == i5) {
                                        try {
                                            int i6 = codecProfileLevel.profile;
                                            mediaCodecInfo = mediaCodecInfo2;
                                            int i7 = 1;
                                            if (i6 != 1) {
                                                i7 = i6 != 2 ? (i6 == 8 || i6 == 16 || i6 == 32 || i6 == 64) ? 4 : 0 : 2;
                                            }
                                            if (i7 != 0) {
                                                try {
                                                    Log.d(TAG, "codec profile=" + i7 + ", level=" + codecProfileLevel.level);
                                                    if (sparseIntArray.get(i7) < codecProfileLevel.level) {
                                                        sparseIntArray.put(i7, codecProfileLevel.level);
                                                    }
                                                } catch (Exception e) {
                                                    e = e;
                                                    Log.wtf(TAG, e);
                                                    i4++;
                                                    codecInfos = mediaCodecInfoArr;
                                                    length = i;
                                                    mediaCodecInfo2 = mediaCodecInfo;
                                                    i2 = 0;
                                                }
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            mediaCodecInfo = mediaCodecInfo2;
                                            Log.wtf(TAG, e);
                                            i4++;
                                            codecInfos = mediaCodecInfoArr;
                                            length = i;
                                            mediaCodecInfo2 = mediaCodecInfo;
                                            i2 = 0;
                                        }
                                    } else {
                                        mediaCodecInfo = mediaCodecInfo2;
                                        if (exportProfile.mimeType == 268502016) {
                                            int i8 = codecProfileLevel.profile;
                                            if (i8 != 0) {
                                                Log.d(TAG, "codec profile=" + i8 + ", level=" + codecProfileLevel.level);
                                                if (sparseIntArray.get(i8) < codecProfileLevel.level) {
                                                    sparseIntArray.put(i8, codecProfileLevel.level);
                                                }
                                            }
                                        } else {
                                            c = 256;
                                            if (exportProfile.mimeType == 268566784) {
                                                int i9 = codecProfileLevel.profile;
                                                if (i9 != 0) {
                                                    Log.d(TAG, "codec profile=" + i9 + ", level=" + codecProfileLevel.level);
                                                    if (sparseIntArray.get(i9) < codecProfileLevel.level) {
                                                        sparseIntArray.put(i9, codecProfileLevel.level);
                                                    }
                                                }
                                            }
                                            i2++;
                                            char c2 = c;
                                            codecInfos = mediaCodecInfoArr;
                                            length = i;
                                            mediaCodecInfo2 = mediaCodecInfo;
                                            i5 = ExportCodec_AVC;
                                        }
                                    }
                                    c = 256;
                                    i2++;
                                    char c22 = c;
                                    codecInfos = mediaCodecInfoArr;
                                    length = i;
                                    mediaCodecInfo2 = mediaCodecInfo;
                                    i5 = ExportCodec_AVC;
                                }
                                mediaCodecInfoArr = codecInfos;
                                i = length;
                                mediaCodecInfo = mediaCodecInfo2;
                                ProfileAndLevel[] unused4 = exportProfile.proFileAndLevel = new ProfileAndLevel[sparseIntArray.size()];
                                for (int i10 = 0; i10 < sparseIntArray.size(); i10++) {
                                    exportProfile.proFileAndLevel[i10] = new ProfileAndLevel(sparseIntArray.keyAt(i10), sparseIntArray.valueAt(i10));
                                }
                                arrayList.add(exportProfile);
                            } catch (Exception e3) {
                                e = e3;
                                mediaCodecInfoArr = codecInfos;
                                i = length;
                                mediaCodecInfo = mediaCodecInfo2;
                                Log.wtf(TAG, e);
                                i4++;
                                codecInfos = mediaCodecInfoArr;
                                length = i;
                                mediaCodecInfo2 = mediaCodecInfo;
                                i2 = 0;
                            }
                        } else {
                            mediaCodecInfoArr = codecInfos;
                            i = length;
                            mediaCodecInfo = mediaCodecInfo2;
                        }
                        i4++;
                        codecInfos = mediaCodecInfoArr;
                        length = i;
                        mediaCodecInfo2 = mediaCodecInfo;
                        i2 = 0;
                    }
                }
                i3++;
                codecInfos = codecInfos;
                length = length;
                i2 = 0;
            }
            s_exportProfiles = new ExportProfile[arrayList.size()];
            for (int i11 = 0; i11 < arrayList.size(); i11++) {
                s_exportProfiles[i11] = (ExportProfile) arrayList.get(i11);
            }
        }
        return s_exportProfiles;
    }

    private NexEditor.v getExternalExport(nexExportFormat nexexportformat, String str, String str2, nexExportListener nexexportlistener) {
        if (str2 != null) {
            Object module = nexExternalModuleManager.getInstance().getModule(str2);
            if (module != null && nexExternalExportProvider.class.isInstance(module)) {
                nexExternalExportProvider nexexternalexportprovider = (nexExternalExportProvider) module;
                if (nexexternalexportprovider.OnPrepare(nexexportformat)) {
                    return new a(nexexternalexportprovider, nexexportlistener);
                }
            }
            return null;
        }
        nexExternalExportProvider nexexternalexportprovider2 = (nexExternalExportProvider) nexExternalModuleManager.getInstance().getModule(str, nexExternalExportProvider.class);
        if (nexexternalexportprovider2 == null || !nexexternalexportprovider2.OnPrepare(nexexportformat)) {
            return null;
        }
        return new a(nexexternalexportprovider2, nexexportlistener);
    }

    private int getOverlayVideoCount() {
        nexProject nexproject = this.mProject;
        int i = 0;
        if (nexproject == null) {
            return 0;
        }
        for (nexOverlayItem isVideo : nexproject.getOverlayItems()) {
            if (isVideo.isVideo()) {
                i++;
            }
        }
        return i;
    }

    private boolean isSetProject(boolean z) {
        nexProject nexproject;
        nexProject nexproject2 = this.mProject;
        if (nexproject2 != null && nexproject2.getTotalClipCount(true) > 0) {
            return true;
        }
        if (sTranscodeMode && (nexproject = sTranscodeProject) != null && nexproject.getTotalClipCount(true) > 0) {
            return true;
        }
        if (!z) {
            return false;
        }
        throw new ProjectNotAttachedException();
    }

    private boolean loadClipToEngine(List<NexVisualClip> list, List<NexAudioClip> list2, boolean z, boolean z2, int i) {
        boolean z3 = true;
        if (z2) {
            this.mCachedNexVisualClips = list;
            this.mCachedNexAudioClips = list2;
            Log.d(TAG, "loadClipToEngine update force");
        } else if (checkUpdateProject(list, list2)) {
            Log.d(TAG, "loadClipToEngine update loadlist call");
        } else {
            Log.d(TAG, "loadClipToEngine No update");
            z3 = false;
        }
        if (z3) {
            NexVisualClip[] nexVisualClipArr = new NexVisualClip[this.mCachedNexVisualClips.size()];
            int size = this.mCachedNexAudioClips.size();
            if (size != 0) {
                NexAudioClip[] nexAudioClipArr = new NexAudioClip[size];
                if (z) {
                    this.mVideoEditor.b((NexVisualClip[]) this.mCachedNexVisualClips.toArray(nexVisualClipArr), (NexAudioClip[]) this.mCachedNexAudioClips.toArray(nexAudioClipArr), i);
                } else {
                    this.mVideoEditor.a((NexVisualClip[]) this.mCachedNexVisualClips.toArray(nexVisualClipArr), (NexAudioClip[]) this.mCachedNexAudioClips.toArray(nexAudioClipArr), i);
                }
            } else if (z) {
                this.mVideoEditor.b((NexVisualClip[]) this.mCachedNexVisualClips.toArray(nexVisualClipArr), (NexAudioClip[]) null, i);
            } else {
                this.mVideoEditor.a((NexVisualClip[]) this.mCachedNexVisualClips.toArray(nexVisualClipArr), (NexAudioClip[]) null, i);
            }
        } else {
            this.mVideoEditor.clearProject();
        }
        return z3;
    }

    /* access modifiers changed from: private */
    public void loadEffectsInEditor(boolean z) {
        loadEffectsInEditor_usingAssetPackageManager(z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v6, types: [int] */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    private void loadEffectsInEditor_usingAssetPackageManager(boolean z) {
        HashSet hashSet = new HashSet();
        Log.d(TAG, "TranscoderMode =" + sTranscodeMode);
        ? r2 = 0;
        if (sTranscodeMode) {
            nexProject nexproject = this.mProject;
        } else if (this.mProject.getTemplateApplyMode() == 3) {
            List<nexDrawInfo> topDrawInfo = this.mProject.getTopDrawInfo();
            if (topDrawInfo != null && topDrawInfo.size() > 0) {
                for (nexDrawInfo effectID : topDrawInfo) {
                    hashSet.add(effectID.getEffectID());
                }
            }
        } else {
            boolean templageOverlappedTransitionMode = this.mProject.getTemplageOverlappedTransitionMode();
            List<nexClip> primaryItems = this.mProject.getPrimaryItems();
            while (r2 < this.mProject.getTotalClipCount(true)) {
                String id = primaryItems.get(r2).getClipEffect(true).getId();
                if (!(id == null || id.compareToIgnoreCase("none") == 0 || hashSet.contains(id))) {
                    hashSet.add(id);
                }
                String id2 = primaryItems.get(r2).getTransitionEffect(true).getId();
                if (!(id2 == null || id2.compareToIgnoreCase("none") == 0 || hashSet.contains(id2))) {
                    hashSet.add(id2);
                }
                r2++;
            }
            r2 = templageOverlappedTransitionMode;
        }
        com.nexstreaming.app.common.nexasset.assetpackage.c.a().a((Iterable<String>) hashSet, this.mVideoEditor, z, r2);
        com.nexstreaming.app.common.nexasset.assetpackage.c.a().a((Iterable<String>) hashSet, this.mVideoEditor, z);
    }

    @Deprecated
    public static void prepareSurfaceSetToNull(boolean z) {
        NexEditor.c(z);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0257  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0289  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x03d6  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x03e1  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x03fb  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0406  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x041d  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x04b1  */
    public int resolveProject(boolean z, boolean z2) {
        int i;
        int i2;
        NexDrawInfo[] nexDrawInfoArr;
        NexDrawInfo[] nexDrawInfoArr2;
        nexProject nexproject;
        String str;
        char c;
        boolean z3;
        int clipType;
        int i3;
        ArrayList arrayList;
        int i4;
        int i5;
        int i6;
        int aVSyncTime;
        int i7;
        nexProject nexproject2 = this.mProject;
        if (sTranscodeMode) {
            nexproject2 = sTranscodeProject;
        }
        nexProject nexproject3 = nexproject2;
        if (this.mForceMixExportMode) {
            this.mForceMixExportMode = false;
        }
        if (nexproject3 == null) {
            Log.i(TAG, "[" + this.mId + "]resolveProject() Project is null");
            clearList();
            this.mCachedNexVisualClips = null;
            this.mCachedNexAudioClips = null;
            return 0;
        }
        nexproject3.updateProject();
        this.mVideoEditor.setBaseFilterRenderItem(nexproject3.getLetterboxEffect());
        List<nexClip> primaryItems = nexproject3.getPrimaryItems();
        int size = primaryItems.size();
        if (size == 0) {
            Log.i(TAG, "[" + this.mId + "]resolveProject() Project[" + nexproject3.getId() + "] clip is zero");
            clearList();
            this.mCachedNexVisualClips = null;
            this.mCachedNexAudioClips = null;
            return 0;
        }
        Log.i(TAG, "[" + this.mId + "]resolveProject() Project[" + nexproject3.getId() + "]");
        int overlayVideoCount = getOverlayVideoCount();
        ArrayList<NexAudioClip> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        int i8 = 0;
        int i9 = 30001;
        int i10 = 1;
        int i11 = 0;
        while (i11 < size) {
            nexClip nexclip = primaryItems.get(i11);
            if (i11 == 0) {
                str = nexproject3.getOpeningTitle();
                c = 1;
            } else if (i11 == size - 1) {
                str = nexproject3.getEndingTitle();
                c = 2;
            } else {
                c = 0;
                str = null;
            }
            NexVisualClip nexVisualClip = new NexVisualClip();
            for (nexDrawInfo next : nexclip.getDrawInfos()) {
                List<nexClip> list = primaryItems;
                next.setClipID(i10);
                arrayList5.add(next);
                primaryItems = list;
            }
            List<nexClip> list2 = primaryItems;
            nexVisualClip.mClipPath = nexclip.getRealPath();
            nexVisualClip.mClipID = i10;
            nexVisualClip.mWidth = nexclip.getWidth();
            nexVisualClip.mHeight = nexclip.getHeight();
            nexVisualClip.mAudioOnOff = nexclip.getAudioOnOff() ? 1 : 0;
            nexVisualClip.mBGMVolume = nexclip.getBGMVolume();
            nexVisualClip.mClipEffectID = nexclip.getTransitionEffect(true).getId();
            if (c == 2) {
                nexVisualClip.mClipEffectID = "none";
                nexVisualClip.mEffectDuration = 0;
            } else {
                nexVisualClip.mClipEffectID = nexclip.getTransitionEffect(true).getId();
                if (nexVisualClip.mClipEffectID.compareTo("none") == 0) {
                    nexVisualClip.mEffectDuration = 0;
                } else {
                    z3 = true;
                    nexVisualClip.mEffectDuration = nexclip.getTransitionEffect(true).getDuration();
                    nexVisualClip.mEffectOffset = nexclip.getTransitionEffect(z3).getOffset();
                    nexVisualClip.mEffectOverlap = nexclip.getTransitionEffect(z3).getOverlap();
                    nexVisualClip.mTitleEffectID = nexclip.getClipEffect(z3).getId();
                    if (nexVisualClip.mTitleEffectID.compareTo("none") == 0) {
                        nexVisualClip.mTitleEffectID = null;
                    }
                    nexVisualClip.mTitleStartTime = nexclip.mTitleEffectStartTime;
                    nexVisualClip.mTitleEndTime = nexclip.mTitleEffectEndTime;
                    clipType = nexclip.getClipType();
                    if (clipType != 1) {
                        nexVisualClip.mStartTime = nexclip.mStartTime;
                        nexVisualClip.mEndTime = nexclip.mEndTime;
                        nexVisualClip.mClipType = 1;
                        nexVisualClip.mTotalTime = nexclip.getImageClipDuration();
                        nexVisualClip.mSpeedControl = 100;
                    } else if (clipType == 4) {
                        nexVisualClip.mClipType = 4;
                        nexVisualClip.mExistAudio = nexclip.hasAudio() ? 1 : 0;
                        nexVisualClip.mExistVideo = nexclip.hasVideo() ? 1 : 0;
                        nexVisualClip.mTotalTime = nexclip.getTotalTime();
                        nexVisualClip.mTotalVideoTime = nexclip.getTotalTime();
                        nexVisualClip.mClipVolume = nexclip.getClipVolume();
                        if (!(nexclip.getVideoClipEdit().mTrimStartDuration == 0 && nexclip.getVideoClipEdit().mTrimEndDuration == 0)) {
                            nexVisualClip.mStartTrimTime = nexclip.getVideoClipEdit().mTrimStartDuration;
                            nexVisualClip.mEndTrimTime = nexclip.getVideoClipEdit().mTrimEndDuration;
                        }
                        nexVisualClip.mFreezeDuration = nexclip.getVideoClipEdit().mFreezeDuration;
                        nexVisualClip.mStartTime = nexclip.mStartTime;
                        nexVisualClip.mEndTime = nexclip.mEndTime;
                        nexVisualClip.mSpeedControl = nexclip.getVideoClipEdit().getSpeedControl();
                        nexVisualClip.mVoiceChanger = nexclip.getAudioEdit().getVoiceChangerFactor();
                        nexVisualClip.mCompressor = nexclip.getAudioEdit().getCompressor();
                        nexVisualClip.mPitchFactor = nexclip.getAudioEdit().getPitch();
                        nexVisualClip.mMusicEffector = nexclip.getAudioEdit().getMusicEffect();
                        nexVisualClip.mPanLeft = nexclip.getAudioEdit().getPanLeft();
                        nexVisualClip.mPanRight = nexclip.getAudioEdit().getPanRight();
                        nexVisualClip.mProcessorStrength = nexclip.getAudioEdit().getProcessorStrength();
                        nexVisualClip.mBassStrength = nexclip.getAudioEdit().getBassStrength();
                        nexVisualClip.mEnhancedAudioFilter = nexclip.getAudioEdit().getEnhancedAudioFilter();
                        nexVisualClip.mSlowMotion = nexclip.mPropertySlowVideoMode ? 1 : 0;
                    }
                    int i12 = i10 + 1;
                    int i13 = nexVisualClip.mEndTime;
                    nexVisualClip.mRotateState = nexclip.getRotateDegree();
                    nexVisualClip.mTitle = getEncodedEffectOptions(nexclip, str);
                    nexVisualClip.mVignette = nexclip.getVignetteEffect() ? 1 : 0;
                    i3 = this.mEnhancedCropMode;
                    if (i3 != 1) {
                        Rect rect = new Rect();
                        Rect rect2 = new Rect();
                        i6 = i13;
                        nexclip.getCrop().getStartPositionRaw(rect);
                        nexclip.getCrop().getEndPositionRaw(rect2);
                        i5 = size;
                        i4 = i12;
                        arrayList = arrayList5;
                        nexCrop.enhancedCrop(rect, nexclip.getWidth(), nexclip.getHeight(), (float) this.mEnhancedCropOutputWidth, (float) this.mEnhancedCropOutputHeight);
                        nexCrop.enhancedCrop(rect2, nexclip.getWidth(), nexclip.getHeight(), (float) this.mEnhancedCropOutputWidth, (float) this.mEnhancedCropOutputHeight);
                        nexVisualClip.mStartRect = new NexRectangle(rect.left, rect.top, rect.right, rect.bottom);
                        nexVisualClip.mEndRect = new NexRectangle(rect2.left, rect2.top, rect2.right, rect2.bottom);
                    } else {
                        i6 = i13;
                        i5 = size;
                        arrayList = arrayList5;
                        i4 = i12;
                        if (i3 == 2) {
                            nexVisualClip.mStartRect = new NexRectangle(0, 0, nexCrop.ABSTRACT_DIMENSION, nexCrop.ABSTRACT_DIMENSION);
                            nexVisualClip.mEndRect = new NexRectangle(0, 0, nexCrop.ABSTRACT_DIMENSION, nexCrop.ABSTRACT_DIMENSION);
                        } else if (nexclip.getCrop().m_rotation == 90 || nexclip.getCrop().m_rotation == 270) {
                            nexVisualClip.mStartRect = new NexRectangle(nexclip.getCrop().m_rotatedStartPositionLeft, nexclip.getCrop().m_rotatedStartPositionTop, nexclip.getCrop().m_rotatedStartPositionRight, nexclip.getCrop().m_rotatedStartPositionBottom);
                            nexVisualClip.mEndRect = new NexRectangle(nexclip.getCrop().m_rotatedEndPositionLeft, nexclip.getCrop().m_rotatedEndPositionTop, nexclip.getCrop().m_rotatedEndPositionRight, nexclip.getCrop().m_rotatedEndPositionBottom);
                        } else {
                            nexVisualClip.mStartRect = new NexRectangle(nexclip.getCrop().m_startPositionLeft, nexclip.getCrop().m_startPositionTop, nexclip.getCrop().m_startPositionRight, nexclip.getCrop().m_startPositionBottom);
                            nexVisualClip.mEndRect = new NexRectangle(nexclip.getCrop().m_endPositionLeft, nexclip.getCrop().m_endPositionTop, nexclip.getCrop().m_endPositionRight, nexclip.getCrop().m_endPositionBottom);
                        }
                    }
                    nexVisualClip.mBrightness = nexclip.getCombinedBrightness();
                    nexVisualClip.mSaturation = nexclip.getCombinedSaturation();
                    nexVisualClip.mContrast = nexclip.getCombinedContrast();
                    nexVisualClip.mTintcolor = nexclip.getTintColor();
                    nexVisualClip.mLUT = nexclip.getLUTId();
                    nexVisualClip.mCustomLUT_A = nexclip.getCustomLUTA();
                    nexVisualClip.mCustomLUT_B = nexclip.getCustomLUTB();
                    nexVisualClip.mCustomLUT_Power = nexclip.getCustomLUTPower();
                    if (nexclip.getAudioEnvelop().getVolumeEnvelopeTimeList() == null) {
                        nexVisualClip.mVolumeEnvelopeTime = nexclip.getAudioEnvelop().getVolumeEnvelopeTimeList();
                    } else {
                        nexVisualClip.mVolumeEnvelopeTime = new int[]{0, nexVisualClip.mEndTime - nexVisualClip.mStartTime};
                    }
                    if (nexclip.getAudioEnvelop().getVolumeEnvelopeLevelList() == null) {
                        nexVisualClip.mVolumeEnvelopeLevel = nexclip.getAudioEnvelop().getVolumeEnvelopeLevelList();
                    } else {
                        nexVisualClip.mVolumeEnvelopeLevel = new int[]{100, 100};
                    }
                    nexVisualClip.mMotionTracked = nexclip.isMotionTrackedVideo() ? 1 : 0;
                    arrayList4.add(nexVisualClip);
                    aVSyncTime = nexclip.getAVSyncTime();
                    if (aVSyncTime == 0) {
                        NexAudioClip nexAudioClip = new NexAudioClip();
                        int i14 = i9;
                        i9 = i14 + 1;
                        nexAudioClip.mClipID = i14;
                        nexAudioClip.mVisualClipID = nexVisualClip.mClipID;
                        nexAudioClip.mTotalTime = nexVisualClip.mTotalTime;
                        nexAudioClip.mClipPath = nexVisualClip.mClipPath;
                        nexAudioClip.mAudioOnOff = nexVisualClip.mAudioOnOff;
                        nexVisualClip.mAudioOnOff = 0;
                        nexAudioClip.mClipType = 3;
                        nexAudioClip.mClipVolume = nexVisualClip.mClipVolume;
                        nexVisualClip.mClipVolume = 0;
                        nexAudioClip.mAutoEnvelop = 0;
                        nexAudioClip.mSpeedControl = nexVisualClip.mSpeedControl;
                        int i15 = (int) ((((float) aVSyncTime) * ((float) nexAudioClip.mSpeedControl)) / 100.0f);
                        if (aVSyncTime <= 0) {
                            i7 = 0;
                            nexAudioClip.mStartTime = nexVisualClip.mStartTime;
                            nexAudioClip.mStartTrimTime = nexVisualClip.mStartTrimTime - aVSyncTime;
                        } else if (nexVisualClip.mStartTime == 0) {
                            nexAudioClip.mStartTime = nexVisualClip.mStartTime + i15;
                            nexAudioClip.mStartTrimTime = nexVisualClip.mStartTrimTime;
                            i7 = 0;
                        } else {
                            nexAudioClip.mStartTime = nexVisualClip.mStartTime;
                            if (nexVisualClip.mStartTrimTime == 0) {
                                nexAudioClip.mStartTime = nexVisualClip.mStartTime + i15;
                                i7 = 0;
                                nexAudioClip.mStartTrimTime = 0;
                            } else {
                                i7 = 0;
                                nexAudioClip.mStartTrimTime = nexVisualClip.mStartTrimTime - aVSyncTime;
                                if (nexAudioClip.mStartTrimTime < 0) {
                                    nexAudioClip.mStartTrimTime = 0;
                                }
                            }
                        }
                        nexAudioClip.mEndTrimTime = i7;
                        nexAudioClip.mEndTime = nexVisualClip.mEndTime;
                        nexAudioClip.mEndTrimTime = nexAudioClip.mTotalTime - (nexAudioClip.mStartTrimTime + ((int) ((((float) (nexAudioClip.mEndTime - nexAudioClip.mStartTime)) * ((float) nexVisualClip.mSpeedControl)) / 100.0f)));
                        if (nexAudioClip.mEndTrimTime < 0) {
                            nexAudioClip.mEndTrimTime = 0;
                        }
                        arrayList2.add(nexAudioClip);
                    } else {
                        int i16 = i9;
                    }
                    i11++;
                    i8 = i6;
                    size = i5;
                    i10 = i4;
                    arrayList5 = arrayList;
                    primaryItems = list2;
                }
            }
            z3 = true;
            nexVisualClip.mEffectOffset = nexclip.getTransitionEffect(z3).getOffset();
            nexVisualClip.mEffectOverlap = nexclip.getTransitionEffect(z3).getOverlap();
            nexVisualClip.mTitleEffectID = nexclip.getClipEffect(z3).getId();
            if (nexVisualClip.mTitleEffectID.compareTo("none") == 0) {
            }
            nexVisualClip.mTitleStartTime = nexclip.mTitleEffectStartTime;
            nexVisualClip.mTitleEndTime = nexclip.mTitleEffectEndTime;
            clipType = nexclip.getClipType();
            if (clipType != 1) {
            }
            int i122 = i10 + 1;
            int i132 = nexVisualClip.mEndTime;
            nexVisualClip.mRotateState = nexclip.getRotateDegree();
            nexVisualClip.mTitle = getEncodedEffectOptions(nexclip, str);
            nexVisualClip.mVignette = nexclip.getVignetteEffect() ? 1 : 0;
            i3 = this.mEnhancedCropMode;
            if (i3 != 1) {
            }
            nexVisualClip.mBrightness = nexclip.getCombinedBrightness();
            nexVisualClip.mSaturation = nexclip.getCombinedSaturation();
            nexVisualClip.mContrast = nexclip.getCombinedContrast();
            nexVisualClip.mTintcolor = nexclip.getTintColor();
            nexVisualClip.mLUT = nexclip.getLUTId();
            nexVisualClip.mCustomLUT_A = nexclip.getCustomLUTA();
            nexVisualClip.mCustomLUT_B = nexclip.getCustomLUTB();
            nexVisualClip.mCustomLUT_Power = nexclip.getCustomLUTPower();
            if (nexclip.getAudioEnvelop().getVolumeEnvelopeTimeList() == null) {
            }
            if (nexclip.getAudioEnvelop().getVolumeEnvelopeLevelList() == null) {
            }
            nexVisualClip.mMotionTracked = nexclip.isMotionTrackedVideo() ? 1 : 0;
            arrayList4.add(nexVisualClip);
            aVSyncTime = nexclip.getAVSyncTime();
            if (aVSyncTime == 0) {
            }
            i11++;
            i8 = i6;
            size = i5;
            i10 = i4;
            arrayList5 = arrayList;
            primaryItems = list2;
        }
        ArrayList<nexDrawInfo> arrayList6 = arrayList5;
        if (!sTranscodeMode) {
            if (overlayVideoCount > 0 && (nexproject = this.mProject) != null) {
                for (nexOverlayItem next2 : nexproject.getOverlayItems()) {
                    if (next2.isVideo()) {
                        NexVisualClip nexVisualClip2 = new NexVisualClip();
                        nexVisualClip2.mClipID = i10;
                        next2.mVideoEngineId = i10;
                        nexVisualClip2.mClipType = 7;
                        nexVisualClip2.mTotalTime = next2.getOverlayImage().getVideoClipInfo().getTotalTime();
                        nexVisualClip2.mStartTime = next2.getStartTime();
                        nexVisualClip2.mEndTime = next2.getEndTime();
                        if (next2.getStartTrimTime() == 0 && next2.getEndTrimTime() == 0) {
                            nexVisualClip2.mStartTrimTime = 0;
                            nexVisualClip2.mEndTrimTime = 0;
                        } else {
                            nexVisualClip2.mStartTrimTime = next2.getStartTrimTime();
                            nexVisualClip2.mEndTrimTime = next2.getEndTrimTime();
                        }
                        nexVisualClip2.mWidth = next2.getOverlayImage().getVideoClipInfo().getWidth();
                        nexVisualClip2.mHeight = next2.getOverlayImage().getVideoClipInfo().getHeight();
                        nexVisualClip2.mExistVideo = next2.getOverlayImage().getVideoClipInfo().hasVideo() ? 1 : 0;
                        nexVisualClip2.mExistAudio = next2.getOverlayImage().getVideoClipInfo().hasAudio() ? 1 : 0;
                        nexVisualClip2.mTitleStartTime = next2.getStartTime();
                        nexVisualClip2.mTitleEndTime = next2.getEndTime();
                        nexVisualClip2.mBGMVolume = 100;
                        nexVisualClip2.mAudioOnOff = next2.getAudioOnOff() ? 1 : 0;
                        nexVisualClip2.mClipVolume = next2.getVolume();
                        nexVisualClip2.mEffectDuration = 0;
                        nexVisualClip2.mClipEffectID = "none";
                        nexVisualClip2.mTitleEffectID = null;
                        nexVisualClip2.mStartRect = new NexRectangle(0, 0, nexCrop.ABSTRACT_DIMENSION, nexCrop.ABSTRACT_DIMENSION);
                        nexVisualClip2.mEndRect = new NexRectangle(0, 0, nexCrop.ABSTRACT_DIMENSION, nexCrop.ABSTRACT_DIMENSION);
                        nexVisualClip2.mClipPath = next2.getOverlayImage().getVideoClipInfo().getPath();
                        nexVisualClip2.mThumbnailPath = null;
                        nexVisualClip2.mRotateState = 0;
                        nexVisualClip2.mEffectOffset = 0;
                        nexVisualClip2.mEffectOverlap = 0;
                        nexVisualClip2.mSpeedControl = next2.getSpeedControl();
                        arrayList4.add(nexVisualClip2);
                        i10++;
                    }
                }
            }
            int i17 = 10001;
            nexClip backgroundMusic = nexproject3.getBackgroundMusic();
            if (backgroundMusic != null) {
                NexAudioClip nexAudioClip2 = new NexAudioClip();
                nexAudioClip2.mClipID = 10001;
                nexAudioClip2.mTotalTime = backgroundMusic.getTotalTime();
                nexAudioClip2.mClipPath = backgroundMusic.getRealPath();
                nexAudioClip2.mAudioOnOff = backgroundMusic.getAudioOnOff() ? 1 : 0;
                nexAudioClip2.mClipType = 3;
                nexAudioClip2.mClipVolume = (int) (nexproject3.getBGMMasterVolumeScale() * 200.0f);
                nexAudioClip2.mAutoEnvelop = 1;
                nexAudioClip2.mStartTime = nexproject3.mStartTimeBGM;
                nexAudioClip2.mStartTrimTime = nexproject3.mBGMTrimStartTime;
                if (nexproject3.mBGMTrimEndTime == 0) {
                    nexAudioClip2.mEndTrimTime = 0;
                } else {
                    nexAudioClip2.mEndTrimTime = nexAudioClip2.mTotalTime - nexproject3.mBGMTrimEndTime;
                    if (nexAudioClip2.mEndTrimTime < 0) {
                        nexAudioClip2.mEndTrimTime = 0;
                    }
                }
                int i18 = nexproject3.mBGMTrimEndTime - nexproject3.mBGMTrimStartTime;
                if (i18 <= 0) {
                    i18 = backgroundMusic.getTotalTime();
                }
                if (nexproject3.mLoopBGM) {
                    i18 = nexproject3.getTotalTime();
                }
                nexAudioClip2.mEndTime = i18 + nexAudioClip2.mStartTime;
                nexAudioClip2.mVolumeEnvelopeTime = backgroundMusic.getAudioEnvelop().getVolumeEnvelopeTimeList();
                nexAudioClip2.mVolumeEnvelopeLevel = backgroundMusic.getAudioEnvelop().getVolumeEnvelopeLevelList();
                arrayList3.add(nexAudioClip2);
                i17 = 10002;
            }
            for (NexAudioClip add : arrayList2) {
                arrayList3.add(add);
            }
            for (nexAudioItem next3 : nexproject3.getAudioItems()) {
                NexAudioClip nexAudioClip3 = new NexAudioClip();
                nexClip nexclip2 = next3.mClip;
                nexAudioClip3.mClipID = i17;
                nexAudioClip3.mTotalTime = nexclip2.getTotalTime();
                nexAudioClip3.mClipPath = nexclip2.getRealPath();
                nexAudioClip3.mAudioOnOff = nexclip2.getAudioOnOff() ? 1 : 0;
                nexAudioClip3.mClipType = 3;
                nexAudioClip3.mClipVolume = nexclip2.getClipVolume();
                nexAudioClip3.mStartTime = nexclip2.mStartTime;
                nexAudioClip3.mEndTime = nexclip2.mEndTime;
                nexAudioClip3.mStartTrimTime = next3.mTrimStartDuration;
                nexAudioClip3.mEndTrimTime = next3.mTrimEndDuration;
                nexAudioClip3.mSpeedControl = next3.getSpeedControl();
                nexAudioClip3.mVolumeEnvelopeTime = nexclip2.getAudioEnvelop().getVolumeEnvelopeTimeList();
                nexAudioClip3.mVolumeEnvelopeLevel = nexclip2.getAudioEnvelop().getVolumeEnvelopeLevelList();
                nexAudioClip3.mVoiceChanger = nexclip2.getAudioEdit().getVoiceChangerFactor();
                nexAudioClip3.mCompressor = nexclip2.getAudioEdit().getCompressor();
                nexAudioClip3.mPitchFactor = nexclip2.getAudioEdit().getPitch();
                nexAudioClip3.mMusicEffector = nexclip2.getAudioEdit().getMusicEffect();
                nexAudioClip3.mPanLeft = nexclip2.getAudioEdit().getPanLeft();
                nexAudioClip3.mPanRight = nexclip2.getAudioEdit().getPanRight();
                nexAudioClip3.mProcessorStrength = nexclip2.getAudioEdit().getProcessorStrength();
                nexAudioClip3.mBassStrength = nexclip2.getAudioEdit().getBassStrength();
                nexAudioClip3.mEnhancedAudioFilter = nexclip2.getAudioEdit().getEnhancedAudioFilter();
                arrayList3.add(nexAudioClip3);
                i17++;
            }
            int i19 = (nexproject3.getTemplateApplyMode() != 3 ? !loadClipToEngine(arrayList4, arrayList3, z, z2, 0) : !loadClipToEngine(arrayList4, arrayList3, z, true, 257)) ? 0 : 1;
            List<nexDrawInfo> topDrawInfo = nexproject3.getTopDrawInfo();
            if (topDrawInfo == null || topDrawInfo.size() <= 0) {
                nexDrawInfoArr = null;
            } else {
                Log.d(TAG, String.format("resolve Project for top drawInfo(%d) ++++++++++++++++++++", new Object[]{Integer.valueOf(topDrawInfo.size())}));
                nexDrawInfoArr = new NexDrawInfo[topDrawInfo.size()];
                int i20 = 0;
                for (nexDrawInfo next4 : topDrawInfo) {
                    NexDrawInfo nexDrawInfo = new NexDrawInfo();
                    nexDrawInfo.mID = next4.getID();
                    nexDrawInfo.mTrackID = next4.getClipID();
                    nexDrawInfo.mSubEffectID = next4.getSubEffectID();
                    nexDrawInfo.mEffectID = next4.getEffectID();
                    nexDrawInfo.mTitle = next4.getTitle();
                    nexDrawInfo.mIsTransition = next4.getIsTransition();
                    nexDrawInfo.mStartTime = next4.getStartTime();
                    nexDrawInfo.mEndTime = next4.getEndTime();
                    nexDrawInfoArr[i20] = nexDrawInfo;
                    i20++;
                    i8 = next4.getEndTime();
                }
                Log.d(TAG, String.format("resolve Project for top drawInfo(%d) --------------------", new Object[]{Integer.valueOf(topDrawInfo.size())}));
            }
            if (nexDrawInfoArr == null || arrayList6.size() <= 0) {
                nexDrawInfoArr2 = null;
            } else {
                Log.d(TAG, String.format("resolve Project for sub drawInfo(%d) ++++++++++++++++++++", new Object[]{Integer.valueOf(arrayList6.size())}));
                nexDrawInfoArr2 = new NexDrawInfo[arrayList6.size()];
                int i21 = 0;
                for (nexDrawInfo nexdrawinfo : arrayList6) {
                    NexDrawInfo nexDrawInfo2 = new NexDrawInfo();
                    nexDrawInfo2.mID = nexdrawinfo.getID();
                    nexDrawInfo2.mTrackID = nexdrawinfo.getClipID();
                    nexDrawInfo2.mSubEffectID = nexdrawinfo.getSubEffectID();
                    nexDrawInfo2.mEffectID = nexdrawinfo.getEffectID();
                    nexDrawInfo2.mTitle = nexdrawinfo.getTitle();
                    nexDrawInfo2.mStartTime = nexdrawinfo.getStartTime();
                    nexDrawInfo2.mEndTime = nexdrawinfo.getEndTime();
                    nexDrawInfo2.mRotateState = nexdrawinfo.getRotateState();
                    nexDrawInfo2.mUserRotateState = nexdrawinfo.getUserRotateState();
                    nexDrawInfo2.mTranslateX = nexdrawinfo.getUserTranslateX();
                    nexDrawInfo2.mTranslateY = nexdrawinfo.getUserTranslateY();
                    nexDrawInfo2.mBrightness = nexdrawinfo.getBrightness();
                    nexDrawInfo2.mContrast = nexdrawinfo.getContrast();
                    nexDrawInfo2.mSaturation = nexdrawinfo.getSaturation();
                    nexDrawInfo2.mTintcolor = nexdrawinfo.getTintcolor();
                    nexDrawInfo2.mLUT = nexdrawinfo.getLUT();
                    nexDrawInfo2.mCustomLUT_A = nexdrawinfo.getCustomLUTA();
                    nexDrawInfo2.mCustomLUT_B = nexdrawinfo.getCustomLUTB();
                    nexDrawInfo2.mCustomLUT_Power = nexdrawinfo.getCustomLUTPower();
                    nexDrawInfo2.mStartRect.setRect(nexdrawinfo.getStartRect().left, nexdrawinfo.getStartRect().top, nexdrawinfo.getStartRect().right, nexdrawinfo.getStartRect().bottom);
                    nexDrawInfo2.mEndRect.setRect(nexdrawinfo.getEndRect().left, nexdrawinfo.getEndRect().top, nexdrawinfo.getEndRect().right, nexdrawinfo.getEndRect().bottom);
                    nexDrawInfo2.mFaceRect.setRect(nexdrawinfo.getFaceRect().left, nexdrawinfo.getFaceRect().top, nexdrawinfo.getFaceRect().right, nexdrawinfo.getFaceRect().bottom);
                    nexDrawInfoArr2[i21] = nexDrawInfo2;
                    i21++;
                }
                Log.d(TAG, String.format("resolve Project for sub drawInfo(%d) --------------------", new Object[]{Integer.valueOf(arrayList6.size())}));
            }
            this.mVideoEditor.asyncDrawInfoList(nexDrawInfoArr, nexDrawInfoArr2);
            long projectAudioFadeInTime = (long) nexproject3.getProjectAudioFadeInTime();
            long projectAudioFadeOutTime = (long) nexproject3.getProjectAudioFadeOutTime();
            long j = projectAudioFadeInTime + projectAudioFadeOutTime;
            long j2 = (long) i8;
            if (j > j2) {
                projectAudioFadeInTime = (projectAudioFadeInTime * j2) / j;
                projectAudioFadeOutTime = j2 - projectAudioFadeInTime;
            }
            if (!(((long) this.mLastProjectFadeIn) == projectAudioFadeInTime && ((long) this.mLastProjectFadeOut) == projectAudioFadeOutTime)) {
                int i22 = (int) projectAudioFadeInTime;
                this.mLastProjectFadeIn = i22;
                int i23 = (int) projectAudioFadeOutTime;
                this.mLastProjectFadeOut = i23;
                this.mVideoEditor.setProjectVolumeFade(i22, i23);
            }
            int projectVolume = nexproject3.getProjectVolume();
            if (this.mLastProjectVolume != projectVolume) {
                this.mLastProjectVolume = projectVolume;
                this.mVideoEditor.setProjectVolume(projectVolume);
            }
            int manualVolumeControl = nexproject3.getManualVolumeControl();
            if (this.mLastManualVolCtl != manualVolumeControl) {
                this.mLastManualVolCtl = manualVolumeControl;
                this.mVideoEditor.setProjectManualVolumeControl(manualVolumeControl);
            }
            i = i19;
        } else if (loadClipToEngine(arrayList4, arrayList3, z, z2, 0)) {
            i2 = 1;
            i = 1;
            this.mState = i2;
            return i;
        } else {
            i = 0;
        }
        i2 = 1;
        this.mState = i2;
        return i;
    }

    /* access modifiers changed from: private */
    public void setEditorListener() {
        if (this.mEditorListener == null) {
            this.mEditorListener = new c() {
                public void a() {
                    if (!nexEngine.this.externalImageExportProcessing && nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onSetTimeIgnored();
                    }
                }

                public void a(int i) {
                    if (!nexEngine.this.externalImageExportProcessing) {
                        if (!nexEngine.sTranscodeMode) {
                            int unused = nexEngine.this.mCurrentPlayTime = i;
                            if (nexEngine.this.mEventListener == null) {
                                return;
                            }
                            if (nexEngine.this.mState == 2 || nexEngine.this.mPlayState == NexEditor.PlayState.RECORD) {
                                int access$800 = nexEngine.this.mExportTotalTime;
                                if (access$800 <= 0) {
                                    access$800 = nexEngine.this.mProject.getTotalTime();
                                }
                                int i2 = access$800 <= 0 ? 0 : (i * 100) / access$800;
                                Log.d(nexEngine.TAG, "[" + nexEngine.this.mId + "]export progress = " + i2 + ", duration=" + access$800 + ", currentTime=" + i);
                                if (i2 >= 0 && i2 <= 100) {
                                    nexEngine.this.mEventListener.onEncodingProgress(i2);
                                    return;
                                }
                                return;
                            }
                            nexEngine.this.mEventListener.onTimeChange(i);
                        } else if (nexEngine.sTranscodeListener != null) {
                            int access$8002 = nexEngine.this.mExportTotalTime;
                            if (access$8002 <= 0) {
                                access$8002 = nexEngine.sTranscodeProject.getTotalTime();
                            }
                            int i3 = access$8002 <= 0 ? i / 10 : (i * 100) / access$8002;
                            if (i3 >= 0 && i3 <= 100) {
                                nexEngine.sTranscodeListener.onEncodingProgress(i3);
                            }
                        }
                    }
                }

                public void a(int i, int i2) {
                    if (nexEngine.this.mEventListener != null) {
                        if (i > i2) {
                            i = 100;
                        }
                        nexEngine.this.mEventListener.onProgressThumbnailCaching(i, i2);
                    }
                }

                public void a(NexEditor.ErrorCode errorCode) {
                    if (!nexEngine.this.externalImageExportProcessing && nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onSetTimeFail(errorCode.getValue());
                    }
                }

                public void a(NexEditor.ErrorCode errorCode, int i) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onPlayFail(errorCode.getValue(), i);
                    }
                }

                public void a(NexEditor.ErrorCode errorCode, int i, int i2) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onFastPreviewStartDone(errorCode.getValue(), i, i2);
                    }
                }

                public void a(NexEditor.PlayState playState, NexEditor.PlayState playState2) {
                    Log.i(nexEngine.TAG, "[" + nexEngine.this.mId + "]onStateChange() oldState=" + playState + ", newState=" + playState2 + ",curState=" + nexEngine.this.mState + ", trans=" + nexEngine.sTranscodeMode);
                    if (playState == NexEditor.PlayState.NONE && playState2 == NexEditor.PlayState.IDLE) {
                        int unused = nexEngine.this.mState = 1;
                    } else {
                        boolean unused2 = nexEngine.this.cacheSeekMode = false;
                    }
                    if (!nexEngine.sTranscodeMode) {
                        NexEditor.PlayState unused3 = nexEngine.this.mPlayState = playState2;
                        if (nexEngine.this.mState == 2 && playState == NexEditor.PlayState.RECORD && playState2 != NexEditor.PlayState.RECORD) {
                            if (playState2 == NexEditor.PlayState.PAUSE) {
                                Log.d(nexEngine.TAG, "[" + nexEngine.this.mId + "]new State is " + playState2);
                            } else if (playState2 == NexEditor.PlayState.RESUME) {
                                Log.d(nexEngine.TAG, "[" + nexEngine.this.mId + "]new State is " + playState2);
                            } else {
                                int unused4 = nexEngine.this.mState = 1;
                            }
                        }
                        if (nexEngine.this.mEventListener != null) {
                            nexEngine.this.mEventListener.onStateChange(playState.getValue(), playState2.getValue());
                        }
                    } else if (nexEngine.this.mState == 2 && playState == NexEditor.PlayState.RECORD && playState2 != NexEditor.PlayState.RECORD && nexEngine.sTranscodeListener != null) {
                        nexEngine.sTranscodeListener.onStateChange(playState.getValue(), playState2.getValue());
                    }
                }

                public void a(boolean z) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onSeekStateChanged(z);
                    }
                }

                public void b() {
                    int unused = nexEngine.this.mState = 1;
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onPlayEnd();
                    }
                }

                public void b(int i) {
                    if (!nexEngine.this.externalImageExportProcessing) {
                        if (!(nexEngine.this.mState == 2 || nexEngine.this.mEventListener == null)) {
                            nexEngine.this.mEventListener.onSetTimeDone(i);
                        }
                        int unused = nexEngine.this.mCurrentPlayTime = i;
                    }
                }

                public void b(int i, int i2) {
                    if (nexEngine.this.mEventListener != null) {
                        if (i > i2) {
                            i = 100;
                        }
                        nexEngine.this.mEventListener.onEncodingProgress(i);
                    }
                }

                public void b(NexEditor.ErrorCode errorCode) {
                    int unused = nexEngine.this.mState = 1;
                    Log.i(nexEngine.TAG, "[" + nexEngine.this.mId + "]onEncodingDone() =" + errorCode + ", trans=" + nexEngine.sTranscodeMode + ", forceMix=" + nexEngine.this.mForceMixExportMode);
                    if (nexEngine.sTranscodeMode) {
                        if (nexEngine.sTranscodeListener != null) {
                            if (!errorCode.isError()) {
                                nexEngine.sTranscodeListener.onEncodingProgress(100);
                            }
                            nexEngine.sTranscodeListener.onEncodingDone(errorCode.isError(), errorCode.getValue());
                        }
                        boolean unused2 = nexEngine.sTranscodeMode = false;
                        int unused3 = nexEngine.this.resolveProject(true, true);
                    } else if (nexEngine.this.mEventListener != null) {
                        if (!errorCode.isError()) {
                            nexEngine.this.mEventListener.onEncodingProgress(100);
                        }
                        nexEngine.this.mEventListener.onEncodingDone(errorCode.isError(), errorCode.getValue());
                    }
                    int unused4 = nexEngine.this.mExportTotalTime = 0;
                }

                public void b(NexEditor.ErrorCode errorCode, int i) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onCheckDirectExport(i);
                    }
                }

                public void c() {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onPlayStart();
                    }
                }

                public void c(int i, int i2) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onPreviewPeakMeter(i, i2);
                    }
                }

                public void c(NexEditor.ErrorCode errorCode) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onEncodingDone(errorCode.isError(), errorCode.getValue());
                    }
                }

                public void d() {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onClipInfoDone();
                    }
                }

                public void d(NexEditor.ErrorCode errorCode) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onFastPreviewStopDone(errorCode.getValue());
                    }
                }

                public void e(NexEditor.ErrorCode errorCode) {
                    if (nexEngine.this.mEventListener != null) {
                        nexEngine.this.mEventListener.onFastPreviewTimeDone(errorCode.getValue());
                    }
                }
            };
            this.mVideoEditor.a(this.mEditorListener);
        }
    }

    public static void setExportVideoTrackUUID(boolean z) {
        Log.i(TAG, "setExportVideoTrackUUID()=" + z);
        if (z) {
            sExportVideoTrackUUIDMode = 1;
        } else {
            sExportVideoTrackUUIDMode = 0;
        }
    }

    public static void setLoadListAsync(boolean z) {
        sLoadListAsync = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        return;
     */
    private void setOverlays(OverlayCommand overlayCommand) {
        synchronized (this.m_layerRenderLock) {
            int i = AnonymousClass13.a[overlayCommand.ordinal()];
            if (i == 1) {
                this.mActiveRenderLayers.clear();
                b.a().b();
            } else if (i != 2) {
                if (i == 3) {
                    this.m_layerLock = true;
                } else if (i == 4) {
                    this.m_layerLock = false;
                }
            } else if (this.mProject != null) {
                this.mActiveRenderLayers.clear();
            }
        }
    }

    private int transcode(String str, int i, int i2, int i3, long j, final int i4, final int i5) {
        if (this.mState == 2) {
            Log.w(TAG, "[" + this.mId + "]already export state");
            return -1;
        } else if (!isSetProject(true)) {
            return -1;
        } else {
            setOverlays(OverlayCommand.upload);
            resolveProject(sLoadListAsync, true);
            this.mState = 2;
            this.mExportTotalTime = sTranscodeProject.getTotalTime();
            this.mExportFilePath = str;
            this.mEncodeWidth = i;
            this.mEncodeHeight = i2;
            this.mEncodeBitrate = i3;
            this.mEncodeMaxFileSize = j;
            this.mVideoEditor.a(this.mAppContext).onComplete(new Task.OnTaskEventListener() {
                public void onTaskEvent(Task task, Task.Event event) {
                    nexEngine.this.loadEffectsInEditor(true);
                    nexEngine.this.setEditorListener();
                    int i = i5;
                    int i2 = i != 90 ? i != 180 ? i != 270 ? 0 : 64 : 32 : 16;
                    if (nexEngine.this.bNeedScaling) {
                        nexEngine.this.bNeedScaling = false;
                        i2 |= nexEngine.ExportHEVCMainTierLevel6;
                    }
                    nexEngine.this.mVideoEditor.a(nexEngine.sExportVideoTrackUUIDMode, (byte[]) null);
                    nexEngine.this.mVideoEditor.a(nexEngine.this.mExportFilePath, nexEngine.this.mEncodeWidth, nexEngine.this.mEncodeHeight, nexEngine.this.mEncodeBitrate, nexEngine.this.mEncodeMaxFileSize, 0, false, i4, i2 | 4096).onFailure((Task.OnFailListener) new Task.OnFailListener() {
                        public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                            Log.e(nexEngine.TAG, "[" + nexEngine.this.mId + "]transcode fail!=" + taskError);
                        }
                    });
                }
            });
            return 0;
        }
    }

    private void undoForceMixProject() {
        if (this.mForceMixExportMode) {
            if (isSetProject(false)) {
                Log.d(TAG, "[" + this.mId + "]undoForceMixProject");
                resolveProject(true, true);
            }
            this.mForceMixExportMode = false;
        }
    }

    /* access modifiers changed from: private */
    public void updateFaceInfo2Clip(nexClip nexclip, int i, RectF rectF, int i2) {
        boolean z;
        Rect rect;
        nexClip nexclip2;
        String str;
        nexDrawInfo nexdrawinfo;
        nexClip nexclip3 = nexclip;
        RectF rectF2 = rectF;
        int i3 = i2;
        Rect rect2 = new Rect();
        Rect rect3 = new Rect();
        Rect rect4 = new Rect();
        int width = nexclip.getCrop().getWidth();
        int height = nexclip.getCrop().getHeight();
        int faceDetectSpeed = nexclip.getCrop().getFaceDetectSpeed();
        Log.d(TAG, "Face Detection speed: " + faceDetectSpeed);
        rect4.set(0, 0, width, height);
        float f = (float) width;
        float f2 = (float) height;
        rect3.set((int) (rectF2.left * f), (int) (rectF2.top * f2), (int) (rectF2.right * f), (int) (rectF2.bottom * f2));
        rect2.set((int) (rectF2.left * f), (int) (rectF2.top * f2), (int) (rectF2.right * f), (int) (rectF2.bottom * f2));
        char c = 2;
        if (rect3.isEmpty()) {
            rect3.set(0, 0, width, height);
            rect3.offset((int) ((((double) width) * Math.random()) / 4.0d), (int) ((((double) height) * Math.random()) / 4.0d));
            Log.d(TAG, "Face Detection Empty ");
            z = false;
        } else {
            int width2 = width - rect3.width();
            if (width2 >= 2) {
                int i4 = width2 / 8;
                rect3.left -= i4;
                rect3.right += i4;
                Log.d(TAG, "Face Detection width addSpace > 0");
            }
            int height2 = height - rect3.height();
            if (height2 >= 2) {
                int i5 = height2 / 8;
                rect3.top -= i5;
                rect3.bottom += i5;
                Log.d(TAG, "Face Detection height addSpace>0");
            }
            z = true;
        }
        String str2 = "Face Detection false  ";
        String str3 = "Face Detection true";
        if (nexclip.getDrawInfos() == null || nexclip.getDrawInfos().size() <= 0) {
            nexClip nexclip4 = nexclip;
            String str4 = str3;
            Rect rect5 = rect2;
            Rect rect6 = rect3;
            String str5 = str2;
            Rect rect7 = rect4;
            String str6 = str4;
            Rect rect8 = new Rect(rect5);
            Rect rect9 = new Rect(rect6);
            Rect rect10 = new Rect(rect7);
            if (rectF.isEmpty() || nexclip.getCrop().getEndPosionLock()) {
                nexclip.getCrop().getStartPositionRaw(rect6);
                nexclip.getCrop().getEndPositionRaw(rect7);
                this.mVideoEditor.a(i, nexclip.isFaceDetected() ? 1 : 0, rect6, rect7, rect7);
                nexclip4.setFaceDetectProcessed(false, rect7);
                return;
            }
            nexclip.getCrop().growToAspect(rect9, nexApplicationConfig.getAspectRatio());
            Rect rect11 = rect8;
            Rect rect12 = rect11;
            Rect rect13 = rect5;
            String str7 = str5;
            nexclip.getCrop().applyCropSpeed(rect10, rect9, width, height, faceDetectSpeed, nexclip.getProjectDuration());
            if (z) {
                nexclip.getCrop().shrinkToAspect(rect9, nexApplicationConfig.getAspectRatio());
                nexclip.getCrop().shrinkToAspect(rect10, nexApplicationConfig.getAspectRatio());
                Log.d(TAG, str6);
                if (rect12.top < rect10.top) {
                    int i6 = rect10.top - rect12.top;
                    rect10.top -= i6;
                    rect10.bottom -= i6;
                }
                if (this.bLetterBox) {
                    int i7 = (rect9.right - rect9.left) / 4;
                    rect9.left -= i7;
                    rect9.right += i7;
                    int i8 = (rect9.bottom - rect9.top) / 4;
                    rect9.top -= i8;
                    rect9.bottom += i8;
                    nexclip.getCrop().growToAspect(rect9, nexApplicationConfig.getAspectRatio());
                }
                nexclip.getCrop().setStartPosition(rect10);
                nexclip.getCrop().setEndPosition(rect9);
                nexclip.getCrop().setFacePosition(rect12);
                nexclip.getCrop().getStartPositionRaw(rect10);
                nexclip.getCrop().getEndPositionRaw(rect9);
                nexclip.getCrop().getFacePositionRaw(rect12);
            } else {
                if (i3 == 2) {
                    nexclip.getCrop().shrinkToAspect(rect9, nexApplicationConfig.getAspectRatio());
                    nexclip.getCrop().growToAspect(rect10, nexApplicationConfig.getAspectRatio());
                    nexclip.getCrop().setStartPosition(rect10);
                    nexclip.getCrop().setEndPosition(rect9);
                    nexclip.getCrop().setFacePosition(rect12);
                    nexclip.getCrop().getStartPositionRaw(rect10);
                    nexclip.getCrop().getEndPositionRaw(rect9);
                    nexclip.getCrop().getFacePositionRaw(rect12);
                } else {
                    nexclip.getCrop().shrinkToAspect(rect9, nexApplicationConfig.getAspectRatio());
                    nexclip.getCrop().shrinkToAspect(rect10, nexApplicationConfig.getAspectRatio());
                    nexclip.getCrop().setStartPosition(rect9);
                    nexclip.getCrop().setEndPosition(rect10);
                    nexclip.getCrop().setFacePosition(rect12);
                    nexclip.getCrop().getStartPositionRaw(rect9);
                    nexclip.getCrop().getEndPositionRaw(rect10);
                    nexclip.getCrop().getFacePositionRaw(rect12);
                }
                Log.d(TAG, str7);
            }
            this.mVideoEditor.a(i, z ? 1 : 0, rect10, rect9, rect12);
            nexclip2 = nexclip;
            rect = rect13;
        } else {
            for (nexDrawInfo next : nexclip.getDrawInfos()) {
                if (next.getFaceRect().isEmpty()) {
                    Rect rect14 = new Rect(rect2);
                    Rect rect15 = new Rect(rect3);
                    Rect rect16 = rect2;
                    Rect rect17 = new Rect(rect4);
                    if (rectF.isEmpty() || nexclip.getCrop().getEndPosionLock()) {
                        Rect rect18 = rect3;
                        Rect rect19 = rect4;
                        String str8 = str3;
                        char c2 = c;
                        nexDrawInfo nexdrawinfo2 = next;
                        String str9 = str2;
                        Rect rect20 = rect18;
                        nexclip.getCrop().getStartPositionRaw(rect20);
                        Rect rect21 = rect19;
                        nexclip.getCrop().getEndPositionRaw(rect21);
                        nexdrawinfo2.setStartRect(rect20);
                        nexdrawinfo2.setEndRect(rect21);
                        nexdrawinfo2.setFaceRect(rect21);
                        updateDrawInfo(nexdrawinfo2);
                        nexclip.setFaceDetectProcessed(false, rect21);
                        RectF rectF3 = rectF;
                        str3 = str8;
                        rect4 = rect21;
                        str2 = str9;
                        c = c2;
                        rect3 = rect20;
                        rect2 = rect16;
                    } else {
                        nexclip.getCrop().growToAspect(rect15, next.getRatio());
                        Rect rect22 = rect4;
                        String str10 = str3;
                        Rect rect23 = rect3;
                        String str11 = str2;
                        nexDrawInfo nexdrawinfo3 = next;
                        nexclip.getCrop().applyCropSpeed(rect17, rect15, width, height, faceDetectSpeed, nexclip.getProjectDuration());
                        if (z) {
                            nexclip.getCrop().shrinkToAspect(rect15, nexdrawinfo3.getRatio());
                            nexclip.getCrop().shrinkToAspect(rect17, nexdrawinfo3.getRatio());
                            Log.d(TAG, str10);
                            if (rect14.top < rect17.top) {
                                int i9 = rect17.top - rect14.top;
                                rect17.top -= i9;
                                rect17.bottom -= i9;
                            }
                            if (this.bLetterBox) {
                                int i10 = (rect15.right - rect15.left) / 4;
                                rect15.left -= i10;
                                rect15.right += i10;
                                int i11 = (rect15.bottom - rect15.top) / 4;
                                rect15.top -= i11;
                                rect15.bottom += i11;
                                nexclip.getCrop().growToAspect(rect15, nexdrawinfo3.getRatio());
                            }
                            nexclip.getCrop().setStartPosition(rect17);
                            nexclip.getCrop().setEndPosition(rect15);
                            nexclip.getCrop().setFacePosition(rect14);
                            nexclip.getCrop().getStartPositionRaw(rect17);
                            nexclip.getCrop().getEndPositionRaw(rect15);
                            nexclip.getCrop().getFacePositionRaw(rect14);
                            nexdrawinfo = nexdrawinfo3;
                            str = str11;
                        } else {
                            if (i3 == 2) {
                                nexclip.getCrop().shrinkToAspect(rect15, nexdrawinfo3.getRatio());
                                nexclip.getCrop().growToAspect(rect17, nexdrawinfo3.getRatio());
                                nexclip.getCrop().setStartPosition(rect17);
                                nexclip.getCrop().setEndPosition(rect15);
                                nexclip.getCrop().setFacePosition(rect14);
                                nexclip.getCrop().getStartPositionRaw(rect17);
                                nexclip.getCrop().getEndPositionRaw(rect15);
                                nexclip.getCrop().getFacePositionRaw(rect14);
                            } else {
                                nexclip.getCrop().shrinkToAspect(rect15, nexdrawinfo3.getRatio());
                                nexclip.getCrop().shrinkToAspect(rect17, nexdrawinfo3.getRatio());
                                nexclip.getCrop().setStartPosition(rect15);
                                nexclip.getCrop().setEndPosition(rect17);
                                nexclip.getCrop().setFacePosition(rect14);
                                nexclip.getCrop().getStartPositionRaw(rect15);
                                nexclip.getCrop().getEndPositionRaw(rect17);
                                nexclip.getCrop().getFacePositionRaw(rect14);
                            }
                            rect14.set(0, 0, 1, 1);
                            str = str11;
                            Log.d(TAG, str);
                            nexdrawinfo = nexdrawinfo3;
                        }
                        nexdrawinfo.setStartRect(rect15);
                        nexdrawinfo.setEndRect(rect17);
                        nexdrawinfo.setFaceRect(rect14);
                        updateDrawInfo(nexdrawinfo);
                        RectF rectF4 = rectF;
                        str3 = str10;
                        str2 = str;
                        rect4 = rect22;
                        rect2 = rect16;
                        c = 2;
                        rect3 = rect23;
                    }
                }
            }
            nexclip2 = nexclip;
            Rect rect24 = rect2;
            nexclip.getCrop().setFacePosition(rect24);
            nexclip.getCrop().getFacePositionRaw(rect24);
            rect = rect24;
        }
        nexclip2.setFaceDetectProcessed(z, rect);
    }

    @Deprecated
    public boolean KineMixExport(String str) {
        return false;
    }

    public int addUdta(int i, String str) {
        return this.mVideoEditor.addUDTA(i, str);
    }

    public int autoTrim(String str, int i, int i2, final OnAutoTrimRatioResultListener onAutoTrimRatioResultListener) {
        if (onAutoTrimRatioResultListener != null) {
            this.mVideoEditor.a((NexEditor.s) new NexEditor.s() {
                public int a(int i) {
                    onAutoTrimRatioResultListener.onAutoTrimRatioResult(i, (int[]) null, (int[]) null);
                    return 0;
                }

                public int a(int i, int[] iArr, int[] iArr2) {
                    onAutoTrimRatioResultListener.onAutoTrimRatioResult(0, iArr, iArr2);
                    return 0;
                }
            });
        }
        this.mVideoEditor.a(str, 1, i, 1, 0, i2, 1);
        return 0;
    }

    public int autoTrim(String str, boolean z, int i, int i2, int i3, OnAutoTrimResultListener onAutoTrimResultListener) {
        final OnAutoTrimResultListener onAutoTrimResultListener2 = onAutoTrimResultListener;
        if (onAutoTrimResultListener2 != null) {
            this.mVideoEditor.a((NexEditor.l) new NexEditor.l() {
                public int a(int i) {
                    onAutoTrimResultListener2.onAutoTrimResult(i, (int[]) null);
                    return 0;
                }

                public int a(int i, int[] iArr) {
                    onAutoTrimResultListener2.onAutoTrimResult(0, iArr);
                    return 0;
                }
            });
        }
        this.mVideoEditor.a(str, z ? 1 : 0, i, i2, i3, 0, 0);
        return 0;
    }

    public int autoTrimStop() {
        return this.mVideoEditor.z();
    }

    public OverlayPreviewBuilder buildOverlayPreview(int i) {
        return new OverlayPreviewBuilder(i);
    }

    @Deprecated
    public void cancelKineMixExport() {
    }

    public nexErrorCode captureCurrentFrame(final OnCaptureListener onCaptureListener) {
        return onCaptureListener == null ? nexErrorCode.ARGUMENT_FAILED : this.mVideoEditor.x() ? nexErrorCode.fromValue(this.mVideoEditor.a(this.lastSeekTime, (NexEditor.e) new NexEditor.e() {
            public void a(Bitmap bitmap) {
                onCaptureListener.onCapture(bitmap);
            }

            public void a(NexEditor.ErrorCode errorCode) {
                onCaptureListener.onCaptureFail(nexErrorCode.fromValue(errorCode.getValue()));
            }
        }).getValue()) : nexErrorCode.fromValue(this.mVideoEditor.a((NexEditor.e) new NexEditor.e() {
            public void a(Bitmap bitmap) {
                onCaptureListener.onCapture(bitmap);
            }

            public void a(NexEditor.ErrorCode errorCode) {
                onCaptureListener.onCaptureFail(nexErrorCode.fromValue(errorCode.getValue()));
            }
        }).getValue());
    }

    public int checkDirectExport() {
        return checkDirectExport(0);
    }

    public int checkDirectExport(int i) {
        if (!this.mProject.getOverlayItems().isEmpty()) {
            return 8;
        }
        setEditorListener();
        this.lastCheckDirectExportOption = i;
        return this.mVideoEditor.checkDirectExport(i);
    }

    @Deprecated
    public int checkKineMixExport(boolean z) {
        return 2;
    }

    @Deprecated
    public boolean checkKineMixExport() {
        return false;
    }

    @Deprecated
    public boolean checkKineMixExport(String str, String str2) {
        return false;
    }

    public boolean checkPFrameDirectExportSync(String str) {
        return this.mVideoEditor.checkPFrameDirectExportSync(str) == 0;
    }

    public void clearFaceDetectInfo() {
        nexProject nexproject = this.mProject;
        if (nexproject != null) {
            nexproject.clearFaceDetectInfo();
        }
    }

    public void clearOverlayCache() {
        setOverlays(OverlayCommand.clear);
    }

    public void clearProject() {
        clearList();
        this.mProject = null;
    }

    public int clearScreen() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor == null) {
            return 1;
        }
        nexEditor.r();
        return 0;
    }

    public void clearTrackCache() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.clearTrackCache();
            setOverlays(OverlayCommand.clear);
        }
    }

    public int clearUdta() {
        return this.mVideoEditor.clearUDTA();
    }

    public boolean directExport(String str, long j, long j2) {
        return directExport(str, j, j2, this.lastCheckDirectExportOption);
    }

    public boolean directExport(String str, long j, long j2, int i) {
        if (!isSetProject(true)) {
            return false;
        }
        this.mState = 2;
        if (str == null) {
            return false;
        }
        this.mExportTotalTime = this.mProject.getTotalTime();
        this.mExportFilePath = str;
        this.mEncodeMaxFileSize = j;
        setEditorListener();
        this.mVideoEditor.a(sExportVideoTrackUUIDMode, (byte[]) null);
        this.mVideoEditor.directExport(this.mExportFilePath, this.mEncodeMaxFileSize, j2, EditorGlobal.b("up"), i);
        return true;
    }

    public int export(String str, int i, int i2, int i3, long j, int i4) {
        return export(str, i, i2, i3, j, i4, export_audio_sampling_rate, 0, 0, export_fps, ExportCodec_AVC);
    }

    public int export(String str, int i, int i2, int i3, long j, int i4, int i5) {
        return export(str, i, i2, i3, j, i4, i5, 0, 0, export_fps, ExportCodec_AVC);
    }

    public int export(String str, int i, int i2, int i3, long j, int i4, int i5, int i6) {
        return export(str, i, i2, i3, j, i4, i5, 0, 0, i6, ExportCodec_AVC);
    }

    public int export(String str, int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.mState == 2) {
            Log.w(TAG, "[" + this.mId + "]already export state");
            return -1;
        } else if (!isSetProject(true)) {
            return -1;
        } else {
            stopAsyncFaceDetect();
            setOverlays(OverlayCommand.upload);
            resolveProject(sLoadListAsync, true);
            faceDetect_internal(this.facedetect_asyncmode, this.facedetect_syncclip_count, this.facedetect_undetected_clip_cropping_mode);
            this.mState = 2;
            setThumbnailRoutine(2);
            this.mExportTotalTime = this.mProject.getTotalTime();
            this.mExportFilePath = str;
            this.mEncodeWidth = i;
            this.mEncodeHeight = i2;
            this.mEncodeBitrate = i3;
            this.mEncodeMaxFileSize = j;
            final int i10 = i4;
            final int i11 = i5;
            final int i12 = i6;
            final int i13 = i7;
            final int i14 = i8;
            final int i15 = i9;
            this.mVideoEditor.a(this.mAppContext).onComplete(new Task.OnTaskEventListener() {
                public void onTaskEvent(Task task, Task.Event event) {
                    nexEngine.this.loadEffectsInEditor(true);
                    nexEngine.this.setEditorListener();
                    int i = i10;
                    int i2 = i != 90 ? i != 180 ? i != 270 ? 0 : 64 : 32 : 16;
                    if (nexEngine.this.bNeedScaling) {
                        nexEngine.this.bNeedScaling = false;
                        i2 |= nexEngine.ExportHEVCMainTierLevel6;
                    }
                    nexEngine.this.mVideoEditor.a((NexEditor.i) null);
                    nexEngine.this.mVideoEditor.a(nexEngine.sExportVideoTrackUUIDMode, (byte[]) null);
                    nexEngine.this.mVideoEditor.a(nexEngine.this.mExportFilePath, nexEngine.this.mEncodeWidth, nexEngine.this.mEncodeHeight, nexEngine.this.mEncodeBitrate, nexEngine.this.mEncodeMaxFileSize, 0, false, i11, i12, i13, i14, i15, i2).onFailure((Task.OnFailListener) new Task.OnFailListener() {
                        public void onFail(Task task, Task.Event event, Task.TaskError taskError) {
                            Log.e(nexEngine.TAG, "[" + nexEngine.this.mId + "]export fail!=" + taskError);
                        }
                    });
                }
            });
            return 0;
        }
    }

    public nexErrorCode export(nexExportFormat nexexportformat, nexExportListener nexexportlistener) {
        nexExportFormat nexexportformat2 = nexexportformat;
        final nexExportListener nexexportlistener2 = nexexportlistener;
        String string = nexexportformat2.getString(nexExportFormat.TAG_FORMAT_TYPE);
        if (string == null) {
            return nexErrorCode.ARGUMENT_FAILED;
        }
        if (string.startsWith("external-")) {
            if (this.mState == 2) {
                Log.w(TAG, "[" + this.mId + "]already external export state");
                return nexErrorCode.INVALID_STATE;
            }
            int parseInt = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_WIDTH));
            int parseInt2 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_HEIGHT));
            int parseInt3 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_INTERVAL_TIME));
            int parseInt4 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_START_TIME));
            int parseInt5 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_END_TIME));
            if (parseInt5 > this.mProject.getTotalTime()) {
                parseInt5 = this.mProject.getTotalTime();
            }
            if (parseInt4 > parseInt5) {
                return nexErrorCode.ARGUMENT_FAILED;
            }
            NexEditor.v externalExport = getExternalExport(nexexportformat2, string.substring(9), nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_UUID), nexexportlistener2);
            if (externalExport == null) {
                return nexErrorCode.DIRECTEXPORT_ENC_ENCODE_ERROR;
            }
            this.externalImageExportProcessing = true;
            if (NexEditor.ErrorCode.NONE == this.mVideoEditor.a(parseInt, parseInt2, parseInt3, parseInt4, parseInt5, externalExport)) {
                this.mState = 2;
                return nexErrorCode.NONE;
            }
            this.externalImageExportProcessing = false;
            externalExport.a();
            externalExport.a(-1);
            if (nexexportlistener2 != null) {
                nexexportlistener2.onExportFail(nexErrorCode.INVALID_STATE);
            }
        }
        if (string.compareTo("bitmap") == 0) {
            return nexexportlistener2 == null ? nexErrorCode.ARGUMENT_FAILED : nexErrorCode.fromValue(this.mVideoEditor.a(Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_WIDTH)), Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_HEIGHT)), Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_TAG)), (NexEditor.e) new NexEditor.e() {
                public void a(Bitmap bitmap) {
                    nexexportlistener2.onExportDone(bitmap);
                }

                public void a(NexEditor.ErrorCode errorCode) {
                    nexexportlistener2.onExportFail(nexErrorCode.fromValue(errorCode.getValue()));
                }
            }).getValue());
        } else if (string.compareTo("jpeg") == 0) {
            final String str = nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_PATH);
            int parseInt6 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_WIDTH));
            int parseInt7 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_HEIGHT));
            final int parseInt8 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_QUALITY));
            return (str == null || str.length() <= 0 || parseInt6 <= 0 || parseInt7 <= 0 || parseInt8 <= 0 || parseInt8 > 100) ? nexErrorCode.ARGUMENT_FAILED : nexErrorCode.fromValue(this.mVideoEditor.a(parseInt6, parseInt7, 0, (NexEditor.e) new NexEditor.e() {
                public void a(Bitmap bitmap) {
                    NexEditor.ErrorCode errorCode = NexEditor.ErrorCode.NONE;
                    File file = new File(str);
                    if (file.exists()) {
                        file.delete();
                    }
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, parseInt8, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        errorCode = NexEditor.ErrorCode.EXPORT_WRITER_INIT_FAIL;
                    }
                    nexExportListener nexexportlistener = nexexportlistener2;
                    if (nexexportlistener != null) {
                        nexexportlistener.onExportDone((Bitmap) null);
                    } else if (nexEngine.this.mEditorListener != null) {
                        nexEngine.this.mEditorListener.b(errorCode);
                    }
                }

                public void a(NexEditor.ErrorCode errorCode) {
                    nexExportListener nexexportlistener = nexexportlistener2;
                    if (nexexportlistener != null) {
                        nexexportlistener.onExportFail(nexErrorCode.fromValue(errorCode.getValue()));
                    } else if (nexEngine.this.mEditorListener != null) {
                        nexEngine.this.mEditorListener.b(errorCode);
                    }
                }
            }).getValue());
        } else if (string.compareTo("mp4") != 0) {
            return nexErrorCode.UNSUPPORT_FORMAT;
        } else {
            try {
                String str2 = nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_PATH);
                int parseInt9 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_WIDTH));
                int parseInt10 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_HEIGHT));
                int parseInt11 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_VIDEO_CODEC));
                int parseInt12 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_VIDEO_BITRATE));
                int parseInt13 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_VIDEO_PROFILE));
                int parseInt14 = Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_VIDEO_LEVEL));
                try {
                    if (export(str2, parseInt9, parseInt10, parseInt12, Long.parseLong(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_MAX_FILESIZE)), Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_VIDEO_ROTATE)), Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_AUDIO_SAMPLERATE)), parseInt13, parseInt14, Integer.parseInt(nexexportformat2.formats.get(nexExportFormat.TAG_FORMAT_VIDEO_FPS)), parseInt11) != 0) {
                        return nexErrorCode.UNKNOWN;
                    }
                    this.mVideoEditor.a((NexEditor.i) new NexEditor.i() {
                        public void a() {
                            nexexportlistener2.onExportDone((Bitmap) null);
                        }

                        public void a(int i) {
                            nexexportlistener2.onExportProgress((i * 100) / nexEngine.this.mProject.getTotalTime());
                        }

                        public void a(NexEditor.ErrorCode errorCode) {
                            nexexportlistener2.onExportFail(nexErrorCode.fromValue(errorCode.getValue()));
                        }
                    });
                    return nexErrorCode.NONE;
                } catch (Exception unused) {
                    return nexErrorCode.UNKNOWN;
                }
            } catch (Exception unused2) {
                return nexErrorCode.ARGUMENT_FAILED;
            }
        }
    }

    public nexErrorCode exportJpeg(String str, int i, int i2, int i3, final OnCaptureListener onCaptureListener) {
        return onCaptureListener == null ? nexErrorCode.ARGUMENT_FAILED : nexErrorCode.fromValue(this.mVideoEditor.a(i, i2, 0, (NexEditor.e) new NexEditor.e() {
            public void a(Bitmap bitmap) {
                onCaptureListener.onCapture(bitmap);
            }

            public void a(NexEditor.ErrorCode errorCode) {
                onCaptureListener.onCaptureFail(nexErrorCode.fromValue(errorCode.getValue()));
            }
        }).getValue());
    }

    public int exportNoException(String str, int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, int i8) {
        try {
            return export(str, i, i2, i3, j, i4, i5, i6, i7, i8, ExportCodec_AVC);
        } catch (Exception unused) {
            return -2;
        }
    }

    public int exportNoException(String str, int i, int i2, int i3, long j, int i4, int i5, int i6, int i7, int i8, int i9) {
        try {
            return export(str, i, i2, i3, j, i4, i5, i6, i7, i8, i9);
        } catch (Exception unused) {
            return -2;
        }
    }

    public int exportPause() {
        if (this.externalImageExportProcessing) {
            return -1;
        }
        return this.mVideoEditor.encodePause();
    }

    public int exportResume() {
        if (this.externalImageExportProcessing) {
            return -1;
        }
        return this.mVideoEditor.encodeResume();
    }

    public void exportSaveStop(final OnCompletionListener onCompletionListener) {
        if (!this.externalImageExportProcessing) {
            if (isSetProject(false)) {
                this.mState = 1;
                this.mVideoEditor.a(1, (NexEditor.f) new NexEditor.f() {
                    public void a(NexEditor.ErrorCode errorCode) {
                        onCompletionListener.onComplete(errorCode.getValue());
                        int unused = nexEngine.this.mState = 1;
                    }
                });
                return;
            }
            onCompletionListener.onComplete(21);
        }
    }

    @Deprecated
    public int faceDetect(boolean z, int i, nexUndetectedFaceCrop nexundetectedfacecrop) {
        this.facedetect_asyncmode = true;
        this.facedetect_syncclip_count = 0;
        this.facedetect_undetected_clip_cropping_mode = nexundetectedfacecrop.getValue();
        return (com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a() == null || this.facedetect_undetected_clip_cropping_mode == 0) ? 0 : 1;
    }

    public int faceDetect_for_seek(int i, int i2) {
        Log.d(TAG, String.format("Face Detection Mode = %d", new Object[]{Integer.valueOf(i2)}));
        if (i2 == 0) {
            return -1;
        }
        int totalClipCount = this.mProject.getTotalClipCount(true);
        for (int i3 = 0; i3 < totalClipCount; i3++) {
            nexClip clip = this.mProject.getClip(i3, true);
            if (clip.getClipType() == 1 && clip.mStartTime <= i && i <= clip.mEndTime && !clip.isAssetResource()) {
                com.nexstreaming.kminternal.kinemaster.utils.facedetect.a a2 = com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a(clip.getPath());
                if (a2 == null) {
                    a2 = new com.nexstreaming.kminternal.kinemaster.utils.facedetect.a(new File(clip.getPath()), true, getAppContext());
                    com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a(clip.getPath(), a2);
                }
                RectF rectF = new RectF();
                a2.a(rectF);
                updateFaceInfo2Clip(clip, i3 + 1, rectF, i2);
            }
        }
        return 0;
    }

    public int faceDetect_internal(boolean z, int i, int i2) {
        int i3;
        final int i4 = i2;
        Log.d(TAG, String.format("Face Detection Mode = %d", new Object[]{Integer.valueOf(i2)}));
        if (i4 == 0) {
            return -1;
        }
        int totalClipCount = this.mProject.getTotalClipCount(true);
        int i5 = 0;
        for (int i6 = 0; i6 < totalClipCount; i6++) {
            if (this.mProject.getClip(i6, true).getClipType() == 1) {
                i5++;
            }
        }
        if (true == z) {
            i5 = i;
        }
        int i7 = 0;
        while (i7 < totalClipCount) {
            int i8 = i7 + 1;
            Log.d(TAG, String.format("Face Detection ResetInfo clip ID = %d", new Object[]{Integer.valueOf(i8)}));
            if (this.mProject.getClip(i7, true).getClipType() == 1) {
                this.mVideoEditor.b(i8);
            }
            i7 = i8;
        }
        int i9 = 0;
        final int i10 = 0;
        while (i9 < totalClipCount) {
            RectF rectF = new RectF();
            int i11 = i9 + 1;
            Log.d(TAG, String.format("Face Detection sync clip ID = %d", new Object[]{Integer.valueOf(i11)}));
            nexClip clip = this.mProject.getClip(i9, true);
            if (clip.getClipType() == 1 && !clip.isFaceDetectProcessed() && !clip.isAssetResource()) {
                com.nexstreaming.kminternal.kinemaster.utils.facedetect.a a2 = com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a(clip.getPath());
                Log.d(TAG, "Face Detection sync total num =" + i5 + " continueClip num= " + i10);
                if (a2 == null) {
                    if (i9 > i5 - 1) {
                        break;
                    }
                    a2 = new com.nexstreaming.kminternal.kinemaster.utils.facedetect.a(new File(clip.getPath()), true, getAppContext());
                    com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a(clip.getPath(), a2);
                }
                a2.a(rectF);
                updateFaceInfo2Clip(clip, i11, rectF, i4);
                i10++;
            } else if (clip.getClipType() == 1) {
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                Rect rect3 = new Rect();
                Log.d(TAG, String.format("Face Detection skip clip ID = %d", new Object[]{Integer.valueOf(i11)}));
                if (clip.getDrawInfos() == null || clip.getDrawInfos().size() <= 0) {
                    clip.getCrop().getStartPositionRaw(rect);
                    clip.getCrop().getEndPositionRaw(rect3);
                    clip.getCrop().getFacePositionRaw(rect2);
                    NexEditor nexEditor = this.mVideoEditor;
                    boolean isFaceDetected = clip.isFaceDetected();
                    Rect rect4 = rect3;
                    boolean z2 = isFaceDetected;
                    i3 = i11;
                    nexEditor.a(i11, z2 ? 1 : 0, rect, rect4, rect2);
                } else {
                    for (nexDrawInfo next : clip.getDrawInfos()) {
                        if (next.getFaceRect().isEmpty()) {
                            clip.getCrop().getStartPositionRaw(rect);
                            clip.getCrop().getEndPositionRaw(rect3);
                            clip.getCrop().getFacePositionRaw(rect2);
                            next.setStartRect(rect);
                            next.setEndRect(rect3);
                            next.setFaceRect(rect2);
                            updateDrawInfo(next);
                            Log.d(TAG, String.format("Face Detection info update for draw info(clip ID = %d)", new Object[]{Integer.valueOf(i11)}));
                        }
                    }
                    i3 = i11;
                }
                i10++;
                i9 = i3;
            }
            i3 = i11;
            i9 = i3;
        }
        while (i10 < totalClipCount) {
            final nexClip clip2 = this.mProject.getClip(i10, true);
            if (clip2.getClipType() == 1 && !clip2.isFaceDetectProcessed() && !clip2.isAssetResource()) {
                Log.d(TAG, String.format("Face Detection async clip ID = %d", new Object[]{Integer.valueOf(i10 + 1)}));
                AnonymousClass11 r7 = new AsyncTask<String, Void, com.nexstreaming.kminternal.kinemaster.utils.facedetect.a>() {
                    Task.TaskError a = null;

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public com.nexstreaming.kminternal.kinemaster.utils.facedetect.a doInBackground(String... strArr) {
                        Log.d(nexEngine.TAG, "Face Detection async thread start =" + strArr[0]);
                        if (i10 == 0) {
                            nexEngine.this.mVideoEditor.e();
                        }
                        com.nexstreaming.kminternal.kinemaster.utils.facedetect.a a2 = com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a(strArr[0]);
                        if (a2 != null) {
                            return a2;
                        }
                        com.nexstreaming.kminternal.kinemaster.utils.facedetect.a aVar = new com.nexstreaming.kminternal.kinemaster.utils.facedetect.a(new File(strArr[0]), 1, nexEngine.this.getAppContext());
                        com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a(strArr[0], aVar);
                        return aVar;
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(com.nexstreaming.kminternal.kinemaster.utils.facedetect.a aVar) {
                        nexEngine.this.async_facedetect_worker_list_.remove(this);
                        Log.d(nexEngine.TAG, "Face Detection worker list size:" + nexEngine.this.async_facedetect_worker_list_.size());
                        RectF rectF = new RectF();
                        aVar.a(rectF);
                        nexEngine.this.updateFaceInfo2Clip(clip2, i10 + 1, rectF, i4);
                        Log.d(nexEngine.TAG, "Face Detection aync thread end =" + clip2.getPath());
                    }

                    /* access modifiers changed from: protected */
                    public void onCancelled() {
                        Log.d(nexEngine.TAG, "Face Detection was cancelled");
                    }
                };
                this.async_facedetect_worker_list_.add(r7);
                r7.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new String[]{clip2.getPath()});
            } else if (clip2.getClipType() == 1) {
                Rect rect5 = new Rect();
                Rect rect6 = new Rect();
                Rect rect7 = new Rect();
                int i12 = i10 + 1;
                Log.d(TAG, String.format("Face Detection skip clip ID = %d", new Object[]{Integer.valueOf(i12)}));
                if (clip2.getDrawInfos() == null || clip2.getDrawInfos().size() <= 0) {
                    clip2.getCrop().getStartPositionRaw(rect5);
                    clip2.getCrop().getEndPositionRaw(rect6);
                    clip2.getCrop().getFacePositionRaw(rect7);
                    this.mVideoEditor.a(i12, clip2.isFaceDetected() ? 1 : 0, rect5, rect6, rect7);
                } else {
                    for (nexDrawInfo next2 : clip2.getDrawInfos()) {
                        if (next2.getFaceRect().isEmpty()) {
                            clip2.getCrop().getStartPositionRaw(rect5);
                            clip2.getCrop().getEndPositionRaw(rect6);
                            clip2.getCrop().getFacePositionRaw(rect7);
                            next2.setStartRect(rect5);
                            next2.setEndRect(rect6);
                            next2.setFaceRect(rect7);
                            updateDrawInfo(next2);
                        }
                    }
                }
            }
            i10++;
        }
        return 0;
    }

    public void fastPreview(FastPreviewOption fastPreviewOption, int i) {
        NexEditor.FastPreviewOption fastPreviewOption2 = NexEditor.FastPreviewOption.normal;
        switch (fastPreviewOption) {
            case normal:
                fastPreviewOption2 = NexEditor.FastPreviewOption.normal;
                break;
            case brightness:
                fastPreviewOption2 = NexEditor.FastPreviewOption.brightness;
                break;
            case contrast:
                fastPreviewOption2 = NexEditor.FastPreviewOption.contrast;
                break;
            case saturation:
                fastPreviewOption2 = NexEditor.FastPreviewOption.saturation;
                break;
            case adj_brightness:
                fastPreviewOption2 = NexEditor.FastPreviewOption.adj_brightness;
                break;
            case adj_contrast:
                fastPreviewOption2 = NexEditor.FastPreviewOption.adj_contrast;
                break;
            case adj_saturation:
                fastPreviewOption2 = NexEditor.FastPreviewOption.adj_saturation;
                break;
            case tintColor:
                fastPreviewOption2 = NexEditor.FastPreviewOption.tintColor;
                break;
            case cts:
                fastPreviewOption2 = NexEditor.FastPreviewOption.cts;
                break;
            case adj_vignette:
                fastPreviewOption2 = NexEditor.FastPreviewOption.adj_vignette;
                break;
            case adj_vignetteRange:
                fastPreviewOption2 = NexEditor.FastPreviewOption.adj_vignetteRange;
                break;
            case adj_sharpness:
                fastPreviewOption2 = NexEditor.FastPreviewOption.adj_sharpness;
                break;
            case customlut_clip:
                fastPreviewOption2 = NexEditor.FastPreviewOption.customlut_clip;
                break;
            case customlut_power:
                fastPreviewOption2 = NexEditor.FastPreviewOption.customlut_power;
                break;
        }
        this.mVideoEditor.a(fastPreviewOption2, i);
    }

    public void fastPreviewCrop(Rect rect) {
        this.mVideoEditor.q().a(NexEditor.FastPreviewOption.nofx, 1).a(rect).a();
    }

    public void fastPreviewCustomlut(int i) {
        nexProject nexproject = this.mProject;
        if (nexproject != null) {
            fastPreviewCustomlut(nexproject.getClipPosition(this.mCurrentPlayTime) + 1, i);
        }
    }

    public void fastPreviewCustomlut(int i, int i2) {
        this.mVideoEditor.q().a(NexEditor.FastPreviewOption.customlut_clip, i).a(NexEditor.FastPreviewOption.customlut_power, i2).a();
    }

    public void fastPreviewEffect(int i, boolean z) {
        this.mVideoEditor.q().a(i).a(z).a();
    }

    public boolean forceMixExport(String str) {
        if (isSetProject(true)) {
            if (this.mProject.getClip(0, true).getClipType() != 4) {
                Log.d(TAG, "[" + this.mId + "]forceMixExport: no video clip.");
                return false;
            }
            nexProject nexproject = new nexProject();
            if (!this.mProject.getClip(0, true).hasAudio() || this.mProject.getClip(0, true).getAudioCodecType().contains("aac")) {
                String realPath = this.mProject.getClip(0, true).getRealPath();
                if (this.mVideoEditor.checkIDRStart(realPath) != 0) {
                    Log.d(TAG, "[" + this.mId + "]forceMixExport: idr finder start fail!");
                    return false;
                }
                int i = 0;
                int i2 = 0;
                while (i < this.mProject.getTotalClipCount(true)) {
                    if (realPath.compareTo(this.mProject.getClip(i, true).getRealPath()) == 0) {
                        nexproject.add(nexClip.dup(this.mProject.getClip(i, true)));
                        int startTrimTime = this.mProject.getClip(i, true).getVideoClipEdit().getStartTrimTime();
                        int endTrimTime = this.mProject.getClip(i, true).getVideoClipEdit().getEndTrimTime();
                        if (this.mProject.getClip(i, true).getVideoClipEdit().getSpeedControl() != 100) {
                            Log.d(TAG, "[" + this.mId + "]forceMixExport: set speed clip index=" + i);
                            i2 = 1;
                        }
                        int checkIDRTime = this.mVideoEditor.checkIDRTime(startTrimTime);
                        if (checkIDRTime == -1) {
                            Log.d(TAG, "[" + this.mId + "]forceMixExport: idr finder fail startTrimTime=" + startTrimTime);
                            this.mVideoEditor.checkIDREnd();
                            return false;
                        }
                        Log.d(TAG, "[" + this.mId + "]forceMixExport: startTrimTime=" + startTrimTime + ", endTrimTime=" + endTrimTime + ", new idrTime=" + checkIDRTime);
                        nexproject.getClip(i, true).getVideoClipEdit().setTrim(checkIDRTime, endTrimTime);
                        i++;
                    } else {
                        Log.d(TAG, "[" + this.mId + "]forceMixExport: [" + i + "] clip invaild path=" + realPath);
                        this.mVideoEditor.checkIDREnd();
                        return false;
                    }
                }
                this.mVideoEditor.checkIDREnd();
                nexProject nexproject2 = this.mProject;
                this.mProject = nexproject;
                try {
                    resolveProject(sLoadListAsync, true);
                    this.mForceMixExportMode = true;
                    this.mExportTotalTime = nexproject.getTotalTime();
                    this.mState = 2;
                    this.mExportFilePath = str;
                    this.mEncodeMaxFileSize = Long.MAX_VALUE;
                    setEditorListener();
                    this.mVideoEditor.a(sExportVideoTrackUUIDMode, (byte[]) null);
                    this.mVideoEditor.directExport(this.mExportFilePath, this.mEncodeMaxFileSize, (long) this.mProject.getTotalTime(), EditorGlobal.b("up"), i2);
                    this.mProject = nexproject2;
                    return true;
                } catch (Exception unused) {
                    this.mProject = nexproject2;
                }
            } else {
                Log.d(TAG, "[" + this.mId + "]forceMixExport: audio is not aac");
                return false;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Context getAppContext() {
        return this.mAppContext;
    }

    @Deprecated
    public int getAudioSessionID() {
        return this.mVideoEditor.d(true);
    }

    public int getBrightness() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            return nexEditor.getBrightness();
        }
        return 0;
    }

    public int getContrast() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            return nexEditor.getContrast();
        }
        return 0;
    }

    public int getCurrentPlayTimeTime() {
        return this.mCurrentPlayTime;
    }

    public int getDuration() {
        if (isSetProject(false)) {
            return this.mVideoEditor.getDuration() * 1000;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public String getEncodedEffectOptions(nexClip nexclip, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(nexclip.getClipEffect(true).getShowStartTime());
        sb.append(',');
        sb.append(nexclip.getClipEffect(true).getShowEndTime());
        sb.append('?');
        encodeEffectOptions(sb, nexclip.getTransitionEffect(true).getEffectOptions(str));
        sb.append('?');
        encodeEffectOptions(sb, nexclip.getClipEffect(true).getEffectOptions(str));
        return sb.toString();
    }

    public int[] getIDRSeekTabSync(nexClip nexclip) {
        ArrayList arrayList = new ArrayList();
        MediaInfo mediaInfo = nexclip.getMediaInfo();
        if (mediaInfo == null) {
            Log.d(TAG, "[" + this.mId + "]getIDRSeekTabSync() getinfo fail!");
            return null;
        }
        int[] d = mediaInfo.d();
        if (this.mVideoEditor.checkIDRStart(nexclip.getRealPath()) != 0) {
            Log.d(TAG, "[" + this.mId + "]getIDRSeekTabSync() checkIDRStart fail!");
            return null;
        }
        int i = -1;
        for (int i2 = 0; i2 < d.length; i2++) {
            int checkIDRTime = this.mVideoEditor.checkIDRTime(d[i2]);
            Log.d(TAG, "[" + this.mId + "]getIDRSeekTabSync() : seektab=" + d[i2] + ", idrTime=" + checkIDRTime);
            if (checkIDRTime < 0) {
                Log.d(TAG, "[" + this.mId + "]getIDRSeekTabSync() idrTime fail! seekTime=" + d[i2]);
                this.mVideoEditor.checkIDREnd();
                return null;
            }
            if (i != checkIDRTime) {
                arrayList.add(Integer.valueOf(checkIDRTime));
                i = checkIDRTime;
            }
        }
        this.mVideoEditor.checkIDREnd();
        int[] iArr = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
            Log.d(TAG, "[" + this.mId + "]getIDRSeekTabSync() : new seektab=" + iArr[i3]);
        }
        return iArr;
    }

    public int getLayerHeight() {
        return nexApplicationConfig.getAspectProfile().getHeight();
    }

    public int getLayerWidth() {
        return nexApplicationConfig.getAspectProfile().getWidth();
    }

    public boolean getLetterBox() {
        return this.bLetterBox;
    }

    public boolean getOverlayHitPoint(nexOverlayItem.HitPoint hitPoint) {
        for (nexOverlayItem next : this.mProject.getOverlayItems()) {
            if (next.getStartTime() <= hitPoint.mTime && next.getEndTime() > hitPoint.mTime) {
                return next.isPointInOverlayItem(hitPoint);
            }
        }
        return false;
    }

    public nexProject getProject() {
        return this.mProject;
    }

    public int getSaturation() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            return nexEditor.getSaturation();
        }
        return 0;
    }

    public int getSharpness() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            return nexEditor.getSharpness();
        }
        return 0;
    }

    public Bitmap getThumbnailCache(int i, int i2) {
        Bitmap n;
        if (!this.cacheSeekMode || (n = this.mVideoEditor.n(i)) == null) {
            return null;
        }
        int width = n.getWidth();
        int height = n.getHeight();
        Rect rect = new Rect(0, 0, width, height);
        if (i2 == 180) {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            canvas.rotate(180.0f, (float) (width / 2), (float) (height / 2));
            canvas.drawBitmap(n, rect, new Rect(0, 0, width, height), (Paint) null);
            return createBitmap;
        } else if (i2 == 0) {
            Bitmap createBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            new Canvas(createBitmap2).drawBitmap(n, rect, new Rect(0, 0, width, height), (Paint) null);
            return createBitmap2;
        } else if (i2 == 270) {
            Bitmap createBitmap3 = Bitmap.createBitmap(height, width, Bitmap.Config.RGB_565);
            Canvas canvas2 = new Canvas(createBitmap3);
            canvas2.rotate(90.0f, 0.0f, 0.0f);
            canvas2.drawBitmap(n, rect, new Rect(0, -height, width, 0), (Paint) null);
            return createBitmap3;
        } else if (i2 != 90) {
            return null;
        } else {
            Bitmap createBitmap4 = Bitmap.createBitmap(height, width, Bitmap.Config.RGB_565);
            Canvas canvas3 = new Canvas(createBitmap4);
            canvas3.rotate(270.0f, 0.0f, 0.0f);
            canvas3.drawBitmap(n, rect, new Rect(-width, 0, 0, height), (Paint) null);
            return createBitmap4;
        }
    }

    public nexEngineView getView() {
        return (nexEngineView) this.mVideoEditor.k();
    }

    public int getVignette() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            return nexEditor.getVignette();
        }
        return 0;
    }

    public int getVignetteRange() {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            return nexEditor.getVignetteRange();
        }
        return 0;
    }

    public boolean isCacheSeekMode() {
        return this.cacheSeekMode;
    }

    public void overlayLock(boolean z) {
        if (z) {
            setOverlays(OverlayCommand.lock);
        } else {
            setOverlays(OverlayCommand.unlock);
        }
    }

    public void pause() {
        if (isSetProject(false)) {
            this.mVideoEditor.s();
        }
    }

    public void play() {
        this.cacheSeekMode = false;
        if (!isSetProject(false)) {
            return;
        }
        if (this.mState == 2) {
            Log.w(TAG, "[" + this.mId + "]export state");
            return;
        }
        stopAsyncFaceDetect();
        setOverlays(OverlayCommand.upload);
        resolveProject(sLoadListAsync, true);
        faceDetect_internal(this.facedetect_asyncmode, this.facedetect_syncclip_count, this.facedetect_undetected_clip_cropping_mode);
        loadEffectsInEditor(false);
        setEditorListener();
        this.mVideoEditor.t();
    }

    public boolean play(boolean z) {
        this.cacheSeekMode = false;
        if (z) {
            try {
                play();
                return true;
            } catch (Exception unused) {
                return false;
            }
        } else {
            play();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void removeClip(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.a(i, (NexEditor.g) null);
        }
    }

    public void removeEditorListener() {
        if (this.mEditorListener != null) {
            this.mEditorListener = null;
        }
    }

    public void resume() {
        this.cacheSeekMode = false;
        if (isSetProject(false)) {
            undoForceMixProject();
            setEditorListener();
            setOverlays(OverlayCommand.upload);
            faceDetect_internal(this.facedetect_asyncmode, this.facedetect_syncclip_count, this.facedetect_undetected_clip_cropping_mode);
            this.mVideoEditor.t();
        }
    }

    public boolean reverseStart(String str, String str2, String str3, int i, int i2, int i3, long j, int i4, int i5, int i6) {
        int i7 = i5 - i4;
        if (i7 >= PERSIST_INTERVAL) {
            return this.mVideoEditor.reverseStart(str, str2, str3, i, i2, i3, j, i4, i5, i6) == 0;
        }
        throw new InvalidRangeException((int) PERSIST_INTERVAL, i7, true);
    }

    public boolean reverseStop() {
        return this.mVideoEditor.reverseStop() == 0;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a8  */
    public void runTranscodeMode(nexTranscode.Option option, String str, nexEngineListener nexenginelistener) {
        int i;
        if (sTranscodeMode) {
            nexenginelistener.onEncodingDone(true, nexErrorCode.TRANSCODING_BUSY.getValue());
        } else if (this.mState == 2) {
            nexenginelistener.onEncodingDone(true, nexErrorCode.TRANSCODING_BUSY.getValue());
        } else {
            nexClip supportedClip = nexClip.getSupportedClip(str);
            if (supportedClip == null) {
                nexenginelistener.onEncodingDone(true, nexErrorCode.TRANSCODING_NOT_SUPPORTED_FORMAT.getValue());
                return;
            }
            sTranscodeMode = true;
            sTranscodeProject = new nexProject();
            sTranscodeProject.add(supportedClip);
            sTranscodeListener = nexenginelistener;
            int i2 = 0;
            if (option.outputRotate != nexTranscode.Rotate.CW_0) {
                if (option.outputRotate == nexTranscode.Rotate.CW_90) {
                    i = 270;
                } else if (option.outputRotate == nexTranscode.Rotate.CW_180) {
                    i = 180;
                } else if (option.outputRotate == nexTranscode.Rotate.CW_270) {
                    i = 90;
                }
                if (i != 0) {
                    sTranscodeProject.getClip(0, true).setRotateDegree(i);
                }
                if (option.outputFitMode != 0) {
                    sTranscodeProject.getClip(0, true).getCrop().resetStartEndPosition();
                } else {
                    sTranscodeProject.getClip(0, true).getCrop().fitStartEndPosition(option.outputWidth, option.outputHeight);
                }
                resolveProject(true, true);
                if (option.outputRotateMeta != nexTranscode.Rotate.BYPASS) {
                    int rotateInMeta = 360 - supportedClip.getRotateInMeta();
                    if (rotateInMeta != 360) {
                        i2 = rotateInMeta;
                    }
                } else if (option.outputRotateMeta != nexTranscode.Rotate.CW_0) {
                    if (option.outputRotateMeta == nexTranscode.Rotate.CW_90) {
                        i2 = 90;
                    } else if (option.outputRotateMeta == nexTranscode.Rotate.CW_180) {
                        i2 = 180;
                    } else if (option.outputRotateMeta == nexTranscode.Rotate.CW_270) {
                        i2 = 270;
                    }
                }
                transcode(option.outputFile.getAbsolutePath(), option.outputWidth, option.outputHeight, option.outputBitRate, Long.MAX_VALUE, option.outputSamplingRate, i2);
            }
            i = 0;
            if (i != 0) {
            }
            if (option.outputFitMode != 0) {
            }
            resolveProject(true, true);
            if (option.outputRotateMeta != nexTranscode.Rotate.BYPASS) {
            }
            transcode(option.outputFile.getAbsolutePath(), option.outputWidth, option.outputHeight, option.outputBitRate, Long.MAX_VALUE, option.outputSamplingRate, i2);
        }
    }

    public void seek(int i) {
        if (this.cacheSeekMode) {
            Log.d(TAG, "[" + this.mId + "] seek fail! cacheSeekMode");
        } else if (isSetProject(false)) {
            undoForceMixProject();
            setEditorListener();
            setOverlays(OverlayCommand.upload);
            stopAsyncFaceDetect();
            faceDetect_for_seek(i, this.facedetect_undetected_clip_cropping_mode);
            this.lastSeekTime = i;
            this.mVideoEditor.j(i);
        }
    }

    public boolean seekFromCache(int i) {
        if (!this.cacheSeekMode || !isSetProject(false)) {
            return false;
        }
        undoForceMixProject();
        setEditorListener();
        setOverlays(OverlayCommand.upload);
        this.mVideoEditor.b(i, (NexEditor.p) null);
        return true;
    }

    public void seekIDROnly(int i) {
        if (this.cacheSeekMode) {
            Log.d(TAG, "[" + this.mId + "] seekIDROnly fail! cacheSeekMode");
        } else if (isSetProject(false)) {
            undoForceMixProject();
            setEditorListener();
            setOverlays(OverlayCommand.upload);
            this.lastSeekTime = i;
            this.mVideoEditor.e(i, (NexEditor.p) null);
        }
    }

    public void seekIDROnly(int i, final OnSeekCompletionListener onSeekCompletionListener) {
        if (this.cacheSeekMode) {
            Log.d(TAG, "[" + this.mId + "] seekIDROnly fail! cacheSeekMode");
        } else if (isSetProject(false)) {
            undoForceMixProject();
            setEditorListener();
            setOverlays(OverlayCommand.upload);
            this.lastSeekTime = i;
            this.mVideoEditor.e(i, (NexEditor.p) new NexEditor.p() {
                public String a() {
                    return "seekIDROnly";
                }

                public void a(int i, int i2) {
                    OnSeekCompletionListener onSeekCompletionListener = onSeekCompletionListener;
                    if (onSeekCompletionListener != null) {
                        onSeekCompletionListener.onSeekComplete(0, i, i2);
                    }
                }

                public void a(NexEditor.ErrorCode errorCode) {
                    OnSeekCompletionListener onSeekCompletionListener = onSeekCompletionListener;
                    if (onSeekCompletionListener != null) {
                        onSeekCompletionListener.onSeekComplete(errorCode.getValue(), 0, 0);
                    }
                }
            });
        }
    }

    public void seekIDRorI(int i) {
        if (this.cacheSeekMode) {
            Log.d(TAG, "[" + this.mId + "] seekIDRorI fail! cacheSeekMode");
        } else if (isSetProject(false)) {
            undoForceMixProject();
            setEditorListener();
            setOverlays(OverlayCommand.upload);
            this.lastSeekTime = i;
            this.mVideoEditor.d(i, (NexEditor.p) null);
        }
    }

    public void set360VideoForceNormalView() {
        this.mVideoEditor.A();
    }

    public boolean set360VideoViewPosition(int i, int i2) {
        return this.mVideoEditor.e(i, i2);
    }

    public void set360VideoViewStopPosition(int i, int i2) {
        this.mVideoEditor.q().a(NexEditor.FastPreviewOption.nofx, 1).a(i, i2).a();
    }

    public void setBrightness(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setBrightness(i);
        }
    }

    public void setContrast(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setContrast(i);
        }
    }

    public void setDeviceGamma(float f) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setDeviceGamma(f);
        }
    }

    public void setDeviceLightLevel(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setDeviceLightLevel(i);
        }
    }

    public void setEventHandler(nexEngineListener nexenginelistener) {
        Log.d(TAG, "[" + this.mId + "] setEventHandler =" + nexenginelistener);
        this.mEventListener = nexenginelistener;
        setEditorListener();
    }

    public void setExportCrop(int i, int i2, int i3) {
        Log.d(TAG, "[" + this.mId + "] setExportCrop mode=" + i + ", (" + i2 + "X" + i3 + ")");
        this.mEnhancedCropMode = i;
        this.mEnhancedCropOutputWidth = i2;
        this.mEnhancedCropOutputHeight = i3;
    }

    public void setFaceModule(String str) {
        stopAsyncFaceDetect();
        if (str == null) {
            com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a((nexFaceDetectionProvider) null);
            return;
        }
        nexFaceDetectionProvider a2 = com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a();
        if (a2 == null || a2.uuid().compareTo(str) != 0) {
            if (this.mProject != null) {
                for (int i = 0; i < this.mProject.getTotalClipCount(true); i++) {
                    nexClip clip = this.mProject.getClip(i, true);
                    if (clip.getClipType() == 1) {
                        clip.resetFaceDetectProcessed();
                    }
                }
            }
            Object module = nexApplicationConfig.getExternalModuleManager().getModule(str);
            if (module == null) {
                com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a((nexFaceDetectionProvider) null);
            } else if (nexFaceDetectionProvider.class.isInstance(module)) {
                com.nexstreaming.kminternal.kinemaster.utils.facedetect.a.a((nexFaceDetectionProvider) module);
            }
        }
    }

    public void setHDRtoSDR(boolean z) {
        this.mVideoEditor.setProperty("HDR2SDR", z ? "1" : MovieStatUtils.DOWNLOAD_SUCCESS);
    }

    public void setLetterBox(boolean z) {
        this.bLetterBox = z;
    }

    /* access modifiers changed from: package-private */
    public void setMark() {
        if (this.mVideoEditor.y() == 1) {
            this.mVideoEditor.e(true);
        } else {
            this.mVideoEditor.e(false);
        }
    }

    public void setOnSurfaceChangeListener(OnSurfaceChangeListener onSurfaceChangeListener) {
        this.m_onSurfaceChangeListener = onSurfaceChangeListener;
        this.mVideoEditor.a((NexEditor.q) new NexEditor.q() {
            public void a() {
                if (nexEngine.this.m_onSurfaceChangeListener != null) {
                    nexEngine.this.m_onSurfaceChangeListener.onSurfaceChanged();
                }
            }
        });
    }

    public boolean setPreviewScaleFactor(float f) {
        return this.mVideoEditor.a(f);
    }

    public void setProject(nexProject nexproject) {
        Log.d(TAG, "[" + this.mId + "] setProject");
        this.mProject = nexproject;
        defaultFaceDetectSetting();
    }

    public void setProperty(String str, String str2) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setProperty(str, str2);
        }
    }

    public void setSaturation(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setSaturation(i);
        }
    }

    public void setScalingFlag2Export(boolean z) {
        this.bNeedScaling = z;
    }

    public void setSharpness(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setSharpness(i);
        }
    }

    public void setTaskSleep(boolean z) {
        this.mVideoEditor.setTaskSleep(z ? 1 : 0);
    }

    public void setThumbnailRoutine(int i) {
        this.mVideoEditor.o(i);
    }

    public void setTotalAudioVolumeProject(int i, int i2) {
        for (nexClip clipVolume : this.mProject.getPrimaryItems()) {
            clipVolume.setClipVolume(i);
        }
        for (nexAudioItem clip : this.mProject.getAudioItems()) {
            clip.getClip().setClipVolume(i2);
        }
        this.mProject.setBGMMasterVolumeScale(((float) i2) / 200.0f);
    }

    public boolean setTotalAudioVolumeResetWhilePlay() {
        return this.mVideoEditor.setVolumeWhilePlay(100, 100) == 0;
    }

    public boolean setTotalAudioVolumeWhilePlay(int i, int i2) {
        if (i == 100) {
            i = 101;
        }
        if (i2 == 100) {
            i2 = 101;
        }
        return i >= 0 && i <= 200 && i2 >= 0 && i2 <= 200 && this.mVideoEditor.setVolumeWhilePlay(i, i2) == 0;
    }

    public int setView(SurfaceView surfaceView) {
        Log.d(TAG, "[" + this.mId + "] setView SurfaceView=" + surfaceView);
        this.mVideoEditor.a((NexThemeView) null);
        SurfaceView surfaceView2 = this.mSurfaceView;
        if (!(surfaceView == surfaceView2 || surfaceView2 == null)) {
            surfaceView2.getHolder().removeCallback(this);
        }
        this.mSurfaceView = surfaceView;
        SurfaceView surfaceView3 = this.mSurfaceView;
        if (surfaceView3 == null) {
            return 0;
        }
        surfaceView3.getHolder().addCallback(this);
        return 0;
    }

    public int setView(nexEngineView nexengineview) {
        Log.d(TAG, "[" + this.mId + "] setView nexEngineView=" + nexengineview);
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null) {
            surfaceView.getHolder().removeCallback(this);
            this.mSurfaceView = null;
        }
        this.mVideoEditor.a((NexThemeView) nexengineview);
        return 0;
    }

    public void setVignette(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setVignette(i);
        }
    }

    public void setVignetteRange(int i) {
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.setVignetteRange(i);
        }
    }

    public boolean startCollectCache(int i, final OnCompletionListener onCompletionListener) {
        if (!isSetProject(false)) {
            return false;
        }
        if (MediaInfo.k()) {
            if (onCompletionListener != null) {
                onCompletionListener.onComplete(NexEditor.ErrorCode.THUMBNAIL_BUSY.getValue());
            }
            return false;
        }
        this.cacheSeekMode = true;
        undoForceMixProject();
        setEditorListener();
        setOverlays(OverlayCommand.upload);
        this.mVideoEditor.a(i, (NexEditor.p) new NexEditor.p() {
            public String a() {
                return null;
            }

            public void a(int i, int i2) {
                OnCompletionListener onCompletionListener = onCompletionListener;
                if (onCompletionListener != null) {
                    onCompletionListener.onComplete(0);
                }
            }

            public void a(NexEditor.ErrorCode errorCode) {
                boolean unused = nexEngine.this.cacheSeekMode = false;
                OnCompletionListener onCompletionListener = onCompletionListener;
                if (onCompletionListener != null) {
                    onCompletionListener.onComplete(errorCode.getValue());
                }
            }
        });
        return true;
    }

    public void stop() {
        if (this.externalImageExportProcessing) {
            this.mVideoEditor.u();
            this.mState = 1;
            return;
        }
        this.cacheSeekMode = false;
        if (!isSetProject(false)) {
            return;
        }
        if (this.mState == 2) {
            this.mState = 1;
            this.mVideoEditor.s();
            return;
        }
        this.mState = 1;
        this.mVideoEditor.s();
    }

    public void stop(final OnCompletionListener onCompletionListener) {
        if (this.externalImageExportProcessing) {
            this.mVideoEditor.u();
            if (onCompletionListener != null) {
                onCompletionListener.onComplete(0);
            }
            this.mState = 1;
            return;
        }
        this.cacheSeekMode = false;
        if (isSetProject(false)) {
            this.mState = 1;
            this.mVideoEditor.a((NexEditor.f) new NexEditor.f() {
                public void a(NexEditor.ErrorCode errorCode) {
                    onCompletionListener.onComplete(errorCode.getValue());
                }
            });
            return;
        }
        onCompletionListener.onComplete(21);
    }

    public void stopAsyncFaceDetect() {
        Iterator<AsyncTask<String, Void, com.nexstreaming.kminternal.kinemaster.utils.facedetect.a>> it = this.async_facedetect_worker_list_.iterator();
        while (it.hasNext()) {
            it.next().cancel(true);
        }
        this.async_facedetect_worker_list_.clear();
    }

    public void stopCollectCache(int i) {
        this.cacheSeekMode = false;
        seek(i);
    }

    @Deprecated
    public void stopSync() {
        if (isSetProject(false)) {
            Log.i(TAG, "[" + this.mId + "]stopSync start");
            if (this.mPlayState == NexEditor.PlayState.NONE || this.mPlayState == NexEditor.PlayState.IDLE) {
                Log.i(TAG, "[" + this.mId + "]stopSync IDLE state");
                this.mState = 1;
                return;
            }
            this.mVideoEditor.b(true);
            this.mVideoEditor.s();
            while (this.mPlayState != NexEditor.PlayState.IDLE) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.mState = 1;
            this.mVideoEditor.b(false);
            Log.i(TAG, "[" + this.mId + "]stopSync End!");
        }
    }

    /* access modifiers changed from: package-private */
    public void stopTranscode() {
        if (!sTranscodeMode) {
            return;
        }
        if (this.mState == 2) {
            stop();
            return;
        }
        sTranscodeMode = false;
        resolveProject(true, true);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        int i4;
        int i5;
        Log.v(TAG, "surfaceChanged called(" + i2 + "," + i3 + ")");
        if (i2 == 0 || i3 == 0) {
            Log.e(TAG, "invalid video width(" + i2 + ") or height(" + i3 + ")");
            return;
        }
        float aspectRatio = nexApplicationConfig.getAspectRatio();
        if (aspectRatio > 0.0f) {
            float f = ((float) i3) * aspectRatio;
            float f2 = (float) i2;
            if (f > f2) {
                i5 = Math.round(f2 / aspectRatio);
                i4 = i2;
                Log.d(TAG, "surfaceChanged aspect view size " + i4 + "x" + i5);
                if (i4 == i2 || i5 != i3) {
                    Log.d(TAG, "surfaceChanged new view size " + i4 + "x" + i5);
                    surfaceHolder.setFixedSize(i4, i5);
                }
                this.mSurface = surfaceHolder.getSurface();
                NexEditor nexEditor = this.mVideoEditor;
                if (nexEditor != null) {
                    nexEditor.a(this.mSurface);
                    this.mVideoEditor.w();
                    return;
                }
                return;
            }
            i4 = Math.round(f);
        } else {
            i4 = i2;
        }
        i5 = i3;
        Log.d(TAG, "surfaceChanged aspect view size " + i4 + "x" + i5);
        if (i4 == i2) {
        }
        Log.d(TAG, "surfaceChanged new view size " + i4 + "x" + i5);
        surfaceHolder.setFixedSize(i4, i5);
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.v(TAG, "surfaceCreated called()");
        if (this.mSurfaceView != null) {
            this.mSurface = surfaceHolder.getSurface();
            NexEditor nexEditor = this.mVideoEditor;
            if (nexEditor != null) {
                nexEditor.a(this.mSurface);
                this.mVideoEditor.w();
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.v(TAG, "surfaceDestroyed called()");
        if (surfaceHolder != null && this.mSurface == surfaceHolder.getSurface()) {
            this.mSurface = null;
        }
        NexEditor nexEditor = this.mVideoEditor;
        if (nexEditor != null) {
            nexEditor.a((Surface) null);
        }
    }

    public boolean transcodingStart(String str, String str2, int i, int i2, int i3, int i4, int i5, long j, int i6, int i7) {
        return !this.mVideoEditor.a(str, str2, i, i2, i3, i4, i5, j, i6, i7, EditorGlobal.b("up")).isError();
    }

    public boolean transcodingStop() {
        return !this.mVideoEditor.p().isError();
    }

    public void updateDrawInfo(nexDrawInfo nexdrawinfo) {
        if (this.mVideoEditor != null) {
            NexDrawInfo nexDrawInfo = new NexDrawInfo();
            nexDrawInfo.mID = nexdrawinfo.getID();
            nexDrawInfo.mTrackID = nexdrawinfo.getClipID();
            nexDrawInfo.mSubEffectID = nexdrawinfo.getSubEffectID();
            nexDrawInfo.mEffectID = nexdrawinfo.getEffectID();
            nexDrawInfo.mTitle = nexdrawinfo.getTitle();
            nexDrawInfo.mIsTransition = nexdrawinfo.getIsTransition();
            nexDrawInfo.mStartTime = nexdrawinfo.getStartTime();
            nexDrawInfo.mEndTime = nexdrawinfo.getEndTime();
            nexDrawInfo.mRotateState = nexdrawinfo.getRotateState();
            nexDrawInfo.mUserRotateState = nexdrawinfo.getUserRotateState();
            nexDrawInfo.mTranslateX = nexdrawinfo.getUserTranslateX();
            nexDrawInfo.mTranslateY = nexdrawinfo.getUserTranslateY();
            nexDrawInfo.mLUT = nexdrawinfo.getLUT();
            nexDrawInfo.mCustomLUT_A = nexdrawinfo.getCustomLUTA();
            nexDrawInfo.mCustomLUT_B = nexdrawinfo.getCustomLUTB();
            nexDrawInfo.mCustomLUT_Power = nexdrawinfo.getCustomLUTPower();
            nexDrawInfo.mBrightness = nexdrawinfo.getBrightness();
            nexDrawInfo.mContrast = nexdrawinfo.getContrast();
            nexDrawInfo.mSaturation = nexdrawinfo.getSaturation();
            nexDrawInfo.mTintcolor = nexdrawinfo.getTintcolor();
            nexDrawInfo.mStartRect.setRect(nexdrawinfo.getStartRect().left, nexdrawinfo.getStartRect().top, nexdrawinfo.getStartRect().right, nexdrawinfo.getStartRect().bottom);
            nexDrawInfo.mEndRect.setRect(nexdrawinfo.getEndRect().left, nexdrawinfo.getEndRect().top, nexdrawinfo.getEndRect().right, nexdrawinfo.getEndRect().bottom);
            nexDrawInfo.mFaceRect.setRect(nexdrawinfo.getFaceRect().left, nexdrawinfo.getFaceRect().top, nexdrawinfo.getFaceRect().right, nexdrawinfo.getFaceRect().bottom);
            this.mVideoEditor.updateDrawInfo(nexDrawInfo);
        }
    }

    public void updateProject() {
        if (this.mProject != null) {
            setOverlays(OverlayCommand.upload);
            int resolveProject = resolveProject(sLoadListAsync, false);
            if (this.mState != 2 && resolveProject == 1 && this.mProject.getTotalClipCount(true) > 0) {
                loadEffectsInEditor(false);
            }
        }
    }

    public boolean updateProject(boolean z) {
        if (z) {
            try {
                updateProject();
                return true;
            } catch (Exception unused) {
                return false;
            }
        } else {
            updateProject();
            return true;
        }
    }

    public void updateScreenMode() {
        if (this.mVideoEditor != null) {
            NexEditor.a(nexApplicationConfig.getAspectProfile().getWidth(), nexApplicationConfig.getAspectProfile().getHeight(), nexApplicationConfig.getOverlayCoordinateMode());
            this.mVideoEditor.a(nexApplicationConfig.getScreenMode());
            NexThemeView.setAspectRatio(nexApplicationConfig.getAspectRatio());
            setMark();
        }
    }
}
