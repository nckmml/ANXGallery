package com.miui.gallery.search.suggestionpage;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.people.PeopleDisplayHelper;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.display.AbstractSuggestionViewFactory;
import com.miui.gallery.search.core.display.BaseSuggestionViewHolder;
import com.miui.gallery.search.core.display.OnActionClickListener;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionSection;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.util.face.PeopleItemBitmapDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SearchSuggestionViewFactory extends AbstractSuggestionViewFactory {
    private static Map<String, Integer> sViewTypes = new HashMap();
    private DisplayImageOptions mItemDisplayImageOptions;
    private DisplayImageOptions mPeopleItemDisplayImageOptions;

    static {
        Map<String, Integer> map = sViewTypes;
        Integer valueOf = Integer.valueOf(R.layout.default_suggestion_item);
        map.put("search_suggestion_people", valueOf);
        sViewTypes.put("search_suggestion_item", valueOf);
        sViewTypes.put("search_suggestion_no_result_header", Integer.valueOf(R.layout.suggestion_no_result_item));
        sViewTypes.put("search_suggestion_guide_item", Integer.valueOf(R.layout.guide_suggestion_item));
    }

    public SearchSuggestionViewFactory(Context context) {
        super(context);
    }

    private int[] findQueryTextSpan(String str, String str2) {
        int indexOf = str.indexOf(str2);
        if (indexOf < 0) {
            return null;
        }
        if (str.lastIndexOf(str2) != indexOf) {
            int indexOf2 = str.indexOf("\"" + str2);
            if (indexOf2 < 0) {
                indexOf2 = str.indexOf("“" + str2);
            }
            if (indexOf2 >= 0) {
                indexOf = indexOf2 + 1;
            }
        }
        return new int[]{indexOf, indexOf + str2.length()};
    }

    /* access modifiers changed from: private */
    public Suggestion getItem(SuggestionSection suggestionSection, int i) {
        if (i == -3) {
            return suggestionSection.moveToMore();
        }
        if (i < 0) {
            return null;
        }
        suggestionSection.moveToPosition(i);
        return suggestionSection.getCurrent();
    }

    public void bindViewHolder(QueryInfo queryInfo, final Suggestion suggestion, final int i, BaseSuggestionViewHolder baseSuggestionViewHolder, final OnActionClickListener onActionClickListener) {
        int[] findQueryTextSpan;
        SuggestionSection suggestionSection = (SuggestionSection) suggestion;
        if (i != -1) {
            Suggestion item = getItem(suggestionSection, i);
            if (item != null) {
                super.bindViewHolder(queryInfo, item, i, baseSuggestionViewHolder, onActionClickListener);
                if (!"search_suggestion_guide_item".equals(baseSuggestionViewHolder.getViewType()) && !TextUtils.isEmpty(queryInfo.getParam("query")) && baseSuggestionViewHolder.getTitle() != null && !TextUtils.isEmpty(item.getSuggestionTitle()) && (findQueryTextSpan = findQueryTextSpan(item.getSuggestionTitle().toLowerCase(), queryInfo.getParam("query").toLowerCase())) != null) {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(item.getSuggestionTitle());
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_blue)), findQueryTextSpan[0], findQueryTextSpan[1], 33);
                    baseSuggestionViewHolder.getTitle().setText(spannableStringBuilder);
                }
                if (item.getIntentActionURI() != null && baseSuggestionViewHolder.getClickView() != null && onActionClickListener != null) {
                    baseSuggestionViewHolder.getClickView().setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            String str = ((SuggestionSection) suggestion).getSectionType() == SearchConstants.SectionType.SECTION_TYPE_GUIDE ? "from_guide" : "from_suggestion";
                            SuggestionSection suggestionSection = (SuggestionSection) suggestion;
                            onActionClickListener.onClick(view, 0, SearchSuggestionViewFactory.this.getItem(suggestionSection, i), SearchStatUtils.buildSearchEventExtras((Bundle) null, new String[]{"from", "sectionType"}, new String[]{str, suggestionSection.getSectionTypeString()}));
                        }
                    });
                }
            }
        } else if (baseSuggestionViewHolder.getTitle() != null) {
            baseSuggestionViewHolder.getTitle().setText(suggestionSection.getSectionTitle());
        }
    }

    /* access modifiers changed from: protected */
    public DisplayImageOptions getDisplayImageOptionsForViewType(String str) {
        return (str.equals("search_suggestion_people") || str.equals("search_suggestion_guide_item")) ? this.mPeopleItemDisplayImageOptions : this.mItemDisplayImageOptions;
    }

    /* access modifiers changed from: protected */
    public int getLayoutIdForViewType(String str) {
        return sViewTypes.get(str).intValue();
    }

    public Collection<String> getSuggestionViewTypes() {
        return sViewTypes.keySet();
    }

    public String getViewType(QueryInfo queryInfo, SuggestionCursor suggestionCursor, int i) {
        if ((queryInfo.getSearchType() != SearchConstants.SearchType.SEARCH_TYPE_SEARCH && queryInfo.getSearchType() != SearchConstants.SearchType.SEARCH_TYPE_SUGGESTION) || !(suggestionCursor instanceof SuggestionSection)) {
            return null;
        }
        SearchConstants.SectionType sectionType = ((SuggestionSection) suggestionCursor).getSectionType();
        if (sectionType == SearchConstants.SectionType.SECTION_TYPE_NO_RESULT && i == -1) {
            return "search_suggestion_no_result_header";
        }
        if (i >= 0 || i == -3) {
            return sectionType == SearchConstants.SectionType.SECTION_TYPE_PEOPLE ? "search_suggestion_people" : sectionType == SearchConstants.SectionType.SECTION_TYPE_GUIDE ? "search_suggestion_guide_item" : "search_suggestion_item";
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void initDisplayImageOptions(Context context) {
        super.initDisplayImageOptions(context);
        this.mItemDisplayImageOptions = this.mDisplayImageOptionBuilder.displayer(new PeopleItemBitmapDisplayer(false)).showImageOnLoading((int) R.drawable.search_suggestion_icon_default).showImageOnFail(R.drawable.search_suggestion_icon_default).showImageForEmptyUri(R.drawable.search_suggestion_icon_default).build();
        this.mPeopleItemDisplayImageOptions = PeopleDisplayHelper.getDefaultPeopleDisplayOptionsBuilder().build();
    }
}
