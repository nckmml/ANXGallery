package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import com.miui.gallery.movie.utils.MovieStatUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: nexTemplate */
final class c {
    private static String w = "nexTemplate";
    private ArrayList<e> A = new ArrayList<>();
    private ArrayList<e> B = new ArrayList<>();
    private Map<String, ArrayList<e>> C = new HashMap();
    private boolean D = true;
    private int E = 10368000;
    private boolean F = false;
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    float g = 1.0f;
    int h = 2500;
    int i = -1;
    int j = -1;
    int k = -1;
    int l = -1;
    int m = 0;
    int n;
    String o;
    boolean p = true;
    float q;
    boolean r = false;
    String s = "";
    boolean t = true;
    g u = null;
    boolean v = false;
    private int x = 1;
    private ArrayList<e> y = new ArrayList<>();
    private ArrayList<e> z = new ArrayList<>();

    /* access modifiers changed from: package-private */
    public int a(nexProject nexproject, List<nexDrawInfo> list) {
        int i2 = 0;
        for (nexDrawInfo next : list) {
            nexproject.getTopDrawInfo().add(next);
            i2 = next.getIsTransition() == 1 ? next.getStartTime() : next.getEndTime();
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public String a(nexProject nexproject, Context context, Context context2) {
        nexproject.setBackgroundMusicPath(this.e);
        nexproject.setBGMMasterVolumeScale(this.g);
        return null;
    }

    /* access modifiers changed from: package-private */
    public String a(nexProject nexproject, Context context, Context context2, boolean z2, boolean z3) {
        this.r = z2;
        this.t = z3;
        Log.d(w, String.format("updateProjectWithTemplate(%b %b)", new Object[]{Boolean.valueOf(z2), Boolean.valueOf(z3)}));
        if (this.b.startsWith("2.0")) {
            nexproject.setTemplateApplyMode(2);
            nexproject.clearDrawInfo();
            return c(nexproject, context, context2);
        } else if (this.b.startsWith("3.0")) {
            nexproject.setTemplateApplyMode(3);
            String d2 = d(nexproject, context, context2);
            int i2 = this.i;
            if (i2 >= 0) {
                nexproject.setProjectAudioFadeInTime(i2);
            }
            int i3 = this.j;
            if (i3 >= 0) {
                nexproject.setProjectAudioFadeOutTime(i3);
            }
            nexproject.updateProject();
            a(nexproject, context, context2);
            return d2;
        } else {
            return "Unsupported Template version : " + this.b;
        }
    }

    /* access modifiers changed from: package-private */
    public String a(nexProject nexproject, nexProject nexproject2, Context context, Context context2) {
        int i2;
        int i3;
        nexClip nexclip;
        int i4;
        nexClip nexclip2;
        int i5;
        nexClip nexclip3;
        nexProject nexproject3 = nexproject;
        nexProject nexproject4 = nexproject2;
        Context context3 = context;
        Context context4 = context2;
        Log.d(w, String.format("Template imageProject Apply Start(%d %d)", new Object[]{Integer.valueOf(nexproject4.getTotalClipCount(true)), Integer.valueOf(nexproject3.getTotalClipCount(true))}));
        if (!this.B.isEmpty()) {
            int totalClipCount = nexproject3.getTotalClipCount(true);
            Iterator<e> it = this.B.iterator();
            while (it.hasNext()) {
                e next = it.next();
                if (next.a(true) >= totalClipCount) {
                    List<nexDrawInfo> a2 = next.a(0, 0, (String) null);
                    nexClip nexclip4 = null;
                    for (d next2 : next.j()) {
                        d dVar = next2;
                        List<nexDrawInfo> list = a2;
                        e eVar = next;
                        if (next2.a(nexproject2, context, next, 0, this.q)) {
                            a2 = list;
                            next = eVar;
                        } else {
                            if (nexproject3.getTotalClipCount(true) > 0) {
                                nexClip clip = nexproject3.getClip(0, true);
                                nexproject3.remove(clip);
                                nexproject4.add(clip);
                                clip.clearDrawInfos();
                                clip.mStartTime = Reader.READ_DONE;
                                clip.mEndTime = Integer.MIN_VALUE;
                                nexclip4 = clip;
                            }
                            e eVar2 = eVar;
                            dVar.a(nexclip4, eVar2.a, eVar2.i(), 0, this.q, (String) null, false);
                            dVar.a(nexproject4, 0, eVar2.i());
                            next = eVar2;
                            a2 = list;
                        }
                    }
                    a(nexproject4, a2);
                    nexproject2.updateProject();
                    b(nexproject4, context3, context4);
                    Log.d(w, String.format("Template imageProject Apply single effect(%d %d)", new Object[]{Integer.valueOf(nexproject4.getTotalClipCount(true)), Integer.valueOf(nexproject3.getTotalClipCount(true))}));
                    return null;
                }
            }
        }
        String str = null;
        int f2 = f();
        this.s = "";
        nexClip nexclip5 = null;
        boolean z2 = true;
        boolean z3 = true;
        int i6 = 0;
        while (true) {
            if (nexproject3.getTotalClipCount(true) <= 0 || this.F) {
                break;
            }
            if (z2) {
                int i7 = i6;
                int i8 = 0;
                while (i8 < this.y.size()) {
                    e eVar3 = this.y.get(i8);
                    List<nexDrawInfo> a3 = eVar3.a(i5, 0, str);
                    List<d> j2 = eVar3.j();
                    this.s = eVar3.c;
                    nexClip nexclip6 = nexclip5;
                    for (d next3 : j2) {
                        e eVar4 = eVar3;
                        int i9 = i5;
                        int i10 = i8;
                        d dVar2 = next3;
                        int i11 = i2;
                        if (next3.a(nexproject2, context, eVar4, i9, this.q)) {
                            i2 = i11;
                            eVar3 = eVar4;
                            i5 = i9;
                        } else {
                            if (nexproject3.getTotalClipCount(true) > i11) {
                                nexClip clip2 = nexproject3.getClip(0, true);
                                nexproject3.remove(clip2);
                                nexproject4.add(clip2);
                                clip2.clearDrawInfos();
                                clip2.mStartTime = Reader.READ_DONE;
                                clip2.mEndTime = Integer.MIN_VALUE;
                                nexclip3 = clip2;
                            } else {
                                nexclip3 = nexclip6;
                            }
                            eVar3 = eVar4;
                            nexclip6 = nexclip3;
                            dVar2.a(nexclip6, eVar3.a, eVar3.i(), i9, this.q, (String) null, false);
                            int i12 = i9;
                            dVar2.a(nexproject4, i12, eVar3.i());
                            i5 = i12;
                            i2 = i11;
                        }
                        i8 = i10;
                    }
                    int i13 = i2;
                    i7 = a(nexproject4, a3);
                    i8++;
                    nexclip5 = nexclip6;
                    str = null;
                }
                int i14 = i5;
                int i15 = i2;
                if (nexproject3.getTotalClipCount(true) <= i15) {
                    z3 = false;
                }
                Log.d(w, String.format("Template imageProject Apply Intro End(%d %d) (%d)", new Object[]{Integer.valueOf(nexproject4.getTotalClipCount(true)), Integer.valueOf(nexproject3.getTotalClipCount(true)), Integer.valueOf(i15)}));
                i6 = i14;
                f2 = i15;
                z2 = false;
            } else {
                int i16 = i2;
                if (z3) {
                    int i17 = i6;
                    int i18 = 0;
                    while (true) {
                        if (i18 >= this.z.size()) {
                            i6 = i4;
                            break;
                        }
                        e eVar5 = this.z.get(i18);
                        List<nexDrawInfo> a4 = eVar5.a(i4, 0, (String) null);
                        List<d> j3 = eVar5.j();
                        this.s = eVar5.c;
                        nexClip nexclip7 = nexclip5;
                        for (d next4 : j3) {
                            d dVar3 = next4;
                            List<nexDrawInfo> list2 = a4;
                            e eVar6 = eVar5;
                            int i19 = i4;
                            if (next4.a(nexproject2, context, eVar5, i4, this.q)) {
                                i4 = i19;
                                a4 = list2;
                                eVar5 = eVar6;
                            } else {
                                if (nexproject3.getTotalClipCount(true) > i16) {
                                    nexClip clip3 = nexproject3.getClip(0, true);
                                    nexproject3.remove(clip3);
                                    nexproject4.add(clip3);
                                    clip3.clearDrawInfos();
                                    clip3.mStartTime = Reader.READ_DONE;
                                    clip3.mEndTime = Integer.MIN_VALUE;
                                    nexclip2 = clip3;
                                } else {
                                    nexclip2 = nexclip7;
                                }
                                e eVar7 = eVar6;
                                dVar3.a(nexclip2, eVar7.a, eVar7.i(), i19, this.q, (String) null, false);
                                int i20 = i19;
                                dVar3.a(nexproject4, i20, eVar7.i());
                                eVar5 = eVar7;
                                nexclip7 = nexclip2;
                                i4 = i20;
                                a4 = list2;
                            }
                        }
                        i17 = a(nexproject4, a4);
                        if (nexproject3.getTotalClipCount(true) <= i16) {
                            Log.d(w, String.format("Template imageProject Apply Loop End(%d %d) (%d)", new Object[]{Integer.valueOf(nexproject4.getTotalClipCount(true)), Integer.valueOf(nexproject3.getTotalClipCount(true)), Integer.valueOf(i16)}));
                            i6 = i17;
                            z3 = false;
                            nexclip5 = nexclip7;
                            break;
                        }
                        i18++;
                        nexclip5 = nexclip7;
                    }
                    f2 = i16;
                } else {
                    ArrayList<e> arrayList = this.A;
                    Map<String, ArrayList<e>> map = this.C;
                    if (map != null && map.size() > 0) {
                        if (this.C.containsKey(this.s)) {
                            arrayList = this.C.get(this.s);
                            Log.d(w, String.format("Template imageProject select Outro(%s)", new Object[]{this.s}));
                        } else {
                            arrayList = this.C.values().iterator().next();
                        }
                    }
                    ArrayList<e> arrayList2 = arrayList;
                    int i21 = i6;
                    int i22 = 0;
                    while (i22 < arrayList2.size()) {
                        e eVar8 = arrayList2.get(i22);
                        List<nexDrawInfo> a5 = eVar8.a(i3, 0, this.s);
                        nexClip nexclip8 = null;
                        for (d next5 : eVar8.j()) {
                            d dVar4 = next5;
                            List<nexDrawInfo> list3 = a5;
                            e eVar9 = eVar8;
                            int i23 = i3;
                            int i24 = i22;
                            if (next5.a(nexproject2, context, eVar8, i3, this.q)) {
                                eVar8 = eVar9;
                                i3 = i23;
                            } else {
                                if (nexproject3.getTotalClipCount(true) > 0) {
                                    nexClip clip4 = nexproject3.getClip(0, true);
                                    nexproject3.remove(clip4);
                                    nexproject4.add(clip4);
                                    clip4.clearDrawInfos();
                                    clip4.mStartTime = Reader.READ_DONE;
                                    clip4.mEndTime = Integer.MIN_VALUE;
                                    nexclip = clip4;
                                } else {
                                    nexclip = nexclip8;
                                }
                                e eVar10 = eVar9;
                                dVar4.a(nexclip, eVar10.a, eVar10.i(), i23, this.q, this.s, false);
                                i3 = i23;
                                dVar4.a(nexproject4, i3, eVar10.i());
                                eVar8 = eVar10;
                                nexclip8 = nexclip;
                            }
                            i22 = i24;
                            a5 = list3;
                        }
                        i21 = a(nexproject4, a5);
                        i22++;
                    }
                    Log.d(w, String.format("Template imageProject Apply Outro End(%d %d) (%d)", new Object[]{Integer.valueOf(nexproject4.getTotalClipCount(true)), Integer.valueOf(nexproject3.getTotalClipCount(true)), Integer.valueOf(i16)}));
                }
            }
            str = null;
        }
        if (this.F) {
            return "apply Template user canceled";
        }
        nexproject2.updateProject();
        a(nexproject4, context3, context4);
        return null;
    }

    /* access modifiers changed from: package-private */
    public String a(nexProject nexproject, nexProject nexproject2, Context context, ArrayList<e> arrayList, boolean z2) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        ArrayList<e> arrayList2;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        nexProject nexproject3 = nexproject;
        nexProject nexproject4 = nexproject2;
        ArrayList<e> arrayList3 = arrayList;
        boolean z3 = z2;
        int c2 = c();
        int size = arrayList.size();
        int i16 = 0;
        int i17 = 0;
        while (i2 < i3) {
            e eVar = arrayList3.get(i2);
            int h2 = eVar.h();
            int c3 = eVar.c() + h2;
            int d2 = eVar.d() + h2;
            int e2 = h2 + eVar.e();
            if (e2 <= c3) {
                e2 = c3;
            }
            boolean f2 = eVar.f();
            int i18 = eVar.r;
            int totalClipCount = nexproject3.getTotalClipCount(true);
            if (totalClipCount > 0 || z3) {
                if (eVar.a(nexproject4, context, this.q)) {
                    if (!z3) {
                        this.s = eVar.c;
                    } else {
                        nexClip clip = nexproject4.getClip(nexproject4.getTotalClipCount(true) - 1, true);
                        if (clip != null) {
                            eVar.a(nexproject4, clip, this.s, z3);
                        }
                    }
                } else if (totalClipCount <= 0) {
                    Log.d(w, String.format("setProjectWithEffects clip apply end", new Object[0]));
                    return null;
                } else if (this.m != 1 || z3 || nexproject3.getClip(0, true).getProjectDuration() - c3 >= i4) {
                    if (!z3) {
                        this.s = eVar.c;
                    }
                    int i19 = e2;
                    int i20 = c3;
                    int i21 = d2;
                    boolean z4 = true;
                    int i22 = i20;
                    while (nexproject3.getTotalClipCount(z4) > 0) {
                        nexClip clip2 = nexproject3.getClip(0, z4);
                        i7 = i4;
                        int projectDuration = clip2.getProjectDuration();
                        if (clip2.getClipType() == z4) {
                            clip2.getCrop().setFaceDetectSpeed(i18);
                            if (clip2.getCrop().getEndPosionLock()) {
                                Rect rect = new Rect();
                                Rect rect2 = new Rect();
                                i14 = i19;
                                clip2.getCrop().getStartPosition(rect);
                                clip2.getCrop().getEndPosition(rect2);
                                clip2.getCrop().applyCropSpeed(rect, rect2, clip2.getWidth(), clip2.getHeight(), i18, projectDuration);
                                i15 = i18;
                                clip2.getCrop().shrinkToAspect(rect, nexApplicationConfig.getAspectRatio());
                                clip2.getCrop().shrinkToAspect(rect2, nexApplicationConfig.getAspectRatio());
                                clip2.getCrop().setStartPosition(rect);
                                clip2.getCrop().setEndPosition(rect2);
                            } else {
                                i14 = i19;
                                i15 = i18;
                            }
                            if (projectDuration <= i20) {
                                if (projectDuration >= i21) {
                                    nexproject3.remove(clip2);
                                    nexproject4.add(clip2);
                                    i6 = 0;
                                    eVar.a(MovieStatUtils.DOWNLOAD_SUCCESS, clip2, true, this.q, this.r);
                                    eVar.a(nexproject4, clip2);
                                    eVar.a(nexproject4, clip2, this.s, z3);
                                    b(nexproject4);
                                } else {
                                    i6 = 0;
                                    if (i20 <= 5000 || nexproject3.getTotalClipCount(true) == 1 || !f2) {
                                        i9 = i3;
                                        i5 = i2;
                                        int i23 = i15;
                                        clip2.setImageClipDuration(i22);
                                        if (clip2.getCrop().getEndPosionLock()) {
                                            Rect rect3 = new Rect();
                                            Rect rect4 = new Rect();
                                            clip2.getCrop().getStartPosition(rect3);
                                            clip2.getCrop().getEndPosition(rect4);
                                            clip2.getCrop().applyCropSpeed(rect3, rect4, clip2.getWidth(), clip2.getHeight(), i23, clip2.getImageClipDuration());
                                            clip2.getCrop().shrinkToAspect(rect3, nexApplicationConfig.getAspectRatio());
                                            clip2.getCrop().shrinkToAspect(rect4, nexApplicationConfig.getAspectRatio());
                                            clip2.getCrop().setStartPosition(rect3);
                                            clip2.getCrop().setEndPosition(rect4);
                                        }
                                        nexproject3.remove(clip2);
                                        nexproject4.add(clip2);
                                        eVar.a(MovieStatUtils.DOWNLOAD_SUCCESS, clip2, true, this.q, this.r);
                                        eVar.a(nexproject4, clip2);
                                        eVar.a(nexproject4, clip2, this.s, z3);
                                        b(nexproject4);
                                    } else {
                                        int i24 = i22 - projectDuration;
                                        nexproject3.remove(clip2);
                                        nexproject4.add(clip2);
                                        if (i24 <= 0) {
                                            eVar.a("1", clip2, true, this.q, this.r);
                                            eVar.a(nexproject4, clip2);
                                            eVar.a(nexproject4, clip2, this.s, z3);
                                            b(nexproject4);
                                        } else {
                                            i9 = i3;
                                            i12 = i20;
                                            int i25 = i14;
                                            i5 = i2;
                                            i11 = i25;
                                            i10 = i15;
                                            eVar.a("1", clip2, false, this.q, this.r);
                                            eVar.a(nexproject4, clip2);
                                            eVar.a(nexproject4, clip2, this.s, z3);
                                            Context context2 = context;
                                            i22 = i24;
                                        }
                                    }
                                }
                                i8 = i3;
                                i5 = i2;
                                break;
                            }
                            i9 = i3;
                            i5 = i2;
                            int i26 = i15;
                            i6 = 0;
                            clip2.setImageClipDuration(i20);
                            if (clip2.getCrop().getEndPosionLock()) {
                                Rect rect5 = new Rect();
                                Rect rect6 = new Rect();
                                clip2.getCrop().getStartPosition(rect5);
                                clip2.getCrop().getEndPosition(rect6);
                                clip2.getCrop().applyCropSpeed(rect5, rect6, clip2.getWidth(), clip2.getHeight(), i26, clip2.getImageClipDuration());
                                clip2.getCrop().shrinkToAspect(rect5, nexApplicationConfig.getAspectRatio());
                                clip2.getCrop().shrinkToAspect(rect6, nexApplicationConfig.getAspectRatio());
                                clip2.getCrop().setStartPosition(rect5);
                                clip2.getCrop().setEndPosition(rect6);
                            }
                            nexproject3.remove(clip2);
                            nexproject4.add(clip2);
                            eVar.a(MovieStatUtils.DOWNLOAD_SUCCESS, clip2, true, this.q, this.r);
                            eVar.a(nexproject4, clip2);
                            eVar.a(nexproject4, clip2, this.s, z3);
                            b(nexproject4);
                            arrayList2 = arrayList;
                            i8 = i9;
                            size = i8;
                            c2 = i7;
                            i16 = i6;
                            ArrayList<e> arrayList4 = arrayList2;
                            i17 = i5 + 1;
                            arrayList3 = arrayList4;
                        } else {
                            i9 = i3;
                            i5 = i2;
                            i12 = i20;
                            i11 = i19;
                            i10 = i18;
                            i6 = 0;
                            if (projectDuration >= i21) {
                                int i27 = i12;
                                if (projectDuration <= i11) {
                                    nexproject3.remove(clip2);
                                    nexproject4.add(clip2);
                                    eVar.a("3", clip2, true, this.q, this.r);
                                    eVar.a(nexproject4, clip2);
                                    eVar.a(nexproject4, clip2, this.s, z3);
                                    b(nexproject4);
                                    if (!this.t) {
                                        clip2.getVideoClipEdit().setSpeedControl(100);
                                        c(nexproject4);
                                    }
                                    arrayList2 = arrayList;
                                    i8 = i9;
                                    size = i8;
                                    c2 = i7;
                                    i16 = i6;
                                    ArrayList<e> arrayList42 = arrayList2;
                                    i17 = i5 + 1;
                                    arrayList3 = arrayList42;
                                } else {
                                    int i28 = i5 + 1;
                                    int i29 = i9;
                                    if (i28 >= i29) {
                                        arrayList2 = arrayList;
                                        i13 = 0;
                                    } else {
                                        i13 = i28;
                                        arrayList2 = arrayList;
                                    }
                                    e eVar2 = arrayList2.get(i13);
                                    int i30 = projectDuration - i27;
                                    int i31 = projectDuration - i21;
                                    int d3 = eVar2.d() + eVar2.h();
                                    if (i30 <= d3 && !eVar2.a() && eVar.g()) {
                                        if (i31 >= d3) {
                                            i27 = i21;
                                        } else {
                                            nexproject3.remove(clip2);
                                            nexproject4.add(clip2);
                                            i8 = i29;
                                            eVar.a("3", clip2, true, this.q, this.r);
                                            eVar.a(nexproject4, clip2);
                                            eVar.a(nexproject4, clip2, this.s, z3);
                                            b(nexproject4);
                                            if (!this.t) {
                                                clip2.getVideoClipEdit().setSpeedControl(100);
                                                c(nexproject4);
                                            }
                                            size = i8;
                                            c2 = i7;
                                            i16 = i6;
                                            ArrayList<e> arrayList422 = arrayList2;
                                            i17 = i5 + 1;
                                            arrayList3 = arrayList422;
                                        }
                                    }
                                    i8 = i29;
                                    int speedControl = clip2.getVideoClipEdit().getSpeedControl();
                                    nexClip dup = nexClip.dup(clip2);
                                    nexproject4.add(dup);
                                    dup.setRotateDegree(clip2.getRotateDegree());
                                    dup.getVideoClipEdit().setSpeedControl(speedControl);
                                    if (speedControl != 100) {
                                        i27 = (i27 * speedControl) / 100;
                                    }
                                    dup.getVideoClipEdit().setTrim(clip2.getVideoClipEdit().getStartTrimTime(), clip2.getVideoClipEdit().getStartTrimTime() + i27);
                                    clip2.getVideoClipEdit().setTrim(clip2.getVideoClipEdit().getStartTrimTime() + i27, clip2.getVideoClipEdit().getEndTrimTime());
                                    nexClip nexclip = dup;
                                    eVar.a("4", dup, true, this.q, this.r);
                                    eVar.a(nexproject4, nexclip);
                                    eVar.a(nexproject4, nexclip, this.s, z3);
                                    if (!this.t) {
                                        clip2.getVideoClipEdit().setSpeedControl(100);
                                        nexclip.getVideoClipEdit().setSpeedControl(100);
                                        c(nexproject4);
                                    } else {
                                        b(nexproject4);
                                    }
                                    size = i8;
                                    c2 = i7;
                                    i16 = i6;
                                    ArrayList<e> arrayList4222 = arrayList2;
                                    i17 = i5 + 1;
                                    arrayList3 = arrayList4222;
                                }
                            } else if (!eVar.g()) {
                                nexproject3.remove(clip2);
                                Context context3 = context;
                            } else {
                                nexproject3.remove(clip2);
                                nexproject4.add(clip2);
                                int i32 = i22;
                                int i33 = i12;
                                eVar.a("2", clip2, false, this.q, this.r);
                                eVar.a(nexproject4, clip2);
                                eVar.a(nexproject4, clip2, this.s, z3);
                                if (!this.t) {
                                    clip2.getVideoClipEdit().setSpeedControl(100);
                                    z4 = true;
                                    if (nexproject3.getTotalClipCount(true) <= 0) {
                                        clip2.setTemplateEffectID(clip2.getTemplateEffectID() & nexEngine.ExportHEVCMainTierLevel62);
                                    }
                                } else {
                                    z4 = true;
                                }
                                i21 -= projectDuration;
                                i19 = i11 - projectDuration;
                                i20 = i33 - projectDuration;
                                Context context4 = context;
                                i22 = i32;
                                i18 = i10;
                                i3 = i9;
                                i4 = i7;
                                i2 = i5;
                            }
                        }
                        i20 = i12;
                        i19 = i11;
                        i18 = i10;
                        i3 = i9;
                        i4 = i7;
                        i2 = i5;
                        z4 = true;
                    }
                } else {
                    this.v = true;
                    Log.d(w, String.format("setProjectWithEffects clip apply end for outro on single video", new Object[0]));
                    return null;
                }
                i7 = i4;
                i8 = i3;
                i5 = i2;
                i6 = 0;
                arrayList2 = arrayList;
                size = i8;
                c2 = i7;
                i16 = i6;
                ArrayList<e> arrayList42222 = arrayList2;
                i17 = i5 + 1;
                arrayList3 = arrayList42222;
            } else {
                Log.d(w, String.format("setProjectWithEffects clip apply end", new Object[i16]));
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String a(String str, JSONObject jSONObject) {
        this.F = false;
        if (str != null) {
            File file = new File(str);
            Log.d(w, String.format("Template templateFile path(%s)", new Object[]{file.getAbsolutePath()}));
            file.getAbsolutePath().endsWith("txt");
        }
        this.x = 1;
        String a2 = a(jSONObject);
        if (a2 != null) {
            return "Template header parse error : " + a2;
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("template_intro");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                int i3 = this.x;
                this.x = i3 + 1;
                e a3 = e.a(jSONObject2, i3);
                if (a3 == null) {
                    return "Template intro parse error";
                }
                this.y.add(a3);
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("template_loop");
            for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                JSONObject jSONObject3 = jSONArray2.getJSONObject(i4);
                int i5 = this.x;
                this.x = i5 + 1;
                e a4 = e.a(jSONObject3, i5);
                if (a4 == null) {
                    return "Template Loop parse error";
                }
                this.z.add(a4);
            }
            JSONArray jSONArray3 = jSONObject.getJSONArray("template_outro");
            for (int i6 = 0; i6 < jSONArray3.length(); i6++) {
                JSONObject jSONObject4 = jSONArray3.getJSONObject(i6);
                if (jSONObject4 != null) {
                    if (!jSONObject4.has("alternative_id") || !jSONObject4.has("alternative_outro")) {
                        JSONObject jSONObject5 = jSONArray3.getJSONObject(i6);
                        int i7 = this.x;
                        this.x = i7 + 1;
                        e a5 = e.a(jSONObject5, i7);
                        if (a5 == null) {
                            return "Template Outro parse error";
                        }
                        this.A.add(a5);
                    } else {
                        String string = jSONObject4.getString("alternative_id");
                        JSONArray jSONArray4 = jSONObject4.getJSONArray("alternative_outro");
                        ArrayList arrayList = new ArrayList();
                        for (int i8 = 0; i8 < jSONArray4.length(); i8++) {
                            JSONObject jSONObject6 = jSONArray4.getJSONObject(i8);
                            int i9 = this.x;
                            this.x = i9 + 1;
                            e a6 = e.a(jSONObject6, i9);
                            if (a6 == null) {
                                return "Template Outro parse error(alternative outro)";
                            }
                            arrayList.add(a6);
                        }
                        this.C.put(string, arrayList);
                    }
                }
            }
            if (jSONObject.has("template_single")) {
                JSONArray jSONArray5 = jSONObject.getJSONArray("template_single");
                for (int i10 = 0; i10 < jSONArray5.length(); i10++) {
                    JSONObject jSONObject7 = jSONArray5.getJSONObject(i10);
                    int i11 = this.x;
                    this.x = i11 + 1;
                    e a7 = e.a(jSONObject7, i11);
                    if (a7 == null) {
                        return "Template Single parse error";
                    }
                    this.B.add(a7);
                }
            }
            Log.d(w, "parseTemplate end");
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.y.clear();
            this.z.clear();
            this.A.clear();
            this.B.clear();
            Log.d(w, "parseTemplate failed : " + e2.getMessage());
            return "parseTemplate failed : " + e2.getMessage();
        }
    }

    /* access modifiers changed from: package-private */
    public String a(JSONObject jSONObject) {
        try {
            this.a = jSONObject.getString("template_name");
            this.b = jSONObject.getString("template_version");
            this.c = jSONObject.getString("template_desc");
            this.d = jSONObject.getString("template_mode");
            this.q = 1.7777778f;
            if (this.d.equals("16v9")) {
                this.q = 1.7777778f;
            } else if (this.d.equals("9v16")) {
                this.q = 0.5625f;
            } else if (this.d.equals("1v1")) {
                this.q = 1.0f;
            } else if (this.d.equals("2v1")) {
                this.q = 2.0f;
            } else if (this.d.equals("1v2")) {
                this.q = 0.5f;
            }
            this.e = jSONObject.getString("template_bgm");
            if (jSONObject.has("template_single_bgm")) {
                this.f = jSONObject.getString("template_single_bgm");
            }
            if (jSONObject.has("template_bgm_volume")) {
                this.g = Float.parseFloat(jSONObject.getString("template_bgm_volume"));
            }
            if (jSONObject.has("template_min_duration")) {
                this.n = Integer.parseInt(jSONObject.getString("template_min_duration"));
            }
            if (jSONObject.has("template_default_effect")) {
                this.o = jSONObject.getString("template_default_effect");
            }
            if (jSONObject.has("template_default_effect_scale")) {
                this.p = !jSONObject.getString("template_default_effect_scale").equals(MovieStatUtils.DOWNLOAD_SUCCESS);
            }
            if (jSONObject.has("template_default_image_duration")) {
                if (!jSONObject.getString("template_default_image_duration").equals("default")) {
                    this.h = Integer.parseInt(jSONObject.getString("template_default_image_duration"));
                }
            }
            if (jSONObject.has("template_project_vol_fade_in_time") && !jSONObject.getString("template_project_vol_fade_in_time").equals("default")) {
                this.i = Integer.parseInt(jSONObject.getString("template_project_vol_fade_in_time"));
            }
            if (jSONObject.has("template_project_vol_fade_out_time") && !jSONObject.getString("template_project_vol_fade_out_time").equals("default")) {
                this.j = Integer.parseInt(jSONObject.getString("template_project_vol_fade_out_time"));
            }
            if (jSONObject.has("template_single_project_vol_fade_in_time") && !jSONObject.getString("template_single_project_vol_fade_in_time").equals("default")) {
                this.k = Integer.parseInt(jSONObject.getString("template_single_project_vol_fade_in_time"));
            }
            if (jSONObject.has("template_single_project_vol_fade_out_time") && !jSONObject.getString("template_single_project_vol_fade_out_time").equals("default")) {
                this.l = Integer.parseInt(jSONObject.getString("template_single_project_vol_fade_out_time"));
            }
            if (!jSONObject.has("template_single_video")) {
                return null;
            }
            this.m = Integer.parseInt(jSONObject.getString("template_single_video"));
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            String str = w;
            Log.d(str, "parse Template failed : " + e2.getMessage());
            Log.d(w, "case1 1");
            return e2.getMessage();
        }
    }

    public void a() {
        this.F = false;
        ArrayList<e> arrayList = this.y;
        if (arrayList != null) {
            arrayList.clear();
        }
        ArrayList<e> arrayList2 = this.z;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        ArrayList<e> arrayList3 = this.A;
        if (arrayList3 != null) {
            arrayList3.clear();
        }
        ArrayList<e> arrayList4 = this.B;
        if (arrayList4 != null) {
            arrayList4.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(nexProject nexproject) {
        for (int i2 = 0; i2 < nexproject.getTotalClipCount(true); i2++) {
            if (nexproject.getClip(i2, true).getClipType() == 1) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public String b(nexProject nexproject, Context context, Context context2) {
        nexproject.setBackgroundMusicPath(this.f);
        nexproject.setBGMMasterVolumeScale(this.g);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void b(nexProject nexproject) {
        int totalClipCount = nexproject.getTotalClipCount(true);
        if (totalClipCount > 1) {
            nexClip clip = nexproject.getClip(totalClipCount - 2, true);
            int templateEffectID = clip.getTemplateEffectID() & -251658241;
            nexClip clip2 = nexproject.getClip(totalClipCount - 1, true);
            int templateEffectID2 = clip2.getTemplateEffectID() & -251658241;
            if (templateEffectID != templateEffectID2 && clip.getTransitionEffect().getDuration() >= clip2.getProjectDuration() - clip2.getTransitionEffect().getDuration()) {
                clip.getTransitionEffect(true).setEffectNone();
                clip.getTransitionEffect(true).setDuration(0);
            }
            if (templateEffectID != templateEffectID2 && clip2.getProjectDuration() <= clip2.getTransitionEffect().getDuration()) {
                clip2.getTransitionEffect(true).setEffectNone();
                clip2.getTransitionEffect(true).setDuration(0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int c() {
        Map<String, ArrayList<e>> map = this.C;
        int i2 = 0;
        if (map != null && map.size() > 0) {
            Iterator<ArrayList<e>> it = this.C.values().iterator();
            if (it.hasNext()) {
                Iterator it2 = it.next().iterator();
                while (it2.hasNext()) {
                    e eVar = (e) it2.next();
                    if (!eVar.b().equals("res_video") && !eVar.b().equals("res_image") && !eVar.b().equals("res_solid")) {
                        i2 += eVar.c();
                    }
                }
                return i2;
            }
        }
        ArrayList<e> arrayList = this.A;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<e> it3 = this.A.iterator();
            while (it3.hasNext()) {
                e next = it3.next();
                if (!next.b().equals("res_video") && !next.b().equals("res_image") && !next.b().equals("res_solid")) {
                    i2 += next.c() + next.h();
                }
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public String c(nexProject nexproject, Context context, Context context2) {
        nexProject nexproject2 = nexproject;
        int totalTime = nexproject.getTotalTime();
        Log.d(w, String.format("applyTemplate20_Project ( ProjectTime:%d TemplateMinDur:%d)", new Object[]{Integer.valueOf(totalTime), Integer.valueOf(b())}));
        nexProject clone = nexProject.clone(nexproject);
        nexproject.allClear(true);
        int e2 = e();
        int c2 = c();
        ArrayList arrayList = new ArrayList();
        if (this.B.isEmpty() || (!(clone.getTotalClipCount(true) == 1 && clone.getClip(0, true).getClipType() == 1) && (clone.getTotalTime() >= d() || a(clone)))) {
            if (this.m != 1) {
                int totalClipCount = clone.getTotalClipCount(true) - 1;
                while (true) {
                    if (totalClipCount < 0 || c2 < 800 || e2 <= 0) {
                        break;
                    }
                    nexClip clip = clone.getClip(totalClipCount, true);
                    if (clip.getClipType() == 1) {
                        arrayList.add(0, clip);
                        clone.remove(clip);
                        c2 -= clip.getProjectDuration();
                        e2--;
                    } else if (clip.getProjectDuration() <= c2) {
                        arrayList.add(0, clip);
                        clone.remove(clip);
                        c2 -= clip.getProjectDuration();
                    } else {
                        if (this.y.get(0).d() > totalTime - c2) {
                            arrayList.add(0, clip);
                            clone.remove(clip);
                        } else {
                            int speedControl = clip.getVideoClipEdit().getSpeedControl();
                            nexClip dup = nexClip.dup(clip);
                            clone.add(totalClipCount, true, dup);
                            dup.getVideoClipEdit().setSpeedControl(speedControl);
                            dup.setRotateDegree(clip.getRotateDegree());
                            if (speedControl != 100) {
                                c2 = (c2 * speedControl) / 100;
                            }
                            int startTrimTime = clip.getVideoClipEdit().getStartTrimTime();
                            int endTrimTime = clip.getVideoClipEdit().getEndTrimTime();
                            int i2 = endTrimTime - c2;
                            dup.getVideoClipEdit().setTrim(startTrimTime, i2);
                            clip.getVideoClipEdit().setTrim(i2, endTrimTime);
                            Log.d(w, String.format("Template Apply 1(%d %d %d) 2(%d %d %d)", new Object[]{Integer.valueOf(dup.getProjectDuration()), Integer.valueOf(dup.getVideoClipEdit().getStartTrimTime()), Integer.valueOf(dup.getVideoClipEdit().getEndTrimTime()), Integer.valueOf(clip.getProjectDuration()), Integer.valueOf(clip.getVideoClipEdit().getStartTrimTime()), Integer.valueOf(clip.getVideoClipEdit().getEndTrimTime())}));
                            arrayList.add(0, clip);
                            clone.remove(clip);
                        }
                    }
                    totalClipCount--;
                }
            }
            Log.d(w, String.format("Template Apply Intro Start(%d %d)", new Object[]{Integer.valueOf(nexproject.getTotalClipCount(true)), Integer.valueOf(clone.getTotalClipCount(true))}));
            String a2 = a(clone, nexproject, context, this.y, false);
            if (a2 != null) {
                return a2;
            }
            Log.d(w, String.format("Template Apply Intro End(%d %d)", new Object[]{Integer.valueOf(nexproject.getTotalClipCount(true)), Integer.valueOf(clone.getTotalClipCount(true))}));
            while (clone.getTotalClipCount(true) > 0 && !this.v) {
                String a3 = a(clone, nexproject, context, this.z, false);
                if (a3 != null) {
                    return a3;
                }
                if (this.F) {
                    Log.d(w, "cancel template");
                    return "cancel template";
                }
            }
            Log.d(w, String.format("Template Apply Loop End(%d %d)", new Object[]{Integer.valueOf(nexproject.getTotalClipCount(true)), Integer.valueOf(clone.getTotalClipCount(true))}));
            while (arrayList.size() > 0) {
                clone.add((nexClip) arrayList.get(0));
                arrayList.remove(0);
            }
            Log.d(w, String.format("Template Apply Outpro Start(%d %d)", new Object[]{Integer.valueOf(nexproject.getTotalClipCount(true)), Integer.valueOf(clone.getTotalClipCount(true))}));
            String a4 = a(clone, nexproject, context, this.A, true);
            if (a4 != null) {
                return a4;
            }
            Log.d(w, String.format("Template Apply Outro End(%d %d)", new Object[]{Integer.valueOf(nexproject.getTotalClipCount(true)), Integer.valueOf(clone.getTotalClipCount(true))}));
            nexproject.updateTimeLine(false);
            int i3 = this.i;
            if (i3 >= 0) {
                nexproject.setProjectAudioFadeInTime(i3);
            }
            int i4 = this.j;
            if (i4 >= 0) {
                nexproject.setProjectAudioFadeOutTime(i4);
            }
            nexproject.updateProject();
            if (!this.t) {
                int totalClipCount2 = nexproject.getTotalClipCount(true);
                for (int i5 = 0; i5 < totalClipCount2; i5++) {
                    if (nexproject.getClip(i5, true).getClipEffect().getId().endsWith(".force_effect")) {
                        Rect rect = new Rect();
                        nexproject.getClip(i5 - 1, true).getCrop().getEndPositionRaw(rect);
                        nexproject.getClip(i5, true).getCrop().setStartPositionRaw(rect);
                        int i6 = i5 + 1;
                        if (i6 < totalClipCount2) {
                            nexproject.getClip(i6, true).getCrop().getStartPositionRaw(rect);
                            nexproject.getClip(i5, true).getCrop().setEndPositionRaw(rect);
                        }
                    }
                }
            }
            a(nexproject, context, context2);
            return a4;
        }
        Log.d(w, String.format("Template Apply Single Start(%d %d)", new Object[]{Integer.valueOf(nexproject.getTotalClipCount(true)), Integer.valueOf(clone.getTotalClipCount(true))}));
        String a5 = a(clone, nexproject, context, this.B, true);
        if (a5 != null) {
            return a5;
        }
        nexproject.updateTimeLine(false);
        int i7 = this.k;
        if (i7 >= 0) {
            nexproject.setProjectAudioFadeInTime(i7);
        }
        int i8 = this.l;
        if (i8 >= 0) {
            nexproject.setProjectAudioFadeOutTime(i8);
        }
        nexproject.updateProject();
        if (this.f != null) {
            b(nexproject, context, context2);
        } else {
            a(nexproject, context, context2);
        }
        return a5;
    }

    /* access modifiers changed from: package-private */
    public void c(nexProject nexproject) {
        nexClip clip;
        int totalClipCount = nexproject.getTotalClipCount(true);
        if (totalClipCount > 0 && (clip = nexproject.getClip(totalClipCount - 1, true)) != null && !clip.getTransitionEffect().getId().equals("none") && clip.getTransitionEffect().getDuration() > 0) {
            int duration = clip.getTransitionEffect().getDuration();
            if (clip.getVideoClipEdit().getSpeedControl() != 100) {
                duration = (duration * clip.getVideoClipEdit().getSpeedControl()) / 100;
            }
            nexClip dup = nexClip.dup(clip);
            nexproject.add(dup);
            dup.setRotateDegree(clip.getRotateDegree());
            dup.getVideoClipEdit().setSpeedControl(clip.getVideoClipEdit().getSpeedControl());
            dup.setColorEffect(clip.getColorEffect());
            dup.setBrightness(clip.getBrightness());
            dup.setContrast(clip.getContrast());
            dup.setSaturation(clip.getSaturation());
            dup.setTemplateEffectID(clip.getTemplateEffectID());
            dup.setClipVolume(clip.getClipVolume());
            dup.setAudioOnOff(clip.getAudioOnOff());
            nexClipEffect clipEffect = dup.getClipEffect();
            clipEffect.setEffect(clip.getTransitionEffect().getId() + ".force_effect");
            dup.getTransitionEffect().setTransitionEffect("none");
            dup.getTransitionEffect().setDuration(0);
            clip.getTransitionEffect().setTransitionEffect("none");
            clip.getTransitionEffect().setDuration(0);
            dup.getVideoClipEdit().setTrim(clip.getVideoClipEdit().getEndTrimTime() - duration, clip.getVideoClipEdit().getEndTrimTime());
            clip.getVideoClipEdit().setTrim(clip.getVideoClipEdit().getStartTrimTime(), clip.getVideoClipEdit().getEndTrimTime() - duration);
            Log.d(w, String.format("Template split clip(%d %d) next(%d %d %s)", new Object[]{Integer.valueOf(clip.getProjectStartTime()), Integer.valueOf(clip.getVideoClipEdit().getStartTrimTime() + duration), Integer.valueOf(clip.getVideoClipEdit().getStartTrimTime() + duration), Integer.valueOf(dup.getProjectEndTime()), dup.getTransitionEffect().getId()}));
        }
    }

    /* access modifiers changed from: package-private */
    public int d() {
        ArrayList<e> arrayList = this.B;
        int i2 = 0;
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<e> it = this.B.iterator();
            while (it.hasNext()) {
                e next = it.next();
                if (!next.b().equals("res_video") && !next.b().equals("res_image") && !next.b().equals("res_solid")) {
                    i2 += next.c() + next.h();
                }
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public String d(nexProject nexproject, Context context, Context context2) {
        Log.d(w, String.format("applyTemplate30_Project ( ProjectTime:%d TemplateMinDur:%d)", new Object[]{Integer.valueOf(nexproject.getTotalTime()), Integer.valueOf(b())}));
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < nexproject.getTotalClipCount(true); i5++) {
            nexClip clip = nexproject.getClip(i5, true);
            if (clip.getClipType() == 1) {
                i2++;
            } else {
                if (i4 < clip.getProjectDuration()) {
                    i4 = clip.getProjectDuration();
                }
                clip.getWidth();
                clip.getHeight();
                i3++;
            }
        }
        Log.d(w, String.format("applyTemplate30_Project(I:%d V:%d)", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
        if (i2 <= 0 && i3 <= 0) {
            return "Not support project on 3.0 template";
        }
        if (i3 > 0) {
            return "Template applying failed(variable content) with preprocessing fail";
        }
        nexProject clone = nexProject.clone(nexproject);
        nexproject.allClear(true);
        nexproject.getTopDrawInfo().clear();
        String a2 = a(clone, nexproject, context, context2);
        nexproject.updateTimeLine(false);
        Log.d(w, "Template3.0 make Project end with only images: " + a2);
        return a2;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        Iterator<e> it = this.A.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            e next = it.next();
            if (!next.b().equals("res_video") && !next.b().equals("res_image") && !next.b().equals("res_solid")) {
                i2++;
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        Map<String, ArrayList<e>> map = this.C;
        int i2 = 0;
        if (map == null || map.size() <= 0) {
            Iterator<e> it = this.A.iterator();
            while (it.hasNext()) {
                e next = it.next();
                if (!next.b().equals("res_video") && !next.b().equals("res_image") && !next.b().equals("res_solid")) {
                    i2 += next.a(true);
                }
            }
            return i2;
        }
        Iterator it2 = this.C.values().iterator().next().iterator();
        while (it2.hasNext()) {
            i2 += ((e) it2.next()).a(true);
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public int g() {
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        Log.d(w, "setCancel");
        this.F = true;
    }
}
