package com.miui.gallery.search.resultpage;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import com.miui.gallery.GalleryApp;
import com.miui.gallery.R;
import com.miui.gallery.provider.GalleryContract;
import com.miui.gallery.search.SearchConfig;
import com.miui.gallery.search.SearchConstants;
import com.miui.gallery.search.core.QueryInfo;
import com.miui.gallery.search.core.result.BaseSuggestionResult;
import com.miui.gallery.search.core.result.ErrorInfo;
import com.miui.gallery.search.core.result.SourceResult;
import com.miui.gallery.search.core.result.SuggestionResult;
import com.miui.gallery.search.core.resultprocessor.LinearResultProcessor;
import com.miui.gallery.search.core.resultprocessor.ResultProcessor;
import com.miui.gallery.search.core.suggestion.BaseSuggestion;
import com.miui.gallery.search.core.suggestion.BaseSuggestionSection;
import com.miui.gallery.search.core.suggestion.CursorBackedSuggestionCursor;
import com.miui.gallery.search.core.suggestion.GroupedSuggestionCursor;
import com.miui.gallery.search.core.suggestion.ListSuggestionCursor;
import com.miui.gallery.search.core.suggestion.MapBackedSuggestionExtras;
import com.miui.gallery.search.core.suggestion.RankInfo;
import com.miui.gallery.search.core.suggestion.Suggestion;
import com.miui.gallery.search.core.suggestion.SuggestionCursor;
import com.miui.gallery.search.core.suggestion.SuggestionSection;
import com.miui.gallery.search.resultpage.SearchResultHelper;
import com.miui.gallery.search.utils.SearchLog;
import com.miui.gallery.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import miui.util.IOUtils;

public class SearchImageResultHelper extends SearchResultHelper {
    /* access modifiers changed from: private */
    public static final String[] OUT_PROJECTION = StringUtils.mergeStringArray(QUERY_PROJECTION, new String[]{"item_collapse_visibility"});
    /* access modifiers changed from: private */
    public static final String[] QUERY_PROJECTION = {"_id", "alias_micro_thumbnail", "alias_create_date", "alias_create_time", "location", "sha1", "serverType", "duration", "mimeType", "alias_sync_state", "thumbnailFile", "localFile", "serverId", "alias_is_favorite", "specialTypeFlags", "size"};
    /* access modifiers changed from: private */
    public static final String TITLE_SEPARATOR = GalleryApp.sGetAndroidContext().getString(R.string.name_split);
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public boolean mSupportExpand;

    private class FilterProcessor extends LinearResultProcessor {
        private FilterProcessor() {
        }

        /* access modifiers changed from: protected */
        public SuggestionResult getMergedResult(List<SourceResult> list) {
            BaseSuggestion suggestion;
            ErrorInfo mergedErrorInfo = getMergedErrorInfo(list);
            ArrayList<Suggestion> arrayList = new ArrayList<>();
            int i = 0;
            QueryInfo queryInfo = null;
            boolean z = false;
            for (SourceResult next : list) {
                if (queryInfo == null && next.getQueryInfo() != null) {
                    queryInfo = next.getQueryInfo();
                }
                SuggestionCursor data = next.getData();
                if (data != null) {
                    boolean z2 = z;
                    for (int i2 = 0; i2 < data.getCount(); i2++) {
                        if (data.moveToPosition(i2) && (suggestion = toSuggestion(data.getCurrent())) != null) {
                            arrayList.add(suggestion);
                            z2 |= TextUtils.isEmpty(suggestion.getSuggestionIcon());
                        }
                    }
                    z = z2;
                }
            }
            if (z) {
                for (Suggestion suggestion2 : arrayList) {
                    ((BaseSuggestion) suggestion2).setSuggestionIcon((String) null);
                }
            }
            ListSuggestionCursor listSuggestionCursor = new ListSuggestionCursor(queryInfo, arrayList);
            Bundle extras = listSuggestionCursor.getExtras();
            if (extras == Bundle.EMPTY) {
                extras = new Bundle();
            }
            if (!z) {
                i = 1;
            }
            extras.putInt("filter_style", i);
            listSuggestionCursor.setExtras(extras);
            return new BaseSuggestionResult(queryInfo, listSuggestionCursor, mergedErrorInfo);
        }
    }

