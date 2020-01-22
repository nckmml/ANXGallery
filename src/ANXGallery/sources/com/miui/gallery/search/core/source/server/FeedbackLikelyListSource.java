package com.miui.gallery.search.core.source.server;

import android.content.Context;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;

public class FeedbackLikelyListSource extends DataListSource {
    private static final SearchConstants.SearchType[] SUPPORT_SEARCH_TYPE = {SearchConstants.SearchType.SEARCH_TYPE_FEEDBACK_LIKELY_RESULT};

    public FeedbackLikelyListSource(Context context) {
        super(context);
    }

    public String getName() {
        return "server_controlled_feedback_likely_list";
    }

    /* access modifiers changed from: protected */
    public String getQueryAppendPath(QueryInfo queryInfo) {
        return "tag/feedback/image/list";
    }

    public SearchConstants.SearchType[] getSupportSearchTypes() {
        return SUPPORT_SEARCH_TYPE;
    }

    /* access modifiers changed from: protected */
    public boolean isPersistable(QueryInfo queryInfo) {
        return false;
    }
}
