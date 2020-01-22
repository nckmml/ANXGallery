package com.nexstreaming.nexeditorsdk;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import com.miui.gallery.movie.utils.MovieStatUtils;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemCategory;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemType;
import com.nexstreaming.app.common.nexasset.assetpackage.c;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.util.q;
import com.nexstreaming.nexeditorsdk.nexAnimate;
import com.nexstreaming.nexeditorsdk.nexOverlayImage;
import com.nexstreaming.nexeditorsdk.nexOverlayManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class nexOverlayTitle {
    private static String a = "nexOverlayTitle";
    private String b;
    private String c;
    private String d;
    private String e;
    private Priority f = Priority.NONE;
    private ArrayList<HashMap<String, String>> g = new ArrayList<>();
    private int h = 0;

    private enum Priority {
        NONE,
        START,
        END,
        START_END
    }

    public interface TitleInfoListener {
        void OnTitleInfoListener(int i, String str, int i2, String str2, int i3, String str3, String str4, int i4, int i5);
    }

    private boolean a(JSONObject jSONObject) {
        new HashMap();
        try {
            this.b = jSONObject.getString("overlay_name");
            this.c = jSONObject.getString("overlay_version");
            this.d = jSONObject.getString("overlay_desc");
            this.e = jSONObject.getString("overlay_priority");
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            String str = a;
            Log.d(str, "pasrse Overlay info failed : " + e2.getMessage());
            return false;
        }
    }

    private nexOverlayImage b(String str) {
        f c2 = c.a().c(str);
        if (c2 != null && c2.getCategory() == ItemCategory.overlay && c2.getType() == ItemType.overlay) {
            return new nexOverlayImage(str, true);
        }
        return null;
    }

    private String b(JSONObject jSONObject, TitleInfoListener titleInfoListener) {
        int i;
        int i2;
        String[] split;
        JSONObject jSONObject2 = jSONObject;
        String str = "group";
        String str2 = "glow_visible";
        try {
            HashMap hashMap = new HashMap();
            String str3 = "glow_color";
            String str4 = a;
            String str5 = "shadow_visible";
            StringBuilder sb = new StringBuilder();
            String str6 = "shadow_color";
            sb.append("pasrse Overlay : ");
            sb.append(jSONObject.toString());
            Log.d(str4, sb.toString());
            hashMap.put("id", "" + this.h);
            hashMap.put(nexExportFormat.TAG_FORMAT_TYPE, jSONObject2.getString(nexExportFormat.TAG_FORMAT_TYPE));
            if (jSONObject2.has("text")) {
                hashMap.put("text", jSONObject2.getString("text"));
            }
            if (jSONObject2.has("text_desc")) {
                hashMap.put("text_desc", jSONObject2.getString("text_desc"));
            }
            if (jSONObject2.has("text_max_len")) {
                hashMap.put("text_max_len", jSONObject2.getString("text_max_len"));
            }
            if (jSONObject2.has("position")) {
                hashMap.put("position", jSONObject2.getString("position"));
            }
            if (jSONObject2.has("start_time")) {
                hashMap.put("start_time", jSONObject2.getString("start_time"));
            }
            if (jSONObject2.has("duration")) {
                hashMap.put("duration", jSONObject2.getString("duration"));
            }
            if (jSONObject2.has("font")) {
                hashMap.put("font", jSONObject2.getString("font"));
            }
            if (jSONObject2.has("font_size")) {
                hashMap.put("font_size", jSONObject2.getString("font_size"));
            }
            if (jSONObject2.has("text_color")) {
                hashMap.put("text_color", jSONObject2.getString("text_color"));
            }
            String str7 = str6;
            if (jSONObject2.has(str7)) {
                hashMap.put(str7, jSONObject2.getString(str7));
            }
            String str8 = str5;
            if (jSONObject2.has(str8)) {
                hashMap.put(str8, jSONObject2.getString(str8));
            }
            String str9 = str3;
            if (jSONObject2.has(str9)) {
                hashMap.put(str9, jSONObject2.getString(str9));
            }
            String str10 = str2;
            if (jSONObject2.has(str10)) {
                hashMap.put(str10, jSONObject2.getString(str10));
            }
            if (jSONObject2.has("outline_color")) {
                hashMap.put("outline_color", jSONObject2.getString("outline_color"));
            }
            if (jSONObject2.has("outline_visible")) {
                hashMap.put("outline_visible", jSONObject2.getString("outline_visible"));
            }
            if (jSONObject2.has("align")) {
                hashMap.put("align", jSONObject2.getString("align"));
            }
            if (jSONObject2.has("animation")) {
                hashMap.put("animation", jSONObject2.getString("animation"));
            }
            if (jSONObject2.has("adjust_animation_non_sub")) {
                hashMap.put("adjust_animation_non_sub", jSONObject2.getString("adjust_animation_non_sub"));
            }
            if (jSONObject2.has("rotate")) {
                hashMap.put("rotate", jSONObject2.getString("rotate"));
            }
            if (jSONObject2.has("scale")) {
                hashMap.put("scale", jSONObject2.getString("scale"));
            }
            String str11 = str;
            if (jSONObject2.has(str11)) {
                hashMap.put(str11, jSONObject2.getString(str11));
            }
            if (jSONObject2.has("adjust_pos_non_sub")) {
                hashMap.put("adjust_pos_non_sub", jSONObject2.getString("adjust_pos_non_sub"));
            }
            if (jSONObject2.has("adjust_align_non_sub")) {
                hashMap.put("adjust_align_non_sub", jSONObject2.getString("adjust_align_non_sub"));
            }
            if (jSONObject2.has("image_res")) {
                hashMap.put("image_res", jSONObject2.getString("image_res"));
            }
            if (jSONObject2.getString("start_time").equals(MovieStatUtils.DOWNLOAD_SUCCESS)) {
                if (this.f != Priority.START_END) {
                    if (this.f == Priority.END) {
                        this.f = Priority.START_END;
                    } else {
                        this.f = Priority.START;
                    }
                }
            } else if (jSONObject2.getString("start_time").equals(MovieStatUtils.DOWNLOAD_FAILED)) {
                if (this.f != Priority.START_END) {
                    if (this.f == Priority.START) {
                        this.f = Priority.START_END;
                    } else {
                        this.f = Priority.END;
                    }
                }
            }
            if (titleInfoListener != null && hashMap.containsKey("text")) {
                String string = jSONObject2.getString("font");
                String string2 = jSONObject2.getString("text");
                String string3 = jSONObject2.getString("text_desc");
                String string4 = jSONObject2.has(str11) ? jSONObject2.getString(str11) : "";
                int parseInt = Integer.parseInt(jSONObject2.getString("text_max_len"));
                int parseInt2 = Integer.parseInt(jSONObject2.getString("font_size"));
                if (!jSONObject2.has("position") || (split = ((String) hashMap.get("position")).replace(" ", "").split(",")) == null) {
                    i2 = 0;
                    i = parseInt2;
                } else {
                    int parseInt3 = Integer.parseInt(split[0]);
                    int parseInt4 = Integer.parseInt(split[1]);
                    int parseInt5 = Integer.parseInt(split[2]);
                    i = Integer.parseInt(split[3]) - parseInt4;
                    i2 = parseInt5 - parseInt3;
                }
                titleInfoListener.OnTitleInfoListener(this.h, string, parseInt2, string2, parseInt, string3, string4, i2, i);
            }
            this.h++;
            this.g.add(hashMap);
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return e2.getMessage();
        }
    }

    /* access modifiers changed from: package-private */
    public int a(int i, List<nexOverlayManager.nexTitleInfo> list, int i2) {
        for (nexOverlayManager.nexTitleInfo next : list) {
            if (next.getId() == i) {
                int fontSize = next.getFontSize();
                String str = a;
                Log.d(str, "getFontSize() id=" + i + ", FontSize=" + fontSize);
                return fontSize <= 0 ? i2 : fontSize;
            }
        }
        String str2 = a;
        Log.d(str2, "getFontSize() id=" + i + ", defaultFontSize=" + i2);
        return i2;
    }

    /* access modifiers changed from: package-private */
    public int a(String str, List<nexOverlayManager.nexTitleInfo> list, int i) {
        for (nexOverlayManager.nexTitleInfo next : list) {
            if (next.getGroup().startsWith(str.substring(0, 1)) && next.getText() != null && next.getText().length() > 0) {
                return next.getFontSize();
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int a(boolean z) {
        Iterator<HashMap<String, String>> it = this.g.iterator();
        int i = 0;
        while (it.hasNext()) {
            HashMap next = it.next();
            int parseInt = Integer.parseInt((String) next.get("start_time"));
            int parseInt2 = Integer.parseInt((String) next.get("duration"));
            if (!z ? parseInt < 0 && i < parseInt2 : parseInt >= 0 && i < parseInt2) {
                i = parseInt2;
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    public TimeInterpolator a(String str) {
        char c2;
        switch (str.hashCode()) {
            case -1539081405:
                if (str.equals("DecelerateInterpolator")) {
                    c2 = 5;
                    break;
                }
            case -1327597199:
                if (str.equals("AnticipateInterpolator")) {
                    c2 = 1;
                    break;
                }
            case -1275674606:
                if (str.equals("OvershootInterpolator")) {
                    c2 = 7;
                    break;
                }
            case -1163632952:
                if (str.equals("AnticipateOvershootInterpolator")) {
                    c2 = 2;
                    break;
                }
            case -142581660:
                if (str.equals("AccelerateInterpolator")) {
                    c2 = 0;
                    break;
                }
            case 593057964:
                if (str.equals("LinearInterpolator")) {
                    c2 = 6;
                    break;
                }
            case 1416217487:
                if (str.equals("BounceInterpolator")) {
                    c2 = 3;
                    break;
                }
            case 1682001069:
                if (str.equals("CycleInterpolator")) {
                    c2 = 4;
                    break;
                }
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return new AccelerateInterpolator();
            case 1:
                return new AnticipateInterpolator();
            case 2:
                return new AnticipateOvershootInterpolator();
            case 3:
                return new BounceInterpolator();
            case 4:
                return new CycleInterpolator(1.0f);
            case 5:
                return new DecelerateInterpolator();
            case 6:
                return new LinearInterpolator();
            case 7:
                return new OvershootInterpolator();
            default:
                return new AccelerateDecelerateInterpolator();
        }
    }

    /* access modifiers changed from: package-private */
    public String a(int i, List<nexOverlayManager.nexTitleInfo> list, String str) {
        for (nexOverlayManager.nexTitleInfo next : list) {
            if (next.getId() == i) {
                return next.getFontID();
            }
        }
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0352  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0355  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0364  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0371  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0387  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x039c  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x03d4  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x03e3  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x03ea  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x03ed  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0402  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0338  */
    public String a(Context context, nexProject nexproject, List<nexOverlayManager.nexTitleInfo> list) {
        int i;
        int i2;
        String str;
        boolean z;
        Priority priority;
        boolean z2;
        nexProject nexproject2;
        char c2;
        int hashCode;
        HashMap hashMap;
        CharSequence charSequence;
        String str2;
        String str3;
        nexOverlayItem nexoverlayitem;
        int i3;
        String str4;
        boolean z3;
        String str5;
        String str6;
        String str7;
        String[] split;
        String str8;
        Paint.Align align;
        int i4;
        String str9;
        String str10;
        int i5;
        boolean z4;
        Paint.Align align2;
        String str11;
        int i6;
        int parseInt;
        int parseInt2;
        nexOverlayTitle nexoverlaytitle = this;
        nexProject nexproject3 = nexproject;
        List<nexOverlayManager.nexTitleInfo> list2 = list;
        nexoverlaytitle.a(nexproject3);
        Priority b2 = nexoverlaytitle.b(nexproject3);
        Iterator<HashMap<String, String>> it = nexoverlaytitle.g.iterator();
        String str12 = null;
        boolean z5 = false;
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            HashMap next = it.next();
            String str13 = (String) next.get(nexExportFormat.TAG_FORMAT_TYPE);
            Iterator<HashMap<String, String>> it2 = it;
            if (str13.equals("title") && next.containsKey("group") && ((String) next.get("group")).endsWith("_1")) {
                if (str12 == null) {
                    str12 = (String) next.get("group");
                    z5 = nexoverlaytitle.a(list2, str12);
                    if (z5 && next.containsKey("adjust_pos_non_sub")) {
                        String[] split2 = ((String) next.get("adjust_pos_non_sub")).replace(" ", "").split(",");
                        parseInt = Integer.parseInt(split2[0]);
                        parseInt2 = Integer.parseInt(split2[1]);
                    }
                } else if (!str12.equals(next.get("group"))) {
                    str12 = (String) next.get("group");
                    z5 = nexoverlaytitle.a(list2, str12);
                    if (!z5 || !next.containsKey("adjust_pos_non_sub")) {
                        str = str12;
                        z = z5;
                        i2 = 0;
                        i = 0;
                        if (!z || !next.containsKey("group") || !((String) next.get("group")).endsWith("_2")) {
                            c2 = 65535;
                            hashCode = str13.hashCode();
                            if (hashCode != 100313435) {
                                if (hashCode == 110371416 && str13.equals("title")) {
                                    c2 = 0;
                                }
                            } else if (str13.equals("image")) {
                                c2 = 1;
                            }
                            if (c2 == 0) {
                                int parseInt3 = Integer.parseInt((String) next.get("id"));
                                String str14 = (String) next.get("text");
                                Integer.parseInt((String) next.get("text_max_len"));
                                String[] split3 = ((String) next.get("position")).replace(" ", "").split(",");
                                if (split3 == null) {
                                    return "Wrong position data of title";
                                }
                                int parseInt4 = Integer.parseInt(split3[0]);
                                int parseInt5 = Integer.parseInt(split3[1]);
                                int parseInt6 = Integer.parseInt(split3[2]);
                                int parseInt7 = Integer.parseInt(split3[3]);
                                int i9 = (parseInt4 + parseInt6) / 2;
                                int i10 = (parseInt5 + parseInt7) / 2;
                                int i11 = parseInt6 - parseInt4;
                                int i12 = parseInt7 - parseInt5;
                                int parseInt8 = Integer.parseInt((String) next.get("start_time"));
                                int parseInt9 = Integer.parseInt((String) next.get("duration"));
                                if ((b2 != Priority.START || parseInt8 >= 0) && (b2 != Priority.END || parseInt8 < 0)) {
                                    String a2 = nexoverlaytitle.a(parseInt3, list2, (String) next.get("font"));
                                    int a3 = nexoverlaytitle.a(parseInt3, list2, Integer.parseInt((String) next.get("font_size")));
                                    priority = b2;
                                    int parseColor = Color.parseColor((String) next.get("text_color"));
                                    if (a3 > i12) {
                                        a3 = i12 - 4;
                                    }
                                    boolean z6 = Integer.parseInt((String) next.get("shadow_visible")) == 1;
                                    int parseColor2 = Color.parseColor((String) next.get("shadow_color"));
                                    str3 = "start_time";
                                    boolean z7 = Integer.parseInt((String) next.get("glow_visible")) == 1;
                                    int parseColor3 = Color.parseColor((String) next.get("glow_color"));
                                    str2 = ",";
                                    charSequence = " ";
                                    boolean z8 = Integer.parseInt((String) next.get("outline_visible")) == 1;
                                    int parseColor4 = Color.parseColor((String) next.get("outline_color"));
                                    String str15 = (String) next.get("align");
                                    if (z) {
                                        str11 = str15;
                                        if (next.containsKey("adjust_align_non_sub")) {
                                            str8 = (String) next.get("adjust_align_non_sub");
                                            Paint.Align align3 = Paint.Align.CENTER;
                                            hashMap = next;
                                            if (!str8.contains("LEFT")) {
                                                align2 = Paint.Align.LEFT;
                                            } else if (str8.contains("RIGHT")) {
                                                align2 = Paint.Align.RIGHT;
                                            } else {
                                                align = align3;
                                                i4 = str8.contains("MIDDLE") ? 2 : str8.contains("BOTTOM") ? 3 : 1;
                                                if (z) {
                                                    str9 = nexoverlaytitle.a(str, list2);
                                                    a3 = nexoverlaytitle.a(str, list2, a3);
                                                    i9 += i2;
                                                    i10 += i;
                                                } else {
                                                    str9 = nexoverlaytitle.b(parseInt3, list2, str14);
                                                }
                                                str10 = str9;
                                                int i13 = i9;
                                                int i14 = i10;
                                                if (str10 != null && str10.length() > 0) {
                                                    if (parseInt8 >= 0) {
                                                        i5 = parseInt8;
                                                    } else if (nexproject.getTotalTime() < parseInt9) {
                                                        parseInt9 = nexproject.getTotalTime();
                                                        i5 = 0;
                                                    } else {
                                                        i5 = nexproject.getTotalTime() - parseInt9;
                                                    }
                                                    q qVar = new q(context, str10, i11, i12, 1.0f);
                                                    nexOverlayItem nexoverlayitem2 = new nexOverlayItem(new nexOverlayImage("" + parseInt3, (nexOverlayImage.runTimeMakeBitMap) qVar), i13, i14, i5, i5 + parseInt9);
                                                    qVar.a((float) a3, parseColor, align, a2, i4);
                                                    if (z6) {
                                                        qVar.a(true, parseColor2, 5.0f, 3.0f, 3.0f);
                                                    }
                                                    if (z7) {
                                                        z4 = true;
                                                        qVar.b(true, parseColor3, 8.0f);
                                                    } else {
                                                        z4 = true;
                                                    }
                                                    if (z8) {
                                                        qVar.a(z4, parseColor4, 1.0f);
                                                    }
                                                    qVar.a(nexoverlayitem2.getId());
                                                    nexoverlayitem = nexoverlayitem2;
                                                }
                                                nexproject2 = nexproject;
                                                z2 = z;
                                                nexoverlaytitle = this;
                                                list2 = list;
                                                nexproject3 = nexproject2;
                                                str12 = str;
                                                it = it2;
                                                i7 = i2;
                                                i8 = i;
                                                z5 = z2;
                                                b2 = priority;
                                            }
                                            align = align2;
                                            if (str8.contains("MIDDLE")) {
                                            }
                                            if (z) {
                                            }
                                            str10 = str9;
                                            int i132 = i9;
                                            int i142 = i10;
                                            if (parseInt8 >= 0) {
                                            }
                                            q qVar2 = new q(context, str10, i11, i12, 1.0f);
                                            nexOverlayItem nexoverlayitem22 = new nexOverlayItem(new nexOverlayImage("" + parseInt3, (nexOverlayImage.runTimeMakeBitMap) qVar2), i132, i142, i5, i5 + parseInt9);
                                            qVar2.a((float) a3, parseColor, align, a2, i4);
                                            if (z6) {
                                            }
                                            if (z7) {
                                            }
                                            if (z8) {
                                            }
                                            qVar2.a(nexoverlayitem22.getId());
                                            nexoverlayitem = nexoverlayitem22;
                                        }
                                    } else {
                                        str11 = str15;
                                    }
                                    str8 = str11;
                                    Paint.Align align32 = Paint.Align.CENTER;
                                    hashMap = next;
                                    if (!str8.contains("LEFT")) {
                                    }
                                    align = align2;
                                    if (str8.contains("MIDDLE")) {
                                    }
                                    if (z) {
                                    }
                                    str10 = str9;
                                    int i1322 = i9;
                                    int i1422 = i10;
                                    if (parseInt8 >= 0) {
                                    }
                                    q qVar22 = new q(context, str10, i11, i12, 1.0f);
                                    nexOverlayItem nexoverlayitem222 = new nexOverlayItem(new nexOverlayImage("" + parseInt3, (nexOverlayImage.runTimeMakeBitMap) qVar22), i1322, i1422, i5, i5 + parseInt9);
                                    qVar22.a((float) a3, parseColor, align, a2, i4);
                                    if (z6) {
                                    }
                                    if (z7) {
                                    }
                                    if (z8) {
                                    }
                                    qVar22.a(nexoverlayitem222.getId());
                                    nexoverlayitem = nexoverlayitem222;
                                } else {
                                    Log.d(a, String.format("Apply title skip(%s %d)", new Object[]{b2.name(), Integer.valueOf(parseInt8)}));
                                }
                            } else if (c2 != 1) {
                                str3 = "start_time";
                                hashMap = next;
                                priority = b2;
                                str2 = ",";
                                charSequence = " ";
                                nexoverlayitem = null;
                            } else {
                                Integer.parseInt((String) next.get("id"));
                                String[] split4 = ((String) next.get("position")).replace(" ", "").split(",");
                                if (split4 == null) {
                                    return "Wrong position data of title";
                                }
                                int parseInt10 = Integer.parseInt(split4[0]);
                                int parseInt11 = Integer.parseInt(split4[1]);
                                int parseInt12 = (parseInt10 + Integer.parseInt(split4[2])) / 2;
                                int parseInt13 = (parseInt11 + Integer.parseInt(split4[3])) / 2;
                                int parseInt14 = Integer.parseInt((String) next.get("start_time"));
                                int parseInt15 = Integer.parseInt((String) next.get("duration"));
                                if ((b2 != Priority.START || parseInt14 >= 0) && (b2 != Priority.END || parseInt14 < 0)) {
                                    if (parseInt14 < 0) {
                                        if (nexproject.getTotalTime() < parseInt15) {
                                            parseInt15 = nexproject.getTotalTime();
                                            i6 = 0;
                                            nexoverlayitem = new nexOverlayItem(nexoverlaytitle.b((String) next.get("image_res")), parseInt12, parseInt13, i6, i6 + parseInt15);
                                            str3 = "start_time";
                                            hashMap = next;
                                            priority = b2;
                                            str2 = ",";
                                            charSequence = " ";
                                        } else {
                                            parseInt14 = nexproject.getTotalTime() - parseInt15;
                                        }
                                    }
                                    i6 = parseInt14;
                                    nexoverlayitem = new nexOverlayItem(nexoverlaytitle.b((String) next.get("image_res")), parseInt12, parseInt13, i6, i6 + parseInt15);
                                    str3 = "start_time";
                                    hashMap = next;
                                    priority = b2;
                                    str2 = ",";
                                    charSequence = " ";
                                } else {
                                    Log.d(a, String.format("Apply image res skip(%s %d)", new Object[]{b2.name(), Integer.valueOf(parseInt14)}));
                                }
                            }
                            if (nexoverlayitem != null) {
                                String str16 = "rotate";
                                HashMap hashMap2 = hashMap;
                                if (hashMap2.containsKey(str16)) {
                                    nexoverlayitem.setRotate(Float.parseFloat((String) hashMap2.get(str16)));
                                }
                                String str17 = "scale";
                                if (!hashMap2.containsKey(str17) || (split = ((String) hashMap2.get(str17)).replace(charSequence, "").split(str2)) == null) {
                                    i3 = 0;
                                } else {
                                    i3 = 0;
                                    nexoverlayitem.setScale(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]));
                                }
                                nexoverlayitem.clearAnimate();
                                String str18 = (String) hashMap2.get("animation");
                                if (z && hashMap2.containsKey("adjust_animation_non_sub")) {
                                    str18 = (String) hashMap2.get("adjust_animation_non_sub");
                                }
                                JSONArray jSONArray = new JSONArray(str18);
                                int i15 = i3;
                                while (i15 < jSONArray.length()) {
                                    JSONObject jSONObject = jSONArray.getJSONObject(i15);
                                    String string = jSONObject.getString(nexExportFormat.TAG_FORMAT_TYPE);
                                    JSONArray jSONArray2 = jSONObject.getJSONArray("values");
                                    int i16 = -1;
                                    switch (string.hashCode()) {
                                        case -925180581:
                                            if (string.equals(str16)) {
                                                i16 = 3;
                                                break;
                                            }
                                            break;
                                        case 3357649:
                                            if (string.equals("move")) {
                                                i16 = i3;
                                                break;
                                            }
                                            break;
                                        case 92909918:
                                            if (string.equals("alpha")) {
                                                i16 = 1;
                                                break;
                                            }
                                            break;
                                        case 109250890:
                                            if (string.equals(str17)) {
                                                i16 = 2;
                                                break;
                                            }
                                            break;
                                    }
                                    String str19 = "end_time";
                                    String str20 = "motion_type";
                                    if (i16 != 0) {
                                        if (i16 != 1) {
                                            char c3 = 2;
                                            if (i16 == 2) {
                                                str5 = str16;
                                                z3 = z;
                                                str6 = str17;
                                                str7 = str3;
                                                for (int i17 = 0; i17 < jSONArray2.length(); i17++) {
                                                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i17);
                                                    TimeInterpolator a4 = nexoverlaytitle.a(jSONObject2.getString(str20));
                                                    int parseInt16 = Integer.parseInt(jSONObject2.getString(str7));
                                                    nexoverlayitem.addAnimate(nexAnimate.getScale(parseInt16, Integer.parseInt(jSONObject2.getString(str19)) - parseInt16, Float.parseFloat(jSONObject2.getString("start_x")), Float.parseFloat(jSONObject2.getString("start_y")), Float.parseFloat(jSONObject2.getString("end_x")), Float.parseFloat(jSONObject2.getString("end_y"))).setInterpolator(a4));
                                                }
                                            } else if (i16 != 3) {
                                                str5 = str16;
                                                z3 = z;
                                                str6 = str17;
                                                str4 = str3;
                                                i15++;
                                                nexoverlaytitle = this;
                                                List<nexOverlayManager.nexTitleInfo> list3 = list;
                                                str17 = str6;
                                                str16 = str5;
                                                z = z3;
                                                str3 = str4;
                                                i3 = 0;
                                            } else {
                                                int i18 = i3;
                                                while (i18 < jSONArray2.length()) {
                                                    try {
                                                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i18);
                                                        TimeInterpolator a5 = nexoverlaytitle.a(jSONObject3.getString(str20));
                                                        String str21 = str3;
                                                        int parseInt17 = Integer.parseInt(jSONObject3.getString(str21));
                                                        String str22 = str16;
                                                        boolean z9 = z;
                                                        nexoverlayitem.addAnimate(nexAnimate.getRotate(parseInt17, Integer.parseInt(jSONObject3.getString(str19)) - parseInt17, Integer.parseInt(jSONObject3.getString("clockwise")) == 1, Float.parseFloat(jSONObject3.getString("rotatedegree")), (nexAnimate.MoveTrackingPath) null).setInterpolator(a5));
                                                        i18++;
                                                        List<nexOverlayManager.nexTitleInfo> list4 = list;
                                                        str3 = str21;
                                                        str17 = str17;
                                                        str16 = str22;
                                                        z = z9;
                                                        c3 = 2;
                                                    } catch (JSONException e2) {
                                                        e2.printStackTrace();
                                                        Log.d(a, "applyOverlayAsset failed" + e2.getMessage());
                                                        return e2.getMessage();
                                                    }
                                                }
                                                str5 = str16;
                                                z3 = z;
                                                str6 = str17;
                                                char c4 = c3;
                                                str4 = str3;
                                            }
                                        } else {
                                            str5 = str16;
                                            z3 = z;
                                            str6 = str17;
                                            str7 = str3;
                                            for (int i19 = 0; i19 < jSONArray2.length(); i19++) {
                                                JSONObject jSONObject4 = jSONArray2.getJSONObject(i19);
                                                TimeInterpolator a6 = nexoverlaytitle.a(jSONObject4.getString(str20));
                                                int parseInt18 = Integer.parseInt(jSONObject4.getString(str7));
                                                nexoverlayitem.addAnimate(nexAnimate.getAlpha(parseInt18, Integer.parseInt(jSONObject4.getString(str19)) - parseInt18, Float.parseFloat(jSONObject4.getString("start")), Float.parseFloat(jSONObject4.getString("end"))).setInterpolator(a6));
                                            }
                                        }
                                        str4 = str7;
                                    } else {
                                        str5 = str16;
                                        z3 = z;
                                        str6 = str17;
                                        str7 = str3;
                                        int i20 = 0;
                                        while (i20 < jSONArray2.length()) {
                                            JSONObject jSONObject5 = jSONArray2.getJSONObject(i20);
                                            TimeInterpolator a7 = nexoverlaytitle.a(jSONObject5.getString(str20));
                                            int parseInt19 = Integer.parseInt(jSONObject5.getString(str7));
                                            int parseInt20 = Integer.parseInt(jSONObject5.getString(str19));
                                            final float parseFloat = Float.parseFloat(jSONObject5.getString("start_x"));
                                            String str23 = str20;
                                            AnonymousClass1 r7 = r1;
                                            String str24 = str7;
                                            final float parseFloat2 = Float.parseFloat(jSONObject5.getString("end_x"));
                                            String str25 = str19;
                                            final float parseFloat3 = Float.parseFloat(jSONObject5.getString("start_y"));
                                            JSONArray jSONArray3 = jSONArray2;
                                            final float parseFloat4 = Float.parseFloat(jSONObject5.getString("end_y"));
                                            AnonymousClass1 r1 = new nexAnimate.MoveTrackingPath() {
                                                public float getTranslatePosition(int i, float f) {
                                                    float f2;
                                                    float f3;
                                                    if (i == 1) {
                                                        f2 = Math.abs(parseFloat - parseFloat2) * f;
                                                        if (parseFloat >= parseFloat2) {
                                                            f2 = -f2;
                                                        }
                                                        f3 = parseFloat;
                                                    } else if (i != 2) {
                                                        return 0.0f;
                                                    } else {
                                                        float abs = Math.abs(parseFloat3 - parseFloat4) * f;
                                                        if (parseFloat3 >= parseFloat4) {
                                                            abs = -abs;
                                                        }
                                                        f3 = parseFloat3;
                                                    }
                                                    return f3 + f2;
                                                }
                                            };
                                            nexoverlayitem.addAnimate(nexAnimate.getMove(parseInt19, parseInt20 - parseInt19, r7).setInterpolator(a7));
                                            i20++;
                                            nexoverlaytitle = this;
                                            str19 = str25;
                                            jSONArray2 = jSONArray3;
                                            str20 = str23;
                                            str7 = str24;
                                        }
                                        str4 = str7;
                                    }
                                    i15++;
                                    nexoverlaytitle = this;
                                    List<nexOverlayManager.nexTitleInfo> list32 = list;
                                    str17 = str6;
                                    str16 = str5;
                                    z = z3;
                                    str3 = str4;
                                    i3 = 0;
                                }
                                z2 = z;
                                nexoverlayitem.setOverlayTitle(true);
                                nexproject2 = nexproject;
                                nexproject2.addOverlay(nexoverlayitem);
                                nexoverlaytitle = this;
                                list2 = list;
                                nexproject3 = nexproject2;
                                str12 = str;
                                it = it2;
                                i7 = i2;
                                i8 = i;
                                z5 = z2;
                                b2 = priority;
                            }
                            nexproject2 = nexproject;
                            z2 = z;
                            nexoverlaytitle = this;
                            list2 = list;
                            nexproject3 = nexproject2;
                            str12 = str;
                            it = it2;
                            i7 = i2;
                            i8 = i;
                            z5 = z2;
                            b2 = priority;
                        }
                        nexproject2 = nexproject3;
                        priority = b2;
                        z2 = z;
                        nexoverlaytitle = this;
                        list2 = list;
                        nexproject3 = nexproject2;
                        str12 = str;
                        it = it2;
                        i7 = i2;
                        i8 = i;
                        z5 = z2;
                        b2 = priority;
                    } else {
                        String[] split5 = ((String) next.get("adjust_pos_non_sub")).replace(" ", "").split(",");
                        parseInt = Integer.parseInt(split5[0]);
                        parseInt2 = Integer.parseInt(split5[1]);
                    }
                }
                str = str12;
                z = z5;
                i = parseInt2;
                i2 = parseInt;
                c2 = 65535;
                hashCode = str13.hashCode();
                if (hashCode != 100313435) {
                }
                if (c2 == 0) {
                }
                if (nexoverlayitem != null) {
                }
                nexproject2 = nexproject;
                z2 = z;
                nexoverlaytitle = this;
                list2 = list;
                nexproject3 = nexproject2;
                str12 = str;
                it = it2;
                i7 = i2;
                i8 = i;
                z5 = z2;
                b2 = priority;
            }
            str = str12;
            z = z5;
            i2 = i7;
            i = i8;
            c2 = 65535;
            hashCode = str13.hashCode();
            if (hashCode != 100313435) {
            }
            if (c2 == 0) {
            }
            if (nexoverlayitem != null) {
            }
            nexproject2 = nexproject;
            z2 = z;
            nexoverlaytitle = this;
            list2 = list;
            nexproject3 = nexproject2;
            str12 = str;
            it = it2;
            i7 = i2;
            i8 = i;
            z5 = z2;
            b2 = priority;
        }
        return null;
    }

    public String a(nexProject nexproject) {
        if (nexproject == null) {
            return "Null project";
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (nexOverlayItem next : nexproject.getOverlayItems()) {
            if (next.getOverlayTitle()) {
                arrayList.add(Integer.valueOf(next.getId()));
            }
        }
        for (Integer intValue : arrayList) {
            nexproject.removeOverlay(intValue.intValue());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String a(String str, List<nexOverlayManager.nexTitleInfo> list) {
        for (nexOverlayManager.nexTitleInfo next : list) {
            if (next.getGroup().startsWith(str.substring(0, 1)) && next.getText() != null && next.getText().length() > 0) {
                return next.getText();
            }
        }
        return null;
    }

    public String a(JSONObject jSONObject, TitleInfoListener titleInfoListener) {
        if (!a(jSONObject)) {
            return "parseOverlayAssetInfo parse error";
        }
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("overlay");
            for (int i = 0; i < jSONArray.length(); i++) {
                String b2 = b(jSONArray.getJSONObject(i), titleInfoListener);
                if (b2 != null) {
                    this.g.clear();
                    String str = a;
                    Log.d(str, "Overlay parse error : " + b2);
                    return b2;
                }
            }
            Log.d(a, "parseOverlayAsset end");
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.g.clear();
            String str2 = a;
            Log.d(str2, "parseOverlayAsset failed" + e2.getMessage());
            return e2.getMessage();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(List<nexOverlayManager.nexTitleInfo> list, String str) {
        boolean z = false;
        for (nexOverlayManager.nexTitleInfo next : list) {
            if (next.getGroup().startsWith(str.substring(0, 1)) && (next.getText() == null || next.getText().length() <= 0)) {
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public Priority b(nexProject nexproject) {
        return nexproject.getTotalTime() >= a(true) + a(false) ? Priority.START_END : this.e.equals("start") ? Priority.START : Priority.END;
    }

    /* access modifiers changed from: package-private */
    public String b(int i, List<nexOverlayManager.nexTitleInfo> list, String str) {
        for (nexOverlayManager.nexTitleInfo next : list) {
            if (next.getId() == i) {
                return next.getText();
            }
        }
        return str;
    }
}
