package com.miui.gallery.search.core.source;

import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;

public abstract class AbstractSource implements Source {
    public int getPriority(QueryInfo queryInfo) {
        return 4;
    }

    public abstract SearchConstants.SearchType[] getSupportSearchTypes();

    public boolean match(QueryInfo queryInfo) {
        SearchConstants.SearchType[] supportSearchTypes;
        if (!(queryInfo == null || queryInfo.getSearchType() == null || (supportSearchTypes = getSupportSearchTypes()) == null || supportSearchTypes.length <= 0)) {
            for (SearchConstants.SearchType searchType : supportSearchTypes) {
                if (searchType == queryInfo.getSearchType()) {
                    return true;
                }
            }
        }
        return false;
    }
}
