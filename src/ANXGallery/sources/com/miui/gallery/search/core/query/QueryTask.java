package com.miui.gallery.search.core.query;

import android.os.Handler;
import com.miui.gallery.search.core.Consumer;
import com.miui.gallery.search.core.ConsumerUtils;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.context.PriorityTaskExecutor;
import com.miui.gallery.search.core.result.SuggestionResult;
import com.miui.gallery.search.core.source.SuggestionResultProvider;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.threadpool.ThreadPool;
import com.miui.gallery.util.GallerySamplingStatHelper;
import java.util.HashMap;

public class QueryTask<C extends SuggestionResult> extends PriorityTaskExecutor.PriorityTask {
    private final Consumer<C> mConsumer;
    private final Handler mHandler;
    private final SuggestionResultProvider<C> mProvider;
    private final QueryInfo mQueryInfo;

    public QueryTask(QueryInfo queryInfo, SuggestionResultProvider<C> suggestionResultProvider, Consumer<C> consumer, Handler handler, int i) {
        this.mQueryInfo = queryInfo;
        this.mProvider = suggestionResultProvider;
        this.mConsumer = consumer;
        this.mHandler = handler;
        this.mPriority = i;
    }

    public Void run(ThreadPool.JobContext jobContext) {
        String str;
        if (jobContext.isCancelled()) {
            return null;
        }
        setExcuteTime(System.currentTimeMillis());
        C suggestions = this.mProvider.getSuggestions(this.mQueryInfo);
        if (jobContext.isCancelled()) {
            return null;
        }
        ConsumerUtils.consumeAsync(this.mHandler, this.mConsumer, suggestions);
        setFinishTime(System.currentTimeMillis());
        String queryTask = toString();
        String valueOf = String.valueOf(getFinishTime() - getExcuteTime());
        if (suggestions == null || suggestions.isEmpty() || suggestions.getData() == null) {
            str = "is empty";
        } else {
            str = suggestions.getData().getCount() + " items@" + Integer.toHexString(suggestions.hashCode());
        }
        SearchLog.d("QueryTask", "%s cost %sms, result %s", queryTask, valueOf, str);
        HashMap hashMap = new HashMap();
        hashMap.put("submit_create", String.valueOf(getSubmitTime() - getNewTime()));
        hashMap.put("execute_submit", String.valueOf(getExcuteTime() - getSubmitTime()));
        hashMap.put("finish_execute", String.valueOf(getFinishTime() - getExcuteTime()));
        GallerySamplingStatHelper.recordCountEvent("search", "search_query_task", hashMap);
        return null;
    }

    public String toString() {
        return "From " + this.mProvider + "[" + this.mQueryInfo + "]";
    }
}
