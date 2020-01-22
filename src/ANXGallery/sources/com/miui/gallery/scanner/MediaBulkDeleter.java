package com.miui.gallery.scanner;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import com.miui.gallery.util.Log;
import java.util.ArrayList;

public class MediaBulkDeleter {
    private static int BULK_MAX = 100;
    private String mColumnName;
    private Uri mUri;
    private ArrayList<String> mWhereArgs = new ArrayList<>(BULK_MAX);
    private StringBuilder mWhereClause = new StringBuilder();

    public MediaBulkDeleter(Uri uri, String str) {
        this.mUri = uri;
        this.mColumnName = str;
    }

    public void delete(Context context, long j) {
        if (this.mWhereClause.length() != 0) {
            this.mWhereClause.append(",");
        }
        this.mWhereClause.append("?");
        this.mWhereArgs.add(String.valueOf(j));
        if (this.mWhereArgs.size() > BULK_MAX) {
            flush(context);
        }
    }

    public void flush(Context context) {
        int size = this.mWhereArgs.size();
        if (size > 0) {
            ArrayList<String> arrayList = this.mWhereArgs;
            ContentResolver contentResolver = context.getContentResolver();
            Uri uri = this.mUri;
            Log.i("MediaBulkDeleter", "rows deleted: %d", (Object) Integer.valueOf(contentResolver.delete(uri, this.mColumnName + " IN (" + this.mWhereClause.toString() + ")", (String[]) arrayList.toArray(new String[size]))));
            this.mWhereClause.setLength(0);
            this.mWhereArgs.clear();
        }
    }
}
