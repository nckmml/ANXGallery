package com.miui.gallery.provider;

import android.content.ContentProviderOperation;
import android.content.Context;
import com.miui.gallery.util.Log;
import java.util.ArrayList;

public class ContentProviderBatchOperator {
    private String mAuthority;
    private int mBatchSize = 50;
    private ArrayList<ContentProviderOperation> mOperations;

    public ContentProviderBatchOperator(String str) {
        this.mAuthority = str;
        this.mOperations = new ArrayList<>(this.mBatchSize);
    }

    public void add(Context context, ContentProviderOperation contentProviderOperation) {
        this.mOperations.add(contentProviderOperation);
        if (this.mOperations.size() >= this.mBatchSize) {
            apply(context);
        }
    }

    public void apply(Context context) {
        if (this.mOperations.size() > 0) {
            try {
                context.getContentResolver().applyBatch(this.mAuthority, this.mOperations);
            } catch (Exception e) {
                Log.e("ContentProviderBatchOperator", (Throwable) e);
            }
            this.mOperations.clear();
        }
    }
}
