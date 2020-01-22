package com.miui.gallery.search.core.suggestion;

import com.miui.gallery.search.SearchConstants;
import java.util.List;

public interface SuggestionSection extends SuggestionCursor {
    int getCount();

    String getDataURI();

    List<RankInfo> getRankInfos();

    SuggestionExtras getSectionExtras();

    String getSectionSubTitle();

    String getSectionTitle();

    SearchConstants.SectionType getSectionType();

    String getSectionTypeString();

    Suggestion moveToMore();
}
