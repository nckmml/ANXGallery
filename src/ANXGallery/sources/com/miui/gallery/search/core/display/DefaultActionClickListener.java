package com.miui.gallery.search.core.display;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.miui.gallery.search.SearchConfig;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.search.history.SearchHistoryService;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.util.ActionURIHandler;

public class DefaultActionClickListener implements OnActionClickListener {
    private Context mContext;

    public DefaultActionClickListener(Context context) {
        this.mContext = context;
    }

    private String extractFrom(Bundle bundle) {
        if (bundle != null && !bundle.isEmpty()) {
            String[] stringArray = bundle.getStringArray("search_event_keys");
            String[] stringArray2 = bundle.getStringArray("search_event_values");
            if (!(stringArray == null || stringArray2 == null || stringArray.length != stringArray2.length)) {
                for (int i = 0; i < stringArray.length; i++) {
                    if ("from".equals(stringArray[i])) {
                        return stringArray2[i];
                    }
                }
            }
        }
        return null;
    }

    private void recordHistoryIfNeeded(Suggestion suggestion, String str) {
        if (SearchConfig.get().getHistoryConfig().shouldRecordHistory(str)) {
            SearchHistoryService.addHistory(this.mContext, suggestion);
        }
    }

    /* access modifiers changed from: protected */
    public void handleUri(Activity activity, Uri uri, Bundle bundle) {
        ActionURIHandler.handleUri(activity, uri, bundle);
    }

    public void onClick(View view, int i, Object obj, Bundle bundle) {
        String extractFrom = extractFrom(bundle);
        if (i == 0 || i == 1) {
            String str = null;
            if (obj instanceof Suggestion) {
                Suggestion suggestion = (Suggestion) obj;
                recordHistoryIfNeeded(suggestion, extractFrom);
                str = suggestion.getIntentActionURI();
            } else if (obj != null) {
                str = obj.toString();
            }
            try {
                handleUri((Activity) this.mContext, Uri.parse(str), bundle);
            } catch (Exception unused) {
                String bundle2 = bundle == null ? "null" : bundle.toString();
                if (extractFrom == null) {
                    extractFrom = "null";
                }
                SearchLog.w("DefaultActionClickListener", "Action uri parse failed for extra [%s] from %s", bundle2, extractFrom);
            }
        } else if (i == 2) {
            SearchHistoryService.clearHistory(this.mContext);
            SearchStatUtils.reportEvent(extractFrom, "clear_search_history");
        }
    }
}
