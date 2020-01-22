package com.miui.gallery.scanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.provider.RecentDiscoveryMediaManager;
import com.miui.gallery.util.Log;
import com.miui.gallery.util.MiscUtil;
import java.io.Closeable;
import java.util.ArrayList;

public class CloudMediaBulkInserter extends MediaBulkInserter {
    private static final String[] PROJECTION = {"_id", "localGroupId", "localFile", "dateModified"};
    private ArrayList<String> mWhereArgs = new ArrayList<>(this.mBulkCount);
    private StringBuilder mWhereClause = new StringBuilder();

    public CloudMediaBulkInserter(boolean z) {
        super(GalleryContract.Cloud.CLOUD_URI.buildUpon().appendQueryParameter("URI_PARAM_REQUEST_SYNC", String.valueOf(z)).build());
    }

    public void flush(Context context) {
        super.flush(context);
        int size = this.mWhereArgs.size();
        if (size > 0) {
            String[] strArr = (String[]) this.mWhereArgs.toArray(new String[size]);
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(GalleryContract.Cloud.CLOUD_URI, PROJECTION, "localFile IN (" + this.mWhereClause.toString() + ")", strArr, "dateModified DESC ");
                if (cursor != null && cursor.getCount() > 0) {
                    RecentDiscoveryMediaManager.RecentMediaEntry[] recentMediaEntryArr = new RecentDiscoveryMediaManager.RecentMediaEntry[cursor.getCount()];
                    cursor.moveToFirst();
                    int i = 0;
                    while (!cursor.isAfterLast()) {
                        recentMediaEntryArr[i] = new RecentDiscoveryMediaManager.RecentMediaEntry(cursor.getLong(1), cursor.getLong(0), cursor.getString(2), cursor.getLong(3));
                        cursor.moveToNext();
                        i++;
                    }
                    RecentDiscoveryMediaManager.insertToRecent(context, recentMediaEntryArr);
                }
            } catch (Exception e) {
                Log.e("CloudMediaBulkInserter", (Throwable) e);
            } catch (Throwable th) {
                MiscUtil.closeSilently((Closeable) null);
                throw th;
            }
            MiscUtil.closeSilently(cursor);
            this.mWhereClause.setLength(0);
            this.mWhereArgs.clear();
        }
    }

    public void insert(Context context, ContentValues contentValues) {
        if (contentValues != null) {
            String asString = contentValues.getAsString("localFile");
            if (!TextUtils.isEmpty(asString)) {
                if (this.mWhereClause.length() != 0) {
                    this.mWhereClause.append(",");
                }
                this.mWhereClause.append("?");
                this.mWhereArgs.add(asString);
            }
        }
        super.insert(context, contentValues);
    }
}
