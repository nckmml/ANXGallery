package com.miui.gallery.search.core.source.server;

import android.content.Context;
import android.text.TextUtils;
import com.miui.gallery.search.SearchConfig;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.util.Log;
import java.util.Map;

public class SearchSource extends SectionedServerSource {
    private static final SearchConstants.SearchType[] SUPPORT_SEARCH_TYPE = {SearchConstants.SearchType.SEARCH_TYPE_SEARCH, SearchConstants.SearchType.SEARCH_TYPE_SUGGESTION};

    /* renamed from: com.miui.gallery.search.core.source.server.SearchSource$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$search$SearchConstants$SearchType = new int[SearchConstants.SearchType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            $SwitchMap$com$miui$gallery$search$SearchConstants$SearchType[SearchConstants.SearchType.SEARCH_TYPE_SUGGESTION.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$search$SearchConstants$SearchType[SearchConstants.SearchType.SEARCH_TYPE_SEARCH.ordinal()] = 2;
        }
    }

    public SearchSource(Context context) {
        super(context);
    }

    public String getName() {
        return "server_controlled_search_suggestions";
    }

    /* access modifiers changed from: protected */
    public String getQueryAppendPath(QueryInfo queryInfo) {
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$search$SearchConstants$SearchType[queryInfo.getSearchType().ordinal()];
        if (i == 1) {
            return "suggestion";
        }
        if (i != 2) {
            return null;
        }
        return "search";
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getQueryParams(QueryInfo queryInfo) {
        Map<String, String> queryParams = super.getQueryParams(queryInfo);
        String str = queryParams.get("query");
        if (!TextUtils.isEmpty(str)) {
            String queryExtras = SearchConfig.get().getSuggestionConfig().getQueryExtras(str);
            if (!TextUtils.isEmpty(queryExtras)) {
                queryParams.put("extraInfo", queryExtras);
                Log.d("SearchSource", "On append extra [%s] to query [%s]", queryExtras, str);
            }
        }
        queryParams.remove("enableShortcut");
        return queryParams;
    }

    public SearchConstants.SearchType[] getSupportSearchTypes() {
        return SUPPORT_SEARCH_TYPE;
    }

    /* access modifiers changed from: protected */
    public boolean isFatalCondition(QueryInfo queryInfo, int i) {
        return super.isFatalCondition(queryInfo, i) || i == 14;
    }

    /* access modifiers changed from: protected */
    public boolean useCache(QueryInfo queryInfo) {
        return TextUtils.isEmpty(queryInfo.getParam("extraInfo"));
    }
}
