package com.meicam.themehelper;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import com.meicam.sdk.NvsAVFileInfo;
import com.meicam.sdk.NvsAudioTrack;
import com.meicam.sdk.NvsMultiThumbnailSequenceView;
import com.meicam.sdk.NvsSize;
import com.meicam.sdk.NvsStreamingContext;
import com.meicam.sdk.NvsTimeline;
import com.meicam.sdk.NvsTimelineAnimatedSticker;
import com.meicam.sdk.NvsTimelineVideoFx;
import com.meicam.sdk.NvsVideoClip;
import com.meicam.sdk.NvsVideoFx;
import com.meicam.sdk.NvsVideoResolution;
import com.meicam.sdk.NvsVideoTrack;
import com.meicam.sdk.NvsVideoTransition;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NvsThemeHelper {
    private static final int DEFAULT_FONT1_SIZE = 71;
    private static final int DEFAULT_FONT2_SIZE = 33;
    private static final int DEFAULT_FONT_COLOR = -1;
    private static final String FONT_MAX_LENGTH = "一二三四五六七八九十";
    private static final int NVS_MAX_IMAGE_FILE_COUNT = 20;
    private static final long NVS_MAX_TIME_LINE_LENGTH = 90000000;
    private static final int NVS_MIN_IMAGE_FILE_COUNT = 3;
    private static final int NVS_USE_ALL_PHOTO = -2;
    private static final int NVS_USE_CURRENT_DURATION = -1;
    private static final String TAG = "NvsThemeHelper";
    public static float m_timelineRatio = 0.5625f;
    public static Random rand = new Random(1000);
    private long clipMaxLen = (this.timeBase * 10);
    private RectF coverEndROI = null;
    private RectF coverStartROI = null;
    private ArrayList<NvsMusicFileDesc> mMusicFileList = new ArrayList<>();
    private Map<String, NvsThemeAsset> mThemeAssetMap = new HashMap();
    private NvsTimelineAnimatedSticker m_cafSticker = null;
    private StringBuilder m_cafStickerId = new StringBuilder();
    private NvsTimelineAnimatedSticker m_caption1 = null;
    private String m_caption1Text = new String();
    private NvsTimelineAnimatedSticker m_caption2 = null;
    private String m_caption2Text = new String();
    private String m_captionBitmapPath1 = new String();
    private String m_captionBitmapPath2 = new String();
    private Context m_context;
    private String m_defaultRhythm10sPath;
    private String m_defaultRhythmPath;
    private int m_fxTransClipCount = 0;
    private ArrayList<NvsImageFileDesc> m_inputIamgeInfo;
    private boolean m_is10sMode = false;
    private long m_maxTotalTime = NVS_MAX_TIME_LINE_LENGTH;
    private float m_musicVolumeGain = 1.0f;
    private int m_selectedExtMusicIdx = -1;
    private String m_selectedLocalMusic = null;
    private long m_selectedLocalMusicEnd = -1;
    private long m_selectedLocalMusicStart = -1;
    private int m_selectedMusicType = -1;
    private boolean m_showCaption = false;
    private String m_stickerId1;
    private String m_stickerId2;
    private NvsStreamingContext m_streamingContext = null;
    private String m_themeAssetID;
    private NvsTimeline m_timeline;
    private long seed = 1000;
    private long timeBase = 1000000;

    public class NvsThemeAsset {
        public boolean isDownload = false;
        public StringBuilder m_blurFxId;
        public ArrayList<String> m_blurWidthFxTransList = new ArrayList<>();
        public StringBuilder m_cafSticker10sId;
        public StringBuilder m_cafStickerId;
        public long m_endingFx10sLen;
        public String m_endingFxId;
        public String m_endingFxId10s;
        public String m_endingFxImgPath;
        public ArrayList<String> m_fhFx9v16List = new ArrayList<>();
        public ArrayList<String> m_fhFx9v18List = new ArrayList<>();
        public ArrayList<String> m_fhFx9v19List = new ArrayList<>();
        public ArrayList<String> m_fhFx9vx73List = new ArrayList<>();
        public ArrayList<String> m_fullFx9v16List = new ArrayList<>();
        public ArrayList<String> m_fullFx9v18List = new ArrayList<>();
        public ArrayList<String> m_fullFx9v19List = new ArrayList<>();
        public ArrayList<String> m_fullFx9vx73List = new ArrayList<>();
        public ArrayList<String> m_halfFx9v16List = new ArrayList<>();
        public ArrayList<String> m_halfFx9v18List = new ArrayList<>();
        public ArrayList<String> m_halfFx9v19List = new ArrayList<>();
        public ArrayList<String> m_halfFx9vx73List = new ArrayList<>();
        public boolean m_hasFxTrans = false;
        public ArrayList<String> m_hfFx9v16List = new ArrayList<>();
        public ArrayList<String> m_hfFx9v18List = new ArrayList<>();
        public ArrayList<String> m_hfFx9v19List = new ArrayList<>();
        public ArrayList<String> m_hfFx9vx73List = new ArrayList<>();
        public long m_musicLen;
        public long m_musicLen10s;
        public ArrayList<NvsMusicPointDesc> m_musicPoints = new ArrayList<>();
        public ArrayList<NvsMusicPointDesc> m_musicPoints10s = new ArrayList<>();
        public StringBuilder m_stickerId1;
        public StringBuilder m_stickerId2;
        public StringBuilder m_theme10sId;
        public StringBuilder m_themeId;
        public NvsTransDesc m_transDesc = new NvsTransDesc();
        public ArrayList<StringBuilder> m_transIDList = new ArrayList<>();
        public int m_transOffset;

        public NvsThemeAsset() {
        }
    }

    private void addCaption(NvsTimeline nvsTimeline) {
        StringBuilder sb;
        nvsTimeline.removeAnimatedSticker(this.m_caption1);
        nvsTimeline.removeAnimatedSticker(this.m_caption2);
        if ((!this.m_caption1Text.isEmpty() || !this.m_caption2Text.isEmpty()) && this.m_showCaption) {
            nvsTimeline.removeAnimatedSticker(this.m_cafSticker);
            this.m_cafSticker = null;
        } else if (this.m_cafSticker == null && (sb = this.m_cafStickerId) != null) {
            String sb2 = sb.toString();
            this.m_cafSticker = nvsTimeline.addAnimatedSticker(0, (long) (((double) this.timeBase) * 3.48d), sb2);
        }
        String str = this.m_captionBitmapPath1;
        if (str != null && !str.isEmpty()) {
            File file = new File(this.m_captionBitmapPath1);
            if (file.exists()) {
                file.delete();
            }
        }
        String str2 = this.m_captionBitmapPath2;
        if (str2 != null && !str2.isEmpty()) {
            File file2 = new File(this.m_captionBitmapPath2);
            if (file2.exists()) {
                file2.delete();
            }
        }
        String str3 = this.m_stickerId1;
        if (str3 == null || this.m_stickerId2 == null) {
            Log.e("meicam", "m_stickerId is null");
        } else if (this.m_showCaption) {
            String str4 = str3.toString();
            String str5 = this.m_stickerId2.toString();
            if (!this.m_caption1Text.isEmpty()) {
                this.m_captionBitmapPath1 = createCaptionBitmap(manageStringLength(this.m_caption1Text, 71), 71);
                String str6 = this.m_captionBitmapPath1;
                if (str6 == null || str6.isEmpty()) {
                    Log.e("meicam", "caption1_path is null");
                } else if (new File(this.m_captionBitmapPath1).exists()) {
                    this.m_caption1 = nvsTimeline.addCustomAnimatedSticker(0, (long) (((double) this.timeBase) * 3.48d), str4, this.m_captionBitmapPath1);
                } else {
                    Log.e("meicam", "bitmap_file1 not exist");
                }
            }
            if (!this.m_caption2Text.isEmpty()) {
                this.m_captionBitmapPath2 = createCaptionBitmap(manageString2Length(this.m_caption2Text, 33), 33);
                String str7 = this.m_captionBitmapPath2;
                if (str7 == null || str7.isEmpty()) {
                    Log.e("meicam", "caption2_path is null");
                } else if (new File(this.m_captionBitmapPath2).exists()) {
                    this.m_caption2 = nvsTimeline.addCustomAnimatedSticker(0, (long) (((double) this.timeBase) * 3.48d), str5, this.m_captionBitmapPath2);
                } else {
                    Log.e("meicam", "bitmap_file2 not exist");
                }
            }
        }
    }

    private void addEndingFx(NvsTimeline nvsTimeline, NvsThemeAsset nvsThemeAsset, boolean z) {
        if (z) {
            nvsTimeline.setTimelineEndingFilter(nvsThemeAsset.m_endingFxId10s, nvsThemeAsset.m_endingFxImgPath, nvsThemeAsset.m_endingFx10sLen);
        } else {
            nvsTimeline.setTimelineEndingFilter(nvsThemeAsset.m_endingFxId, nvsThemeAsset.m_endingFxImgPath, (long) (((double) this.timeBase) * 1.5d));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007a A[LOOP:0: B:29:0x0078->B:30:0x007a, LOOP_END] */
    private void addMusicFile(NvsTimeline nvsTimeline, String str, boolean z) {
        long j;
        int i;
        int i2;
        String str2 = str;
        if (str2 != null && str2 != "") {
            Log.d("theme helper", str2);
            NvsAVFileInfo aVFileInfo = this.m_streamingContext.getAVFileInfo(str2);
            if (aVFileInfo != null && aVFileInfo.getAudioStreamCount() >= 1) {
                long duration = nvsTimeline.getDuration();
                long audioStreamDuration = aVFileInfo.getAudioStreamDuration(0);
                long j2 = this.timeBase;
                long j3 = (audioStreamDuration / j2) * j2;
                long j4 = 0;
                if (z) {
                    long j5 = this.m_selectedLocalMusicStart;
                    if (j5 >= 0) {
                        long j6 = this.m_selectedLocalMusicEnd;
                        if (j6 > 0) {
                            long j7 = (j5 / j2) * j2;
                            long j8 = (j6 / j2) * j2;
                            j = j8 - j7;
                            if (j <= j2 * 2) {
                                j = j3;
                            } else if (j8 > j3) {
                                j = j3 - j7;
                            }
                            if (j >= this.timeBase * 2) {
                                j4 = j7;
                                nvsTimeline.setThemeMusicVolumeGain(0.0f, 0.0f);
                                NvsAudioTrack appendAudioTrack = nvsTimeline.appendAudioTrack();
                                i = (int) (((duration + j) - this.timeBase) / j);
                                for (i2 = 0; i2 < i; i2++) {
                                    appendAudioTrack.appendClip(str, j4, j4 + j);
                                }
                            }
                        }
                    }
                }
                j = j3;
                nvsTimeline.setThemeMusicVolumeGain(0.0f, 0.0f);
                NvsAudioTrack appendAudioTrack2 = nvsTimeline.appendAudioTrack();
                i = (int) (((duration + j) - this.timeBase) / j);
                while (i2 < i) {
                }
            }
        }
    }

    private void addToFxList(NvsThemeAsset nvsThemeAsset, String str, String str2) {
        if (str2.contains("9v16")) {
            if (str2.contains("-full")) {
                nvsThemeAsset.m_fullFx9v16List.add(str);
            } else if (str2.contains("-half")) {
                nvsThemeAsset.m_halfFx9v16List.add(str);
            } else if (str2.contains("-hf")) {
                nvsThemeAsset.m_hfFx9v16List.add(str);
            } else if (str2.contains("-fh")) {
                nvsThemeAsset.m_fhFx9v16List.add(str);
            } else {
                nvsThemeAsset.m_fullFx9v16List.add(str);
                nvsThemeAsset.m_halfFx9v16List.add(str);
                nvsThemeAsset.m_hfFx9v16List.add(str);
                nvsThemeAsset.m_fhFx9v16List.add(str);
            }
        } else if (str2.contains("9v18")) {
            if (str2.contains("-full")) {
                nvsThemeAsset.m_fullFx9v18List.add(str);
            } else if (str2.contains("-half")) {
                nvsThemeAsset.m_halfFx9v18List.add(str);
            } else if (str2.contains("-hf")) {
                nvsThemeAsset.m_hfFx9v18List.add(str);
            } else if (str2.contains("-fh")) {
                nvsThemeAsset.m_fhFx9v18List.add(str);
            } else {
                nvsThemeAsset.m_fullFx9v18List.add(str);
                nvsThemeAsset.m_halfFx9v18List.add(str);
                nvsThemeAsset.m_hfFx9v18List.add(str);
                nvsThemeAsset.m_fhFx9v18List.add(str);
            }
        } else if (str2.contains("9v19")) {
            if (str2.contains("-full")) {
                nvsThemeAsset.m_fullFx9v19List.add(str);
            } else if (str2.contains("-half")) {
                nvsThemeAsset.m_halfFx9v19List.add(str);
            } else if (str2.contains("-hf")) {
                nvsThemeAsset.m_hfFx9v19List.add(str);
            } else if (str2.contains("-fh")) {
                nvsThemeAsset.m_fhFx9v19List.add(str);
            } else {
                nvsThemeAsset.m_fullFx9v19List.add(str);
                nvsThemeAsset.m_halfFx9v19List.add(str);
                nvsThemeAsset.m_hfFx9v19List.add(str);
                nvsThemeAsset.m_fhFx9v19List.add(str);
            }
        } else if (!str2.contains("9vx73")) {
        } else {
            if (str2.contains("-full")) {
                nvsThemeAsset.m_fullFx9vx73List.add(str);
            } else if (str2.contains("-half")) {
                nvsThemeAsset.m_halfFx9vx73List.add(str);
            } else if (str2.contains("-hf")) {
                nvsThemeAsset.m_hfFx9vx73List.add(str);
            } else if (str2.contains("-fh")) {
                nvsThemeAsset.m_fhFx9vx73List.add(str);
            } else {
                nvsThemeAsset.m_fullFx9vx73List.add(str);
                nvsThemeAsset.m_halfFx9vx73List.add(str);
                nvsThemeAsset.m_hfFx9vx73List.add(str);
                nvsThemeAsset.m_fhFx9vx73List.add(str);
            }
        }
    }

    private long applyFxTrans(NvsVideoTrack nvsVideoTrack, int i, String str, String str2, long j, boolean z, int i2) {
        long j2;
        String str3;
        String str4;
        String str5;
        NvsVideoTransition transitionBySourceClipIndex;
        NvsVideoTrack nvsVideoTrack2 = nvsVideoTrack;
        int i3 = i;
        String str6 = str;
        String str7 = str2;
        int i4 = i2;
        if (str6.equalsIgnoreCase("")) {
            return 0;
        }
        NvsVideoClip clipByIndex = nvsVideoTrack.getClipByIndex(i);
        int i5 = i3 + 1;
        NvsVideoClip clipByIndex2 = nvsVideoTrack2.getClipByIndex(i5);
        String filePath = clipByIndex.getFilePath();
        String filePath2 = clipByIndex2.getFilePath();
        long outPoint = clipByIndex.getOutPoint() - clipByIndex.getInPoint();
        long outPoint2 = clipByIndex2.getOutPoint() - clipByIndex2.getInPoint();
        String obj = clipByIndex.getAttachment("fullscreenMode").toString();
        int intValue = Integer.valueOf(clipByIndex.getAttachment("indexOfShowList").toString()).intValue();
        int intValue2 = Integer.valueOf(clipByIndex2.getAttachment("indexOfShowList").toString()).intValue();
        int i6 = i3 - 1;
        String builtinVideoTransitionName = (i6 < 0 || (transitionBySourceClipIndex = nvsVideoTrack2.getTransitionBySourceClipIndex(i6)) == null) ? null : transitionBySourceClipIndex.getVideoTransitionType() == 0 ? transitionBySourceClipIndex.getBuiltinVideoTransitionName() : transitionBySourceClipIndex.getVideoTransitionPackageId();
        if (builtinVideoTransitionName != null) {
            str3 = filePath;
            j2 = (long) i4;
        } else {
            str3 = filePath;
            j2 = 0;
        }
        nvsVideoTrack2.removeClip(i3, false);
        nvsVideoTrack2.removeClip(i3, false);
        String str8 = builtinVideoTransitionName;
        String str9 = str3;
        String str10 = "";
        String str11 = filePath2;
        int i7 = i5;
        NvsVideoClip insertClip = nvsVideoTrack.insertClip(str9, 0, outPoint + outPoint2, i);
        insertClip.setAttachment("fullscreenMode", obj);
        insertClip.setAttachment("hasFxTrans", "true");
        insertClip.setAttachment("fxFilePath", str11);
        insertClip.setAttachment("fxInpoint", String.valueOf(outPoint / 1000));
        int index = insertClip.getIndex();
        nvsVideoTrack2.setBuiltinTransition(index, (String) null);
        int i8 = index - 1;
        if (i8 >= 0) {
            if (str8 == null) {
                nvsVideoTrack2.setBuiltinTransition(i8, (String) null);
            } else if (str8.length() > 30) {
                nvsVideoTrack2.setPackagedTransition(i8, str8);
            } else {
                nvsVideoTrack2.setBuiltinTransition(i8, str8);
            }
        }
        NvsVideoFx appendBuiltinFx = insertClip.appendBuiltinFx("Storyboard");
        long j3 = (long) i4;
        long j4 = (outPoint - (j3 - j2)) / 1000;
        NvsVideoClip nvsVideoClip = insertClip;
        String changeFxDesc = changeFxDesc(str, str11, j4, ((((insertClip.getOutPoint() - insertClip.getInPoint()) + this.timeBase) - j3) / 1000) - j4, z);
        if (changeFxDesc == null) {
            return 0;
        }
        if (str6.contains("hori")) {
            str5 = Utils.changeHoriROI(this.m_inputIamgeInfo.get(intValue).imgRatio, this.m_inputIamgeInfo.get(intValue).faceRect, Utils.changeHoriROI(this.m_inputIamgeInfo.get(intValue2).imgRatio, this.m_inputIamgeInfo.get(intValue2).faceRect, changeFxDesc, (NvsVideoClip) null), nvsVideoClip);
            str4 = null;
        } else if (str6.contains("vert")) {
            str4 = null;
            str5 = Utils.changeVertROI(this.m_inputIamgeInfo.get(intValue).imgRatio, this.m_inputIamgeInfo.get(intValue).faceRect, Utils.changeVertROI(this.m_inputIamgeInfo.get(intValue2).imgRatio, this.m_inputIamgeInfo.get(intValue2).faceRect, changeFxDesc, (NvsVideoClip) null), nvsVideoClip);
        } else {
            str4 = null;
            setImageMotion(nvsVideoClip, this.m_inputIamgeInfo.get(intValue));
            str5 = Utils.changeROI(this.m_inputIamgeInfo.get(intValue2).imgRatio, this.m_inputIamgeInfo.get(intValue2).faceRect, changeFxDesc);
        }
        appendBuiltinFx.setStringVal("Description String", str5);
        if (!z) {
            str6 = "assets:/" + str6;
        }
        appendBuiltinFx.setStringVal("Resource Dir", str6.substring(0, str6.lastIndexOf("/")));
        if (nvsVideoTrack2.getClipByIndex(i7) != null) {
            String str12 = str2;
            String str13 = str4;
            if (str12.equalsIgnoreCase(str10)) {
                nvsVideoTrack2.setBuiltinTransition(i, str13);
            } else {
                int i9 = i;
                NvsVideoTransition packagedTransition = str2.length() > 30 ? nvsVideoTrack2.setPackagedTransition(i9, str12) : nvsVideoTrack2.setBuiltinTransition(i9, str12);
                if (packagedTransition != null) {
                    packagedTransition.setVideoTransitionDurationScaleFactor(((float) j) / 1000.0f);
                }
            }
        }
        this.m_fxTransClipCount++;
        return nvsVideoClip.getInPoint() + outPoint;
    }

    private void applyFxTransV2(NvsVideoTrack nvsVideoTrack, int i, String str, String str2, long j, NvsThemeAsset nvsThemeAsset) {
        NvsThemeAsset nvsThemeAsset2 = nvsThemeAsset;
        long applyFxTrans = applyFxTrans(nvsVideoTrack, i, str, str2, j, nvsThemeAsset2.isDownload, nvsThemeAsset2.m_transOffset * 1000);
        if (nvsThemeAsset2.m_blurFxId != null) {
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= nvsThemeAsset2.m_blurWidthFxTransList.size()) {
                    break;
                }
                String str3 = str;
                if (str.contains(nvsThemeAsset2.m_blurWidthFxTransList.get(i2))) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (z) {
                NvsTimeline nvsTimeline = this.m_timeline;
                long j2 = this.timeBase;
                nvsTimeline.addPackagedTimelineVideoFx(applyFxTrans - (j2 / 2), j2, nvsThemeAsset2.m_blurFxId.toString()).setBooleanVal("No Background", true);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x017f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0180  */
    private void applyFxTransV3(NvsVideoTrack nvsVideoTrack, int i, String str, String str2, long j, NvsThemeAsset nvsThemeAsset) {
        int i2;
        long j2;
        String changeFxDescV3;
        int i3;
        String str3;
        String str4;
        NvsVideoTransition transitionBySourceClipIndex;
        NvsVideoTrack nvsVideoTrack2 = nvsVideoTrack;
        int i4 = i;
        String str5 = str2;
        NvsThemeAsset nvsThemeAsset2 = nvsThemeAsset;
        if (!str.equalsIgnoreCase("") && (i2 = i4 + 2) < nvsVideoTrack.getClipCount()) {
            NvsVideoClip clipByIndex = nvsVideoTrack.getClipByIndex(i);
            int i5 = i4 + 1;
            NvsVideoClip clipByIndex2 = nvsVideoTrack2.getClipByIndex(i5);
            NvsVideoClip clipByIndex3 = nvsVideoTrack2.getClipByIndex(i2);
            String filePath = clipByIndex.getFilePath();
            String filePath2 = clipByIndex2.getFilePath();
            String filePath3 = clipByIndex3.getFilePath();
            long outPoint = clipByIndex.getOutPoint() - clipByIndex.getInPoint();
            long outPoint2 = clipByIndex2.getOutPoint() - clipByIndex2.getInPoint();
            long outPoint3 = clipByIndex3.getOutPoint() - clipByIndex3.getInPoint();
            String str6 = "";
            int i6 = i5;
            String obj = clipByIndex.getAttachment("fullscreenMode").toString();
            String str7 = filePath3;
            int intValue = Integer.valueOf(clipByIndex.getAttachment("indexOfShowList").toString()).intValue();
            int intValue2 = Integer.valueOf(clipByIndex2.getAttachment("indexOfShowList").toString()).intValue();
            int intValue3 = Integer.valueOf(clipByIndex3.getAttachment("indexOfShowList").toString()).intValue();
            int i7 = i4 - 1;
            int i8 = intValue2;
            String builtinVideoTransitionName = (i7 < 0 || (transitionBySourceClipIndex = nvsVideoTrack2.getTransitionBySourceClipIndex(i7)) == null) ? null : transitionBySourceClipIndex.getVideoTransitionType() == 0 ? transitionBySourceClipIndex.getBuiltinVideoTransitionName() : transitionBySourceClipIndex.getVideoTransitionPackageId();
            long j3 = 0;
            String str8 = filePath;
            long j4 = (long) (nvsThemeAsset2.m_transOffset * 1000);
            if (builtinVideoTransitionName != null) {
                j3 = j4;
                j2 = j3;
            } else {
                j2 = j4;
            }
            nvsVideoTrack2.removeClip(i4, false);
            nvsVideoTrack2.removeClip(i4, false);
            nvsVideoTrack2.removeClip(i4, false);
            int i9 = intValue;
            String str9 = builtinVideoTransitionName;
            long j5 = j2;
            int i10 = intValue3;
            String str10 = str7;
            NvsVideoClip insertClip = nvsVideoTrack.insertClip(str8, 0, outPoint + outPoint2 + outPoint3, i);
            insertClip.setAttachment("fullscreenMode", obj);
            insertClip.setAttachment("hasFxTrans", "true");
            insertClip.setAttachment("fxFilePath", filePath2);
            insertClip.setAttachment("fxFileV3Path", str10);
            insertClip.setAttachment("fxInpoint", String.valueOf(outPoint / 1000));
            long j6 = outPoint2 / 1000;
            insertClip.setAttachment("fxV3Inpoint", String.valueOf(j6));
            int index = insertClip.getIndex();
            nvsVideoTrack2.setBuiltinTransition(index, (String) null);
            int i11 = index - 1;
            if (i11 >= 0) {
                if (str9 == null) {
                    nvsVideoTrack2.setBuiltinTransition(i11, (String) null);
                    setImageMotion(insertClip, this.m_inputIamgeInfo.get(i9));
                    NvsVideoFx appendBuiltinFx = insertClip.appendBuiltinFx("Storyboard");
                    long j7 = (outPoint - (j5 - j3)) / 1000;
                    String str11 = str6;
                    int i12 = i6;
                    changeFxDescV3 = changeFxDescV3(str, filePath2, str10, j7, j7 + j6, (((insertClip.getOutPoint() - insertClip.getInPoint()) + j5) / 1000) - j7, nvsThemeAsset2.isDownload);
                    if (changeFxDescV3 == null) {
                        String str12 = str;
                        if (str12.contains("hori")) {
                            int i13 = i8;
                            int i14 = i10;
                            i3 = 0;
                            str3 = Utils.changeHoriROIV3(this.m_inputIamgeInfo.get(i14).imgRatio, this.m_inputIamgeInfo.get(i14).faceRect, Utils.changeHoriROIV3(this.m_inputIamgeInfo.get(i13).imgRatio, this.m_inputIamgeInfo.get(i13).faceRect, changeFxDescV3, true), false);
                        } else {
                            int i15 = i8;
                            int i16 = i10;
                            i3 = 0;
                            str3 = str12.contains("vert") ? Utils.changeVertROIV3(this.m_inputIamgeInfo.get(i16).imgRatio, this.m_inputIamgeInfo.get(i16).faceRect, Utils.changeVertROIV3(this.m_inputIamgeInfo.get(i15).imgRatio, this.m_inputIamgeInfo.get(i15).faceRect, changeFxDescV3, true), false) : Utils.changeROIV3(this.m_inputIamgeInfo.get(i16).imgRatio, this.m_inputIamgeInfo.get(i16).faceRect, Utils.changeROIV3(this.m_inputIamgeInfo.get(i15).imgRatio, this.m_inputIamgeInfo.get(i15).faceRect, changeFxDescV3, true), false);
                        }
                        appendBuiltinFx.setStringVal("Description String", str3);
                        if (!nvsThemeAsset.isDownload) {
                            str4 = "assets:/" + str12;
                        } else {
                            str4 = str12;
                        }
                        appendBuiltinFx.setStringVal("Resource Dir", str4.substring(i3, str4.lastIndexOf("/")));
                        appendBuiltinFx.setBooleanVal("Compact Preload Resource", true);
                        NvsVideoTrack nvsVideoTrack3 = nvsVideoTrack;
                        if (nvsVideoTrack3.getClipByIndex(i12) != null) {
                            String str13 = str2;
                            if (str13.equalsIgnoreCase(str11)) {
                                nvsVideoTrack3.setBuiltinTransition(i, (String) null);
                            } else {
                                int i17 = i;
                                NvsVideoTransition packagedTransition = str2.length() > 30 ? nvsVideoTrack3.setPackagedTransition(i17, str13) : nvsVideoTrack3.setBuiltinTransition(i17, str13);
                                if (packagedTransition != null) {
                                    packagedTransition.setVideoTransitionDurationScaleFactor(((float) j) / 1000.0f);
                                }
                            }
                        }
                        this.m_fxTransClipCount += 2;
                        return;
                    }
                    return;
                } else if (str9.length() > 30) {
                    nvsVideoTrack2.setPackagedTransition(i11, str9);
                } else {
                    nvsVideoTrack2.setBuiltinTransition(i11, str9);
                }
            }
            setImageMotion(insertClip, this.m_inputIamgeInfo.get(i9));
            NvsVideoFx appendBuiltinFx2 = insertClip.appendBuiltinFx("Storyboard");
            long j72 = (outPoint - (j5 - j3)) / 1000;
            String str112 = str6;
            int i122 = i6;
            changeFxDescV3 = changeFxDescV3(str, filePath2, str10, j72, j72 + j6, (((insertClip.getOutPoint() - insertClip.getInPoint()) + j5) / 1000) - j72, nvsThemeAsset2.isDownload);
            if (changeFxDescV3 == null) {
            }
        }
    }

    private Map<Integer, NvsMusicPointDesc> buildVideoTrack(ArrayList<NvsImageFileDesc> arrayList, NvsVideoTrack nvsVideoTrack, ArrayList<NvsMusicPointDesc> arrayList2, long j, long j2, boolean z) {
        int i;
        ArrayList<NvsImageFileDesc> arrayList3 = arrayList;
        ArrayList<NvsMusicPointDesc> arrayList4 = arrayList2;
        HashMap hashMap = new HashMap();
        int i2 = 0;
        int i3 = 0;
        while (i2 < arrayList.size()) {
            int i4 = (int) this.clipMaxLen;
            if (arrayList2.size() > 0) {
                if (i2 <= 0) {
                    i4 = arrayList4.get(0).cutPoint * 1000;
                } else if (i3 >= arrayList2.size()) {
                    break;
                } else {
                    NvsMusicPointDesc nvsMusicPointDesc = arrayList4.get(i3);
                    i3++;
                    if (i3 < arrayList2.size()) {
                        i = (arrayList4.get(i3).cutPoint - nvsMusicPointDesc.cutPoint) * 1000;
                    } else {
                        i = (int) ((z ? 10 * this.timeBase : j) - ((long) (nvsMusicPointDesc.cutPoint * 1000)));
                    }
                    if (i > 0) {
                        if (!nvsMusicPointDesc.transNames.isEmpty() || !nvsMusicPointDesc.fxNames.isEmpty()) {
                            hashMap.put(Integer.valueOf(nvsVideoTrack.getClipCount() > 0 ? nvsVideoTrack.getClipCount() - 1 : 0), nvsMusicPointDesc);
                        }
                        i4 = i;
                    } else {
                        i2++;
                    }
                }
            }
            NvsImageFileDesc nvsImageFileDesc = arrayList3.get(i2);
            NvsVideoClip appendClip = nvsVideoTrack.appendClip(nvsImageFileDesc.filePath, 0, (long) i4);
            if (appendClip != null) {
                appendClip.setAttachment("indexOfShowList", String.valueOf(i2));
                setImageMotion(appendClip, nvsImageFileDesc);
            } else {
                arrayList3.remove(i2);
                i2--;
            }
            i2++;
        }
        return hashMap;
    }

    private String changeFxDesc(String str, String str2, long j, long j2, boolean z) {
        String readFile = Utils.readFile(str, z ? null : this.m_context.getAssets());
        if (readFile == null) {
            return null;
        }
        return readFile.replace("placeholder.jpg", str2).replace("xiaomiStartTime", String.valueOf(j)).replace("xiaomiDurationTime", String.valueOf(j2));
    }

    private String changeFxDescV3(String str, String str2, String str3, long j, long j2, long j3, boolean z) {
        String readFile = Utils.readFile(str, z ? null : this.m_context.getAssets());
        if (readFile == null) {
            return null;
        }
        return readFile.replace("placeholder.jpg", str2).replace("picture.jpg", str3).replace("xiaomiStartTime", String.valueOf(j)).replace("pictureShowUp", String.valueOf(j2)).replace("xiaomiDurationTime", String.valueOf(j3)).replace("pictureLoop", String.valueOf(j3)).replace("lastMove", String.valueOf(j2 + 1000));
    }

    private void changeLocalMusic(String str, long j, long j2) {
        this.m_selectedExtMusicIdx = -1;
        this.m_selectedLocalMusic = str;
        this.m_selectedLocalMusicStart = j;
        this.m_selectedLocalMusicEnd = j2;
        reBuildTimeLineExt(-1, true);
    }

    private boolean checkUpdateAsset(String str, String str2, int i) {
        if (str == null || str2 == null || str == null) {
            return false;
        }
        if (this.m_streamingContext == null) {
            this.m_streamingContext = NvsStreamingContext.getInstance();
        }
        NvsStreamingContext nvsStreamingContext = this.m_streamingContext;
        return nvsStreamingContext != null && nvsStreamingContext.getAssetPackageManager().getAssetPackageVersionFromAssetPackageFilePath(str2) > this.m_streamingContext.getAssetPackageManager().getAssetPackageVersion(str, i);
    }

    private void cleanUpTimeLine(NvsTimeline nvsTimeline) {
        nvsTimeline.removeCurrentTheme();
        NvsTimelineVideoFx firstTimelineVideoFx = nvsTimeline.getFirstTimelineVideoFx();
        while (firstTimelineVideoFx != null) {
            firstTimelineVideoFx = nvsTimeline.removeTimelineVideoFx(firstTimelineVideoFx);
        }
        NvsTimelineAnimatedSticker firstAnimatedSticker = nvsTimeline.getFirstAnimatedSticker();
        while (firstAnimatedSticker != null) {
            firstAnimatedSticker = nvsTimeline.removeAnimatedSticker(firstAnimatedSticker);
        }
        this.m_cafSticker = null;
        this.m_caption1 = null;
        this.m_caption2 = null;
        int videoTrackCount = nvsTimeline.videoTrackCount();
        for (int i = 0; i < videoTrackCount; i++) {
            nvsTimeline.removeVideoTrack(i);
        }
        int audioTrackCount = nvsTimeline.audioTrackCount();
        for (int i2 = 0; i2 < audioTrackCount; i2++) {
            nvsTimeline.removeAudioTrack(i2);
        }
        nvsTimeline.setThemeMusicVolumeGain(1.0f, 1.0f);
    }

    private String createCaptionBitmap(String str, int i) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextSize((float) i);
        textPaint.setColor(-1);
        if (Build.VERSION.SDK_INT >= 21) {
            textPaint.setLetterSpacing(0.03f);
        }
        StaticLayout staticLayout = new StaticLayout(str, textPaint, Utils.getScreenWidth(this.m_context), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, true);
        Bitmap createBitmap = Bitmap.createBitmap(staticLayout.getWidth(), staticLayout.getHeight(), Bitmap.Config.ARGB_8888);
        staticLayout.draw(new Canvas(createBitmap));
        return saveBitmapToSD(createBitmap);
    }

    private String getAssetPath(JSONObject jSONObject, String str, String str2, boolean z) {
        String str3;
        try {
            str3 = jSONObject.getString(str);
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = null;
        }
        if (str3 == null || str3.isEmpty()) {
            return null;
        }
        String str4 = "assets:/" + str2 + "/" + str3;
        if (!z) {
            return str4;
        }
        return str2 + "/" + str3;
    }

    private int getClipCountInDuation(ArrayList<NvsMusicPointDesc> arrayList, long j) {
        int size = arrayList.size();
        if (size < 1) {
            return getMinImgCount();
        }
        if (j > ((long) (arrayList.get(size - 1).cutPoint * 1000))) {
            return arrayList.size() + 1;
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            if (((long) (arrayList.get(size2).cutPoint * 1000)) <= (this.timeBase + j) - 1) {
                return size2 + 1;
            }
        }
        return size;
    }

    private String getFxXml(int i, NvsVideoTrack nvsVideoTrack, NvsThemeAsset nvsThemeAsset, NvsMusicPointDesc nvsMusicPointDesc) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (nvsMusicPointDesc.fxNames.get(0).equalsIgnoreCase("")) {
            return "";
        }
        String transType = getTransType(i, nvsVideoTrack);
        if (transType.equalsIgnoreCase("full")) {
            float f = m_timelineRatio;
            arrayList = ((double) f) >= 0.5625d ? nvsThemeAsset.m_fullFx9v16List : f >= 0.5f ? nvsThemeAsset.m_fullFx9v18List : ((double) f) >= 0.4804270462633452d ? nvsThemeAsset.m_fullFx9vx73List : nvsThemeAsset.m_fullFx9v19List;
        } else if (transType.equalsIgnoreCase("half")) {
            float f2 = m_timelineRatio;
            arrayList = ((double) f2) >= 0.5625d ? nvsThemeAsset.m_halfFx9v16List : f2 >= 0.5f ? nvsThemeAsset.m_halfFx9v18List : ((double) f2) >= 0.4804270462633452d ? nvsThemeAsset.m_halfFx9vx73List : nvsThemeAsset.m_halfFx9v19List;
        } else if (transType.equalsIgnoreCase("hf")) {
            float f3 = m_timelineRatio;
            arrayList = ((double) f3) >= 0.5625d ? nvsThemeAsset.m_hfFx9v16List : f3 >= 0.5f ? nvsThemeAsset.m_hfFx9v18List : ((double) f3) >= 0.4804270462633452d ? nvsThemeAsset.m_hfFx9vx73List : nvsThemeAsset.m_hfFx9v19List;
        } else if (transType.equalsIgnoreCase("fh")) {
            float f4 = m_timelineRatio;
            arrayList = ((double) f4) >= 0.5625d ? nvsThemeAsset.m_fhFx9v16List : f4 >= 0.5f ? nvsThemeAsset.m_fhFx9v18List : ((double) f4) >= 0.4804270462633452d ? nvsThemeAsset.m_fhFx9vx73List : nvsThemeAsset.m_fhFx9v19List;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).contains(nvsMusicPointDesc.fxNames.get(0))) {
                return arrayList.get(i2);
            }
        }
        return "";
    }

    private String getLicFilePath(String str) {
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return null;
        }
        return split[0] + ".lic";
    }

    private int getMaxImgCount() {
        return 20;
    }

    private int getMinImgCount() {
        return (this.m_inputIamgeInfo.isEmpty() || !this.m_inputIamgeInfo.get(0).isCover) ? 3 : 4;
    }

    private ArrayList<NvsImageFileDesc> getRebuildFileList(ArrayList<NvsImageFileDesc> arrayList, ArrayList<NvsMusicPointDesc> arrayList2, int i) {
        if (i > arrayList2.size()) {
            i = arrayList2.size() + 1;
        }
        if (i > getMaxImgCount()) {
            i = getMaxImgCount();
        }
        ArrayList<NvsImageFileDesc> arrayList3 = new ArrayList<>();
        if (i == -1) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                NvsImageFileDesc nvsImageFileDesc = arrayList.get(i2);
                if (nvsImageFileDesc.show) {
                    arrayList3.add(nvsImageFileDesc);
                }
            }
        } else if (i == NVS_USE_ALL_PHOTO) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                NvsImageFileDesc nvsImageFileDesc2 = arrayList.get(i3);
                nvsImageFileDesc2.show = true;
                arrayList3.add(nvsImageFileDesc2);
            }
        } else {
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                NvsImageFileDesc nvsImageFileDesc3 = arrayList.get(i4);
                if (i4 >= i) {
                    nvsImageFileDesc3.show = false;
                } else {
                    nvsImageFileDesc3.show = true;
                    arrayList3.add(nvsImageFileDesc3);
                }
            }
        }
        if (arrayList3.size() <= 20) {
            return arrayList3;
        }
        ArrayList<NvsImageFileDesc> arrayList4 = new ArrayList<>();
        for (int i5 = 0; i5 < 20; i5++) {
            arrayList4.add(arrayList3.get(i5));
        }
        return arrayList4;
    }

    private String getTrans(ArrayList<String> arrayList, ArrayList<StringBuilder> arrayList2) {
        if (arrayList.isEmpty()) {
            return "";
        }
        int size = arrayList.size();
        if (hasNullTrans(arrayList)) {
            size *= 2;
        }
        int nextInt = rand.nextInt(size);
        if (nextInt >= arrayList.size() || nextInt < 0) {
            return "";
        }
        String str = arrayList.get(nextInt);
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            for (int i = 0; i < arrayList2.size(); i++) {
                if (arrayList2.get(i).toString().equalsIgnoreCase(str)) {
                    return arrayList2.get(i).toString();
                }
            }
        }
        return str;
    }

    private ArrayList<String> getTrans(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<StringBuilder> arrayList3, ArrayList<String> arrayList4) {
        ArrayList<String> arrayList5 = new ArrayList<>();
        if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
            int size = arrayList.size() + arrayList2.size();
            if (hasNullTrans(arrayList)) {
                size *= 2;
            }
            int nextInt = rand.nextInt(size);
            if (nextInt < 0) {
                arrayList5.add("");
                return arrayList5;
            }
            int i = 0;
            if (nextInt < arrayList.size()) {
                String str = arrayList.get(nextInt);
                if (arrayList3 != null && !arrayList3.isEmpty()) {
                    while (i < arrayList3.size()) {
                        if (arrayList3.get(i).toString().equalsIgnoreCase(str)) {
                            arrayList5.add(arrayList3.get(i).toString());
                            return arrayList5;
                        }
                        i++;
                    }
                }
                arrayList5.add(str);
                return arrayList5;
            }
            int size2 = nextInt - arrayList.size();
            if (size2 >= arrayList2.size() || size2 < 0) {
                arrayList5.add("");
                return arrayList5;
            }
            String str2 = arrayList2.get(size2);
            if (str2.equalsIgnoreCase("")) {
                arrayList5.add("");
                return arrayList5;
            }
            if (arrayList4 != null && !arrayList4.isEmpty()) {
                while (i < arrayList4.size()) {
                    if (arrayList4.get(i).toString().contains(str2)) {
                        arrayList5.add(arrayList4.get(i).toString());
                        arrayList5.add(getTransFollowFx(arrayList, arrayList3));
                        return arrayList5;
                    }
                    i++;
                }
            }
            arrayList5.add("");
            return arrayList5;
        }
        arrayList5.add("");
        return arrayList5;
    }

    private String getTransFollowFx(ArrayList<String> arrayList, ArrayList<StringBuilder> arrayList2) {
        int nextInt;
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).length() >= 30) {
                arrayList3.add(arrayList.get(i));
            }
        }
        if (!arrayList3.isEmpty() && (nextInt = rand.nextInt(arrayList3.size())) >= 0 && nextInt < arrayList3.size()) {
            String str = (String) arrayList3.get(nextInt);
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                if (arrayList2.get(i2).toString().equalsIgnoreCase(str)) {
                    return arrayList2.get(i2).toString();
                }
            }
        }
        return "";
    }

    private String getTransId(String str, NvsThemeAsset nvsThemeAsset) {
        if (nvsThemeAsset == null || nvsThemeAsset.m_transIDList == null) {
            return null;
        }
        for (int i = 0; i < nvsThemeAsset.m_transIDList.size(); i++) {
            String sb = nvsThemeAsset.m_transIDList.get(i).toString();
            if (sb.contains(str.replace(".videotransition", ""))) {
                return sb;
            }
        }
        return null;
    }

    private String getTransType(int i, NvsVideoTrack nvsVideoTrack) {
        NvsVideoClip clipByIndex = nvsVideoTrack.getClipByIndex(i);
        NvsVideoClip clipByIndex2 = nvsVideoTrack.getClipByIndex(i + 1);
        String obj = clipByIndex.getAttachment("fullscreenMode").toString();
        String obj2 = clipByIndex2.getAttachment("fullscreenMode").toString();
        return obj.equalsIgnoreCase("true") ? obj2.equalsIgnoreCase("true") ? "full" : "fh" : obj2.equalsIgnoreCase("true") ? "hf" : "half";
    }

    private boolean hasNullTrans(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals("")) {
                return true;
            }
        }
        return false;
    }

    private StringBuilder installAssetToContext(String str, boolean z) {
        int i;
        if (this.m_streamingContext == null || str == null) {
            return null;
        }
        String licFilePath = getLicFilePath(str);
        int i2 = 4;
        if (str.contains(".captionstyle")) {
            i = 2;
        } else {
            if (str.contains(".videofx")) {
                i2 = 0;
            } else if (str.contains(".videotransition")) {
                i2 = 1;
            } else if (str.contains(".animatedsticker")) {
                i2 = 3;
            }
            i = i2;
        }
        StringBuilder sb = new StringBuilder();
        if (!z) {
            int installAssetPackage = this.m_streamingContext.getAssetPackageManager().installAssetPackage(str, licFilePath, i, true, sb);
            if (installAssetPackage != 0 && installAssetPackage != 2) {
                Log.d(TAG, "Failed to install package!");
                return null;
            } else if (installAssetPackage == 2 && checkUpdateAsset(sb.toString(), str, i) && this.m_streamingContext.getAssetPackageManager().upgradeAssetPackage(str, licFilePath, i, true, sb) != 0) {
                Log.d(TAG, "Failed to upgrade package!");
                return null;
            }
        } else if (this.m_streamingContext.getAssetPackageManager().upgradeAssetPackage(str, licFilePath, i, true, sb) != 0) {
            Log.d(TAG, "Failed to upgrade package!");
            return null;
        }
        return sb;
    }

    private boolean installThemeAsset(Context context, String str, NvsThemeAsset nvsThemeAsset) {
        String str2 = str;
        NvsThemeAsset nvsThemeAsset2 = nvsThemeAsset;
        if (this.m_streamingContext == null) {
            this.m_streamingContext = NvsStreamingContext.getInstance();
        }
        if (this.m_streamingContext == null) {
            return false;
        }
        String readFile = Utils.readFile(str2 + "/info.json", context == null ? null : context.getAssets());
        if (readFile == null) {
            Log.d(TAG, "read theme info json file error!");
            return false;
        }
        NvsThemeAsset nvsThemeAsset3 = new NvsThemeAsset();
        nvsThemeAsset3.isDownload = context == null;
        try {
            JSONObject jSONObject = new JSONObject(readFile);
            if (jSONObject.has("hasFxTrans")) {
                nvsThemeAsset3.m_hasFxTrans = jSONObject.getBoolean("hasFxTrans");
            }
            if (jSONObject.has("transOffset")) {
                nvsThemeAsset3.m_transOffset = jSONObject.getInt("transOffset");
            } else {
                nvsThemeAsset3.m_transOffset = 500;
            }
            String assetPath = getAssetPath(jSONObject, "theme", str2, context == null);
            nvsThemeAsset3.m_themeId = installAssetToContext(assetPath, checkUpdateAsset(nvsThemeAsset2 == null ? null : nvsThemeAsset2.m_themeId.toString(), assetPath, 4));
            if (nvsThemeAsset3.m_themeId == null) {
                Log.d(TAG, "Failed to install theme package!");
                return false;
            }
            String assetPath2 = getAssetPath(jSONObject, "theme10s", str2, context == null);
            nvsThemeAsset3.m_theme10sId = installAssetToContext(assetPath2, checkUpdateAsset(nvsThemeAsset2 == null ? null : nvsThemeAsset2.m_theme10sId.toString(), assetPath2, 4));
            if (nvsThemeAsset3.m_theme10sId == null) {
                Log.d(TAG, "Failed to install theme 10s package!");
                return false;
            }
            if (jSONObject.has("cafSticker")) {
                String assetPath3 = getAssetPath(jSONObject, "cafSticker", str2, context == null);
                nvsThemeAsset3.m_cafStickerId = installAssetToContext(assetPath3, checkUpdateAsset(nvsThemeAsset2 == null ? null : nvsThemeAsset2.m_cafStickerId.toString(), assetPath3, 3));
            }
            if (jSONObject.has("cafSticker10s")) {
                String assetPath4 = getAssetPath(jSONObject, "cafSticker10s", str2, context == null);
                nvsThemeAsset3.m_cafSticker10sId = installAssetToContext(assetPath4, checkUpdateAsset(nvsThemeAsset2 == null ? null : nvsThemeAsset2.m_cafSticker10sId.toString(), assetPath4, 3));
            }
            String assetPath5 = getAssetPath(jSONObject, "sticker1", str2, context == null);
            nvsThemeAsset3.m_stickerId1 = installAssetToContext(assetPath5, checkUpdateAsset(nvsThemeAsset2 == null ? null : nvsThemeAsset2.m_stickerId1.toString(), assetPath5, 3));
            if (nvsThemeAsset3.m_stickerId1 == null) {
                Log.d(TAG, "Failed to install sticker package!");
                return false;
            }
            this.m_stickerId1 = nvsThemeAsset3.m_stickerId1.toString();
            String assetPath6 = getAssetPath(jSONObject, "sticker2", str2, context == null);
            nvsThemeAsset3.m_stickerId2 = installAssetToContext(assetPath6, checkUpdateAsset(nvsThemeAsset2 == null ? null : nvsThemeAsset2.m_stickerId2.toString(), assetPath6, 3));
            if (nvsThemeAsset3.m_stickerId2 == null) {
                Log.d(TAG, "Failed to install sticker2 package!");
                return false;
            }
            this.m_stickerId2 = nvsThemeAsset3.m_stickerId2.toString();
            if (jSONObject.has("blurFx")) {
                String assetPath7 = getAssetPath(jSONObject, "blurFx", str2, context == null);
                nvsThemeAsset3.m_blurFxId = installAssetToContext(assetPath7, checkUpdateAsset(nvsThemeAsset2 == null ? null : nvsThemeAsset2.m_blurFxId.toString(), assetPath7, 0));
                if (jSONObject.has("blurWithFxTrans")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("blurWithFxTrans");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        nvsThemeAsset3.m_blurWidthFxTransList.add(jSONArray.getString(i));
                    }
                }
            }
            try {
                nvsThemeAsset3.m_endingFxId = Utils.readFile(str2 + "/" + jSONObject.getString("endingVideoFX"), context == null ? null : this.m_context.getAssets());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                nvsThemeAsset3.m_endingFxId10s = Utils.readFile(str2 + "/" + jSONObject.getString("endingVideoFX10s"), context == null ? null : this.m_context.getAssets());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            nvsThemeAsset3.m_endingFx10sLen = jSONObject.getLong("endingVideoFX10sDuration") * 1000;
            String str3 = "assets:/" + str2 + "/black_block.png";
            if (context == null) {
                str3 = str2 + "/black_block.png";
            }
            nvsThemeAsset3.m_endingFxImgPath = str3;
            String readFile2 = Utils.readFile(str2 + "/" + jSONObject.getString("musicrhythm"), context == null ? null : context.getAssets());
            if (readFile2 != null) {
                nvsThemeAsset3.m_musicLen = NvsParseHelper.readMusicPoint(readFile2, nvsThemeAsset3.m_musicPoints, nvsThemeAsset3.m_transDesc);
            }
            String readFile3 = Utils.readFile(str2 + "/" + jSONObject.getString("musicrhythm10s"), context == null ? null : context.getAssets());
            if (readFile3 != null) {
                nvsThemeAsset3.m_musicLen10s = NvsParseHelper.readMusicPoint(readFile3, nvsThemeAsset3.m_musicPoints10s, (NvsTransDesc) null);
            }
            if (jSONObject.has("transition")) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("transition");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    String string = jSONArray2.getString(i2);
                    if (string != null) {
                        String str4 = "assets:/" + str2 + "/" + string;
                        if (context == null) {
                            str4 = str2 + "/" + string;
                        }
                        StringBuilder installAssetToContext = installAssetToContext(str4, nvsThemeAsset2 != null ? checkUpdateAsset(getTransId(string, nvsThemeAsset2), str4, 1) : false);
                        if (installAssetToContext != null) {
                            nvsThemeAsset3.m_transIDList.add(installAssetToContext);
                        }
                    }
                }
            }
            if (jSONObject.has("transFx")) {
                JSONArray jSONArray3 = jSONObject.getJSONArray("transFx");
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    String string2 = jSONArray3.getString(i3);
                    addToFxList(nvsThemeAsset3, str2 + "/" + string2, string2);
                }
            }
            if (nvsThemeAsset2 != null) {
                this.mThemeAssetMap.remove(nvsThemeAsset2);
            }
            this.mThemeAssetMap.put(str2, nvsThemeAsset3);
            return true;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    private boolean isLargeImg(NvsSize nvsSize) {
        NvsSize nvsSize2 = new NvsSize(nvsSize.width, nvsSize.height);
        if (nvsSize2.width >= nvsSize2.height) {
            nvsSize2.width = nvsSize.height;
            nvsSize2.height = nvsSize.width;
        }
        NvsVideoResolution videoRes = this.m_timeline.getVideoRes();
        double min = Math.min(Math.max(((double) videoRes.imageWidth) / ((double) nvsSize2.width), ((double) videoRes.imageHeight) / ((double) nvsSize2.height)), 1.0d);
        int i = nvsSize2.width;
        int i2 = nvsSize2.height;
        if (min < 1.0d) {
            i = (int) ((((double) nvsSize2.width) * min) + 0.5d);
            i2 = (int) ((((double) nvsSize2.height) * min) + 0.5d);
        }
        return i > 8192 || i2 > 8192;
    }

    private String manageString2Length(String str, int i) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String trim = str.trim();
        Paint paint = new Paint();
        paint.setTextSize((float) i);
        float screenWidth = ((float) Utils.getScreenWidth(this.m_context)) - 100.0f;
        float measureText = paint.measureText("😀") - paint.measureText("😀".substring(0, 1));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= trim.length()) {
                i2 = i3;
                break;
            }
            i3 = i2 + 1;
            float measureText2 = paint.measureText(trim.substring(0, i3));
            if (measureText2 < screenWidth) {
                i2 = i3;
            } else if (i2 <= trim.length() - 1 && measureText2 - paint.measureText(trim.substring(0, i2)) <= measureText) {
                i2--;
            }
        }
        return trim.substring(0, i2);
    }

    private String manageStringLength(String str, int i) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String trim = str.trim();
        Paint paint = new Paint();
        paint.setTextSize((float) i);
        float measureText = paint.measureText(FONT_MAX_LENGTH);
        float measureText2 = paint.measureText("😀") - paint.measureText("😀".substring(0, 1));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= trim.length()) {
                break;
            }
            i3 = i2 + 1;
            float measureText3 = paint.measureText(trim.substring(0, i3));
            if (measureText3 < measureText) {
                i2 = i3;
            } else if (i2 < trim.length() - 1) {
                int i4 = i2 + 2;
                if (paint.measureText(trim.substring(0, i4)) - measureText3 <= measureText2) {
                    i3 = i4;
                }
            } else {
                i3 = trim.length();
            }
        }
        if (i3 == trim.length()) {
            return trim.substring(0, i3);
        }
        return trim.substring(0, i3) + "...";
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02ab  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0145  */
    private void reBuildTimeLine(NvsTimeline nvsTimeline, NvsThemeAsset nvsThemeAsset, ArrayList<NvsImageFileDesc> arrayList, long j, NvsMusicFileDesc nvsMusicFileDesc, boolean z) {
        NvsTransDesc nvsTransDesc;
        long j2;
        boolean z2;
        boolean z3;
        ArrayList<NvsMusicPointDesc> arrayList2;
        Map<Integer, NvsMusicPointDesc> map;
        int i;
        Map<Integer, NvsMusicPointDesc> map2;
        NvsVideoTrack nvsVideoTrack;
        ArrayList<NvsMusicPointDesc> arrayList3;
        int i2;
        ArrayList<NvsMusicPointDesc> arrayList4;
        String str;
        int i3;
        String str2;
        ArrayList<NvsMusicPointDesc> arrayList5;
        int i4;
        NvsTimeline nvsTimeline2 = nvsTimeline;
        NvsThemeAsset nvsThemeAsset2 = nvsThemeAsset;
        ArrayList<NvsImageFileDesc> arrayList6 = arrayList;
        long j3 = j;
        NvsMusicFileDesc nvsMusicFileDesc2 = nvsMusicFileDesc;
        rand = new Random(this.seed);
        NvsStreamingContext instance = NvsStreamingContext.getInstance();
        if (instance != null && nvsTimeline2 != null && nvsThemeAsset2 != null && arrayList6 != null) {
            instance.clearCachedResources(false);
            arrayList.size();
            NvsTransDesc nvsTransDesc2 = nvsThemeAsset2.m_transDesc;
            long j4 = nvsThemeAsset2.m_musicLen;
            ArrayList<NvsMusicPointDesc> arrayList7 = nvsThemeAsset2.m_musicPoints;
            if (nvsMusicFileDesc2 != null) {
                if (!(nvsMusicFileDesc2.pointsDesc == null || nvsMusicFileDesc2.pointsDesc.size() <= 0 || nvsMusicFileDesc2.musicFile == null)) {
                    arrayList7 = nvsMusicFileDesc2.pointsDesc;
                }
                if (nvsMusicFileDesc2.transDesc != null) {
                    nvsTransDesc2 = nvsMusicFileDesc2.transDesc;
                }
                boolean z4 = this.m_selectedMusicType == 1;
                nvsTransDesc = nvsTransDesc2;
                j2 = nvsMusicFileDesc2.musicLen;
                z3 = true;
                z2 = z4;
            } else {
                nvsTransDesc = nvsTransDesc2;
                j2 = j4;
                z3 = false;
                z2 = false;
            }
            this.m_is10sMode = false;
            int i5 = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
            if (i5 > 0) {
                this.m_is10sMode = true;
                arrayList7 = z3 ? nvsMusicFileDesc2.pointsDesc10s : nvsThemeAsset2.m_musicPoints10s;
            }
            ArrayList<NvsImageFileDesc> rebuildFileList = getRebuildFileList(arrayList6, arrayList7, (i5 <= 0 || arrayList7.size() <= 0) ? (int) j3 : getClipCountInDuation(arrayList7, j3));
            if (rebuildFileList.size() < 1) {
                Log.e(TAG, "not any show file!");
                return;
            }
            if (rebuildFileList.size() <= 5) {
                this.m_is10sMode = true;
                arrayList2 = z3 ? nvsMusicFileDesc2.pointsDesc10s : nvsThemeAsset2.m_musicPoints10s;
            } else {
                arrayList2 = arrayList7;
            }
            cleanUpTimeLine(nvsTimeline);
            NvsVideoTrack appendVideoTrack = nvsTimeline.appendVideoTrack();
            if (appendVideoTrack == null) {
                Log.e(TAG, "appendVideoTrack failed!");
                return;
            }
            NvsVideoTrack nvsVideoTrack2 = appendVideoTrack;
            long j5 = j;
            ArrayList<NvsMusicPointDesc> arrayList8 = arrayList2;
            Map<Integer, NvsMusicPointDesc> buildVideoTrack = buildVideoTrack(rebuildFileList, appendVideoTrack, arrayList2, j2, j5, this.m_is10sMode);
            if (!z) {
                for (int i6 = 0; i6 < nvsVideoTrack2.getClipCount() - 1; i6++) {
                    nvsVideoTrack2.setBuiltinTransition(i6, (String) null);
                }
                return;
            }
            NvsVideoTrack nvsVideoTrack3 = nvsVideoTrack2;
            nvsTimeline2.applyTheme((this.m_is10sMode ? nvsThemeAsset2.m_theme10sId : nvsThemeAsset2.m_themeId).toString());
            this.m_cafStickerId = this.m_is10sMode ? nvsThemeAsset2.m_cafSticker10sId : nvsThemeAsset2.m_cafStickerId;
            if (this.m_cafStickerId != null) {
                if (!this.m_showCaption || (this.m_caption1Text.isEmpty() && this.m_caption2Text.isEmpty())) {
                    map = buildVideoTrack;
                    this.m_cafSticker = nvsTimeline.addAnimatedSticker(0, (long) (((double) this.timeBase) * 3.48d), this.m_cafStickerId.toString());
                    this.m_fxTransClipCount = 0;
                    i = 0;
                    while (i < nvsVideoTrack3.getClipCount() - 1) {
                        nvsVideoTrack3.setBuiltinTransition(i, (String) null);
                        Map<Integer, NvsMusicPointDesc> map3 = map;
                        if (map3.containsKey(Integer.valueOf(this.m_fxTransClipCount + i))) {
                            NvsMusicPointDesc nvsMusicPointDesc = map3.get(Integer.valueOf(this.m_fxTransClipCount + i));
                            if (!nvsMusicPointDesc.transNames.isEmpty()) {
                                setTrans(nvsVideoTrack3, i, nvsThemeAsset2.m_transIDList, nvsMusicPointDesc);
                            } else if (!nvsMusicPointDesc.fxNames.isEmpty()) {
                                String fxXml = getFxXml(i, nvsVideoTrack3, nvsThemeAsset2, nvsMusicPointDesc);
                                String str3 = "";
                                if (fxXml.contains("fxV3")) {
                                    if (this.m_fxTransClipCount + i + 2 < arrayList8.size()) {
                                        arrayList5 = arrayList8;
                                        NvsMusicPointDesc nvsMusicPointDesc2 = arrayList5.get(this.m_fxTransClipCount + i + 2);
                                        i4 = nvsMusicPointDesc2.transLen;
                                        if (!nvsMusicPointDesc2.transNames.isEmpty()) {
                                            str3 = nvsMusicPointDesc2.transNames.get(0);
                                        }
                                        str2 = str3;
                                    } else {
                                        arrayList5 = arrayList8;
                                        str2 = str3;
                                        i4 = 1000;
                                    }
                                    arrayList8 = arrayList5;
                                    map2 = map3;
                                    nvsVideoTrack = nvsVideoTrack3;
                                    applyFxTransV3(nvsVideoTrack3, i, fxXml, str2, (long) i4, nvsThemeAsset);
                                    arrayList3 = arrayList8;
                                } else {
                                    map2 = map3;
                                    nvsVideoTrack = nvsVideoTrack3;
                                    if (this.m_fxTransClipCount + i + 1 < arrayList8.size()) {
                                        arrayList4 = arrayList8;
                                        NvsMusicPointDesc nvsMusicPointDesc3 = arrayList4.get(this.m_fxTransClipCount + i + 1);
                                        i3 = nvsMusicPointDesc3.transLen;
                                        if (!nvsMusicPointDesc3.transNames.isEmpty()) {
                                            str3 = nvsMusicPointDesc3.transNames.get(0);
                                        }
                                        str = str3;
                                    } else {
                                        arrayList4 = arrayList8;
                                        str = str3;
                                        i3 = 1000;
                                    }
                                    arrayList8 = arrayList4;
                                    applyFxTransV2(nvsVideoTrack, i, fxXml, str, (long) i3, nvsThemeAsset);
                                    arrayList3 = arrayList8;
                                }
                            }
                            map2 = map3;
                            nvsVideoTrack = nvsVideoTrack3;
                            arrayList3 = arrayList8;
                        } else {
                            map2 = map3;
                            nvsVideoTrack = nvsVideoTrack3;
                            if (i < arrayList8.size()) {
                                arrayList3 = arrayList8;
                                i2 = arrayList3.get(i).transLen;
                            } else {
                                arrayList3 = arrayList8;
                                i2 = 1000;
                            }
                            if (nvsThemeAsset2.m_hasFxTrans) {
                                setFxTrans(nvsVideoTrack, i2, nvsTransDesc, nvsThemeAsset, i);
                            } else {
                                setTrans(nvsVideoTrack, i2, nvsTransDesc, nvsThemeAsset2.m_transIDList, i);
                            }
                        }
                        i++;
                        arrayList8 = arrayList3;
                        nvsVideoTrack3 = nvsVideoTrack;
                        map = map2;
                    }
                    if (z3) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("assets:/");
                        sb.append(this.m_is10sMode ? nvsMusicFileDesc2.musicFile10s : nvsMusicFileDesc2.musicFile);
                        String sb2 = sb.toString();
                        if (z2 || nvsMusicFileDesc2.isDownloadFile) {
                            sb2 = this.m_is10sMode ? nvsMusicFileDesc2.musicFile10s : nvsMusicFileDesc2.musicFile;
                        }
                        addMusicFile(nvsTimeline2, sb2, z2);
                    }
                    addCaption(nvsTimeline);
                    addEndingFx(nvsTimeline2, nvsThemeAsset2, this.m_is10sMode);
                    if (this.m_selectedMusicType != 0) {
                        NvsTimeline nvsTimeline3 = this.m_timeline;
                        float f = this.m_musicVolumeGain;
                        nvsTimeline3.setThemeMusicVolumeGain(f, f);
                    } else {
                        NvsAudioTrack audioTrackByIndex = this.m_timeline.getAudioTrackByIndex(0);
                        if (audioTrackByIndex != null) {
                            float f2 = this.m_musicVolumeGain;
                            audioTrackByIndex.setVolumeGain(f2, f2);
                        }
                    }
                    long j6 = this.timeBase;
                    long j7 = 5 * j6;
                    if (this.m_is10sMode) {
                        j7 = (long) (((double) j6) * 1.5d);
                    }
                    nvsTimeline2.setAudioFadeOutDuration(j7);
                }
            }
            map = buildVideoTrack;
            this.m_fxTransClipCount = 0;
            i = 0;
            while (i < nvsVideoTrack3.getClipCount() - 1) {
            }
            if (z3) {
            }
            addCaption(nvsTimeline);
            addEndingFx(nvsTimeline2, nvsThemeAsset2, this.m_is10sMode);
            if (this.m_selectedMusicType != 0) {
            }
            long j62 = this.timeBase;
            long j72 = 5 * j62;
            if (this.m_is10sMode) {
            }
            nvsTimeline2.setAudioFadeOutDuration(j72);
        }
    }

    private void reBuildTimeLineExt(long j, boolean z) {
        NvsMusicFileDesc nvsMusicFileDesc;
        NvsMusicFileDesc nvsMusicFileDesc2;
        NvsThemeAsset nvsThemeAsset = this.mThemeAssetMap.get(this.m_themeAssetID);
        if (nvsThemeAsset != null) {
            int i = this.m_selectedExtMusicIdx;
            if (i >= 0 && i < this.mMusicFileList.size()) {
                nvsMusicFileDesc2 = this.mMusicFileList.get(this.m_selectedExtMusicIdx);
            } else if (this.m_selectedLocalMusic != null) {
                nvsMusicFileDesc2 = new NvsMusicFileDesc();
                String str = this.m_selectedLocalMusic;
                nvsMusicFileDesc2.musicFile = str;
                nvsMusicFileDesc2.musicFile10s = str;
                String readFile = Utils.readFile(this.m_defaultRhythmPath, this.m_context.getAssets());
                if (readFile != null) {
                    nvsMusicFileDesc2.musicLen = NvsParseHelper.readMusicPoint(readFile, nvsMusicFileDesc2.pointsDesc, nvsMusicFileDesc2.transDesc);
                }
                String readFile2 = Utils.readFile(this.m_defaultRhythm10sPath, this.m_context.getAssets());
                if (readFile2 != null) {
                    nvsMusicFileDesc2.musicLen10s = NvsParseHelper.readMusicPoint(readFile2, nvsMusicFileDesc2.pointsDesc10s, (NvsTransDesc) null);
                }
                nvsMusicFileDesc2.jsonFile = null;
                nvsMusicFileDesc2.jsonFile10s = null;
            } else {
                nvsMusicFileDesc = null;
                reBuildTimeLine(this.m_timeline, nvsThemeAsset, this.m_inputIamgeInfo, j, nvsMusicFileDesc, z);
            }
            nvsMusicFileDesc = nvsMusicFileDesc2;
            reBuildTimeLine(this.m_timeline, nvsThemeAsset, this.m_inputIamgeInfo, j, nvsMusicFileDesc, z);
        }
    }

    private String saveBitmapToSD(Bitmap bitmap) {
        Context context = this.m_context;
        if (context == null) {
            Log.e("meicam", "m_context is null,can not make path!");
            return null;
        }
        File file = new File(context.getCacheDir(), "caption_bitmap");
        if (file.exists() || file.mkdirs()) {
            File file2 = new File(file, String.valueOf(System.nanoTime()) + ".png");
            if (file2.exists()) {
                file2.delete();
            }
            String absolutePath = file2.getAbsolutePath();
            if (bitmap == null || absolutePath.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("bt == null ");
                sb.append(bitmap == null);
                sb.append(" target_path.isEmpty(): ");
                sb.append(absolutePath.isEmpty());
                Log.e("meicam", sb.toString());
                return null;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(absolutePath));
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return absolutePath;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        } else {
            Log.e("meicam", "Failed to make caption bitmap directory");
            return null;
        }
    }

    private void setFxTrans(NvsVideoTrack nvsVideoTrack, int i, NvsTransDesc nvsTransDesc, NvsThemeAsset nvsThemeAsset, int i2) {
        NvsVideoTrack nvsVideoTrack2 = nvsVideoTrack;
        int i3 = i;
        NvsTransDesc nvsTransDesc2 = nvsTransDesc;
        NvsThemeAsset nvsThemeAsset2 = nvsThemeAsset;
        int i4 = i2;
        String transType = getTransType(i4, nvsVideoTrack);
        ArrayList<String> arrayList = new ArrayList<>();
        new ArrayList();
        if (transType.equalsIgnoreCase("full")) {
            float f = m_timelineRatio;
            arrayList = getTrans(nvsTransDesc2.m_fulltransList, nvsTransDesc2.m_fulltransFxList, nvsThemeAsset2.m_transIDList, ((double) f) >= 0.5625d ? nvsThemeAsset2.m_fullFx9v16List : f >= 0.5f ? nvsThemeAsset2.m_fullFx9v18List : ((double) f) >= 0.4804270462633452d ? nvsThemeAsset2.m_fullFx9vx73List : nvsThemeAsset2.m_fullFx9v19List);
        } else if (transType.equalsIgnoreCase("half")) {
            float f2 = m_timelineRatio;
            arrayList = getTrans(nvsTransDesc2.m_halftransList, nvsTransDesc2.m_halftransFxList, nvsThemeAsset2.m_transIDList, ((double) f2) >= 0.5625d ? nvsThemeAsset2.m_halfFx9v16List : f2 >= 0.5f ? nvsThemeAsset2.m_halfFx9v18List : ((double) f2) >= 0.4804270462633452d ? nvsThemeAsset2.m_halfFx9vx73List : nvsThemeAsset2.m_halfFx9v19List);
        } else if (transType.equalsIgnoreCase("fh")) {
            float f3 = m_timelineRatio;
            arrayList = getTrans(nvsTransDesc2.m_hftransList, nvsTransDesc2.m_fhtransFxList, nvsThemeAsset2.m_transIDList, ((double) f3) >= 0.5625d ? nvsThemeAsset2.m_fhFx9v16List : f3 >= 0.5f ? nvsThemeAsset2.m_fhFx9v18List : ((double) f3) >= 0.4804270462633452d ? nvsThemeAsset2.m_fhFx9vx73List : nvsThemeAsset2.m_fhFx9v19List);
        } else if (transType.equalsIgnoreCase("hf")) {
            float f4 = m_timelineRatio;
            arrayList = getTrans(nvsTransDesc2.m_hftransList, nvsTransDesc2.m_hftransFxList, nvsThemeAsset2.m_transIDList, ((double) f4) >= 0.5625d ? nvsThemeAsset2.m_hfFx9v16List : f4 >= 0.5f ? nvsThemeAsset2.m_hfFx9v18List : ((double) f4) >= 0.4804270462633452d ? nvsThemeAsset2.m_hfFx9vx73List : nvsThemeAsset2.m_hfFx9v19List);
        }
        if (arrayList.size() > 1) {
            String str = arrayList.get(0);
            String str2 = arrayList.get(1);
            if (str.contains("fxV3")) {
                applyFxTransV3(nvsVideoTrack, i2, str, str2, (long) i3, nvsThemeAsset);
            } else {
                applyFxTransV2(nvsVideoTrack, i2, str, str2, (long) i3, nvsThemeAsset);
            }
        } else {
            String str3 = arrayList.get(0);
            NvsVideoTransition nvsVideoTransition = null;
            if (str3.equalsIgnoreCase("")) {
                nvsVideoTrack.setBuiltinTransition(i4, (String) null);
            } else {
                nvsVideoTransition = str3.length() >= 30 ? nvsVideoTrack.setPackagedTransition(i4, str3) : nvsVideoTrack.setBuiltinTransition(i4, str3);
            }
            if (nvsVideoTransition != null) {
                nvsVideoTransition.setVideoTransitionDurationScaleFactor(((float) i3) / 1000.0f);
            }
        }
    }

    private void setImageMotion(NvsVideoClip nvsVideoClip, NvsImageFileDesc nvsImageFileDesc) {
        RectF rectF;
        RectF rectF2;
        if (nvsVideoClip != null) {
            if (nvsImageFileDesc.imgRatio == 0.0f) {
                NvsAVFileInfo aVFileInfo = this.m_streamingContext.getAVFileInfo(nvsVideoClip.getFilePath());
                nvsImageFileDesc.imgRatio = Utils.getImgRatio(aVFileInfo);
                nvsImageFileDesc.isLargeImg = isLargeImg(aVFileInfo.getVideoStreamDimension(0));
            }
            RectF rectF3 = null;
            if (nvsImageFileDesc.hasFace && nvsImageFileDesc.faceRect != null) {
                rectF3 = nvsImageFileDesc.faceRect;
            }
            if (!nvsImageFileDesc.isCover || (rectF = this.coverStartROI) == null || (rectF2 = this.coverEndROI) == null) {
                Utils.setImageMotion(nvsVideoClip, nvsImageFileDesc.imgRatio, nvsImageFileDesc.isLargeImg, rectF3);
            } else {
                nvsVideoClip.setImageMotionROI(rectF, rectF2);
                nvsVideoClip.setImageMotionMode(2);
            }
            nvsVideoClip.setSourceBackgroundMode(1);
            if (nvsVideoClip.getOutPoint() - nvsVideoClip.getInPoint() < this.timeBase) {
                nvsVideoClip.setImageMotionAnimationEnabled(false);
            }
        }
    }

    private void setTrans(NvsVideoTrack nvsVideoTrack, int i, NvsTransDesc nvsTransDesc, ArrayList<StringBuilder> arrayList, int i2) {
        String transType = getTransType(i2, nvsVideoTrack);
        String trans = transType.equalsIgnoreCase("full") ? getTrans(nvsTransDesc.m_fulltransList, arrayList) : transType.equalsIgnoreCase("half") ? getTrans(nvsTransDesc.m_halftransList, arrayList) : getTrans(nvsTransDesc.m_hftransList, arrayList);
        NvsVideoTransition nvsVideoTransition = null;
        if (trans.equalsIgnoreCase("")) {
            nvsVideoTrack.setBuiltinTransition(i2, (String) null);
        } else {
            nvsVideoTransition = trans.length() >= 30 ? nvsVideoTrack.setPackagedTransition(i2, trans) : nvsVideoTrack.setBuiltinTransition(i2, trans);
        }
        if (nvsVideoTransition != null) {
            nvsVideoTransition.setVideoTransitionDurationScaleFactor(((float) i) / 1000.0f);
        }
    }

    private void setTrans(NvsVideoTrack nvsVideoTrack, int i, ArrayList<StringBuilder> arrayList, NvsMusicPointDesc nvsMusicPointDesc) {
        int i2 = 0;
        NvsVideoTransition nvsVideoTransition = null;
        String str = (nvsMusicPointDesc.transNames == null || nvsMusicPointDesc.transNames.isEmpty()) ? null : nvsMusicPointDesc.transNames.get(0);
        if (str == null || str.equalsIgnoreCase("")) {
            nvsVideoTrack.setBuiltinTransition(i, (String) null);
            return;
        }
        while (true) {
            if (i2 >= arrayList.size()) {
                break;
            }
            String sb = arrayList.get(i2).toString();
            if (sb.contains(str)) {
                nvsVideoTransition = nvsVideoTrack.setPackagedTransition(i, sb);
                break;
            }
            i2++;
        }
        if (nvsVideoTransition == null) {
            nvsVideoTransition = nvsVideoTrack.setBuiltinTransition(i, str);
        }
        if (nvsVideoTransition != null) {
            nvsVideoTransition.setVideoTransitionDurationScaleFactor(((float) nvsMusicPointDesc.transLen) / 1000.0f);
        }
    }

    private void sortFileList(ArrayList<NvsImageFileDesc> arrayList) {
        if (arrayList != null && arrayList.size() >= 1) {
            NvsImageFileDesc nvsImageFileDesc = null;
            int i = 0;
            while (true) {
                if (i >= arrayList.size()) {
                    i = 0;
                    break;
                } else if (arrayList.get(i).isCover) {
                    nvsImageFileDesc = arrayList.get(i);
                    break;
                } else {
                    i++;
                }
            }
            if (nvsImageFileDesc != null) {
                arrayList.remove(i);
            }
            NvsParseHelper.sortFileByName(arrayList);
            NvsParseHelper.sortFileByModifyTime(arrayList);
            if (nvsImageFileDesc != null) {
                arrayList.add(0, nvsImageFileDesc);
            }
        }
    }

    public boolean applyTimelineTheme(String str, boolean z, boolean z2) {
        if (this.m_context == null || this.m_inputIamgeInfo.isEmpty() || str == null) {
            Log.d(TAG, "helper need init");
            return false;
        } else if (str.isEmpty()) {
            return false;
        } else {
            if (!installThemeAsset(z ? null : this.m_context, str, this.mThemeAssetMap.get(str))) {
                Log.d(TAG, "install theme asset error!, id:" + str);
                return false;
            }
            NvsThemeAsset nvsThemeAsset = this.mThemeAssetMap.get(str);
            if (nvsThemeAsset == null) {
                return false;
            }
            NvsStreamingContext instance = NvsStreamingContext.getInstance();
            if (instance != null) {
                instance.stop();
            }
            this.m_themeAssetID = str;
            this.m_showCaption = z2;
            this.m_selectedMusicType = 0;
            this.m_selectedExtMusicIdx = -1;
            this.m_musicVolumeGain = 1.0f;
            this.m_selectedLocalMusic = null;
            this.m_selectedLocalMusicStart = -1;
            this.m_selectedLocalMusicEnd = -1;
            reBuildTimeLine(this.m_timeline, nvsThemeAsset, this.m_inputIamgeInfo, -1, (NvsMusicFileDesc) null, true);
            return true;
        }
    }

    public boolean changeCaptionText(String str, int i) {
        if (str == null) {
            return false;
        }
        if (i == 0) {
            this.m_caption1Text = str;
        } else if (i != 1) {
            return false;
        } else {
            this.m_caption2Text = str;
        }
        addCaption(this.m_timeline);
        return true;
    }

    public boolean changeMusic(String str, int i) {
        NvsMusicFileDesc nvsMusicFileDesc;
        NvsStreamingContext instance = NvsStreamingContext.getInstance();
        if (instance != null) {
            instance.stop();
        }
        if (str == null) {
            this.m_musicVolumeGain = 0.0f;
            if (this.m_selectedMusicType == 0) {
                NvsTimeline nvsTimeline = this.m_timeline;
                float f = this.m_musicVolumeGain;
                nvsTimeline.setThemeMusicVolumeGain(f, f);
            } else {
                NvsAudioTrack audioTrackByIndex = this.m_timeline.getAudioTrackByIndex(0);
                if (audioTrackByIndex != null) {
                    float f2 = this.m_musicVolumeGain;
                    audioTrackByIndex.setVolumeGain(f2, f2);
                }
            }
            return true;
        }
        this.m_selectedMusicType = i;
        this.m_musicVolumeGain = 1.0f;
        if (i == 1) {
            NvsAVFileInfo aVFileInfo = instance.getAVFileInfo(str);
            if (aVFileInfo == null) {
                Log.d(TAG, "loacl music format is not suported!");
                return false;
            }
            changeLocalMusic(str, 0, aVFileInfo.getAudioStreamDuration(0));
            return true;
        } else if (i != 2) {
            return false;
        } else {
            int i2 = 0;
            while (true) {
                if (i2 >= this.mMusicFileList.size()) {
                    nvsMusicFileDesc = null;
                    break;
                }
                nvsMusicFileDesc = this.mMusicFileList.get(i2);
                if (nvsMusicFileDesc.id.equals(str)) {
                    this.m_selectedExtMusicIdx = i2;
                    break;
                }
                i2++;
            }
            boolean z = nvsMusicFileDesc == null;
            if (z) {
                String readFile = Utils.readFile(str + "/info.json", (AssetManager) null);
                if (readFile == null) {
                    Log.d(TAG, "read music info json error!");
                    return false;
                }
                try {
                    nvsMusicFileDesc = NvsParseHelper.GetMusicFileFromJsonObject(new JSONObject(readFile), str);
                    nvsMusicFileDesc.isDownloadFile = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (nvsMusicFileDesc == null) {
                Log.d(TAG, "currentSelected music item is null!");
                return false;
            }
            if (nvsMusicFileDesc.pointsDesc.size() <= 0) {
                String readFile2 = Utils.readFile(nvsMusicFileDesc.jsonFile, z ? null : this.m_context.getAssets());
                if (readFile2 != null) {
                    nvsMusicFileDesc.musicLen = NvsParseHelper.readMusicPoint(readFile2, nvsMusicFileDesc.pointsDesc, nvsMusicFileDesc.transDesc);
                }
                String readFile3 = Utils.readFile(nvsMusicFileDesc.jsonFile10s, z ? null : this.m_context.getAssets());
                if (readFile3 != null) {
                    nvsMusicFileDesc.musicLen10s = NvsParseHelper.readMusicPoint(readFile3, nvsMusicFileDesc.pointsDesc10s, (NvsTransDesc) null);
                }
            }
            if (z) {
                this.mMusicFileList.add(nvsMusicFileDesc);
                this.m_selectedExtMusicIdx = this.mMusicFileList.size() - 1;
            }
            this.m_selectedLocalMusic = null;
            this.m_selectedLocalMusicStart = -1;
            this.m_selectedLocalMusicEnd = -1;
            reBuildTimeLineExt(-1, true);
            return true;
        }
    }

    public boolean changeMusicVolumeGain(float f) {
        int i = this.m_selectedMusicType;
        if (i == 0) {
            this.m_timeline.setThemeMusicVolumeGain(f, f);
        } else if (i != 1 && i != 2) {
            return false;
        } else {
            NvsAudioTrack audioTrackByIndex = this.m_timeline.getAudioTrackByIndex(0);
            if (audioTrackByIndex != null) {
                audioTrackByIndex.setVolumeGain(f, f);
            }
        }
        this.m_musicVolumeGain = f;
        return true;
    }

    public boolean changeTimelineDuration(boolean z) {
        if (z) {
            reBuildTimeLineExt(this.timeBase * 10, true);
        } else {
            reBuildTimeLineExt(-2, true);
        }
        return true;
    }

    public boolean deleteClip(int i) {
        ArrayList<NvsImageFileDesc> arrayList = this.m_inputIamgeInfo;
        if (arrayList == null) {
            Log.d(TAG, "invalid file!");
            return false;
        } else if (arrayList.size() <= getMinImgCount()) {
            Log.d(TAG, "less than min clip count!");
            return false;
        } else if (this.m_inputIamgeInfo.size() <= i) {
            Log.d(TAG, "invalid clip index!");
            return false;
        } else {
            this.m_inputIamgeInfo.remove(i);
            randomFx();
            reBuildTimeLineExt(-1, true);
            return true;
        }
    }

    public String getCaptionText(int i) {
        String str;
        if (i == 0) {
            if (this.m_caption1 != null) {
                str = this.m_caption1Text;
                return str.replace("\n", "");
            }
        } else if (this.m_caption2 != null) {
            str = this.m_caption2Text;
            return str.replace("\n", "");
        }
        str = "";
        return str.replace("\n", "");
    }

    public String getCurrentThemeName() {
        return this.m_themeAssetID;
    }

    public ArrayList<NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc> getThumbnailImages() {
        int i;
        NvsTimeline nvsTimeline = this.m_timeline;
        String str = null;
        if (nvsTimeline == null) {
            return null;
        }
        int i2 = 0;
        NvsVideoTrack videoTrackByIndex = nvsTimeline.getVideoTrackByIndex(0);
        if (videoTrackByIndex == null) {
            return null;
        }
        ArrayList<NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc> arrayList = new ArrayList<>();
        while (i2 < videoTrackByIndex.getClipCount()) {
            NvsVideoClip clipByIndex = videoTrackByIndex.getClipByIndex(i2);
            if (clipByIndex == null) {
                i = i2;
            } else {
                NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc thumbnailSequenceDesc = new NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc();
                thumbnailSequenceDesc.mediaFilePath = clipByIndex.getFilePath();
                thumbnailSequenceDesc.trimIn = clipByIndex.getTrimIn();
                thumbnailSequenceDesc.trimOut = clipByIndex.getTrimOut();
                thumbnailSequenceDesc.inPoint = clipByIndex.getInPoint();
                thumbnailSequenceDesc.outPoint = clipByIndex.getOutPoint();
                thumbnailSequenceDesc.stillImageHint = true;
                String obj = clipByIndex.getAttachment("hasFxTrans") != null ? clipByIndex.getAttachment("hasFxTrans").toString() : str;
                if (obj == null || !obj.equalsIgnoreCase("true")) {
                    i = i2;
                    arrayList.add(thumbnailSequenceDesc);
                } else {
                    String obj2 = clipByIndex.getAttachment("fxFilePath").toString();
                    long longValue = Integer.valueOf(clipByIndex.getAttachment("fxInpoint").toString()).longValue() * 1000;
                    thumbnailSequenceDesc.trimOut = thumbnailSequenceDesc.trimIn + longValue;
                    thumbnailSequenceDesc.outPoint = thumbnailSequenceDesc.inPoint + longValue;
                    arrayList.add(thumbnailSequenceDesc);
                    NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc thumbnailSequenceDesc2 = new NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc();
                    thumbnailSequenceDesc2.mediaFilePath = obj2;
                    thumbnailSequenceDesc2.inPoint = thumbnailSequenceDesc.outPoint;
                    thumbnailSequenceDesc2.outPoint = clipByIndex.getOutPoint();
                    thumbnailSequenceDesc2.trimIn = 0;
                    i = i2;
                    thumbnailSequenceDesc2.trimOut = thumbnailSequenceDesc2.outPoint - thumbnailSequenceDesc2.inPoint;
                    thumbnailSequenceDesc2.stillImageHint = true;
                    if (clipByIndex.getAttachment("fxFileV3Path") != null) {
                        String obj3 = clipByIndex.getAttachment("fxFileV3Path").toString();
                        long longValue2 = Integer.valueOf(clipByIndex.getAttachment("fxV3Inpoint").toString()).longValue() * 1000;
                        thumbnailSequenceDesc2.trimOut = thumbnailSequenceDesc2.trimIn + longValue2;
                        thumbnailSequenceDesc2.outPoint = thumbnailSequenceDesc2.inPoint + longValue2;
                        arrayList.add(thumbnailSequenceDesc2);
                        NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc thumbnailSequenceDesc3 = new NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc();
                        thumbnailSequenceDesc3.mediaFilePath = obj3;
                        thumbnailSequenceDesc3.inPoint = thumbnailSequenceDesc2.outPoint;
                        thumbnailSequenceDesc3.outPoint = clipByIndex.getOutPoint();
                        thumbnailSequenceDesc3.trimIn = 0;
                        thumbnailSequenceDesc3.trimOut = thumbnailSequenceDesc3.outPoint - thumbnailSequenceDesc3.inPoint;
                        thumbnailSequenceDesc3.stillImageHint = true;
                        arrayList.add(thumbnailSequenceDesc3);
                    } else {
                        arrayList.add(thumbnailSequenceDesc2);
                    }
                }
            }
            i2 = i + 1;
            str = null;
        }
        return arrayList;
    }

    public long getTimelinePosition(int i) {
        ArrayList<NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc> thumbnailImages;
        NvsMultiThumbnailSequenceView.ThumbnailSequenceDesc thumbnailSequenceDesc;
        if (i >= 0 && (thumbnailImages = getThumbnailImages()) != null && i < thumbnailImages.size() && (thumbnailSequenceDesc = thumbnailImages.get(i)) != null) {
            return (thumbnailSequenceDesc.inPoint + thumbnailSequenceDesc.outPoint) / 2;
        }
        return 0;
    }

    public boolean initHelper(Context context, NvsTimeline nvsTimeline, ArrayList<NvsImageFileDesc> arrayList) {
        this.m_context = context;
        if (this.m_context == null) {
            Log.d(TAG, "Context can not be NULL");
            return false;
        } else if (nvsTimeline == null) {
            Log.d(TAG, "timeline can not be NULL");
            return false;
        } else if (arrayList == null || arrayList.size() < 1) {
            Log.d(TAG, "file list is empty");
            return false;
        } else {
            this.m_inputIamgeInfo = new ArrayList<>();
            for (int i = 0; i < arrayList.size(); i++) {
                this.m_inputIamgeInfo.add(arrayList.get(i).copy());
            }
            this.m_timeline = nvsTimeline;
            NvsVideoResolution videoRes = this.m_timeline.getVideoRes();
            m_timelineRatio = (((float) videoRes.imageWidth) * 1.0f) / ((float) videoRes.imageHeight);
            return true;
        }
    }

    public boolean insertClip(ArrayList<NvsImageFileDesc> arrayList) {
        boolean z;
        if (this.m_inputIamgeInfo == null || arrayList == null || arrayList.size() == 0) {
            Log.d(TAG, "invalid clip!");
            return false;
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            NvsImageFileDesc nvsImageFileDesc = arrayList.get(i);
            if (nvsImageFileDesc.alternative) {
                int i2 = 0;
                while (true) {
                    if (i2 >= this.m_inputIamgeInfo.size()) {
                        z = false;
                        break;
                    }
                    NvsImageFileDesc nvsImageFileDesc2 = this.m_inputIamgeInfo.get(i2);
                    if (nvsImageFileDesc2.filePath.contains(nvsImageFileDesc.filePath)) {
                        Log.d(TAG, "exist file!");
                        nvsImageFileDesc2.show = true;
                        z = true;
                        break;
                    }
                    i2++;
                }
                if (!z) {
                    NvsImageFileDesc copy = nvsImageFileDesc.copy();
                    copy.show = true;
                    arrayList2.add(copy);
                }
            } else {
                NvsImageFileDesc copy2 = nvsImageFileDesc.copy();
                copy2.show = true;
                arrayList2.add(copy2);
            }
        }
        this.m_inputIamgeInfo.addAll(arrayList2);
        randomFx();
        reBuildTimeLineExt(-1, true);
        return true;
    }

    public boolean moveClip(int i, int i2) {
        ArrayList<NvsImageFileDesc> arrayList;
        if (i >= this.m_inputIamgeInfo.size() || i2 >= this.m_inputIamgeInfo.size() || i < 0 || i2 < 0 || this.m_timeline == null || (arrayList = this.m_inputIamgeInfo) == null) {
            return false;
        }
        NvsImageFileDesc copy = arrayList.get(i).copy();
        this.m_inputIamgeInfo.remove(i);
        this.m_inputIamgeInfo.add(i2, copy);
        randomFx();
        reBuildTimeLineExt(-1, true);
        return true;
    }

    public void randomFx() {
        this.seed = System.nanoTime();
    }

    public boolean resetClip(ArrayList<NvsImageFileDesc> arrayList) {
        if (this.m_inputIamgeInfo == null || arrayList == null || arrayList.size() == 0) {
            Log.d(TAG, "invalid clip!");
            return false;
        }
        this.m_inputIamgeInfo.clear();
        return insertClip(arrayList);
    }

    public boolean setDefaultRhythmPath(String str, String str2) {
        this.m_defaultRhythmPath = str;
        this.m_defaultRhythm10sPath = str2;
        String str3 = this.m_defaultRhythmPath;
        if (str3 == null || this.m_defaultRhythm10sPath == null) {
            return false;
        }
        this.m_defaultRhythmPath = str3.replace("assets:/", "");
        this.m_defaultRhythm10sPath = this.m_defaultRhythm10sPath.replace("assets:/", "");
        return true;
    }

    public void setThemeEnabled(boolean z) {
        if (this.m_timeline != null && this.mThemeAssetMap != null && this.m_themeAssetID != null) {
            if (z) {
                reBuildTimeLineExt(-1, true);
            } else {
                reBuildTimeLineExt(-1, false);
            }
        }
    }
}
