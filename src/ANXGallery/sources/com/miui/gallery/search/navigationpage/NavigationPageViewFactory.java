package com.miui.gallery.search.navigationpage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.miui.gallery.R;
import com.miui.gallery.search.SearchConfig;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.context.SearchContext;
import com.miui.gallery.search.core.display.AbstractSuggestionViewFactory;
import com.miui.gallery.search.core.display.BaseSuggestionViewHolder;
import com.miui.gallery.search.core.display.OnActionClickListener;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionSection;
import com.miui.gallery.search.statistics.SearchStatUtils;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.search.widget.RoundedCornerRectBitmapDisplayer;
import com.miui.gallery.util.face.PeopleItemBitmapDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NavigationPageViewFactory extends AbstractSuggestionViewFactory {
    private static Map<String, Integer> sViewTypes = new HashMap();
    private DisplayImageOptions mPeopleItemDisplayImageOptions;
    private DisplayImageOptions mRecommendItemDisplayImageOptions;

    /* renamed from: com.miui.gallery.search.navigationpage.NavigationPageViewFactory$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType = new int[SearchConstants.SectionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[SearchConstants.SectionType.SECTION_TYPE_WARNING.ordinal()] = 1;
            $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[SearchConstants.SectionType.SECTION_TYPE_RECOMMEND.ordinal()] = 2;
            $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[SearchConstants.SectionType.SECTION_TYPE_PEOPLE.ordinal()] = 3;
            $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[SearchConstants.SectionType.SECTION_TYPE_LOCATION.ordinal()] = 4;
            $SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[SearchConstants.SectionType.SECTION_TYPE_TAG.ordinal()] = 5;
        }
    }

    static {
        sViewTypes.put("navigation_section_header", Integer.valueOf(R.layout.navigation_section_header));
        sViewTypes.put("navigation_recommend", Integer.valueOf(R.layout.navigation_recommend_section_content));
        sViewTypes.put("navigation_recommend_item", Integer.valueOf(R.layout.navigation_recommend_item));
        sViewTypes.put("navigation_people", Integer.valueOf(R.layout.navigation_people_section_content));
        sViewTypes.put("navigation_people_item", Integer.valueOf(R.layout.navigation_people_item));
        sViewTypes.put("navigation_people_more_item", Integer.valueOf(R.layout.navigation_people_more_item));
        sViewTypes.put("navigation_tag", Integer.valueOf(R.layout.navigation_tag_section_content));
        sViewTypes.put("navigation_tag_item", Integer.valueOf(R.layout.navigation_tag_item));
        sViewTypes.put("navigation_location_item", Integer.valueOf(R.layout.navigation_location_item));
        sViewTypes.put("navigation_section_content", Integer.valueOf(R.layout.navigation_default_section_content));
        sViewTypes.put("navigation_warning_header", Integer.valueOf(R.layout.navigation_warning_section_header));
    }

    public NavigationPageViewFactory(Context context) {
        super(context);
    }

    public void bindViewHolder(QueryInfo queryInfo, Suggestion suggestion, int i, BaseSuggestionViewHolder baseSuggestionViewHolder, final OnActionClickListener onActionClickListener) {
        SuggestionSection suggestionSection = (SuggestionSection) suggestion;
        if (i == -3) {
            super.bindViewHolder(queryInfo, suggestionSection.moveToMore(), i, baseSuggestionViewHolder, onActionClickListener);
            if (suggestionSection.getSectionType() == SearchConstants.SectionType.SECTION_TYPE_PEOPLE && suggestionSection.moveToPosition(SearchConfig.get().getNavigationConfig().getSectionMaxItemCount(suggestionSection.getSectionType())) && suggestionSection.getIntentActionURI() != null && baseSuggestionViewHolder.getIconView() != null) {
                bindIcon(baseSuggestionViewHolder.getIconView(), suggestionSection.getSuggestionIcon(), getDisplayImageOptionsForViewType(baseSuggestionViewHolder.getViewType()));
            }
        } else if (i == -2) {
            NavigationSectionAdapter contentAdapter = ((NavigationSectionContentView) baseSuggestionViewHolder.itemView).getContentAdapter();
            if (contentAdapter != null) {
                contentAdapter.changeSectionData(suggestionSection);
                return;
            }
            NavigationSectionAdapter createContentAdapter = createContentAdapter(suggestionSection);
            createContentAdapter.setActionClickListener(onActionClickListener);
            ((NavigationSectionContentView) baseSuggestionViewHolder.itemView).setContentAdapter(createContentAdapter);
        } else if (i != -1) {
            super.bindViewHolder(queryInfo, suggestion, i, baseSuggestionViewHolder, onActionClickListener);
        } else if (suggestionSection.getSectionType() != SearchConstants.SectionType.SECTION_TYPE_WARNING) {
            if (!(suggestionSection.getSectionType() != SearchConstants.SectionType.SECTION_TYPE_HISTORY || baseSuggestionViewHolder.getSubTitle() == null || onActionClickListener == null)) {
                baseSuggestionViewHolder.getSubTitle().setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        onActionClickListener.onClick(view, 2, (Object) null, SearchStatUtils.buildSearchEventExtras((Bundle) null, new String[]{"from"}, new String[]{"from_navigation_history"}));
                    }
                });
            }
            if (baseSuggestionViewHolder.getTitle() != null) {
                baseSuggestionViewHolder.getTitle().setText(suggestionSection.getSectionTitle());
            }
            if (baseSuggestionViewHolder.getSubTitle() != null) {
                baseSuggestionViewHolder.getSubTitle().setText(suggestionSection.getSectionSubTitle());
            }
        } else if (baseSuggestionViewHolder.getTitle() == null) {
        } else {
            if (suggestionSection.moveToFirst()) {
                baseSuggestionViewHolder.getTitle().setText(suggestionSection.getSuggestionTitle());
            } else {
                baseSuggestionViewHolder.getTitle().setText(suggestionSection.getSectionTitle());
            }
        }
    }

    /* access modifiers changed from: protected */
    public NavigationSectionAdapter createContentAdapter(SuggestionSection suggestionSection) {
        return new NavigationSectionAdapter(getContext(), SearchContext.getInstance().getSuggestionViewFactory(), suggestionSection, suggestionSection.getSectionType() == SearchConstants.SectionType.SECTION_TYPE_HISTORY ? "from_navigation_history" : "from_navigation", suggestionSection.getSectionType() == SearchConstants.SectionType.SECTION_TYPE_PEOPLE || suggestionSection.getSectionType() == SearchConstants.SectionType.SECTION_TYPE_FEATURE);
    }

    /* access modifiers changed from: protected */
    public DisplayImageOptions getDisplayImageOptionsForViewType(String str) {
        if (str != null) {
            return str.equals("navigation_recommend_item") ? this.mRecommendItemDisplayImageOptions : (str.equals("navigation_people_item") || str.equals("navigation_people_more_item")) ? this.mPeopleItemDisplayImageOptions : super.getDisplayImageOptionsForViewType(str);
        }
        SearchLog.e("Error", "empty view type");
        return super.getDisplayImageOptionsForViewType((String) null);
    }

    /* access modifiers changed from: protected */
    public int getLayoutIdForViewType(String str) {
        return sViewTypes.get(str).intValue();
    }

    public Collection<String> getSuggestionViewTypes() {
        return sViewTypes.keySet();
    }

    public String getViewType(QueryInfo queryInfo, SuggestionCursor suggestionCursor, int i) {
        if (queryInfo.getSearchType() != SearchConstants.SearchType.SEARCH_TYPE_NAVIGATION || !(suggestionCursor instanceof SuggestionSection)) {
            return null;
        }
        SearchConstants.SectionType sectionType = ((SuggestionSection) suggestionCursor).getSectionType();
        if (i == -3) {
            int i2 = AnonymousClass2.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
            return i2 != 2 ? i2 != 3 ? "navigation_tag_item" : "navigation_people_more_item" : "navigation_recommend_item";
        } else if (i == -2) {
            int i3 = AnonymousClass2.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
            return i3 != 2 ? i3 != 3 ? (i3 == 4 || i3 == 5) ? "navigation_tag" : "navigation_section_content" : "navigation_people" : "navigation_recommend";
        } else if (i == -1) {
            return AnonymousClass2.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()] != 1 ? "navigation_section_header" : "navigation_warning_header";
        } else {
            if (i < 0 || !SearchConfig.get().getNavigationConfig().useBatchContent(sectionType)) {
                return null;
            }
            int i4 = AnonymousClass2.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
            return i4 != 2 ? i4 != 3 ? i4 != 4 ? "navigation_tag_item" : "navigation_location_item" : "navigation_people_item" : "navigation_recommend_item";
        }
    }

    /* access modifiers changed from: protected */
    public void initDisplayImageOptions(Context context) {
        super.initDisplayImageOptions(context);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.search_radius_large);
        this.mRecommendItemDisplayImageOptions = this.mDisplayImageOptionBuilder.displayer(new RoundedCornerRectBitmapDisplayer(new int[]{dimensionPixelSize, 0, 0, dimensionPixelSize})).build();
        this.mPeopleItemDisplayImageOptions = this.mDisplayImageOptionBuilder.showImageOnLoading((int) R.drawable.default_face_cover).showImageOnFail(R.drawable.default_face_cover).showImageForEmptyUri(R.drawable.default_face_cover).displayer(new PeopleItemBitmapDisplayer(true)).usingRegionDecoderFace(true).build();
    }
}
