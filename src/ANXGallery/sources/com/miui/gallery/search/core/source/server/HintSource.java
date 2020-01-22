package com.miui.gallery.search.core.source.server;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import com.miui.gallery.cloud.HostManager;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.result.BaseSourceResult;
import com.miui.gallery.search.core.result.SourceResult;
import com.miui.gallery.search.core.source.Source;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.utils.SearchLog;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HintSource extends ServerSource {
    private static final SearchConstants.SearchType[] SUPPORT_SEARCH_TYPE = {SearchConstants.SearchType.SEARCH_TYPE_HINT};

    private static class HintResponseData implements Cacheable {
        @SerializedName("expireMs")
        public long expireMills;
        @SerializedName("hints")
        public List<HintSuggestion> hintSuggestions;

        private HintResponseData() {
        }

        public long getExpireMills() {
            return this.expireMills;
        }
    }

    public HintSource(Context context) {
        super(context);
    }

    private static SuggestionCursor createSuggestionCursor(List<HintSuggestion> list, Source source, QueryInfo queryInfo) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (HintSuggestion next : list) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("hint_text", next.hintText);
            hashMap.put("display_duration", String.valueOf(next.displayDurationMs));
            if (next.queryText != null) {
                hashMap.put("query_text", next.queryText);
            }
            arrayList.add(ConvertUtil.createSuggestion((Map<String, String>) hashMap, source));
        }
        return ConvertUtil.createSuggestionCursor(arrayList, queryInfo);
    }

    /* access modifiers changed from: protected */
    public boolean canCarryLog() {
        return true;
    }

    /* access modifiers changed from: protected */
    public int getMethod(QueryInfo queryInfo, String str) {
        if (str == null) {
            return 0;
        }
        return super.getMethod(queryInfo, str);
    }

    public String getName() {
        return "server_controlled_hints";
    }

    /* access modifiers changed from: protected */
    public String getQueryAppendPath(QueryInfo queryInfo) {
        return "hint";
    }

    /* access modifiers changed from: protected */
    public String getQueryPathPrefix(QueryInfo queryInfo, String str) {
        return str == null ? HostManager.Search.getSearchAnonymousUrlHost() : super.getQueryPathPrefix(queryInfo, str);
    }

    /* access modifiers changed from: protected */
    public String getQueryUserPath(String str) {
        return str == null ? "anonymous" : super.getQueryUserPath(str);
    }

    /* access modifiers changed from: protected */
    public Type getResponseDataType(QueryInfo queryInfo) {
        return HintResponseData.class;
    }

    public SearchConstants.SearchType[] getSupportSearchTypes() {
        return SUPPORT_SEARCH_TYPE;
    }

    /* access modifiers changed from: protected */
    public boolean isFatalCondition(QueryInfo queryInfo, int i) {
        return false;
    }

    /* access modifiers changed from: protected */
    public SourceResult onResponse(QueryInfo queryInfo, ServerSearchRequest serverSearchRequest, Object obj) {
        if (obj instanceof HintResponseData) {
            return new BaseSourceResult(queryInfo, this, createSuggestionCursor(((HintResponseData) obj).hintSuggestions, this, queryInfo));
        }
        SearchLog.e("HintSource", "Not supported response data type");
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean useCache(QueryInfo queryInfo) {
        return true;
    }
}
