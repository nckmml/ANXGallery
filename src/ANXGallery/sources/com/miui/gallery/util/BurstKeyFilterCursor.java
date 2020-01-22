package com.miui.gallery.util;

import android.database.Cursor;
import android.text.TextUtils;
import java.io.File;

public abstract class BurstKeyFilterCursor extends BurstFilterCursor {
    public BurstKeyFilterCursor(Cursor cursor) {
        super(cursor);
    }

    private String wrapKey(String str) {
        String trim = str == null ? null : str.trim();
        if (TextUtils.isEmpty(trim)) {
            return "";
        }
        if (trim.endsWith(File.separator)) {
            trim = trim.substring(0, trim.length() - 1);
        }
        int lastIndexOf = trim.lastIndexOf(".");
        return lastIndexOf > -1 ? trim.substring(trim.lastIndexOf(File.separator) + 1, lastIndexOf) : trim.substring(trim.lastIndexOf(File.separator) + 1);
    }

    /* access modifiers changed from: protected */
    public abstract int getBurstKeyIndex();

    /* access modifiers changed from: protected */
    public void initResultList() {
        Cursor wrappedCursor = getWrappedCursor();
        if (getWrappedCursor() != null) {
            int burstKeyIndex = getBurstKeyIndex();
            String str = null;
            for (int i = 0; i < wrappedCursor.getCount(); i++) {
                if (wrappedCursor.moveToPosition(i)) {
                    String wrapKey = wrapKey(wrappedCursor.getString(burstKeyIndex));
                    if (wrapKey.contains("_BURST")) {
                        String str2 = wrapKey.split("_BURST")[0];
                        if (!TextUtils.equals(str2, str)) {
                            this.mResultPos.add(Integer.valueOf(i));
                            str = str2;
                        }
                    } else {
                        this.mResultPos.add(Integer.valueOf(i));
                        str = null;
                    }
                }
            }
        }
    }

    public boolean isBurstPosition(int i) {
        if (i < 0 || i > this.mResultPos.size()) {
            return false;
        }
        moveToPosition(i);
        String string = getString(getBurstKeyIndex());
        return string != null && string.contains("_BURST");
    }
}
