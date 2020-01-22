package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.util.Log;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.nexCrop;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: nexTemplateDrawInfo */
final class d {
    private static String r = "nexTemplateDrawInfo";
    int a = 0;
    String b = "";
    String c = "";
    float d;
    float e;
    nexColorEffect f = null;
    Map<String, String> g = new HashMap();
    String h = "default";
    String i = "";
    String j = "";
    float k;
    float l;
    int m = 0;
    int n = 0;
    int o = 0;
    String p = "none";
    float q;

    d() {
    }

    public static d a(JSONObject jSONObject, int i2) {
        d dVar = new d();
        dVar.a = i2;
        dVar.b = a(jSONObject, "source_type");
        if (!dVar.b.equals("user")) {
            dVar.c = a(jSONObject, "source_path");
            if (jSONObject.has("system_source_width")) {
                dVar.m = Integer.parseInt(a(jSONObject, "system_source_width"));
            }
            if (jSONObject.has("system_source_height")) {
                dVar.n = Integer.parseInt(a(jSONObject, "system_source_height"));
            }
        }
        dVar.d = Float.parseFloat(a(jSONObject, "start"));
        dVar.e = Float.parseFloat(a(jSONObject, "end"));
        String a2 = a(jSONObject, "lut");
        if (!(a2 == null || a2.compareTo("null") == 0 || a2.compareTo("none") == 0)) {
            dVar.f = nexColorEffect.getLutColorEffect(a2);
        }
        if (jSONObject.has("alternative_lut")) {
            try {
                dVar.g.clear();
                JSONObject jSONObject2 = jSONObject.getJSONObject("alternative_lut");
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    dVar.g.put(next, jSONObject2.getString(next));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                dVar.g.clear();
            }
        }
        dVar.h = a(jSONObject, "crop_mode");
        dVar.i = a(jSONObject, "video_crop_mode");
        dVar.j = a(jSONObject, "image_crop_speed");
        dVar.k = Float.parseFloat(a(jSONObject, "draw_width"));
        dVar.l = Float.parseFloat(a(jSONObject, "draw_height"));
        dVar.o = Integer.parseInt(a(jSONObject, "volume"));
        if (jSONObject.has("audio_res")) {
            String a3 = a(jSONObject, "audio_res");
            if (!a3.equals("none")) {
                String a4 = a(jSONObject, "audio_res_pos");
                dVar.p = a3;
                dVar.q = Float.parseFloat(a4);
            }
        }
        return dVar;
    }

