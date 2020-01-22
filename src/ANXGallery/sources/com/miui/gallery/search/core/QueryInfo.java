package com.miui.gallery.search.core;

import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.statistics.SearchStatUtils;
import java.util.HashMap;
import java.util.Map;

public class QueryInfo {
    /* access modifiers changed from: private */
    public Map<String, String> mParams = new HashMap();
    /* access modifiers changed from: private */
    public SearchConstants.SearchType mSearchType;

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean mAppendSerialInfo = false;
        /* access modifiers changed from: private */
        public Map<String, String> mParams;
        /* access modifiers changed from: private */
        public SearchConstants.SearchType mSearchType;

        public Builder() {
        }

        public Builder(SearchConstants.SearchType searchType) {
            if (searchType != null) {
                this.mSearchType = searchType;
                return;
            }
            throw new IllegalArgumentException("Empty search type!");
        }

        public Builder addParam(String str, String str2) {
            if (this.mParams == null) {
                this.mParams = new HashMap();
            }
            this.mParams.put(str, str2);
            return this;
        }

        public Builder addParams(Map<String, String> map) {
            if (this.mParams == null) {
                this.mParams = new HashMap();
            }
            this.mParams.putAll(map);
            return this;
        }

        public QueryInfo build() {
            if (this.mSearchType != null) {
                return new QueryInfo(this);
            }
            throw new IllegalArgumentException("Empty search type!");
        }

        public Builder cloneFrom(QueryInfo queryInfo) {
            this.mSearchType = queryInfo.mSearchType;
            this.mParams = new HashMap(queryInfo.mParams);
            return this;
        }

        public String removeParam(String str) {
            Map<String, String> map = this.mParams;
            if (map == null) {
                return null;
            }
            return map.remove(str);
        }

        public Builder setAppendSerialInfo(boolean z) {
            this.mAppendSerialInfo = z;
            return this;
        }

        public Builder setSearchType(SearchConstants.SearchType searchType) {
            this.mSearchType = searchType;
            return this;
        }
    }

    public QueryInfo(Builder builder) {
        this.mSearchType = builder.mSearchType;
        if (builder.mParams != null) {
            this.mParams.putAll(builder.mParams);
        }
        if (builder.mAppendSerialInfo) {
            this.mParams.put("serialId", SearchStatUtils.getCurrentSerial());
        }
    }

    public String getParam(String str) {
        return this.mParams.get(str);
    }

    public Map<String, String> getParams() {
        return this.mParams;
    }

    public SearchConstants.SearchType getSearchType() {
        return this.mSearchType;
    }

    public String toString() {
        return "search type = " + this.mSearchType + ", params = " + this.mParams;
    }
}
