package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.nexstreaming.app.common.nexasset.assetpackage.AssetPackageReader;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.util.b;
import com.nexstreaming.kminternal.kinemaster.config.EditorGlobal;
import com.nexstreaming.nexeditorsdk.exception.nexSDKException;
import com.nexstreaming.nexeditorsdk.nexCrop;
import com.nexstreaming.nexeditorsdk.nexOverlayImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class nexTemplateComposer {
    private static final String TAG = "nexTemplateComposer";
    private static final String TAG_BRIGHTNESS = "brightness";
    private static final String TAG_COLOR_FILTER = "color_filter";
    private static final String TAG_CONTRAST = "contrast";
    private static final String TAG_CROP_MODE = "crop_mode";
    private static final String TAG_CROP_SPEED = "image_crop_speed";
    private static final String TAG_DURATION = "duration";
    private static final String TAG_EFFECTS = "effects";
    private static final String TAG_EXTERNAL_IMAGE_PATH = "external_image_path";
    private static final String TAG_EXTERNAL_VIDEO_PATH = "external_video_path";
    private static final String TAG_ID = "id";
    private static final String TAG_IMAGE_CROP_MODE = "image_crop_mode";
    private static final String TAG_LUT = "lut";
    private static final String TAG_SATURATION = "saturation";
    private static final String TAG_SOLID_COLOR = "solid_color";
    private static final String TAG_SOURCE_TYPE = "source_type";
    private static final String TAG_SPEED_CONTROL = "speed_control";
    private static final String TAG_TEMPLATE = "template";
    private static final String TAG_TEMPLATE_BGM = "template_bgm";
    private static final String TAG_TEMPLATE_BGM_VOLUME = "template_bgm_volume";
    private static final String TAG_TEMPLATE_DESCRIPTION = "template_desc";
    private static final String TAG_TEMPLATE_INTRO = "template_intro";
    private static final String TAG_TEMPLATE_LETTERBOX = "template_letterbox";
    private static final String TAG_TEMPLATE_LOOP = "template_loop";
    private static final String TAG_TEMPLATE_NAME = "template_name";
    private static final String TAG_TEMPLATE_OUTRO = "template_outro";
    private static final String TAG_TEMPLATE_VERSION = "template_version";
    private static final String TAG_TYPE = "type";
    private static final String TAG_VIDEO_CROP_MODE = "video_crop_mode";
    private static final String TAG_VIGNETTE = "vignette";
    private static final String TAG_VOLUME = "volume";
    private static final String cancelMassage = "cancel template";
    int introCount = 0;
    int loopCount = 0;
    private Context mAppContext;
    private String mBGMPath = null;
    private boolean mBGMTempFile = false;
    private boolean mCancel = false;
    private InputStream mInputStream;
    private JSONArray mIntroTemplateArray = null;
    private ArrayList<HashMap<String, String>> mIntroTemplateList = new ArrayList<>();
    private JSONArray mLoopTemplateArray = null;
    private ArrayList<HashMap<String, String>> mLoopTemplateList = new ArrayList<>();
    private JSONArray mOutroTemplateArray = null;
    private ArrayList<HashMap<String, String>> mOutroTemplateList = new ArrayList<>();
    private boolean mOverlappedTransition = true;
    private ArrayList<a> mOverlayList = new ArrayList<>();
    private nexProject mProject;
    /* access modifiers changed from: private */
    public Context mResContext;
    private c mTemplate = null;
    private JSONArray mTemplateArray = null;
    private String mTemplateFilePath;
    private String mTemplateID;
    private ArrayList<HashMap<String, String>> mTemplateList = new ArrayList<>();
    private SparseArray<Integer> mTemplateTypeCountList = new SparseArray<>();
    private String mTemplateVersion = null;
    private boolean mUseProjectSpeed;
    int tempClipID = 0;
    ArrayList<HashMap<String, String>> templateList;
    int templateListID = 0;

    private class a {
        private int b;
        private int c;
        private int d;
        private boolean e;
        private int f;
        private int g;
        private int h;
        private int i;

        public a(int i2, int i3, int i4, boolean z, int i5, int i6, int i7, int i8) {
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = z;
            this.f = i5;
            this.g = i6;
            this.h = i7;
            this.i = i8;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.c;
        }

        public int c() {
            return this.d;
        }

        public boolean d() {
            return this.e;
        }

        public int e() {
            return this.f;
        }

        public int f() {
            return this.g;
        }

        public int g() {
            return this.h;
        }

        public int h() {
            return this.i;
        }
    }

    static String AssetPackageTemplateJsonToString(String str) {
        f c = c.a().c(str);
        if (c == null) {
            Log.e(TAG, "AssetPackageTemplateJsonToString info fail=" + str);
            return null;
        } else if (c.a().a(c.getAssetPackage())) {
            Log.e(TAG, "AssetPackageTemplateJsonToString expire item id=" + str);
            return null;
        } else {
            try {
                AssetPackageReader a2 = AssetPackageReader.a(com.nexstreaming.kminternal.kinemaster.config.a.a().b(), c.getPackageURI(), c.getAssetPackage().getAssetId());
                Log.d(TAG, "Template(" + str + ") Asset(" + c.getAssetPackage().getAssetIdx() + ") version(In DB)=" + c.getAssetPackage().getPackageVersion() + ", version(In reader)=" + a2.b());
                try {
                    InputStream a3 = a2.a(c.getFilePath());
                    if (a3 != null) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a3));
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                sb.append(readLine);
                                sb.append("\n");
                            } else {
                                a3.close();
                                String sb2 = sb.toString();
                                b.a(a2);
                                return sb2;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    b.a(a2);
                    throw th;
                }
                b.a(a2);
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    static boolean checkEffectId(nexEffectLibrary nexeffectlibrary, String str) {
        if (str == null || str.compareToIgnoreCase("none") == 0 || str.compareToIgnoreCase("null") == 0 || nexeffectlibrary.checkEffectID(str)) {
            return true;
        }
        Log.d(TAG, "missing effect: (" + str + "))");
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:40|41) */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        android.util.Log.d(TAG, "Info(name: (" + r13 + "))");
        r9 = "template 1.0";
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:40:0x00e1 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008b A[Catch:{ JSONException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00ac A[Catch:{ JSONException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00cd A[Catch:{ JSONException -> 0x00e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ff A[Catch:{ Exception | JSONException -> 0x0120 }] */
    static String[] checkMissEffects(nexEffectLibrary nexeffectlibrary, InputStream inputStream) {
        String str;
        JSONArray jSONArray;
        int i;
        JSONArray jSONArray2;
        int i2;
        JSONArray jSONArray3;
        int i3;
        String readFromFile = readFromFile(inputStream);
        ArrayList arrayList = new ArrayList();
        if (readFromFile != null) {
            try {
                JSONObject jSONObject = new JSONObject(readFromFile);
                String string = jSONObject.getString(TAG_TEMPLATE_NAME);
                String string2 = jSONObject.getString(TAG_TEMPLATE_VERSION);
                nexApplicationConfig.getDefaultLetterboxEffect();
                if (jSONObject.has(TAG_TEMPLATE_LETTERBOX)) {
                    jSONObject.getString(TAG_TEMPLATE_LETTERBOX);
                }
                String string3 = jSONObject.getString(TAG_TEMPLATE_DESCRIPTION);
                if (!string2.equals("v1.x")) {
                    if (!string2.equals("v2.0.0")) {
                        str = string2.equals("2.0.0") ? "template 2.0" : "template 1.0";
                        Log.d(TAG, "Info(name, version, description): (" + string + " ," + str + " ," + string3 + "))");
                        jSONArray = jSONObject.getJSONArray(TAG_TEMPLATE_INTRO);
                        for (i = 0; i < jSONArray.length(); i++) {
                            String string4 = jSONArray.getJSONObject(i).getString(TAG_EFFECTS);
                            if (!checkEffectId(nexeffectlibrary, string4)) {
                                arrayList.add(string4);
                            }
                        }
                        jSONArray2 = jSONObject.getJSONArray(TAG_TEMPLATE_LOOP);
                        for (i2 = 0; i2 < jSONArray2.length(); i2++) {
                            String string5 = jSONArray2.getJSONObject(i2).getString(TAG_EFFECTS);
                            if (!checkEffectId(nexeffectlibrary, string5)) {
                                arrayList.add(string5);
                            }
                        }
                        jSONArray3 = jSONObject.getJSONArray(TAG_TEMPLATE_OUTRO);
                        for (i3 = 0; i3 < jSONArray3.length(); i3++) {
                            String string6 = jSONArray3.getJSONObject(i3).getString(TAG_EFFECTS);
                            if (!checkEffectId(nexeffectlibrary, string6)) {
                                arrayList.add(string6);
                            }
                        }
                        if (str.equals("template 1.0")) {
                            JSONArray jSONArray4 = jSONObject.getJSONArray(TAG_TEMPLATE);
                            for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                                String string7 = jSONArray4.getJSONObject(i4).getString(TAG_EFFECTS);
                                if (!checkEffectId(nexeffectlibrary, string7)) {
                                    arrayList.add(string7);
                                }
                            }
                        }
                    }
                }
                str = "template 1.x";
                Log.d(TAG, "Info(name, version, description): (" + string + " ," + str + " ," + string3 + "))");
                jSONArray = jSONObject.getJSONArray(TAG_TEMPLATE_INTRO);
                while (i < jSONArray.length()) {
                }
                jSONArray2 = jSONObject.getJSONArray(TAG_TEMPLATE_LOOP);
                while (i2 < jSONArray2.length()) {
                }
                jSONArray3 = jSONObject.getJSONArray(TAG_TEMPLATE_OUTRO);
                while (i3 < jSONArray3.length()) {
                }
                if (str.equals("template 1.0")) {
                }
            } catch (Exception | JSONException unused) {
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        String[] strArr = new String[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            strArr[i5] = ((String) arrayList.get(i5)).toString();
        }
        return strArr;
    }

    static void in2sdcard(InputStream inputStream, File file) throws IOException {
        byte[] bArr = new byte[1024];
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        fileOutputStream2.close();
                        return;
                    }
                    fileOutputStream2.write(bArr, 0, read);
                } catch (IOException e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            try {
                throw e;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    static String readFromFile(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    inputStream.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    static String readFromFile(String str) {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(str)));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                    sb.append("\n");
                } else {
                    bufferedInputStream.close();
                    bufferedInputStream.close();
                    return sb.toString();
                }
            }
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e2) {
            return e2.getMessage();
        } catch (Throwable th) {
            r0.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public boolean addSpecialClip2Project(nexProject nexproject) {
        if (!((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("EXTERNAL_VIDEO") && !((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("EXTERNAL_IMAGE") && !((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("SOLID")) {
            return false;
        }
        if (((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("EXTERNAL_VIDEO")) {
            nexClip supportedClip = nexClip.getSupportedClip((String) this.templateList.get(this.templateListID).get(TAG_EXTERNAL_VIDEO_PATH));
            if (supportedClip != null) {
                nexproject.add(supportedClip);
                ArrayList<HashMap<String, String>> arrayList = this.templateList;
                if (arrayList == this.mIntroTemplateList) {
                    this.introCount++;
                } else if (arrayList == this.mLoopTemplateList) {
                    this.loopCount++;
                }
                this.tempClipID++;
            }
        } else {
            int parseInt = Integer.parseInt((String) this.templateList.get(this.templateListID).get(TAG_DURATION));
            if (((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("EXTERNAL_IMAGE")) {
                nexClip supportedClip2 = nexClip.getSupportedClip((String) this.templateList.get(this.templateListID).get(TAG_EXTERNAL_IMAGE_PATH));
                if (supportedClip2 != null) {
                    supportedClip2.setImageClipDuration(parseInt);
                    nexproject.add(supportedClip2);
                    ArrayList<HashMap<String, String>> arrayList2 = this.templateList;
                    if (arrayList2 == this.mIntroTemplateList) {
                        this.introCount++;
                    } else if (arrayList2 == this.mLoopTemplateList) {
                        this.loopCount++;
                    }
                    this.tempClipID++;
                }
            } else {
                nexClip solidClip = nexClip.getSolidClip(Color.parseColor((String) this.templateList.get(this.templateListID).get(TAG_SOLID_COLOR)));
                if (solidClip != null) {
                    solidClip.setImageClipDuration(parseInt);
                    nexproject.add(solidClip);
                    ArrayList<HashMap<String, String>> arrayList3 = this.templateList;
                    if (arrayList3 == this.mIntroTemplateList) {
                        this.introCount++;
                    } else if (arrayList3 == this.mLoopTemplateList) {
                        this.loopCount++;
                    }
                    this.tempClipID++;
                }
            }
        }
        manageTemplateList(true);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addTemplateOverlay(int i, String str) {
        String[] split = str.split(",");
        String str2 = split[0];
        String str3 = split[1];
        String str4 = split[2];
        String str5 = split[3];
        String str6 = split[4];
        String str7 = split[5];
        String str8 = split[6];
        String str9 = split[7];
        Log.d(TAG, "clipID=" + i + " /type=" + str2 + " /duration=" + str3 + " /variation=" + str4 + " /activeAnimation=" + str5 + " /inAnimationStartTime=" + str6 + " /inAnimationTime=" + str7 + " /outAnimationStartTime=" + str8 + " /outAnimationTime=" + str9);
        if (str2.equals("overlay")) {
            this.mOverlayList.add(new a(i, Integer.parseInt(str3), Integer.parseInt(str4), Boolean.parseBoolean(str5), Integer.parseInt(str6), Integer.parseInt(str7), Integer.parseInt(str8), Integer.parseInt(str9)));
            return;
        }
    }

    /* access modifiers changed from: package-private */
    public void applyBGM2Project(nexProject nexproject, String str) {
        if (str != null) {
            Log.d(TAG, "bgm path1=" + str);
            if (this.mTemplateID != null) {
                if (str.equalsIgnoreCase("null")) {
                    nexproject.setBackgroundMusicPath((String) null);
                } else {
                    nexproject.setBackgroundMusicPath(nexAssetPackageManager.getAssetPackageMediaPath(this.mAppContext, str));
                }
            } else if (str.equalsIgnoreCase("null")) {
                nexproject.setBackgroundMusicPath((String) null);
            } else if (str.regionMatches(true, 0, TAG_TEMPLATE, 0, 8)) {
                try {
                    nexproject.setBackgroundMusicPath(raw2file(this.mAppContext, this.mResContext, str));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (str.contains("/storage/")) {
                    String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                    str = str.contains("/storage/emulated/0") ? str.replace("/storage/emulated/0", absolutePath) : str.replace("/storage", absolutePath);
                }
                nexproject.setBackgroundMusicPath(str);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String applyTemplateOnProject(nexProject nexproject) {
        c cVar = this.mTemplate;
        if (cVar == null) {
            return "Template did not exist while apply template 2.0";
        }
        int g = cVar.g();
        for (int i = 0; i < nexproject.getTotalClipCount(true); i++) {
            nexClip clip = nexproject.getClip(i, true);
            if (clip.getClipType() == 1) {
                clip.setImageClipDuration(g);
            }
        }
        nexproject.updateProject();
        nexproject.setTemplageOverlappedTransitionMode(this.mOverlappedTransition);
        String a2 = this.mTemplate.a(nexproject, this.mAppContext, this.mResContext, this.mUseProjectSpeed, this.mOverlappedTransition);
        if (a2 != null) {
            return a2;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0270 A[LOOP:0: B:1:0x0017->B:53:0x0270, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x026c A[SYNTHETIC] */
    public void consistProjectViaVer1(nexProject nexproject) {
        boolean z;
        int i;
        nexProject nexproject2 = nexproject;
        this.templateList = this.mTemplateList;
        nexProject clone = nexProject.clone(nexproject);
        boolean z2 = true;
        nexproject2.allClear(true);
        int i2 = 0;
        this.templateListID = 0;
        int i3 = 100;
        int i4 = 0;
        while (true) {
            if (clone.getClip(i4, z2).getClipType() == 4) {
                int rotateDegree = clone.getClip(i4, z2).getRotateDegree();
                if (this.mUseProjectSpeed) {
                    i3 = clone.getClip(i4, z2).getVideoClipEdit().getSpeedControl();
                }
                int i5 = i3;
                int startTrimTime = clone.getClip(i4, z2).getVideoClipEdit().getStartTrimTime();
                int duration = this.mUseProjectSpeed ? clone.getClip(i4, z2).getVideoClipEdit().getDuration() : (clone.getClip(i4, z2).getVideoClipEdit().getEndTrimTime() == 0 ? clone.getClip(i4, z2).getTotalTime() : clone.getClip(i4, z2).getVideoClipEdit().getEndTrimTime()) - startTrimTime;
                nexClip dup = nexClip.dup(clone.getClip(i4, z2));
                int i6 = startTrimTime;
                boolean z3 = z2;
                while (!this.mCancel) {
                    int parseInt = Integer.parseInt((String) this.templateList.get(this.templateListID).get(TAG_DURATION));
                    if (parseInt < 0) {
                        parseInt = i2;
                    }
                    Log.d(TAG, "remainTime2Project=" + duration + ", defined_duration=" + parseInt + ", templateListID=" + this.templateListID);
                    duration -= parseInt;
                    if (duration <= 500) {
                        parseInt += duration;
                        z3 = false;
                    }
                    Log.d(TAG, "index=" + 0 + ", defined_duration=" + parseInt + ", loop=" + z3);
                    if (this.mUseProjectSpeed) {
                        parseInt = (int) (((float) parseInt) * (((float) i5) / 100.0f));
                    }
                    nexproject2.add(nexClip.dup(dup));
                    if (((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("ALL") || ((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("VIDEO")) {
                        nexproject.getLastPrimaryClip().setRotateDegree(rotateDegree);
                        if (this.mUseProjectSpeed) {
                            nexproject.getLastPrimaryClip().getVideoClipEdit().setSpeedControl(i5);
                        }
                        nexproject.getLastPrimaryClip().getVideoClipEdit().setTrim(i6, i6 + parseInt);
                    }
                    Rect rect = new Rect();
                    clone.getClip(i4, true).getCrop().getStartPositionRaw(rect);
                    nexproject.getLastPrimaryClip().getCrop().setStartPositionRaw(rect);
                    clone.getClip(i4, true).getCrop().getEndPositionRaw(rect);
                    nexproject.getLastPrimaryClip().getCrop().setEndPositionRaw(rect);
                    i6 += parseInt;
                    this.templateListID += 2;
                    Log.d(TAG, "templateListID=" + this.templateListID + ", templateList.size()=" + this.templateList.size());
                    if (this.templateListID >= this.templateList.size()) {
                        this.templateListID = 0;
                    }
                    if (!z3) {
                        i3 = i5;
                    } else {
                        i2 = 0;
                    }
                }
                return;
            } else if (clone.getClip(i4, z2).getClipType() == z2) {
                int parseInt2 = Integer.parseInt((String) this.templateList.get(this.templateListID).get(TAG_DURATION));
                int rotateDegree2 = clone.getClip(i4, z2).getRotateDegree();
                nexClip dup2 = nexClip.dup(clone.getClip(i4, z2));
                nexproject2.add(dup2);
                dup2.setRotateDegree(rotateDegree2);
                dup2.setImageClipDuration(parseInt2);
                Rect rect2 = new Rect();
                clone.getClip(i4, z2).getCrop().getStartPositionRaw(rect2);
                dup2.getCrop().setStartPositionRaw(rect2);
                clone.getClip(i4, z2).getCrop().getEndPositionRaw(rect2);
                dup2.getCrop().setEndPositionRaw(rect2);
                this.templateListID += 2;
                if (this.templateListID >= this.templateList.size()) {
                    i = 0;
                    this.templateListID = 0;
                    z = true;
                    i4++;
                    if (i4 >= clone.getTotalClipCount(z)) {
                        nexproject.updateProject();
                        return;
                    } else {
                        i2 = i;
                        z2 = z;
                    }
                }
            } else {
                i = 0;
                StringBuilder sb = new StringBuilder();
                sb.append("not support_type in template:");
                z = true;
                sb.append(clone.getClip(i4, true).getClipType());
                Log.d(TAG, sb.toString());
                i4++;
                if (i4 >= clone.getTotalClipCount(z)) {
                }
            }
            i = 0;
            z = true;
            i4++;
            if (i4 >= clone.getTotalClipCount(z)) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0474 A[LOOP:3: B:23:0x0120->B:117:0x0474, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x046b A[SYNTHETIC] */
    public void consistProjectViaVer2(nexProject nexproject) {
        String str;
        int i;
        int i2;
        boolean z;
        String str2;
        nexProject nexproject2;
        int i3;
        int i4;
        boolean z2;
        int i5;
        nexProject nexproject3;
        int i6;
        int i7;
        int i8;
        nexProject nexproject4;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z3;
        boolean z4;
        int i14;
        boolean z5;
        int i15;
        int i16;
        boolean z6;
        int i17;
        boolean z7;
        nexProject nexproject5 = nexproject;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (true) {
            int size = this.mIntroTemplateList.size();
            str = TAG_DURATION;
            if (i19 >= size) {
                break;
            }
            i20 += Integer.parseInt((String) this.mIntroTemplateList.get(i19).get(str));
            i19 += 2;
        }
        int i21 = 0;
        for (int i22 = 0; i22 < this.mOutroTemplateList.size(); i22 += 2) {
            i21 += Integer.parseInt((String) this.mOutroTemplateList.get(i22).get(str));
        }
        this.templateList = this.mIntroTemplateList;
        nexProject clone = nexProject.clone(nexproject);
        boolean z8 = true;
        nexproject5.allClear(true);
        int i23 = 0;
        int i24 = 0;
        boolean z9 = false;
        while (true) {
            if (clone.getClip(i23, z8).getClipType() == 4) {
                int rotateDegree = clone.getClip(i23, z8).getRotateDegree();
                int speedControl = clone.getClip(i23, z8).getVideoClipEdit().getSpeedControl();
                int startTrimTime = clone.getClip(i23, z8).getVideoClipEdit().getStartTrimTime();
                int totalTime = clone.getClip(i23, z8).getVideoClipEdit().getEndTrimTime() == 0 ? clone.getClip(i23, z8).getTotalTime() : clone.getClip(i23, z8).getVideoClipEdit().getEndTrimTime();
                nexClip dup = nexClip.dup(clone.getClip(i23, z8));
                this.tempClipID = nexproject5.getTotalClipCount(z8);
                if (this.tempClipID < 0) {
                    this.tempClipID = i18;
                }
                int i25 = totalTime - startTrimTime;
                int i26 = i20 + i21;
                if (i26 >= i25) {
                    z2 = z9;
                    StringBuilder sb = new StringBuilder();
                    i4 = i24;
                    sb.append("OVER durationOfSourceClip/ introDuration:");
                    sb.append(i20);
                    sb.append(" outroDuration:");
                    sb.append(i21);
                    sb.append(" sourceDuration:");
                    sb.append(i25);
                    Log.d(TAG, sb.toString());
                    i3 = i23;
                    i5 = i25;
                } else {
                    i4 = i24;
                    z2 = z9;
                    i5 = i25 - i21;
                    StringBuilder sb2 = new StringBuilder();
                    i3 = i23;
                    sb2.append("UNDER durationOfSourceClip/ introDuration:");
                    sb2.append(i20);
                    sb2.append(" outroDuration:");
                    sb2.append(i21);
                    sb2.append(" remainTime2Project:");
                    sb2.append(i5);
                    Log.d(TAG, sb2.toString());
                }
                int i27 = startTrimTime;
                boolean z10 = z2;
                int i28 = i4;
                i2 = i20;
                while (!this.mCancel) {
                    addSpecialClip2Project(nexproject);
                    i = i21;
                    int parseInt = Integer.parseInt((String) this.templateList.get(this.templateListID).get(str)) < 0 ? 0 : Integer.parseInt((String) this.templateList.get(this.templateListID).get(str));
                    int i29 = i5 - parseInt;
                    int i30 = totalTime;
                    int i31 = this.templateListID;
                    while (true) {
                        i31 += 2;
                        nexproject3 = clone;
                        if (i31 >= this.templateList.size()) {
                            i31 = 0;
                        }
                        i6 = i28;
                        i7 = i27;
                        if (!((String) this.templateList.get(i31).get(TAG_SOURCE_TYPE)).equals("EXTERNAL_VIDEO") && !((String) this.templateList.get(i31).get(TAG_SOURCE_TYPE)).equals("EXTERNAL_IMAGE") && !((String) this.templateList.get(i31).get(TAG_SOURCE_TYPE)).equals("SOLID")) {
                            break;
                        }
                        i30 = i30;
                        i27 = i7;
                        i28 = i6;
                        str = str;
                        i5 = i5;
                        z10 = z10;
                        clone = nexproject3;
                        i26 = i26;
                    }
                    int parseInt2 = Integer.parseInt((String) this.templateList.get(i31).get(str));
                    String str3 = str;
                    int i32 = i5;
                    if (this.templateList == this.mOutroTemplateList || z10) {
                        boolean z11 = z10;
                        nexproject4 = nexproject3;
                        int i33 = i7;
                        i11 = i26;
                        nexproject5.add(nexClip.dup(dup));
                        nexClip clip = nexproject5.getClip(this.tempClipID, true);
                        if ((((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("ALL") || ((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("VIDEO")) && clip.getClipType() == 4) {
                            clip.setRotateDegree(rotateDegree);
                            clip.getVideoClipEdit().setSpeedControl(speedControl);
                            i12 = i30;
                            clip.getVideoClipEdit().setTrim(i33, i12);
                        } else {
                            i12 = i30;
                        }
                        i13 = i33 + i12;
                        if (z11) {
                            ArrayList<HashMap<String, String>> arrayList = this.templateList;
                            if (arrayList == this.mIntroTemplateList) {
                                z4 = true;
                                this.introCount++;
                            } else {
                                z4 = true;
                                if (arrayList == this.mLoopTemplateList) {
                                    this.loopCount++;
                                }
                            }
                            manageTemplateList(z4);
                            z3 = false;
                        } else {
                            z4 = true;
                            z3 = z11;
                        }
                        i14 = i6;
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("id:");
                        boolean z12 = z10;
                        sb3.append(Integer.parseInt((String) this.templateList.get(this.templateListID).get(TAG_ID)));
                        sb3.append("/ current defined duration:");
                        sb3.append(parseInt);
                        sb3.append("/ next defined duration:");
                        sb3.append(parseInt2);
                        sb3.append("/ remain duration(based on source_duration - outro_duration)):");
                        sb3.append(i29);
                        Log.d(TAG, sb3.toString());
                        if (i26 >= i25) {
                            nexproject5.add(nexClip.dup(dup));
                            int i34 = this.tempClipID;
                            this.tempClipID = i34 + 1;
                            nexClip clip2 = nexproject5.getClip(i34, true);
                            if ((((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("ALL") || ((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("VIDEO")) && clip2.getClipType() == 4) {
                                clip2.setRotateDegree(rotateDegree);
                                clip2.getVideoClipEdit().setSpeedControl(speedControl);
                                i17 = i7;
                                clip2.getVideoClipEdit().setTrim(i17, i7 + (i32 / 2));
                            } else {
                                i17 = i7;
                            }
                            i27 = i17 + (i32 / 2);
                            if (this.templateList == this.mIntroTemplateList) {
                                z7 = true;
                                this.introCount++;
                            } else {
                                z7 = true;
                                this.loopCount++;
                            }
                            i28 = i6 + 1;
                            nexproject4 = nexproject3;
                            if (i28 < nexproject4.getTotalClipCount(z7)) {
                                Log.d(TAG, "video,case 1] some clips exist in the source project.");
                                manageTemplateList(z7);
                                z10 = z7;
                            } else {
                                this.introCount += z7 ? 1 : 0;
                                manageTemplateList(false);
                                z10 = z12;
                            }
                            i8 = i26;
                            i9 = i30;
                            i10 = i32;
                        } else {
                            nexproject4 = nexproject3;
                            int i35 = i7;
                            if (i29 <= parseInt2) {
                                i11 = i26;
                                nexproject5.add(nexClip.dup(dup));
                                int i36 = this.tempClipID;
                                this.tempClipID = i36 + 1;
                                nexClip clip3 = nexproject5.getClip(i36, true);
                                if ((((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("ALL") || ((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("VIDEO")) && clip3.getClipType() == 4) {
                                    clip3.setRotateDegree(rotateDegree);
                                    clip3.getVideoClipEdit().setSpeedControl(speedControl);
                                    clip3.getVideoClipEdit().setTrim(i35, i35 + (i29 / 2) + parseInt);
                                }
                                i13 = i35 + parseInt + (i29 / 2);
                                if (this.templateList == this.mIntroTemplateList) {
                                    z5 = true;
                                    this.introCount++;
                                } else {
                                    z5 = true;
                                    this.loopCount++;
                                }
                                i14 = i6 + 1;
                                if (i14 < nexproject4.getTotalClipCount(z5)) {
                                    Log.d(TAG, "video,case 3] the certain clip exist in the source project.");
                                    manageTemplateList(z5);
                                    z4 = z5;
                                    z3 = z4;
                                    i12 = i30;
                                } else {
                                    manageTemplateList(false);
                                    z = z5;
                                    i15 = i30;
                                    i16 = i32;
                                }
                            } else if (i29 < 0) {
                                i8 = i26;
                                i27 = i35;
                                i9 = i30;
                                i28 = i6;
                                i10 = i32;
                                z10 = z12;
                            } else {
                                nexproject5.add(nexClip.dup(dup));
                                int i37 = this.tempClipID;
                                this.tempClipID = i37 + 1;
                                nexClip clip4 = nexproject5.getClip(i37, true);
                                i11 = i26;
                                if ((((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("ALL") || ((String) this.templateList.get(this.templateListID).get(TAG_SOURCE_TYPE)).equals("VIDEO")) && clip4.getClipType() == 4) {
                                    clip4.setRotateDegree(rotateDegree);
                                    clip4.getVideoClipEdit().setSpeedControl(speedControl);
                                    clip4.getVideoClipEdit().setTrim(i35, i35 + parseInt);
                                }
                                i13 = i35 + parseInt;
                                if (this.templateList == this.mIntroTemplateList) {
                                    z6 = true;
                                    this.introCount++;
                                } else {
                                    z6 = true;
                                    this.loopCount++;
                                }
                                manageTemplateList(z6);
                                z = z6;
                                i16 = i29;
                                i15 = i30;
                                i14 = i6;
                            }
                            z10 = z12;
                            if (i27 >= i9) {
                                i24 = i28;
                                nexproject2 = nexproject4;
                                i23 = i3;
                                str2 = str3;
                                z9 = z10;
                            } else {
                                i21 = i;
                                i26 = i8;
                                str = str3;
                                nexProject nexproject6 = nexproject4;
                                totalTime = i9;
                                clone = nexproject6;
                            }
                        }
                        z = true;
                        if (i27 >= i9) {
                        }
                    }
                    i10 = i32;
                    if (i27 >= i9) {
                    }
                }
                return;
            }
            nexproject2 = clone;
            i2 = i20;
            i = i21;
            String str4 = str;
            z = z8;
            int i38 = i24;
            boolean z13 = z9;
            if (nexproject2.getClip(i23, z).getClipType() == z) {
                addSpecialClip2Project(nexproject);
                str2 = str4;
                int parseInt3 = Integer.parseInt((String) this.templateList.get(this.templateListID).get(str2));
                int rotateDegree2 = nexproject2.getClip(i23, z).getRotateDegree();
                nexClip dup2 = nexClip.dup(nexproject2.getClip(i23, z));
                dup2.setRotateDegree(rotateDegree2);
                dup2.setImageClipDuration(parseInt3);
                nexproject5.add(dup2);
                i24 = i38 + 1;
                if (i24 < nexproject2.getTotalClipCount(z)) {
                    if (this.templateList == this.mIntroTemplateList) {
                        this.introCount += z;
                    } else {
                        this.loopCount += z;
                    }
                    if (i24 != nexproject2.getTotalClipCount(z) - z) {
                        Log.d(TAG, "image] some clips exist in the source project.");
                        manageTemplateList(z);
                    } else if (nexproject2.getClip(i24, z).getClipType() == 4) {
                        Log.d(TAG, "image] the clipType of last clip is video, and go continually");
                        manageTemplateList(z);
                    } else {
                        Log.d(TAG, "image] the clipType of last clip is image, and goto OutTroTemplate");
                        manageTemplateList(false);
                    }
                } else {
                    Log.d(TAG, "image] No more any clip after this image clip.");
                    this.mTemplateTypeCountList.append(2, Integer.valueOf(z ? 1 : 0));
                }
                z9 = z13;
            } else {
                str2 = str4;
                Log.d(TAG, "not support_type in template:" + nexproject2.getClip(i23, z).getClipType());
                z9 = z13;
                i24 = i38;
            }
            i23++;
            if (i23 >= nexproject2.getTotalClipCount(z)) {
                Log.d(TAG, "intro:" + this.mTemplateTypeCountList.get(0) + " loop:" + this.mTemplateTypeCountList.get(1) + " out-ro:" + this.mTemplateTypeCountList.get(2));
                nexproject.updateProject();
                return;
            }
            clone = nexproject2;
            str = str2;
            i20 = i2;
            i21 = i;
            i18 = 0;
            z8 = true;
        }
    }

    public nexProject createProject() {
        return new nexProject();
    }

    /* access modifiers changed from: package-private */
    public nexColorEffect getColorEffect(int i) {
        return nexColorEffect.getPresetList().get(i);
    }

    /* access modifiers changed from: package-private */
    public String getValue(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            if (str.equals(TAG_EFFECTS)) {
                return "none";
            }
            if (str.equals(TAG_ID) || str.equals(TAG_VOLUME)) {
                return MovieStatUtils.DOWNLOAD_SUCCESS;
            }
            if (str.equals(TAG_SOURCE_TYPE)) {
                return "ALL";
            }
            if (str.equals(TAG_EXTERNAL_VIDEO_PATH) || str.equals(TAG_EXTERNAL_IMAGE_PATH) || str.equals(TAG_SOLID_COLOR) || str.equals(TAG_LUT)) {
                return null;
            }
            return str.equals(TAG_VIGNETTE) ? "clip,no" : str.equals(TAG_CROP_MODE) ? "" : str.equals(TAG_CROP_SPEED) ? MovieStatUtils.DOWNLOAD_SUCCESS : "default";
        }
    }

    /* access modifiers changed from: package-private */
    public void in2file(Context context, InputStream inputStream, String str) throws Exception {
        byte[] bArr = new byte[1024];
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(str, 1);
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Exception e) {
            throw e;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void initTemplateComposer(nexProject nexproject, Context context, Context context2, String str) throws nexSDKException {
        this.mProject = nexproject;
        this.mAppContext = context;
        this.mResContext = context2;
        this.mCancel = false;
        this.mTemplateID = null;
        this.mTemplateFilePath = null;
        this.mInputStream = null;
        this.mTemplateID = str;
        release();
        this.mTemplateTypeCountList.append(0, 0);
        this.mTemplateTypeCountList.append(1, 0);
        this.mTemplateTypeCountList.append(2, 0);
    }

    /* access modifiers changed from: package-private */
    public void manageTemplateList(boolean z) {
        ArrayList<HashMap<String, String>> arrayList = this.templateList;
        if (arrayList == this.mIntroTemplateList) {
            this.mTemplateTypeCountList.append(0, Integer.valueOf(this.introCount));
        } else if (arrayList == this.mLoopTemplateList) {
            this.mTemplateTypeCountList.append(1, Integer.valueOf(this.loopCount));
        }
        this.templateListID += 2;
        Log.d(TAG, "templateListID/templateList.size:" + this.templateListID + "/" + this.templateList.size());
        if (!z) {
            ArrayList<HashMap<String, String>> arrayList2 = this.templateList;
            if (arrayList2 == this.mIntroTemplateList) {
                this.templateListID = 0;
                this.templateList = this.mOutroTemplateList;
                this.mTemplateTypeCountList.append(2, 1);
                Log.d(TAG, "intro -> outro");
            } else if (arrayList2 == this.mLoopTemplateList) {
                this.templateListID = 0;
                this.templateList = this.mOutroTemplateList;
                this.mTemplateTypeCountList.append(2, 1);
                Log.d(TAG, "loop -> outro");
            }
        } else if (this.templateListID >= this.templateList.size()) {
            ArrayList<HashMap<String, String>> arrayList3 = this.templateList;
            if (arrayList3 == this.mIntroTemplateList) {
                this.templateListID = 0;
                this.templateList = this.mLoopTemplateList;
                Log.d(TAG, "intro -> loop");
            } else if (arrayList3 == this.mLoopTemplateList) {
                this.templateListID = 0;
                Log.d(TAG, "loop -> loop");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x017b, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0180, code lost:
        return r14.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0181, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0186, code lost:
        return r14.getMessage();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0121 */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0151 A[Catch:{ JSONException -> 0x0181, Exception -> 0x017b }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x017b A[ExcHandler: Exception (r14v4 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:11:0x002b] */
    public String parsingJSONFile(nexProject nexproject) {
        String str;
        String string;
        String str2 = this.mTemplateID;
        if (str2 != null) {
            str = AssetPackageTemplateJsonToString(str2);
        } else {
            String str3 = this.mTemplateFilePath;
            if (str3 != null) {
                str = readFromFile(str3);
            } else {
                InputStream inputStream = this.mInputStream;
                str = inputStream != null ? readFromFile(inputStream) : null;
            }
        }
        if (str == null) {
            return "template parsing fail!";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            string = jSONObject.getString(TAG_TEMPLATE_NAME);
            String string2 = jSONObject.getString(TAG_TEMPLATE_VERSION);
            String defaultLetterboxEffect = nexApplicationConfig.getDefaultLetterboxEffect();
            if (jSONObject.has(TAG_TEMPLATE_LETTERBOX)) {
                defaultLetterboxEffect = jSONObject.getString(TAG_TEMPLATE_LETTERBOX);
            }
            nexproject.setLetterboxEffect(defaultLetterboxEffect);
            String string3 = jSONObject.getString(TAG_TEMPLATE_DESCRIPTION);
            if (!string2.equals("v1.x")) {
                if (!string2.equals("v2.0.0")) {
                    this.mTemplateVersion = "template 2.0";
                    this.mTemplate = new c();
                    String a2 = this.mTemplate.a(this.mTemplateFilePath, jSONObject);
                    if (a2 != null) {
                        return a2;
                    }
                    if (this.mTemplateVersion.equals("template 1.0")) {
                        this.mTemplateArray = jSONObject.getJSONArray(TAG_TEMPLATE);
                        for (int i = 0; i < this.mTemplateArray.length(); i++) {
                            this.mTemplateList.add(setParameter2List(this.mTemplateArray.getJSONObject(i)));
                        }
                        applyBGM2Project(nexproject, jSONObject.getString(TAG_TEMPLATE_BGM));
                    }
                    return null;
                }
            }
            this.mTemplateVersion = "template 1.x";
            Log.d(TAG, "Info(name, version, description): (" + string + " ," + this.mTemplateVersion + " ," + string3 + "))");
            applyBGM2Project(nexproject, jSONObject.getString(TAG_TEMPLATE_BGM));
            nexproject.setBGMMasterVolumeScale(Float.parseFloat(jSONObject.getString(TAG_TEMPLATE_BGM_VOLUME)));
            this.mIntroTemplateArray = jSONObject.getJSONArray(TAG_TEMPLATE_INTRO);
            for (int i2 = 0; i2 < this.mIntroTemplateArray.length(); i2++) {
                this.mIntroTemplateList.add(setParameter2List(this.mIntroTemplateArray.getJSONObject(i2)));
            }
            this.mLoopTemplateArray = jSONObject.getJSONArray(TAG_TEMPLATE_LOOP);
            for (int i3 = 0; i3 < this.mLoopTemplateArray.length(); i3++) {
                this.mLoopTemplateList.add(setParameter2List(this.mLoopTemplateArray.getJSONObject(i3)));
            }
            this.mOutroTemplateArray = jSONObject.getJSONArray(TAG_TEMPLATE_OUTRO);
            for (int i4 = 0; i4 < this.mOutroTemplateArray.length(); i4++) {
                this.mOutroTemplateList.add(setParameter2List(this.mOutroTemplateArray.getJSONObject(i4)));
            }
        } catch (JSONException ) {
            Log.d(TAG, "Info(name: (" + string + "))");
            this.mIntroTemplateList.clear();
            this.mLoopTemplateList.clear();
            this.mOutroTemplateList.clear();
            this.mTemplateVersion = "template 1.0";
        } catch (Exception e) {
        }
        if (this.mTemplateVersion.equals("template 1.0")) {
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String raw2file(Context context, Context context2, String str) throws Exception {
        int lastIndexOf = str.lastIndexOf("/");
        StringBuilder sb = new StringBuilder();
        sb.append(EditorGlobal.f().getAbsolutePath());
        sb.append(File.separator);
        sb.append(TAG_TEMPLATE);
        sb.append(File.separator);
        int i = lastIndexOf + 1;
        sb.append(str.substring(i, str.length()));
        String sb2 = sb.toString();
        File file = new File(sb2);
        AssetManager assets = context2.getAssets();
        if (file.isFile()) {
            try {
                AssetFileDescriptor openFd = assets.openFd(str);
                if (file.length() == openFd.getLength()) {
                    openFd.close();
                    Log.d(TAG, "bgm file found in sdcard.");
                    this.mBGMPath = sb2;
                    return this.mBGMPath;
                }
                openFd.close();
            } catch (IOException unused) {
                Log.d(TAG, "bgm file found in sdcard.");
                this.mBGMPath = sb2;
                return this.mBGMPath;
            }
        }
        InputStream open = assets.open(str);
        if (open == null) {
            return null;
        }
        try {
            File file2 = new File(EditorGlobal.f().getAbsolutePath() + File.separator + TAG_TEMPLATE);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            in2sdcard(open, file);
            Log.d(TAG, "bgm file copy assete to sdcard.");
            open.close();
            this.mBGMPath = sb2;
            return this.mBGMPath;
        } catch (Exception unused2) {
            String substring = str.substring(i, str.length());
            try {
                in2file(context, open, substring);
                Log.d(TAG, "bgm file copy assete to temp data.");
                this.mBGMTempFile = true;
                this.mBGMPath = context.getFilesDir().getAbsolutePath() + File.separator + substring;
                return this.mBGMPath;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void release() {
        this.introCount = 0;
        this.loopCount = 0;
        this.tempClipID = 0;
        this.mCancel = false;
        c cVar = this.mTemplate;
        if (cVar != null) {
            cVar.a();
        }
        ArrayList<HashMap<String, String>> arrayList = this.mTemplateList;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<HashMap<String, String>> arrayList2 = this.mIntroTemplateList;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        ArrayList<HashMap<String, String>> arrayList3 = this.mLoopTemplateList;
        if (arrayList3 != null) {
            arrayList3.clear();
        }
        ArrayList<HashMap<String, String>> arrayList4 = this.mOutroTemplateList;
        if (arrayList4 != null) {
            arrayList4.clear();
        }
        SparseArray<Integer> sparseArray = this.mTemplateTypeCountList;
        if (sparseArray != null) {
            sparseArray.clear();
        }
        ArrayList<a> arrayList5 = this.mOverlayList;
        if (arrayList5 != null) {
            arrayList5.clear();
        }
        String str = this.mBGMPath;
        if (str != null && this.mBGMTempFile) {
            new File(str).delete();
        }
        this.templateListID = 0;
        this.mTemplateVersion = null;
    }

    /* access modifiers changed from: package-private */
    public void setCancel() {
        this.mCancel = true;
        if (this.mTemplate != null) {
            Log.d(TAG, "setCancel");
            this.mTemplate.h();
            return;
        }
        Log.d(TAG, "setCancel mTemplate is null");
    }

    /* access modifiers changed from: package-private */
    public void setOverlappedTransitionFlag(boolean z) {
        this.mOverlappedTransition = z;
    }

    /* access modifiers changed from: package-private */
    public void setOverlay2Project() {
        Iterator<a> it = this.mOverlayList.iterator();
        while (it.hasNext()) {
            a next = it.next();
            nexOverlayItem vignetteOverlayViaRatioMode = vignetteOverlayViaRatioMode("vignette.png", nexApplicationConfig.getAspectProfile().getWidth(), nexApplicationConfig.getAspectProfile().getHeight(), this.mProject.getClip(next.a(), true).mStartTime + next.c(), next.b());
            this.mProject.addOverlay(vignetteOverlayViaRatioMode);
            if (next.d()) {
                vignetteOverlayViaRatioMode.clearAnimate();
                vignetteOverlayViaRatioMode.addAnimate(nexAnimate.getAlpha(next.e(), next.f(), 0.0f, 1.0f).setInterpolator(new AccelerateDecelerateInterpolator()));
                vignetteOverlayViaRatioMode.addAnimate(nexAnimate.getAlpha(next.g(), next.h(), 1.0f, 0.0f).setInterpolator(new AccelerateDecelerateInterpolator()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public HashMap<String, String> setParameter2List(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        HashMap<String, String> hashMap = new HashMap<>();
        String value = getValue(jSONObject2, "type");
        if (value.equals("scene")) {
            String value2 = getValue(jSONObject2, TAG_ID);
            String value3 = getValue(jSONObject2, TAG_SOURCE_TYPE);
            String value4 = getValue(jSONObject2, TAG_DURATION);
            String value5 = getValue(jSONObject2, TAG_VOLUME);
            String value6 = getValue(jSONObject2, TAG_EFFECTS);
            Object obj = TAG_EFFECTS;
            String value7 = getValue(jSONObject2, TAG_BRIGHTNESS);
            Object obj2 = TAG_BRIGHTNESS;
            String value8 = getValue(jSONObject2, TAG_CONTRAST);
            Object obj3 = TAG_CONTRAST;
            String str = value8;
            String value9 = getValue(jSONObject2, TAG_SATURATION);
            Object obj4 = TAG_SATURATION;
            String str2 = value9;
            String value10 = getValue(jSONObject2, TAG_COLOR_FILTER);
            Object obj5 = TAG_COLOR_FILTER;
            String str3 = value10;
            String value11 = getValue(jSONObject2, TAG_SPEED_CONTROL);
            Object obj6 = TAG_SPEED_CONTROL;
            String str4 = value11;
            String value12 = getValue(jSONObject2, TAG_VIGNETTE);
            Object obj7 = TAG_VIGNETTE;
            String str5 = value12;
            String value13 = getValue(jSONObject2, TAG_LUT);
            Object obj8 = TAG_LUT;
            String str6 = value13;
            String value14 = getValue(jSONObject2, TAG_EXTERNAL_VIDEO_PATH);
            Object obj9 = TAG_EXTERNAL_VIDEO_PATH;
            String str7 = value14;
            String value15 = getValue(jSONObject2, TAG_EXTERNAL_IMAGE_PATH);
            Object obj10 = TAG_EXTERNAL_IMAGE_PATH;
            String value16 = getValue(jSONObject2, TAG_SOLID_COLOR);
            String value17 = getValue(jSONObject2, TAG_CROP_MODE);
            String value18 = getValue(jSONObject2, TAG_CROP_SPEED);
            String value19 = getValue(jSONObject2, TAG_IMAGE_CROP_MODE);
            hashMap.put("type", value);
            hashMap.put(TAG_ID, value2);
            hashMap.put(TAG_SOURCE_TYPE, value3);
            hashMap.put(TAG_DURATION, value4);
            hashMap.put(TAG_VOLUME, value5);
            hashMap.put(obj, value6);
            hashMap.put(obj2, value7);
            hashMap.put(obj3, str);
            hashMap.put(obj4, str2);
            hashMap.put(obj5, str3);
            hashMap.put(obj6, str4);
            hashMap.put(obj8, str6);
            hashMap.put(TAG_CROP_MODE, value17);
            hashMap.put(TAG_CROP_SPEED, value18);
            hashMap.put(TAG_VIDEO_CROP_MODE, getValue(jSONObject2, TAG_VIDEO_CROP_MODE));
            hashMap.put(TAG_IMAGE_CROP_MODE, value19);
            hashMap.put(obj9, str7);
            hashMap.put(obj10, value15);
            hashMap.put(TAG_SOLID_COLOR, value16);
            hashMap.put(obj7, str5);
        } else {
            String str8 = TAG_EFFECTS;
            if (value.equals("transition")) {
                String value20 = getValue(jSONObject2, str8);
                String value21 = getValue(jSONObject2, TAG_DURATION);
                hashMap.put("type", value);
                hashMap.put(str8, value20);
                hashMap.put(TAG_DURATION, value21);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public String setProperty2Clips(nexProject nexproject, String str) {
        String property2ImageClip;
        if (nexproject.getTotalClipCount(true) == 0) {
            return "no clip in the project";
        }
        if (str.equals("template 1.0")) {
            this.templateList = this.mTemplateList;
        } else {
            this.templateList = this.mIntroTemplateList;
        }
        this.templateListID = 0;
        int i = 0;
        while (!this.mCancel) {
            nexClip clip = nexproject.getClip(i, true);
            if (clip.getClipType() == 4) {
                String property2VideoClip = setProperty2VideoClip(nexproject, clip, i);
                if (property2VideoClip != null) {
                    return property2VideoClip;
                }
            } else if (clip.getClipType() == 1 && (property2ImageClip = setProperty2ImageClip(clip, i)) != null) {
                return property2ImageClip;
            }
            this.templateListID++;
            if (this.templateListID == this.templateList.size()) {
                this.templateListID = 0;
            }
            i++;
            if (!str.equals("template 1.0")) {
                if (i == this.mTemplateTypeCountList.get(0).intValue()) {
                    if (this.mTemplateTypeCountList.get(1).intValue() == 0) {
                        this.templateList = this.mOutroTemplateList;
                    } else {
                        this.templateList = this.mLoopTemplateList;
                    }
                    this.templateListID = 0;
                }
                if (this.templateList == this.mLoopTemplateList && i == this.mTemplateTypeCountList.get(0).intValue() + this.mTemplateTypeCountList.get(1).intValue()) {
                    this.templateList = this.mOutroTemplateList;
                    this.templateListID = 0;
                }
            }
            if (i == nexproject.getTotalClipCount(true)) {
                clip.getTransitionEffect(true).setEffectNone();
                clip.getTransitionEffect(true).setDuration(0);
            }
            if (i >= nexproject.getTotalClipCount(true)) {
                nexproject.updateProject();
                return null;
            }
        }
        this.mCancel = false;
        return cancelMassage;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: java.lang.String} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    public String setProperty2ImageClip(nexClip nexclip, int i) {
        Object obj;
        boolean z;
        nexClip nexclip2 = nexclip;
        if (!((String) this.templateList.get(this.templateListID).get("type")).equals("scene")) {
            return null;
        }
        int parseInt = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_CONTRAST)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_CONTRAST));
        int parseInt2 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_BRIGHTNESS)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_BRIGHTNESS));
        int parseInt3 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_SATURATION)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_SATURATION));
        int parseInt4 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_COLOR_FILTER)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_COLOR_FILTER));
        String str = (String) this.templateList.get(this.templateListID).get(TAG_EFFECTS);
        String str2 = (String) this.templateList.get(this.templateListID).get(TAG_LUT);
        String str3 = (String) this.templateList.get(this.templateListID).get(TAG_VIGNETTE);
        ArrayList<HashMap<String, String>> arrayList = this.templateList;
        String str4 = MovieStatUtils.DOWNLOAD_FAILED;
        String str5 = (String) arrayList.get(this.templateListID).get(TAG_CROP_MODE);
        if (str5.equals("")) {
            str5 = (String) this.templateList.get(this.templateListID).get(TAG_IMAGE_CROP_MODE);
        }
        try {
            nexclip2.setClipVolume(0);
            if (str.equals("none")) {
                nexclip2.getClipEffect(true).setEffectNone();
                obj = "none";
            } else {
                obj = "none";
                nexclip2.getClipEffect(true).setEffect(str);
                nexclip2.getClipEffect(true).setEffectShowTime(0, nexclip.getProjectEndTime() - nexclip.getProjectStartTime());
            }
            if (parseInt2 != -1) {
                nexclip2.setBrightness(parseInt2);
            }
            if (parseInt != -1) {
                nexclip2.setContrast(parseInt);
            }
            if (parseInt3 != -1) {
                nexclip2.setSaturation(parseInt3);
            }
            if (parseInt4 != -1) {
                nexclip2.setColorEffect(getColorEffect(parseInt4 - 1));
            }
            switch (str5.hashCode()) {
                case 101393:
                    if (str5.equals("fit")) {
                        z = true;
                        break;
                    }
                case 3143043:
                    if (str5.equals("fill")) {
                        z = false;
                        break;
                    }
                case 1054849215:
                    if (str5.equals("pan_face")) {
                        z = true;
                        break;
                    }
                case 1055207047:
                    if (str5.equals("pan_rand")) {
                        z = true;
                        break;
                    }
            }
            z = true;
            if (!z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FILL);
            } else if (z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.PAN_RAND);
            } else if (z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.PAN_FACE);
            } else if (z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FIT);
            }
            if (str2 != null && !str2.equals("null")) {
                Iterator<nexColorEffect> it = nexColorEffect.getPresetList().iterator();
                while (true) {
                    if (it.hasNext()) {
                        nexColorEffect next = it.next();
                        if (next.getPresetName().equals(str2)) {
                            nexclip2.setColorEffect(next);
                        }
                    }
                }
            }
            String[] split = str3.split(",");
            if (split[0].equals("clip")) {
                if (split[1].equals("yes")) {
                    nexclip2.setVignetteEffect(true);
                } else {
                    nexclip2.setVignetteEffect(false);
                }
            } else if (split[0].equals("overlay")) {
                addTemplateOverlay(i, str3);
            }
            this.templateListID++;
            if (!((String) this.templateList.get(this.templateListID).get("type")).equals("transition")) {
                return null;
            }
            String str6 = (String) this.templateList.get(this.templateListID).get(TAG_EFFECTS);
            if (!((String) this.templateList.get(this.templateListID).get(TAG_DURATION)).equals("default")) {
                str4 = this.templateList.get(this.templateListID).get(TAG_DURATION);
            }
            int parseInt5 = Integer.parseInt(str4);
            if (!this.mOverlappedTransition) {
                nexclip2.getTransitionEffect(true).setEffectNone();
                nexclip2.getTransitionEffect(true).setDuration(0);
                return null;
            } else if (str6.equals(obj)) {
                nexclip2.getTransitionEffect(true).setEffectNone();
                nexclip2.getTransitionEffect(true).setDuration(0);
                return null;
            } else {
                nexclip2.getTransitionEffect(true).setTransitionEffect(str6);
                if (parseInt5 == -1) {
                    return null;
                }
                nexclip2.getTransitionEffect(true).setDuration(parseInt5);
                return null;
            }
        } catch (nexSDKException e) {
            return e.getMessage();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v27, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.String} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    public String setProperty2VideoClip(nexProject nexproject, nexClip nexclip, int i) {
        boolean z;
        nexClip nexclip2 = nexclip;
        int i2 = i;
        int parseInt = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_VOLUME)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_VOLUME));
        int parseInt2 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_CONTRAST)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_CONTRAST));
        int parseInt3 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_BRIGHTNESS)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_BRIGHTNESS));
        int parseInt4 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_SATURATION)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_SATURATION));
        int parseInt5 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_COLOR_FILTER)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_COLOR_FILTER));
        int parseInt6 = Integer.parseInt(((String) this.templateList.get(this.templateListID).get(TAG_SPEED_CONTROL)).equals("default") ? MovieStatUtils.DOWNLOAD_FAILED : (String) this.templateList.get(this.templateListID).get(TAG_SPEED_CONTROL));
        String str = (String) this.templateList.get(this.templateListID).get(TAG_EFFECTS);
        String str2 = (String) this.templateList.get(this.templateListID).get(TAG_LUT);
        ArrayList<HashMap<String, String>> arrayList = this.templateList;
        String str3 = MovieStatUtils.DOWNLOAD_FAILED;
        String str4 = (String) arrayList.get(this.templateListID).get(TAG_VIGNETTE);
        int i3 = parseInt6;
        String str5 = (String) this.templateList.get(this.templateListID).get(TAG_CROP_MODE);
        if (str5.equals("")) {
            str5 = (String) this.templateList.get(this.templateListID).get(TAG_VIDEO_CROP_MODE);
        }
        try {
            Object obj = "none";
            if (str.equals("none")) {
                nexclip2.getClipEffect(true).setEffectNone();
            } else {
                nexclip2.getClipEffect(true).setEffect(str);
                if (this.mUseProjectSpeed && nexclip.getVideoClipEdit().getSpeedControl() != 100) {
                    i3 = nexclip.getVideoClipEdit().getSpeedControl();
                }
                nexclip2.getClipEffect(true).setEffectShowTime(0, 0);
                String substring = str.substring(str.lastIndexOf("."));
                if (substring.equals(".opening") || substring.equals(".middle") || substring.equals(".ending")) {
                    nexclip2.getClipEffect(true).setTitle(0, " ");
                    nexclip2.getClipEffect(true).setTitle(1, " ");
                }
            }
            int i4 = i3;
            if (parseInt != -1) {
                nexclip2.setClipVolume(parseInt);
            }
            if (parseInt3 != -1) {
                nexclip2.setBrightness(parseInt3);
            }
            if (parseInt2 != -1) {
                nexclip2.setContrast(parseInt2);
            }
            if (parseInt4 != -1) {
                nexclip2.setSaturation(parseInt4);
            }
            if (parseInt5 != -1) {
                nexclip2.setColorEffect(getColorEffect(parseInt5 - 1));
            }
            if (i4 != -1) {
                nexclip.getVideoClipEdit().setSpeedControl(i4);
            }
            switch (str5.hashCode()) {
                case 101393:
                    if (str5.equals("fit")) {
                        z = true;
                        break;
                    }
                case 3143043:
                    if (str5.equals("fill")) {
                        z = false;
                        break;
                    }
                case 1054849215:
                    if (str5.equals("pan_face")) {
                        z = true;
                        break;
                    }
                case 1055207047:
                    if (str5.equals("pan_rand")) {
                        z = true;
                        break;
                    }
            }
            z = true;
            if (!z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FILL);
            } else if (z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.PAN_RAND);
            } else if (z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.PAN_FACE);
            } else if (z) {
                nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FIT);
            }
            if (str2 != null && !str2.equals("null")) {
                if (this.mTemplateID != null) {
                    nexColorEffect lutColorEffect = nexColorEffect.getLutColorEffect(str2);
                    if (lutColorEffect != null) {
                        Log.d(TAG, "lut color effect set =" + lutColorEffect.getPresetName());
                        nexclip2.setColorEffect(lutColorEffect);
                    }
                } else {
                    Iterator<nexColorEffect> it = nexColorEffect.getPresetList().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            nexColorEffect next = it.next();
                            if (next.getPresetName().equals(str2)) {
                                nexclip2.setColorEffect(next);
                            }
                        }
                    }
                }
            }
            String[] split = str4.split(",");
            if (split[0].equals("clip")) {
                if (split[1].equals("yes")) {
                    nexclip2.setVignetteEffect(true);
                } else {
                    nexclip2.setVignetteEffect(false);
                }
            } else if (split[0].equals("overlay")) {
                addTemplateOverlay(i2, str4);
            }
            this.templateListID++;
            if (!((String) this.templateList.get(this.templateListID).get("type")).equals("transition")) {
                return null;
            }
            String str6 = (String) this.templateList.get(this.templateListID).get(TAG_EFFECTS);
            if (!((String) this.templateList.get(this.templateListID).get(TAG_DURATION)).equals("default")) {
                str3 = this.templateList.get(this.templateListID).get(TAG_DURATION);
            }
            int parseInt7 = Integer.parseInt(str3);
            if (this.mOverlappedTransition) {
                int[] transitionDurationTimeGuideLine = nexproject.getTransitionDurationTimeGuideLine(i2, parseInt7);
                if (transitionDurationTimeGuideLine[0] == 0 && transitionDurationTimeGuideLine[1] == 0) {
                    nexclip2.getTransitionEffect(true).setEffectNone();
                    nexclip2.getTransitionEffect(true).setDuration(0);
                    return null;
                } else if (str6.equals(obj)) {
                    nexclip2.getTransitionEffect(true).setEffectNone();
                    nexclip2.getTransitionEffect(true).setDuration(0);
                    return null;
                } else {
                    nexclip2.getTransitionEffect(true).setTransitionEffect(str6);
                    if (parseInt7 == -1) {
                        return null;
                    }
                    nexclip2.getTransitionEffect(true).setDuration(parseInt7);
                    return null;
                }
            } else {
                nexclip2.getTransitionEffect(true).setEffectNone();
                nexclip2.getTransitionEffect(true).setDuration(0);
                return null;
            }
        } catch (nexSDKException e) {
            return e.getMessage();
        }
    }

    /* access modifiers changed from: package-private */
    public String setTemplateEffect() {
        String str;
        String parsingJSONFile = parsingJSONFile(this.mProject);
        if (parsingJSONFile != null) {
            return parsingJSONFile;
        }
        if (this.mTemplateVersion.equals("template 1.0")) {
            consistProjectViaVer1(this.mProject);
            if (this.mCancel) {
                return cancelMassage;
            }
            str = setProperty2Clips(this.mProject, this.mTemplateVersion);
        } else if (this.mTemplateVersion.equals("template 1.x")) {
            consistProjectViaVer2(this.mProject);
            if (this.mCancel) {
                return cancelMassage;
            }
            str = setProperty2Clips(this.mProject, this.mTemplateVersion);
        } else {
            str = applyTemplateOnProject(this.mProject);
        }
        if (str != null) {
            return str;
        }
        setOverlay2Project();
        return null;
    }

    public String setTemplateEffects2Project(nexProject nexproject, Context context, Context context2, String str, boolean z) throws nexSDKException {
        initTemplateComposer(nexproject, context, context2, str);
        return setTemplateEffect();
    }

    /* access modifiers changed from: package-private */
    public void setUseProjectSpeed(boolean z) {
        this.mUseProjectSpeed = z;
    }

    /* access modifiers changed from: package-private */
    public nexOverlayItem vignetteOverlayViaRatioMode(final String str, final int i, final int i2, int i3, int i4) {
        return new nexOverlayItem(new nexOverlayImage("template_overlay", i, i2, (nexOverlayImage.runTimeMakeBitMap) new nexOverlayImage.runTimeMakeBitMap() {
            public int getBitmapID() {
                return 100;
            }

            public boolean isAniMate() {
                return false;
            }

            public Bitmap makeBitmap() {
                try {
                    return Bitmap.createScaledBitmap(BitmapFactory.decodeStream(nexTemplateComposer.this.mResContext.getResources().getAssets().open(str), (Rect) null, (BitmapFactory.Options) null), i, i2, true);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }), i / 2, i2 / 2, i3, i3 + i4);
    }
}