    static String a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException unused) {
            return str.equals("source_type") ? "ALL" : str.equals("source_path") ? "default" : str.equals("start") ? MovieStatUtils.DOWNLOAD_SUCCESS : str.equals("end") ? "1" : (str.equals("crop_mode") || str.equals("video_crop_mode") || str.equals("image_crop_speed")) ? "default" : str.equals("lut") ? "null" : (str.equals("draw_width") || str.equals("draw_height") || str.equals("volume") || str.equals("system_source_width") || str.equals("system_source_height")) ? MovieStatUtils.DOWNLOAD_SUCCESS : str.equals("audio_res") ? "none" : str.equals("audio_res_pos") ? MovieStatUtils.DOWNLOAD_SUCCESS : "default";
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00d8  */
    public String a(nexClip nexclip, float f2) {
        ExifInterface exifInterface;
        float height;
        int width;
        String str = this.h;
        if (nexclip.getClipType() == 4 && this.i.length() > 0) {
            str = this.i;
        }
        char c2 = 65535;
        switch (str.hashCode()) {
            case 101393:
                if (str.equals("fit")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    c2 = 0;
                    break;
                }
                break;
            case 1054849215:
                if (str.equals("pan_face")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1055207047:
                if (str.equals("pan_rand")) {
                    c2 = 1;
                    break;
                }
                break;
        }
        if (c2 == 0) {
            nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FILL, f2);
        } else if (c2 == 1) {
            nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.PAN_RAND, f2);
        } else if (c2 == 2) {
            nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.PAN_FACE, f2);
        } else if (c2 == 3) {
            nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FIT, f2);
        } else if (f2 != 0.0f) {
            float width2 = ((float) nexclip.getWidth()) / (((float) nexclip.getHeight()) * 1.0f);
            int rotateDegree = nexclip.getRotateDegree();
            if (nexclip.getClipType() == 4) {
                if (rotateDegree == 90 || rotateDegree == 270) {
                    height = (float) nexclip.getHeight();
                    width = nexclip.getWidth();
                }
                if (((double) Math.abs(f2 - width2)) > 0.05d) {
                    nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.PAN_RAND, f2);
                } else {
                    nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FIT, f2);
                }
                Log.d(r, String.format("Apply default crop mode(%f %f) (%d)", new Object[]{Float.valueOf(f2), Float.valueOf(width2), Integer.valueOf(rotateDegree)}));
            } else {
                if (nexclip.getClipType() == 1) {
                    try {
                        exifInterface = new ExifInterface(nexclip.getRealPath());
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        exifInterface = null;
                    }
                    if (exifInterface != null) {
                        String attribute = exifInterface.getAttribute("Orientation");
                        int parseInt = attribute != null ? Integer.parseInt(attribute) : 1;
                        if (parseInt == 6 || parseInt == 8) {
                            if (parseInt == 6) {
                                rotateDegree = 90;
                            }
                            if (parseInt == 8) {
                                rotateDegree = 270;
                            }
                            height = (float) nexclip.getHeight();
                            width = nexclip.getWidth();
                        }
                    }
                }
                if (((double) Math.abs(f2 - width2)) > 0.05d) {
                }
                Log.d(r, String.format("Apply default crop mode(%f %f) (%d)", new Object[]{Float.valueOf(f2), Float.valueOf(width2), Integer.valueOf(rotateDegree)}));
            }
            width2 = height / (((float) width) * 1.0f);
            if (((double) Math.abs(f2 - width2)) > 0.05d) {
            }
            Log.d(r, String.format("Apply default crop mode(%f %f) (%d)", new Object[]{Float.valueOf(f2), Float.valueOf(width2), Integer.valueOf(rotateDegree)}));
        } else {
            nexclip.getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FIT, f2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String a(nexProject nexproject, int i2, int i3) {
        String str = this.p;
        if (str == null || str.length() <= 0 || this.p.equals("none")) {
            return null;
        }
        nexproject.updateProject();
        nexClip supportedClip = nexClip.getSupportedClip(nexAssetPackageManager.getAssetPackageMediaPath(a.a().b(), this.p));
        if (supportedClip == null) {
            return null;
        }
        int totalTime = supportedClip.getTotalTime();
        supportedClip.setAssetResource(true);
        int i4 = i2 + ((int) (((float) i3) * this.q));
        nexproject.addAudio(supportedClip, i4, totalTime + i4);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        Log.d(r, String.format("subId : %d", new Object[]{Integer.valueOf(this.a)}));
        Log.d(r, String.format("start : %f", new Object[]{Float.valueOf(this.d)}));
        Log.d(r, String.format("end : %f", new Object[]{Float.valueOf(this.e)}));
        String str = r;
        Object[] objArr = new Object[1];
        nexColorEffect nexcoloreffect = this.f;
        objArr[0] = Integer.valueOf(nexcoloreffect == null ? 0 : nexcoloreffect.getLUTId());
        Log.d(str, String.format("lut : %d", objArr));
        Log.d(r, String.format("cropMode : %s", new Object[]{this.h}));
        Log.d(r, String.format("videoCropMode : %s", new Object[]{this.i}));
        Log.d(r, String.format("draw size : %f %f", new Object[]{Float.valueOf(this.k), Float.valueOf(this.l)}));
        Log.d(r, String.format("volume : %d", new Object[]{Integer.valueOf(this.o)}));
        Log.d(r, "---------------------------------------------------");
    }

    public void a(nexClip nexclip, int i2, int i3, int i4, float f2, String str, boolean z) {
        if (nexclip != null) {
            nexDrawInfo nexdrawinfo = new nexDrawInfo();
            nexdrawinfo.setTopEffectID(i2);
            nexdrawinfo.setID(this.a);
            nexdrawinfo.setSubEffectID(this.a);
            float f3 = (float) i3;
            int i5 = ((int) (this.d * f3)) + i4;
            int i6 = ((int) (this.e * f3)) + i4;
            int i7 = 0;
            Log.d(r, String.format("Template setDrawInfo2Clip(dur:%d start:%d %d %d)", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)}));
            if (nexclip.getClipType() == 1) {
                nexclip.mStartTime = i5 < nexclip.mStartTime ? i5 : nexclip.mStartTime;
                nexclip.mEndTime = i6 > nexclip.mEndTime ? i6 : nexclip.mEndTime;
                nexclip.setImageClipDuration(nexclip.mEndTime - nexclip.mStartTime);
            } else {
                nexclip.mStartTime = i5 < nexclip.mStartTime ? i5 : nexclip.mStartTime;
                nexclip.mEndTime = i6 > nexclip.mEndTime ? i6 : nexclip.mEndTime;
                if (z) {
                    int i8 = (int) (f3 * (this.e - this.d));
                    if (i8 >= nexclip.getVideoDuration()) {
                        i8 = nexclip.getVideoDuration();
                    }
                    nexclip.getVideoClipEdit().setTrim(0, i8);
                }
                nexclip.setClipVolume(this.o);
            }
            nexdrawinfo.setStartTime(i5);
            nexdrawinfo.setEndTime(i6);
            if (str == null || !this.g.containsKey(str)) {
                nexColorEffect nexcoloreffect = this.f;
                if (nexcoloreffect != null) {
                    i7 = nexcoloreffect.getLUTId();
                }
                nexdrawinfo.setLUT(i7);
            } else {
                nexColorEffect lutColorEffect = nexColorEffect.getLutColorEffect(this.g.get(str));
                if (lutColorEffect != null) {
                    i7 = lutColorEffect.getLUTId();
                }
                nexdrawinfo.setLUT(i7);
            }
            float f4 = this.k;
            if (f4 != 0.0f) {
                float f5 = this.l;
                if (f5 != 0.0f) {
                    f2 = f4 / f5;
                }
            }
            nexdrawinfo.setRatio(f2);
            a(nexclip, f2);
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            nexclip.getCrop().getStartPositionRaw(rect);
            nexclip.getCrop().getEndPositionRaw(rect2);
            nexdrawinfo.setRotateState(nexclip.getRotateDegree());
            nexdrawinfo.setStartRect(rect);
            nexdrawinfo.setEndRect(rect2);
            nexclip.addDrawInfo(nexdrawinfo);
        }
    }

    public boolean a(nexProject nexproject, Context context, e eVar, int i2, float f2) {
        String str;
        if ((this.b.compareTo("system") != 0 && this.b.compareTo("system_mt") != 0) || (str = this.c) == null || str.length() <= 0) {
            return false;
        }
        nexClip nexclip = null;
        String assetPackageMediaPath = nexAssetPackageManager.getAssetPackageMediaPath(context, this.c);
        if (assetPackageMediaPath != null) {
            nexclip = nexClip.getSupportedClip(assetPackageMediaPath);
        }
        nexClip nexclip2 = nexclip;
        if (nexclip2 != null) {
            nexclip2.setAssetResource(true);
            if (this.b.compareTo("system_mt") == 0) {
                nexclip2.setMotionTrackedVideo(true);
            }
            nexproject.add(nexclip2);
            nexclip2.clearDrawInfos();
            nexclip2.mStartTime = i2;
            nexclip2.mEndTime = nexclip2.getTotalTime() + i2;
            a(nexclip2, eVar.a, eVar.i(), i2, f2, (String) null, false);
        }
        return true;
    }
}
