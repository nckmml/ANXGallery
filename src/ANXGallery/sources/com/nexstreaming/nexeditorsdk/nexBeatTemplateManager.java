package com.nexstreaming.nexeditorsdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.google.gson_nex.Gson;
import com.nexstreaming.kminternal.json.TemplateMetaData;
import com.nexstreaming.nexeditorsdk.nexAssetPackageManager;
import com.nexstreaming.nexeditorsdk.nexCrop;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class nexBeatTemplateManager {
    private static final String TAG = "nexMusicTempMan";
    private static Context mAppContext;
    private static nexBeatTemplateManager sSingleton;
    private List<BeatTemplate> externalView_musicTemplates = null;
    private Object m_musicTemplateLock = new Object();
    private List<BeatTemplate> musicTemplates = new ArrayList();

    public static class BeatTemplate extends nexAssetPackageManager.c {
        private String bgmId;
        private int internalSourceCount = -1;
        private int maxExtendCount = -1;
        private int maxRecommendCount;
        private int maxSourceCount;
        private boolean parsed = false;

        BeatTemplate(nexAssetPackageManager.Item item) {
            super(item);
        }

        private void parseTemplate() {
            TemplateMetaData.Music music;
            if (!this.parsed) {
                int i = 1;
                this.parsed = true;
                String AssetPackageTemplateJsonToString = nexTemplateComposer.AssetPackageTemplateJsonToString(id());
                if (AssetPackageTemplateJsonToString != null && (music = (TemplateMetaData.Music) new Gson().fromJson(AssetPackageTemplateJsonToString, TemplateMetaData.Music.class)) != null) {
                    this.bgmId = music.string_audio_id;
                    int i2 = -1;
                    Iterator<TemplateMetaData.EffectEntry> it = music.list_effectEntries.iterator();
                    int i3 = 0;
                    int i4 = 0;
                    while (it.hasNext()) {
                        TemplateMetaData.EffectEntry next = it.next();
                        i2++;
                        if (next.b_source_change || next.int_priority > 0) {
                            if (next.int_priority > 0) {
                                i3++;
                            } else if (next.internal_clip_id != null) {
                                i4++;
                            } else {
                                i++;
                            }
                        }
                    }
                    this.maxSourceCount = i2;
                    this.maxRecommendCount = i;
                    this.maxExtendCount = i3;
                    this.internalSourceCount = i4;
                }
            }
        }

        public static BeatTemplate promote(nexAssetPackageManager.c cVar) {
            if (cVar.category() != nexAssetPackageManager.Category.beattemplate) {
                return null;
            }
            return new BeatTemplate(cVar);
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Category category() {
            return super.category();
        }

        public String getBGMId() {
            parseTemplate();
            return this.bgmId;
        }

        public int getInternalSourceCount() {
            if (this.internalSourceCount == -1) {
                parseTemplate();
            }
            return this.internalSourceCount;
        }

        public int getMaxExtendCount() {
            if (this.maxExtendCount < 0) {
                parseTemplate();
            }
            return this.maxExtendCount;
        }

        public int getMaxRecommendCount() {
            if (this.maxRecommendCount == 0) {
                parseTemplate();
            }
            return this.maxRecommendCount;
        }

        public int getMaxSourceCount() {
            if (this.maxSourceCount == 0) {
                if (id().contains(".sc.")) {
                    String id = id();
                    String substring = id.substring(id.indexOf(".sc.") + 4, id.length());
                    if (substring != null && substring.length() > 0) {
                        this.maxSourceCount = Integer.parseInt(substring);
                        return this.maxSourceCount;
                    }
                } else {
                    parseTemplate();
                }
            }
            return this.maxSourceCount;
        }

        public /* bridge */ /* synthetic */ String[] getSupportedLocales() {
            return super.getSupportedLocales();
        }

        public /* bridge */ /* synthetic */ boolean hidden() {
            return super.hidden();
        }

        public /* bridge */ /* synthetic */ Bitmap icon() {
            return super.icon();
        }

        public /* bridge */ /* synthetic */ String id() {
            return super.id();
        }

        public /* bridge */ /* synthetic */ boolean isDelete() {
            return super.isDelete();
        }

        public /* bridge */ /* synthetic */ String name(String str) {
            return super.name(str);
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.Asset packageInfo() {
            return super.packageInfo();
        }

        public /* bridge */ /* synthetic */ Bitmap thumbnail() {
            return super.thumbnail();
        }

        public /* bridge */ /* synthetic */ nexAssetPackageManager.ItemMethodType type() {
            return super.type();
        }

        public /* bridge */ /* synthetic */ boolean validate() {
            return super.validate();
        }
    }

    public enum Level {
        Recommend,
        Extends,
        Max
    }

    private static class a {
        public int a;
        public int b;
        public String c;
        public String d;
        public boolean e;
        public String f;
        public int g;
        public int h;

        private a() {
        }
    }

    private nexBeatTemplateManager(Context context) {
        mAppContext = context;
    }

    public static nexBeatTemplateManager getBeatTemplateManager(Context context) {
        if (sSingleton != null && !mAppContext.getPackageName().equals(context.getPackageName())) {
            sSingleton = null;
        }
        if (sSingleton == null) {
            sSingleton = new nexBeatTemplateManager(context);
        }
        return sSingleton;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: com.nexstreaming.nexeditorsdk.nexBeatTemplateManager$a} */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x04ae, code lost:
        if (r0 >= r4.length) goto L_0x04b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x04b2, code lost:
        r9 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x04cc, code lost:
        if (r0 >= r4.length) goto L_0x04b0;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0524  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0536 A[EDGE_INSN: B:204:0x0536->B:169:0x0536 ?: BREAK  , SYNTHETIC] */
    static boolean internalApplyTemplateToProjectById(nexProject nexproject, String str) {
        int i;
        int i2;
        int i3;
        TemplateMetaData.Music music;
        int i4;
        int i5;
        int i6;
        int i7;
        nexProject nexproject2 = nexproject;
        String AssetPackageTemplateJsonToString = nexTemplateComposer.AssetPackageTemplateJsonToString(str);
        Level level = Level.Max;
        if (AssetPackageTemplateJsonToString == null) {
            return false;
        }
        TemplateMetaData.Music music2 = (TemplateMetaData.Music) new Gson().fromJson(AssetPackageTemplateJsonToString, TemplateMetaData.Music.class);
        Iterator<TemplateMetaData.EffectEntry> it = music2.list_effectEntries.iterator();
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 10800000;
        int i12 = -1;
        int i13 = 1;
        while (it.hasNext()) {
            TemplateMetaData.EffectEntry next = it.next();
            if (i8 > 0 && i11 > next.int_time - i8) {
                i11 = next.int_time - i8;
            }
            i8 = next.int_time;
            i12++;
            if (next.b_source_change || next.int_priority > 0) {
                if (music2.default_effect != null) {
                    if (next.color_filter_id == null) {
                        next.color_filter_id = music2.default_effect.sc_color_effect_id;
                    }
                    if (next.clip_effect_id == null) {
                        next.clip_effect_id = music2.default_effect.sc_clip_effect_id;
                    }
                }
                if (next.int_priority > 0) {
                    i9++;
                } else if (next.internal_clip_id != null) {
                    i10++;
                } else {
                    i13++;
                }
            } else {
                if (music2.default_effect != null) {
                    if (next.color_filter_id == null) {
                        next.color_filter_id = music2.default_effect.color_filter_id;
                    }
                    if (next.clip_effect_id == null) {
                        next.clip_effect_id = music2.default_effect.clip_effect_id;
                    }
                }
                if (next.clip_effect_id != null) {
                    if (next.int_effect_in_duration == -1) {
                        next.int_effect_in_duration = 50;
                    }
                    if (next.int_effect_out_duration == -1) {
                        next.int_effect_out_duration = 50;
                    }
                }
            }
        }
        music2.list_effectEntries.get(0).int_effect_in_duration = 0;
        music2.list_effectEntries.get(0).int_effect_out_duration = 0;
        AnonymousClass1 r6 = null;
        music2.list_effectEntries.get(0).clip_effect_id = null;
        music2.list_effectEntries.get(music2.list_effectEntries.size() - 1).int_effect_in_duration = 0;
        music2.list_effectEntries.get(music2.list_effectEntries.size() - 1).int_effect_out_duration = 0;
        int totalClipCount = nexproject2.getTotalClipCount(true);
        if (totalClipCount > i13) {
            i2 = totalClipCount - i13;
            if (i2 > i9) {
                i = i2 - i9;
                i2 = i9;
            } else {
                i = 0;
            }
        } else {
            i2 = 0;
            i = 0;
        }
        if (level == Level.Extends) {
            i = 0;
        } else if (level == Level.Recommend) {
            i2 = 0;
            i = 0;
        }
        Log.d(TAG, "sourceClipCount=" + totalClipCount + ", recommend=" + i13 + ", extends=" + i9 + " ,max=" + i12 + ", priorityLevel=" + i2 + ", eventLevel=" + i + ", internal=" + i10);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<TemplateMetaData.EffectEntry> it2 = music2.list_effectEntries.iterator();
        while (it2.hasNext()) {
            TemplateMetaData.EffectEntry next2 = it2.next();
            if (next2.int_priority > 0) {
                if (next2.int_priority <= i2) {
                    next2.b_source_change = true;
                    Log.d(TAG, "t=" + next2.int_time + ", priority=" + next2.int_priority);
                }
            }
            if (!next2.b_source_change && i > 0 && next2.internal_clip_id == null && next2.int_time > 0) {
                next2.b_source_change = true;
                i--;
                Log.d(TAG, "t=" + next2.int_time + ", event=" + i);
            }
            arrayList2.add(next2);
        }
        int size = arrayList2.size() - 1;
        int i14 = 0;
        int i15 = 0;
        while (i14 < size) {
            a aVar = new a();
            aVar.a = i15;
            aVar.c = ((TemplateMetaData.EffectEntry) arrayList2.get(i14)).color_filter_id;
            aVar.f = ((TemplateMetaData.EffectEntry) arrayList2.get(i14)).internal_clip_id;
            int i16 = i14 + 1;
            if (((TemplateMetaData.EffectEntry) arrayList2.get(i16)).b_source_change) {
                aVar.b = ((TemplateMetaData.EffectEntry) arrayList2.get(i16)).int_time;
            } else {
                aVar.b = ((TemplateMetaData.EffectEntry) arrayList2.get(i16)).int_time - ((TemplateMetaData.EffectEntry) arrayList2.get(i16)).int_effect_in_duration;
            }
            int i17 = aVar.b;
            int i18 = aVar.b - aVar.a;
            if (i18 < 100) {
                Log.w(TAG, "W[" + i14 + "](" + aVar.a + ") lower duration (" + i18 + ")");
            }
            if (((TemplateMetaData.EffectEntry) arrayList2.get(i14)).b_source_change) {
                aVar.e = true;
            } else {
                aVar.d = ((TemplateMetaData.EffectEntry) arrayList2.get(i14)).clip_effect_id;
                aVar.g = 0;
                aVar.h = ((TemplateMetaData.EffectEntry) arrayList2.get(i14)).int_effect_in_duration + ((TemplateMetaData.EffectEntry) arrayList2.get(i14)).int_effect_out_duration;
                if (aVar.h < 100 && i14 != 0) {
                    Log.w(TAG, "W[" + i14 + "](" + aVar.a + ") lower effect duration (" + aVar.h + ")");
                }
                if (i18 < aVar.h) {
                    Log.w(TAG, "W[" + i14 + "](" + aVar.a + ") higher effect duration (" + i18 + " < " + aVar.h + ")");
                    aVar.h = i18;
                }
            }
            if (aVar.a < aVar.b) {
                arrayList.add(aVar);
            }
            i14 = i16;
            i15 = i17;
            r6 = null;
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            a aVar2 = (a) it3.next();
            Log.d(TAG, "st=" + aVar2.a + ",et=" + aVar2.b + ",sc=" + aVar2.e + ",ei=" + aVar2.d + ", cf=" + aVar2.c + ", et=" + aVar2.h + ", is=" + aVar2.f);
        }
        nexProject clone = nexProject.clone(nexproject);
        nexproject2.allClear(true);
        int totalClipCount2 = clone.getTotalClipCount(true);
        nexColorEffect[] internalPresetList = nexColorEffect.getInternalPresetList();
        int size2 = arrayList.size();
        int i19 = 0;
        int i20 = 0;
        int i21 = 0;
        while (i19 < size2) {
            a aVar3 = (a) arrayList.get(i19);
            if (aVar3.e && aVar3.f == null && (i20 = i20 + 1) >= totalClipCount2) {
                i20 = 0;
            }
            if (aVar3.f != null) {
                nexproject2.add(new nexClip(aVar3.f, "@assetItem:" + aVar3.f));
                i3 = 1;
                nexproject.getLastPrimaryClip().setAssetResource(true);
            } else {
                i3 = 1;
                nexproject2.add(nexClip.dup(clone.getClip(i20, true)));
                nexproject.getLastPrimaryClip().setRotateDegree(clone.getClip(i20, true).getRotateDegree());
            }
            if (nexproject.getLastPrimaryClip().getClipType() == i3) {
                nexproject.getLastPrimaryClip().setImageClipDuration(aVar3.b - aVar3.a);
                nexproject.getLastPrimaryClip().getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FILL);
                if (aVar3.c != null) {
                    if (aVar3.c.compareToIgnoreCase("rand") != 0) {
                        Iterator<nexColorEffect> it4 = nexColorEffect.getPresetList().iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                break;
                            }
                            nexColorEffect next3 = it4.next();
                            if (next3.getPresetName().compareToIgnoreCase(aVar3.c) == 0) {
                                nexproject.getLastPrimaryClip().setColorEffect(next3);
                                break;
                            }
                        }
                    } else {
                        nexproject.getLastPrimaryClip().setColorEffect(internalPresetList[i21]);
                        i21++;
                        if (i21 >= internalPresetList.length) {
                            i21 = 0;
                        }
                    }
                }
                if (!(aVar3.d == null || aVar3.d.compareToIgnoreCase("none") == 0)) {
                    nexproject.getLastPrimaryClip().getClipEffect().setEffect(aVar3.d);
                    nexproject.getLastPrimaryClip().getClipEffect().setEffectShowTime(aVar3.g, aVar3.h);
                }
                music = music2;
                i5 = i19;
            } else {
                if (nexproject.getLastPrimaryClip().getClipType() == 4) {
                    int startTrimTime = clone.getClip(i20, true).getVideoClipEdit().getStartTrimTime();
                    int endTrimTime = clone.getClip(i20, true).getVideoClipEdit().getEndTrimTime();
                    i6 = i19;
                    int i22 = aVar3.b - aVar3.a;
                    if (startTrimTime + i22 > endTrimTime) {
                        music = music2;
                    } else {
                        boolean z = false;
                        while (true) {
                            music = music2;
                            int i23 = i22 + startTrimTime;
                            nexproject.getLastPrimaryClip().getVideoClipEdit().setTrim(startTrimTime, i23);
                            nexproject.getLastPrimaryClip().getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FILL);
                            if (z) {
                                nexproject.getLastPrimaryClip().setColorEffect(internalPresetList[i21]);
                                i7 = i21 + 1;
                            } else {
                                if (aVar3.c != null) {
                                    if (aVar3.c.compareToIgnoreCase("rand") != 0) {
                                        Iterator<nexColorEffect> it5 = nexColorEffect.getPresetList().iterator();
                                        while (true) {
                                            if (!it5.hasNext()) {
                                                break;
                                            }
                                            nexColorEffect next4 = it5.next();
                                            if (next4.getPresetName().compareToIgnoreCase(aVar3.c) == 0) {
                                                nexproject.getLastPrimaryClip().setColorEffect(next4);
                                                break;
                                            }
                                        }
                                    } else {
                                        nexproject.getLastPrimaryClip().setColorEffect(internalPresetList[i21]);
                                        i7 = i21 + 1;
                                    }
                                }
                                if (!(aVar3.d == null || aVar3.d.compareToIgnoreCase("none") == 0)) {
                                    nexproject.getLastPrimaryClip().getClipEffect().setEffect(aVar3.d);
                                    nexproject.getLastPrimaryClip().getClipEffect().setEffectShowTime(aVar3.g, aVar3.h);
                                }
                                i5 = i6 + 1;
                                if (i5 < size2) {
                                    break;
                                }
                                z = aVar3.e;
                                aVar3 = arrayList.get(i5);
                                int i24 = aVar3.b - aVar3.a;
                                if (i23 + i24 > endTrimTime) {
                                    break;
                                }
                                nexproject.add(nexClip.dup(clone.getClip(i20, true)));
                                int i25 = i5;
                                nexproject.getLastPrimaryClip().setRotateDegree(clone.getClip(i20, true).getRotateDegree());
                                music2 = music;
                                startTrimTime = i23;
                                i22 = i24;
                                i6 = i25;
                            }
                            i21 = 0;
                            nexproject.getLastPrimaryClip().getClipEffect().setEffect(aVar3.d);
                            nexproject.getLastPrimaryClip().getClipEffect().setEffectShowTime(aVar3.g, aVar3.h);
                            i5 = i6 + 1;
                            if (i5 < size2) {
                            }
                        }
                        i20++;
                        if (i20 >= totalClipCount2) {
                            i4 = 1;
                            i20 = 0;
                        } else {
                            i4 = 1;
                        }
                        nexproject2 = nexproject;
                        i19 = i5 + i4;
                        music2 = music;
                    }
                } else {
                    music = music2;
                    i6 = i19;
                }
                i5 = i6;
            }
            i4 = 1;
            i19 = i5 + i4;
            music2 = music;
        }
        nexproject2.setBackgroundMusicPath(music2.string_audio_id);
        nexproject2.setProjectAudioFadeInTime(0);
        nexproject2.setProjectAudioFadeOutTime(0);
        return true;
    }

    private void resolve(boolean z) {
        synchronized (this.m_musicTemplateLock) {
            this.musicTemplates.clear();
            for (nexAssetPackageManager.Item next : nexAssetPackageManager.getAssetPackageManager(mAppContext).getInstalledAssetItems(nexAssetPackageManager.Category.beattemplate)) {
                if (!next.hidden()) {
                    if (z) {
                        nexAssetPackageManager.getAssetPackageManager(mAppContext);
                        if (nexAssetPackageManager.checkExpireAsset(next.packageInfo())) {
                        }
                    }
                    this.musicTemplates.add(new BeatTemplate(next));
                }
            }
        }
    }

    public boolean applyTemplateToProjectById(nexProject nexproject, String str) {
        BeatTemplate beatTemplate = getBeatTemplate(str);
        if (beatTemplate != null && !nexAssetPackageManager.checkExpireAsset(beatTemplate.packageInfo())) {
            return internalApplyTemplateToProjectById(nexproject, str);
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: com.nexstreaming.nexeditorsdk.nexBeatTemplateManager$a} */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03ef, code lost:
        if (r0 >= r4.length) goto L_0x03f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x03f3, code lost:
        r10 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x040d, code lost:
        if (r0 >= r4.length) goto L_0x03f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x049d, code lost:
        if (r9 >= r3) goto L_0x049f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0456  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x023e A[EDGE_INSN: B:175:0x023e->B:85:0x023e ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0468 A[EDGE_INSN: B:185:0x0468->B:149:0x0468 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x028e A[LOOP:2: B:86:0x0288->B:88:0x028e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02eb  */
    public boolean applyTemplateToProjectById2(nexProject nexproject, String str) {
        int i;
        int i2;
        int size;
        int i3;
        int i4;
        Iterator it;
        int size2;
        int i5;
        TemplateMetaData.Music music;
        int i6;
        boolean z;
        int i7;
        int i8;
        nexProject nexproject2 = nexproject;
        String AssetPackageTemplateJsonToString = nexTemplateComposer.AssetPackageTemplateJsonToString(str);
        Level level = Level.Max;
        if (AssetPackageTemplateJsonToString == null) {
            return false;
        }
        TemplateMetaData.Music music2 = (TemplateMetaData.Music) new Gson().fromJson(AssetPackageTemplateJsonToString, TemplateMetaData.Music.class);
        Iterator<TemplateMetaData.EffectEntry> it2 = music2.list_effectEntries.iterator();
        boolean z2 = true;
        int i9 = 10800000;
        int i10 = -1;
        int i11 = 1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (it2.hasNext()) {
            TemplateMetaData.EffectEntry next = it2.next();
            if (i12 > 0 && i9 > next.int_time - i12) {
                i9 = next.int_time - i12;
            }
            i12 = next.int_time;
            i10++;
            if (next.b_source_change || next.int_priority > 0) {
                if (music2.default_effect != null) {
                    if (next.color_filter_id == null) {
                        next.color_filter_id = music2.default_effect.sc_color_effect_id;
                    }
                    if (next.clip_effect_id == null) {
                        next.clip_effect_id = music2.default_effect.sc_clip_effect_id;
                    }
                }
                if (next.int_priority > 0) {
                    i13++;
                } else if (next.internal_clip_id != null) {
                    i14++;
                } else {
                    i11++;
                }
            } else {
                if (music2.default_effect != null) {
                    if (next.color_filter_id == null) {
                        next.color_filter_id = music2.default_effect.color_filter_id;
                    }
                    if (next.clip_effect_id == null) {
                        next.clip_effect_id = music2.default_effect.clip_effect_id;
                    }
                }
                if (!(next.clip_effect_id == null || next.clip_effect_id.compareToIgnoreCase("none") == 0)) {
                    if (next.int_effect_in_duration == 0) {
                        next.int_effect_in_duration = 50;
                    }
                    if (next.int_effect_out_duration == 0) {
                        next.int_effect_out_duration = 50;
                    }
                }
            }
        }
        int totalClipCount = nexproject2.getTotalClipCount(true);
        if (totalClipCount > i11) {
            i2 = totalClipCount - i11;
            if (i2 > i13) {
                i = i2 - i13;
                i2 = i13;
                if (level != Level.Extends) {
                    if (level == Level.Recommend) {
                        i2 = 0;
                    }
                    Log.d(TAG, "sourceClipCount=" + totalClipCount + ", recommend=" + i11 + ", extends=" + i13 + " ,max=" + i10 + ", priorityLevel=" + i2 + ", eventLevel=" + i + ", internal=" + i14);
                    size = music2.list_effectEntries.size();
                    ArrayList arrayList = new ArrayList();
                    i3 = 1;
                    int i15 = 0;
                    while (true) {
                        i4 = size - 1;
                        if (i3 >= i4) {
                            break;
                        }
                        a aVar = new a();
                        aVar.e = music2.list_effectEntries.get(i3).b_source_change;
                        if (music2.list_effectEntries.get(i3).int_priority > 0) {
                            if (i2 == 0) {
                                i3++;
                            } else if (music2.list_effectEntries.get(i3).int_priority <= i2) {
                                aVar.e = true;
                                Log.d(TAG, "num=" + i3 + ", priority=" + music2.list_effectEntries.get(i3).int_priority);
                            }
                        }
                        if (!aVar.e && i > 0 && aVar.f == null) {
                            aVar.e = true;
                            i--;
                            Log.d(TAG, "num=" + i3 + ", event=" + i);
                        }
                        aVar.d = music2.list_effectEntries.get(i3).clip_effect_id;
                        aVar.c = music2.list_effectEntries.get(i3).color_filter_id;
                        aVar.f = music2.list_effectEntries.get(i3).internal_clip_id;
                        if (!aVar.e) {
                            a aVar2 = new a();
                            aVar2.a = i15;
                            aVar2.d = "none";
                            aVar2.c = "none";
                            aVar2.f = aVar.f;
                            aVar.a = music2.list_effectEntries.get(i3).int_time - music2.list_effectEntries.get(i3).int_effect_in_duration;
                            aVar.d = music2.list_effectEntries.get(i3).clip_effect_id;
                            aVar2.b = aVar.a;
                            aVar.b = aVar.a + music2.list_effectEntries.get(i3).int_effect_in_duration + music2.list_effectEntries.get(i3).int_effect_out_duration;
                            i15 = aVar.b;
                            if (aVar2.a < aVar2.b) {
                                arrayList.add(aVar2);
                            }
                        } else {
                            aVar.a = i15;
                            aVar.b = music2.list_effectEntries.get(i3).int_time;
                            if (aVar.a >= aVar.b) {
                                i3++;
                            } else {
                                i15 = aVar.b;
                            }
                        }
                        arrayList.add(aVar);
                        i3++;
                    }
                    a aVar3 = new a();
                    aVar3.e = false;
                    aVar3.a = i15;
                    aVar3.b = music2.list_effectEntries.get(i4).int_time;
                    aVar3.d = music2.list_effectEntries.get(i4).clip_effect_id;
                    aVar3.c = music2.list_effectEntries.get(i4).color_filter_id;
                    arrayList.add(aVar3);
                    Log.d(TAG, "s= ,gap duration=" + i9);
                    it = arrayList.iterator();
                    while (it.hasNext()) {
                        a aVar4 = (a) it.next();
                        Log.d(TAG, "s=" + aVar4.a + ",e=" + aVar4.b + ",c=" + aVar4.e + ",ee=" + aVar4.d + ", sc=" + aVar4.f);
                    }
                    nexProject clone = nexProject.clone(nexproject);
                    nexproject2.allClear(true);
                    int totalClipCount2 = clone.getTotalClipCount(true);
                    nexColorEffect[] internalPresetList = nexColorEffect.getInternalPresetList();
                    size2 = arrayList.size();
                    i5 = 0;
                    int i16 = 0;
                    int i17 = 0;
                    while (i5 < size2) {
                        a aVar5 = (a) arrayList.get(i5);
                        if (aVar5.f != null) {
                            nexproject2.add(new nexClip(aVar5.f));
                        } else {
                            nexproject2.add(nexClip.dup(clone.getClip(i16, z2)));
                        }
                        if (nexproject.getLastPrimaryClip().getClipType() == z2) {
                            nexproject.getLastPrimaryClip().setImageClipDuration(aVar5.b - aVar5.a);
                            nexproject.getLastPrimaryClip().getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FILL);
                            if (aVar5.c != null) {
                                if (aVar5.c.compareToIgnoreCase("rand") != 0) {
                                    Iterator<nexColorEffect> it3 = nexColorEffect.getPresetList().iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            break;
                                        }
                                        nexColorEffect next2 = it3.next();
                                        if (next2.getPresetName().compareToIgnoreCase(aVar5.c) == 0) {
                                            nexproject.getLastPrimaryClip().setColorEffect(next2);
                                            break;
                                        }
                                    }
                                } else {
                                    nexproject.getLastPrimaryClip().setColorEffect(internalPresetList[i17]);
                                    int i18 = i17 + 1;
                                    i17 = i18 >= internalPresetList.length ? 0 : i18;
                                }
                            }
                            if (!(aVar5.d == null || aVar5.d.compareToIgnoreCase("none") == 0)) {
                                nexproject.getLastPrimaryClip().getClipEffect().setEffect(aVar5.d);
                            }
                        } else if (nexproject.getLastPrimaryClip().getClipType() == 4) {
                            int startTrimTime = clone.getClip(i16, true).getVideoClipEdit().getStartTrimTime();
                            int endTrimTime = clone.getClip(i16, true).getVideoClipEdit().getEndTrimTime();
                            i6 = i5;
                            int i19 = aVar5.b - aVar5.a;
                            if (startTrimTime + i19 > endTrimTime) {
                                music = music2;
                            } else {
                                boolean z3 = false;
                                while (true) {
                                    music = music2;
                                    int i20 = i19 + startTrimTime;
                                    nexproject.getLastPrimaryClip().getVideoClipEdit().setTrim(startTrimTime, i20);
                                    nexproject.getLastPrimaryClip().getCrop().randomizeStartEndPosition(false, nexCrop.CropMode.FILL);
                                    if (z3) {
                                        nexproject.getLastPrimaryClip().setColorEffect(internalPresetList[i17]);
                                        i8 = i17 + 1;
                                    } else {
                                        if (aVar5.c != null) {
                                            if (aVar5.c.compareToIgnoreCase("rand") != 0) {
                                                Iterator<nexColorEffect> it4 = nexColorEffect.getPresetList().iterator();
                                                while (true) {
                                                    if (!it4.hasNext()) {
                                                        break;
                                                    }
                                                    nexColorEffect next3 = it4.next();
                                                    if (next3.getPresetName().compareToIgnoreCase(aVar5.c) == 0) {
                                                        nexproject.getLastPrimaryClip().setColorEffect(next3);
                                                        break;
                                                    }
                                                }
                                            } else {
                                                nexproject.getLastPrimaryClip().setColorEffect(internalPresetList[i17]);
                                                i8 = i17 + 1;
                                            }
                                        }
                                        if (!(aVar5.d == null || aVar5.d.compareToIgnoreCase("none") == 0)) {
                                            nexproject.getLastPrimaryClip().getClipEffect().setEffect(aVar5.d);
                                        }
                                        i7 = i6 + 1;
                                        if (i7 < size2) {
                                            break;
                                        }
                                        z3 = aVar5.e;
                                        aVar5 = arrayList.get(i7);
                                        int i21 = aVar5.b - aVar5.a;
                                        if (i20 + i21 > endTrimTime) {
                                            break;
                                        }
                                        nexproject.add(nexClip.dup(clone.getClip(i16, true)));
                                        startTrimTime = i20;
                                        i19 = i21;
                                        i6 = i7;
                                        music2 = music;
                                    }
                                    i17 = 0;
                                    nexproject.getLastPrimaryClip().getClipEffect().setEffect(aVar5.d);
                                    i7 = i6 + 1;
                                    if (i7 < size2) {
                                    }
                                }
                                i16++;
                                if (i16 >= totalClipCount2) {
                                    nexproject2 = nexproject;
                                    i6 = i7;
                                    z = true;
                                    i16 = 0;
                                    i5 = i6 + 1;
                                    z2 = z;
                                    music2 = music;
                                } else {
                                    nexproject2 = nexproject;
                                    i6 = i7;
                                }
                            }
                            z = true;
                            i5 = i6 + 1;
                            z2 = z;
                            music2 = music;
                        }
                        music = music2;
                        i6 = i5;
                        if (aVar5.e) {
                            if (aVar5.f == null) {
                                i16++;
                            }
                        }
                        z = true;
                        i5 = i6 + 1;
                        z2 = z;
                        music2 = music;
                    }
                    boolean z4 = z2;
                    TemplateMetaData.Music music3 = music2;
                    boolean z5 = z4;
                    nexproject2.setBackgroundMusicPath(music3.string_audio_id);
                    nexproject2.setProjectAudioFadeInTime(0);
                    nexproject2.setProjectAudioFadeOutTime(0);
                    return z5;
                }
                i = 0;
                Log.d(TAG, "sourceClipCount=" + totalClipCount + ", recommend=" + i11 + ", extends=" + i13 + " ,max=" + i10 + ", priorityLevel=" + i2 + ", eventLevel=" + i + ", internal=" + i14);
                size = music2.list_effectEntries.size();
                ArrayList arrayList2 = new ArrayList();
                i3 = 1;
                int i152 = 0;
                while (true) {
                    i4 = size - 1;
                    if (i3 >= i4) {
                    }
                    i3++;
                }
                a aVar32 = new a();
                aVar32.e = false;
                aVar32.a = i152;
                aVar32.b = music2.list_effectEntries.get(i4).int_time;
                aVar32.d = music2.list_effectEntries.get(i4).clip_effect_id;
                aVar32.c = music2.list_effectEntries.get(i4).color_filter_id;
                arrayList2.add(aVar32);
                Log.d(TAG, "s= ,gap duration=" + i9);
                it = arrayList2.iterator();
                while (it.hasNext()) {
                }
                nexProject clone2 = nexProject.clone(nexproject);
                nexproject2.allClear(true);
                int totalClipCount22 = clone2.getTotalClipCount(true);
                nexColorEffect[] internalPresetList2 = nexColorEffect.getInternalPresetList();
                size2 = arrayList2.size();
                i5 = 0;
                int i162 = 0;
                int i172 = 0;
                while (i5 < size2) {
                }
                boolean z42 = z2;
                TemplateMetaData.Music music32 = music2;
                boolean z52 = z42;
                nexproject2.setBackgroundMusicPath(music32.string_audio_id);
                nexproject2.setProjectAudioFadeInTime(0);
                nexproject2.setProjectAudioFadeOutTime(0);
                return z52;
            }
        } else {
            i2 = 0;
        }
        i = 0;
        if (level != Level.Extends) {
        }
        i = 0;
        Log.d(TAG, "sourceClipCount=" + totalClipCount + ", recommend=" + i11 + ", extends=" + i13 + " ,max=" + i10 + ", priorityLevel=" + i2 + ", eventLevel=" + i + ", internal=" + i14);
        size = music2.list_effectEntries.size();
        ArrayList arrayList22 = new ArrayList();
        i3 = 1;
        int i1522 = 0;
        while (true) {
            i4 = size - 1;
            if (i3 >= i4) {
            }
            i3++;
        }
        a aVar322 = new a();
        aVar322.e = false;
        aVar322.a = i1522;
        aVar322.b = music2.list_effectEntries.get(i4).int_time;
        aVar322.d = music2.list_effectEntries.get(i4).clip_effect_id;
        aVar322.c = music2.list_effectEntries.get(i4).color_filter_id;
        arrayList22.add(aVar322);
        Log.d(TAG, "s= ,gap duration=" + i9);
        it = arrayList22.iterator();
        while (it.hasNext()) {
        }
        nexProject clone22 = nexProject.clone(nexproject);
        nexproject2.allClear(true);
        int totalClipCount222 = clone22.getTotalClipCount(true);
        nexColorEffect[] internalPresetList22 = nexColorEffect.getInternalPresetList();
        size2 = arrayList22.size();
        i5 = 0;
        int i1622 = 0;
        int i1722 = 0;
        while (i5 < size2) {
        }
        boolean z422 = z2;
        TemplateMetaData.Music music322 = music2;
        boolean z522 = z422;
        nexproject2.setBackgroundMusicPath(music322.string_audio_id);
        nexproject2.setProjectAudioFadeInTime(0);
        nexproject2.setProjectAudioFadeOutTime(0);
        return z522;
    }

    public BeatTemplate getBeatTemplate(String str) {
        synchronized (this.m_musicTemplateLock) {
            for (BeatTemplate next : this.musicTemplates) {
                if (next.id().compareTo(str) == 0) {
                    return next;
                }
            }
            return null;
        }
    }

    public List<BeatTemplate> getBeatTemplates() {
        List<BeatTemplate> list;
        synchronized (this.m_musicTemplateLock) {
            if (this.externalView_musicTemplates == null) {
                this.externalView_musicTemplates = Collections.unmodifiableList(this.musicTemplates);
            }
            list = this.externalView_musicTemplates;
        }
        return list;
    }

    public void loadTemplate() {
        resolve(false);
    }

    public void loadTemplate(boolean z) {
        resolve(z);
    }

    /* access modifiers changed from: package-private */
    public boolean updateBeatTemplate(boolean z, nexAssetPackageManager.Item item) {
        synchronized (this.m_musicTemplateLock) {
            Log.d(TAG, "updateMusicTemplate(" + z + "," + item.packageInfo().assetIdx() + ")");
            if (!z) {
                Iterator<BeatTemplate> it = this.musicTemplates.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    BeatTemplate next = it.next();
                    if (next.id().compareTo(item.id()) == 0) {
                        this.musicTemplates.remove(next);
                        break;
                    }
                }
            } else if (!item.hidden()) {
                this.musicTemplates.add(new BeatTemplate(item));
            }
        }
        return false;
    }
}
