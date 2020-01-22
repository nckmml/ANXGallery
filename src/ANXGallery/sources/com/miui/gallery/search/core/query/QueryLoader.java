package com.miui.gallery.search.core.query;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.ContentObserver;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.OperationCanceledException;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.result.SuggestionResult;
import com.miui.gallery.search.core.resultprocessor.ResultProcessor;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.threadpool.ThreadManager;

public class QueryLoader<T extends SuggestionResult> extends AsyncTaskLoader<T> {
    private final QueryInfo mQueryInfo;
    /* access modifiers changed from: private */
    public ControllableTask<QueryResult> mQueryTask;
    private boolean mReceiveResultUpdates;
    private boolean mReceiveSourceUpdates;
    /* access modifiers changed from: private */
    public boolean mReportTillDone;
    private T mResult;
    private ContentObserver mResultContentObserver;
    private final ResultProcessor<T> mResultProcessor;
    private DataSetObserver mSourceDataSetObserver;

    public QueryLoader(Context context, QueryInfo queryInfo, ResultProcessor<T> resultProcessor) {
        this(context, queryInfo, resultProcessor, false, false, false);
    }

    public QueryLoader(Context context, QueryInfo queryInfo, ResultProcessor<T> resultProcessor, boolean z, boolean z2, boolean z3) {
        super(context);
        this.mQueryInfo = queryInfo;
        this.mResultProcessor = resultProcessor;
        this.mReceiveSourceUpdates = z;
        this.mReceiveResultUpdates = z2;
        this.mReportTillDone = z3;
    }

    private ContentObserver getResultContentObserver() {
        if (this.mResultContentObserver == null) {
            this.mResultContentObserver = new ContentObserver(ThreadManager.getMainHandler()) {
                public void onChange(boolean z) {
                    QueryLoader.this.onContentChanged();
                }
            };
        }
        return this.mResultContentObserver;
    }

    private DataSetObserver getSourceDataSetObserver() {
        if (this.mSourceDataSetObserver == null) {
            this.mSourceDataSetObserver = new DataSetObserver() {
                public void onChanged() {
                    if (!QueryLoader.this.mReportTillDone || !(QueryLoader.this.mQueryTask == null || QueryLoader.this.mQueryTask.getResult() == null || !((QueryResult) QueryLoader.this.mQueryTask.getResult()).isDone())) {
                        QueryLoader.this.onContentChanged();
                    } else {
                        SearchLog.e("QueryLoader", "On block query loader update");
                    }
                }
            };
        }
        return this.mSourceDataSetObserver;
    }

    private void onReleaseResources(T t) {
        if (t != null && !t.isClosed()) {
            t.release();
        }
    }

    public void deliverResult(T t) {
        T t2;
        T t3;
        if (isReset()) {
            onReleaseResources(t);
            return;
        }
        T t4 = this.mResult;
        this.mResult = t;
        if (this.mReceiveResultUpdates && (t3 = this.mResult) != t4 && t3 != null && !t3.isEmpty()) {
            this.mResult.registerContentObserver(getResultContentObserver());
        }
        if (isStarted() && (t2 = this.mResult) != t4) {
            super.deliverResult(t2);
        }
        if (t4 != this.mResult && t4 != null) {
            onReleaseResources(t4);
        }
    }

    public QueryInfo getQueryInfo() {
        return this.mQueryInfo;
    }

    public T loadInBackground() {
        String str;
        if (!isLoadInBackgroundCanceled()) {
            long currentTimeMillis = System.currentTimeMillis();
            T process = this.mResultProcessor.process(this.mQueryTask.getResult().getSourceResults());
            if (process != null) {
                boolean isDone = this.mQueryTask.getResult().isDone();
                Bundle resultExtras = process.getResultExtras();
                if (resultExtras == Bundle.EMPTY) {
                    resultExtras = new Bundle();
                }
                resultExtras.putBoolean("is_done", isDone);
                process.setResultExtras(resultExtras);
            }
            QueryInfo queryInfo = this.mQueryInfo;
            String valueOf = String.valueOf(System.currentTimeMillis() - currentTimeMillis);
            if (process == null) {
                str = "is null";
            } else if (process.isEmpty() || process.getData() == null) {
                str = "is empty";
            } else {
                str = "has " + process.getData().getCount() + "items";
            }
            SearchLog.d("QueryLoader", "Load result for {%s} cost %s ms, result %s", queryInfo, valueOf, str);
            return process;
        }
        throw new OperationCanceledException();
    }

    public void onCanceled(T t) {
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        onStopLoading();
        ControllableTask<QueryResult> controllableTask = this.mQueryTask;
        if (controllableTask != null) {
            controllableTask.cancel();
            if (this.mQueryTask.getResult() != null && !this.mQueryTask.getResult().isClosed()) {
                this.mQueryTask.getResult().close();
            }
            this.mQueryTask = null;
        }
        onReleaseResources(this.mResult);
        this.mResult = null;
    }

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        if (this.mQueryTask == null) {
            this.mQueryTask = QueryPackageHelper.getQueryResult(this.mQueryInfo, this.mReceiveSourceUpdates);
        }
        ControllableTask<QueryResult> controllableTask = this.mQueryTask;
        if (controllableTask == null) {
            SearchLog.e("QueryLoader", "No query task was created for query %s", this.mQueryInfo);
        } else if (!controllableTask.isCanceled()) {
            T t = this.mResult;
            if (t != null) {
                deliverResult(t);
            }
            if (!this.mQueryTask.started()) {
                this.mQueryTask.start();
                this.mQueryTask.getResult().registerDataSetObserver(getSourceDataSetObserver());
            }
            if (takeContentChanged()) {
                forceLoad();
            }
        } else {
            throw new RuntimeException("Invalid inner source, query task has been cancelled!");
        }
    }

    /* access modifiers changed from: protected */
    public void onStopLoading() {
        cancelLoad();
    }

    public String toString() {
        return super.toString() + "," + this.mQueryInfo;
    }
}
