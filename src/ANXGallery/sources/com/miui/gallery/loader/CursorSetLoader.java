package com.miui.gallery.loader;

import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Printer;
import com.miui.gallery.model.BaseDataSet;
import com.miui.gallery.model.CursorDataSet;
import com.miui.gallery.util.GallerySamplingStatHelper;
import com.miui.gallery.util.logger.TimingTracing;
import java.util.HashMap;

public abstract class CursorSetLoader extends BaseLoader {
    private Loader<BaseDataSet>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);

    public CursorSetLoader(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public abstract String getOrder();

    /* access modifiers changed from: protected */
    public abstract String[] getProjection();

    /* access modifiers changed from: protected */
    public abstract String getSelection();

    /* access modifiers changed from: protected */
    public abstract String[] getSelectionArgs();

    /* access modifiers changed from: protected */
    public abstract String getTAG();

    /* access modifiers changed from: protected */
    public abstract Uri getUri();

    public CursorDataSet loadInBackground() {
        Cursor query;
        String str = getTAG() + "_load";
        TimingTracing.beginTracing(str, "loadInBackground");
        try {
            query = getContext().getContentResolver().query(getUri(), getProjection(), getSelection(), getSelectionArgs(), getOrder(), (CancellationSignal) null);
            if (query != null) {
                query.getCount();
            }
            CursorDataSet wrapDataSet = wrapDataSet(query);
            long stopTracing = TimingTracing.stopTracing(str, (Printer) null);
            if (stopTracing > 500) {
                HashMap hashMap = new HashMap();
                hashMap.put("cost", getSelection() + "_" + stopTracing);
                GallerySamplingStatHelper.recordErrorEvent("load_performance", getTAG(), hashMap);
            }
            return wrapDataSet;
        } catch (RuntimeException e) {
            query.close();
            throw e;
        } catch (Throwable th) {
            long stopTracing2 = TimingTracing.stopTracing(str, (Printer) null);
            if (stopTracing2 > 500) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("cost", getSelection() + "_" + stopTracing2);
                GallerySamplingStatHelper.recordErrorEvent("load_performance", getTAG(), hashMap2);
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public abstract CursorDataSet wrapDataSet(Cursor cursor);
}
