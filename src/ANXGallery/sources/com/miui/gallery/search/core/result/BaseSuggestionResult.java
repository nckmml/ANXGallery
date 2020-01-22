package com.miui.gallery.search.core.result;

import android.database.ContentObserver;
import android.database.DataSetObserver;
import android.os.Bundle;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.utils.SearchLog;

public class BaseSuggestionResult<S extends SuggestionCursor> implements SuggestionResult<S> {
    private boolean mClosed;
    private final S mData;
    private ErrorInfo mErrorInfo;
    private Bundle mExtras;
    private final QueryInfo mQueryInfo;
    private int mRefCount;

    public BaseSuggestionResult(QueryInfo queryInfo, S s) {
        this(queryInfo, s, (ErrorInfo) null);
    }

    public BaseSuggestionResult(QueryInfo queryInfo, S s, ErrorInfo errorInfo) {
        this.mClosed = false;
        this.mRefCount = 0;
        this.mExtras = Bundle.EMPTY;
        this.mQueryInfo = queryInfo;
        this.mData = s;
        this.mErrorInfo = errorInfo;
    }

    private void close() {
        SearchLog.d("BaseSuggestionResult", "SuggestionResult close() [" + hashCode() + "]");
        if (this.mClosed) {
            SearchLog.e("BaseSuggestionResult", "Double close()");
            return;
        }
        this.mClosed = true;
        try {
            if (this.mData != null && !this.mData.isClosed()) {
                this.mData.close();
            } else if (this.mData != null) {
                SearchLog.w("BaseSuggestionResult", this + "]'s data is already closed before result is closed!");
            }
        } catch (Exception e) {
            SearchLog.e("BaseSuggestionResult", (Throwable) e);
        }
    }

    public void acquire() {
        this.mRefCount++;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (!isClosed()) {
            SearchLog.e("BaseSuggestionResult", "LEAK! Finalized without being closed: BaseSuggestionResult[" + getQueryInfo() + "]");
            close();
        }
    }

    public S getData() {
        S s = this.mData;
        if (s != null && !s.isClosed()) {
            return this.mData;
        }
        return null;
    }

    public ErrorInfo getErrorInfo() {
        if (!isClosed()) {
            return this.mErrorInfo;
        }
        throw new IllegalStateException("getErrorMessage() when closed");
    }

    public QueryInfo getQueryInfo() {
        return this.mQueryInfo;
    }

    public Bundle getResultExtras() {
        return this.mExtras;
    }

    public boolean isClosed() {
        return this.mClosed;
    }

    public boolean isEmpty() {
        S s = this.mData;
        return s == null || s.getCount() <= 0;
    }

    public void registerContentObserver(ContentObserver contentObserver) {
        if (getData() != null) {
            this.mData.registerContentObserver(contentObserver);
        }
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        if (getData() != null) {
            this.mData.registerDataSetObserver(dataSetObserver);
        }
    }

    public void release() {
        this.mRefCount--;
        if (this.mRefCount <= 0 && !isClosed()) {
            close();
        }
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.mErrorInfo = errorInfo;
    }

    public void setResultExtras(Bundle bundle) {
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        this.mExtras = bundle;
    }
}
