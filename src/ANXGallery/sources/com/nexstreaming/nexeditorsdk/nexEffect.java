package com.nexstreaming.nexeditorsdk;

import android.util.Log;
import com.nexstreaming.app.common.nexasset.assetpackage.ItemParameterType;
import com.nexstreaming.app.common.nexasset.assetpackage.f;
import com.nexstreaming.app.common.nexasset.assetpackage.g;
import com.nexstreaming.app.common.nexasset.assetpackage.h;
import com.nexstreaming.app.common.nexasset.assetpackage.i;
import com.nexstreaming.app.common.util.c;
import com.nexstreaming.kminternal.kinemaster.config.a;
import com.nexstreaming.nexeditorsdk.nexAssetPackageManager;
import com.nexstreaming.nexeditorsdk.nexEffectOptions;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.keyczar.Keyczar;
import org.xmlpull.v1.XmlPullParserException;

public abstract class nexEffect {
    public static final int kEFFECT_CLIP_AUTO = 1;
    public static final int kEFFECT_CLIP_USER = 2;
    public static final int kEFFECT_NONE = 0;
    public static final int kEFFECT_OVERLAY_FILTER = 5;
    public static final int kEFFECT_TRANSITION_AUTO = 3;
    public static final int kEFFECT_TRANSITION_USER = 4;
    private static final int kMaxStringTrackCount = 8;
    nexAssetPackageManager.ItemMethodType itemMethodType = nexAssetPackageManager.ItemMethodType.ItemExtra;
    String mAutoID;
    int mDuration;
    String mID;
    boolean mIsResolveOptions = false;
    String mName;
    private b mObserver;
    boolean mOptionsUpdate;
    private String[] mTitles = null;
    int mType = 0;
    boolean mUpdated;
    HashMap<String, String> m_effectOptions;

