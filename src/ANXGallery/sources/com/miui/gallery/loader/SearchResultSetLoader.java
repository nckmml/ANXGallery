package com.miui.gallery.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import com.miui.gallery.loader.CloudSetLoader;
import com.miui.gallery.model.CursorDataSet;
import com.miui.gallery.provider.GalleryContract;
import java.util.Arrays;
import java.util.List;

public class SearchResultSetLoader extends CloudSetLoader {
    private String[] mIds;

    private static class SearchResultDataSet extends CloudSetLoader.UnfoldBurstCloudDataSet {
        private List<String> mIds;
        SparseIntArray mPositionMap;

        public SearchResultDataSet(String[] strArr, Cursor cursor, int i) {
            super(cursor, i, 0, -1, "");
            if (strArr != null) {
                this.mIds = Arrays.asList(strArr);
                this.mPositionMap = new SparseIntArray(this.mIds.size());
                if (isValidate()) {
                    for (int i2 = 0; i2 < cursor.getCount(); i2++) {
                        cursor.moveToPosition(i2);
                        this.mPositionMap.put(this.mIds.indexOf(cursor.getString(0)), i2);
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public boolean foldBurst() {
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean moveToPosition(int i) {
            SparseIntArray sparseIntArray = this.mPositionMap;
            return sparseIntArray != null && i >= 0 && i < sparseIntArray.size() && isValidate(this.mPositionMap.valueAt(i)) && this.mCursor.moveToPosition(this.mPositionMap.valueAt(i));
        }
    }

    public SearchResultSetLoader(Context context, Uri uri, Bundle bundle) {
        super(context, GalleryContract.Media.URI, bundle);
        if (bundle != null) {
            this.mIds = bundle.getStringArray("photo_selection_args");
        }
        this.mUnfoldBurst = true;
    }

    /* access modifiers changed from: protected */
    public String getOrder() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSelection() {
        Object[] objArr = new Object[4];
        objArr[0] = "_id";
        String[] strArr = this.mIds;
        objArr[1] = (strArr == null || strArr.length <= 0) ? "" : TextUtils.join(",", strArr);
        objArr[2] = "localGroupId";
        objArr[3] = -1000L;
        return String.format("%s in (%s) AND %s != %s", objArr);
    }

    /* access modifiers changed from: protected */
    public String[] getSelectionArgs() {
        return null;
    }

    public String getTAG() {
        return "SearchResultSetLoader";
    }

    /* access modifiers changed from: protected */
    public CursorDataSet wrapDataSet(Cursor cursor) {
        return new SearchResultDataSet(this.mIds, cursor, this.mInitPos);
    }
}
