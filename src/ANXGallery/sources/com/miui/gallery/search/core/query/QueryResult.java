package com.miui.gallery.search.core.query;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Looper;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.result.SourceResult;
import com.miui.gallery.search.core.source.Source;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.search.utils.SearchUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QueryResult {
    private boolean mClosed = false;
    private final DataSetObservable mDataSetObservable = new DataSetObservable();
    private boolean mDone = false;
    private final List<Source> mExpectedSources;
    private boolean mHasResults = false;
    protected final QueryInfo mQuery;
    private final HashMap<String, Integer> mSourcePositions;
    private final Object mSourceResultLock = new Object();
    private final SourceResult[] mSourceResults;

    public QueryResult(QueryInfo queryInfo, List<Source> list) {
        this.mQuery = queryInfo;
        this.mExpectedSources = list;
        this.mSourceResults = new SourceResult[this.mExpectedSources.size()];
        this.mSourcePositions = new HashMap<>();
        for (int i = 0; i < this.mExpectedSources.size(); i++) {
            this.mSourcePositions.put(this.mExpectedSources.get(i).getName(), Integer.valueOf(i));
        }
        SearchLog.d("QueryResult", "new QueryResult [" + hashCode() + "] query \"" + queryInfo + "\" expected sources: " + this.mExpectedSources);
        this.mHasResults = false;
    }

    private int getSourceResultsCount() {
        int i;
        synchronized (this.mSourceResultLock) {
            i = 0;
            for (SourceResult sourceResult : this.mSourceResults) {
                if (sourceResult != null) {
                    i++;
                }
            }
        }
        return i;
    }

    public boolean addSourceResults(List<SourceResult> list) {
        boolean z = false;
        if (isClosed()) {
            for (SourceResult release : list) {
                release.release();
            }
            return false;
        }
        for (SourceResult next : list) {
            boolean z2 = true;
            if (next.getData() != null && next.getData().getCount() > 0) {
                this.mHasResults = true;
            }
            Integer num = this.mSourcePositions.get(next.getSource().getName());
            if (num == null) {
                SearchLog.w("QueryResult", "Got unexpected SourceResult from corpus " + next.getSource().getName());
                next.release();
            } else {
                synchronized (this.mSourceResultLock) {
                    if (this.mSourceResults[num.intValue()] == null) {
                        next.acquire();
                        this.mSourceResults[num.intValue()] = next;
                    } else if (SearchUtils.compareResultHashCode(this.mSourceResults[num.intValue()], next)) {
                        SearchLog.d("QueryResult", "We ignored later result of query [%s] source %s, for they are identical", this.mQuery, next.getSource());
                        next.release();
                        z2 = z;
                    } else {
                        this.mSourceResults[num.intValue()].release();
                        next.acquire();
                        this.mSourceResults[num.intValue()] = next;
                    }
                }
                z = z2;
            }
        }
        if (z) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    QueryResult.this.notifyDataSetChanged();
                }
            });
        }
        return z;
    }

    public void close() {
        if (!this.mClosed) {
            this.mClosed = true;
            this.mDataSetObservable.unregisterAll();
            synchronized (this.mSourceResultLock) {
                for (SourceResult sourceResult : this.mSourceResults) {
                    if (sourceResult != null) {
                        sourceResult.release();
                    }
                }
                Arrays.fill(this.mSourceResults, (Object) null);
            }
            return;
        }
        throw new IllegalStateException("Double close()");
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (!this.mClosed) {
            SearchLog.e("QueryResult", "LEAK! Finalized without being closed: QueryResult[" + getQuery() + "]");
            close();
        }
    }

    public QueryInfo getQuery() {
        return this.mQuery;
    }

    public List<SourceResult> getSourceResults() {
        ArrayList arrayList;
        synchronized (this.mSourceResultLock) {
            arrayList = new ArrayList(this.mSourceResults.length);
            for (int i = 0; i < this.mSourceResults.length; i++) {
                if (this.mSourceResults[i] != null) {
                    arrayList.add(this.mSourceResults[i]);
                }
            }
        }
        return arrayList;
    }

    public boolean isClosed() {
        return this.mClosed;
    }

    public boolean isDone() {
        return this.mDone || getSourceResultsCount() >= this.mExpectedSources.size();
    }

    /* access modifiers changed from: protected */
    public void notifyDataSetChanged() {
        this.mDataSetObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (!this.mClosed) {
            this.mDataSetObservable.registerObserver(dataSetObserver);
            return;
        }
        throw new IllegalStateException("registerDataSetObserver() when closed");
    }

    public String toString() {
        return "QueryResult@" + hashCode() + "{expectedSources=" + this.mExpectedSources + ",getSourceResultsCount()=" + getSourceResultsCount() + "}";
    }
}
