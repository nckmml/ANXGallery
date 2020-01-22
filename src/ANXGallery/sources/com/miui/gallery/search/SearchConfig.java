package com.miui.gallery.search;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.suggestion.BaseSuggestion;
import com.miui.gallery.search.core.suggestion.MapBackedSuggestionExtras;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.ui.AIAlbumStatusHelper;
import com.miui.gallery.util.GsonUtils;
import com.miui.gallery.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

public class SearchConfig {
    private static SearchConfig sConfig;
    private HistoryConfig mHistoryConfig;
    private NavigationConfig mNavigationConfig;
    private ResultConfig mResultConfig;
    private SuggestionConfig mSuggestionConfig;

    public static class HistoryConfig {
        private int maxStoreCount;
        private int navigationReturnCount;

        public HistoryConfig(Context context) {
            this.maxStoreCount = context.getResources().getInteger(R.integer.search_history_max_store_count);
            this.navigationReturnCount = context.getResources().getInteger(R.integer.search_history_navigation_return_count);
        }

        public int getNavigationReturnCount() {
            return this.navigationReturnCount;
        }

        public SearchConstants.SectionType getSectionType() {
            return SearchConstants.SectionType.SECTION_TYPE_HISTORY;
        }

        public String getSubTitle(Context context) {
            return context.getString(R.string.search_clear_histories);
        }

        public String getTitle(Context context) {
            return context.getString(R.string.search_title_history);
        }

        public boolean shouldRecordHistory(String str) {
            return !TextUtils.isEmpty(str) && ("from_location_list".equals(str) || "from_tag_list".equals(str) || "from_suggestion".equals(str) || "from_navigation_history".equals(str) || "from_image_result_filter".equals(str));
        }
    }

    public static class NavigationConfig {
        private int peopleItemCount;
        private int recommendItemCount;

        public NavigationConfig(Context context) {
            this.recommendItemCount = context.getResources().getInteger(R.integer.search_navigation_recommend_item_count);
            this.peopleItemCount = context.getResources().getInteger(R.integer.search_navigation_people_item_count);
        }

        public int getSectionMaxItemCount(SearchConstants.SectionType sectionType) {
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
            return i != 3 ? i != 5 ? Reader.READ_DONE : this.recommendItemCount : this.peopleItemCount;
        }

        public boolean isFatalCondition(int i) {
            if (i == 3 || i == 10) {
                return true;
            }
            switch (i) {
                case 12:
                case 13:
                case 14:
                    return true;
                default:
                    return false;
            }
        }

        public boolean useBatchContent(SearchConstants.SectionType sectionType) {
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
            return i == 1 || i == 2 || i == 3 || i == 5 || i == 8;
        }
    }

    public static class ResultConfig {
        private int imageLoadCount;
        private int likelyImageLoadCount;
        private int tagLocationLoadCount;

        private ResultConfig(Context context) {
            this.tagLocationLoadCount = context.getResources().getInteger(R.integer.search_result_tag_location_load_count);
            this.imageLoadCount = context.getResources().getInteger(R.integer.search_result_image_load_count);
            this.likelyImageLoadCount = context.getResources().getInteger(R.integer.search_likely_image_load_count);
        }

        public int getImageLoadCount() {
            return this.imageLoadCount;
        }

        public int getLikelyImageLoadCount() {
            return this.likelyImageLoadCount;
        }

        public int getTagLocationLoadCount() {
            return this.tagLocationLoadCount;
        }
    }

    public static class SuggestionConfig {
        private Map<String, Map<String, String>> mQueryExtras = null;
        private boolean mShouldJumpWhenSingleSug = false;

        public SuggestionConfig(Context context) {
            this.mShouldJumpWhenSingleSug = context.getResources().getBoolean(R.bool.search_should_jump_when_single_suggestion);
        }

        public void addQueryExtra(String str, String str2, String str3) {
            if (this.mQueryExtras == null) {
                this.mQueryExtras = new HashMap();
            }
            HashMap hashMap = new HashMap(2);
            hashMap.put("name", str);
            hashMap.put("peopleName", str2);
            hashMap.put("peopleId", str3);
            this.mQueryExtras.put(str, hashMap);
        }

        public boolean countToRecall(SearchConstants.SectionType sectionType) {
            switch (sectionType) {
                case SECTION_TYPE_GUIDE:
                case SECTION_TYPE_NO_RESULT:
                case SECTION_TYPE_WARNING:
                    return false;
                default:
                    return true;
            }
        }

