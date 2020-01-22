package com.miui.gallery.search.resultpage;

import com.miui.gallery.search.core.result.SourceResult;
import com.miui.gallery.search.core.resultprocessor.ResultProcessor;
import java.util.List;

public class DataListResultProcessor extends ResultProcessor<DataListSourceResult> {
    /* access modifiers changed from: protected */
    public DataListSourceResult getMergedResult(List<SourceResult> list) {
        for (SourceResult next : list) {
            if (next instanceof DataListSourceResult) {
                return (DataListSourceResult) next;
            }
        }
        return null;
    }
}
