package com.miui.gallery.search.core.resultprocessor;

import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.result.ErrorInfo;
import com.miui.gallery.search.core.result.SourceResult;
import com.miui.gallery.search.utils.SearchUtils;
import java.util.List;

public class SimpleErrorProcessor {
    public ErrorInfo process(List<SourceResult> list) {
        ErrorInfo errorInfo = null;
        if (list != null && !list.isEmpty()) {
            if (list.size() == 1) {
                return list.get(0).getErrorInfo();
            }
            int i = -1;
            for (SourceResult next : list) {
                int errorStatus = SearchUtils.getErrorStatus(next);
                if (SearchConstants.isErrorStatus(errorStatus) && SearchConstants.compareErrorStatus(errorStatus, i) > 0) {
                    errorInfo = next.getErrorInfo();
                    i = errorStatus;
                }
            }
        }
        return errorInfo;
    }
}
