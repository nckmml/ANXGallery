package com.miui.gallery.provider.cache;

import android.text.TextUtils;
import com.miui.gallery.util.Numbers;
import java.util.Arrays;

public class CacheUtils {
    public static boolean columnEquals(CacheItem cacheItem, CacheItem cacheItem2, int i) {
        if (cacheItem == cacheItem2) {
            return true;
        }
        if (cacheItem == null || cacheItem2 == null) {
            return false;
        }
        int type = cacheItem.getType(i);
        if (type == 0) {
            return cacheItem2.getType(i) == 0;
        }
        if (type == 1) {
            Object obj = cacheItem.get(i, true);
            Object obj2 = cacheItem2.get(i, true);
            if (obj instanceof Long) {
                return Numbers.equals((Long) obj, obj2 instanceof Long ? ((Long) obj2).longValue() : (long) ((Integer) obj2).intValue());
            }
            return Numbers.equals((Integer) obj, obj2 instanceof Long ? ((Long) obj2).longValue() : (long) ((Integer) obj2).intValue());
        } else if (type == 2) {
            return Numbers.equals((Float) cacheItem.get(i, true), (Float) cacheItem2.get(i, true));
        } else {
            if (type == 3) {
                return TextUtils.equals((String) cacheItem.get(i, true), (String) cacheItem2.get(i, true));
            }
            if (type != 4) {
                return false;
            }
            return Arrays.deepEquals((Byte[]) cacheItem.get(i, true), (Byte[]) cacheItem2.get(i, true));
        }
    }
}