    private class ImageResultProcessor extends SearchResultHelper.DataListResultProcessor {
        private SparseBooleanArray mCachedGroupExpandState = null;

        public ImageResultProcessor(RankInfo rankInfo) {
            super(rankInfo);
        }

        private String appendNotInSecretSelection(String str) {
            return String.format("(%s) AND (%s != %s)", new Object[]{str, "localGroupId", -1000L});
        }

        private void doExpandableFirstStepQuery(String str, ArrayList<String> arrayList, List<Long> list, List<Integer> list2) {
            String str2 = str;
            long currentTimeMillis = System.currentTimeMillis();
            Cursor cursor = null;
            try {
                cursor = SearchImageResultHelper.this.mContext.getContentResolver().query(getQueryUri(true), SearchImageResultHelper.QUERY_PROJECTION, appendNotInSecretSelection(str), (String[]) null, "alias_create_time DESC ");
                if (cursor != null && cursor.getCount() > 0) {
                    arrayList.addAll(cursor.getExtras().getStringArrayList("extra_timeline_group_start_locations"));
                    ArrayList<Integer> integerArrayList = cursor.getExtras().getIntegerArrayList("extra_timeline_group_start_pos");
                    if (cursor.moveToFirst()) {
                        do {
                            list.add(Long.valueOf(cursor.getLong(0)));
                        } while (cursor.moveToNext());
                    }
                    Iterator<Integer> it = integerArrayList.iterator();
                    while (it.hasNext()) {
                        cursor.moveToPosition(it.next().intValue());
                        list2.add(Integer.valueOf(cursor.getInt(2)));
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                SearchLog.d("ImageResultProcessor", "[%d]ms: first step query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:55:0x01c8  */
        /* JADX WARNING: Removed duplicated region for block: B:61:0x01df  */
        private ImageResultSuggestionCursor doExpandableSecondStepQuery(QueryInfo queryInfo, RankInfo rankInfo, List<Integer> list, ArrayList<String> arrayList, List<Long> list2) {
            Cursor cursor;
            String str;
            Cursor cursor2;
            String str2;
            ImageResultSuggestionCursor imageResultSuggestionCursor;
            RankInfo rankInfo2 = rankInfo;
            ArrayList<String> arrayList2 = arrayList;
            List<Long> list3 = list2;
            long currentTimeMillis = System.currentTimeMillis();
            int i = 0;
            String format = String.format("%s IN (%s)", new Object[]{"alias_create_date", TextUtils.join(",", list.toArray())});
            SparseBooleanArray sparseBooleanArray = null;
            try {
                Cursor query = SearchImageResultHelper.this.mContext.getContentResolver().query(getQueryUri(true), SearchImageResultHelper.QUERY_PROJECTION, appendNotInSecretSelection(format), (String[]) null, "alias_create_time DESC ");
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            ArrayList<String> stringArrayList = query.getExtras().getStringArrayList("extra_timeline_group_start_locations");
                            ArrayList<Integer> integerArrayList = query.getExtras().getIntegerArrayList("extra_timeline_group_start_pos");
                            ArrayList arrayList3 = new ArrayList(integerArrayList.size());
                            MatrixCursor matrixCursor = new MatrixCursor(SearchImageResultHelper.OUT_PROJECTION);
                            ArrayList arrayList4 = new ArrayList();
                            ArrayList arrayList5 = new ArrayList();
                            MatrixCursor matrixCursor2 = matrixCursor;
                            loop0:
                            while (true) {
                                int i2 = i;
                                while (true) {
                                    if (i2 >= query.getCount()) {
                                        break loop0;
                                    }
                                    query.moveToPosition(i2);
                                    long j = query.getLong(0);
                                    arrayList5.add(Long.valueOf(j));
                                    if (list3.contains(Long.valueOf(j))) {
                                        arrayList4.add(Long.valueOf(j));
                                    }
                                    matrixCursor2.addRow(toRow(query, list3.contains(Long.valueOf(j)) ? "visible" : "invisible"));
                                    i = i2 + 1;
                                    if (integerArrayList.contains(Integer.valueOf(i)) || i == query.getCount()) {
                                        int size = (i == query.getCount() ? integerArrayList.size() : integerArrayList.indexOf(Integer.valueOf(i))) - 1;
                                        ArrayList arrayList6 = arrayList5;
                                        Long valueOf = Long.valueOf(query.getLong(3));
                                        Integer num = list.get(size);
                                        String str3 = arrayList2 != null ? arrayList2.get(size) : null;
                                        String str4 = stringArrayList != null ? stringArrayList.get(size) : null;
                                        ArrayList arrayList7 = arrayList4;
                                        MatrixCursor matrixCursor3 = matrixCursor2;
                                        ArrayList arrayList8 = arrayList3;
                                        Long l = valueOf;
                                        ArrayList<Integer> arrayList9 = integerArrayList;
                                        Integer num2 = num;
                                        ArrayList<String> arrayList10 = stringArrayList;
                                        String str5 = str3;
                                        cursor = query;
                                        str = format;
                                        int i3 = i;
                                        try {
                                            ArrayList arrayList11 = arrayList8;
                                            arrayList11.add(toSection(queryInfo, matrixCursor3, l, num2, str5, str4, (String) null, arrayList7, arrayList6));
                                            matrixCursor2 = new MatrixCursor(SearchImageResultHelper.OUT_PROJECTION);
                                            arrayList7.clear();
                                            arrayList6.clear();
                                            arrayList3 = arrayList11;
                                            format = str;
                                            arrayList5 = arrayList6;
                                            integerArrayList = arrayList9;
                                            i2 = i3;
                                            stringArrayList = arrayList10;
                                            query = cursor;
                                            arrayList4 = arrayList7;
                                            arrayList2 = arrayList;
                                        } catch (Throwable th) {
                                            th = th;
                                            if (cursor != null) {
                                            }
                                            SearchLog.d("ImageResultProcessor", "[%d]ms: second step query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str);
                                            throw th;
                                        }
                                    }
                                }
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                SearchLog.d("ImageResultProcessor", "[%d]ms: second step query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str2);
                                return imageResultSuggestionCursor;
                            }
                            ArrayList arrayList12 = arrayList3;
                            cursor2 = query;
                            str2 = format;
                            SuggestionCursor data = SearchImageResultHelper.this.getResult() != null ? SearchImageResultHelper.this.getResult().getData() : null;
                            if (data != null) {
                                sparseBooleanArray = ((ImageResultSuggestionCursor) data).getGroupExpandStateArray();
                            }
                            this.mCachedGroupExpandState = sparseBooleanArray == null ? this.mCachedGroupExpandState : sparseBooleanArray;
                            imageResultSuggestionCursor = new ImageResultSuggestionCursor(queryInfo, arrayList12, SearchImageResultHelper.this.mSupportExpand, this.mCachedGroupExpandState);
                            imageResultSuggestionCursor.setNotificationUri(SearchImageResultHelper.this.mContext.getContentResolver(), cursor2.getNotificationUri());
                            if (rankInfo2 != null) {
                                imageResultSuggestionCursor.setExtras(createRankInfoBundle(rankInfo2));
                            }
                            if (cursor2 != null) {
                            }
                            SearchLog.d("ImageResultProcessor", "[%d]ms: second step query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str2);
                            return imageResultSuggestionCursor;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        str = format;
                        if (cursor != null) {
                        }
                        SearchLog.d("ImageResultProcessor", "[%d]ms: second step query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str);
                        throw th;
                    }
                }
                cursor2 = query;
                str2 = format;
                imageResultSuggestionCursor = null;
                if (cursor2 != null) {
                }
                SearchLog.d("ImageResultProcessor", "[%d]ms: second step query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str2);
                return imageResultSuggestionCursor;
            } catch (Throwable th3) {
                th = th3;
                str = format;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                SearchLog.d("ImageResultProcessor", "[%d]ms: second step query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), str);
                throw th;
            }
        }

        private Uri getQueryUri(boolean z) {
            return GalleryContract.Media.URI.buildUpon().appendQueryParameter("generate_headers", String.valueOf(z)).build();
        }

        private String getSuggestionsServerIdSelection(List<Suggestion> list) {
            StringBuilder sb = new StringBuilder();
            for (Suggestion suggestionExtra : list) {
                String suggestionExtra2 = getSuggestionExtra(suggestionExtra, "serverId");
                if (suggestionExtra2 != null) {
                    sb.append("'");
                    sb.append(suggestionExtra2);
                    sb.append("',");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
                sb.insert(0, "serverId IN (").append(")");
            }
            return sb.toString();
        }

        private Object[] toRow(Cursor cursor, String str) {
            Object[] objArr = new Object[SearchImageResultHelper.OUT_PROJECTION.length];
            objArr[0] = Long.valueOf(cursor.getLong(0));
            objArr[1] = cursor.getString(1);
            objArr[2] = Integer.valueOf(cursor.getInt(2));
            objArr[3] = Long.valueOf(cursor.getLong(3));
            objArr[4] = cursor.getString(4);
            objArr[5] = cursor.getString(5);
            objArr[6] = Integer.valueOf(cursor.getInt(6));
            objArr[7] = Long.valueOf(cursor.getLong(7));
            objArr[8] = cursor.getString(8);
            objArr[9] = Integer.valueOf(cursor.getInt(9));
            objArr[10] = cursor.getString(10);
            objArr[11] = cursor.getString(11);
            objArr[12] = cursor.getString(12);
            objArr[13] = cursor.getString(13);
            objArr[14] = cursor.getString(14);
            objArr[15] = Long.valueOf(cursor.getLong(15));
            objArr[16] = str;
            return objArr;
        }

        private BaseSuggestionSection toSection(QueryInfo queryInfo, Cursor cursor, Long l, Integer num, String str, String str2, String str3, List<Long> list, List<Long> list2) {
            BaseSuggestionSection baseSuggestionSection = new BaseSuggestionSection(queryInfo, SearchConstants.SectionType.SECTION_TYPE_IMAGE_LIST.getName(), new CursorBackedSuggestionCursor(queryInfo, cursor));
            HashMap hashMap = new HashMap();
            hashMap.put("all_ids", TextUtils.join(",", list2));
            if (num != null) {
                hashMap.put("create_date", num.toString());
            }
            if (l != null) {
                hashMap.put("create_time", String.valueOf(l));
            }
            if (str != null) {
                hashMap.put("title", str);
            }
            if (str3 != null) {
                hashMap.put("rank_value", str3);
            }
            if (list != null) {
                hashMap.put("collapse_visible_ids", TextUtils.join(",", list));
            }
            if (str2 != null) {
                hashMap.put("expand_title", str2);
            }
            baseSuggestionSection.setSectionExtras(new MapBackedSuggestionExtras(hashMap));
            return baseSuggestionSection;
        }

        /* access modifiers changed from: protected */
        public SuggestionResult createSuggestionResult(List<Suggestion> list, QueryInfo queryInfo, RankInfo rankInfo, ErrorInfo errorInfo) {
            ImageResultSuggestionCursor imageResultSuggestionCursor;
            long currentTimeMillis = System.currentTimeMillis();
            String suggestionsServerIdSelection = getSuggestionsServerIdSelection(list);
            if (!TextUtils.isEmpty(suggestionsServerIdSelection)) {
                if (!supportExpand(rankInfo)) {
                    imageResultSuggestionCursor = doSimpleQuery(list, queryInfo, rankInfo, suggestionsServerIdSelection);
                } else {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    doExpandableFirstStepQuery(suggestionsServerIdSelection, arrayList, arrayList2, arrayList3);
                    if (arrayList3.size() > 0) {
                        imageResultSuggestionCursor = doExpandableSecondStepQuery(queryInfo, rankInfo, arrayList3, arrayList, arrayList2);
                    }
                }
                SearchLog.d("ImageResultProcessor", "[%d]ms: query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), queryInfo);
                return new BaseSuggestionResult(queryInfo, imageResultSuggestionCursor, errorInfo);
            }
            imageResultSuggestionCursor = null;
            SearchLog.d("ImageResultProcessor", "[%d]ms: query time for [%s]", Long.valueOf(System.currentTimeMillis() - currentTimeMillis), queryInfo);
            return new BaseSuggestionResult(queryInfo, imageResultSuggestionCursor, errorInfo);
        }

        /* access modifiers changed from: protected */
        public ImageResultSuggestionCursor doSimpleQuery(List<Suggestion> list, QueryInfo queryInfo, RankInfo rankInfo, String str) {
            ImageResultSuggestionCursor doSimpleQueryWithDate = (rankInfo == null || "date".equals(rankInfo.getName())) ? doSimpleQueryWithDate(queryInfo, rankInfo, str) : doSimpleQueryWithRankValue(list, rankInfo, queryInfo, str);
            if (!(doSimpleQueryWithDate == null || rankInfo == null)) {
                doSimpleQueryWithDate.setExtras(createRankInfoBundle(rankInfo));
            }
            return doSimpleQueryWithDate;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x0147  */
        public ImageResultSuggestionCursor doSimpleQueryWithDate(QueryInfo queryInfo, RankInfo rankInfo, String str) {
            Cursor cursor;
            int i = 2;
            try {
                Object[] objArr = new Object[2];
                objArr[0] = "alias_create_time";
                objArr[1] = (rankInfo == null || rankInfo.getOrder() == null) ? SearchConstants.getDefaultOrder("date") : rankInfo.getOrder();
                cursor = SearchImageResultHelper.this.mContext.getContentResolver().query(getQueryUri(true), SearchImageResultHelper.QUERY_PROJECTION, appendNotInSecretSelection(str), (String[]) null, String.format("%s %s", objArr));
                if (cursor != null) {
                    try {
                        if (cursor.getCount() > 0) {
                            ArrayList<String> stringArrayList = cursor.getExtras().getStringArrayList("extra_timeline_group_start_locations");
                            ArrayList<Integer> integerArrayList = cursor.getExtras().getIntegerArrayList("extra_timeline_group_start_pos");
                            ArrayList arrayList = new ArrayList(integerArrayList.size());
                            MatrixCursor matrixCursor = new MatrixCursor(SearchImageResultHelper.OUT_PROJECTION);
                            ArrayList arrayList2 = new ArrayList();
                            MatrixCursor matrixCursor2 = matrixCursor;
                            int i2 = 0;
                            while (i2 < cursor.getCount()) {
                                cursor.moveToPosition(i2);
                                arrayList2.add(Long.valueOf(cursor.getLong(0)));
                                matrixCursor2.addRow(toRow(cursor, "visible"));
                                int i3 = i2 + 1;
                                if (!integerArrayList.contains(Integer.valueOf(i3))) {
                                    if (i3 != cursor.getCount()) {
                                        i2 = i3;
                                    }
                                }
                                ArrayList arrayList3 = arrayList2;
                                ArrayList arrayList4 = arrayList;
                                arrayList4.add(toSection(queryInfo, matrixCursor2, Long.valueOf(cursor.getLong(3)), Integer.valueOf(cursor.getInt(i)), stringArrayList.get((i3 == cursor.getCount() ? integerArrayList.size() : integerArrayList.indexOf(Integer.valueOf(i3))) - 1), (String) null, (String) null, (List<Long>) null, arrayList3));
                                IOUtils.closeQuietly(matrixCursor2);
                                matrixCursor2 = new MatrixCursor(SearchImageResultHelper.OUT_PROJECTION);
                                arrayList3.clear();
                                arrayList = arrayList4;
                                arrayList2 = arrayList3;
                                integerArrayList = integerArrayList;
                                i2 = i3;
                                i = 2;
                            }
                            IOUtils.closeQuietly(matrixCursor2);
                            ImageResultSuggestionCursor imageResultSuggestionCursor = new ImageResultSuggestionCursor(queryInfo, arrayList, false);
                            imageResultSuggestionCursor.setNotificationUri(SearchImageResultHelper.this.mContext.getContentResolver(), cursor.getNotificationUri());
                            if (cursor != null) {
                                cursor.close();
                            }
                            return imageResultSuggestionCursor;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                        }
                        throw th;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:71:0x01ef  */
        public ImageResultSuggestionCursor doSimpleQueryWithRankValue(List<Suggestion> list, RankInfo rankInfo, QueryInfo queryInfo, String str) {
            String str2;
            Cursor cursor;
            int i;
            ArrayList arrayList;
            ArrayList arrayList2;
            ArrayList arrayList3;
            ArrayList<String> arrayList4 = new ArrayList<>();
            ArrayList arrayList5 = new ArrayList(list.size());
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            Iterator<Suggestion> it = list.iterator();
            while (true) {
                str2 = "#";
                if (!it.hasNext()) {
                    break;
                }
                Suggestion next = it.next();
                String suggestionExtra = getSuggestionExtra(next, "rankValue");
                if (!TextUtils.isEmpty(suggestionExtra)) {
                    str2 = suggestionExtra;
                }
                String suggestionExtra2 = getSuggestionExtra(next, "serverId");
                if (!TextUtils.isEmpty(suggestionExtra2)) {
                    if (!arrayList5.contains(suggestionExtra2)) {
                        arrayList5.add(suggestionExtra2);
                    }
                    if (!arrayList4.contains(str2)) {
                        arrayList4.add(str2);
                    }
                    hashMap.put(suggestionExtra2, str2);
                    String suggestionTitle = next.getSuggestionTitle();
                    if (!TextUtils.isEmpty(suggestionTitle)) {
                        hashMap2.put(suggestionExtra2, suggestionTitle);
                    }
                }
            }
            arrayList4.remove(str2);
            int i2 = 0;
            if ("DESC".equals(rankInfo.getOrder())) {
                arrayList4.add(0, str2);
            } else {
                arrayList4.add(str2);
            }
            HashMap hashMap3 = new HashMap();
            try {
                cursor = SearchImageResultHelper.this.mContext.getContentResolver().query(getQueryUri(false), SearchImageResultHelper.QUERY_PROJECTION, appendNotInSecretSelection(str), (String[]) null, (String) null);
                int i3 = 12;
                if (cursor != null) {
                    try {
                        if (cursor.getCount() > 0) {
                            for (int i4 = 0; i4 < cursor.getCount(); i4++) {
                                cursor.moveToPosition(i4);
                                String string = cursor.getString(12);
                                String str3 = (String) hashMap.get(string);
                                if (!TextUtils.isEmpty(str3)) {
                                    if (hashMap3.get(str3) == null) {
                                        hashMap3.put(str3, new SparseIntArray());
                                    }
                                    ((SparseIntArray) hashMap3.get(str3)).put(arrayList5.indexOf(string), i4);
                                }
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (hashMap3.size() > 0) {
                    ArrayList arrayList6 = new ArrayList(hashMap3.size());
                    ArrayList arrayList7 = new ArrayList();
                    ArrayList arrayList8 = new ArrayList();
                    for (String str4 : arrayList4) {
                        SparseIntArray sparseIntArray = (SparseIntArray) hashMap3.get(str4);
                        if (sparseIntArray == null || sparseIntArray.size() <= 0) {
                            arrayList2 = arrayList8;
                            arrayList = arrayList7;
                            arrayList3 = arrayList6;
                            i = i3;
                        } else {
                            MatrixCursor matrixCursor = new MatrixCursor(SearchImageResultHelper.OUT_PROJECTION);
                            int i5 = i2;
                            while (i5 < sparseIntArray.size()) {
                                cursor.moveToPosition(sparseIntArray.valueAt(i5));
                                arrayList7.add(Long.valueOf(cursor.getLong(i2)));
                                String str5 = (String) hashMap2.get(cursor.getString(i3));
                                if (!TextUtils.isEmpty(str5) && !arrayList8.contains(str5)) {
                                    arrayList8.add(str5);
                                }
                                matrixCursor.addRow(toRow(cursor, "visible"));
                                SearchLog.e("ImageResultProcessor", "On add image " + cursor.getString(i3));
                                i5++;
                                i2 = 0;
                            }
                            String str6 = str4;
                            arrayList2 = arrayList8;
                            arrayList = arrayList7;
                            i = i3;
                            arrayList3 = arrayList6;
                            arrayList3.add(toSection(queryInfo, matrixCursor, (Long) null, (Integer) null, TextUtils.join(SearchImageResultHelper.TITLE_SEPARATOR, arrayList8), (String) null, str6, (List<Long>) null, arrayList));
                            arrayList.clear();
                            arrayList2.clear();
                        }
                        arrayList6 = arrayList3;
                        arrayList8 = arrayList2;
                        arrayList7 = arrayList;
                        i3 = i;
                        i2 = 0;
                    }
                    ImageResultSuggestionCursor imageResultSuggestionCursor = new ImageResultSuggestionCursor(queryInfo, arrayList6, false);
                    imageResultSuggestionCursor.setNotificationUri(SearchImageResultHelper.this.mContext.getContentResolver(), cursor.getNotificationUri());
                    if (cursor != null) {
                        cursor.close();
                    }
                    return imageResultSuggestionCursor;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                }
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        public String getSuggestionExtra(Suggestion suggestion, String str) {
            if (suggestion == null || suggestion.getSuggestionExtras() == null) {
                return null;
            }
            return suggestion.getSuggestionExtras().getExtra(str);
        }

        /* access modifiers changed from: protected */
        public boolean supportExpand(RankInfo rankInfo) {
            return SearchImageResultHelper.this.mSupportExpand && (rankInfo == null || "date".equals(rankInfo.getName()));
        }

        /* access modifiers changed from: protected */
        public BaseSuggestion toSuggestion(Suggestion suggestion) {
            BaseSuggestion suggestion2 = super.toSuggestion(suggestion);
            if (suggestion2 != null) {
                MapBackedSuggestionExtras mapBackedSuggestionExtras = new MapBackedSuggestionExtras("serverId", Uri.parse(suggestion2.getIntentActionURI()).getQueryParameter("serverId"));
                if (!(suggestion.getSuggestionExtras() == null || suggestion.getSuggestionExtras().getExtraColumnNames() == null)) {
                    for (String next : suggestion.getSuggestionExtras().getExtraColumnNames()) {
                        mapBackedSuggestionExtras.putExtra(next, suggestion.getSuggestionExtras().getExtra(next));
                    }
                }
                suggestion2.setSuggestionExtras(mapBackedSuggestionExtras);
            }
            return suggestion2;
        }
    }

    public SearchImageResultHelper(Context context, QueryInfo queryInfo, boolean z, GroupedSuggestionCursor<SuggestionSection> groupedSuggestionCursor) {
        super(queryInfo, groupedSuggestionCursor);
        this.mContext = context;
        this.mSupportExpand = z;
    }

    public SearchImageResultHelper(Context context, QueryInfo queryInfo, boolean z, SuggestionSection suggestionSection) {
        super(queryInfo, suggestionSection);
        this.mContext = context;
        this.mSupportExpand = z;
    }

    /* access modifiers changed from: protected */
    public ImageResultProcessor createDataListResultProcessor(RankInfo rankInfo) {
        return new ImageResultProcessor(rankInfo);
    }

    /* access modifiers changed from: protected */
    public ResultProcessor<SuggestionResult> createFilterProcessor() {
        return new FilterProcessor();
    }

    /* access modifiers changed from: protected */
    public int getDataLoadCount(QueryInfo queryInfo) {
        return queryInfo.getSearchType() == SearchConstants.SearchType.SEARCH_TYPE_FEEDBACK_LIKELY_RESULT ? SearchConfig.get().getResultConfig().getLikelyImageLoadCount() : SearchConfig.get().getResultConfig().getImageLoadCount();
    }
}
