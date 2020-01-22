package com.miui.gallery.provider.cache;

import android.database.CursorWindow;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.miui.gallery.provider.cache.CacheItem;
import com.miui.gallery.provider.cache.Filter;
import java.util.Comparator;
import java.util.Map;

public class SearchIconItem implements Parcelable, CacheItem {
    /* access modifiers changed from: private */
    public static final Map<String, Integer> COLUMN_MAP = new ArrayMap(PROJECTION.length);
    /* access modifiers changed from: private */
    public static final CacheItem.ColumnMapper COLUMN_MAPPER = new CacheItem.ColumnMapper() {
        public int getIndex(String str) {
            Integer num = (Integer) SearchIconItem.COLUMN_MAP.get(str);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }
    };
    public static final Parcelable.Creator<SearchIconItem> CREATOR = new Parcelable.Creator<SearchIconItem>() {
        public SearchIconItem createFromParcel(Parcel parcel) {
            return new SearchIconItem(parcel);
        }

        public SearchIconItem[] newArray(int i) {
            return new SearchIconItem[i];
        }
    };
    protected static final String[] PROJECTION = {"icon_uri", "file_path", "download_uri", "decode_region_orientation", "decode_region_x", "decode_region_y", "decode_region_w", "decode_region_h"};
    public Float decodeRegionH;
    public int decodeRegionOrientation;
    public Float decodeRegionW;
    public Float decodeRegionX;
    public Float decodeRegionY;
    public String downloadUri;
    public String filePath;
    public String iconUri;
    Uri notifyUri;

    public static class QueryFactory implements CacheItem.QueryFactory<SearchIconItem> {
        public Comparator<SearchIconItem> getComparator(int i, boolean z) {
            return null;
        }

        public Filter.CompareFilter<SearchIconItem> getFilter(int i, Filter.Comparator comparator, String str) {
            return null;
        }

        public CacheItem.ColumnMapper getMapper() {
            return SearchIconItem.COLUMN_MAPPER;
        }

        public CacheItem.Merger<SearchIconItem> getMerger(int i) {
            return null;
        }
    }

    static {
        COLUMN_MAP.put("icon_uri", 0);
        COLUMN_MAP.put("file_path", 1);
        COLUMN_MAP.put("download_uri", 2);
        COLUMN_MAP.put("decode_region_orientation", 3);
        COLUMN_MAP.put("decode_region_x", 4);
        COLUMN_MAP.put("decode_region_y", 5);
        COLUMN_MAP.put("decode_region_w", 6);
        COLUMN_MAP.put("decode_region_h", 7);
    }

    public SearchIconItem() {
    }

    protected SearchIconItem(Parcel parcel) {
        this.iconUri = parcel.readString();
        this.filePath = parcel.readString();
        this.downloadUri = parcel.readString();
        this.decodeRegionOrientation = parcel.readInt();
        this.decodeRegionX = (Float) parcel.readValue(Float.class.getClassLoader());
        this.decodeRegionY = (Float) parcel.readValue(Float.class.getClassLoader());
        this.decodeRegionW = (Float) parcel.readValue(Float.class.getClassLoader());
        this.decodeRegionH = (Float) parcel.readValue(Float.class.getClassLoader());
        this.notifyUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public boolean fillWindow(CursorWindow cursorWindow, int i, int i2, int i3) {
        return false;
    }

    public Object get(int i, boolean z) {
        if (i == 0) {
            return this.iconUri;
        }
        if (i == 1) {
            return this.filePath;
        }
        if (i == 2) {
            return this.downloadUri;
        }
        if (i == 3) {
            return Integer.valueOf(this.decodeRegionOrientation);
        }
        if (i == 4) {
            return this.decodeRegionX;
        }
        if (i == 5) {
            return this.decodeRegionY;
        }
        if (i == 6) {
            return this.decodeRegionW;
        }
        if (i == 7) {
            return this.decodeRegionH;
        }
        throw new IllegalArgumentException(" not recognized column.");
    }

    public int getType(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return 3;
        }
        if (i == 3) {
            return 1;
        }
        if (i == 4 || i == 5 || i == 6 || i == 7) {
            return 2;
        }
        throw new IllegalArgumentException(" not recognized column.");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.iconUri);
        parcel.writeString(this.filePath);
        parcel.writeString(this.downloadUri);
        parcel.writeInt(this.decodeRegionOrientation);
        parcel.writeValue(this.decodeRegionX);
        parcel.writeValue(this.decodeRegionY);
        parcel.writeValue(this.decodeRegionW);
        parcel.writeValue(this.decodeRegionH);
        parcel.writeParcelable(this.notifyUri, i);
    }
}
