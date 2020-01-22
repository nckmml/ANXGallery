package com.miui.gallery.search.core.display;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.display.QuickAdapterBase;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.statistics.SearchStatUtils;
import java.util.HashMap;

public class BaseSuggestionAdapter<S extends SuggestionCursor> extends QuickAdapterBase<BaseSuggestionViewHolder> {
    protected OnActionClickListener mActionClickListener;
    private String mFrom;
    protected QueryInfo mQueryInfo;
    protected S mSuggestionCursor;
    protected final SuggestionViewFactory mViewFactory;
    protected final HashMap<String, Integer> mViewTypeMap;
    private final HashMap<Integer, String> mViewTypeReverseMap;

    private static class SuggestionDiffCallback extends QuickAdapterBase.InnerDiffCallback {
        private SuggestionCursor mNewSuggestionCursor;
        private SuggestionCursor mOldSuggestionCursor;

        public SuggestionDiffCallback(SuggestionCursor suggestionCursor, SuggestionCursor suggestionCursor2) {
            this.mOldSuggestionCursor = suggestionCursor;
            this.mNewSuggestionCursor = suggestionCursor2;
        }

        public boolean areContentsTheSame(int i, int i2) {
            SuggestionCursor suggestionCursor;
            SuggestionCursor suggestionCursor2 = this.mOldSuggestionCursor;
            if (suggestionCursor2 == null || i >= suggestionCursor2.getCount() || (suggestionCursor = this.mNewSuggestionCursor) == null || i2 >= suggestionCursor.getCount()) {
                return false;
            }
            this.mOldSuggestionCursor.moveToPosition(i);
            this.mNewSuggestionCursor.moveToPosition(i2);
            return this.mOldSuggestionCursor.getCurrent().equals(this.mNewSuggestionCursor.getCurrent());
        }
    }

    public BaseSuggestionAdapter(Activity activity, SuggestionViewFactory suggestionViewFactory, String str) {
        this(activity, suggestionViewFactory, str, new DefaultActionClickListener(activity));
    }

    public BaseSuggestionAdapter(Activity activity, SuggestionViewFactory suggestionViewFactory, String str, OnActionClickListener onActionClickListener) {
        super(activity);
        this.mViewFactory = suggestionViewFactory;
        this.mFrom = str;
        this.mActionClickListener = onActionClickListener;
        this.mViewTypeMap = new HashMap<>();
        this.mViewTypeReverseMap = new HashMap<>();
        for (String next : this.mViewFactory.getSuggestionViewTypes()) {
            if (!this.mViewTypeMap.containsKey(next)) {
                HashMap<String, Integer> hashMap = this.mViewTypeMap;
                hashMap.put(next, Integer.valueOf(hashMap.size() + 16));
                HashMap<Integer, String> hashMap2 = this.mViewTypeReverseMap;
                hashMap2.put(Integer.valueOf(hashMap2.size() + 16), next);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void bindActionClickListener(View view, int i, SuggestionCursor suggestionCursor, int i2, String str) {
        final String str2 = str;
        final SuggestionCursor suggestionCursor2 = suggestionCursor;
        final int i3 = i2;
        final View view2 = view;
        final int i4 = i;
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str = str2;
                Bundle bundle = null;
                if (str != null) {
                    bundle = SearchStatUtils.buildSearchEventExtras((Bundle) null, new String[]{"from"}, new String[]{str});
                }
                suggestionCursor2.moveToPosition(i3);
                BaseSuggestionAdapter.this.getActionClickListener().onClick(view2, i4, suggestionCursor2, bundle);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void bindInnerItemViewHolder(BaseSuggestionViewHolder baseSuggestionViewHolder, int i) {
        this.mSuggestionCursor.moveToPosition(i);
        if (!(this.mSuggestionCursor.getIntentActionURI() == null || baseSuggestionViewHolder.getClickView() == null)) {
            bindActionClickListener(baseSuggestionViewHolder.getClickView(), 0, this.mSuggestionCursor, i, this.mFrom);
        }
        this.mViewFactory.bindViewHolder(this.mQueryInfo, this.mSuggestionCursor, i, baseSuggestionViewHolder, getActionClickListener());
    }

    public void changeSuggestions(QueryInfo queryInfo, S s) {
        changeSuggestions(queryInfo, s, true);
    }

    public void changeSuggestions(QueryInfo queryInfo, S s, boolean z) {
        if (this.mSuggestionCursor != s || this.mQueryInfo != queryInfo) {
            if (z) {
                S s2 = this.mSuggestionCursor;
                this.mSuggestionCursor = s;
                this.mQueryInfo = queryInfo;
                notifyDataChanged(new SuggestionDiffCallback(s2, this.mSuggestionCursor));
                if (s2 != null) {
                    s2.close();
                    return;
                }
                return;
            }
            S s3 = this.mSuggestionCursor;
            if (s3 != null) {
                s3.close();
            }
            this.mSuggestionCursor = s;
            this.mQueryInfo = queryInfo;
            if (this.mSuggestionCursor != null) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    /* access modifiers changed from: protected */
    public BaseSuggestionViewHolder createInnerItemViewHolder(ViewGroup viewGroup, int i) {
        if (this.mViewTypeReverseMap.containsKey(Integer.valueOf(i))) {
            return this.mViewFactory.createViewHolder(viewGroup, this.mViewTypeReverseMap.get(Integer.valueOf(i)));
        }
        throw new IllegalStateException("Unknown viewType " + i);
    }

    public OnActionClickListener getActionClickListener() {
        return this.mActionClickListener;
    }

    /* access modifiers changed from: protected */
    public int getInnerItemViewCount() {
        S s = this.mSuggestionCursor;
        if (s == null) {
            return 0;
        }
        return s.getCount();
    }

    /* access modifiers changed from: protected */
    public int getInnerItemViewType(int i) {
        this.mSuggestionCursor.moveToPosition(i);
        String viewType = this.mViewFactory.getViewType(this.mQueryInfo, this.mSuggestionCursor, i);
        if (this.mViewTypeMap.containsKey(viewType)) {
            return this.mViewTypeMap.get(viewType).intValue();
        }
        throw new IllegalStateException("Unknown viewType " + viewType);
    }

    /* access modifiers changed from: protected */
    public S getSuggestionCursor() {
        return this.mSuggestionCursor;
    }

    public boolean isEmpty() {
        return getInnerItemViewCount() <= 0;
    }

    /* access modifiers changed from: protected */
    public void notifyDataSetInvalidated() {
    }

    public void setActionClickListener(OnActionClickListener onActionClickListener) {
        this.mActionClickListener = onActionClickListener;
    }
}