    /* JADX WARNING: Removed duplicated region for block: B:103:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001d  */
    private static void encodeEffectOptions(StringBuilder sb, nexEffectOptions nexeffectoptions) {
        h hVar;
        try {
            hVar = i.a(a.a().b(), nexeffectoptions.getEffectID());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            hVar = null;
            if (hVar != null) {
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            hVar = null;
            if (hVar != null) {
            }
        }
        if (hVar != null) {
            try {
                boolean z = true;
                for (g next : hVar.a()) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append('&');
                    }
                    if (next.a() == ItemParameterType.TEXT) {
                        for (nexEffectOptions.TextOpt next2 : nexeffectoptions.getTextOptions()) {
                            if (next.e().compareTo(next2.getId()) == 0 && next2.getText() != null) {
                                sb.append(URLEncoder.encode(next2.getId(), Keyczar.DEFAULT_ENCODING));
                                sb.append("=");
                                sb.append(URLEncoder.encode(next2.getText(), Keyczar.DEFAULT_ENCODING));
                            }
                        }
                    } else {
                        if (next.a() != ItemParameterType.RGBA) {
                            if (next.a() != ItemParameterType.RGB) {
                                if (next.a() == ItemParameterType.CHOICE) {
                                    if (nexeffectoptions != null) {
                                        for (nexEffectOptions.SelectOpt next3 : nexeffectoptions.getSelectOptions()) {
                                            if (next.e().compareTo(next3.getId()) == 0) {
                                                sb.append(URLEncoder.encode(next3.getId(), Keyczar.DEFAULT_ENCODING));
                                                sb.append("=");
                                                sb.append(URLEncoder.encode(next3.getSelectValue(), Keyczar.DEFAULT_ENCODING));
                                            }
                                        }
                                    }
                                } else if (next.a() == ItemParameterType.RANGE) {
                                    if (nexeffectoptions != null) {
                                        for (nexEffectOptions.RangeOpt next4 : nexeffectoptions.getRangeOptions()) {
                                            if (next.e().compareTo(next4.getId()) == 0) {
                                                sb.append(URLEncoder.encode(next4.getId(), Keyczar.DEFAULT_ENCODING));
                                                sb.append("=");
                                                sb.append(URLEncoder.encode("" + next4.getValue(), Keyczar.DEFAULT_ENCODING));
                                            }
                                        }
                                    }
                                } else if (next.a() == ItemParameterType.SWITCH && nexeffectoptions != null) {
                                    for (nexEffectOptions.SwitchOpt next5 : nexeffectoptions.getSwitchOptions()) {
                                        if (next.e().compareTo(next5.getId()) == 0) {
                                            sb.append(URLEncoder.encode(next5.getId(), Keyczar.DEFAULT_ENCODING));
                                            sb.append("=");
                                            if (next5.getValue()) {
                                                sb.append(URLEncoder.encode(next.d(), Keyczar.DEFAULT_ENCODING));
                                            } else {
                                                sb.append(URLEncoder.encode(next.c(), Keyczar.DEFAULT_ENCODING));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (nexeffectoptions != null) {
                            for (nexEffectOptions.ColorOpt next6 : nexeffectoptions.getColorOptions()) {
                                if (next.e().compareTo(next6.getId()) == 0) {
                                    sb.append(URLEncoder.encode(next6.getId(), Keyczar.DEFAULT_ENCODING));
                                    sb.append("=");
                                    sb.append(URLEncoder.encode(c.a(next6.getARGBformat()), Keyczar.DEFAULT_ENCODING));
                                }
                            }
                        }
                    }
                }
            } catch (UnsupportedEncodingException e3) {
                throw new IllegalStateException(e3);
            }
        }
    }

    public static String getTitleOptions(nexEffectOptions nexeffectoptions) {
        StringBuilder sb = new StringBuilder();
        if (nexeffectoptions.mEffectType == 5) {
            encodeEffectOptions(sb, nexeffectoptions);
        } else {
            sb.append(0);
            sb.append(',');
            sb.append(10000);
            sb.append('?');
            if (nexeffectoptions.mEffectType == 4 || nexeffectoptions.mEffectType == 3) {
                encodeEffectOptions(sb, nexeffectoptions);
            }
            sb.append('?');
            if (nexeffectoptions.mEffectType == 2 || nexeffectoptions.mEffectType == 1) {
                encodeEffectOptions(sb, nexeffectoptions);
            }
        }
        return sb.toString();
    }

    private boolean isAllEmptyTitle() {
        if (this.mTitles == null) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            if (this.mTitles[i] != null) {
                return false;
            }
        }
        return true;
    }

    private void resetOptions() {
        HashMap<String, String> hashMap = this.m_effectOptions;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public int getDuration() {
        return this.mDuration;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getEffectOptions(String str) {
        if (this.m_effectOptions == null) {
            this.m_effectOptions = new HashMap<>();
        }
        int i = this.mType;
        if (i == 1 || i == 3) {
            this.m_effectOptions.clear();
        }
        if (str == null && isAllEmptyTitle()) {
            return this.m_effectOptions;
        }
        h hVar = null;
        try {
            hVar = i.a(a.a().b(), getId());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (hVar != null) {
            int i2 = 0;
            for (g next : hVar.a()) {
                if (next.a() == ItemParameterType.TEXT) {
                    if (str != null) {
                        this.m_effectOptions.put(next.e(), str);
                    } else {
                        if (getTitle(i2) != null) {
                            this.m_effectOptions.put(next.e(), getTitle(i2));
                        }
                        i2++;
                    }
                }
            }
        }
        return this.m_effectOptions;
    }

    public String getId() {
        int i = this.mType;
        if (i == 0) {
            return "none";
        }
        if (i == 1 || i == 3) {
            String str = this.mAutoID;
            if (str != null) {
                return str;
            }
            Log.d("nexEffect", "mType=" + this.mType + " mAutoID=null");
            return "none";
        }
        String str2 = this.mID;
        if (str2 != null) {
            return str2;
        }
        Log.d("nexEffect", "mType=" + this.mType + " mID=null");
        return "none";
    }

    public nexAssetPackageManager.ItemMethodType getMethodType() {
        if (this.itemMethodType == nexAssetPackageManager.ItemMethodType.ItemExtra) {
            if (this.mID.compareToIgnoreCase("none") == 0) {
                this.itemMethodType = nexAssetPackageManager.ItemMethodType.ItemKedl;
            } else {
                f c = com.nexstreaming.app.common.nexasset.assetpackage.c.a().c(this.mID);
                if (c != null) {
                    this.itemMethodType = nexAssetPackageManager.getMethodType(c.getType());
                }
            }
        }
        return this.itemMethodType;
    }

    public String getTitle(int i) {
        String[] strArr = this.mTitles;
        if (strArr != null && i < 8) {
            return strArr[i];
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getTitleCount() {
        int i = 0;
        if (this.mTitles == null) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            String[] strArr = this.mTitles;
            if (i >= strArr.length) {
                return i2;
            }
            if (strArr[i] != null && !strArr[i].isEmpty()) {
                i2++;
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public String[] getTitles() {
        return this.mTitles;
    }

    public int getType() {
        return this.mType;
    }

    /* access modifiers changed from: package-private */
    public boolean isValidId(String str) {
        return true;
    }

    public void setDuration(int i) {
        if (this.mDuration != i) {
            this.mUpdated = true;
            setModified(false);
        }
        this.mDuration = i;
    }

    /* access modifiers changed from: package-private */
    public boolean setEffect(String str, int i) {
        this.mType = i;
        String str2 = this.mID;
        if ((str2 != null && str.compareTo(str2) == 0) || !isValidId(str)) {
            return false;
        }
        this.mUpdated = true;
        this.mID = str;
        this.mOptionsUpdate = false;
        resetOptions();
        this.mIsResolveOptions = false;
        return true;
    }

    public void setEffectNone() {
        if (this.mType != 0) {
            this.mUpdated = true;
            this.mOptionsUpdate = false;
            setModified(false);
            resetOptions();
            this.mIsResolveOptions = false;
        }
        this.mType = 0;
    }

    /* access modifiers changed from: package-private */
    public void setModified(boolean z) {
        b bVar = this.mObserver;
        if (bVar != null) {
            bVar.updateTimeLine(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void setObserver(b bVar) {
        this.mObserver = bVar;
    }

    public void setTitle(int i, String str) {
        if (this.mTitles == null) {
            this.mTitles = new String[8];
        }
        if (i < 8) {
            this.mTitles[i] = str;
        }
    }

    public boolean updateEffectOptions(nexEffectOptions nexeffectoptions, boolean z) {
        if (this.m_effectOptions == null) {
            this.m_effectOptions = new HashMap<>();
        }
        if (nexeffectoptions.mEffectID.compareTo(this.mID) != 0) {
            return false;
        }
        this.mOptionsUpdate = true;
        if (z) {
            this.m_effectOptions.clear();
        }
        List<nexEffectOptions.TextOpt> textOptions = nexeffectoptions.getTextOptions();
        if (textOptions != null) {
            int i = 0;
            for (nexEffectOptions.TextOpt next : textOptions) {
                if (z) {
                    if (next.getText() != null) {
                        setTitle(i, next.getText());
                        this.m_effectOptions.put(next.getId(), getTitle(i));
                    }
                } else if (getTitle(i) != null) {
                    next.setText(getTitle(i));
                }
                i++;
            }
        }
        List<nexEffectOptions.ColorOpt> colorOptions = nexeffectoptions.getColorOptions();
        if (colorOptions != null) {
            for (nexEffectOptions.ColorOpt next2 : colorOptions) {
                if (!z) {
                    String str = this.m_effectOptions.get(next2.getId());
                    if (str != null) {
                        next2.argb_color = c.a(str);
                    }
                } else if (next2.default_argb_color != next2.argb_color) {
                    this.m_effectOptions.put(next2.getId(), c.a(next2.getARGBformat()));
                }
            }
        }
        List<nexEffectOptions.SelectOpt> selectOptions = nexeffectoptions.getSelectOptions();
        if (selectOptions != null) {
            for (nexEffectOptions.SelectOpt next3 : selectOptions) {
                if (!z) {
                    String str2 = this.m_effectOptions.get(next3.getId());
                    if (str2 != null) {
                        next3.setValue(str2);
                    }
                } else if (next3.default_select_index != next3.select_index) {
                    this.m_effectOptions.put(next3.getId(), next3.getSelectValue());
                }
            }
        }
        List<nexEffectOptions.RangeOpt> rangeOptions = nexeffectoptions.getRangeOptions();
        if (rangeOptions != null) {
            for (nexEffectOptions.RangeOpt next4 : rangeOptions) {
                if (!z) {
                    next4.setValue(Integer.parseInt(this.m_effectOptions.get(next4.getId())));
                } else if (next4.default_value != next4.mValue) {
                    this.m_effectOptions.put(next4.getId(), "" + next4.getValue());
                }
            }
        }
        List<nexEffectOptions.SwitchOpt> switchOptions = nexeffectoptions.getSwitchOptions();
        if (switchOptions != null) {
            for (nexEffectOptions.SwitchOpt next5 : switchOptions) {
                if (z) {
                    if (next5.default_on != next5.on) {
                        if (next5.on) {
                            this.m_effectOptions.put(next5.getId(), "on");
                        } else {
                            this.m_effectOptions.put(next5.getId(), "off");
                        }
                    }
                } else if (this.m_effectOptions.get(next5.getId()).compareTo("on") == 0) {
                    next5.setValue(true);
                } else {
                    next5.setValue(false);
                }
            }
        }
        this.mIsResolveOptions = true;
        return true;
    }
}