        public String getQueryExtras(String str) {
            if (this.mQueryExtras != null && !TextUtils.isEmpty(str)) {
                ArrayList arrayList = new ArrayList();
                for (String next : this.mQueryExtras.keySet()) {
                    if (str.contains(next)) {
                        arrayList.add(this.mQueryExtras.get(next));
                    }
                }
                if (!arrayList.isEmpty()) {
                    HashMap hashMap = new HashMap(1);
                    hashMap.put("extraInfo", arrayList);
                    try {
                        return GsonUtils.toJson(hashMap);
                    } catch (JSONException unused) {
                        Log.e("SearchConfig", "Failed to transform to json [%s]", (Object) arrayList);
                    }
                }
            }
            return null;
        }

        public boolean shouldDrawSectionHeader(SearchConstants.SectionType sectionType) {
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
            if (i == 6) {
                return true;
            }
            switch (i) {
                case 10:
                case 11:
                case 12:
                    return true;
                default:
                    return false;
            }
        }

        public boolean supportShortcut(SearchConstants.SectionType sectionType) {
            int i = AnonymousClass1.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
            if (i == 6) {
                return false;
            }
            switch (i) {
                case 10:
                case 11:
                case 12:
                    return false;
                default:
                    return true;
            }
        }
    }

    private SearchConfig() {
        init(GalleryApp.sGetAndroidContext());
    }

    public static SearchConfig get() {
        if (sConfig == null) {
            synchronized (SearchConfig.class) {
                if (sConfig == null) {
                    sConfig = new SearchConfig();
                }
            }
        }
        return sConfig;
    }

    public Suggestion getDefaultMoreItem(Context context, SearchConstants.SectionType sectionType, boolean z) {
        if (context == null || sectionType == null) {
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$com$miui$gallery$search$SearchConstants$SectionType[sectionType.ordinal()];
        Uri uri = i != 1 ? i != 2 ? i != 3 ? null : GalleryContract.Common.URI_PEOPLE_LIST_PAGE : GalleryContract.Search.URI_LOCATION_LIST_PAGE : GalleryContract.Search.URI_TAG_LIST_PAGE;
        if (uri == null) {
            return null;
        }
        BaseSuggestion baseSuggestion = new BaseSuggestion();
        baseSuggestion.setSuggestionTitle(context.getString(R.string.search_title_more));
        baseSuggestion.setIntentActionURI(uri.toString());
        baseSuggestion.setSuggestionExtras(new MapBackedSuggestionExtras("fixed", String.valueOf(z)));
        return baseSuggestion;
    }

    public HistoryConfig getHistoryConfig() {
        return this.mHistoryConfig;
    }

    public NavigationConfig getNavigationConfig() {
        NavigationConfig navigationConfig = this.mNavigationConfig;
        if (navigationConfig != null) {
            return navigationConfig;
        }
        throw new RuntimeException("SearchConfig haven't been initiated yet!");
    }

    public ResultConfig getResultConfig() {
        return this.mResultConfig;
    }

    public SuggestionConfig getSuggestionConfig() {
        return this.mSuggestionConfig;
    }

    public String getTitleForSection(SearchConstants.SectionType sectionType) {
        int i;
        switch (sectionType) {
            case SECTION_TYPE_TAG:
                i = R.string.search_title_tag;
                break;
            case SECTION_TYPE_LOCATION:
                i = R.string.search_title_location;
                break;
            case SECTION_TYPE_PEOPLE:
                i = R.string.search_title_people;
                break;
            case SECTION_TYPE_ALBUM:
                i = R.string.search_title_album;
                break;
            case SECTION_TYPE_RECOMMEND:
                i = R.string.search_title_recommend;
                break;
            case SECTION_TYPE_SUGGESTION:
                i = R.string.search_title_suggestion;
                break;
            case SECTION_TYPE_OTHER:
                i = R.string.search_title_others;
                break;
            case SECTION_TYPE_HISTORY:
                i = R.string.search_title_history;
                break;
            case SECTION_TYPE_APP_SCREENSHOT:
                i = R.string.search_title_app_screenshot;
                break;
            default:
                return null;
        }
        return GalleryApp.sGetAndroidContext().getString(i);
    }

    public ArrayList<String> getVoiceAssistantSuggestion(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array.search_voice_assistant_suggestion);
        if (stringArray.length <= 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>(stringArray.length);
        for (String add : stringArray) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public void init(Context context) {
        if (this.mNavigationConfig == null) {
            this.mNavigationConfig = new NavigationConfig(context);
        }
        if (this.mSuggestionConfig == null) {
            this.mSuggestionConfig = new SuggestionConfig(context);
        }
        if (this.mHistoryConfig == null) {
            this.mHistoryConfig = new HistoryConfig(context);
        }
        if (this.mResultConfig == null) {
            this.mResultConfig = new ResultConfig(context);
        }
    }

    public boolean showSection(SearchConstants.SectionType sectionType) {
        return sectionType != SearchConstants.SectionType.SECTION_TYPE_PEOPLE || AIAlbumStatusHelper.isFaceAlbumEnabled();
    }
}
