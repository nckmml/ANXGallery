package com.miui.gallery.editor.photo.app.filter.skytransfer;

import com.miui.gallery.editor.photo.core.imports.filter.render.IFilterEmptyValidate;
import java.io.File;

public class SkyTransferItem implements IFilterEmptyValidate {
    public boolean mCanAdjustMoment;
    private String mName;
    private int mProgress;

    public SkyTransferItem(String str, int i) {
        this.mName = str;
        this.mProgress = i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    private static final int getCateForSky(String str) {
        char c;
        switch (str.hashCode()) {
            case -1278996498:
                if (str.equals("filter_sky_dusk")) {
                    c = 4;
                    break;
                }
            case -1278915886:
                if (str.equals("filter_sky_glow")) {
                    c = 3;
                    break;
                }
            case -980337550:
                if (str.equals("filter_sky_sunny")) {
                    c = 0;
                    break;
                }
            case -792035095:
                if (str.equals("filter_sky_cloudy")) {
                    c = 1;
                    break;
                }
            case -325688677:
                if (str.equals("filter_sky_sunset")) {
                    c = 5;
                    break;
                }
            case 1323696721:
                if (str.equals("filter_sky_rainbow")) {
                    c = 2;
                    break;
                }
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return 3;
        }
        if (c == 1) {
            return 5;
        }
        if (c == 2) {
            return 4;
        }
        if (c == 3) {
            return 1;
        }
        if (c == 4) {
            return 2;
        }
        if (c != 5) {
        }
        return 0;
    }

    public int getCate() {
        return getCateForSky(this.mName);
    }

    public String getPath() {
        return SkyResourceDownloadHelper.getInstance().getSkyResPathV1() + File.separator + this.mName;
    }

    public float getProgress() {
        return ((float) this.mProgress) / 100.0f;
    }

    public boolean isEmpty() {
        return this.mName == null;
    }

    public String toString() {
        return this.mName + "-" + this.mProgress;
    }
}
