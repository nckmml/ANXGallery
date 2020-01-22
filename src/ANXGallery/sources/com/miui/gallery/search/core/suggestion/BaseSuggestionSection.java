package com.miui.gallery.search.core.suggestion;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.source.Source;
import java.util.ArrayList;
import java.util.List;

public class BaseSuggestionSection implements SuggestionSection {
    protected String mDataURI;
    protected final SuggestionCursor mItems;
    protected Suggestion mMoreItem;
    protected final QueryInfo mQueryInfo;
    private List<RankInfo> mRankInfos;
    private SuggestionExtras mSectionExtras;
    private String mSectionSubTitle;
    private String mSectionTitle;
    protected final String mSectionTypeString;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BaseSuggestionSection(QueryInfo queryInfo, SearchConstants.SectionType sectionType, SuggestionCursor suggestionCursor, String str, String str2, String str3, Suggestion suggestion) {
        this(queryInfo, sectionType != null ? sectionType.getName() : SearchConstants.SectionType.SECTION_TYPE_DEFAULT.getName(), suggestionCursor, str, str2, str3, suggestion, (List<RankInfo>) null, (Bundle) null);
    }

    public BaseSuggestionSection(QueryInfo queryInfo, String str, SuggestionCursor suggestionCursor) {
        this(queryInfo, str, suggestionCursor, (String) null, (String) null, (String) null, (Suggestion) null, (List<RankInfo>) null, (Bundle) null);
    }

    public BaseSuggestionSection(QueryInfo queryInfo, String str, SuggestionCursor suggestionCursor, String str2, String str3, String str4, Suggestion suggestion, List<RankInfo> list, Bundle bundle) {
        this.mQueryInfo = queryInfo;
        this.mItems = suggestionCursor;
        if (TextUtils.isEmpty(str)) {
            this.mSectionTypeString = SearchConstants.SectionType.SECTION_TYPE_DEFAULT.getName();
        } else {
            this.mSectionTypeString = str;
        }
        this.mDataURI = str2;
        this.mSectionTitle = str3;
        this.mSectionSubTitle = str4;
        this.mMoreItem = suggestion;
        if (list != null && list.size() > 0) {
            this.mRankInfos = new ArrayList(list);
        }
        if (bundle != null && bundle != Bundle.EMPTY) {
            setExtras(bundle);
        }
    }

    public void close() {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.close();
        }
    }

    public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.copyStringToBuffer(i, charArrayBuffer);
        }
    }

    public void deactivate() {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.deactivate();
        }
    }

    public byte[] getBlob(int i) {
        return this.mItems.getBlob(i);
    }

    public int getColumnCount() {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return 0;
        }
        return suggestionCursor.getColumnCount();
    }

    public int getColumnIndex(String str) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return -1;
        }
        return suggestionCursor.getColumnIndex(str);
    }

    public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            return suggestionCursor.getColumnIndexOrThrow(str);
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist");
    }

    public String getColumnName(int i) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return null;
        }
        return suggestionCursor.getColumnName(i);
    }

    public String[] getColumnNames() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor == null ? new String[0] : suggestionCursor.getColumnNames();
    }

    public int getCount() {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return 0;
        }
        return suggestionCursor.getCount();
    }

    public Suggestion getCurrent() {
        return this.mItems.getCurrent();
    }

    public String getDataURI() {
        return this.mDataURI;
    }

    public double getDouble(int i) {
        return this.mItems.getDouble(i);
    }

    public Bundle getExtras() {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return null;
        }
        return suggestionCursor.getExtras();
    }

    public float getFloat(int i) {
        return this.mItems.getFloat(i);
    }

    public int getInt(int i) {
        return this.mItems.getInt(i);
    }

    public String getIntentActionURI() {
        return this.mItems.getIntentActionURI();
    }

    public long getLong(int i) {
        return this.mItems.getLong(i);
    }

    public Uri getNotificationUri() {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return null;
        }
        return suggestionCursor.getNotificationUri();
    }

    public int getPosition() {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return -1;
        }
        return suggestionCursor.getPosition();
    }

    public QueryInfo getQueryInfo() {
        return this.mQueryInfo;
    }

    public List<RankInfo> getRankInfos() {
        return this.mRankInfos;
    }

    public SuggestionExtras getSectionExtras() {
        return this.mSectionExtras;
    }

    public String getSectionSubTitle() {
        return this.mSectionSubTitle;
    }

    public String getSectionTitle() {
        return this.mSectionTitle;
    }

    public SearchConstants.SectionType getSectionType() {
        return SearchConstants.SectionType.fromName(this.mSectionTypeString);
    }

    public String getSectionTypeString() {
        return this.mSectionTypeString;
    }

    public short getShort(int i) {
        return this.mItems.getShort(i);
    }

    public String getString(int i) {
        return this.mItems.getString(i);
    }

    public SuggestionExtras getSuggestionExtras() {
        return this.mItems.getSuggestionExtras();
    }

    public String getSuggestionIcon() {
        return this.mItems.getSuggestionIcon();
    }

    public Source getSuggestionSource() {
        return this.mItems.getSuggestionSource();
    }

    public String getSuggestionSubTitle() {
        return this.mItems.getSuggestionSubTitle();
    }

    public String getSuggestionTitle() {
        return this.mItems.getSuggestionTitle();
    }

    public int getType(int i) {
        return this.mItems.getType(i);
    }

    public boolean getWantsAllOnMoveCalls() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.getWantsAllOnMoveCalls();
    }

    public boolean isAfterLast() {
        return this.mItems.isAfterLast();
    }

    public boolean isBeforeFirst() {
        return this.mItems.isBeforeFirst();
    }

    public boolean isClosed() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.isClosed();
    }

    public boolean isFirst() {
        return this.mItems.isFirst();
    }

    public boolean isLast() {
        return this.mItems.isLast();
    }

    public boolean isNull(int i) {
        return this.mItems.isNull(i);
    }

    public boolean move(int i) {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.move(i);
    }

    public boolean moveToFirst() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.moveToFirst();
    }

    public boolean moveToLast() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.moveToLast();
    }

    public Suggestion moveToMore() {
        return this.mMoreItem;
    }

    public boolean moveToNext() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.moveToNext();
    }

    public boolean moveToPosition(int i) {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.moveToPosition(i);
    }

    public boolean moveToPrevious() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.moveToPrevious();
    }

    public void registerContentObserver(ContentObserver contentObserver) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.registerContentObserver(contentObserver);
        }
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.registerDataSetObserver(dataSetObserver);
        }
    }

    public boolean requery() {
        SuggestionCursor suggestionCursor = this.mItems;
        return suggestionCursor != null && suggestionCursor.requery();
    }

    public Bundle respond(Bundle bundle) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor == null) {
            return null;
        }
        return suggestionCursor.respond(bundle);
    }

    public void setExtras(Bundle bundle) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.setExtras(bundle);
        }
    }

    public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.setNotificationUri(contentResolver, uri);
        }
    }

    public void setSectionExtras(SuggestionExtras suggestionExtras) {
        this.mSectionExtras = suggestionExtras;
    }

    public void setSectionMoreItem(Suggestion suggestion) {
        this.mMoreItem = suggestion;
    }

    public void unregisterContentObserver(ContentObserver contentObserver) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.unregisterContentObserver(contentObserver);
        }
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        SuggestionCursor suggestionCursor = this.mItems;
        if (suggestionCursor != null) {
            suggestionCursor.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
