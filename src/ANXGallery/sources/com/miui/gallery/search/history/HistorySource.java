package com.miui.gallery.search.history;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.search.SearchConfig;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.SearchContract;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.result.BaseSourceResult;
import com.miui.gallery.search.core.result.SourceResult;
import com.miui.gallery.search.core.source.InterceptableSource;
import com.miui.gallery.search.core.suggestion.BaseSuggestionSection;
import com.miui.gallery.search.core.suggestion.CursorBackedSuggestionCursor;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.util.Log;

public class HistorySource extends InterceptableSource {
    private static final String[] PROJECTION = {"title", "subTitle", "actionUri", "icon"};
    private static final SearchConstants.SearchType[] SUPPORT_SEARCH_TYPE = {SearchConstants.SearchType.SEARCH_TYPE_HISTORY, SearchConstants.SearchType.SEARCH_TYPE_NAVIGATION};

    public HistorySource(Context context) {
        super(context);
    }

    public SourceResult doGetSuggestions(QueryInfo queryInfo) {
        Uri.Builder appendQueryParameter = SearchContract.History.URI.buildUpon().appendQueryParameter("query_limit", String.valueOf(SearchConfig.get().getHistoryConfig().getNavigationReturnCount()));
        if (!TextUtils.isEmpty(queryInfo.getParam("query_text"))) {
            appendQueryParameter.appendQueryParameter("query_text", queryInfo.getParam("query_text"));
        }
        Cursor query = this.mContext.getContentResolver().query(appendQueryParameter.build(), PROJECTION, (String) null, (String[]) null, "timestamp DESC");
        SearchConfig.HistoryConfig historyConfig = SearchConfig.get().getHistoryConfig();
        BaseSuggestionSection baseSuggestionSection = null;
        if (query != null) {
            baseSuggestionSection = new BaseSuggestionSection(queryInfo, historyConfig.getSectionType(), new CursorBackedSuggestionCursor(queryInfo, query), (String) null, historyConfig.getTitle(this.mContext), historyConfig.getSubTitle(this.mContext), (Suggestion) null);
        }
        Log.d("HistorySource", "On load %s search histories", (Object) Integer.valueOf(baseSuggestionSection == null ? 0 : baseSuggestionSection.getCount()));
        return new BaseSourceResult(queryInfo, this, baseSuggestionSection);
    }

    public String getName() {
        return "local_history_source";
    }

    public int getPriority(QueryInfo queryInfo) {
        return (queryInfo == null || queryInfo.getSearchType() != SearchConstants.SearchType.SEARCH_TYPE_HISTORY) ? 3 : 0;
    }

    public SearchConstants.SearchType[] getSupportSearchTypes() {
        return SUPPORT_SEARCH_TYPE;
    }

    /* access modifiers changed from: protected */
    public boolean isFatalCondition(QueryInfo queryInfo, int i) {
        return queryInfo.getSearchType() == SearchConstants.SearchType.SEARCH_TYPE_NAVIGATION ? SearchConfig.get().getNavigationConfig().isFatalCondition(i) : super.isFatalCondition(queryInfo, i);
    }
}
