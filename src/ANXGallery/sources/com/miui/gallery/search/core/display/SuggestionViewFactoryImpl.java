package com.miui.gallery.search.core.display;

import android.content.Context;
import android.view.ViewGroup;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.display.DefaultSectionHeaderView;
import com.miui.gallery.search.core.display.DefaultSuggestionView;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.navigationpage.NavigationPageViewFactory;
import com.miui.gallery.search.resultpage.FilterBarViewFactory;
import com.miui.gallery.search.resultpage.ResultPageViewFactory;
import com.miui.gallery.search.suggestionpage.SearchSuggestionViewFactory;
import com.miui.gallery.search.utils.SearchLog;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class SuggestionViewFactoryImpl implements SuggestionViewFactory {
    private final SuggestionViewFactory mDefaultFactory;
    private final LinkedList<SuggestionViewFactory> mFactories = new LinkedList<>();
    private Map<String, SuggestionViewFactory> mViewTypeToFactoryMap = new HashMap();

    public SuggestionViewFactoryImpl(Context context) {
        this.mDefaultFactory = new DefaultSuggestionView.Factory(context);
        addFactory(this.mDefaultFactory);
        addFactory(new DefaultSectionHeaderView.Factory(context));
        addFactory(new NavigationPageViewFactory(context));
        addFactory(new ResultPageViewFactory(context));
        addFactory(new SearchSuggestionViewFactory(context));
        addFactory(new FilterBarViewFactory(context));
    }

    private void addViewTypes(SuggestionViewFactory suggestionViewFactory) {
        for (String next : suggestionViewFactory.getSuggestionViewTypes()) {
            if (!this.mViewTypeToFactoryMap.containsKey(next)) {
                this.mViewTypeToFactoryMap.put(next, suggestionViewFactory);
            } else {
                throw new RuntimeException(String.format("The view type %s has already exists in other factory %s, please change a name", new Object[]{next, this.mViewTypeToFactoryMap.get(next)}));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void addFactory(SuggestionViewFactory suggestionViewFactory) {
        this.mFactories.addFirst(suggestionViewFactory);
        addViewTypes(suggestionViewFactory);
    }

    public void bindViewHolder(QueryInfo queryInfo, Suggestion suggestion, int i, BaseSuggestionViewHolder baseSuggestionViewHolder, OnActionClickListener onActionClickListener) {
        if (baseSuggestionViewHolder.getViewType() != null && this.mViewTypeToFactoryMap.containsKey(baseSuggestionViewHolder.getViewType())) {
            this.mViewTypeToFactoryMap.get(baseSuggestionViewHolder.getViewType()).bindViewHolder(queryInfo, suggestion, i, baseSuggestionViewHolder, onActionClickListener);
        }
    }

    public BaseSuggestionViewHolder createViewHolder(ViewGroup viewGroup, String str) {
        BaseSuggestionViewHolder createViewHolder = this.mViewTypeToFactoryMap.get(str).createViewHolder(viewGroup, str);
        if (str == null) {
            SearchLog.e("Error", "empty view type");
        }
        createViewHolder.setViewType(str);
        return createViewHolder;
    }

    public Collection<String> getSuggestionViewTypes() {
        return this.mViewTypeToFactoryMap.keySet();
    }

    public String getViewType(QueryInfo queryInfo, SuggestionCursor suggestionCursor, int i) {
        suggestionCursor.moveToPosition(i);
        Iterator it = this.mFactories.iterator();
        while (it.hasNext()) {
            String viewType = ((SuggestionViewFactory) it.next()).getViewType(queryInfo, suggestionCursor, i);
            if (viewType != null) {
                return viewType;
            }
        }
        return this.mDefaultFactory.getViewType(queryInfo, suggestionCursor, i);
    }
}
