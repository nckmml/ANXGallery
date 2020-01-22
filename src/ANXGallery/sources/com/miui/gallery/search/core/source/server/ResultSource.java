package com.miui.gallery.search.core.source.server;

import android.content.Context;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;

public class ResultSource extends SectionedServerSource {
    private static final SearchConstants.SearchType[] SUPPORT_SEARCH_TYPE = {SearchConstants.SearchType.SEARCH_TYPE_RESULT};

    public ResultSource(Context context) {
        super(context);
    }

    public String getName() {
        return "server_controlled_results";
    }

    /* access modifiers changed from: protected */
    public String getQueryAppendPath(QueryInfo queryInfo) {
        return "result";
    }

    public SearchConstants.SearchType[] getSupportSearchTypes() {
        return SUPPORT_SEARCH_TYPE;
    }
}
